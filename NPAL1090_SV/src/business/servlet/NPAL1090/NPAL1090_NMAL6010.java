/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1090;

import static business.servlet.NPAL1090.constant.NPAL1090Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * 03/08/2016: This class does not used. NMAL6010 => NMAL6760
 * 
 * Business ID : NPAL1090 Tech Parts Request List
 * Function Name : Return Action from NMAL6010(Ship To Customer Popup)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/09/2015   CITS       Yasushi Nomura       Create          N/A
 * 03/08/2016   CITS       Takeshi Tokutomi     Update          QC#4275
 *</pre>
 */
public class NPAL1090_NMAL6010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1090BMsg scrnMsg = (NPAL1090BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_H1, scrnMsg.shipToCustCd_P3);
            ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H1, scrnMsg.shipToLocNm_P3);
            scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd_SE);
        }
    }
}
