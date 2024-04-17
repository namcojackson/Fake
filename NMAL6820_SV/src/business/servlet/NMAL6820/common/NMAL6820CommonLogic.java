/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820.common;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6820.NMAL6820BMsg;
import business.servlet.NMAL6820.NMAL6820Bean;
import business.servlet.NMAL6820.constant.NMAL6820Constant;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUIFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableFocusRule;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NMAL6820 Warehouse Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC# 2380
 * 02/11/2016   CSAI            D.Fukaya        Update          QC# 1598
 * 02/11/2016   CSAI            D.Fukaya        Update          QC# 1600
 * 02/11/2016   CSAI            D.Fukaya        Update          QC# 2368
 * 02/18/2016   CSAI            D.Fukaya        Update          QC# 3436
 * 02/22/2016   CSAI            D.Fukaya        Update          QC# 2369
 * 08/05/2016   CITS            S.Endo          Update          QC#10838
 * 10/19/2016   Fujitsu         C.Yokoi         Update          QC# 4096
 * 11/21/2017   CITS            K.Ogino         Update          QC#22212
 * 04/09/2019   Fujitsu         T.Ogura         Update          QC#28667
 * 09/17/2020   CITS            J.Evangelista   Update          QC#57659
 *</pre>
 */
public class NMAL6820CommonLogic {

