/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NWAL1710.constant.NWAL1710Constant.PARAM_SIZE_2;

/**
 *<pre>
 * NWAL1710Scrn00_Select_Reason
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1710Scrn00_Select_Reason extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //donothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        int index = getButtonSelectNumber();
        Object[] params = new Object[PARAM_SIZE_2];
        params[0] = scrnMsg.A.no(index).dsOrdCatgCd_A;
        params[1] = scrnMsg.A.no(index).dsOrdTpCd_A;
        setArgForSubScreen(params);
    }
}
