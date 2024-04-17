/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.db.PRC_GRPTMsg;
import business.servlet.NMAL7420.common.NMAL7420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/09   Fujitsu         K.Ishizuka      Create          N/A
 * 2019/01/08   Fujitsu         Hd.Sugawara     Update          QC#29751
 *</pre>
 */
public class NMAL7420Scrn00_OpenWin_PrcGrpCustSold extends S21CommonHandler {

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
        // Mod Start 2019/01/08 QC#29751
        //setArgForSubScreen(NMAL7420CommonLogic.getPopParamFor1130(scrnMsg, scrnMsg.prcRuleCondFromTxt_53, //
        //        scrnMsg.prcGrpNm_53, ctx.getEventName(), PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO, getGlobalCompanyCode()));
        String prcGrpPk = scrnMsg.xxPrcQlfyValSrchTxt_53.getValue();

        PRC_GRPTMsg prcGrpTmsg = new PRC_GRPTMsg();
        int prcGrpPkLength = prcGrpTmsg.getAttr("prcGrpPk").getDigit();

        if (ZYPCommonFunc.hasValue(prcGrpPk) && prcGrpPk.length() > prcGrpPkLength) {
            // If over max length, clear.
            scrnMsg.xxPrcQlfyValSrchTxt_53.clear();
        }

        setArgForSubScreen(NMAL7420CommonLogic.getPopParamFor1130(scrnMsg, scrnMsg.xxPrcQlfyValSrchTxt_53, //
                scrnMsg.prcGrpNm_53, ctx.getEventName(), PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO, getGlobalCompanyCode()));

        if (ZYPCommonFunc.hasValue(prcGrpPk) && prcGrpPk.length() > prcGrpPkLength) {
            // If over max length, set again.
            scrnMsg.xxPrcQlfyValSrchTxt_53.setValue(prcGrpPk);
        }
        // Mod End 2019/01/08 QC#29751

    }
}
