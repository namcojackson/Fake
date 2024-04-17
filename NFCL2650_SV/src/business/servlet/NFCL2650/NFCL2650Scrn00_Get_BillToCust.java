/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2650;

import static business.servlet.NFCL2650.constant.NFCL2650Constant.BIZ_ID;
import static business.servlet.NFCL2650.constant.NFCL2650Constant.NFCM0038E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL2650.NFCL2650CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Ohno          Create          N/A
 * 2016/04/19   Fujitsu         C.Naito         Update          QC#7115
 * 2016/11/25   Fujitsu         T.Murai         Update          QC#13259
 *</pre>
 */
public class NFCL2650Scrn00_Get_BillToCust extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL2650BMsg scrnMsg = (NFCL2650BMsg) bMsg;
        // START 2016/11/25 T.Murai [QC#13259, MOD]
//        // [QC#7115] UPDATE start
//        if (scrnMsg.billToCustCd.isClear()) {
//            if (scrnMsg.dsAcctNm_BL.isClear()) {
//                scrnMsg.billToCustCd.setErrorInfo(1, NFCM0038E);
//            } else {
//                scrnMsg.dsAcctNm_BL.clear();
//            }
//        }
//        scrnMsg.addCheckItem(scrnMsg.billToCustCd);
//        scrnMsg.putErrorScreen();
        // [QC#7115] UPDATE end
        if (scrnMsg.locNum.isClear()) {
            if (scrnMsg.locNm.isClear()) {
                scrnMsg.locNum.setErrorInfo(1, NFCM0038E);
            } else {
                scrnMsg.locNm.clear();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.locNum);
        scrnMsg.putErrorScreen();
        // END   2016/11/25 T.Murai [QC#13259, MOD]
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
        // START 2016/11/25 T.Murai [QC#13259, MOD]
//        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_BL);
//        scrnMsg.addCheckItem(scrnMsg.billToCustCd);
        scrnMsg.setFocusItem(scrnMsg.locNum);
        scrnMsg.addCheckItem(scrnMsg.locNum);
        // START 2016/11/25 T.Murai [QC#13259, MOD]
        scrnMsg.putErrorScreen();

    }
}
