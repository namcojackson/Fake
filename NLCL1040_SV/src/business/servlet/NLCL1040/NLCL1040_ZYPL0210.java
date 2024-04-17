/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLCL1040.common.NLCL1040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040_ZYPL0210
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1040_ZYPL0210 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1040BMsg scrnMsg = (NLCL1040BMsg) bMsg;

        NLCL1040CommonLogic.ctrlScrnItemDisplay(this, scrnMsg);

    }
}
