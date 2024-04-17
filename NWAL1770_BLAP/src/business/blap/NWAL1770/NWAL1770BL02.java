/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import static business.blap.NWAL1770.constant.NWAL1770Constant.ADDL_HDR_ID;
import static business.blap.NWAL1770.constant.NWAL1770Constant.BILL_EVENT_LIST;
import static business.blap.NWAL1770.constant.NWAL1770Constant.DS_DROP_SHIP_WH_CD;
import static business.blap.NWAL1770.constant.NWAL1770Constant.ITEMS_HDR_ID;
import static business.blap.NWAL1770.constant.NWAL1770Constant.KEY_OUTPUT_CONTACT_CD;
import static business.blap.NWAL1770.constant.NWAL1770Constant.NEW_LINE;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SHIP_EVENT_LIST;
import static business.blap.NWAL1770.constant.NWAL1770Constant.SOLD_EVENT_LIST;
import static business.blap.NWAL1770.constant.NWAL1770Constant.TAB_ITEMS;
import static business.blap.NWAL1770.constant.NWAL1770Constant.TAB_ADDITIONAL;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_BILL_TO_LOC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_BILL_TO_NM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_BILL_TO_NUM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_CATG;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_FRT_TERMS;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_ITEM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SHIP_TO_LOC;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SHIP_TO_NM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SHIP_TO_NUM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SLS_REP_NM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SLS_REP_NUM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SOLD_TO_NM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_SOLD_TO_NUM;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.MSG_PARAM_WH;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0037E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0100E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.NWAM0181E;
import static business.blap.NWAL1770.constant.NWAL1770MsgConstant.ZZM8100I;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1770.common.NWAL1770CommonLogic;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForCustomer;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForDefOrdCatgRsn;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForItemLine;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForOrderHistory;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForPriceChanges;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForPricing;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForSalesCredit;
import business.blap.NWAL1770.common.NWAL1770CommonLogicForSaveSubmit;
import business.db.MDSETMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.parts.NWZC157001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/09/27   Fujitsu         H.Ikeda         Update          S21_NA#13914
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/02/24   Fujitsu         Y.Kanefusa      Update          S21_NA#13688
 * 2017/03/13   Fujitsu         T.Aoi           Update          S21_NA#16987
 * 2017/07/27   Fujitsu         S.Takami        Update          S21_NA#18243
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#10347
 * 2017/08/09   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2017/08/17   Fujitsu         S.Takami        Update          S21_NA#20659
 * 2017/09/28   Fujitsu         T.Murai         Update          S21_NA#21121
 * 2017/10/19   Hitachi         J.Kim           Update          QC#21157
 * 2017/10/24   Hitachi         J.Kim           Update          QC#21312
 * 2017/10/26   Fujitsu         R.Nakamura      Update          S21_NA#20304
 * 2017/11/27   Fujitsu         M.Ohno          Update          S21_NA#21155
 * 2017/11/30   Fujitsu         A.Kosai         Update          S21_NA#21580
 * 2018/02/13   Fujitsu         T.Aoi           Update          S21_NA#21165
 * 2018/03/07   Fujitsu         A.Kosai         Update          S21_NA#22387
 * 2018/03/19   Fujitsu         A.Kosai         Update          S21_NA#24810
 * 2018/04/04   Fujitsu         K.Ishizuka      Update          S21_NA#24988
 * 2018/04/16   Fujitsu         N.Sugiura       Update          S21_NA#22965
 * 2018/04/17   Fujitsu         A.Kosai         Update          S21_NA#25230
 * 2018/05/15   Fujitsu         S.Takami        Update          S21_NA#26134
 * 2018/06/21   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/07/10   Fujitsu         T.Noguchi       Update          S21_NA#26661,26713
 * 2018/07/19   Fujitsu         M.Ishii         Update          S21_NA#26153
 * 2018/08/02   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/08/24   Fujitsu         T.Noguchi       Update          S21_NA#27202
 * 2018/12/12   Fujitsu         K.Kato          Update          S21_NA#29315
 * 2019/01/08   Fujitsu         K.Kato          Update          S21_NA#29241
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * 2023/11/29   CITS            L.Duy           Update          CSA-QC#62421
 * 2024/04/03   CITS            A.Shimada       Update          CSA-QC#63691
 * </pre>
 */
