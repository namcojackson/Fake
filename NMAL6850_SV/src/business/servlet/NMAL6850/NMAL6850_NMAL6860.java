/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6850;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
// business.blap.NMAL6850.NMAL6850CMsg;
// import business.servlet.NMAL6850.constant.NMAL6850Constant;

import business.blap.NMAL6850.NMAL6850CMsg;
import business.servlet.NMAL6850.common.NMAL6850CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/23   CITS            M.Ouchi         Create          N/A
 *</pre>
 */
public class NMAL6850_NMAL6860 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return NMAL6850CommonLogic.copyScrnMsgToBizMsgForSearch((NMAL6850BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;
        NMAL6850CMsg bizMsg = (NMAL6850CMsg) cMsg;
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }

        // focus on Supplier Number.
        scrnMsg.setFocusItem(scrnMsg.prntVndCd_H);
    }
}
