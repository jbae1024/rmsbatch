package com.skt.rmsbatch.repositories;

import com.skt.rmsbatch.domain.RiskMasterInfo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

/**
 * Created by Sanjay on 7/27/2016.
 */
@Repository
public interface RiskMasterInfoRepository extends CrudRepository<RiskMasterInfo, String> {
	
	List<RiskMasterInfo> findByRiskMngNum(String riskMngNum);
	
	List<RiskMasterInfo> findByRiskCauseAndStartDateBetween(String riskCause, Date startDate, Date endDate);
	
	@Modifying
	@Transactional
	@Query("update RiskMasterInfo u set u.riskCause = ?1 where u.riskMngNum = ?2")
	void updateRiskCauseByRiskMngNum(String riskCause, String riskMngNum);
	
}
