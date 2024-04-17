/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070.common;

import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL3070.NLBL3070BMsg;
import business.servlet.NLBL3070.NLBL3070Bean;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#5125
 * 08/31/2016   CSAI            Y.Imazu         Update          QC#9845
 * 11/21/2016   CSAI            Y.Imazu         Update          QC#15622
 * 11/21/2016   CITS            T.Tokutomi      Update          QC#15145
 * 06/15/2017   CITS            R.Shimamoto     Update          QC#18272
 * 09/07/2017   CITS            S.Katsuma       Update          Sol#032(QC#13117)
 * 2017/12/28   CITS            T.Hakodate      Update          QC#18460(SOL#087)
 * 2018/02/23   CITS            K.Ogino         Update          QC#20043
 *</pre>
 */
public class NLBL3070CommonLogic {

    /**
     * initialControlScreen
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3070BMsg
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3070BMsg scrnMsg) {

        // Clear Attribute
        scrnMsg.clearAllGUIAttribute(NLBL3070Constant.SCREEN_ID);

        initCommonButton(userProfileService, handler);
        initButton(userProfileService, handler);
        controlScreenFields(userProfileService, handler, scrnMsg);

        if (0 < scrnMsg.A.getValidCount() || 0 < scrnMsg.B.getValidCount()) {

            controlButton(userProfileService, handler, scrnMsg);
        }

        if (0 < scrnMsg.A.getValidCount() && NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

            setBgColorScheduling(scrnMsg);
            setVisibilityScheduling(scrnMsg);
        }

        if (0 < scrnMsg.B.getValidCount() && NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

            setBgColorDeliveries(scrnMsg);
            setVisibilityDeliveries(scrnMsg, handler);
        }
    }

    /**
     * initCommonButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {

        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_SAVE[0], NLBL3070Constant.BTN_CMN_SAVE[1], NLBL3070Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_SUBMIT[0], NLBL3070Constant.BTN_CMN_SUBMIT[1], NLBL3070Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_APPLY[0], NLBL3070Constant.BTN_CMN_APPLY[1], NLBL3070Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_APPROVE[0], NLBL3070Constant.BTN_CMN_APPROVE[1], NLBL3070Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_REJECT[0], NLBL3070Constant.BTN_CMN_REJECT[1], NLBL3070Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_DOWNLOAD[0], NLBL3070Constant.BTN_CMN_DOWNLOAD[1], NLBL3070Constant.BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_DETELE[0], NLBL3070Constant.BTN_CMN_DETELE[1], NLBL3070Constant.BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_CLEAR[0], NLBL3070Constant.BTN_CMN_CLEAR[1], NLBL3070Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_RESET[0], NLBL3070Constant.BTN_CMN_RESET[1], NLBL3070Constant.BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_CMN_RETURN[0], NLBL3070Constant.BTN_CMN_RETURN[1], NLBL3070Constant.BTN_CMN_RETURN[2], 1, null);
    }

    /**
     * initButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     */
    public static final void initButton(S21UserProfileService userProfileService, EZDCommonHandler handler) {

        // Scheduling Tab/Deliveries Tab
        handler.setButtonProperties(NLBL3070Constant.BTN_SELECT_ALL[0], NLBL3070Constant.BTN_SELECT_ALL[1], NLBL3070Constant.BTN_SELECT_ALL[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_UNSELECT_ALL[0], NLBL3070Constant.BTN_UNSELECT_ALL[1], NLBL3070Constant.BTN_UNSELECT_ALL[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_APPLY[0], NLBL3070Constant.BTN_APPLY[1], NLBL3070Constant.BTN_APPLY[2], 0, null);

        // Scheduling Tab
        handler.setButtonProperties(NLBL3070Constant.BTN_REL_SO[0], NLBL3070Constant.BTN_REL_SO[1], NLBL3070Constant.BTN_REL_SO[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_SEND_RQST[0], NLBL3070Constant.BTN_SEND_RQST[1], NLBL3070Constant.BTN_SEND_RQST[2], 0, null);

        // Deliveries Tab
        handler.setButtonProperties(NLBL3070Constant.BTN_SO_CANCEL[0], NLBL3070Constant.BTN_SO_CANCEL[1], NLBL3070Constant.BTN_SO_CANCEL[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_SO_SHIP[0], NLBL3070Constant.BTN_SO_SHIP[1], NLBL3070Constant.BTN_SO_SHIP[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_SO_CLOSE[0], NLBL3070Constant.BTN_SO_CLOSE[1], NLBL3070Constant.BTN_SO_CLOSE[2], 0, null);
        handler.setButtonProperties(NLBL3070Constant.BTN_DELY_CONF[0], NLBL3070Constant.BTN_DELY_CONF[1], NLBL3070Constant.BTN_DELY_CONF[2], 0, null);
    }

    /**
     * controlButton
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3070BMsg
     */
    public static final void controlButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3070BMsg scrnMsg) {

        if (isUpdateUser(userProfileService) && 0 < scrnMsg.A.getValidCount() && NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

            handler.setButtonProperties(NLBL3070Constant.BTN_APPLY[0], NLBL3070Constant.BTN_APPLY[1], NLBL3070Constant.BTN_APPLY[2], 1, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_REL_SO[0], NLBL3070Constant.BTN_REL_SO[1], NLBL3070Constant.BTN_REL_SO[2], 1, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_SEND_RQST[0], NLBL3070Constant.BTN_SEND_RQST[1], NLBL3070Constant.BTN_SEND_RQST[2], 1, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_CMN_SUBMIT[0], NLBL3070Constant.BTN_CMN_SUBMIT[1], NLBL3070Constant.BTN_CMN_SUBMIT[2], 1, null);

        } else if (isUpdateUser(userProfileService) && 0 < scrnMsg.B.getValidCount() && NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

            handler.setButtonProperties(NLBL3070Constant.BTN_APPLY[0], NLBL3070Constant.BTN_APPLY[1], NLBL3070Constant.BTN_APPLY[2], 1, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_SO_CANCEL[0], NLBL3070Constant.BTN_SO_CANCEL[1], NLBL3070Constant.BTN_SO_CANCEL[2], 1, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_SO_SHIP[0], NLBL3070Constant.BTN_SO_SHIP[1], NLBL3070Constant.BTN_SO_SHIP[2], 1, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_SO_CLOSE[0], NLBL3070Constant.BTN_SO_CLOSE[1], NLBL3070Constant.BTN_SO_CLOSE[2], 1, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_DELY_CONF[0], NLBL3070Constant.BTN_DELY_CONF[1], NLBL3070Constant.BTN_DELY_CONF[2], 1, null);

        } else {

            handler.setButtonProperties(NLBL3070Constant.BTN_APPLY[0], NLBL3070Constant.BTN_APPLY[1], NLBL3070Constant.BTN_APPLY[2], 0, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_REL_SO[0], NLBL3070Constant.BTN_REL_SO[1], NLBL3070Constant.BTN_REL_SO[2], 0, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_SEND_RQST[0], NLBL3070Constant.BTN_SEND_RQST[1], NLBL3070Constant.BTN_SEND_RQST[2], 0, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_SO_CANCEL[0], NLBL3070Constant.BTN_SO_CANCEL[1], NLBL3070Constant.BTN_SO_CANCEL[2], 0, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_SO_SHIP[0], NLBL3070Constant.BTN_SO_SHIP[1], NLBL3070Constant.BTN_SO_SHIP[2], 0, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_SO_CLOSE[0], NLBL3070Constant.BTN_SO_CLOSE[1], NLBL3070Constant.BTN_SO_CLOSE[2], 0, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_DELY_CONF[0], NLBL3070Constant.BTN_DELY_CONF[1], NLBL3070Constant.BTN_DELY_CONF[2], 0, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_CMN_SUBMIT[0], NLBL3070Constant.BTN_CMN_SUBMIT[1], NLBL3070Constant.BTN_CMN_SUBMIT[2], 0, null);
        }

        if (0 < scrnMsg.A.getValidCount() || 0 < scrnMsg.B.getValidCount()) {

            handler.setButtonProperties(NLBL3070Constant.BTN_SELECT_ALL[0], NLBL3070Constant.BTN_SELECT_ALL[1], NLBL3070Constant.BTN_SELECT_ALL[2], 1, null);
            handler.setButtonProperties(NLBL3070Constant.BTN_UNSELECT_ALL[0], NLBL3070Constant.BTN_UNSELECT_ALL[1], NLBL3070Constant.BTN_UNSELECT_ALL[2], 1, null);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (isUpdateUser(userProfileService) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).schdOpenFlg_A1.getValue())) {

                handler.setButtonEnabled(NLBL3070Constant.BTN_CARR_INFO[0], i, true);

            } else {

                handler.setButtonEnabled(NLBL3070Constant.BTN_CARR_INFO[0], i, false);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            if (isUpdateUser(userProfileService) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).soLineOpenFlg_B1.getValue())) {

                handler.setButtonEnabled(NLBL3070Constant.BTN_CARR_INFO[0], i, true);

            } else {

                handler.setButtonEnabled(NLBL3070Constant.BTN_CARR_INFO[0], i, false);
            }

            if (1 == scrnMsg.B.no(i).xxShipQty_B1.getValueInt()) {

                handler.setButtonEnabled(NLBL3070Constant.BTN_SER_NUM[0], i, false);

            } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).serNumTakeFlg_B1.getValue())) {

                handler.setButtonEnabled(NLBL3070Constant.BTN_SER_NUM[0], i, true);

            } else {

                handler.setButtonEnabled(NLBL3070Constant.BTN_SER_NUM[0], i, false);
            }

