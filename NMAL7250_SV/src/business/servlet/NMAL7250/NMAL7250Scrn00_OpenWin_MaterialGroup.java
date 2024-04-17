/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7250Scrn00_OpenWin_MaterialGroup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, ctx.getEventName());
        ZYPTableUtil.clear(scrnMsg.P);

        scrnMsg.P.no(29).xxPopPrm.clear();
        scrnMsg.P.no(28).xxPopPrm.clear();
        // PK is BigDecimal, But Method hope is String
        if (ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk_MA)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(28).xxPopPrm, scrnMsg.prcGrpPk_MA.getValue().toString());
        }

        setArgForSubScreen(NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor1130(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP, scrnMsg.P.no(28).xxPopPrm, scrnMsg.P.no(29).xxPopPrm, getGlobalCompanyCode(), scrnMsg.P));
    }
}
