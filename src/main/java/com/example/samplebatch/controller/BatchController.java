package com.example.samplebatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job drugJob;

    public BatchController(JobLauncher jobLauncher, Job drugJob) {
        this.jobLauncher = jobLauncher;
        this.drugJob = drugJob;
    }

    @GetMapping("/run-drug-job")
    public String runDrugJob(@RequestParam("date") String date) throws Exception {
        jobLauncher.run(drugJob, new JobParametersBuilder()
                .addString("date", date)
                .toJobParameters());
        return "Batch job has been invoked";
    }
}
