/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539 
 *</pre>
 */
public class NMAL2610Scrn00_OpenWin_Territory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start --------------
        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CommonLogic.clearMandantoryForHeader(scrnMsg);
        NMAL2610CommonLogic.addCheckItem(scrnMsg, false);
        // 2016/03/04 S21_NA#4539 Add Start --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        // Making of sub screen delivery information
        Object[] params = NMAL2610CommonLogic.setParamForHeaderTerritorySearchPopup(scrnMsg);

        // Sub screen transition
        setArgForSubScreen(params);

    }
}
