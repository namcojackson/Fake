/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2400.common;

import static business.blap.NWAL2400.constant.NWAL2400Constant.BIZ_APP_ID;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_COLUMN_RTL_DIV_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_BILL_TO_CUST_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_BIZ_APP_ID;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_COA_BR_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_DS_ORD_CATG_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_DS_ORD_CATG_DESC_TXT;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_DS_ORD_TP_DESC_TXT;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_DS_RTL_DLR_INFO_PK;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_EFF_FROM_DT;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_EFF_THRU_DT;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_RTL_DIV_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_RTL_DLR_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_RTL_WH_NM;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_SALES_DATE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NWAL2400.NWAL2400CMsg;
import business.blap.NWAL2400.NWAL2400Query;
import business.blap.NWAL2400.NWAL2400SMsg;
import business.blap.NWAL2400.NWAL2400_ASMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 * 2016/08/01   CITS            S.Tanikawa      Update          QC#11099
 * 2016/08/02   CITS            S.Tanikawa      Update          QC#10537
 *</pre>
 */
public class NWAL2400CommonLogic {

    /**
     * Create Pulldown Retail Division
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRetailDivision(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg, String glblCmpyCd) {

        // Clear Pulldown Data
        cMsg.rtlDivCd_PC.clear();
        cMsg.rtlDivCd_PD.clear();

        // Set Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);

        // Execute
        S21SsmEZDResult result = NWAL2400Query.getInstance().getRetailDivisionPulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.rtlDivCd_PC.no(i), (String) recode.get(DB_COLUMN_RTL_DIV_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.rtlDivCd_PD.no(i), (String) recode.get(DB_COLUMN_RTL_DIV_CD));

                if (i >= cMsg.rtlDivCd_PC.length() - 1) {
                    break;
                }
            }
        }
    }

    /**
     * <pre>
     * pagination for Add New Line,AddConfig
     * </pre>
     * @param sMsg NWAL2400SMsg
     * @param cMsg NWAL2400CMsg
     */
    public static void paginationForInsertOrDeleteRow(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        if (pagenationFrom < 0) {
            pagenationFrom = 0;
        }
        int pagenationTo = cMsg.xxPageShowToNum.getValueInt();
        int row = pagenationTo - pagenationFrom;

        int i = pagenationFrom;

        if (row + 1 > cMsg.A.length()) {
            i = pagenationTo;
            pagenationFrom = pagenationTo;
        }

        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Update the global Message.
     * @param bizMsg NWAL2400CMsg
     * @param glblMsg NWAL2400SMsg
     */
    public static void updateGlblMsg(NWAL2400CMsg bizMsg, NWAL2400SMsg glblMsg) {

        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * loadOnePageToCMsg
     * @param bizMsg NWAL2400CMsg
     * @param cMsgArray EZDCMsgArray
     * @param glblMsg NWAL2400SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NWAL2400CMsg bizMsg, EZDCMsgArray cMsgArray, NWAL2400SMsg glblMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

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
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * Get dsOrdCatgCd
     * @param glblCmpyCd String
     * @param dsOrdCatgDescTxt String
     * @return dsOrdCatgCd
     */
    public static String getDsOrdCatgCd(String glblCmpyCd, String dsOrdCatgDescTxt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_BIZ_APP_ID, BIZ_APP_ID);
        ssmParam.put(DB_PARAM_DS_ORD_CATG_DESC_TXT, dsOrdCatgDescTxt);

        S21SsmEZDResult result = NWAL2400Query.getInstance().getDsOrdCatgCd(ssmParam);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }

    /**
     * Check coaBrCd
     * @param glblCmpyCd String
     * @param coaBrCd String
     * @return count of coaBrCd
     */
    public static int chkCoaBrCd(String glblCmpyCd, String coaBrCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_COA_BR_CD, coaBrCd);

        S21SsmEZDResult result = NWAL2400Query.getInstance().chkCoaBrCd(ssmParam);

        if (result.isCodeNormal()) {
            return (Integer) result.getResultObject();
        } else {
            return 0;
        }
    }

