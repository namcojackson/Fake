/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.util.List;

import parts.common.EZDDebugOutput;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 01/27/2010   Canon           H.Tokunaga      Update          ACC case add. it is DED case copy. And, The COA_PROD_CD item is added.
 * 04/27/2010   Canon           H.Tokunaga      Update          DefID 5884 
 * 04/13/2015   Canon           K.Kimura        Update          For CSA
 * 04/21/2016   Canon           T.Tsuchida      Update          DefId 4925
 * 05/16/2016   Canon           T.Tsuchida      Update          DefId 7881
 * 08/01/2016   Canon           T.Tsuchida      Update          DefId 12657
 * 02/22/2016   Hitachi         E.Kameishi      Update          QC#16802
 * 04/22/2022   CITS            D.Mamaril       Update          QC#59333
 *</pre>
 */
public class NFCCashApplyPattern extends S21ApiCommonBase {

    /** Apply Update Pattern Index : 0. */
    private static final int XX_RCPT_HDR_PROC_TP_CD = 0;

    /** Apply Update Pattern Index : 1. */
    private static final int XX_TRX_RCPT_PROC_TP_CD = 1;

    /** Apply Update Pattern Index : 2. */
    private static final int XX_ADJ_PROC_TP_CD = 2;

    /** Apply Update Pattern Index : 3. */
    private static final int XX_TRX_INV_PROC_TP_CD = 3;

    /** Apply Update Pattern Index : 4. */
    private static final int XX_RCPT_UN_PROC_TP_CD = 4;

    /** Apply Update Pattern Index : 5. */
    private static final int XX_CASH_APP_PROC_TP_CD = 5;

    /** Apply Update Pattern Index : 6. */
    private static final int XX_INSTN_RCPT_PROC_TP_CD = 6;

    /** Apply Update Pattern Index : 7. */
    private static final int XX_INSTN_INV_PROC_TP_CD = 7;

    /** Apply Update Pattern Index : 8. */
    private static final int XX_RCPT_RCV_PROC_TP_CD = 8;

    /** Apply Update Pattern Index : 9. */
    private static final int XX_CR_PRFL_PROC_TP_CD = 9;

    /**
     */
    public NFCCashApplyPattern() {
        super();
    }

