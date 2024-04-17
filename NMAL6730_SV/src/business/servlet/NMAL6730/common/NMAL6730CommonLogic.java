/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6730.common;

import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_ADD_ATTRIBUTE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_ADD_INV_SRC;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_APPLY;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_APPROVE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_BLANK6;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_BLANK7;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_CLEAR;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_REJECT;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_RESET;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_RETURN;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_SAVE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_CMN_SUBMIT;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_DEL_ATTRIBUTE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_DEL_INV_SRC;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_GET_CLT_CUST;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_GET_CLT_PTFO;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_GET_COA_AFFL_NM;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_GET_COA_CH_NM;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_OPEN_WIN_CTAC_PSN;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_OPEN_WIN_RESRC;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_TEMPLATE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BUSINESS_ID;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.FUNC_ID_BILL_GENERAL_UPDATE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.FUNC_ID_DI_CHECK_ITEM_UPDATE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.FUNC_ID_FINANCIAL_UPDATE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.FUNC_ID_INV_UPDATE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.FUNC_ID_TAXING_UPDATE;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.SCREEN_ID;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.TAB_FINANCIAL;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.TAB_INV_BLLG;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.TAB_TAXING;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6730.NMAL6730BMsg;
import business.servlet.NMAL6730.NMAL6730Bean;
import business.servlet.NMAL6730.NMAL6730_ABMsg;
import business.servlet.NMAL6730.NMAL6730_KBMsg;
import business.servlet.NMAL6730.constant.NMAL6730Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            Fujitsu         Create          N/A
 * 2015/11/05   Fujitsu         N.Sugiura       Update          N/A
 * 2016/05/02   SRAA            Y.Chen          Update          QC#4324
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2016/06/09   SRAA            Y.Chen          Update          QC#7781
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2018/01/16   Hitachi         Y.Takeno        Update          QC#21734
 * 2018/01/25   Fujitsu         H.Ikeda         Update          QC#22095
 * 2018/08/01   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/13   Fujitsu         Y.Matsui        Update          QC#27222
 * 2018/08/21   Fujitsu         S.Ohki          Update          QC#27222-2
 * 2023/02/02   Hitachi         S.Nakatani      Update          QC#60811
 *</pre>
 */
