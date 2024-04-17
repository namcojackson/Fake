/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1040.common;

import static business.servlet.NLCL1040.constant.NLCL1040Constant.BIZ_APP_ID;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_ADD_TAG;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_ANALYZE;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_1;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_10;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_2;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_3;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_4;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_5;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_6;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_7;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_8;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_CMN_BTN_9;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_DELETE;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_DELETE_TAG;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_EDIT_CLASS;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_EDIT_TAG;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_IMPORT_NEW;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_ITEM;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_REFLESH;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_SAVE;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_SEARCH;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_SEARCH_ITEM;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.BTN_SEARCH_LINE_ITEM_NAME;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.EVENT_NM_EDIT_CLASS;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.FUNC_EDIT;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.ITEM_SEARCH_AL;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.MDSE_LEN;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_10;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_14;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_15;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_16;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_2;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_3;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_4;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_6;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_7;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_8;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.POP_UP_PARAM_9;
import static business.servlet.NLCL1040.constant.NLCL1040Constant.SCRN_ID;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NLCL1040.NLCL1040CMsg;
import business.servlet.NLCL1040.NLCL1040BMsg;
import business.servlet.NLCL1040.NLCL1040_PBMsgArray;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLCL1040 Inventory ABC Analysis Setup
 * Function Name : NLCL1040CommonLogic
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            T.Hakodate      Create          N/A
 *</pre>
 */
public class NLCL1040CommonLogic {

    /**
     * setParamForNLCL1050
     * @param scrnMsg NLCL1040BMsg
     * @param bizMsg NLCL1040CMsg
     * @return Object[] params
     */
    public static Object[] setParamForNLCL1050(NLCL1040BMsg scrnMsg, NLCL1040CMsg bizMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        if (EVENT_NM_EDIT_CLASS.equals(scrnMsg.xxMntEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).srchOptTxt_P, bizMsg.abcAnlsClsNm.getValue());
        } else {
            scrnMsg.P.no(0).srchOptTxt_P.clear();
        }

        Object[] params = toArray_popup(scrnMsg.P, 1);

