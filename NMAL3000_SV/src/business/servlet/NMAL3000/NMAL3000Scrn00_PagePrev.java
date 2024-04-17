/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000;

import static business.servlet.NMAL3000.constant.NMAL3000Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL3000.NMAL3000CMsg;
import business.servlet.NMAL3000.common.NMAL3000CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;


/**
 *<pre>
 * NMAL3000Scrn00_PagePrev
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL3000Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CommonLogic.checkInputValue(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg = new NMAL3000CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        NMAL3000CMsg bizMsg = (NMAL3000CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL3000CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
    }
}
