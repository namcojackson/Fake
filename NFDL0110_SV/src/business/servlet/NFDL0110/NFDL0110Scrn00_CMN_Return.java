/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0110.NFDL0110CMsg;
//import business.servlet.NFDL0110.constant.NFDL0110Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Customer Care
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/14   CUSA            K.Lee           Create          N/A
 *</pre>
 */
public class NFDL0110Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFDL0110BMsg scrnMsg = (NFDL0110BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFDL0110BMsg scrnMsg = (NFDL0110BMsg) bMsg;

        //NFDL0110CMsg bizMsg = new NFDL0110CMsg();
        //bizMsg.setBusinessID("NFDL0110");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NFDL0110BMsg scrnMsg = (NFDL0110BMsg) bMsg;
        //NFDL0110CMsg bizMsg  = (NFDL0110CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
