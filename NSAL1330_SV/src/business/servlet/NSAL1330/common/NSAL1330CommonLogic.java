package business.servlet.NSAL1330.common;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.AFTER_DECIMAL_DIGITS_AMT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.AFTER_DECIMAL_DIGITS_AMT_RATE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.AFTER_DECIMAL_DIGITS_MULTI_RATE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_APL;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_APR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_CLR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_DEL;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_DWL;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_RJT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_RST;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_RTN;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_SAV;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BTN_CMN_SUB;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ADD_ACCESSORY;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ADD_ACCESSORY_RE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ADD_ACTIVE_METER;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ADD_SERVICE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_APPLY_ALL_HEADER;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_APPLY_ALL_MODEL;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_APPLY_BUNDLE_PRICE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_BAND_SEARCH;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_BAND_SEARCH_CONFIG;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_DEL_ACCESSORY;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_DEL_ACCESSORY_RE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_DEL_ACTIVE_METER;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_DEL_SERVICE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_DESC_ADD;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_DESC_COVERED_ITEM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_DESC_COVERED_ITEM_RE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_DESC_COVERED_UNIT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_SEARCH_ADD;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_SEARCH_COVERED_ITEM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_SEARCH_COVERED_ITEM_RE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_SEARCH_COVERED_UNIT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM_CONFIG;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_REFRESH;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_SERVICE_PRICE_LIST_ACP;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_SERVICE_PRICE_LIST_REP;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_SUPPLY_AGREEMENT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.BUTTON_SUPPLY_AGREEMENT_MODEL;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.COMMA;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.CPO_SVC_CONFIG_ITEMS;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.DF_AMT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.DISP_HRCH_ACCT_CD_BILL;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.FLEET;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_CTY_ADDR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_FIRST_LINE_ADDR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_FRTH_LINE_ADDR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_POST_CD;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_SCD_LINE_ADDR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_ST_CD;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.IX_POP_PRM_THIRD_LINE_ADDR;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.MAX_BASE_AMT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NEWLINE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAL0320_MTR_MULT_RATE_FCT_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NWAL1130_PRM_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NWAL1760_PRM_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAL1340_PRM_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0652E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0656E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0658E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0661E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0667E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0675E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSZM0681E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0717E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0477E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_ADD_CHARGE_ITEM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_BAND_LIST;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_BAND_LIST_CONFIG;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_COVERED_UNIT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_RENTAL_EQUIP_COVERED_ITEM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.SCREEN_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.SINGLE_QUOTE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.STATUS_CD_ACTIVE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.XX_FLG_HARD;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.XX_FLG_PARENT;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.ZZM9000E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.ZZZM9025E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBItem;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1330.NSAL1330BMsg;
import business.servlet.NSAL1330.NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc_forConfigPricing;
import business.servlet.NSAL1330.NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc;
import business.servlet.NSAL1330.NSAL1330_ABMsg;
import business.servlet.NSAL1330.NSAL1330_BBMsg;
import business.servlet.NSAL1330.NSAL1330_CBMsg;
import business.servlet.NSAL1330.NSAL1330_JBMsg;
import business.servlet.NSAL1330.NSAL1330_QBMsg;
import business.servlet.NSAL1330.NSAL1330_RBMsg;
import business.servlet.NSAL1330.NSAL1330_UBMsg;
import business.servlet.NSAL1330.NSAL1330_VBMsg;
import business.servlet.NSAL1330.NSAL1330_XBMsg;
import business.servlet.NSAL1330.NSAL1330_ZBMsg;
import business.servlet.NSAL1330.constant.NSAL1330Constant.XX_PAGE;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/13   Hitachi         K.Kitachi       Update          QC#19004
 * 2017/06/13   Hitachi         Y.Takeno        Update          QC#18941
 * 2017/06/13   Hitachi         Y.Takeno        Update          QC#18934
 * 2017/06/19   Hitachi         Y.Takeno        Update          QC#18829
 * 2017/06/21   Hitachi         A.Kohinata      Update          QC#19330
 * 2017/07/24   Hitachi         Y.Takeno        Update          QC#20055
 * 2017/08/04   Hitachi         Y.Takeno        Update          QC#20443
 * 2017/10/05   Hitachi         Y.Takeno        Update          QC#20059
 * 2017/10/13   Hitachi         Y.Takeno        Update          QC#20059-1
 * 2017/10/16   Hitachi         Y.Takeno        Update          QC#20001
 * 2017/10/17   Hitachi         Y.Takeno        Update          QC#21851
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21656
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/05/09   Fujitsu         M.Ohno          Update          QC#25030
 * 2018/07/02   Hitachi         T.Tomita        Update          QC#26738
 * 2018/07/08   Fujitsu         K.Ishizuka      Update          QC#26528
 * 2018/10/10   Hitachi         K.Kojima        Update          QC#28715
 * 2018/11/15   Hitachi         K.Kim           Update          QC#28638
 * 2018/11/16   Fujitsu         R.Nakamura      Update          QC#29260
 * 2019/05/29   Hitachi         K.Kitachi       Update          QC#50567
 *</pre>
 */
public class NSAL1330CommonLogic {

