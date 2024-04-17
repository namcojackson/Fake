/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL5140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5140.NFCL5140CMsg;
import business.servlet.NFCL5140.common.NFCL5140CommonLogic;
//import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL5140Scrn00_OpenWin_ChargeAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;
   
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;

        NFCL5140CMsg bizMsg = new NFCL5140CMsg();
        bizMsg.setBusinessID("NFCL5140");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    	
    	 NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;
         NFCL5140CMsg bizMsg  = (NFCL5140CMsg) cMsg;

         EZDMsg.copy(bizMsg, null, scrnMsg, null);

         Object[] params = NFCL5140CommonLogic.getParamForChargeAccount(scrnMsg);
         scrnMsg.addCheckItem(scrnMsg.xxCmntTxt);
         scrnMsg.putErrorScreen();

         setArgForSubScreen(params);
    }
}