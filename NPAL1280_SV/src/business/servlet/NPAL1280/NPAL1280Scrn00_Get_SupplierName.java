/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/04/2019   CITS            R.Shimamoto     Create          QC#53300
 *</pre>
 */
public class NPAL1280Scrn00_Get_SupplierName extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        // Add check Item
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

    	NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

    	NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
    	NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.prntVndCd.isError()) {
            scrnMsg.addCheckItem(scrnMsg.prntVndCd);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.setFocusItem(scrnMsg.prntVndCd);
        }
    }
}
