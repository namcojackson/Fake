/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL1100.common;

import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLAL1100.NLAL1100BMsg;
import business.servlet.NLAL1100.NLAL1100Bean;
import business.servlet.NLAL1100.constant.NLAL1100Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Business ID : NLAL1100 Manage RMA Orders
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CITS            M.Ito           Create          N/A
 * 05/16/2016   CSAI            Y.Imazu         Update          QC#7972
 * 06/22/2016   CSAI            Y.Imazu         Update          QC#10514
 * 11/22/2016   CITS            Y.Fujii         Update          R362
 * 09/28/2017   CITS            R.Shimamoto     Update          QC#18669
 * 01/24/2018   CITS            K.Ogino         Update          QC#23044
 * 02/07/2018   CITS            K.Ogino         Update          QC#24010
 * 05/09/2019   Fujitsu         T.Ogura         Update          QC#50027
 *</pre>
 */
public class NLAL1100CommonLogic {

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL1100BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NLAL1100BMsg scrnMsg) {

        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(NLAL1100Constant.SCRN_ID);

        initCommonButton(userProfileService, handler);
        initButton(userProfileService, handler);
        controlScreenFieldsAndButton(userProfileService, handler, scrnMsg);

        if (0 < scrnMsg.A.getValidCount()) {

            setBgColorDetail(scrnMsg);
            setVisibilityDetail(scrnMsg, handler);
        }

        if (0 < scrnMsg.C.getValidCount()) {

            setBgColorCommnets(scrnMsg);
        }
    }

    /**
     * initCommonButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {

        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_SAVE[0], NLAL1100Constant.BTN_CMN_SAVE[1], NLAL1100Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_SUBMIT[0], NLAL1100Constant.BTN_CMN_SUBMIT[1], NLAL1100Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_APPLY[0], NLAL1100Constant.BTN_CMN_APPLY[1], NLAL1100Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_APPROVE[0], NLAL1100Constant.BTN_CMN_APPROVE[1], NLAL1100Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_REJECT[0], NLAL1100Constant.BTN_CMN_REJECT[1], NLAL1100Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_DOWNLOAD[0], NLAL1100Constant.BTN_CMN_DOWNLOAD[1], NLAL1100Constant.BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_DETELE[0], NLAL1100Constant.BTN_CMN_DETELE[1], NLAL1100Constant.BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_CLEAR[0], NLAL1100Constant.BTN_CMN_CLEAR[1], NLAL1100Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_RESET[0], NLAL1100Constant.BTN_CMN_RESET[1], NLAL1100Constant.BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMN_RETURN[0], NLAL1100Constant.BTN_CMN_RETURN[1], NLAL1100Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * initButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {

        // Comment
        handler.setButtonProperties(NLAL1100Constant.BTN_CMNT_ADD[0], NLAL1100Constant.BTN_CMNT_ADD[1], NLAL1100Constant.BTN_CMNT_ADD[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_CMNT_SAVE[0], NLAL1100Constant.BTN_CMNT_SAVE[1], NLAL1100Constant.BTN_CMNT_SAVE[2], 0, null);

        // Detail
        handler.setButtonProperties(NLAL1100Constant.BTN_SELECT_ALL[0], NLAL1100Constant.BTN_SELECT_ALL[1], NLAL1100Constant.BTN_SELECT_ALL[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_UNSELECT_ALL[0], NLAL1100Constant.BTN_UNSELECT_ALL[1], NLAL1100Constant.BTN_UNSELECT_ALL[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_SEND_RQST[0], NLAL1100Constant.BTN_SEND_RQST[1], NLAL1100Constant.BTN_SEND_RQST[2], 0, null);
        handler.setButtonProperties(NLAL1100Constant.BTN_PRINT[0], NLAL1100Constant.BTN_PRINT[1], NLAL1100Constant.BTN_PRINT[2], 0, null);
    }

    /**
     * The method explanation: The display control of the screen item and button
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLAL1100BMsg
     */
    public static void controlScreenFieldsAndButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NLAL1100BMsg scrnMsg) {

        // Header
        scrnMsg.rtlWhNm_H1.setInputProtected(true);
        scrnMsg.rtlSwhNm_H1.setInputProtected(true);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);
        scrnMsg.carrNm_H1.setInputProtected(true);
        scrnMsg.tocNm_H1.setInputProtected(true);
        scrnMsg.xxPsnFirstMidLastNm_H1.setInputProtected(true);

        boolean isUpdateUser = isUpdateUser(userProfileService);

        // Detail Header Button
        if (isUpdateUser && 0 < scrnMsg.A.getValidCount()) {

            handler.setButtonProperties(NLAL1100Constant.BTN_SELECT_ALL[0], NLAL1100Constant.BTN_SELECT_ALL[1], NLAL1100Constant.BTN_SELECT_ALL[2], 1, null);
            handler.setButtonProperties(NLAL1100Constant.BTN_UNSELECT_ALL[0], NLAL1100Constant.BTN_UNSELECT_ALL[1], NLAL1100Constant.BTN_UNSELECT_ALL[2], 1, null);
            handler.setButtonProperties(NLAL1100Constant.BTN_SEND_RQST[0], NLAL1100Constant.BTN_SEND_RQST[1], NLAL1100Constant.BTN_SEND_RQST[2], 1, null);
            handler.setButtonProperties(NLAL1100Constant.BTN_CMN_SUBMIT[0], NLAL1100Constant.BTN_CMN_SUBMIT[1], NLAL1100Constant.BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(NLAL1100Constant.BTN_PRINT[0], NLAL1100Constant.BTN_PRINT[1], NLAL1100Constant.BTN_PRINT[2], 1, null);
        }

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).fromLocStCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).fromLocPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rwsQty_A1.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxTsDsp19Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).ordAgingBcktDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtrnTrkNtfyGrpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtrnTrkNtfyGrpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).schdPickUpDt_A1.setInputProtected(true);
            // START 2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.A.no(i).rcvTsDplyTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rqstRcvDtTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).techMeetTruckFlg_A1.setInputProtected(true);
            // END   2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.A.no(i).carrRsnCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).actlPickUpDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).inspCpltDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxTsDsp19Txt_OC.setInputProtected(true);
            scrnMsg.A.no(i).carrCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).locNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdCatgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdLineCatgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtrnLineDplyStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtrnTrkStsCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).fsrNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).svcMachRmvDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).relMemoTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rwsStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxTsDsp19Txt_RC.setInputProtected(true);
            scrnMsg.A.no(i).rqstPickUpDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).shpgSvcLvlCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrAcctNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).schdCoordPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).schdCoordStsDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).shipLocCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).fromLocCtyAddr_A1.setInputProtected(true);

            if (isUpdateUser && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).openStsFlg_A1.getValue())) {

                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).rtrnTrkNtfyGrpCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).schdPickUpDt_A1.setInputProtected(false);
                // START 2019/05/09 T.Ogura [QC#50027,ADD]
                scrnMsg.A.no(i).rcvTsDplyTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).rqstRcvDtTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).techMeetTruckFlg_A1.setInputProtected(false);
                // END   2019/05/09 T.Ogura [QC#50027,ADD]
                scrnMsg.A.no(i).carrRsnCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).actlPickUpDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).inspCpltDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).carrCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).rtrnTrkStsCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).shpgSvcLvlCd_A1.setInputProtected(false);
                /** QC#15145 Enable Carrier Account# 11/22/2016 T.Tokutomi Start **/
                scrnMsg.A.no(i).carrAcctNum_A1.setInputProtected(false);
                /** QC#15145 Enable Carrier Account# 11/22/2016 T.Tokutomi End **/

                handler.setButtonEnabled(NLAL1100Constant.BTN_NTFY_GRP[0], i, true);
                handler.setButtonEnabled(NLAL1100Constant.BTN_SRCH_NTFY_GRP[0], i, true);
                handler.setButtonEnabled(NLAL1100Constant.BTN_CARR_INFO[0], i, true);
                handler.setButtonEnabled(NLAL1100Constant.BTN_SRCH_CARR[0], i, true);

            } else {

                scrnMsg.A.no(i).xxChkBox_A1.clear();

                handler.setButtonEnabled(NLAL1100Constant.BTN_NTFY_GRP[0], i, false);
                handler.setButtonEnabled(NLAL1100Constant.BTN_SRCH_NTFY_GRP[0], i, false);
                handler.setButtonEnabled(NLAL1100Constant.BTN_CARR_INFO[0], i, false);
                handler.setButtonEnabled(NLAL1100Constant.BTN_SRCH_CARR[0], i, false);
            }
        }

        // Comments Button 
        if (isUpdateUser && ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_C1)) {

            handler.setButtonProperties(NLAL1100Constant.BTN_CMNT_ADD[0], NLAL1100Constant.BTN_CMNT_ADD[1], NLAL1100Constant.BTN_CMNT_ADD[2], 1, null);
            handler.setButtonProperties(NLAL1100Constant.BTN_CMNT_SAVE[0], NLAL1100Constant.BTN_CMNT_SAVE[1], NLAL1100Constant.BTN_CMNT_SAVE[2], 1, null);
        }

        // Comments
        controlCommentFields(scrnMsg, isUpdateUser);

        // Apply Fields
        if (0 < scrnMsg.A.getValidCount() && isUpdateUser(userProfileService)) {

        	scrnMsg.rtrnTrkNtfyGrpCd_G.setInputProtected(false);
            scrnMsg.xxLinkAncr_GP.setInputProtected(false);
            scrnMsg.carrCd_G.setInputProtected(false);
            scrnMsg.xxLinkAncr_CA.setInputProtected(false);
            scrnMsg.schdPickUpDt_G.setInputProtected(false);
            // START 2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.rcvTsDplyTxt_G.setInputProtected(false);
            scrnMsg.rqstRcvDtTxt_G.setInputProtected(false);
            // END   2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.inspCpltDt_G.setInputProtected(false);
            scrnMsg.shpgSvcLvlCd_G.setInputProtected(false);
            scrnMsg.actlPickUpDt_G.setInputProtected(false);
            scrnMsg.carrRsnCd_G.setInputProtected(false);
            scrnMsg.rtrnTrkStsCd_G.setInputProtected(false);
            handler.setButtonEnabled(NLAL1100Constant.BTN_APPLY[0], true);

        } else {

        	scrnMsg.rtrnTrkNtfyGrpCd_G.setInputProtected(true);
            scrnMsg.xxLinkAncr_GP.setInputProtected(true);
            scrnMsg.carrCd_G.setInputProtected(true);
            scrnMsg.xxLinkAncr_CA.setInputProtected(true);
            scrnMsg.schdPickUpDt_G.setInputProtected(true);
            // START 2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.rcvTsDplyTxt_G.setInputProtected(true);
            scrnMsg.rqstRcvDtTxt_G.setInputProtected(true);
            // END   2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.inspCpltDt_G.setInputProtected(true);
            scrnMsg.shpgSvcLvlCd_G.setInputProtected(true);
            scrnMsg.actlPickUpDt_G.setInputProtected(true);
            scrnMsg.carrRsnCd_G.setInputProtected(true);
            scrnMsg.rtrnTrkStsCd_G.setInputProtected(true);
            handler.setButtonEnabled(NLAL1100Constant.BTN_APPLY[0], false);
        }

    }

    /**
     * controlCommentFields
     * @param scrnMsg NLAL1100BMsg
     * @param isUpdateUser boolean
     */
    public static void controlCommentFields(NLAL1100BMsg scrnMsg, boolean isUpdateUser) {

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            // QC#24010
            scrnMsg.C.no(i).xxScrItem130Txt_C1.setInputProtected(true);
            scrnMsg.C.no(i).xxTsDsp19Txt_C1.setInputProtected(true);

            if (isUpdateUser && ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).manCratFlg_C1.getValue())) {

                scrnMsg.C.no(i).rtrnTrxCmntTxt_C1.setInputProtected(false);

            } else {

                scrnMsg.C.no(i).rtrnTrxCmntTxt_C1.setInputProtected(true);
            }
        }
    }

    /**
     * setVisibilityDetail
     * @param scrnMsg NLAL1100BMsg
     * @param handler EZDCommonHandler
     */
    public static void setVisibilityDetail(NLAL1100BMsg scrnMsg, EZDCommonHandler handler) {

        String preRwsNum = "";

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // Clear
            scrnMsg.clearGUIAttribute(NLAL1100Constant.SCRN_ID, "xxSmryLineFlg#" + i);
            scrnMsg.clearGUIAttribute(NLAL1100Constant.SCRN_ID, "svcConfigMstrPk#" + i);
            scrnMsg.clearGUIAttribute(NLAL1100Constant.SCRN_ID, "Open_OrdEntry#" + i);
            scrnMsg.clearGUIAttribute(NLAL1100Constant.SCRN_ID, "Open_RcvOrd#" + i);
            // QC#23044
            scrnMsg.clearGUIAttribute(NLAL1100Constant.SCRN_ID, "Open_DelyInstn" + i);

            // Set Visibility
            if (preRwsNum.equals(scrnMsg.A.no(i).rwsNum_A1.getValue())) {

                // [+][-]bottom
                EZDGUIAttribute trxHdrNumBtm = new EZDGUIAttribute(NLAL1100Constant.SCRN_ID, "xxSmryLineFlg#" + i);
                trxHdrNumBtm.setVisibility(false);
                scrnMsg.addGUIAttribute(trxHdrNumBtm);

                // Config PK
                EZDGUIAttribute svcConfigMstrPk = new EZDGUIAttribute(NLAL1100Constant.SCRN_ID, "svcConfigMstrPk#" + i);
                svcConfigMstrPk.setVisibility(false);
                scrnMsg.addGUIAttribute(svcConfigMstrPk);

                // Sales Order Number
                EZDGUIAttribute trxHdrNumLink = new EZDGUIAttribute(NLAL1100Constant.SCRN_ID, "Open_OrdEntry#" + i);
                trxHdrNumLink.setVisibility(false);
                scrnMsg.addGUIAttribute(trxHdrNumLink);

                // RWS Number
                EZDGUIAttribute rwsNumLink = new EZDGUIAttribute(NLAL1100Constant.SCRN_ID, "Open_RcvOrd#" + i);
                rwsNumLink.setVisibility(false);
                scrnMsg.addGUIAttribute(rwsNumLink);

                // QC#23044 Delivery Instruction
                EZDGUIAttribute delyInstnBtm = new EZDGUIAttribute(NLAL1100Constant.SCRN_ID, "Open_DelyInstn" + i);
                delyInstnBtm.setVisibility(false);
                scrnMsg.addGUIAttribute(delyInstnBtm);
            }

            preRwsNum = scrnMsg.A.no(i).rwsNum_A1.getValue();
        }
    }

    /**
     * setBgColorDetail
     * @param scrnMsg NLAL1100BMsg
     */
    public static void setBgColorDetail(NLAL1100BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NLAL1100Constant.SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLAL1100Bean.A, scrnMsg.A);
    }

    /**
     * setBgColorCommnets
     * @param scrnMsg NLAL1100BMsg
     */
    public static void setBgColorCommnets(NLAL1100BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NLAL1100Constant.SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLAL1100Bean.C, scrnMsg.C);
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(NLAL1100Constant.BIZ_APP_ID);

        if (functionIds.contains(NLAL1100Constant.FUNC_EDIT)) {

            return true;

        } else {

            return false;
        }
    }

    /**
     * Check Search Criteria Items.
     * @param scrnMsg NLAL1100BMsg
     */
    public static void checkInputSearch(NLAL1100BMsg scrnMsg) {

        if (isReqHdrAllBlank(scrnMsg)) {

            scrnMsg.rmaNum_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.soNum_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.rwsNum_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.rtrnLineDplyStsCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.ordAgingBcktCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.rtlWhCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.shipToCustCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.carrCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.slsRepBrCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.rqstPickUpDt_HF.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.rqstPickUpDt_HT.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.schdPickUpDt_HF.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.schdPickUpDt_HT.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.actlPickUpDt_HF.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.actlPickUpDt_HT.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.serNum_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.svcConfigMstrPk_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
            scrnMsg.psnCd_H1.setErrorInfo(1, NLAL1100Constant.NLZM2276E);
        }

        // check from/to Date.
        if (ZYPCommonFunc.hasValue(scrnMsg.rqstPickUpDt_HF) && ZYPCommonFunc.hasValue(scrnMsg.rqstPickUpDt_HT)) {

            if (1 == ZYPDateUtil.compare(scrnMsg.rqstPickUpDt_HF.getValue(), scrnMsg.rqstPickUpDt_HT.getValue())) {

                scrnMsg.rqstPickUpDt_HF.setErrorInfo(1, NLAL1100Constant.NLZM2277E, new String[] {"Request Pickup Date To", "Request Pickup Date From" });
                scrnMsg.rqstPickUpDt_HT.setErrorInfo(1, NLAL1100Constant.NLZM2277E, new String[] {"Request Pickup Date To", "Request Pickup Date From" });
                scrnMsg.addCheckItem(scrnMsg.rqstPickUpDt_HF);
                scrnMsg.addCheckItem(scrnMsg.rqstPickUpDt_HT);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.schdPickUpDt_HF) && ZYPCommonFunc.hasValue(scrnMsg.schdPickUpDt_HT)) {

            if (1 == ZYPDateUtil.compare(scrnMsg.schdPickUpDt_HF.getValue(), scrnMsg.schdPickUpDt_HT.getValue())) {

                scrnMsg.schdPickUpDt_HF.setErrorInfo(1, NLAL1100Constant.NLZM2277E, new String[] {"Schedule Pickup Date To", "Schedule Pickup Date From" });
                scrnMsg.schdPickUpDt_HT.setErrorInfo(1, NLAL1100Constant.NLZM2277E, new String[] {"Schedule Pickup Date To", "Schedule Pickup Date From" });
                scrnMsg.addCheckItem(scrnMsg.schdPickUpDt_HF);
                scrnMsg.addCheckItem(scrnMsg.schdPickUpDt_HT);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.actlPickUpDt_HF) && ZYPCommonFunc.hasValue(scrnMsg.actlPickUpDt_HT)) {

            if (1 == ZYPDateUtil.compare(scrnMsg.actlPickUpDt_HF.getValue(), scrnMsg.actlPickUpDt_HT.getValue())) {

                scrnMsg.actlPickUpDt_HF.setErrorInfo(1, NLAL1100Constant.NLZM2277E, new String[] {"Actual Pickup Date To", "Actual Pickup Date From" });
                scrnMsg.actlPickUpDt_HT.setErrorInfo(1, NLAL1100Constant.NLZM2277E, new String[] {"Actual Pickup Date To", "Actual Pickup Date From" });
                scrnMsg.addCheckItem(scrnMsg.actlPickUpDt_HF);
                scrnMsg.addCheckItem(scrnMsg.actlPickUpDt_HT);
            }
        }

        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    /**
     * isReqHdrAllBlank
     * @param scrnMsg NLAL1100BMsg
     */
    private static boolean isReqHdrAllBlank(NLAL1100BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.rmaNum_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.soNum_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rwsNum_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rtrnLineDplyStsCd_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.ordAgingBcktCd_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.carrCd_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.slsRepBrCd_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rqstPickUpDt_HF)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rqstPickUpDt_HT)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdPickUpDt_HF)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdPickUpDt_HT)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.actlPickUpDt_HF)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.actlPickUpDt_HT)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.serNum_H1)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_H1)) {

            return false;
        } else if (ZYPCommonFunc.hasValue(scrnMsg.psnCd_H1)) {

            return false;
        }

        return true;
    }

    /**
     * <pre>
     * The input scrnMsg is cleared. 
     * </pre>
     * @param arg0 NLAL1100BMsg
     */
    public static void setNameForMessage(NLAL1100BMsg arg0) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) arg0;

        scrnMsg.srchOptPk_S1.setNameForMessage("Save Search Option");
        scrnMsg.srchOptNm_TX.setNameForMessage("Search Option Name");
        scrnMsg.rmaNum_H1.setNameForMessage("RMA Number");
        scrnMsg.soNum_H1.setNameForMessage("Shipping Order Number");
        scrnMsg.rwsNum_H1.setNameForMessage("RWS Number");
        scrnMsg.rtrnLineDplyStsCd_H1.setNameForMessage("RMA Line Status");
        scrnMsg.ordAgingBcktCd_H1.setNameForMessage("Aging Bucket");
        scrnMsg.rtlWhCd_H1.setNameForMessage("Warehouse");
        scrnMsg.rtlWhNm_H1.setNameForMessage("Warehouse");
        scrnMsg.rtlSwhCd_H1.setNameForMessage("Sub Warehouse");
        scrnMsg.rtlSwhNm_H1.setNameForMessage("Sub Warehouse");
        scrnMsg.shipToCustCd_H1.setNameForMessage("Pickup Customer");
        scrnMsg.dsAcctNm_H1.setNameForMessage("Pickup Customer");
        scrnMsg.carrCd_H1.setNameForMessage("Carrier");
        scrnMsg.carrNm_H1.setNameForMessage("Carrier");
        scrnMsg.slsRepBrCd_H1.setNameForMessage("Sales Rep");
        scrnMsg.tocNm_H1.setNameForMessage("Sales Rep");
        scrnMsg.rqstPickUpDt_HF.setNameForMessage("Request Pickup Date From");
        scrnMsg.rqstPickUpDt_HT.setNameForMessage("Request Pickup Date To");
        scrnMsg.schdPickUpDt_HF.setNameForMessage("Schedule Pickup Date From");
        scrnMsg.schdPickUpDt_HT.setNameForMessage("Schedule Pickup Date To");
        scrnMsg.actlPickUpDt_HF.setNameForMessage("Actual Pickup Date From");
        scrnMsg.actlPickUpDt_HT.setNameForMessage("Actual Pickup Date To");
        scrnMsg.serNum_H1.setNameForMessage("Serial Number");
        scrnMsg.svcConfigMstrPk_H1.setNameForMessage("Configuration ID");
        scrnMsg.xxPageShowCurNum.setNameForMessage("Current Page Number");
        scrnMsg.psnCd_H1.setNameForMessage("Coordinator");
        // QC#18669 Add.
        scrnMsg.rtrnTrkNtfyGrpCd_G.setNameForMessage("Assigned to Group");
        scrnMsg.carrCd_G.setNameForMessage("Carrier");
        scrnMsg.schdPickUpDt_G.setNameForMessage("Scheduled Pickup Date");
        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        scrnMsg.rcvTsDplyTxt_G.setNameForMessage("Scheduled Pickup Time");
        scrnMsg.rqstRcvDtTxt_G.setNameForMessage("Scheduled Pickup Time AM/PM");
        // END   2019/05/09 T.Ogura [QC#50027,ADD]
        scrnMsg.inspCpltDt_G.setNameForMessage("Inspection Completion Date");
        scrnMsg.actlPickUpDt_G.setNameForMessage("Actual Pickup Date");
        scrnMsg.carrRsnCd_G.setNameForMessage("Carrier Reason");
        scrnMsg.shpgSvcLvlCd_G.setNameForMessage("Pickup Service Level");
        scrnMsg.rtrnTrkStsCd_G.setNameForMessage("Pickup Status");
    }

    /**
     * addCheckItemSaveSrch
     * @param scrnMsg NLAL1100BMsg
     */
    public static void addCheckItemSaveSrch(NLAL1100BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S1);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_TX);
    }

    /**
     * addCheckItemHeader
     * @param scrnMsg NLAL1100BMsg
     */
    public static void addCheckItemHeader(NLAL1100BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.rmaNum_H1);
        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rwsNum_H1);
        scrnMsg.addCheckItem(scrnMsg.rtrnLineDplyStsCd_H1);
        scrnMsg.addCheckItem(scrnMsg.ordAgingBcktCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.carrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.slsRepBrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rqstPickUpDt_HF);
        scrnMsg.addCheckItem(scrnMsg.rqstPickUpDt_HT);
        scrnMsg.addCheckItem(scrnMsg.schdPickUpDt_HF);
        scrnMsg.addCheckItem(scrnMsg.schdPickUpDt_HT);
        scrnMsg.addCheckItem(scrnMsg.actlPickUpDt_HF);
        scrnMsg.addCheckItem(scrnMsg.actlPickUpDt_HT);
        scrnMsg.addCheckItem(scrnMsg.serNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk_H1);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H1);
    }

    /**
     * addCheckItemDetail
     * @param scrnMsg NLAL1100BMsg
     */
    public static void addCheckItemDetail(NLAL1100BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtrnTrkNtfyGrpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdPickUpDt_A1);
            // START 2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rcvTsDplyTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDtTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).techMeetTruckFlg_A1);
            // END   2019/05/09 T.Ogura [QC#50027,ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).actlPickUpDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).inspCpltDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).carrCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtrnTrkStsCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgSvcLvlCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).carrAcctNum_A1);
        }

        addCheckItemComment(scrnMsg);
    }

    /**
     * addCheckItemComment
     * @param scrnMsg NLAL1100BMsg
     */
    public static void addCheckItemComment(NLAL1100BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).manCratFlg_C1.getValue())) {

                scrnMsg.addCheckItem(scrnMsg.C.no(i).rtrnTrxCmntTxt_C1);
            }
        }
    }

    /**
     * isApplyAllBlank
     * @param scrnMsg
     * @return
     */
    public static boolean isApplyAllBlank(NLAL1100BMsg scrnMsg) {

    	if (ZYPCommonFunc.hasValue(scrnMsg.rtrnTrkNtfyGrpCd_G)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.carrCd_G)) {

            return false;

		} else if (ZYPCommonFunc.hasValue(scrnMsg.schdPickUpDt_G)) {

			return false;

        // START 2019/05/09 T.Ogura [QC#50027,ADD]
        } else if (ZYPCommonFunc.hasValue(scrnMsg.rcvTsDplyTxt_G)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDtTxt_G)) {

            return false;

        // END   2019/05/09 T.Ogura [QC#50027,ADD]
        }  else if (ZYPCommonFunc.hasValue(scrnMsg.inspCpltDt_G)) {

			return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_G)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.actlPickUpDt_G)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.carrRsnCd_G)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rtrnTrkStsCd_G)) {

        	return false;

        }

        return true;
    }
}
