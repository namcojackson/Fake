/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NPAL1500Scrn00_Get_SupplierName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        // Add check Item
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.prntVndCd.isError()) {
            scrnMsg.addCheckItem(scrnMsg.prntVndCd);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.setFocusItem(scrnMsg.prntVndCd);
        }
    }
}
