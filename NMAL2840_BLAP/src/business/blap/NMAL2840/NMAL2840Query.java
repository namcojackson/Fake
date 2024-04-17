/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2840;

import static business.blap.NMAL2840.constant.NMAL2840Constant.FETCH_SIZE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.blap.NMAL2840.common.NMAL2840CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DUNS_PROC_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL2840Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Fujitsu         R.Nakamura      Create          N/A
 * 2016/06/16   Fujitsu         R.Nakamura      Update          QC#10224
 * 2016/06/17   Fujitsu         R.Nakamura      Update          QC#10224
 * 2016/06/17   Fujitsu         R.Nakamura      Update          QC#10340
 * 2016/06/20   Fujitsu         R.Nakamura      Update          QC#10340
 * 2016/06/22   Fujitsu         R.Nakamura      Update          QC#10340
 * 2016/06/27   Fujitsu         R.Nakamura      Update          QC#10905
 * 2016/07/04   Fujitsu         R.Nakamura      Update          QC#11316
 * 2016/10/06   Fujitsu         R.Nakamura      Update          QC#14861
 * 2016/11/08   Fujitsu         N.Sugiura       Update          QC#14832
 *</pre>
 */
public final class NMAL2840Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2840Query MY_INSTANCE = new NMAL2840Query();

    /**
     * Private constructor
     */
    private NMAL2840Query() {
        super();
    }

    /**
     * Get the NMAL2840Query instance.
     * @return NMAL2840Query instance
     */
    public static NMAL2840Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getDunsCriteriaList
     * @param cMsg NMAL2840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPullDownDunsExtractModeList(NMAL2840CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getPullDownDunsExtractModeList", params);
    }

    /**
     * getPullDownImportedDNBDateList
     * @param cMsg NMAL2840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPullDownImportedDNBDateList(NMAL2840CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // Mod Start 2016/10/06 QC#14861
//        params.put("dunsProcTpCd20", DUNS_PROC_TP_CD_20);
//        params.put("dunsProcStsCd10", DUNS_PROC_STS_CD_10);
        params.put("dunsProcTpCd20", DUNS_PROC_TP.RECEIVE_AND_IMOORT_DNB_FILE);
        params.put("dunsProcStsCd10", DUNS_PROC_STS.DONE);
//        params.put("rowNum", MAX_NUM_IDDL);
        params.put("rowNum", cMsg.xxDtTm_PC.length() + 1);
        // Mod End 2016/10/06 QC#14861

        return getSsmEZDClient().queryObjectList("getPullDownImportedDNBDateList", params);
    }

    /**
     * getLastDNBDate
     * @param cMsg NMAL2840CMsg
     * @param dunsProcTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLastDNBDate(NMAL2840CMsg cMsg, String dunsProcTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dunsProcTpCd", dunsProcTpCd);
        // Mod Start 2016/10/06 QC#14861
//        params.put("dunsProcStsCd90", DUNS_PROC_STS_CD_90);
        params.put("dunsProcStsCd90", DUNS_PROC_STS.ERROR);
        // Mod End 2016/10/06 QC#14861

        return getSsmEZDClient().queryObject("getLastDNBDate", params);
    }

    /**
     * getExtractFieldsExtractSend
     * @param cMsg NMAL2840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getExtractFieldsExtractSend(NMAL2840CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getExtractFieldsExtractSend", params);
    }

    /**
     * getExtractFieldsExtractSend
     * @param cMsg NMAL2840CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getExtractFieldsDNBFileUpdate(NMAL2840CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getExtractFieldsDNBFileUpdate", params);
    }

    /**
     * getAuditInformation
     * @param bizMsg NMAL2840CMsg
     * @param glblMsg NMAL2840SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAuditInformation(NMAL2840CMsg bizMsg, NMAL2840SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        // Mod Start 2016/10/06 QC#14861
//        params.put("rowNum", MAX_NUM_AID);
        params.put("rowNum", bizMsg.A.length() + 1);
//        params.put("dunsProcTpCd10", DUNS_PROC_TP_CD_10);
//        params.put("dunsProcTpCd20", DUNS_PROC_TP_CD_20);
//        params.put("dunsProcTpCd30", DUNS_PROC_TP_CD_30);
//        params.put("dunsProcTpCd40", DUNS_PROC_TP_CD_40);
//        params.put("dunsProcStsCd90", DUNS_PROC_STS_CD_90);
        params.put("dunsProcTpCd10", DUNS_PROC_TP.EXTRACT_FOR_DNB);
        params.put("dunsProcTpCd20", DUNS_PROC_TP.RECEIVE_AND_IMOORT_DNB_FILE);
        params.put("dunsProcTpCd30", DUNS_PROC_TP.DOWNLOAD_DNB_FILE_FOR_REVIEW);
        params.put("dunsProcTpCd40", DUNS_PROC_TP.UPLOAD_DNB_FILE);
        params.put("dunsProcStsCd90", DUNS_PROC_STS.ERROR);
        // Mod End 2016/10/06 QC#14861

        return getSsmEZDClient().queryObjectList("getAuditInformation", params);

    }

    // Del Start 2016/07/04 QC#11316
//    /**
//     * <pre>
//     * extractForReviewList
//     * </pre>
//     * @param map Map
//     * @param sMsg NMAL2840SMsg
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult extractForReviewList(Map<String, Object> map, NMAL2840SMsg sMsg) {
//
//        return getSsmEZDClient().queryEZDMsgArray("extractForReviewList", map, sMsg.B);
//    }
    // Del End 2016/07/04 QC#11316

    /**
     * <pre>
     * createCSV
     * </pre>
     * @param bizMsg NMAL2840CMsg
     * @param params Map
     */
    public void createCSV(NMAL2840CMsg bizMsg, Map<String, Object> params) {

        // Mod Start 2016/06/27 QC#10905
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm("DNBCleansingReviewList");
            bizMsg.xxFileData.setTempFilePath(null, csvFileNm, ".csv");

            stmtSelect = ssmLLClient.createPreparedStatement("extractForReviewList", params, execParam);
            rs = stmtSelect.executeQuery();

            NMAL2840CommonLogic.writeCsvFile(bizMsg, rs);

            // Mod Start 2016/06/16 QC#10224
            // S21SsmBatchClient.getClient(this.getClass()).queryObject("extractForReviewList", params, new CsvCreator(bizMsg.xxFileData));
            // S21SsmBatchClient.getClient(this.getClass()).queryObject("extractForReviewList", params, new CsvCreator(bizMsg.xxFileData, bizMsg));
            // Mod End 2016/06/16 QC#10224

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // Mod End 2016/06/27 QC#10905
    }

    // Del Start 2016/06/27 QC#10905
