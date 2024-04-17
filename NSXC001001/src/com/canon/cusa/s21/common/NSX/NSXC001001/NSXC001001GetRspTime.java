/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CALTMsg;
import business.db.CALTMsgArray;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_PRC_SHIFTTMsg;
import business.db.SVC_PRC_SHIFTTMsgArray;
import business.db.SVC_TERM_CONDTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.SVC_COV_FEAT;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Get Response Time
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/11/01   Hitachi         T.Yonekura      Create          N/A
 * 08/27/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 10/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 12/31/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 02/10/2016   Hitachi         Y.Takeno        Update          QC#3723
 * 02/17/2016   Hitachi         T.Iwamoto       Update          QC#3723
 * 02/18/2016   Hitachi         Y.Takeno        Update          QC#3723
 * 2016/03/10   Hitachi         K.Yamada        Update          CSA QC#5225
 * 04/15/2016   Hitachi         T.Iwamoto       Update          QC#5486
 * 05/16/2016   Hitachi         T.Iwamoto       Update          QC#8219
 * 05/17/2016   Hitachi         K.Kasai         Update          QC#447
 * 09/07/2016   Hitachi         A.Kohinata      Update          QC#13661
 * 11/01/2016   Hitachi         Y.Takeno        Update          QC#15658
 * 2017/04/21   Hitachi         K.Kitachi       Update          QC#18117
 * 2017/08/01   Hitachi         K.Kitachi       Update          QC#20026
 * 2017/10/02   Hitachi         T.Tomita        Update          QC#21273
 * 2018/05/25   Hitachi         K.Kim           Update          QC#15410(Sol#246)
 * 2018/07/25   Hitachi         T.Tomita        Update          QC#27414
 * 2018/08/01   Hitachi         K.Kitachi       Update          QC#27554
 * 2022/04/11   Hitachi         K.Kitachi       Update          QC#59899
 * 2023/05/24   Hitachi         T.Nagae         Update          CSA-QC#61365
 * 2023/06/14   Hitachi         K.Kitachi       Update          QC#61365
 *</pre>
 */
public class NSXC001001GetRspTime {

    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NSXC001001GetRspTime.class);

    /** DATE format for methods of this class */
    private static final String DATE_FORMAT = S21CalendarUtilConstants.TYPE_YYYYMMDD;

    /** Format Date Time */
    private static final String FORMAT_SYS_DT_TM = "yyyyMMddHHmmss";

    /** Format Time */
    private static final String FORMAT_TM = "HHmmss";

//NA#ASCC CLICK BugFix Add Start
    /** 000000 */
    private static final String TM_START_OFTHEDAY = "000000";

    /** 240000 */
    private static final String TM_END_OFTHEDAY = "240000";
//NA#ASCC CLICK BugFix Add End

    // add start 2016/09/07 CSA Defect#13661
    /** 235959 */
    private static final String TM_235959 = "235959";
    // add end 2016/09/07 CSA Defect#13661

    /** 60 */
    private static final BigDecimal MN_60 = new BigDecimal("60");

    // START 2017/04/21 K.Kitachi [QC#18117, ADD]
    /** UTC */
    private static final String ID_UTC = "UTC";
    // END 2017/04/21 K.Kitachi [QC#18117, ADD]

// START 02/10/2016 Y.Takeno [QC#3727, ADD]

    /**
     * SVC_TERM_COND_DATA_FROM_TM
     */
    private static final String SVC_TERM_COND_DATA_FROM_TM = "SVC_TERM_COND_DATA_FROM_TM";

    /**
     * SVC_TERM_COND_DATA_THRU_TM
     */
    private static final String SVC_TERM_COND_DATA_THRU_TM = "SVC_TERM_COND_DATA_THRU_TM";

// END   02/10/2016 Y.Takeno [QC#3727, ADD]
// START 02/18/2016 Y.Takeno [QC#3727, ADD]

    /**
     * VAR_CHAR_CONST(ATTR_NM) : SVC_TERM_COND_ATTRB_NM_RSP_TM_COMIT
     */
    private static final String TERM_COND_RSP_TM_COMIT = "TERM_COND_RSP_TM_COMIT";

    /**
     * VAR_CHAR_CONST(ATTR_NM) : SVC_TERM_COND_ATTRB_NM_AFTER_HOURS_WRK_PGM
     */
    private static final String TERM_COND_AFTER_HOURS_WRK_PGM = "TERM_COND_AFTER_HOURS_WRK_PGM";

// END 02/18/2016 Y.Takeno [QC#3727, ADD]

    // add start 2016/04/15 CSA Defect#
    /** STD_TIME_FROM */
    private static final String STD_TIME_FROM = "STD_TIME_FROM";

    /** STD_TIME_THRU */
    private static final String STD_TIME_THRU = "STD_TIME_THRU";
    // add end 2016/04/15 CSA Defect#
    // add start 2016/05/17 CSA Defect#447
    /** Input parameter "Global Company Code" is a mandatory field. */
    private static final String NSXM0003E = "NSXM0003E";

    /** Input parameter "DS Contract Detail PK" is a mandatory field. */
    private static final String NSXM0106E = "NSXM0106E";

    /** Input parameter "SLA Date" is a mandatory field. */
    private static final String NSXM0107E = "NSXM0107E";

    /** The date format is not YYYYMMDD. Please check the format of "SLA Date". */
    private static final String NSXM0108E = "NSXM0108E";
    // add end 2016/05/17 CSA Defect#447

    /**
     * getRspTime
     * @param glblCmpyCd String
     * @param fromDt String
     * @param fromTm String
     * @param toDt String
     * @param toTm String
     * @param availableHourFrom String
     * @param availableHourTo String
     * @return
     */
    public static Long getRspTime(String glblCmpyCd, String fromDt, String fromTm, String toDt, String toTm, String availableHourFrom, String availableHourTo) {

        AvailableHourBean infoBean = inputCheck(glblCmpyCd, fromDt, fromTm, toDt, toTm, availableHourFrom, availableHourTo);

        if (infoBean == null) {
            return null;
        }

        boolean SameDateFlg = false;
        boolean nextDateFlg = false;

        // Same Date Check
        if (infoBean.getFromDt().compareTo(infoBean.getToDt()) == 0) {
            SameDateFlg = true;
        }
        // Next Date Check
        String nextDate = addDays(infoBean.getFromDt(), 1);
        String prevDate = addDays(infoBean.getToDt(), -1);
        if (nextDate.compareTo(infoBean.getToDt()) == 0 && prevDate.compareTo(infoBean.getFromDt()) == 0) {
            nextDateFlg = true;
        }

        String availableDtTmFrom = infoBean.getFromDt() + infoBean.getAvailableHourFrom();
        String availableDtTmTo = infoBean.getToDt() + infoBean.getAvailableHourTo();

        String calcDtTmFrom = null;

        if (infoBean.getFromDtTm().compareTo(availableDtTmFrom) > 0) {
            calcDtTmFrom = infoBean.getFromDtTm();
        } else {
            calcDtTmFrom = availableDtTmFrom;
        }

        String calcDtTmTo = null;

        if (infoBean.getToDtTm().compareTo(availableDtTmTo) > 0) {
            calcDtTmTo = availableDtTmTo;
        } else {
            calcDtTmTo = infoBean.getToDtTm();
        }

        Date fromDateTime = toDate(calcDtTmFrom, "yyyyMMddHHmmss");
        Date toDateTime = toDate(calcDtTmTo, "yyyyMMddHHmmss");

        Long diffTime = 0L;
        if (toDateTime != null && fromDateTime != null) {
            diffTime = toDateTime.getTime() - fromDateTime.getTime();
        } else {
            return null;
        }

        // Same Date
        if (SameDateFlg) {
            return diffTime;
        }

        fromDateTime = toDate(calcDtTmFrom, "yyyyMMddHHmmss");
        toDateTime = toDate(infoBean.getFromDt() + infoBean.getAvailableHourTo(), "yyyyMMddHHmmss");

        Long fromDiffTime = 0L;

        if (toDateTime != null && fromDateTime != null) {
            fromDiffTime = toDateTime.getTime() - fromDateTime.getTime();
        } else {
            return null;
        }

        fromDateTime = toDate(infoBean.getToDt() + infoBean.getAvailableHourFrom(), "yyyyMMddHHmmss");
        toDateTime = toDate(calcDtTmTo, "yyyyMMddHHmmss");

        Long toDiffTime = 0L;

        if (toDateTime != null && fromDateTime != null) {
            toDiffTime = toDateTime.getTime() - fromDateTime.getTime();
        } else {
            return null;
        }

        // Next Date
        if (nextDateFlg) {
            return fromDiffTime + toDiffTime;
        }

        int businessDays = getBusinessDays(infoBean, nextDate, prevDate);

        if (businessDays == 0) {
            return fromDiffTime + toDiffTime;
        }

        fromDateTime = toDate(infoBean.getToDt() + infoBean.getAvailableHourFrom(), "yyyyMMddHHmmss");
        toDateTime = toDate(infoBean.getToDt() + infoBean.getAvailableHourTo(), "yyyyMMddHHmmss");

        if (toDateTime != null && fromDateTime != null) {
            diffTime = toDateTime.getTime() - fromDateTime.getTime();
            diffTime = diffTime * businessDays;
        } else {
            return null;
        }

        diffTime = diffTime + fromDiffTime + toDiffTime;

        return diffTime;
    }

    private static int getBusinessDays(AvailableHourBean infoBean, String fromDate, String toDate) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
        queryParam.put("fromDate", fromDate);
        queryParam.put("toDate", toDate);
        // START 2023/06/14 K.Kitachi [QC#61365, ADD]
        queryParam.put("calTpCd", CAL_TP.SVC_CALENDAR);
        // END 2023/06/14 K.Kitachi [QC#61365, ADD]

        Integer businessDays = (Integer) ssmClient.queryObject("getBusinessDays", queryParam);

        if (businessDays != null && businessDays < 0) {
            return 0;
        }

        return businessDays;
    }

    private static AvailableHourBean getNextFromBusinessDay(AvailableHourBean infoBean) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
        queryParam.put("fromDate", infoBean.getFromDt());
        // START 2023/06/14 K.Kitachi [QC#61365, ADD]
        queryParam.put("calTpCd", CAL_TP.SVC_CALENDAR);
        // END 2023/06/14 K.Kitachi [QC#61365, ADD]

        String nextFromDt = (String) ssmClient.queryObject("getBusinessDay", queryParam);

        if (ZYPCommonFunc.hasValue(nextFromDt)) {
            infoBean.setFromDt(nextFromDt);
            infoBean.setFromTm(infoBean.getAvailableHourFrom());
            infoBean.setFromDtTm(nextFromDt + infoBean.getAvailableHourFrom());
        } else {
            infoBean = null;
        }

        return infoBean;
    }

    private static AvailableHourBean getPrevToBusinessDay(AvailableHourBean infoBean) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
        queryParam.put("toDate", infoBean.getToDt());
        // START 2023/06/14 K.Kitachi [QC#61365, ADD]
        queryParam.put("calTpCd", CAL_TP.SVC_CALENDAR);
        // END 2023/06/14 K.Kitachi [QC#61365, ADD]

        String prevToDt = (String) ssmClient.queryObject("getBusinessDay", queryParam);

        if (ZYPCommonFunc.hasValue(prevToDt)) {
            infoBean.setToDt(prevToDt);
            infoBean.setToDtTm(prevToDt + infoBean.getAvailableHourTo());
        } else {
            infoBean = null;
        }

        return infoBean;
    }

    private static AvailableHourBean inputCheck(String glblCmpyCd, String fromDt, String fromTm, String toDt, String toTm, String availableHourFrom, String availableHourTo) {

        AvailableHourBean infoBean = new AvailableHourBean();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return null;
        } else {
            infoBean.setGlblCmpyCd(glblCmpyCd);
        }
        if (!ZYPCommonFunc.hasValue(fromDt)) {
            return null;
        } else {
            if (fromDt.length() != 8) {
                return null;
            } else {
                infoBean.setFromDt(fromDt);
            }
        }
        if (!ZYPCommonFunc.hasValue(toDt)) {
            return null;
        } else {
            if (toDt.length() != 8) {
                return null;
            } else {
                infoBean.setToDt(toDt);
            }
        }

        if (fromDt.compareTo(toDt) > 0) {
            return null;
        }

        if (!ZYPCommonFunc.hasValue(fromTm)) {
            return null;
        } else {
            if (fromTm.length() < 4 || fromTm.length() > 6) {
                return null;
            } else {
                if (fromTm.length() == 4) {
                    fromTm = fromTm + "00";
                }
                infoBean.setFromTm(fromTm);
            }
        }

        if (!ZYPCommonFunc.hasValue(toTm)) {
            return null;
        } else {
            if (toTm.length() < 4 || toTm.length() > 6) {
                return null;
            } else {
                if (toTm.length() == 4) {
                    toTm = toTm + "00";
                }
                infoBean.setToTm(toTm);
            }
        }

        String fromDtTm = infoBean.getFromDt() + infoBean.getFromTm();
        String toDtTm = infoBean.getToDt() + infoBean.getToTm();

        if (fromDtTm.compareTo(toDtTm) > 0) {
            return null;
        } else {
            infoBean.setFromDtTm(fromDtTm);
            infoBean.setToDtTm(toDtTm);
        }

        if (!ZYPCommonFunc.hasValue(availableHourFrom)) {
            // get Const Value
            availableHourFrom = ZYPCodeDataUtil.getVarCharConstValue("DEF_CUST_AVAL_FROM_HOUR_MN", glblCmpyCd);
        }

        if (!ZYPCommonFunc.hasValue(availableHourFrom) || availableHourFrom.length() < 4 || availableHourFrom.length() > 6) {
            return null;
        }

        if (availableHourFrom.length() == 4) {
            availableHourFrom = availableHourFrom + "00";
        }
        infoBean.setAvailableHourFrom(availableHourFrom);

        if (!ZYPCommonFunc.hasValue(availableHourTo)) {
            // get Const Value
            availableHourTo = ZYPCodeDataUtil.getVarCharConstValue("DEF_CUST_AVAL_TO_HOUR_MN", glblCmpyCd);
        }

        if (!ZYPCommonFunc.hasValue(availableHourTo) || availableHourTo.length() < 4 || availableHourTo.length() > 6) {
            return null;
        }

        if (availableHourTo.length() == 4) {
            availableHourTo = availableHourTo + "00";
        }
        infoBean.setAvailableHourTo(availableHourTo);

        if (infoBean.getAvailableHourFrom().compareTo(infoBean.getAvailableHourTo()) > 0) {
            return null;
        }

        // Business Date Check From Date
        // START 2023/06/14 K.Kitachi [QC#61365, MOD]
