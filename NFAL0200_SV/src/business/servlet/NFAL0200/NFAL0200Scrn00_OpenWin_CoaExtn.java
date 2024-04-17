/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFAL0200.common.NFAL0200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/30   Hitachi         Y.Takeno        Create          QC#12525
 *</pre>
 */
public class NFAL0200Scrn00_OpenWin_CoaExtn extends S21CommonHandler {

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

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

        Object[] params = NFAL0200CommonLogic.setParamForCoaExtnPopup(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
