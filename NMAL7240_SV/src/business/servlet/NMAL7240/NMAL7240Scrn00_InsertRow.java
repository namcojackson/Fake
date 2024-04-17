/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7240;

import static business.servlet.NMAL7240.constant.NMAL7240Constant.BIZ_ID;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.FREIGHT_RATE;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7240.constant.NMAL7240Constant.NMAM8187E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7240.NMAL7240CMsg;
import business.servlet.NMAL7240.common.NMAL7240CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7240Scrn00_InsertRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Fujitsu         W.Honda         Create          N/A
 * 2016/05/17   Fujitsu         W.Honda         Update          QC#7040
 *</pre>
 */
public class NMAL7240Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        NMAL7240CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL7240CommonLogic.clearMandantoryCheckDetail(scrnMsg);
        NMAL7240CommonLogic.addCheckItemForDetail(scrnMsg);

        // QC#7040 2016/05/17 Add start
        scrnMsg.putErrorScreen();
        // QC#7040 2016/05/17 Add end

        if (scrnMsg.A.length() < scrnMsg.A.getValidCount() + 1) {
            scrnMsg.setMessageInfo(NMAM8187E, new String[] {FREIGHT_RATE, String.valueOf(scrnMsg.A.length())});
            throw new EZDFieldErrorException();
        }

        // QC#7040 2016/05/17 Del start
//        scrnMsg.putErrorScreen();
        // QC#7040 2016/05/17 Del start
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        NMAL7240CMsg bizMsg = new NMAL7240CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;
        NMAL7240CMsg bizMsg  = (NMAL7240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7240CommonLogic.controlScreen(this, scrnMsg);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).lineBizTpDescTxt_A1);
    }
}
