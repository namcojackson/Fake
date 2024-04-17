/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL2030.constant.NLAL2030Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLAL2030_NLBL3000
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 *</pre>
 */
public class NLAL2030_NLBL3000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;
        int index = getButtonSelectNumber();

        if (NLAL2030Constant.TAB_ID_ORD.equals(scrnMsg.xxDplyTab.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.A.no(index).serNum_A1);

        } else if (NLAL2030Constant.TAB_ID_RWS.equals(scrnMsg.xxDplyTab.getValue())) {

            scrnMsg.setFocusItem(scrnMsg.B.no(index).serNum_B1);
        }
    }
}
