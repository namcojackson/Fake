/**
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFDL0070;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMimeSourceItem;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFDL0070.constant.NFDL0070Constant;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.file.NFDL0070F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_RF_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * NFDL0070Query.
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/16   Fujitsu         M.Yamada        Create          N/A
 * 2018/07/20   Hitachi         Y.Takeno        Update          QC#26989
 * 2019/03/25   Fujitsu         S.Takami        Update          QC#30758
 * 2022/04/22   CITS            K.Suzuki        Update          QC#59333
 * 2022/05/18   CITS            D.Mamaril       Update          QC#59333
 *</pre>
 */
public final class NFDL0070Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFDL0070Query MY_INSTANCE = new NFDL0070Query();

    /**
     * Constructor
     */
    public NFDL0070Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0070Query
     */
    public static NFDL0070Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param map Map
     * @param sMsg NFDL0070SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findApplicationInfoList(Map<String, Object> map, NFDL0070SMsg sMsg) {

        return getSsmEZDClient().queryEZDMsgArray("findApplicationInfoList", map, sMsg.A);
    }

    /**
     * getAcctNm
     * @param cMsg NFDL0070CMsg
     * @return boolean
     */
    public boolean getAcctNm(NFDL0070CMsg cMsg) {

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("sellToCustCd01", cMsg.billToCustAcctCd.getValue());
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        SELL_TO_CUSTTMsgArray outMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray == null || outMsgArray.getValidCount() == 0) {
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, outMsgArray.no(0).dsAcctNm);
            return true;
        }
    }

    public void createDetailCSV(NFDL0070CMsg bizMsg, Map<String, Object> ssmParam) {
        Object queryObject = S21SsmBatchClient.getClient(this.getClass()).queryObject(
                            "findApplicationInfoList",
                            ssmParam,
                            new CsvCreator(bizMsg.xxFileData)
                        );
        // START 2019/03/25 S.Takami [QC#30758,ADD]
        if(queryObject instanceof Boolean //
                && ((Boolean) queryObject).equals(Boolean.FALSE)) {
            bizMsg.setMessageInfo(NFDL0070Constant.MSG_ID.NZZM0000E.name());
        }
        // END 2019/03/25 S.Takami [QC#30758,ADD]
    }

    private static final class CsvCreator extends S21SsmBooleanResultSetHandlerSupport {

        /** CSV Header */
        private static final String[] CSV_HEADER = {
                  "AR Transaction Balance Primary Key"
                , "Bill To"
                , "Transaction#"
                , "Trx Type"
                , "Trx/Bill Date"
                , "Contract#"
                , "ConBill#"
                , "PO Number"
                , "Bill Period From"
                , "Bill Period To"
                , "Orig Amt"
                , "Unapplied Amt"
                , "Amt To Apply"
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
            final NFDL0070F00FMsg fMsg       = new NFDL0070F00FMsg();
            final ZYPCSVOutFile   fileWriter = new ZYPCSVOutFile(xxFileData.getTempFilePath(), fMsg);
            fileWriter.writeHeader(CSV_HEADER);

            do {

                final int row = rs.getRow();
                if (row > NFDL0070Constant.MAX_DOWNLOAD_CNT) {
                    break;
                }

                writeCsvLine(fMsg, rs);
                fileWriter.write();

            } while(rs.next());

            fileWriter.close();
        }

        private static void writeCsvLine(NFDL0070F00FMsg fMsg, ResultSet rs) throws SQLException {
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxBalPk_A1, rs.getBigDecimal("AR_TRX_BAL_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_A1, rs.getString("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.arCustRefNum_A1, rs.getString("AR_CUST_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxTpNm_A1, rs.getString("AR_TRX_TP_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.arTrxDt_A1, rs.getString("AR_TRX_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrNum_A1, rs.getString("DS_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.grpInvNum_A1, rs.getString("GRP_INV_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.custIssPoNum_A1, rs.getString("CUST_ISS_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgPerFromDt_A1, rs.getString("BLLG_PER_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgPerToDt_A1, rs.getString("BLLG_PER_TO_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dealOrigGrsAmt_A1, rs.getBigDecimal("DEAL_ORIG_GRS_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt_A1, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt_A2, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT"));
        }
    }

    // START 2016/11/11 J.Kim [QC#15716,ADD]
    /**
     * getCreditCardRefundArRfRqstInfo
     * @param bizMsg NFDL0070CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCreditCardRefundArRfRqstInfo(NFDL0070CMsg bizMsg, String arTrxNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("arTrxNum", arTrxNum);
        param.put("arRfTpCd", AR_RF_TP.CREDIT_CARD_REFUND);
        // START 2022/05/18 D.Mamaril [QC#59333,ADD]
        param.put("arDsWfStsCd", AR_DS_WF_STS.REJECTED);
        // END 2022/05/18 D.Mamaril [QC#59333,ADD]
        return getSsmEZDClient().queryObject("getArRfRqstPk", param);
    }
    // END 2016/11/11 J.Kim [QC#15756,ADD]

    // START 2022/04/22 K.Suzuki [QC#59333,ADD]
    /**
     * getCountNotProcessRefundRqst
     * @param bizMsg NFDL0070CMsg
     * @param String arTrxNum
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCountNotProcessRefundRqst(NFDL0070CMsg bizMsg, String arTrxNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        param.put("arTrxNum", arTrxNum);
        // START 2022/05/18 D.Mamaril [QC#59333,ADD]
        param.put("arDsWfStsCd", AR_DS_WF_STS.REJECTED);
        // END 2022/05/18 D.Mamaril [QC#59333,ADD]
        return getSsmEZDClient().queryObject("getCountNotProcessRefundRqst", param);
    }
    // END   2022/04/22 K.Suzuki [QC#59333,ADD]
}
