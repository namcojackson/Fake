/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7270Scrn00_OpenWin_PrcRuleCondVal_Item
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/09/27   Fujitsu         R.Nakamura      Update          QC#6931
 *</pre>
 */
public class NMAL7270Scrn00_OpenWin_PrcRuleCondVal_Item extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7270BMsg scrnMsg = (NMAL7270BMsg) bMsg;

        int idx = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());

        // Clear Params
        ZYPTableUtil.clear(scrnMsg.P);

        // Set Params
        // Mod Start 2016/09/27 QC#6931
//        Object[] params = new Object[7];
        Object[] params = new Object[8];
        // Mod End 2016/09/27 QC#6931
        params[0] = scrnMsg.A.no(idx).prcRuleCondFromTxt_A1;
        params[1] = scrnMsg.A.no(idx).xxRecNmTxt_A1;
        params[2] = scrnMsg.P.no(0).xxPopPrm;
        params[3] = scrnMsg.P.no(1).xxPopPrm;
        params[4] = scrnMsg.P.no(2).xxPopPrm;
        params[5] = scrnMsg.P.no(3).xxPopPrm;
        params[6] = scrnMsg.P.no(4).xxPopPrm;
        // Add Start 2016/09/27 QC#6931
        params[7] = scrnMsg.A.no(idx).xxRecNmTxt_A1;
        // Add End 2016/09/27 QC#6931

        setArgForSubScreen(params);
    }
}
