/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC600001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.getBigDecimal;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDAbendException;
import parts.common.EZDDebugOutput;
import parts.common.EZDValidatorException;
import business.db.CR_BAL_HISTTMsg;
import business.db.CUST_CR_PRFLTMsg;
import business.db.CUST_CR_PRFLTMsgArray;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_CR_BAL_HISTTMsg;
import business.parts.NMZC600001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC600001.cache.DataCacheForSSM;
import com.canon.cusa.s21.api.NMZ.NMZC600001.cache.VarcharConstCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Credit Balance is updated.
 * 
 * 1.Order Entry
 *  Order Amount is summed up as In Process AMT.
 *  Order Amount of the difference is summed up as In ProcessAMT at Order Change Order Cancel.
 * 
 * 2.Invoice
 *  Invoiced Amount actually claimed when the bill is issued is summed up as AR Balance AMT.
 *  Moreover, Order Amount when receiving an order claiming it is summed up as In Process AMT.
 * 
 * 3.Receipt
 *  Receipt Amount is summed up as AR Balance AMT.
 * 
 * 4.Adjustment/Cash Discount
 *  When payment is erased, amount of money Receipt AMT of erasing is summed up as AR Balance AMT.
 * 
 * Amount of money (In Process AMT + AR Balance AMT) summed up from Credit Limit Amount*1 is subtracted,
 * and Credit Balance Amount is calculated, and updated.
 * 
 * *1 Seasonal Credit Amount is added to Credit Limit Amount about Credit Limit Amount
 *    if the sales date is in the Seasonal Credit Limit expiration date.
 * 
 * After Credit Balance is updated, the history information is registered in the CR_BAL_HIST table.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/29/2009   Fujitsu         S.Sugino        Create          N/A
 * 03/18/2011   Fujitsu         R.Watanabe      Update          1481(PRD)
 * 09/30/2015   Fujitsu         C.Tanaka        Update          CSA
 * 01/22/2016   Fujitsu         N.Sugiura       Update          CSA-QC#3013
 * 08/03/2016   Fujitsu         T.Yoshida       Update          CSA-QC#12894
 * 08/10/2016   Fujitsu         T.Yoshida       Update          CSA-QC#3834
 * 09/01/2016   Fujitsu         T.Yoshida       Update          CSA-QC#10321
 * 10/05/2016   Fujitsu         H.Nagashima     Update          CSA-QC#14972
 * 10/07/2016   Fujitsu         H.Nagashima     Update          CSA-QC#14647
 * 02/15/2017   Fujitsu         K.Ishizuka      Update          CSA-QC#17321
 * 02/22/2017   Fujitsu         H.Nagashima     Update          CSA-QC#17252
 * 12/19/2017   Hitachi         E.Kameishi      Update          CSA-QC#22096
 * 04/13/2018   Hitachi         E.Kameishi      Update          CSA-QC#25426
 * 05/22/2018   Fujitsu         T.Aoi           Update          CSA-QC#21841
 * 06/22/2018   Fujitsu         Y.Matsui        Update          CSA-QC#26026
 * 08/03/2018   Fujitsu         Y.Kanefusa      Update          S21_NA#27479
 * </pre>
 */
public class NMZC600001 extends S21ApiCommonBase {

    // Start 2015/09/30 C.Tanaka Add
    /**
     * Mode: Update profile for Account (01)
     */
    public static final String MODE_ACCT = "01";

    /**
     * Mode: Update profile for Bill to (02)
     */
    public static final String MODE_BILL = "02";

    /**
     * Mode: Update profile for All (03)
     */
    public static final String MODE_ALL = "03";

    /**
     * Mode is invalid.
     */
    public static final String NMZM0024E = "NMZM0024E";

    // End 2015/09/30 C.Tanaka Add

    /**
     * It is message ID set when the global company code is a
     * unsetting.
     */
    public static final String NMZM0001E = "NMZM0001E";

    /**
     * It is message ID set when the Account Code(Bill to Customer
     * Code) is a unsetting.
     */
    public static final String NMZM0004E = "NMZM0004E";

    /**
     * It failed in the update of Credit Profile.
     */
    public static final String NMZM0005E = "NMZM0005E";

    /**
     * It failed in the insert of Credit Balance History.
     */
    public static final String NMZM0006E = "NMZM0006E";

    /**
     * It is message ID set when the sales date is a unsetting.
     */
    public static final String NMZM0007E = "NMZM0007E";

    /**
     * It is message ID set when the amount of money overflows.
     */
    public static final String NMZM0008E = "NMZM0008E";

    // DEL START S21_NA #17321 
//    /**
//     * Credit Check Required Type:No Check (1)
//     */
//    public static final String CR_CHK_REQ_TP_NO_CHECK = CR_CHK_REQ_TP.NO_CHECK;
//
//    /**
//     * Credit Check Required Type:Hold (2)
//     */
//    public static final String CR_CHK_REQ_TP_HOLD = CR_CHK_REQ_TP.HOLD;
//
//    /**
//     * Credit Check Required Type:CreditLimit (3)
//     */
//    public static final String CR_CHK_REQ_TP_CREDIT_LIMIT = CR_CHK_REQ_TP.CREDIT_LIMIT;
    // DEL END S21_NA #17321

    /**
     * Database Access Return Code:normal
     */
    private static final String RTNCD_NORMAL = "0000";

    /**
     * Oracle Sequence Name:CR_BAL_HIST
     */
    private static final String CR_BAL_HIST_SQ = ZYPOracleSeqConstant.CR_BAL_HIST_SQ;

    // Start 2015/09/30 C.Tanaka Add
    /**
     * Oracle Sequence Name:DS_CR_BAL_HIST
     */
    private static final String DS_CR_BAL_HIST_SQ = ZYPOracleSeqConstant.DS_CR_BAL_HIST_SQ;

    /**
     * CPO Detail Line Sub Number
     */
    private static final String CPO_DTL_LINE_SUB_NUM = "000";

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmClient = null;

    // End 2015/09/30 C.Tanaka Add
    //QC#14647 add Start
    /** PMT_TERM_CASH_DISC for Credit Card. */
    private List<String> pmtTermCashDiscCrCardList = null;

    /** Order In Process Amount */
    private BigDecimal ordInProcAmt = null;
    //QC#14647 add End

//QC#15306 add Start
    /** After Decimal Point Digit Num */
    private Integer aftDeclPntDigitNum = null;
//QC#15306 add End
    // START 2018/01/25 E.Kameishi[QC#23681,MOD]
    // START 2017/12/19 E.Kameishi[QC#22096,ADD]
    /** */
    public static final String SUM_AMT = "SUM_AMT";
    // END 2017/12/19 E.Kameishi[QC#22096,ADD]
    // END 2018/01/25 E.Kameishi[QC#23681,MOD]

