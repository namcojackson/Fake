/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_OpenWin_BillTo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/30   Fujitsu         R.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7260Scrn00_OpenWin_BillTo extends S21CommonHandler {

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
        Object[] params = new Object[15];
        params[0] = scrnMsg.B.no(idx).prcRuleCondFromTxt_16;
        params[1] = scrnMsg.B.no(idx).billToAcctNm_16;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm, PRC_RULE_ATTRB.BILL_TO_ACCT_NUM);
        params[12] = scrnMsg.P.no(12).xxPopPrm;
        params[13] = scrnMsg.P.no(0).xxPopPrm;
        params[14] = scrnMsg.P.no(1).xxPopPrm;

        setArgForSubScreen(params);
    }
}
