/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1010;

import static business.blap.NLCL1010.constant.NLCL1010Constant.CSV_FILE_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCMimeSourceItem;
import business.blap.NLCL1010.constant.NLCL1010Constant;
import business.file.NLCL1010F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmStringResultSetHandlerSupport;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 * 2013/10/30   Hitachi         T.Kawazu        Update          QC2852
 * 2017/02/09   CITS            M.Naito         Update          QC#12673
 * </pre>
 */
public final class NLCL1010Query extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NLCL1010Query MY_INSTANCE = new NLCL1010Query();

    /** CSV Max Record. */
    private static final int CSV_MAX_RECORD = 65000;

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NLCL1010Query() {
        super();
    }

    /**
     * <pre>
     * Get the NLCL1010Query instance.
     * </pre>
     * @return NLCL1010Query instance
     */
    public static NLCL1010Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Find serial transaction.
     * @param queryParam Map<String,Object>
     * @param glblMsg NLCL1010SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult findSerTrx(Map<String, Object> queryParam, NLCL1010SMsg glblMsg) {
        return getSsmEZDClient().queryEZDMsgArray("findSerTrx", queryParam, glblMsg.A);
    }

    /**
     * create serial transaction CSV.
     * @param cMsg NLCL1010CMsg
     * @param queryParam Map<String, Object>
     * @return String
     */
    public String createSerTrxCSV(NLCL1010CMsg cMsg, Map<String, Object> queryParam) {
        queryParam.put("rowNum", CSV_MAX_RECORD + 1);
        queryParam.put("invtyLocTpVnd", LOC_TP.VENDOR);
        queryParam.put("invtyLocTpCust", LOC_TP.CUSTOMER);

        return (String) S21SsmBatchClient.getClient(this.getClass()).queryObject("findSerTrxCsvList", queryParam, new SerTrxCsvCreator(cMsg.xxFileData));
    }

    /**
     * Serial Transaction CSV Creator.
     * @author T.Tozuka
     */
    private static final class SerTrxCsvCreator extends S21SsmStringResultSetHandlerSupport {

        /** csv header. */
        // 10/19/2015 mod start
//        private static final String[] CSV_HEADER = {"Serial#", "Mdse Code", "Mdse Name", "Event Name", "Trx Date", "Man", "Location Code (From)", "Location Name (From)", "Location Code (To)", "Location Name (To)", "Trx Source",
//                "Trx SRC Header#", "Line", "Line Sub", "Serial Trx Ref#", "Original MDSE", "Comment", "Error Status Name", "Error Status Desc", };
        private static final String[] CSV_HEADER = {"Serial#", "Item Num", "Item Name", "Event Name", "Trx Date Time", "Man", "Location (From)", "Location (To)", 
                                                      "Stock Status Code (From)", "Stock Status Name (From)", "Stock Status Code", "Stock Status Name", "Trx Source", "Trx SRC Header#", "Line", "Line Sub", "Serial Trx Ref#", "Original Item", 
                                                      "Comment","Error Status Name", "Error Status Desc", };
        // 10/19/2015 mod end

        /**
         * select.
         */
        private enum SELECT {
            /** SER_NUM */
            SER_NUM,
            /** MDSE_CD */
            MDSE_CD,
            // 10/19/2015 mod start
//            /** XX_DT_TXT */
//            XX_DT_TXT,
            /** XX_DT_NM */
            XX_DT_NM,
            // 10/19/2015 mod end
            /** SER_TRX_SRC_HDR_NUM */
            SER_TRX_SRC_HDR_NUM,
            /** SER_TRX_SRC_LINE_NUM */
            SER_TRX_SRC_LINE_NUM,
            /** SER_TRX_SRC_LINE_SUB_NUM */
            SER_TRX_SRC_LINE_SUB_NUM,
            /** SER_TRX_REF_NUM */
            SER_TRX_REF_NUM,
            /** FROM_LOC */
            FROM_LOC,
            /** TO_LOC */
            TO_LOC,
            /** FROM_STK_STS_CD */
            FROM_STK_STS_CD,          // 10/19/2015 add 
            /** FROM_STK_STS_NM */
            FROM_STK_STS_DESC_TXT,    // 10/19/2015 add 
            /** TO_STK_STS_CD */
            TO_STK_STS_CD,            // 10/19/2015 add 
            /** TO_STK_STS_NM */
            TO_STK_STS_DESC_TXT,      // 10/19/2015 add 
            /** ORIG_MDSE_CD */
            ORIG_MDSE_CD,
            /** XX_YES_NO_CD */
            XX_YES_NO_CD,
            /** MAN_CRAT_FLG */
            MAN_CRAT_FLG,
            /** MDSE_NM */
            MDSE_DESC_SHORT_TXT,
            /** SER_TRX_EVENT_DESC_TXT */
            SER_TRX_EVENT_DESC_TXT,
            /** SER_TRX_SRC_TP_DESC_TXT */
            SER_TRX_SRC_TP_DESC_TXT,
            /** SER_ERR_STS_NM */
            SER_ERR_STS_NM,
            /** SER_ERR_STS_DESC_TXT */
            SER_ERR_STS_DESC_TXT,
        }

        /** output csv data. */
        private EZDCMimeSourceItem xxFileData;

        /**
         * Instantiates a new ser trx csv creator.
         * @param xxFileData EZDCMimeSourceItem
         */
        private SerTrxCsvCreator(EZDCMimeSourceItem xxFileData) {
            this.xxFileData = xxFileData;
        }

        @Override
        protected String doProcessQueryResult(ResultSet rs) throws SQLException {

            rs.last();

            int rowCount = rs.getRow();

            if (rowCount == 0) {
                return NLCL1010Constant.NZZM0000E;
            }

            if (rowCount > CSV_MAX_RECORD) {
                return NLCL1010Constant.NZZM0007E;
            }

            rs.beforeFirst();

            createCsvFile(rs);
            return "";
        }

        /**
         * Creates the csv file.
         * @param rs ResultSet
         * @throws SQLException the sQL exception
         */
        private void createCsvFile(ResultSet rs) throws SQLException {

            // CSV file path.
            final String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME);
            xxFileData.setTempFilePath(null, csvFileNm, ".csv");

            // CSV file writer.
            final NLCL1010F00FMsg fMsg = new NLCL1010F00FMsg();
            final ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(xxFileData.getTempFilePath(), fMsg);
            fileWriter.writeHeader(CSV_HEADER);

            while (rs.next()) {
                writeCsvLine(fMsg, rs);
                fileWriter.write();
            }

            fileWriter.close();
        }

        // 10/19/2015 delete start
