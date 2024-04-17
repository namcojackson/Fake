/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.ZZIL0040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.ZZIL0040.constant.ZZIL0040Constant;
import business.servlet.ZZIL0040.common.ZZIL0040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0040_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        this.checkBusinessAppGranted(getContextUserInfo().getUserId(), ZZIL0040Constant.BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) bMsg;

        ZZIL0040CommonLogic.setListBox(scrnMsg);
        ZZIL0040CommonLogic.dspScrn00(scrnMsg, this);
        scrnMsg.setFocusItem(scrnMsg.intfcId_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        ZZIL0040BMsg scrnMsg = (ZZIL0040BMsg) arg0;

        scrnMsg.intfcId_H.setNameForMessage("Interface ID");
    }
}
