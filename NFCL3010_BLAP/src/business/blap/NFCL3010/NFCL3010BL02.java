package business.blap.NFCL3010;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import business.blap.NFCL3010.common.NFCL3010CommonLogic;
import business.blap.NFCL3010.constant.NFCL3010Constant;
import business.db.CLT_PTFOTMsg;
import business.file.NFCL3010F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RCPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 02/11/2016   Fujitsu         T.Tanaka        Update          Def#2601
 * 02/23/2016   Fujitsu         T.Tanaka        Update          Def#2631
 * 04/26/2016   Fujitsu         S.Fujita        Update          QC#3293
 * 05/11/2016   Fujitsu         S.Fujita        Update          QC#7666
 * 2016/07/27   Hitachi         K.Kojima        Update          QC#6394
 * 2017/03/17   Fujitsu         T.Murai         Update          QC#14205
 * 2017/08/25   Hitachi         T.Tsuchida      Update          QC#18653
 * 2018/02/02   Fujitsu         T.Murai         Update          QC#21372
 * 2018/02/14   Fujitsu         T.Murai         Update          QC#24138
 * 2018/03/16   Fujitsu         H.Ikeda         Update          QC#21737
 * 2018/06/08   Hitachi         E.Kameishi      Update          QC#25735
 * 2018/06/19   CITS            K.Ogino         Update          QC#24288
 * 2018/11/08   Fujitsu         S.Ohki          Update          QC#29097
 * 2018/11/13   Fujitsu         S.Ohki          Update          QC#29097-2
 * 2018/12/06   Fujitsu         Y.Matsui        Update          QC#29528
 * 2021/10/01   CITS            S.Go            Update          QC#57903
 *</pre>
 */
