/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1530;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1530 Min-Max Planning Report Run Screen
 * Function Name : Return
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/23/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1530Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // There is no processing.

    }
}
