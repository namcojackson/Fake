/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLEL0060.common.NLEL0060CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_OpenWin_ExpAcctGL
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/30   Fujitsu         C.Tanaka        Create          QC#13360
 * 2016/10/17   Hitachi         J.Kim           Update          QC#13453 
 *</pre>
 */
public class NLEL0060Scrn00_OpenWin_ExpAcctGL extends S21CommonHandler {

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

        // START 2016/10/17 J.Kim [QC#13453,ADD]
        int lineNum = getButtonSelectNumber();
        String expAcctGl = scrnMsg.B.no(lineNum).xxScrItem50Txt_B2.getValue();
        if (!NLEL0060CommonLogic.checkSearchPopupScreen(scrnMsg, expAcctGl, lineNum)) {
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                scrnMsg.B.no(lineNum).xxScrItem50Txt_B2.setErrorInfo(1, NLEM0007E);
                NLEL0060_BBMsg abMsg = scrnMsg.B.no(i);
                scrnMsg.addCheckItem(abMsg.xxScrItem50Txt_B2);
                scrnMsg.putErrorScreen();
            }
        }
        // END 2016/10/17 J.Kim [QC#13453,ADD]

        scrnMsg.P.no(0).xxPopPrm.setValue(BIZ_ID + "02");
        scrnMsg.P.no(10).xxPopPrm.setValue(OPENWIN_EXP_ACCT_GL);

        Object[] params = new Object[11];
        int index = 0;
        params[index++] = scrnMsg.P.no(0).xxPopPrm;
        params[index++] = scrnMsg.P.no(1).xxPopPrm;
        params[index++] = scrnMsg.P.no(2).xxPopPrm;
        params[index++] = scrnMsg.P.no(3).xxPopPrm;
        params[index++] = scrnMsg.P.no(4).xxPopPrm;
        params[index++] = scrnMsg.P.no(5).xxPopPrm;
        params[index++] = scrnMsg.P.no(6).xxPopPrm;
        params[index++] = scrnMsg.P.no(7).xxPopPrm;
        params[index++] = scrnMsg.P.no(8).xxPopPrm;
        params[index++] = scrnMsg.P.no(9).xxPopPrm;
        params[index++] = scrnMsg.P.no(10).xxPopPrm;

        setArgForSubScreen(params);
    }
}
