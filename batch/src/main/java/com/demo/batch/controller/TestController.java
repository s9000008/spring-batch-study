package com.demo.batch.controller;

import com.demo.batch.job.DemoJob;
import com.demo.batch.job.OrderJob;
import com.demo.batch.job.ProductReportJob;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("job")
public class TestController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private DemoJob job;

    @Autowired
    private OrderJob orderJob;

    @Autowired
    private ProductReportJob productReportJob;
    @Autowired
    private JobRepository jobRepository;
    @GetMapping("/job1")
    public String runJob1() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", new Date());
        JobParameters jobParameters = builder.toJobParameters();
        jobLauncher.run(orderJob.finalOrderJob(),jobParameters);
        return "Job1 success.";
    }
    @GetMapping("/job2")
    public String runJob2() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", new Date());
        jobLauncher.run(productReportJob.productReportJob(),builder.toJobParameters());
        return "Job2 success.";
    }
    @GetMapping("/demo")
    public String orders() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", new Date());
        jobLauncher.run(job.jobFlowDemo1(),builder.toJobParameters());
        return "Demo success.";
    }

}
