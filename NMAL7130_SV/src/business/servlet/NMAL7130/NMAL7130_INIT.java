/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130;

import static business.servlet.NMAL7130.constant.NMAL7130Constant.BIZ_ID;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.REG_AUTHORITY;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.SCRN_ID_00;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.ZZM9000E;
import static business.servlet.NMAL7130.constant.NMAL7130Constant.ZZM9004E;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7130.NMAL7130CMsg;
import business.servlet.NMAL7130.common.NMAL7130CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7130_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7130_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

        // Function
        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;
        S21UserProfileService userProfileService = getUserProfileService();
         List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);

         for (String functionId : functionIds) {
             if (REG_AUTHORITY.equals(functionId)) {
                 ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, ZYPConstant.FLG_ON_Y);
                 break;
             }
         }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;
        NMAL7130CMsg bizMsg = new NMAL7130CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null) {
            if (params[0] instanceof EZDBBigDecimalItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrPk_BK, (EZDBBigDecimalItem) params[0]);
            } else if (params[0] instanceof BigDecimal) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prcContrPk_BK, (BigDecimal) params[0]);
            } else {
                scrnMsg.setMessageInfo(ZZM9004E, new String[] {scrnMsg.prcContrPk_H1.getNameForMessage()});
                return null;
            }
            if (params.length >= 1) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.prcContrPk_BK)) {
                    scrnMsg.setMessageInfo(ZZM9000E, new String[] {scrnMsg.prcContrPk_H1.getNameForMessage()});
                    return null;
                }
            }
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            NMAL7130CommonLogic.initCmnBtnProp(this, ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxYesNoCd.getValue()));
            NMAL7130CommonLogic.initScrnProtect(this, scrnMsg);
            return;
        }
        NMAL7130CMsg bizMsg = (NMAL7130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7130CommonLogic.initCmnBtnProp(this, ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxYesNoCd.getValue()));
        NMAL7130CommonLogic.initScrnProtect(this, scrnMsg);

        setAppFracDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.prcContrNm_H1);

        NMAL7130CommonLogic.setCmnBtnProp(this, BTN_CMN_DWL, 1);
        NMAL7130CommonLogic.setTermCondProtect(this, scrnMsg);
        NMAL7130CommonLogic.setAttProtect(this, scrnMsg);

        NMAL7130CommonLogic.setRowsBGWithClear(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7130BMsg scrnMsg = (NMAL7130BMsg) bMsg;
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.prcContrPk_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Contract ID"));
        scrnMsg.prcContrPk_BK.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Contract ID"));
        scrnMsg.prcContrNm_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Contract Name"));
        scrnMsg.prcContrNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Contract#"));
        scrnMsg.prcContrCustBidNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Bid#"));
        scrnMsg.effFromDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Start Date"));
        scrnMsg.effThruDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "End Date"));
        scrnMsg.prcContrVrsnNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Version"));
        scrnMsg.prcContrRnwDt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Renewed Date"));
        scrnMsg.prcContrTermMthNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Term"));
        scrnMsg.lineBizTpCd_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Line of Business"));

        scrnMsg.assnPgmContrFlg_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "CSAP Contract"));
        scrnMsg.actvFlg_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Active"));
        scrnMsg.prcContrTermMthNum_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Term"));
        scrnMsg.prcContrShortDescTxt_H1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Brief Description"));

        scrnMsg.dsAcctNum_C1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Lease Company"));
        scrnMsg.initFdRate_C1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Initial Funding Rate"));

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).xxChkBox_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Check Box"));
            scrnMsg.D.no(i).prcContrChrgTpCd_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Charge Type"));
            scrnMsg.D.no(i).mdseCd_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Item Code"));
            scrnMsg.D.no(i).prcContrTrxChrgPct_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Rebate%"));
            scrnMsg.D.no(i).prcContrTrxChrgAmt_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Admin Fee"));
            scrnMsg.D.no(i).prcContrTrxChrgNm_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Charge Name"));
            scrnMsg.D.no(i).xxRecNmTxt_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Transaction Type"));
            scrnMsg.D.no(i).dsAcctNum_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Special Qualifying Account#"));
            scrnMsg.D.no(i).dsAcctNm_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Account Name"));
            scrnMsg.D.no(i).effFromDt_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Start Date"));
            scrnMsg.D.no(i).effThruDt_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "End Date"));
            scrnMsg.D.no(i).prcContrRebTpCd_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Rebate Type"));
            scrnMsg.D.no(i).appReqFlg_D1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Required"));
        }

        scrnMsg.prcTermCondVrsnNum_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Version"));
        scrnMsg.prcTermCondStsCd_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Status"));
        scrnMsg.ordTrxFlexPct_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Flexibility Percentage"));
        scrnMsg.allwDclnMaintFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Allow Decline Maintenance"));
        scrnMsg.mustUseEquipPrcFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Must Use Equipment Pricing"));
        scrnMsg.leaseRtrnInclInPrcFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Lease Returns Included in Pricing"));
        scrnMsg.ovrdSysTonerTpFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Override System Toner Type"));
        scrnMsg.billTonerFrtChrgFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Bill Toner Freight Charges"));
        scrnMsg.tonerAlwncPct_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Toner Allowance Percentage"));
        scrnMsg.nonStdStartTm_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Non Standard Start Time"));
        scrnMsg.lnrEttlFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Loaner Entitlement"));
        scrnMsg.maxDownTmDaysAot_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max Downtime to Ship Loaner (in Days)"));
        scrnMsg.lflReplOptFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Like for Like Replacement Option"));
        scrnMsg.lflReplTermNum_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Like for Like Replacement Term (in months)"));
        scrnMsg.unltdTngReqFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Unlimited Training Required"));
        scrnMsg.custPrvtyFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Cusomer Productivity (CPP)"));
        scrnMsg.hddSvcInclFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "HDD Services Included"));
        scrnMsg.hddCleanPrcGtdFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "HDD Cleaning Price Guarantee"));
        scrnMsg.tmAndMatUplftTxt_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Time & Materials Uplift"));
        scrnMsg.docReqFrmAgmtNm_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Form of Agreement"));
        scrnMsg.mstrAgmtDocNm_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Master Agreement Doc Name"));
        scrnMsg.mstrReplAquFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "MAA Replaces Acquisition Agreement"));
        scrnMsg.mstrReplCmbnPrchFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "MAA Replaces Combined Purchase Agreement"));
        scrnMsg.mstrReplLeaseFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "MAA Replaces Unified Lease Agreement"));
        scrnMsg.leaseTrxAllwFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Lease Transaction Allowed"));
        scrnMsg.supplTermCmpyStdFrmTxt_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Supplemental Terms to Company Standard Forms"));
        scrnMsg.aftHourBillRate_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "After Hrs Bill Rate"));
        scrnMsg.rspTmMeasPerCd_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Response Time Measurement Period"));
        scrnMsg.rspTmComitTxt_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Response Time Commitment"));
        scrnMsg.svcEtaCallReqHrsNum_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Service ETA Call Required (in Hours)"));
        scrnMsg.tonerTpNm_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Toner Type"));
        scrnMsg.tonerYieldCnt_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Toner Yield"));
        scrnMsg.stplInclSvcFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Staples Included Service"));
        scrnMsg.prcContrPrcTpCd_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Contract Price Type"));
        scrnMsg.dlyFirstCallGtdFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Daily First Call Guarantee"));
        scrnMsg.onSiteTechInclFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Onsite Tech Included"));
        scrnMsg.primTechInclFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Primary Tech Included"));
        scrnMsg.iwrEsclFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "imageWARE Escalator"));
        scrnMsg.maxRnwIncrAmtRate_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max Renewal Rate Increase"));
        scrnMsg.maxStdAnnIncrPct_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Max Std Annual Increase Percentage"));
        scrnMsg.erlTrmnOptFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Early Termination Option"));
        scrnMsg.upTmGtdPct_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Uptime Percentage Guarantee"));
        scrnMsg.fleetContrAllwFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Fleet Contracts Allowed"));
        scrnMsg.aggrContrAllwFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Aggregate Contracts Allowed"));
        scrnMsg.custQtlyBizRvwReqFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Canon Customer QBR Reporting Required"));
        scrnMsg.stdQtlyBizRvwReqFlg_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Canon Standard QBR Reporting Require"));
        scrnMsg.reqRptIntvlCd_E1.setNameForMessage(labelConv.convLabel2i18nLabel(SCRN_ID_00, "Required Reporting Interval"));

    }

    private void setAppFracDigit(NMAL7130BMsg scrnMsg) {

        final int int4 = 4;
        final int int5 = 5;

        scrnMsg.initFdRate_C1.setAppFracDigit(int5);

        scrnMsg.equipRevSumAmt_C1.setAppFracDigit(2);
        scrnMsg.mainUnitSumCnt_C1.setAppFracDigit(0);

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).prcContrTrxChrgPct_D1.setAppFracDigit(int4);
            scrnMsg.D.no(i).prcContrTrxChrgAmt_D1.setAppFracDigit(2);
        }
        scrnMsg.prcTermCondVrsnNum_E1.setAppFracDigit(0);

        scrnMsg.ordTrxFlexPct_E1.setAppFracDigit(int5);
        scrnMsg.tonerAlwncPct_E1.setAppFracDigit(2);
        scrnMsg.maxDownTmDaysAot_E1.setAppFracDigit(0);
        scrnMsg.lflReplTermNum_E1.setAppFracDigit(0);

        scrnMsg.aftHourBillRate_E1.setAppFracDigit(2);
        scrnMsg.svcEtaCallReqHrsNum_E1.setAppFracDigit(2);
        scrnMsg.tonerYieldCnt_E1.setAppFracDigit(0);
        scrnMsg.maxRnwIncrAmtRate_E1.setAppFracDigit(2);
        scrnMsg.maxStdAnnIncrPct_E1.setAppFracDigit(2);
        scrnMsg.upTmGtdPct_E1.setAppFracDigit(2);
    }
}
