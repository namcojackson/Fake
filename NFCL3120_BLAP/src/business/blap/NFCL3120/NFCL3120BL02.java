package business.blap.NFCL3120;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL3120.common.NFCL3120CommonLogic;
import business.blap.NFCL3120.constant.NFCL3120Constant;
import business.file.NFCL3120F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
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
 * write-Off Request Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/03/2015   Fujitsu         T.Tanaka        Create          Initial
 * 02/01/2016   Fujitsu         T.Tanaka        Update          Def#2569
 * 03/08/2016   Fujitsu         T.Tanaka        Update          Def#5182
 * 2016/07/25   Hitachi         K.Kojima        Update          QC#4870
 * 2016/08/05   Hitachi         K.Kojima        Update          QC#5521
 *</pre>
 */
public class NFCL3120BL02 extends S21BusinessHandler implements NFCL3120Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3120_INIT".equals(screenAplID)) {
                doProcess_NFCL3120_INIT(cMsg, sMsg);
                // START 2016/08/01 K.Kojima [QC#5521,ADD]
                ZYPGUITableColumn.getColData(cMsg, sMsg);
                // END 2016/08/01 K.Kojima [QC#5521,ADD]
            } else if ("NFCL3120_NFCL3170".equals(screenAplID)) {
                doProcess_NFCL3120_NFCL3170(cMsg, sMsg);
            } else if ("NFCL3120_NMAL6760".equals(screenAplID)) {
                doProcess_NFCL3120_NMAL6760(cMsg, sMsg);
            } else if ("NFCL3120_NWAL1130".equals(screenAplID)) {
                doProcess_NFCL3120_NWAL1130(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_Click_SetCustomerName".equals(screenAplID)) {
            } else if ("NFCL3120Scrn00_Click_SetBankAccountName".equals(screenAplID)) {
            } else if ("NFCL3120Scrn00_Search".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_Search(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_CMN_Download(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_OnChangeRadio".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_OnChangeRadio(cMsg, sMsg);
                // START 2016/07/25 K.Kojima [QC#4870,ADD]
            } else if ("NFCL3120Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_PageJump(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_PagePrev(cMsg, sMsg);
                // END 2016/07/25 K.Kojima [QC#4870,ADD]
                // START 2016/08/01 K.Kojima [QC#5521,ADD]
            } else if ("NFCL3120Scrn00_CMN_ColClear".equals(screenAplID)) {
            } else if ("NFCL3120Scrn00_CMN_ColSave".equals(screenAplID)) {
            } else if ("NFCL3120Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_TBLColumnSort(cMsg, sMsg);
            } else if ("NFCL3120Scrn00_SaveSearch".equals(screenAplID)) {
            } else if ("NFCL3120Scrn00_DeleteSearch".equals(screenAplID)) {
            } else if ("NFCL3120Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NFCL3120Scrn00_OnChangeSavedSearchOption(cMsg, sMsg);
                // END 2016/08/01 K.Kojima [QC#5521,ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3120_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        
        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.procDt.setValue(ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        
        bizMsg.xxRadioBtn.setValue(BigDecimal.ONE);
        bizMsg.dsBankAcctTpCd_H1.setValue(DS_BANK_ACCT_TP.EXTERNAL);

        // START 2016/08/05 K.Kojima [QC#5521,ADD]
        NFCL3120CommonLogic.createSavedSearchOptionsPullDown(bizMsg, bizMsg.glblCmpyCd.getValue(), getContextUserInfo().getUserId());
        // END 2016/08/05 K.Kojima [QC#5521,ADD]

//        NFCL3120CommonLogic.createPulldownListDsBank(bizMsg.glblCmpyCd.getValue(), bizMsg);
//        NFCL3120CommonLogic.createPulldownListDsBankBr(bizMsg.glblCmpyCd.getValue(), bizMsg);
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3120_NFCL3170(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        //Def#5182
//        doProcess_NFCL3120Scrn00_Search(cMsg, sMsg);
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3120_NMAL6760(EZDCMsg cMsg, EZDSMsg sMsg) {
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3120_NWAL1130(EZDCMsg cMsg, EZDSMsg sMsg) {
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3120Scrn00_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg globalMsg = (NFCL3120SMsg) sMsg;
        
        bizMsg.A.clear();
        globalMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.setValidCount(0);

        if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H1.getValue())) {
            if(bizMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
                searchBankAcct_Customer_Identified(bizMsg, globalMsg);
            } else {
                searchBankAcct_Customer_Unidentified(bizMsg, globalMsg);
            }
        } else {
            searchBankAcct_Customer_Unidentified(bizMsg, globalMsg);
        }
        // START 2016/07/25 K.Kojima [QC#4870,ADD]
        NFCL3120CommonLogic.pagenation(bizMsg, globalMsg, 0);
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
        // END 2016/07/25 K.Kojima [QC#4870,ADD]
    }


    /**
     * 
     * @param bizMsg
     * @param globalMsg
     */
    private void searchBankAcct_Customer_Identified(NFCL3120CMsg bizMsg, NFCL3120SMsg globalMsg) {

        // START 2016/07/27 K.Kojima [QC#4870,DEL]
        // Map<String, Object> ssmMap = new HashMap<String, Object>();
        // ssmMap.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        //
        // String dsBankAcctTpCd = DS_BANK_ACCT_TP.INTERNAL;
        // if (bizMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
        // dsBankAcctTpCd = DS_BANK_ACCT_TP.EXTERNAL;
        // }
        // // ssmMap.put("dsBankAcctNum",
        // bizMsg.dsBankAcctNum_H1.getValue());
        // // ssmMap.put("dsBankAcctTpCd", dsBankAcctTpCd);
        // // ssmMap.put("dsBankCd", bizMsg.dsBankCd_H1.getValue());
        // // ssmMap.put("bankRteNum",
        // bizMsg.bankRteNum_H1.getValue());
        // // ssmMap.put("procDt", bizMsg.procDt.getValue());
        // // ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H1.getValue());
        //
        // ssmMap.put("dsBankAcctTpCd", dsBankAcctTpCd);
        // ssmMap.put("dsBankAcctNum",
        // bizMsg.dsBankAcctNum_H1.getValue());
        // ssmMap.put("dsBankAcctNm",
        // bizMsg.dsBankAcctNm_H1.getValue());
        // ssmMap.put("dsBankBrNm", bizMsg.dsBankBrNm_H1.getValue());
        // ssmMap.put("bankRteNum", bizMsg.bankRteNum_H1.getValue());
        // ssmMap.put("dsAcctNm", bizMsg.dsAcctNm_H1.getValue());
        // ssmMap.put("procDt", bizMsg.procDt.getValue());
        // ssmMap.put("rowNum", globalMsg.A.length() + 1);
        // END 2016/07/27 K.Kojima [QC#4870,DEL]

        // START 2016/07/27 K.Kojima [QC#4870,ADD]
        Map<String, Object> ssmMap = NFCL3120CommonLogic.createSsmMap_Identified(bizMsg, globalMsg.A.length() + 1);
        // END 2016/07/27 K.Kojima [QC#4870,ADD]

        S21SsmEZDResult ssmResult = NFCL3120Query.getInstance().searchBankAcct_Customer_Identified(globalMsg, ssmMap);
        
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt < 1) {
                bizMsg.setMessageInfo("NZZM0000E");
            }
            else if (queryResCnt > globalMsg.A.length()) {
                // START 2016/07/25 K.Kojima [QC#4870,MOD]
                // bizMsg.setMessageInfo("NFAM0008E", new
                // String[]{String.valueOf(globalMsg.A.length())});
                bizMsg.setMessageInfo("ZZZM9002W");
                // END 2016/07/25 K.Kojima [QC#4870,MOD]
                queryResCnt = globalMsg.A.length();
                // START 2016/07/25 K.Kojima [QC#4870,DEL]
                // } else {
                // bizMsg.setMessageInfo("NZZM0002I");
                // END 2016/07/25 K.Kojima [QC#4870,DEL]
            }

            int i;
            for (i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (i < bizMsg.A.length()) {
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                } else {
                    break;
                }
            }
            bizMsg.A.setValidCount(i);
        } else {
//            bizMsg.setMessageInfo("NZZM0002I");
            bizMsg.setMessageInfo("NZZM0000E");
            bizMsg.A.setValidCount(0);
        }
    }

    /**
     * 
     * @param bizMsg
     * @param globalMsg
     */
    private void searchBankAcct_Customer_Unidentified(NFCL3120CMsg bizMsg, NFCL3120SMsg globalMsg) {

        // START 2016/07/27 K.Kojima [QC#4870,DEL]
        // Map<String, Object> ssmMap = new HashMap<String, Object>();
        // ssmMap.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        //
        // String dsBankAcctTpCd = DS_BANK_ACCT_TP.INTERNAL;
        // if (bizMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
        // dsBankAcctTpCd = DS_BANK_ACCT_TP.EXTERNAL;
        // }
        // // ssmMap.put("dsBankAcctNum",
        // bizMsg.dsBankAcctNum_H1.getValue());
        // // ssmMap.put("dsBankAcctTpCd", dsBankAcctTpCd);
        // // ssmMap.put("dsBankCd", bizMsg.dsBankCd_H1.getValue());
        // // ssmMap.put("bankRteNum",
        // bizMsg.bankRteNum_H1.getValue());
        // // ssmMap.put("procDt", bizMsg.procDt.getValue());
        // // ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H1.getValue());
        // // ssmMap.put("rowNum", globalMsg.A.length() + 1);
        //        
        // ssmMap.put("dsBankAcctTpCd", dsBankAcctTpCd);
        // ssmMap.put("dsBankAcctNum",
        // bizMsg.dsBankAcctNum_H1.getValue());
        // ssmMap.put("dsBankAcctNm",
        // bizMsg.dsBankAcctNm_H1.getValue());
        // ssmMap.put("dsBankBrNm", bizMsg.dsBankBrNm_H1.getValue());
        // ssmMap.put("bankRteNum", bizMsg.bankRteNum_H1.getValue());
        // ssmMap.put("procDt", bizMsg.procDt.getValue());
        // ssmMap.put("rowNum", globalMsg.A.length() + 1);
        // END 2016/07/27 K.Kojima [QC#4870,DEL]

        // START 2016/07/27 K.Kojima [QC#4870,ADD]
        Map<String, Object> ssmMap = NFCL3120CommonLogic.createSsmMap_Unidentified(bizMsg, globalMsg.A.length() + 1);
        // END 2016/07/27 K.Kojima [QC#4870,ADD]

        S21SsmEZDResult ssmResult = NFCL3120Query.getInstance().searchBankAcct_Customer_Unidentified(globalMsg, ssmMap);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt < 1) {
                bizMsg.setMessageInfo("NZZM0000E");
            }
            else if (queryResCnt > globalMsg.A.length()) {
//                bizMsg.setMessageInfo("NZZM0001W");
                // START 2016/07/25 K.Kojima [QC#4870,MOD]
                // bizMsg.setMessageInfo("NFAM0008E", new
                // String[]{String.valueOf(globalMsg.A.length())});
                bizMsg.setMessageInfo("ZZZM9002W");
                // END 2016/07/25 K.Kojima [QC#4870,MOD]
                queryResCnt = globalMsg.A.length();
                // START 2016/07/25 K.Kojima [QC#4870,DEL]
                // } else {
                // bizMsg.setMessageInfo("NZZM0002I");
                // END 2016/07/25 K.Kojima [QC#4870,DEL]
            }

            int i;
            for (i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (i < bizMsg.A.length()) {
                    EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                } else {
                    break;
                }
            }
            bizMsg.A.setValidCount(i);
        } else {
//            bizMsg.setMessageInfo("NZZM0002I");
            bizMsg.setMessageInfo("NZZM0000E");
            bizMsg.A.setValidCount(0);
        }
    }
    
    // START 2016/07/27 K.Kojima [QC#4870,DEL]
//    /**
//     * 
//     * @param cMsg
//     * @param sMsg
//     */
//    private void doProcess_NFCL3120Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
//
//        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
//        NFCL3120SMsg globalMsg = (NFCL3120SMsg) sMsg;
//        
//        int detailCount=0;
//        for(int i = 0; i < globalMsg.A.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
//                continue;
//            }
//            detailCount++;
//        }
//        if(detailCount == 0) {
//            bizMsg.setMessageInfo("NFCM0029E", null);
//            return;
//        }
//        
//        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(DL_FILE_NAME), DL_FILE_EXT);
//        
//        NFCL3120F00FMsg fMsg = new NFCL3120F00FMsg();
//        
//        
//        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
//
//        csvOutFile.writeHeader(new String[] {
//                "Bank Account#", 
//                "Bank Name", 
//                "Customer Account#", 
//                "Customer Account Name", 
//                "Routing Number", 
//                "Branch Name", 
//                "Effective From Date", 
//                "Effective Thrugh Date"});
//
//        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).xxChkBox_A1.getValue())) {
//                continue;
//            }
//            EZDMsg.copy(globalMsg.A.no(i), null, fMsg, null);
//            csvOutFile.write();
//        }
//        csvOutFile.close();
//    }
    // END 2016/07/27 K.Kojima [QC#4870,DEL]

    // START 2016/07/27 K.Kojima [QC#4870,ADD]
    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NFCL3120Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(DL_FILE_NAME), DL_FILE_EXT);

        NFCL3120F00FMsg fMsg = new NFCL3120F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        // START 2016/08/08 K.Kojima [QC#13005,ADD]
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        // END 2016/08/08 K.Kojima [QC#13005,ADD]
        // START 2016/08/08 K.Kojima [QC#13005,MOD]
        // csvOutFile.writeHeader(new String[] {"Bank Account#",
        // "Bank Name", "Customer Account#", "Customer Account Name",
        // "Routing Number", "Branch Name", "Effective From Date",
        // "Effective Thrugh Date" });
        csvOutFile.writeHeader(new String[] {"Bank Account#", "Bank Name", "Customer Account#", "Customer Account Name", "Routing Number", "Branch Name", "Effective From Date", "Effective Thrugh Date" }, fMsg, ZYPGUITableColumn
                .getColOrder(bizMsg));
        // END 2016/08/08 K.Kojima [QC#13005,MOD]

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFCL3120Query.getInstance().getClass());

            String sqlname = null;
            Map<String, Object> params = null;
            if (!ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H1.getValue())) {
                if(bizMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
                    sqlname = "searchBankAcct_Customer_Identified";
                    params = NFCL3120CommonLogic.createSsmMap_Identified(bizMsg, CSV_LIMIT_COUNT);
                } else {
                    sqlname = "searchBankAcct_Customer_Unidentified";
                    params = NFCL3120CommonLogic.createSsmMap_Unidentified(bizMsg, CSV_LIMIT_COUNT);
                }
            } else {
                sqlname = "searchBankAcct_Customer_Unidentified";
                params = NFCL3120CommonLogic.createSsmMap_Unidentified(bizMsg, CSV_LIMIT_COUNT);
            }
            stmtSelect = ssmLLClient.createPreparedStatement(sqlname, params, execParam);
            rs = stmtSelect.executeQuery();
            while (rs.next()) {
                ZYPEZDItemValueSetter.setValue(fMsg.dsBankAcctNum_A1, rs.getString("DS_BANK_ACCT_NUM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsBankAcctNm_A1, rs.getString("DS_BANK_ACCT_NM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A1, rs.getString("DS_ACCT_NUM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A1, rs.getString("DS_ACCT_NM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.bankRteNum_A1, rs.getString("BANK_RTE_NUM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.dsBankBrNm_A1, rs.getString("DS_BANK_BR_NM_A1"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A1, convertDateFormat(rs.getString("EFF_FROM_DT_A1")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A2, convertDateFormat(rs.getString("EFF_THRU_DT_A1")));
                csvOutFile.write();
            }
            csvOutFile.close();
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    // END 2016/07/27 K.Kojima [QC#4870,ADD]

    private void doProcess_NFCL3120Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg globalMsg = (NFCL3120SMsg) sMsg;

        // START 2016/08/05 K.Kojima [QC#5521,ADD]
        bizMsg.srchOptPk_S.clear();
        bizMsg.srchOptNm_S.clear();
        // END 2016/08/05 K.Kojima [QC#5521,ADD]
        bizMsg.xxChkBox_H1.clear();
        bizMsg.dsAcctNum_H1.clear();
        bizMsg.dsAcctNm_H1.clear();
        bizMsg.dsBankAcctNum_H1.clear();
        bizMsg.dsBankAcctNm_H1.clear();
        // START 2016/08/05 K.Kojima [QC#5521,ADD]
        bizMsg.dsBankBrNm_H1.clear();
        // END 2016/08/05 K.Kojima [QC#5521,ADD]
        bizMsg.dsBankCd_H1.clear();
        bizMsg.bankRteNum_H1.clear();
        bizMsg.xxRadioBtn.setValue(1);
        bizMsg.A.setValidCount(0);
        bizMsg.A.clear();
        globalMsg.A.setValidCount(0);
        globalMsg.A.clear();
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3120Scrn00_OnChangeRadio(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg globalMsg = (NFCL3120SMsg) sMsg;

        if(bizMsg.xxRadioBtn.getValue().equals(BigDecimal.ONE)) {
            bizMsg.dsBankAcctTpCd_H1.setValue(DS_BANK_ACCT_TP.EXTERNAL);
        } else {
            bizMsg.dsBankAcctTpCd_H1.setValue(DS_BANK_ACCT_TP.INTERNAL);
        }
        
        bizMsg.xxChkBox_H1.clear();
        bizMsg.dsAcctNum_H1.clear();
        bizMsg.dsAcctNm_H1.clear();
        bizMsg.dsBankCd_H1.clear();
        bizMsg.bankRteNum_H1.clear();
        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);
    }

    // START 2016/07/25 K.Kojima [QC#4870,ADD]
    private void doProcess_NFCL3120Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg globalMsg = (NFCL3120SMsg) sMsg;
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        NFCL3120CommonLogic.pagenation(bizMsg, globalMsg, pagenationFrom);
    }

    private void doProcess_NFCL3120Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg globalMsg = (NFCL3120SMsg) sMsg;
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() + bizMsg.A.length() - 1;
        NFCL3120CommonLogic.pagenation(bizMsg, globalMsg, pagenationFrom);
    }

    private void doProcess_NFCL3120Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg globalMsg = (NFCL3120SMsg) sMsg;
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length() - 1;
        NFCL3120CommonLogic.pagenation(bizMsg, globalMsg, pagenationFrom);
    }

    /**
     * convertDateFormat
     * @param date String
     * @return String
     */
    private static String convertDateFormat(String date) {
        if (ZYPCommonFunc.hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }

    // END 2016/07/25 K.Kojima [QC#4870,ADD]

    // START 2016/08/02 K.Kojima [QC#5521,ADD]
    /**
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NFCL3120Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg globalMsg = (NFCL3120SMsg) sMsg;

        NFCL3120CommonLogic.clearPageNum(bizMsg);

        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        S21SortTarget sortTarget = new S21SortTarget(globalMsg.A, globalMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, globalMsg.A.getValidCount());

        EZDMsg.copy(globalMsg.A, null, bizMsg.A, null);
        NFCL3120CommonLogic.pagenation(bizMsg, globalMsg, 0);
        bizMsg.xxPageShowOfNum.setValue(globalMsg.A.getValidCount());
    }

    /**
     * Method name: doProcess_NFCL3120Scrn00_OnChangeSavedSearchOption
     * <dd>The method explanation: OnChangeSavedSearchOption <dd>
     * Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL3120Scrn00_OnChangeSavedSearchOption(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3120CMsg bizMsg = (NFCL3120CMsg) cMsg;
        NFCL3120SMsg glblMsg = (NFCL3120SMsg) sMsg;

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NFCL3120CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), this.getGlobalCompanyCode());
        }
    }
    // END 2016/08/02 K.Kojima [QC#5521,ADD]

}
