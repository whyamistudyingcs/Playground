###example repo
https://github.com/mewebstudio/java-spring-boot-quartz-impl/tree/main

- The Job interface is all about what the job does when it runs—like sending an email or processing a file. It’s the core logic of the task.
- The JobDetail object, on the other hand, is about how the job is managed. It holds metadata like the job’s identity (via a JobKey), runtime data (via a JobDataMap), and settings like whether the job is durable (persists even without triggers).

- example config: https://www.quartz-scheduler.org/documentation/quartz-2.3.0/configuration/ConfigJDBCJobStoreClustering.html

- config ref: https://www.quartz-scheduler.org/documentation/quartz-2.2.2/configuration/index.html

```java
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class)
                .withIdentity("myJob", "group1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger myTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
                .build();
    }
}
```