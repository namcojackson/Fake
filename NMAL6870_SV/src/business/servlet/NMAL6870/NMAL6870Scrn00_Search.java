/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL6870;

import static business.servlet.NMAL6870.constant.NMAL6870Constant.SCRN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6870.NMAL6870CMsg;
import business.servlet.NMAL6870.common.NMAL6870CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Multi Selection Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/27   Fujitsu         S.Yoshida         Create          N/A
 *</pre>
 */
public class NMAL6870Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6870CommonLogic.ezCheck(bMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6870BMsg scrnMsg = (NMAL6870BMsg) bMsg;

        // prepare business logic call
        NMAL6870CMsg bizMsg = NMAL6870CommonLogic.setRequestData_NMAL6870Scrn00_Search();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6870BMsg scrnMsg = (NMAL6870BMsg) bMsg;
        NMAL6870CMsg bizMsg = (NMAL6870CMsg) cMsg;

        // copy data from CMsg onto BMsg
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        NMAL6870CommonLogic.ezCheck(scrnMsg);

        NMAL6870CommonLogic.setTableBGColor(scrnMsg);

        NMAL6870CommonLogic.initDisplayInfo(this, scrnMsg);

        NMAL6870CommonLogic.selection(scrnMsg);

        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
