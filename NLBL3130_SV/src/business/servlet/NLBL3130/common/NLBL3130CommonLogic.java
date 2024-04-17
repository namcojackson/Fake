/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3130.common;

import java.util.List;

import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3130.NLBL3130BMsg;
import business.servlet.NLBL3130.constant.NLBL3130Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NLBL3130 Delivery Instruction
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 04/13/2016   CSAI            Y.Imazu         Update          QC#5868
 * 07/14/2016   CSAI            Y.Imazu         Update          QC#11900
 * 2017/01/16   CITS            T.Kikuhara      Update          QC#15619 and QC#16256
 * 2018/01/12   CITS            T.Tokutomi      Update          QC#18460_Sol#087
 * 01/23/2018   CITS            K.Ogino         Update          QC#23044
 * 06/05/2018   CITS            K.Ogino         Update          QC#25233
 *</pre>
 */
public class NLBL3130CommonLogic implements NLBL3130Constant {

    /**
     * The method explanation: The display control of the screen item
     * for Initialized screen
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3130BMsg
     */
    public static void cntrlScrnItemDispInit(EZDCommonHandler handler, NLBL3130BMsg scrnMsg) {

        // column input protection
        // true : block entry
        // false : input possible
        // Header
        scrnMsg.trxHdrNum_H1.setInputProtected(true);
        scrnMsg.soNum_H1.setInputProtected(true);
        // QC#23044
        scrnMsg.rwsNum_H1.setInputProtected(true);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);

        // Site Information
        scrnMsg.xxScrItem7Txt_OF.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_OT.setInputProtected(false);
        scrnMsg.secClncReqTxt_Y.setInputProtected(false);
        scrnMsg.secClncReqTxt_N.setInputProtected(false);
        scrnMsg.insCertReqTxt_Y.setInputProtected(false);
        scrnMsg.insCertReqTxt_N.setInputProtected(false);
        scrnMsg.flCovTxt_H1.setInputProtected(false);
        scrnMsg.trctrAndTrailAccsTxt_Y.setInputProtected(false);
        scrnMsg.trctrAndTrailAccsTxt_N.setInputProtected(false);
        scrnMsg.loadDockAvalTxt_Y.setInputProtected(false);
        scrnMsg.loadDockAvalTxt_N.setInputProtected(false);
        scrnMsg.rampAvalTxt_Y.setInputProtected(false);
        scrnMsg.rampAvalTxt_N.setInputProtected(false);
        scrnMsg.stepAvalTxt_Y.setInputProtected(false);
        scrnMsg.stepAvalTxt_N.setInputProtected(false);
        scrnMsg.insdStepNum_H1.setInputProtected(false);
        scrnMsg.otsdStepNum_H1.setInputProtected(false);
        scrnMsg.frontDoorAvalTxt_Y.setInputProtected(false);
        scrnMsg.frontDoorAvalTxt_N.setInputProtected(false);
        scrnMsg.backDoorAvalTxt_Y.setInputProtected(false);
        scrnMsg.backDoorAvalTxt_N.setInputProtected(false);
        scrnMsg.pwrOtltConfigTxt_Y.setInputProtected(false);
        scrnMsg.pwrOtltConfigTxt_N.setInputProtected(false);
        scrnMsg.sgnOnBldgRdsdTxt_Y.setInputProtected(false);
        scrnMsg.sgnOnBldgRdsdTxt_N.setInputProtected(false);
        scrnMsg.bldgStryNum_H1.setInputProtected(false);
        scrnMsg.bldgGurdNtfyTxt_Y.setInputProtected(false);
        scrnMsg.bldgGurdNtfyTxt_N.setInputProtected(false);
        scrnMsg.indlParkTxt_Y.setInputProtected(false);
        scrnMsg.indlParkTxt_N.setInputProtected(false);
        scrnMsg.bizParkTxt_Y.setInputProtected(false);
        scrnMsg.bizParkTxt_N.setInputProtected(false);
        scrnMsg.proBldgTxt_Y.setInputProtected(false);
        scrnMsg.proBldgTxt_N.setInputProtected(false);
        scrnMsg.shpngCtrTxt_Y.setInputProtected(false);
        scrnMsg.shpngCtrTxt_N.setInputProtected(false);
        scrnMsg.pvtResTxt_Y.setInputProtected(false);
        scrnMsg.pvtResTxt_N.setInputProtected(false);

        // Building Entrance Dimensions
        scrnMsg.bldgEntDoorHgt_A1.setInputProtected(false);
        scrnMsg.bldgEntDoorWdt_A1.setInputProtected(false);
        scrnMsg.crdrWdt_A1.setInputProtected(false);
        scrnMsg.doorWdt_A1.setInputProtected(false);

        // QC#23044 Instructions Mod QC#25233
        scrnMsg.shpgInstnCmntTxt_B1.setInputProtected(false);
        scrnMsg.delyInstnCmntTxt_B1.setInputProtected(false);
        scrnMsg.istlInstnCmntTxt_B1.setInputProtected(false);
        scrnMsg.techInstnCmntTxt_B1.setInputProtected(false);

        // Elevator Information & Dimensions
        scrnMsg.elevAvalTxt_Y.setInputProtected(false);
        scrnMsg.elevAvalTxt_N.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_EF.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_ET.setInputProtected(false);
        scrnMsg.elevApptReqTxt_Y.setInputProtected(false);
        scrnMsg.elevApptReqTxt_N.setInputProtected(false);
        scrnMsg.elevCtacPsnNm_C1.setInputProtected(false);
        scrnMsg.elevCtacTelNum_C1.setInputProtected(false);
        scrnMsg.elevWdt_C1.setInputProtected(false);
        scrnMsg.elevDepthNum_C1.setInputProtected(false);
        scrnMsg.elevCapWt_C1.setInputProtected(false);
        scrnMsg.elevDoorHgt_C1.setInputProtected(false);
        scrnMsg.elevDoorWdt_C1.setInputProtected(false);

