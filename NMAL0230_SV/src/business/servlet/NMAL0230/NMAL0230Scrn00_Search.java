/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0230;

import static business.servlet.NMAL0230.constant.NMAL0230Constant.BIZ_ID;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL0230.constant.NMAL0230Constant.MESSAGE_KIND_ERROR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0230.NMAL0230CMsg;
import business.servlet.NMAL0230.common.NMAL0230CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0230Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 *</pre>
 */
public class NMAL0230Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0230BMsg scrnMsg = (NMAL0230BMsg) bMsg;

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

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NMAL0230CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        scrnMsg.setFocusItem(scrnMsg.mdseCd_BO);
    }
}