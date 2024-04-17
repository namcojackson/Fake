package com.canon.cusa.s21.common.NSX.NSXC003001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import parts.common.EZDDebugOutput;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Aoyagi        Create          N/A
 * 04/22/2016   Hitachi         T.Iwamoto       Update          QC#7400
 *</pre>
 */
public class NSXC003001CalcRvsSchdDt implements ZYPConstant {

    /** MODE1_THRU */
    public static final String MODE1_THRU = "1";

    /** MODE2_FROM */
    public static final String MODE2_FROM = "2";

    /** MODE3_BOTH */
    public static final String MODE3_BOTH = "3";

    /** Format MONTH */
    private static final String MONTH = "MM";

    /** MONTH_END */
    private static final String MONTH_END = "99";

    /** NEXT_DAY */
    private static final String NEXT_DAY = "0";

    /** MONTHS_OF_YEAR */
    private static final int MONTHS_OF_YEAR = 12;

    /**
     * @param inBean CalcRvsSchdBean
     * @return CalcRvsSchdBean
     */
    public static CalcRvsSchdBean calcRvsSchdDt(CalcRvsSchdBean inBean) {

        String xxModeCd = inBean.getXxModeCd();
        String bllgSchdFromDt = inBean.getBllgSchdFromDt();
        String bllgSchdThruDt = inBean.getBllgSchdThruDt();
        Calendar fromDt = toCalendar(bllgSchdFromDt);
        Calendar thruDt = toCalendar(bllgSchdThruDt);
        String fromMonth = toMonth(fromDt);
        String thruMonth = toMonth(thruDt);
        Calendar bllgThruDt;

        List<CalcRvsSchdLineBean> lineList = inBean.getSkipLine();

        for (CalcRvsSchdLineBean lineBean : lineList) {

            String skipRecovMth = lineBean.getSkipRecovMth();
            String bllgRecovFlg = lineBean.getBllgRecovFlg();

            if (thruMonth.equals(skipRecovMth) && (MODE1_THRU.equals(xxModeCd) || MODE3_BOTH.equals(xxModeCd)) || fromMonth.equals(skipRecovMth) && (MODE2_FROM.equals(xxModeCd) || MODE3_BOTH.equals(xxModeCd))) {

                inBean.setSkipRecovTpCd(lineBean.getSkipRecovTpCd());
                inBean.setBllgSkipFlg(lineBean.getBllgSkipFlg());

                if (FLG_OFF_N.equals(bllgRecovFlg)) {
                    continue;
                }

                CalcRvsSchdLineBean nextLineBean;
                String curMonth = skipRecovMth;

                for (int j = 0; j < MONTHS_OF_YEAR; j++) {

                    nextLineBean = getNextMonthLine(curMonth, lineList);
                    String nextBllgSkipFlg = nextLineBean.getBllgSkipFlg();
                    // mod start 2016/04/22 CSA Defect#7400
                    String nextBllgRecovFlg = nextLineBean.getBllgRecovFlg();
                    // mod end 2016/04/22 CSA Defect#7400

                    if (FLG_ON_Y.equals(nextBllgSkipFlg) || FLG_ON_Y.equals(nextBllgRecovFlg)) {
                        curMonth = nextLineBean.getSkipRecovMth();
                        continue;
                    }

                    bllgThruDt = thruDt;

                    if (toMonth(bllgThruDt).compareTo(skipRecovMth) > 0) {
                        bllgThruDt.add(Calendar.YEAR, 1);
                        bllgThruDt.set(Calendar.MONTH, Integer.parseInt(skipRecovMth));
                    } else {
                        bllgThruDt.set(Calendar.MONTH, Integer.parseInt(skipRecovMth));
                    }

                    String contrCloDay = inBean.getContrCloDay();
                    int cloDay = Integer.parseInt(contrCloDay);
                    if (MONTH_END.equals(contrCloDay) || NEXT_DAY.equals(contrCloDay)) {
                        cloDay = bllgThruDt.getActualMaximum(Calendar.DATE);
                        bllgThruDt.set(Calendar.DATE, cloDay);
                    }
                    inBean.setRvsSchdDt(toString(bllgThruDt));
                    break;
                }
                break;
            } else {
                inBean.setSkipRecovTpCd(SKIP_RECOV_TP.NONE);
                inBean.setBllgSkipFlg(FLG_OFF_N);
            }
        }

        return inBean;
    }

    /**
     * @param inBean CalcRvsSchdBean
     * @return String
     */
    public static String getSkipRecovTp(CalcRvsSchdBean inBean) {

        String xxModeCd = inBean.getXxModeCd();
        String bllgSchdFromDt = inBean.getBllgSchdFromDt();
        String bllgSchdThruDt = inBean.getBllgSchdThruDt();
        Calendar fromDt = toCalendar(bllgSchdFromDt);
        Calendar thruDt = toCalendar(bllgSchdThruDt);
        String fromMonth = toMonth(fromDt);
        String thruMonth = toMonth(thruDt);

        String skipRecovTp = SKIP_RECOV_TP.NONE;

        for (CalcRvsSchdLineBean lineBean : inBean.getSkipLine()) {

            String skipRecovMth = lineBean.getSkipRecovMth();

            if (MODE1_THRU.equals(xxModeCd) || MODE3_BOTH.equals(xxModeCd)) {

                if (thruMonth.equals(skipRecovMth)) {
                    skipRecovTp = lineBean.getSkipRecovTpCd();
                    break;
                }

            } else if (MODE2_FROM.equals(xxModeCd) || MODE3_BOTH.equals(xxModeCd)) {

                if (fromMonth.equals(skipRecovMth)) {
                    skipRecovTp = lineBean.getSkipRecovTpCd();
                    break;
                }
            }
        }
        return skipRecovTp;
    }

    private static CalcRvsSchdLineBean getNextMonthLine(String curMonth, List<CalcRvsSchdLineBean> lineList) {

        CalcRvsSchdLineBean outLineBean = new CalcRvsSchdLineBean();

        int i = 0;

        for (CalcRvsSchdLineBean lineBean : lineList) {

            String skipRecovMth = lineBean.getSkipRecovMth();
            if (curMonth.equals(skipRecovMth)) {

                if (i < lineList.size() - 1) {
                    outLineBean = lineList.get(i + 1);
                    return outLineBean;
                }
            }
            i++;
        }
        // First month
        if (lineList.size() > 0) {
            outLineBean = lineList.get(0);
        }

        return outLineBean;
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

    private static String toMonth(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat(MONTH);
        return sdf.format(cal.getTime());
    }
}
