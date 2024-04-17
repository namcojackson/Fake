/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * Function Name : Return Action from NMAL6760(Account Search Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 *  3/8/2016   CITS       Takeshi Tokutomi       Create          N/A
 *</pre>
 */
public class NPAL1080_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        NPAL1080CMsg bizMsg = null;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            // scrnMsg.O.no(16).xxPopPrm_O1 = scrnMsg.shipToCustCd_P3
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_H1, scrnMsg.O.no(16).xxPopPrm_O1);
            // scrnMsg.O.no(1).xxPopPrm_O1 = scrnMsg.shipToLocNm_P3
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.O.no(1).xxPopPrm_O1);

            bizMsg = new NPAL1080CMsg();
            bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
            NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;

            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
    }
}
