/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3070.NFCL3070CMsg;
//import business.servlet.NFCL3070.constant.NFCL3070Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/30   Hitachi         E.Kameishi      Create          QC#26229
 *</pre>
 */
public class NFCL3070_NFCL3000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;

        //NFCL3070CMsg bizMsg = new NFCL3070CMsg();
        //bizMsg.setBusinessID("NFCL3070");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;
        //NFCL3070CMsg bizMsg  = (NFCL3070CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}
