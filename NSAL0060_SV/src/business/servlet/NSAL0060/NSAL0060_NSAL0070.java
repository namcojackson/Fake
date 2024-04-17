/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *
 * Model Group Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/08/2013   Hitachi         Y.Igarashi         Create          N/A
 *</pre>
 */
public class NSAL0060_NSAL0070 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;

        //NSAL0060CMsg bizMsg = new NSAL0060CMsg();
        //bizMsg.setBusinessID("NSAL0060");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NSAL0060BMsg scrnMsg = (NSAL0060BMsg) bMsg;
        //NSAL0060CMsg bizMsg  = (NSAL0060CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
