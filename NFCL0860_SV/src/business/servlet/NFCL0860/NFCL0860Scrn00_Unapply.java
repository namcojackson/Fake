/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0860;

import static business.servlet.NFCL0860.constant.NFCL0860Constant.BIZ_ID;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.FUNC_CD_UPD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0860.NFCL0860CMsg;
import business.servlet.NFCL0860.common.NFCL0860CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL0860Scrn00_Unapply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Fujita        Create          N/A
 *</pre>
 */
public class NFCL0860Scrn00_Unapply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
     // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0860BMsg scrnMsg = (NFCL0860BMsg) bMsg;

        NFCL0860CMsg bizMsg = new NFCL0860CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0860BMsg scrnMsg = (NFCL0860BMsg) bMsg;
        NFCL0860CMsg bizMsg  = (NFCL0860CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL0860CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        
        //---- start add 2016/05/16
        NFCL0860CommonLogic.setGuiAttr(this, scrnMsg);
        //---- end 2016/05/16
        
        scrnMsg.setFocusItem(scrnMsg.invNum_H);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }
}
