package com.jbjohn.spring.batch.datastore;

import com.jbjohn.spring.objects.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Batch config
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public ItemReader<Employee> reader() {
        return new BatchReader();
    }

    @Bean
    public ItemProcessor<Employee, Employee> processor() {
        return new BatchProcessor();
    }

    @Bean
    public ItemWriter<Employee> writer() {
        return new BatchUpdater();
    }

    @Bean
    public Job updateEmployee(JobBuilderFactory jobs, @Qualifier("update") Step s1) {
        return jobs.get("updateEmployee")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step update(StepBuilderFactory stepBuilderFactory, ItemReader<Employee> reader,
                      ItemWriter<Employee> writer, ItemProcessor<Employee, Employee> processor) {
        return stepBuilderFactory.get("update")
                .<Employee, Employee>chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
