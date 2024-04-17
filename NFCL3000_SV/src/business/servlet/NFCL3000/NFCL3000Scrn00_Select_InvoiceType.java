/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Tanaka        Create          N/A
 * 2016/05/24   Fujitsu         S.Fujita        Update          QC#8522
 * 2016/06/16   Fujitsu         S.Fujita        Update          QC#10176
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 *</pre>
 */
public class NFCL3000Scrn00_Select_InvoiceType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
        bizMsg.setBusinessID("NFCL3000");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2016/05/24 S.Fujita [QC#8522,ADD]
        NFCL3000CommonLogic.setMandatorySalesCreditInput(scrnMsg);
        // END   2016/05/24 S.Fujita [QC#8522,ADD]
        // START 2016/06/16 S.Fujita [QC#10176,ADD]
        if(scrnMsg.invTpCd_H1.getValue().equals(INV_TP.CREDIT_MEMO)) {
            scrnMsg.pmtTermCashDiscCd_H1.setInputProtected(true);
        } else {
            scrnMsg.pmtTermCashDiscCd_H1.setInputProtected(false);
        }
        // END   2016/06/16 S.Fujita [QC#10176,ADD]
        // START 2019/04/25 S.Takami [QC#50078,ADD]
        NFCL3000CommonLogic.setQtyIndispensable(scrnMsg);
        // END 2019/04/25 S.Takami [QC#50078,ADD]
        scrnMsg.setFocusItem(scrnMsg.dsInvTpCd_H1);
    }
}
