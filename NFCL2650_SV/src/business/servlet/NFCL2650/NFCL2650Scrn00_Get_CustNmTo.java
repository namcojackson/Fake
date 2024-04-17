/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2650;

import static business.servlet.NFCL2650.constant.NFCL2650Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2650.NFCL2650CMsg;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.NFCM0038E;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/04/19   Fujitsu         C.Naito         Update          QC#7115
 *</pre>
 */
public class NFCL2650Scrn00_Get_CustNmTo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        // [QC#7115] UPDATE start
        if (scrnMsg.dsAcctNum_TO.isClear()) {
            if (scrnMsg.dsAcctNm_T1.isClear()) {
                scrnMsg.dsAcctNum_TO.setErrorInfo(1, NFCM0038E);
            } else {
                scrnMsg.dsAcctNm_T1.clear();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_TO);
        scrnMsg.putErrorScreen();
        // [QC#7115] UPDATE end
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        NFCL2650CMsg bizMsg = new NFCL2650CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        NFCL2650CMsg bizMsg = (NFCL2650CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_T1);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum_TO);
        scrnMsg.putErrorScreen();
    }
}
