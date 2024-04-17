package business.servlet.ZZZL0040.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.ZZZL0040.ZZZL0040BMsg;
import business.servlet.ZZZL0040.constant.ZZZL0040Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class ZZZL0040CommonLogic implements ZZZL0040Constant {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * Initial Common Button setting method.
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        // Common Buttons.
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Setting data to all pulldowns for initializing.
     * @param handler EZDCommonHandler
     */
    public static void initPullDowns(ZZZL0040BMsg scrnMsg) {

        // From Hours
        for (int i = 0; i < Hour.length; i++) {
            scrnMsg.xxHrs_FC.no(i).setValue(Hour[i]);
            scrnMsg.xxHrs_FD.no(i).setValue(Hour[i]);
        }
        // From Min
        for (int i = 0; i < Min.length; i++) {
            scrnMsg.xxMn_FC.no(i).setValue(Min[i]);
            scrnMsg.xxMn_FD.no(i).setValue(Min[i]);
        }
        // To Hour
        for (int i = 0; i < Hour.length; i++) {
            scrnMsg.xxHrs_TC.no(i).setValue(Hour[i]);
            scrnMsg.xxHrs_TD.no(i).setValue(Hour[i]);
        }
        // To Min
        for (int i = 0; i < Min.length; i++) {
            scrnMsg.xxMn_TC.no(i).setValue(Min[i]);
            scrnMsg.xxMn_TD.no(i).setValue(Min[i]);
        }

        // Set Default Date for initializing.
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        scrnMsg.xxFromDt.setValue(today);
        scrnMsg.xxToDt.setValue(today);
        scrnMsg.xxHrs_FS.setValue(DEF_HRS_FROM);
        scrnMsg.xxMn_FS.setValue(DEF_MIN_FROM);
        scrnMsg.xxHrs_TS.setValue(DEF_HRS_TO);
        scrnMsg.xxMn_TS.setValue(DEF_MIN_TO);
        scrnMsg.xxTotCnt.setValue(BigDecimal.ZERO);

        // Set Default radio button
        scrnMsg.xxRadioBtn.setValue(BIZ_PROC_TIME_RANK);
    }

    /**
     * Method name: checkItem
     * <dd>The method explanation: Add check item.
     * <dd>Remarks:
     * @param scrnMsg Screen Component Interface Message
     */
    public static void checkItem(ZZZL0040BMsg scrnMsg) {

        // add check items.
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
    }

    public static synchronized void correlativeTimeCheck(ZZZL0040BMsg scrnMsg) {

        combineDateFormat(scrnMsg);

        String frDateNm = scrnMsg.xxFromDt.getNameForMessage();
        String toDateNm = scrnMsg.xxToDt.getNameForMessage();
        String frHourNm = scrnMsg.xxHrs_FS.getNameForMessage();
        String frMinNm = scrnMsg.xxMn_FS.getNameForMessage();
        String toHourNm = scrnMsg.xxHrs_TS.getNameForMessage();
        String toMinNm = scrnMsg.xxMn_TS.getNameForMessage();

        if (!scrnMsg.xxFromDt.isClear() && !scrnMsg.xxToDt.isClear()) {
            int cmpDate = ZYPDateUtil.compare(scrnMsg.xxFromDt.getValue(), scrnMsg.xxToDt.getValue());

            // Bigger the 'From Date' is error
            if (cmpDate > 0) {
                scrnMsg.xxFromDt.setErrorInfo(1, "ZZZM9010E", new String[] {frDateNm, toDateNm });
                scrnMsg.xxToDt.setErrorInfo(1, "ZZZM9010E", new String[] {frDateNm, toDateNm });
                return;
            } else if (cmpDate == 0) {

                if (!scrnMsg.xxHrs_FS.isClear() && !scrnMsg.xxHrs_TS.isClear()) {
                    int frHrs = Integer.parseInt(scrnMsg.xxHrs_FS.getValue());
                    int toHrs = Integer.parseInt(scrnMsg.xxHrs_TS.getValue());

                    // Bigger the 'From Hour' is error
                    if (frHrs > toHrs) {
                        scrnMsg.xxHrs_FS.setErrorInfo(1, "ZZZM9010E", new String[] {frHourNm, toHourNm });
                        scrnMsg.xxHrs_TS.setErrorInfo(1, "ZZZM9010E", new String[] {frHourNm, toHourNm });
                        return;
                    } else if (frHrs == toHrs) {
                        if (!scrnMsg.xxMn_FS.isClear() && !scrnMsg.xxMn_TS.isClear()) {
                            int frMin = Integer.parseInt(scrnMsg.xxMn_FS.getValue());
                            int toMin = Integer.parseInt(scrnMsg.xxMn_TS.getValue());

                            // Bigger the 'From Minutes' is error
                            if (frMin > toMin) {
                                scrnMsg.xxMn_FS.setErrorInfo(1, "ZZZM9010E", new String[] {frMinNm, toMinNm });
                                scrnMsg.xxMn_TS.setErrorInfo(1, "ZZZM9010E", new String[] {frMinNm, toMinNm });
                                return;
                            }
                        }
                    }
                }
            }
            // Comparing From date and End date whether over 31days or
            // not.
            combineDateFormat(scrnMsg);
            String fromDt = scrnMsg.bizStartTs.getValue();
            String toDt = scrnMsg.bizEndTs.getValue();
            long diff = 0;
            long stTm = 0;
            long edTm = 0;

            try {
                stTm = dateFormat.parse(fromDt).getTime();
                edTm = dateFormat.parse(toDt).getTime();
            } catch (ParseException e) {
            }

            long day31 = 1000L * 60L * 60L * 24L * 31L;
            long hour1 = 1000L * 60L * 60L;

            diff = edTm - stTm;
            if (diff > day31) {
                scrnMsg.xxFromDt.setErrorInfo(1, "ZZZM9052E", new String[] {"Operation period", "31days" });
                scrnMsg.xxToDt.setErrorInfo(1, "ZZZM9052E", new String[] {"Operation period", "31days" });
                scrnMsg.xxHrs_FS.setErrorInfo(1, "ZZZM9052E", new String[] {"Operation period", "31days" });
                scrnMsg.xxMn_FS.setErrorInfo(1, "ZZZM9052E", new String[] {"Operation period", "31days" });
                scrnMsg.xxHrs_TS.setErrorInfo(1, "ZZZM9052E", new String[] {"Operation period", "31days" });
                scrnMsg.xxMn_TS.setErrorInfo(1, "ZZZM9052E", new String[] {"Operation period", "31days" });
            } else if (diff < hour1) {
                scrnMsg.xxHrs_FS.setErrorInfo(1, "ZZZM9053E", new String[] {"Operation period", "1hour" });
                scrnMsg.xxMn_FS.setErrorInfo(1, "ZZZM9053E", new String[] {"Operation period", "1hour" });
                scrnMsg.xxHrs_TS.setErrorInfo(1, "ZZZM9053E", new String[] {"Operation period", "1hour" });
                scrnMsg.xxMn_TS.setErrorInfo(1, "ZZZM9053E", new String[] {"Operation period", "1hour" });
            }
        }
    }

    public static ZZZL0040BMsg combineDateFormat(ZZZL0040BMsg scrnMsg) {
        // From Time
        if (!scrnMsg.xxFromDt.isClear()) {
            String frDate = scrnMsg.xxFromDt.getValue();
            String frHour = null;
            String frMin = null;

            if (!scrnMsg.xxHrs_FS.isClear()) {
                frHour = scrnMsg.xxHrs_FS.getValue();
            } else {
                frHour = DEF_HRS_FROM;
            }

            if (!scrnMsg.xxMn_FS.isClear()) {
                frMin = scrnMsg.xxMn_FS.getValue();
            } else {
                frMin = DEF_MIN_FROM;
            }
            scrnMsg.bizStartTs.setValue(frDate + frHour + frMin + DEF_SEC_MILLI_FROM);
        }

        // To Time
        if (!scrnMsg.xxToDt.isClear()) {
            String frDate = scrnMsg.xxToDt.getValue();
            String frHour = null;
            String frMin = null;

            if (!scrnMsg.xxHrs_TS.isClear()) {
                frHour = scrnMsg.xxHrs_TS.getValue();
            } else {
                frHour = DEF_HRS_TO;
            }

            if (!scrnMsg.xxMn_TS.isClear()) {
                frMin = scrnMsg.xxMn_TS.getValue();
            } else {
                frMin = DEF_MIN_TO;
            }
            scrnMsg.bizEndTs.setValue(frDate + frHour + frMin + DEF_SEC_MILLI_TO);
        }
        return scrnMsg;
    }

    /**
     * Method name: changeTableColorByRow
     * <dd>The method explanation: change Table Coloer by Row.
     * <dd>Remarks:
     * @param bMsg Screen Component Interface Message
     */
    public static void changeTableColorByRow(ZZZL0040BMsg bMsg) {

        // set alternate rows back-ground color
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, bMsg);
        tblColor.setAlternateRowsBG("A_LeftTBL", bMsg.A);
        tblColor.setAlternateRowsBG("A_RightTBL", bMsg.A);
    }
}
