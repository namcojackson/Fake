/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280.common;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1280.NPAL1280BMsg;
import business.servlet.NPAL1280.NPAL1280_XBMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 02/29/2016   CITS            K.Ogino          Update          QC#4636
 * 03/01/2016   CITS            K.Ogino          Update          QC#4729
 * 03/01/2016   CITS            K.Ogino          Update          QC#4734
 * 03/01/2016   CITS            K.Ogino          Update          QC#4743
 * 03/01/2016   CITS            K.Ogino          Update          QC#4643
 * 03/01/2016   CITS            K.Ogino          Update          QC#4635
 * 03/01/2016   CITS            K.Ogino          Update          QC#4639
 * 03/01/2016   CITS            K.Ogino          Update          QC#4732
 * 03/02/2016   CITS            K.Ogino          Update          QC#4676
 * 03/06/2016   CITS            K.Ogino          Update          QC#4614
 * 03/21/2016   CITS            K.Ogino          Update          QC#5717
 * 05/26/2016   CITS            K.Ogino          Update          QC#8940
 * 05/26/2016   CITS            K.Ogino          Update          QC#8959
 * 05/26/2016   CITS            K.Ogino          Update          QC#8950
 * 05/26/2016   CITS            K.Ogino          Update          QC#8935
 * 05/26/2016   CITS            K.Ogino          Update          QC#8963
 * 05/26/2016   CITS            K.Ogino          Update          QC#8964
 * 12/22/2016   CITS            S.Endo           Update          QC#15717
 * 01/19/2017   CITS            S.Endo           Update          QC#15717
 * 01/24/2017   CITS            S.Endo           Update          QC#15717
 * 07/07/2017   CITS            S.Katsuma        Update          QC#18425
 * 07/14/2017   CITS            S.Katsuma        Update          QC#18425
 * 08/07/2017   CITS            H.Naoi           Update          Sol#097(QC#18398)
 * 09/01/2017   CITS            R.Shimamoto      Update          QC#20439
 * 09/26/2017   CITS            R.Shimamoto      Update          QC#21006
 * 10/06/2017   CITS            T.Tokutomi       Update          QC#21209
 * 11/07/2017   CITS            S.Katsuma        Update          SOL#014(QC#18401)
 * 12/19/2017   CITS            K.Kameoka        Update          SOL#060(QC#14858)
 * 02/08/2018   CITS            K.Ogino          Update          QC#21169
 * 07/09/2018   CITS            K.Ogino          Update          QC#24918
 * 07/19/2018   CITS            K.Kameoka        Update          QC#26990
 * 10/25/2018   CITS            T.Tokutomi       Update          QC#28941
 * 10/25/2018   CITS            K.Kameoka        Update          QC#27770
 * 11/12/2018   CITS            T.Tokutomi       Update          QC#28939
 * 12/21/2018   CITS            K.Ogino          Update          QC#29729
 * 01/11/2019   CITS            T.Tokutomi       Update          QC#28709
 * 03/20/2019   Fujitsu         T.Ogura          Update          QC#30769
 * 05/21/2019   Fujitsu         T.Ogura          Update          QC#50138
 * 07/15/2019   CITS            K.Ogino          Update          QC#51575
 * 09/20/2019   CITS            R.Shimamoto      Update          QC#52362
 * 10/01/2019   CITS            R.Shimamoto      Update          QC#53816
 * 10/04/2019   CITS            R.Shimamoto      Update          QC#53300
 * 12/03/2019   Fujitsu         T.Ogura          Update          QC#54814
 * 03/17/2020   CITS            M.Furugoori      Update          QC#56122
 * 02/03/2023   Hitachi         T.kuroda         Update          QC#60966
 * 04/04/2023   Hitachi         S.Dong           Update          QC#61287
 *</pre>
 */
public class NPAL1280CommonLogic {