//    /** CsvCreator */
//    private static final class CsvCreator extends S21SsmBooleanResultSetHandlerSupport {
//        /** CSV Header */
//        private static final String[] CSV_HEADER = {"S21 ACCOUNT NUMBER", "S21 ACCOUNT NAME", "S21 LOCATION NUMBER", "S21 ADDRESS1", "S21 ADDRESS2", "S21 CITY", "S21 STATE", "S21 POSTAL CODE", "S21 EMPLOYEE TOTAL", "S21 ANNUAL US SALES",
//                "S21 INDUSTRY", "S21 SIC CODE", "S21 DUNS#", "S21 UDUNS", "S21 HQ DUNS#", "S21 PARENT DUNS#", "S21 DNB FILE DATE", "S21 DNB BUSINESS NAME", "S21 DNB TRADESTYLE NAME", "S21 DNB ADDRESS", "S21 DNB BEMFAB CODE",
//                "S21 DNB LAST UPDATE DATE", "S21 COMPANY SIC CODE", "S21 COMPANY SIC DESCRIPTION", "DNB MATCH_CODE", "DNB NAME_PROFILE_CODE", "DNB STREET_NO_PROFILE_CODE", "DNB STREET_NAME_PROFILE_CODE", "DNB CONFIDENCE_CODE",
//                "DNB MATCH_GRADE", "DNB NIXIE_A", "DNB BEMFAB", "DNB BUSINESS_NAME", "DNB TRADE_NAME", "DNB ADDRESS_LINE", "DNB CITY_NAME", "DNB STATE_ABBREVIATION", "DNB ZIP_CODE", "DNB DUNS_NUMBER", "DNB EMPLOYEES_TOTAL",
//                "DNB ANNUAL_US_SALES", "DNB LINE_OF_BUSINESS", "DNB_SIC_CODE", "DNB GLOBAL_ULTIMATE_DUNS_NO", "DNB HEADQUARTERS_DUNS_NO", "DNB PARENT_DUNS_NO", "DNB GLOBAL_ULTIMATE_BUSINESS_NAME", "DNB COMPANY_SIC_CODE",
//                "DNB COMPANY_SIC_DESCRIPTION" };
//
//        /** xxFileData */
//        private EZDCMimeSourceItem xxFileData;
//
//        // Add Start 2016/06/16 QC#10224
//        private NMAL2840CMsg bizMsg;
//        // Add End 2016/06/16 QC#10224
//
//        // Mod Start 2016/06/16 QC#10224
//        private CsvCreator(EZDCMimeSourceItem xxFileData, NMAL2840CMsg bizMsg) {
//            this.xxFileData = xxFileData;
//            this.bizMsg = bizMsg;
//        }
//        // Mod End 2016/06/16 QC#10224
//
//        @Override
//        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
//            if (!rs.next()) {
//                // Add Start 2016/06/16 QC#10224
//                bizMsg.setMessageInfo(NZZM0000E);
//                // Add End 2016/06/16 QC#10224
//                return false;
//            }
//
//            createCsvFile(rs);
//            return true;
//        }
//
//        private void createCsvFile(ResultSet rs) throws SQLException {
//
//            final String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm("DNBCleansingReviewList");
//            xxFileData.setTempFilePath(null, csvFileNm, ".csv");
//
//            final NMAL2840FMsg fMsg = new NMAL2840FMsg();
//            final ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(xxFileData.getTempFilePath(), fMsg);
//            fileWriter.writeHeader(CSV_HEADER);
//
//            do {
//                final int row = rs.getRow();
//                // Del Start 2016/06/22 QC#10340
////                if (row > MAX_DOWNLOAD_CNT) {
////                    break;
////                }
//                // Del End 2016/06/22 QC#10340
//
//                writeCsvLine(fMsg, rs);
//                fileWriter.write();
//
//            } while (rs.next());
//
//            fileWriter.close();
//        }
//
//        private void writeCsvLine(NMAL2840FMsg fMsg, ResultSet rs) throws SQLException {
//            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A, rs.getString("S21_ACCOUNT_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A, rs.getString("S21_ACCOUNT_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.locNum_A, rs.getString("S21_LOCATION_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.firstLineAddr_A, rs.getString("S21_ADDRESS1"));
//            ZYPEZDItemValueSetter.setValue(fMsg.scdLineAddr_A, rs.getString("S21_ADDRESS2"));
//            ZYPEZDItemValueSetter.setValue(fMsg.ctyAddr_A, rs.getString("S21_CITY"));
//            // Mod Start 2016/06/17 QC#10340
////            ZYPEZDItemValueSetter.setValue(fMsg.fill11Txt_A, rs.getString("S21_STATE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsStCd_A, rs.getString("S21_STATE"));
//            // Mod End 2016/06/17 QC#10340
//            ZYPEZDItemValueSetter.setValue(fMsg.postCd_A, rs.getString("S21_POSTAL_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsLocEmpNum_A, rs.getBigDecimal("S21_EMPLOYEE_TOTAL"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsLocRevAmt_A, rs.getBigDecimal("S21_ANNUAL_US_SALES"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsCustSicDescTxt_A, rs.getString("S21_INDUSTRY"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsCustSicCd_RQ, rs.getString("S21_SIC_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsNum_SC, rs.getString("S21_DUNS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsUltDunsNum_A, rs.getString("S21_UDUNS"));
//            ZYPEZDItemValueSetter.setValue(fMsg.hqDunsNum_SH, rs.getString("S21_HQ_DUNS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsPrntDunsNum, rs.getString("S21_PARENT_DUNS_NUM"));
//            // Mod Start 2016/06/17 QC#10340
////            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm, rs.getString("S21_DNB_FILE_DATE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt, NMAL2840CommonLogic.formatDate(rs.getString("S21_DNB_FILE_DATE")));
//            // Mod End 2016/06/17 QC#10340
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsBizNm_1, rs.getString("S21_DNB_BUSINESS_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsTradeStyleNm_1, rs.getString("S21_DNB_TRADESTYLE_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsLineAddr_1, rs.getString("S21_DNB_ADDRESS"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsActvCd_1, rs.getString("S21_DNB_BEMFAB_CODE"));
//            // Mod Start 2016/06/17 QC#10340
////            ZYPEZDItemValueSetter.setValue(fMsg.dsLastUpdDunsDt, NMAL2840CommonLogic.formatDate(rs.getString("S21_DNB_LAST_UPDATE_DATE")));
////            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt, NMAL2840CommonLogic.formatDate(rs.getString("S21_DNB_LAST_UPDATE_DATE")));
//            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm, rs.getString("S21_DNB_LAST_UPDATE_DATE"));
//            // Mod End 2016/06/17 QC#10340
//            ZYPEZDItemValueSetter.setValue(fMsg.dsCustSicCd, rs.getString("S21_COMPANY_SIC_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsCustSicDescTxt, rs.getString("S21_COMPANY_SIC_DESCRIPTION"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsMatchCd, rs.getString("DNB_MATCH_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.nmPrflCd, rs.getString("DNB_NM_PROFILE_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.strNoPrflCd, rs.getString("DNB_STREET_NO_PROFILE_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.strNmPrflCd, rs.getString("DNB_STREET_NM_PROFILE_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsCnfdCd, rs.getString("DNB_CONFIDENCE_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.matchGrdCd, rs.getString("DNB_MATCH_GRADE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.nixieACd, rs.getString("DNB_NIXIE_A"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsActvCd_2, rs.getString("DNB_BEMFAB"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsBizNm_2, rs.getString("DNB_BUSINESS_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsTradeStyleNm_2, rs.getString("DNB_TRADE_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsLineAddr_2, rs.getString("DNB_ADDRESS_LINE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsCtyNm, rs.getString("DNB_CITY_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsStCd, rs.getString("DNB_STATE_ABBREVIATION"));
//            ZYPEZDItemValueSetter.setValue(fMsg.postCd, rs.getString("DNB_ZIP_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsNum, rs.getString("DNB_DUNS_NUM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.empTotNum, rs.getBigDecimal("DNB_EMPLOYEES_TOTAL"));
//            ZYPEZDItemValueSetter.setValue(fMsg.annSlsAmt, rs.getBigDecimal("DNB_ANNUAL_US_SALES"));
//            ZYPEZDItemValueSetter.setValue(fMsg.lineBizNm, rs.getString("DNB_LINE_OF_BUSINESS"));
//            ZYPEZDItemValueSetter.setValue(fMsg.firstSicCd, rs.getString("DNB_SIC_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.glblUltDunsNum, rs.getString("DNB_GLOBAL_ULTIMATE_DUNS_NO"));
//            ZYPEZDItemValueSetter.setValue(fMsg.hqDunsNum, rs.getString("DNB_HEADQUARTERS_DUNS_NO"));
//            ZYPEZDItemValueSetter.setValue(fMsg.prntDunsNum, rs.getString("DNB_PARENT_DUNS_NO"));
//            ZYPEZDItemValueSetter.setValue(fMsg.glblUltBizNm, rs.getString("DNB_GLOBAL_ULTI_BUSINESS_NM"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsCmpySicCd, rs.getString("DNB_COMPANY_SIC_CODE"));
//            ZYPEZDItemValueSetter.setValue(fMsg.dunsCmpySicDescTxt, rs.getString("DNB_COMPANY_SIC_DESCRIPTION"));
//        }
//    }
    // Del End 2016/06/27 QC#10905
    // Add Start 2016/11/08 QC#14832
    /**
     * countDuplicateFilter
     * @param cMsg NMAL2840CMsg
     * @param dunsCritId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countDuplicateFilter(NMAL2840CMsg cMsg, String dunsCritId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dunsCritId", dunsCritId);
        params.put("dunsProcStsCd10", DUNS_PROC_STS.DONE);
        params.put("dunsProcStsCd90", DUNS_PROC_STS.ERROR);

        return getSsmEZDClient().queryObject("countDuplicateFilter", params);
    }
    // Add End 2016/11/08 QC#14832
}
