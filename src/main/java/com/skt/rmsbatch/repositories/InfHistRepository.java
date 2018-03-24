package com.skt.rmsbatch.repositories;

import com.skt.rmsbatch.domain.InfHist;
import com.skt.rmsbatch.domain.RiskMasterInfo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sanjay on 7/27/2016.
 */
@Repository
public interface InfHistRepository extends CrudRepository<InfHist, Long> {
	
	public List<InfHist> findByJobNameAndResultNotAndExecutionDateBetween(String jobName, String result, Date startDate, Date endDate);
	
}
