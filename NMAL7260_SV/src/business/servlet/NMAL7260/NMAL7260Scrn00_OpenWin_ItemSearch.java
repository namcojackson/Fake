/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_OpenWin_ItemSearch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 *</pre>
 */
public class NMAL7260Scrn00_OpenWin_ItemSearch extends S21CommonHandler {

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

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());

        // Clear Params
        ZYPTableUtil.clear(scrnMsg.P);

        // Set Params
        Object[] params = new Object[13];
        params[0] = scrnMsg.B.no(idx).prcRuleCondFromTxt_10;
        params[1] = scrnMsg.B.no(idx).mdseDescShortTxt_10;
        params[2] = scrnMsg.P.no(0).xxPopPrm;
        params[3] = scrnMsg.P.no(1).xxPopPrm;
        params[4] = scrnMsg.P.no(2).xxPopPrm;
        params[5] = scrnMsg.P.no(3).xxPopPrm;
        params[6] = scrnMsg.P.no(4).xxPopPrm;
        params[7] = scrnMsg.B.no(idx).mdseDescShortTxt_10;
        params[8] = scrnMsg.P.no(6).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, "08");
        params[9] = scrnMsg.P.no(7).xxPopPrm;
        params[10] = scrnMsg.P.no(8).xxPopPrm;
        params[11] = scrnMsg.P.no(9).xxPopPrm;
        params[12] = scrnMsg.B.no(idx).mnfItemCd_10;

        setArgForSubScreen(params);
    }
}
