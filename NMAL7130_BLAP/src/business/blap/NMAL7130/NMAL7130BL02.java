/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7130;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;


import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import business.blap.NMAL7130.common.NMAL7130CommonLogic;
import business.file.NMAL7130F00FMsg;
import business.file.NMAL7130F01FMsg;
import business.file.NMAL7130F02FMsg;
import static business.blap.NMAL7130.constant.NMAL7130Constant.CSV_DOWNLOAD_HEADER_REGD_ACCT;
import static business.blap.NMAL7130.constant.NMAL7130Constant.CSV_DOWNLOAD_HEADER_RELN_PRC_LIST;
import static business.blap.NMAL7130.constant.NMAL7130Constant.CSV_DOWNLOAD_HEADER_TRX_CHRG;
import static business.blap.NMAL7130.constant.NMAL7130Constant.CSV_FILE_EXTENSION;
import static business.blap.NMAL7130.constant.NMAL7130Constant.CSV_FILE_NM_REGD_ACCT;
import static business.blap.NMAL7130.constant.NMAL7130Constant.CSV_FILE_NM_RELN_PRC_LIST;
import static business.blap.NMAL7130.constant.NMAL7130Constant.CSV_FILE_NM_TRX_CHRG;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAL7130_SEPT_CHAR;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM0050E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM8020E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NZZM0000E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.NMAM8121E;
import static business.blap.NMAL7130.constant.NMAL7130Constant.TAB_REGD_ACCT;
import static business.blap.NMAL7130.constant.NMAL7130Constant.TAB_RELN_PRC_LIST;
import static business.blap.NMAL7130.constant.NMAL7130Constant.TAB_TRX_CHRG;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CONTR_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CONTR_PRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CONTR_REB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_TERM_COND_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.REQ_RPT_INTVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RSP_TM_MEAS_PER;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;

/**
 *<pre>
 * NMAL7130BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 * 2016/07/01   Fujitsu         Y.Kanefusa      Update          S21_NA#11221
 *</pre>
 */
