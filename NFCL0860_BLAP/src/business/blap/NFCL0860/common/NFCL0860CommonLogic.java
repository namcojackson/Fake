/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL0860.common;

import static business.blap.NFCL0860.constant.NFCL0860Constant.CREDITMEMO;
import static business.blap.NFCL0860.constant.NFCL0860Constant.CRM;
import static business.blap.NFCL0860.constant.NFCL0860Constant.INV;
import static business.blap.NFCL0860.constant.NFCL0860Constant.INVOICE;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL0860.NFCL0860CMsg;
import business.blap.NFCL0860.NFCL0860_ACMsg;
import business.db.AR_APPLY_INTFC_WRKTMsg;
import business.db.AR_CASH_APPTMsg;
import business.db.AR_RCPTTMsg;
import business.db.AR_TRX_BALTMsg;
import business.parts.NFZC202001PMsg;

import com.canon.cusa.s21.api.NFC.NFZC202001.NFZC202001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.log.S21MessageFunc;


/**
 *<pre>
 * NFCL0860CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/1/22    Fujitsu         S.Fujita        Create          N/A
 * 2018/09/20   Fujitsu         T.Ogura         Update          QC#28097
 * 2019/09/03   Fujitsu         H.Ikeda         Update          QC#53152
 * 2020/01/28   Fujitsu         H.Ikeda         Update          QC#55633
 *</pre>
 */
