package business.servlet.NWAL2180.common;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.*;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.AFTER_DECIMAL_DIGITS_AMT_RATE;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.AFTER_DECIMAL_DIGITS_MULTI_RATE;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_APL;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_APR;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_DEL;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_DWL;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_RJT;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_RST;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_RTN;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BTN_CMN_SUB;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ADD_ACCESSORY;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ADD_ACCESSORY_RE;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ADD_ACTIVE_METER;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ADD_SERVICE;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_APPLY_ALL_HEADER;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_APPLY_ALL_MODEL;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_APPLY_BUNDLE_PRICE;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_BAND_SEARCH;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_BAND_SEARCH_CONFIG;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_DEL_ACCESSORY;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_DEL_ACCESSORY_RE;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_DEL_ACTIVE_METER;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_DEL_SERVICE;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_DESC_ADD;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_DESC_COVERED_ITEM;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_DESC_COVERED_ITEM_RE;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_DESC_COVERED_UNIT;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_SEARCH_ADD;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_SEARCH_COVERED_ITEM;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_SEARCH_COVERED_ITEM_RE;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_SEARCH_COVERED_UNIT;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM_CONFIG;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_REFRESH;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_SERVICE_PRICE_LIST_ACP;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_SERVICE_PRICE_LIST_REP;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.BUTTON_SUPPLY_AGREEMENT;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.NWZM1107E;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.SCREEN_ID;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.XX_FLG_PARENT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL2180.NWAL2180BMsg;
import business.servlet.NWAL2180.NWAL2180_ABMsg;
import business.servlet.NWAL2180.NWAL2180_BBMsg;
import business.servlet.NWAL2180.NWAL2180_CBMsg;
import business.servlet.NWAL2180.NWAL2180_JBMsg;
import business.servlet.NWAL2180.NWAL2180_QBMsg;
import business.servlet.NWAL2180.NWAL2180_RBMsg;
import business.servlet.NWAL2180.NWAL2180_UBMsg;
import business.servlet.NWAL2180.NWAL2180_VBMsg;
import business.servlet.NWAL2180.NWAL2180_XBMsg;
import business.servlet.NWAL2180.NWAL2180_ZBMsg;
import business.servlet.NWAL2180.constant.NWAL2180Constant.XX_PAGE;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Murai         Create          N/A
 * 2016/02/03   Fujitsu         M.Yamada        Update          QC#2945
 * 2016/03/08   Fujitsu         M.Yamada        Update          QC#5038, 5091
 * 2016/03/29   Fujitsu         M.Yamada        Update          QC#5038-1
 * 2016/05/13   Fujitsu         M.Yamada        Update          QC#4631
 * 2016/05/30   Fujitsu         M.Yamada        Update          QC#4628
 * 2016/05/30   Fujitsu         T.Ishii         Update          S21_NA#9283
 * 2016/07/20   Fujitsu         M.Yamada        Update          QC#10378
 * 2016/07/21   Fujitsu         M.Yamada        Update          QC#9311
 * 2016/07/22   Fujitsu         M.Yamada        Update          QC#11339
 * 2016/09/07   Fujitsu         M.Yamada        Update          QC#13256
 * 2016/09/16   Fujitsu         M.Yamada        Update          QC#10375
 * 2016/10/01   Fujitsu         M.Yamada        Update          QC#14869
 * 2016/10/04   Fujitsu         M.Yamada        Update          QC#14940
 * 2016/10/14   Fujitsu         M.Yamada        Update          QC#9362
 * 2016/12/20   Fujitsu         S.Iidaka        Update          QC#16141
 * 2017/03/15   Fujitsu         M.Yamada        Update          QC#17971
 * 2017/03/29   Fujitsu         S.Ohki          Update          QC#18171
 * 2017/04/19   Fujitsu         M.Yamada        Update          QC#18346
 * 2017/04/20   Fujitsu         M.Yamada        Update          QC#18394
 * 2017/05/31   Fujitsu         S.Ohki          Update          RS#8233
 * 2018/07/06   Fujitsu         K.Ishizuka      Ypdate          QC#26528
 * 2018/11/16   Fujitsu         M.Yamadaka      Update          QC#28638
 *</pre>
 */
public class NWAL2180CommonLogic {

