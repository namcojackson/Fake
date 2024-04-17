/**
 * <Pre>
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * </Pre>
 */
package business.blap.NFDL0080;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFDL0080.common.NFDL0080CommonLogic;
import business.db.AR_ACCT_DTTMsg;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCCmnMethod;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * NFDL0080BL02.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura      Create          N/A
 * 2017/01/04   Fujitsu         T.Murai         Update          QC#16867
 * 2018/04/06   Hitachi         J.Kim           Update          QC#25096
 * 2018/05/11   Matsui          Y.Matsui        Update          QC#25509
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/24   Hitachi         Y.Takeno        Update          QC#26989-1
 * </pre>
 */
public class NFDL0080BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // NFDL0080_INIT
            if ("NFDL0080_INIT".equals(screenAplID)) {
                doProcess_NFDL0080_INIT(cMsg, sMsg);

                // NFDL0080Scrn00_PageNext
            } else if ("NFDL0080Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_PageNext(cMsg, sMsg);

                // NFDL0080Scrn00_PagePrev
            } else if ("NFDL0080Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_PagePrev(cMsg, sMsg);

                // NFDL0080Scrn00_PageJump
            } else if ("NFDL0080Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_PageJump(cMsg, sMsg);

                // NFDL0080Scrn00_CMN_Submit
            } else if ("NFDL0080Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0080_INIT(cMsg, sMsg);

                // NFDL0080Scrn00_Refresh
            } else if ("NFDL0080Scrn00_Refresh".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_Refresh(cMsg, sMsg);

            } else if ("NFDL0080Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_CMN_Download(cMsg, sMsg);

            } else if ("NFDL0080Scrn00_OnChange_ChkBoxA1".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_OnChange_ChkBoxA1(cMsg, sMsg);

            } else if ("NFDL0080Scrn00_OnBlur_ChangeDealApplyAmtNum".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_OnBlur_ChangeDealApplyAmtNum(cMsg, sMsg);

            } else if ("NFDL0080Scrn00_Check_All".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_Check_All(cMsg, sMsg);

            } else if ("NFDL0080Scrn00_Un_Check_All".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_Un_Check_All(cMsg, sMsg);

            // START 2018/07/11 [QC#26989, ADD]
            } else if ("NFDL0080Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFDL0080Scrn00_TBLColumnSort(cMsg, sMsg);
            // END   2018/07/11 [QC#26989, ADD]

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0080_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0080_INIT================================START", this);

        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;

        bizMsg.A.setValidCount(0);

        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;
        globalMsg.clear();
        ZYPTableUtil.clear(globalMsg.A);
        bizMsg.setCommitSMsg(true);

        // GlobalCompanyCode -Get
        bizMsg.glblCmpyCd_H1.setValue(getGlobalCompanyCode());

        // AcctDt -Search
        setArAcctDt(bizMsg);
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }

        if (!setHeaderInfo(bizMsg)) {
            return;
        }
        if (!getDetailData(bizMsg, globalMsg)) {
            return;
        }
        bizMsg.xxPageShowFromNum.setValue(1);
        bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());

        NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, 0);

        setAftDeclPntDigitNum(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0080_INIT================================END", this);
    }

    private boolean setHeaderInfo(NFDL0080CMsg bizMsg) {
        S21SsmEZDResult s21SsmEZDResult = NFDL0080Query.getInstance().findHeaderInfo(bizMsg);
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(s21SsmEZDResult.getResultCode())) {
            bizMsg.setMessageInfo("ZZZM9001E");
            return false;
        }

        Map<String, Object> resMap = (Map<String, Object>) s21SsmEZDResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNum_H1, (String) resMap.get("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctNm_H1, (String) resMap.get("DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.arTrxNum_H1, (String) resMap.get("AR_TRX_NUM"));
        // ZYPEZDItemValueSetter.setValue(bizMsg.crMgrPsnCd_H1, (String) resMap.get("CR_MGR_PSN_CD")); // DEL 2017/01/04 [QC#16867]
        ZYPEZDItemValueSetter.setValue(bizMsg.arTrxTpCd_H1, (String) resMap.get("AR_TRX_TP_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_H1, (String) resMap.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_H1, (String) resMap.get("EZUPTIMEZONE"));

        ZYPEZDItemValueSetter.setValue(bizMsg.billToCustCd_H1, (String) resMap.get("BILL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.arTrxDt_H1, (String) resMap.get("AR_TRX_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum_H1, (String) resMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.custIssPoNum_H1, (String) resMap.get("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.arTrxBillFromDt_H1, (String) resMap.get("AR_TRX_BILL_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.arTrxBillThruDt_H1, (String) resMap.get("AR_TRX_BILL_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dealOrigGrsAmt_H1, (BigDecimal) resMap.get("DEAL_ORIG_GRS_AMT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.invDueDt_H1, (String) resMap.get("INV_DUE_DT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.pastDtAot_H1, (BigDecimal) resMap.get("PAST_DT_AOT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.tocCd_H1, (String) resMap.get("TOC_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaProdCd_H1, (String) resMap.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.arAdjNum_H1, (String) resMap.get("ATT_ADJ_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.arCustRefNum_H1, (String) resMap.get("AR_CUST_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.cashDiscPct_H1, (BigDecimal) resMap.get("CASH_DISC_PCT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.dealCashDiscAmt_H1, (BigDecimal) resMap.get("DEAL_APPLY_CASH_DISC_AMT"));
        if (AR_TRX_TP.RECEIPT.equals(bizMsg.arTrxTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_H1, ((BigDecimal) resMap.get("DEAL_RMNG_BAL_GRS_AMT")).negate());

            s21SsmEZDResult = NFDL0080Query.getInstance().findRcptInfo(bizMsg);
            if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(s21SsmEZDResult.getResultCode())) {
                bizMsg.setMessageInfo("ZZZM9001E");
                return false;
            }
            Map<String, Object> resMapRcpt = (Map<String, Object>) s21SsmEZDResult.getResultObject();

            ZYPEZDItemValueSetter.setValue(bizMsg.rcptNum_H1, (String) resMapRcpt.get("AR_TRX_NUM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.rcptHdrLastUpdTs_H1, (String) resMapRcpt.get("RCPTHDRLASTUPDTS_H1"));
            ZYPEZDItemValueSetter.setValue(bizMsg.rcptHdrTz_H1, (String) resMapRcpt.get("RCPT_HD_TM_ZN_H1"));
            ZYPEZDItemValueSetter.setValue(bizMsg.rcptTrxBalPk_H1, (BigDecimal) resMapRcpt.get("RCPTTRXBALPK_H1"));
            ZYPEZDItemValueSetter.setValue(bizMsg.rcptTrxBalLastUpdTs_H1, (String) resMapRcpt.get("RCPTTRXBALLASTUPDTS_H1"));
            ZYPEZDItemValueSetter.setValue(bizMsg.rcptTrxBalTz_H1, (String) resMapRcpt.get("RCPT_TRX_BAL_TM_ZN_H1"));
            String glDt = (String) resMapRcpt.get("GL_DT");
            ZYPEZDItemValueSetter.setValue(bizMsg.glDt_H1, glDt);
            // START 2018/05/11 [QC#25509, DEL]
            // ZYPEZDItemValueSetter.setValue(bizMsg.glDt_H2, getGlDt(glDt, bizMsg.glDt_H1.getValue()));
            // END   2018/05/11 [QC#25509, DEL]

        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H1, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(bizMsg.dealRmngBalGrsAmt_H1, (BigDecimal) resMap.get("DEAL_RMNG_BAL_GRS_AMT"));
        }

        return true;

    }

    private boolean getDetailData(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H2)) {
            if (!isExistDsAcctReln(bizMsg)) {
                bizMsg.dsAcctNum_H2.setErrorInfo(1, "NFDM0015E");
                return false;
            }
        }

        S21SsmEZDResult s21SsmEZDResult = NFDL0080Query.getInstance().getDetailData(bizMsg, globalMsg);
        if (S21SsmEZDResult.RESULT_CODE_NOT_FOUND.equals(s21SsmEZDResult.getResultCode())
                && !"NFDL0080Scrn00_CMN_Submit".equals(bizMsg.getScreenAplID())) {
            bizMsg.setMessageInfo("ZZZM9001E");
            bizMsg.A.clear();
            bizMsg.A.setValidCount(0);
            return false;
        }
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(s21SsmEZDResult.getResultCode())
                && !"NFDL0080Scrn00_CMN_Submit".equals(bizMsg.getScreenAplID())) {
            bizMsg.setMessageInfo("ZZZM9001E");
            bizMsg.A.clear();
            bizMsg.A.setValidCount(0);
            return false;
        }
        return true;
    }

    private boolean isExistDsAcctReln(NFDL0080CMsg bizMsg) {

        S21SsmEZDResult s21SsmEZDResult = NFDL0080Query.getInstance().findDsAcctReln(bizMsg);
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(s21SsmEZDResult.getResultCode())) {
            return false;
        }

        return true;
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0080Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0080Scrn00_PageNext================================START", this);

        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;

        int paginationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NFDL0080CommonLogic.setCurrentPageIn(bizMsg, globalMsg, paginationFrom);

        int errCnt = 0;

        if (!isCorrelation(bizMsg, globalMsg)) {
            int errPagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
            NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, errPagenationFrom);
            return;

        }

        if (errCnt == 0) {
            paginationFrom = paginationFrom + bizMsg.A.length();
        }

        NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, paginationFrom);

        setAftDeclPntDigitNum(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0080Scrn00_PageNext================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0080Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0080Scrn00_PagePrev================================START", this);

        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;

        int paginationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NFDL0080CommonLogic.setCurrentPageIn(bizMsg, globalMsg, paginationFrom);

        int errCnt = 0;

        if (!isCorrelation(bizMsg, globalMsg)) {
            int errPagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
            NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, errPagenationFrom);
            return;

        }

        if (errCnt == 0) {
            paginationFrom = paginationFrom - bizMsg.A.length();
        }
        NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, paginationFrom);

        setAftDeclPntDigitNum(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0080Scrn00_PagePrev================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0080Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0080Scrn00_PageJump================================START", this);

        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;

        // copy data from SMsg onto CMsg
        int paginationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int paginationFromOriginal = bizMsg.xxPageShowFromNum_H1.getValueInt();
        if (paginationFromOriginal > 0) {
            paginationFromOriginal = paginationFromOriginal - 1;
        }
        NFDL0080CommonLogic.setCurrentPageIn(bizMsg, globalMsg, paginationFromOriginal);

            if (!isCorrelation(bizMsg, globalMsg)) {
                NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, paginationFrom);
                return;

            }

        int i = paginationFrom;
        for (; i < paginationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - paginationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - paginationFrom);

        // set value to pagination items
        bizMsg.xxPageShowFromNum.setValue(paginationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(paginationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(1, "doProcess_NFDL0080Scrn00_PageJump================================END", this);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @return void
     */
    private void setArAcctDt(NFDL0080CMsg bizMsg) {

        String subSysCd = ZYPCodeDataUtil.getVarCharConstValue("AR_SUB_SYS_ID", bizMsg.glblCmpyCd_H1.getValue());

        AR_ACCT_DTTMsg inArAcctDtMsg = new AR_ACCT_DTTMsg();
        inArAcctDtMsg.subSysCd.setValue(subSysCd);
        AR_ACCT_DTTMsg outArAcctDtMsg = NFDL0080CommonLogic.findArAcctDtInfo(bizMsg, inArAcctDtMsg);

        if (outArAcctDtMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.glDt_H1, outArAcctDtMsg.acctDt.getValue());
            // START 2018/05/11 [QC#25509, ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.glDt_H2, outArAcctDtMsg.acctDt.getValue());
            // END   2018/05/11 [QC#25509, ADD]
        } else {
            EZDDebugOutput.println(1, "setArAcctDt() Err:None Data", this);
            bizMsg.setMessageInfo("NFDM0008E", null);
        }
    }


    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @return boolean
     */
    private boolean isCorrelation(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {

        boolean retVal = true;
        int no = -1;

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            // Check Detail
            if (!NFDL0080CommonLogic.chkDtl(bizMsg, globalMsg, i)) {

                if (no == -1) {
                    no = i;
                    bizMsg.xxPageShowFromNum.setValue(NFDL0080CommonLogic.getPagenationFrom(bizMsg, no));
                }

            }

        }

        // In Loop Err Check
        if (no > -1) {
            retVal = false;
        }

        return retVal;
    }

    private void setAftDeclPntDigitNum(NFDL0080CMsg bizMsg) {
        GLBL_CMPYTMsg inArAcctDtMsg = new GLBL_CMPYTMsg();
        GLBL_CMPYTMsg outArAcctDtMsg = NFDL0080CommonLogic.findGlblCmpyInfo(bizMsg, inArAcctDtMsg);

        if (outArAcctDtMsg != null) {
            BigDecimal aftDeclPntDigitNum = NFCCmnMethod.getAftDeclPntDigitNum(bizMsg.glblCmpyCd_H1.getValue(), outArAcctDtMsg.stdCcyCd.getValue());

            bizMsg.aftDeclPntDigitNum_H1.setValue(aftDeclPntDigitNum);

            bizMsg.stdCcyCd_H1.setValue(outArAcctDtMsg.stdCcyCd.getValue());
            bizMsg.ccyCd_H1.setValue(outArAcctDtMsg.stdCcyCd.getValue());
        } else {
            EZDDebugOutput.println(1, "setAftDeclPntDigitNum() Err:None Data", this);
            bizMsg.setMessageInfo("NFDM0008E", null);
        }
    }

    /**
     * 
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NFDL0080Scrn00_Refresh(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0080CMsg bizMsg    = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;
        if (!getDetailData(bizMsg, globalMsg)) {
            return;
        }
        bizMsg.xxPageShowFromNum.setValue(1);
        bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());

        NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, 0);

        // START 2018/04/03 J.Kim [QC#25096,MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H1, BigDecimal.ZERO);
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        setAftDeclPntDigitNum(bizMsg);
    }

    private String getGlDt(String rcptGlDt, String acctDt) {
        String rcptYM = NFDL0080CommonLogic.getYearMonth(rcptGlDt);
        String acctYM = NFDL0080CommonLogic.getYearMonth(acctDt);
        String batProcDt = ZYPDateUtil.getBatProcDate(getGlobalCompanyCode());
        String batProcYM = NFDL0080CommonLogic.getYearMonth(batProcDt);
        String preBatProcYM = NFDL0080CommonLogic.getYearMonth(NFDL0080CommonLogic.getBeforeMonth(batProcDt, "yyyyMMdd"));

        if (acctYM.equals(batProcYM)) {

            if (!batProcYM.equals(rcptYM)) {
                return acctDt;
            } else {
                return rcptGlDt;
            }
        } else {

            if (!batProcYM.equals(rcptYM) && !preBatProcYM.equals(rcptYM)) {
                return acctDt;
            } else {
                return rcptGlDt;
            }
        }
    }

    private void doProcess_NFDL0080Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H2)) {
            if (!isExistDsAcctReln(bizMsg)) {
                bizMsg.dsAcctNum_H2.setErrorInfo(1, "NFDM0015E");
                return;
            }
        }
        NFDL0080Query.getInstance().createDetailCSV(bizMsg);
    }

    private void doProcess_NFDL0080Scrn00_OnChange_ChkBoxA1(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;
        calculateBalanceToApply(bizMsg, globalMsg);
    }

    private void doProcess_NFDL0080Scrn00_OnBlur_ChangeDealApplyAmtNum(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;
        calculateBalanceToApply(bizMsg, globalMsg);
    }

    private void calculateBalanceToApply(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NFDL0080CommonLogic.setCurrentPageIn(bizMsg, globalMsg, pagenationFrom);

        BigDecimal totAmt = BigDecimal.ZERO;
        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
                continue;
            }
            totAmt = totAmt.add(globalMsg.A.no(i).xxDealApplyAmtNum_A1.getValue());
        }
        // START 2018/04/03 J.Kim [QC#25096,MOD]
        if (BigDecimal.ZERO.compareTo(totAmt) != 0) {
            BigDecimal dealRmngBalGrsAmt = bizMsg.dealRmngBalGrsAmt_H1.getValue();
            totAmt = dealRmngBalGrsAmt.add(totAmt);
        }
        // END 2018/04/03 J.Kim [QC#25096,MOD]
        ZYPEZDItemValueSetter.setValue(bizMsg.xxTotAmt_H1, totAmt);
    }

    private void doProcess_NFDL0080Scrn00_Check_All(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NFDL0080CommonLogic.setCurrentPageIn(bizMsg, globalMsg, pagenationFrom);

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            globalMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
        }
        NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        calculateBalanceToApply(bizMsg, globalMsg);
    }

    private void doProcess_NFDL0080Scrn00_Un_Check_All(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        NFDL0080CommonLogic.setCurrentPageIn(bizMsg, globalMsg, pagenationFrom);

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            globalMsg.A.no(i).xxChkBox_A1.clear();
        }
        NFDL0080CommonLogic.setCurrentPageOut(bizMsg, globalMsg, pagenationFrom);
        calculateBalanceToApply(bizMsg, globalMsg);
    }

    // START 2018/07/11 [QC#26989, ADD]
    private void doProcess_NFDL0080Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFDL0080CMsg bizMsg = (NFDL0080CMsg) cMsg;
        NFDL0080SMsg globalMsg = (NFDL0080SMsg) sMsg;

        int cnt = bizMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, globalMsg.A.no(cnt + i), null);
        }

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(globalMsg.A, globalMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.A.getValidCount());

            if (bizMsg.A.getValidCount() > 0) {

                // Copy from SMsg to CMsg
                int i = 0;
                for (; i < globalMsg.A.getValidCount(); i++) {
                    if (i == bizMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                }
                bizMsg.A.setValidCount(i);

                // Page break setting
                bizMsg.xxPageShowFromNum.setValue(1);
                bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            }
        }
    }
    // END   2018/07/11 [QC#26989, ADD]
}
