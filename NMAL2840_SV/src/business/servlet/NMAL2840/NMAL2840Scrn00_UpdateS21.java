/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2840;

import static business.servlet.NMAL2840.constant.NMAL2840Constant.DUNS_RVW_INFO_WRK;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   Fujitsu         R.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2840Scrn00_UpdateS21 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2840BMsg scrnMsg = (NMAL2840BMsg) bMsg;

        Object[] params = new Object[1];
        scrnMsg.xxPopPrm.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, DUNS_RVW_INFO_WRK);

        params[0] = scrnMsg.xxPopPrm;
        setArgForSubScreen(params);
    }
}
