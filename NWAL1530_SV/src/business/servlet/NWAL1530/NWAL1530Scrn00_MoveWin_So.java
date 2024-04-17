/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1530;

import static business.servlet.NWAL1530.constant.NWAL1530Constant.TAB_SHIPPING_DETAIL;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1530Scrn00_MoveWin_So
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/23   Fujitsu         M.suzuki        Update          S21_NA#1975
 * 2016/04/13   Fujitsu         S.Takami        Update          S21_NA#5809
 *</pre>
 */
public class NWAL1530Scrn00_MoveWin_So extends S21CommonHandler {

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
            scrnMsg.P.no(0).xxPopPrm_P.setValue(abMsg.cpoOrdNum_A.getValue());
            scrnMsg.P.no(3).xxPopPrm_P.setValue(abMsg.soNum_A.getValue());

        } else {

            NWAL1530_BBMsg bbMsg = scrnMsg.B.no(getButtonSelectNumber());
            scrnMsg.P.no(0).xxPopPrm_P.setValue(bbMsg.cpoOrdNum_B.getValue());
            scrnMsg.P.no(3).xxPopPrm_P.setValue(bbMsg.soNum_B.getValue());
        }

        // Set Params
        Object[] params = new Object[10];
        params[0] = scrnMsg.P.no(0).xxPopPrm_P;
        params[1] = scrnMsg.P.no(1).xxPopPrm_P;
        // 2016/04/13 S21_NA#5809 Mod Start
//        params[2] = scrnMsg.P.no(2).xxPopPrm_P;
        params[2] = scrnMsg.P.no(2).xxNum_PP;
        // 2016/04/13 S21_NA#5809 Mod Start
        params[3] = scrnMsg.P.no(3).xxPopPrm_P;
        params[4] = scrnMsg.P.no(4).xxPopPrm_P;
        params[5] = scrnMsg.P.no(5).xxPopPrm_P;
        params[6] = scrnMsg.P.no(6).xxPopPrm_P;
        params[7] = scrnMsg.P.no(7).xxPopPrm_P;
        params[8] = scrnMsg.P.no(8).xxPopPrm_P;
        params[9] = scrnMsg.P.no(9).xxPopPrm_P;

        setArgForSubScreen(params);
    }
}
