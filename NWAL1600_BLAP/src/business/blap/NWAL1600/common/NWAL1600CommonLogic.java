/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1600.common;

import static business.blap.NWAL1600.constant.NWAL1600Constant.COLON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NWAL1600.NWAL1600Query;
import business.blap.NWAL1600.NWAL1600_ACMsg;
import business.blap.NWAL1600.NWAL1600_BCMsg;
import business.db.TOCTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NWAL1600CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 * 2015/11/18   Fujitsu         T.Ishii         update          S21_NA#824
 * 2016/02/19   Fujitsu         T.Ishii         update          S21_NA#4338
 * 2016/05/12   Fujitsu         T.Murai         update          S21_NA#7861
 * 2016/05/27   Fujitsu         T.Murai         Update          S21_NA#4618
 * 2017/01/13   SRAA            K.Aratani       Update          S21_NA#17127
 * 2017/02/01   SRAA            K.Aratani       Update          S21_NA#17127
 * 2017/11/01   Fujitsu         K.Ishizuka      Update          S21_NA#20146
 * 2017/11/10   Fujitsu         T.Aoi           Update          S21_NA#20146
 * 2020/04/02   Fujitsu         C.Hara          Update          QC#56425
 *</pre>
 */
public class NWAL1600CommonLogic {

//    /**
//     * setSlsRep
//     * @param glblCmpyCd String
//     * @param acMsg NWAL1600_ACMsg
//     * @return boolean
//     */
//    public static boolean setSlsRep(String glblCmpyCd, NWAL1600_ACMsg acMsg) {
//        S21SsmEZDResult ssmResult = NWAL1600Query.getInstance().getSlsRepDetail(glblCmpyCd, acMsg.slsRepTocCd_A.getValue());
//
//        int totalResult = ssmResult.getQueryResultCount();
//        // has more than one results
//        if (ssmResult.isCodeNormal()) {
//            List<Map<String, Object>> resultRsnCdList = (List<Map<String, Object>>) ssmResult.getResultObject();
//            for (int i = 0; i < totalResult; i++) {
//                Map<String, Object> map = (Map<String, Object>) resultRsnCdList.get(i);
//                if (map != null) {
//                    // S21_NA#824 modify start
//                    // acMsg.tocNm_A.setValue((String)
//                    // map.get("TOC_NM"));
//                    // acMsg.coaExtnNm_A.setValue((String)
//                    // map.get("COA_EXTN_NM"));
//                    // acMsg.coaBrNm_A.setValue((String)
//                    // map.get("COA_BR_NM"));
//                    // acMsg.coaCcNm_A.setValue((String)
//                    // map.get("COA_CC_NM"));
//                    ZYPEZDItemValueSetter.setValue(acMsg.tocNm_A, (String) map.get("TOC_NM"));
//                    ZYPEZDItemValueSetter.setValue(acMsg.coaExtnNm_A, (String) map.get("COA_EXTN_NM"));
//                    ZYPEZDItemValueSetter.setValue(acMsg.coaBrNm_A, (String) map.get("COA_BR_NM"));
//                    ZYPEZDItemValueSetter.setValue(acMsg.coaCcNm_A, (String) map.get("COA_CC_NM"));
//                    // S21_NA#824 modify end
//                }
//            }
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * setNonQuoteSlsRep
//     * @param glblCmpyCd String
//     * @param bcMsg NWAL1600_BCMsg
//     * @return boolean
//     */
//    public static boolean setNonQuoteSlsRep(String glblCmpyCd, NWAL1600_BCMsg bcMsg) {
//        S21SsmEZDResult ssmResult = NWAL1600Query.getInstance().getSlsRepDetail(glblCmpyCd, bcMsg.slsRepTocCd_B.getValue());
//
//        int totalResult = ssmResult.getQueryResultCount();
//        // has more than one results
//        if (ssmResult.isCodeNormal()) {
//            List<Map<String, Object>> resultRsnCdList = (List<Map<String, Object>>) ssmResult.getResultObject();
//            for (int i = 0; i < totalResult; i++) {
//                Map<String, Object> map = (Map<String, Object>) resultRsnCdList.get(i);
//                if (map != null) {
//                    ZYPEZDItemValueSetter.setValue(bcMsg.tocNm_B, (String) map.get("TOC_NM"));
//                    ZYPEZDItemValueSetter.setValue(bcMsg.coaExtnNm_B, (String) map.get("COA_EXTN_NM"));
//                    ZYPEZDItemValueSetter.setValue(bcMsg.coaBrNm_B, (String) map.get("COA_BR_NM"));
//                    ZYPEZDItemValueSetter.setValue(bcMsg.coaCcNm_B, (String) map.get("COA_CC_NM"));
//                }
//            }
//            return true;
//        }
//        return false;
//    }

