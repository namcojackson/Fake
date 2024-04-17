/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8830;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NYEL8830.NYEL8830CMsg;
//import business.servlet.NYEL8830.constant.NYEL8830Constant;

import business.servlet.NYEL8830.common.NYEL8830CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8830Scrn00_MoveWin_NFCL2610 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8830BMsg scrnMsg = (NYEL8830BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NYEL8830BMsg scrnMsg = (NYEL8830BMsg) bMsg;

        //NYEL8830CMsg bizMsg = new NYEL8830CMsg();
        //bizMsg.setBusinessID("NYEL8830");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NYEL8830BMsg scrnMsg = (NYEL8830BMsg) bMsg;

        Object[] params = NYEL8830CommonLogic.getMoveWinParam(this, scrnMsg);
        setArgForSubScreen(params);

        openMultiModeScreen();
    }
}
