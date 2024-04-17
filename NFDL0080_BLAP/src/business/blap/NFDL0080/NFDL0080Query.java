/**
 * <Pre>
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 * </Pre>
 */
package business.blap.NFDL0080;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMimeSourceItem;

import business.blap.NFDL0080.constant.NFDL0080Constant;
import business.file.NFDL0080F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NFDL0080Query.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/20   Fujitsu         M.Nakamura       Create          N/A
 * 2017/11/15   Hitachi         Y.Takeno         Update          QC#17322
 * 2018/07/20   Hitachi         Y.Takeno         Update          QC#26989
 * 2018/07/24   Hitachi         Y.Takeno         Update          QC#26989-1
 * 2018/10/03   Hitachi         J.Kim            Update          QC#28568
 * 2019/07/31   Fujitsu         M.Ishii          Update          QC#52217
 * 2019/12/23   Fujitsu         H.Ikeda          Update          QC#54619
 * 2020/04/16   CITS            R.Azucena        Update          QC#58672
 * 2022/08/10   Hitachi         S.Naya           Update          QC#56154
 * </pre>/
 */
public final class NFDL0080Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFDL0080Query MY_INSTANCE = new NFDL0080Query();

    /**
     * Constructor
     */
    public NFDL0080Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0080Query
     */
    public static NFDL0080Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param cMsg NFDL0080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRcptData(NFDL0080CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsg("findRcptData", cMsg, cMsg);
    }

    /**
     * <pre>
     * </pre>
     * @param bizMsg NFDL0080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findHeaderInfo(NFDL0080CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        // START 2018/10/12 J.Kim [QC#28580,MOD]
        // ssmParam.put("acctDt", bizMsg.glDt_H1.getValue());
        ssmParam.put("salesDt", ZYPDateUtil.getSalesDate());
        // END 2018/10/12 J.Kim [QC#28580,MOD]
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        ssmParam.put("arTrxBalPk", bizMsg.arTrxBalPk_H1.getValue());

        return getSsmEZDClient().queryObject("findHeaderInfo", ssmParam);
    }

    /**
     * <pre>
     * </pre>
     * @param bizMsg NFDL0080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findRcptInfo(NFDL0080CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        ssmParam.put("arTrxBalPk", bizMsg.arTrxBalPk_H1.getValue());

        return getSsmEZDClient().queryObject("findRcptInfo", ssmParam);
    }

    /**
     * <pre>
     * </pre>
     * @param bizMsg NFDL0080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findDsAcctReln(NFDL0080CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        ssmParam.put("accountNum", bizMsg.dsAcctNum_H1.getValue());
        List<String> acctRelnTps = new ArrayList<String>();
        acctRelnTps.add(DS_ACCT_RELN_TP.PARENT_ACCOUNT);
        acctRelnTps.add(DS_ACCT_RELN_TP.RELATED_ACCOUNT);
        ssmParam.put("acctRelnTps", acctRelnTps);
        ssmParam.put("relnAcct", bizMsg.dsAcctNum_H2.getValue());

        return getSsmEZDClient().queryObject("findDsAcctReln", ssmParam);
    }

    // START 2021/04/16 R.Azucena [QC#58672, ADD]
    /**
     * @param bizMsg NFDL0080CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findArDsWfStsCd(NFDL0080CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        ssmParam.put("arTrxNum", bizMsg.arTrxNum_H1.getValue());

        return getSsmEZDClient().queryObject("findArDsWfStsCd", ssmParam);
    }
    // END 2021/04/16 R.Azucena [QC#58672, ADD]

    /**
     * <pre>
     * </pre>
     * @param bizMsg NFDL0080CMsg
     * @param globalMsg NFDL0080SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetailData(NFDL0080CMsg bizMsg, NFDL0080SMsg globalMsg) {
        Map<String, Object> ssmParam = getDetailDataCond(bizMsg);
        return getSsmEZDClient().queryEZDMsgArray((String)ssmParam.get("statementId"), ssmParam, globalMsg.A);
    }

    private Map<String, Object> getDetailDataCond(NFDL0080CMsg bizMsg) {

        String statementId = "findDetailData";
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd_H1.getValue());
        // START 2018/10/12 J.Kim [QC#28580,MOD]
        // ssmParam.put("acctDt", bizMsg.glDt_H1.getValue());
        String salesDt = ZYPDateUtil.getSalesDate();
        ssmParam.put("salesDt", salesDt);
        // END 2018/10/12 J.Kim [QC#28580,MOD]
        ssmParam.put("accountNum", bizMsg.dsAcctNum_H1.getValue());
        ssmParam.put("arTrxTpInv", AR_TRX_TP.INVOICE);
        ssmParam.put("arTrxTpDed", AR_TRX_TP.DEDUCTION);
        ssmParam.put("arTrxTpDem", AR_TRX_TP.DEBIT_MEMO);
        ssmParam.put("unApply", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmParam.put("partial", AR_CASH_APPLY_STS.PARTIAL);
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H1)) {
            ssmParam.put("related", bizMsg.xxChkBox_H1.getValue());
            ssmParam.put("tpRelnAcct", DS_ACCT_RELN_TP.RELATED_ACCOUNT);

            if (ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H2)) {
                ssmParam.put("relnAcct", bizMsg.dsAcctNum_H2.getValue());
                // START 2017/11/15 [QC#17322, ADD]
                List<String> acctRelnTps = new ArrayList<String>();
                acctRelnTps.add(DS_ACCT_RELN_TP.PARENT_ACCOUNT);
                acctRelnTps.add(DS_ACCT_RELN_TP.RELATED_ACCOUNT);
                ssmParam.put("acctRelnTps", acctRelnTps);
                // END   2017/11/15 [QC#17322, ADD]
                statementId = "findDetailDataReln";
            }
        }
        // 2019/07/31 QC#52217 Mod Start
//        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxCdSrchTxt_H1)) {
//            String[] invoices = bizMsg.xxTrxCdSrchTxt_H1.getValue().split(",");
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt_H1)) {
            String[] invoices = bizMsg.xxTrxNumSrchTxt_H1.getValue().split(",");
        // 2019/07/31 QC#52217 Mod End
            ssmParam.put("invoices", invoices);
        }
        // START 2018/07/24 [QC#26989-1, ADD]
        ssmParam.put("custIssPoNum", bizMsg.custIssPoNum_H2.getValue());
        // END   2018/07/24 [QC#26989-1, ADD]
        // START 2022/08/10 [QC#56154, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.xxTrxNumSrchTxt_H2)) {
            String[] grpInvNum = bizMsg.xxTrxNumSrchTxt_H2.getValue().split(",");
            ssmParam.put("grpInvNum", grpInvNum);
        }
        // END   2022/08/10 [QC#56154, ADD]
        ssmParam.put("statementId", statementId);
        return ssmParam;
    }
    
    public void createDetailCSV(NFDL0080CMsg bizMsg) {

        Map<String, Object> ssmParam = getDetailDataCond(bizMsg);

        Object queryObject = S21SsmBatchClient.getClient(this.getClass()).queryObject(
                            (String)ssmParam.get("statementId"),
                            ssmParam,
                            new CsvCreator(bizMsg.xxFileData)
                        );
        // START 2019/03/25 S.Takami [QC#30758,ADD]
        if (queryObject instanceof Boolean //
                && ((Boolean) queryObject).equals(Boolean.FALSE)) {
            bizMsg.setMessageInfo("ZZZM9001E");
        }
        // End 2019/03/25 S.Takami [QC#30758,ADD]
    }
    

    private static final class CsvCreator extends S21SsmBooleanResultSetHandlerSupport {

        /** CSV Header */
        private static final String[] CSV_HEADER = {
                  "AR Transaction Balance Primary Key"
                , "Account#"
                , "Bill To"
                , "Invoice#"
                , "Invoice Date"
                , "Trx Type"
                , "Orig Amt"
                , "Remain Amt"
                , "Due Date"
                , "Days Past Due"
                , "Amt To Apply"
                , "Contract/Order#"
                , "ConBill#"
                , "PO Number"
                , "Bill Period From"
                , "Bill Period To"
        };

        /** File data */
        private EZDCMimeSourceItem xxFileData;

        private CsvCreator(EZDCMimeSourceItem xxFileData) {
            this.xxFileData = xxFileData;
        }

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return false;
            }

            createCsvFile(rs);
            return true;
        }

        private void createCsvFile(ResultSet rs) throws SQLException {

            // CSV file path.
            final String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm("PostApplicationList");
            xxFileData.setTempFilePath(null, csvFileNm, ".csv");

            // CSV file writer.
            final NFDL0080F00FMsg fMsg       = new NFDL0080F00FMsg();
            final ZYPCSVOutFile   fileWriter = new ZYPCSVOutFile(xxFileData.getTempFilePath(), fMsg);
            fileWriter.writeHeader(CSV_HEADER);

            do {

                final int row = rs.getRow();
                if (row > NFDL0080Constant.MAX_DOWNLOAD_CNT) {
                    break;
                }

                writeCsvLine(fMsg, rs);
                fileWriter.write();

            } while(rs.next());

            fileWriter.close();
        }

        private static void writeCsvLine(NFDL0080F00FMsg fMsg, ResultSet rs) throws SQLException {
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxBalPk, rs.getBigDecimal("AR_TRX_BAL_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.arCustRefNum, rs.getString("AR_CUST_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxDt, rs.getString("AR_TRX_DT"));
            // START 2018/07/20 [QC#26989, MOD]
            // ZYPEZDItemValueSetter.setValue(fMsg.arTrxTpCd, rs.getString("AR_TRX_TP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxTpNm, rs.getString("AR_TRX_TP_NM"));
            // END   2018/07/20 [QC#26989, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.dealOrigGrsAmt, rs.getBigDecimal("DEAL_ORIG_GRS_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.invDueDt, rs.getString("INV_DUE_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.pastDtAot, rs.getBigDecimal("PAST_DT_AOT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDealApplyAmtNum, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum, rs.getString("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.grpInvNum, rs.getString("GRP_INV_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.custIssPoNum, rs.getString("CUST_ISS_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgPerFromDt, rs.getString("BLLG_PER_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgPerToDt, rs.getString("BLLG_PER_TO_DT"));
        }
    }

    // START 2019/12/23 H.Ikeda [QC#54619, ADD]
    /**
     * findCanselCashApp
     * 
     * @param bizMsg NFDL0080CMsg
     * @return       S21SsmEZDResult
     */
    public S21SsmEZDResult findCanselCashApp(NFDL0080CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",     bizMsg.glblCmpyCd_H1.getValue());
        ssmParam.put("arTrxBalPk",     bizMsg.arTrxBalPk_H1.getValue());
        ssmParam.put("flgN",           ZYPConstant.FLG_OFF_N);
        ssmParam.put("arApplyTpCdAdj", AR_APPLY_TP.ADJUSTMENT);

        return getSsmEZDClient().queryObject("findCanselCashApp", ssmParam);
    }

    /**
     * findCanselArApplyIntfcWrk
     * 
     * @param bizMsg  NFDL0080CMsg
     * @param rcptNum String
     * @return        S21SsmEZDResult
     */
    public S21SsmEZDResult findCanselArApplyIntfcWrk(NFDL0080CMsg bizMsg, String rcptNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd",      bizMsg.glblCmpyCd_H1.getValue());
        ssmParam.put("rcptNum",         rcptNum);
        ssmParam.put("invNum",          bizMsg.arTrxNum_H1.getValue());
        ssmParam.put("arAdjNum",        bizMsg.arAdjNum_H1.getValue());
        ssmParam.put("arAdjTrxTpCdAcc", AR_ADJ_TRX_TP.ON_ACCOUNT);

        return getSsmEZDClient().queryObject("findCanselArApplyIntfcWrk", ssmParam);
    }
    // END   2019/12/23 H.Ikeda [QC#54619, ADD]

}
