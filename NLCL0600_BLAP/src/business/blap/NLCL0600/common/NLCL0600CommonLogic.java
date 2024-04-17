/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0600.common;

import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_COLUMN_EFF_THRU_DT;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_COLUMN_INVTY_LOC_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_COLUMN_LOC_TP_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_COLUMN_PHYS_INVTY_CTRL_PK;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_COLUMN_PHYS_INVTY_NUM;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_COLUMN_RTL_SWH_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_COLUMN_RTL_SWH_NM;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_CMSG;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_INVTY_LOC_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_LOC_TP_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_MAX_ROWNUM;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_PHYS_INVTY_CTRL_NM;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_PHYS_INVTY_DT;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_PHYS_INVTY_NUM;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_ROWNUM;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_RTL_SWH_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_SALES_DATE;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_VALUE_ROWNUM_1;
import static business.blap.NLCL0600.constant.NLCL0600Constant.DB_PARAM_WH_OWNR_CD;
import static business.blap.NLCL0600.constant.NLCL0600Constant.MSG_VALUE_PHYS_INVTY_CTRL;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NLCM0176E;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NLCM0177E;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NLZM2278E;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NLZM2279E;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NMAM0038I;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NMAM0176E;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NMAM0177E;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NMAM8181W;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NPAM0002E;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NPAM0006E;
import static business.blap.NLCL0600.constant.NLCL0600Constant.NLCM0019E;
import static business.blap.NLCL0600.constant.NLCL0600Constant.VAR_CHAR_CONST_VAL_DELIM;
import static business.blap.NLCL0600.constant.NLCL0600Constant.ZZM8100I;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0600.NLCL0600CMsg;
import business.blap.NLCL0600.NLCL0600Query;
import business.blap.NLCL0600.NLCL0600SMsg;
import business.db.PHYS_INVTY_CTRLTMsg;
import business.db.PHYS_INVTY_STSTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS      Makoto Okigami      Create          N/A
 * 10/05/2016   CITS      Y.Fujii             Update          QC#14417
 * 
 *</pre>
 */
public class NLCL0600CommonLogic {