    /**
     * @param bizMsg NSAL1330CMsg
     * @return Map<BigDecimal, Integer>
     */
    public static Map<BigDecimal, Integer> countQtyI(NSAL1330BMsg bizMsg) {
        Map<BigDecimal, Integer> qtyMap = new HashMap<BigDecimal, Integer>();

        for (int i = 0; i < bizMsg.I.getValidCount(); i++) {
            BigDecimal modelId = bizMsg.I.no(i).mdlId_I.getValue();
            if (qtyMap.containsKey(modelId)) {
                int qty = qtyMap.get(modelId).intValue();
                qty++;
                qtyMap.put(modelId, qty);
            } else {
                qtyMap.put(modelId, 1);
            }
        }
        return qtyMap;
    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NSAL1330BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NSAL1330BMsg scrnMsg) {
        // START 2017/10/20 [QC#21656, MOD]
        // int isActive = 0; // inactive
        // for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        //     if (!isImport(scrnMsg) //
        //             && (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).scrEntAvalFlg_A.getValue()) //
        //             || !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).scrEntAvalFlg_A))) {
        //         isActive = 1; // active
        //         break;
        //     }
        // }
        // 
        // // add start 2017/06/21 CSA Defect#19330
        // if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
        //     for (int i = 0; i < scrnMsg.J.length(); i++) {
        //         if (!ZYPConstant.FLG_OFF_N.equals(scrnMsg.J.no(i).scrEntAvalFlg_J.getValue())) {
        //             isActive = 1; // active
        //             break;
        //         }
        //     }
        // }
        // add end 2017/06/21 CSA Defect#19330
        int isActive = getCmnBtnActiveSts(scrnMsg);
        // END 2017/10/20 [QC#21656, MOD]

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
        // Add Start 2018/07/02 QC#26738
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.ordBookFlg.getValue())) {
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        }
        // Add End 2018/07/02 QC#26738
    }

    /**
     * @param scrnMsg
     * @return
     */
    public static boolean isImport(NSAL1330BMsg scrnMsg) {
        return XX_PAGE.PAGE_IMPT.getCode().equals(scrnMsg.xxPageCd.getValue());
    }

    /**
     * init AppFracDigit
     * @param scrnMsg NSAL1330BMsg
     */
    public static void initAppFracDigit(NSAL1330BMsg scrnMsg) {

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
            // 2018/05/09 QC#25030 add start
            scrnMsg.Z.no(i).bllgMinAmtRate_Z.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
            scrnMsg.Z.no(i).bllgRollOverRatio_Z.setAppFracDigit(0);
            // 2018/05/09 QC#25030 add end
        }
        // Service Pricing Detail Config
        for (int i = 0; i < scrnMsg.U.length(); i++) {
            scrnMsg.U.no(i).contrMtrMultRate_U.setAppFracDigit(AFTER_DECIMAL_DIGITS_MULTI_RATE);
            scrnMsg.U.no(i).xsMtrAmtRate_U.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT_RATE);
            // 2018/05/09 QC#25030 add start
            scrnMsg.U.no(i).bllgMinAmtRate_U.setAppFracDigit(AFTER_DECIMAL_DIGITS_AMT);
            scrnMsg.U.no(i).bllgRollOverRatio_U.setAppFracDigit(0);
            // 2018/05/09 QC#25030 add start
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
     * @param scrnMsg NSAL1330BMsg
     */
    public static void initInputProtected(NSAL1330BMsg scrnMsg) {

        // Input Protect
        scrnMsg.xxScrItem50Txt.setInputProtected(true);
        scrnMsg.shellLineNum.setInputProtected(true);
        scrnMsg.svcPgmMdseCd.setInputProtected(true);
        scrnMsg.mdseDescShortTxt.setInputProtected(true);
        scrnMsg.prcSvcContrTpCd.setInputProtected(true);
        scrnMsg.prcSvcPlnTpCd.setInputProtected(true);
        scrnMsg.fromPerMthNum.setInputProtected(true);
        scrnMsg.toPerMthNum.setInputProtected(true);
        scrnMsg.termMthAot.setInputProtected(true);
        scrnMsg.dsContrCatgCd.setInputProtected(true);
        scrnMsg.dsContrNum_AD.setInputProtected(true);
        scrnMsg.baseBllgCycleCd.setInputProtected(true);
        scrnMsg.usgBllgCycleCd.setInputProtected(true);
        // START 2018/11/15 [QC#28638, ADD]
        scrnMsg.fixTermInMthAot.setInputProtected(true);
        // END 2018/11/15 [QC#28638, ADD]
        scrnMsg.billWithEquipFlg.setInputProtected(true);
        scrnMsg.billByTpCd.setInputProtected(true);
        scrnMsg.xxTotAmt.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnMsg.dsAcctNum.setInputProtected(true);
        scrnMsg.xxGenlFldAreaTxt_BI.setInputProtected(true);
        scrnMsg.soldToCustLocCd.setInputProtected(true);
        scrnMsg.shipToCustLocCd.setInputProtected(true);
        scrnMsg.cpoSvcAgmtVerNum.setInputProtected(true);
        scrnMsg.manContrOvrdFlg.setInputProtected(false);
        scrnMsg.svcMemoRsnDescTxt.setInputProtected(false);
        scrnMsg.svcCmntTxt.setInputProtected(false);
        scrnMsg.basePrcDealAmt.setInputProtected(false);

        // Service Pricing Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1330_ABMsg pricingMsg = scrnMsg.A.no(i);

            // START 2017/10/06 [QC#20059, ADD]
            pricingMsg.xxSelFlg_A.setInputProtected(false);
            if(isFleet(scrnMsg) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue())) {
                pricingMsg.xxSelFlg_A.setInputProtected(true);
            }
            if (isConfigLevelPriceSetting(scrnMsg, pricingMsg.mdlId_A.getValue()) ) {
                pricingMsg.t_MdlNm_A.setInputProtected(true);
                pricingMsg.xxTotQtyCnt_A.setInputProtected(true);
                pricingMsg.prcCatgNm_A.setInputProtected(false);
                pricingMsg.prcMtrPkgPk_A.setInputProtected(true);
                pricingMsg.prcTierSvcOfferCd_A.setInputProtected(true);
                pricingMsg.termMthAot_A.setInputProtected(true);
                pricingMsg.xxTotPrcAmt_EB.setInputProtected(true);
                pricingMsg.xxTotPrcAmt_TB.setInputProtected(true);
                continue;
            }
            // END   2017/10/06 [QC#20059, ADD]

            pricingMsg.t_MdlNm_A.setInputProtected(true);
            pricingMsg.xxTotQtyCnt_A.setInputProtected(true);
            pricingMsg.prcCatgNm_A.setInputProtected(false);
            pricingMsg.prcMtrPkgPk_A.setInputProtected(false); // QC#4631
            pricingMsg.prcTierSvcOfferCd_A.setInputProtected(isRateTypeAnuual(pricingMsg.prcRateTpCd_A.getValue())); // QC#4631
            pricingMsg.termMthAot_A.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_EB.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_TB.setInputProtected(true);
        }
        scrnMsg.prcCatgNm_HB.setInputProtected(false);
        scrnMsg.prcCatgNm_C.setInputProtected(false);

        // Service Pricing Detail
        for (int i = 0; i < scrnMsg.R.length(); i++) {
            NSAL1330_RBMsg pricingMsg = scrnMsg.R.no(i);

            // START 2017/10/06 [QC#20059, ADD]
            if (!isConfigLevelPriceSetting(scrnMsg, pricingMsg.mdlId_R.getValue()) ) {
                pricingMsg.t_MdlNm_R.setInputProtected(true);
                pricingMsg.xxTotQtyCnt_R.setInputProtected(true);
                pricingMsg.prcCatgNm_R.setInputProtected(true);
                pricingMsg.prcMtrPkgPk_R.setInputProtected(true);
                pricingMsg.prcTierSvcOfferCd_R.setInputProtected(true);
                pricingMsg.termMthAot_R.setInputProtected(true);
                pricingMsg.xxTotPrcAmt_ER.setInputProtected(true);
                pricingMsg.xxTotPrcAmt_TR.setInputProtected(true);
                pricingMsg.xxGenlFldAreaTxt_R.setInputProtected(true);
                continue;
            }
            // END   2017/10/06 [QC#20059, ADD]

            pricingMsg.t_MdlNm_R.setInputProtected(true);
            pricingMsg.xxTotQtyCnt_R.setInputProtected(true);
            pricingMsg.prcCatgNm_R.setInputProtected(false);
            pricingMsg.prcMtrPkgPk_R.setInputProtected(false);
            pricingMsg.prcTierSvcOfferCd_R.setInputProtected(isRateTypeAnuual(pricingMsg.prcRateTpCd_R.getValue())); // QC#4631
            pricingMsg.termMthAot_R.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_ER.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_TR.setInputProtected(true);
            pricingMsg.xxGenlFldAreaTxt_R.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.U.length(); i++) {
            NSAL1330_UBMsg usgMsg = scrnMsg.U.no(i);
            usgMsg.xxGenlFldAreaTxt_U.setInputProtected(true);
        }

        // Accessory Charge Detail
        for (int i = 0; i < scrnMsg.J.length(); i++) {

            NSAL1330_JBMsg acsryMsg = scrnMsg.J.no(i);
            acsryMsg.xxChkBox_J.setInputProtected(false);
            acsryMsg.xxLineNum_J.setInputProtected(true);
            acsryMsg.mdseCd_J.setInputProtected(false);
            acsryMsg.mdseDescShortTxt_J.setInputProtected(true);
            acsryMsg.addlBasePrcDealAmt_J.setInputProtected(false);
            acsryMsg.prcCatgNm_J.setInputProtected(false);
            acsryMsg.prcListEquipConfigNum_J.setInputProtected(false);
            // START 2017/06/14 [QC#18934, ADD]
            if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
                acsryMsg.addlBasePrcDealAmt_J.setInputProtected(true);
                acsryMsg.prcCatgNm_J.setInputProtected(true);
            }
            // END   2017/06/14 [QC#18934, ADD]
        }
        // Rental Equipment Charge Base & Accessory Detail
        for (int i = 0; i < scrnMsg.B.length(); i++) {

            NSAL1330_BBMsg acsryMsg = scrnMsg.B.no(i);
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
            NSAL1330_CBMsg addtionlMsg = scrnMsg.C.no(i);

            addtionlMsg.xxChkBox_C.setInputProtected(false);
            addtionlMsg.xxLineNum_C.setInputProtected(true);
            addtionlMsg.mdseCd_CU.setInputProtected(false);
            addtionlMsg.mdseDescShortTxt_CU.setInputProtected(true);
            addtionlMsg.mdseCd_CI.setInputProtected(false);
            addtionlMsg.mdseDescShortTxt_CI.setInputProtected(true);
            addtionlMsg.addlChrgPrcDealAmt_C.setInputProtected(false);
            // 2018/05/09 QC#25030 add start
            addtionlMsg.printDtlFlg_C.setInputProtected(false);
            // 2018/05/09 QC#25030 add start
        }

    }

    private static boolean isRateTypeAnuual(String prcRateTpCd) {
        return PRC_RATE_TP.ANNUAL.equals(prcRateTpCd);
    }

    /**
     * initial InputProtected
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setInputProtectedOnChangeForUsage(NSAL1330BMsg scrnMsg) {
        //        BigDecimal multiplier = BigDecimal.ZERO;
        String paretntHardNm = "";

        if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
                NSAL1330_ZBMsg pricingMsg = scrnMsg.Z.no(i);

                int ixA = getPrcDtlIndex(scrnMsg, i);
                NSAL1330_ABMsg prcHeaderMsg = scrnMsg.A.no(ixA);
                boolean isProtect = false;
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue())) {
                    isProtect = true;
                }

                //                multiplier = pricingMsg.contrMtrMultRate_Z.getValue();
                paretntHardNm = pricingMsg.mtrLbDescTxt_Z.getValue();

                // Parent Data
                if (XX_FLG_PARENT.equals(pricingMsg.xxFlgNm_Z.getValue())) {

                    pricingMsg.prcListBandDescTxt_Z.setInputProtected(isProtect);
                    pricingMsg.prcBookMdseCd_Z.setInputProtected(isProtect);
                    pricingMsg.mtrLbDescTxt_ZB.setInputProtected(true);
                    pricingMsg.mdseDescShortTxt_Z.setInputProtected(isProtect);
                    pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);
                    pricingMsg.contrMtrMultRate_Z.setInputProtected(true);
                    // 2018/05/09 QC#25030 add start
                    pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinAmtRate_Z.setInputProtected(isProtect);
                    pricingMsg.bllgRollOverRatio_Z.setInputProtected(isProtect);
                    // 2018/05/09 QC#25030 add end
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
                    // 2018/05/09 QC#25030 add start
                    pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinAmtRate_Z.setInputProtected(isProtect);
                    pricingMsg.bllgRollOverRatio_Z.setInputProtected(isProtect);
                    // 2018/05/09 QC#25030 add end

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
                    // 2018/05/09 QC#25030 add start
                    pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinAmtRate_Z.setInputProtected(isProtect);
                    pricingMsg.bllgRollOverRatio_Z.setInputProtected(isProtect);
                    // 2018/05/09 QC#25030 add end
                }
            }

        } else {
            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
                NSAL1330_ZBMsg pricingMsg = scrnMsg.Z.no(i);

                int ixA = getPrcDtlIndex(scrnMsg, i);
                NSAL1330_ABMsg prcHeaderMsg = scrnMsg.A.no(ixA);
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
                    pricingMsg.mdseDescShortTxt_Z.setInputProtected(true);
                    pricingMsg.mtrLbDescTxt_Z.setInputProtected(true);
                    pricingMsg.contrMtrMultRate_Z.setInputProtected(true);
                    // 2018/05/09 QC#25030 add start
                    pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinAmtRate_Z.setInputProtected(isProtect);
                    pricingMsg.bllgRollOverRatio_Z.setInputProtected(isProtect);
                    // 2018/05/09 QC#25030 add end
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
                    // 2018/05/09 QC#25030 add start
                    pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinAmtRate_Z.setInputProtected(isProtect);
                    pricingMsg.bllgRollOverRatio_Z.setInputProtected(isProtect);
                    // 2018/05/09 QC#25030 add end

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
                    // 2018/05/09 QC#25030 add start
                    pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinCnt_Z.setInputProtected(isProtect);
                    pricingMsg.bllgMinAmtRate_Z.setInputProtected(isProtect);
                    pricingMsg.bllgRollOverRatio_Z.setInputProtected(isProtect);
                    // 2018/05/09 QC#25030 add end
                }
            }

        }
    }

    /**
     * initial InputProtected
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setInputProtectedOnChangeForUsageConfig(NSAL1330BMsg scrnMsg) {
        String paretntHardNm = "";

        // Service Pricing Detail
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NSAL1330_UBMsg pricingMsg = scrnMsg.U.no(i);

            int ixR = getPrcDtlIndexConfig(scrnMsg, i);
            NSAL1330_RBMsg prcHeaderMsg = scrnMsg.R.no(ixR);
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
                // 2018/05/09 QC#25030 add start
                pricingMsg.bllgFreeCopyCnt_U.setInputProtected(isProtect);
                pricingMsg.bllgMinCnt_U.setInputProtected(isProtect);
                pricingMsg.bllgMinAmtRate_U.setInputProtected(isProtect);
                pricingMsg.bllgRollOverRatio_U.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add end
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
                // 2018/05/09 QC#25030 add start
                pricingMsg.bllgFreeCopyCnt_U.setInputProtected(isProtect);
                pricingMsg.bllgMinCnt_U.setInputProtected(isProtect);
                pricingMsg.bllgMinAmtRate_U.setInputProtected(isProtect);
                pricingMsg.bllgRollOverRatio_U.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add end

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
                // 2018/05/09 QC#25030 add start
                pricingMsg.bllgFreeCopyCnt_U.setInputProtected(isProtect);
                pricingMsg.bllgMinCnt_U.setInputProtected(isProtect);
                pricingMsg.bllgMinAmtRate_U.setInputProtected(isProtect);
                pricingMsg.bllgRollOverRatio_U.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add end
            }
        }
    }

    /**
     * override Protected
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1330BMsg
     */
    public static void overrideProtected(EZDCommonHandler handler, NSAL1330BMsg scrnMsg) {
        handler.setButtonEnabled(BUTTON_APPLY_ALL_HEADER //
                , !NSAL1330CommonLogic.isConfigPricingAllCollapsed(scrnMsg) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue()));

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.manContrOvrdFlg.getValue())) {
            scrnMsg.manContrOvrdFlg.setInputProtected(isImport(scrnMsg));

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NSAL1330_ABMsg prcHeaderMsg = scrnMsg.A.no(i);
                boolean isProtect = false;
                // START 2017/10/06 [QC#20059, MOD]
                // if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue())) {
                //     isProtect = true;
                // }
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue()) || isConfigLevelPriceSetting(scrnMsg, prcHeaderMsg.mdlId_A.getValue())) {
                    isProtect = true;
                }
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue())) {
                    prcHeaderMsg.xxSelFlg_A.setInputProtected(true);
                }
                // END   2017/10/06 [QC#20059, MOD]
                // START 2017/10/16 [QC#20001, ADD]
                handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT_MODEL, i, true);
                // END   2017/10/16 [QC#20001, ADD]

                prcHeaderMsg.prcCatgNm_A.setInputProtected(isProtect);
                // START 2017/06/19 [QC#18829, MOD]
                boolean baseOnlyFlag = false;
                if (ZYPCommonFunc.hasValue(prcHeaderMsg.prcMtrPkgPk_A) 
                        && !ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                    baseOnlyFlag = true;
                }
                prcHeaderMsg.prcMtrPkgPk_A.setInputProtected(isProtect || baseOnlyFlag);
                //prcHeaderMsg.prcMtrPkgPk_A.setInputProtected(isProtect); // QC#4631
                // END   2017/06/19 [QC#18829, MOD]

                if (!prcHeaderMsg.prcTierSvcOfferCd_AB.isInputProtected()) {
                    prcHeaderMsg.prcTierSvcOfferCd_A.setInputProtected(//
                            isProtect || isRateTypeAnuual(prcHeaderMsg.prcRateTpCd_A.getValue())); // QC#4631
                }
                prcHeaderMsg.xxTotPrcAmt_PB.setInputProtected(isProtect);
                prcHeaderMsg.termMthAot_A.setInputProtected(true);
                setTierLinkCtrl(handler, scrnMsg, i);
                setSupplyButton(handler, prcHeaderMsg, i, scrnMsg);
                handler.setButtonEnabled(BUTTON_APPLY_ALL_MODEL //
                        , i, ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxSmryLineFlg_A.getValue()) && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue()));
            }
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                NSAL1330_RBMsg prcHeaderMsg = scrnMsg.R.no(i);
                boolean isProtect = false;
                // START 2017/10/06 [QC#20059, MOD]
                // if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_R.getValue())) {
                //     isProtect = true;
                // }
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_R.getValue()) || !isConfigLevelPriceSetting(scrnMsg, prcHeaderMsg.mdlId_R.getValue())) {
                    isProtect = true;
                }
                // END   2017/10/06 [QC#20059, MOD]
                handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, i, true);

                prcHeaderMsg.dsOrdPosnNum_R.setInputProtected(true);
                prcHeaderMsg.prcCatgNm_R.setInputProtected(isProtect);
                prcHeaderMsg.prcCatgCd_R.setInputProtected(isProtect);
                // START 2017/06/19 [QC#18829, MOD]
                boolean baseOnlyFlag = false;
                if (ZYPCommonFunc.hasValue(prcHeaderMsg.prcMtrPkgPk_R) 
                        && !ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                    baseOnlyFlag = true;
                }
                prcHeaderMsg.prcMtrPkgPk_R.setInputProtected(isProtect || baseOnlyFlag);
                // prcHeaderMsg.prcMtrPkgPk_R.setInputProtected(isProtect);
                // END   2017/06/19 [QC#18829, MOD]
                prcHeaderMsg.xxTotPrcAmt_PR.setInputProtected(isProtect);
                prcHeaderMsg.termMthAot_R.setInputProtected(true);
                prcHeaderMsg.billToCustCd_R.setInputProtected(isProtect);
                prcHeaderMsg.dsAcctNm_R.setInputProtected(isProtect); // 2017/03/29 S21_NA#18171 Mod
                prcHeaderMsg.billToLocNum_R.setInputProtected(isProtect);
                prcHeaderMsg.xxGenlFldAreaTxt_R.setInputProtected(true);
                prcHeaderMsg.prcTierSvcOfferCd_R.setInputProtected(isProtect);

                setTierLinkCtrlConfig(handler, scrnMsg, i);
                setSupplyButton(handler, prcHeaderMsg, i, scrnMsg);
            }

            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
                NSAL1330_ZBMsg pricingMsg = scrnMsg.Z.no(i);
                int ixA = getPrcDtlIndex(scrnMsg, i);
                NSAL1330_ABMsg prcHeaderMsg = scrnMsg.A.no(ixA);
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
                // 2018/05/09 QC#25030 add start
                pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(isProtect);
                pricingMsg.bllgMinCnt_Z.setInputProtected(isProtect);
                pricingMsg.bllgMinAmtRate_Z.setInputProtected(isProtect);
                pricingMsg.bllgRollOverRatio_Z.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add end
            }
            for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
                NSAL1330_UBMsg usgMsg = scrnMsg.U.no(i);
                int ixR = getPrcDtlIndexConfig(scrnMsg, i);
                NSAL1330_RBMsg prcHeaderMsg = scrnMsg.R.no(ixR);
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
                usgMsg.dsAcctNm_U.setInputProtected(isProtect); // 2017/03/29 S21_NA#18171 Mod
                usgMsg.billToLocNum_U.setInputProtected(isProtect);
                usgMsg.xxGenlFldAreaTxt_U.setInputProtected(true);
                // 2018/05/09 QC#25030 add start
                usgMsg.bllgFreeCopyCnt_U.setInputProtected(isProtect);
                usgMsg.bllgMinCnt_U.setInputProtected(isProtect);
                usgMsg.bllgMinAmtRate_U.setInputProtected(isProtect);
                usgMsg.bllgRollOverRatio_U.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add end
            }
            // Accessory Charge Detail
            // START 2017/06/14 [QC#18934, MOD]
            // scrnMsg.prcCatgNm_HJ.setInputProtected(isImport(scrnMsg));
            scrnMsg.prcCatgNm_HJ.setInputProtected(DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()));
            // END   2017/06/14 [QC#18934, ADD]

            for (int i = 0; i < scrnMsg.J.length(); i++) {
                NSAL1330_JBMsg acsryMsg = scrnMsg.J.no(i);
                boolean isProtect = ZYPConstant.FLG_OFF_N.equals(acsryMsg.scrEntAvalFlg_J.getValue());
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM, i, !isProtect);
                // START 2017/06/14 [QC#18934, MOD]
                // handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_ACP, i, !isProtect);
                handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_ACP, i, !(isProtect
                        || DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())));
                // END   2017/06/14 [QC#18934, MOD]
                acsryMsg.mdseCd_J.setInputProtected(isProtect);
                acsryMsg.addlBasePrcDealAmt_J.setInputProtected(isProtect);
                acsryMsg.prcCatgNm_J.setInputProtected(isProtect);
                acsryMsg.xxChkBox_J.setInputProtected(isProtect);
                // START 2017/06/14 [QC#18934, ADD]
                if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
                    acsryMsg.addlBasePrcDealAmt_J.setInputProtected(true);
                    acsryMsg.prcCatgNm_J.setInputProtected(true);
                }
                // END   2017/06/14 [QC#18934, ADD]
                // START 2017/08/04 [QC#20443, ADD]
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
                    handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM, i, false);
                    handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM, i, false);
                    acsryMsg.xxChkBox_J.setInputProtected(true);
                    acsryMsg.mdseCd_J.setInputProtected(true);
                }
                // END   2017/08/04 [QC#20443, ADD]
            }
            // Rental Equipment Charge Base & Accessory
            scrnMsg.prcCatgNm_HB.setInputProtected(isImport(scrnMsg));
            for (int i = 0; i < scrnMsg.B.length(); i++) {
                NSAL1330_BBMsg acsryMsg = scrnMsg.B.no(i);
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
                NSAL1330_CBMsg addtionlMsg = scrnMsg.C.no(i);
                boolean isProtect = ZYPConstant.FLG_OFF_N.equals(addtionlMsg.scrEntAvalFlg_C.getValue());
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_UNIT, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_UNIT, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_ADD, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_ADD, i, !isProtect);
                addtionlMsg.mdseCd_CU.setInputProtected(isProtect);
                addtionlMsg.mdseCd_CI.setInputProtected(isProtect);
                addtionlMsg.addlChrgPrcDealAmt_C.setInputProtected(isProtect);
                addtionlMsg.xxChkBox_C.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add start
                addtionlMsg.printDtlFlg_C.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add end
            }
            setInputProtectedOnChangeForUsage(scrnMsg);
            setInputProtectedOnChangeForUsageConfig(scrnMsg);

            // START 2017/08/04 [QC#20443, MOD]
            boolean isEnable = !isImport(scrnMsg);
            boolean isAddAsry = ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue());
            handler.setButtonEnabled(BUTTON_APPLY_BUNDLE_PRICE, isEnable);
            // handler.setButtonEnabled(BUTTON_ADD_ACCESSORY, isEnable);
            // handler.setButtonEnabled(BUTTON_DEL_ACCESSORY, isEnable);
            handler.setButtonEnabled(BUTTON_ADD_ACCESSORY, isEnable && !isAddAsry);
            handler.setButtonEnabled(BUTTON_DEL_ACCESSORY, isEnable && !isAddAsry);
            // END   2017/08/04 [QC#20443, MOD]
            handler.setButtonEnabled(BUTTON_ADD_ACCESSORY_RE, false);
            handler.setButtonEnabled(BUTTON_DEL_ACCESSORY_RE, false);
            handler.setButtonEnabled(BUTTON_ADD_SERVICE, isEnable);
            handler.setButtonEnabled(BUTTON_DEL_SERVICE, isEnable);

            // START 2017/10/24 [QC#21556, ADD]
            overrideProtectedForAddContr(handler, scrnMsg);
            // END   2017/10/24 [QC#21556, ADD]
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
                NSAL1330_ABMsg prcHeaderMsg = scrnMsg.A.no(i);
                setTierLinkCtrl(handler, scrnMsg, i);

                prcHeaderMsg.prcCatgNm_A.setInputProtected(true);
                prcHeaderMsg.prcMtrPkgPk_A.setInputProtected(true);
                prcHeaderMsg.prcTierSvcOfferCd_A.setInputProtected(true);
                prcHeaderMsg.xxTotPrcAmt_PB.setInputProtected(true);
                prcHeaderMsg.termMthAot_A.setInputProtected(true);

                handler.setButtonEnabled(BUTTON_APPLY_ALL_MODEL, i, false);
            }
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                NSAL1330_RBMsg prcHeaderMsg = scrnMsg.R.no(i);
                setTierLinkCtrlConfig(handler, scrnMsg, i);

                handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, i, false);

                prcHeaderMsg.prcCatgNm_R.setInputProtected(true);
                prcHeaderMsg.prcMtrPkgPk_R.setInputProtected(true);
                prcHeaderMsg.prcTierSvcOfferCd_R.setInputProtected(true);
                prcHeaderMsg.xxTotPrcAmt_PR.setInputProtected(true);
                prcHeaderMsg.termMthAot_R.setInputProtected(true);
                prcHeaderMsg.billToCustCd_R.setInputProtected(true);
                prcHeaderMsg.dsAcctNm_R.setInputProtected(true); // 2017/03/29 S21_NA#18171 Mod
                prcHeaderMsg.billToLocNum_R.setInputProtected(true);
                prcHeaderMsg.xxGenlFldAreaTxt_R.setInputProtected(true);
            }
            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.length(); i++) {
                NSAL1330_ZBMsg pricingMsg = scrnMsg.Z.no(i);
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
                // 2018/05/09 QC#25030 add start
                pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(true);
                pricingMsg.bllgMinCnt_Z.setInputProtected(true);
                pricingMsg.bllgMinAmtRate_Z.setInputProtected(true);
                pricingMsg.bllgRollOverRatio_Z.setInputProtected(true);
                // 2018/05/09 QC#25030 add end

            }
            for (int i = 0; i < scrnMsg.U.length(); i++) {
                NSAL1330_UBMsg usgMsg = scrnMsg.U.no(i);
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
                usgMsg.dsAcctNm_U.setInputProtected(true); // 2017/03/29 S21_NA#18171 Mod
                usgMsg.billToLocNum_U.setInputProtected(true);
                usgMsg.xxGenlFldAreaTxt_U.setInputProtected(true);
                // 2018/05/09 QC#25030 add start
                usgMsg.bllgFreeCopyCnt_U.setInputProtected(true);
                usgMsg.bllgMinCnt_U.setInputProtected(true);
                usgMsg.bllgMinAmtRate_U.setInputProtected(true);
                usgMsg.bllgRollOverRatio_U.setInputProtected(true);
                // 2018/05/09 QC#25030 add end
            }
            // Accessory Charge Detail
            scrnMsg.prcCatgNm_HJ.setInputProtected(true);
            for (int i = 0; i < scrnMsg.J.length(); i++) {
                NSAL1330_JBMsg acsryMsg = scrnMsg.J.no(i);
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
                NSAL1330_BBMsg acsryMsg = scrnMsg.B.no(i);
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
                NSAL1330_CBMsg addtionlMsg = scrnMsg.C.no(i);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_UNIT, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_UNIT, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_ADD, i, false);
                handler.setButtonEnabled(BUTTON_ITEM_DESC_ADD, i, false);
                addtionlMsg.mdseCd_CU.setInputProtected(true);
                addtionlMsg.mdseCd_CI.setInputProtected(true);
                addtionlMsg.addlChrgPrcDealAmt_C.setInputProtected(true);
                // 2018/05/09 QC#25030 add start
                addtionlMsg.printDtlFlg_C.setInputProtected(true);
                // 2018/05/09 QC#25030 add end
            }
        }
    }

    /**
     * @param scrnMsg
     */
    private static void closeCheckAddlChrg(NSAL1330BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NSAL1330_CBMsg cScrnMsg = scrnMsg.C.no(i);
            if (!ZYPCommonFunc.hasValue(cScrnMsg.mdseCd_CU)) {
                cScrnMsg.mdseCd_CU.setErrorInfo(1, ZZZM9025E, new String[] {"Covered Unit" });
            }
            if (!ZYPCommonFunc.hasValue(cScrnMsg.mdseCd_CI)) {
                cScrnMsg.mdseCd_CI.setErrorInfo(1, ZZZM9025E, new String[] {"Additional Charge Item" });
            }
            if (!ZYPCommonFunc.hasValue(cScrnMsg.addlChrgPrcDealAmt_C)) {
                cScrnMsg.addlChrgPrcDealAmt_C.setErrorInfo(1, ZZZM9025E, new String[] {"Periodic Service Price" });
            } else if (BigDecimal.ZERO.compareTo(cScrnMsg.addlChrgPrcDealAmt_C.getValue()) > 0) {
                cScrnMsg.addlChrgPrcDealAmt_C.setErrorInfo(1, NSAM0675E, new String[] {"Periodic Service Price" });
                scrnMsg.addCheckItem(cScrnMsg.addlChrgPrcDealAmt_C);
            }
        }
        if (scrnMsg.C.getValidCount() > 0) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.prcCatgNm_C)) {
                scrnMsg.prcCatgNm_C.setErrorInfo(1, ZZZM9025E, new String[] {"Service Price List" });
            }
        }
    }

    /**
     * @param scrnMsg
     */
    private static void closeCheckAddlBase(NSAL1330BMsg scrnMsg) {
        // Accessory Charge
        for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
            NSAL1330_JBMsg jScrnMsg = scrnMsg.J.no(i);
            if (!ZYPCommonFunc.hasValue(jScrnMsg.mdseCd_J)) {
                jScrnMsg.mdseCd_J.setErrorInfo(1, ZZZM9025E, new String[] {"Covered Item" });
            }
            // START 2017/06/14 [QC#18934, ADD]
            if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
                continue;
            }
            // END   2017/06/14 [QC#18934, ADD]
            if (!ZYPCommonFunc.hasValue(jScrnMsg.addlBasePrcDealAmt_J)) {
                jScrnMsg.addlBasePrcDealAmt_J.setErrorInfo(1, ZZZM9025E, new String[] {"Periodic Service Price" });
            } else if (BigDecimal.ZERO.compareTo(jScrnMsg.addlBasePrcDealAmt_J.getValue()) > 0) {
                jScrnMsg.addlBasePrcDealAmt_J.setErrorInfo(1, NSAM0675E, new String[] {"Periodic Service Price" });
                scrnMsg.addCheckItem(jScrnMsg.addlBasePrcDealAmt_J);
            }
            if (!ZYPCommonFunc.hasValue(jScrnMsg.prcCatgNm_J)) {
                jScrnMsg.prcCatgNm_J.setErrorInfo(1, ZZZM9025E, new String[] {"Accessory Charge Price List" });
            }
        }

        // Rental Equipment Charge Base & Accessory
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NSAL1330_BBMsg bScrnMsg = scrnMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(bScrnMsg.mdseCd_B)) {
                bScrnMsg.mdseCd_B.setErrorInfo(1, ZZZM9025E, new String[] {"Covered Item" });
            }
            if (!ZYPCommonFunc.hasValue(bScrnMsg.addlBasePrcDealAmt_B)) {
                bScrnMsg.addlBasePrcDealAmt_B.setErrorInfo(1, ZZZM9025E, new String[] {"Periodic Amount" });
            } else if (BigDecimal.ZERO.compareTo(bScrnMsg.addlBasePrcDealAmt_B.getValue()) > 0) {
                bScrnMsg.addlBasePrcDealAmt_B.setErrorInfo(1, NSAM0675E, new String[] {"Periodic Amount" });
                scrnMsg.addCheckItem(bScrnMsg.addlBasePrcDealAmt_B);
            }
            if (!ZYPCommonFunc.hasValue(bScrnMsg.prcCatgNm_B)) {
                bScrnMsg.prcCatgNm_B.setErrorInfo(1, ZZZM9025E, new String[] {"Rental Equipment Price List" });
            }
        }
    }

    /**
     * AGGREGATE CHECK
     * @param scrnMsg NSAL1330BMsg
     */
    public static void aggregateCheck(NSAL1330BMsg scrnMsg) {
        BigDecimal prcPk = null;

        int tierCnt = 0;
        int chkCnt = 0;
        boolean nullFlg = false;
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(i);
            if (isNoEntry(rScrnMsg, scrnMsg.usgBllgCycleCd.getValue())) {
                continue;
            }
            if (isTier(rScrnMsg)) {
                tierCnt++;
            }
            if (chkCnt == 0) {
                if (!ZYPCommonFunc.hasValue(rScrnMsg.prcMtrPkgPk_R)) {
                    nullFlg = true;
                }
                prcPk = rScrnMsg.prcMtrPkgPk_R.getValue();
                continue;
            }
            // CHECK1
            if (nullFlg) {
                if (ZYPCommonFunc.hasValue(rScrnMsg.prcMtrPkgPk_R)) {
                    scrnMsg.setMessageInfo(NSAM0656E);
                }
            } else {
                BigDecimal nowPrcPk = rScrnMsg.prcMtrPkgPk_R.getValue();
                if (ZYPCommonFunc.hasValue(nowPrcPk)) {
                    if (prcPk == null || prcPk.compareTo(nowPrcPk) != 0) {
                        scrnMsg.setMessageInfo(NSAM0656E);
                    }
                } else {
                    scrnMsg.setMessageInfo(NSAM0656E);
                }
            }
            chkCnt++;
        }

        if (tierCnt == 0) {
            aggregateCheckForNonTier(scrnMsg);
            return;
        }
        if (tierCnt != scrnMsg.R.getValidCount()) {
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(i);
                if (isNoEntry(rScrnMsg, scrnMsg.usgBllgCycleCd.getValue())) {
                    continue;
                }
                rScrnMsg.prcCatgNm_R.setErrorInfo(1, NSAM0667E);
                if (!PRC_LIST_TP.SERVICE_TIERS.equals(rScrnMsg.prcListTpCd_R.getValue())) {
                    rScrnMsg.prcTierSvcOfferCd_R.setErrorInfo(1, NSAM0667E);
                }
            }
            return;
        }

        aggregateCheckForTier(scrnMsg);
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     */
    private static void aggregateCheckForTier(NSAL1330BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.V.getValidCount(); i++) {
//            NSAL1330_VBMsg vScrnMsg = scrnMsg.V.no(i);
//            BigDecimal mdlId = vScrnMsg.mdlId_V.getValue();
//            BigDecimal cpoSvcConfigRefPk = vScrnMsg.cpoSvcConfigRefPk_V.getValue();
//            String bllgMtrLbCd = vScrnMsg.bllgMtrLbCd_V.getValue();
//            String prcSvcTierTpCd = vScrnMsg.prcSvcTierTpCd_V.getValue();
//            String prcSvcTierTpNm = vScrnMsg.prcSvcTierTpDescTxt_V.getValue();
//            BigDecimal xsMtrAmtRate = vScrnMsg.xsMtrAmtRate_V.getValue();
//
//            for (int ix = 0; ix < scrnMsg.V.getValidCount(); ix++) {
//                NSAL1330_VBMsg viScrnMsg = scrnMsg.V.no(ix);
//
//                if (i == ix) {
//                    continue;
//                }
//                if (mdlId.compareTo(viScrnMsg.mdlId_V.getValue()) == 0) {
//                    continue;
//                }
//                if (cpoSvcConfigRefPk.compareTo(viScrnMsg.cpoSvcConfigRefPk_V.getValue()) == 0) {
//                    continue;
//                }
//                if (!bllgMtrLbCd.equals(viScrnMsg.bllgMtrLbCd_V.getValue())) {
//                    continue;
//                }
//                if (prcSvcTierTpCd.equals(viScrnMsg.prcSvcTierTpCd_V.getValue())) {
//                    if (xsMtrAmtRate.compareTo(viScrnMsg.xsMtrAmtRate_V.getValue()) != 0) {
//                        for (int ixU = 0; ixU < scrnMsg.U.getValidCount(); ixU++) {
//                            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(ixU);
//                            if (!XX_FLG_PARENT.equals(uScrnMsg.xxFlgNm_U.getValue())) {
//                                continue;
//                            }
//                            if (mdlId.compareTo(uScrnMsg.mdlId_U.getValue()) != 0) {
//                                continue;
//                            }
//
//                            if (viScrnMsg.bllgMtrLbCd_V.getValue().equals(uScrnMsg.bllgMtrLbCd_U.getValue())) {
//                                uScrnMsg.mtrLbDescTxt_UB.setErrorInfo(1, NSAM0658E, new String[] {prcSvcTierTpNm });
//                                scrnMsg.addCheckItem(uScrnMsg.mtrLbDescTxt_UB);
//                            }
//                        }
//                    }
//                }
//
//            }
            NSAL1330_VBMsg vScrnMsg = scrnMsg.V.no(i);
            BigDecimal mdlId = vScrnMsg.mdlId_V.getValue();
            String bllgMtrLbCd = vScrnMsg.bllgMtrLbCd_V.getValue();
            String prcSvcTierTpCd = vScrnMsg.prcSvcTierTpCd_V.getValue();
            String prcSvcTierTpNm = vScrnMsg.prcSvcTierTpDescTxt_V.getValue();
            BigDecimal xsMtrAmtRate = vScrnMsg.xsMtrAmtRate_V.getValue();

            for (int ix = 0; ix < scrnMsg.V.getValidCount(); ix++) {
                NSAL1330_VBMsg viScrnMsg = scrnMsg.V.no(ix);

                if (i == ix) {
                    continue;
                }
                if (mdlId.compareTo(viScrnMsg.mdlId_V.getValue()) == 0) {
                    continue;
                }
                if (!bllgMtrLbCd.equals(viScrnMsg.bllgMtrLbCd_V.getValue())) {
                    continue;
                }
                if (prcSvcTierTpCd.equals(viScrnMsg.prcSvcTierTpCd_V.getValue())) {
                    if (xsMtrAmtRate.compareTo(viScrnMsg.xsMtrAmtRate_V.getValue()) != 0) {
                        for (int ixU = 0; ixU < scrnMsg.U.getValidCount(); ixU++) {
                            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(ixU);
                            if (!XX_FLG_PARENT.equals(uScrnMsg.xxFlgNm_U.getValue())) {
                                continue;
                            }
                            if (mdlId.compareTo(uScrnMsg.mdlId_U.getValue()) != 0) {
                                continue;
                            }

                            if (viScrnMsg.bllgMtrLbCd_V.getValue().equals(uScrnMsg.bllgMtrLbCd_U.getValue())) {
                                uScrnMsg.mtrLbDescTxt_UB.setErrorInfo(1, NSAM0658E, new String[] {prcSvcTierTpNm });
                                scrnMsg.addCheckItem(uScrnMsg.mtrLbDescTxt_UB);
                            }
                        }
                    }
                }

            }
        }
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @return if error then return true.
     */
    private static boolean aggregateCheckForNonTier(NSAL1330BMsg scrnMsg) {
        boolean isError = false;
        List<String> bllgMtrList = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(i);
            if (!XX_FLG_PARENT.equals(uScrnMsg.xxFlgNm_U.getValue())) {
                continue;
            }
            String bllgMtr = uScrnMsg.bllgMtrLbCd_U.getValue();
            if (!bllgMtrList.contains(bllgMtr)) {
                bllgMtrList.add(bllgMtr);
            }
        }

        for (String bllgMtrLbCd : bllgMtrList) {
            List<Integer> ixList = new ArrayList<Integer>();
            List<BigDecimal> xsMtrAmtRateList = new ArrayList<BigDecimal>();
            for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
                NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(i);
                if (!XX_FLG_PARENT.equals(uScrnMsg.xxFlgNm_U.getValue())) {
                    continue;
                }
                if (bllgMtrLbCd.equals(uScrnMsg.bllgMtrLbCd_U.getValue())) {
                    ixList.add(i);
                    if (!xsMtrAmtRateList.contains(uScrnMsg.xsMtrAmtRate_U.getValue())) {
                        xsMtrAmtRateList.add(uScrnMsg.xsMtrAmtRate_U.getValue());
                    }
                }
            }
            if (xsMtrAmtRateList.size() > 1) {
                for (int ix : ixList) {
                    scrnMsg.U.no(ix).xsMtrAmtRate_U.setErrorInfo(1, NSAM0658E);
                    scrnMsg.addCheckItem(scrnMsg.U.no(ix).xsMtrAmtRate_U);
                }
                isError = true;
            }
        }
        return isError;
    }

    /**
     * Set Button Enable Init
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1330BMsg
     */
    public static void initButton(EZDCommonHandler handler, NSAL1330BMsg scrnMsg) {

        handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, false);
        // START 2017/10/16 [QC#20001, ADD]
        handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT_MODEL, false);
        // END   2017/10/16 [QC#20001, ADD]
        setBandButtonControl(handler, scrnMsg);
        handler.setButtonEnabled(BUTTON_APPLY_ALL_HEADER, false);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            handler.setButtonEnabled(BUTTON_APPLY_ALL_MODEL, i, false);
            NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(i);
            if (isTier(aScrnMsg)) {
                for (int ixZ = 0; ixZ < scrnMsg.Z.getValidCount(); ixZ++) {
                    NSAL1330_ZBMsg zScrnMsg = scrnMsg.Z.no(i);
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
        // START 2017/06/14 [QC#18934, MOD]
        // handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_ACP, true);
        handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_ACP, !DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()));
        // END   2017/06/14 [QC#18934, MOD]
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_ITEM_RE, true);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_ITEM_RE, true);
        handler.setButtonEnabled(BUTTON_SERVICE_PRICE_LIST_REP, true);

        handler.setButtonEnabled(BUTTON_ADD_SERVICE, true);
        handler.setButtonEnabled(BUTTON_DEL_SERVICE, true);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_COVERED_UNIT, true);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_COVERED_UNIT, true);
        handler.setButtonEnabled(BUTTON_ITEM_SEARCH_ADD, true);
        handler.setButtonEnabled(BUTTON_ITEM_DESC_ADD, true);

        NSAL1330CommonLogic.setButtonControlForConfigPricing(handler, scrnMsg);
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setBandButtonControl(EZDCommonHandler handler, NSAL1330BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            NSAL1330_ZBMsg zScrnMsg = scrnMsg.Z.no(i);
            if (XX_FLG_PARENT.equals(zScrnMsg.xxFlgNm_Z.getValue())) {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, true);

            } else {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, false);
            }
        }
    }

    /**
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setBandButtonControlConfig(EZDCommonHandler handler, NSAL1330BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(i);
            if (XX_FLG_PARENT.equals(uScrnMsg.xxFlgNm_U.getValue())) {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, true);

            } else {
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, false);
            }
        }
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @return if Contract Indicator is "Fleet" then return true.
     */
    public static boolean isFleet(NSAL1330BMsg scrnMsg) {
        return DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue());
    }

    private static boolean isTier(NSAL1330_ABMsg aScrnMsg) {
        return ZYPCommonFunc.hasValue(aScrnMsg.prcTierSvcOfferCd_A);
    }

    private static boolean isTier(NSAL1330_RBMsg rScrnMsg) {
        return ZYPCommonFunc.hasValue(rScrnMsg.prcTierSvcOfferCd_R);
    }

    /**
     * add CheckItems
     * @param scrnMsg NSAL1330BMsg
     */
    public static void addCheckItems(NSAL1330BMsg scrnMsg) {

        // 2018/05/07 QC#22482 Del Start
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue())) {
//            scrnMsg.addCheckItem(scrnMsg.svcMemoRsnDescTxt);
//            scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);
//        }
        // 2018/05/07 QC#22482 Del End
        scrnMsg.addCheckItem(scrnMsg.basePrcDealAmt);
        scrnMsg.addCheckItem(scrnMsg.dsContrPk_AD);
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_C);

        int size = 0;
        // START 2017/10/05 [QC#20059, DEL]
        // if (isFleet(scrnMsg)) {
        // END   2017/10/05 [QC#20059, DEL]
        size = scrnMsg.A.getValidCount();
        for (int i = 0; i < size; i++) {
            // START 2017/10/17 [QC#21851, MOD]
            // START 2017/10/05 [QC#20059, ADD]
            // if (isConfigLevelPriceSetting(scrnMsg, scrnMsg.A.no(i).mdlId_A.getValue())) {
            if (isConfigLevelPriceSetting(scrnMsg, scrnMsg.A.no(i).mdlId_A.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
            // END   2017/10/17 [QC#21851, MOD]
                continue;
            }
            // END   2017/10/05 [QC#20059, ADD]
            // START 2017/10/26 [QC#21556, ADD]
            if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.dsContrNum_AD)) {
                continue;
            }
            // END   2017/10/26 [QC#21556, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcMtrPkgPk_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcTierSvcOfferCd_A);
        }
        size = scrnMsg.Z.getValidCount();
        for (int i = 0; i < size; i++) {
            // START 2017/10/17 [QC#21851, MOD]
            // START 2017/10/05 [QC#20059, ADD]
            // if (isConfigLevelPriceSetting(scrnMsg, scrnMsg.Z.no(i).mdlId_Z.getValue())) {
            if (isConfigLevelPriceSetting(scrnMsg, scrnMsg.Z.no(i).mdlId_Z.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
            // END   2017/10/17 [QC#21851, MOD]
                continue;
            }
            // END   2017/10/05 [QC#20059, ADD]
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
            // 2018/05/09 QC#25030 add start
            scrnMsg.addCheckItem(scrnMsg.Z.no(i).bllgFreeCopyCnt_Z);
            scrnMsg.addCheckItem(scrnMsg.Z.no(i).bllgMinCnt_Z);
            scrnMsg.addCheckItem(scrnMsg.Z.no(i).bllgMinAmtRate_Z);
            scrnMsg.addCheckItem(scrnMsg.Z.no(i).bllgRollOverRatio_Z);
            // 2018/05/09 QC#25030 add end
        }
        // START 2017/10/05 [QC#20059, DEL]
        // } else {
        // END   2017/10/05 [QC#20059, DEL]
        size = scrnMsg.R.getValidCount();
        for (int i = 0; i < size; i++) {
            // START 2017/10/17 [QC#21851, MOD]
            // START 2017/10/05 [QC#20059, ADD]
            // if (!isConfigLevelPriceSetting(scrnMsg, scrnMsg.R.no(i).mdlId_R.getValue())) {
            if (!isConfigLevelPriceSetting(scrnMsg, scrnMsg.R.no(i).mdlId_R.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
            // END   2017/10/17 [QC#21851, MOD]
                continue;
            }
            // END   2017/10/05 [QC#20059, ADD]
            scrnMsg.addCheckItem(scrnMsg.R.no(i).prcMtrPkgPk_R);
            scrnMsg.addCheckItem(scrnMsg.R.no(i).prcTierSvcOfferCd_R);

            // START 2017/07/24 [QC#20055, ADD]
            if (ZYPCommonFunc.hasValue(scrnMsg.R.no(i).billToCustCd_R)) {
                scrnMsg.addCheckItem(scrnMsg.R.no(i).billToCustCd_R);
            }
            if (ZYPCommonFunc.hasValue(scrnMsg.R.no(i).billToLocNum_R)) {
                scrnMsg.addCheckItem(scrnMsg.R.no(i).billToLocNum_R);
            }
            // END 2017/07/24 [QC#20055, ADD]
        }
        size = scrnMsg.U.getValidCount();
        for (int i = 0; i < size; i++) {
            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(i);
            if (ZYPConstant.FLG_OFF_N.equals(uScrnMsg.actvFlg_U.getValue())) {
                continue;
            }
            // START 2017/10/17 [QC#21851, MOD]
            // START 2017/10/05 [QC#20059, ADD]
            // if (!isConfigLevelPriceSetting(scrnMsg, scrnMsg.U.no(i).mdlId_U.getValue())) {
            if (!isConfigLevelPriceSetting(scrnMsg, scrnMsg.U.no(i).mdlId_U.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
            // END   2017/10/17 [QC#21851, MOD]
                continue;
            }
            // END   2017/10/05 [QC#20059, ADD]
            scrnMsg.addCheckItem(uScrnMsg.prcListBandDescTxt_U);
            scrnMsg.addCheckItem(uScrnMsg.prcBookMdseCd_U);
            scrnMsg.addCheckItem(uScrnMsg.contrMtrMultRate_U);
            scrnMsg.addCheckItem(uScrnMsg.prcListBandDescTxt_U);
            scrnMsg.addCheckItem(uScrnMsg.xxChkBox_U);
            scrnMsg.addCheckItem(uScrnMsg.prcSvcTierTpCd_U);
            scrnMsg.addCheckItem(uScrnMsg.minCopyVolCnt_U);
            scrnMsg.addCheckItem(uScrnMsg.maxCopyVolCnt_U);
//            if (ZYPCommonFunc.hasValue(uScrnMsg.cpoSvcUsgPrcPk_U) //
//                    && ZYPConstant.FLG_ON_Y.equals(uScrnMsg.xxSmryLineFlg_U.getValue()) //
//                    && !ZYPCommonFunc.hasValue(uScrnMsg.regMtrLbCd_U)) {
//                scrnMsg.addCheckItem(uScrnMsg.mlyCopyInclPrcQty_U);
//                scrnMsg.addCheckItem(uScrnMsg.xsMtrAmtRate_U);
//            }
            if (ZYPCommonFunc.hasValue(uScrnMsg.dsContrBllgMtrPk_U) //
                    && ZYPConstant.FLG_ON_Y.equals(uScrnMsg.xxSmryLineFlg_U.getValue()) //
                    && !ZYPCommonFunc.hasValue(uScrnMsg.regMtrLbCd_U)) {
                scrnMsg.addCheckItem(uScrnMsg.mlyCopyInclPrcQty_U);
                scrnMsg.addCheckItem(uScrnMsg.xsMtrAmtRate_U);
            }
            scrnMsg.addCheckItem(uScrnMsg.mtrLbDescTxt_UB);

            // START 2017/07/24 [QC#20055, ADD]
            if (ZYPCommonFunc.hasValue(uScrnMsg.billToCustCd_U)) {
                scrnMsg.addCheckItem(uScrnMsg.billToCustCd_U);
            }
            if (ZYPCommonFunc.hasValue(uScrnMsg.billToLocNum_U)) {
                scrnMsg.addCheckItem(uScrnMsg.billToLocNum_U);
            }
            // END 2017/07/24 [QC#20055, ADD]
            // 2018/05/09 QC#25030 add start
            scrnMsg.addCheckItem(scrnMsg.U.no(i).bllgFreeCopyCnt_U);
            scrnMsg.addCheckItem(scrnMsg.U.no(i).bllgMinCnt_U);
            scrnMsg.addCheckItem(scrnMsg.U.no(i).bllgMinAmtRate_U);
            scrnMsg.addCheckItem(scrnMsg.U.no(i).bllgRollOverRatio_U);
            // 2018/05/09 QC#25030 add end
        }
        // START 2017/10/05 [QC#20059, DEL]
        // }
        // END   2017/10/05 [QC#20059, DEL]
    
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
            // 2018/05/09 QC#25030 add start
            scrnMsg.addCheckItem(scrnMsg.C.no(i).printDtlFlg_C);
            // 2018/05/09 QC#25030 add end
        }
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param scrnMsg String
     * @param ixA index of R.
     */
    public static void setTierLinkCtrl(EZDCommonHandler handler, NSAL1330BMsg scrnMsg, int ixA) {
        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(ixA);
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
    public static void setTierLinkCtrlConfig(EZDCommonHandler handler, NSAL1330BMsg scrnMsg, int ixR) {
        NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(ixR);
//        BigDecimal mdlId = rScrnMsg.mdlId_R.getValue();
//        BigDecimal cpoSvcConfigRefPk = rScrnMsg.cpoSvcConfigRefPk_R.getValue();
//        String prcTierSvcOfferCd = rScrnMsg.prcTierSvcOfferCd_R.getValue();
//
//        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
//            if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
//                    || !isSameBigDecimal(scrnMsg.U.no(i).mdlId_U.getValue(), mdlId) //
//                    || !isSameBigDecimal(scrnMsg.U.no(i).cpoSvcConfigRefPk_U.getValue(), cpoSvcConfigRefPk)) {
//                continue;
//            }
//            boolean isProtect = !ZYPCommonFunc.hasValue(prcTierSvcOfferCd); //
//            scrnMsg.U.no(i).xxScrEdtTpCd_UL.setInputProtected(isProtect);
//        }
        BigDecimal mdlId = rScrnMsg.mdlId_R.getValue();
        BigDecimal dsContrDtlPk = rScrnMsg.dsContrDtlPk_R.getValue();
        String prcTierSvcOfferCd = rScrnMsg.prcTierSvcOfferCd_R.getValue();

        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                    || !isSameBigDecimal(scrnMsg.U.no(i).mdlId_U.getValue(), mdlId) //
                    || !isSameBigDecimal(scrnMsg.U.no(i).dsContrDtlPk_U.getValue(), dsContrDtlPk)) {
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
     * @param val1 String
     * @param val2 String
     * @return if val1 = val2 then return true.
     */
    private static boolean isSameString(String val1, String val2) {
        return ZYPCommonFunc.hasValue(val1) //
                && ZYPCommonFunc.hasValue(val2) //
                && val1.compareTo(val2) == 0;
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
     * @param scrnMsg NSAL1330BMsg
     * @param sts boolean
     * @param ixZ int
     */
    public static void setBandButton(EZDCommonHandler handler, NSAL1330BMsg scrnMsg, boolean sts, int ixZ) {
        int ixA = getPrcDtlIndex(scrnMsg, ixZ);
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(ixA).scrEntAvalFlg_A.getValue())) {
            sts = false;
        }
        handler.setButtonEnabled(BUTTON_BAND_SEARCH, ixZ, sts);
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1330BMsg
     * @param sts boolean
     * @param ixU int
     */
    public static void setBandButtonConfig(EZDCommonHandler handler, NSAL1330BMsg scrnMsg, boolean sts, int ixU) {
        int ixR = getPrcDtlIndexConfig(scrnMsg, ixU);
        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.R.no(ixR).scrEntAvalFlg_R.getValue())) {
            sts = false;
        }
        handler.setButtonEnabled(BUTTON_BAND_SEARCH_CONFIG, ixU, sts);
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param aScrnMsg NSAL1330_ABMsg
     * @param index int
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setSupplyButton(EZDCommonHandler handler, NSAL1330_ABMsg aScrnMsg, int index, NSAL1330BMsg scrnMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                && PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(aScrnMsg.prcListTpCd_A.getValue()) //
                && ZYPCommonFunc.hasValue(aScrnMsg.prcMtrPkgPk_A)) {
            // START 2017/10/16 [QC#20001, MOD]
            // handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, true);
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT_MODEL, index, true);
            // END   2017/10/16 [QC#20001, MOD]
        } else {
            // START 2017/10/16 [QC#20001, MOD]
            // handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, false);
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT_MODEL, index, false);
            // END   2017/10/16 [QC#20001, MOD]
        }
    }

    /**
     * Set Button Enable when Tier
     * @param handler EZDCommonHandler
     * @param rScrnMsg NSAL1330_RBMsg
     * @param index int
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setSupplyButton(EZDCommonHandler handler, NSAL1330_RBMsg rScrnMsg, int index, NSAL1330BMsg scrnMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                && PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(rScrnMsg.prcListTpCd_R.getValue()) //
                && ZYPCommonFunc.hasValue(rScrnMsg.prcMtrPkgPk_R)) {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, true);
        } else {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, false);
        }
    }

    /**
     * <pre>Band List Pop up open.
     * @param scrnMsg   NSAL1330BMsg
     * @return  NSAL1340 parameter
     * </pre>
     */
    public static Object[] getNSAL1340Param(NSAL1330BMsg scrnMsg) {
        scrnMsg.P.clear();

        int zIndex = scrnMsg.xxNum_Z.getValueInt();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POP_UP_BAND_LIST);
        int ixW = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.Z.no(zIndex).prcCatgCd_Z);
        ixW++;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.Z.no(zIndex).t_MdlNm_Z);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!FLEET.equals(scrnMsg.A.no(i).t_MdlNm_A.getValue()) //
                    && scrnMsg.Z.no(zIndex).mdlId_Z.getValue().compareTo(scrnMsg.A.no(i).mdlId_A.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(zIndex).prcMtrPkgPk_Z, scrnMsg.A.no(i).prcMtrPkgPk_A);
            }
        }
        ixW++;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.prcSvcPlnTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.prcSvcContrTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.Z.no(zIndex).bllgMtrLbCd_Z);

        Object[] param = new Object[NSAL1340_PRM_NUM];
        int ixP = 0;
        ixW = 0;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        ixW++;
        param[ixP++] = scrnMsg.Z.no(zIndex).mdlId_Z;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        ixW++;
        param[ixP++] = scrnMsg.Z.no(zIndex).prcMtrPkgPk_Z;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        param[ixP++] = scrnMsg.prcBaseDt;

        // 20160308 add
        param[ixP++] = scrnMsg.fromPerMthNum;
        param[ixP++] = scrnMsg.toPerMthNum;
        param[ixP++] = scrnMsg.Z.no(zIndex).prcListBandDescTxt_Z;

        // return parameters.
        param[ixP++] = scrnMsg.prcListBandDescTxt_P; // Price List Band Desc Text
        param[ixP++] = scrnMsg.mdseCd_P; // Intangible Merchandise Code
        param[ixP++] = scrnMsg.minCopyVolCnt_P; // Min Cost Per Copy Amount Rate
        param[ixP++] = scrnMsg.maxCopyVolCnt_P; // Max Cost Per Copy Amount Rate
        param[ixP++] = scrnMsg.cpcAmtRate_P; // Cost Per Copy Amount Rate
        param[ixP++] = scrnMsg.baseAmt_P; // Base Amount
        param[ixP] = scrnMsg.prcSvcTierTpCd_P; // Price Service Tier Type Code
        return param;
    }

    /**
     * <pre>Band List Pop up open.
     * @param scrnMsg   NSAL1330BMsg
     * @return  NSAL1340 parameter
     * </pre>
     */
    public static Object[] getNSAL1340ParamConfig(NSAL1330BMsg scrnMsg) {
        scrnMsg.P.clear();

        int ixU = scrnMsg.xxNum_Z.getValueInt();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POP_UP_BAND_LIST_CONFIG);
        int ixW = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.U.no(ixU).prcCatgCd_U);
        ixW++;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.U.no(ixU).t_MdlNm_U);

        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
