/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 12/07/2009   Canon           Y.Kondo         Create          DefID 2343
 * 04/18/2010   Canon           H.Tokunaga      Create          DefID 5732
 * 05/06/2010   Canon           H.Tokunaga      Update          DefID 6144
 * 05/24/2010   Canon           I.Kondo         Update          DefID 6695 No.037
 * 05/24/2010   Canon           T.Tanaka        Update          DefID 7244
 *</pre>
 */
public class NFCCashApplyCheckBalance extends S21ApiCommonBase {

    /** Map Key : DTL_DIFF_ADD_FLG. */
    private static final String DTL_DIFF_ADD_FLG = "DTL_DIFF_ADD_FLG";

    /** Map Key : SUM_DTL_DIFF_AMT. */
    private static final String SUM_DTL_DIFF_AMT = "SUM_DTL_DIFF_AMT";

    /** */
    private static final BigDecimal STANDARD_AMT = BigDecimal.ZERO;

    /** BigDecimal : NAGATE. */
    private static final BigDecimal NEGATE = BigDecimal.valueOf(-1);

    /** CASH_DISC_APPLY_CD : NET_DISC_AMT. */
    private static final String CASH_DISC_APPLY_NET = "1";

    /** CASH_DISC_APPLY_CD : NET_EXTN_DISC_AMT. */
    private static final String CASH_DISC_APPLY_NET_EXTN = "2";

    /** CASH_DISC_APPLY_CD : INV_DISC_AMT. */
    private static final String CASH_DISC_APPLY_INV = "3";

    /** CASH_DISC_APPLY_CD : INV_EXTN_DISC_AMT. */
    private static final String CASH_DISC_APPLY_INV_EXTN = "4";

    /**
     */
    public NFCCashApplyCheckBalance() {
        super();
    }

    /**
     * <pre>
     * </pre>
     */
    protected void checkRcptHdr(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptHdr : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        checkRcptHdr(msgMap, onBatchType);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        debugLog("checkRcptHdr : end");
    }

