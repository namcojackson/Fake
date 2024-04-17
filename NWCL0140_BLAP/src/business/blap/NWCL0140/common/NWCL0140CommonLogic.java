/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0140.common;

import static business.blap.NWCL0140.constant.NWCL0140Constant.ATTRB_VAL_NUM;
import static business.blap.NWCL0140.constant.NWCL0140Constant.BILL_TO_CUST_ACCT_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.BILL_TO_CUST_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.CFS_LEASE_ATTRB;
import static business.blap.NWCL0140.constant.NWCL0140Constant.CFS_LEASE_ELIG_ACCT_PK;
import static business.blap.NWCL0140.constant.NWCL0140Constant.CFS_LEASE_ELIG_ORD_CATG_PK;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_ATTRB_KEY_NM;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_BILL_TO_CUST_ACCT_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_BILL_TO_CUST_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_DS_ORD_CATG_DESC_TXT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_DS_ORD_TP_DESC_TXT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DB_PARAM_ROWNUM;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DS_ACCT_NM;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DS_ORD_CATG_DESC_TXT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DS_ORD_TP_CD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.DS_ORD_TP_DESC_TXT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EVENT_NM_NWCL0140_CMN_SUBMIT;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EZUPTIME;
import static business.blap.NWCL0140.constant.NWCL0140Constant.EZUPTIMEZONE;
import static business.blap.NWCL0140.constant.NWCL0140Constant.INV_THRHD;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0003I;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0135I;
import static business.blap.NWCL0140.constant.NWCL0140Constant.NWCM0142E;
import static business.blap.NWCL0140.constant.NWCL0140Constant.ORD_CATG_INCL_FLG;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NWCL0140.NWCL0140CMsg;
import business.blap.NWCL0140.NWCL0140Query;
import business.blap.NWCL0140.NWCL0140SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * NWCL0140 CFS Lease Package Maintenance Screen
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/30/2016   CITS            K.Ogino         Create          N/A
 * 07/11/2017   CITS            S.Endo          Update          QC#19860

 *</pre>
 */
public class NWCL0140CommonLogic {

