/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2400;

import static business.blap.NWAL2400.constant.NWAL2400Constant.CHKBOX_APPEND;
import static business.blap.NWAL2400.constant.NWAL2400Constant.CSV_FILE_EXTENSION;
import static business.blap.NWAL2400.constant.NWAL2400Constant.CSV_FILE_NAME;
import static business.blap.NWAL2400.constant.NWAL2400Constant.CSV_FORMAT_ERR;
import static business.blap.NWAL2400.constant.NWAL2400Constant.CSV_HDR_DOWNLOAD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_COLUMN_DS_RTL_DLR_INFO_PK;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_COLUMN_EZUPTIME;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_COLUMN_EZUPTIMEZONE;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_CMSG;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.DB_PARAM_ROW_NUM;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_ADD_NEW_LINE;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_CMN_CLEAR;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_CMN_DOWNLOAD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_CMN_SUBMIT;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_DELETE_LINE;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_INIT;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_ORDER_REASON_POPUP;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_PAGE_NEXT;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_PAGE_PREV;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_SEARCH;
import static business.blap.NWAL2400.constant.NWAL2400Constant.EVENT_NM_NWAL2400_UPLOAD;
import static business.blap.NWAL2400.constant.NWAL2400Constant.LIMIT_DL_ROWNUM;
import static business.blap.NWAL2400.constant.NWAL2400Constant.MAX_FETCH_SIZE;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NMAM0038I;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NMAM0052E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NMAM8114E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NWAM0181E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NWAM0504E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NZZM0000E;
import static business.blap.NWAL2400.constant.NWAL2400Constant.NZZM0001W;
import static business.blap.NWAL2400.constant.NWAL2400Constant.ZYEM0004E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2400.common.NWAL2400CommonLogic;
import business.file.NWAL2400F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 * 2016/08/02   CITS            S.Tanikawa      Update          QC#10537
 *</pre>
 */
public class NWAL2400BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NWAL2400_INIT.equals(screenAplID)) {

