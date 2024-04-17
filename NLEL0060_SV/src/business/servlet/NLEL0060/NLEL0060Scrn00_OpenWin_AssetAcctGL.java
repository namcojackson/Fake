/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.OPENWIN_ASSET_ACCT_GL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_OpenWin_AssetAcctGL
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/30   Fujitsu         C.Tanaka        Create          QC#13360
 * 2017/11/01   Hitachi         J.Kim           Update          QC#16345
 *</pre>
 */
public class NLEL0060Scrn00_OpenWin_AssetAcctGL extends S21CommonHandler {

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

        // START 2016/11/01 J.Kim [QC#16345,DEL]
        //// START 2016/10/17 J.Kim [QC#13453,ADD]
        //int lineNum = getButtonSelectNumber();
        //String assetAcctGl = scrnMsg.B.no(lineNum).xxScrItem50Txt_B1.getValue();
        //if (!NLEL0060CommonLogic.checkSearchPopupScreen(scrnMsg, assetAcctGl, lineNum)) {
        //    for (int i = 0; i < scrnMsg.B.length(); i++) {
        //        scrnMsg.B.no(lineNum).xxScrItem50Txt_B1.setErrorInfo(1, NLEM0007E);
        //        NLEL0060_BBMsg abMsg = scrnMsg.B.no(i);
        //        scrnMsg.addCheckItem(abMsg.xxScrItem50Txt_B1);
        //        scrnMsg.putErrorScreen();
        //    }
        //}
        //// END 2016/10/17 J.Kim [QC#13453,ADD]
        // END 2016/11/01 J.Kim [QC#16345,DEL]

        scrnMsg.P.no(0).xxPopPrm.setValue(BIZ_ID + "01");
        scrnMsg.P.no(10).xxPopPrm.setValue(OPENWIN_ASSET_ACCT_GL);

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
