/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220;

import static business.servlet.NWAL2220.constant.NWAL2220Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NWAL2220Scrn00_OnClick_SrcRefNum extends S21CommonHandler {

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

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;

        int index = getButtonSelectNumber();
        setValue(scrnMsg.ordSrcRefNum_P, scrnMsg.A.no(index).ordSrcRefNum_A);
        setValue(scrnMsg.dsImptOrdPk_P, scrnMsg.A.no(index).dsImptOrdPk_A);

        Object[] params = new Object[NWAL2220_PRM_LENGTH];
        params[0] = scrnMsg.ordSrcRefNum_P;
        params[1] = scrnMsg.dsImptOrdPk_P;
        setArgForSubScreen(params);
    }
}