//            if (scrnMsg.U.no(ixU).mdlId_U.getValue().compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0 //
//                    && scrnMsg.U.no(ixU).cpoSvcConfigRefPk_U.getValue().compareTo(scrnMsg.R.no(i).cpoSvcConfigRefPk_R.getValue()) == 0) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(ixU).prcMtrPkgPk_U, scrnMsg.R.no(i).prcMtrPkgPk_R);
//            }
            if (scrnMsg.U.no(ixU).mdlId_U.getValue().compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0 //
                    && scrnMsg.U.no(ixU).dsOrdPosnNum_U.getValue().compareTo(scrnMsg.R.no(i).dsOrdPosnNum_R.getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(ixU).prcMtrPkgPk_U, scrnMsg.R.no(i).prcMtrPkgPk_R);
            }
        }
        ixW++;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.prcSvcPlnTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.prcSvcContrTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixW++).xxPopPrm_P, scrnMsg.U.no(ixU).bllgMtrLbCd_U);

        Object[] param = new Object[NSAL1340_PRM_NUM];
        int ixP = 0;
        ixW = 0;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        ixW++;
        param[ixP++] = scrnMsg.U.no(ixU).mdlId_U;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        ixW++;
        param[ixP++] = scrnMsg.U.no(ixU).prcMtrPkgPk_U;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        param[ixP++] = scrnMsg.P.no(ixW++).xxPopPrm_P;
        param[ixP++] = scrnMsg.prcBaseDt;

        param[ixP++] = scrnMsg.fromPerMthNum;
        param[ixP++] = scrnMsg.toPerMthNum;
        param[ixP++] = scrnMsg.U.no(ixU).prcListBandDescTxt_U;

        param[ixP++] = scrnMsg.prcListBandDescTxt_P; // Price List Band Desc Text
        param[ixP++] = scrnMsg.mdseCd_P; // Intangible Merchandise Code
        param[ixP++] = scrnMsg.minCopyVolCnt_P; // Min Cost Per Copy Amount Rate
        param[ixP++] = scrnMsg.maxCopyVolCnt_P; // Max Cost Per Copy Amount Rate
        param[ixP++] = scrnMsg.cpcAmtRate_P; // Cost Per Copy Amount Rate
        param[ixP++] = scrnMsg.baseAmt_P; // Base Amount
        param[ixP] = scrnMsg.prcSvcTierTpCd_P; // Price Service Tier Type Code
        return param;
    }

    /**
     * @return AddlChrgItemQuery
     */
    // START 2018/10/10 K.Kojima [QC#28715,MOD]
    // public static String getAddlChrgItemQuery() {
    public static String getAddlChrgItemQuery(NSAL1330BMsg scrnMsg) {
    // END 2018/10/10 K.Kojima [QC#28715,MOD]

        // S21_NA#9283 modify start
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(NEWLINE).append("     M.EZCANCELFLAG                                          ");
        sb.append(NEWLINE).append("    ,M.GLBL_CMPY_CD                                          ");
        sb.append(NEWLINE).append("    ,NVL(OTM.ORD_TAKE_MDSE_CD, M.MDSE_CD) AS MDSE_CD         ");
        sb.append(NEWLINE).append("    ,RTRIM(M.MDSE_DESC_SHORT_TXT) AS MDSE_DESC_SHORT_TXT     ");
        sb.append(NEWLINE).append("FROM                                                           ");
        // START 2018/10/10 K.Kojima [QC#28715,MOD]
        // sb.append(NEWLINE).append("     ITEM_TP_VAL_SET ITVS                                      ");
        // sb.append(NEWLINE).append("    ,MDSE            M                                         ");
        sb.append(NEWLINE).append("     MDSE            M                                         ");
        // END 2018/10/10 K.Kojima [QC#28715,MOD]
        sb.append(NEWLINE).append("    ,ORD_TAKE_MDSE   OTM                                       ");
        sb.append(NEWLINE).append("WHERE                                                          ");
        // START 2018/10/10 K.Kojima [QC#28715,MOD]
        // sb.append(NEWLINE).append("        ITVS.EZCANCELFLAG       = '0'                          ");
        // sb.append(NEWLINE).append("    AND ITVS.ITEM_TP_CTX_TP_CD  = 'CPO_SVC_ADDL_CHRG_QLFY_ITEM_TP'  ");
        // sb.append(NEWLINE).append("    AND ITVS.EZCANCELFLAG       = M.EZCANCELFLAG             ");
        // sb.append(NEWLINE).append("    AND ITVS.GLBL_CMPY_CD       = M.GLBL_CMPY_CD             ");
        // sb.append(NEWLINE).append("    AND ITVS.MDSE_ITEM_TP_CD    = M.MDSE_ITEM_TP_CD          ");
        sb.append(NEWLINE).append("        M.EZCANCELFLAG          = '0'                          ");
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemTpCd)) {
            sb.append(NEWLINE).append("    AND M.MDSE_ITEM_TP_CD       = '").append(scrnMsg.mdseItemTpCd.getValue()).append("'");
        }
        // END 2018/10/10 K.Kojima [QC#28715,MOD]
        sb.append(NEWLINE).append("    AND M.RGTN_STS_CD           = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
        sb.append(NEWLINE).append("    AND M.EZCANCELFLAG          = OTM.EZCANCELFLAG(+)          ");
        sb.append(NEWLINE).append("    AND M.GLBL_CMPY_CD          = OTM.GLBL_CMPY_CD(+)          ");
        sb.append(NEWLINE).append("    AND M.MDSE_CD               = OTM.MDSE_CD(+)               ");
        // S21_NA#9283 modify end
        return sb.toString();
    }

    /**
     * isParentLine
     * @param zScrnMsg NSAL1330_ZBMsg
     * @return if parent(billing meter) line then return true.
     */
    public static boolean isParentLine(NSAL1330_ZBMsg zScrnMsg) {
        return XX_FLG_PARENT.equals(zScrnMsg.xxFlgNm_Z.getValue());
    }

    /**
     * isParentLine
     * @param uScrnMsg NSAL1330_ZBMsg
     * @return if parent(billing meter) line then return true.
     */
    public static boolean isParentLine(NSAL1330_UBMsg uScrnMsg) {
        return XX_FLG_PARENT.equals(uScrnMsg.xxFlgNm_U.getValue());
    }

    /**
     * item protect for reference mode.
     * @param handler S21CommonHandler
     * @param scrnMsg NSAL1330BMsg
     */
    public static void protectRefMode(S21CommonHandler handler, NSAL1330BMsg scrnMsg) {
        // Input Protect
        scrnMsg.xxScrItem50Txt.setInputProtected(true);
        scrnMsg.shellLineNum.setInputProtected(true);
        scrnMsg.manContrOvrdFlg.setInputProtected(true);
        scrnMsg.svcMemoRsnDescTxt.setInputProtected(true);
        scrnMsg.svcCmntTxt.setInputProtected(true);
        // }
        scrnMsg.basePrcDealAmt.setInputProtected(true);
        scrnMsg.dsContrNum_AD.setInputProtected(true);

        // Service Pricing Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1330_ABMsg pricingMsg = scrnMsg.A.no(i);

            // START 2017/10/06 [QC#20059, ADD]
            pricingMsg.xxSelFlg_A.setInputProtected(true);
            // END   2017/10/06 [QC#20059, ADD]
            pricingMsg.t_MdlNm_A.setInputProtected(true);
            pricingMsg.xxTotQtyCnt_A.setInputProtected(true);
            pricingMsg.prcCatgNm_A.setInputProtected(true);
            pricingMsg.prcMtrPkgPk_A.setInputProtected(true);
            pricingMsg.prcTierSvcOfferCd_A.setInputProtected(true);
            pricingMsg.termMthAot_A.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_PB.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_EB.setInputProtected(true);
            pricingMsg.xxTotPrcAmt_TB.setInputProtected(true);
        }
        scrnMsg.prcCatgNm_HB.setInputProtected(true);
        scrnMsg.prcCatgNm_C.setInputProtected(true);

        // Usage
        for (int i = 0; i < scrnMsg.Z.length(); i++) {
            NSAL1330_ZBMsg pricingMsg = scrnMsg.Z.no(i);
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
            // 2018/05/09 QC#25030 add start
            pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(true);
            pricingMsg.bllgMinCnt_Z.setInputProtected(true);
            pricingMsg.bllgMinAmtRate_Z.setInputProtected(true);
            pricingMsg.bllgRollOverRatio_Z.setInputProtected(true);
            // 2018/05/09 QC#25030 add end
        }

        // Accessory Charge Detail
        for (int i = 0; i < scrnMsg.B.length(); i++) {

            NSAL1330_BBMsg acsryMsg = scrnMsg.B.no(i);
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
            NSAL1330_CBMsg addtionlMsg = scrnMsg.C.no(i);

            addtionlMsg.xxChkBox_C.setInputProtected(true);
            addtionlMsg.xxLineNum_C.setInputProtected(true);
            addtionlMsg.mdseCd_CU.setInputProtected(true);
            addtionlMsg.mdseDescShortTxt_CU.setInputProtected(true);
            addtionlMsg.mdseCd_CI.setInputProtected(true);
            addtionlMsg.mdseDescShortTxt_CI.setInputProtected(true);
            addtionlMsg.addlChrgPrcDealAmt_C.setInputProtected(true);
            // 2018/05/09 QC#25030 add start
            addtionlMsg.printDtlFlg_C.setInputProtected(true);
            // 2018/05/09 QC#25030 add end
            
        }

        // buttons
        handler.setButtonEnabled(BUTTON_REFRESH, false);
        handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, false);
        // START 2017/10/16 [QC#20001, ADD]
        handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT_MODEL, false);
        // END   2017/10/16 [QC#20001, ADD]
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
     * @param scrnMsg   NSAL1330BMsg
     * @param handler   EZDCommonHandler
     * </pre>
     */
    public static void setUsgPrcAreaCtrl(NSAL1330BMsg scrnMsg, EZDCommonHandler handler) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(i);
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

            // START 2017/06/19 [QC#18829, ADD]
            boolean baseOnlyFlag = false;
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcMtrPkgPk_A) 
                    && !ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                baseOnlyFlag = true;
            }
            scrnMsg.A.no(i).prcMtrPkgPk_A.setInputProtected(baseOnlyFlag);
            // END   2017/06/19 [QC#18829, ADD]
            // START 2017/10/10 [QC#20059, ADD]
            if (isConfigLevelPriceSetting(scrnMsg, scrnMsg.A.no(i).mdlId_A.getValue())) {
                scrnMsg.A.no(i).prcMtrPkgPk_A.setInputProtected(true);
            }
            // END   2017/10/10 [QC#20059, ADD]
        }
        NSAL1330CommonLogic.setUsgPrcAreaCtrlConfig(scrnMsg, handler);
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1330BMsg
     * @param handler   EZDCommonHandler
     * </pre>
     */
    public static void setUsgPrcAreaCtrlConfig(NSAL1330BMsg scrnMsg, EZDCommonHandler handler) {
        List<String> tierConfigList = new ArrayList<String>();
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(i);
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

            if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
                EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCREEN_ID, "usgPrcConfig" + i);
                guiAttr.setStyleAttribute("display", dspVal);
                scrnMsg.addGUIAttribute(guiAttr);
            }

            if (ZYPCommonFunc.hasValue(rScrnMsg.prcTierSvcOfferCd_R)) {
                if (!tierConfigList.contains(rScrnMsg.dsOrdPosnNum_R.getValue())) {
                    tierConfigList.add(rScrnMsg.dsOrdPosnNum_R.getValue());
                }
            }

            // START 2017/06/19 [QC#18829, ADD]
            boolean baseOnlyFlag = false;
            if (ZYPCommonFunc.hasValue(rScrnMsg.prcMtrPkgPk_R) 
                    && !ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                baseOnlyFlag = true;
            }
            rScrnMsg.prcMtrPkgPk_R.setInputProtected(baseOnlyFlag);
            // END   2017/06/19 [QC#18829, ADD]
        
        }
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(i);
            boolean isProtected = false;
            if (tierConfigList.contains(uScrnMsg.dsOrdPosnNum_U.getValue())) {
                isProtected = true;
            }
            uScrnMsg.mlyCopyInclPrcQty_U.setInputProtected(isProtected);
            uScrnMsg.xsMtrAmtRate_U.setInputProtected(isProtected);
            // START 2017/06/13 K.Kitachi [QC#19004, ADD]
            uScrnMsg.mtrLbDescTxt_UB.setInputProtected(true);
            uScrnMsg.mtrLbDescTxt_U.setInputProtected(true);
            // END 2017/06/13 K.Kitachi [QC#19004, ADD]
        }
        // START 2019/05/29 K.Kitachi [QC#50567, ADD]
        sortMsgArrayRU(scrnMsg);
        // END 2019/05/29 K.Kitachi [QC#50567, ADD]
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1330BMsg
     * </pre>
     */
    public static void setAccessoryChargeAreaCtrl(NSAL1330BMsg scrnMsg) {
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
    private static boolean isRentalOrder(NSAL1330BMsg scrnMsg) {
        return ZYPConstant.FLG_ON_Y.equals(scrnMsg.rntlOrdFlg.getValue());
    }

    /**
     * setScrnDspCtrl
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setScrnDspCtrl(NSAL1330BMsg scrnMsg) {
        setScrnManOvrdDspCtrl(scrnMsg);

    }

    /**
     * setScrnManOvrdDspCtrl
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setScrnManOvrdDspCtrl(NSAL1330BMsg scrnMsg) {
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
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setNSAL1370ReturnInfo(NSAL1330BMsg scrnMsg) {
        List<Integer> delList = new ArrayList<Integer>();
        int ixZ = scrnMsg.xxNum_Z.getValueInt();
        NSAL1330_ZBMsg zScrnMsg = scrnMsg.Z.no(ixZ);
        BigDecimal mdlId = zScrnMsg.mdlId_Z.getValue();
        String mtrLbCd = zScrnMsg.bllgMtrLbCd_Z.getValue();

        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            NSAL1330_XBMsg xScrnMsg = scrnMsg.X.no(i);
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
            NSAL1330_XBMsg xScrnMsg = scrnMsg.X.no(ixX);
            NSAL1330_QBMsg qScrnMsg = scrnMsg.Q.no(ixQ);

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
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setNSAL1370ReturnInfoConfig(NSAL1330BMsg scrnMsg) {
        List<Integer> delList = new ArrayList<Integer>();
        int ixU = scrnMsg.xxNum_Z.getValueInt();
        NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(ixU);
        BigDecimal mdlId = uScrnMsg.mdlId_U.getValue();
        BigDecimal dsContrBllgMtrPk = uScrnMsg.dsContrBllgMtrPk_U.getValue();
        BigDecimal dsContrDtlPk = uScrnMsg.dsContrDtlPk_U.getValue();
        String dsOrdPosnNum = uScrnMsg.dsOrdPosnNum_U.getValue();
        String mtrLbCd = uScrnMsg.bllgMtrLbCd_U.getValue();

        for (int i = 0; i < scrnMsg.V.getValidCount(); i++) {
            NSAL1330_VBMsg vScrnMsg = scrnMsg.V.no(i);
            if (!isSameBigDecimal(mdlId, vScrnMsg.mdlId_V.getValue()) //
                    || !isSameString(dsOrdPosnNum, vScrnMsg.dsOrdPosnNum_V.getValue())) {
                continue;
            }
            if (mtrLbCd.equals(vScrnMsg.bllgMtrLbCd_V.getValue())) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(scrnMsg.V, delList);

        int ixV = scrnMsg.V.getValidCount();
        for (int ixQ = 0; ixQ < scrnMsg.Q.getValidCount(); ixQ++) {
            NSAL1330_VBMsg vScrnMsg = scrnMsg.V.no(ixV);
            NSAL1330_QBMsg qScrnMsg = scrnMsg.Q.no(ixQ);

            EZDMsg.copy(qScrnMsg, "Q", vScrnMsg, "V");
            ZYPEZDItemValueSetter.setValue(vScrnMsg.mdlId_V, mdlId);
            ZYPEZDItemValueSetter.setValue(vScrnMsg.dsContrDtlPk_V, dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(vScrnMsg.dsContrBllgMtrPk_V, dsContrBllgMtrPk);
            ZYPEZDItemValueSetter.setValue(vScrnMsg.dsOrdPosnNum_V, dsOrdPosnNum);

            ixV++;
        }
        scrnMsg.V.setValidCount(ixV);

        if (scrnMsg.Q.getValidCount() > 1) {
            uScrnMsg.xxScrEdtTpCd_U.setValue(ZYPConstant.FLG_ON_Y);
        }
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     */
    public static void checkForSave(NSAL1330BMsg scrnMsg) {
        // 2018/05/07 QC#22482 Del Start
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue())) {
//            if (!ZYPCommonFunc.hasValue(scrnMsg.svcCmntTxt)) {
//                scrnMsg.svcCmntTxt.setErrorInfo(1, NSAM0654E, new String[] {"Manual Override Comments" });
//            }
//            if (!ZYPCommonFunc.hasValue(scrnMsg.svcMemoRsnDescTxt)) {
//                scrnMsg.svcMemoRsnDescTxt.setErrorInfo(1, NSAM0654E, new String[] {"Manual Override Reason" });
//            }
//            return;
//        }
        // 2018/05/07 QC#22482 Del End

        // START 2017/10/06 [QC#20059, MOD]
        // if (isFleet(scrnMsg)) {
        //     checkForFleet(scrnMsg);
        // } else {
        //    checkForNonFleet(scrnMsg);
        // }
        checkForFleet(scrnMsg);
        checkForNonFleet(scrnMsg);
        // START 2017/10/06 [QC#20059, MOD]

        closeCheckAddlBase(scrnMsg);

        closeCheckAddlChrg(scrnMsg);
    }

    private static void checkForNonFleet(NSAL1330BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(i);
            // START 2017/10/18 [QC#21851, MOD]
            // START 2017/10/06 [QC#20059, ADD]
            // if (!isConfigLevelPriceSetting(scrnMsg, rScrnMsg.mdlId_R.getValue())) {
            if (!isConfigLevelPriceSetting(scrnMsg, rScrnMsg.mdlId_R.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
            // END   2017/10/18 [QC#21851, MOD]
                continue;
            }
            // END   2017/10/06 [QC#20059, ADD]
            if (ZYPCommonFunc.hasValue(rScrnMsg.xxTotPrcAmt_PR) //
                    && BigDecimal.ZERO.compareTo(rScrnMsg.xxTotPrcAmt_PR.getValue()) > 0) {
                rScrnMsg.xxTotPrcAmt_PR.setErrorInfo(1, NSAM0675E, new String[] {"Unit Periodic Base" });
                scrnMsg.addCheckItem(scrnMsg.R.no(i).xxTotPrcAmt_PR);
            }
            NSAL1330CommonLogic.checkMdlSvcForSaveConfig(rScrnMsg, scrnMsg.usgBllgCycleCd.getValue(), scrnMsg);
        }

//        BigDecimal multRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_FCT_NUM, scrnMsg.glblCmpyCd.getValue());
//        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
//            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(i);
//            if (ZYPConstant.FLG_OFF_N.equals(uScrnMsg.actvFlg_U.getValue())) {
//                continue;
//            }
//            BigDecimal mdlId = uScrnMsg.mdlId_U.getValue();
//            BigDecimal cpoSvcConfigRefPk = uScrnMsg.cpoSvcConfigRefPk_U.getValue();
//            if (hasSvcPkg(scrnMsg, mdlId, cpoSvcConfigRefPk) //
//                    && ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
//                NSAL1330CommonLogic.checkUsgPrcForSaveConfig(uScrnMsg, scrnMsg, multRate);
//            }
//        }
        BigDecimal multRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_FCT_NUM, scrnMsg.glblCmpyCd.getValue());
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NSAL1330_UBMsg uScrnMsg = scrnMsg.U.no(i);
            // START 2017/10/18 [QC#21851, MOD]
            // START 2017/10/06 [QC#20059, ADD]
            // if (!isConfigLevelPriceSetting(scrnMsg, uScrnMsg.mdlId_U.getValue())) {
            if (!isConfigLevelPriceSetting(scrnMsg, uScrnMsg.mdlId_U.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
            // END   2017/10/18 [QC#21851, MOD]
                continue;
            }
            // END   2017/10/06 [QC#20059, ADD]
            if (ZYPConstant.FLG_OFF_N.equals(uScrnMsg.actvFlg_U.getValue())) {
                continue;
            }
            BigDecimal mdlId = uScrnMsg.mdlId_U.getValue();
            String dsOrdPosnNum = uScrnMsg.dsOrdPosnNum_U.getValue();
            if (hasSvcPkg(scrnMsg, mdlId, dsOrdPosnNum) //
                    && ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                NSAL1330CommonLogic.checkUsgPrcForSaveConfig(uScrnMsg, scrnMsg, multRate);
            }
        }

        if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue())) {
            aggregateCheck(scrnMsg);
        }
    }

    /**
     * @param scrnMsg
     */
    private static void checkForFleet(NSAL1330BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(i);
            // START 2017/10/18 [QC#21851, MOD]
            // START 2017/10/06 [QC#20059, ADD]
            // if (isConfigLevelPriceSetting(scrnMsg, aScrnMsg.mdlId_A.getValue())) {
            if (isConfigLevelPriceSetting(scrnMsg, aScrnMsg.mdlId_A.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
            // END   2017/10/18 [QC#21851, MOD]
                continue;
            }
            // END   2017/10/06 [QC#20059, ADD]
            if (ZYPCommonFunc.hasValue(aScrnMsg.xxTotPrcAmt_PB) //
                    && BigDecimal.ZERO.compareTo(aScrnMsg.xxTotPrcAmt_PB.getValue()) > 0) {
                aScrnMsg.xxTotPrcAmt_PB.setErrorInfo(1, NSAM0675E, new String[] {"Unit Periodic Base" });
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxTotPrcAmt_PB);
            }
            NSAL1330CommonLogic.checkMdlSvcForSave(aScrnMsg, scrnMsg.usgBllgCycleCd.getValue(), scrnMsg);
        }

        BigDecimal multRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_FCT_NUM, scrnMsg.glblCmpyCd.getValue());
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            NSAL1330_ZBMsg zScrnMsg = scrnMsg.Z.no(i);
            // START 2017/10/18 [QC#21851, MOD]
            // START 2017/10/05 [QC#20059, ADD]
            // if (isConfigLevelPriceSetting(scrnMsg, zScrnMsg.mdlId_Z.getValue())) {
            if (isConfigLevelPriceSetting(scrnMsg, zScrnMsg.mdlId_Z.getValue()) || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
            // END   2017/10/18 [QC#21851, MOD]
                continue;
            }
            // END   2017/10/05 [QC#20059, ADD]
            BigDecimal mdlId = zScrnMsg.mdlId_Z.getValue();
            if (hasSvcPkg(scrnMsg, mdlId) && ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                NSAL1330CommonLogic.checkUsgPrcForSave(zScrnMsg, scrnMsg, multRate);
            }
        }
    }

    private static boolean hasSvcPkg(NSAL1330BMsg scrnMsg, BigDecimal mdlId) {
        int ixA = 0;
        if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(i);
                if (mdlId.compareTo(aScrnMsg.mdlId_A.getValue()) == 0) {
                    ixA = i;
                    break;
                }
            }
        }
        return ZYPCommonFunc.hasValue(scrnMsg.A.no(ixA).prcMtrPkgPk_A);
    }

    private static boolean hasSvcPkg(NSAL1330BMsg scrnMsg, BigDecimal mdlId, String dsOrdPosnNum) {
        int ixR = 0;
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NSAL1330_RBMsg aScrnMsg = scrnMsg.R.no(i);
//            if (mdlId.compareTo(aScrnMsg.mdlId_R.getValue()) == 0 //
//                    && cpoSvcConfigRefPk.compareTo(aScrnMsg.cpoSvcConfigRefPk_R.getValue()) == 0) {
//                ixR = i;
//                break;
//            }
            if (mdlId.compareTo(aScrnMsg.mdlId_R.getValue()) == 0 //
                     && dsOrdPosnNum.compareTo(aScrnMsg.dsOrdPosnNum_R.getValue()) == 0) {
                ixR = i;
                break;
            }
        }
        return ZYPCommonFunc.hasValue(scrnMsg.R.no(ixR).prcMtrPkgPk_R);
    }

    private static void checkUsgPrcForSave(NSAL1330_ZBMsg zScrnMsg, NSAL1330BMsg scrnMsg, BigDecimal multRate) {
        if (ZYPCommonFunc.hasValue(zScrnMsg.contrMtrMultRate_Z) //
                && BigDecimal.ZERO.compareTo(zScrnMsg.contrMtrMultRate_Z.getValue()) > 0) {
            zScrnMsg.contrMtrMultRate_Z.setErrorInfo(1, NSAM0675E, new String[] {"Multiplier" });
            scrnMsg.addCheckItem(zScrnMsg.contrMtrMultRate_Z);
        }
        if (ZYPCommonFunc.hasValue(zScrnMsg.mlyCopyInclPrcQty_Z) //
                && BigDecimal.ZERO.compareTo(zScrnMsg.mlyCopyInclPrcQty_Z.getValue()) > 0) {
            zScrnMsg.mlyCopyInclPrcQty_Z.setErrorInfo(1, NSAM0675E, new String[] {"Per Unit Periodic Covered Images" });
            scrnMsg.addCheckItem(zScrnMsg.mlyCopyInclPrcQty_Z);
        }
        if (ZYPCommonFunc.hasValue(zScrnMsg.xsMtrAmtRate_Z) //
                && BigDecimal.ZERO.compareTo(zScrnMsg.xsMtrAmtRate_Z.getValue()) > 0) {
            zScrnMsg.xsMtrAmtRate_Z.setErrorInfo(1, NSAM0675E, new String[] {"Excess Per Image Charge" });
            scrnMsg.addCheckItem(zScrnMsg.xsMtrAmtRate_Z);
        }

        if (XX_FLG_PARENT.equals(zScrnMsg.xxFlgNm_Z.getValue())) {
            // 2018/05/09 QC#25030 add start
            if (!NSXC001001ContrValidation.checkBllgMtrParam(zScrnMsg.bllgMinCnt_Z.getValue(), zScrnMsg.bllgMinAmtRate_Z.getValue(), zScrnMsg.bllgRollOverRatio_Z.getValue(), zScrnMsg.bllgFreeCopyCnt_Z.getValue())) {
                zScrnMsg.bllgMinCnt_Z.setErrorInfo(1, NSZM0681E);
                zScrnMsg.bllgMinAmtRate_Z.setErrorInfo(1, NSZM0681E);
                zScrnMsg.bllgRollOverRatio_Z.setErrorInfo(1, NSZM0681E);
                zScrnMsg.bllgFreeCopyCnt_Z.setErrorInfo(1, NSZM0681E);
            }

            int tierCnt = 0;
            if (isTier(zScrnMsg, scrnMsg)) {
                tierCnt = 2;
            }

            if (!NSXC001001ContrValidation.checkXsMinVol(zScrnMsg.bllgMinCnt_Z.getValue(), tierCnt)) {
                zScrnMsg.bllgMinCnt_Z.setErrorInfo(1, NSAM0477E, new String[] {"Min. Vol" });
            }

            if (!NSXC001001ContrValidation.checkXsMinVol(zScrnMsg.bllgMinCnt_Z.getValue(), zScrnMsg.mlyCopyInclPrcQty_Z.getValue())) {
                zScrnMsg.mlyCopyInclPrcQty_Z.setErrorInfo(1, NSAM0717E);
            }

            if (!NSXC001001ContrValidation.checkXsMinAmt(zScrnMsg.bllgMinAmtRate_Z.getValue(), tierCnt)) {
                zScrnMsg.bllgMinAmtRate_Z.setErrorInfo(1, NSAM0477E, new String[] {"Min. Amt" });
            }

            if (!ZYPCommonFunc.hasValue(zScrnMsg.bllgFreeCopyCnt_Z) && !NSXC001001ContrValidation.checkRollOver(zScrnMsg.bllgRollOverRatio_Z.getValue(), tierCnt)) {
                zScrnMsg.bllgRollOverRatio_Z.setErrorInfo(1, NSAM0477E, new String[] {"Rollover%" });
            }

            if (!NSXC001001ContrValidation.checkFreeCopy(zScrnMsg.bllgFreeCopyCnt_Z.getValue(), tierCnt)) {
                zScrnMsg.bllgFreeCopyCnt_Z.setErrorInfo(1, NSAM0477E, new String[] {"Free copies" });
            }
            // 2018/05/09 QC#25030 add end

            if (isTier(zScrnMsg, scrnMsg)) {
                checkTierInfo(zScrnMsg, scrnMsg);
            } else {
                if (!ZYPCommonFunc.hasValue(zScrnMsg.mlyCopyInclPrcQty_Z)) {
                    zScrnMsg.mlyCopyInclPrcQty_Z.setErrorInfo(1, ZZM9000E, new String[] {"Periodic Covered Image" });
                }
                if (!ZYPCommonFunc.hasValue(zScrnMsg.xsMtrAmtRate_Z)) {
                    zScrnMsg.xsMtrAmtRate_Z.setErrorInfo(1, ZZM9000E, new String[] {"Excess Per Image Charge" });
                }
            }

        }

        if (XX_FLG_HARD.equals(zScrnMsg.xxFlgNm_Z.getValue())) {
            if (!ZYPCommonFunc.hasValue(zScrnMsg.contrMtrMultRate_Z)) {
                zScrnMsg.contrMtrMultRate_Z.setErrorInfo(1, ZZM9000E, new String[] {"Multiplier" });
            }

            BigDecimal multiplier = zScrnMsg.contrMtrMultRate_Z.getValue();
            if (ZYPCommonFunc.hasValue(multiplier) && BigDecimal.ZERO.compareTo(multiplier) != 0) {
                if (multiplier.remainder(multRate).compareTo(BigDecimal.ZERO) != 0) {
                    zScrnMsg.contrMtrMultRate_Z.setErrorInfo(1, NSAM0661E, new String[] {multRate.toPlainString() });
                }
            }
        }
    }

    private static void checkUsgPrcForSaveConfig(//
            NSAL1330_UBMsg uScrnMsg, NSAL1330BMsg scrnMsg, BigDecimal multRate) {
        if (ZYPCommonFunc.hasValue(uScrnMsg.contrMtrMultRate_U) //
                && BigDecimal.ZERO.compareTo(uScrnMsg.contrMtrMultRate_U.getValue()) > 0) {
            uScrnMsg.contrMtrMultRate_U.setErrorInfo(1, NSAM0675E, new String[] {"Multiplier" });
            scrnMsg.addCheckItem(uScrnMsg.contrMtrMultRate_U);
        }
        if (ZYPCommonFunc.hasValue(uScrnMsg.mlyCopyInclPrcQty_U) //
                && BigDecimal.ZERO.compareTo(uScrnMsg.mlyCopyInclPrcQty_U.getValue()) > 0) {
            uScrnMsg.mlyCopyInclPrcQty_U.setErrorInfo(1, NSAM0675E, new String[] {"Per Unit Periodic Covered Images" });
            scrnMsg.addCheckItem(uScrnMsg.mlyCopyInclPrcQty_U);
        }
        if (ZYPCommonFunc.hasValue(uScrnMsg.xsMtrAmtRate_U) //
                && BigDecimal.ZERO.compareTo(uScrnMsg.xsMtrAmtRate_U.getValue()) > 0) {
            uScrnMsg.xsMtrAmtRate_U.setErrorInfo(1, NSAM0675E, new String[] {"Excess Per Image Charge" });
            scrnMsg.addCheckItem(uScrnMsg.xsMtrAmtRate_U);
        }

        if (XX_FLG_PARENT.equals(uScrnMsg.xxFlgNm_U.getValue())) {
            // 2018/05/09 QC#25030 add start
            if (!NSXC001001ContrValidation.checkBllgMtrParam(uScrnMsg.bllgMinCnt_U.getValue(), uScrnMsg.bllgMinAmtRate_U.getValue(), uScrnMsg.bllgRollOverRatio_U.getValue(), uScrnMsg.bllgFreeCopyCnt_U.getValue())) {
                uScrnMsg.bllgMinCnt_U.setErrorInfo(1, NSZM0681E);
                uScrnMsg.bllgMinAmtRate_U.setErrorInfo(1, NSZM0681E);
                uScrnMsg.bllgRollOverRatio_U.setErrorInfo(1, NSZM0681E);
                uScrnMsg.bllgFreeCopyCnt_U.setErrorInfo(1, NSZM0681E);
            }

            int tierCnt = 0;
            if (isTier(uScrnMsg, scrnMsg)) {
                tierCnt = 2;
            }

            if (!NSXC001001ContrValidation.checkXsMinVol(uScrnMsg.bllgMinCnt_U.getValue(), tierCnt)) {
                uScrnMsg.bllgMinCnt_U.setErrorInfo(1, NSAM0477E, new String[] {"Min. Vol" });
            }

            if (!NSXC001001ContrValidation.checkXsMinVol(uScrnMsg.bllgMinCnt_U.getValue(), uScrnMsg.mlyCopyInclPrcQty_U.getValue())) {
                uScrnMsg.mlyCopyInclPrcQty_U.setErrorInfo(1, NSAM0717E);
            }

            if (!NSXC001001ContrValidation.checkXsMinAmt(uScrnMsg.bllgMinAmtRate_U.getValue(), tierCnt)) {
                uScrnMsg.bllgMinAmtRate_U.setErrorInfo(1, NSAM0477E, new String[] {"Min. Amt" });
            }

            if (!ZYPCommonFunc.hasValue(uScrnMsg.bllgFreeCopyCnt_U) && !NSXC001001ContrValidation.checkRollOver(uScrnMsg.bllgRollOverRatio_U.getValue(), tierCnt)) {
                uScrnMsg.bllgRollOverRatio_U.setErrorInfo(1, NSAM0477E, new String[] {"Rollover%" });
            }

            if (!NSXC001001ContrValidation.checkFreeCopy(uScrnMsg.bllgFreeCopyCnt_U.getValue(), tierCnt)) {
                uScrnMsg.bllgFreeCopyCnt_U.setErrorInfo(1, NSAM0477E, new String[] {"Free copies" });
            }
            // 2018/05/09 QC#25030 add end

            if (isTier(uScrnMsg, scrnMsg)) {
                checkTierInfo(uScrnMsg, scrnMsg);
            } else {
                if (!ZYPCommonFunc.hasValue(uScrnMsg.mlyCopyInclPrcQty_U)) {
                    uScrnMsg.mlyCopyInclPrcQty_U.setErrorInfo(1, ZZM9000E, new String[] {"Periodic Covered Image" });
                }
                if (!ZYPCommonFunc.hasValue(uScrnMsg.xsMtrAmtRate_U)) {
                    uScrnMsg.xsMtrAmtRate_U.setErrorInfo(1, ZZM9000E, new String[] {"Excess Per Image Charge" });
                }
            }

        }

        if (XX_FLG_HARD.equals(uScrnMsg.xxFlgNm_U.getValue())) {
            if (!ZYPCommonFunc.hasValue(uScrnMsg.contrMtrMultRate_U)) {
                uScrnMsg.contrMtrMultRate_U.setErrorInfo(1, ZZM9000E, new String[] {"Multiplier" });
            }

            BigDecimal multiplier = uScrnMsg.contrMtrMultRate_U.getValue();
            if (ZYPCommonFunc.hasValue(multiplier) && BigDecimal.ZERO.compareTo(multiplier) != 0) {
                if (multiplier.remainder(multRate).compareTo(BigDecimal.ZERO) != 0) {
                    uScrnMsg.contrMtrMultRate_U.setErrorInfo(1, NSAM0661E, new String[] {multRate.toPlainString() });
                }
            }
        }
    }

    private static void checkTierInfo(NSAL1330_ZBMsg zScrnMsg, NSAL1330BMsg scrnMsg) {
        String mtrLbCd = zScrnMsg.bllgMtrLbCd_Z.getValue();
        BigDecimal mdlId = zScrnMsg.mdlId_Z.getValue();
        int tireCnt = 0;
        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            if (mtrLbCd.equals(scrnMsg.X.no(i).bllgMtrLbCd_X.getValue()) //
                    && (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                    || mdlId.compareTo(scrnMsg.X.no(i).mdlId_X.getValue()) == 0)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.X.no(i).minCopyVolCnt_X) //
                        || !ZYPCommonFunc.hasValue(scrnMsg.X.no(i).maxCopyVolCnt_X) //
                        || !ZYPCommonFunc.hasValue(scrnMsg.X.no(i).xsMtrAmtRate_X)) {
                    // START 2017/10/27 [QC#21556, ADD]
                    if (!DS_CONTR_CATG.REGULAR.equals(scrnMsg.dsContrCatgCd.getValue()) 
                            && ZYPCommonFunc.hasValue(scrnMsg.dsContrPk_AD) ) {
                        continue;
                    }
                    // END   2017/10/27 [QC#21556, ADD]
                    zScrnMsg.prcListBandDescTxt_Z.setErrorInfo(1, ZZM9000E, new String[] {"Tier Information" });
                }
                tireCnt++;
            }
        }
        // 2018/07/09 S21_NA#26528 Mod Start
        // if (tireCnt < 2) {
            // zScrnMsg.prcListBandDescTxt_Z.setErrorInfo(1, NSAM0662E);
        // }
        if (tireCnt < 1) {
            zScrnMsg.prcListBandDescTxt_Z.setErrorInfo(1, ZZM9000E, new String[] {"Tier Information" });
        }
        // 2018/07/09 S21_NA#26528 Mod End
    }

    private static void checkTierInfo(NSAL1330_UBMsg uScrnMsg, NSAL1330BMsg scrnMsg) {
//        String mtrLbCd = uScrnMsg.bllgMtrLbCd_U.getValue();
//        BigDecimal mdlId = uScrnMsg.mdlId_U.getValue();
//        BigDecimal cpoSvcConfigRefPk = uScrnMsg.cpoSvcConfigRefPk_U.getValue();
//        int tireCnt = 0;
//        for (int i = 0; i < scrnMsg.V.getValidCount(); i++) {
//            if (mtrLbCd.equals(scrnMsg.V.no(i).bllgMtrLbCd_V.getValue()) //
//                    && (mdlId.compareTo(scrnMsg.V.no(i).mdlId_V.getValue()) == 0 //
//                    && cpoSvcConfigRefPk.compareTo(scrnMsg.V.no(i).cpoSvcConfigRefPk_V.getValue()) == 0)) {
//                if (!ZYPCommonFunc.hasValue(scrnMsg.V.no(i).minCopyVolCnt_V) //
//                        || !ZYPCommonFunc.hasValue(scrnMsg.V.no(i).maxCopyVolCnt_V) //
//                        || !ZYPCommonFunc.hasValue(scrnMsg.V.no(i).xsMtrAmtRate_V)) {
//                    uScrnMsg.prcListBandDescTxt_U.setErrorInfo(1, ZZM9000E, new String[] {"Tier Information" });
//                }
//                tireCnt++;
//            }
//        }
        String mtrLbCd = uScrnMsg.bllgMtrLbCd_U.getValue();
        BigDecimal mdlId = uScrnMsg.mdlId_U.getValue();
        String dsOrdPosnNum = uScrnMsg.dsOrdPosnNum_U.getValue();
        int tireCnt = 0;
        for (int i = 0; i < scrnMsg.V.getValidCount(); i++) {
            if (mtrLbCd.equals(scrnMsg.V.no(i).bllgMtrLbCd_V.getValue()) //
                    && (mdlId.compareTo(scrnMsg.V.no(i).mdlId_V.getValue()) == 0 //
                    && dsOrdPosnNum.compareTo(scrnMsg.V.no(i).dsOrdPosnNum_V.getValue()) == 0)) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.V.no(i).minCopyVolCnt_V) //
                        || !ZYPCommonFunc.hasValue(scrnMsg.V.no(i).maxCopyVolCnt_V) //
                        || !ZYPCommonFunc.hasValue(scrnMsg.V.no(i).xsMtrAmtRate_V)) {
                    // START 2017/10/27 [QC#21556, ADD]
                    if (!DS_CONTR_CATG.REGULAR.equals(scrnMsg.dsContrCatgCd.getValue()) 
                            && ZYPCommonFunc.hasValue(scrnMsg.dsContrPk_AD) ) {
                        continue;
                    }
                    // END   2017/10/27 [QC#21556, ADD]
                    uScrnMsg.prcListBandDescTxt_U.setErrorInfo(1, ZZM9000E, new String[] {"Tier Information" });
                }
                tireCnt++;
            }
        }
        // 2018/07/09 S21_NA#26528 Mod Start
        // if (tireCnt < 2) {
            // uScrnMsg.prcListBandDescTxt_U.setErrorInfo(1, NSAM0662E);
        // }
        if (tireCnt < 1) {
            uScrnMsg.prcListBandDescTxt_U.setErrorInfo(1, ZZM9000E, new String[] {"Tier Information" });
        }
        // 2018/07/09 S21_NA#26528 Mod End
    }

    private static boolean isTier(NSAL1330_ZBMsg zScrnMsg, NSAL1330BMsg scrnMsg) {
        if (isFleet(scrnMsg)) {
            return isTier(scrnMsg.A.no(0));
        }
        BigDecimal mdlId = zScrnMsg.mdlId_Z.getValue();
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (mdlId.compareTo(scrnMsg.A.no(i).mdlId_A.getValue()) == 0) {
                return isTier(scrnMsg.A.no(i));
            }
        }
        return false;
    }

    private static boolean isTier(NSAL1330_UBMsg uScrnMsg, NSAL1330BMsg scrnMsg) {
        BigDecimal mdlId = uScrnMsg.mdlId_U.getValue();
//        BigDecimal cpoSvcConfigRefPk = uScrnMsg.cpoSvcConfigRefPk_U.getValue();
//        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
//            if (mdlId.compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0 //
//                    && cpoSvcConfigRefPk.compareTo(scrnMsg.R.no(i).cpoSvcConfigRefPk_R.getValue()) == 0) {
//                return isTier(scrnMsg.R.no(i));
//            }
//        }

        String dsOrdPosnNum = uScrnMsg.dsOrdPosnNum_U.getValue();
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (mdlId.compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0 //
                    && dsOrdPosnNum.compareTo(scrnMsg.R.no(i).dsOrdPosnNum_R.getValue()) == 0) {
                return isTier(scrnMsg.R.no(i));
            }
        }

        return false;
    }

    private static void checkMdlSvcForSave(NSAL1330_ABMsg aScrnMsg, String usgBllgCycleCd, NSAL1330BMsg scrnMsg) {
        // START 2017/10/10 [QC#20059, DEL]
        // if (isNoEntry(aScrnMsg, usgBllgCycleCd)) {
        //     return;
        // }
        // END   2017/10/10 [QC#20059, DEL]
        // START 2017/10/26 [QC#21556, ADD]
        if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.dsContrNum_AD)) {
            return;
        }
        // END   2017/10/26 [QC#21556, ADD]
        if (!ZYPCommonFunc.hasValue(aScrnMsg.prcCatgNm_A)) {
            aScrnMsg.prcCatgNm_A.setErrorInfo(1, ZZM9000E, new String[] {"Service Price List" });
            scrnMsg.addCheckItem(aScrnMsg.prcCatgNm_A);
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.xxTotPrcAmt_PB)) {
            aScrnMsg.xxTotPrcAmt_PB.setErrorInfo(1, ZZM9000E, new String[] {"Unit Periodic Base" });
            scrnMsg.addCheckItem(aScrnMsg.xxTotPrcAmt_PB);
        } else {
            if (MAX_BASE_AMT.compareTo(aScrnMsg.xxTotPrcAmt_PB.getValue()) < 0) {
                aScrnMsg.xxTotPrcAmt_PB.setErrorInfo(1, NSAM0652E, new String[] {DF_AMT.format(MAX_BASE_AMT.longValue()) });
                scrnMsg.addCheckItem(aScrnMsg.xxTotPrcAmt_PB);
            }
        }
        if (!ZYPCommonFunc.hasValue(aScrnMsg.prcMtrPkgPk_A)) {
            aScrnMsg.prcMtrPkgPk_A.setErrorInfo(1, ZZM9000E, new String[] {"Service Package" });
            scrnMsg.addCheckItem(aScrnMsg.prcMtrPkgPk_A);
        }

    }

    private static void checkMdlSvcForSaveConfig(NSAL1330_RBMsg rScrnMsg, String usgBllgCycleCd, NSAL1330BMsg scrnMsg) {
        // START 2017/10/10 [QC#20059, DEL]
        // if (isNoEntry(rScrnMsg, usgBllgCycleCd)) {
        //     return;
        // }
        // END   2017/10/10 [QC#20059, DEL]
        if (!ZYPCommonFunc.hasValue(rScrnMsg.prcCatgNm_R)) {
            rScrnMsg.prcCatgNm_R.setErrorInfo(1, ZZM9000E, new String[] {"Service Price List" });
            scrnMsg.addCheckItem(rScrnMsg.prcCatgNm_R);
        }
        if (!ZYPCommonFunc.hasValue(rScrnMsg.xxTotPrcAmt_PR)) {
            rScrnMsg.xxTotPrcAmt_PR.setErrorInfo(1, ZZM9000E, new String[] {"Unit Periodic Base" });
            scrnMsg.addCheckItem(rScrnMsg.xxTotPrcAmt_PR);
        } else {
            if (MAX_BASE_AMT.compareTo(rScrnMsg.xxTotPrcAmt_PR.getValue()) < 0) {
                rScrnMsg.xxTotPrcAmt_PR.setErrorInfo(1, NSAM0652E, new String[] {DF_AMT.format(MAX_BASE_AMT.longValue()) });
                scrnMsg.addCheckItem(rScrnMsg.xxTotPrcAmt_PR);
            }
        }
        if (!ZYPCommonFunc.hasValue(rScrnMsg.prcMtrPkgPk_R)) {
            rScrnMsg.prcMtrPkgPk_R.setErrorInfo(1, ZZM9000E, new String[] {"Service Package" });
            scrnMsg.addCheckItem(rScrnMsg.prcMtrPkgPk_R);
        }

    }

