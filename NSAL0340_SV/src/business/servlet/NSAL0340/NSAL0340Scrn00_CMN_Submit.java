/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0340;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0340.NSAL0340CMsg;
import business.servlet.NSAL0340.constant.NSAL0340Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/09   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NSAL0340Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0340BMsg scrnMsg = (NSAL0340BMsg) bMsg;

        NSAL0340CMsg bizMsg = new NSAL0340CMsg();
        bizMsg.setBusinessID(NSAL0340Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0340Constant.FUNCTION_SUBMIT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0340BMsg scrnMsg = (NSAL0340BMsg) bMsg;
        NSAL0340CMsg bizMsg = (NSAL0340CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).skipRecovTpCd_A0);
        }
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.A.no(0).skipRecovTpCd_A0);
    }
}
