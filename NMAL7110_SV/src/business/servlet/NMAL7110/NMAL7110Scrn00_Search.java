/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7110.NMAL7110CMsg;
import business.servlet.NMAL7110.common.NMAL7110CommonLogic;
import business.servlet.NMAL7110.constant.NMAL7110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NMAL7110Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.prcContrNm);
        scrnMsg.addCheckItem(scrnMsg.prcContrNum);
        scrnMsg.addCheckItem(scrnMsg.prcContrCustBidNum);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;

        NMAL7110CMsg bizMsg = new NMAL7110CMsg();
        bizMsg.setBusinessID(NMAL7110Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7110BMsg scrnMsg = (NMAL7110BMsg) bMsg;
        NMAL7110CMsg bizMsg  = (NMAL7110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (NMAL7110Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL7110CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NMAL7110CommonLogic.protectedControl(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcContrNm);

    }
}
