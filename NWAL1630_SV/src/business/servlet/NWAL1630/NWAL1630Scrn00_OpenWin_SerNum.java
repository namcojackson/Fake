/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1630;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1630.common.NWAL1630CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/22   Fujitsu         T.Ogura         Create          S21_NA#18859(Sol#154)
 *</pre>
 */
public class NWAL1630Scrn00_OpenWin_SerNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.serNum);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;

        setArgForSubScreen(NWAL1630CommonLogic.setParamNSAL1240(scrnMsg));
    }
}