    /**
     * Search
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     */
    public static void search(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_LOC_TP_CD,  cMsg.varCharConstVal_01.getValue().split(VAR_CHAR_CONST_VAL_DELIM));
        ssmParam.put(DB_PARAM_MAX_ROWNUM, sMsg.A.length() + 1);

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().search(ssmParam, sMsg);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NMAM0038I);
            return;
        }

        // Max Recode Over
        if (result.getQueryResultCount() > sMsg.A.length()) {
            cMsg.setMessageInfo(NMAM8181W, new String[] {String.format("%d", sMsg.A.length()), String.format("%d", sMsg.A.length())});
        }

        // Set Header Item
        if (sMsg.A.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCd, sMsg.A.no(0).rtlWhCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm, sMsg.A.no(0).rtlWhNm_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.physInvtyDt, sMsg.A.no(0).physInvtyDt_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.physInvtyCtrlNm, sMsg.A.no(0).physInvtyCtrlNm_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.physInvtyCtrlDescNm, sMsg.A.no(0).physInvtyCtrlDescNm_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.locTpCd, sMsg.A.no(0).locTpCd_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.xxRqstTs, sMsg.A.no(0).xxRqstTs_A1);
            ZYPEZDItemValueSetter.setValue(sMsg.xxRqstTz, sMsg.A.no(0).xxRqstTz_A1);
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
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, sMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, sMsg.rtlWhNm);
        ZYPEZDItemValueSetter.setValue(cMsg.physInvtyDt, sMsg.physInvtyDt);
        ZYPEZDItemValueSetter.setValue(cMsg.physInvtyCtrlNm, sMsg.physInvtyCtrlNm);
        ZYPEZDItemValueSetter.setValue(cMsg.physInvtyCtrlDescNm, sMsg.physInvtyCtrlDescNm);
        ZYPEZDItemValueSetter.setValue(cMsg.locTpCd, sMsg.locTpCd);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTs, sMsg.xxRqstTs);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTz, sMsg.xxRqstTz);

    }

    /**
     * Add Specific SubWH
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     */
    public static void addSpecificSubWH(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            if (!getRtlWh(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            if (!getLocation(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        } else {
            if (!getRtlSwh(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        }

        int nextRowNum = cMsg.A.getValidCount();
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(nextRowNum).rtlSwhCd_A1, cMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(nextRowNum).rtlSwhNm_A1, cMsg.rtlSwhNm);
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(nextRowNum).invtyLocCd_A1, cMsg.invtyLocCd);
        cMsg.A.setValidCount(nextRowNum + 1);

    }

    /**
     * Add All SubWH
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     */
    public static void addAllSubWH(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        if (!getRtlWh(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().getRtlSwhFromRtlWhCd(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NPAM0002E);
            return;
        }

        List<Map> rtlSwhList = (List<Map>) result.getResultObject();
        if (rtlSwhList.size() == 0) {
            cMsg.setMessageInfo(NPAM0002E);
            return;
        }

        ZYPTableUtil.clear(cMsg.A);

        int i = 0;
        for (i = 0; i < rtlSwhList.size(); i++) {
            Map record = rtlSwhList.get(i);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlSwhCd_A1, (String) record.get(DB_COLUMN_RTL_SWH_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).rtlSwhNm_A1, (String) record.get(DB_COLUMN_RTL_SWH_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).invtyLocCd_A1, (String) record.get(DB_COLUMN_INVTY_LOC_CD));
        }
        cMsg.A.setValidCount(i);

    }

    /**
     * Submit
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     */
    public static void submit(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(cMsg.physInvtyNum)) {
            if (!getRtlWh(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            if (!getLocation(cMsg, sMsg, glblCmpyCd, i)) {
                return;
            }

        }

        if (!checkPhysicalInventoryControlName(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        if (!checkRtlWhEffectiveDate(cMsg, sMsg, glblCmpyCd)) {
            return;
        }

        if (!checkPhysicalInventoryControl(cMsg, sMsg, glblCmpyCd)) {
            return;
        }
 
        if (ZYPCommonFunc.hasValue(cMsg.physInvtyNum)) {
            if (!deletePIControl(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
        }

        DecimalFormat df = new DecimalFormat("#");
        df.setMinimumIntegerDigits(15);
        df.setMaximumIntegerDigits(15);
        String physInvtyNum = cMsg.physInvtyNum.getValue();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            BigDecimal physInvtyCtrlSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PHYS_INVTY_CTRL_SQ);

            if (!ZYPCommonFunc.hasValue(cMsg.physInvtyNum) && i == 0) {
                physInvtyNum = df.format(physInvtyCtrlSq);
            }

            PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, physInvtyCtrlSq);
            ZYPEZDItemValueSetter.setValue(inMsg.whCd, cMsg.A.no(i).invtyLocCd_A1);
            ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, cMsg.rtlWhCd);
            ZYPEZDItemValueSetter.setValue(inMsg.rtlSwhCd, cMsg.A.no(i).rtlSwhCd_A1);
            ZYPEZDItemValueSetter.setValue(inMsg.locTpCd, cMsg.locTpCd);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlNm, cMsg.physInvtyCtrlNm);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyDt, cMsg.physInvtyDt);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyStsCd, PHYS_INVTY_STS.SCHEDULED);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyStsNm, cMsg.physInvtyStsDescTxt_03);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.SCHEDULED);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyNum, physInvtyNum);
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlDescNm, cMsg.physInvtyCtrlDescNm);
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
            EZDTBLAccessor.insert(inMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0176E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return;
            }
        }
        if (!ZYPCommonFunc.hasValue(cMsg.physInvtyNum)) {
            ZYPEZDItemValueSetter.setValue(cMsg.physInvtyNum, physInvtyNum);
        }

        cMsg.setMessageInfo(ZZM8100I);
    }

    /**
     * Get Physical Inventory Status Name
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     */
    public static void getPhysicalInventoryStatusName(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        PHYS_INVTY_STSTMsg tMsg = (PHYS_INVTY_STSTMsg) ZYPCodeDataUtil.findByCode(PHYS_INVTY_STS.class, glblCmpyCd, PHYS_INVTY_STS.SCHEDULED);
        ZYPEZDItemValueSetter.setValue(cMsg.physInvtyStsDescTxt_03, tMsg.physInvtyStsDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.physInvtyStsDescTxt_03, tMsg.physInvtyStsDescTxt);

    }

    /**
     * Search WH Info
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     */
    public static void searchWHInfo(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        getRtlWh(cMsg, sMsg, glblCmpyCd);

    }

    /**
     * Search Retail Sub WH Info
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     */
    public static void searchRetailSubWHInfo(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            if (!getRtlWh(cMsg, sMsg, glblCmpyCd)) {
                return;
            }
            getLocation(cMsg, sMsg, glblCmpyCd);
        } else {
            getRtlSwh(cMsg, sMsg, glblCmpyCd);
        }

    }

    /**
     * Get Rtl WH
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean getRtlWh(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmParam.put(DB_PARAM_LOC_TP_CD,  cMsg.varCharConstVal_01.getValue().split(VAR_CHAR_CONST_VAL_DELIM));
        ssmParam.put(DB_PARAM_WH_OWNR_CD,  cMsg.varCharConstVal_02.getValue().split(VAR_CHAR_CONST_VAL_DELIM));

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().getRtlWh(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            return false;
        }

        List<Map> rtlWhList = (List<Map>) result.getResultObject();
        if (rtlWhList.size() == 0) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlWhCd.getValue()});
            return false;
        }

        Map record = rtlWhList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, (String) record.get(DB_COLUMN_RTL_WH_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.locTpCd, (String) record.get(DB_COLUMN_LOC_TP_CD));

        return true;
    }

    /**
     * Get Rtl SWH
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean getRtlSwh(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_SWH_CD, cMsg.rtlSwhCd.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmParam.put(DB_PARAM_ROWNUM, DB_PARAM_VALUE_ROWNUM_1);

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().getRtlSwh(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.rtlSwhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlSwhCd.getValue()});
            return false;
        }

        List<Map> rtlSwhList = (List<Map>) result.getResultObject();
        if (rtlSwhList.size() == 0) {
            cMsg.rtlSwhCd.setErrorInfo(1, NLZM2278E, new String[] {cMsg.rtlSwhCd.getValue()});
            return false;
        }

        Map record = rtlSwhList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, (String) record.get(DB_COLUMN_RTL_SWH_NM));

        return true;
    }

    /**
     * Get Location
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean getLocation(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_INVTY_LOC_CD, ZYPCommonFunc.concatString(cMsg.rtlWhCd.getValue(), "", cMsg.rtlSwhCd.getValue()));
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().getLocation(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2279E, new String[] {cMsg.rtlWhCd.getValue(), cMsg.rtlSwhCd.getValue()});
            cMsg.rtlSwhCd.setErrorInfo(1, NLZM2279E, new String[] {cMsg.rtlWhCd.getValue(), cMsg.rtlSwhCd.getValue()});
            return false;
        }

        List<Map> locationList = (List<Map>) result.getResultObject();
        if (locationList.size() == 0) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2279E, new String[] {cMsg.rtlWhCd.getValue(), cMsg.rtlSwhCd.getValue()});
            cMsg.rtlSwhCd.setErrorInfo(1, NLZM2279E, new String[] {cMsg.rtlWhCd.getValue(), cMsg.rtlSwhCd.getValue()});
            return false;
        }

        Map record = locationList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, (String) record.get(DB_COLUMN_RTL_SWH_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.invtyLocCd, (String) record.get(DB_COLUMN_INVTY_LOC_CD));

        return true;
    }

    /**
     * Get Location
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     * @param rowNumber int
     * @return false for error
     */
    private static boolean getLocation(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd, int rowNumber) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_INVTY_LOC_CD, ZYPCommonFunc.concatString(cMsg.rtlWhCd.getValue(),
                                                                       "",
                                                                       cMsg.A.no(rowNumber).rtlSwhCd_A1.getValue()));
        ssmParam.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().getLocation(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2279E, new String[] {cMsg.rtlWhCd.getValue(), cMsg.A.no(rowNumber).rtlSwhCd_A1.getValue()});
            return false;
        }

        List<Map> locationList = (List<Map>) result.getResultObject();
        if (locationList.size() == 0) {
            cMsg.rtlWhCd.setErrorInfo(1, NLZM2279E, new String[] {cMsg.rtlWhCd.getValue(), cMsg.A.no(rowNumber).rtlSwhCd_A1.getValue()});
            return false;
        }

        Map record = locationList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(rowNumber).rtlSwhNm_A1, (String) record.get(DB_COLUMN_RTL_SWH_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(rowNumber).invtyLocCd_A1, (String) record.get(DB_COLUMN_INVTY_LOC_CD));

        return true;
    }

    /**
     * Check Physical Inventory Control Name
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean checkPhysicalInventoryControlName(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_CTRL_NM, cMsg.physInvtyCtrlNm.getValue());
        ssmParam.put(DB_PARAM_ROWNUM, DB_PARAM_VALUE_ROWNUM_1);

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().getPhysicalInventoryControlFromName(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> piList = (List<Map>) result.getResultObject();
        if (piList.size() == 0) {
            return true;
        }

        Map record = piList.get(0);
        if (ZYPCommonFunc.hasValue(cMsg.physInvtyNum)) {
            if (!cMsg.physInvtyNum.getValue().equals((String) record.get(DB_COLUMN_PHYS_INVTY_NUM))) {
                cMsg.physInvtyCtrlNm.setErrorInfo(1, NLCM0176E, new String[] {cMsg.physInvtyCtrlNm.getValue()});
                return false;
            }
        } else {
            cMsg.physInvtyCtrlNm.setErrorInfo(1, NLCM0176E, new String[] {cMsg.physInvtyCtrlNm.getValue()});
            return false;
        }

        return true;
    }

    /**
     * Check Rtl WH Effective Date
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean checkRtlWhEffectiveDate(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        ssmParam.put(DB_PARAM_PHYS_INVTY_DT, cMsg.physInvtyDt.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().checkRtlWhEffectiveDate(ssmParam);

        if (!result.isCodeNormal()) {
            return true;
        }

        List<Map> rtlWhList = (List<Map>) result.getResultObject();
        if (rtlWhList.size() >= 1) {
            Map record = rtlWhList.get(0);
            cMsg.physInvtyDt.setErrorInfo(1, NLCM0177E, new String[] {ZYPDateUtil.formatEzd8ToDisp((String) record.get(DB_COLUMN_EFF_THRU_DT)), cMsg.rtlWhCd.getValue()});
            return false;
        }

        return true;
    }

    /**
     * Delete PI Control
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean deletePIControl(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().getPhysicalInventoryControl(ssmParam);

        if (!result.isCodeNormal()) {
            cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
            return false;
        }

        List<Map> piList = (List<Map>) result.getResultObject();
        if (piList.size() == 0) {
            cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
            return false;
        }

        for (int i = 0; i < piList.size(); i++) {

            Map record = piList.get(i);

            PHYS_INVTY_CTRLTMsg inMsg = new PHYS_INVTY_CTRLTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.physInvtyCtrlPk, (BigDecimal) record.get(DB_COLUMN_PHYS_INVTY_CTRL_PK));
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);

            PHYS_INVTY_CTRLTMsg outMsg = (PHYS_INVTY_CTRLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(inMsg);

            if (outMsg == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                return false;
            }

            if (!ZYPDateUtil.isSameTimeStamp(cMsg.xxRqstTs.getValue(), cMsg.xxRqstTz.getValue(),
                                             outMsg.ezUpTime.getValue(), outMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(NPAM0006E);
                return false;
            }

            EZDTBLAccessor.logicalRemove(outMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAM0177E, new String[] {MSG_VALUE_PHYS_INVTY_CTRL});
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                return false;
            }
        }

        return true;
    }

    /**
     * Check Physical Inventory Control
     * @param cMsg NLCL0600CMsg
     * @param sMsg NLCL0600SMsg
     * @param glblCmpyCd String
     * @return false for error
     */
    private static boolean checkPhysicalInventoryControl(NLCL0600CMsg cMsg, NLCL0600SMsg sMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
        ssmParam.put(DB_PARAM_PHYS_INVTY_DT, cMsg.physInvtyDt.getValue());
        ssmParam.put(DB_PARAM_PHYS_INVTY_NUM, cMsg.physInvtyNum.getValue());
        List<String> rtlSwhCdList = new ArrayList<String>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            rtlSwhCdList.add(cMsg.A.no(i).rtlSwhCd_A1.getValue());
        }
        ssmParam.put(DB_PARAM_RTL_SWH_CD, rtlSwhCdList);

        S21SsmEZDResult result = null;
        result = NLCL0600Query.getInstance().checkPhysicalInventoryControl(ssmParam);

        if (result.isCodeNotFound()) {
            return true;
        }

        List<Map> rtlSwhList = (List<Map>) result.getResultObject();
        if (rtlSwhList.size() >= 1) {
            for (int i = 0; i < rtlSwhList.size(); i++ ) {
                String rtlSwhCd = (String) rtlSwhList.get(i).get(DB_COLUMN_RTL_SWH_CD);
                for (int j = 0; j < cMsg.A.getValidCount() ; j++) {
                    if (cMsg.A.no(j).rtlSwhCd_A1.getValue().equals(rtlSwhCd)) {
                        cMsg.A.no(j).xxChkBox_A1.setErrorInfo(1, NLCM0019E);
                        break;
                    }
                }
            }
            return false;
        }

        return true;
    }

}
