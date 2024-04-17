/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1200;

import static business.servlet.NPAL1200.constant.NPAL1200Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.MODE_DELETE;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.MODE_NORMAL;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.NPAM0049E;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1200.NPAL1200CMsg;
import business.servlet.NPAL1200.common.NPAL1200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 *</pre>
 */
public class NPAL1200Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;

        if (scrnMsg.xxNum_MD.getValueInt() != MODE_DELETE) {
            if (scrnMsg.A.getValidCount() <= 0) {
                scrnMsg.setMessageInfo(NPAM0049E);
            }
        }
        NPAL1200CommonLogic.addCheckItem(scrnMsg);
        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;

        NPAL1200CMsg bizMsg = new NPAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
        NPAL1200CMsg bizMsg = (NPAL1200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NPAL1200CommonLogic.control(this, scrnMsg);
        NPAL1200CommonLogic.setTableScreen(scrnMsg);
        if (scrnMsg.xxNum_EF.getValueInt() == MODE_NORMAL) {
            scrnMsg.xxNum_MD.setValue(MODE_NORMAL);
        } else {
            NPAL1200CommonLogic.addCheckItem(scrnMsg);
            scrnMsg.putErrorScreen();
        }
        // set focus
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_DF);
    }
}
