/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0120.common;

import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.blap.ZZIL0120.ZZIL0120CMsg;
import business.servlet.ZZIL0120.ZZIL0120BMsg;
import business.servlet.ZZIL0120.ZZIL0120Bean;
import business.servlet.ZZIL0120.constant.ZZIL0120Constant;

public class ZZIL0120CommonLogic extends ZZIL0120Constant {

    /**
     * Set Table(A) color and exchange display value
     * @param scrnMsg ZZIL0120BMsg
     * @param bizMsg ZZIL0120CMsg
     */
    public static void setTableColor(ZZIL0120BMsg scrnMsg, ZZIL0120CMsg bizMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(ZZIL0120Bean.A, scrnMsg.A);

        if (scrnMsg.A.getValidCount() > 0) {

            String styleClass = "";

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // register date setting
                scrnMsg.A.no(i).xxDtTm_AC.setValue(convYmd(bizMsg.A.no(i).xxDtTm_AC.getValue()));
                // updated date setting
                scrnMsg.A.no(i).xxDtTm_AU.setValue(convYmd(bizMsg.A.no(i).xxDtTm_AU.getValue()));

                if ("pEvenNumberBGcolor".equals(styleClass)) {
                    styleClass = "";
                } else {
                    styleClass = "pEvenNumberBGcolor";
                }
                tblColor.setRowStyle(ZZIL0120Bean.A, i, styleClass);
            }

        }
    }

    /**
     * check common input.
     * @param scrnMsg
     */
    public static void checkCommonInput(ZZIL0120BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.itrlIntfcId);
        scrnMsg.putErrorScreen();
    }

    /**
     * initialize common button.
     * @param handler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * initialize message name
     * @param scrnMsg
     */
    public static void setNameForMessage(ZZIL0120BMsg scrnMsg) {
        scrnMsg.itrlIntfcId.setNameForMessage("Internal Interface ID");
    }

    /**
     * set selected low data to return object
     * @param scrnMsg
     * @param index
     * @param arg
     */
    public static void returnSelectedData(ZZIL0120BMsg scrnMsg, int index, Object[] arg) {

        String itrlIntfcId_A = "";
        String itrlIntfcTrxConfigId_A = "";
        if (index != NO_SELECT) {
            itrlIntfcId_A = scrnMsg.A.no(index).itrlIntfcId_A.getValue();
            itrlIntfcTrxConfigId_A = scrnMsg.A.no(index).itrlIntfcTrxConfigId_A.getValue();
        }

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param00 = (EZDBStringItem) params[0];
            param00.setValue(itrlIntfcId_A);
            EZDBStringItem param01 = (EZDBStringItem) params[1];
            param01.setValue(itrlIntfcTrxConfigId_A);
        }
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
