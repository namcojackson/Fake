/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.AR_ADJTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_TRX_BALTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * 
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 1602
 * 01/27/2010   Canon           H.Tokunaga      Update          ACC case add. it is DED case copy. And, The COA_PROD_CD item is added.
 * 04/15/2011   Canon           T.Tanaka        Update          DefID 1986
 * 04/13/2015   Canon           K.Kimura        Update          For CSA
 * 02/03/2016   Canon           S.Fujita        Update          For CSA
 * 03/11/2016   CSAI            K.Uramori       Update          QC#5391
 * 2018/08/28   Fujitsu         H.Ikeda         Update          QC#27856
 * 
 *</pre>
 */
public class NFCCashApplyCheckExist extends S21ApiCommonBase {

    /** Table Column Name : AR_RCPT.GLBL_CMPY_CD. */
    private static final String AR_RCPT_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Column Name : AR_RCPT.RCPT_NUM. */
    private static final String AR_RCPT_RCPT_NUM = "rcptNum";

    /** Table Column Name : AR_TRX_BAL.GLBL_CMPY_CD. */
    private static final String ATB_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Column Name : AR_TRX_BAL.AR_TRX_BAL_PK. */
    private static final String ATB_AR_TRX_BAL_PK = "arTrxBalPk";

    /** Table Column Name : AR_ADJ.GLBL_CMPY_CD. */
    private static final String AR_ADJ_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Column Name : AR_ADJ.AR_ADJ_NUM. */
    private static final String AR_ADJ_AR_ADJ_NUM = "arAdjNum";

