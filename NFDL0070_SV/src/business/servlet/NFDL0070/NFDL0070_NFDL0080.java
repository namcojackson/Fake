/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFDL0070.NFDL0070CMsg;
//import business.servlet.NFDL0070.constant.NFDL0070Constant;

import business.blap.NFDL0070.NFDL0070CMsg;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 * 2018/07/06   Hitachi         Y.Takeno        Update          QC#26989
 *</pre>
 */
public class NFDL0070_NFDL0080 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;

        NFDL0070CMsg bizMsg = new NFDL0070CMsg();
        bizMsg.setBusinessID("NFDL0070");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0070BMsg scrnMsg = (NFDL0070BMsg) bMsg;
        NFDL0070CMsg bizMsg  = (NFDL0070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2018/07/06 [QC#26989, ADD]
        S21SortColumnIMGController.clearIMG("NFDL0070Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END   2018/07/06 [QC#26989, ADD]
    }
}
