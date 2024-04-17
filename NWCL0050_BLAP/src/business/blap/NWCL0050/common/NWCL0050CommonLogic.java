/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0050.common;

import static business.blap.NWCL0050.constant.NWCL0050Constant.CSV_FILE_EXT;
import static business.blap.NWCL0050.constant.NWCL0050Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NWCL0050.constant.NWCL0050Constant.CSV_MAX_ROW;
import static business.blap.NWCL0050.constant.NWCL0050Constant.ZZZM9001E;
import static business.blap.NWCL0050.constant.NWCL0050Constant.ZZZM9002W;

import java.sql.ResultSet;
import java.sql.SQLException;

import business.blap.NWCL0050.NWCL0050CMsg;
import business.blap.NWCL0050.NWCL0050Query;
import business.blap.NWCL0050.NWCL0050SMsg;
import business.file.NWCL0050F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/03/07   Fujitsu         K.Ishizuka      Create          N/A
 * 2018/05/28   Fujitsu         Y.Matsui        Update          QC#26342
 *</pre>
 */
public class NWCL0050CommonLogic {
    
    /**
     * CVS DownLoad Search result
     * @param bizMsg NWCL0050CMsg
     * @param glblMsg NWCL0050SMsg
     */
    public static void csvDownload(NWCL0050CMsg bizMsg, NWCL0050SMsg glblMsg) {

        NWCL0050Query.getInstance().searchForCSV(bizMsg, glblMsg, new CreateDownloadData(bizMsg));
    }

    /**
     * Create DownLoad Date for CSV
     */
    private static class CreateDownloadData extends S21SsmBooleanResultSetHandlerSupport {
        /** cMsg */
        private NWCL0050CMsg bizMsg;

        public CreateDownloadData(NWCL0050CMsg cMsg) {
            this.bizMsg = cMsg;
        }

        /** CSV Header Column */
        // QC#53014 2019/09/17 Mod Start
        // START 2018/05/28 Y.Matsui [QC#26342,MOD]
        // private static final String[] CSV_HEADER = {"Category", "URN Number", "Bill Number", "Invoice Number", "Account Number", "Customer Name", "Customer PO#", "Invoice Date", "Creation Date", "Amount "};
        // END   2018/05/28 Y.Matsui [QC#26342,MOD]
        private static final String[] CSV_HEADER = {"Category", "URN Number", "Bill Number", "Invoice Number", "Account Number", "Customer Name", "Customer PO#", "Invoice Date", "Creation Date", "Amount", "Original Bill Number"};
        // QC#53014 2019/09/17 Mod End

        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {
            if (!result.next()) {
                bizMsg.setMessageInfo(ZZZM9001E);
                return false;
            }
            NWCL0050F00FMsg fMsg = new NWCL0050F00FMsg();
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), CSV_FILE_EXT);
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            csvOutFile.writeHeader(CSV_HEADER);

            int rowCount = 1;
            do {
                if (rowCount > CSV_MAX_ROW) {
                    bizMsg.setMessageInfo(ZZZM9002W);
                    return true;
                }

                fMsg.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.invProcTpCd_A, result.getString("INV_PROC_TP_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxUrnNum_A, result.getString("URN_NUMBER"));
                ZYPEZDItemValueSetter.setValue(fMsg.conslBillNum_A, result.getString("CONSL_BILL_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.invNum_A, result.getString("INV_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToDsAcctNum_A, result.getString("BILL_TO_DS_ACCT_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToDsAcctNm_A, result.getString("BILL_TO_DS_ACCT_NM"));
                // START 2018/05/28 Y.Matsui [QC#26342,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.custIssPoNum_A, result.getString("CUST_ISS_PO_NUM"));
                // END   2018/05/28 Y.Matsui [QC#26342,ADD]
                ZYPEZDItemValueSetter.setValue(fMsg.conslBillInvDt_A, result.getString("CONSL_BILL_INV_DT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_A, result.getString("INV_CRAT_TS"));
                ZYPEZDItemValueSetter.setValue(fMsg.conslBillTotAmt_A, result.getBigDecimal("CONSL_BILL_TOT_AMT"));
                // QC#53014 2019/09/17 Mod Start
                ZYPEZDItemValueSetter.setValue(fMsg.origConslBillNum_A, result.getString("ORIG_CONSL_BILL_NUM"));
                // QC#53014 2019/09/17 Mod End
                csvOutFile.write();
                rowCount++;
            } while (result.next());
            csvOutFile.close();

            return true;
        }
    }

}
