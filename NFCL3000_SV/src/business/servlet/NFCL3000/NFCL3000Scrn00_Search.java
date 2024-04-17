/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;
import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2019/04/25   Fujitsu         S.Takami        Update          QC#50078
 *</pre>
 */
public class NFCL3000Scrn00_Search extends S21CommonHandler implements NFCL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.invNum_H1);
        scrnMsg.putErrorScreen();
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

        if(scrnMsg.getMessageType()==EZDMessageInfo.MSGTYPE_ERROR) {
            scrnMsg.setFocusItem(scrnMsg.invNum_H1);
            return;
        }

        NFCL3000CommonLogic.initialize_Update(this, scrnMsg, false);
        if(scrnMsg.xxDplyTab.getValue().equals(TAB_Accounting)) {
            NFCL3000CommonLogic.setProtectAcctInput(this, scrnMsg);
            NFCL3000CommonLogic.addCheckItem_AcctDist(scrnMsg, false);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.setFocusItem(scrnMsg.custIssPoNum_H1);
        }

        // START 2019/04/25 S.Takami [QC#50078,ADD]
        NFCL3000CommonLogic.setQtyIndispensable(scrnMsg);
        // END 2019/04/25 S.Takami [QC#50078,ADD]
    }
}
