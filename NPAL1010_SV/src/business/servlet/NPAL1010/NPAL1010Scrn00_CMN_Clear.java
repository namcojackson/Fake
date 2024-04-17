/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1010.common.NPAL1010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/26   Fujitsu         S.Noguchi       Create          N/A
 * 2012/09/13   Fujitsu         Y.Kamide        Update          N/A
 *</pre>
 */
public class NPAL1010Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        NPAL1010CommonLogic.clearScrnItem(scrnMsg);
        NPAL1010CommonLogic.setListItemProtected(scrnMsg);
        NPAL1010CommonLogic.setScreenList(scrnMsg);

        // 10/13/2015 mod start
        // scrnMsg.setFocusItem(scrnMsg.invtyLocCd);
        scrnMsg.setFocusItem(scrnMsg.rtlWhCatgCd);
        // 10/13/2015 mod end
    }
}
