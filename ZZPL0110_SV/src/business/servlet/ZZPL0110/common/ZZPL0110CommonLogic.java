/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0110.common;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import parts.common.EZDBMsg;
import business.servlet.ZZPL0110.ZZPL0110BMsg;
import business.servlet.ZZPL0110.constant.ZZPL0110Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/13   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0110CommonLogic {

    /**
     * @param handler S21 Common Handler
     */
    public static void setButtonProperties_INIT(S21CommonHandler handler) {
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_BTN1[0], ZZPL0110Constant.BTN_CMN_BTN1[1], ZZPL0110Constant.BTN_CMN_BTN1[2], 0, null);
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_BTN2[0], ZZPL0110Constant.BTN_CMN_BTN2[1], ZZPL0110Constant.BTN_CMN_BTN2[2], 0, null);
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_BTN3[0], ZZPL0110Constant.BTN_CMN_BTN3[1], ZZPL0110Constant.BTN_CMN_BTN3[2], 0, null);
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_BTN4[0], ZZPL0110Constant.BTN_CMN_BTN4[1], ZZPL0110Constant.BTN_CMN_BTN4[2], 0, null);
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_BTN5[0], ZZPL0110Constant.BTN_CMN_BTN5[1], ZZPL0110Constant.BTN_CMN_BTN5[2], 0, null);
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_BTN6[0], ZZPL0110Constant.BTN_CMN_BTN6[1], ZZPL0110Constant.BTN_CMN_BTN6[2], 0, null);
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_BTN7[0], ZZPL0110Constant.BTN_CMN_BTN7[1], ZZPL0110Constant.BTN_CMN_BTN7[2], 0, null);
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_BTN8[0], ZZPL0110Constant.BTN_CMN_BTN8[1], ZZPL0110Constant.BTN_CMN_BTN8[2], 1, null);
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_BTN9[0], ZZPL0110Constant.BTN_CMN_BTN9[1], ZZPL0110Constant.BTN_CMN_BTN9[2], 0, null);
        handler.setButtonProperties(ZZPL0110Constant.BTN_CMN_RETURN[0], ZZPL0110Constant.BTN_CMN_RETURN[1], ZZPL0110Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Change the row color on table
     * @param bMsg EZDBMsg
     * @param scrnId ScreenID
     */
    public static void setTableRowColor(EZDBMsg bMsg, String scrnId) {
        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        // set alternate rows background color
        S21TableColorController tblController = new S21TableColorController(ZZPL0110Constant.SCREENID_SCRN00, scrnMsg);
        tblController.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * Set activation pulldown list of table A
     * @param bMsg EZDBMsg
     */
    public static void setActivationPulldownList(EZDBMsg bMsg) {
        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        scrnMsg.A.setInputProtected(true);

        // if "Report Job Status" is "FAIL", "CANCELED", or
        // "SUSPENDED", activate pulldown list
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String rptSts = scrnMsg.A.no(i).rptJobStsTxt_A.getValue();
            if (Arrays.asList(ZZPL0110Constant.RPT_JOB_STS_CHANGEABLE).contains(rptSts)) {
                scrnMsg.A.no(i).rptJobStsTxt_CS.setInputProtected(false);
            }
        }
    }

    /**
     * Convert job start/end time format for display
     * @param bMsg EZDBMsg
     */
    public static void convertJobTimeFormat(EZDBMsg bMsg) {
        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        SimpleDateFormat dbFormat = new SimpleDateFormat(ZZPL0110Constant.SYSTEM_DATE_FORMAT);
        SimpleDateFormat displayFormat = new SimpleDateFormat(ZZPL0110Constant.DISPLAY_DATE_FORMAT);

        String displayStartTime = null;
        String displayEndTime = null;
        Date startTime = null;
        Date endTime = null;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (scrnMsg.A.no(i).xxDtTm_FR.getValue() != null && !scrnMsg.A.no(i).xxDtTm_FR.getValue().equals("")) {
                try {
                    startTime = dbFormat.parse(scrnMsg.A.no(i).xxDtTm_FR.getValue());
                    displayStartTime = displayFormat.format(startTime);
                    scrnMsg.A.no(i).xxDtTm_FR.setValue(displayStartTime);
                } catch (java.text.ParseException e) {
                    throw new S21AbendException("ZZXM0022E", new String[] {ZZPL0110Constant.SYSTEM_DATE_FORMAT, scrnMsg.A.no(i).xxDtTm_FR.getValue() });
                }
            }

            if (scrnMsg.A.no(i).xxDtTm_TO.getValue() != null && !scrnMsg.A.no(i).xxDtTm_TO.getValue().equals("")) {
                try {
                    endTime = dbFormat.parse(scrnMsg.A.no(i).xxDtTm_TO.getValue());
                    displayEndTime = displayFormat.format(endTime);
                    scrnMsg.A.no(i).xxDtTm_TO.setValue(displayEndTime);
                } catch (java.text.ParseException e) {
                    throw new S21AbendException("ZZXM0022E", new String[] {ZZPL0110Constant.SYSTEM_DATE_FORMAT, scrnMsg.A.no(i).xxDtTm_TO.getValue() });
                }
            }
        }
    }

    /**
     * Compare FromDate to EndDate
     * @param bMsg EZDBMsg
     */
    public static void compareFromToDate(EZDBMsg bMsg) {
        ZZPL0110BMsg scrnMsg = (ZZPL0110BMsg) bMsg;

        String fromDate = scrnMsg.xxFromDt.getValue();
        String toDate = scrnMsg.xxToDt.getValue();

        if (ZYPDateUtil.compare(toDate, fromDate) == -1) {
            scrnMsg.xxFromDt.setErrorInfo(1, "ZZPM0065E", new String[] {scrnMsg.xxFromDt.getValue(), scrnMsg.xxToDt.getValue() });
            scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        }
    }
}
