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
public class NMAL2510Scrn00_OpenWin_GLString extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start --------------

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CommonLogic.clearMandantoryCheckForRevenueData(scrnMsg);
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).coaCmpyCd_C2);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).coaExtnCd_C2);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).coaBrCd_C2);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).coaCcCd_C2);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effFromDt_C2);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).effThruDt_C2);
        }
        scrnMsg.addCheckItem(scrnMsg.geoCd_C1);
        // 2016/03/04 S21_NA#4539 Add Start --------------
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        int index = getButtonSelectNumber();

        // Making of sub screen delivery information
        Object[] params = NMAL2510CommonLogic.setParamForGLCommonSearchPopup(scrnMsg, index);

        // Sub screen transition
        setArgForSubScreen(params);

    }
}
