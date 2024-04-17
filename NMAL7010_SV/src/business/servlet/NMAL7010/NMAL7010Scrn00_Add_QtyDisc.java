/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.NMAM8190E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_Add_QtyDisc
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7010Scrn00_Add_QtyDisc extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        if (scrnMsg.I.getValidCount() <= 0) {
            scrnMsg.setMessageInfo(NMAM8190E);
            throw new EZDFieldErrorException();
        }

        for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.J.no(i).qtyDiscDtlQty_PJ);
            scrnMsg.addCheckItem(scrnMsg.J.no(i).pkgUomCd_PJ);
            scrnMsg.addCheckItem(scrnMsg.J.no(i).prcBreakAmt_PJ);
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        NMAL7010CMsg bizMsg = new NMAL7010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg  = (NMAL7010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectIdx = bizMsg.J.getValidCount() - 1;
        scrnMsg.setFocusItem(scrnMsg.J.no(selectIdx).qtyDiscDtlQty_PJ);
    }
}
