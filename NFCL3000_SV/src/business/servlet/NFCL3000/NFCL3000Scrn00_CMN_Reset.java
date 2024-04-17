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
import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Fujitsu         T.Tanaka        Create          N/A
 * 2016/08/01   Fujitsu         S.Fujita        Update          QC#10148
 * 2016/08/22   Fujitsu         S.Fujita        Update          QC#12579
 *</pre>
 */
public class NFCL3000Scrn00_CMN_Reset extends S21CommonHandler implements NFCL3000Constant {

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

        NFCL3000CommonLogic.initialize(this, scrnMsg, false);

        // START 2016/08/22 S.Fujita [QC#12579,ADD]
        NFCL3000CommonLogic.setProtectAcctInput(this, scrnMsg);
        if (!NFCL3000CommonLogic.setAcctDistError(scrnMsg)) {
            NFCL3000CommonLogic.addCheckItem_AcctDist(scrnMsg, false);
            scrnMsg.setMessageInfo("NFCM0576E", new String[]{"Accounting Information"});
        }
        // END   2016/08/22 S.Fujita [QC#12579,ADD]

        // START 2016/08/01 S.Fujita [QC#10148,ADD]
        // Clear Sort Icon
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END   2016/08/01 S.Fujita [QC#10148,ADD]
    }
}