//        if (!ZYPDateUtil.isBusinessDay(infoBean.getGlblCmpyCd(), infoBean.getFromDt())) {
        if (!ZYPDateUtil.isBusinessDay(CAL_TP.SVC_CALENDAR, infoBean.getFromDt())) {
        // END 2023/06/14 K.Kitachi [QC#61365, MOD]
            infoBean = getNextFromBusinessDay(infoBean);
            if (infoBean == null) {
                return null;
            }
        }

        // Business Hour Check From Date
        if (infoBean.getFromTm().compareTo(infoBean.getAvailableHourTo()) > 0) {
            infoBean = getNextFromBusinessDay(infoBean);
            if (infoBean == null) {
                return null;
            }
        }
        if (infoBean.getFromTm().compareTo(infoBean.getAvailableHourFrom()) < 0) {
            infoBean.setFromTm(infoBean.getAvailableHourFrom());
            infoBean.setFromDtTm(infoBean.getFromDt() + infoBean.getAvailableHourFrom());
        }

        // Business Date Check To Date
        // START 2023/06/14 K.Kitachi [QC#61365, MOD]
//        if (!ZYPDateUtil.isBusinessDay(infoBean.getGlblCmpyCd(), infoBean.getToDt())) {
        if (!ZYPDateUtil.isBusinessDay(CAL_TP.SVC_CALENDAR, infoBean.getToDt())) {
        // END 2023/06/14 K.Kitachi [QC#61365, MOD]
            infoBean = getPrevToBusinessDay(infoBean);
            if (infoBean == null) {
                return null;
            }
        }
        // Business Hour Check To Date
        if (infoBean.getToTm().compareTo(infoBean.getAvailableHourFrom()) < 0) {
            infoBean = getPrevToBusinessDay(infoBean);
            if (infoBean == null) {
                return null;
            }
        }
        if (infoBean.getToTm().compareTo(infoBean.getAvailableHourTo()) > 0) {
            infoBean.setToTm(infoBean.getAvailableHourTo());
            infoBean.setToDtTm(infoBean.getToDt() + infoBean.getAvailableHourTo());
        }

        // FromDtTm > ToDtTM
        if (infoBean.getFromDtTm().compareTo(infoBean.getToDtTm()) > 0) {
            return null;
        }

        return infoBean;
    }

    private static Date toDate(String fromTs, String formatFrom) {

        if (!hasValue(fromTs)) {
            return null;
        }
        SimpleDateFormat parser = new SimpleDateFormat(formatFrom);
        parser.setLenient(false);

        try {
            return parser.parse(fromTs);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * addDays
     * @param date String
     * @param numberOfDays int
     * @return String
     */
    public static String addDays(String date, int numberOfDays) {
        if (!hasValue(date)) {
            return null;
        }
        if (numberOfDays == 0) {
            return date;
        }

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.DATE, numberOfDays);
        return format.format(cal.getTime());
    }

//NA#ASCC CLICK ADD Start
    /**
     * getRspTmMnAot
     * 
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @param svcTaskRcvDt String
     * @param machDownFlg String
     * @param mdlNm String
     * @return BigDecimal
     */
    public static BigDecimal getRspTmMnAot(String glblCmpyCd, BigDecimal svcMachMstrPk, String svcTaskRcvDt, String machDownFlg, String mdlNm) {

        BigDecimal svcRspTmMnAot = BigDecimal.ZERO;
        // START 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
        BigDecimal tmpSvcRspTmMnAot = BigDecimal.ZERO;
        // END 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]

// START 02/10/2016 Y.Takeno [QC#3727, DEL]
//        ContrInfoBean contrInfo = (new NSXC001001Contr()).getContrInfo(glblCmpyCd, null, svcMachMstrPk, svcTaskRcvDt);
//
//        boolean isMachDown = false;
//        if (ZYPCommonFunc.hasValue(machDownFlg) && ZYPConstant.FLG_ON_Y.equals(machDownFlg)) {
//
//            isMachDown = true;
//        }
//
//        if (contrInfo != null) {
//
//            if (isMachDown) {
//
//                svcRspTmMnAot = (BigDecimal) contrInfo.getSvcRspTmDownMnAot();
//
//            } else {
//
//                svcRspTmMnAot = (BigDecimal) contrInfo.getSvcRspTmUpMnAot();
//            }
//        }
// END   02/10/2016 Y.Takeno [QC#3727, DEL]

// START 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
//// START 02/10/2016 Y.Takeno [QC#3727, ADD]
//
//        DS_CONTR_DTLTMsg dsContrDltTMsg = NSXC001001GetContr.getContrDtlByMachMstrPk(glblCmpyCd, svcMachMstrPk, svcTaskRcvDt);
//        if (dsContrDltTMsg == null) {
//            dsContrDltTMsg = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarranty(glblCmpyCd, svcMachMstrPk, svcTaskRcvDt);
//        }
//
//        boolean isMachDown = false;
//        if (ZYPCommonFunc.hasValue(machDownFlg) && ZYPConstant.FLG_ON_Y.equals(machDownFlg)) {
//            isMachDown = true;
//        }
//
//        if (dsContrDltTMsg != null) {
//            SVC_TERM_CONDTMsg tMsg = getServiceTermCond(glblCmpyCd, dsContrDltTMsg.dsContrPk.getValue(), dsContrDltTMsg.dsContrDtlPk.getValue());
//            if (tMsg != null) {
//                if (hasValue(tMsg.svcTermAttrbMapValCd)) {
//                    svcRspTmMnAot = new BigDecimal(tMsg.svcTermAttrbMapValCd.getValue());
//                    // mod start 2016/05/16 CSA Defect#8219
//                    svcRspTmMnAot = svcRspTmMnAot.multiply(MN_60);
//                    // mod end 2016/05/16 CSA Defect#8219
//                }
//            }
//        }
//
//// END   02/10/2016 Y.Takeno [QC#3727, ADD]

        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = NSXC001001GetContr.getContrDtlByMachMstrPkList(glblCmpyCd, svcMachMstrPk, svcTaskRcvDt);
        if (dsContrDtlTMsgList.size() == 0) {
            dsContrDtlTMsgList = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarrantyList(glblCmpyCd, svcMachMstrPk, svcTaskRcvDt);
        }

        boolean isMachDown = false;
        if (ZYPCommonFunc.hasValue(machDownFlg) && ZYPConstant.FLG_ON_Y.equals(machDownFlg)) {
            isMachDown = true;
        }

        // Mod Start 2018/07/25 QC#27414
//        if (dsContrDtlTMsgList.size() != 0) {
        for(DS_CONTR_DTLTMsg dsContrDtlTMsg:dsContrDtlTMsgList){
            SVC_TERM_CONDTMsg tMsg = getServiceTermCond(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
            if (tMsg != null) {
                if (hasValue(tMsg.svcTermAttrbMapValCd)) {
                    tmpSvcRspTmMnAot = new BigDecimal(tMsg.svcTermAttrbMapValCd.getValue());
                    // mod start 2016/05/16 CSA Defect#8219
                    tmpSvcRspTmMnAot = tmpSvcRspTmMnAot.multiply(MN_60);
                    // mod end 2016/05/16 CSA Defect#8219
                }
            }
            // set Minimum Value of svcRspTmMnAot In Contract
            if (svcRspTmMnAot.compareTo(tmpSvcRspTmMnAot) == 1 || svcRspTmMnAot.compareTo(BigDecimal.ZERO) == 0) {
                svcRspTmMnAot = tmpSvcRspTmMnAot;
            }
        }

        if (!ZYPCommonFunc.hasValue(svcRspTmMnAot) || svcRspTmMnAot.compareTo(BigDecimal.ZERO) == 0) {

            if (ZYPCommonFunc.hasValue(mdlNm)) {

                Map<String, String> param = new HashMap<String, String>();
                param.put("glblCmpyCd", glblCmpyCd);
                param.put("mdlNm", mdlNm);
                param.put("mdlActvFlg", ZYPConstant.FLG_ON_Y);

                Map<String, Object> resultMap = (Map<String, Object>) ssmClient.queryObject("getSvcRspTmMnAot", param);

                if (resultMap != null) {

                    if (isMachDown) {

                        svcRspTmMnAot = (BigDecimal) resultMap.get("RSP_TM_DOWN_MN_AOT");

                    } else {

                        svcRspTmMnAot = (BigDecimal) resultMap.get("RSP_TM_UP_MN_AOT");
                    }
                }
            }

            if (!ZYPCommonFunc.hasValue(svcRspTmMnAot) || svcRspTmMnAot.compareTo(BigDecimal.ZERO) == 0) {

                String keyVarCharConstNm = "RSP_TM_UP_MN_AOT";

                if (isMachDown) {

                    keyVarCharConstNm = "RSP_TM_DOWN_MN_AOT";
                }

                String rspTmMnAot = ZYPCodeDataUtil.getVarCharConstValue(keyVarCharConstNm, glblCmpyCd);

                if (ZYPCommonFunc.hasValue(rspTmMnAot)) {

                    svcRspTmMnAot = new BigDecimal(rspTmMnAot);
                }
            }
        }

//                // set Minimum Value of svcRspTmMnAot In Contract
//                if (svcRspTmMnAot.compareTo(tmpSvcRspTmMnAot) == 1 || svcRspTmMnAot.compareTo(BigDecimal.ZERO) == 0) {
//                    svcRspTmMnAot = tmpSvcRspTmMnAot;
//                }
//            }
//        }
        // Mod Start 2018/07/25 QC#27414
// END 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]

        return svcRspTmMnAot;
    }

    // Mod Start 2017/10/02 QC#21273
    /**
     * getEndDateByRspTime
     * 
     * @param glblCmpyCd String
     * @param rspTmMnAot BigDecimal
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param fromDt String
     * @param fromTM String
     * @param svcPrcShiftOnlyFlg boolean
     * @return String DateTime with 'yyyyMMddHHmmss' format
     */
    public static String getEndDateByRspTime(String glblCmpyCd, BigDecimal rspTmMnAot, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String fromDt, String fromTM, boolean svcPrcShiftOnlyFlg) {
        return getEndDateByRspTime(glblCmpyCd, rspTmMnAot, svcMachMstrTMsg, fromDt, fromTM, svcPrcShiftOnlyFlg, null);
    }

    /**
     * getEndDateByRspTime
     * 
     * @param glblCmpyCd String
     * @param rspTmMnAot BigDecimal
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param fromDt String
     * @param fromTM String
     * @param svcPrcShiftOnlyFlg boolean
     * @return String DateTime with 'yyyyMMddHHmmss' format
     */
    public static String getEndDateByRspTime(String glblCmpyCd, BigDecimal rspTmMnAot, SVC_MACH_MSTRTMsg svcMachMstrTMsg, String fromDt, String fromTM, boolean svcPrcShiftOnlyFlg, String dsSvcCallTpCd) {
        /**
         * Initial
         */
        // START 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
        Calendar endDateByRspTimeCal = Calendar.getInstance();
        Calendar initEndDateByRspTimeCal = Calendar.getInstance();
        // END 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
        Calendar tmpCalendar = Calendar.getInstance();
        Date inParamDate = toDate(fromDt + fromTM, FORMAT_SYS_DT_TM);
        if (inParamDate == null) {

            return null;
        }
        // START 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
        endDateByRspTimeCal.setTime(inParamDate);
        initEndDateByRspTimeCal.setTime(inParamDate);
        // END 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
        tmpCalendar.setTime(inParamDate);

        if (BigDecimal.ZERO.compareTo(rspTmMnAot) > 0) {

            return getDtFmtString(tmpCalendar.getTime(), FORMAT_SYS_DT_TM);
        }

        Calendar getHolidayThruCal = Calendar.getInstance();
        Date getHolidayThruDate = toDate(fromDt, DATE_FORMAT);
        if (getHolidayThruDate == null) {

            return null;
        }
        getHolidayThruCal.setTime(getHolidayThruDate);

        getHolidayThruCal.add(Calendar.YEAR, 1);

        List<String> holidaysList = getHolidays(glblCmpyCd, fromDt, getDtFmtString(getHolidayThruCal.getTime(), DATE_FORMAT));

        /**
         * Get End Date
         */
        Long tempRspTm = rspTmMnAot.longValue() * 60000;

        // START 2018/08/01 K.Kitachi [QC#27554, ADD]
        if (isAftHrsCall(glblCmpyCd, dsSvcCallTpCd)) {
            tmpCalendar.add(Calendar.MILLISECOND, tempRspTm.intValue());
            return getDtFmtString(tmpCalendar.getTime(), FORMAT_SYS_DT_TM);
        }
        // END 2018/08/01 K.Kitachi [QC#27554, ADD]

        // START 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
//        DS_CONTR_DTLTMsg dsContrDltTMsg = NSXC001001GetContr.getContrDtlByMachMstrPk(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), fromDt);
//        if (dsContrDltTMsg == null) {
//            dsContrDltTMsg = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarranty(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), fromDt);
//        }
//
//        Map<String, Object> cvrgTimeMap = null;
//        if (isAftHrs(glblCmpyCd, dsSvcCallTpCd) && dsContrDltTMsg != null && !svcPrcShiftOnlyFlg) {
//            cvrgTimeMap = getCoverageTime(glblCmpyCd, dsContrDltTMsg.dsContrPk.getValue(), dsContrDltTMsg.dsContrDtlPk.getValue());
//        }
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = NSXC001001GetContr.getContrDtlByMachMstrPkList(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), fromDt);
        if (dsContrDtlTMsgList.size() == 0) {
            dsContrDtlTMsgList = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarrantyList(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), fromDt);
        }

        Map<String, Object> cvrgTimeMap = null;
        List<Map<String, Object>> cvrgTimeMapList = new ArrayList<Map<String, Object>>();
        if (dsContrDtlTMsgList.size() != 0) {
            for (DS_CONTR_DTLTMsg dsContrDtlTMsg:dsContrDtlTMsgList) {
                if (isAftHrs(glblCmpyCd, dsSvcCallTpCd) && dsContrDtlTMsg != null && !svcPrcShiftOnlyFlg) {
                    // Mod Start 2018/07/25 QC#27414
                    Map<String, Object> cvrgTmMap = getCoverageTime(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
                    if (cvrgTmMap != null && !cvrgTmMap.isEmpty()) {
                        cvrgTimeMapList.add(cvrgTmMap);
                    }
                    // Mod End 2018/07/25 QC#27414
                }
            }
        }

        // add start 2016/04/15 CSA Defect#5486
        SVC_PRC_SHIFTTMsg svcPrcShiftTMsg  = new SVC_PRC_SHIFTTMsg();
        // if (cvrgTimeMap == null) {
        if (cvrgTimeMapList.size() == 0) {
            svcPrcShiftTMsg = getSvcPrcShift(glblCmpyCd, svcMachMstrTMsg);
        }
        // add end 2016/04/15 CSA Defect#5486

        int i = 0;
        do {
            if (cvrgTimeMapList.size() != 0) {
                cvrgTimeMap = cvrgTimeMapList.get(i);
            }

            boolean loopContFlag = false;

            do {
                String tempPrstDt = getDtFmtString(tmpCalendar.getTime(), DATE_FORMAT);
                String tempPrstTm = getDtFmtString(tmpCalendar.getTime(), FORMAT_SYS_DT_TM).substring(8);

                if (tmpCalendar.getTime().compareTo(getHolidayThruCal.getTime()) == 1) {
                    break;
                }

                String bizFromTm = null;
                String bizThruTm = null;
                if (cvrgTimeMap != null) {

                    if (!isStartDay(tmpCalendar, cvrgTimeMap, holidaysList)) {
                        tmpCalendar = getNextMidNight(tmpCalendar);
                        loopContFlag = true;
                        continue;
                    }

                    // START 2016/11/01 [QC#15658, MOD]
                    // bizFromTm = (String) cvrgTimeMap.get(SVC_TERM_COND_DATA_FROM_TM);
                    // bizThruTm = (String) cvrgTimeMap.get(SVC_TERM_COND_DATA_THRU_TM);
                    bizFromTm = getCoverageTimeFromTm(tmpCalendar, cvrgTimeMap, holidaysList);
                    bizThruTm = getCoverageTimeThruTm(tmpCalendar, cvrgTimeMap, holidaysList);
                    // END 2016/11/01 [QC#15658, MOD]

                    if (bizFromTm.length() == 4) {
                        bizFromTm += "00";
                    }

                    if (bizThruTm.length() == 4) {
                        bizThruTm += "00";
                    }

                // add start 2016/04/15 CSA Defect#5486
                } else if (svcPrcShiftTMsg != null) {

                    if (!isStartDayByPrcShift(tmpCalendar, svcPrcShiftTMsg, holidaysList)) {
                        tmpCalendar = getNextMidNight(tmpCalendar);
                        loopContFlag = true;
                        continue;
                    }

                    Map<String, Object> prcShiftTimeMap = getStandardTime(tmpCalendar, svcPrcShiftTMsg, holidaysList);
                    if (prcShiftTimeMap != null) {
                        bizFromTm = (String) prcShiftTimeMap.get(STD_TIME_FROM);
                        bizThruTm = (String) prcShiftTimeMap.get(STD_TIME_THRU);
                    }
                // add end 2016/04/15 CSA Defect#5486
                } else {

                    bizFromTm = TM_START_OFTHEDAY;
                    bizThruTm = TM_END_OFTHEDAY;
                }

                if (!ZYPCommonFunc.hasValue(bizFromTm) || !ZYPCommonFunc.hasValue(bizThruTm)) {
                    tmpCalendar = getNextMidNight(tmpCalendar);
                    loopContFlag = true;
                    continue;
                }

                /**
                 * Get start-end date for forwarding tmpDt
                 */
                String tmpTm = tempPrstTm;
                if (Integer.parseInt(bizFromTm) > Integer.parseInt(tempPrstTm)) {

                    tmpTm = bizFromTm;

                } else if (Integer.parseInt(tempPrstTm) > Integer.parseInt(bizThruTm)) {

                    // mod start 2016/04/15 CSA Defect#5486
                    if (BigDecimal.ZERO.compareTo(rspTmMnAot) == 0) {
                        tmpCalendar = getNextMidNight(tmpCalendar);
                        loopContFlag = true;
                        continue;
                    }
                    // mod end 2016/04/15 CSA Defect#5486

                    tmpTm = bizThruTm;
                }

                Date tmpDate = toDate(tempPrstDt + tmpTm, FORMAT_SYS_DT_TM);
                if (tmpDate == null) {

                    return null;
                }
                tmpCalendar.setTime(tmpDate);

                Calendar bizThrumTmCal = Calendar.getInstance();

                if (TM_END_OFTHEDAY.equals(bizThruTm)) {

                    bizThrumTmCal = getNextMidNight(tmpCalendar);

                } else {

                    tmpDate = toDate(tempPrstDt + bizThruTm, FORMAT_SYS_DT_TM);

                    if (tmpDate == null) {

                        return null;
                    }
                    bizThrumTmCal.setTime(tmpDate);
                }

                /**
                 * Forwarding tmpDt
                 */
                // START 2017/04/21 K.Kitachi [QC#18117, MOD]
//              Long diffTm = bizThrumTmCal.getTimeInMillis() - tmpCalendar.getTimeInMillis();
                Long diffTm = getDiffTm(bizThrumTmCal, tmpCalendar);
                // END 2017/04/21 K.Kitachi [QC#18117, MOD]
                if (tempRspTm > diffTm) {

                    tempRspTm -= diffTm;
                    tmpCalendar.add(Calendar.MILLISECOND, diffTm.intValue());

                    if (bizThrumTmCal.compareTo(tmpCalendar) == 0 && diffTm.compareTo(0L) == 0) {

                        tmpCalendar = getNextMidNight(tmpCalendar);
                    }

                    loopContFlag = false;

                } else {

                    tmpCalendar.add(Calendar.MILLISECOND, tempRspTm.intValue());
                    tempRspTm -= tempRspTm;

                    if (bizThruTm.equals(getDtFmtString(tmpCalendar.getTime(), FORMAT_TM)) && diffTm.compareTo(0L) != 0) {

                        tmpCalendar = getNextMidNight(tmpCalendar);

                        loopContFlag = true;

                    } else {

                        if (!isStartDay(tmpCalendar, cvrgTimeMap, holidaysList)) {

                            loopContFlag = true;

// START 2016/11/01 [QC#15658, MOD]
                        // add start 2016/04/15 CSA Defect#5486
//                    } else if (!isStartDayByPrcShift(tmpCalendar, svcPrcShiftTMsg, holidaysList)) {
                        } else if (cvrgTimeMap == null && !isStartDayByPrcShift(tmpCalendar, svcPrcShiftTMsg, holidaysList)) {
// END 2016/11/01 [QC#15658, MOD]

                            loopContFlag = true;
                        // add end 2016/04/15 CSA Defect#5486
                        } else {

                            loopContFlag = false;
                        }
                    }
                }

            } while (tempRspTm > 0 || loopContFlag);

            // set Minimum Value of endDateByRspTime In Contract
            if (endDateByRspTimeCal.compareTo(tmpCalendar) == 1 || endDateByRspTimeCal.compareTo(initEndDateByRspTimeCal) == 0) {
                endDateByRspTimeCal = tmpCalendar;
            }

            i++;
        } while (i < cvrgTimeMapList.size());
        // return getDtFmtString(tmpCalendar.getTime(), FORMAT_SYS_DT_TM);
        return getDtFmtString(endDateByRspTimeCal.getTime(), FORMAT_SYS_DT_TM);
// END 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
    }
    // Mod End 2017/10/02 QC#21273

    // add 2016/04/15 CSA Defect#5486
    private static Map<String, Object> getStandardTime(Calendar tmpCalendar, SVC_PRC_SHIFTTMsg svcPrcShiftTMsg, List<String> holidaysList) {
        Map<String, Object> standardTimeMap = new HashMap<String, Object>();
        String tempPrstDt = getDtFmtString(tmpCalendar.getTime(), DATE_FORMAT);

        if (svcPrcShiftTMsg == null) {
            return null;
        }

        if (holidaysList != null && holidaysList.contains(tempPrstDt)) {
            if (hasValue(svcPrcShiftTMsg.svcPrcHolStartValTxt) && hasValue(svcPrcShiftTMsg.svcPrcHolEndValTxt)) {
                standardTimeMap.put(STD_TIME_FROM, svcPrcShiftTMsg.svcPrcHolStartValTxt.getValue());
                standardTimeMap.put(STD_TIME_THRU, svcPrcShiftTMsg.svcPrcHolEndValTxt.getValue());
                return standardTimeMap;
            }
        }

        int dayOfWeek = tmpCalendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                standardTimeMap.put(STD_TIME_FROM, svcPrcShiftTMsg.svcPrcMonStartValTxt.getValue());
                standardTimeMap.put(STD_TIME_THRU, svcPrcShiftTMsg.svcPrcMonEndValTxt.getValue());
                break;
            case Calendar.TUESDAY:
                standardTimeMap.put(STD_TIME_FROM, svcPrcShiftTMsg.svcPrcTueStartValTxt.getValue());
                standardTimeMap.put(STD_TIME_THRU, svcPrcShiftTMsg.svcPrcTueEndValTxt.getValue());
                break;
            case Calendar.WEDNESDAY:
                standardTimeMap.put(STD_TIME_FROM, svcPrcShiftTMsg.svcPrcWedStartValTxt.getValue());
                standardTimeMap.put(STD_TIME_THRU, svcPrcShiftTMsg.svcPrcWedEndValTxt.getValue());
                break;
            case Calendar.THURSDAY:
                standardTimeMap.put(STD_TIME_FROM, svcPrcShiftTMsg.svcPrcThuStartValTxt.getValue());
                standardTimeMap.put(STD_TIME_THRU, svcPrcShiftTMsg.svcPrcThuEndValTxt.getValue());
                break;
            case Calendar.FRIDAY:
                standardTimeMap.put(STD_TIME_FROM, svcPrcShiftTMsg.svcPrcFriStartValTxt.getValue());
                standardTimeMap.put(STD_TIME_THRU, svcPrcShiftTMsg.svcPrcFriEndValTxt.getValue());
                break;
            case Calendar.SATURDAY:
                standardTimeMap.put(STD_TIME_FROM, svcPrcShiftTMsg.svcPrcSatStartValTxt.getValue());
                standardTimeMap.put(STD_TIME_THRU, svcPrcShiftTMsg.svcPrcSatEndValTxt.getValue());
                break;
            case Calendar.SUNDAY:
                standardTimeMap.put(STD_TIME_FROM, svcPrcShiftTMsg.svcPrcSunStartValTxt.getValue());
                standardTimeMap.put(STD_TIME_THRU, svcPrcShiftTMsg.svcPrcSunEndValTxt.getValue());
                break;
            default:
        }

        return standardTimeMap;
    }

    // add 2016/04/15 CSA Defect#5486
    private static boolean isStartDayByPrcShift(Calendar tmpCalendar, SVC_PRC_SHIFTTMsg svcPrcShiftTMsg, List<String> holidaysList) {
        String tempPrstDt = getDtFmtString(tmpCalendar.getTime(), DATE_FORMAT);

        if (svcPrcShiftTMsg == null) {
            return true;
        }

        if (holidaysList != null && holidaysList.contains(tempPrstDt)) {
            return hasValue(svcPrcShiftTMsg.svcPrcHolStartValTxt) && hasValue(svcPrcShiftTMsg.svcPrcHolEndValTxt);
        }

        int dayOfWeek = tmpCalendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return hasValue(svcPrcShiftTMsg.svcPrcMonStartValTxt) && hasValue(svcPrcShiftTMsg.svcPrcMonEndValTxt);
            case Calendar.TUESDAY:
                return hasValue(svcPrcShiftTMsg.svcPrcTueStartValTxt) && hasValue(svcPrcShiftTMsg.svcPrcTueEndValTxt);
            case Calendar.WEDNESDAY:
                return hasValue(svcPrcShiftTMsg.svcPrcWedStartValTxt) && hasValue(svcPrcShiftTMsg.svcPrcWedEndValTxt);
            case Calendar.THURSDAY:
                return hasValue(svcPrcShiftTMsg.svcPrcThuStartValTxt) && hasValue(svcPrcShiftTMsg.svcPrcThuEndValTxt);
            case Calendar.FRIDAY:
                return hasValue(svcPrcShiftTMsg.svcPrcFriStartValTxt) && hasValue(svcPrcShiftTMsg.svcPrcFriEndValTxt);
            case Calendar.SATURDAY:
                return hasValue(svcPrcShiftTMsg.svcPrcSatStartValTxt) && hasValue(svcPrcShiftTMsg.svcPrcSatEndValTxt);
            case Calendar.SUNDAY:
                return hasValue(svcPrcShiftTMsg.svcPrcSunStartValTxt) && hasValue(svcPrcShiftTMsg.svcPrcSunEndValTxt);
            default:
        }

        return false;
    }

    // add 2016/04/15 CSA Defect#5486
    private static SVC_PRC_SHIFTTMsg getSvcPrcShift(String glblCmpyCd, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        SVC_PRC_SHIFTTMsg tMsg = new SVC_PRC_SHIFTTMsg();

        tMsg.setSQLID("009");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcPrcShiftAhsFlg01", ZYPConstant.FLG_OFF_N);
        tMsg.setConditionValue("svcPrcShiftActvFlg01", ZYPConstant.FLG_ON_Y);
        tMsg.setConditionValue("svcLineBizCd01", svcMachMstrTMsg.svcByLineBizTpCd.getValue());

        SVC_PRC_SHIFTTMsgArray tMsgArry = (SVC_PRC_SHIFTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgArry.getValidCount() > 0) {
            return tMsgArry.no(0);
        }
        return null;
    }

    private static boolean isStartDay(Calendar calendar, Map<String, Object> cvrgTimeMap, List<String> holidaysList) {
        String tempPrstDt = getDtFmtString(calendar.getTime(), DATE_FORMAT);

        if (cvrgTimeMap == null) {
            return true;
        }

// START 2016/11/01 [QC#15658, MOD]
//        if (holidaysList != null && holidaysList.contains(tempPrstDt)) {
//            return ZYPConstant.FLG_ON_Y.equals((String) cvrgTimeMap.get("SVC_TERM_COND_DATA_HOL_FLG"));
//        }
//
//        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//        switch (dayOfWeek) {
//            case Calendar.MONDAY:
//                return ZYPConstant.FLG_ON_Y.equals((String) cvrgTimeMap.get("SVC_TERM_COND_DATA_MON_FLG"));
//            case Calendar.TUESDAY:
//                return ZYPConstant.FLG_ON_Y.equals((String) cvrgTimeMap.get("SVC_TERM_COND_DATA_TUE_FLG"));
//            case Calendar.WEDNESDAY:
//                return ZYPConstant.FLG_ON_Y.equals((String) cvrgTimeMap.get("SVC_TERM_COND_DATA_WED_FLG"));
//            case Calendar.THURSDAY:
//                return ZYPConstant.FLG_ON_Y.equals((String) cvrgTimeMap.get("SVC_TERM_COND_DATA_THU_FLG"));
//            case Calendar.FRIDAY:
//                return ZYPConstant.FLG_ON_Y.equals((String) cvrgTimeMap.get("SVC_TERM_COND_DATA_FRI_FLG"));
//            case Calendar.SATURDAY:
//                return ZYPConstant.FLG_ON_Y.equals((String) cvrgTimeMap.get("SVC_TERM_COND_DATA_SAT_FLG"));
//            case Calendar.SUNDAY:
//                return ZYPConstant.FLG_ON_Y.equals((String) cvrgTimeMap.get("SVC_TERM_COND_DATA_SUN_FLG"));
//            default:
//        }
        if (holidaysList != null && holidaysList.contains(tempPrstDt)) {
            return hasValue((String) cvrgTimeMap.get("AHS_HOL_FROM_TM")) && hasValue((String) cvrgTimeMap.get("AHS_HOL_TO_TM"));
        }

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return hasValue((String) cvrgTimeMap.get("AHS_MON_FROM_TM")) && hasValue((String) cvrgTimeMap.get("AHS_MON_TO_TM"));
            case Calendar.TUESDAY:
                return hasValue((String) cvrgTimeMap.get("AHS_TUE_FROM_TM")) && hasValue((String) cvrgTimeMap.get("AHS_TUE_TO_TM"));
            case Calendar.WEDNESDAY:
                return hasValue((String) cvrgTimeMap.get("AHS_WED_FROM_TM")) && hasValue((String) cvrgTimeMap.get("AHS_WED_TO_TM"));
            case Calendar.THURSDAY:
                return hasValue((String) cvrgTimeMap.get("AHS_THU_FROM_TM")) && hasValue((String) cvrgTimeMap.get("AHS_THU_TO_TM"));
            case Calendar.FRIDAY:
                return hasValue((String) cvrgTimeMap.get("AHS_FRI_FROM_TM")) && hasValue((String) cvrgTimeMap.get("AHS_FRI_TO_TM"));
            case Calendar.SATURDAY:
                return hasValue((String) cvrgTimeMap.get("AHS_SAT_FROM_TM")) && hasValue((String) cvrgTimeMap.get("AHS_SAT_TO_TM"));
            case Calendar.SUNDAY:
                return hasValue((String) cvrgTimeMap.get("AHS_SUN_FROM_TM")) && hasValue((String) cvrgTimeMap.get("AHS_SUN_TO_TM"));
            default:
        }