    /**
     * checkSlsrepCd
     * @param glblCmpyCd String
     * @param slsrepCd String
     * @return boolean
     */
    public static boolean checkSlsrepCd(String glblCmpyCd, String slsrepCd) {
        // Master Check: Salesrep(TOC) Code
        TOCTMsg tocTMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tocTMsg.tocCd, slsrepCd);
        ZYPEZDItemValueSetter.setValue(tocTMsg.glblCmpyCd, glblCmpyCd);

        TOCTMsg tMsgResult = (TOCTMsg) S21CacheTBLAccessor.findByKey(tocTMsg);
        // has no result
        if (tMsgResult == null) {
            return false;
        }

        return true;
    }

    /**
     * isSalesSlsrep
     * @param glblCmpyCd String
     * @param slsrepCd String
     * @return boolean
     */
    public static boolean isSalesSlsrep(String glblCmpyCd, String slsrepCd) {
        S21SsmEZDResult ssmResult = NWAL1600Query.getInstance().getCntSalesSlsrep(glblCmpyCd, slsrepCd);

        Integer cnt = (Integer) ssmResult.getResultObject();
        if (cnt > 0) {
            return true;
        }
        return false;
    }

    /**
     * isModifiedSlsCrdt
     * @param acMsg NWAL1600_ACMsg
     * @return boolean
     */
    public static boolean isModifiedSlsCrdt(NWAL1600_ACMsg acMsg) {
        if (!acMsg.lineBizRoleTpCd_A1.getValue().equals(acMsg.lineBizRoleTpCd_A.getValue())) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(acMsg.slsRepCrPct_A1)) {
            if (acMsg.slsRepCrPct_A1.getValue().compareTo(acMsg.slsRepCrPct_A.getValue()) != 0) {
                return true;
            }
        } else if (ZYPCommonFunc.hasValue(acMsg.slsRepCrPct_A)) {
            return true;
        }

        if (ZYPCommonFunc.hasValue(acMsg.slsRepTocCd_A1)) {
            if (!acMsg.slsRepTocCd_A1.getValue().equals(acMsg.slsRepTocCd_A.getValue())) {
                return true;
            }
        } else if (ZYPCommonFunc.hasValue(acMsg.slsRepTocCd_A)) {
            return true;
        }
        return false;
    }

    /**
     * isModifiedNonQuote
     * @param bcMsg NWAL1600_BCMsg
     * @return boolean
     */
    public static boolean isModifiedNonQuote(NWAL1600_BCMsg bcMsg) {
        if (!bcMsg.lineBizRoleTpCd_B1.getValue().equals(bcMsg.lineBizRoleTpCd_B.getValue())) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(bcMsg.slsRepTocCd_B1)) {
            if (!bcMsg.slsRepTocCd_B1.getValue().equals(bcMsg.slsRepTocCd_B.getValue())) {
                return true;
            }
        } else if (ZYPCommonFunc.hasValue(bcMsg.slsRepTocCd_B)) {
            return true;
        }
        return false;
    }

    /**
     * getSalesRepList
     * @param glblCmpyCd String
     * @param psnNum String
     * @param tocNm String
     * @param slsRepTocCd String
     * @return SalesRepList
     */
    public static List<Map<String, Object>> getSalesRepList(String glblCmpyCd, String psnNum, String tocNm, String slsRepTocCd) {

        if (!ZYPCommonFunc.hasValue(slsRepTocCd)) {
            return null;
        }
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnNum", psnNum); // 2016/05/12 S21_NA#7861 Mod psnCd -> psnNum
        ssmParam.put("tocNm", tocNm);
        ssmParam.put("slsRepTocCd", slsRepTocCd);
        ssmParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        S21SsmEZDResult ssmResult = NWAL1600Query.getInstance().getSalesRepList(ssmParam);
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    // 2017/11/10 S21_NA#20146 Add Start
    /**
     * getSalesRepListForNonQuota
     * @param glblCmpyCd String
     * @param psnNum String
     * @param tocNm String
     * @param slsRepTocCd String
     * @return SalesRepList
     */
    public static List<Map<String, Object>> getSalesRepListForNonQuot(String glblCmpyCd, String psnNum, String tocNm, String slsRepTocCd) {

        if (!ZYPCommonFunc.hasValue(slsRepTocCd)) {
            return null;
        }
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnNum", psnNum);
        ssmParam.put("tocNm", tocNm);
        ssmParam.put("slsRepTocCd", slsRepTocCd);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate()); // 2020/04/02 QC#56425 Add
        S21SsmEZDResult ssmResult = NWAL1600Query.getInstance().getSalesRepListForNonQuot(ssmParam);
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }
    // 2017/11/10 S21_NA#20146 Add End

    //QC#17127
    /**
     * getSalesRepList
     * @param glblCmpyCd String
     * @param psnNum String
     * @param tocNm String
     * @param slsRepTocCd String
     * @return SalesRepList
     */
    public static List<Map<String, Object>> getSalesRepListForOnBlur(String glblCmpyCd, String psnNum, String tocNm, String slsRepTocCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnNum", psnNum); // 2016/05/12 S21_NA#7861 Mod psnCd -> psnNum
        ssmParam.put("tocNm", tocNm);
        ssmParam.put("slsRepTocCd", slsRepTocCd);
        ssmParam.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        S21SsmEZDResult ssmResult = NWAL1600Query.getInstance().getSalesRepList(ssmParam);
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }

    // 2017/11/10 S21_NA#20146 Add Start
    /**
     * getSalesRepListForNonQuota
     * @param glblCmpyCd String
     * @param psnNum String
     * @param tocNm String
     * @param slsRepTocCd String
     * @return SalesRepList
     */
    public static List<Map<String, Object>> getSalesRepListForOnBlurNonQuot(String glblCmpyCd, String psnNum, String tocNm, String slsRepTocCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("psnNum", psnNum);
        ssmParam.put("tocNm", tocNm);
        ssmParam.put("slsRepTocCd", slsRepTocCd);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate()); // 2020/04/02 QC#56425 Add
        S21SsmEZDResult ssmResult = NWAL1600Query.getInstance().getSalesRepListForNonQuot(ssmParam);
        return (List<Map<String, Object>>) ssmResult.getResultObject();
    }
    // 2017/11/10 S21_NA#20146 Add End

    /**
     * setupSalesCreditLine
     * @param line NWAL1600_ACMsg
     * @param slsCr Map<String, Object>
     */
    public static void setupSalesCreditLine(NWAL1600_ACMsg line, Map<String, Object> slsCr) {

        ZYPEZDItemValueSetter.setValue(line.psnNum_A, (String) slsCr.get("PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(line.tocNm_A, (String) slsCr.get("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(line.coaExtnNm_A, (String) slsCr.get("COA_EXTN_NM"));
        ZYPEZDItemValueSetter.setValue(line.coaBrNm_A, (String) slsCr.get("COA_BR_NM"));
        ZYPEZDItemValueSetter.setValue(line.coaCcNm_A, (String) slsCr.get("COA_CC_NM"));
        ZYPEZDItemValueSetter.setValue(line.slsRepTocCd_A, (String) slsCr.get("TOC_CD"));
        // S21_NA ADD START QC#20146
        ZYPEZDItemValueSetter.setValue(line.xxCoaExtnSrchTxt_A, (String) slsCr.get("COA_EXTN_CD") + COLON + (String) slsCr.get("COA_EXTN_NM"));
        ZYPEZDItemValueSetter.setValue(line.xxCoaBrSrchTxt_A, (String) slsCr.get("COA_BR_CD") + COLON +(String) slsCr.get("COA_BR_NM"));
        ZYPEZDItemValueSetter.setValue(line.xxCoaProdSrchTxt_A, (String) slsCr.get("COA_CC_CD") + COLON +(String) slsCr.get("COA_CC_NM"));
        // S21_NA ADD END QC#20146
    }

    /**
     * setupSalesRepLine
     * @param line NWAL1600_BCMsg
     * @param map Map<String, Object>
     */
    public static void setupSalesRepLine(NWAL1600_BCMsg line, Map<String, Object> slsRep) {

        ZYPEZDItemValueSetter.setValue(line.psnNum_B, (String) slsRep.get("PSN_NUM"));
        ZYPEZDItemValueSetter.setValue(line.tocNm_B, (String) slsRep.get("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(line.coaExtnNm_B, (String) slsRep.get("COA_EXTN_NM"));
        ZYPEZDItemValueSetter.setValue(line.coaBrNm_B, (String) slsRep.get("COA_BR_NM"));
        ZYPEZDItemValueSetter.setValue(line.coaCcNm_B, (String) slsRep.get("COA_CC_NM"));
        ZYPEZDItemValueSetter.setValue(line.slsRepTocCd_B, (String) slsRep.get("TOC_CD"));
        // S21_NA ADD START QC#20146
        ZYPEZDItemValueSetter.setValue(line.xxCoaExtnSrchTxt_B, (String) slsRep.get("COA_EXTN_CD") + COLON + (String) slsRep.get("COA_EXTN_NM"));
        ZYPEZDItemValueSetter.setValue(line.xxCoaBrSrchTxt_B, (String) slsRep.get("COA_BR_CD") + COLON +(String) slsRep.get("COA_BR_NM"));
        ZYPEZDItemValueSetter.setValue(line.xxCoaProdSrchTxt_B, (String) slsRep.get("COA_CC_CD") + COLON +(String) slsRep.get("COA_CC_NM"));
        // S21_NA ADD END QC#20146
    }

    // S21_NA#4618 add Start
    /**
     * setupRoleTpPulldown -- Quota
     * @param ssmResultQuot S21SsmEZDResult
     * @param acMsg NWAL1600_BCMsg
     */
    public static void setupQuotRoleTpPulldown(S21SsmEZDResult ssmResultQuot, NWAL1600_ACMsg acMsg) {

        if (ssmResultQuot.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResultQuot.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(acMsg.lineBizRoleTpCd_AC.no(i), resultMap.get("LINE_BIZ_ROLE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(acMsg.lineBizRoleTpDescTxt_AD.no(i), resultMap.get("LINE_BIZ_ROLE_TP_DESC_TXT"));
            }
        }
    }

    /**
     * setupRoleTpPulldown -- Non Quota
     * @param ssmResultNonQuot S21SsmEZDResult
     * @param bcMsg NWAL1600_BCMsg
     */
    public static void setupNonQuotRoleTpPulldown(S21SsmEZDResult ssmResultNonQuot, NWAL1600_BCMsg bcMsg) {

        if (ssmResultNonQuot.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResultNonQuot.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
                ZYPEZDItemValueSetter.setValue(bcMsg.lineBizRoleTpCd_BC.no(i), resultMap.get("LINE_BIZ_ROLE_TP_CD"));
                ZYPEZDItemValueSetter.setValue(bcMsg.lineBizRoleTpDescTxt_BD.no(i), resultMap.get("LINE_BIZ_ROLE_TP_DESC_TXT"));
            }
        }
    }
    //S21_NA#4618 Add End
}
