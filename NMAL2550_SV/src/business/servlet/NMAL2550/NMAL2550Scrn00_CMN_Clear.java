/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2550;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2550.NMAL2550CMsg;
import business.servlet.NMAL2550.constant.NMAL2550Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2550Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2550BMsg scrnMsg = (NMAL2550BMsg) bMsg;

        NMAL2550CMsg bizMsg = new NMAL2550CMsg();
        bizMsg.setBusinessID(NMAL2550Constant.APP_ID);
        bizMsg.setFunctionCode(NMAL2550Constant.FUNCTION_CODE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2550BMsg scrnMsg = (NMAL2550BMsg) bMsg;
        NMAL2550CMsg bizMsg = (NMAL2550CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
