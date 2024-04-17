/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL0090;

import static business.blap.NLBL0090.constant.NLBL0090Constant.BOL_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.CSV_FILE_EXT;
import static business.blap.NLBL0090.constant.NLBL0090Constant.CSV_FILE_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.CSV_MAX_ROWS;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_BOL_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_FROM_STK_STS_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_LOC_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_MDSE_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_MDSE_DESC_SHORT_TXT;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_ORD_QTY;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_POD_STS_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_POD_STS_DT;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_POD_STS_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_POD_STS_RSN_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_POD_STS_RSN_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_PRO_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_SCE_ORD_TP_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_SCE_ORD_TP_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_SELL_TO_CUST_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_SHIP_TO_CUST_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_SO_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_SO_SLP_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_TRX_HDR_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_COLUMN_WH_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.ITEM_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.ITEM_NUMBER;
import static business.blap.NLBL0090.constant.NLBL0090Constant.LOC_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.NZZM0000E;
import static business.blap.NLBL0090.constant.NLBL0090Constant.NZZM0007E;
import static business.blap.NLBL0090.constant.NLBL0090Constant.ORD_QTY;
import static business.blap.NLBL0090.constant.NLBL0090Constant.POD_STS_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.POD_STS_DT;
import static business.blap.NLBL0090.constant.NLBL0090Constant.POD_STS_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.POD_STS_RSN_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.POD_STS_RSN_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.PRO_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.SCE_ORD_TP_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.SCE_ORD_TP_NM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.SELL_TO_CUST_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.SHIP_TO_CUST_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.SO_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.SO_SLP_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.STK_STS_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.TRX_HDR_NUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.WH_CD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.file.NLBL0090F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 * 2010/06/15   Fujitsu         Mori            Update          6918 CSV Download
 * 2016/04/07   CITS            Y.Nomura        Update          for CSA
 *</pre>
 */
