/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3010.NFCL3010CMsg;
//import business.servlet.NFCL3010.constant.NFCL3010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/11   Fujitsu         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL3010Scrn00_Click_New extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;

        //NFCL3010CMsg bizMsg = new NFCL3010CMsg();
        //bizMsg.setBusinessID("NFCL3010");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        //NFCL3010CMsg bizMsg  = (NFCL3010CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = new Object[0];
        setArgForSubScreen(params);
    }
}
