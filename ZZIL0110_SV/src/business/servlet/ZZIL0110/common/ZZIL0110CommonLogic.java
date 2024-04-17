/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0110.common;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.servletcommon.EZDCommonHandler;
import business.blap.ZZIL0110.ZZIL0110CMsg;
import business.servlet.ZZIL0110.ZZIL0110BMsg;
import business.servlet.ZZIL0110.ZZIL0110Bean;
import business.servlet.ZZIL0110.constant.ZZIL0110Constant;

public class ZZIL0110CommonLogic extends ZZIL0110Constant {

    /**
     * Set Table(A) color and exchange display value
     * @param scrnMsg ZZIL0110BMsg
     * @param bizMsg ZZIL0110CMsg
     */
    public static void setTableColorA(ZZIL0110BMsg scrnMsg, ZZIL0110CMsg bizMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID0, scrnMsg);
        tblColor.clearRowsBG(ZZIL0110Bean.A, scrnMsg.A);

        if (scrnMsg.A.getValidCount() > 0) {
            String styleClass = "";
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // register date setting
                scrnMsg.A.no(i).xxDtTm_AC.setValue(convYmd(bizMsg.A.no(i).xxDtTm_AC.getValue()));
                // updated date setting
                scrnMsg.A.no(i).xxDtTm_AU.setValue(convYmd(bizMsg.A.no(i).xxDtTm_AU.getValue()));

                if (TABLE_BG_COLOR_CLASS.equals(styleClass)) {
                    styleClass = "";
                } else {
                    styleClass = TABLE_BG_COLOR_CLASS;
                }
                tblColor.setRowStyle(ZZIL0110Bean.A, i, styleClass);
            }

        }
    }

    /**
     * Set Table(B) color and exchange display value
     * @param scrnMsg ZZIL0110BMsg
     * @param bizMsg ZZIL0110CMsg
     */
    public static void setTableColorB(ZZIL0110BMsg scrnMsg, ZZIL0110CMsg bizMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID1, scrnMsg);
        tblColor.clearRowsBG(ZZIL0110Bean.B, scrnMsg.B);

        if (scrnMsg.B.getValidCount() > 0) {
            String styleClass = "";

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                // clear checkBox
                scrnMsg.B.no(i).xxChkBox_B.clear();

                // register date setting
                scrnMsg.B.no(i).xxDtTm_BC.setValue(convYmd(bizMsg.B.no(i).xxDtTm_BC.getValue()));
                // updated date setting
                scrnMsg.B.no(i).xxDtTm_BU.setValue(convYmd(bizMsg.B.no(i).xxDtTm_BU.getValue()));

                if (TABLE_BG_COLOR_CLASS.equals(styleClass)) {
                    styleClass = "";
                } else {
                    styleClass = TABLE_BG_COLOR_CLASS;
                }
                tblColor.setRowStyle(ZZIL0110Bean.B, i, styleClass);
            }

        }
    }

    /**
     * set focus item for scrn01
     * @param scrnMsg
     */
    public static void setFocusItemScrn01(ZZIL0110BMsg scrnMsg) {
        if (scrnMsg.itrlIntfcId_B1.isInputProtected()) {
            scrnMsg.setFocusItem(scrnMsg.itrlIntfcTrxTblId_B1);
        } else {
            scrnMsg.setFocusItem(scrnMsg.itrlIntfcId_B1);
        }
    }

    /**
     * initialize button
     * @param handler
     */
    public static void setInitButton(EZDCommonHandler handler) {
        setCommonButton(handler);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
    }

    /**
     * set selected row data from scrn00 to scrn01 and set screen
     * attribute
     * @param scrnMsg
     * @param index
     */
    public static void setSelectedDataFromScrn00(ZZIL0110BMsg scrnMsg, int index) {
        scrnMsg.itrlIntfcTrxTblId_B1.clear();
        scrnMsg.itrlIntfcTrxConfigId_BS.setValue(scrnMsg.itrlIntfcTrxConfigId_PS.getValue());
        scrnMsg.itrlIntfcTrxConfigId_BS.setInputProtected(true);
        // Add interface
        if (index == -1) {
            scrnMsg.itrlIntfcId_B1.setInputProtected(false);
            scrnMsg.itrlIntfcId_B1.clear();
            return;
        }
        scrnMsg.itrlIntfcId_B1.setInputProtected(true);
        scrnMsg.itrlIntfcId_B1.setValue(scrnMsg.A.no(index).itrlIntfcId_A.getValue());
    }

    /**
     * set message name for scrn00
     * @param scrnMsg
     * @param screenId
     */
    public static void setNameForMessage_00(ZZIL0110BMsg scrnMsg, String screenId) {
        scrnMsg.itrlIntfcTrxConfigId_PS.setNameForMessage("Config Name");
        scrnMsg.itrlIntfcId.setNameForMessage("Internal Interface ID");
    }

    /**
     * set message name for scrn01
     * @param scrnMsg
     * @param screenId
     */
    public static void setNameForMessage_01(ZZIL0110BMsg scrnMsg, String screenId) {
        scrnMsg.itrlIntfcTrxConfigId_BS.setNameForMessage("Config Name");
        scrnMsg.itrlIntfcId_B1.setNameForMessage("Internal Interface ID");
        scrnMsg.itrlIntfcTrxTblId_B1.setNameForMessage("Internal Interface Table ID");
    }

    /**
     * set check item for scrn00
     * @param scrnMsg
     */
    public static void checkCommonInputScrn00(ZZIL0110BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.itrlIntfcTrxConfigId_PS);
        scrnMsg.addCheckItem(scrnMsg.itrlIntfcId);
        scrnMsg.putErrorScreen();
    }

    /**
     * set check item for scrn01
     * @param scrnMsg
     */
    public static void checkCommonInputScrn01(ZZIL0110BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.itrlIntfcTrxConfigId_BS);
        scrnMsg.addCheckItem(scrnMsg.itrlIntfcId_B1);
        scrnMsg.addCheckItem(scrnMsg.itrlIntfcTrxTblId_B1);
        scrnMsg.putErrorScreen();
    }

    /**
     * set common button attribute
     * @param handler
     */
    private static void setCommonButton(EZDCommonHandler handler) {
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
    }

    /**
     * set message name
     * @param scrnMsg
     */
    public static void setNameForMessage(ZZIL0110BMsg scrnMsg) {
        scrnMsg.itrlIntfcTrxConfigId_PS.setNameForMessage("Config Name");
        scrnMsg.itrlIntfcId.setNameForMessage("Internal Interface ID");
        scrnMsg.itrlIntfcId_B1.setNameForMessage("Internal Interface ID");
        scrnMsg.itrlIntfcTrxConfigId_BS.setNameForMessage("Config Name");
        scrnMsg.itrlIntfcTrxTblId_B1.setNameForMessage("Internal Interface Table");
    }

    /**
     * Exchange String to Date String
     * @param ymd String
     * @return Date String (MM/DD/YYYY hh:mm:ss.SSS)
     */
    private static String convYmd(String ymd) {
        if (ymd.length() > 0) {
            return ymd.substring(4, 6) + "/" + ymd.substring(6, 8) + "/" + ymd.substring(0, 4) + " " + ymd.substring(8, 10) + ":" + ymd.substring(10, 12) + ":" + ymd.substring(12, 14) + "." + ymd.substring(14, 17);
        }
        return ymd;
    }
}
