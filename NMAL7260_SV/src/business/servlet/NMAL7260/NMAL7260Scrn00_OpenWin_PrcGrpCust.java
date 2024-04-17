/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_OpenWin_PrcGrpCust
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NMAL7260Scrn00_OpenWin_PrcGrpCust extends S21CommonHandler {

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

//        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
//
//        int idx = getButtonSelectNumber();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(idx));
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
//
//        setArgForSubScreen(NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor1130(PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO,
//                scrnMsg.B.no(idx).prcRuleCondFromTxt_B2, scrnMsg.B.no(idx).prcGrpNm_B1, getGlobalCompanyCode(), scrnMsg.R));
    }
}