/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0220;

import static business.servlet.NFAL0220.constant.NFAL0220Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFAL0220Scrn00_OpenWin_DsAcctSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/03   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NFAL0220Scrn00_OpenWin_DsAcctSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;

        scrnMsg.P.clear();
        Object[] params = new Object[PRM_NMAL6760];
        int i = 0;

        int index = getButtonSelectNumber();
        setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(index).dsAcctNum_A);
        params[i++] = scrnMsg.P.no(0).xxPopPrm;
        params[i++] = scrnMsg.P.no(1).xxPopPrm;
        params[i++] = scrnMsg.P.no(2).xxPopPrm;
        params[i++] = scrnMsg.P.no(3).xxPopPrm;
        params[i++] = scrnMsg.P.no(4).xxPopPrm;
        params[i++] = scrnMsg.P.no(5).xxPopPrm;
        params[i++] = scrnMsg.P.no(6).xxPopPrm;
        params[i++] = scrnMsg.P.no(7).xxPopPrm;
        params[i++] = scrnMsg.P.no(8).xxPopPrm;
        params[i++] = scrnMsg.P.no(9).xxPopPrm;
        params[i++] = scrnMsg.P.no(10).xxPopPrm;
        params[i++] = scrnMsg.P.no(11).xxPopPrm;
        setValue(scrnMsg.P.no(12).xxPopPrm, DISP_HRCH_ACCT_CD_BILL);
        params[i++] = scrnMsg.P.no(12).xxPopPrm;
        setArgForSubScreen(params);
    }
}
