/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NLCL0290.common;


import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0290.NLCL0290BMsg;
import business.servlet.NLCL0290.NLCL0290_XBMsgArray;
import business.servlet.NLCL0290.constant.NLCL0290Constant;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472/18490
 * 06/05/2018   CITS            K.Ogino         Update          QC#26383
 * 11/12/2018   CITS            T.hakodate      Update          QC#29172
 *</pre>
 */
public class NLCL0290CommonLogic implements NLCL0290Constant {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * 
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NLCL0290BMsg scrnMsg, String userId) {

        initCommonButton(handler);
        setTblBackColor(scrnMsg);
        controlScreenDetailFields(handler, scrnMsg, userId);
    }

    /**
     * <pre>
     * The screen item is set.
     * </pre>
     * 
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void setControlScreen(EZDCommonHandler handler, NLCL0290BMsg scrnMsg, String userId) {

        setTblBackColor(scrnMsg);
        controlScreenFields(handler, scrnMsg, userId);
    }

    /**
     * Method name: initCommonButton
     * <dd>The method explanation: Initialize Common Button Control.
     * <dd>Remarks:
     * @param handler EZ Common Handler
     */
    public static final void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

    }

    /**
     * Control screen fields
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NLCL0290BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }

    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NLCL0290BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        tblColor.setAlternateRowsBG(TABLE_ID_A_LEFT, scrnMsg.A);
        tblColor.setAlternateRowsBG(TABLE_ID_A_RIGHT, scrnMsg.A);
    }

    /**
     * Return true if userId have update authority.
     * @param userId
     * @return
     */
    public static final boolean hasUpdateAuthority(String userId) {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        if (profileService.isFunctionGranted(userId, new String[] {FUNC_UPDATE})) {
            return true;
        }
        return false;
    }

    private static final void controlScreenDetailFields(EZDCommonHandler handler, NLCL0290BMsg scrnMsg, String userId) {

        scrnMsg.setInputProtected(true);
        // QC:18472
        scrnMsg.invtyOrdNum_H.setInputProtected(false);
        handler.setButtonEnabled(BTN_SEARCH[0], false);
        handler.setButtonEnabled(BTN_NEW[0], false);
        /** active add line button*/
        handler.setButtonProperties(BTN_ADD_LINE[0],BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
        handler.setButtonEnabled(BTN_ADD_LINE[1], false);
        handler.setButtonEnabled(BTN_DELETE_LINE[0], false);
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        handler.setButtonEnabled(BTN_IMPORT[0], false);
        handler.setButtonEnabled(BTN_OPEN_WIN_SERIAL[0], false);
        handler.setButtonEnabled(BTN_OPEN_WIN_ACCOUNT[0], false);
        handler.setButtonEnabled(BTN_ATTACHMENTS[0], false);
        // QC:18472-18490 Start
        handler.setButtonEnabled(SEARCH_RTL_WH_NM, false);
        handler.setButtonEnabled(BTN_ADD_CONFIG, false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!hasUpdateAuthority(userId) || ZYPCommonFunc.hasValue(scrnMsg.A.no(i).invtyOrdNum_A)) {
                // Not Authority the screen or Search Order
                handler.setButtonEnabled(BTN_SEARCH_ITEM_CD, i, false);
                handler.setButtonEnabled(BTN_SEARCH_ITEM_NM, i, false);
                handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, false);
            } else if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk)) {
                // Config
                handler.setButtonEnabled(BTN_SEARCH_ITEM_CD, i, false);
                handler.setButtonEnabled(BTN_SEARCH_ITEM_NM, i, false);
                if (ZYPConstant.FLG_OFF_0.equals(scrnMsg.A.no(i).xxConfigFlg_A.getValue())) {
                    // Config Header
                    // QC#26383
                    if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                        handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, false);
                        scrnMsg.A.no(i).adjCatgCd_A.setInputProtected(true);
                        scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
                        // QC#29172 add start
                    } else if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                        handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, false);
                        scrnMsg.A.no(i).adjCatgCd_A.setInputProtected(true);
                        scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
                    } else {
                        // QC#29172 add start
                        handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, true);
                        scrnMsg.A.no(i).adjCatgCd_A.setInputProtected(false);
                        scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(false);
                    }
                    scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                    scrnMsg.A.no(i).invtyOrdLineCmntTxt_A.setInputProtected(false);

                    // Adjustment Transaction Type = "Sub WH Transfer"
                    if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                        scrnMsg.A.no(i).toRtlSwhCd_A.setInputProtected(false);
                    }

                    // QC#29172 add start
                    if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                        scrnMsg.A.no(i).toStkStsCd_A.setInputProtected(false);
                    }
                    // QC#29172 add end

                    // Account Control
                    if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).adjCatgCd_A)) {
                        if (ADJ_CATG.SPECIAL_ACCOUNT.equals(scrnMsg.A.no(i).adjCatgCd_A.getValue())) {
                            handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, true);
                            scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(false);
                        } else {
                            handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, false);
                            scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
                        }
                    } else {
                        // QC#29172 add start
                        // QC#26383
                        if (!ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(scrnMsg.adjTrxTpCd_H.getValue()) && !ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                            handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, true);
                            scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(false);
                        }
                        // QC#29172 add end
                    }
                } else {
                    // Config Detail
                    handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, false);
                }
            } else {
                // Not Config
                handler.setButtonEnabled(BTN_SEARCH_ITEM_CD, i, true);
                handler.setButtonEnabled(BTN_SEARCH_ITEM_NM, i, true);
                // QC#26383
                if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                    handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, false);
                    scrnMsg.A.no(i).adjCatgCd_A.setInputProtected(true);
                    scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
                    // QC#29172 add start
                } else if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                    handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, false);
                    scrnMsg.A.no(i).adjCatgCd_A.setInputProtected(true);
                    scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
                } else {
                    // QC#29172 add start
                    handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, true);
                    scrnMsg.A.no(i).adjCatgCd_A.setInputProtected(false);
                    scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(false);
                }

                scrnMsg.A.no(i).xxChkBox_A.setInputProtected(false);
                scrnMsg.A.no(i).mdseCd_A.setInputProtected(false);
                // QC#29172 add start
                if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                    scrnMsg.A.no(i).toRtlSwhCd_A.setInputProtected(true);
                } else {
                    scrnMsg.A.no(i).toRtlSwhCd_A.setInputProtected(false);
                }

                // QC#29172 add end
                scrnMsg.A.no(i).ordQty_A.setInputProtected(false);
                scrnMsg.A.no(i).stkStsCd_A.setInputProtected(false);
                scrnMsg.A.no(i).invtyOrdLineCmntTxt_A.setInputProtected(false);
                scrnMsg.A.no(i).serNum_A.setInputProtected(false);

                // Adjustment Transaction Type = "Sub WH Transfer"
                if (ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                    scrnMsg.A.no(i).fromRtlSwhCd_A.setInputProtected(false);
                }
                // QC#29172 add start
                if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                    scrnMsg.A.no(i).fromRtlSwhCd_A.setInputProtected(false);
                    scrnMsg.A.no(i).toStkStsCd_A.setInputProtected(false);
                }
                // QC#29172 add end
                // Account Control
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).adjCatgCd_A)) {
                    if (ADJ_CATG.SPECIAL_ACCOUNT.equals(scrnMsg.A.no(i).adjCatgCd_A.getValue())) {
                        handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, true);
                        scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(false);
                    } else {
                        handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, false);
                        scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
                    }
                } else {
                    // QC#29172 add start
                    if (ADJ_TRX_TP.STOCK_STATUS_CHANGE.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                        handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, false);
                        scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(true);
                    } else
                    // QC#26383
                    if (!ADJ_TRX_TP.SUB_WAREHOUSE_TRANSFER.equals(scrnMsg.adjTrxTpCd_H.getValue())) {
                        handler.setButtonEnabled(BTN_SEARCH_ACCT_CD, i, true);
                        scrnMsg.A.no(i).xxScrItem130Txt_A.setInputProtected(false);
                    }
                }
            }
        }
        // QC:18472-18490 End

        if (hasUpdateAuthority(userId) && !ZYPCommonFunc.hasValue(scrnMsg.invtyOrdNum_HD)) {
            handler.setButtonEnabled(BTN_SEARCH[0], true);
            handler.setButtonEnabled(BTN_IMPORT[0], true);
            scrnMsg.xxFileData_UP.setInputProtected(false);

            if (scrnMsg.A.getValidCount() > 0) {
                handler.setButtonEnabled(BTN_NEW[0], false);
                // QC:18472
                handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
                handler.setButtonEnabled(SEARCH_RTL_WH_NM, false);
            } else {
                handler.setButtonEnabled(BTN_NEW[0], true);
                // QC:18472
                handler.setButtonEnabled(SEARCH_RTL_WH_NM, true);
                scrnMsg.adjTrxTpCd_H.setInputProtected(false);
                //scrnMsg.invtyOrdNum_H.setInputProtected(false);
                scrnMsg.rtlWhCd_H.setInputProtected(false);
                //scrnMsg.rtlWhNm_H.setInputProtected(false);
                scrnMsg.rtlWhCd_HL.setInputProtected(false);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_HL)) {
                scrnMsg.adjTrxTpCd_H.setInputProtected(true);
                scrnMsg.rtlWhCd_H.setInputProtected(true);
                scrnMsg.rtlWhNm_H.setInputProtected(true);
                scrnMsg.rtlWhCd_HL.setInputProtected(true);
                // QC:18472
                handler.setButtonEnabled(BTN_NEW[0], false);
                handler.setButtonEnabled(SEARCH_RTL_WH_NM, false);

//                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.acctAliasAvalFlg_H.getValue())) {
//                    scrnMsg.adjAcctAliasNm_H.setInputProtected(false);
//                    scrnMsg.xxScrItem130Txt_HL.setInputProtected(true);
//                    scrnMsg.xxScrItem130Txt_H.setInputProtected(true);
//                } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.acctReqFlg_H.getValue())) {
//                    scrnMsg.adjAcctAliasNm_H.setInputProtected(true);
//                    scrnMsg.xxScrItem130Txt_HL.setInputProtected(false);
//                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.coaEnblFlg_H.getValue())) {
//                        scrnMsg.xxScrItem130Txt_H.setInputProtected(false);
//                    } else {
//                        scrnMsg.xxScrItem130Txt_H.setInputProtected(true);
//                    }
//                } else {
//                    scrnMsg.adjAcctAliasNm_H.setInputProtected(true);
//                    scrnMsg.xxScrItem130Txt_HL.setInputProtected(true);
//                    scrnMsg.xxScrItem130Txt_H.setInputProtected(true);
//                }

//                scrnMsg.locStsCd_H.setInputProtected(false);
//                scrnMsg.stkStsCd_H.setInputProtected(false);
                // QC:18490
                /** active add line button*/
                handler.setButtonProperties(BTN_ADD_LINE[0],BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
                handler.setButtonEnabled(BTN_ADD_LINE[1], true);
                if (scrnMsg.A.getValidCount() > 0) {
                    handler.setButtonEnabled(BTN_DELETE_LINE[0], true);
                    handler.setButtonEnabled(BTN_ADD_CONFIG, false);

                    if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk)) {
                        handler.setButtonProperties(BTN_ADD_LINE[0],BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
                        handler.setButtonEnabled(BTN_ADD_LINE[1], false);
                    }
                } else {
                    handler.setButtonEnabled(BTN_ADD_CONFIG, true);
                    scrnMsg.svcConfigMstrPk.setInputProtected(false);
                    scrnMsg.svcConfigMstrPk_HL.setInputProtected(false);
                }
            }

            // QC:18472
            if (scrnMsg.xxPageShowOfNum_A.getValueInt() == 999) {
                handler.setButtonProperties(BTN_ADD_LINE[0],BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
                handler.setButtonEnabled(BTN_ADD_LINE[1], false);
            }
//            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                NLCL0290_ABMsg detail = scrnMsg.A.no(i);
//                if (!ZYPCommonFunc.hasValue(detail.invtyOrdNum_A)) {
//                    detail.xxChkBox_A.setInputProtected(false);
//                    detail.mdseCd_A.setInputProtected(false);
//                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.destSwhReqFlg_H.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.adjQtyDecrFlg_H.getValue())) {
//                        detail.fromRtlSwhCd_A.setInputProtected(false);
//                    }
//                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.destSwhReqFlg_H.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.adjQtyIncrFlg_H.getValue())) {
//                        detail.toRtlSwhCd_A.setInputProtected(false);
//                    }
//                    detail.ordQty_A.setInputProtected(false);
//                    detail.adjCatgCd_A.setInputProtected(false);
//                    detail.invtyOrdLineCmntTxt_A.setInputProtected(false);
//                    detail.serNum_A.setInputProtected(false);

//                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.acctAliasAvalFlg_H.getValue())) {
//                        handler.setButtonEnabled(BTN_OPEN_WIN_ACCOUNT[0], false);
//                        detail.xxScrItem130Txt_A.setInputProtected(true);
//                    } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.acctReqFlg_H.getValue())) {
//                        handler.setButtonEnabled(BTN_OPEN_WIN_ACCOUNT[0], true);
//                        if (ZYPConstant.FLG_ON_Y.equals(detail.coaEnblFlg_A.getValue())) {
//                            detail.xxScrItem130Txt_A.setInputProtected(false);
//                        } else {
//                            detail.xxScrItem130Txt_A.setInputProtected(true);
//                        }
//                        
//                    } else {
//                        handler.setButtonEnabled(BTN_OPEN_WIN_ACCOUNT[0], false);
//                        detail.xxScrItem130Txt_A.setInputProtected(true);
//                    }
//                } 
//            }
        } else {
            handler.setButtonEnabled(BTN_SEARCH[0], true);
            handler.setButtonEnabled(BTN_ATTACHMENTS[0], true);
            // QC:18472
            if (!hasUpdateAuthority(userId)) {
                handler.setButtonEnabled(SEARCH_RTL_WH_NM, true);
            }

            // QC:18472
            //scrnMsg.invtyOrdNum_H.setInputProtected(false);
        }
    }

    /**
     * Set Location Pop-up Parameter
     * @param scrnMsg NLCL0290BMsg
     * @param glblCmpyCd Sting
     * @return LocationPopup Parameter (NPAL1010) Object[]
     */
    public static Object[] setParamForLocationPopup(NLCL0290BMsg scrnMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(scrnMsg.P);

        List<String> bizAppKeyId = null;

        String locRoleTpList = NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BUSINESS_APPL_ID, bizAppKeyId);
        scrnMsg.P.no(0).clear();
        scrnMsg.P.no(1).clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, locRoleTpList);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(5).clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.rtlWhCd_H);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.rtlWhNm_H);
        scrnMsg.P.no(8).clear();
        scrnMsg.P.no(9).clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_ON_Y);
        scrnMsg.P.no(11).clear();
        scrnMsg.P.no(12).clear();
        scrnMsg.P.no(13).clear();

        Object[] params = new Object[14];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;
        params[12] = scrnMsg.P.no(12).xxPopPrm;
        params[13] = scrnMsg.P.no(13).xxPopPrm;

        return params;
    }

    /**
     * Set Popup Argument for NMAL6800.
     * @param scrnMsg NLCL0290BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6800(NLCL0290BMsg scrnMsg, String eventNm, int eventRow) {
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.A.no(eventRow).mdseCd_A);
        //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.A.no(eventRow).mdseCd_A);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, "10");

        Object[] params = new Object[10];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;

        return params;
    }

    
    // QC:18490 Start
    /**
     * Set Popup Argument for NSAL1240.
     * @param scrnMsg NLCL0290BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setParamForConfigSearchPopup(NLCL0290BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.X);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "02");
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(0).xxPopPrm_AT, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(scrnMsg.X.no(1).xxPopPrm_AT, SVC_MACH_MSTR_STS.REMOVED);
        scrnMsg.X.setValidCount(2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(11).xxPopPrm, "03");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(14).xxPopPrm, scrnMsg.rtlWhCd_H);

        
        Object[] params = new Object[32];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.svcConfigMstrPk;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.X;
        params[10] = scrnMsg.P.no(10).xxPopPrm;
        params[11] = scrnMsg.P.no(11).xxPopPrm;
        params[12] = scrnMsg.P.no(12).xxPopPrm;
        params[13] = scrnMsg.P.no(13).xxPopPrm;
        params[14] = scrnMsg.P.no(14).xxPopPrm;
        params[15] = scrnMsg.P.no(15).xxPopPrm;
        params[16] = scrnMsg.P.no(16).xxPopPrm;
        params[17] = scrnMsg.P.no(17).xxPopPrm;
        params[18] = scrnMsg.P.no(18).xxPopPrm;
        params[19] = scrnMsg.P.no(19).xxPopPrm;
        params[20] = scrnMsg.P.no(20).xxPopPrm;
        params[21] = scrnMsg.P.no(21).xxPopPrm;
        params[22] = scrnMsg.P.no(22).xxPopPrm;
        params[23] = scrnMsg.P.no(23).xxPopPrm;
        params[24] = scrnMsg.P.no(24).xxPopPrm;
        params[25] = scrnMsg.P.no(25).xxPopPrm;
        params[26] = scrnMsg.P.no(26).xxPopPrm;
        params[27] = scrnMsg.P.no(27).xxPopPrm;
        params[28] = scrnMsg.P.no(28).xxPopPrm;
        params[29] = scrnMsg.svcConfigMstrPk;
        params[30] = scrnMsg.P.no(30).xxPopPrm;
        params[31] = scrnMsg.P.no(31).xxPopPrm;

        return params;
    }
    // QC:18490 End

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NLCL0290BMsg
     * @param idx Integer
     */
    public static Object[] setParamForAccountPopup(NLCL0290BMsg scrnMsg, int idx) {

        ZYPTableUtil.clear(scrnMsg.P);

        if (idx < 0) {
            // QC:18472
//            if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem130Txt_H)) {
                //QC:14488
                //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "NLCL0290");
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "NLCL0290" + scrnMsg.adjTrxTpCd_H.getValue());
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, scrnMsg.xxScrItem130Txt_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.coaCmpyCd_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, scrnMsg.coaAfflCd_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.coaBrCd_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, scrnMsg.coaCcCd_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, scrnMsg.coaAcctCd_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.coaProdCd_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.coaChCd_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, scrnMsg.coaProjCd_H);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.coaExtnCd_H);
//            } else {
//                scrnMsg.P.clear();
                //QC:14488
                //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "NLCL0290");
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "NLCL0290" + scrnMsg.adjTrxTpCd_H.getValue());
//            }

        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).xxScrItem130Txt_A)) {
                //QC:14488
                //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "NLCL0290");
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "NLCL0290" + scrnMsg.adjTrxTpCd_H.getValue());
                // QC:18472 Start
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, scrnMsg.A.no(idx).xxScrItem130Txt_A);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.A.no(idx).coaCmpyCd_A);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, scrnMsg.A.no(idx).coaAfflCd_A);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.A.no(idx).coaBrCd_A);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, scrnMsg.A.no(idx).coaCcCd_A);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(5).xxPopPrm, scrnMsg.A.no(idx).coaAcctCd_A);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, scrnMsg.A.no(idx).coaProdCd_A);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(7).xxPopPrm, scrnMsg.A.no(idx).coaChCd_A);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxPopPrm, scrnMsg.A.no(idx).coaProjCd_A);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, scrnMsg.A.no(idx).coaExtnCd_A);
                // QC:18472 End
            } else {
                scrnMsg.P.no(0).clear();
                //QC:14488
                //ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "NLCL0290");
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, "NLCL0290" + scrnMsg.adjTrxTpCd_H.getValue());
            }

        }

        Object[] params = new Object[10];
        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;
        params[7] = scrnMsg.P.no(7).xxPopPrm;
        params[8] = scrnMsg.P.no(8).xxPopPrm;
        params[9] = scrnMsg.P.no(9).xxPopPrm;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call Pop up.
     * @param scrnMsg NLCL0290BMsg
     * @param idx Integer
     */
    public static void setAccountFromParameter(NLCL0290BMsg scrnMsg) {

        StringBuilder acctNum = new StringBuilder();
        acctNum.append(scrnMsg.P.no(1).xxPopPrm.getValue());
        acctNum.append(".");
        acctNum.append(scrnMsg.P.no(3).xxPopPrm.getValue());
        acctNum.append(".");
        acctNum.append(scrnMsg.P.no(4).xxPopPrm.getValue());
        acctNum.append(".");
        acctNum.append(scrnMsg.P.no(5).xxPopPrm.getValue());
        acctNum.append(".");
        acctNum.append(scrnMsg.P.no(6).xxPopPrm.getValue());
        acctNum.append(".");
        acctNum.append(scrnMsg.P.no(7).xxPopPrm.getValue());
        acctNum.append(".");
        acctNum.append(scrnMsg.P.no(2).xxPopPrm.getValue());
        acctNum.append(".");
        acctNum.append(scrnMsg.P.no(8).xxPopPrm.getValue());
        acctNum.append(".");
        acctNum.append(scrnMsg.P.no(9).xxPopPrm.getValue());

        int idx = scrnMsg.xxNum.getValueInt();
        if (idx < 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaCmpyCd_H ,scrnMsg.P.no(1).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaAfflCd_H ,scrnMsg.P.no(2).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrCd_H ,scrnMsg.P.no(3).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaCcCd_H ,scrnMsg.P.no(4).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaAcctCd_H ,scrnMsg.P.no(5).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProdCd_H ,scrnMsg.P.no(6).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaChCd_H ,scrnMsg.P.no(7).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaProjCd_H ,scrnMsg.P.no(8).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.coaExtnCd_H ,scrnMsg.P.no(9).xxPopPrm);
            // QC:18472
            //ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem130Txt_H, acctNum.toString());
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaCmpyCd_A ,scrnMsg.P.no(1).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaAfflCd_A ,scrnMsg.P.no(2).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaBrCd_A ,scrnMsg.P.no(3).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaCcCd_A ,scrnMsg.P.no(4).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaAcctCd_A ,scrnMsg.P.no(5).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaProdCd_A ,scrnMsg.P.no(6).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaChCd_A ,scrnMsg.P.no(7).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaProjCd_A ,scrnMsg.P.no(8).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).coaExtnCd_A ,scrnMsg.P.no(9).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxScrItem130Txt_A, acctNum.toString());
        }
    }
    
    /**
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NLCL0290_XBMsgArray
     * @param size int
     * @return Object[]
     */

    public static Object[] toArray_popupForZYPL0310(NLCL0290_XBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }

}
