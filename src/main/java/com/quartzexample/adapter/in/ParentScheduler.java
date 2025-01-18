package com.quartzexample.adapter.in;

import com.quartzexample.adapter.out.job.ParentJob;
import lombok.RequiredArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ParentScheduler {

    /*
        JobDetail 과 Trigger 를 Bean 으로 생성하면
        Spring 에서 자동으로 Scheduler 에 등록 해줍니다.
     */
    @Bean
    public JobDetail parentJob() {
        return JobBuilder.newJob(ParentJob.class)
            .withIdentity("parentJob")
            .storeDurably()
            .build();
    }

    @Bean
    public Trigger parentTrigger(JobDetail testJobDetail) {
        return TriggerBuilder.newTrigger()
            .forJob(testJobDetail)
            .withIdentity("parentTrigger")
            .startNow()
            .build();
    }
}
