/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDDebugOutput;
import business.parts.NFZC301002PMsg;
//import business.parts.NMZC600001PMsg;

//import com.canon.cusa.s21.api.NMZ.NMZC600001.NMZC600001;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFXC3070Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/11/2009   Canon           Y.Kondo         Update          DefID 555
 * 12/07/2009   Canon           Y.Kondo         Create          DefID 2343
 * 05/20/2010   Canon           I.Kondo         Update          DefID 6444 No.016
 * 05/20/2010   Canon           I.Kondo         Update          DefID 6641 No.020
 * 05/24/2010   Canon           I.Kondo         Update          DefID 6444 No.029
 * 05/27/2010   Canon           I.Kondo         Update          DefID 6695 No.037
 * 09/07/2010   Canon           I.Kondo         Update          DefID 7343 No.372
 * 10/08/2010   Canon           T.Tanaka        Update          Merge R2->R3
 * 10/06/2015   Fujitsu         T.Tanaka        Update          delete NMZC600001
 * </pre>
 */
public class NFCCashApplyUpdateCreditBalance extends S21ApiCommonBase {

    /** BigDecimal : NAGATE. */
    private static final BigDecimal NEGATE = BigDecimal.valueOf(-1);

    /**
     */
    public NFCCashApplyUpdateCreditBalance() {
        super();
    }

