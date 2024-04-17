/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import static business.servlet.NMAL7010.constant.NMAL7010Constant.NMAM8233I;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.NMAM8234I;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.SCRN_ID_00;
import static business.servlet.NMAL7010.constant.NMAL7010Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7010.NMAL7010CMsg;
import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7010_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         M.Nakamura      Create          N/A
 * 2016/02/02   SRAA            Y.Chen          Update          QC#2175
 * 2016/03/22   Fujitsu         Y.Kanefusa      Update          QC#4767
 * 2016/05/27   Fujitsu         W.Honda         Update          QC#8505
 * 2016/09/12   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/10/17   Fujitsu         W.Honda         Update          QC#15193
 * 2018/11/17   Fujitsu         N.Sugiura       Create          QC#29147
 *</pre>
 */
public class NMAL7010_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg = new NMAL7010CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null) {
            if (params[0] instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd_BK, (EZDBStringItem) params[0]);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd_BK, (String) params[0]);
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        NMAL7010CMsg bizMsg = (NMAL7010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7010CommonLogic.initCmnBtnProp(this);
        // Mod Start 2017/02/23 QC#16011
//        setScrnCtrl(scrnMsg);
        NMAL7010CommonLogic.setScrnCtrl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/23 QC#16011

        // Mod Start 2017/02/23 QC#16011
//        NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg);
        NMAL7010CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/23 QC#16011

        scrnMsg.setFocusItem(scrnMsg.prcCatgCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.prcCatgCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price List ID"));
        scrnMsg.prcCatgNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price List Name"));
        scrnMsg.prcListDplyNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price List Display Name"));
        scrnMsg.prcCatgDescTxt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Description/Notes"));

        scrnMsg.prcListTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price List Type"));
        scrnMsg.prcSlsVisTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Sales Visibility"));
        scrnMsg.prcContrNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Related Contract#"));

        scrnMsg.effFromDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date From"));
        scrnMsg.effThruDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date To"));

        scrnMsg.effThruDt_DH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date To"));

        // QC#8505 2016/05/26 Del start
//        scrnMsg.xxFileData_HC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "File Upload"));
//        scrnMsg.xxFileData_DH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "File Upload"));
        // QC#8505 2016/05/26 Del end

        scrnMsg.custBidNum_GA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Bid Number"));
        scrnMsg.spclCsmpExclArNm_CA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Special CSMP Exclude from AR Name"));

        scrnMsg.somEligTrxTpCd_CA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "SOM Transaction Type Eligible"));
        scrnMsg.prcCatgNm_CA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Related Lease Price List Name"));

        scrnMsg.grsPrcPct_CB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Cap SRT (%)"));

        // QC#2175
        for (int i = 0; i < scrnMsg.W.length(); i++) {
            scrnMsg.W.no(i).prcCatgNm_SW.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Sub Price Category Name"));
            scrnMsg.W.no(i).effFromDt_SW.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective From Date"));
            scrnMsg.W.no(i).effThruDt_SW.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective To Date"));
            scrnMsg.W.no(i).subPrcPrtyNum_SW.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Sub Price Sequence Number"));
        }
        
        for (int i = 0; i < scrnMsg.X.length(); i++) {
            scrnMsg.X.no(i).prcCustAudcCatgCd_X1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Audience Category 1"));
            scrnMsg.X.no(i).xxScrItem30Txt_X1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Audience Value 1"));
            scrnMsg.X.no(i).xxRecNmTxt_X1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Audience Name 1"));
            scrnMsg.X.no(i).prcCustAudcCatgCd_X2.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Audience Category 2"));
            scrnMsg.X.no(i).xxScrItem30Txt_X2.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Audience Value 2"));
            scrnMsg.X.no(i).xxRecNmTxt_X2.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Audience Name 2"));
            scrnMsg.X.no(i).prcCustAudcCatgCd_X3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Audience Category 3"));
            scrnMsg.X.no(i).xxScrItem30Txt_X3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Audience Value 3"));
            scrnMsg.X.no(i).xxRecNmTxt_X3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Audience Name 3"));
            scrnMsg.X.no(i).effFromDt_CX.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date From"));
            scrnMsg.X.no(i).effThruDt_CX.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date To"));
        }
        for (int i = 0; i < scrnMsg.Y.length(); i++) {
            scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Transaction Qualifer 1"));
            scrnMsg.Y.no(i).xxScrItem30Txt_Y1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Transaction Value 1"));
            scrnMsg.Y.no(i).xxRecNmTxt_Y1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Value 1 Name"));
            scrnMsg.Y.no(i).prcTrxAudcCatgCd_Y2.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Transaction Qualifer 2"));
            scrnMsg.Y.no(i).xxScrItem30Txt_Y2.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Transaction Value 2"));
            scrnMsg.Y.no(i).xxRecNmTxt_Y2.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Value 2 Name"));
            scrnMsg.Y.no(i).effFromDt_TY.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date From"));
            scrnMsg.Y.no(i).effThruDt_TY.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Effective Date To"));
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).prcListEquipConfigNum_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Config#"));
            scrnMsg.A.no(i).prcListEquipConfigNm_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Config Name"));
            scrnMsg.A.no(i).prcQlfyTpCd_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Qualifier Type"));
            scrnMsg.A.no(i).prcQlfyValTxt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Value"));
            scrnMsg.A.no(i).pkgUomCd_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "UOM"));
            // Add #4767 2016/03/22 Start
            scrnMsg.A.no(i).xxScrItem61Txt_P0.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Zeroth Product Control Code"));
            scrnMsg.A.no(i).xxScrItem61Txt_P1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "First Product Control Code"));
            scrnMsg.A.no(i).xxScrItem61Txt_P2.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Second Product Control Code"));
            scrnMsg.A.no(i).xxScrItem61Txt_P3.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Third Product Control Code"));
            scrnMsg.A.no(i).xxScrItem61Txt_P4.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Forth Product Control Code"));
            // Add #4767 2016/03/22 End
            scrnMsg.A.no(i).prcListEquipPrcAmt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price"));
            scrnMsg.A.no(i).minPrcAmt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Min Price"));
            scrnMsg.A.no(i).maxPrcAmt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max Price"));
            scrnMsg.A.no(i).prcTermAot_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Term"));
            scrnMsg.A.no(i).custBidQty_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Bid Qty"));
            scrnMsg.A.no(i).mlyPmtAmt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Monthly Payment Amount"));
            scrnMsg.A.no(i).minLeasePmtAmt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Lease Payment Min"));
            scrnMsg.A.no(i).maxLeasePmtAmt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Lease Payment Max"));
            scrnMsg.A.no(i).prcFmlaPk_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price Formula"));
            scrnMsg.A.no(i).quotRevAmt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Quota Rev"));
            scrnMsg.A.no(i).compCd_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Comp Code"));
            scrnMsg.A.no(i).effFromDt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date From"));
            scrnMsg.A.no(i).effThruDt_PA.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date To"));
        }
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).prcRateTpCd_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Rate Type"));
            scrnMsg.B.no(i).prcListMdseCd_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Item#"));
            scrnMsg.B.no(i).mdseDescShortTxt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Item Description"));
            scrnMsg.B.no(i).mdlNm_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Model Name"));
            scrnMsg.B.no(i).prcMtrPkgNm_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Meter Package Name"));
            scrnMsg.B.no(i).prcSvcPlnTpCd_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Plan Type"));
            scrnMsg.B.no(i).prcSvcContrTpCd_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Contract Type"));
            scrnMsg.B.no(i).mtrLbNm_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Meter Type Name"));
            scrnMsg.B.no(i).minCopyVolCnt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Min Vol"));
            scrnMsg.B.no(i).maxCopyVolCnt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max Vol"));
            scrnMsg.B.no(i).baseAmt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Base Amount"));
            scrnMsg.B.no(i).minBaseAmt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Min Base"));
            scrnMsg.B.no(i).maxBaseAmt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max Base"));
            scrnMsg.B.no(i).cpcAmtRate_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Cost Per Copy OVERAGE"));
            scrnMsg.B.no(i).minCpcAmtRate_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Min CPC"));
            scrnMsg.B.no(i).maxCpcAmtRate_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max CPC"));
            scrnMsg.B.no(i).termFromMthAot_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Term From(MTH)"));
            scrnMsg.B.no(i).termThruMthAot_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Term To(MTH)"));
            scrnMsg.B.no(i).mdseCd_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Item"));
            scrnMsg.B.no(i).mtrLbDescTxt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Billing Counter Name"));
            scrnMsg.B.no(i).quotRevAmt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Quota Rev"));
            scrnMsg.B.no(i).compCd_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Comp Code"));
            scrnMsg.B.no(i).effFromDt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date From"));
            scrnMsg.B.no(i).effThruDt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date To"));
            scrnMsg.B.no(i).prcListBandDescTxt_PB.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price List Band Description")); // 2018/11/17 QC#29147 Add
        }
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).mdlNm_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Model Name"));
            scrnMsg.C.no(i).prcSvcTierTpCd_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Tier Type"));
            scrnMsg.C.no(i).prcMtrPkgNm_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Meter Package Name"));
            scrnMsg.C.no(i).prcSvcPlnTpCd_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Plan Type"));
            scrnMsg.C.no(i).prcSvcContrTpCd_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Program"));
            scrnMsg.C.no(i).mtrLbCd_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Meter Type"));
            scrnMsg.C.no(i).avgCopyVolCnt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Ave Copy Volume"));
            scrnMsg.C.no(i).minCopyVolCnt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Min Vol"));
            scrnMsg.C.no(i).maxCopyVolCnt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max Vol"));
            scrnMsg.C.no(i).baseAmt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Base Amount"));
            scrnMsg.C.no(i).minBaseAmt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Min Base"));
            scrnMsg.C.no(i).maxBaseAmt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max Base"));
            scrnMsg.C.no(i).cpcAmtRate_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Cost Per Copy OVERAGE"));
            scrnMsg.C.no(i).minCpcAmtRate_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Min CPC"));
            scrnMsg.C.no(i).maxCpcAmtRate_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max CPC"));
            scrnMsg.C.no(i).termFromMthAot_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Term From(MTH)"));
            scrnMsg.C.no(i).termThruMthAot_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Term To(MTH)"));
            scrnMsg.C.no(i).mdseCd_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Item"));
            scrnMsg.C.no(i).mtrLbDescTxt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Billing Counter Name"));
            scrnMsg.C.no(i).effFromDt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date From"));
            scrnMsg.C.no(i).effThruDt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date To"));
            scrnMsg.C.no(i).prcListBandDescTxt_PC.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price List Band Description")); // 2018/11/17 QC#29147 Add
        }
        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).mdseCd_PD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Item"));
            scrnMsg.D.no(i).mdlNm_PD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Model Name"));
            scrnMsg.D.no(i).addlChrgPrcAmt_PD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price"));
            scrnMsg.D.no(i).effFromDt_PD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date From"));
            scrnMsg.D.no(i).effThruDt_PD.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date To"));
        }
        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).mdlNm_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Model Name"));
            scrnMsg.E.no(i).prcMtrPkgNm_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Meter Package Name"));
            scrnMsg.E.no(i).prcSvcPlnTpCd_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Plan Type"));
            scrnMsg.E.no(i).prcSvcContrTpCd_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Contract Type"));
            scrnMsg.E.no(i).mtrLbCd_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Meter Type"));
            scrnMsg.E.no(i).minCopyVolCnt_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Min Vol"));
            scrnMsg.E.no(i).maxCopyVolCnt_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max Vol"));
            scrnMsg.E.no(i).totBaseAmt_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Total Bace Amount"));
            scrnMsg.E.no(i).splyBaseAmt_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supply Bace Amount"));
            scrnMsg.E.no(i).cpcAmtRate_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Cost Per Copy OVERAGE"));
            scrnMsg.E.no(i).minCpcAmtRate_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Min CPC"));
            scrnMsg.E.no(i).maxCpcAmtRate_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max CPC"));
            scrnMsg.E.no(i).termFromMthAot_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Term From(MTH)"));
            scrnMsg.E.no(i).termThruMthAot_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Term To(MTH)"));
            scrnMsg.E.no(i).mdseCd_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Item"));
            scrnMsg.E.no(i).mtrLbDescTxt_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Billing Counter Name"));
            scrnMsg.E.no(i).prcSvcZoneCd_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Zone(s) Included"));
            scrnMsg.E.no(i).effFromDt_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date From"));
            scrnMsg.E.no(i).effThruDt_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date To"));
            scrnMsg.E.no(i).prcListBandDescTxt_PE.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price List Band Description")); // 2018/11/17 QC#29147 Add
        }
        for (int i = 0; i < scrnMsg.F.length(); i++) {
            scrnMsg.F.no(i).prcLeaseCmpyAbbrNm_PF.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Lease Company Abbreviation"));
            scrnMsg.F.no(i).mdlNm_PF.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Model Name"));
            scrnMsg.F.no(i).minTotFinAmt_PF.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Total Financed Min"));
            scrnMsg.F.no(i).maxTotFinAmt_PF.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Total Financed Max"));
            scrnMsg.F.no(i).termFromMthAot_PF.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Qualifiying Term From"));
            scrnMsg.F.no(i).termThruMthAot_PF.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Qualifiying Term To"));
            scrnMsg.F.no(i).leaseRate_PF.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Lease Rate"));
            scrnMsg.F.no(i).effFromDt_PF.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date From"));
            scrnMsg.F.no(i).effThruDt_PF.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date To"));
        }
        for (int i = 0; i < scrnMsg.G.length(); i++) {
            scrnMsg.G.no(i).prcLeaseCmpyAbbrNm_PG.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Return to Lease Company Abbreviation"));
            scrnMsg.G.no(i).dstMileAmt_PG.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Distannce(Miles)"));
            scrnMsg.G.no(i).rtrnFeeAmt_PG.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Return Fee"));
            scrnMsg.G.no(i).effFromDt_PG.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date From"));
            scrnMsg.G.no(i).effThruDt_PG.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date To"));
        }
        for (int i = 0; i < scrnMsg.H.length(); i++) {
            scrnMsg.H.no(i).mdlNm_PH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service Model Item"));
            scrnMsg.H.no(i).tiAmt_PH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Trade In Value"));
            scrnMsg.H.no(i).fromMtrCopyVolCnt_PH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Meter From"));
            scrnMsg.H.no(i).thruMtrCopyVolCnt_PH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Meter To"));
            scrnMsg.H.no(i).effFromDt_PH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date From"));
            scrnMsg.H.no(i).effThruDt_PH.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date To"));
        }
        for (int i = 0; i < scrnMsg.I.length(); i++) {
            scrnMsg.I.no(i).prcQlfyTpCd_PI.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Qualifier Type"));
            scrnMsg.I.no(i).prcQlfyValTxt_PI.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Value"));
            scrnMsg.I.no(i).qtyDiscPrcAmt_PI.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price"));
            scrnMsg.I.no(i).pkgUomCd_PI.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "UOM"));
            scrnMsg.I.no(i).effFromDt_PI.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date From"));
            scrnMsg.I.no(i).effThruDt_PI.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Date To"));
        }
        for (int i = 0; i < scrnMsg.J.length(); i++) {
            scrnMsg.J.no(i).qtyDiscDtlQty_PJ.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Qty"));
            scrnMsg.J.no(i).pkgUomCd_PJ.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "UOM"));
            scrnMsg.J.no(i).prcBreakAmt_PJ.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Price Break Amount"));
        }

        // Script Message.
        ZYPEZDItemValueSetter.setValue(scrnMsg.exMsgTxt_01, S21MessageFunc.clspGetMessage(NMAM8233I).substring(10));
        ZYPEZDItemValueSetter.setValue(scrnMsg.exMsgTxt_02, S21MessageFunc.clspGetMessage(NMAM8234I).substring(10));

    }

    // Del Start 2017/02/23 QC#16011
