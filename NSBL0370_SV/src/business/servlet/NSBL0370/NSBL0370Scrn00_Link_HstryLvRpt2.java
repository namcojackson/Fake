/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSBL0370;

import static business.servlet.NSBL0370.constant.NSBL0370Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Hourly Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Hitachi         T.Mizuki        Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5735
 *</pre>
 */
public class NSBL0370Scrn00_Link_HstryLvRpt2 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0370BMsg scrnMsg = (NSBL0370BMsg) bMsg;
        int index = getButtonSelectNumber();
        setArgForSubScreen(setConfigSearchPopUpInputParam(ctx, scrnMsg, index));
        // START 2016/03/22 K.Yamada [QC#5735, ADD]
        openMultiModeScreen(TAB_NAME_NSBL0380);
        // END 2016/03/22 K.Yamada [QC#5735, ADD]

    }

    private Object[] setConfigSearchPopUpInputParam(EZDApplicationContext ctx, NSBL0370BMsg scrnMsg, int index) {

        Object[] params = new Object[2];

        params[0] = scrnMsg.B.no(index).orgCd_B;
        params[1] = scrnMsg.cratDt_H;

        return params;
    }
}
