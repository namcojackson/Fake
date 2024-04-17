/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1160.common;

import static business.blap.NSAL1160.constant.NSAL1160Constant.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NSAL1160.NSAL1160CMsg;
import business.blap.NSAL1160.NSAL1160Query;
import business.blap.NSAL1160.NSAL1160SMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * Service Task Summary
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/26/2015   Hitachi         J.Kim           Create          N/A
 * 02/09/2015   Hitachi         J.Kim           Update          QC#3880
 * 2017/02/08   Hitachi         K.Kojima        Update          QC#17511
 * 2017/07/12   Hitachi         U.Kim           Update          QC#18313
 *</pre>
 */
public class NSAL1160CommonLogic {

    /**
     * Check Mandatory
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     * @return boolean
     */
    public static boolean checkMandatory(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.dsContrPk_P)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"DS_CONTR_PK" });
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.shipToCustAcctCd_P)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"SHIP_TO_CUST_ACCT_CD" });
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.svcPgmMdseCd_P)) {
            cMsg.setMessageInfo(ZZM9000E, new String[] {"SVC_PGM_MDSE_CD" });
            return false;
        }
        return true;
    }

    /**
     * searchInit
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     */
    public static void searchInit(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        String salesDate = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        if (!salesDate.isEmpty()) {
            String sdFrom = getStartSalesDate(salesDate);
            String sdTo = getEndSalesDate(salesDate);
            ZYPEZDItemValueSetter.setValue(cMsg.procDt_AF, sdFrom);
            ZYPEZDItemValueSetter.setValue(cMsg.procDt_AT, sdTo);
            ZYPEZDItemValueSetter.setValue(cMsg.procDt_BF, sdFrom);
            ZYPEZDItemValueSetter.setValue(cMsg.procDt_BT, sdTo);
            ZYPEZDItemValueSetter.setValue(cMsg.cratDt_CF, sdFrom);
            ZYPEZDItemValueSetter.setValue(cMsg.cratDt_CT, sdTo);
        }

        searchHeaderInfo(cMsg, sMsg);
        searchNotesInfo(cMsg, sMsg);
        searchWorkflowActionsInfo(cMsg, sMsg);
        searchEnforcementActions(cMsg, sMsg);
    }

    /**
     * Search Header Info
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     */
    public static void searchHeaderInfo(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        Map<String, Object> searchHeaderInfo = geSearchHeaderQuery(cMsg);
        if (searchHeaderInfo != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum, (String) searchHeaderInfo.get("DS_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustAcctCd, (String) searchHeaderInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, (String) searchHeaderInfo.get("DS_ACCT_NM"));
        }
    }

    /**
     * Search Notes
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchNotesInfo(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        Map<String, Object> params = setSearchNotesParam(cMsg, SEARCH_LIMIT_COUNT + 1);
        S21SsmEZDResult ssmResult = NSAL1160Query.getInstance().getSearchNotesInfo(sMsg, params);
        return ssmResult;
    }

    /**
     * Search Workflow Actions
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchWorkflowActionsInfo(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        Map<String, Object> params = setSearchWorkflowActionsParam(cMsg, SEARCH_LIMIT_COUNT + 1);
        S21SsmEZDResult ssmResult = NSAL1160Query.getInstance().getSearchWorkflowActionsInfo(sMsg, params);
        return ssmResult;
    }

    /**
     * Search Enforcement Actions
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchEnforcementActions(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg) {

        Map<String, Object> params = setSearchEnforcementActions(cMsg, SEARCH_LIMIT_COUNT + 1);
        S21SsmEZDResult ssmResult = NSAL1160Query.getInstance().getSearchEnforcementActions(sMsg, params);
        return ssmResult;
    }

    /**
     * getStartSalesDate
     * @param salesDate String
     * @return String
     */
    public static String getStartSalesDate(String salesDate) {
        StringBuilder sbFrom = new StringBuilder();
        sbFrom.append(salesDate.substring(0, SUBSTRING_LEN6));
        sbFrom.append("01");
        return sbFrom.toString();
    }

    /**
     * @param salesDate String
     * @return String
     */
    public static String getEndSalesDate(String salesDate) {
        StringBuilder sbTo = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(salesDate.substring(0, SUBSTRING_LEN4)));
        // START 2017/02/08 K.Kojima [QC#16600,MOD]
        // cal.set(Calendar.MONTH, Integer.parseInt(salesDate.substring(SUBSTRING_LEN4, SUBSTRING_LEN6)));
        // int lasDay = cal.getMaximum(Calendar.DATE);
        cal.set(Calendar.MONTH, Integer.parseInt(salesDate.substring(SUBSTRING_LEN4, SUBSTRING_LEN6)) - 1);
        int lasDay = cal.getActualMaximum(Calendar.DATE);
        // END 2017/02/08 K.Kojima [QC#16600,MOD]
        sbTo.append(salesDate.substring(0, SUBSTRING_LEN6));
        sbTo.append(lasDay);
        return sbTo.toString();
    }

    /**
     * clearPageNum(TalbeA)
     * @param cMsg NSAL1160CMsg
     */
    public static void clearPageNum_A(NSAL1160CMsg cMsg) {
        cMsg.xxPageShowFromNum_A.clear();
        cMsg.xxPageShowToNum_A.clear();
        cMsg.xxPageShowOfNum_A.clear();
    }

    /**
     * clearPageNum(TalbeB)
     * @param cMsg NSAL1160CMsg
     */
    public static void clearPageNum_B(NSAL1160CMsg cMsg) {
        cMsg.xxPageShowFromNum_B.clear();
        cMsg.xxPageShowToNum_B.clear();
        cMsg.xxPageShowOfNum_B.clear();
    }

    /**
     * clearPageNum(TalbeC)
     * @param cMsg NSAL1160CMsg
     */
    public static void clearPageNum_C(NSAL1160CMsg cMsg) {
        cMsg.xxPageShowFromNum_C.clear();
        cMsg.xxPageShowToNum_C.clear();
        cMsg.xxPageShowOfNum_C.clear();
    }

    /**
     * Pagenation(TableA)
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     * @param pageFrom int
     */
    public static void pagenation_A(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg, int pageFrom) {

        EZDCMsgArray cAry = null;
        EZDSMsgArray sAry = null;

        cAry = cMsg.A;
        sAry = sMsg.A;

        int i = pageFrom;
        int j = 0;
        for (; i < sAry.getValidCount() && j < cAry.length(); i++, j++) {
            EZDMsg.copy(sAry.get(i), null, cAry.get(j), null);
        }
        cAry.setValidCount(j);

        cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cAry.getValidCount());
        cMsg.xxPageShowOfNum_A.setValue(sAry.getValidCount());
    }

    /**
     * Pagenation(TableB)
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     * @param pageFrom int
     */
    public static void pagenation_B(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg, int pageFrom) {

        EZDCMsgArray cAry = null;
        EZDSMsgArray sAry = null;

        cAry = cMsg.B;
        sAry = sMsg.B;

        int i = pageFrom;
        int j = 0;
        for (; i < sAry.getValidCount() && j < cAry.length(); i++, j++) {
            EZDMsg.copy(sAry.get(i), null, cAry.get(j), null);
        }
        cAry.setValidCount(j);

        cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_B.setValue(pageFrom + cAry.getValidCount());
        cMsg.xxPageShowOfNum_B.setValue(sAry.getValidCount());
    }

    /**
     * Pagenation(TableC)
     * @param cMsg NSAL1160CMsg
     * @param sMsg NSAL1160SMsg
     * @param pageFrom int
     */
    public static void pagenation_C(NSAL1160CMsg cMsg, NSAL1160SMsg sMsg, int pageFrom) {

        EZDCMsgArray cAry = null;
        EZDSMsgArray sAry = null;

        cAry = cMsg.C;
        sAry = sMsg.C;

        int i = pageFrom;
        int j = 0;
        for (; i < sAry.getValidCount() && j < cAry.length(); i++, j++) {
            EZDMsg.copy(sAry.get(i), null, cAry.get(j), null);
        }
        cAry.setValidCount(j);

        cMsg.xxPageShowFromNum_C.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_C.setValue(pageFrom + cAry.getValidCount());
        cMsg.xxPageShowOfNum_C.setValue(sAry.getValidCount());
    }

    private static Map<String, Object> setSearchNotesParam(NSAL1160CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        params.put("shipToCustAcctCd", cMsg.shipToCustAcctCd_P.getValue());
        params.put("svcPgmMdseCd", cMsg.svcPgmMdseCd_P.getValue());
        params.put("procDtAf", cMsg.procDt_AF.getValue());
        params.put("procDtAt", cMsg.procDt_AT.getValue());
        params.put("limitCount", limitCount);
        return params;
    }

    private static Map<String, Object> setSearchWorkflowActionsParam(NSAL1160CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        // START 2017/07/12 U.Kim [QC#18313,MOD]
        //params.put("svcMemoTrxNm", "DS_ACCT_NUM");
        params.put("svcMemoTrxNm", "DS_ACCT_CUST_PK");
        // END 2017/07/12 U.Kim [QC#18313,MOD]
        params.put("lastUpdTsBf", cMsg.procDt_BF.getValue());
        params.put("lastUpdTsBt", cMsg.procDt_BT.getValue());
        params.put("shipToCustAcctCd", cMsg.shipToCustAcctCd_P.getValue());
        params.put("limitCount", limitCount);
        return params;
    }

    private static Map<String, Object> setSearchEnforcementActions(NSAL1160CMsg cMsg, int limitCount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", cMsg.dsContrPk_P.getValue());
        params.put("shipToCustAcctCd", cMsg.shipToCustAcctCd_P.getValue());
        params.put("svcPgmMdseCd", cMsg.svcPgmMdseCd_P.getValue());
        params.put("cratDtCf", cMsg.cratDt_CF.getValue());
        params.put("cratDtCt", cMsg.cratDt_CT.getValue());
        params.put("limitCount", limitCount);
        return params;
    }

    // del start 2016/02/09 CSA Defect#3880
    // @SuppressWarnings("unchecked")
    // del end 2016/02/09 CSA Defect#3880
    private static Map<String, Object> geSearchHeaderQuery(NSAL1160CMsg cMsg) {
        Map<String, Object> searchHeaderInfo = (Map<String, Object>) NSAL1160Query.getInstance().searchHeaderInfo(cMsg).getResultObject();
        return searchHeaderInfo;
    }
}