// END 2016/11/01 [QC#15658, MOD]

        return false;
    }

// ADD 2016/11/01 [QC#15658, START]
    private static String getCoverageTimeFromTm(Calendar calendar, Map<String, Object> cvrgTimeMap, List<String> holidaysList) {
        String tempPrstDt = getDtFmtString(calendar.getTime(), DATE_FORMAT);

        if (cvrgTimeMap == null) {
            return null;
        }

        if (holidaysList != null && holidaysList.contains(tempPrstDt)) {
            return (String) cvrgTimeMap.get("AHS_HOL_FROM_TM");
        }

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return (String) cvrgTimeMap.get("AHS_MON_FROM_TM");
            case Calendar.TUESDAY:
                return (String) cvrgTimeMap.get("AHS_TUE_FROM_TM");
            case Calendar.WEDNESDAY:
                return (String) cvrgTimeMap.get("AHS_WED_FROM_TM");
            case Calendar.THURSDAY:
                return (String) cvrgTimeMap.get("AHS_THU_FROM_TM");
            case Calendar.FRIDAY:
                return (String) cvrgTimeMap.get("AHS_FRI_FROM_TM");
            case Calendar.SATURDAY:
                return (String) cvrgTimeMap.get("AHS_SAT_FROM_TM");
            case Calendar.SUNDAY:
                return (String) cvrgTimeMap.get("AHS_SUN_FROM_TM");
            default:
        }

        return null;
    }

    private static String getCoverageTimeThruTm(Calendar calendar, Map<String, Object> cvrgTimeMap, List<String> holidaysList) {
        String tempPrstDt = getDtFmtString(calendar.getTime(), DATE_FORMAT);

        if (cvrgTimeMap == null) {
            return null;
        }

        if (holidaysList != null && holidaysList.contains(tempPrstDt)) {
            return (String) cvrgTimeMap.get("AHS_HOL_TO_TM");
        }

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return (String) cvrgTimeMap.get("AHS_MON_TO_TM");
            case Calendar.TUESDAY:
                return (String) cvrgTimeMap.get("AHS_TUE_TO_TM");
            case Calendar.WEDNESDAY:
                return (String) cvrgTimeMap.get("AHS_WED_TO_TM");
            case Calendar.THURSDAY:
                return (String) cvrgTimeMap.get("AHS_THU_TO_TM");
            case Calendar.FRIDAY:
                return (String) cvrgTimeMap.get("AHS_FRI_TO_TM");
            case Calendar.SATURDAY:
                return (String) cvrgTimeMap.get("AHS_SAT_TO_TM");
            case Calendar.SUNDAY:
                return (String) cvrgTimeMap.get("AHS_SUN_TO_TM");
            default:
        }

        return null;
    }