    /**
     * Check input Parameter.
     * @param scrnMsg NWAL2180BMsg
     * @return boolean
     */
    public static boolean checkInputParam(NWAL2180BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoSvcDtlPk)) {
            scrnMsg.setMessageInfo(NWZM1107E, new String[] {"CPO_SVC_DTL_PK" });
            return false;
        }

        return true;
    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL2180BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NWAL2180BMsg scrnMsg) {
        int isActive = 0; // inactive

        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], isActive, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], isActive, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * @param scrnMsg
     * @return if import then return true.
     */
    public static boolean isImport(NWAL2180BMsg scrnMsg) {
        return XX_PAGE.PAGE_IMPT.getCode().equals(scrnMsg.xxPageCd.getValue());
    }

    /**
     * init AppFracDigit
     * @param scrnMsg NWAL2180BMsg
     */
    public static void initAppFracDigit(NWAL2180BMsg scrnMsg) {

        scrnMsg.basePrcDealAmt.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
        scrnMsg.xxTotAmt.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);

        // Service Pricing Header
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxTotPrcAmt_PB.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
            scrnMsg.A.no(i).xxTotPrcAmt_EB.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
            scrnMsg.A.no(i).xxTotPrcAmt_TB.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
        }
        // Service Pricing Header Config
        for (int i = 0; i < scrnMsg.R.length(); i++) {
            scrnMsg.R.no(i).xxTotPrcAmt_PR.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
            scrnMsg.R.no(i).xxTotPrcAmt_ER.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
            scrnMsg.R.no(i).xxTotPrcAmt_TR.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
        }

        // Service Pricing Detail
        for (int i = 0; i < scrnMsg.Z.length(); i++) {
            scrnMsg.Z.no(i).contrMtrMultRate_Z.setAppFracDigit(AFTER_DECIMAL_DIGITS_MULTI_RATE);
            scrnMsg.Z.no(i).xsMtrAmtRate_Z.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT_RATE);

            // Add Start 2018/05/08 QC#25030
            scrnMsg.Z.no(i).bllgFreeCopyCnt_Z.setAppFracDigit(AFTER_DECIMAL_DIGITS_INT);
            scrnMsg.Z.no(i).bllgMinCnt_Z.setAppFracDigit(AFTER_DECIMAL_DIGITS_INT);
            scrnMsg.Z.no(i).bllgMinAmtRate_Z.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
            scrnMsg.Z.no(i).bllgRollOverRatio_Z.setAppFracDigit(AFTER_DECIMAL_DIGITS_INT);
            // Add End 2018/05/08 QC#25030
        }
        // Service Pricing Detail Config
        for (int i = 0; i < scrnMsg.U.length(); i++) {
            scrnMsg.U.no(i).contrMtrMultRate_U.setAppFracDigit(AFTER_DECIMAL_DIGITS_MULTI_RATE);
            scrnMsg.U.no(i).xsMtrAmtRate_U.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT_RATE);

            // Add Start 2018/05/08 QC#25030
            scrnMsg.U.no(i).bllgFreeCopyCnt_U.setAppFracDigit(AFTER_DECIMAL_DIGITS_INT);
            scrnMsg.U.no(i).bllgMinCnt_U.setAppFracDigit(AFTER_DECIMAL_DIGITS_INT);
            scrnMsg.U.no(i).bllgMinAmtRate_U.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
            scrnMsg.U.no(i).bllgRollOverRatio_U.setAppFracDigit(AFTER_DECIMAL_DIGITS_INT);
            // Add End 2018/05/08 QC#25030
        }

        // Accessory Charge Detail
        for (int i = 0; i < scrnMsg.J.length(); i++) {
            scrnMsg.J.no(i).addlBasePrcDealAmt_J.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
        }

        // Rental Equip Accessory Charge Detail
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).addlBasePrcDealAmt_B.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
        }

        // AdditionalService Charge Detail
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).addlChrgPrcDealAmt_C.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
        }

    }

    /**
     * initial InputProtected
     * @param scrnMsg NWAL2180BMsg
     */
    public static void initInputProtected(NWAL2180BMsg scrnMsg) {

        // Input Protect
        scrnMsg.xxScrItem50Txt.setInputProtected(true);
        scrnMsg.dsImptSvcLineNum.setInputProtected(true);
        scrnMsg.dsImptSvcMdseCd.setInputProtected(true);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        scrnMsg.prcSvcContrTpCd.setInputProtected(true);
        scrnMsg.prcSvcPlnTpCd.setInputProtected(true);
        scrnMsg.fromPerMthNum.setInputProtected(true);
        scrnMsg.toPerMthNum.setInputProtected(true);
        scrnMsg.shpgIntvlMthNum.setInputProtected(true);
        scrnMsg.dsContrCatgCd.setInputProtected(true);
        scrnMsg.dsContrNum.setInputProtected(true);
        scrnMsg.baseBllgCycleCd.setInputProtected(true);
        scrnMsg.usgBllgCycleCd.setInputProtected(true);
        scrnMsg.fixTermInMthAot.setInputProtected(true); // QC#28638
        scrnMsg.billWithEquipFlg.setInputProtected(true);
        scrnMsg.billByTpCd.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.dsAcctNum.setInputProtected(true);
        scrnMsg.xxBillToAcctNmSrchTxt.setInputProtected(true);
        scrnMsg.soldToCustLocCd.setInputProtected(true);
        scrnMsg.shipToCustLocCd.setInputProtected(true);
        scrnMsg.cpoSvcAgmtVerNum.setInputProtected(true);
        scrnMsg.manContrOvrdFlg.setInputProtected(false);
        scrnMsg.manContrOvrdRsnNm.setInputProtected(false);
        scrnMsg.manContrOvrdCmntTxt.setInputProtected(false);
        scrnMsg.basePrcDealAmt.setInputProtected(false);

        // Service Pricing Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL2180_ABMsg pricingMsg = scrnMsg.A.no(i);

            pricingMsg.t_MdlNm_A.setInputProtected(true);
            pricingMsg.xxTotQtyCnt_A.setInputProtected(true);
            pricingMsg.prcCatgNm_A.setInputProtected(false);
            pricingMsg.prcMtrPkgPk_A.setInputProtected(false); // QC#4631
            pricingMsg.prcTierSvcOfferCd_A.setInputProtected(isRateTypeAnuual(pricingMsg.prcRateTpCd_A.getValue())); // QC#4631
            pricingMsg.shpgIntvlMthNum_A.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_EB.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_TB.setInputProtected(true);
        }
        scrnMsg.prcCatgNm_HB.setInputProtected(false);
        scrnMsg.prcCatgNm_C.setInputProtected(false);

        // Service Pricing Detail
        for (int i = 0; i < scrnMsg.R.length(); i++) {
            NWAL2180_RBMsg pricingMsg = scrnMsg.R.no(i);

            pricingMsg.t_MdlNm_R.setInputProtected(true);
            pricingMsg.xxTotQtyCnt_R.setInputProtected(true);
            pricingMsg.prcCatgNm_R.setInputProtected(false);
            pricingMsg.prcMtrPkgPk_R.setInputProtected(false);
            pricingMsg.prcTierSvcOfferCd_R.setInputProtected(isRateTypeAnuual(pricingMsg.prcRateTpCd_R.getValue())); // QC#4631
            pricingMsg.shpgIntvlMthNum_R.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_ER.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_TR.setInputProtected(true);
            pricingMsg.xxBillToAcctNmSrchTxt_R.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.U.length(); i++) {
            NWAL2180_UBMsg usgMsg = scrnMsg.U.no(i);
            usgMsg.xxBillToAcctNmSrchTxt_U.setInputProtected(true);
        }

        // Accessory Charge Detail
        for (int i = 0; i < scrnMsg.J.length(); i++) {

            NWAL2180_JBMsg acsryMsg = scrnMsg.J.no(i);
            acsryMsg.xxChkBox_J.setInputProtected(false);
            acsryMsg.xxLineNum_J.setInputProtected(true);
            acsryMsg.mdseCd_J.setInputProtected(false);
            acsryMsg.mdseDescShortTxt_J.setInputProtected(true);
            acsryMsg.addlBasePrcDealAmt_J.setInputProtected(false);
            acsryMsg.prcCatgNm_J.setInputProtected(false);
            acsryMsg.prcListEquipConfigNum_J.setInputProtected(false);
        }
        // Rental Equipment Charge Base & Accessory Detail
        for (int i = 0; i < scrnMsg.B.length(); i++) {

            NWAL2180_BBMsg acsryMsg = scrnMsg.B.no(i);
            acsryMsg.xxChkBox_B.setInputProtected(false);
            acsryMsg.xxLineNum_B.setInputProtected(true);
            acsryMsg.mdseCd_B.setInputProtected(false);
            acsryMsg.mdseDescShortTxt_B.setInputProtected(true);
            acsryMsg.addlBasePrcDealAmt_B.setInputProtected(false);
            acsryMsg.prcCatgNm_B.setInputProtected(false);
            acsryMsg.prcListEquipConfigNum_B.setInputProtected(false);
        }
        // AdditionalService Charge Detail
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NWAL2180_CBMsg addtionlMsg = scrnMsg.C.no(i);

            addtionlMsg.xxChkBox_C.setInputProtected(false);
            addtionlMsg.xxLineNum_C.setInputProtected(true);
            addtionlMsg.mdseCd_CU.setInputProtected(false);
            addtionlMsg.mdseDescShortTxt_CU.setInputProtected(true);
            addtionlMsg.mdseCd_CI.setInputProtected(false);
            addtionlMsg.mdseDescShortTxt_CI.setInputProtected(true);
            addtionlMsg.addlChrgPrcDealAmt_C.setInputProtected(false);
        }

    }

    private static boolean isRateTypeAnuual(String prcRateTpCd) {
        return PRC_RATE_TP.ANNUAL.equals(prcRateTpCd);
    }

    /**
     * initial InputProtected
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setInputProtectedOnChangeForUsage(NWAL2180BMsg scrnMsg) {
        // BigDecimal multiplier = BigDecimal.ZERO;
        String paretntHardNm = "";

        if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
                NWAL2180_ZBMsg pricingMsg = scrnMsg.Z.no(i);

                int ixA = getPrcDtlIndex(scrnMsg, i);
                NWAL2180_ABMsg prcHeaderMsg = scrnMsg.A.no(ixA);
                boolean isProtect = false;
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue())) {
                    isProtect = true;
                }

                paretntHardNm = pricingMsg.mtrLbDescTxt_Z.getValue();

                // Parent Data
                if (XX_FLG_PARENT.equals(pricingMsg.xxFlgNm_Z.getValue())) {

                    pricingMsg.prcListBandDescTxt_Z.setInputProtected(isProtect);
                    pricingMsg.prcBookMdseCd_Z.setInputProtected(isProtect);
                    pricingMsg.mtrLbDescTxt_ZB.setInputProtected(true);
                    pricingMsg.mdseDescShortTxt_Z.setInputProtected(isProtect);
                    pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);
                    pricingMsg.contrMtrMultRate_Z.setInputProtected(true);
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(ixA).prcTierSvcOfferCd_A)) {
                        pricingMsg.prcSvcTierTpCd_Z.setInputProtected(true);
                        pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(isProtect);
                        pricingMsg.minCopyVolCnt_Z.setInputProtected(true);
                        pricingMsg.maxCopyVolCnt_Z.setInputProtected(true);
                        pricingMsg.xsMtrAmtRate_Z.setInputProtected(isProtect);
                    } else {
                        // able to set Tier Info
                        pricingMsg.prcSvcTierTpCd_Z.setInputProtected(true);
                        pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(true);
                        pricingMsg.minCopyVolCnt_Z.setInputProtected(true);
                        pricingMsg.maxCopyVolCnt_Z.setInputProtected(true);
                        pricingMsg.xsMtrAmtRate_Z.setInputProtected(true);
                    }
                    // Hard counter Line
                } else if (ZYPCommonFunc.hasValue(paretntHardNm)) {

                    pricingMsg.prcListBandDescTxt_Z.setInputProtected(isProtect);
                    pricingMsg.prcBookMdseCd_Z.setInputProtected(isProtect);
                    pricingMsg.mtrLbDescTxt_ZB.setInputProtected(isProtect);
                    pricingMsg.mdseDescShortTxt_Z.setInputProtected(true);
                    pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);
                    pricingMsg.contrMtrMultRate_Z.setInputProtected(isProtect);
                    pricingMsg.xxChkBox_Z.setInputProtected(true);
                    pricingMsg.prcSvcTierTpCd_Z.setInputProtected(true);
                    pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(true);
                    pricingMsg.minCopyVolCnt_Z.setInputProtected(true);
                    pricingMsg.maxCopyVolCnt_Z.setInputProtected(true);
                    pricingMsg.xsMtrAmtRate_Z.setInputProtected(true);

                    // Tier Info
                } else {
                    pricingMsg.prcListBandDescTxt_Z.setInputProtected(isProtect);
                    pricingMsg.prcBookMdseCd_Z.setInputProtected(isProtect);
                    pricingMsg.mtrLbDescTxt_ZB.setInputProtected(isProtect);
                    pricingMsg.mdseDescShortTxt_Z.setInputProtected(true);
                    pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);
                    pricingMsg.contrMtrMultRate_Z.setInputProtected(true);
                    pricingMsg.xxChkBox_Z.setInputProtected(isProtect);
                    pricingMsg.prcSvcTierTpCd_Z.setInputProtected(isProtect);
                    pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(true);
                    pricingMsg.minCopyVolCnt_Z.setInputProtected(isProtect);
                    pricingMsg.maxCopyVolCnt_Z.setInputProtected(isProtect);
                    pricingMsg.xsMtrAmtRate_Z.setInputProtected(isProtect);
                }
            }

        } else {
            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
                NWAL2180_ZBMsg pricingMsg = scrnMsg.Z.no(i);

                int ixA = getPrcDtlIndex(scrnMsg, i);
                NWAL2180_ABMsg prcHeaderMsg = scrnMsg.A.no(ixA);
                boolean isProtect = false;
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue())) {
                    isProtect = true;
                }

                paretntHardNm = pricingMsg.mtrLbDescTxt_Z.getValue();

                if (XX_FLG_PARENT.equals(pricingMsg.xxFlgNm_Z.getValue())) {

                    pricingMsg.prcListBandDescTxt_Z.setInputProtected(isProtect);
                    pricingMsg.prcBookMdseCd_Z.setInputProtected(isProtect);
                    pricingMsg.mtrLbDescTxt_ZB.setInputProtected(true);
                    pricingMsg.mdseDescShortTxt_Z.setInputProtected(true);
                    pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);
                    pricingMsg.contrMtrMultRate_Z.setInputProtected(true);
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(ixA).prcTierSvcOfferCd_A)) {
                        pricingMsg.prcSvcTierTpCd_Z.setInputProtected(true);
                        pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(isProtect);
                        pricingMsg.minCopyVolCnt_Z.setInputProtected(true);
                        pricingMsg.maxCopyVolCnt_Z.setInputProtected(true);
                        pricingMsg.xsMtrAmtRate_Z.setInputProtected(isProtect);
                    } else {
                        pricingMsg.prcSvcTierTpCd_Z.setInputProtected(true);
                        pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(true);
                        pricingMsg.minCopyVolCnt_Z.setInputProtected(true);
                        pricingMsg.maxCopyVolCnt_Z.setInputProtected(true);
                        pricingMsg.xsMtrAmtRate_Z.setInputProtected(true);
                    }
                    // Hard counter Line
                } else if (ZYPCommonFunc.hasValue(paretntHardNm)) {
                    pricingMsg.prcListBandDescTxt_Z.setInputProtected(isProtect);
                    pricingMsg.prcBookMdseCd_Z.setInputProtected(isProtect);
                    pricingMsg.mtrLbDescTxt_ZB.setInputProtected(true);
                    pricingMsg.mdseDescShortTxt_Z.setInputProtected(true);
                    pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);
                    pricingMsg.contrMtrMultRate_Z.setInputProtected(isProtect);
                    pricingMsg.xxChkBox_Z.setInputProtected(true);
                    pricingMsg.prcSvcTierTpCd_Z.setInputProtected(true);
                    pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(true);
                    pricingMsg.minCopyVolCnt_Z.setInputProtected(true);
                    pricingMsg.maxCopyVolCnt_Z.setInputProtected(true);
                    pricingMsg.xsMtrAmtRate_Z.setInputProtected(true);

                    // Tier Info
                } else {
                    pricingMsg.prcListBandDescTxt_Z.setInputProtected(isProtect);
                    pricingMsg.prcBookMdseCd_Z.setInputProtected(isProtect);
                    pricingMsg.mtrLbDescTxt_ZB.setInputProtected(true);
                    pricingMsg.mdseDescShortTxt_Z.setInputProtected(true);
                    pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);
                    pricingMsg.contrMtrMultRate_Z.setInputProtected(true);
                    pricingMsg.xxChkBox_Z.setInputProtected(isProtect);
                    pricingMsg.prcSvcTierTpCd_Z.setInputProtected(isProtect);
                    pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(true);
                    pricingMsg.minCopyVolCnt_Z.setInputProtected(isProtect);
                    pricingMsg.maxCopyVolCnt_Z.setInputProtected(isProtect);
                    pricingMsg.xsMtrAmtRate_Z.setInputProtected(isProtect);
                }
            }

        }
    }

    /**
     * initial InputProtected
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setInputProtectedOnChangeForUsageConfig(NWAL2180BMsg scrnMsg) {
        String paretntHardNm = "";

        // Service Pricing Detail
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NWAL2180_UBMsg pricingMsg = scrnMsg.U.no(i);

            int ixR = getPrcDtlIndexConfig(scrnMsg, i);
            NWAL2180_RBMsg prcHeaderMsg = scrnMsg.R.no(ixR);
            boolean isProtect = false;
            if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_R.getValue())) {
                isProtect = true;
            }

            paretntHardNm = pricingMsg.mtrLbDescTxt_U.getValue();

            if (XX_FLG_PARENT.equals(pricingMsg.xxFlgNm_U.getValue())) {

                pricingMsg.prcListBandDescTxt_U.setInputProtected(isProtect);
                pricingMsg.prcBookMdseCd_U.setInputProtected(isProtect);
                pricingMsg.mtrLbDescTxt_UB.setInputProtected(true);
                pricingMsg.mdseDescShortTxt_U.setInputProtected(true);
                pricingMsg.mtrLbDescTxt_U.setInputProtected(true);
                pricingMsg.contrMtrMultRate_U.setInputProtected(true);
                if (!ZYPCommonFunc.hasValue(scrnMsg.R.no(ixR).prcTierSvcOfferCd_R)) {
                    pricingMsg.prcSvcTierTpCd_U.setInputProtected(true);
                    pricingMsg.mlyCopyInclPrcQty_U.setInputProtected(isProtect);
                    pricingMsg.minCopyVolCnt_U.setInputProtected(true);
                    pricingMsg.maxCopyVolCnt_U.setInputProtected(true);
                    pricingMsg.xsMtrAmtRate_U.setInputProtected(isProtect);
                } else {
                    pricingMsg.prcSvcTierTpCd_U.setInputProtected(true);
                    pricingMsg.mlyCopyInclPrcQty_U.setInputProtected(true);
                    pricingMsg.minCopyVolCnt_U.setInputProtected(true);
                    pricingMsg.maxCopyVolCnt_U.setInputProtected(true);
                    pricingMsg.xsMtrAmtRate_U.setInputProtected(true);
                }
                // Hard counter Line
            } else if (ZYPCommonFunc.hasValue(paretntHardNm)) {
                pricingMsg.prcListBandDescTxt_U.setInputProtected(isProtect);
                pricingMsg.prcBookMdseCd_U.setInputProtected(isProtect);
                pricingMsg.mtrLbDescTxt_UB.setInputProtected(true);
                pricingMsg.mdseDescShortTxt_U.setInputProtected(true);
                pricingMsg.mtrLbDescTxt_U.setInputProtected(true);
                pricingMsg.contrMtrMultRate_U.setInputProtected(isProtect);
                pricingMsg.xxChkBox_U.setInputProtected(true);
                pricingMsg.prcSvcTierTpCd_U.setInputProtected(true);
                pricingMsg.mlyCopyInclPrcQty_U.setInputProtected(true);
                pricingMsg.minCopyVolCnt_U.setInputProtected(true);
                pricingMsg.maxCopyVolCnt_U.setInputProtected(true);
                pricingMsg.xsMtrAmtRate_U.setInputProtected(true);

                // Tier Info
            } else {
                pricingMsg.prcListBandDescTxt_U.setInputProtected(isProtect);
                pricingMsg.prcBookMdseCd_U.setInputProtected(isProtect);
                pricingMsg.mtrLbDescTxt_UB.setInputProtected(true);
                pricingMsg.mdseDescShortTxt_U.setInputProtected(true);
                pricingMsg.mtrLbDescTxt_U.setInputProtected(true);
                pricingMsg.contrMtrMultRate_U.setInputProtected(true);
                pricingMsg.xxChkBox_U.setInputProtected(isProtect);
                pricingMsg.prcSvcTierTpCd_U.setInputProtected(isProtect);
                pricingMsg.mlyCopyInclPrcQty_U.setInputProtected(true);
                pricingMsg.minCopyVolCnt_U.setInputProtected(isProtect);
                pricingMsg.maxCopyVolCnt_U.setInputProtected(isProtect);
                pricingMsg.xsMtrAmtRate_U.setInputProtected(isProtect);
            }
        }
    }

    /**
     * override Protected
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2180BMsg
     */
    public static void overrideProtected(EZDCommonHandler handler, NWAL2180BMsg scrnMsg) {
        handler.setButtonEnabled(BUTTON_APPLY_ALL_HEADER, false);

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.manContrOvrdFlg.getValue())) {
            scrnMsg.manContrOvrdFlg.setInputProtected(isImport(scrnMsg));

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NWAL2180_ABMsg prcHeaderMsg = scrnMsg.A.no(i);
                boolean isProtect = false;
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue())) {
                    isProtect = true;
                }

                prcHeaderMsg.prcCatgNm_A.setInputProtected(isProtect);
                prcHeaderMsg.prcMtrPkgPk_A.setInputProtected(isProtect); // QC#4631

                if (!prcHeaderMsg.prcTierSvcOfferCd_AB.isInputProtected()) {
                    prcHeaderMsg.prcTierSvcOfferCd_A.setInputProtected(//
                            isProtect || isRateTypeAnuual(prcHeaderMsg.prcRateTpCd_A.getValue())); // QC#4631
                }
                prcHeaderMsg.xxTotPrcAmt_PB.setInputProtected(isProtect);
                prcHeaderMsg.shpgIntvlMthNum_A.setInputProtected(true);
                setTierLinkCtrl(handler, scrnMsg, i);
                setSupplyButton(handler, prcHeaderMsg, i, scrnMsg);
                handler.setButtonEnabled(BUTTON_APPLY_ALL_MODEL, i, false);
            }
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                NWAL2180_RBMsg prcHeaderMsg = scrnMsg.R.no(i);
                boolean isProtect = false;
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_R.getValue())) {
                    isProtect = true;
                }
                handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, i, true);

                prcHeaderMsg.dsOrdPosnNum_R.setInputProtected(true);
                prcHeaderMsg.prcCatgNm_R.setInputProtected(isProtect);
                prcHeaderMsg.prcCatgCd_R.setInputProtected(isProtect);
                prcHeaderMsg.prcMtrPkgPk_R.setInputProtected(isProtect);

                prcHeaderMsg.xxTotPrcAmt_PR.setInputProtected(isProtect);
                prcHeaderMsg.shpgIntvlMthNum_R.setInputProtected(true);
                prcHeaderMsg.billToCustCd_R.setInputProtected(isProtect);
                prcHeaderMsg.dsAcctNm_R.setInputProtected(isProtect); // 2017/03/29
                // S21_NA#18171
                // Mod
                prcHeaderMsg.billToLocNum_R.setInputProtected(isProtect);
                prcHeaderMsg.xxBillToAcctNmSrchTxt_R.setInputProtected(true);
                prcHeaderMsg.prcTierSvcOfferCd_R.setInputProtected(isProtect);

                setTierLinkCtrlConfig(handler, scrnMsg, i);
                setSupplyButton(handler, prcHeaderMsg, i, scrnMsg);
            }

            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
                NWAL2180_ZBMsg pricingMsg = scrnMsg.Z.no(i);
                int ixA = getPrcDtlIndex(scrnMsg, i);
                NWAL2180_ABMsg prcHeaderMsg = scrnMsg.A.no(ixA);
                boolean isProtect = false;
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue())) {
                    isProtect = true;
                }
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM, i, !isProtect);

                pricingMsg.mtrLbDescTxt_ZB.setInputProtected(true);
                pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);

                pricingMsg.prcListBandDescTxt_Z.setInputProtected(isProtect);
                pricingMsg.prcBookMdseCd_Z.setInputProtected(isProtect);
                pricingMsg.contrMtrMultRate_Z.setInputProtected(isProtect);
                pricingMsg.xxChkBox_Z.setInputProtected(isProtect);
                pricingMsg.prcSvcTierTpCd_Z.setInputProtected(isProtect);
                pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(isProtect);
                pricingMsg.minCopyVolCnt_Z.setInputProtected(isProtect);
                pricingMsg.maxCopyVolCnt_Z.setInputProtected(isProtect);
                pricingMsg.xsMtrAmtRate_Z.setInputProtected(isProtect);

                // Add Start 2018/05/08 QC#25030
                pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(true);
                pricingMsg.bllgMinCnt_Z.setInputProtected(true);
                pricingMsg.bllgMinAmtRate_Z.setInputProtected(true);
                pricingMsg.bllgRollOverRatio_Z.setInputProtected(true);
                // Add End 2018/05/08 QC#25030
            }
            for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
                NWAL2180_UBMsg usgMsg = scrnMsg.U.no(i);
                int ixR = getPrcDtlIndexConfig(scrnMsg, i);
                NWAL2180_RBMsg prcHeaderMsg = scrnMsg.R.no(ixR);
                boolean isProtect = false;
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_R.getValue())) {
                    isProtect = true;
                }

                handler.setButtonEnabled(BUTTON_BAND_SEARCH_CONFIG, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM_CONFIG, i, !isProtect);

                usgMsg.mtrLbDescTxt_UB.setInputProtected(true);
                usgMsg.mtrLbDescTxt_U.setInputProtected(true);

                usgMsg.dsOrdPosnNum_U.setInputProtected(isProtect);
                usgMsg.prcListBandDescTxt_U.setInputProtected(isProtect);
                usgMsg.prcBookMdseCd_U.setInputProtected(isProtect);
                usgMsg.contrMtrMultRate_U.setInputProtected(isProtect);
                usgMsg.xxChkBox_U.setInputProtected(isProtect);
                usgMsg.prcSvcTierTpCd_U.setInputProtected(isProtect);
                usgMsg.mlyCopyInclPrcQty_U.setInputProtected(isProtect);
                usgMsg.minCopyVolCnt_U.setInputProtected(isProtect);
                usgMsg.maxCopyVolCnt_U.setInputProtected(isProtect);
                usgMsg.xsMtrAmtRate_U.setInputProtected(isProtect);
                usgMsg.billToCustCd_U.setInputProtected(isProtect);
                usgMsg.dsAcctNm_U.setInputProtected(isProtect); // 2017/03/29
                // S21_NA#18171
                // Mod
                usgMsg.billToLocNum_U.setInputProtected(isProtect);
                usgMsg.xxBillToAcctNmSrchTxt_U.setInputProtected(true);

                // Add Start 2018/05/08 QC#25030
                usgMsg.bllgFreeCopyCnt_U.setInputProtected(true);
                usgMsg.bllgMinCnt_U.setInputProtected(true);
                usgMsg.bllgMinAmtRate_U.setInputProtected(true);
                usgMsg.bllgRollOverRatio_U.setInputProtected(true);
                // Add End 2018/05/08 QC#25030
            }
            // Accessory Charge Detail
            scrnMsg.prcCatgNm_HJ.setInputProtected(isImport(scrnMsg));
            for (int i = 0; i < scrnMsg.J.length(); i++) {
                NWAL2180_JBMsg acsryMsg = scrnMsg.J.no(i);
                boolean isProtect = ZYPConstant.FLG_OFF_N.equals(acsryMsg.scrEntAvalFlg_J.getValue());
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM, i, !isProtect);
                handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_ACP, i, !isProtect);
                acsryMsg.mdseCd_J.setInputProtected(isProtect);
                acsryMsg.addlBasePrcDealAmt_J.setInputProtected(isProtect);
                acsryMsg.prcCatgNm_J.setInputProtected(isProtect);
                acsryMsg.xxChkBox_J.setInputProtected(isProtect);
            }
            // Rental Equipment Charge Base & Accessory
            scrnMsg.prcCatgNm_HB.setInputProtected(isImport(scrnMsg));
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                NWAL2180_BBMsg acsryMsg = scrnMsg.B.no(i);
                boolean isProtect = ZYPConstant.FLG_OFF_N.equals(acsryMsg.scrEntAvalFlg_B.getValue());
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM_RE, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM_RE, i, !isProtect);
                handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_REP, i, !isProtect);
                acsryMsg.mdseCd_B.setInputProtected(isProtect);
                acsryMsg.addlBasePrcDealAmt_B.setInputProtected(isProtect);
                acsryMsg.prcCatgNm_B.setInputProtected(isProtect);
                acsryMsg.prcListEquipConfigNm_B.setInputProtected(true);
                acsryMsg.xxChkBox_B.setInputProtected(isProtect);
            }
            // AdditionalService Charge Detail
            scrnMsg.prcCatgNm_C.setInputProtected(isImport(scrnMsg));
            for (int i = 0; i < scrnMsg.C.length(); i++) {
                NWAL2180_CBMsg addtionlMsg = scrnMsg.C.no(i);
                boolean isProtect = ZYPConstant.FLG_OFF_N.equals(addtionlMsg.scrEntAvalFlg_C.getValue());
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_UNIT, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_UNIT, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_ADD, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_ADD, i, !isProtect);
                addtionlMsg.mdseCd_CU.setInputProtected(isProtect);
                addtionlMsg.mdseCd_CI.setInputProtected(isProtect);
                addtionlMsg.addlChrgPrcDealAmt_C.setInputProtected(isProtect);
                addtionlMsg.xxChkBox_C.setInputProtected(isProtect);

                // Add Start 2018/05/08 QC#25030
                addtionlMsg.printDtlFlg_C.setInputProtected(true);
                // Add End 2018/05/08 QC#25030
            }
            setInputProtectedOnChangeForUsage(scrnMsg);
            setInputProtectedOnChangeForUsageConfig(scrnMsg);

            boolean isEnable = !isImport(scrnMsg);
            handler.setButtonEnabled(BUTTON_APPLY_BUNDLE_PRICE, isEnable);
            handler.setButtonEnabled(BUTTON_ADD_ACCESSORY, isEnable);
            handler.setButtonEnabled(BUTTON_DEL_ACCESSORY, isEnable);
            handler.setButtonEnabled(BUTTON_ADD_ACCESSORY_RE, false);
            handler.setButtonEnabled(BUTTON_DEL_ACCESSORY_RE, false);
            handler.setButtonEnabled(BUTTON_ADD_SERVICE, isEnable);
            handler.setButtonEnabled(BUTTON_DEL_SERVICE, isEnable);

        } else {

            setInputProtectedOnChangeForUsage(scrnMsg);
            // all protect
            handler.setButtonEnabled(BUTTON_APPLY_BUNDLE_PRICE, false);
            handler.setButtonEnabled(BUTTON_ADD_ACCESSORY, false);
            handler.setButtonEnabled(BUTTON_DEL_ACCESSORY, false);
            handler.setButtonEnabled(BUTTON_ADD_ACCESSORY_RE, false);
            handler.setButtonEnabled(BUTTON_DEL_ACCESSORY_RE, false);
            handler.setButtonEnabled(BUTTON_ADD_SERVICE, false);
            handler.setButtonEnabled(BUTTON_DEL_SERVICE, false);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NWAL2180_ABMsg prcHeaderMsg = scrnMsg.A.no(i);
                setTierLinkCtrl(handler, scrnMsg, i);

                prcHeaderMsg.prcCatgNm_A.setInputProtected(true);
                prcHeaderMsg.prcMtrPkgPk_A.setInputProtected(true);
                prcHeaderMsg.prcTierSvcOfferCd_A.setInputProtected(true);
                prcHeaderMsg.xxTotPrcAmt_PB.setInputProtected(true);
                prcHeaderMsg.shpgIntvlMthNum_A.setInputProtected(true);

                handler.setButtonEnabled(BUTTON_APPLY_ALL_MODEL, i, false);
            }
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                NWAL2180_RBMsg prcHeaderMsg = scrnMsg.R.no(i);
                setTierLinkCtrlConfig(handler, scrnMsg, i);

                handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, i, false);

                prcHeaderMsg.prcCatgNm_R.setInputProtected(true);
                prcHeaderMsg.prcMtrPkgPk_R.setInputProtected(true);
                prcHeaderMsg.prcTierSvcOfferCd_R.setInputProtected(true);
                prcHeaderMsg.xxTotPrcAmt_PR.setInputProtected(true);
                prcHeaderMsg.shpgIntvlMthNum_R.setInputProtected(true);
                prcHeaderMsg.billToCustCd_R.setInputProtected(true);
                prcHeaderMsg.dsAcctNm_R.setInputProtected(true); // 2017/03/29
                // S21_NA#18171
                // Mod
                prcHeaderMsg.billToLocNum_R.setInputProtected(true);
                prcHeaderMsg.xxBillToAcctNmSrchTxt_R.setInputProtected(true);
            }
            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.length(); i++) {
                NWAL2180_ZBMsg pricingMsg = scrnMsg.Z.no(i);
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM, i, false);
                pricingMsg.prcListBandDescTxt_Z.setInputProtected(true);
                pricingMsg.prcBookMdseCd_Z.setInputProtected(true);
                pricingMsg.contrMtrMultRate_Z.setInputProtected(true);
                pricingMsg.xxChkBox_Z.setInputProtected(true);
                pricingMsg.prcSvcTierTpCd_Z.setInputProtected(true);
                pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(true);
                pricingMsg.minCopyVolCnt_Z.setInputProtected(true);
                pricingMsg.maxCopyVolCnt_Z.setInputProtected(true);
                pricingMsg.xsMtrAmtRate_Z.setInputProtected(true);
            }
            for (int i = 0; i < scrnMsg.U.length(); i++) {
                NWAL2180_UBMsg usgMsg = scrnMsg.U.no(i);
                handler.setButtonEnabled(BUTTON_BAND_SEARCH_CONFIG, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM_CONFIG, i, false);
                usgMsg.prcListBandDescTxt_U.setInputProtected(true);
                usgMsg.prcBookMdseCd_U.setInputProtected(true);
                usgMsg.contrMtrMultRate_U.setInputProtected(true);
                usgMsg.xxChkBox_U.setInputProtected(true);
                usgMsg.prcSvcTierTpCd_U.setInputProtected(true);
                usgMsg.mlyCopyInclPrcQty_U.setInputProtected(true);
                usgMsg.minCopyVolCnt_U.setInputProtected(true);
                usgMsg.maxCopyVolCnt_U.setInputProtected(true);
                usgMsg.xsMtrAmtRate_U.setInputProtected(true);
                usgMsg.billToCustCd_U.setInputProtected(true);
                usgMsg.dsAcctNm_U.setInputProtected(true); // 2017/03/29
                // S21_NA#18171
                // Mod
                usgMsg.billToLocNum_U.setInputProtected(true);
                usgMsg.xxBillToAcctNmSrchTxt_U.setInputProtected(true);
            }
            // Accessory Charge Detail
            scrnMsg.prcCatgNm_HJ.setInputProtected(true);
            for (int i = 0; i < scrnMsg.J.length(); i++) {
                NWAL2180_JBMsg acsryMsg = scrnMsg.J.no(i);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM_RE, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM_RE, i, false);
                handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_REP, i, false);
                acsryMsg.mdseCd_J.setInputProtected(true);
                acsryMsg.addlBasePrcDealAmt_J.setInputProtected(true);
                acsryMsg.prcCatgNm_J.setInputProtected(true);
            }
            // Rental Equipment Charge Base & Accessory
            scrnMsg.prcCatgNm_HB.setInputProtected(true);
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                NWAL2180_BBMsg acsryMsg = scrnMsg.B.no(i);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM, i, false);
                handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_ACP, i, false);
                acsryMsg.mdseCd_B.setInputProtected(true);
                acsryMsg.addlBasePrcDealAmt_B.setInputProtected(true);
                acsryMsg.prcCatgNm_B.setInputProtected(true);
                acsryMsg.prcListEquipConfigNum_B.setInputProtected(true);
            }
            // AdditionalService Charge Detail
            scrnMsg.prcCatgNm_C.setInputProtected(true);
            for (int i = 0; i < scrnMsg.C.length(); i++) {
                NWAL2180_CBMsg addtionlMsg = scrnMsg.C.no(i);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_UNIT, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_UNIT, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_ADD, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_ADD, i, false);
                addtionlMsg.mdseCd_CU.setInputProtected(true);
                addtionlMsg.mdseCd_CI.setInputProtected(true);
                addtionlMsg.addlChrgPrcDealAmt_C.setInputProtected(true);
            }
        }
    }

    /**
     * Set Button Enable Init
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2180BMsg
     */
    public static void initButton(EZDCommonHandler handler, NWAL2180BMsg scrnMsg) {

        handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, false);
        setBandButtonControl(handler, scrnMsg);
        handler.setButtonEnabled(BUTTON_APPLY_ALL_HEADER, false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled(BUTTON_APPLY_ALL_MODEL, i, false);
            NWAL2180_ABMsg aScrnMsg = scrnMsg.A.no(i);
            if (isTier(aScrnMsg)) {
                for (int ixZ = 0; ixZ < scrnMsg.Z.getValidCount(); ixZ++) {
                    NWAL2180_ZBMsg zScrnMsg = scrnMsg.Z.no(i);
                    if (isFleet(scrnMsg) || aScrnMsg.mdlId_A.getValue().compareTo(zScrnMsg.mdlId_Z.getValue()) == 0) {
                        handler.setButtonEnabled("Tier#" + ixZ, true);
                    }
                }
            }
        }
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM, true);
        handler.setButtonEnabled(BUTTON_ADD_ACCESSORY, true);
        handler.setButtonEnabled(BUTTON_DEL_ACCESSORY, true);
        handler.setButtonEnabled(BUTTON_ADD_ACCESSORY_RE, !isRentalOrder(scrnMsg));
        handler.setButtonEnabled(BUTTON_DEL_ACCESSORY_RE, !isRentalOrder(scrnMsg));
        handler.setButtonEnabled(BUTTON_APPLY_BUNDLE_PRICE, true);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM, true);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM, true);
        handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_ACP, true);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM_RE, true);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM_RE, true);
        handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_REP, true);

        handler.setButtonEnabled(BUTTON_ADD_SERVICE, true);
        handler.setButtonEnabled(BUTTON_DEL_SERVICE, true);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_UNIT, true);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_UNIT, true);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_ADD, true);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_ADD, true);

        NWAL2180CommonLogic.setButtonControlForConfigPricing(handler, scrnMsg);
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setBandButtonControl(EZDCommonHandler handler, NWAL2180BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            NWAL2180_ZBMsg zScrnMsg = scrnMsg.Z.no(i);
            if (XX_FLG_PARENT.equals(zScrnMsg.xxFlgNm_Z.getValue())) {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, true);

            } else {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, false);
            }
        }
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setBandButtonControlConfig(EZDCommonHandler handler, NWAL2180BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NWAL2180_UBMsg uScrnMsg = scrnMsg.U.no(i);
            if (XX_FLG_PARENT.equals(uScrnMsg.xxFlgNm_U.getValue())) {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, true);

            } else {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, false);
            }
        }
    }

    /**
     * @param scrnMsg NWAL2180BMsg
     * @return if Contract Indicator is "Fleet" then return true.
     */
    public static boolean isFleet(NWAL2180BMsg scrnMsg) {
        return DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue());
    }

    private static boolean isTier(NWAL2180_ABMsg aScrnMsg) {
        return ZYPCommonFunc.hasValue(aScrnMsg.prcTierSvcOfferCd_A);
    }

    /**
     * add CheckItems
     * @param scrnMsg NWAL2180BMsg
     */
    public static void addCheckItems(NWAL2180BMsg scrnMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.manContrOvrdRsnNm);
            scrnMsg.addCheckItem(scrnMsg.manContrOvrdCmntTxt);
        }
        scrnMsg.addCheckItem(scrnMsg.basePrcDealAmt);
        scrnMsg.addCheckItem(scrnMsg.dsContrPk);
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_C);

        int size = 0;
        if (isFleet(scrnMsg)) {
            size = scrnMsg.A.getValidCount();
            for (int i = 0; i < size; i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcMtrPkgPk_A);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcTierSvcOfferCd_A);
            }
            size = scrnMsg.Z.getValidCount();
            for (int i = 0; i < size; i++) {
                scrnMsg.addCheckItem(scrnMsg.Z.no(i).prcListBandDescTxt_Z);
                scrnMsg.addCheckItem(scrnMsg.Z.no(i).prcBookMdseCd_Z);
                scrnMsg.addCheckItem(scrnMsg.Z.no(i).contrMtrMultRate_Z);
                scrnMsg.addCheckItem(scrnMsg.Z.no(i).xxChkBox_Z);
                scrnMsg.addCheckItem(scrnMsg.Z.no(i).prcSvcTierTpCd_Z);
                scrnMsg.addCheckItem(scrnMsg.Z.no(i).minCopyVolCnt_Z);
                scrnMsg.addCheckItem(scrnMsg.Z.no(i).maxCopyVolCnt_Z);
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.Z.no(i).xxSmryLineFlg_Z.getValue())//
                        && !ZYPCommonFunc.hasValue(scrnMsg.Z.no(i).regMtrLbCd_Z)) {
                    scrnMsg.addCheckItem(scrnMsg.Z.no(i).mlyCopyInclPrcQty_Z);
                    scrnMsg.addCheckItem(scrnMsg.Z.no(i).xsMtrAmtRate_Z);
                }
                scrnMsg.addCheckItem(scrnMsg.Z.no(i).mtrLbDescTxt_ZB);
            }
        } else {
            size = scrnMsg.R.getValidCount();
            for (int i = 0; i < size; i++) {
                scrnMsg.addCheckItem(scrnMsg.R.no(i).prcMtrPkgPk_R);
                scrnMsg.addCheckItem(scrnMsg.R.no(i).prcTierSvcOfferCd_R);
            }
            size = scrnMsg.U.getValidCount();
            for (int i = 0; i < size; i++) {
                NWAL2180_UBMsg uScrnMsg = scrnMsg.U.no(i);
                if (ZYPConstant.FLG_OFF_N.equals(uScrnMsg.actvFlg_U.getValue())) {
                    continue;
                }
                scrnMsg.addCheckItem(uScrnMsg.prcListBandDescTxt_U);
                scrnMsg.addCheckItem(uScrnMsg.prcBookMdseCd_U);
                scrnMsg.addCheckItem(uScrnMsg.contrMtrMultRate_U);
                scrnMsg.addCheckItem(uScrnMsg.prcListBandDescTxt_U);
                scrnMsg.addCheckItem(uScrnMsg.xxChkBox_U);
                scrnMsg.addCheckItem(uScrnMsg.prcSvcTierTpCd_U);
                scrnMsg.addCheckItem(uScrnMsg.minCopyVolCnt_U);
                scrnMsg.addCheckItem(uScrnMsg.maxCopyVolCnt_U);
                if (ZYPCommonFunc.hasValue(uScrnMsg.dsImptSvcUsgPrcPk_U) //
                        && ZYPConstant.FLG_ON_Y.equals(uScrnMsg.xxSmryLineFlg_U.getValue()) //
                        && !ZYPCommonFunc.hasValue(uScrnMsg.regMtrLbCd_U)) {
                    scrnMsg.addCheckItem(uScrnMsg.mlyCopyInclPrcQty_U);
                    scrnMsg.addCheckItem(uScrnMsg.xsMtrAmtRate_U);
                }
                scrnMsg.addCheckItem(uScrnMsg.mtrLbDescTxt_UB);
            }

        }
        size = scrnMsg.J.getValidCount();
        for (int i = 0; i < size; i++) {
            scrnMsg.addCheckItem(scrnMsg.J.no(i).xxChkBox_J);
            scrnMsg.addCheckItem(scrnMsg.J.no(i).mdseCd_J);
            scrnMsg.addCheckItem(scrnMsg.J.no(i).addlBasePrcDealAmt_J);
            scrnMsg.addCheckItem(scrnMsg.J.no(i).prcCatgNm_J);
        }
        size = scrnMsg.B.getValidCount();
        for (int i = 0; i < size; i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseCd_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).addlBasePrcDealAmt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcCatgNm_B);
        }
        size = scrnMsg.C.getValidCount();
        for (int i = 0; i < size; i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_C);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).mdseCd_CU);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).mdseCd_CI);
            scrnMsg.addCheckItem(scrnMsg.C.no(i).addlChrgPrcDealAmt_C);
        }
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param scrnMsg String
     * @param ixA index of R.
     */
    public static void setTierLinkCtrl(EZDCommonHandler handler, NWAL2180BMsg scrnMsg, int ixA) {
        NWAL2180_ABMsg aScrnMsg = scrnMsg.A.no(ixA);
        BigDecimal mdlId = aScrnMsg.mdlId_A.getValue();
        String prcTierSvcOfferCd = aScrnMsg.prcTierSvcOfferCd_A.getValue();

        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                    && ZYPCommonFunc.hasValue(mdlId) //
                    && ZYPCommonFunc.hasValue(scrnMsg.Z.no(i).mdlId_Z) //
                    && mdlId.compareTo(scrnMsg.Z.no(i).mdlId_Z.getValue()) != 0) {
                continue;
            }
            boolean isProtect = !ZYPCommonFunc.hasValue(prcTierSvcOfferCd); //
            scrnMsg.Z.no(i).xxScrEdtTpCd_ZL.setInputProtected(isProtect);
            // 2018/07/06 S21_NA#26528 Add Start
            if(!isProtect){
                ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(i).xxScrEdtTpCd_Z, ZYPConstant.FLG_ON_Y);
            }
            // 2018/07/06 S21_NA#26528 Add End
        }
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param scrnMsg String
     * @param ixR index of R.
     */
    public static void setTierLinkCtrlConfig(EZDCommonHandler handler, NWAL2180BMsg scrnMsg, int ixR) {
        NWAL2180_RBMsg rScrnMsg = scrnMsg.R.no(ixR);
        BigDecimal mdlId = rScrnMsg.mdlId_R.getValue();
        BigDecimal cpoSvcConfigRefPk = rScrnMsg.cpoSvcConfigRefPk_R.getValue();
        String prcTierSvcOfferCd = rScrnMsg.prcTierSvcOfferCd_R.getValue();

        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                    || !isSameBigDecimal(scrnMsg.U.no(i).mdlId_U.getValue(), mdlId) //
                    || !isSameBigDecimal(scrnMsg.U.no(i).cpoSvcConfigRefPk_U.getValue(), cpoSvcConfigRefPk)) {
                continue;
            }
            boolean isProtect = !ZYPCommonFunc.hasValue(prcTierSvcOfferCd); //
            scrnMsg.U.no(i).xxScrEdtTpCd_UL.setInputProtected(isProtect);
            // 2018/07/06 S21_NA#26528 Add Start
            if(!isProtect){
                ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(i).xxScrEdtTpCd_U, ZYPConstant.FLG_ON_Y);
            }
            // 2018/07/06 S21_NA#26528 Add End
        }
    }

    /**
     * @param val1 BigDecimal
     * @param val2 BigDecimal
     * @return if val1 = val2 then return true.
     */
    private static boolean isSameBigDecimal(BigDecimal val1, BigDecimal val2) {
        return ZYPCommonFunc.hasValue(val1) //
                && ZYPCommonFunc.hasValue(val2) //
                && val1.compareTo(val2) == 0;
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2180BMsg
     * @param sts boolean
     * @param ixZ int
     */
    public static void setBandButton(EZDCommonHandler handler, NWAL2180BMsg scrnMsg, boolean sts, int ixZ) {
        int ixA = getPrcDtlIndex(scrnMsg, ixZ);
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(ixA).scrEntAvalFlg_A.getValue())) {
            sts = false;
        }
        handler.setButtonEnabled(BUTTON_BAND_SEARCH, ixZ, sts);
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2180BMsg
     * @param sts boolean
     * @param ixU int
     */
    public static void setBandButtonConfig(EZDCommonHandler handler, NWAL2180BMsg scrnMsg, boolean sts, int ixU) {
        int ixR = getPrcDtlIndexConfig(scrnMsg, ixU);
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.R.no(ixR).scrEntAvalFlg_R.getValue())) {
            sts = false;
        }
        handler.setButtonEnabled(BUTTON_BAND_SEARCH_CONFIG, ixU, sts);
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param aScrnMsg NWAL2180_ABMsg
     * @param index int
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setSupplyButton(EZDCommonHandler handler, NWAL2180_ABMsg aScrnMsg, int index, NWAL2180BMsg scrnMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                && PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(aScrnMsg.prcListTpCd_A.getValue()) //
                && ZYPCommonFunc.hasValue(aScrnMsg.prcMtrPkgPk_A)) {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, true);
        } else {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, false);
        }
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param rScrnMsg NWAL2180_RBMsg
     * @param index int
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setSupplyButton(EZDCommonHandler handler, NWAL2180_RBMsg rScrnMsg, int index, NWAL2180BMsg scrnMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                && PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(rScrnMsg.prcListTpCd_R.getValue()) //
                && ZYPCommonFunc.hasValue(rScrnMsg.prcMtrPkgPk_R)) {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, true);
        } else {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, false);
        }
    }

    /**
     * isParentLine
     * @param zScrnMsg NWAL2180_ZBMsg
     * @return if parent(billing meter) line then return true.
     */
    public static boolean isParentLine(NWAL2180_ZBMsg zScrnMsg) {
        return XX_FLG_PARENT.equals(zScrnMsg.xxFlgNm_Z.getValue());
    }

    /**
     * isParentLine
     * @param uScrnMsg NWAL2180_ZBMsg
     * @return if parent(billing meter) line then return true.
     */
    public static boolean isParentLine(NWAL2180_UBMsg uScrnMsg) {
        return XX_FLG_PARENT.equals(uScrnMsg.xxFlgNm_U.getValue());
    }

    /**
     * item protect for reference mode.
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2180BMsg
     */
    public static void protectRefMode(S21CommonHandler handler, NWAL2180BMsg scrnMsg) {
        // Input Protect
        scrnMsg.xxScrItem50Txt.setInputProtected(true);
        scrnMsg.dsImptSvcLineNum.setInputProtected(true);
        scrnMsg.manContrOvrdFlg.setInputProtected(true);
        scrnMsg.manContrOvrdRsnNm.setInputProtected(true);
        scrnMsg.manContrOvrdCmntTxt.setInputProtected(true);

        scrnMsg.basePrcDealAmt.setInputProtected(true);
        scrnMsg.dsContrNum.setInputProtected(true);

        // Service Pricing Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL2180_ABMsg pricingMsg = scrnMsg.A.no(i);

            pricingMsg.t_MdlNm_A.setInputProtected(true);
            pricingMsg.xxTotQtyCnt_A.setInputProtected(true);
            pricingMsg.prcCatgNm_A.setInputProtected(true);
            pricingMsg.prcMtrPkgPk_A.setInputProtected(true);
            pricingMsg.prcTierSvcOfferCd_A.setInputProtected(true);
            pricingMsg.shpgIntvlMthNum_A.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_PB.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_EB.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_TB.setInputProtected(true);
        }
        scrnMsg.prcCatgNm_HB.setInputProtected(true);
        scrnMsg.prcCatgNm_C.setInputProtected(true);

        // Usage
        for (int i = 0; i < scrnMsg.Z.length(); i++) {
            NWAL2180_ZBMsg pricingMsg = scrnMsg.Z.no(i);
            pricingMsg.prcListBandDescTxt_Z.setInputProtected(true);
            pricingMsg.prcBookMdseCd_Z.setInputProtected(true);
            pricingMsg.mtrLbDescTxt_ZB.setInputProtected(true);
            pricingMsg.mdseDescShortTxt_Z.setInputProtected(true);
            pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);
            pricingMsg.contrMtrMultRate_Z.setInputProtected(true);
            pricingMsg.xxChkBox_Z.setInputProtected(true);
            pricingMsg.prcSvcTierTpCd_Z.setInputProtected(true);
            pricingMsg.mlyCopyInclPrcQty_Z.setInputProtected(true);
            pricingMsg.minCopyVolCnt_Z.setInputProtected(true);
            pricingMsg.maxCopyVolCnt_Z.setInputProtected(true);
            pricingMsg.xsMtrAmtRate_Z.setInputProtected(true);
        }

        // Accessory Charge Detail
        for (int i = 0; i < scrnMsg.B.length(); i++) {

            NWAL2180_BBMsg acsryMsg = scrnMsg.B.no(i);
            acsryMsg.xxChkBox_B.setInputProtected(true);
            acsryMsg.xxLineNum_B.setInputProtected(true);
            acsryMsg.mdseCd_B.setInputProtected(true);
            acsryMsg.mdseDescShortTxt_B.setInputProtected(true);
            acsryMsg.addlBasePrcDealAmt_B.setInputProtected(true);
            acsryMsg.prcCatgNm_B.setInputProtected(true);
            acsryMsg.prcListEquipConfigNum_B.setInputProtected(true);
        }
        // AdditionalService Charge Detail
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NWAL2180_CBMsg addtionlMsg = scrnMsg.C.no(i);

            addtionlMsg.xxChkBox_C.setInputProtected(true);
            addtionlMsg.xxLineNum_C.setInputProtected(true);
            addtionlMsg.mdseCd_CU.setInputProtected(true);
            addtionlMsg.mdseDescShortTxt_CU.setInputProtected(true);
            addtionlMsg.mdseCd_CI.setInputProtected(true);
            addtionlMsg.mdseDescShortTxt_CI.setInputProtected(true);
            addtionlMsg.addlChrgPrcDealAmt_C.setInputProtected(true);
        }

        // buttons
        handler.setButtonEnabled(BUTTON_REFRESH, false);
        handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, false);
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            if (XX_FLG_PARENT.equals(scrnMsg.Z.no(i).xxFlgNm_Z.getValue())) {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, false);
            } else {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, false);
            }
        }
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM, false);
        handler.setButtonEnabled(BUTTON_ADD_ACCESSORY, false);
        handler.setButtonEnabled(BUTTON_DEL_ACCESSORY, false);
        handler.setButtonEnabled(BUTTON_ADD_ACCESSORY_RE, false);
        handler.setButtonEnabled(BUTTON_DEL_ACCESSORY_RE, false);
        handler.setButtonEnabled(BUTTON_APPLY_BUNDLE_PRICE, false);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM, false);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM, false);
        handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_ACP, false);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM_RE, false);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM_RE, false);
        handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_REP, false);
        handler.setButtonEnabled(BUTTON_ADD_SERVICE, false);
        handler.setButtonEnabled(BUTTON_DEL_SERVICE, false);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_UNIT, false);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_UNIT, false);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_ADD, false);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_ADD, false);

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * <pre>
     * @param scrnMsg   NWAL2180BMsg
     * @param handler   EZDCommonHandler
     * </pre>
     */
    public static void setUsgPrcAreaCtrl(NWAL2180BMsg scrnMsg, EZDCommonHandler handler) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2180_ABMsg aScrnMsg = scrnMsg.A.no(i);
            String dspVal = "";
            if (!ZYPCommonFunc.hasValue(aScrnMsg.prcMtrPkgPk_A) //
                    || isRateTypeAnuual(aScrnMsg.prcRateTpCd_A.getValue())) { // QC#4631
                dspVal = "none";
                scrnMsg.A.no(i).prcTierSvcOfferCd_A.clear();
                scrnMsg.A.no(i).prcTierSvcOfferCd_A.setInputProtected(true);
            } else {
                setTierLinkCtrl(handler, scrnMsg, i);
                scrnMsg.A.no(i).prcTierSvcOfferCd_A.setInputProtected(false);
            }
            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCREEN_ID, "usgPrc" + i);
            guiAttr.setStyleAttribute("display", dspVal);
            scrnMsg.addGUIAttribute(guiAttr);
        }
        NWAL2180CommonLogic.setUsgPrcAreaCtrlConfig(scrnMsg, handler);
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL2180BMsg
     * @param handler   EZDCommonHandler
     * </pre>
     */
    public static void setUsgPrcAreaCtrlConfig(NWAL2180BMsg scrnMsg, EZDCommonHandler handler) {
        List<String> tierConfigList = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NWAL2180_RBMsg rScrnMsg = scrnMsg.R.no(i);
            if (!ZYPCommonFunc.hasValue(rScrnMsg.mdlId_R)) {
                continue;
            }
            String dspVal = "";
            if (!ZYPCommonFunc.hasValue(rScrnMsg.prcMtrPkgPk_R) //
                    || isRateTypeAnuual(rScrnMsg.prcRateTpCd_R.getValue())) {
                dspVal = "none";
                rScrnMsg.prcTierSvcOfferCd_R.clear();
                rScrnMsg.prcTierSvcOfferCd_R.setInputProtected(true);
            } else {
                setTierLinkCtrlConfig(handler, scrnMsg, i);
                rScrnMsg.prcTierSvcOfferCd_R.setInputProtected(false);
            }
            EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCREEN_ID, "usgPrcConfig" + i);
            guiAttr.setStyleAttribute("display", dspVal);
            scrnMsg.addGUIAttribute(guiAttr);

            if (ZYPCommonFunc.hasValue(rScrnMsg.prcTierSvcOfferCd_R)) {
                if (!tierConfigList.contains(rScrnMsg.dsOrdPosnNum_R.getValue())) {
                    tierConfigList.add(rScrnMsg.dsOrdPosnNum_R.getValue());
                }
            }
        }
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NWAL2180_UBMsg uScrnMsg = scrnMsg.U.no(i);
            boolean isProtected = false;
            if (tierConfigList.contains(uScrnMsg.dsOrdPosnNum_U.getValue())) {
                isProtected = true;
            }
            uScrnMsg.mlyCopyInclPrcQty_U.setInputProtected(isProtected);
            uScrnMsg.xsMtrAmtRate_U.setInputProtected(isProtected);
        }
    }

    /**
     * <pre>
     * @param scrnMsg   NWAL2180BMsg
     * </pre>
     */
    public static void setAccessoryChargeAreaCtrl(NWAL2180BMsg scrnMsg) {
        String dspVal = "";
        EZDGUIAttribute guiAttrRe = new EZDGUIAttribute(SCREEN_ID, "rentalEquip");
        if (!isRentalOrder(scrnMsg)) {
            dspVal = "none";
        }
        guiAttrRe.setStyleAttribute("display", dspVal);
        scrnMsg.addGUIAttribute(guiAttrRe);

        dspVal = "";
        EZDGUIAttribute guiAttrAsc = new EZDGUIAttribute(SCREEN_ID, "addlSvcChrg");
        EZDGUIAttribute guiAttrMsp = new EZDGUIAttribute(SCREEN_ID, "mdlSvcPrc");
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
            dspVal = "none";
        }
        guiAttrAsc.setStyleAttribute("display", dspVal);
        guiAttrMsp.setStyleAttribute("display", dspVal);
        scrnMsg.addGUIAttribute(guiAttrAsc);
        scrnMsg.addGUIAttribute(guiAttrMsp);
    }

    /**
     * @param scrnMsg
     * @return
     */
    private static boolean isRentalOrder(NWAL2180BMsg scrnMsg) {
        return ZYPConstant.FLG_ON_Y.equals(scrnMsg.rntlOrdFlg.getValue());
    }

    /**
     * setScrnDspCtrl
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setScrnDspCtrl(NWAL2180BMsg scrnMsg) {
        setScrnManOvrdDspCtrl(scrnMsg);

    }

    /**
     * setScrnManOvrdDspCtrl
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setScrnManOvrdDspCtrl(NWAL2180BMsg scrnMsg) {
        String dspVal = "";
        String bodyHeight = "375px";
        if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue())) {
            dspVal = "none";
            bodyHeight = "395px";
        }
        EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCREEN_ID, "manOvrd");
        guiAttr.setStyleAttribute("display", dspVal);
        scrnMsg.addGUIAttribute(guiAttr);

        EZDGUIAttribute guiAttrBody = new EZDGUIAttribute(SCREEN_ID, "body");
        guiAttrBody.setStyleAttribute("height", bodyHeight);
        scrnMsg.addGUIAttribute(guiAttrBody);
    }

    /**
     * Set Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setNWAL2160ReturnInfo(NWAL2180BMsg scrnMsg) {
        List<Integer> delList = new ArrayList<Integer>();
        int ixZ = scrnMsg.xxNum_Z.getValueInt();
        NWAL2180_ZBMsg zScrnMsg = scrnMsg.Z.no(ixZ);
        BigDecimal mdlId = zScrnMsg.mdlId_Z.getValue();
        String mtrLbCd = zScrnMsg.bllgMtrLbCd_Z.getValue();

        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            NWAL2180_XBMsg xScrnMsg = scrnMsg.X.no(i);
            if (!isFleet(scrnMsg) && mdlId.compareTo(xScrnMsg.mdlId_X.getValue()) != 0) {
                continue;
            }
            if (mtrLbCd.equals(xScrnMsg.bllgMtrLbCd_X.getValue())) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(scrnMsg.X, delList);

        int ixX = scrnMsg.X.getValidCount();
        for (int ixQ = 0; ixQ < scrnMsg.Q.getValidCount(); ixQ++) {
            NWAL2180_XBMsg xScrnMsg = scrnMsg.X.no(ixX);
            NWAL2180_QBMsg qScrnMsg = scrnMsg.Q.no(ixQ);

            EZDMsg.copy(qScrnMsg, "Q", xScrnMsg, "X");
            ZYPEZDItemValueSetter.setValue(xScrnMsg.mdlId_X, mdlId);

            ixX++;
        }
        scrnMsg.X.setValidCount(ixX);

        if (scrnMsg.Q.getValidCount() > 1) {
            zScrnMsg.xxScrEdtTpCd_Z.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setNWAL2160ReturnInfoConfig(NWAL2180BMsg scrnMsg) {
        List<Integer> delList = new ArrayList<Integer>();
        int ixU = scrnMsg.xxNum_Z.getValueInt();
        NWAL2180_UBMsg uScrnMsg = scrnMsg.U.no(ixU);
        BigDecimal mdlId = uScrnMsg.mdlId_U.getValue();
        BigDecimal cpoSvcConfigRefPk = uScrnMsg.cpoSvcConfigRefPk_U.getValue();
        String dsOrdPosnNum = uScrnMsg.dsOrdPosnNum_U.getValue();
        String mtrLbCd = uScrnMsg.bllgMtrLbCd_U.getValue();

        for (int i = 0; i < scrnMsg.V.getValidCount(); i++) {
            NWAL2180_VBMsg vScrnMsg = scrnMsg.V.no(i);
            if (!isSameBigDecimal(mdlId, vScrnMsg.mdlId_V.getValue()) //
                    || !isSameBigDecimal(cpoSvcConfigRefPk, vScrnMsg.cpoSvcConfigRefPk_V.getValue())) {
                continue;
            }
            if (mtrLbCd.equals(vScrnMsg.bllgMtrLbCd_V.getValue())) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(scrnMsg.V, delList);

        int ixV = scrnMsg.V.getValidCount();
        for (int ixQ = 0; ixQ < scrnMsg.Q.getValidCount(); ixQ++) {
            NWAL2180_VBMsg vScrnMsg = scrnMsg.V.no(ixV);
            NWAL2180_QBMsg qScrnMsg = scrnMsg.Q.no(ixQ);

            EZDMsg.copy(qScrnMsg, "Q", vScrnMsg, "V");
            ZYPEZDItemValueSetter.setValue(vScrnMsg.mdlId_V, mdlId);
            ZYPEZDItemValueSetter.setValue(vScrnMsg.cpoSvcConfigRefPk_V, cpoSvcConfigRefPk);
            ZYPEZDItemValueSetter.setValue(vScrnMsg.dsOrdPosnNum_V, dsOrdPosnNum);

            ixV++;
        }
        scrnMsg.V.setValidCount(ixV);

        if (scrnMsg.Q.getValidCount() > 1) {
            uScrnMsg.xxScrEdtTpCd_U.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * @param scrnMsg NWAL2180BMsg
     * @param ixZ int
     * @return index of Price detail.
     */
    public static int getPrcDtlIndex(NWAL2180BMsg scrnMsg, int ixZ) {
        if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            return 0;
        }
        BigDecimal mdlId = scrnMsg.Z.no(ixZ).mdlId_Z.getValue();
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return 0;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlId_A) //
                    && mdlId.compareTo(scrnMsg.A.no(i).mdlId_A.getValue()) == 0) {
                return i;
            }
        }
        return 0;
    }

    /**
     * @param scrnMsg NWAL2180BMsg
     * @param ixU int
     * @return index of Price detail.
     */
    public static int getPrcDtlIndexConfig(NWAL2180BMsg scrnMsg, int ixU) {
        if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            return 0;
        }
        BigDecimal mdlId = scrnMsg.U.no(ixU).mdlId_U.getValue();
        BigDecimal cpoSvcConfigRefPk = scrnMsg.U.no(ixU).cpoSvcConfigRefPk_U.getValue();
        if (!ZYPCommonFunc.hasValue(mdlId) && !ZYPCommonFunc.hasValue(cpoSvcConfigRefPk)) {
            return 0;
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.R.no(i).mdlId_R) //
                    && mdlId.compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0 //
                    && ZYPCommonFunc.hasValue(scrnMsg.R.no(i).cpoSvcConfigRefPk_R) //
                    && cpoSvcConfigRefPk.compareTo(scrnMsg.R.no(i).cpoSvcConfigRefPk_R.getValue()) == 0) {
                return i;
            }
        }
        return 0;
    }

    /**
     * setConfigPricingAreaCtrl
     * @param scrnMsg NWAL2180BMsg
     * @param isDispaly boolean
     */
    public static void setConfigPricingAreaCtrl(NWAL2180BMsg scrnMsg, boolean isDispaly) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxSmryLineFlg_A.setValue(ZYPConstant.FLG_OFF_N);
            setConfigPricingAreaCtrlByConfig(scrnMsg, i, isDispaly);
        }

        String bgColor = ZYPCodeDataUtil.getVarCharConstValue("NWAL2180_CONFIG_PRC_BGCOLOR", scrnMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(bgColor)) {
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCREEN_ID, "configSvcPrc" + i);
                guiAttr.setStyleAttribute("BACKGROUND-COLOR", bgColor);
                scrnMsg.addGUIAttribute(guiAttr);
            }
        }
    }

    /**
     * @param scrnMsg NWAL2180BMsg
     * @param ixA index of A Table.
     * @param isDisplay if config pricing area is display then true.
     */
    public static void setConfigPricingAreaCtrlByConfig(NWAL2180BMsg scrnMsg, int ixA, boolean isDisplay) {
        String cssValue = "";
        if (!isDisplay) {
            cssValue = "none";
        }
        EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCREEN_ID, "configPricing" + ixA);
        guiAttr.setStyleAttribute("display", cssValue);
        scrnMsg.addGUIAttribute(guiAttr);
    }

    /**
     * setCustomerCtrlConfig
     * @param scrnMsg NWAL2180BMsg
     */
    public static void setCustomerCtrlConfig(NWAL2180BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            scrnMsg.U.no(i).xxBillToAcctNmSrchTxt_U.setInputProtected(true);
        }
    }

    public static void setButtonControlForConfigPricing(EZDCommonHandler handler, NWAL2180BMsg scrnMsg) {
        for (int ixR = 0; ixR < scrnMsg.R.getValidCount(); ixR++) {
            handler.setButtonEnabled(BUTTON_ADD_ACTIVE_METER, ixR, false);
        }
        for (int ixU = 0; ixU < scrnMsg.U.length(); ixU++) {
            handler.setButtonEnabled(BUTTON_DEL_ACTIVE_METER, ixU, false);
        }
    }

}
