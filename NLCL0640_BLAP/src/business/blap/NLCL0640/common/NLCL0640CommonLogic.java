/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0640.common;

import static business.blap.NLCL0640.constant.NLCL0640Constant.API_PARAM_TIMESTAMP_FORMAT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.CONST_PAGE_SIZE;
import static business.blap.NLCL0640.constant.NLCL0640Constant.CREATE_MODE;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_CNT_ADJ_VAR;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_CNT_ADJ_VAR_FLG_Y;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_CNT_ITEM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_CNT_SERIAL;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_CUR_LOC_NUM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_FROM_SER_NUM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_INVTY_LOC_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_INVTY_QTY;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_LG_SER_NUM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_LOC_NM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_MAX_FIRST_CNT_INP_TS;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_MAX_INVTY_REGD_TS;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_MAX_SCD_CNT_INP_TS;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_MDSE_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_MDSE_DESC_SHORT_TXT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_PHYS_INVTY_CNT_DTL_PK;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_PHYS_INVTY_CNT_HDR_PK;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_PHYS_INVTY_CNT_STS_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_PHYS_INVTY_CTRL_PK;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_QTY;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_RCV_SER_TAKE_FLG;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_RTL_SWH_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_RTL_WH_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_SER_NUM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_STK_STS_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_SUM_ADJ_VAR_AMT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_SUM_ADJ_VAR_QTY;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_SUM_FIRST_CNT_INP_QTY;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_SUM_INVTY_AVAL_QTY;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_SUM_INVTY_QTY;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_SUM_SCD_CNT_INP_QTY;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_TECH_LOC_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_THRU_SER_NUM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_COLUMN_WH_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_ADJ_VAR_FLG;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_INVTY_QTY;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_LOC_STS_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_LOC_TP_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_MDSE_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_PHYS_INVTY_CTRL_NM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_PHYS_INVTY_CTRL_PK;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_PHYS_INVTY_DT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_PHYS_INVTY_NUM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_RCV_SER_TAKE_FLG;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_ROWNUM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_SALES_DATE;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_SER_NUM;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_STK_STS_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_SVC_MACH_MSTR_STS_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_TECH_LOC_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_TECH_TOC_CD;
import static business.blap.NLCL0640.constant.NLCL0640Constant.DB_PARAM_VALUE_ROWNUM_1;
import static business.blap.NLCL0640.constant.NLCL0640Constant.MAX_UPLOAD_COUNT;
import static business.blap.NLCL0640.constant.NLCL0640Constant.MSG_VALUE_PHYS_INVTY_CNT_DTL;
import static business.blap.NLCL0640.constant.NLCL0640Constant.MSG_VALUE_PHYS_INVTY_CNT_HDR;
import static business.blap.NLCL0640.constant.NLCL0640Constant.MSG_VALUE_PHYS_INVTY_CTRL;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0025E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0146E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0147E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0148E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0180E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLCM0181E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NLZM2292E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NMAM0176E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.NMAM0177E;
import static business.blap.NLCL0640.constant.NLCL0640Constant.TIMESTAMP_PATTERN;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0640.NLCL0640CMsg;
import business.blap.NLCL0640.NLCL0640Query;
import business.blap.NLCL0640.NLCL0640SMsg;
import business.blap.NLCL0640.NLCL0640_ASMsg;
import business.blap.NLCL0640.NLCL0640_BSMsg;
import business.blap.NLCL0640.constant.NLCL0640Constant;
import business.db.PHYS_INVTY_ADJTMsg;
import business.db.PHYS_INVTY_CNT_DTLTMsg;
import business.db.PHYS_INVTY_CNT_HDRTMsg;
import business.db.PHYS_INVTY_CTRLTMsg;
import business.parts.NLZC004001PMsg;
import business.parts.NLZC061001PMsg;
import business.parts.NLZC063001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC004001.NLZC004001;
import com.canon.cusa.s21.api.NLZ.NLZC004001.constant.NLZC004001Constant;
import com.canon.cusa.s21.api.NLZ.NLZC061001.NLZC061001;
import com.canon.cusa.s21.api.NLZ.NLZC063001.NLZC063001;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_ADJ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLCL0640 Tech PI Count
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/10/2016   CITS         Makoto Okigami     Create          N/A
 * 10/17/2018   CITS            M.Naito         Update          QC#28770
 * 10/26/2018   CITS            T.Wada          Update          QC#26948-2
 * 12/03/2018   Fujitsu         T.Ogura         Update          QC#27978
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 * 12/21/2018   Fujitsu         T.Ogura         Update          QC#29730
 * 05/08/2019   CITS            T.Tokutomi      Update          QC#50029
 *</pre>
 */
public class NLCL0640CommonLogic {


    /**
     * Get Count Status
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     */
    public static void getCountStatus(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_ROWNUM, DB_PARAM_VALUE_ROWNUM_1);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getCountStatus(ssmParam);

        if (!result.isCodeNormal()) {
            return;
        }

        List<Map> countStatusList = (List<Map>) result.getResultObject();
        if (countStatusList.size() == 0) {
            return;
        }
        Map record = countStatusList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.physInvtyCntStsCd, (String) record.get(DB_COLUMN_PHYS_INVTY_CNT_STS_CD));

    }

    /**
     * Update Count Status
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @param changeStatus String
     * @return false for error
     */
    public static boolean updateCountStatus(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd, String changeStatus) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getPIControlsFromPINumer(ssmParam);

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
            // START 2018/10/17 M.Naito [QC#28770, ADD]
            if (PHYS_INVTY_CNT_STS.WAITAPPROVAL.equals(changeStatus)) {
                ZYPEZDItemValueSetter.setValue(outMsg.adjSubmtTs, ZYPDateUtil.getCurrentSystemTime(TIMESTAMP_PATTERN));
            }
            // END 2018/10/17 M.Naito [QC#28770, ADD]
            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }
        return true;
    }

    // START 2018/12/03 T.Ogura [QC#27978,DEL]
