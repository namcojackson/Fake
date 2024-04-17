/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.SCREEN_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_LINES;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_SA_HISTORY;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/10/10   Hitachi         T.Fukuta        Create          CSA-QC#61730
 *</pre>
 */
public class NWAL1840Scrn00_TAB_SaHistory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (TAB_SA_HISTORY.equals(dplyTab) || TAB_LINES.equals(dplyTab) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(0).mdseCd_A)) {
            return;
        }

        NWAL1840CommonLogic.checkItemAllFields(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1840CommonLogic.checkItemAllFields(scrnMsg);
        scrnMsg.putErrorScreen();

        scrnMsg.xxDplyTab.setValue(TAB_SA_HISTORY);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        NWAL1840CommonLogic.setProtect(this, scrnMsg);
        NWAL1840CommonLogic.setTblBackColorForSaHistory(scrnMsg);
        NWAL1840CommonLogic.setTblBackColorForSummary(scrnMsg);
    }
}
