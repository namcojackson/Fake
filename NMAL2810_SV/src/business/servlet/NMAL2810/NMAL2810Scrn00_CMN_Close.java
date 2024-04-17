/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2810;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2810.common.NMAL2810CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2810Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2810Scrn00_CMN_Close extends S21CommonHandler {

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

        NMAL2810BMsg scrnMsg = (NMAL2810BMsg) bMsg;

        // OUT Parameter Set
        Object[] params = (Object[]) getArgForSubScreen();
        NMAL2810CommonLogic.setOutParams(scrnMsg, params);
    }
}
