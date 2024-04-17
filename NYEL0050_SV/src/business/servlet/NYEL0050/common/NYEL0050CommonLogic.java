package business.servlet.NYEL0050.common;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NYEL0050.NYEL0050BMsg;
import business.servlet.NYEL0050.NYEL0050Bean;
import business.servlet.NYEL0050.constant.NYEL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

public class NYEL0050CommonLogic implements NYEL0050Constant {

    static public void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);

        // Common Button Confirm Message Settings
        handler.setButtonConfirmMsg(CMN_BTN10[1], "ZZZM9009W", null, 0);
    }

    /**
     * @param scrnMsg
     */
    public static void setTableColor(NYEL0050BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController("NYEL0050Scrn00", scrnMsg);
        tblColor.clearRowsBG(NYEL0050Bean.A, scrnMsg.A);

        if (scrnMsg.A.getValidCount() > 0) {

            String styleClass = "";

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                styleClass = i % 2 == 0 ? "pEvenNumberBGcolor" : "";
                tblColor.setRowStyle(NYEL0050Bean.A, i, styleClass);
            }
        }
    }

}