// START 2017/10/10 [QC#20059, DEL]
//    private static boolean isNoEntry(NSAL1330_ABMsg aScrnMsg, String usgBllgCycleCd) {
//        if (!ZYPCommonFunc.hasValue(usgBllgCycleCd)) {
//            if (ZYPCommonFunc.hasValue(aScrnMsg.prcCatgCd_A)) {
//                return false;
//            }
//            if (ZYPCommonFunc.hasValue(aScrnMsg.basePrcDealAmt_A) //
//                    && BigDecimal.ZERO.compareTo(aScrnMsg.basePrcDealAmt_A.getValue()) != 0) {
//                return false;
//            }
//            return true;
//        }
//
//        if (ZYPCommonFunc.hasValue(aScrnMsg.prcMtrPkgPk_A)) {
//            return false;
//        }
//        if (ZYPCommonFunc.hasValue(aScrnMsg.prcTierSvcOfferCd_A)) {
//            return false;
//        }
//        if (ZYPCommonFunc.hasValue(aScrnMsg.xxTotPrcAmt_PB) //
//                && BigDecimal.ZERO.compareTo(aScrnMsg.xxTotPrcAmt_PB.getValue()) != 0) {
//            return false;
//        }
//        return true;
//    }
// END   2017/10/10 [QC#20059, DEL]

    private static boolean isNoEntry(NSAL1330_RBMsg rScrnMsg, String usgBllgCycleCd) {
        if (!ZYPCommonFunc.hasValue(usgBllgCycleCd)) {
            if (ZYPCommonFunc.hasValue(rScrnMsg.prcCatgCd_R)) {
                return false;
            }
            if (ZYPCommonFunc.hasValue(rScrnMsg.basePrcDealAmt_R) //
                    && BigDecimal.ZERO.compareTo(rScrnMsg.basePrcDealAmt_R.getValue()) != 0) {
                return false;
            }
            return true;
        }

        if (ZYPCommonFunc.hasValue(rScrnMsg.prcMtrPkgPk_R)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(rScrnMsg.prcTierSvcOfferCd_R)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(rScrnMsg.xxTotPrcAmt_PR) //
                && BigDecimal.ZERO.compareTo(rScrnMsg.xxTotPrcAmt_PR.getValue()) != 0) {
            return false;
        }
        return true;
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @param ixZ int
     * @return index of Price detail.
     */
    public static int getPrcDtlIndex(NSAL1330BMsg scrnMsg, int ixZ) {
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
     * @param scrnMsg NSAL1330BMsg
     * @param ixU int
     * @return index of Price detail.
     */
    public static int getPrcDtlIndexConfig(NSAL1330BMsg scrnMsg, int ixU) {
        if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            return 0;
        }
//        BigDecimal mdlId = scrnMsg.U.no(ixU).mdlId_U.getValue();
//        BigDecimal cpoSvcConfigRefPk = scrnMsg.U.no(ixU).cpoSvcConfigRefPk_U.getValue();
//        if (!ZYPCommonFunc.hasValue(mdlId) && !ZYPCommonFunc.hasValue(cpoSvcConfigRefPk)) {
//            return 0;
//        }
//        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(scrnMsg.R.no(i).mdlId_R) //
//                    && mdlId.compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0 //
//                    && ZYPCommonFunc.hasValue(scrnMsg.R.no(i).cpoSvcConfigRefPk_R) //
//                    && cpoSvcConfigRefPk.compareTo(scrnMsg.R.no(i).cpoSvcConfigRefPk_R.getValue()) == 0) {
//                return i;
//            }
//        }
        BigDecimal mdlId = scrnMsg.U.no(ixU).mdlId_U.getValue();
        String dsOrdPosnNum = scrnMsg.U.no(ixU).dsOrdPosnNum_U.getValue();
        if (!ZYPCommonFunc.hasValue(mdlId) || !ZYPCommonFunc.hasValue(dsOrdPosnNum)) {
            return 0;
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.R.no(i).mdlId_R) //
                    && mdlId.compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0 //
                    && ZYPCommonFunc.hasValue(scrnMsg.R.no(i).dsOrdPosnNum_R) //
                    && dsOrdPosnNum.compareTo(scrnMsg.R.no(i).dsOrdPosnNum_R.getValue()) == 0) {
                return i;
            }
        }
        return 0;
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @param popUpEventNm String
     * @param prcCtxTp String
     * @param prcCatgNm String
     * @return Object[]
     */
    public static Object[] getNWAL1760Prm(NSAL1330BMsg scrnMsg, String popUpEventNm, String prcCtxTp, String prcCatgNm) {
        // set the transition screen name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, popUpEventNm);

        int ixP = 1;
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, prcCtxTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, scrnMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, scrnMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, scrnMsg.sellToCustCd);

        Object[] param = new Object[NWAL1760_PRM_NUM];
        ixP = 0;
        param[ixP] = scrnMsg.prcBaseDt; // 0
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 1
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 2
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 3
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 4
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 5
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 6
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 7
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 8
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 9
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 10
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 11
        ixP++;
        param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 12

        if (ZYPCommonFunc.hasValue(prcCatgNm)) {
            ixP++;
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP).xxPopPrm_P, prcCatgNm);
            param[ixP] = scrnMsg.P.no(ixP).xxPopPrm_P; // 13
        }

        return param;
    }

    /**
     * @param cpoOrdNum String
     * @param mdseTpCtxTpList String
     * @param cpoSvcLineNum BigDecimal
     * @return query string.
     */
    public static String getCoveredItemQueryStr(String cpoOrdNum, List<String> mdseTpCtxTpList, BigDecimal cpoSvcLineNum) {
        StringBuilder sbSelect = new StringBuilder();
        sbSelect.append("SELECT")//
                // START 2018/10/10 K.Kojima [QC#28715,MOD]
                // .append("   CD.DPLY_LINE_NUM")//
                .append("   CONCAT (CD.DS_ORD_POSN_NUM, '.') || CONCAT (CD.DS_CPO_LINE_NUM, DECODE (CD.DS_CPO_LINE_SUB_NUM, NULL, '', '.')) || CD.DS_CPO_LINE_SUB_NUM AS DPLY_LINE_NUM")//
                // END 2018/10/10 K.Kojima [QC#28715,MOD]
                .append("   , CD.DS_ORD_POSN_NUM")//
                .append("   , CD.CPO_DTL_LINE_NUM")//
                .append("   , CD.CPO_DTL_LINE_SUB_NUM")//
                // START 2018/10/10 K.Kojima [QC#28715,MOD]
                // .append("   , MDSE.MDSE_CD")//
                .append("   , NVL(OTM.ORD_TAKE_MDSE_CD, MDSE.MDSE_CD) AS MDSE_CD")//
                // END 2018/10/10 K.Kojima [QC#28715,MOD]
                .append("   , MDSE.MDSE_DESC_SHORT_TXT")//
                .append("   , CD.GLBL_CMPY_CD")//
                .append("   , CD.EZCANCELFLAG")//
                .append("   FROM")//
                // START 2018/10/10 K.Kojima [QC#28715,MOD]
                // .append("           CPO_DTL_V      CD")//
                .append("             CPO_DTL         CD")//
                .append("           , ORD_TAKE_MDSE   OTM")//
                // END 2018/10/10 K.Kojima [QC#28715,MOD]
                .append("           , ALL_MDSE_V      MDSE")//
                .append("   WHERE")//
                .append("           CD.GLBL_CMPY_CD     = MDSE.GLBL_CMPY_CD")//
                .append("           AND CD.CPO_ORD_NUM  = '")//
                .append(cpoOrdNum)//
                .append("'")//
                .append("   AND CD.MDSE_CD                = MDSE.MDSE_CD")//
                .append("   AND CD.EZCANCELFLAG           = MDSE.EZCANCELFLAG")//
                // START 2018/10/10 K.Kojima [QC#28715,ADD]
                .append("   AND CD.GLBL_CMPY_CD           = OTM.GLBL_CMPY_CD(+)")//
                .append("   AND SUBSTR(CD.MDSE_CD,1,8)    = OTM.ORD_TAKE_MDSE_CD(+)")//
                .append("   AND CD.EZCANCELFLAG           = OTM.EZCANCELFLAG(+)")//
                // END 2018/10/10 K.Kojima [QC#28715,ADD]
                .append("   AND MDSE.INSTL_BASE_CTRL_FLG  = 'Y'"); //
        if (mdseTpCtxTpList != null && mdseTpCtxTpList.size() > 0) {
            // Mod Start 2018/11/16 QC#29260
//            sbSelect.append("   AND NOT EXISTS (")//
//                    .append("       SELECT")//
//                    .append("                 1")//
//                    .append("           FROM")//
//                    .append("               MDSE_TP_VAL_SET MTVS")//
//                    .append("           WHERE")//
//                    .append("               MTVS.GLBL_CMPY_CD       = MDSE.GLBL_CMPY_CD")//
//                    .append("               AND MTVS.COA_MDSE_TP_CD = MDSE.COA_MDSE_TP_CD")//
//                    .append("               AND MTVS.MDSE_TP_CTX_TP_CD IN (")//
//                    .append(toStringListForSql(mdseTpCtxTpList)).append(")")//
//                    .append("               AND MTVS.EZCANCELFLAG = '0'")//
//                    .append("   )"); //
            sbSelect.append("   AND CD.BASE_CMPT_FLG          = 'N' ");
            // Mod End 2018/11/16 QC#29260
        }

        sbSelect.append("   AND EXISTS (")//
                .append("       SELECT")//
                .append("               1")//
                .append("           FROM")//
                .append("               DS_CONTR_DTL  DCD")//
                .append("           WHERE")//
                .append("               DCD.GLBL_CMPY_CD         = CD.GLBL_CMPY_CD")//
                .append("           AND DCD.CPO_ORD_NUM          = CD.CPO_ORD_NUM")//
                .append("           AND (")//
                .append("               (")//
                .append("                       DCD.CPO_DTL_LINE_NUM     = CD.REF_CPO_DTL_LINE_NUM")//
                .append("                   AND DCD.CPO_DTL_LINE_SUB_NUM = CD.REF_CPO_DTL_LINE_SUB_NUM")//
                .append("               )")//
                .append("               OR (")//
                .append("                       DCD.CPO_DTL_LINE_NUM     = CD.CPO_DTL_LINE_NUM")//
                .append("           AND DCD.CPO_DTL_LINE_SUB_NUM = CD.CPO_DTL_LINE_SUB_NUM")//
                .append("               )")//
                .append("           )")//
                .append("           AND DCD.SHELL_LINE_NUM       = ")   // 2017/06/13 [QC#18941, ADD]
                .append(cpoSvcLineNum)                                  // 2017/06/13 [QC#18941, ADD]
                .append("           AND DCD.EZCANCELFLAG         = '0'")//
                .append("   )")//

                .append("   AND NOT EXISTS (")//
                .append("       SELECT")//
                .append("               1")//
                .append("           FROM")//
                .append("               SHPG_PLN SP")//
                .append("           WHERE")//
                .append("               SP.GLBL_CMPY_CD = CD.GLBL_CMPY_CD")//
                .append("               AND SP.TRX_HDR_NUM = CD.CPO_ORD_NUM")//
                .append("               AND SP.TRX_LINE_NUM = CD.REF_CPO_DTL_LINE_NUM")//
                .append("               AND SP.TRX_LINE_SUB_NUM = CD.REF_CPO_DTL_LINE_SUB_NUM")//
                .append("               AND SP.SHPG_STS_CD >= '")//
                .append(SHPG_STS.ARRIVED)//
                .append("'")//
                .append("               AND SP.EZCANCELFLAG = '0'")//
                .append("   )");
        return sbSelect.toString();
    }

    private static String toStringListForSql(List<String> strList) {
        if (strList.size() == 0) {
            return SINGLE_QUOTE + SINGLE_QUOTE;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strList) {
            sb.append(COMMA);
            sb.append(SINGLE_QUOTE).append(str).append(SINGLE_QUOTE);
        }
        return sb.toString().substring(1);
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @param index int
     * @param scrnNm Screen Name
     * @return Object[]
     */
    public static Object[] getCoveredItemPopUpPrm(NSAL1330BMsg scrnMsg, int index, String scrnNm, String mdseCd) {
        scrnMsg.P.clear();

        String cpoOrdNum = scrnMsg.xxScrItem50Txt.getValue();
        List<String> mdseTpCtxTpList = null;
        if (POP_UP_RENTAL_EQUIP_COVERED_ITEM.equals(scrnNm)) {
        } else {
            mdseTpCtxTpList = Arrays.asList("CPO_SVC_CONFIG_ITEMS");
        }
        BigDecimal cpoSvcLineNum = scrnMsg.shellLineNum.getValue();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(index));

        // make parameterList
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            mdseCd = "%";
        }
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Covered Item", "MDSE_CD", mdseCd, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Item Description", "MDSE_DESC_SHORT_TXT", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);

        List<Object> colList = new ArrayList<Object>();
        Object[] colObj1 = {"Line#", "DPLY_LINE_NUM", new BigDecimal("20"), ZYPConstant.FLG_OFF_N };
        Object[] colObj2 = {"Covered Item", "MDSE_CD", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj3 = {"Item Description", "MDSE_DESC_SHORT_TXT", new BigDecimal("30"), ZYPConstant.FLG_ON_Y };
        Object[] colObj4 = {"Line#", "CPO_DTL_LINE_NUM", BigDecimal.ZERO, ZYPConstant.FLG_OFF_N };
        Object[] colObj5 = {"Sub Line#", "CPO_DTL_LINE_SUB_NUM", BigDecimal.ZERO, ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        colList.add(colObj5);

        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"TO_NUMBER(DS_ORD_POSN_NUM)", "ASC" };
        Object[] sortObj2 = {"CPO_DTL_LINE_NUM", "ASC" };
        Object[] sortObj3 = {"CPO_DTL_LINE_SUB_NUM", "ASC" };
        sortList.add(sortObj1);
        sortList.add(sortObj2);
        sortList.add(sortObj3);

        // set the transition screen name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnNm);

        Object[] params = new Object[NWAL1130_PRM_NUM];

        String queryStr = NSAL1330CommonLogic.getCoveredItemQueryStr(cpoOrdNum, mdseTpCtxTpList, cpoSvcLineNum);

        int ixP = 0;
        // 0 : Lv1 : Suffix
        params[ixP++] = "P";
        // 1 : Lv1 : Window Title
        params[ixP++] = scrnNm;
        // 2 : Lv1 : Select Table Name
        params[ixP++] = queryStr;
        // 3 : Lv1 : Where List
        params[ixP++] = whereList;
        // 4 : Lv1 : Column List
        params[ixP++] = colList;
        // 5 : Lv1 : Sort Condition List
        params[ixP++] = sortList;
        // 6 : Output
        params[ixP++] = scrnMsg.P;
        return params;
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @param index int
     * @return Object[]
     */
    public static Object[] getAddlChrgItemPopUpPrm(NSAL1330BMsg scrnMsg, int index) {
        scrnMsg.P.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(index));

        // make parameterList
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Shell Item", "MDSE_CD", "%", ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Description", "MDSE_DESC_SHORT_TXT", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);

        List<Object> colList = new ArrayList<Object>();
        Object[] colObj1 = {"Additional Charge Item", "MDSE_CD", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Charge Description", "MDSE_DESC_SHORT_TXT", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);

        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"MDSE_CD", "ASC" };
        sortList.add(sortObj1);

        // set the transition screen name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POP_UP_ADD_CHARGE_ITEM);

        Object[] params = new Object[NWAL1130_PRM_NUM];

        // START 2018/10/10 K.Kojima [QC#28715,MOD]
        // String selectQuery = NSAL1330CommonLogic.getAddlChrgItemQuery();
        String selectQuery = NSAL1330CommonLogic.getAddlChrgItemQuery(scrnMsg);
        // END 2018/10/10 K.Kojima [QC#28715,MOD]

        int ixP = 0;
        // 0 : Lv1 : Suffix
        params[ixP++] = "P";
        // 1 : Lv1 : Window Title
        params[ixP++] = POP_UP_ADD_CHARGE_ITEM;
        // 2 : Lv1 : Select Table Name
        params[ixP++] = selectQuery;
        // 3 : Lv1 : Where List
        params[ixP++] = whereList;
        // 4 : Lv1 : Column List
        params[ixP++] = colList;
        // 5 : Lv1 : Sort Condition List
        params[ixP++] = sortList;
        // 6 : Output
        params[ixP++] = scrnMsg.P;
        return params;
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @param index int
     * @return Object[]
     */
    public static Object[] getCoveredUnitPopUpPrm(NSAL1330BMsg scrnMsg, int index) {
        String cpoOrdNum = scrnMsg.xxScrItem50Txt.getValue();
        BigDecimal cpoSvcLineNum = scrnMsg.shellLineNum.getValue();
        String mdseTpCtxTp = CPO_SVC_CONFIG_ITEMS;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(index));

        // make parameterList
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Covered Item", "MDSE_CD", "%", ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Item Description", "MDSE_DESC_SHORT_TXT", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);

        List<Object> colList = new ArrayList<Object>();
        Object[] colObj1 = {"Line#", "DPLY_LINE_NUM", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj2 = {"Covered Item", "MDSE_CD", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj3 = {"Item Description", "MDSE_DESC_SHORT_TXT", new BigDecimal("30"), ZYPConstant.FLG_ON_Y };
        Object[] colObj4 = {"Line#", "CPO_DTL_LINE_NUM", BigDecimal.ZERO, ZYPConstant.FLG_OFF_N };
        Object[] colObj5 = {"Sub Line#", "CPO_DTL_LINE_SUB_NUM", BigDecimal.ZERO, ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        colList.add(colObj5);

        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"CPO_DTL_LINE_NUM", "ASC" };
        Object[] sortObj2 = {"CPO_DTL_LINE_SUB_NUM", "ASC" };
        sortList.add(sortObj1);
        sortList.add(sortObj2);

        // set the transition screen name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POP_UP_COVERED_UNIT);

        Object[] params = new Object[NWAL1130_PRM_NUM];

        String queryStr = getCoveredUnitQueryStr(cpoOrdNum, cpoSvcLineNum, mdseTpCtxTp);

        int ixP = 0;
        // 0 : Lv1 : Suffix
        params[ixP++] = "P";
        // 1 : Lv1 : Window Title
        params[ixP++] = POP_UP_COVERED_UNIT;
        // 2 : Lv1 : Select Table Name
        params[ixP++] = queryStr;
        // 3 : Lv1 : Where List
        params[ixP++] = whereList;
        // 4 : Lv1 : Column List
        params[ixP++] = colList;
        // 5 : Lv1 : Sort Condition List
        params[ixP++] = sortList;
        // 6 : Output
        params[ixP++] = scrnMsg.P;
        return params;
    }

    /**
     * @param cpoOrdNum String
     * @param mdseTpCtxTp String
     * @param cpoSvcLineNum BigDecimal
     * @return query string
     */
    private static String getCoveredUnitQueryStr(String cpoOrdNum, BigDecimal cpoSvcLineNum, String mdseTpCtxTp) {
        StringBuilder sbSelect = new StringBuilder();
        sbSelect.append("SELECT")//
                // START 2018/10/10 K.Kojima [QC#28715,MOD]
                // .append("   CD.DPLY_LINE_NUM")//
                .append("   CONCAT (CD.DS_ORD_POSN_NUM, '.') || CONCAT (CD.DS_CPO_LINE_NUM, DECODE (CD.DS_CPO_LINE_SUB_NUM, NULL, '', '.')) || CD.DS_CPO_LINE_SUB_NUM AS DPLY_LINE_NUM")//
                // END 2018/10/10 K.Kojima [QC#28715,MOD]
                .append("   , CD.CPO_DTL_LINE_NUM")//
                .append("   , CD.CPO_DTL_LINE_SUB_NUM")//
                .append("   , MTVS.MDSE_TP_CTX_TP_CD")//
                // START 2018/10/10 K.Kojima [QC#28715,MOD]
                // .append("   , MDSE.MDSE_CD")//
                .append("   , NVL(OTM.ORD_TAKE_MDSE_CD, MDSE.MDSE_CD) AS MDSE_CD")//
                // END 2018/10/10 K.Kojima [QC#28715,MOD]
                .append("   , MDSE.MDSE_DESC_SHORT_TXT")//
                .append("   , CD.GLBL_CMPY_CD")//
                .append("   , CD.EZCANCELFLAG")//
                .append("   FROM")//
                // START 2018/10/10 K.Kojima [QC#28715,MOD]
                // .append("           CPO_DTL_V      CD")//
                .append("             CPO_DTL         CD")//
                .append("           , ORD_TAKE_MDSE   OTM")//
                // END 2018/10/10 K.Kojima [QC#28715,MOD]
                .append("           , MDSE_TP_VAL_SET MTVS")//
                .append("           , ALL_MDSE_V      MDSE")//
                .append("   WHERE")//
                .append("           CD.GLBL_CMPY_CD     = MDSE.GLBL_CMPY_CD")//
                .append("           AND CD.CPO_ORD_NUM  = '")//
                .append(cpoOrdNum)//
                .append("'")//
                .append("   AND CD.MDSE_CD                  = MDSE.MDSE_CD")//
                .append("   AND CD.EZCANCELFLAG             = MDSE.EZCANCELFLAG")//
                .append("   AND MDSE.INSTL_BASE_CTRL_FLG    = 'Y'")//
                .append("   AND MTVS.GLBL_CMPY_CD           = MDSE.GLBL_CMPY_CD")//
                .append("   AND MTVS.COA_MDSE_TP_CD         = MDSE.COA_MDSE_TP_CD")//
                .append("   AND MTVS.EZCANCELFLAG           = MDSE.EZCANCELFLAG")//
                // START 2018/10/10 K.Kojima [QC#28715,ADD]
                .append("   AND CD.GLBL_CMPY_CD           = OTM.GLBL_CMPY_CD(+)")//
                .append("   AND SUBSTR(CD.MDSE_CD,1,8)    = OTM.ORD_TAKE_MDSE_CD(+)")//
                .append("   AND CD.EZCANCELFLAG           = OTM.EZCANCELFLAG(+)")//
                // END 2018/10/10 K.Kojima [QC#28715,ADD]
                .append("   AND MTVS.MDSE_TP_CTX_TP_CD      = '")//
                .append(mdseTpCtxTp).append("'")//
                .append("   AND EXISTS (")//
                .append("       SELECT")//
                .append("               DCD.GLBL_CMPY_CD")//
                .append("           FROM")//
                .append("               DS_CONTR_DTL        DCD")//
                .append("           WHERE")//
                .append("               DCD.GLBL_CMPY_CD             = CD.GLBL_CMPY_CD")//
                .append("               AND DCD.CPO_ORD_NUM          = CD.CPO_ORD_NUM")//
                .append("               AND DCD.CPO_DTL_LINE_NUM     = CD.CPO_DTL_LINE_NUM")//
                .append("               AND DCD.CPO_DTL_LINE_SUB_NUM = CD.CPO_DTL_LINE_SUB_NUM")//
                .append("               AND DCD.SHELL_LINE_NUM       = ")   // 2017/06/13 [QC#18941, ADD]
                .append(cpoSvcLineNum)                                      // 2017/06/13 [QC#18941, ADD]
                .append("               AND DCD.EZCANCELFLAG         = '0'")//
                .append("   )");
        return sbSelect.toString();
    }

    /**
     * <pre>
     * @param scrnMsg   NSAL1330BMsg
     * @param ixA       selected index(A)
     * </pre>
     */
    public static void clearSvcPrcListInfo(NSAL1330BMsg scrnMsg, int ixA) {
        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(ixA);
        aScrnMsg.prcCatgCd_A.clear();
        aScrnMsg.prcMtrPkgPk_A.clear();
        aScrnMsg.prcMtrPkgPk_KP.clear();
        aScrnMsg.prcMtrPkgNm_VW.clear();
        aScrnMsg.prcTierSvcOfferCd_A.clear();
        aScrnMsg.xxTotPrcAmt_PB.clear();
        aScrnMsg.xxTotPrcAmt_EB.clear();
        aScrnMsg.xxTotPrcAmt_TB.clear();
        scrnMsg.xxTotAmt.clear();

        BigDecimal mdlId = aScrnMsg.mdlId_A.getValue();
        List<Integer> delList = new ArrayList<Integer>();
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            if (isFleet(scrnMsg) //
                    || (ZYPCommonFunc.hasValue(mdlId) && mdlId.compareTo(scrnMsg.Z.no(i).mdlId_Z.getValue()) == 0)) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(scrnMsg.Z, delList);

        delList.clear();
        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            if (isFleet(scrnMsg) //
                    || (ZYPCommonFunc.hasValue(mdlId) && mdlId.compareTo(scrnMsg.X.no(i).mdlId_X.getValue()) == 0)) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(scrnMsg.X, delList);
    }

    /**
     * clearSvcPrcListInfoForConfig
     * @param scrnMsg NSAL1330BMsg
     * @param ixR int
     */
    public static void clearSvcPrcListInfoForConfig(NSAL1330BMsg scrnMsg, int ixR) {
        NSAL1330_RBMsg rScrnMsg = scrnMsg.R.no(ixR);
        rScrnMsg.prcCatgCd_R.clear();
        rScrnMsg.prcMtrPkgPk_R.clear();
        rScrnMsg.prcMtrPkgPk_RL.clear();
        rScrnMsg.prcMtrPkgNm_RL.clear();
        rScrnMsg.prcTierSvcOfferCd_R.clear();
        rScrnMsg.xxTotPrcAmt_PR.clear();
        rScrnMsg.xxTotPrcAmt_ER.clear();
        rScrnMsg.xxTotPrcAmt_TR.clear();
        scrnMsg.xxTotAmt.clear();

//        BigDecimal mdlId = rScrnMsg.mdlId_R.getValue();
//        BigDecimal cpoSvcConfigRefPk = rScrnMsg.cpoSvcConfigRefPk_R.getValue();
//        List<Integer> delList = new ArrayList<Integer>();
//        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
//            if ((ZYPCommonFunc.hasValue(mdlId) //
//                    && mdlId.compareTo(scrnMsg.U.no(i).mdlId_U.getValue()) == 0) //
//                    && (ZYPCommonFunc.hasValue(cpoSvcConfigRefPk) //
//                    && cpoSvcConfigRefPk.compareTo(scrnMsg.U.no(i).cpoSvcConfigRefPk_U.getValue()) == 0)) {
//                delList.add(i);
//            }
//        }
        BigDecimal mdlId = rScrnMsg.mdlId_R.getValue();
        String dsOrdPosnNum = rScrnMsg.dsOrdPosnNum_R.getValue();
        List<Integer> delList = new ArrayList<Integer>();
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            if ((ZYPCommonFunc.hasValue(mdlId) //
                    && mdlId.compareTo(scrnMsg.U.no(i).mdlId_U.getValue()) == 0) //
                    && (ZYPCommonFunc.hasValue(dsOrdPosnNum) //
                    && dsOrdPosnNum.compareTo(scrnMsg.U.no(i).dsOrdPosnNum_U.getValue()) == 0)) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(scrnMsg.U, delList);

        delList.clear();
//        for (int i = 0; i < scrnMsg.V.getValidCount(); i++) {
//            if ((ZYPCommonFunc.hasValue(mdlId) && mdlId.compareTo(scrnMsg.V.no(i).mdlId_V.getValue()) == 0) && (ZYPCommonFunc.hasValue(cpoSvcConfigRefPk) && cpoSvcConfigRefPk.compareTo(scrnMsg.V.no(i).cpoSvcConfigRefPk_V.getValue()) == 0)) {
//                delList.add(i);
//            }
//        }
        for (int i = 0; i < scrnMsg.V.getValidCount(); i++) {
            if ((ZYPCommonFunc.hasValue(mdlId) && mdlId.compareTo(scrnMsg.V.no(i).mdlId_V.getValue()) == 0) && (ZYPCommonFunc.hasValue(dsOrdPosnNum) && dsOrdPosnNum.compareTo(scrnMsg.V.no(i).dsOrdPosnNum_V.getValue()) == 0)) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(scrnMsg.V, delList);
    }

    /**
     * <pre>
     * 20160308 add
     * @param scrnMsg NSAL1330BMsg
     * @param ixZ ixZ
     * </pre>
     */
    public static void setCopyInclPrcQty(NSAL1330BMsg scrnMsg, int ixZ) {
        BigDecimal minCopyVolCnt = scrnMsg.minCopyVolCnt_P.getValue();
        BigDecimal maxCopyVolCnt = scrnMsg.maxCopyVolCnt_P.getValue();

        if (!ZYPCommonFunc.hasValue(minCopyVolCnt)//
                || !ZYPCommonFunc.hasValue(maxCopyVolCnt)) {
            scrnMsg.Z.no(ixZ).mlyCopyInclPrcQty_Z.setValue(BigDecimal.ZERO);
            return;
        }

        if (minCopyVolCnt.compareTo(maxCopyVolCnt) == 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(ixZ).mlyCopyInclPrcQty_Z, minCopyVolCnt);
        } else {
            scrnMsg.Z.no(ixZ).mlyCopyInclPrcQty_Z.setValue(BigDecimal.ZERO);
        }

    }

    /**
     * <pre>
     * @param scrnMsg NSAL1330BMsg
     * @param ixU ixU
     * </pre>
     */
    public static void setCopyInclPrcQtyConfig(NSAL1330BMsg scrnMsg, int ixU) {
        BigDecimal minCopyVolCnt = scrnMsg.minCopyVolCnt_P.getValue();
        BigDecimal maxCopyVolCnt = scrnMsg.maxCopyVolCnt_P.getValue();

        if (!ZYPCommonFunc.hasValue(minCopyVolCnt)//
                || !ZYPCommonFunc.hasValue(maxCopyVolCnt)) {
            scrnMsg.U.no(ixU).mlyCopyInclPrcQty_U.setValue(BigDecimal.ZERO);
            return;
        }

        if (minCopyVolCnt.compareTo(maxCopyVolCnt) == 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.U.no(ixU).mlyCopyInclPrcQty_U, minCopyVolCnt);
        } else {
            scrnMsg.U.no(ixU).mlyCopyInclPrcQty_U.setValue(BigDecimal.ZERO);
        }

    }

    /**
     * deriveSvcPrcSelectNum
     * @param scrnMsg NSAL1330BMsg
     */
    public static void deriveSvcPrcSelectNum(NSAL1330BMsg scrnMsg) {
        int ixZ = scrnMsg.xxNum_Z.getValueInt();
        scrnMsg.xxNum_A.setValue(BigDecimal.ZERO);

        if (isFleet(scrnMsg)) {
            return;
        }
        BigDecimal mdlId = scrnMsg.Z.no(ixZ).mdlId_Z.getValue();
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return;
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (mdlId.compareTo(scrnMsg.A.no(i).mdlId_A.getValue()) == 0) {
                scrnMsg.xxNum_A.setValue(new BigDecimal(i));
                return;
            }
        }
    }

    /**
     * deriveSvcPrcSelectNum
     * @param scrnMsg NSAL1330BMsg
     */
    public static void deriveSvcPrcSelectNumConfig(NSAL1330BMsg scrnMsg) {
        int ixU = scrnMsg.xxNum_Z.getValueInt();
        scrnMsg.xxNum_A.setValue(BigDecimal.ZERO);

        if (isFleet(scrnMsg)) {
            return;
        }
//        BigDecimal mdlId = scrnMsg.U.no(ixU).mdlId_U.getValue();
//        BigDecimal cpoSvcConfigRefPk = scrnMsg.U.no(ixU).cpoSvcConfigRefPk_U.getValue();
//        if (!ZYPCommonFunc.hasValue(mdlId)) {
//            return;
//        }
//
//        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
//            if (mdlId.compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0 //
//                    && cpoSvcConfigRefPk.compareTo(scrnMsg.R.no(i).cpoSvcConfigRefPk_R.getValue()) == 0) {
//                scrnMsg.xxNum_A.setValue(new BigDecimal(i));
//                return;
//            }
//        }
        BigDecimal mdlId = scrnMsg.U.no(ixU).mdlId_U.getValue();
        String dsOrdPosnNum = scrnMsg.U.no(ixU).dsOrdPosnNum_U.getValue();
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return;
        }

        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (mdlId.compareTo(scrnMsg.R.no(i).mdlId_R.getValue()) == 0 //
                    && dsOrdPosnNum.compareTo(scrnMsg.R.no(i).dsOrdPosnNum_R.getValue()) == 0) {
                scrnMsg.xxNum_A.setValue(new BigDecimal(i));
                return;
            }
        }

    }

    /**
     * setSupplyButton
     * @param handler 
     * NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc_forConfigPricing
     * @param rScrnMsg NSAL1330_RBMsg
     * @param index int
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setSupplyButton(//
            NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc_forConfigPricing handler, NSAL1330_RBMsg rScrnMsg, int index, NSAL1330BMsg scrnMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                && PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(rScrnMsg.prcListTpCd_R.getValue()) //
                && ZYPCommonFunc.hasValue(rScrnMsg.prcMtrPkgPk_R)) {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, true);
        } else {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT, index, false);
        }
    }

    /**
     * setConfigPricingAreaCtrl
     * @param scrnMsg NSAL1330BMsg
     * @param isDispaly boolean
     */
    public static void setConfigPricingAreaCtrl(NSAL1330BMsg scrnMsg) {
        // START 2017/10/13 [QC#20059-1, MOD]
        boolean isDisplay = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // scrnMsg.A.no(i).xxSmryLineFlg_A.setValue(ZYPConstant.FLG_OFF_N);
            if (isConfigLevelPriceSetting(scrnMsg.A.no(i))) {
                isDisplay = true;
                scrnMsg.A.no(i).xxSmryLineFlg_A.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                isDisplay = false;
                scrnMsg.A.no(i).xxSmryLineFlg_A.setValue(ZYPConstant.FLG_OFF_N);
            }
            setConfigPricingAreaCtrlByConfig(scrnMsg, i, isDisplay);
        }
        // END   2017/10/13 [QC#20059-1, MOD]
 
        String bgColor = ZYPCodeDataUtil.getVarCharConstValue("NSAL1330_CONFIG_PRC_BGCOLOR", scrnMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(bgColor)) {
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                EZDGUIAttribute guiAttr = new EZDGUIAttribute(SCREEN_ID, "configSvcPrc" + i);
                guiAttr.setStyleAttribute("BACKGROUND-COLOR", bgColor);
                scrnMsg.addGUIAttribute(guiAttr);
            }
        }
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     * @param ixA index of A Table.
     * @param isDisplay if config pricing area is display then true.
     */
    public static void setConfigPricingAreaCtrlByConfig(NSAL1330BMsg scrnMsg, int ixA, boolean isDisplay) {
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
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setCustomerCtrlConfig(NSAL1330BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            scrnMsg.U.no(i).xxGenlFldAreaTxt_U.setInputProtected(true);
        }
    }

    /**
     * isConfigPricingAllCollapsee
     * @param scrnMsg NSAL1330BMsg
     * @return if config pricing is all collapsed then return true.
     */
    public static boolean isConfigPricingAllCollapsed(NSAL1330BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(i).xxSmryLineFlg_A.getValue())) {
                return false;
            }
        }
        return true;
    }

    public static Object[] getParamNMAL6760ForBillTo(NSAL1330BMsg scrnMsg, boolean isConfig) {
        // clear
        scrnMsg.P.clear();
        int ixSelRow = -1;

        if (isConfig) {
            ixSelRow = scrnMsg.xxNum_A.getValueInt();
        } else {
            ixSelRow = scrnMsg.xxNum_Z.getValueInt();
        }

        int ixP = 1;
        scrnMsg.P.no(ixP++).xxPopPrm_P.setValue(STATUS_CD_ACTIVE);
        scrnMsg.P.no(ixP++).xxPopPrm_P.setValue(DISP_HRCH_ACCT_CD_BILL);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        if (isConfig) {
            paramList.add(scrnMsg.R.no(ixSelRow).billToCustCd_R); // Bill To Acct Code
            paramList.add(scrnMsg.R.no(ixSelRow).dsAcctNm_R);  // 2017/03/29 S21_NA#18171 Mod
        } else {
            paramList.add(scrnMsg.U.no(ixSelRow).billToCustCd_U); // Bill To Acct Code
            paramList.add(scrnMsg.U.no(ixSelRow).dsAcctNm_U); // 2017/03/29 S21_NA#18171 Mod
        }

        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(IX_POP_PRM_FIRST_LINE_ADDR).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(IX_POP_PRM_CTY_ADDR).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(IX_POP_PRM_ST_CD).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(IX_POP_PRM_POST_CD).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(1).xxPopPrm_P); // Active Only
        paramList.add(scrnMsg.P.no(2).xxPopPrm_P); // Bill To's Only
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used

        if (isConfig) {
            paramList.add(scrnMsg.R.no(ixSelRow).billToLocNum_R);
        } else {
            paramList.add(scrnMsg.U.no(ixSelRow).billToLocNum_U);
        }

        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(IX_POP_PRM_SCD_LINE_ADDR).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(IX_POP_PRM_THIRD_LINE_ADDR).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(IX_POP_PRM_FRTH_LINE_ADDR).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP).xxPopPrm_P); // no used

        return paramList.toArray(new EZDBItem[paramList.size()]);
    }

    public static void setButtonControlForConfigPricing(EZDCommonHandler handler, NSAL1330BMsg scrnMsg) {
        boolean isAggregate = DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue());

        for (int ixR = 0; ixR < scrnMsg.R.getValidCount(); ixR++) {
            handler.setButtonEnabled(BUTTON_ADD_ACTIVE_METER, ixR, isAggregate);
        }
        for (int ixU = 0; ixU < scrnMsg.U.length(); ixU++) {
            handler.setButtonEnabled(BUTTON_DEL_ACTIVE_METER, ixU, isAggregate);
        }
    }

    // START 2017/10/13 [QC#20059-1, ADD]
    public static boolean isConfigLevelPriceSetting(NSAL1330_ABMsg aScrnMsg) {
        if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.xxSelFlg_A.getValue())) {
            return true;
        }
        return false;
    }
    // END   2017/10/13 [QC#20059-1, ADD]

    // START 2017/10/05 [QC#20059, ADD]
    public static boolean isConfigLevelPriceSetting(NSAL1330BMsg scrnMsg, BigDecimal mdlId) {
        for (int ixA = 0; ixA < scrnMsg.A.getValidCount(); ixA++) {
            NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(ixA);
            if (!isSameBigDecimal(mdlId, aScrnMsg.mdlId_A.getValue())) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(aScrnMsg.xxSelFlg_A.getValue())) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * @param scrnMsg NSAL1330BMsg
     */
    public static void checkForToggleServicePriceSetting(NSAL1330BMsg scrnMsg, int idxA) {

        NSAL1330_ABMsg aScrnMsg = scrnMsg.A.no(idxA);
        BigDecimal mdlId_A = aScrnMsg.mdlId_A.getValue();
        if (ZYPCommonFunc.hasValue(aScrnMsg.xxTotPrcAmt_PB) //
                && BigDecimal.ZERO.compareTo(aScrnMsg.xxTotPrcAmt_PB.getValue()) > 0) {
            aScrnMsg.xxTotPrcAmt_PB.setErrorInfo(1, NSAM0675E, new String[] {"Unit Periodic Base" });
            scrnMsg.addCheckItem(scrnMsg.A.no(idxA).xxTotPrcAmt_PB);
        }
        NSAL1330CommonLogic.checkMdlSvcForSave(aScrnMsg, scrnMsg.usgBllgCycleCd.getValue(), scrnMsg);


        BigDecimal multRate = ZYPCodeDataUtil.getNumConstValue(NSAL0320_MTR_MULT_RATE_FCT_NUM, scrnMsg.glblCmpyCd.getValue());
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            NSAL1330_ZBMsg zScrnMsg = scrnMsg.Z.no(i);
            if (!isSameBigDecimal(mdlId_A, zScrnMsg.mdlId_Z.getValue())) {
                continue;
            }
            BigDecimal mdlId = zScrnMsg.mdlId_Z.getValue();
            if (hasSvcPkg(scrnMsg, mdlId) && ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                NSAL1330CommonLogic.checkUsgPrcForSave(zScrnMsg, scrnMsg, multRate);
            }
        }
    }

    public static void addCheckItemsForToggleServicePriceSetting(NSAL1330BMsg scrnMsg, int idxA) {
        scrnMsg.addCheckItem(scrnMsg.A.no(idxA).prcMtrPkgPk_A);
        scrnMsg.addCheckItem(scrnMsg.A.no(idxA).prcTierSvcOfferCd_A);

        int size = scrnMsg.Z.getValidCount();
        BigDecimal mdlId_A = scrnMsg.A.no(idxA).mdlId_A.getValue();
        for (int i = 0; i < size; i++) {
            if (!isSameBigDecimal(mdlId_A, scrnMsg.Z.no(i).mdlId_Z.getValue())) {
                continue;
            }

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
            // 2018/05/09 QC#25030 add start
            scrnMsg.addCheckItem(scrnMsg.Z.no(i).bllgFreeCopyCnt_Z);
            scrnMsg.addCheckItem(scrnMsg.Z.no(i).bllgMinCnt_Z);
            scrnMsg.addCheckItem(scrnMsg.Z.no(i).bllgMinAmtRate_Z);
            scrnMsg.addCheckItem(scrnMsg.Z.no(i).bllgRollOverRatio_Z);
            // 2018/05/09 QC#25030 add end
        }
    }
    // END   2017/10/05 [QC#20059, ADD]

    // START 2017/10/16 [QC#20001, ADD]
    /**
     * setSupplyButton
     * @param handler 
     * NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc
     * @param rScrnMsg NSAL1330_ABMsg
     * @param index int
     * @param scrnMsg NSAL1330BMsg
     */
    public static void setSupplyButton(//
            NSAL1330Scrn00_OnBlur_ServicePriceList_SvcPrc handler, NSAL1330_ABMsg aScrnMsg, int index, NSAL1330BMsg scrnMsg) {
        if (!DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue()) //
                && PRC_LIST_TP.EASYPACII_OPTIMIZEIT.equals(aScrnMsg.prcListTpCd_A.getValue()) //
                && ZYPCommonFunc.hasValue(aScrnMsg.prcMtrPkgPk_A)) {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT_MODEL, index, true);
        } else {
            handler.setButtonEnabled(BUTTON_SUPPLY_AGREEMENT_MODEL, index, false);
        }
    }
    // END   2017/10/16 [QC#20001, ADD]

    // START 2017/10/20 [QC#21656, ADD]
    private static int getCmnBtnActiveSts(NSAL1330BMsg scrnMsg) {
        int isActive = 0; // inactive

        // Model Service Pricing
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (isConfigLevelPriceSetting(scrnMsg.A.no(i))
                    || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
                continue;
            }
            if (!ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).scrEntAvalFlg_A.getValue())) {
                isActive = 1; // active
                break;
            }
        }

        // Config Service Pricing
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            if (!isConfigLevelPriceSetting(scrnMsg, scrnMsg.R.no(i).mdlId_R.getValue())
                    || ZYPConstant.FLG_ON_Y.equals(scrnMsg.addAsryFlg.getValue())) {
                continue;
            }
            if (!ZYPConstant.FLG_OFF_N.equals(scrnMsg.R.no(i).scrEntAvalFlg_R.getValue())) {
                isActive = 1; // active
                break;
            }
        }

        // Accessory Charge
        for (int i = 0; i < scrnMsg.J.getValidCount(); i++) {
            if (!ZYPConstant.FLG_OFF_N.equals(scrnMsg.J.no(i).scrEntAvalFlg_J.getValue())) {
                isActive = 1; // active
                break;
            }
        }

        return isActive;
    }
    // END   2017/10/20 [QC#21656, ADD]
    // START 2017/10/24 [QC#21556, ADD]
    /**
     * overrideProtectedForAddContr
     * 
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1330BMsg
     */
    public static void overrideProtectedForAddContr(EZDCommonHandler handler, NSAL1330BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsContrPk_AD)) {
            return;
        }

        // Fleet
        if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            // Service Pricing
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NSAL1330_ABMsg prcHeaderMsg = scrnMsg.A.no(i);
                boolean isProtect = true;
                prcHeaderMsg.xxSelFlg_A.setInputProtected(true);
                prcHeaderMsg.prcCatgNm_A.setInputProtected(isProtect);
                prcHeaderMsg.prcMtrPkgPk_A.setInputProtected(isProtect);

                if (!prcHeaderMsg.prcTierSvcOfferCd_AB.isInputProtected()) {
                    prcHeaderMsg.prcTierSvcOfferCd_A.setInputProtected(isProtect);
                }
                prcHeaderMsg.xxTotPrcAmt_PB.setInputProtected(isProtect);
                prcHeaderMsg.termMthAot_A.setInputProtected(true);
                setTierLinkCtrl(handler, scrnMsg, i);
                setSupplyButton(handler, prcHeaderMsg, i, scrnMsg);
            }

            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
                NSAL1330_ZBMsg pricingMsg = scrnMsg.Z.no(i);
                boolean isProtect = true;
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
                // 2018/05/09 QC#25030 add start
                pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(isProtect);
                pricingMsg.bllgMinCnt_Z.setInputProtected(isProtect);
                pricingMsg.bllgMinAmtRate_Z.setInputProtected(isProtect);
                pricingMsg.bllgRollOverRatio_Z.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add end
            }
        }
    
        // Aggregate
        if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue())) {
            // Service Pricing
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NSAL1330_ABMsg prcHeaderMsg = scrnMsg.A.no(i);
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue())
                        || isConfigLevelPriceSetting(scrnMsg, prcHeaderMsg.mdlId_A.getValue())) {
                    continue;
                }
                boolean isProtect = true;
                prcHeaderMsg.prcTierSvcOfferCd_A.setInputProtected(isProtect);
                setTierLinkCtrl(handler, scrnMsg, i);
            }
            for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
                NSAL1330_RBMsg prcHeaderMsg = scrnMsg.R.no(i);
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_R.getValue())
                        || !isConfigLevelPriceSetting(scrnMsg, prcHeaderMsg.mdlId_R.getValue())) {
                    continue;
                }
                boolean isProtect = true;
                prcHeaderMsg.prcTierSvcOfferCd_R.setInputProtected(isProtect);
                setTierLinkCtrlConfig(handler, scrnMsg, i);
            }

            // Service Pricing Detail
            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
                NSAL1330_ZBMsg pricingMsg = scrnMsg.Z.no(i);
                int ixA = getPrcDtlIndex(scrnMsg, i);
                NSAL1330_ABMsg prcHeaderMsg = scrnMsg.A.no(ixA);
                boolean isProtect = true;
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_A.getValue())
                        || isConfigLevelPriceSetting(scrnMsg, prcHeaderMsg.mdlId_A.getValue())) {
                    continue;
                }
                handler.setButtonEnabled(BUTTON_BAND_SEARCH, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM, i, !isProtect);

                pricingMsg.prcListBandDescTxt_Z.setInputProtected(isProtect);
                pricingMsg.prcBookMdseCd_Z.setInputProtected(isProtect);
                pricingMsg.contrMtrMultRate_Z.setInputProtected(isProtect);
                pricingMsg.prcSvcTierTpCd_Z.setInputProtected(isProtect);
                pricingMsg.xsMtrAmtRate_Z.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add start
                pricingMsg.bllgFreeCopyCnt_Z.setInputProtected(isProtect);
                pricingMsg.bllgMinCnt_Z.setInputProtected(isProtect);
                pricingMsg.bllgMinAmtRate_Z.setInputProtected(isProtect);
                pricingMsg.bllgRollOverRatio_Z.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add end
            }
            for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
                NSAL1330_UBMsg usgMsg = scrnMsg.U.no(i);
                int ixR = getPrcDtlIndexConfig(scrnMsg, i);
                NSAL1330_RBMsg prcHeaderMsg = scrnMsg.R.no(ixR);
                boolean isProtect = true;
                if (ZYPConstant.FLG_OFF_N.equals(prcHeaderMsg.scrEntAvalFlg_R.getValue())
                        || !isConfigLevelPriceSetting(scrnMsg, prcHeaderMsg.mdlId_R.getValue())) {
                    continue;
                }

                handler.setButtonEnabled(BUTTON_BAND_SEARCH_CONFIG, i, !isProtect);
                handler.setButtonEnabled(BUTTON_ITEM_SEARCH_PRICING_BOOK_ITEM_CONFIG, i, !isProtect);

                usgMsg.mtrLbDescTxt_UB.setInputProtected(true);
                usgMsg.mtrLbDescTxt_U.setInputProtected(true);

                usgMsg.dsOrdPosnNum_U.setInputProtected(isProtect);
                usgMsg.prcListBandDescTxt_U.setInputProtected(isProtect);
                usgMsg.prcBookMdseCd_U.setInputProtected(isProtect);
                usgMsg.contrMtrMultRate_U.setInputProtected(isProtect);
                usgMsg.prcSvcTierTpCd_U.setInputProtected(isProtect);
                usgMsg.xsMtrAmtRate_U.setInputProtected(isProtect);
                usgMsg.xxGenlFldAreaTxt_U.setInputProtected(true);
                // 2018/05/09 QC#25030 add start
                usgMsg.bllgFreeCopyCnt_U.setInputProtected(isProtect);
                usgMsg.bllgMinCnt_U.setInputProtected(isProtect);
                usgMsg.bllgMinAmtRate_U.setInputProtected(isProtect);
                usgMsg.bllgRollOverRatio_U.setInputProtected(isProtect);
                // 2018/05/09 QC#25030 add end
            }
        }
    }
    // END   2017/10/24 [QC#21556, ADD]

    // START 2019/05/29 K.Kitachi [QC#50567, ADD]
    private static void sortMsgArrayRU(NSAL1330BMsg scrnMsg) {
        if (DS_CONTR_CATG.FLEET.equals(scrnMsg.dsContrCatgCd.getValue())) {
            return;
        }
        List<NSAL1330_RBMsg> rBMsgList = new ArrayList<NSAL1330_RBMsg>();
        List<NSAL1330_UBMsg> uBMsgList = new ArrayList<NSAL1330_UBMsg>();
        for (int aIdx = 0; aIdx < scrnMsg.A.getValidCount(); aIdx++) {
            NSAL1330_ABMsg aBMsg = scrnMsg.A.no(aIdx);
            BigDecimal mdlId_A = aBMsg.mdlId_A.getValue();
            for (int rIdx = 0; rIdx < scrnMsg.R.getValidCount(); rIdx++) {
                NSAL1330_RBMsg rBMsg = scrnMsg.R.no(rIdx);
                BigDecimal mdlId_R = rBMsg.mdlId_R.getValue();
                String dsOrdPosnNum_R = rBMsg.dsOrdPosnNum_R.getValue();
                if (!ZYPCommonFunc.hasValue(mdlId_A) || !ZYPCommonFunc.hasValue(mdlId_R) || mdlId_A.compareTo(mdlId_R) != 0) {
                    continue;
                }
                NSAL1330_RBMsg copyRBMsg = new NSAL1330_RBMsg();
                EZDMsg.copy(rBMsg, null, copyRBMsg, null);
                rBMsgList.add(copyRBMsg);
                if (!ZYPCommonFunc.hasValue(scrnMsg.usgBllgCycleCd)) {
                    continue;
                }
                for (int uIdx = 0; uIdx < scrnMsg.U.getValidCount(); uIdx++) {
                    NSAL1330_UBMsg uBMsg = scrnMsg.U.no(uIdx);
                    BigDecimal mdlId_U = uBMsg.mdlId_U.getValue();
                    String dsOrdPosnNum_U = uBMsg.dsOrdPosnNum_U.getValue();
                    if (!ZYPCommonFunc.hasValue(mdlId_R) || !ZYPCommonFunc.hasValue(mdlId_U) || mdlId_R.compareTo(mdlId_U) != 0) {
                        continue;
                    }
                    if (!ZYPCommonFunc.hasValue(dsOrdPosnNum_R) || !ZYPCommonFunc.hasValue(dsOrdPosnNum_U) || !dsOrdPosnNum_R.equals(dsOrdPosnNum_U)) {
                        continue;
                    }
                    NSAL1330_UBMsg copyUBMsg = new NSAL1330_UBMsg();
                    EZDMsg.copy(uBMsg, null, copyUBMsg, null);
                    uBMsgList.add(copyUBMsg);
                }
            }
        }
        ZYPTableUtil.clear(scrnMsg.R);
        scrnMsg.R.setValidCount(rBMsgList.size());
        for (int rIdx = 0; rIdx < scrnMsg.R.getValidCount(); rIdx++) {
            EZDMsg.copy(rBMsgList.get(rIdx), null, scrnMsg.R.no(rIdx), null);
        }
        ZYPTableUtil.clear(scrnMsg.U);
        scrnMsg.U.setValidCount(uBMsgList.size());
        for (int uIdx = 0; uIdx < scrnMsg.U.getValidCount(); uIdx++) {
            EZDMsg.copy(uBMsgList.get(uIdx), null, scrnMsg.U.no(uIdx), null);
        }
    }
    // END 2019/05/29 K.Kitachi [QC#50567, ADD]
}
