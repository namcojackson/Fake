/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL7420.NMAL7420CMsg;
//import business.servlet.NMAL7420.constant.NMAL7420Constant;

import business.servlet.NMAL7420.common.NMAL7420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/10   Fujitsu         Y.Kanefusa      Create          S21_NA#20249
 *</pre>
 */
public class NMAL7420Scrn00_OpenWin_PrcGrpMatQtyBrk extends S21CommonHandler {

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
        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) bMsg;
        setArgForSubScreen(NMAL7420CommonLogic.getPopParamFor1130(scrnMsg, scrnMsg.prcRuleCondFromTxt_57, //
                scrnMsg.prcGrpNm_05, ctx.getEventName(), PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK, getGlobalCompanyCode()));

    }
}
