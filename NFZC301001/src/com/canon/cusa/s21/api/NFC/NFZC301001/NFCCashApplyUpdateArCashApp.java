/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.AR_CASH_APPTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 727
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 1504
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 1602
 * 12/07/2009   Canon           Y.Kondo         Update          DefID 2343
 * 12/15/2009   Canon           Y.Kondo         Update          DefID 2631
 * 01/27/2010   Canon           H.Tokunaga      Update          ACC case add. it is DED case copy. And, The COA_PROD_CD item is added.
 * 05/06/2010   Canon           H.Tokunaga      Update          DefID 6144
 * 11/20/2010   Canon           T.Tanaka        Update          DefID M125
 * 11/24/2010   Canon           I.Kondo         Update          M125
 * 03/04/2022   CITS            D.Mamaril       Update          QC#59333
 * 02/20/2024   Hitachi         TZ.Win          Update          QC#63450
 * </pre>
 */
public class NFCCashApplyUpdateArCashApp extends S21ApiCommonBase {

    /** Map Key : AR_CASH_APP_PK. */
    private static final String AR_CASH_APP_PK = "AR_CASH_APP_PK";

    /** Map Key : AR_CASH_APP_SQ_NUM. */
    private static final String AR_CASH_APP_SQ_NUM = "AR_CASH_APP_SQ_NUM";

    // START 2024/02/20 TZ.Win [QC#63450, ADD]
    /** Map Key : AR_CASH_APP_SORT_NUM. */
    private static final String AR_CASH_APP_SORT_NUM = "AR_CASH_APP_SORT_NUM";
    // END 2024/02/20 TZ.Win [QC#63450, ADD]

    /** BigDecimal : NAGATE. */
    private static final BigDecimal NEGATE = BigDecimal.valueOf(-1);

