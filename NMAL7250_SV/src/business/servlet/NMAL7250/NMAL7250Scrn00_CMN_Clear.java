/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7250.common.NMAL7250CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DISP_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_COND_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Fujitsu         W.Honda         Create          N/A
 * 2016/06/01   Fujitsu         Y.Kanefusa      Update          S21_NA#9173
 *</pre>
 */
public class NMAL7250Scrn00_CMN_Clear extends S21CommonHandler {

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
        ZYPTableUtil.clear(scrnMsg.A);

        scrnMsg.srchOptPk.clear();
        scrnMsg.srchOptNm.clear();

        scrnMsg.prcRuleNm.clear();
        // QC#9173 2016/06/01 Mod Start
        // scrnMsg.prcRulePrcdGrpNum.clear();
        scrnMsg.defRulePrcdNum.clear();
        // QC#9173 2016/06/01 Mod End
        scrnMsg.prcRuleCatgCd.clear();
        scrnMsg.prcRuleTrxCatgCd.clear();
        scrnMsg.dsOrdCatgCd.clear();
        scrnMsg.dsOrdTpCd.clear();
        scrnMsg.prcRuleAttrbCd.clear();
        scrnMsg.dsAcctNum.clear();
        scrnMsg.dsAcctNm.clear();
        scrnMsg.csmpContrNum.clear();
        scrnMsg.prcGrpPk_CG.clear();
        scrnMsg.coaBrCd.clear();
        scrnMsg.lineBizTpCd.clear();
        scrnMsg.prcGrpPk_MA.clear();
        scrnMsg.effFromDt.clear();
        scrnMsg.effThruDt.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcDispRecTpCd, PRC_DISP_REC_TP.ACTIVE_ONLY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcRuleCondTpCd, PRC_RULE_COND_TP.RULES_AND_TABLES);

        NMAL7250CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.prcRuleNm);
    }
}
