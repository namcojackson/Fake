/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0090;

import static business.blap.NFDL0090.constant.NFDL0090Constant.NZZM0000E;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMimeSourceItem;
import business.blap.NFDL0090.constant.NFDL0090Constant;
import business.file.NFDL0090F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Fujitsu         M.Nakamura      Create          N/A
 * 2018/07/06   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/24   Hitachi         Y.Takeno        Update          QC#26989-1
 * 2019/03/27   Fujitsu         S.Takami        Update          QC#30758
 * 2021/02/15   CITS            A.Raguero       Update          QC#56199
 * 2021/02/18   CITS            A.Raguero       Update          QC#56199
 * 2022/12/02   Hitachi         S.Fujita        Update          QC#60034 
 *</pre>
 */
public final class NFDL0090Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NFDL0090Query MY_INSTANCE = new NFDL0090Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NFDL0090Query() {
        super();
    }

    /**
     * <pre>
     * Get the NFDL0090Query instance.
     * </pre>
     * @return NFDL0090Query instance
     */
    public static NFDL0090Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getArTrxBal.
     * @param cMsg NFDL0090CMsg
     * @param sMsg NFDL0090SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArTrxBal(NFDL0090CMsg cMsg, NFDL0090SMsg sMsg) {
        Map<String, Object> ssmParam = getArTrxBalCond(cMsg);
        return getSsmEZDClient().queryEZDMsgArray("getArTrxBal", ssmParam, sMsg.A);
    }

    private Map<String, Object> getArTrxBalCond(NFDL0090CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("acctDt", cMsg.acctDt_H1.getValue());
        ssmParam.put("minus", BigDecimal.ONE.negate());
        ssmParam.put("rcpt", AR_TRX_TP.RECEIPT);
        ssmParam.put("inv", AR_TRX_TP.INVOICE);
        ssmParam.put("dem", AR_TRX_TP.DEBIT_MEMO);
        ssmParam.put("ded", AR_TRX_TP.DEDUCTION);
        ssmParam.put("crm", AR_TRX_TP.CREDIT_MEMO);
        ssmParam.put("acc", AR_TRX_TP.ON_ACCOUNT);
        ssmParam.put("baseSvcInvChargTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        ssmParam.put("fmtYMD", "YYYY/MM/DD");
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("unapply", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmParam.put("partial", AR_CASH_APPLY_STS.PARTIAL);
        ssmParam.put("accountNum", cMsg.dsAcctNum_H1.getValue());
        ssmParam.put("billToCustCd", cMsg.billToCustCd_H1.getValue());
        // START 2018/07/24 [QC#26989-1, MOD]
        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt_H1)) {
            String[] arCustRefNumList = cMsg.xxQueryFltrTxt_H1.getValue().split(",");
            ssmParam.put("arCustRefNumList", arCustRefNumList);
        } else {
            ssmParam.put("arCustRefNumList", null);
        }
        ssmParam.put("custIssPoNum", cMsg.custIssPoNum_H1.getValue());
        // END   2018/07/24 [QC#26989-1, MOD]
        return ssmParam;
    }

    /**
     * getWrtOffActv.
     * @param cMsg NFDL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDefWrtOffActv(NFDL0090CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());

        return getSsmEZDClient().queryObject("getWrtOffActv", ssmParam);
    }
    // START 2022/12/02 S.Fujita [QC#60034, ADD]
    /**
     * getArAdjTp.
     * @param cMsg NFDL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArAdjTp(NFDL0090CMsg cMsg, List<String> funcList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("arAdjCatgAdj", AR_ADJ_CATG.ADJUSTMENT);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("funcList", funcList);

        return getSsmEZDClient().queryObjectList("getArAdjTp", ssmParam);
    }
    // END 2022/12/02 S.Fujita [QC#60034, ADD]
    /**
     * getCltPtfoPk.
     * @param cMsg NFDL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCltPtfoPk(NFDL0090CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("dsAcctNum", cMsg.dsAcctNum_H1.getValue());

        return getSsmEZDClient().queryObject("getCltPtfoPk", ssmParam);
    }

    // START 2021/02/15 A.Raguero [QC#56199, ADD]
    /**
     * getArWrtOffNoteNm
     * @param cMsg NFDL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArWrtOffNoteDesc(NFDL0090CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("arWrtOffNoteCd", cMsg.arWrtOffNoteCd_FS.getValue());

        return getSsmEZDClient().queryObject("getArWrtOffNoteDesc", ssmParam);
    }
    // END 2021/02/15 A.Raguero [QC#56199, ADD]

    // START 2021/02/18 A.Raguero [QC#56199, ADD]
    /**
     * getArAdjTpDesc
     * @param cMsg NFDL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArAdjTpDesc(NFDL0090CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("arAdjTpCd", cMsg.arAdjTpCd_FS.getValue());

        return getSsmEZDClient().queryObject("getArAdjTpDesc", ssmParam);
    }
    // END 2021/02/18 A.Raguero [QC#56199, ADD]

    public void createDetailCSV(NFDL0090CMsg bizMsg) {

        Map<String, Object> ssmParam = getArTrxBalCond(bizMsg);
        Object queryObject = S21SsmBatchClient.getClient(this.getClass()).queryObject(
                            "getArTrxBal",
                            ssmParam,
                            new CsvCreator(bizMsg.xxFileData)
                        );
        // START 2019/03/27 S.Takami [QC#30758,ADD]
        if (queryObject instanceof Boolean //
                && ((Boolean) queryObject).equals(Boolean.FALSE)) {
            bizMsg.setMessageInfo(NZZM0000E);
        }
        // END 2019/03/27 S.Takami [QC#30758,ADD]
    }
    

    private static final class CsvCreator extends S21SsmBooleanResultSetHandlerSupport {

        /** CSV Header */
        private static final String[] CSV_HEADER = {
                  "AR Transaction Balance Primary Key"
                , "Bill To"
                , "Contract/Order#"
                , "Transaction#"
                , "Trx Type"
                , "ConBill#"
                , "Line Type"
                , "Bill Period From"
                , "Bill Period To"
                , "Orig Amt"
                , "Remain Amt"
                , "Pending Amt"
                , "Write Off Amt"
                , "Invoice Date"
                , "Due Date"
                , "Days Past Due"
                , "PO Number" // 2018/07/24 [QC#26989-1, ADD]
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
            final String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm("WriteOffList");
            xxFileData.setTempFilePath(null, csvFileNm, ".csv");

            // CSV file writer.
            final NFDL0090F00FMsg fMsg       = new NFDL0090F00FMsg();
            final ZYPCSVOutFile   fileWriter = new ZYPCSVOutFile(xxFileData.getTempFilePath(), fMsg);
            fileWriter.writeHeader(CSV_HEADER);

            do {

                final int row = rs.getRow();
                if (row > NFDL0090Constant.MAX_DOWNLOAD_CNT) {
                    break;
                }

                writeCsvLine(fMsg, rs);
                fileWriter.write();

            } while(rs.next());

            fileWriter.close();
        }

        private static void writeCsvLine(NFDL0090F00FMsg fMsg, ResultSet rs) throws SQLException {
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxBalPk_A1, rs.getBigDecimal("AR_TRX_BAL_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_A1, rs.getString("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem30Txt_A1, rs.getString("DISP_NUM"));
            // START 2018/07/17 [QC#26989, MOD]
            // ZYPEZDItemValueSetter.setValue(fMsg.arTrxNum_A1, rs.getString("AR_TRX_NUM"));
            // ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem20Txt_A1, rs.getString("DOC_TYPE"));
            // END   2018/07/17 [QC#26989, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.arCustRefNum_A1, rs.getString("AR_CUST_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxTpNm_A1, rs.getString("AR_TRX_TP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.grpInvNum_A1, rs.getString("GRP_INV_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem20Txt_A2, rs.getString("LINE_TYPE"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgPerFromDt_A1, rs.getString("BLLG_PER_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgPerToDt_A1, rs.getString("BLLG_PER_TO_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dealOrigGrsAmt_A1, rs.getBigDecimal("DEAL_ORIG_GRS_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt_A1, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dealApplyAdjRsvdAmt_A1, rs.getBigDecimal("DEAL_APPLY_ADJ_RSVD_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDealApplyAmtNum_A1, rs.getBigDecimal("WRT_OFF_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxDt_A1, rs.getString("AR_TRX_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.invDueDt_A1, rs.getString("INV_DUE_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.pastDtAot_A1, rs.getBigDecimal("PAST_DT_AOT"));
            // START 2018/07/24 [QC#26989-1, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.custIssPoNum_A1, rs.getString("CUST_ISS_PO_NUM"));
            // END   2018/07/24 [QC#26989-1, ADD]
        }
    }
}