        return params;

    }

    /**
     * setParamForZYPL0210
     * @param scrnMsg NLCL1040BMsg
     * @return Object[] params
     */
    public static Object[] setParamForZYPL0210(NLCL1040BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).srchOptTxt_P, "NLCB110001");
        Object[] params = toArray_popup(scrnMsg.P, 1);

        return params;

    }

    /**
     * setParamForNMAL6800
     * @param scrnMsg NLCL1040BMsg
     * @return Object[]
     */
    public static Object[] setParamForNMAL6800(NLCL1040BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd) && scrnMsg.mdseCd.getValue().length() > MDSE_LEN) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).srchOptTxt_P, scrnMsg.mdseCd.getValue().substring(0, MDSE_LEN));

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).srchOptTxt_P, scrnMsg.mdseCd);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_9).srchOptTxt_P, ITEM_SEARCH_AL);
        Object[] params = toArray_popup(scrnMsg.P, POP_UP_PARAM_10);

        return params;
    }

    /**
     * setParamForNMAL6800ForDetail
     * @param scrnMsg NLCL1040BMsg
     * @param selectNum int
     * @return Object[]
     */
    public static Object[] setParamForNMAL6800ForDetail(NLCL1040BMsg scrnMsg, int selectNum) {

        ZYPTableUtil.clear(scrnMsg.P);

        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(selectNum).mdseCd_A) && scrnMsg.A.no(selectNum).mdseCd_A.getValue().length() > MDSE_LEN) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).srchOptTxt_P, scrnMsg.A.no(selectNum).mdseCd_A.getValue().substring(0, MDSE_LEN));

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).srchOptTxt_P, scrnMsg.A.no(selectNum).mdseCd_A);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_9).srchOptTxt_P, ITEM_SEARCH_AL);
        Object[] params = toArray_popup(scrnMsg.P, POP_UP_PARAM_10);

        return params;
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

    /**
     * setParamForNPAL1010
     * @param scrnMsg NLCL1040BMsg
     * @param isRtlWhLink boolean
     * @return Object[]
     */
    public static Object[] setParamForNPAL1010(NLCL1040BMsg scrnMsg, boolean isRtlWhLink) {

        String sbLocRoleTpList = NMXC100001EnableWH.getLocRoleTpForPopup(scrnMsg.glblCmpyCd.getValue(), BIZ_APP_ID);

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_2).srchOptTxt_P, sbLocRoleTpList);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_3).srchOptTxt_P, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_4).srchOptTxt_P, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_6).srchOptTxt_P, scrnMsg.rtlWhCdSrchTxt_RW);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_7).srchOptTxt_P, scrnMsg.rtlWhNmSrchTxt_RW);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_14).srchOptTxt_P, scrnMsg.rtlWhCatgCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_15).srchOptTxt_P, ZYPConstant.FLG_ON_Y);

        if (isRtlWhLink) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_10).srchOptTxt_P, ZYPConstant.FLG_ON_Y);

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_8).srchOptTxt_P, scrnMsg.rtlSwhCdSrchTxt_SW);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_UP_PARAM_10).srchOptTxt_P, ZYPConstant.FLG_OFF_N);
        }

        Object[] params = toArray_popup(scrnMsg.P, POP_UP_PARAM_16);

        return params;
    }

    /**
     * @param p
     * @param size
     * @return
     */
    private static Object[] toArray_popup(NLCL1040_PBMsgArray p, int size) {

        Object[] param = new Object[size];

        for (int i = 0; i < size; i++) {

            param[i] = p.no(i).srchOptTxt_P;
        }

        return param;
    }

    /**
     * ctrlScrnItemDisplay
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL1040BMsg
     */
    public static void ctrlScrnItemDisplay(EZDCommonHandler handler, NLCL1040BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        S21TableColorController tblColorStkSts = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColorStkSts.setAlternateRowsBG("S", scrnMsg.S);

        S21TableColorController tblColorLine = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColorLine.setAlternateRowsBG("A", scrnMsg.A);

        if (isEntryGranted()) {
            // Edit Mode
            ctrlScrnItemDisplayEdit(handler, scrnMsg);
        } else {
            // Reference Mode
            ctrlScrnItemDisplayReference(handler, scrnMsg);
        }
    }

    /**
     * Control Screen Item Display Edit Mode
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL1040BMsg
     */
    public static void ctrlScrnItemDisplayEdit(EZDCommonHandler handler, NLCL1040BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.abcAsgPk)) {
            // After Search
            // Header
            scrnMsg.abcAsgNm.setInputProtected(false);
            scrnMsg.abcAsgDescTxt.setInputProtected(false);
            scrnMsg.rtlWhCatgCd_H1.setInputProtected(false);
            scrnMsg.rtlWhCdSrchTxt_RW.setInputProtected(false);
            scrnMsg.rtlWhNmSrchTxt_RW.setInputProtected(true);
            scrnMsg.rtlSwhCdSrchTxt_SW.setInputProtected(false);
            scrnMsg.mdseItemTpCd_H1.setInputProtected(false);
            scrnMsg.xxLinkAncr_W.setInputProtected(false);
            scrnMsg.xxLinkAncr_SW.setInputProtected(false);
            scrnMsg.abcAnlsCritCd_H1.setInputProtected(false);
            scrnMsg.effFromDt.setInputProtected(false);
            scrnMsg.effThruDt.setInputProtected(false);
            scrnMsg.abcAnlsClsNum_H1.setInputProtected(false);
            scrnMsg.abcAnlsRqstStsDescTxt.setInputProtected(true);
            scrnMsg.xxScrItem30Txt.setInputProtected(true);

            for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
                scrnMsg.S.no(i).xxChkBox_SS.setInputProtected(false);
            }

            // Header1
            handler.setButtonEnabled(BTN_SEARCH, true);
            handler.setButtonEnabled(BTN_IMPORT_NEW, true);
            handler.setButtonEnabled(BTN_SAVE, true);
            handler.setButtonEnabled(BTN_ANALYZE, true);
            handler.setButtonEnabled(BTN_DELETE, true);
            handler.setButtonEnabled(BTN_REFLESH, true);
            handler.setButtonEnabled(BTN_EDIT_CLASS, true);

            // Detail Footer
            handler.setButtonEnabled(BTN_ADD_TAG, true);

            // common button protection
            // 0 : inactive
            // 1 : active
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

            if (scrnMsg.A.getValidCount() == 0) {
                // Item Search
                scrnMsg.xxLinkAncr_I.setInputProtected(true);
                scrnMsg.mdseCd.setInputProtected(true);
                handler.setButtonEnabled(BTN_SEARCH_ITEM, false);
                // Edit Tag
                handler.setButtonEnabled(BTN_EDIT_TAG, false);
                // Delete Line
                handler.setButtonEnabled(BTN_DELETE_TAG, false);
                // submit
                handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            } else if (scrnMsg.A.getValidCount() > 0) {
                // Item Search
                scrnMsg.mdseCd.setInputProtected(false);
                scrnMsg.xxLinkAncr_I.setInputProtected(false);
                handler.setButtonEnabled(BTN_SEARCH_ITEM, true);
                // Edit Tag
                handler.setButtonEnabled(BTN_EDIT_TAG, true);
                // Delete Line
                handler.setButtonEnabled(BTN_DELETE_TAG, true);
                // submit
                handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
            }

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).mdseCd_A.setInputProtected(true);
                scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhCatgCd_A.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhCd_A.setInputProtected(true);
                scrnMsg.A.no(i).rtlWhNm_A.setInputProtected(true);
                scrnMsg.A.no(i).rtlSwhCd_A.setInputProtected(true);
                scrnMsg.A.no(i).stkStsCd_A.setInputProtected(true);
                scrnMsg.A.no(i).curInvtyQty_A.setInputProtected(true);
                scrnMsg.A.no(i).curInvtyCostAmt_A.setInputProtected(true);
                scrnMsg.A.no(i).histInvtyTrxQty_A.setInputProtected(true);
                scrnMsg.A.no(i).histInvtyTrxCostAmt_A.setInputProtected(true);
                scrnMsg.A.no(i).histInvtyTrxRecCnt_A.setInputProtected(true);

                // Edit Tag Line
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {
                    scrnMsg.A.no(i).abcAnlsClsTagCd_A.setInputProtected(false);
                } else {
                    scrnMsg.A.no(i).abcAnlsClsTagCd_A.setInputProtected(true);
                }

                // Add New Line
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).processedFlag_A.getValue())) {
                    scrnMsg.A.no(i).mdseCd_A.setInputProtected(false);
                    scrnMsg.A.no(i).rtlWhCatgCd_A.setInputProtected(false);
                    scrnMsg.A.no(i).rtlWhCd_A.setInputProtected(false);
                    scrnMsg.A.no(i).rtlSwhCd_A.setInputProtected(false);
                    scrnMsg.A.no(i).stkStsCd_A.setInputProtected(false);
                    scrnMsg.A.no(i).abcAnlsClsTagCd_A.setInputProtected(false);
                    handler.setButtonEnabled(BTN_ITEM, i, true);
                    handler.setButtonEnabled(BTN_SEARCH_LINE_ITEM_NAME, i, true);
                } else {
                    handler.setButtonEnabled(BTN_ITEM, i, false);
                    handler.setButtonEnabled(BTN_SEARCH_LINE_ITEM_NAME, i, false);
                }

                // Set Application Fraction Digit
                scrnMsg.A.no(i).curInvtyCostAmt_A.setAppFracDigit(2);
                scrnMsg.A.no(i).histInvtyTrxCostAmt_A.setAppFracDigit(2);
            }
        } else {
            // Initial
            // Header
            scrnMsg.abcAsgNm.setInputProtected(false);
            scrnMsg.abcAsgDescTxt.setInputProtected(false);
            scrnMsg.rtlWhCatgCd_H1.setInputProtected(false);
            scrnMsg.rtlWhCdSrchTxt_RW.setInputProtected(false);
            scrnMsg.rtlWhNmSrchTxt_RW.setInputProtected(true);
            scrnMsg.rtlSwhCdSrchTxt_SW.setInputProtected(false);
            scrnMsg.mdseItemTpCd_H1.setInputProtected(false);
            scrnMsg.xxLinkAncr_W.setInputProtected(false);
            scrnMsg.xxLinkAncr_SW.setInputProtected(false);
            scrnMsg.abcAnlsCritCd_H1.setInputProtected(false);
            scrnMsg.effFromDt.setInputProtected(false);
            scrnMsg.effThruDt.setInputProtected(false);
            scrnMsg.abcAnlsClsNum_H1.setInputProtected(false);
            scrnMsg.abcAnlsRqstStsDescTxt.setInputProtected(true);
            scrnMsg.xxScrItem30Txt.setInputProtected(true);

            for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
                scrnMsg.S.no(i).xxChkBox_SS.setInputProtected(false);
            }

            // Header1
            handler.setButtonEnabled(BTN_SEARCH, true);
            handler.setButtonEnabled(BTN_IMPORT_NEW, true);
            handler.setButtonEnabled(BTN_SAVE, true);
            handler.setButtonEnabled(BTN_ANALYZE, false);
            handler.setButtonEnabled(BTN_DELETE, false);
            handler.setButtonEnabled(BTN_REFLESH, false);
            handler.setButtonEnabled(BTN_EDIT_CLASS, true);

            // Item Search
            scrnMsg.xxLinkAncr_I.setInputProtected(true);
            scrnMsg.mdseCd.setInputProtected(true);
            handler.setButtonEnabled(BTN_SEARCH_ITEM, false);

            // Detail Footer
            handler.setButtonEnabled(BTN_ADD_TAG, false);
            handler.setButtonEnabled(BTN_EDIT_TAG, false);
            handler.setButtonEnabled(BTN_DELETE_TAG, false);

            // common button protection
            // 0 : inactive
            // 1 : active
            handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
            handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
            handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        }
    }

    /**
     * Control Screen Item Display Reference Mode
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL1040BMsg
     */
    public static void ctrlScrnItemDisplayReference(EZDCommonHandler handler, NLCL1040BMsg scrnMsg) {
        // Header
        scrnMsg.abcAsgNm.setInputProtected(false);
        scrnMsg.abcAsgDescTxt.setInputProtected(true);
        scrnMsg.rtlWhCatgCd_H1.setInputProtected(true);
        scrnMsg.rtlWhCdSrchTxt_RW.setInputProtected(true);
        scrnMsg.rtlWhNmSrchTxt_RW.setInputProtected(true);
        scrnMsg.rtlSwhCdSrchTxt_SW.setInputProtected(true);
        scrnMsg.mdseItemTpCd_H1.setInputProtected(true);
        scrnMsg.xxLinkAncr_W.setInputProtected(true);
        scrnMsg.xxLinkAncr_SW.setInputProtected(true);
        scrnMsg.abcAnlsCritCd_H1.setInputProtected(true);
        scrnMsg.effFromDt.setInputProtected(true);
        scrnMsg.effThruDt.setInputProtected(true);
        scrnMsg.abcAnlsClsNum_H1.setInputProtected(true);
        scrnMsg.abcAnlsRqstStsDescTxt.setInputProtected(true);
        scrnMsg.xxScrItem30Txt.setInputProtected(true);

        for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
            scrnMsg.S.no(i).xxChkBox_SS.setInputProtected(true);
        }

        // Header1
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_IMPORT_NEW, false);
        handler.setButtonEnabled(BTN_SAVE, false);
        handler.setButtonEnabled(BTN_ANALYZE, false);
        handler.setButtonEnabled(BTN_DELETE, false);
        handler.setButtonEnabled(BTN_REFLESH, true);
        handler.setButtonEnabled(BTN_EDIT_CLASS, true);

        // Detail Footer
        handler.setButtonEnabled(BTN_EDIT_TAG, false);
        handler.setButtonEnabled(BTN_ADD_TAG, false);
        handler.setButtonEnabled(BTN_DELETE_TAG, false);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.xxLinkAncr_I.setInputProtected(true);
            scrnMsg.mdseCd.setInputProtected(true);
            handler.setButtonEnabled(BTN_SEARCH_ITEM, false);
        } else if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.mdseCd.setInputProtected(false);
            scrnMsg.xxLinkAncr_I.setInputProtected(false);
            handler.setButtonEnabled(BTN_SEARCH_ITEM, true);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).xxChkBox_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhCatgCd_A.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhCd_A.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhCd_A.setInputProtected(true);
            scrnMsg.A.no(i).stkStsCd_A.setInputProtected(true);
            scrnMsg.A.no(i).curInvtyQty_A.setInputProtected(true);
            scrnMsg.A.no(i).curInvtyCostAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).histInvtyTrxQty_A.setInputProtected(true);
            scrnMsg.A.no(i).histInvtyTrxCostAmt_A.setInputProtected(true);
            scrnMsg.A.no(i).histInvtyTrxRecCnt_A.setInputProtected(true);
            scrnMsg.A.no(i).abcAnlsClsTagCd_A.setInputProtected(true);

            // Set Application Fraction Digit
            scrnMsg.A.no(i).curInvtyCostAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).histInvtyTrxCostAmt_A.setAppFracDigit(2);
        }
    }

    /**
     * isEntryGranted
     * @return boolean
     */
    public static final boolean isEntryGranted() {
        S21UserProfileService service = S21UserProfileServiceFactory.getInstance().getService();
        return service.isFunctionGranted(service.getContextUserInfo().getUserId(), FUNC_EDIT);
    }

    /**
     * setNameForMessage
     * @param scrnMsg NLCL1040BMsg
     */
    public static void setNameForMessage(NLCL1040BMsg scrnMsg) {

        scrnMsg.abcAsgNm.setNameForMessage("ABC Name");
        scrnMsg.abcAsgDescTxt.setNameForMessage("Description");
        scrnMsg.abcAnlsRqstStsDescTxt.setNameForMessage("Analysis Status");
        scrnMsg.abcAnlsLastProcTs.setNameForMessage("Last Analysis");
        scrnMsg.xxScrItem30Txt.setNameForMessage("Last Analysis");
        scrnMsg.rtlWhCatgCd_H1.setNameForMessage("Warehouse Type");
        scrnMsg.rtlWhCdSrchTxt_RW.setNameForMessage("Warehouse Code");
        scrnMsg.rtlWhNmSrchTxt_RW.setNameForMessage("Warehouse Name");
        scrnMsg.rtlSwhCdSrchTxt_SW.setNameForMessage("Sub Warehouse Code");
        scrnMsg.mdseItemTpCd_H1.setNameForMessage("Item Type");
        scrnMsg.abcAnlsCritCd_H1.setNameForMessage("Analysis Criteria");
        scrnMsg.effFromDt.setNameForMessage("From Date");
        scrnMsg.effThruDt.setNameForMessage("To Date");
        scrnMsg.abcAnlsClsNum_H1.setNameForMessage("ABC Class Name");
        scrnMsg.mdseCd.setNameForMessage("Item Number");
        scrnMsg.xxPageShowCurNum_A.setNameForMessage("Current Page Number");

        // Set Detail.
        for (int index = 0; index < scrnMsg.A.length(); index++) {
            scrnMsg.A.no(index).xxChkBox_A.setNameForMessage("Detail Check Box");
            scrnMsg.A.no(index).mdseCd_A.setNameForMessage("Item Number");
            scrnMsg.A.no(index).mdseDescShortTxt_A.setNameForMessage("Item Description");
            scrnMsg.A.no(index).rtlWhCatgCd_A.setNameForMessage("Warehouse Type");
            scrnMsg.A.no(index).rtlWhCd_A.setNameForMessage("Warehouse");
            scrnMsg.A.no(index).rtlSwhCd_A.setNameForMessage("Sub Warehouse");
            scrnMsg.A.no(index).stkStsCd_A.setNameForMessage("Stock Status");
            scrnMsg.A.no(index).curInvtyQty_A.setNameForMessage("Current On Hand Quantity");
            scrnMsg.A.no(index).curInvtyCostAmt_A.setNameForMessage("Current On Hand Value");
            scrnMsg.A.no(index).histInvtyTrxQty_A.setNameForMessage("Historical Usage Qty");
            scrnMsg.A.no(index).histInvtyTrxCostAmt_A.setNameForMessage("Historical Usage Value");
            scrnMsg.A.no(index).histInvtyTrxRecCnt_A.setNameForMessage("Historical Usage Transaction");
            scrnMsg.A.no(index).abcAnlsClsTagCd_A.setNameForMessage("ABC Class Tag");
        }

        // set Stock Status
        for (int index = 0; index < scrnMsg.S.length(); index++) {
            scrnMsg.S.no(index).stkStsCd_SS.setNameForMessage("Stock Status");
        }
    }

}
