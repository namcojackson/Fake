/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120.common;

import static business.servlet.NLBL3120.constant.NLBL3120Constant.BIZ_ID;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_ALL_COLLAPSE;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_ALL_EXPAND;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_APPLY;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CARR_INFO;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_APPLY;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_APPROVE;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_CLEAR;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_DETELE;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_DOWNLOAD;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_REJECT;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_RESET;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_RETURN;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_SAVE;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_CMN_SUBMIT;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_COORD_INFO;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_COORD_WRK_BENCH;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_MNG_BO;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_REL_SO;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_SELECT_ALL;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.BTN_UNSELECT_ALL;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.FUNC_ID_UPDATE;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.NLZM2276E;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.NLZM2277E;
import static business.servlet.NLBL3120.constant.NLBL3120Constant.SCREEN_ID;

import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3120.NLBL3120BMsg;
import business.servlet.NLBL3120.NLBL3120_ABMsg;
import business.servlet.NLBL3120.constant.NLBL3120Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 * 2016/11/22   CITS            T.Tokutomi      Update          QC#15145
 * 2016/12/19   CITS            M.Naito         Update          QC#16318
 * 2017/01/26   CITS            M.Naito         Update          QC#16924
 * 2017/06/26   CITS            T.Kikuhara      Update          QC#19137
 * 2017/06/28   CITS            T.Kikuhara      Update          QC#19521
 * 2017/06/29   CITS            T.Kikuhara      Update          QC#19525
 * 2017/06/29   CITS            T.Kikuhara      Update          QC#19644
 * 2023/08/01   Hitachi         M.Kikushima     Update          QC#61677
 *</pre>
 */
