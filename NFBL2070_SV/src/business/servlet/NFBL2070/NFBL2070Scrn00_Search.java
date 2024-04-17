/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2070.NFBL2070CMsg;
//import business.servlet.NFBL2070.constant.NFBL2070Constant;

import business.blap.NFBL2070.NFBL2070CMsg;
import business.servlet.NFBL2070.common.NFBL2070CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Compensation Credit I/F Error Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   CUSA            Y.Miyauchi      Create          New
 * 2016/11/28   Fujitsu         T.Murai         Update          QC#16158
 *</pre>
 */
public class NFBL2070Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/11/28 [QC#16158, ADD]
        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;
        NFBL2070CommonLogic.itemCheckForSearch(scrnMsg);
        scrnMsg.putErrorScreen();
        // END 2016/11/28 [QC#16158, ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;

        NFBL2070CMsg bizMsg = new NFBL2070CMsg();
        bizMsg.setBusinessID("NFBL2070");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2070BMsg scrnMsg = (NFBL2070BMsg) bMsg;
        NFBL2070CMsg bizMsg  = (NFBL2070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFBL2070CommonLogic.openSelectedRow(scrnMsg);
    }
}
