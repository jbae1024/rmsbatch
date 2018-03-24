package com.skt.rmsbatch.riskmaster.service;

import com.skt.rmsbatch.domain.InfHist;
import com.skt.rmsbatch.domain.RiskMasterInfo;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * <b>History:</b>
 *    작성자 : jinhwancom, 1.0, 2018. 3. 24. Init
 * </pre>
 *
 * @author 최종 수정자
 * @version 1.0, 2018. 3. 24. Init
 * @see
 */
public class SkServiceImpl implements HiomsService{


    @Override
    public List<InfHist> readFailRiskMasterInfoList(String periodDay) {
        return null;
    }

    @Override
    public List<RiskMasterInfo> readRiskCauseIsNullList(String periodDay) {
        return null;
    }

    @Override
    public void writeInfHist(InfHist infHist) {

    }

    @Override
    public void updateRiskCauseByRiskMngNum(List<Map<String, Object>> riskMngNumList) {

    }

    @Override
    public int findByRiskMngNum(String riskMngNum) {
        return 0;
    }
}
