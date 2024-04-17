/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL0140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL0140.common.NWAL0140CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   SRAA            Y.Chen          Create          QC#4505
 *</pre>
 */
public class NWAL0140Scrn00_OpenWin_Cnty extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;
        Object[] params = NWAL0140CommonLogic.prepareAddressLookupPopupParameter(scrnMsg, this.getGlobalCompanyCode(), ctx.getEventName());
        setArgForSubScreen(params);
    }
}