// END 2016/11/01 [QC#15658, ADD]

    private static List<String> getHolidays(String glblCmpyCd, String fromDt, String thruDt) {

        CALTMsg srchCalTMsg = new CALTMsg();

        srchCalTMsg.setSQLID("901");
        srchCalTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        // START 2023/05/24 T.Nagae [CSA-QC#61365, MOD]
//        srchCalTMsg.setConditionValue("calTpCd01", CAL_TP.CSA_HOLIDAY);
        srchCalTMsg.setConditionValue("calTpCd01", CAL_TP.SVC_HOLIDAY);
        // END   2023/05/24 T.Nagae [CSA-QC#61365, MOD]
        srchCalTMsg.setConditionValue("dtAttrbCd01", DT_ATTRB.BUSINESS);
        srchCalTMsg.setConditionValue("dtAttrbValCd01", ZYPConstant.FLG_ON_1);
        srchCalTMsg.setConditionValue("calDt01", fromDt);
        srchCalTMsg.setConditionValue("calDt02", thruDt);

        CALTMsgArray calArry = (CALTMsgArray) S21ApiTBLAccessor.findByCondition(srchCalTMsg);
        List<String> retList = new ArrayList<String>();
        for (int i = 0; i < calArry.length(); i++) {
            CALTMsg calTMsg = (CALTMsg) calArry.get(i);
            retList.add(calTMsg.calDt.getValue());
        }

        return retList;
    }

    private static Calendar getNextMidNight(Calendar cal) {

        Calendar tmpNextDayCal = (Calendar) cal.clone();

        tmpNextDayCal.add(Calendar.DATE, 1);

        Date tmpDate = toDate(getDtFmtString(tmpNextDayCal.getTime(), DATE_FORMAT) + TM_START_OFTHEDAY, FORMAT_SYS_DT_TM);
        if (tmpDate == null) {

            return null;
        }

        tmpNextDayCal.setTime(tmpDate);

        return tmpNextDayCal;
    }

    private static String getDtFmtString(Date date, String dateFmt) {

        SimpleDateFormat format = new SimpleDateFormat(dateFmt);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return format.format(cal.getTime());
    }

    // Mod Start 2017/10/02 QC#21273
    /**
     * getEndDateByRspTimeWithTimeDiff
     * 
     * @param glblCmpyCd String
     * @param rspTmMnAot BigDecimal
     * @param svcMachMstrPk BigDecimal
     * @param fromDt String
     * @param fromTm String
     * @param svcPrcShiftOnlyFlg boolean
     * @return String DateTime with 'yyyyMMddHHmmss' format
     */
    public static String getEndDateByRspTimeWithTimeDiff(String glblCmpyCd, BigDecimal rspTmMnAot, BigDecimal svcMachMstrPk, String fromDt, String fromTm, boolean svcPrcShiftOnlyFlg) {
        // START 2022/04/11 K.Kitachi [QC#59899, MOD]
//        return getEndDateByRspTimeWithTimeDiff(glblCmpyCd, rspTmMnAot, svcMachMstrPk, fromDt, fromTm, svcPrcShiftOnlyFlg, null);
        return getEndDateByRspTimeWithTimeDiff(glblCmpyCd, rspTmMnAot, svcMachMstrPk, fromDt, fromTm, svcPrcShiftOnlyFlg, null, null);
        // END 2022/04/11 K.Kitachi [QC#59899, MOD]
    }
    
    /**
     * getEndDateByRspTimeWithTimeDiff
     * 
     * @param glblCmpyCd String
     * @param rspTmMnAot BigDecimal
     * @param svcMachMstrPk BigDecimal
     * @param fromDt String
     * @param fromTm String
     * @param svcPrcShiftOnlyFlg boolean
     * @param dsSvcCallTpCd String
     * @param shipToUpdCustCd String
     * @return String DateTime with 'yyyyMMddHHmmss' format
     */
    // START 2022/04/11 K.Kitachi [QC#59899, MOD]