//    /**
//     * Set PI Start Time
//     * @param cMsg NLCL0640CMsg
//     * @param sMsg NLCL0640SMsg
//     * @param glblCmpyCd String
//     * @return false for error
//     */
//    public static boolean setPIStartTime(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
//        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
//
//        S21SsmEZDResult result = null;
//        result = NLCL0640Query.getInstance().getPIControlsFromPINumer(ssmParam);
//
//        if (!result.isCodeNormal()) {
//            return true;
//        }
//
//        List<Map> piControlList = (List<Map>) result.getResultObject();
//        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);
//
//        for (int i = 0; i < piControlList.size(); i++) {
//
//            Map record = piControlList.get(i);
//
//            PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
//            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
//            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
//
//            PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);
//
//            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
//                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
//                return false;
//            }
//
//            ZYPEZDItemValueSetter.setValue(outMsg.physInvtyStartTs, systemTime);
//            ZYPEZDItemValueSetter.setValue(outMsg.physInvtyStsCd, PHYS_INVTY_STS.OPEN);
//            ZYPEZDItemValueSetter.setValue(outMsg.physInvtyStsNm, cMsg.physInvtyStsDescTxt_01);
//
//            EZDTBLAccessor.update(outMsg);
//            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
//                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
//                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
//                return false;
//            }
//        }
//        return true;
//    }
    // END   2018/12/03 T.Ogura [QC#27978,DEL]

    /**
     * Get TechLocInfo from Tech Location
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     */
    public static void getTechLocInfoFromTechLocation(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        // START 2018/12/11 T.Ogura [QC#28755,MOD]
//        ssmParam.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        // END   2018/12/11 T.Ogura [QC#28755,MOD]
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getTechLocInfoFromTechLocation(ssmParam);

        if (!result.isCodeNormal()) {
            return;
        }

        List<Map> techLocationList = (List<Map>) result.getResultObject();

        cMsg.techLocCd_PD.clear();
        cMsg.locNm_PD.clear();
        ZYPTableUtil.clear(cMsg.T);
        int i = 0;
        for (i = 0; i < techLocationList.size(); i++) {

            if (i >= cMsg.techLocCd_PD.length()) {
                break;
            }

            Map record = techLocationList.get(i);
            ZYPEZDItemValueSetter.setValue(cMsg.techLocCd_PD.no(i), (String) record.get(DB_COLUMN_TECH_LOC_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.locNm_PD.no(i), (String) record.get(DB_COLUMN_LOC_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.T.no(i).techLocCd_T1, (String) record.get(DB_COLUMN_TECH_LOC_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.T.no(i).locNm_T1, (String) record.get(DB_COLUMN_LOC_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.T.no(i).rtlWhCd_T1, (String) record.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.T.no(i).rtlSwhCd_T1, (String) record.get(DB_COLUMN_RTL_SWH_CD));

        }
        cMsg.T.setValidCount(i);

    }

    /**
     * Get TechLocInfo From PI Count Header
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     */
    public static void getTechLocInfoFromPICountHeader(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        // START 2018/12/11 T.Ogura [QC#28755,MOD]
//        ssmParam.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        // END   2018/12/11 T.Ogura [QC#28755,MOD]
        ssmParam.put(DB_PARAM_PHYS_INVTY_CTRL_NM, cMsg.physInvtyCtrlNm.getValue());
        ssmParam.put(DB_PARAM_PHYS_INVTY_DT, cMsg.physInvtyDt.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getTechLocInfoFromPICountHeader(ssmParam);

        if (!result.isCodeNormal()) {
            return;
        }

        List<Map> piCountHeaderList = (List<Map>) result.getResultObject();

        cMsg.techLocCd_PD.clear();
        cMsg.locNm_PD.clear();
        ZYPTableUtil.clear(cMsg.T);
        int i = 0;
        for (i = 0; i < piCountHeaderList.size(); i++) {

            if (i >= cMsg.techLocCd_PD.length()) {
                break;
            }

            Map record = piCountHeaderList.get(i);
            ZYPEZDItemValueSetter.setValue(cMsg.techLocCd_PD.no(i), (String) record.get(DB_COLUMN_TECH_LOC_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.locNm_PD.no(i), (String) record.get(DB_COLUMN_LOC_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.T.no(i).techLocCd_T1, (String) record.get(DB_COLUMN_TECH_LOC_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.T.no(i).locNm_T1, (String) record.get(DB_COLUMN_LOC_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.T.no(i).rtlWhCd_T1, (String) record.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.T.no(i).rtlSwhCd_T1, (String) record.get(DB_COLUMN_RTL_SWH_CD));

        }
        cMsg.T.setValidCount(i);

    }

    /**
     * Check Duplication Serial
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean checkDuplicationSerial(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(cMsg.serNum)) {
            return true;
        }

        for (int n = 0; n < sMsg.A.getValidCount(); ++n) {
            if (cMsg.mdseCd.getValue().equals(sMsg.A.no(n).mdseCd_A.getValue())
                    && cMsg.serNum.getValue().equals(sMsg.A.no(n).serNum_A.getValue())) {
                cMsg.setMessageInfo(NLCM0146E, new String[] {cMsg.mdseCd.getValue(), cMsg.serNum.getValue() });
                cMsg.serNum.setErrorInfo(1, NLCM0146E, new String[] {cMsg.mdseCd.getValue(), cMsg.serNum.getValue() });
                return false;
            }
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd.getValue());
        ssmParam.put(DB_PARAM_SER_NUM, cMsg.serNum.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().countSerial(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NLCM0146E, new String[] {cMsg.mdseCd.getValue(), cMsg.serNum.getValue()});
            cMsg.serNum.setErrorInfo(1, NLCM0146E, new String[] {cMsg.mdseCd.getValue(), cMsg.serNum.getValue() });
            return false;
        }

        List<Map<String, Object>> countList = (List<Map<String, Object>>) result.getResultObject();
        Map<String, Object> record = countList.get(0);
        BigDecimal count = (BigDecimal) record.get(DB_COLUMN_CNT_SERIAL);
        if (count.intValue() > 0) {
            cMsg.setMessageInfo(NLCM0146E, new String[] {cMsg.mdseCd.getValue(), cMsg.serNum.getValue()});
            cMsg.serNum.setErrorInfo(1, NLCM0146E, new String[] {cMsg.mdseCd.getValue(), cMsg.serNum.getValue() });
            return false;
        }

        return true;
    }

    /**
     * Check Duplication Item
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean checkDuplicationItem(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        for (int n = 0; n < sMsg.A.getValidCount(); ++n) {
            if (cMsg.techLocCd_SL.getValue().equals(sMsg.A.no(n).techLocCd_A.getValue())
                    && cMsg.mdseCd.getValue().equals(sMsg.A.no(n).mdseCd_A.getValue())) {
                cMsg.setMessageInfo(NLCM0180E, new String[] {cMsg.mdseCd.getValue()});
                cMsg.mdseCd.setErrorInfo(1, NLCM0180E, new String[] {cMsg.mdseCd.getValue() });
                return false;
            }
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_TECH_LOC_CD, cMsg.techLocCd_SL.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().countItem(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NLCM0180E, new String[] {cMsg.mdseCd.getValue()});
            cMsg.mdseCd.setErrorInfo(1, NLCM0180E, new String[] {cMsg.mdseCd.getValue()});
            return false;
        }

        List<Map<String, Object>> countList = (List<Map<String, Object>>) result.getResultObject();
        Map<String, Object> record = countList.get(0);
        BigDecimal count = (BigDecimal) record.get(DB_COLUMN_CNT_ITEM);
        if (count.intValue() > 0) {
            cMsg.setMessageInfo(NLCM0180E, new String[] {cMsg.mdseCd.getValue()});
            cMsg.mdseCd.setErrorInfo(1, NLCM0180E, new String[] {cMsg.mdseCd.getValue()});
            return false;
        }

        return true;
    }

    /**
     * Check Serial Format
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean checkSerialFormat(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getSerialNumRange(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map<String, Object>> serialNumberRangeList = (List<Map<String, Object>>) result.getResultObject();
        if (serialNumberRangeList.size() == 0) {
            return true;
        }

        boolean rangeOK = false;
        for (Map<String, Object> record : serialNumberRangeList) {

            BigDecimal lgSerNum = (BigDecimal) record.get(DB_COLUMN_LG_SER_NUM);
            String fromSerNum = (String) record.get(DB_COLUMN_FROM_SER_NUM);
            String thruSerNum = (String) record.get(DB_COLUMN_THRU_SER_NUM);

            if (lgSerNum == null || fromSerNum == null || thruSerNum == null) {
                continue;
            }

            if (lgSerNum.intValue() != cMsg.serNum.getValue().length()) {
                continue;
            }

            if (fromSerNum.compareTo(cMsg.serNum.getValue()) <= 0 && thruSerNum.compareTo(cMsg.serNum.getValue()) >= 0) {
                rangeOK = true;
            }
        }

        if (!rangeOK) {
            cMsg.setMessageInfo(NLZM2292E);
            cMsg.serNum.setErrorInfo(1, NLZM2292E);
        }

        return rangeOK;
    }

    /**
     * Get Merchandise Name
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean getMerchandiseName(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getMerchandiseName(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NLCM0148E, new String[] {cMsg.mdseCd.getValue()});
            cMsg.mdseCd.setErrorInfo(1, NLCM0148E, new String[] {cMsg.mdseCd.getValue()});
            return false;
        }

        List<Map<String, Object>> merchandiseNameList = (List<Map<String, Object>>) result.getResultObject();
        if (merchandiseNameList.size() == 0) {
            cMsg.setMessageInfo(NLCM0148E, new String[] {cMsg.mdseCd.getValue()});
            cMsg.mdseCd.setErrorInfo(1, NLCM0148E, new String[] {cMsg.mdseCd.getValue()});
            return false;
        }
        Map<String, Object> record = merchandiseNameList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, (String) record.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(cMsg.rcvSerTakeFlg, (String) record.get(DB_COLUMN_RCV_SER_TAKE_FLG));

        return true;
    }

    /**
     * addCountItem
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean addCountItem(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        // Check size limit.
        // QC#26948-2 Mod Start
//      if (sMsg.A.getValidCount() >= sMsg.A.length()) {
        if (sMsg.A.getValidCount() >= MAX_UPLOAD_COUNT) {
        // QC#26948-2 Mod End
            cMsg.setMessageInfo(NLCM0025E);
            return false;
        }

        // EZDMsg.copy(cMsg, null, sMsg, null);
        NLCL0640_ASMsg asMsg = sMsg.A.no(sMsg.A.getValidCount());

        // Map LOC_NM from TECH_LOC_CD
        ZYPEZDItemValueSetter.setValue(asMsg.techLocCd_A, cMsg.techLocCd_SL);
        asMsg.locNm_A.clear();
        for (int n = 0; n < cMsg.T.getValidCount(); ++n) {
            if (asMsg.techLocCd_A.getValue().equals(cMsg.T.no(n).techLocCd_T1.getValue())) {
                ZYPEZDItemValueSetter.setValue(asMsg.locNm_A, cMsg.T.no(n).locNm_T1);
                break;
            }
        }

        // Find MDSE
        if (!NLCL0640CommonLogic.getMerchandiseName(cMsg, sMsg, glblCmpyCd)) {
            return false;
        }

        if (ZYPConstant.FLG_OFF_N.equals(cMsg.rcvSerTakeFlg.getValue())) {
            // Non-Serial items
            if (ZYPCommonFunc.hasValue(cMsg.serNum)) {
                cMsg.serNum.clear();
            }

            if (cMsg.cntInpQty.getValueInt() < 0) {
                cMsg.setMessageInfo(NLCM0147E, new String[] {String.valueOf(cMsg.cntInpQty.getValueInt()), cMsg.mdseCd.getValue() });
                cMsg.cntInpQty.setErrorInfo(1, NLCM0147E, new String[] {String.valueOf(cMsg.cntInpQty.getValueInt()), cMsg.mdseCd.getValue() });
                return false;
            }

            if (PHYS_INVTY_CNT_STS.COUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
                // When COUNTING, check item duplication. Skip
                // duplication check when RECOUNTING.
                if (!NLCL0640CommonLogic.checkDuplicationItem(cMsg, sMsg, glblCmpyCd)) {
                    return false;
                }
            }

        } else {
            // Serial items
            if (!ZYPCommonFunc.hasValue(cMsg.serNum)) {
                cMsg.setMessageInfo(NLCM0181E, new String[] {cMsg.mdseCd.getValue() });
                cMsg.serNum.setErrorInfo(1, NLCM0181E, new String[] {cMsg.mdseCd.getValue() });
                return false;
            }

            if (cMsg.cntInpQty.getValueInt() < 0 || cMsg.cntInpQty.getValueInt() > 1) {
                cMsg.setMessageInfo(NLCM0147E, new String[] {String.valueOf(cMsg.cntInpQty.getValueInt()), cMsg.mdseCd.getValue() });
                cMsg.cntInpQty.setErrorInfo(1, NLCM0147E, new String[] {String.valueOf(cMsg.cntInpQty.getValueInt()), cMsg.mdseCd.getValue() });
                return false;
            }

            if (PHYS_INVTY_CNT_STS.COUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
                // When COUNTING, check item duplication. Skip
                // duplication check when RECOUNTING.
                if (!NLCL0640CommonLogic.checkDuplicationSerial(cMsg, sMsg, glblCmpyCd)) {
                    return false;
                }

                if (!NLCL0640CommonLogic.checkSerialFormat(cMsg, sMsg, glblCmpyCd)) {
                    return false;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(asMsg.mdseCd_A, cMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(asMsg.mdseDescShortTxt_A, cMsg.mdseDescShortTxt);
        ZYPEZDItemValueSetter.setValue(asMsg.rcvSerTakeFlg_A, cMsg.rcvSerTakeFlg);
        ZYPEZDItemValueSetter.setValue(asMsg.cntInpQty_A, cMsg.cntInpQty);
        ZYPEZDItemValueSetter.setValue(asMsg.serNum_A, cMsg.serNum);

        sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);

        return true;
    }

    /**
     * saveCountItems
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean saveCountItems(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        for (int n = 0; n < sMsg.A.getValidCount(); ++n) {
            NLCL0640_ASMsg asMsg = sMsg.A.no(n);

            BigDecimal piControlPK = getPIControlPK(glblCmpyCd, cMsg.physInvtyNum.getValue(), asMsg.techLocCd_A.getValue());
            if (piControlPK == null) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_HDR });
                return false;
            }

            BigDecimal piCountHeaderPK = getPICountHeaderPK(glblCmpyCd, piControlPK, asMsg.mdseCd_A.getValue());
            if (piCountHeaderPK == null) {
                piCountHeaderPK = createPICountHeader(cMsg, sMsg, glblCmpyCd, piControlPK, cMsg.physInvtyCtrlNm.getValue(), cMsg.physInvtyDt.getValue(), asMsg.techLocCd_A.getValue(), cMsg.techTocCd.getValue(), asMsg.mdseCd_A.getValue());
            }
            if (piCountHeaderPK == null) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_HDR });
                return false;
            }

            if (null == createPICountDetail(cMsg, sMsg, glblCmpyCd, piCountHeaderPK, piControlPK, asMsg.techLocCd_A.getValue(), asMsg.mdseCd_A.getValue(), asMsg.serNum_A.getValue(), asMsg.cntInpQty_A.getValue())) {
                return false;
            }
        }

        return true;
    }

    /**
     * updateCountItems
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean updateCountItems(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        for (int n = 0; n < sMsg.A.getValidCount(); ++n) {
            NLCL0640_ASMsg asMsg = sMsg.A.no(n);

            BigDecimal piControlPK = getPIControlPK(glblCmpyCd, cMsg.physInvtyNum.getValue(), asMsg.techLocCd_A.getValue());
            if (piControlPK == null) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL });
                return false;
            }

            String serNum = null;
            if (ZYPConstant.FLG_ON_Y.equals(asMsg.rcvSerTakeFlg_A.getValue())) {
                serNum = asMsg.serNum_A.getValue();
            }
            BigDecimal piCountDetailPK = getPICountDetailPK(glblCmpyCd, piControlPK, asMsg.mdseCd_A.getValue(), serNum);
            if (piCountDetailPK == null) {
                // START 2018/12/21 T.Ogura [QC#29730,MOD]
//                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL });
//                return false;
                BigDecimal piCountHeaderPK = getPICountHeaderPK(glblCmpyCd, piControlPK, asMsg.mdseCd_A.getValue());
                if (piCountHeaderPK == null) {
                    piCountHeaderPK = createPICountHeader(cMsg, sMsg, glblCmpyCd, piControlPK, cMsg.physInvtyCtrlNm.getValue(), cMsg.physInvtyDt.getValue(), asMsg.techLocCd_A.getValue(), cMsg.techTocCd.getValue(), asMsg.mdseCd_A.getValue());
                }
                if (piCountHeaderPK == null) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_HDR });
                    return false;
                }
                if (null == createPICountDetail(cMsg, sMsg, glblCmpyCd, piCountHeaderPK, piControlPK, asMsg.techLocCd_A.getValue(), asMsg.mdseCd_A.getValue(), asMsg.serNum_A.getValue(), asMsg.cntInpQty_A.getValue())) {
                    return false;
                }
                continue;
                // END   2018/12/21 T.Ogura [QC#29730,MOD]
            }

            PHYS_INVTY_CNT_DTLTMsg inMsg = new PHYS_INVTY_CNT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntDtlPk, piCountDetailPK);
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

            PHYS_INVTY_CNT_DTLTMsg outMsg = (PHYS_INVTY_CNT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL });
                return false;
            }

            String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);

            // START 2018/12/21 T.Ogura [QC#29730,ADD]
            ZYPEZDItemValueSetter.setValue(outMsg.cntInpQty, asMsg.cntInpQty_A);
            // END   2018/12/21 T.Ogura [QC#29730,ADD]
            ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpQty, asMsg.cntInpQty_A);
            ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpTs, systemTime);
            ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpQty, asMsg.cntInpQty_A);
            ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpTs, systemTime);

            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL });
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }

    /**
     * getPIControlPK
     * @param glblCmpyCd
     * @param physInvtyNum
     * @param techLocCd
     * @return
     */
    private static BigDecimal getPIControlPK(String glblCmpyCd, String physInvtyNum, String techLocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, physInvtyNum);
        ssmParam.put(DB_PARAM_TECH_LOC_CD, techLocCd);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getPIControl(ssmParam);

        if (!result.isCodeNormal()) {
            return null;
        }

        List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
        if (list.size() <= 0) {
            return null;
        }
        Map<String, Object> record = list.get(0);
        return (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK);
    }

    /**
     * Get PI Count Header PK
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @param piControlPK BigDecimal
     * @param mdseCd String
     * @return PI Count Header PK
     */
    private static BigDecimal getPICountHeaderPK(String glblCmpyCd, BigDecimal piControlPK, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_CTRL_PK, piControlPK);
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getPICountHeader(ssmParam);

        if (!result.isCodeNormal()) {
            return null;
        }

        List<Map<String, Object>> piCountHeaderList = (List<Map<String, Object>>) result.getResultObject();
        if (piCountHeaderList.size() == 0) {
            return null;
        }
        Map<String, Object> record = piCountHeaderList.get(0);
        return (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CNT_HDR_PK);
    }

    /**
     * Get PI Count Detail PK
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @param piControlPK BigDecimal
     * @return PI Count Header PK
     */
    private static BigDecimal getPICountDetailPK(String glblCmpyCd, BigDecimal piControlPK, String mdseCd, String serNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_CTRL_PK, piControlPK);
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);
        if (ZYPCommonFunc.hasValue(serNum)) {
            ssmParam.put(DB_PARAM_SER_NUM, serNum);
        }

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getPICountDetail(ssmParam);

        if (!result.isCodeNormal()) {
            return null;
        }

        List<Map<String, Object>> piCountDetailList = (List<Map<String, Object>>) result.getResultObject();
        if (piCountDetailList.size() == 0) {
            return null;
        }
        Map<String, Object> record = piCountDetailList.get(0);
        return (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CNT_DTL_PK);
    }

    /**
     * createPICountHeader
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @param piControlPK
     * @param physInvtyCtrlNm
     * @param physInvtyDt
     * @param techLocCd
     * @param techTocCd
     * @param mdseCd
     * @return
     */
    private static BigDecimal createPICountHeader(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd, BigDecimal piControlPK, String physInvtyCtrlNm, String physInvtyDt, String techLocCd, String techTocCd, String mdseCd) {
        PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();

        String rtlWhCd = null;
        String rtlSwhCd = null;
        for (int i = 0; i < cMsg.T.getValidCount(); i++) {
            if (techLocCd.equals(cMsg.T.no(i).techLocCd_T1.getValue())) {
                rtlWhCd = cMsg.T.no(i).rtlWhCd_T1.getValue();
                rtlSwhCd = cMsg.T.no(i).rtlSwhCd_T1.getValue();
                break;
            }
        }

        BigDecimal physInvtyCntHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_HDR_SQ);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
        ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, piControlPK);
        ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlNm, physInvtyCtrlNm);
        ZYPEZDItemValueSetter.setValue(inMsg.physInvtyDt, physInvtyDt);
        ZYPEZDItemValueSetter.setValue(inMsg.whCd, techLocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlSwhCd, rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.techTocCd, techTocCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(inMsg.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(inMsg.sysCntRegdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(inMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);

        EZDTBLAccessor.insert(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_HDR });
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
            return null;
        }

        return physInvtyCntHdrSq;
    }

    /**
     * createPICountDetail
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @param piCountHeaderPK
     * @param piControlPK
     * @param techLocCd
     * @param mdseCd
     * @param serNum
     * @param cntInpQty
     * @return
     */
    private static BigDecimal createPICountDetail(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd, BigDecimal piCountHeaderPK, BigDecimal piControlPK, String techLocCd, String mdseCd, String serNum, BigDecimal cntInpQty) {
        PHYS_INVTY_CNT_DTLTMsg inMsg = new PHYS_INVTY_CNT_DTLTMsg();

        String rtlWhCd = null;
        String rtlSwhCd = null;
        for (int i = 0; i < cMsg.T.getValidCount(); i++) {
            if (techLocCd.equals(cMsg.T.no(i).techLocCd_T1.getValue())) {
                rtlWhCd = cMsg.T.no(i).rtlWhCd_T1.getValue();
                rtlSwhCd = cMsg.T.no(i).rtlSwhCd_T1.getValue();
                break;
            }
        }

        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);

        BigDecimal physInvtyCntDtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_DTL_SQ);
        ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntDtlPk, physInvtyCntDtlSq);
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, piCountHeaderPK);
        ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, piControlPK);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlSwhCd, rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(inMsg.serNum, serNum);
        ZYPEZDItemValueSetter.setValue(inMsg.cntInpQty, cntInpQty);
        // START 2018/12/21 T.Ogura [QC#29730,MOD]
