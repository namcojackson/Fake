/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.lang.reflect.Method;

import parts.common.EZDItem;

import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Service Time Zone
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/12   SRA             E.Inada         Create          N/A
 * 2016/02/08   Hitachi         Y.Takeno        Update          QC#3727
 * 2016/03/25   Hitachi         K.Yamada        Update          CSA QC#5737
 *</pre>
 */
public class NSXC001001SvcTimeZone {
    /** System time to Local time */
    public static final int MODE1_SYS_TO_LOC = 1;

    /** Local time to System time */
    public static final int MODE2_LOC_TO_SYS = 2;

    /** System time to Login User time */
    public static final int MODE3_SYS_TO_USR = 3;

    private static final String ZERO = "00000000000000000";

    private static final int DATE_LEN = 8;

    private static String defaultTimeZoneId = "";

    public static SvcTimeZoneInfo convertTime(int mode, String dateTime, String ctryCd, String postCd) {

        ZYPLocalTimeData locTmDt = null;
        SvcTimeZoneInfo info = new SvcTimeZoneInfo();

        if (!ZYPCommonFunc.hasValue(dateTime) || !ZYPCommonFunc.hasValue(ctryCd) || !ZYPCommonFunc.hasValue(postCd)) {
            return null;
        }

        String dtTmStr = getSysTmFormat(dateTime);

        // add start 2016/03/25 CSA Defect#5737
        postCd = subStrPostCd(postCd);
        // add end 2016/03/25 CSA Defect#5737

        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        String glblCmpyCd = prof.getGlobalCompanyCode();
        defaultTimeZoneId = ZYPCodeDataUtil.getVarCharConstValue("DEF_TIME_ZONE_ID", glblCmpyCd);

        try {

            switch (mode) {
                case MODE1_SYS_TO_LOC:
                    locTmDt = convertSysToLoc(dtTmStr, ctryCd, postCd);
                    break;

                case MODE2_LOC_TO_SYS:
                    locTmDt = convertLocToSys(dtTmStr, ctryCd, postCd);
                    break;

                case MODE3_SYS_TO_USR:
                    // no use
                    return null;

                default:
                    return null;
            }
        } catch (ZYPLocalTimeException e) {
            return null;
        }

        info.setDateTime(locTmDt.getTime());
        info.setTimeZone(locTmDt.getTZDspName());
        info.setCtryCd(ctryCd);
        info.setPostCd(postCd);

        return info;
    }

