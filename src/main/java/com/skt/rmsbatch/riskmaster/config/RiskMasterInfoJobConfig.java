package com.skt.rmsbatch.riskmaster.config;

import com.skt.rmsbatch.riskmaster.listener.RiskMasterInfoListener;
import com.skt.rmsbatch.riskmaster.tasklet.ResponseMakeXmlTasklet;
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
public class RiskMasterInfoJobConfig {
	
	@Bean
    RiskMasterInfoInsertTasklet riskMasterInfoInsertTasklet(){
    	return new RiskMasterInfoInsertTasklet();
    }
    
    @Bean
    ResponseMakeXmlTasklet responseMakeXmlTasklet(){
    	return new ResponseMakeXmlTasklet();
    }
    
    
    @Bean
    Step riskMasterInfoTaskletStep( StepBuilderFactory stepBuilderFactory, 
    								RiskMasterInfoInsertTasklet riskMasterInfoInsertTasklet	) {
		return stepBuilderFactory.get("riskMasterInfoTaskletStep")
				.tasklet(riskMasterInfoInsertTasklet)
				.build();
	}
    
    @Bean
    Step responseMakeXmlTaskletStep( StepBuilderFactory stepBuilderFactory, 
    									ResponseMakeXmlTasklet responseMakeXmlTasklet	) {
		return stepBuilderFactory.get("responseMakeXmlTaskletStep")
				.tasklet(responseMakeXmlTasklet)
				.build();
	}
    

    @Bean
    Job riskMasterInfoJob(JobBuilderFactory jobBuilderFactory,
                       @Qualifier("riskMasterInfoTaskletStep") Step riskMasterInfoTaskletStep,
                       @Qualifier("responseMakeXmlTaskletStep") Step responseMakeXmlTaskletStep) {
    	
        return jobBuilderFactory.get("riskMasterInfoJob")
                .incrementer(new RunIdIncrementer())
                .listener(reskMasterInfolistener())
                .flow(responseMakeXmlTaskletStep)
                .next(riskMasterInfoTaskletStep)
                .end()
                .build();
    }
    
    
    @Bean
    Job riskCauseJob(JobBuilderFactory jobBuilderFactory,
                       @Qualifier("riskMasterInfoTaskletStep") Step riskMasterInfoTaskletStep,
                       @Qualifier("responseMakeXmlTaskletStep") Step responseMakeXmlTaskletStep) {
    	
        return jobBuilderFactory.get("riskCauseJob")
                .incrementer(new RunIdIncrementer())
                .flow(responseMakeXmlTaskletStep)
                .next(riskMasterInfoTaskletStep)
                .end()
                .build();
    }
    
    
    @Bean
    Job reRiskMasterInfoJob(JobBuilderFactory jobBuilderFactory,
                       @Qualifier("riskMasterInfoTaskletStep") Step riskMasterInfoTaskletStep,
                       @Qualifier("responseMakeXmlTaskletStep") Step responseMakeXmlTaskletStep) {
    	
        return jobBuilderFactory.get("reRiskMasterInfoJob")
                .incrementer(new RunIdIncrementer())
                .flow(responseMakeXmlTaskletStep)
                .next(riskMasterInfoTaskletStep)
                .end()
                .build();
    }
    
    
    @Bean
    public JobExecutionListener reskMasterInfolistener() {
        return new RiskMasterInfoListener();
    }
    
}
