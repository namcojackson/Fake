/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_CUST_CODE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_OpenWin_CustCode
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/18   Hitachi         J.Kim           Update          QC#6581
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 *</pre>
 */
public class NLEL0060Scrn00_OpenWin_CustCode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        scrnMsg.P.clear();

        // START 2016/04/18 J.Kim [QC#6581,MOD]
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.sellToCustCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.shipToCustAcctCd_H1);
        // END 2016/04/18 J.Kim [QC#6581,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(36).xxPopPrm, OPENWIN_CUST_CODE);

        // START 2018/07/10 J.Kim [QC#27177,MOD]
        // Object[] params = new Object[37];
        Object[] params = new Object[36];
        // END 2018/07/10 J.Kim [QC#27177,MOD]
        for (int i = 0; i < params.length; i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm;
        }

        setArgForSubScreen(params);
    }
}
