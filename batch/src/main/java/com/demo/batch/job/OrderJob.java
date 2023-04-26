package com.demo.batch.job;

import com.demo.batch.entity.FinalOrder;
import com.demo.batch.entity.Orders;
import com.demo.batch.processor.OrdersProcessor;
import com.demo.batch.provider.OrdersProvider;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
//@Transactional(isolation = Isolation.SERIALIZABLE)
@Component
public class OrderJob {
    @Autowired
    private OrdersProcessor processor;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private OrdersProvider provider;
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobListener jobListener;
    @Autowired
    JobRepository jobRepository;

    @Autowired
    ProductReportJob productReportJob;
    public JpaPagingItemReader<Orders> reader(){
        System.out.println("Orders - reader");
        JpaPagingItemReader<Orders> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryProvider(provider);
        reader.setPageSize(100);
        return reader;
    }

    public JpaItemWriter<FinalOrder> writer(){
        JpaItemWriter<FinalOrder> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
    public Job finalOrderJob(
    ) throws Exception {
        return new JobBuilder("statistics")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .flow(step1(stepBuilderFactory, jobRepository))
                //.next(productReportJob.step1(stepBuilderFactory, jobRepository))
                .end()
                .repository(jobRepository)
                .build();
    }
    public Step step1(StepBuilderFactory stepBuilderFactory, JobRepository jobRepository) throws Exception {
        return stepBuilderFactory
                .get("orderStep1")
                .<Orders, FinalOrder> chunk(100)
                .reader(reader())
                .processor(processor)
                .writer(writer())
                .repository(jobRepository)
                .build();
    }
}