    /**
     * <pre>
     * </pre>
     */
    protected void checkRcptHdr(final List<NFZC301002PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NFZC301002PMsg msg : params) {
            checkRcptHdr(msg, onBatchType);
            if (!NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(msg.getReturnCode())) {
                break;
            }
        }
    }

    /**
     * <pre>
     * </pre>
     */
    protected void setRcptHdrDtl(final NFZC301002PMsg hdrParam, final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("setRcptHdrDtl : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        setRcptHdrDtl(hdrParam, msgMap, onBatchType);

        debugLog("setRcptHdrDtl : end");
    }

    /**
     * Check Rcpt Dtl
     * @param params NFZC301002PMsg
     * @param tmpDtlMap Map<String, Object>
     * @param onBatchType ONBATCH_TYPE
     */
    protected void checkRcptDtl(final NFZC301002PMsg param, Map<String, Object> tmpDtlMap, final ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptDtl : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        checkRcptDtl(msgMap, tmpDtlMap, onBatchType);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        debugLog("checkRcptDtl : end");
    }

    /**
     * Check Rcpt Dtl
     * @param params List<NFZC301002PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    protected void checkRcptDtl(final List<NFZC301002PMsg> params, final ONBATCH_TYPE onBatchType) {

        String tempDtlDiffAddFlg = NFCConst.CST_FLAG_OFF;
        BigDecimal tempSumDtlDiffAmt = NFCConst.CST_DB_INIT_VAL_NUM;
        // parameter map
        Map<String, Object> tmpDtlMap = new HashMap<String, Object>();
        tmpDtlMap.put(DTL_DIFF_ADD_FLG, tempDtlDiffAddFlg);
        tmpDtlMap.put(SUM_DTL_DIFF_AMT, tempSumDtlDiffAmt);

        for (NFZC301002PMsg msg : params) {

            checkRcptDtl(msg, tmpDtlMap, onBatchType);
            if (!NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(msg.getReturnCode())) {
                return;
            }
        }
        // when roop end, get parameter map
        tempDtlDiffAddFlg = (String) tmpDtlMap.get(DTL_DIFF_ADD_FLG);
        tempSumDtlDiffAmt = (BigDecimal) tmpDtlMap.get(SUM_DTL_DIFF_AMT);
        for (NFZC301002PMsg msg : params) {

            if (NFCConst.CST_EXTN_DISC_FLG_ON.equals(msg.xxCashDiscGetFlg.getValue()) && NFCConst.CST_FLAG_OFF.equals(msg.xxDtlTotMatchFlg.getValue())) {

                if (NFCConst.CST_FLAG_OFF.equals(tempDtlDiffAddFlg) && NFCConst.CST_DB_INIT_VAL_NUM.compareTo(msg.xxDtlDiffAmt.getValue()) != 0) {

                    S21ApiMessageMap msgMap = new S21ApiMessageMap(msg);
                    msgMap.addXxMsgId(NFZC301001.NFCM0575E);
                    msg.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
                    msgMap.flush();
                } else {
                    // The Diff judges ADJ_TP by a plus or a minus
                    if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(msg.xxDtlDiffAmt.getValue()) <= 0) {

                        // ADJ_TP : B(NON_OPERATING_MISCELLANEOUS)
                        if (NFCConst.CST_FLAG_OFF.equals(msg.xxDtlDiffSetFlg.getValue())) {

                            msg.xxDtlDiffAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                            debugLog("xxDtlDiffAmt : " + msg.xxDtlDiffAmt.getValue());
                        }
                    } else {

                        // ADJ_TP : C(MISCELLANEOUS_INCOME)
                        if (!NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(msg.xxHdrNumGetFlg.getValue())) {

                            msg.xxDtlDiffAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                            debugLog("xxDtlDiffAmt : " + msg.xxDtlDiffAmt.getValue());
                        }
                    }
                }
            }
            // 2010/04/27 H.Tokunaga update S80 start DefID 5807
            // if
            // (NFCConst.CST_EXTN_DISC_FLG_ON.equals(msg.xxCashDiscGetFlg.getValue())
            // &&
            // NFCConst.CST_FLAG_ON.equals(msg.xxDtlTotMatchFlg.getValue()))
            // {
            if (NFCConst.CST_EXTN_DISC_FLG_ON.equals(msg.xxCashDiscGetFlg.getValue())) {
                // 2010/04/27 H.Tokunaga update S80 end DefID 5807
                if (NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(msg.xxHdrNumGetFlg.getValue())) {

                    if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(tempSumDtlDiffAmt) != 0) {
                        msg.xxDtlDiffAmt.setValue(tempSumDtlDiffAmt);
                        debugLog("xxDtlDiffAmt : " + msg.xxDtlDiffAmt.getValue());
                    }
                }
            }
        }
    }

    /**
     * <pre>
     * </pre>
     */
    protected void checkDiff(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("checkDiff : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        checkDiff(msgMap, onBatchType);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        debugLog("checkDiff : end");
    }

    /**
     * <pre>
     * </pre>
     */
    protected void checkDiff(final List<NFZC301002PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NFZC301002PMsg msg : params) {
            checkDiff(msg, onBatchType);
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
    private void checkRcptHdr(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptHdr : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(param.xxHdrNumGetFlg.getValue())) {

            if (NFCConst.CST_XX_PROC_CASE_TP_CD_PMT.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_DED_OR_ACC.equals(param.xxProcCaseTpCd.getValue())
                    || NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ADJ.equals(param.xxProcCaseTpCd.getValue()) || NFCConst.CST_XX_PROC_CASE_TP_CD_ADD_ACC.equals(param.xxProcCaseTpCd.getValue())) {

                if (checkCashApplyBalance(msgMap, onBatchType)) {
                    debugLog("checkRcptHdr : end");
                    return;
                }

            } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(param.xxProcCaseTpCd.getValue())) {

                if (checkRf(msgMap, onBatchType)) {
                    debugLog("checkRcptHdr : end");
                    return;
                }
            }
            if (NFCConst.CST_EXTN_DISC_FLG_ON.equals(param.xxCashDiscGetFlg.getValue())) {
                // 2010/04/27 H.Tokunaga update S80 start DefID 5807
                // if
                // (param.xxHdrRmngBalAmt.getValue().compareTo(param.xxTotManAmt.getValue())
                // == 0 &&
                // NFCConst.CST_GRP_INV_FLG_GRP_OFF.equals(param.grpInvFlg.getValue()))
                // {

                // param.xxDtlTotMatchFlg.setValue(NFCConst.CST_FLAG_ON);
                param.xxDtlTotMatchFlg.setValue(NFCConst.CST_FLAG_OFF);

                // } else {
                // 2010/04/27 H.Tokunaga update S80 end DefID 5807
                // 2010/04/18 H.Tokunaga Delete nomal cash disc check
                // start DefID 5732
                // if
                // (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue()))
                // {
                //
                // checkRcptHdrNetBalance(msgMap, onBatchType);
                // }
                // 2010/04/18 H.Tokunaga Delete nomal cash disc check
                // end
                if (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue())) {

                    checkRcptHdrNetExtnBalance(msgMap, onBatchType);
                }
                if (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxDiscGracePerFromDt.getValue())
                        && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxDiscGracePerThruDt.getValue()) && Integer.parseInt(param.batDt.getValue()) >= Integer.parseInt(param.xxDiscGracePerFromDt.getValue())
                        && Integer.parseInt(param.batDt.getValue()) <= Integer.parseInt(param.xxDiscGracePerThruDt.getValue())) {
                    // 2010/04/18 H.Tokunaga Delete nomal cash disc
                    // check start DefID 5732
                    // checkRcptHdrInvBalance(msgMap, onBatchType);
                    // 2010/04/18 H.Tokunaga Delete nomal cash disc
                    // check end
                    if (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue())) {

                        // RCPT_HDR_INV_EXTN_Cash
                        checkRcptHdrInvExtnBalance(msgMap, onBatchType);
                    }
                }
                if (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue())) {

                    msgMap.addXxMsgId(NFZC301001.NFCM0540E);
                    param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
                }
                // 2010/04/27 H.Tokunaga update S80 start DefID 5807
                // }
                // 2010/04/27 H.Tokunaga update S80 end DefID 5807
            }
        }
        debugLog("checkRcptHdr : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void setRcptHdrDtl(NFZC301002PMsg hdrParam, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("setRcptHdrDtl : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        if (!NFCConst.CST_FLAG_ON.equals(param.xxDtlTotMatchFlg.getValue())) {

            param.xxTotRcptApplyAmt.setValue(hdrParam.xxTotRcptApplyAmt.getValue());
            param.xxTotInvApplyAmt.setValue(hdrParam.xxTotInvApplyAmt.getValue());
            param.xxTotDedctApplyAmt.setValue(hdrParam.xxTotDedctApplyAmt.getValue());
            param.xxTotRfAmt.setValue(hdrParam.xxTotRfAmt.getValue());
            param.xxTotVoidAmt.setValue(hdrParam.xxTotVoidAmt.getValue());
            param.xxTotOnAcctCashAmt.setValue(hdrParam.xxTotOnAcctCashAmt.getValue());
            param.xxTotCashDiscAmt.setValue(hdrParam.xxTotCashDiscAmt.getValue());
            param.xxTotInvAdjAmt.setValue(hdrParam.xxTotInvAdjAmt.getValue());
            param.xxTotAdjAmt.setValue(hdrParam.xxTotAdjAmt.getValue());
            param.xxTotManAmt.setValue(hdrParam.xxTotManAmt.getValue());
            param.xxTotDiscAmt.setValue(hdrParam.xxTotDiscAmt.getValue());
            param.xxTotExtnDiscAmt.setValue(hdrParam.xxTotExtnDiscAmt.getValue());
            param.xxInvTotRmngGrsAmt.setValue(hdrParam.xxInvTotRmngGrsAmt.getValue());
            param.xxInvTotOrigGrsAmt.setValue(hdrParam.xxInvTotOrigGrsAmt.getValue());
            param.xxTotNetSlsAmt.setValue(hdrParam.xxTotNetSlsAmt.getValue());
            param.xxCashDiscApplyCd.setValue(hdrParam.xxCashDiscApplyCd.getValue());
            param.xxTotNetDiscAmt.setValue(hdrParam.xxTotNetDiscAmt.getValue());
            param.xxTotNetExtnDiscAmt.setValue(hdrParam.xxTotNetExtnDiscAmt.getValue());
            param.xxTotInvDiscAmt.setValue(hdrParam.xxTotInvDiscAmt.getValue());
            param.xxTotInvExtnDiscAmt.setValue(hdrParam.xxTotInvExtnDiscAmt.getValue());
            param.xxDtlTotMatchFlg.setValue(hdrParam.xxDtlTotMatchFlg.getValue());
            param.xxDtlDiscFlg.setValue(hdrParam.xxDtlDiscFlg.getValue());
            param.xxDtlExtnDiscFlg.setValue(hdrParam.xxDtlExtnDiscFlg.getValue());
            param.xxDtlDiffAmt.setValue(hdrParam.xxDtlDiffAmt.getValue());
            debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            param.xxDtlDiffSetFlg.setValue(hdrParam.xxDtlDiffSetFlg.getValue());
        }
        debugLog("setRcptHdrDtl : end");
    }

    /**
     * Check Rcpt Dtl
     * @param msgMap S21ApiMessageMap
     * @param tmpDtlMap Map<String, Object>
     * @param onBatchType ONBATCH_TYPE
     */
    private void checkRcptDtl(S21ApiMessageMap msgMap, Map<String, Object> tmpDtlMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptDtl : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        if (NFCConst.CST_EXTN_DISC_FLG_ON.equals(param.xxCashDiscGetFlg.getValue())) {

            if (NFCConst.CST_FLAG_ON.equals(param.xxDtlTotMatchFlg.getValue())) {

                if (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue())) {

                    checkRcptDtlNetBalance(msgMap, onBatchType);
                }
                if (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue())) {

                    checkRcptDtlNetExtnBalance(msgMap, onBatchType);
                }
                if (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue()) && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxDiscGracePerFromDt.getValue())
                        && !NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxDiscGracePerThruDt.getValue()) && Integer.parseInt(param.batDt.getValue()) >= Integer.parseInt(param.xxDiscGracePerFromDt.getValue())
                        && Integer.parseInt(param.batDt.getValue()) <= Integer.parseInt(param.xxDiscGracePerThruDt.getValue())) {

                    checkRcptDtlInvBalance(msgMap, onBatchType);

                    if (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue())) {

                        checkRcptDtlInvExtnBalance(msgMap, onBatchType);
                    }
                }
                if (NFCConst.CST_EXTN_DISC_FLG_OFF.equals(param.xxDtlDiscFlg.getValue())) {

                    msgMap.addXxMsgId(NFZC301001.NFCM0540E);
                    param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
                }
                if (NFCConst.CST_EXTN_DISC_FLG_ON.equals(param.xxDtlDiscFlg.getValue())) {

                    // The Diff judges ADJ_TP by a plus or a minus
                    if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.xxDtlDiffAmt.getValue()) > 0) {

                        // ADJ_TP : C(MISCELLANEOUS_INCOME)
                        // Add SUM_DTL_DIFF_AMT
                        tmpDtlMap.put(SUM_DTL_DIFF_AMT, param.xxDtlDiffAmt.getValue().add((BigDecimal) tmpDtlMap.get(SUM_DTL_DIFF_AMT)));
                        // Reflect Apply Amount
                        param.dealApplyAmt.setValue(param.dealApplyAmt.getValue().subtract(param.xxDtlDiffAmt.getValue().multiply(NEGATE)));
                        param.xxIntfcInvApplyAmt.setValue(param.dealApplyAmt.getValue());
                        param.xxDtlDiffAmt.setValue(NFCConst.CST_DB_INIT_VAL_NUM);
                        debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
                    }
                }
            } else {

                addInvDiffAmt(msgMap, tmpDtlMap, onBatchType);
            }
        }
        debugLog("checkRcptDtl : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkDiff(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkDiff : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        if (!NFCConst.CST_EXTN_DISC_FLG_ON.equals(param.xxCashDiscGetFlg.getValue())) {

            if (NFCConst.CST_XX_PROC_CASE_TP_CD_CNT_BAL.equals(param.xxProcCaseTpCd.getValue()) && NFCConst.CST_XX_INV_NUM_GET_FLG_ON.equals(param.xxInvNumGetFlg.getValue())) {

                if (checkOfs(msgMap, onBatchType)) {
                    debugLog("checkDiff : end");
                    return;
                }
            }
        }
        debugLog("checkDiff : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkRcptHdrNetBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptHdrNetBalance : start");
        checkRcptHdrBalance(msgMap, CASH_DISC_APPLY_NET, onBatchType);
        debugLog("checkRcptHdrNetBalance : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkRcptHdrNetExtnBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptHdrNetExtnBalance : start");
        checkRcptHdrBalance(msgMap, CASH_DISC_APPLY_NET_EXTN, onBatchType);
        debugLog("checkRcptHdrNetExtnBalance : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkRcptHdrInvBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptHdrInvBalance : start");
        checkRcptHdrBalance(msgMap, CASH_DISC_APPLY_INV, onBatchType);
        debugLog("checkRcptHdrInvBalance : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkRcptHdrInvExtnBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptHdrInvExtnBalance : start");
        checkRcptHdrBalance(msgMap, CASH_DISC_APPLY_INV_EXTN, onBatchType);
        debugLog("checkRcptHdrInvExtnBalance : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkRcptDtlNetBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptDtlNetBalance : start");
        checkRcptDtlBalance(msgMap, CASH_DISC_APPLY_NET, onBatchType);
        debugLog("checkRcptDtlNetBalance : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkRcptDtlNetExtnBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptDtlNetExtnBalance : start");
        checkRcptDtlBalance(msgMap, CASH_DISC_APPLY_NET_EXTN, onBatchType);
        debugLog("checkRcptDtlNetExtnBalance : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkRcptDtlInvBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptDtlInvBalance : start");
        checkRcptDtlBalance(msgMap, CASH_DISC_APPLY_INV, onBatchType);
        debugLog("checkRcptDtlInvBalance : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private void checkRcptDtlInvExtnBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptDtlInvExtnBalance : start");
        checkRcptDtlBalance(msgMap, CASH_DISC_APPLY_INV_EXTN, onBatchType);
        debugLog("checkRcptDtlInvExtnBalance : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param cashDiscApplyCd CASH_DISC_APPLY_CD
     */
    private void checkRcptHdrBalance(S21ApiMessageMap msgMap, String cashDiscApplyCd, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptHdrBalance : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal tempRmngBalAmt = param.xxInvTotRmngGrsAmt.getValue();

        BigDecimal tempDiffAmt;
        BigDecimal tempTotDiscAmt;
        BigDecimal tempTolDiffAmt;
        if (CASH_DISC_APPLY_NET.equals(cashDiscApplyCd)) {
            tempTotDiscAmt = param.xxTotNetDiscAmt.getValue();
        } else if (CASH_DISC_APPLY_NET_EXTN.equals(cashDiscApplyCd)) {
            tempTotDiscAmt = param.xxTotNetExtnDiscAmt.getValue();
        } else if (CASH_DISC_APPLY_INV.equals(cashDiscApplyCd)) {
            tempTotDiscAmt = param.xxTotInvDiscAmt.getValue();
        } else {
            tempTotDiscAmt = param.xxTotInvExtnDiscAmt.getValue();
        }

        tempDiffAmt = tempRmngBalAmt.subtract(tempTotDiscAmt).subtract(param.xxHdrRmngBalAmt.getValue());
        if (tempDiffAmt.compareTo(STANDARD_AMT) == 0) {

            param.xxDtlDiffAmt.setValue(tempDiffAmt);
            debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
        } else if (tempDiffAmt.multiply(NEGATE).compareTo(STANDARD_AMT) < 0) {
            // 2010/04/27 H.Tokunaga update S80 start DefID 5807
            // tempTolDiffAmt = NFZC301001.account(tempRmngBalAmt,
            // param.nonOpsMiscPmtAmtPct.getValue(),
            // param.aftDeclPntDigitNum.getValue(),
            // param.roundMethCd.getValue());
            tempTolDiffAmt = NFZC301001.account(param.xxHdrRmngBalAmt.getValue(), param.nonOpsMiscPmtAmtPct.getValue(), param.aftDeclPntDigitNum.getValue(), param.roundMethCd.getValue());
            // 2010/04/27 H.Tokunaga update S80 end DefID 5807
            debugLog("Total Level Amt Percent Negate : " + tempTolDiffAmt.abs());
            debugLog("Total Level Amt Negate : " + param.nonOpsMiscPmtAmt.getValue().abs());
            debugLog("Diff Amt Negate : " + tempDiffAmt.abs());
            if (tempTolDiffAmt.abs().compareTo(tempDiffAmt.abs()) >= 0 && param.nonOpsMiscPmtAmt.getValue().abs().compareTo(tempDiffAmt.abs()) >= 0) {

                param.xxDtlDiffAmt.setValue(tempDiffAmt);
                param.xxDtlDiffSetFlg.setValue(NFCConst.CST_FLAG_ON);
                tempDiffAmt = NFCConst.CST_DB_INIT_VAL_NUM;
                debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            }
        } else if (tempDiffAmt.multiply(NEGATE).compareTo(STANDARD_AMT) > 0) {
            // 2010/04/27 H.Tokunaga update S80 start DefID 5807
            // tempTolDiffAmt = NFZC301001.account(tempRmngBalAmt,
            // param.miscIncPmtAmtPct.getValue(),
            // param.aftDeclPntDigitNum.getValue(),
            // param.roundMethCd.getValue());
            tempTolDiffAmt = NFZC301001.account(param.xxHdrRmngBalAmt.getValue(), param.miscIncPmtAmtPct.getValue(), param.aftDeclPntDigitNum.getValue(), param.roundMethCd.getValue());
            // 2010/04/27 H.Tokunaga update S80 end DefID 5807
            debugLog("Total Level Amt Percent Plus : " + tempTolDiffAmt.abs());
            debugLog("Total Level Amt Plus : " + param.miscIncPmtAmt.getValue().abs());
            debugLog("Diff Amt Plus : " + tempDiffAmt.abs());
            if (tempTolDiffAmt.abs().compareTo(tempDiffAmt.abs()) >= 0 && param.miscIncPmtAmt.getValue().abs().compareTo(tempDiffAmt.abs()) >= 0) {

                param.xxDtlDiffAmt.setValue(tempDiffAmt);
                param.xxDtlDiffSetFlg.setValue(NFCConst.CST_FLAG_ON);
                tempDiffAmt = NFCConst.CST_DB_INIT_VAL_NUM;
                debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            }
        }
        if (tempDiffAmt.compareTo(STANDARD_AMT) == 0) {

            param.xxDtlDiscFlg.setValue(NFCConst.CST_FLAG_ON);
            param.xxCashDiscApplyCd.setValue(cashDiscApplyCd);
        }
        debugLog("checkRcptHdrBalance : end");
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param cashDiscApplyCd CASH_DISC_APPLY_CD
     */
    private void checkRcptDtlBalance(S21ApiMessageMap msgMap, String cashDiscApplyCd, ONBATCH_TYPE onBatchType) {

        debugLog("checkRcptDtlBalance : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal tempRmngBalAmt = param.xxInvTrxRmngGrsAmt.getValue();
        BigDecimal tempDiffAmt;
        BigDecimal tempTotDiscAmt;
        BigDecimal tempTolDiffAmt;
        if (CASH_DISC_APPLY_NET.equals(cashDiscApplyCd)) {
            tempTotDiscAmt = param.xxNetDiscAmt.getValue();
        } else if (CASH_DISC_APPLY_NET_EXTN.equals(cashDiscApplyCd)) {
            tempTotDiscAmt = param.xxNetExtnDiscAmt.getValue();
        } else if (CASH_DISC_APPLY_INV.equals(cashDiscApplyCd)) {
            tempTotDiscAmt = param.xxInvDiscAmt.getValue();
        } else {
            tempTotDiscAmt = param.xxInvExtnDiscAmt.getValue();
        }

        tempDiffAmt = tempRmngBalAmt.subtract(tempTotDiscAmt).subtract(param.dealApplyAmt.getValue());
        if (tempDiffAmt.compareTo(STANDARD_AMT) == 0) {

            param.xxDtlDiffAmt.setValue(tempDiffAmt);
        } else if (tempDiffAmt.multiply(NEGATE).compareTo(STANDARD_AMT) < 0) {
            // 2010/04/27 H.Tokunaga update S80 start DefID 5807
            // tempTolDiffAmt = NFZC301001.account(tempRmngBalAmt,
            // param.nonOpsMiscInvAmtPct.getValue(),
            // param.aftDeclPntDigitNum.getValue(),
            // param.roundMethCd.getValue());
            tempTolDiffAmt = NFZC301001.account(param.xxHdrRmngBalAmt.getValue(), param.nonOpsMiscInvAmtPct.getValue(), param.aftDeclPntDigitNum.getValue(), param.roundMethCd.getValue());
            // 2010/04/27 H.Tokunaga update S80 end DefID 5807
            debugLog("Total Level Amt Percent Negate : " + tempTolDiffAmt.abs());
            debugLog("Total Level Amt Negate : " + param.nonOpsMiscInvAmt.getValue().abs());
            debugLog("Diff Amt Negate : " + tempDiffAmt.abs());
            if (tempTolDiffAmt.abs().compareTo(tempDiffAmt.abs()) >= 0 && param.nonOpsMiscInvAmt.getValue().abs().compareTo(tempDiffAmt.abs()) >= 0) {

                param.xxDtlDiffAmt.setValue(tempDiffAmt);
                param.xxDtlDiffSetFlg.setValue(NFCConst.CST_FLAG_OFF);
                tempDiffAmt = NFCConst.CST_DB_INIT_VAL_NUM;
                debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            }
        } else if (tempDiffAmt.multiply(NEGATE).compareTo(STANDARD_AMT) > 0) {
            // 2010/04/27 H.Tokunaga update S80 start DefID 5807
            // tempTolDiffAmt = NFZC301001.account(tempRmngBalAmt,
            // param.miscIncInvAmtPct.getValue(),
            // param.aftDeclPntDigitNum.getValue(),
            // param.roundMethCd.getValue());
            tempTolDiffAmt = NFZC301001.account(param.xxHdrRmngBalAmt.getValue(), param.miscIncInvAmtPct.getValue(), param.aftDeclPntDigitNum.getValue(), param.roundMethCd.getValue());
            // 2010/04/27 H.Tokunaga update S80 end DefID 5807

            debugLog("Total Level Amt Percent Plus : " + tempTolDiffAmt.abs());
            debugLog("Total Level Amt Plus : " + param.miscIncInvAmt.getValue().abs());
            debugLog("Diff Amt Plus : " + tempDiffAmt.abs());
            if (tempTolDiffAmt.abs().compareTo(tempDiffAmt.abs()) >= 0 && param.miscIncInvAmt.getValue().abs().compareTo(tempDiffAmt.abs()) >= 0) {

                param.xxDtlDiffAmt.setValue(tempDiffAmt);
                param.xxDtlDiffSetFlg.setValue(NFCConst.CST_FLAG_OFF);
                tempDiffAmt = NFCConst.CST_DB_INIT_VAL_NUM;
                debugLog("xxDtlDiffAmt : " + param.xxDtlDiffAmt.getValue());
            }
        }
        if (tempDiffAmt.compareTo(STANDARD_AMT) == 0) {

            param.xxDtlDiscFlg.setValue(NFCConst.CST_FLAG_ON);
            param.xxCashDiscApplyCd.setValue(cashDiscApplyCd);
            param.xxDtlDiscAmt.setValue(tempTotDiscAmt);
            debugLog("xxDtlDiscAmt : " + param.xxDtlDiscAmt.getValue());

            if (CASH_DISC_APPLY_NET_EXTN.equals(cashDiscApplyCd) || CASH_DISC_APPLY_INV_EXTN.equals(cashDiscApplyCd)) {

                param.xxDtlDiscPct.setValue(param.xxDtlExtnDiscPct.getValue());
            }
        }
        debugLog("checkRcptDtlBalance : end");
    }

    /**
     * Add Invoice Diff Amount
     * @param msgMap S21ApiMessageMap
     * @param tmpDtlMap Map<String, Object>
     * @param onBatchType ONBATCH_TYPE
     */
    private void addInvDiffAmt(S21ApiMessageMap msgMap, Map<String, Object> tmpDtlMap, ONBATCH_TYPE onBatchType) {

        debugLog("addInvDiffAmt : start");

        String tempDtlDiffAddFlg = (String) tmpDtlMap.get(DTL_DIFF_ADD_FLG);
        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (CASH_DISC_APPLY_NET.equals(param.xxCashDiscApplyCd.getValue())) {

            param.xxDtlDiscAmt.setValue(param.xxNetDiscAmt.getValue());
            debugLog("xxDtlDiscAmt : " + param.xxDtlDiscAmt.getValue());
        } else if (CASH_DISC_APPLY_NET_EXTN.equals(param.xxCashDiscApplyCd.getValue())) {

            param.xxDtlDiscAmt.setValue(param.xxNetExtnDiscAmt.getValue());
            debugLog("xxDtlDiscAmt : " + param.xxDtlDiscAmt.getValue());
            param.xxDtlDiscPct.setValue(param.xxDtlExtnDiscPct.getValue());
        } else if (CASH_DISC_APPLY_INV.equals(param.xxCashDiscApplyCd.getValue())) {

            param.xxDtlDiscAmt.setValue(param.xxInvDiscAmt.getValue());
            debugLog("xxDtlDiscAmt : " + param.xxDtlDiscAmt.getValue());
        } else if (CASH_DISC_APPLY_INV_EXTN.equals(param.xxCashDiscApplyCd.getValue())) {

            param.xxDtlDiscAmt.setValue(param.xxInvExtnDiscAmt.getValue());
            debugLog("xxDtlDiscAmt : " + param.xxDtlDiscAmt.getValue());
            param.xxDtlDiscPct.setValue(param.xxDtlExtnDiscPct.getValue());
        }
        param.dealApplyAmt.setValue(param.dealApplyAmt.getValue().subtract(param.xxDtlDiscAmt.getValue()));

        if (NFCConst.CST_DB_INIT_VAL_NUM.compareTo(param.xxDtlDiffAmt.getValue()) > 0) {

            // ADJ_TP : C(MISCELLANEOUS_INCOME)
            // When the balance of the receipt is subtracted, no check
            tempDtlDiffAddFlg = NFCConst.CST_FLAG_ON;
        } else {

            // ADJ_TP : B(NON_OPERATING_MISCELLANEOUS)
            if (NFCConst.CST_FLAG_ON.equals(param.xxDtlDiffSetFlg.getValue())) {

                if (NFCConst.CST_FLAG_OFF.equals(tempDtlDiffAddFlg)) {
                    // 2010/04/27 H.Tokunaga Add Start defid 6144
                    // BigDecimal applyAmt =
                    // param.dealApplyAmt.getValue().subtract(param.xxDtlDiffAmt.getValue());
                    BigDecimal applyAmt = param.dealApplyAmt.getValue();
                    // 2010/04/27 H.Tokunaga Add end defid 6144

                    // 2010/06/21 T.Tanaka Defid 7244
//                    if (applyAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) >= 0) {
                    if (applyAmt.compareTo(NFCConst.CST_DB_INIT_VAL_NUM) != 0) {

                        param.dealApplyAmt.setValue(applyAmt);
                        param.xxIntfcInvApplyAmt.setValue(applyAmt);
                        tempDtlDiffAddFlg = NFCConst.CST_FLAG_ON;
                    } else {

                        param.xxDtlDiffSetFlg.setValue(NFCConst.CST_FLAG_OFF);

                        if (param.xxDtlDiffAmt.getValue().compareTo(BigDecimal.ZERO) != 0) {
                            tempDtlDiffAddFlg = NFCConst.CST_FLAG_ON;
                        }

                    }
                } else {

                    param.xxDtlDiffSetFlg.setValue(NFCConst.CST_FLAG_OFF);
                }
            }
        }
        debugLog("addInvDiffAmt : end");
        tmpDtlMap.put(DTL_DIFF_ADD_FLG, tempDtlDiffAddFlg);
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkCashApplyBalance(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkCashApplyBalance : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal tempHdrRmngBalAmt = NFZC301001.subtractBalAmt(param.xxHdrRmngBalAmt.getValue(), param.xxTotInvApplyAmt.getValue(), param.xxTotAdjAmt.getValue(), param.xxTotDedctApplyAmt.getValue(), param.xxTotOnAcctCashAmt.getValue());
        if (tempHdrRmngBalAmt.compareTo(STANDARD_AMT) < 0) {
            debugLog("tempHdrRmngBalAmt : xxHdrRmngBalAmt(" + param.xxHdrRmngBalAmt.getValue() + ") - xxTotInvApplyAmt(" + param.xxTotInvApplyAmt.getValue() + ") - xxTotAdjAmt(" + param.xxTotAdjAmt.getValue() + ") - xxTotDedctApplyAmt("
                    + param.xxTotDedctApplyAmt.getValue() + ") - xxTotOnAcctCashAmt(" + param.xxTotOnAcctCashAmt.getValue() + ")");
            debugLog("tempHdrRmngBalAmt < STANDARD_AMT");
            debugLog("tempHdrRmngBalAmt : " + tempHdrRmngBalAmt);
            debugLog("STANDARD_AMT : " + STANDARD_AMT);
            msgMap.addXxMsgId(NFZC301001.NFCM0577E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("checkCashApplyBalance : end");
            return true;
        }
        debugLog("checkCashApplyBalance : end");
        return false;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkRf(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkRf : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (param.xxHdrRmngBalAmt.getValue().compareTo(param.xxTotRfAmt.getValue()) != 0) {

            msgMap.addXxMsgId(NFZC301001.NFCM0546E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("checkRf : end");
            return true;
        }
        debugLog("checkRf : end");
        return false;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    private boolean checkOfs(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("checkOfs : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        BigDecimal tempAmt = param.xxInvTrxRmngGrsAmt.getValue().subtract(param.xxInvTotCrAmt.getValue().multiply(NEGATE));
        if (tempAmt.compareTo(STANDARD_AMT) < 0) {

            msgMap.addXxMsgId(NFZC301001.NFCM0545E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ERR);
            debugLog("checkOfs : end");
            return true;
        }
        debugLog("checkOfs : end");
        return false;
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
