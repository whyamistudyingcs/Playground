package com.example.playground.Domain.Scheduling;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

@Slf4j
public class PingJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap dataMap = context.getMergedJobDataMap();
        log.info("Executing PingJob with target: {} and cron expression: {}",
                dataMap.getString("target"),
                dataMap.getString("cronExpression") );
        ping();
    }

    private void ping() {
        try {
            String host = "www.google.com";
            InetAddress inet = InetAddress.getByName(host);
            if (inet.isReachable(5000)) {
                log.info("Ping to {} successful", host);
            } else {
                log.warn("Ping to {} failed", host);
            }
        } catch (Exception e) {
            log.error("Error occurred while pinging: {}", e.getMessage());
        }
    }
}
    