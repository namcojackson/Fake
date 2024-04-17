/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.common.NPAL1500CommonLogic.getParamNWAL1130ForPaymentTerm;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.LINK_OPENWIN_PAYMENT_TERM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;


import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/25/2018   CITS            K.Kameoka       Update          QC#28226
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_PoTerm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Do nothing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, LINK_OPENWIN_PAYMENT_TERM);
        Object[] params = getParamNWAL1130ForPaymentTerm(scrnMsg);

        setArgForSubScreen(params);

    }
}
