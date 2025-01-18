package com.quartzexample.adapter.out.job;

import com.quartzexample.applicaiton.port.in.ParentUseCase;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@RequiredArgsConstructor
public class ParentJob implements Job {

    private final ParentUseCase parentUseCase;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        parentUseCase.process();
    }
}