    /**
     */
    public NFCCashApplyUpdateArCashApp() {
        super();
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param tmpNumbering Map<String, Object>
     * @return tmpNumbering Map<String, Object>
     */
    protected Map<String, Object> execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType, Map<String, Object> tmpNumbering) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        tmpNumbering = updateCashApplyArCashApp(msgMap, onBatchType, tmpNumbering);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        debugLog("execute : end");
        return tmpNumbering;
    }

    /**
     * <pre>
     * </pre>
     */
    protected void execute(final List<NFZC301002PMsg> params, final ONBATCH_TYPE onBatchType) {

        Map<String, Object> tmpNumbering = new HashMap<String, Object>();
        tmpNumbering.put(AR_CASH_APP_PK, NFCConst.CST_DB_INIT_VAL_NUM);
        tmpNumbering.put(AR_CASH_APP_SQ_NUM, NFZC301001.INIT_CASH_APP_SQ_NUM);
        // START 2024/02/20 TZ.Win [QC#63450, ADD]
        tmpNumbering.put(AR_CASH_APP_SORT_NUM, BigDecimal.ZERO);
        // END 2024/02/20 TZ.Win [QC#63450, ADD]

        for (NFZC301002PMsg msg : params) {
            tmpNumbering = execute(msg, onBatchType, tmpNumbering);
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
     * @param tmpNumbering Map<String, Object>
     * @return tmpNumbering Map<String, Object>
     */
    protected Map<String, Object> updateCashApplyArCashApp(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, Map<String, Object> tmpNumbering) {

        debugLog("updateCashApplyArCashApp : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_XX_PROC_TP_CD_NON.equals(param.xxCashAppProcTpCd.getValue())) {
            setArCashApp(msgMap, tmpNumbering);
        }
        debugLog("updateCashApplyArCashApp : end");
        return tmpNumbering;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param tmpNumbering Map<String, Object>
     * @return tmpNumbering Map<String, Object>
     */
    private Map<String, Object> setArCashApp(S21ApiMessageMap msgMap, Map<String, Object> tmpNumbering) {

        debugLog("setArCashApp : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_CASH_APPTMsg appT = null;
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_PMT.equals(param.xxProcCaseTpCd.getValue())) {

            appT = editArCashAppRcptInv(msgMap, tmpNumbering);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {
                tmpNumbering = insArCashApp(msgMap, appT, tmpNumbering);
            }
            // START 2022/03/04 D.Mamaril [QC#59333,MOD]
            // if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT.equals(param.arAdjTrxTpCd.getValue())) {
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {
                if (NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT.equals(param.arAdjTrxTpCd.getValue())
                        || (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue())
                                && NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND.equals(param.arAdjTrxTpCd.getValue())
                                && NFCConst.CST_AR_ADJ_TP_CD_A_OR_R_REFUND.equals(param.arAdjTpCd.getValue()))) {
            // END 2022/03/04 D.Mamaril [QC#59333,MOD]
                    appT = editArCashAppInvAdj(msgMap, tmpNumbering);
                    if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {
                        tmpNumbering = insArCashApp(msgMap, appT, tmpNumbering);
                    }
                }
            // START 2022/03/04 D.Mamaril [QC#59333,ADD]
            }
            // END 2022/03/04 D.Mamaril [QC#59333,ADD]
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ADJ.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_ADJ.equals(param.xxProcCaseTpCd.getValue())
                || NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue())
                || NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(param.xxProcCaseTpCd.getValue())) {

            // 3040 or 1120)
            appT = editArCashAppRcptAdj(msgMap, tmpNumbering);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {
                tmpNumbering = insArCashApp(msgMap, appT, tmpNumbering);
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_WRK.equals(param.xxProcCaseTpCd.getValue())) {

            appT = editArCashAppRcptEdi(msgMap, tmpNumbering);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {
                tmpNumbering = insArCashApp(msgMap, appT, tmpNumbering);
            }

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxArDiffAdjNum.getValue())) {

                appT = editArCashAppInvDiffAdj(msgMap, tmpNumbering);
                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {
                    tmpNumbering = insArCashApp(msgMap, appT, tmpNumbering);
                }
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_AUTO_APVL.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_APVL.equals(param.xxProcCaseTpCd.getValue())) {

            appT = editArCashAppInvApvlAdj(msgMap, tmpNumbering);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {
                tmpNumbering = insArCashApp(msgMap, appT, tmpNumbering);
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CNT_BAL.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_CNT_BAL.equals(param.xxProcCaseTpCd.getValue())) {

            appT = editArCashAppCrMemo(msgMap, tmpNumbering);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {
                tmpNumbering = insArCashApp(msgMap, appT, tmpNumbering);
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(param.xxProcCaseTpCd.getValue())) {

            appT = editArCashAppRf(msgMap, tmpNumbering);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {
                tmpNumbering = insArCashApp(msgMap, appT, tmpNumbering);
            }
        }
        debugLog("setArCashApp : end");
        return tmpNumbering;
    }

    /**
     * <pre>
     * Edit AR_CASH_APP Receipt Invoice
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param tmpNumbering Map<String, Object>
     * @return appT AR_CASH_APPTMsg
     */
    private AR_CASH_APPTMsg editArCashAppRcptInv(S21ApiMessageMap msgMap, Map<String, Object> tmpNumbering) {

        debugLog("editArCashAppRcptInv : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealCashDiscAmt = param.dealCashDiscAmt.getValue();
        BigDecimal tmpArCashAppPk = (BigDecimal) tmpNumbering.get(AR_CASH_APP_PK);

        AR_CASH_APPTMsg appT = new AR_CASH_APPTMsg();

        appT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        appT.arCashAppPk.setValue(tmpArCashAppPk);
        appT.rcptNum.setValue(param.rcptNum.getValue());
        appT.arAdjNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        appT.setArAdjNum.setValue(appT.arAdjNum.getValue());
        appT.arTrxNum.setValue(param.invNum.getValue());
        appT.arCrTrxNum.setValue(param.crNum.getValue());
        appT.arTrxBalPk.setValue(param.invTrxBalPk.getValue());
        appT.crArTrxBalPk.setValue(param.crTrxBalPk.getValue());
        appT.exchRate.setValue(param.exchRate.getValue());
        appT.dealApplyCashDiscAmt.setValue(dealCashDiscAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealCashDiscAmt, param.exchRate.getValue());

        if (null == funcCashDiscAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppRcptInv : end");
            return appT;
        } else {
            appT.funcApplyCashDiscAmt.setValue(funcCashDiscAmt);
        }

        appT.dealApplyAmt.setValue(param.dealApplyAmt.getValue());

        BigDecimal funcApplyAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAmt.getValue(), param.exchRate.getValue());

        if (null == funcApplyAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppRcptInv : end");
            return appT;
        } else {
            appT.funcApplyAmt.setValue(funcApplyAmt);
        }

        appT.arApplyTpCd.setValue(NFCConst.CST_AR_APPLY_TP_CD_CASH);
        appT.glDt.setValue(param.cashAppGlDt.getValue());
        appT.cashAppDt.setValue(param.batDt.getValue());

        // START 2022/03/04 D.Mamaril [QC#59333,MOD]
        //if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_PMT.equals(param.xxProcCaseTpCd.getValue()) {
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_PMT.equals(param.xxProcCaseTpCd.getValue())
                || (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue())
                        && NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND.equals(param.arAdjTrxTpCd.getValue())
                        && NFCConst.CST_AR_ADJ_TP_CD_A_OR_R_REFUND.equals(param.arAdjTpCd.getValue()))) {
        // END 2022/03/04 D.Mamaril [QC#59333,MOD]
            appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_ON);
        } else {

            appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_OFF);
        }

        appT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArCashAppRcptInv : end");
        return appT;
    }

    /**
     * <pre>
     * Edit AR_CASH_APP Invoice Adjust
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param tmpNumbering Map<String, Object>
     * @return appT AR_CASH_APPTMsg
     */
    private AR_CASH_APPTMsg editArCashAppInvAdj(S21ApiMessageMap msgMap, Map<String, Object> tmpNumbering) {

        debugLog("editArCashAppInvAdj : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal tmpArCashAppPk = (BigDecimal) tmpNumbering.get(AR_CASH_APP_PK);
        String tmpArCashAppSqNum = (String) tmpNumbering.get(AR_CASH_APP_SQ_NUM);
        BigDecimal dealCashDiscAmt = NFCConst.CST_DB_INIT_VAL_NUM;
        BigDecimal dealApplyAdjAmt = param.dealApplyAdjAmt.getValue();

        AR_CASH_APPTMsg appT = new AR_CASH_APPTMsg();
        appT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        appT.arCashAppPk.setValue(tmpArCashAppPk);
        appT.arCashAppSqNum.setValue(tmpArCashAppSqNum);
        appT.rcptNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        appT.arAdjNum.setValue(param.xxArAdjNum.getValue());
        appT.arTrxNum.setValue(param.invNum.getValue());
        appT.arCrTrxNum.setValue(param.crNum.getValue());
        appT.arTrxBalPk.setValue(param.invTrxBalPk.getValue());
        appT.crArTrxBalPk.setValue(param.crTrxBalPk.getValue());
        appT.exchRate.setValue(param.exchRate.getValue());
        appT.dealApplyCashDiscAmt.setValue(dealCashDiscAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealCashDiscAmt, param.exchRate.getValue());

        if (null == funcCashDiscAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppInvAdj : end");
            return appT;
        } else {
            appT.funcApplyCashDiscAmt.setValue(funcCashDiscAmt);
        }

        appT.dealApplyAmt.setValue(dealApplyAdjAmt);

        BigDecimal funcApplyAdjAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealApplyAdjAmt, param.exchRate.getValue());

        if (null == funcApplyAdjAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppInvAdj : end");
            return appT;
        } else {
            appT.funcApplyAmt.setValue(funcApplyAdjAmt);
        }

        appT.arApplyTpCd.setValue(NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT);
        appT.glDt.setValue(param.cashAppGlDt.getValue());
        appT.cashAppDt.setValue(param.batDt.getValue());
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_PMT.equals(param.xxProcCaseTpCd.getValue())) {

            appT.setArAdjNum.setValue(param.arAdjNum.getValue());
            appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_ON);
        } else {

            appT.setArAdjNum.setValue(appT.arAdjNum.getValue());
            appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_OFF);
        }
        appT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        // START 2022/03/04 D.Mamaril [QC#59333,ADD]
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue()) && NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND.equals(param.arAdjTrxTpCd.getValue())
                && NFCConst.CST_AR_ADJ_TP_CD_A_OR_R_REFUND.equals(param.arAdjTpCd.getValue())) {
            appT.rcptNum.setValue(param.rcptNum.getValue());
            appT.arTrxNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            appT.arApplyTpCd.setValue(NFCConst.CST_AR_APPLY_TP_CD_REFUND);
        }
        // END 2022/03/04 D.Mamaril [QC#59333,ADD]

        debugLog("editArCashAppInvAdj : end");
        return appT;
    }

    /**
     * <pre>
     * Edit AR_CASH_APP Receipt Adjust
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param tmpNumbering Map<String, Object>
     * @return appT AR_CASH_APPTMsg
     */
    private AR_CASH_APPTMsg editArCashAppRcptAdj(S21ApiMessageMap msgMap, Map<String, Object> tmpNumbering) {

        debugLog("editArCashAppRcptAdj : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealCashDiscAmt = param.dealCashDiscAmt.getValue();
        BigDecimal dealApplyAdjAmt = param.dealApplyAdjAmt.getValue();
        BigDecimal tmpArCashAppPk = (BigDecimal) tmpNumbering.get(AR_CASH_APP_PK);
        String tmpArCashAppSqNum = (String) tmpNumbering.get(AR_CASH_APP_SQ_NUM);

        AR_CASH_APPTMsg appT = new AR_CASH_APPTMsg();

        appT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        appT.arCashAppPk.setValue(tmpArCashAppPk);
        appT.arCashAppSqNum.setValue(tmpArCashAppSqNum);
        appT.rcptNum.setValue(param.rcptNum.getValue());
        appT.arAdjNum.setValue(param.xxArAdjNum.getValue());
        appT.arTrxNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        appT.arCrTrxNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        appT.arTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        appT.crArTrxBalPk.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        appT.exchRate.setValue(param.exchRate.getValue());
        appT.dealApplyCashDiscAmt.setValue(dealCashDiscAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealCashDiscAmt, param.exchRate.getValue());

        if (null == funcCashDiscAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppRcptAdj : end");
            return appT;
        } else {
            appT.funcApplyCashDiscAmt.setValue(funcCashDiscAmt);
        }

        appT.dealApplyAmt.setValue(dealApplyAdjAmt);

        BigDecimal funcApplyAdjAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealApplyAdjAmt, param.exchRate.getValue());

        if (null == funcApplyAdjAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppRcptAdj : end");
            return appT;
        } else {
            appT.funcApplyAmt.setValue(funcApplyAdjAmt);
        }

        appT.arApplyTpCd.setValue(NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT);
        appT.glDt.setValue(param.cashAppGlDt.getValue());
        appT.cashAppDt.setValue(param.batDt.getValue());
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_ADJ.equals(param.xxProcCaseTpCd.getValue())) {

            appT.setArAdjNum.setValue(param.arAdjNum.getValue());
            appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_ON);
        } else {

            appT.setArAdjNum.setValue(appT.arAdjNum.getValue());
            appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_OFF);
        }
        appT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArCashAppRcptAdj : end");
        return appT;
    }

    /**
     * <pre>
     * Edit AR_CASH_APP Receipt EDI Invoice
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param tmpNumbering Map<String, Object>
     * @return appT AR_CASH_APPTMsg
     */
    private AR_CASH_APPTMsg editArCashAppRcptEdi(S21ApiMessageMap msgMap, Map<String, Object> tmpNumbering) {

        debugLog("editArCashAppRcptEdi : start");
        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealApplyCashDiscAmt = param.xxDtlDiscAmt.getValue();
        BigDecimal tmpArCashAppPk = (BigDecimal) tmpNumbering.get(AR_CASH_APP_PK);
        String tmpArCashAppSqNum = (String) tmpNumbering.get(AR_CASH_APP_SQ_NUM);

        AR_CASH_APPTMsg appT = new AR_CASH_APPTMsg();

        appT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        appT.arCashAppPk.setValue(tmpArCashAppPk);
        appT.arCashAppSqNum.setValue(tmpArCashAppSqNum);
        appT.rcptNum.setValue(param.rcptNum.getValue());
        appT.arAdjNum.setValue(param.arAdjNum.getValue());
        appT.setArAdjNum.setValue(appT.arAdjNum.getValue());
        appT.arTrxNum.setValue(param.invNum.getValue());
        appT.arCrTrxNum.setValue(param.crNum.getValue());
        appT.arTrxBalPk.setValue(param.invTrxBalPk.getValue());
        appT.crArTrxBalPk.setValue(param.crTrxBalPk.getValue());
        appT.exchRate.setValue(param.exchRate.getValue());
        appT.dealApplyCashDiscAmt.setValue(dealApplyCashDiscAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcApplyCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealApplyCashDiscAmt, param.exchRate.getValue());
        if (null == funcApplyCashDiscAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppRcptEdi : end");
            return appT;
        } else {
            appT.funcApplyCashDiscAmt.setValue(funcApplyCashDiscAmt);
        }

        appT.dealApplyAmt.setValue(param.dealApplyAmt.getValue());

        BigDecimal funcApplyAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAmt.getValue(), param.exchRate.getValue());
        if (null == funcApplyAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppRcptEdi : end");
            return appT;
        } else {
            appT.funcApplyAmt.setValue(funcApplyAmt);
        }

        appT.arApplyTpCd.setValue(NFCConst.CST_AR_APPLY_TP_CD_CASH);
        appT.glDt.setValue(param.cashAppGlDt.getValue());
        appT.cashAppDt.setValue(param.batDt.getValue());
        appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArCashAppRcptEdi : end");
        return appT;
    }

    /**
     * <pre>
     * Edit AR_CASH_APP Invoice Diff Adjust
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param tmpNumbering Map<String, Object>
     * @return appT AR_CASH_APPTMsg
     */
    private AR_CASH_APPTMsg editArCashAppInvDiffAdj(S21ApiMessageMap msgMap, Map<String, Object> tmpNumbering) {

        debugLog("editArCashAppInvDiffAdj : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealCashDiscAmt = NFCConst.CST_DB_INIT_VAL_NUM;
        BigDecimal tmpArCashAppPk = (BigDecimal) tmpNumbering.get(AR_CASH_APP_PK);
        String tmpArCashAppSqNum = (String) tmpNumbering.get(AR_CASH_APP_SQ_NUM);
        // It is judged contents in ADJ_TP
        String rcptNum = null;
        String arTrxNum = null;
        String arCrTrxNum = null;
        BigDecimal arTrxBalPk = null;
        BigDecimal crArTrxBalPk = null;
        BigDecimal dealApplyAmt = null;

        // The Diff judges ADJ_TP by a plus or a minus
        if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.xxDtlDiffAmt.getValue()) <= 0) {

            // ADJ_TP : B(NON_OPERATING_MISCELLANEOUS)
            rcptNum = param.rcptNum.getValue();
            arTrxNum = NFCConst.CST_DB_INIT_VAL_STR;
            arCrTrxNum = NFCConst.CST_DB_INIT_VAL_STR;
            arTrxBalPk = NFCConst.CST_DB_INIT_VAL_NUM;
            crArTrxBalPk = NFCConst.CST_DB_INIT_VAL_NUM;
            dealApplyAmt = param.xxDtlDiffAmt.getValue().multiply(NEGATE);
        } else {

            // ADJ_TP : C(MISCELLANEOUS_INCOME)
            rcptNum = param.rcptNum.getValue();
            arTrxNum = NFCConst.CST_DB_INIT_VAL_STR;
            arCrTrxNum = NFCConst.CST_DB_INIT_VAL_STR;
            arTrxBalPk = NFCConst.CST_DB_INIT_VAL_NUM;
            crArTrxBalPk = NFCConst.CST_DB_INIT_VAL_NUM;
            dealApplyAmt = param.xxDtlDiffAmt.getValue().multiply(NEGATE);
        }
        AR_CASH_APPTMsg appT = new AR_CASH_APPTMsg();

        appT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        appT.arCashAppPk.setValue(tmpArCashAppPk);
        appT.arCashAppSqNum.setValue(tmpArCashAppSqNum);
        appT.rcptNum.setValue(rcptNum);
        appT.arAdjNum.setValue(param.xxArDiffAdjNum.getValue());
        appT.setArAdjNum.setValue(appT.arAdjNum.getValue());
        appT.arTrxNum.setValue(arTrxNum);
        appT.arCrTrxNum.setValue(arCrTrxNum);
        appT.arTrxBalPk.setValue(arTrxBalPk);
        appT.crArTrxBalPk.setValue(crArTrxBalPk);
        appT.exchRate.setValue(param.exchRate.getValue());
        appT.dealApplyCashDiscAmt.setValue(dealCashDiscAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealCashDiscAmt, param.exchRate.getValue());

        if (null == funcCashDiscAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppInvDiffAdj : end");
            return appT;
        } else {
            appT.funcApplyCashDiscAmt.setValue(funcCashDiscAmt);
        }

        appT.dealApplyAmt.setValue(dealApplyAmt);

        BigDecimal funcApplyAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealApplyAmt, param.exchRate.getValue());

        if (null == funcApplyAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppInvDiffAdj : end");
            return appT;
        } else {
            appT.funcApplyAmt.setValue(funcApplyAmt);
        }

        appT.arApplyTpCd.setValue(NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT);
        appT.glDt.setValue(param.cashAppGlDt.getValue());
        appT.cashAppDt.setValue(param.batDt.getValue());
        appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArCashAppInvDiffAdj : end");
        return appT;
    }

    /**
     * <pre>
     * Edit AR_CASH_APP Invoice Approval Adjust
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param tmpNumbering Map<String, Object>
     * @return appT AR_CASH_APPTMsg
     */
    private AR_CASH_APPTMsg editArCashAppInvApvlAdj(S21ApiMessageMap msgMap, Map<String, Object> tmpNumbering) {

        debugLog("editArCashAppInvApvlAdj : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealCashDiscAmt = param.dealCashDiscAmt.getValue();
        BigDecimal dealApplyAdjAmt = param.dealApplyAdjAmt.getValue();
        BigDecimal tmpArCashAppPk = (BigDecimal) tmpNumbering.get(AR_CASH_APP_PK);
        String tmpArCashAppSqNum = (String) tmpNumbering.get(AR_CASH_APP_SQ_NUM);

        AR_CASH_APPTMsg appT = new AR_CASH_APPTMsg();

        appT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        appT.arCashAppPk.setValue(tmpArCashAppPk);
        appT.arCashAppSqNum.setValue(tmpArCashAppSqNum);
        appT.rcptNum.setValue(param.rcptNum.getValue());
        if (!NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxArAdjNum.getValue())) {
            appT.arAdjNum.setValue(param.xxArAdjNum.getValue());
        } else {
            appT.arAdjNum.setValue(param.arAdjNum.getValue());
        }
        appT.setArAdjNum.setValue(appT.arAdjNum.getValue());
        appT.arTrxNum.setValue(param.invNum.getValue());
        appT.arCrTrxNum.setValue(param.crNum.getValue());
        appT.arTrxBalPk.setValue(param.invTrxBalPk.getValue());
        appT.crArTrxBalPk.setValue(param.crTrxBalPk.getValue());
        appT.exchRate.setValue(param.exchRate.getValue());
        appT.dealApplyCashDiscAmt.setValue(dealCashDiscAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealCashDiscAmt, param.exchRate.getValue());

        if (null == funcCashDiscAmt) {

            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppInvDiffAdj : end");
            return appT;
        } else {
            appT.funcApplyCashDiscAmt.setValue(funcCashDiscAmt);
        }

        appT.dealApplyAmt.setValue(dealApplyAdjAmt);

        BigDecimal funcApplyAdjAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealApplyAdjAmt, param.exchRate.getValue());

        if (null == funcApplyAdjAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppInvDiffAdj : end");
            return appT;
        } else {
            appT.funcApplyAmt.setValue(funcApplyAdjAmt);
        }

        appT.arApplyTpCd.setValue(NFCConst.CST_AR_APPLY_TP_CD_ADJUSTMENT);
        appT.glDt.setValue(param.cashAppGlDt.getValue());
        appT.cashAppDt.setValue(param.batDt.getValue());
        appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArCashAppInvApvlAdj : end");
        return appT;
    }

    /**
     * <pre>
     * Edit AR_CASH_APP CreditMemo
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param tmpNumbering Map<String, Object>
     * @return appT AR_CASH_APPTMsg
     */
    private AR_CASH_APPTMsg editArCashAppCrMemo(S21ApiMessageMap msgMap, Map<String, Object> tmpNumbering) {

        debugLog("editArCashAppCrMemo : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealCashDiscAmt = param.dealCashDiscAmt.getValue();
        BigDecimal tmpArCashAppPk = (BigDecimal) tmpNumbering.get(AR_CASH_APP_PK);
        String tmpArCashAppSqNum = (String) tmpNumbering.get(AR_CASH_APP_SQ_NUM);

        AR_CASH_APPTMsg appT = new AR_CASH_APPTMsg();

        appT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        appT.arCashAppPk.setValue(tmpArCashAppPk);
        appT.arCashAppSqNum.setValue(tmpArCashAppSqNum);
        appT.rcptNum.setValue(param.rcptNum.getValue());
        appT.arAdjNum.setValue(param.arAdjNum.getValue());
        appT.setArAdjNum.setValue(appT.arAdjNum.getValue());
        appT.arTrxNum.setValue(param.invNum.getValue());
        appT.arCrTrxNum.setValue(param.crNum.getValue());
        appT.arTrxBalPk.setValue(param.invTrxBalPk.getValue());
        appT.crArTrxBalPk.setValue(param.crTrxBalPk.getValue());
        appT.exchRate.setValue(param.exchRate.getValue());
        appT.dealApplyCashDiscAmt.setValue(dealCashDiscAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealCashDiscAmt, param.exchRate.getValue());

        if (null == funcCashDiscAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppCrMemo : end");
            return appT;
        } else {
            appT.funcApplyCashDiscAmt.setValue(funcCashDiscAmt);
        }

        appT.dealApplyAmt.setValue(param.dealApplyAmt.getValue());

        BigDecimal funcApplyAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAmt.getValue(), param.exchRate.getValue());

        if (null == funcApplyAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppCrMemo : end");
            return appT;
        } else {
            appT.funcApplyAmt.setValue(funcApplyAmt);
        }

        appT.arApplyTpCd.setValue(NFCConst.CST_AR_APPLY_TP_CD_CREDITMEMO);
        appT.glDt.setValue(param.cashAppGlDt.getValue());
        appT.cashAppDt.setValue(param.batDt.getValue());
        appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArCashAppCrMemo : end");
        return appT;
    }

    /**
     * <pre>
     * Edit AR_CASH_APP Refund
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param tmpNumbering Map<String, Object>
     * @return appT AR_CASH_APPTMsg
     */
    private AR_CASH_APPTMsg editArCashAppRf(S21ApiMessageMap msgMap, Map<String, Object> tmpNumbering) {

        debugLog("editArCashAppRf : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealCashDiscAmt = param.dealCashDiscAmt.getValue();
        BigDecimal dealApplyAdjAmt = param.dealApplyAdjAmt.getValue();
        BigDecimal tmpArCashAppPk = (BigDecimal) tmpNumbering.get(AR_CASH_APP_PK);
        String tmpArCashAppSqNum = (String) tmpNumbering.get(AR_CASH_APP_SQ_NUM);

        AR_CASH_APPTMsg appT = new AR_CASH_APPTMsg();

        appT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        appT.arCashAppPk.setValue(tmpArCashAppPk);
        appT.arCashAppSqNum.setValue(tmpArCashAppSqNum);
        appT.rcptNum.setValue(param.rcptNum.getValue());
        appT.arAdjNum.setValue(param.xxArAdjNum.getValue());
        appT.setArAdjNum.setValue(appT.arAdjNum.getValue());
        appT.arTrxNum.setValue(param.invNum.getValue());
        appT.arCrTrxNum.setValue(param.crNum.getValue());
        appT.arTrxBalPk.setValue(param.invTrxBalPk.getValue());
        appT.crArTrxBalPk.setValue(param.crTrxBalPk.getValue());
        appT.exchRate.setValue(param.exchRate.getValue());
        appT.dealApplyCashDiscAmt.setValue(dealCashDiscAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealCashDiscAmt, param.exchRate.getValue());
        if (null == funcCashDiscAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppRf : end");
            return appT;
        } else {
            appT.funcApplyCashDiscAmt.setValue(funcCashDiscAmt);
        }

        appT.dealApplyAmt.setValue(dealApplyAdjAmt);

        BigDecimal funcApplyAdjAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealApplyAdjAmt, param.exchRate.getValue());
        if (null == funcApplyAdjAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArCashAppRf : end");
            return appT;
        } else {
            appT.funcApplyAmt.setValue(funcApplyAdjAmt);
        }

        appT.arApplyTpCd.setValue(NFCConst.CST_AR_APPLY_TP_CD_REFUND);
        appT.glDt.setValue(param.cashAppGlDt.getValue());
        appT.cashAppDt.setValue(param.batDt.getValue());
        appT.arScrCancFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        appT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArCashAppRf : end");
        return appT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param unApplyT AR_CASH_APPTMsg
     * @return tmpNumbering Map<String, Object>
     */
    private Map<String, Object> insArCashApp(S21ApiMessageMap msgMap, AR_CASH_APPTMsg appT, Map<String, Object> tmpNumbering) {

        debugLog("insArCashApp : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal tmpArCashAppPk = (BigDecimal) tmpNumbering.get(AR_CASH_APP_PK);
        String tmpArCashAppSqNum = (String) tmpNumbering.get(AR_CASH_APP_SQ_NUM);
        // START 2024/02/20 TZ.Win [QC#63450, ADD]
        BigDecimal tmpArCashAppSortNum = (BigDecimal) tmpNumbering.get(AR_CASH_APP_SORT_NUM);
        // END 2024/02/20 TZ.Win [QC#63450, ADD]

        if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(tmpArCashAppPk) == 0 || (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.arCashAppPk.getValue()) != 0 && tmpArCashAppPk.compareTo(param.arCashAppPk.getValue()) != 0)) {

            tmpArCashAppPk = param.arCashAppPk.getValue();
            tmpNumbering.put(AR_CASH_APP_PK, tmpArCashAppPk);
            tmpArCashAppSqNum = NFZC301001.INIT_CASH_APP_SQ_NUM;

            // START 2024/02/20 TZ.Win [QC#63450, ADD]
            if (ZYPCommonFunc.hasValue(appT.rcptNum.getValue())) {
                Map<String, Object> sortParam = new HashMap<String, Object>();
                sortParam.put("glblCmpyCd", appT.glblCmpyCd.getValue());
                sortParam.put("rcptNum", appT.rcptNum.getValue());
                S21SsmEZDResult result = NFZC301001Query.getInstance().getMaxSortNum(sortParam);
                if (result.isCodeNormal() && result.getResultObject() != null) {
                    tmpArCashAppSortNum = (BigDecimal) result.getResultObject();
                } else {
                    tmpArCashAppSortNum = BigDecimal.ZERO;
                }
            } else {
                tmpArCashAppSortNum = BigDecimal.ZERO;
            }
            // END 2024/02/20 TZ.Win [QC#63450, ADD]
        }

        if (tmpArCashAppPk.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

            NFCNumbering numbering = new NFCNumbering();
            NFXC3060Bean numberBean = numbering.getNumber(NFCConst.CST_SEQ_ID_AR_CASH_APP, null, 0);
            if (!NFCConst.CST_RTN_CD_NORM.equals(numberBean.getRtrnNo())) {

                msgMap.addXxMsgId(NFZC301001.NFCM0530E);
                param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
                debugLog("insArCashApp : end");
                return tmpNumbering;
            } else {
                tmpArCashAppPk = numberBean.getOraSqNo();
                tmpNumbering.put(AR_CASH_APP_PK, tmpArCashAppPk);
            }
        }

        appT.arCashAppPk.setValue(tmpArCashAppPk);
        int sqNumCounter = Integer.parseInt(tmpArCashAppSqNum);
        sqNumCounter++;
        String arCashAppSqNum = NFZC301001.zeroPadding(sqNumCounter, NFZC301001.FIVE_FIGURE);
        appT.arCashAppSqNum.setValue(arCashAppSqNum);
        tmpNumbering.put(AR_CASH_APP_SQ_NUM, arCashAppSqNum);

        // START 2024/02/20 TZ.Win [QC#63450, ADD]
        BigDecimal sortNumCounter = tmpArCashAppSortNum.add(BigDecimal.ONE);
        appT.arCashAppSortNum.setValue(sortNumCounter);
        tmpNumbering.put(AR_CASH_APP_SORT_NUM, sortNumCounter);
        // END 2024/02/20 TZ.Win [QC#63450, ADD]

        S21ApiTBLAccessor.insert(appT);
        debugLog("AR_CASH_APP : INSERT RETURN CODE : " + appT.getReturnCode());
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(appT.getReturnCode())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0562E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("AR_CASH_APP : INSERT ERROR");
            debugLog("GLBL_CMPY_CD : " + appT.glblCmpyCd.getValue());
            debugLog("AR_CASH_APP_PK : " + appT.arCashAppPk.getValue());
            debugLog("AR_CASH_APP_SQ_NUM : " + appT.arCashAppSqNum.getValue());
        } else {
            param.arCashAppPk.setValue(appT.arCashAppPk.getValue());
        }
        debugLog("insArCashApp : end");
        return tmpNumbering;
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
