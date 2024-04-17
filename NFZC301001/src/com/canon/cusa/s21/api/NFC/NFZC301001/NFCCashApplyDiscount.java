/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.AR_CASH_DISC_SCHDTMsg;
import business.db.AR_CCY_CTRLTMsg;
import business.db.AR_RCPT_DTLTMsg;
import business.db.AR_TOL_LVLTMsg;
import business.db.AR_TRX_BALTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * 
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/25/2009   Canon           Y.Kondo         Update          DefID 2086
 * 04/19/2010   Canon           H.Tokunaga      Update          DefID 5790
 * 04/22/2010   Canon           H.Tokunaga      Update          DefID 5884
 * 04/27/2010   Canon           H.Tokunaga      Delete          DefID 5884
 *</pre>
 */
public class NFCCashApplyDiscount extends S21ApiCommonBase {

    /** Table Column Name : AR_RCPT_DTL.GLBL_CMPY_CD. */
    private static final String AR_RCPT_DTL_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Column Name : AR_TRX_BAL.GLBL_CMPY_CD. */
    private static final String ATB_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Column Name : AR_RCPT_DTL.RCPT_NUM. */
    private static final String AR_RCPT_DTL_RCPT_NUM = "rcptNum";

    /** Table Column Name : AR_RCPT_DTL.RCPT_DTL_NUM. */
    private static final String AR_RCPT_DTL_RCPT_DTL_NUM = "rcptDtlNum";

    /** Table Column Name : AR_TRX_BAL.AR_TRX_NUM. */
    private static final String ATB_AR_TRX_NUM = "arTrxNum";

    /** Table Column Name : AR_TRX_BAL.CCY_CD. */
    private static final String ATB_CCY_CD = "ccyCd";

    /** Table Column Name : AR_TRX_BAL.CCY_CD. */
    private static final String ATB_AR_TRX_BAL_PK = "arTrxBalPk";

    /** Table Column Name : AR_TRX_BAL.RANGE_DATE. */
    private static final String ATB_RANGE_DATE = "rngDt";

    /** Table Column Name : AR_CCY_CTRL.GLBL_CMPY_CD. */
    private static final String AR_CCY_CTRL_GLBL_CMPY_CD = "glblCmpyCd";

    /** Table Column Name : AR_CCY_CTRL.CCY_CD. */
    private static final String AR_CCY_CTRL_CCY_CD = "ccyCd";

    /** BAT_DT. */
    private static final String BAT_DT = "batDt";

    /** Map Key : CCY_CD. */
    private static final String CCY_CD = "CCY_CD";

    /** Map Key : ROUND_METH_CD. */
    private static final String ROUND_METH_CD = "ROUND_METH_CD";

    /** Map Key : AFT_DECL_PNT_DIGIT_NUM. */
    private static final String AFT_DECL_PNT_DIGIT_NUM = "AFT_DECL_PNT_DIGIT_NUM";

    /** Map Key : DISC_GRACE_PER_FROM_DT. */
    private static final String DISC_GRACE_PER_FROM_DT = "DISC_GRACE_PER_FROM_DT";

    /** Map Key : DISC_GRACE_PER_THRU_DT. */
    private static final String DISC_GRACE_PER_THRU_DT = "DISC_GRACE_PER_THRU_DT";

    /** Map Key : INV_CCY_CD. */
    private static final String INV_CCY_CD = "INV_CCY_CD";

    /** Map Key : NON_OPS_MISC_PMT_AMT. */
    private static final String NON_OPS_MISC_PMT_AMT = "NON_OPS_MISC_PMT_AMT";

    /** Map Key : NON_OPS_MISC_PMT_AMT_PCT. */
    private static final String NON_OPS_MISC_PMT_AMT_PCT = "NON_OPS_MISC_PMT_AMT_PCT";

    /** Map Key : MISC_INC_PMT_AMT. */
    private static final String MISC_INC_PMT_AMT = "MISC_INC_PMT_AMT";

    /** Map Key : MISC_INC_PMT_AMT_PCT. */
    private static final String MISC_INC_PMT_AMT_PCT = "MISC_INC_PMT_AMT_PCT";

