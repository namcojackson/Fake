/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.HEADER_VALUE;
import static business.servlet.NMAL3000.constant.NMAL3000Constant.PARAM_SIZE_15;

/**
 *<pre>
 * NMAL3000Scrn00_OpenWin_SearchAccount
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL3000Scrn00_OpenWin_SearchAccount extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL3000BMsg scrnMsg = (NMAL3000BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);

        //Set Params
        Object[] params = new Object[PARAM_SIZE_15];
        int index = getButtonSelectNumber();
        if (index == HEADER_VALUE) {
            params[0]  = scrnMsg.dsAcctCustNum;
        } else {
            params[0]  = scrnMsg.A.no(index).dsAcctCustNum_A;
        }

        params[1]  = scrnMsg.P.no(1).xxPopPrm;
        params[2]  = scrnMsg.P.no(2).xxPopPrm;
        params[3]  = scrnMsg.P.no(3).xxPopPrm;
        params[4]  = scrnMsg.P.no(4).xxPopPrm;
        params[5]  = scrnMsg.P.no(5).xxPopPrm;
        params[6]  = scrnMsg.P.no(6).xxPopPrm;
        params[7]  = scrnMsg.P.no(7).xxPopPrm;
        params[8]  = scrnMsg.P.no(8).xxPopPrm;
        params[9]  = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm, "01");
        params[12] = scrnMsg.P.no(12).xxPopPrm;
        params[13] = scrnMsg.P.no(13).xxPopPrm;
        params[14] = scrnMsg.P.no(14).xxPopPrm;
        setArgForSubScreen(params);
    }
}