    /**
     * Control items on INIT event
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     * @param glblCmpyCd String
     * @param loginFullName String
     * @param loginUserId String
     * @param funcList List<String>
     * @param salesDate String
     */
    public static void controlItemsOnInit(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, String loginFullName, String loginUserId, List<String> funcList, String salesDate) {
        if (TAB_DETAIL.equals(bMsg.xxDplyTab.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(BUSINESS_SCREEN_ID, bMsg);
            tblColor.setAlternateRowsBG("A", bMsg.A);
        }
        initializeCommandButtons(handler);
        controlItemsInit(handler, bMsg, glblCmpyCd, loginFullName, loginUserId, funcList, salesDate);
        controlButtonsInit(handler, bMsg);
        // setIndispensable(bMsg);
    }

    /**
     * Control items on INIT event
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     * @param glblCmpyCd String
     * @param funcList List<String>
     */
    public static void controlItemsOnSearchNoOpen(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, List<String> funcList) {

        if (TAB_DETAIL.equals(bMsg.xxDplyTab.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(BUSINESS_SCREEN_ID, bMsg);
            tblColor.setAlternateRowsBG("A", bMsg.A);
        }
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(CUST_DROP_SHIP_PO_QULF, glblCmpyCd);
        controlItemsSearchNoOpen(handler, bMsg, glblCmpyCd, funcList);
        controlButtonsSearchNoOpen(handler, bMsg, varCharConstVal, funcList);
    }

    /**
     * Control items on INIT event
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     * @param glblCmpyCd String
     * @param funcList List<String>
     */
    public static void controlItemsOnSearchOpenY(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, List<String> funcList) {
        if (TAB_DETAIL.equals(bMsg.xxDplyTab.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(BUSINESS_SCREEN_ID, bMsg);
            tblColor.setAlternateRowsBG("A", bMsg.A);
        }
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(CUST_DROP_SHIP_PO_QULF, glblCmpyCd);
        if (isUpdatable(bMsg)) {
            controlItemsSearchOpenY(handler, bMsg, glblCmpyCd, funcList, varCharConstVal);
            controlButtonsSearchOpenY(handler, bMsg, varCharConstVal, funcList);
        } else {
            controlItemsSearchNoOpen(handler, bMsg, glblCmpyCd, funcList);
            controlButtonsSearchNoOpen(handler, bMsg, varCharConstVal, funcList);
        }
    }

    /**
     * Control items on INIT event
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     * @param glblCmpyCd String
     * @param loginFullName String
     * @param loginUserId String
     * @param funcList List<String>
     * @param childFlg boolean
     */
    public static void controlItemsOnSearchOpenN(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, String loginFullName, String loginUserId, List<String> funcList, boolean childFlg) {
        if (TAB_DETAIL.equals(bMsg.xxDplyTab.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(BUSINESS_SCREEN_ID, bMsg);
            tblColor.setAlternateRowsBG("A", bMsg.A);
        }
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(CUST_DROP_SHIP_PO_QULF, glblCmpyCd);
        if (isUpdatable(bMsg)) {
            controlItemsSearchOpenN(handler, bMsg, glblCmpyCd, loginFullName, loginUserId, funcList, childFlg);
            controlButtonsSearchOpenN(handler, bMsg, varCharConstVal, funcList);
        } else {
            controlItemsSearchNoOpen(handler, bMsg, glblCmpyCd, funcList);
            controlButtonsSearchNoOpen(handler, bMsg, varCharConstVal, funcList);
        }
    }

    /**
     * Initialize command buttons
     * @param handler EZDCommonHandler
     */
    public static void initializeCommandButtons(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_BTN_1_ID, BTN_CMN_BTN_1_NAME, BTN_CMN_BTN_1_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2_ID, BTN_CMN_BTN_2_NAME, BTN_CMN_BTN_2_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_3_ID, BTN_CMN_BTN_3_NAME, BTN_CMN_BTN_3_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4_ID, BTN_CMN_BTN_4_NAME, BTN_CMN_BTN_4_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5_ID, BTN_CMN_BTN_5_NAME, BTN_CMN_BTN_5_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6_ID, BTN_CMN_BTN_6_NAME, BTN_CMN_BTN_6_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7_ID, BTN_CMN_BTN_7_NAME, BTN_CMN_BTN_7_VAL, 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8_ID, BTN_CMN_BTN_8_NAME, BTN_CMN_BTN_8_VAL, 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9_ID, BTN_CMN_BTN_9_NAME, BTN_CMN_BTN_9_VAL, 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10_ID, BTN_CMN_BTN_10_NAME, BTN_CMN_BTN_10_VAL, 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_11_ID, BTN_CMN_BTN_11_NAME, BTN_CMN_BTN_11_VAL, 0, null);
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     * @param glblCmpyCd String
     * @param loginFullName String
     * @param loginUserId String
     * @param funcList List<String>
     * @param salesDate String
     */
    public static void controlItemsInit(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, String loginFullName, String loginUserId, List<String> funcList, String salesDate) {
        boolean isInputProtected = false;
        if (isUpdatable(bMsg)) {
            bMsg.xxLinkAncr_A0.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A1.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A2.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A3.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A4.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A5.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A6.setInputProtected(isInputProtected);
            bMsg.prchReqNum.setInputProtected(isInputProtected);
            bMsg.prchReqTpCd_SL.setInputProtected(isInputProtected);
            bMsg.prchReqStsCd.setInputProtected(true);
            bMsg.prchReqStsNm.setInputProtected(true);
            bMsg.prchReqApvlStsCd.setInputProtected(true);
            bMsg.prchReqApvlStsNm.setInputProtected(true);
            bMsg.prchReqCratDt.setInputProtected(true);
            bMsg.rqstRcvDt.setInputProtected(isInputProtected);
            // QC#21169
            handler.setButtonEnabled(BTN_APPLY, true);
            // START 02/03/2023 T.Kuroda [QC#60966, ADD]
            bMsg.rqstShipDt.setInputProtected(isInputProtected);
            handler.setButtonEnabled(BTN_RQSTSHIPDT_APPLY, true);
            // END   02/03/2023 T.Kuroda [QC#60966, ADD]
            bMsg.xxDtTm.setInputProtected(true);
            bMsg.xxTpCd_SL.setInputProtected(true);
            bMsg.prchReqSrcTpNm.setInputProtected(true);
            bMsg.prchReqSrcTpCd.setInputProtected(true);
            bMsg.trxRefNum.setInputProtected(true);
            bMsg.poQlfyCd.setInputProtected(true);
            //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            bMsg.mrpPlnNm.setInputProtected(true);
            //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            bMsg.prchReqCratByNm.setInputProtected(true);
            bMsg.prchReqRqstByPsnCd.setInputProtected(isInputProtected);
            bMsg.fullPsnNm.setInputProtected(isInputProtected);
            bMsg.prchGrpCd_SL.setInputProtected(isInputProtected);
            bMsg.prchReqCmntTxt.setInputProtected(isInputProtected);
            // QC#53300 2019/10/04 Mod Start
//            bMsg.prntVndNm.setInputProtected(isInputProtected);
            bMsg.prntVndNm.setInputProtected(true);
            bMsg.prntVndCd.setInputProtected(isInputProtected);
            bMsg.locNm.setInputProtected(true);
            // QC#53300 2019/10/04 Mod End
            bMsg.vndCd.setInputProtected(isInputProtected);
            bMsg.rtlWhNm.setInputProtected(true);
            bMsg.rtlSwhNm.setInputProtected(true);
            bMsg.destRtlWhCd.setInputProtected(isInputProtected);
            bMsg.destRtlSwhCd.setInputProtected(isInputProtected);
            bMsg.cpoOrdNum.setInputProtected(isInputProtected);
            bMsg.dsOrdPosnNum.setInputProtected(isInputProtected);
            // Detail Tab
            bMsg.xxTotAmt.setInputProtected(true);
            bMsg.ccyCd.setInputProtected(true);
            // Additional Header Tab
            //QC#26990 Mod Start QC#28709 Update
            //Protect Control change to False.
            String qlfyCdDropShip = ZYPCodeDataUtil.getVarCharConstValue(CUST_DROP_SHIP_PO_QULF, glblCmpyCd);
            if (ZYPCommonFunc.hasValue(qlfyCdDropShip) && qlfyCdDropShip.equals(bMsg.poQlfyCd.getValue())) {
                // Customer drop ship. not change address.
                bMsg.xxLocNm.setInputProtected(true);
                bMsg.xxLocNm_HS.setInputProtected(true);
                bMsg.shipToAddlLocNm_HS.setInputProtected(true);
                bMsg.xxAllLineAddr_HS.setInputProtected(true);
                bMsg.shipToPostCd_HS.setInputProtected(true);
                bMsg.shipToCtyAddr_HS.setInputProtected(true);
                bMsg.shipToCntyNm_HS.setInputProtected(true);
                bMsg.shipToStCd_HS.setInputProtected(true);
                bMsg.shipToProvNm_HS.setInputProtected(true);
                bMsg.shipToCtryCd_HS.setInputProtected(true);
                bMsg.ctryNm_HS.setInputProtected(true);
                bMsg.ctacPsnNm_HS.setInputProtected(true);
                bMsg.xxLinkAncr_AL.setInputProtected(true);
                handler.setButtonEnabled("GetAddress", false);

            } else {
                bMsg.xxLocNm.setInputProtected(isInputProtected);
                bMsg.xxLocNm_HS.setInputProtected(isInputProtected);
                bMsg.shipToAddlLocNm_HS.setInputProtected(isInputProtected);
                bMsg.xxAllLineAddr_HS.setInputProtected(isInputProtected);
                bMsg.shipToPostCd_HS.setInputProtected(isInputProtected);
                bMsg.shipToCtyAddr_HS.setInputProtected(isInputProtected);
                bMsg.shipToCntyNm_HS.setInputProtected(isInputProtected);
                bMsg.shipToStCd_HS.setInputProtected(isInputProtected);
                bMsg.shipToProvNm_HS.setInputProtected(isInputProtected);
                bMsg.shipToCtryCd_HS.setInputProtected(isInputProtected);
                bMsg.ctryNm_HS.setInputProtected(true);
                bMsg.ctacPsnNm_HS.setInputProtected(isInputProtected);
                bMsg.xxLinkAncr_AL.setInputProtected(isInputProtected);
                handler.setButtonEnabled("GetAddress", true);
            }
            //QC#26990 Mod End

            bMsg.locNm_HB.setInputProtected(true);
            bMsg.addlLocNm_HB.setInputProtected(true);
            bMsg.xxAllLineAddr_HB.setInputProtected(true);
            bMsg.postCd_HB.setInputProtected(true);
            bMsg.ctyAddr_HB.setInputProtected(true);
            bMsg.cntyNm_HB.setInputProtected(true);
            bMsg.shipToCntyNm_HB.setInputProtected(true);
            bMsg.stCd_HB.setInputProtected(true);
            bMsg.provNm_HB.setInputProtected(true);
            bMsg.ctryNm_HB.setInputProtected(true);
            bMsg.frtCondCd_SL.setInputProtected(isInputProtected);
            bMsg.shpgSvcLvlCd_SL.setInputProtected(isInputProtected);
            // QC#29155 Add
            bMsg.carrCd_HF.setInputProtected(isInputProtected);
            bMsg.locNm_HF.setInputProtected(true);
            bMsg.carrAcctNum_HF.setInputProtected(isInputProtected);
            bMsg.prchReqRelErrMsgTxt_SL.setInputProtected(true);
            bMsg.xxDtTm_HP.setInputProtected(true);
            bMsg.rtlWhNm_HC.setInputProtected(true);
            bMsg.xxAllLineAddr_HC.setInputProtected(true);
            bMsg.ctyAddr_HC.setInputProtected(true);
            bMsg.stCd_HC.setInputProtected(true);
            bMsg.postCd_HC.setInputProtected(true);
            bMsg.xxTotAmt.setAppFracDigit(IDX_2);
            //QC#26990 Add Start
            bMsg.shipToCustCd.setInputProtected(isInputProtected);
            //QC#26990 Add End
            for (int i = 0; i < bMsg.A.getValidCount(); i++) {
                if (PO_MDSE_CMPSN_TP.CHILD.equals(bMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                    controlItemsChild(handler, bMsg, glblCmpyCd, funcList, i);
                } else if ((ZYPCommonFunc.hasValue(bMsg.A.no(i).openStsFlg_D) && ZYPConstant.FLG_OFF_N.equals(bMsg.A.no(i).openStsFlg_D.getValue()))) {
                    controlItemsCloseLine(handler, bMsg, glblCmpyCd, funcList, i);
                } else {
                    bMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    bMsg.A.no(i).xxLineNum_A1.setInputProtected(true);
                    bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqLineTpCd_HD.setValue(PRCH_REQ_LINE_TP.GOODS);
                    bMsg.A.no(i).mdseCd_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).aslMdseCd_A1.setInputProtected(isInputProtected);
                    // QC#28939 Update.
                    bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqDsplUomCd_A1.setInputProtected(true);
                    bMsg.A.no(i).prntVndNm_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).locNm_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).rqstRcvDt_A1.setInputProtected(isInputProtected);
                    // START 02/03/2023 T.Kuroda [QC#60966, ADD]
                    bMsg.A.no(i).rqstShipDt_A1.setInputProtected(isInputProtected);
                    // END   02/03/2023 T.Kuroda [QC#60966, ADD]
                    //bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(true);
                    if (!PRCH_REQ_LINE_TP.GOODS.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue()) && (!PO_MDSE_CMPSN_TP.CHILD.equals(bMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue()))) {
                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(false);
                    } else {
                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(true);
                    }
                    // QC#21209
                    bMsg.A.no(i).spclInstnCmntTxt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqLineStsNm_A1.setInputProtected(true);
                    bMsg.A.no(i).xxUnitPrc_A1.setInputProtected(true);
                    bMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(true);
                    bMsg.A.no(i).coaProdCd_A1.setInputProtected(true);
                    bMsg.A.no(i).xxLineNum_TR.setInputProtected(true);
                    bMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(true);
                    bMsg.A.no(i).poSchdRelDt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).poOrdNum_A1.setInputProtected(true);
                    bMsg.A.no(i).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).poSubmtTs_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(true);
                    if (ZYPCommonFunc.hasValue(bMsg.A.no(i).poSubmtTs_A1)) {
                        // IDX_8: Timestamp value substring yyyymmdd
                        // format
                        ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(i).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
                    }
                    bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
                    bMsg.A.no(i).xxUnitPrc_A1.setAppFracDigit(IDX_2);
                    // setIndispensable(bMsg, i);
                }
                bMsg.prchReqSrcTpNm.setValue(PRCH_REQ_NUM);
                // START 2017/07/14 S.Katsuma [QC#18425]
                // bMsg.prchReqCratByNm.setValue(loginFullName);
                // END 2017/07/14 S.Katsuma [QC#18425]
                // START 2017/07/07 S.Katsuma [QC#18425]
                // bMsg.prchReqRqstByPsnCd.setValue(loginUserId);
                // bMsg.fullPsnNm.setValue(loginFullName);
                // END 2017/07/07 S.Katsuma [QC#18425]
                bMsg.prchReqCratDt.setValue(salesDate);
            }
        } else {
            isInputProtected = true;
            bMsg.xxLinkAncr_A0.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A1.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A2.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A3.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A4.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A5.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A6.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_AL.setInputProtected(isInputProtected);
            bMsg.prchReqNum.setInputProtected(isInputProtected);
            bMsg.prchReqTpCd_SL.setInputProtected(isInputProtected);
            bMsg.prchReqStsCd.setInputProtected(isInputProtected);
            bMsg.prchReqStsNm.setInputProtected(isInputProtected);
            bMsg.prchReqApvlStsCd.setInputProtected(isInputProtected);
            bMsg.prchReqApvlStsNm.setInputProtected(isInputProtected);
            bMsg.prchReqCratDt.setInputProtected(isInputProtected);
            bMsg.rqstRcvDt.setInputProtected(isInputProtected);
            // QC#21169
            handler.setButtonEnabled(BTN_APPLY, false);
            // START 02/03/2023 T.Kuroda [QC#60966, ADD]
            bMsg.rqstShipDt.setInputProtected(isInputProtected);
            handler.setButtonEnabled(BTN_RQSTSHIPDT_APPLY, false);
            // END   02/03/2023 T.Kuroda [QC#60966, ADD]
            bMsg.xxDtTm.setInputProtected(true);
            bMsg.xxTpCd_SL.setInputProtected(true);
            bMsg.prchReqSrcTpNm.setInputProtected(isInputProtected);
            bMsg.prchReqSrcTpCd.setInputProtected(isInputProtected);
            bMsg.trxRefNum.setInputProtected(isInputProtected);
            bMsg.poQlfyCd.setInputProtected(isInputProtected);
            //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) START
            bMsg.mrpPlnNm.setInputProtected(isInputProtected);
            //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) END
            bMsg.prchReqCratByNm.setInputProtected(isInputProtected);
            bMsg.prchReqRqstByPsnCd.setInputProtected(isInputProtected);
            bMsg.fullPsnNm.setInputProtected(isInputProtected);
            bMsg.prchGrpCd_SL.setInputProtected(isInputProtected);
            bMsg.prchReqCmntTxt.setInputProtected(isInputProtected);
            bMsg.prntVndNm.setInputProtected(isInputProtected);
            bMsg.vndCd.setInputProtected(isInputProtected);
            bMsg.locNm.setInputProtected(isInputProtected);
            bMsg.rtlWhNm.setInputProtected(isInputProtected);
            bMsg.rtlSwhNm.setInputProtected(isInputProtected);
            bMsg.xxLocNm.setInputProtected(isInputProtected);
            bMsg.destRtlWhCd.setInputProtected(isInputProtected);
            bMsg.destRtlSwhCd.setInputProtected(isInputProtected);
            bMsg.cpoOrdNum.setInputProtected(isInputProtected);
            bMsg.dsOrdPosnNum.setInputProtected(isInputProtected);
            bMsg.xxTotAmt.setInputProtected(isInputProtected);
            bMsg.ccyCd.setInputProtected(isInputProtected);
            bMsg.xxLocNm_HS.setInputProtected(isInputProtected);
            bMsg.shipToAddlLocNm_HS.setInputProtected(isInputProtected);
            bMsg.xxAllLineAddr_HS.setInputProtected(isInputProtected);
            bMsg.shipToPostCd_HS.setInputProtected(isInputProtected);
            bMsg.shipToCtyAddr_HS.setInputProtected(isInputProtected);
            bMsg.shipToCntyNm_HS.setInputProtected(isInputProtected);
            bMsg.shipToStCd_HS.setInputProtected(isInputProtected);
            bMsg.shipToProvNm_HS.setInputProtected(isInputProtected);
            bMsg.ctryNm_HS.setInputProtected(isInputProtected);
            bMsg.ctacPsnNm_HS.setInputProtected(isInputProtected);
            bMsg.locNm_HB.setInputProtected(isInputProtected);
            bMsg.addlLocNm_HB.setInputProtected(isInputProtected);
            bMsg.xxAllLineAddr_HB.setInputProtected(isInputProtected);
            bMsg.postCd_HB.setInputProtected(isInputProtected);
            bMsg.ctyAddr_HB.setInputProtected(isInputProtected);
            bMsg.cntyNm_HB.setInputProtected(isInputProtected);
            bMsg.shipToCntyNm_HB.setInputProtected(isInputProtected);
            bMsg.stCd_HB.setInputProtected(isInputProtected);
            bMsg.provNm_HB.setInputProtected(isInputProtected);
            bMsg.ctryNm_HB.setInputProtected(isInputProtected);
            bMsg.frtCondCd_SL.setInputProtected(isInputProtected);
            bMsg.shpgSvcLvlCd_SL.setInputProtected(isInputProtected);
            // QC#29155
            bMsg.carrCd_HF.setInputProtected(isInputProtected);
            bMsg.locNm_HF.setInputProtected(true);
            bMsg.carrAcctNum_HF.setInputProtected(isInputProtected);
            bMsg.prchReqRelErrMsgTxt_SL.setInputProtected(isInputProtected);
            bMsg.xxDtTm_HP.setInputProtected(isInputProtected);
            bMsg.rtlWhNm_HC.setInputProtected(isInputProtected);
            bMsg.xxAllLineAddr_HC.setInputProtected(isInputProtected);
            bMsg.ctyAddr_HC.setInputProtected(isInputProtected);
            bMsg.stCd_HC.setInputProtected(isInputProtected);
            bMsg.postCd_HC.setInputProtected(isInputProtected);
            bMsg.xxTotAmt.setAppFracDigit(IDX_2);
            //QC#26990 Add Start
            bMsg.shipToCtryCd_HS.setInputProtected(isInputProtected);
            bMsg.shipToCustCd.setInputProtected(isInputProtected);
            //QC#26990 Add End
            // QC#28709 Add.
            bMsg.shipToCtryCd_HS.setInputProtected(isInputProtected);
            handler.setButtonEnabled("GetAddress", !isInputProtected);

            for (int i = 0; i < bMsg.A.getValidCount(); i++) {
                bMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                bMsg.A.no(i).xxLineNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
                ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).prchReqLineTpCd_HD, bMsg.A.no(i).prchReqLineTpCd_A1);
                bMsg.A.no(i).mdseCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).aslMdseCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqDsplUomCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prntVndNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).locNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).rqstRcvDt_A1.setInputProtected(isInputProtected);
                // START 02/03/2023 T.Kuroda [QC#60966, ADD]
                bMsg.A.no(i).rqstShipDt_A1.setInputProtected(isInputProtected);
                // END   02/03/2023 T.Kuroda [QC#60966, ADD]
                bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqLineStsNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxUnitPrc_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaProdCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxLineNum_TR.setInputProtected(isInputProtected);
                bMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSchdRelDt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poOrdNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSubmtTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(isInputProtected);
                if (ZYPCommonFunc.hasValue(bMsg.A.no(i).poSubmtTs_A1)) {
                    // IDX_8: Timestamp value substring yyyymmdd
                    // format
                    ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(i).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
                }
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
                bMsg.A.no(i).xxUnitPrc_A1.setAppFracDigit(IDX_2);
                // setIndispensable(bMsg, i);
                // QC#21209
                bMsg.A.no(i).spclInstnCmntTxt_A1.setInputProtected(isInputProtected);
            }
        }
    }

    private static void controlItemsSearchNoOpen(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, List<String> funcList) {
        boolean isInputProtected = true;
        bMsg.xxLinkAncr_A0.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A1.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A2.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A3.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A4.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A5.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A6.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_AL.setInputProtected(isInputProtected);
        bMsg.prchReqTpCd_SL.setInputProtected(isInputProtected);
        bMsg.prchReqStsCd.setInputProtected(isInputProtected);
        bMsg.prchReqStsNm.setInputProtected(isInputProtected);
        bMsg.prchReqApvlStsCd.setInputProtected(isInputProtected);
        bMsg.prchReqApvlStsNm.setInputProtected(isInputProtected);
        bMsg.prchReqCratDt.setInputProtected(isInputProtected);
        bMsg.rqstRcvDt.setInputProtected(isInputProtected);
        // QC#21169
        handler.setButtonEnabled(BTN_APPLY, false);
        // START 02/03/2023 T.Kuroda [QC#60966, ADD]
        bMsg.rqstShipDt.setInputProtected(isInputProtected);
        handler.setButtonEnabled(BTN_RQSTSHIPDT_APPLY, false);
        // END   02/03/2023 T.Kuroda [QC#60966, ADD]
        bMsg.xxDtTm.setInputProtected(true);
        bMsg.xxTpCd_SL.setInputProtected(true);
        bMsg.prchReqSrcTpNm.setInputProtected(isInputProtected);
        bMsg.prchReqSrcTpCd.setInputProtected(isInputProtected);
        bMsg.trxRefNum.setInputProtected(isInputProtected);
        bMsg.poQlfyCd.setInputProtected(isInputProtected);
        //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        bMsg.mrpPlnNm.setInputProtected(isInputProtected);
        //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        bMsg.prchReqCratByNm.setInputProtected(isInputProtected);
        bMsg.prchReqRqstByPsnCd.setInputProtected(isInputProtected);
        bMsg.fullPsnNm.setInputProtected(isInputProtected);
        bMsg.prchGrpCd_SL.setInputProtected(isInputProtected);
        bMsg.prchReqCmntTxt.setInputProtected(isInputProtected);
        bMsg.prntVndNm.setInputProtected(isInputProtected);
        // QC#53300 2019/10/04 Add Start
        bMsg.prntVndCd.setInputProtected(isInputProtected);
        // QC#53300 2019/10/04 Add End
        bMsg.vndCd.setInputProtected(isInputProtected);
        bMsg.locNm.setInputProtected(isInputProtected);
        bMsg.rtlWhNm.setInputProtected(isInputProtected);
        bMsg.rtlSwhNm.setInputProtected(isInputProtected);
        bMsg.xxLocNm.setInputProtected(isInputProtected);
        bMsg.destRtlWhCd.setInputProtected(isInputProtected);
        bMsg.destRtlSwhCd.setInputProtected(isInputProtected);
        bMsg.cpoOrdNum.setInputProtected(isInputProtected);
        bMsg.dsOrdPosnNum.setInputProtected(isInputProtected);
        bMsg.xxTotAmt.setInputProtected(isInputProtected);
        bMsg.ccyCd.setInputProtected(isInputProtected);
        bMsg.xxLocNm_HS.setInputProtected(isInputProtected);
        bMsg.shipToAddlLocNm_HS.setInputProtected(isInputProtected);
        bMsg.xxAllLineAddr_HS.setInputProtected(isInputProtected);
        bMsg.shipToPostCd_HS.setInputProtected(isInputProtected);
        bMsg.shipToCtyAddr_HS.setInputProtected(isInputProtected);
        bMsg.shipToCntyNm_HS.setInputProtected(isInputProtected);
        bMsg.shipToStCd_HS.setInputProtected(isInputProtected);
        bMsg.shipToProvNm_HS.setInputProtected(isInputProtected);
        bMsg.ctryNm_HS.setInputProtected(isInputProtected);
        bMsg.ctacPsnNm_HS.setInputProtected(isInputProtected);
        bMsg.locNm_HB.setInputProtected(isInputProtected);
        bMsg.addlLocNm_HB.setInputProtected(isInputProtected);
        bMsg.xxAllLineAddr_HB.setInputProtected(isInputProtected);
        bMsg.postCd_HB.setInputProtected(isInputProtected);
        bMsg.ctyAddr_HB.setInputProtected(isInputProtected);
        bMsg.cntyNm_HB.setInputProtected(isInputProtected);
        bMsg.shipToCntyNm_HB.setInputProtected(isInputProtected);
        bMsg.stCd_HB.setInputProtected(isInputProtected);
        bMsg.provNm_HB.setInputProtected(isInputProtected);
        bMsg.ctryNm_HB.setInputProtected(isInputProtected);
        bMsg.frtCondCd_SL.setInputProtected(isInputProtected);
        bMsg.shpgSvcLvlCd_SL.setInputProtected(isInputProtected);
        // QC#29155 Add.
        bMsg.carrCd_HF.setInputProtected(isInputProtected);
        bMsg.locNm_HF.setInputProtected(true);
        bMsg.carrAcctNum_HF.setInputProtected(isInputProtected);
        bMsg.prchReqRelErrMsgTxt_SL.setInputProtected(isInputProtected);
        bMsg.xxDtTm_HP.setInputProtected(isInputProtected);
        bMsg.rtlWhNm_HC.setInputProtected(isInputProtected);
        bMsg.xxAllLineAddr_HC.setInputProtected(isInputProtected);
        bMsg.ctyAddr_HC.setInputProtected(isInputProtected);
        bMsg.stCd_HC.setInputProtected(isInputProtected);
        bMsg.postCd_HC.setInputProtected(isInputProtected);
        bMsg.xxTotAmt.setAppFracDigit(IDX_2);
        //QC#26990 Add Start
        bMsg.shipToCtryCd_HS.setInputProtected(isInputProtected);
        bMsg.shipToCustCd.setInputProtected(isInputProtected);
        //QC#26990 Add End
        // QC#28709 Add.
        bMsg.shipToCtryCd_HS.setInputProtected(isInputProtected);
        handler.setButtonEnabled("GetAddress", !isInputProtected);
       
        for (int i = 0; i < bMsg.A.getValidCount(); i++) {
            bMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            bMsg.A.no(i).xxLineNum_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
            ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).prchReqLineTpCd_HD, bMsg.A.no(i).prchReqLineTpCd_A1);
            bMsg.A.no(i).mdseCd_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).aslMdseCd_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).prchReqDsplUomCd_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).prntVndNm_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).locNm_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).rqstRcvDt_A1.setInputProtected(isInputProtected);
            // START 02/03/2023 T.Kuroda [QC#60966, ADD]
            bMsg.A.no(i).rqstShipDt_A1.setInputProtected(isInputProtected);
            // END   02/03/2023 T.Kuroda [QC#60966, ADD]
            bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).prchReqLineStsNm_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).xxUnitPrc_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).coaProdCd_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).xxLineNum_TR.setInputProtected(isInputProtected);
            bMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).poSchdRelDt_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).poOrdNum_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).poSubmtTs_A1.setInputProtected(isInputProtected);
            bMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(isInputProtected);
            if (ZYPCommonFunc.hasValue(bMsg.A.no(i).poSubmtTs_A1)) {
                // IDX_8: Timestamp value substring yyyymmdd format
                ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(i).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
            }
            bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
            bMsg.A.no(i).xxUnitPrc_A1.setAppFracDigit(IDX_2);
            // setIndispensable(bMsg, i);
            // QC#21209
            bMsg.A.no(i).spclInstnCmntTxt_A1.setInputProtected(isInputProtected);
        }
    }

    private static void controlItemsSearchOpenN(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, String loginFullName, String loginUserId, List<String> funcList, boolean childFlg) {
        boolean isInputProtected = true;
        bMsg.xxLinkAncr_A0.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A1.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A2.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A3.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A4.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A5.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A6.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_AL.setInputProtected(isInputProtected);
        bMsg.prchReqTpCd_SL.setInputProtected(isInputProtected);
        bMsg.prchReqStsCd.setInputProtected(isInputProtected);
        bMsg.prchReqStsNm.setInputProtected(isInputProtected);
        bMsg.prchReqApvlStsCd.setInputProtected(isInputProtected);
        bMsg.prchReqApvlStsNm.setInputProtected(isInputProtected);
        bMsg.prchReqCratDt.setInputProtected(isInputProtected);
        bMsg.rqstRcvDt.setInputProtected(false);
        // QC#21169
        handler.setButtonEnabled(BTN_APPLY, true);
        // START 02/03/2023 T.Kuroda [QC#60966, ADD]
        bMsg.rqstShipDt.setInputProtected(false);
        handler.setButtonEnabled(BTN_RQSTSHIPDT_APPLY, true);
        // END   02/03/2023 T.Kuroda [QC#60966, ADD]
        bMsg.xxDtTm.setInputProtected(true);
        bMsg.xxTpCd_SL.setInputProtected(true);
        bMsg.prchReqSrcTpNm.setInputProtected(isInputProtected);
        bMsg.prchReqSrcTpCd.setInputProtected(isInputProtected);
        bMsg.trxRefNum.setInputProtected(isInputProtected);
        bMsg.poQlfyCd.setInputProtected(isInputProtected);
        //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        bMsg.mrpPlnNm.setInputProtected(isInputProtected);
        //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) END        
        bMsg.prchReqCratByNm.setInputProtected(isInputProtected);
        bMsg.prchReqRqstByPsnCd.setInputProtected(isInputProtected);
        bMsg.fullPsnNm.setInputProtected(isInputProtected);
        bMsg.prchGrpCd_SL.setInputProtected(isInputProtected);
        bMsg.prchReqCmntTxt.setInputProtected(false);
        bMsg.prntVndNm.setInputProtected(isInputProtected);
        // QC#53300 2019/10/04 Add Start
        bMsg.prntVndCd.setInputProtected(isInputProtected);
        // QC#53300 2019/10/04 Add End
        bMsg.vndCd.setInputProtected(isInputProtected);
        bMsg.locNm.setInputProtected(isInputProtected);
        bMsg.rtlWhNm.setInputProtected(isInputProtected);
        bMsg.rtlSwhNm.setInputProtected(isInputProtected);
        bMsg.xxLocNm.setInputProtected(isInputProtected);
        bMsg.destRtlWhCd.setInputProtected(isInputProtected);
        bMsg.destRtlSwhCd.setInputProtected(isInputProtected);
        bMsg.cpoOrdNum.setInputProtected(isInputProtected);
        bMsg.dsOrdPosnNum.setInputProtected(isInputProtected);
        bMsg.xxTotAmt.setInputProtected(isInputProtected);
        bMsg.ccyCd.setInputProtected(isInputProtected);
        bMsg.xxLocNm_HS.setInputProtected(isInputProtected);
        bMsg.shipToAddlLocNm_HS.setInputProtected(isInputProtected);
        bMsg.xxAllLineAddr_HS.setInputProtected(isInputProtected);
        bMsg.shipToPostCd_HS.setInputProtected(isInputProtected);
        bMsg.shipToCtyAddr_HS.setInputProtected(isInputProtected);
        bMsg.shipToCntyNm_HS.setInputProtected(isInputProtected);
        bMsg.shipToStCd_HS.setInputProtected(isInputProtected);
        bMsg.shipToProvNm_HS.setInputProtected(isInputProtected);
        bMsg.ctryNm_HS.setInputProtected(isInputProtected);
        bMsg.ctacPsnNm_HS.setInputProtected(isInputProtected);
        bMsg.locNm_HB.setInputProtected(isInputProtected);
        bMsg.addlLocNm_HB.setInputProtected(isInputProtected);
        bMsg.xxAllLineAddr_HB.setInputProtected(isInputProtected);
        bMsg.postCd_HB.setInputProtected(isInputProtected);
        bMsg.ctyAddr_HB.setInputProtected(isInputProtected);
        bMsg.cntyNm_HB.setInputProtected(isInputProtected);
        bMsg.shipToCntyNm_HB.setInputProtected(isInputProtected);
        bMsg.stCd_HB.setInputProtected(isInputProtected);
        bMsg.provNm_HB.setInputProtected(isInputProtected);
        bMsg.ctryNm_HB.setInputProtected(isInputProtected);
        bMsg.frtCondCd_SL.setInputProtected(isInputProtected);
        bMsg.shpgSvcLvlCd_SL.setInputProtected(isInputProtected);
        // QC#29155 Add.
        bMsg.carrCd_HF.setInputProtected(isInputProtected);
        bMsg.locNm_HF.setInputProtected(true);
        bMsg.carrAcctNum_HF.setInputProtected(isInputProtected);
        bMsg.prchReqRelErrMsgTxt_SL.setInputProtected(isInputProtected);
        bMsg.xxDtTm_HP.setInputProtected(isInputProtected);
        bMsg.rtlWhNm_HC.setInputProtected(isInputProtected);
        bMsg.xxAllLineAddr_HC.setInputProtected(isInputProtected);
        bMsg.ctyAddr_HC.setInputProtected(isInputProtected);
        bMsg.stCd_HC.setInputProtected(isInputProtected);
        bMsg.postCd_HC.setInputProtected(isInputProtected);
        bMsg.xxTotAmt.setAppFracDigit(IDX_2);
        //QC#26990 Add Start
        bMsg.shipToCtryCd_HS.setInputProtected(isInputProtected);
        bMsg.shipToCustCd.setInputProtected(isInputProtected);
        //QC#26990 Add End
        // QC#28709 Add.
        handler.setButtonEnabled("GetAddress", !isInputProtected);
        for (int i = 0; i < bMsg.A.getValidCount(); i++) {
            if (PO_MDSE_CMPSN_TP.CHILD.equals(bMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                controlItemsChild(handler, bMsg, glblCmpyCd, funcList, i);
            } else if ((ZYPCommonFunc.hasValue(bMsg.A.no(i).openStsFlg_D) && ZYPConstant.FLG_OFF_N.equals(bMsg.A.no(i).openStsFlg_D.getValue()))) {
                controlItemsCloseLine(handler, bMsg, glblCmpyCd, funcList, i);
            } else {
                bMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                bMsg.A.no(i).xxLineNum_A1.setInputProtected(false);
                bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
                ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).prchReqLineTpCd_HD, bMsg.A.no(i).prchReqLineTpCd_A1);
                bMsg.A.no(i).mdseCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).aslMdseCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqDsplUomCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prntVndNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).locNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).rqstRcvDt_A1.setInputProtected(false);
                // START 02/03/2023 T.Kuroda [QC#60966, ADD]
                bMsg.A.no(i).rqstShipDt_A1.setInputProtected(false);
                // END   02/03/2023 T.Kuroda [QC#60966, ADD]
                // QC#29729
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {
                    bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(false);
                } else {
                    bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
                }
                bMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(false);
                bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(false);
                bMsg.A.no(i).prchReqLineStsNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxUnitPrc_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaProdCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxLineNum_TR.setInputProtected(isInputProtected);
                bMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSchdRelDt_A1.setInputProtected(false);
                bMsg.A.no(i).poOrdNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSubmtTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(isInputProtected);
                if (ZYPCommonFunc.hasValue(bMsg.A.no(i).poSubmtTs_A1)) {
                    // IDX_8: Timestamp value substring yyyymmdd
                    // format
                    ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(i).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
                }
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
                bMsg.A.no(i).xxUnitPrc_A1.setAppFracDigit(IDX_2);
                // setIndispensable(bMsg, i);
                // QC#21209
                bMsg.A.no(i).spclInstnCmntTxt_A1.setInputProtected(false);
            }
        }
    }

    private static void controlItemsSearchOpenY(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, List<String> funcList, String varCharConstVal) {
        boolean isInputProtected = true;

        // header
        bMsg.prchReqTpCd_SL.setInputProtected(isInputProtected);
        bMsg.prchReqStsCd.setInputProtected(isInputProtected);
        bMsg.prchReqStsNm.setInputProtected(isInputProtected);
        bMsg.prchReqApvlStsCd.setInputProtected(isInputProtected);
        bMsg.prchReqApvlStsNm.setInputProtected(isInputProtected);
        bMsg.prchReqCratDt.setInputProtected(isInputProtected);
        bMsg.rqstRcvDt.setInputProtected(false);
        // QC#21169
        handler.setButtonEnabled(BTN_APPLY, true);
        // START 02/03/2023 T.Kuroda [QC#60966, ADD]
        bMsg.rqstShipDt.setInputProtected(false);
        handler.setButtonEnabled(BTN_RQSTSHIPDT_APPLY, true);
        // END   02/03/2023 T.Kuroda [QC#60966, ADD]
        bMsg.xxDtTm.setInputProtected(true);
        bMsg.xxTpCd_SL.setInputProtected(true);
        bMsg.prchReqSrcTpNm.setInputProtected(isInputProtected);
        bMsg.prchReqSrcTpCd.setInputProtected(isInputProtected);
        bMsg.trxRefNum.setInputProtected(isInputProtected);
        bMsg.poQlfyCd.setInputProtected(isInputProtected);
        //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) START
        bMsg.mrpPlnNm.setInputProtected(isInputProtected);
        //08/07/2017 CITS H.Naoi Add Sol#097(QC#18398) END
        bMsg.prchReqCratByNm.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A3.setInputProtected(false);
        bMsg.prchReqRqstByPsnCd.setInputProtected(false);
        bMsg.fullPsnNm.setInputProtected(false);
        bMsg.prchGrpCd_SL.setInputProtected(false);
        bMsg.prchReqCmntTxt.setInputProtected(false);
        // QC#53300 2019/10/11 Mod Start
//        bMsg.xxLinkAncr_A0.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A0.setInputProtected(false);
        // QC#53300 2019/10/11 Mod End
        bMsg.prntVndNm.setInputProtected(isInputProtected);
        // QC#53300 2019/10/11 Mod Start
//        bMsg.xxLinkAncr_A1.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A1.setInputProtected(false);
        // QC#53300 2019/10/11 Mod End
        // QC#53300 2019/10/04 Add Start
        bMsg.prntVndCd.setInputProtected(false);
//        bMsg.vndCd.setInputProtected(isInputProtected);
        bMsg.vndCd.setInputProtected(false);
        // QC#53300 2019/10/04 Add End
        bMsg.locNm.setInputProtected(isInputProtected);
        // QC#53300 2019/10/11 Mod Start
//        bMsg.xxLinkAncr_A2.setInputProtected(false);
        // START 2023/04/04 S.Dong [QC#61287,MOD]
//        bMsg.xxLinkAncr_A2.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A2.setInputProtected(false);
        // END 2023/04/04 S.Dong [QC#61287,MOD]
        // QC#53300 2019/10/11 Mod End
        //QC#26990 mod Start
        // START 2023/04/04 S.Dong [QC#61287,MOD]
//        bMsg.destRtlWhCd.setInputProtected(isInputProtected);
//        bMsg.destRtlSwhCd.setInputProtected(isInputProtected);
        bMsg.destRtlWhCd.setInputProtected(false);
        bMsg.destRtlSwhCd.setInputProtected(false);
        // END 2023/04/04 S.Dong [QC#61287,MOD]
//        bMsg.destRtlWhCd.setInputProtected(false);
//        bMsg.destRtlSwhCd.setInputProtected(false);
        //QC#26990 mod End
        bMsg.rtlWhNm.setInputProtected(isInputProtected);
        // QC#53300 2019/10/11 Mod Start
//        bMsg.xxLinkAncr_A4.setInputProtected(false);
        // START 2023/04/04 S.Dong [QC#61287,MOD]
//        bMsg.xxLinkAncr_A4.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A4.setInputProtected(false);
        // END 2023/04/04 S.Dong [QC#61287,MOD]
        // QC#53300 2019/10/11 Mod End
        bMsg.rtlSwhNm.setInputProtected(isInputProtected);
        bMsg.xxLocNm.setInputProtected(isInputProtected);
        bMsg.cpoOrdNum.setInputProtected(isInputProtected);
        bMsg.dsOrdPosnNum.setInputProtected(isInputProtected);
        // detail header
        bMsg.xxTotAmt.setInputProtected(isInputProtected);
        bMsg.ccyCd.setInputProtected(isInputProtected);
        // additional header
        // QC#28709 Update.
        bMsg.xxLinkAncr_AL.setInputProtected(false);
        bMsg.xxLocNm.setInputProtected(false);
        bMsg.xxLocNm_HS.setInputProtected(false);
        bMsg.shipToAddlLocNm_HS.setInputProtected(false);
        bMsg.xxAllLineAddr_HS.setInputProtected(false);
        bMsg.shipToPostCd_HS.setInputProtected(false);
        bMsg.shipToCtyAddr_HS.setInputProtected(false);
        bMsg.shipToCntyNm_HS.setInputProtected(false);
        bMsg.shipToStCd_HS.setInputProtected(false);
        bMsg.shipToProvNm_HS.setInputProtected(false);
        bMsg.ctryNm_HS.setInputProtected(false);
        // QC#28709 Update.
        bMsg.shipToCtryCd_HS.setInputProtected(false);
        handler.setButtonEnabled("GetAddress", true);
        //QC#26990 mod Start
//        bMsg.ctacPsnNm_HS.setInputProtected(isInputProtected);
        bMsg.ctacPsnNm_HS.setInputProtected(false);
        // START 2023/04/04 S.Dong [QC#61287,MOD]
//        bMsg.shipToCustCd.setInputProtected(isInputProtected);
        bMsg.shipToCustCd.setInputProtected(false);
        // END 2023/04/04 S.Dong [QC#61287,MOD]
        //QC#26990 mod End
        
        bMsg.locNm_HB.setInputProtected(isInputProtected);
        bMsg.addlLocNm_HB.setInputProtected(isInputProtected);
        bMsg.xxAllLineAddr_HB.setInputProtected(isInputProtected);
        bMsg.postCd_HB.setInputProtected(isInputProtected);
        bMsg.ctyAddr_HB.setInputProtected(isInputProtected);
        bMsg.cntyNm_HB.setInputProtected(isInputProtected);
        bMsg.shipToCntyNm_HB.setInputProtected(isInputProtected);
        bMsg.stCd_HB.setInputProtected(isInputProtected);
        bMsg.provNm_HB.setInputProtected(isInputProtected);
        bMsg.ctryNm_HB.setInputProtected(isInputProtected);
        // QC#28941 Update.
        bMsg.frtCondCd_SL.setInputProtected(false);
        bMsg.shpgSvcLvlCd_SL.setInputProtected(false);
        bMsg.xxLinkAncr_A5.setInputProtected(false);
        // START 2023/04/04 S.Dong [QC#61287,MOD]
//        bMsg.xxLinkAncr_A6.setInputProtected(isInputProtected);
        bMsg.xxLinkAncr_A6.setInputProtected(false);
        // END 2023/04/04 S.Dong [QC#61287,MOD]
        // QC#29155 Add.
        bMsg.carrCd_HF.setInputProtected(false);
        bMsg.locNm_HF.setInputProtected(true);
        bMsg.carrAcctNum_HF.setInputProtected(false);
        bMsg.prchReqRelErrMsgTxt_SL.setInputProtected(isInputProtected);
        bMsg.xxDtTm_HP.setInputProtected(isInputProtected);
        bMsg.rtlWhNm_HC.setInputProtected(true);
        bMsg.xxAllLineAddr_HC.setInputProtected(true);
        bMsg.ctyAddr_HC.setInputProtected(true);
        bMsg.stCd_HC.setInputProtected(true);
        bMsg.postCd_HC.setInputProtected(true);

        // switch protect by po qualifier
        if (varCharConstVal.equals(bMsg.poQlfyCd.getValue())) {
            bMsg.xxLinkAncr_A3.setInputProtected(isInputProtected);
            bMsg.prchReqRqstByPsnCd.setInputProtected(isInputProtected);
            bMsg.fullPsnNm.setInputProtected(isInputProtected);
            bMsg.prchGrpCd_SL.setInputProtected(isInputProtected);
            bMsg.prchReqCmntTxt.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A2.setInputProtected(isInputProtected);
            bMsg.destRtlWhCd.setInputProtected(isInputProtected);
            bMsg.xxLinkAncr_A4.setInputProtected(isInputProtected);
            bMsg.destRtlSwhCd.setInputProtected(isInputProtected);
        }

        bMsg.xxTotAmt.setAppFracDigit(IDX_2);
        // detail
        for (int i = 0; i < bMsg.A.getValidCount(); i++) {
            if (PO_MDSE_CMPSN_TP.CHILD.equals(bMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                controlItemsChild(handler, bMsg, glblCmpyCd, funcList, i);
            } else if ((ZYPCommonFunc.hasValue(bMsg.A.no(i).openStsFlg_D) && ZYPConstant.FLG_OFF_N.equals(bMsg.A.no(i).openStsFlg_D.getValue()))) {
                controlItemsCloseLine(handler, bMsg, glblCmpyCd, funcList, i);
            } else {
                if (ZYPCommonFunc.hasValue(bMsg.A.no(i).openStsFlg_D)) {
                    bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).mdseCd_A1.setInputProtected(isInputProtected);

                    if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                            || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {

                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(false);
                        // QC#28939 Update.
                        bMsg.A.no(i).mdseCd_A1.setIndispensable(false);
                        bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
                    } else {
                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
                        // QC#28939 Update.
                        bMsg.A.no(i).mdseCd_A1.setIndispensable(true);
                        bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
                    }
                } else {
                    bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(false);
                    bMsg.A.no(i).mdseCd_A1.setInputProtected(false);
                    
                    if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                            || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {

                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(false);
                        // QC#28939 Update.
                        bMsg.A.no(i).mdseCd_A1.setIndispensable(false);
                        bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
                    } else {
                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
                        // QC#28939 Update.
                        bMsg.A.no(i).mdseCd_A1.setIndispensable(true);
                        bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
                    }
                }
                bMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                bMsg.A.no(i).xxLineNum_A1.setInputProtected(false);
                ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).prchReqLineTpCd_HD, bMsg.A.no(i).prchReqLineTpCd_A1);
                bMsg.A.no(i).aslMdseCd_A1.setInputProtected(false);
                bMsg.A.no(i).prchReqDsplUomCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(false);
                bMsg.A.no(i).prntVndNm_A1.setInputProtected(false);
                bMsg.A.no(i).locNm_A1.setInputProtected(false);
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(false);
                bMsg.A.no(i).rqstRcvDt_A1.setInputProtected(false);
                // START 02/03/2023 T.Kuroda [QC#60966, ADD]
                bMsg.A.no(i).rqstShipDt_A1.setInputProtected(false);
                // END   02/03/2023 T.Kuroda [QC#60966, ADD]
                bMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(false);
                bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(false);
                bMsg.A.no(i).prchReqLineStsNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxUnitPrc_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaProdCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxLineNum_TR.setInputProtected(isInputProtected);
                bMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSchdRelDt_A1.setInputProtected(false);
                bMsg.A.no(i).poOrdNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSubmtTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(isInputProtected);
                // QC#21209
                bMsg.A.no(i).spclInstnCmntTxt_A1.setInputProtected(false);
                if (ZYPCommonFunc.hasValue(bMsg.A.no(i).poSubmtTs_A1)) {
                    // IDX_8: Timestamp value substring yyyymmdd
                    // format
                    ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(i).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
                }
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
                bMsg.A.no(i).xxUnitPrc_A1.setAppFracDigit(IDX_2);
                // setIndispensable(bMsg, i);
                // switch protect by po qualifier
                if (varCharConstVal.equals(bMsg.poQlfyCd.getValue())) {
                    bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);
                }
            }
        }
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     * @param glblCmpyCd String
     * @param funcList List<String>
     */
    public static void controlItemsDetailLine(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, List<String> funcList) {
        boolean isInputProtected = true;
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(CUST_DROP_SHIP_PO_QULF, glblCmpyCd);
        for (int i = 0; i < bMsg.A.getValidCount(); i++) {
            if (PO_MDSE_CMPSN_TP.CHILD.equals(bMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                controlItemsChild(handler, bMsg, glblCmpyCd, funcList, i);
            } else if ((ZYPCommonFunc.hasValue(bMsg.A.no(i).openStsFlg_D) && ZYPConstant.FLG_OFF_N.equals(bMsg.A.no(i).openStsFlg_D.getValue()))) {
                controlItemsCloseLine(handler, bMsg, glblCmpyCd, funcList, i);
            } else {
                if (ZYPCommonFunc.hasValue(bMsg.A.no(i).openStsFlg_D)) {
                    bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).mdseCd_A1.setInputProtected(isInputProtected);

                    if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                            || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {

                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(false);
                        // QC#28939 Update.
                        bMsg.A.no(i).mdseCd_A1.setIndispensable(false);
                        bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
                    } else {
                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
                        // QC#28939 Update.
                        bMsg.A.no(i).mdseCd_A1.setIndispensable(true);
                        bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
                    }

                } else {
                    bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(false);
                    bMsg.A.no(i).mdseCd_A1.setInputProtected(false);

                    if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                            || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {

                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(false);
                        // QC#28939 Update.
                        bMsg.A.no(i).mdseCd_A1.setIndispensable(false);
                        bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(false);
                    } else {
                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
                        // QC#28939 Update.
                        bMsg.A.no(i).mdseCd_A1.setIndispensable(true);
                        bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
                    }
                }
                bMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                bMsg.A.no(i).xxLineNum_A1.setInputProtected(false);
                ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).prchReqLineTpCd_HD, bMsg.A.no(i).prchReqLineTpCd_A1);
                bMsg.A.no(i).aslMdseCd_A1.setInputProtected(false);
                bMsg.A.no(i).prchReqDsplUomCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(false);
                bMsg.A.no(i).prntVndNm_A1.setInputProtected(false);
                bMsg.A.no(i).locNm_A1.setInputProtected(false);
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(false);
                bMsg.A.no(i).rqstRcvDt_A1.setInputProtected(false);
                // START 02/03/2023 T.Kuroda [QC#60966, ADD]
                bMsg.A.no(i).rqstShipDt_A1.setInputProtected(false);
                // END   02/03/2023 T.Kuroda [QC#60966, ADD]
                bMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(false);
                bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(false);
                bMsg.A.no(i).prchReqLineStsNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxUnitPrc_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaProdCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxLineNum_TR.setInputProtected(isInputProtected);
                bMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSchdRelDt_A1.setInputProtected(false);
                bMsg.A.no(i).poOrdNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSubmtTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(isInputProtected);
                // QC#21209
                bMsg.A.no(i).spclInstnCmntTxt_A1.setInputProtected(false);
                if (ZYPCommonFunc.hasValue(bMsg.A.no(i).poSubmtTs_A1)) {
                    // IDX_8: Timestamp value substring yyyymmdd
                    // format
                    ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(i).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
                }
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
                bMsg.A.no(i).xxUnitPrc_A1.setAppFracDigit(IDX_2);
                // setIndispensable(bMsg, i);
                // switch protect by po qualifier
                if (varCharConstVal.equals(bMsg.poQlfyCd.getValue())) {
                    bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);

                }
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, i, true);
                } else {
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, i, false);
                }

                handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, i, true);
                handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, i, true);
                handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, i, true);
            }
        }
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     * @param index int
     */
    public static void controlItemsDetailLineDef(EZDCommonHandler handler, NPAL1280BMsg bMsg, int index) {
        boolean isInputProtected = true;
        bMsg.A.no(index).xxLineNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqDsplUomCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineStsNm_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).coaMdseTpCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).coaProdCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).xxLineNum_TR.setInputProtected(isInputProtected);
        bMsg.A.no(index).poOrdNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).poOrdDtlLineNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).poSubmtTs_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqRelErrMsgTxt_A1.setInputProtected(isInputProtected);
        //bMsg.A.no(index).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
        if (PRCH_REQ_LINE_TP.GOODS.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue())) {
            bMsg.A.no(index).xxScrItem130Txt_A1.setInputProtected(true);
            // QC#28939 Update.
            bMsg.A.no(index).mdseCd_A1.setIndispensable(true);
            bMsg.A.no(index).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
        } else {
            bMsg.A.no(index).xxScrItem130Txt_A1.setInputProtected(false);
            //QC#28939 Update.
            bMsg.A.no(index).mdseCd_A1.setIndispensable(false);
            bMsg.A.no(index).mdseDescShortTxt_A1.setInputProtected(false);
        }
        isInputProtected = false;
        bMsg.A.no(index).xxChkBox_A1.setInputProtected(false);
        bMsg.A.no(index).aslMdseCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqDispQty_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prntVndNm_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).locNm_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).entDealNetUnitPrcAmt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).xxUnitPrc_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).rqstRcvDt_A1.setInputProtected(isInputProtected);
        // START 02/03/2023 T.Kuroda [QC#60966, ADD]
        bMsg.A.no(index).rqstShipDt_A1.setInputProtected(isInputProtected);
        // END   02/03/2023 T.Kuroda [QC#60966, ADD]
        bMsg.A.no(index).poSchdRelDt_A1.setInputProtected(isInputProtected);
        if (ZYPCommonFunc.hasValue(bMsg.A.no(index).poSubmtTs_A1)) {
            // IDX_8: Timestamp value substring yyyymmdd format
            ZYPEZDItemValueSetter.setValue(bMsg.A.no(index).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(index).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
        }
        bMsg.A.no(index).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
        bMsg.A.no(index).xxUnitPrc_A1.setAppFracDigit(IDX_2);
        // setIndispensable(bMsg, index);
        // QC#21209
        bMsg.A.no(index).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     * @param glblCmpyCd String
     * @param funcList List<String>
     * @param index int
     */
    public static void controlItemsChild(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, List<String> funcList, int index) {
        boolean isInputProtected = true;
        bMsg.A.no(index).xxChkBox_A1.setInputProtected(false);
        bMsg.A.no(index).xxLineNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineTpCd_PD.setInputProtected(isInputProtected);
        bMsg.A.no(index).mdseCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).aslMdseCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqDsplUomCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqDispQty_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prntVndNm_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).locNm_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).entDealNetUnitPrcAmt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).rqstRcvDt_A1.setInputProtected(isInputProtected);
        // START 02/03/2023 T.Kuroda [QC#60966, ADD]
        bMsg.A.no(index).rqstShipDt_A1.setInputProtected(isInputProtected);
        // END   02/03/2023 T.Kuroda [QC#60966, ADD]
        // QC#29729
        if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue()) //
                || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue())) {

            bMsg.A.no(index).xxScrItem130Txt_A1.setInputProtected(false);
        } else {
            bMsg.A.no(index).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
        }
        bMsg.A.no(index).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineStsNm_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).xxUnitPrc_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).coaMdseTpCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).coaProdCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).xxLineNum_TR.setInputProtected(isInputProtected);
        bMsg.A.no(index).poOrdDtlLineNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).poSchdRelDt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).poOrdNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).poSubmtTs_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqRelErrMsgTxt_A1.setInputProtected(isInputProtected);
        if (ZYPCommonFunc.hasValue(bMsg.A.no(index).poSubmtTs_A1)) {
            // IDX_8: Timestamp value substring yyyymmdd format
            ZYPEZDItemValueSetter.setValue(bMsg.A.no(index).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(index).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
        }
        bMsg.A.no(index).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
        bMsg.A.no(index).xxUnitPrc_A1.setAppFracDigit(IDX_2);
        handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
        handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, false);
        handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, false);
        handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, false);
        // setIndispensable(bMsg, index);
        // QC#21209
        bMsg.A.no(index).spclInstnCmntTxt_A1.setInputProtected(isInputProtected);
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     * @param glblCmpyCd String
     * @param funcList List<String>
     * @param index int
     */
    public static void controlItemsCloseLine(EZDCommonHandler handler, NPAL1280BMsg bMsg, String glblCmpyCd, List<String> funcList, int index) {
        boolean isInputProtected = true;
        bMsg.A.no(index).xxChkBox_A1.setInputProtected(false);
        bMsg.A.no(index).xxLineNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineTpCd_PD.setInputProtected(isInputProtected);
        bMsg.A.no(index).mdseCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).aslMdseCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqDsplUomCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqDispQty_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prntVndNm_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).locNm_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).entDealNetUnitPrcAmt_A1.setInputProtected(isInputProtected);
        if (PRCH_REQ_LINE_STS.CANCELLED.equals(bMsg.A.no(index).prchReqLineStsCd_HD.getValue())) {
            bMsg.A.no(index).rqstRcvDt_A1.setInputProtected(isInputProtected);
            // START 02/03/2023 T.Kuroda [QC#60966, ADD]
            bMsg.A.no(index).rqstShipDt_A1.setInputProtected(isInputProtected);
            // END   02/03/2023 T.Kuroda [QC#60966, ADD]
        } else {
            bMsg.A.no(index).rqstRcvDt_A1.setInputProtected(false);
            // START 02/03/2023 T.Kuroda [QC#60966, ADD]
            bMsg.A.no(index).rqstShipDt_A1.setInputProtected(false);
            // END   02/03/2023 T.Kuroda [QC#60966, ADD]
        }
        bMsg.A.no(index).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqLineStsNm_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).xxUnitPrc_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).coaMdseTpCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).coaProdCd_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).xxLineNum_TR.setInputProtected(isInputProtected);
        bMsg.A.no(index).poOrdDtlLineNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).poSchdRelDt_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).poOrdNum_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).poSubmtTs_A1.setInputProtected(isInputProtected);
        bMsg.A.no(index).prchReqRelErrMsgTxt_A1.setInputProtected(isInputProtected);
        if (ZYPCommonFunc.hasValue(bMsg.A.no(index).poSubmtTs_A1)) {
            // IDX_8: Timestamp value substring yyyymmdd format
            ZYPEZDItemValueSetter.setValue(bMsg.A.no(index).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(index).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
        }
        bMsg.A.no(index).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
        bMsg.A.no(index).xxUnitPrc_A1.setAppFracDigit(IDX_2);
        handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
        handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
        handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, false);
        handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, false);
        handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, false);

        // setIndispensable(bMsg, index);
        // QC#21209
        bMsg.A.no(index).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
    }

    /**
     * @param handler EZDCommonHandler
     * @param bMsg NPAL1280BMsg
     */
    public static void controlItemsPagenate(EZDCommonHandler handler, NPAL1280BMsg bMsg) {
        boolean isInputProtected = false;
        if (isUpdatable(bMsg)) {
            for (int i = 0; i < bMsg.A.getValidCount(); i++) {
                if (PO_MDSE_CMPSN_TP.CHILD.equals(bMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                    controlItemsChild(handler, bMsg, null, null, i);
                } else if ((ZYPCommonFunc.hasValue(bMsg.A.no(i).openStsFlg_D) && ZYPConstant.FLG_OFF_N.equals(bMsg.A.no(i).openStsFlg_D.getValue()))) {
                    controlItemsCloseLine(handler, bMsg, null, null, i);
                } else {
                    bMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                    bMsg.A.no(i).xxLineNum_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).mdseCd_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).aslMdseCd_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqDsplUomCd_A1.setInputProtected(true);
                    bMsg.A.no(i).prntVndNm_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).locNm_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).rqstRcvDt_A1.setInputProtected(isInputProtected);
                    // START 02/03/2023 T.Kuroda [QC#60966, ADD]
                    bMsg.A.no(i).rqstShipDt_A1.setInputProtected(isInputProtected);
                    // END   02/03/2023 T.Kuroda [QC#60966, ADD]
                    bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(true);
                    bMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).prchReqLineStsNm_A1.setInputProtected(true);
                    bMsg.A.no(i).xxUnitPrc_A1.setInputProtected(true);
                    bMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(true);
                    bMsg.A.no(i).coaProdCd_A1.setInputProtected(true);
                    bMsg.A.no(i).xxLineNum_TR.setInputProtected(true);
                    bMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(true);
                    bMsg.A.no(i).poSchdRelDt_A1.setInputProtected(isInputProtected);
                    bMsg.A.no(i).poOrdNum_A1.setInputProtected(true);
                    bMsg.A.no(i).prchReqRelDtTmTs_A1.setInputProtected(true);
                    bMsg.A.no(i).poSubmtTs_A1.setInputProtected(true);
                    bMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(true);
                    if (ZYPCommonFunc.hasValue(bMsg.A.no(i).poSubmtTs_A1)) {
                        // IDX_8: Timestamp value substring yyyymmdd
                        // format
                        ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(i).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
                    }
                    bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
                    bMsg.A.no(i).xxUnitPrc_A1.setAppFracDigit(IDX_2);
                    // setIndispensable(bMsg, i);
                    // QC#21209
                    bMsg.A.no(i).spclInstnCmntTxt_A1.setInputProtected(isInputProtected);
                    // QC#27770 MOD START
                    if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                            || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {
                        bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(false);
                    }
                    // QC#27770 MOD END
                }
            }
        } else {
            isInputProtected = true;
            for (int i = 0; i < bMsg.A.getValidCount(); i++) {
                bMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                bMsg.A.no(i).xxLineNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqLineTpCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).mdseCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).aslMdseCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqDsplUomCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqDispQty_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prntVndNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).locNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).rqstRcvDt_A1.setInputProtected(isInputProtected);
                // START 02/03/2023 T.Kuroda [QC#60966, ADD]
                bMsg.A.no(i).rqstShipDt_A1.setInputProtected(isInputProtected);
                // END   02/03/2023 T.Kuroda [QC#60966, ADD]
                bMsg.A.no(i).xxScrItem130Txt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqLineCmntTxt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).relRqstToPoOrdNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqLineStsNm_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxUnitPrc_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).coaProdCd_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).xxLineNum_TR.setInputProtected(isInputProtected);
                bMsg.A.no(i).poOrdDtlLineNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSchdRelDt_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poOrdNum_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelDtTmTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).poSubmtTs_A1.setInputProtected(isInputProtected);
                bMsg.A.no(i).prchReqRelErrMsgTxt_A1.setInputProtected(isInputProtected);
                if (ZYPCommonFunc.hasValue(bMsg.A.no(i).poSubmtTs_A1)) {
                    // IDX_8: Timestamp value substring yyyymmdd
                    // format
                    ZYPEZDItemValueSetter.setValue(bMsg.A.no(i).xxDtTm_A1, ZYPDateUtil.formatEzd8ToDisp(bMsg.A.no(i).poSubmtTs_A1.getValue().substring(0, IDX_8), true));
                }
                bMsg.A.no(i).entDealNetUnitPrcAmt_A1.setAppFracDigit(IDX_2);
                bMsg.A.no(i).xxUnitPrc_A1.setAppFracDigit(IDX_2);
                // setIndispensable(bMsg, i);
                // QC#21209
                bMsg.A.no(i).spclInstnCmntTxt_A1.setInputProtected(isInputProtected);
            }
        }

        controlButtonsInit(handler, bMsg);

    }

    private static void controlButtonsInit(EZDCommonHandler handler, NPAL1280BMsg bMsg) {
        if (isUpdatable(bMsg)) {
            handler.setButtonEnabled(BTN_SEARCH_NAME, true);
            handler.setButtonEnabled(BTN_CMN_BTN_1_ID, true);
            handler.setButtonEnabled(BTN_CMN_BTN_2_ID, false);
            handler.setButtonEnabled(BTN_CMN_BTN_11_ID, true);
            handler.setButtonEnabled(BTN_COPY_NAME, true);
            handler.setButtonEnabled(BTN_ATTACHMENTS_NAME, false);

            handler.setButtonEnabled(BTN_HEADER_CANCEL_NAME, false);
            handler.setButtonEnabled(BTN_ADD_NEW_LINE_NAME, true);
            handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, true);
            // QC#53300 2019/10/04 Add Start
            handler.setButtonEnabled(BTN_GET_SUPPLIER_PREFIX, true);
            handler.setButtonEnabled(BTN_GET_SUPPLIER_SITE_PREFIX, true);
            handler.setButtonEnabled(BTN_GET_SHIP_TO_INFO_PREFIX, true);
            handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_PREFIX, true);
            // QC#53300 2019/10/04 Add End
            for (int index = 0; index < bMsg.A.getValidCount(); index++) {
                if (ZYPCommonFunc.hasValue(bMsg.A.no(index).poMdseCmpsnTpCd_A1) && PO_MDSE_CMPSN_TP.CHILD.equals(bMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue())) {
                    // QC#29729
                    if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue()) //
                            || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue())) {
                        handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, true);
                    } else {
                        handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
                    }
                    handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
                    handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, false);
                    handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, false);
                    handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, false);
                } else {
                    // QC#29729
                    if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue()) //
                            || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue())) {
                        handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, true);
                    } else {
                        handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
                    }
                    handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, true);
                    handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, true);
                    handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, true);
                    handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, true);
                }
            }
        } else {
            handler.setButtonEnabled(BTN_SEARCH_NAME, true);
            handler.setButtonEnabled(BTN_COPY_NAME, false);
            handler.setButtonEnabled(BTN_ATTACHMENTS_NAME, false);
            handler.setButtonEnabled(BTN_HEADER_CANCEL_NAME, false);
            handler.setButtonEnabled(BTN_ADD_NEW_LINE_NAME, false);
            handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, false);
            for (int index = 0; index < bMsg.A.getValidCount(); index++) {
                // QC#29729
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue())) {
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, true);
                } else {
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);   
                }
                handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, false);
            }
            handler.setButtonEnabled(BTN_CMN_BTN_1_ID, false);
            handler.setButtonEnabled(BTN_CMN_BTN_2_ID, false);
            handler.setButtonEnabled(BTN_CMN_BTN_3_ID, false);
        }
    }

    private static void controlButtonsSearchNoOpen(EZDCommonHandler handler, NPAL1280BMsg bMsg, String varCharConstVal, List<String> funcList) {

        // check Role
        boolean inquiry = chkInquiryRole(funcList);

        handler.setButtonEnabled(BTN_SEARCH_NAME, true);
        // switch protect by po qualifier
        if (varCharConstVal.equals(bMsg.poQlfyCd.getValue())) {
            handler.setButtonEnabled(BTN_COPY_NAME, false);
        } else {
            handler.setButtonEnabled(BTN_COPY_NAME, true);
        }
        handler.setButtonEnabled(BTN_ATTACHMENTS_NAME, true);
        handler.setButtonEnabled(BTN_HEADER_CANCEL_NAME, false);
        handler.setButtonEnabled(BTN_ADD_NEW_LINE_NAME, false);
        // START QC#21006 MOD.
        if (PRCH_REQ_TP.ITT_OUTBOUND.equals(bMsg.prchReqTpCd_SL.getValue())) {
        	handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, false);
        	handler.setButtonEnabled(BTN_AUTO_CREATE_PO_NAME, false);
        } else {
        	handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, true);
        }
        // ENS QC#21006 MOD.

        for (int index = 0; index < bMsg.A.getValidCount(); index++) {
            handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
            handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
            handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, false);
            handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, false);
            handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, false);
        }
        handler.setButtonEnabled(BTN_CMN_BTN_1_ID, false);
        handler.setButtonEnabled(BTN_CMN_BTN_2_ID, false);
        handler.setButtonEnabled(BTN_CMN_BTN_11_ID, false);
        // QC#53300 2019/10/04 Add Start
        handler.setButtonEnabled(BTN_GET_SUPPLIER_PREFIX, false);
        handler.setButtonEnabled(BTN_GET_SUPPLIER_SITE_PREFIX, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_INFO_PREFIX, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_PREFIX, false);
        // QC#53300 2019/10/04 Add End
        if (!inquiry) {
            handler.setButtonEnabled(BTN_HEADER_CANCEL_NAME, false);
            handler.setButtonEnabled(BTN_COPY_NAME, false);
            handler.setButtonEnabled(BTN_ATTACHMENTS_NAME, false);
            handler.setButtonEnabled(BTN_ADD_NEW_LINE_NAME, false);
            handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, false);
            handler.setButtonEnabled(BTN_AUTO_CREATE_PO_NAME, false);
        }
    }

    private static void controlButtonsSearchOpenN(EZDCommonHandler handler, NPAL1280BMsg bMsg, String varCharConstVal, List<String> funcList) {

        // check Role
        boolean inquiry = chkInquiryRole(funcList);

        handler.setButtonEnabled(BTN_SEARCH_NAME, true);
        // START QC#21006 MOD.
        if (PRCH_REQ_TP.ITT_OUTBOUND.equals(bMsg.prchReqTpCd_SL.getValue())) {
        	handler.setButtonEnabled(BTN_HEADER_CANCEL_NAME, false);
        	handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, false);
        	handler.setButtonEnabled(BTN_AUTO_CREATE_PO_NAME, false);
        } else {
        	handler.setButtonEnabled(BTN_HEADER_CANCEL_NAME, true);
        	handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, true);
        }
        // END QC#21006 MOD.
        // switch protect by po qualifier
        if (varCharConstVal.equals(bMsg.poQlfyCd.getValue())) {
            handler.setButtonEnabled(BTN_COPY_NAME, false);
        } else {
            handler.setButtonEnabled(BTN_COPY_NAME, true);
        }
        handler.setButtonEnabled(BTN_ATTACHMENTS_NAME, true);
        handler.setButtonEnabled(BTN_ADD_NEW_LINE_NAME, false);
        for (int index = 0; index < bMsg.A.getValidCount(); index++) {
            // QC#29729
            if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue()) //
                    || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue())) {
                handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, true);
            } else {
                handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
            }
            handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
            handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, false);
            handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, false);
            handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, false);
        }
        handler.setButtonEnabled(BTN_OPENWND_APPROVALHIST_NAME, true);
        handler.setButtonEnabled(BTN_OPENWND_TEXT_NAME, true);
        handler.setButtonEnabled(BTN_CMN_BTN_1_ID, false);
        // QC#QC#20439
        if ((REQUESTER_SYSTEM.equals(bMsg.fullPsnNm.getValue()))
        		&& PRCH_REQ_APVL_STS.AWAITING_APPROVAL.equals(bMsg.prchReqApvlStsCd.getValue())) {
        	handler.setButtonEnabled(BTN_CMN_BTN_2_ID, false);
        // START QC#21006 MOD.
        } else if (PRCH_REQ_TP.ITT_OUTBOUND.equals(bMsg.prchReqTpCd_SL.getValue())) {
        	handler.setButtonEnabled(BTN_CMN_BTN_2_ID, false);
        // END QC#21006 MOD.
        } else {
        	handler.setButtonEnabled(BTN_CMN_BTN_2_ID, true);
        }

        handler.setButtonEnabled(BTN_CMN_BTN_11_ID, false);

        // QC#53300 2019/10/04 Add Start
        handler.setButtonEnabled(BTN_GET_SUPPLIER_PREFIX, false);
        handler.setButtonEnabled(BTN_GET_SUPPLIER_SITE_PREFIX, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_INFO_PREFIX, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_PREFIX, false);
        // QC#53300 2019/10/04 Add End


        if (!inquiry) {
            handler.setButtonEnabled(BTN_HEADER_CANCEL_NAME, false);
            handler.setButtonEnabled(BTN_COPY_NAME, false);
            handler.setButtonEnabled(BTN_ATTACHMENTS_NAME, false);
            handler.setButtonEnabled(BTN_ADD_NEW_LINE_NAME, false);
            handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, false);
            handler.setButtonEnabled(BTN_AUTO_CREATE_PO_NAME, false);
            handler.setButtonEnabled(BTN_CMN_BTN_1_ID, false);
            handler.setButtonEnabled(BTN_CMN_BTN_2_ID, false);
            handler.setButtonEnabled(BTN_CMN_BTN_11_ID, false);
        }
    }

    private static void controlButtonsSearchOpenY(EZDCommonHandler handler, NPAL1280BMsg bMsg, String varCharConstVal, List<String> funcList) {

        // check Role
        boolean inquiry = chkInquiryRole(funcList);

        handler.setButtonEnabled(BTN_SEARCH_NAME, true);
        handler.setButtonEnabled(BTN_HEADER_CANCEL_NAME, true);
        handler.setButtonEnabled(BTN_COPY_NAME, true);
        handler.setButtonEnabled(BTN_ATTACHMENTS_NAME, true);
        handler.setButtonEnabled(BTN_CMN_BTN_2_ID, true);
        handler.setButtonEnabled(BTN_ADD_NEW_LINE_NAME, true);
        handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, true);
        for (int index = 0; index < bMsg.A.getValidCount(); index++) {
            if (PO_MDSE_CMPSN_TP.CHILD.equals(bMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue())) {
                // QC#29729
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue())) {
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, true);
                } else {
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
                }
                handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, false);
            } else if ((ZYPCommonFunc.hasValue(bMsg.A.no(index).openStsFlg_D) && ZYPConstant.FLG_OFF_N.equals(bMsg.A.no(index).openStsFlg_D.getValue()))) {
                handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, false);
            } else if ((!ZYPCommonFunc.hasValue(bMsg.A.no(index).openStsFlg_D))) {
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue())) {
                    handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, true);
                } else {
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
                }
                handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, true);
                handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, true);
                handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, true);
                handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, true);
            } else {
                handler.setButtonEnabled(BTN_ITEM_ID_PREFIX, index, false);
                handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, true);
                handler.setButtonEnabled(BTN_SUPPLIER1_ID_PREFIX, index, true);
                handler.setButtonEnabled(BTN_SUPPLIER2_ID_PREFIX, index, true);
                handler.setButtonEnabled(BTN_GET_MDSE_INFO_ID_PREFIX, index, false);
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(bMsg.A.no(index).prchReqLineTpCd_A1.getValue())) {
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, true);
                } else {
                    handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
                }
            }
            // switch protect by po qualifier
            if (varCharConstVal.equals(bMsg.poQlfyCd.getValue())) {
                handler.setButtonEnabled(BTN_ACCOUNT_ID_PREFIX, index, false);
            }
        }
        handler.setButtonEnabled(BTN_OPENWND_APPROVALHIST_NAME, true);
        handler.setButtonEnabled(BTN_OPENWND_TEXT_NAME, true);
        handler.setButtonEnabled(BTN_CMN_BTN_1_ID, true);
        handler.setButtonEnabled(BTN_CMN_BTN_11_ID, true);
        // QC#53300 2019/10/04 Add Start
        handler.setButtonEnabled(BTN_GET_SUPPLIER_PREFIX, true);
        handler.setButtonEnabled(BTN_GET_SUPPLIER_SITE_PREFIX, true);
        // START 2023/04/04 S.Dong [QC#61287,MOD]