public class NFCL3010BL02 extends S21BusinessHandler implements NFCL3010Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3010_INIT".equals(screenAplID)) {
                doProcess_NFCL3010_INIT(cMsg, sMsg);
            } else if ("NFCL3010Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFCL3010Scrn00_CMN_Reset(cMsg, sMsg);

            } else if ("NFCL3010Scrn00_PageNext".equals(screenAplID)) {
                nextPage((NFCL3010CMsg) cMsg, (NFCL3010SMsg) sMsg);

            } else if ("NFCL3010Scrn00_PagePrev".equals(screenAplID)) {
                prevPage((NFCL3010CMsg) cMsg, (NFCL3010SMsg) sMsg);

            } else if ("NFCL3010Scrn00_Click_Search".equals(screenAplID)) {
                doProcess_NFCL3010Scrn00_Click_Search(cMsg, sMsg);

            } else if ("NFCL3010Scrn00_Click_SearchCollectorName".equals(screenAplID)) {
                doProcess_NFCL3010Scrn00_Click_SearchCollectorName(cMsg, sMsg);

            } else if ("NFCL3010_NWAL1130".equals(screenAplID)) {
                doProcess_NFCL3010_NWAL1130(cMsg, sMsg);

            } else if ("NFCL3010Scrn00_Select_Bank".equals(screenAplID)) {
                doProcess_NFCL3010Scrn00_Select_Bank(cMsg, sMsg);

            } else if ("NFCL3010Scrn00_Select_BankBr".equals(screenAplID)) {
                doProcess_NFCL3010Scrn00_Select_BankBr(cMsg, sMsg);

            } else if ("NFCL3010Scrn00_Select_BankBr".equals(screenAplID)) {
                doProcess_NFCL3010Scrn00_Select_BankBr(cMsg, sMsg);

            // START 2018/03/16 H.Ikeda [QC#21737,DEL]
            //} else if ("NFCL3010Scrn00_Click_ArRcptBatNmRadioBtn".equals(screenAplID)) {
            //    doProcess_NFCL3010Scrn00_Click_ArRcptBatNmRadioBtn(cMsg, sMsg);
            // END   2018/03/16 H.Ikeda [QC#21737,DEL]
            } else if ("NFCL3010_NFCL3020".equals(screenAplID)) {
                doProcess_NFCL3010_NFCL3020(cMsg, sMsg);

            } else if ("NFCL3010_NFCL3030".equals(screenAplID)) {
                doProcess_NFCL3010_NFCL3030(cMsg, sMsg);

            // START 2018/03/16 H.Ikeda [QC#21737,ADD]
            } else if ("NFCL3010Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL3010Scrn00_CMN_Download(cMsg, sMsg);

            } else if ("NFCL3010Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL3010Scrn00_TBLColumnSort((NFCL3010CMsg) cMsg, (NFCL3010SMsg) sMsg);

            // END   2018/03/16 H.Ikeda [QC#21737,ADD]
                
            } else {
                // START 2018/03/16 H.Ikeda [QC#21737,MOD]
                //throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
                return;
                // END   2018/03/16 H.Ikeda [QC#21737,MOD]
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // START 2018/03/16 H.Ikeda [QC#21737,ADD]
    private void doProcess_NFCL3010Scrn00_TBLColumnSort(NFCL3010CMsg bizMsg, NFCL3010SMsg glblMsg) {

        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {

            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            bizMsg.xxPageShowFromNum_A.setValue(1);
            //NFCL3010CommonLogic.firstPage(bizMsg, glblMsg);
            //NFCL3010CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            NFCL3010CommonLogic.pagenation(bizMsg, glblMsg, 0);
        }
    }
    
    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFCL3010Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3010Scrn00_CMN_Download================================START", this);

        // START 2018/06/08 E.Kameishi [QC#25735,MOD]
        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
        NFCL3010SMsg globalMsg = (NFCL3010SMsg) sMsg;

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("sMsg", globalMsg);
        ssmMap.put("rowNum", String.valueOf(globalMsg.A.length()));
        // START 2018/11/13 S.Ohki [QC#29097-2,MOD]
//        if (ZYPCommonFunc.hasValue(bizMsg.arRcptSrcCd_H)
//                || ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)
        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)
        // END 2018/11/13 S.Ohki [QC#29097-2,MOD]
                || ZYPCommonFunc.hasValue(bizMsg.arBatRcptStsCd_H)
                || ZYPCommonFunc.hasValue(bizMsg.arLockBoxFileNm_H)
                || ZYPCommonFunc.hasValue(bizMsg.arLockBoxCd_H)
                || ZYPCommonFunc.hasValue(bizMsg.arLockBoxBatNum_H)) {
            ssmMap.put("existBatRcptCondition", ZYPConstant.FLG_ON_Y);
        } else {
            ssmMap.put("existBatRcptCondition", ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H)
                || ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H)
                || ZYPCommonFunc.hasValue(bizMsg.rcptNum_H1)
                || ZYPCommonFunc.hasValue(bizMsg.rcptNum_H2)
                || ZYPCommonFunc.hasValue(bizMsg.rcptChkNum_H1)
                || ZYPCommonFunc.hasValue(bizMsg.rcptChkNum_H2)
                || ZYPCommonFunc.hasValue(bizMsg.rcptDt_H1)
                || ZYPCommonFunc.hasValue(bizMsg.rcptDt_H2)
                || ZYPCommonFunc.hasValue(bizMsg.funcRcptAmt_H1)
                || ZYPCommonFunc.hasValue(bizMsg.funcRcptAmt_H2)
                || ZYPCommonFunc.hasValue(bizMsg.arRcptStsCd_H)
                || ZYPCommonFunc.hasValue(bizMsg.arCashApplyStsCd_H)
                || ZYPCommonFunc.hasValue(bizMsg.psnCd_H)
                // START 2018/11/13 S.Ohki [QC#29097-2,ADD]
                || ZYPCommonFunc.hasValue(bizMsg.arRcptSrcCd_H)
                // END 2018/11/13 S.Ohki [QC#29097-2,ADD]
        ) {
            ssmMap.put("existRcptCondition", ZYPConstant.FLG_ON_Y);
        } else {
            ssmMap.put("existRcptCondition", ZYPConstant.FLG_OFF_N);
        }
        // Receipt Numbers
        String rcptSearchFlg = NFCL3010CommonLogic.setSearchFlg( bizMsg.rcptChkNum_H1.getValue());
        ssmMap.put("rcptSearchFlg", rcptSearchFlg);
        // Receipt Doc Numbers
        String rcptDocSearchFlg = NFCL3010CommonLogic.setSearchFlg( bizMsg.rcptNum_H1.getValue());
        ssmMap.put("rcptDocSearchFlg", rcptDocSearchFlg);
        if (ZYPCommonFunc.hasValue(bizMsg.dsBankAcctNm_H)) {
            ssmMap.put("dsBankAcctNm_H", bizMsg.dsBankAcctNm_H.getValue().toUpperCase());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsBankBrNm_H)) {
            ssmMap.put("dsBankBrNm_H", bizMsg.dsBankBrNm_H.getValue().toUpperCase());
        }
        ssmMap.put("arApplyTp_Adjustment", AR_APPLY_TP.ADJUSTMENT);
        ssmMap.put("arAdjTrxTp_OnAccount", AR_ADJ_TRX_TP.ON_ACCOUNT);
        ssmMap.put("arCashApplyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmMap.put("arCashApplyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        ssmMap.put("unidentified", AR_CASH_APPLY_STS.UNIDENTIFIED);
        ssmMap.put("asterisk", ASTERISK);

        // START 2018/12/06 Y.Matsui [QC#29528,ADD]
        ssmMap.put("arRcptTp_Offset", AR_RCPT_TP.OFFSET);
        // END 2018/12/06 Y.Matsui [QC#29528,ADD]

        S21SsmEZDResult ssmResult = NFCL3010Query.getInstance().getRcptDataDLList(ssmMap);
        List<Map<String, Object>> rcptDataDLList = (List<Map<String, Object>>) ssmResult.getResultObject();

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), ".csv");

        NFCL3010F00FMsg fMsg = new NFCL3010F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        // write header
        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        // write contents
        //for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
        //    EZDMsg.copy(globalMsg.A.no(i), null, fMsg, null);
        //    ZYPEZDItemValueSetter.setValue(fMsg.rcptNum_A,            globalMsg.A.no(i).rcptNum_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.rcptChkNum_A,         globalMsg.A.no(i).rcptChkNum_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.funcRcptAmt_A,        globalMsg.A.no(i).funcRcptAmt_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.funcApplyAmt_A,       globalMsg.A.no(i).funcApplyAmt_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.xxTotAmt_A,           globalMsg.A.no(i).xxTotAmt_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.funcRcptRmngBalAmt_A, globalMsg.A.no(i).funcRcptRmngBalAmt_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arRcptTrxTpNm_A,      globalMsg.A.no(i).arRcptTrxTpNm_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.rcptDt_A,             globalMsg.A.no(i).rcptDt_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.payerCustCd_A,        globalMsg.A.no(i).payerCustCd_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A,           globalMsg.A.no(i).dsAcctNm_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptNm_A,        globalMsg.A.no(i).arBatRcptNm_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arRcptSrcNm_A,        globalMsg.A.no(i).arRcptSrcNm_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptDt_A,        globalMsg.A.no(i).arBatRcptDt_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptStsNm_A,     globalMsg.A.no(i).arBatRcptStsNm_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptCnt_A,       globalMsg.A.no(i).arBatRcptCnt_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptAmt_A,       globalMsg.A.no(i).arBatRcptAmt_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxFileNm_A,    globalMsg.A.no(i).arLockBoxFileNm_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxNm_A,        globalMsg.A.no(i).arLockBoxNm_A);
        //    ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxBatNum_A,    globalMsg.A.no(i).arLockBoxBatNum_A);

        for (int i = 0; i < rcptDataDLList.size(); i++) {
            ZYPEZDItemValueSetter.setValue(fMsg.rcptNum_A,            (String) rcptDataDLList.get(i).get("RCPT_NUM_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.rcptChkNum_A,         (String) rcptDataDLList.get(i).get("RCPT_CHK_NUM_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.funcRcptAmt_A,        (BigDecimal) rcptDataDLList.get(i).get("FUNC_RCPT_AMT_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.funcApplyAmt_A,       (BigDecimal) rcptDataDLList.get(i).get("FUNC_APPLY_AMT_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxTotAmt_A,           (BigDecimal) rcptDataDLList.get(i).get("XX_TOT_AMT_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.funcRcptRmngBalAmt_A, (BigDecimal) rcptDataDLList.get(i).get("FUNC_RCPT_RMNG_BAL_AMT_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arRcptTrxTpNm_A,      (String) rcptDataDLList.get(i).get("AR_RCPT_TRX_TP_NM_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.rcptDt_A,             (String) rcptDataDLList.get(i).get("RCPT_DT_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.payerCustCd_A,        (String) rcptDataDLList.get(i).get("PAYER_CUST_CD_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A,           (String) rcptDataDLList.get(i).get("DS_ACCT_NM_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptNm_A,        (String) rcptDataDLList.get(i).get("AR_BAT_RCPT_NM_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arRcptSrcNm_A,        (String) rcptDataDLList.get(i).get("AR_RCPT_SRC_NM_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptDt_A,        (String) rcptDataDLList.get(i).get("AR_BAT_RCPT_DT_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptStsNm_A,     (String) rcptDataDLList.get(i).get("AR_BAT_RCPT_STS_NM_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptCnt_A,       (BigDecimal) rcptDataDLList.get(i).get("AR_BAT_RCPT_CNT_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arBatRcptAmt_A,       (BigDecimal) rcptDataDLList.get(i).get("AR_BAT_RCPT_AMT_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxFileNm_A,    (String) rcptDataDLList.get(i).get("AR_LOCK_BOX_FILE_NM_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxNm_A,        (String) rcptDataDLList.get(i).get("AR_LOCK_BOX_NM_A"));
            ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxBatNum_A,    (String) rcptDataDLList.get(i).get("AR_LOCK_BOX_BAT_NUM_A"));
            
            csvOutFile.write();
        }
        // END 2018/06/08 E.Kameishi [QC#25735,MOD]
        csvOutFile.close();
    }
    // END   2018/03/16 H.Ikeda [QC#21737,ADD]

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3010_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3010_INIT================================START", this);

        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
        NFCL3010SMsg globalMsg = (NFCL3010SMsg) sMsg;

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.procDt.setValue(ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        bizMsg.dsBankAcctTpCd.setValue(DS_BANK_ACCT_TP.INTERNAL);

        bizMsg.arRcptSrcCd_H.clear();
        bizMsg.arRcptSrcCd_LC.clear();
        // START 2016/05/11 S.Fujita [QC#7666,MOD]
//        bizMsg.arRcptSrcNm_LD.clear();
        bizMsg.arRcptSrcDescTxt_LD.clear();
        // END 2016/05/11 S.Fujita [QC#7666,MOD]
        // START 2018/06/15 J.Kim [QC#25695,DEL]
        // bizMsg.arBatRcptNm_H.clear();
        // END 2018/06/15 J.Kim [QC#25695,DEL]
        bizMsg.arBatRcptStsCd_H.clear();
        bizMsg.arBatRcptStsCd_LC.clear();
        bizMsg.arBatRcptStsNm_LD.clear();
        bizMsg.arLockBoxFileNm_H.clear();
        bizMsg.arLockBoxCd_H.clear();
        bizMsg.arLockBoxCd_LC.clear();
        bizMsg.arLockBoxNm_LD.clear();
        bizMsg.arLockBoxBatNum_H.clear();
        // Mod Start 2018/02/02 S21_NA#21372
        bizMsg.dsAcctNum_H.clear();
        // bizMsg.dsAcctNum_H1.clear();
        // bizMsg.dsAcctNum_H2.clear();
        // Mod Start 2018/02/02 S21_NA#21372
        bizMsg.dsAcctNm_H.clear();
        bizMsg.rcptNum_H1.clear();
        bizMsg.rcptNum_H2.clear();
        bizMsg.rcptChkNum_H1.clear();
        bizMsg.rcptChkNum_H2.clear();
        bizMsg.rcptDt_H1.clear();
        bizMsg.rcptDt_H2.clear();
        bizMsg.funcRcptAmt_H1.clear();
        bizMsg.funcRcptAmt_H2.clear();
        bizMsg.psnCd_H.clear();
        bizMsg.fullPsnNm_H.clear();
        bizMsg.cratDt_H1.clear();
        bizMsg.cratDt_H2.clear();
        bizMsg.xxPageShowFromNum_A.clear();
        bizMsg.xxPageShowToNum_A.clear();
        bizMsg.xxPageShowOfNum_A.clear();
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
        //bizMsg.xxPageShowFromNum_B.clear();
        //bizMsg.xxPageShowToNum_B.clear();
        //bizMsg.xxPageShowOfNum_B.clear();
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]     
        bizMsg.dsBankAcctNm_H.clear();
        bizMsg.dsBankAcctNum_H.clear();
        bizMsg.bankRteNum_H.clear();
        bizMsg.dsBankBrNm_H.clear();
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
        bizMsg.arRcptStsCd_H.clear();
        bizMsg.arCashApplyStsCd_H.clear();
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]

        ZYPCodeDataUtil.createPulldownList(AR_BAT_RCPT_STS.class, bizMsg.arBatRcptStsCd_LC, bizMsg.arBatRcptStsNm_LD);
        ZYPCodeDataUtil.createPulldownList(AR_LOCK_BOX.class, bizMsg.arLockBoxCd_LC, bizMsg.arLockBoxNm_LD);
        // START 2016/05/11 S.Fujita [QC#7666,MOD]
//        ZYPCodeDataUtil.createPulldownList(AR_RCPT_SRC.class, bizMsg.arRcptSrcCd_LC, bizMsg.arRcptSrcNm_LD);
        ZYPCodeDataUtil.createPulldownList(AR_RCPT_SRC.class, bizMsg.arRcptSrcCd_LC, bizMsg.arRcptSrcDescTxt_LD);
        // END 2016/05/11 S.Fujita [QC#7666,MOD]
        ZYPCodeDataUtil.createPulldownList(AR_RCPT_STS.class, bizMsg.arRcptStsCd_LC, bizMsg.arRcptStsDescTxt_LD);
        ZYPCodeDataUtil.createPulldownList(AR_CASH_APPLY_STS.class, bizMsg.arCashApplyStsCd_LC, bizMsg.arCashApplyStsDescTxt_LD);
//        NFCL3010CommonLogic.createPulldownListBank(getGlobalCompanyCode(), bizMsg);

        ZYPTableUtil.clear(bizMsg.A);
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
        //ZYPTableUtil.clear(bizMsg.B);
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]
        globalMsg.clear();
        ZYPTableUtil.clear(globalMsg.A);
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
        //ZYPTableUtil.clear(globalMsg.B);
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]
        // START 2018/03/19 H.Ikeda [QC#21737,ADD]
        ZYPGUITableColumn.getColData((NFCL3010CMsg) cMsg, (NFCL3010SMsg) sMsg);
        // END   2018/03/19 H.Ikeda [QC#21737,ADD]

        // START 2018/06/15 J.Kim [QC#25695,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)) {
            doProcess_NFCL3010Scrn00_Click_Search(cMsg, sMsg);
        }
        // END 2018/06/15 J.Kim [QC#25695,ADD]

        EZDDebugOutput.println(1, "doProcess_NFCL3010_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3010Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3010Scrn00_CMN_Reset================================START", this);

        // START 2018/06/15 J.Kim [QC#25695,ADD]
        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
        bizMsg.arBatRcptNm_H.clear();
        // END 2018/06/15 J.Kim [QC#25695,ADD]

        doProcess_NFCL3010_INIT(cMsg, sMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL3010Scrn00_CMN_Reset================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3010_NWAL1130(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3010_NWAL1130================================START", this);

        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
        setCollectorName(bizMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL3010_NWAL1130================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3010Scrn00_Click_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3010Scrn00_Search================================START", this);

        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
        NFCL3010SMsg globalMsg = (NFCL3010SMsg) sMsg;
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
        //bizMsg.xxRadioBtn_A.clear();
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]

        EZDMsg.copy(bizMsg, null, globalMsg, null);

        if (!setCollectorName(bizMsg)) {
            return;
        }
        getBatRcptList(bizMsg, globalMsg);
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
        // getRcptList(bizMsg, globalMsg);
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]

        EZDDebugOutput.println(1, "doProcess_NFCL3010Scrn00_Search================================END", this);
    }
    // START 2018/03/16 H.Ikeda [QC#21737,DEL]
    ///**
    // * @param cMsg Business Component Interface Message
    // * @param sMsg Global area information
    // */
    //private void doProcess_NFCL3010Scrn00_Click_ArRcptBatNmRadioBtn(EZDCMsg cMsg, EZDSMsg sMsg) {
    //    EZDDebugOutput.println(1, "doProcess_NFCL3010Scrn00_Search================================START", this);
    //
    //      NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
    //    NFCL3010SMsg globalMsg = (NFCL3010SMsg) sMsg;
    //
    //      getRcptList(bizMsg, globalMsg);
    //
    //      EZDDebugOutput.println(1, "doProcess_NFCL3010Scrn00_Search================================END", this);
    //}
    // END   2018/03/16 H.Ikeda [QC#21737,DEL]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3010Scrn00_Click_SearchCollectorName(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3010Scrn00_Search================================START", this);

        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;

        setCollectorName(bizMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL3010Scrn00_Search================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private boolean setCollectorName(NFCL3010CMsg bizMsg) {
        bizMsg.fullPsnNm_H.clear();
        if (!ZYPCommonFunc.hasValue(bizMsg.psnCd_H)) {
            return true;
        }

//        S21UserInfo userInfo = getUserProfileService().getUserInfoFor(bizMsg.psnCd_H.getValue());
//        if (userInfo == null) {
//            bizMsg.psnCd_H.setErrorInfo(1, "ZZZM9006E", new String[] {"User ID"});
//            return false;
//        } else {
//            ZYPEZDItemValueSetter.setValue(bizMsg.fullPsnNm_H, userInfo.getFullName());
//        }
        
        if (ZYPCommonFunc.hasValue(bizMsg.psnCd_H)) {
            CLT_PTFOTMsg inMsg = new CLT_PTFOTMsg();
            inMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
            inMsg.cltPsnCd.setValue(bizMsg.psnCd_H.getValue());
            S21SsmEZDResult ssmResult = NFCL3010Query.getInstance().getCltPsnNm(inMsg);
            CLT_PTFOTMsg outMsg = (CLT_PTFOTMsg)ssmResult.getResultObject();
            if(outMsg == null) {
                bizMsg.psnCd_H.setErrorInfo(1, "ZZZM9006E", new String[] {"Collector Person"});
                return false;
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.fullPsnNm_H, outMsg.cltPsnNm);
        }
        
        return true;
    }

    /**
     * @param  bizMsg Business Component Interface Message
     * @param  globalMsg Global area information
     */
    private void getBatRcptList(NFCL3010CMsg bizMsg, NFCL3010SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("sMsg", globalMsg);
        ssmMap.put("rowNum", String.valueOf(globalMsg.A.length()));
        // START 2018/11/08 S.Ohki [QC#29097,MOD]
//        if (ZYPCommonFunc.hasValue(bizMsg.arRcptSrcCd_H)
//                || ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)
        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)
        // END 2018/11/08 S.Ohki [QC#29097,MOD]
                || ZYPCommonFunc.hasValue(bizMsg.arBatRcptStsCd_H)
                || ZYPCommonFunc.hasValue(bizMsg.arLockBoxFileNm_H)
                || ZYPCommonFunc.hasValue(bizMsg.arLockBoxCd_H)
                || ZYPCommonFunc.hasValue(bizMsg.arLockBoxBatNum_H)) {
            ssmMap.put("existBatRcptCondition", ZYPConstant.FLG_ON_Y);
        } else {
            ssmMap.put("existBatRcptCondition", ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H) // Mod 2018/02/02 S21_NA#21372
//                || ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H2) // Del 2018/02/02 S21_NA#21372
                || ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H)
                || ZYPCommonFunc.hasValue(bizMsg.rcptNum_H1)
                || ZYPCommonFunc.hasValue(bizMsg.rcptNum_H2)
                || ZYPCommonFunc.hasValue(bizMsg.rcptChkNum_H1)
                || ZYPCommonFunc.hasValue(bizMsg.rcptChkNum_H2)
                || ZYPCommonFunc.hasValue(bizMsg.rcptDt_H1)
                || ZYPCommonFunc.hasValue(bizMsg.rcptDt_H2)
                || ZYPCommonFunc.hasValue(bizMsg.funcRcptAmt_H1)
                || ZYPCommonFunc.hasValue(bizMsg.funcRcptAmt_H2)
                || ZYPCommonFunc.hasValue(bizMsg.arRcptStsCd_H)
                || ZYPCommonFunc.hasValue(bizMsg.arCashApplyStsCd_H)
                || ZYPCommonFunc.hasValue(bizMsg.psnCd_H)
                // START 2018/11/08 S.Ohki [QC#29097,ADD]
                || ZYPCommonFunc.hasValue(bizMsg.arRcptSrcCd_H)
                // END 2018/11/08 S.Ohki [QC#29097,ADD]
                ) {
            ssmMap.put("existRcptCondition", ZYPConstant.FLG_ON_Y);
        } else {
            ssmMap.put("existRcptCondition", ZYPConstant.FLG_OFF_N);
        }

        // START 2016/04/26 S.Fujita [QC#3293,MOD]
        // Del Start 2018/02/02 S21_NA#21372
//        // Customer Number
//        String custSearchFlg = NFCL3010CommonLogic.setSearchFlg( bizMsg.dsAcctNum_H1.getValue());
//        ssmMap.put("custSearchFlg", custSearchFlg);
        // Del End 2018/02/02 S21_NA#21372
        // Receipt Numbers
        String rcptSearchFlg = NFCL3010CommonLogic.setSearchFlg( bizMsg.rcptChkNum_H1.getValue());
        ssmMap.put("rcptSearchFlg", rcptSearchFlg);
        // Receipt Doc Numbers
        String rcptDocSearchFlg = NFCL3010CommonLogic.setSearchFlg( bizMsg.rcptNum_H1.getValue());
        ssmMap.put("rcptDocSearchFlg", rcptDocSearchFlg);
        // END 2016/04/26 S.Fujita [QC#3293,MOD]

        // START 2016/07/27 K.Kojima [QC#6394,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.dsBankAcctNm_H)) {
            ssmMap.put("dsBankAcctNm_H", bizMsg.dsBankAcctNm_H.getValue().toUpperCase());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.dsBankBrNm_H)) {
            ssmMap.put("dsBankBrNm_H", bizMsg.dsBankBrNm_H.getValue().toUpperCase());
        }
        // END 2016/07/27 K.Kojima [QC#6394,ADD]

        // START 2018/03/16 H.ikeda [QC#21737,MOD]
        // S21SsmEZDResult ssmResult = NFCL3010Query.getInstance().getBatRcptList(globalMsg, ssmMap);
        ssmMap.put("arApplyTp_Adjustment", AR_APPLY_TP.ADJUSTMENT);
        ssmMap.put("arAdjTrxTp_OnAccount", AR_ADJ_TRX_TP.ON_ACCOUNT);
        ssmMap.put("arCashApplyStsCd_U", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmMap.put("arCashApplyStsCd_P", AR_CASH_APPLY_STS.PARTIAL);
        ssmMap.put("unidentified", AR_CASH_APPLY_STS.UNIDENTIFIED);
        ssmMap.put("asterisk", ASTERISK);
        // QC#24288 Modify Performance Issue

        // START 2018/12/06 Y.Matsui [QC#29528,ADD]
        ssmMap.put("arRcptTp_Offset", AR_RCPT_TP.OFFSET);
        // END 2018/12/06 Y.Matsui [QC#29528,ADD]

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(globalMsg.A.length() + 1);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFCL3010Query.getInstance().getClass());

            ps = ssmLLClient.createPreparedStatement("getRcptDataList", ssmMap, execParam);
            rs = ps.executeQuery();
            // END   2018/03/16 H.ikeda [QC#21737,MOD]

            int cnt = 0;
            if (!rs.next()) {
                // no search result
                bizMsg.setMessageInfo("NZZM0000E");

                bizMsg.A.setValidCount(0);
                bizMsg.xxPageShowFromNum_A.setValue(1);
                bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
                bizMsg.xxPageShowOfNum_A.setValue(0);

            } else {

                do {
                    // set global
                    if (cnt > globalMsg.A.length() - 1) {
                        bizMsg.setMessageInfo("NZZM0001W");
                        cnt = globalMsg.A.length();
                        break;
                    } else {
                        bizMsg.setMessageInfo("NZZM0002I");
                    }

                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arBatRcptNm_A, rs.getString("AR_BAT_RCPT_NM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).rcptNum_A, rs.getString("RCPT_NUM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arRcptSrcNm_A, rs.getString("AR_RCPT_SRC_NM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arBatRcptDt_A, rs.getString("AR_BAT_RCPT_DT_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arBatRcptStsNm_A, rs.getString("AR_BAT_RCPT_STS_NM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arBatRcptCnt_A, rs.getBigDecimal("AR_BAT_RCPT_CNT_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arBatRcptAmt_A, rs.getBigDecimal("AR_BAT_RCPT_AMT_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arLockBoxFileNm_A, rs.getString("AR_LOCK_BOX_FILE_NM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arLockBoxNm_A, rs.getString("AR_LOCK_BOX_NM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arLockBoxBatNum_A, rs.getString("AR_LOCK_BOX_BAT_NUM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arRcptTrxTpNm_A, rs.getString("AR_RCPT_TRX_TP_NM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).rcptDt_A, rs.getString("RCPT_DT_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).funcRcptAmt_A, rs.getBigDecimal("FUNC_RCPT_AMT_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).funcApplyAmt_A, rs.getBigDecimal("FUNC_APPLY_AMT_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).payerCustCd_A, rs.getString("PAYER_CUST_CD_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).dsAcctNm_A, rs.getString("DS_ACCT_NM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arCashApplyStsCd_A, rs.getString("AR_CASH_APPLY_STS_CD_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).arBatRcptStsCd_A, rs.getString("AR_BAT_RCPT_STS_CD_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).rcptChkNum_A, rs.getString("RCPT_CHK_NUM_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).xxTotAmt_A, rs.getBigDecimal("XX_TOT_AMT_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).funcRcptRmngBalAmt_A, rs.getBigDecimal("FUNC_RCPT_RMNG_BAL_AMT_A"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).xxRecHistCratTs_A, rs.getString("XX_REC_HIST_CRAT_TS"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).xxRecHistCratByNm_A, rs.getString("XX_REC_HIST_CRAT_BY_NM"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).xxRecHistUpdTs_A, rs.getString("XX_REC_HIST_UPD_TS"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).xxRecHistUpdByNm_A, rs.getString("XX_REC_HIST_UPD_BY_NM"));
                    ZYPEZDItemValueSetter.setValue(globalMsg.A.no(cnt).xxRecHistTblNm_A, rs.getString("XX_REC_HIST_TBL_NM"));
                    cnt++;

                } while (rs.next());

                globalMsg.A.setValidCount(cnt);
                // set Screen
                int i = 0;
                for (; i < globalMsg.A.getValidCount(); i++) {
                    if (i == bizMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                }

                bizMsg.A.setValidCount(i);

                bizMsg.xxPageShowFromNum_A.setValue(1);
                bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
                bizMsg.xxPageShowOfNum_A.setValue(cnt);
            }

        } catch (SQLException e) {
            throw new S21AbendException(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }
    // START 2018/03/16 H.ikeda [QC#21737,DEL]
//    /**
//     * @param  bizMsg Business Component Interface Message
//     * @param  globalMsg Global area information
//     */
//    private void getRcptList(NFCL3010CMsg bizMsg, NFCL3010SMsg globalMsg) {
//
//// Because there is a case in which the receipt only exist
////        if (!ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_A)) {
////            return;
////        }
//
//        String arBatRcptNm = null;
//        String rcptNum = null;
//        if (ZYPCommonFunc.hasValue(bizMsg.xxRadioBtn_A)) {
//            arBatRcptNm = bizMsg.A.no(bizMsg.xxRadioBtn_A.getValueInt()).arBatRcptNm_A.getValue();
//            rcptNum = bizMsg.A.no(bizMsg.xxRadioBtn_A.getValueInt()).rcptNum_A.getValue();
//        }
//
//        Map<String, Object> ssmMap = new HashMap<String, Object>();
//        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
//        ssmMap.put("arBatRcptNm", arBatRcptNm);
//        ssmMap.put("rcptNum", rcptNum);
//        ssmMap.put("sMsg", globalMsg);
//        ssmMap.put("rowNum", String.valueOf(globalMsg.B.length()));
//        ssmMap.put("unidentified", AR_CASH_APPLY_STS.UNIDENTIFIED);
//        ssmMap.put("asterisk", ASTERISK);
////        if (ZYPCommonFunc.hasValue(bizMsg.arRcptSrcCd_H)
////            || ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_H)
////            || ZYPCommonFunc.hasValue(bizMsg.arBatRcptStsCd_H)
////            || ZYPCommonFunc.hasValue(bizMsg.arLockBoxFileNm_H)
////            || ZYPCommonFunc.hasValue(bizMsg.arLockBoxCd_H)
////            || ZYPCommonFunc.hasValue(bizMsg.arLockBoxBatNum_H)) {
////            ssmMap.put("existBatchCondition", ZYPConstant.FLG_ON_Y);
////        } else {
////            ssmMap.put("existBatchCondition", ZYPConstant.FLG_OFF_N);
////        }
//
//        // START 2016/04/26 S.Fujita [QC#3293,MOD]
//        // Del Start 2018/02/02 S21_NA#21372
//        // Customer Number
////        String custSearchFlg = NFCL3010CommonLogic.setSearchFlg( bizMsg.dsAcctNum_H1.getValue());
////        ssmMap.put("custSearchFlg", custSearchFlg);
//        // Del End 2018/02/02 S21_NA#21372
//        // Receipt Numbers
//        String rcptSearchFlg = NFCL3010CommonLogic.setSearchFlg( bizMsg.rcptChkNum_H1.getValue());
//        ssmMap.put("rcptSearchFlg", rcptSearchFlg);
//        // Receipt Doc Numbers
//        String rcptDocSearchFlg = NFCL3010CommonLogic.setSearchFlg( bizMsg.rcptNum_H1.getValue());
//        ssmMap.put("rcptDocSearchFlg", rcptDocSearchFlg);
//        // END 2016/04/26 S.Fujita [QC#3293,MOD]
//
//        // START 2016/07/27 K.Kojima [QC#6394,ADD]
//        if (ZYPCommonFunc.hasValue(bizMsg.dsBankAcctNm_H)) {
//            ssmMap.put("dsBankAcctNm_H", bizMsg.dsBankAcctNm_H.getValue().toUpperCase());
//        }
//        if (ZYPCommonFunc.hasValue(bizMsg.dsBankBrNm_H)) {
//            ssmMap.put("dsBankBrNm_H", bizMsg.dsBankBrNm_H.getValue().toUpperCase());
//        }
//        // END 2016/07/27 K.Kojima [QC#6394,ADD]
//
//        S21SsmEZDResult ssmResult = NFCL3010Query.getInstance().getRcptList(globalMsg, ssmMap);
//
//        if (ssmResult.isCodeNormal()) {
//
//            int queryResCnt = ssmResult.getQueryResultCount();
//
//            if (queryResCnt > globalMsg.B.length()) {
//
//                bizMsg.setMessageInfo("NZZM0001W");
//
//                queryResCnt = globalMsg.B.length();
//            } else {
//
//                bizMsg.setMessageInfo("NZZM0002I");
//            }
//
//            int i;
//            for (i = 0; i < globalMsg.B.getValidCount(); i++) {
//
//                if (i < bizMsg.B.length()) {
//
//                    EZDMsg.copy(globalMsg.B.no(i), null, bizMsg.B.no(i), null);
//
//                } else {
//                    break;
//                }
//            }
//
//            bizMsg.B.setValidCount(i);
//
//            bizMsg.xxPageShowFromNum_B.setValue(1);
//            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
//            bizMsg.xxPageShowOfNum_B.setValue(queryResCnt);
//
//        } else {
//
//            //Def#5396
//            if(bizMsg.A.getValidCount()<1) {
//                bizMsg.setMessageInfo("NZZM0000E");
//            } else {
//                bizMsg.setMessageInfo("NFCM0828E");
//            }
//
//            bizMsg.B.setValidCount(0);
//            bizMsg.xxPageShowFromNum_B.setValue(1);
//            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
//            bizMsg.xxPageShowOfNum_B.setValue(0);
//        }
//    }
    // END   2018/03/16 H.ikeda [QC#21737,DEL]
    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3010Scrn00_Select_Bank(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_Select_AdditionalBank================================START", this);
        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
        bizMsg.bankRteNum_H.clear();
        bizMsg.remDsBankAcctPk_H.clear();
        NFCL3010CommonLogic.createPulldownListBankBr(getGlobalCompanyCode(), bizMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_Select_AdditionalBank================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3010Scrn00_Select_BankBr(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_Select_AdditionalBankBr================================START", this);
        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
        bizMsg.remDsBankAcctPk_H.clear();
        NFCL3010CommonLogic.createPulldownListBankAccount(getGlobalCompanyCode(), bizMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL3030Scrn00_Select_AdditionalBankBr================================END", this);
    }

    /**
     * Previous Page
     * @param bizMsg NFCL3010CMsg
     * @param globalMsg NFCL3010SMsg
     */
    public void prevPage(NFCL3010CMsg bizMsg, NFCL3010SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;

        if (bizMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
            bizMsgAry = bizMsg.A;
            shareMsgAry = globalMsg.A;
            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsgAry.length());
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
//        } else if (bizMsg.xxPageTblNm.getValue().equals(TABLE_B)) {
//            bizMsgAry = bizMsg.B;
//            shareMsgAry = globalMsg.B;
//            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValueInt() - bizMsgAry.length());
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]
        }
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
//        if (bizMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
//            getRcptList(bizMsg, globalMsg);
//        }
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]
    }

    /**
     * Next Page
     * @param bizMsg NFCL3010CMsg
     * @param globalMsg NFCL3010SMsg
     */
    public void nextPage(NFCL3010CMsg bizMsg, NFCL3010SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;
        if (bizMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
            bizMsgAry = bizMsg.A;
            shareMsgAry = globalMsg.A;
            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() + bizMsgAry.length());
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
//        } else if (bizMsg.xxPageTblNm.getValue().equals(TABLE_B)) {
//            bizMsgAry = bizMsg.B;
//            shareMsgAry = globalMsg.B;
//            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValueInt() + bizMsgAry.length());
        // END 2018/03/16 H.Ikeda [QC#21737,DEL]
        }
        dispPage(bizMsg, bizMsgAry, shareMsgAry);
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
//        if (bizMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
//            getRcptList(bizMsg, globalMsg);
//        }
        // END 2018/03/16 H.Ikeda [QC#21737,DEL]
    }

    /**
     * Display Page
     * @param bizMsg NFCL3010CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NFCL3010CMsg bizMsg, EZDCMsgArray bizMsgAry, EZDSMsgArray shareMsgAry) {
        ZYPTableUtil.clear(bizMsgAry);

        if (bizMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
            int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
                EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
            }
            bizMsgAry.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_A.setValue(shareMsgAry.getValidCount());
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_A, BigDecimal.ZERO);

        } else if (bizMsg.xxPageTblNm.getValue().equals(TABLE_B)) {
            int startIndex = bizMsg.xxPageShowFromNum_B.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_B.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsgAry.length() && startIndex + dispRowNum < shareMsgAry.getValidCount(); dispRowNum++) {
                EZDMsg.copy(shareMsgAry.get(startIndex + dispRowNum), null, bizMsgAry.get(dispRowNum), null);
            }
            bizMsgAry.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_B.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_B.setValue(shareMsgAry.getValidCount());
        }
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3010_NFCL3020(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
        NFCL3010SMsg globalMsg = (NFCL3010SMsg) sMsg;
        // START 2017/08/25 T.Tsuchida [QC#18653,MOD]
        //bizMsg.xxRadioBtn_A.clear();
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
        //BigDecimal xxRadioBtn = bizMsg.xxRadioBtn_A.getValue();
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
        //ZYPTableUtil.clear(bizMsg.B);
        //ZYPTableUtil.clear(globalMsg.B);
        // END   2018/03/16 H.Ikeda [QC#21737,DEL]
        // END 2017/08/25 T.Tsuchida [QC#18653,MOD]

        EZDMsg.copy(bizMsg, null, globalMsg, null);

        if (!setCollectorName(bizMsg)) {
            return;
        }
        getBatRcptList(bizMsg, globalMsg);
        // START 2018/03/16 H.Ikeda [QC#21737,DEL]
        //// START 2017/08/25 T.Tsuchida [QC#18653,ADD]
        //if (xxRadioBtn.intValue() < bizMsg.A.getValidCount()) {
        //    ZYPEZDItemValueSetter.setValue(bizMsg.xxRadioBtn_A, xxRadioBtn);
        //}
        //// END 2017/08/25 T.Tsuchida [QC#18653,ADD]
        //getRcptList(bizMsg, globalMsg);
        // END 2018/03/16 H.Ikeda [QC#21737,DEL]
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3010_NFCL3030(EZDCMsg cMsg, EZDSMsg sMsg) {

//        NFCL3010CMsg bizMsg = (NFCL3010CMsg) cMsg;
//        NFCL3010SMsg globalMsg = (NFCL3010SMsg) sMsg;
//        bizMsg.xxRadioBtn_A.clear();
//
//        EZDMsg.copy(bizMsg, null, globalMsg, null);
//
//        if (!setCollectorName(bizMsg)) {
//            return;
//        }
//        getBatRcptList(bizMsg, globalMsg);
//        getRcptList(bizMsg, globalMsg);
    }
}
