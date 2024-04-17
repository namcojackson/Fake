/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7110;

import static business.servlet.NMAL7110.constant.NMAL7110Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7110.NMAL7110CMsg;
import business.servlet.NMAL7110.common.NMAL7110CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7110Scrn00_SaveSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/23   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7110Scrn00_SaveSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;
        NMAL7110CommonLogic.commonAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;

        NMAL7110CMsg bizMsg = new NMAL7110CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;
        NMAL7110CMsg bizMsg = (NMAL7110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //TODO [Template]
//        scrnMsg.addCheckItem(scrnMsg.xxx_H);
//        scrnMsg.putErrorScreen();
    }
}
