/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2840.common;

import static business.blap.NMAL2840.constant.NMAL2840Constant.CHECK_CRIT_CD;
import static business.blap.NMAL2840.constant.NMAL2840Constant.COMMA;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DATE_LENGTH;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DATE_TIME_LENGTH;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_DLR_CD_1000;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_DLR_CD_1010;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_DLR_CD_1020;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_DLR_CD_2000;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_DLR_CD_3000;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_NM_01;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_NM_02;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_NM_03;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_NM_04;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_NM_05;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_NM_06;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DS_ACCT_NM_07;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_11;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_12;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_21;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_31;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_32;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_33;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_34;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_51;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_52;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_53;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_54;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_55;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_56;
import static business.blap.NMAL2840.constant.NMAL2840Constant.DUNS_CRIT_DEF_VAL_FLG_57;
import static business.blap.NMAL2840.constant.NMAL2840Constant.FIRST_LINE_ADDR_01;
import static business.blap.NMAL2840.constant.NMAL2840Constant.FIRST_LINE_ADDR_02;
import static business.blap.NMAL2840.constant.NMAL2840Constant.FIRST_LINE_ADDR_03;
import static business.blap.NMAL2840.constant.NMAL2840Constant.MAX_CRIT_CD;
import static business.blap.NMAL2840.constant.NMAL2840Constant.VALUE_IS_NULL;
import static business.blap.NMAL2840.constant.NMAL2840Constant.XX_DUNS_PROC_CMNT_TXT_MAX_LEN;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2840.NMAL2840CMsg;
import business.blap.NMAL2840.NMAL2840SMsg;
import business.db.DUNS_CRITTMsg;
import business.file.NMAL2840FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL2840CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Fujitsu         R.Nakamura      Create          N/A
 * 2016/06/15   Fujitsu         R.Nakamura      Update          QC#10073
 * 2016/06/20   Fujitsu         R.Nakamura      Update          QC#10340
 * 2016/06/27   Fujitsu         R.Nakamura      Update          QC#10905
 * 2016/06/29   Fujitsu         R.Nakamura      Update          QC#10905
 * 2016/07/01   Fujitsu         R.Nakamura      Update          QC#11316
 * 2016/07/04   Fujitsu         R.Nakamura      Update          QC#11316
 * 2016/07/12   Fujitsu         R.Nakamura      Update          QC#9536
 * 2016/10/04   Fujitsu         C.Yokoi         Update          QC#14862
 * 2016/10/06   Fujitsu         R.Nakamura      Update          QC#14861
 * 2016/11/08   Fujitsu         N.Sugiura       Update          QC#14832
 * 2016/12/19   Fujitsu         Y.Kanefusa      Update          QC#16209
 * 2017/12/15   Fujitsu         Hd.Sugawara     Update          QC#20905
 *</pre>
 */