    /**
     * findCfsLeaseEligAcct
     * @param glblCmpyCd String
     * @param cMsg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     */
    public static void findCfsLeaseEligAcct(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length() + 1);
        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd)) {
            ssmParam.put(DB_PARAM_BILL_TO_CUST_ACCT_CD, cMsg.billToCustAcctCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.billToCustCd)) {
            ssmParam.put(DB_PARAM_BILL_TO_CUST_CD, cMsg.billToCustCd.getValue());
        }
        S21SsmEZDResult result = NWCL0140Query.getInstance().findMultipleRecord("findCfsLeaseEligAcct", ssmParam);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                if (!EVENT_NM_NWCL0140_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                    cMsg.setMessageInfo(NWCM0135I, new String[] {String.valueOf(sMsg.A.length()) });
                }
                queryResCnt = sMsg.A.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();
            for (int i = 0; i < queryResCnt; i++) {
                Map<String, Object> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).cfsLeaseEligAcctPk_EL, (BigDecimal) map.get(CFS_LEASE_ELIG_ACCT_PK));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).billToCustAcctCd_EL, (String) map.get(BILL_TO_CUST_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).billToCustCd_EL, (String) map.get(BILL_TO_CUST_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsAcctNm_EL, (String) map.get(DS_ACCT_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTime_EL, (String) map.get(EZUPTIME));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).ezUpTimeZone_EL, (String) map.get(EZUPTIMEZONE));
            }
            sMsg.A.setValidCount(queryResCnt);

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowFromNum_EL.setValue(1);
            cMsg.xxPageShowToNum_EL.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_EL.setValue(queryResCnt);

        } else {
            if (!EVENT_NM_NWCL0140_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                cMsg.setMessageInfo(NWCM0003I);
            }
            cMsg.xxPageShowFromNum_EL.clear();
            cMsg.xxPageShowToNum_EL.clear();
            cMsg.xxPageShowOfNum_EL.clear();
        }
    }

    /**
     * findCfsLeaseAttrb
     * @param glblCmpyCd String
     * @param cMsg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     */
    public static void findCfsLeaseAttrb(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ATTRB_KEY_NM, INV_THRHD);

        S21SsmEZDResult result = NWCL0140Query.getInstance().findSingleRecord("findCfsLeaseAttrb", ssmParam);

        if (result.isCodeNormal()) {
            Map<String, Object> map = (Map) result.getResultObject();
            ZYPEZDItemValueSetter.setValue(cMsg.attrbValNum, (BigDecimal) map.get(ATTRB_VAL_NUM));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime_IT, (String) map.get(EZUPTIME));
            ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone_IT, (String) map.get(EZUPTIMEZONE));
        } else {
            if (!EVENT_NM_NWCL0140_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                cMsg.setMessageInfo(NWCM0142E, new String[] {CFS_LEASE_ATTRB, INV_THRHD });
            }
        }
    }

    /**
     * findCfsLeaseEligAOrdCatg
     * @param glblCmpyCd String
     * @param cMsg NWCL0140CMsg
     * @param sMsg NWCL0140SMsg
     */
    public static void findCfsLeaseEligAOrdCatg(String glblCmpyCd, NWCL0140CMsg cMsg, NWCL0140SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.B.length() + 1);
        if (ZYPCommonFunc.hasValue(cMsg.dsOrdCatgDescTxt)) {
            ssmParam.put(DB_PARAM_DS_ORD_CATG_DESC_TXT, cMsg.dsOrdCatgDescTxt);
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsOrdTpDescTxt)) {
            ssmParam.put(DB_PARAM_DS_ORD_TP_DESC_TXT, cMsg.dsOrdTpDescTxt);
        }

        S21SsmEZDResult result = NWCL0140Query.getInstance().findMultipleRecord("findCfsLeaseEligAOrdCatg", ssmParam);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                if (!EVENT_NM_NWCL0140_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                    cMsg.setMessageInfo(NWCM0135I, new String[] {String.valueOf(sMsg.B.length()) });
                }
                queryResCnt = sMsg.B.length();
            }

            List<Map<String, Object>> list = (List) result.getResultObject();
            for (int i = 0; i < queryResCnt; i++) {
                Map<String, Object> map = list.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).cfsLeaseEligOrdCatgPk_EX, (BigDecimal) map.get(CFS_LEASE_ELIG_ORD_CATG_PK));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).dsOrdCatgDescTxt_EX, (String) map.get(DS_ORD_CATG_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).dsOrdTpDescTxt_EX, (String) map.get(DS_ORD_TP_DESC_TXT));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).dsOrdTpCd_EX, (String) map.get(DS_ORD_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ordCatgInclFlg_EX, (String) map.get(ORD_CATG_INCL_FLG));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ezUpTime_EX, (String) map.get(EZUPTIME));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).ezUpTimeZone_EX, (String) map.get(EZUPTIMEZONE));
            }
            sMsg.B.setValidCount(queryResCnt);

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowFromNum_EX.setValue(1);
            cMsg.xxPageShowToNum_EX.setValue(cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_EX.setValue(queryResCnt);

        } else {
            if (!EVENT_NM_NWCL0140_CMN_SUBMIT.equals(cMsg.xxScrEventNm.getValue())) {
                cMsg.setMessageInfo(NWCM0003I);
            }
            cMsg.xxPageShowFromNum_EX.clear();
            cMsg.xxPageShowToNum_EX.clear();
            cMsg.xxPageShowOfNum_EX.clear();
        }
    }

    /**
     * Check Exist DS Order Category
     * @param bizMsg NWCL0140CMsg
     * @param glblCmpyCd String
     * @param dsOrdCatgDescTxt String
     * @param csItem EZDCStringItem
     * @return true: exist
     */
    public static boolean findDsOrdCatg(NWCL0140CMsg bizMsg, String glblCmpyCd, String dsOrdCatgDescTxt, EZDCStringItem csItem) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        params.put(DB_PARAM_DS_ORD_CATG_DESC_TXT, dsOrdCatgDescTxt);

        S21SsmEZDResult result = NWCL0140Query.getInstance().findSingleRecord("findDsOrdCatg", params);

        if (result.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(csItem, (String) result.getResultObject());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update the global Message.
     * @param bizMsg NWCL0140CMsg
     * @param glblMsg NWCL0140SMsg
     */
    public static void updateGlblMsgEL(NWCL0140CMsg bizMsg, NWCL0140SMsg glblMsg) {
        int ixG = bizMsg.xxPageShowFromNum_EL.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * Update the global Message.
     * @param bizMsg NWCL0140CMsg
     * @param glblMsg NWCL0140SMsg
     */
    public static void updateGlblMsgEX(NWCL0140CMsg bizMsg, NWCL0140SMsg glblMsg) {
        int ixG = bizMsg.xxPageShowFromNum_EX.getValueInt() - 1;
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.B.no(i), null, glblMsg.B.no(ixG + i), null);
        }
    }

    /**
     * loadOnePageToCMsg
     * @param bizMsg NWCL0140CMsg
     * @param cMsgArray EZDCMsgArray
     * @param glblMsg NWCL0140SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsgEL(NWCL0140CMsg bizMsg, EZDCMsgArray cMsgArray, NWCL0140SMsg glblMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum_EL.clear();
        bizMsg.xxPageShowOfNum_EL.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum_EL.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
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
        bizMsg.xxPageShowFromNum_EL.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum_EL.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum_EL.setValue(sMsgArray.getValidCount());
    }

    /**
     * loadOnePageToCMsg
     * @param bizMsg NWCL0140CMsg
     * @param cMsgArray EZDCMsgArray
     * @param glblMsg NWCL0140SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsgEX(NWCL0140CMsg bizMsg, EZDCMsgArray cMsgArray, NWCL0140SMsg glblMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum_EX.clear();
        bizMsg.xxPageShowOfNum_EX.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum_EX.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
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
        bizMsg.xxPageShowFromNum_EX.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum_EX.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum_EX.setValue(sMsgArray.getValidCount());
    }
}