//    public static String getEndDateByRspTimeWithTimeDiff(String glblCmpyCd, BigDecimal rspTmMnAot, BigDecimal svcMachMstrPk, String fromDt, String fromTm, boolean svcPrcShiftOnlyFlg, String dsSvcCallTpCd) {
    public static String getEndDateByRspTimeWithTimeDiff(String glblCmpyCd, BigDecimal rspTmMnAot, BigDecimal svcMachMstrPk, String fromDt, String fromTm, boolean svcPrcShiftOnlyFlg, String dsSvcCallTpCd, String shipToUpdCustCd) {
    // END 2022/04/11 K.Kitachi [QC#59899, MOD]

        StringBuilder sb = new StringBuilder();
        sb.append(fromDt);
        sb.append(fromTm);

        String retDtTm = sb.toString();

        if (!ZYPCommonFunc.hasValue(rspTmMnAot)
                || !ZYPCommonFunc.hasValue(svcMachMstrPk)
                || !ZYPCommonFunc.hasValue(fromDt)
                || !ZYPCommonFunc.hasValue(fromTm)
        ) {

            return retDtTm;
        }

        SVC_MACH_MSTRTMsg srchSvcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        srchSvcMachMstrTMsg.glblCmpyCd.setValue(glblCmpyCd);
        srchSvcMachMstrTMsg.svcMachMstrPk.setValue(svcMachMstrPk);

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(srchSvcMachMstrTMsg);
        if (svcMachMstrTMsg == null) {

            return convertTimeToMSecFormat(retDtTm);
        }
        SHIP_TO_CUSTTMsg srchShipToCustTMsg = new SHIP_TO_CUSTTMsg();

        srchShipToCustTMsg.setSQLID("002");
        srchShipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        srchShipToCustTMsg.setConditionValue("shipToCustCd01", svcMachMstrTMsg.curLocNum.getValue());
        // START 2022/04/11 K.Kitachi [QC#59899, ADD]
        if (hasValue(shipToUpdCustCd)) {
            srchShipToCustTMsg.setConditionValue("shipToCustCd01", shipToUpdCustCd);
        }
        // END 2022/04/11 K.Kitachi [QC#59899, ADD]
        srchShipToCustTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        SHIP_TO_CUSTTMsgArray shipToCustArry = (SHIP_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(srchShipToCustTMsg);

        String ctryCd = null;
        String postCd = null;

        if (shipToCustArry == null || shipToCustArry.getValidCount() < 1) {

            return  convertTimeToMSecFormat(retDtTm);
        }

        // add start 2016/03/10 CSA Defect#5225
        if (!ZYPCommonFunc.hasValue(shipToCustArry.no(0).ctryCd)
                || !ZYPCommonFunc.hasValue(shipToCustArry.no(0).postCd)) {
            return  convertTimeToMSecFormat(retDtTm);
        }
        // add end 2016/03/10 CSA Defect#5225

        ctryCd = shipToCustArry.no(0).ctryCd.getValue();
        // mod start 2016/03/10 CSA Defect#5225
        if (shipToCustArry.no(0).postCd.getValue().length() > 5) {
            postCd = shipToCustArry.no(0).postCd.getValue().substring(0, 5);
        } else {
            postCd = shipToCustArry.no(0).postCd.getValue();
        }
        // mod end 2016/03/10 CSA Defect#5225

        SvcTimeZoneInfo svcTmZn = NSXC001001SvcTimeZone.convertTime(
                  NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC
                , sb.toString()
                , ctryCd
                , postCd
        );

        // mod start 2016/04/15 CSA Defect#5486
        String lateStartTs = null;
        lateStartTs = getEndDateByRspTime(
                  glblCmpyCd
                , rspTmMnAot
                , svcMachMstrTMsg
                , svcTmZn.getDateTime().substring(0, 8)
                , svcTmZn.getDateTime().substring(8, 14)
                , svcPrcShiftOnlyFlg
                , dsSvcCallTpCd
            );
        // mod end 2016/04/15 CSA Defect#5486

        svcTmZn = NSXC001001SvcTimeZone.convertTime(
                  NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS
                , lateStartTs
                , ctryCd
                , postCd
        );

        retDtTm = svcTmZn.getDateTime();

        return retDtTm;
    }
    // Mod End 2017/10/02 QC#21273
//NA#ASCC CLICK ADD End

    private static String convertTimeToMSecFormat(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date orgDate = format.parse(date);
            format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            return format.format(orgDate);
        } catch (ParseException e) {
            return date;
        }
    }

