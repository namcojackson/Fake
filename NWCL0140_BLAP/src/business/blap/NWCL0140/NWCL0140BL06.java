/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0140;

import static business.blap.NWCL0140.constant.NWCL0140Constant.BILL_TO_CUST;
import static business.blap.NWCL0140.constant.NWCL0140Constant.CFS_LEASE_ATTRB;
import static business.blap.NWCL0140.constant.NWCL0140Constant.CFS_LEASE_ELIG_ACCT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.CFS_LEASE_ELIG_ORD_CATG;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_BILL_TO_CUST_ACCT_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_BILL_TO_CUST_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_CFS_LEASE_ELIG_ACCT_PK_LIST;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_CFS_LEASE_ELIG_ORD_CATG_PK_LIST;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_DS_ORD_CATG_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_DS_ORD_CATG_DESC_TXT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_DS_ORD_TP_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_DS_ORD_TP_DESC_TXT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DS_ORD_CATG;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DS_ORD_TP;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_CMN_SUBMIT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.INV_THRHD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.MSG_PARAM_ACCT_NUM;
import static business.blap.NWCL0140.constant.NWCL0140Constant.MSG_PARAM_LOC_NUM;
import static business.blap.NWCL0140.constant.NWCL0140Constant.MSG_PARAM_ORD_CATG;
import static business.blap.NWCL0140.constant.NWCL0140Constant.MSG_PARAM_REASON;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0109E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0110E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0137E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0138E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0139E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0140E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0141E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0142E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.SUBMIT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWCL0140.common.NWCL0140CommonLogic;
import business.db.CFS_LEASE_ATTRBTMsg;
import business.db.CFS_LEASE_ELIG_ACCTTMsg;
import business.db.CFS_LEASE_ELIG_ORD_CATGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * NWCL0140 CFS Lease Package Maintenance Screen
 * Function Name : update business process
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NWCL0140BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NWCL0140_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_CMN_Submit((NWCL0140CMsg) cMsg, (NWCL0140SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    /**
     * doProcess_CMN_Submit
     * @param cMsg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     */
    private void doProcess_CMN_Submit(NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        boolean result = false;

        String glblCmpyCd = getUserProfileService().getGlobalCompanyCode();

        // glblMsg update Eligible Account and Bill To area
        NWCL0140CommonLogic.updateGlblMsgEL(cMsg, sMsg);
        // glblMsg update Exclusion/Inclusion Order Category area
        NWCL0140CommonLogic.updateGlblMsgEX(cMsg, sMsg);

        try {
            // Validate Eligible Account and Bill To area
            result = validateAreaEL(glblCmpyCd, cMsg, sMsg);
            if (result == false) {
                return;
            }
            // Validate Exclusion/Inclusion Order Category area
            result = validateAndSetCdAreaEX(glblCmpyCd, cMsg, sMsg);
            if (result == false) {
                return;
            }
            // Submit Eligible Account and Bill To area
            result = submitAreaEL(glblCmpyCd, cMsg, sMsg);
            if (result == false) {
                return;
            }
            // Validate and Submit Invoiced Threshold
            result = validateAndSubmitAreaIT(glblCmpyCd, cMsg, sMsg);
            if (result == false) {
                return;
            }
            // Submit Exclusion/Inclusion Order Category area
            result = submitAreaEX(glblCmpyCd, cMsg, sMsg);
            if (result == false) {
                return;
            }
            // Logical remove Eligible Account and Bill To area
            result = logicalRemoveAreaEL(glblCmpyCd, cMsg, sMsg);
            if (result == false) {
                return;
            }
            // Logical remove Exclusion/Inclusion Order Category area
            result = logicalRemoveAreaEX(glblCmpyCd, cMsg, sMsg);
            if (result == false) {
                return;
            }
            cMsg.setMessageInfo(ZZZM9003I, new String[] {SUBMIT });
        } finally {
            NWCL0140CommonLogic.loadOnePageToCMsgEL(cMsg, cMsg.A, sMsg, sMsg.A);
            NWCL0140CommonLogic.loadOnePageToCMsgEX(cMsg, cMsg.B, sMsg, sMsg.B);
        }

    }

    /**
     * Validate Eligible Account and Bill To area
     * @param glblCmpyCd String
     * @param cMSg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     * @return boolean
     */
    private boolean validateAreaEL(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        int maxDisplayRows = cMsg.A.length();
        int maxIndex = sMsg.A.getValidCount();

        // All Pk List
        List<BigDecimal> cfsLeaseEligAcctPkList = new ArrayList<BigDecimal>();

        // glblMsg Validation Check
        for (int i = 0; i < maxIndex; i++) {
            // mandatory field check
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).billToCustAcctCd_EL)) {
                sMsg.A.no(i).billToCustAcctCd_EL.setErrorInfo(1, NWCM0139E, new String[] {MSG_PARAM_ACCT_NUM });
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum_EL.setValue(errScrnInex);
                return false;
            }

            // Duplicate check
            String chkBillToCustAcctCd = sMsg.A.no(i).billToCustAcctCd_EL.getValue();
            String chkBillToCustCd = null;
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).billToCustCd_EL)) {
                chkBillToCustCd = sMsg.A.no(i).billToCustCd_EL.getValue();
            }

            // Detail Loop
            for (int j = i + 1; j < maxIndex; j++) {
                if (chkBillToCustAcctCd.equals(sMsg.A.no(j).billToCustAcctCd_EL.getValue())) {
                    // Check Location number
                    String glBillToCustCd = null;
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(j).billToCustCd_EL)) {
                        glBillToCustCd = sMsg.A.no(j).billToCustCd_EL.getValue();
                    }
                    // Location Number blank
                    if (chkBillToCustCd == null && glBillToCustCd == null) {
                        sMsg.A.no(i).billToCustAcctCd_EL.setErrorInfo(1, NWCM0140E, new String[] {MSG_PARAM_ACCT_NUM });
                        sMsg.A.no(i).billToCustCd_EL.setErrorInfo(1, NWCM0140E, new String[] {MSG_PARAM_ACCT_NUM });
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_EL.setValue(errScrnInex);
                        return false;
                    }

                    // Location number duplicate
                    if (chkBillToCustCd != null && chkBillToCustCd.equals(glBillToCustCd)) {
                        sMsg.A.no(i).billToCustAcctCd_EL.setErrorInfo(1, NWCM0140E, new String[] {MSG_PARAM_LOC_NUM });
                        sMsg.A.no(i).billToCustCd_EL.setErrorInfo(1, NWCM0140E, new String[] {MSG_PARAM_LOC_NUM });
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_EL.setValue(errScrnInex);
                        return false;
                    }
                }
            }

            // DB All Check parameter
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).cfsLeaseEligAcctPk_EL)) {
                cfsLeaseEligAcctPkList.add(sMsg.A.no(i).cfsLeaseEligAcctPk_EL.getValue());
            }

        }
        // DB All Check
        for (int i = 0; i < maxIndex; i++) {
            // Check New Line
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).cfsLeaseEligAcctPk_EL)) {
                // CFS_LEASE_ELIG_ACCT Record Check
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_BILL_TO_CUST_ACCT_CD, sMsg.A.no(i).billToCustAcctCd_EL.getValue());
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).billToCustCd_EL)) {
                    ssmParam.put(DB_PARAM_BILL_TO_CUST_CD, sMsg.A.no(i).billToCustCd_EL.getValue());
                }
                ssmParam.put(DB_PARAM_CFS_LEASE_ELIG_ACCT_PK_LIST, cfsLeaseEligAcctPkList);

                S21SsmEZDResult result = NWCL0140Query.getInstance().findSingleRecord("countCfsLeaseEligAcct", ssmParam);
                if (result.isCodeNormal()) {
                    BigDecimal retCnt = (BigDecimal) result.getResultObject();
                    if (BigDecimal.ZERO.compareTo(retCnt) == -1) {
                        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).billToCustCd_EL)) {
                            sMsg.A.no(i).billToCustAcctCd_EL.setErrorInfo(1, NWCM0141E, new String[] {MSG_PARAM_LOC_NUM });
                            sMsg.A.no(i).billToCustCd_EL.setErrorInfo(1, NWCM0141E, new String[] {MSG_PARAM_LOC_NUM });
                        } else {
                            sMsg.A.no(i).billToCustAcctCd_EL.setErrorInfo(1, NWCM0141E, new String[] {MSG_PARAM_ACCT_NUM });
                            sMsg.A.no(i).billToCustCd_EL.setErrorInfo(1, NWCM0141E, new String[] {MSG_PARAM_ACCT_NUM });
                        }
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_EL.setValue(errScrnInex);
                        return false;
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(sMsg.A.no(i).billToCustCd_EL)) {
                        sMsg.A.no(i).billToCustAcctCd_EL.setErrorInfo(1, NWCM0141E, new String[] {MSG_PARAM_LOC_NUM });
                        sMsg.A.no(i).billToCustCd_EL.setErrorInfo(1, NWCM0141E, new String[] {MSG_PARAM_LOC_NUM });
                    } else {
                        sMsg.A.no(i).billToCustAcctCd_EL.setErrorInfo(1, NWCM0141E, new String[] {MSG_PARAM_ACCT_NUM });
                        sMsg.A.no(i).billToCustCd_EL.setErrorInfo(1, NWCM0141E, new String[] {MSG_PARAM_ACCT_NUM });
                    }
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum_EL.setValue(errScrnInex);
                    return false;
                }
            }

            // Account Number and Location Number Master Check
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_BILL_TO_CUST_ACCT_CD, sMsg.A.no(i).billToCustAcctCd_EL.getValue());
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).billToCustCd_EL)) {
                ssmParam.put(DB_PARAM_BILL_TO_CUST_CD, sMsg.A.no(i).billToCustCd_EL.getValue());
            }
            ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

            S21SsmEZDResult result = NWCL0140Query.getInstance().findSingleRecord("countBillSellCd", ssmParam);
            if (result.isCodeNormal()) {
                BigDecimal retCnt = (BigDecimal) result.getResultObject();
                if (BigDecimal.ZERO.compareTo(retCnt) == 0) {
                    sMsg.A.no(i).billToCustAcctCd_EL.setErrorInfo(1, NWCM0142E, new String[] {BILL_TO_CUST, sMsg.A.no(i).billToCustAcctCd_EL.getValue() });
                    sMsg.A.no(i).billToCustCd_EL.setErrorInfo(1, NWCM0142E, new String[] {BILL_TO_CUST, sMsg.A.no(i).billToCustAcctCd_EL.getValue() });
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum_EL.setValue(errScrnInex);
                    return false;
                }
            } else {
                sMsg.A.no(i).billToCustAcctCd_EL.setErrorInfo(1, NWCM0142E, new String[] {BILL_TO_CUST, sMsg.A.no(i).billToCustAcctCd_EL.getValue() });
                sMsg.A.no(i).billToCustCd_EL.setErrorInfo(1, NWCM0142E, new String[] {BILL_TO_CUST, sMsg.A.no(i).billToCustAcctCd_EL.getValue() });
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum_EL.setValue(errScrnInex);
                return false;
            }
        }
        return true;
    }

    /**
     * Submit Eligible Account and Bill To area
     * @param glblCmpyCd String
     * @param cMSg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     * @return boolean
     */
    private boolean submitAreaEL(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        int maxIndex = sMsg.A.getValidCount();

        for (int i = 0; i < maxIndex; i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).cfsLeaseEligAcctPk_EL)) {
                // New Line
                CFS_LEASE_ELIG_ACCTTMsg tMsg = new CFS_LEASE_ELIG_ACCTTMsg();
                BigDecimal cfsLeaseEligAcctPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_ELIG_ACCT_SQ);
                ZYPEZDItemValueSetter.setValue(tMsg.cfsLeaseEligAcctPk, cfsLeaseEligAcctPk);
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, sMsg.A.no(i).billToCustAcctCd_EL);
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, sMsg.A.no(i).billToCustCd_EL);
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NWCM0109E, new String[] {CFS_LEASE_ELIG_ACCT });
                    return false;
                }
            } else {
                // Update Line
                BigDecimal cfsLeaseEligAcctPk = sMsg.A.no(i).cfsLeaseEligAcctPk_EL.getValue();
                CFS_LEASE_ELIG_ACCTTMsg tMsg = lockCfsLeaseEligAcctForUpdate(cMsg, glblCmpyCd, sMsg.A.no(i).cfsLeaseEligAcctPk_EL.getValue(), sMsg.A.no(i).ezUpTime_EL.getValue(), sMsg.A.no(i).ezUpTimeZone_EL.getValue());
                if (NWCM0137E.equals(cMsg.getMessageCode())) {
                    return false;
                }
                if (tMsg == null) {
                    cMsg.setMessageInfo(NWCM0137E, null);
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustAcctCd, sMsg.A.no(i).billToCustAcctCd_EL);
                ZYPEZDItemValueSetter.setValue(tMsg.billToCustCd, sMsg.A.no(i).billToCustCd_EL);
                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NWCM0110E, new String[] {CFS_LEASE_ELIG_ACCT, String.valueOf(cfsLeaseEligAcctPk) });
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * Validate Exclusion/Inclusion Order Category area
     * @param glblCmpyCd String
     * @param cMSg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     * @return boolean
     */
    private boolean validateAndSetCdAreaEX(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        int maxDisplayRows = cMsg.B.length();
        int maxIndex = sMsg.B.getValidCount();

        // All Pk List
        List<BigDecimal> cfsLeaseEligOrdCatgPkList = new ArrayList<BigDecimal>();

        // set code value
        for (int i = 0; i < maxIndex; i++) {
            // mandatory field check
            if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdCatgDescTxt_EX)) {
                sMsg.B.no(i).dsOrdCatgDescTxt_EX.setErrorInfo(1, NWCM0139E, new String[] {MSG_PARAM_ORD_CATG });
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum_EX.setValue(errScrnInex);
                return false;
            }

            if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).dsOrdTpDescTxt_EX)) {
                sMsg.B.no(i).dsOrdTpDescTxt_EX.setErrorInfo(1, NWCM0139E, new String[] {MSG_PARAM_REASON });
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum_EX.setValue(errScrnInex);
                return false;
            }

            // Category Code Master Check and set DS_ORD_CATG_CD value
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_DS_ORD_CATG_DESC_TXT, sMsg.B.no(i).dsOrdCatgDescTxt_EX.getValue());

            S21SsmEZDResult result = NWCL0140Query.getInstance().findSingleRecord("findDsOrdCatg", ssmParam);
            if (result.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).dsOrdCatgCd_EX, (String) result.getResultObject());
            } else {
                sMsg.B.no(i).dsOrdCatgDescTxt_EX.setErrorInfo(1, NWCM0142E, new String[] {DS_ORD_CATG, sMsg.B.no(i).dsOrdCatgDescTxt_EX.getValue() });
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum_EX.setValue(errScrnInex);
                return false;
            }

            // Order Type Code Master Check and set DS_ORD_TP_CD value
            ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_DS_ORD_CATG_CD, sMsg.B.no(i).dsOrdCatgCd_EX.getValue());
            ssmParam.put(DB_PARAM_DS_ORD_TP_DESC_TXT, sMsg.B.no(i).dsOrdTpDescTxt_EX.getValue());

            result = NWCL0140Query.getInstance().findSingleRecord("findDsOrdTp", ssmParam);
            if (result.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).dsOrdTpCd_EX, (String) result.getResultObject());
            } else {
                sMsg.B.no(i).dsOrdTpDescTxt_EX.setErrorInfo(1, NWCM0142E, new String[] {DS_ORD_TP, sMsg.B.no(i).dsOrdTpDescTxt_EX.getValue() });
                sMsg.B.no(i).dsOrdCatgDescTxt_EX.setErrorInfo(1, NWCM0142E, new String[] {DS_ORD_TP, sMsg.B.no(i).dsOrdCatgDescTxt_EX.getValue() });
                int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                cMsg.xxPageShowFromNum_EX.setValue(errScrnInex);
                return false;
            }
        }

        // Duplicate Check
        for (int i = 0; i < maxIndex; i++) {
            // Check Value
            String chkDsOrdCatgCd = sMsg.B.no(i).dsOrdCatgCd_EX.getValue();
            String chkDsOrdTpCd = sMsg.B.no(i).dsOrdTpCd_EX.getValue();

            // Detail Loop
            for (int j = i + 1; j < maxIndex; j++) {
                if (chkDsOrdCatgCd.equals(sMsg.B.no(j).dsOrdCatgCd_EX.getValue())) {
                    // Check Reason
                    String glDsOrdTpCd = sMsg.B.no(j).dsOrdTpCd_EX.getValue();
                    // Reason duplicate check
                    if (chkDsOrdTpCd.equals(glDsOrdTpCd)) {
                        sMsg.B.no(i).dsOrdCatgDescTxt_EX.setErrorInfo(1, NWCM0138E, null);
                        sMsg.B.no(i).dsOrdTpDescTxt_EX.setErrorInfo(1, NWCM0138E, null);
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_EX.setValue(errScrnInex);
                        return false;
                    }
                }
            }

            // DB All Check parameter
            if (ZYPCommonFunc.hasValue(sMsg.B.no(i).cfsLeaseEligOrdCatgPk_EX)) {
                cfsLeaseEligOrdCatgPkList.add(sMsg.B.no(i).cfsLeaseEligOrdCatgPk_EX.getValue());
            }

        }

        // DB All Check
        for (int i = 0; i < maxIndex; i++) {
            // Check New Line
            if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).cfsLeaseEligOrdCatgPk_EX)) {
                // CFS_LEASE_ELIG_ACCT Record Check
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
                ssmParam.put(DB_PARAM_DS_ORD_CATG_CD, sMsg.B.no(i).dsOrdCatgCd_EX.getValue());
                ssmParam.put(DB_PARAM_DS_ORD_TP_CD, sMsg.B.no(i).dsOrdTpCd_EX.getValue());
                ssmParam.put(DB_PARAM_CFS_LEASE_ELIG_ORD_CATG_PK_LIST, cfsLeaseEligOrdCatgPkList);

                S21SsmEZDResult result = NWCL0140Query.getInstance().findSingleRecord("countCfsLeaseEligOrdCatg", ssmParam);

                if (result.isCodeNormal()) {
                    BigDecimal retCnt = (BigDecimal) result.getResultObject();
                    if (BigDecimal.ZERO.compareTo(retCnt) == -1) {
                        sMsg.B.no(i).dsOrdCatgDescTxt_EX.setErrorInfo(1, NWCM0138E, null);
                        sMsg.B.no(i).dsOrdTpDescTxt_EX.setErrorInfo(1, NWCM0138E, null);
                        int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                        cMsg.xxPageShowFromNum_EX.setValue(errScrnInex);
                        return false;
                    }
                } else {
                    sMsg.B.no(i).dsOrdCatgDescTxt_EX.setErrorInfo(1, NWCM0138E, null);
                    sMsg.B.no(i).dsOrdTpDescTxt_EX.setErrorInfo(1, NWCM0138E, null);
                    int errScrnInex = (i / maxDisplayRows) * maxDisplayRows + 1;
                    cMsg.xxPageShowFromNum_EX.setValue(errScrnInex);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Submit Exclusion/Inclusion Order Category area
     * @param glblCmpyCd String
     * @param cMSg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     * @return boolean
     */
    private boolean submitAreaEX(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        int maxIndex = sMsg.B.getValidCount();

        for (int i = 0; i < maxIndex; i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.B.no(i).cfsLeaseEligOrdCatgPk_EX)) {
                // New Line
                CFS_LEASE_ELIG_ORD_CATGTMsg tMsg = new CFS_LEASE_ELIG_ORD_CATGTMsg();
                BigDecimal cfsLeaseEligAcctPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_LEASE_ELIG_ORD_CATG_SQ);
                ZYPEZDItemValueSetter.setValue(tMsg.cfsLeaseEligOrdCatgPk, cfsLeaseEligAcctPk);
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, sMsg.B.no(i).dsOrdCatgCd_EX);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, sMsg.B.no(i).dsOrdTpCd_EX);
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).ordCatgInclFlg_EX.getValue())) {
                    ZYPEZDItemValueSetter.setValue(tMsg.ordCatgInclFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.ordCatgInclFlg, ZYPConstant.FLG_OFF_N);
                }

                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NWCM0109E, new String[] {CFS_LEASE_ELIG_ORD_CATG });
                    return false;
                }
            } else {
                // Update Line
                BigDecimal cfsLeaseEligOrdCatgPk = sMsg.B.no(i).cfsLeaseEligOrdCatgPk_EX.getValue();
                CFS_LEASE_ELIG_ORD_CATGTMsg tMsg = lockCfsLeaseEligOrdCatgForUpdate(cMsg, glblCmpyCd, sMsg.B.no(i).cfsLeaseEligOrdCatgPk_EX.getValue(), sMsg.B.no(i).ezUpTime_EX.getValue(), sMsg.B.no(i).ezUpTimeZone_EX.getValue());
                if (NWCM0137E.equals(cMsg.getMessageCode())) {
                    return false;
                }

                if (tMsg == null) {
                    cMsg.setMessageInfo(NWCM0137E, null);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, sMsg.B.no(i).dsOrdCatgCd_EX);
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, sMsg.B.no(i).dsOrdTpCd_EX);
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).ordCatgInclFlg_EX.getValue())) {
                    ZYPEZDItemValueSetter.setValue(tMsg.ordCatgInclFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(tMsg.ordCatgInclFlg, ZYPConstant.FLG_OFF_N);
                }
                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NWCM0110E, new String[] {CFS_LEASE_ELIG_ORD_CATG, String.valueOf(cfsLeaseEligOrdCatgPk) });
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * Logical remove Eligible Account and Bill To area
     * @param glblCmpyCd String
     * @param cMSg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     * @return boolean
     */
    private boolean logicalRemoveAreaEL(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        int maxIndex = sMsg.C.getValidCount();

        for (int i = 0; i < maxIndex; i++) {
            // Update Line
            BigDecimal cfsLeaseEligAcctPk = sMsg.C.no(i).cfsLeaseEligAcctPk_DL.getValue();
            CFS_LEASE_ELIG_ACCTTMsg tMsg = lockCfsLeaseEligAcctForUpdate(cMsg, glblCmpyCd, sMsg.C.no(i).cfsLeaseEligAcctPk_DL.getValue(), sMsg.C.no(i).ezUpTime_DL.getValue(), sMsg.C.no(i).ezUpTimeZone_DL.getValue());
            if (NWCM0137E.equals(cMsg.getMessageCode())) {
                return false;
            }
            if (tMsg == null) {
                cMsg.setMessageInfo(NWCM0137E, null);
                return false;
            }
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NWCM0110E, new String[] {CFS_LEASE_ELIG_ACCT, String.valueOf(cfsLeaseEligAcctPk) });
                return false;
            }

        }
        return true;
    }

    /**
     * Logical remove Exclusion/Inclusion Order Category area
     * @param glblCmpyCd String
     * @param cMSg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     * @return boolean
     */
    private boolean logicalRemoveAreaEX(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        int maxIndex = sMsg.D.getValidCount();

        for (int i = 0; i < maxIndex; i++) {
            // Update Line
            BigDecimal cfsLeaseEligOrdCatgPk = sMsg.D.no(i).cfsLeaseEligOrdCatgPk_DX.getValue();
            CFS_LEASE_ELIG_ORD_CATGTMsg tMsg = lockCfsLeaseEligOrdCatgForUpdate(cMsg, glblCmpyCd, sMsg.D.no(i).cfsLeaseEligOrdCatgPk_DX.getValue(), sMsg.D.no(i).ezUpTime_DX.getValue(), sMsg.D.no(i).ezUpTimeZone_DX.getValue());
            if (NWCM0137E.equals(cMsg.getMessageCode())) {
                return false;
            }

            if (tMsg == null) {
                cMsg.setMessageInfo(NWCM0137E, null);
                return false;
            }
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NWCM0110E, new String[] {CFS_LEASE_ELIG_ORD_CATG, String.valueOf(cfsLeaseEligOrdCatgPk) });
                return false;
            }

        }
        return true;
    }

    /**
     * Validate and submit Invoiced Threshold Area
     * @param glblCmpyCd String
     * @param cMSg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     * @return boolean
     */
    private boolean validateAndSubmitAreaIT(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {

        CFS_LEASE_ATTRBTMsg tMsg = lockCfsLeaseAttrbForUpdate(cMsg, sMsg, glblCmpyCd);
        if (NWCM0137E.equals(cMsg.getMessageCode())) {
            return false;
        }

        if (tMsg == null) {
            cMsg.attrbValNum.setErrorInfo(1, NWCM0137E, null);
            return false;
        } else {
            // update
            ZYPEZDItemValueSetter.setValue(tMsg.attrbValNum, cMsg.attrbValNum);
            EZDTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NWCM0110E, new String[] {CFS_LEASE_ATTRB, INV_THRHD });
                return false;
            }
        }
        return true;
    }

    /**
     * CFS_LEASE_ELIG_ACCT Table Record Lock
     * @param cMsg NWCL0140CMsg
     * @param cfsLeaseEligAcctPk BigDecimal
     * @param ezUpTime String
     * @param ezUpTimeZone String
     * @return CFS_LEASE_ELIG_ACCTTMsg
     */
    private static CFS_LEASE_ELIG_ACCTTMsg lockCfsLeaseEligAcctForUpdate(NWCL0140CMsg cMsg, String glblCmpyCd, BigDecimal cfsLeaseEligAcctPk, String ezUpTime, String ezUpTimeZone) {

        CFS_LEASE_ELIG_ACCTTMsg tMsg = new CFS_LEASE_ELIG_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.cfsLeaseEligAcctPk, cfsLeaseEligAcctPk);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);

        tMsg = (CFS_LEASE_ELIG_ACCTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            return null;
        }

        String findEzUpTime = ezUpTime;
        String findEzUpTimeZone = ezUpTimeZone;
        String currentEzUpTime = tMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NWCM0137E, null);
            return null;
        }
        return tMsg;
    }

    /**
     * CFS_LEASE_ATTRB Table Record Lock
     * @param cMsg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     * @param glblCmpyCd String
     * @return CFS_LEASE_ATTRBTMsg
     */
    private static CFS_LEASE_ATTRBTMsg lockCfsLeaseAttrbForUpdate(NWCL0140CMsg cMsg, NWCL0140SMsg sMsg, String glblCmpyCd) {

        CFS_LEASE_ATTRBTMsg tMsg = new CFS_LEASE_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.attrbKeyNm, INV_THRHD);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);

        tMsg = (CFS_LEASE_ATTRBTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            return null;
        }

        String findEzUpTime = cMsg.ezUpTime_IT.getValue();
        String findEzUpTimeZone = cMsg.ezUpTimeZone_IT.getValue();
        String currentEzUpTime = tMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NWCM0137E, null);
            return null;
        }
        return tMsg;
    }

    /**
     * CFS_LEASE_ELIG_ORD_CATG Table Record Lock
     * @param cMsg NWCL0140CMsg
     * @param glblCmpyCd String
     * @param cfsLeaseEligOrdCatgPk BigDecimal
     * @param ezUpTime String
     * @param ezUpTimeZone String
     * @return CFS_LEASE_ELIG_ORD_CATGTMsg
     */
    private static CFS_LEASE_ELIG_ORD_CATGTMsg lockCfsLeaseEligOrdCatgForUpdate(NWCL0140CMsg cMsg, String glblCmpyCd, BigDecimal cfsLeaseEligOrdCatgPk, String ezUpTime, String ezUpTimeZone) {

        CFS_LEASE_ELIG_ORD_CATGTMsg tMsg = new CFS_LEASE_ELIG_ORD_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.cfsLeaseEligOrdCatgPk, cfsLeaseEligOrdCatgPk);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);

        tMsg = (CFS_LEASE_ELIG_ORD_CATGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tMsg);
        if (tMsg == null) {
            return null;
        }

        String findEzUpTime = ezUpTime;
        String findEzUpTimeZone = ezUpTimeZone;
        String currentEzUpTime = tMsg.ezUpTime.getValue();
        String currentEzUpTimeZone = tMsg.ezUpTimeZone.getValue();

        if (!ZYPDateUtil.isSameTimeStamp(findEzUpTime, findEzUpTimeZone, currentEzUpTime, currentEzUpTimeZone)) {
            cMsg.setMessageInfo(NWCM0137E, new String[] {CFS_LEASE_ELIG_ORD_CATG, String.valueOf(tMsg.cfsLeaseEligOrdCatgPk.getValue()) });
            return null;
        }
        return tMsg;
    }
}
