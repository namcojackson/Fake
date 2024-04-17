/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NPAL1160 PO Approval Setting Screen
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/24   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/08   CITS            R.Shimamoto     Update          V0.1
 *</pre>
 */
public class NPAL1160Scrn00_OpenWinGrp extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // no process.
        // STR 2016/03/08 R.Shimamoto Delete
        // NPAL1160BMsg scrnMsg = (NPAL1160BMsg) bMsg;
        // int idx = getButtonSelectNumber();
        // NPAL1160CommonLogic.clearParamsOpenWinPrchGrp(scrnMsg);
        // Object[] params =
        // NPAL1160CommonLogic.setParamsOpenWinPrchGrp(scrnMsg, idx);
        // setArgForSubScreen(params);
        // scrnMsg.xxMntEventNm.setValue(EVENT_ID_OPEN_WIN_GRP);
        // END 2016/03/08 R.Shimamoto Delete
    }
}
