/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC041001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.common.EZDPMsg;
import parts.common.EZDPStringItem;
import business.db.CUST_CR_PRFLTMsg;
import business.db.CUST_CR_PRFLTMsgArray;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.SVC_BILL_TPTMsg;
import business.parts.NSZC041001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC037002.NSZC037002;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CHK_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Service Credit Profile Check API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         A.Kohinata      Create          N/A
 * 2018/01/10   Hitachi         K.Kojima        Update          QC#22975
 * 2018/04/20   Hitachi         K.Ochiai        Update          QC#25757
 * 2018/07/19   Hitachi         A.Kohinata      Update          QC#27143
 * 2018/08/20   Hitachi         K.Kojima        Update          QC#27789
 * 2018/08/30   Hitachi         K.Kitachi       Update          QC#22665
 * 2018/10/19   Hitachi         K.fujimoto      Update          QC#28851
 * 2022/07/20   CITS            L.Mandanas      Update          QC#60316
 *</pre>
 */
public class NSZC041001 extends NSZC037002 {

    // START 2018/01/10 K.Kojima [QC#22975,ADD]
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** VarCharConst : CR_CHK_LVL_ACCT */
    private static final String VARCHAR_CR_CHK_LVL_ACCT = "CR_CHK_LVL_ACCT";

    // END 2018/01/10 K.Kojima [QC#22975,ADD]

    /** Input parameter "@" is a mandatory field. */
    private static final String NSZM0609E = "NSZM0609E";

    // START 2018/01/10 K.Kojima [QC#22975,DEL]
    // /** @ does not exist. */
    // private static final String NSZM0713E = "NSZM0713E";
    // END 2018/01/10 K.Kojima [QC#22975,DEL]

    /**
     * Constructor
     */
    public NSZC041001() {
        super();
        // START 2018/01/10 K.Kojima [QC#22975,ADD]
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // END 2018/01/10 K.Kojima [QC#22975,ADD]
    }