    /**
     * <pre>
     * It is a constructor.
     * </pre>
     */
    public NMZC600001() {
        super();
        // Start 2015/09/30 C.Tanaka Add
        ssmClient = S21SsmBatchClient.getClient(getClass());
        // End 2015/09/30 C.Tanaka Add
    }

    /**
     * <pre>
     * Refer to the class comment.
     * </pre>
     * @param param Interface Msg of Update Credit Limit API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NMZC600001PMsg param, final ONBATCH_TYPE onBatchType) {

        writeStartLog("execute");
        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("InputData=" + param.toString());
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        doProcess(msgMap, onBatchType);

        msgMap.flush();

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("OutputData=" + param.toString());
        }
        writeEndLog("execute");
    }

    /**
     * <pre>
     * Refer to the class comment.
     * One Msg in List is taken out, and the execute(NMZC600001PMsg, ONBATCH_TYPE) is executed. 
     * </pre>
     * @see #execute(NMZC600001PMsg,
     * com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE)
     * @param params Interface Msg list of Update Credit Limit API
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final List<NMZC600001PMsg> params, final ONBATCH_TYPE onBatchType) {

        writeStartLog("execute:params");

        for (NMZC600001PMsg param : params) {
            execute(param, onBatchType);
        }

        writeEndLog("execute:params");
    }

    private void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        checkInput(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // Start 2015/09/30 C.Tanaka Mod
        NMZC600001PMsg param = (NMZC600001PMsg) msgMap.getPmsg();

        // QC#15306 add Start
        this.aftDeclPntDigitNum = DataCacheForSSM.getInstance().getAftDeclPntDigitNumFromCache(param.glblCmpyCd.getValue(), ssmClient);
        // QC#15306 add End

        // QC#14647 add Start
        String pmtTermCashDiscCrCard = VarcharConstCache.getInstance().getVarCharConst(param.glblCmpyCd.getValue(), "PMT_TERM_CR_CARD");
        if (hasValue(pmtTermCashDiscCrCard)) {
            this.pmtTermCashDiscCrCardList = Arrays.asList(pmtTermCashDiscCrCard.split(","));
        }
        // get Order Credit Check Amount
        if (hasValue(param.updKeyNum) && !hasValue(param.inProcAmt) && !hasValue(param.invAmt) && !hasValue(param.rcptAmt)) {
            this.ordInProcAmt = getOrdInProcAmt(param.glblCmpyCd.getValue(), param.updKeyNum.getValue());
        } else {
            this.ordInProcAmt = BigDecimal.ZERO;
        }
        // QC#14647 add End

        if (MODE_ACCT.equals(param.xxModeCd.getValue())) {
            updateAcctAmt(msgMap);
        } else if (MODE_BILL.equals(param.xxModeCd.getValue())) {
            updateAmt(msgMap);
            // Start 2016/01/22 N.Sugiura Add
            //QC#17252 mod Start
//            getDsAcctNum(msgMap);
            if (!hasValue(param.dsAcctNum)) {
                if (!hasValue(getDsAcctNum(param))) {
                    msgMap.addXxMsgId(NMZM0005E);
                    return;
                }

            }
            //QC#17252 mod End
            updateAcctAmt(msgMap);
            // End 2016/01/22 N.Sugiura Add
        } else if (MODE_ALL.equals(param.xxModeCd.getValue())) {
            // Start 2016/01/22 N.Sugiura Mod
//            if (hasValue(param.dsAcctNum)) {
//                updateAcctAmt(msgMap);
//            }
//            if (hasValue(param.billToCustCd)) {
//                updateAmt(msgMap);
//            }
            if (hasValue(param.billToCustCd)) {
                updateAmt(msgMap);
            }
            if (hasValue(param.dsAcctNum)) {
                updateAcctAmt(msgMap);
            }
             // End 2016/01/22 N.Sugiura Mod
        }
        // End 2015/09/30 C.Tanaka Mod

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
    }

    private void checkInput(S21ApiMessageMap msgMap) {

        NMZC600001PMsg param = (NMZC600001PMsg) msgMap.getPmsg();

        if (!hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NMZM0001E);
        }

        // Start 2015/09/30 C.Tanaka Mod
        if (!hasValue(param.xxModeCd)) {
            msgMap.addXxMsgId(NMZM0001E);
        }

        if (!MODE_ACCT.equals(param.xxModeCd.getValue()) && !MODE_BILL.equals(param.xxModeCd.getValue()) && !MODE_ALL.equals(param.xxModeCd.getValue())) {
            msgMap.addXxMsgId(NMZM0024E);
        }

        if (MODE_ACCT.equals(param.xxModeCd.getValue()) && !hasValue(param.dsAcctNum)) {
            msgMap.addXxMsgId(NMZM0004E);
        }

        if (MODE_BILL.equals(param.xxModeCd.getValue()) && !hasValue(param.billToCustCd)) {
            msgMap.addXxMsgId(NMZM0004E);
        }

        if (MODE_ALL.equals(param.xxModeCd.getValue()) && !hasValue(param.dsAcctNum) && !hasValue(param.billToCustCd)) {
            msgMap.addXxMsgId(NMZM0004E);
        }
        // End 2015/09/30 C.Tanaka Mod

        if (!hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NMZM0007E);
        }
    }

    private void updateAmt(S21ApiMessageMap msgMap) {

        writeStartLog("updateAmt");

        NMZC600001PMsg param = (NMZC600001PMsg) msgMap.getPmsg();

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String billToCustCd = param.billToCustCd.getValue();
        String readOnlyFlg = param.xxReadOnlyFlg.getValue(); // CSA-QC#12894 Add

        // Start 2016/01/22 N.Sugiura Mod
//        // Start 2015/09/30 C.Tanaka Add
//        Map<String, Object> requestList = getBillInProcAmt(glblCmpyCd, billToCustCd);
//        if (requestList == null) {
//            msgMap.addXxMsgId(NMZM0005E);
//            return;
//        }
//        // End 2015/09/30 C.Tanaka Add
//
//        CUST_CR_PRFLTMsg custCrPrflMsg = searchCustCrPrfl(glblCmpyCd, billToCustCd);
//        if (custCrPrflMsg == null) {
//            msgMap.addXxMsgId(NMZM0005E);
//            return;
//        }
        CUST_CR_PRFLTMsg custCrPrflMsg = searchCustCrPrfl(glblCmpyCd, billToCustCd, readOnlyFlg); // CSA-QC#12894 Mod
        if (custCrPrflMsg == null) {
            return;
        }

        // QC#17252 mod Start
        Map<String, Object> requestList;
        BigDecimal crLimitAmt = custCrPrflMsg.crLimitAmt.getValue();
        if (hasValue(crLimitAmt)) {
            requestList = getBillInProcAmt(glblCmpyCd, billToCustCd, readOnlyFlg); // CSA-QC#3834 Mod
            if (requestList == null) {
                msgMap.addXxMsgId(NMZM0005E);
                return;
            }
        } else {
            String dsAcctNum =  getDsAcctNum(param);
            if (!hasValue(dsAcctNum)) {
                msgMap.addXxMsgId(NMZM0005E);
                return;
            }
            requestList = getAcctInProcAmt(glblCmpyCd, dsAcctNum, readOnlyFlg);
            if (requestList == null) {
                msgMap.addXxMsgId(NMZM0005E);
                return;
            }
            crLimitAmt = getAcctCrLimitAmt(param, custCrPrflMsg, dsAcctNum);

        }
        // QC#17252 mod End

        // End 2016/01/22 N.Sugiura Mod

        try {
            // Start 2015/09/30 C.Tanaka Add
            BigDecimal inProcAmt = (BigDecimal) requestList.get("TRX_IN_PROC");
            // QC#14647 add Start
            inProcAmt = inProcAmt.add(this.ordInProcAmt);
            // QC#14647 add End
            ZYPEZDItemValueSetter.setValue(custCrPrflMsg.inProcAmt, inProcAmt);
            // End 2015/09/30 C.Tanaka Add

//            setCustCrPrfl(custCrPrflMsg, param);
            setCustCrPrfl(custCrPrflMsg, param, crLimitAmt);    //QC#17252 mod
        } catch (EZDAbendException abendEx) {
            // Amount Overflow
            if (abendEx.getRootCause() instanceof EZDValidatorException) {
                if (EZDDebugOutput.isDebug()) {
                    writeDebugLog("Amount Overflow. " + abendEx.toString());
                }
                msgMap.addXxMsgId(NMZM0008E);
                return;
            } else {
                throw abendEx;
            }
        }

        // Start 2015/09/30 C.Tanaka Mod
        if (!ZYPConstant.FLG_ON_Y.equals(readOnlyFlg)) {
            updateCrBal(msgMap, custCrPrflMsg);
        }

        int cnt = param.xxOutPrmList.getValidCount();
        ZYPEZDItemValueSetter.setValue(param.xxOutPrmList.no(cnt).billToCustCd, custCrPrflMsg.billToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(param.xxOutPrmList.no(cnt).crBalAmt, custCrPrflMsg.crBalAmt.getValue());
        ZYPEZDItemValueSetter.setValue(param.xxOutPrmList.no(cnt).crChkReqTpCd, custCrPrflMsg.crChkReqTpCd.getValue());
        param.xxOutPrmList.setValidCount(cnt + 1);
        // End 2015/09/30 C.Tanaka Mod

        // Start 2016/01/22 N.Sugiura Del
        // Start 2015/09/30 C.Tanaka Add
//        BigDecimal zero = new BigDecimal("0");
//        if ((hasValue(param.invAmt) && param.invAmt.getValue().compareTo(zero) != 0) || (hasValue(param.rcptAmt) && param.rcptAmt.getValue().compareTo(zero) != 0)) {
//            Map<String, Object> requestMap = getDsAcctNum(glblCmpyCd, billToCustCd);
//            if (requestMap == null) {
//                msgMap.addXxMsgId(NMZM0005E);
//                return;
//            }
//
//            ZYPEZDItemValueSetter.setValue(param.dsAcctNum, (String) requestMap.get("SELL_TO_CUST_CD"));
//            updateAcctAmt(msgMap);
//        }
        // End 2015/09/30 C.Tanaka Add
        // Start 2016/01/22 N.Sugiura Del
        writeEndLog("updateAmt");
    }

    // Start 2015/09/30 C.Tanaka Add
    private void updateAcctAmt(S21ApiMessageMap msgMap) {

        writeStartLog("updateAcctAmt");

        // Start 2016/01/22 N.Sugiura Mod
//        NMZC600001PMsg param = (NMZC600001PMsg) msgMap.getPmsg();
//        String glblCmpyCd = param.glblCmpyCd.getValue();
//        String dsAcctNum = param.dsAcctNum.getValue();
//        Map<String, Object> requestList = getAcctInProcAmt(glblCmpyCd, dsAcctNum);
//        if (requestList == null) {
//            msgMap.addXxMsgId(NMZM0005E);
//            return;
//        }
//
//        DS_ACCT_CR_PRFLTMsg dsAcctCrPrflMsg = searchDsAcctCrPrfl(glblCmpyCd, dsAcctNum);
//        if (dsAcctCrPrflMsg == null) {
//            msgMap.addXxMsgId(NMZM0005E);
//            return;
//        }
        NMZC600001PMsg param = (NMZC600001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String dsAcctNum = param.dsAcctNum.getValue();
        String readOnlyFlg = param.xxReadOnlyFlg.getValue(); // CSA-QC#12894 Add

        DS_ACCT_CR_PRFLTMsg dsAcctCrPrflMsg = searchDsAcctCrPrfl(glblCmpyCd, dsAcctNum, readOnlyFlg); // CSA-QC#12894 Mod
        if (dsAcctCrPrflMsg == null) {
            msgMap.addXxMsgId(NMZM0005E);
            return;
        }

        Map<String, Object> requestList = getAcctInProcAmt(glblCmpyCd, dsAcctNum, readOnlyFlg); // CSA-QC#3834 Mod
        if (requestList == null) {
            msgMap.addXxMsgId(NMZM0005E);
            return;
        }
        // End 2016/01/22 N.Sugiura Mod

        try {
            BigDecimal inProcAmt = (BigDecimal) requestList.get("TRX_IN_PROC");
            // QC#14647 add Start
            inProcAmt = inProcAmt.add(this.ordInProcAmt);
            // QC#14647 add End
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflMsg.inProcAmt, inProcAmt);

            setDsAcctCrPrfl(dsAcctCrPrflMsg, param);
        } catch (EZDAbendException abendEx) {
            // Amount Overflow
            if (abendEx.getRootCause() instanceof EZDValidatorException) {
                if (EZDDebugOutput.isDebug()) {
                    writeDebugLog("Amount Overflow. " + abendEx.toString());
                }
                msgMap.addXxMsgId(NMZM0008E);
                return;
            } else {
                throw abendEx;
            }
        }

        if (!ZYPConstant.FLG_ON_Y.equals(readOnlyFlg)) {
            updateAcctCrBal(msgMap, dsAcctCrPrflMsg);
        }

        int cnt = param.xxOutPrmList.getValidCount();
        ZYPEZDItemValueSetter.setValue(param.xxOutPrmList.no(cnt).dsAcctNum, dsAcctCrPrflMsg.dsAcctNum.getValue());
        ZYPEZDItemValueSetter.setValue(param.xxOutPrmList.no(cnt).crBalAmt, dsAcctCrPrflMsg.crBalAmt.getValue());
        ZYPEZDItemValueSetter.setValue(param.xxOutPrmList.no(cnt).crChkReqTpCd, dsAcctCrPrflMsg.crChkReqTpCd.getValue());
        param.xxOutPrmList.setValidCount(cnt + 1);

        writeEndLog("updateAcctAmt");
    }

    // End 2015/09/30 C.Tanaka Add

    private void updateCrBal(S21ApiMessageMap msgMap, CUST_CR_PRFLTMsg custCrPrflMsg) {
        writeStartLog("updateCrBal");

        S21ApiTBLAccessor.update(custCrPrflMsg);
        if (!RTNCD_NORMAL.equals(custCrPrflMsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0005E);
            return;
        }

        // Start 2015/09/30 C.Tanaka Mod
        insertCrBalHist(msgMap, custCrPrflMsg.inProcAmt.getValue());
        // End 2015/09/30 C.Tanaka Mod

        writeEndLog("updateCrBal");
    }

    // Start 2015/09/30 C.Tanaka Add
    private void updateAcctCrBal(S21ApiMessageMap msgMap, DS_ACCT_CR_PRFLTMsg dsAcctCrPrflMsg) {
        writeStartLog("updateAcctCrBal");

        S21ApiTBLAccessor.update(dsAcctCrPrflMsg);
        if (!RTNCD_NORMAL.equals(dsAcctCrPrflMsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0005E);
            return;
        }

        insertDsCrBalHist(msgMap, dsAcctCrPrflMsg.inProcAmt.getValue());

        writeEndLog("updateAcctCrBal");
    }

    // End 2015/09/30 C.Tanaka Add

    // Start 2015/09/30 C.Tanaka Mod
    private void insertCrBalHist(S21ApiMessageMap msgMap, BigDecimal inProcAmt) {
        // End 2015/09/30 C.Tanaka Mod

        writeStartLog("insCR_BAL_HIST");

        NMZC600001PMsg param = (NMZC600001PMsg) msgMap.getPmsg();

        CR_BAL_HISTTMsg crBalHistMsg = new CR_BAL_HISTTMsg();
        // Start 2015/09/30 C.Tanaka Mod
        setCrBalHist(crBalHistMsg, param, inProcAmt);
        // End 2015/09/30 C.Tanaka Mod

        S21ApiTBLAccessor.create(crBalHistMsg);
        if (!RTNCD_NORMAL.equals(crBalHistMsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0006E);
            return;
        }

        writeEndLog("insCR_BAL_HIST");
    }

    // Start 2015/09/30 C.Tanaka Add
    private void insertDsCrBalHist(S21ApiMessageMap msgMap, BigDecimal inProcAmt) {

        writeStartLog("insDS_CR_BAL_HIST");

        NMZC600001PMsg param = (NMZC600001PMsg) msgMap.getPmsg();

        DS_CR_BAL_HISTTMsg crDsBalHistMsg = new DS_CR_BAL_HISTTMsg();
        setDsCrBalHist(crDsBalHistMsg, param, inProcAmt);

        S21ApiTBLAccessor.create(crDsBalHistMsg);
        if (!RTNCD_NORMAL.equals(crDsBalHistMsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0006E);
            return;
        }

        writeEndLog("insDS_CR_BAL_HIST");
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getBillInProcAmt(String glblCmpyCd, String billToCustCd, String readOnlyFlg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("shpgSts_VA", SHPG_STS.VALIDATED);
        paramMap.put("shpgSts_HA", SHPG_STS.HARD_ALLOCATED);
        paramMap.put("cpoDtlLineSubNum", CPO_DTL_LINE_SUB_NUM);
        paramMap.put("cpoOrdTp", CPO_ORD_TP.SALES);
//QC#14647 mod Start
//        paramMap.put("crRebilCd", CR_REBIL.CREDIT);
        paramMap.put("crRebilCd_R", CR_REBIL.REBILL);
//QC#14647 mod End
        paramMap.put("billToCustCd", billToCustCd);

        // CSA-QC#10321 Mod Start
        // List<String> shpgStsTgblList = new ArrayList<String>();
        // shpgStsTgblList.add(SHPG_STS.S_OR_O_PRINTED);
        // shpgStsTgblList.add(SHPG_STS.PICKED);
        // shpgStsTgblList.add(SHPG_STS.PACKED);
        // shpgStsTgblList.add(SHPG_STS.STAGED);
        // shpgStsTgblList.add(SHPG_STS.INSHED);
        // shpgStsTgblList.add(SHPG_STS.SHIPPED);
        // shpgStsTgblList.add(SHPG_STS.S_OR_O_CANCELLED);
        // shpgStsTgblList.add(SHPG_STS.P_OR_O_PRINTED);
        // shpgStsTgblList.add(SHPG_STS.P_OR_O_CANCELLED);
        // shpgStsTgblList.add(SHPG_STS.ARRIVED);
        // shpgStsTgblList.add(SHPG_STS.N_INVOICE_READY);
        // shpgStsTgblList.add(SHPG_STS.INSTALLED);
        // paramMap.put("shpgStsTgblList", shpgStsTgblList);

        // List<String> shpgStsIntgList = new ArrayList<String>();
        // shpgStsIntgList.add(SHPG_STS.PICKED);
        // shpgStsIntgList.add(SHPG_STS.PACKED);
        // shpgStsIntgList.add(SHPG_STS.SHIPPED);
        // shpgStsIntgList.add(SHPG_STS.N_INVOICE_READY);
        // paramMap.put("shpgStsIntgList", shpgStsIntgList);

        paramMap.put("sOrOPrinted", SHPG_STS.S_OR_O_PRINTED);
        paramMap.put("picked", SHPG_STS.PICKED);
        paramMap.put("packed", SHPG_STS.PACKED);
        paramMap.put("staged", SHPG_STS.STAGED);
        paramMap.put("inshed", SHPG_STS.INSHED);
        paramMap.put("shipped", SHPG_STS.SHIPPED);
        paramMap.put("sOrOCancelled", SHPG_STS.S_OR_O_CANCELLED);
        paramMap.put("pOrOPrinted", SHPG_STS.P_OR_O_PRINTED);
        paramMap.put("pOrOCancelled", SHPG_STS.P_OR_O_CANCELLED);
        paramMap.put("arrived", SHPG_STS.ARRIVED);
        paramMap.put("nInvoiceReady", SHPG_STS.N_INVOICE_READY);
        paramMap.put("installed", SHPG_STS.INSTALLED);
        // CSA-QC#10321 Mod End
        // CSA-QC#14972 add Start
        paramMap.put("prcDtlGrp_TAX",   PRC_DTL_GRP.TAX);
        //paramMap.put("prcDtlGrp_FRT",   PRC_DTL_GRP.FREIGHT);
        //paramMap.put("prcDtlGrp_RND1",  PRC_DTL_GRP.ROUNDING_FACTOR_1);
        paramMap.put("revRecogExem",    "REV_RECOG_EXEM");
        // CSA-QC#14972 add End
        // CSA-QC#14647 add Start
        paramMap.put("pmtTermCashDiscCrCardList", pmtTermCashDiscCrCardList);
        // CSA-QC#14647 add End
        // QC#15306 add Start
        paramMap.put("aftDeclPntDigitNum",  this.aftDeclPntDigitNum);
        // QC#15306 add End
        // 2018/05/22 QC#21841 Add Start
        //paramMap.put("prcDtlGrp_HNDF", PRC_DTL_GRP.HANDLING_FEE);
        //paramMap.put("prcDtlGrp_FSC", PRC_DTL_GRP.FUEL_SURCHARGE);
        //paramMap.put("prcDtlGrp_SHPF", PRC_DTL_GRP.SHIPPING_FEE);
        //paramMap.put("prcDtlGrp_RND2", PRC_DTL_GRP.ROUNDING_FACTOR_2);
        // 2018/05/22 QC#21841 Add End
        // QC#27479 2018/08/03 Add Start
        List<String> prcDtlGrpCdList = new ArrayList<String>();
        prcDtlGrpCdList.add(PRC_DTL_GRP.FREIGHT);
        prcDtlGrpCdList.add(PRC_DTL_GRP.ROUNDING_FACTOR_1);
        prcDtlGrpCdList.add(PRC_DTL_GRP.HANDLING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.FUEL_SURCHARGE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.SHIPPING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.RESTOCKING_FEE);
        prcDtlGrpCdList.add(PRC_DTL_GRP.ROUNDING_FACTOR_2);
        paramMap.put("prcDtlGrpCdList",     prcDtlGrpCdList);
        // QC#27479 2018/08/03 Add End
        // QC#22965 2018/06/05 Add Start
        paramMap.put("flgN", ZYPConstant.FLG_OFF_N);
        // QC#22965 2018/06/05 Add End

        // CSA-QC#3834 Mod Start
        if (!ZYPConstant.FLG_ON_Y.equals(readOnlyFlg)) {
            List<Map<String, Object>> list = ssmClient.queryObjectList("getBillInProcAmt", paramMap); // S21_NA#2709:XML
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }

        return DataCacheForSSM.getInstance().getBillInProcAmtFromCache(paramMap, ssmClient);
        // CSA-QC#3834 Mod End
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getAcctInProcAmt(String glblCmpyCd, String dsAcctNum, String readOnlyFlg) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("shpgSts_VA", SHPG_STS.VALIDATED);
        paramMap.put("shpgSts_HA", SHPG_STS.HARD_ALLOCATED);
        paramMap.put("cpoDtlLineSubNum", CPO_DTL_LINE_SUB_NUM);
        paramMap.put("cpoOrdTp", CPO_ORD_TP.SALES);
// QC#14647 mod Start
//        paramMap.put("crRebilCd", CR_REBIL.CREDIT);
        paramMap.put("crRebilCd_R",  CR_REBIL.REBILL);
// QC#14647 mod End
        paramMap.put("dsAcctNum", dsAcctNum);

        // CSA-QC#10321 Mod Start
        // List<String> shpgStsTgblList = new ArrayList<String>();
        // shpgStsTgblList.add(SHPG_STS.S_OR_O_PRINTED);
        // shpgStsTgblList.add(SHPG_STS.PICKED);
        // shpgStsTgblList.add(SHPG_STS.PACKED);
        // shpgStsTgblList.add(SHPG_STS.STAGED);
        // shpgStsTgblList.add(SHPG_STS.INSHED);
        // shpgStsTgblList.add(SHPG_STS.SHIPPED);
        // shpgStsTgblList.add(SHPG_STS.S_OR_O_CANCELLED);
        // shpgStsTgblList.add(SHPG_STS.P_OR_O_PRINTED);
        // shpgStsTgblList.add(SHPG_STS.P_OR_O_CANCELLED);
        // shpgStsTgblList.add(SHPG_STS.ARRIVED);
        // shpgStsTgblList.add(SHPG_STS.N_INVOICE_READY);
        // shpgStsTgblList.add(SHPG_STS.INSTALLED);
        // paramMap.put("shpgStsTgblList", shpgStsTgblList);

        // List<String> shpgStsIntgList = new ArrayList<String>();
        // shpgStsIntgList.add(SHPG_STS.PICKED);
        // shpgStsIntgList.add(SHPG_STS.PACKED);
        // shpgStsIntgList.add(SHPG_STS.SHIPPED);
        // shpgStsIntgList.add(SHPG_STS.N_INVOICE_READY);
        // paramMap.put("shpgStsIntgList", shpgStsIntgList);

        paramMap.put("sOrOPrinted", SHPG_STS.S_OR_O_PRINTED);
        paramMap.put("picked", SHPG_STS.PICKED);
        paramMap.put("packed", SHPG_STS.PACKED);
        paramMap.put("staged", SHPG_STS.STAGED);
        paramMap.put("inshed", SHPG_STS.INSHED);
        paramMap.put("shipped", SHPG_STS.SHIPPED);
        paramMap.put("sOrOCancelled", SHPG_STS.S_OR_O_CANCELLED);
        paramMap.put("pOrOPrinted", SHPG_STS.P_OR_O_PRINTED);
        paramMap.put("pOrOCancelled", SHPG_STS.P_OR_O_CANCELLED);
        paramMap.put("arrived", SHPG_STS.ARRIVED);
        paramMap.put("nInvoiceReady", SHPG_STS.N_INVOICE_READY);
        paramMap.put("installed", SHPG_STS.INSTALLED);
        // CSA-QC#10321 Mod End
        // CSA-QC#14972 add Start
        paramMap.put("prcDtlGrp_TAX",   PRC_DTL_GRP.TAX);
        paramMap.put("prcDtlGrp_FRT",   PRC_DTL_GRP.FREIGHT);
        paramMap.put("prcDtlGrp_RND1",  PRC_DTL_GRP.ROUNDING_FACTOR_1);
        paramMap.put("revRecogExem",    "REV_RECOG_EXEM");
        // CSA-QC#14972 add End
        // CSA-QC#14647 add Start
        paramMap.put("pmtTermCashDiscCrCardList", pmtTermCashDiscCrCardList);
        // CSA-QC#14647 add End
        // QC#15306 add Start
        paramMap.put("aftDeclPntDigitNum",  this.aftDeclPntDigitNum);
        // QC#15306 add End
        // 2018/05/22 QC#21841 Add Start
        paramMap.put("prcDtlGrp_HNDF", PRC_DTL_GRP.HANDLING_FEE);
        paramMap.put("prcDtlGrp_FSC", PRC_DTL_GRP.FUEL_SURCHARGE);
        paramMap.put("prcDtlGrp_SHPF", PRC_DTL_GRP.SHIPPING_FEE);
        paramMap.put("prcDtlGrp_RND2", PRC_DTL_GRP.ROUNDING_FACTOR_2);
        // 2018/05/22 QC#21841 Add End
        // QC#22965 2018/06/05 Add Start
        paramMap.put("flgN", ZYPConstant.FLG_OFF_N);
        // QC#22965 2018/06/05 Add End

        // CSA-QC#3834 Mod Start
        if (!ZYPConstant.FLG_ON_Y.equals(readOnlyFlg)) {
            List<Map<String, Object>> list = ssmClient.queryObjectList("getAcctInProcAmt", paramMap); // S21_NA#2709:XML
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }

        return DataCacheForSSM.getInstance().getAcctInProcAmtFromCache(paramMap, ssmClient);
        // CSA-QC#3834 Mod End
    }

    // Start 2016/01/22 N.Sugiura Mod
//    @SuppressWarnings("unchecked")
//    private Map<String, Object> getDsAcctNum(String glblCmpyCd, String billToCustCd) {
//        Map<String, String> paramMap = new HashMap<String, String>();
//        paramMap.put("glblCmpyCd", glblCmpyCd);
//        paramMap.put("billToCustCd", billToCustCd);
//
//        List<Map> list = ssmClient.queryObjectList("getDsAcctNum", paramMap);
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
//    }
//    private void getDsAcctNum(S21ApiMessageMap msgMap) {
    private String getDsAcctNum(NMZC600001PMsg param) {//QC#17252 mod
//        NMZC600001PMsg param = (NMZC600001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        String billToCustCd = param.billToCustCd.getValue();

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("billToCustCd", billToCustCd);

        //QC#17252 mod Start
//        List<Map> list = ssmClient.queryObjectList("getDsAcctNum", paramMap);
        String dsAcctNum = DataCacheForSSM.getInstance().getDsAcctNumCache(paramMap, ssmClient);
//        if (list.isEmpty()) {
//            msgMap.addXxMsgId(NMZM0005E);
//            return null;
//        }
//        Map<String, Object> requestMap = list.get(0);
//        ZYPEZDItemValueSetter.setValue(param.dsAcctNum, (String) requestMap.get("SELL_TO_CUST_CD"));
        if (MODE_BILL.equals(param.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(param.dsAcctNum, dsAcctNum);
        }
        return dsAcctNum;
        //QC#17252 mod End
    }
    // End 2016/01/22 N.Sugiura Mod

    // End 2015/09/30 C.Tanaka Add

//QC#14647 add Start
    private BigDecimal getOrdInProcAmt(String glblCmpyCd, String cpoOrdNum) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",      glblCmpyCd);
        paramMap.put("cpoOrdNum",       cpoOrdNum);
        paramMap.put("shpgSts_VA",      SHPG_STS.VALIDATED);
        paramMap.put("cpoOrdTp",        CPO_ORD_TP.SALES);
        paramMap.put("crRebilCd_R",     CR_REBIL.REBILL);
        paramMap.put("prcDtlGrp_TAX",   PRC_DTL_GRP.TAX);
        paramMap.put("prcDtlGrp_FRT",   PRC_DTL_GRP.FREIGHT);
        paramMap.put("prcDtlGrp_RND1",  PRC_DTL_GRP.ROUNDING_FACTOR_1);
        // 2018/05/22 QC#21841 Add Start
        paramMap.put("prcDtlGrp_HNDF", PRC_DTL_GRP.HANDLING_FEE);
        paramMap.put("prcDtlGrp_FSC", PRC_DTL_GRP.FUEL_SURCHARGE);
        paramMap.put("prcDtlGrp_SHPF", PRC_DTL_GRP.SHIPPING_FEE);
        paramMap.put("prcDtlGrp_RND2", PRC_DTL_GRP.ROUNDING_FACTOR_2);
        // 2018/05/22 QC#21841 Add End
        // QC#22965 2018/06/05 Add Start
        paramMap.put("flgN", ZYPConstant.FLG_OFF_N);
        // QC#22965 2018/06/05 Add End
        paramMap.put("revRecogExem",    "REV_RECOG_EXEM");
        paramMap.put("pmtTermCashDiscCrCardList", pmtTermCashDiscCrCardList);

        BigDecimal amt = (BigDecimal) ssmClient.queryObject("getOrdInProcAmt", paramMap);

        if (amt == null) {
            amt = BigDecimal.ZERO;
        }

        return amt;
    }
//QC#14647 add End

//    private void setCustCrPrfl(CUST_CR_PRFLTMsg inCustCrPrflMsg, NMZC600001PMsg param) {
    private void setCustCrPrfl(CUST_CR_PRFLTMsg inCustCrPrflMsg, NMZC600001PMsg param, BigDecimal crLimitAmt) {    //QC#17252 mod
        //START 2017/12/19 E.Kameishi[QC#22096, ADD]
        // START 2018/06/22 [QC#26026, MOD]
        BigDecimal arBalAmt = getArBalAmt(param, MODE_BILL);
        // END   2018/06/22 [QC#26026, MOD]
        ZYPEZDItemValueSetter.setValue(inCustCrPrflMsg.arBalAmt, arBalAmt);
        //END 2017/12/19 E.Kameishi[QC#22096, ADD]
        //START 2017/12/19 E.Kameishi[QC#22096, DEL]
        //if (hasValue(param.invAmt)) {
        //    inCustCrPrflMsg.arBalAmt.setValue(add(param.invAmt.getValue(), inCustCrPrflMsg.arBalAmt.getValue()));
        //}
        //END 2017/12/19 E.Kameishi[QC#22096, DEL]

        if (hasValue(param.invDt)) {
            inCustCrPrflMsg.lastInvDt.setValue(param.invDt.getValue());
        }

        //START 2017/12/19 E.Kameishi[QC#22096, DEL]
        //if (hasValue(param.rcptAmt)) {
        //    inCustCrPrflMsg.arBalAmt.setValue(add(param.rcptAmt.getValue(), inCustCrPrflMsg.arBalAmt.getValue()));
        //}
        //END 2017/12/19 E.Kameishi[QC#22096, DEL]

        if (hasValue(param.rcptDt)) {
            inCustCrPrflMsg.lastPmtRcptDt.setValue(param.rcptDt.getValue());
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("inProcAmt=" + inCustCrPrflMsg.inProcAmt.getValue());
            writeDebugLog("arBalAmt=" + inCustCrPrflMsg.arBalAmt.getValue());
        }

        BigDecimal big = add(inCustCrPrflMsg.inProcAmt.getValue(), inCustCrPrflMsg.arBalAmt.getValue());
        // Start 2015/09/30 C.Tanaka Mod
//        inCustCrPrflMsg.crBalAmt.setValue(subtract(inCustCrPrflMsg.crLimitAmt.getValue(), big));
        inCustCrPrflMsg.crBalAmt.setValue(subtract(crLimitAmt, big));    // QC#17252 mod
        // End 2015/09/30 C.Tanaka Mod

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("Update CUST_CR_PRFL Msg=" + inCustCrPrflMsg.toString());
        }
    }
    //QC#17252 add Start
    private BigDecimal getAcctCrLimitAmt(NMZC600001PMsg param, CUST_CR_PRFLTMsg custCrPrflTMsg, String dsAcctNum) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd",      param.glblCmpyCd.getValue());
        paramMap.put("billToCustCd",    param.billToCustCd.getValue());
        paramMap.put("dsAcctNum",       dsAcctNum);

        return DataCacheForSSM.getInstance().getAcctCrLimitAmtFromCache(paramMap, ssmClient);
    }
    //QC#17252 add End

    // Start 2015/09/30 C.Tanaka Add
    private void setDsAcctCrPrfl(DS_ACCT_CR_PRFLTMsg dsAcctCrPrflMsg, NMZC600001PMsg param) {
        //START 2017/12/19 E.Kameishi[QC#22096, ADD]
        // START 2018/06/22 [QC#26026, MOD]
        BigDecimal arBalAmt = getArBalAmt(param, MODE_ACCT);
        // END   2018/06/22 [QC#26026, MOD]
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflMsg.arBalAmt, arBalAmt);
        //END 2017/12/19 E.Kameishi[QC#22096, ADD]
        //START 2017/12/19 E.Kameishi[QC#22096, DEL]
        //if (hasValue(param.invAmt)) {
        //    ZYPEZDItemValueSetter.setValue(dsAcctCrPrflMsg.arBalAmt, add(param.invAmt.getValue(), dsAcctCrPrflMsg.arBalAmt.getValue()));
        //}
        //END 2017/12/19 E.Kameishi[QC#22096, DEL]

        if (hasValue(param.invDt)) {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflMsg.lastInvDt, param.invDt.getValue());
        }

        //START 2017/12/19 E.Kameishi[QC#22096, DEL]
        //if (hasValue(param.rcptAmt)) {
        //    ZYPEZDItemValueSetter.setValue(dsAcctCrPrflMsg.arBalAmt, add(param.rcptAmt.getValue(), dsAcctCrPrflMsg.arBalAmt.getValue()));
        //}
        //END 2017/12/19 E.Kameishi[QC#22096, DEL]

        if (hasValue(param.rcptDt)) {
            ZYPEZDItemValueSetter.setValue(dsAcctCrPrflMsg.lastPmtRcptDt, param.rcptDt.getValue());
        }

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("inProcAmt=" + dsAcctCrPrflMsg.inProcAmt.getValue());
            writeDebugLog("arBalAmt=" + dsAcctCrPrflMsg.arBalAmt.getValue());
        }

        BigDecimal big = add(dsAcctCrPrflMsg.inProcAmt.getValue(), dsAcctCrPrflMsg.arBalAmt.getValue());
        ZYPEZDItemValueSetter.setValue(dsAcctCrPrflMsg.crBalAmt, subtract(dsAcctCrPrflMsg.crLimitAmt.getValue(), big));

        if (EZDDebugOutput.isDebug()) {
            writeDebugLog("Update DS_ACCT_CR_PRFL Msg=" + dsAcctCrPrflMsg.toString());
        }
    }

    // End 2015/09/30 C.Tanaka Add

    private void setCrBalHist(CR_BAL_HISTTMsg inCrBalHistMsg, NMZC600001PMsg param, BigDecimal inProcAmt) {

        inCrBalHistMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inCrBalHistMsg.crBalHistPk.setValue(getBigDecimalCrBalHistSQ());
        inCrBalHistMsg.billToCustCd.setValue(param.billToCustCd.getValue());
        inCrBalHistMsg.updKeyNum.setValue(param.updKeyNum.getValue());
        inCrBalHistMsg.invAmt.setValue(getBigDecimal(param.invAmt, "0"));
        inCrBalHistMsg.invDt.setValue(param.invDt.getValue());
        inCrBalHistMsg.rcptAmt.setValue(getBigDecimal(param.rcptAmt, "0"));
        inCrBalHistMsg.rcptDt.setValue(param.rcptDt.getValue());
        inCrBalHistMsg.slsDt.setValue(param.slsDt.getValue());
        // Start 2015/09/30 C.Tanaka Mod
        inCrBalHistMsg.inProcAmt.setValue(inProcAmt);
        // End 2015/09/30 C.Tanaka Mod
    }

    // Start 2015/09/30 C.Tanaka Add
    private void setDsCrBalHist(DS_CR_BAL_HISTTMsg inCrBalHistMsg, NMZC600001PMsg param, BigDecimal inProcAmt) {

        inCrBalHistMsg.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        inCrBalHistMsg.dsCrBalHistPk.setValue(getBigDecimalDsCrBalHistSQ());
        inCrBalHistMsg.dsAcctCustCd.setValue(param.dsAcctNum.getValue());
        inCrBalHistMsg.updKeyNum.setValue(param.updKeyNum.getValue());
        inCrBalHistMsg.invAmt.setValue(getBigDecimal(param.invAmt, "0"));
        inCrBalHistMsg.invDt.setValue(param.invDt.getValue());
        inCrBalHistMsg.rcptAmt.setValue(getBigDecimal(param.rcptAmt, "0"));
        inCrBalHistMsg.rcptDt.setValue(param.rcptDt.getValue());
        inCrBalHistMsg.slsDt.setValue(param.slsDt.getValue());
        inCrBalHistMsg.inProcAmt.setValue(inProcAmt);
    }

    // End 2015/09/30 C.Tanaka Add

    private CUST_CR_PRFLTMsg searchCustCrPrfl(String glblCmpyCd, String billToCustCd, String readOnlyFlg) {

        CUST_CR_PRFLTMsg crPrflMsg = new CUST_CR_PRFLTMsg();
        crPrflMsg.setSQLID("100");
        crPrflMsg.setMaxCount(1);
        crPrflMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        crPrflMsg.setConditionValue("billToCustCd01", billToCustCd);

        // CSA-QC#12894 Mod Start
        // CUST_CR_PRFLTMsgArray outMsgList = (CUST_CR_PRFLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(crPrflMsg);
        CUST_CR_PRFLTMsgArray outMsgList = null;
        if (!ZYPConstant.FLG_ON_Y.equals(readOnlyFlg)) {
            outMsgList = (CUST_CR_PRFLTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(crPrflMsg);
        } else {
            outMsgList = (CUST_CR_PRFLTMsgArray) S21ApiTBLAccessor.findByCondition(crPrflMsg);
        }
        // CSA-QC#12894 Mod End

        if (outMsgList.length() > 0) {
            return outMsgList.no(0);
        }

        return null;
    }

    // Start 2015/09/30 C.Tanaka Add
    private DS_ACCT_CR_PRFLTMsg searchDsAcctCrPrfl(String glblCmpyCd, String dsAcctNum, String readOnlyFlg) {

        DS_ACCT_CR_PRFLTMsg crPrflMsg = new DS_ACCT_CR_PRFLTMsg();
        ZYPEZDItemValueSetter.setValue(crPrflMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(crPrflMsg.dsAcctNum, dsAcctNum);

        // CSA-QC#12894 Mod Start
        // DS_ACCT_CR_PRFLTMsg outMsg = (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKey(crPrflMsg);
        DS_ACCT_CR_PRFLTMsg outMsg = null;
        if (!ZYPConstant.FLG_ON_Y.equals(readOnlyFlg)) {
            outMsg = (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(crPrflMsg);
        } else {
            outMsg = (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKey(crPrflMsg);
        }
        // CSA-QC#12894 Mod End

        if (outMsg != null) {
            return outMsg;
        }

        return null;
    }

    // End 2015/09/30 C.Tanaka Add

    private BigDecimal getBigDecimalCrBalHistSQ() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(CR_BAL_HIST_SQ);
    }

    // Start 2015/09/30 C.Tanaka Add
    private BigDecimal getBigDecimalDsCrBalHistSQ() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(DS_CR_BAL_HIST_SQ);
    }

    // End 2015/09/30 C.Tanaka Add

    private BigDecimal add(BigDecimal big1, BigDecimal big2) {
        if (big1 == null) {
            big1 = ZERO;
        }
        if (big2 == null) {
            big2 = ZERO;
        }
        return big1.add(big2);
    }

    private BigDecimal subtract(BigDecimal big1, BigDecimal big2) {
        if (big1 == null) {
            big1 = ZERO;
        }
        if (big2 == null) {
            big2 = ZERO;
        }
        return big1.subtract(big2);
    }

    private void writeDebugLog(String str) {
        EZDDebugOutput.println(1, "[Update Credit Limit API] " + str, this);
    }

    private void writeStartLog(String methodNm) {
        writeDebugLog("[START]NMZC600001_" + methodNm + "-------");
    }

    private void writeEndLog(String methodNm) {
        writeDebugLog("[END]NMZC600001_" + methodNm + "-------");
    }
    //START 2017/12/19 E.Kameishi[QC#22096, ADD]
    private BigDecimal getArBalAmt(NMZC600001PMsg param, String mode) {
        BigDecimal arBalAmt = BigDecimal.ZERO;

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        queryParam.put("applyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        queryParam.put("applyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        queryParam.put("arTrxTp_Rcpt", AR_TRX_TP.RECEIPT);
        queryParam.put("cltDsptStsCdApproved", CLT_DSPT_STS.APPROVED);
        // START 2018/06/22 [QC#26026, MOD]
        if (MODE_BILL.equals(mode)) {
            queryParam.put("billToCustCd", param.billToCustCd.getValue());
        }
        // END   2018/06/22 [QC#26026, MOD]
        // START 2018/01/25 E.Kameishi[QC#23681,ADD]
        queryParam.put("fnlzInvFlg", ZYPConstant.FLG_ON_Y);
        // END 2018/01/25 E.Kameishi[QC#23681,ADD]
        // START 2018/04/13 E.Kameishi[QC#25426,ADD]
        queryParam.put("sellToCustCd", param.dsAcctNum.getValue());
        // START 2018/04/13 E.Kameishi[QC#25426,ADD]

        Map ssmRes = (Map) ssmClient.queryObject("getArBalAmt", queryParam);

        if(ssmRes==null) {
            arBalAmt = BigDecimal.ZERO;
        } else {
            // START 2018/01/25 E.Kameishi[QC#23681,MOD]
            if(ssmRes.get(SUM_AMT)==null) {
                arBalAmt = BigDecimal.ZERO;
            } else {
                arBalAmt = new BigDecimal(ssmRes.get(SUM_AMT).toString());
            }
            // END 2018/01/25 E.Kameishi[QC#23681,MOD]
        }
        return arBalAmt;
    }
    //END 2017/12/19 E.Kameishi[QC#22096, ADD]

}