//        ZYPEZDItemValueSetter.setValue(inMsg.firstCntInpQty, cntInpQty);
//        ZYPEZDItemValueSetter.setValue(inMsg.firstCntInpTs, systemTime);
        if (PHYS_INVTY_CNT_STS.COUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(inMsg.firstCntInpQty, cntInpQty);
            ZYPEZDItemValueSetter.setValue(inMsg.firstCntInpTs, systemTime);
        }
        if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(inMsg.scdCntInpQty, cntInpQty);
            ZYPEZDItemValueSetter.setValue(inMsg.scdCntInpTs, systemTime);
        }
        // END   2018/12/21 T.Ogura [QC#29730,MOD]
        ZYPEZDItemValueSetter.setValue(inMsg.ltstCntInpQty, cntInpQty);
        ZYPEZDItemValueSetter.setValue(inMsg.ltstCntInpTs, systemTime);
        ZYPEZDItemValueSetter.setValue(inMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(inMsg.physInvtyAdjStsCd, PHYS_INVTY_ADJ_STS.NONE);

        EZDTBLAccessor.insert(inMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL });
            cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
            return null;
        }

        return physInvtyCntDtlSq;
    }

    /**
     * Calculate Discrepancy for Non Serialized Item Input
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean calculateDiscrepancyForNonSerializedItemInput(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        // START 2018/12/21 T.Ogura [QC#29730,DEL]
//        ssmParam.put(DB_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);
        // END   2018/12/21 T.Ogura [QC#29730,DEL]
        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_LOC_TP_CD, LOC_TP.TECHNICIAN);
        ssmParam.put(DB_PARAM_RCV_SER_TAKE_FLG, ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getInventoryQty(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> inventoryQtyList = (List<Map>) result.getResultObject();
        if (inventoryQtyList.size() == 0) {
            return true;
        }

        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);

        for (int i = 0; i < inventoryQtyList.size(); i++) {

            Map record = inventoryQtyList.get(i);

            PHYS_INVTY_CNT_DTLTMsg inMsg = new PHYS_INVTY_CNT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntDtlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CNT_DTL_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

            PHYS_INVTY_CNT_DTLTMsg outMsg = (PHYS_INVTY_CNT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL});
                return false;
            }

            // START 2018/12/21 T.Ogura [QC#29730,ADD]
            if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(outMsg.scdCntInpQty)) {
                    ZYPEZDItemValueSetter.setValue(outMsg.cntInpQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpTs, systemTime);
                    ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpTs, systemTime);
                }
            }
            // END   2018/12/21 T.Ogura [QC#29730,ADD]
            ZYPEZDItemValueSetter.setValue(outMsg.invtyAvalQty, (BigDecimal) record.get(DB_COLUMN_SUM_INVTY_QTY));
            ZYPEZDItemValueSetter.setValue(outMsg.invtyRegdTs, systemTime);
            if (ZYPCommonFunc.hasValue(outMsg.ltstCntInpQty)) {
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarQty, outMsg.ltstCntInpQty.getValue().subtract((BigDecimal) record.get(DB_COLUMN_SUM_INVTY_QTY)));
            } else {
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarQty, BigDecimal.ZERO.subtract((BigDecimal) record.get(DB_COLUMN_SUM_INVTY_QTY)));
            }

            if (outMsg.adjVarQty.getValueInt() != 0) {

                ZYPEZDItemValueSetter.setValue(outMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);

                NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
                nlxc001001Bean.setGlblCmpyCd(glblCmpyCd);
                nlxc001001Bean.setInvtyLocCd((String) record.get(DB_COLUMN_WH_CD));
                nlxc001001Bean.setMdseCd(outMsg.mdseCd.getValue());
                nlxc001001Bean.setQty(outMsg.adjVarQty.getValue());

                NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);
                if (!nlxc001001Bean.getErrList().isEmpty()) {
                    cMsg.setMessageInfo(nlxc001001Bean.getErrList().get(0));
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarAmt, nlxc001001Bean.getTotPrcAmt());

            } else {

                ZYPEZDItemValueSetter.setValue(outMsg.adjVarAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarFlg, ZYPConstant.FLG_OFF_N);
            }

            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }

    /**
     * Calculate Discrepancy for Non Serialized Item Not Input
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean calculateDiscrepancyForNonSerializedItemNotInput(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_INVTY_QTY, BigDecimal.ZERO);
        ssmParam.put(DB_PARAM_LOC_TP_CD, LOC_TP.TECHNICIAN);
        ssmParam.put(DB_PARAM_RCV_SER_TAKE_FLG, ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getInventoryItem(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> inventoryItemList = (List<Map>) result.getResultObject();
        if (inventoryItemList.size() == 0) {
            return true;
        }

        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);

        for (int i = 0; i < inventoryItemList.size(); i++) {

            Map record = inventoryItemList.get(i);

            PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();

            BigDecimal physInvtyCntHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_HDR_SQ);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.techTocCd, cMsg.techTocCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlNm, cMsg.physInvtyCtrlNm);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyDt, cMsg.physInvtyDt);
            ZYPEZDItemValueSetter.setValue(inMsg.whCd, (String) record.get(DB_COLUMN_INVTY_LOC_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, (String) record.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.rtlSwhCd, (String) record.get(DB_COLUMN_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) record.get(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.sysCntRegdFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(inMsg.stkStsCd, (String) record.get(DB_COLUMN_STK_STS_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);

            EZDTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_HDR});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }

            PHYS_INVTY_CNT_DTLTMsg inDtlMsg = new PHYS_INVTY_CNT_DTLTMsg();

            BigDecimal physInvtyCntDtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_DTL_SQ);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyCntDtlPk, physInvtyCntDtlSq);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyCtrlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.rtlWhCd, (String) record.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.rtlSwhCd, (String) record.get(DB_COLUMN_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseCd, (String) record.get(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.invtyAvalQty, (BigDecimal) record.get(DB_COLUMN_INVTY_QTY));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.invtyRegdTs, systemTime);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.adjVarQty, BigDecimal.ZERO.subtract(inDtlMsg.invtyAvalQty.getValue()));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyAdjStsCd, PHYS_INVTY_ADJ_STS.NONE);

            NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
            nlxc001001Bean.setGlblCmpyCd(glblCmpyCd);
            nlxc001001Bean.setInvtyLocCd((String) record.get(DB_COLUMN_INVTY_LOC_CD));
            nlxc001001Bean.setMdseCd(inDtlMsg.mdseCd.getValue());
            nlxc001001Bean.setQty(inDtlMsg.adjVarQty.getValue());

            NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);
            if (!nlxc001001Bean.getErrList().isEmpty()) {
                cMsg.setMessageInfo(nlxc001001Bean.getErrList().get(0));
                return false;
            }
            ZYPEZDItemValueSetter.setValue(inDtlMsg.adjVarAmt, nlxc001001Bean.getTotPrcAmt());

            EZDTBLAccessor.insert(inDtlMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inDtlMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }

    /**
     * Calculate Discrepancy for Serialized Item Input
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean calculateDiscrepancyForSerializedItemInput(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        // START 2018/12/21 T.Ogura [QC#29730,DEL]
//        ssmParam.put(DB_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);
        // END   2018/12/21 T.Ogura [QC#29730,DEL]
        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_SVC_MACH_MSTR_STS_CD, new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED});
        ssmParam.put(DB_PARAM_RCV_SER_TAKE_FLG, ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getMachineMasterQty(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> machineMasterQtyList = (List<Map>) result.getResultObject();
        if (machineMasterQtyList.size() == 0) {
            return true;
        }

        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);

        for (int i = 0; i < machineMasterQtyList.size(); i++) {

            Map record = machineMasterQtyList.get(i);

            PHYS_INVTY_CNT_DTLTMsg inMsg = new PHYS_INVTY_CNT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntDtlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CNT_DTL_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

            PHYS_INVTY_CNT_DTLTMsg outMsg = (PHYS_INVTY_CNT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL});
                return false;
            }

            // START 2018/12/21 T.Ogura [QC#29730,ADD]
            if (PHYS_INVTY_CNT_STS.RECOUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(outMsg.scdCntInpQty)) {
                    ZYPEZDItemValueSetter.setValue(outMsg.cntInpQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpTs, systemTime);
                    ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpQty, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpTs, systemTime);
                }
            }
            // END   2018/12/21 T.Ogura [QC#29730,ADD]
            ZYPEZDItemValueSetter.setValue(outMsg.invtyAvalQty, (BigDecimal) record.get(DB_COLUMN_QTY));
            ZYPEZDItemValueSetter.setValue(outMsg.invtyRegdTs, systemTime);
            if (ZYPCommonFunc.hasValue(outMsg.ltstCntInpQty)) {
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarQty, outMsg.ltstCntInpQty.getValue().subtract((BigDecimal) record.get(DB_COLUMN_QTY)));
            } else {
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarQty, BigDecimal.ZERO.subtract((BigDecimal) record.get(DB_COLUMN_QTY)));
            }

            if (outMsg.adjVarQty.getValueInt() != 0) {

                ZYPEZDItemValueSetter.setValue(outMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);

                NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
                nlxc001001Bean.setGlblCmpyCd(glblCmpyCd);
                nlxc001001Bean.setInvtyLocCd((String) record.get(DB_COLUMN_WH_CD));
                nlxc001001Bean.setMdseCd(outMsg.mdseCd.getValue());
                nlxc001001Bean.setQty(outMsg.adjVarQty.getValue());

                NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);
                if (!nlxc001001Bean.getErrList().isEmpty()) {
                    cMsg.setMessageInfo(nlxc001001Bean.getErrList().get(0));
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarAmt, nlxc001001Bean.getTotPrcAmt());

            } else {

                ZYPEZDItemValueSetter.setValue(outMsg.adjVarAmt, BigDecimal.ZERO);
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarFlg, ZYPConstant.FLG_OFF_N);
            }

            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }

    /**
     * Calculate Discrepancy for Serialized Item Not Input
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean calculateDiscrepancyForSerializedItemNotInput(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_SVC_MACH_MSTR_STS_CD, new String[] {SVC_MACH_MSTR_STS.CREATED, SVC_MACH_MSTR_STS.REMOVED});
        ssmParam.put(DB_PARAM_RCV_SER_TAKE_FLG, ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getMachineMasterItem(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> machineMasterItemList = (List<Map>) result.getResultObject();
        if (machineMasterItemList.size() == 0) {
            return true;
        }

        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);

        for (int i = 0; i < machineMasterItemList.size(); i++) {

            Map record = machineMasterItemList.get(i);

            BigDecimal physInvtyCntHdrSq = getPICountHeaderPK(glblCmpyCd, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK), (String) record.get(DB_COLUMN_MDSE_CD));
            if (physInvtyCntHdrSq == null) {

                PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();

                physInvtyCntHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_HDR_SQ);
                ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
                ZYPEZDItemValueSetter.setValue(inMsg.techTocCd, cMsg.techTocCd);
                ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlNm, cMsg.physInvtyCtrlNm);
                ZYPEZDItemValueSetter.setValue(inMsg.physInvtyDt, cMsg.physInvtyDt);
                ZYPEZDItemValueSetter.setValue(inMsg.whCd, (String) record.get(DB_COLUMN_CUR_LOC_NUM));
                ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, (String) record.get(DB_COLUMN_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(inMsg.rtlSwhCd, (String) record.get(DB_COLUMN_RTL_SWH_CD));
                ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) record.get(DB_COLUMN_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(inMsg.sysCntRegdFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(inMsg.stkStsCd, (String) record.get(DB_COLUMN_STK_STS_CD));
                ZYPEZDItemValueSetter.setValue(inMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);

                EZDTBLAccessor.insert(inMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_HDR});
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                    return false;
                }
            }

            PHYS_INVTY_CNT_DTLTMsg inDtlMsg = new PHYS_INVTY_CNT_DTLTMsg();

            BigDecimal physInvtyCntDtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_DTL_SQ);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyCntDtlPk, physInvtyCntDtlSq);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyCtrlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.rtlWhCd, (String) record.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.rtlSwhCd, (String) record.get(DB_COLUMN_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseCd, (String) record.get(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.invtyAvalQty, (BigDecimal) record.get(DB_COLUMN_QTY));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.invtyRegdTs, systemTime);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.adjVarQty, BigDecimal.ZERO.subtract(inDtlMsg.invtyAvalQty.getValue()));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.serNum, (String) record.get(DB_COLUMN_SER_NUM));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyAdjStsCd, PHYS_INVTY_ADJ_STS.NONE);

            NLXC001001GetInventoryItemCostBean nlxc001001Bean = new NLXC001001GetInventoryItemCostBean();
            nlxc001001Bean.setGlblCmpyCd(glblCmpyCd);
            nlxc001001Bean.setInvtyLocCd((String) record.get(DB_COLUMN_CUR_LOC_NUM));
            nlxc001001Bean.setMdseCd(inDtlMsg.mdseCd.getValue());
            nlxc001001Bean.setQty(inDtlMsg.adjVarQty.getValue());

            NLXC001001GetInventoryItemCost.getInventoryItemCost(nlxc001001Bean);
            if (!nlxc001001Bean.getErrList().isEmpty()) {
                cMsg.setMessageInfo(nlxc001001Bean.getErrList().get(0));
                return false;
            }
            ZYPEZDItemValueSetter.setValue(inDtlMsg.adjVarAmt, nlxc001001Bean.getTotPrcAmt());

            EZDTBLAccessor.insert(inDtlMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inDtlMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }

    /**
     * Has Variance Items
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return Variance Items Count
     */
    public static int hasVarianceItems(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().countVarianceItem(ssmParam);

        if (!result.isCodeNormal()) {
            return 0;
        }

        List<Map> countList = (List<Map>) result.getResultObject();
        Map record = countList.get(0);
        return ((BigDecimal) record.get(DB_COLUMN_CNT_ADJ_VAR)).intValue();
    }

    /**
     * Close Tech PI
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean closeTechPI(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        NLZC063001PMsg pMsg = new NLZC063001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.salesDate, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.PI_COMPLETED);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, cMsg.physInvtyNum);

        NLZC063001 nlzc063001 = new NLZC063001();
        nlzc063001.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }

        if (!updateAdjAmt(cMsg, sMsg, glblCmpyCd)) {
            return false;
        }

        return true;
    }

    /**
     * Setup Workflow
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean setupWorkflow(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        NLZC061001PMsg pMsg = new NLZC061001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.salesDate, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.xxMode, CREATE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyCtrlNm, cMsg.physInvtyCtrlNm);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyDt, cMsg.physInvtyDt);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, cMsg.physInvtyNum);
        ZYPEZDItemValueSetter.setValue(pMsg.techTocCd, cMsg.techTocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.techNm, cMsg.techNm);
        ZYPEZDItemValueSetter.setValue(pMsg.xxUserId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());

        NLZC061001 nlzc061001 = new NLZC061001();
        nlzc061001.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }

        return true;
    }

    /**
     * Prepre ReCount
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     */
    public static void prepreReCount(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getReCountItem(ssmParam);

        if (!result.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        // Clear counted items
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        // Set recounting items
        sMsg.B.clear();
        sMsg.B.setValidCount(list.size());
        for (int n = 0; n < list.size(); ++n) {
            NLCL0640_BSMsg bsMsg = sMsg.B.no(n);
            Map<String, Object> rec = list.get(n);

            ZYPEZDItemValueSetter.setValue(bsMsg.techLocCd_B, (String) rec.get(DB_COLUMN_WH_CD));
            // ZYPEZDItemValueSetter.setValue(bsMsg.locNm_B,
            // (String)rec.get(DB_COLUMN_LOC_NM));
            ZYPEZDItemValueSetter.setValue(bsMsg.mdseCd_B, (String) rec.get(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(bsMsg.mdseDescShortTxt_B, (String) rec.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(bsMsg.rcvSerTakeFlg_B, (String) rec.get(DB_COLUMN_RCV_SER_TAKE_FLG));
            // ZYPEZDItemValueSetter.setValue(bsMsg.cntInpQty_B,
            // (BigDecimal)rec.get(DB_COLUMN_CNT_INP_QTY));
            ZYPEZDItemValueSetter.setValue(bsMsg.serNum_B, (String) rec.get(DB_COLUMN_SER_NUM));
            // ZYPEZDItemValueSetter.setValue(bsMsg.,
            // (String)rec.get(DB_COLUMN_));
        }

        setReCountHeader(cMsg, sMsg, glblCmpyCd, 0);

    }

    /**
     * 
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @param rowNum int
     */
    public static void setReCountHeader(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd, int rowNum) {

        cMsg.mdseCd.clear();
        cMsg.mdseDescShortTxt.clear();
        cMsg.rcvSerTakeFlg.clear();
        cMsg.cntInpQty.clear();
        cMsg.serNum.clear();

        if (rowNum < sMsg.B.getValidCount()) {
            ZYPEZDItemValueSetter.setValue(sMsg.xxRowNum, BigDecimal.valueOf(rowNum));

            NLCL0640_BSMsg bsMsg = sMsg.B.no(rowNum);
            ZYPEZDItemValueSetter.setValue(cMsg.techLocCd_SL, bsMsg.techLocCd_B);
            ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, bsMsg.mdseCd_B);
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, bsMsg.mdseDescShortTxt_B);
            ZYPEZDItemValueSetter.setValue(cMsg.rcvSerTakeFlg, bsMsg.rcvSerTakeFlg_B);
            cMsg.cntInpQty.clear();
            ZYPEZDItemValueSetter.setValue(cMsg.serNum, bsMsg.serNum_B);
        }
    }

    /**
     * Add Additional Items
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean addAdditionalItems(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmParam.put(DB_PARAM_TECH_TOC_CD, cMsg.techTocCd.getValue());
        ssmParam.put(DB_PARAM_LOC_STS_CD, LOC_STS.DC_STOCK);
        ssmParam.put(DB_PARAM_STK_STS_CD, STK_STS.GOOD);
        ssmParam.put(DB_PARAM_LOC_TP_CD, LOC_TP.WAREHOUSE);
        ssmParam.put(DB_PARAM_RCV_SER_TAKE_FLG, ZYPConstant.FLG_OFF_N);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getAdditionalItem(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> additionalItemList = (List<Map>) result.getResultObject();

        Map<String, Object> ssmParamWH = new HashMap<String, Object>();
        ssmParamWH.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParamWH.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParamWH.put(DB_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult resultWH = null;
        resultWH = NLCL0640Query.getInstance().getReCountWH(ssmParamWH);

        if (!resultWH.isCodeNormal()) {
            return true;
        }

        List<Map> reCountWHList = (List<Map>) resultWH.getResultObject();
        if (reCountWHList.size() == 0) {
            return true;
        }
        Map recordWH = reCountWHList.get(0);

        String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);

        for (int i = 0; i < additionalItemList.size(); i++) {

            Map record = additionalItemList.get(i);

            PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();

            BigDecimal physInvtyCntHdrSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_HDR_SQ);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) recordWH.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.techTocCd, cMsg.techTocCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlNm, cMsg.physInvtyCtrlNm);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyDt, cMsg.physInvtyDt);
            ZYPEZDItemValueSetter.setValue(inMsg.whCd, (String) recordWH.get(DB_COLUMN_WH_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, (String) recordWH.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.rtlSwhCd, (String) recordWH.get(DB_COLUMN_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, (String) record.get(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inMsg.sysCntRegdFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(inMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(inMsg.stkStsCd, STK_STS.GOOD);
            ZYPEZDItemValueSetter.setValue(inMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);

            EZDTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_HDR});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }

            PHYS_INVTY_CNT_DTLTMsg inDtlMsg = new PHYS_INVTY_CNT_DTLTMsg();

            BigDecimal physInvtyCntDtlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CNT_DTL_SQ);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyCntDtlPk, physInvtyCntDtlSq);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyCntHdrPk, physInvtyCntHdrSq);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyCtrlPk, (BigDecimal) recordWH.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.rtlWhCd, (String) recordWH.get(DB_COLUMN_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.rtlSwhCd, (String) recordWH.get(DB_COLUMN_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.mdseCd, (String) record.get(DB_COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(inDtlMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(inDtlMsg.physInvtyAdjStsCd, PHYS_INVTY_ADJ_STS.NONE);

            EZDTBLAccessor.insert(inDtlMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inDtlMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }

    // START 2018/12/03 T.Ogura [QC#27978,DEL]
//    /**
//     * Get Physical Inventory Status Name
//     * @param cMsg NLCL0640CMsg
//     * @param sMsg NLCL0640SMsg
//     * @param glblCmpyCd String
//     */
//    public static void getPhysicalInventoryStatusName(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
//        ssmParam.put(DB_PARAM_PHYS_INVTY_STS_CD, PHYS_INVTY_STS.OPEN);
//
//        S21SsmEZDResult result = null;
//        result = NLCL0640Query.getInstance().getPhysicalInventoryStatusName(ssmParam);
//
//        if (!result.isCodeNormal()) {
//            return;
//        }
//
//        List<Map> physicalInventoryStatusNameList = (List<Map>) result.getResultObject();
//        if (physicalInventoryStatusNameList.size() == 0) {
//            return;
//        }
//
//        Map record = physicalInventoryStatusNameList.get(0);
//        ZYPEZDItemValueSetter.setValue(cMsg.physInvtyStsDescTxt_01, (String) record.get(DB_COLUMN_PHYS_INVTY_STS_DESC_TXT));
//
//    }
    // END   2018/12/03 T.Ogura [QC#27978,DEL]

    /**
     * Sum Up Inputted Qty By Counting
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean sumUpInputtedQtyByCounting(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getCountingQty(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> countingQtyList = (List<Map>) result.getResultObject();
        if (countingQtyList.size() == 0) {
            return true;
        }

        for (int i = 0; i < countingQtyList.size(); i++) {

            Map record = countingQtyList.get(i);

            PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CNT_HDR_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

            PHYS_INVTY_CNT_HDRTMsg outMsg = (PHYS_INVTY_CNT_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_HDR});
                return false;
            }

            if (PHYS_INVTY_CNT_STS.COUNTING.equals(cMsg.physInvtyCntStsCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(outMsg.firstCntInpQty, (BigDecimal) record.get(DB_COLUMN_SUM_FIRST_CNT_INP_QTY));
                ZYPEZDItemValueSetter.setValue(outMsg.firstCntInpTs, (String) record.get(DB_COLUMN_MAX_FIRST_CNT_INP_TS));
                ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpQty, (BigDecimal) record.get(DB_COLUMN_SUM_FIRST_CNT_INP_QTY));
                ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpTs, (String) record.get(DB_COLUMN_MAX_FIRST_CNT_INP_TS));
            } else {
                ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpQty, (BigDecimal) record.get(DB_COLUMN_SUM_SCD_CNT_INP_QTY));
                ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpTs, (String) record.get(DB_COLUMN_MAX_SCD_CNT_INP_TS));
                ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpQty, (BigDecimal) record.get(DB_COLUMN_SUM_SCD_CNT_INP_QTY));
                ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpTs, (String) record.get(DB_COLUMN_MAX_SCD_CNT_INP_TS));
            }
            ZYPEZDItemValueSetter.setValue(outMsg.invtyAvalQty, (BigDecimal) record.get(DB_COLUMN_SUM_INVTY_AVAL_QTY));
            ZYPEZDItemValueSetter.setValue(outMsg.invtyRegdTs, (String) record.get(DB_COLUMN_MAX_INVTY_REGD_TS));
            ZYPEZDItemValueSetter.setValue(outMsg.adjVarQty, (BigDecimal) record.get(DB_COLUMN_SUM_ADJ_VAR_QTY));
            if (BigDecimal.ZERO.compareTo((BigDecimal) record.get(DB_COLUMN_CNT_ADJ_VAR_FLG_Y)) == 0) {
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarFlg, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(outMsg.adjVarFlg, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(outMsg.adjVarAmt, (BigDecimal) record.get(DB_COLUMN_SUM_ADJ_VAR_AMT));

            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_HDR});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }

    /**
     * update Adjustment (Gross/Net)
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean updateAdjAmt(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getPIControlsFromPINumer(ssmParam);

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

            ZYPEZDItemValueSetter.setValue(outMsg.adjGrsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(outMsg.adjNetAmt, BigDecimal.ZERO);

            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }

    /**
     * setPagination
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param fromNum int
     */
    public static void setPagination(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, int fromNum) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.ZERO);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.ZERO);
        int recCount = sMsg.A.getValidCount();
        int fromIndex = 0;
        int fetchSize = 0;
        if (recCount > 0) {
            int pageNum = (fromNum - 1) / CONST_PAGE_SIZE + 1;
            int lastPage = (recCount - 1) / CONST_PAGE_SIZE + 1;
            if (pageNum <= 0) {
                pageNum = 1;
            }
            if (pageNum > lastPage) {
                pageNum = lastPage;
            }
            fromNum = (pageNum - 1) * CONST_PAGE_SIZE + 1;
            int toNum = fromNum + CONST_PAGE_SIZE - 1;
            if (toNum > recCount) {
                toNum = recCount;
            }
            fromIndex = fromNum - 1;
            fetchSize = toNum - fromNum + 1;
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, BigDecimal.valueOf(fromNum));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum, BigDecimal.valueOf(toNum));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum, BigDecimal.valueOf(recCount));
        }

        // EZDMsg.copy(sMsg, null, cMsg, null);
        cMsg.A.setValidCount(fetchSize);
        for (int n = 0; n < fetchSize; ++n) {
            EZDMsg.copy(sMsg.A.no(fromIndex + n), null, cMsg.A.no(n), null);
        }
    }

    /**
     * lastPage
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     */
    public static void lastPage(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg) {
        setPagination(cMsg, sMsg, 10000);
    }

    // START 2018/12/11 T.Ogura [QC#28755,ADD]
    /**
     * Update ReCounting Not Input Counting Detail
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    public static boolean updateReCountNotInputCntDtl(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        ssmParam.put(DB_PARAM_ADJ_VAR_FLG, ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult result = null;
        result = NLCL0640Query.getInstance().getReCountNotInputCntDtl(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> countingDetailList = (List<Map>) result.getResultObject();
        if (countingDetailList.size() == 0) {
            return true;
        }

        for (int i = 0; i < countingDetailList.size(); i++) {

            Map record = countingDetailList.get(i);

            PHYS_INVTY_CNT_DTLTMsg inMsg = new PHYS_INVTY_CNT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntDtlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CNT_DTL_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

            PHYS_INVTY_CNT_DTLTMsg outMsg = (PHYS_INVTY_CNT_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL});
                return false;
            }

            String systemTime = ZYPDateUtil.getCurrentSystemTime(API_PARAM_TIMESTAMP_FORMAT);
            ZYPEZDItemValueSetter.setValue(outMsg.cntInpQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(outMsg.scdCntInpTs, systemTime);
            ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(outMsg.ltstCntInpTs, systemTime);
            ZYPEZDItemValueSetter.setValue(outMsg.adjVarFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(outMsg.adjVarQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(outMsg.adjVarAmt, BigDecimal.ZERO);

            EZDTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CNT_DTL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }
    // END   2018/12/11 T.Ogura [QC#28755,ADD]

    // QC#50029 Add method.
    /**
     * @param cMsg NLCL0640CMsg
     * @param sMsg NLCL0640SMsg
     * @param glblCmpyCd String
     */
    public static void updateAdjustment(NLCL0640CMsg cMsg, NLCL0640SMsg sMsg, String glblCmpyCd) {
        if (updateAdjustmentOrder(cMsg.physInvtyNum.getValue(), glblCmpyCd, cMsg)) {
            closeTechPiVariance(glblCmpyCd, cMsg.physInvtyNum.getValue(), cMsg);
        }
    }

    // QC#50029 Add method.
    private static boolean updateAdjustmentOrder(String physInvtyNum, String glblCmpyCd, NLCL0640CMsg cMsg) {

        // Get PhysInfo
        List<Map<String, Object>> resultList = getAdjustmentPiCount(physInvtyNum, glblCmpyCd);

        // Init adjustmentOrder.
        ArrayList<NLZC004001PMsg> pMsgList = new ArrayList<NLZC004001PMsg>();
        List<PHYS_INVTY_ADJTMsg> adjMsgList = new ArrayList<PHYS_INVTY_ADJTMsg>();
        HashMap<Integer, BigDecimal> relnMap = new HashMap<Integer, BigDecimal>();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String preRtlWhCd = "";
        String preWhCd = "";
        String preMdseCd = "";
        String preStkStsCd = "";
        BigDecimal preAdjVarQty = BigDecimal.ZERO;

        for (Map<String, Object> record : resultList) {

            if (pMsgList.isEmpty()) {
                // Set Header Param
                pMsgList.add(createHeaderParam(slsDt, record, glblCmpyCd));
            }

            // Set Detail Param
            NLZC004001PMsg lastMsg = pMsgList.get(pMsgList.size() - 1);
            if (ZYPConstant.FLG_ON_Y.equals(record.get("RCV_SER_TAKE_FLG"))//
                    && preWhCd.equals(record.get("WH_CD")) //
                    && preMdseCd.equals(record.get("MDSE_CD")) //
                    && preStkStsCd.equals(record.get("STK_STS_CD")) //
                    && preAdjVarQty.signum() == ((BigDecimal) record.get("ADJ_VAR_QTY")).signum() //
                    && lastMsg.serialInfoList.getValidCount() < lastMsg.serialInfoList.length()) {
                // AddSerial
                addSerial(lastMsg, record);
            } else {
                pMsgList.add(createDetailParam(slsDt, record, glblCmpyCd));
                relnMap.put(pMsgList.size() - 1, (BigDecimal) record.get("PHYS_INVTY_CNT_HDR_PK"));
            }

            // Set Adjustment
            adjMsgList.add(createPIAdjustMsg(record, glblCmpyCd));

            preRtlWhCd = (String) record.get("RTL_WH_CD");
            preWhCd = (String) record.get("WH_CD");
            preMdseCd = (String) record.get("MDSE_CD");
            preStkStsCd = (String) record.get("STK_STS_CD");
            preAdjVarQty = (BigDecimal) record.get("ADJ_VAR_QTY");

        }

        // Run API
        NLZC004001 api = new NLZC004001();
        api.execute(pMsgList, ONBATCH_TYPE.ONLINE);

        // Check API Result
        boolean isErrorForAPI = false;
        for (NLZC004001PMsg pMsg : pMsgList) {
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<String> msgIdList = S21ApiUtil.getXxMsgIdList(pMsg);
                for (String msgId : msgIdList) {
                    // cmt:Error/NG, Warning:OK
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        isErrorForAPI = true;
                    }
                }
            }
        }

        if (isErrorForAPI) {
            // Error.
            return false;
        }

        // Update physInvtyCntHdr. Set invtyOrdNum.
        for (Map.Entry<Integer, BigDecimal> map : relnMap.entrySet()) {
            NLZC004001PMsg pMsg = pMsgList.get(map.getKey());

            PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, map.getValue());
            PHYS_INVTY_CNT_HDRTMsg tMsg = (PHYS_INVTY_CNT_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (tMsg == null) {
                cMsg.setMessageInfo(NLCL0640Constant.NLZM2454E);
                return false;
            }
            ZYPEZDItemValueSetter.setValue(tMsg.invtyOrdNum, pMsg.invtyOrdNum);
            S21ApiTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NLCL0640Constant.NLZM2454E);
                return false;
            }
        }

        // Insert PhysInvtyAdj table.
        if (!piAdjustInsert(adjMsgList, pMsgList, cMsg)) {
            // Insert Error.
            return false;
        }

        // Summary Update.
        if (!summaryPICountHeader(physInvtyNum, pMsgList, glblCmpyCd , cMsg)) {
            // Error.
            return false;
        }
        if (!summaryPIControl(physInvtyNum, glblCmpyCd , cMsg)) {
            // Error.
            return false;
        }

        return true;
    }

    private static List<Map<String, Object>> getAdjustmentPiCount(String physInvtyNum, String glblCmpyCd) {

        Map<String, Object> bindParam = new HashMap<String, Object>();
        bindParam.put("GLBL_CMPY_CD", glblCmpyCd);
        bindParam.put("PHYS_INVTY_NUM", physInvtyNum);
        bindParam.put("DC_STOCK", LOC_STS.DC_STOCK); // ADJ_VAR_FLG
        bindParam.put("FLG_ON_Y", ZYPConstant.FLG_ON_Y); // LOC_STS_CD

        return (List<Map<String, Object>>) NLCL0640Query.getInstance().adjustmentPiCount(bindParam).getResultObject();
    }

    // -----------------------------------------------------------
    // Create NLZC0040 Parameter
    // -----------------------------------------------------------
    /**
     * Header Param
     * @param tokenObj NLZC061001TokenObject
     * @param map Map<String, Object>
     * @return NLZC004001PMsg
     */
    private static NLZC004001PMsg createHeaderParam(String slsDt, Map<String, Object> record, String glblCmpyCd) {
        NLZC004001PMsg pMsg = new NLZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
        ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, NLZC004001Constant.DT_TP_HDR);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) record.get("RTL_WH_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(pMsg.adjTrxTpCd, ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT);
        String physInvtyAdjNm = (String) record.get("PHYS_INVTY_ADJ_NM");
        if (ZYPCommonFunc.hasValue(physInvtyAdjNm)) {
            if (physInvtyAdjNm.length() > NLCL0640Constant.MAX_NM_LENGTH) {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm.substring(0, NLCL0640Constant.MAX_NM_LENGTH));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm);
            }
        }

        return pMsg;
    }

    /**
     * Detail Param
     * @param tokenObj NLZC061001TokenObject
     * @param map Map<String, Object>
     * @return NLZC004001PMsg
     */
    private static NLZC004001PMsg createDetailParam(String slsDt, Map<String, Object> record, String glblCmpyCd) {
        NLZC004001PMsg pMsg = new NLZC004001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcTpCd, NLZC004001Constant.PROC_TP_CRAT_CLO);
        ZYPEZDItemValueSetter.setValue(pMsg.xxDtTpCd, NLZC004001Constant.DT_TP_DTL);
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        BigDecimal adjVarQty = (BigDecimal) record.get("ADJ_VAR_QTY");
        if (adjVarQty.compareTo(BigDecimal.ZERO) == 1) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_IN);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, NLZC004001Constant.RQST_STOCK_OUT);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.invtyOrdTpCd, INVTY_ORD_TP.ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd, (String) record.get("WH_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.trxSrcTpCd, TRX_SRC_TP.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(pMsg.adjTrxTpCd, ADJ_TRX_TP.PHYSICAL_INVENTORY_ADJUSTMENT);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, (String) record.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, (String) record.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.invtyLocCd_D1, (String) record.get("WH_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.locStsCd_D1, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, adjVarQty);
        String physInvtyAdjNm = (String) record.get("PHYS_INVTY_ADJ_NM");
        if (ZYPCommonFunc.hasValue(physInvtyAdjNm)) {
            if (physInvtyAdjNm.length() > NLCL0640Constant.MAX_NM_LENGTH) {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm.substring(0, NLCL0640Constant.MAX_NM_LENGTH));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.firstInvtyOrdCmntTxt, physInvtyAdjNm);
            }
        }

        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, (BigDecimal) record.get("SVC_CONFIG_MSTR_PK"));
        if (ZYPConstant.FLG_ON_Y.equals(record.get("RCV_SER_TAKE_FLG"))) {
            ZYPEZDItemValueSetter.setValue(pMsg.serialInfoList.no(pMsg.serialInfoList.getValidCount()).serNum, (String) record.get("SER_NUM"));
            pMsg.serialInfoList.setValidCount(pMsg.serialInfoList.getValidCount() + 1);
        }
        return pMsg;
    }

    /**
     * Add Serial
     * @param pMsg NLZC004001PMsg
     * @param tokenObj NLZC061001TokenObject
     * @param map Map<String, Object>
     */
    private static void addSerial(NLZC004001PMsg pMsg, Map<String, Object> map) {
        BigDecimal adjVarQty = (BigDecimal) map.get("ADJ_VAR_QTY");
        ZYPEZDItemValueSetter.setValue(pMsg.ordQty, pMsg.ordQty.getValue().add(adjVarQty));
        ZYPEZDItemValueSetter.setValue(pMsg.serialInfoList.no(pMsg.serialInfoList.getValidCount()).serNum, (String) map.get("SER_NUM"));
        pMsg.serialInfoList.setValidCount(pMsg.serialInfoList.getValidCount() + 1);
    }

    private static PHYS_INVTY_ADJTMsg createPIAdjustMsg(Map<String, Object> map, String glblCmpyCd) {
        PHYS_INVTY_ADJTMsg tMsg = new PHYS_INVTY_ADJTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCtrlPk, (BigDecimal) map.get("PHYS_INVTY_CTRL_PK"));
        ZYPEZDItemValueSetter.setValue(tMsg.whCd, (String) map.get("WH_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, (String) map.get("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, (String) map.get("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(tMsg.adjVarQty, (BigDecimal) map.get("ADJ_VAR_QTY"));
        if (tMsg.adjVarQty.getValue().signum() == 1) {
            ZYPEZDItemValueSetter.setValue(tMsg.adjVarSgnCd, "+");
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.adjVarSgnCd, "-");
        }
        if (ZYPConstant.FLG_ON_Y.equals(map.get("RCV_SER_TAKE_FLG"))) {
            ZYPEZDItemValueSetter.setValue(tMsg.serNum, (String) map.get("SER_NUM"));
        }
        return tMsg;
    }

    private static boolean piAdjustInsert(List<PHYS_INVTY_ADJTMsg> adjMsgList, List<NLZC004001PMsg> pMsgList , NLCL0640CMsg cMsg) {

        boolean result = true;

        for (PHYS_INVTY_ADJTMsg inMsg : adjMsgList) {
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyAdjPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_ADJ_SQ));
            for (int i = 1; i < pMsgList.size(); i++) {
                NLZC004001PMsg pMsg = pMsgList.get(i);
                if (pMsg.mdseCd.getValue().equals(inMsg.mdseCd.getValue()) && pMsg.invtyLocCd_D1.getValue().equals(inMsg.whCd.getValue()) && pMsg.stkStsCd.getValue().equals(inMsg.stkStsCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(inMsg.invtyOrdNum, pMsg.invtyOrdNum);
                    ZYPEZDItemValueSetter.setValue(inMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
                    ZYPEZDItemValueSetter.setValue(inMsg.physInvtyAdjNm, pMsg.firstInvtyOrdCmntTxt);
                }
            }
            S21ApiTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                cMsg.setMessageInfo(NLCL0640Constant.NLZM2453E);
                result = false;
            }
        }

        return result;
    }

    private static boolean summaryPICountHeader(String physInvtyNum, List<NLZC004001PMsg> pMsgList, String glblCmpyCd, NLCL0640CMsg cMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("GLBL_CMPY_CD", glblCmpyCd);
        paramMap.put("PHYS_INVTY_NUM", physInvtyNum);
        paramMap.put("PHYS_INVTY_ADJ_STS_CD", PHYS_INVTY_ADJ_STS.SKIP_ADJUSTMENT_DUE_TO_ERROR);
        List<Map<String, Object>> result = (List<Map<String, Object>>) NLCL0640Query.getInstance().summaryPICountHeader(paramMap).getResultObject();
        if (result == null) {
            cMsg.setMessageInfo(NLCL0640Constant.NLZM2454E);
            return false;
        }

        for (Map<String, Object> record : result) {
            PHYS_INVTY_CNT_HDRTMsg inMsg = new PHYS_INVTY_CNT_HDRTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntHdrPk, (BigDecimal) record.get("PHYS_INVTY_CNT_HDR_PK"));
            PHYS_INVTY_CNT_HDRTMsg outMsg = (PHYS_INVTY_CNT_HDRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg == null) {
                cMsg.setMessageInfo(NLCL0640Constant.NLZM2454E);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(outMsg.adjVarQty, (BigDecimal) record.get("SUB_TOT_ADJ_VAR_QTY"));
            ZYPEZDItemValueSetter.setValue(outMsg.adjVarAmt, (BigDecimal) record.get("SUB_TOT_ADJ_VAR_AMT"));

            for (NLZC004001PMsg pMsg : pMsgList) {
                if (NLZC004001Constant.DT_TP_HDR.equals(pMsg.xxDtTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(outMsg.invtyOrdNum, pMsg.invtyOrdNum);
                    break;
                }
            }
            S21ApiTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NLCL0640Constant.NLZM2454E);
                return false;
            }
        }

        return true;
    }

    private static boolean summaryPIControl(String physInvtyNum, String glblCmpyCd, NLCL0640CMsg cMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("GLBL_CMPY_CD", glblCmpyCd);
        paramMap.put("PHYS_INVTY_NUM", physInvtyNum);
        paramMap.put("PHYS_INVTY_ADJ_STS_CD", PHYS_INVTY_ADJ_STS.SKIP_ADJUSTMENT_DUE_TO_ERROR);
        List<Map<String, Object>> result = (List<Map<String, Object>>) NLCL0640Query.getInstance().summaryPIControl(paramMap).getResultObject();
        if (result == null) {
            cMsg.setMessageInfo(NLCL0640Constant.NLCM0163E);
            return false;
        }

        for (Map<String, Object> record : result) {
            PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) record.get("PHYS_INVTY_CTRL_PK"));
            PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
            if (outMsg == null) {
                cMsg.setMessageInfo(NLCL0640Constant.NLCM0163E);
                return false;
            }

            ZYPEZDItemValueSetter.setValue(outMsg.adjGrsAmt, (BigDecimal) record.get("TOT_ADJ_VAR_GRS_AMT"));
            ZYPEZDItemValueSetter.setValue(outMsg.adjNetAmt, (BigDecimal) record.get("TOT_ADJ_VAR_NET_AMT"));
            S21ApiTBLAccessor.update(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NLCL0640Constant.NLCM0163E);
                return false;
            }
        }

        return true;
    }

    private static boolean closeTechPiVariance(String glblCmpyCd, String physInvtyNum, NLCL0640CMsg cMsg) {
        NLZC063001PMsg pMsg = new NLZC063001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.salesDate, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.PI_VARIANCE);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, physInvtyNum);

        NLZC063001 api = new NLZC063001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            cMsg.setMessageInfo(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }

        return true;
    }
}