    /** Map Key : NON_OPS_MISC_INV_AMT. */
    private static final String NON_OPS_MISC_INV_AMT = "NON_OPS_MISC_INV_AMT";

    /** Map Key : NON_OPS_MISC_INV_AMT_PCT. */
    private static final String NON_OPS_MISC_INV_AMT_PCT = "NON_OPS_MISC_INV_AMT_PCT";

    /** Map Key : MISC_INC_INV_AMT. */
    private static final String MISC_INC_INV_AMT = "MISC_INC_INV_AMT";

    /** Map Key : MISC_INC_INV_AMT_PCT. */
    private static final String MISC_INC_INV_AMT_PCT = "MISC_INC_INV_AMT_PCT";

    /**
     */
    public NFCCashApplyDiscount() {
        super();
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType, Map<String, Object> tmpMaster) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        getCashDiscount(msgMap, onBatchType, tmpMaster);
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

        Map<String, Object> tmpMaster = new HashMap<String, Object>();
        tmpMaster.put(CCY_CD, NFCConst.CST_DB_INIT_VAL_STR);
        tmpMaster.put(AFT_DECL_PNT_DIGIT_NUM, NFCConst.CST_DB_INIT_VAL_NUM);
        tmpMaster.put(DISC_GRACE_PER_FROM_DT, NFCConst.CST_DB_INIT_VAL_STR);
        tmpMaster.put(DISC_GRACE_PER_THRU_DT, NFCConst.CST_DB_INIT_VAL_STR);
        tmpMaster.put(INV_CCY_CD, NFCConst.CST_DB_INIT_VAL_STR);
        tmpMaster.put(NON_OPS_MISC_PMT_AMT, NFCConst.CST_DB_INIT_VAL_NUM);
        tmpMaster.put(NON_OPS_MISC_PMT_AMT_PCT, NFCConst.CST_DB_INIT_VAL_NUM);
        tmpMaster.put(MISC_INC_PMT_AMT, NFCConst.CST_DB_INIT_VAL_NUM);
        tmpMaster.put(MISC_INC_PMT_AMT_PCT, NFCConst.CST_DB_INIT_VAL_NUM);
        tmpMaster.put(NON_OPS_MISC_INV_AMT, NFCConst.CST_DB_INIT_VAL_NUM);
        tmpMaster.put(NON_OPS_MISC_INV_AMT_PCT, NFCConst.CST_DB_INIT_VAL_NUM);
        tmpMaster.put(MISC_INC_INV_AMT, NFCConst.CST_DB_INIT_VAL_NUM);
        tmpMaster.put(MISC_INC_INV_AMT_PCT, NFCConst.CST_DB_INIT_VAL_NUM);

