/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2180;

import static business.servlet.NWAL2180.constant.NWAL2180Constant.BIZ_ID;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.FUNC_ID_UPD;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.NWZM1107E;
import static business.servlet.NWAL2180.constant.NWAL2180Constant.SCREEN_ID;

import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2180.NWAL2180CMsg;
import business.servlet.NWAL2180.common.NWAL2180CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         T.Murai         Create          N/A
 * 2016/05/30   Fujitsu         M.Yamada        Update          QC#4628
 * 2016/07/22   Fujitsu         M.Yamada        Update          QC#11339
 * 2017/05/31   Fujitsu         S.Ohki          Update          RS#8233
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2023/04/06   CITS            K.Ogino         Update          QC#61314
 *</pre>
 */
public class NWAL2180_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;
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

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && (params.length > 0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoSvcDtlPk, (EZDBBigDecimalItem) params[0]);
            if (params.length > 1) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageCd, (EZDBStringItem) params[1]);
            }
        } else {
            scrnMsg.cpoSvcDtlPk.clear();
        }

        NWAL2180CMsg bizMsg = new NWAL2180CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;

        if (NWZM1107E.equals(scrnMsg.getMessageCode())) {
            NWAL2180CommonLogic.initAppFracDigit(scrnMsg);
            NWAL2180CommonLogic.initCmnBtnProp(this, scrnMsg);
            NWAL2180CommonLogic.initInputProtected(scrnMsg);
            NWAL2180CommonLogic.initButton(this, scrnMsg);
            return;
        }
        NWAL2180CMsg bizMsg = (NWAL2180CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // 2018/05/07 QC#22482 Del Start
//        NWAL2180CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/07 QC#22482 Del End
        NWAL2180CommonLogic.setAccessoryChargeAreaCtrl(scrnMsg);
        NWAL2180CommonLogic.setUsgPrcAreaCtrl(scrnMsg, this);
        NWAL2180CommonLogic.setConfigPricingAreaCtrl(scrnMsg, false);

        NWAL2180CommonLogic.initAppFracDigit(scrnMsg);
        NWAL2180CommonLogic.initCmnBtnProp(this, scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxFuncId_UP)) {
            NWAL2180CommonLogic.protectRefMode(this, scrnMsg);
            // QC#61314
//            return;
        }
        NWAL2180CommonLogic.initInputProtected(scrnMsg);
        NWAL2180CommonLogic.initButton(this, scrnMsg);
        NWAL2180CommonLogic.overrideProtected(this, scrnMsg);
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            NWAL2180CommonLogic.setBandButton(this, scrnMsg, NWAL2180CommonLogic.isParentLine(scrnMsg.Z.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.U.getValidCount(); i++) {
            NWAL2180CommonLogic.setBandButtonConfig(this, scrnMsg, NWAL2180CommonLogic.isParentLine(scrnMsg.U.no(i)), i);
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL2180CommonLogic.setTierLinkCtrl(this, scrnMsg, i);
            NWAL2180CommonLogic.setSupplyButton(this, scrnMsg.A.no(i), i, scrnMsg);
        }
        for (int i = 0; i < scrnMsg.R.getValidCount(); i++) {
            NWAL2180CommonLogic.setTierLinkCtrlConfig(this, scrnMsg, i);
            NWAL2180CommonLogic.setSupplyButton(this, scrnMsg.R.no(i), i, scrnMsg);
        }
        NWAL2180CommonLogic.setCustomerCtrlConfig(scrnMsg);
        scrnMsg.putErrorScreen();

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.manContrOvrdFlg.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.manContrOvrdRsnCd);
        } else {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).prcCatgNm_A);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2180BMsg scrnMsg = (NWAL2180BMsg) bMsg;

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.xxScrItem50Txt.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Order Number"));
        scrnMsg.dsImptSvcLineNum.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Maintenance Shell#"));
        scrnMsg.manContrOvrdFlg.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Manual Override"));
        scrnMsg.manContrOvrdRsnNm.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Manual Override Reason"));
        scrnMsg.manContrOvrdCmntTxt.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Manual Override Comments"));
        scrnMsg.basePrcDealAmt.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Shell Base Price"));

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL2180_ABMsg pricingMsg = scrnMsg.A.no(i);
            pricingMsg.t_MdlNm_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Model"));
            pricingMsg.xxTotQtyCnt_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Qty"));
            pricingMsg.prcCatgNm_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
            pricingMsg.prcMtrPkgPk_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Package"));
            pricingMsg.prcTierSvcOfferCd_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tiered Pricing"));
            pricingMsg.xxTotPrcAmt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Base"));
            pricingMsg.xxTotPrcAmt_EB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Extended Base"));
            pricingMsg.xxTotPrcAmt_TB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Total Base"));
            pricingMsg.shpgIntvlMthNum_A.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Term"));
        }

        for (int i = 0; i < scrnMsg.R.length(); i++) {
            NWAL2180_RBMsg pricingMsg = scrnMsg.R.no(i);
            pricingMsg.t_MdlNm_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Model"));
            pricingMsg.xxTotQtyCnt_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Qty"));
            pricingMsg.prcCatgNm_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
            pricingMsg.prcMtrPkgPk_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Package"));
            pricingMsg.prcTierSvcOfferCd_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tiered Pricing"));
            pricingMsg.xxTotPrcAmt_PR.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Base"));
            pricingMsg.xxTotPrcAmt_ER.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Extended Base"));
            pricingMsg.xxTotPrcAmt_TR.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Total Base"));
            pricingMsg.shpgIntvlMthNum_R.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Term"));
        }

        for (int i = 0; i < scrnMsg.Z.length(); i++) {
            NWAL2180_ZBMsg usgMsg = scrnMsg.Z.no(i);
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
        }

        for (int i = 0; i < scrnMsg.U.length(); i++) {
            NWAL2180_UBMsg usgMsg = scrnMsg.U.no(i);
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
        }

        scrnMsg.prcCatgNm_HJ.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
        for (int i = 0; i < scrnMsg.J.length(); i++) {
            NWAL2180_JBMsg acsryMsg = scrnMsg.J.no(i);
            acsryMsg.xxLineNum_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Line#"));
            acsryMsg.mdseCd_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Covered Item"));
            acsryMsg.mdseDescShortTxt_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Desciption"));
            acsryMsg.addlBasePrcDealAmt_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Service Price"));
            acsryMsg.prcCatgNm_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price List"));
            acsryMsg.prcListEquipConfigNum_J.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price Configration Name"));

        }

        scrnMsg.prcCatgNm_HB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            NWAL2180_BBMsg rntlEqipMsg = scrnMsg.B.no(i);
            rntlEqipMsg.xxLineNum_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Line#"));
            rntlEqipMsg.mdseCd_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Covered Item"));
            rntlEqipMsg.mdseDescShortTxt_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Desciption"));
            rntlEqipMsg.addlBasePrcDealAmt_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Service Price"));
            rntlEqipMsg.prcCatgNm_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price List"));
            rntlEqipMsg.prcListEquipConfigNum_B.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Price Configration Name"));

        }

        scrnMsg.prcCatgNm_C.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Price List"));
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            NWAL2180_CBMsg addtionlMsg = scrnMsg.C.no(i);
            addtionlMsg.xxLineNum_C.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Line#"));
            addtionlMsg.mdseCd_CU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Covered Unit"));
            addtionlMsg.mdseDescShortTxt_CU.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Unit Description"));
            addtionlMsg.mdseCd_CI.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Addtional Charge Item"));
            addtionlMsg.mdseDescShortTxt_CI.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Charge Description"));
            addtionlMsg.addlChrgPrcDealAmt_C.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Periodic Service Price"));
        }
    }

}
