/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6720.NMAL6720CMsg;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/06   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 *</pre>
 */
public class NMAL6720Scrn00_TAB_Classifications extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CommonLogic.exceptMandatoryError(scrnMsg);
        NMAL6720CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6720CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
    }
}
