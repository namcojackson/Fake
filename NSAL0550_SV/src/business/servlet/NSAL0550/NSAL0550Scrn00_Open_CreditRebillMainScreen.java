/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0550;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0550.NSAL0550CMsg;
//import business.servlet.NSAL0550.constant.NSAL0550Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/21   CITS            M.Naito         Create          CSA QC#18758
 *</pre>
 */
public class NSAL0550Scrn00_Open_CreditRebillMainScreen extends S21CommonHandler {

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
        Object[] prm = new Object[1];
        prm[0] = scrnMsg.A.no(idx).custCareTktNum_A1;
        setArgForSubScreen(prm);
    }
}
