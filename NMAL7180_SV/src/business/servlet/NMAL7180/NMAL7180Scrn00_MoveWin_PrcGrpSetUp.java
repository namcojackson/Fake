/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7180;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7180.common.NMAL7180CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7180Scrn00_MoveWin_PrcGrpSetUp
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 *</pre>
 */
public class NMAL7180Scrn00_MoveWin_PrcGrpSetUp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;

        NMAL7180CommonLogic.addCheckHeaderItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7180BMsg scrnMsg = (NMAL7180BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName()); // QC#18785

        Object[] params = new Object[1];
        params[0] = scrnMsg.A.no(scrnMsg.xxRadioBtn.getValueInt()).prcGrpPk_A1;
        setArgForSubScreen(params);
    }
}
