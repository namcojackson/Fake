/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.SHIP_TO_POSTAL_CODE;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/01/11   CITS            T.Tokutomi      Create          QC#28709
 *</pre>
 */
public class NPAL1280Scrn00_GetAddress extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        // Init Error Info.
        scrnMsg.clearErrorInfo();

        if(!ZYPCommonFunc.hasValue(scrnMsg.shipToPostCd_HS)){
            scrnMsg.shipToPostCd_HS.setErrorInfo(1, ZZM9000E, new String[] {SHIP_TO_POSTAL_CODE });
        }
        
        scrnMsg.addCheckItem(scrnMsg.shipToPostCd_HS);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        bizMsg.setBusinessID("NPAL1280");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.shipToPostCd_HS.isError()) {
            scrnMsg.addCheckItem(scrnMsg.shipToPostCd_HS);
            scrnMsg.putErrorScreen();
        } else {
            scrnMsg.setFocusItem(scrnMsg.shipToPostCd_HS);
        }
    }
}
