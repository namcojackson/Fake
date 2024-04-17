/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1090;

import static business.servlet.NPAL1090.constant.NPAL1090Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1090.NPAL1090CMsg;
import business.servlet.NPAL1090.common.NPAL1090CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1090 Tech Parts Request List
 * Function Name : Button Action to delete Search option.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPAL1090Scrn00_DeleteSearchOption extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.srchOptPk_SE);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;

        NPAL1090CMsg bizMsg = new NPAL1090CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        NPAL1090CMsg bizMsg = (NPAL1090CMsg) cMsg;

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