    /**
     * <pre>
     * </pre>
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        setApplyPattern(msgMap, onBatchType);
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
     * @param onBatchType ONBATCH_TYPE
     */
    public void setApplyPattern(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("setApplyPattern : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        setApplyCasePattern(param);

        if (NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxProcCaseTpCd.getValue())) {

            param.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_ERR);

            msgMap.addXxMsgId(NFZC301001.NFCM0535E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
        } else {

            setApplyUpdatePattern(param);
        }
        debugLog("setApplyPattern : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param wrkP NFZC301002PMsg
     * @return wrkP NFZC301002PMsg
     */
    private NFZC301002PMsg setApplyCasePattern(NFZC301002PMsg wrkP) {

        debugLog("setApplyCasePattern : start");

        if (NFCConst.CST_GRP_INV_FLG_GRP_ON.equals(wrkP.grpInvFlg.getValue())) {

            wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_GRP_INV);

        } else if (NFCConst.CST_PROC_TP_CD_NEW.equals(wrkP.procTpCd.getValue())) {

            if (!NFZC301001.AUTO_CASH_APPLICATION.equals(wrkP.bizAppId.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcvHdrNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue())
                    && (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()))) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_WRK);
                // START 2017/02/22 E.Kameishi [QC#16802,MOD]
                // Add Start 2015/04/13 for CSA
                if (NFCConst.CST_INTERFACEID_BOA_CHICAGO.equals(wrkP.rcvFuncId.getValue()) || NFCConst.CST_INTERFACEID_BOA_LA.equals(wrkP.rcvFuncId.getValue()) ||
                       NFCConst.CST_INTERFACEID_CFS_REGULAR.equals(wrkP.rcvFuncId.getValue()) || NFCConst.CST_INTERFACEID_CFS_CPC.equals(wrkP.rcvFuncId.getValue()) ||
                       NFCConst.CST_INTERFACEID_CFS_MD.equals(wrkP.rcvFuncId.getValue()) || NFCConst.CST_INTERFACEID_ECHECK.equals(wrkP.rcvFuncId.getValue())
                       || NFCConst.CST_INTERFACEID_BOA_SAP.equals(wrkP.rcvFuncId.getValue()) // Add 2018/01/29 [QC#23110,ADD]
                       || NFCConst.CST_INTERFACEID_EMANAGE.equals(wrkP.rcvFuncId.getValue())) {
                    wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_PMT);
                }
                // Add End 2015/04/13 for CSA
                // END 2017/02/22 E.Kameishi [QC#16802,MOD]

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_PMT);

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_AR_ADJ_TRX_TP_CD_DEDUCTION.equals(wrkP.arAdjTrxTpCd.getValue())
                    && !NFCConst.CST_AR_ADJ_TP_CD_BANK_COMMISSION_FEE.equals(wrkP.arAdjTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC);

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_AR_ADJ_TRX_TP_CD_ACCOUNT.equals(wrkP.arAdjTrxTpCd.getValue())
                    && !NFCConst.CST_AR_ADJ_TP_CD_BANK_COMMISSION_FEE.equals(wrkP.arAdjTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC);

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT.equals(wrkP.arAdjTrxTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ADJ);

                // 2009.10.28 [Purge Offset] add start
            } else if (NFZC301001.PURGE_OFFSET.equals(wrkP.bizAppId.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue()) && wrkP.dealOnAcctCashAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                // NEW_RCPT TYPE_CD(1100)
                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT);
            } else if (NFZC301001.CROSS_PURGE_OFFSET.equals(wrkP.bizAppId.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue()) && wrkP.dealOnAcctCashAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT);
                // 2016.04.21 [Refund DefId 4925] add start
            } else if (NFZC301001.REFUND.equals(wrkP.bizAppId.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && AR_ADJ_TRX_TP.REFUND.equals(wrkP.arAdjTrxTpCd.getValue()) && wrkP.dealOnAcctCashAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT);
                // 2016.04.21 [Refund DefId 4925] add start
            } else if (NFZC301001.MANUAL_RECEIPT_ENTRY.equals(wrkP.bizAppId.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue()) && !(wrkP.dealOnAcctCashAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0)) {
                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT);

            } else if (NFZC301001.UPLOADED_RECEIPT_DATA_CHECK.equals(wrkP.bizAppId.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue()) && !(wrkP.dealOnAcctCashAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0)) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_UPLOAD);

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ACC);

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_AR_ADJ_TRX_TP_CD_DEDUCTION.equals(wrkP.arAdjTrxTpCd.getValue())
                    && NFCConst.CST_AR_ADJ_TP_CD_BANK_COMMISSION_FEE.equals(wrkP.arAdjTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE);

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_AR_ADJ_TRX_TP_CD_ACCOUNT.equals(wrkP.arAdjTrxTpCd.getValue())
                    && NFCConst.CST_AR_ADJ_TP_CD_BANK_COMMISSION_FEE.equals(wrkP.arAdjTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE);

            } else if (NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT.equals(wrkP.arAdjTrxTpCd.getValue())
                    && !(wrkP.dealApplyAdjAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) && wrkP.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_AUTO_APVL);

            } else if (NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT.equals(wrkP.arAdjTrxTpCd.getValue())
                    && wrkP.dealApplyAdjAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && !(wrkP.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0)) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_APVL_WAIT);

            } else if (NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.crNum.getValue())
                    && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CNT_BAL);

            } else if (NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.crNum.getValue())
                    && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_INV);

            }
        } else if (NFCConst.CST_PROC_TP_CD_UPD.equals(wrkP.procTpCd.getValue())) {

            if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CHNG_RCPT);

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_AR_ADJ_TP_CD_A_OR_R_REFUND.equals(wrkP.arAdjTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_RF);

                // 2016.05.16 [Refund DefId 7881] add start
            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_AR_ADJ_TP_CD_CM_RFND.equals(wrkP.arAdjTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_RF);

                // 2016.05.16 [Refund DefId 7881] add start
                // 2016.08.01 [Refund DefId 12657] add start
            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_AR_ADJ_TP_CD_CC_RFND.equals(wrkP.arAdjTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_RF);

                // 2016.08.01 [Refund DefId 12657] add start
            }
        } else if (NFCConst.CST_PROC_TP_CD_CANC.equals(wrkP.procTpCd.getValue())) {

            if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && !NFCConst.CST_AR_ADJ_TRX_TP_CD_DEDUCTION.equals(wrkP.arAdjTrxTpCd.getValue())
                    && !NFCConst.CST_AR_ADJ_TRX_TP_CD_ACCOUNT.equals(wrkP.arAdjTrxTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_PMT);

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_AR_ADJ_TRX_TP_CD_DEDUCTION.equals(wrkP.arAdjTrxTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC);

            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_AR_ADJ_TRX_TP_CD_ACCOUNT.equals(wrkP.arAdjTrxTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC);

            // START 2022/04/22 D.Mamaril [QC#59333,MOD]
            //} else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT.equals(wrkP.arAdjTrxTpCd.getValue())) {
            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && (NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT.equals(wrkP.arAdjTrxTpCd.getValue()) || NFCConst.CST_AR_ADJ_TRX_TP_CD_REFUND.equals(wrkP.arAdjTrxTpCd.getValue()))) {
            // END 2022/04/22 D.Mamaril [QC#59333,MOD]

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_ADJ);

            } else if (NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT.equals(wrkP.arAdjTrxTpCd.getValue())
                    && wrkP.dealApplyAdjAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0 && !(wrkP.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0)) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADJ_APVL_WAIT);

            } else if (NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.crNum.getValue())
                    && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue())) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_CNT_BAL);

                // 2009.10.28 [Purge Offset] add start
            } else if (NFZC301001.PURGE_OFFSET.equals(wrkP.bizAppId.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue()) && wrkP.dealOnAcctCashAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_RCPT);
                // 2009.10.28 [Purge Offset] add end
                // 2009.10.29 [DefID-1234] mod start
            } else if (NFZC301001.CROSS_PURGE_OFFSET.equals(wrkP.bizAppId.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue())
                    && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue()) && wrkP.dealOnAcctCashAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_RCPT);
            } else if (!NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.arAdjTrxTpCd.getValue())) {
                // 2009.10.28 [DefID-1234] mod end

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_RCPT);

            }
        } else if (NFCConst.CST_PROC_TP_CD_APVL.equals(wrkP.procTpCd.getValue())) {

            if (NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.rcptNum.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(wrkP.invNum.getValue()) && NFCConst.CST_AR_ADJ_TRX_TP_CD_ADJUSTMENT.equals(wrkP.arAdjTrxTpCd.getValue())
                    && !(wrkP.dealApplyAdjAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) && !(wrkP.dealApplyAdjRsvdAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0)) {

                wrkP.xxProcCaseTpCd.setValue(NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_APVL);

            }
        }
        debugLog("setApplyCasePattern : end");

        return wrkP;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param wrkP NFZC301002PMsg
     * @return wrkP NFZC301002PMsg
     */
    private NFZC301002PMsg setApplyUpdatePattern(NFZC301002PMsg wrkP) {

        debugLog("setApplyUpdatePattern : start");

        String[] updatePattern = {NFCConst.CST_XX_PROC_TP_CD_NON, NFCConst.CST_XX_PROC_TP_CD_NON, NFCConst.CST_XX_PROC_TP_CD_NON, NFCConst.CST_XX_PROC_TP_CD_NON, NFCConst.CST_XX_PROC_TP_CD_NON, NFCConst.CST_XX_PROC_TP_CD_NON,
                NFCConst.CST_XX_PROC_TP_CD_NON, NFCConst.CST_XX_PROC_TP_CD_NON, NFCConst.CST_XX_PROC_TP_CD_NON, NFCConst.CST_XX_PROC_TP_CD_NON };

        if (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_RCPT_RCV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_WRK.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_RCPT_RCV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_RCPT_RCV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ADJ.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_RCPT_RCV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_AUTO_APVL.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_APVL_WAIT.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CNT_BAL.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_INV.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CHNG_RCPT.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_PMT.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_ADJ.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADJ_APVL_WAIT.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_CNT_BAL.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_RCPT.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_RCPT_HDR_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_RCPT_UN_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_RCPT_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_APVL.equals(wrkP.xxProcCaseTpCd.getValue())) {

            updatePattern[XX_ADJ_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_TRX_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;
            updatePattern[XX_CASH_APP_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_INSTN_INV_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_ADD;
            updatePattern[XX_CR_PRFL_PROC_TP_CD] = NFCConst.CST_XX_PROC_TP_CD_UPD;

        }

        wrkP.xxAdjProcTpCd.setValue(updatePattern[XX_ADJ_PROC_TP_CD]);
        wrkP.xxTrxInvProcTpCd.setValue(updatePattern[XX_TRX_INV_PROC_TP_CD]);
        wrkP.xxTrxRcptProcTpCd.setValue(updatePattern[XX_TRX_RCPT_PROC_TP_CD]);
        wrkP.xxRcptHdrProcTpCd.setValue(updatePattern[XX_RCPT_HDR_PROC_TP_CD]);
        wrkP.xxRcptUnProcTpCd.setValue(updatePattern[XX_RCPT_UN_PROC_TP_CD]);
        wrkP.xxCashAppProcTpCd.setValue(updatePattern[XX_CASH_APP_PROC_TP_CD]);
        wrkP.xxInstnInvProcTpCd.setValue(updatePattern[XX_INSTN_INV_PROC_TP_CD]);
        wrkP.xxInstnRcptProcTpCd.setValue(updatePattern[XX_INSTN_RCPT_PROC_TP_CD]);
        wrkP.xxRcptRcvProcTpCd.setValue(updatePattern[XX_RCPT_RCV_PROC_TP_CD]);
        wrkP.xxCrPrflProcTpCd.setValue(updatePattern[XX_CR_PRFL_PROC_TP_CD]);

        debugLog("setApplyUpdatePattern : end");

        return wrkP;
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
