package com.canon.cusa.s21.common.NLX.NLXC901001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.common.S21OracleSeqAccessor;

/**
 * NLCPurgeNumberUtil
 */
public final class NLCPurgeNumberUtil {

    private NLCPurgeNumberUtil() {
    }

    /**
     * This method will return ARC_RQST_SQ for ARC_RQST_PK.
     * @return BigDecimal
     */
    public static BigDecimal getArcRqstPk() {
        return S21OracleSeqAccessor.getSeqNumber("ARC_RQST_SQ");
    }

    /**
     * This method will return ARC_RQST_REC_SQ for ARC_RQST_REC_PK.
     * @return BigDecimal
     */
    public static BigDecimal getArcRqstRecPk() {
        return S21OracleSeqAccessor.getSeqNumber("ARC_RQST_REC_SQ");
    }
}
