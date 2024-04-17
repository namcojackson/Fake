/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0220;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0220.NFAL0220CMsg;
import business.servlet.NFAL0220.common.NFAL0220CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/05   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NFAL0220Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum, scrnMsg.xxPageShowToNum, scrnMsg.xxPageShowOfNum, scrnMsg.xxPageShowCurNum, scrnMsg.xxPageShowTotNum);
        NFAL0220CMsg bizMsg = NFAL0220CommonLogic.setRequestData_SearchCommon();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0220BMsg scrnMsg = (NFAL0220BMsg) bMsg;
        NFAL0220CMsg bizMsg = (NFAL0220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL0220CommonLogic.screenControlPage(this, scrnMsg);

    }
}
