/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL0300.NLCL0300CMsg;
//import business.servlet.NLCL0300.constant.NLCL0300Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLCL0300_ZYPL0310 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;

        //NLCL0300CMsg bizMsg = new NLCL0300CMsg();
        //bizMsg.setBusinessID("NLCL0300");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NLCL0300BMsg scrnMsg = (NLCL0300BMsg) bMsg;
        //NLCL0300CMsg bizMsg  = (NLCL0300CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
