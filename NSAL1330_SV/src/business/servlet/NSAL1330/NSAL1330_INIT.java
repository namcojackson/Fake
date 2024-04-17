/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.BIZ_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.FUNC_ID_UPD;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NSAM0681E;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.SCREEN_ID;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.XX_PAGE;

import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1330.NSAL1330CMsg;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2017/10/13   Hitachi         Y.Takeno        Update          QC#20059-1
 * 2017/10/26   Hitachi         Y.Takeno        Update          QC#21556
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/05/09   Fujitsu         M.Ohno          Update          QC#25030
 *</pre>
 */
public class NSAL1330_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

        S21UserProfileService userProfileService = getUserProfileService();
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        if (functionIds.size() == 0) {
            throw new S21AbendException("ZZSM4300E", new String[] {userProfileService.getContextUserInfo().getUserId() });
        }

        for (String functionId : functionIds) {
            if (FUNC_ID_UPD.equals(functionId)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxFuncId_UP, functionId);
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && (params.length > 0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk, (EZDBBigDecimalItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.refCpoOrdNum, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shellLineNum, (EZDBBigDecimalItem) params[2]);
        } else {
            scrnMsg.dsContrPk.clear();
            scrnMsg.refCpoOrdNum.clear();
            scrnMsg.shellLineNum.clear();
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageCd, XX_PAGE.PAGE_SHELL.getCode());

        NSAL1330CMsg bizMsg = new NSAL1330CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        if (NSAM0681E.equals(scrnMsg.getMessageCode())) {
            NSAL1330CommonLogic.initAppFracDigit(scrnMsg);
            NSAL1330CommonLogic.initCmnBtnProp(this, scrnMsg);
            NSAL1330CommonLogic.initInputProtected(scrnMsg);
            NSAL1330CommonLogic.initButton(this, scrnMsg);
            return;
        }
        NSAL1330CMsg bizMsg = (NSAL1330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/05/07 QC#22482 Del Start
//        NSAL1330CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/07 QC#22482 Del End
        NSAL1330CommonLogic.setAccessoryChargeAreaCtrl(scrnMsg);
        NSAL1330CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);
        // START 2017/10/13 [QC#20059-1, MOD]
        // NSAL1330CommonLogic.setConfigPricingAreaCtrl(scrnMsg, false);
        NSAL1330CommonLogic.setConfigPricingAreaCtrl(scrnMsg);
        // END   2017/10/13 [QC#20059-1, MOD]

        NSAL1330CommonLogic.initAppFracDigit(scrnMsg);
        NSAL1330CommonLogic.initCmnBtnProp(this, scrnMsg);
        //        if (!ZYPCommonFunc.hasValue(scrnMsg.xxFuncId_UP) //
        //                || NSAL1330CommonLogic.isImport(scrnMsg)) {
        //            NSAL1330CommonLogic.protectRefMode(this, scrnMsg);
        //            NSAL1330CommonLogic.initButton(this, scrnMsg);
        //            return;
        //        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxFuncId_UP)) {
            NSAL1330CommonLogic.protectRefMode(this, scrnMsg);
            return;
        }
        NSAL1330CommonLogic.initInputProtected(scrnMsg);
        NSAL1330CommonLogic.initButton(this, scrnMsg);
        NSAL1330CommonLogic.overrideProtected(this, scrnMsg);
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            //            NSAL1330CommonLogic.setBandButton(this, scrnMsg.Z.no(i).usgMdseCd_Z.getValue(), i);
            // START 2017/10/26 [QC#21556, ADD]
            if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.dsContrPk_AD)) {
                continue;
            }
            // END   2017/10/26 [QC#21556, ADD]
            NSAL1330CommonLogic.setBandButton(this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.Z.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            // START 2017/10/26 [QC#21556, ADD]
            if (DS_CONTR_CATG.AGGREGATE.equals(scrnMsg.dsContrCatgCd.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.dsContrPk_AD)) {
                continue;
            }
            // END   2017/10/26 [QC#21556, ADD]
            NSAL1330CommonLogic.setBandButtonConfig(this, scrnMsg, NSAL1330CommonLogic.isParentLine(scrnMsg.U.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1330CommonLogic.setTierLinkCtrl(this, scrnMsg, i);
            NSAL1330CommonLogic.setSupplyButton(this, scrnMsg.A.no(i), i, scrnMsg);
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NSAL1330CommonLogic.setTierLinkCtrlConfig(this, scrnMsg, i);
            NSAL1330CommonLogic.setSupplyButton(this, scrnMsg.R.no(i), i, scrnMsg);
        }
        NSAL1330CommonLogic.setCustomerCtrlConfig(scrnMsg);
        scrnMsg.putErrorScreen();

        // 2018/05/07 QC#22482 Mod Start
//        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue())) {
//            scrnMsg.setFocusItem(scrnMsg.svcMemoRsnCd);
//        } else {
//            scrnMsg.setFocusItem(scrnMsg.A.no(0).prcCatgNm_A);
//        }
        scrnMsg.setFocusItem(scrnMsg.A.no(0).prcCatgNm_A);
        // 2018/05/07 QC#22482 Mod End
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.xxScrItem50Txt.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Order Number"));
        scrnMsg.shellLineNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Maintenance Shell#"));
        scrnMsg.manContrOvrdFlg.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Manual Override"));
        scrnMsg.svcMemoRsnDescTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Manual Override Reason"));
        scrnMsg.svcCmntTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Manual Override Comments"));
        scrnMsg.basePrcDealAmt.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Shell Base Price"));

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL1330_ABMsg pricingMsg = scrnMsg.A.no(i);
            pricingMsg.t_MdlNm_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Model"));
            pricingMsg.xxTotQtyCnt_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Qty"));
            pricingMsg.prcCatgNm_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
            pricingMsg.prcMtrPkgPk_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Package"));
            pricingMsg.prcTierSvcOfferCd_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tiered Pricing"));
            pricingMsg.xxTotPrcAmt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Base"));
            pricingMsg.xxTotPrcAmt_EB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Extended Base"));
            pricingMsg.xxTotPrcAmt_TB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Total Base"));
            pricingMsg.termMthAot_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Term"));
        }

        for (int i = 0; i < scrnMsg.R.length(); i++) {
            NSAL1330_RBMsg pricingMsg = scrnMsg.R.no(i);
            pricingMsg.t_MdlNm_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Model"));
            pricingMsg.xxTotQtyCnt_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Qty"));
            pricingMsg.prcCatgNm_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
            pricingMsg.prcMtrPkgPk_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Package"));
            pricingMsg.prcTierSvcOfferCd_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tiered Pricing"));
            pricingMsg.xxTotPrcAmt_PR.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Base"));
            pricingMsg.xxTotPrcAmt_ER.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Extended Base"));
            pricingMsg.xxTotPrcAmt_TR.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Total Base"));
            pricingMsg.termMthAot_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Term"));
        }

        for (int i = 0; i < scrnMsg.Z.length(); i++) {
            NSAL1330_ZBMsg usgMsg = scrnMsg.Z.no(i);
            usgMsg.prcListBandDescTxt_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Band"));
            usgMsg.prcBookMdseCd_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price Book Item"));
            usgMsg.mtrLbDescTxt_ZB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Billing Counter Name"));
            usgMsg.mdseDescShortTxt_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Usage Item"));
            usgMsg.mtrLbDescTxt_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Hard Counter Name"));
            usgMsg.contrMtrMultRate_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Multi Plier"));
            usgMsg.xxChkBox_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tier Type CheckBox"));
            usgMsg.prcSvcTierTpCd_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tier Type"));
            usgMsg.mlyCopyInclPrcQty_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Per Unit Periodic Covered Images"));
            usgMsg.minCopyVolCnt_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Range From"));
            usgMsg.maxCopyVolCnt_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Range To"));
            usgMsg.xsMtrAmtRate_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Excess Per Image Charge"));
            // 2018/05/09 QC#25030 add start
            usgMsg.bllgFreeCopyCnt_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Free Copy"));
            usgMsg.bllgMinCnt_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Min. Vol"));
            usgMsg.bllgMinAmtRate_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Min. Amt"));
            usgMsg.bllgRollOverRatio_Z.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Roll Over %"));
            // 2018/05/09 QC#25030 add end
        }

        for (int i = 0; i < scrnMsg.U.length(); i++) {
            NSAL1330_UBMsg usgMsg = scrnMsg.U.no(i);
            usgMsg.prcListBandDescTxt_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Band"));
            usgMsg.prcBookMdseCd_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price Book Item"));
            usgMsg.mtrLbDescTxt_UB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Billing Counter Name"));
            usgMsg.mdseDescShortTxt_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Usage Item"));
            usgMsg.mtrLbDescTxt_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Hard Counter Name"));
            usgMsg.contrMtrMultRate_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Multi Plier"));
            usgMsg.xxChkBox_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tier Type CheckBox"));
            usgMsg.prcSvcTierTpCd_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tier Type"));
            usgMsg.mlyCopyInclPrcQty_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Per Unit Periodic Covered Images"));
            usgMsg.minCopyVolCnt_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Range From"));
            usgMsg.maxCopyVolCnt_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Range To"));
            usgMsg.xsMtrAmtRate_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Excess Per Image Charge"));
            // 2018/05/09 QC#25030 add start
            usgMsg.bllgFreeCopyCnt_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Free Copy"));
            usgMsg.bllgMinCnt_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Min. Vol"));
            usgMsg.bllgMinAmtRate_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Min. Amt"));
            usgMsg.bllgRollOverRatio_U.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Roll Over %"));
            // 2018/05/09 QC#25030 add end
        }

        scrnMsg.prcCatgNm_HJ.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
        for (int i = 0; i < scrnMsg.J.length(); i++) {
            NSAL1330_JBMsg acsryMsg = scrnMsg.J.no(i);
            acsryMsg.xxLineNum_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Line#"));
            acsryMsg.mdseCd_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Covered Item"));
            acsryMsg.mdseDescShortTxt_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Desciption"));
            acsryMsg.addlBasePrcDealAmt_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Service Price"));
            acsryMsg.prcCatgNm_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price List"));
            acsryMsg.prcListEquipConfigNum_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price Configration Name"));

        }

        scrnMsg.prcCatgNm_HB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            NSAL1330_BBMsg rntlEqipMsg = scrnMsg.B.no(i);
            rntlEqipMsg.xxLineNum_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Line#"));
            rntlEqipMsg.mdseCd_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Covered Item"));
            rntlEqipMsg.mdseDescShortTxt_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Desciption"));
            rntlEqipMsg.addlBasePrcDealAmt_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Service Price"));
            rntlEqipMsg.prcCatgNm_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price List"));
            rntlEqipMsg.prcListEquipConfigNum_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price Configration Name"));

        }

        scrnMsg.prcCatgNm_C.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NSAL1330_CBMsg addtionlMsg = scrnMsg.C.no(i);
            addtionlMsg.xxLineNum_C.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Line#"));
            addtionlMsg.mdseCd_CU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Covered Unit"));
            addtionlMsg.mdseDescShortTxt_CU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Unit Description"));
            addtionlMsg.mdseCd_CI.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Addtional Charge Item"));
            addtionlMsg.mdseDescShortTxt_CI.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Charge Description"));
            addtionlMsg.addlChrgPrcDealAmt_C.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Service Price"));
            // 2018/05/09 QC#25030 add start
            addtionlMsg.printDtlFlg_C.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Print Detail on Invoice"));
            // 2018/05/09 QC#25030 add end
        }
    }

}
