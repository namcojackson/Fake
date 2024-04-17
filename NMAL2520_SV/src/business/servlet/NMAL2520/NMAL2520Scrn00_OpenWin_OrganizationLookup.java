/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539
 *</pre>
 */
public class NMAL2520Scrn00_OpenWin_OrganizationLookup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start ------------
        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CommonLogic.clearMandantoryCheck(scrnMsg);
        NMAL2520CommonLogic.addCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Add End --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        // Making of sub screen delivery information
        Object[] params = NMAL2520CommonLogic.setParamForHeaderOrganizationSearchPopup(scrnMsg);

        // Sub screen transition
        setArgForSubScreen(params);
    }
}
