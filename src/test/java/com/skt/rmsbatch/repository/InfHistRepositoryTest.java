package com.skt.rmsbatch.repository;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skt.rmsbatch.RepositoryConfiguration;
import com.skt.rmsbatch.repositories.InfHistRepository;
import com.skt.rmsbatch.utils.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={ RepositoryConfiguration.class} )
public class InfHistRepositoryTest {
	
//	@Autowired
//	@Qualifier("entityManagerFactory")
//	private EntityManager entityManager;
//	
//    private InfHistRepository infHistRepository;
    
//    @Autowired	
//    private DateUtils dateUtils;
    
	@Test
    public void init() {
		
		System.out.println("test");
		
		
//        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
//
//        infHistRepository = jpaRepositoryFactory.getRepository(InfHistRepository.class);
        
        //assertNotNull(infHistRepository);
        
//        System.out.println(infHistRepository);

        // In case your need to initialize a custom repository use this
//        OtherRepositoryCustom otherRepoImpl = new OtherRepositoryImpl();
//        otherRepository = jpaRepositoryFactory.getRepository(OtherRepository.class, otherRepoImpl);
//        assertNotNull(otherRepository);
    }
	

//    @Autowired
//    public void setProductRepository(InfHistRepository infHistRepository) {
//        this.infHistRepository = infHistRepository;
//    }

}
