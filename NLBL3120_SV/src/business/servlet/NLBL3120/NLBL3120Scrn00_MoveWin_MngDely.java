/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         S.Yoshida       Create          N/A
 *</pre>
 */
public class NLBL3120Scrn00_MoveWin_MngDely extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;

        int i = 0;
        int index = getButtonSelectNumber();
        String soNum = scrnMsg.A.no(index).soNum_A1.getValue();

        // Create Parameter
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.setValue(soNum);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "S");

        scrnMsg.P.setValidCount(i);
        Object[] params = new Object[10];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        params[2] = scrnMsg.svcConfigMstrPk_PM;

        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}