public class NMAL2840CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param bizMsg NMAL2840CMsg
     * @param glblMsg NMAL2840SMsg
     * @param startRow int
     */
    public static void loadOnePageToCMsg(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg, int startRow) {

        int i = startRow;
        for (; i < startRow + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - startRow).dunsProcTpDescTxt, glblMsg.A.no(i).dunsProcTpDescTxt_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - startRow).dunsFileNm_2, glblMsg.A.no(i).dunsFileNm_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - startRow).dunsFileLineNum, glblMsg.A.no(i).dunsFileLineNum_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - startRow).xxDtTm_4, glblMsg.A.no(i).xxDtTm_A);
                // Mod Start 2016/07/01 QC#11316
                // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i -
                // startRow).dunsProcById,
                // glblMsg.A.no(i).dunsProcById_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - startRow).fill103Txt, glblMsg.A.no(i).fill103Txt_A);
                // Mod Start 2016/06/20 QC#10340
                // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i -
                // startRow).dunsCritValTxt,
                // glblMsg.A.no(i).dunsCritValTxt_A);
                // ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i -
                // startRow).fill212Txt,
                // glblMsg.A.no(i).fill212Txt_A);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i - startRow).xxDunsProcCmntTxt, glblMsg.A.no(i).xxDunsProcCmntTxt_A);
                // Mod End 2016/06/20 QC#10340
                // Mod End 2016/07/01 QC#11316
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - startRow);

        bizMsg.xxPageShowFromNum.setValue(startRow + 1);
        bizMsg.xxPageShowToNum.setValue(startRow + bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
    }

    /**
     * clearExtractFieldsExtractSend
     * @param bizMsg NMAL2840CMsg
     */
    public static void clearExtractFieldsExtractSend(NMAL2840CMsg bizMsg) {

        bizMsg.dunsCritDefValFlg_11.clear();
        bizMsg.dunsCritDefValFlg_12.clear();
        bizMsg.dunsCritDefValFlg_21.clear();
        bizMsg.dunsCritDefValFlg_31.clear();
        bizMsg.dunsCritDefValFlg_32.clear();
        bizMsg.dunsCritDefValFlg_33.clear();

        bizMsg.effFromDt.clear();

        // Add Start 2016/11/08 QC#14832
        bizMsg.dunsCritCd_BK.clear();
        bizMsg.dunsCritDefValFlg_B1.clear();
        bizMsg.dunsCritDefValFlg_B2.clear();
        bizMsg.dunsCritDefValFlg_B3.clear();
        bizMsg.dunsCritDefValFlg_B4.clear();
        bizMsg.dunsCritDefValFlg_B5.clear();
        bizMsg.dunsCritDefValFlg_B6.clear();
        bizMsg.effFromDt_BK.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_WN, ZYPConstant.FLG_OFF_N);
        // Add End 2016/11/08 QC#14832
    }

    /**
     * clearExtractFieldsDNBFileUpload
     * @param bizMsg NMAL2840CMsg
     */
    public static void clearExtractFieldsDNBFileUpload(NMAL2840CMsg bizMsg) {

        bizMsg.dunsCritDescTxt_51.clear();
        bizMsg.dunsCritDescTxt_52.clear();
        bizMsg.dunsCritDescTxt_53.clear();
        bizMsg.dunsCritDescTxt_54.clear();
        bizMsg.dunsCritDescTxt_55.clear();
        bizMsg.dunsCritDescTxt_56.clear();
        bizMsg.dunsCritDescTxt_57.clear();

        bizMsg.dunsCritDefValFlg_51.clear();
        bizMsg.dunsCritDefValFlg_52.clear();
        bizMsg.dunsCritDefValFlg_53.clear();
        bizMsg.dunsCritDefValFlg_54.clear();
        bizMsg.dunsCritDefValFlg_55.clear();
        bizMsg.dunsCritDefValFlg_56.clear();
        bizMsg.dunsCritDefValFlg_57.clear();
    }

    /**
     * clearAuditInformation
     * @param bizMsg NMAL2840CMsg
     * @param glblMsg NMAL2840SMsg
     */
    public static void clearAuditInformation(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);
    }

    /**
     * setExtractFieldsExtractSend
     * @param bizMsg NMAL2840CMsg
     * @param ssmResult S21SsmEZDResult
     */
    public static void setExtractFieldsExtractSend(NMAL2840CMsg bizMsg, S21SsmEZDResult ssmResult) {

        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < bizMsg.xxDtTm_PC.length(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            String checkFlagValue = (String) resultMap.get("DUNS_CRIT_CD");
            String setValue = (String) resultMap.get("DUNS_CRIT_DEF_VAL_FLG");
            String setValueDate = (String) resultMap.get("DUNS_CRIT_DEF_VAL_TXT");

            // Mod Start 2016/07/12 QC#9536
            if (DUNS_CRIT_DEF_VAL_FLG_11.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValue) && ZYPConstant.CHKBOX_ON_Y.equals(setValue)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_11, setValue);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_12.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValue) && ZYPConstant.CHKBOX_ON_Y.equals(setValue)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_12, setValue);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_21.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValue) && ZYPConstant.CHKBOX_ON_Y.equals(setValue)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_21, setValue);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_31.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValue) && ZYPConstant.CHKBOX_ON_Y.equals(setValue)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_31, setValue);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_31.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValue) && ZYPConstant.CHKBOX_ON_Y.equals(setValue)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_31, setValue);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_32.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValue) && ZYPConstant.CHKBOX_ON_Y.equals(setValue)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_32, setValue);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_33.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValue) && ZYPConstant.CHKBOX_ON_Y.equals(setValue)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_33, setValue);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_34.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValueDate)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt, ZYPDateUtil.formatEzd8ToDisp(setValueDate, true));
                }
            }
            // Mod End 2016/07/12 QC#9536
        }
    }

    /**
     * setExtractFieldsExtractSend
     * @param bizMsg NMAL2840CMsg
     * @param ssmResult S21SsmEZDResult
     */
    public static void setExtractFieldsDNBFileUpload(NMAL2840CMsg bizMsg, S21SsmEZDResult ssmResult) {

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size() && i < bizMsg.xxDtTm_PC.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            // Mod Start 2016/10/06 QC#14861
//            String checkFlagValue = setStringValue(resultMap, "DUNS_CRIT_CD");
//            String setValueFalg = setStringValue(resultMap, "DUNS_CRIT_DEF_VAL_FLG");
//            String setValueData = setStringValue(resultMap, "DUNS_CRIT_DEF_VAL_TXT");
            String checkFlagValue = getStringValue(resultMap, "DUNS_CRIT_CD");
            String setValueFalg = getStringValue(resultMap, "DUNS_CRIT_DEF_VAL_FLG");
            String setValueData = getStringValue(resultMap, "DUNS_CRIT_DEF_VAL_TXT");
            // Mod End 2016/10/06 QC#14861

            // Mod Start 2016/07/12 QC#9536
            if (DUNS_CRIT_DEF_VAL_FLG_51.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValueFalg) && ZYPConstant.CHKBOX_ON_Y.equals(setValueFalg)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_51, setValueFalg);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDescTxt_51, setValueData);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_52.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValueFalg) && ZYPConstant.CHKBOX_ON_Y.equals(setValueFalg)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_52, setValueFalg);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDescTxt_52, setValueData);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_53.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValueFalg) && ZYPConstant.CHKBOX_ON_Y.equals(setValueFalg)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_53, setValueFalg);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDescTxt_53, setValueData);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_54.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValueFalg) && ZYPConstant.CHKBOX_ON_Y.equals(setValueFalg)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_54, setValueFalg);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDescTxt_54, setValueData);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_55.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValueFalg) && ZYPConstant.CHKBOX_ON_Y.equals(setValueFalg)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_55, setValueFalg);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDescTxt_55, setValueData);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_56.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValueFalg) && ZYPConstant.CHKBOX_ON_Y.equals(setValueFalg)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_56, setValueFalg);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDescTxt_56, setValueData);
                }
            } else if (DUNS_CRIT_DEF_VAL_FLG_57.equals(checkFlagValue)) {
                if (ZYPCommonFunc.hasValue(setValueFalg) && ZYPConstant.CHKBOX_ON_Y.equals(setValueFalg)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDefValFlg_57, setValueFalg);
                } else {
                    ZYPEZDItemValueSetter.setValue(bizMsg.dunsCritDescTxt_57, setValueData);
                }
            }
            // Mod End 2016/07/12 QC#9536
        }
    }

    /**
     * setStringValueDetail
     * @param resultMap Map<String, Object>
     * @param valueKey String
     * @param setValue String
     * @return setValue
     */
    public static String getStringValueDetail(Map<String, Object> resultMap, String valueKey, String setValue) {

        if (!ZYPCommonFunc.hasValue(setValue)) {
            // Mod Start 2016/10/06 QC#14861
//            setValue = setStringValue(resultMap, valueKey);
            setValue = getStringValue(resultMap, valueKey);
            // Mod End 2016/10/06 QC#14861
        }

        return setValue;
    }

    // Add Start 2016/06/29 QC#10905
    /**
     * setStringValueComment
     * @param resultMap Map<String, Object>
     * @param valueKey String
     * @param setValue String
     * @return setValue
     */
    public static String getStringValueComment(Map<String, Object> resultMap, String valueKey, String setValue) {

        // Mod Start 2016/07/12 QC#9536
        if (!ZYPCommonFunc.hasValue(setValue) || ZYPConstant.FLG_OFF_N.equals(setValue)) {
            // Mod Start 2016/10/06 QC#14861
//            setValue = setStringValue(resultMap, valueKey);
            setValue = getStringValue(resultMap, valueKey);
            // Mod End 2016/10/06 QC#14861
        }
        // Mod End 2016/07/12 QC#9536

        return setValue;
    }

    // Add End 2016/06/29 QC#10905

    /**
     * setStringValue
     * @param resultMap Map<String, Object>
     * @param valueKey String
     * @return setValue
     */
    public static String getStringValue(Map<String, Object> resultMap, String valueKey) {

        if (null == resultMap.get(valueKey) //
                || !ZYPCommonFunc.hasValue(resultMap.get(valueKey).toString())) {
            return "";
        } else {
            return (String) resultMap.get(valueKey).toString();
        }

    }

    /**
     * getDunsCRITDescTxt
     * @param glblCmpyCd String
     * @param critCd String
     * @return getValue
     */
    public static String getDunsCRITDescTxt(String glblCmpyCd, String critCd) {

        DUNS_CRITTMsg tMsg = new DUNS_CRITTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsCritCd, critCd);
        tMsg = (DUNS_CRITTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (null != tMsg) {
            return tMsg.dunsCritDescTxt.getValue();
        } else {
            return "";
        }

    }

    /**
     * formatDt
     * @param dt date
     * @return after format date
     */
    public static String formatDate(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > DATE_LENGTH) {
            dt = dt.substring(0, DATE_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt);
    }

    /**
     * formatDt
     * @param dt date
     * @return after format date
     */
    public static String formatDateTime(String dt) {

        // Mod Start 2016/07/01
        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > DATE_TIME_LENGTH) {
            dt = dt.substring(0, DATE_TIME_LENGTH);
        }

        dt = ZYPDateUtil.formatEzd14ToDisp(dt);

        return dt;
        // Mod End 2016/07/01
    }

    /**
     * setMapValue
     * @param bizMsg NMAL2840CMsg
     * @param glblCmpyCd String
     * @return SQL parameeter
     */
    public static Map<String, Object> setMapValue(NMAL2840CMsg bizMsg, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsAcctNm01", DS_ACCT_NM_01);
        params.put("dsAcctNm02", DS_ACCT_NM_02);
        params.put("dsAcctNm03", DS_ACCT_NM_03);
        params.put("dsAcctNm04", DS_ACCT_NM_04);
        params.put("dsAcctNm05", DS_ACCT_NM_05);
        params.put("dsAcctNm06", DS_ACCT_NM_06);
        params.put("dsAcctNm07", DS_ACCT_NM_07);
        params.put("firstLineAddr01", FIRST_LINE_ADDR_01);
        params.put("firstLineAddr02", FIRST_LINE_ADDR_02);
        params.put("firstLineAddr03", FIRST_LINE_ADDR_03);
        params.put("dsActDlrCd1000", DS_ACCT_DLR_CD_1000);
        params.put("dsActDlrCd1010", DS_ACCT_DLR_CD_1010);
        params.put("dsActDlrCd1020", DS_ACCT_DLR_CD_1020);
        params.put("dsActDlrCd2000", DS_ACCT_DLR_CD_2000);
        params.put("dsActDlrCd3000", DS_ACCT_DLR_CD_3000);
        params.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("ctryCd", CTRY.UNITED_STATES_OF_AMERICA);
        params.put("dunsProcTs", bizMsg.xxDtTm_PS.getValue());
        // Mod Start 2016/10/06 QC#14861
//        params.put("dunsProcTpCd20", DUNS_PROC_TP_CD_20);
//        params.put("dunsProcStsCd10", DUNS_PROC_STS_CD_10);
        params.put("dunsProcTpCd20", DUNS_PROC_TP.RECEIVE_AND_IMOORT_DNB_FILE);
        params.put("dunsProcStsCd10", DUNS_PROC_STS.DONE);
        // Mod End 2016/10/06 QC#14861
        params.put("dunsCritTxt51", bizMsg.dunsCritDescTxt_51.getValue());
        params.put("dunsCritTxt52", bizMsg.dunsCritDescTxt_52.getValue());
        params.put("dunsCritTxt53", bizMsg.dunsCritDescTxt_53.getValue());
        params.put("dunsCritTxt54", bizMsg.dunsCritDescTxt_54.getValue());
        params.put("dunsCritTxt55", bizMsg.dunsCritDescTxt_55.getValue());
        params.put("dunsCritTxt56", bizMsg.dunsCritDescTxt_56.getValue());
        params.put("dunsCritTxt57", bizMsg.dunsCritDescTxt_57.getValue());
        params.put("dunsCritTxtArrray51", setStringArrayValue(bizMsg.dunsCritDescTxt_51.getValue()));
        params.put("dunsCritTxtArrray52", setStringArrayValue(bizMsg.dunsCritDescTxt_52.getValue()));
        params.put("dunsCritTxtArrray53", setStringArrayValue(bizMsg.dunsCritDescTxt_53.getValue()));
        params.put("dunsCritTxtArrray54", setStringArrayValue(bizMsg.dunsCritDescTxt_54.getValue()));
        params.put("dunsCritTxtArrray55", setStringArrayValue(bizMsg.dunsCritDescTxt_55.getValue()));
        params.put("dunsCritTxtArrray56", setStringArrayValue(bizMsg.dunsCritDescTxt_56.getValue()));
        params.put("dunsCritTxtArrray57", setStringArrayValue(bizMsg.dunsCritDescTxt_57.getValue()));
        // Add Start 2016/10/04 QC#14862
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunsCritDefValFlg_51.getValue())) {
            params.put("dunsCritFlg51", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunsCritDefValFlg_52.getValue())) {
            params.put("dunsCritFlg52", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunsCritDefValFlg_53.getValue())) {
            params.put("dunsCritFlg53", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunsCritDefValFlg_54.getValue())) {
            params.put("dunsCritFlg54", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunsCritDefValFlg_55.getValue())) {
            params.put("dunsCritFlg55", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunsCritDefValFlg_56.getValue())) {
            params.put("dunsCritFlg56", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dunsCritDefValFlg_57.getValue())) {
            params.put("dunsCritFlg57", ZYPConstant.FLG_ON_Y);
        }
        // Add End 2016/10/04 QC#14862
        // Add Start 2016/06/27 QC#10905
        params.put("rowNum", bizMsg.xxRowNum);
        // Add End 2016/06/27 QC#10905

        return params;
    }

    /**
     * setStringArrayValue
     * @param getValue String
     * @return String Array
     */
    public static String[] setStringArrayValue(String getValue) {
        String[] returnVlue = getValue.split(",");

        return returnVlue;
    }

    /**
     * set the global Message.
     * @param bizMsg NMAL3000CMsg
     * @param glblMsg NMAL3000SMsg
     */
    public static void updateGlblMsg(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {

        bizMsg.clearErrorInfo();
        bizMsg.A.clearErrorInfo();
        glblMsg.clearErrorInfo();
        glblMsg.A.clearErrorInfo();

        int setRowNum = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(setRowNum + i).dunsProcTpDescTxt_A, bizMsg.A.no(i).dunsProcTpDescTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(setRowNum + i).dunsFileNm_A, bizMsg.A.no(i).dunsFileNm_2);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(setRowNum + i).dunsFileLineNum_A, bizMsg.A.no(i).dunsFileLineNum);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(setRowNum + i).xxDtTm_A, bizMsg.A.no(i).xxDtTm_4);
            // Mod Start 2016/07/01 QC#11316
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(setRowNum +
            // i).dunsProcById_A, bizMsg.A.no(i).dunsProcById);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(setRowNum + i).fill103Txt_A, bizMsg.A.no(i).fill103Txt);
            // Mod Start 2016/06/20 QC#10340
            // ZYPEZDItemValueSetter.setValue(glblMsg.A.no(setRowNum +
            // i).dunsCritValTxt_A, bizMsg.A.no(i).dunsCritValTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(setRowNum + i).xxDunsProcCmntTxt_A, bizMsg.A.no(i).xxDunsProcCmntTxt);
            // Mod End 2016/06/20 QC#10340
            // Mod End 2016/07/01 QC#11316
        }
    }

    // Add Start 2016/06/15 QC#10073
    /**
     * getDunsCRITCd
     * @param glblCmpyCd String
     * @param critTxt String
     * @return tMsg
     */
    public static String getDunsCRITCd(String glblCmpyCd, String critTxt) {

        DUNS_CRITTMsg tMsg = new DUNS_CRITTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dunsCritDefValTxt, critTxt);
        tMsg = (DUNS_CRITTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (null != tMsg) {
            return tMsg.dunsCritCd.getValue();
        } else {
            return "";
        }

    }

    // Add End 2016/06/15 QC#10073

    // Add Start 2016/06/27 QC#10905
    /**
     * writeCsvFile
     * @param bizMsg NMAL2840CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NMAL2840CMsg bizMsg, ResultSet rs) throws SQLException {

        NMAL2840FMsg fMsg = new NMAL2840FMsg();
        ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // QC#16290 2016/12/19 Add Start
        // String[] csvHeader = {"S21 ACCOUNT NUMBER" //
        //         , "S21 ACCOUNT NAME" //
        String[] csvHeader = {"S21 ACCOUNT NAME" //
                , "S21 ACCOUNT NUMBER" //
        // QC#16290 2016/12/19 Add End
                , "S21 LOCATION NUMBER" //
                , "S21 ADDRESS1" //
                , "S21 ADDRESS2" //
                , "S21 CITY" //
                , "S21 STATE" //
                , "S21 POSTAL CODE" //
                , "S21 EMPLOYEE TOTAL" //
                , "S21 ANNUAL US SALES" //
                , "S21 INDUSTRY" //
                , "S21 SIC CODE" //
                , "S21 DUNS#" //
                , "S21 UDUNS" //
                , "S21 HQ DUNS#" //
                , "S21 PARENT DUNS#" //
                , "S21 DNB FILE DATE" //
                , "S21 DNB BUSINESS NAME" //
                , "S21 DNB TRADESTYLE NAME" //
                , "S21 DNB ADDRESS" //
                , "S21 DNB BEMFAB CODE" //
                , "S21 DNB LAST UPDATE DATE(MM/DD/YYYY)" //
                , "S21 COMPANY SIC CODE" //
                , "S21 COMPANY SIC DESCRIPTION" //
                , "DNB MATCH_CODE" //
                , "DNB NAME_PROFILE_CODE" //
                , "DNB STREET_NO_PROFILE_CODE" //
                , "DNB STREET_NAME_PROFILE_CODE" //
                , "DNB CONFIDENCE_CODE" //
                , "DNB MATCH_GRADE" //
                , "DNB NIXIE_A" //
                , "DNB BEMFAB" //
                , "DNB BUSINESS_NAME" //
                , "DNB TRADE_NAME" //
                , "DNB ADDRESS_LINE" //
                , "DNB CITY_NAME" //
                , "DNB STATE_ABBREVIATION" //
                , "DNB ZIP_CODE" //
                , "DNB DUNS_NUMBER" //
                , "DNB EMPLOYEES_TOTAL" //
                , "DNB ANNUAL_US_SALES" //
                , "DNB LINE_OF_BUSINESS" //
                , "DNB_SIC_CODE" //
                , "DNB GLOBAL_ULTIMATE_DUNS_NO" //
                , "DNB HEADQUARTERS_DUNS_NO" //
                , "DNB PARENT_DUNS_NO" //
                , "DNB GLOBAL_ULTIMATE_BUSINESS_NAME" //
                , "DNB COMPANY_SIC_CODE" //
                , "DNB COMPANY_SIC_DESCRIPTION" };

        fileWriter.writeHeader(csvHeader);

        // Mod Start 2016/07/04 QC#11316
        int cnt = 0;
        while (rs.next()) {
            writeCsvLine(fMsg, rs);
            fileWriter.write();

            cnt++;
            fMsg.clear();
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum_DL, BigDecimal.valueOf(cnt));
        // Mod End 2016/07/04 QC#11316

        fileWriter.close();
    }

    /**
     * writeCsvLine
     * @param fMsg NMAL2840FMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvLine(NMAL2840FMsg fMsg, ResultSet rs) throws SQLException {
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm, rs.getString("S21_ACCOUNT_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum, rs.getString("S21_ACCOUNT_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.locNum, rs.getString("S21_LOCATION_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.curFirstLineAddr, rs.getString("S21_ADDRESS1"));
        ZYPEZDItemValueSetter.setValue(fMsg.curScdLineAddr, rs.getString("S21_ADDRESS2"));
        ZYPEZDItemValueSetter.setValue(fMsg.curCtyAddr, rs.getString("S21_CITY"));
        ZYPEZDItemValueSetter.setValue(fMsg.curStCd, rs.getString("S21_STATE"));
        ZYPEZDItemValueSetter.setValue(fMsg.curPostCd, rs.getString("S21_POSTAL_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDsLocEmpNum, rs.getBigDecimal("S21_EMPLOYEE_TOTAL"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDsLocRevAmt, rs.getBigDecimal("S21_ANNUAL_US_SALES"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDsCustSicDescTxt, rs.getString("S21_INDUSTRY"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDsCustSicCd, rs.getString("S21_SIC_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDunsNum, rs.getString("S21_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDsUltDunsNum, rs.getString("S21_UDUNS"));
        ZYPEZDItemValueSetter.setValue(fMsg.curHqDunsNum, rs.getString("S21_HQ_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDsPrntDunsNum, rs.getString("S21_PARENT_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDunsProcDtTxt, NMAL2840CommonLogic.formatDate(rs.getString("S21_DNB_FILE_DATE")));
        ZYPEZDItemValueSetter.setValue(fMsg.curDunsBizNm, rs.getString("S21_DNB_BUSINESS_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDunsTradeStyleNm, rs.getString("S21_DNB_TRADESTYLE_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDunsLineAddr, rs.getString("S21_DNB_ADDRESS"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDunsActvCd, rs.getString("S21_DNB_BEMFAB_CODE"));
        // Mod Start 2016/06/20 QC#10340
        ZYPEZDItemValueSetter.setValue(fMsg.curDsLastUpdDunsDtTxt, NMAL2840CommonLogic.formatDate(rs.getString("S21_DNB_LAST_UPDATE_DATE")));
        // Mod Start 2016/06/20 QC#10340
        ZYPEZDItemValueSetter.setValue(fMsg.curDsCmpySicCd, rs.getString("S21_COMPANY_SIC_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.curDsCmpySicDescTxt, rs.getString("S21_COMPANY_SIC_DESCRIPTION"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvDunsMatchCd, rs.getString("DNB_MATCH_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvNmPrflCd, rs.getString("DNB_NM_PROFILE_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvStrNoPrflCd, rs.getString("DNB_STREET_NO_PROFILE_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvStrNmPrflCd, rs.getString("DNB_STREET_NM_PROFILE_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvDunsCnfdCd, rs.getString("DNB_CONFIDENCE_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvMatchGrdCd, rs.getString("DNB_MATCH_GRADE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvNixieACd, rs.getString("DNB_NIXIE_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvDunsActvCd, rs.getString("DNB_BEMFAB"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvDunsBizNm, rs.getString("DNB_BUSINESS_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvDunsTradeStyleNm, rs.getString("DNB_TRADE_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvDunsLineAddr, rs.getString("DNB_ADDRESS_LINE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvDunsCtyNm, rs.getString("DNB_CITY_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvDunsStCd, rs.getString("DNB_STATE_ABBREVIATION"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvPostCd, rs.getString("DNB_ZIP_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvDunsNum, rs.getString("DNB_DUNS_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvEmpTotNum, rs.getBigDecimal("DNB_EMPLOYEES_TOTAL"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvAnnSlsAmt, rs.getBigDecimal("DNB_ANNUAL_US_SALES"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvLineBizNm, rs.getString("DNB_LINE_OF_BUSINESS"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvFirstSicCd, rs.getString("DNB_SIC_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvGlblUltDunsNum, rs.getString("DNB_GLOBAL_ULTIMATE_DUNS_NO"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvHqDunsNum, rs.getString("DNB_HEADQUARTERS_DUNS_NO"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvPrntDunsNum, rs.getString("DNB_PARENT_DUNS_NO"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvGlblUltBizNm, rs.getString("DNB_GLOBAL_ULTI_BUSINESS_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvCmpySicCd, rs.getString("DNB_COMPANY_SIC_CODE"));
        ZYPEZDItemValueSetter.setValue(fMsg.rcvCmpySicDescTxt, rs.getString("DNB_COMPANY_SIC_DESCRIPTION"));
    }

    // Add End 2016/06/27 QC#10905

    // Add Start 2016/07/01 QC#11316
    /**
     * getAuditComment
     * @param rs Map<String, Object>
     * @param setComment String
     * @return setValue
     */
    public static String getAuditComment(Map<String, Object> rs, String setComment) {

        // Mod Start 2016/10/06 QC#14861
//        String codeValue = setStringValue(rs, "DUNS_CRIT_CD");
//        String flgValue = setStringValue(rs, "DUNS_CRIT_VAL_FLG");
//        String keyValue = setStringValue(rs, "DUNS_CRIT_DESC_TXT");
//        String txtValue = setStringValue(rs, "DUNS_CRIT_VAL_TXT");
        String codeValue = getStringValue(rs, "DUNS_CRIT_CD");
        String flgValue = getStringValue(rs, "DUNS_CRIT_VAL_FLG");
        String keyValue = getStringValue(rs, "DUNS_CRIT_DESC_TXT");
        String txtValue = getStringValue(rs, "DUNS_CRIT_VAL_TXT");
        // Mod End 2016/10/06 QC#14861
        String commentTxt = getCommentTxt(codeValue, flgValue, keyValue, txtValue);
        StringBuilder setCommentSB = new StringBuilder();
        setCommentSB.append(setComment);

        if (ZYPCommonFunc.hasValue(setComment) && ZYPCommonFunc.hasValue(commentTxt)) {
//            setComment += " + ";
            setCommentSB.append(" + ");
        }
//        setComment += commentTxt;
        setCommentSB.append(commentTxt);

        // Mod Start 2017/12/15 QC#20905
        //return setCommentSB.toString();
        String result = null;
        if (setCommentSB.length() > XX_DUNS_PROC_CMNT_TXT_MAX_LEN) {
            result = setCommentSB.substring(0, XX_DUNS_PROC_CMNT_TXT_MAX_LEN);
        } else {
            result = setCommentSB.toString();
        }

        return result;
        // Mod End 2017/12/15 QC#20905
    }

    /**
     * getCommentTxt
     * @param codeValue String
     * @param flgValue String
     * @param keyValue String
     * @param txtValue String
     * @return commentTxt
     */
    public static String getCommentTxt(String codeValue, String flgValue, String keyValue, String txtValue) {

        if (!ZYPCommonFunc.hasValue(codeValue) //
                && !ZYPCommonFunc.hasValue(flgValue) //
                && !ZYPCommonFunc.hasValue(keyValue) //
                && !ZYPCommonFunc.hasValue(txtValue)) {
            return "";
        }
        if (!ZYPCommonFunc.hasValue(codeValue)) {
            codeValue = MAX_CRIT_CD;
        }

        String commentTxt = "";
        if (codeValue.compareTo(CHECK_CRIT_CD) >= 0) {
            commentTxt = keyValue + ":";
            if (ZYPConstant.FLG_ON_Y.equals(flgValue)) {
                commentTxt += VALUE_IS_NULL;
            } else {
                commentTxt += txtValue;
            }
        } else {
            commentTxt = keyValue;

            // Add Start 2016/07/12 QC#9536
            if (DUNS_CRIT_DEF_VAL_FLG_34.equals(codeValue)) {
                commentTxt += txtValue;
            }
            // Add End 2016/07/12 QC#9536
        }

        return commentTxt;
    }
    // Add End 2016/07/01 QC#11316

    // Add Start 2017/12/15 QC#20905
    /**
     * @param resultMap Map<String, Object>
     * @return String
     */
    public static String getAuditCommentKey(Map<String, Object> resultMap){
        StringBuilder setCommentKeySB = new StringBuilder();

        setCommentKeySB.append(NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_PROC_TP_DESC_TXT"));
        setCommentKeySB.append(COMMA);
        setCommentKeySB.append(NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_FILE_NM"));
        setCommentKeySB.append(COMMA);
        setCommentKeySB.append(NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_FILE_LINE_NUM"));
        setCommentKeySB.append(COMMA);
        setCommentKeySB.append(NMAL2840CommonLogic.getStringValue(resultMap, "DUNS_PROC_TS"));

        return setCommentKeySB.toString();
    }
    // Add End 2017/12/15 QC#20905
}
