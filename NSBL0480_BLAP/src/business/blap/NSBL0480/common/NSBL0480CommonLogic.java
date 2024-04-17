/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0480.common;

import static business.blap.NSBL0480.constant.NSBL0480Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSBL0480.NSBL0480CMsg;
import business.blap.NSBL0480.NSBL0480SMsg;
import business.blap.NSBL0480.NSBL0480_ACMsg;
import business.blap.NSBL0480.NSBL0480_ASMsg;
import business.blap.NSBL0480.NSBL0480_BCMsg;
import business.blap.NSBL0480.NSBL0480_BSMsg;
import business.blap.NSBL0480.NSBL0480Query;
import business.db.SVC_ACCS_PMIT_TECH_RELNTMsg;
import business.db.SVC_PMIT_LVL_TPTMsg;
import business.db.SVC_PMIT_LVL_TPTMsgArray;

/**
 *<pre>
 * Access Permits Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         J.Kim           Create          N/A
 * 2016/06/06   Hitachi         J.Kim           Update          QC#9486
 * 2016/12/15   Hitachi         K.Kojima        Update          QC#15653
 * 2017/02/01   Hitachi         K.Kitachi       Update          QC#16629
 *</pre>
 */
public class NSBL0480CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     */
    public static void clearMsg(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);
    }

    /**
     * getSearchResourceData
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     */
    public static void getSearchResourceData(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSBL0480Query.getInstance().getSearchResourceData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 5000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_A, BigDecimal.ZERO);
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9005W);
        }
    }

    /**
     * getSearchAccessPermitsData
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     */
    public static void getSearchAccessPermitsData(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSBL0480Query.getInstance().getSearchAccessPermitsData(cMsg, sMsg, sMsg.B.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 5000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxRadioBtn_B, BigDecimal.ZERO);
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9005W);
        }
    }

    /**
     * isErrorSubmitCondition
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     * @return boolean
     */
    public static boolean isErrorSubmitCondition(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        sMsg.xxErrNum.clear();
        if (!isErrorOverlapping(cMsg, sMsg)) {
            return false;
        }

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {

            if (!checkEffFromDtResourceType(cMsg)) {
                return false;
            }

        } else {

            if (!checkEffFromDtAccessPermits(cMsg)) {
                return false;
            }
        }

        return true;
    }

    /**
     * isErrorOverlapping
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     * @return boolean
     */
    public static boolean isErrorOverlapping(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            for (int row = 0; row < sMsg.A.getValidCount(); row++) {
                // START 2017/02/01 K.Kitachi [QC#16629, MOD]
                String svcAccsPmitNum = sMsg.A.no(row).svcAccsPmitNum_A.getValue();
                String svcPmitLvlTpCd = sMsg.A.no(row).svcPmitLvlTpCd_A.getValue();
                String svcPmitLvlValTxt = sMsg.A.no(row).xxScrItem30Txt_A.getValue();
                String effFromDt = sMsg.A.no(row).effFromDt_A.getValue();
                String effThruDt = sMsg.A.no(row).effThruDt_A.getValue();
                for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                    String checkNum = sMsg.A.no(index).svcAccsPmitNum_A.getValue();
                    String checkLvlTp = sMsg.A.no(index).svcPmitLvlTpCd_A.getValue();
                    String checkLvlVal = sMsg.A.no(index).xxScrItem30Txt_A.getValue();
                    String checkFromDt = sMsg.A.no(index).effFromDt_A.getValue();
                    String checkThruDt = sMsg.A.no(index).effThruDt_A.getValue();
                    if ((row != index) && svcAccsPmitNum.equals(checkNum) && svcPmitLvlTpCd.equals(checkLvlTp) && svcPmitLvlValTxt.equals(checkLvlVal) && isDtPerOvlp(effFromDt, effThruDt, checkFromDt, checkThruDt)) {
                        sMsg.xxErrNum.setValue(row);
                        sMsg.A.no(row).svcAccsPmitNum_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(row).svcPmitLvlTpCd_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(row).xxScrItem30Txt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(row).svcPmitLvlTpCd_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(row).xxScrItem30Txt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(row).effFromDt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(row).effThruDt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(index).svcAccsPmitNum_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(index).svcPmitLvlTpCd_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(index).xxScrItem30Txt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(index).effFromDt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        sMsg.A.no(index).effThruDt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Other record" });
                        return false;
                    }
                }
                BigDecimal svcAccsPmitTechRelnPk = sMsg.A.no(row).svcAccsPmitTechRelnPk_A.getValue();
                BigDecimal count = NSBL0480Query.getInstance().getDtPerOvlpCount(cMsg, svcAccsPmitNum, svcPmitLvlTpCd, svcPmitLvlValTxt, effFromDt, effThruDt, svcAccsPmitTechRelnPk);
                if (count.compareTo(BigDecimal.ZERO) > 0) {
                    sMsg.xxErrNum.setValue(row);
                    sMsg.A.no(row).svcAccsPmitNum_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Access Permit Relation data" });
                    sMsg.A.no(row).svcPmitLvlTpCd_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Access Permit Relation data" });
                    sMsg.A.no(row).xxScrItem30Txt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Access Permit Relation data" });
                    sMsg.A.no(row).effFromDt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Access Permit Relation data" });
                    sMsg.A.no(row).effThruDt_A.setErrorInfo(1, NSBM0185E, new String[] {"This record", "Access Permit Relation data" });
                    return false;
                }
                // END 2017/02/01 K.Kitachi [QC#16629, MOD]
                // START 2017/02/01 K.Kitachi [QC#16629, DEL]
//                if (BigDecimal.ONE.compareTo(searchSvcAccsPmitNum) == 0) {
//                    sMsg.xxErrNum.setValue(row);
//                    sMsg.A.no(row).svcAccsPmitNum_A.setErrorInfo(1, NSBM0089E, new String[] {svcAccsPmitNum });
//                    return false;
//                }
                // END 2017/02/01 K.Kitachi [QC#16629, DEL]
            }
        } else {
            for (int row = 0; row < sMsg.B.getValidCount(); row++) {
                String techCd = sMsg.B.no(row).techCd_B.getValue();
                for (int index = 0; index < sMsg.B.getValidCount(); index++) {
                    String checkCd = sMsg.B.no(index).techCd_B.getValue();
                    if ((row != index) && techCd.equals(checkCd)) {
                        sMsg.xxErrNum.setValue(row);
                        sMsg.B.no(row).techCd_B.setErrorInfo(1, NSBM0082E, new String[] {techCd, "Access Permit" });
                        sMsg.B.no(index).techCd_B.setErrorInfo(1, NSBM0082E, new String[] {checkCd, "Access Permit" });
                        return false;
                    }
                }
                // START 2016/12/15 K.Kojima [QC#15653,ADD]
                if (ZYPCommonFunc.hasValue(sMsg.B.no(row).svcAccsPmitTechRelnPk_B)) {
                    if (ZYPConstant.FLG_ON_Y.equals(sMsg.B.no(row).xxSelFlg_B.getValue())) {
                        continue;
                    }
                }
                // END 2016/12/15 K.Kojima [QC#15653,ADD]
                if (!getSearchPsn(cMsg, techCd)) {
                    sMsg.xxErrNum.setValue(row);
                    sMsg.B.no(row).techCd_B.setErrorInfo(1, NSBM0084E, new String[] {techCd, "S21_PSN" });
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * checkEffFromDtResourceType
     * @param cMsg NSBL0480CMsg
     * @return boolean
     */
    public static boolean checkEffFromDtResourceType(NSBL0480CMsg cMsg) {

        for (int index = 0; index < cMsg.A.getValidCount(); index++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(index).effFromDt_A) && ZYPCommonFunc.hasValue(cMsg.A.no(index).effThruDt_A)) {
                String effFromDt = cMsg.A.no(index).effFromDt_A.getValue();
                String effThruDt = cMsg.A.no(index).effThruDt_A.getValue();
                if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
                    cMsg.A.no(index).effFromDt_A.setErrorInfo(1, NSBM0083E);
                    cMsg.A.no(index).effThruDt_A.setErrorInfo(1, NSBM0083E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * checkEffFromDtAccessPermits
     * @param cMsg NSBL0480CMsg
     * @return boolean
     */
    public static boolean checkEffFromDtAccessPermits(NSBL0480CMsg cMsg) {

        for (int index = 0; index < cMsg.B.getValidCount(); index++) {
            if (ZYPCommonFunc.hasValue(cMsg.B.no(index).effFromDt_B) && ZYPCommonFunc.hasValue(cMsg.B.no(index).effThruDt_B)) {
                String effFromDt = cMsg.B.no(index).effFromDt_B.getValue();
                String effThruDt = cMsg.B.no(index).effThruDt_B.getValue();
                if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
                    cMsg.B.no(index).effFromDt_B.setErrorInfo(1, NSBM0083E);
                    cMsg.B.no(index).effThruDt_B.setErrorInfo(1, NSBM0083E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * isErrorSearchCondition
     * @param cMsg NSBL0480CMsg
     * @return boolean
     */
    public static boolean isErrorSearchCondition(NSBL0480CMsg cMsg) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            if (!getSearchPsn(cMsg, cMsg.techCd_A1.getValue())) {
                cMsg.techCd_A1.setErrorInfo(1, NSBM0084E, new String[] {cMsg.techCd_A1.getValue(), "S21_PSN" });
                return false;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.techCd_A2, cMsg.techCd_A1);

        } else {
            // START 2017/02/01 K.Kitachi [QC#16629, MOD]
            BigDecimal searchSvcAccsPmitNum = getSearchSvcAccsPmit(cMsg, cMsg.svcAccsPmitNum.getValue(), cMsg.svcPmitLvlTpCd.getValue(), cMsg.xxScrItem30Txt.getValue());
            if (searchSvcAccsPmitNum == null) {
                cMsg.svcAccsPmitNum.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                cMsg.svcPmitLvlTpCd.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                cMsg.xxScrItem30Txt.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                return false;
            }
            // END 2017/02/01 K.Kitachi [QC#16629, MOD]
            // START 2017/02/01 K.Kitachi [QC#16629, DEL]
//            if (BigDecimal.ONE.compareTo(searchSvcAccsPmitNum) == 0) {
//                cMsg.svcAccsPmitNum.setErrorInfo(1, NSBM0089E, new String[] {cMsg.svcAccsPmitNum.getValue() });
//                return false;
//            }
            // END 2017/02/01 K.Kitachi [QC#16629, DEL]
            ZYPEZDItemValueSetter.setValue(cMsg.techCd_B2, cMsg.svcAccsPmitNum);
        }
        return true;
    }

    /**
     * isErrorSwitchViewCondition
     * @param cMsg NSBL0480CMsg
     * @return boolean
     */
    public static boolean isErrorSwitchViewCondition(NSBL0480CMsg cMsg) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            int targetIdxNum = cMsg.xxRadioBtn_A.getValueInt();
            if (ZYPCommonFunc.hasValue(cMsg.A.no(targetIdxNum).svcAccsPmitNum_A)) {
                String svcAccsPmitNum = cMsg.A.no(targetIdxNum).svcAccsPmitNum_A.getValue();
                // START 2017/02/01 K.Kitachi [QC#16629, MOD]
                String svcPmitLvlTpCd = cMsg.A.no(targetIdxNum).svcPmitLvlTpCd_A.getValue();
                String svcPmitLvlValTxt = cMsg.A.no(targetIdxNum).xxScrItem30Txt_A.getValue();
                BigDecimal searchSvcAccsPmitNum = getSearchSvcAccsPmit(cMsg, svcAccsPmitNum, svcPmitLvlTpCd, svcPmitLvlValTxt);
                if (searchSvcAccsPmitNum == null) {
                    cMsg.A.no(targetIdxNum).svcAccsPmitNum_A.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                    cMsg.A.no(targetIdxNum).svcPmitLvlTpCd_A.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                    cMsg.A.no(targetIdxNum).xxScrItem30Txt_A.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                    return false;
                }
                // END 2017/02/01 K.Kitachi [QC#16629, MOD]
                // START 2017/02/01 K.Kitachi [QC#16629, DEL]
//                if (BigDecimal.ONE.compareTo(searchSvcAccsPmitNum) == 0) {
//                    cMsg.A.no(targetIdxNum).svcAccsPmitNum_A.setErrorInfo(1, NSBM0089E, new String[] {svcAccsPmitNum });
//                    return false;
//                }
                // END 2017/02/01 K.Kitachi [QC#16629, DEL]
            }
        } else {
            int targetIdxNum = cMsg.xxRadioBtn_B.getValueInt();
            if (ZYPCommonFunc.hasValue(cMsg.B.no(targetIdxNum).techCd_B)) {
                String techCd = cMsg.B.no(targetIdxNum).techCd_B.getValue();
                if (!getSearchPsn(cMsg, techCd)) {
                    cMsg.B.no(targetIdxNum).techCd_B.setErrorInfo(1, NSBM0084E, new String[] {techCd, "S21_PSN" });
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean getSearchPsn(NSBL0480CMsg cMsg, String techCd) {

        Map<String, Object> psnInfo = NSBL0480Query.getInstance().getSearchPsn(cMsg, techCd);
        if (psnInfo == null) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_A3, (String) psnInfo.get("PSN_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.psnTpDescTxt, (String) psnInfo.get("PSN_TP_DESC_TXT"));
        return true;
    }

    // START 2017/02/01 K.Kitachi [QC#16629, MOD]
    private static BigDecimal getSearchSvcAccsPmit(NSBL0480CMsg cMsg, String svcAccsPmitNum, String svcPmitLvlTpCd, String svcPmitLvlValTxt) {

        List<Map<String, Object>> svcAccsPmitInfo = NSBL0480Query.getInstance().getSearchSvcAccsPmitList(cMsg, svcAccsPmitNum, svcPmitLvlTpCd, svcPmitLvlValTxt);
        if (svcAccsPmitInfo == null || svcAccsPmitInfo.size() == 0) {
            return null;
        }

        if (svcAccsPmitInfo.size() > 1) {
            return BigDecimal.ONE;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.svcAccsPmitNum, (String) svcAccsPmitInfo.get(0).get("SVC_ACCS_PMIT_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.svcAccsPmitDescTxt, (String) svcAccsPmitInfo.get(0).get("SVC_ACCS_PMIT_DESC_TXT"));
        return BigDecimal.ZERO;
    }
    // END 2017/02/01 K.Kitachi [QC#16629, MOD]

    /**
     * deleteRow
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     * @return boolean
     */
    public static boolean deleteRow(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        int sLineNum = 0;
        int selectLineNum = 0;
        EZDMsgArray asMsg = null;
        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            asMsg = sMsg.A;
            selectLineNum = cMsg.xxRadioBtn_A.getValueInt();
            sLineNum = sMsg.xxRadioBtn_A.getValueInt();
            if (ZYPCommonFunc.hasValue(cMsg.A.no(selectLineNum).svcAccsPmitTechRelnPk_A)) {
                cMsg.A.no(selectLineNum).svcAccsPmitNum_A.setErrorInfo(1, NSBM0085E);
                cMsg.A.no(selectLineNum).svcAccsPmitDescTxt_A.setErrorInfo(1, NSBM0085E);
                // START 2017/02/01 K.Kitachi [QC#16629, ADD]
                cMsg.A.no(selectLineNum).svcPmitLvlTpCd_A.setErrorInfo(1, NSBM0085E);
                cMsg.A.no(selectLineNum).xxScrItem30Txt_A.setErrorInfo(1, NSBM0085E);
                // END 2017/02/01 K.Kitachi [QC#16629, ADD]
                cMsg.A.no(selectLineNum).effFromDt_A.setErrorInfo(1, NSBM0085E);
                cMsg.A.no(selectLineNum).effThruDt_A.setErrorInfo(1, NSBM0085E);
                return false;
            }
        } else {
            asMsg = sMsg.B;
            selectLineNum = cMsg.xxRadioBtn_B.getValueInt();
            sLineNum = sMsg.xxRadioBtn_B.getValueInt();
            if (ZYPCommonFunc.hasValue(cMsg.B.no(selectLineNum).svcAccsPmitTechRelnPk_B)) {
                cMsg.B.no(selectLineNum).techCd_B.setErrorInfo(1, NSBM0085E);
                cMsg.B.no(selectLineNum).fullPsnNm_B.setErrorInfo(1, NSBM0085E);
                cMsg.B.no(selectLineNum).effFromDt_B.setErrorInfo(1, NSBM0085E);
                cMsg.B.no(selectLineNum).effThruDt_B.setErrorInfo(1, NSBM0085E);
                return false;
            }
        }

        List<Integer> selectedRow = new ArrayList<Integer>();
        selectedRow.add(sLineNum);
        ZYPTableUtil.deleteRows(asMsg, selectedRow);

        return true;
    }

    /**
     * setUpdateFlg
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     */
    public static void setUpdateFlg(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            for (int index = 0; index < cMsg.A.getValidCount(); index++) {
                NSBL0480_ACMsg acMsg = cMsg.A.no(index);
                if (ZYPCommonFunc.hasValue(acMsg.svcAccsPmitTechRelnPk_A)) {
                    BigDecimal acTechTngHistPk = acMsg.svcAccsPmitTechRelnPk_A.getValue();
                    for (int cnt = 0; cnt < sMsg.A.getValidCount(); cnt++) {
                        NSBL0480_ASMsg asMsg = sMsg.A.no(cnt);
                        BigDecimal asTechTngHistPk = asMsg.svcAccsPmitTechRelnPk_A.getValue();
                        if (acTechTngHistPk.equals(asTechTngHistPk) && isSameDataResource(acMsg, asMsg)) {
                            ZYPEZDItemValueSetter.setValue(acMsg.xxSelFlg_A, ZYPConstant.FLG_ON_Y);
                            break;
                        }
                    }
                }
            }
        } else {
            for (int index = 0; index < cMsg.B.getValidCount(); index++) {
                NSBL0480_BCMsg bcMsg = cMsg.B.no(index);
                if (ZYPCommonFunc.hasValue(bcMsg.svcAccsPmitTechRelnPk_B)) {
                    BigDecimal bcTechTngHistPk = bcMsg.svcAccsPmitTechRelnPk_B.getValue();
                    for (int cnt = 0; cnt < sMsg.B.getValidCount(); cnt++) {
                        NSBL0480_BSMsg bsMsg = sMsg.B.no(cnt);
                        BigDecimal bsTechTngHistPk = bsMsg.svcAccsPmitTechRelnPk_B.getValue();
                        if (bcTechTngHistPk.equals(bsTechTngHistPk) && isSameDataAccessPermits(bcMsg, bsMsg)) {
                            ZYPEZDItemValueSetter.setValue(bcMsg.xxSelFlg_B, ZYPConstant.FLG_ON_Y);
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean isSameDataResource(NSBL0480_ACMsg acMsg, NSBL0480_ASMsg asMsg) {

        // START 2017/02/01 K.Kitachi [QC#16629, MOD]
        String svcAccsPmitNum = acMsg.svcAccsPmitNum_A.getValue();
        String svcAccsPmitDescTxt = acMsg.svcAccsPmitDescTxt_A.getValue();
        String svcPmitLvlTpCd = acMsg.svcPmitLvlTpCd_A.getValue();
        String svcPmitLvlValTxt = acMsg.xxScrItem30Txt_A.getValue();
        String acMsgEffFromDt = checkNull(acMsg.effFromDt_A);
        String acMsgEffThruDt = checkNull(acMsg.effThruDt_A);

        String asMsgSvcAccsPmitNum = asMsg.svcAccsPmitNum_A.getValue();
        String asMsgSvcAccsPmitDescTxt = asMsg.svcAccsPmitDescTxt_A.getValue();
        String asMsgSvcPmitLvlTpCd = asMsg.svcPmitLvlTpCd_A.getValue();
        String asMsgSvcPmitLvlValTxt = asMsg.xxScrItem30Txt_A.getValue();
        String asMsgEffFromDt = checkNull(asMsg.effFromDt_A);
        String asMsgEffThruDt = checkNull(asMsg.effThruDt_A);

        if (svcAccsPmitNum.equals(asMsgSvcAccsPmitNum) && svcAccsPmitDescTxt.equals(asMsgSvcAccsPmitDescTxt) && svcPmitLvlTpCd.equals(asMsgSvcPmitLvlTpCd) && svcPmitLvlValTxt.equals(asMsgSvcPmitLvlValTxt)
                && acMsgEffFromDt.equals(asMsgEffFromDt) && acMsgEffThruDt.equals(asMsgEffThruDt)) {
            return true;
        }
        // END 2017/02/01 K.Kitachi [QC#16629, MOD]
        return false;
    }

    private static boolean isSameDataAccessPermits(NSBL0480_BCMsg bcMsg, NSBL0480_BSMsg bsMsg) {

        String bcMsgTechCd = bcMsg.techCd_B.getValue();
        String bcMsgFfullPsnNm = bcMsg.fullPsnNm_B.getValue();
        String bcMsgEffFromDt = checkNull(bcMsg.effFromDt_B);
        String bcMsgEffThruDt = checkNull(bcMsg.effThruDt_B);

        String bsMsgTechCd = bsMsg.techCd_B.getValue();
        String bsMsgFfullPsnNm = bsMsg.fullPsnNm_B.getValue();
        String bsMsgEffFromDt = checkNull(bsMsg.effFromDt_B);
        String bsMsgEffThruDt = checkNull(bsMsg.effThruDt_B);

        if (bcMsgTechCd.equals(bsMsgTechCd) && bcMsgFfullPsnNm.equals(bsMsgFfullPsnNm) && bcMsgEffFromDt.equals(bsMsgEffFromDt) && bcMsgEffThruDt.equals(bsMsgEffThruDt)) {
            return true;
        }
        return false;
    }

    /**
     * updateProcess
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     */
    public static void updateProcess(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.fullPsnNm_A3)) {
                if (!getSearchPsn(cMsg, cMsg.techCd_A1.getValue())) {
                    cMsg.techCd_A1.setErrorInfo(1, NSBM0084E, new String[] {cMsg.techCd_A1.getValue(), "S21_PSN" });
                    return;
                }
            }
            doProcessResource(cMsg, sMsg);
        } else {
            if (!ZYPCommonFunc.hasValue(cMsg.svcAccsPmitDescTxt)) {
                // START 2017/02/01 K.Kitachi [QC#16629, MOD]
                BigDecimal svcAccsPmit = getSearchSvcAccsPmit(cMsg, cMsg.svcAccsPmitNum.getValue(), cMsg.svcPmitLvlTpCd.getValue(), cMsg.xxScrItem30Txt.getValue());
                if (svcAccsPmit == null) {
                    cMsg.svcAccsPmitNum.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                    cMsg.svcPmitLvlTpCd.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                    cMsg.xxScrItem30Txt.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                    return;
                }
                // END 2017/02/01 K.Kitachi [QC#16629, MOD]
                // START 2017/02/01 K.Kitachi [QC#16629, DEL]
//                if (BigDecimal.ONE.compareTo(svcAccsPmit) == 0) {
//                    cMsg.svcAccsPmitNum.setErrorInfo(1, NSBM0089E, new String[] {cMsg.svcAccsPmitNum.getValue() });
//                    return;
//                }
                // END 2017/02/01 K.Kitachi [QC#16629, DEL]
            }
            doProcessAccessPermits(cMsg, sMsg);
        }
    }

    private static boolean doProcessResource(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {

            NSBL0480_ASMsg asMsg = sMsg.A.no(index);
            String glblCmpyCd = cMsg.glblCmpyCd.getValue();
            String svcAccsPmitNum = asMsg.svcAccsPmitNum_A.getValue();
            // START 2017/02/01 K.Kitachi [QC#16629, ADD]
            String svcPmitLvlTpCd = asMsg.svcPmitLvlTpCd_A.getValue();
            String svcPmitLvlValTxt = asMsg.xxScrItem30Txt_A.getValue();
            // END 2017/02/01 K.Kitachi [QC#16629, ADD]
            if (!ZYPCommonFunc.hasValue(asMsg.svcAccsPmitDescTxt_A)) {
                // START 2017/02/01 K.Kitachi [QC#16629, MOD]
                List<Map<String, Object>> svcAccsPmitInfo = NSBL0480Query.getInstance().getSearchSvcAccsPmitList(cMsg, svcAccsPmitNum, svcPmitLvlTpCd, svcPmitLvlValTxt);
                if (svcAccsPmitInfo == null || svcAccsPmitInfo.size() == 0) {
                    sMsg.xxErrNum.setValue(index);
                    sMsg.A.no(index).svcAccsPmitNum_A.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                    sMsg.A.no(index).svcPmitLvlTpCd_A.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                    sMsg.A.no(index).xxScrItem30Txt_A.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                    return false;
                }
                // END 2017/02/01 K.Kitachi [QC#16629, MOD]
                // START 2017/02/01 K.Kitachi [QC#16629, DEL]
//                if (svcAccsPmitInfo.size() > 1) {
//                    sMsg.xxErrNum.setValue(index);
//                    sMsg.A.no(index).svcAccsPmitNum_A.setErrorInfo(1, NSBM0089E, new String[] {svcAccsPmitNum });
//                    return false;
//                }
                // END 2017/02/01 K.Kitachi [QC#16629, DEL]
                ZYPEZDItemValueSetter.setValue(asMsg.svcAccsPmitNum_A, (String) svcAccsPmitInfo.get(0).get("SVC_ACCS_PMIT_NUM"));
                ZYPEZDItemValueSetter.setValue(asMsg.svcAccsPmitDescTxt_A, (String) svcAccsPmitInfo.get(0).get("SVC_ACCS_PMIT_DESC_TXT"));
            }

            // START 2017/02/01 K.Kitachi [QC#16629, MOD]
            BigDecimal svcAccsPmitPk = (BigDecimal) NSBL0480Query.getInstance().getSvcAccsPmitPk(cMsg, svcAccsPmitNum, svcPmitLvlTpCd, svcPmitLvlValTxt).getResultObject();
            if (svcAccsPmitPk == null) {
                sMsg.xxErrNum.setValue(index);
                sMsg.A.no(index).svcAccsPmitNum_A.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                sMsg.A.no(index).svcPmitLvlTpCd_A.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                sMsg.A.no(index).xxScrItem30Txt_A.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
                return false;
            }
            // END 2017/02/01 K.Kitachi [QC#16629, MOD]

            if (ZYPCommonFunc.hasValue(asMsg.svcAccsPmitTechRelnPk_A)) {

                if (ZYPConstant.FLG_ON_Y.equals(asMsg.xxSelFlg_A.getValue())) {
                    continue;
                }

                // Update
                BigDecimal svcAccsPmitTechRelnPk = asMsg.svcAccsPmitTechRelnPk_A.getValue();
                String sMsgEzUpTime = asMsg.ezUpTime_A.getValue();
                String sMsgEzUpTimeZone = asMsg.ezUpTimeZone_A.getValue();
                if (!isSameTimeSvcAccsPmitTechReln(glblCmpyCd, svcAccsPmitTechRelnPk, sMsgEzUpTime, sMsgEzUpTimeZone)) {
                    sMsg.xxErrNum.setValue(index);
                    sMsg.A.no(index).svcAccsPmitNum_A.setErrorInfo(1, ZZZM9004E);
                    return false;
                }

                if (!updateSvcAccsPmitTechRelnResource(cMsg, asMsg, svcAccsPmitPk)) {
                    sMsg.xxErrNum.setValue(index);
                    sMsg.A.no(index).svcAccsPmitNum_A.setErrorInfo(1, NSBM0073E, new String[] {"SVC_ACCS_PMIT_TECH_RELN", "SVC_ACCS_PMIT_TECH_RELN_PK", asMsg.svcAccsPmitTechRelnPk_A.getValue().toString() });
                    return false;
                }

            } else {
                // Insert
                if (!insertSvcAccsPmitTechRelnResource(cMsg, asMsg, svcAccsPmitPk)) {
                    sMsg.xxErrNum.setValue(index);
                    sMsg.A.no(index).svcAccsPmitNum_A.setErrorInfo(1, NSBM0081E, new String[] {"SVC_ACCS_PMIT_TECH_RELN", "SVC_ACCS_PMIT_TECH_RELN_PK", asMsg.svcAccsPmitTechRelnPk_A.getValue().toString() });
                    asMsg.svcAccsPmitTechRelnPk_A.clear();
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean doProcessAccessPermits(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg) {

        // START 2017/02/01 K.Kitachi [QC#16629, ADD]
        String svcAccsPmitNum = cMsg.svcAccsPmitNum.getValue();
        String svcPmitLvlTpCd = cMsg.svcPmitLvlTpCd.getValue();
        String svcPmitLvlValTxt = cMsg.xxScrItem30Txt.getValue();
        BigDecimal svcAccsPmitPk = (BigDecimal) NSBL0480Query.getInstance().getSvcAccsPmitPk(cMsg, svcAccsPmitNum, svcPmitLvlTpCd, svcPmitLvlValTxt).getResultObject();
        if (svcAccsPmitPk == null) {
            cMsg.svcAccsPmitNum.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
            cMsg.svcPmitLvlTpCd.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
            cMsg.xxScrItem30Txt.setErrorInfo(1, NSBM0025E, new String[] {"This record", "Access Permit" });
            return false;
        }
        // END 2017/02/01 K.Kitachi [QC#16629, ADD]

        for (int index = 0; index < sMsg.B.getValidCount(); index++) {

            NSBL0480_BSMsg bsMsg = sMsg.B.no(index);
            String glblCmpyCd = cMsg.glblCmpyCd.getValue();
            String psnCd = bsMsg.techCd_B.getValue();
            if (!ZYPCommonFunc.hasValue(bsMsg.fullPsnNm_B)) {
                Map<String, Object> psnInfo = NSBL0480Query.getInstance().getSearchPsn(cMsg, psnCd);
                if (psnInfo == null) {
                    sMsg.xxErrNum.setValue(index);
                    sMsg.B.no(index).techCd_B.setErrorInfo(1, NSBM0084E, new String[] {psnCd, "S21_PSN" });
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(bsMsg.techCd_B, (String) psnInfo.get("PSN_CD"));
                ZYPEZDItemValueSetter.setValue(bsMsg.fullPsnNm_B, (String) psnInfo.get("PSN_NM"));
            }

            // START 2017/02/01 K.Kitachi [QC#16629, DEL]
//            String svcAccsPmitNum = cMsg.svcAccsPmitNum.getValue();
//            String svcAccsPmitDescTxt = cMsg.svcAccsPmitDescTxt.getValue();
//            BigDecimal svcAccsPmitPk = (BigDecimal) NSBL0480Query.getInstance().getSvcAccsPmitPk(cMsg, svcAccsPmitNum, svcAccsPmitDescTxt).getResultObject();
//            if (svcAccsPmitPk == null) {
//                sMsg.xxErrNum.setValue(index);
//                sMsg.B.no(index).techCd_B.setErrorInfo(1, NSBM0084E, new String[] {svcAccsPmitNum, "Access Permit" });
//                return false;
//            }
            // END 2017/02/01 K.Kitachi [QC#16629, DEL]

            if (ZYPCommonFunc.hasValue(bsMsg.svcAccsPmitTechRelnPk_B)) {

                if (ZYPConstant.FLG_ON_Y.equals(bsMsg.xxSelFlg_B.getValue())) {
                    continue;
                }

                // Update
                BigDecimal svcAccsPmitTechRelnPk = bsMsg.svcAccsPmitTechRelnPk_B.getValue();
                String sMsgEzUpTime = bsMsg.ezUpTime_B.getValue();
                String sMsgEzUpTimeZone = bsMsg.ezUpTimeZone_B.getValue();
                if (!isSameTimeSvcAccsPmitTechReln(glblCmpyCd, svcAccsPmitTechRelnPk, sMsgEzUpTime, sMsgEzUpTimeZone)) {
                    sMsg.xxErrNum.setValue(index);
                    sMsg.B.no(index).techCd_B.setErrorInfo(1, ZZZM9004E);
                    return false;
                }

                if (!updateSvcAccsPmitTechRelnAccessPermits(cMsg, bsMsg, svcAccsPmitPk)) {
                    sMsg.xxErrNum.setValue(index);
                    sMsg.B.no(index).techCd_B.setErrorInfo(1, NSBM0073E, new String[] {"SVC_ACCS_PMIT_TECH_RELN", "SVC_ACCS_PMIT_TECH_RELN_PK", bsMsg.svcAccsPmitTechRelnPk_B.getValue().toString() });
                    return false;
                }

            } else {
                // Insert
                if (!insertSvcAccsPmitTechRelnAccessPermits(cMsg, bsMsg, svcAccsPmitPk)) {
                    sMsg.xxErrNum.setValue(index);
                    sMsg.B.no(index).techCd_B.setErrorInfo(1, NSBM0081E, new String[] {"SVC_ACCS_PMIT_TECH_RELN", "SVC_ACCS_PMIT_TECH_RELN_PK", bsMsg.svcAccsPmitTechRelnPk_B.getValue().toString() });
                    bsMsg.svcAccsPmitTechRelnPk_B.clear();
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isSameTimeSvcAccsPmitTechReln(String glblCmpyCd, BigDecimal svcAccsPmitTechRelnPk, String sMsgEzUpTime, String sMsgEzUpTimeZone) {

        SVC_ACCS_PMIT_TECH_RELNTMsg outTMsg = NSBL0480Query.getInstance().getSvcAccsPmitTechRelnInfo(glblCmpyCd, svcAccsPmitTechRelnPk);
        if (outTMsg == null) {
            return false;
        }

        String bfMsgEzUpTime = outTMsg.ezUpTime.getValue();
        String bfsgEzUpTimeZone = outTMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(bfMsgEzUpTime, bfsgEzUpTimeZone, sMsgEzUpTime, sMsgEzUpTimeZone)) {
            return false;
        }
        return true;
    }

    private static boolean updateSvcAccsPmitTechRelnResource(NSBL0480CMsg cMsg, NSBL0480_ASMsg asMsg, BigDecimal svcAccsPmitPk) {

        SVC_ACCS_PMIT_TECH_RELNTMsg accsPmit = new SVC_ACCS_PMIT_TECH_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(accsPmit.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(accsPmit.svcAccsPmitTechRelnPk, asMsg.svcAccsPmitTechRelnPk_A);
        SVC_ACCS_PMIT_TECH_RELNTMsg outAccsPmit = (SVC_ACCS_PMIT_TECH_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(accsPmit);

        if (outAccsPmit != null) {
            ZYPEZDItemValueSetter.setValue(outAccsPmit.techCd, cMsg.techCd_A1);
            ZYPEZDItemValueSetter.setValue(outAccsPmit.svcAccsPmitPk, svcAccsPmitPk);
            ZYPEZDItemValueSetter.setValue(outAccsPmit.effFromDt, asMsg.effFromDt_A);
            ZYPEZDItemValueSetter.setValue(outAccsPmit.effThruDt, asMsg.effThruDt_A);
            S21FastTBLAccessor.update(outAccsPmit);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outAccsPmit.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    private static boolean insertSvcAccsPmitTechRelnResource(NSBL0480CMsg cMsg, NSBL0480_ASMsg asMsg, BigDecimal svcAccsPmitPk) {

        SVC_ACCS_PMIT_TECH_RELNTMsg accsPmit = new SVC_ACCS_PMIT_TECH_RELNTMsg();
        BigDecimal svcAccsPmitTechRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_ACCS_PMIT_TECH_RELN_SQ);
        ZYPEZDItemValueSetter.setValue(accsPmit.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(accsPmit.svcAccsPmitTechRelnPk, svcAccsPmitTechRelnPk);
        ZYPEZDItemValueSetter.setValue(accsPmit.techCd, cMsg.techCd_A1);
        ZYPEZDItemValueSetter.setValue(accsPmit.svcAccsPmitPk, svcAccsPmitPk);
        ZYPEZDItemValueSetter.setValue(accsPmit.effFromDt, asMsg.effFromDt_A);
        ZYPEZDItemValueSetter.setValue(accsPmit.effThruDt, asMsg.effThruDt_A);
        S21FastTBLAccessor.insert(accsPmit);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(accsPmit.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(asMsg.svcAccsPmitTechRelnPk_A, svcAccsPmitTechRelnPk);
            return false;
        }
        return true;
    }

    private static boolean updateSvcAccsPmitTechRelnAccessPermits(NSBL0480CMsg cMsg, NSBL0480_BSMsg bsMsg, BigDecimal svcAccsPmitPk) {

        SVC_ACCS_PMIT_TECH_RELNTMsg accsPmit = new SVC_ACCS_PMIT_TECH_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(accsPmit.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(accsPmit.svcAccsPmitTechRelnPk, bsMsg.svcAccsPmitTechRelnPk_B);
        SVC_ACCS_PMIT_TECH_RELNTMsg outAccsPmit = (SVC_ACCS_PMIT_TECH_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(accsPmit);

        if (outAccsPmit != null) {
            ZYPEZDItemValueSetter.setValue(outAccsPmit.techCd, bsMsg.techCd_B);
            ZYPEZDItemValueSetter.setValue(outAccsPmit.svcAccsPmitPk, svcAccsPmitPk);
            ZYPEZDItemValueSetter.setValue(outAccsPmit.effFromDt, bsMsg.effFromDt_B);
            ZYPEZDItemValueSetter.setValue(outAccsPmit.effThruDt, bsMsg.effThruDt_B);
            S21FastTBLAccessor.update(outAccsPmit);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outAccsPmit.getReturnCode())) {
                return false;
            }
        }
        return true;
    }

    private static boolean insertSvcAccsPmitTechRelnAccessPermits(NSBL0480CMsg cMsg, NSBL0480_BSMsg bsMsg, BigDecimal svcAccsPmitPk) {

        SVC_ACCS_PMIT_TECH_RELNTMsg accsPmit = new SVC_ACCS_PMIT_TECH_RELNTMsg();
        BigDecimal svcAccsPmitTechRelnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_ACCS_PMIT_TECH_RELN_SQ);
        ZYPEZDItemValueSetter.setValue(accsPmit.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(accsPmit.svcAccsPmitTechRelnPk, svcAccsPmitTechRelnPk);
        ZYPEZDItemValueSetter.setValue(accsPmit.techCd, bsMsg.techCd_B);
        ZYPEZDItemValueSetter.setValue(accsPmit.svcAccsPmitPk, svcAccsPmitPk);
        ZYPEZDItemValueSetter.setValue(accsPmit.effFromDt, bsMsg.effFromDt_B);
        ZYPEZDItemValueSetter.setValue(accsPmit.effThruDt, bsMsg.effThruDt_B);
        S21FastTBLAccessor.insert(accsPmit);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(accsPmit.getReturnCode())) {
            ZYPEZDItemValueSetter.setValue(bsMsg.svcAccsPmitTechRelnPk_B, svcAccsPmitTechRelnPk);
            return false;
        }
        return true;
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0480CMsg
     * @param sMsg NSBL0480SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg, int pagenationFrom) {

        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            int cnt = pagenationFrom;
            for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
                if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                    EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
                } else {
                    break;
                }
            }
            sMsg.xxRadioBtn_A.setValue(cMsg.xxRadioBtn_A.getValueInt() + pagenationFrom);
        } else {
            int cnt = pagenationFrom;
            for (; cnt < pagenationFrom + cMsg.B.length(); cnt++) {
                if (cnt < pagenationFrom + cMsg.B.getValidCount()) {
                    EZDMsg.copy(cMsg.B.get(cnt - pagenationFrom), null, sMsg.B.get(cnt), null);
                } else {
                    break;
                }
            }
            sMsg.xxRadioBtn_B.setValue(cMsg.xxRadioBtn_B.getValueInt() + pagenationFrom);
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSBL0480CMsg cMsg, NSBL0480SMsg sMsg, int pageFrom) {

        cMsg.xxNum.clear();
        if (RESOURCE_MODE.equals(cMsg.xxScrDply.getValue())) {
            int i = pageFrom;
            for (; i < pageFrom + cMsg.A.length(); i++) {
                if (i < sMsg.A.getValidCount()) {
                    EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
                } else {
                    break;
                }
            }

            cMsg.A.setValidCount(i - pageFrom);
            cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());
            cMsg.xxRadioBtn_A.setValue(0);
            if (sMsg.A.length() == sMsg.A.getValidCount()) {
                cMsg.xxNum.setValue(BigDecimal.ONE);
            }
        } else {
            int i = pageFrom;
            for (; i < pageFrom + cMsg.B.length(); i++) {
                if (i < sMsg.B.getValidCount()) {
                    EZDMsg.copy(sMsg.B.get(i), null, cMsg.B.get(i - pageFrom), null);
                } else {
                    break;
                }
            }

            cMsg.B.setValidCount(i - pageFrom);
            cMsg.xxPageShowFromNum_B.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_B.setValue(pageFrom + cMsg.B.getValidCount());
            cMsg.xxRadioBtn_B.setValue(0);
            if (sMsg.B.length() == sMsg.B.getValidCount()) {
                cMsg.xxNum.setValue(BigDecimal.ONE);
            }
        }
    }

    private static String checkNull(EZDSItem value) {
        String rt = "";
        if (value instanceof EZDSDateItem) {
            EZDSDateItem item = (EZDSDateItem) value;
            if (ZYPCommonFunc.hasValue(item)) {
                return item.getValue();
            }
        } else if (value instanceof EZDSStringItem) {
            EZDSStringItem item = (EZDSStringItem) value;
            if (ZYPCommonFunc.hasValue(item)) {
                return item.getValue();
            }
        }
        return rt;
    }

    private static String checkNull(EZDCItem value) {
        String rt = "";
        if (value instanceof EZDCDateItem) {
            EZDCDateItem item = (EZDCDateItem) value;
            if (ZYPCommonFunc.hasValue(item)) {
                return item.getValue();
            }
        }
        return rt;
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param sMsgCount int
     * @return int
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int sMsgCount) {

        int insertPage = (sMsgCount / rowsPerPage) * rowsPerPage;
        return insertPage;
    }

    /**
     * getPageNum
     * @param maxDispalyRows int
     * @param rowNum int
     * @return int
     */
    public static int getPageNum(int maxDispalyRows, int rowNum) {
        return ((rowNum - 1) / maxDispalyRows + 1);
    }

    // START 2017/02/01 K.Kitachi [QC#16629, ADD]
    /**
     * Create Pull Down
     * @param cMsg NSAL0720CMsg
     */
    public static void createPullDown(NSBL0480CMsg cMsg) {
        createSvcPmitLvlTpPulldown(cMsg);
    }

    private static void createSvcPmitLvlTpPulldown(NSBL0480CMsg cMsg) {
        SVC_PMIT_LVL_TPTMsgArray tMsgAry = getSvcPmitLvlTpPulldownList(cMsg.glblCmpyCd.getValue());
        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcPmitLvlTpCd");
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcPmitLvlTpDescTxt");
        ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.svcPmitLvlTpCd_PC, cMsg.svcPmitLvlTpDescTxt_PD);
    }

    private static SVC_PMIT_LVL_TPTMsgArray getSvcPmitLvlTpPulldownList(String glblCmpyCd) {
        SVC_PMIT_LVL_TPTMsg inMsg = new SVC_PMIT_LVL_TPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        return (SVC_PMIT_LVL_TPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private static boolean isDtPerOvlp(String fromDt, String thruDt, String chkFromDt, String chkThruDt) {
        if (!ZYPCommonFunc.hasValue(thruDt)) {
            thruDt = "99991231";
        }
        if (!ZYPCommonFunc.hasValue(chkThruDt)) {
            chkThruDt = "99991231";
        }
        if ((ZYPDateUtil.compare(fromDt, chkFromDt) < 0 && ZYPDateUtil.compare(thruDt, chkFromDt) < 0) || (ZYPDateUtil.compare(fromDt, chkThruDt) > 0 && ZYPDateUtil.compare(thruDt, chkThruDt) > 0)) {
            return false;
        }
        return true;
    }
    // END 2017/02/01 K.Kitachi [QC#16629, ADD]
}
