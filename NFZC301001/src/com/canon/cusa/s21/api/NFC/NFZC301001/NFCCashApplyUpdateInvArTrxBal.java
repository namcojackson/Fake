/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDDBCICarrier;
import business.db.AR_TRX_BALTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/11/2009   Canon           Y.Kondo         Update          DefID 753
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 1602
 * 12/07/2009   Canon           Y.Kondo         Create          DefID 2343
 * 01/27/2010   Canon           H.Tokunaga      Update          ACC case add. it is DED case copy. And, The COA_PROD_CD item is added.
 * 04/07/2010   Canon           H.Tokunaga      Update          DEFID 5470
 * 04/27/2010   Canon           H.Tokunaga      Update          DEFID 5807
 * 05/06/2010   Canon           H.Tokunaga      Update          DefID 6144
 * 05/20/2010   Canon           I.Kondo         Update          DefID 6144/6452
 * 05/20/2010   Canon           I.Kondo         Update          DefID 6641 No.020
 * 09/22/2010   Canon           I.Kondo         Update          DefID 8104 No.398
 * 10/08/2010   Canon           T.Tanaka        Update          R2->R3
 * 11/10/2010   Canon           I.Kondo         Update          DefID:8308
 * 11/15/2010   Canon           I.Kondo         Update          R2->R3
 * 11/19/2010   Canon           T.Tanaka        Update          M125
 * 11/24/2010   Canon           I.Kondo         Update          M125
 * 02/16/2011   Canon           T.Tanaka        Update          DefID:1595 Cash App Func Amount
 * 03/10/2011   Canon           Y.Suga          Update          DefID 1654
 * 04/13/2015   Canon           K.Kimura        Update          For CSA
 * 10/08/2015   Fujitsu         T.Tanaka        Update          set AR_AUTO_CASH_PURGE_OFS_FLG
 * 04/14/2016   CSAI            K.Uramori       Update          QC#7008 Set AR_TRX_BAL.BILL_TO_CUST_ACCT_CD
 * 04/15/2021   CITS            G.Delgado       Update          QC#58689
 * 12/09/2021   CITS            G.Delgado       Update          QC#59328
 * 03/04/2022   CITS            D.Mamaril       Update          QC#59333
 * </pre>
 */
public class NFCCashApplyUpdateInvArTrxBal extends S21ApiCommonBase {

    /** BigDecimal : NAGATE. */
    private static final BigDecimal NEGATE = BigDecimal.valueOf(-1);

    /** AR_CASH_APPLY_STS_CD : Apply */
    private static final String AR_CASH_APPLY_STS_CD_A = "A";

    /** PMT_TERM_CD:000 */
    private static final String PMT_TERM_CD_000 = "000";

    /** PMT_TERM_CASH_DISC_CD:NN */
    private static final String PMT_TERM_CASH_DISC_CD_NN = "NN";

    /**
     */
    public NFCCashApplyUpdateInvArTrxBal() {
        super();
    }

