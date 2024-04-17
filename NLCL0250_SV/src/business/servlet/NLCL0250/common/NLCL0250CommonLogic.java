/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0250.common;

import static business.servlet.NLCL0250.constant.NLCL0250Constant.BUSINESS_ID;
import static business.servlet.NLCL0250.constant.NLCL0250Constant.FUNCTION_UPDATE;
import static business.servlet.NLCL0250.constant.NLCL0250Constant.BTN_3RD_PARTY_INV_MATERIAL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0250.NLCL0250BMsg;
import business.servlet.NLCL0250.NLCL0250Bean;
import business.servlet.NLCL0250.NLCL0250_PBMsgArray;
import business.servlet.NLCL0250.constant.NLCL0250Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Onhand Inventory Inquiry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3134
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3137
 * 01/20/2016   CSAI            Y.Imazu         Update          QC#3142
 * 09/14/2016   CSAI            Y.Imazu         Update          QC#13187
 * 10/20/2016   CSAI            Y.Imazu         Update          QC#14081
 * 01/16/2017   CITS            T.Kikuhara      Update          QC#16256
 * 12/18/2017   CITS            S.Katsuma       Update          QC#22469
 * 03/07/2023   Hitachi         S.Dong          Update          QC#61205
 * 05/15/2023   Hitachi         S.Dong          Update          QC#61398
 *</pre>
 */
