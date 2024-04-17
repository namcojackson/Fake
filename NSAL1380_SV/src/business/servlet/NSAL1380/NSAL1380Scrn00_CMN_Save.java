/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1380;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1380.NSAL1380CMsg;
import business.servlet.NSAL1380.common.NSAL1380CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Hitachi         N.Arai          Create          N/A
 *</pre>
 */
public class NSAL1380Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1380BMsg scrnMsg = (NSAL1380BMsg) bMsg;
        NSAL1380CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1380BMsg scrnMsg = (NSAL1380BMsg) bMsg;

        NSAL1380CMsg bizMsg = new NSAL1380CMsg();
        bizMsg.setBusinessID("NSAL1380");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1380BMsg scrnMsg = (NSAL1380BMsg) bMsg;
        NSAL1380CMsg bizMsg  = (NSAL1380CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1380CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }
}
