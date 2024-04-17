/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.AR_RCPTTMsg;
import business.db.AR_TRX_BALTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
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
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 1602
 * 12/07/2009   Canon           Y.Kondo         Create          DefID 2343
 * 04/27/2010   Canon           H.Tokunaga      Update          DEFID 5807
 * 05/06/2010   Canon           H.Tokunaga      Update          DefID 6144
 * 11/20/2010   Canon           T.Tanaka        Update          DefID M125
 * 11/24/2010   Canon           I.Kondo         Update          M125
 * 02/16/2011   Canon           T.Tanaka        Update          DefID:1595 Cash App Func Amount
 * 10/25/2011   Canon           T.Tanaka        Update          ITG: 367301 void check
 * 10/08/2015   Fujitsu         T.Tanaka        Update          set AR_AUTO_CASH_PURGE_OFS_FLG
 * 04/14/2016   CSAI            K.Uramori       Update          QC#7008 Set AR_TRX_BAL.BILL_TO_CUST_ACCT_CD
 * 2016/09/28   Hitachi         K.Kojima        Update          QC#11021
 * 04/15/2021   CITS            G.Delgado       Update          QC#58689
 * 03/04/2022   CITS            D.Mamaril       Update          QC#59333
 * </pre>
 */
public class NFCCashApplyUpdateRcptArTrxBal extends S21ApiCommonBase {

    /** BigDecimal : NAGATE. */
    private static final BigDecimal NEGATE = BigDecimal.valueOf(-1);

    /**
     */
    public NFCCashApplyUpdateRcptArTrxBal() {
        super();
    }

