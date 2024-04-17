/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.SCREEN_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_CUSTOMER;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NWAL1840Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            scrnMsg.schdAgmtNum.setValue(param0.getValue());

        } else {
            // To process the menu transition.
            scrnMsg.schdAgmtNum.clear();
        }

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
        scrnMsg.xxDplyTab.setValue(TAB_CUSTOMER);
        scrnMsg.setFocusItem(scrnMsg.schdAgmtNum);

        NWAL1840CommonLogic.initCommonButton(this);
        NWAL1840CommonLogic.setProtect(this, scrnMsg);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

    }
}
