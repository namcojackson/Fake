/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.REBIL;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/30   Hitachi         E.Kameishi      Create          QC#26229
 *</pre>
 */
public class NFCL3070Scrn00_MoveWin_InvoiceRebill extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;
        Object[] params = new Object[1];
        params[0] = scrnMsg.invNum_R;

        setArgForSubScreen(params);

    }
}