//        handler.setButtonEnabled(BTN_GET_SHIP_TO_INFO_PREFIX, false);
//        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_PREFIX, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_INFO_PREFIX, true);
        handler.setButtonEnabled(BTN_GET_SHIP_TO_CUST_PREFIX, true);
        // END 2023/04/04 S.Dong [QC#61287,MOD]
        // QC#53300 2019/10/04 Add End


        // switch protect by po qualifier
        if (varCharConstVal.equals(bMsg.poQlfyCd.getValue())) {
            handler.setButtonEnabled(BTN_COPY_NAME, false);
            handler.setButtonEnabled(BTN_ADD_NEW_LINE_NAME, false);
            handler.setButtonEnabled(BTN_CMN_BTN_1_ID, false);
            handler.setButtonEnabled(BTN_CMN_BTN_3_ID, false);
        }
        if (!inquiry) {
            handler.setButtonEnabled(BTN_HEADER_CANCEL_NAME, false);
            handler.setButtonEnabled(BTN_COPY_NAME, false);
            handler.setButtonEnabled(BTN_ATTACHMENTS_NAME, false);
            handler.setButtonEnabled(BTN_ADD_NEW_LINE_NAME, false);
            handler.setButtonEnabled(BTN_LINE_CANCEL_NAME, false);
            handler.setButtonEnabled(BTN_AUTO_CREATE_PO_NAME, false);
            handler.setButtonEnabled(BTN_CMN_BTN_1_ID, false);
            handler.setButtonEnabled(BTN_CMN_BTN_2_ID, false);
            handler.setButtonEnabled(BTN_CMN_BTN_11_ID, false);
        }
    }

    private static boolean isUpdatable(NPAL1280BMsg bMsg) {
        return hasUpdateFunction();
    }

    private static boolean hasUpdateFunction() {
        List<String> functionList = getFunctionList();
        return functionList.contains(FUNCTION_REFERENCE);
    }

    private static List<String> getFunctionList() {
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        List<String> functionList = profileService.getAuthorizedFunctionCodeListForBizAppId(BUSINESS_APPL_ID);
        return functionList;
    }

    /**
     * @param scrnMsg NPAL1280BMsg
     * @param glblCmpyCd String
     */
    public static void checkItem(NPAL1280BMsg scrnMsg, String glblCmpyCd) {
        // Input Attribute Check Area
        scrnMsg.addCheckItem(scrnMsg.fullPsnNm);
        scrnMsg.addCheckItem(scrnMsg.prchReqCmntTxt);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm);
        scrnMsg.addCheckItem(scrnMsg.locNm);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.carrAcctNum_HF);
        // QC#29155 Update.
        scrnMsg.addCheckItem(scrnMsg.carrCd_HF);

        if (!(ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SL))) {
            scrnMsg.prchReqTpCd_SL.setErrorInfo(1, ZZM9000E, new String[] {REQ_TYPE });
            scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SL);
        }
        if (!(ZYPCommonFunc.hasValue(scrnMsg.prchGrpCd_SL))) {
            scrnMsg.prchGrpCd_SL.setErrorInfo(1, ZZM9000E, new String[] {BUSINESS_UNIT });
            scrnMsg.addCheckItem(scrnMsg.prchGrpCd_SL);
        }
        if (!(ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd))) {
            scrnMsg.destRtlWhCd.setErrorInfo(1, ZZM9000E, new String[] {DEST_WH_CD });
            scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
        }

        if (!PRCH_REQ_STS.CLOSED.equals(scrnMsg.prchReqStsCd.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.rqstRcvDt);
            // START 02/03/2023 T.Kuroda [QC#60966, ADD]
            scrnMsg.addCheckItem(scrnMsg.rqstShipDt);
            // END   02/03/2023 T.Kuroda [QC#60966, ADD]
            scrnMsg.addCheckItem(scrnMsg.xxDtTm);

            // START 2019/03/20 T.Ogura [QC#30769,DEL]
//            if (ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt)) {
//                if (!(ZYPDateUtil.isBusinessDay(glblCmpyCd, scrnMsg.rqstRcvDt.getValue()))) {
//                    scrnMsg.rqstRcvDt.setErrorInfo(1, NPAM0094E, new String[] {DATE_NEEDED });
//                    scrnMsg.addCheckItem(scrnMsg.rqstRcvDt);
//                }
//            }
            // END   2019/03/20 T.Ogura [QC#30769,DEL]
            // QC#51575 START
//            if (ZYPCommonFunc.hasValue(scrnMsg.xxDtTm)) {
//                if (!(ZYPCommonFunc.isNumeric(scrnMsg.xxDtTm.getValue().replace(":", "")))) {
//                    scrnMsg.xxDtTm.setErrorInfo(1, NPAM1515E, new String[] {DATE_NEEDED });
//                    scrnMsg.addCheckItem(scrnMsg.xxDtTm);
//                } else {
//                    if (isTime(scrnMsg.xxDtTm)) {
//                        String sTime = getTm(scrnMsg.xxDtTm.getValue());
//                        int time = Integer.parseInt(sTime);
//                        // TIME_CHECK_VAL:1200 More than 1200 error
//                        if (TIME_CHECK_VAL < time) {
//                            scrnMsg.xxDtTm.setErrorInfo(1, NPAM1515E, new String[] {DATE_NEEDED });
//                            scrnMsg.setMessageInfo(NPAM1515E);
//                        }
//                        if (!(ZYPCommonFunc.hasValue(scrnMsg.xxTpCd_SL.getValue()))) {
//                            scrnMsg.xxDtTm.setErrorInfo(1, NLZM2274E, new String[] {DATE_NEEDED });
//                            scrnMsg.setMessageInfo(NLZM2274E);
//                        }
//                    } else {
//                        scrnMsg.addCheckItem(scrnMsg.xxDtTm);
//                    }
//                }
//            }
            // QC#51575 END
            //QC#28709 Add
            if (ZYPCommonFunc.hasValue(scrnMsg.xxAllLineAddr_HS) //
                    || ZYPCommonFunc.hasValue(scrnMsg.shipToPostCd_HS) //
                    || ZYPCommonFunc.hasValue(scrnMsg.shipToCtyAddr_HS) //
                    || ZYPCommonFunc.hasValue(scrnMsg.shipToCtryCd_HS)) {

                // START 2019/12/03 T.Ogura [QC#54814,DEL]
//                if (!ZYPCommonFunc.hasValue(scrnMsg.xxLocNm)) {
//                    scrnMsg.xxLocNm.setErrorInfo(1, ZZM9000E, new String[] {SHIP_TO_NAME });
//                }
                // END   2019/12/03 T.Ogura [QC#54814,DEL]
                if (!ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd)//
                        && ZYPCommonFunc.hasValue(scrnMsg.xxLocNm)) {
                    scrnMsg.shipToCustCd.setErrorInfo(1, ZZM9000E, new String[] {SHIP_TO_CUST_CODE });
                }

                scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
//                scrnMsg.addCheckItem(scrnMsg.xxLocNm);    // 2019/12/03 T.Ogura [QC#54814,DEL]
                scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_HS);
                scrnMsg.addCheckItem(scrnMsg.shipToPostCd_HS);
                scrnMsg.addCheckItem(scrnMsg.shipToCtyAddr_HS);
                scrnMsg.addCheckItem(scrnMsg.shipToCtryCd_HS);

                if(scrnMsg.xxAllLineAddr_HS.isError()//
                        || scrnMsg.shipToPostCd_HS.isError()//
                        || scrnMsg.shipToCtyAddr_HS.isError()//
                        || scrnMsg.shipToCtryCd_HS.isError()){
                    scrnMsg.xxDplyTab.setValue(TAB_HEADER);
                }
            }
        }

        //QC#14858 MOD START
        // Delete multiple Line Type Check.
        //String prchReqLineTypePre = "";
        //QC#14858 MOD END
        // QC#21170 Start
        NPAL1280CommonLogic.setHeaderRqstRcvDt_SmsgMaxVal(scrnMsg, glblCmpyCd);
        // QC#21170 End
        // START 02/03/2023 T.Kuroda [QC#60966, ADD]
        NPAL1280CommonLogic.setHeaderRqstShipDt_SmsgMaxVal(scrnMsg, glblCmpyCd);
        // END   02/03/2023 T.Kuroda [QC#60966, ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).aslMdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqDispQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prntVndNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).locNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqLineCmntTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).relRqstToPoOrdNum_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poSchdRelDt_A1);
            // QC#21209
            scrnMsg.addCheckItem(scrnMsg.A.no(i).spclInstnCmntTxt_A1);

            String prchReqLineType = scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue();
            if (!(ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).prntCmpySetMdseFlg_A1.getValue()))) {
                // QC#28939 Update. Expense Text Item check.
                if (PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue()) //
                        || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1) //
                            && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseDescShortTxt_A1)) {
                        scrnMsg.A.no(i).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Item Number or Item Description" });
                        scrnMsg.A.no(i).mdseDescShortTxt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Item Number or Item Description" });
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseDescShortTxt_A1);
                        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
                    }
                } else {
                    if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1))) {
                        scrnMsg.A.no(i).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {MDSE_CD_D });
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
                        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
                    }
                }

                if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prchReqLineTpCd_A1))) {
                    scrnMsg.A.no(i).prchReqLineTpCd_A1.setErrorInfo(1, ZZM9000E, new String[] {LINE_TYPE_D });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqLineTpCd_A1);
                    scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
                }
                if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prchReqDispQty_A1))) {
                    scrnMsg.A.no(i).prchReqDispQty_A1.setErrorInfo(1, ZZM9000E, new String[] {ORDER_QTY_D });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqDispQty_A1);
                    scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
                }
            }
            if (PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(i).prchReqLineTpCd_A1.getValue())) {
                if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxScrItem130Txt_A1))) {
                    scrnMsg.A.no(i).xxScrItem130Txt_A1.setErrorInfo(1, NPAM1489E, new String[] {CHARGE_ACCOUNT_D });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_A1);
                    scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
                }
            }
            //QC#14858 MOD START