//        /**
//         * Date format.
//         * @param dateStr String
//         * @return string
//         */
//        private static String dateFormat(String dateStr) {
//            if (!ZYPCommonFunc.hasValue(dateStr)) {
//                return dateStr;
//            } else {
//                // Update QC2852 Start
//                //return ZYPDateUtil.DateFormatter(dateStr, "yyyyMMdd", "MM/dd/yyyy");
//                return ZYPDateUtil.formatEzd8ToDisp(dateStr);
//                // Update QC2852 End
//            }
//        }
        // 10/19/2015 delete end

        /**
         * Gets string.
         * @param rs ResultSet
         * @param select SELECT
         * @return string
         * @throws SQLException the sQL exception
         */
        private String getString(ResultSet rs, SELECT select) throws SQLException {
            return nullToEmpty(rs.getString(select.toString()));
        }

        /**
         * Null to empty.
         * @param str String
         * @return string
         */
        private String nullToEmpty(String str) {
            if (str == null) {
                return "";
            }
            return str;
        }

        /**
         * Write csv line.
         * @param fMsg NLCL1010F00FMsg
         * @param rs ResultSet
         * @throws SQLException the sQL exception
         */
        private void writeCsvLine(NLCL1010F00FMsg fMsg, ResultSet rs) throws SQLException {
            ZYPEZDItemValueSetter.setValue(fMsg.serNum_A1, getString(rs, SELECT.SER_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A1, getString(rs, SELECT.MDSE_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A1, getString(rs, SELECT.MDSE_DESC_SHORT_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.serTrxEventDescTxt_A1, getString(rs, SELECT.SER_TRX_EVENT_DESC_TXT));
            // 10/19/2015 mod start
            // ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A1, dateFormat(getString(rs, SELECT.XX_DT_TXT)));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtNm_A1, getString(rs, SELECT.XX_DT_NM));
            // 10/19/2015 mod end
            ZYPEZDItemValueSetter.setValue(fMsg.manCratFlg_A1, getString(rs, SELECT.MAN_CRAT_FLG));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem500Txt_A1, getString(rs, SELECT.FROM_LOC));
            ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem500Txt_A2, getString(rs, SELECT.TO_LOC));
            ZYPEZDItemValueSetter.setValue(fMsg.stkStsCd_A1, getString(rs, SELECT.FROM_STK_STS_CD));            // 10/19/2015 add 
            ZYPEZDItemValueSetter.setValue(fMsg.stkStsDescTxt_A1, getString(rs, SELECT.FROM_STK_STS_DESC_TXT)); // 10/19/2015 add 
            ZYPEZDItemValueSetter.setValue(fMsg.stkStsCd_A2, getString(rs, SELECT.TO_STK_STS_CD));              // 10/19/2015 add 
            ZYPEZDItemValueSetter.setValue(fMsg.stkStsDescTxt_A2, getString(rs, SELECT.TO_STK_STS_DESC_TXT));   // 10/19/2015 add 
            ZYPEZDItemValueSetter.setValue(fMsg.serTrxSrcTpDescTxt_A1, getString(rs, SELECT.SER_TRX_SRC_TP_DESC_TXT));
            ZYPEZDItemValueSetter.setValue(fMsg.serTrxSrcHdrNum_A1, getString(rs, SELECT.SER_TRX_SRC_HDR_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.serTrxSrcLineNum_A1, getString(rs, SELECT.SER_TRX_SRC_LINE_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.serTrxSrcLineSubNum_A1, getString(rs, SELECT.SER_TRX_SRC_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.serTrxRefNum_A1, getString(rs, SELECT.SER_TRX_REF_NUM));
            ZYPEZDItemValueSetter.setValue(fMsg.origMdseCd_A1, getString(rs, SELECT.ORIG_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.xxYesNoCd_A1, getString(rs, SELECT.XX_YES_NO_CD));
            ZYPEZDItemValueSetter.setValue(fMsg.serErrStsNm_A1, getString(rs, SELECT.SER_ERR_STS_NM));
            ZYPEZDItemValueSetter.setValue(fMsg.serErrStsDescTxt_A1, getString(rs, SELECT.SER_ERR_STS_DESC_TXT));

        }
    }
}
