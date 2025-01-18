package com.quartzexample.adapter.out.job;

import com.quartzexample.applicaiton.port.in.ChildUseCase;
import com.quartzexample.domain.TestDTO;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;

@RequiredArgsConstructor
public class ChildJob implements Job {

    private final ChildUseCase childUseCase;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap dataMap = context.getMergedJobDataMap();
            childUseCase.process(TestDTO.builder()
                .name(dataMap.getString("name"))
                .build());

            Scheduler scheduler = context.getScheduler();
            scheduler.deleteJob(context.getJobDetail().getKey()); // 스케쥴러에서 키를 삭제하면 재시도 작업을 하지 않습니다.
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
