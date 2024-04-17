/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NLBL2020.NLBL2020BMsg;
import business.servlet.NLBL2020.NLBL2020Bean;
import business.servlet.NLBL2020.NLBL2020_ABMsg;
import business.servlet.NLBL2020.NLBL2020_PBMsgArray;
import business.servlet.NLBL2020.NLBL2020_SBMsgArray;
import business.servlet.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 2016/03/10   CITS      Takeshi Tokutomi      Update          QC#5242
 * 05/03/2016   CSAI            Y.Imazu         Update          QC#5771
 * 05/31/2016   CSAI            Y.Imazu         Update          QC#7334
 * 2016/09/29   CITS            S.Tanikawa      UPDATE          QC#11665
 * 2017/11/16   CITS            S.Katsuma       Update          QC#22086
 * 2017/11/22   CITS            T.Tokutomi      Update          QC#22086
 * 2018/02/16   CITS            T.Tokutomi      Update          QC#18367
 * 06/19/2018   CITS            Y.Iwasaki       Update          QC#21717
 * 2022/10/18   Hitachi         A.Kohinata      Update          QC#60559
 *</pre>
 */
public class NLBL2020CommonLogic {

    /**
     * ctrlScrnItemProtection
     * @param scrnMsg NLBL2020BMsg
     * @param handler EZDCommonHandler
     * @param functionList List<String>
     */
    public static void ctrlScrnItemProtection(NLBL2020BMsg scrnMsg, EZDCommonHandler handler, List<String> functionList) {

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(NLBL2020Constant.SCRN_ID);
        ctrlScrnItem(scrnMsg, handler, functionList);
    }

    /**
     * ctrlScrnItem
     * @param scrnMsg NLBL2020BMsg
     * @param handler EZDCommonHandler
     * @param functionList List<String>
     */
    public static void ctrlScrnItem(NLBL2020BMsg scrnMsg, EZDCommonHandler handler, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible

        // Search Option
        scrnMsg.srchOptPk_PD.setInputProtected(false);
        scrnMsg.srchOptNm_PD.setInputProtected(false);
        scrnMsg.srchOptNm_H1.setInputProtected(false);

        // Header (Enabled)
        scrnMsg.trxHdrNum_H1.setInputProtected(false);
        scrnMsg.soNum_H1.setInputProtected(false);
        scrnMsg.dsSoLineStsCd_PS.setInputProtected(false);
        scrnMsg.dsSoLineStsCd_PD.setInputProtected(false);
        scrnMsg.dsSoLineStsDescTxt_PD.setInputProtected(false);
        scrnMsg.sceOrdTpCd_PS.setInputProtected(false);
        scrnMsg.sceOrdTpCd_PD.setInputProtected(false);
        scrnMsg.sceOrdTpNm_PD.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_PS.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_PD.setInputProtected(false);
        scrnMsg.shpgSvcLvlDescTxt_PD.setInputProtected(false);
        scrnMsg.xxRtrnInvtyLocSrchTxt_RW.setInputProtected(false);
        scrnMsg.xxRtrnInvtyLocSrchTxt_SW.setInputProtected(false);
        scrnMsg.shipToCustCd_H1.setInputProtected(false);
        scrnMsg.carrCd_H1.setInputProtected(false);
        scrnMsg.mdseCd_H1.setInputProtected(false);
        scrnMsg.xxTrxDt_FR.setInputProtected(false);
        scrnMsg.xxTrxDt_TO.setInputProtected(false);
        scrnMsg.rddDt_FR.setInputProtected(false);
        scrnMsg.rddDt_TO.setInputProtected(false);
        scrnMsg.xxTrxDt_F3.setInputProtected(false);
        scrnMsg.xxTrxDt_T3.setInputProtected(false);
        scrnMsg.xxExstFlg_BO.setInputProtected(false);
        scrnMsg.xxExstFlg_NO.setInputProtected(false);
        scrnMsg.svcConfigMstrPk_H1.setInputProtected(false);
        scrnMsg.proNum_H1.setInputProtected(false);
        scrnMsg.wmsDropFlg_Y.setInputProtected(false);
        scrnMsg.wmsDropFlg_N.setInputProtected(false);
        scrnMsg.xxDtNm_F1.setInputProtected(false);
        scrnMsg.xxDtNm_T1.setInputProtected(false);
        scrnMsg.xxDtNm_F2.setInputProtected(false);
        scrnMsg.xxDtNm_T2.setInputProtected(false);
        scrnMsg.xxDtNm_F3.setInputProtected(false);
        scrnMsg.xxDtNm_T3.setInputProtected(false);

        // Header (Protected)
        scrnMsg.rtlWhNm_H1.setInputProtected(true);
        scrnMsg.rtlSwhNm_H1.setInputProtected(true);
        scrnMsg.dsAcctNm_H1.setInputProtected(true);
        scrnMsg.carrNm_H1.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);

        // Detail Header
        scrnMsg.xxPageShowCurNum_A.setInputProtected(false);
        scrnMsg.xxPageShowFromNum_A.setInputProtected(false);
        scrnMsg.xxPageShowOfNum_A.setInputProtected(false);
        scrnMsg.xxPageShowToNum_A.setInputProtected(false);
        scrnMsg.xxPageShowTotNum_A.setInputProtected(false);