    public static boolean convertTime(int mode, EZDItem dtMsg, EZDItem tmMsg, EZDItem tmZnMsg, String ctryCd, String postCd) {

        Class<EZDItem> cls = EZDItem.class;
        ZYPLocalTimeData lclTmDt = null;
        String dtTmCnvStr = null;

        if (dtMsg == null || tmMsg == null || tmZnMsg == null || !ZYPCommonFunc.hasValue(ctryCd) || !ZYPCommonFunc.hasValue(postCd)) {
            return false;
        }

        try {
            Method methodSet = cls.getDeclaredMethod("setValueString", String.class);
            Method methodGet = cls.getDeclaredMethod("getValueString");
            methodSet.setAccessible(true);
            methodGet.setAccessible(true);

            String dtStr = (String) methodGet.invoke(dtMsg);
            String tmStr = (String) methodGet.invoke(tmMsg);
            String dtTmStr = dtStr;
            if (tmStr != null) {
                dtTmStr += tmStr;
            }
            dtTmStr = getSysTmFormat(dtTmStr);

            // add start 2016/03/25 CSA Defect#5737
            postCd = subStrPostCd(postCd);
            // add end 2016/03/25 CSA Defect#5737

            S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
            String glblCmpyCd = prof.getGlobalCompanyCode();
            defaultTimeZoneId = ZYPCodeDataUtil.getVarCharConstValue("DEF_TIME_ZONE_ID", glblCmpyCd);

            switch (mode) {
                case MODE1_SYS_TO_LOC:
                    lclTmDt = convertSysToLoc(dtTmStr, ctryCd, postCd);
                    break;

                case MODE2_LOC_TO_SYS:
                    lclTmDt = convertLocToSys(dtTmStr, ctryCd, postCd);
                    break;

                case MODE3_SYS_TO_USR:
                    // no use
                    return false;

                default:
                    return false;
            }

            dtTmCnvStr = lclTmDt.getTime();

            methodSet.invoke(dtMsg, dtTmCnvStr.substring(0, DATE_LEN));
            if (tmStr != null) {
                methodSet.invoke(tmMsg, dtTmCnvStr.substring(DATE_LEN, DATE_LEN + tmStr.length()));
            }

            methodSet.invoke(tmZnMsg, lclTmDt.getTZDspName());

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getSysTimeZone(EZDItem dtTmMsg) {
        Class<EZDItem> cls = EZDItem.class;

        try {
            Method methodGet = cls.getDeclaredMethod("getValueString");
            methodGet.setAccessible(true);

            String dtTmStr = (String) methodGet.invoke(dtTmMsg);

            return getSysTimeZone(dtTmStr);

        } catch (Exception e) {
            return null;
        }
    }

    public static String getSysTimeZone(EZDItem dtMsg, EZDItem tmMsg) {
        Class<EZDItem> cls = EZDItem.class;

        try {
            Method methodGet = cls.getDeclaredMethod("getValueString");
            methodGet.setAccessible(true);

            String dtStr = (String) methodGet.invoke(dtMsg);
            String tmStr = (String) methodGet.invoke(tmMsg);
            String dtTmStr = dtStr;
            if (tmStr != null) {
                dtTmStr += tmStr;
            }

            return getSysTimeZone(dtTmStr);

        } catch (Exception e) {
            return null;
        }
    }

    public static String getSysTimeZone(String dtTmStr) {
        return ZYPLocalTimeUtil.getDspNameBySysTime(getSysTmFormat(dtTmStr));
    }

    private static ZYPLocalTimeData convertSysToLoc(String dtTmStr, String ctryCd, String postCd) throws ZYPLocalTimeException {

        String lclTzId = null;

        try {
            lclTzId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
        } catch (Exception e) {
        }

        ZYPLocalTimeData lclTmData = new ZYPLocalTimeData();

        if (ZYPCommonFunc.hasValue(lclTzId)) {
            lclTmData = ZYPLocalTimeUtil.convertTimeSys2Lcl(lclTzId, dtTmStr);
        } else {
            lclTmData = ZYPLocalTimeUtil.convertTimeSys2Lcl(defaultTimeZoneId, dtTmStr);
        }

        return lclTmData;
    }

    private static ZYPLocalTimeData convertLocToSys(String dtTmStr, String ctryCd, String postCd) throws ZYPLocalTimeException {

        String lclTzId = null;

        try {
            lclTzId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
        } catch (Exception e) {
        }

        ZYPLocalTimeData lclTmData = new ZYPLocalTimeData();

        if (ZYPCommonFunc.hasValue(lclTzId)) {
            lclTmData = ZYPLocalTimeUtil.convertTimeLcl2Sys(lclTzId, dtTmStr);
        } else {
            lclTmData = ZYPLocalTimeUtil.convertTimeLcl2Sys(defaultTimeZoneId, dtTmStr);
        }

        return lclTmData;
    }

    public static String getSysTmFormat(String dtTm) {
        if (dtTm == null) {
            return dtTm;
        }
        int len = dtTm.length();
        if (ZERO.length() - len <= 0) {
            return dtTm;
        }
        return dtTm + ZERO.substring(len);
    }

    // START 02/08/2016 Y.Takeno [QC#3727, ADD]

    /**
     * convert time
     * @param mode mode
     * @param dateTime dateTime
     * @param rtlWhCd rtlWhCd
     */
    public static SvcTimeZoneInfo convertTimeRtlWh(int mode, String dateTime, String rtlWhCd) {

        RTL_WHTMsg rtlWhTMsg = getRtlWh(rtlWhCd);
        if (rtlWhTMsg != null) {
            return convertTime(mode, dateTime, rtlWhTMsg.ctryCd.getValue(), rtlWhTMsg.postCd.getValue());
        } else {
            return null;
        }
    }

    private static RTL_WHTMsg getRtlWh(String rtlWhCd) {

        S21UserProfileService prof = S21UserProfileServiceFactory.getInstance().getService();
        String glblCmpyCd = prof.getGlobalCompanyCode();

        RTL_WHTMsg rtlWhInTMsg = new RTL_WHTMsg();
        rtlWhInTMsg.glblCmpyCd.setValue(glblCmpyCd);
        rtlWhInTMsg.rtlWhCd.setValue(rtlWhCd);

        return (RTL_WHTMsg) S21ApiTBLAccessor.findByKey(rtlWhInTMsg);
    }

    // END   02/08/2016 Y.Takeno [QC#3727, ADD]

    // add start 2016/03/25 CSA Defect#5737
    private static String subStrPostCd(String postCd) {
        if (!ZYPCommonFunc.hasValue(postCd)) {
            return null;
        }
        if (postCd.length() > 5) {
            return postCd.substring(0, 5);
        }
        return postCd;
    }
    // add end 2016/03/25 CSA Defect#5737
}
