/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NLCL0300.common;

import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_ADD_LINE;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_APPLY;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_APPROVE;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_CLEAR;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_DELETE;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_DOWNLOAD;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_REJECT;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_RESET;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_RETURN;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_SAVE;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_CMN_SUBMIT;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_DELETE_LINE;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.*;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BTN_SEARCH;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.BUSINESS_APPL_ID;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.FUNC_UPDATE;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.SCREEN_ID;
import static business.servlet.NLCL0300.constant.NLCL0300Constant.TABLE_ID_A;

import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLCL0300.NLCL0300BMsg;
import business.servlet.NLCL0300.NLCL0300_ABMsg;
import business.servlet.NLCL0300.NLCL0300_XBMsgArray;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   CSAI            K.Lee           Create          N/A
 * 2017/02/07   CITS            Y.IWASAKI       Update          QC#17234
 *</pre>
 */
public class NLCL0300CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * 
     * @param handler
     * @param scrnMsg
     * @param userId
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NLCL0300BMsg scrnMsg, String userId) {

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
    public static final void setControlScreen(EZDCommonHandler handler, NLCL0300BMsg scrnMsg, String userId) {

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
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
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
    public static final void controlScreenFields(EZDCommonHandler handler, NLCL0300BMsg scrnMsg, String userId) {

        controlScreenDetailFields(handler, scrnMsg, userId);

    }

    /**
     * Set table's back color
     * @param scrnMsg
     */
    public static final void setTblBackColor(NLCL0300BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        tblColor.setAlternateRowsBG(TABLE_ID_A, scrnMsg.A);
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

    private static final void controlScreenDetailFields(EZDCommonHandler handler, NLCL0300BMsg scrnMsg, String userId) {

        scrnMsg.setInputProtected(true);
        handler.setButtonEnabled(BTN_SEARCH[0], false);
        handler.setButtonEnabled(BTN_ADD_LINE[0], false);
        handler.setButtonEnabled(BTN_DELETE_LINE[0], false);
        handler.setButtonEnabled(BTN_ATTACHMENTS[0], false);
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG[0], false);
        handler.setButtonEnabled(BTN_ADD_NEW_CONFIG_FROM_MODEL[0], false);
        handler.setButtonEnabled(BTN_REMOVE_ALL[0], false);
        handler.setButtonEnabled(BTN_OPEN_WIN_MANAGE_SHIPPING_ORDER[0], false);

        
        scrnMsg.invtyOrdNum_H.setInputProtected(false);
        handler.setButtonEnabled(BTN_SEARCH[0], true);
        if (ZYPCommonFunc.hasValue(scrnMsg.soNum_H)) {
            handler.setButtonEnabled(BTN_OPEN_WIN_MANAGE_SHIPPING_ORDER[0], true);
        }

        if (hasUpdateAuthority(userId) && !ZYPCommonFunc.hasValue(scrnMsg.invtyOrdNum_HD)) {
            handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
            scrnMsg.firstInvtyOrdCmntTxt_H.setInputProtected(false);
            scrnMsg.scdInvtyOrdCmntTxt_H.setInputProtected(false);
            scrnMsg.thirdInvtyOrdCmntTxt_H.setInputProtected(false);
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.rtlWhCd_H.setInputProtected(true);
                scrnMsg.rtlWhNm_H.setInputProtected(true);
                scrnMsg.rtlWhCd_HL.setInputProtected(true);
                scrnMsg.svcConfigMstrPk_HL.setInputProtected(true);
                scrnMsg.svcConfigMstrPk_H.setInputProtected(true);
                scrnMsg.mdlDescTxt_HL.setInputProtected(true);
                scrnMsg.mdlDescTxt_H.setInputProtected(true);
                handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG[0], false);
                handler.setButtonEnabled(BTN_ADD_NEW_CONFIG_FROM_MODEL[0], false);
            } else {
                scrnMsg.rtlWhCd_H.setInputProtected(false);
                scrnMsg.rtlWhNm_H.setInputProtected(false);
                scrnMsg.rtlWhCd_HL.setInputProtected(false);
                scrnMsg.svcConfigMstrPk_HL.setInputProtected(false);
                scrnMsg.svcConfigMstrPk_H.setInputProtected(false);
                scrnMsg.mdlDescTxt_HL.setInputProtected(false);
                scrnMsg.mdlDescTxt_H.setInputProtected(false);
                handler.setButtonEnabled(BTN_ADD_EXISTING_CONFIG[0], true);
                handler.setButtonEnabled(BTN_ADD_NEW_CONFIG_FROM_MODEL[0], true);

            }

            if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_HL)) {
                handler.setButtonEnabled(BTN_ADD_LINE[0], true);
                handler.setButtonEnabled(BTN_DELETE_LINE[0], true);
            }

            // QC#17234
            // Enable "Remove All" button only when at least 1 detail has CONFIG_PK.
            boolean enableBtnRemoveAll = false;
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NLCL0300_ABMsg detail = scrnMsg.A.no(i);
                detail.ordQty_A.setInputProtected(true);
                if (!ZYPCommonFunc.hasValue(detail.invtyOrdNum_A)) {
                    if (ZYPCommonFunc.hasValue(detail.svcConfigMstrPk_A)) {
                        detail.xxChkBox_A.setInputProtected(true);
                        detail.mdseCd_A.setInputProtected(true);
                        detail.stkStsCd_A.setInputProtected(true);
                        detail.rtlSwhCd_A.setInputProtected(true);
                        detail.serNum_A.setInputProtected(true);
                        detail.rmvConfigFlg_A.setInputProtected(false);
                        enableBtnRemoveAll = true;
                    } else {
                        detail.xxChkBox_A.setInputProtected(false);
                        detail.mdseCd_A.setInputProtected(false);
                        detail.stkStsCd_A.setInputProtected(false);
                        detail.rtlSwhCd_A.setInputProtected(false);
                        detail.serNum_A.setInputProtected(false);
                        detail.rmvConfigFlg_A.setInputProtected(true);
                    }
                }
            }
            scrnMsg.mdseCd_H.setInputProtected(false);
            scrnMsg.mdseCd_HL.setInputProtected(false);
            scrnMsg.serNum_H.setInputProtected(false);
            handler.setButtonEnabled(BTN_ADD_LINE[0], true);
            handler.setButtonEnabled(BTN_REMOVE_ALL[0], enableBtnRemoveAll);
        } else {
            handler.setButtonEnabled(BTN_SEARCH[0], true);
            handler.setButtonEnabled(BTN_ATTACHMENTS[0], true);
            scrnMsg.invtyOrdNum_H.setInputProtected(false);
        }
    }

    /**
     * Set Location Pop-up Parameter
     * @param scrnMsg NLCL0300BMsg
     * @param glblCmpyCd Sting
     * @return LocationPopup Parameter (NPAL1010) Object[]
     */
    public static Object[] setParamForLocationPopup(NLCL0300BMsg scrnMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(scrnMsg.P);

        List<String> bizAppKeyId = null;

        String locRoleTpList = NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BUSINESS_APPL_ID, bizAppKeyId);
        scrnMsg.P.no(0).clear();
        scrnMsg.P.no(1).clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, locRoleTpList);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_OFF_N);
        scrnMsg.P.no(5).clear();
        scrnMsg.P.no(6).clear();
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
     * @param scrnMsg NLCL0300BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6800(NLCL0300BMsg scrnMsg, String eventNm, int eventRow) {
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


    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NLCL0300BMsg
     */
    public static void setInitParamForConfigPopup(NLCL0300BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPTableUtil.clear(scrnMsg.R); 
        ZYPTableUtil.clear(scrnMsg.S);

        // Location Parameters are set to Sub screen delivery
        // information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_PI, "02");
        if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_H)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_PI, scrnMsg.svcConfigMstrPk_H);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(0).svcMachMstrStsCd_I, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(1).svcMachMstrStsCd_I, SVC_MACH_MSTR_STS.REMOVED);
        scrnMsg.R.setValidCount(2);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, "03");
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm, ZYPConstant.FLG_OFF_N);
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_PI, scrnMsg.rtlWhCd_H);
        }
    }

    /**
     * The method explanation: set parameter to call Pop-up.
     * @param scrnMsg NLCL0300BMsg
     * @return Object[]
     */
    public static Object[] setParamForConfigPopup(NLCL0300BMsg scrnMsg) {

        List<Object> parameters = new ArrayList<Object>();

        // Input Parameter
        parameters.add(scrnMsg.xxModeCd_PI);                 //[0]: CONFIG_EXST_MODE_CD
        parameters.add(scrnMsg.svcConfigMstrPk_PI);          //[1]: SVC_CONFIG_MSTR_PK
        parameters.add(scrnMsg.serNum_PI);                   //[2]: SER_NUM
        parameters.add(scrnMsg.svcMachMstrPk_PI);            //[3]: SVC_MACH_MSTR_PK
        parameters.add(scrnMsg.mdseCd_PI);                   //[4]: MDSE_CD
        parameters.add(scrnMsg.mdlNm_PI);                    //[5]: MDL_NM
        parameters.add(scrnMsg.P.no(0).xxPopPrm);            //[6]: SHIP_FEOM_DT
        parameters.add(scrnMsg.P.no(1).xxPopPrm);            //[7]: SHIP_THRU_DT
        parameters.add(scrnMsg.svcMachUsgStsCd_PI);          //[8]: SVC_MACH_USG_STS_CD
        parameters.add(scrnMsg.R);                           //[9]: SVC_MACH_MSTR_STS_CD, Array
        parameters.add(scrnMsg.P.no(2).xxPopPrm);            //[10]: SVC_MACH_MSTR_STS_EDIT_FLG
        parameters.add(scrnMsg.P.no(3).xxPopPrm);            //[11]: MACH_ALLOC_MODE_CODE
        parameters.add(scrnMsg.P.no(4).xxPopPrm);            //[12]: MAIN_UNIT_FLG
        parameters.add(scrnMsg.stkStsCd_PI);                 //[13]: STK_STS_CD
        parameters.add(scrnMsg.rtlWhCd_PI);                  //[14]: WH_CD
        parameters.add(scrnMsg.rtlSwhCd_PI);                 //[15]: SUB_WH_CD
        parameters.add(scrnMsg.svcSlnSq_PI);                 //[16]: SVC_SLN_SQ
        parameters.add(scrnMsg.svcSlnNm_PI);                 //[17]: SVC_SLN_NM
        parameters.add(scrnMsg.contrNum_PI);                 //[18]: CONTR_NUM
        parameters.add(scrnMsg.P.no(5).xxPopPrm);            //[19]: DS_OWNR_ACCT_NUM
        parameters.add(scrnMsg.P.no(6).xxPopPrm);            //[20]: OWNR_LOC_CD
        parameters.add(scrnMsg.P.no(7).xxPopPrm);            //[21]: DS_BILL_TO_ACCT_NUM
        parameters.add(scrnMsg.P.no(8).xxPopPrm);            //[22]: BILL_TO_LOC_CD
        parameters.add(scrnMsg.P.no(9).xxPopPrm);            //[23]: DS_CUR_LOC_ACCT_NUM
        parameters.add(scrnMsg.curLocNum_PI);                //[24]: CUR_LOC_NUM

        // Output Parameter
        parameters.add(scrnMsg.mdlId_PO);                    //[25]: MDL_ID
        parameters.add(scrnMsg.mdlNm_PO);                    //[26]: MDL_NM
        parameters.add(scrnMsg.contrPk_PO);                  //[27]: CONTR_PK
        parameters.add(scrnMsg.contrNum_PO);                 //[28]: CONTR_NUM
        parameters.add(scrnMsg.svcConfigMstrPk_PO);          //[29]: SVC_CONFIG_MSTR_PK
        parameters.add(scrnMsg.S);                           //[30]: 

        return parameters.toArray(new Object[0]);
    }

    /**
     * Set Popup Argument for NMAL6800.
     * @param scrnMsg NLCL0290BMsg
     * @param eventNm String
     * @param eventRow int
     * @return Object[]
     */
    public static Object[] setArgumentNMAL6800(NLCL0300BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.mdseCd_H);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, TEN_DIGIT);

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
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NLCL0300_XBMsgArray
     * @param size int
     * @return Object[]
     */

    public static Object[] toArray_popupForZYPL0310(NLCL0300_XBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }
}
