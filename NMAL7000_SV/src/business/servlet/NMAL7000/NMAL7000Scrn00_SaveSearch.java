/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7000;

import static business.servlet.NMAL7000.constant.NMAL7000Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7000.NMAL7000CMsg;
import business.servlet.NMAL7000.common.NMAL7000CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7000Scrn00_SaveSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7000Scrn00_SaveSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;
        NMAL7000CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;

        NMAL7000CMsg bizMsg = new NMAL7000CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;
        NMAL7000CMsg bizMsg = (NMAL7000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL7000CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }
}
