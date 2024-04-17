/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1400;

import static business.servlet.NPAL1400.constant.NPAL1400Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1400 Reman Inquiry
 * Function Name : Open Return to Tec Search Popup(NWAL1130)
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/08/2016   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NPAL1400_NWAL1130 extends S21CommonHandler {

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

        NPAL1400BMsg scrnMsg = (NPAL1400BMsg) bMsg;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.techTocCd, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm, scrnMsg.R.no(1).xxComnScrColValTxt);

            // Set Focus
            scrnMsg.setFocusItem(scrnMsg.techTocCd);
        }
    }
}