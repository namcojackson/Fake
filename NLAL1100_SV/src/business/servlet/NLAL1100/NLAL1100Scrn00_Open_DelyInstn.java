package business.servlet.NLAL1100;

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/19/2018   CITS            K.Ogino         Create          QC#23044
 *</pre>
 */
public class NLAL1100Scrn00_Open_DelyInstn extends S21CommonHandler {

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

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;

        int index = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(index).rwsNum_A1);

        Object[] params = new Object[1];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}
