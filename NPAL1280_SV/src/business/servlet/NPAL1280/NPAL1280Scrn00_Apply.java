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
import business.servlet.NPAL1280.common.NPAL1280CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/09   CITS            K.Ogino         Create          QC#21169
 *</pre>
 */
public class NPAL1280Scrn00_Apply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no process.
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

        NPAL1280CommonLogic.retCheckItems(scrnMsg);
        scrnMsg.putErrorScreen();

    }
}
