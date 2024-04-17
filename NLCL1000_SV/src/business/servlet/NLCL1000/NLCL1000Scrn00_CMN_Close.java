/**
 *<pre>
 * Copyright (c) 2012 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.servlet.NLCL1000;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1000.NLCL1000CMsg;
import business.servlet.NLCL1000.constant.NLCL1000Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Stock Over View Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/07/17   Fujitsu         Y.Mori          Create          N/A
 *</pre>
 */
public class NLCL1000Scrn00_CMN_Close extends S21CommonHandler implements NLCL1000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;
        NLCL1000CMsg bizMsg = new NLCL1000CMsg();
        bizMsg.setBusinessID("NLCL1000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1000BMsg scrnMsg = (NLCL1000BMsg) bMsg;
        NLCL1000CMsg bizMsg  = (NLCL1000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set argments for main screen.
        Object[] param = (Object[]) getArgForSubScreen();
        if (param != null) {
            EZDMsg.copy(scrnMsg.P, null, (EZDBMsgArray)param[9], null); // DetailList
        }
    }
}
