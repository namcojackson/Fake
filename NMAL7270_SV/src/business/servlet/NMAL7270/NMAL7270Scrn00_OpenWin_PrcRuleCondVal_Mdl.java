/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7270Scrn00_OpenWin_PrcRuleCondVal_Mdl
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2017/09/01   Fujitsu         R.Nakamura      Update          QC#20729-2
 *</pre>
 */
public class NMAL7270Scrn00_OpenWin_PrcRuleCondVal_Mdl extends S21CommonHandler {

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

        // Mod Start 2017/09/01 QC#20729-2
//        // Clear Params
//        ZYPTableUtil.clear(scrnMsg.P);
//
//        // Set Params
//        Object[] params = new Object[1];
//        params[0]  = scrnMsg.P.no(0).xxPopPrm;
//
//        setArgForSubScreen(params);
//        openMultiModeScreen();

        scrnMsg.R.clear();

        setArgForSubScreen(NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor1130(scrnMsg.A.no(idx).prcRuleAttrbCd_A1.getValue(),
                scrnMsg.A.no(idx).prcRuleCondFromTxt_A1, scrnMsg.A.no(idx).xxRecNmTxt_A1, getGlobalCompanyCode(), scrnMsg.R));
        // Mod End 2017/09/01 QC#20729-2
    }
}
