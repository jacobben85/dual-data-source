package com.jbjohn.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Batch Scheduler
 */
@Component
public class BatchScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchScheduler.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Scheduled(cron = "${batch.cron.expression}")
    public void batchRunner() {
        try {
            String dateParam = new Date().toString();
            JobParameters param = new JobParametersBuilder().addString("date", dateParam).toJobParameters();
            JobExecution execution = jobLauncher.run(job, param);
        } catch (Exception e) {
            LOGGER.error("Exception running batch", e);
        }
    }
}