public final class NLBL0090Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL0090Query MY_INSTANCE = new NLBL0090Query();

    /**
     * Constructor.
     */
    private NLBL0090Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return SSML0001Query
     */
    public static NLBL0090Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get S21SsmBatchClient for CSV Download
     * @return S21SsmBatchClient
     */
    private S21SsmBatchClient getSsmBatchClient() {
        return S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * get WH
     * </pre>
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWH(Map<String, Object> ssmParam) {

        return getSsmEZDClient().queryObjectList("getWH", ssmParam);
    }

    /**
     * make pullDownList(POD Status Type)
     * @param cMessage NLBL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPODStatusTypePullDownList(NLBL0090CMsg cMessage) {

        return getSsmEZDClient().queryObjectList("getPODStatusTypePullDownList", cMessage);
    }

    /**
     * make pullDownList(POD Status)
     * @param cMessage NLBL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPODStatusPullDownList(NLBL0090CMsg cMessage) {

        return getSsmEZDClient().queryObjectList("getPODStatusPullDownList", cMessage);
    }

    /**
     * get PODStatusList by PODStatusTypeCd
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPODStatusListByPODStatusTypeCd(Map ssmParam) {

        return getSsmEZDClient().queryObjectList("getPODStatusListByPODStatusTypeCd", ssmParam);
    }

    /**
     * <pre>
     * search CarrierName
     * </pre>
     * @param ssmParam Map
     * @param cMessage NLBL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrierName(Map ssmParam, NLBL0090CMsg cMessage) {

        return getSsmEZDClient().queryEZDMsg("getCarrierName", ssmParam, cMessage);
    }

    /**
     * search SellToName
     * @param ssmParam Map
     * @param cMessage NLBL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSellToName(Map ssmParam, NLBL0090CMsg cMessage) {

        return getSsmEZDClient().queryEZDMsg("getSellToName", ssmParam, cMessage);
    }

    /**
     * <pre>
     * search ShipToName
     * </pre>
     * @param ssmParam Map
     * @param cMessage NLBL0090CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getShipToName(Map ssmParam, NLBL0090CMsg cMessage) {

        return getSsmEZDClient().queryEZDMsg("getShipToName", ssmParam, cMessage);
    }

    /**
     * <pre>
     * Search BOLTrackingList
     * </pre>
     * @param ssmParam Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countBOLTrackingList(Map ssmParam) {

        return getSsmEZDClient().queryObject("countBOLTrackingList", ssmParam);
    }

    /**
     * <pre>
     * Search BOLTrackingList
     * </pre>
     * @param ssmParam Map
     * @param sMessage NLBL0090SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBOLTrackingList(Map ssmParam, NLBL0090SMsg sMessage) {

        return getSsmEZDClient().queryEZDMsgArray("getBOLTrackingList", ssmParam, sMessage.A);
    }

    /**
     * <pre>
     * Search BOLTrackingDetailList
     * </pre>
     * @param ssmParam Map
     * @param sMessage NLBL0090SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBOLTrackingDetailList(Map ssmParam, NLBL0090SMsg sMessage) {

        return getSsmEZDClient().queryEZDMsgArray("getBOLTrackingDetailList", ssmParam, sMessage.B);
    }

    // Defect#6918
    /**
     * Search BOLTracking data and create CSV file.
     * @param cMsg
     * @param ssmParam
     * @return true/success, false/nothing data.
     */
    boolean getBOLTrackingListForCsv(Map ssmParam, NLBL0090CMsg cMsg) {

        return (Boolean) getSsmBatchClient().queryObject("getBOLTrackingListForCsv", ssmParam, new CsvCreator(cMsg));
    }

    /**
     * Create FMsg using ResultSet
     */
    private static class CsvCreator extends S21SsmBooleanResultSetHandlerSupport {

        /** */
        private NLBL0090CMsg bizMsg;

        public CsvCreator(NLBL0090CMsg bizMsg) {
            this.bizMsg = bizMsg;
        }

        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {

            rs.last();
            int cnt = rs.getRow();

            if (cnt == 0) {
                bizMsg.setMessageInfo(NZZM0000E);
                return false;
            }

            if (cnt > CSV_MAX_ROWS) {
                bizMsg.setMessageInfo(NZZM0007E);
                return false;
            }

            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NM), CSV_FILE_EXT);

            NLBL0090F00FMsg fMsg = new NLBL0090F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

            String[] csvHeader = getCsvHdrColum();
            csvOutFile.writeHeader(csvHeader);

            rs.first();
            do {
                setCsvData(fMsg, rs);
                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();

            return true;
        }

        private String[] getCsvHdrColum() {
            List<String> csvHeaderList = new ArrayList<String>();
            csvHeaderList.add(BOL_NUM);
            csvHeaderList.add(PRO_NUM);
            csvHeaderList.add(LOC_NM);
            csvHeaderList.add(WH_CD);
            csvHeaderList.add(SELL_TO_CUST_CD);
            csvHeaderList.add(SHIP_TO_CUST_CD);
            csvHeaderList.add(SO_NUM);
            csvHeaderList.add(POD_STS_DT);
            csvHeaderList.add(POD_STS_CD);
            csvHeaderList.add(POD_STS_NM);
            csvHeaderList.add(POD_STS_RSN_CD);
            csvHeaderList.add(POD_STS_RSN_NM);
            csvHeaderList.add(SO_SLP_NUM);
            csvHeaderList.add(ITEM_NUMBER);
            csvHeaderList.add(ITEM_NM);
            csvHeaderList.add(STK_STS_CD);
            csvHeaderList.add(ORD_QTY);
            csvHeaderList.add(TRX_HDR_NUM);
            csvHeaderList.add(SCE_ORD_TP_CD);
            csvHeaderList.add(SCE_ORD_TP_NM);
            String[] csvHeader = (String[]) csvHeaderList.toArray(new String[0]);
            return csvHeader;
        }

        private void setCsvData(NLBL0090F00FMsg fMsg, ResultSet rs) throws SQLException {
            setValue(fMsg.bolNum, rs.getString(DB_COLUMN_BOL_NUM));
            setValue(fMsg.locNm, rs.getString(DB_COLUMN_LOC_NM));
            setValue(fMsg.mdseCd, rs.getString(DB_COLUMN_MDSE_CD));
            setValue(fMsg.mdseDescShortTxt, rs.getString(DB_COLUMN_MDSE_DESC_SHORT_TXT));
            setValue(fMsg.ordQty, rs.getBigDecimal(DB_COLUMN_ORD_QTY));
            setValue(fMsg.podStsCd, rs.getString(DB_COLUMN_POD_STS_CD));
            setValue(fMsg.podStsNm, rs.getString(DB_COLUMN_POD_STS_NM));
            setValue(fMsg.podStsRsnCd, rs.getString(DB_COLUMN_POD_STS_RSN_CD));
            setValue(fMsg.podStsRsnNm, rs.getString(DB_COLUMN_POD_STS_RSN_NM));
            setValue(fMsg.proNum, rs.getString(DB_COLUMN_PRO_NUM));
            setValue(fMsg.sceOrdTpCd, rs.getString(DB_COLUMN_SCE_ORD_TP_CD));
            setValue(fMsg.sceOrdTpNm, rs.getString(DB_COLUMN_SCE_ORD_TP_NM));
            setValue(fMsg.sellToCustCd, rs.getString(DB_COLUMN_SELL_TO_CUST_CD));
            setValue(fMsg.shipToCustCd, rs.getString(DB_COLUMN_SHIP_TO_CUST_CD));
            setValue(fMsg.soNum, rs.getString(DB_COLUMN_SO_NUM));
            setValue(fMsg.soSlpNum, rs.getString(DB_COLUMN_SO_SLP_NUM));
            setValue(fMsg.stkStsCd, rs.getString(DB_COLUMN_FROM_STK_STS_CD));
            setValue(fMsg.trxHdrNum, rs.getString(DB_COLUMN_TRX_HDR_NUM));
            setValue(fMsg.whCd, rs.getString(DB_COLUMN_WH_CD));

            setValue(fMsg.xxDtTxt, rs.getString(DB_COLUMN_POD_STS_DT));
            if (ZYPCommonFunc.hasValue(fMsg.xxDtTxt)) {
                String formattedDt = ZYPDateUtil.formatEzd8ToDisp(fMsg.xxDtTxt.getValue());
                setValue(fMsg.xxDtTxt, formattedDt);
            }
        }
    }

}
