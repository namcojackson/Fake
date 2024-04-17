/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2410;

import static business.servlet.NWAL2410.constant.NWAL2410Constant.BIZ_ID;
import static business.servlet.NWAL2410.constant.NWAL2410Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2410.NWAL2410CMsg;
import business.servlet.NWAL2410.common.NWAL2410CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2410Scrn00_InsertRow
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 *</pre>
 */
public class NWAL2410Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        NWAL2410CommonLogic.addCheckDetailsItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;

        NWAL2410CMsg bizMsg = new NWAL2410CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2410BMsg scrnMsg = (NWAL2410BMsg) bMsg;
        NWAL2410CMsg bizMsg = (NWAL2410CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2410CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        // scrnMsg.addCheckItem(scrnMsg.A);
        NWAL2410CommonLogic.addCheckDetailsItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            throw new EZDFieldErrorException();
        }
        NWAL2410CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWAL2410CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).docMgtOrgCd_A);

    }
}