public class NLBL3120CommonLogic {

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3120BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3120BMsg scrnMsg) {
        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        initCommonButton(userProfileService, handler);
        initButton(userProfileService, handler);
        controlScreenFields(userProfileService, handler, scrnMsg);

        if (0 == scrnMsg.A.getValidCount()) {
            return;
        }

        // Background color
        setBgColorDeliveries(scrnMsg);

    }

    // QC#19521 ADD START
    /**
     * controlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3120BMsg
     */
    public static final void controlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3120BMsg scrnMsg) {

        initCommonButton(userProfileService, handler);
        initButton(userProfileService, handler);
        controlScreenFields(userProfileService, handler, scrnMsg);

        if (0 == scrnMsg.A.getValidCount()) {
            return;
        }

        // Background color
        setBgColorDeliveries(scrnMsg);

    }
    // QC#19521 ADD END

    /**
     * initCommonButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }


    /**
     * initButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {
        handler.setButtonEnabled(BTN_SELECT_ALL, false);
        handler.setButtonEnabled(BTN_UNSELECT_ALL, false);
        handler.setButtonEnabled(BTN_ALL_EXPAND, false);
        handler.setButtonEnabled(BTN_ALL_COLLAPSE, false);
        handler.setButtonEnabled(BTN_COORD_WRK_BENCH, false);
        handler.setButtonEnabled(BTN_MNG_BO, false);
        handler.setButtonEnabled(BTN_APPLY, false);
        handler.setButtonEnabled(BTN_REL_SO, false);
    }

    /**
     * controlScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3120BMsg
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3120BMsg scrnMsg) {

        // Header
        scrnMsg.xxLinkAncr_ML.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxLinkAncr_CF.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxLinkAncr_WR.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxLinkAncr_SC.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxLinkAncr_SV.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxLinkAncr_MG.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxLinkAncr_SR.setValue(ZYPConstant.FLG_ON_Y);

        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.schdCoordPsnNm.setInputProtected(true);
        scrnMsg.xxPsnFirstMidLastNm_SV.setInputProtected(true);
        scrnMsg.xxPsnFirstMidLastNm_MG.setInputProtected(true);
        scrnMsg.xxPsnFirstMidLastNm_SR.setInputProtected(true);

        // Bottom
        scrnMsg.xxLinkAncr_BC.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.schdCoordPsnCd_BT.setInputProtected(false);
        scrnMsg.schdCoordDt_BT.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_BT.setInputProtected(false);
        scrnMsg.carrNm_BT.setInputProtected(false);
        scrnMsg.xxLinkAncr_BA.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.xxLinkAncr_BC.clear();
        scrnMsg.xxLinkAncr_BA.clear();

        boolean updFlg = isUpdateUser(userProfileService);
        if (scrnMsg.A.getValidCount() > 0) {

            handler.setButtonEnabled(BTN_SELECT_ALL, true);
            handler.setButtonEnabled(BTN_UNSELECT_ALL, true);
            handler.setButtonEnabled(BTN_ALL_EXPAND, true);
            handler.setButtonEnabled(BTN_ALL_COLLAPSE, true);
            handler.setButtonEnabled(BTN_COORD_WRK_BENCH, true);
            handler.setButtonEnabled(BTN_MNG_BO, true);
            handler.setButtonEnabled(BTN_APPLY, true);
            handler.setButtonEnabled(BTN_REL_SO, true);

            //Bottom
            scrnMsg.schdCoordPsnCd_BT.setInputProtected(!updFlg);
            scrnMsg.schdCoordDt_BT.setInputProtected(!updFlg);
            scrnMsg.shpgSvcLvlCd_BT.setInputProtected(!updFlg);
            scrnMsg.carrNm_BT.setInputProtected(!updFlg);
            handler.setButtonEnabled(BTN_APPLY, updFlg);

            if (updFlg) {
                scrnMsg.xxLinkAncr_BC.setValue(ZYPConstant.FLG_ON_Y);
                scrnMsg.xxLinkAncr_BA.setValue(ZYPConstant.FLG_ON_Y);
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            }

            handler.setButtonEnabled(BTN_REL_SO, updFlg);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NLBL3120_ABMsg scrnMsgALine = scrnMsg.A.no(i);
            //QC#19137 MOD START
            scrnMsgALine.dsOrdCatgDescTxt_A1.setInputProtected(true);
            scrnMsgALine.dsOrdTpDescTxt_A1.setInputProtected(true);
            scrnMsgALine.svcConfigMstrPk_A1.setInputProtected(true);
            scrnMsgALine.t_MdlNm_A1.setInputProtected(true);
            scrnMsgALine.xxDtlCnt_A1.setInputProtected(true);
            scrnMsgALine.pickSvcConfigMstrPk_A1.setInputProtected(true);
            scrnMsgALine.rtlWhNm_A1.setInputProtected(true);
            scrnMsgALine.xxPsnFirstMidLastNm_A1.setInputProtected(true);
            scrnMsgALine.fromLocCd_A1.setInputProtected(true);
            scrnMsgALine.dsAcctNm_A1.setInputProtected(true);
            scrnMsgALine.fromLocAddr_A1.setInputProtected(true);
            scrnMsgALine.fromLocCtyAddr_A1.setInputProtected(true);
            scrnMsgALine.fromLocStCd_A1.setInputProtected(true);
            scrnMsgALine.schdCoordPsnCd_A1.setInputProtected(true);
            handler.setButtonEnabled(BTN_COORD_INFO, i, false);
            scrnMsgALine.schdCoordStsCd_A1.setInputProtected(true);
            scrnMsgALine.shpgSvcLvlCd_A1.setInputProtected(true);
            scrnMsgALine.carrCd_A1.setInputProtected(true);
            handler.setButtonEnabled(BTN_CARR_INFO, i, false);
            scrnMsgALine.carrAcctNum_A1.setInputProtected(true);
            scrnMsgALine.schdPickUpDt_A1.setInputProtected(true);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).schdOpenFlg_A1.getValue())) {
                scrnMsgALine.schdCoordPsnCd_A1.setInputProtected(!updFlg);
                handler.setButtonEnabled(BTN_COORD_INFO, i, updFlg);
                scrnMsgALine.schdCoordStsCd_A1.setInputProtected(!updFlg);
                scrnMsgALine.shpgSvcLvlCd_A1.setInputProtected(!updFlg);
                scrnMsgALine.carrCd_A1.setInputProtected(!updFlg);
                handler.setButtonEnabled(BTN_CARR_INFO, i, updFlg);
                //QC#19525 MOD START
                if (!ZYPCommonFunc.hasValue(scrnMsgALine.rwsNum_A1)) {
                    scrnMsgALine.carrAcctNum_A1.setInputProtected(!updFlg);
                }
                //QC#19525 MOD END
                scrnMsgALine.schdPickUpDt_A1.setInputProtected(!updFlg);
            }
            //QC#19137 MOD END
            scrnMsgALine.rwsStsDescTxt_A1.setInputProtected(true);
            scrnMsgALine.locNm_A1.setInputProtected(true);
            scrnMsgALine.xxScrItem130Txt_A1.setInputProtected(true);
            scrnMsgALine.tocNm_A1.setInputProtected(true);

            /** QC#16318# 12/19/2016 M.Naito Start **/
            String schdTrxCloDtTmTs = scrnMsgALine.schdTrxCloDtTmTs_A1.getValue();
            if (ZYPCommonFunc.hasValue(schdTrxCloDtTmTs)) {
                if (schdTrxCloDtTmTs.length() == 14) {
                    ZYPEZDItemValueSetter.setValue(scrnMsgALine.xxTsDsp19Txt_A1, ZYPDateUtil.formatEzd14ToDisp(schdTrxCloDtTmTs));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsgALine.xxTsDsp19Txt_A1, ZYPDateUtil.formatEzd8ToDisp(schdTrxCloDtTmTs));
                }
            //QC#19644 MOD START
            } else {
                scrnMsgALine.xxTsDsp19Txt_A1.clear();
            //QC#19644 MOD END
            }
            /** QC#16318# 12/19/2016 M.Naito End **/
            scrnMsgALine.xxLinkAncr_AT.setValue(ZYPConstant.FLG_ON_Y);
            if (ZYPCommonFunc.hasValue(scrnMsgALine.soNum_A1)) {
                scrnMsgALine.xxLinkAncr_AS.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsgALine.xxLinkAncr_AS.clear();
            }
            if (ZYPCommonFunc.hasValue(scrnMsgALine.rwsNum_A1)) {
                scrnMsgALine.xxLinkAncr_AR.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsgALine.xxLinkAncr_AR.clear();
            }
        }
        /** QC#16924# 01/27/2017 M.Naito Start **/
        setVisibility(handler, scrnMsg);
        /** QC#16924# 01/27/2017 M.Naito End **/
    }

    /**
     * controlSubmitWarning
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3120BMsg
     */
    public static final void controlSubmitWarning(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3120BMsg scrnMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxWrnSkipFlg.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NLBL3120_ABMsg scrnMsgALine = scrnMsg.A.no(i);
                scrnMsgALine.svcConfigMstrPk_A1.setInputProtected(true);
                scrnMsgALine.pickSvcConfigMstrPk_A1.setInputProtected(true);
                scrnMsgALine.t_MdlNm_A1.setInputProtected(true);
                scrnMsgALine.dsOrdCatgDescTxt_A1.setInputProtected(true);
                scrnMsgALine.dsOrdTpDescTxt_A1.setInputProtected(true);
                scrnMsgALine.rtlWhNm_A1.setInputProtected(true);
                scrnMsgALine.schdCoordPsnCd_A1.setInputProtected(true);
                scrnMsgALine.xxPsnFirstMidLastNm_A1.setInputProtected(true);
                //QC#19137 MOD START
                scrnMsgALine.schdCoordStsCd_A1.setInputProtected(true);
                //QC#19137 MOD END
                scrnMsgALine.locNm_A1.setInputProtected(true);
                scrnMsgALine.carrAcctNum_A1.setInputProtected(true);
                scrnMsgALine.schdPickUpDt_A1.setInputProtected(true);
                scrnMsgALine.tocNm_A1.setInputProtected(true);
                scrnMsgALine.shpgSvcLvlCd_A1.setInputProtected(true);
                scrnMsgALine.carrCd_A1.setInputProtected(true);
            }
            handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 1, null);
            handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DOWNLOAD[0], BTN_CMN_DOWNLOAD[1], BTN_CMN_DOWNLOAD[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DETELE[0], BTN_CMN_DETELE[1], BTN_CMN_DETELE[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);

        } else {
            initialControlScreen(userProfileService, handler, scrnMsg);
        }
    }

    /**
     * checkInputSearch
     * @param scrnMsg NLBL3120BMsg
     */
    public static void checkInputSearch(NLBL3120BMsg scrnMsg) {

        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.trxHdrNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.t_MdlNm)
                && !ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk)
                && !ZYPCommonFunc.hasValue(scrnMsg.soNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.rwsNum)
                && !ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.schdCoordPsnCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.supvPsnCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.mgrPsnCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.slsRepOrSlsTeamTocCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO)
                && !ZYPCommonFunc.hasValue(scrnMsg.schdCoordDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.schdCoordDt_TO)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsSoLineStsCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.rwsStsCd)
                && !ZYPCommonFunc.hasValue(scrnMsg.schdCoordStsCd)) {

            scrnMsg.trxHdrNum.setErrorInfo(1, NLZM2276E);
            scrnMsg.t_MdlNm.setErrorInfo(1, NLZM2276E);
            scrnMsg.svcConfigMstrPk.setErrorInfo(1, NLZM2276E);
            scrnMsg.soNum.setErrorInfo(1, NLZM2276E);
            scrnMsg.rwsNum.setErrorInfo(1, NLZM2276E);
            scrnMsg.rtlWhCd.setErrorInfo(1, NLZM2276E);
            scrnMsg.schdCoordPsnCd.setErrorInfo(1, NLZM2276E);
            scrnMsg.supvPsnCd.setErrorInfo(1, NLZM2276E);
            scrnMsg.mgrPsnCd.setErrorInfo(1, NLZM2276E);
            scrnMsg.slsRepOrSlsTeamTocCd.setErrorInfo(1, NLZM2276E);
            scrnMsg.rddDt_FR.setErrorInfo(1, NLZM2276E);
            scrnMsg.rddDt_TO.setErrorInfo(1, NLZM2276E);
            scrnMsg.schdCoordDt_FR.setErrorInfo(1, NLZM2276E);
            scrnMsg.schdCoordDt_TO.setErrorInfo(1, NLZM2276E);
            scrnMsg.dsOrdCatgCd.setErrorInfo(1, NLZM2276E);
            scrnMsg.dsOrdTpCd.setErrorInfo(1, NLZM2276E);
            scrnMsg.dsSoLineStsCd.setErrorInfo(1, NLZM2276E);
            scrnMsg.rwsStsCd.setErrorInfo(1, NLZM2276E);
            scrnMsg.schdCoordStsCd.setErrorInfo(1, NLZM2276E);
        }
        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();

        if (ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR)
                && ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO)) {
            if (0 < scrnMsg.rddDt_FR.getValue().compareTo(scrnMsg.rddDt_TO.getValue())) {
                String[] msgPrm = new String[]{
                         scrnMsg.rddDt_FR.getNameForMessage()
                        , scrnMsg.rddDt_TO.getNameForMessage()};
                scrnMsg.rddDt_FR.setErrorInfo(1, NLZM2277E, msgPrm);
                scrnMsg.rddDt_TO.setErrorInfo(1, NLZM2277E, msgPrm);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.schdCoordDt_FR)
                && ZYPCommonFunc.hasValue(scrnMsg.schdCoordDt_TO)) {
            if (0 < scrnMsg.schdCoordDt_FR.getValue().compareTo(scrnMsg.schdCoordDt_TO.getValue())) {
                String[] msgPrm = new String[]{
                         scrnMsg.schdCoordDt_FR.getNameForMessage()
                        , scrnMsg.schdCoordDt_TO.getNameForMessage()};
                scrnMsg.schdCoordDt_FR.setErrorInfo(1, NLZM2277E, msgPrm);
                scrnMsg.schdCoordDt_TO.setErrorInfo(1, NLZM2277E, msgPrm);
            }
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItemDeliveries
     * @param scrnMsg NLBL3120BMsg
     */
    public static void addCheckItemSchd(NLBL3120BMsg scrnMsg) {

        S21SequentialSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_A,
                scrnMsg.xxPageShowToNum_A, scrnMsg.xxPageShowOfNum_A, scrnMsg.xxPageShowCurNum_A, scrnMsg.xxPageShowTotNum_A);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NLBL3120_ABMsg scrnMsgALine = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(scrnMsgALine.xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsgALine.xxChkBox_A2);
            scrnMsg.addCheckItem(scrnMsgALine.rtlWhNm_A1);
            scrnMsg.addCheckItem(scrnMsgALine.schdCoordPsnCd_A1);
            scrnMsg.addCheckItem(scrnMsgALine.shpgSvcLvlCd_A1);
            scrnMsg.addCheckItem(scrnMsgALine.carrCd_A1);
            scrnMsg.addCheckItem(scrnMsgALine.schdPickUpDt_A1);
            // START 2023/08/01 M.Kikushima [QC#61677, ADD]
            scrnMsg.addCheckItem(scrnMsgALine.schdCoordStsCd_A1);
            // END 2023/08/01 M.Kikushima [QC#61677, ADD]
        }
    }

    /**
     * addCheckItemApply
     * @param scrnMsg NLBL3120BMsg
     */
    public static void addCheckItemApply(NLBL3120BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd_BT);
        scrnMsg.addCheckItem(scrnMsg.schdCoordDt_BT);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_BT);
        scrnMsg.addCheckItem(scrnMsg.carrNm_BT);
    }

    /**
     * 
     * setBgColorDeliveries
     * 
     * @param scrnMsg NLBL3120BMsg
     */
    public static void setBgColorDeliveries(NLBL3120BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(FUNC_ID_UPDATE);
    }

    /**
     * AddCheckItemHeader
     * @param scrnMsg NLBL3120BMsg
     */
    public static void addCheckItemHeader(NLBL3120BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.trxHdrNum);
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.addCheckItem(scrnMsg.soNum);
        scrnMsg.addCheckItem(scrnMsg.rwsNum);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd);
        scrnMsg.addCheckItem(scrnMsg.supvPsnCd);
        scrnMsg.addCheckItem(scrnMsg.mgrPsnCd);
        scrnMsg.addCheckItem(scrnMsg.slsRepOrSlsTeamTocCd);
        scrnMsg.addCheckItem(scrnMsg.rddDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rddDt_TO);
        scrnMsg.addCheckItem(scrnMsg.schdCoordDt_FR);
        scrnMsg.addCheckItem(scrnMsg.schdCoordDt_TO);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.dsSoLineStsCd);
        scrnMsg.addCheckItem(scrnMsg.rwsStsCd);
        scrnMsg.addCheckItem(scrnMsg.schdCoordStsCd);
    }

    /** QC#16924# 01/27/2017 M.Naito Start **/
    /**
     *  setVisibility
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3120BMsg
     */
    public static void setVisibility(EZDCommonHandler handler, NLBL3120BMsg scrnMsg) {

        String preTrxHdrNum = "";

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // Clear
            scrnMsg.clearGUIAttribute(NLBL3120Constant.SCREEN_ID, "OnChange_ChkBoxTrxHdrNum" + i);
            scrnMsg.clearGUIAttribute(NLBL3120Constant.SCREEN_ID, "OnChange_ChkBoxSoNum" + i);

            if (i >= scrnMsg.A.getValidCount()) {
                continue;
            }

            // Set Visibility Check Box
            NLBL3120_ABMsg scrnMsgLine = scrnMsg.A.no(i);

            if (i == 0 && ZYPCommonFunc.hasValue(scrnMsgLine.soNum_A1.getValue())) {
                // Header (1stLine)
                EZDGUIAttribute chkBoxTrxHdrNum = new EZDGUIAttribute(NLBL3120Constant.SCREEN_ID, "OnChange_ChkBoxTrxHdrNum" + i);
                chkBoxTrxHdrNum.setVisibility(false);
                scrnMsg.addGUIAttribute(chkBoxTrxHdrNum);
            } else if (preTrxHdrNum.equals(scrnMsgLine.trxHdrNum_A1.getValue())) {
                // Header
                EZDGUIAttribute chkBoxTrxHdrNum = new EZDGUIAttribute(NLBL3120Constant.SCREEN_ID, "OnChange_ChkBoxTrxHdrNum" + i);
                chkBoxTrxHdrNum.setVisibility(false);
                scrnMsg.addGUIAttribute(chkBoxTrxHdrNum);
            } else {
                // Detail
                EZDGUIAttribute chkBoxSoNum = new EZDGUIAttribute(NLBL3120Constant.SCREEN_ID, "OnChange_ChkBoxSoNum" + i);
                chkBoxSoNum.setVisibility(false);
                scrnMsg.addGUIAttribute(chkBoxSoNum);
                ZYPEZDItemValueSetter.setValue(scrnMsgLine.xxChkBox_A2, "");
            }

            preTrxHdrNum = scrnMsgLine.trxHdrNum_A1.getValue();
        }
    }
    /** QC#16924# 01/27/2017 M.Naito End **/
}