    /**
     * <pre>
     * </pre>
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateCashApplyInvArTrxBal(msgMap, onBatchType);
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
    protected void updateCashApplyInvArTrxBal(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("updateCashApplyInvArTrxBal : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_XX_PROC_TP_CD_NON.equals(param.xxTrxInvProcTpCd.getValue())) {
            setInvArTrxBal(msgMap);
        }
        debugLog("updateCashApplyInvArTrxBal : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void setInvArTrxBal(S21ApiMessageMap msgMap) {

        debugLog("setInvArTrxBal : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(param.xxProcCaseTpCd.getValue())) {

            AR_TRX_BALTMsg trxT = editInvArTrxBalDed(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxT = insInvArTrxBal(msgMap, trxT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                param.xxInvTrxBalPk.setValue(trxT.arTrxBalPk.getValue());
                param.xxInvInvNum.setValue(trxT.arTrxNum.getValue());
            }

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue())) {

            AR_TRX_BALTMsg trxT = findByKeyForUpdateInvArTrxBal(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxT = editInvArTrxBalCancDed(msgMap, trxT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArTrxBal(msgMap, trxT);
            }

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_AUTO_APVL.equals(param.xxProcCaseTpCd.getValue())) {

            AR_TRX_BALTMsg trxT = findByKeyForUpdateInvArTrxBal(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxT = editInvArTrxBalAutoApvl(msgMap, trxT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArTrxBal(msgMap, trxT);
            }

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_APVL_WAIT.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADJ_APVL_WAIT.equals(param.xxProcCaseTpCd.getValue())) {

            AR_TRX_BALTMsg trxT = findByKeyForUpdateInvArTrxBal(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxT = editInvArTrxBalApvlWait(msgMap, trxT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArTrxBal(msgMap, trxT);
            }

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_APVL.equals(param.xxProcCaseTpCd.getValue())) {

            AR_TRX_BALTMsg trxT = findByKeyForUpdateInvArTrxBal(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxT = editInvArTrxBalApvl(msgMap, trxT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArTrxBal(msgMap, trxT);
            }

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CNT_BAL.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_CNT_BAL.equals(param.xxProcCaseTpCd.getValue())) {

            if (NFCConst.CST_XX_INV_NUM_GET_FLG_ON.equals(param.xxInvNumGetFlg.getValue())) {

                AR_TRX_BALTMsg invTrxT = findByKeyForUpdateInvArTrxBal(msgMap);
                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                    invTrxT = editInvArTrxBalOfs(msgMap, invTrxT);
                }
                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                    updArTrxBal(msgMap, invTrxT);
                }
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                AR_TRX_BALTMsg crTrxT = findByKeyForUpdateCrArTrxBal(msgMap);
                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                    crTrxT = editCrArTrxBalOfs(msgMap, crTrxT);
                }
                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                    updArTrxBal(msgMap, crTrxT);
                }
            }
        } else {

            AR_TRX_BALTMsg trxT = findByKeyForUpdateInvArTrxBal(msgMap);
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                trxT = editInvArTrxBalApply(msgMap, trxT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                updArTrxBal(msgMap, trxT);
            }
        }
        debugLog("setInvArTrxBal : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editInvArTrxBalDed(S21ApiMessageMap msgMap) {

        debugLog("editInvArTrxBalDed : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealOrigGrsAmt = null;
        BigDecimal dealRmngBalGrsAmt = null;
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(param.xxProcCaseTpCd.getValue())) {

            dealOrigGrsAmt = param.dealApplyAdjAmt.getValue();
            dealRmngBalGrsAmt = param.dealApplyAdjAmt.getValue();
        } else {

            dealOrigGrsAmt = param.dealApplyAdjAmt.getValue().multiply(NEGATE);
            dealRmngBalGrsAmt = param.dealApplyAdjAmt.getValue().multiply(NEGATE);
        }
        AR_TRX_BALTMsg trxT = new AR_TRX_BALTMsg();

        trxT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());

        trxT.arTrxTpCd.setValue(NFCConst.CST_AR_ADJ_TRX_TP_CD_DEDUCTION);
        // 01/26/2010 OnAccount Add
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(param.arAdjTrxTpCd.getValue())) {
            if (NFCConst.CST_AR_TRX_TP_CD_ACCOUNT.equals(param.arTrxTpCd.getValue())) {
                trxT.arTrxTpCd.setValue(NFCConst.CST_AR_TRX_TP_CD_ACCOUNT);
            }
        } else {
            if (NFCConst.CST_AR_ADJ_TRX_TP_CD_ACCOUNT.equals(param.arAdjTrxTpCd.getValue())) {
                trxT.arTrxTpCd.setValue(NFCConst.CST_AR_TRX_TP_CD_ACCOUNT);
            }
        }

        trxT.attAdjNum.setValue(param.xxArAdjNum.getValue());
        trxT.dealCcyCd.setValue(param.dealCcyCd.getValue());
        trxT.dealOrigGrsAmt.setValue(dealOrigGrsAmt);
        trxT.dealApplyGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealApplyCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealApplyCrAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealApplyAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealApplyAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);
        trxT.dealNetSlsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealFrtAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealTaxAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealInvDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealPrmoDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.dealRcptVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.exchRate.setValue(param.exchRate.getValue());
        trxT.funcCcyCd.setValue(param.funcCcyCd.getValue());

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcOrigGrsAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealOrigGrsAmt, param.exchRate.getValue());

        if (null == funcOrigGrsAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalDed : end");
            return trxT;
        } else {
            trxT.funcOrigGrsAmt.setValue(funcOrigGrsAmt);
        }

        trxT.funcApplyGrsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcApplyCashDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcApplyCrAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcApplyAdjAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcApplyAdjRsvdAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcRvalVarAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);

        trxT.funcRmngBalGrsAmt.setValue(funcOrigGrsAmt);

        trxT.funcNetSlsAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcFrtAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcTaxAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcInvDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcPrmoDiscAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.funcRcptVoidAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.cashDiscPct.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
        trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
        trxT.arTrxDt.setValue(param.cashAppGlDt.getValue());
        // START 2021/12/09 G.Delgado [QC#59328,MOD]
        // trxT.invDueDt.setValue(param.cashAppGlDt.getValue());
        trxT.invDueDt.setValue(param.xxHdrRcptDt.getValue());
        // END 2021/12/09 G.Delgado [QC#59328,MOD]
        trxT.glDt.setValue(param.cashAppGlDt.getValue());
        trxT.cashAppDt.setValue(param.batDt.getValue());
        trxT.billToCustCd.setValue(param.xxHdrTrxBillToCustCd.getValue());
        // START 2021/04/15 G.Delgado [QC#58689,MOD]
        // trxT.sellToCustCd.setValue(param.xxHdrTrxBillToCustCd.getValue());
        trxT.sellToCustCd.setValue(param.xxHdrTrxSellToCustCd.getValue());
        // END 2021/04/15 G.Delgado [QC#58689,MOD]
        trxT.pmtTermCd.setValue(PMT_TERM_CD_000);
        trxT.pmtTermCashDiscCd.setValue(PMT_TERM_CASH_DISC_CD_NN);
        trxT.payerCustCd.setValue(param.xxHdrTrxPayerCustCd.getValue());
        trxT.tocCd.setValue(param.tocCd.getValue());
        trxT.grpInvNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        trxT.cpoOrdNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        trxT.custIssPoNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);
        trxT.arCustRefNum.setValue(param.arCustRefNum.getValue());
        trxT.coaProdCd.setValue(param.coaProdCd.getValue());

        if (ZYPCommonFunc.hasValue(param.exptFlg.getValue())) {
            trxT.exptFlg.setValue(param.exptFlg.getValue());
        } else {
            trxT.exptFlg.setValue(NFCConst.CST_FLAG_OFF);
        }

        trxT.arAutoPurgeOfsFlg.setValue(ZYPConstant.FLG_OFF_N);

        //---- start 2016/04/14 QC#7008 Set payer customer code to BILL_TO_CUST_ACCT_CD
        ZYPEZDItemValueSetter.setValue(trxT.billToCustAcctCd, param.payerCustCd.getValue());
        //---- end 2016/04/14

        debugLog("editInvArTrxBalDed : end");
        return trxT;
    }

    /**
     * <pre>
     * edit Invoice AR_TRX_BAL Cancel Deduction
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editInvArTrxBalCancDed(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editInvArTrxBalCancDed : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealRcptVoidAmt = trxT.dealRcptVoidAmt.getValue().add(param.dealApplyAdjAmt.getValue());

        BigDecimal dealRmngBalGrsAmt = NFZC301001.subtractBalVoidAmt(trxT.dealOrigGrsAmt.getValue(), trxT.dealApplyGrsAmt.getValue(), trxT.dealApplyCashDiscAmt.getValue(), trxT.dealApplyCrAmt.getValue(), trxT.dealApplyAdjAmt.getValue(),
                dealRcptVoidAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcRcptVoidAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAdjAmt.getValue(), param.exchRate.getValue());
        funcRcptVoidAmt = funcRcptVoidAmt.add(trxT.funcRcptVoidAmt.getValue());

        if (null == funcRcptVoidAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalCancDed : end");
            return trxT;
        } else {
            trxT.funcRcptVoidAmt.setValue(funcRcptVoidAmt);

            BigDecimal funcRmngBalGrsAmt = NFZC301001.subtractFuncVoidAmt(trxT.funcOrigGrsAmt.getValue(), trxT.funcApplyGrsAmt.getValue(), trxT.funcApplyCashDiscAmt.getValue(), trxT.funcApplyCrAmt.getValue(), trxT.funcApplyAdjAmt
                    .getValue(), funcRcptVoidAmt, trxT.funcRvalVarAmt.getValue());
            trxT.funcRmngBalGrsAmt.setValue(funcRmngBalGrsAmt);
        }

        trxT.dealRcptVoidAmt.setValue(dealRcptVoidAmt);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);

        trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_VOID);
        trxT.cashAppDt.setValue(param.batDt.getValue());

        debugLog("editInvArTrxBalCancDed : end");
        return trxT;
    }

    /**
     * <pre>
     * Edit Invoice AR_TRX_BAL Auto Approval
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editInvArTrxBalAutoApvl(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editInvArTrxBalAutoApvl : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal dealApplyAdjAmt = trxT.dealApplyAdjAmt.getValue().add(param.dealApplyAdjAmt.getValue());

        BigDecimal dealRmngBalGrsAmt = NFZC301001.subtractBalVoidAmt(trxT.dealOrigGrsAmt.getValue(), trxT.dealApplyGrsAmt.getValue(), trxT.dealApplyCashDiscAmt.getValue(), trxT.dealApplyCrAmt.getValue(), dealApplyAdjAmt,
                trxT.dealRcptVoidAmt.getValue());

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcApplyAdjAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAdjAmt.getValue(), param.exchRate.getValue());
        funcApplyAdjAmt = funcApplyAdjAmt.add(trxT.funcApplyAdjAmt.getValue());

        if (null == funcApplyAdjAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalAutoApvl : end");
            return trxT;
        } else {
            trxT.funcApplyAdjAmt.setValue(funcApplyAdjAmt);
            BigDecimal funcRmngBalGrsAmt = NFZC301001.subtractFuncVoidAmt(trxT.funcOrigGrsAmt.getValue(), trxT.funcApplyGrsAmt.getValue(), trxT.funcApplyCashDiscAmt.getValue(), trxT.funcApplyCrAmt.getValue(), funcApplyAdjAmt,
                    trxT.funcRcptVoidAmt.getValue(), trxT.funcRvalVarAmt.getValue());
            trxT.funcRmngBalGrsAmt.setValue(funcRmngBalGrsAmt);
        }

        trxT.dealApplyAdjAmt.setValue(dealApplyAdjAmt);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);

        if (trxT.dealRcptVoidAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

            if (dealRmngBalGrsAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            } else if (trxT.dealOrigGrsAmt.getValue().compareTo(dealRmngBalGrsAmt) == 0 && trxT.dealApplyGrsAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyCashDiscAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && trxT.dealApplyCrAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && dealApplyAdjAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && trxT.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            } else {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
            }
        }
        trxT.cashAppDt.setValue(param.batDt.getValue());

        debugLog("editInvArTrxBalAutoApvl : end");
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
    private AR_TRX_BALTMsg editInvArTrxBalApvlWait(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editInvArTrxBalApvlWait : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealApplyAdjRsvdAmt = trxT.dealApplyAdjRsvdAmt.getValue().add(param.dealApplyAdjRsvdAmt.getValue());

        trxT.dealApplyAdjRsvdAmt.setValue(dealApplyAdjRsvdAmt);

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcApplyAdjRsvdAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAdjRsvdAmt.getValue(), param.exchRate.getValue());
        funcApplyAdjRsvdAmt = funcApplyAdjRsvdAmt.add(trxT.funcApplyAdjRsvdAmt.getValue());

        if (null == funcApplyAdjRsvdAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalApvlWait : end");
            return trxT;
        } else {
            trxT.funcApplyAdjRsvdAmt.setValue(funcApplyAdjRsvdAmt);
        }

        debugLog("editInvArTrxBalApvlWait : end");
        return trxT;
    }

    /**
     * <pre>
     * Edit Invoice AR_TRX_BAL Approval
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editInvArTrxBalApvl(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editInvArTrxBalApvl : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal dealApplyAdjAmt = trxT.dealApplyAdjAmt.getValue().add(param.dealApplyAdjAmt.getValue());

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcApplyAdjAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAdjAmt.getValue(), param.exchRate.getValue());
        funcApplyAdjAmt = funcApplyAdjAmt.add(trxT.funcApplyAdjAmt.getValue());
        
        if (null == funcApplyAdjAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalApvl : end");
            return trxT;
        }

        BigDecimal dealApplyAdjRsvdAmt = trxT.dealApplyAdjRsvdAmt.getValue().add(param.dealApplyAdjRsvdAmt.getValue());

        BigDecimal funcApplyAdjRsvdAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAdjRsvdAmt.getValue(), param.exchRate.getValue());
        funcApplyAdjRsvdAmt = funcApplyAdjRsvdAmt.add(trxT.funcApplyAdjRsvdAmt.getValue());
        
        if (null == funcApplyAdjRsvdAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalApvl : end");
            return trxT;
        }

        BigDecimal dealRmngBalGrsAmt = NFZC301001.subtractBalVoidAmt(trxT.dealOrigGrsAmt.getValue(), trxT.dealApplyGrsAmt.getValue(), trxT.dealApplyCashDiscAmt.getValue(), trxT.dealApplyCrAmt.getValue(), dealApplyAdjAmt,
                trxT.dealRcptVoidAmt.getValue());
        BigDecimal funcRmngBalGrsAmt = NFZC301001.subtractFuncVoidAmt(trxT.funcOrigGrsAmt.getValue(), trxT.funcApplyGrsAmt.getValue(), trxT.funcApplyCashDiscAmt.getValue(), trxT.funcApplyCrAmt.getValue(), funcApplyAdjAmt,
                trxT.funcRcptVoidAmt.getValue(), trxT.funcRvalVarAmt.getValue());

        trxT.dealApplyAdjAmt.setValue(dealApplyAdjAmt);
        trxT.dealApplyAdjRsvdAmt.setValue(dealApplyAdjRsvdAmt);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);
        trxT.funcApplyAdjAmt.setValue(funcApplyAdjAmt);
        trxT.funcApplyAdjRsvdAmt.setValue(funcApplyAdjRsvdAmt);
        trxT.funcRmngBalGrsAmt.setValue(funcRmngBalGrsAmt);
        if (trxT.dealRcptVoidAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

            if (dealRmngBalGrsAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            } else if (trxT.dealOrigGrsAmt.getValue().compareTo(dealRmngBalGrsAmt) == 0 && trxT.dealApplyGrsAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyCashDiscAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && trxT.dealApplyCrAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && dealApplyAdjAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && dealApplyAdjRsvdAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            } else {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
            }
        }
        trxT.cashAppDt.setValue(param.batDt.getValue());

        debugLog("editInvArTrxBalApvl : end");
        return trxT;
    }

    /**
     * <pre>
     * Edit Invoice AR_TRX_BAL Offset
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editInvArTrxBalOfs(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editInvArTrxBalOfs : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal dealApplyCrAmt = trxT.dealApplyCrAmt.getValue().add(param.xxInvTotCrAmt.getValue().multiply(NEGATE));

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcApplyCrAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.xxInvTotCrAmt.getValue().multiply(NEGATE), param.exchRate.getValue());
        funcApplyCrAmt = funcApplyCrAmt.add(trxT.funcApplyCrAmt.getValue());

        if (null == funcApplyCrAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalOfs : end");
            return trxT;
        }

        BigDecimal dealRmngBalGrsAmt = NFZC301001.subtractBalVoidAmt(trxT.dealOrigGrsAmt.getValue(), trxT.dealApplyGrsAmt.getValue(), trxT.dealApplyCashDiscAmt.getValue(), dealApplyCrAmt, trxT.dealApplyAdjAmt.getValue(),
                trxT.dealRcptVoidAmt.getValue());
        BigDecimal funcRmngBalGrsAmt = NFZC301001.subtractFuncVoidAmt(trxT.funcOrigGrsAmt.getValue(), trxT.funcApplyGrsAmt.getValue(), trxT.funcApplyCashDiscAmt.getValue(), funcApplyCrAmt, trxT.funcApplyAdjAmt.getValue(),
                trxT.funcRcptVoidAmt.getValue(), trxT.funcRvalVarAmt.getValue());

        trxT.dealApplyCrAmt.setValue(dealApplyCrAmt);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);
        trxT.funcApplyCrAmt.setValue(funcApplyCrAmt);
        trxT.funcRmngBalGrsAmt.setValue(funcRmngBalGrsAmt);
        if (trxT.dealRcptVoidAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

            if (dealRmngBalGrsAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            } else if (trxT.dealOrigGrsAmt.getValue().compareTo(dealRmngBalGrsAmt) == 0 && trxT.dealApplyGrsAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyCashDiscAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && dealApplyCrAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyAdjAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && trxT.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            } else {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
            }
        }
        trxT.cashAppDt.setValue(param.batDt.getValue());

        debugLog("editInvArTrxBalOfs : end");
        return trxT;
    }

    /**
     * <pre>
     * edit Credit AR_TRX_BAL Offset
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editCrArTrxBalOfs(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editCrArTrxBalOfs : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal dealApplyCrAmt = trxT.dealApplyCrAmt.getValue().add(param.dealApplyAmt.getValue());

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcApplyCrAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAmt.getValue(), param.exchRate.getValue());
        funcApplyCrAmt = funcApplyCrAmt.add(trxT.funcApplyCrAmt.getValue());

        if (null == funcApplyCrAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editCrArTrxBalOfs : end");
            return trxT;
        }

        BigDecimal dealRmngBalGrsAmt = NFZC301001.subtractBalVoidAmt(trxT.dealOrigGrsAmt.getValue(), trxT.dealApplyGrsAmt.getValue(), trxT.dealApplyCashDiscAmt.getValue(), dealApplyCrAmt, trxT.dealApplyAdjAmt.getValue(),
                trxT.dealRcptVoidAmt.getValue());
        BigDecimal funcRmngBalGrsAmt = NFZC301001.subtractFuncVoidAmt(trxT.funcOrigGrsAmt.getValue(), trxT.funcApplyGrsAmt.getValue(), trxT.funcApplyCashDiscAmt.getValue(), funcApplyCrAmt, trxT.funcApplyAdjAmt.getValue(),
                trxT.funcRcptVoidAmt.getValue(), trxT.funcRvalVarAmt.getValue());

        trxT.dealApplyCrAmt.setValue(dealApplyCrAmt);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);
        trxT.funcApplyCrAmt.setValue(funcApplyCrAmt);
        trxT.funcRmngBalGrsAmt.setValue(funcRmngBalGrsAmt);
        if (trxT.dealRcptVoidAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

            if (dealRmngBalGrsAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            } else if (trxT.dealOrigGrsAmt.getValue().compareTo(dealRmngBalGrsAmt) == 0 && trxT.dealApplyGrsAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyCashDiscAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && dealApplyCrAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyAdjAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && trxT.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            } else {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
            }
        }
        trxT.cashAppDt.setValue(param.batDt.getValue());

        debugLog("editCrArTrxBalOfs : end");
        return trxT;
    }

    /**
     * <pre>
     * Edit Invoice AR_TRX_BAL Apply
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg editInvArTrxBalApply(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("editInvArTrxBalApply : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal dealApplyCashDiscAmt = null;
        BigDecimal cashDiscPct = null;

        BigDecimal dealApplyGrsAmt = trxT.dealApplyGrsAmt.getValue().add(param.dealApplyAmt.getValue());
        BigDecimal dealApplyAdjAmt = trxT.dealApplyAdjAmt.getValue().add(param.dealApplyAdjAmt.getValue());

        BigDecimal dealApplyCashDiscAmt2 = null;

        if (param.dealCashDiscAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

            if (NFCConst.CST_FLAG_ON.equals(param.xxDtlDiscFlg.getValue())) {

                dealApplyCashDiscAmt = trxT.dealApplyCashDiscAmt.getValue().add(param.xxDtlDiscAmt.getValue());
                dealApplyCashDiscAmt2 = param.xxDtlDiscAmt.getValue();

                if (NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY.equals(param.xxCashApplyStsCd.getValue())) {

                    cashDiscPct = param.xxDtlDiscPct.getValue();
                } else {

                    cashDiscPct = trxT.cashDiscPct.getValue();
                }
            } else {

                dealApplyCashDiscAmt = trxT.dealApplyCashDiscAmt.getValue();
                cashDiscPct = trxT.cashDiscPct.getValue();
            }
        } else {
            dealApplyCashDiscAmt = trxT.dealApplyCashDiscAmt.getValue().add(param.dealCashDiscAmt.getValue());
            cashDiscPct = param.cashDiscPct.getValue();
            dealApplyCashDiscAmt2 = param.dealCashDiscAmt.getValue();
        }

        NFCCurrencyConversion afxc3070 = new NFCCurrencyConversion();
        BigDecimal funcApplyGrsAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAmt.getValue(), param.exchRate.getValue());
        funcApplyGrsAmt = funcApplyGrsAmt.add(trxT.funcApplyGrsAmt.getValue());

        if (null == funcApplyGrsAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalApply : end");
            return trxT;
        }

        BigDecimal funcApplyCashDiscAmt = null;
        if (dealApplyCashDiscAmt2 == null) {
            funcApplyCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealApplyCashDiscAmt, param.exchRate.getValue());
        } else {
            funcApplyCashDiscAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealApplyCashDiscAmt2, param.exchRate.getValue());
            funcApplyCashDiscAmt = funcApplyCashDiscAmt.add(trxT.funcApplyCashDiscAmt.getValue());
        }

        if (null == funcApplyCashDiscAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalApply : end");
            return trxT;
        }

        BigDecimal funcApplyAdjAmt = afxc3070.getFuncAmtByRate(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), param.dealApplyAdjAmt.getValue(), param.exchRate.getValue());
        funcApplyAdjAmt = funcApplyAdjAmt.add(trxT.funcApplyAdjAmt.getValue());

        if (null == funcApplyAdjAmt) {
            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editInvArTrxBalApply : end");
            return trxT;
        }

        // START 2022/03/04 D.Mamaril [QC#59333,ADD]
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue()) && NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND.equals(param.arAdjTrxTpCd.getValue())
                && NFCConst.CST_AR_ADJ_TP_CD_A_OR_R_REFUND.equals(param.arAdjTpCd.getValue())) {
            dealApplyAdjAmt = dealApplyAdjAmt.add(trxT.dealRcptVoidAmt.getValue());
            funcApplyAdjAmt = funcApplyAdjAmt.add(trxT.funcRcptVoidAmt.getValue());
        }
        // END 2022/03/04 D.Mamaril [QC#59333,ADD]

        BigDecimal dealRmngBalGrsAmt = NFZC301001.subtractBalVoidAmt(trxT.dealOrigGrsAmt.getValue(), dealApplyGrsAmt, dealApplyCashDiscAmt, trxT.dealApplyCrAmt.getValue(), dealApplyAdjAmt, trxT.dealRcptVoidAmt.getValue());
        BigDecimal funcRmngBalGrsAmt = NFZC301001.subtractFuncVoidAmt(trxT.funcOrigGrsAmt.getValue(), funcApplyGrsAmt, funcApplyCashDiscAmt, trxT.funcApplyCrAmt.getValue(), funcApplyAdjAmt, trxT.funcRcptVoidAmt.getValue(),
                trxT.funcRvalVarAmt.getValue());

        trxT.dealApplyGrsAmt.setValue(dealApplyGrsAmt);
        trxT.dealApplyCashDiscAmt.setValue(dealApplyCashDiscAmt);
        trxT.dealApplyAdjAmt.setValue(dealApplyAdjAmt);
        trxT.dealRmngBalGrsAmt.setValue(dealRmngBalGrsAmt);
        trxT.funcApplyGrsAmt.setValue(funcApplyGrsAmt);
        trxT.funcApplyCashDiscAmt.setValue(funcApplyCashDiscAmt);
        trxT.funcApplyAdjAmt.setValue(funcApplyAdjAmt);
        trxT.funcRmngBalGrsAmt.setValue(funcRmngBalGrsAmt);
        if (trxT.dealRcptVoidAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

            if (dealRmngBalGrsAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_APPLY);
            } else if (trxT.dealOrigGrsAmt.getValue().compareTo(dealRmngBalGrsAmt) == 0 && dealApplyGrsAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && dealApplyCashDiscAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyCrAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && dealApplyAdjAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0
                    && trxT.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_UNAPPLY);
            } else {
                trxT.arCashApplyStsCd.setValue(NFCConst.CST_AR_CASH_APPLY_STS_CD_PARTIAL);
            }
        }
        trxT.cashDiscPct.setValue(cashDiscPct);
        trxT.cashAppDt.setValue(param.batDt.getValue());

        debugLog("editInvArTrxBalApply : end");
        return trxT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg findByKeyForUpdateInvArTrxBal(S21ApiMessageMap msgMap) {

        debugLog("findByKeyForUpdateInvArTrxBal : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_TRX_BALTMsg trxT = new AR_TRX_BALTMsg();
        trxT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        trxT.arTrxBalPk.setValue(param.invTrxBalPk.getValue());
        trxT = (AR_TRX_BALTMsg) S21ApiTBLAccessor.findByKeyForUpdate(trxT);
        if (trxT == null) {

            msgMap.addXxMsgId(NFZC301001.NFCM0553E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("findByKeyForUpdateInvArTrxBal : end");
            return trxT;
        }
        if (!param.invTrxBalLastUpdTs.getValue().equals(trxT.ezUpTime.getValue()) || !param.invTrxBalTz.getValue().equals(trxT.ezUpTimeZone.getValue())) {

            // Mod Start 2015/04/13 for CSA
            if ((NFCConst.CST_INTERFACEID_BOA_CHICAGO.equals(param.rcvFuncId.getValue()) || NFCConst.CST_INTERFACEID_BOA_LA.equals(param.rcvFuncId.getValue()) ||
                    NFCConst.CST_INTERFACEID_CFS_REGULAR.equals(param.rcvFuncId.getValue()) || NFCConst.CST_INTERFACEID_CFS_CPC.equals(param.rcvFuncId.getValue()) ||
                    NFCConst.CST_INTERFACEID_CFS_MD.equals(param.rcvFuncId.getValue()) || NFCConst.CST_INTERFACEID_ECHECK.equals(param.rcvFuncId.getValue())
                    || NFCConst.CST_INTERFACEID_BOA_SAP.equals(param.rcvFuncId.getValue()) // Add 2018/01/29 [QC#23110,ADD]
                    ) && param.xxProcCaseTpCd.getValue().equals(NFCConst.CST_XX_PROC_CASE_TP_CD_PMT)) {
                // do nothing
            } else {
                msgMap.addXxMsgId(NFZC301001.NFCM0554E);
                param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_EXCLERR);
            }
            // Mod End 2015/04/13 by for CSA

        }
        debugLog("findByKeyForUpdateInvArTrxBal : end");
        return trxT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return trxT AR_TRX_BALTMsg
     */
    private AR_TRX_BALTMsg findByKeyForUpdateCrArTrxBal(S21ApiMessageMap msgMap) {

        debugLog("findByKeyForUpdateCrArTrxBal : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_TRX_BALTMsg trxT = new AR_TRX_BALTMsg();
        trxT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        trxT.arTrxBalPk.setValue(param.crTrxBalPk.getValue());
        trxT = (AR_TRX_BALTMsg) S21ApiTBLAccessor.findByKeyForUpdate(trxT);
        if (trxT == null) {

            msgMap.addXxMsgId(NFZC301001.NFCM0553E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("findByKeyForUpdateCrArTrxBal : end");
            return trxT;
        }
        if (!param.crTrxBalLastUpdTs.getValue().equals(trxT.ezUpTime.getValue()) || !param.crTrxBalTz.getValue().equals(trxT.ezUpTimeZone.getValue())) {

            // Mod Start 2015/04/13 for CSA
            if ((NFCConst.CST_INTERFACEID_BOA_CHICAGO.equals(param.rcvFuncId.getValue()) || NFCConst.CST_INTERFACEID_BOA_LA.equals(param.rcvFuncId.getValue()) ||
                    NFCConst.CST_INTERFACEID_CFS_REGULAR.equals(param.rcvFuncId.getValue()) || NFCConst.CST_INTERFACEID_CFS_CPC.equals(param.rcvFuncId.getValue()) ||
                    NFCConst.CST_INTERFACEID_CFS_MD.equals(param.rcvFuncId.getValue()) || NFCConst.CST_INTERFACEID_ECHECK.equals(param.rcvFuncId.getValue())
                    || NFCConst.CST_INTERFACEID_BOA_SAP.equals(param.rcvFuncId.getValue()) // Add 2018/01/29 [QC#23110,ADD]
                    ) && param.xxProcCaseTpCd.getValue().equals(NFCConst.CST_XX_PROC_CASE_TP_CD_PMT)) {
                // do nothing
            } else {
                msgMap.addXxMsgId(NFZC301001.NFCM0554E);
                param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_EXCLERR);
            }
            // Mod End 2015/04/13 by for CSA
        }
        debugLog("findByKeyForUpdateCrArTrxBal : end");
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
    private AR_TRX_BALTMsg insInvArTrxBal(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("insInvArTrxBal : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        NFCNumbering numbering = new NFCNumbering();
        NFXC3060Bean numberBean = numbering.getNumber(NFCConst.CST_SEQ_ID_AR_TRX_BAL, null, 0);
        if (!NFCConst.CST_RTN_CD_NORM.equals(numberBean.getRtrnNo())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0530E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("insInvArTrxBal : end");
            return trxT;
        } else {
            trxT.arTrxBalPk.setValue(numberBean.getOraSqNo());
        }


        String num = "";
        // 01/26/2010 OnAccount Add
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(param.arAdjTrxTpCd.getValue())) {
            if (NFCConst.CST_AR_TRX_TP_CD_ACCOUNT.equals(param.arTrxTpCd.getValue())) {
                if(EZDDBCICarrier.getUppgID().equals(NFCConst.AR_CASH_APPLY_BATCH_ID)){
                    num = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NFCConst.CST_NUMBERING_KEY_AC_BAT);                                 
                }else{
                    num = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NFCConst.CST_NUMBERING_KEY_AC);
                }
            }
        } else {
            if (NFCConst.CST_AR_ADJ_TRX_TP_CD_ACCOUNT.equals(param.arAdjTrxTpCd.getValue())) {
                if(EZDDBCICarrier.getUppgID().equals(NFCConst.AR_CASH_APPLY_BATCH_ID)){
                    num = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NFCConst.CST_NUMBERING_KEY_AC_BAT);                                                     
                }else{
                    num = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NFCConst.CST_NUMBERING_KEY_AC);
                }
            }
        }
        
        if ( num.equals("")){
            if(EZDDBCICarrier.getUppgID().equals(NFCConst.AR_CASH_APPLY_BATCH_ID)){
                num = ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NFCConst.CST_NUMBERING_KEY_DD_BAT);                                             
            }else{
                num =  ZYPNumbering.getUniqueID(param.glblCmpyCd.getValue(), NFCConst.CST_NUMBERING_KEY_DD);                        
            }           
        }

        trxT.arTrxNum.setValue(num);

        S21ApiTBLAccessor.insert(trxT);
        debugLog("AR_TRX_BAL : INSERT RETURN CODE : " + trxT.getReturnCode());
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(trxT.getReturnCode())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0552E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("AR_TRX_BAL : INSERT ERROR");
            debugLog("GLBL_CMPY_CD : " + trxT.glblCmpyCd.getValue());
            debugLog("AR_TRX_BAL_PK : " + trxT.arTrxBalPk.getValue());
        }
        debugLog("insInvArTrxBal : end");
        return trxT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param trxT AR_TRX_BALTMsg
     */
    protected void updArTrxBal(S21ApiMessageMap msgMap, AR_TRX_BALTMsg trxT) {

        debugLog("updArTrxBal : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        // 2010/05/14 DefID 6144/6452/6444/6641 start
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_WRK.equals(param.xxProcCaseTpCd.getValue())) {
            boolean applyFlg = false;
            if (trxT.dealRmngBalGrsAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal tmpDealRcptRmngBalAmt = trxT.dealRmngBalGrsAmt.getValue();
                trxT.dealApplyAdjAmt.setValue(trxT.dealApplyAdjAmt.getValue().add(tmpDealRcptRmngBalAmt));
                trxT.dealRmngBalGrsAmt.setValue(BigDecimal.ZERO);
                applyFlg = true;
            }
            if (trxT.funcRmngBalGrsAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal tmpFuncRcptRmngBalAmt = trxT.funcRmngBalGrsAmt.getValue();
                trxT.funcApplyAdjAmt.setValue(trxT.funcApplyAdjAmt.getValue().add(tmpFuncRcptRmngBalAmt));
                trxT.funcRmngBalGrsAmt.setValue(BigDecimal.ZERO);
                applyFlg = true;
            }
            if (applyFlg && trxT.dealRmngBalGrsAmt.getValue().compareTo(BigDecimal.ZERO) == 0 && trxT.funcRmngBalGrsAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
                trxT.arCashApplyStsCd.setValue(AR_CASH_APPLY_STS_CD_A);
            }
        }
        // 2010/05/14 DefID 6144/6452/6444/6641 end
        S21ApiTBLAccessor.update(trxT);
        debugLog("AR_TRX_BAL : UPDATE RETURN CODE : " + trxT.getReturnCode());
        debugLog("updArTrxBal : end");
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