    /**
     * execute
     * @param ezdPMsg EZDPMsg
     * @param onBatchType ONBATCH_TYPE
     */
    @Override
    public void execute(EZDPMsg ezdPMsg, final ONBATCH_TYPE onBatchType) {
        NSZC041001PMsg pMsg = (NSZC041001PMsg) ezdPMsg;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        if (checkParameter(msgMap)) {
            doProcess(msgMap);
        }
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC041001PMsg pMsg = (NSZC041001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0609E, "Global Company Code");
        mandatoryCheck(msgMap, pMsg.slsDt, NSZM0609E, "Sales Date");
        mandatoryCheck(msgMap, pMsg.usrId, NSZM0609E, "User ID");
        mandatoryCheck(msgMap, pMsg.billToAcctNum, NSZM0609E, "Bill To Account Number");
        mandatoryCheck(msgMap, pMsg.billToCustCd, NSZM0609E, "Bill To Customer Code");
        // START 2018/04/20 K.Ochiai [QC#25757 DEL]
        // START 2018/01/10 K.Kojima [QC#22975,ADD]
//        mandatoryCheck(msgMap, pMsg.dsSvcCallTpCd, NSZM0609E, "Service Call Type Code");
//        mandatoryCheck(msgMap, pMsg.svcBillTpCd, NSZM0609E, "Service Bill Type Code");
        // END 2018/01/10 K.Kojima [QC#22975,ADD]
        // START 2018/04/20 K.Ochiai [QC#25757 DEL]

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId, String itemName) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgIdWithPrm(messageId, new String[] {itemName });
        }
    }

    // START 2018/01/10 K.Kojima [QC#22975,DEL]
    // END 2018/01/10 K.Kojima [QC#22975,DEL]

    // START 2018/01/10 K.Kojima [QC#22975,ADD]
    private void doProcess(S21ApiMessageMap msgMap) {
        NSZC041001PMsg pMsg = (NSZC041001PMsg) msgMap.getPmsg();

        String crChkLvlAcct = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CR_CHK_LVL_ACCT, pMsg.glblCmpyCd.getValue());
        boolean isCharge = isCharge(pMsg.glblCmpyCd.getValue(), pMsg.svcBillTpCd.getValue());
        boolean isSystemCall = isSystemCall(pMsg.glblCmpyCd.getValue(), pMsg.dsSvcCallTpCd.getValue());
        boolean isBillToLevelCheck = isBillToLevelCheck(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue(), crChkLvlAcct);
        boolean isAcctLevelCheck = isAcctLevelCheck(pMsg.glblCmpyCd.getValue(), pMsg.billToAcctNum.getValue(), isBillToLevelCheck);
        // START 2018/10/19 K.Fujimoto [QC#28851,ADD]
        boolean isCrChk = isCrChk(pMsg.glblCmpyCd.getValue(), pMsg.dsSvcCallTpCd.getValue());
        // END   2018/10/19 K.Fujimoto [QC#28851,ADD]
        
        // Non Billable
        if (!isCharge && !isSystemCall) {
            // Check Credit Profile (BillTo Level)
            if (isBillToLevelCheck) {
                if (!checkCreditProfileForBillTo(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue())) {
                    setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.CREDIT_PROFILE);
                    pMsg.A.setValidCount(1);
                    return;
                }
                // add start 2018/07/19 QC#27143
                // Check Over Due (BillTo Level)
                // START 2018/10/19 K.Fujimoto [QC#28851,MOD]
                if (isCrChk) {
                     if (!checkOverdueForBillTo(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue(), pMsg.svcTaskNum.getValue(), pMsg.slsDt.getValue())) {
                        setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.OVER_DUE);
                        pMsg.A.setValidCount(1);
                        return;
                     }
                }
                // END 2018/10/19 K.Fujimoto [QC#28851,MOD]
                // add end 2018/07/19 QC#27143
            }
            // Check Credit Profile (Account Level)
            if (isAcctLevelCheck) {
                if (!checkCreditProfileForAcct(pMsg.glblCmpyCd.getValue(), pMsg.billToAcctNum.getValue())) {
                    setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.CREDIT_PROFILE);
                    pMsg.A.setValidCount(1);
                    return;
                }
                // add start 2018/07/19 QC#27143
                // Check Over Due (Account Level)
                // START 2018/10/19 K.Fujimoto [QC#28851,MOD]
                if (isCrChk) {
                     if (!checkOverdueForAcct(pMsg.glblCmpyCd.getValue(), pMsg.billToAcctNum.getValue(), pMsg.svcTaskNum.getValue(), pMsg.slsDt.getValue())) {
                        setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.OVER_DUE);
                        pMsg.A.setValidCount(1);
                        return;
                     }
                }
                // END 2018/10/19 K.Fujimoto [QC#28851,MOD]
                // add end 2018/07/19 QC#27143
            }
        }

        // Billable
        if (isCharge) {
            // Check Credit Profile (BillTo Level)
            if (isBillToLevelCheck) {
                if (!checkCreditProfileForBillTo(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue())) {
                    setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.CREDIT_PROFILE);
                    pMsg.A.setValidCount(1);
                    return;
                }
                // Check Credit Balance And Past Due (BillTo Level)
                // START 2018/10/19 K.Fujimoto [QC#28851,MOD]
                if (isCrChk) {
                // END 2018/10/19 K.Fujimoto [QC#28851,MOD]
                    // START 2022/07/20 L.Mandanas [QC#60316, MOD]
                    //if (!checkCreditBalanceAndPastDueForBillTo(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue())) {
                    if (!checkCreditBalanceAndPastDueForBillTo(pMsg.glblCmpyCd.getValue(), pMsg.billToCustCd.getValue(),
                            pMsg.A.no(0).svcTaskHldRsnCd)) {
                    // END 2022/07/20 L.Mandanas [QC#60316, MOD]
                        // START 2018/08/30 K.Kitachi [QC#22665, MOD]
//                        setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.CREDIT_PROFILE);
                        // START 2022/07/20 L.Mandanas [QC#60316, DEL]
                        //setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.OVER_DUE);
                        // END 2022/07/20 L.Mandanas [QC#60316, DEL]
                        // END 2018/08/30 K.Kitachi [QC#22665, MOD]
                        pMsg.A.setValidCount(1);
                        return;
                    }
                }
            }
            // Check Credit Profile (Account Level)
            if (isAcctLevelCheck) {
                if (!checkCreditProfileForAcct(pMsg.glblCmpyCd.getValue(), pMsg.billToAcctNum.getValue())) {
                    setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.CREDIT_PROFILE);
                    pMsg.A.setValidCount(1);
                    return;
                }
                // Check Credit Balance And Past Due (Account Level)
                // START 2018/10/19 K.Fujimoto [QC#28851,MOD]
                if (isCrChk) {
                // END 2018/10/19 K.Fujimoto [QC#28851,MOD]
                    // START 2022/07/20 L.Mandanas [QC#60316, MOD]
                    //if (!checkCreditBalanceAndPastDueForAcct(pMsg.glblCmpyCd.getValue(), pMsg.billToAcctNum.getValue())) {
                    if (!checkCreditBalanceAndPastDueForAcct(pMsg.glblCmpyCd.getValue(), pMsg.billToAcctNum.getValue(),
                            pMsg.A.no(0).svcTaskHldRsnCd)) {
                    // END 2022/07/20 L.Mandanas [QC#60316, MOD]
                        // START 2018/08/30 K.Kitachi [QC#22665, MOD]
//                        setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.CREDIT_PROFILE);
                        // START 2022/07/20 L.Mandanas [QC#60316, DEL]
                        //setValue(pMsg.A.no(0).svcTaskHldRsnCd, SVC_TASK_HLD_RSN.OVER_DUE);
                        // END 2022/07/20 L.Mandanas [QC#60316, DEL]
                        // END 2018/08/30 K.Kitachi [QC#22665, MOD]
                        pMsg.A.setValidCount(1);
                    }
                }
            }
        }
    }

    // END 2018/01/10 K.Kojima [QC#22975,ADD]

    private CUST_CR_PRFLTMsgArray getCustCrPrfl(String glblCmpyCd, String billToCustCd) {
        CUST_CR_PRFLTMsg tMsg = new CUST_CR_PRFLTMsg();
        // START 2018/01/10 K.Kojima [QC#22975,MOD]
        // tMsg.setSQLID("001");
        tMsg.setSQLID("103");
        // END 2018/01/10 K.Kojima [QC#22975,MOD]
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        return (CUST_CR_PRFLTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private DS_ACCT_CR_PRFLTMsg getDsAcctCrPrfl(String glblCmpyCd, String billToAcctNum) {
        DS_ACCT_CR_PRFLTMsg tMsg = new DS_ACCT_CR_PRFLTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsAcctNum, billToAcctNum);
        return (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    // START 2018/01/10 K.Kojima [QC#22975,ADD]
    private boolean isAcctLevelCheck(String glblCmpyCd, String billToAcctNum, boolean isBillToLevelCheck) {
        if (isBillToLevelCheck) {
            return false;
        }
        DS_ACCT_CR_PRFLTMsg tMsg = getDsAcctCrPrfl(glblCmpyCd, billToAcctNum);
        if (tMsg != null) {
            return true;
        }
        return false;
    }

    private boolean isBillToLevelCheck(String glblCmpyCd, String billToCustCd, String crChkLvlAcct) {
        if (crChkLvlAcct != null && ZYPConstant.FLG_OFF_N.equals(crChkLvlAcct)) {
            CUST_CR_PRFLTMsgArray tMsgArray = getCustCrPrfl(glblCmpyCd, billToCustCd);
            if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isCharge(String glblCmpyCd, String svcBillTpCd) {
        // START 2018/04/20 K.Ochiai [QC#25757 ADD]
        if (!hasValue(svcBillTpCd)) {
            return true;
        }
        // END 2018/04/20 K.Ochiai [QC#25757 ADD]
        SVC_BILL_TPTMsg tMsg = new SVC_BILL_TPTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcBillTpCd, svcBillTpCd);
        tMsg = (SVC_BILL_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(tMsg.lborChrgFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(tMsg.prtChrgFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(tMsg.drumChrgFlg.getValue())
                || ZYPConstant.FLG_ON_Y.equals(tMsg.expChrgFlg.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isSystemCall(String glblCmpyCd, String dsSvcCallTpCd) {
        // START 2018/04/20 K.Ochiai [QC#25757 ADD]
        if (!hasValue(dsSvcCallTpCd)) {
            return false;
        }
        // END 2018/04/20 K.Ochiai [QC#25757 ADD]
        DS_SVC_CALL_TPTMsg tMsg = new DS_SVC_CALL_TPTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsSvcCallTpCd, dsSvcCallTpCd);
        tMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        if (ZYPConstant.FLG_OFF_N.equals(tMsg.svcCallFlg.getValue())) {
            return true;
        }
        return false;
    }

    private boolean isCrChk(String glblCmpyCd, String dsSvcCallTpCd) {
        // START 2018/04/20 K.Ochiai [QC#25757 ADD]
        if (!hasValue(dsSvcCallTpCd)) {
            // START 2018/08/20 K.Kojima [QC#27789,MOD]
            // return true;
            return false;
            // END 2018/08/20 K.Kojima [QC#27789,MOD]
        }
        // END 2018/04/20 K.Ochiai [QC#25757 ADD]
        DS_SVC_CALL_TPTMsg tMsg = new DS_SVC_CALL_TPTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsSvcCallTpCd, dsSvcCallTpCd);
        tMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(tMsg.crChkFlg.getValue())) {
            return true;
        }
        return false;
    }

    private boolean checkCreditProfileForBillTo(String glblCmpyCd, String billToCustCd) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("billToCustCd", billToCustCd);
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countCrHldForBillTo", paramMap);
        if (count != null && count.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }

    private boolean checkCreditProfileForAcct(String glblCmpyCd, String billToAcctNum) {
        DS_ACCT_CR_PRFLTMsg tMsg = getDsAcctCrPrfl(glblCmpyCd, billToAcctNum);
        if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.custHardHldFlg.getValue())) {
            return false;
        }
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("sellToCustCd", billToAcctNum);
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countCrHldForAcct", paramMap);
        if (count != null && count.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }

    // START 2022/07/20 L.Mandanas [QC#60316, MOD]
    //private boolean checkCreditBalanceAndPastDueForBillTo(String glblCmpyCd, String billToCustCd) {
    private boolean checkCreditBalanceAndPastDueForBillTo(String glblCmpyCd, String billToCustCd, EZDPStringItem svcTaskHldRsnCd) {
    // END 2022/07/20 L.Mandanas [QC#60316, MOD]
        CUST_CR_PRFLTMsgArray tMsgArray = getCustCrPrfl(glblCmpyCd, billToCustCd);
        for (int i = 0; i < tMsgArray.length(); i++) {
            CUST_CR_PRFLTMsg tMsg = tMsgArray.no(i);
            if (CR_CHK_REQ_TP.HOLD.equals(tMsg.crChkReqTpCd.getValue())) {
                if (hasValue(tMsg.crBalAmt) && tMsg.crBalAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
                    // START 2022/07/20 L.Mandanas [QC#60316, ADD]
                    setValue(svcTaskHldRsnCd, SVC_TASK_HLD_RSN.CREDIT_LIMIT);
                    // END 2022/07/20 L.Mandanas [QC#60316, ADD]
                    return false;
                }
                if (hasValue(tMsg.ovdWsFlg) && ZYPConstant.FLG_ON_Y.equals(tMsg.ovdWsFlg.getValue())) {
                    // START 2022/07/20 L.Mandanas [QC#60316, ADD]
                    setValue(svcTaskHldRsnCd, SVC_TASK_HLD_RSN.OVER_DUE);
                    // END 2022/07/20 L.Mandanas [QC#60316, ADD]
                    return false;
                }
            }
        }
        return true;
    }

    // START 2022/07/20 L.Mandanas [QC#60316, MOD]
    //private boolean checkCreditBalanceAndPastDueForAcct(String glblCmpyCd, String billToAcctNum) {
    private boolean checkCreditBalanceAndPastDueForAcct(String glblCmpyCd, String billToAcctNum, EZDPStringItem svcTaskHldRsnCd) {
    // END 2022/07/20 L.Mandanas [QC#60316, MOD]
        DS_ACCT_CR_PRFLTMsg tMsg = getDsAcctCrPrfl(glblCmpyCd, billToAcctNum);
        if (tMsg != null && CR_CHK_REQ_TP.HOLD.equals(tMsg.crChkReqTpCd.getValue())) {
            if (hasValue(tMsg.crBalAmt) && tMsg.crBalAmt.getValue().compareTo(BigDecimal.ZERO) < 0) {
                // START 2022/07/20 L.Mandanas [QC#60316, ADD]
                setValue(svcTaskHldRsnCd, SVC_TASK_HLD_RSN.CREDIT_LIMIT);
                // END 2022/07/20 L.Mandanas [QC#60316, ADD]
                return false;
            }
            if (hasValue(tMsg.ovdWsFlg) && ZYPConstant.FLG_ON_Y.equals(tMsg.ovdWsFlg.getValue())) {
                // START 2022/07/20 L.Mandanas [QC#60316, ADD]
                setValue(svcTaskHldRsnCd, SVC_TASK_HLD_RSN.OVER_DUE);
                // END 2022/07/20 L.Mandanas [QC#60316, ADD]
                return false;
            }
        }
        return true;
    }
    // END 2018/01/10 K.Kojima [QC#22975,ADD]

    // add start 2018/07/19 QC#27143
    private boolean checkOverdueForBillTo(String glblCmpyCd, String billToCustCd, String svcTaskNum, String slsDt) {
        if (!isBillToLevelCrChkReq(glblCmpyCd, billToCustCd)) {
            return true;
        }

        List<String> dsContrNumList = getDsContrNumList(glblCmpyCd, svcTaskNum, slsDt);
        if (dsContrNumList == null || dsContrNumList.isEmpty()) {
            return true;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("billToCustCd", billToCustCd);
        paramMap.put("slsDt", slsDt);
        paramMap.put("arTrxTpInvoice", AR_TRX_TP.INVOICE);
        paramMap.put("arCashApplyStsUnapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        paramMap.put("arCashApplyStsPartial", AR_CASH_APPLY_STS.PARTIAL);
        paramMap.put("dsContrNumList", dsContrNumList);
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countOverdueForBillTo", paramMap);
        if (count != null && count.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }

    private boolean checkOverdueForAcct(String glblCmpyCd, String billToAcctNum, String svcTaskNum, String slsDt) {
        if (!isAcctLevelCrChkReq(glblCmpyCd, billToAcctNum)) {
            return true;
        }

        List<String> dsContrNumList = getDsContrNumList(glblCmpyCd, svcTaskNum, slsDt);
        if (dsContrNumList == null || dsContrNumList.isEmpty()) {
            return true;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("billToCustAcctCd", billToAcctNum);
        paramMap.put("slsDt", slsDt);
        paramMap.put("arTrxTpInvoice", AR_TRX_TP.INVOICE);
        paramMap.put("arCashApplyStsUnapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        paramMap.put("arCashApplyStsPartial", AR_CASH_APPLY_STS.PARTIAL);
        paramMap.put("dsContrNumList", dsContrNumList);
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countOverdueForAcct", paramMap);
        if (count != null && count.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        return true;
    }

    private boolean isBillToLevelCrChkReq(String glblCmpyCd, String billToCustCd) {
        CUST_CR_PRFLTMsgArray tMsgArray = getCustCrPrfl(glblCmpyCd, billToCustCd);
        for (int i = 0; i < tMsgArray.length(); i++) {
            CUST_CR_PRFLTMsg tMsg = tMsgArray.no(i);
            if (CR_CHK_REQ_TP.HOLD.equals(tMsg.crChkReqTpCd.getValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAcctLevelCrChkReq(String glblCmpyCd, String billToAcctNum) {
        DS_ACCT_CR_PRFLTMsg tMsg = getDsAcctCrPrfl(glblCmpyCd, billToAcctNum);
        if (tMsg != null && CR_CHK_REQ_TP.HOLD.equals(tMsg.crChkReqTpCd.getValue())) {
            return true;
        }
        return false;
    }

    private List<String> getDsContrNumList(String glblCmpyCd, String svcTaskNum, String slsDt) {
        if (!hasValue(svcTaskNum)) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        paramMap.put("slsDt", slsDt);
        paramMap.put("dsContrCatgWty", DS_CONTR_CATG.WARRANTY);
        return (List<String>) ssmBatchClient.queryObjectList("getContrNum", paramMap);
    }
    // add end 2018/07/19 QC#27143
}
