/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Hitachi         A.Kohinata      Create          QC#5375
 *</pre>
 */
public class NSAL0990Scrn00_OnChange_PaymentMethod extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CommonLogic.setProtectByPmtMeth(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.dsPmtMethCd);
    }
}