    /**
     */
    public NFCCashApplyCheckExist() {
        super();
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        checkCashApplyExist(msgMap, onBatchType);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        debugLog("execute : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     */
    protected void execute(final List<NFZC301002PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NFZC301002PMsg msg : params) {
            execute(msg, onBatchType);
            if (!NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(msg.getReturnCode())) {
                break;
            }
        }
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param onBatchType ONBATCH_TYPE
     */
    public void checkCashApplyExist(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkCashApplyExist : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (!NFCConst.CST_XX_PROC_CASE_TP_CD_GRP_INV.equals(param.xxProcCaseTpCd.getValue())) {

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(param.getReturnCode()) && NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(param.xxHdrNumGetFlg.getValue())) {

                if (!checkRcptExist(msgMap)) {
                    debugLog("checkCashApplyExist : end");
                    return;
                }
                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(param.getReturnCode()) && !NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(param.xxProcCaseTpCd.getValue())) {
                    if (!checkTrxBalRcptExist(msgMap)) {
                        debugLog("checkCashApplyExist : end");
                        return;
                    }
                }
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(param.getReturnCode()) && NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.invTrxBalPk.getValue()) != 0 && !NFCConst.CST_FLAG_ON.equals(param.xxGrpFlg.getValue())) {

                if (!checkTrxBalInvExist(msgMap)) {
                    debugLog("checkCashApplyExist : end");
                    return;
                }
            }

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(param.getReturnCode()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.arAdjNum.getValue())) {

                if (!checkAdjustmentExist(msgMap)) {
                    debugLog("checkCashApplyExist : end");
                    return;
                }
            }

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(param.getReturnCode()) && NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.crTrxBalPk.getValue()) != 0) {
                if (!checkTrxBalCrmExist(msgMap)) {
                    debugLog("checkCashApplyExist : end");
                    return;
                }
            }
        }
        debugLog("checkCashApplyExist : end");
        return;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkRcptExist(S21ApiMessageMap msgMap) {

        debugLog("checkRcptExist : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_RCPTTMsg rcptT = new AR_RCPTTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(AR_RCPT_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(AR_RCPT_RCPT_NUM, param.rcptNum.getValue());
        S21SsmEZDResult result = NFZC301001Query.getInstance().checkRcptExist(queryParam, rcptT);

        if (result.getQueryResultCount() <= 0) {
            msgMap.addXxMsgId(NFZC301001.NFCM0525E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("checkRcptExist : end");
            return false;
        }

        param.xxHdrRcptAmt.setValue(NFZC301001.initNumber(rcptT.dealRcptAmt.getValue()));
        param.xxHdrApplyAmt.setValue(NFZC301001.initNumber(rcptT.dealApplyAmt.getValue()));
        param.xxHdrAdjAmt.setValue(NFZC301001.initNumber(rcptT.dealApplyAdjAmt.getValue()));
        param.xxHdrRfAmt.setValue(NFZC301001.initNumber(rcptT.dealRfAmt.getValue()));
        param.xxHdrVoidAmt.setValue(NFZC301001.initNumber(rcptT.dealVoidAmt.getValue()));
        param.xxHdrRmngBalAmt.setValue(NFZC301001.initNumber(rcptT.dealRcptRmngBalAmt.getValue()));
        param.xxHdrRcptDt.setValue(rcptT.rcptDt.getValue());
        param.xxHdrCratMethTpCd.setValue(rcptT.cratMethTpCd.getValue());
        param.exptFlg.setValue(rcptT.exptFlg.getValue());

        param.exchRate.setValue(rcptT.exchRate.getValue());
        param.funcCcyCd.setValue(rcptT.funcCcyCd.getValue());

        msgMap.setPmsg(param);
        debugLog("checkRcptExist : end");
        return true;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkTrxBalRcptExist(S21ApiMessageMap msgMap) {

        debugLog("checkTrxBalRcptExist : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_TRX_BALTMsg arTrxBalT = new AR_TRX_BALTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(ATB_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(ATB_AR_TRX_BAL_PK, param.rcptTrxBalPk.getValue());
        S21SsmEZDResult result = NFZC301001Query.getInstance().checkTrxBalRcptExist(queryParam, arTrxBalT);

        if (result.getQueryResultCount() <= 0) {
            msgMap.addXxMsgId(NFZC301001.NFCM0526E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("checkTrxBalRcptExist : end");
            return false;
        }

        param.xxHdrTrxBalPk.setValue(NFZC301001.initNumber(arTrxBalT.arTrxBalPk.getValue()));
        param.xxHdrTrxRmngGrsAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealRmngBalGrsAmt.getValue()));
        param.xxHdrTrxArTrxTpCd.setValue(arTrxBalT.arTrxTpCd.getValue());
        param.xxHdrTrxGlDt.setValue(arTrxBalT.glDt.getValue());
        param.xxHdrTrxBillToCustCd.setValue(arTrxBalT.billToCustCd.getValue());
        param.xxHdrTrxSellToCustCd.setValue(arTrxBalT.sellToCustCd.getValue());
        param.xxHdrTrxPayerCustCd.setValue(arTrxBalT.payerCustCd.getValue());

        msgMap.setPmsg(param);
        debugLog("checkTrxBalRcptExist : end");
        return true;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkTrxBalInvExist(S21ApiMessageMap msgMap) {

        debugLog("checkTrxBalInvExist : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_TRX_BALTMsg arTrxBalT = new AR_TRX_BALTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(ATB_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(ATB_AR_TRX_BAL_PK, param.invTrxBalPk.getValue());
        S21SsmEZDResult result = NFZC301001Query.getInstance().checkTrxBalInvExist(queryParam, arTrxBalT);

        if (result.getQueryResultCount() <= 0) {
            msgMap.addXxMsgId(NFZC301001.NFCM0527E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("checkTrxBalInvExist : end");
            return false;
        }

        // Add Start 2016/02/03 for CSA
        if (!param.procTpCd.getValue().equals("3")) {
            // Add Start 2015/04/13 for CSA
            // Check Balance
            if (!isApplyAndBalance(param.dealApplyAmt.getValue(), arTrxBalT.dealRmngBalGrsAmt.getValue())) {
                msgMap.addXxMsgId(NFZC301001.NFCM0542E);
                param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
                debugLog("checkTrxBalInvExist : end");
                return false;
            }
            // Add End 2015/04/13 for CSA
        }
        // Add End 2016/02/03 for CSA

        param.xxInvTrxApplyGrsAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealApplyGrsAmt.getValue()));
        param.xxInvTrxCashDiscAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealApplyCashDiscAmt.getValue()));
        param.xxInvTrxCrAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealApplyCrAmt.getValue()));
        param.xxInvTrxAdjAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealApplyAdjAmt.getValue()));
        param.xxInvTrxAdjRsvdAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealApplyAdjRsvdAmt.getValue()));
        param.xxInvTrxRmngGrsAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealRmngBalGrsAmt.getValue()));
        param.xxInvTrxArTrxTpCd.setValue(arTrxBalT.arTrxTpCd.getValue());
        param.xxInvTrxBillToCustCd.setValue(arTrxBalT.billToCustCd.getValue());
        param.xxInvTrxSellToCustCd.setValue(arTrxBalT.sellToCustCd.getValue());
        param.xxInvTrxPayerCustCd.setValue(arTrxBalT.payerCustCd.getValue());
        if (NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY.equals(arTrxBalT.arCashApplyStsCd.getValue())) {

            param.xxInvTrxNetSlsAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealNetSlsAmt.getValue()));
        } else {

            param.xxInvTrxNetSlsAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealRmngBalGrsAmt.getValue()));
        }
        param.xxCashApplyStsCd.setValue(arTrxBalT.arCashApplyStsCd.getValue());
        param.xxInvTrxOrigGrsAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealOrigGrsAmt.getValue()));
        param.xxInvTrxAttAdjNum.setValue(arTrxBalT.attAdjNum.getValue());

        msgMap.setPmsg(param);
        debugLog("checkTrxBalInvExist : end");
        return true;
    }

    // Add Start 2015/04/13 for CSA
    /**
     * @param dealApplyAmt BigDecimal
     * @param dealRmngGrsAmt BigDecimal
     * @return boolean
     */
    private static boolean isApplyAndBalance(BigDecimal dealApplyAmt, BigDecimal dealRmngGrsAmt) {

        //---- start mod 2016/03/11 remove '=' so that apply amount $0 for credit memo adjustment can go through
        //if ((BigDecimal.ZERO.compareTo(dealApplyAmt) <= 0 && BigDecimal.ZERO.compareTo(dealRmngGrsAmt) > 0) || (BigDecimal.ZERO.compareTo(dealApplyAmt) > 0 && BigDecimal.ZERO.compareTo(dealRmngGrsAmt) <= 0)) {
        //    return false;
        //}
        // Mod Start 2018/08/28 QC#27856
        //if ((BigDecimal.ZERO.compareTo(dealApplyAmt) < 0 && BigDecimal.ZERO.compareTo(dealRmngGrsAmt) > 0) || (BigDecimal.ZERO.compareTo(dealApplyAmt) > 0 && BigDecimal.ZERO.compareTo(dealRmngGrsAmt) <= 0)) {
        if (BigDecimal.ZERO.compareTo(dealApplyAmt) < 0 && BigDecimal.ZERO.compareTo(dealRmngGrsAmt) > 0) {
        // Mod End   2018/08/28 QC#27856
            return false;
        }
        //---- end 2016/03/11

        BigDecimal amt = dealRmngGrsAmt.abs().subtract(dealApplyAmt.abs());
        if (BigDecimal.ZERO.compareTo(amt) > 0) {
            return false;
        }
        return true;
    }
    // Add End 2015/04/13 for CSA

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkAdjustmentExist(S21ApiMessageMap msgMap) {

        debugLog("checkAdjustmentExist : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_ADJTMsg arAdjT = new AR_ADJTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(AR_ADJ_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(AR_ADJ_AR_ADJ_NUM, param.arAdjNum.getValue());
        S21SsmEZDResult result = NFZC301001Query.getInstance().checkAdjExist(queryParam, arAdjT);

        if (result.getQueryResultCount() <= 0) {
            msgMap.addXxMsgId(NFZC301001.NFCM0528E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("checkAdjustmentExist : end");
            return false;
        }

        param.xxAdjStsCd.setValue(arAdjT.arAdjStsCd.getValue());

        msgMap.setPmsg(param);
        debugLog("checkAdjustmentExist : end");
        return true;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkTrxBalCrmExist(S21ApiMessageMap msgMap) {

        debugLog("checkTrxBalCrmExist : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_TRX_BALTMsg arTrxBalT = new AR_TRX_BALTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(ATB_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(ATB_AR_TRX_BAL_PK, param.crTrxBalPk.getValue());
        S21SsmEZDResult result = NFZC301001Query.getInstance().checkTrxBalCrmExist(queryParam, arTrxBalT);

        if (result.getQueryResultCount() <= 0) {
            msgMap.addXxMsgId(NFZC301001.NFCM0529E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("checkTrxBalCrmExist : end");
            return false;
        }

        param.xxCrTrxOfsRmngAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealRmngBalGrsAmt.getValue()));

        if (NFCConst.CST_AR_TRX_TP_CD_DEDUCTION.equals(param.xxInvTrxArTrxTpCd.getValue())) {

            param.xxInvTrxSellToCustCd.setValue(arTrxBalT.sellToCustCd.getValue());
        }
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(param.arAdjTrxTpCd.getValue())) {
            if (NFCConst.CST_AR_TRX_TP_CD_ACCOUNT.equals(param.arTrxTpCd.getValue())) {
                param.xxInvTrxSellToCustCd.setValue(arTrxBalT.sellToCustCd.getValue());
            }
        } else {
            if (NFCConst.CST_AR_ADJ_TRX_TP_CD_ACCOUNT.equals(param.arAdjTrxTpCd.getValue())) {
                param.xxInvTrxSellToCustCd.setValue(arTrxBalT.sellToCustCd.getValue());
            }
        }
        msgMap.setPmsg(param);
        debugLog("checkTrxBalCrmExist : end");
        return true;
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
