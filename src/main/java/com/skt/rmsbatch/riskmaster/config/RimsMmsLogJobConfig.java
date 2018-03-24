package com.skt.rmsbatch.riskmaster.config;

import com.skt.rmsbatch.riskmaster.listener.RiskMasterInfoListener;
import com.skt.rmsbatch.riskmaster.tasklet.ResponseMakeXmlTasklet;
import com.skt.rmsbatch.riskmaster.tasklet.RimsMmsLogInsertTasklet;
import com.skt.rmsbatch.riskmaster.tasklet.RiskMasterInfoInsertTasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jincom on 7/24/2016.
 */
@Configuration
public class RimsMmsLogJobConfig {
    
    @Bean
    RimsMmsLogInsertTasklet rimsMmsLogInsertTasklet(){
    	return new RimsMmsLogInsertTasklet();
    }
    
    @Bean
    Step rimsMmsLogInsertTaskletStep( StepBuilderFactory stepBuilderFactory, 
    									RimsMmsLogInsertTasklet rimsMmsLogInsertTasklet	) {
		return stepBuilderFactory.get("rimsMmsLogInsertTaskletStep")
				.tasklet(rimsMmsLogInsertTasklet)
				.build();
	}
   
    @Bean
    Job rimsMmsLogJob(JobBuilderFactory jobBuilderFactory,
                       @Qualifier("rimsMmsLogInsertTaskletStep") Step rimsMmsLogInsertTaskletStep) {
        return jobBuilderFactory.get("rimsMmsLogJob")
                .incrementer(new RunIdIncrementer())
                .flow(rimsMmsLogInsertTaskletStep)
                .end()
                .build();
    }
    
}
