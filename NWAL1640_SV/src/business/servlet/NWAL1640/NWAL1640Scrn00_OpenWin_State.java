/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1640.common.NWAL1640CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1640Scrn00_OpenWin_State
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public class NWAL1640Scrn00_OpenWin_State extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1640BMsg scrnMsg = (NWAL1640BMsg) bMsg;
        Object[] params = NWAL1640CommonLogic.prepareAddressLookupPopupParameter(scrnMsg, this.getGlobalCompanyCode(), ctx.getEventName());
        setArgForSubScreen(params);
    }
}
