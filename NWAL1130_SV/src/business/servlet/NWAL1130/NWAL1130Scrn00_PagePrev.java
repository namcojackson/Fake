/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NWAL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1130.NWAL1130CMsg;
import business.servlet.NWAL1130.common.NWAL1130CommonLogic;
import business.servlet.NWAL1130.constant.NWAL1130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Common PopUp NWAL1130Scrn00_PagePrev
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/22/2012   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1130Scrn00_PagePrev extends S21CommonHandler implements NWAL1130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;

        // prepare business logic call
        NWAL1130CMsg bizMsg = NWAL1130CommonLogic.setRequestData_NWAL1130Scrn00_Search();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1130BMsg scrnMsg = (NWAL1130BMsg) bMsg;
        NWAL1130CMsg bizMsg = (NWAL1130CMsg) cMsg;

        // copy data from CMsg onto BMsg
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1130CommonLogic.setTableBGColor(scrnMsg);

        NWAL1130CommonLogic.initDisplayInfo(this, scrnMsg);
    }
}
