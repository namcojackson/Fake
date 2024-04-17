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

import business.db.ARC_RQSTTMsg;
import business.db.ARC_RQST_RECTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * NFCPurgeArcMsgUtil.
 */
public final class NFCPurgeArcMsgUtil {

    /** REC_RETEN_START_YR_MTH_LENGTH */
    private static final int REC_RETEN_START_YR_MTH_LENGTH = 6;

    private NFCPurgeArcMsgUtil() {
    }

    /**
     * This method will return ARC_RQSTTMsg.</br> If null parameter
     * include, then will return null.
     * @param glblCmpyCd String
     * @param srcTblNm String
     * @param srcSchmNm String
     * @param arcRqstPk BigDecimal
     * @param recRetenStartYrMth String
     * @return ARC_RQSTTMsg
     */
    public static ARC_RQSTTMsg createArcRqst(String glblCmpyCd, String srcTblNm, String srcSchmNm, BigDecimal arcRqstPk, String recRetenStartYrMth) {
        try {
            if (null == glblCmpyCd) {
                throw new IllegalArgumentException("glblCmpyCd is null.");
            }
            if (null == srcTblNm) {
                throw new IllegalArgumentException("srcTblNm is null.");
            }
            if (null == arcRqstPk) {
                throw new IllegalArgumentException("arcRqstPk is null.");
            }

            if (null == srcSchmNm) {
                throw new IllegalArgumentException("srcSchmNm is null.");
            }

            // check recRetenStartYrMth
            if (null == recRetenStartYrMth) {
                throw new IllegalArgumentException("srcTblNm is null.");
            } else if (REC_RETEN_START_YR_MTH_LENGTH != recRetenStartYrMth.length()) {
                throw new IllegalArgumentException("srcTblNm length = " + recRetenStartYrMth.length());
            }
        } catch (Exception e) {
            throw new S21AbendException(e);
        }

        ARC_RQSTTMsg arcRqst = new ARC_RQSTTMsg();
        arcRqst.glblCmpyCd.setValue(glblCmpyCd);
        arcRqst.arcRqstPk.setValue(arcRqstPk);
        arcRqst.srcSchmNm.setValue(srcSchmNm);
        arcRqst.srcTblNm.setValue(srcTblNm);
        arcRqst.recRetenStartYrMth.setValue(recRetenStartYrMth);

        return arcRqst;
    }

    /**
     * This method will return ARC_RQST_RECTMsg.</br> If null
     * parameter include, then will return null.
     * @param arcRqst ARC_RQSTTMsg
     * @param condSqlTxt String
     * @return ARC_RQST_RECTMsg
     */
    public static ARC_RQST_RECTMsg createArcRqstRec(ARC_RQSTTMsg arcRqst, String condSqlTxt) {
        try {
            // check arcRqst
            if (null == arcRqst) {
                throw new IllegalArgumentException("arcRqst is null.");
            } else if (!ZYPCommonFunc.hasValue(arcRqst.glblCmpyCd) || 0 == arcRqst.glblCmpyCd.getValue().length()) {
                throw new IllegalArgumentException("arcRqst(glblCmpyCd) is invalid data.");
            } else if (!ZYPCommonFunc.hasValue(arcRqst.arcRqstPk) || arcRqst.arcRqstPk.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("arcRqst(arcRqstPk) is invalid data.");
            }

            // check condSqlTxt
            if (null == condSqlTxt || 0 == condSqlTxt.length()) {
                throw new IllegalArgumentException("condSqlTxt is null.");
            }
        } catch (Exception e) {
            throw new S21AbendException(e);
        }

        ARC_RQST_RECTMsg arcRqstRec = new ARC_RQST_RECTMsg();
        arcRqstRec.glblCmpyCd.setValue(arcRqst.glblCmpyCd.getValue());
        arcRqstRec.arcRqstRecPk.setValue(NFCPurgeNumberUtil.getArcRqstRecPk());
        arcRqstRec.arcRqstPk.setValue(arcRqst.arcRqstPk.getValue());
        arcRqstRec.condSqlTxt.setValue(condSqlTxt);

        return arcRqstRec;
    }
}