// START 02/18/2016 Y.Takeno [QC#3727, MOD]
    private static SVC_TERM_CONDTMsg getServiceTermCond(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        String attrNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_RSP_TM_COMIT, glblCmpyCd);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("attrNm", attrNm);

        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmClient.queryObjectList("getServiceTermCondPk", param);
        if (resultList == null || (resultList != null && resultList.isEmpty())) {
            param.put("dsContrDtlPk", null);
            resultList = (List<Map<String, Object>>) ssmClient.queryObjectList("getServiceTermCondPk", param);
        }

        if (resultList != null && !resultList.isEmpty()) {
            Map<String, Object> resultMap = resultList.get(0);
            SVC_TERM_CONDTMsg inTMsg = new SVC_TERM_CONDTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.svcTermCondPk, (BigDecimal) resultMap.get("SVC_TERM_COND_PK"));
            return (SVC_TERM_CONDTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        }

        return null;
    }

    private static Map<String, Object> getCoverageTime(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {

        String attrNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_AFTER_HOURS_WRK_PGM, glblCmpyCd);

        // START 2016/11/01 [QC#15658, MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("attrNm", attrNm);

//        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmClient.queryObjectList("getServiceTermCondDsContrDtl", param);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmClient.queryObjectList("getCoverageTime", param);
        if (resultList != null && !resultList.isEmpty()) {
            return resultList.get(0);
        }

        param.put("dsContrDtlPk", null);
//        resultList = (List<Map<String, Object>>) ssmClient.queryObjectList("getServiceTermCondDsContr", param);
        resultList = (List<Map<String, Object>>) ssmClient.queryObjectList("getCoverageTime", param);
        if (resultList != null && !resultList.isEmpty()) {
            return resultList.get(0);
        }
        // END 2016/11/01 [QC#15658, MOD]

        return null;
    }
