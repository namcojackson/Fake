package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/10/14   SRAA            N.Otsuji        Create          N/A
 *</pre>
 */
public class NSXC003001SvcContrBllg {

    /**
     * Month end day
     */
    public static final String MTH_END_DAY = "99";

    /**
     * Get Billing From Date
     * @param svcInvChrgTpCd Service invoice charge type code
     * @param bllgCycleCd Billing cycle code
     * @param istlDt Install date
     * @param contrEffFromDt Contract effective from date
     * @param invUpToDt Invoice up to date
     * @return Billing from date
     */
    public static String getBllgFromDt(String svcInvChrgTpCd, String bllgCycleCd, String istlDt, String contrEffFromDt, String invUpToDt) {

        // QC2688
        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTpCd)) {
            if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bllgCycleCd)) {
                return contrEffFromDt;
            }
        }

        String nextDtOfInvUpToDt = addDays(invUpToDt, 1);

        return getMaxDt(istlDt, contrEffFromDt, nextDtOfInvUpToDt);
    }

    /**
     * Get Billing Through Date
     * @param slsDt Sales date
     * @param bllgTmgTpCd Billing timing type code
     * @param bllgCycleCd Billing cycle code
     * @param bllgCycleMthAot Billing cycle month
     * @param contrCloDay Contract closing day
     * @param invUpToDt Invoice up to date
     * @param contrCloDt Contract close date
     * @param contrEffThruDt Contract effective through date
     * @return Billing through date
     */
    public static String getBllgThruDt(String slsDt, String bllgTmgTpCd, String bllgCycleCd, int bllgCycleMthAot, String contrCloDay, String invUpToDt, String contrCloDt, String contrEffThruDt) {

        String bllgThruDt = null;

        if (BLLG_CYCLE.CONTRACT_PERIOD.equals(bllgCycleCd)) {

            bllgThruDt = contrEffThruDt;
        } else {

            if (BLLG_TMG_TP.ADVANCE.equals(bllgTmgTpCd)) {

                String dt;

                if (ZYPCommonFunc.hasValue(invUpToDt)) {
                    dt = getCloPerThruDt(invUpToDt, contrCloDay);
                } else {
                    dt = getCloPerThruDt(slsDt, contrCloDay);
                }

                for (int i = 0; i < bllgCycleMthAot; i++) {
                    dt = getNextCloPerThruDt(dt, contrCloDay);
                }

                bllgThruDt = dt;
            } else {

                String dt;

                if (ZYPCommonFunc.hasValue(invUpToDt)) {
                    dt = getNextCloPerThruDt(getCloPerThruDt(invUpToDt, contrCloDay), contrCloDay);
                } else {
                    dt = getCloPerThruDt(slsDt, contrCloDay);
                }

                if (ZYPCommonFunc.hasValue(invUpToDt)) {
                    for (int i = 0; i < bllgCycleMthAot - 1; i++) {
                        dt = getNextCloPerThruDt(dt, contrCloDay);
                    }
                }

                bllgThruDt = dt;
            }
        }

        bllgThruDt = getMinDt(bllgThruDt, contrCloDt, contrEffThruDt);

        return bllgThruDt;
    }

    /**
     * Calculate Billing Cycle
     * @param contrCloDay Contract closing day
     * @param fromDt Date from
     * @param thruDt Date through
     * @return BigDecimal Months
     */
    public static BigDecimal calcBllgCycleMthAot(String contrCloDay, String fromDt, String thruDt) {

        BigDecimal bllgCycleMthAot = BigDecimal.ZERO;

        String firstCloPerFromDt = ZYPDateUtil.addDays(getPrevCloPerThruDt(fromDt, contrCloDay), 1);
        String firstCloPerThruDt = getCloPerThruDt(fromDt, contrCloDay);

        int days;

        if (thruDt.compareTo(firstCloPerThruDt) < 0) {
            days = ZYPDateUtil.getDiffDays(thruDt, fromDt) + 1;
        } else {
            days = ZYPDateUtil.getDiffDays(firstCloPerThruDt, fromDt) + 1;
        }

        int cloPerDaysAot = ZYPDateUtil.getDiffDays(firstCloPerThruDt, firstCloPerFromDt) + 1;

        BigDecimal firstBllgCycleMthAot = new BigDecimal(days).divide(new BigDecimal(cloPerDaysAot), 2, RoundingMode.HALF_UP);

        bllgCycleMthAot = bllgCycleMthAot.add(firstBllgCycleMthAot);

        String scdLastCloPerThruDt = getPrevCloPerThruDt(thruDt, contrCloDay);

        for (String dt = firstCloPerThruDt; dt.compareTo(scdLastCloPerThruDt) < 0; dt = getNextCloPerThruDt(dt, contrCloDay)) {
            bllgCycleMthAot = bllgCycleMthAot.add(BigDecimal.ONE);
        }

        String lastCloPerFromDt = ZYPDateUtil.addDays(scdLastCloPerThruDt, 1);
        String lastCloPerThruDt = getNextCloPerThruDt(scdLastCloPerThruDt, contrCloDay);

        if (firstCloPerFromDt.compareTo(lastCloPerFromDt) < 0) {

            days = 0;

            if (thruDt.compareTo(lastCloPerThruDt) <= 0) {
                days = ZYPDateUtil.getDiffDays(thruDt, lastCloPerFromDt) + 1;
            }

            cloPerDaysAot = ZYPDateUtil.getDiffDays(lastCloPerThruDt, lastCloPerFromDt) + 1;

            if (days > 0 && cloPerDaysAot > 0) {
                BigDecimal lastBllgCycleMthAot = new BigDecimal(days).divide(new BigDecimal(cloPerDaysAot), 2, RoundingMode.HALF_UP);
                bllgCycleMthAot = bllgCycleMthAot.add(lastBllgCycleMthAot);
            }
        }

        return bllgCycleMthAot;
    }

    /**
     * Check if the specified day is on the contract closing day
     * @param baseDt Date
     * @param contrCloDay Contract closing day
     * @return True if the specified date is on the contract closing
     * day, otherwise false.
     */
    public static boolean onContrCloDay(String baseDt, String contrCloDay) {

        if (MTH_END_DAY.equals(contrCloDay)) {

            String dt = baseDt.substring(0, 6) + "01";

            String contrCloDt = getMthEndDt(dt);

            return baseDt.equals(contrCloDt);
        } else {

            String dt = baseDt.substring(0, 6) + "01";

            String contrCloDt = baseDt.substring(0, 6) + contrCloDay;

            String mthEndDt = getMthEndDt(dt);

            if (contrCloDt.compareTo(mthEndDt) > 0) {
                contrCloDt = mthEndDt;
            }

            return baseDt.equals(contrCloDt);
        }
    }

    /**
     * Calculate the end date of the next contract closing period.
     * @param curCloPerThruDt Current contract closing date
     * @param contrCloDay Contract closing day
     * @return The end date of the next contract closing period
     */
    public static String getNextCloPerThruDt(String curCloPerThruDt, String contrCloDay) {

        if (!ZYPDateUtil.isValidDate(curCloPerThruDt, "yyyyMMdd")) {
            return null;
        }

        if (contrCloDay.trim().length() == 1) {
            contrCloDay = "0" + contrCloDay.trim();
        }

        if (!onContrCloDay(curCloPerThruDt, contrCloDay)) {
            return null;
        }

        String curContrCloYrMth = curCloPerThruDt.substring(0, 6);

        String dt = addMths(curContrCloYrMth + "01", 1);

        if (MTH_END_DAY.equals(contrCloDay)) {

            return getMthEndDt(dt);
        } else {

            String contrCloDt = dt.substring(0, 6) + contrCloDay;
            String mthEndDt = getMthEndDt(dt);

            if (contrCloDt.compareTo(mthEndDt) > 0) {
                return mthEndDt;
            } else {
                return contrCloDt;
            }
        }
    }

    /**
     * Calculate the start date of the contract closing period.
     * @param baseDt Date
     * @param contrCloDay Contract closing day
     * @return The start date of the contract closing period.
     */
    public static String getCloPerFromDt(String baseDt, String contrCloDay) {
        return ZYPDateUtil.addDays(getPrevCloPerThruDt(getCloPerThruDt(baseDt, contrCloDay), contrCloDay), 1);
    }

    /**
     * Calculate the end date of the contract closing period.
     * @param baseDt Base date
     * @param contrCloDay Contract closing day
     * @return The end date of the contract closing period.
     */
    public static String getCloPerThruDt(String baseDt, String contrCloDay) {

        if (!ZYPDateUtil.isValidDate(baseDt, "yyyyMMdd")) {
            return null;
        }

        if (contrCloDay.trim().length() == 1) {
            contrCloDay = "0" + contrCloDay.trim();
        }

        boolean onContrCloDay = onContrCloDay(baseDt, contrCloDay);

        if (onContrCloDay) {
            return baseDt;
        }

        String baseYrMth = baseDt.substring(0, 6);
        String baseDay = baseDt.substring(6);

        if (MTH_END_DAY.equals(contrCloDay)) {

            String dt = baseYrMth + "01";

            return getMthEndDt(dt);
        } else {

            String dt;

            if (baseDay.compareTo(contrCloDay) < 0) {
                dt = baseYrMth + "01";
            } else {
                dt = addMths(baseYrMth + "01", 1);
            }

            String contrCloDt = dt.substring(0, 6) + contrCloDay;
            String mthEndDt = getMthEndDt(dt);

            if (contrCloDt.compareTo(mthEndDt) > 0) {
                return mthEndDt;
            } else {
                return contrCloDt;
            }
        }
    }

    /**
     * Calculate the end date of the previous contract closing period.
     * @param baseDt Date
     * @param contrCloDay Contract closing date
     * @return The end date of the previous contract closing period.
     */
    public static String getPrevCloPerThruDt(String baseDt, String contrCloDay) {

        if (!ZYPDateUtil.isValidDate(baseDt, "yyyyMMdd")) {
            return null;
        }

        if (contrCloDay.trim().length() == 1) {
            contrCloDay = "0" + contrCloDay.trim();
        }

        String baseYrMth = baseDt.substring(0, 6);
        String baseDay = baseDt.substring(6);

        if (MTH_END_DAY.equals(contrCloDay)) {

            String dt = addMths(baseYrMth + "01", -1);

            return getMthEndDt(dt);
        } else {

            String dt;

            if (baseDay.compareTo(contrCloDay) > 0) {
                dt = baseYrMth + "01";
            } else {
                dt = addMths(baseYrMth + "01", -1);
            }

            String contrCloDt = dt.substring(0, 6) + contrCloDay;
            String mthEndDt = getMthEndDt(dt);

            if (contrCloDt.compareTo(mthEndDt) > 0) {
                return mthEndDt;
            } else {
                return contrCloDt;
            }
        }
    }

    /**
     * Add N months to the date
     * @param dt Date
     * @param mthAot Number of months
     * @return Date
     */
    static String addMths(String dt, int mthAot) {
        if (mthAot == 0) {
            return dt;
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        fmt.setLenient(false);
        Calendar cal = Calendar.getInstance();
        cal.clear();
        try {
            cal.setTime(fmt.parse(dt));
            cal.add(Calendar.MONTH, mthAot);
            return fmt.format(cal.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Check if the date is a month end date
     * @param dt Date
     * @return True if the date is a month end date, otherwise false.
     */
    public static boolean isMthEndDt(String dt) {
        String mthEndDt = getMthEndDt(dt);
        return mthEndDt.equals(dt);
    }

    /**
     * Get month end day
     * @param dt Date
     * @return Month end date
     */
    public static String getMthEndDt(String dt) {
        if (!dt.endsWith("01")) {
            dt = dt.substring(0, 6) + "01";
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        fmt.setLenient(false);
        Calendar cal = Calendar.getInstance();
        cal.clear();
        try {
            cal.setTime(fmt.parse(dt));
            int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH, day);
            return fmt.format(cal.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Get minimum date
     * @param dts Dates
     * @return Minimum date
     */
    public static String getMinDt(String... dts) {

        if (dts == null) {
            return null;
        }

        List<String> list = new ArrayList<String>();

        for (String dt : dts) {
            if (ZYPCommonFunc.hasValue(dt) && dt.length() == 8) {
                list.add(dt);
            }
        }

        if (list.isEmpty()) {
            return null;
        } else {
            Collections.sort(list);
            return list.get(0);
        }
    }

    /**
     * Get maximum date
     * @param dts Dates
     * @return maximum date
     */
    public static String getMaxDt(String... dts) {

        if (dts == null) {
            return null;
        }

        List<String> list = new ArrayList<String>();

        for (String dt : dts) {
            if (ZYPCommonFunc.hasValue(dt) && dt.length() == 8) {
                list.add(dt);
            }
        }

        if (list.isEmpty()) {
            return null;
        } else {
            Collections.sort(list, Collections.reverseOrder());
            return list.get(0);
        }
    }

    /**
     * Divide billing period into months
     * @param contrCloDay Contract closing day
     * @param bllgFromDt Billing from
     * @param bllgThruDt Billing through
     * @return Months
     */
    public static List<Map<String, Object>> getMthDivInfoList(String contrCloDay, String bllgFromDt, String bllgThruDt) {

        List<Map<String, Object>> mthDivInfoList = new ArrayList<Map<String, Object>>();

        String fromDt = bllgFromDt;
        String thruDt = bllgThruDt;

        while (true) {

            Map<String, Object> mthDivInfo = new HashMap<String, Object>();

            // bllgToDt - 1 Month + 1 Day
            String curStDt = ZYPDateUtil.addDays(addMonths(thruDt, -1), 1);
            String preToDt = addMonths(thruDt, -1);

            mthDivInfo.put("DISC_YYYYMMDD", thruDt);
            mthDivInfo.put("BLLG_TO_DT", thruDt);

            int cmpResult = ZYPDateUtil.compare(fromDt, curStDt);

            BigDecimal mthAot = BigDecimal.ZERO;

            if (cmpResult == 0 || cmpResult == 1) {
                mthDivInfo.put("BLLG_FROM_DT", fromDt);
                mthAot = calcBllgCycleMthAot(contrCloDay, fromDt, thruDt);
            } else {
                mthDivInfo.put("BLLG_FROM_DT", curStDt);
                mthAot = calcBllgCycleMthAot(contrCloDay, curStDt, thruDt);
            }

            mthDivInfo.put("MONTH_NUM", mthAot);
            mthDivInfoList.add(mthDivInfo);

            // bllgFromDt < preBllgToDt
            if (cmpResult == 0 || cmpResult == 1) {
                break;
            } else if (cmpResult == -1) {
                thruDt = preToDt;
            } else {
                return null;
            }
        }

        return mthDivInfoList;
    }

    /**
     * Add the specified days to the specified date
     * @param date Date
     * @param daysAot Days
     * @return The resulting date
     */
    public static String addDays(String date, int daysAot) {
        if (hasValue(date)) {
            return ZYPDateUtil.addDays(date, daysAot);
        } else {
            return null;
        }
    }

    /**
     * addMonths
     * @param String date
     * @param int numberOfMonths
     * @return String
     */
    static String addMonths(String date, int numberOfMonths) {

        if (!hasValue(date)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
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
        if (isLastDateOfMonth(date)) {
            returnDate = getLastDateOfMonth(returnDate);
        }
        return returnDate;
    }

    /**
     * Returns true if the specified date is the last date(YYYYMMDD)
     * of the month, otherwise false.
     * @param date YYYMMDD
     * @return true if the specified date is the last date of the
     * month, false if not.
     */
    static boolean isLastDateOfMonth(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        return (day == lastDay);
    }

    /**
     * get the last date(YYYYMMDD) of the month of the specified date.
     * @param date YYYMMDD
     * @return the last date(YYYYMMDD) of the month
     */
    static String getLastDateOfMonth(String date) {

        if (!hasValue(date)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return format.format(cal.getTime());
    }

}
