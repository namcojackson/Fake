/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL2220.common;

import static business.blap.NWAL2220.constant.NWAL2220Constant.BILL_TO_CUST_ACCT_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.BILL_TO_CUST_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.BUSINESS_ID;
import static business.blap.NWAL2220.constant.NWAL2220Constant.COA_BR_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.COA_BR_DESC_TXT;
import static business.blap.NWAL2220.constant.NWAL2220Constant.COA_EXTN_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.COA_EXTN_DESC_TXT;
import static business.blap.NWAL2220.constant.NWAL2220Constant.CPO_ORD_NUM;
import static business.blap.NWAL2220.constant.NWAL2220Constant.CPO_SRC_TP_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.CPO_SRC_TP_DESC_TXT;
import static business.blap.NWAL2220.constant.NWAL2220Constant.CSV_FILE_NAME;
import static business.blap.NWAL2220.constant.NWAL2220Constant.DS_ACCT_NM_AB;
import static business.blap.NWAL2220.constant.NWAL2220Constant.DS_ACCT_NM_AH;
import static business.blap.NWAL2220.constant.NWAL2220Constant.DS_ACCT_NM_AO;
import static business.blap.NWAL2220.constant.NWAL2220Constant.DS_IMPT_ORD_PK;
import static business.blap.NWAL2220.constant.NWAL2220Constant.DS_ORD_CATG_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.DS_ORD_CATG_DESC_TXT;
import static business.blap.NWAL2220.constant.NWAL2220Constant.DS_ORD_TP_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.DS_ORD_TP_DESC_TXT;
import static business.blap.NWAL2220.constant.NWAL2220Constant.ERR_PRM_IMPT_DT_FROM;
import static business.blap.NWAL2220.constant.NWAL2220Constant.ERR_PRM_IMPT_DT_TO;
import static business.blap.NWAL2220.constant.NWAL2220Constant.IMPT_STS_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.IMPT_STS_DESC_TXT;
import static business.blap.NWAL2220.constant.NWAL2220Constant.LIMIT_DL_ROWNUM;
import static business.blap.NWAL2220.constant.NWAL2220Constant.MAX_FETCH_SIZE;
import static business.blap.NWAL2220.constant.NWAL2220Constant.NWAM0266E;
import static business.blap.NWAL2220.constant.NWAL2220Constant.NWAM0754E;
import static business.blap.NWAL2220.constant.NWAL2220Constant.NZZM0001W;
import static business.blap.NWAL2220.constant.NWAL2220Constant.NZZM0002I;
import static business.blap.NWAL2220.constant.NWAL2220Constant.ORD_SRC_IMPT_TS_TXT;
import static business.blap.NWAL2220.constant.NWAL2220Constant.ORD_SRC_REF_NUM;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_BILL_TO_CUST_ACCT_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_BILL_TO_CUST_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_COA_BR_DESC_TXT;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_COA_EXTN_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_CPO_ORD_NUM;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_DS_ACCT_NM_BILL;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_DS_ACCT_NM_SELL;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_DS_ACCT_NM_SHIP;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_IMPT_STS_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_ORD_SRC_IMPT_TS_FROM;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_ORD_SRC_IMPT_TS_TO;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_ORD_SRC_REF_NUM;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_SELL_TO_CUST_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_SHIP_TO_CUST_ACCT_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_SHIP_TO_CUST_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_SOLD_TO_CUST_LOC_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.RQ_TOC_NM;
import static business.blap.NWAL2220.constant.NWAL2220Constant.SELL_TO_CUST_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.SHIP_TO_CUST_ACCT_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.SHIP_TO_CUST_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.SOLD_TO_CUST_LOC_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.TOC_CD;
import static business.blap.NWAL2220.constant.NWAL2220Constant.TOC_NM;
import static business.blap.NWAL2220.constant.NWAL2220Constant.ZZMM0001E;
import static business.blap.NWAL2220.constant.NWAL2220Constant.ZZZM9001E;
import static business.blap.NWAL2220.constant.NWAL2220Constant.ZZZM9003I;
import static business.blap.NWAL2220.constant.NWAL2220Constant.SRCH_COND_FROM_TIME;
import static business.blap.NWAL2220.constant.NWAL2220Constant.SRCH_COND_THRU_TIME;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NWAL2220.NWAL2220CMsg;
import business.blap.NWAL2220.NWAL2220Query;
import business.blap.NWAL2220.NWAL2220SMsg;
import business.blap.NWAL2220.NWAL2220_ASMsg;
import business.db.RPT_RQST_CONDTMsg;
import business.db.RPT_RQST_HDRTMsg;
import business.file.NWAL2220F00FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_EXTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RPT_RQST_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/16   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/24   Hitachi         T.Tsuchida      Create          QC#5891
 * 2016/04/26   Hitachi         K.Kojima        Update          QC#6283
 * 2016/06/15   SRAA            K.Aratani       Update          QC#9971
 * 2016/11/11   Fujitsu         T.Yoshida       Update          QC#14410
 * 2017/09/01   Fujitsu         R.Nakamura      Update          QC#20892
 *</pre>
 */