public class NMAL6730CommonLogic {
    /**
     * <pre>
    * The initial state of the screen item is set.
    * </pre>
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6730BMsg
     * @param userId String
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6730BMsg scrnMsg, String userId) {
        initCommonButton(userProfileService, handler, scrnMsg);
        controlScreenFields(userProfileService, handler, scrnMsg, userId);
        setBgColor(scrnMsg); //Add QC#16521 2016/12/12
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     * @param scrnMsg NMAL6730BMsg
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6730BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (TAB_FINANCIAL.equals(scrnMsg.xxDplyTab.getValue())) {
            if (functionIds.contains(FUNC_ID_FINANCIAL_UPDATE)) {
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            }
            // Add Start 2018/08/21 QC#27222-2
            if (functionIds.contains(FUNC_ID_DI_CHECK_ITEM_UPDATE)) {
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            }
            // Add End 2018/08/21 QC#27222-2
        } else if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {
            if (functionIds.contains(FUNC_ID_INV_UPDATE)) {
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            }
        // Add Start 2018/08/01 QC#27222
        } else if (TAB_TAXING.equals(scrnMsg.xxDplyTab.getValue())) {
            if (functionIds.contains(FUNC_ID_TAXING_UPDATE)) {
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            }
        // Add End 2018/08/01 QC#27222
        }
    }

    /**
     * Control screen fields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6730BMsg
     * @param userId String
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6730BMsg scrnMsg, String userId) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (TAB_FINANCIAL.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabFinancial(userProfileService, handler, scrnMsg, userId, functionIds);

        } else if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabInvoice(userProfileService, handler, scrnMsg, userId, functionIds);
        // Add Start 2018/08/01 QC#27222
        } else if (TAB_TAXING.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabTaxing(userProfileService, handler, scrnMsg, userId, functionIds);
        // Add End 2018/08/01 QC#27222
        }
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6730BMsg
     * @param userId String
     */
    private static final void controlScreenTabFinancial(S21UserProfileService userProfileService, EZDCommonHandler handler
            , NMAL6730BMsg scrnMsg, String userId, List<String> functionIds) {

        scrnMsg.cltCustTpNm_F1.setInputProtected(true);
        scrnMsg.cltPtfoNm_F1.setInputProtected(true);
// QC#6382        
//        if (functionIds.contains(FUNC_ID_BILL_GENERAL_UPDATE)) {
//            scrnMsg.coaChCd_H1.setInputProtected(false);
//            scrnMsg.coaAfflCd_H1.setInputProtected(false);
//            handler.setButtonEnabled(BTN_OPEN_WIN_COA[0], true);
//        } else {
//            scrnMsg.coaChCd_H1.setInputProtected(true);
//            scrnMsg.coaAfflCd_H1.setInputProtected(true);
//            handler.setButtonEnabled(BTN_OPEN_WIN_COA[0], false);
//        }
        scrnMsg.coaChNm_H1.setInputProtected(true);
        // QC#9448
        // scrnMsg.coaAfflNm_H1.setInputProtected(true);
        if (functionIds.contains(FUNC_ID_BILL_GENERAL_UPDATE)) {
            scrnMsg.coaChCd_H1.setInputProtected(false);
            // QC#9448
            // scrnMsg.coaAfflCd_H1.setInputProtected(false);
            handler.setButtonEnabled(BTN_GET_COA_CH_NM[0], true);
            handler.setButtonEnabled(BTN_GET_COA_AFFL_NM[0], true);
        } else {
            scrnMsg.coaChCd_H1.setInputProtected(true);
            // QC#9448
            // scrnMsg.coaAfflCd_H1.setInputProtected(true);
            handler.setButtonEnabled(BTN_GET_COA_CH_NM[0], false);
            handler.setButtonEnabled(BTN_GET_COA_AFFL_NM[0], false);
        }

        if (functionIds.contains(FUNC_ID_FINANCIAL_UPDATE)) {
            scrnMsg.dsCustArTmplNm_F1.setInputProtected(false);
            scrnMsg.ccyCd_P1.setInputProtected(false);
            scrnMsg.custCrRtgCd_P1.setInputProtected(false);
            scrnMsg.crLimitAmt_F1.setInputProtected(false);
            scrnMsg.crChkReqTpCd_P1.setInputProtected(false);
            scrnMsg.crRiskClsCd_P1.setInputProtected(false);
            // START 2018/01/25 [QC#22095, ADD]
            scrnMsg.contrCrRiskClsCd_P1.setInputProtected(false);
            // END   2018/01/25 [QC#22095, ADD]
            scrnMsg.pmtTermCashDiscCd_P1.setInputProtected(false);
            scrnMsg.ovrdPmtTermFlg_F1.setInputProtected(false);
            scrnMsg.cashOrCcReqFlg_F1.setInputProtected(false);
            // QC#4324
            scrnMsg.custHardHldFlg_F1.setInputProtected(false);
            scrnMsg.remId_F1.setInputProtected(false);

            scrnMsg.arStmtFlg_F1.setInputProtected(false);
            scrnMsg.arStmtIssCycleCd_P1.setInputProtected(false);
            // START 2018/01/16 [QC#21734, DEL]
            // scrnMsg.dunFlg_F1.setInputProtected(false);
            // END   2018/01/16 [QC#21734, DEL]
            scrnMsg.cltCustTpCd_F1.setInputProtected(false);
            scrnMsg.cltPtfoCd_F1.setInputProtected(false);
            scrnMsg.dsCltAcctStsCd_P1.setInputProtected(false);
            scrnMsg.lateFeeAmt_F1.setInputProtected(false);
            // Start 2023/2/02 S.Naktani [QC#60811, ADD]
            scrnMsg.mlyLateFeeRate_F1.setInputProtected(false);
            // End 2023/2/02 S.Naktani [QC#60811, ADD]

            // Del Start 2018/08/01 QC#27222
//            scrnMsg.dsCustTaxCd_F1.setInputProtected(false);
//            scrnMsg.dsCustTaxCalcCd_P1.setInputProtected(false);
//            scrnMsg.dsTaxExemFlg_F1.setInputProtected(false);
//            scrnMsg.dsExemExprDt_F1.setInputProtected(false);
//            scrnMsg.dsTaxGrpExemCd_P1.setInputProtected(false);
            // Del End 2018/08/01 QC#27222
            // Del Start 2018/08/21 QC#27222-2
//            scrnMsg.dsTaxPrntTpCd_P1.setInputProtected(false);
//            // Add Start 2018/08/13 QC#27222
//            scrnMsg.autoCashHrchCd_P1.setInputProtected(false);
//            // Add End 2018/08/13 QC#27222
            // Del End 2018/08/21 QC#27222-2

            handler.setButtonEnabled(BTN_TEMPLATE[0], true);
            handler.setButtonEnabled(BTN_GET_CLT_CUST[0], true);
            handler.setButtonEnabled(BTN_GET_CLT_PTFO[0], true);

            scrnMsg.xxLinkProt_F1.setInputProtected(false);
            scrnMsg.xxLinkProt_F2.setInputProtected(false);
            scrnMsg.xxLinkProt_F3.setInputProtected(false);
            scrnMsg.xxLinkProt_F4.setInputProtected(false);
        } else {

            scrnMsg.dsCustArTmplNm_F1.setInputProtected(true);
            scrnMsg.ccyCd_P1.setInputProtected(true);
            scrnMsg.custCrRtgCd_P1.setInputProtected(true);
            scrnMsg.crLimitAmt_F1.setInputProtected(true);
            scrnMsg.crChkReqTpCd_P1.setInputProtected(true);
            scrnMsg.crRiskClsCd_P1.setInputProtected(true);
            // START 2018/01/25 [QC#22095, ADD]
            scrnMsg.contrCrRiskClsCd_P1.setInputProtected(true);
            // END   2018/01/25 [QC#22095, ADD]
            scrnMsg.pmtTermCashDiscCd_P1.setInputProtected(true);
            scrnMsg.ovrdPmtTermFlg_F1.setInputProtected(true);
            scrnMsg.cashOrCcReqFlg_F1.setInputProtected(true);
            // QC#4324
            scrnMsg.custHardHldFlg_F1.setInputProtected(true);
            scrnMsg.remId_F1.setInputProtected(true);

            scrnMsg.arStmtFlg_F1.setInputProtected(true);
            scrnMsg.arStmtIssCycleCd_P1.setInputProtected(true);
            // START 2018/01/16 [QC#21734, DEL]
            // scrnMsg.dunFlg_F1.setInputProtected(true);
            // END   2018/01/16 [QC#21734, DEL]
            scrnMsg.cltCustTpCd_F1.setInputProtected(true);
            scrnMsg.cltPtfoCd_F1.setInputProtected(true);
            scrnMsg.dsCltAcctStsCd_P1.setInputProtected(true);
            scrnMsg.lateFeeAmt_F1.setInputProtected(true);
            // Start 2023/2/02 S.Naktani [QC#60811, ADD]
            scrnMsg.mlyLateFeeRate_F1.setInputProtected(true);
            // End 2023/2/02 S.Naktani [QC#60811, ADD]

            // Del Start 2018/08/01 QC#27222
//            scrnMsg.dsCustTaxCd_F1.setInputProtected(true);
//            scrnMsg.dsCustTaxCalcCd_P1.setInputProtected(true);
//            scrnMsg.dsTaxExemFlg_F1.setInputProtected(true);
//            scrnMsg.dsExemExprDt_F1.setInputProtected(true);
//            scrnMsg.dsTaxGrpExemCd_P1.setInputProtected(true);
            // Del End 2018/08/01 QC#27222
            // Del Start 2018/08/21 QC#27222-2
//            scrnMsg.dsTaxPrntTpCd_P1.setInputProtected(true);
//            // Add Start 2018/08/13 QC#27222
//            scrnMsg.autoCashHrchCd_P1.setInputProtected(true);
//            // Add End 2018/08/13 QC#27222
            // Del End 2018/08/21 QC#27222-2

            handler.setButtonEnabled(BTN_TEMPLATE[0], false);
            handler.setButtonEnabled(BTN_GET_CLT_CUST[0], false);
            handler.setButtonEnabled(BTN_GET_CLT_PTFO[0], false);

            scrnMsg.xxLinkProt_F1.setInputProtected(true);
            scrnMsg.xxLinkProt_F2.setInputProtected(true);
            scrnMsg.xxLinkProt_F3.setInputProtected(true);
            scrnMsg.xxLinkProt_F4.setInputProtected(true);
        }

        // Add Start 2018/08/21 QC#27222-2
        if (functionIds.contains(FUNC_ID_DI_CHECK_ITEM_UPDATE)) {
            scrnMsg.dsTaxExemFlg_F1.setInputProtected(false);
            scrnMsg.dsExemExprDt_F1.setInputProtected(false);
            scrnMsg.dsTaxPrntTpCd_P1.setInputProtected(false);
            scrnMsg.autoCashHrchCd_P1.setInputProtected(false);
        } else {
        	scrnMsg.dsTaxExemFlg_F1.setInputProtected(true);
            scrnMsg.dsExemExprDt_F1.setInputProtected(true);
            scrnMsg.dsTaxPrntTpCd_P1.setInputProtected(true);
            scrnMsg.autoCashHrchCd_P1.setInputProtected(true);
        }
        // Add End 2018/08/21 QC#27222-2
    }

