/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.SCREEN_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_HEADER;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_LINES;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Fujitsu         T.Murai         Create          N/A
 * 2016/10/13   Fujitsu         H.Ikeda         Update          S21_NA#13032
 *</pre>
 */
public class NWAL1840Scrn00_TAB_Header extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        // S21_NA#13032 Mod Start
        //if (TAB_HEADER.equals(dplyTab) || TAB_LINES.equals(dplyTab) && ZYPCommonFunc.hasValue(scrnMsg.A.no(0).mdseCd_A)) {
        if (TAB_HEADER.equals(dplyTab) || TAB_LINES.equals(dplyTab) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(0).mdseCd_A)) {
        // S21_NA#13032 Mod End
            return;
        }

        NWAL1840CommonLogic.checkItemAllFields(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        scrnMsg.xxDplyTab.setValue(TAB_HEADER);
        scrnMsg.setFocusItem(scrnMsg.custIssPoNum);

        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        NWAL1840CommonLogic.setProtect(this, scrnMsg);
        NWAL1840CommonLogic.setTblBackColorForSummary(scrnMsg);

    }
}
