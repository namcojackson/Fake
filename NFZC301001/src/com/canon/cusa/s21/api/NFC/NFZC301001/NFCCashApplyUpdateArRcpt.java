/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.AR_RCPTTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFXC3070Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 12/07/2009   Canon           Y.Kondo         Create          DefID 2343
 * 05/06/2010   Canon           H.Tokunaga      Update          DefID 6144
 * 05/20/2010   Canon           I.Kondo         Update          DefID 6144/6452
 * 05/20/2010   Canon           I.Kondo         Update          DefID 6444 No.016
 * 05/20/2010   Canon           I.Kondo         Update          DefID 6641 No.020
 * 02/16/2011   Canon           T.Tanaka        Update          DefID:1595 Cash App Func Amount
 * 02/22/2016   Fujitsu         T.Tanaka        Update          Def#2631
 * 09/20/2018   Fujitsu         T.Ogura         Update          QC#28097
 * 03/04/2022   CITS            D.Mamaril       Update          QC#59333
 * 04/22/2022   CITS            D.Mamaril       Update          QC#59333
 * 09/26/2022   CITS            D.Mamaril       Update          QC#60602
 *</pre>
 */
public class NFCCashApplyUpdateArRcpt extends S21ApiCommonBase {

    /** BigDecimal : NAGATE. */
    private static final BigDecimal NEGATE = BigDecimal.valueOf(-1);

    /** AR_CASH_APPLY_STS_CD : Apply */
    private static final String AR_CASH_APPLY_STS_CD_A = "A";

    /**
     */
    public NFCCashApplyUpdateArRcpt() {
        super();
    }

