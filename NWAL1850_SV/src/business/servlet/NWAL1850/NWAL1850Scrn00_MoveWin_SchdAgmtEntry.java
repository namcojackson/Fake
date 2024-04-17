/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1850;

//import static business.servlet.NWAL1850.constant.NWAL1850Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1850.NWAL1850CMsg;
//import business.servlet.NWAL1850.constant.NWAL1850Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1850Scrn00_MoveWin_SchdAgmtEntry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NWAL1850Scrn00_MoveWin_SchdAgmtEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;
        int index = getButtonSelectNumber();

        // set param
        Object[] params = new Object[1];

        params[0] = scrnMsg.A.no(index).schdAgmtNum_A;
        setArgForSubScreen(params);

    }
}