public class NWAL2220CommonLogic {

    /**
     * Clear Message
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     */
    public static void clearMsg(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        sMsg.clear();
    }

    /**
     * Create Pull Down
     * @param cMsg NWAL2220CMsg
     */
    public static void createPullDown(NWAL2220CMsg cMsg) {
        createCPOSrcTpPullDown(cMsg);
        ZYPCodeDataUtil.createPulldownList(IMPT_STS.class, cMsg.imptStsCd_L, cMsg.imptStsDescTxt_L);
        ZYPCodeDataUtil.createPulldownList(COA_EXTN.class, cMsg.coaExtnCd_L, cMsg.coaExtnDescTxt_L);
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     */
    public static void setInitParams(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        setValue(cMsg.ordSrcImptDt_TO, cMsg.slsDt);
    }

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NWAL2220CMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isErrorSearchCondition(NWAL2220CMsg cMsg) {
        boolean rtnVal = false;
        if (hasValue(cMsg.ordSrcImptDt_FM) && hasValue(cMsg.ordSrcImptDt_TO) && ZYPDateUtil.compare(cMsg.ordSrcImptDt_FM.getValue(), cMsg.ordSrcImptDt_TO.getValue()) > 0) {
            cMsg.ordSrcImptDt_FM.setErrorInfo(1, NWAM0266E, new String[] {ERR_PRM_IMPT_DT_FROM });
            cMsg.ordSrcImptDt_TO.setErrorInfo(1, NWAM0266E, new String[] {ERR_PRM_IMPT_DT_TO });
            rtnVal = true;
        }
        return rtnVal;
    }

    /**
     * Get CPO Source Type List
     * @param cMsg NWAL2220CMsg
     */
    @SuppressWarnings("unchecked")
    public static void createCPOSrcTpPullDown(NWAL2220CMsg cMsg) {
        S21SsmEZDResult ssmResult = NWAL2220Query.getInstance().getCpoSrcTpList(cMsg, cMsg.cpoSrcTpCd_L.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 99
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.cpoSrcTpCd_L.length()) {
                cMsg.cpoSrcTpCd_SV.setErrorInfo(2, NZZM0001W);
            }
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size() && i < cMsg.cpoSrcTpCd_L.length(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                cMsg.cpoSrcTpCd_L.no(i).setValue((String) resultMap.get(CPO_SRC_TP_CD));
                cMsg.cpoSrcTpDescTxt_L.no(i).setValue((String) resultMap.get(CPO_SRC_TP_DESC_TXT));
            }
        } else {
            // No result
            cMsg.cpoSrcTpCd_SV.setErrorInfo(0, ZZZM9001E);
        }
    }

    /**
     * Get Search Data
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     * @return true : has result
     */
    public static boolean getSearchData(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg) {
        // QC#14410 Del Start
//        S21SsmEZDResult ssmResult = NWAL2220Query.getInstance().getSearchData(cMsg, cMsg.A.length() + 1);
//        if (ssmResult.isCodeNormal()) {
//            // Result > 2000
//            int queryResCnt = ssmResult.getQueryResultCount();
//            if (queryResCnt > cMsg.A.length()) {
//                cMsg.setMessageInfo(NZZM0001W);
//            } else {
//                cMsg.setMessageInfo(NZZM0002I);
//            }
//            for (int i = 0; i < cMsg.A.getValidCount() && i < cMsg.A.length(); i++) {
//                NWAL2220_ACMsg acMsg = cMsg.A.no(i);
//                setValue(acMsg.xxTsDsp19Txt_A, formatTimeStamp(acMsg.xxTsDsp19Txt_A.getValue()));
//            }
//        } else {
//            // No result
//            cMsg.setMessageInfo(ZZZM9001E);
//        }
        // QC#14410 Del End

        // QC#14410 Add Start
        S21SsmEZDResult ssmResult = NWAL2220Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 2000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
            for (int i = 0; i < sMsg.A.getValidCount() && i < sMsg.A.length(); i++) {
                NWAL2220_ASMsg asMsg = sMsg.A.no(i);
                setValue(asMsg.xxTsDsp19Txt_A, formatTimeStamp(asMsg.xxTsDsp19Txt_A.getValue()));
            }

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
            return true;
        }