//    private void setScrnCtrl(NMAL7010BMsg scrnMsg) {
//
//        int amtNum = scrnMsg.aftDeclPntDigitNum.getValueInt();
//        int rateNum = 5;
//        int aotNum = 0;
//
//        scrnMsg.prcContrNm_H1.setInputProtected(true);
//
//        scrnMsg.prcCatgCd_H2.setInputProtected(true);
//        scrnMsg.prcCatgNm_H2.setInputProtected(true);
//
//        // QC#2175
//        for (int i = 0; i < scrnMsg.W.length(); i++) {
//            scrnMsg.W.no(i).prcCatgNm_SW.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.X.length(); i++) {
//            scrnMsg.X.no(i).xxRecNmTxt_X1.setInputProtected(true);
//            scrnMsg.X.no(i).xxRecNmTxt_X2.setInputProtected(true);
//            scrnMsg.X.no(i).xxRecNmTxt_X3.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.Y.length(); i++) {
//            scrnMsg.Y.no(i).xxRecNmTxt_Y1.setInputProtected(true);
//            scrnMsg.Y.no(i).xxRecNmTxt_Y2.setInputProtected(true);
//        }
//
//        // In Transaction Audience, the name is an input item.
//        for (int i = 0; i < scrnMsg.A.length(); i++) {
//            scrnMsg.A.no(i).prcListEquipPrcAmt_PA.setAppFracDigit(amtNum);
//            scrnMsg.A.no(i).minPrcAmt_PA.setAppFracDigit(amtNum);
//            scrnMsg.A.no(i).maxPrcAmt_PA.setAppFracDigit(amtNum);
//            scrnMsg.A.no(i).prcTermAot_PA.setAppFracDigit(aotNum);
//            scrnMsg.A.no(i).mlyPmtAmt_PA.setAppFracDigit(amtNum);
//            scrnMsg.A.no(i).minLeasePmtAmt_PA.setAppFracDigit(amtNum);
//            scrnMsg.A.no(i).maxLeasePmtAmt_PA.setAppFracDigit(amtNum);
//            scrnMsg.A.no(i).xxCalcTotPrcAmt_PA.setAppFracDigit(amtNum);
//            scrnMsg.A.no(i).quotRevAmt_PA.setAppFracDigit(amtNum);
//
//            scrnMsg.A.no(i).mdseDescShortTxt_PA.setInputProtected(true);
//            // Mod Start 2016/10/17 QC#15193
////            scrnMsg.A.no(i).coaMdseTpNm_PA.setInputProtected(true);
//            scrnMsg.A.no(i).coaProjNm_PA.setInputProtected(true);
//          // Mod End 2016/10/17 QC#15193
//            scrnMsg.A.no(i).mdseItemTpNm_PA.setInputProtected(true);
//            scrnMsg.A.no(i).coaProdNm_PA.setInputProtected(true);
//            scrnMsg.A.no(i).t_MdlNm_PA.setInputProtected(true);
//            // Add #4767 2016/03/22 Start
//            scrnMsg.A.no(i).xxScrItem61Txt_P0.setInputProtected(true);
//            scrnMsg.A.no(i).xxScrItem61Txt_P1.setInputProtected(true);
//            scrnMsg.A.no(i).xxScrItem61Txt_P2.setInputProtected(true);
//            scrnMsg.A.no(i).xxScrItem61Txt_P3.setInputProtected(true);
//            scrnMsg.A.no(i).xxScrItem61Txt_P4.setInputProtected(true);
//            // Add #4767 2016/03/22 End
//
//            scrnMsg.A.no(i).xxCalcTotPrcAmt_PA.setInputProtected(true);
//            scrnMsg.A.no(i).prcFmlaNm_PA.setInputProtected(true);
//            scrnMsg.A.no(i).xxScrStsTxt_A1.setInputProtected(true);
//            scrnMsg.A.no(i).xxFullNm_A1.setInputProtected(true);
//            scrnMsg.A.no(i).cratDt_PA.setInputProtected(true);
//            scrnMsg.A.no(i).xxFullNm_A2.setInputProtected(true);
//            scrnMsg.A.no(i).lastUpdDt_PA.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.B.length(); i++) {
//            scrnMsg.B.no(i).baseAmt_PB.setAppFracDigit(amtNum);
//            scrnMsg.B.no(i).minBaseAmt_PB.setAppFracDigit(amtNum);
//            scrnMsg.B.no(i).maxBaseAmt_PB.setAppFracDigit(amtNum);
//            scrnMsg.B.no(i).cpcAmtRate_PB.setAppFracDigit(rateNum);
//            scrnMsg.B.no(i).minCpcAmtRate_PB.setAppFracDigit(rateNum);
//            scrnMsg.B.no(i).maxCpcAmtRate_PB.setAppFracDigit(rateNum);
//            scrnMsg.B.no(i).termFromMthAot_PB.setAppFracDigit(aotNum);
//            scrnMsg.B.no(i).termThruMthAot_PB.setAppFracDigit(aotNum);
//            scrnMsg.B.no(i).quotRevAmt_PB.setAppFracDigit(amtNum);
//
//            scrnMsg.B.no(i).mdseDescShortTxt_PB.setInputProtected(true);
//            //Mod Start 2016/09/12 QC#11615
////            scrnMsg.B.no(i).mdseNm_PB.setInputProtected(true);
//            scrnMsg.B.no(i).mdseDescShortTxt_PZ.setInputProtected(true);
//            // Mod End 2016/09/12 QC#11615
//            scrnMsg.B.no(i).mtrLbDescTxt_PB.setInputProtected(true);
//            scrnMsg.B.no(i).xxScrStsTxt_B1.setInputProtected(true);
//            scrnMsg.B.no(i).xxFullNm_B1.setInputProtected(true);
//            scrnMsg.B.no(i).cratDt_PB.setInputProtected(true);
//            scrnMsg.B.no(i).xxFullNm_B2.setInputProtected(true);
//            scrnMsg.B.no(i).lastUpdDt_PB.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.C.length(); i++) {
//            scrnMsg.C.no(i).baseAmt_PC.setAppFracDigit(amtNum);
//            scrnMsg.C.no(i).minBaseAmt_PC.setAppFracDigit(amtNum);
//            scrnMsg.C.no(i).maxBaseAmt_PC.setAppFracDigit(amtNum);
//            scrnMsg.C.no(i).cpcAmtRate_PC.setAppFracDigit(rateNum);
//            scrnMsg.C.no(i).minCpcAmtRate_PC.setAppFracDigit(rateNum);
//            scrnMsg.C.no(i).maxCpcAmtRate_PC.setAppFracDigit(rateNum);
//            scrnMsg.C.no(i).termFromMthAot_PC.setAppFracDigit(aotNum);
//            scrnMsg.C.no(i).termThruMthAot_PC.setAppFracDigit(aotNum);
//
//            // Mod Start 2016/09/12 QC#11615
////            scrnMsg.C.no(i).mdseNm_PC.setInputProtected(true);
//            scrnMsg.C.no(i).mdseDescShortTxt_PC.setInputProtected(true);
//            // Mod End 2016/09/12 QC#11615
//            scrnMsg.C.no(i).mtrLbDescTxt_PC.setInputProtected(true);
//            scrnMsg.C.no(i).xxScrStsTxt_C1.setInputProtected(true);
//            scrnMsg.C.no(i).xxFullNm_C1.setInputProtected(true);
//            scrnMsg.C.no(i).cratDt_PC.setInputProtected(true);
//            scrnMsg.C.no(i).xxFullNm_C2.setInputProtected(true);
//            scrnMsg.C.no(i).lastUpdDt_PC.setInputProtected(true);
//        }
//
//        scrnMsg.grsPrcPct_CB.setAppFracDigit(rateNum);
//
//        for (int i = 0; i < scrnMsg.D.length(); i++) {
//            scrnMsg.D.no(i).addlChrgPrcAmt_PD.setAppFracDigit(amtNum);
//
//            // Mod Start 2016/09/12 QC#11615
////            scrnMsg.D.no(i).mdseNm_PD.setInputProtected(true);
//            scrnMsg.D.no(i).mdseDescShortTxt_PD.setInputProtected(true);
//            // Mod End 2016/09/12 QC#11615
//            scrnMsg.D.no(i).xxScrStsTxt_D1.setInputProtected(true);
//            scrnMsg.D.no(i).xxFullNm_D1.setInputProtected(true);
//            scrnMsg.D.no(i).cratDt_PD.setInputProtected(true);
//            scrnMsg.D.no(i).xxFullNm_D2.setInputProtected(true);
//            scrnMsg.D.no(i).lastUpdDt_PD.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.E.length(); i++) {
//            scrnMsg.E.no(i).totBaseAmt_PE.setAppFracDigit(amtNum);
//            scrnMsg.E.no(i).splyBaseAmt_PE.setAppFracDigit(amtNum);
//            scrnMsg.E.no(i).cpcAmtRate_PE.setAppFracDigit(rateNum);
//            scrnMsg.E.no(i).minCpcAmtRate_PE.setAppFracDigit(rateNum);
//            scrnMsg.E.no(i).maxCpcAmtRate_PE.setAppFracDigit(rateNum);
//            scrnMsg.E.no(i).termFromMthAot_PE.setAppFracDigit(aotNum);
//            scrnMsg.E.no(i).termThruMthAot_PE.setAppFracDigit(aotNum);
//
//            // Mod Start 2016/09/12 QC#11615
////            scrnMsg.E.no(i).mdseNm_PE.setInputProtected(true);
//            scrnMsg.E.no(i).mdseDescShortTxt_PE.setInputProtected(true);
//            // Mod End 2016/09/12 QC#11615
//            scrnMsg.E.no(i).mtrLbDescTxt_PE.setInputProtected(true);
//            scrnMsg.E.no(i).splyAgmtPlnNm_PE.setInputProtected(true);
//            scrnMsg.E.no(i).xxScrStsTxt_E1.setInputProtected(true);
//            scrnMsg.E.no(i).xxFullNm_E1.setInputProtected(true);
//            scrnMsg.E.no(i).cratDt_PE.setInputProtected(true);
//            scrnMsg.E.no(i).xxFullNm_E2.setInputProtected(true);
//            scrnMsg.E.no(i).lastUpdDt_PE.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.F.length(); i++) {
//            scrnMsg.F.no(i).minTotFinAmt_PF.setAppFracDigit(amtNum);
//            scrnMsg.F.no(i).maxTotFinAmt_PF.setAppFracDigit(amtNum);
//            scrnMsg.F.no(i).termFromMthAot_PF.setAppFracDigit(aotNum);
//            scrnMsg.F.no(i).termThruMthAot_PF.setAppFracDigit(aotNum);
//            scrnMsg.F.no(i).leaseRate_PF.setAppFracDigit(rateNum);
//
//            scrnMsg.F.no(i).dsAcctNm_PF.setInputProtected(true);
//            scrnMsg.F.no(i).xxScrStsTxt_F1.setInputProtected(true);
//            scrnMsg.F.no(i).xxFullNm_F1.setInputProtected(true);
//            scrnMsg.F.no(i).cratDt_PF.setInputProtected(true);
//            scrnMsg.F.no(i).xxFullNm_F2.setInputProtected(true);
//            scrnMsg.F.no(i).lastUpdDt_PF.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.G.length(); i++) {
//            scrnMsg.G.no(i).dstMileAmt_PG.setAppFracDigit(amtNum);
//            scrnMsg.G.no(i).rtrnFeeAmt_PG.setAppFracDigit(amtNum);
//
//            scrnMsg.G.no(i).xxScrStsTxt_G1.setInputProtected(true);
//            scrnMsg.G.no(i).xxFullNm_G1.setInputProtected(true);
//            scrnMsg.G.no(i).cratDt_PG.setInputProtected(true);
//            scrnMsg.G.no(i).xxFullNm_G2.setInputProtected(true);
//            scrnMsg.G.no(i).lastUpdDt_PG.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.H.length(); i++) {
//            scrnMsg.H.no(i).tiAmt_PH.setAppFracDigit(amtNum);
//
//            scrnMsg.H.no(i).xxScrStsTxt_H1.setInputProtected(true);
//            scrnMsg.H.no(i).xxFullNm_H1.setInputProtected(true);
//            scrnMsg.H.no(i).cratDt_PH.setInputProtected(true);
//            scrnMsg.H.no(i).xxFullNm_H2.setInputProtected(true);
//            scrnMsg.H.no(i).lastUpdDt_PH.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.I.length(); i++) {
//            scrnMsg.I.no(i).qtyDiscPrcAmt_PI.setAppFracDigit(amtNum);
//
//            scrnMsg.I.no(i).prodCtrlNm_PI.setInputProtected(true);
//            // Mod Start 2016/10/17 QC#15193
////            scrnMsg.I.no(i).coaMdseTpNm_PI.setInputProtected(true);
//            scrnMsg.I.no(i).coaProjNm_PI.setInputProtected(true);
//        // Mod End 2016/10/17 QC#15193
//            scrnMsg.I.no(i).mdseItemTpNm_PI.setInputProtected(true);
//            scrnMsg.I.no(i).coaProdNm_PI.setInputProtected(true);
//            scrnMsg.I.no(i).t_MdlNm_PI.setInputProtected(true);
//            scrnMsg.I.no(i).xxFullNm_I1.setInputProtected(true);
//            scrnMsg.I.no(i).cratDt_PI.setInputProtected(true);
//            scrnMsg.I.no(i).xxFullNm_I2.setInputProtected(true);
//            scrnMsg.I.no(i).lastUpdDt_PI.setInputProtected(true);
//        }
//
//        for (int i = 0; i < scrnMsg.J.length(); i++) {
//            scrnMsg.J.no(i).prcBreakAmt_PJ.setAppFracDigit(amtNum);
//        }
//    }
    // Del End 2017/02/23 QC#16011
}
