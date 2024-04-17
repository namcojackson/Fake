package business.servlet.NMAL0110;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0110.NMAL0110CMsg;
import business.servlet.NMAL0110.common.NMAL0110CommonLogic;
import business.servlet.NMAL0110.NMAL0110BMsg;
import business.servlet.NMAL0110.constant.NMAL0110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/16/2016   SRAA            K.Aratani       Update          QC#6748,9891,9916,9970
 * 02/07/2017   Fujitsu         K.Ishizuka      Update          QC16894
 * 09/25/2017   Fujitsu         T.Aoi           Update          QC#18534(L3#445)
 * 2020/04/07   Fujitsu         M.Ohno          Update          QC#56017
 * 2020/04/13   CITS            K.Ogino         Update          QC#56494
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NMAL0110_INIT extends S21INITCommonHandler implements NMAL0110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd_SC, param01);
            }
        }
        NMAL0110CMsg bizMsg = new NMAL0110CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
		NMAL0110CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        NMAL0110CommonLogic.changeActivation_Detail(this, getUserProfileService(), scrnMsg);
        NMAL0110CommonLogic.changeTableColorController(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.mdseCd_H1);
    }

    protected void setNameForMessage(EZDBMsg bMsg) {
    	
        NMAL0110BMsg scrnMsg = (NMAL0110BMsg) bMsg;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        scrnMsg.mdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Number"));
        scrnMsg.mdseDescShortTxt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Description"));
        scrnMsg.mdseItemMnfCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Manufacturer"));
        scrnMsg.mnfItemCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Manufacturer Item #"));
        scrnMsg.upcCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "UPC Code"));
        scrnMsg.mdseItemStsCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Status"));
        scrnMsg.mdseDescLongTxt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Long Description"));
        scrnMsg.mdseItemActvDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Implementation Date"));
        scrnMsg.mdseCratTmplNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Created From Template"));
        scrnMsg.mdseRgtnTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Registration Mode"));
        scrnMsg.mdseItemTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Type"));
        scrnMsg.mdseItemClsTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Classification"));
        scrnMsg.coaMdseTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Merchandise Type"));
        scrnMsg.prchGrpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Planning Group"));
        //scrnMsg.mdsePrcGrpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Pricing Group"));
        scrnMsg.mktMdlCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Marketing Model"));
        scrnMsg.mktMdlSegCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Marketing Segment"));
        scrnMsg.zerothProdCtrlCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Product Level 1"));
        scrnMsg.firstProdCtrlCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Product Level 2"));
        scrnMsg.scdProdCtrlCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Product Level 3"));
        scrnMsg.thirdProdCtrlCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Product Level 4"));
        scrnMsg.frthProdCtrlCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Product Level 5"));
        scrnMsg.mdseCratTmplNm_H2.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Template Name"));
        scrnMsg.mdseCratTmplCratDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Template Created"));
        scrnMsg.inPoundWt_EA.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Boxed - Weight(pounds)"));
        scrnMsg.inPoundWt_UN.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "UnBoxed - Weight(pounds)"));
        scrnMsg.inInchLg_EA.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Boxed - Length(inches)"));
        scrnMsg.inInchLg_UN.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "UnBoxed - Length(inches)"));
        scrnMsg.inInchWdt_EA.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Boxed - Depth(inches)"));
        scrnMsg.inInchWdt_UN.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "UnBoxed - Depth(inches)"));
        scrnMsg.inInchHgt_EA.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Boxed - Height(inches)"));
        scrnMsg.inInchHgt_UN.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "UnBoxed - Height(inches)"));
        scrnMsg.xxChkBox_ME.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Main Engine"));
        scrnMsg.backOrdImpctTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Criticality"));
        scrnMsg.xxChkBox_RM.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Reman Available"));
        scrnMsg.sowReqFlg_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "SOW Required"));
        scrnMsg.svcChrgItemTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Charge Type"));
        scrnMsg.imgSplyOemMnfCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Supply OEM Manufacturer"));
        scrnMsg.imgSplyOemCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Supply OEM Code"));
        scrnMsg.imgSplyTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Supply Type"));
        scrnMsg.imgSplyColorTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Supply Color"));
        scrnMsg.imgSplyYieldCnt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Supply Yield"));
        scrnMsg.imgSplyYieldUomCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Supply Yield UOM"));
        scrnMsg.imgSplyYieldTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Supply Yield Type"));
        scrnMsg.easyPackTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Easy PAC I"));
        scrnMsg.areaOfPaperNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Area of Paper"));
        scrnMsg.xxChkBox_PD.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Parts Dropship Disabled"));
        scrnMsg.svcCovTmplTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Coverage Type"));
        scrnMsg.svcAllocTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Allocation Type"));
        // 2017/09/25 QC#18534(L3#445) ADD Start
        scrnMsg.svcPgmTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Program Type"));
        scrnMsg.xxChkBox_OM.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Override Manufacture Warranty"));
        // 2017/09/25 QC#18534(L3#445) ADD End
        scrnMsg.swCatgCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Software Category"));
        scrnMsg.swTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Software Type"));
        scrnMsg.swVrsnTxt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Software Version"));
        scrnMsg.swLicCtrlTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "License Control"));
        scrnMsg.swLicSeatFromQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Seats From"));
        scrnMsg.swLicSeatToQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Seats To"));
        scrnMsg.swLicSeatFixQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Fixed # of Seat"));
        scrnMsg.xxChkBox_EC.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Elan Control"));
        scrnMsg.swMaintCtrlTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Software Maintenance Controlled"));
        scrnMsg.asrnPntPerLicQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Assurance Points Per License"));
        scrnMsg.swMaintTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Maintenance Item Type"));
        scrnMsg.swMaintTermYr_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Maintenance Item Term"));
        scrnMsg.asrnPntMinQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Assurance Point Min"));
        scrnMsg.asrnPntMaxQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Assurance Point Max"));
        scrnMsg.asrnPntFixPerOrdQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Assurance Points Fixed"));
        scrnMsg.svcChrgItemTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Charge Type"));
        scrnMsg.xxChkBox_HM.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Hazardous Material"));
        scrnMsg.hazMatCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Hazardous Number"));
        scrnMsg.hazClsNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Hazardous Class"));
        scrnMsg.hazPrpShipNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Hazardous Shipping Label"));
        scrnMsg.hazIdNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Hazardous ID"));
        scrnMsg.madeInCtryCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Manufactured Country"));
        scrnMsg.asmInCtryCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Assembled Country"));
        scrnMsg.pkgUomClsCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Unit of Measure Class"));
        scrnMsg.xxChkBox_IT.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Inventory Trackable"));
        // 2020/04/13 QC#56494 Add Start
        scrnMsg.xxChkBox_II.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Internal Item"));
        // 2020/04/13 QC#56494 Add End
        scrnMsg.xxChkBox_RA.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "RMA Allowed"));
        scrnMsg.xxChkBox_RI.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "RMA Inspection Required"));
        scrnMsg.defSrcWhCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Default Source Warehouse"));
        scrnMsg.defSrcSubWhCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Default Source Sub Warehouse"));
        scrnMsg.defSrcProcrTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Default Source Type"));
        scrnMsg.invtyHardAllocTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Hard Allocation Type"));
        scrnMsg.xxChkBox_RR.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Return Controlled"));
        scrnMsg.rtrnCtrlTpNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Return Control Type"));
        scrnMsg.rtrnDsplTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Return Sub Warehouse"));
        scrnMsg.locNm_V1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Return Vendor"));
        scrnMsg.locNm_P1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Return Site"));
        scrnMsg.locNm_W1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Return Warehouse"));
        scrnMsg.xxRadioBtn_SS.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Serial Control"));
        scrnMsg.thisMthTotStdCostAmt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Standard Cost"));
        scrnMsg.mdseCstUpdDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Standard Cost Date"));
        scrnMsg.lastMthTotStdCostAmt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Standard Cost Previous"));
        scrnMsg.assetRecovCostAmt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "ARV Cost"));
        scrnMsg.assetRecovCostAmtUpdDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "ARV Cost Date"));
        scrnMsg.prevAssetRecovCostAmt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "ARV Cost Previous"));
        scrnMsg.origFobAmt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Purchase Price"));
        scrnMsg.origFobAmtUpdDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Purchase Price Date"));
        scrnMsg.revCoaAcctCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Revenue"));
        scrnMsg.cogsCoaAcctCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Cost of Goods"));
        scrnMsg.expCoaAcctCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Expense"));
        scrnMsg.lineBizTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Line of Business"));
        scrnMsg.xxChkBox_IP.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Invoiceable"));
        scrnMsg.dfrdAcctgRuleCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Accounting Rules"));
        scrnMsg.dfrdInvRuleCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Invoicing Rules"));
        scrnMsg.taxExemTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tax Code"));
        scrnMsg.svcWtyTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Warrenty"));
        scrnMsg.wtyDaysAot_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Warrenty Period( days )"));
        scrnMsg.xxChkBox_MM.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Metered Machine"));
        scrnMsg.xxChkBox_IB.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Install Base Trackable"));
        scrnMsg.xxChkBox_SC.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Service Call Enabled"));
        scrnMsg.xxChkBox_IR.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Imageware Remote Enabled"));
        scrnMsg.iwrMdlCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Imageware Remote Model"));
        scrnMsg.iwrMdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Imageware Remote Item"));
        scrnMsg.mdseItemBillTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Billing Type"));
        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
        scrnMsg.svcIstlRuleNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Installation Rules"));
        scrnMsg.svcIstlCallGrpNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Install Call Group"));
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]
        // START 2023/12/12 K.Watanabe [QC#61300, ADD]
        scrnMsg.svcDeinsRuleNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Deinstallation Rules"));
        scrnMsg.svcDeinsCallGrpNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Deinstall Call Group"));
        // END 2023/12/12 K.Watanabe [QC#61300, ADD]
        scrnMsg.xxChkBox_CO.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Customer Orderable"));
        scrnMsg.cpoMinOrdQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Minimum Order Quantity"));
        scrnMsg.cpoMaxOrdQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Maximum Order Quantity"));
        scrnMsg.cpoIncrOrdQty_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Order Increments"));
        scrnMsg.xxChkBox_IE.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Remanufactured Item Exists")); // Mod S21_NA#16894
        scrnMsg.xxChkBox_CF.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Requires Config Center"));
        scrnMsg.mdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Number"));
        scrnMsg.mdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Number"));
        scrnMsg.mdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Number"));
        scrnMsg.mdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Number"));
        scrnMsg.mdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Number"));
        scrnMsg.mdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Number"));
        scrnMsg.mdseCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Item Number"));
        scrnMsg.dsIntgMdseTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Intangible Type"));
        scrnMsg.frtClsCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Freight Class"));
        scrnMsg.trfCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Tariff"));
        // 2020/04/02 QC#56017 Add Start
        scrnMsg.xxChkBox_RP.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Representative"));
        // 2020/04/02 QC#56017 Add End
    }
}
