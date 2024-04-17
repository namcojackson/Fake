/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NFDL0030.common.NFDL0030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/03/10   Hitachi         S.Nakatani      Create          QC#55645
 *
 *</pre>
 */
public class NFDL0030Scrn00_OpenWin_BankAccountDetail extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0030BMsg scrnMsg = (NFDL0030BMsg) bMsg;
        NFDL0030CommonLogic.clearPopupParam(scrnMsg);

        scrnMsg.putErrorScreen();

        Object[] params = new Object[3];
        params[0] = "";
        params[1] = "";
        params[2] = scrnMsg.dsCustBankAcctRelnPk_H;
        setArgForSubScreen(params);
    }
}
