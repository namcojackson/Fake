/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZPL0030.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import parts.common.EZDBMsg;
import business.servlet.ZZPL0030.ZZPL0030BMsg;
import business.servlet.ZZPL0030.constant.ZZPL0030Constant;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/23   Fujitsu         T.Tsuji         Create          N/A
 *</pre>
 */
public class ZZPL0030CommonLogic {

    /**
     * @param handler S21 Common Handler
     */
    public static void setButtonProperties_INIT(S21CommonHandler handler) {
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_BTN1[0], ZZPL0030Constant.BTN_CMN_BTN1[1], ZZPL0030Constant.BTN_CMN_BTN1[2], 0, null);
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_BTN2[0], ZZPL0030Constant.BTN_CMN_BTN2[1], ZZPL0030Constant.BTN_CMN_BTN2[2], 0, null);
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_BTN3[0], ZZPL0030Constant.BTN_CMN_BTN3[1], ZZPL0030Constant.BTN_CMN_BTN3[2], 0, null);
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_BTN4[0], ZZPL0030Constant.BTN_CMN_BTN4[1], ZZPL0030Constant.BTN_CMN_BTN4[2], 0, null);
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_BTN5[0], ZZPL0030Constant.BTN_CMN_BTN5[1], ZZPL0030Constant.BTN_CMN_BTN5[2], 0, null);
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_BTN6[0], ZZPL0030Constant.BTN_CMN_BTN6[1], ZZPL0030Constant.BTN_CMN_BTN6[2], 0, null);
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_BTN7[0], ZZPL0030Constant.BTN_CMN_BTN7[1], ZZPL0030Constant.BTN_CMN_BTN7[2], 0, null);
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_BTN8[0], ZZPL0030Constant.BTN_CMN_BTN8[1], ZZPL0030Constant.BTN_CMN_BTN8[2], 1, null);
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_BTN9[0], ZZPL0030Constant.BTN_CMN_BTN9[1], ZZPL0030Constant.BTN_CMN_BTN9[2], 0, null);
        handler.setButtonProperties(ZZPL0030Constant.BTN_CMN_RETURN[0], ZZPL0030Constant.BTN_CMN_RETURN[1], ZZPL0030Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * Change the row color on table
     * @param bMsg EZDBMsg
     * @param scrnId ScreenID
     */
    public static void setTableRowColor(EZDBMsg bMsg, String scrnId) {
        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        // set alternate rows background color
        if (ZZPL0030Constant.SCREENID_SCRN00.equals(scrnId)) {
            S21TableColorController tblController = new S21TableColorController(ZZPL0030Constant.SCREENID_SCRN00, scrnMsg);
            tblController.setAlternateRowsBG("A", scrnMsg.A);
        } else {
            S21TableColorController tblController = new S21TableColorController(ZZPL0030Constant.SCREENID_SCRN01, scrnMsg);
            tblController.setAlternateRowsBG("B", scrnMsg.B);
        }
    }

    /**
     * change "Creation Time" format
     * @param bMsg EZDBMsg
     */
    public static void setCreationTimeFormat(EZDBMsg bMsg) {
        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        SimpleDateFormat displayFormat = new SimpleDateFormat(ZZPL0030Constant.DATE_TIME_FORMAT);
        SimpleDateFormat dbFormat = new SimpleDateFormat(ZZPL0030Constant.SYSTEM_DATE_FORMAT);
        String displayDate = null;
        Date date = null;

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            try {
                date = dbFormat.parse(scrnMsg.B.no(i).xxRptCratTm.getValue());
                displayDate = displayFormat.format(date);
                scrnMsg.B.no(i).xxRptCratTm.setValue(displayDate);
            } catch (java.text.ParseException e) {
                throw new S21AbendException("ZZPM0045E", new String[] {e.getMessage() });
            }
        }
    }

    /**
     * set pulldown activation
     * @param bMsg EZDBMsg
     */
    public static void setPulldownActivation(EZDBMsg bMsg) {
        ZZPL0030BMsg scrnMsg = (ZZPL0030BMsg) bMsg;

        scrnMsg.xxRptSrchCd_I1.setInputProtected(true);
        scrnMsg.xxRptSrchTxt_1.setInputProtected(true);
        scrnMsg.xxRptSrchCd_I2.setInputProtected(true);
        scrnMsg.xxRptSrchTxt_2.setInputProtected(true);
        scrnMsg.xxRptSrchCd_I3.setInputProtected(true);
        scrnMsg.xxRptSrchTxt_3.setInputProtected(true);
        scrnMsg.xxRptSrchCd_I4.setInputProtected(true);
        scrnMsg.xxRptSrchTxt_4.setInputProtected(true);

        for (int i = 0; i < 4; i++) {
            if (scrnMsg.xxRptSrchCd_O1.no(i).getValue() != null && !scrnMsg.xxRptSrchCd_O1.no(i).getValue().equals("")) {
                if (i == 0) {
                    scrnMsg.xxRptSrchCd_I1.setInputProtected(false);
                    scrnMsg.xxRptSrchTxt_1.setInputProtected(false);
                    scrnMsg.xxRptSrchCd_I1.setValue(scrnMsg.xxRptSrchCd_O1.no(i).getValue());
                } else if (i == 1) {
                    scrnMsg.xxRptSrchCd_I2.setInputProtected(false);
                    scrnMsg.xxRptSrchTxt_2.setInputProtected(false);
                    scrnMsg.xxRptSrchCd_I2.setValue(scrnMsg.xxRptSrchCd_O1.no(i).getValue());
                } else if (i == 2) {
                    scrnMsg.xxRptSrchCd_I3.setInputProtected(false);
                    scrnMsg.xxRptSrchTxt_3.setInputProtected(false);
                    scrnMsg.xxRptSrchCd_I3.setValue(scrnMsg.xxRptSrchCd_O1.no(i).getValue());
                } else if (i == 3) {
                    scrnMsg.xxRptSrchCd_I4.setInputProtected(false);
                    scrnMsg.xxRptSrchTxt_4.setInputProtected(false);
                    scrnMsg.xxRptSrchCd_I4.setValue(scrnMsg.xxRptSrchCd_O1.no(i).getValue());
                }
            }
        }
    }

}
