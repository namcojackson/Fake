/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/10/19   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539 
 *</pre>
 */
public class NMAL2510Scrn00_OpenWin_Location extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Mod Start ------------
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        scrnMsg.xxAllLineAddr_H1.clearErrorInfo();
        NMAL2510CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2510CommonLogic.addCheckHeaderItems(scrnMsg);
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Mod End --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        // Making of sub screen delivery information
        Object[] params = NMAL2510CommonLogic.setParamForLocationSearchPopup(scrnMsg);

        // Sub screen transition
        setArgForSubScreen(params);

    }
}
