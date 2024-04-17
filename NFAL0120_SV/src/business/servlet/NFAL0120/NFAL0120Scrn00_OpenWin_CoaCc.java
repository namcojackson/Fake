/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0120.common.NFAL0120CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         Y.Takeno        Create          QC#12525
 *</pre>
 */
public class NFAL0120Scrn00_OpenWin_CoaCc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0120BMsg scrnMsg = (NFAL0120BMsg) bMsg;

        Object[] params = NFAL0120CommonLogic.setParamForCoaCcPopup(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