        // Button activation
        handler.setButtonEnabled(NLBL2020Constant.BTN_SAVE_SEARCH, true);
        handler.setButtonEnabled(NLBL2020Constant.BTN_DELETE_SEARCH, true);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_RTL_WH, true);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_RTL_SWH, true);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_SHIP_TO_CUST, true);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_CARRIER, true);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_MDSE_INFO, true);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH, true);

        // Common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_SAVE[0], NLBL2020Constant.BTN_CMN_SAVE[1], NLBL2020Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_SUBMIT[0], NLBL2020Constant.BTN_CMN_SUBMIT[1], NLBL2020Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_APPLY[0], NLBL2020Constant.BTN_CMN_APPLY[1], NLBL2020Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_APPROVE[0], NLBL2020Constant.BTN_CMN_APPROVE[1], NLBL2020Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_REJECT[0], NLBL2020Constant.BTN_CMN_REJECT[1], NLBL2020Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_DOWNLOAD[0], NLBL2020Constant.BTN_CMN_DOWNLOAD[1], NLBL2020Constant.BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_DETELE[0], NLBL2020Constant.BTN_CMN_DETELE[1], NLBL2020Constant.BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_CLEAR[0], NLBL2020Constant.BTN_CMN_CLEAR[1], NLBL2020Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_RESET[0], NLBL2020Constant.BTN_CMN_RESET[1], NLBL2020Constant.BTN_CMN_RESET[2], 1, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_RETURN[0], NLBL2020Constant.BTN_CMN_RETURN[1], NLBL2020Constant.BTN_CMN_RETURN[2], 1, null);

        boolean inquiry = chkInquiryRole(functionList);

        if (NLBL2020Constant.TAB_SO_LIST.equals(scrnMsg.xxDplyTab.getValue())) {

            ctrlScrnItemProtectionForSOList(scrnMsg, handler, inquiry);

            // Detail button protection
            handler.setButtonEnabled(NLBL2020Constant.BTN_SELECT_ALL, false);
            handler.setButtonEnabled(NLBL2020Constant.BTN_UN_SELECT_ALL, false);
            handler.setButtonEnabled(NLBL2020Constant.BTN_PRINT, false);
            handler.setButtonEnabled(NLBL2020Constant.BTN_CANCLE, false);
            handler.setButtonProperties(NLBL2020Constant.BTN_SHIP[0], NLBL2020Constant.BTN_SHIP[1], NLBL2020Constant.BTN_SHIP[2], 0, null);
            handler.setButtonEnabled(NLBL2020Constant.BTN_SO_CLOSE, false);
            handler.setButtonEnabled(NLBL2020Constant.BTN_APPLY, false);
            // QC#18367 Add
            handler.setButtonEnabled(NLBL2020Constant.BTN_CUSTOM_DOC_PRINT, false);

            // Apply area protection
            scrnMsg.xxLinkAncr_D1.setInputProtected(true);
            scrnMsg.carrNm_D1.setInputProtected(true);
            scrnMsg.totFrtAmt_D1.setInputProtected(true);
            scrnMsg.totFrtAmt_D1.setAppFracDigit(2); // UPDATE 2016/09/29 QC#11665 
            scrnMsg.proNum_D1.setInputProtected(true);

            if (scrnMsg.A.getValidCount() > 0) {

                handler.setButtonEnabled(NLBL2020Constant.BTN_SELECT_ALL, true);
                handler.setButtonEnabled(NLBL2020Constant.BTN_UN_SELECT_ALL, true);
                handler.setButtonEnabled(NLBL2020Constant.BTN_PRINT, true);
                // QC#18367 Add.
                handler.setButtonEnabled(NLBL2020Constant.BTN_CUSTOM_DOC_PRINT, true);

                if (!inquiry) {

                    handler.setButtonEnabled(NLBL2020Constant.BTN_CANCLE, true);
                    handler.setButtonEnabled(NLBL2020Constant.BTN_SO_CLOSE, true);
                    handler.setButtonEnabled(NLBL2020Constant.BTN_APPLY, true);

                    handler.setButtonProperties(NLBL2020Constant.BTN_SHIP[0] //
                            , NLBL2020Constant.BTN_SHIP[1], NLBL2020Constant.BTN_SHIP[2], 1, null);

//                    if (ZYPCommonFunc.hasValue(scrnMsg.actvFlg_SC) //
//                            && ZYPConstant.FLG_ON_Y.equals(scrnMsg.actvFlg_SC.getValue())) {
//                        // Ship Conf Btn Active.
//                        handler.setButtonProperties(NLBL2020Constant.BTN_SHIP[0] //
//                                , NLBL2020Constant.BTN_SHIP[1], NLBL2020Constant.BTN_SHIP[2], 1, null);
//                    }

                    scrnMsg.xxLinkAncr_D1.setInputProtected(false);
                    scrnMsg.carrNm_D1.setInputProtected(false);
                    scrnMsg.totFrtAmt_D1.setInputProtected(false);
                    scrnMsg.proNum_D1.setInputProtected(false);
                }

                // add start 2022/10/18 QC#60559
                handler.setButtonProperties(NLBL2020Constant.BTN_CMN_DOWNLOAD[0], NLBL2020Constant.BTN_CMN_DOWNLOAD[1], NLBL2020Constant.BTN_CMN_DOWNLOAD[2], 1, null);
                // add end 2022/10/18 QC#60559
            }

        } else {

            ctrlScrnItemProtectionForPickList(scrnMsg, handler, inquiry);

            if (!inquiry) {

                handler.setButtonProperties(NLBL2020Constant.BTN_CMN_SUBMIT[0], NLBL2020Constant.BTN_CMN_SUBMIT[1], NLBL2020Constant.BTN_CMN_SUBMIT[2], 1, null);
            }

            // add start 2022/10/18 QC#60559
            if (scrnMsg.B.getValidCount() > 0) {
                handler.setButtonProperties(NLBL2020Constant.BTN_CMN_DOWNLOAD[0], NLBL2020Constant.BTN_CMN_DOWNLOAD[1], NLBL2020Constant.BTN_CMN_DOWNLOAD[2], 1, null);
            }
            // add end 2022/10/18 QC#60559
        }
    }

    /**
     * searchItemCheck
     * @param scrnMsg NLBL2020BMsg
     */
    public static void searchItemCheck(NLBL2020BMsg scrnMsg) {

        // mandatory check
        if (!ZYPCommonFunc.hasValue(scrnMsg.trxHdrNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.soNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.svcConfigMstrPk_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxRtrnInvtyLocSrchTxt_RW) && !ZYPCommonFunc.hasValue(scrnMsg.xxRtrnInvtyLocSrchTxt_SW) && !ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.carrCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.proNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1)
                && !ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_TO) && !ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR)
                && !ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO) && !ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_F3) && !ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_T3)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsSoLineStsCd_PS) && !ZYPCommonFunc.hasValue(scrnMsg.sceOrdTpCd_PS) && !ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_PS)) {

            scrnMsg.trxHdrNum_H1.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.soNum_H1.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.dsSoLineStsCd_PS.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.sceOrdTpCd_PS.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.shpgSvcLvlCd_PS.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.svcConfigMstrPk_H1.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.xxRtrnInvtyLocSrchTxt_RW.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.xxRtrnInvtyLocSrchTxt_SW.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.shipToCustCd_H1.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.carrCd_H1.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.proNum_H1.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.mdseCd_H1.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.xxTrxDt_FR.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.xxTrxDt_TO.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.rddDt_FR.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.rddDt_TO.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.xxTrxDt_F3.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.xxTrxDt_T3.setErrorInfo(1, NLBL2020Constant.NLZM2276E);
            scrnMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
        }

        // check Item
        scrnMsg.addCheckItem(scrnMsg.trxHdrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk_H1);
        scrnMsg.addCheckItem(scrnMsg.dsSoLineStsCd_PS);
        scrnMsg.addCheckItem(scrnMsg.sceOrdTpCd_PS);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_PS);
        scrnMsg.addCheckItem(scrnMsg.xxRtrnInvtyLocSrchTxt_RW);
        scrnMsg.addCheckItem(scrnMsg.xxRtrnInvtyLocSrchTxt_SW);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.carrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.proNum_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_TO);
        scrnMsg.addCheckItem(scrnMsg.rddDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rddDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_F3);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_T3);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_F1);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_T1);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_F2);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_T2);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_F3);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_T3);
        scrnMsg.addCheckItem(scrnMsg.xxExstFlg_BO);
        scrnMsg.addCheckItem(scrnMsg.xxExstFlg_NO);

        // Shipping Creation Date (From)
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxDtNm_F1)) {
            scrnMsg.xxTrxDt_FR.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Shipping Creation Date(From)"});
        }
        checkTimeAndAmPm(scrnMsg.xxDtNm_F1, scrnMsg.rqstRcvDtTxt_F1);

        // Shipping Creation Date (To)
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_TO) && ZYPCommonFunc.hasValue(scrnMsg.xxDtNm_T1)) {
            scrnMsg.xxTrxDt_TO.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Shipping Creation Date(From)"});
        }
        checkTimeAndAmPm(scrnMsg.xxDtNm_T1, scrnMsg.rqstRcvDtTxt_T1);

        // Request Delivery Date (From)
        if (!ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxDtNm_F2)) {
            scrnMsg.rddDt_FR.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Request Delivery Date(From)"});
        }
        checkTimeAndAmPm(scrnMsg.xxDtNm_F2, scrnMsg.rqstRcvDtTxt_F2);

        // Request Delivery Date (To)
        if (!ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO) && ZYPCommonFunc.hasValue(scrnMsg.xxDtNm_T2)) {
            scrnMsg.rddDt_TO.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Request Delivery Date(To)"});
        }
        checkTimeAndAmPm(scrnMsg.xxDtNm_T2, scrnMsg.rqstRcvDtTxt_T2);

        // Need By Date (From)
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_F3) && ZYPCommonFunc.hasValue(scrnMsg.xxDtNm_F3)) {
            scrnMsg.xxTrxDt_F3.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Need By Date(From)"});
        }
        checkTimeAndAmPm(scrnMsg.xxDtNm_F3, scrnMsg.rqstRcvDtTxt_F3);

        // Need By Date (To)
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_T3) && ZYPCommonFunc.hasValue(scrnMsg.xxDtNm_T3)) {
            scrnMsg.xxTrxDt_T3.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Need By Date(To)"});
        }
        checkTimeAndAmPm(scrnMsg.xxDtNm_T3, scrnMsg.rqstRcvDtTxt_T3);

        // check Item
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_TO);
        scrnMsg.addCheckItem(scrnMsg.rddDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rddDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_F3);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_T3);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_F1);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_T1);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_F2);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_T2);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_F3);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_T3);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_F1);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_T1);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_F2);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_T2);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_F3);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_T3);

        // Date check
        if (ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_TO) && ZYPDateUtil.compare(scrnMsg.xxTrxDt_FR.getValue(), scrnMsg.xxTrxDt_TO.getValue()) > 0) {

            scrnMsg.xxTrxDt_FR.setErrorInfo(1, NLBL2020Constant.NLZM2277E, new String[] {NLBL2020Constant.DISPLAY_SHIP_ORD_DT_TO, NLBL2020Constant.DISPLAY_SHIP_ORD_DT_FROM });
            scrnMsg.xxTrxDt_TO.setErrorInfo(1, NLBL2020Constant.NLZM2277E, new String[] {NLBL2020Constant.DISPLAY_SHIP_ORD_DT_TO, NLBL2020Constant.DISPLAY_SHIP_ORD_DT_FROM });
            scrnMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.rddDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.rddDt_TO) && ZYPDateUtil.compare(scrnMsg.rddDt_FR.getValue(), scrnMsg.rddDt_TO.getValue()) > 0) {

            scrnMsg.rddDt_FR.setErrorInfo(1, NLBL2020Constant.NLZM2277E, new String[] {NLBL2020Constant.DISPLAY_RDD_DT_TO, NLBL2020Constant.DISPLAY_RDD_DT_FROM });
            scrnMsg.rddDt_TO.setErrorInfo(1, NLBL2020Constant.NLZM2277E, new String[] {NLBL2020Constant.DISPLAY_RDD_DT_TO, NLBL2020Constant.DISPLAY_RDD_DT_FROM });
            scrnMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_F3) && ZYPCommonFunc.hasValue(scrnMsg.xxTrxDt_T3) && ZYPDateUtil.compare(scrnMsg.xxTrxDt_F3.getValue(), scrnMsg.xxTrxDt_T3.getValue()) > 0) {
            scrnMsg.xxTrxDt_F3.setErrorInfo(1, NLBL2020Constant.NLZM2277E, new String[] {NLBL2020Constant.DISPLAY_NEED_BY_DT_TO, NLBL2020Constant.DISPLAY_NEED_BY_DT_FROM });
            scrnMsg.xxTrxDt_T3.setErrorInfo(1, NLBL2020Constant.NLZM2277E, new String[] {NLBL2020Constant.DISPLAY_NEED_BY_DT_TO, NLBL2020Constant.DISPLAY_NEED_BY_DT_FROM });
            scrnMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
        }


        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRtrnInvtyLocSrchTxt_RW) && ZYPCommonFunc.hasValue(scrnMsg.xxRtrnInvtyLocSrchTxt_SW)) {
            scrnMsg.xxRtrnInvtyLocSrchTxt_RW.setErrorInfo(1, NLBL2020Constant.NLBM1361E, null);
            scrnMsg.setMessageInfo(NLBL2020Constant.ZZM9037E);
        }
    }

    /**
     * checkTimeAndAmPm
     * @param timeHHMM EZDBStringItem
     * @param timeAMPM EZDBStringItem
     */
    private static void checkTimeAndAmPm(EZDBStringItem timeHHMM, EZDBStringItem timeAMPM) {

        if (ZYPCommonFunc.hasValue(timeHHMM) && !ZYPCommonFunc.hasValue(timeAMPM)) {

            timeAMPM.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Time(AM/PM)"});
        }

        if (!ZYPCommonFunc.hasValue(timeHHMM) && ZYPCommonFunc.hasValue(timeAMPM)) {

            timeHHMM.setErrorInfo(1, NLBL2020Constant.ZZM9000E, new String[] {"Time"});
        }

        if (ZYPCommonFunc.hasValue(timeHHMM) && ZYPCommonFunc.hasValue(timeAMPM)) {

            if (!checkTime(timeHHMM.getValue(), timeAMPM.getValue())) {

                timeHHMM.setErrorInfo(1, NLBL2020Constant.NWAM0665E);
            }
        }
    }

    /**
     * checkTime
     * @param timeHHMM String
     * @param timeAMPM String
     * @return boolean
     */
    private static boolean checkTime(String timeHHMM, String timeAMPM) {

        int h = 0;
        int m = 0;
        if (0 <= timeHHMM.indexOf(":")) {
            String[] temp = timeHHMM.split(":");
            if (temp.length < 2) {
                return false;
            }
            h = Integer.valueOf(temp[0]);
            m = Integer.valueOf(temp[1]);
        } else {
            if (timeHHMM.length() == NLBL2020Constant.HOUR_MINUTE_STR_LENGTH) {
                h = Integer.valueOf(timeHHMM.substring(0, 2));
                m = Integer.valueOf(timeHHMM.substring(2));
            } else if (timeHHMM.length() == NLBL2020Constant.HOUR_MINUTE_STR_LENGTH - 1) {
                timeHHMM = "0" + timeHHMM;
                h = Integer.valueOf(timeHHMM.substring(0, 2));
                m = Integer.valueOf(timeHHMM.substring(2));
            } else {
                return false;
            }
        }
        if (h < 0) {
            return false;
        } else if (NLBL2020Constant.HALF_DAY_HOURS < h) {
            return false;
        }
        if (NLBL2020Constant.TIME_PM.equals(timeAMPM)) {
            h += NLBL2020Constant.HALF_DAY_HOURS;
        }
        if (NLBL2020Constant.ONE_DAY_HOURS <= h) {
            h = 0;
        }
        if (m < 0) {
            return false;
        }
        if (NLBL2020Constant.ONE_HOUR_MINUTES <= m) {
            return false;
        }
        return true;
    }

    /**
     * addCheckItemApply
     * @param scrnMsg NLBL2020BMsg
     */
    public static void addCheckItemApply(NLBL2020BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.carrNm_D1);
        scrnMsg.addCheckItem(scrnMsg.totFrtAmt_D1);
        scrnMsg.addCheckItem(scrnMsg.proNum_D1);

        addCheckItemSoDetail(scrnMsg);
    }

    /**
     * ctrlScrnItemProtectionForSOList
     * @param scrnMsg NLBL2020BMsg
     * @param handler EZDCommonHandler
     * @param inquiry boolean
     */
    public static void ctrlScrnItemProtectionForSOList(NLBL2020BMsg scrnMsg, EZDCommonHandler handler, boolean inquiry) {

        // full Protect
        scrnMsg.A.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            // Same Line check
            if (i > 0) {

                // Visibility Item
                EZDGUIAttribute headChkBox = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_HEAD_CHK_BOX + i);
                EZDGUIAttribute ancher = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_SRC_ORDER_LINK + i);
                EZDGUIAttribute srcOrdNum = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_SRC_ORD_NUM + i);
                EZDGUIAttribute srcOrdLinerNum = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_SRC_ORD_LINE_NUM + i);
                EZDGUIAttribute soNum = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_SO_NUM + i);
                EZDGUIAttribute soLineNum = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_SO_LINE_NUM + i);

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).soNum_HI)) {

                    if (scrnMsg.A.no(i).soNum_A1.getValue().equals(scrnMsg.A.no(i - 1).soNum_A1.getValue())
                            && scrnMsg.A.no(i).soSlpNum_A1.getValue().equals(scrnMsg.A.no(i - 1).soSlpNum_A1.getValue())) {

                        headChkBox.setVisibility(false);
                        srcOrdNum.setVisibility(false);
                        soNum.setVisibility(false);
                        soLineNum.setVisibility(false);
                        srcOrdLinerNum.setVisibility(false);
                    }

                    if (scrnMsg.A.no(i).soNum_HI.getValue().equals(scrnMsg.A.no(i - 1).soNum_HI.getValue())) {

                        scrnMsg.A.no(i).trxHdrNum_A1.clear();
                        scrnMsg.A.no(i).soNum_A1.clear();

                        headChkBox.setVisibility(false);
                        ancher.setVisibility(false);

                        scrnMsg.addGUIAttribute(headChkBox);
                        scrnMsg.addGUIAttribute(ancher);
                    }

                    if (scrnMsg.A.no(i).soNum_HI.getValue().equals(scrnMsg.A.no(i - 1).soNum_HI.getValue())
                            && scrnMsg.A.no(i).soSlpNum_HI.getValue().equals(scrnMsg.A.no(i - 1).soSlpNum_HI.getValue())) {

                        scrnMsg.A.no(i).soSlpNum_A1.clear();
                    }

                } else {

                    if (scrnMsg.A.no(i).trxHdrNum_HI.getValue().equals(scrnMsg.A.no(i - 1).trxHdrNum_HI.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i - 1).soNum_HI)) {

                        scrnMsg.A.no(i).trxHdrNum_A1.clear();

                        headChkBox.setVisibility(false);
                        ancher.setVisibility(false);

                        scrnMsg.addGUIAttribute(headChkBox);
                        scrnMsg.addGUIAttribute(ancher);
                    }
                }

                if (scrnMsg.A.no(i).trxHdrNum_HI.getValue().equals(scrnMsg.A.no(i - 1).trxHdrNum_HI.getValue())
                        && scrnMsg.A.no(i).dplyLineNum_HI.getValue().equals(scrnMsg.A.no(i - 1).dplyLineNum_HI.getValue())) {

                    scrnMsg.A.no(i).dplyLineNum_A1.clear();
                }
            }

            // START 2017/11/16 S.Katsuma [QC#22086,ADD]
            
            if (!isProtectedOrder(scrnMsg.A.no(i), scrnMsg.glblCmpyCd.getValue())) {
                // Common Edit
                scrnMsg.A.no(i).xxExstFlg_A1.setInputProtected(false);
                scrnMsg.A.no(i).xxYesNoCd_A2.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkAncr_A1.setInputProtected(false);
                handler.setButtonEnabled(NLBL2020Constant.BTN_CARRIER, i, false);

                if (!inquiry) {

                     // Not shipped
                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).soLineOpenFlg_AH.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).shipFlg_AH.getValue())) {

                        scrnMsg.A.no(i).xxExstFlg_A2.setInputProtected(false);
                        scrnMsg.A.no(i).shipQty_A1.setInputProtected(false);
                        scrnMsg.A.no(i).carrNm_A1.setInputProtected(false);
                        scrnMsg.A.no(i).actlShpgSvcLvlCd_A1.setInputProtected(false);
                        scrnMsg.A.no(i).totFrtAmt_A1.setInputProtected(false);
                        scrnMsg.A.no(i).totFrtAmt_A1.setAppFracDigit(2); // UPDATE 2016/09/29 QC#11665
                        scrnMsg.A.no(i).proNum_A1.setInputProtected(false);
                        handler.setButtonEnabled(NLBL2020Constant.BTN_CARRIER, i, true);

                    } else {

                        scrnMsg.A.no(i).xxExstFlg_A2.clear();
                    }

                    // Line Ship not available
                    if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxExstFlg_C4.getValue())) {

                        scrnMsg.A.no(i).xxExstFlg_A2.setInputProtected(true);
                        scrnMsg.A.no(i).shipQty_A1.setInputProtected(true);
                    }

                    // Serial
                    if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).serNumTakeFlg_AH.getValue())) {

                        if (scrnMsg.A.no(i).shpgQty_A1.getValue().abs().intValue() > 1) {

                            scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                            handler.setButtonEnabled(NLBL2020Constant.BTN_SERIAL, i, true);

                        } else {

                            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).soLineOpenFlg_AH.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).shipFlg_AH.getValue())) {

                                scrnMsg.A.no(i).serNum_A1.setInputProtected(false);

                            } else {

                                scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                            }

                            handler.setButtonEnabled(NLBL2020Constant.BTN_SERIAL, i, false);
                        }

                    } else {

                        scrnMsg.A.no(i).serNum_A1.setInputProtected(true);
                        handler.setButtonEnabled(NLBL2020Constant.BTN_SERIAL, i, false);
                    }

                } else {

                    handler.setButtonEnabled(NLBL2020Constant.BTN_CARRIER, i, false);
                    handler.setButtonEnabled(NLBL2020Constant.BTN_SERIAL, i, false);
                }

                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).soNum_HI)) {

                    scrnMsg.A.no(i).xxExstFlg_A1.setInputProtected(true);
                    scrnMsg.A.no(i).xxExstFlg_A1.clear();
                }
            } else {
                /** Protected Line */
                scrnMsg.A.no(i).xxYesNoCd_A2.setInputProtected(false);
                scrnMsg.A.no(i).xxLinkAncr_A1.setInputProtected(false);
                handler.setButtonEnabled(NLBL2020Constant.BTN_CARRIER, i, false);
                handler.setButtonEnabled(NLBL2020Constant.BTN_SERIAL, i, false);
            }
            // END 2017/11/16 S.Katsuma [QC#22086,ADD]
        }

        if (0 < scrnMsg.A.getValidCount() && NLBL2020Constant.TAB_SO_LIST.equals(scrnMsg.xxDplyTab.getValue())) {

            // set alternate rows back-ground color
            S21TableColorController tblColor = new S21TableColorController(NLBL2020Constant.SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(NLBL2020Bean.A, scrnMsg.A);
        }
    }

    /**
     * ctrlScrnItemProtectionForPickList
     * @param scrnMsg NLBL2020BMsg
     * @param handler EZDCommonHandler
     * @param inquiry boolean
     */
    public static void ctrlScrnItemProtectionForPickList(NLBL2020BMsg scrnMsg, EZDCommonHandler handler, boolean inquiry) {

        // full Protect
        scrnMsg.B.setInputProtected(true);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

            // Visibility
            if (i > 0) {

                if (scrnMsg.B.no(i).trxHdrNum_HD.getValue().equals(scrnMsg.B.no(i - 1).trxHdrNum_HD.getValue())) {

                    scrnMsg.B.no(i).trxHdrNum_B1.clear();
                }

                if (scrnMsg.B.no(i).trxHdrNum_HD.getValue().equals(scrnMsg.B.no(i - 1).trxHdrNum_HD.getValue())
                        && scrnMsg.B.no(i).dplyLineNum_HD.getValue().equals(scrnMsg.B.no(i - 1).dplyLineNum_HD.getValue())) {

                    scrnMsg.B.no(i).dplyLineNum_B1.clear();
                }

                if (scrnMsg.B.no(i).soNum_HD.getValue().equals(scrnMsg.B.no(i - 1).soNum_HD.getValue())
                        && scrnMsg.B.no(i).soSlpNum_HD.getValue().equals(scrnMsg.B.no(i - 1).soSlpNum_HD.getValue())) {

                    scrnMsg.B.no(i).soSlpNum_B1.clear();

                    EZDGUIAttribute swhCd = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_RTL_SWH_CD_B1 + i);
                    swhCd.setVisibility(false);
                    scrnMsg.addGUIAttribute(swhCd);

                    EZDGUIAttribute mdseCd = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_MDSE_CD_B1 + i);
                    mdseCd.setVisibility(false);
                    scrnMsg.addGUIAttribute(mdseCd);

                    EZDGUIAttribute mdseNm = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_MDSE_NM_B1 + i);
                    mdseNm.setVisibility(false);
                    scrnMsg.addGUIAttribute(mdseNm);

                    EZDGUIAttribute shpgQty = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_SHPG_QTY_B1 + i);
                    shpgQty.setVisibility(false);
                    scrnMsg.addGUIAttribute(shpgQty);

                    EZDGUIAttribute shipQty = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_SHIP_QTY_B1 + i);
                    shipQty.setVisibility(false);
                    scrnMsg.addGUIAttribute(shipQty);
                }

                if (scrnMsg.B.no(i).soNum_HD.getValue().equals(scrnMsg.B.no(i - 1).soNum_HD.getValue())) {

                    scrnMsg.B.no(i).soNum_B1.clear();

                    EZDGUIAttribute whNm = new EZDGUIAttribute(NLBL2020Constant.SCRN_ID, NLBL2020Constant.HTML_ID_RTL_WH_NM_B1 + i);
                    whNm.setVisibility(false);
                    scrnMsg.addGUIAttribute(whNm);
                }
            }

            // Open Line
            if (!inquiry && ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).soLineOpenFlg_B1.getValue())) {

                scrnMsg.B.no(i).pickConfQty_B1.setInputProtected(false);

                // Serialized
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).serNumTakeFlg_B1.getValue())) {

                    scrnMsg.B.no(i).serNum_B1.setInputProtected(false);

                } else {

                    scrnMsg.B.no(i).serNum_B1.setInputProtected(true);
                }

                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.B.no(i).shipFlg_HD.getValue())) {

                    scrnMsg.B.no(i).pickConfQty_B1.setInputProtected(true);
                    scrnMsg.B.no(i).serNum_B1.setInputProtected(true);
                }

            } else {

                scrnMsg.B.no(i).pickConfQty_B1.setInputProtected(true);
                scrnMsg.B.no(i).serNum_B1.setInputProtected(true);
            }
        }

        if (0 < scrnMsg.B.getValidCount() && NLBL2020Constant.TAB_PICK_LIST.equals(scrnMsg.xxDplyTab.getValue())) {

            // set alternate rows back-ground color
            S21TableColorController tblColor = new S21TableColorController(NLBL2020Constant.SCRN_ID, scrnMsg);
            tblColor.setAlternateRowsBG("B", scrnMsg.B);
        }
    }

    /**
     * chkInquiryRole
     * @param functionList List<String>
     * @return true:Inquiry false:Update
     */
    public static boolean chkInquiryRole(List<String> functionList) {
        return !functionList.contains(NLBL2020Constant.ROLE_UPDATE);
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NLBL2020BMsg
     */
    public static void setInitParamForCarrierPopup(NLBL2020BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();

        // vender Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, NLBL2020Constant.TBL_NM_FOR_OTBD_CARR_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, NLBL2020Constant.TBL_CD_COL_NM_FOR_CARR_CD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, NLBL2020Constant.TBL_NM_COL_NM_FOR_CARR_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, NLBL2020Constant.TBL_SORT_COL_NM_FOR_CARR_SORT_NUM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, NLBL2020Constant.SCR_NM_FOR_PRNT_CARRIER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, NLBL2020Constant.HDR_CD_LB_NM_FOR_CARRIER_CD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, NLBL2020Constant.HDR_NM_LB_NM_FOR_CARRIER_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, NLBL2020Constant.DTL_CD_LB_NM_FOR_CARRIER_CD);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, NLBL2020Constant.DTL_NM_LB_NM_FOR_CARRIER_NM);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NLBL2020BMsg
     * @return Object[]
     */
    public static Object[] setParamForCarrierPopup(NLBL2020BMsg scrnMsg) {

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P1;
        params[1] = scrnMsg.xxTblCdColNm_P1;
        params[2] = scrnMsg.xxTblNmColNm_P1;
        params[3] = scrnMsg.xxTblSortColNm_P1;
        params[4] = scrnMsg.xxScrNm_P1;
        params[5] = scrnMsg.xxHdrCdLbNm_P1;
        params[6] = scrnMsg.xxHdrNmLbNm_P1;
        params[7] = scrnMsg.xxDtlCdLbNm_P1;
        params[8] = scrnMsg.xxDtlNmLbNm_P1;
        params[9] = scrnMsg.carrCd_H1;
        params[10] = scrnMsg.carrNm_H1;

        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NLBL2020BMsg
     * @param isRtlWhLink boolean
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setLocationPopupParam(NLBL2020BMsg scrnMsg, boolean isRtlWhLink) {

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxComnScrColValTxt, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxComnScrColValTxt, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxComnScrColValTxt, scrnMsg.xxRtrnInvtyLocSrchTxt_RW);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(15).xxComnScrColValTxt, ZYPConstant.FLG_ON_Y);

        if (isRtlWhLink) {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxComnScrColValTxt, ZYPConstant.FLG_ON_Y);

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(8).xxComnScrColValTxt, scrnMsg.xxRtrnInvtyLocSrchTxt_SW);
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(10).xxComnScrColValTxt, ZYPConstant.FLG_OFF_N);
        }

        Object[] params = toArrayPopup(scrnMsg.P, 16);
        return params;
    }

    /**
     * Set Mdse Info Popup param
     * @param scrnMsg NLBL2020BMsg
     * @return LocationPopup Param (NMAL6800) Object[]
     */
    public static Object[] setMdseInfoPopupParam(NLBL2020BMsg scrnMsg) {

        scrnMsg.P.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.mdseCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, NLBL2020Constant.MDSE_CD_TARGET_ALL);

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
     * setSerNumEntryPopupParam
     * @param scrnMsg NLBL2020BMsg
     * @param scrnMsgALine NLBL2020_ABMsg
     * @param inquiry boolean
     * @return NLBL2020_SBMsgArray
     */
    public static NLBL2020_SBMsgArray setSerNumEntryPopupParam(NLBL2020BMsg scrnMsg, NLBL2020_ABMsg scrnMsgALine, boolean inquiry) {

        scrnMsg.S.clear();

        for (int i = 0; i < scrnMsgALine.shipQty_A1.getValue().abs().intValue(); i++) {

            if (i < scrnMsgALine.serNum_AH.length()) {

                if (ZYPCommonFunc.hasValue(scrnMsgALine.serNum_AH.no(i))) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).serNum_SN, scrnMsgALine.serNum_AH.no(i));
                } else {

                    if (scrnMsgALine.shipQty_A1.getValue().abs().intValue() == 1) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).serNum_SN, scrnMsgALine.serNum_A1);
                    }
                }
            }

            if (inquiry || !ZYPConstant.FLG_ON_Y.equals(scrnMsgALine.soLineOpenFlg_AH.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsgALine.shipFlg_AH.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxEdtModeFlg_SN, ZYPConstant.FLG_OFF_N);

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxEdtModeFlg_SN, ZYPConstant.FLG_ON_Y);
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxHdrNum_SN, scrnMsgALine.soNum_HI);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).xxDplyTrxNumTxt_SN, scrnMsgALine.soSlpNum_HI);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).mdseCd_SN, scrnMsgALine.mdseCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).rtlSwhCd_SN, scrnMsgALine.shipFromRtlSwhCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).invtyLocCd_SN, scrnMsgALine.invtyLocCd_AH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).svcConfigMstrPk_SN, scrnMsgALine.pickSvcConfigMstrPk_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).serNumTakeFlg_SN, scrnMsg.S.no(i).xxEdtModeFlg_SN);
        }

        scrnMsg.S.setValidCount(scrnMsgALine.shipQty_A1.getValue().abs().intValue());
        return scrnMsg.S;
    }

    /**
     * setShipToCustPopupParam
     * @param scrnMsg NLBL2020BMsg
     * @return LocationPopup Param (NMAL6800) Object[]
     */
    public static Object[] setShipToCustPopupParam(NLBL2020BMsg scrnMsg) {

        scrnMsg.P.no(0).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.dsAcctNm_H1);
        scrnMsg.P.no(2).xxPopPrm.clear();
        scrnMsg.P.no(3).xxPopPrm.clear();
        scrnMsg.P.no(4).xxPopPrm.clear();
        scrnMsg.P.no(5).xxPopPrm.clear();
        scrnMsg.P.no(6).xxPopPrm.clear();
        scrnMsg.P.no(7).xxPopPrm.clear();
        scrnMsg.P.no(8).xxPopPrm.clear();
        scrnMsg.P.no(9).xxPopPrm.clear();
        scrnMsg.P.no(10).xxPopPrm.clear();
        scrnMsg.P.no(11).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(12).xxPopPrm, NLBL2020Constant.DISP_HRCH_ACCT_CD_SHIP);
        scrnMsg.P.no(13).xxPopPrm.clear();
        scrnMsg.P.no(14).xxPopPrm.clear();
        scrnMsg.P.no(15).xxPopPrm.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(16).xxPopPrm, scrnMsg.shipToCustCd_H1);
        scrnMsg.P.no(17).xxPopPrm.clear();
        scrnMsg.P.no(18).xxPopPrm.clear();
        scrnMsg.P.no(19).xxPopPrm.clear();
        scrnMsg.P.no(20).xxPopPrm.clear();
        scrnMsg.P.no(21).xxPopPrm.clear();
        scrnMsg.P.no(22).xxPopPrm.clear();
        scrnMsg.P.no(23).xxPopPrm.clear();

        scrnMsg.P.setValidCount(24);

        Object[] params = new Object[24];

        for (int i = 0; i < scrnMsg.P.getValidCount() && i < params.length; i++) {

            params[i] = scrnMsg.P.no(i).xxPopPrm;
        }

        return params;
    }

    /**
     * addCheckTableItem
     * @param scrnMsg NLBL2020BMsg
     */
    public static void addCheckTableItem(NLBL2020BMsg scrnMsg) {

        if (NLBL2020Constant.TAB_SO_LIST.equals(scrnMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxExstFlg_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxExstFlg_A2);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).pickConfQty_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).shipQty_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).proNum_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).totFrtAmt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).carrCd_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).carrNm_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).actlShpgSvcLvlCd_A1);
            }

        } else if (NLBL2020Constant.TAB_PICK_LIST.equals(scrnMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {

                scrnMsg.addCheckItem(scrnMsg.B.no(i).pickConfQty_B1);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).serNum_B1);
            }
        }
    }

    /**
     * protectFullScreenItem
     * @param scrnMsg NLBL2020BMsg
     * @param handler EZDCommonHandler
     */
    public static void protectFullScreenItem(NLBL2020BMsg scrnMsg, EZDCommonHandler handler) {

        // screen item
        scrnMsg.setInputProtected(true);

        // button activation
        handler.setButtonEnabled(NLBL2020Constant.BTN_SAVE_SEARCH, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_DELETE_SEARCH, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_RTL_WH, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_RTL_SWH, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_SHIP_TO_CUST, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_CARRIER, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH_MDSE_INFO, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SEARCH, false);

        handler.setButtonEnabled(NLBL2020Constant.BTN_SELECT_ALL, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_UN_SELECT_ALL, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_PRINT, false);
        handler.setButtonEnabled(NLBL2020Constant.BTN_CANCLE, false);
        handler.setButtonProperties(NLBL2020Constant.BTN_SHIP[0], NLBL2020Constant.BTN_SHIP[1], NLBL2020Constant.BTN_SHIP[2], 0, null);
        handler.setButtonEnabled(NLBL2020Constant.BTN_SO_CLOSE, false);
        // QC#18367 Add.
        handler.setButtonEnabled(NLBL2020Constant.BTN_CUSTOM_DOC_PRINT, false);

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_SAVE[0], NLBL2020Constant.BTN_CMN_SAVE[1], NLBL2020Constant.BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_SUBMIT[0], NLBL2020Constant.BTN_CMN_SUBMIT[1], NLBL2020Constant.BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_APPLY[0], NLBL2020Constant.BTN_CMN_APPLY[1], NLBL2020Constant.BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_APPROVE[0], NLBL2020Constant.BTN_CMN_APPROVE[1], NLBL2020Constant.BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_REJECT[0], NLBL2020Constant.BTN_CMN_REJECT[1], NLBL2020Constant.BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_DOWNLOAD[0], NLBL2020Constant.BTN_CMN_DOWNLOAD[1], NLBL2020Constant.BTN_CMN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_DETELE[0], NLBL2020Constant.BTN_CMN_DETELE[1], NLBL2020Constant.BTN_CMN_DETELE[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_CLEAR[0], NLBL2020Constant.BTN_CMN_CLEAR[1], NLBL2020Constant.BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_RESET[0], NLBL2020Constant.BTN_CMN_RESET[1], NLBL2020Constant.BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(NLBL2020Constant.BTN_CMN_RETURN[0], NLBL2020Constant.BTN_CMN_RETURN[1], NLBL2020Constant.BTN_CMN_RETURN[2], 0, null);
    }

    /**
     * Popup array.
     * @param p NLBL2020_PBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArrayPopup(NLBL2020_PBMsgArray p, int size) {

        Object[] param = new Object[size];

        for (int i = 0; i < size; i++) {

            param[i] = p.no(i).xxComnScrColValTxt;
        }

        return param;
    }

    /**
     * chkMultipleLocNm
     * @param chkLocNm String
     * @return String
     */
    public static String chkMultipleLocNm(String chkLocNm) {

        if (ZYPCommonFunc.hasValue(chkLocNm)) {

            if (chkLocNm.indexOf(",") != -1) {

                ArrayList<String> locNmList = new ArrayList<String>();
                String[] locNmArray = chkLocNm.split(",");

                for (int i = 0; i < locNmArray.length; i++) {

                    if (!locNmArray[i].trim().equals("") && locNmArray[i].length() > 0) {

                        String locNm = locNmArray[i].trim();

                        if (ZYPCommonFunc.hasValue(locNm) && locNm.length() > 0) {

                            locNmList.add(locNm);
                        }
                    }
                }

                if (locNmList != null && !locNmList.isEmpty()) {

                    if (locNmList.size() > 1) {

                        return "Multiple";

                    } else {

                        return locNmList.get(0);
                    }
                }
            }
        }

        return chkLocNm;
    }

    /**
     * addCheckItemSoDetail NLBL2020BMsg so line format check.
     * @param scrnMsg
     */
    public static void addCheckItemSoDetail(NLBL2020BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxExstFlg_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).carrNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).totFrtAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A1);
        }
    }

    // START 2017/11/16 S.Katsuma [QC#22086,ADD] Update 2017/11/22.
    public static boolean isProtectedOrder(NLBL2020_ABMsg abMsg, String glblCmpyCd) {

        String protectOrdListTxt = ZYPCodeDataUtil.getVarCharConstValue("NLBL2020_PROTECT_ORD", glblCmpyCd);
        List<String> protectOrdList = new ArrayList<String>();
        if (protectOrdListTxt != null) {
            protectOrdList = Arrays.asList(protectOrdListTxt.split(","));
        }

        if (protectOrdList.contains(abMsg.sceOrdTpCd_AH.getValue())) {
            return true;
        }

        return false;
    }
    // END 2017/11/16 S.Katsuma [QC#22086,ADD]
}