public class NFCL0860CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);
        int startIndex = 0;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
    }

    /**
     * @param bizMsg NFCL0860CMsg
     * @param arTrxBalPk BigDecimal
     * @return AR_TRX_BALTMsg
     */
    public static AR_TRX_BALTMsg findArTrxBalInfo(NFCL0860CMsg bizMsg, BigDecimal arTrxBalPk) {

        AR_TRX_BALTMsg inArTrxBalMsg = new AR_TRX_BALTMsg();

        inArTrxBalMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArTrxBalMsg.arTrxBalPk.setValue(arTrxBalPk);

        AR_TRX_BALTMsg outMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inArTrxBalMsg);
        return outMsg;
    }

    /**
     * @param bizMsg NFCL0860CMsg
     * @param rcptNum String
     * @return AR_RCPTTMsg
     */
    public static AR_RCPTTMsg findArRcptInfo(NFCL0860CMsg bizMsg, String rcptNum) {

        AR_RCPTTMsg inArRcptMsg = new AR_RCPTTMsg();

        inArRcptMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inArRcptMsg.rcptNum.setValue(rcptNum);

        AR_RCPTTMsg outMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKey(inArRcptMsg);
        return outMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param param BigDecimal
     * @param scale int
     * @return BigDecimal
     */
    public static BigDecimal convertZero(BigDecimal param, int scale) {

        BigDecimal retVal = null;

        // param == null
        if (!ZYPCommonFunc.hasValue(param)) {
            retVal = BigDecimal.ZERO;
        } else {
            retVal = getRoundDown(param, scale);
        }
        return retVal;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param param BigDecimal
     * @param scale int
     * @return BigDecimal
     */
    public static BigDecimal getRoundDown(BigDecimal param, int scale) {

        BigDecimal retVal = param;

        if (ZYPCommonFunc.hasValue(param)) {
            retVal = param.setScale(scale, BigDecimal.ROUND_HALF_DOWN);

        }
        return retVal;
    }

    /**
     * @param inMsg AR_APPLY_INTFC_WRKTMsg
     * @return AR_APPLY_INTFC_WRKTMsg
     */
    public static AR_APPLY_INTFC_WRKTMsg createArApplyIntfcWrk(AR_APPLY_INTFC_WRKTMsg inMsg) {

        if (!ZYPCommonFunc.hasValue(inMsg.dealApplyAmt)) {
            ZYPEZDItemValueSetter.setValue(inMsg.dealApplyAmt, BigDecimal.ZERO);
        }

        EZDTBLAccessor.create(inMsg);
        return inMsg;
    }

    /**
     * @param bizMsg NFCL0860CMsg
     * @param inMsg AR_RCPTTMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @return AR_RCPTTMsg
     */
    public static AR_RCPTTMsg updateArRcptInfo(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA, AR_RCPTTMsg inMsg) {

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.rcptNum.setValue(bizMsgA.rcptNum_R1.getValue());

        AR_RCPTTMsg resultMsg = (AR_RCPTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (resultMsg == null) {
            return null;
        }

        resultMsg.arCashApplyStsCd.setValue(AR_CASH_APPLY_STS.VOID);
        resultMsg.voidFlg.setValue("Y");
        resultMsg.arRcptVoidRsnCd.clear();
        resultMsg.voidDt.setValue(bizMsgA.acctDt_AD.getValue());
        resultMsg.voidFirstCmntTxt.clear();
        resultMsg.voidScdCmntTxt.clear();
        resultMsg.voidGlDt.setValue(bizMsgA.acctDt_AD.getValue());
        // START 2018/09/20 T.Ogura [QC#28097,ADD]
        resultMsg.arRcptStsCd.setValue(AR_RCPT_STS.REVERSED);
        // END   2018/09/20 T.Ogura [QC#28097,ADD]

        EZDTBLAccessor.update(resultMsg);

        return resultMsg;
    }

    /**
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @param inMsg AR_TRX_BALTMsg
     * @return AR_TRX_BALTMsg
     */
    public static AR_TRX_BALTMsg updateArTrxBalLInfo(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA, AR_TRX_BALTMsg inMsg) {

        inMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        inMsg.arTrxBalPk.setValue(bizMsgA.arTrxBalPk_B2.getValue());

        AR_TRX_BALTMsg resultMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (resultMsg == null) {
            return null;
        }

        resultMsg.arCashApplyStsCd.setValue(AR_CASH_APPLY_STS.VOID);
        resultMsg.cashAppDt.setValue(inMsg.cashAppDt.getValue());

        EZDTBLAccessor.update(resultMsg);

        return resultMsg;
    }

    // START 2019/09/03 H.Ikeda [QC#53152, ADD]
    /**
     * update AR_TRX_BAL AR_AUTO_PURGE_OfS_FLG
     * 
     * @param glblCmpyCd
     * @param arTrxBalPk
     * @return
     */
    public static AR_TRX_BALTMsg updateArTrxBalArAutoPurgeOfsFlg(String glblCmpyCd, BigDecimal arTrxBalPk) {

        AR_TRX_BALTMsg resultMsg = null;

        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.arTrxBalPk.setValue(arTrxBalPk);
        resultMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        if (resultMsg != null) {
            if (ZYPCommonFunc.hasValue(resultMsg.arTrxTpCd) && AR_TRX_TP.CREDIT_MEMO.equals(resultMsg.arTrxTpCd.getValue())) {
                if (ZYPCommonFunc.hasValue(resultMsg.arAutoPurgeOfsFlg) && ZYPConstant.FLG_ON_Y.equals(resultMsg.arAutoPurgeOfsFlg.getValue())) {
                    resultMsg.arAutoPurgeOfsFlg.setValue(ZYPConstant.FLG_OFF_N);
                    EZDTBLAccessor.update(resultMsg);
                }
            }
        }
        return resultMsg;
    }
    // END   2019/09/03 H.Ikeda [QC#53152, ADD]

    /**
     * @param inMsg AR_CASH_APPTMsg
     * @return AR_CASH_APPTMsg
     */
    public static AR_CASH_APPTMsg updateArCashAppInfoExclusive(AR_CASH_APPTMsg inMsg) {

        AR_CASH_APPTMsg resultMsg = (AR_CASH_APPTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

        if (null == resultMsg) {
            return null;

        }
        if (!ZYPDateUtil.isSameTimeStamp(inMsg.ezUpTime.getValue(), inMsg.ezUpTimeZone.getValue(), resultMsg.ezUpTime.getValue(), resultMsg.ezUpTimeZone.getValue())) {
            return null;

        }

        resultMsg.glblCmpyCd.setValue(inMsg.glblCmpyCd.getValue());
        resultMsg.arCashAppPk.setValue(inMsg.arCashAppPk.getValue());
        resultMsg.arCashAppSqNum.setValue(inMsg.arCashAppSqNum.getValue());
        resultMsg.arScrCancFlg.setValue(inMsg.arScrCancFlg.getValue());

        EZDTBLAccessor.update(resultMsg);

        return resultMsg;
    }

    /**
     * @param bizMsg NFCL0860CMsg
     * @return String
     */
    public static String getErrMsgName(NFCL0860CMsg bizMsg) {

        String errMsg = "";
        if (bizMsg.arTrxTpCd_AB.getValue().equals(INV)) {
            errMsg = INVOICE;
        } else if (bizMsg.arTrxTpCd_AB.getValue().equals(CRM)) {
            errMsg = CREDITMEMO;
        }
        return errMsg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param btPrDt String
     * @return boolean
     */
    public static boolean callCreditProfileUpdateApiHeader(NFCL0860CMsg bizMsg, String btPrDt) {
        // Credit Profile Update (Balance)
        boolean rtnFlg = true;
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsg = new NFZC202001PMsg();

        // Header
        paramMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        // START 2020/01/28 H.Ikeda [QC#55633, MOD]
        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd_AB)) {
            paramMsg.xxModeCd.setValue(NFZC202001.MODE_BILL_TO_CUST);
            paramMsg.billToCustCd.setValue(bizMsg.billToCustCd_AB.getValue());
        } else {
            paramMsg.xxModeCd.setValue(NFZC202001.MODE_CUST_ACCT);
            paramMsg.sellToCustCd.setValue(bizMsg.billToCustAcctCd_AB.getValue());
        }
        // END   2020/01/28 H.Ikeda [QC#55633, MOD]
        paramMsg.procDt.setValue(btPrDt);

        crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(paramMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
            S21ApiMessage msg = msgList.get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            bizMsg.setMessageInfo("NFCM0796E", new String[] {getRtnMsg(msgId, msgPrms)});
            rtnFlg = false;
        }
        return rtnFlg;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFCL0860CMsg
     * @param bizMsgA NFCL0860_ACMsg
     * @param btPrDt String
     * @return boolean
     */
    public static boolean callCreditProfileUpdateApiDetail(NFCL0860CMsg bizMsg, NFCL0860_ACMsg bizMsgA, String btPrDt) {

        // Credit Profile Update (Balance)
        boolean rtnFlg = true;
        NFZC202001 crPrflUpdApi = new NFZC202001();
        NFZC202001PMsg paramMsg = new NFZC202001PMsg();

        // Detail
        paramMsg.glblCmpyCd.setValue(bizMsg.glblCmpyCd.getValue());
        // START 2020/01/28 H.Ikeda [QC#55633, MOD]
        if (ZYPCommonFunc.hasValue(bizMsgA.billToCustCd_B1)) {
            paramMsg.xxModeCd.setValue(NFZC202001.MODE_BILL_TO_CUST);
            paramMsg.billToCustCd.setValue(bizMsgA.billToCustCd_B1.getValue());
        } else {
            paramMsg.xxModeCd.setValue(NFZC202001.MODE_CUST_ACCT);
            paramMsg.sellToCustCd.setValue(bizMsgA.billToCustAcctCd_B1.getValue());
        }
        // END   2020/01/28 H.Ikeda [QC#55633, MOD]
        paramMsg.procDt.setValue(btPrDt);
        crPrflUpdApi.execute(paramMsg, ONBATCH_TYPE.ONLINE);

        if (S21ApiUtil.isXxMsgId(paramMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
            S21ApiMessage msg = msgList.get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            bizMsg.setMessageInfo("NFCM0796E", new String[] {getRtnMsg(msgId, msgPrms)});
            rtnFlg = false;
        }
        return rtnFlg;
    }

    /**
     * Get Return Message
     * @param msgId String
     * @param prm String[]
     * @return String
     */
    public static String getRtnMsg(String msgId, String[] prm) {
        String rtnVal = "";
        if (ZYPCommonFunc.hasValue(msgId)) {
            rtnVal = S21MessageFunc.clspGetMessage(msgId, prm);
            rtnVal = rtnVal.substring(msgId.length()).trim();
        }
        return rtnVal;
    }

    // START 2020/01/28 H.Ikeda [QC#55633, ADD]
    /**
     * Get AR_TRX_BAL
     * 
     * @param  glblCmpyCd String
     * @param  arTrxBalPk Bigdecimal
     * @return AR_TRX_BALTMsg
     */
    public static AR_TRX_BALTMsg getArTrxBalInfo(String glblCmpyCd, BigDecimal arTrxBalPk) {

        AR_TRX_BALTMsg inMsg = new AR_TRX_BALTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.arTrxBalPk.setValue(arTrxBalPk);

        AR_TRX_BALTMsg resultMsg = (AR_TRX_BALTMsg) EZDTBLAccessor.findByKey(inMsg);

        if (resultMsg == null) {
            return null;
        }

        return resultMsg;
    }
    // END   2020/01/28 H.Ikeda [QC#55633, ADD]
}
