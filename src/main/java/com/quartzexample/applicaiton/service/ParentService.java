package com.quartzexample.applicaiton.service;

import static org.quartz.TriggerBuilder.newTrigger;

import com.quartzexample.adapter.out.job.ChildJob;
import com.quartzexample.applicaiton.port.in.ParentUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class ParentService implements ParentUseCase {

    private final Scheduler scheduler;

    @Override
    public void process() {
        log.info("hello from parent");

        for (int i = 0; i < 10; i++) {
            String name = i == 0 ? "error" + UUID.randomUUID().toString() :
                UUID.randomUUID().toString();
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("name", i == 0 ? "error" : UUID.randomUUID().toString());

            JobDetail jobDetail = JobBuilder.newJob(ChildJob.class)
                .withIdentity("childJob-" + name) // 해당 값으로 Job 을 등록하고 관리 (유니크 해야 함)
                .storeDurably() // Trigger 없이 JobKey 만으로 실행 가능 하도록 하는 옵션
                .setJobData(jobDataMap)
                .build();

            Trigger trigger = newTrigger()
                .withIdentity("childTrigger-" + name)
                .startNow() // 즉시 실행
                //.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")) // cron 표현식으로 실행
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(10) // 10초마다 실행
                    // .repeatForever()
                    .withRepeatCount(3)) // 재시도 횟수 (재시도 포함 총 4번 실행)
                .build();

            try {
                scheduler.scheduleJob(jobDetail, trigger);
                // scheduler.triggerJob(JobKey.jobKey(jobDetail.getKey().getName())); trigger 없이 단발성으로 실행
            } catch (Exception ignored) {
            }
        }
    }
}
