/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0240;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSBL0240.constant.NSBL0240Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDFBigDecimalItem;
import parts.common.EZDFStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSBL0240.common.NSBL0240CommonLogic;
import business.db.DS_MDL_GRPTMsg;
import business.db.MDSETMsg;
import business.db.SVC_MBL_INTFC_XREFTMsg;
import business.db.SVC_MBL_INTFC_XREFTMsgArray;
import business.file.NSBL0240F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TRVL_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Travel Charge Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/15   Hitachi         Y.Takeno        Create          N/A
 * 2016/06/23   Hitachi         M.Gotou         Update          QC#10726
 * 2016/11/14   Hitachi         Y.Takeno        Update          QC#15935
 * 2016/11/14   Hitachi         Y.Takeno        Update          QC#15935-1
 * 2016/12/02   Hitachi         N.Arai          Create          QC#14204
 * 2017/01/24   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/08/08   Hitachi         U.Kim           Update          QC#19276
 *</pre>
 */
public class NSBL0240BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0240_INIT".equals(screenAplID)) {
                doProcess_NSBL0240_INIT((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_CMN_Submit((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_CMN_Download((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            // START 2017/01/24 K.Ochiai [QC#16331,MOD]
            } else if ("NSBL0240Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_CMN_Clear((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            // END 2017/01/24 K.Ochiai [QC#16331,MOD]
            } else if ("NSBL0240Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_Search((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_TemplateDownload((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_Upload((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_InsertRow((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_DeleteRow((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_SelectAll((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_UnSelectAll".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_UnSelectAll((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_ApplyModelGroup".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_ApplyModelGroup((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_ApplyIntgItem".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_ApplyIntgItem((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_PageNext((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0240Scrn00_PagePrev((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);
            } else if ("NSBL0240_NMAL6050".equals(screenAplID)) {
                doProcess_NSBL0240_NMAL6050((NSBL0240CMsg) cMsg, (NSBL0240SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0240_INIT(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {

        initMessages(cMsg, sMsg);

        cMsg.setCommitSMsg(true);
        EZDMsg.copy(cMsg, null, sMsg, null);

        NSBL0240CommonLogic.createSvcLineBizPulldownList(cMsg);
        ZYPCodeDataUtil.createPulldownList(SVC_TRVL_CHRG_TP.class, cMsg.svcTrvlChrgTpCd_L, cMsg.svcTrvlChrgTpDescTxt_L);

        setupZoneDefinition(cMsg, sMsg);

// START 2016/11/14 [QC#15935, DEL]
//        searchData(cMsg, sMsg);
//
//        if (!hasValue(cMsg.getMessageCode())) {
//            if (sMsg.A.getValidCount() == 0) {
//                cMsg.setMessageInfo(NZZM0000E);
//            } else {
//                cMsg.setMessageInfo(NZZM0002I);
//            }
//        }
// END 2016/11/14 [QC#15935, DEL]
    }

    private void doProcess_NSBL0240Scrn00_CMN_Submit(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        clearSearchResult(cMsg, sMsg);
        EZDMsg.copy(cMsg, null, sMsg, null);

        cMsg.setCommitSMsg(true);

        searchData(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    private void doProcess_NSBL0240Scrn00_CMN_Download(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSBL0240Query query = NSBL0240Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            // create CSV file
            cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
            Map<String, Object> params = setQueryParam(cMsg, sMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("searchData", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSBL0240F00FMsg fMsg = new NSBL0240F00FMsg();

            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);

            // write header
            writeCsvFileHeader(csvOutFile, fMsg, cMsg);

            // write data records
            int recordCount = 0;
            String prvGrpKey = null;
            String curGrpKey = null;
            List<Map<String, Object>> resultSubList = new ArrayList<Map<String, Object>>();
            do {
                if (recordCount >= sMsg.A.length() - 1) {
                    cMsg.setMessageInfo(NZZM0001W);
                    break;
                }

                // fetch & gather record data (1 record)
                Map<String, Object> element = createRecordData(rs);
                curGrpKey = createRecordGroupKey(element);
                if ((prvGrpKey != null) && !curGrpKey.equals(prvGrpKey)) {
                    // write record
                    writeCsvRecord(cMsg, sMsg, resultSubList, fMsg, csvOutFile);
                    recordCount++;
                    resultSubList = new ArrayList<Map<String, Object>>();
                }

                resultSubList.add(element);
                prvGrpKey = curGrpKey;

            } while (rs.next());

            // write last record
            writeCsvRecord(cMsg, sMsg, resultSubList, fMsg, csvOutFile);

            csvOutFile.close();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    // START 2017/01/24 K.Ochiai [QC#16331,MOD]
    private void doProcess_NSBL0240Scrn00_CMN_Clear(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {

        cMsg.clear();
        doProcess_NSBL0240_INIT(cMsg, sMsg);

// START 2016/11/14 [QC#15935-1, DEL]
//        searchData(cMsg, sMsg);
//
//        if (!hasValue(cMsg.getMessageCode())) {
//            cMsg.setMessageInfo(NZZM0002I);
//        }
// END   2016/11/14 [QC#15935-1, DEL]
    }
    // END 2017/01/24 K.Ochiai [QC#16331,MOD]

    private void doProcess_NSBL0240Scrn00_Search(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {

        clearSearchResult(cMsg, sMsg);
        EZDMsg.copy(cMsg, null, sMsg, null);

        cMsg.setCommitSMsg(true);

        searchData(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            if (sMsg.A.getValidCount() == 0) {
                cMsg.setMessageInfo(NZZM0000E);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        }
    }

    private void doProcess_NSBL0240Scrn00_TemplateDownload(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        // create csv file
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSBL0240F00FMsg fMsg = new NSBL0240F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }

    private void doProcess_NSBL0240Scrn00_Upload(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        String filePath = cMsg.xxFileData_U.getTempFilePath();
        if (filePath.length() == 0) {
            cMsg.setMessageInfo(ZYEM0004E);
            return;
        }

        String csvFilePath = ZYPExcelUtil.excelToCsvFile(filePath);

        NSBL0240F00FMsg fMsg = new NSBL0240F00FMsg();

        clearSearchResult(cMsg, sMsg);

        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);
        try {
            int header = mappedFile.read();
            if (header == 1) {
                // empty file
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }

            int status = -1;
            int rowIndex = sMsg.A.getValidCount();

            while ((status = mappedFile.read()) != 1) {

                if (rowIndex >= sMsg.A.length()) {
                    cMsg.setMessageInfo(NSAM0214E);
                    return;
                }

                if (status == CSV_READ_STATUS_CODE_1000) {
                    // NSAM0208E
                    cMsg.setMessageInfo(NSAM0208E);
                    clearSearchResult(cMsg, sMsg);
                    return;
                } else if (status > CSV_READ_STATUS_CODE_1000 && status < CSV_READ_STATUS_CODE_2000) {
                    // NSAM0209E
                    int errorColumnIndex = status - CSV_READ_STATUS_CODE_1000 - 1;
                    String[] itemNameArray = createCsvFileHeaderNameArray(cMsg);
                    String itemName = "";
                    if (errorColumnIndex >= 0 && errorColumnIndex < itemNameArray.length) {
                        itemName = itemNameArray[errorColumnIndex];
                    }
                    cMsg.setMessageInfo(NSAM0209E, new String[] {itemName });
                    clearSearchResult(cMsg, sMsg);
                    return;
                } else if (status >= CSV_READ_STATUS_CODE_2000) {
                    // NSAM0210E
                    int errorColumnIndex = status - CSV_READ_STATUS_CODE_2000 - 1;
                    String[] itemNameArray = createCsvFileHeaderNameArray(cMsg);
                    String itemName = "";
                    if (errorColumnIndex >= 0 && errorColumnIndex < itemNameArray.length) {
                        itemName = itemNameArray[errorColumnIndex];
                    }
                    cMsg.setMessageInfo(NSAM0210E, new String[] {itemName });
                    clearSearchResult(cMsg, sMsg);
                    return;
                }

                List<Map<String, Object>> resultList = NSBL0240CommonLogic.getResultMapListFromFMsg(cMsg.glblCmpyCd.getValue(), cMsg, fMsg, cMsg.C.getValidCount());
                for (Map<String, Object> result : resultList) {
                    NSBL0240CommonLogic.setResultToSMsg(result, sMsg, rowIndex);
                }
                rowIndex++;

                fMsg.clear();
            }
            sMsg.A.setValidCount(rowIndex);

            if (rowIndex == 0) {
                // only header line
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }

            // data validation check
            NSBL0240CommonLogic.validateDetailLinesForUpload(cMsg, sMsg);

            if (!hasValue(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(NZZM0002I);

            } else {
                clearSearchResult(cMsg, sMsg);
            }

        } finally {
            // set Paging Data
            NSBL0240CommonLogic.pagenation(cMsg, sMsg, 0);

            mappedFile.close();
            cMsg.xxFileData_U.deleteTempFile();
            ZYPExcelUtil.deleteFile(csvFilePath);
        }
    }

    private void doProcess_NSBL0240Scrn00_InsertRow(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        NSBL0240CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int currentRowCount = sMsg.A.getValidCount();
        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo(NSAM0320E, new String[] {"Service Travel Charge", String.valueOf(sMsg.A.length()) });
            return;
        }

        NSBL0240CommonLogic.setEmptyRecord(getGlobalCompanyCode(), cMsg, sMsg, currentRowCount);

        int rowsPerPage = cMsg.A.length();
        int lastPage = NSBL0240CommonLogic.getLastPageNum(cMsg, sMsg);
        int pagenationFrom = NSBL0240CommonLogic.convertPageNumToFirstRowIndex(rowsPerPage, lastPage);
        NSBL0240CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0240Scrn00_DeleteRow(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        NSBL0240CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX, ZYPConstant.FLG_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            return;
        }

        int deleteRowIndex = sMsg.D.getValidCount();
        for (int rowIndex : selectedRows) {
            for (int colIndex = 0; colIndex < cMsg.C.getValidCount(); colIndex++) {
                NSBL0240_ASMsg asMsg = sMsg.A.no(rowIndex);
                EZDSBigDecimalItem svcTrvlChrgPk = (EZDSBigDecimalItem) NSBL0240CommonLogic.getSItem(FLD_NM_SVC_TRVL_CHRG_PK, asMsg, colIndex);
                EZDSStringItem ezUpTime = (EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_EZUPTIME, asMsg, colIndex);
                EZDSStringItem ezUpTimeZone = (EZDSStringItem) NSBL0240CommonLogic.getSItem(FLD_NM_EZUPTIMEZONE, asMsg, colIndex);

                if (hasValue(svcTrvlChrgPk)) {
                    setValue(sMsg.D.no(deleteRowIndex).svcTrvlChrgPk, svcTrvlChrgPk);
                    setValue(sMsg.D.no(deleteRowIndex).ezUpTime, ezUpTime);
                    setValue(sMsg.D.no(deleteRowIndex).ezUpTimeZone, ezUpTimeZone);
                    deleteRowIndex++;
                }
            }
        }

        sMsg.D.setValidCount(deleteRowIndex);

        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);

        NSBL0240CommonLogic.reNumberingRowNum(sMsg);

        int rowsPerPage = cMsg.A.length();
        int lastPage = NSBL0240CommonLogic.getLastPageNum(cMsg, sMsg);
        int pagenationFrom = NSBL0240CommonLogic.convertPageNumToFirstRowIndex(rowsPerPage, lastPage);
        NSBL0240CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0240Scrn00_PageNext(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        NSBL0240CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int rowIndex = NSBL0240CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() + 1);
        NSBL0240CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0240Scrn00_PagePrev(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        NSBL0240CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int rowIndex = NSBL0240CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() - 1);
        NSBL0240CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0240Scrn00_SelectAll(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        NSBL0240CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            setValue(sMsg.A.no(i).xxChkBox, ZYPConstant.CHKBOX_ON_Y);
        }
        int rowIndex = NSBL0240CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSBL0240CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0240Scrn00_UnSelectAll(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        NSBL0240CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox.clear();
        }
        int rowIndex = NSBL0240CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSBL0240CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0240Scrn00_ApplyModelGroup(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        //START 2017/08/08 U.Kim [QC#19276, ADD]
        NSBL0240CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        //END 2017/08/08 U.Kim [QC#19276, ADD]
        int selRowIndex = cMsg.xxRowNum_S.getValueInt();
        String mdlGrpNm = cMsg.A.no(selRowIndex).mdlGrpNm.getValue();

        if (!hasValue(mdlGrpNm)) {
            cMsg.A.no(selRowIndex).mdlGrpNm.setErrorInfo(1, ZZZM9025E, new String[] {"Model Group" });
            return;
        }

        DS_MDL_GRPTMsg tMsg = NSBL0240CommonLogic.findDsMdlGrp(cMsg.glblCmpyCd.getValue(), mdlGrpNm);
        if (tMsg == null) {
            cMsg.A.no(selRowIndex).mdlGrpNm.setErrorInfo(1, NSBM0151E, new String[] {"Model Group", "Model Group" });
            return;
        }

        int sMsgIndex = cMsg.A.no(selRowIndex).xxRowNum.getValueInt() - 1;
        setValue(sMsg.A.no(sMsgIndex).mdlGrpId, tMsg.mdlGrpId);
        setValue(sMsg.A.no(sMsgIndex).mdlGrpNm, tMsg.mdlGrpNm);
        setValue(sMsg.A.no(sMsgIndex).mdlGrpDescTxt, tMsg.mdlGrpDescTxt);

        int rowIndex = NSBL0240CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSBL0240CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0240Scrn00_ApplyIntgItem(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        //START 2017/08/08 U.Kim [QC#19276, ADD]
        NSBL0240CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        //END 2017/08/08 U.Kim [QC#19276, ADD]
        int selRowIndex = cMsg.xxRowNum_S.getValueInt();
        String mdseCd = cMsg.A.no(selRowIndex).intgMdseCd.getValue();

        if (!hasValue(mdseCd)) {
            cMsg.A.no(selRowIndex).intgMdseCd.setErrorInfo(1, ZZZM9025E, new String[] {"Default Intangible Item#" });
            return;
        }

        MDSETMsg tMsg = NSBL0240CommonLogic.findMdse(cMsg.glblCmpyCd.getValue(), mdseCd);
        if (tMsg == null) {
            cMsg.A.no(selRowIndex).intgMdseCd.setErrorInfo(1, NSBM0151E, new String[] {"Default Intangible Item#", "Mdse" });
            return;
        }

        int sMsgIndex = cMsg.A.no(selRowIndex).xxRowNum.getValueInt() - 1;
        setValue(sMsg.A.no(sMsgIndex).intgMdseCd, tMsg.mdseCd);
        setValue(sMsg.A.no(sMsgIndex).mdseDescShortTxt, tMsg.mdseDescShortTxt);

        int rowIndex = NSBL0240CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSBL0240CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0240_NMAL6050(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        doProcess_NSBL0240Scrn00_ApplyModelGroup(cMsg, sMsg);
    }

    private void initMessages(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        ZYPTableUtil.clear(sMsg.C);
        ZYPTableUtil.clear(cMsg.C);
        sMsg.clear();
        clearSearchResult(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
    }

    private void clearSearchResult(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(cMsg.A);
    }

    private void setupZoneDefinition(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        SVC_MBL_INTFC_XREFTMsg inTMsg = new SVC_MBL_INTFC_XREFTMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        inTMsg.setConditionValue("svcMblIntfcKeyTxt01", SVC_MBL_INTFC_KEY_TXT);
        SVC_MBL_INTFC_XREFTMsgArray tMsgAry = (SVC_MBL_INTFC_XREFTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (tMsgAry == null) {
            return;
        }

        for (int i = 0; i < tMsgAry.getValidCount() && i < sMsg.C.length(); i++) {
            setValue(sMsg.C.no(i).svcZnCd, tMsgAry.no(i).svcMblS21ValTxt);
            setValue(sMsg.C.no(i).xxScrItem50Txt, tMsgAry.no(i).svcMblIntfcValTxt);
        }
        sMsg.C.setValidCount(tMsgAry.getValidCount());

        EZDMsg.copy(sMsg, null, cMsg, null);
    }

    private void searchData(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSBL0240Query query = NSBL0240Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());
            Map<String, Object> params = setQueryParam(cMsg, sMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("searchData", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.xxPageShowFromNum.clear();
                cMsg.xxPageShowToNum.clear();
                cMsg.xxPageShowOfNum.clear();
                cMsg.xxPageShowCurNum.clear();
                cMsg.xxPageShowTotNum.clear();
                return;
            }

            int sIndex = 0;
            String prvGrpKey = null;
            String curGrpKey = null;
            List<Map<String, Object>> resultSubList = new ArrayList<Map<String, Object>>();
            do {
                if (sIndex >= sMsg.A.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                    sMsg.A.setValidCount(sIndex);
                    NSBL0240CommonLogic.pagenation(cMsg, sMsg, 0);
                    return;
                }

                // fetch & gather record data (1 record)
                Map<String, Object> element = createRecordData(rs);
                curGrpKey = createRecordGroupKey(element);
                if ((prvGrpKey != null) && !curGrpKey.equals(prvGrpKey)) {
                    for (Map<String, Object> result : resultSubList) {
                        NSBL0240CommonLogic.setResultToSMsg(result, sMsg, sIndex);
                    }
                    sIndex++;
                    resultSubList = new ArrayList<Map<String, Object>>();
                }

                resultSubList.add(element);
                prvGrpKey = curGrpKey;

            } while (rs.next());

            // add start 2016/06/24 CSA Defect#10726
            if (sIndex >= sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                sMsg.A.setValidCount(sIndex);
                NSBL0240CommonLogic.pagenation(cMsg, sMsg, 0);
                return;
            }
            // add end 2016/06/24 CSA Defect#10726
            for (Map<String, Object> result : resultSubList) {
                NSBL0240CommonLogic.setResultToSMsg(result, sMsg, sIndex);
            }
            sIndex++;
            sMsg.A.setValidCount(sIndex);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

        NSBL0240CommonLogic.pagenation(cMsg, sMsg, 0);
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSBL0240F00FMsg fMsg, NSBL0240CMsg cMsg) {
        csvOutFile.writeHeader(createCsvFileHeaderNameArray(cMsg));
    }

    private String[] createCsvFileHeaderNameArray(NSBL0240CMsg cMsg) {
        List<String> hdrElementList = new ArrayList<String>();
        hdrElementList.add("Model Group Name");
        hdrElementList.add("Description");
        hdrElementList.add("Line of Business");
        hdrElementList.add("Intangible Item#");
        hdrElementList.add("Intangible Item Description");

        for (int colIndex = 0; colIndex < cMsg.C.getValidCount(); colIndex++) {
            String zoneName = cMsg.C.no(colIndex).xxScrItem50Txt.getValue();
            hdrElementList.add(zoneName + " Rate");
            hdrElementList.add(zoneName + " UOM");
        }
        for (int colIndex = cMsg.C.getValidCount(); colIndex < cMsg.C.length(); colIndex++) {
            hdrElementList.add("no use");
            hdrElementList.add("no use");
        }

        return hdrElementList.toArray(new String[hdrElementList.size()]);
    }

    private Map<String, Object> createRecordData(ResultSet rs) throws SQLException {
        Map<String, Object> element = new HashMap<String, Object>();
        element.put("MDL_GRP_NM", rs.getString("MDL_GRP_NM"));
        element.put("MDL_GRP_DESC_TXT", rs.getString("MDL_GRP_DESC_TXT"));
        element.put("SVC_LINE_BIZ_CD", rs.getString("SVC_LINE_BIZ_CD"));
        element.put("LINE_BIZ_TP_DESC_TXT", rs.getString("LINE_BIZ_TP_DESC_TXT"));
        element.put("INTG_MDSE_CD", rs.getString("INTG_MDSE_CD"));
        element.put("MDSE_DESC_SHORT_TXT", rs.getString("MDSE_DESC_SHORT_TXT"));
        element.put("SVC_TRVL_UNIT_AMT", rs.getBigDecimal("SVC_TRVL_UNIT_AMT"));
        element.put("SVC_TRVL_CHRG_TP_CD", rs.getString("SVC_TRVL_CHRG_TP_CD"));
        element.put("SVC_TRVL_CHRG_PK", rs.getBigDecimal("SVC_TRVL_CHRG_PK"));
        element.put("MDL_GRP_ID", rs.getBigDecimal("MDL_GRP_ID"));
        element.put("SVC_ZN_CD", rs.getString("SVC_ZN_CD"));
        element.put("EZUPTIME", rs.getString("EZUPTIME"));
        element.put("EZUPTIMEZONE", rs.getString("EZUPTIMEZONE"));
// START 2016/12/02 N.Arai [QC#14204, MOD]
        element.put("XX_REC_HIST_CRAT_TS", rs.getString("XX_REC_HIST_CRAT_TS"));
        element.put("XX_REC_HIST_CRAT_BY_NM", rs.getString("XX_REC_HIST_CRAT_BY_NM"));
        element.put("XX_REC_HIST_UPD_TS", rs.getString("XX_REC_HIST_UPD_TS"));
        element.put("XX_REC_HIST_UPD_BY_NM", rs.getString("XX_REC_HIST_UPD_BY_NM"));
        element.put("XX_REC_HIST_TBL_NM", rs.getString("XX_REC_HIST_TBL_NM"));
// END 2016/12/02 N.Arai [QC#14204, MOD]

        return element;
    }

    private String createRecordGroupKey(Map<String, Object> element) {
        StringBuilder builder = new StringBuilder();
        builder.append(element.get("MDL_GRP_NM"));
        builder.append(DELIM);
        builder.append(element.get("SVC_LINE_BIZ_CD"));

        return builder.toString();
    }

    private void writeCsvRecord(NSBL0240CMsg bizMsg, NSBL0240SMsg globalMsg, List<Map<String, Object>> result, NSBL0240F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // write one record
        boolean isRecordEmpty = true;
        Iterator<Map<String, Object>> itr = result.iterator();
        while (itr.hasNext()) {
            Map<String, Object> element = itr.next();

            if (isRecordEmpty) {
                // Model Group Name
                setValue(fMsg.mdlGrpNm, (String) element.get("MDL_GRP_NM"));
                // Description
                setValue(fMsg.mdlGrpDescTxt, (String) element.get("MDL_GRP_DESC_TXT"));
                // Line of Business
                setValue(fMsg.svcLineBizCd, (String) element.get("SVC_LINE_BIZ_CD"));
                // Intangible Item#
                setValue(fMsg.intgMdseCd, (String) element.get("INTG_MDSE_CD"));
                // Intangible Item Description
                setValue(fMsg.mdseDescShortTxt, (String) element.get("MDSE_DESC_SHORT_TXT"));

                isRecordEmpty = false;
            }

            for (int colIndex = 0; colIndex < bizMsg.C.getValidCount(); colIndex++) {
                String svcZoneCd = (String) element.get("SVC_ZN_CD");
                if (bizMsg.C.no(colIndex).svcZnCd.getValue().equals(svcZoneCd)) {
                    EZDFBigDecimalItem svcTrvlUnitAmt = (EZDFBigDecimalItem) NSBL0240CommonLogic.getFItem(FLD_NM_SVC_TRVL_UNIT_AMT, fMsg, colIndex);
                    EZDFStringItem svcTrvlChrgTpCd = (EZDFStringItem) NSBL0240CommonLogic.getFItem(FLD_NM_SVC_TRVL_CHRG_TP_CD, fMsg, colIndex);
                    // Zone X Rate
                    setValue(svcTrvlUnitAmt, (BigDecimal) element.get("SVC_TRVL_UNIT_AMT"));
                    // Zone X UOM
                    setValue(svcTrvlChrgTpCd, (String) element.get("SVC_TRVL_CHRG_TP_CD"));
                    break;
                }
            }
        }

        csvOutFile.write();

        fMsg.clear();
    }

    private Map<String, Object> setQueryParam(NSBL0240CMsg cMsg, NSBL0240SMsg sMsg) {
        int limitNum = cMsg.C.getValidCount() * sMsg.A.length() + 1;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdlGrpNm", sMsg.mdlGrpNm_H);
        params.put("svcLineBizCd", sMsg.svcLineBizCd_H);
        params.put("svcMblIntfcKeyTxt", SVC_MBL_INTFC_KEY_TXT);
        params.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        params.put("limitNum", limitNum);

        return params;
    }

}
