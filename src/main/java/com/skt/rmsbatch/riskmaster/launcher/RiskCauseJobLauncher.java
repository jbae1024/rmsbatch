package com.skt.rmsbatch.riskmaster.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jincom on 7/24/2016.
 */
@Component
public class RiskCauseJobLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RiskCauseJobLauncher.class);

    private final Job job;

    private final JobLauncher jobLauncher;
    
    @Autowired
    RiskCauseJobLauncher(@Qualifier("riskCauseJob") Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

    @Scheduled(cron = "${hioms.riskCauseJob.cron}")
    //@Scheduled(cron="0 0 02 * * ?") = 매일 새벽2시에 실행
    //@Scheduled(cron="0 0 02 2,20 * ?") = 매월 2일,20일 새벽2시에 실행
    //@Scheduled(fixedRate = 5000)
    void launchXmlFileToDatabaseJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        LOGGER.info("Starting RiskCauseJob job");

        jobLauncher.run(job, newExecution());

        LOGGER.info("Stopping RiskCauseJob job");
    }
    
    private JobParameters newExecution() {
    	
        Map<String, JobParameter> parameters = new HashMap<>();
        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);
        return new JobParameters(parameters);
        
    }
    
}
