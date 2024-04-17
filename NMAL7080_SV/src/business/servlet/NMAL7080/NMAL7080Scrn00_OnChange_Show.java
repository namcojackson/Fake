/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7080;

import static business.servlet.NMAL7080.constant.NMAL7080Constant.BIZ_ID;
import static business.servlet.NMAL7080.constant.NMAL7080Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7080.NMAL7080CMsg;
import business.servlet.NMAL7080.common.NMAL7080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supply Agreement Plan Set Up
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7080Scrn00_OnChange_Show extends S21CommonHandler {
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;

        NMAL7080CommonLogic.clearMandantoryCheckHeader(scrnMsg);
        NMAL7080CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.splyAgmtPlnPk)) {
            scrnMsg.splyAgmtPlnPk.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.splyAgmtPlnPk.getNameForMessage() });
        }

        NMAL7080CommonLogic.headerAddCheckItem(scrnMsg);
        NMAL7080CommonLogic.detailAddCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;

        NMAL7080CMsg bizMsg = new NMAL7080CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7080BMsg scrnMsg = (NMAL7080BMsg) bMsg;
        NMAL7080CMsg bizMsg = (NMAL7080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (scrnMsg.A.getValidCount() <= 0) {
            NMAL7080CommonLogic.zeroDetailScreen(this, scrnMsg);
        } else {
            NMAL7080CommonLogic.searchScreen(this, scrnMsg);
        }

        scrnMsg.setFocusItem(scrnMsg.splyAgmtPlnStsCd);

    }
}
