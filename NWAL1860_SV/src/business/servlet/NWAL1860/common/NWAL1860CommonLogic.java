/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1860.common;


import static business.servlet.NWAL1860.constant.NWAL1860Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1860.constant.NWAL1860Constant.BTN_CMN_CLS;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import business.servlet.NWAL1860.NWAL1860BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.security.as.common.utils.S21DateUtil;

/**
 *<pre>
 * NWAL1860CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1860CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * addCheckItem
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NWAL1860BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.ordQty);
        scrnMsg.addCheckItem(scrnMsg.shpgIntvlCd);
        scrnMsg.addCheckItem(scrnMsg.xxDay);
        scrnMsg.addCheckItem(scrnMsg.xxMthDt_ST);
        scrnMsg.addCheckItem(scrnMsg.xxYrDt_ST);
        scrnMsg.addCheckItem(scrnMsg.xxMthDt_EN);
        scrnMsg.addCheckItem(scrnMsg.xxYrDt_EN);
        scrnMsg.putErrorScreen();
    }

    /**
     * setInputParam
     * @param scrnMsg NWAL1860BMsg
     * @param params Object[]
     * @return boolean
     */
    public static boolean setInputParam(NWAL1860BMsg scrnMsg, Object[] params) {
        // Delivery QTY 
        if (ZYPCommonFunc.hasValue((EZDBBigDecimalItem) params[1])) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordQty, (EZDBBigDecimalItem) params[1]);
        }
        // Shipping Interval
        if (ZYPCommonFunc.hasValue((EZDBStringItem) params[2])) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shpgIntvlCd, (EZDBStringItem) params[2]);
        }
        // Valid Date from 
        if (ZYPCommonFunc.hasValue((EZDBDateItem) params[3])) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.schdAgmtVldFromDt, (EZDBDateItem) params[3]);
        } else {
            // Mandatory Error
            scrnMsg.setMessageInfo("NWAM0093E");
            return false;
        }
        // Valid Date Through 
        if (ZYPCommonFunc.hasValue((EZDBDateItem) params[4])) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.schdAgmtVldThruDt, (EZDBDateItem) params[4]);
        } else {
            // Mandatory Error
            scrnMsg.setMessageInfo("NWAM0093E");
            return false;
        }
        // Line#s
        if (params[5] != null) {
            if (params[5] instanceof List) {
                StringBuffer sb = new StringBuffer();
                boolean first = true;
                for (Object obj : (List) params[5]) {
                    if (obj instanceof EZDBBigDecimalItem) {
                        if (!first) {
                            sb.append(",");
                        }
                        sb.append(((EZDBBigDecimalItem) obj).getValue());
                        first = false;
                    }
                }
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxTrxLineNumListTxt, sb.toString());
            }

        }
        // Planed Max Date
        if (ZYPCommonFunc.hasValue((EZDBDateItem) params[6])) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.pddDt, (EZDBDateItem) params[6]);
        }
        return true;
    }

    /**
     * controlScreenFields
     * @param scrnMsg NWAL1860BMsg
     */
    public static final void controlScreenFields(NWAL1860BMsg scrnMsg) {

        scrnMsg.xxTrxLineNumListTxt.setInputProtected(true);
        scrnMsg.ordQty.setInputProtected(false);
        scrnMsg.shpgIntvlCd.setInputProtected(false);
        scrnMsg.xxDay.setInputProtected(false);
        scrnMsg.xxMthDt_ST.setInputProtected(false);
        scrnMsg.xxYrDt_ST.setInputProtected(false);
        scrnMsg.xxMthDt_EN.setInputProtected(false);
        scrnMsg.xxYrDt_EN.setInputProtected(false);
        scrnMsg.xxMthDt_ST.setInputProtected(false);
        scrnMsg.schdAgmtVldFromDt.setInputProtected(true);
        scrnMsg.schdAgmtVldThruDt.setInputProtected(true);
        scrnMsg.pddDt.setInputProtected(true);
    }

    /**
     * initVal
     * @param scrnMsg NWAL1860BMsg
     */
    public static final void initVal(NWAL1860BMsg scrnMsg) {
        //int thisYear = Integer.parseInt(ZYPDateUtil.getSalesDate().substring(0, 4));
        // Initialize PullDown Value
        scrnMsg.shpgIntvlCd.clear();
        scrnMsg.xxDay.clear();

        // Start Date
        Date dateFrom = toDateByString(scrnMsg.schdAgmtVldFromDt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMthDt_ST, toStringByDate(dateFrom, "MM"));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxYrDt_ST, toStringByDate(dateFrom, "yyyy"));
        // End Date
        Date dateTo = toDateByString(scrnMsg.schdAgmtVldThruDt.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMthDt_EN, toStringByDate(dateTo, "MM"));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxYrDt_EN, toStringByDate(dateTo, "yyyy"));
    }

    /**
     * clear
     * @param scrnMsg NWAL1860BMsg
     * @param params Object[]
     */
    public static final void clear(NWAL1860BMsg scrnMsg, Object[] params) {
        // scrnMsg.xxTrxLineNumListTxt.clear(); // Del S21_NA#13039
        scrnMsg.ordQty.clear();

        // Valid Date from 
        if (ZYPCommonFunc.hasValue((EZDBDateItem) params[3])) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.schdAgmtVldFromDt, (EZDBDateItem) params[3]);
        } else {
            // Mandatory Error
            scrnMsg.setMessageInfo("NWAM0093E");
        }
        // Valid Date Through 
        if (ZYPCommonFunc.hasValue((EZDBDateItem) params[4])) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.schdAgmtVldThruDt, (EZDBDateItem) params[4]);
        } else {
            // Mandatory Error
            scrnMsg.setMessageInfo("NWAM0093E");
        }
        initVal(scrnMsg);
    }

    /**
     * checkClose
     * @param scrnMsg NWAL1860BMsg
     */
    public static final void checkClose(NWAL1860BMsg scrnMsg) {
        addCheckItem(scrnMsg);

        // Start/End Date
        String starDt = new StringBuffer().append(scrnMsg.xxYrDt_ST.getValue()).append(scrnMsg.xxMthDt_ST.getValue()).append(scrnMsg.xxDay.getValue()).toString();
        String endDt = new StringBuffer().append(scrnMsg.xxYrDt_EN.getValue()).append(scrnMsg.xxMthDt_EN.getValue()).append(scrnMsg.xxDay.getValue()).toString();

        if (0 < starDt.compareTo(endDt)) {
            // End Deliveries Date < Start Deliveries Date
            scrnMsg.xxDay.setErrorInfo(1, "ZZZM9010E", new String[]{"Start Deliveries Date", "End Deliveries Date"});
            scrnMsg.xxMthDt_ST.setErrorInfo(1, "ZZZM9010E", new String[]{"Start Deliveries Date", "End Deliveries Date"});
            scrnMsg.xxYrDt_ST.setErrorInfo(1, "ZZZM9010E", new String[]{"Start Deliveries Date", "End Deliveries Date"});
            scrnMsg.xxMthDt_EN.setErrorInfo(1, "ZZZM9010E", new String[]{"Start Deliveries Date", "End Deliveries Date"});
            scrnMsg.xxYrDt_EN.setErrorInfo(1, "ZZZM9010E", new String[]{"Start Deliveries Date", "End Deliveries Date"});
        }

        String vldDtFrom = ZYPDateUtil.formatEzd8ToDisp(scrnMsg.schdAgmtVldFromDt.getValue());
        if (0 < scrnMsg.schdAgmtVldFromDt.getValue().compareTo(starDt)) {
            // Start Deliveries Date < Valid Date From 
            scrnMsg.xxDay.setErrorInfo(1, "NWAM0711E", new String[]{"Start Deliveries Date", vldDtFrom});
            scrnMsg.xxMthDt_ST.setErrorInfo(1, "NWAM0711E", new String[]{"Start Deliveries Date", vldDtFrom});
            scrnMsg.xxYrDt_ST.setErrorInfo(1, "NWAM0711E", new String[]{"Start Deliveries Date", vldDtFrom});
        }

        if (0 < scrnMsg.schdAgmtVldFromDt.getValue().compareTo(endDt)) {
            // End Deliveries Date < Valid Date From 
            scrnMsg.xxDay.setErrorInfo(1, "NWAM0711E", new String[]{"End Deliveries Date", vldDtFrom});
            scrnMsg.xxMthDt_EN.setErrorInfo(1, "NWAM0711E", new String[]{"End Deliveries Date", vldDtFrom});
            scrnMsg.xxYrDt_EN.setErrorInfo(1, "NWAM0711E", new String[]{"End Deliveries Date", vldDtFrom});
        }

        String vldDtThru = ZYPDateUtil.formatEzd8ToDisp(scrnMsg.schdAgmtVldThruDt.getValue());
        if (0 < starDt.compareTo(scrnMsg.schdAgmtVldThruDt.getValue())) {
            // Valid Date Thru < Start Deliveries Date
            scrnMsg.xxDay.setErrorInfo(1, "ZZZM9010E", new String[]{"Start Deliveries Date", vldDtThru});
            scrnMsg.xxMthDt_ST.setErrorInfo(1, "ZZZM9010E", new String[]{"Start Deliveries Date", vldDtThru});
            scrnMsg.xxYrDt_ST.setErrorInfo(1, "ZZZM9010E", new String[]{"Start Deliveries Date", vldDtThru});
        }

        if (0 < endDt.compareTo(scrnMsg.schdAgmtVldThruDt.getValue())) {
            // Valid Date Thru  < End Deliveries Date
            scrnMsg.xxDay.setErrorInfo(1, "ZZZM9010E", new String[]{"End Deliveries Date", vldDtThru});
            scrnMsg.xxMthDt_EN.setErrorInfo(1, "ZZZM9010E", new String[]{"End Deliveries Date", vldDtThru});
            scrnMsg.xxYrDt_EN.setErrorInfo(1, "ZZZM9010E", new String[]{"End Deliveries Date", vldDtThru});
        }

        String maxDt = ZYPDateUtil.formatEzd8ToDisp(scrnMsg.pddDt.getValue());
        if (ZYPCommonFunc.hasValue(scrnMsg.pddDt)) {
            // Start Deliveries Date <= Planned Max Date
            if (0 <= scrnMsg.pddDt.getValue().compareTo(starDt)) {
                scrnMsg.xxDay.setErrorInfo(1, "NWAM0712E", new String[]{"Start Deliveries Date", maxDt});
                scrnMsg.xxMthDt_ST.setErrorInfo(1, "NWAM0712E", new String[]{"Start Deliveries Date", maxDt});
                scrnMsg.xxYrDt_ST.setErrorInfo(1, "NWAM0712E", new String[]{"Start Deliveries Date", maxDt});
            }

            if (0 <= scrnMsg.pddDt.getValue().compareTo(endDt)) {
                // End Deliveries Date <= Planned Max Date
                scrnMsg.xxDay.setErrorInfo(1, "NWAM0712E", new String[]{"End Deliveries Date", maxDt});
                scrnMsg.xxMthDt_EN.setErrorInfo(1, "NWAM0712E", new String[]{"End Deliveries Date", maxDt});
                scrnMsg.xxYrDt_EN.setErrorInfo(1, "NWAM0712E", new String[]{"End Deliveries Date", maxDt});
            }
        }
        addCheckItem(scrnMsg);
    }

    /**
     * setReturnValues
     * @param scrnMsg NWAL1860BMsg
     * @param params Object[]
     */
    public static final void setReturnValues(NWAL1860BMsg scrnMsg, Object[] params) {
        // Delivery Date
        List<String> delyDateList = createDeliveryDateList(scrnMsg);
        int i = 0;
        for (String date : delyDateList) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxPopPrm_A, date);
            i++;
        }
        scrnMsg.A.setValidCount(delyDateList.size());
        String suffix = null;
        if (params[7] instanceof String) {
            suffix = (String) params[7];
        }
        if (params[0] instanceof EZDMsgArray) {
            EZDMsg.copy(scrnMsg.A, "A", (EZDMsgArray) params[0], (suffix == null) ? "" : suffix);
        }
        // QTY
        ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[1], scrnMsg.ordQty);
        // Interval
        ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[2], scrnMsg.shpgIntvlCd);
    }

    /**
     * createDeliveryDateList
     * @param scrnMsg NWAL1860BMsg
     * @return List<String>
     */
    private static final List<String> createDeliveryDateList(NWAL1860BMsg scrnMsg) {

        String startDtYYYYMM = new StringBuffer().append(scrnMsg.xxYrDt_ST.getValue()).append(scrnMsg.xxMthDt_ST.getValue()).toString();
        String endDtYYYYMM = new StringBuffer().append(scrnMsg.xxYrDt_EN.getValue()).append(scrnMsg.xxMthDt_EN.getValue()).toString();
        List<String> delyDateList = new ArrayList<String>(400);
        try {
            int diffMonth = getDiffMonth(startDtYYYYMM, endDtYYYYMM);
            if (diffMonth == 0) {
                diffMonth = 1;
            } else {
                diffMonth = diffMonth + 1;
            }
            int lineCount = getLineCount(diffMonth, scrnMsg.shpgIntvlMthNum.getValue());
            if (scrnMsg.A.length() < lineCount) {
                lineCount = scrnMsg.A.length();
            }

            // Convert Calendar
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String starDt01 = new StringBuffer().append(scrnMsg.xxYrDt_ST.getValue()).append(scrnMsg.xxMthDt_ST.getValue()).append("01").toString();
            Date startDtDate = format.parse(starDt01);
            Calendar startDtCal = Calendar.getInstance();
            startDtCal.setTime(startDtDate);

            int endOfDate = startDtCal.getActualMaximum(Calendar.DATE);
            if (endOfDate < Integer.parseInt(scrnMsg.xxDay.getValue())) {
                startDtCal.set(Calendar.DATE, endOfDate);
            }

            int cnt = 0;
            int intvl = scrnMsg.shpgIntvlMthNum.getValueInt();
            int addMonth = intvl;
            // Set First Date
            Calendar delyDate = getDelyDate(startDtCal, Integer.parseInt(scrnMsg.xxDay.getValue()), 0);
            delyDateList.add(format.format(delyDate.getTime()));
            cnt++;
            // After first date
            while (cnt < lineCount) {
                delyDate = getDelyDate(startDtCal, Integer.parseInt(scrnMsg.xxDay.getValue()), addMonth);
                delyDateList.add(format.format(delyDate.getTime()));
                addMonth = addMonth + intvl;
                cnt++;
            }

        } catch (ParseException e) {
            scrnMsg.setMessageInfo("NWAM0713E");
        }
        return delyDateList;
    }

    /**
     * getDiffMonth
     * @param starDt String
     * @param endDt String
     * @return int
     * @throws ParseException
     */
    private static final int getDiffMonth(String starDt, String endDt) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Date starDtD = format.parse(starDt);
        Date endDtD = format.parse(endDt);

        Calendar starDtC = Calendar.getInstance();
        starDtC.setTime(starDtD);
        starDtC.set(Calendar.DATE, 1);
        Calendar endDtC = Calendar.getInstance();
        endDtC.setTime(endDtD);
        endDtC.set(Calendar.DATE, 1);

        int count = 0;
        // starDt < endDt
        while (starDtC.before(endDtC)) {
            starDtC.add(Calendar.MONTH, 1);
            count++;
        }
        return count;
    }

    /**
     * getLineCount
     * @param diffMonth int
     * @param shpgIntvlMthNum BigDecimal
     * @return int
     */
    private static final int getLineCount(int diffMonth, BigDecimal shpgIntvlMthNum) {

        if (diffMonth == 0) {
            return 0;
        } else {
            BigDecimal diffMonthBd = new BigDecimal(diffMonth);
            BigDecimal lineCount = diffMonthBd.divide(shpgIntvlMthNum, 0, BigDecimal.ROUND_UP);
            return lineCount.intValue();
        }
    }

    /**
     * getDelyDate
     * @param startDate Calendar
     * @param inputDay int
     * @param addMonth int
     * @return Calendar
     */
    private static final Calendar getDelyDate(Calendar startDate, int inputDay, int addMonth) {
        Calendar delyDate = (Calendar) startDate.clone();
        delyDate.add(Calendar.MONTH, addMonth);
        int date = delyDate.get(Calendar.DATE);
        if (date != inputDay) {
            int endOfDate = delyDate.getActualMaximum(Calendar.DATE);
            if (inputDay <= endOfDate) {
                delyDate.set(Calendar.DATE, inputDay);
            } else {
                delyDate.set(Calendar.DATE, endOfDate);
            }
        }
        return delyDate;
    }

    private static Date toDateByString(String s) {
        SimpleDateFormat parserToDate = new SimpleDateFormat("yyyyMMdd");
        Date date;
        try {
            date = parserToDate.parse(s);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    private static String toStringByDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        return (new SimpleDateFormat(format)).format(date);
    }


}
