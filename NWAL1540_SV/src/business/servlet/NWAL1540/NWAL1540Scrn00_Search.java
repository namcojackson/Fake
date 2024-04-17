/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1540;

import static business.servlet.NWAL1540.constant.NWAL1540Constant.BIZ_ID;
import static business.servlet.NWAL1540.constant.NWAL1540Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1540.NWAL1540CMsg;
import business.servlet.NWAL1540.common.NWAL1540CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1540Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL1540Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1540BMsg scrnMsg = (NWAL1540BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.trxHdrNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1540BMsg scrnMsg = (NWAL1540BMsg) bMsg;

        NWAL1540CMsg bizMsg = new NWAL1540CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1540BMsg scrnMsg = (NWAL1540BMsg) bMsg;
        NWAL1540CMsg bizMsg = (NWAL1540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.trxHdrNum);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NWAL1540CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWAL1540CommonLogic.setSearchControl(this, scrnMsg);
    }
}