    /**
     * Get dsOrdCatgCd
     * @param glblCmpyCd String
     * @param dsOrdCatgCd String
     * @param dsOrdTpDescTxt String
     *  @return dsOrdTpCd
     */
    public static String getDsOrdTpCd(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpDescTxt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DS_ORD_CATG_CD, dsOrdCatgCd);
        ssmParam.put(DB_PARAM_DS_ORD_TP_DESC_TXT, dsOrdTpDescTxt);

        S21SsmEZDResult result = NWAL2400Query.getInstance().getDsOrdTpCd(ssmParam);

        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return null;
        }
    }

    // UPDATE START QC#10457
    /**
     * Get Retail Warehouse
     * @param glblCmpyCd String
     * @param rtlWhNm String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getRtlWh(String glblCmpyCd, String rtlWhNm, String salesDate) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_NM, rtlWhNm);
        ssmParam.put(DB_PARAM_SALES_DATE, salesDate);

        return NWAL2400Query.getInstance().getRtlWh(ssmParam);
    }
    // UPDATE END QC#10457

    /**
     * Check Bill To Cust Code
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return count of result
     */
    public static int chkBillToCustCd(String glblCmpyCd, String billToCustCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_BILL_TO_CUST_CD, billToCustCd);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult result = NWAL2400Query.getInstance().chkBillToCustCd(ssmParam);

        if (result.isCodeNormal()) {
            return (Integer) result.getResultObject();
        } else {
            return 0;
        }
    }

    /**
     * Check D Retail Dealer Info Duplicate
     * @param glblCmpyCd String
     * @param sMsgA NWAL2400_ASMsg
     * @return count of result
     */
    public static int chkDsRtlDlrInfo(String glblCmpyCd, NWAL2400_ASMsg sMsgA) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_COA_BR_CD, sMsgA.coaBrCd_A.getValue());
        ssmParam.put(DB_PARAM_RTL_DLR_CD, sMsgA.rtlDlrCd_A.getValue());
        ssmParam.put(DB_PARAM_RTL_DIV_CD, sMsgA.rtlDivCd_A.getValue());
        ssmParam.put(DB_PARAM_DS_RTL_DLR_INFO_PK, sMsgA.dsRtlDlrInfoPk_A.getValue());
        ssmParam.put(DB_PARAM_EFF_FROM_DT, sMsgA.effFromDt_A.getValue());
        ssmParam.put(DB_PARAM_EFF_THRU_DT, sMsgA.effThruDt_A.getValue());

        S21SsmEZDResult result = NWAL2400Query.getInstance().chkDsRtlDlrInfo(ssmParam);

        if (result.isCodeNormal()) {
            return (Integer) result.getResultObject();
        } else {
            return 0;
        }
    }

    /**
     * Get DS_RTL_DLR_INFO_PK
     * @param glblCmpyCd String
     * @param rtlDlrCd String
     * @param rtlDivCd String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getRtlDlrInfoPk(String glblCmpyCd, String rtlDlrCd, String rtlDivCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_DLR_CD, rtlDlrCd);
        ssmParam.put(DB_PARAM_RTL_DIV_CD, rtlDivCd);

        return NWAL2400Query.getInstance().getRtlDlrInfoPk(ssmParam);
    }

    /**
     * Get COA_BR_DESC_TXT
     * @param glblCmpyCd String
     * @param coaBrCd String
     * @return String
     */
    public static String getCoaBr(String glblCmpyCd, String coaBrCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_COA_BR_CD, coaBrCd);

        S21SsmEZDResult result = NWAL2400Query.getInstance().getCoaBr(ssmParam);
        if (result.isCodeNormal()) {
            return (String) result.getResultObject();
        } else {
            return "";
        }
    }
}
