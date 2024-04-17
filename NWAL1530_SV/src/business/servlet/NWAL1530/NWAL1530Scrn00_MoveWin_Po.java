/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1530;

import static business.servlet.NWAL1530.constant.NWAL1530Constant.TAB_SHIPPING_DETAIL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1530Scrn00_MoveWin_Po
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/19   Fujitsu         M.Suzuki        Update          S21_NA#3554
 *</pre>
 */
public class NWAL1530Scrn00_MoveWin_Po extends S21CommonHandler {

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

        NWAL1530BMsg scrnMsg = (NWAL1530BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        if (TAB_SHIPPING_DETAIL.equals(scrnMsg.xxDplyTab.getValue())) {

            NWAL1530_ABMsg abMsg = scrnMsg.A.no(getButtonSelectNumber());
            scrnMsg.P.no(0).xxPopPrm_P.setValue(abMsg.poOrdNum_A.getValue());

        } else {

            NWAL1530_CBMsg cbMsg = scrnMsg.C.no(getButtonSelectNumber());
            scrnMsg.P.no(0).xxPopPrm_P.setValue(cbMsg.poOrdNum_C.getValue());
        }

        // Set Params
        Object[] params = new Object[1];
        params[0] = scrnMsg.P.no(0).xxPopPrm_P;

        setArgForSubScreen(params);
    }
}