                doProcess_Init((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if (EVENT_NM_NWAL2400_CMN_CLEAR.equals(screenAplID)) {

                doProcess_Clear((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
                doProcess_Init((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if (EVENT_NM_NWAL2400_ADD_NEW_LINE.equals(screenAplID)) {

                doProcess_AddNewLine((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if (EVENT_NM_NWAL2400_DELETE_LINE.equals(screenAplID)) {

                doProcess_DeleteLine((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if (EVENT_NM_NWAL2400_SEARCH.equals(screenAplID)) {

                doProcess_Search((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if (EVENT_NM_NWAL2400_CMN_DOWNLOAD.equals(screenAplID)) {

                doProcess_Download((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if (EVENT_NM_NWAL2400_UPLOAD.equals(screenAplID)) {

                doProcess_Upload((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if (EVENT_NM_NWAL2400_PAGE_NEXT.equals(screenAplID)) {

                doProcess_PageNext((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if (EVENT_NM_NWAL2400_PAGE_PREV.equals(screenAplID)) {

                doProcess_PagePrev((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if (EVENT_NM_NWAL2400_ORDER_REASON_POPUP.equals(screenAplID)) {

                doProcess_OrderReason((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            } else if ((EVENT_NM_NWAL2400_CMN_SUBMIT.equals(screenAplID))) {

                doProcess_Search((NWAL2400CMsg) cMsg, (NWAL2400SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_Init(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        String glblCmpyCd = getGlobalCompanyCode();
        cMsg.glblCmpyCd.setValue(glblCmpyCd);

        NWAL2400CommonLogic.createPullDownRetailDivision(cMsg, sMsg, glblCmpyCd);
    }

    /**
     * Clear
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_Clear(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.D);

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);

        // Default value
        ZYPEZDItemValueSetter.setValue(cMsg.xxSelRadioBtnObj, CHKBOX_APPEND);
    }

    /**
     * Search
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_Search(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_ROW_NUM, sMsg.A.length() + 1);

        S21SsmEZDResult result = NWAL2400Query.getInstance().searchRetailDealer(ssmParam, sMsg);
        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {

            if (!(EVENT_NM_NWAL2400_CMN_SUBMIT.equals(cMsg.getScreenAplID()))) {
                cMsg.setMessageInfo(NMAM0038I);
            }
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Add New Line
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_AddNewLine(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        // checks if the row counts exceeds the maximum counts.
        int count = sMsg.A.getValidCount();
        if (count == sMsg.A.length()) {
            cMsg.setMessageInfo(NMAM8114E);
            return;
        }

        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pageShowFromNum + i), null);
        }

        // Insert row.
        sMsg.A.setValidCount(count + 1);

        String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(count).effFromDt_A, slsDt);

        // Pagination
        paginationForInsertRow(cMsg, sMsg);
    }

    /**
     * <pre>
     * pagination for Insert New Row
     * </pre>
     * @param sMsg NWAL2400SMsg
     * @param cMsg NWAL2400CMsg
     */
    private void paginationForInsertRow(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);

        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        // Calculate last page rows
        int maxDisplayRows = cMsg.A.length();
        int startIndex = sMsg.A.getValidCount() - (sMsg.A.getValidCount() % maxDisplayRows);

        int i = startIndex;
        if (startIndex == sMsg.A.getValidCount()) {
            startIndex = startIndex - cMsg.A.length();
        }

        for (; i < startIndex + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg sLineMsg = sMsg.A.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsg.A.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - startIndex);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Delete Line
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_DeleteLine(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pageShowFromNum + i), null);
        }

        int lineCnt = sMsg.A.getValidCount();
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);

        if (selectedRows.size() == 0) {
            if (cMsg.A.getValidCount() == 0) {
                cMsg.setMessageInfo(NWAM0504E);
                return;
            }
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NWAM0504E);
            }
        }

        // Collect deleted rows
        int delIdx = sMsg.D.getValidCount();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())
                    && ZYPCommonFunc.hasValue(sMsg.A.no(i).dsRtlDlrInfoPk_A)) {

                EZDMsg.copy(sMsg.A.no(i), "A", sMsg.D.no(delIdx), "D");
                sMsg.D.setValidCount(delIdx + 1);
                delIdx++;
            }
        }

        if (!selectedRows.isEmpty()) {
            int result = ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
            sMsg.A.setValidCount(lineCnt - result);
        }

        // Pagination
        paginationForDeleteRow(cMsg, sMsg);
    }

    /**
     * <pre>
     * pagination for Delete New
     * </pre>
     * @param sMsg NWAL2400SMsg
     * @param cMsg NWAL2400CMsg
     */
    private void paginationForDeleteRow(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() - 1;

        if (startIndex > sMsg.A.getValidCount() - 1) {
            startIndex = sMsg.A.getValidCount() - (sMsg.A.getValidCount() % cMsg.A.length());
            if (startIndex == sMsg.A.getValidCount()) {
                startIndex = startIndex - cMsg.A.length();
            }
        }

        if (startIndex < 0) {
            startIndex = 0;
        }

        int i = startIndex;
        for (; i < startIndex + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg sLineMsg = sMsg.A.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsg.A.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - startIndex);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * Download
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_Download(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NWAL2400Query.getInstance().getClass());
            //create csv file, parameters
            cMsg.xxFileData_DL.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_FILE_EXTENSION);

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            ssmParam.put(DB_PARAM_CMSG, cMsg);
            ssmParam.put(DB_PARAM_ROW_NUM, LIMIT_DL_ROWNUM);

            ps = ssmLLClient.createPreparedStatement("searchRetailDealer", ssmParam, execParam);

            rs = ps.executeQuery();
            writeCsvFile(cMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    /**
     * Download Event
     * @param cMsg      NWAL2400CMsg
     * @param ResultSet ResultSet
     */
    private void writeCsvFile(NWAL2400CMsg cMsg, ResultSet rs) throws SQLException {

        NWAL2400F00FMsg fMsg = new NWAL2400F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_DL.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));

        //write header
        csvOutFile.writeHeader(CSV_HDR_DOWNLOAD, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                break;
            }

            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.coaBrCd, rs.getString("COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlDlrCd, rs.getString("RTL_DLR_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlDivCd, rs.getString("RTL_DIV_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdCatgDescTxt, rs.getString("DS_ORD_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsOrdTpDescTxt, rs.getString("DS_ORD_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.rtlWhNm, rs.getString("RTL_WH_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.contrGrpCd, rs.getString("CONTR_GRP_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * Upload
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_Upload(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String filelPath = cMsg.xxFileData_UP.getTempFilePath();
        String csvFilePath = ZYPExcelUtil.excelToCsvFile(filelPath);

        NWAL2400F00FMsg fMsg = new NWAL2400F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;

        EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);
        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }

            if (!CHKBOX_APPEND.equals(cMsg.xxSelRadioBtnObj.getValue())) {
                ZYPTableUtil.clear(cMsg.A);
                ZYPTableUtil.clear(sMsg.A);
            } else {
                NWAL2400CommonLogic.updateGlblMsg(cMsg, sMsg);
            }

            int status = -1;
            while ((status = mappedFile.read()) != 1) {

                int newIndx = sMsg.A.getValidCount();

                if (newIndx >= sMsg.A.length()) {
                    cMsg.setMessageInfo(NMAM8114E);
                    return;
                }

                // format error
                if (status == CSV_FORMAT_ERR) {
                    cMsg.setMessageInfo(NMAM0052E, new String[] {"Upload File"});
                    return;
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).coaBrCd_A, fMsg.coaBrCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).coaBrDescTxt_A, NWAL2400CommonLogic.getCoaBr(glblCmpyCd,sMsg.A.no(newIndx).coaBrCd_A.getValue()));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).rtlDlrCd_A, fMsg.rtlDlrCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).rtlDivCd_A, fMsg.rtlDivCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).dsOrdCatgDescTxt_A, fMsg.dsOrdCatgDescTxt);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).dsOrdTpDescTxt_A, fMsg.dsOrdTpDescTxt);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).rtlWhNm_A, fMsg.rtlWhNm);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).billToCustCd_A, fMsg.billToCustCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).contrGrpCd_A, fMsg.contrGrpCd);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).effFromDt_A, fMsg.effFromDt);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).effThruDt_A, fMsg.effThruDt);

                // Get DS_RTL_DLR_INFO_PK
                S21SsmEZDResult result = NWAL2400CommonLogic.getRtlDlrInfoPk(glblCmpyCd,
                        fMsg.rtlDlrCd.getValue(),
                        fMsg.rtlDivCd.getValue());
                if (result.isCodeNormal()) {
                    Map<String, Object>map = (Map<String, Object>) result.getResultObject();
                    BigDecimal dsRtlDlrInfoPk = (BigDecimal) map.get(DB_COLUMN_DS_RTL_DLR_INFO_PK);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).dsRtlDlrInfoPk_A, dsRtlDlrInfoPk);

                    String ezUpTime = (String) map.get(DB_COLUMN_EZUPTIME);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).ezUpTime_A, ezUpTime);

                    String ezUpTimeZone = (String) map.get(DB_COLUMN_EZUPTIMEZONE);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(newIndx).ezUpTimeZone_A, ezUpTimeZone);
                }

                sMsg.A.setValidCount(newIndx + 1);
            }

        } finally {
            mappedFile.close();
            cMsg.xxFileData_UP.deleteTempFile();
            ZYPExcelUtil.deleteFile(csvFilePath);

            ZYPTableUtil.clear(cMsg.A);

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        }
    }

    /**
     * Page Next
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_PageNext(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        NWAL2400CommonLogic.updateGlblMsg(cMsg, sMsg);

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt() + 1);

        // copy data from SMsg onto CMsg
        NWAL2400CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * Page Prev
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_PagePrev(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        NWAL2400CommonLogic.updateGlblMsg(cMsg, sMsg);

        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length());

        // copy data from SMsg onto CMsg
        NWAL2400CommonLogic.loadOnePageToCMsg(cMsg, cMsg.A, sMsg, sMsg.A);
    }

    /**
     * Order Reason Poupup
     * @param cMsg NWAL2400CMsg
     * @param sMsg NWAL2400SMsg
     */
    private void doProcess_OrderReason(NWAL2400CMsg cMsg, NWAL2400SMsg sMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        int selectNum = cMsg.xxRowNum.getValueInt();

        String dsOrdCatgCd = NWAL2400CommonLogic.getDsOrdCatgCd(glblCmpyCd, cMsg.A.no(selectNum).dsOrdCatgDescTxt_A.getValue());

        if (dsOrdCatgCd == null) {
            cMsg.A.no(selectNum).dsOrdCatgDescTxt_A.setErrorInfo(1, NWAM0181E, new String[] {"Order Category" });
        }

        ZYPEZDItemValueSetter.setValue(cMsg.A.no(selectNum).dsOrdCatgCd_A, dsOrdCatgCd);
    }
}
