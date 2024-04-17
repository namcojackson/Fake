/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1260;

import static business.servlet.NPAL1260.constant.NPAL1260Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1260.constant.NPAL1260Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1260.NPAL1260CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1260 Tech Parts Request Inquiry
 * Function Name : Return Action from NMAL6760(Account Search Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/08/2016   CITS       Takeshi Tokutomi       Create          N/A
 *</pre>
 */
public class NPAL1260_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;

        NPAL1260CMsg bizMsg = null;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            // scrnMsg.O.no(1).xxPopPrm_O1 =  scrnMsg.shipToLocNm_P1
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.O.no(1).xxPopPrm_O1);
            
           // scrnMsg.O.no(16).xxPopPrm_O1 =  scrnMsg.shipToCustCd_P1
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_P1, scrnMsg.O.no(16).xxPopPrm_O1);

            bizMsg = new NPAL1260CMsg();
            bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
            bizMsg.setFunctionCode("20");
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        NPAL1260CMsg bizMsg = (NPAL1260CMsg) cMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
    }
}
