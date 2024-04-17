/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

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
 * NMAL7270Scrn00_OpenWin_PrcRuleCondVal_Acct
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7270Scrn00_OpenWin_PrcRuleCondVal_Acct extends S21CommonHandler {

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
        Object[] params = new Object[15];
        params[0]  = scrnMsg.A.no(idx).prcRuleCondFromTxt_A1;
        params[1]  = scrnMsg.A.no(idx).xxRecNmTxt_A1;
        params[2]  = scrnMsg.P.no(5).xxPopPrm;
        params[3]  = scrnMsg.P.no(5).xxPopPrm;
        params[4]  = scrnMsg.P.no(5).xxPopPrm;
        params[5]  = scrnMsg.P.no(5).xxPopPrm;
        params[6]  = scrnMsg.P.no(5).xxPopPrm;
        params[7]  = scrnMsg.P.no(5).xxPopPrm;
        params[8]  = scrnMsg.P.no(5).xxPopPrm;
        params[9]  = scrnMsg.P.no(5).xxPopPrm;
        params[10] = scrnMsg.P.no(5).xxPopPrm;
        params[11] = scrnMsg.P.no(5).xxPopPrm;
        if (PRC_RULE_ATTRB.ACCNT_NUM_SHIP_TO.equals(scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue())) {
            // 02 not Code Table.
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, "03");
        } else if (PRC_RULE_ATTRB.BILL_TO_ACCT_NUM.equals(scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue())) {
            // 03 not Code Table.
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, "02");
        }
        params[12] = scrnMsg.P.no(4).xxPopPrm;
        params[13] = scrnMsg.P.no(0).xxPopPrm;
        params[14] = scrnMsg.P.no(1).xxPopPrm;

        setArgForSubScreen(params);
    }
}
