/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1250.common;

import static business.blap.NSAL1250.constant.NSAL1250Constant.*;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1250.NSAL1250CMsg;
import business.blap.NSAL1250.NSAL1250Query;
import business.blap.NSAL1250.NSAL1250SMsg;
import business.db.S21_PSNTMsg;
import business.db.S21_PSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Preview Billing Workflow Details
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Hitachi         Y.Tsuchimoto    Create          N/A
 *</pre>
 */
public class NSAL1250CommonLogic {

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param page int
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }

    /**
     * Paginate to item(For A Table)
     * @param cMsg NSAL1250CMsg
     * @param sMsg NSAL1250SMsg
     * @param itemIndex int
     */
    public static void pagenationA(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.A.length()) * cMsg.A.length();
        int num = 0;
        ZYPTableUtil.clear(cMsg.A);
        for (int i = startIndex; i < sMsg.A.getValidCount(); i++) {
            if (num >= cMsg.A.length()) {
                break;
            }
            EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(num), null);
            num++;
        }
        cMsg.A.setValidCount(num);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.valueOf(startIndex + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(startIndex + cMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_A, BigDecimal.valueOf(sMsg.A.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_A, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum_A, BigDecimal.valueOf(sMsg.A.getValidCount()).divide(BigDecimal.valueOf(cMsg.A.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * Paginate to item(For B Table)
     * @param cMsg NSAL1250CMsg
     * @param sMsg NSAL1250SMsg
     * @param itemIndex int
     */
    public static void pagenationB(NSAL1250CMsg cMsg, NSAL1250SMsg sMsg, int itemIndex) {

        int startIndex = (itemIndex / cMsg.B.length()) * cMsg.B.length();
        int num = 0;
        ZYPTableUtil.clear(cMsg.B);
        for (int i = startIndex; i < sMsg.B.getValidCount(); i++) {
            if (num >= cMsg.B.length()) {
                break;
            }
            EZDMsg.copy(sMsg.B.get(i), null, cMsg.B.get(num), null);
            num++;
        }
        cMsg.B.setValidCount(num);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, BigDecimal.valueOf(startIndex + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_B, BigDecimal.valueOf(startIndex + cMsg.B.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_B, BigDecimal.valueOf(sMsg.B.getValidCount()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowCurNum_B, BigDecimal.valueOf(startIndex + 1).divide(BigDecimal.valueOf(cMsg.B.length()), 0, BigDecimal.ROUND_UP));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowTotNum_B, BigDecimal.valueOf(sMsg.B.getValidCount()).divide(BigDecimal.valueOf(cMsg.B.length()), 0, BigDecimal.ROUND_UP));
    }

    /**
     * getPsnNm
     * @param glblCmpyCd String
     * @param psnCd String
     * @return Person name
     */
    public static String getPsnNm(String glblCmpyCd, String psnCd) {

        S21_PSNTMsg inMsg = new S21_PSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("psnCd01", psnCd);
        S21_PSNTMsgArray list = (S21_PSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list.getValidCount() > 0) {
            S21_PSNTMsg tMsg = (S21_PSNTMsg) list.get(0);
            if (tMsg != null) {
                return tMsg.psnFirstNm.getValue() + ", " + tMsg.psnLastNm.getValue();
            }
        }
        return null;
    }

    /**
     * getDocumentId
     * @param svcContrBllgPk BigDecimal
     * @return Document Id
     */
    public static String getDocumentId(BigDecimal svcContrBllgPk) {
        S21SsmEZDResult ssmResult = NSAL1250Query.getInstance().getTransactionInfo(svcContrBllgPk);
        Map<String, Object> map = (Map<String, Object>) ssmResult.getResultObject();

        String dsContrCatgCd = (String) map.get("DS_CONTR_CATG_CD");
        if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
            return DOCUMENT_ID_NON_FLEET + ((BigDecimal) map.get("SVC_MACH_MSTR_PK")).toString() + (String) map.get("BILL_TO_CUST_CD") + (String) map.get("SVC_CONTR_BLLG_THRU_DT");
        // mod start 2016/10/05 CSA Defect#10729
        } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return DOCUMENT_ID_FLEET + ((BigDecimal) map.get("DS_CONTR_PK")).toString() + (String) map.get("BILL_TO_CUST_CD") + (String) map.get("SVC_CONTR_BLLG_THRU_DT");
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            return DOCUMENT_ID_AGG + ((BigDecimal) map.get("SVC_MACH_MSTR_PK")).toString() + (String) map.get("BILL_TO_CUST_CD") + (String) map.get("SVC_CONTR_BLLG_THRU_DT");
        // mod end 2016/10/05 CSA Defect#10729
        } else {
            return null;
        }
    }
}
