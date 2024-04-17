/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1340;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1340.common.NSAL1340CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1340Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/09   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public class NSAL1340Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1340BMsg scrnMsg = (NSAL1340BMsg) bMsg;

        //TODO [Template] if javascript error happen, uncomment below
        //scrnMsg.clearAllGUIAttribute(SCREEN_ID_00);
        //TODO [Template] if there are pre set data (for example, pulldown), please clear the items one by one.
        scrnMsg.clear();

        NSAL1340CommonLogic.initCmnBtnProp(this);
        //TODO [Template]
        //scrnMsg.setFocusItem(scrnMsg.xxxx);
    }
}
