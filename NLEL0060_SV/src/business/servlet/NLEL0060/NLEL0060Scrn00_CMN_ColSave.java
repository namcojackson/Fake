/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0060.NLEL0060CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/07   Hitachi         J.Kim           Create          QC#5521
 *</pre>
 */
public class NLEL0060Scrn00_CMN_ColSave extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing to do.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        NLEL0060CMsg bizMsg = new NLEL0060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}
