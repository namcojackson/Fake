/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0230;

import static business.servlet.NMAL0230.constant.NMAL0230Constant.BIZ_ID;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0230.NMAL0230CMsg;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/07   Fujitsu         K.Ishizuka      Create          QC#16807
 *</pre>
 */
public class NMAL0230Scrn00_PageJump extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;
        S21BatchSearchPagenationScrnSupport.checkInputForPageJump(//
                scrnMsg //
                , scrnMsg.A //
                , scrnMsg.xxPageShowFromNum //
                , scrnMsg.xxPageShowToNum //
                , scrnMsg.xxPageShowOfNum //
                , scrnMsg.xxPageShowCurNum //
                , scrnMsg.xxPageShowTotNum);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;

        S21BatchSearchPagenationScrnSupport.setRequestDataForPageJump(//
                scrnMsg//
                , scrnMsg.A//
                , scrnMsg.xxPageShowFromNum//
                , scrnMsg.xxPageShowToNum//
                , scrnMsg.xxPageShowOfNum//
                , scrnMsg.xxPageShowCurNum//
                , scrnMsg.xxPageShowTotNum);

        NMAL0230CMsg bizMsg = new NMAL0230CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;
        NMAL0230CMsg bizMsg = (NMAL0230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
