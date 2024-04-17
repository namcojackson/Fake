/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
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
 * 2016/11/02   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NFAL0200Scrn00_OpenWin_Branch extends S21CommonHandler {

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

        int idx = getButtonSelectNumber();
        Object[] params = NFAL0200CommonLogic.setParamForBrPopup(scrnMsg, idx);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
