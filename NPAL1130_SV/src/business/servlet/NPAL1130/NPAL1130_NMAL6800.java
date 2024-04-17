/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1130;

import static business.servlet.NPAL1130.constant.NPAL1130Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1130 Replenishment Plan Inquiry(Detail)
 * Function Name : Call pop up from NPAL1130 to NMAL6800
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/29/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1130_NMAL6800 extends S21CommonHandler {

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

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.P.no(0).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt, scrnMsg.P.no(7).xxPopPrm);
        }

        scrnMsg.setFocusItem(scrnMsg.mdseCd);

    }
}
