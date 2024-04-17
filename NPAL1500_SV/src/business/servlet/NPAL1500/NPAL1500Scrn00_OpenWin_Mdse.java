/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_Mdse extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        int idx = this.getButtonSelectNumber();

        scrnMsg.addCheckItem(scrnMsg.A.no(idx).poLineTpCd_A1);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        int idx = this.getButtonSelectNumber();

        // Initialization of subscreen delivery information
        NPAL1500CommonLogic.setInitParamForMdsePopup(scrnMsg, idx);

        // Making of subscreen delivery information
        Object[] params = NPAL1500CommonLogic.setParamForMdsePopup(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
