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
 *</pre>
 */
public class NLBL3070Scrn00_Open_MngBo extends S21CommonHandler {

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

            // Create Parameter
            int i = 0;
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.A.no(index).trxHdrNum_A1);
            scrnMsg.P.no(i++).xxPopPrm.clear();
            scrnMsg.P.no(i++).xxPopPrm.clear();
            scrnMsg.P.no(i++).xxPopPrm.clear();
            scrnMsg.P.no(i++).xxPopPrm.clear();
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "D");

            scrnMsg.P.setValidCount(i);
            Object[] params = new Object[6];

            for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {

                params[j] = scrnMsg.P.no(j).xxPopPrm;
            }

            params[3] = scrnMsg.A.no(index).svcConfigMstrPk_A1;

            setArgForSubScreen(params);
            openMultiModeScreen();
        }
    }
}
