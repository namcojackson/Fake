/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.AR_RCPT_UN_APPLYTMsg;
import business.db.AR_RCPT_UN_APPLYTMsgArray;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC306001.NFCNumbering;
import com.canon.cusa.s21.common.NFX.NFXC306001.NFXC3060Bean;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFCCurrencyConversion;
import com.canon.cusa.s21.common.NFX.NFXC307001.NFXC3070Bean;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * 
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 727
 * 11/12/2009   Canon           Y.Kondo         Update          DefID 1504
 *</pre>
 */
public class NFCCashApplyUpdateArRcptUnApply extends S21ApiCommonBase {

    /** Map Key : AR_CASH_APP_PK. */
    private static final String GLBL_CMPY_CD = "glblCmpyCd";

    /** Map Key : AR_CASH_APP_SQ_NUM. */
    private static final String RCPT_NUM = "rcptNum";

    /** HEAD. */
    private static final int HEAD = 1;

    /** HEAD INDEX. */
    private static final int HEAD_INDEX = 0;

    /** BigDecimal : NAGATE. */
    private static final BigDecimal NEGATE = BigDecimal.valueOf(-1);

    /**
     */
    public NFCCashApplyUpdateArRcptUnApply() {
        super();
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @return unApplyT AR_RCPT_UN_APPLYTMsg
     */
    protected AR_RCPT_UN_APPLYTMsg execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType, AR_RCPT_UN_APPLYTMsg unApplyT) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        unApplyT = updateCashApplyArRcptUnApply(msgMap, onBatchType, unApplyT);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        debugLog("execute : end");
        return unApplyT;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     */
    protected void execute(final List<NFZC301002PMsg> params, final ONBATCH_TYPE onBatchType) {

        AR_RCPT_UN_APPLYTMsg unApplyT = null;

        for (NFZC301002PMsg msg : params) {
            unApplyT = execute(msg, onBatchType, unApplyT);
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
     * @return unApplyT AR_RCPT_UN_APPLYTMsg
     */
    protected AR_RCPT_UN_APPLYTMsg updateCashApplyArRcptUnApply(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType, AR_RCPT_UN_APPLYTMsg unApplyT) {

        debugLog("updateCashApplyArRcptUnApply : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_XX_PROC_TP_CD_NON.equals(param.xxRcptUnProcTpCd.getValue())
                && NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(param.xxHdrNumGetFlg.getValue())) {
            unApplyT = setArRcptUnApply(msgMap, unApplyT);
        }
        debugLog("updateCashApplyArRcptUnApply : end");
        return unApplyT;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return budgetUnApplyT AR_RCPT_UN_APPLYTMsg
     */
    private AR_RCPT_UN_APPLYTMsg setArRcptUnApply(S21ApiMessageMap msgMap, AR_RCPT_UN_APPLYTMsg unApplyTLast) {

        debugLog("setArRcptUnApply : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_RCPT_UN_APPLYTMsg budgetUnApplyT = null;

        AR_RCPT_UN_APPLYTMsgArray unApplyTArray = findByConditionArRcptUnApply(msgMap);
        if (unApplyTArray.getValidCount() != HEAD) {

            budgetUnApplyT = editArRcptUnApplyNewRcptBudget(msgMap);

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                budgetUnApplyT = numberingArCashAppPk(msgMap, budgetUnApplyT);
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                insArRcptUnApply(msgMap, budgetUnApplyT);
            }
        } else {

            AR_RCPT_UN_APPLYTMsg deficitUnApplyT = editArRcptUnApplyRcptDeficit(msgMap, unApplyTArray.no(HEAD_INDEX));

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                deficitUnApplyT = numberingArCashAppPk(msgMap, deficitUnApplyT);
            }

            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                if (unApplyTArray.no(HEAD_INDEX).dealUnApplyAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) <= 0 && param.xxHdrRmngBalAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) == 0) {

                    deficitUnApplyT.arCashAppSqNum.setValue(NFZC301001.INIT_CASH_APP_SQ_NUM);
                } else {

                    insArRcptUnApply(msgMap, deficitUnApplyT);
                }
            }
            if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && param.xxHdrTrxRmngGrsAmt.getValue().compareTo(NFCConst.CST_DB_INIT_VAL_NUM) != 0) {

                budgetUnApplyT = editArRcptUnApplyApplyRcptBudget(msgMap, deficitUnApplyT);

                if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap))) {

                    insArRcptUnApply(msgMap, budgetUnApplyT);
                }
            }
        }
        debugLog("setArRcptUnApply : end");
        return budgetUnApplyT;
    }

    /**
     * 
     * <pre>
     * Edit AR_RCPT_UN_APPLY New Receipt Budget
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return unApplyT AR_RCPT_UN_APPLYTMsg
     */
    private AR_RCPT_UN_APPLYTMsg editArRcptUnApplyNewRcptBudget(S21ApiMessageMap msgMap) {

        debugLog("editArRcptUnApplyNewRcptBudget : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal hdrTrxRmngGrsAmt = param.xxHdrTrxRmngGrsAmt.getValue();
        AR_RCPT_UN_APPLYTMsg unApplyT = new AR_RCPT_UN_APPLYTMsg();
        NFCCurrencyConversion afxc307001 = new NFCCurrencyConversion();
        NFXC3070Bean currencyData = afxc307001.convertCurrency(param.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), hdrTrxRmngGrsAmt, param.xxHdrTrxGlDt.getValue(), null);
        if (!NFCConst.CST_RTN_CD_NORM.equals(currencyData.getRtrnCd())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArRcptUnApplyNewRcptBudget : end");
            return unApplyT;
        }
        unApplyT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        unApplyT.arCashAppSqNum.setValue(NFZC301001.zeroPadding(1, NFZC301001.FIVE_FIGURE));
        unApplyT.rcptNum.setValue(param.rcptNum.getValue());
        unApplyT.dealUnApplyAmt.setValue(hdrTrxRmngGrsAmt);
        unApplyT.funcUnApplyAmt.setValue(currencyData.getFuncAmt());
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxHdrTrxPayerCustCd.getValue())) {
            unApplyT.arUnApplyTpCd.setValue(NFCConst.CST_AR_UN_APPLY_TP_CD_UNIDENTIFED);
        } else {
            unApplyT.arUnApplyTpCd.setValue(NFCConst.CST_AR_UN_APPLY_TP_CD_ON_ACCOUNT);
        }
        unApplyT.glDt.setValue(param.xxHdrTrxGlDt.getValue());
        unApplyT.cashAppDt.setValue(param.batDt.getValue());
        unApplyT.arUnApplyStsCd.setValue(NFCConst.CST_AR_UN_APPLY_STS_CD_NEW);
        unApplyT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        unApplyT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArRcptUnApplyNewRcptBudget : end");
        return unApplyT;
    }

    /**
     * 
     * <pre>
     * Edit AR_RCPT_UN_APPLY Receipt Deficit
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param unApplyTLast AR_RCPT_UN_APPLYTMsg
     * @return unApplyT AR_RCPT_UN_APPLYTMsg
     */
    private AR_RCPT_UN_APPLYTMsg editArRcptUnApplyRcptDeficit(S21ApiMessageMap msgMap, AR_RCPT_UN_APPLYTMsg unApplyTLast) {

        debugLog("editArRcptUnApplyRcptDeficit : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_RCPT_UN_APPLYTMsg unApplyT = new AR_RCPT_UN_APPLYTMsg();

        unApplyT.glblCmpyCd.setValue(unApplyTLast.glblCmpyCd.getValue());
        unApplyT.arCashAppSqNum.setValue(NFZC301001.zeroPadding(1, NFZC301001.FIVE_FIGURE));
        unApplyT.rcptNum.setValue(unApplyTLast.rcptNum.getValue());
        unApplyT.dealUnApplyAmt.setValue(unApplyTLast.dealUnApplyAmt.getValue().multiply(NEGATE));
        unApplyT.funcUnApplyAmt.setValue(unApplyTLast.funcUnApplyAmt.getValue().multiply(NEGATE));
        unApplyT.arUnApplyTpCd.setValue(unApplyTLast.arUnApplyTpCd.getValue());
        if (!NFCConst.CST_DB_INIT_VAL_STR.equals(param.cashAppGlDt.getValue())) {
            unApplyT.glDt.setValue(param.cashAppGlDt.getValue());
        } else {
            unApplyT.glDt.setValue(param.xxHdrTrxGlDt.getValue());
        }
        unApplyT.cashAppDt.setValue(param.batDt.getValue());
        if (NFCConst.CST_XX_PROC_CASE_TP_CD_RF.equals(param.xxProcCaseTpCd.getValue())) {
            unApplyT.arUnApplyStsCd.setValue(NFCConst.CST_AR_UN_APPLY_STS_CD_RF);
        } else if (NFCConst.CST_XX_PROC_CASE_TP_CD_CANC_RCPT.equals(param.xxProcCaseTpCd.getValue())) {
            unApplyT.arUnApplyStsCd.setValue(NFCConst.CST_AR_UN_APPLY_STS_CD_VOID);
        } else {
            unApplyT.arUnApplyStsCd.setValue(NFCConst.CST_AR_UN_APPLY_STS_CD_CASH_CANC);
        }
        unApplyT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        unApplyT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArRcptUnApplyRcptDeficit : end");
        return unApplyT;
    }

    /**
     * 
     * <pre>
     * Edit AR_RCPT_UN_APPLY Receipt Budget
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param unApplyT AR_RCPT_UN_APPLYTMsg
     * @return unApplyT AR_RCPT_UN_APPLYTMsg
     */
    private AR_RCPT_UN_APPLYTMsg editArRcptUnApplyApplyRcptBudget(S21ApiMessageMap msgMap, AR_RCPT_UN_APPLYTMsg unApplyTLast) {

        debugLog("editArRcptUnApplyApplyRcptBudget : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        BigDecimal hdrTrxRmngGrsAmt = param.xxHdrTrxRmngGrsAmt.getValue();
        AR_RCPT_UN_APPLYTMsg unApplyT = new AR_RCPT_UN_APPLYTMsg();
        NFCCurrencyConversion afxc307001 = new NFCCurrencyConversion();
        NFXC3070Bean currencyData = afxc307001.convertCurrency(unApplyTLast.glblCmpyCd.getValue(), param.dealCcyCd.getValue(), hdrTrxRmngGrsAmt, unApplyTLast.glDt.getValue(), null);
        if (!NFCConst.CST_RTN_CD_NORM.equals(currencyData.getRtrnCd())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0550E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("editArRcptUnApplyApplyRcptBudget : end");
            return unApplyT;
        }
        unApplyT.glblCmpyCd.setValue(unApplyTLast.glblCmpyCd.getValue());
        unApplyT.arCashAppPk.setValue(unApplyTLast.arCashAppPk.getValue());
        int nextSqNum = Integer.parseInt(unApplyTLast.arCashAppSqNum.getValue());
        nextSqNum++;
        unApplyT.arCashAppSqNum.setValue(NFZC301001.zeroPadding(nextSqNum, NFZC301001.FIVE_FIGURE));
        unApplyT.rcptNum.setValue(unApplyTLast.rcptNum.getValue());
        unApplyT.dealUnApplyAmt.setValue(hdrTrxRmngGrsAmt);
        unApplyT.funcUnApplyAmt.setValue(currencyData.getFuncAmt());
        if (NFCConst.CST_DB_INIT_VAL_STR.equals(param.xxHdrTrxPayerCustCd.getValue())) {
            unApplyT.arUnApplyTpCd.setValue(NFCConst.CST_AR_UN_APPLY_TP_CD_UNIDENTIFED);
        } else {
            unApplyT.arUnApplyTpCd.setValue(NFCConst.CST_AR_UN_APPLY_TP_CD_ON_ACCOUNT);
        }
        if (!NFCConst.CST_DB_INIT_VAL_STR.equals(param.cashAppGlDt.getValue())) {
            unApplyT.glDt.setValue(param.cashAppGlDt.getValue());
        } else {
            unApplyT.glDt.setValue(param.xxHdrTrxGlDt.getValue());
        }
        unApplyT.cashAppDt.setValue(param.batDt.getValue());
        unApplyT.arUnApplyStsCd.setValue(NFCConst.CST_AR_UN_APPLY_STS_CD_CASH);
        unApplyT.ajeCratCpltFlg.setValue(NFCConst.CST_FLAG_OFF);
        unApplyT.ajeCratVrsnNum.setValue(NFCConst.CST_DB_INIT_VAL_STR);

        debugLog("editArRcptUnApplyApplyRcptBudget : end");
        return unApplyT;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @return unApplyTArray AR_RCPT_UN_APPLYTMsgArray
     */
    private AR_RCPT_UN_APPLYTMsgArray findByConditionArRcptUnApply(S21ApiMessageMap msgMap) {

        debugLog("findByConditionArRcptUnApply : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        map.put(RCPT_NUM, param.rcptNum.getValue());

        AR_RCPT_UN_APPLYTMsgArray unApplyTArray = new AR_RCPT_UN_APPLYTMsgArray();
        unApplyTArray.setMsgList(new AR_RCPT_UN_APPLYTMsg[HEAD]);

        NFZC301001Query.getInstance().getArRcptUnApply(map, unApplyTArray);

        debugLog("findByConditionArRcptUnApply : end");
        return unApplyTArray;
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param unApplyT AR_RCPT_UN_APPLYTMsg
     */
    private void insArRcptUnApply(S21ApiMessageMap msgMap, AR_RCPT_UN_APPLYTMsg unApplyT) {

        debugLog("insArRcptUnApply : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        S21ApiTBLAccessor.insert(unApplyT);
        debugLog("AR_RCPT_UN_APPLY : INSERT RETURN CODE : " + unApplyT.getReturnCode());
        if (!NFCConst.CST_EZ_RETURN_CODE_CPLT.equals(unApplyT.getReturnCode())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0559E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
            debugLog("AR_RCPT_UN_APPLY : INSERT ERROR");
            debugLog("GLBL_CMPY_CD : " + unApplyT.glblCmpyCd.getValue());
            debugLog("AR_CASH_APP_PK : " + unApplyT.arCashAppPk.getValue());
        } else {

            param.arCashAppPk.setValue(unApplyT.arCashAppPk.getValue());
        }
        debugLog("insArRcptUnApply : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param unApplyT AR_RCPT_UN_APPLYTMsg
     * @return unApplyT AR_RCPT_UN_APPLYTMsg
     */
    private AR_RCPT_UN_APPLYTMsg numberingArCashAppPk(S21ApiMessageMap msgMap, AR_RCPT_UN_APPLYTMsg unApplyT) {

        debugLog("numberingArCashAppPk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        NFCNumbering numbering = new NFCNumbering();
        NFXC3060Bean numberBean = numbering.getNumber(NFCConst.CST_SEQ_ID_AR_CASH_APP, null, 0);
        if (!NFCConst.CST_RTN_CD_NORM.equals(numberBean.getRtrnNo())) {

            msgMap.addXxMsgId(NFZC301001.NFCM0530E);
            param.setReturnCode(NFCConst.CST_NFZC301001_RTN_CD_ETCERR);
        } else {
            unApplyT.arCashAppPk.setValue(numberBean.getOraSqNo());
        }
        debugLog("numberingArCashAppPk : end");
        return unApplyT;
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