    /**
     * <pre>
     * </pre>
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateCashApplyArRcpt(msgMap, onBatchType);
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
    protected void updateCashApplyArRcpt(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("updateCashApplyArRcpt : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_XX_PROC_TP_CD_NON.equals(param.xxRcptHdrProcTpCd.getValue())
                && NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(param.xxHdrNumGetFlg.getValue())) {
            setArRcpt(msgMap);
        }
        debugLog("updateCashApplyArRcpt : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void setArRcpt(S21ApiMessageMap msgMap) {

        debugLog("setArRcpt : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT.equals(param.xxProcCaseTpCd.getValue())) {

            AR_RCPTTMsg rcptT = findByKeyForUpdateArRcpt(msgMap);

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                rcptT = editArRcptNew(msgMap, rcptT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArRcpt(msgMap, rcptT);
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(param.xxProcCaseTpCd.getValue())) {

            AR_RCPTTMsg rcptT = findByKeyForUpdateArRcpt(msgMap);

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                rcptT = editArRcptRf(msgMap, rcptT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArRcpt(msgMap, rcptT);
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CHNG_RCPT.equals(param.xxProcCaseTpCd.getValue())) {

            AR_RCPTTMsg rcptT = findByKeyForUpdateArRcpt(msgMap);

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                if (NFCConst.CST_DB_INIT_VAL_STR.equals(rcptT.payerCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue())) {

                    rcptT = editArRcptChng(msgMap, rcptT);
                    if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                        updArRcpt(msgMap, rcptT);
                    }
                }
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_RCPT.equals(param.xxProcCaseTpCd.getValue())) {

            AR_RCPTTMsg rcptT = findByKeyForUpdateArRcpt(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                rcptT = editArRcptCanc(msgMap, rcptT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArRcpt(msgMap, rcptT);
            }
        } else {

            AR_RCPTTMsg rcptT = findByKeyForUpdateArRcpt(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                rcptT = editArRcptApply(msgMap, rcptT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArRcpt(msgMap, rcptT);
            }
        }
        debugLog("setArRcpt : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rcptT AR_RCPTTMsg
     * @return rcptT AR_RCPTTMsg
     */
    private AR_RCPTTMsg editArRcptNew(S21ApiMessageMap msgMap, AR_RCPTTMsg rcptT) {

        debugLog("editArRcptNew : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealRcptAmt = null;
        BigDecimal dealRcptRmngBalAmt = null;
        BigDecimal funcRcptRmngBalAmt = null;
        BigDecimal funcRcptAmt = null;

        dealRcptAmt = rcptT.dealRcptAmt.getValue().add(param.xxTotRcptApplyAmt.getValue());

        NFCCurrencyConversion afxc307001 = new NFCCurrencyConversion();
        NFXC3070Bean currencyData = afxc307001.convertCurrency(rcptT.glblCmpyCd.getValue(), rcptT.dealCcyCd.getValue(), param.xxTotRcptApplyAmt.getValue(), rcptT.glDt.getValue(), null);
        if (!NFCConst.CST_RTN_CD_NORM.equals(currencyData.getRtrnCd())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArRcptRf : end");
            return rcptT;
        }
        funcRcptAmt = currencyData.getFuncAmt();
        funcRcptAmt = funcRcptAmt.add(rcptT.funcRcptAmt.getValue());

        dealRcptRmngBalAmt = NFZC301001.subtractBalAmt(dealRcptAmt, rcptT.dealApplyAmt.getValue(), rcptT.dealApplyAdjAmt.getValue(), rcptT.dealRfAmt.getValue(), rcptT.dealVoidAmt.getValue());
        funcRcptRmngBalAmt = NFZC301001.subtractFuncAmt(funcRcptAmt, rcptT.funcApplyAmt.getValue(), rcptT.funcApplyAdjAmt.getValue(), rcptT.funcRfAmt.getValue(), rcptT.funcVoidAmt.getValue(), rcptT.funcRvalVarAmt.getValue());

        rcptT.dealRcptAmt.setValue(dealRcptAmt);
        rcptT.dealRcptRmngBalAmt.setValue(dealRcptRmngBalAmt);
        rcptT.exchRate.setValue(currencyData.getExchRate());
        rcptT.funcCcyCd.setValue(currencyData.getFuncCcyCd());
        rcptT.funcRcptAmt.setValue(funcRcptAmt);
        rcptT.funcRcptRmngBalAmt.setValue(funcRcptRmngBalAmt);
        rcptT.cashAppDt.setValue(param.batDt.getValue());
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(rcptT.payerCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue())) {

            rcptT.payerCustCd.setValue(param.payerCustCd.getValue());
        }

        debugLog("editArRcptNew : end");
        return rcptT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rcptT AR_RCPTTMsg
     * @return rcptT AR_RCPTTMsg
     */
    private AR_RCPTTMsg editArRcptRf(S21ApiMessageMap msgMap, AR_RCPTTMsg rcptT) {

        debugLog("editArRcptRf : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealRfAmt = null;
        BigDecimal dealRcptRmngBalAmt = null;
        BigDecimal funcRcptRmngBalAmt = null;
        BigDecimal funcRfAmt = null;

        dealRfAmt = rcptT.dealRfAmt.getValue().add(param.xxTotRfAmt.getValue());

        NFCCurrencyConversion afxc307001 = new NFCCurrencyConversion();
        NFXC3070Bean currencyData = afxc307001.convertCurrency(rcptT.glblCmpyCd.getValue(), rcptT.dealCcyCd.getValue(), param.xxTotRfAmt.getValue(), rcptT.rfGlDt.getValue(), null);
        if (!NFCConst.CST_RTN_CD_NORM.equals(currencyData.getRtrnCd())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArRcptRf : end");
            return rcptT;
        }
        funcRfAmt =  currencyData.getFuncAmt();
        funcRfAmt = funcRfAmt.add(rcptT.funcRfAmt.getValue());

        dealRcptRmngBalAmt = NFZC301001.subtractBalAmt(rcptT.dealRcptAmt.getValue(), rcptT.dealApplyAmt.getValue(), rcptT.dealApplyAdjAmt.getValue(), dealRfAmt, rcptT.dealVoidAmt.getValue());
        funcRcptRmngBalAmt = NFZC301001
                .subtractFuncAmt(rcptT.funcRcptAmt.getValue(), rcptT.funcApplyAmt.getValue(), rcptT.funcApplyAdjAmt.getValue(), funcRfAmt, rcptT.funcVoidAmt.getValue(), rcptT.funcRvalVarAmt.getValue());

        rcptT.dealRfAmt.setValue(dealRfAmt);
        rcptT.dealRcptRmngBalAmt.setValue(dealRcptRmngBalAmt);
        rcptT.funcRfAmt.setValue(funcRfAmt);
        rcptT.funcRcptRmngBalAmt.setValue(funcRcptRmngBalAmt);
        rcptT.cashAppDt.setValue(param.batDt.getValue());
        rcptT.rfExchRate.setValue(currencyData.getExchRate());

        debugLog("editArRcptRf : end");
        return rcptT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rcptT AR_RCPTTMsg
     * @return rcptT AR_RCPTTMsg
     */
    private AR_RCPTTMsg editArRcptChng(S21ApiMessageMap msgMap, AR_RCPTTMsg rcptT) {

        debugLog("editArRcptChng : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        rcptT.payerCustCd.setValue(param.payerCustCd.getValue());
        rcptT.cashAppDt.setValue(param.batDt.getValue());

        debugLog("editArRcptChng : end");
        return rcptT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rcptT AR_RCPTTMsg
     * @return rcptT AR_RCPTTMsg
     */
    private AR_RCPTTMsg editArRcptCanc(S21ApiMessageMap msgMap, AR_RCPTTMsg rcptT) {

        debugLog("editArRcptCanc : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealVoidAmt = null;
        BigDecimal dealRcptRmngBalAmt = null;
        BigDecimal funcRcptRmngBalAmt = null;
        BigDecimal funcVoidAmt = null;

        dealVoidAmt = rcptT.dealVoidAmt.getValue().add(param.xxTotVoidAmt.getValue());

        NFCCurrencyConversion afxc307001 = new NFCCurrencyConversion();
        NFXC3070Bean currencyData = afxc307001.convertCurrency(rcptT.glblCmpyCd.getValue(), rcptT.dealCcyCd.getValue(), param.xxTotVoidAmt.getValue(), rcptT.glDt.getValue(), null);
        if (!NFCConst.CST_RTN_CD_NORM.equals(currencyData.getRtrnCd())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArRcptCanc : end");
            return rcptT;
        }
        funcVoidAmt = currencyData.getFuncAmt();
        funcVoidAmt = funcVoidAmt.add(rcptT.funcVoidAmt.getValue());

        dealRcptRmngBalAmt = NFZC301001.subtractBalAmt(rcptT.dealRcptAmt.getValue(), rcptT.dealApplyAmt.getValue(), rcptT.dealApplyAdjAmt.getValue(), rcptT.dealRfAmt.getValue(), dealVoidAmt);
        funcRcptRmngBalAmt = NFZC301001.subtractFuncAmt(rcptT.funcRcptAmt.getValue(), rcptT.funcApplyAmt.getValue(), rcptT.funcApplyAdjAmt.getValue(), rcptT.funcRfAmt.getValue(), funcVoidAmt, rcptT.funcRvalVarAmt.getValue());

        rcptT.dealVoidAmt.setValue(dealVoidAmt);
        rcptT.dealRcptRmngBalAmt.setValue(dealRcptRmngBalAmt);
        rcptT.funcVoidAmt.setValue(funcVoidAmt);
        rcptT.funcRcptRmngBalAmt.setValue(funcRcptRmngBalAmt);
        rcptT.cashAppDt.setValue(param.batDt.getValue());

        debugLog("editArRcptCanc : end");
        return rcptT;
    }

    /**
     * Edit AR_RCPT Apply
     * @param msgMap S21ApiMessageMap
     * @param rcptT AR_RCPTTMsg
     * @return rcptT AR_RCPTTMsg
     */
    private AR_RCPTTMsg editArRcptApply(S21ApiMessageMap msgMap, AR_RCPTTMsg rcptT) {

        debugLog("editArRcptApply : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealApplyAmt = null;
        BigDecimal dealApplyAmt2 = null;
        BigDecimal dealApplyAdjAmt = null;
        BigDecimal dealApplyAdjAmt2 = null;
        BigDecimal dealRcptRmngBalAmt = null;
        BigDecimal funcRcptRmngBalAmt = null;
        BigDecimal funcApplyAmt = null;
        BigDecimal funcApplyAdjAmt = null;

        // START 2022/04/22 D.Mamaril [QC#59333,ADD]
        BigDecimal dealRfAmt = null;
        BigDecimal funcRfAmt = null;
        // END 2022/04/22 D.Mamaril [QC#59333,ADD]

        NFCCurrencyConversion afxc307001 = new NFCCurrencyConversion();
        Map<String, Object> exchRateData = null;

        dealApplyAmt = rcptT.dealApplyAmt.getValue().add(param.xxTotInvApplyAmt.getValue());
        dealApplyAmt2 = param.xxTotInvApplyAmt.getValue();

        dealApplyAdjAmt = rcptT.dealApplyAdjAmt.getValue().add(param.xxTotDedctApplyAmt.getValue().add(param.xxTotAdjAmt.getValue()));
        dealApplyAdjAmt2 = param.xxTotDedctApplyAmt.getValue().add(param.xxTotAdjAmt.getValue());

        // The Diff judges ADJ_TP by a plus or a minus
        if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.xxDtlDiffAmt.getValue()) > 0) {

            // ADJ_TP : C(MISCELLANEOUS_INCOME)
            // Add a DiffAmount that reversed a sign
            dealApplyAdjAmt = dealApplyAdjAmt.add(param.xxDtlDiffAmt.getValue().multiply(NEGATE));
            // 2010/04/27 H.Tokunaga Add Start defid 6144
        } else {
            dealApplyAdjAmt = dealApplyAdjAmt.add(param.xxDtlDiffAmt.getValue().multiply(NEGATE));
            debugLog("editArRcptApply after dealApplyAdjAmt  : " + dealApplyAdjAmt);
            // 2010/04/27 H.Tokunaga Add end defid 6144
        }

        NFXC3070Bean currencyData = afxc307001.convertCurrency(rcptT.glblCmpyCd.getValue(), rcptT.dealCcyCd.getValue(), dealApplyAmt2, rcptT.glDt.getValue(), null);
        if (!NFCConst.CST_RTN_CD_NORM.equals(currencyData.getRtrnCd())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArRcptApply : end");
            return rcptT;
        }
        exchRateData = currencyData.getExchRateData();
        funcApplyAmt = currencyData.getFuncAmt();
        funcApplyAmt = funcApplyAmt.add(rcptT.funcApplyAmt.getValue());

        NFXC3070Bean adjCurrencyData = afxc307001.convertCurrency(rcptT.glblCmpyCd.getValue(), rcptT.dealCcyCd.getValue(), dealApplyAdjAmt2, rcptT.glDt.getValue(), exchRateData);
        if (!NFCConst.CST_RTN_CD_NORM.equals(adjCurrencyData.getRtrnCd())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArRcptApply : end");
            return rcptT;
        }
        funcApplyAdjAmt = adjCurrencyData.getFuncAmt();
        funcApplyAdjAmt = funcApplyAdjAmt.add(rcptT.funcApplyAdjAmt.getValue());

        // START 2022/04/22 D.Mamaril [QC#59333,ADD]
        dealRfAmt = rcptT.dealRfAmt.getValue().add(param.xxTotRfAmt.getValue());
        NFXC3070Bean rfdCurrencyData = afxc307001.convertCurrency(rcptT.glblCmpyCd.getValue(), rcptT.dealCcyCd.getValue(), param.xxTotRfAmt.getValue(), rcptT.glDt.getValue(), exchRateData);
        if (!NFCConst.CST_RTN_CD_NORM.equals(rfdCurrencyData.getRtrnCd())) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArRcptApply : end");
            return rcptT;
        }
        funcRfAmt = rfdCurrencyData.getFuncAmt();
        funcRfAmt = funcRfAmt.add(rcptT.funcRfAmt.getValue());

        if (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue())
                && NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND.equals(param.arAdjTrxTpCd.getValue()) && NFCConst.CST_AR_ADJ_TP_CD_A_OR_R_REFUND.equals(param.arAdjTpCd.getValue())) {
            dealRfAmt = dealRfAmt.add(param.dealApplyAdjAmt.getValue());
            NFXC3070Bean rfdApplyCurrencyData = afxc307001.convertCurrency(rcptT.glblCmpyCd.getValue(), rcptT.dealCcyCd.getValue(), param.dealApplyAdjAmt.getValue(), rcptT.glDt.getValue(), null);
            if (!NFCConst.CST_RTN_CD_NORM.equals(rfdApplyCurrencyData.getRtrnCd())) {
                msgMap.addXxMsgId(NFZC301001.NFCM0550E);
                param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
                debugLog("editArRcptApply : end");
                return rcptT;
            }
            funcRfAmt = funcRfAmt.add(rfdApplyCurrencyData.getFuncAmt());
        }
        // END 2022/04/22 D.Mamaril [QC#59333,ADD]

        // START 2022/04/22 D.Mamaril [QC#59333,MOD]
        //dealRcptRmngBalAmt = NFZC301001.subtractBalAmt(rcptT.dealRcptAmt.getValue(), dealApplyAmt, dealApplyAdjAmt, rcptT.dealRfAmt.getValue(), rcptT.dealVoidAmt.getValue());
        //funcRcptRmngBalAmt = NFZC301001.subtractFuncAmt(rcptT.funcRcptAmt.getValue(), funcApplyAmt, funcApplyAdjAmt, rcptT.funcRfAmt.getValue(), rcptT.funcVoidAmt.getValue(), rcptT.funcRvalVarAmt.getValue())
        dealRcptRmngBalAmt = NFZC301001.subtractBalAmt(rcptT.dealRcptAmt.getValue(), dealApplyAmt, dealApplyAdjAmt, dealRfAmt, rcptT.dealVoidAmt.getValue());
        funcRcptRmngBalAmt = NFZC301001.subtractFuncAmt(rcptT.funcRcptAmt.getValue(), funcApplyAmt, funcApplyAdjAmt, funcRfAmt, rcptT.funcVoidAmt.getValue(), rcptT.funcRvalVarAmt.getValue());
        // END 2022/04/22 D.Mamaril [QC#59333,MOD]

        rcptT.dealApplyAmt.setValue(dealApplyAmt);
        rcptT.dealApplyAdjAmt.setValue(dealApplyAdjAmt);
        rcptT.dealRcptRmngBalAmt.setValue(dealRcptRmngBalAmt);
        rcptT.funcApplyAmt.setValue(funcApplyAmt);
        rcptT.funcApplyAdjAmt.setValue(funcApplyAdjAmt);
        rcptT.funcRcptRmngBalAmt.setValue(funcRcptRmngBalAmt);
        // START 2022/04/22 D.Mamaril [QC#59333,ADD]
        rcptT.dealRfAmt.setValue(dealRfAmt);
        rcptT.funcRfAmt.setValue(funcRfAmt);
        // END 2022/04/22 D.Mamaril [QC#59333,ADD]
        if (dealRcptRmngBalAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {
            rcptT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            //Def#2631
            // START 2018/09/20 T.Ogura [QC#28097,MOD]
//            rcptT.arRcptStsCd.setValue(AR_RCPT_STS.APPLIED_PARTIAL_OR_APPLIED);
            // START 2022/03/04 D.Mamaril [QC#59333,MOD]
            //rcptT.arRcptStsCd.setValue(AR_RCPT_STS.APPLIED);
            // START 2022/09/26 D.Mamaril [QC#60602,MOD]
            //if (rcptT.dealRfAmt.getValue().compareTo(rcptT.dealRcptAmt.getValue()) == 0) {
            if (rcptT.dealRfAmt.getValue().compareTo(rcptT.dealRcptAmt.getValue()) == 0
                    && NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue())
                    && NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND.equals(param.arAdjTrxTpCd.getValue())
                    && NFCConst.CST_AR_ADJ_TP_CD_A_OR_R_REFUND.equals(param.arAdjTpCd.getValue())) {
            // END 2022/09/26 D.Mamaril [QC#60602,MOD]
               rcptT.arRcptStsCd.setValue(AR_RCPT_STS.REFUND);
            } else {
               rcptT.arRcptStsCd.setValue(AR_RCPT_STS.APPLIED);
            }
            // END 2022/03/04 D.Mamaril [QC#59333,MOD]
            // END   2018/09/20 T.Ogura [QC#28097,MOD]
        } else {
            if (dealRcptRmngBalAmt.compareTo(rcptT.dealRcptAmt.getValue()) == 0 && dealApplyAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && dealApplyAdjAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {
                rcptT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
                //Def#2631
                // START 2018/09/20 T.Ogura [QC#28097,MOD]
//                rcptT.arRcptStsCd.setValue(AR_RCPT_STS.OPEN);
                rcptT.arRcptStsCd.setValue(AR_RCPT_STS.UNAPPLIED);
                // END   2018/09/20 T.Ogura [QC#28097,MOD]
            } else {
                rcptT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
                //Def#2631
                // START 2018/09/20 T.Ogura [QC#28097,MOD]
//                rcptT.arRcptStsCd.setValue(AR_RCPT_STS.APPLIED_PARTIAL_OR_APPLIED);
                rcptT.arRcptStsCd.setValue(AR_RCPT_STS.PARTIAL_APPLIED);
                // END   2018/09/20 T.Ogura [QC#28097,MOD]
            }
        }
        rcptT.cashAppDt.setValue(param.batDt.getValue());
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(rcptT.payerCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue())) {

            rcptT.payerCustCd.setValue(param.payerCustCd.getValue());
        }

        debugLog("editArRcptApply : end");
        return rcptT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return rcptT AR_RCPTTMsg
     */
    private AR_RCPTTMsg findByKeyForUpdateArRcpt(S21ApiMessageMap msgMap) {

        debugLog("findByKeyForUpdateArRcpt : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_RCPTTMsg rcptT = new AR_RCPTTMsg();
        rcptT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        rcptT.rcptNum.setValue(param.rcptNum.getValue());
        rcptT = (AR_RCPTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(rcptT);
        if (rcptT == null) {

            msgMap.addXxMsgId(NFZC301001.NFCM0595E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("findByKeyForUpdateArRcpt : end");
            return rcptT;
        }
        if (!param.rcptHdrLastUpdTs.getValue().equals(rcptT.ezUpTime.getValue()) || !param.rcptHdrTz.getValue().equals(rcptT.ezUpTimeZone.getValue())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0596E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_EXCLERR);
        }
        debugLog("findByKeyForUpdateArRcpt : end");
        return rcptT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param rcptT AR_RCPTTMsg
     */
    private void updArRcpt(S21ApiMessageMap msgMap, AR_RCPTTMsg rcptT) {

        debugLog("updArRcpt : start");

        // 2010/05/20 DefID 6144/6452/6444 start
        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_WRK.equals(param.xxProcCaseTpCd.getValue())) {
            boolean applyFlg = false;
            if (rcptT.dealRcptRmngBalAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal tmpDealRcptRmngBalAmt = rcptT.dealRcptRmngBalAmt.getValue();
                rcptT.dealApplyAdjAmt.setValue(rcptT.dealApplyAdjAmt.getValue().add(tmpDealRcptRmngBalAmt));
                rcptT.dealRcptRmngBalAmt.setValue(BigDecimal.ZERO);
                applyFlg = true;
            }
            if (rcptT.funcRcptRmngBalAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal tmpFuncRcptRmngBalAmt = rcptT.funcRcptRmngBalAmt.getValue();
                rcptT.funcApplyAdjAmt.setValue(rcptT.funcApplyAdjAmt.getValue().add(tmpFuncRcptRmngBalAmt));
                rcptT.funcRcptRmngBalAmt.setValue(BigDecimal.ZERO);
                applyFlg = true;
            }
            if (applyFlg && rcptT.dealRcptRmngBalAmt.getValue().compareTo(BigDecimal.ZERO) == 0 && rcptT.funcRcptRmngBalAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
                rcptT.arCashApplyStsCd.setValue(AR_CASH_APPLY_STS_CD_A);

                //Def#2631
                // START 2018/09/20 T.Ogura [QC#28097,MOD]
//                rcptT.arRcptStsCd.setValue(AR_RCPT_STS.APPLIED_PARTIAL_OR_APPLIED);
                rcptT.arRcptStsCd.setValue(AR_RCPT_STS.APPLIED);
                // END   2018/09/20 T.Ogura [QC#28097,MOD]
            }
        }
        // 2010/05/20 DefID 6144/6452/6444 end
        S21ApiTBLAccessor.update(rcptT);
        debugLog("AR_RCPT : UPDATE RETURN CODE : " + rcptT.getReturnCode());
        debugLog("updArRcpt : end");
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
