/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0630.common;

import static business.blap.NLCL0630.constant.NLCL0630Constant.API_PARAM_TIMESTAMP_FORMAT;
import static business.blap.NLCL0630.constant.NLCL0630Constant.BIZ_APP_ID;
import static business.blap.NLCL0630.constant.NLCL0630Constant.CANCEL_MODE;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_COLUMN_TECH_NM;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_COLUMN_PHYS_INVTY_STS_DESC_TXT;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_COLUMN_PHYS_INVTY_CNT_STS_CD;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_COLUMN_PHYS_INVTY_CTRL_PK;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_CMSG;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_COA_BR_CD;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_MAX_ROWNUM;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_TECH_TOC_CD;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_SALES_DATE;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_USER_ID;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_PHYS_INVTY_STS_CD;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_PHYS_INVTY_NUM;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_ROWNUM;
import static business.blap.NLCL0630.constant.NLCL0630Constant.DB_PARAM_VALUE_ROWNUM_1;
import static business.blap.NLCL0630.constant.NLCL0630Constant.FUNC_ID_ALL_USERS;
import static business.blap.NLCL0630.constant.NLCL0630Constant.NLZM2278E;
import static business.blap.NLCL0630.constant.NLCL0630Constant.NMAM0038I;
import static business.blap.NLCL0630.constant.NLCL0630Constant.NMAM8181W;
import static business.blap.NLCL0630.constant.NLCL0630Constant.NMAM0177E;
import static business.blap.NLCL0630.constant.NLCL0630Constant.NLCM0231E;
import static business.blap.NLCL0630.constant.NLCL0630Constant.MSG_VALUE_PHYS_INVTY_CTRL;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0630.NLCL0630CMsg;
import business.blap.NLCL0630.NLCL0630Query;
import business.blap.NLCL0630.NLCL0630SMsg;
import business.db.COA_BRTMsg;
import business.db.PHYS_INVTY_CTRLTMsg;
import business.parts.NLZC061001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC061001.NLZC061001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/26/2016   CITS         Makoto Okigami     Create          N/A
 * 08/07/2018   CITS            Y.Iwasaki       Update          QC#27010
 * 09/12/2018   CITS            M.Naito         Update          QC#28190
 * 12/03/2018   Fujitsu         T.Ogura         Update          QC#27978
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 * 08/15/2023   Hitachi         G.Quan          Update          QC#61208
 *</pre>
 */
public class NLCL0630CommonLogic {

