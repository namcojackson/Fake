/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0100.common;

import java.math.BigDecimal;

import business.servlet.ZZOL0100.ZZOL0100BMsg;
import business.servlet.ZZOL0100.constant.ZZOL0100Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * Method name: Screen processing Common Logic
 */
public class ZZOL0100CommonLogic implements ZZOL0100Constant {

    /**
     * Method name: dspScrn
     * <dd>The method explanation: button Screen Control.
     * <dd>Remarks:
     * @param scrnMsg ZZOL0100BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn(ZZOL0100BMsg scrnMsg, S21CommonHandler handler) {

        String NowYmd = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
        scrnMsg.menuEffFromDt_P0.setValue(NowYmd);
        String NowHms = ZYPDateUtil.getCurrentSystemTime("kk") + "0000";
        scrnMsg.menuEffFromTm_P3.setValue(NowHms);
        
        // Common Button Control
        handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);
        handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);
        handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);
        handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);
        handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);
        handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null);
        handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);
        handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);
        handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);

        // Common Button Confirm Message Settings       
        handler.setButtonConfirmMsg(CMN_BTN7[1], "ZZM8102I" , new String[] {"Delete"}, 0);
        handler.setButtonConfirmMsg(CMN_BTN10[1], "ZZM8101I", new String[] {"Return"}, 0);

        // Color Settings
        S21TableColorController tblColor = new S21TableColorController(SCREEN_NAME, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        handler.setButtonEnabled("Upd", false);
        if ((scrnMsg.menuInfoPk.getValue() != null) && (scrnMsg.menuInfoPk.getValue() != new BigDecimal(0))) {
            handler.setButtonEnabled("Upd", true);
        }
        handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 1, null);
        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).menuInfoTxt_A1.setInputProtected(true);            
        }
    }

    /**
     * Method name: chkHead
     * <dd>The method explanation: button Screen Control.
     * <dd>Remarks:
     * @param scrnMsg ZZOL0100BMsg
     */
    public static boolean chkHead(ZZOL0100BMsg scrnMsg) {

        String menuEffFromDt = scrnMsg.menuEffFromDt.getValue();
        String menuEffThruDt = scrnMsg.menuEffThruDt.getValue();
        String menuEffFromTm = scrnMsg.menuEffFromTm_F3.getValue();
        String menuEffThruTm = scrnMsg.menuEffThruTm_T3.getValue();

        if ((!menuEffFromDt.equals("")) && (!menuEffThruDt.equals(""))) {
            if (menuEffFromDt.compareTo(menuEffThruDt) > 0) {
                scrnMsg.menuEffFromDt.setErrorInfo(1, "ZZZM9010E", new String[] {EFFECTIVE_DATE_FROM,EFFECTIVE_DATE_To});
                scrnMsg.menuEffThruDt.setErrorInfo(1, "ZZZM9010E", new String[] {EFFECTIVE_DATE_FROM,EFFECTIVE_DATE_To});
                return false;
            }
        }
        if ((menuEffFromDt.equals("")) && (!menuEffFromTm.equals(""))) {
            scrnMsg.menuEffFromDt.setErrorInfo(1, "ZZZM9007E", new String[] {EFFECTIVE_DATE_FROM});
            return false;
        }
        if ((menuEffThruDt.equals("")) && (!menuEffThruTm.equals(""))) {
            scrnMsg.menuEffThruDt.setErrorInfo(1, "ZZZM9007E", new String[] {EFFECTIVE_DATE_To});
            return false;
        }
        if ((menuEffFromDt.compareTo(menuEffThruDt) == 0) && (!menuEffFromTm.equals("")) && (!menuEffThruTm.equals(""))) {
            if (menuEffFromTm.compareTo(menuEffThruTm) > 0) {
                scrnMsg.menuEffFromTm_F3.setErrorInfo(1, "ZZZM9010E", new String[] {EFFECTIVE_DATE_FROM_TIME,EFFECTIVE_DATE_To_TIME});
                scrnMsg.menuEffThruTm_T3.setErrorInfo(1, "ZZZM9010E", new String[] {EFFECTIVE_DATE_FROM_TIME,EFFECTIVE_DATE_To_TIME});
                return false;
            }
        }
        return true;
    }

}
