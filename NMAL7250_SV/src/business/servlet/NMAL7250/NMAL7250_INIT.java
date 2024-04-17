/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250;

import static business.servlet.NMAL7250.constant.NMAL7250Constant.BIZ_ID;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7250.NMAL7250CMsg;
import business.servlet.NMAL7250.common.NMAL7250CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Fujitsu         W.Honda         Create          N/A
 * 2016/06/01   Fujitsu         Y.Kanefusa      Update          S21_NA#9173
 *</pre>
 */
public class NMAL7250_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;
        NMAL7250CMsg bizMsg = new NMAL7250CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;
        NMAL7250CMsg bizMsg  = (NMAL7250CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7250CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.prcRuleNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7250BMsg scrnMsg = (NMAL7250BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.prcRuleNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Rule Or Table Name"));
        // QC#9173 2016/06/01 Mod Start
        // scrnMsg.prcRulePrcdGrpNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Precedence#"));
        scrnMsg.defRulePrcdNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Precedence#"));
        // QC#9173 2016/06/01 Mod End
        scrnMsg.prcRuleCatgCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price Adjustment Category"));
        scrnMsg.prcRuleTrxCatgCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Transaction Group"));
        scrnMsg.dsOrdCatgCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Order Categories"));
        scrnMsg.dsOrdTpCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Order Reason"));
        scrnMsg.prcRuleAttrbCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Rule Attribute"));
        scrnMsg.prcRuleCondFromTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Value"));
        scrnMsg.dsAcctNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Account Number"));
        scrnMsg.dsAcctNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Account Name"));
        scrnMsg.csmpContrNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "CSMP#"));
        scrnMsg.prcGrpPk_CG.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Customer Group"));
        scrnMsg.coaBrCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Branch#"));
        scrnMsg.lineBizTpCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Line Of Business"));
        scrnMsg.prcGrpPk_MA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Material Group"));
        scrnMsg.effFromDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date From"));
        scrnMsg.effThruDt.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date To"));
        scrnMsg.prcDispRecTpCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Status"));
        scrnMsg.prcRuleCondTpCd.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price Adjustment Type"));
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
    }
}
