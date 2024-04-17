/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3000.NFCL3000CMsg;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Fujitsu         T.Tanaka        Create          N/A
 * 2016/10/05   Fujitsu         S.Fujita        Update          QC#15013
 *</pre>
 */
public class NFCL3000Scrn00_Select_AccountingClass extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
        int no = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(no);
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

        // START 2016/10/05 S.Fujita [QC#15013,ADD]
        NFCL3000CommonLogic.addCheckItem_AcctDist(scrnMsg, false);
        scrnMsg.putErrorScreen();
        // END   2016/10/05 S.Fujita [QC#15013,ADD]
    }
}