//            if (ZYPCommonFunc.hasValue(prchReqLineTypePre) && !prchReqLineTypePre.equals(prchReqLineType)) {
//                scrnMsg.A.no(i).prchReqLineTpCd_A1.setErrorInfo(1, NPAM1490E, new String[] {LINE_TYPE_D });
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqLineTpCd_A1);
//                scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
//            }
            //QC#14858 MOD END
            if (0 >= scrnMsg.A.no(i).prchReqDispQty_A1.getValueInt()) {
                scrnMsg.A.no(i).prchReqDispQty_A1.setErrorInfo(1, NPAM0046E, new String[] {ORDER_QTY_D });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqDispQty_A1);
                scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
            }
            if (0 > scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.getValueInt()) {
                scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setErrorInfo(1, NPAM0046E, new String[] {UNIT_PRICE_D });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1);
                scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
            }
            //QC#14858 MOD START
            //prchReqLineTypePre = prchReqLineType;
            //QC#14858 MOD END
         // QC#21170 Start
//            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rqstRcvDt_A1)) {
//                //ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).rqstRcvDt_A1, scrnMsg.rqstRcvDt);
//            }
//            if (!PRCH_REQ_STS.CLOSED.equals(scrnMsg.prchReqStsCd.getValue())
//                    && !PRCH_REQ_LINE_STS.CANCELLED.equals(scrnMsg.A.no(i).prchReqLineStsCd_HD.getValue())
//                    && !PO_MDSE_CMPSN_TP.CHILD.equals(scrnMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
//                scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDt_A1);
//
//                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rqstRcvDt_A1)) {
//                    String rqstRcvDt = scrnMsg.A.no(i).rqstRcvDt_A1.getValue();
//                    String now = ZYPDateUtil.getSalesDate();
//                    if (ZYPDateUtil.compare(rqstRcvDt, now) < 0) {
//                        scrnMsg.A.no(i).rqstRcvDt_A1.setErrorInfo(1, NPAM0079E, new String[] {DATE_NEEDED });
//                        scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDt_A1);
//                        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
//                    }
//                    if (!(ZYPDateUtil.isBusinessDay(glblCmpyCd, rqstRcvDt))) {
//                        scrnMsg.A.no(i).rqstRcvDt_A1.setErrorInfo(1, NPAM0094E, new String[] {DATE_NEEDED });
//                        scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDt_A1);
//                        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
//                    }
//                }
//            }
         // QC#21170 End
        }
    }

    private static boolean isTime(EZDBStringItem... timeItems) {
        for (EZDBStringItem timeItem : timeItems) {
            if (ZYPCommonFunc.hasValue(timeItem)) {
                // IDX_5:5 hh24mi input length check
                if (timeItem.getValue().length() < IDX_5) {
                    // timeItem.setErrorInfo(1, NWAM0664E, new
                    // String[] {TIME_FORMAT });
                    timeItem.setErrorInfo(1, NPAM1515E, new String[] {TIME_FORMAT });
                    return false;
                }

                if (!timeItem.getValue().matches(CHK_TIME_PATTERN)) {
                    timeItem.setErrorInfo(1, NPAM1515E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get Time
     * @param tm time
     * @return time
     */
    public static String getTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            return tm.replace(COLON, "");
        }
        return tm;
    }

    /**
     * Get Param NWAL1130 For OpenWin_Requester
     * @param scrnMsg NPAL1510BMsg
     * @param glblCmpyCd String
     * @return Param NWAL1130 For OpenWin_Requester
     */
    public static Object[] getParamNWAL1130ForRequester(NPAL1280BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Requester Search Popup";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("   AP.USR_NM ");
        sb.append("  ,AP.FIRST_NM || (NVL2(AP.MID_NM, ' '||AP.MID_NM||' ', ' ')) || AP.LAST_NM FULL_PSN_NM ");
        sb.append("  ,AP.EZCANCELFLAG ");
        sb.append("  ,AP.GLBL_CMPY_CD ");
        sb.append("FROM ");
        sb.append("   AUTH_PSN AP ");
        sb.append("WHERE");
        sb.append("    AP.GLBL_CMPY_CD   = " + "'" + glblCmpyCd + "'");
        sb.append(" AND AP.EZCANCELFLAG  = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Person Code";
        whereArray1[IDX_1] = "USR_NM";
        whereArray1[IDX_2] = "";
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Person Name";
        whereArray2[IDX_1] = "FULL_PSN_NM";
        whereArray2[IDX_2] = "%" + scrnMsg.fullPsnNm.getValue() + "%";
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Person Code";
        columnArray1[IDX_1] = "USR_NM";
        columnArray1[IDX_2] = new BigDecimal(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Person Name";
        columnArray2[IDX_1] = "FULL_PSN_NM";
        columnArray2[IDX_2] = new BigDecimal(IDX_62);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "USR_NM";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        params[IDX_5] = sortList;
        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * Get Param NWAL1130 For OpenWin_Supplier
     * @param scrnMsg NPAL1510BMsg
     * @param lineNum int
     * @return Param NWAL1130 For OpenWin_Supplier
     */
    public static Object[] getParamNWAL1130ForSupplier(NPAL1280BMsg scrnMsg, int lineNum) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = null;
        params[IDX_1] = "Supplier Search Popup";
        params[IDX_2] = "PO_VND_V";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Supplier Code";
        whereArray1[IDX_1] = "PRNT_VND_CD";
        // QC#53300 2019/10/4 Mod Start
//        whereArray1[IDX_2] = null;
        if (lineNum > -1) {
        	whereArray1[IDX_2] = scrnMsg.A.no(lineNum).prntVndCd_A1.getValue();
        } else {
        	whereArray1[IDX_2] = scrnMsg.prntVndCd.getValue();
        }
        // QC#53300 2019/10/4 Mod End
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Supplier Name";
        whereArray2[IDX_1] = "PRNT_VND_NM";
        if (lineNum > -1) {
            whereArray2[IDX_2] = scrnMsg.A.no(lineNum).prntVndNm_A1.getValue();
        } else {
            whereArray2[IDX_2] = scrnMsg.prntVndNm.getValue();
        }
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Supplier Site Code";
        whereArray3[IDX_1] = "VND_CD";
        if (lineNum > -1) {
            whereArray3[IDX_2] = null;
        } else {
            whereArray3[IDX_2] = scrnMsg.vndCd.getValue();
        }
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "Supplier Site Name";
        whereArray4[IDX_1] = "VND_NM";
        if (lineNum > -1) {
            whereArray4[IDX_2] = scrnMsg.A.no(lineNum).locNm_A1.getValue();
        } else {
            whereArray4[IDX_2] = scrnMsg.locNm.getValue();
        }
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        whereList.add(whereArray3);
        whereList.add(whereArray4);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Supplier Code";
        columnArray1[IDX_1] = "PRNT_VND_CD";
        columnArray1[IDX_2] = new BigDecimal(IDX_10);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Supplier Name";
        columnArray2[IDX_1] = "PRNT_VND_NM";
        columnArray2[IDX_2] = new BigDecimal(IDX_30);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Supplier Site Code";
        columnArray3[IDX_1] = "VND_CD";
        columnArray3[IDX_2] = new BigDecimal(IDX_15);
        columnArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Supplier Site Name";
        columnArray4[IDX_1] = "VND_NM";
        columnArray4[IDX_2] = new BigDecimal(IDX_30);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        columnList.add(columnArray3);
        columnList.add(columnArray4);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRNT_VND_CD, VND_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        params[IDX_5] = sortList;
        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * Get Param NWAL1130 For OpenWin_Carrier
     * @param scrnMsg NPAL1510BMsg
     * @return Param NWAL1130 For OpenWin_Carrier
     */
    public static Object[] getParamNWAL1130ForCarrier(NPAL1280BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Carrier Search Popup";
        params[IDX_2] = "OTBD_CARR_V";

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Carrier Code";
        whereArray1[IDX_1] = "CARR_CD";
        // QC#29155 Update.
        whereArray1[IDX_2] = scrnMsg.carrCd_HF.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Carrier Name";
        whereArray2[IDX_1] = "CARR_NM";
        // QC#29155 Update.
        whereArray2[IDX_2] = scrnMsg.locNm_HF.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Carrier Code";
        columnArray1[IDX_1] = "CARR_CD";
        columnArray1[IDX_2] = new BigDecimal(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Carrier Name";
        columnArray2[IDX_1] = "CARR_NM";
        columnArray2[IDX_2] = new BigDecimal(IDX_60);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "CARR_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        params[IDX_5] = sortList;
        ZYPTableUtil.clear(scrnMsg.P);
        params[IDX_6] = scrnMsg.P;
        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1280BMsg
     * @param glblCmpyCd Sting
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setParamForLocationPopup(NPAL1280BMsg scrnMsg, String glblCmpyCd) {

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

        // START 2019/05/21 T.Ogura [QC#50138,MOD]
//        List<String> bizAppKeyId = null;
//        String locRoleTpList = NMXC100001EnableWH.getLocRoleTpForPopup(glblCmpyCd, BUSINESS_APPL_ID, bizAppKeyId, scrnMsg.prchReqTpCd_SL.getValue());
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, locRoleTpList);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, LOC_ROLE_TP_CDLIST);
        // END   2019/05/21 T.Ogura [QC#50138,MOD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PA, ZYPConstant.FLG_OFF_N);

        int paramCount = 0;
        Object[] params = new Object[IDX_11];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     *Item Search Popup param
     * @param scrnMsg NPAL1280BMsg
     * @param lineIndex int
     * @return Item Search Popup Param (NMAL6800) Object[]
     */
    public static Object[] setParamForItemPopup(NPAL1280BMsg scrnMsg, int lineIndex) {
        scrnMsg.xxLineNum.setValue(Integer.valueOf(lineIndex).toString());

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.A.no(lineIndex).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.prchGrpCd_SL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, MODE_CD);

        int paramCount = 0;
        Object[] params = new Object[IDX_11];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * Save or Submit Return CheckItems
     * @param scrnMsg NPAL1280BMsg
     * @return boolean
     */
    public static boolean retCheckItems(NPAL1280BMsg scrnMsg) {
        boolean isError = false;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!isError) {
                isError = checkErrorItem(scrnMsg.A.no(i).mdseCd_A1 //
                        , scrnMsg.A.no(i).prntVndNm_A1 //
                        , scrnMsg.A.no(i).locNm_A1 //
                        , scrnMsg.A.no(i).prntVndCd_A1 //
                        , scrnMsg.A.no(i).vndCd_A1 //
                        , scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1 //
                        , scrnMsg.A.no(i).rqstRcvDt_A1 //
                        // START 02/03/2023 T.Kuroda [QC#60966, ADD]
                        , scrnMsg.A.no(i).rqstShipDt_A1 //
                        // END   02/03/2023 T.Kuroda [QC#60966, ADD]
                        , scrnMsg.A.no(i).prchReqDispQty_A1 //
                        , scrnMsg.A.no(i).prchReqLineTpCd_A1 //
                        , scrnMsg.A.no(i).xxScrItem130Txt_A1 //
                        , scrnMsg.A.no(i).xxChkBox_A1 //
                        // QC#24918
                        , scrnMsg.A.no(i).prchReqLineCmntTxt_A1);
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prntVndNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).locNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prntVndCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).vndCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstRcvDt_A1);
            // START 02/03/2023 T.Kuroda [QC#60966, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rqstShipDt_A1);
            // END   02/03/2023 T.Kuroda [QC#60966, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqDispQty_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqLineTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            // QC#24918
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqLineCmntTxt_A1);
            if (isError) {
                scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
            }
        }

        if (!isError) {
            isError = checkErrorItem(scrnMsg.carrCd_HF, scrnMsg.shpgSvcLvlCd_SL, scrnMsg.frtCondCd_SL, scrnMsg.locNm_HF, scrnMsg.carrAcctNum_HF);
            scrnMsg.addCheckItem(scrnMsg.locNm_HF);
            scrnMsg.addCheckItem(scrnMsg.carrCd_HF);
            scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SL);
            scrnMsg.addCheckItem(scrnMsg.frtCondCd_SL);
            scrnMsg.addCheckItem(scrnMsg.carrAcctNum_HF);
            // QC#53816 2019/10/1 Add Start
            scrnMsg.addCheckItem(scrnMsg.shipToCustCd);
            // QC#53816 2019/10/1 Add End

            // QC#28709 Add.
            isError = checkErrorItem(scrnMsg.xxAllLineAddr_HS //
                    , scrnMsg.shipToCtyAddr_HS//
                    , scrnMsg.shipToStCd_HS//
                    , scrnMsg.shipToPostCd_HS//
                    , scrnMsg.shipToCtryCd_HS//
                    , scrnMsg.shipToCntyNm_HS//
                    , scrnMsg.xxLocNm);
            scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr_HS);
            scrnMsg.addCheckItem(scrnMsg.shipToCtyAddr_HS);
            scrnMsg.addCheckItem(scrnMsg.shipToStCd_HS);
            scrnMsg.addCheckItem(scrnMsg.shipToPostCd_HS);
            scrnMsg.addCheckItem(scrnMsg.shipToCtryCd_HS);
            scrnMsg.addCheckItem(scrnMsg.shipToCntyNm_HS);
            scrnMsg.addCheckItem(scrnMsg.xxLocNm);

            if (isError) {
                scrnMsg.xxDplyTab.setValue(TAB_HEADER);
            }
        }

        if (!isError) {
            // START 02/03/2023 T.Kuroda [QC#60966, MOD]
            // QC#21169
//            isError = checkErrorItem(scrnMsg.destRtlWhCd, scrnMsg.destRtlSwhCd, scrnMsg.prchReqTpCd_SL, scrnMsg.rqstRcvDt);
            isError = checkErrorItem(scrnMsg.destRtlWhCd, scrnMsg.destRtlSwhCd, scrnMsg.prchReqTpCd_SL, scrnMsg.rqstRcvDt, scrnMsg.rqstShipDt);
            // END   02/03/2023 T.Kuroda [QC#60966, MOD]
            scrnMsg.addCheckItem(scrnMsg.destRtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd);
            scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SL);
            scrnMsg.addCheckItem(scrnMsg.rqstRcvDt);
            // START 02/03/2023 T.Kuroda [QC#60966, ADD]
            scrnMsg.addCheckItem(scrnMsg.rqstShipDt);
            // END   02/03/2023 T.Kuroda [QC#60966, ADD]
            scrnMsg.xxDplyTab.setValue(scrnMsg.xxDplyTab.getValue());
        }
        // clear html attribute
        scrnMsg.clearAllGUIAttribute(BUSINESS_SCREEN_ID);

        S21TableColorController tblColor = new S21TableColorController(BUSINESS_SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);

        return isError;
    }

    /**
     * <pre>
     * Check ErrorItem
     * </pre>
     * @param List EZDBItem
     */
    private static boolean checkErrorItem(EZDBItem... inParamEzdbItems) {
        boolean isError = false;
        for (EZDBItem item : inParamEzdbItems) {
            // QC#29155 Add warning check.
            if (item.isError() || item.getErrorCode() == MSG_ERR_CD_WARN) {
                isError = true;
                break;
            }
        }
        return isError;
    }

    /**
     * Set Account Popup param
     * @param scrnMsg NPAL1280BMsg
     * @param glblCmpyCd Sting
     * @param lineIndex int
     * @return GL Common Popup Param (NMAL2550) Object[]
     */
    public static Object[] setParamForAccountPopup(NPAL1280BMsg scrnMsg, String glblCmpyCd, int lineIndex) {

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

        
        if (PRCH_REQ_LINE_TP.GOODS.equals(scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(lineIndex).coaCmpyCd_A1)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, BUSINESS_APPL_ID + scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.coaCmpyCd_GO);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.coaAfflCd_GO);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.A.no(lineIndex).coaBrCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, scrnMsg.coaCcCd_GO);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, scrnMsg.A.no(lineIndex).coaAcctCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.coaProdCd_GO);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.coaChCd_GO);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.coaProjCd_GO);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.coaExtnCd_GO);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, BUSINESS_APPL_ID + scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.A.no(lineIndex).coaCmpyCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.A.no(lineIndex).coaAfflCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.A.no(lineIndex).coaBrCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, scrnMsg.A.no(lineIndex).coaCcCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, scrnMsg.A.no(lineIndex).coaAcctCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.A.no(lineIndex).coaProdCd_HD);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.A.no(lineIndex).coaChCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(lineIndex).coaProjCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.A.no(lineIndex).coaExtnCd_A1);
            }
        } else if (PRCH_REQ_LINE_TP.EXPENSE.equals(scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue())//
                || PRCH_REQ_LINE_TP.EXPENSE_W_OR_RECEIPT.equals(scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue())) {

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, BUSINESS_APPL_ID + scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
                String[] list = scrnMsg.A.no(lineIndex).xxScrItem130Txt_A1.getValue().split(Pattern.quote(DOT), -1);
                if (list.length < SEGMENT_TOKEN_LIST_SIZE) {
                    return null;
                }
                if (list[IDX_0].length() > SEGMENT_ELEMENT_LENGTH_CMPY) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaCmpyCd_A1, list[IDX_0].substring(IDX_0, SEGMENT_ELEMENT_LENGTH_CMPY));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaCmpyCd_A1, list[IDX_0]);
                }
                if (list[IDX_1].length() > SEGMENT_ELEMENT_LENGTH_BR) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaBrCd_A1, list[IDX_1].substring(IDX_0, SEGMENT_ELEMENT_LENGTH_BR));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaBrCd_A1, list[IDX_1]);
                }
                if (list[IDX_2].length() > SEGMENT_ELEMENT_LENGTH_CC) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaCcCd_A1, list[IDX_2].substring(IDX_0, SEGMENT_ELEMENT_LENGTH_CC));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaCcCd_A1, list[IDX_2]);
                }
                if (list[IDX_3].length() > SEGMENT_ELEMENT_LENGTH_ACCT) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaAcctCd_A1, list[IDX_3].substring(IDX_0, SEGMENT_ELEMENT_LENGTH_ACCT));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaAcctCd_A1, list[IDX_3]);
                }
                if (list[IDX_4].length() > SEGMENT_ELEMENT_LENGTH_PROD) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaProdCd_HD, list[IDX_4].substring(IDX_0, SEGMENT_ELEMENT_LENGTH_PROD));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaProdCd_HD, list[IDX_4]);
                }
                if (list[IDX_5].length() > SEGMENT_ELEMENT_LENGTH_CH) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaChCd_A1, list[IDX_5].substring(IDX_0, SEGMENT_ELEMENT_LENGTH_CH));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaChCd_A1, list[IDX_5]);
                }
                if (list[IDX_6].length() > SEGMENT_ELEMENT_LENGTH_AFFL) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaAfflCd_A1, list[IDX_6].substring(IDX_0, SEGMENT_ELEMENT_LENGTH_AFFL));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaAfflCd_A1, list[IDX_6]);
                }
                if (list[IDX_7].length() > SEGMENT_ELEMENT_LENGTH_PROJ) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaProjCd_A1, list[IDX_7].substring(IDX_0, SEGMENT_ELEMENT_LENGTH_PROJ));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaProjCd_A1, list[IDX_7]);
                }
                if (list[IDX_8].length() > SEGMENT_ELEMENT_LENGTH_EXTN) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaExtnCd_A1, list[IDX_8].substring(IDX_0, SEGMENT_ELEMENT_LENGTH_EXTN));
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(lineIndex).coaExtnCd_A1, list[IDX_8]);
                }

            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(lineIndex).coaCmpyCd_A1)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, BUSINESS_APPL_ID + scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.coaCmpyCd_AS);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.coaAfflCd_AS);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.coaBrCd_AS);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, scrnMsg.coaCcCd_AS);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.coaProdCd_AS);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.coaChCd_AS);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.coaProjCd_AS);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.coaExtnCd_AS);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, BUSINESS_APPL_ID + scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.A.no(lineIndex).coaCmpyCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.A.no(lineIndex).coaAfflCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.A.no(lineIndex).coaBrCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, scrnMsg.A.no(lineIndex).coaCcCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, scrnMsg.A.no(lineIndex).coaAcctCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.A.no(lineIndex).coaProdCd_HD);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.A.no(lineIndex).coaChCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(lineIndex).coaProjCd_A1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.A.no(lineIndex).coaExtnCd_A1);
            }
        } else if (!(ZYPCommonFunc.hasValue(scrnMsg.A.no(lineIndex).coaCmpyCd_A1))) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, BUSINESS_APPL_ID + scrnMsg.A.no(lineIndex).prchReqLineTpCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.A.no(lineIndex).coaCmpyCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.A.no(lineIndex).coaAfflCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, scrnMsg.A.no(lineIndex).coaBrCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, scrnMsg.A.no(lineIndex).coaCcCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, scrnMsg.A.no(lineIndex).coaAcctCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.A.no(lineIndex).coaProdCd_HD);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.A.no(lineIndex).coaChCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(lineIndex).coaProjCd_A1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.A.no(lineIndex).coaExtnCd_A1);
        }
            
        int paramCount = 0;
        Object[] params = new Object[IDX_11];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;

        return params;
    }

    /**
     * Set TextEntry Popup param
     * @param scrnMsg NPAL1280BMsg
     * @return PO Text Entry Popup Param (NPAL0170) Object[]
     */
    public static Object[] setParamForTextEntryPopup(NPAL1280BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.prchReqNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, PO_MSG_TP.INTERNAL_PO_MESSAGE);

        int paramCount = 0;
        Object[] params = new Object[IDX_7];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.R;

        return params;
    }

    /**
     * Set Inventory Inquiry Screen param
     * @param scrnMsg NPAL1280BMsg
     * @param lineIndex int
     * @return Inventory Inquiry Screen Param(NLCL0250) Object[]
     */
    public static Object[] setParamForOnhandInvInq(NPAL1280BMsg scrnMsg, int lineIndex) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.A.no(lineIndex).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.destRtlSwhCd);

        int paramCount = 0;
        Object[] params = new Object[IDX_3];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;

        return params;
    }

    /**
     * Set Inventory Transaction History Screen param
     * @param scrnMsg NPAL1280BMsg
     * @param salesDate String
     * @param varSalesDate String
     * @param lineIndex int
     * @return Inventory Transaction History Screeny Param Object[]
     */
    public static Object[] setParamForInvTrxHist(NPAL1280BMsg scrnMsg, String salesDate, String varSalesDate, int lineIndex) {

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
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxPopPrm_PN.clear();
        scrnMsg.xxTrxDt_P1.clear();
        scrnMsg.xxTrxDt_P2.clear();
        scrnMsg.xxTrxRefPk_P1.clear();

        if (ZYPCommonFunc.hasValue(varSalesDate) && ZYPCommonFunc.isNumeric(varSalesDate)) {
            String minusDay = "-" + varSalesDate;
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTrxDt_P1, ZYPDateUtil.addDays(salesDate, Integer.valueOf(minusDay)));
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTrxDt_P1, salesDate);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTrxDt_P2, salesDate);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, scrnMsg.A.no(lineIndex).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PJ, scrnMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PK, scrnMsg.destRtlSwhCd);

        int paramCount = 0;
        Object[] params = new Object[IDX_24];
        params[paramCount++] = scrnMsg.xxTrxDt_P1;
        params[paramCount++] = scrnMsg.xxTrxDt_P2;
        params[paramCount++] = scrnMsg.xxTrxRefPk_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;
        params[paramCount++] = scrnMsg.xxPopPrm_PB;
        params[paramCount++] = scrnMsg.xxPopPrm_PC;
        params[paramCount++] = scrnMsg.xxPopPrm_PD;
        params[paramCount++] = scrnMsg.xxPopPrm_PE;
        params[paramCount++] = scrnMsg.xxPopPrm_PF;
        params[paramCount++] = scrnMsg.xxPopPrm_PG;
        params[paramCount++] = scrnMsg.xxPopPrm_PH;
        params[paramCount++] = scrnMsg.xxPopPrm_PI;
        params[paramCount++] = scrnMsg.xxPopPrm_PJ;
        params[paramCount++] = scrnMsg.xxPopPrm_PK;
        params[paramCount++] = scrnMsg.xxPopPrm_PL;
        params[paramCount++] = scrnMsg.xxPopPrm_PM;
        params[paramCount++] = scrnMsg.xxPopPrm_PN;

        return params;
    }

    /**
     * PR Approval History Popup param
     * @param scrnMsg NPAL1280BMsg
     * @return PR Approval History Popup Param (NPAL1210) Object[]
     */
    public static Object[] setParamForNPAL1210Popup(NPAL1280BMsg scrnMsg) {
        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, APVL_HIST_SRC_TP.PO_REQUISITION);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.prchReqNum);

        int paramCount = 0;
        Object[] params = new Object[IDX_2];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;

        return params;
    }

    /**
     * chkInquiryRole
     * @param functionList List<String>
     * @return true:Update false:Inquiry
     */
    public static boolean chkInquiryRole(List<String> functionList) {
        for (String function : functionList) {
            if (FUNCTION_UPDATE.equals(function)) {
                return true;
            }
        }
        return false;
    }


    /**
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NPAL1280_XBMsgArray
     * @param size int
     * @return Object[]
     */

    public static Object[] toArray_popupForZYPL0310(NPAL1280_XBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }

    // START 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]
    /**
     * Set Supply Demand Screen param
     * @param scrnMsg NPAL1280BMsg
     * @param lineIndex int
     * @return Supply / Demand Screen Param(NPAL1130) Object[]
     */
    public static Object[] setParamForSupplyDemand(NPAL1280BMsg scrnMsg, int lineIndex) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.A.no(lineIndex).mdseCd_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.destRtlSwhCd);

        int paramCount = 0;
        Object[] params = new Object[IDX_3];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;

        return params;
    }
    // END 2017/11/07 S.Katsuma [SOL#014(QC#18401),ADD]

    //QC#21170 ADD Start
    /**
     * dtCompare
     * @param date1
     * @param date2
     * @return
     */
    private static boolean dtCompare (String date1, String date2) {
        if (ZYPCommonFunc.hasValue(date1) && ZYPCommonFunc.hasValue(date2)) {
            if (new BigDecimal(date1).compareTo(new BigDecimal(date2)) > 0 ) {
                return true;
            }
        }
        return false;
    }
    /**
     * getHeaderRqstRcvDt
     * @param bMsg
     * @param salesDate
     */
    public static void setHeaderRqstRcvDt_SmsgMaxVal(NPAL1280BMsg bMsg, String glblCmpyCd) {
        // get Max RqstRcvDt
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String maxDt = salesDate;
        for (int i=0; i<bMsg.A.getValidCount(); i++) {
            if(!ZYPCommonFunc.hasValue(bMsg.A.no(i).rqstRcvDt_A1) || PRCH_REQ_LINE_STS.CANCELLED.equals(bMsg.A.no(i).prchReqLineStsCd_HD.getValue())) {
                continue;
            }
            if(dtCompare(bMsg.A.no(i).rqstRcvDt_A1.getValue(), maxDt)) {
                maxDt = bMsg.A.no(i).rqstRcvDt_A1.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(bMsg.rqstRcvDt, maxDt);
    }
    //QC#21170 ADD End

    // START 02/03/2023 T.Kuroda [QC#60966, ADD]
    /**
     * getHeaderRqstShipDt
     * @param bMsg
     * @param salesDate
     */
    public static void setHeaderRqstShipDt_SmsgMaxVal(NPAL1280BMsg bMsg, String glblCmpyCd) {
        // get Max RqstShipDt
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        String maxDt = salesDate;
        for (int i=0; i<bMsg.A.getValidCount(); i++) {
            if(!ZYPCommonFunc.hasValue(bMsg.A.no(i).rqstShipDt_A1) || PRCH_REQ_LINE_STS.CANCELLED.equals(bMsg.A.no(i).prchReqLineStsCd_HD.getValue())) {
                continue;
            }
            if(dtCompare(bMsg.A.no(i).rqstShipDt_A1.getValue(), maxDt)) {
                maxDt = bMsg.A.no(i).rqstShipDt_A1.getValue();
            }
        }
        ZYPEZDItemValueSetter.setValue(bMsg.rqstShipDt, maxDt);
    }
    // END   02/03/2023 T.Kuroda [QC#60966, ADD]

 // QC#26990 Add Start
    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1500BMsg
     * @return Object[]
     */
    public static Object[] setParamForNMAL6760Popup(NPAL1280BMsg scrnMsg) {

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
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxPopPrm_PN.clear();

        // 2019/09/20 QC#52362 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.xxLocNm);
        String shipToCustLocNm = "";
        if (ZYPCommonFunc.hasValue(scrnMsg.xxLocNm)) {
        	shipToCustLocNm = scrnMsg.xxLocNm.getValue();
        	if (shipToCustLocNm.length() == 60) {
        		shipToCustLocNm = shipToCustLocNm.substring(0, 59) + "%";
        	}
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, shipToCustLocNm);
        // 2019/09/20 QC#52362 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PG, scrnMsg.shipToCustCd);


        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, "02");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PC, "03");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PD, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PE, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PK, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PL, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PM, "-");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PN, "-");

        int paramCount = 0;
        Object[] params = new Object[24];
        params[paramCount++] = scrnMsg.xxPopPrm_P0;
        params[paramCount++] = scrnMsg.xxPopPrm_P1;
        params[paramCount++] = scrnMsg.xxPopPrm_P2;
        params[paramCount++] = scrnMsg.xxPopPrm_P3;
        params[paramCount++] = scrnMsg.xxPopPrm_P4;
        params[paramCount++] = scrnMsg.xxPopPrm_P5;
        params[paramCount++] = scrnMsg.xxPopPrm_P6;
        params[paramCount++] = scrnMsg.xxPopPrm_P7;
        params[paramCount++] = scrnMsg.xxPopPrm_P8;
        params[paramCount++] = scrnMsg.xxPopPrm_P9;
        params[paramCount++] = scrnMsg.xxPopPrm_PA;
        params[paramCount++] = scrnMsg.xxPopPrm_PB;
        params[paramCount++] = scrnMsg.xxPopPrm_PC;
        params[paramCount++] = scrnMsg.xxPopPrm_PD;
        params[paramCount++] = scrnMsg.xxPopPrm_PE;
        params[paramCount++] = scrnMsg.xxPopPrm_PF;
        params[paramCount++] = scrnMsg.xxPopPrm_PG;
        params[paramCount++] = scrnMsg.xxPopPrm_PH;
        params[paramCount++] = scrnMsg.xxPopPrm_PI;
        params[paramCount++] = scrnMsg.xxPopPrm_PJ;
        params[paramCount++] = scrnMsg.xxPopPrm_PK;
        params[paramCount++] = scrnMsg.xxPopPrm_PL;
        params[paramCount++] = scrnMsg.xxPopPrm_PM;
        params[paramCount++] = scrnMsg.xxPopPrm_PN;

        return params;
    }
    
    /**
     * Get Address Lookup Popup param
     * @param scrnMsg NPAL1500BMsg
     * @param glblCmpyCd String
     * @return Parameter[ Object[7] ]
     */
    public static Object[] getAddressPopupParam(NPAL1280BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[IDX_7];
        scrnMsg.P.clear();

        params[IDX_0] = "";
        params[IDX_1] = "Address Lookup Popup";
        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT ");
        baseSql.append("    P.GLBL_CMPY_CD ");
        baseSql.append("  , P.EZCANCELFLAG ");
        baseSql.append("  , P.CTY_ADDR ");
        baseSql.append("  , P.ST_CD ");
        baseSql.append("  , P.POST_CD ");
        baseSql.append("  , C.CNTY_NM ");
        baseSql.append("FROM ");
        baseSql.append("    POST P ");
        baseSql.append("  , CNTY_POST_RELN R ");
        baseSql.append("  , CNTY C ");
        baseSql.append("WHERE ");
        baseSql.append("    P.GLBL_CMPY_CD = '" + glblCmpyCd + "' ");
        baseSql.append("AND P.EZCANCELFLAG = '0' ");
        baseSql.append("AND R.POST_PK(+) = P.POST_PK ");
        baseSql.append("AND R.GLBL_CMPY_CD(+) = P.GLBL_CMPY_CD ");
        baseSql.append("AND R.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.GLBL_CMPY_CD(+) = R.GLBL_CMPY_CD ");
        baseSql.append("AND C.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.CNTY_PK(+) = R.CNTY_PK ");
        String sql = baseSql.toString();
        params[IDX_2] = sql;

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "City";
        whereArray1[IDX_1] = "CTY_ADDR";
        whereArray1[IDX_2] = scrnMsg.shipToCtyAddr_HS.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "State";
        whereArray2[IDX_1] = "ST_CD";
        whereArray2[IDX_2] = scrnMsg.shipToStCd_HS.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Postal Code";
        whereArray3[IDX_1] = "POST_CD";
        whereArray3[IDX_2] = scrnMsg.shipToPostCd_HS.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "County";
        whereArray4[IDX_1] = "CNTY_NM";
        whereArray4[IDX_2] = scrnMsg.shipToCntyNm_HS.getValue();
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[0] = "City";
        columnArray1[1] = "CTY_ADDR";
        columnArray1[2] = BigDecimal.valueOf(25);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[0] = "State";
        columnArray2[1] = "ST_CD";
        columnArray2[2] = BigDecimal.valueOf(5);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[0] = "Postal Code";
        columnArray3[1] = "POST_CD";
        columnArray3[2] = BigDecimal.valueOf(10);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[0] = "County";
        columnArray4[1] = "CNTY_NM";
        columnArray4[2] = BigDecimal.valueOf(30);
        columnArray4[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray4);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "CTY_ADDR";
        sortConditionArray1[IDX_1] = "ASC";

        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "ST_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        Object[] sortConditionArray3 = new Object[IDX_2];
        sortConditionArray3[IDX_0] = "POST_CD";
        sortConditionArray3[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray3);

        Object[] sortConditionArray4 = new Object[IDX_2];
        sortConditionArray4[IDX_0] = "CNTY_NM";
        sortConditionArray4[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray4);

        params[IDX_5] = sortConditionList;
        params[IDX_6] = scrnMsg.P;

        return params;
    }
// QC#26990 Add End

    // START 03/17/2020 [QC#56122,ADD]
    /**
     * validateSegmentElement
     * @param element String
     * @param len int
     * @return boolean
     */
    public static boolean validateSegmentElement(String element, int len) {
        if (!hasValue(element)) {
            return true;
        }
        if (element.length() > len) {
            return false;
        }
        return true;
    }
    // END 03/17/2020 [QC#56122,ADD]
}
