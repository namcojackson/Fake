/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7250.common;

import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_APL;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_APR;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_RST;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_MOVEWIN_PRICE_ADJUSTMENT;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_MOVEWIN_PRICE_RULE_PRECEDENCE;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_MOVEWIN_PRICING_RULE;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.BTN_SEARCH;
import static business.servlet.NMAL7250.constant.NMAL7250Constant.SCRN_ID_00;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7250.NMAL7250BMsg;
import business.servlet.NMAL7250.NMAL7250_ABMsg;
import business.servlet.NMAL7250.NMAL7250_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7250CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   Fujitsu         W.Honda         Create          N/A
 * 2016/06/01   Fujitsu         Y.Kanefusa      Update          S21_NA#9173
 * 2016/12/13   Fujitsu         T.Aoi           Update          S21_NA#16394
 *</pre>
 */
public class NMAL7250CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7250BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL7250BMsg scrnMsg) {

        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        controlScreenFields(userProfileService, handler, scrnMsg);
        initCmnBtnProp(handler);
        initButton(userProfileService, handler, scrnMsg);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7250BMsg
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL7250BMsg scrnMsg) {
        // Header
        scrnMsg.prcRuleNm.setInputProtected(false);
        // QC#9173 2016/06/01 Mod Start
        // scrnMsg.prcRulePrcdGrpNum.setInputProtected(false);
        scrnMsg.defRulePrcdNum.setInputProtected(false);
        // QC#9173 2016/06/01 Mod End
        scrnMsg.prcRuleAttrbCd.setInputProtected(false);
        scrnMsg.prcRuleTrxCatgCd.setInputProtected(false);
        scrnMsg.xxLinkAncr_OC.setInputProtected(false);
        scrnMsg.xxLinkAncr_OC.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dsOrdCatgCd.setInputProtected(false);
        scrnMsg.xxLinkAncr_OT.setInputProtected(false);
        scrnMsg.xxLinkAncr_OT.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dsOrdTpCd.setInputProtected(false);
        scrnMsg.prcRuleAttrbCd.setInputProtected(false);
        scrnMsg.prcRuleCondFromTxt.setInputProtected(false);
        scrnMsg.xxLinkAncr_AC.setInputProtected(false);
        scrnMsg.xxLinkAncr_AC.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dsAcctNum.setInputProtected(false);
        scrnMsg.xxLinkAncr_AN.setInputProtected(false);
        scrnMsg.xxLinkAncr_AN.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.dsAcctNm.setInputProtected(false);
        scrnMsg.xxLinkAncr_CN.setInputProtected(false);
        scrnMsg.xxLinkAncr_CN.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.csmpContrNum.setInputProtected(false);
        scrnMsg.xxLinkAncr_CG.setInputProtected(false);
        scrnMsg.xxLinkAncr_CG.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.prcGrpPk_CG.setInputProtected(false);
        scrnMsg.xxLinkAncr_BR.setInputProtected(false);
        scrnMsg.xxLinkAncr_BR.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.coaBrCd.setInputProtected(false);
        scrnMsg.lineBizTpCd.setInputProtected(false);
        scrnMsg.xxLinkAncr_MA.setInputProtected(false);
        scrnMsg.xxLinkAncr_MA.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.prcGrpPk_MA.setInputProtected(false);
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);
        scrnMsg.prcDispRecTpCd.setInputProtected(false);
        scrnMsg.prcRuleCondTpCd.setInputProtected(false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7250_ABMsg scrnMsgALine = scrnMsg.A.no(i);
            scrnMsgALine.xxChkBox_A1.setInputProtected(false);
            scrnMsgALine.prcRuleHdrPk_A1.setInputProtected(true);
            scrnMsgALine.xxLinkAncr_ID.setInputProtected(false);
            scrnMsgALine.xxLinkAncr_ID.setValue(ZYPConstant.FLG_ON_Y);
            scrnMsgALine.prcRuleNm_A1.setInputProtected(true);
            scrnMsgALine.prcRuleCondTpDescTxt_A1.setInputProtected(true);
            //Mod Start 2016/12/13 T.Aoi S21_NA#16394
            //scrnMsgALine.dsBizLineDescTxt_A1.setInputProtected(true);
            scrnMsgALine.lineBizTpDescTxt_A1.setInputProtected(true);
            //Mod End   2016/12/13 T.Aoi S21_NA#16394
            scrnMsgALine.prcRuleCatgDescTxt_A1.setInputProtected(true);
            scrnMsgALine.xxFlgNm_AP.setInputProtected(true);
            scrnMsgALine.xxFlgNm_OV.setInputProtected(true);
            scrnMsgALine.prcDispRecTpDescTxt_A1.setInputProtected(true);
            scrnMsgALine.defRulePrcdNum_A1.setInputProtected(true);
            scrnMsgALine.effFromDt_A1.setInputProtected(true);
            scrnMsgALine.effThruDt_A1.setInputProtected(true);
        }
    }

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(EZDCommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }
    /**
     * Method name: initButton <dd>The method explanation: init
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     * @param scrnMsg NMAL7250BMsg
     */
    public static final void initButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL7250BMsg scrnMsg) {

        // Header
        handler.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1,  null);

        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties(BTN_MOVEWIN_PRICE_RULE_PRECEDENCE[0], BTN_MOVEWIN_PRICE_RULE_PRECEDENCE[1], BTN_MOVEWIN_PRICE_RULE_PRECEDENCE[2], 1, null);
            handler.setButtonProperties(BTN_MOVEWIN_PRICING_RULE[0], BTN_MOVEWIN_PRICING_RULE[1], BTN_MOVEWIN_PRICING_RULE[2], 1, null);
            handler.setButtonProperties(BTN_MOVEWIN_PRICE_ADJUSTMENT[0], BTN_MOVEWIN_PRICE_ADJUSTMENT[1], BTN_MOVEWIN_PRICE_ADJUSTMENT[2], 1, null);
       } else {
           handler.setButtonProperties(BTN_MOVEWIN_PRICE_RULE_PRECEDENCE[0], BTN_MOVEWIN_PRICE_RULE_PRECEDENCE[1], BTN_MOVEWIN_PRICE_RULE_PRECEDENCE[2], 0, null);
           handler.setButtonProperties(BTN_MOVEWIN_PRICING_RULE[0], BTN_MOVEWIN_PRICING_RULE[1], BTN_MOVEWIN_PRICING_RULE[2], 0, null);
           handler.setButtonProperties(BTN_MOVEWIN_PRICE_ADJUSTMENT[0], BTN_MOVEWIN_PRICE_ADJUSTMENT[1], BTN_MOVEWIN_PRICE_ADJUSTMENT[2], 0, null);
       }
    }

    /**
     * Set Common Button properties.
     * 
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7250BMsg
     * @param scrnAMsgAry NMAL7250_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7250BMsg scrnMsg, NMAL7250_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7250BMsg
     * @param scrnAMsgAry NMAL7250_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7250BMsg scrnMsg, NMAL7250_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7250BMsg
     * @param scrnAMsgAry NMAL7250_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7250BMsg scrnMsg, NMAL7250_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NMAL7250BMsg
     */
    public static void commonAddCheckItem(NMAL7250BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm);

        scrnMsg.addCheckItem(scrnMsg.prcRuleNm);
        // QC#9173 2016/06/01 Mod Start
        // scrnMsg.addCheckItem(scrnMsg.prcRulePrcdGrpNum);
        scrnMsg.addCheckItem(scrnMsg.defRulePrcdNum);
        // QC#9173 2016/06/01 Mod End
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.csmpContrNum);
        scrnMsg.addCheckItem(scrnMsg.prcGrpPk_CG);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd);
        scrnMsg.addCheckItem(scrnMsg.prcGrpPk_MA);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
    }

}
