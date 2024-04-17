/**
 * <Pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import parts.common.EZDDebugOutput;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;

/**
 * <pre>
 * Calculation date
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/01/21   Hitachi         K.Kitachi       Create          QC#29083
 * 2019/01/25   Hitachi         T.Tomita        Update          QC#30080
 * </pre>
 */
public class NSXC001001CalcDate {

    /** INT_6 : 6 */
    private static final int INT_6 = 6;

    /** START_DAY : 01 */
    private static final String START_DAY = "01";

    /**
     * Difference months
     * @param fromDate String
     * @param thruDate String
     * @return int
     */
    public static int diffMonths(String fromDate, String thruDate) {
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

    // Mod Start 2019/01/25 QC#30080
    /**
     * Next Month Start Date
     * @param date String
     * @return String
     */
    public static String getNextMonthStartDate(String date) {
        String startDate = date.substring(0, INT_6) + START_DAY;
        return addMonths(startDate, 1, false);
    }
    // Mod End 2019/01/25 QC#30080

    /**
     * Add months
     * @param date String
     * @param numberOfMonths int
     * @return String
     */
    public static String addMonths(String date, int numberOfMonths) {
        boolean isMonthEnd = isMonthEnd(date);
        return addMonths(date, numberOfMonths, isMonthEnd);
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
