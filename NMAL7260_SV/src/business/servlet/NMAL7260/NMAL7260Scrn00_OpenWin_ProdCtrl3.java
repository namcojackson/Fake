/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7260.NMAL7260CMsg;
//import business.servlet.NMAL7260.constant.NMAL7260Constant;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceMasterUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 *NMAL7260Scrn00_OpenWin_ProdCtrl3
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/06   Fujitsu         Y.Kanefusa      Create          QC#6397
 *</pre>
 */
public class NMAL7260Scrn00_OpenWin_ProdCtrl3 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
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

        setArgForSubScreen(NMXC105001PriceMasterUtil.getRuleAttrbPopParamFor1130(PRC_RULE_ATTRB.PROD_CTRL_3_PRODUCT,
                scrnMsg.B.no(idx).prcRuleCondFromTxt_34, scrnMsg.B.no(idx).prodCtrlNm_34, getGlobalCompanyCode(), scrnMsg.R));

    }
}