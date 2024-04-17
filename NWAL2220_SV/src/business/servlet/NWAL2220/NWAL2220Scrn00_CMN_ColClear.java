/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220;

import static business.servlet.NWAL2220.constant.NWAL2220Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2220.NWAL2220CMsg;
import business.servlet.NWAL2220.common.NWAL2220CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NWAL2220Scrn00_CMN_ColClear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        NWAL2220CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        NWAL2220CMsg bizMsg = new NWAL2220CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.UPDATE.getFunc());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        NWAL2220CMsg bizMsg = (NWAL2220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL2220CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }
}
