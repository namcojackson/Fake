/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NPAL1130 Replenishment Plan Inquiry (Detail)
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/21   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NPAL1130Scrn00_OpenWinMdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.mdseDescShortTxt);
        scrnMsg.P.no(2).xxPopPrm.clear();
        scrnMsg.P.no(3).xxPopPrm.clear();
        scrnMsg.P.no(4).xxPopPrm.clear();
        scrnMsg.P.no(5).xxPopPrm.clear();
        scrnMsg.P.no(6).xxPopPrm.clear();
        scrnMsg.P.no(7).xxPopPrm.clear();
        scrnMsg.P.no(8).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, "AL");

        Object[] params = new Object[10];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;

        setArgForSubScreen(params);
    }
}
