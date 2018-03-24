package com.skt.rmsbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBatchProcessing 
@ComponentScan
@EnableScheduling
@PropertySource("classpath:batch.properties")
public class SpringRestBatchApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringRestBatchApplication.class, args);
	}
}
