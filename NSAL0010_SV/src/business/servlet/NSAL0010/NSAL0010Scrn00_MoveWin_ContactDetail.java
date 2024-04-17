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
 * 2019/02/08   Hitachi         K.Kitachi       Update          QC#30265
 *</pre>
 */
public class NSAL0010Scrn00_MoveWin_ContactDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        int rowNum = getButtonSelectNumber();
        Object[] params = new Object[2];
        params[0] = scrnMsg.C.no(rowNum).ctacPsnNum_C;
        // START 2019/02/08 K.Kitachi [QC#30265, MOD]
//        params[1] = scrnMsg.ownrAcctNum_M1;
        params[1] = scrnMsg.curLocAcctNum_M3;
        // END 2019/02/08 K.Kitachi [QC#30265, MOD]
        setArgForSubScreen(params);
    }
}
