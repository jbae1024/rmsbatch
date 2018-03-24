package com.skt.rmsbatch.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Multi DataSource를 위한  entityManagerFactory 설정
 * @author jinhwancom
 *
 */
@Configuration
public class DataBaseConfig {
	
	private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

	/************************************************************************************/
	/** Primary entityManagerFactoryUser **/
	/************************************************************************************/
	
	/**
	 * rms 데이터 소스 생성
	 * @return
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.rms")
	public DataSource rmsDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * entityManagerFactory Bean 생성
	 * @param builder
	 * @return
	 */
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

		Map<String, String> propertiesHashMap = new HashMap<>();
		propertiesHashMap.put("hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY);

		return builder.dataSource(rmsDataSource())
				.packages("com.skt.rmsbatch.domain")
				.properties(propertiesHashMap)
				.build();
	}
	
	/**
	 * transactionManager 생성
	 * @param builder
	 * @return
	 */
	@Primary
	@Bean(name = "transactionManager")
	PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactory(builder).getObject());
	}
	

	/**
	 * JPA Config
	 * @author jinhwancom
	 *
	 */
	@Configuration
	@EnableJpaRepositories(basePackages="com.skt.rmsbatch.repositories",
						   entityManagerFactoryRef = "entityManagerFactory", 
						   transactionManagerRef = "transactionManager")
	static class DbArticleJpaRepositoriesConfig {
	}
	
	
	/************************************************************************************/
	/** Second entityManagerFactoryUser **/
	/************************************************************************************/
	@Bean
	@ConfigurationProperties(prefix = "datasource.rims")
	public DataSource rimsDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "entityManagerFactoryRims")
	public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder) {

		return builder.dataSource(rimsDataSource())
				.packages("com.skt.rmsbatch.rims.domain")
				.build();
	}

	@Bean(name = "transactionManagerRims")
	PlatformTransactionManager userTransactionManagerMain(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(userEntityManagerFactory(builder).getObject());
	}
	
	@Configuration
	@EnableJpaRepositories(
			basePackages="com.skt.rmsbatch.rims.repositories",
			entityManagerFactoryRef = "entityManagerFactoryRims",
			transactionManagerRef = "transactionManagerRims")
	static class DbUserJpaRepositoriesConfig {
	}
	
}
