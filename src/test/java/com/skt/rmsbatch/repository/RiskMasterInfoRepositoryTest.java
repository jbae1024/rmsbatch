package com.skt.rmsbatch.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skt.rmsbatch.RepositoryConfiguration;
import com.skt.rmsbatch.domain.RiskMasterInfo;
import com.skt.rmsbatch.repositories.RiskMasterInfoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={ RepositoryConfiguration.class} )
public class RiskMasterInfoRepositoryTest {
	
	@Autowired
	@Qualifier("entityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
    private RiskMasterInfoRepository riskMasterInfoRepository;
    
	@Before
    public void init() {
		assertNotNull(riskMasterInfoRepository);
    }
	
	@Test
	public void getCauseNullRecord(){
		
//		List<RiskMasterInfo> riskMasterInfos  = riskMasterInfoRepository.findByRiskCauseNull();
//		
//		assertThat(riskMasterInfoRepository.findByRiskCauseNull().size(), is(0));
//		
//		for(RiskMasterInfo riskMasterInfo: riskMasterInfos ){
//			
//			System.out.println(riskMasterInfo.toString());
//			
//		}
//
//		assertThat(riskMasterInfoRepository.findByRiskCauseNullAndRiskTitle("일부 판매점 스캔등록 메뉴 일부 선택불가 장애 발생").size(), is(0));
		
	}
	
	
	

}
