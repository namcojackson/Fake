/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/31   Hitachi         T.Tsuchida      Create          QC#19574
 *</pre>
 */
public class NFDL0010Scrn00_OpenWin_SearchAccountByTrxPopup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        scrnMsg.M.clear();
        scrnMsg.M.no(0).xxPopPrm.clear();

        Object[] param = new Object[1];
        param[0] = scrnMsg.M.no(0).xxPopPrm;

        setArgForSubScreen(param);
    }
}
