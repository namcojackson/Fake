/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.util.Map;

import business.db.AR_ADJTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_APPLY_INTFC_WRKTMsgArray;
import business.db.AR_CASH_DISC_SCHDTMsg;
import business.db.AR_CCY_CTRLTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_RCPT_RCV_HISTTMsgArray;
import business.db.AR_RCPT_UN_APPLYTMsgArray;
import business.db.AR_TOL_LVLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_TRX_BALTMsgArray;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/20/2009   Canon           Y.Kondo         Update          DefID 1972
 * 2016/09/28   Hitachi         K.Kojima        Update          QC#11021
 * 2024/02/20   Hitachi         TZ.Win          Update          QC#63450
 *</pre>
 */
public final class NFZC301001Query extends S21SsmEZDQuerySupport {

    /** */
    private static final NFZC301001Query MYINSTANCE = new NFZC301001Query();

    /**
     */
    private NFZC301001Query() {
    }

    /**
     * @return MYINSTANCE
     */
    public static NFZC301001Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_APPLY_INTFC_WRKTMsgArray
     * @return AR_APPLY_INTFC_WRKTMsgArray
     */
    public S21SsmEZDResult getApplyIntfcWrk(Map<String, Object> param, AR_APPLY_INTFC_WRKTMsgArray tMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getApplyIntfcWrk", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_TRX_BALTMsgArray
     * @return AR_TRX_BALTMsgArray
     */
    public S21SsmEZDResult getGroupInvoice(Map<String, Object> param, AR_TRX_BALTMsgArray tMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getGroupInvoice", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_RCPTTMsg
     * @return AR_RCPTTMsg
     */
    public S21SsmEZDResult checkRcptExist(Map<String, Object> param, AR_RCPTTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("checkRcptExist", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_TRX_BALTMsg
     * @return AR_RCPTTMsg
     */
    public S21SsmEZDResult checkTrxBalRcptExist(Map<String, Object> param, AR_TRX_BALTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("checkTrxBalRcptExist", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsg
     */
    public S21SsmEZDResult checkTrxBalInvExist(Map<String, Object> param, AR_TRX_BALTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("checkTrxBalInvExist", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_ADJTMsg
     * @return AR_ADJTMsg
     */
    public S21SsmEZDResult checkAdjExist(Map<String, Object> param, AR_ADJTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("checkAdjExist", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsg
     */
    public S21SsmEZDResult checkTrxBalCrmExist(Map<String, Object> param, AR_TRX_BALTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("checkTrxBalCrmExist", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_RCPT_DTLTMsg
     * @return AR_RCPT_DTLTMsg
     */
    public S21SsmEZDResult getRcptDtl(Map<String, Object> param, AR_RCPT_DTLTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getRcptDtl", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsg
     */
    public S21SsmEZDResult getTrxBalRcptDtl(Map<String, Object> param, AR_TRX_BALTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getTrxBalRcptDtl", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsg
     */
    public S21SsmEZDResult getEdiAdjTrxBal(Map<String, Object> param, AR_TRX_BALTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getEdiAdjTrxBal", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_CCY_CTRLTMsg
     * @return AR_CCY_CTRLTMsg
     */
    public S21SsmEZDResult getCcyCtrl(Map<String, Object> param, AR_CCY_CTRLTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getCcyCtrl", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_TOL_LVLTMsg
     * @return AR_TOL_LVLTMsg
     */
    public S21SsmEZDResult getTolLvl(Map<String, Object> param, AR_TOL_LVLTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getTolLvl", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_CASH_DISC_SCHDTMsg
     * @return AR_CASH_DISC_SCHDTMsg
     */
    public S21SsmEZDResult getRcptDtCashDiscRange(Map<String, Object> param, AR_CASH_DISC_SCHDTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getRcptDtCashDiscRange", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_CASH_DISC_SCHDTMsg
     * @return AR_CASH_DISC_SCHDTMsg
     */
    public S21SsmEZDResult getRcptDtExtnCashDiscRange(Map<String, Object> param, AR_CASH_DISC_SCHDTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getRcptDtExtnCashDiscRange", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_RCPT_UN_APPLYTMsgArray
     * @return AR_RCPT_UN_APPLYTMsgArray
     */
    public S21SsmEZDResult getArRcptUnApply(Map<String, Object> param, AR_RCPT_UN_APPLYTMsgArray tMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getArRcptUnApply", param, tMsg);
    }

    /**
     * @param param Map<String, Object>
     * @param tMsg AR_RCPT_RCV_HISTTMsgArray
     * @return AR_RCPT_RCV_HISTTMsgArray
     */
    public S21SsmEZDResult getRcptRcvHist(Map<String, Object> param, AR_RCPT_RCV_HISTTMsgArray tMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getRcptRcvHist", param, tMsg);
    }

    /**
     * Get DEAL_SQ_DTL_NUM Max
     * @param param Map<String, Object>
     * @param tMsg AR_APPLY_INTFC_WRKTMsg
     * @return AR_APPLY_INTFC_WRKTMsg
     */
    public S21SsmEZDResult getDealSqNumMax(Map<String, Object> param, AR_APPLY_INTFC_WRKTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getDealSqNumMax", param, tMsg);
    }

    /**
     * Get APPLY_GRP_SUB_PK Max
     * @param param Map<String, Object>
     * @param tMsg AR_APPLY_INTFC_WRKTMsg
     * @return AR_APPLY_INTFC_WRKTMsg
     */
    public S21SsmEZDResult getApplyGrpSubPkMax(Map<String, Object> param, AR_APPLY_INTFC_WRKTMsg tMsg) {
        return getSsmEZDClient().queryEZDMsg("getApplyGrpSubPkMax", param, tMsg);
    }

    // START 2016/09/28 K.Kojima [QC#11021,ADD]
    /**
     * Get BILL_TO_CUST_CD
     * @param param Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBillToCustCd(Map<String, Object> param) {
        return getSsmEZDClient().queryObject("getBillToCustCd", param);
    }
    // END 2016/09/28 K.Kojima [QC#11021,ADD]

    // START 2024/02/20 TZ.Win [QC#63450, ADD]
    /**
     * Get Maximum Sort Num
     * @param sortParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMaxSortNum(Map<String, Object> sortParam) {
        return getSsmEZDClient().queryObject("getMaxSortNum", sortParam);
    }
    // END 2024/02/20 TZ.Win [QC#63450, ADD]

}
