/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0530.common;

import static business.blap.NSAL0530.constant.NSAL0530Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCItem;
import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0530.NSAL0530CMsg;
import business.blap.NSAL0530.NSAL0530Query;
import business.blap.NSAL0530.NSAL0530SMsg;
import business.file.NSAL0530F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_SRCH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 * 2016/02/04   Hitachi         T.Tomita        Update          CSA QC#545
 * 2016/05/10   Hitachi         T.Tomita        Update          QC#7976
 *</pre>
 */
public class NSAL0530CommonLogic {

    /**
     * Sets the pagenation.
     * @param xxPageShowOfNum the xx page show of num
     * @param xxPageShowToNum the xx page show to num
     * @param xxPageShowFromNum the xx page show from num
     * @param pageRecs the page records
     * @param totalRecs the total records
     */
    public static void setPagenation(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {

        if (xxPageShowOfNum == null || xxPageShowToNum == null) {
            return;
        }
        if (pageRecs == 0 || totalRecs == 0) {
            return;
        }

        setValue(xxPageShowToNum, new BigDecimal(xxPageShowFromNum + pageRecs - 1));
        setValue(xxPageShowOfNum, new BigDecimal(totalRecs));
    }

    /**
     * Clear Message
     * @param cMsg NSAL0530CMsg
     * @param sMsg NSAL0530SMsg
     */
    public static void clearMsg(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {
        sMsg.A.clear();
        sMsg.xxComnColOrdTxt.clear();

        // START 2016/05/10 T.Tomita [QC#7976, MOD]
        cMsg.xxScrItem29Txt_HS.clear();
        cMsg.svcSlnNm_H.clear();
        cMsg.svcMachMstrPk_H.clear();
        cMsg.xxScrItem29Txt_HC.clear();
        cMsg.istlDt_H.clear();
        cMsg.serNum_H.clear();
        cMsg.custMachCtrlNum_H.clear();
        cMsg.custIssPoNum_H.clear();
        cMsg.mdseCd_H.clear();
        cMsg.cpoOrdNum_H.clear();
        cMsg.xxLocNm_H1.clear();
        cMsg.xxLocNm_H2.clear();
        cMsg.xxLocNm_H3.clear();
        cMsg.mdlNm_H.clear();
        cMsg.dsContrNum_H.clear();
        // END 2016/05/10 T.Tomita [QC#7976, MOD]

        cMsg.dsAcctSrchTpCd_H1.clear();
        cMsg.dsAcctSrchTpCd_H2.clear();
        cMsg.dsAcctSrchTpCd_H3.clear();
        cMsg.coaMdseTpCd_HS.clear();

        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    /**
     * Create Pull Down
     * @param cMsg NSAL0530CMsg
     */
    public static void createPullDown(NSAL0530CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(DS_ACCT_SRCH_TP.class, cMsg.dsAcctSrchTpCd_H, cMsg.dsAcctSrchTpDescTxt_H);
        // START 2016/02/04 T.Tomita [QC#545, MOD]
//        ZYPCodeDataUtil.createPulldownList(COA_MDSE_TP.class, cMsg.coaMdseTpCd_H, cMsg.coaMdseTpDescTxt_H);
        ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, cMsg.coaMdseTpCd_H, cMsg.coaMdseTpDescTxt_H);
        // END 2016/02/04 T.Tomita [QC#545, MOD]
    }

    /**
     * Is Error Search Condition
     * @param cMsg NSAL0530CMsg
     * @return boolean
     */
    public static boolean isErrorSearchCondition(NSAL0530CMsg cMsg) {
        // START 2016/05/10 T.Tomita [QC#7976, MOD]
        if (hasValueItems(
                  cMsg.xxScrItem29Txt_HS
                , cMsg.svcSlnNm_H
                , cMsg.xxScrItem29Txt_HC
                , cMsg.svcMachMstrPk_H
                , cMsg.istlDt_H
                , cMsg.serNum_H
                , cMsg.custMachCtrlNum_H
                , cMsg.custIssPoNum_H
                , cMsg.mdseCd_H
                , cMsg.cpoOrdNum_H
                , cMsg.xxLocNm_H1
                , cMsg.xxLocNm_H2
                , cMsg.xxLocNm_H3
                , cMsg.mdlNm_H
                , cMsg.coaMdseTpCd_HS
                , cMsg.dsContrNum_H)) {
            return false;
        }
        // END 2016/05/10 T.Tomita [QC#7976, MOD]

        cMsg.setMessageInfo(NSAM0005E);
        return true;
    }

    /**
     * Get Search Data
     * @param cMsg NSAL0530CMsg
     * @param sMsg NSAL0530SMsg
     */
    public static void getSearchData(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL0530Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NSAM0006I, new String[] {Integer.toString(sMsg.A.length()) });
                queryResCnt = sMsg.A.length();
            } else {
                cMsg.setMessageInfo(NSAM0200I, new String[] {Integer.toString(sMsg.A.length()) });
            }

            // Copy one page from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Set page#
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
            setValue(cMsg.xxPageShowToNum, new BigDecimal(cMsg.A.getValidCount()));
            setValue(cMsg.xxPageShowOfNum, new BigDecimal(queryResCnt));
        } else {
            // No result
            cMsg.setMessageInfo(NSAM0194I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Copy SMsg To cMsg
     * @param cMsg NSAL0530CMsg
     * @param sMsg NSAL0530SMsg
     */
    public static void copyGlblMsgToBizMsg(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {
        int pageFromNum = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.A.length() && pageFromNum < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(pageFromNum++), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    /**
     * Pre Set To Page One
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        setValue(xxPageShowFromNum, BigDecimal.ONE);
    }

    /**
     * Download
     * @param cMsg NSAL0530CMsg
     * @param sMsg NSAL0530SMsg
     */
    public static void download(NSAL0530CMsg cMsg, NSAL0530SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE_MAX);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NSAL0530Query.getInstance().getClass());

            // create csv file
            cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + CSV_FILE_NAME), ".csv");

            Map<String, Object> ssMParam = NSAL0530Query.getSsmParam(cMsg, sMsg, DOWNLOAD_LIMIT_COUNT + 1);
            ps = ssmLLClient.createPreparedStatement("getSearchData", ssMParam, execParam);
            rs = ps.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NSAM0194I, null);
                return;
            }
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private static void writeCsvFile(NSAL0530CMsg cMsg, ResultSet rs) throws SQLException {

        NSAL0530F00FMsg fMsg = new NSAL0530F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        // write contents
        do {
            if (rs.getRow() > DOWNLOAD_LIMIT_COUNT) {
                cMsg.setMessageInfo(NSAM0006I, new String[] {Integer.toString(DOWNLOAD_LIMIT_COUNT) });
                break;
            }
            // result set -> fMsg
            setValue(fMsg.svcSlnSq_A, rs.getBigDecimal("SVC_SLN_SQ_A"));
            setValue(fMsg.svcSlnNm_A, rs.getString("SVC_SLN_NM_A"));
            setValue(fMsg.xxLocNm_A1, rs.getString("XX_LOC_NM_A1"));
            setValue(fMsg.xxLocNm_A2, rs.getString("XX_LOC_NM_A2"));
            setValue(fMsg.xxLocNm_A3, rs.getString("XX_LOC_NM_A3"));
            setValue(fMsg.xxDtTm_AC, formatDate(rs.getString("SVC_SLN_CRAT_DT_A")));
            setValue(fMsg.svcSlnCratPsnCd_A, rs.getString("SVC_SLN_CRAT_PSN_CD_A"));
            setValue(fMsg.xxDtTm_AU, formatDate(rs.getString("SVC_SLN_UPD_DT_A")));
            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0530F00FMsg fMsg, NSAL0530CMsg cMsg) {

        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[]{
                  labelConv.convLabel2i18nLabel(SCREEN_ID, "Solution#")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Solution Name")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Acct Name - Owner")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Acct Name - Bill To")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Acct Name - Current Location")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Creation Date")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Created By")
                , labelConv.convLabel2i18nLabel(SCREEN_ID, "Last Update Date")
        };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }

    private static String formatDate(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date, true);
        }
        return date;
    }

    /**
     * hasValueItems
     * @param items EZDCItem
     * @return boolean
     */
    public static boolean hasValueItems(EZDCItem... items) {
        for (EZDCItem item : items) {
            if (hasValue(item)) {
                return true;
            }
        }
        return false;
    }
}
