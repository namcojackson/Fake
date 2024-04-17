/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1510;

import static business.servlet.NPAL1510.constant.NPAL1510Constant.EVENT_OPEN_WIN_ITEM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1510.common.NPAL1510CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Hisashi         Create          n/a
 *</pre>
 */
public class NPAL1510Scrn00_OpenWin_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1510BMsg scrnMsg = (NPAL1510BMsg) bMsg;
        Object[] params = NPAL1510CommonLogic.getParamNMAL6800ForItem(scrnMsg, EVENT_OPEN_WIN_ITEM);
        setArgForSubScreen(params);
    }
}
