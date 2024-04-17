/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2330;

import static business.servlet.NWAL2330.constant.NWAL2330Constant.NMAL6760_DISPLAY_HIRARCHEY_ACCOUNTS_03;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.NMAL6760_DISPLAY_RELATED_ACCOUNTS_04;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.NMAL6760_SEARCH_MODE_02;
import static business.servlet.NWAL2330.constant.NWAL2330Constant.NMAL6760_STATUS_01;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2330Scrn00_OpenWin_ShipTo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NWAL2330Scrn00_OpenWin_ShipTo extends S21CommonHandler {

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

        NWAL2330BMsg scrnMsg = (NWAL2330BMsg) bMsg;

        // Clear Params
        ZYPTableUtil.clear(scrnMsg.P);

        // Set Params
        Object[] params = new Object[24];
        params[0]  = scrnMsg.shipToCustAcctCd_H1;
        params[1]  = scrnMsg.dsAcctNm_H3;
        params[2]  = scrnMsg.P.no(2).xxPopPrm;
        params[3]  = scrnMsg.P.no(3).xxPopPrm;
        params[4]  = scrnMsg.P.no(4).xxPopPrm;
        params[5]  = scrnMsg.P.no(5).xxPopPrm;
        params[6]  = scrnMsg.P.no(6).xxPopPrm;
        params[7]  = scrnMsg.P.no(7).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, NMAL6760_DISPLAY_RELATED_ACCOUNTS_04);
        params[8]  = scrnMsg.P.no(8).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, NMAL6760_SEARCH_MODE_02);
        params[9]  = scrnMsg.P.no(9).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_OFF_N);
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm, NMAL6760_STATUS_01);
        params[11] = scrnMsg.P.no(11).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm, NMAL6760_DISPLAY_HIRARCHEY_ACCOUNTS_03);
        params[12] = scrnMsg.P.no(12).xxPopPrm;
        params[13] = scrnMsg.P.no(13).xxPopPrm;
        params[14] = scrnMsg.P.no(14).xxPopPrm;
        params[15] = scrnMsg.P.no(15).xxPopPrm;
        params[16] = scrnMsg.P.no(16).xxPopPrm;
        params[17] = scrnMsg.P.no(17).xxPopPrm;
        params[18] = scrnMsg.P.no(18).xxPopPrm;
        params[19] = scrnMsg.P.no(19).xxPopPrm;
        params[20] = scrnMsg.P.no(20).xxPopPrm;
        params[21] = scrnMsg.P.no(21).xxPopPrm;
        params[22] = scrnMsg.P.no(22).xxPopPrm;
        params[23] = scrnMsg.P.no(23).xxPopPrm;

        setArgForSubScreen(params);
    }
}
