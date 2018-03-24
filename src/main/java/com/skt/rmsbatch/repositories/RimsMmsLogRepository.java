package com.skt.rmsbatch.repositories;

import com.skt.rmsbatch.domain.RimsMmsLog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sanjay on 7/27/2016.
 */
@Repository
public interface RimsMmsLogRepository extends CrudRepository<RimsMmsLog, Long> {
}