    /**
     * The method explanation: The display control of the screen item
     * for Create Mode
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6820BMsg
     * @param functionList List<String>
     */
    public static void cntrlScrnItemDispCreateMode(S21CommonHandler handler, NMAL6820BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Header
        scrnMsg.rtlWhCd_H2.setInputProtected(false);
        scrnMsg.rtlWhCd_H1.setInputProtected(false);
        scrnMsg.rtlWhNm_H1.setInputProtected(false);
        scrnMsg.rtlWhDescTxt_H1.setInputProtected(false);
        scrnMsg.rtlWhCatgCd_H1.setInputProtected(false);
        scrnMsg.whMgrPsnCd_H2.setInputProtected(false);
        scrnMsg.whMgrPsnCd_H1.setInputProtected(false);
        scrnMsg.fullPsnNm_H1.setInputProtected(true);
        scrnMsg.altPsnCd_H2.setInputProtected(false);
        scrnMsg.altPsnCd_H1.setInputProtected(false);
        scrnMsg.fullPsnNm_H2.setInputProtected(true);
        scrnMsg.telNum_H1.setInputProtected(false);
        scrnMsg.faxNum_H1.setInputProtected(false);
        scrnMsg.invtyAcctCd_H1.setInputProtected(false);
        scrnMsg.invtyOwnrCd_H1.setInputProtected(false);
        scrnMsg.whOwnrCd_H1.setInputProtected(false);
        scrnMsg.whSysTpCd_H1.setInputProtected(false);
        scrnMsg.wmsWhCd_H1.setInputProtected(false);
        scrnMsg.physWhCd_H1.setInputProtected(false);
        scrnMsg.autoSoDropFlg_H1.setInputProtected(false);
        scrnMsg.firstRefCmntTxt_H1.setInputProtected(false);
        scrnMsg.effFromDt_H1.setInputProtected(false);
        scrnMsg.effThruDt_H1.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_ST.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_ET.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_CT.setInputProtected(false);
        // START 2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.xxScrItem7Txt_FT.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_UT.setInputProtected(false);
        // END	 2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.tmZoneCd_H1.setInputProtected(true);
        scrnMsg.geoCd_H1.setInputProtected(false);
        scrnMsg.geoCd_H2.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.geoCd_H2.setInputProtected(false);        // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.carrCd_H2.setInputProtected(false);
        scrnMsg.carrCd_H1.setInputProtected(false);
        scrnMsg.carrNm_H1.setInputProtected(false);
        scrnMsg.coaBrCd_H2.setInputProtected(false);
        scrnMsg.coaBrCd_H1.setInputProtected(false);
        scrnMsg.coaBrNm_H1.setInputProtected(true);
        scrnMsg.locTpCd_H1.setInputProtected(false);
        scrnMsg.whCd_H1.setInputProtected(false);

        scrnMsg.prntVndCd_L1.setInputProtected(false);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.prntVndCd.setInputProtected(false);
        // START 2019/04/09 T.Ogura [QC#28667,MOD]
//        scrnMsg.prntVndNm.setInputProtected(false);
        scrnMsg.prntVndNm.setInputProtected(true);
        // END   2019/04/09 T.Ogura [QC#28667,MOD]
        scrnMsg.vndCd_L1.setInputProtected(false);        // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.vndCd.setInputProtected(false);
        // START 2019/04/09 T.Ogura [QC#28667,MOD]
//        scrnMsg.vndNm.setInputProtected(false);
        scrnMsg.vndNm.setInputProtected(true);
        // END   2019/04/09 T.Ogura [QC#28667,MOD]

        // START 2019/04/09 T.Ogura [QC#28667,ADD]
        if (RTL_WH_CATG.DROP_RMA.equals(scrnMsg.rtlWhCatgCd_H1.getValue())) {
            scrnMsg.prntVndCd.setIndispensable(true);
            scrnMsg.vndCd.setIndispensable(true);
        } else {
            scrnMsg.prntVndCd.setIndispensable(false);
            scrnMsg.vndCd.setIndispensable(false);
        }
        // END   2019/04/09 T.Ogura [QC#28667,ADD]

        // Address
        scrnMsg.dsAcctNum_S1.setInputProtected(true);
        scrnMsg.dsAcctNm_S1.setInputProtected(true);
        scrnMsg.vndLocCd_S1.setInputProtected(false);
        scrnMsg.locNum_S1.setInputProtected(false);
        scrnMsg.shipToCustCd_S2.setInputProtected(false);
        if (isShipToAreaToBeActivated(scrnMsg)) {
            scrnMsg.dsLocNm_S1.setInputProtected(false);
            scrnMsg.addlLocNm_S1.setInputProtected(false);
            scrnMsg.firstLineAddr_S1.setInputProtected(false);
            scrnMsg.scdLineAddr_S1.setInputProtected(false);
            scrnMsg.thirdLineAddr_S1.setInputProtected(false);
            scrnMsg.frthLineAddr_S1.setInputProtected(false);
            scrnMsg.ctyAddr_S1.setInputProtected(false);
            scrnMsg.ctyAddr_S2.setInputProtected(false);
            scrnMsg.cntyPk_S1.setInputProtected(false);
            scrnMsg.cntyNm_S1.setInputProtected(false);
            scrnMsg.cntyNm_S2.setInputProtected(false);
            scrnMsg.stCd_S2.setInputProtected(false);
            scrnMsg.stCd_S1.setInputProtected(false);
            scrnMsg.provNm_S1.setInputProtected(false);
            scrnMsg.postCd_S1.setInputProtected(false);
            scrnMsg.postCd_S2.setInputProtected(false);
            scrnMsg.ctryCd_S1.setInputProtected(false);
            // START 2019/04/09 T.Ogura [QC#28667,MOD]
//            handler.setButtonEnabled("GetAddressShipTo", true);
            handler.setButtonEnabled(BTN_GET_SHIP_TO, true);
            // END   2019/04/09 T.Ogura [QC#28667,MOD]
        } else {
            scrnMsg.dsLocNm_S1.setInputProtected(true);
            scrnMsg.addlLocNm_S1.setInputProtected(true);
            scrnMsg.firstLineAddr_S1.setInputProtected(true);
            scrnMsg.scdLineAddr_S1.setInputProtected(true);
            scrnMsg.thirdLineAddr_S1.setInputProtected(true);
            scrnMsg.frthLineAddr_S1.setInputProtected(true);
            scrnMsg.ctyAddr_S1.setInputProtected(true);
            scrnMsg.ctyAddr_S2.setInputProtected(true);
            scrnMsg.cntyPk_S1.setInputProtected(true);
            scrnMsg.cntyNm_S1.setInputProtected(true);
            scrnMsg.cntyNm_S2.setInputProtected(true);
            scrnMsg.stCd_S2.setInputProtected(true);
            scrnMsg.stCd_S1.setInputProtected(true);
            scrnMsg.provNm_S1.setInputProtected(true);
            scrnMsg.postCd_S1.setInputProtected(true);
            scrnMsg.postCd_S2.setInputProtected(true);
            scrnMsg.ctryCd_S1.setInputProtected(true);
            // START 2019/04/09 T.Ogura [QC#28667,MOD]
//            handler.setButtonEnabled("GetAddressShipTo", false);
            handler.setButtonEnabled(BTN_GET_SHIP_TO, false);
            // END   2019/04/09 T.Ogura [QC#28667,MOD]
        }
        scrnMsg.poRcptRteTpCd_S1.setInputProtected(false);
        scrnMsg.rmaRcptRteTpCd_S1.setInputProtected(false);
        scrnMsg.xxChkBox_R1.setInputProtected(false);
        scrnMsg.dsAcctNum_R1.setInputProtected(true);
        scrnMsg.dsAcctNm_R1.setInputProtected(true);
        scrnMsg.vndLocCd_R1.setInputProtected(false);
        scrnMsg.locNum_R1.setInputProtected(false);
        scrnMsg.shipToCustCd_R2.setInputProtected(false);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg_R1.getValue())) {
            scrnMsg.dsLocNm_R1.setInputProtected(true);
            scrnMsg.rtrnToAddlLocNm_R1.setInputProtected(true);
            scrnMsg.rtrnToFirstLineAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToScdLineAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToThirdLineAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToFrthLineAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToCtyAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToCtyAddr_R2.setInputProtected(true);
            scrnMsg.cntyPk_R1.setInputProtected(true);
            scrnMsg.cntyNm_R1.setInputProtected(true);
            scrnMsg.cntyNm_R2.setInputProtected(true);
            scrnMsg.rtrnToStCd_R2.setInputProtected(true);
            scrnMsg.rtrnToStCd_R1.setInputProtected(true);
            scrnMsg.rtrnToProvNm_R1.setInputProtected(true);
            scrnMsg.rtrnToPostCd_R1.setInputProtected(true);
            scrnMsg.rtrnToPostCd_R2.setInputProtected(true);
            scrnMsg.ctryCd_R1.setInputProtected(true);
            // START 2019/04/09 T.Ogura [QC#28667,MOD]
//            handler.setButtonEnabled("GetAddressReturnTo", false);
            handler.setButtonEnabled(BTN_GET_RETURN_TO, false);
            // END   2019/04/09 T.Ogura [QC#28667,MOD]
        } else {
            scrnMsg.dsLocNm_R1.setInputProtected(false);
            scrnMsg.rtrnToAddlLocNm_R1.setInputProtected(false);
            scrnMsg.rtrnToFirstLineAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToScdLineAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToThirdLineAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToFrthLineAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToCtyAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToCtyAddr_R2.setInputProtected(false);
            scrnMsg.cntyPk_R1.setInputProtected(false);
            scrnMsg.cntyNm_R1.setInputProtected(false);
            scrnMsg.cntyNm_R2.setInputProtected(false);
            scrnMsg.rtrnToStCd_R2.setInputProtected(false);
            scrnMsg.rtrnToStCd_R1.setInputProtected(false);
            scrnMsg.rtrnToProvNm_R1.setInputProtected(false);
            scrnMsg.rtrnToPostCd_R1.setInputProtected(false);
            scrnMsg.rtrnToPostCd_R2.setInputProtected(false);
            scrnMsg.ctryCd_R1.setInputProtected(false);
            // START 2019/04/09 T.Ogura [QC#28667,MOD]
//            handler.setButtonEnabled("GetAddressReturnTo", true);
            handler.setButtonEnabled(BTN_GET_RETURN_TO, true);
            // END   2019/04/09 T.Ogura [QC#28667,MOD]
        }

        // Sourcing
        scrnMsg.plnItemInsrcCd_S1.setInputProtected(false);
        scrnMsg.srcZnCd_S1.setInputProtected(false);

        cntrlSourcePullDownListForSourcingTab(scrnMsg);

        // SWH
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).rtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(false);
            scrnMsg.A.no(i).rtlSwhDescTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).xxChkBox_P1.setInputProtected(false);
            scrnMsg.A.no(i).xxChkBox_M1.setInputProtected(false);
            scrnMsg.A.no(i).xxScrItem8Txt_A1.setInputProtected(true);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rtlSwhMndFlg_A1.getValue())) {
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(false);
            }
        }
        cntrlSourcePullDownListForSWHTab(handler, scrnMsg);

        // button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_MGRNM, true);
        handler.setButtonEnabled(BTN_ALTOWNRNM, true);
        handler.setButtonEnabled(BTN_BRNM, true);             // 2019/04/09 T.Ogura [QC#28667,ADD]
        handler.setButtonEnabled(BTN_SUPPLIER, true);         // 2019/04/09 T.Ogura [QC#28667,ADD]
        handler.setButtonEnabled(BTN_SUPPLIER_SITE, true);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        handler.setButtonEnabled(BTN_SET_SHIP_TO, true);
        handler.setButtonEnabled(BTN_CLEAR_SHIP_TO, true);
        handler.setButtonEnabled(BTN_SET_RETURN_TO, true);
        handler.setButtonEnabled(BTN_CLEAR_RETURN_TO, true);

        // TAB protection
        scrnMsg.xxTabProt_A1.setInputProtected(true); // Address Tab
        scrnMsg.xxTabProt_A2.setInputProtected(true); // Account Tab
        scrnMsg.xxTabProt_S1.setInputProtected(true); // Sourcing Tab
        scrnMsg.xxTabProt_W1.setInputProtected(true); // SWH Tab

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        setAuthorityProtect(handler, scrnMsg, functionList);
        disableAllFieldsForVirtualWH(handler, scrnMsg);
        changeFormatDate(scrnMsg);
    }

    /**
     * The method explanation: The display control of the screen item
     * for Update Mode
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6820BMsg
     * @param functionList List<String>
     */
    public static void cntrlScrnItemDispUpdateMode(EZDCommonHandler handler, NMAL6820BMsg scrnMsg, List<String> functionList) {

        // column input protection
        // true : block entry
        // false : input possible
        // Header
        scrnMsg.rtlWhCd_H2.setInputProtected(false);
        scrnMsg.rtlWhCd_H1.setInputProtected(false);
        scrnMsg.rtlWhNm_H1.setInputProtected(false);
        scrnMsg.rtlWhDescTxt_H1.setInputProtected(false);
        scrnMsg.rtlWhCatgCd_H1.setInputProtected(false);
        scrnMsg.whMgrPsnCd_H2.setInputProtected(false);
        scrnMsg.whMgrPsnCd_H1.setInputProtected(false);
        scrnMsg.fullPsnNm_H1.setInputProtected(true);
        scrnMsg.altPsnCd_H2.setInputProtected(false);
        scrnMsg.altPsnCd_H1.setInputProtected(false);
        scrnMsg.fullPsnNm_H2.setInputProtected(true);
        scrnMsg.telNum_H1.setInputProtected(false);
        scrnMsg.faxNum_H1.setInputProtected(false);
        scrnMsg.invtyAcctCd_H1.setInputProtected(false);
        scrnMsg.invtyOwnrCd_H1.setInputProtected(false);
        scrnMsg.whOwnrCd_H1.setInputProtected(false);
        scrnMsg.whSysTpCd_H1.setInputProtected(false);
        scrnMsg.wmsWhCd_H1.setInputProtected(false);
        scrnMsg.physWhCd_H1.setInputProtected(false);
        scrnMsg.autoSoDropFlg_H1.setInputProtected(false);
        scrnMsg.firstRefCmntTxt_H1.setInputProtected(false);
        scrnMsg.effFromDt_H1.setInputProtected(false);
        scrnMsg.effThruDt_H1.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_ST.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_ET.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_CT.setInputProtected(false);
        // START 2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.xxScrItem7Txt_FT.setInputProtected(false);
        scrnMsg.xxScrItem7Txt_UT.setInputProtected(false);        
        // END   2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.tmZoneCd_H1.setInputProtected(true);
        scrnMsg.geoCd_H1.setInputProtected(false);
        scrnMsg.geoCd_H2.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.geoCd_H2.setInputProtected(false);        // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.carrCd_H2.setInputProtected(false);
        scrnMsg.carrCd_H1.setInputProtected(false);
        scrnMsg.carrNm_H1.setInputProtected(false);
        scrnMsg.coaBrCd_H2.setInputProtected(false);
        scrnMsg.coaBrCd_H1.setInputProtected(false);
        scrnMsg.coaBrNm_H1.setInputProtected(true);
        scrnMsg.locTpCd_H1.setInputProtected(false);
        scrnMsg.whCd_H1.setInputProtected(false);

        scrnMsg.prntVndCd_L1.setInputProtected(false);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.prntVndCd.setInputProtected(false);
        // START 2019/04/09 T.Ogura [QC#28667,MOD]
//        scrnMsg.prntVndNm.setInputProtected(false);
        scrnMsg.prntVndNm.setInputProtected(true);
        // END   2019/04/09 T.Ogura [QC#28667,MOD]
        scrnMsg.vndCd_L1.setInputProtected(false);        // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.vndCd.setInputProtected(false);
        // START 2019/04/09 T.Ogura [QC#28667,MOD]
//        scrnMsg.vndNm.setInputProtected(false);
        scrnMsg.vndNm.setInputProtected(true);
        // END   2019/04/09 T.Ogura [QC#28667,MOD]

        // START 2019/04/09 T.Ogura [QC#28667,ADD]
        if (RTL_WH_CATG.DROP_RMA.equals(scrnMsg.rtlWhCatgCd_H1.getValue())) {
            scrnMsg.prntVndCd.setIndispensable(true);
            scrnMsg.vndCd.setIndispensable(true);
        } else {
            scrnMsg.prntVndCd.setIndispensable(false);
            scrnMsg.vndCd.setIndispensable(false);
        }
        // END   2019/04/09 T.Ogura [QC#28667,ADD]

        // Address
        scrnMsg.dsAcctNum_S1.setInputProtected(true);
        scrnMsg.dsAcctNm_S1.setInputProtected(true);
        scrnMsg.vndLocCd_S1.setInputProtected(false);
        scrnMsg.locNum_S1.setInputProtected(false);
        scrnMsg.shipToCustCd_S2.setInputProtected(false);
        if (isShipToAreaToBeActivated(scrnMsg)) {
            scrnMsg.dsLocNm_S1.setInputProtected(false);
            scrnMsg.addlLocNm_S1.setInputProtected(false);
            scrnMsg.firstLineAddr_S1.setInputProtected(false);
            scrnMsg.scdLineAddr_S1.setInputProtected(false);
            scrnMsg.thirdLineAddr_S1.setInputProtected(false);
            scrnMsg.frthLineAddr_S1.setInputProtected(false);
            scrnMsg.ctyAddr_S1.setInputProtected(false);
            scrnMsg.ctyAddr_S2.setInputProtected(false);
            scrnMsg.cntyPk_S1.setInputProtected(false);
            scrnMsg.cntyNm_S1.setInputProtected(false);
            scrnMsg.cntyNm_S2.setInputProtected(false);
            scrnMsg.stCd_S2.setInputProtected(false);
            scrnMsg.stCd_S1.setInputProtected(false);
            scrnMsg.provNm_S1.setInputProtected(false);
            scrnMsg.postCd_S1.setInputProtected(false);
            scrnMsg.postCd_S2.setInputProtected(false);
            scrnMsg.ctryCd_S1.setInputProtected(false);
            // START 2019/04/09 T.Ogura [QC#28667,MOD]
//            handler.setButtonEnabled("GetAddressShipTo", true);
            handler.setButtonEnabled(BTN_GET_SHIP_TO, true);
            // END   2019/04/09 T.Ogura [QC#28667,MOD]
        } else {
            scrnMsg.dsLocNm_S1.setInputProtected(true);
            scrnMsg.addlLocNm_S1.setInputProtected(true);
            scrnMsg.firstLineAddr_S1.setInputProtected(true);
            scrnMsg.scdLineAddr_S1.setInputProtected(true);
            scrnMsg.thirdLineAddr_S1.setInputProtected(true);
            scrnMsg.frthLineAddr_S1.setInputProtected(true);
            scrnMsg.ctyAddr_S1.setInputProtected(true);
            scrnMsg.ctyAddr_S2.setInputProtected(true);
            scrnMsg.cntyPk_S1.setInputProtected(true);
            scrnMsg.cntyNm_S1.setInputProtected(true);
            scrnMsg.cntyNm_S2.setInputProtected(true);
            scrnMsg.stCd_S2.setInputProtected(true);
            scrnMsg.stCd_S1.setInputProtected(true);
            scrnMsg.provNm_S1.setInputProtected(true);
            scrnMsg.postCd_S1.setInputProtected(true);
            scrnMsg.postCd_S2.setInputProtected(true);
            scrnMsg.ctryCd_S1.setInputProtected(true);
            // START 2019/04/09 T.Ogura [QC#28667,MOD]
//            handler.setButtonEnabled("GetAddressShipTo", false);
            handler.setButtonEnabled(BTN_GET_SHIP_TO, false);
            // END   2019/04/09 T.Ogura [QC#28667,MOD]
        }
        scrnMsg.poRcptRteTpCd_S1.setInputProtected(false);
        scrnMsg.rmaRcptRteTpCd_S1.setInputProtected(false);
        scrnMsg.xxChkBox_R1.setInputProtected(false);
        scrnMsg.dsAcctNum_R1.setInputProtected(true);
        scrnMsg.dsAcctNm_R1.setInputProtected(true);
        scrnMsg.vndLocCd_R1.setInputProtected(false);
        scrnMsg.locNum_R1.setInputProtected(false);
        scrnMsg.shipToCustCd_R2.setInputProtected(false);
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg_R1.getValue())) {
            scrnMsg.dsLocNm_R1.setInputProtected(true);
            scrnMsg.rtrnToAddlLocNm_R1.setInputProtected(true);
            scrnMsg.rtrnToFirstLineAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToScdLineAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToThirdLineAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToFrthLineAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToCtyAddr_R1.setInputProtected(true);
            scrnMsg.rtrnToCtyAddr_R2.setInputProtected(true);
            scrnMsg.cntyPk_R1.setInputProtected(true);
            scrnMsg.cntyNm_R1.setInputProtected(true);
            scrnMsg.cntyNm_R2.setInputProtected(true);
            scrnMsg.rtrnToStCd_R2.setInputProtected(true);
            scrnMsg.rtrnToStCd_R1.setInputProtected(true);
            scrnMsg.rtrnToProvNm_R1.setInputProtected(true);
            scrnMsg.rtrnToPostCd_R1.setInputProtected(true);
            scrnMsg.rtrnToPostCd_R2.setInputProtected(true);
            scrnMsg.ctryCd_R1.setInputProtected(true);
            // START 2019/04/09 T.Ogura [QC#28667,MOD]
//            handler.setButtonEnabled("GetAddressReturnTo", false);
            handler.setButtonEnabled(BTN_GET_RETURN_TO, false);
            // END   2019/04/09 T.Ogura [QC#28667,MOD]
        } else {
            scrnMsg.dsLocNm_R1.setInputProtected(false);
            scrnMsg.rtrnToAddlLocNm_R1.setInputProtected(false);
            scrnMsg.rtrnToFirstLineAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToScdLineAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToThirdLineAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToFrthLineAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToCtyAddr_R1.setInputProtected(false);
            scrnMsg.rtrnToCtyAddr_R2.setInputProtected(false);
            scrnMsg.cntyPk_R1.setInputProtected(false);
            scrnMsg.cntyNm_R1.setInputProtected(false);
            scrnMsg.cntyNm_R2.setInputProtected(false);
            scrnMsg.rtrnToStCd_R2.setInputProtected(false);
            scrnMsg.rtrnToStCd_R1.setInputProtected(false);
            scrnMsg.rtrnToProvNm_R1.setInputProtected(false);
            scrnMsg.rtrnToPostCd_R1.setInputProtected(false);
            scrnMsg.rtrnToPostCd_R2.setInputProtected(false);
            scrnMsg.ctryCd_R1.setInputProtected(false);
            // START 2019/04/09 T.Ogura [QC#28667,MOD]
//            handler.setButtonEnabled("GetAddressReturnTo", true);
            handler.setButtonEnabled(BTN_GET_RETURN_TO, true);
            // END   2019/04/09 T.Ogura [QC#28667,MOD]
        }

        // Sourcing
        scrnMsg.plnItemInsrcCd_S1.setInputProtected(false);
        scrnMsg.srcZnCd_S1.setInputProtected(false);

        cntrlSourcePullDownListForSourcingTab(scrnMsg);

        // SWH
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).rtlSwhCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).rtlSwhNm_A1.setInputProtected(false);
            scrnMsg.A.no(i).rtlSwhDescTxt_A1.setInputProtected(false);
            scrnMsg.A.no(i).xxChkBox_P1.setInputProtected(false);
            scrnMsg.A.no(i).xxChkBox_M1.setInputProtected(false);
            scrnMsg.A.no(i).xxScrItem8Txt_A1.setInputProtected(true);
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).rtlSwhMndFlg_A1.getValue())) {
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(false);
            }
        }
        cntrlSourcePullDownListForSWHTab(handler, scrnMsg);

        // button activation
        handler.setButtonEnabled(BTN_SEARCH, true);
        handler.setButtonEnabled(BTN_MGRNM, true);
        handler.setButtonEnabled(BTN_ALTOWNRNM, true);
        handler.setButtonEnabled(BTN_BRNM, true);             // 2019/04/09 T.Ogura [QC#28667,ADD]
        handler.setButtonEnabled(BTN_SUPPLIER, true);         // 2019/04/09 T.Ogura [QC#28667,ADD]
        handler.setButtonEnabled(BTN_SUPPLIER_SITE, true);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        handler.setButtonEnabled(BTN_SET_SHIP_TO, true);
        handler.setButtonEnabled(BTN_CLEAR_SHIP_TO, true);
        handler.setButtonEnabled(BTN_SET_RETURN_TO, true);
        handler.setButtonEnabled(BTN_CLEAR_RETURN_TO, true);

        // TAB protection
        scrnMsg.xxTabProt_A1.setInputProtected(true); // Address Tab
        scrnMsg.xxTabProt_A2.setInputProtected(true); // Account Tab
        scrnMsg.xxTabProt_S1.setInputProtected(true); // Sourcing Tab
        scrnMsg.xxTabProt_W1.setInputProtected(true); // SWH Tab

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);

        setAuthorityProtect(handler, scrnMsg, functionList);
        disableAllFieldsForVirtualWH(handler, scrnMsg);
        changeFormatDate(scrnMsg);
    }

    /**
     * Check if Ship-To input field should be activated or not.
     * @param rtlWhCdForSearch
     * @param locNumForShipTo
     * @return boolean (true:Should be activated, false:Not should be activated)
     */
    public static boolean isShipToAreaToBeActivated(NMAL6820BMsg scrnMsg) {
//        if (ZYPCommonFunc.hasValue(scrnMsg.whCd_H1.getValue())) {
//            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg_S1.getValue())) {
//                if (scrnMsg.whCd_H1.getValue().equals(scrnMsg.locNum_S1.getValue())) {
//                    return true;
//                } else {
//                    return false;
//                }
//            } else {
//                return true;
//            }
//
//        } else {
//            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg_S1.getValue())) {
//                return false;
//            } else {
//                return true;
//            }
//        }
        if (ZYPCommonFunc.hasValue(scrnMsg.whCd_H1.getValue())) {
            return false;
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg_S1.getValue())) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * The method explanation: The display control of the screen item for Sourcing Tab
     * for Create Mode
     * @param scrnMsg NMAL6820BMsg
     */
    public static void cntrlSourcePullDownListForSourcingTab(NMAL6820BMsg scrnMsg) {

        if (PROCR_TP.SUPPLIER.equals(scrnMsg.procrTpCd_S1.getValue()) || PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_S1.getValue())) {
            scrnMsg.procrTpCd_S1.setInputProtected(false);
            scrnMsg.procrTpCd_S2.setInputProtected(false);
            scrnMsg.prntVndNm_SD.setInputProtected(false);
            scrnMsg.vndNm_SD.setInputProtected(false);

        } else {
            scrnMsg.procrTpCd_S1.setInputProtected(false);
            scrnMsg.procrTpCd_S2.setInputProtected(true);
            scrnMsg.prntVndNm_SD.setInputProtected(true);
            scrnMsg.vndNm_SD.setInputProtected(true);
        }

        if (LOC_TP.TECHNICIAN.equals(scrnMsg.locTpCd_H1.getValue())) {
            if (PROCR_TP.SUPPLIER.equals(scrnMsg.procrTpCd_E1.getValue()) || PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_E1.getValue())) {
                scrnMsg.procrTpCd_E1.setInputProtected(false);
                scrnMsg.prntVndNm_SE.setInputProtected(false);
                scrnMsg.vndNm_SE.setInputProtected(false);
                scrnMsg.procrTpCd_E2.setInputProtected(false);

            } else {
                scrnMsg.procrTpCd_E1.setInputProtected(false);
                scrnMsg.procrTpCd_E2.setInputProtected(true);
                scrnMsg.prntVndNm_SE.setInputProtected(true);
                scrnMsg.vndNm_SE.setInputProtected(true);
            }
            // QC#22212
            scrnMsg.techMblMsgAddr.setInputProtected(false);
        } else {
            scrnMsg.procrTpCd_E1.setInputProtected(true);
            scrnMsg.procrTpCd_E2.setInputProtected(true);
            scrnMsg.prntVndNm_SE.setInputProtected(true);
            scrnMsg.vndNm_SE.setInputProtected(true);
            // QC#22212
            scrnMsg.techMblMsgAddr.setInputProtected(true);
        }

        if (PROCR_TP.SUPPLIER.equals(scrnMsg.procrTpCd_R1.getValue()) || PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_R1.getValue())) {
            scrnMsg.procrTpCd_R1.setInputProtected(false);
            scrnMsg.procrTpCd_R2.setInputProtected(false);
            scrnMsg.prntVndNm_SR.setInputProtected(false);
            scrnMsg.vndNm_SR.setInputProtected(false);

        } else {
            scrnMsg.procrTpCd_R1.setInputProtected(false);
            scrnMsg.procrTpCd_R2.setInputProtected(true);
            scrnMsg.prntVndNm_SR.setInputProtected(true);
            scrnMsg.vndNm_SR.setInputProtected(true);
        }
    }

    /**
     * The method explanation: Set the value blank for the screen item for Sourcing Tab
     * for Create Mode
     * @param scrnMsg NMAL6820BMsg
     */
    public static void clearSourcePullDownListForSourcingTab(NMAL6820BMsg scrnMsg) {
        if (!PROCR_TP.SUPPLIER.equals(scrnMsg.procrTpCd_S1.getValue()) && !PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_S1.getValue())) {
            scrnMsg.prntVndNm_SD.clear();
            scrnMsg.vndNm_SD.clear();
        }
        if (LOC_TP.TECHNICIAN.equals(scrnMsg.locTpCd_H1.getValue())) {
            if (!PROCR_TP.SUPPLIER.equals(scrnMsg.procrTpCd_E1.getValue()) && !PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_E1.getValue())) {
                scrnMsg.prntVndNm_SE.clear();
                scrnMsg.vndNm_SE.clear();
            }
        } else {
            scrnMsg.procrTpCd_E1.clear();
            scrnMsg.prntVndNm_SE.clear();
            scrnMsg.vndNm_SE.clear();
        }
        if (!PROCR_TP.SUPPLIER.equals(scrnMsg.procrTpCd_R1.getValue()) && !PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_R1.getValue())) {
            scrnMsg.prntVndNm_SR.clear();
            scrnMsg.vndNm_SR.clear();
        }
    }

    /**
     * The method explanation: The display control of the screen item for SWH Tab
     * for Create Mode
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6820BMsg
     */
    public static void cntrlSourcePullDownListForSWHTab(EZDCommonHandler handler, NMAL6820BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (PROCR_TP.SUPPLIER.equals(scrnMsg.A.no(i).procrTpCd_A1.getValue()) || PROCR_TP.WAREHOUSE.equals(scrnMsg.A.no(i).procrTpCd_A1.getValue())) {
                scrnMsg.A.no(i).prntVndNm_AS.setInputProtected(false);
                scrnMsg.A.no(i).vndNm_AS.setInputProtected(false);
                handler.setButtonEnabled(NMAL6820Constant.BTN_SOURCE_LOC, i, true);

            } else {
                scrnMsg.A.no(i).prntVndNm_AS.setInputProtected(true);
                scrnMsg.A.no(i).vndNm_AS.setInputProtected(true);
                handler.setButtonEnabled(NMAL6820Constant.BTN_SOURCE_LOC, i, false);
            }

            if (PROCR_TP.SUPPLIER.equals(scrnMsg.A.no(i).procrTpCd_A2.getValue()) || PROCR_TP.WAREHOUSE.equals(scrnMsg.A.no(i).procrTpCd_A2.getValue())) {
                scrnMsg.A.no(i).prntVndNm_AR.setInputProtected(false);
                scrnMsg.A.no(i).vndNm_AR.setInputProtected(false);
                handler.setButtonEnabled(NMAL6820Constant.BTN_RETURN_TO_LOC, i, true);

            } else {
                scrnMsg.A.no(i).prntVndNm_AR.setInputProtected(true);
                scrnMsg.A.no(i).vndNm_AR.setInputProtected(true);
                handler.setButtonEnabled(NMAL6820Constant.BTN_RETURN_TO_LOC, i, false);
            }
        }
    }

    /**
     * The method explanation: The display control of the screen item for SWH Tab
     * for Create Mode
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6820BMsg
     */
    public static void clearSourcePullDownListForSWHTab(NMAL6820BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!PROCR_TP.SUPPLIER.equals(scrnMsg.A.no(i).procrTpCd_A1.getValue()) && !PROCR_TP.WAREHOUSE.equals(scrnMsg.A.no(i).procrTpCd_A1.getValue())) {
                scrnMsg.A.no(i).prntVndNm_AS.clear();
                scrnMsg.A.no(i).vndNm_AS.clear();
            }
            if (!PROCR_TP.SUPPLIER.equals(scrnMsg.A.no(i).procrTpCd_A2.getValue()) && !PROCR_TP.WAREHOUSE.equals(scrnMsg.A.no(i).procrTpCd_A2.getValue())) {
                scrnMsg.A.no(i).prntVndNm_AR.clear();
                scrnMsg.A.no(i).vndNm_AR.clear();
            }
        }
    }

    /**
     * <pre>
     * The input scrnMsg is cleared. 
     * </pre>
     * @param scrnMsg NMAL6820BMsg
     */
    public static void clearScreenMsg(NMAL6820BMsg scrnMsg) {

        // Header
        scrnMsg.rtlWhCd_H1.clear();
        scrnMsg.rtlWhNm_H1.clear();
        scrnMsg.rtlWhDescTxt_H1.clear();
        scrnMsg.rtlWhCatgCd_H1.clear();
        scrnMsg.whMgrPsnCd_H1.clear();
        scrnMsg.fullPsnNm_H1.clear();
        scrnMsg.altPsnCd_H1.clear();
        scrnMsg.fullPsnNm_H2.clear();
        scrnMsg.telNum_H1.clear();
        scrnMsg.faxNum_H1.clear();
        scrnMsg.invtyAcctCd_H1.clear();
        scrnMsg.invtyOwnrCd_H1.clear();
        scrnMsg.whOwnrCd_H1.clear();
        scrnMsg.effFromDt_H1.clear();
        scrnMsg.effThruDt_H1.clear();
        scrnMsg.whSysTpCd_H1.clear();
        scrnMsg.wmsWhCd_H1.clear();
        scrnMsg.physWhCd_H1.clear();
        scrnMsg.autoSoDropFlg_H1.clear();
        scrnMsg.firstRefCmntTxt_H1.clear();
        scrnMsg.whStartTm_H1.clear();
        scrnMsg.xxScrItem7Txt_ST.clear();
        scrnMsg.whEndTm_H1.clear();
        scrnMsg.xxScrItem7Txt_ET.clear();
        scrnMsg.whCutOffTm_H1.clear();
        scrnMsg.xxScrItem7Txt_CT.clear();
        // START 2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.fedexCutOffTm_H1.clear();
        scrnMsg.xxScrItem7Txt_FT.clear();
        scrnMsg.upsCutOffTm_H1.clear();
        scrnMsg.xxScrItem7Txt_UT.clear();        
        // END   2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.tmZoneCd_H1.clear();
        scrnMsg.geoCd_H1.clear();
        scrnMsg.carrCd_H1.clear();
        scrnMsg.carrNm_H1.clear();
        scrnMsg.coaBrCd_H1.clear();
        scrnMsg.coaBrNm_H1.clear();
        scrnMsg.locTpCd_H1.clear();
        scrnMsg.whCd_H1.clear();

        scrnMsg.prntVndCd.clear();
        scrnMsg.prntVndNm.clear();
        scrnMsg.vndCd.clear();
        scrnMsg.vndNm.clear();

        
        // Address
        scrnMsg.xxSetFlg_S1.clear();
        scrnMsg.locNum_S1.clear();
        scrnMsg.dsAcctNum_S1.clear();
        scrnMsg.dsAcctNm_S1.clear();
        scrnMsg.dsLocNm_S1.clear();
        scrnMsg.addlLocNm_S1.clear();
        scrnMsg.firstLineAddr_S1.clear();
        scrnMsg.scdLineAddr_S1.clear();
        scrnMsg.thirdLineAddr_S1.clear();
        scrnMsg.frthLineAddr_S1.clear();
        scrnMsg.ctyAddr_S1.clear();
        scrnMsg.cntyPk_S1.clear();
        scrnMsg.stCd_S2.clear();
        scrnMsg.stCd_S1.clear();
        scrnMsg.provNm_S1.clear();
        scrnMsg.postCd_S1.clear();
        scrnMsg.ctryCd_S1.clear();
        scrnMsg.vndLocCd_S1.clear();
        scrnMsg.poRcptRteTpCd_S1.clear();
        scrnMsg.rmaRcptRteTpCd_S1.clear();
        scrnMsg.xxChkBox_R1.clear();
        scrnMsg.xxSetFlg_R1.clear();
        scrnMsg.locNum_R1.clear();
        scrnMsg.dsAcctNum_R1.clear();
        scrnMsg.dsAcctNm_R1.clear();
        scrnMsg.dsLocNm_R1.clear();
        scrnMsg.rtrnToAddlLocNm_R1.clear();
        scrnMsg.rtrnToFirstLineAddr_R1.clear();
        scrnMsg.rtrnToScdLineAddr_R1.clear();
        scrnMsg.rtrnToThirdLineAddr_R1.clear();
        scrnMsg.rtrnToFrthLineAddr_R1.clear();
        scrnMsg.rtrnToCtyAddr_R1.clear();
        scrnMsg.cntyPk_R1.clear();
        scrnMsg.rtrnToStCd_R2.clear();
        scrnMsg.rtrnToStCd_R1.clear();
        scrnMsg.rtrnToProvNm_R1.clear();
        scrnMsg.rtrnToPostCd_R1.clear();
        scrnMsg.ctryCd_R1.clear();
        scrnMsg.vndLocCd_R1.clear();

        // Sourcing
        scrnMsg.plnItemInsrcCd_S1.clear();
        scrnMsg.srcZnCd_S1.clear();
        scrnMsg.procrTpCd_S1.clear();
        scrnMsg.procrTpCd_S2.clear();
        scrnMsg.prntVndNm_SD.clear();
        scrnMsg.vndNm_SD.clear();
//        scrnMsg.invtyLocCd_S1.clear();
        scrnMsg.procrTpCd_E1.clear();
        scrnMsg.procrTpCd_E2.clear();
        scrnMsg.prntVndNm_SE.clear();
        scrnMsg.vndNm_SE.clear();
//        scrnMsg.invtyLocCd_E1.clear();
        scrnMsg.procrTpCd_R1.clear();
        scrnMsg.procrTpCd_R2.clear();
        scrnMsg.prntVndNm_SR.clear();
        scrnMsg.vndNm_SR.clear();
//        scrnMsg.invtyLocCd_R1.clear();

        // SWH
        ZYPTableUtil.clear(scrnMsg.A);
    }

    /**
     * <pre>
     * The input scrnMsg is cleared. 
     * </pre>
     * @param scrnMsg NMAL6820BMsg
     */
    public static void clearScreenMsgForReset(NMAL6820BMsg scrnMsg) {

        // Header
        scrnMsg.rtlWhCd_H1.clear();
        scrnMsg.rtlWhNm_H1.clear();
        scrnMsg.rtlWhDescTxt_H1.clear();
        scrnMsg.rtlWhCatgCd_H1.clear();
        scrnMsg.whMgrPsnCd_H1.clear();
        scrnMsg.fullPsnNm_H1.clear();
        scrnMsg.altPsnCd_H1.clear();
        scrnMsg.fullPsnNm_H2.clear();
        scrnMsg.telNum_H1.clear();
        scrnMsg.faxNum_H1.clear();
        scrnMsg.invtyAcctCd_H1.clear();
        scrnMsg.invtyOwnrCd_H1.clear();
        scrnMsg.whOwnrCd_H1.clear();
        scrnMsg.effFromDt_H1.clear();
        scrnMsg.effThruDt_H1.clear();
        scrnMsg.whSysTpCd_H1.clear();
        scrnMsg.wmsWhCd_H1.clear();
        scrnMsg.physWhCd_H1.clear();
        scrnMsg.autoSoDropFlg_H1.clear();
        scrnMsg.firstRefCmntTxt_H1.clear();
        scrnMsg.whStartTm_H1.clear();
        scrnMsg.xxScrItem7Txt_ST.clear();
        scrnMsg.whEndTm_H1.clear();
        scrnMsg.xxScrItem7Txt_ET.clear();
        scrnMsg.whCutOffTm_H1.clear();
        scrnMsg.xxScrItem7Txt_CT.clear();
        // START 2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.fedexCutOffTm_H1.clear();
        scrnMsg.xxScrItem7Txt_FT.clear();
        scrnMsg.upsCutOffTm_H1.clear();
        scrnMsg.xxScrItem7Txt_UT.clear();
        // END   2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.tmZoneCd_H1.clear();
        scrnMsg.geoCd_H1.clear();
        scrnMsg.carrCd_H1.clear();
        scrnMsg.carrNm_H1.clear();
        scrnMsg.coaBrCd_H1.clear();
        scrnMsg.coaBrNm_H1.clear();
        scrnMsg.locTpCd_H1.clear();
        // scrnMsg.whCd_H1.clear();

        // Address
        scrnMsg.xxSetFlg_S1.clear();
        scrnMsg.locNum_S1.clear();
        scrnMsg.dsAcctNum_S1.clear();
        scrnMsg.dsAcctNm_S1.clear();
        scrnMsg.dsLocNm_S1.clear();
        scrnMsg.addlLocNm_S1.clear();
        scrnMsg.firstLineAddr_S1.clear();
        scrnMsg.scdLineAddr_S1.clear();
        scrnMsg.thirdLineAddr_S1.clear();
        scrnMsg.frthLineAddr_S1.clear();
        scrnMsg.ctyAddr_S1.clear();
        scrnMsg.cntyPk_S1.clear();
        scrnMsg.stCd_S2.clear();
        scrnMsg.stCd_S1.clear();
        scrnMsg.provNm_S1.clear();
        scrnMsg.postCd_S1.clear();
        scrnMsg.ctryCd_S1.clear();
        scrnMsg.vndLocCd_S1.clear();
        scrnMsg.poRcptRteTpCd_S1.clear();
        scrnMsg.rmaRcptRteTpCd_S1.clear();
        scrnMsg.xxChkBox_R1.clear();
        scrnMsg.xxSetFlg_R1.clear();
        scrnMsg.locNum_R1.clear();
        scrnMsg.dsAcctNum_R1.clear();
        scrnMsg.dsAcctNm_R1.clear();
        scrnMsg.dsLocNm_R1.clear();
        scrnMsg.rtrnToAddlLocNm_R1.clear();
        scrnMsg.rtrnToFirstLineAddr_R1.clear();
        scrnMsg.rtrnToScdLineAddr_R1.clear();
        scrnMsg.rtrnToThirdLineAddr_R1.clear();
        scrnMsg.rtrnToFrthLineAddr_R1.clear();
        scrnMsg.rtrnToCtyAddr_R1.clear();
        scrnMsg.cntyPk_R1.clear();
        scrnMsg.rtrnToStCd_R2.clear();
        scrnMsg.rtrnToStCd_R1.clear();
        scrnMsg.rtrnToProvNm_R1.clear();
        scrnMsg.rtrnToPostCd_R1.clear();
        scrnMsg.ctryCd_R1.clear();
        scrnMsg.vndLocCd_R1.clear();

        // Sourcing
        scrnMsg.plnItemInsrcCd_S1.clear();
        scrnMsg.srcZnCd_S1.clear();
        scrnMsg.procrTpCd_S1.clear();
        scrnMsg.procrTpCd_S2.clear();
        scrnMsg.prntVndNm_SD.clear();
        scrnMsg.vndNm_SD.clear();
        scrnMsg.procrTpCd_E1.clear();
        scrnMsg.procrTpCd_E2.clear();
        scrnMsg.prntVndNm_SE.clear();
        scrnMsg.vndNm_SE.clear();
        scrnMsg.procrTpCd_R1.clear();
        scrnMsg.procrTpCd_R2.clear();
        scrnMsg.prntVndNm_SR.clear();
        scrnMsg.vndNm_SR.clear();

        // SWH
        ZYPTableUtil.clear(scrnMsg.A);
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForWhPopup(NMAL6820BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.rtlWhCd_G1.clear();
        scrnMsg.rtlWhNm_G1.clear();

        // warehouse Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_WH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_WH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_WH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_WH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_WH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_WH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_WH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_WH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_WH);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @return Object[]
     */
    public static Object[] setParamForWhPopup(NMAL6820BMsg scrnMsg) {

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
        params[9] = scrnMsg.rtlWhCd_G1;
        params[10] = scrnMsg.rtlWhNm_G1;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForTechTocPopup(NMAL6820BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.techTocCd_G1.clear();
        scrnMsg.techNm_G1.clear();

        // technician Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_TECH);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_TECH);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @return Object[]
     */
    public static Object[] setParamForTechTocPopup(NMAL6820BMsg scrnMsg) {

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
        params[9] = scrnMsg.techTocCd_G1;
        params[10] = scrnMsg.techNm_G1;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForAltOwnrPopup(NMAL6820BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.fullPsnNm_G1.clear();

        // technician Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_ALT_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_ALT_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_ALT_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_ALT_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_ALT_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_ALT_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_ALT_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_ALT_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_ALT_OWNER);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @return Object[]
     */
    public static Object[] setParamForAltOwnrPopup(NMAL6820BMsg scrnMsg) {

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
        params[9] = scrnMsg.altPsnCd_H1;
        params[10] = scrnMsg.fullPsnNm_G1;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForBrPopup(NMAL6820BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.coaBrNm_G1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_BR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_BR);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @return Object[]
     */
    public static Object[] setParamForBrPopup(NMAL6820BMsg scrnMsg) {

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
        params[9] = scrnMsg.coaBrCd_H1;
        params[10] = scrnMsg.coaBrNm_G1;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForMgrPopup(NMAL6820BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.fullPsnNm_G1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_OWNER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_OWNER);

        if (LOC_TP.TECHNICIAN.equals(scrnMsg.locTpCd_H1.getValue())) {
            // technician Code is set to subscreen delivery information.
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_TECH);
        } else {
            // Manager Code is set to subscreen delivery information.
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_OWNER);
        }
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @return Object[]
     */
    public static Object[] setParamForMgrPopup(NMAL6820BMsg scrnMsg) {

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
        params[9] = scrnMsg.whMgrPsnCd_H1;
        params[10] = scrnMsg.fullPsnNm_G1;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForCrrPopup(NMAL6820BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.carrCd_G1.clear();
        scrnMsg.carrNm_G1.clear();

        // Manager Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_CRR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_CRR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_CRR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_CRR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_CRR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_CRR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_CRR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_CRR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_CRR);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @return Object[]
     */
    public static Object[] setParamForCrrPopup(NMAL6820BMsg scrnMsg) {

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
        params[9] = scrnMsg.carrCd_G1;
        params[10] = scrnMsg.carrNm_G1;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForShipToStPopup(NMAL6820BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.stCd_G1.clear();
        scrnMsg.stNm_G1.clear();

        // State Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_SHIP_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_SHIP_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_SHIP_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_SHIP_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_SHIP_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_SHIP_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_SHIP_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_SHIP_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_SHIP_TO_ST);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @return Object[]
     */
    public static Object[] setParamForShipToStPopup(NMAL6820BMsg scrnMsg) {

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
        params[9] = scrnMsg.stCd_G1;
        params[10] = scrnMsg.stNm_G1;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForReturnToStPopup(NMAL6820BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.stCd_G2.clear();
        scrnMsg.stNm_G2.clear();

        // State Code is set to subscreen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_RETURN_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_RETURN_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_RETURN_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_RETURN_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_RETURN_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_RETURN_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_RETURN_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_RETURN_TO_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_RETURN_TO_ST);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @return Object[]
     */
    public static Object[] setParamForReturnToStPopup(NMAL6820BMsg scrnMsg) {

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
        params[9] = scrnMsg.stCd_G2;
        params[10] = scrnMsg.stNm_G2;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForLocationPopup(NMAL6820BMsg scrnMsg, String setLocRoleTpFlag) {

        scrnMsg.invtyLocCd_P1.clear();
        scrnMsg.invtyLocCd_P2.clear();
        scrnMsg.invtyLocCd_P3.clear();
        scrnMsg.invtyLocNm_P1.clear();
        scrnMsg.xxLocRoleTpCdListTxt_P1.clear();
        scrnMsg.xxChkInpValFlg_P1.clear();
        scrnMsg.xxSelFlg_P1.clear();
        scrnMsg.locRoleTpCd_P1.clear();
        scrnMsg.rtlWhCd_P1.clear();
        scrnMsg.rtlWhNm_P1.clear();
        scrnMsg.rtlSwhCd_P1.clear();
        scrnMsg.rtlSwhNm_P1.clear();
        scrnMsg.xxChkBox_P2.clear();
        scrnMsg.invtyAcctCd_P1.clear();
        scrnMsg.invtyOwnrCd_P1.clear();
        scrnMsg.whOwnrCd_P1.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();

        // Location Parameters are set to subscreen delivery
        // information.
        if (ZYPConstant.FLG_ON_Y.equals(setLocRoleTpFlag)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxLocRoleTpCdListTxt_P1, NMXC100001EnableWH.getLocRoleTpForPopup(scrnMsg.glblCmpyCd_G1.getValue(), BIZ_APP_ID));
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkInpValFlg_P1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_P1, ZYPConstant.FLG_ON_Y);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @param rtlWhCd EZDBStringItem
     * @param rtlWhNm EZDBStringItem
     * @param rtlSwhNm EZDBStringItem
     * @param onlyRtlWhLvlFlg String
     * @return Object[]
     */
    public static Object[] setParamForLocationPopup(NMAL6820BMsg scrnMsg, EZDBStringItem rtlWhCd, EZDBStringItem rtlWhNm, EZDBStringItem rtlSwhNm, String onlyRtlWhLvlFlg) {

        if (ZYPCommonFunc.hasValue(rtlWhCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_P1, rtlWhCd);
        }
        if (ZYPCommonFunc.hasValue(rtlWhNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_P1, rtlWhNm);
        }
        if (ZYPCommonFunc.hasValue(rtlSwhNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm_P1, rtlSwhNm);
        }
        if (ZYPCommonFunc.hasValue(onlyRtlWhLvlFlg)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_P2, onlyRtlWhLvlFlg);
        }

        Object[] params = new Object[20];

        params[0] = scrnMsg.invtyLocCd_P2;
        params[1] = scrnMsg.invtyLocNm_P1;
        params[2] = scrnMsg.xxLocRoleTpCdListTxt_P1;
        params[3] = scrnMsg.xxChkInpValFlg_P1;
        params[4] = scrnMsg.xxSelFlg_P1;
        params[5] = scrnMsg.locRoleTpCd_P1;
        params[6] = scrnMsg.rtlWhCd_P1;
        params[7] = scrnMsg.rtlWhNm_P1;
        params[8] = scrnMsg.rtlSwhCd_P1;
        params[9] = scrnMsg.rtlSwhNm_P1;
        params[10] = scrnMsg.xxChkBox_P2;
        params[11] = scrnMsg.invtyAcctCd_P1;
        params[12] = scrnMsg.invtyOwnrCd_P1;
        params[13] = scrnMsg.whOwnrCd_P1;
        params[16] = scrnMsg.xxPopPrm_17;
        params[17] = scrnMsg.xxPopPrm_18;
        params[18] = scrnMsg.xxPopPrm_19;
        params[19] = scrnMsg.xxPopPrm_20;

        return params;
    }


    /**
     * The method explanation: set parameter to call common popup(NWAL1130).
     * @param scrnMsg NMAL6820BMsg
     * @param prntVndNm String
     * @param vndNm String
     * @return Object[]
     */
    public static Object[] setParamForSupplierPopup(NMAL6820BMsg scrnMsg, String prntVndNm, String vndNm) {
        Object[] params = new Object[7];

        params[0] = "";
        params[1] = "Supplier/Supplier Site Search";
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" V.EZCANCELFLAG,");
        sb.append(" V.GLBL_CMPY_CD,");
        sb.append(" V.VND_CD,");
        sb.append(" V.LOC_NM,");
        sb.append(" V.ARCS_SPLY_SITE_CD,");
        sb.append(" V.INAC_DT,");
        sb.append(" PV.PRNT_VND_CD,");
        sb.append(" PV.PRNT_VND_NM,");
        sb.append(" PV.SPLY_TP_CD,");
        sb.append(" PV.PRNT_VND_TP_CD");
        sb.append(" FROM PRNT_VND PV,");
        sb.append(" VND V,");
        sb.append(" VND_TP_RELN VTR");
        sb.append(" WHERE V.VND_CD = VTR.VND_CD");
        sb.append(" AND V.GLBL_CMPY_CD = VTR.GLBL_CMPY_CD");
        sb.append(" AND V.EZCANCELFLAG = VTR.EZCANCELFLAG");
        sb.append(" AND VTR.VND_TP_CD = '01'");
        sb.append(" AND V.RGTN_STS_CD = 'P20'");
        sb.append(" AND V.EZCANCELFLAG = '0'");
        sb.append(" AND V.EFF_THRU_DT >= TO_CHAR (SYSDATE, 'YYYYMMDD')");
        sb.append(" AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD");
        sb.append(" AND V.EZCANCELFLAG = PV.EZCANCELFLAG");
        sb.append(" AND V.PRNT_VND_PK = PV.PRNT_VND_PK");
        sb.append(" AND (PV.INAC_DT IS NULL");
        sb.append(" OR PV.INAC_DT > TO_CHAR (SYSDATE, 'YYYYMMDD'))");
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Supplier Code";
        whereArray1[1] = "PRNT_VND_CD";
        whereArray1[2] = "";
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Supplier Name";
        whereArray2[1] = "PRNT_VND_NM";
        whereArray2[2] = prntVndNm;
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Supplier Site Code";
        whereArray3[1] = "VND_CD";
        whereArray3[2] = "";
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[4];
        whereArray4[0] = "Supplier Site Name";
        whereArray4[1] = "LOC_NM";
        whereArray4[2] = vndNm;
        whereArray4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Supplier Code";
        columnArray1[1] = "PRNT_VND_CD";
        columnArray1[2] = BigDecimal.valueOf(10);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Supplier Name";
        columnArray2[1] = "PRNT_VND_NM";
        columnArray2[2] = BigDecimal.valueOf(30);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Supplier Site Code";
        columnArray3[1] = "VND_CD";
        columnArray3[2] = BigDecimal.valueOf(15);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Supplier Site Name";
        columnArray4[1] = "LOC_NM";
        columnArray4[2] = BigDecimal.valueOf(30);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "PRNT_VND_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "VND_CD";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        params[5] = sortConditionList;
        params[6] = scrnMsg.P;

        return params;
    }

    /**
     * The method explanation: check for Postal Code Format
     * @param scrnMsg NMAL6820BMsg
     */
    public static void checkPostCd(NMAL6820BMsg scrnMsg) {

        if (!CTRY.UNITED_STATES_OF_AMERICA.equals(scrnMsg.ctryCd_S1.getValue())) {
            return;
        }

        String shipToPostCd = scrnMsg.postCd_S1.getValue();

        if (ZYPCommonFunc.hasValue(shipToPostCd)) {

            checkPostalFormat(shipToPostCd, scrnMsg.postCd_S1, scrnMsg.xxDplyTab, TAB_ADDR);
            scrnMsg.addCheckItem(scrnMsg.postCd_S1);
        }

        if (!CTRY.UNITED_STATES_OF_AMERICA.equals(scrnMsg.ctryCd_R1.getValue())) {
            return;
        }
        String rtrnToPostCd = scrnMsg.rtrnToPostCd_R1.getValue();

        if (ZYPCommonFunc.hasValue(rtrnToPostCd)) {

            checkPostalFormat(rtrnToPostCd, scrnMsg.rtrnToPostCd_R1, scrnMsg.xxDplyTab, TAB_ADDR);
            scrnMsg.addCheckItem(scrnMsg.rtrnToPostCd_R1);
        }
    }

    /**
     * The method explanation: check for Postal Code Format
     * @param postCd String
     * @param targetField EZDBStringItem
     * @param displayTab EZDBStringItem
     * @param tabNm String
     */
    public static void checkPostalFormat(String postCd, EZDBStringItem targetField, EZDBStringItem displayTab, String tabNm) {

        if (postCd.length() != 5 && postCd.length() != 10) {

            targetField.setErrorInfo(1, NMAM8075E, new String[] {ZIP_CODE_FORMAT });
            ZYPEZDItemValueSetter.setValue(displayTab, tabNm);
        } else if (postCd.length() == 10) {

            if (!HYPHEN.equals(postCd.substring(5, 6))) {

                targetField.setErrorInfo(1, NMAM8075E, new String[] {ZIP_CODE_FORMAT });
                ZYPEZDItemValueSetter.setValue(displayTab, tabNm);
            }
        } else if (postCd.length() == 5) {

            try {

                new BigDecimal(String.valueOf(postCd.substring(0, 5)));
            } catch (Exception e) {

                targetField.setErrorInfo(1, NMAM8075E, new String[] {ZIP_CODE_FORMAT });
                ZYPEZDItemValueSetter.setValue(displayTab, tabNm);
            }
        }
    }

    /**
     * Sets the authority protect.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL1010BMsg
     * @param functionList List<String>
     */
    private static void setAuthorityProtect(EZDCommonHandler handler, NMAL6820BMsg scrnMsg, List<String> functionList) {

        if (hasRegisterAuthority(functionList)) {
            return;
        }
        disableAllFields(handler, scrnMsg);
    }

    /**
     * Disable all fields for virtual WH
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL1010BMsg
     */
    private static void disableAllFieldsForVirtualWH(EZDCommonHandler handler, NMAL6820BMsg scrnMsg) {

        if (!RTL_WH_CATG.VIRTUAL.equals(scrnMsg.rtlWhCatgCd_H1.getValue())) {
            return;
        }
        disableAllFields(handler, scrnMsg);
        if (MODE_CREATE.equals(scrnMsg.xxModeCd_G1.getValue())) {
            scrnMsg.rtlWhCatgCd_H1.setInputProtected(false);
        }
    }

    /**
     * Disable all fields.
     * @param handler EZDCommonHandler
     * @param scrnMsg NLCL1010BMsg
     * @param functionList List<String>
     */
    private static void disableAllFields(EZDCommonHandler handler, NMAL6820BMsg scrnMsg) {

        // Header
        scrnMsg.rtlWhCd_H2.setInputProtected(false);
        scrnMsg.rtlWhCd_H1.setInputProtected(false);
        scrnMsg.rtlWhNm_H1.setInputProtected(true);
        scrnMsg.rtlWhDescTxt_H1.setInputProtected(true);
        scrnMsg.rtlWhCatgCd_H1.setInputProtected(true);
        scrnMsg.whMgrPsnCd_H2.setInputProtected(true);
        scrnMsg.whMgrPsnCd_H1.setInputProtected(true);
        scrnMsg.fullPsnNm_H1.setInputProtected(true);
        scrnMsg.altPsnCd_H2.setInputProtected(true);
        scrnMsg.altPsnCd_H1.setInputProtected(true);
        scrnMsg.fullPsnNm_H2.setInputProtected(true);
        scrnMsg.telNum_H1.setInputProtected(true);
        scrnMsg.faxNum_H1.setInputProtected(true);
        scrnMsg.invtyAcctCd_H1.setInputProtected(true);
        scrnMsg.invtyOwnrCd_H1.setInputProtected(true);
        scrnMsg.whOwnrCd_H1.setInputProtected(true);
        scrnMsg.effFromDt_H1.setInputProtected(true);
        scrnMsg.effThruDt_H1.setInputProtected(true);
        scrnMsg.whSysTpCd_H1.setInputProtected(true);
        scrnMsg.wmsWhCd_H1.setInputProtected(true);
        scrnMsg.physWhCd_H1.setInputProtected(true);
        scrnMsg.autoSoDropFlg_H1.setInputProtected(true);
        scrnMsg.firstRefCmntTxt_H1.setInputProtected(true);
        scrnMsg.xxScrItem7Txt_ST.setInputProtected(true);
        scrnMsg.xxScrItem7Txt_ET.setInputProtected(true);
        scrnMsg.xxScrItem7Txt_CT.setInputProtected(true);
        // START 2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.xxScrItem7Txt_FT.setInputProtected(true);
        scrnMsg.xxScrItem7Txt_UT.setInputProtected(true);
        // END   2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.tmZoneCd_H1.setInputProtected(true);
        scrnMsg.geoCd_H2.setInputProtected(true);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.geoCd_H1.setInputProtected(true);
        scrnMsg.carrCd_H2.setInputProtected(true);
        scrnMsg.carrCd_H1.setInputProtected(true);
        scrnMsg.carrNm_H1.setInputProtected(true);
        scrnMsg.coaBrCd_H2.setInputProtected(true);
        scrnMsg.coaBrCd_H1.setInputProtected(true);
        scrnMsg.coaBrNm_H1.setInputProtected(true);
        scrnMsg.locTpCd_H1.setInputProtected(true);
        scrnMsg.whCd_H1.setInputProtected(true);

        // START 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.prntVndCd_L1.setInputProtected(true);
        scrnMsg.prntVndCd.setInputProtected(true);
        scrnMsg.prntVndNm.setInputProtected(true);
        scrnMsg.vndCd_L1.setInputProtected(true);
        scrnMsg.vndCd.setInputProtected(true);
        scrnMsg.vndNm.setInputProtected(true);
        // END   2019/04/09 T.Ogura [QC#28667,ADD]

        // Address
        scrnMsg.locNum_S1.setInputProtected(true);
        scrnMsg.shipToCustCd_S2.setInputProtected(true);
        scrnMsg.dsAcctNum_S1.setInputProtected(true);
        scrnMsg.dsAcctNm_S1.setInputProtected(true);
        scrnMsg.vndLocCd_S1.setInputProtected(true);
        scrnMsg.dsLocNm_S1.setInputProtected(true);
        scrnMsg.addlLocNm_S1.setInputProtected(true);
        scrnMsg.firstLineAddr_S1.setInputProtected(true);
        scrnMsg.scdLineAddr_S1.setInputProtected(true);
        scrnMsg.thirdLineAddr_S1.setInputProtected(true);
        scrnMsg.frthLineAddr_S1.setInputProtected(true);
        scrnMsg.ctyAddr_S2.setInputProtected(true);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.ctyAddr_S1.setInputProtected(true);
        // START 2019/04/09 T.Ogura [QC#28667,MOD]
//        scrnMsg.cntyPk_S1.setInputProtected(true);
        scrnMsg.cntyNm_S2.setInputProtected(true);
        scrnMsg.cntyNm_S1.setInputProtected(true);
        // END   2019/04/09 T.Ogura [QC#28667,MOD]
        scrnMsg.stCd_S2.setInputProtected(true);
        scrnMsg.stCd_S1.setInputProtected(true);
        scrnMsg.provNm_S1.setInputProtected(true);
        scrnMsg.postCd_S2.setInputProtected(true);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.postCd_S1.setInputProtected(true);
        scrnMsg.ctryCd_S1.setInputProtected(true);
        scrnMsg.poRcptRteTpCd_S1.setInputProtected(true);
        scrnMsg.rmaRcptRteTpCd_S1.setInputProtected(true);
        scrnMsg.xxChkBox_R1.setInputProtected(true);
        scrnMsg.locNum_R1.setInputProtected(true);
        scrnMsg.shipToCustCd_R2.setInputProtected(true);
        scrnMsg.dsAcctNum_R1.setInputProtected(true);
        scrnMsg.dsAcctNm_R1.setInputProtected(true);
        scrnMsg.vndLocCd_R1.setInputProtected(true);
        scrnMsg.dsLocNm_R1.setInputProtected(true);
        scrnMsg.rtrnToAddlLocNm_R1.setInputProtected(true);
        scrnMsg.rtrnToFirstLineAddr_R1.setInputProtected(true);
        scrnMsg.rtrnToScdLineAddr_R1.setInputProtected(true);
        scrnMsg.rtrnToThirdLineAddr_R1.setInputProtected(true);
        scrnMsg.rtrnToFrthLineAddr_R1.setInputProtected(true);
        scrnMsg.rtrnToCtyAddr_R2.setInputProtected(true);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.rtrnToCtyAddr_R1.setInputProtected(true);
        // START 2019/04/09 T.Ogura [QC#28667,MOD]
//        scrnMsg.cntyPk_R1.setInputProtected(true);
        scrnMsg.cntyNm_R2.setInputProtected(true);
        scrnMsg.cntyNm_R1.setInputProtected(true);
        // END   2019/04/09 T.Ogura [QC#28667,MOD]
        scrnMsg.rtrnToStCd_R2.setInputProtected(true);
        scrnMsg.rtrnToStCd_R1.setInputProtected(true);
        scrnMsg.rtrnToProvNm_R1.setInputProtected(true);
        scrnMsg.rtrnToPostCd_R2.setInputProtected(true);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.rtrnToPostCd_R1.setInputProtected(true);
        scrnMsg.ctryCd_R1.setInputProtected(true);

        // Sourcing
        scrnMsg.srcZnCd_S1.setInputProtected(true);
        scrnMsg.procrTpCd_S1.setInputProtected(true);
        scrnMsg.procrTpCd_S2.setInputProtected(true);
        scrnMsg.prntVndNm_SD.setInputProtected(true);
        scrnMsg.vndNm_SD.setInputProtected(true);
//        scrnMsg.invtyLocCd_S1.setInputProtected(true);
        scrnMsg.procrTpCd_E1.setInputProtected(true);
        scrnMsg.procrTpCd_E2.setInputProtected(true);
        scrnMsg.prntVndNm_SE.setInputProtected(true);
        scrnMsg.vndNm_SE.setInputProtected(true);
//        scrnMsg.invtyLocCd_E1.setInputProtected(true);
        scrnMsg.procrTpCd_R1.setInputProtected(true);
        scrnMsg.procrTpCd_R2.setInputProtected(true);
        scrnMsg.prntVndNm_SR.setInputProtected(true);
        scrnMsg.vndNm_SR.setInputProtected(true);
//        scrnMsg.invtyLocCd_R1.setInputProtected(true);

        // SWH
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            scrnMsg.A.no(i).xxChkBox_P1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_M1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);

            scrnMsg.A.no(i).procrTpCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).prntVndNm_AS.setInputProtected(true);
            scrnMsg.A.no(i).vndNm_AS.setInputProtected(true);
            handler.setButtonEnabled(NMAL6820Constant.BTN_SOURCE_LOC, i, false);

            scrnMsg.A.no(i).procrTpCd_A2.setInputProtected(true);
            scrnMsg.A.no(i).prntVndNm_AR.setInputProtected(true);
            scrnMsg.A.no(i).vndNm_AR.setInputProtected(true);
            handler.setButtonEnabled(NMAL6820Constant.BTN_RETURN_TO_LOC, i, false);
        }

        handler.setButtonEnabled(BTN_MGRNM, false);
        handler.setButtonEnabled(BTN_ALTOWNRNM, false);
        handler.setButtonEnabled(BTN_BRNM, false);
        handler.setButtonEnabled(BTN_SUPPLIER, false);         // 2019/04/09 T.Ogura [QC#28667,ADD]
        handler.setButtonEnabled(BTN_SUPPLIER_SITE, false);    // 2019/04/09 T.Ogura [QC#28667,ADD]
        handler.setButtonEnabled(BTN_SET_SHIP_TO, false);
        handler.setButtonEnabled(BTN_CLEAR_SHIP_TO, false);
        handler.setButtonEnabled(BTN_GET_SHIP_TO, false);      // 2019/04/09 T.Ogura [QC#28667,ADD]
        handler.setButtonEnabled(BTN_SET_RETURN_TO, false);
        handler.setButtonEnabled(BTN_CLEAR_RETURN_TO, false);
        handler.setButtonEnabled(BTN_GET_RETURN_TO, false);    // 2019/04/09 T.Ogura [QC#28667,ADD]

        // common button protection
        // 0 : inactive
        // 1 : active
        handler.setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    public static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_EDIT.equals(function)) {
                return true;
            }
        }
        return false;
    }

    public static void checkDate(NMAL6820BMsg scrnMsg) {
        String regex = "([0-1][0-9]|[2][0-3])(:)([0-5][0-9])";
        Pattern p = Pattern.compile(regex);

        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem7Txt_ST)) {
            Matcher m = p.matcher(scrnMsg.xxScrItem7Txt_ST.getValue());
            if (m.find()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.whStartTm_H1, scrnMsg.xxScrItem7Txt_ST.getValue().replace(":", ""));
            } else {
                // 2020/09/17 J.Evangelista [QC#57659,MOD]
                scrnMsg.xxScrItem7Txt_ST.setErrorInfo(1, NMAM0052E, new String[] {NAME_FOR_MESSAGE_WH_START_TM});
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_ST);
            }
        } else {
            scrnMsg.whStartTm_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem7Txt_ET)) {
            Matcher m = p.matcher(scrnMsg.xxScrItem7Txt_ET.getValue());
            if (m.find()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.whEndTm_H1, scrnMsg.xxScrItem7Txt_ET.getValue().replace(":", ""));
            } else {
                // 2020/09/17 J.Evangelista [QC#57659,MOD]
                scrnMsg.xxScrItem7Txt_ET.setErrorInfo(1, NMAM0052E, new String[] {NAME_FOR_MESSAGE_WH_END_TM});
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_ET);
            }
        } else {
            scrnMsg.whEndTm_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem7Txt_CT)) {
            Matcher m = p.matcher(scrnMsg.xxScrItem7Txt_CT.getValue());
            if (m.find()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.whCutOffTm_H1, scrnMsg.xxScrItem7Txt_CT.getValue().replace(":", ""));
            } else {
                // 2020/09/17 J.Evangelista [QC#57659,MOD]
                scrnMsg.xxScrItem7Txt_CT.setErrorInfo(1, NMAM0052E, new String[] {NAME_FOR_MESSAGE_WH_CUT_OFF_TM});
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_CT);
            }
        } else {
            scrnMsg.whCutOffTm_H1.clear();
        }

        // START 2020/09/17 J.Evangelista [QC#57659,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem7Txt_FT)) {
            Matcher m = p.matcher(scrnMsg.xxScrItem7Txt_FT.getValue());
            if (m.find()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.fedexCutOffTm_H1, scrnMsg.xxScrItem7Txt_FT.getValue().replace(":", ""));
            } else {
                scrnMsg.xxScrItem7Txt_FT.setErrorInfo(1, NMAM0052E, new String[] {NAME_FOR_MESSAGE_FEDEX_CUT_OFF_TM});
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_FT);
            }
        } else {
            scrnMsg.fedexCutOffTm_H1.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxScrItem7Txt_UT)) {
            Matcher m = p.matcher(scrnMsg.xxScrItem7Txt_UT.getValue());
            if (m.find()) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.upsCutOffTm_H1, scrnMsg.xxScrItem7Txt_UT.getValue().replace(":", ""));
            } else {
                scrnMsg.xxScrItem7Txt_UT.setErrorInfo(1, NMAM0052E, new String[] {NAME_FOR_MESSAGE_UPS_CUT_OFF_TM});
                scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_UT);
            }
        } else {
            scrnMsg.upsCutOffTm_H1.clear();
        }
        // END   2020/09/17 J.Evangelista [QC#57659,ADD]

    }

    private static void changeFormatDate(NMAL6820BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.whStartTm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem7Txt_ST, scrnMsg.whStartTm_H1.getValue().substring(0, 2) + ':' + scrnMsg.whStartTm_H1.getValue().substring(2, 4));
        } else {
            scrnMsg.xxScrItem7Txt_ST.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.whEndTm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem7Txt_ET, scrnMsg.whEndTm_H1.getValue().substring(0, 2) + ':' + scrnMsg.whEndTm_H1.getValue().substring(2, 4));
        } else {
            scrnMsg.xxScrItem7Txt_ET.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.whCutOffTm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem7Txt_CT, scrnMsg.whCutOffTm_H1.getValue().substring(0, 2) + ':' + scrnMsg.whCutOffTm_H1.getValue().substring(2, 4));
        } else {
            scrnMsg.xxScrItem7Txt_CT.clear();
        }

        // START 2020/09/17 J.Evangelista [QC#57659,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.fedexCutOffTm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem7Txt_FT, scrnMsg.fedexCutOffTm_H1.getValue().substring(0, 2) + ':' + scrnMsg.fedexCutOffTm_H1.getValue().substring(2, 4));
        } else {
            scrnMsg.xxScrItem7Txt_FT.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.upsCutOffTm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem7Txt_UT, scrnMsg.upsCutOffTm_H1.getValue().substring(0, 2) + ':' + scrnMsg.upsCutOffTm_H1.getValue().substring(2, 4));
        } else {
            scrnMsg.xxScrItem7Txt_UT.clear();
        }
        // END   2020/09/17 J.Evangelista [QC#57659,ADD]

    }

    /**
     * <p>
     * Checks that the value is "2:Warehouse".<br>
     * If the value is not correct, set error info to item.<br>
     * </p>
     * @param value the field item
     */
    public static void checkIsSrcTpWh(EZDBStringItem value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            return;
        }

        if (!PROCR_TP.WAREHOUSE.equals(value.getValue())) {
            setWarningInfo(value, NMAM8148W, value.getNameForMessage(), MESSAGE_PARAM_NOT_WH, MESSAGE_PARAM_SRC_WH_SWH);
        }
    }

    /**
     * <p>
     * Sets the warning information to the field item.
     * </p>
     * @param item the field item.
     * @param messageCd the message code
     * @param messageParams the message parameters
     */
    private static void setWarningInfo(EZDBItem item, String messageCd, Object... messageParams) {
        item.setErrorInfo(2, messageCd, toStringArray(messageParams));
    }

    /**
     * <p>
     * Converts the array of {@link Object} to the array of
     * {@link String}.
     * </p>
     * @param objects the array of Object.
     * @return the array of String.
     */
    private static String[] toStringArray(Object[] objects) {
        if (objects == null) {
            return null;
        }
        String[] params = new String[objects.length];
        for (int index = 0; index < objects.length; index++) {
            if (objects[index] == null) {
                params[index] = "";
            } else {
                params[index] = objects[index].toString();
            }
        }
        return params;
    }

    /**
     * <pre>
     * set Screen Properties for SWH Detail
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setScreenPropertiesForSwhDetail(EZDCommonHandler handler, NMAL6820BMsg scrnMsg) {
        setBackGroundColorSwhDetail(handler, scrnMsg);
        setCursorRuleForSwhDetail(handler, scrnMsg);
    }

    /**
     * <pre>
     * set Back Ground Color for SWH Detail
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6820BMsg
     */
    private static void setBackGroundColorSwhDetail(EZDCommonHandler handler, NMAL6820BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG(SWH_LEFT_TABLE_NAME_VALUE, scrnMsg.A);
        tblColor.setAlternateRowsBG(SWH_RIGHT_TABLE_NAME_VALUE, scrnMsg.A);
    }

    /**
     * <pre>
     * set cursor rule
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6820BMsg
     */
    private static void setCursorRuleForSwhDetail(EZDCommonHandler handler, NMAL6820BMsg scrnMsg) {
        ZYPGUITableFocusRule tblFocusRule = new ZYPGUITableFocusRule("NMAL6820Scrn00", NMAL6820Bean.A);
        scrnMsg.addGUIAttribute(tblFocusRule);

        ZYPGUIFocusRule fRule;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            fRule = new ZYPGUIFocusRule(SWH_RTL_SWH_NM_VALUE + "#" + i);
            fRule.setNextId(NMAL6820Bean.rtlSwhDescTxt_A1 + "#" + i);
            tblFocusRule.addRule(fRule);

            fRule = new ZYPGUIFocusRule(RTL_SWH_DESC_TXT_VALUE + "#" + i);
            fRule.setPrevId(NMAL6820Bean.rtlSwhNm_A1 + "#" + i);
            tblFocusRule.addRule(fRule);
        }
    }

    /**
     * <pre>
     * Check maximum length
     * </pre>
     * @param targetField EZDBStringItem
     * @param maxLength int
     * @param messageParam
     * @return boolean (true : No Error / false : error)
     */
    public static boolean checkMaxLength(EZDBStringItem targetField, int maxLength, String messageParam) {
        if (!ZYPCommonFunc.hasValue(targetField)) {
            return true;
        }
        if (targetField.getValue().length() > maxLength) {
            targetField.setErrorInfo(1, NMAM8350E, new String[] {messageParam, new Integer(maxLength).toString()});
            return false;
        }
        return true;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     */
    public static void setInitParamForAccountPopup(NMAL6820BMsg scrnMsg) {
        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @param locNum EZDBStringItem
     * @param dsAcctNum EZDBStringItem
     * @param dsAcctNm EZDBStringItem
     * @return Object[]
     */
    public static Object[] setParamForAccountPopup(NMAL6820BMsg scrnMsg, EZDBStringItem locNum, EZDBStringItem dsAcctNum, EZDBStringItem dsAcctNm) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_09, ACCT_POPUP_PARAM_08_SHIP_TO_ONLY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_12, ACCT_POPUP_PARAM_11_ACTIVE_ONLY);
        Object[] params = new Object[17];

        params[0] = dsAcctNum;
        params[1] = dsAcctNm;
        params[2] = locNum;
        params[3] = scrnMsg.xxPopPrm_04;
        params[4] = scrnMsg.xxPopPrm_05;
        params[5] = scrnMsg.xxPopPrm_06;
        params[6] = scrnMsg.xxPopPrm_07;
        params[7] = scrnMsg.xxPopPrm_08;
        params[8] = scrnMsg.xxPopPrm_09;
        params[9] = scrnMsg.xxPopPrm_10;
        params[10] = scrnMsg.xxPopPrm_11;
        params[11] = scrnMsg.xxPopPrm_12;
        params[12] = scrnMsg.xxPopPrm_13;
        params[13] = scrnMsg.xxPopPrm_14;
        params[14] = scrnMsg.xxPopPrm_15;
        params[15] = scrnMsg.xxPopPrm_16;
        params[16] = scrnMsg.xxPopPrm_17;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL6820BMsg
     * @return Object[]
     */
    public static Object[] setParamForGeoCodeSearchPopup(NMAL6820BMsg scrnMsg) {
        // 2016/10/12 CSA-QC#4096 Add Start
        Object[] params = new Object[5];

        setInitParamForAccountPopup(scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_S1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.ctyAddr_S1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.stCd_S1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.stCd_S1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.postCd_S1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, scrnMsg.postCd_S1);
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.cntyNm_S1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, scrnMsg.cntyNm_S1);
        }

        // City Address
        params[0] = scrnMsg.xxPopPrm_01;
        // State
        params[1] = scrnMsg.xxPopPrm_02;
        // Post
        params[2] = scrnMsg.xxPopPrm_03;
        // County Name
        params[3] = scrnMsg.xxPopPrm_04;
        // Geo Code
        params[4] = scrnMsg.xxPopPrm_05;

        return params;
        // 2016/10/12 CSA-QC#4096 Add End
    }
    /**
     * @param scrnMsg scrnMsg
     * @param globalCompanyCode globalCompanyCode
     * @return Object[]
     */
    public static Object[] getAddressPopUpShipToParam(NMAL6820BMsg scrnMsg, String globalCompanyCode) {
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
        baseSql.append("    P.GLBL_CMPY_CD = '" + globalCompanyCode + "' ");
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
        whereArray1[IDX_2] = scrnMsg.ctyAddr_S1.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "State";
        whereArray2[IDX_1] = "ST_CD";
        whereArray2[IDX_2] = scrnMsg.stCd_S1.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Postal Code";
        whereArray3[IDX_1] = "POST_CD";
        whereArray3[IDX_2] = scrnMsg.postCd_S1.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "County";
        whereArray4[IDX_1] = "CNTY_NM";
        String selectedCntyNm = scrnMsg.cntyNm_S1.getValue();
//       for (int i = 0; i < scrnMsg.cntyPk_L1.length(); i++) {
//           if (scrnMsg.cntyPk_S1.getValue() == null) {
//               break;
//           }
//           if (scrnMsg.cntyPk_S1.getValue().compareTo(((EZDBBigDecimalItem) scrnMsg.cntyPk_L1.get(i)).getValue()) == 0) {
//               selectedCntyNm = scrnMsg.cntyNm_S1.getValue();
//               break;
//           }
//       }
        whereArray4[IDX_2] = selectedCntyNm;
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
    /**
     * @param scrnMsg scrnMsg
     * @param globalCompanyCode globalCompanyCode
     * @return Object[]
     */
    public static Object[] getAddressPopUpReturnToParam(NMAL6820BMsg scrnMsg, String globalCompanyCode) {
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
        baseSql.append("    P.GLBL_CMPY_CD = '" + globalCompanyCode + "' ");
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
        whereArray1[IDX_2] = scrnMsg.rtrnToCtyAddr_R1.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "State";
        whereArray2[IDX_1] = "ST_CD";
        whereArray2[IDX_2] = scrnMsg.rtrnToStCd_R1.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Postal Code";
        whereArray3[IDX_1] = "POST_CD";
        whereArray3[IDX_2] = scrnMsg.rtrnToPostCd_R1.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "County";
        whereArray4[IDX_1] = "CNTY_NM";
        String selectedCntyNm = scrnMsg.cntyNm_R1.getValue();
//       for (int i = 0; i < scrnMsg.cntyPk_L2.length(); i++) {
//           if (scrnMsg.cntyPk_R1.getValue() == null) {
//               break;
//           }
//           if (scrnMsg.cntyPk_R1.getValue().compareTo(((EZDBBigDecimalItem) scrnMsg.cntyPk_L2.get(i)).getValue()) == 0) {
//               selectedCntyNm = scrnMsg.cntyNm_R1.getValue();
//               break;
//           }
//       }
        whereArray4[IDX_2] = selectedCntyNm;
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
}
