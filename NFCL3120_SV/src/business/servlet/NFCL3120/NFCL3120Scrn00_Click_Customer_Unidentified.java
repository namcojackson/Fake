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

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL3120Scrn00_Click_Customer_Unidentified extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

        //NFCL3120CMsg bizMsg = new NFCL3120CMsg();
        //bizMsg.setBusinessID("NFCL3120");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        if(ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H1.getValue())) {
//            scrnMsg.dsAcctNum_H1.setIndispensable(false);
//            scrnMsg.dsAcctNum_H1.clear();
//            scrnMsg.dsAcctNm_H1.clear();
//            scrnMsg.dsAcctNum_H1.setInputProtected(true);
//            this.setButtonProperties("Click_SetCustomerName", "Click_SetCustomerName", ">>", 0, null);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.dsAcctNum_L1.setInputProtected(true);
        } else {
//            scrnMsg.dsAcctNum_H1.clear();
//            scrnMsg.dsAcctNm_H1.clear();
//            scrnMsg.dsAcctNum_H1.setInputProtected(false);
//            scrnMsg.dsAcctNum_H1.setIndispensable(true);
//            this.setButtonProperties("Click_SetCustomerName", "Click_SetCustomerName", ">>", 1, null);
            scrnMsg.dsAcctNm_H1.setInputProtected(false);
            scrnMsg.dsAcctNum_L1.setInputProtected(false);
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        //NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;
        //NFCL3120CMsg bizMsg  = (NFCL3120CMsg) cMsg;

        //EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}