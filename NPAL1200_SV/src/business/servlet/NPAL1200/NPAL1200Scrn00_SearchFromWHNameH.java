/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1200;

import static business.servlet.NPAL1200.constant.NPAL1200Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.SCREEN_ID;
import static business.servlet.NPAL1200.constant.NPAL1200Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1200.NPAL1200CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
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
public class NPAL1200Scrn00_SearchFromWHNameH extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_HF)) {
            scrnMsg.rtlWhCd_HF.setErrorInfo(1, ZZM9000E, new String[] {"From Warehouse" });
        }
        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_HF);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;

        NPAL1200CMsg bizMsg = new NPAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1200BMsg scrnMsg = (NPAL1200BMsg) bMsg;
        NPAL1200CMsg bizMsg = (NPAL1200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        if (scrnMsg.rtlWhCd_HF.getErrorCode() != 0) {
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd_HF);
            scrnMsg.putErrorScreen();
        }

        // set focus
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_HF);
    }
}
