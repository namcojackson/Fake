/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL1070.NSAL1070CMsg;
//import business.servlet.NSAL1070.constant.NSAL1070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/26/2015   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NSAL1070Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSAL1070BMsg scrnMsg = (NSAL1070BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSAL1070BMsg scrnMsg = (NSAL1070BMsg) bMsg;

        //NSAL1070CMsg bizMsg = new NSAL1070CMsg();
        //bizMsg.setBusinessID("NSAL1070");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NSAL1070BMsg scrnMsg = (NSAL1070BMsg) bMsg;
        //NSAL1070CMsg bizMsg  = (NSAL1070CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
