/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3170;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3170.NFCL3170CMsg;
//import business.servlet.NFCL3170.constant.NFCL3170Constant;

import business.blap.NFCL3170.NFCL3170CMsg;
import business.servlet.NFCL3170.common.NFCL3170CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/12/16   Fujitsu         T.Murai         Update          QC#16533
 *</pre>
 */
public class NFCL3170Scrn00_OnChangeRadio extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

        NFCL3170CMsg bizMsg = new NFCL3170CMsg();
        bizMsg.setBusinessID("NFCL3170");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
        NFCL3170CMsg bizMsg  = (NFCL3170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL3170CommonLogic.setCustomerProtect(this, scrnMsg);
        NFCL3170CommonLogic.setAddressMandatory(scrnMsg); // ADD 2016/12/16 [QC#16533]
/*
        // External
        if(scrnMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
            // Protect
            scrnMsg.dsAcctNum_H1.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.locNum_H1.setInputProtected(true);

            scrnMsg.dsAcctNum_L1.setInputProtected(false);
            scrnMsg.dsAcctNm_L1.setInputProtected(false);
            scrnMsg.locNum_L1.setInputProtected(false);
            
            this.setButtonProperties("Add", "Add", "Add", 1, null);
            if(scrnMsg.A.getValidCount() > 0) {
                this.setButtonProperties("Delete", "Delete", "Delete", 1, null);
            } else {
                this.setButtonProperties("Delete", "Delete", "Delete", 0, null);
            }

        } else {
            // Protect
            scrnMsg.dsAcctNum_H1.setInputProtected(true);
            scrnMsg.dsAcctNm_H1.setInputProtected(true);
            scrnMsg.locNum_H1.setInputProtected(true);
            
            scrnMsg.dsAcctNum_H1.clear();
            scrnMsg.dsAcctNm_H1.clear();
            scrnMsg.locNum_H1.clear();
            
            scrnMsg.dsAcctNum_L1.setInputProtected(true);
            scrnMsg.dsAcctNm_L1.setInputProtected(true);
            scrnMsg.locNum_L1.setInputProtected(true);

            this.setButtonProperties("Add", "Add", "Add", 0, null);
            this.setButtonProperties("Delete", "Delete", "Delete", 0, null);
        }
*/
    }
}
