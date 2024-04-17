/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2070.NFBL2070CMsg;
//import business.servlet.NFBL2070.constant.NFBL2070Constant;

import business.blap.NFBL2070.NFBL2070CMsg;
import business.servlet.NFBL2070.common.NFBL2070CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Compensation Credit I/F Error Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   CUSA            Y.Miyauchi      Create          New
 *</pre>
 */
public class NFBL2070Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_P1, scrnMsg.xxPageShowToNum_P1, scrnMsg.xxPageShowOfNum_P1, scrnMsg.xxPageShowCurNum_P1, scrnMsg.xxPageShowTotNum_P1);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;

        NFBL2070CMsg bizMsg = new NFBL2070CMsg();
        bizMsg.setBusinessID("NFBL2070");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;
        NFBL2070CMsg bizMsg  = (NFBL2070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFBL2070CommonLogic.openSelectedRow(scrnMsg);
    }
}
