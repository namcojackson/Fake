/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2290;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL2290.NWAL2290CMsg;
//import business.servlet.NWAL2290.constant.NWAL2290Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/01/05   Fujitsu         A.Kosai         Create          N/A
 *</pre>
 */
public class NWAL2290Scrn00_MoveWin_OrderEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2290BMsg scrnMsg = (NWAL2290BMsg) bMsg;
        int idx = this.getButtonSelectNumber();

        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(idx).cpoOrdNum_A;

        setArgForSubScreen(params);

        openMultiModeScreen();
    }
}
