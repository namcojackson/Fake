/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/05/2016   CITS            K.Ogino         Update          N/A
 *</pre>
 */
public class NPBL0010Scrn00_MoveTo_POReqEntryUpd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPBL0010BMsg scrnMsg = (NPBL0010BMsg) bMsg;
        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(getButtonSelectNumber()).prchReqNum_A1;

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
