package business.blap.NFDL0030;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0030.constant.NFDL0030Constant;
import business.blap.NFDL0030.NFDL0030Query;
import business.db.AR_ACCT_DTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SUB_SYS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/14   Hitachi         T.Tsuchida      Update          QC#9829
 * 2018/01/18   Fujitsu         T.Murai         Update          QC#20563
 * 2018/05/15   Hitachi         E.Kameishi      Update          QC#24833
 * 2018/06/15   Hitachi         Y.Takeno        Update          QC#26239
 * 2019/02/07   Fujitsu         S.Ohki          Update          QC#30023
 * 2019/03/04   Hitachi         Y.Takeno        Update          QC#30608
 * 2020/01/29   CITS            M.Furugoori     Update          QC#55017
 * 2020/03/03   Fujitsu         H.Mizukami      Update          QC#55664
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFDL0030BL02 extends S21BusinessHandler implements NFDL0030Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFDL0030_INIT".equals(screenAplID)) {
                doProcess_NFDL0030_INIT(cMsg, sMsg);
            } else if ("NFDL0030_NWAL2010".equals(screenAplID)) {
                doProcess_NFDL0030_NWAL2010(cMsg, sMsg);
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            } else if ("NFDL0030_NFCL3170".equals(screenAplID)) {
                doProcess_NFDL0030_NFCL3170(cMsg, sMsg);
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            } else if ("NFDL0030Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFDL0030Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFDL0030Scrn00_CMN_Submit".equals(screenAplID)) {
                //doProcess_NFDL0030Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFDL0030Scrn00_AddLine".equals(screenAplID)) {
                doProcess_NFDL0030Scrn00_AddLine(cMsg, sMsg);
            // START 2023/03/10 S.Nakatani [QC#55645,ADD]
            } else if ("NFDL0030Scrn00_AddOnAcctLine".equals(screenAplID)) {
                doProcess_NFDL0030Scrn00_AddLine(cMsg, sMsg);
            // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            } else if ("NFDL0030Scrn00_DeleteLine".equals(screenAplID)) {
                doProcess_NFDL0030Scrn00_DeleteLine(cMsg, sMsg);
            } else if ("NFDL0030Scrn00_SearchInvoice".equals(screenAplID)) {
                doProcess_NFDL0030Scrn00_SearchInvoice(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0030_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0030_INIT================================START", this);

        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;

        // START 2023/03/10 S.Nakatani [QC#55645,ADD]
        createPmtMethPullDown(bizMsg);

        setPayerName(bizMsg);
        // END 2023/03/10 S.Nakatani [QC#55645,ADD]

        if (bizMsg.xxModeCd.getValue().equals(MODE_ON_ACCOUNT)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H)) {
                bizMsg.setMessageInfo("NFCM0041E");
            }
        } else {
            if (!ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H)) {
                bizMsg.setMessageInfo("NFCM0041E");
            }
            // START 2020/01/29 [QC#55017, MOD]
            // if (!ZYPCommonFunc.hasValue(bizMsg.xxTrxCdSrchTxt)) {
            if (!ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt)) {
                bizMsg.setMessageInfo("NFCM0041E");
            }
            // END   2020/01/29 [QC#55017, MOD]
        }

        // START 2019/03/04 [QC#30608, ADD]
        ZYPTableUtil.clear(bizMsg.A);
        // END   2019/03/04 [QC#30608, ADD]

        AR_ACCT_DTTMsg arAcctDtInMsg = new AR_ACCT_DTTMsg();
        ZYPEZDItemValueSetter.setValue(arAcctDtInMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(arAcctDtInMsg.subSysCd, SUB_SYS.NFC);
        ZYPEZDItemValueSetter.setValue(arAcctDtInMsg.onlBatTpCd, "1");

        AR_ACCT_DTTMsg arAcctDtOutMsg = (AR_ACCT_DTTMsg) EZDTBLAccessor.findByKey(arAcctDtInMsg);

        if (arAcctDtOutMsg == null) {
            bizMsg.setMessageInfo("NFCM0041E");
        } else if (!ZYPCommonFunc.hasValue(arAcctDtOutMsg.acctDt)) {
            bizMsg.setMessageInfo("NFCM0041E");
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.acctDt_H, arAcctDtOutMsg.acctDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.acctDt_BK, arAcctDtOutMsg.acctDt);
            ZYPEZDItemValueSetter.setValue(bizMsg.acctYrMth_BK, arAcctDtOutMsg.acctYrMth);
        }

        if (bizMsg.xxModeCd.getValue().equals(MODE_INVOICE)) {
            Map<String, Object> prmMap = new HashMap<String, Object>();
            prmMap.put("glblCmpyCd", getGlobalCompanyCode());
            // START 2020/01/29 [QC#55017, MOD]
            // prmMap.put("invNumList", bizMsg.xxTrxCdSrchTxt.getValue().split(","));
            prmMap.put("invNumList", bizMsg.xxTrxNumSrchTxt.getValue().split(","));
            // END   2020/01/29 [QC#55017, MOD]
            S21SsmEZDResult arTrxBal =  NFDL0030Query.getInstance().getArTrxBalList(prmMap, bizMsg);
    
            if (arTrxBal.isCodeNormal()) {
                List<Map<String, Object>> arTrxBalList = (List<Map<String, Object>>) arTrxBal.getResultObject();
                int i = 0;
                for ( ; i < arTrxBalList.size() && i < bizMsg.A.length(); i++) {
                    String arTrxNum = (String) arTrxBalList.get(i).get("AR_TRX_NUM");
                    BigDecimal dealRmngBalGrsAmt = (BigDecimal) arTrxBalList.get(i).get("DEAL_RMNG_BAL_GRS_AMT");
                    BigDecimal arTrxBalPk = (BigDecimal) arTrxBalList.get(i).get("AR_TRX_BAL_PK");
                    String ezUpTime = (String) arTrxBalList.get(i).get("EZUPTIME");
                    String ezUpTimeZone = (String) arTrxBalList.get(i).get("EZUPTIMEZONE");
    
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).arTrxNum_A1, arTrxNum);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealRmngBalGrsAmt_A1, dealRmngBalGrsAmt);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).arTrxBalPk_A1, arTrxBalPk);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTime_A1, ezUpTime);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).ezUpTimeZone_A1, ezUpTimeZone);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).acctDt_A1, bizMsg.acctDt_H);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).arTrxNum_BK, arTrxNum);

                    // Add Start 2018/01/16 S21_NA#20563
                    BigDecimal dealApplyAdjRsvdAmt = (BigDecimal) arTrxBalList.get(0).get("DEAL_APPLY_ADJ_RSVD_AMT");
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).dealApplyAdjRsvdAmt_A1, dealApplyAdjRsvdAmt);
                    // Add End 2018/01/16 S21_NA#20563

                    // START 2018/06/15 [QC#26239, ADD]
                    String arTrxTpCd = (String) arTrxBalList.get(i).get("AR_TRX_TP_CD");
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i).arTrxTpCd_A1, arTrxTpCd);
                    // END   2018/06/15 [QC#26239, ADD]
                }
                bizMsg.A.setValidCount(i);
            } else {
                bizMsg.setMessageInfo("NFCM0041E");
            }
        }
        EZDDebugOutput.println(1, "doProcess_NFDL0030_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0030Scrn00_AddLine(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_AddLine================================START", this);

        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;

        if (bizMsg.A.getValidCount() == bizMsg.A.length()) {
            bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(bizMsg.A.length()) });
        } else {
        	ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).acctDt_A1, bizMsg.acctDt_H);
            // START 2019/02/07 S.Ohki [QC#30023,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.A.getValidCount()).xxChkBox_A2, ZYPConstant.CHKBOX_ON_Y);
            // END   2019/02/07 S.Ohki [QC#30023,ADD]
            bizMsg.A.setValidCount(bizMsg.A.getValidCount() + 1);
        }
        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_AddLine================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0030Scrn00_DeleteLine(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_DeleteLine================================START", this);

        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;

        NFDL0030CMsg tmpMsg = new NFDL0030CMsg();
        EZDMsg.copy(bizMsg, null, tmpMsg, null);
        ZYPTableUtil.clear(bizMsg.A);

        int newIdx = 0; 
        for (int i = 0; i < tmpMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.CHKBOX_ON_Y.equals(tmpMsg.A.no(i).xxChkBox_A1.getValue())) {
                EZDMsg.copy(tmpMsg.A.no(i), null, bizMsg.A.no(newIdx), null);
                bizMsg.A.setValidCount(newIdx + 1);
                newIdx++;
            }
        }
        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_DeleteLine================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0030Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_CMN_Clear================================START", this);

        doProcess_NFDL0030_INIT(cMsg, sMsg);

        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_CMN_Clear================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0030Scrn00_SearchInvoice(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_SearchInvoice================================START", this);

        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;
        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        prmMap.put("invNumList", new String[]{bizMsg.A.no(bizMsg.xxNum.getValueInt()).arTrxNum_A1.getValue()});
        prmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
        prmMap.put("arCashApplyStsCdList", new String[]{AR_CASH_APPLY_STS.UNAPPLIED, AR_CASH_APPLY_STS.PARTIAL});
        // START 2018/06/23 [QC#26239, ADD]
        prmMap.put("arTrxTpCdList", new String[]{ AR_TRX_TP.INVOICE, AR_TRX_TP.CREDIT_MEMO, AR_TRX_TP.DEDUCTION, AR_TRX_TP.ON_ACCOUNT });
        // END   2018/06/23 [QC#26239, ADD]
        S21SsmEZDResult arTrxBal =  NFDL0030Query.getInstance().getArTrxBalList(prmMap, bizMsg);

        if (arTrxBal.isCodeNormal()) {
            List<Map<String, Object>> arTrxBalList = (List<Map<String, Object>>) arTrxBal.getResultObject();
            String arTrxNum = (String) arTrxBalList.get(0).get("AR_TRX_NUM");
            BigDecimal dealRmngBalGrsAmt = (BigDecimal) arTrxBalList.get(0).get("DEAL_RMNG_BAL_GRS_AMT");
            BigDecimal arTrxBalPk = (BigDecimal) arTrxBalList.get(0).get("AR_TRX_BAL_PK");
            String ezUpTime = (String) arTrxBalList.get(0).get("EZUPTIME");
            String ezUpTimeZone = (String) arTrxBalList.get(0).get("EZUPTIMEZONE");

            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).dealRmngBalGrsAmt_A1, dealRmngBalGrsAmt);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).arTrxBalPk_A1, arTrxBalPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).ezUpTime_A1, ezUpTime);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).ezUpTimeZone_A1, ezUpTimeZone);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).acctDt_A1, bizMsg.acctDt_H);
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).arTrxNum_BK, arTrxNum);

            // Add Start 2018/01/16 S21_NA#20563
            BigDecimal dealApplyAdjRsvdAmt = (BigDecimal) arTrxBalList.get(0).get("DEAL_APPLY_ADJ_RSVD_AMT");
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).dealApplyAdjRsvdAmt_A1, dealApplyAdjRsvdAmt);
            // Add End 2018/01/16 S21_NA#20563

            // START 2018/06/15 [QC#26239, ADD]
            String arTrxTpCd = (String) arTrxBalList.get(0).get("AR_TRX_TP_CD");
            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).arTrxTpCd_A1, arTrxTpCd);
            // END   2018/06/15 [QC#26239, ADD]

            if (bizMsg.A.no(bizMsg.xxNum.getValueInt()).xxChkBox_A2.getValue().equals(ZYPConstant.CHKBOX_ON_Y)) {
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(bizMsg.xxNum.getValueInt()).dealNetRcptAmt_A1, dealRmngBalGrsAmt);
            }

        } else {
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).dealRmngBalGrsAmt_A1.clear();
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).arTrxNum_A1.setErrorInfo(1, "NFDM0015E");
        }

        // START 2020/03/03 [QC#55664, ADD]
        if (AR_TRX_TP.ON_ACCOUNT.equals(bizMsg.A.no(bizMsg.xxNum.getValueInt()).arTrxTpCd_A1.getValue())) {
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).dealRmngBalGrsAmt_A1.clear();
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).dealNetRcptAmt_A1.clear();
            bizMsg.A.no(bizMsg.xxNum.getValueInt()).arTrxNum_A1.setErrorInfo(1, "NFDM0052E");
        }
        // END   2020/03/03 [QC#55664, ADD]
        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_SearchInvoice================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFDL0030_NWAL2010(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFDL0030ryouScrn00_CMN_Reset================================START", this);

        NFDL0030CMsg bizMsg = (NFDL0030CMsg) cMsg;
        // START 2016/06/14 T.Tsuchida [QC#9829,MOD]
        if (!ZYPCommonFunc.hasValue(bizMsg.dsCrCardPk_O)) {
            EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_CMN_Reset================================END", this);
            return;
        }
        // END 2016/06/14 T.Tsuchida [QC#9829,MOD]

        Map<String, Object> prmMap = new HashMap<String, Object>();
        prmMap.put("glblCmpyCd", getGlobalCompanyCode());
        // START 2016/06/14 T.Tsuchida [QC#9829,MOD]
        //prmMap.put("firstTrxInfoTxt", bizMsg.dsAcctNum_H.getValue());
        //prmMap.put("scdTrxInfoTxt", getContextUserInfo().getUserId());
        //prmMap.put("thirdTrxInfoTxt", bizMsg.xxPopPrm_05.getValue());
        prmMap.put("dsCrCardPk", bizMsg.dsCrCardPk_O.getValue());
        // END 2016/06/14 T.Tsuchida [QC#9829,MOD]
        S21SsmEZDResult creditCardInfo =  NFDL0030Query.getInstance().getCreditCardInfo(prmMap, bizMsg);
        if (creditCardInfo.isCodeNormal()) {
            Map<String, Object> creditCardInfoMap = (Map<String, Object>) creditCardInfo.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.crCardTpNm_H, (String) creditCardInfoMap.get("CR_CARD_TP_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.crCardLastDigitNum_H, (String) creditCardInfoMap.get("CR_CARD_LAST_DIGIT_NUM"));
            // START 2018/05/15 E.Kameishi [QC#24833,MOD]
            ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum_H2, S21StringUtil.concatStrings(ASTERISK, (String) creditCardInfoMap.get("CR_CARD_LAST_DIGIT_NUM")));
            // END 2018/05/15 E.Kameishi [QC#24833,MOD]
            ZYPEZDItemValueSetter.setValue(bizMsg.crCardExprYrMth_H, (String) creditCardInfoMap.get("CR_CARD_EXPR_YR_MTH"));
            ZYPEZDItemValueSetter.setValue(bizMsg.crCardHldNm_H, (String) creditCardInfoMap.get("CR_CARD_HLD_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxAllLineAddr_H, (String) creditCardInfoMap.get("XX_ALL_LINE_ADDR"));
            ZYPEZDItemValueSetter.setValue(bizMsg.crCardTpNm_H, (String) creditCardInfoMap.get("CR_CARD_TP_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.crCardCustRefNum_H, (String) creditCardInfoMap.get("CR_CARD_CUST_REF_NUM"));
        }

        EZDDebugOutput.println(1, "doProcess_NFDL0030Scrn00_CMN_Reset================================END", this);
    }

    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    private void doProcess_NFDL0030_NFCL3170(EZDCMsg ezdCMsg, EZDSMsg sMsg) {
        NFDL0030CMsg cMsg = (NFDL0030CMsg) ezdCMsg;
        String glblCmpyCd = getGlobalCompanyCode();
        if (ZYPCommonFunc.hasValue(cMsg.dsCustBankAcctRelnPk_H)) {
            Map<String, Object> resultMap = NFDL0030Query.getInstance().getBankInfo(glblCmpyCd, cMsg.dsCustBankAcctRelnPk_H.getValue().toString());
            if (resultMap != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.bankRteNum_H, resultMap.get("BANK_RTE_NUM").toString());
                ZYPEZDItemValueSetter.setValue(cMsg.dsBankAcctNum_H, resultMap.get("MASK_BANK_ACCT_NUM").toString());
                ZYPEZDItemValueSetter.setValue(cMsg.dsBankAcctNum_H2, resultMap.get("DS_BANK_ACCT_NUM").toString());
            }
        }
    }
    private void createPmtMethPullDown(NFDL0030CMsg cMsg) {
        // get Pulldown List
        S21SsmEZDResult ssmResult = NFDL0030Query.getInstance().getPmtMethList(getGlobalCompanyCode());
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            List<Map<String, Object>> result = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < result.size(); i++) {
                Map<String, Object> pullDownData = result.get(i);
                cMsg.dsPmtMethCd_FC.no(i).setValue((String) pullDownData.get("DS_PMT_METH_CD"));
                cMsg.dsPmtMethDescTxt_FD.no(i).setValue((String) pullDownData.get("DS_PMT_METH_DESC_TXT"));
            }
        }
    }
    private void setPayerName(NFDL0030CMsg cMsg) {
        // get payerName
        S21SsmEZDResult ssmResult = NFDL0030Query.getInstance().getPayerName(getGlobalCompanyCode(), cMsg.dsAcctNum_H.getValue());
        String payerName = (String) ssmResult.getResultObject();
        if (ZYPCommonFunc.hasValue(payerName)) {
            cMsg.sellToCustLocNm_H.setValue(payerName);
        }
    }
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]

}
