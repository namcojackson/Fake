/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3120.NFCL3120CMsg;
//import business.servlet.NFCL3120.constant.NFCL3120Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL3120Scrn00_Click_Link_NFCL3170 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

        //NFCL3120CMsg bizMsg = new NFCL3120CMsg();
        //bizMsg.setBusinessID("NFCL3120");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;
         int selectIdx = getButtonSelectNumber();

         Object[] params = new Object[1];
         params[0] = scrnMsg.A.no(selectIdx).dsBankAcctPk_A1;
         setArgForSubScreen(params);
    }
}
