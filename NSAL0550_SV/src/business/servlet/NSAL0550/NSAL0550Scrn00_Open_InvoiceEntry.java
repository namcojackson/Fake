/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0550;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/24   Hitachi         T.Kanasaka      Update          QC#3353
 *</pre>
 */
public class NSAL0550Scrn00_Open_InvoiceEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;
        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_00, scrnMsg.A.no(idx).svcInvNum_A1);
        Object[] prm = new Object[1];
        prm[0] = scrnMsg.xxPopPrm_00;
        setArgForSubScreen(prm);
    }
}
