/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NPAL1090 Service Parts Request Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/11/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NPAL1090Scrn00_SearchMdseName extends S21CommonHandler {


    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1)) {
//            scrnMsg.mdseCd_H1.setErrorInfo(1, NPAM1179E, new String[] {scrnMsg.mdseCd_H1.getNameForMessage() });
//        }
//        NPAL1090CommonLogic.checkInput_item(scrnMsg, scrnMsg.mdseCd_H1);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
//
//        NPAL1090CMsg bizMsg = new NPAL1090CMsg();
//        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
//        bizMsg.setFunctionCode("20");
//        EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        return bizMsg;
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        //
        // NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
        // NPAL1090CMsg bizMsg = (NPAL1090CMsg) cMsg;
        //
        // EZDMsg.copy(bizMsg, null, scrnMsg, null);
        //
        // NPAL1090CommonLogic.checkInput_item(scrnMsg,
        // scrnMsg.mdseCd_H1);
        //
        // // Set focus.
        // scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);

    }
}
