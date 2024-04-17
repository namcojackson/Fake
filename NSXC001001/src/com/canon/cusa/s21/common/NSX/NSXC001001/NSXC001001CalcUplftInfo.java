/**
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import parts.common.EZDDebugOutput;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;

/**
 * <pre>
 * Calculation uplift info
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/13   Hitachi         K.Kitachi       Create          QC#28638
 * 2019/07/30   Hitachi         A.Kohinata      Update          QC#52070
 * </pre>
 */
public class NSXC001001CalcUplftInfo {

    /** INT_6 : 6 */
    private static final int INT_6 = 6;

    /** START_DAY : 01 */
    private static final String START_DAY = "01";

    /**
     * Calculation uplift info by changed fixTermInMthAot
     * @param bean UpliftInfoBean
     */
    public static void calcUplftInfoByChngFixTermInMthAot(UpliftInfoBean bean) {

        if (!mandatoryCheckByChngFixTermInMthAot(bean)) {
            return;
        }

        String contrEffFromDt = bean.getContrEffFromDt();
        String contrEffThruDt = bean.getContrEffThruDt();
        BigDecimal fixTermInMthAot = bean.getFixTermInMthAot();

        String uplftFixedDt = getUplftFixedDt(contrEffFromDt, fixTermInMthAot);
        String uplftPcyDt = getUplftPcyDt(uplftFixedDt, contrEffThruDt);

        bean.setUplftFixedDt(uplftFixedDt);
        bean.setUplftPcyDt(uplftPcyDt);
    }

    /**
     * Calculation uplift info by changed uplftFixedDt
     * @param bean UpliftInfoBean
     */
    public static void calcUplftInfoByChngUplftFixedDt(UpliftInfoBean bean) {

        if (!mandatoryCheckByChngUplftFixedDt(bean)) {
            return;
        }

        String contrEffFromDt = bean.getContrEffFromDt();
        String contrEffThruDt = bean.getContrEffThruDt();
        String uplftFixedDt = bean.getUplftFixedDt();

        int diffMonths = diffMonths(contrEffFromDt, uplftFixedDt);
        String uplftPcyDt = getUplftPcyDt(uplftFixedDt, contrEffThruDt);

        bean.setFixTermInMthAot(BigDecimal.valueOf(diffMonths));
        bean.setUplftPcyDt(uplftPcyDt);
    }

    private static boolean mandatoryCheckByChngFixTermInMthAot(UpliftInfoBean bean) {
        if (bean == null) {
            return false;
        }
        if (!hasValue(bean.getContrEffFromDt())) {
            return false;
        }
        if (!hasValue(bean.getContrEffThruDt())) {
            return false;
        }
        if (!hasValue(bean.getFixTermInMthAot())) {
            return false;
        }
        return true;
    }

    private static boolean mandatoryCheckByChngUplftFixedDt(UpliftInfoBean bean) {
        if (bean == null) {
            return false;
        }
        if (!hasValue(bean.getContrEffFromDt())) {
            return false;
        }
        if (!hasValue(bean.getContrEffThruDt())) {
            return false;
        }
        if (!hasValue(bean.getUplftFixedDt())) {
            return false;
        }
        return true;
    }

    private static String getUplftFixedDt(String contrEffFromDt, BigDecimal fixTermInMthAot) {
        boolean isMonthEnd = isMonthEnd(contrEffFromDt);
        String tmpAddMthDt = addMonths(contrEffFromDt, fixTermInMthAot.intValue(), isMonthEnd);
        return getPrevDay(tmpAddMthDt);
    }

    private static String getUplftPcyDt(String uplftFixedDt, String contrEffThruDt) {
        String nextMonthStartDate = getNextMonthStartDate(uplftFixedDt);
        // del start 2019/07/30 QC#52070
        //if (ZYPDateUtil.compare(nextMonthStartDate, contrEffThruDt) > 0) {
        //    return contrEffThruDt;
        //}
        // del end 2019/07/30 QC#52070
        return nextMonthStartDate;
    }

//    private static int diffMonths(String fromDate, String thruDate) {
//        if (ZYPDateUtil.compare(fromDate, thruDate) >= 0) {
//            return 0;
//        }
//        boolean isMonthEnd = isMonthEnd(fromDate);
//        String tmpFromDt = fromDate;
//        String tmpThruDt = getNextDay(thruDate);
//        int diffMonths = 0;
//        while (ZYPDateUtil.compare(tmpFromDt, tmpThruDt) <= 0) {
//            tmpFromDt = addMonths(fromDate, diffMonths + 1, isMonthEnd);
//            diffMonths++;
//        }
//        return diffMonths - 1;
//    }

    private static int diffMonths(String fromDate, String thruDate) {
        if (ZYPDateUtil.compare(fromDate, thruDate) >= 0) {
            return 0;
        }
        String tmpThruDt = getNextDay(thruDate);
        boolean isMonthEnd = isMonthEnd(fromDate);
        return diffMonths(fromDate, tmpThruDt, 1, isMonthEnd);
    }

    private static int diffMonths(String fromDate, String tmpThruDt, int diffMonths, boolean isMonthEnd) {
        String tmpFromDt = addMonths(fromDate, diffMonths, isMonthEnd);
        if (ZYPDateUtil.compare(tmpFromDt, tmpThruDt) <= 0) {
            return diffMonths(fromDate, tmpThruDt, diffMonths + 1, isMonthEnd);
        }
        return diffMonths - 1;
    }

    private static boolean isMonthEnd(String date) {
        String monthEndDate = getMonthEndDate(date);
        if (date.equals(monthEndDate)) {
            return true;
        }
        return false;
    }

    private static String getMonthEndDate(String date) {
        String nextMonthStartDate = getNextMonthStartDate(date);
        return getPrevDay(nextMonthStartDate);
    }

    private static String getNextMonthStartDate(String date) {
        String startDate = date.substring(0, INT_6) + START_DAY;
        return addMonths(startDate, 1, false);
    }

    private static String addMonths(String date, int numberOfMonths, boolean isMonthEnd) {
        SimpleDateFormat format = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MONTH, numberOfMonths);
        String returnDate = format.format(cal.getTime());
        if (isMonthEnd) {
            return getMonthEndDate(returnDate);
        }
        return returnDate;
    }

    private static String getPrevDay(String date) {
        return ZYPDateUtil.addDays(date, -1);
    }

    private static String getNextDay(String date) {
        return ZYPDateUtil.addDays(date, 1);
    }
}
