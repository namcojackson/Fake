/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL0730.NFCL0730CMsg;
//import business.servlet.NFCL0730.constant.NFCL0730Constant;

import business.blap.NFCL0730.NFCL0730CMsg;
import business.servlet.NFCL0730.common.NFCL0730CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NFCL0730Scrn00_DeleteLines extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;

        NFCL0730CMsg bizMsg = new NFCL0730CMsg();
        bizMsg.setBusinessID("NFCL0730");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;
        NFCL0730CMsg bizMsg  = (NFCL0730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        //if(scrnMsg.A.getValidCount() > 0) {
        if (NFCL0730CommonLogic.getAddedCount(scrnMsg) > 0) {
            
            this.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
            this.setButtonProperties("DeleteLines", "DeleteLines", "Delete", 1, null);
        } else {
            this.setButtonProperties("btn2", "CMN_Submit", "Submit", 0, null);
            this.setButtonProperties("DeleteLines", "DeleteLines", "Delete", 0, null);
        }

    }
}
