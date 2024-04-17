/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 *</pre>
 */
public class NLBL3070Scrn00_Open_DelyInstn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;

        int index = getButtonSelectNumber();

        if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(index).soNum_A1);

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.B.no(index).soNum_B1);
        }

        Object[] params = new Object[1];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
