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
 * 03/08/2016: This class does not used. NMAL6010 => NMAL6760
 * 
 * Business ID : NPAL1260 Tech Parts Request Inquiry
 * Function Name : Return Action from NMAL6010(Ship To Customer Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS       Yasushi Nomura       Create          N/A
 * 03/08/2016   CITS       Takeshi Tokutomi     Update          QC#4275
 *</pre>
 */
public class NPAL1260_NMAL6010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;

        NPAL1260CMsg bizMsg = null;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNm_H1, scrnMsg.shipToLocNm_P1);

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
