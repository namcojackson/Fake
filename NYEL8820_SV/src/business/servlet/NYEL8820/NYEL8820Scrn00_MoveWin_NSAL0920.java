/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8820;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NYEL8820.NYEL8820CMsg;
//import business.servlet.NYEL8820.constant.NYEL8820Constant;

import business.servlet.NYEL8820.common.NYEL8820CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8820Scrn00_MoveWin_NSAL0920 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;

        //NYEL8820CMsg bizMsg = new NYEL8820CMsg();
        //bizMsg.setBusinessID("NYEL8820");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NYEL8820BMsg scrnMsg = (NYEL8820BMsg) bMsg;

        Object[] params = NYEL8820CommonLogic.getMoveWinParam(scrnMsg);
        setArgForSubScreen(params);

        openMultiModeScreen();
    }
}
