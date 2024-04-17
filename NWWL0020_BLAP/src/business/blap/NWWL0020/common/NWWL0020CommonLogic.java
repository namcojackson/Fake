/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0020.common;

import static business.blap.NWWL0020.constant.NWWL0020Constant.BLANK;
import static business.blap.NWWL0020.constant.NWWL0020Constant.CHK_BAN_STRING_PATTERNM;
import static business.blap.NWWL0020.constant.NWWL0020Constant.COMMA;
import static business.blap.NWWL0020.constant.NWWL0020Constant.DATE_PATTERN;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0010E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0024E;
import static business.blap.NWWL0020.constant.NWWL0020Constant.NWWM0036I;
import static business.blap.NWWL0020.constant.NWWL0020Constant.SEARCH_MODE_ALL;
import static business.blap.NWWL0020.constant.NWWL0020Constant.SEARCH_MODE_HEADER;
import static business.blap.NWWL0020.constant.NWWL0020Constant.SEARCH_MODE_SQL;
import static business.blap.NWWL0020.constant.NWWL0020Constant.SQL_CHK_WORD_LIST;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TABLE_NM_NTFY_ACT_TP;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TABLE_NM_NTFY_ATT_TP;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TABLE_NM_NTFY_BIZ_AREA_TP;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TABLE_NM_NTFY_FREQ_TP;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TABLE_NM_NTFY_INTVL_UOM_TP;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TABLE_NM_NTFY_OTPT_TP;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TABLE_NM_NTFY_SUB_AREA_TP;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TAB_NAME_HEADER;
import static business.blap.NWWL0020.constant.NWWL0020Constant.TAB_NAME_SQL;
import static business.blap.NWWL0020.constant.NWWL0020Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWWL0020.NWWL0020CMsg;
import business.blap.NWWL0020.NWWL0020Query;
import business.blap.NWWL0020.NWWL0020SMsg;
import business.blap.NWWL0020.NWWL0020_ACMsg;
import business.blap.NWWL0020.NWWL0020_BCMsg;
import business.blap.NWWL0020.NWWL0020_BSMsg;
import business.blap.NWWL0020.NtfyEmlBodyClodAccessor;
import business.blap.NWWL0020.NtfySqlClodAccessor;
import business.db.NTFY_ACT_DTLTMsg;
import business.db.NTFY_ACT_DTL_COLTMsg;
import business.db.NTFY_ACT_DTL_COLTMsgArray;
import business.db.NTFY_DIST_LISTTMsg;
import business.db.NTFY_DIST_RELNTMsg;
import business.db.NTFY_DIST_RELNTMsgArray;
import business.db.NTFY_HDRTMsg;
import business.db.NTFY_HDRTMsgArray;
import business.db.NTFY_RUN_JOBTMsg;
import business.db.NTFY_SUB_AREA_TPTMsg;
import business.db.NTFY_SUB_AREA_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_ATT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_FREQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_INTVL_UOM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.NTFY_OTPT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NWWL0020CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020CommonLogic {

    /**
     * createPulldownList
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     */
    public static void createPulldownList(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        ZYPCodeDataUtil.createPulldownList(TABLE_NM_NTFY_BIZ_AREA_TP, bizMsg.ntfyBizAreaTpCd_L0, bizMsg.ntfyBizAreaTpDescTxt_L0);

        createNtfySubAreaPulldownList(bizMsg);

        ZYPCodeDataUtil.createPulldownList(TABLE_NM_NTFY_FREQ_TP, bizMsg.ntfyFreqTpCd_L0, bizMsg.ntfyFreqTpDescTxt_L0);

        ZYPCodeDataUtil.createPulldownList(TABLE_NM_NTFY_INTVL_UOM_TP, bizMsg.ntfyIntvlUomTpCd_L0, bizMsg.ntfyIntvlUomTpDescTxt_L0);

        ZYPCodeDataUtil.createPulldownList(TABLE_NM_NTFY_ACT_TP, bizMsg.ntfyActTpCd_L0, bizMsg.ntfyActTpDescTxt_L0);
        ZYPEZDItemValueSetter.setValue(glblMsg.ntfyActTpCd_BK, NTFY_ACT_TP.MESSAGE);

        ZYPCodeDataUtil.createPulldownList(TABLE_NM_NTFY_OTPT_TP, bizMsg.ntfyOtptTpCd_L0, bizMsg.ntfyOtptTpDescTxt_L0);
        ZYPEZDItemValueSetter.setValue(glblMsg.ntfyOtptTpCd_BK, NTFY_OTPT_TP.EMAIL);

        ZYPCodeDataUtil.createPulldownList(TABLE_NM_NTFY_ATT_TP, bizMsg.ntfyAttTpCd_L0, bizMsg.ntfyAttTpDescTxt_L0);
        ZYPEZDItemValueSetter.setValue(glblMsg.ntfyAttTpCd_BK, NTFY_ATT_TP.HTML);
    }

    /**
     * Create Notification Sub Area Pull down List
     * @param bizMsg NWWL0020CMsg
     */
    public static void createNtfySubAreaPulldownList(NWWL0020CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.ntfyBizAreaTpCd_SL)) {
            ZYPCodeDataUtil.createPulldownList(TABLE_NM_NTFY_SUB_AREA_TP, bizMsg.ntfySubAreaTpCd_L0, bizMsg.ntfySubAreaTpDescTxt_L0);
        } else {

            bizMsg.ntfySubAreaTpCd_L0.clear();
            bizMsg.ntfySubAreaTpDescTxt_L0.clear();

            NTFY_SUB_AREA_TPTMsgArray subAreaList = getNtfySubAreaList(bizMsg);

            for (int i = 0; i < subAreaList.length() && i < bizMsg.ntfySubAreaTpCd_L0.length(); i++) {
                bizMsg.ntfySubAreaTpCd_L0.no(i).setValue(subAreaList.no(i).ntfySubAreaTpCd.getValue());
                bizMsg.ntfySubAreaTpDescTxt_L0.no(i).setValue(subAreaList.no(i).ntfySubAreaTpDescTxt.getValue());
            }
        }
    }

    /**
     * Get Notification Sub Area List
     * @param bizMsg NWWL0020CMsg
     * @return NTFY_SUB_AREA_TPTMsgArray
     */
    private static NTFY_SUB_AREA_TPTMsgArray getNtfySubAreaList(NWWL0020CMsg bizMsg) {
        NTFY_SUB_AREA_TPTMsg subAreaTMsg = new NTFY_SUB_AREA_TPTMsg();
        ZYPEZDItemValueSetter.setValue(subAreaTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(subAreaTMsg.ntfyBizAreaTpCd, bizMsg.ntfyBizAreaTpCd_SL);

        return (NTFY_SUB_AREA_TPTMsgArray) S21CodeTableAccessor.findByCondition(subAreaTMsg);
    }

    /**
     * Search Notification
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     */
    public static void searchNotification(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {
        NTFY_HDRTMsg ntfyHdrTMsg = new NTFY_HDRTMsg();
        ntfyHdrTMsg.setSQLID("001");
        ntfyHdrTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        ntfyHdrTMsg.setConditionValue("ntfyHdrId01", bizMsg.ntfyHdrId_H0.getValue());

        NTFY_HDRTMsgArray ntfyHdrTMsgList = (NTFY_HDRTMsgArray) EZDTBLAccessor.findByCondition(ntfyHdrTMsg);

        if (ntfyHdrTMsgList != null && 0 < ntfyHdrTMsgList.getValidCount()) {

            ntfyHdrTMsg = ntfyHdrTMsgList.no(0);

            String serchMode = null;
            String screenAplID = bizMsg.getScreenAplID();
            if (!"NWWL0020Scrn00_CMN_Submit".equals(screenAplID)) {
                serchMode = SEARCH_MODE_ALL;
            } else if (TAB_NAME_HEADER.equals(bizMsg.xxDplyTab.getValue())) {
                serchMode = SEARCH_MODE_HEADER;
            } else if (TAB_NAME_SQL.equals(bizMsg.xxDplyTab.getValue())) {
                serchMode = SEARCH_MODE_SQL;
            } else {
                serchMode = BLANK;
            }

            if (SEARCH_MODE_ALL.equals(serchMode) || SEARCH_MODE_HEADER.equals(serchMode)) {
                // Header
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyHdrNm_H0, ntfyHdrTMsg.ntfyHdrNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyHdrDescTxt_H0, ntfyHdrTMsg.ntfyHdrDescTxt);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyBizAreaTpCd_SL, ntfyHdrTMsg.ntfyBizAreaTpCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfySubAreaTpCd_SL, ntfyHdrTMsg.ntfySubAreaTpCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H0, ntfyHdrTMsg.effFromDt);
                ZYPEZDItemValueSetter.setValue(bizMsg.effThruDt_H0, ntfyHdrTMsg.effThruDt);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyHdrActvFlg_H0, ntfyHdrTMsg.ntfyHdrActvFlg);

                // Notification Periodic Detail
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyFreqTpCd_SL, ntfyHdrTMsg.ntfyFreqTpCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunDayListTxt_PD, ntfyHdrTMsg.ntfyRunDayListTxt);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunMnListTxt_PD, ntfyHdrTMsg.ntfyRunMnListTxt);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEomFlg_PD, ntfyHdrTMsg.ntfyEomFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunSunFlg_PD, ntfyHdrTMsg.ntfyRunSunFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunMonFlg_PD, ntfyHdrTMsg.ntfyRunMonFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunTueFlg_PD, ntfyHdrTMsg.ntfyRunTueFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunWedFlg_PD, ntfyHdrTMsg.ntfyRunWedFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunThuFlg_PD, ntfyHdrTMsg.ntfyRunThuFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunFriFlg_PD, ntfyHdrTMsg.ntfyRunFriFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunSatFlg_PD, ntfyHdrTMsg.ntfyRunSatFlg);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyStartHourMn_PD, ntfyHdrTMsg.ntfyStartHourMn);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEndHourMn_PD, ntfyHdrTMsg.ntfyEndHourMn);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyIntvlAot_PD, ntfyHdrTMsg.ntfyIntvlAot);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyIntvlUomTpCd_SL, ntfyHdrTMsg.ntfyIntvlUomTpCd);
                ZYPEZDItemValueSetter.setValue(bizMsg.histDaysAot_PD, ntfyHdrTMsg.histDaysAot);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyHdrPk_H0, ntfyHdrTMsg.ntfyHdrPk);
                ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_H0, ntfyHdrTMsg.ezUpTime);
                ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_H0, ntfyHdrTMsg.ezUpTimeZone);

                // Back Up
                ZYPEZDItemValueSetter.setValue(bizMsg.xxEndDplyTmTxt_PB, bizMsg.xxEndDplyTmTxt);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyRunMnListTxt_PB, bizMsg.ntfyRunMnListTxt_PD);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyIntvlAot_PB, bizMsg.ntfyIntvlAot_PD);
                ZYPEZDItemValueSetter.setValue(bizMsg.ntfyIntvlUomTpCd_PB, bizMsg.ntfyIntvlUomTpCd_SL);

                // SMsg Header
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyHdrNm_H0, ntfyHdrTMsg.ntfyHdrNm);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyHdrDescTxt_H0, ntfyHdrTMsg.ntfyHdrDescTxt);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyBizAreaTpCd_SL, ntfyHdrTMsg.ntfyBizAreaTpCd);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfySubAreaTpCd_SL, ntfyHdrTMsg.ntfySubAreaTpCd);
                ZYPEZDItemValueSetter.setValue(glblMsg.effFromDt_H0, ntfyHdrTMsg.effFromDt);
                ZYPEZDItemValueSetter.setValue(glblMsg.effThruDt_H0, ntfyHdrTMsg.effThruDt);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyHdrActvFlg_H0, ntfyHdrTMsg.ntfyHdrActvFlg);

                // SMsg Notification Periodic Detail
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyFreqTpCd_SL, ntfyHdrTMsg.ntfyFreqTpCd);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyRunDayListTxt_PD, ntfyHdrTMsg.ntfyRunDayListTxt);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyRunMnListTxt_PD, ntfyHdrTMsg.ntfyRunMnListTxt);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyEomFlg_PD, ntfyHdrTMsg.ntfyEomFlg);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyRunSunFlg_PD, ntfyHdrTMsg.ntfyRunSunFlg);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyRunMonFlg_PD, ntfyHdrTMsg.ntfyRunMonFlg);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyRunTueFlg_PD, ntfyHdrTMsg.ntfyRunTueFlg);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyRunWedFlg_PD, ntfyHdrTMsg.ntfyRunWedFlg);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyRunThuFlg_PD, ntfyHdrTMsg.ntfyRunThuFlg);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyRunFriFlg_PD, ntfyHdrTMsg.ntfyRunFriFlg);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyRunSatFlg_PD, ntfyHdrTMsg.ntfyRunSatFlg);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyStartHourMn_PD, ntfyHdrTMsg.ntfyStartHourMn);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyEndHourMn_PD, ntfyHdrTMsg.ntfyEndHourMn);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyIntvlAot_PD, ntfyHdrTMsg.ntfyIntvlAot);
                ZYPEZDItemValueSetter.setValue(glblMsg.ntfyIntvlUomTpCd_SL, ntfyHdrTMsg.ntfyIntvlUomTpCd);
                ZYPEZDItemValueSetter.setValue(glblMsg.histDaysAot_PD, ntfyHdrTMsg.histDaysAot);

                if (ZYPCommonFunc.hasValue(bizMsg.ntfyEndHourMn_PD)) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRptChkFlg_PD, ZYPConstant.CHKBOX_ON_Y);
                    ZYPEZDItemValueSetter.setValue(bizMsg.xxRptChkFlg_PB, ZYPConstant.CHKBOX_ON_Y);
                } else {
                    bizMsg.xxRptChkFlg_PD.clear();
                    bizMsg.xxRptChkFlg_PB.clear();
                }

                // Run Job
                NTFY_RUN_JOBTMsg ntfyRunJobTMsg = new NTFY_RUN_JOBTMsg();
                ZYPEZDItemValueSetter.setValue(ntfyRunJobTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(ntfyRunJobTMsg.ntfyHdrPk, bizMsg.ntfyHdrPk_H0);

                ntfyRunJobTMsg = (NTFY_RUN_JOBTMsg) EZDTBLAccessor.findByKey(ntfyRunJobTMsg);

                if (ntfyRunJobTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_JB, ntfyRunJobTMsg.ezUpTime);
                    ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_JB, ntfyRunJobTMsg.ezUpTimeZone);
                }
            }

            if (SEARCH_MODE_ALL.equals(serchMode) || SEARCH_MODE_SQL.equals(serchMode)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxNtfySqlTxt, new NtfySqlClodAccessor(ntfyHdrTMsg).getNtfySqlTxt());
            }

        } else {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {ntfyHdrTMsg.getTableName() });
            return;
        }
    }

    /**
     * Search Notification Action List
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     */
    public static void searchNotificationActionList(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        // Search Action
        S21SsmEZDResult ssmResult = NWWL0020Query.getInstance().getNtfyActionList(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {
            return;
        } else {
            glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            EZDMsg.copy(glblMsg.A, null, bizMsg.A, null);
            bizMsg.xxRadioBtn_A0.setValue(0);
        }
    }

    /**
     * Search Notification
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     */
    public static void searchNotificationActionDetail(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        clearActionDetailData(bizMsg, glblMsg);

        if (!ZYPCommonFunc.hasValue(bizMsg.ntfyActDtlPk)) {
            return;
        }

        // Action Detail
        NTFY_ACT_DTLTMsg ntfyActDtlTMsg = new NTFY_ACT_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(ntfyActDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(ntfyActDtlTMsg.ntfyActDtlPk, bizMsg.ntfyActDtlPk);

        ntfyActDtlTMsg = (NTFY_ACT_DTLTMsg) EZDTBLAccessor.findByKey(ntfyActDtlTMsg);

        if (ntfyActDtlTMsg != null) {

            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyActNm, ntfyActDtlTMsg.ntfyActNm);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyActDescTxt, ntfyActDtlTMsg.ntfyActDescTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyActTpCd_SL, ntfyActDtlTMsg.ntfyActTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyOtptTpCd_SL, ntfyActDtlTMsg.ntfyOtptTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlRpyToAddr, ntfyActDtlTMsg.ntfyEmlRpyToAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlToAddr, ntfyActDtlTMsg.ntfyEmlToAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlCcAddr, ntfyActDtlTMsg.ntfyEmlCcAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlBccAddr, ntfyActDtlTMsg.ntfyEmlBccAddr);
            ZYPEZDItemValueSetter.setValue(bizMsg.rtrvToAddrFromSqlFlg, ntfyActDtlTMsg.rtrvToAddrFromSqlFlg);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyAttTpCd_SL, ntfyActDtlTMsg.ntfyAttTpCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyEmlSubjTxt, ntfyActDtlTMsg.ntfyEmlSubjTxt);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_DT, ntfyActDtlTMsg.ezUpTime);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_DT, ntfyActDtlTMsg.ezUpTimeZone);

            ZYPEZDItemValueSetter.setValue(bizMsg.xxNtfyEmlBodyTxt, new NtfyEmlBodyClodAccessor(ntfyActDtlTMsg).getNtfyMailTxt());

            // Back Up
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyActNm_BK, bizMsg.ntfyActNm);
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyActDescTxt_BK, bizMsg.ntfyActDescTxt);
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyActTpCd_BK, bizMsg.ntfyActTpCd_SL);
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyOtptTpCd_BK, bizMsg.ntfyOtptTpCd_SL);
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyEmlRpyToAddr_BK, bizMsg.ntfyEmlRpyToAddr);
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyEmlToAddr_BK, bizMsg.ntfyEmlToAddr);
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyEmlCcAddr_BK, bizMsg.ntfyEmlCcAddr);
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyEmlBccAddr_BK, bizMsg.ntfyEmlBccAddr);
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyAttTpCd_BK, bizMsg.ntfyAttTpCd_SL);
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyEmlSubjTxt_BK, bizMsg.ntfyEmlSubjTxt);

            if (ZYPConstant.FLG_OFF_N.equals(bizMsg.rtrvToAddrFromSqlFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(glblMsg.rtrvToAddrFromSqlFlg_BK, BLANK);
            } else {
                ZYPEZDItemValueSetter.setValue(glblMsg.rtrvToAddrFromSqlFlg_BK, bizMsg.rtrvToAddrFromSqlFlg);
            }

        } else {
            bizMsg.setMessageInfo(ZZZM9006E, new String[] {"NTFY_ACT_DTL" });
            return;
        }

        // Distribution List
        NTFY_DIST_RELNTMsg ntfyDistRelnTMsg = new NTFY_DIST_RELNTMsg();
        ntfyDistRelnTMsg.setSQLID("001");
        ntfyDistRelnTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        ntfyDistRelnTMsg.setConditionValue("ntfyActDtlPk01", bizMsg.ntfyActDtlPk.getValue());

        NTFY_DIST_RELNTMsgArray ntfyDistRelnTMsgList = (NTFY_DIST_RELNTMsgArray) EZDTBLAccessor.findByCondition(ntfyDistRelnTMsg);

        if (ntfyDistRelnTMsgList != null && 0 < ntfyDistRelnTMsgList.getValidCount()) {

            StringBuilder sbDistList = new StringBuilder();

            for (int i = 0; i < ntfyDistRelnTMsgList.getValidCount(); i++) {

                NTFY_DIST_LISTTMsg ntfyDistListTMsg = new NTFY_DIST_LISTTMsg();
                ZYPEZDItemValueSetter.setValue(ntfyDistListTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(ntfyDistListTMsg.ntfyDistListPk, ntfyDistRelnTMsgList.no(i).ntfyDistListPk);

                ntfyDistListTMsg = (NTFY_DIST_LISTTMsg) EZDTBLAccessor.findByKey(ntfyDistListTMsg);

                if (ntfyDistListTMsg != null) {
                    if (i != 0) {
                        sbDistList.append(",");
                    }
                    sbDistList.append(ntfyDistListTMsg.ntfyDistListNm.getValue());
                }
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.ntfyDistListNmListTxt, sbDistList.toString());
            ZYPEZDItemValueSetter.setValue(glblMsg.ntfyDistListNmListTxt_BK, sbDistList.toString());
        }

        // #of Column
        NTFY_ACT_DTL_COLTMsg ntfyActDtlColTMsg = new NTFY_ACT_DTL_COLTMsg();
        ntfyActDtlColTMsg.setSQLID("001");
        ntfyActDtlColTMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        ntfyActDtlColTMsg.setConditionValue("ntfyActDtlPk01", bizMsg.ntfyActDtlPk.getValue());

        NTFY_ACT_DTL_COLTMsgArray ntfyActDtlTMsgList = (NTFY_ACT_DTL_COLTMsgArray) EZDTBLAccessor.findByCondition(ntfyActDtlColTMsg);

        if (ntfyActDtlTMsgList != null && 0 < ntfyActDtlTMsgList.getValidCount()) {

            for (int i = 0; i < ntfyActDtlTMsgList.getValidCount(); i++) {

                ntfyActDtlColTMsg = ntfyActDtlTMsgList.no(i);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ntfyActDtlColPk_B0, ntfyActDtlColTMsg.ntfyActDtlColPk);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ezUpTime_B0, ntfyActDtlColTMsg.ezUpTime);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ezUpTimeZone_B0, ntfyActDtlColTMsg.ezUpTimeZone);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).ntfyActDtlColSortNum_B0, ntfyActDtlColTMsg.ntfyActDtlColSortNum);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).hdrLbNm_B0, ntfyActDtlColTMsg.hdrLbNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).placeHldNm_B0, ntfyActDtlColTMsg.placeHldNm);
            }
            bizMsg.B.setValidCount(ntfyActDtlTMsgList.getValidCount());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxNum, BigDecimal.valueOf(ntfyActDtlTMsgList.getValidCount()));
        }
        EZDMsg.copy(bizMsg.B, null, glblMsg.B, null);
    }

    /**
     * Set Changed Warning
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     * @return boolean
     */
    public static boolean setChangedWarning(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxDtlProtFlg.getValue())) {
            return false;
        }

        if (!ZYPConstant.FLG_ON_Y.equals(bizMsg.xxWrnSkipFlg.getValue())) {

            if (isChangeActionDetailData(bizMsg, glblMsg)) {
                bizMsg.setMessageInfo(NWWM0036I, new String[] {"Notification Action Detail" });
                return true;
            }
        }
        return false;
    }

    /**
     * Is Change Action Detail Data
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     * @return boolean
     */
    public static boolean isChangeActionDetailData(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        if (!bizMsg.ntfyActNm.getValue().equals(glblMsg.ntfyActNm_BK.getValue()) || !bizMsg.ntfyActDescTxt.getValue().equals(glblMsg.ntfyActDescTxt_BK.getValue())
                || !bizMsg.ntfyActTpCd_SL.getValue().equals(glblMsg.ntfyActTpCd_BK.getValue()) || !bizMsg.ntfyOtptTpCd_SL.getValue().equals(glblMsg.ntfyOtptTpCd_BK.getValue())
                || !bizMsg.ntfyEmlRpyToAddr.getValue().equals(glblMsg.ntfyEmlRpyToAddr_BK.getValue()) || !bizMsg.ntfyEmlToAddr.getValue().equals(glblMsg.ntfyEmlToAddr_BK.getValue())
                || !bizMsg.ntfyEmlCcAddr.getValue().equals(glblMsg.ntfyEmlCcAddr_BK.getValue()) || !bizMsg.ntfyEmlBccAddr.getValue().equals(glblMsg.ntfyEmlBccAddr_BK.getValue())
                || !bizMsg.rtrvToAddrFromSqlFlg.getValue().equals(glblMsg.rtrvToAddrFromSqlFlg_BK.getValue()) || !bizMsg.ntfyAttTpCd_SL.getValue().equals(glblMsg.ntfyAttTpCd_BK.getValue())
                || !bizMsg.ntfyDistListNmListTxt.getValue().equals(glblMsg.ntfyDistListNmListTxt_BK.getValue()) || !bizMsg.ntfyEmlSubjTxt.getValue().equals(glblMsg.ntfyEmlSubjTxt_BK.getValue())) {

            return true;
        }

        // Action Detail
        String mailTxt = BLANK;
        if (ZYPCommonFunc.hasValue(bizMsg.ntfyActDtlPk)) {
            NTFY_ACT_DTLTMsg ntfyActDtlTMsg = new NTFY_ACT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(ntfyActDtlTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(ntfyActDtlTMsg.ntfyActDtlPk, bizMsg.ntfyActDtlPk);
            mailTxt = new NtfyEmlBodyClodAccessor(ntfyActDtlTMsg).getNtfyMailTxt();
        }

        if (!bizMsg.xxNtfyEmlBodyTxt.getValue().equals(mailTxt)) {
            return true;
        }

        if (isChangeActionDetailColumnData(bizMsg, glblMsg)) {
            return true;
        }

        return false;
    }

    /**
     * Is Change Action Detail Column Data
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     * @return boolean
     */
    public static boolean isChangeActionDetailColumnData(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWWL0020_BCMsg bcMsg = bizMsg.B.no(i);

            if (!ZYPCommonFunc.hasValue(bcMsg.ntfyActDtlColPk_B0)) {
                return true;
            }

            for (int j = 0; j < glblMsg.B.getValidCount(); j++) {

                NWWL0020_BSMsg bsMsg = glblMsg.B.no(j);

                if (isEqualsBigDecimalValue(bcMsg.ntfyActDtlColPk_B0, bsMsg.ntfyActDtlColPk_B0)) {

                    if (!isEqualsBigDecimalValue(bcMsg.ntfyActDtlColSortNum_B0, bsMsg.ntfyActDtlColSortNum_B0) || !bcMsg.hdrLbNm_B0.getValue().equals(bsMsg.hdrLbNm_B0.getValue())
                            || !bcMsg.placeHldNm_B0.getValue().equals(bsMsg.placeHldNm_B0.getValue())) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Is Contains Blank Action List
     * @param bizMsg NWWL0020CMsg
     * @return boolean
     */
    public static boolean isContainsBlankActionList(NWWL0020CMsg bizMsg) {

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWWL0020_ACMsg acMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(acMsg.ntfyActId_A0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clear Notification Data
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     */
    public static void clearNtfyData(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        bizMsg.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.A.clear();
        bizMsg.B.setValidCount(0);
        bizMsg.B.clear();

        glblMsg.clear();
        glblMsg.A.setValidCount(0);
        glblMsg.A.clear();
        glblMsg.B.setValidCount(0);
        glblMsg.B.clear();
    }

    /**
     * Clear Action Detail Data
     * @param bizMsg NWWL0020CMsg
     * @param glblMsg NWWL0020SMsg
     */
    public static void clearActionDetailData(NWWL0020CMsg bizMsg, NWWL0020SMsg glblMsg) {

        bizMsg.ntfyActNm.clear();
        bizMsg.ntfyActDescTxt.clear();
        bizMsg.ntfyActTpCd_SL.clear();
        bizMsg.ntfyOtptTpCd_SL.clear();
        bizMsg.ntfyEmlRpyToAddr.clear();
        bizMsg.ntfyEmlToAddr.clear();
        bizMsg.ntfyEmlCcAddr.clear();
        bizMsg.ntfyEmlBccAddr.clear();
        bizMsg.rtrvToAddrFromSqlFlg.clear();
        bizMsg.ntfyAttTpCd_SL.clear();
        bizMsg.ntfyDistListNmListTxt.clear();
        bizMsg.ntfyEmlSubjTxt.clear();
        bizMsg.xxNtfyEmlBodyTxt.clear();
        bizMsg.xxNum.clear();
        bizMsg.B.setValidCount(0);
        bizMsg.B.clear();

        glblMsg.ntfyActNm_BK.clear();
        glblMsg.ntfyActDescTxt_BK.clear();
        glblMsg.ntfyActTpCd_BK.clear();
        glblMsg.ntfyOtptTpCd_BK.clear();
        glblMsg.ntfyEmlRpyToAddr_BK.clear();
        glblMsg.ntfyEmlToAddr_BK.clear();
        glblMsg.ntfyEmlCcAddr_BK.clear();
        glblMsg.ntfyEmlBccAddr_BK.clear();
        glblMsg.rtrvToAddrFromSqlFlg_BK.clear();
        glblMsg.ntfyAttTpCd_BK.clear();
        glblMsg.ntfyDistListNmListTxt_BK.clear();
        glblMsg.ntfyEmlSubjTxt_BK.clear();
        glblMsg.xxNum_BK.clear();
        glblMsg.B.setValidCount(0);
        glblMsg.B.clear();

        ZYPEZDItemValueSetter.setValue(glblMsg.ntfyActTpCd_BK, NTFY_ACT_TP.MESSAGE);
        ZYPEZDItemValueSetter.setValue(glblMsg.ntfyOtptTpCd_BK, NTFY_OTPT_TP.EMAIL);
        ZYPEZDItemValueSetter.setValue(glblMsg.ntfyAttTpCd_BK, NTFY_ATT_TP.HTML);
    }

    /**
     * Check SQL Statement
     * @param xxRptSqlTxt EZDCStringItem
     * @return boolean
     */
    public static boolean checkSqlStatement(EZDCStringItem xxRptSqlTxt) {

        final String sqlIgnoredMsg = NWWL0020Query.getInstance().checkSqlStatement(xxRptSqlTxt.getValue());

        if (ZYPCommonFunc.hasValue(sqlIgnoredMsg)) {
            xxRptSqlTxt.setErrorInfo(1, NWWM0024E, new String[] {sqlIgnoredMsg });
            return false;
        }

        return true;
    }

    /**
     * Check Prohibited Char
     * @param bizMsg NWWL0020CMsg
     * @return boolean
     */
    public static boolean checkProhibitedChar(NWWL0020CMsg bizMsg) {

        String sqlTxt = bizMsg.xxNtfySqlTxt.getValue().toUpperCase();
        StringBuilder pattern = null;

        for (String chkWord : SQL_CHK_WORD_LIST) {

            pattern = new StringBuilder();
            pattern.append(CHK_BAN_STRING_PATTERNM);
            pattern.append(chkWord);
            pattern.append(CHK_BAN_STRING_PATTERNM);

            Pattern pat = Pattern.compile(pattern.toString());
            Matcher mat = pat.matcher(sqlTxt);

            if (mat.find()) {
                bizMsg.xxNtfySqlTxt.setErrorInfo(1, NWWM0010E, new String[] {chkWord });
                return false;
            }
        }

        return true;
    }

    /**
     * Get Next Run TimeStamp
     * @param ntfyHdr Map<String, Object>
     * @return String
     * @throws ParseException ParseException
     */
    public static String getNextRunTs(NTFY_HDRTMsg ntfyHdr) throws ParseException {
        String nowTs = ZYPDateUtil.getCurrentSystemTime(DATE_PATTERN);
        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_PATTERN);
        Calendar cal = Calendar.getInstance();
        Date date = new Date(dateformat.parse(nowTs).getTime());
        cal.setTime(date);

        if (NTFY_FREQ_TP.DAILY.equals(ntfyHdr.ntfyFreqTpCd.getValue())) {
            String endHM = (String) ntfyHdr.ntfyEndHourMn.getValue();
            String startHM = (String) ntfyHdr.ntfyStartHourMn.getValue();
            if (ZYPCommonFunc.hasValue(ntfyHdr.ntfyIntvlAot)) {
                int intvl = ntfyHdr.ntfyIntvlAot.getValueInt();

                // add min
                if (NTFY_INTVL_UOM_TP.MINUTES.equals(ntfyHdr.ntfyIntvlUomTpCd.getValue())) {
                    cal.add(Calendar.MINUTE, intvl);
                } else {
                    // add hour
                    cal.add(Calendar.HOUR_OF_DAY, intvl);
                }

                // add result HHmm
                String hourMin = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + String.format("%02d", cal.get(Calendar.MINUTE));

                if (endHM.compareTo(hourMin) < 0) {
                    cal.add(Calendar.DATE, 1);
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

                } else if (startHM.compareTo(hourMin) > 0) {
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

                }
            } else if (ZYPCommonFunc.hasValue(ntfyHdr.ntfyRunMnListTxt)) {
                String runMnTxt = (String) ntfyHdr.ntfyRunMnListTxt.getValue();

                List<String> runMnList = new ArrayList<String>();
                if (ZYPCommonFunc.hasValue(runMnTxt)) {
                    runMnList.addAll(Arrays.asList(runMnTxt.split(COMMA)));
                }

                Collections.sort(runMnList);
                int mn = cal.get(Calendar.MINUTE);

                // next Hour
                if (mn == Integer.parseInt(Collections.max(runMnList)) || mn > Integer.parseInt(Collections.max(runMnList))) {
                    cal.add(Calendar.HOUR_OF_DAY, 1);
                    cal.set(Calendar.MINUTE, Integer.parseInt(Collections.min(runMnList)));

                } else {

                    // next Minute
                    for (String runMn : runMnList) {
                        int runMnInt = Integer.parseInt(runMn);
                        if (mn > runMnInt || mn == runMnInt) {
                            continue;
                        }
                        cal.set(Calendar.MINUTE, Integer.parseInt(runMn));
                        break;
                    }
                }

                // add result HHmm
                String hourMin = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + String.format("%02d", cal.get(Calendar.MINUTE));

                if (endHM.compareTo(hourMin) < 0) {
                    cal.add(Calendar.DATE, 1);
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

                } else if (startHM.compareTo(hourMin) > 0) {
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                    cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

                }

            } else {

                // add result HHmm
                String hourMin = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + String.format("%02d", cal.get(Calendar.MINUTE));

                if (startHM.compareTo(hourMin) <= 0) {
                    cal.add(Calendar.DATE, 1);
                }

                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
                cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));
            }

        } else if (NTFY_FREQ_TP.MONTHLY.equals(ntfyHdr.ntfyFreqTpCd.getValue())) {
            String runDayTxt = (String) ntfyHdr.ntfyRunDayListTxt.getValue();

            List<String> runDayList = new ArrayList<String>();
            if (ZYPCommonFunc.hasValue(runDayTxt)) {
                runDayList.addAll(Arrays.asList(runDayTxt.split(COMMA)));
            }

            Collections.sort(runDayList);
            int day = cal.get(Calendar.DATE);
            int lastDay = cal.getActualMaximum(Calendar.DATE);

            List<String> runDayListSub = new ArrayList<String>();
            runDayListSub.addAll(runDayList);

            // remove day
            if (ZYPCommonFunc.hasValue(runDayTxt)) {
                for (String runDay : runDayList) {
                    if (Integer.parseInt(runDay) > lastDay) {
                        runDayList.remove(runDay);
                    }
                }
            }

            // EOM Flag
            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.ntfyEomFlg.getValue())) {
                if (!runDayList.contains(String.valueOf(lastDay))) {
                    runDayList.add(String.valueOf(lastDay));
                }
            }

            // next Month
            if (runDayList.size() < 0 || day == Integer.parseInt(Collections.max(runDayList)) || day > Integer.parseInt(Collections.max(runDayList))) {
                cal.add(Calendar.MONTH, 1);

                if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.ntfyEomFlg.getValue())) {
                    if (!runDayListSub.contains(cal.getActualMaximum(Calendar.DATE))) {
                        runDayListSub.add(String.valueOf(cal.getActualMaximum(Calendar.DATE)));
                    }
                }

                if (Integer.parseInt(Collections.min(runDayListSub)) < cal.getActualMaximum(Calendar.DATE)) {
                    cal.set(Calendar.DATE, Integer.parseInt(Collections.min(runDayListSub)));

                } else {
                    cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                }

            } else {

                // next Day
                for (String runDay : runDayList) {
                    int runDayInt = Integer.parseInt(runDay);
                    if (day > runDayInt || day == runDayInt) {
                        continue;
                    }
                    cal.set(Calendar.DATE, Integer.parseInt(runDay));
                    break;
                }
            }

            String startHM = (String) ntfyHdr.ntfyStartHourMn.getValue();

            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

        } else if (NTFY_FREQ_TP.WEEKLY.equals(ntfyHdr.ntfyFreqTpCd.getValue())) {
            int week = cal.get(Calendar.DAY_OF_WEEK);
            List<Integer> weekList = new ArrayList<Integer>();

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.ntfyRunSunFlg.getValue())) {
                weekList.add(Calendar.SUNDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.ntfyRunMonFlg.getValue())) {
                weekList.add(Calendar.MONDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.ntfyRunTueFlg.getValue())) {
                weekList.add(Calendar.TUESDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.ntfyRunWedFlg.getValue())) {
                weekList.add(Calendar.WEDNESDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.ntfyRunThuFlg.getValue())) {
                weekList.add(Calendar.THURSDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.ntfyRunFriFlg.getValue())) {
                weekList.add(Calendar.FRIDAY);
            }

            if (ZYPConstant.FLG_ON_Y.equals(ntfyHdr.ntfyRunSatFlg.getValue())) {
                weekList.add(Calendar.SATURDAY);
            }

            // next Week
            if (week == Collections.max(weekList) || week > Collections.max(weekList)) {
                cal.add(Calendar.DATE, Calendar.SATURDAY - week + Collections.min(weekList));
            } else {

                // next Day
                for (int weekInt : weekList) {

                    if (week > weekInt || week == weekInt) {
                        continue;
                    }
                    cal.add(Calendar.DATE, weekInt - week);
                    break;
                }
            }

            String startHM = (String) ntfyHdr.ntfyStartHourMn.getValue();

            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHM.substring(0, 2)));
            cal.set(Calendar.MINUTE, Integer.parseInt(startHM.substring(2, 4)));

        }

        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 000);

        return dateformat.format(cal.getTime());
    }

    /**
     * Is Equals BigDecimal Value
     * @param origItem EZDCBigDecimalItem
     * @param preItem EZDCBigDecimalItem
     * @return boolean
     */
    public static boolean isEqualsBigDecimalValue(EZDCBigDecimalItem origItem, EZDSBigDecimalItem preItem) {

        if (!ZYPCommonFunc.hasValue(origItem) && !ZYPCommonFunc.hasValue(preItem)) {
            return true;
        } else if (ZYPCommonFunc.hasValue(origItem) && !ZYPCommonFunc.hasValue(preItem)) {
            return false;
        } else if (!ZYPCommonFunc.hasValue(origItem) && ZYPCommonFunc.hasValue(preItem)) {
            return false;
        } else if (origItem.getValue().compareTo(preItem.getValue()) == 0) {
            return true;
        }
        return false;
    }
}
