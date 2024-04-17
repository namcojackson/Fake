/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
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
 * 2013/05/20   Hitachi         T.Aoyagi        Update          QC1224
 *</pre>
 */
public class NPAL1090Scrn00_SearchLocName extends S21CommonHandler  {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;
//
//        if (!ZYPCommonFunc.hasValue(scrnMsg.toInvtyLocCd_H1)) {
//            scrnMsg.toInvtyLocCd_H1.setErrorInfo(1, NPAM1180E, new String[] {scrnMsg.toInvtyLocCd_H1.getNameForMessage() });
//        }
//        scrnMsg.addCheckItem(scrnMsg.toInvtyLocCd_H1);
//        scrnMsg.putErrorScreen();
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
        // scrnMsg.addCheckItem(scrnMsg.toInvtyLocCd_H1);
        // scrnMsg.putErrorScreen();
        //
        // // Set focus.
        // // START 2013/05/20 T.Aoyagi [QC1224,MOD]
        // // scrnMsg.setFocusItem(scrnMsg.svcCallJobNum_H1);
        // scrnMsg.setFocusItem(scrnMsg.toInvtyLocCd_H1);
        // // END 2013/05/20 T.Aoyagi [QC1224,MOD]
    }

}
