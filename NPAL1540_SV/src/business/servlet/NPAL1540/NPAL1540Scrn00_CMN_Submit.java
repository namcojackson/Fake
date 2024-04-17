/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1540;

import static business.servlet.NPAL1540.constant.NPAL1540Constant.BIZ_APP_ID;
import static business.servlet.NPAL1540.constant.NPAL1540Constant.FUNCTION_CD_UPDATE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1540.NPAL1540CMsg;
import business.servlet.NPAL1540.common.NPAL1540CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NPAL1540 Manual ASN Entry
 * Function Name : Submit
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/20   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NPAL1540Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.asnSoNum);
        scrnMsg.addCheckItem(scrnMsg.poOrdNum);
        scrnMsg.addCheckItem(scrnMsg.shipFromSoNum);
        scrnMsg.addCheckItem(scrnMsg.carrCd);
        scrnMsg.addCheckItem(scrnMsg.asnBolNum);
        scrnMsg.addCheckItem(scrnMsg.asnProNum);
        scrnMsg.addCheckItem(scrnMsg.shipDt);
        scrnMsg.addCheckItem(scrnMsg.asnPlnDelyDt);
        scrnMsg.addCheckItem(scrnMsg.asnTotShipWt);
        scrnMsg.addCheckItem(scrnMsg.vndInvtyLocCd);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asnMdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asnQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asnTotShipWt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).asnTotFrtAmt_A1);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;

        NPAL1540CMsg bizMsg = new NPAL1540CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1540BMsg scrnMsg = (NPAL1540BMsg) bMsg;
        NPAL1540CMsg bizMsg  = (NPAL1540CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1540CommonLogic.chkErrorInfo(scrnMsg);
        scrnMsg.putErrorScreen();

        NPAL1540CommonLogic.setScrnItemAttr(this, scrnMsg);
        NPAL1540CommonLogic.setTableScreen(scrnMsg);
    }
}
