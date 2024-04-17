/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.util.List;

import parts.common.EZDDebugOutput;
import business.db.AR_BAL_INSTN_WRKTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 01/27/2010   Canon           H.Tokunaga      Update          ACC case add. it is DED case copy. And, The COA_PROD_CD item is added.    
 *</pre>
 */
public class NFCCashApplyUpdateInvArBalInstnWrk extends S21ApiCommonBase {

    /**
     */
    public NFCCashApplyUpdateInvArBalInstnWrk() {
        super();
    }

    /**
     * <pre>
     * </pre>
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateCashApplyInvArBalInstnWrk(msgMap, onBatchType);
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
    protected void updateCashApplyInvArBalInstnWrk(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("updateCashApplyInvArBalInstnWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_XX_PROC_TP_CD_NON.equals(param.xxInstnInvProcTpCd.getValue())) {

            if (NFCConst.CST_XX_PROC_CASE_TP_CD_CNT_BAL.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_CNT_BAL.equals(param.xxProcCaseTpCd.getValue())) {

                if (NFCConst.CST_XX_INV_NUM_GET_FLG_ON.equals(param.xxInvNumGetFlg.getValue())) {

                    AR_BAL_INSTN_WRKTMsg invInstnWrkT = editArBalInvInstnWrk(msgMap);

                    insArBalInstnWrk(msgMap, invInstnWrkT);
                }
                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                    // 3080)
                    AR_BAL_INSTN_WRKTMsg crInstnWrkT = editArBalCrInstnWrk(msgMap);

                    insArBalInstnWrk(msgMap, crInstnWrkT);
                }
            } else {

                AR_BAL_INSTN_WRKTMsg invInstnWrkT = editArBalInvInstnWrk(msgMap);

                insArBalInstnWrk(msgMap, invInstnWrkT);
            }
        }
        debugLog("updateCashApplyInvArBalInstnWrk : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return instnWrkT AR_BAL_INSTN_WRKTMsg
     */
    private AR_BAL_INSTN_WRKTMsg editArBalInvInstnWrk(S21ApiMessageMap msgMap) {

        debugLog("editArBalInvInstnWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_BAL_INSTN_WRKTMsg instnWrkT = new AR_BAL_INSTN_WRKTMsg();

        instnWrkT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        instnWrkT.batDt.setValue(param.batDt.getValue());
        instnWrkT.procTpCd.setValue(param.procTpCd.getValue());
        instnWrkT.batStsCd.setValue(NFCConst.CST_BAT_STS_CD_UNPROC);
        // 01/26/2010 OnAccount Add
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(param.xxProcCaseTpCd.getValue())
                || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue())) {

            instnWrkT.billToCustCd.setValue(param.xxHdrTrxBillToCustCd.getValue());
            instnWrkT.sellToCustCd.setValue(param.xxHdrTrxSellToCustCd.getValue());
            instnWrkT.payerCustCd.setValue(param.xxHdrTrxPayerCustCd.getValue());
        } else {

            instnWrkT.billToCustCd.setValue(param.xxInvTrxBillToCustCd.getValue());
            instnWrkT.payerCustCd.setValue(param.xxInvTrxPayerCustCd.getValue());
            // 01/26/2010 OnAccount Add
            if (NFCConst.CST_AR_TRX_TP_CD_DEDUCTION.equals(param.xxInvTrxArTrxTpCd.getValue())) {
                instnWrkT.sellToCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
            } else {
                if (NFCConst.CST_AR_TRX_TP_CD_ACCOUNT.equals(param.xxInvTrxArTrxTpCd.getValue())) {
                    instnWrkT.sellToCustCd.setValue(NFCConst.CST_DB_INIT_VAL_STR);
                } else {
                    instnWrkT.sellToCustCd.setValue(param.xxInvTrxSellToCustCd.getValue());
                }
            }
        }
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(param.xxProcCaseTpCd.getValue())) {

            instnWrkT.arTrxBalPk.setValue(param.xxInvTrxBalPk.getValue());

            instnWrkT.arTrxTpCd.setValue(NFCConst.CST_AR_TRX_TP_CD_DEDUCTION);
            // 01/26/2010 OnAccount Add
            if (NFCConst.CST_DB_INIT_VAL_STR.equals(param.arAdjTrxTpCd.getValue())) {
                if (NFCConst.CST_AR_TRX_TP_CD_ACCOUNT.equals(param.arTrxTpCd.getValue())) {
                    instnWrkT.arTrxTpCd.setValue(NFCConst.CST_AR_TRX_TP_CD_ACCOUNT);
                }
            } else {
                if (NFCConst.CST_AR_ADJ_TRX_TP_CD_ACCOUNT.equals(param.arAdjTrxTpCd.getValue())) {
                    instnWrkT.arTrxTpCd.setValue(NFCConst.CST_AR_TRX_TP_CD_ACCOUNT);
                }
            }

        } else {

            instnWrkT.arTrxBalPk.setValue(param.invTrxBalPk.getValue());
            instnWrkT.arTrxTpCd.setValue(param.xxInvTrxArTrxTpCd.getValue());
        }

        debugLog("editArBalInstnWrk : end");
        return instnWrkT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return instnWrkT AR_BAL_INSTN_WRKTMsg
     */
    private AR_BAL_INSTN_WRKTMsg editArBalCrInstnWrk(S21ApiMessageMap msgMap) {

        debugLog("editArBalCrInstnWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_BAL_INSTN_WRKTMsg instnWrkT = new AR_BAL_INSTN_WRKTMsg();

        instnWrkT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        instnWrkT.batDt.setValue(param.batDt.getValue());
        instnWrkT.billToCustCd.setValue(param.xxInvTrxBillToCustCd.getValue());
        instnWrkT.sellToCustCd.setValue(param.xxInvTrxSellToCustCd.getValue());
        instnWrkT.payerCustCd.setValue(param.xxInvTrxPayerCustCd.getValue());
        instnWrkT.procTpCd.setValue(param.procTpCd.getValue());
        instnWrkT.batStsCd.setValue(NFCConst.CST_BAT_STS_CD_UNPROC);
        instnWrkT.arTrxBalPk.setValue(param.crTrxBalPk.getValue());
        instnWrkT.arTrxTpCd.setValue(NFCConst.CST_AR_TRX_TP_CD_CREDITMEMO);

        debugLog("editArBalCrInstnWrk : end");
        return instnWrkT;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param instnWrkT AR_BAL_INSTN_WRKTMsg
     */
    protected void insArBalInstnWrk(S21ApiMessageMap msgMap, AR_BAL_INSTN_WRKTMsg instnWrkT) {

        debugLog("insArBalInstnWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        NFCNumbering numbering = new NFCNumbering();
        NFXC3060Bean numberBean = numbering.getNumber(NFCConst.CST_SEQ_ID_BAL_INSTN, null, 0);
        if (!NFCConst.CST_RTN_CD_NORM.equals(numberBean.getRtrnNo())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0530E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("insArBalInstnWrk : end");
            return;
        }
        instnWrkT.balInstnSqPk.setValue(numberBean.getOraSqNo());

        S21ApiTBLAccessor.insert(instnWrkT);
        debugLog("AR_BAL_INSTN_WRK : INSERT RETURN CODE : " + instnWrkT.getReturnCode());
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(instnWrkT.getReturnCode())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0556E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("AR_BAL_INSTN_WRK : INSERT ERROR");
            debugLog("GLBL_CMPY_CD : " + instnWrkT.glblCmpyCd.getValue());
            debugLog("BAL_INSTN_SQ_PK : " + instnWrkT.balInstnSqPk.getValue());
        }
        debugLog("insArBalInstnWrk : end");
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