public class NWAL1770BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
            NWAL1770SMsg glblMsg = (NWAL1770SMsg) sMsg;
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            boolean isRequireCheckWarning = false;
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

            if ("NWAL1770_INIT".equals(screenAplID)) {
                if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteNum)) {
                    // 2018/04/04 S21_NA#24988 Mod Start
                    // doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg);
                    doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg, false);
                    // 2018/04/04 S21_NA#24988 Mod End
                } else {
                    doProcess_NWAL1770_INIT(bizMsg, glblMsg);
                }
            } else if ("NWAL1770Scrn00_Quote_Search".equals(screenAplID)) {
                // 2018/04/04 S21_NA#24988 Mod Start
                // doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg);
                doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg, false);
                // 2018/04/04 S21_NA#24988 Mod End
            } else if ("NWAL1770Scrn00_Quote_Copy".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_Quote_Copy(bizMsg, glblMsg);
            } else if ("NWAL1770Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_CMN_Clear(bizMsg, glblMsg);
            } else if ("NWAL1770Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_CMN_Reset(bizMsg, glblMsg);
            } else if ("NWAL1770Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_CMN_Save(bizMsg, glblMsg);
            } else if ("NWAL1770Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_CMN_Submit(bizMsg, glblMsg);
                // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
                isRequireCheckWarning = true;
                // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            } else if ("NWAL1770Scrn00_TAB_Items".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_TAB_Items(bizMsg, glblMsg);
                // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
                isRequireCheckWarning = true;
                // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            } else if ("NWAL1770Scrn00_TAB_AdditionalData".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_TAB_AdditionalData(bizMsg, glblMsg);
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            } else if ("NWAL1770Scrn00_TAB_CustomerContact".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_TAB_CustomerContact(bizMsg);
            } else if ("NWAL1770Scrn00_TAB_OrderHistory".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_TAB_OrderHistory(bizMsg);
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            } else if ("NWAL1770Scrn00_Calculation_QuoteAmount".equals(screenAplID)) {
                // 2018/07/19 QC#26153 Mod Start
//                doProcess_NWAL1770Scrn00_Calculation_QuoteAmount(bizMsg, glblMsg);
                doProcess_NWAL1770Scrn00_Calculation_QuoteAmount(bizMsg, glblMsg, false);
                // 2018/07/19 QC#26153 Mod End
            } else if ("NWAL1770Scrn00_Add_ContactLine".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_Add_ContactLine(bizMsg);
            } else if ("NWAL1770Scrn00_Delete_ContactLine".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_Delete_ContactLine(bizMsg, glblMsg);
            } else if ("NWAL1770Scrn00_Add_ItemLine".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_Add_ItemLine(bizMsg);
            } else if ("NWAL1770Scrn00_Cancel_ItemLine".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_Cancel_ItemLine(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromCategory".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromCategory(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromSalesRepName".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSalesRepName(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromSalesRepCode".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSalesRepCode(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromBillToAccount".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromBillToAccount(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromBillToLocation".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromBillToLocation(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromBillToName".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromBillToName(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromShipToAccount".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromShipToAccount(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromShipToLocation".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromShipToLocation(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromShipToName".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromShipToName(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromSoldToAccount".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSoldToAccount(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromSoldToLocation".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSoldToLocation(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromSoldToName".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSoldToName(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromItem".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromItem(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromQty".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromQty(bizMsg);
            // QC#6480 2016/06/16 Add Start
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromDtlSlsAmt".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromDtlSlsAmt(bizMsg);
            // QC#6480 2016/06/16 Add End
            } else if ("NWAL1770Scrn00_OnBlur_FreightTerms".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_FreightTerms(bizMsg);
            // Add Start 2017/09/28 S21_NA#21121
            } else if ("NWAL1770Scrn00_OnBlur_DeriveFromPaymentTerms".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_DeriveFromPaymentTerms(bizMsg);
            // Add End 2017/09/28 S21_NA#21121
            } else if ("NWAL1770Scrn00_OnChange_ShpgSvcLvlCd".equals(screenAplID)) { // QC#13688 2017/02/24 Add
                doProcess_NWAL1770Scrn00_OnChange_ShpgSvcLvlCd(bizMsg);
            } else if ("NWAL1770Scrn00_OnBlur_ChangeWH".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnBlur_ChangeWH(bizMsg);
            } else if ("NWAL1770Scrn00_OnChange_Reason".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnChange_Reason(bizMsg);
            } else if ("NWAL1770Scrn00_OnChange_UOM".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OnChange_UOM(bizMsg);
            } else if ("NWAL1770Scrn00_OpenWin_SalesRep".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_SalesRep(bizMsg);
            } else if ("NWAL1770Scrn00_OpenWin_SpecialInstruction".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_SpecialInstruction(bizMsg);
            } else if ("NWAL1770Scrn00_OpenWin_ShipToChange".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_ShipToChange(bizMsg);
            } else if ("NWAL1770Scrn00_OpenWin_CarrierServiceLevel".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_CarrierServiceLevel(bizMsg);
            } else if ("NWAL1770Scrn00_OpenWin_PriceChanges".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_PriceChanges(bizMsg);
            } else if ("NWAL1770Scrn00_OpenWin_AssnProgram".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_AssnProgram(bizMsg);
            // 2018/07/19 QC#26153 Add Start
            } else if ("NWAL1770Scrn00_OpenWin_Profitability".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_Profitability(bizMsg, glblMsg);
            // 2018/07/19 QC#26153 Add End
            // Add Start 2017/09/28 S21_NA#21121
            } else if ("NWAL1770Scrn00_MoveWin_CreditCard".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_CMN_Save(bizMsg, glblMsg);
            // Add End 2017/09/28 S21_NA#21121
            } else if ("NWAL1770_NLCL1000".equals(screenAplID)) {
                doProcess_NWAL1770_NLCL1000(bizMsg);
            } else if ("NWAL1770_NMAL6760".equals(screenAplID)) {
                doProcess_NWAL1770_NMAL6760(bizMsg);
            } else if ("NWAL1770_NMAL6800".equals(screenAplID)) {
                doProcess_NWAL1770_NMAL6800(bizMsg);
            } else if ("NWAL1770_NWAL1130".equals(screenAplID)) {
                doProcess_NWAL1770_NWAL1130(bizMsg);
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            } else if ("NWAL1770_NWAL1500".equals(screenAplID)) {
                doProcess_NWAL1770_NWAL1500(bizMsg);
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            } else if ("NWAL1770_NWAL1600".equals(screenAplID)) {
                doProcess_NWAL1770_NWAL1600(bizMsg);
            } else if ("NWAL1770_NWAL1660".equals(screenAplID)) {
                doProcess_NWAL1770_NWAL1660(bizMsg);
            } else if ("NWAL1770_NWAL1870".equals(screenAplID)) {
                doProcess_NWAL1770_NWAL1870(bizMsg, glblMsg);
            // QC#16452 Add Start
            } else if ("NWAL1770_NMAL6770".equals(screenAplID)) {
                doProcess_NWAL1770_NMAL6770(bizMsg);
            // QC#16452 Add End
            // Add Start 2017/09/28 S21_NA#21121
            } else if ("NWAL1770_NMAL6050".equals(screenAplID)) {
                doProcess_NWAL1770_NMAL6050(bizMsg);
                // Add End 2017/09/28 S21_NA#21121
            // 2018/06/21 QC#14307 Add Start
            } else if ("NWAL1770Scrn00_OpenWin_SoldTo".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_SoldTo(bizMsg);
            } else if ("NWAL1770Scrn00_OpenWin_BillTo".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_BillTo(bizMsg);
            } else if ("NWAL1770Scrn00_OpenWin_ShipTo".equals(screenAplID)) {
                doProcess_NWAL1770Scrn00_OpenWin_ShipTo(bizMsg);
            // 2018/06/21 QC#14307 Add End
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,DEL]
//            } else {
//                return;
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,DEL]
            }

            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            if (TAB_ITEMS.equals(bizMsg.xxDplyTab.getValue()) || isRequireCheckWarning) {
                NWAL1770CommonLogic.checkSupplyCoveredContract(bizMsg);
                NWAL1770CommonLogicForItemLine.checkHazMat(bizMsg);
            }
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do Process (Init)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770_INIT(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        // 2018/07/19 QC#26153 Mod Start

//        // Initial Screen Objects
//        bizMsg.clear();
//        ZYPTableUtil.clear(bizMsg.A);
//        ZYPTableUtil.clear(bizMsg.B);
//        ZYPTableUtil.clear(bizMsg.C);
//        ZYPTableUtil.clear(bizMsg.D);
//        ZYPTableUtil.clear(bizMsg.E);
//        ZYPTableUtil.clear(bizMsg.T);
//        ZYPTableUtil.clear(bizMsg.U);
//        ZYPTableUtil.clear(bizMsg.V);
//        ZYPTableUtil.clear(bizMsg.W);
//        ZYPTableUtil.clear(bizMsg.X);
//        ZYPTableUtil.clear(bizMsg.Y);
//        ZYPTableUtil.clear(bizMsg.Z);
//        glblMsg.clear();
//        ZYPTableUtil.clear(glblMsg.B);
//        ZYPTableUtil.clear(glblMsg.F);
//
//        // Setting Initial Values
//        String glblCmpyCd = getGlobalCompanyCode();
//        NWAL1770CommonLogic.setInitialValues(bizMsg, glblCmpyCd, getContextUserInfo().getUserId());
//        NWAL1770CommonLogic.setAuthority(bizMsg, getUserProfileService());
//
//        // Create Pulldown
//        NWAL1770CommonLogic.createPulldown(bizMsg);
//
//        // Set Function Currency Digit Number
//        GLBL_CMPYTMsg glblTMsg = new GLBL_CMPYTMsg();
//        ZYPEZDItemValueSetter.setValue(glblTMsg.glblCmpyCd, glblCmpyCd);
//        glblTMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(glblTMsg);
//        if (glblTMsg != null) {
//            CCYTMsg ccyMsg = new CCYTMsg();
//            ZYPEZDItemValueSetter.setValue(ccyMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(ccyMsg.ccyCd, glblTMsg.stdCcyCd);
//            ccyMsg = (CCYTMsg) S21FastTBLAccessor.findByKey(ccyMsg);
//            if (ccyMsg != null) {
//                ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd, ccyMsg.ccyCd);
//                ZYPEZDItemValueSetter.setValue(bizMsg.aftDeclPntDigitNum, ccyMsg.aftDeclPntDigitNum);
//            }
//        }

        // Init -> CommonLogic
        String glblCmpyCd = getGlobalCompanyCode();
        String adminPsnCd = getContextUserInfo().getUserId();
        S21UserProfileService userProfileService = getUserProfileService();
        NWAL1770CommonLogic.doProcess_Init_BL02(bizMsg, glblMsg, glblCmpyCd, adminPsnCd, userProfileService);
        // 2018/07/19 QC#26153 Mod End

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        List<String> roleIdList = getContextUserInfo().getRoleNameList();
        if (NWAL1770CommonLogicForDefOrdCatgRsn.setCatgRsnDefaultValue(bizMsg, adminPsnCd, roleIdList)) {
            doProcess_NWAL1770Scrn00_OnChange_Reason(bizMsg);
        }
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    }

    /**
     * do Process (Quote_Search)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    // 2018/04/04 S21_NA#24988 Mod Start
    // private void doProcess_NWAL1770Scrn00_Quote_Search(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {
    private void doProcess_NWAL1770Scrn00_Quote_Search(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, boolean isCallCopy) {
    // 2018/04/04 S21_NA#24988 Mod End

        // 2018/07/19 QC#26153 Mod Start        
//        // Backup Data
//        String dplyTab = bizMsg.xxDplyTab.getValue();
//        String splyQuoteNum = bizMsg.splyQuoteNum.getValue();
//
//        // Initial Screen Objects
//        doProcess_NWAL1770_INIT(bizMsg, glblMsg);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, dplyTab);
//        ZYPEZDItemValueSetter.setValue(bizMsg.splyQuoteNum, splyQuoteNum);
//
//        // Get Header (SPLY_QUOTE)
//        S21SsmEZDResult ssmResult = NWAL1770QueryForSearch.getInstance().getSplyQuoteHdr(bizMsg);
//        if (ssmResult.isCodeNotFound()) {
//            bizMsg.setMessageInfo(ZZZM9001E);
//            return;
//        }
//        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdPmtTermFlg, NWXC150001DsCheck.getOvrdPmtTermFlg(bizMsg.glblCmpyCd.getValue(), bizMsg.billToCustAcctCd.getValue(), bizMsg.billToCustCd.getValue()));         // QC#17474 2017/02/21 Add
//
//        // Get Detail (SPLY_QUOTE_DTL)
//        // 2018/04/04 S21_NA#24988 Mod Start
//        // ssmResult = NWAL1770QueryForSearch.getInstance().getSplyQuoteDtl(bizMsg);
//        ssmResult = NWAL1770QueryForSearch.getInstance().getSplyQuoteDtl(bizMsg, isCallCopy);
//        // 2018/04/04 S21_NA#24988 Mod End
//        // 2017/08/17 S21_NA#20659 Del Start
////        if (ssmResult.isCodeNotFound()) {
////            bizMsg.setMessageInfo(ZZZM9001E);
////            return;
////        }
//        // 2017/08/17 S21_NA#20659 Del End
//
//        // Get Sales Credit (SPLY_QUOTE_SLS_CR)
//        NWAL1770QueryForSearch.getInstance().getSplyQuoteSalesCredit(bizMsg);
//
//        // Get Contact Person (SPLY_QUOTE_CTAC_PSN)
//        NWAL1770QueryForSearch.getInstance().getSplyQuoteCtacPsn(bizMsg);
//
//        // Get Additional Data
//        NWAL1770CommonLogicForSearch.getSplyQuoteAddlData(bizMsg);
//
//        // Get Price Information (SPLY_QUOTE_PRC_CALC_BASE)
//        // 2018/04/04 S21_NA#24988 Mod Start
//        // NWAL1770QueryForSearch.getInstance().getSplyQuotePrcCalcBase(bizMsg);
//        NWAL1770QueryForSearch.getInstance().getSplyQuotePrcCalcBase(bizMsg, isCallCopy);
//        // 2018/04/04 S21_NA#24988 Mod End
//        NWAL1770CommonLogicForSearch.setPrice(bizMsg);
//
//        // Get Location Number
//        NWAL1770CommonLogicForSearch.getLocNum(bizMsg);
//
//        // Create Pulldown
//        NWAL1770CommonLogicForSearch.createPulldown(bizMsg);
//        
//        // Store Backup Data For $ Button
//        EZDMsg.copy(bizMsg, null, glblMsg, null);
//        EZDMsg.copy(bizMsg.B, null, glblMsg.B, null);
//        ZYPEZDItemValueSetter.setValue(bizMsg.xxSrchNum, bizMsg.splyQuoteNum);

        String glblCmpyCd = getGlobalCompanyCode();
        NWAL1770CommonLogicForItemLine.quote_search(bizMsg, glblMsg, isCallCopy, glblCmpyCd);
        // 2018/07/19 QC#26153 Mod End

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        // Get Credit Rep
        if (!isCallCopy) {
            NWAL1770CommonLogicForCustomer.searchCreditRep(bizMsg);
        }
        // Check Supply Covered Contract
        NWAL1770CommonLogic.checkSupplyCoveredContractInfo(bizMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}

    /**
     * do Process (Quote_Copy)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_Quote_Copy(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        // 2018/04/04 S21_NA#24988 Mod Start
        // doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg);
        doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg, true);
        // 2018/04/04 S21_NA#24988 Mod End
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        // Setting Initial Values For Copy
        NWAL1770CommonLogic.setInitialValuesForCopy(bizMsg);

        // Calculation Quote Amount
        // 2018/07/19 QC#26153 Mod Start
//        doProcess_NWAL1770Scrn00_Calculation_QuoteAmount(bizMsg, glblMsg);
        doProcess_NWAL1770Scrn00_Calculation_QuoteAmount(bizMsg, glblMsg, false);
        // 2018/07/19 QC#26153 Mod End

        // Clear Calc Base PK
        for (int i = 0; i < bizMsg.E.getValidCount(); i++) {
            bizMsg.E.no(i).ordPrcCalcBasePk_E.clear();
        }
    }

    /**
     * do Process (CMN_Clear)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_CMN_Clear(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        doProcess_NWAL1770_INIT(bizMsg, glblMsg);
    }

    /**
     * do Process (CMN_Reset)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_CMN_Reset(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.splyQuoteNum)) {
            // 2018/04/04 S21_NA#24988 Mod Start
            // doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg);
            doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg, false);
            // 2018/04/04 S21_NA#24988 Mod End
        } else {
            doProcess_NWAL1770_INIT(bizMsg, glblMsg);
        }
    }

    /**
     * do Process (CMN_Save)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_CMN_Save(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        doProcess_NWAL1770Scrn00_CMN_Submit(bizMsg, glblMsg);

        //START 2024/04/03 [CSA-QC#63691,ADD] 
        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            boolean isItemExists = ZYPCommonFunc.hasValue(bizMsg.B.no(i).mdseCd_B);
            if (isItemExists) {
                NWAL1770CommonLogic.createBlankDetailLine(bizMsg);
                break;
            }
        }
        //END 2024/04/03 [CSA-QC#63691,ADD]
    }

    /**
     * do Process (CMN_Submit)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_CMN_Submit(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        if ("W".equals(bizMsg.getMessageKind())) {
            return;
        }

        // 2018/04/04 S21_NA#24988 Mod Start
        // doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg);
       doProcess_NWAL1770Scrn00_Quote_Search(bizMsg, glblMsg, false);
        // 2018/04/04 S21_NA#24988 Mod End

        // 2023/11/29 CSA-QC#62421 Add Start
        String dplyTab = bizMsg.xxDplyTab.getValue();
        if (TAB_ITEMS.equals(dplyTab)) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, ITEMS_HDR_ID);
        } else if (TAB_ADDITIONAL.equals(dplyTab)) {
            ZYPGUITableColumn.getColData(bizMsg, glblMsg, ADDL_HDR_ID);
        }
        // 2023/11/29 CSA-QC#62421 Add End
    }

    /**
     * do Process (TAB_Items)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_TAB_Items(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        ZYPGUITableColumn.getColData(bizMsg, glblMsg, ITEMS_HDR_ID);

        if (!NWAL1770CommonLogicForItemLine.checkMandatoryAddlField(bizMsg)) {
            return;
        }

        //START 2024/04/03 [CSA-QC#63691,ADD]
        NWAL1770CommonLogic.createBlankDetailLine(bizMsg);
        //END 2024/04/03 [CSA-QC#63691,ADD]
        //START 2024/04/03 [CSA-QC#63691,DEL]
        // 2017/08/17 S21_NA#20659 Add Start
        //NWAL1770CommonLogic.createEmptyDetaiLine(bizMsg);
        // 2017/08/17 S21_NA#20659 Add End
        //END 2024/04/03 [CSA-QC#63691,DEL]


        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
            if (!ZYPCommonFunc.hasValue(itemLineMsg.prcCatgNm_B)) {
                ZYPEZDItemValueSetter.setValue(itemLineMsg.prcCatgNm_B, bizMsg.prcCatgNm);
            }
            // 2018/02/13 QC#21165 Add Start
            if (!ZYPCommonFunc.hasValue(itemLineMsg.rddDt_B)) {
                if (ZYPCommonFunc.hasValue(bizMsg.rddDt)) {
                    ZYPEZDItemValueSetter.setValue(itemLineMsg.rddDt_B, bizMsg.rddDt);
                }
            }
            // 2018/02/13 QC#21165 Add End
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            NWAL1770CommonLogicForItemLine.checkHazMat(itemLineMsg);
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        }

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkSupplyCoveredContractInfo(bizMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    }

    /**
     * do Process (TAB_AdditionalData)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_TAB_AdditionalData(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        ZYPGUITableColumn.getColData(bizMsg, glblMsg, ADDL_HDR_ID);
    }

    /**
     * do Process (Calculation_QuoteAmount)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
      // 2018/07/19 QC#26153 Mod Start 
//    private void doProcess_NWAL1770Scrn00_Calculation_QuoteAmount(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {
      private void doProcess_NWAL1770Scrn00_Calculation_QuoteAmount(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg, boolean isCallProfitability) {
      // 2018/07/19 QC#26153 Mod End

        // Check Mandatory
        // 2016/09/27 S21_NA#13914 Mod Start
        boolean isCallSave = true;
        if (!NWAL1770CommonLogicForSaveSubmit.checkMandatoryField(bizMsg, isCallSave)) {
        //if (!NWAL1770CommonLogicForSaveSubmit.checkMandatoryField(bizMsg)) {
        // 2016/09/27 S21_NA#13914 Mod End
            return;
        }

        // Set Manual Price
        // QC#6480 2016/06/16 Del Start
        // for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
        // NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(i);
        //
        // // Skip Cancel Line
        // String cancelStsTxt = ZYPCodeDataUtil.getVarCharConstValue(KEY_QUOTE_CANCELLED,
        // bizMsg.glblCmpyCd.getValue());
        // if (cancelStsTxt.equals(itemLineMsg.splyQuoteStsDescTxt_B.getValue()) ||
        // SPLY_QUOTE_STS.CANCELLED.equals(itemLineMsg.splyQuoteLineStsCd_B.getValue())) {
        // continue;
        // }
        //
        // // Skip Set Component
        // String[] xxLineNumArray = itemLineMsg.xxLineNum_B.getValue().split("\\.");
        // if (xxLineNumArray.length == IDX_3 && BigDecimal.ZERO.compareTo(new
        // BigDecimal(xxLineNumArray[2])) != 0) {
        // continue;
        // }
        //
        // NWAL1770_ECMsg basePriceLine = NWAL1770CommonLogicForPricing.getBasePrcData(bizMsg,
        // itemLineMsg);
        //
        // ZYPEZDItemValueSetter.setValue(itemLineMsg.manPrcFlg_B, ZYPConstant.FLG_OFF_N);
        // if (ZYPCommonFunc.hasValue(itemLineMsg.dealSplyQuoteDtlSlsAmt_B) && basePriceLine !=
        // null) {
        // if
        // (itemLineMsg.dealSplyQuoteDtlSlsAmt_B.getValue().compareTo(basePriceLine.autoPrcAmtRate_E.getValue())
        // != 0) {
        // ZYPEZDItemValueSetter.setValue(basePriceLine.manPrcAmtRate_E,
        // itemLineMsg.dealSplyQuoteDtlSlsAmt_B);
        // ZYPEZDItemValueSetter.setValue(basePriceLine.prcCondManEntryFlg_E, ZYPConstant.FLG_ON_Y);
        // ZYPEZDItemValueSetter.setValue(itemLineMsg.manPrcFlg_B, ZYPConstant.FLG_ON_Y);
        // }
        // }
        // }
        // QC#6480 2016/06/16 Del End

        // Call Pricing API
        NWZC157001PMsg prcApiPMsg = NWAL1770CommonLogicForPricing.callPricingApi(bizMsg, NWZC157001.GET_ORDER_ALL); // S21_NA#22965 Mod
        if (prcApiPMsg == null) {
            return;
        }

        // Setup Price Elements
        NWAL1770CommonLogicForPricing.setPriceElement(bizMsg, prcApiPMsg);

        // Store Backup Data For $ Button
        EZDMsg.copy(bizMsg, null, glblMsg, null);
        EZDMsg.copy(bizMsg.B, null, glblMsg.B, null);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxBtnFlg_PR, ZYPConstant.FLG_ON_Y);
        // 2018/07/19 QC#26153 Mod Start
//        bizMsg.setMessageInfo(ZZM8100I);
        if (!isCallProfitability) {
            bizMsg.setMessageInfo(ZZM8100I);
        }
        // 2018/07/19 QC#26153 Mod End
    }

    /**
     * do Process (Add_ContactLine)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_Add_ContactLine(NWAL1770CMsg bizMsg) {

        if (bizMsg.A.getValidCount() == bizMsg.A.length()) {
            bizMsg.setMessageInfo(NWAM0100E);
            return;
        }

        bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);

        String defaultContactTpCd = ZYPCodeDataUtil.getVarCharConstValue(KEY_OUTPUT_CONTACT_CD, bizMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(defaultContactTpCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount() - 1).ctacPsnTpCd_A, defaultContactTpCd);
        }

        // QC#16452 add Start
        int idx = bizMsg.A.getValidCount() - 1;
        //set default
        String ctacPsnTpCd = bizMsg.A.no(idx).ctacPsnTpCd_A.getValue();
        for (int i = 0; i < bizMsg.ctacTpCd_PL.length(); ++i) {
            if (ctacPsnTpCd.equals(bizMsg.ctacTpCd_PL.no(i).getValue())) {
                bizMsg.A.no(idx).ctacCustRefTpCd_A.setValue(bizMsg.L.no(i).ctacCustRefTpCd_L.getValue());
                break;
            }
        }
        // QC#16452 add End

    }

    /**
     * do Process (Delete_ContactLine)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_Delete_ContactLine(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);

        for (int checkIndex : checkList) {
            NWAL1770_ACMsg ctacLineMsg = bizMsg.A.no(checkIndex);

            if (ZYPCommonFunc.hasValue(ctacLineMsg.splyQuoteCtacPsnPk_A)) {
                NWAL1770_FSMsg ctacLineGlblMsg = glblMsg.F.no(glblMsg.F.getValidCount());
                EZDMsg.copy(ctacLineMsg, "A", ctacLineGlblMsg, "F");
                glblMsg.F.setValidCount(glblMsg.F.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.A, checkList);
    }

    /**
     * do Process (Add_ItemLine)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_Add_ItemLine(NWAL1770CMsg bizMsg) {

        int validCnt = bizMsg.B.getValidCount();
        if (validCnt == bizMsg.B.length()) {
            bizMsg.setMessageInfo(NWAM0100E);
            return;
        }

        // Set New ItemLine
        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(validCnt);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.dplyQuoteLineNum_B, new BigDecimal(validCnt + 1));
        ZYPEZDItemValueSetter.setValue(itemLineMsg.xxLineNum_B, NWAL1770CommonLogic.concatLineNum(itemLineMsg));
        ZYPEZDItemValueSetter.setValue(itemLineMsg.prcCatgNm_B, bizMsg.prcCatgNm);
        ZYPEZDItemValueSetter.setValue(itemLineMsg.supdLockFlg_B, ZYPConstant.FLG_OFF_N);
        // QC#10347 2017/07/24 Add Start
        ZYPEZDItemValueSetter.setValue(itemLineMsg.prcBaseDt_B, bizMsg.slsDt);
        // QC#10347 2017/07/24 Add End
        // 2018/02/13 QC#21165 Add Start
        ZYPEZDItemValueSetter.setValue(itemLineMsg.rddDt_B, bizMsg.rddDt);
        // 2018/02/13 QC#21165 Add End
        bizMsg.B.setValidCount(validCnt + 1);

        // Renumbering Line Number
        NWAL1770CommonLogic.reNumberingItemLine(bizMsg);
    }

    /**
     * do Process (Cancel_ItemLine)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_Cancel_ItemLine(NWAL1770CMsg bizMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);
        List<Integer> delItemLineList = new ArrayList<Integer>();

        for (int checkIndex : checkList) {
            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(checkIndex);
            BigDecimal dplyQuoteLineNum = itemLineMsg.dplyQuoteLineNum_B.getValue();
            // 2018/03/19 S21_NA#24810 Mod Start
//            String cancelStsTxt = ZYPCodeDataUtil.getVarCharConstValue(KEY_QUOTE_CANCELLED, bizMsg.glblCmpyCd.getValue());
            String cancelStsTxt = NWAL1770CommonLogic.getCancelStsTxt(bizMsg);
            // 2018/03/19 S21_NA#24810 Mod End

            if (!ZYPCommonFunc.hasValue(itemLineMsg.splyQuoteStsDescTxt_B)) {
                delItemLineList.add(checkIndex);

                // For Set Component
                int componentIndex = checkIndex + 1;
                for (int i = checkIndex + 1; i < bizMsg.B.getValidCount(); i++) {
                    NWAL1770_BCMsg componentLineMsg = bizMsg.B.no(i);
                    if (dplyQuoteLineNum.compareTo(componentLineMsg.dplyQuoteLineNum_B.getValue()) == 0) {
                        delItemLineList.add(componentIndex++);
                    } else {
                        break;
                    }
                }
                continue;
            } else if (cancelStsTxt.equals(itemLineMsg.splyQuoteStsDescTxt_B.getValue()) || SPLY_QUOTE_STS.CANCELLED.equals(itemLineMsg.splyQuoteLineStsCd_B.getValue())) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(itemLineMsg.xxChkBox_B, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.splyQuoteStsDescTxt_B, cancelStsTxt);

            // For Set Component
            for (int i = checkIndex + 1; i < bizMsg.B.getValidCount(); i++) {
                NWAL1770_BCMsg componentLineMsg = bizMsg.B.no(i);
                if (dplyQuoteLineNum.compareTo(componentLineMsg.dplyQuoteLineNum_B.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(componentLineMsg.splyQuoteStsDescTxt_B, cancelStsTxt);
                } else {
                    break;
                }
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.B, delItemLineList);

        //START 2024/04/03 [CSA-QC#63691,DEL]
//        int validCnt = bizMsg.B.getValidCount();
//        if (validCnt == 0) {
//            // Set New ItemLine
//            NWAL1770_BCMsg newItemLineMsg = bizMsg.B.no(validCnt);
//            newItemLineMsg.dplyQuoteLineNum_B.setValue(validCnt + 1);
//            newItemLineMsg.xxLineNum_B.setValue(NWAL1770CommonLogic.concatLineNum(newItemLineMsg));
//            newItemLineMsg.prcCatgNm_B.setValue(bizMsg.prcCatgNm.getValue());
//            newItemLineMsg.supdLockFlg_B.setValue(ZYPConstant.FLG_OFF_N);
//            // QC#10347 2017/07/24 Add Start
//            ZYPEZDItemValueSetter.setValue(newItemLineMsg.prcBaseDt_B, bizMsg.slsDt);
//            // QC#10347 2017/07/24 Add End
//            // 2018/02/13 QC#21165 Add Start
//            ZYPEZDItemValueSetter.setValue(newItemLineMsg.rddDt_B, bizMsg.rddDt);
//            // 2018/02/13 QC#21165 Add End
//            bizMsg.B.setValidCount(validCnt + 1);
//        }
//
//        // Renumbering Line Number
//        NWAL1770CommonLogic.reNumberingItemLine(bizMsg);
        //END 2024/04/03 [CSA-QC#63691,DEL]
        //START 2024/04/03 [CSA-QC#63691,ADD]
        NWAL1770CommonLogic.createBlankDetailLine(bizMsg);
        //END 2024/04/03 [CSA-QC#63691,ADD]
    }

    /**
     * do Process (OnBlur_DeriveFromCategory)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromCategory(NWAL1770CMsg bizMsg) {

        // 2018/08/02 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstructionForClear(bizMsg);
        // 2018/08/02 S21_NA#14307 Add End

        if (!NWAL1770CommonLogic.checkExistCatg(bizMsg)) {
            return;
        }

        // Create Reason Code Pulldown
        NWAL1770CommonLogic.createRsnCdPulldown(bizMsg);
        bizMsg.dsOrdTpCd.clear();

        // 2018/08/02 S21_NA#14307 Add Start
        bizMsg.lineBizTpCd.clear();
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/08/02 S21_NA#14307 Add End

        // 2018/12/12 QC#29315 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, NWAL1770CommonLogic.getBizAreaCd(bizMsg));
        // 2018/12/12 QC#29315 Add End

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogicForOrderHistory.searchOrderHistory(bizMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    }

    /**
     * do Process (OnBlur_DeriveFromSalesRepName)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSalesRepName(NWAL1770CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocNm)) {
            bizMsg.psnNum.clear(); // S21_NA#7861 Mod
            bizMsg.slsRepTocCd.clear();
            bizMsg.coaBrCd.clear();
            bizMsg.coaBrDescTxt.clear();
            bizMsg.coaExtnCd.clear();
            bizMsg.coaExtnDescTxt.clear();
            bizMsg.xxScrItem54Txt_CB.clear();
            bizMsg.xxScrItem54Txt_CE.clear();

            // Delete All Sales Credit
            NWAL1770CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

            return;
        }

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getDsOrdCatgList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return;
        }

        String writerSlsRepTocCd = NWAL1770CommonLogicForSalesCredit.getSlsRepCd(bizMsg, true, MSG_PARAM_SLS_REP_NM);
        if (!ZYPCommonFunc.hasValue(writerSlsRepTocCd)) {
            return;
        }

        // Delete All Sales Credit
        NWAL1770CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

        // Set Writer Sales Credit
        NWAL1770CommonLogicForSalesCredit.setWriterSlsCreditInfo(bizMsg, writerSlsRepTocCd);
    }

    /**
     * do Process (OnBlur_DeriveFromSalesRepCode)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSalesRepCode(NWAL1770CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.psnNum)) { // S21_NA#7861 Mod
            bizMsg.slsRepTocCd.clear();
            bizMsg.slsRepTocNm.clear();
            bizMsg.coaBrCd.clear();
            bizMsg.coaBrDescTxt.clear();
            bizMsg.coaExtnCd.clear();
            bizMsg.coaExtnDescTxt.clear();
            bizMsg.xxScrItem54Txt_CB.clear();
            bizMsg.xxScrItem54Txt_CE.clear();

            // Delete All Sales Credit
            NWAL1770CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

            return;
        }

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getDsOrdCatgList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return;
        }

        String writerSlsRepTocCd = NWAL1770CommonLogicForSalesCredit.getSlsRepCd(bizMsg, false, MSG_PARAM_SLS_REP_NUM); // S21_NA#7861 Mod
        if (!ZYPCommonFunc.hasValue(writerSlsRepTocCd)) {
            return;
        }

        // Delete All Sales Credit
        NWAL1770CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

        // Set Writer Sales Credit
        NWAL1770CommonLogicForSalesCredit.setWriterSlsCreditInfo(bizMsg, writerSlsRepTocCd);
    }

    /**
     * do Process (OnBlur_FreightTerms)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_FreightTerms(NWAL1770CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.frtCondDescTxt)) {
            S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getFrtCondCd(bizMsg);
            if (ssmResult.isCodeNormal()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, (String) ssmResult.getResultObject());
                NWAL1770CommonLogic.createShpgSvcLvlPulldown(bizMsg);
                // 2018/12/21 S21_NA#29315 Del Start
//                NWAL1770CommonLogic.deriveDefaultCarrSvcLvl(bizMsg); // QC#13688 2017/02/24 Add
                // 2018/12/21 S21_NA#29315 Del End
            } else {
                bizMsg.frtCondCd.clear();
            }
        } else {
            bizMsg.frtCondCd.clear();
        }
    }

    // Add Start 2017/09/28 S21_NA#21121
    /**
     * do Process (OnBlur_PaymentTerm)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromPaymentTerms(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getPmtTermList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWAM0181E, new String[] {"Ppayment Terms" });
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        List<Map<String, String>> pmtTermCashDiscCdList = (List<Map<String, String>>) ssmResult.getResultObject();
        if (pmtTermCashDiscCdList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscCd, pmtTermCashDiscCdList.get(0).get("PMT_TERM_CASH_DISC_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscDescTxt, pmtTermCashDiscCdList.get(0).get("PMT_TERM_CASH_DISC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.pmtCcFlg, pmtTermCashDiscCdList.get(0).get("PMT_CC_FLG"));
        if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals((bizMsg.pmtTermCashDiscCd.getValue()))) {
            bizMsg.dsPmtMethCd.setValue(DS_PMT_METH.CREDIT_CARD);
        }
        return;
    }
    // Add End 2017/09/28 S21_NA#21121

    // QC#13688 2017/02/24 Add Start
    private void doProcess_NWAL1770Scrn00_OnChange_ShpgSvcLvlCd(NWAL1770CMsg bizMsg) {
        // 2018/12/21 S21_NA#29315 Del Start
//        NWAL1770CommonLogic.deriveDefaultCarrSvcLvl(bizMsg);
        // 2018/12/21 S21_NA#29315 Del End
    }
    // QC#13688 2017/02/24 Add End

    /**
     * do Process (OnBlur_DeriveFromBillToAccount)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromBillToAccount(NWAL1770CMsg bizMsg) {

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/06/21 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1770CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, false, bizMsg.billToCustAcctCd, MSG_PARAM_BILL_TO_NUM);
        if (dsAcctCustTMsg == null) {
            return;
        }

        // 2018/08/02 S21_NA#14307 Add Start
        bizMsg.billToCustCd.clear();
        // 2018/08/02 S21_NA#14307 Add End
        NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg);

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/06/21 S21_NA#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromBillToLocation)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromBillToLocation(NWAL1770CMsg bizMsg) {

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, bizMsg.billToCustCd_BK);
        // 2018/06/21 S21_NA#14307 Add End

        Map<String, String> billToInfo = NWAL1770CommonLogicForCustomer.getBillToInfo(bizMsg, bizMsg.billToCustCd, MSG_PARAM_BILL_TO_LOC);
        if (billToInfo == null) {
            return;
        }

        String billToCustCd = billToInfo.get("BILL_TO_CUST_CD");
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctCd, billToInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustAcctNm, billToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd, billToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, billToInfo.get("BILL_TO_ADDR"));
        // 2018/03/07 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.billToLocNum, billToInfo.get("LOC_NUM"));
        // 2018/03/07 S21_NA#22387 Add End
        NWAL1770CommonLogic.deriveDefaultPmtTerm(bizMsg, billToCustCd);

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/06/21 S21_NA#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromBillToName)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromBillToName(NWAL1770CMsg bizMsg) {

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/06/21 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1770CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, true, bizMsg.billToCustAcctNm, MSG_PARAM_BILL_TO_NM);
        if (dsAcctCustTMsg == null) {
            return;
        }

        // 2018/08/02 S21_NA#14307 Add Start
        bizMsg.billToCustCd.clear();
        // 2018/08/02 S21_NA#14307 Add End
        NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromBillTo(bizMsg, dsAcctCustTMsg);

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/06/21 S21_NA#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromShipToAccount)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromShipToAccount(NWAL1770CMsg bizMsg) {

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/06/21 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1770CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, false, bizMsg.shipToCustAcctCd, MSG_PARAM_SHIP_TO_NUM);
        if (dsAcctCustTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        // 2017/07/27 S21_NA#18243 Add Start
        bizMsg.shipToCustCd.clear();
        // 2017/07/27 S21_NA#18243 Add End
        NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, true);

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/06/21 S21_NA#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromShipToLocation)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromShipToLocation(NWAL1770CMsg bizMsg) {

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, bizMsg.shipToCustCd_BK);
        // 2018/06/21 S21_NA#14307 Add End

        Map<String, String> shipToInfo = NWAL1770CommonLogicForCustomer.getShipToInfo(bizMsg);
        if (shipToInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, shipToInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, shipToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustCd, shipToInfo.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNm, shipToInfo.get("LOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToAddlLocNm, shipToInfo.get("ADDL_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, shipToInfo.get("SHIP_TO_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstLineAddr, shipToInfo.get("FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdLineAddr, shipToInfo.get("SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToThirdLineAddr, shipToInfo.get("THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFrthLineAddr, shipToInfo.get("FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToFirstRefCmntTxt, shipToInfo.get("FIRST_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToScdRefCmntTxt, shipToInfo.get("SCD_REF_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtyAddr, shipToInfo.get("CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToStCd, shipToInfo.get("ST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToPostCd, shipToInfo.get("POST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToProvNm, shipToInfo.get("PROV_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCtryCd, shipToInfo.get("CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCntyNm, shipToInfo.get("CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.locNum, shipToInfo.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        // 2017/11/27 S21_NA#21155 add star
        bizMsg.shipToLocNm_DS.clear();
        // 2017/11/27 S21_NA#21155 add end
        // 2018/03/09 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToLocNum, shipToInfo.get("LOC_NUM"));
        // 2018/03/09 S21_NA#22387 Add End

        NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, false);

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/06/21 S21_NA#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromShipToName)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromShipToName(NWAL1770CMsg bizMsg) {

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/06/21 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1770CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, true, bizMsg.shipToCustAcctNm, MSG_PARAM_SHIP_TO_NM);
        if (dsAcctCustTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.shipToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        // 2018/08/02 S21_NA#14307 Add Start
        bizMsg.shipToCustCd.clear();
        // 2018/08/02 S21_NA#14307 Add End
        NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, true);

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/06/21 S21_NA#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromSoldToAccount)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSoldToAccount(NWAL1770CMsg bizMsg) {

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, bizMsg.sellToCustCd_BK);
        // 2018/06/21 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1770CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, false, bizMsg.sellToCustCd, MSG_PARAM_SOLD_TO_NUM);
        if (dsAcctCustTMsg == null) {
            return;
        }

        // 2017/07/27 S21_NA#18243 Add Start
        bizMsg.soldToCustLocCd.clear();
        // 2017/07/27 S21_NA#18243 Add End
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, true);

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/06/21 S21_NA#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromSoldToLocation)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSoldToLocation(NWAL1770CMsg bizMsg) {

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/06/21 S21_NA#14307 Add End

        Map<String, String> billToInfo = NWAL1770CommonLogicForCustomer.getBillToInfo(bizMsg, bizMsg.soldToCustLocCd, MSG_PARAM_SHIP_TO_LOC);
        if (billToInfo == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, billToInfo.get("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, billToInfo.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustLocCd, billToInfo.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, billToInfo.get("BILL_TO_ADDR"));
        // 2018/03/07 S21_NA#22387 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.sellToLocNum, billToInfo.get("LOC_NUM"));
        // 2018/03/07 S21_NA#22387 Add End

        NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, false);

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/06/21 S21_NA#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromSoldToName)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSoldToName(NWAL1770CMsg bizMsg) {

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstruction(bizMsg, null);
        // 2018/06/21 S21_NA#14307 Add End

        SELL_TO_CUSTTMsg dsAcctCustTMsg = NWAL1770CommonLogicForCustomer.getDsAcctCustInfo(bizMsg, true, bizMsg.soldToCustAcctNm, MSG_PARAM_SOLD_TO_NM);
        if (dsAcctCustTMsg == null) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.sellToCustCd, dsAcctCustTMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.soldToCustAcctNm, dsAcctCustTMsg.dsAcctNm);
        // 2018/08/02 S21_NA#14307 Add Start
        bizMsg.soldToCustLocCd.clear();
        // 2018/08/02 S21_NA#14307 Add End
        NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, true);

        // 2018/06/21 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/06/21 S21_NA#14307 Add End
    }

    /**
     * do Process (OnBlur_DeriveFromItem)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromItem(NWAL1770CMsg bizMsg) {

        String inputMdseCd = NWAL1770CommonLogicForItemLine.checkMdseCd(bizMsg);
        if (!ZYPCommonFunc.hasValue(inputMdseCd)) {
            return;
        }

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkSupplyCoveredContractInfo(bizMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        MDSETMsg baseMdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), inputMdseCd);
        // 2017/03/13 S21_NA#16987 Mod Start
        int slctLine = bizMsg.xxCellIdx.getValueInt();
        MDSETMsg mdseMsg = new MDSETMsg();
        if (baseMdseTMsg != null) {
            ZYPEZDItemValueSetter.setValue(mdseMsg.glblCmpyCd, baseMdseTMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mdseMsg.mdseCd, baseMdseTMsg.mdseCd);
            mdseMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseMsg);
            if (mdseMsg != null) {

                //if (baseMdseTMsg == null || ZYPConstant.FLG_ON_Y.equals(baseMdseTMsg.sellHldFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(baseMdseTMsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(mdseMsg.custOrdEnblFlg.getValue())) {
                    bizMsg.B.no(slctLine).mdseCd_B.setErrorInfo(1, NWAM0037E);
                    return;
                }
            } else {
                bizMsg.B.no(slctLine).mdseCd_B.setErrorInfo(1, NWAM0037E);
                return;
            }
            // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
            NWAL1770CommonLogicForItemLine.checkHazMat(bizMsg, baseMdseTMsg.mdseCd.getValue());
            // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        } else {
            bizMsg.B.no(slctLine).mdseCd_B.setErrorInfo(1, NWAM0037E);
            return;
        }
        // 2017/03/13 S21_NA#16987 Mod End   

        NWAL1770CommonLogicForItemLine.deriveItemLineInfo(bizMsg, inputMdseCd, baseMdseTMsg);
        NWAL1770CommonLogicForItemLine.setEachQty(bizMsg, baseMdseTMsg.mdseCd.getValue());
        NWAL1770CommonLogicForItemLine.deriveLinePrice(bizMsg);
        NWAL1770CommonLogicForItemLine.deriveSetComponent(bizMsg);
        // Add Start 2017/10/27 QC#20304
        NWAL1770CommonLogicForItemLine.easyPackCheck(bizMsg, bizMsg.B.no(slctLine));
        // Add End 2017/10/27 QC#20304
    }

    /**
     * do Process (OnBlur_DeriveFromQty)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromQty(NWAL1770CMsg bizMsg) {

        int slctLine = bizMsg.xxCellIdx.getValueInt();
        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(slctLine);

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), itemLineMsg.mdseCd_B.getValue());

        if (mdseTMsg == null) {
            itemLineMsg.mdseCd_B.setErrorInfo(1, NWAM0037E);
            return;
        }
        // 2017/11/30 S21_NA#21580 Mod Start
        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.custOrdEnblFlg.getValue())) {
            itemLineMsg.mdseCd_B.setErrorInfo(1, NWAM0037E);
            return;
        }
        // 2017/11/30 S21_NA#21580 Mod End
        String mdseCd = mdseTMsg.mdseCd.getValue();
        BigDecimal eachQty = NWAL1770CommonLogicForItemLine.getEachQty(bizMsg, mdseCd, itemLineMsg.custUomCd_B.getValue(), itemLineMsg.ordCustUomQty_B.getValue());
        ZYPEZDItemValueSetter.setValue(itemLineMsg.ordQty_B, eachQty);

        // Set Supersede MDSE
        if (!NWAL1770CommonLogicForItemLine.setSupersedeMdse(bizMsg, mdseCd)) {
            return;
        }

        // START 2017/10/19 J.Kim [QC#21157,ADD]
        // Call Default WH API
        if (!NWAL1770CommonLogicForSaveSubmit.deriveDefaultWh(bizMsg, itemLineMsg)) {
            return;
        }
        // END 2017/10/19 J.Kim [QC#21157,ADD]

        NWAL1770CommonLogicForItemLine.deriveLinePrice(bizMsg);
        NWAL1770CommonLogicForItemLine.updateChildLine(bizMsg, slctLine);
        // START 2017/10/24 J.Kim [QC#21312,ADD]
        // 2019/01/08 QC#29241 Add Start
        //NWAL1770CommonLogic.checkOrdOty(mdseTMsg, itemLineMsg);
        NWAL1770CommonLogic.checkOrdOty(mdseTMsg, bizMsg, itemLineMsg);
        // 2019/01/08 QC#29241 Add End
        // END 2017/10/24 J.Kim [QC#21312,ADD]
        // Add Start 2017/10/27 QC#20304
        NWAL1770CommonLogicForItemLine.easyPackCheck(bizMsg, bizMsg.B.no(slctLine));
        // Add End 2017/10/27 QC#20304
    }

    // QC#6480 2016/06/16 Add Start
    /**
     * do Process (OnBlur_DeriveFromQty)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_DeriveFromDtlSlsAmt(NWAL1770CMsg bizMsg) {

        int slctLine = bizMsg.xxCellIdx.getValueInt();
        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(slctLine);

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), itemLineMsg.mdseCd_B.getValue());

        if (mdseTMsg == null) {
            itemLineMsg.mdseCd_B.setErrorInfo(1, NWAM0037E);
            return;
        }
        // 2017/11/30 S21_NA#21580 Mod Start
        if (ZYPConstant.FLG_ON_Y.equals(mdseTMsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.custOrdEnblFlg.getValue())) {
            itemLineMsg.mdseCd_B.setErrorInfo(1, NWAM0037E);
            return;
        }
        // 2017/11/30 S21_NA#21580 Mod End
        String mdseCd = mdseTMsg.mdseCd.getValue();
        BigDecimal eachQty = NWAL1770CommonLogicForItemLine.getEachQty(bizMsg, mdseCd, itemLineMsg.custUomCd_B.getValue(), itemLineMsg.ordCustUomQty_B.getValue());
        ZYPEZDItemValueSetter.setValue(itemLineMsg.ordQty_B, eachQty);

        // Set Supersede MDSE
        if (!NWAL1770CommonLogicForItemLine.setSupersedeMdse(bizMsg, mdseCd)) {
            return;
        }

        NWAL1770CommonLogicForItemLine.deriveLinePrice(bizMsg, false);
        NWAL1770CommonLogicForItemLine.updateChildLine(bizMsg, slctLine);
    }
    // QC#6480 2016/06/16 Add End

    /**
     * do Process (OnBlur_ChangeWH)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnBlur_ChangeWH(NWAL1770CMsg bizMsg) {

        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());

        if (ZYPCommonFunc.hasValue(bizMsg.dsOrdTpCd)) {
            if (!NWAL1770QueryForItemLine.getInstance().isExistWhWithDsOrdTp(bizMsg, itemLineMsg)) {
                itemLineMsg.rtlWhNm_B.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
            }
        } else {
            if (!NWAL1770QueryForItemLine.getInstance().isExistWhWithOutDsOrdTp(bizMsg, itemLineMsg)) {
                itemLineMsg.rtlWhNm_B.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_WH });
            }
        }
    }

    /**
     * do Process (OnChange_Reason)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnChange_Reason(NWAL1770CMsg bizMsg) {

        // 2018/08/02 S21_NA#14307 Add Start
        NWAL1770CommonLogicForCustomer.initProcSpecialInstructionForClear(bizMsg);
        // 2018/08/02 S21_NA#14307 Add End

        // Create Line Category Pulldown
        String primaryLineCatgCd = NWAL1770CommonLogic.createLineCatgPulldown(bizMsg, bizMsg.slsDt.getValue());
        if (ZYPCommonFunc.hasValue(primaryLineCatgCd)) {
            for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(i).dsOrdLineCatgCd_B, primaryLineCatgCd);
            }
        }

        // Set Default Data
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdTpCd, NWAL1770CommonLogic.getCpoOrdTpCd(bizMsg));
        NWAL1770CommonLogic.deriveDefaultData(bizMsg);

        // 2018/08/02 S21_NA#14307 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/08/02 S21_NA#14307 Add End

        // 2018/12/12 QC#29315 Add Start
        ZYPEZDItemValueSetter.setValue(bizMsg.dsBizAreaCd, NWAL1770CommonLogic.getBizAreaCd(bizMsg));
        // 2018/12/12 QC#29315 Add End
    }

    /**
     * do Process (OnChange_UOM)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OnChange_UOM(NWAL1770CMsg bizMsg) {

        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(bizMsg.xxCellIdx.getValueInt());

        if (!ZYPCommonFunc.hasValue(itemLineMsg.mdseCd_B) || !ZYPCommonFunc.hasValue(itemLineMsg.ordCustUomQty_B)) {
            return;
        }

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(bizMsg.glblCmpyCd.getValue(), itemLineMsg.mdseCd_B.getValue());
        if (null == mdseTMsg) {
            itemLineMsg.mdseCd_B.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ITEM });
            return;
        }

        BigDecimal eachQty = NWAL1770CommonLogicForItemLine.getEachQty(bizMsg, mdseTMsg.mdseCd.getValue(), itemLineMsg.custUomCd_B.getValue(), itemLineMsg.ordCustUomQty_B.getValue());
        ZYPEZDItemValueSetter.setValue(itemLineMsg.ordQty_B, eachQty);
    }

    /**
     * do Process (OpenWin_SalesRep)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_SalesRep(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getDsOrdCatgList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return;
        }
    }

    /**
     * do Process (OpenWin_SpecialInstruction)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_SpecialInstruction(NWAL1770CMsg bizMsg) {

        // 2018/08/02 S21_NA#14307 Mod Start
        //// 2018/07/10 S21_NA#26661,26713 Add Start
        //String funcCatgId = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.CUST_SPCL_ORDER_CATEGORY, bizMsg.splyQuoteCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
        //ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_02, funcCatgId);
        //// 2018/07/10 S21_NA#26661,26713 Add End
        //String trxRuleTp = NWAL1770CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        //ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_03, trxRuleTp);
        NWAL1770CommonLogicForCustomer.initProcSpecialInstructionForClear(bizMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);
        NWAL1770CommonLogicForCustomer.cmnProcForSpecialInstruction(bizMsg);
        // 2018/08/02 S21_NA#14307 Mod End
    }

    /**
     * do Process (OpenWin_ShipToChange)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_ShipToChange(NWAL1770CMsg bizMsg) {

        NWAL1770CommonLogicForCustomer.checkExistCustomer(bizMsg);
    }

    /**
     * do Process (OpenWin_CarrierServiceLevel)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_CarrierServiceLevel(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getFrtCondCd(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FRT_TERMS });
            return;
        }

        String frtCondCd = (String) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.frtCondCd, frtCondCd);
    }

    /**
     * do Process (OpenWin_PriceChanges)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_PriceChanges(NWAL1770CMsg bizMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);
// S21_NA#22965 Mod Start
//      if (checkList.size() == 0) {
//          return;
//      }
//      NWAL1770CommonLogicForPriceChanges.setPrcChangeParam(bizMsg, bizMsg.B.no(checkList.get(0)));
        if (checkList.size() == 0) {
            NWAL1770CommonLogicForPriceChanges.setPrcChangeParamForHeader(bizMsg);
        } else {
            NWAL1770CommonLogicForPriceChanges.setPrcChangeParam(bizMsg, bizMsg.B.no(checkList.get(0)));
        }
// S21_NA#22965 Mod End
    }

    /**
     * do Process (OpenWin_AssnProgram)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_AssnProgram(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getDsOrdCatgList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsOrdCatgDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CATG });
            return;
        }
    }


    // 2018/07/19 QC#26153 Add Start

    /**
     * do Process (OpenWin_Profitability)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_Profitability(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {
        if (bizMsg.cpoOrdNum.getValue().equals("")) {
            doProcess_NWAL1770Scrn00_Calculation_QuoteAmount(bizMsg, glblMsg, true);
        }
    }
    // 2018/07/19 QC#26153 Add End

    /**
     * do Process (NWAL1770_NLCL1000)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NLCL1000(NWAL1770CMsg bizMsg) {

        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NWAL1770_UCMsg popupMsg = bizMsg.U.no(i);
            String rtlWhCd = popupMsg.rtlWhCd_P1.getValue();
            String rtlSwhCd = popupMsg.rtlSwhCd_P1.getValue();

            NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(Integer.parseInt(popupMsg.xxScrItem20Txt_P1.getValue()));
            // 2018/08/24 S21_NA#27202 Mod Start
            //ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlWhCd_B, rtlWhCd);
            //ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlWhNm_B, NWAL1770CommonLogic.getRtlWhNm(bizMsg, rtlWhCd));
            //ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhCd_B, rtlSwhCd);
            //ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhNm_B, NWAL1770CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
            //ZYPEZDItemValueSetter.setValue(itemLineMsg.invtyLocCd_B, popupMsg.invtyLocCd_P1);
            if (ZYPConstant.FLG_OFF_N.equals(itemLineMsg.xxReadOnlyFlg_B.getValue())) {
                String rtlWhNm = NWAL1770CommonLogic.getRtlWhNm(bizMsg, rtlWhCd);
                if (S21StringUtil.isEmpty(rtlWhNm)) {
                    rtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(DS_DROP_SHIP_WH_CD, bizMsg.glblCmpyCd.getValue());
                    rtlWhNm = NWAL1770CommonLogic.getRtlWhNm(bizMsg, rtlWhCd);
                }
                ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlWhCd_B, rtlWhCd);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlWhNm_B, rtlWhNm);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhCd_B, rtlSwhCd);
                ZYPEZDItemValueSetter.setValue(itemLineMsg.rtlSwhNm_B, NWAL1770CommonLogic.getRtlSubWhNm(bizMsg, rtlWhCd, rtlSwhCd));
                ZYPEZDItemValueSetter.setValue(itemLineMsg.invtyLocCd_B, popupMsg.invtyLocCd_P1);
            }
            // 2018/08/24 S21_NA#27202 Mod End
        }
    }

    /**
     * do Process (NWAL1770_NMAL6760)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NMAL6760(NWAL1770CMsg bizMsg) {

        String scrEventNm = bizMsg.xxScrEventNm.getValue();

        if (BILL_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_BT, NWAL1770CommonLogicForCustomer.cmbnAddr(bizMsg, NEW_LINE));
            NWAL1770CommonLogic.deriveDefaultPmtTerm(bizMsg, bizMsg.billToCustCd.getValue());

        } else if (SHIP_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SH, NWAL1770CommonLogicForCustomer.cmbnAddr(bizMsg, NEW_LINE));
            ZYPEZDItemValueSetter.setValue(bizMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            // 2017/11/27 QC#21155 add start
            bizMsg.shipToLocNm_DS.clear();
            // 2017/11/27 QC#21155 add end
            NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromShipTo(bizMsg, false);

        } else if (SOLD_EVENT_LIST.contains(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_SE, NWAL1770CommonLogicForCustomer.cmbnAddr(bizMsg, NEW_LINE));
            NWAL1770CommonLogicForCustomer.cmnProcForDeriveFromSoldTo(bizMsg, false);
        }
    }

    /**
     * do Process (NWAL1770_NMAL6800)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NMAL6800(NWAL1770CMsg bizMsg) {

        doProcess_NWAL1770Scrn00_OnBlur_DeriveFromItem(bizMsg);
    }

    /**
     * do Process (NWAL1770_NWAL1130)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NWAL1130(NWAL1770CMsg bizMsg) {

        String scrEventNm = bizMsg.xxScrEventNm.getValue();

        if ("OpenWin_OrderCategory".equals(scrEventNm) || "OnBlur_DeriveFromCategory".equals(scrEventNm)) {
            doProcess_NWAL1770Scrn00_OnBlur_DeriveFromCategory(bizMsg);
        } else if ("OpenWin_SalesRep".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepCode".equals(scrEventNm) || "OnBlur_DeriveFromSalesRepName".equals(scrEventNm)) {
            doProcess_NWAL1770Scrn00_OnBlur_DeriveFromSalesRepCode(bizMsg);
        } else if ("OpenWin_FreightTerms".equals(scrEventNm)) {
            NWAL1770CommonLogic.createShpgSvcLvlPulldown(bizMsg);
         // 2018/12/21 S21_NA#29315 Del Start
//            NWAL1770CommonLogic.deriveDefaultCarrSvcLvl(bizMsg); // QC#13688 2017/02/24 Add
         // 2018/12/21 S21_NA#29315 Del End
        }
    }

    /**
     * do Process (NWAL1770_NWAL1600)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NWAL1600(NWAL1770CMsg bizMsg) {

        NWAL1770CommonLogicForSalesCredit.copySlsCrFromPopup(bizMsg);

        // Header All Deleted
        if (NWAL1770CommonLogicForSalesCredit.isParamDtAllDeleted(bizMsg.T)) {
            bizMsg.slsRepTocCd.clear();
            bizMsg.slsRepTocNm.clear();
            bizMsg.psnNum.clear(); // S21_NA#7861 Mod
            bizMsg.coaBrCd.clear();
            bizMsg.coaBrDescTxt.clear();
            bizMsg.coaExtnCd.clear();
            bizMsg.coaExtnDescTxt.clear();
            bizMsg.xxScrItem54Txt_CB.clear();
            bizMsg.xxScrItem54Txt_CE.clear();

            // Delete All Sales Credit
            NWAL1770CommonLogicForSalesCredit.delAllSlsCreditInfo(bizMsg);

            return;
        }
    }

    /**
     * do Process (NWAL1770_NWAL1660)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NWAL1660(NWAL1770CMsg bizMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(bizMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);
// S21_NA#22965 Mod Start
//        NWAL1770_BCMsg lineMsg = bizMsg.B.no(checkList.get(0));
//
//        // 2018/03/19 S21_NA#24810 Mod Start
////        String cancelStsTxt = ZYPCodeDataUtil.getVarCharConstValue(KEY_QUOTE_CANCELLED, bizMsg.glblCmpyCd.getValue());
//        String cancelStsTxt = NWAL1770CommonLogic.getCancelStsTxt(bizMsg);
//        // 2018/03/19 S21_NA#24810 Mod End
//        if (!cancelStsTxt.equals(lineMsg.splyQuoteLineStsCd_B.getValue()) && !SPLY_QUOTE_STS.CANCELLED.equals(lineMsg.splyQuoteLineStsCd_B.getValue())) {
//            NWAL1770CommonLogicForPriceChanges.setScreenItemsFromPrcChange(bizMsg, lineMsg);
//            NWAL1770CommonLogicForPriceChanges.checkPriceRange(lineMsg, NWAL1770CommonLogicForPricing.getBasePrcData(bizMsg, lineMsg));
//        }

        if (checkList.size() == 0) {
            NWAL1770CommonLogicForPriceChanges.setScreenItemsFromPrcChangeForHeader(bizMsg);
        } else {
            NWAL1770_BCMsg lineMsg = bizMsg.B.no(checkList.get(0));
            String cancelStsTxt = NWAL1770CommonLogic.getCancelStsTxt(bizMsg);
            if (!cancelStsTxt.equals(lineMsg.splyQuoteLineStsCd_B.getValue()) && !SPLY_QUOTE_STS.CANCELLED.equals(lineMsg.splyQuoteLineStsCd_B.getValue())) {
                NWAL1770CommonLogicForPriceChanges.setScreenItemsFromPrcChange(bizMsg, lineMsg);
                NWAL1770CommonLogicForPriceChanges.checkPriceRange(lineMsg, NWAL1770CommonLogicForPricing.getBasePrcData(bizMsg, lineMsg));
            }
        }
// S21_NA#22965 Mod End
    }

    /**
     * do Process (NWAL1770_NWAL1870)
     * @param bizMsg NWAL1770CMsg
     * @param glblMsg NWAL1770SMsg
     */
    private void doProcess_NWAL1770_NWAL1870(NWAL1770CMsg bizMsg, NWAL1770SMsg glblMsg) {

        int slctLine = bizMsg.xxCellIdx.getValueInt();
        NWAL1770_BCMsg itemLineMsg = bizMsg.B.no(slctLine);

        String executeSupersede = bizMsg.xxPopPrm_00.getValue();
        if (ZYPConstant.FLG_ON_Y.equals(executeSupersede)) {
            ZYPEZDItemValueSetter.setValue(itemLineMsg.origMdseCd_B, itemLineMsg.mdseCd_B);
            // 2018/04/17 S21_NA#25230 Mod Start
//            ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_B, bizMsg.mdseCd_SS);
            ZYPEZDItemValueSetter.setValue(itemLineMsg.mdseCd_B,
                    NWAL1770CommonLogic.getOrdTakeMdseCd(getGlobalCompanyCode(), bizMsg.mdseCd_SS.getValue()));
            // 2018/04/17 S21_NA#25230 Mod End
            doProcess_NWAL1770Scrn00_OnBlur_DeriveFromItem(bizMsg);

        } else {
            // Continuation Of OnBlur Qty Event
            NWAL1770CommonLogicForItemLine.deriveLinePrice(bizMsg);
            NWAL1770CommonLogicForItemLine.updateChildLine(bizMsg, slctLine);

            // Set Supersede Lock Flag
            ZYPEZDItemValueSetter.setValue(itemLineMsg.supdLockFlg_B, ZYPConstant.FLG_ON_Y);
        }
    }
    // QC#16452 Add Start
    /**
     * do Process (NWAL1770_NMAL6770)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NMAL6770(NWAL1770CMsg bizMsg) {

        int idx = bizMsg.xxCellIdx.getValueInt();
        bizMsg.A.no(idx).ctacCustRefTpCd_AP.clear();
        String ctacTpCd = bizMsg.xxPopPrm_01.getValue();
        String ctacCustRefTpCd = "";
        if (ZYPCommonFunc.hasValue(bizMsg.A.no(idx).ctacCustRefTpCd_A)) {
            ctacCustRefTpCd = bizMsg.A.no(idx).ctacCustRefTpCd_A.getValue();
        } else {
            ctacCustRefTpCd = CTAC_CUST_REF_TP.SHIP_TO;
        }

        S21SsmEZDResult ssmResult = NWAL1770QueryForSearch.getInstance().getCtacCustRefTp(bizMsg, ctacTpCd, ctacCustRefTpCd);

        if (ssmResult.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(idx).ctacCustRefTpCd_AP, ctacCustRefTpCd); // not null -> protect
        }

    }
    // QC#16452 Add End
    // Add Start 2017/09/28 S21_NA#21121
    /**
     * do Process (NWAL1770_NMAL6050)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NMAL6050(NWAL1770CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1770Query.getInstance().getPmtTerm(bizMsg);
        if (ssmResult != null && ssmResult.isCodeNormal()) { // 2018/05/15 S21_NA#26134 Add "ssmResult.isCodeNormal()"
            Map<String, String> resultMap =  (Map<String, String>) ssmResult.getResultObject();

            ZYPEZDItemValueSetter.setValue(bizMsg.pmtCcFlg, resultMap.get("PMT_CC_FLG"));
            ZYPEZDItemValueSetter.setValue(bizMsg.pmtTermCashDiscCd, resultMap.get("PMT_TERM_CASH_DISC_CD"));
        }
    }
    // Add End 2017/09/28 S21_NA#21121

    // 2018/06/21 QC#14307 Add Start
    /**
     * do Process (OpenWin_SoldTo)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_SoldTo(NWAL1770CMsg bizMsg) {

        // 2018/07/10 S21_NA#26661,26713 Mod Start
        // String trxRuleTp = NWAL1770CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        // ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_10, trxRuleTp);
        NWAL1770CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
        // 2018/07/10 S21_NA#26661,26713 Mod End
    }

    /**
     * do Process (OpenWin_BillTo)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_BillTo(NWAL1770CMsg bizMsg) {

        // 2018/07/10 S21_NA#26661,26713 Mod Start
        // String trxRuleTp = NWAL1770CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        // ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_10, trxRuleTp);
        NWAL1770CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
        // 2018/07/10 S21_NA#26661,26713 Mod End
    }

    /**
     * do Process (OpenWin_ShipTo)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_OpenWin_ShipTo(NWAL1770CMsg bizMsg) {

        // 2018/07/10 S21_NA#26661,26713 Mod Start
        // String trxRuleTp = NWAL1770CommonLogicForCustomer.getTrxRuleTp(bizMsg);
        // ZYPEZDItemValueSetter.setValue(bizMsg.xxPopPrm_10, trxRuleTp);
        NWAL1770CommonLogicForCustomer.setAccountSearchPopupParam(bizMsg);
        // 2018/07/10 S21_NA#26661,26713 Mod End
    }
    // 2018/06/21 QC#14307 Add End

    // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
    /**
     * do Process (TAB_OrderHistory)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_TAB_OrderHistory(NWAL1770CMsg bizMsg) {
        NWAL1770CommonLogicForOrderHistory.searchOrderHistory(bizMsg);
    }

    /**
     * do Process (TAB_CustomerContact)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770Scrn00_TAB_CustomerContact(NWAL1770CMsg bizMsg) {
        NWAL1770CommonLogicForCustomer.searchCreditRep(bizMsg);
    }

    /**
     * do Process (NWAL1770_NWAL1500)
     * @param bizMsg NWAL1770CMsg
     */
    private void doProcess_NWAL1770_NWAL1500(NWAL1770CMsg bizMsg) {
        NWAL1770CommonLogicForCustomer.searchCreditRep(bizMsg);
    }
    // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
}
