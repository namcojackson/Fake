package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import parts.common.EZDDebugOutput;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Aoyagi        Create          N/A
 * 06/23/2016   Hitachi         T.Kanasaka      Update          QC#10524
 * 07/04/2016   Hitachi         T.Kanasaka      Update          QC#10524
 * 01/16/2017   Hitachi         Y.Takeno        Update          QC#16970
 * 07/13/2017   Hitachi         T.Tomita        Update          QC#19904
 * 13/12/2017   Hitachi         U.Kim           Update          QC#18779
 *</pre>
 */
public class NSXC003001CalcNextBllgDt {

    /** MONTH_END */
    private static final String MONTH_END = "99";

    /** NEXT_DAY */
    private static final String NEXT_DAY = "0";

    // START 2016/06/23 T.Kanasaka [QC#10524, MOD]
    /**
     * @param inBean CalcNextBllgDtBean
     * @return String
     */
    public static String calcTermAmt(CalcNextBllgDtBean inBean) {

        String bllgTmgTp = inBean.getBllgTmgTp();
        String bllgSchdFromDt = inBean.getBllgSchdFromDt();
        String bllgSchdThruDt = inBean.getBllgSchdThruDt();
        String contrBllgDay = inBean.getContrBllgDay();
//        String invUpToDt = inBean.getInvUpToDt();
        Calendar fromDt = toCalendar(bllgSchdFromDt);
        Calendar thruDt = toCalendar(bllgSchdThruDt);
        Calendar bllgDt;
        Calendar nextBllgDt = (Calendar) thruDt.clone();
        Calendar contrEffFromDt = toCalendar(inBean.getContrEffFromDt());
        // START 2017/12/13 U.Kim [QC#18779, ADD]
        boolean minusFlg = false;
        // END 2017/12/13 U.Kim [QC#18779, ADD]

        if (BLLG_TMG_TP.ADVANCE.equals(bllgTmgTp)) {
//            if (!hasValue(invUpToDt)) {
            // START 2017/12/13 U.Kim [QC#18779, ADD]
            if (Integer.parseInt(contrBllgDay) < 0) {
                minusFlg = true;
            }
            // END 2017/12/13 U.Kim [QC#18779, ADD]
            if (fromDt.compareTo(contrEffFromDt) == 0) {

                bllgDt = getBllgFromDate(bllgSchdFromDt, bllgSchdThruDt, contrBllgDay);

                if (fromDt.compareTo(bllgDt) >= 0) {
                    nextBllgDt = bllgDt;
                } else {
                    nextBllgDt = fromDt;
                }
            } else {

                bllgDt = getBllgFromDate(bllgSchdFromDt, bllgSchdThruDt, contrBllgDay);

                if (fromDt.compareTo(bllgDt) >= 0) {
                    nextBllgDt = bllgDt;
                } else {
                    bllgDt.add(Calendar.MONTH, -1);
                    // START 2016/07/04 T.Kanasaka [QC#10524, ADD]
                    if (MONTH_END.equals(contrBllgDay)) {
                        int monthEnd = bllgDt.getActualMaximum(Calendar.DATE);
                        bllgDt.set(Calendar.DATE, monthEnd);
                    }
                    // END 2016/07/04 T.Kanasaka [QC#10524, ADD]
                    nextBllgDt = bllgDt;
                }
                // del start 2017/07/13 QC#19904
//                if (nextBllgDt.compareTo(contrEffFromDt) < 0) {
//                    nextBllgDt = contrEffFromDt;
//                }
                // del end   2017/07/13 QC#19904
            }
            // add start 2017/07/13 QC#19907
            // START 2017/12/13 U.Kim [QC#18779, MOD]
            //if (nextBllgDt.compareTo(contrEffFromDt) < 0) {
            if (nextBllgDt.compareTo(contrEffFromDt) < 0 && !minusFlg) {
            // END 2017/12/13 U.Kim [QC#18779, MOD]
                nextBllgDt = contrEffFromDt;
            }
            // and end   2017/07/13 QC#19907
        }

        if (BLLG_TMG_TP.ARREARS.equals(bllgTmgTp)) {

            bllgDt = getBllgFromDate(bllgSchdThruDt, contrBllgDay);
            if (thruDt.compareTo(bllgDt) <= 0) {
                nextBllgDt = bllgDt;
            } else {
                bllgDt.add(Calendar.MONTH, 1);
                nextBllgDt = bllgDt;
            }
        }
        return toString(nextBllgDt);
    }
    // END 2016/06/23 T.Kanasaka [QC#10524, MOD]

    private static Calendar getBllgFromDate(String bllgSchdFromDt, String bllgSchdThruDt, String contrBllgDay) {

        Calendar fromDt = toCalendar(bllgSchdFromDt);
        // START 2017/01/16 [QC#16970, DEL]
        // Calendar thruDt = toCalendar(bllgSchdThruDt);
        // END   2017/01/16 [QC#16970, DEL]
        // START 2017/12/13 U.Kim [QC#18779, ADD]
        if(Integer.parseInt(contrBllgDay) < 0){
            fromDt.add(Calendar.DATE, Integer.parseInt(contrBllgDay));
        // END 2017/12/13 U.Kim [QC#18779, ADD]
        // START 2017/12/13 U.Kim [QC#18779, MOD]
        //if (MONTH_END.equals(contrBllgDay)) {
        } else if (MONTH_END.equals(contrBllgDay)) {
        // END 2017/12/13 U.Kim [QC#18779, MOD]
            int monthEnd = fromDt.getActualMaximum(Calendar.DATE);
            fromDt.set(Calendar.DATE, monthEnd);
        // START 2017/01/16 [QC#16970, DEL]
        // } else if (NEXT_DAY.equals(contrBllgDay)) {
        //     thruDt.add(Calendar.DATE, 1);
        //     fromDt = thruDt;
        // END   2017/01/16 [QC#16970, DEL]
        // START 2017/01/16 [QC#16970, MOD]
        // } else {
        } else if (!NEXT_DAY.equals(contrBllgDay)) {
        // END   2017/01/16 [QC#16970, MOD]
            fromDt.set(Calendar.DATE, Integer.parseInt(contrBllgDay));
        }
        return fromDt;
    }

    private static Calendar getBllgFromDate(String bllgSchdThruDt, String contrBllgDay) {

        Calendar thruDt = toCalendar(bllgSchdThruDt);

        if (MONTH_END.equals(contrBllgDay)) {
            int monthEnd = thruDt.getActualMaximum(Calendar.DATE);
            thruDt.set(Calendar.DATE, monthEnd);
        } else if (NEXT_DAY.equals(contrBllgDay)) {
            thruDt.add(Calendar.DATE, 1);
        } else {
            thruDt.set(Calendar.DATE, Integer.parseInt(contrBllgDay));
        }
        return thruDt;
    }

    private static Calendar toCalendar(String strDate) {

        if (!hasValue(strDate)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(strDate);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return cal;
    }

    private static String toString(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        return sdf.format(cal.getTime());
    }
}