        for (NFZC301002PMsg msg : params) {
            execute(msg, onBatchType, tmpMaster);
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
     */
    protected void getCashDiscount(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, Map<String, Object> tmpMaster) {

        debugLog("getCashDiscount : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_XX_PROC_CASE_TP_CD_WRK.equals(param.xxProcCaseTpCd.getValue())) {
            param.xxCashDiscGetFlg.setValue(NFCConst.CST_FLAG_ON);

            if (NFCConst.CST_GRP_INV_FLG_GRP_ON.equals(param.grpInvFlg.getValue())) {

                setRcptDtlInv(msgMap);
            } else {

                if (getRcptDtl(msgMap)) {
                    debugLog("getCashDiscount : end");
                    return;
                }
            }
            if (getTrxBalRcptDtl(msgMap)) {
                debugLog("getCashDiscount : end");
                return;
            }
            if (getCcyCtrl(msgMap, tmpMaster)) {
                debugLog("getCashDiscount : end");
                return;
            }
            getPerDt(msgMap, tmpMaster);

            getTolLvl(msgMap, tmpMaster);

            getRcptDtCashDiscRange(msgMap);

            getRcptDtExtnCashDiscRange(msgMap);
        } else {
            param.xxCashDiscGetFlg.setValue(NFCConst.CST_FLAG_OFF);
        }
        debugLog("getCashDiscount : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void setRcptDtlInv(S21ApiMessageMap msgMap) {

        debugLog("setRcptDtlInv : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
//      h.tokunaga 2010/4/18
        debugLog("setRcptDtlInv xxDtlInvNum : " + param.invNum.getValue());
        param.xxDtlInvNum.setValue(param.invNum.getValue());
        param.xxDtlAmt.setValue(param.xxInvTrxRmngGrsAmt.getValue());
        param.xxDtlAutoCratFlg.setValue(NFCConst.CST_AUTO_CRAT_FLG_AUTO_OFF);
        param.xxDtlManAmt.setValue(param.xxInvTrxRmngGrsAmt.getValue());
        param.dealApplyAmt.setValue(param.xxInvTrxRmngGrsAmt.getValue());

        debugLog("setRcptDtlInv : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean getRcptDtl(S21ApiMessageMap msgMap) {

        debugLog("getRcptDtl : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        // h.tokunaga 2010/4/18
        debugLog("getRcptDtl rcptNum : " + param.rcptNum.getValue());
        debugLog("getRcptDtl rcptDtlNum : " + param.rcptDtlNum.getValue());

        AR_RCPT_DTLTMsg arRcptDtlT = new AR_RCPT_DTLTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(AR_RCPT_DTL_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(AR_RCPT_DTL_RCPT_NUM, param.rcptNum.getValue());
        queryParam.put(AR_RCPT_DTL_RCPT_DTL_NUM, param.rcptDtlNum.getValue());
        S21SsmEZDResult result = NFZC301001Query.getInstance().getRcptDtl(queryParam, arRcptDtlT);

        if (result.getQueryResultCount() <= 0) {
            msgMap.addXxMsgId(NFZC301001.NFCM0536E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("getRcptDtl : end");
            return true;
        }
        //h.tokunaga 2010/4/18
        debugLog("getRcptDtl xxDtlInvNum : " + param.invNum.getValue());

        param.xxDtlInvNum.setValue(arRcptDtlT.invNum.getValue());
        param.xxDtlAmt.setValue(NFZC301001.initNumber(arRcptDtlT.dealRcptDtlAmt.getValue()));
        param.xxDtlAutoCratFlg.setValue(arRcptDtlT.autoCratFlg.getValue());
        if (NFCConst.CST_AUTO_CRAT_FLG_AUTO_OFF.equals(arRcptDtlT.autoCratFlg.getValue())) {
            param.xxDtlManAmt.setValue(NFZC301001.initNumber(arRcptDtlT.dealRcptDtlAmt.getValue()));
        } else {
            param.xxDtlManAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        }

        debugLog("getRcptDtl : end");
        return false;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean getTrxBalRcptDtl(S21ApiMessageMap msgMap) {

        debugLog("getTrxBalRcptDtl : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_TRX_BALTMsg arTrxBalT = new AR_TRX_BALTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(ATB_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(ATB_AR_TRX_NUM, param.xxDtlInvNum.getValue());
        S21SsmEZDResult result = NFZC301001Query.getInstance().getTrxBalRcptDtl(queryParam, arTrxBalT);

        if (result.getQueryResultCount() <= 0) {
            msgMap.addXxMsgId(NFZC301001.NFCM0537E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("getTrxBalRcptDtl : end");
            return true;
        }

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
        param.xxInvTrxNetSlsAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealNetSlsAmt.getValue()));
        param.xxCashApplyStsCd.setValue(arTrxBalT.arCashApplyStsCd.getValue());
        param.xxInvTrxOrigGrsAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealOrigGrsAmt.getValue()));
        param.xxDtlTrxBalPk.setValue(NFZC301001.initNumber(arTrxBalT.arTrxBalPk.getValue()));
        param.xxDtlTrxRmngGrsAmt.setValue(NFZC301001.initNumber(arTrxBalT.dealRmngBalGrsAmt.getValue()));
        param.xxDtlTrxInvDueDt.setValue(arTrxBalT.invDueDt.getValue());
        param.xxDtlTrxCcyCd.setValue(arTrxBalT.dealCcyCd.getValue());

        debugLog("getTrxBalRcptDtl : end");
        return false;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean getCcyCtrl(S21ApiMessageMap msgMap, Map<String, Object> tmpMaster) {

        debugLog("getCcyCtrl : start");

        String tempCcyCd = (String) tmpMaster.get(CCY_CD);
        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        if (tempCcyCd.equals(param.xxDtlTrxCcyCd.getValue())) {

            String tempRoundMethCd = (String) tmpMaster.get(ROUND_METH_CD);
            BigDecimal tempAftDeclPntDigitNum = (BigDecimal) tmpMaster.get(AFT_DECL_PNT_DIGIT_NUM);

            param.roundMethCd.setValue(tempRoundMethCd);
            param.aftDeclPntDigitNum.setValue(tempAftDeclPntDigitNum);
        } else {

            AR_CCY_CTRLTMsg arCcyCtrlT = new AR_CCY_CTRLTMsg();
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(AR_CCY_CTRL_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            queryParam.put(AR_CCY_CTRL_CCY_CD, param.xxDtlTrxCcyCd.getValue());
            S21SsmEZDResult result = NFZC301001Query.getInstance().getCcyCtrl(queryParam, arCcyCtrlT);

            if (result.getQueryResultCount() <= 0) {
                msgMap.addXxMsgId(NFZC301001.NFCM0611E);
                param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
                debugLog("getCcyCtrl : end");
                return true;
            } else {

                param.roundMethCd.setValue(arCcyCtrlT.roundMethCd.getValue());
                param.aftDeclPntDigitNum.setValue(NFZC301001.initNumber(arCcyCtrlT.aftDeclPntDigitNum.getValue()));
            }

            tmpMaster.put(CCY_CD, param.xxDtlTrxCcyCd.getValue());
            tmpMaster.put(ROUND_METH_CD, param.roundMethCd.getValue());
            tmpMaster.put(AFT_DECL_PNT_DIGIT_NUM, param.aftDeclPntDigitNum.getValue());
        }
        debugLog("getCcyCtrl : end");
        return false;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void getPerDt(S21ApiMessageMap msgMap, Map<String, Object> tmpMaster) {

        debugLog("getPerDt : start");

        String tempDiscGracePerFromDt = (String) tmpMaster.get(DISC_GRACE_PER_FROM_DT);
        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(tempDiscGracePerFromDt)) {

            String arDiscGracePerFromDt = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_AR_DISC_GRACE_PER_FROM_DT, param.glblCmpyCd.getValue());
            if (S21StringUtil.isEmpty(arDiscGracePerFromDt)) {

                param.xxDiscGracePerFromDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            } else {

                param.xxDiscGracePerFromDt.setValue(arDiscGracePerFromDt);
            }

            String arDiscGracePerToDt = ZYPCodeDataUtil.getVarCharConstValue(NFCConst.CST_VAR_CHAR_CONST_NAME_AR_DISC_GRACE_PER_TO_DT, param.glblCmpyCd.getValue());
            if (S21StringUtil.isEmpty(arDiscGracePerToDt)) {

                param.xxDiscGracePerThruDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            } else {

                param.xxDiscGracePerThruDt.setValue(arDiscGracePerToDt);
            }

            tmpMaster.put(DISC_GRACE_PER_FROM_DT, param.xxDiscGracePerFromDt.getValue());
            tmpMaster.put(DISC_GRACE_PER_THRU_DT, param.xxDiscGracePerThruDt.getValue());
        } else {

            String tempDiscGracePerThruDt = (String) tmpMaster.get(DISC_GRACE_PER_THRU_DT);

            param.xxDiscGracePerFromDt.setValue(tempDiscGracePerFromDt);
            param.xxDiscGracePerThruDt.setValue(tempDiscGracePerThruDt);
        }
        debugLog("getPerDt : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void getTolLvl(S21ApiMessageMap msgMap, Map<String, Object> tmpMaster) {

        debugLog("getTolLvl : start");

        String tempInvCcyCd = (String) tmpMaster.get(INV_CCY_CD);
        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (tempInvCcyCd.equals(param.xxDtlTrxCcyCd.getValue())) {

            BigDecimal tempNonOpsMiscPmtAmt = (BigDecimal) tmpMaster.get(NON_OPS_MISC_PMT_AMT);
            BigDecimal tempNonOpsMiscPmtAmtPct = (BigDecimal) tmpMaster.get(NON_OPS_MISC_PMT_AMT_PCT);
            BigDecimal tempMiscIncPmtAmt = (BigDecimal) tmpMaster.get(MISC_INC_PMT_AMT);
            BigDecimal tempMiscIncPmtAmtPct = (BigDecimal) tmpMaster.get(MISC_INC_PMT_AMT_PCT);
            BigDecimal tempNonOpsMiscInvAmt = (BigDecimal) tmpMaster.get(NON_OPS_MISC_INV_AMT);
            BigDecimal tempNonOpsMiscInvAmtPct = (BigDecimal) tmpMaster.get(NON_OPS_MISC_INV_AMT_PCT);
            BigDecimal tempMiscInvAmt = (BigDecimal) tmpMaster.get(MISC_INC_INV_AMT);
            BigDecimal tempMiscInvAmtPct = (BigDecimal) tmpMaster.get(MISC_INC_INV_AMT_PCT);

            param.nonOpsMiscPmtAmt.setValue(tempNonOpsMiscPmtAmt);
            param.nonOpsMiscPmtAmtPct.setValue(tempNonOpsMiscPmtAmtPct);
            param.miscIncPmtAmt.setValue(tempMiscIncPmtAmt);
            param.miscIncPmtAmtPct.setValue(tempMiscIncPmtAmtPct);
            param.nonOpsMiscInvAmt.setValue(tempNonOpsMiscInvAmt);
            param.nonOpsMiscInvAmtPct.setValue(tempNonOpsMiscInvAmtPct);
            param.miscIncInvAmt.setValue(tempMiscInvAmt);
            param.miscIncInvAmtPct.setValue(tempMiscInvAmtPct);
        } else {

            AR_TOL_LVLTMsg arTolLvlT = new AR_TOL_LVLTMsg();
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(ATB_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            queryParam.put(BAT_DT, param.batDt.getValue());
            queryParam.put(ATB_CCY_CD, param.xxDtlTrxCcyCd.getValue());
            S21SsmEZDResult result = NFZC301001Query.getInstance().getTolLvl(queryParam, arTolLvlT);

            if (result.getQueryResultCount() <= 0) {
                param.nonOpsMiscPmtAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                param.nonOpsMiscPmtAmtPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                param.miscIncPmtAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                param.miscIncPmtAmtPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                param.nonOpsMiscInvAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                param.nonOpsMiscInvAmtPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                param.miscIncInvAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                param.miscIncInvAmtPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            } else {

                param.nonOpsMiscPmtAmt.setValue(arTolLvlT.nonOpsMiscPmtAmt.getValue());
                param.nonOpsMiscPmtAmtPct.setValue(arTolLvlT.nonOpsMiscPmtAmtPct.getValue());
                param.miscIncPmtAmt.setValue(arTolLvlT.miscIncPmtAmt.getValue());
                param.miscIncPmtAmtPct.setValue(arTolLvlT.miscIncPmtAmtPct.getValue());
                param.nonOpsMiscInvAmt.setValue(arTolLvlT.nonOpsMiscInvAmt.getValue());
                param.nonOpsMiscInvAmtPct.setValue(arTolLvlT.nonOpsMiscInvAmtPct.getValue());
                param.miscIncInvAmt.setValue(arTolLvlT.miscIncInvAmt.getValue());
                param.miscIncInvAmtPct.setValue(arTolLvlT.miscIncInvAmtPct.getValue());
            }

            tmpMaster.put(INV_CCY_CD, param.xxDtlTrxCcyCd.getValue());
            tmpMaster.put(NON_OPS_MISC_PMT_AMT, param.nonOpsMiscPmtAmt.getValue());
            tmpMaster.put(NON_OPS_MISC_PMT_AMT_PCT, param.nonOpsMiscPmtAmtPct.getValue());
            tmpMaster.put(MISC_INC_PMT_AMT, param.miscIncPmtAmt.getValue());
            tmpMaster.put(MISC_INC_PMT_AMT_PCT, param.miscIncPmtAmtPct.getValue());
            tmpMaster.put(NON_OPS_MISC_INV_AMT, param.nonOpsMiscInvAmt.getValue());
            tmpMaster.put(NON_OPS_MISC_INV_AMT_PCT, param.nonOpsMiscInvAmtPct.getValue());
            tmpMaster.put(MISC_INC_INV_AMT, param.miscIncInvAmt.getValue());
            tmpMaster.put(MISC_INC_INV_AMT_PCT, param.miscIncInvAmtPct.getValue());
        }
        debugLog("getTolLvl : end");
        return;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void getRcptDtCashDiscRange(S21ApiMessageMap msgMap) {

        debugLog("getRcptDtCashDiscRange : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_CASH_DISC_SCHDTMsg arCashDiscSchdT = new AR_CASH_DISC_SCHDTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(ATB_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(ATB_AR_TRX_BAL_PK, param.xxDtlTrxBalPk.getValue());
        queryParam.put(ATB_RANGE_DATE, param.xxHdrRcptDt.getValue());
        S21SsmEZDResult result = NFZC301001Query.getInstance().getRcptDtCashDiscRange(queryParam, arCashDiscSchdT);

        if (result.getQueryResultCount() <= 0 || !NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY.equals(param.xxCashApplyStsCd.getValue())) {
            param.xxDtlDiscPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            param.xxDtlDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            param.xxNetDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            param.xxInvDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        } else {

            param.xxDtlDiscPct.setValue(arCashDiscSchdT.cashDiscPct.getValue());
            param.xxDtlDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            param.xxNetDiscAmt.setValue(NFZC301001.account(param.xxInvTrxNetSlsAmt.getValue(), param.xxDtlDiscPct.getValue(), param.aftDeclPntDigitNum.getValue(), param.roundMethCd.getValue()));
            param.xxInvDiscAmt.setValue(NFZC301001.account(param.xxInvTrxOrigGrsAmt.getValue(), param.xxDtlDiscPct.getValue(), param.aftDeclPntDigitNum.getValue(), param.roundMethCd.getValue()));
        }

        debugLog("getRcptDtCashDiscRange : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void getRcptDtExtnCashDiscRange(S21ApiMessageMap msgMap) {

        debugLog("getRcptDtExtnCashDiscRange : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        AR_CASH_DISC_SCHDTMsg arCashDiscSchdT = new AR_CASH_DISC_SCHDTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(ATB_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        queryParam.put(ATB_AR_TRX_BAL_PK, param.xxDtlTrxBalPk.getValue());
        queryParam.put(ATB_RANGE_DATE, param.xxHdrRcptDt.getValue());
        S21SsmEZDResult result = NFZC301001Query.getInstance().getRcptDtExtnCashDiscRange(queryParam, arCashDiscSchdT);

        if (result.getQueryResultCount() <= 0 || !NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY.equals(param.xxCashApplyStsCd.getValue())) {
            param.xxDtlExtnDiscPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            param.xxDtlExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            param.xxNetExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            param.xxInvExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        } else {

            param.xxDtlExtnDiscPct.setValue(arCashDiscSchdT.extnCashDiscPct.getValue());
            param.xxDtlExtnDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
            param.xxNetExtnDiscAmt.setValue(NFZC301001.account(param.xxInvTrxNetSlsAmt.getValue(), param.xxDtlExtnDiscPct.getValue(), param.aftDeclPntDigitNum.getValue(), param.roundMethCd.getValue()));
            param.xxInvExtnDiscAmt.setValue(NFZC301001.account(param.xxInvTrxOrigGrsAmt.getValue(), param.xxDtlExtnDiscPct.getValue(), param.aftDeclPntDigitNum.getValue(), param.roundMethCd.getValue()));
        }

        debugLog("getRcptDtExtnCashDiscRange : end");
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
