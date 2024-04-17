/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDDBCICarrier;
import business.db.AR_ADJTMsg;
import business.db.AR_TRX_BALTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCDbConst;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/10/2009   Canon           Y.Kondo         Update          DefID 463
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 1504
 * 12/07/2009   Canon           Y.Kondo         Create          DefID 2343
 * 01/27/2010   Canon           H.Tokunaga      Update          ACC case add. it is DED case copy. And, The COA_PROD_CD item is added.
 * 05/06/2010   Canon           H.Tokunaga      Update          DefID 6144
 * 11/20/2010   Canon           T.Tanaka        Update          DefID M125
 * 11/24/2010   Canon           I.Kondo         Update          M125
 * 03/10/2011   Canon           Y.Suga          Update          DefID 1654
 * </pre>
 */
public class NFCCashApplyUpdateArAdj extends S21ApiCommonBase {
    /** BigDecimal : NAGATE. */
    private static final BigDecimal NEGATE = BigDecimal.valueOf(-1);

    /**
     */
    public NFCCashApplyUpdateArAdj() {
        super();
    }

    /**
     * <pre>
     * </pre>
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateCashApplyArAdj(msgMap, onBatchType);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        debugLog("execute : end");
    }

    /**
     * <pre>
     * </pre>
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
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    protected void updateCashApplyArAdj(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("updateCashApplyArAdj : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_XX_PROC_TP_CD_NON.equals(param.xxAdjProcTpCd.getValue())) {
            setArAdj(msgMap);
        }
        debugLog("updateCashApplyArAdj : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void setArAdj(S21ApiMessageMap msgMap) {

        debugLog("setArAdj : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADJ_APVL_WAIT.equals(param.xxProcCaseTpCd.getValue())) {

            AR_ADJTMsg adjT = findByKeyForUpdateArAdj(msgMap);

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                adjT = editArAdjApvlWait(msgMap, adjT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArAdj(msgMap, adjT);
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_APVL.equals(param.xxProcCaseTpCd.getValue())) {

            AR_ADJTMsg adjT = findByKeyForUpdateArAdj(msgMap);

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                adjT = editArAdjApvl(msgMap, adjT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArAdj(msgMap, adjT);
            }
        } else {

            if (param.xxDtlDiffAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) != 0) {

                AR_ADJTMsg adjT = editArAdjDiff(msgMap);

                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                    if (insArAdj(msgMap, adjT)) {
                        debugLog("setArAdj : end");
                        return;
                    }
                    param.xxArDiffAdjNum.setValue(adjT.arAdjNum.getValue());
                }
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))
                    && (param.dealApplyAdjAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) != 0 || param.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) != 0)) {

                AR_ADJTMsg adjT = editArAdjRsvd(msgMap);

                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                    if (insArAdj(msgMap, adjT)) {
                        debugLog("setArAdj : end");
                        return;
                    }
                    param.xxArAdjNum.setValue(adjT.arAdjNum.getValue());
                }
            }
        }
        debugLog("setArAdj : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param adjT AR_ADJTMsg
     * @return adjT AR_ADJTMsg
     */
    private AR_ADJTMsg editArAdjApvlWait(S21ApiMessageMap msgMap, AR_ADJTMsg adjT) {

        debugLog("editArAdjApvlWait : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        adjT.adjDt.setValue(param.batDt.getValue());
        adjT.adjApvlCmntTxt.setValue(param.adjCmntTxt.getValue());
        adjT.arAdjStsCd.setValue(NFCConst.CST_ADJ_STS_CD_REJECTED);
        adjT.tocCd.setValue(param.tocCd.getValue());
        adjT.coaProdCd.setValue(param.coaProdCd.getValue());

        debugLog("editArAdjApvlWait : end");
        return adjT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param adjT AR_ADJTMsg
     * @return adjT AR_ADJTMsg
     */
    private AR_ADJTMsg editArAdjApvl(S21ApiMessageMap msgMap, AR_ADJTMsg adjT) {

        debugLog("editArAdjApvl : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        adjT.adjDt.setValue(param.batDt.getValue());
        adjT.adjApvlCmntTxt.setValue(param.adjCmntTxt.getValue());
        adjT.apvlByPsnCd.setValue(param.apvlPsnCd.getValue());
        adjT.arAdjStsCd.setValue(NFCConst.CST_ADJ_STS_CD_APPROVED);
        adjT.tocCd.setValue(param.tocCd.getValue());
        adjT.coaProdCd.setValue(param.coaProdCd.getValue());

        debugLog("editArAdjApvl : end");
        return adjT;
    }

    /**
     * <pre>
     * Edit AR_ADJ Diff Adjust Amount Insert
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return adjT AR_ADJTMsg
     */
    private AR_ADJTMsg editArAdjDiff(S21ApiMessageMap msgMap) {

        debugLog("editArAdjDiff : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealArAdjAmt = param.xxDtlDiffAmt.getValue();
        BigDecimal origArTrxBalPk = null;
        String arAdjTpCd = null;
        // The Diff judges ADJ_TP by a plus or a minus
        if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.xxDtlDiffAmt.getValue()) <= 0) {

            // ADJ_TP : B(NON_OPERATING_MISCELLANEOUS)
            arAdjTpCd = NFCConst.CST_AR_ADJ_TP_CD_NON_OPERATING_MISCELLANEOUS;
            // 05/06/2010 add H.Tokunaga Start defid 6144
            // origArTrxBalPk = param.xxDtlTrxBalPk.getValue();
            origArTrxBalPk = param.xxHdrTrxBalPk.getValue();
            dealArAdjAmt = dealArAdjAmt.multiply(NEGATE);
            // 05/06/2010 add H.Tokunaga end defid 6144
        } else {

            // ADJ_TP : C(MISCELLANEOUS_INCOME)
            arAdjTpCd = NFCConst.CST_AR_ADJ_TP_CD_MISCELLANEOUS_INCOME;
            // 05/06/2010 add H.Tokunaga Start defid 6144
            origArTrxBalPk = param.xxHdrTrxBalPk.getValue();
            // origArTrxBalPk = param.xxDtlTrxBalPk.getValue();
            dealArAdjAmt = dealArAdjAmt.multiply(NEGATE);
            // 05/06/2010 add H.Tokunaga end defid 6144
        }

        AR_ADJTMsg adjT = new AR_ADJTMsg();

        AR_TRX_BALTMsg arEdiAdjTrxBalT = new AR_TRX_BALTMsg();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(NFCDbConst.GLBL_CMPY_CD_J, param.glblCmpyCd.getValue());
        queryParam.put(NFCDbConst.AR_TRX_BAL_PK_J, origArTrxBalPk);
        S21SsmEZDResult result = NFZC301001Query.getInstance().getEdiAdjTrxBal(queryParam, arEdiAdjTrxBalT);

        if (result.getQueryResultCount() <= 0) {
            // h.tokunaga 2010/4/18
            debugLog("editArAdjDiff rcptDtlNum : " + param.rcptDtlNum.getValue());
            debugLog("editArAdjDiff xxDtlInvNum : " + param.xxDtlInvNum.getValue());
            debugLog("invNum : " + NFCCmnMethod.convertDbString(param.invNum.getValue()));
            debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            debugLog("arAdjTpCd : " + NFCCmnMethod.convertDbString(arAdjTpCd));
            msgMap.addXxMsgId(NFZC301001.NFCM0526E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("editArAdjDiff : end");
            return adjT;
        } else if (S21StringUtil.isEmpty(arEdiAdjTrxBalT.coaProdCd.getValue())) {
            // h.tokunaga 2010/4/18
            debugLog("editArAdjDiff rcptDtlNum : " + param.rcptDtlNum.getValue());
            debugLog("editArAdjDiff xxDtlInvNum : " + param.xxDtlInvNum.getValue());
            debugLog("invNum : " + NFCCmnMethod.convertDbString(param.invNum.getValue()));
            debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            debugLog("arAdjTpCd : " + NFCCmnMethod.convertDbString(arAdjTpCd));
            msgMap.addXxMsgId(NFZC301001.NFCM0526E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("editArAdjDiff : end");
            return adjT;
        } else if (S21StringUtil.isEmpty(arEdiAdjTrxBalT.tocCd.getValue())) {
            // h.tokunaga 2010/4/18
            debugLog("editArAdjDiff rcptDtlNum : " + param.rcptDtlNum.getValue());
            debugLog("editArAdjDiff xxDtlInvNum : " + param.xxDtlInvNum.getValue());
            debugLog("invNum : " + NFCCmnMethod.convertDbString(param.invNum.getValue()));
            debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            debugLog("arAdjTpCd : " + NFCCmnMethod.convertDbString(arAdjTpCd));
            msgMap.addXxMsgId(NFZC301001.NFCM0526E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("editArAdjDiff : end");
            return adjT;
        }

        adjT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        adjT.arAdjTrxTpCd.setValue(NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT);
        adjT.arAdjTpCd.setValue(arAdjTpCd);
        adjT.dealCcyCd.setValue(param.dealCcyCd.getValue());
        adjT.dealArAdjAmt.setValue(dealArAdjAmt);
        adjT.exchRate.setValue(param.exchRate.getValue());
        adjT.funcCcyCd.setValue(param.funcCcyCd.getValue());

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealArAdjAmt, param.exchRate.getValue());

        if (null == funcAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArAdjDiff : end");
            return adjT;
        }

        adjT.funcArAdjAmt.setValue(funcAmt);

        adjT.origArTrxBalPk.setValue(origArTrxBalPk);
        adjT.adjDt.setValue(param.batDt.getValue());
        adjT.glDt.setValue(param.cashAppGlDt.getValue());
        adjT.adjCmntTxt.setValue(param.adjCmntTxt.getValue());
        adjT.adjApvlCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        adjT.apvlByPsnCd.setValue(param.apvlPsnCd.getValue());
        adjT.arAdjStsCd.setValue(NFCConst.CST_ADJ_STS_CD_APPROVED);
        adjT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        adjT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        adjT.tocCd.setValue(arEdiAdjTrxBalT.tocCd.getValue());
        adjT.coaProdCd.setValue(arEdiAdjTrxBalT.coaProdCd.getValue());

        debugLog("editArAdjDiff : end");
        return adjT;
    }

    /**
     * <pre>
     * Edit AR_ADJ Adjust Amount Insert
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return adjT AR_ADJTMsg
     */
    private AR_ADJTMsg editArAdjRsvd(S21ApiMessageMap msgMap) {

        debugLog("editArAdjRsvd : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_ADJTMsg adjT = new AR_ADJTMsg();
        BigDecimal dealArAdjAmt = null;

        if (!(param.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0)) {
            dealArAdjAmt = param.dealApplyAdjRsvdAmt.getValue();
        } else {
            dealArAdjAmt = param.dealApplyAdjAmt.getValue();
        }

        if (NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(param.xxProcCaseTpCd.getValue())) {
            adjT.origArTrxBalPk.setValue(param.xxHdrTrxBalPk.getValue());
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue())) {
            adjT.origArTrxBalPk.setValue(param.rcptTrxBalPk.getValue());
        } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(param.invNum.getValue())) {
            adjT.origArTrxBalPk.setValue(param.invTrxBalPk.getValue());
        } else {
            adjT.origArTrxBalPk.setValue(param.rcptTrxBalPk.getValue());
        }
        if (!(param.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0)) {
            adjT.arAdjStsCd.setValue(NFCConst.CST_ADJ_STS_CD_SUBMITTED);
        } else {
            adjT.arAdjStsCd.setValue(NFCConst.CST_ADJ_STS_CD_APPROVED);
        }
        adjT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(param.xxProcCaseTpCd.getValue()) && NFCConst.CST_AR_ADJ_TP_CD_A_OR_R_REFUND.equals(param.arAdjTpCd.getValue())) {
            adjT.arAdjTrxTpCd.setValue(NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND);
        } else {
            adjT.arAdjTrxTpCd.setValue(param.arAdjTrxTpCd.getValue());
        }
        adjT.arAdjTpCd.setValue(param.arAdjTpCd.getValue());
        adjT.dealCcyCd.setValue(param.dealCcyCd.getValue());
        adjT.dealArAdjAmt.setValue(dealArAdjAmt);
        adjT.exchRate.setValue(param.exchRate.getValue());
        adjT.funcCcyCd.setValue(param.funcCcyCd.getValue());

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealArAdjAmt, param.exchRate.getValue());

        if (null == funcAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArAdjRsvd : end");
            return adjT;
        }

        adjT.funcArAdjAmt.setValue(funcAmt);

        adjT.adjDt.setValue(param.batDt.getValue());
        adjT.glDt.setValue(param.cashAppGlDt.getValue());
        adjT.adjCmntTxt.setValue(param.adjCmntTxt.getValue());
        adjT.adjApvlCmntTxt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        adjT.apvlByPsnCd.setValue(param.apvlPsnCd.getValue());
        adjT.tocCd.setValue(param.tocCd.getValue());
        adjT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        adjT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        adjT.coaProdCd.setValue(param.coaProdCd.getValue());

        debugLog("editArAdjRsvd : end");
        return adjT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param adjT AR_ADJTMsg
     */
    private boolean insArAdj(S21ApiMessageMap msgMap, AR_ADJTMsg adjT) {

        debugLog("insArAdj : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        String numbering = "";
        if(EZDDBCICarrier.getUppgID().equals(NFCConst.AR_CASH_APPLY_BATCH_ID)){
        	numbering = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NFCConst.CST_NUMBERING_KEY_AD_BAT);                    	                    	
        }else{
        	numbering = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NFCConst.CST_NUMBERING_KEY_AD);;                    	
        }

        adjT.arAdjNum.setValue(numbering);

        S21ApiTBLAccessor.insert(adjT);
        debugLog("AR_ADJ : INSERT RETURN CODE : " + adjT.getReturnCode());
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(adjT.getReturnCode())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0551E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("AR_ADJ : INSERT ERROR");
            debugLog("GLBL_CMPY_CD : " + adjT.glblCmpyCd.getValue());
            debugLog("AR_ADJ_NUM : " + adjT.arAdjNum.getValue());
            return true;
        }
        debugLog("insArAdj : end");
        return false;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return rcptT AR_ADJTTMsg
     */
    private AR_ADJTMsg findByKeyForUpdateArAdj(S21ApiMessageMap msgMap) {

        debugLog("findByKeyForUpdateArAdj : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_ADJTMsg adjT = new AR_ADJTMsg();
        adjT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        adjT.arAdjNum.setValue(param.arAdjNum.getValue());
        adjT = (AR_ADJTMsg) S21ApiTBLAccessor.findByKeyForUpdate(adjT);
        if (adjT == null) {

            msgMap.addXxMsgId(NFZC301001.NFCM0597E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("findByKeyForUpdateArAdj : end");
            return adjT;
        }
        if (!param.arAdjLastUpdTs.getValue().equals(adjT.ezUpTime.getValue()) || !param.arAdjTz.getValue().equals(adjT.ezUpTimeZone.getValue())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0598E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_EXCLERR);
        }
        debugLog("findByKeyForUpdateArAdj : end");
        return adjT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param adjT AR_ADJTMsg
     */
    private void updArAdj(S21ApiMessageMap msgMap, AR_ADJTMsg adjT) {

        debugLog("updArAdj : start");

        S21ApiTBLAccessor.update(adjT);
        debugLog("AR_ADJ : UPDATE RETURN CODE : " + adjT.getReturnCode());
        debugLog("updArAdj : end");
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(8, logmsg, this);
    }
}
