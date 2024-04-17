/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7030.common.NMAL7030CommonLogic;
import business.servlet.NMAL7030.constant.NMAL7030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   SRA             E.Inada         Create          QC#2174
 *</pre>
 */
public class NMAL7030Scrn00_Add_QtyDisc extends S21CommonHandler implements NMAL7030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7030BMsg scrnMsg = (NMAL7030BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).qtyDiscDtlQty_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcBreakAmt_A);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7030BMsg scrnMsg = (NMAL7030BMsg) bMsg;

        NMAL7030CommonLogic.addNewLine(scrnMsg);

        NMAL7030CommonLogic.setDetailProtect(scrnMsg);
    }
}
