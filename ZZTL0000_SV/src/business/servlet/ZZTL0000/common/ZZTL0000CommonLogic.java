/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/03/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZTL0000.common;

import business.servlet.ZZTL0000.ZZTL0000BMsg;
import business.servlet.ZZTL0000.constant.ZZTL0000Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;


/**
 * Method name: Screen processing Common Logic
 */
public class ZZTL0000CommonLogic implements ZZTL0000Constant {
    
    /**
     * Method name: dspScrn00
     * <dd>The method explanation: init Screen00 Control.
     * <dd>Remarks:
     * @param scrnMsg ZZOL0110BMsg
     * @param zztl0000_init S21CommonHandler
     */
    public static void dspScrn00(ZZTL0000BMsg scrnMsg, S21CommonHandlerEx handler) {

        // Common Button Control
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

        // disabled buttons
        handler.setButtonEnabled("BO_Validation", false);
        handler.setButtonEnabled("Kewill_Validation", false);
    }

    
    /**
     * <dd>The method explanation: Set information before Transition.
     * <dd>Remarks:
     * @param EZDApplicationContext ctx
     * @param String jumpBusinessID
     */
//    public static void setTransition(EZDApplicationContext ctx, ZZOL0110BMsg scrnMsg, String jumpBusinessID) {
//
//        String processNm = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters().getSingleValue("SelectedS21BusinessProcessName");
//        ctx.setAttribute("SelectedS21BusinessProcessName", processNm);
//        ctx.setAttribute("SelectedS21BusinessAplID", jumpBusinessID);
//
//        String globalCompanyCode = scrnMsg.glblCmpyCd_X1.getValue();
//        if (globalCompanyCode != null && globalCompanyCode.length() > 0) {
//            S21SecurityContextHolder.getContext().getSystemAttributes().setGlobalCompanyCode(globalCompanyCode);
//        }
//    }

}