        // No result
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
        cMsg.setMessageInfo(ZZZM9001E);
        return false;
        // QC#14410 Add End
    }

    /**
     * Format TimeStamp
     * @param val String
     * @return String
     */
    public static String formatTimeStamp(String val) {
        if (hasValue(val)) {
            return ZYPDateUtil.formatEzd14ToDisp(val);
        }
        return val;
    }

    /**
     * Get Download Data
     * @param cMsg NWAL2220CMsg
     */
    public static void getDownloadData(NWAL2220CMsg cMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        NWAL2220Query query = NWAL2220Query.getInstance();

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), ".csv");

            Map<String, Object> ssMParam = query.getSsmParam(cMsg, LIMIT_DL_ROWNUM);
            ps = ssmLLClient.createPreparedStatement("getSearchData", ssMParam, execParam);
            rs = ps.executeQuery();
            NWAL2220CommonLogic.writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * writeCsvFile
     * @param cMsg NWAL2220CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NWAL2220CMsg cMsg, ResultSet rs) throws SQLException {

        NWAL2220F00FMsg fMsg = new NWAL2220F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(ZZZM9001E, null);
            csvOutFile.close();
            return;
        }

        // write contents
        do {
            if (rs.getRow() > LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }
            // resultSet -> fMsg
            setValue(fMsg.cpoSrcTpDescTxt_A, rs.getString(CPO_SRC_TP_DESC_TXT));
            setValue(fMsg.ordSrcRefNum_A, rs.getString(ORD_SRC_REF_NUM));
            setValue(fMsg.dsImptOrdPk_A, rs.getBigDecimal(DS_IMPT_ORD_PK));
            setValue(fMsg.imptStsCd_A, rs.getString(IMPT_STS_CD));
            setValue(fMsg.imptStsDescTxt_A, rs.getString(IMPT_STS_DESC_TXT));
            setValue(fMsg.xxTsDsp19Txt_A, formatTimeStamp(rs.getString(ORD_SRC_IMPT_TS_TXT)));
            setValue(fMsg.tocCd_A, rs.getString(TOC_CD));
            setValue(fMsg.tocNm_A, rs.getString(TOC_NM));
            setValue(fMsg.coaExtnCd_A, rs.getString(COA_EXTN_CD));
            setValue(fMsg.coaExtnDescTxt_A, rs.getString(COA_EXTN_DESC_TXT));
            setValue(fMsg.coaBrCd_A, rs.getString(COA_BR_CD));
            setValue(fMsg.coaBrDescTxt_A, rs.getString(COA_BR_DESC_TXT));
            setValue(fMsg.dsOrdCatgCd_A, rs.getString(DS_ORD_CATG_CD));
            setValue(fMsg.dsOrdCatgDescTxt_A, rs.getString(DS_ORD_CATG_DESC_TXT));
            // Mod Start 2017/09/01 QC#20892
//            setValue(fMsg.dsOrdRsnCd_A, rs.getString(DS_ORD_RSN_CD));
//            setValue(fMsg.dsOrdRsnDescTxt_A, rs.getString(DS_ORD_RSN_DESC_TXT));
            setValue(fMsg.dsOrdTpCd_A, rs.getString(DS_ORD_TP_CD));
            setValue(fMsg.dsOrdTpDescTxt_A, rs.getString(DS_ORD_TP_DESC_TXT));
            // Mod End 2017/09/01 QC#20892
            setValue(fMsg.cpoOrdNum_A, rs.getString(CPO_ORD_NUM));
            setValue(fMsg.sellToCustCd_A, rs.getString(SELL_TO_CUST_CD));
            setValue(fMsg.dsAcctNm_AO, rs.getString(DS_ACCT_NM_AO));
            setValue(fMsg.soldToCustLocCd_A, rs.getString(SOLD_TO_CUST_LOC_CD));
            setValue(fMsg.billToCustAcctCd_A, rs.getString(BILL_TO_CUST_ACCT_CD));
            setValue(fMsg.dsAcctNm_AB, rs.getString(DS_ACCT_NM_AB));
            setValue(fMsg.billToCustCd_A, rs.getString(BILL_TO_CUST_CD));
            setValue(fMsg.shipToCustAcctCd_A, rs.getString(SHIP_TO_CUST_ACCT_CD));
            setValue(fMsg.dsAcctNm_AH, rs.getString(DS_ACCT_NM_AH));
            setValue(fMsg.shipToCustCd_A, rs.getString(SHIP_TO_CUST_CD));
            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NWAL2220F00FMsg fMsg, NWAL2220CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {labelConv.convLabel2i18nLabel(BUSINESS_ID, "Source Name"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Source Reference Num"),
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "DS Import Order PK"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Import Status Code"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Import Status"),
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "Import Date"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Sales Rep Code"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Sales Rep"),
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "Business Unit Code"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Business Unit"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Branch Code"),
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "Branch"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Order Category Code"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Order Category"),
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "Order Reson Code"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Order Reson"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "CPO Order Num"),
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "Sold To Customer Num"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Sold To Customer Name"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Sold To Location"),
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "Bill To Customer Num"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Bill To Customer Name"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Bill To Location"),
                labelConv.convLabel2i18nLabel(BUSINESS_ID, "Ship To Customer Num"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Ship To Customer Name"), labelConv.convLabel2i18nLabel(BUSINESS_ID, "Ship To Location") };
        csvOutFile.writeHeader(csvHeader);
    }

    // START 2016/04/26 K.Kojima [QC#6283,ADD]
    /**
     * isExistSaveSearchName
     * @param cMsg NWAL2220CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NWAL2220CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }

            if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_S) && cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }

        return false;
    }

    /**
     * callNszc0330forSaveSearch
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     */
    public static void callNszc0330forSaveSearch(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg, String usrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S) || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        }

        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.cpoSrcTpCd_SV.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.ordSrcRefNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.imptStsCd_SV.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.tocNm.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.ordSrcImptDt_FM.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.ordSrcImptDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.coaExtnCd_SV.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.coaBrDescTxt.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.cpoOrdNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.sellToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.dsAcctNm_OT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.soldToCustLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.billToCustAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.dsAcctNm_BT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.billToCustCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.shipToCustAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.dsAcctNm_HT.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.shipToCustCd.getValue());

        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, usrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_S, pMsg.srchOptPk);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo("ZZZM9003I", new String[] {"Save Search" });
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NWAL2220CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NWAL2220CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }

            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }

        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NWAL2220CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NWAL2220CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_L.no(i))) {
                return;
            }

            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_L.no(i));
            }
        }

        return;
    }

    /**
     * callNszc0330
     * @param cMsg NWAL2220CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    private static boolean callNszc0330(NWAL2220CMsg cMsg, NSZC033001PMsg pMsg) {
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        cMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * createSavedSearchOptionsPullDown
     * @param cMsg NWAL2220CMsg
     */
    @SuppressWarnings("unchecked")
    public static void createSavedSearchOptionsPullDown(NWAL2220CMsg cMsg, String usrId) {
        S21SsmEZDResult ssmResult = NWAL2220Query.getInstance().getSavedSearchOptionList(cMsg.glblCmpyCd.getValue(), usrId);
        if (!ssmResult.isCodeNormal()) {
            cMsg.srchOptPk_L.clear();
            cMsg.srchOptNm_L.clear();
            return;
        }
        cMsg.srchOptPk_L.clear();
        cMsg.srchOptNm_L.clear();
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < cMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            cMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get("SRCH_OPT_PK"));
            cMsg.srchOptNm_L.no(i).setValue((String) resultMap.get("SRCH_OPT_NM"));
        }
    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     */
    public static void callNszc0330forSearchOption(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg, String usrId) {

        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (!callNszc0330(cMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.cpoSrcTpCd_SV, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.ordSrcRefNum, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.imptStsCd_SV, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.tocNm, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(cMsg.ordSrcImptDt_FM, pMsg.srchOptTxt_05.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.ordSrcImptDt_TO, pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.coaExtnCd_SV, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(cMsg.coaBrDescTxt, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(cMsg.cpoOrdNum, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(cMsg.sellToCustCd, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_OT, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(cMsg.soldToCustLocCd, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustAcctCd, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_BT, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustCd, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustAcctCd, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_HT, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, pMsg.srchOptTxt_18);
    }

    /**
     * callNszc0330forDeleteSearch
     * @param cMsg NWAL2220CMsg
     * @param sMsg NWAL2220SMsg
     * @param usrId String
     */
    public static void callNszc0330forDeleteSearch(NWAL2220CMsg cMsg, NWAL2220SMsg sMsg, String usrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_ID);

        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, usrId);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete Search" });
        }
    }
    // END 2016/04/26 K.Kojima [QC#6283,ADD]

    //QC#9971
    public static void createCusaReportPrintRequest(NWAL2220CMsg cMsg, String usrId) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        RPT_RQST_HDRTMsg hdr = new RPT_RQST_HDRTMsg();
        BigDecimal rptRqstHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RPT_RQST_HDR_SQ);
        ZYPEZDItemValueSetter.setValue(hdr.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hdr.rptRqstHdrPk, rptRqstHdrPk);
        ZYPEZDItemValueSetter.setValue(hdr.rqstUsrId, usrId);
        ZYPEZDItemValueSetter.setValue(hdr.rqstCratTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss"));
        ZYPEZDItemValueSetter.setValue(hdr.rptId, "NWAF0090");
        ZYPEZDItemValueSetter.setValue(hdr.rptRqstProcStsCd, RPT_RQST_PROC_STS.UNPROCESS);
        S21FastTBLAccessor.insert(hdr);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(hdr.getReturnCode())) {
            // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
            cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_HDR", "RPT_RQST_HDR_PK", String.valueOf(hdr.rptRqstHdrPk.getValue()) });
            return;
        }

        // if Source Reference Number(ordSrcRefNum) has value, create condition records.
        if (hasValue(cMsg.ordSrcRefNum)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_ORD_SRC_REF_NUM, "01", cMsg.ordSrcRefNum.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Import Status Code(imptStsCd_SV) has value, create condition records.
        if (hasValue(cMsg.imptStsCd_SV)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_IMPT_STS_CD, "01", cMsg.imptStsCd_SV.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Sales Rep Name(tocNm)) has value, create condition records.
        if (hasValue(cMsg.tocNm)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_TOC_NM, "01", cMsg.tocNm.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if CPO Order Num(cpoOrdNum)) has value, create condition records.
        if (hasValue(cMsg.cpoOrdNum)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_CPO_ORD_NUM, "01", cMsg.cpoOrdNum.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Sales Business Unit(coaExtnCd_SV)) has value, create condition records.
        if (hasValue(cMsg.coaExtnCd_SV)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_COA_EXTN_CD, "01", cMsg.coaExtnCd_SV.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Sold To Customer Number(sellToCustCd)) has value, create condition records.
        if (hasValue(cMsg.sellToCustCd)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_SELL_TO_CUST_CD, "01", cMsg.sellToCustCd.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Bill To Customer Number(billToCustAcctCd)) has value, create condition records.
        if (hasValue(cMsg.billToCustAcctCd)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_BILL_TO_CUST_ACCT_CD, "01", cMsg.billToCustAcctCd.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Ship To Customer Number(shipToCustAcctCd)) has value, create condition records.
        if (hasValue(cMsg.shipToCustAcctCd)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_SHIP_TO_CUST_ACCT_CD, "01", cMsg.shipToCustAcctCd.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Import Date From(ordSrcImptDt_FM)) has value, create condition records.
        if (hasValue(cMsg.ordSrcImptDt_FM)) {
            
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_ORD_SRC_IMPT_TS_FROM, "03", cMsg.ordSrcImptDt_FM.getValue() + SRCH_COND_FROM_TIME);
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Sold To Customer Name(dsAcctNm_OT)) has value, create condition records.
        if (hasValue(cMsg.dsAcctNm_OT)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_DS_ACCT_NM_SELL, "01", cMsg.dsAcctNm_OT.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Bill To Customer Name(dsAcctNm_BT)) has value, create condition records.
        if (hasValue(cMsg.dsAcctNm_BT)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_DS_ACCT_NM_BILL, "01", cMsg.dsAcctNm_BT.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Ship To Customer Name(dsAcctNm_HT)) has value, create condition records.
        if (hasValue(cMsg.dsAcctNm_HT)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_DS_ACCT_NM_SHIP, "05", cMsg.dsAcctNm_HT.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Import Date To(ordSrcImptDt_TO)) has value, create condition records.
        if (hasValue(cMsg.ordSrcImptDt_TO)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_ORD_SRC_IMPT_TS_TO, "02", cMsg.ordSrcImptDt_TO.getValue() + SRCH_COND_THRU_TIME);
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Sales Branch Name(coaBrDescTxt)) has value, create condition records.
        if (hasValue(cMsg.coaBrDescTxt)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_COA_BR_DESC_TXT, "01", cMsg.coaBrDescTxt.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Sold To Location Number(soldToCustLocCd)) has value, create condition records.
        if (hasValue(cMsg.soldToCustLocCd)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_SOLD_TO_CUST_LOC_CD, "01", cMsg.soldToCustLocCd.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Bill To Location Number(billToCustCd)) has value, create condition records.
        if (hasValue(cMsg.billToCustCd)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_BILL_TO_CUST_CD, "01", cMsg.billToCustCd.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
                return;
            }
        }
        // if Ship To Location Number(shipToCustCd)) has value, create condition records.
        if (hasValue(cMsg.shipToCustCd)) {
            RPT_RQST_CONDTMsg cond = getRptRqstCondTMsg(glblCmpyCd, rptRqstHdrPk, RQ_SHIP_TO_CUST_CD, "01", cMsg.shipToCustCd.getValue());
            S21FastTBLAccessor.insert(cond);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cond.getReturnCode())) {
                // ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ]
                cMsg.setMessageInfo(ZZMM0001E, new String[] {"RPT_RQST_COND", "RPT_RQST_COND_PK", String.valueOf(cond.rptRqstCondPk.getValue()) });
            }
        }
    }

    private static RPT_RQST_CONDTMsg getRptRqstCondTMsg(String glblCmpyCd, BigDecimal rptRqstHdrPk, String rptRqstColTpCd, String rptRqstOpTpCd, String rptRqstColValTxt) {
        RPT_RQST_CONDTMsg cond = new RPT_RQST_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(cond.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cond.rptRqstCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.RPT_RQST_COND_SQ));
        ZYPEZDItemValueSetter.setValue(cond.rptRqstHdrPk, rptRqstHdrPk);
        ZYPEZDItemValueSetter.setValue(cond.rptRqstColTpCd, rptRqstColTpCd);
        ZYPEZDItemValueSetter.setValue(cond.rptRqstOpTpCd, rptRqstOpTpCd);
        ZYPEZDItemValueSetter.setValue(cond.rptRqstColValTxt, rptRqstColValTxt);
        return cond;
    }

    public static boolean isErrorSearchConditionForCusaRetailReport(NWAL2220CMsg cMsg) {
        boolean rtnVal = false;
        if (!hasValue(cMsg.cpoSrcTpCd_SV) || !CPO_SRC_TP.CUSA_NAD_OR_GMD.equals(cMsg.cpoSrcTpCd_SV.getValue())) {
            // NWAM0861E=0,Source Name should be CUSA NAD/GMD.
            cMsg.cpoSrcTpCd_SV.setErrorInfo(1, "NWAM0861E");
            rtnVal = true;
        }
        return rtnVal;
    }

    // QC#14410 Add Start
    public static boolean hasInput(NWAL2220CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.cpoSrcTpCd_SV)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaExtnCd_SV)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaBrDescTxt)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.ordSrcRefNum)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.sellToCustCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_OT)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.soldToCustLocCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.imptStsCd_SV)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_BT)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.billToCustCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.tocNm)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustAcctCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_HT)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.cpoOrdNum)) {
            return true;
        }
        if (ZYPCommonFunc.hasValue(cMsg.ordSrcImptDt_FM)) {
            return true;
        }

        cMsg.setMessageInfo(NWAM0754E);
        return false;
    }
    // QC#14410 Add End
}
