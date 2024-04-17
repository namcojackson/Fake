package com.canon.cusa.s21.api.NFA.NFZC103001.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

/**
 * <pre>
 * Invoice Distribution Create API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/05   Hitachi         K.Kojima        Create          QC#11049
 * </pre>
 */
public class NFZC103001Common {

    /**
     * @param procDt
     * @return
     */
    public static String getGlDt(String glblCmpyCd, String procDt, String acctDt) {
        String glDt = null;
        BigDecimal days = BigDecimal.ONE;
        BigDecimal constDays = ZYPCodeDataUtil.getNumConstValue("NFZC1030_PERIOD_CHNG_PRE_MONTH", glblCmpyCd);
        if (constDays != null) {
            days = constDays;
        }
        String preMonthStartDay = getPreMonthStartDay(procDt);
        String preMonthEndDay = getPreMonthEndDay(procDt);
        String businessDay = ZYPDateUtil.addBusinessDay(glblCmpyCd, preMonthEndDay, days.intValue());
        boolean preChangeFlag = false;
        if (procDt.compareTo(businessDay) <= 0) {
            preChangeFlag = true;
        }
        if (preChangeFlag == true) {
            if (acctDt.compareTo(preMonthStartDay) < 0) {
                glDt = procDt;
            } else {
                glDt = acctDt;
            }
        } else {
            if (acctDt.compareTo(preMonthEndDay) <= 0) {
                glDt = procDt;
            } else {
                glDt = acctDt;
            }
        }
        return glDt;
    }

    /**
     * getPreMonthStartDay
     * @param date String "YYYYMMDD'
     * @return lastDay String "YYYYMMDD"
     */
    public static String getPreMonthStartDay(String date) {
        String preMonthStartDay = null;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 2);
        c.set(Calendar.DATE, 1);
        preMonthStartDay = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        return preMonthStartDay;
    }

    /**
     * getPreMonthEndDay
     * @param date String "YYYYMMDD'
     * @return preMonthEndDay String "YYYYMMDD"
     */
    public static String getPreMonthEndDay(String date) {
        String preMonthEndDay = null;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 2);
        c.set(Calendar.DATE, 1);
        int day = c.getActualMaximum(Calendar.DATE);
        preMonthEndDay = new SimpleDateFormat("yyyyMM").format(c.getTime()) + day;
        return preMonthEndDay;
    }
}
