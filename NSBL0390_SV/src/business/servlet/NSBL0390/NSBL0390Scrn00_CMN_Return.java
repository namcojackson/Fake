/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0390;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSBL0390.NSBL0390CMsg;
//import business.servlet.NSBL0390.constant.NSBL0390Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NSBL0390Scrn00_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSBL0390BMsg scrnMsg = (NSBL0390BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSBL0390BMsg scrnMsg = (NSBL0390BMsg) bMsg;

        //NSBL0390CMsg bizMsg = new NSBL0390CMsg();
        //bizMsg.setBusinessID("NSBL0390");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NSBL0390BMsg scrnMsg = (NSBL0390BMsg) bMsg;
        //NSBL0390CMsg bizMsg  = (NSBL0390CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
