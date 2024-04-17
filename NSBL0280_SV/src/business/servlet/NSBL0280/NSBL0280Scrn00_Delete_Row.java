/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0280;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;

import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0280.NSBL0280CMsg;
import business.servlet.NSBL0280.common.NSBL0280CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NSBL0280Scrn00_Delete_Row extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;
        NSBL0280CommonLogic.addCheckItemRadioBtn(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;

        NSBL0280CMsg bizMsg = NSBL0280CommonLogic.setRequestData_SearchCommon();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0280BMsg scrnMsg = (NSBL0280BMsg) bMsg;
        NSBL0280CMsg bizMsg = (NSBL0280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0280CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        NSBL0280CommonLogic.screenControlDelete(this, scrnMsg);
    }
}