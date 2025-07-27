package com.example.playground.Infrastructure.Service;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;

import com.example.playground.Infrastructure.Repository.TaskEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchedulingService {
    private final Scheduler scheduler;

    public SchedulingService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void scheduleJob(Class <? extends Job> jobClass, TaskEntity taskEntity) throws SchedulerException {
        JobBuilder jobBuilder = JobBuilder.newJob(jobClass);
        jobBuilder.withIdentity(taskEntity.getTaskName(), taskEntity.getTarget());
        JobDetail jobDetail = jobBuilder
            .withIdentity(taskEntity.getTaskName(), taskEntity.getTarget())
            .usingJobData("target", taskEntity.getTarget())
            .usingJobData("cronExpression", taskEntity.getCronExpression())
            .build();
         Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity(taskEntity.getTaskName() + "Trigger", taskEntity.getTarget())
            .withSchedule(CronScheduleBuilder.cronSchedule(taskEntity.getCronExpression()))
            .build();
        if (scheduler.checkExists(getJobKey(taskEntity))) {
            scheduler.rescheduleJob(trigger.getKey(), trigger);
            log.info("Task {} - {} rescheduled with new trigger successfully", taskEntity.getTaskName(), taskEntity.getTarget());
        } else {
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("Task {} - {} scheduled successfully", taskEntity.getTaskName(), taskEntity.getTarget());
        }
    }

    public boolean unScheduleJob(TaskEntity taskEntity) {
        try {
            boolean isDeleted = scheduler.deleteJob(getJobKey(taskEntity));
            log.info("Task {} - {} with cronExpression {} removed from scheduler successfully", taskEntity.getTaskName(), taskEntity.getTarget(), taskEntity.getCronExpression());
            return isDeleted;
        } catch (SchedulerException e) {
            log.error("Error when deleting task {} - {} from scheduler", taskEntity.getTaskName(), taskEntity.getTarget(), e);
            return false;
        }
    }
    
    private JobKey getJobKey(TaskEntity taskEntity) {
        return JobKey.jobKey(taskEntity.getTaskName(), taskEntity.getTarget());
    }
}
