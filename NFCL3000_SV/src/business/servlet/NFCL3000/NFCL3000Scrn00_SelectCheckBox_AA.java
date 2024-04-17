/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         T.Tanaka        Create          N/A
 * 2016/07/20   Fujitsu         S.Fujita        Update          QC#10148
 *</pre>
 */
public class NFCL3000Scrn00_SelectCheckBox_AA extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/07/28 S.Fujita [QC#10148,MOD]
//        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
//
//        NFCL3000CMsg bizMsg = new NFCL3000CMsg();
//        bizMsg.setBusinessID("NFCL3000");
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
        // END   2016/07/28 S.Fujita [QC#10148,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2016/07/28 S.Fujita [QC#10148,DEL]
//        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;
//        NFCL3000CMsg bizMsg  = (NFCL3000CMsg) cMsg;
//
//        EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//        NFCL3000CommonLogic.setProtectLineInput(scrnMsg);
        // END   2016/07/28 S.Fujita [QC#10148,DEL]
    }
}
