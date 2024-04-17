/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3060Scrn00_Adjust
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/14   Fujitsu         S.Fujita        Create          QC#11023
 *</pre>
 */
public class NFCL3060Scrn00_Adjust extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3060BMsg scrnMsg = (NFCL3060BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.arTrxNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3060BMsg scrnMsg = (NFCL3060BMsg) bMsg;
        if (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn)) {
            int radioValue = scrnMsg.xxRadioBtn.getValueInt();
            if (scrnMsg.A.getValidCount() > radioValue && radioValue >= 0) {
                Object[] params = new Object[1];
                params[0] = scrnMsg.A.no(radioValue).arTrxNum_A;
                setArgForSubScreen(params);
            }
        }
    }
}
