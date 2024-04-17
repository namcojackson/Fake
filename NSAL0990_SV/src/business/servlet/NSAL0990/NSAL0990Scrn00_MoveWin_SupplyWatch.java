/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/02/24   Hitachi         K.Kasai         Update          QC#4122
 *</pre>
 */
public class NSAL0990Scrn00_MoveWin_SupplyWatch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // add start 2016/02/24 CSA Defect#4122
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        Object[] prm = new Object[1];
        prm[0] = scrnMsg.dsContrNum;

        setArgForSubScreen(prm);
        // add end 2016/02/24 CSA Defect#4122
    }
}