    /**
     * Search
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     * @param glblCmpyCd String
     */
    public static void search(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg, String glblCmpyCd) {

        boolean ret = true;
        cMsg.techNm.clear();
        cMsg.coaBrNm.clear();
        // START 2018/12/11 T.Ogura [QC#28755,ADD]
        cMsg.rtlWhNm.clear();
        // END   2018/12/11 T.Ogura [QC#28755,ADD]
        if (ZYPCommonFunc.hasValue(cMsg.techTocCd)) {
            ret &= searchTechnician(cMsg, sMsg, glblCmpyCd);
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaBrCd)) {
            ret &= searchBranch(cMsg, sMsg, glblCmpyCd);
        }
        // START 2018/12/11 T.Ogura [QC#28755,ADD]
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            ret &= searchLocation(cMsg, sMsg, glblCmpyCd);
        }
        // END   2018/12/11 T.Ogura [QC#28755,ADD]
        if (!ret) {
            return;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            ssmParam.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        ssmParam.put(DB_PARAM_COA_BR_CD, cMsg.coaBrCd.getValue());
        ssmParam.put(DB_PARAM_MAX_ROWNUM, new BigDecimal(sMsg.A.length() + 1));
        // START 2023/08/15 G.Quan [QC#61208, ADD]
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        // END 2023/08/15 G.Quan [QC#61208, ADD]

        S21SsmEZDResult result = null;
        result = NLCL0630Query.getInstance().search(ssmParam, sMsg);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            return;
        }

        // Max Recode Over
        int queryResCnt = result.getQueryResultCount();
        if (queryResCnt > sMsg.A.length()) {
            cMsg.setMessageInfo(NMAM8181W, new String[] {String.format("%03d", sMsg.A.length()), String.format("%03d", sMsg.A.length())});
            queryResCnt = sMsg.A.length();
        }

        String prePhysInvtyNum = null;
        for (int j = 0; j < sMsg.A.getValidCount(); j++) {
            if (prePhysInvtyNum == null || !prePhysInvtyNum.equals(sMsg.A.no(j).physInvtyNum_A1.getValue())) {
                prePhysInvtyNum = sMsg.A.no(j).physInvtyNum_A1.getValue();
            } else {
                sMsg.A.no(j).physInvtyNum_A1.clear();
                sMsg.A.no(j).techTocCd_A1.clear();
                sMsg.A.no(j).techNm_A1.clear();
                sMsg.A.no(j).physInvtyDt_A1.clear();
                sMsg.A.no(j).physInvtyCtrlNm_A1.clear();
            }
        }

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
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(queryResCnt);

    }

    /**
     * Search Technician
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchTechnician(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg, String glblCmpyCd) {

        cMsg.techNm.clear();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            ssmParam.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        S21SsmEZDResult result = null;
        result = NLCL0630Query.getInstance().getTechnicianName(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.techTocCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.techTocCd.getValue()});
            return false;
        }

        List<Map> technicianList = (List<Map>) result.getResultObject();
        if (technicianList.size() == 0) {
            cMsg.techTocCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.techTocCd.getValue()});
            return false;
        }
        Map record = technicianList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.techNm, (String) record.get(DB_COLUMN_TECH_NM));
        return true;
    }

    /**
     * Search Branch
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchBranch(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg, String glblCmpyCd) {

        cMsg.coaBrNm.clear();
        COA_BRTMsg tMsg = new COA_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, cMsg.coaBrCd);
        tMsg = (COA_BRTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.coaBrCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.coaBrCd.getValue() });
            return false;
        }
        ZYPEZDItemValueSetter.setValue(cMsg.coaBrNm, tMsg.coaBrNm);
        return true;
    }

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Search Location
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchLocation(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg, String glblCmpyCd) {

        cMsg.rtlWhNm.clear();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        if (funcList == null || !funcList.contains(FUNC_ID_ALL_USERS)) {
            ssmParam.put(DB_PARAM_USER_ID, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        S21SsmEZDResult result = null;
        result = NLCL0630Query.getInstance().getLocationName(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            return false;
        }

        List<Map> locationList = (List<Map>) result.getResultObject();
        if (locationList.size() == 0) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            return false;
        }
        Map record = locationList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, (String) record.get(DB_COLUMN_RTL_WH_NM));
        return true;
    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    /**
     * Cancel
     * @param cMsg NLCL0630CMsg
     * @param sMsg NLCL0630SMsg
     * @param glblCmpyCd String
     */
    public static void cancel(NLCL0630CMsg cMsg, NLCL0630SMsg sMsg, String glblCmpyCd) {

        int rowNum = cMsg.xxRadioBtn.getValueInt();

        NLZC061001PMsg pMsg = new NLZC061001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.salesDate, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.xxMode, CANCEL_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, cMsg.A.no(rowNum).physInvtyNum_A1);
        ZYPEZDItemValueSetter.setValue(pMsg.xxUserId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());

        NLZC061001 nlzc061001 = new NLZC061001();
        nlzc061001.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                cMsg.setMessageInfo(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
    }

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Update Status
     * @param cMsg NLCL0630CMsg
     * @param glblCmpyCd String
     */
    public static void updateSts(NLCL0630CMsg cMsg, String glblCmpyCd) {

        String physInvtyNum = cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).physInvtyNum_A1.getValue();
        String physInvtyStsDescTxt_01 = getPhysicalInventoryStatusName(cMsg, glblCmpyCd);
        String physInvtyCntStsCd = getCountStatus(cMsg, glblCmpyCd, physInvtyNum);

        if (PHYS_INVTY_CNT_STS.SCHEDULED.equals(physInvtyCntStsCd)) {

            // START 2018/12/11 T.Ogura [QC#28755,ADD]
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).rtlWhCd_A1.getValue());
            ssmParam.put(DB_PARAM_PHYS_INVTY_STS_CD, PHYS_INVTY_STS.OPEN);
            S21SsmEZDResult ssmResult = NLCL0630Query.getInstance().countSameLocationOpenStatus(ssmParam);
            if (ssmResult.isCodeNormal()) {
                BigDecimal count = (BigDecimal) ssmResult.getResultObject();
                if (count.compareTo(BigDecimal.ZERO) > 0) {
                    cMsg.xxRadioBtn.setErrorInfo(1, NLCM0231E, new String[] {cMsg.A.no(cMsg.xxRadioBtn.getValueInt()).rtlWhCd_A1.getValue()});
                    return;
                }
            }
            // END   2018/12/11 T.Ogura [QC#28755,ADD]

            if (!updateCountStatus(cMsg, glblCmpyCd, physInvtyNum, PHYS_INVTY_CNT_STS.COUNTING)) {
                return;
            }
            if (!setPIStartTime(cMsg, glblCmpyCd, physInvtyNum, physInvtyStsDescTxt_01)) {
                return;
            }
        }
    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Get Physical Inventory Status Name
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return String
     */
    public static String getPhysicalInventoryStatusName(NLCL0630CMsg cMsg, String glblCmpyCd) {

        String physInvtyStsDescTxt_01 = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_STS_CD, PHYS_INVTY_STS.OPEN);

        S21SsmEZDResult result = null;
        result = NLCL0630Query.getInstance().getPhysicalInventoryStatusName(ssmParam);

        if (!result.isCodeNormal()) {
            return physInvtyStsDescTxt_01;
        }

        List<Map> physicalInventoryStatusNameList = (List<Map>) result.getResultObject();
        if (physicalInventoryStatusNameList.size() == 0) {
            return physInvtyStsDescTxt_01;
        }

        Map record = physicalInventoryStatusNameList.get(0);
        return (String) record.get(DB_COLUMN_PHYS_INVTY_STS_DESC_TXT);

    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Get Count Status
     * @param cMsg NLCL0630CMsg
     * @param glblCmpyCd String
     * @param physInvtyNum String
     * @return String
     */
    public static String getCountStatus(NLCL0630CMsg cMsg, String glblCmpyCd, String physInvtyNum) {

        String physInvtyCntStsCd = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, physInvtyNum);
        ssmParam.put(DB_PARAM_ROWNUM, DB_PARAM_VALUE_ROWNUM_1);

        S21SsmEZDResult result = null;
        result = NLCL0630Query.getInstance().getCountStatus(ssmParam);

        if (!result.isCodeNormal()) {
            return physInvtyCntStsCd;
        }

        List<Map> countStatusList = (List<Map>) result.getResultObject();
        if (countStatusList.size() == 0) {
            return physInvtyCntStsCd;
        }
        Map record = countStatusList.get(0);
        return (String) record.get(DB_COLUMN_PHYS_INVTY_CNT_STS_CD);

    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Update Count Status
     * @param cMsg NLCL0630CMsg
     * @param glblCmpyCd String
     * @param physInvtyNum String
     * @param changeStatus String
     * @return false for error
     */
    public static boolean updateCountStatus(NLCL0630CMsg cMsg, String glblCmpyCd, String physInvtyNum, String changeStatus) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, physInvtyNum);

        S21SsmEZDResult result = null;
        result = NLCL0630Query.getInstance().getPIControlsFromPINumer(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> piControlList = (List<Map>) result.getResultObject();

        for (int i = 0; i < piControlList.size(); i++) {

            Map record = piControlList.get(i);

            PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

            PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                return false;
            }

            ZYPEZDItemValueSetter.setValue(outMsg.physInvtyCntStsCd, changeStatus);

            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }
        return true;
    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

    // START 2018/12/03 T.Ogura [QC#27978,ADD]
    /**
     * Set PI Start Time
     * @param cMsg NLCL0630CMsg
     * @param glblCmpyCd String
     * @param physInvtyNum String
     * @param physInvtyStsDescTxt_01 String
     * @return false for error
     */
    public static boolean setPIStartTime(NLCL0630CMsg cMsg, String glblCmpyCd, String physInvtyNum, String physInvtyStsDescTxt_01) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, physInvtyNum);

        S21SsmEZDResult result = null;
        result = NLCL0630Query.getInstance().getPIControlsFromPINumer(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> piControlList = (List<Map>) result.getResultObject();
        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);

        for (int i = 0; i < piControlList.size(); i++) {

            Map record = piControlList.get(i);

            PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

            PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                return false;
            }

            ZYPEZDItemValueSetter.setValue(outMsg.physInvtyStartTs, systemTime);
            ZYPEZDItemValueSetter.setValue(outMsg.physInvtyStsCd, PHYS_INVTY_STS.OPEN);
            ZYPEZDItemValueSetter.setValue(outMsg.physInvtyStsNm, physInvtyStsDescTxt_01);

            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }
        return true;
    }
    // END   2018/12/03 T.Ogura [QC#27978,ADD]

}