        // Additional Comments
        scrnMsg.delyInstnAddlCmntTxt_D1.setInputProtected(false);

        // Validation
        scrnMsg.xxPsnNm_E1.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_E1.setInputProtected(true);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTsDsp19Txt_E1, ZYPDateUtil.formatEzd14ToDisp(scrnMsg.xxTsDsp19Txt_E1.getValue()));

        // QC#18460_Sol#087 Add column
        // Header
        scrnMsg.tmZoneCd_H1.setInputProtected(true);
        // Site Information
        scrnMsg.xxTmFrameTxt_OF.setInputProtected(false);
        scrnMsg.xxTmFrameTxt_OT.setInputProtected(false);
        scrnMsg.cmpyInfoFlNm_H1.setInputProtected(false);
        scrnMsg.loadDockHgt_H1.setInputProtected(false);
        scrnMsg.delyTrnspOptCd_H1.setInputProtected(false);
        scrnMsg.stairCrawReqFlg_Y.setInputProtected(false);
        scrnMsg.stairCrawReqFlg_N.setInputProtected(false);
        scrnMsg.stairAndLdgWdt_H1.setInputProtected(false);
        // Elevator Information & Dimensions
        scrnMsg.xxTmFrameTxt_EF.setInputProtected(false);
        scrnMsg.xxTmFrameTxt_ET.setInputProtected(false);
        // Validation
        scrnMsg.custInfoBoFlg_Y.setInputProtected(false);
        scrnMsg.custInfoBoFlg_N.setInputProtected(false);
        scrnMsg.pickUpXtrTonerFlg_Y.setInputProtected(false);
        scrnMsg.pickUpXtrTonerFlg_N.setInputProtected(false);
        scrnMsg.pickUpAtSameTmFlg_Y.setInputProtected(false);
        scrnMsg.pickUpAtSameTmFlg_N.setInputProtected(false);

        // common button protection
        // 0 : inactive
        // 1 : active
        // QC#15619 and QC#16256 and QC#23044 update start
        if ((ZYPCommonFunc.hasValue(scrnMsg.soNum_H1) || ZYPCommonFunc.hasValue(scrnMsg.rwsNum_H1)) && isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        } else {
            scrnMsg.setInputProtected(true);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_P1)) {
            scrnMsg.xxLinkAncr_H1.setInputProtected(true);
        } else {
            scrnMsg.xxLinkAncr_H1.setInputProtected(false);
        }
        // QC#15619 and QC#16256 update end

        changeFormatDate(scrnMsg);
    }

    /**
     * The method explanation: The display control of the screen item
     * when the search button action is done.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3130BMsg
     */
    public static void cntrlScrnItemDispSearch(EZDCommonHandler handler, NLBL3130BMsg scrnMsg) {

        // column input protection
        // true : block entry
        // false : input possible
        // Header
        scrnMsg.trxHdrNum_H1.setInputProtected(true);
        scrnMsg.soNum_H1.setInputProtected(true);
        // QC#23044
        scrnMsg.rwsNum_H1.setInputProtected(true);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);

        // Site Information
        scrnMsg.xxScrItem7Txt_OF.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_OT.setInputProtected(false);
        scrnMsg.secClncReqTxt_Y.setInputProtected(false);
        scrnMsg.secClncReqTxt_N.setInputProtected(false);
        scrnMsg.insCertReqTxt_Y.setInputProtected(false);
        scrnMsg.insCertReqTxt_N.setInputProtected(false);
        scrnMsg.flCovTxt_H1.setInputProtected(false);
        scrnMsg.trctrAndTrailAccsTxt_Y.setInputProtected(false);
        scrnMsg.trctrAndTrailAccsTxt_N.setInputProtected(false);
        scrnMsg.loadDockAvalTxt_Y.setInputProtected(false);
        scrnMsg.loadDockAvalTxt_N.setInputProtected(false);
        scrnMsg.rampAvalTxt_Y.setInputProtected(false);
        scrnMsg.rampAvalTxt_N.setInputProtected(false);
        scrnMsg.stepAvalTxt_Y.setInputProtected(false);
        scrnMsg.stepAvalTxt_N.setInputProtected(false);
        scrnMsg.insdStepNum_H1.setInputProtected(false);
        scrnMsg.otsdStepNum_H1.setInputProtected(false);
        scrnMsg.frontDoorAvalTxt_Y.setInputProtected(false);
        scrnMsg.frontDoorAvalTxt_N.setInputProtected(false);
        scrnMsg.backDoorAvalTxt_Y.setInputProtected(false);
        scrnMsg.backDoorAvalTxt_N.setInputProtected(false);
        scrnMsg.pwrOtltConfigTxt_Y.setInputProtected(false);
        scrnMsg.pwrOtltConfigTxt_N.setInputProtected(false);
        scrnMsg.sgnOnBldgRdsdTxt_Y.setInputProtected(false);
        scrnMsg.sgnOnBldgRdsdTxt_N.setInputProtected(false);
        scrnMsg.bldgStryNum_H1.setInputProtected(false);
        scrnMsg.bldgGurdNtfyTxt_Y.setInputProtected(false);
        scrnMsg.bldgGurdNtfyTxt_N.setInputProtected(false);
        scrnMsg.indlParkTxt_Y.setInputProtected(false);
        scrnMsg.indlParkTxt_N.setInputProtected(false);
        scrnMsg.bizParkTxt_Y.setInputProtected(false);
        scrnMsg.bizParkTxt_N.setInputProtected(false);
        scrnMsg.proBldgTxt_Y.setInputProtected(false);
        scrnMsg.proBldgTxt_N.setInputProtected(false);
        scrnMsg.shpngCtrTxt_Y.setInputProtected(false);
        scrnMsg.shpngCtrTxt_N.setInputProtected(false);
        scrnMsg.pvtResTxt_Y.setInputProtected(false);
        scrnMsg.pvtResTxt_N.setInputProtected(false);

        // Building Entrance Dimensions
        scrnMsg.bldgEntDoorHgt_A1.setInputProtected(false);
        scrnMsg.bldgEntDoorWdt_A1.setInputProtected(false);
        scrnMsg.crdrWdt_A1.setInputProtected(false);
        scrnMsg.doorWdt_A1.setInputProtected(false);

        // QC#23044 Instructions Mod QC#25233
        scrnMsg.shpgInstnCmntTxt_B1.setInputProtected(false);
        scrnMsg.delyInstnCmntTxt_B1.setInputProtected(false);
        scrnMsg.istlInstnCmntTxt_B1.setInputProtected(false);
        scrnMsg.techInstnCmntTxt_B1.setInputProtected(false);

        // Elevator Information & Dimensions
        scrnMsg.elevAvalTxt_Y.setInputProtected(false);
        scrnMsg.elevAvalTxt_N.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_EF.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_ET.setInputProtected(false);
        scrnMsg.elevApptReqTxt_Y.setInputProtected(false);
        scrnMsg.elevApptReqTxt_N.setInputProtected(false);
        scrnMsg.elevCtacPsnNm_C1.setInputProtected(false);
        scrnMsg.elevCtacTelNum_C1.setInputProtected(false);
        scrnMsg.elevWdt_C1.setInputProtected(false);
        scrnMsg.elevDepthNum_C1.setInputProtected(false);
        scrnMsg.elevCapWt_C1.setInputProtected(false);
        scrnMsg.elevDoorHgt_C1.setInputProtected(false);
        scrnMsg.elevDoorWdt_C1.setInputProtected(false);

        // Additional Comments
        scrnMsg.delyInstnAddlCmntTxt_D1.setInputProtected(false);

        // Validation
        scrnMsg.xxPsnNm_E1.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_E1.setInputProtected(true);
        scrnMsg.xxTsDsp19Txt_E1.setValue(ZYPDateUtil.formatEzd14ToDisp(scrnMsg.xxTsDsp19Txt_E1.getValue()));

        // QC#18460_Sol#087 Add column
        // Header
        scrnMsg.tmZoneCd_H1.setInputProtected(true);
        // Site Information
        scrnMsg.xxTmFrameTxt_OF.setInputProtected(false);
        scrnMsg.xxTmFrameTxt_OT.setInputProtected(false);
        scrnMsg.cmpyInfoFlNm_H1.setInputProtected(false);
        scrnMsg.loadDockHgt_H1.setInputProtected(false);
        scrnMsg.delyTrnspOptCd_H1.setInputProtected(false);
        scrnMsg.stairCrawReqFlg_Y.setInputProtected(false);
        scrnMsg.stairCrawReqFlg_N.setInputProtected(false);
        scrnMsg.stairAndLdgWdt_H1.setInputProtected(false);
        // Elevator Information & Dimensions
        scrnMsg.xxTmFrameTxt_EF.setInputProtected(false);
        scrnMsg.xxTmFrameTxt_ET.setInputProtected(false);
        // Validation
        scrnMsg.custInfoBoFlg_Y.setInputProtected(false);
        scrnMsg.custInfoBoFlg_N.setInputProtected(false);
        scrnMsg.pickUpXtrTonerFlg_Y.setInputProtected(false);
        scrnMsg.pickUpXtrTonerFlg_N.setInputProtected(false);
        scrnMsg.pickUpAtSameTmFlg_Y.setInputProtected(false);
        scrnMsg.pickUpAtSameTmFlg_N.setInputProtected(false);

        // common button protection
        // 0 : inactive
        // 1 : active
        // QC#15619 and QC#16256 update start
        if (isUpdatable()) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        } else {
            scrnMsg.setInputProtected(true);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_P1)) {
            scrnMsg.xxLinkAncr_H1.setInputProtected(true);
        } else {
            scrnMsg.xxLinkAncr_H1.setInputProtected(false);
        }
        // QC#15619 and QC#16256 update end

        changeFormatDate(scrnMsg);
    }

    /**
     * <pre>
     * The input scrnMsg is cleared. 
     * </pre>
     * @param arg0 NLBL3130BMsg
     */
    public static void setNameForMessage(NLBL3130BMsg arg0) {

        NLBL3130BMsg scrnMsg = (NLBL3130BMsg) arg0;

        scrnMsg.trxHdrNum_H1.setNameForMessage(NAME_FOR_MESSAGE_TRX_HDR_NUM);
        scrnMsg.soNum_H1.setNameForMessage(NAME_FOR_MESSAGE_SO_NUM);
        // QC#23044
        scrnMsg.rwsNum_H1.setNameForMessage(NAME_FOR_MESSAGE_RWS_NUM);
        scrnMsg.dsAcctNm_H1.setNameForMessage(NAME_FOR_MESSAGE_DS_ACCT_NM);
        scrnMsg.xxScrItem7Txt_OF.setNameForMessage(NAME_FOR_MESSAGE_OPS_FROM_HOUR_MN);
        scrnMsg.xxScrItem7Txt_OT.setNameForMessage(NAME_FOR_MESSAGE_OPS_TO_HOUR_MN);
        scrnMsg.secClncReqTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_SEC_CLNC_REQ_FLG);
        scrnMsg.secClncReqTxt_N.setNameForMessage(NAME_FOR_MESSAGE_SEC_CLNC_REQ_FLG);
        scrnMsg.insCertReqTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_INS_CERT_REQ_FLG);
        scrnMsg.insCertReqTxt_N.setNameForMessage(NAME_FOR_MESSAGE_INS_CERT_REQ_FLG);
        scrnMsg.flCovTxt_H1.setNameForMessage(NAME_FOR_MESSAGE_FL_COV_TXT);
        scrnMsg.trctrAndTrailAccsTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_TRCTR_AND_TRAIL_ACCS_FLG);
        scrnMsg.trctrAndTrailAccsTxt_N.setNameForMessage(NAME_FOR_MESSAGE_TRCTR_AND_TRAIL_ACCS_FLG);
        scrnMsg.loadDockAvalTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_LOAD_DOCK_AVAL_FLG);
        scrnMsg.loadDockAvalTxt_N.setNameForMessage(NAME_FOR_MESSAGE_LOAD_DOCK_AVAL_FLG);
        scrnMsg.rampAvalTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_RAMP_AVAL_FLG);
        scrnMsg.rampAvalTxt_N.setNameForMessage(NAME_FOR_MESSAGE_RAMP_AVAL_FLG);
        scrnMsg.stepAvalTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_STEP_AVAL_FLG);
        scrnMsg.stepAvalTxt_N.setNameForMessage(NAME_FOR_MESSAGE_STEP_AVAL_FLG);
        scrnMsg.insdStepNum_H1.setNameForMessage(NAME_FOR_MESSAGE_INSD_STEP_NUM);
        scrnMsg.otsdStepNum_H1.setNameForMessage(NAME_FOR_MESSAGE_OTSD_STEP_NUM);
        scrnMsg.frontDoorAvalTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_FRONT_DOOR_AVAL_FLG);
        scrnMsg.frontDoorAvalTxt_N.setNameForMessage(NAME_FOR_MESSAGE_FRONT_DOOR_AVAL_FLG);
        scrnMsg.backDoorAvalTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_BACK_DOOR_AVAL_FLG);
        scrnMsg.backDoorAvalTxt_N.setNameForMessage(NAME_FOR_MESSAGE_BACK_DOOR_AVAL_FLG);
        scrnMsg.pwrOtltConfigTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_PWR_OTLT_CONFIG_FLG);
        scrnMsg.pwrOtltConfigTxt_N.setNameForMessage(NAME_FOR_MESSAGE_PWR_OTLT_CONFIG_FLG);
        scrnMsg.sgnOnBldgRdsdTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_SGN_ON_BLDG_RDSD_FLG);
        scrnMsg.sgnOnBldgRdsdTxt_N.setNameForMessage(NAME_FOR_MESSAGE_SGN_ON_BLDG_RDSD_FLG);
        scrnMsg.bldgStryNum_H1.setNameForMessage(NAME_FOR_MESSAGE_BLDG_STRY_NUM);
        scrnMsg.bldgGurdNtfyTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_BLDG_GURD_NTFY_FLG);
        scrnMsg.bldgGurdNtfyTxt_N.setNameForMessage(NAME_FOR_MESSAGE_BLDG_GURD_NTFY_FLG);
        scrnMsg.indlParkTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_INDL_PARK_FLG);
        scrnMsg.indlParkTxt_N.setNameForMessage(NAME_FOR_MESSAGE_INDL_PARK_FLG);
        scrnMsg.bizParkTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_BIZ_PARK_FLG);
        scrnMsg.bizParkTxt_N.setNameForMessage(NAME_FOR_MESSAGE_BIZ_PARK_FLG);
        scrnMsg.proBldgTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_PRO_BLDG_FLG);
        scrnMsg.proBldgTxt_N.setNameForMessage(NAME_FOR_MESSAGE_PRO_BLDG_FLG);
        scrnMsg.shpngCtrTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_SHPNG_CTR_FLG);
        scrnMsg.shpngCtrTxt_N.setNameForMessage(NAME_FOR_MESSAGE_SHPNG_CTR_FLG);
        scrnMsg.pvtResTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_PVT_RES_FLG);
        scrnMsg.pvtResTxt_N.setNameForMessage(NAME_FOR_MESSAGE_PVT_RES_FLG);
        scrnMsg.bldgEntDoorHgt_A1.setNameForMessage(NAME_FOR_MESSAGE_BLDG_ENT_DOOR_HGT);
        scrnMsg.bldgEntDoorWdt_A1.setNameForMessage(NAME_FOR_MESSAGE_BLDG_ENT_DOOR_WDT);
        scrnMsg.crdrWdt_A1.setNameForMessage(NAME_FOR_MESSAGE_CRDR_WDT);
        scrnMsg.doorWdt_A1.setNameForMessage(NAME_FOR_MESSAGE_DOOR_WDT);
        scrnMsg.shpgInstnCmntTxt_B1.setNameForMessage(NAME_FOR_MESSAGE_SHPG_INSTN_CMNT_TXT);
        scrnMsg.delyInstnCmntTxt_B1.setNameForMessage(NAME_FOR_MESSAGE_DELY_INSTN_CMNT_TXT);
        scrnMsg.istlInstnCmntTxt_B1.setNameForMessage(NAME_FOR_MESSAGE_ISTL_INSTN_CMNT_TXT);
        scrnMsg.techInstnCmntTxt_B1.setNameForMessage(NAME_FOR_MESSAGE_TECH_INSTN_CMNT_TXT);
        scrnMsg.elevAvalTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_ELEV_AVAL_FLG);
        scrnMsg.elevAvalTxt_N.setNameForMessage(NAME_FOR_MESSAGE_ELEV_AVAL_FLG);
        scrnMsg.xxScrItem7Txt_EF.setNameForMessage(NAME_FOR_MESSAGE_ELEV_AVAL_FROM_HOUR_MN);
        scrnMsg.xxScrItem7Txt_ET.setNameForMessage(NAME_FOR_MESSAGE_ELEV_AVAL_TO_HOUR_MN);
        scrnMsg.elevApptReqTxt_Y.setNameForMessage(NAME_FOR_MESSAGE_ELEV_APPT_REQ_FLG);
        scrnMsg.elevApptReqTxt_N.setNameForMessage(NAME_FOR_MESSAGE_ELEV_APPT_REQ_FLG);
        scrnMsg.elevCtacPsnNm_C1.setNameForMessage(NAME_FOR_MESSAGE_ELEV_CTAC_PSN_NM);
        scrnMsg.elevCtacTelNum_C1.setNameForMessage(NAME_FOR_MESSAGE_ELEV_CTAC_TEL_NUM);
        scrnMsg.elevWdt_C1.setNameForMessage(NAME_FOR_MESSAGE_ELEV_WDT);
        scrnMsg.elevDepthNum_C1.setNameForMessage(NAME_FOR_MESSAGE_ELEV_DEPTH_NUM);
        scrnMsg.elevCapWt_C1.setNameForMessage(NAME_FOR_MESSAGE_ELEV_CAP_WT);
        scrnMsg.elevDoorHgt_C1.setNameForMessage(NAME_FOR_MESSAGE_ELEV_DOOR_HGT);
        scrnMsg.elevDoorWdt_C1.setNameForMessage(NAME_FOR_MESSAGE_ELEV_DOOR_WDT);
        scrnMsg.delyInstnAddlCmntTxt_D1.setNameForMessage(NAME_FOR_MESSAGE_DELY_INSTN_ADDL_CMNT_TXT);
        scrnMsg.xxPsnNm_E1.setNameForMessage(NAME_FOR_MESSAGE_VLD_USR_ID);
        scrnMsg.xxTsDsp19Txt_E1.setNameForMessage(NAME_FOR_MESSAGE_VLD_TS);
        scrnMsg.dsCpoConfigPk_G1.setNameForMessage(NAME_FOR_MESSAGE_DS_CPO_CONFIG_PK);
        scrnMsg.cpoOrdNum_P1.setNameForMessage(NAME_FOR_MESSAGE_CPO_ORD_NUM);
        scrnMsg.dsOrdPosnNum_P1.setNameForMessage(NAME_FOR_MESSAGE_DS_ORD_POSN_NUM);
        scrnMsg.configCatgCd_P1.setNameForMessage(NAME_FOR_MESSAGE_CONFIG_CATG_CD);

        // QC#18460_Sol#087 Add column
        // Header
        scrnMsg.tmZoneCd_H1.setNameForMessage(NAME_FOR_MESSAGE_TIME_ZONE_CD);
        // Site Information
        scrnMsg.xxTmFrameTxt_OF.setNameForMessage(NAME_FOR_MESSAGE_OPS_FROM_HOUR_MN);
        scrnMsg.xxTmFrameTxt_OT.setNameForMessage(NAME_FOR_MESSAGE_OPS_TO_HOUR_MN);
        scrnMsg.cmpyInfoFlNm_H1.setNameForMessage(NAME_FOR_MESSAGE_CMPY_INFO_FL_NM);
        scrnMsg.loadDockHgt_H1.setNameForMessage(NAME_FOR_MESSAGE_LOAD_DOCK_HGT);
        scrnMsg.delyTrnspOptCd_H1.setNameForMessage(NAME_FOR_MESSAGE_DELY_TRNSP_OPT_CD);
        scrnMsg.stairCrawReqFlg_Y.setNameForMessage(NAME_FOR_MESSAGE_STAIR_CRAW_REQ_FLG);
        scrnMsg.stairCrawReqFlg_N.setNameForMessage(NAME_FOR_MESSAGE_STAIR_CRAW_REQ_FLG);
        scrnMsg.stairAndLdgWdt_H1.setNameForMessage(NAME_FOR_MESSAGE_STAIR_AND_LDG_WDT);
        // Elevator Information & Dimensions
        scrnMsg.xxTmFrameTxt_EF.setNameForMessage(NAME_FOR_MESSAGE_ELEV_AVAL_FROM_HOUR_MN);
        scrnMsg.xxTmFrameTxt_ET.setNameForMessage(NAME_FOR_MESSAGE_ELEV_AVAL_TO_HOUR_MN);
        // Validation
        scrnMsg.custInfoBoFlg_Y.setNameForMessage(NAME_FOR_MESSAGE_CUST_INFO_BO_FLG);
        scrnMsg.custInfoBoFlg_N.setNameForMessage(NAME_FOR_MESSAGE_CUST_INFO_BO_FLG);
        scrnMsg.pickUpXtrTonerFlg_Y.setNameForMessage(NAME_FOR_MESSAGE_PICK_UP_XTR_TONER_FLG);
        scrnMsg.pickUpXtrTonerFlg_N.setNameForMessage(NAME_FOR_MESSAGE_PICK_UP_XTR_TONER_FLG);
        scrnMsg.pickUpAtSameTmFlg_Y.setNameForMessage(NAME_FOR_MESSAGE_PICK_UP_AT_SAME_TM_FLG);
        scrnMsg.pickUpAtSameTmFlg_N.setNameForMessage(NAME_FOR_MESSAGE_PICK_UP_AT_SAME_TM_FLG);
    }

    /**
     * checkDate
     * @param scrnMsg NLBL3130BMsg
     */
    public static void checkDate(NLBL3130BMsg scrnMsg) {

        // Operations From
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem7Txt_OF)) {

            String opsFromHourMn = getAllDayTimes(scrnMsg.xxScrItem7Txt_OF.getValue(), scrnMsg.xxTmFrameTxt_OF.getValue());

            if (!ZYPCommonFunc.hasValue(opsFromHourMn)) {

                scrnMsg.opsFromHourMn_H1.clear();
                scrnMsg.xxScrItem7Txt_OF.setErrorInfo(1, NLBM0060E);
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_OF);

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.opsFromHourMn_H1, opsFromHourMn);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem7Txt_OF, opsFromHourMn.substring(0, 2) + ':' + opsFromHourMn.substring(2, 4));
            }

        } else {

            scrnMsg.opsFromHourMn_H1.clear();
        }

        // Operations To
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem7Txt_OT)) {

            String opsToHourMn = getAllDayTimes(scrnMsg.xxScrItem7Txt_OT.getValue(), scrnMsg.xxTmFrameTxt_OT.getValue());

            if (!ZYPCommonFunc.hasValue(opsToHourMn)) {

                scrnMsg.opsToHourMn_H1.clear();
                scrnMsg.xxScrItem7Txt_OT.setErrorInfo(1, NLBM0060E);
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_OT);

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.opsToHourMn_H1, opsToHourMn);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem7Txt_OT, opsToHourMn.substring(0, 2) + ':' + opsToHourMn.substring(2, 4));
            }

        } else {

            scrnMsg.opsToHourMn_H1.clear();
        }

        // Elevator Available From
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem7Txt_EF)) {

            String elevAvalFromHourMn = getAllDayTimes(scrnMsg.xxScrItem7Txt_EF.getValue(), scrnMsg.xxTmFrameTxt_EF.getValue());

            if (!ZYPCommonFunc.hasValue(elevAvalFromHourMn)) {

                scrnMsg.elevAvalFromHourMn_C1.clear();
                scrnMsg.xxScrItem7Txt_EF.setErrorInfo(1, NLBM0060E);
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_EF);

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.elevAvalFromHourMn_C1, elevAvalFromHourMn);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem7Txt_EF, elevAvalFromHourMn.substring(0, 2) + ':' + elevAvalFromHourMn.substring(2, 4));
            }

        } else {

            scrnMsg.elevAvalFromHourMn_C1.clear();
        }

        // Elevator Available To
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem7Txt_ET)) {

            String elevAvalToHourMn = getAllDayTimes(scrnMsg.xxScrItem7Txt_ET.getValue(), scrnMsg.xxTmFrameTxt_ET.getValue());

            if (!ZYPCommonFunc.hasValue(elevAvalToHourMn)) {

                scrnMsg.elevAvalToHourMn_C1.clear();
                scrnMsg.xxScrItem7Txt_ET.setErrorInfo(1, NLBM0060E);
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_ET);

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.elevAvalToHourMn_C1, elevAvalToHourMn);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem7Txt_ET, elevAvalToHourMn.substring(0, 2) + ':' + elevAvalToHourMn.substring(2, 4));
            }

        } else {

            scrnMsg.elevAvalToHourMn_C1.clear();
        }

        // Operations From/To Check
        if (ZYPCommonFunc.hasValue(scrnMsg.opsFromHourMn_H1) && ZYPCommonFunc.hasValue(scrnMsg.opsToHourMn_H1)) {

            if (Integer.valueOf(scrnMsg.opsFromHourMn_H1.getValue()) > Integer.valueOf(scrnMsg.opsToHourMn_H1.getValue())) {

                scrnMsg.xxScrItem7Txt_OF.setErrorInfo(1, NFCM0780E, new String[] {"Operations To", "Operations From"});
                scrnMsg.xxScrItem7Txt_OT.setErrorInfo(1, NFCM0780E, new String[] {"Operations To", "Operations From"});
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_OF);
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_OT);
            }
        }

        // Elevator Available From/To Check
        if (ZYPCommonFunc.hasValue(scrnMsg.elevAvalFromHourMn_C1) && ZYPCommonFunc.hasValue(scrnMsg.elevAvalToHourMn_C1)) {

            if (Integer.valueOf(scrnMsg.elevAvalFromHourMn_C1.getValue()) > Integer.valueOf(scrnMsg.elevAvalToHourMn_C1.getValue())) {

                scrnMsg.xxScrItem7Txt_EF.setErrorInfo(1, NFCM0780E, new String[] {"Elevator Available To", "Elevator Available From"});
                scrnMsg.xxScrItem7Txt_ET.setErrorInfo(1, NFCM0780E, new String[] {"Elevator Available To", "Elevator Available From"});
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_EF);
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_ET);
            }
        }
    }

    /**
     * getAllDayTimes
     * @param timeHHMM String
     * @param timeAMPM String
     * @return String
     */
    private static String getAllDayTimes(String timeHHMM, String timeAMPM) {

        int hour = 0;
        int minute = 0;

        if (0 <= timeHHMM.indexOf(":")) {

            String[] temp = timeHHMM.split(":");

            if (temp.length != 2) {

                return null;
            }

            if (!ZYPCommonFunc.isNumberCheck(temp[0]) || !ZYPCommonFunc.isNumberCheck(temp[1])) {

                return null;
            }

            hour = Integer.valueOf(temp[0]);
            minute = Integer.valueOf(temp[1]);

        } else {

            if (!ZYPCommonFunc.isNumberCheck(timeHHMM)) {

                return null;
            }

            if (timeHHMM.length() == HOUR_MINUTE_STR_LENGTH) {

                hour = Integer.valueOf(timeHHMM.substring(0, 2));
                minute = Integer.valueOf(timeHHMM.substring(2));

            } else if (timeHHMM.length() == HOUR_MINUTE_STR_LENGTH - 1) {

                timeHHMM = "0" + timeHHMM;
                hour = Integer.valueOf(timeHHMM.substring(0, 2));
                minute = Integer.valueOf(timeHHMM.substring(2));

            } else {

                return null;
            }
        }

        if (hour < 0 || minute < 0) {

            return null;

            // QC#18460_Sol#087 Update.
        } else if (HALF_DAY_HOURS <= hour) {

            return null;

        } else if (ONE_HOUR_MINUTES <= minute) {

            return null;
        }

        // QC#18460_Sol#087 convert 12 hour => 24 house.
        if (NLBL3130Constant.TIME_PM.equals(timeAMPM)) {

            hour += NLBL3130Constant.HALF_DAY_HOURS;
        }

        return (String.format("%1$02d", hour)).concat(String.format("%1$02d", minute));
    }

    /**
     * changeFormatDate
     * QC#18460_Sol#087 Update.
     * @param scrnMsg NLBL3130BMsg
     */
    private static void changeFormatDate(NLBL3130BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.opsFromHourMn_H1)) {

            setConvertAmPm(scrnMsg.xxScrItem7Txt_OF, scrnMsg.xxTmFrameTxt_OF, scrnMsg.opsFromHourMn_H1.getValue());

        } else {

            scrnMsg.xxScrItem7Txt_OF.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.opsToHourMn_H1)) {

            setConvertAmPm(scrnMsg.xxScrItem7Txt_OT, scrnMsg.xxTmFrameTxt_OT, scrnMsg.opsToHourMn_H1.getValue());

        } else {

            scrnMsg.xxScrItem7Txt_OT.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.elevAvalFromHourMn_C1)) {

            setConvertAmPm(scrnMsg.xxScrItem7Txt_EF, scrnMsg.xxTmFrameTxt_EF, scrnMsg.elevAvalFromHourMn_C1.getValue());

        } else {

            scrnMsg.xxScrItem7Txt_EF.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.elevAvalToHourMn_C1)) {

            setConvertAmPm(scrnMsg.xxScrItem7Txt_ET, scrnMsg.xxTmFrameTxt_ET, scrnMsg.elevAvalToHourMn_C1.getValue());

        } else {

            scrnMsg.xxScrItem7Txt_ET.clear();
        }
    }

    /**
     * chkBoxBothOn
     * @param scrnMsg NLBL3130BMsg
     */
    public static void chkBoxBothOn(NLBL3130BMsg scrnMsg) {

        chkBoxBothOn(scrnMsg.secClncReqTxt_Y, scrnMsg.secClncReqTxt_N, NAME_FOR_MESSAGE_SEC_CLNC_REQ_FLG);
        chkBoxBothOn(scrnMsg.insCertReqTxt_Y, scrnMsg.insCertReqTxt_N, NAME_FOR_MESSAGE_INS_CERT_REQ_FLG);
        chkBoxBothOn(scrnMsg.trctrAndTrailAccsTxt_Y, scrnMsg.trctrAndTrailAccsTxt_N, NAME_FOR_MESSAGE_TRCTR_AND_TRAIL_ACCS_FLG);
        chkBoxBothOn(scrnMsg.loadDockAvalTxt_Y, scrnMsg.loadDockAvalTxt_N, NAME_FOR_MESSAGE_LOAD_DOCK_AVAL_FLG);
        chkBoxBothOn(scrnMsg.rampAvalTxt_Y, scrnMsg.rampAvalTxt_N, NAME_FOR_MESSAGE_RAMP_AVAL_FLG);
        chkBoxBothOn(scrnMsg.stepAvalTxt_Y, scrnMsg.stepAvalTxt_N, NAME_FOR_MESSAGE_STEP_AVAL_FLG);
        chkBoxBothOn(scrnMsg.frontDoorAvalTxt_Y, scrnMsg.frontDoorAvalTxt_N, NAME_FOR_MESSAGE_FRONT_DOOR_AVAL_FLG);
        chkBoxBothOn(scrnMsg.backDoorAvalTxt_Y, scrnMsg.backDoorAvalTxt_N, NAME_FOR_MESSAGE_BACK_DOOR_AVAL_FLG);
        chkBoxBothOn(scrnMsg.pwrOtltConfigTxt_Y, scrnMsg.pwrOtltConfigTxt_N, NAME_FOR_MESSAGE_PWR_OTLT_CONFIG_FLG);
        chkBoxBothOn(scrnMsg.sgnOnBldgRdsdTxt_Y, scrnMsg.sgnOnBldgRdsdTxt_N, NAME_FOR_MESSAGE_SGN_ON_BLDG_RDSD_FLG);
        chkBoxBothOn(scrnMsg.bldgGurdNtfyTxt_Y, scrnMsg.bldgGurdNtfyTxt_N, NAME_FOR_MESSAGE_BLDG_GURD_NTFY_FLG);
        chkBoxBothOn(scrnMsg.indlParkTxt_Y, scrnMsg.indlParkTxt_N, NAME_FOR_MESSAGE_INDL_PARK_FLG);
        chkBoxBothOn(scrnMsg.bizParkTxt_Y, scrnMsg.bizParkTxt_N, NAME_FOR_MESSAGE_BIZ_PARK_FLG);
        chkBoxBothOn(scrnMsg.proBldgTxt_Y, scrnMsg.proBldgTxt_N, NAME_FOR_MESSAGE_PRO_BLDG_FLG);
        chkBoxBothOn(scrnMsg.shpngCtrTxt_Y, scrnMsg.shpngCtrTxt_N, NAME_FOR_MESSAGE_SHPNG_CTR_FLG);
        chkBoxBothOn(scrnMsg.pvtResTxt_Y, scrnMsg.pvtResTxt_N, NAME_FOR_MESSAGE_PVT_RES_FLG);
        chkBoxBothOn(scrnMsg.elevAvalTxt_Y, scrnMsg.elevAvalTxt_N, NAME_FOR_MESSAGE_ELEV_AVAL_FLG);
        chkBoxBothOn(scrnMsg.elevApptReqTxt_Y, scrnMsg.elevApptReqTxt_N, NAME_FOR_MESSAGE_ELEV_APPT_REQ_FLG);

        // QC#18460_Sol#087
        chkBoxBothOn(scrnMsg.stairCrawReqFlg_Y, scrnMsg.stairCrawReqFlg_N, NAME_FOR_MESSAGE_STAIR_CRAW_REQ_FLG);
        chkBoxBothOn(scrnMsg.custInfoBoFlg_Y, scrnMsg.custInfoBoFlg_N, NAME_FOR_MESSAGE_CUST_INFO_BO_FLG);
        chkBoxBothOn(scrnMsg.pickUpXtrTonerFlg_Y, scrnMsg.pickUpXtrTonerFlg_N, NAME_FOR_MESSAGE_PICK_UP_XTR_TONER_FLG);
        chkBoxBothOn(scrnMsg.pickUpAtSameTmFlg_Y, scrnMsg.pickUpAtSameTmFlg_N, NAME_FOR_MESSAGE_PICK_UP_AT_SAME_TM_FLG);
    }

    /**
     * chkBoxBothOn
     * @param chkBoxYItem EZDBStringItem
     * @param chkBoxNItem EZDBStringItem
     * @param errItemNm String
     */
    private static void chkBoxBothOn(EZDBStringItem chkBoxYItem, EZDBStringItem chkBoxNItem, String errItemNm) {

        if (ZYPCommonFunc.hasValue(chkBoxYItem) && ZYPCommonFunc.hasValue(chkBoxNItem)) {

            if (ZYPConstant.FLG_ON_Y.equals(chkBoxYItem.getValue()) && ZYPConstant.FLG_ON_Y.equals(chkBoxNItem.getValue())) {

                chkBoxYItem.setErrorInfo(1, NMAM8287E, new String[] {errItemNm });
                chkBoxNItem.setErrorInfo(1, NMAM8287E, new String[] {errItemNm });
            }
        }
    }

    // QC#16256 add start
    /**
     * Function check
     * @return true:edit false:reference
     */
    private static boolean isUpdatable() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        return functionList.contains(FUNCID_INSERT_UPDATE);
    }
    // QC#16256 add end
    
    /**
     * setConvertAmPm
     * QC#18460_Sol#087 Add method.
     * @param timeHHMM EZDSStringItem
     * @param timeAMPM EZDSStringItem
     * @param hourMinute String
     */
    public static void setConvertAmPm(EZDBStringItem timeHHMM, EZDBStringItem timeAMPM, String hourMinute) {

        if (ZYPCommonFunc.hasValue(hourMinute) && ZYPCommonFunc.isNumberCheck(hourMinute)) {

            String hh = null;
            String mm = null;
            int hour = 0;

            if (hourMinute.length() == NLBL3130Constant.HOUR_MINUTE_STR_LENGTH) {

                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2);
                hour = Integer.valueOf(hh);

            } else if (hourMinute.length() == NLBL3130Constant.HOUR_MINUTE_STR_LENGTH - 1) {

                hourMinute = "0" + hourMinute;
                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2);
                hour = Integer.valueOf(hh);

            } else if (hourMinute.length() == NLBL3130Constant.HOUR_MINUTE_STR_LENGTH + 2) {
                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2, 4);
                hour = Integer.valueOf(hh);

            } else {

                timeHHMM.clear();
                timeAMPM.clear();
                return;
            }

            if (NLBL3130Constant.HALF_DAY_HOURS <= hour) {

                hour -= NLBL3130Constant.HALF_DAY_HOURS;
                ZYPEZDItemValueSetter.setValue(timeAMPM, NLBL3130Constant.TIME_PM);
                ZYPEZDItemValueSetter.setValue(timeHHMM, ZYPCommonFunc.concatString(String.format("%1$02d", hour), ":", mm));
                return;

            } else {

                ZYPEZDItemValueSetter.setValue(timeAMPM, NLBL3130Constant.TIME_AM);
                ZYPEZDItemValueSetter.setValue(timeHHMM, ZYPCommonFunc.concatString(hh, ":", mm));
                return;
            }
        }

        timeHHMM.clear();
        timeAMPM.clear();
        return;
    }
}
