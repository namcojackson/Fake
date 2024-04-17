/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1420;

import static business.servlet.NPAL1420.constant.NPAL1420Constant.BUSINESS_APPLICATION_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1420.NPAL1420CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1420Scrn00_Search_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;

        NPAL1420CMsg bizMsg = new NPAL1420CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        bizMsg.xxNum_H.setValue(getButtonSelectNumber());
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;
        NPAL1420CMsg bizMsg = (NPAL1420CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.A.no(getButtonSelectNumber()).mdseCd_A1);
        scrnMsg.putErrorScreen();
    }
}