public class NLCL0250CommonLogic {

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0250BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NLCL0250BMsg scrnMsg) {

        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(NLCL0250Constant.SCREEN_ID);

        controlScreen(userProfileService, handler, scrnMsg);
    }

    /**
     * controlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0250BMsg
     */
    public static final void controlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NLCL0250BMsg scrnMsg) {

        initCommonButton(userProfileService, handler);
        controlScreenFields(userProfileService, handler, scrnMsg);
    }

    /**
     * initCommonButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {
        // QC#16256 add start
        // This Screen is Read only Screen
        if (isUpdatable()) {
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_SAVE[0], NLCL0250Constant.BTN_CMN_SAVE[1], NLCL0250Constant.BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_SUBMIT[0], NLCL0250Constant.BTN_CMN_SUBMIT[1], NLCL0250Constant.BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_APPLY[0], NLCL0250Constant.BTN_CMN_APPLY[1], NLCL0250Constant.BTN_CMN_APPLY[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_APPROVE[0], NLCL0250Constant.BTN_CMN_APPROVE[1], NLCL0250Constant.BTN_CMN_APPROVE[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_REJECT[0], NLCL0250Constant.BTN_CMN_REJECT[1], NLCL0250Constant.BTN_CMN_REJECT[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_DOWNLOAD[0], NLCL0250Constant.BTN_CMN_DOWNLOAD[1], NLCL0250Constant.BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_DETELE[0], NLCL0250Constant.BTN_CMN_DETELE[1], NLCL0250Constant.BTN_CMN_DETELE[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_CLEAR[0], NLCL0250Constant.BTN_CMN_CLEAR[1], NLCL0250Constant.BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_RESET[0], NLCL0250Constant.BTN_CMN_RESET[1], NLCL0250Constant.BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_RETURN[0], NLCL0250Constant.BTN_CMN_RETURN[1], NLCL0250Constant.BTN_CMN_RETURN[2], 1, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_SEARCH[0], NLCL0250Constant.BTN_SEARCH[1], NLCL0250Constant.BTN_SEARCH[2], 1, null);
        } else {
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_SAVE[0], NLCL0250Constant.BTN_CMN_SAVE[1], NLCL0250Constant.BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_SUBMIT[0], NLCL0250Constant.BTN_CMN_SUBMIT[1], NLCL0250Constant.BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_APPLY[0], NLCL0250Constant.BTN_CMN_APPLY[1], NLCL0250Constant.BTN_CMN_APPLY[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_APPROVE[0], NLCL0250Constant.BTN_CMN_APPROVE[1], NLCL0250Constant.BTN_CMN_APPROVE[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_REJECT[0], NLCL0250Constant.BTN_CMN_REJECT[1], NLCL0250Constant.BTN_CMN_REJECT[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_DOWNLOAD[0], NLCL0250Constant.BTN_CMN_DOWNLOAD[1], NLCL0250Constant.BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_DETELE[0], NLCL0250Constant.BTN_CMN_DETELE[1], NLCL0250Constant.BTN_CMN_DETELE[2], 0, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_CLEAR[0], NLCL0250Constant.BTN_CMN_CLEAR[1], NLCL0250Constant.BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_RESET[0], NLCL0250Constant.BTN_CMN_RESET[1], NLCL0250Constant.BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_CMN_RETURN[0], NLCL0250Constant.BTN_CMN_RETURN[1], NLCL0250Constant.BTN_CMN_RETURN[2], 1, null);
            handler.setButtonProperties(NLCL0250Constant.BTN_SEARCH[0], NLCL0250Constant.BTN_SEARCH[1], NLCL0250Constant.BTN_SEARCH[2], 1, null);
        }
        // QC#16256 add end
    }

    /**
     * controlScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL0250BMsg
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NLCL0250BMsg scrnMsg) {

        // Inactive
        scrnMsg.rtlWhNmSrchTxt_RW.setInputProtected(true);
        scrnMsg.rtlWhNmSrchTxt_SW.setInputProtected(true);
        scrnMsg.xxFldValTxt_HN.setInputProtected(true);
        // START 2018/01/18 S.Katsuma [QC#22469,ADD]
        scrnMsg.xxScrItem500Txt_H1.setInputProtected(true);
        // END 2018/01/18 S.Katsuma [QC#22469,ADD]
        // START 2023/05/15 S.Dong [QC#61398, ADD]
        handler.setButtonEnabled(BTN_3RD_PARTY_INV_MATERIAL, true);
        // END 2023/05/15 S.Dong [QC#61398, ADD]
        // Focus Rule
        ZYPGUITableFocusRule tblFocusRuleH = new ZYPGUITableFocusRule(NLCL0250Constant.SCREEN_ID, NLCL0250Constant.HEADER_TABLE_ID);
        scrnMsg.addGUIAttribute(tblFocusRuleH);
        setHeaderFocusRule(tblFocusRuleH);

        // Location Status
        if (0 < scrnMsg.L.getValidCount()) {

            S21TableColorController tblColor = new S21TableColorController(NLCL0250Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLCL0250Bean.L, scrnMsg.L);

            addRuleNext(tblFocusRuleH, NLCL0250Bean.xxThruDt_H1, NLCL0250Bean.xxChkBox_LS + 0);
            addRulePrev(tblFocusRuleH, NLCL0250Bean.xxThruDt_H1, NLCL0250Bean.xxChkBox_LS + 0);

            for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

                scrnMsg.L.no(i).xxScrItem61Txt_LS.setInputProtected(true);

                if ((scrnMsg.L.getValidCount() - 1) > i) {

                    addRuleNext(tblFocusRuleH, NLCL0250Bean.xxChkBox_LS + i, NLCL0250Bean.xxChkBox_LS + (i + 1));
                    addRulePrev(tblFocusRuleH, NLCL0250Bean.xxChkBox_LS + i, NLCL0250Bean.xxChkBox_LS + (i + 1));

                } else {

                    if (0 < scrnMsg.S.getValidCount()) {

                        addRuleNext(tblFocusRuleH, NLCL0250Bean.xxChkBox_LS + i, NLCL0250Bean.xxChkBox_SS + 0);
                        addRulePrev(tblFocusRuleH, NLCL0250Bean.xxChkBox_LS + i, NLCL0250Bean.xxChkBox_SS + 0);

                    } else {

                        addRuleNext(tblFocusRuleH, NLCL0250Bean.xxChkBox_LS + i, NLCL0250Bean.xxDplyCtrlFlg_H1);
                        addRulePrev(tblFocusRuleH, NLCL0250Bean.xxChkBox_LS + i, NLCL0250Bean.xxDplyCtrlFlg_H1);
                    }
                }
            }

        } else if (0 < scrnMsg.S.getValidCount()) {

            addRuleNext(tblFocusRuleH, NLCL0250Bean.xxThruDt_H1, NLCL0250Bean.xxChkBox_SS + 0);
            addRulePrev(tblFocusRuleH, NLCL0250Bean.xxThruDt_H1, NLCL0250Bean.xxChkBox_SS + 0);

        } else {

            addRuleNext(tblFocusRuleH, NLCL0250Bean.xxThruDt_H1, NLCL0250Bean.xxDplyCtrlFlg_H1);
            addRulePrev(tblFocusRuleH, NLCL0250Bean.xxThruDt_H1, NLCL0250Bean.xxDplyCtrlFlg_H1);
        }

        // Stock Status
        if (0 < scrnMsg.S.getValidCount()) {

            S21TableColorController tblColor = new S21TableColorController(NLCL0250Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLCL0250Bean.S, scrnMsg.S);

            for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {

                scrnMsg.S.no(i).xxScrItem61Txt_SS.setInputProtected(true);

                if ((scrnMsg.S.getValidCount() - 1) > i) {

                    addRuleNext(tblFocusRuleH, NLCL0250Bean.xxChkBox_SS + i, NLCL0250Bean.xxChkBox_SS + (i + 1));
                    addRulePrev(tblFocusRuleH, NLCL0250Bean.xxChkBox_SS + i, NLCL0250Bean.xxChkBox_SS + (i + 1));

                } else {

                    addRuleNext(tblFocusRuleH, NLCL0250Bean.xxChkBox_SS + i, NLCL0250Bean.xxDplyCtrlFlg_H1);
                    addRulePrev(tblFocusRuleH, NLCL0250Bean.xxChkBox_SS + i, NLCL0250Bean.xxDplyCtrlFlg_H1);
                }
            }
        }

        // Search Result
        scrnMsg.A.setInputProtected(true);

        if (0 < scrnMsg.A.getValidCount()) {

            S21TableColorController tblColor = new S21TableColorController(NLCL0250Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLCL0250Bean.A, scrnMsg.A);
        }

        addCheckItemHeader(scrnMsg);
    }

    /**
     * addRuleNext
     * @param tblFcsRule ZYPGUITableFocusRule
     * @param item String
     * @param nextItem String
     */
    private static void addRuleNext(ZYPGUITableFocusRule tblFcsRule, String item, String nextItem) {

        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule(item);
        fRule.setNextId(nextItem);
        tblFcsRule.addRule(fRule);
    }

    /**
     * addRulePrev
     * @param tblFcsRule ZYPGUITableFocusRule
     * @param prevItem String
     * @param item String
     */
    private static void addRulePrev(ZYPGUITableFocusRule tblFcsRule, String prevItem, String item) {

        ZYPGUIFocusRule fRule = new ZYPGUIFocusRule(item);
        fRule.setPrevId(prevItem);
        tblFcsRule.addRule(fRule);
    }

    /**
     * setHeaderFocusRule
     * @param tblFocusRule ZYPGUITableFocusRule
     */
    private static void setHeaderFocusRule(ZYPGUITableFocusRule tblFocusRule) {

        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_BU, NLCL0250Bean.zerothProdCtrlNm_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_BU, NLCL0250Bean.zerothProdCtrlNm_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.zerothProdCtrlNm_H1, NLCL0250Bean.xxLinkAncr_PG);
        addRulePrev(tblFocusRule, NLCL0250Bean.zerothProdCtrlNm_H1, NLCL0250Bean.xxLinkAncr_PG);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_PG, NLCL0250Bean.firstProdCtrlNm_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_PG, NLCL0250Bean.firstProdCtrlNm_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.firstProdCtrlNm_H1, NLCL0250Bean.xxLinkAncr_PF);
        addRulePrev(tblFocusRule, NLCL0250Bean.firstProdCtrlNm_H1, NLCL0250Bean.xxLinkAncr_PF);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_PF, NLCL0250Bean.scdProdCtrlNm_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_PF, NLCL0250Bean.scdProdCtrlNm_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.scdProdCtrlNm_H1, NLCL0250Bean.xxLinkAncr_PL);
        addRulePrev(tblFocusRule, NLCL0250Bean.scdProdCtrlNm_H1, NLCL0250Bean.xxLinkAncr_PL);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_PL, NLCL0250Bean.thirdProdCtrlNm_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_PL, NLCL0250Bean.thirdProdCtrlNm_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.thirdProdCtrlNm_H1, NLCL0250Bean.xxLinkAncr_PS);
        addRulePrev(tblFocusRule, NLCL0250Bean.thirdProdCtrlNm_H1, NLCL0250Bean.xxLinkAncr_PS);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_PS, NLCL0250Bean.frthProdCtrlNm_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_PS, NLCL0250Bean.frthProdCtrlNm_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.frthProdCtrlNm_H1, NLCL0250Bean.coaProjCd_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.frthProdCtrlNm_H1, NLCL0250Bean.coaProjCd_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.coaProjCd_H1, NLCL0250Bean.mdseItemTpCd_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.coaProjCd_H1, NLCL0250Bean.mdseItemTpCd_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.mdseItemTpCd_H1, NLCL0250Bean.xxLinkAncr_IT);
        addRulePrev(tblFocusRule, NLCL0250Bean.mdseItemTpCd_H1, NLCL0250Bean.xxLinkAncr_IT);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_IT, NLCL0250Bean.xxMdseSrchTxt_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_IT, NLCL0250Bean.xxMdseSrchTxt_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxMdseSrchTxt_H1, NLCL0250Bean.xxLinkAncr_SN);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxMdseSrchTxt_H1, NLCL0250Bean.xxLinkAncr_SN);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_SN, NLCL0250Bean.xxSerNumSrchTxt_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_SN, NLCL0250Bean.xxSerNumSrchTxt_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxSerNumSrchTxt_H1, NLCL0250Bean.xxLinkAncr_CN);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxSerNumSrchTxt_H1, NLCL0250Bean.xxLinkAncr_CN);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_CN, NLCL0250Bean.srchOptTxt_CF);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_CN, NLCL0250Bean.srchOptTxt_CF);
        addRuleNext(tblFocusRule, NLCL0250Bean.srchOptTxt_CF, NLCL0250Bean.rtlWhCatgCd_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.srchOptTxt_CF, NLCL0250Bean.rtlWhCatgCd_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.rtlWhCatgCd_H1, NLCL0250Bean.xxLinkAncr_WR);
        addRulePrev(tblFocusRule, NLCL0250Bean.rtlWhCatgCd_H1, NLCL0250Bean.xxLinkAncr_WR);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_WR, NLCL0250Bean.rtlWhCdSrchTxt_RW);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_WR, NLCL0250Bean.rtlWhCdSrchTxt_RW);
        addRuleNext(tblFocusRule, NLCL0250Bean.rtlWhNmSrchTxt_RW, NLCL0250Bean.xxLinkAncr_WS);
        addRulePrev(tblFocusRule, NLCL0250Bean.rtlWhNmSrchTxt_RW, NLCL0250Bean.xxLinkAncr_WS);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_WS, NLCL0250Bean.rtlWhCdSrchTxt_SW);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_WS, NLCL0250Bean.rtlWhCdSrchTxt_SW);
        addRuleNext(tblFocusRule, NLCL0250Bean.rtlWhNmSrchTxt_SW, NLCL0250Bean.xxLinkAncr_ST);
        addRulePrev(tblFocusRule, NLCL0250Bean.rtlWhNmSrchTxt_SW, NLCL0250Bean.xxLinkAncr_ST);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_ST, NLCL0250Bean.xxFldValTxt_HC);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_ST, NLCL0250Bean.xxFldValTxt_HC);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxFldValTxt_HN, NLCL0250Bean.xxFromDt_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxFldValTxt_HN, NLCL0250Bean.xxFromDt_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxFromDt_H1, NLCL0250Bean.xxThruDt_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxFromDt_H1, NLCL0250Bean.xxThruDt_H1);
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        addRuleNext(tblFocusRule, NLCL0250Bean.xxChkBox_PR, NLCL0250Bean.xxChkBox_PR);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxChkBox_PR, NLCL0250Bean.xxChkBox_PR);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxLinkAncr_RC, NLCL0250Bean.xxLinkAncr_RC);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxLinkAncr_RC, NLCL0250Bean.xxLinkAncr_RC);
        addRuleNext(tblFocusRule, NLCL0250Bean.rtrnCtrlTpVndRelnPk_H1, NLCL0250Bean.rtrnCtrlTpVndRelnPk_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.rtrnCtrlTpVndRelnPk_H1, NLCL0250Bean.rtrnCtrlTpVndRelnPk_H1);
        addRuleNext(tblFocusRule, NLCL0250Bean.xxScrItem500Txt_H1, NLCL0250Bean.xxScrItem500Txt_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.xxScrItem500Txt_H1, NLCL0250Bean.xxScrItem500Txt_H1);
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        addRuleNext(tblFocusRule, NLCL0250Bean.whOwnrCd_H1, NLCL0250Bean.whOwnrCd_H1);
        addRulePrev(tblFocusRule, NLCL0250Bean.whOwnrCd_H1, NLCL0250Bean.whOwnrCd_H1);
        // END 2023/03/07 S.Dong [QC#61205, ADD]
    }

    /**
     * AddCheckItemHeader
     * @param scrnMsg NLCL0250BMsg
     */
    public static void addCheckItemHeader(NLCL0250BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.zerothProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.firstProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.scdProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.thirdProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.frthProdCtrlNm_H1);
        scrnMsg.addCheckItem(scrnMsg.coaProjCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.xxMdseSrchTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxSerNumSrchTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.srchOptTxt_CF);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCdSrchTxt_RW);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNmSrchTxt_RW);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCdSrchTxt_SW);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNmSrchTxt_SW);
        scrnMsg.addCheckItem(scrnMsg.xxFldValTxt_HC);
        scrnMsg.addCheckItem(scrnMsg.xxFldValTxt_HN);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxThruDt_H1);
        // START 2017/12/18 S.Katsuma [QC#22469,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_PR);
        scrnMsg.addCheckItem(scrnMsg.rtrnCtrlTpVndRelnPk_H1);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem500Txt_H1);
        // END 2017/12/18 S.Katsuma [QC#22469,ADD]
        // START 2023/03/07 S.Dong [QC#61205, ADD]
        scrnMsg.addCheckItem(scrnMsg.whOwnrCd_H1);
        // END 2023/03/07 S.Dong [QC#61205, ADD]

        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.L.no(i).xxChkBox_LS);
        }
    }

    /**
     * checkInputSearch
     * @param scrnMsg NLCL0250BMsg
     */
    public static void checkInputSearch(NLCL0250BMsg scrnMsg) {

        // Clear Name
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCdSrchTxt_RW)) {

            scrnMsg.rtlWhNmSrchTxt_RW.clear();
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCdSrchTxt_SW)) {

            scrnMsg.rtlWhNmSrchTxt_SW.clear();
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxFldValTxt_HC)) {

            scrnMsg.xxFldValTxt_HN.clear();
        }

        // START 2018/01/18 S.Katsuma [QC#22469,ADD]
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxScrItem500Txt_H1)) {

            scrnMsg.xxScrItem500Txt_H1.clear();
        }
        // END 2018/01/18 S.Katsuma [QC#22469,ADD]

        // Detail Search
        if (NLCL0250Constant.DETAIL_SEARCH.equals(scrnMsg.xxDplyCtrlFlg_H1.getValue()) && isSrchCondTxtBlank(scrnMsg)) {

            scrnMsg.xxMdseSrchTxt_H1.setErrorInfo(1, NLCL0250Constant.NLZM2276E);
            scrnMsg.rtlWhCdSrchTxt_RW.setErrorInfo(1, NLCL0250Constant.NLZM2276E);
            scrnMsg.rtlWhCdSrchTxt_SW.setErrorInfo(1, NLCL0250Constant.NLZM2276E);
            scrnMsg.xxFldValTxt_HC.setErrorInfo(1, NLCL0250Constant.NLZM2276E);
            scrnMsg.srchOptTxt_CF.setErrorInfo(1, NLCL0250Constant.NLZM2276E);
            scrnMsg.xxSerNumSrchTxt_H1.setErrorInfo(1, NLCL0250Constant.NLZM2276E);
            scrnMsg.xxFromDt_H1.setErrorInfo(1, NLCL0250Constant.NLZM2276E);
            scrnMsg.xxThruDt_H1.setErrorInfo(1, NLCL0250Constant.NLZM2276E);
        }

        // Age
        if (ZYPCommonFunc.hasValue(scrnMsg.xxFromDt_H1) || ZYPCommonFunc.hasValue(scrnMsg.xxThruDt_H1)) {

            if (!isFromThruDtChk(scrnMsg.xxFromDt_H1, scrnMsg.xxThruDt_H1)) {

                scrnMsg.xxFromDt_H1.setErrorInfo(1, NLCL0250Constant.NLCM0115E, new String[]{"Age"});
                scrnMsg.xxThruDt_H1.setErrorInfo(1, NLCL0250Constant.NLCM0115E, new String[]{"Age"});

            } else if (ZYPCommonFunc.hasValue(scrnMsg.xxFromDt_H1) && ZYPDateUtil.compare(scrnMsg.xxFromDt_H1.getValue(), scrnMsg.slsDt.getValue()) > 0) {

                scrnMsg.xxFromDt_H1.setErrorInfo(1, NLCL0250Constant.NLAM1087E);
            }

            // Location Status Check
            boolean locStsChk = false;
            boolean locStsDcStk = false;

            for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.L.no(i).xxChkBox_LS.getValue())) {

                    locStsChk = true;

                    if (LOC_STS.DC_STOCK.equals(scrnMsg.L.no(i).locStsCd_LS.getValue())) {

                        locStsDcStk = true;
                        break;
                    }

                    if (LOC_STS.TRIAL_USE.equals(scrnMsg.L.no(i).locStsCd_LS.getValue())) {

                        locStsDcStk = true;
                        break;
                    }
                }
            }

            if (locStsChk && !locStsDcStk) {

                for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {

                    if (LOC_STS.DC_STOCK.equals(scrnMsg.L.no(i).locStsCd_LS.getValue()) || LOC_STS.TRIAL_USE.equals(scrnMsg.L.no(i).locStsCd_LS.getValue())) {

                        scrnMsg.L.no(i).xxChkBox_LS.setErrorInfo(1, NLCL0250Constant.NMAM8179E, new String[]{scrnMsg.L.no(i).xxScrItem61Txt_LS.getValue(), "Age"});
                    }
                }
            }
        }

        // Config ID
        if (!isSrchCondTxtNumChk(scrnMsg.srchOptTxt_CF)) {

            scrnMsg.srchOptTxt_CF.setErrorInfo(1, NLCL0250Constant.ZZM9004E, new String[]{"Config ID"});
        }

        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    /**
     * isSrchCondTxtBlank
     * @param scrnMsg NLCL0250BMsg
     * @return boolean
     */
    private static boolean isSrchCondTxtBlank(NLCL0250BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.xxMdseSrchTxt_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCdSrchTxt_RW)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCdSrchTxt_SW)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxFldValTxt_HC)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.srchOptTxt_CF)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxSerNumSrchTxt_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxFromDt_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxThruDt_H1)) {

            return false;
        }

        return true;
    }

    /**
     * isFromThruDtChk
     * @param fromDt EZDBDateItem
     * @param thruDt EZDBDateItem
     * @return boolean
     */
    private static boolean isFromThruDtChk(EZDBDateItem fromDt, EZDBDateItem thruDt) {

        if (!ZYPCommonFunc.hasValue(fromDt) || !ZYPCommonFunc.hasValue(thruDt)) {

            return true;

        } else if (!ZYPDateUtil.checkDate(fromDt.getValue()) || !ZYPDateUtil.checkDate(thruDt.getValue())) {

            return true;

        } else if (ZYPDateUtil.compare(fromDt.getValue(), thruDt.getValue()) < 1) {

            return true;
        }

        return false;
    }

    /**
     * isSrchCondTxtNumChk
     * @param srchCondTxt EZDBStringItem
     * @return boolean
     */
    public static boolean isSrchCondTxtNumChk(EZDBStringItem srchCondTxt) {

        if (ZYPCommonFunc.hasValue(srchCondTxt)) {

            if (srchCondTxt.getValue().indexOf(NLCL0250Constant.COMMA) != -1) {

                String[] configIdArray = srchCondTxt.getValue().split(NLCL0250Constant.COMMA);
                boolean isSplit = false;

                for (int i = 0; i < configIdArray.length; i++) {

                    if (!configIdArray[i].trim().equals(NLCL0250Constant.BLANK) && configIdArray[i].length() > 0) {

                        // START 2017/12/18 S.Katsuma [QC#22469,DEL]
//                        if (!ZYPCommonFunc.isNumberCheck(configIdArray[i].trim())) {
//
//                            return false;
//                        }
                        // END 2017/12/18 S.Katsuma [QC#22469,DEL]

                        isSplit = true;
                    }
                }

                if (isSplit) {

                    return true;
                }
            }

            // START 2017/12/18 S.Katsuma [QC#22469,DEL]
//            if (!ZYPCommonFunc.isNumberCheck(srchCondTxt.getValue())) {
//
//                return false;
//            }
            // START 2017/12/18 S.Katsuma [QC#22469,DEL]
        }

        return true;
    }

    /**
     * Check Mandatory Field
     * @param ezStrgItem EZDBStringItem
     * @param errDplyNm String
     */
    public static void chkMndFld(EZDBStringItem ezStrgItem, String errDplyNm) {

        if (!ZYPCommonFunc.hasValue(ezStrgItem)) {

            ezStrgItem.setErrorInfo(1, NLCL0250Constant.ZZM9000E, new String[]{errDplyNm});
        }
    }

    /**
     * setParamForNMAL6040
     * @param scrnMsg NLCL0250BMsg
     * @return params
     */
    public static Object[] setParamForNMAL6040(NLCL0250BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);
        int i = 0;

        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.zerothProdCtrlNm_H1);
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.firstProdCtrlNm_H1);
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.scdProdCtrlNm_H1);
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.thirdProdCtrlNm_H1);
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.frthProdCtrlNm_H1);
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();

        if (ZYPCommonFunc.hasValue(scrnMsg.xxMdseSrchTxt_H1) && scrnMsg.xxMdseSrchTxt_H1.getValue().length() > 16) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.xxMdseSrchTxt_H1.getValue().substring(0, 16));

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.xxMdseSrchTxt_H1);
        }

        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();

        Object[] params = toArray_popup(scrnMsg.P, i++);

        return params;
    }

    /**
     * setParamForNPAL1010
     * @param scrnMsg NLCL0250BMsg
     * @param isRtlWhLink boolean
     * @param String eventNm
     * @return params
     */
    public static Object[] setParamForNPAL1010(NLCL0250BMsg scrnMsg, boolean isRtlWhLink, String eventNm) {

        ZYPTableUtil.clear(scrnMsg.P);
        int i = 0;

        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, ZYPConstant.FLG_ON_Y);
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.rtlWhCdSrchTxt_RW);
        scrnMsg.P.no(i++).srchOptTxt_P.clear();

        if (isRtlWhLink) {

            scrnMsg.P.no(i++).srchOptTxt_P.clear();
            scrnMsg.P.no(i++).srchOptTxt_P.clear();
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, ZYPConstant.FLG_ON_Y);

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.rtlWhCdSrchTxt_SW);
            scrnMsg.P.no(i++).srchOptTxt_P.clear();
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, ZYPConstant.FLG_OFF_N);
        }

        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        scrnMsg.P.no(i++).srchOptTxt_P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, scrnMsg.rtlWhCatgCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).srchOptTxt_P, ZYPConstant.FLG_ON_Y);

        Object[] params = toArray_popup(scrnMsg.P, i);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, eventNm);
        return params;
    }

    /**
     * setParamForNSAL1240
     * @param scrnMsg NLCL0250BMsg
     * @param String eventNm
     * @return params
     */
    public static Object[] setParamForNSAL1240(NLCL0250BMsg scrnMsg, String eventNm) {

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.O);
        scrnMsg.svcConfigMstrPk_P1.clear();
        scrnMsg.svcConfigMstrPk_P2.clear();

        // Config PK
        if (ZYPCommonFunc.hasValue(scrnMsg.srchOptTxt_CF)) {

            if (!isSrchCondTxtNumChk(scrnMsg.srchOptTxt_CF)) {

                scrnMsg.srchOptTxt_CF.setErrorInfo(1, NLCL0250Constant.ZZM9004E, new String[]{"Config ID"});
                scrnMsg.addCheckItem(scrnMsg.srchOptTxt_CF);

            } else {

                String svcConfigMstrPk = scrnMsg.srchOptTxt_CF.getValue();

                if (scrnMsg.srchOptTxt_CF.getValue().indexOf(NLCL0250Constant.COMMA) != -1) {

                    String[] configIdArray = scrnMsg.srchOptTxt_CF.getValue().split(NLCL0250Constant.COMMA);

                    for (int i = 0; i < configIdArray.length; i++) {

                        if (!configIdArray[i].trim().equals(NLCL0250Constant.BLANK) && configIdArray[i].length() > 0) {

                            svcConfigMstrPk = configIdArray[i].trim();
                            break;
                        }
                    }
                }

                if (svcConfigMstrPk.length() > 28) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_P1, new BigDecimal(svcConfigMstrPk.substring(0, 28)));

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_P1, new BigDecimal(svcConfigMstrPk));
                }
            }
        }

        // Serial
        if (ZYPCommonFunc.hasValue(scrnMsg.xxSerNumSrchTxt_H1)) {

            String serNum = scrnMsg.xxSerNumSrchTxt_H1.getValue();

            if (scrnMsg.xxSerNumSrchTxt_H1.getValue().indexOf(NLCL0250Constant.COMMA) != -1) {

                String[] configIdArray = scrnMsg.xxSerNumSrchTxt_H1.getValue().split(NLCL0250Constant.COMMA);

                for (int i = 0; i < configIdArray.length; i++) {

                    if (!configIdArray[i].trim().equals(NLCL0250Constant.BLANK) && configIdArray[i].length() > 0) {

                        serNum = configIdArray[i].trim();
                        break;
                    }
                }
            }

            if (serNum.length() > 30) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).srchOptTxt_P, serNum.substring(0, 30));

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).srchOptTxt_P, serNum);
            }
        }

        Object[] params = new Object[31];
        params[1] = scrnMsg.svcConfigMstrPk_P1;
        params[2] = scrnMsg.P.no(0).srchOptTxt_P;
        params[29] = scrnMsg.svcConfigMstrPk_P2;
        params[30] = scrnMsg.O;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, eventNm);
        return params;
    }

    /**
     * toArray_popup
     * @param p NLCL0250_PBMsgArray
     * @param size int
     * @return params
     */
    public static Object[] toArray_popup(NLCL0250_PBMsgArray p, int size) {

        Object[] param = new Object[size];

        for (int i = 0; i < size; i++) {

            param[i] = p.no(i).srchOptTxt_P;
        }

        return param;
    }

    /**
     * toUpperCase
     * @param arg String
     * @return String
     */
    public static String toUpperCase(String arg) {

        if (ZYPCommonFunc.hasValue(arg)) {

            return arg.toUpperCase();
        }

        return arg;
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList.contains(FUNCTION_UPDATE);
    }
    // QC#16256 add end

    // START 2017/12/18 S.Katsuma [QC#22469,ADD]
    /**
     * setParamForNWAL1130
     * @param scrnMsg NLCL0250BMsg
     * @param glblCmpyCd
     * @return params
     */
    public static Object[] setParamForNWAL1130(NLCL0250BMsg scrnMsg, String glblCmpyCd) {
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.Q);

        Object[] params = new Object[7];
        String selectStr = setSelectForRtrnCtrl(glblCmpyCd, ZYPDateUtil.getSalesDate());
        List<Object[]> whereList = setWhereListForRtrnCtrl(scrnMsg);
        List<Object[]> tblColumnList = setTblColumnListForRtrnCtrl();
        List<Object[]> sortCondList = setSortListForRtrnCtrl();
        params[0] = "Q";                   
        params[1] = "Rtrn Ctrl Ty Srch Popup";    //1.Screen Title
        params[2] = selectStr;              //2.Table Name
        params[3] = whereList;              //3.Search Criteria(H)
        params[4] = tblColumnList;          //4.Column (C)
        params[5] = sortCondList;           //5.Sort Order(S)
        params[6] = scrnMsg.Q;              //6.Output(R)

        return params;
    }

    private static String setSelectForRtrnCtrl(String glblCmpyCd, String slsDt) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    A.EZCANCELFLAG ");
        sb.append("   ,A.GLBL_CMPY_CD ");
        sb.append("   ,A.RTRN_CTRL_TP_VND_RELN_PK ");
        sb.append("   ,A.RTRN_CTRL_TP_CD ");
        sb.append("   ,C.RTRN_CTRL_TP_NM ");
        sb.append("   ,B.PRNT_VND_CD ");
        sb.append("   ,B.PRNT_VND_NM ");
        sb.append("   ,A.VND_CD ");
        sb.append("   ,B.VND_NM ");
        sb.append("   ,A.RTRN_WH_CD ");
        sb.append("   ,D.RTL_WH_NM RTRN_WH_NM ");
        sb.append("FROM ");
        sb.append("    RTRN_CTRL_TP_VND_RELN A ");
        sb.append("   ,PO_VND_V B ");
        sb.append("   ,RTRN_CTRL_TP C ");
        sb.append("   ,RTL_WH D ");
        sb.append("WHERE ");
        sb.append("    A.EZCANCELFLAG = '0' ");
        sb.append("AND A.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND A.EZCANCELFLAG = B.EZCANCELFLAG (+) ");
        sb.append("AND A.GLBL_CMPY_CD = B.GLBL_CMPY_CD (+) ");
        sb.append("AND A.VND_CD = B.VND_CD (+) ");
        sb.append("AND A.EZCANCELFLAG = C.EZCANCELFLAG (+) ");
        sb.append("AND A.GLBL_CMPY_CD = C.GLBL_CMPY_CD (+) ");
        sb.append("AND A.RTRN_CTRL_TP_CD = C.RTRN_CTRL_TP_CD (+) ");
        sb.append("AND A.EZCANCELFLAG = D.EZCANCELFLAG (+) ");
        sb.append("AND A.GLBL_CMPY_CD = D.GLBL_CMPY_CD (+) ");
        sb.append("AND A.RTRN_WH_CD = D.RTL_WH_CD (+) ");
        sb.append("AND D.EFF_FROM_DT(+) <= '").append(slsDt).append("' ");
        sb.append("AND D.EFF_THRU_DT(+) >= '").append(slsDt).append("' ");

        return sb.toString();
    }

    private static List<Object[]> setWhereListForRtrnCtrl(NLCL0250BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "ID";
        whereArray0[1] = "RTRN_CTRL_TP_VND_RELN_PK";
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnCtrlTpVndRelnPk_H1)) {
            whereArray0[2] = scrnMsg.rtrnCtrlTpVndRelnPk_H1.getValue().toPlainString();
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Return Ctrl";
        whereArray1[1] = "RTRN_CTRL_TP_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnCtrlTpCd_H1)) {
            whereArray1[2] = scrnMsg.rtrnCtrlTpCd_H1.getValue();
        }
        whereArray1[3] = FLG_OFF_N;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Return Ctrl Nm";
        whereArray2[1] = "RTRN_CTRL_TP_NM";
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnCtrlTpNm_H1)) {
            whereArray2[2] = scrnMsg.rtrnCtrlTpNm_H1.getValue();
        }
        whereArray2[3] = FLG_ON_Y;
        whereList.add(whereArray2);
        
        return whereList;
    }

    private static List<Object[]> setTblColumnListForRtrnCtrl() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray00 = new Object[4];
        columnArray00[0] = "ID";
        columnArray00[1] = "RTRN_CTRL_TP_VND_RELN_PK";
        columnArray00[2] = BigDecimal.valueOf(5);
        columnArray00[3] = FLG_ON_Y;
        columnList.add(columnArray00);
        
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Rtrn Ctrl";
        columnArray0[1] = "RTRN_CTRL_TP_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Rtrn Ctrl Nm";
        columnArray1[1] = "RTRN_CTRL_TP_NM";
        columnArray1[2] = BigDecimal.valueOf(21);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Rtrn Vnd";
        columnArray2[1] = "PRNT_VND_CD";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Rtrn Vnd Nm";
        columnArray3[1] = "PRNT_VND_NM";
        columnArray3[2] = BigDecimal.valueOf(21);
        columnArray3[3] = FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Rtrn Vnd Site";
        columnArray4[1] = "VND_CD";
        columnArray4[2] = BigDecimal.valueOf(10);
        columnArray4[3] = FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "Rtrn Vnd Site Nm";
        columnArray5[1] = "VND_NM";
        columnArray5[2] = BigDecimal.valueOf(21);
        columnArray5[3] = FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[4];
        columnArray6[0] = "Rtrn WH";
        columnArray6[1] = "RTRN_WH_CD";
        columnArray6[2] = BigDecimal.valueOf(10);
        columnArray6[3] = FLG_OFF_N;
        columnList.add(columnArray6);

        Object[] columnArray7 = new Object[4];
        columnArray7[0] = "Rtrn WH Nm";
        columnArray7[1] = "RTRN_WH_NM";
        columnArray7[2] = BigDecimal.valueOf(21);
        columnArray7[3] = FLG_OFF_N;
        columnList.add(columnArray7);

        return columnList;
    }

    public static List<Object[]> setSortListForRtrnCtrl() {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "RTRN_CTRL_TP_CD";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        Object[] sortCondArray1 = new Object[2];
        sortCondArray1[0] = "VND_CD";
        sortCondArray1[1] = "";
        sortCondList.add(sortCondArray1);

        
        return sortCondList;
    }
    // END 2017/12/18 S.Katsuma [QC#22469,ADD]
}
