/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3080.constant.NLBL3080Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   CITS            H.Sugawara      Create          N/A
 *</pre>
 */
public class NLBL3080_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3080BMsg scrnMsg = (NLBL3080BMsg) bMsg;

        if (!NLBL3080Constant.EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, scrnMsg.P.no(0).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseDescShortTxt, scrnMsg.P.no(1).xxPopPrm);
            scrnMsg.setFocusItem(scrnMsg.mdseCd);
        }
    }
}
