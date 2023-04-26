package com.demo.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoJob {
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;
    public Job jobFlowDemo1(){
        System.out.println("test");
        return jobBuilderFactory.get("jobFlowDemo1")
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

    public Step step1() {
        System.out.println("STEP 1");
        return stepBuilderFactory.get("step1").tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Step-1");
                        return RepeatStatus.FINISHED;
                    }
                }
        ).build();
    }

    public Step step2() {
        return stepBuilderFactory.get("step2").tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Step-2");
                        return RepeatStatus.FINISHED;
                    }
                }
        ).build();
    }

    public Step step3() {
        return stepBuilderFactory.get("step3").tasklet(
                new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("Step-3");
                        return RepeatStatus.FINISHED;
                    }
                }
        ).build();
    }
}
