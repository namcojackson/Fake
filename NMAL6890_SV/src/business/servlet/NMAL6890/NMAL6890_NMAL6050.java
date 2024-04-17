/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6890;

import static business.servlet.NMAL6890.constant.NMAL6890Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_OPEN_WIN_ALT_OWNR;
import static business.servlet.NMAL6890.constant.NMAL6890Constant.EVENT_NM_NMAL6890SCRN00_OPEN_WIN_OWNR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6890 Warehouse Search
 * Function Name : forward to Person Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/11/2016   CITS            Y.Kuroda        Create          N/A
 *</pre>
 */
public class NMAL6890_NMAL6050 extends S21CommonHandler {

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

        NMAL6890BMsg scrnMsg = (NMAL6890BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (EVENT_NM_NMAL6890SCRN00_OPEN_WIN_OWNR.equals(scrnMsg.xxPopPrm_P1.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_O1, scrnMsg.fullPsnNm_G1);
                scrnMsg.setFocusItem(scrnMsg.fullPsnNm_O1);

            } else if (EVENT_NM_NMAL6890SCRN00_OPEN_WIN_ALT_OWNR.equals(scrnMsg.xxPopPrm_P1.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_O2, scrnMsg.fullPsnNm_G1);
                scrnMsg.setFocusItem(scrnMsg.fullPsnNm_O2);

            } else {
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
            }
        }
    }

}
