/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800Scrn00_OpenWin_AcctMain
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL2800Scrn00_OpenWin_AcctMain extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        int index = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).dsAcctNum_A1)) {
            return;
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        int index = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(index).dsAcctNum_A1)) {
            throw new EZDFieldErrorException();
        }

        Object[] params = new Object[2];
        params[0] = scrnMsg.A.no(index).dsAcctNum_A1;
        params[1] = null;

        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
