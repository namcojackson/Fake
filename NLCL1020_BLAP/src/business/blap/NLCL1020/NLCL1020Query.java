/**
 * <pre>Copyright(c)2010 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1020;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NLCL1020.common.NLCL1020CommonLogic;
import business.blap.NLCL1020.constant.NLCL1020Constant;
import business.file.NLCL1020.NLCL1020F00FMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 * 02/16/2017   CITS            M.Naito         Update          QC#12673
 *</pre>
 */
public final class NLCL1020Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL1020Query MYINSTANCE = new NLCL1020Query();

    /**
     * Constructor
     */
    public NLCL1020Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLCL1020Query
     */
    public static NLCL1020Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * getInvtyOrdDetailInfoList
     * @param cMsg NLCL1020CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvtyOrdDetailInfoList(NLCL1020CMsg cMsg) {

        return getSsmEZDClient().queryEZDMsgArray("getInvtyOrdDetailInfoList", cMsg, cMsg.A);
    }

    /**
     * searchByTrxNum
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchByTrxNum(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchByTrxNum", ssmParam);
    }

    /**
     * get S21SsmBatchClient for CSV Download.
     * @return S21SsmBatchClient
     */
    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    boolean getResultForCsv(NLCL1020CMsg bizMsg, Map<String, Object> paramMap) {

        return (Boolean) getSsmBatchClient().queryObject("getResultForCsv", paramMap, new GetCsvResult(bizMsg));
    }

    /** DB Process */
    private final class GetCsvResult extends S21SsmBooleanResultSetHandlerSupport {

        /** Singleton instance. */
        private NLCL1020CMsg bizMsg;

        private GetCsvResult(NLCL1020CMsg bizMsg) {
            this.bizMsg = bizMsg;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            rs.last();
            int cnt = rs.getRow();

            if (cnt == 0) {
                bizMsg.setMessageInfo("NZZM0000E");
                return false;
            }

            if (cnt > NLCL1020Constant.DOWNLOAD_MAX_COUNT) {
                bizMsg.setMessageInfo("NZZM0007E");
                return false;
            }

            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NLCL1020Constant.CSV_FILE_NAME), NLCL1020Constant.CSV_EXT);

            NLCL1020F00FMsg fMsg = new NLCL1020F00FMsg();

            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            String[] csvHeader = getCsvHeaderList();
            csvOutFile.writeHeader(csvHeader);

            rs.first();

            do {
                NLCL1020CMsg tempCMsg = new NLCL1020CMsg();
                setValueToCMsg(tempCMsg, rs);
                EZDMsg.copy(tempCMsg, null, fMsg, null);
                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

            return Boolean.TRUE;
        }

        private void setValueToCMsg(NLCL1020CMsg tempMsg, ResultSet rs) throws SQLException {
            tempMsg.invtyLocCd_A.setValue(NLCL1020CommonLogic.checkNull(rs.getString("WH_CD")));
            tempMsg.mdseCd_A.setValue(NLCL1020CommonLogic.checkNull(rs.getString("ITEM_CD")));
            tempMsg.mdseDescShortTxt_A.setValue(NLCL1020CommonLogic.checkNull(rs.getString("ITEM_NM")));
            tempMsg.xxFldValTxt_A.setValue(NLCL1020CommonLogic.checkNull(rs.getString("PROD_DESC")));
            tempMsg.locDescTxt_A.setValue(NLCL1020CommonLogic.checkNull(rs.getString("LOC_DESC")));
            tempMsg.stkStsCd_A.setValue(NLCL1020CommonLogic.checkNull(rs.getString("STK_STS_CD")));
            tempMsg.invtyAvalQty_A.setValue(NLCL1020CommonLogic.checkNull(rs.getBigDecimal("AVAL_QTY")));
        }

        private String[] getCsvHeaderList() {
            List<String> csvHeaderList = new ArrayList<String>();
            csvHeaderList.add("Location");
            csvHeaderList.add("Item Code");
            csvHeaderList.add("Item Name");
            csvHeaderList.add("Product Control");
            csvHeaderList.add("Location Status");
            csvHeaderList.add("SS");
            csvHeaderList.add("Available Qty");

            return (String[]) csvHeaderList.toArray(new String[0]);
        }
    }

}
