/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/03/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.ZZOL0110.common;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZOL0110.ZZOL0110BMsg;
import business.servlet.ZZOL0110.constant.ZZOL0110Constant;

import com.canon.cusa.s21.common.ZZX.ZZXC001001.ZZXC001002;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;
import com.canon.cusa.s21.framework.security.context.S21SecurityContextHolder;
import com.fujitsu.uji.http.HttpDispatchContext;

/**
 * Method name: Screen processing Common Logic
 */
public class ZZOL0110CommonLogic implements ZZOL0110Constant {
    
    /**
     * Method name: dspScrn00
     * <dd>The method explanation: init Screen00 Control.
     * <dd>Remarks:
     * @param scrnMsg ZZOL0110BMsg
     * @param handler S21CommonHandler
     */
    public static void dspScrn00(ZZOL0110BMsg scrnMsg, S21CommonHandlerEx handler) {

        String strSysFlg = ZZXC001002.SYS_FLG;
        // PARTS
        if (ZZXC001002.SYS_FLG_PARTS.contains(strSysFlg)) {
            EZDGUIAttribute sysTable = new EZDGUIAttribute("ZZOL0110Scrn00", "sysTable");
            sysTable.setVisibility(false);
            scrnMsg.addGUIAttribute(sysTable);

            scrnMsg.glblCmpyCd_X1.setValue(ZZXC001002.PARTS_GLBL_CMPY_CD);

        } else {
            // S21
            EZDGUIAttribute sysTable = new EZDGUIAttribute("ZZOL0110Scrn00", "sysTable");
            sysTable.setVisibility(true);
            scrnMsg.addGUIAttribute(sysTable);
        }

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

    }

    
    /**
     * <dd>The method explanation: Set information before Transition.
     * <dd>Remarks:
     * @param EZDApplicationContext ctx
     * @param String jumpBusinessID
     */
    public static void setTransition(EZDApplicationContext ctx, ZZOL0110BMsg scrnMsg, String jumpBusinessID) {

        String processNm = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters().getSingleValue("SelectedS21BusinessProcessName");
        ctx.setAttribute("SelectedS21BusinessProcessName", processNm);
        ctx.setAttribute("SelectedS21BusinessAplID", jumpBusinessID);

        String globalCompanyCode = scrnMsg.glblCmpyCd_X1.getValue();
        if (globalCompanyCode != null && globalCompanyCode.length() > 0) {
            S21SecurityContextHolder.getContext().getSystemAttributes().setGlobalCompanyCode(globalCompanyCode);
        }
    }

}
