package com.demo.batch.job;

import com.demo.batch.entity.OrderItem;
import com.demo.batch.entity.ProductReport;
import com.demo.batch.processor.ProductReportProcessor;
import com.demo.batch.provider.OrderItemProvider;
import javax.persistence.EntityManagerFactory;

import com.demo.batch.writer.ProductReportWriter;
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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.SERIALIZABLE)
@Component
public class ProductReportJob {
    @Autowired
    private ProductReportProcessor processor;
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private OrderItemProvider provider;
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    JobListener jobListener;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    ProductReportWriter writer;
    public JpaPagingItemReader<OrderItem> orderItemReader(){
        JpaPagingItemReader<OrderItem> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryProvider(provider);
        reader.setPageSize(100);
        return reader;
    }
    public Job productReportJob() {
        return new JobBuilder("importProductReport")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .flow(step1(stepBuilderFactory, jobRepository))
                .end()
                .repository(jobRepository)
                .build();
    }
    public Step step1(StepBuilderFactory stepBuilderFactory,
                                   JobRepository jobRepository) {
        return stepBuilderFactory.get("productReportSteo1")
                .<OrderItem, ProductReport> chunk(100)
                .reader(orderItemReader())
                .processor(processor)
                .writer(writer)
                .repository(jobRepository)
                .build();
    }
}
