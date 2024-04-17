/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0330.common;

import static business.servlet.NSBL0330.constant.NSBL0330Constant.*;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import business.servlet.NSBL0330.NSBL0330BMsg;
import parts.servletcommon.EZDCommonHandler;

/**
 *<pre>
 * Service Request By Summary Criteria
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0330CommonLogic {

    /**
     * initialize
     * @param handler EZDCommonHandler
     */
    public static void initialize(EZDCommonHandler handler) {

        // set button property
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * screenItemControl
     * @param handler EZDCommonHandler
     * @param scrnMsg NSBL0330BMsg
     */
    public static void screenItemControl(EZDCommonHandler handler, NSBL0330BMsg scrnMsg) {

        controlProcess(handler, scrnMsg);
        setTableBGColor(scrnMsg);
    }

    private static void controlProcess(EZDCommonHandler handler, NSBL0330BMsg scrnMsg) {

        if (scrnMsg.xxPageShowFromNum_W.getValue().compareTo(BigDecimal.ONE) > 0) {
            handler.setButtonEnabled(BTN_PREV_MGR[0], true);
        } else {
            handler.setButtonEnabled(BTN_PREV_MGR[0], false);
        }
        if (scrnMsg.xxPageShowToNum_W.getValue().compareTo(scrnMsg.xxPageShowOfNum_W.getValue()) < 0) {
            handler.setButtonEnabled(BTN_NEXT_MGR[0], true);
        } else {
            handler.setButtonEnabled(BTN_NEXT_MGR[0], false);
        }
        if (scrnMsg.xxPageShowFromNum_H.getValue().compareTo(BigDecimal.ONE) > 0) {
            handler.setButtonEnabled(BTN_PAGE_PREV[0], true);
        } else {
            handler.setButtonEnabled(BTN_PAGE_PREV[0], false);
        }
        if (scrnMsg.xxPageShowToNum_H.getValue().compareTo(scrnMsg.xxPageShowOfNum_H.getValue()) < 0) {
            handler.setButtonEnabled(BTN_PAGE_NEXT[0], true);
        } else {
            handler.setButtonEnabled(BTN_PAGE_NEXT[0], false);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxScrItem81Txt.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_00.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_01.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_02.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_03.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_04.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_05.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_06.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_07.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_08.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_09.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_10.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_11.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_12.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_13.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_14.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_15.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_16.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_17.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_18.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_19.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_20.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_21.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_22.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_23.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_24.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_25.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_26.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_27.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_28.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_29.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_30.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_31.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_32.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_33.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_34.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_35.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_36.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_37.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_38.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_39.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_40.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_41.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_42.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_43.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_44.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_45.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_46.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_47.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_48.setInputProtected(true);
            scrnMsg.A.no(i).xxScrItem130Txt_49.setInputProtected(true);
        }
    }

    private static void setTableBGColor(NSBL0330BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A1", scrnMsg.A);
        tblColor.clearRowsBG("A3", scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("A1", scrnMsg.A);
            tblColor.setAlternateRowsBG("A3", scrnMsg.A);
        }
    }
}
