/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3080;

import static business.servlet.NFCL3080.constant.NFCL3080Constant.BIZ_ID;
import static business.servlet.NFCL3080.constant.NFCL3080Constant.FUNC_CD_UPD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3080.NFCL3080CMsg;
import business.servlet.NFCL3080.common.NFCL3080CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3080Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/1/13    Fujitsu         S.Fujita        Create          N/A
 *</pre>
 */
public class NFCL3080Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3080BMsg scrnMsg = (NFCL3080BMsg) bMsg;
        NFCL3080CommonLogic.addCheckAllItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3080BMsg scrnMsg = (NFCL3080BMsg) bMsg;

        NFCL3080CMsg bizMsg = new NFCL3080CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3080BMsg scrnMsg = (NFCL3080BMsg) bMsg;
        NFCL3080CMsg bizMsg = (NFCL3080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3080CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        NFCL3080CommonLogic.addCheckAllItem(scrnMsg);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.invNum);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }
}
