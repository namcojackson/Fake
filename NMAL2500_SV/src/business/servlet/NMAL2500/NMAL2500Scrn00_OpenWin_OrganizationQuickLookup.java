/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2500.common.NMAL2500CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2015/03/04   Fujitsu         M.suzuki        Update          S21_NA#4539
 *</pre>
 */
public class NMAL2500Scrn00_OpenWin_OrganizationQuickLookup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start ------------
        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;
        NMAL2500CommonLogic.addcheckItemH2(scrnMsg);
        scrnMsg.putErrorScreen();
        // 2016/03/04 S21_NA#4539 Add End --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        // Making of sub screen delivery information
        Object[] params = NMAL2500CommonLogic.setParamForOrganizationSearchPopup(scrnMsg);

        // Sub screen transition
        setArgForSubScreen(params);
    }
}
