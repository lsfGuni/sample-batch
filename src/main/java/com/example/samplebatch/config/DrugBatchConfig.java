package com.example.samplebatch.config;

import com.example.samplebatch.batch.DrugApiReader;
import com.example.samplebatch.entity.DrugEntity;
import com.example.samplebatch.repository.DrugRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class DrugBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final DrugApiReader drugApiReader;
    private final DrugRepository drugRepository;

    public DrugBatchConfig(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           DrugApiReader drugApiReader,
                           DrugRepository drugRepository) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.drugApiReader = drugApiReader;
        this.drugRepository = drugRepository;
    }

    @Bean
    public Job drugJob() {
        return new JobBuilder("drugJob", jobRepository)
                .start(drugStep())
                .build();
    }

    @Bean
    public Step drugStep() {
        return new StepBuilder("drugStep", jobRepository)
                .<DrugEntity, DrugEntity>chunk(10, transactionManager)
                .reader(drugApiReader)
                .processor(drugProcessor())
                .writer(drugWriter())
                .build();
    }

    @Bean
    public ItemProcessor<DrugEntity, DrugEntity> drugProcessor() {
        return item -> item; // 현재는 특별한 처리 없이 그대로 저장
    }

    @Bean
    public ItemWriter<DrugEntity> drugWriter() {
        return new RepositoryItemWriterBuilder<DrugEntity>()
                .repository(drugRepository)
                .methodName("save")
                .build();
    }
}

