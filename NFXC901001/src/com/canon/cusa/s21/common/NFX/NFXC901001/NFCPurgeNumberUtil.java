/**
 * <pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2011   Fujitsu         I.Kondo         Create          N/A
 * </pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC901001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.common.S21OracleSeqAccessor;

/**
 * NFCPurgeNumberUtil
 */
public final class NFCPurgeNumberUtil {

    private NFCPurgeNumberUtil() {
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
