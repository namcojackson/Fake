/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0150.NSAL0150CMsg;
//import business.servlet.NSAL0150.constant.NSAL0150Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 *</pre>
 */
public class NSAL0150_NSAL0240 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;

        //NSAL0150CMsg bizMsg = new NSAL0150CMsg();
        //bizMsg.setBusinessID("NSAL0150");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        //NSAL0150CMsg bizMsg  = (NSAL0150CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