    /**
     * <pre>
     * </pre>
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateCashApplyRcptArTrxBal(msgMap, onBatchType);
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
    protected void updateCashApplyRcptArTrxBal(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("updateCashApplyRcptArTrxBal : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_XX_PROC_TP_CD_NON.equals(param.xxTrxRcptProcTpCd.getValue())
                && NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(param.xxHdrNumGetFlg.getValue())) {
            setRcptArTrxBal(msgMap);
        }
        debugLog("updateCashApplyRcptArTrxBal : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void setRcptArTrxBal(S21ApiMessageMap msgMap) {

        debugLog("setRcptArTrxBal : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        NFCCashApplyUpdateInvArTrxBal trxBal = new NFCCashApplyUpdateInvArTrxBal();

        if (NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT.equals(param.xxProcCaseTpCd.getValue())) {

            AR_TRX_BALTMsg trxT = findByKeyForUpdateArTrxBal(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxT = editRcptArTrxBalNew(msgMap, trxT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxBal.updArTrxBal(msgMap, trxT);
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CHNG_RCPT.equals(param.xxProcCaseTpCd.getValue())) {

            AR_TRX_BALTMsg trxT = findByKeyForUpdateArTrxBal(msgMap);
            if (NFZC301001.getRtnCdNFZC301002(msgMap).equals(NFCConst.CST_NFZC301001_RTN_CD_UNPROC)) {

                if (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.payerCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue())) {

                    trxT = editRcptArTrxBalChg(msgMap, trxT);

                    trxBal.updArTrxBal(msgMap, trxT);
                }
            }
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_RCPT.equals(param.xxProcCaseTpCd.getValue())) {

            AR_TRX_BALTMsg trxT = findByKeyForUpdateArTrxBal(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxT = editRcptArTrxBalVoid(msgMap, trxT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxBal.updArTrxBal(msgMap, trxT);
            }
        } else {

            AR_TRX_BALTMsg trxT = findByKeyForUpdateArTrxBal(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxT = editRcptArTrxBalApply(msgMap, trxT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxBal.updArTrxBal(msgMap, trxT);
            }
        }
        debugLog("setRcptArTrxBal : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editRcptArTrxBalNew(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editRcptArTrxBalNew : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal dealOrigGrsAmt = trxT.dealOrigGrsAmt.getValue().add(param.xxTotRcptApplyAmt.getValue());

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcOrigGrsAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.xxTotRcptApplyAmt.getValue(), param.exchRate.getValue());
        funcOrigGrsAmt = funcOrigGrsAmt.add(trxT.funcOrigGrsAmt.getValue());

        if (null == funcOrigGrsAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editRcptArTrxBalNew : end");
            return trxT;
        }

        BigDecimal dealRmngBalGrsAmt = NFZC301001.subtractBalAmt(dealOrigGrsAmt, trxT.dealApplyGrsAmt.getValue(), trxT.dealApplyCashDiscAmt.getValue(), trxT.dealApplyCrAmt.getValue(), trxT.dealApplyAdjAmt.getValue());
        BigDecimal funcRmngBalGrsAmt = NFZC301001.subtractFuncAmt(funcOrigGrsAmt, trxT.funcApplyGrsAmt.getValue(), trxT.funcApplyCashDiscAmt.getValue(), trxT.funcApplyCrAmt.getValue(), trxT.funcApplyAdjAmt.getValue(), trxT.funcRvalVarAmt
                .getValue());
        trxT.dealOrigGrsAmt.setValue(dealOrigGrsAmt);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);
        trxT.exchRate.setValue(param.exchRate.getValue());
        trxT.funcCcyCd.setValue(param.funcCcyCd.getValue());
        trxT.funcOrigGrsAmt.setValue(funcOrigGrsAmt);
        trxT.funcRmngBalGrsAmt.setValue(funcRmngBalGrsAmt);
        trxT.cashAppDt.setValue(param.batDt.getValue());

        // START 2016/09/28 K.Kojima [QC#11021,MOD]
        // if
        // (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.billToCustCd.getValue())
        // &&
        // !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue()))
        // {
        // trxT.billToCustCd.setValue(param.payerCustCd.getValue());
        // }
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.billToCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.rcptNum.getValue())) {
            String billToCustCd = getBillToCustCd(param.glblCmpyCd.getValue(), param.rcptNum.getValue());
            if (billToCustCd != null) {
                trxT.billToCustCd.setValue(billToCustCd);
            }
        }
        // END 2016/09/28 K.Kojima [QC#11021,MOD]

        // START 2021/04/15 G.Delgado [QC#58689,ADD]
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.sellToCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.rcptNum.getValue())) {
            String sellToCustCd = getRcptPayerCustCd(param.glblCmpyCd.getValue(), param.rcptNum.getValue());
            if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                trxT.sellToCustCd.setValue(sellToCustCd);
            }
        }
        // END 2021/04/15 G.Delgado [QC#58689,ADD]

        if (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.payerCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue())) {

            trxT.payerCustCd.setValue(param.payerCustCd.getValue());
        }

        trxT.arAutoPurgeOfsFlg.setValue(ZYPConstant.FLG_OFF_N);

        //---- start 2016/04/14 QC#7008 Set payer customer code to BILL_TO_CUST_ACCT_CD
        ZYPEZDItemValueSetter.setValue(trxT.billToCustAcctCd, param.payerCustCd.getValue());
        //---- end 2016/04/14

        param.xxHdrTrxRmngGrsAmt.setValue(trxT.dealRmngBalGrsAmt.getValue());
        param.xxHdrTrxBillToCustCd.setValue(trxT.billToCustCd.getValue());
        // START 2021/04/15 G.Delgado [QC#58689,ADD]
        param.xxHdrTrxSellToCustCd.setValue(trxT.sellToCustCd.getValue());
        // END 2021/04/15 G.Delgado [QC#58689,ADD]
        param.xxHdrTrxPayerCustCd.setValue(trxT.payerCustCd.getValue());

        debugLog("editRcptArTrxBalNew : end");
        return trxT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editRcptArTrxBalChg(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editRcptArTrxBalChg : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        // START 2016/09/28 K.Kojima [QC#11021,DEL]
        // trxT.billToCustCd.setValue(param.payerCustCd.getValue());
        // END 2016/09/28 K.Kojima [QC#11021,DEL]
        trxT.payerCustCd.setValue(param.payerCustCd.getValue());
        trxT.cashAppDt.setValue(param.batDt.getValue());
        param.xxHdrTrxBillToCustCd.setValue(trxT.billToCustCd.getValue());
        param.xxHdrTrxPayerCustCd.setValue(trxT.payerCustCd.getValue());
        debugLog("editRcptArTrxBalChg : end");
        return trxT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editRcptArTrxBalVoid(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editRcptArTrxBalVoid : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal dealRcptVoidAmt = trxT.dealRcptVoidAmt.getValue().add(param.xxTotVoidAmt.getValue());

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcRcptVoidAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.xxTotVoidAmt.getValue(), param.exchRate.getValue());
        funcRcptVoidAmt = funcRcptVoidAmt.add(trxT.funcRcptVoidAmt.getValue());

        if (null == funcRcptVoidAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editRcptArTrxBalVoid : end");
            return trxT;
        }

        BigDecimal dealRmngBalGrsAmt = NFZC301001.subtractFuncAmt(trxT.dealOrigGrsAmt.getValue(), trxT.dealApplyGrsAmt.getValue(), trxT.dealApplyCashDiscAmt.getValue(), trxT.dealApplyCrAmt.getValue(), trxT.dealApplyAdjAmt.getValue(),
                dealRcptVoidAmt);
        BigDecimal funcRmngBalGrsAmt = NFZC301001.subtractFuncVoidAmt(trxT.funcOrigGrsAmt.getValue(), trxT.funcApplyGrsAmt.getValue(), trxT.funcApplyCashDiscAmt.getValue(), trxT.funcApplyCrAmt.getValue(), trxT.funcApplyAdjAmt.getValue(),
                trxT.funcRvalVarAmt.getValue(), funcRcptVoidAmt);

        trxT.dealRcptVoidAmt.setValue(dealRcptVoidAmt);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);
        trxT.funcRcptVoidAmt.setValue(funcRcptVoidAmt);
        trxT.funcRmngBalGrsAmt.setValue(funcRmngBalGrsAmt);
        trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_VOID);
        trxT.cashAppDt.setValue(param.batDt.getValue());

        // START 2016/09/28 K.Kojima [QC#11021,MOD]
        // if
        // (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.billToCustCd.getValue())
        // &&
        // !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue()))
        // {
        // trxT.billToCustCd.setValue(param.payerCustCd.getValue());
        // }
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.billToCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.rcptNum.getValue())) {
            String billToCustCd = getBillToCustCd(param.glblCmpyCd.getValue(), param.rcptNum.getValue());
            if (billToCustCd != null) {
                trxT.billToCustCd.setValue(billToCustCd);
            }
        }
        // END 2016/09/28 K.Kojima [QC#11021,MOD]
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.payerCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue())) {

            trxT.payerCustCd.setValue(param.payerCustCd.getValue());
        }
        param.xxHdrTrxRmngGrsAmt.setValue(trxT.dealRmngBalGrsAmt.getValue());
        param.xxHdrTrxBillToCustCd.setValue(trxT.billToCustCd.getValue());
        param.xxHdrTrxPayerCustCd.setValue(trxT.payerCustCd.getValue());
        debugLog("editRcptArTrxBalVoid : end");
        return trxT;
    }

    /**
     * <pre>
     * Edit Receipt AR_TRX_BAL CashApply
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editRcptArTrxBalApply(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editRcptArTrxBalApply : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal dealApplyGrsAmt = trxT.dealApplyGrsAmt.getValue().add(param.xxTotInvApplyAmt.getValue());
        BigDecimal dealApplyAdjAmt = trxT.dealApplyAdjAmt.getValue().add(param.xxTotDedctApplyAmt.getValue().add(param.xxTotAdjAmt.getValue().add(param.xxTotRfAmt.getValue())));
        BigDecimal dealApplyAdjAmt2 = param.xxTotDedctApplyAmt.getValue().add(param.xxTotAdjAmt.getValue().add(param.xxTotRfAmt.getValue()));

        // The Diff judges ADJ_TP by a plus or a minus
        if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.xxDtlDiffAmt.getValue()) > 0) {
            debugLog("xxDtlDiffAmt < 0");
            debugLog("editRcptArTrxBalApply dealApplyGrsAmt  : " + dealApplyGrsAmt);
            debugLog("editRcptArTrxBalApply before dealApplyAdjAmt  : " + dealApplyAdjAmt);
            debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            // ADJ_TP : C(MISCELLANEOUS_INCOME)
            // Add a DiffAmount that reversed a sign
            dealApplyAdjAmt = dealApplyAdjAmt.add(param.xxDtlDiffAmt.getValue().multiply(NEGATE));
            debugLog("editRcptArTrxBalApply after dealApplyAdjAmt  : " + dealApplyAdjAmt);

            // 2010/04/27 H.Tokunaga Add Start defid 6144
        } else {
            debugLog("xxDtlDiffAmt >= 0");
            debugLog("editRcptArTrxBalApply dealApplyGrsAmt  : " + dealApplyGrsAmt);
            debugLog("editRcptArTrxBalApply before dealApplyAdjAmt  : " + dealApplyAdjAmt);
            debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            if (!NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(param.xxProcCaseTpCd.getValue())) {
                dealApplyAdjAmt = dealApplyAdjAmt.add(param.xxDtlDiffAmt.getValue().multiply(NEGATE));
            } else {
                dealApplyAdjAmt = dealApplyAdjAmt.add(param.xxDtlDiffAmt.getValue());
            }
            debugLog("editRcptArTrxBalApply after dealApplyAdjAmt  : " + dealApplyAdjAmt);
            // 2010/04/27 H.Tokunaga Add end defid 6144
        }

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcApplyGrsAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.xxTotInvApplyAmt.getValue(), param.exchRate.getValue());
        funcApplyGrsAmt = funcApplyGrsAmt.add(trxT.funcApplyGrsAmt.getValue());

        if (null == funcApplyGrsAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editRcptArTrxBalApply : end");
            return trxT;
        }

        // START 2022/03/04 D.Mamaril [QC#59333,ADD]
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue()) && NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND.equals(param.arAdjTrxTpCd.getValue())
                && NFCConst.CST_AR_ADJ_TP_CD_A_OR_R_REFUND.equals(param.arAdjTpCd.getValue())) {
            dealApplyAdjAmt = dealApplyAdjAmt.add(param.dealApplyAdjAmt.getValue());
            dealApplyAdjAmt2 = dealApplyAdjAmt2.add(param.dealApplyAdjAmt.getValue());
        }
        // END 2022/03/04 D.Mamaril [QC#59333,ADD]

        BigDecimal funcApplyAdjAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealApplyAdjAmt2, param.exchRate.getValue());
        funcApplyAdjAmt = funcApplyAdjAmt.add(trxT.funcApplyAdjAmt.getValue());

        if (null == dealApplyAdjAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editRcptArTrxBalApply : end");
            return trxT;
        }

        BigDecimal dealRmngBalGrsAmt = NFZC301001.subtractBalVoidAmt(trxT.dealOrigGrsAmt.getValue(), dealApplyGrsAmt, trxT.dealApplyCashDiscAmt.getValue(), trxT.dealApplyCrAmt.getValue(), dealApplyAdjAmt, trxT.dealRcptVoidAmt.getValue());
        BigDecimal funcRmngBalGrsAmt = NFZC301001.subtractFuncVoidAmt(trxT.funcOrigGrsAmt.getValue(), funcApplyGrsAmt, trxT.funcApplyCashDiscAmt.getValue(), trxT.funcApplyCrAmt.getValue(), funcApplyAdjAmt, trxT.funcRcptVoidAmt.getValue(),
                trxT.funcRvalVarAmt.getValue());

        trxT.dealApplyGrsAmt.setValue(dealApplyGrsAmt);
        debugLog("RcpttrxT set dealApplyAdjAmt : " + dealApplyAdjAmt);
        trxT.dealApplyAdjAmt.setValue(dealApplyAdjAmt);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);
        trxT.funcApplyGrsAmt.setValue(funcApplyGrsAmt);
        trxT.funcApplyAdjAmt.setValue(funcApplyAdjAmt);
        trxT.funcRmngBalGrsAmt.setValue(funcRmngBalGrsAmt);

        if (!trxT.arCashApplyStsCd.getValue().equals(AR_CASH_APPLY_STS.VOID)) {

            if (dealRmngBalGrsAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            } else if (trxT.dealOrigGrsAmt.getValue().compareTo(dealRmngBalGrsAmt) == 0 && dealApplyGrsAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && trxT.dealApplyCashDiscAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyCrAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && dealApplyAdjAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            } else {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
            }
        }
        trxT.cashAppDt.setValue(param.batDt.getValue());

        // START 2016/09/28 K.Kojima [QC#11021,MOD]
        // if
        // (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.billToCustCd.getValue())
        // &&
        // !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue()))
        // {
        // trxT.billToCustCd.setValue(param.payerCustCd.getValue());
        // }
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.billToCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.rcptNum.getValue())) {
            String billToCustCd = getBillToCustCd(param.glblCmpyCd.getValue(), param.rcptNum.getValue());
            if (billToCustCd != null) {
            trxT.billToCustCd.setValue(billToCustCd);
            }
        }
        // END 2016/09/28 K.Kojima [QC#11021,MOD]

        // START 2021/04/15 G.Delgado [QC#58689,ADD]
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.sellToCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.rcptNum.getValue())) {
            String sellToCustCd = getRcptPayerCustCd(param.glblCmpyCd.getValue(), param.rcptNum.getValue());
            if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                trxT.sellToCustCd.setValue(sellToCustCd);
            }
        }
        // END 2021/04/15 G.Delgado [QC#58689,ADD]

        if (NFCConst.CST_DB_INIT_VAL_STR.equals(trxT.payerCustCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.payerCustCd.getValue())) {

            trxT.payerCustCd.setValue(param.payerCustCd.getValue());
        }
        param.xxHdrTrxRmngGrsAmt.setValue(trxT.dealRmngBalGrsAmt.getValue());
        param.xxHdrTrxBillToCustCd.setValue(trxT.billToCustCd.getValue());
        // START 2021/04/15 G.Delgado [QC#58689,ADD]
        param.xxHdrTrxSellToCustCd.setValue(trxT.sellToCustCd.getValue());
        // END 2021/04/15 G.Delgado [QC#58689,ADD]
        param.xxHdrTrxPayerCustCd.setValue(trxT.payerCustCd.getValue());
        debugLog("editRcptArTrxBalApply : end");
        return trxT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     */
    protected AR_TRX_BALTMsg findByKeyForUpdateArTrxBal(S21ApiMessageMap msgMap) {

        debugLog("findByKeyForUpdateArTrxBal : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_TRX_BALTMsg trxT = new AR_TRX_BALTMsg();
        trxT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        trxT.arTrxBalPk.setValue(param.rcptTrxBalPk.getValue());
        trxT = (AR_TRX_BALTMsg) S21ApiTBLAccessor.findByKeyForUpdate(trxT);
        if (trxT == null) {

            msgMap.addXxMsgId(NFZC301001.NFCM0553E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("findByKeyForUpdateArTrxBal : end");
            return trxT;
        }
        if (!param.rcptTrxBalLastUpdTs.getValue().equals(trxT.ezUpTime.getValue()) || !param.rcptTrxBalTz.getValue().equals(trxT.ezUpTimeZone.getValue())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0554E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_EXCLERR);
            return trxT;
        }
        debugLog("findByKeyForUpdateArTrxBal : end");
        return trxT;
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }

    // START 2016/09/28 K.Kojima [QC#11021,ADD]
    /**
     * Get BILL_TO_CUST_CD
     * @param glblCmpyCd String
     * @param rcptNum String
     * @return String
     */
    private String getBillToCustCd(String glblCmpyCd, String rcptNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rcptNum", rcptNum);
        S21SsmEZDResult result = NFZC301001Query.getInstance().getBillToCustCd(param);
        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }
    // END 2016/09/28 K.Kojima [QC#11021,ADD]

    // START 2021/04/15 G.Delgado [QC#58689,ADD]
    /**
     * Get payerCustCd of AR receipt
     * @param glblCmpyCd String
     * @param rcptNum String
     * @return String
     */
    private String getRcptPayerCustCd(String glblCmpyCd, String rcptNum) {
        AR_RCPTTMsg rcptT = new AR_RCPTTMsg();
        rcptT.glblCmpyCd.setValue(glblCmpyCd);
        rcptT.rcptNum.setValue(rcptNum);
        rcptT = (AR_RCPTTMsg) S21ApiTBLAccessor.findByKey(rcptT);
        if (rcptT != null) {
            return rcptT.payerCustCd.getValue();
        }
        return null;
    }
    // END 2021/04/15 G.Delgado [QC#58689,ADD]
}
