/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7000Scrn00_MoveWin_CopyPricing
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/16   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7000Scrn00_MoveWin_CopyPricing extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL7000BMsg scrnMsg = (NMAL7000BMsg) bMsg;

        if (scrnMsg.A.getValidCount() > 0) {
            Object[] param = new Object[1];
            param[0] = scrnMsg.A.no(scrnMsg.xxRadioBtn.getValueInt()).prcCatgCd_A1;

            setArgForSubScreen(param);
        }

    }
}
