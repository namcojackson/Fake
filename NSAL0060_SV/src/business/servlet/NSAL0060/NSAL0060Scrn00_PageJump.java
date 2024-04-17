/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0060;

import static business.servlet.NSAL0060.constant.NSAL0060Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0060.NSAL0060CMsg;
import business.servlet.NSAL0060.common.NSAL0060CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public class NSAL0060Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg,
                scrnMsg.A,
                scrnMsg.xxPageShowFromNum,
                scrnMsg.xxPageShowToNum,
                scrnMsg.xxPageShowOfNum,
                scrnMsg.xxPageShowCurNum,
                scrnMsg.xxPageShowTotNum);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlGrpNm_SR);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlGrpDescTxt_SR);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJumpNotClear(scrnMsg,
                scrnMsg.A,
                scrnMsg.xxPageShowFromNum,
                scrnMsg.xxPageShowToNum,
                scrnMsg.xxPageShowOfNum,
                scrnMsg.xxPageShowCurNum,
                scrnMsg.xxPageShowTotNum);

        NSAL0060CMsg bizMsg = new NSAL0060CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;
        NSAL0060CMsg bizMsg  = (NSAL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0060CommonLogic.setProtected(scrnMsg, this);
        NSAL0060CommonLogic.checkAuth(scrnMsg, this);
        NSAL0060CommonLogic.setBgColor(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).mdlGrpNm_SR);
    }
}
