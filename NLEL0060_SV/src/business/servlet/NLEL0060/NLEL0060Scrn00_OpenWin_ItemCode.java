/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_OpenWin_ItemCode
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/15   Hitachi         J.Kim           Update          QC#6581
 *</pre>
 */
public class NLEL0060Scrn00_OpenWin_ItemCode extends S21CommonHandler {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.mdseCd_H1);
        // START 2016/04/15 J.Kim [QC#6581,MOD]
        // ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, "AL");
        // Mode Length (10)
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, "10");
        // END 2016/04/15 J.Kim [QC#6581,MOD]

        Object[] params = new Object[10];
        for (int i = 0; i < params.length; i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm;
        }

        setArgForSubScreen(params);
    }
}
