/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.BIZ_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7060.NMAL7060CMsg;
import business.servlet.NMAL7060.common.NMAL7060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7060Scrn00_OnChange_RadioBtn_Bllg
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7060Scrn00_OnChange_RadioBtn_Bllg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        NMAL7060CMsg bizMsg = new NMAL7060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg  = (NMAL7060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            NMAL7060CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
            throw new EZDFieldErrorException();
        }

        NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");
        NMAL7060CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.C, "C");

        NMAL7060CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
    }
}
