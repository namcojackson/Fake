/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1260;

import static business.servlet.NSAL1260.constant.NSAL1260Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1260.NSAL1260CMsg;
import business.servlet.NSAL1260.common.NSAL1260CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract Machine Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class NSAL1260Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1260BMsg scrnMsg = (NSAL1260BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);
        NSAL1260CommonLogic.addCheckItemDetailForUpdate(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1260BMsg scrnMsg = (NSAL1260BMsg) bMsg;
        NSAL1260CMsg bizMsg = new NSAL1260CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1260BMsg scrnMsg = (NSAL1260BMsg) bMsg;
        NSAL1260CMsg bizMsg = (NSAL1260CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1260CommonLogic.screenItemControl(this, scrnMsg);
    }
}