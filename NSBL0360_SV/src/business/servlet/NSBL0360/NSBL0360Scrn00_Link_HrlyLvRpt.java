/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0360;

import static business.servlet.NSBL0360.constant.NSBL0360Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Sub Group Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 *</pre>
 */
public class NSBL0360Scrn00_Link_HrlyLvRpt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0360BMsg scrnMsg = (NSBL0360BMsg) bMsg;
        int index = getButtonSelectNumber();
        setArgForSubScreen(setConfigSearchPopUpInputParam(ctx, scrnMsg, index));
        // START 2016/03/22 K.Yamada [QC#5735, ADD]
        openMultiModeScreen(TAB_NAME_NSBL0370);
        // END 2016/03/22 K.Yamada [QC#5735, ADD]

    }

    private Object[] setConfigSearchPopUpInputParam(EZDApplicationContext ctx, NSBL0360BMsg scrnMsg, int index) {

        Object[] params = new Object[2];

        params[0] = scrnMsg.A.no(index).orgCd_A;
        params[1] = scrnMsg.cratDt_H;

        return params;

    }
}