    /**
     * <pre>
     * </pre>
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

//        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
//
//        updateCreditBalance(msgMap, onBatchType);
//        if (msgMap.getMsgMgr().isXxMsgId()) {
//            msgMap.flush();
//        }

        debugLog("execute : end");
    }

    /**
     * <pre>
     * </pre>
     */
    protected void execute(final List<NFZC301002PMsg> params, final ONBATCH_TYPE onBatchType) {

//        for (NFZC301002PMsg msg : params) {
//            execute(msg, onBatchType);
//            if (!NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(msg.getReturnCode())) {
//                break;
//            }
//        }
    }

//    /**
//     * <pre>
//     * </pre>
//     * 
//     * @param msgMap S21ApiMessageMap
//     */
//    protected void updateCreditBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
//
//        debugLog("updateCreditBalance : start");
//
//        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
//
//        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_XX_PROC_TP_CD_NON.equals(param.xxCrPrflProcTpCd.getValue())) {
//
//            List<NMZC600001PMsg> creditBalanceList = setCreditBalance(msgMap);
//
//            if (null == creditBalanceList) {
//                msgMap.addXxMsgId("NFCM0115E");
//                param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
//                return;
//            }
//
//            if (creditBalanceList.size() > 0) {
//
//                NMZC600001 api = new NMZC600001();
//                api.execute(creditBalanceList, onBatchType);
//                for (NMZC600001PMsg pmsg : creditBalanceList) {
//
//                    List<String> errList = S21ApiUtil.getXxMsgIdList(pmsg);
//                    if (errList.size() > 0) {
//
//                        for (String errorId : errList) {
//                            msgMap.addXxMsgId(errorId);
//                        }
//                        param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
//                    }
//                }
//            }
//        }
//        debugLog("updateCreditBalance : end");
//    }
//
//    /**
//     * <pre>
//     * When an error occurred, this method return null.
//     * </pre>
//     * 
//     * @param msgMap S21ApiMessageMap
//     */
//    private List<NMZC600001PMsg> setCreditBalance(S21ApiMessageMap msgMap) {
//
//        debugLog("setCreditBalance : start");
//
//        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
//        List<NMZC600001PMsg> pmsgList = new ArrayList<NMZC600001PMsg>();
//        if (NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(param.xxHdrNumGetFlg.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxHdrTrxBillToCustCd.getValue())) {
//
//            BigDecimal rcptAmt = calculateHdrRcptAmt(msgMap);
//            if (null == rcptAmt) {
//                // Error.
//                return null;
//            }
//
//            if (rcptAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) != 0) {
//                pmsgList.add(editAmzc600001Pmsg(msgMap, param.xxHdrTrxBillToCustCd.getValue(), rcptAmt, param.rcptNum.getValue()));
//            }
//        }
//        if (NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(param.xxProcCaseTpCd.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxHdrTrxBillToCustCd.getValue())) {
//
//            BigDecimal rcptAmt = calculateInvRcptAmt(msgMap);
//            if (null == rcptAmt) {
//                // Error.
//                return null;
//            }
//
//            if (rcptAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) != 0) {
//                pmsgList.add(editAmzc600001Pmsg(msgMap, param.xxHdrTrxBillToCustCd.getValue(), rcptAmt, param.xxInvInvNum.getValue()));
//            }
//        } else if ((NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_WRK.equals(param.xxProcCaseTpCd.getValue())
//                || NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_AUTO_APVL.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_PMT.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_ADJ_APVL
//                .equals(param.xxProcCaseTpCd.getValue()))
//                && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxInvTrxBillToCustCd.getValue())) {
//
//            BigDecimal rcptAmt = calculateInvRcptAmt(msgMap);
//            if (null == rcptAmt) {
//                // Error.
//                return null;
//            }
//
//            if (rcptAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) != 0) {
//                pmsgList.add(editAmzc600001Pmsg(msgMap, param.xxInvTrxBillToCustCd.getValue(), rcptAmt, param.invNum.getValue()));
//            }
//        }
//
//        debugLog("setCreditBalance : end");
//        return pmsgList;
//    }
//
//    /**
//     * calculate Receipt Amount.When an error occurred, this method
//     * return null.
//     * @param msgMap S21ApiMessageMap
//     * @return amt RcptAmt
//     */
//    private BigDecimal calculateHdrRcptAmt(S21ApiMessageMap msgMap) {
//
//        debugLog("calculateHdrRcptAmt : start");
//
//        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
//        BigDecimal amt = BigDecimal.ZERO;
//        if (NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_UPLOAD.equals(param.xxProcCaseTpCd.getValue())) {
//
//            BigDecimal funcXxTotRcptApplyAmt = this.getFuncAmt(param, param.xxTotRcptApplyAmt.getValue());
//
//            if (null == funcXxTotRcptApplyAmt) {
//                return null;
//            } else {
//                amt = funcXxTotRcptApplyAmt.multiply(NEGATE);
//            }
//
//        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(param.xxProcCaseTpCd.getValue())) {
//
//            amt = this.getFuncAmt(param, param.xxTotRfAmt.getValue());
//
//        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CHNG_RCPT.equals(param.xxProcCaseTpCd.getValue())) {
//
//            BigDecimal funcXxHdrRcptAmt = this.getFuncAmt(param, param.xxHdrRcptAmt.getValue());
//            BigDecimal funcXxHdrAdjAmt = this.getFuncAmt(param, param.xxHdrAdjAmt.getValue());
//
//            if (null == funcXxHdrRcptAmt || null == funcXxHdrAdjAmt) {
//                amt = null;
//            } else {
//                amt = funcXxHdrRcptAmt.multiply(NEGATE).add(funcXxHdrAdjAmt);
//            }
//
//        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_RCPT.equals(param.xxProcCaseTpCd.getValue())) {
//
//            amt = this.getFuncAmt(param, param.xxTotVoidAmt.getValue());
//
//        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_WRK.equals(param.xxProcCaseTpCd.getValue()) && NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.xxDtlDiffAmt.getValue()) > 0) {
//
//            // ADJ_TP : C(MISCELLANEOUS_INCOME)
//            BigDecimal funcXxDtlDiffAmt = this.getFuncAmt(param, param.xxDtlDiffAmt.getValue());
//
//            if (null == funcXxDtlDiffAmt) {
//                return null;
//            } else {
//                amt = funcXxDtlDiffAmt.multiply(NEGATE);
//            }
//
//        } else {
//
//            amt = this.getFuncAmt(param, param.xxTotAdjAmt.getValue());
//
//        }
//        debugLog("calculateHdrRcptAmt : end");
//        return amt;
//    }
//
//    /**
//     * calculate Invoice Amount.When an error occurred, this method
//     * return null.
//     * @param msgMap S21ApiMessageMap
//     * @return amt RcptAmt
//     */
//    private BigDecimal calculateInvRcptAmt(S21ApiMessageMap msgMap) {
//
//        debugLog("calculateInvRcptAmt : start");
//
//        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
//        if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.xxDtlDiffAmt.getValue()) > 0) {
//
//            // ADJ_TP : C(MISCELLANEOUS_INCOME)
//            // Because reflection in the receipt
//            param.xxDtlDiffAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
//            debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
//        }
//        BigDecimal amt = null;
//        BigDecimal cashDisc = param.dealCashDiscAmt.getValue();
//        BigDecimal dtlDisc = param.xxDtlDiscAmt.getValue();
//        BigDecimal dtlDiff = param.xxDtlDiffAmt.getValue();
//        BigDecimal applyAdj = null;
//        if (NFCConst.CST_XX_PROC_CASE_TP_CD_BANK_FEE.equals(param.xxProcCaseTpCd.getValue())) {
//            applyAdj = param.dealApplyAdjAmt.getValue().multiply(NEGATE);
//        } else {
//            applyAdj = param.dealApplyAdjAmt.getValue();
//        }
//
//        // change deal amount to func amount.
//        // cashDisc
//        cashDisc = this.getFuncAmt(param, cashDisc);
//        if (null == cashDisc) {
//            // Error.
//            return null;
//        }
//
//        // dtlDisc
//        dtlDisc = this.getFuncAmt(param, dtlDisc);
//        if (null == dtlDisc) {
//            // Error.
//            return null;
//        }
//
//        // dtlDiff
//        dtlDiff = this.getFuncAmt(param, dtlDiff);
//        if (null == dtlDiff) {
//            // Error.
//            return null;
//        }
//
//        // applyAdj
//        applyAdj = this.getFuncAmt(param, applyAdj);
//        if (null == applyAdj) {
//            // Error.
//            return null;
//        }
//
//        amt = cashDisc.add(dtlDisc).add(dtlDiff).add(applyAdj);
//        debugLog("calculateInvRcptAmt : end");
//        return amt.multiply(NEGATE);
//    }
//
//    /**
//     * <pre>
//     * </pre>
//     * 
//     * @param msgMap S21ApiMessageMap
//     * @param billToCustCd billToCustCd
//     * @param rcptAmt rcptAmt
//     * @param updKeyNum updKeyNum
//     * @return pmsg NMZC600001PMsg
//     */
//    private NMZC600001PMsg editAmzc600001Pmsg(S21ApiMessageMap msgMap, String billToCustCd, BigDecimal rcptAmt, String updKeyNum) {
//
//        debugLog("editAmzc600001Pmsg : start");
//
//        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
//
//        NMZC600001PMsg pmsg = new NMZC600001PMsg();
//        pmsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
//        pmsg.billToCustCd.setValue(billToCustCd);
//        pmsg.inProcAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
//        pmsg.invAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
//        pmsg.invDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
//        pmsg.rcptAmt.setValue(rcptAmt);
//        pmsg.updKeyNum.setValue(updKeyNum);
//        pmsg.slsDt.setValue(param.batDt.getValue());
//        if (NFCConst.CST_XX_PROC_CASE_TP_CD_NEW_RCPT.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_UPLOAD.equals(param.xxProcCaseTpCd.getValue())) {
//            pmsg.rcptDt.setValue(param.xxHdrRcptDt.getValue());
//        } else {
//            pmsg.rcptDt.setValue(NFCConst.CST_DB_INIT_VAL_STR);
//        }
//
//        debugLog("editAmzc600001Pmsg : end");
//        return pmsg;
//    }
//
//    /**
//     * When an error occurred, this method return null.
//     * @param param NFZC301002PMsg
//     * @param dealAmt BigDecimal
//     * @return BigDecimal
//     */
//    private BigDecimal getFuncAmt(NFZC301002PMsg param, BigDecimal dealAmt) {
//        NFCCurrencyConversion afcCryCon = new NFCCurrencyConversion();
//        String glDt;
//        if (ZYPCommonFunc.hasValue(param.cashAppGlDt)) {
//            glDt = param.cashAppGlDt.getValue();
//        } else {
//            glDt = param.rcptGlDt.getValue();
//        }
//        NFXC3070Bean resultBean = afcCryCon.convertCurrency(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), dealAmt, glDt, null);
//        if (!NFCConst.CST_RTN_CD_NORM.equals(resultBean.getRtrnCd())) {
//            return null;
//        } else {
//            return resultBean.getFuncAmt();
//        }
//    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