// END   02/18/2016 Y.Takeno [QC#3727, MOD]
    // add start 2016/05/17 CSA Defect#447
    /**
     * getSLAInfo
     * @param infoBean SlaInfoBean
     * @return boolean isErr:false, isNomal:true
     */
    public static boolean getSLA(SlaInfoBean infoBean) {

        //input check & date format check
        if (!ZYPCommonFunc.hasValue(infoBean.getGlblCmpyCd())) {
            infoBean.setXxMsgId(NSXM0003E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(infoBean.getDsContrDtlPk())) {
            infoBean.setXxMsgId(NSXM0106E);
            return false;
        }
        if (!ZYPCommonFunc.hasValue(infoBean.getSlaDt())) {
            infoBean.setXxMsgId(NSXM0107E);
            return false;
        } else {
            if (!ZYPDateUtil.checkDate(infoBean.getSlaDt())) {
                infoBean.setXxMsgId(NSXM0108E);
                return false;
            }
        }

        //get Fleet Line for DS_CONTR_DTL_PK if input oaram:DS_CONTR_DTL_PK is Machine line in fleet contract
        BigDecimal fltLineDsContrDtlPk = (BigDecimal) ssmClient.queryObject("getFleetLineDsContrDtlPk", setParamForFleetLine(infoBean));
        infoBean.setSlaAddlChrgFlg(ZYPConstant.FLG_OFF_N);
        //get TERM_COND_OPT info from MDSE_TERM_COND_OPT
        Map<String, String> termCondOptMap = (Map<String, String>) ssmClient.queryObject("getTermCondOpt", setParamForTermCondOpt(infoBean, fltLineDsContrDtlPk));
        if (termCondOptMap != null && !termCondOptMap.isEmpty()) {
            infoBean.setSlaAddlChrgFlg(ZYPConstant.FLG_ON_Y);
            infoBean.setEffFromDt(termCondOptMap.get("EFF_FROM_DT"));
            infoBean.setEffToDt(termCondOptMap.get("EFF_THRU_DT"));
            infoBean.setTermCondOptDescTxt(termCondOptMap.get("TERM_COND_OPT_TP_DESC_TXT"));
            infoBean.setTermCondOptValTxt(termCondOptMap.get("TERM_COND_OPT_VAL_TXT"));
        //get TERM_COND_OPT info from SVC_COV_TMPL_DTL
        } else {
            Map<String, String> svcCovMap = (Map<String, String>) ssmClient.queryObject("getSvcCov", setParamForSvcCov(infoBean, fltLineDsContrDtlPk));
            if (svcCovMap != null && !svcCovMap.isEmpty()) {
                infoBean.setSlaAddlChrgFlg(ZYPConstant.FLG_OFF_N);
                infoBean.setTermCondOptDescTxt(svcCovMap.get("SVC_COV_FEAT_VAL_TXT"));
                infoBean.setTermCondOptValTxt(svcCovMap.get("SVC_COV_DTL_VAL_TXT"));
            }
        }
        return true;
    }

    private static Map<String, Object> setParamForFleetLine(SlaInfoBean infoBean) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
        queryParam.put("dsContrDtlPk", infoBean.getDsContrDtlPk());
        queryParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.FLEET);
        return queryParam;
    }

    private static Map<String, Object> setParamForTermCondOpt(SlaInfoBean infoBean, BigDecimal fltLineDsContrDtlPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
        queryParam.put("dsContrDtlPk", infoBean.getDsContrDtlPk());
        if (fltLineDsContrDtlPk != null) {
            queryParam.put("dsContrDtlPk", fltLineDsContrDtlPk);
        }
        queryParam.put("slaDt", infoBean.getSlaDt());
        queryParam.put("maxDate", "20991231");
        queryParam.put("rspTmTpFlg", ZYPConstant.FLG_ON_Y);
        return queryParam;
    }

    private static Map<String, Object> setParamForSvcCov(SlaInfoBean infoBean, BigDecimal fltLineDsContrDtlPk) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
        queryParam.put("dsContrDtlPk", infoBean.getDsContrDtlPk());
        if (fltLineDsContrDtlPk != null) {
            queryParam.put("dsContrDtlPk", fltLineDsContrDtlPk);
        }
        queryParam.put("slaDt", infoBean.getSlaDt());
        queryParam.put("maxDate", "20991231");
        queryParam.put("svcCovFeatCd", SVC_COV_FEAT.SLA_TIME);
        queryParam.put("svcCovDtlActvFlg", ZYPConstant.FLG_ON_Y);
        return queryParam;
    }

    private static DS_CONTR_ADDL_CHRGTMsgArray getDsContrAddlChrg(SlaInfoBean infoBean) {
        DS_CONTR_ADDL_CHRGTMsg inTMsg = new DS_CONTR_ADDL_CHRGTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", infoBean.getGlblCmpyCd());
        inTMsg.setConditionValue("dsContrDtlPk01", infoBean.getDsContrDtlPk());
        DS_CONTR_ADDL_CHRGTMsgArray resultArray = (DS_CONTR_ADDL_CHRGTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        return resultArray;
    }
    // add end 2016/05/17 CSA Defect#447

    // add start 2016/09/07 CSA Defect#13661
    /**
     * getRspTime
     * @param glblCmpyCd String
     * @param fromDt String
     * @param fromTm String
     * @param toDt String
     * @param toTm String
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @return
     */
    public static Long getRspTime(String glblCmpyCd, String fromDt, String fromTm, String toDt, String toTm, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        AvailableHourBean infoBean = inputCheck(glblCmpyCd, fromDt, fromTm, toDt, toTm);
        if (infoBean == null) {
            return null;
        }

        // START 2017/08/01 K.Kitachi [QC#20026, ADD]
        Long defRspTime = null;
        Date inFromDateTime = toDate(infoBean.getFromDtTm(), FORMAT_SYS_DT_TM);
        Date inToDateTime = toDate(infoBean.getToDtTm(), FORMAT_SYS_DT_TM);
        if (inFromDateTime != null && inToDateTime != null) {
            defRspTime = inToDateTime.getTime() - inFromDateTime.getTime();
        }
        // END 2017/08/01 K.Kitachi [QC#20026, ADD]

        // get SVC_PRC_SHIFT
        SVC_PRC_SHIFTTMsg svcPrcShiftTMsg = getSvcPrcShift(infoBean.getGlblCmpyCd(), svcMachMstrTMsg);

        // get holidays list
        List<String> holidaysList = getHolidays(infoBean.getGlblCmpyCd(), infoBean.getFromDt(), infoBean.getToDt());

// START 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
//        // START 2016/11/01 [QC#15658, ADD]
//        DS_CONTR_DTLTMsg dsContrDltTMsg = NSXC001001GetContr.getContrDtlByMachMstrPk(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), fromDt);
//        if (dsContrDltTMsg == null) {
//            dsContrDltTMsg = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarranty(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), fromDt);
//        }
//
//        Map<String, Object> cvrgTimeMap = null;
//        if (dsContrDltTMsg != null) {
//            cvrgTimeMap = getCoverageTime(glblCmpyCd, dsContrDltTMsg.dsContrPk.getValue(), dsContrDltTMsg.dsContrDtlPk.getValue());
//        }
//        // END 2016/11/01 [QC#15658, ADD]
        List<DS_CONTR_DTLTMsg> dsContrDtlTMsgList = NSXC001001GetContr.getContrDtlByMachMstrPkList(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), fromDt);
        if (dsContrDtlTMsgList.size() == 0) {
            dsContrDtlTMsgList = NSXC001001GetContr.getContrDtlByMachMstrPkInclWarrantyList(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue(), fromDt);
        }

        Map<String, Object> cvrgTimeMap = null;
        List<Map<String, Object>> cvrgTimeMapList = new ArrayList<Map<String, Object>>();
        if (dsContrDtlTMsgList.size() != 0) {
            for (DS_CONTR_DTLTMsg dsContrDtlTMsg:dsContrDtlTMsgList) {
                cvrgTimeMapList.add(getCoverageTime(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue()));
            }
        }

        Long rspTime = 0L;
        Long tmpRspTime = 0L;
        int i = 0;
        do {
            if (cvrgTimeMapList.size() != 0) {
                cvrgTimeMap = cvrgTimeMapList.get(i);
            }
        // get calcFromDtTm
        Calendar tempCal = Calendar.getInstance();
        Date tempDtTmDate = toDate(infoBean.getFromDtTm(), FORMAT_SYS_DT_TM);
        if (tempDtTmDate == null) {
            // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//            return null;
            return defRspTime;
            // END 2017/08/01 K.Kitachi [QC#20026, MOD]
        }
        tempCal.setTime(tempDtTmDate);

        String bizFromTmFrom = null;
        String bizThruTmFrom = null;
        while (true) {
            if (tempCal == null) {
                // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//                return null;
                return defRspTime;
                // END 2017/08/01 K.Kitachi [QC#20026, MOD]
            }
            String tempFromDt = getDtFmtString(tempCal.getTime(), DATE_FORMAT);
            String tempFromTm = getDtFmtString(tempCal.getTime(), FORMAT_SYS_DT_TM).substring(8);

            if ((tempFromDt + tempFromTm).compareTo(infoBean.getToDtTm()) > 0) {
                // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//                return null;
                return defRspTime;
                // END 2017/08/01 K.Kitachi [QC#20026, MOD]
            }

            // START 2016/11/01 [QC#15658, MOD]
            // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//            if (cvrgTimeMap != null && !isStartDay(tempCal, cvrgTimeMap, holidaysList)) {
//                tempCal = getNextMidNight(tempCal);
//                continue;
//                
//            } else if (!isStartDayByPrcShift(tempCal, svcPrcShiftTMsg, holidaysList)) {
//                tempCal = getNextMidNight(tempCal);
//                continue;
//            }
//
//            if (cvrgTimeMap != null) {
//                bizFromTmFrom = getCoverageTimeFromTm(tempCal, cvrgTimeMap, holidaysList);
//                bizThruTmFrom = getCoverageTimeThruTm(tempCal, cvrgTimeMap, holidaysList);
//
//            } else {
//                Map<String, Object> prcShiftTimeMap = getStandardTime(tempCal, svcPrcShiftTMsg, holidaysList);
//                if (prcShiftTimeMap != null) {
//                    bizFromTmFrom = (String) prcShiftTimeMap.get(STD_TIME_FROM);
//                    bizThruTmFrom = (String) prcShiftTimeMap.get(STD_TIME_THRU);
//                }
//            }
            if (cvrgTimeMap != null && isStartDay(tempCal, cvrgTimeMap, holidaysList)) {
                bizFromTmFrom = getCoverageTimeFromTm(tempCal, cvrgTimeMap, holidaysList);
                bizThruTmFrom = getCoverageTimeThruTm(tempCal, cvrgTimeMap, holidaysList);
            } else if (svcPrcShiftTMsg != null && isStartDayByPrcShift(tempCal, svcPrcShiftTMsg, holidaysList)) {
                Map<String, Object> prcShiftTimeMap = getStandardTime(tempCal, svcPrcShiftTMsg, holidaysList);
                if (prcShiftTimeMap != null) {
                    bizFromTmFrom = (String) prcShiftTimeMap.get(STD_TIME_FROM);
                    bizThruTmFrom = (String) prcShiftTimeMap.get(STD_TIME_THRU);
                }
            }
            // END 2017/08/01 K.Kitachi [QC#20026, MOD]
            // END 2016/11/01 [QC#15658, MOD]

            if (!ZYPCommonFunc.hasValue(bizFromTmFrom) || !ZYPCommonFunc.hasValue(bizThruTmFrom)) {
                tempCal = getNextMidNight(tempCal);
                continue;
            }

            if (Integer.parseInt(tempFromTm) < Integer.parseInt(bizFromTmFrom)) {
                infoBean.setFromDt(tempFromDt);
                infoBean.setFromTm(bizFromTmFrom);
                infoBean.setFromDtTm(tempFromDt + bizFromTmFrom);
            } else if (Integer.parseInt(tempFromTm) < Integer.parseInt(bizThruTmFrom)) {
                infoBean.setFromDt(tempFromDt);
                infoBean.setFromTm(tempFromTm);
                infoBean.setFromDtTm(tempFromDt + tempFromTm);
            } else if (Integer.parseInt(bizThruTmFrom) < Integer.parseInt(tempFromTm)) {
                tempCal = getNextMidNight(tempCal);
                continue;
            }
            break;
        }

        // get calcToDtTm
        tempDtTmDate = toDate(infoBean.getToDtTm(), FORMAT_SYS_DT_TM);
        if (tempDtTmDate == null) {
            // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//            return null;
            return defRspTime;
            // END 2017/08/01 K.Kitachi [QC#20026, MOD]
        }
        tempCal.setTime(tempDtTmDate);

        String bizFromTmTo = null;
        String bizThruTmTo = null;
        while (true) {
            if (tempCal == null) {
                // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//                return null;
                return defRspTime;
                // END 2017/08/01 K.Kitachi [QC#20026, MOD]
            }
            String tempToDt = getDtFmtString(tempCal.getTime(), DATE_FORMAT);
            String tempToTm = getDtFmtString(tempCal.getTime(), FORMAT_SYS_DT_TM).substring(8);

            if (infoBean.getFromDtTm().compareTo(tempToDt + tempToTm) > 0) {
                // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//                return null;
                return defRspTime;
                // END 2017/08/01 K.Kitachi [QC#20026, MOD]
            }

            // START 2016/11/01 [QC#15658, MOD]
            // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//            if (cvrgTimeMap != null && !isStartDay(tempCal, cvrgTimeMap, holidaysList)) {
//                tempCal = getPrevMidNight(tempCal);
//                continue;
//                
//            } else if (!isStartDayByPrcShift(tempCal, svcPrcShiftTMsg, holidaysList)) {
//                tempCal = getPrevMidNight(tempCal);
//                continue;
//            }
//
//            if (cvrgTimeMap != null) {
//                bizFromTmTo = getCoverageTimeFromTm(tempCal, cvrgTimeMap, holidaysList);
//                bizThruTmTo = getCoverageTimeThruTm(tempCal, cvrgTimeMap, holidaysList);
//
//            } else {
//                Map<String, Object> prcShiftTimeMap = getStandardTime(tempCal, svcPrcShiftTMsg, holidaysList);
//                if (prcShiftTimeMap != null) {
//                    bizFromTmTo = (String) prcShiftTimeMap.get(STD_TIME_FROM);
//                    bizThruTmTo = (String) prcShiftTimeMap.get(STD_TIME_THRU);
//                }
//            }
            if (cvrgTimeMap != null && isStartDay(tempCal, cvrgTimeMap, holidaysList)) {
                bizFromTmTo = getCoverageTimeFromTm(tempCal, cvrgTimeMap, holidaysList);
                bizThruTmTo = getCoverageTimeThruTm(tempCal, cvrgTimeMap, holidaysList);
            } else if (svcPrcShiftTMsg != null && isStartDayByPrcShift(tempCal, svcPrcShiftTMsg, holidaysList)) {
                Map<String, Object> prcShiftTimeMap = getStandardTime(tempCal, svcPrcShiftTMsg, holidaysList);
                if (prcShiftTimeMap != null) {
                    bizFromTmTo = (String) prcShiftTimeMap.get(STD_TIME_FROM);
                    bizThruTmTo = (String) prcShiftTimeMap.get(STD_TIME_THRU);
                }
            }
            // END 2017/08/01 K.Kitachi [QC#20026, MOD]
            // END 2016/11/01 [QC#15658, MOD]

            if (!ZYPCommonFunc.hasValue(bizFromTmTo) || !ZYPCommonFunc.hasValue(bizThruTmTo)) {
                tempCal = getPrevMidNight(tempCal);
                continue;
            }

            if (Integer.parseInt(tempToTm) < Integer.parseInt(bizFromTmTo)) {
                tempCal = getPrevMidNight(tempCal);
                continue;
            } else if (Integer.parseInt(tempToTm) < Integer.parseInt(bizThruTmTo)) {
                infoBean.setToDt(tempToDt);
                infoBean.setToTm(tempToTm);
                infoBean.setToDtTm(tempToDt + tempToTm);
            } else if (Integer.parseInt(bizThruTmTo) < Integer.parseInt(tempToTm)) {
                infoBean.setToDt(tempToDt);
                infoBean.setToTm(bizThruTmTo);
                infoBean.setToDtTm(tempToDt + bizThruTmTo);
            }
            break;
        }

        // Same Date Check
        boolean sameDateFlg = false;
        if (infoBean.getFromDt().compareTo(infoBean.getToDt()) == 0) {
            sameDateFlg = true;
        }

        // Next Date Check
        boolean nextDateFlg = false;
        String nextDate = addDays(infoBean.getFromDt(), 1);
        String prevDate = addDays(infoBean.getToDt(), -1);
        if (nextDate.compareTo(infoBean.getToDt()) == 0 && prevDate.compareTo(infoBean.getFromDt()) == 0) {
            nextDateFlg = true;
        }

        Date fromDateTime = null;
        Date toDateTime = null;

        // Same Date
        if (sameDateFlg) {
            fromDateTime = toDate(infoBean.getFromDtTm(), FORMAT_SYS_DT_TM);
            toDateTime = toDate(infoBean.getToDtTm(), FORMAT_SYS_DT_TM);

            if (fromDateTime != null && toDateTime != null) {
                return toDateTime.getTime() - fromDateTime.getTime();
            } else {
                // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//                return null;
                return defRspTime;
                // END 2017/08/01 K.Kitachi [QC#20026, MOD]
            }
        }

        // get fromDate diffTime
        Long fromDiffTime = 0L;
        fromDateTime = toDate(infoBean.getFromDtTm(), FORMAT_SYS_DT_TM);
        toDateTime = toDate(infoBean.getFromDt() + bizThruTmFrom, FORMAT_SYS_DT_TM);

        if (fromDateTime != null && toDateTime != null) {
            fromDiffTime = toDateTime.getTime() - fromDateTime.getTime();
        } else {
            // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//            return null;
            return defRspTime;
            // END 2017/08/01 K.Kitachi [QC#20026, MOD]
        }

        // get toDate diffTime
        Long toDiffTime = 0L;
        fromDateTime = toDate(infoBean.getToDt() + bizFromTmTo, FORMAT_SYS_DT_TM);
        toDateTime = toDate(infoBean.getToDtTm(), FORMAT_SYS_DT_TM);

        if (fromDateTime != null && toDateTime != null) {
            toDiffTime = toDateTime.getTime() - fromDateTime.getTime();
        } else {
            // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//            return null;
            return defRspTime;
            // END 2017/08/01 K.Kitachi [QC#20026, MOD]
        }

        // Next Date
        if (nextDateFlg) {
            return fromDiffTime + toDiffTime;
        }

        // get diffTime between fromDate and toDate
        Long diffTime = 0L;
        tempDtTmDate = toDate(infoBean.getFromDtTm(), FORMAT_SYS_DT_TM);
        if (tempDtTmDate == null) {
            // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//            return null;
            return defRspTime;
            // END 2017/08/01 K.Kitachi [QC#20026, MOD]
        }
        tempCal.setTime(tempDtTmDate);

        String bizFromTm = null;
        String bizThruTm = null;
        while (true) {
            tempCal = getNextMidNight(tempCal);
            if (tempCal == null) {
                // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//                return null;
                return defRspTime;
                // END 2017/08/01 K.Kitachi [QC#20026, MOD]
            }
            String tempDt = getDtFmtString(tempCal.getTime(), DATE_FORMAT);

            if (tempDt.compareTo(infoBean.getToDt()) >= 0) {
                break;
            }

            // START 2016/11/01 [QC#15658, MOD]
            if (cvrgTimeMap != null && !isStartDay(tempCal, cvrgTimeMap, holidaysList)) {
                continue;

            } else if (!isStartDayByPrcShift(tempCal, svcPrcShiftTMsg, holidaysList)) {
                continue;
            }

            if (cvrgTimeMap != null) {
                bizFromTm = getCoverageTimeFromTm(tempCal, cvrgTimeMap, holidaysList);
                bizThruTm = getCoverageTimeThruTm(tempCal, cvrgTimeMap, holidaysList);

            } else {
                Map<String, Object> prcShiftTimeMap = getStandardTime(tempCal, svcPrcShiftTMsg, holidaysList);
                if (prcShiftTimeMap != null) {
                    bizFromTm = (String) prcShiftTimeMap.get(STD_TIME_FROM);
                    bizThruTm = (String) prcShiftTimeMap.get(STD_TIME_THRU);
                }
            }
            // END 2016/11/01 [QC#15658, MOD]

            if (!ZYPCommonFunc.hasValue(bizFromTmFrom) || !ZYPCommonFunc.hasValue(bizThruTmFrom)) {
                continue;
            }

            fromDateTime = toDate(tempDt + bizFromTm, FORMAT_SYS_DT_TM);
            toDateTime = toDate(tempDt + bizThruTm, FORMAT_SYS_DT_TM);

            if (fromDateTime != null && toDateTime != null) {
                diffTime = diffTime + toDateTime.getTime() - fromDateTime.getTime();
            } else {
                // START 2017/08/01 K.Kitachi [QC#20026, MOD]
//                return null;
                return defRspTime;
                // END 2017/08/01 K.Kitachi [QC#20026, MOD]
            }
        }
        tmpRspTime = fromDiffTime + toDiffTime + diffTime;

        // set Minimum Value of rspTime In Contract
        if (rspTime.compareTo(tmpRspTime) == 1 || rspTime.compareTo(0L) == 0) {
            rspTime = tmpRspTime;
        }

        i++;
        } while (i < cvrgTimeMapList.size());
        // return fromDiffTime + toDiffTime + diffTime;
        return rspTime;
// END 2018/05/25 K.Kim [QC#15410(Sol#246),MOD]
    }

    private static AvailableHourBean inputCheck(String glblCmpyCd, String fromDt, String fromTm, String toDt, String toTm) {

        AvailableHourBean infoBean = new AvailableHourBean();

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return null;
        } else {
            infoBean.setGlblCmpyCd(glblCmpyCd);
        }
        if (!ZYPCommonFunc.hasValue(fromDt)) {
            return null;
        } else {
            if (fromDt.length() != 8) {
                return null;
            } else {
                infoBean.setFromDt(fromDt);
            }
        }
        if (!ZYPCommonFunc.hasValue(toDt)) {
            return null;
        } else {
            if (toDt.length() != 8) {
                return null;
            } else {
                infoBean.setToDt(toDt);
            }
        }

        if (fromDt.compareTo(toDt) > 0) {
            return null;
        }

        if (!ZYPCommonFunc.hasValue(fromTm)) {
            return null;
        } else {
            if (fromTm.length() < 4 || fromTm.length() > 6) {
                return null;
            } else {
                if (fromTm.length() == 4) {
                    fromTm = fromTm + "00";
                }
                infoBean.setFromTm(fromTm);
            }
        }

        if (!ZYPCommonFunc.hasValue(toTm)) {
            return null;
        } else {
            if (toTm.length() < 4 || toTm.length() > 6) {
                return null;
            } else {
                if (toTm.length() == 4) {
                    toTm = toTm + "00";
                }
                infoBean.setToTm(toTm);
            }
        }

        String fromDtTm = infoBean.getFromDt() + infoBean.getFromTm();
        String toDtTm = infoBean.getToDt() + infoBean.getToTm();

        if (fromDtTm.compareTo(toDtTm) > 0) {
            return null;
        } else {
            infoBean.setFromDtTm(fromDtTm);
            infoBean.setToDtTm(toDtTm);
        }

        return infoBean;
    }

    private static Calendar getPrevMidNight(Calendar cal) {

        Calendar tmpPrevDayCal = (Calendar) cal.clone();
        tmpPrevDayCal.add(Calendar.DATE, -1);
        Date tmpDate = toDate(getDtFmtString(tmpPrevDayCal.getTime(), DATE_FORMAT) + TM_235959, FORMAT_SYS_DT_TM);
        if (tmpDate == null) {
            return null;
        }
        tmpPrevDayCal.setTime(tmpDate);
        return tmpPrevDayCal;
    }
    // add end 2016/09/07 CSA Defect#13661

    // START 2017/04/21 K.Kitachi [QC#18117, ADD]
    private static long getDiffTm(Calendar inCal1, Calendar inCal2) {
        Calendar cal1 = transCalendarUTC(getDtFmtString(inCal1.getTime(), FORMAT_SYS_DT_TM));
        Calendar cal2 = transCalendarUTC(getDtFmtString(inCal2.getTime(), FORMAT_SYS_DT_TM));
        return cal1.getTimeInMillis() - cal2.getTimeInMillis();
    }

    private static Calendar transCalendarUTC(String date) {
        SimpleDateFormat form = new SimpleDateFormat(FORMAT_SYS_DT_TM);
        form.setTimeZone(TimeZone.getTimeZone(ID_UTC));
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(ID_UTC));
        try {
            cal.setTime(form.parse(date));
        } catch (ParseException e) {
            return null;
        }
        return cal;
    }
    // END 2017/04/21 K.Kitachi [QC#18117, ADD]
    // Add Start 2017/10/02 QC#21273
    private static boolean isAftHrs(String glblCmpyCd, String dsSvcCallTpCd) {
        if (!hasValue(dsSvcCallTpCd)) {
            return true;
        }
        DS_SVC_CALL_TPTMsg inMsg = new DS_SVC_CALL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsSvcCallTpCd, dsSvcCallTpCd);
        DS_SVC_CALL_TPTMsg outMsg = (DS_SVC_CALL_TPTMsg) S21CodeTableAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        }
        if (hasValue(outMsg.aftHrsFlg) && ZYPConstant.FLG_OFF_N.equals(outMsg.aftHrsFlg.getValue())) {
            return false;
        }
        return true;
    }
    // Add End 2017/10/02 QC#21273

    // START 2018/08/01 K.Kitachi [QC#27554, ADD]
    private static boolean isAftHrsCall(String glblCmpyCd, String dsSvcCallTpCd) {
        if (!hasValue(dsSvcCallTpCd)) {
            return false;
        }
        DS_SVC_CALL_TPTMsg inMsg = new DS_SVC_CALL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsSvcCallTpCd, dsSvcCallTpCd);
        DS_SVC_CALL_TPTMsg outMsg = (DS_SVC_CALL_TPTMsg) S21CodeTableAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return false;
        }
        if (hasValue(outMsg.aftHrsFlg) && ZYPConstant.FLG_ON_Y.equals(outMsg.aftHrsFlg.getValue())) {
            return true;
        }
        return false;
    }
    // END 2018/08/01 K.Kitachi [QC#27554, ADD]
}
