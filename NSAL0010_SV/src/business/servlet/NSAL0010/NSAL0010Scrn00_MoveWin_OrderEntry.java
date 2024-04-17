/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2016/01/12   Hitachi         T.Tomita        Update          QC#2865
 *</pre>
 */
public class NSAL0010Scrn00_MoveWin_OrderEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2015/01/12 T.Tomita [QC#2865, ADD]
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        Object[] params = new Object[1];
        params[0] = scrnMsg.G.no(rowNum).cpoOrdNum_G;
        setArgForSubScreen(params);
        // END 2015/01/12 T.Tomita [QC#2865, ADD]
    }
}
