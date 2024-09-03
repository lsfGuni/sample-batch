package com.example.samplebatch;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstSchedule {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

    public FirstSchedule(JobLauncher jobLauncher, JobRegistry jobRegistry) {
        this.jobLauncher = jobLauncher;
        this.jobRegistry = jobRegistry;
    }

//    @Scheduled(cron = "10 * * * * *", zone = "Asia/Seoul")
//    public void runFirstJob() throws Exception {
//
//        System.out.println("first schedule start");
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
//        String date = dateFormat.format(new Date());
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("date", date)
//                .toJobParameters();
//
//        jobLauncher.run(jobRegistry.getJob("firstJob"), jobParameters);
//    }
}