            // Not Back Order
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).soNum_B1)) {

                handler.setButtonEnabled(NLBL3070Constant.BTN_DELY_INSTN[0], i, true);

            } else {

                handler.setButtonEnabled(NLBL3070Constant.BTN_DELY_INSTN[0], i, false);
            }

            // Summarize Lines
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxSmryLineFlg_B1.getValue())) {

                handler.setButtonEnabled(NLBL3070Constant.BTN_CARR_INFO[0], i, false);
                handler.setButtonEnabled(NLBL3070Constant.BTN_SER_NUM[0], i, false);
            }
        }
    }

    /**
     * controlScreenFields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NLBL3070BMsg
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NLBL3070BMsg scrnMsg) {

        // Header
        scrnMsg.rtlWhNm.setInputProtected(true);
        scrnMsg.rtlSwhNm.setInputProtected(true);
        scrnMsg.shipToCustNm.setInputProtected(true);
        scrnMsg.carrNm.setInputProtected(true);
        scrnMsg.xxPsnFirstMidLastNm.setInputProtected(true);

        // Schedule Tab Bottom
        if (0 < scrnMsg.A.getValidCount() && isUpdateUser(userProfileService)) {

            scrnMsg.schdCarrPickUpDt_AP.setInputProtected(false);
            scrnMsg.schdDelyDt_AP.setInputProtected(false);
            // QC#18272 Mod.
            scrnMsg.schdDelyTsDplyTxt_AP.setInputProtected(false);
            scrnMsg.rqstRcvDtTxt_P2.setInputProtected(false);

            scrnMsg.shpgSvcLvlCd_AP.setInputProtected(false);
            scrnMsg.carrNm_AP.setInputProtected(false);
            scrnMsg.xxLinkAncr_AP.setInputProtected(false);
            scrnMsg.schdCoordStsCd_AP.setInputProtected(false);

        } else {

            scrnMsg.schdCarrPickUpDt_AP.setInputProtected(true);
            scrnMsg.schdDelyDt_AP.setInputProtected(true);
            // QC#18272 Mod.
            scrnMsg.schdDelyTsDplyTxt_AP.setInputProtected(true);
            scrnMsg.rqstRcvDtTxt_P2.setInputProtected(true);

            scrnMsg.shpgSvcLvlCd_AP.setInputProtected(true);
            scrnMsg.carrNm_AP.setInputProtected(true);
            scrnMsg.xxLinkAncr_AP.setInputProtected(true);
            scrnMsg.schdCoordStsCd_AP.setInputProtected(true);
        }

        // Delivery Tab Bottom
        if (0 < scrnMsg.B.getValidCount() && isUpdateUser(userProfileService)) {

            scrnMsg.xxLinkAncr_BP.setInputProtected(false);
            scrnMsg.carrNm_BP.setInputProtected(false);
            scrnMsg.proNum_BP.setInputProtected(false);
            scrnMsg.totFrtAmt_BP.setInputProtected(false);
            scrnMsg.carrRsnCd_BP.setInputProtected(false);
            scrnMsg.actlDelyDt_BP.setInputProtected(false);
            scrnMsg.schdDelyTsDplyTxt_BP.setInputProtected(false);
            scrnMsg.rqstRcvDtTxt_BP.setInputProtected(false);

        } else {

            scrnMsg.xxLinkAncr_BP.setInputProtected(true);
            scrnMsg.carrNm_BP.setInputProtected(true);
            scrnMsg.proNum_BP.setInputProtected(true);
            scrnMsg.totFrtAmt_BP.setInputProtected(true);
            scrnMsg.carrRsnCd_BP.setInputProtected(true);
            scrnMsg.actlDelyDt_BP.setInputProtected(true);
            // QC#18272 Mod.
            scrnMsg.schdDelyTsDplyTxt_BP.setInputProtected(true);
            scrnMsg.rqstRcvDtTxt_BP.setInputProtected(true);
        }

        // Schedule Tab Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).svcConfigMstrPk_A1.setInputProtected(true);
            scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).schdCarrPickUpDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).schdDelyDt_A1.setInputProtected(true);
            // QC18272 Mod.
            scrnMsg.A.no(i).schdDelyTsDplyTxt_A2.setInputProtected(true);
            // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
            scrnMsg.A.no(i).techMeetTruckFlg_A1.setInputProtected(true);
            // END 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
            // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            scrnMsg.A.no(i).nextActDt_A1.setInputProtected(true);
            // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            scrnMsg.A.no(i).carrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).schdIstlDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).schdDelyTsDplyTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rqstRcvDtTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).rqstRcvDtTxt_S2.setInputProtected(true);
            scrnMsg.A.no(i).schdDurnTmNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).techNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).schdCoordStsCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnFirstMidLastNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).tocNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctacPsnTelNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).firstLineAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).scdLineAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).postCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlWhNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).shpgSvcLvlCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).carrAcctNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnFirstMidLastNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).tempSchdRsnCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).tempSchdCmntTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdCatgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdTpDescTxt_A1.setInputProtected(true);

            if (isUpdateUser(userProfileService)) {

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).schdOpenFlg_A1.getValue())) {

                    scrnMsg.A.no(i).schdCarrPickUpDt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).schdDelyDt_A1.setInputProtected(false);
                    // QC18272 Mod.
                    scrnMsg.A.no(i).schdDelyTsDplyTxt_A2.setInputProtected(false);
                    scrnMsg.A.no(i).rqstRcvDtTxt_S2.setInputProtected(false);
                    
                    // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
                    scrnMsg.A.no(i).techMeetTruckFlg_A1.setInputProtected(false);
                    // END   2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
                    
                    // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
                    scrnMsg.A.no(i).nextActDt_A1.setInputProtected(false);
                    // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]

                    scrnMsg.A.no(i).carrNm_A1.setInputProtected(false);
                    scrnMsg.A.no(i).schdCoordStsCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).shpgSvcLvlCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).tempSchdRsnCd_A1.setInputProtected(false);
                    scrnMsg.A.no(i).tempSchdCmntTxt_A1.setInputProtected(false);
                    /** QC#15145 Enable Carrier Account# 11/21/2016 T.Tokutomi Start **/
                    scrnMsg.A.no(i).carrAcctNum_A1.setInputProtected(false);
                    /** QC#15145 Enable Carrier Account# 11/21/2016 T.Tokutomi End **/
                }

                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).fsrNum_A1)) {

                    scrnMsg.A.no(i).schdIstlDt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).schdDelyTsDplyTxt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).rqstRcvDtTxt_A1.setInputProtected(false);
                    scrnMsg.A.no(i).schdDurnTmNum_A1.setInputProtected(false);
                }
            }
        }

        // Delivery Tab Detail
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxChkBox_B2.setInputProtected(true);
            scrnMsg.B.no(i).shipSvcConfigMstrPk_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxPsnFirstMidLastNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).rtlWhNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).rtlSwhCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).stCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxPsnFirstMidLastNm_B2.setInputProtected(true);
            scrnMsg.B.no(i).mdseCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).mdseDescShortTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).backOrdImpctTpDescTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).xxShipQty_B1.setInputProtected(true);
            scrnMsg.B.no(i).serNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).carrRsnCd_B1.setInputProtected(true);
            scrnMsg.B.no(i).actlDelyDt_B1.setInputProtected(true);
            scrnMsg.B.no(i).schdDelyTsDplyTxt_B2.setInputProtected(true);
            // QC#18272 Mod.
            scrnMsg.B.no(i).rqstRcvDtTxt_B2.setInputProtected(true);
            scrnMsg.B.no(i).svcTaskStsDescTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).dsSoLineStsDescTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).carrNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).proNum_B1.setInputProtected(true);
            scrnMsg.B.no(i).shpgSvcLvlDescTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).schdCoordStsDescTxt_B1.setInputProtected(true);
            scrnMsg.B.no(i).totFrtAmt_B1.setInputProtected(true);
            scrnMsg.B.no(i).totFrtAmt_B1.setAppFracDigit(2);
            scrnMsg.B.no(i).dsAcctNm_B1.setInputProtected(true);
            scrnMsg.B.no(i).ctyAddr_B1.setInputProtected(true);
            scrnMsg.B.no(i).pickSvcConfigMstrPk_B1.setInputProtected(true);
            scrnMsg.B.no(i).dsOrdCatgDescTxt_B1.setInputProtected(true);

            if (isUpdateUser(userProfileService) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).xxSmryLineFlg_B1.getValue())) {

                // Open SO
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).soLineOpenFlg_B1.getValue())) {

                    scrnMsg.B.no(i).carrNm_B1.setInputProtected(false);
                    scrnMsg.B.no(i).proNum_B1.setInputProtected(false);
                    scrnMsg.B.no(i).totFrtAmt_B1.setInputProtected(false);

                    // Serialized and Quantity = 1
                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).serNumTakeFlg_B1.getValue()) && scrnMsg.B.no(i).xxShipQty_B1.getValueInt() == 1) {

                        scrnMsg.B.no(i).serNum_B1.setInputProtected(false);
                    }

                    // Not allowed to ship partially or Quantity = 1
                    if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).shipAvalFlg_PS.getValue()) || scrnMsg.B.no(i).xxShipQty_B1.getValueInt() == 1) {

                        scrnMsg.B.no(i).xxShipQty_B1.setInputProtected(true);

                    // Pick Config Component or Set Component
                    } else if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).setMdseCd_B1) || ZYPCommonFunc.hasValue(scrnMsg.B.no(i).pickSvcConfigMstrPk_B1)) {

                        scrnMsg.B.no(i).xxShipQty_B1.setInputProtected(true);

                    } else {

                        scrnMsg.B.no(i).xxShipQty_B1.setInputProtected(false);
                    }
                }

                // Shipped
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).shipFlg_B1.getValue())) {

                    // QC#20043
                    if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).actlDelyDt_BK)) {
                        scrnMsg.B.no(i).carrRsnCd_B1.setInputProtected(false);
                        scrnMsg.B.no(i).actlDelyDt_B1.setInputProtected(false);
                        scrnMsg.B.no(i).schdDelyTsDplyTxt_B2.setInputProtected(false);
                        // QC#18272 Mod.
                        scrnMsg.B.no(i).rqstRcvDtTxt_B2.setInputProtected(false);
                    }
                }
            }

            // Not Back Order
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).soNum_B1)) {

                // QC#20043
                if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).actlDelyDt_BK)) {
                    scrnMsg.B.no(i).xxChkBox_B1.setInputProtected(false);
                    scrnMsg.B.no(i).xxChkBox_B2.setInputProtected(false);
                }
            }
        }
    }

    /**
     * checkInputSearch
     * @param scrnMsg NLBL3070BMsg
     */
    public static void checkInputSearch(NLBL3070BMsg scrnMsg) {

        // Source WH
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {

            scrnMsg.rtlWhNm.clear();
        }

        // Source SWH
        if (!ZYPCommonFunc.hasValue(scrnMsg.rtlSwhCd)) {

            scrnMsg.rtlSwhNm.clear();
        }

        // Ship To Customer
        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {

            scrnMsg.shipToCustNm.clear();
        }

        // Carrier
        if (!ZYPCommonFunc.hasValue(scrnMsg.carrCd)) {

            scrnMsg.carrNm.clear();
        }

        // Coordinator
        if (!ZYPCommonFunc.hasValue(scrnMsg.schdCoordPsnCd)) {

            scrnMsg.xxPsnFirstMidLastNm.clear();
        }

        // check column format
        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();

        if (isReqHdrAllBlank(scrnMsg)) {

            scrnMsg.trxHdrNum.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.t_MdlNm.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.svcConfigMstrPk.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.soNum.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.bolNum.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.rtlWhCd.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.shipToCustCd.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.carrCd.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.schdCoordPsnCd.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.rddDt_FR.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.rddDt_TO.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.schdCarrPickUpDt_FR.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.schdCarrPickUpDt_TO.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.schdDelyDt_FR.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.schdDelyDt_TO.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.actlDelyDt_FR.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.actlDelyDt_TO.setErrorInfo(1, NLBL3070Constant.NLZM2276E);
            scrnMsg.putErrorScreen();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO)) {

            if (0 < scrnMsg.rddDt_FR.getValue().compareTo(scrnMsg.rddDt_TO.getValue())) {

                scrnMsg.rddDt_FR.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Request Delivery Date(Through)", "Request Delivery Date(From)" });
                scrnMsg.rddDt_TO.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Request Delivery Date(Through)", "Request Delivery Date(From)" });
                scrnMsg.setMessageInfo(NLBL3070Constant.ZZM9037E);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.schdCarrPickUpDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.schdCarrPickUpDt_TO)) {

            if (0 < scrnMsg.schdCarrPickUpDt_FR.getValue().compareTo(scrnMsg.schdCarrPickUpDt_TO.getValue())) {

                scrnMsg.schdCarrPickUpDt_FR.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Scheduled Carrie Pickup Date(Through)", "Scheduled Carrie Pickup Date(From)" });
                scrnMsg.schdCarrPickUpDt_TO.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Scheduled Carrie Pickup Date(Through)", "Scheduled Carrie Pickup Date(From)" });
                scrnMsg.setMessageInfo(NLBL3070Constant.ZZM9037E);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.schdDelyDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.schdDelyDt_TO)) {

            if (0 < scrnMsg.schdDelyDt_FR.getValue().compareTo(scrnMsg.schdDelyDt_TO.getValue())) {

                scrnMsg.schdDelyDt_FR.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Scheduled Delivery Date(Through)", "Scheduled Delivery Date(From)" });
                scrnMsg.schdDelyDt_TO.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Scheduled Delivery Date(Through)", "Scheduled Delivery Date(From)" });
                scrnMsg.setMessageInfo(NLBL3070Constant.ZZM9037E);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.actlDelyDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.actlDelyDt_TO)) {

            if (0 < scrnMsg.actlDelyDt_FR.getValue().compareTo(scrnMsg.actlDelyDt_TO.getValue())) {

                scrnMsg.actlDelyDt_FR.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Actual Delivery Date(Through)", "Actual Delivery Date(From)" });
                scrnMsg.actlDelyDt_TO.setErrorInfo(1, NLBL3070Constant.NFCM0780E, new String[] {"Actual Delivery Date(Through)", "Actual Delivery Date(From)" });
                scrnMsg.setMessageInfo(NLBL3070Constant.ZZM9037E);
            }
        }

        addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    /**
     * isReqHdrAllBlank
     * @param scrnMsg NLBL3070BMsg
     */
    private static boolean isReqHdrAllBlank(NLBL3070BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.trxHdrNum)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.t_MdlNm)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.soNum)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.bolNum)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCd)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.carrCd)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdCoordPsnCd)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdCarrPickUpDt_FR)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdCarrPickUpDt_TO)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdDelyDt_FR)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdDelyDt_TO)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.actlDelyDt_FR)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.actlDelyDt_TO)) {

            return false;
        }

        return true;
    }

    /**
     * setVisibilityScheduling
     * @param scrnMsg NLBL3070BMsg
     */
    public static void setVisibilityScheduling(NLBL3070BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // Clear
            scrnMsg.clearGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_OrdEntry" + i);
            scrnMsg.clearGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_MngBo" + i);

            // Set Visibility (Back Order)
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).backOrdImpctTpDescTxt_A1)) {

                EZDGUIAttribute boLink = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_MngBo" + i);
                boLink.setVisibility(false);
                scrnMsg.addGUIAttribute(boLink);
            }
        }
    }

    /**
     * setVisibilityDeliveries
     * @param scrnMsg NLBL3070BMsg
     * @param handler EZDCommonHandler
     */
    public static void setVisibilityDeliveries(NLBL3070BMsg scrnMsg, EZDCommonHandler handler) {

        String preCpoNum = "";
        String preSoNum = "";

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            // Clear
            scrnMsg.clearGUIAttribute(NLBL3070Constant.SCREEN_ID, "xxSmryLineFlg#" + i);
            scrnMsg.clearGUIAttribute(NLBL3070Constant.SCREEN_ID, "OnChange_ChkBoxSoNum" + i);
            scrnMsg.clearGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_DelyInstn" + i);
            scrnMsg.clearGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_OrdEntry" + i);
            scrnMsg.clearGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_MngSO" + i);

            // Set Visibility
            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(i).soNum_B1)) {

                if (preSoNum.equals(scrnMsg.B.no(i).soNum_B1.getValue())) {

                    // [+][-]bottom
                    EZDGUIAttribute trxHdrNumBtm = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "xxSmryLineFlg#" + i);
                    trxHdrNumBtm.setVisibility(false);
                    scrnMsg.addGUIAttribute(trxHdrNumBtm);

                    // Check Box (Header)
                    EZDGUIAttribute soNumChkBox = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "OnChange_ChkBoxSoNum" + i);
                    soNumChkBox.setVisibility(false);
                    scrnMsg.addGUIAttribute(soNumChkBox);

                    // Delivery Instruction
                    EZDGUIAttribute delyInstnBtm = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_DelyInstn" + i);
                    delyInstnBtm.setVisibility(false);
                    scrnMsg.addGUIAttribute(delyInstnBtm);

                    // Sales Order Number
                    EZDGUIAttribute trxHdrNumLink = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_OrdEntry" + i);
                    trxHdrNumLink.setVisibility(false);
                    scrnMsg.addGUIAttribute(trxHdrNumLink);

                    // SO Number
                    EZDGUIAttribute soNummLink = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_MngSO" + i);
                    soNummLink.setVisibility(false);
                    scrnMsg.addGUIAttribute(soNummLink);
                }

                preCpoNum = "";

            } else {

                // SO Number
                EZDGUIAttribute soNummLink = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_MngSO" + i);
                soNummLink.setVisibility(false);
                scrnMsg.addGUIAttribute(soNummLink);

                if (preCpoNum.equals(scrnMsg.B.no(i).trxHdrNum_B1.getValue())) {

                    // [+][-]bottom
                    EZDGUIAttribute trxHdrNumBtm = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "xxSmryLineFlg#" + i);
                    trxHdrNumBtm.setVisibility(false);
                    scrnMsg.addGUIAttribute(trxHdrNumBtm);

                    // Check Box (Header)
                    EZDGUIAttribute soNumChkBox = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "OnChange_ChkBoxSoNum" + i);
                    soNumChkBox.setVisibility(false);
                    scrnMsg.addGUIAttribute(soNumChkBox);

                    // Delivery Instruction
                    EZDGUIAttribute delyInstnBtm = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_DelyInstn" + i);
                    delyInstnBtm.setVisibility(false);
                    scrnMsg.addGUIAttribute(delyInstnBtm);

                    // Sales Order Number
                    EZDGUIAttribute trxHdrNumLink = new EZDGUIAttribute(NLBL3070Constant.SCREEN_ID, "Open_OrdEntry" + i);
                    trxHdrNumLink.setVisibility(false);
                    scrnMsg.addGUIAttribute(trxHdrNumLink);
                }

                preCpoNum = scrnMsg.B.no(i).trxHdrNum_B1.getValue();
            }

            preSoNum = scrnMsg.B.no(i).soNum_B1.getValue();
        }
    }

    /**
     * 
     * setBgColorScheduling
     * 
     * @param scrnMsg NLBL3070BMsg
     */
    public static void setBgColorScheduling(NLBL3070BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NLBL3070Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLBL3070Bean.A, scrnMsg.A);
    }

    /**
     * 
     * setBgColorDeliveries
     * 
     * @param scrnMsg NLBL3070BMsg
     */
    public static void setBgColorDeliveries(NLBL3070BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(NLBL3070Constant.SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(NLBL3070Bean.B, scrnMsg.B);
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(NLBL3070Constant.BUSINESS_ID);

        if (functionIds.contains(NLBL3070Constant.FUNC_ID_UPDATE)) {

            return true;

        } else {

            return false;
        }
    }

    /**
     * AddCheckItemHeader
     * @param scrnMsg NLBL3070BMsg
     */
    public static void addCheckItemHeader(NLBL3070BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.trxHdrNum);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
        scrnMsg.addCheckItem(scrnMsg.t_MdlNm);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk);
        scrnMsg.addCheckItem(scrnMsg.soNum);
        scrnMsg.addCheckItem(scrnMsg.dsSoLineStsCd);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd);
        scrnMsg.addCheckItem(scrnMsg.schdCoordStsCd);
        scrnMsg.addCheckItem(scrnMsg.bolNum);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
        scrnMsg.addCheckItem(scrnMsg.carrCd);
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd);
        scrnMsg.addCheckItem(scrnMsg.rddDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rddDt_TO);
        scrnMsg.addCheckItem(scrnMsg.schdCarrPickUpDt_FR);
        scrnMsg.addCheckItem(scrnMsg.schdCarrPickUpDt_TO);
        scrnMsg.addCheckItem(scrnMsg.schdDelyDt_FR);
        scrnMsg.addCheckItem(scrnMsg.schdDelyDt_TO);
        scrnMsg.addCheckItem(scrnMsg.actlDelyDt_FR);
        scrnMsg.addCheckItem(scrnMsg.actlDelyDt_TO);
        scrnMsg.addCheckItem(scrnMsg.wmsDropFlg_Y);
        scrnMsg.addCheckItem(scrnMsg.wmsDropFlg_N);
        scrnMsg.addCheckItem(scrnMsg.delyReqFlg_Y);
        scrnMsg.addCheckItem(scrnMsg.delyReqFlg_N);
    }

    /**
     * addCheckItemScheduling
     * @param scrnMsg NLBL3070BMsg
     */
    public static void addCheckItemScheduling(NLBL3070BMsg scrnMsg) {

        if (0 < scrnMsg.A.getValidCount()) {

            S21SequentialSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.A, scrnMsg.xxPageShowFromNum_A,
                scrnMsg.xxPageShowToNum_A, scrnMsg.xxPageShowOfNum_A, scrnMsg.xxPageShowCurNum_A, scrnMsg.xxPageShowTotNum_A);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdCarrPickUpDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdDelyDt_A1);
            // QC#18272 Mod.
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdDelyTsDplyTxt_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDtTxt_S2);

            scrnMsg.addCheckItem(scrnMsg.A.no(i).carrNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdCoordStsCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).shpgSvcLvlCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).tempSchdRsnCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).tempSchdCmntTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdIstlDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdDelyTsDplyTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDtTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdDurnTmNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).carrAcctNum_A1);
            // START 2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).techMeetTruckFlg_A1);
            // END   2017/12/28 T.Hakodate [QC#18460(SOL#087),ADD]
            // START 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).nextActDt_A1);
            // END 2017/09/07 S.Katsuma [Sol#032(QC#13117),ADD]
        }
    }

    /**
     * addCheckItemDeliveries
     * @param scrnMsg NLBL3070BMsg
     */
    public static void addCheckItemDeliveries(NLBL3070BMsg scrnMsg) {

        if (0 < scrnMsg.B.getValidCount()) {

            S21SequentialSearchPagenationScrnSupport.checkInputForPageJump(scrnMsg, scrnMsg.B, scrnMsg.xxPageShowFromNum_B,
                scrnMsg.xxPageShowToNum_B, scrnMsg.xxPageShowOfNum_B, scrnMsg.xxPageShowCurNum_B, scrnMsg.xxPageShowTotNum_B);
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B2);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxShipQty_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).serNum_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).carrRsnCd_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).actlDelyDt_B1);
            // QC#18272 Mod.
            scrnMsg.addCheckItem(scrnMsg.B.no(i).schdDelyTsDplyTxt_B2);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).rqstRcvDtTxt_B2);

            scrnMsg.addCheckItem(scrnMsg.B.no(i).carrNm_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).proNum_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).totFrtAmt_B1);
        }
    }

    /**
     * checkInputApply
     * @param scrnMsg NLBL3070BMsg
     */
    public static void checkInputApply(NLBL3070BMsg scrnMsg) {

        if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

            if (isApplySchdAllBlank(scrnMsg)) {

                // All blank
                scrnMsg.schdCarrPickUpDt_AP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                scrnMsg.schdDelyDt_AP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                // QC#18272 Mod.
                scrnMsg.schdDelyTsDplyTxt_AP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                scrnMsg.rqstRcvDtTxt_P2.setErrorInfo(1, NLBL3070Constant.NMAM8119E);

                scrnMsg.schdCoordStsCd_AP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                scrnMsg.shpgSvcLvlCd_AP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                scrnMsg.carrNm_AP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
            }

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

            if (isApplyDelvAllBlank(scrnMsg)) {

                // All blank
                scrnMsg.carrNm_BP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                scrnMsg.proNum_BP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                scrnMsg.totFrtAmt_BP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                scrnMsg.carrRsnCd_BP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                scrnMsg.actlDelyDt_BP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                // QC#18272 Mod.
                scrnMsg.schdDelyTsDplyTxt_BP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
                scrnMsg.rqstRcvDtTxt_BP.setErrorInfo(1, NLBL3070Constant.NMAM8119E);
            }
        }

    }

    /**
     * isApplySchdAllBlank
     * @param scrnMsg NLBL3070BMsg
     * @return boolean
     */
    private static boolean isApplySchdAllBlank(NLBL3070BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.schdCarrPickUpDt_AP)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdDelyDt_AP)) {

            return false;

        // QC#18272 Add.
		} else if (ZYPCommonFunc.hasValue(scrnMsg.schdDelyTsDplyTxt_AP)) {

			return false;

        }  else if (ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDtTxt_P2)) {

			return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdCoordStsCd_AP)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_AP)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.carrNm_AP)) {

            return false;
        }

        return true;
    }

    /**
     * isApplyDelvAllBlank
     * @param scrnMsg NLBL3070BMsg
     */
    private static boolean isApplyDelvAllBlank(NLBL3070BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.carrNm_BP)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.proNum_BP)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.totFrtAmt_BP)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.carrRsnCd_BP)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.actlDelyDt_BP)) {

            return false;

        // QC#18272 Add.
        } else if (ZYPCommonFunc.hasValue(scrnMsg.schdDelyTsDplyTxt_BP)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDtTxt_BP)) {

            return false;
        }

        return true;
    }

    /**
     * addCheckItemApply
     * @param scrnMsg NLBL3070BMsg
     */
    public static void addCheckItemApply(NLBL3070BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.schdCarrPickUpDt_AP);
        scrnMsg.addCheckItem(scrnMsg.schdDelyDt_AP);
        // QC#18272 Mod.
        scrnMsg.addCheckItem(scrnMsg.schdDelyTsDplyTxt_AP);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_P2);

        scrnMsg.addCheckItem(scrnMsg.schdCoordStsCd_AP);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_AP);
        scrnMsg.addCheckItem(scrnMsg.carrNm_AP);
        scrnMsg.addCheckItem(scrnMsg.carrNm_BP);
        scrnMsg.addCheckItem(scrnMsg.proNum_BP);
        scrnMsg.addCheckItem(scrnMsg.totFrtAmt_BP);
        scrnMsg.addCheckItem(scrnMsg.carrRsnCd_BP);
        scrnMsg.addCheckItem(scrnMsg.actlDelyDt_BP);
        // QC#18272 Mod.
    }
}