public class NMAL7130BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7130CMsg bizMsg = (NMAL7130CMsg) cMsg;
            NMAL7130SMsg glblMsg = (NMAL7130SMsg) sMsg;

            if ("NMAL7130_INIT".equals(screenAplID)) {
                doProcess_NMAL7130_INIT(bizMsg, glblMsg);

            } else if ("NMAL7130Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_CMN_Download(bizMsg);

            } else if ("NMAL7130Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_CMN_Reset(bizMsg, glblMsg);

            } else if ("NMAL7130Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else if ("NMAL7130Scrn00_CopyPrcContr".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_CopyPrcContr(bizMsg);

            } else if ("NMAL7130Scrn00_CratNewVrsn".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_CratNewVrsn(bizMsg);

            } else if ("NMAL7130Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_DeleteRow(bizMsg);

            } else if ("NMAL7130Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_InsertRow(bizMsg);

            } else if ("NMAL7130Scrn00_OnChange_PrcContrChrgTp".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_OnChange_PrcContrChrgTp(bizMsg);

            } else if ("NMAL7130Scrn00_OnChange_PrcTermCondVrsnNum".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_OnChange_PrcTermCondVrsnNum(bizMsg);

            } else if ("NMAL7130Scrn00_OnClick_RegdAcct".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_OnClick_RegdAcct(bizMsg);

            } else if ("NMAL7130Scrn00_OnClick_RelnPrcList".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_OnClick_RelnPrcList(bizMsg);

            } else if ("NMAL7130Scrn00_OpenWin_TrxTp".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_OpenWin_TrxTp(bizMsg);

            } else if ("NMAL7130Scrn00_SrchPrcContr".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_SrchPrcContr(bizMsg, glblMsg);

                // QC#11221 2016/07/01 Add Start
            } else if ("NMAL7130Scrn00_Setting_AcctNm".equals(screenAplID)) {
                doProcess_NMAL7130Scrn00_Setting_AcctNm(bizMsg, glblMsg);
                // QC#11221 2016/07/01 Add End
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7130_INIT(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {

        // Create Pull down.
        createPulldown(bizMsg);

        // Get COA Mdse Type.
        getCoaMdseTp(bizMsg);

        // Get VarCharConst
        ZYPEZDItemValueSetter.setValue(bizMsg.addCharTxt, ZYPCodeDataUtil.getVarCharConstValue(NMAL7130_SEPT_CHAR, getGlobalCompanyCode()));

        if (ZYPCommonFunc.hasValue(bizMsg.prcContrPk_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.prcContrPk_H1, bizMsg.prcContrPk_BK);
            doProcess_NMAL7130Scrn00_SrchPrcContr(bizMsg, glblMsg);
        } else {
            // Initial Set.
            bizMsg.assnPgmContrFlg_H1.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg_H1, ZYPConstant.FLG_ON_Y);
            bizMsg.xxChkBox_AH.clear();
            bizMsg.xxChkBox_BH.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondStsCd_E1, PRC_TERM_COND_STS.INITIAL_DRAFT);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab_H1, TAB_REGD_ACCT);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_CMN_Download(NMAL7130CMsg bizMsg) {

        if (TAB_REGD_ACCT.equals(bizMsg.xxDplyTab_H1.getValue())) {
            download_RegdAcct(bizMsg);
        } else if (TAB_RELN_PRC_LIST.equals(bizMsg.xxDplyTab_H1.getValue())) {
            download_RelnPrcList(bizMsg);
        } else if (TAB_TRX_CHRG.equals(bizMsg.xxDplyTab_H1.getValue())) {
            download_TrxChrg(bizMsg);
        }
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_CMN_Reset(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.D);

        bizMsg.prcContrPk_H1.clear();
        bizMsg.prcContrPk_BK.clear();

        bizMsg.prcContrNm_H1.clear();
        bizMsg.prcContrNum_H1.clear();
        bizMsg.prcContrCustBidNum_H1.clear();
        bizMsg.prcContrShortDescTxt_H1.clear();
        bizMsg.effFromDt_H1.clear();
        bizMsg.effThruDt_H1.clear();
        bizMsg.prcContrVrsnNum_H1.clear();
        bizMsg.prcContrRnwDt_H1.clear();
        bizMsg.prcContrTermMthNum_H1.clear();
        bizMsg.lineBizTpCd_H1.clear();

        bizMsg.xxFullNm_H1.clear();
        bizMsg.cratDt_H1.clear();
        bizMsg.xxFullNm_H2.clear();
        bizMsg.lastUpdDt_H1.clear();

        bizMsg.dsAcctNum_C1.clear();
        bizMsg.dsAcctNm_C1.clear(); // QC#11221 2016/07/01 Add
        bizMsg.initFdRate_C1.clear();

        bizMsg.prcTermCondVrsnNum_E1.clear();
        bizMsg.prcTermCondVrsnNum_L1.clear();
        bizMsg.prcTermCondVrsnNum_L2.clear();

        bizMsg.prcTermCondVrsnNum_E1.clear();
        bizMsg.prcTermCondStsCd_E1.clear();
        bizMsg.ordTrxFlexPct_E1.clear();
        bizMsg.allwDclnMaintFlg_E1.clear();
        bizMsg.mustUseEquipPrcFlg_E1.clear();
        bizMsg.leaseRtrnInclInPrcFlg_E1.clear();
        bizMsg.ovrdSysTonerTpFlg_E1.clear();
        bizMsg.billTonerFrtChrgFlg_E1.clear();
        bizMsg.tonerAlwncPct_E1.clear();
        bizMsg.nonStdStartTm_E1.clear();
        bizMsg.lnrEttlFlg_E1.clear();
        bizMsg.maxDownTmDaysAot_E1.clear();
        bizMsg.lflReplOptFlg_E1.clear();
        bizMsg.lflReplTermNum_E1.clear();
        bizMsg.unltdTngReqFlg_E1.clear();
        bizMsg.custPrvtyFlg_E1.clear();
        bizMsg.hddSvcInclFlg_E1.clear();
        bizMsg.hddCleanPrcGtdFlg_E1.clear();
        bizMsg.tmAndMatUplftTxt_E1.clear();
        bizMsg.docReqFrmAgmtNm_E1.clear();
        bizMsg.mstrAgmtDocNm_E1.clear();
        bizMsg.mstrReplAquFlg_E1.clear();
        bizMsg.mstrReplCmbnPrchFlg_E1.clear();
        bizMsg.mstrReplLeaseFlg_E1.clear();
        bizMsg.leaseTrxAllwFlg_E1.clear();
        bizMsg.supplTermCmpyStdFrmTxt_E1.clear();
        bizMsg.aftHourBillRate_E1.clear();
        bizMsg.rspTmMeasPerCd_E1.clear();
        bizMsg.rspTmComitTxt_E1.clear();
        bizMsg.svcEtaCallReqHrsNum_E1.clear();
        bizMsg.tonerTpNm_E1.clear();
        bizMsg.tonerYieldCnt_E1.clear();
        bizMsg.stplInclSvcFlg_E1.clear();
        bizMsg.prcContrPrcTpCd_E1.clear();
        bizMsg.dlyFirstCallGtdFlg_E1.clear();
        bizMsg.onSiteTechInclFlg_E1.clear();
        bizMsg.primTechInclFlg_E1.clear();
        bizMsg.iwrEsclFlg_E1.clear();
        bizMsg.maxRnwIncrAmtRate_E1.clear();
        bizMsg.maxStdAnnIncrPct_E1.clear();
        bizMsg.erlTrmnOptFlg_E1.clear();
        bizMsg.upTmGtdPct_E1.clear();
        bizMsg.fleetContrAllwFlg_E1.clear();
        bizMsg.aggrContrAllwFlg_E1.clear();
        bizMsg.custQtlyBizRvwReqFlg_E1.clear();
        bizMsg.stdQtlyBizRvwReqFlg_E1.clear();
        bizMsg.reqRptIntvlCd_E1.clear();

        NMAL7130CommonLogic.clearGlblMsg(glblMsg);

        doProcess_NMAL7130_INIT(bizMsg, glblMsg);

    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7130Scrn00_CMN_Submit(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {
        doProcess_NMAL7130Scrn00_SrchPrcContr(bizMsg, glblMsg);
    }

    /**
     * CopyPrcContr Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_CopyPrcContr(NMAL7130CMsg bizMsg) {

        bizMsg.prcContrPk_H1.clear();
        bizMsg.prcContrPk_BK.clear();

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);

        bizMsg.equipRevSumAmt_C1.clear();
        bizMsg.mainUnitSumCnt_C1.clear();

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            bizMsg.D.no(i).prcContrTrxChrgPk_D1.clear();
            bizMsg.D.no(i).ezUpTime_D1.clear();
            bizMsg.D.no(i).ezUpTimeZone_D1.clear();
        }

        bizMsg.prcTermCondVrsnNum_E1.clear();
        bizMsg.prcTermCondVrsnNum_L1.clear();
        bizMsg.prcTermCondVrsnNum_L2.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondStsCd_E1, PRC_TERM_COND_STS.INITIAL_DRAFT);
    }

    /**
     * CratNewVrsn Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_CratNewVrsn(NMAL7130CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().getPrcTermCondVrsn(bizMsg);
        if (ssmResult.isCodeNormal()) {

            // Create Pull down(Version)
            List<BigDecimal> rsltList = (List<BigDecimal>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondVrsnNum_L1.no(i), rsltList.get(i));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondVrsnNum_L2.no(i), rsltList.get(i));
            }

            ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondVrsnNum_E1, rsltList.get(0));

            // Term & Condition(No Record OK)
            ssmResult = NMAL7130Query.getInstance().searchPrcTermCond(bizMsg);
            if (ssmResult.isCodeNormal()) {
                setPrcTermCondVrsn(bizMsg, (Map< ? , ? >) ssmResult.getResultObject());
            }
        }
    }

    /**
     * DeleteRow Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_DeleteRow(NMAL7130CMsg bizMsg) {
        //
    }

    /**
     * InsertRow Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_InsertRow(NMAL7130CMsg bizMsg) {

        if (bizMsg.D.getValidCount() >= bizMsg.D.length()) {
            bizMsg.setMessageInfo(NMAM0050E, new String[] {String.valueOf(bizMsg.D.length())});
            return;
        }

        int idx = bizMsg.D.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).prcContrChrgTpCd_D1, bizMsg.prcContrChrgTpCd_L1.no(0));
        ZYPEZDItemValueSetter.setValue(bizMsg.D.no(idx).effFromDt_D1, ZYPDateUtil.getSalesDate());

        idx++;
        bizMsg.D.setValidCount(idx);
    }

    /**
     * OnChange_PrcContrChrgTp Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_OnChange_PrcContrChrgTp(NMAL7130CMsg bizMsg) {
        //
    }

    /**
     * OnChange_PrcTermCondVrsnNum Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_OnChange_PrcTermCondVrsnNum(NMAL7130CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().searchPrcTermCond(bizMsg);
        if (ssmResult.isCodeNormal()) {
            setPrcTermCondVrsn(bizMsg, (Map< ? , ? >) ssmResult.getResultObject());
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

    }

    /**
     * OnClick_RegdAcct Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_OnClick_RegdAcct(NMAL7130CMsg bizMsg) {
        // Registered Accounts(No Record OK)
        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().searchRegdAcct(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

    }

    /**
     * OnClick_RelnPrcList Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_OnClick_RelnPrcList(NMAL7130CMsg bizMsg) {
        // Related Price Lists(No Record OK)
        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().searchRelnPrcList(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

    }

    /**
     * OpenWin_TrxTp Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL7130Scrn00_OpenWin_TrxTp(NMAL7130CMsg bizMsg) {
        int idx = bizMsg.xxCellIdx.getValueInt();
        if (ZYPCommonFunc.hasValue(bizMsg.D.no(idx).xxRecNmTxt_D1)) {
            NMAL7130CommonLogic.checkOrdTpAndSetCodeBK(bizMsg, bizMsg.D.no(idx).xxRecNmTxt_D1, bizMsg.D.no(idx).xxRecNmTxt_BK);
        }
    }

    /**
     * SrchPrcContr Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7130Scrn00_SrchPrcContr(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg) {

        // Header

        // Table Clear
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        ZYPTableUtil.clear(bizMsg.D);

        // Back up(GlblMsg) Clear
        NMAL7130CommonLogic.clearGlblMsg(glblMsg);

        // Header, Initial Price Points & Summary
        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().searchPrcContr(bizMsg);
        if (ssmResult.isCodeNotFound()) {
//            if (!afterSubmit) {
            bizMsg.setMessageInfo(NZZM0000E);
//            }
            return;
        }

        // Registered Accounts(No Record OK)
        ssmResult = NMAL7130Query.getInstance().searchRegdAcct(bizMsg);

        // Related Price Lists(No Record OK)
        ssmResult = NMAL7130Query.getInstance().searchRelnPrcList(bizMsg);

        // Transaction Charges(No Record OK)
        ssmResult = NMAL7130Query.getInstance().searchTrxChrg(bizMsg);

        // Term & Condition Version (No Record OK)
        ssmResult = NMAL7130Query.getInstance().getPrcTermCondVrsn(bizMsg);
        if (ssmResult.isCodeNormal()) {

            // Create Pull down(Version)
            bizMsg.prcTermCondVrsnNum_L1.clear();
            bizMsg.prcTermCondVrsnNum_L2.clear();

            List<BigDecimal> rsltList = (List<BigDecimal>) ssmResult.getResultObject();
            for (int i = 0; i < rsltList.size(); i++) {
                ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondVrsnNum_L1.no(i), rsltList.get(i));
                ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondVrsnNum_L2.no(i), rsltList.get(i));
            }

            // First Record : Blank.
            ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondVrsnNum_E1, rsltList.get(0));

            // Term & Condition(No Record OK)
            ssmResult = NMAL7130Query.getInstance().searchPrcTermCond(bizMsg);
            setPrcTermCondVrsn(bizMsg, (Map< ? , ? >) ssmResult.getResultObject());
        }

        NMAL7130CommonLogic.copyFromBizMsgToGlblMsg(bizMsg, glblMsg);

    }

    // QC#11221 2016/07/01 Add Start
    private void doProcess_NMAL7130Scrn00_Setting_AcctNm(NMAL7130CMsg bizMsg, NMAL7130SMsg glblMsg){
        bizMsg.dsAcctNm_C1.clear();
        if(!ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_C1)){
            return;
        }
        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().getLeaseAcct(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.dsAcctNum_C1.setErrorInfo(1, NMAM8121E, new String[] {"Leasing Company" });
            return;
        }
        List<Map<String, String>> rsltAcctList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_C1, (String) rsltAcctList.get(0).get("DS_ACCT_NM"));
        
    }
    // QC#11221 2016/07/01 Add End

    private void createPulldown(NMAL7130CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_L1, bizMsg.lineBizTpDescTxt_L1);

        // QC#11221 2016/07/01 Del Start
        //S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().getLeaseAcct(bizMsg);
        //if (ssmResult.isCodeNotFound()) {
        //    bizMsg.setMessageInfo(NMAM8020E);
        //    return;
        //}
        // List<Map<String, String>> rsltAcctList = (List<Map<String, String>>) ssmResult.getResultObject();
        // int idx = 0;
        // for (Map<String, String> rsltMap : rsltAcctList) {
        //     ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_L1.no(idx), (String) rsltMap.get("DS_ACCT_NUM"));
        //     ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_L1.no(idx), (String) rsltMap.get("DS_ACCT_NM"));
        //     idx++;
        // }
        // QC#11221 2016/07/01 Del End

        ZYPCodeDataUtil.createPulldownList(PRC_CONTR_CHRG_TP.class, bizMsg.prcContrChrgTpCd_L1, bizMsg.prcContrChrgTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_CONTR_REB_TP.class, bizMsg.prcContrRebTpCd_L1, bizMsg.prcContrRebTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_TERM_COND_STS.class, bizMsg.prcTermCondStsCd_L1, bizMsg.prcTermCondStsDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(RSP_TM_MEAS_PER.class, bizMsg.rspTmMeasPerCd_L1, bizMsg.rspTmMeasPerDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(PRC_CONTR_PRC_TP.class, bizMsg.prcContrPrcTpCd_L1, bizMsg.prcContrPrcTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(REQ_RPT_INTVL.class, bizMsg.reqRptIntvlCd_L1, bizMsg.reqRptIntvlDescTxt_L1);
    }

    private void setPrcTermCondVrsn(NMAL7130CMsg bizMsg, Map< ? , ? > rsltMap) {
        ZYPEZDItemValueSetter.setValue(bizMsg.prcTermCondStsCd_E1, (String) rsltMap.get("PRC_TERM_COND_STS_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ordTrxFlexPct_E1, (BigDecimal) rsltMap.get("ORD_TRX_FLEX_PCT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.allwDclnMaintFlg_E1, (String) rsltMap.get("ALLW_DCLN_MAINT_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.mustUseEquipPrcFlg_E1, (String) rsltMap.get("MUST_USE_EQUIP_PRC_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.leaseRtrnInclInPrcFlg_E1, (String) rsltMap.get("LEASE_RTRN_INCL_IN_PRC_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ovrdSysTonerTpFlg_E1, (String) rsltMap.get("OVRD_SYS_TONER_TP_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.billTonerFrtChrgFlg_E1, (String) rsltMap.get("BILL_TONER_FRT_CHRG_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.tonerAlwncPct_E1, (BigDecimal) rsltMap.get("TONER_ALWNC_PCT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.nonStdStartTm_E1, (String) rsltMap.get("NON_STD_START_TM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.lnrEttlFlg_E1, (String) rsltMap.get("LNR_ETTL_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.maxDownTmDaysAot_E1, (BigDecimal) rsltMap.get("MAX_DOWN_TM_DAYS_AOT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.lflReplOptFlg_E1, (String) rsltMap.get("LFL_REPL_OPT_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.lflReplTermNum_E1, (BigDecimal) rsltMap.get("LFL_REPL_TERM_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.unltdTngReqFlg_E1, (String) rsltMap.get("UNLTD_TNG_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.custPrvtyFlg_E1, (String) rsltMap.get("CUST_PRVTY_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.hddSvcInclFlg_E1, (String) rsltMap.get("HDD_SVC_INCL_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.hddCleanPrcGtdFlg_E1, (String) rsltMap.get("HDD_CLEAN_PRC_GTD_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.tmAndMatUplftTxt_E1, (String) rsltMap.get("TM_AND_MAT_UPLFT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.docReqFrmAgmtNm_E1, (String) rsltMap.get("DOC_REQ_FRM_AGMT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.mstrAgmtDocNm_E1, (String) rsltMap.get("MSTR_AGMT_DOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.mstrReplAquFlg_E1, (String) rsltMap.get("MSTR_REPL_AQU_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.mstrReplCmbnPrchFlg_E1, (String) rsltMap.get("MSTR_REPL_CMBN_PRCH_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.mstrReplLeaseFlg_E1, (String) rsltMap.get("MSTR_REPL_LEASE_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.leaseTrxAllwFlg_E1, (String) rsltMap.get("LEASE_TRX_ALLW_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.supplTermCmpyStdFrmTxt_E1, (String) rsltMap.get("SUPPL_TERM_CMPY_STD_FRM_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.aftHourBillRate_E1, (BigDecimal) rsltMap.get("AFT_HOUR_BILL_RATE"));
        ZYPEZDItemValueSetter.setValue(bizMsg.rspTmMeasPerCd_E1, (String) rsltMap.get("RSP_TM_MEAS_PER_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.rspTmComitTxt_E1, (String) rsltMap.get("RSP_TM_COMIT_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.svcEtaCallReqHrsNum_E1, (BigDecimal) rsltMap.get("SVC_ETA_CALL_REQ_HRS_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.tonerTpNm_E1, (String) rsltMap.get("TONER_TP_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.tonerYieldCnt_E1, (BigDecimal) rsltMap.get("TONER_YIELD_CNT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.stplInclSvcFlg_E1, (String) rsltMap.get("STPL_INCL_SVC_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.prcContrPrcTpCd_E1, (String) rsltMap.get("PRC_CONTR_PRC_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dlyFirstCallGtdFlg_E1, (String) rsltMap.get("DLY_FIRST_CALL_GTD_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.onSiteTechInclFlg_E1, (String) rsltMap.get("ON_SITE_TECH_INCL_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.primTechInclFlg_E1, (String) rsltMap.get("PRIM_TECH_INCL_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.iwrEsclFlg_E1, (String) rsltMap.get("IWR_ESCL_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.maxRnwIncrAmtRate_E1, (BigDecimal) rsltMap.get("MAX_RNW_INCR_AMT_RATE"));
        ZYPEZDItemValueSetter.setValue(bizMsg.maxStdAnnIncrPct_E1, (BigDecimal) rsltMap.get("MAX_STD_ANN_INCR_PCT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.erlTrmnOptFlg_E1, (String) rsltMap.get("ERL_TRMN_OPT_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.upTmGtdPct_E1, (BigDecimal) rsltMap.get("UP_TM_GTD_PCT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.fleetContrAllwFlg_E1, (String) rsltMap.get("FLEET_CONTR_ALLW_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.aggrContrAllwFlg_E1, (String) rsltMap.get("AGGR_CONTR_ALLW_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.custQtlyBizRvwReqFlg_E1, (String) rsltMap.get("CUST_QTLY_BIZ_RVW_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.stdQtlyBizRvwReqFlg_E1, (String) rsltMap.get("STD_QTLY_BIZ_RVW_REQ_FLG"));
        ZYPEZDItemValueSetter.setValue(bizMsg.reqRptIntvlCd_E1, (String) rsltMap.get("REQ_RPT_INTVL_CD"));

        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_E1, (String) rsltMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_E1, (String) rsltMap.get("EZUPTIMEZONE"));
    }

    private void getCoaMdseTp(NMAL7130CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NMAL7130Query.getInstance().getCoaMdseTp(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NMAM8020E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.coaMdseTpCd_P1, (String) ssmResult.getResultObject());
    }

    private void download_RegdAcct(NMAL7130CMsg bizMsg) {
        bizMsg.xxFileData.clear();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM_REGD_ACCT), CSV_FILE_EXTENSION);
        NMAL7130F00FMsg fMsg = new NMAL7130F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER_REGD_ACCT);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrPk_H1, bizMsg.prcContrPk_BK);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm_H1, bizMsg.prcContrNm_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum_H1, bizMsg.prcContrNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrCustBidNum_H1, bizMsg.prcContrCustBidNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.assnPgmContrFlg_H1, bizMsg.assnPgmContrFlg_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrShortDescTxt_H1, bizMsg.prcContrShortDescTxt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrVrsnNum_H1, bizMsg.prcContrVrsnNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrRnwDt_H1, bizMsg.prcContrRnwDt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrTermMthNum_H1, bizMsg.prcContrTermMthNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpDescTxt_H1, ZYPCodeDataUtil.getName(LINE_BIZ_TP.class, getGlobalCompanyCode(), bizMsg.lineBizTpCd_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_H1, bizMsg.effFromDt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.effThruDt_H1, bizMsg.effThruDt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg_H1, bizMsg.actvFlg_H1);

            EZDMsg.copy(bizMsg.A.no(i), null, fMsg, null);

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void download_RelnPrcList(NMAL7130CMsg bizMsg) {
        bizMsg.xxFileData.clear();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM_RELN_PRC_LIST), CSV_FILE_EXTENSION);
        NMAL7130F01FMsg fMsg = new NMAL7130F01FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER_RELN_PRC_LIST);

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrPk_H1, bizMsg.prcContrPk_BK);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm_H1, bizMsg.prcContrNm_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum_H1, bizMsg.prcContrNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrCustBidNum_H1, bizMsg.prcContrCustBidNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.assnPgmContrFlg_H1, bizMsg.assnPgmContrFlg_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrShortDescTxt_H1, bizMsg.prcContrShortDescTxt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrVrsnNum_H1, bizMsg.prcContrVrsnNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrRnwDt_H1, bizMsg.prcContrRnwDt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrTermMthNum_H1, bizMsg.prcContrTermMthNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpDescTxt_H1, ZYPCodeDataUtil.getName(LINE_BIZ_TP.class, getGlobalCompanyCode(), bizMsg.lineBizTpCd_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_H1, bizMsg.effFromDt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.effThruDt_H1, bizMsg.effThruDt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg_H1, bizMsg.actvFlg_H1);

            EZDMsg.copy(bizMsg.B.no(i), null, fMsg, null);

            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void download_TrxChrg(NMAL7130CMsg bizMsg) {
        bizMsg.xxFileData.clear();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM_TRX_CHRG), CSV_FILE_EXTENSION);
        NMAL7130F02FMsg fMsg = new NMAL7130F02FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER_TRX_CHRG);
        S21SsmEZDResult ssmResult = null;

        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrPk_H1, bizMsg.prcContrPk_BK);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNm_H1, bizMsg.prcContrNm_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrNum_H1, bizMsg.prcContrNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrCustBidNum_H1, bizMsg.prcContrCustBidNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.assnPgmContrFlg_H1, bizMsg.assnPgmContrFlg_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrShortDescTxt_H1, bizMsg.prcContrShortDescTxt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrVrsnNum_H1, bizMsg.prcContrVrsnNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrRnwDt_H1, bizMsg.prcContrRnwDt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.prcContrTermMthNum_H1, bizMsg.prcContrTermMthNum_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.lineBizTpDescTxt_H1, ZYPCodeDataUtil.getName(LINE_BIZ_TP.class, getGlobalCompanyCode(), bizMsg.lineBizTpCd_H1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_H1, bizMsg.effFromDt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.effThruDt_H1, bizMsg.effThruDt_H1);
            ZYPEZDItemValueSetter.setValue(fMsg.actvFlg_H1, bizMsg.actvFlg_H1);

            EZDMsg.copy(bizMsg.D.no(i), null, fMsg, null);

            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).prcContrChrgTpCd_D1)) {
                ssmResult = NMAL7130Query.getInstance().getAnyColmn("PRC_CONTR_CHRG_TP_DESC_TXT", "PRC_CONTR_CHRG_TP", "PRC_CONTR_CHRG_TP_CD", bizMsg.D.no(i).prcContrChrgTpCd_D1.getValue());
                if (ssmResult.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(fMsg.prcContrChrgTpDescTxt_D1, (String) ssmResult.getResultObject());
                } else {
                    fMsg.prcContrChrgTpDescTxt_D1.clear();
                }
            }

            if (ZYPCommonFunc.hasValue(bizMsg.D.no(i).prcContrRebTpCd_D1)) {
                ssmResult = NMAL7130Query.getInstance().getAnyColmn("PRC_CONTR_REB_TP_DESC_TXT", "PRC_CONTR_REB_TP", "PRC_CONTR_REB_TP_CD", bizMsg.D.no(i).prcContrRebTpCd_D1.getValue());
                if (ssmResult.isCodeNormal()) {
                    ZYPEZDItemValueSetter.setValue(fMsg.prcContrRebTpDescTxt_D1, (String) ssmResult.getResultObject());
                } else {
                    fMsg.prcContrRebTpDescTxt_D1.clear();
                }
            }

            csvOutFile.write();
        }

        csvOutFile.close();
    }
}