    /**
     * controlScreenDetailFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6730BMsg
     * @param userId String
     */
    private static final void controlScreenTabInvoice(S21UserProfileService userProfileService
            , EZDCommonHandler handler, NMAL6730BMsg scrnMsg, String userId, List<String> functionIds) {

// QC#6382
//        // Mod Start CSA #4332 2016/02/23
////        scrnMsg.coaChCd_H1.setInputProtected(true);
////        scrnMsg.coaAfflCd_H1.setInputProtected(true);
////        handler.setButtonEnabled(BTN_OPEN_WIN_COA[0], false);
//        if (functionIds.contains(FUNC_ID_BILL_GENERAL_UPDATE)) {
//            scrnMsg.coaChCd_H1.setInputProtected(false);
//            scrnMsg.coaAfflCd_H1.setInputProtected(false);
//            handler.setButtonEnabled(BTN_OPEN_WIN_COA[0], true);
//        } else {
//            scrnMsg.coaChCd_H1.setInputProtected(true);
//            scrnMsg.coaAfflCd_H1.setInputProtected(true);
//            handler.setButtonEnabled(BTN_OPEN_WIN_COA[0], false);
//        }
//        // Mod End CSA #4332 2016/02/23
        scrnMsg.coaChNm_H1.setInputProtected(true);
        // QC#9448
        // scrnMsg.coaAfflNm_H1.setInputProtected(true);
        if (functionIds.contains(FUNC_ID_BILL_GENERAL_UPDATE)) {
            scrnMsg.coaChCd_H1.setInputProtected(false);
            // QC#9448
            // scrnMsg.coaAfflCd_H1.setInputProtected(false);
            handler.setButtonEnabled(BTN_GET_COA_CH_NM[0], true);
            handler.setButtonEnabled(BTN_GET_COA_AFFL_NM[0], true);
        } else {
            scrnMsg.coaChCd_H1.setInputProtected(true);
            // QC#9448
            // scrnMsg.coaAfflCd_H1.setInputProtected(true);
            handler.setButtonEnabled(BTN_GET_COA_CH_NM[0], false);
            handler.setButtonEnabled(BTN_GET_COA_AFFL_NM[0], false);
        }

        if (functionIds.contains(FUNC_ID_FINANCIAL_UPDATE)) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).custInvSrcCd_P1.setInputProtected(false);
                scrnMsg.A.no(i).custBllgTpCd_P1.setInputProtected(false);
                scrnMsg.A.no(i).custConslTermCd_P1.setInputProtected(false);
                scrnMsg.A.no(i).custBllgVcleCd_P1.setInputProtected(false);
                // QC#7781
                scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxCustInvRuleRcpntTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxGenlFldAreaTxt_A2.setInputProtected(false);
                scrnMsg.A.no(i).xxCustInvRuleRcpntTxt_A2.setInputProtected(true);
                scrnMsg.A.no(i).custEmlMsgTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).defInvGrpCd_P1.setInputProtected(false);
                scrnMsg.A.no(i).invGrpNum_A1.setInputProtected(false);

                handler.setButtonEnabled(BTN_OPEN_WIN_RESRC[0], i, true);
                handler.setButtonEnabled(BTN_OPEN_WIN_CTAC_PSN[0], i, true);
            }

            scrnMsg.defBaseCycleCd_P1.setInputProtected(false);
            scrnMsg.defBaseTpCd_P1.setInputProtected(false);
            scrnMsg.defUsgCycleCd_P1.setInputProtected(false);
            scrnMsg.defUsgTpCd_P1.setInputProtected(false);
            scrnMsg.dsBillTgtrFlg_I1.setInputProtected(false);

            handler.setButtonEnabled(BTN_ADD_INV_SRC[0], true);
            handler.setButtonEnabled(BTN_DEL_INV_SRC[0], true);



            for (int i = 0; i < scrnMsg.K.length(); i++) {
                scrnMsg.K.no(i).bllgAttrbNm_K1.setInputProtected(false);
                scrnMsg.K.no(i).bllgAttrbValTxt_K1.setInputProtected(false);
                scrnMsg.K.no(i).bllgAttrbEnblFlg_K1.setInputProtected(false);
                scrnMsg.K.no(i).bllgAttrbReqFlg_K1.setInputProtected(false);
            }
            handler.setButtonEnabled(BTN_ADD_ATTRIBUTE[0], true);
            handler.setButtonEnabled(BTN_DEL_ATTRIBUTE[0], true);
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).custInvSrcCd_P1.setInputProtected(true);
                scrnMsg.A.no(i).custBllgTpCd_P1.setInputProtected(true);
                scrnMsg.A.no(i).custConslTermCd_P1.setInputProtected(true);
                scrnMsg.A.no(i).custBllgVcleCd_P1.setInputProtected(true);
                // QC#7781
                scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxCustInvRuleRcpntTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).xxGenlFldAreaTxt_A2.setInputProtected(true);
                scrnMsg.A.no(i).xxCustInvRuleRcpntTxt_A2.setInputProtected(true);
                scrnMsg.A.no(i).custEmlMsgTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).defInvGrpCd_P1.setInputProtected(true);
                scrnMsg.A.no(i).invGrpNum_A1.setInputProtected(true);

                handler.setButtonEnabled(BTN_OPEN_WIN_RESRC[0], i, false);
                handler.setButtonEnabled(BTN_OPEN_WIN_CTAC_PSN[0], i, false);
            }

            scrnMsg.defBaseCycleCd_P1.setInputProtected(true);
            scrnMsg.defBaseTpCd_P1.setInputProtected(true);
            scrnMsg.defUsgCycleCd_P1.setInputProtected(true);
            scrnMsg.defUsgTpCd_P1.setInputProtected(true);
            scrnMsg.dsBillTgtrFlg_I1.setInputProtected(true);

            handler.setButtonEnabled(BTN_ADD_INV_SRC[0], false);
            handler.setButtonEnabled(BTN_DEL_INV_SRC[0], false);



            for (int i = 0; i < scrnMsg.K.length(); i++) {
                scrnMsg.K.no(i).bllgAttrbNm_K1.setInputProtected(true);
                scrnMsg.K.no(i).bllgAttrbValTxt_K1.setInputProtected(true);
                scrnMsg.K.no(i).bllgAttrbEnblFlg_K1.setInputProtected(true);
                scrnMsg.K.no(i).bllgAttrbReqFlg_K1.setInputProtected(true);
            }
            handler.setButtonEnabled(BTN_ADD_ATTRIBUTE[0], false);
            handler.setButtonEnabled(BTN_DEL_ATTRIBUTE[0], false);
        }
    }

    // Add Start 2018/08/01 QC#27222
    /**
     * controlScreenTabTaxing
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6730BMsg
     * @param userId String
     */
    private static final void controlScreenTabTaxing(S21UserProfileService userProfileService, EZDCommonHandler handler
            , NMAL6730BMsg scrnMsg, String userId, List<String> functionIds) {

        if (functionIds.contains(FUNC_ID_TAXING_UPDATE)) {
        	scrnMsg.dsTaxGrpExemCd_P1.setInputProtected(false);
        } else {
        	scrnMsg.dsTaxGrpExemCd_P1.setInputProtected(true);
        }
    }
    // Add End 2018/08/01 QC#27222

    /**
     * <pre>
    * Check addCheckItem return UPDATE
    * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NMAL6730BMsg scrnMsg) {
        addHeaderCheckItem(scrnMsg);
        if (TAB_FINANCIAL.equals(scrnMsg.xxDplyTab.getValue())) {
          addTabLinkageCheckItem(scrnMsg);
        } else if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {
          addTabLinkageCheckItem(scrnMsg);
          // Add Start 2018/08/01 QC#27222
        } else if (TAB_TAXING.equals(scrnMsg.xxDplyTab.getValue())) {
            addTabLinkageCheckItem(scrnMsg);
          // Add End 2018/08/01 QC#27222
        }
    }

    /**
     * addHeaderCheckItem
     * @param scrnMsg NMAL6730BMsg
     */
    public static void addHeaderCheckItem(NMAL6730BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.coaChCd_H1);

        // boolean hasAcct = false;
        // if
        // (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_H1.getValue()))
        // {
        // hasAcct = true;
        // }
        //        
        //        
        // scrnMsg.addCheckItem(scrnMsg.ctryCd_H1);
        //        
        // scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
        // scrnMsg.addCheckItem(scrnMsg.scdLineAddr_H1);
        // scrnMsg.addCheckItem(scrnMsg.thirdLineAddr_H1);
        // scrnMsg.addCheckItem(scrnMsg.frthLineAddr_H1);
        //
        // scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        // scrnMsg.addCheckItem(scrnMsg.stCd_H1);
        // scrnMsg.addCheckItem(scrnMsg.postCd_H1);
        // scrnMsg.addCheckItem(scrnMsg.cntyNm_H1);
        // scrnMsg.addCheckItem(scrnMsg.provNm_H1);
        //        
        // scrnMsg.addCheckItem(scrnMsg.locNm_H1);
        // scrnMsg.addCheckItem(scrnMsg.telNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsOrigSysRefTxt_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsDataSrcTxt_H1);
        //        
        // if (hasAcct) {
        // scrnMsg.addCheckItem(scrnMsg.dsCustLocStsCd_H3);
        // scrnMsg.addCheckItem(scrnMsg.dunsNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsUltDunsNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsCustIndyCd_H3);
        // scrnMsg.addCheckItem(scrnMsg.dsCustLocClsCd_H3);
        // }
        //        
        // if
        // (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_HB.getValue()))
        // {
        // scrnMsg.addCheckItem(scrnMsg.effFromDt_HB);
        // scrnMsg.addCheckItem(scrnMsg.effThruDt_HB);
        // }
        // if
        // (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_HS.getValue()))
        // {
        // scrnMsg.addCheckItem(scrnMsg.effFromDt_HS);
        // scrnMsg.addCheckItem(scrnMsg.effThruDt_HS);
        // scrnMsg.addCheckItem(scrnMsg.billToCustCd_HS);
        // }
        //        
        // if (hasAcct) {
        // scrnMsg.addCheckItem(scrnMsg.dsEquipPoReqFlg_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsEquipBlktPoNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsEquipPoExprDt_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsEquipEffCd_H1);
        //    
        // scrnMsg.addCheckItem(scrnMsg.dsContrPoReqFlg_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsContrBlktPoNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsContrPoExprDt_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsContrEffCd_H1);
        //            
        // scrnMsg.addCheckItem(scrnMsg.dsSvcPoReqFlg_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsSvcBlktPoNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsSvcPoExprDt_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsSvcEffCd_H1);
        //    
        // scrnMsg.addCheckItem(scrnMsg.dsSplyPoReqFlg_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsSplyBlktPoNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsSplyPoExprDt_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsSplyEffCd_H1);
        //            
        // scrnMsg.addCheckItem(scrnMsg.dsDefBaseBllgLocNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsDefUsgBllgLocNum_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsSplyCrCardReqFlg_H1);
        // scrnMsg.addCheckItem(scrnMsg.dsSplyOvngtAllwFlg_H1);
        // }
    }

    /**
     * addDetailCheckItem
     * @param scrnMsg NMAL6730BMsg
     */
    public static void addTabLinkageCheckItem(NMAL6730BMsg scrnMsg) {

        // Add Start CSA#4332 2016/02/23
        scrnMsg.addCheckItem(scrnMsg.crLimitAmt_F1);
        scrnMsg.addCheckItem(scrnMsg.remId_F1);
        scrnMsg.addCheckItem(scrnMsg.cltCustTpCd_F1);
        scrnMsg.addCheckItem(scrnMsg.cltPtfoCd_F1);
        scrnMsg.addCheckItem(scrnMsg.lateFeeAmt_F1);
        // Start 2023/2/02 S.Naktani [QC#60811, ADD]
        scrnMsg.addCheckItem(scrnMsg.mlyLateFeeRate_F1);
        // End 2023/2/02 S.Naktani [QC#60811, ADD]
        // Del Start 2018/08/01 QC#27222
//        scrnMsg.addCheckItem(scrnMsg.dsCustTaxCd_F1);
        // Mod Start 2018/08/21 QC#27222-2 Uncomment
        scrnMsg.addCheckItem(scrnMsg.dsExemExprDt_F1);
        // Mod End 2018/08/21 QC#27222-2
        // Del End 2018/08/01 QC#27222

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL6730_ABMsg abMsg = scrnMsg.A.no(i);
            // QC#7781
            scrnMsg.addCheckItem(abMsg.xxGenlFldAreaTxt_A1);
            scrnMsg.addCheckItem(abMsg.xxGenlFldAreaTxt_A2);
            scrnMsg.addCheckItem(abMsg.custEmlMsgTxt_A1);
            scrnMsg.addCheckItem(abMsg.invGrpNum_A1);
        }
        // Add End CSA#4332 2016/02/23

        // for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        // NMAL6730_ABMsg abMsg = scrnMsg.A.no(i);
        // scrnMsg.addCheckItem(abMsg.mdseCd_A1);
        // scrnMsg.addCheckItem(abMsg.svcInvQty_A1);
        // scrnMsg.addCheckItem(abMsg.dealUnitPrcAmt_A1);
        // scrnMsg.addCheckItem(abMsg.bllgPerFromDt_A1);
        // scrnMsg.addCheckItem(abMsg.bllgPerThruDt_A1);
        // scrnMsg.addCheckItem(abMsg.shipToCustCd_A1);
        //
        // }
    }
    /**
     * clearParams
     * @param scrnMsg NMAL6730BMsg
     */
    public static void clearParams(NMAL6730BMsg scrnMsg) {
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();

    }
    // Add Start CSA#4332 2016/02/23
    /**
     * clearMandatoryError
     * @param scrnMsg NMAL6700BMsg
     */
    public static void clearMandatoryError(NMAL6730BMsg scrnMsg) {

        if (scrnMsg.coaChCd_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6730Bean.coaChCd_H1);
            if (chkMandatoryError(ezdMsgInfo)) {
                scrnMsg.coaChCd_H1.clearErrorInfo();
            }
        }

        if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NMAL6730_ABMsg abMsg = scrnMsg.A.no(i);
                if (abMsg.custInvSrcCd_P1.isError()) {
                    EZDMessageInfo ezdMsgInfo = abMsg.getErrorInfo(NMAL6730Bean.custInvSrcCd_P1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        abMsg.custInvSrcCd_P1.clearErrorInfo();
                    }
                }

                if (abMsg.custBllgTpCd_P1.isError()) {
                    EZDMessageInfo ezdMsgInfo = abMsg.getErrorInfo(NMAL6730Bean.custBllgTpCd_P1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        abMsg.custBllgTpCd_P1.clearErrorInfo();
                    }
                }

                if (abMsg.custBllgVcleCd_P1.isError()) {
                    EZDMessageInfo ezdMsgInfo = abMsg.getErrorInfo(NMAL6730Bean.custBllgVcleCd_P1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        abMsg.custBllgVcleCd_P1.clearErrorInfo();
                    }
                }
            }

            for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
                NMAL6730_KBMsg kbMsg = scrnMsg.K.no(i);
                if (kbMsg.bllgAttrbNm_K1.isError()) {
                    EZDMessageInfo ezdMsgInfo = kbMsg.getErrorInfo(NMAL6730Bean.bllgAttrbNm_K1);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        kbMsg.bllgAttrbNm_K1.clearErrorInfo();
                    }
                }

                if (kbMsg.custEffLvlCd_K3.isError()) {
                    EZDMessageInfo ezdMsgInfo = kbMsg.getErrorInfo(NMAL6730Bean.custEffLvlCd_K3);
                    if (chkMandatoryError(ezdMsgInfo)) {
                        kbMsg.custEffLvlCd_K3.clearErrorInfo();
                    }
                }
            }
        }
        if (TAB_FINANCIAL.equals(scrnMsg.xxDplyTab.getValue())) {
            if (scrnMsg.remId_F1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo(NMAL6730Bean.remId_F1);
                if (chkMandatoryError(ezdMsgInfo)) {
                    scrnMsg.remId_F1.clearErrorInfo();
                }
            }
        }
    }

    /**
     * chkMandatoryError
     * @param ezdMsgInfo EZDMessageInfo
     * @return boolean
     */
    public static boolean chkMandatoryError(EZDMessageInfo ezdMsgInfo) {

        if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
            return true;
        }
        return false;

    }

    // Add End CSA#4332 2016/02/23

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6730BMsg
     * @return Object[]
     */
    public static Object[] setParamForResourceSearchPopup(NMAL6730BMsg scrnMsg) {

        // QC#7781
        Object[] params = new Object[10];
        clearParams(scrnMsg);
        ZYPTableUtil.clear(scrnMsg.Q);

        NMAL6730_ABMsg aBMsg = scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt());
        String psnCdList = aBMsg.xxGenlFldAreaTxt_A1.getValue();
        String[] psnCdArray = NMAL6730CommonLogic.splitByComma(psnCdList);
        EZDBStringItem[] inputs = new EZDBStringItem[scrnMsg.Q.length()];
        EZDBStringItem[] outputs = new EZDBStringItem[scrnMsg.Q.length()];
        for (int i = 0; i < scrnMsg.Q.length(); i++) {
            if (i < psnCdArray.length) {
                scrnMsg.Q.no(i).psnCd_Q1.setValue(psnCdArray[i]);
            }
            inputs[i] = scrnMsg.Q.no(i).psnCd_Q1;
            outputs[i] = scrnMsg.Q.no(i).psnCd_Q2;
        }

        scrnMsg.xxPopPrm_P6.setValue("M");
        scrnMsg.xxPopPrm_P7.setValue(ZYPConstant.CHKBOX_ON_Y);

        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6; // mode
        params[7] = scrnMsg.xxPopPrm_P7; // has email flag 
        params[8] = inputs;
        params[9] = outputs;

        return params;
    }

    /**
     * setBgColorForAddressTab
     * @param scrnMsg NMAL6730BMsg
     */
    public static void setBgColor(NMAL6730BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        if (TAB_INV_BLLG.equals(scrnMsg.xxDplyTab.getValue())) {
            setBgColorForInv(scrnMsg);
            setBgColorForAttribute(scrnMsg);
        }
    }

    /**
     * setBgColorForLocation
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForInv(NMAL6730BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }
    }

    /**
     * setBgColorForRelationship
     * @param scrnMsg NMAL6700BMsg
     */
    public static void setBgColorForAttribute(NMAL6730BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("K", scrnMsg.K);
        if (scrnMsg.K.getValidCount() > 0) {
            tblColor.setAlternateRowsBG("K", scrnMsg.K);
        }
    }
    
    // QC#7781
    /**
     * @param str String
     * @return String[]
     */
    public static String[] splitByComma(String str){
        String[] array = str.split(NMAL6730Constant.CHAR_COMMA);
        List<String> list = new ArrayList<String>();
        for(String s : array){
            if(ZYPCommonFunc.hasValue(s)){
                list.add(s);
            }
        }
        return list.toArray(new String[]{});
    }
}
