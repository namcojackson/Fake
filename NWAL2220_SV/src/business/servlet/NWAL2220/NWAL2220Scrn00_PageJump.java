/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220;

import static business.servlet.NWAL2220.constant.NWAL2220Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2220.NWAL2220CMsg;
import business.servlet.NWAL2220.constant.NWAL2220Constant.FUNC;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/11   Fujitsu         T.Yoshida       Create          QC#14410
 * </pre>
 */
public class NWAL2220Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, //
                scrnMsg.A, //
                scrnMsg.xxPageShowFromNum, //
                scrnMsg.xxPageShowToNum, //
                scrnMsg.xxPageShowOfNum, //
                scrnMsg.xxPageShowCurNum, //
                scrnMsg.xxPageShowTotNum);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(scrnMsg, //
                scrnMsg.A, //
                scrnMsg.xxPageShowFromNum, //
                scrnMsg.xxPageShowToNum, //
                scrnMsg.xxPageShowOfNum, //
                scrnMsg.xxPageShowCurNum, //
                scrnMsg.xxPageShowTotNum);

        NWAL2220CMsg bizMsg = new NWAL2220CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        NWAL2220CMsg bizMsg = (NWAL2220CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
