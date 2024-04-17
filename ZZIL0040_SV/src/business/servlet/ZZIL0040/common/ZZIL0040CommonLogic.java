package business.servlet.ZZIL0040.common;

import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_APL;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_APR;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_CLR;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_DEL;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_DWL;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_RJT;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_RST;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_RTN;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_SAV;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.BTN_CMN_SUB;
import static business.servlet.ZZIL0040.constant.ZZIL0040Constant.SCRN_ID_00;

import java.util.Arrays;

import business.blap.ZZIL0040.ZZIL0040CMsg;
import business.servlet.ZZIL0040.ZZIL0040BMsg;
import business.servlet.ZZIL0040.constant.ZZIL0040Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/15   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0040CommonLogic {

    /**
     * Method name: dspScrn00 <dd>The method explanation: init
     * Screen00 Control. <dd>Remarks:
     * @param scrnMsg ZZIL0040BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn00(ZZIL0040BMsg scrnMsg, S21CommonHandler handler) {

        // Common Button Control
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A_LeftTBL", scrnMsg.A);
        tblColor.setAlternateRowsBG("A_RightTBL", scrnMsg.A);

        // if "Status" is "COMPLETED", "FAILED1", or
        // "FAILED2", activate pulldown list
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String rptSts = scrnMsg.A.no(i).intfcRqstStsTxt_A.getValue();
            if (Arrays.asList(ZZIL0040Constant.RQST_STS_CHANGEABLE).contains(rptSts)) {
                scrnMsg.A.no(i).intfcRqstStsTxt_AS.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).intfcRqstStsTxt_AS.setInputProtected(true);
            }
        }
    }

    /**
     * Method name: dspScrn01 <dd>The method explanation: init
     * Screen01 Control. <dd>Remarks:
     * @param scrnMsg ZZIL0040BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn01(ZZIL0040BMsg scrnMsg, S21CommonHandler handler) {

        // Common Button Control
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * set ListBox
     * @param scrnMsg ZZIL0040BMsg
     */
    public static void setListBox(ZZIL0040BMsg scrnMsg) {

        String hmCd = null;
        String hmDisp = null;

        // time listbox setting
        for (int i = 0; i < ZZIL0040Constant.HH24; i++) {
            hmCd = String.format("%02d", i);
            hmDisp = String.format("%02d:00", i);

            scrnMsg.xxHrs_SF.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_SF.no(i).setValue(hmDisp);

            scrnMsg.xxHrs_ST.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_ST.no(i).setValue(hmDisp);

            scrnMsg.xxHrs_EF.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_EF.no(i).setValue(hmDisp);

            scrnMsg.xxHrs_ET.no(i).setValue(hmCd);
            scrnMsg.xxHrsMn_ET.no(i).setValue(hmDisp);
        }

        // processed_flag listbox setting
        for (int i = 0; i < ZZIL0040Constant.PROC_LIST.length; i++) {
            scrnMsg.intfcRqstStsTxt_HC.no(i).setValue(ZZIL0040Constant.PROC_LIST[i]);
            scrnMsg.intfcRqstStsTxt_HD.no(i).setValue(ZZIL0040Constant.PROC_LIST[i]);
        }
    }

    /**
     * Convert Date String
     * @param ymd String
     * @return Date String
     */
    private static String convYmd(String ymd) {
        if (ymd.length() == 17) {
            return ymd.substring(4, 6) + "/" + ymd.substring(6, 8) + "/" + ymd.substring(0, 4) + " " + ymd.substring(8, 10) + ":" + ymd.substring(10, 12) + ":" + ymd.substring(12, 14) + "." + ymd.substring(14, 17);
        }
        return ymd;
    }

    /**
     * Convert Display Date for Scrn00
     * @param scrnMsg ZZIL0040BMsg
     * @param bizMsg ZZIL0040CMsg
     */
    public static void setDispDate00(ZZIL0040BMsg scrnMsg, ZZIL0040CMsg bizMsg) {

        if (scrnMsg.A.getValidCount() > 0) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                // start timestamp date setting
                scrnMsg.A.no(i).xxDtTm_AS.setValue(convYmd(bizMsg.A.no(i).xxDtTm_AS.getValue()));
                // end timestamp date setting
                scrnMsg.A.no(i).xxDtTm_AE.setValue(convYmd(bizMsg.A.no(i).xxDtTm_AE.getValue()));

            }
        }
    }

    /**
     * Convert Display Date for Scrn01
     * @param scrnMsg ZZIL0040BMsg
     * @param bizMsg ZZIL0040CMsg
     */
    public static void setDispDate01(ZZIL0040BMsg scrnMsg, ZZIL0040CMsg bizMsg) {

        scrnMsg.xxDtTm_DS.setValue(convYmd(bizMsg.xxDtTm_DS.getValue()));
        scrnMsg.xxDtTm_DE.setValue(convYmd(bizMsg.xxDtTm_DE.getValue()));
    }

}
