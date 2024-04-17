/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1260;

import static business.servlet.NPAL1260.constant.NPAL1260Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1260.NPAL1260CMsg;
import business.servlet.NPAL1260.common.NPAL1260CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1260 Tech Parts Request Inquiry
 * Function Name : Pulldown on change action to search
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPAL1260Scrn00_OnChange_SearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;

        NPAL1260CMsg bizMsg = new NPAL1260CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        NPAL1260CMsg bizMsg = (NPAL1260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.srchOptNm_TX.isError()) {
            scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);
            scrnMsg.putErrorScreen();
        } else {
            // Set Focus
            scrnMsg.setFocusItem(scrnMsg.srchOptNm_TX);
        }
    }
}
