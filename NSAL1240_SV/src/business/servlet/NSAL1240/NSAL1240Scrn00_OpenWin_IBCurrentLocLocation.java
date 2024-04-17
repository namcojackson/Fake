/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1240;

import static business.servlet.NSAL1240.constant.NSAL1240Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL1240.common.NSAL1240CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Config# Search Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Hitachi         A.Kohinata      Create          N/A
 * 2016/03/29   Hitachi         M.Gotou         Update          QC#4910
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 *</pre>
 */
public class NSAL1240Scrn00_OpenWin_IBCurrentLocLocation extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1240BMsg scrnMsg = (NSAL1240BMsg) bMsg;
        NSAL1240CommonLogic.clearPopupParameter(scrnMsg);
        // START 2016/03/29 M.Gotou [QC#4910, ADD]
        setValue(scrnMsg.xxPopPrm_12, PARAMS_SHIP_TO_ONLY);
        // END 2016/03/29 M.Gotou [QC#4910, ADD]

        Object[] params = new Object[NMAL6760_PRM_LENGTH];
        int i = 0;
        params[i++] = scrnMsg.curLocAcctNum_H;
        params[i++] = scrnMsg.xxPopPrm_01;
        // mod start 2016/05/16 CSA Defect#4578
        params[i++] = scrnMsg.indCurLocNum_H;
        // mod start 2016/05/16 CSA Defect#4578
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        params[i++] = scrnMsg.xxPopPrm_11;
        params[i++] = scrnMsg.xxPopPrm_12;
        params[i++] = scrnMsg.xxPopPrm_13;
        params[i++] = scrnMsg.xxPopPrm_14;
        setArgForSubScreen(params);
    }
}
