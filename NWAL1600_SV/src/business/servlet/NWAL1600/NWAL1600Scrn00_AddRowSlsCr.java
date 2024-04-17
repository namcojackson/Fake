/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1600;

import static business.servlet.NWAL1600.constant.NWAL1600Constant.BIZ_ID;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1600.constant.NWAL1600Constant.NWAM0599E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1600.NWAL1600CMsg;
import business.servlet.NWAL1600.common.NWAL1600CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWAL1600Scrn00_AddRowSlsCr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        if (100 < scrnMsg.A.getValidCount()) {
            scrnMsg.setMessageInfo(NWAM0599E);
        }
        if (!NWAL1600CommonLogic.isUnderMaxLineCnt(scrnMsg)) {
            scrnMsg.setMessageInfo(NWAM0599E);
        }
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;

        NWAL1600CMsg bizMsg = new NWAL1600CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NWAL1600BMsg scrnMsg = (NWAL1600BMsg) bMsg;
        NWAL1600CMsg bizMsg = (NWAL1600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }
        NWAL1600CommonLogic.setRowsBGWithClear(scrnMsg);
        NWAL1600CommonLogic.protectInput(this, scrnMsg);

        int index = scrnMsg.A.getValidCount() - 1;
        scrnMsg.setFocusItem(scrnMsg.A.no(index).lineBizRoleTpCd_A);

    }
}
