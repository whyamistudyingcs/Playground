package com.example.playground.Infrastructure.Service;

import com.example.playground.Domain.Config.SchedulingConfig;
import com.example.playground.Domain.Config.SchedulingConfig.JobConfig;
import com.example.playground.Infrastructure.Repository.TaskEntity;
import com.example.playground.Infrastructure.Repository.TaskRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulingTaskEntityService {
    private final SchedulingConfig schedulingConfig;
    private final TaskRepository taskRepository;
    private final SchedulingService schedulingService;

    @PostConstruct
    public void init() {
        syncTasksWithConfig();
        log.info("SchedulingTaskEntityService initialized and tasks synced with config.");
    }
    
    @Transactional
    public void syncTasksWithConfig() {
        // 1. Load config tasks
        Map<String, Map<String, JobConfig>> configTasks = schedulingConfig.getJobs();
        Map<String, TaskEntity> configTaskEntities = new HashMap<>();
        for (Map.Entry<String, Map<String, JobConfig>> taskEntry : configTasks.entrySet()) {
            String taskName = taskEntry.getKey();
            Map<String, JobConfig> targets = taskEntry.getValue();
            for (Map.Entry<String, JobConfig> targetEntry : targets.entrySet()) {
                String target = targetEntry.getKey();
                JobConfig cfg = targetEntry.getValue();
                TaskEntity entity = new TaskEntity();
                entity.setTaskName(taskName);
                entity.setTarget(target);
                entity.setEnabled(cfg.getEnabled());
                entity.setCronExpression(cfg.getCronExpression());
                configTaskEntities.put(taskKey(taskName, target), entity);
            }
        }

        // 2. Load persisted tasks
        List<TaskEntity> persistedTasks = taskRepository.findAll();
        Map<String, TaskEntity> persistedTaskMap = persistedTasks.stream()
                .collect(Collectors.toMap(t -> taskKey(t.getTaskName(), t.getTarget()), t -> t));

        // 3. Unschedule and remove tasks not in config or disabled
        for (TaskEntity persisted : persistedTasks) {
            String key = taskKey(persisted.getTaskName(), persisted.getTarget());
            TaskEntity configEntity = configTaskEntities.get(key);
            if (configEntity == null || Boolean.FALSE.equals(configEntity.getEnabled())) {
                // Unschedule and delete
                schedulingService.unScheduleJob(persisted);
                taskRepository.delete(persisted);
                log.info("Task {}-{} removed (not in config or disabled)", persisted.getTaskName(), persisted.getTarget());
            }
        }

        // 4. Upsert and schedule enabled tasks from config
        for (TaskEntity configEntity : configTaskEntities.values()) {
            if (Boolean.TRUE.equals(configEntity.getEnabled())) {
                TaskEntity persisted = persistedTaskMap.get(taskKey(configEntity.getTaskName(), configEntity.getTarget()));
                if (persisted == null) {
                    // New task
                    persisted = new TaskEntity();
                    persisted.setTaskName(configEntity.getTaskName());
                    persisted.setTarget(configEntity.getTarget());
                }
                persisted.setEnabled(true);
                persisted.setCronExpression(configEntity.getCronExpression());
                taskRepository.save(persisted);
                // Reschedule
                try {
                    schedulingService.scheduleJob(
                        // You may want to map taskName to jobClass, here using PingJob for example
                        com.example.playground.Domain.Scheduling.PingJob.class,
                        persisted
                    );
                } catch (Exception e) {
                    log.error("Failed to schedule task {}-{}: {}", persisted.getTaskName(), persisted.getTarget(), e.getMessage());
                }
            }
        }
    }

    private String taskKey(String taskName, String target) {
        return taskName + "::" + target;
    }
}
