/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0210;

import static business.blap.NSBL0210.constant.NSBL0210Constant.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCSVInFile;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0210.common.NSBL0210CommonLogic;
import business.db.DS_MDL_GRPTMsg;
import business.db.MDSETMsg;
import business.db.SVC_PRC_SHIFTTMsg;
import business.file.NSBL0210F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * <pre>
 * Labor Charge Table Maintenance
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   Hitachi         Y.Takeno        Create          N/A
 * 2016/05/23   Hitachi         Y.Takeno        Update          QC#8567
 * 2016/06/06   Hitachi         Y.Takeno        Update          QC#5489
 * 2016/08/01   Hitachi         Y.Osawa         Update          QC#12242
 * 2016/11/14   Hitachi         Y.Takeno        Update          QC#15935
 * 2017/01/24   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/06/15   Hitachi         N.Arai          Update          QC#19188
 *</pre>
 */
public class NSBL0210BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0210_INIT".equals(screenAplID)) {
                doProcess_NSBL0210_INIT((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_CMN_Submit((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_CMN_Download((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            // START 2017/01/24 K.Ochiai [QC#16331,MOD]
            } else if ("NSBL0210Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_CMN_Clear((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            // END 2017/01/24 K.Ochiai [QC#16331,MOD]
            } else if ("NSBL0210Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_Search((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_TemplateDownload((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_Upload((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_InsertRow((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_DeleteRow".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_DeleteRow((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_SelectAll((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_UnSelectAll".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_UnSelectAll((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_ApplyModelGroup".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_ApplyModelGroup((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_ApplyShift".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_ApplyShift((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_ApplyIntgItem".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_ApplyIntgItem((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_PageNext((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_PagePrev((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210_NMAL6050".equals(screenAplID)) {
                doProcess_NSBL0210_NMAL6050((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else if ("NSBL0210Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSBL0210Scrn00_TBLColumnSort((NSBL0210CMsg) cMsg, (NSBL0210SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0210_INIT(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {

        initMessages(cMsg, sMsg);

        cMsg.setCommitSMsg(true);
        EZDMsg.copy(cMsg, null, sMsg, null);

        NSBL0210CommonLogic.createSvcLineBizPulldownList(cMsg);

// START 2016/11/14 [QC#15935, DEL]
//        searchData(cMsg, sMsg);
//
//        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
//            if (sMsg.A.getValidCount() == 0) {
//                cMsg.setMessageInfo(NZZM0000E);
//            } else {
//                cMsg.setMessageInfo(NZZM0002I);
//            }
//        }
// END   2016/11/14 [QC#15935, DEL]
    }

    private void doProcess_NSBL0210Scrn00_CMN_Submit(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        clearSearchResult(cMsg, sMsg);
        EZDMsg.copy(cMsg, null, sMsg, null);

        cMsg.setCommitSMsg(true);

        searchData(cMsg, sMsg);

        cMsg.setMessageInfo(NZZM0002I);
    }

    // START 2017/01/24 K.Ochiai [QC#16331,MOD]
    private void doProcess_NSBL0210Scrn00_CMN_Clear(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        doProcess_NSBL0210_INIT(cMsg, sMsg);
    }
    // END 2017/01/24 K.Ochiai [QC#16331,MOD]

    private void doProcess_NSBL0210Scrn00_CMN_Download(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSBL0210Query query = NSBL0210Query.getInstance();
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
            NSBL0210F00FMsg fMsg = new NSBL0210F00FMsg();

            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);

            // write header
            writeCsvFileHeader(csvOutFile, fMsg, cMsg);

            // write data records
            int recordCount = 0;
            do {
                if (recordCount >= LIMIT_DOWNLOAD) {
                    cMsg.setMessageInfo(NZZM0001W);
                    break;
                }

                writeCsvRecord(cMsg, sMsg, rs, fMsg, csvOutFile);
                recordCount++;

            } while (rs.next());

            csvOutFile.close();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    private void doProcess_NSBL0210Scrn00_Search(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        clearSearchResult(cMsg, sMsg);
        EZDMsg.copy(cMsg, null, sMsg, null);

        cMsg.setCommitSMsg(true);

        searchData(cMsg, sMsg);

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            if (sMsg.A.getValidCount() == 0) {
                cMsg.setMessageInfo(NZZM0000E);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        }
    }

    private void doProcess_NSBL0210Scrn00_TemplateDownload(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        // create csv file
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSBL0210F00FMsg fMsg = new NSBL0210F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }

    private void doProcess_NSBL0210Scrn00_Upload(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        String filePath = cMsg.xxFileData_U.getTempFilePath();
        if (filePath.length() == 0) {
            cMsg.setMessageInfo(ZYEM0004E);
            return;
        }

        String csvFilePath = ZYPExcelUtil.excelToCsvFile(filePath);

        NSBL0210F00FMsg fMsg = new NSBL0210F00FMsg();

        clearSearchResult(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvFilePath, fMsg, option);
        int pageIndex = 0;
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
                    cMsg.setMessageInfo(NSBM0162E);
                    return;
                }

                if (status == CSV_READ_STATUS_CODE_1000) {
                    // NSAM0208E
                    cMsg.setMessageInfo(NSBM0159E);
                    return;
                } else if (status > CSV_READ_STATUS_CODE_1000 && status < CSV_READ_STATUS_CODE_2000) {
                    // NSAM0209E
                    int errorColumnIndex = status - CSV_READ_STATUS_CODE_1000 - 1;
                    String[] itemNameArray = createCsvFileHeaderNameArray(cMsg);
                    String itemName = "";
                    if (errorColumnIndex >= 0 && errorColumnIndex < itemNameArray.length) {
                        itemName = itemNameArray[errorColumnIndex];
                    }
                    cMsg.setMessageInfo(NSBM0160E, new String[] {itemName });
                    return;
                } else if (status >= CSV_READ_STATUS_CODE_2000) {
                    // NSAM0210E
                    int errorColumnIndex = status - CSV_READ_STATUS_CODE_2000 - 1;
                    String[] itemNameArray = createCsvFileHeaderNameArray(cMsg);
                    String itemName = "";
                    if (errorColumnIndex >= 0 && errorColumnIndex < itemNameArray.length) {
                        itemName = itemNameArray[errorColumnIndex];
                    }
                    cMsg.setMessageInfo(NSBM0161E, new String[] {itemName });
                    return;
                }

                NSBL0210CommonLogic.setFMsgToSMsg(fMsg, sMsg, rowIndex);
                rowIndex++;

                fMsg.clear();
            }
            sMsg.A.setValidCount(rowIndex);

            if (rowIndex == 0) {
                // only header line
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }

            NSBL0210CommonLogic.completionSMsgAfterUpload(cMsg.glblCmpyCd.getValue(), cMsg, sMsg);

            boolean result = NSBL0210CommonLogic.validateDetailLinesForUpload(cMsg, sMsg);

            if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode()) && result) {
                cMsg.setMessageInfo(NZZM0002I);
            } else {
                pageIndex = NSBL0210CommonLogic.getFirstErrorIndex(cMsg, sMsg);
            }

        } finally {
            // set Paging Data
            NSBL0210CommonLogic.pagenation(cMsg, sMsg, pageIndex);

            mappedFile.close();
            cMsg.xxFileData_U.deleteTempFile();
            ZYPExcelUtil.deleteFile(csvFilePath);
        }
    }

    private void doProcess_NSBL0210Scrn00_SelectAll(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox, ZYPConstant.CHKBOX_ON_Y);
        }
        int rowIndex = NSBL0210CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSBL0210CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0210Scrn00_UnSelectAll(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox.clear();
        }
        int rowIndex = NSBL0210CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSBL0210CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0210Scrn00_InsertRow(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int currentRowCount = sMsg.A.getValidCount();
        if (sMsg.A.getValidCount() >= sMsg.A.length()) {
            cMsg.setMessageInfo(NSBM0058E, new String[] {"Service Travel Charge", String.valueOf(sMsg.A.length()) });
            return;
        }

        NSBL0210CommonLogic.setEmptyRecord(getGlobalCompanyCode(), cMsg, sMsg, currentRowCount);

        int rowsPerPage = cMsg.A.length();
        int lastPage = NSBL0210CommonLogic.getLastPageNum(cMsg, sMsg);
        int pagenationFrom = NSBL0210CommonLogic.convertPageNumToFirstRowIndex(rowsPerPage, lastPage);
        NSBL0210CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0210Scrn00_DeleteRow(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX, ZYPConstant.FLG_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSBM0007E);
            return;
        }

        int deleteRowIndex = sMsg.D.getValidCount();
        for (int rowIndex : selectedRows) {
                NSBL0210_ASMsg asMsg = sMsg.A.no(rowIndex);

                if (ZYPCommonFunc.hasValue(asMsg.svcLborChrgPk)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(deleteRowIndex).svcLborChrgPk, asMsg.svcLborChrgPk);
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(deleteRowIndex).ezUpTime, asMsg.ezUpTime);
                    ZYPEZDItemValueSetter.setValue(sMsg.D.no(deleteRowIndex).ezUpTimeZone, asMsg.ezUpTimeZone);
                    deleteRowIndex++;
                }
        }

        sMsg.D.setValidCount(deleteRowIndex);

        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);

        NSBL0210CommonLogic.reNumberingRowNum(sMsg);

        int rowsPerPage = cMsg.A.length();
        int lastPage = NSBL0210CommonLogic.getLastPageNum(cMsg, sMsg);
        int pagenationFrom = NSBL0210CommonLogic.convertPageNumToFirstRowIndex(rowsPerPage, lastPage);
        NSBL0210CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0210Scrn00_ApplyModelGroup(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {

        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int selRowIndex = cMsg.xxRowNum_S.getValueInt();
        String mdlGrpNm = cMsg.A.no(selRowIndex).mdlGrpNm.getValue();

        if (!ZYPCommonFunc.hasValue(mdlGrpNm)) {
            cMsg.A.no(selRowIndex).mdlGrpNm.setErrorInfo(1, ZZZM9025E, new String[] {"Model Group" });
            return;
        }

        DS_MDL_GRPTMsg tMsg = NSBL0210CommonLogic.findDsMdlGrp(cMsg.glblCmpyCd.getValue(), mdlGrpNm);
        if (tMsg == null) {
            cMsg.A.no(selRowIndex).mdlGrpNm.setErrorInfo(1, NSBM0151E, new String[] {"Model Group", "Model Group" });
            return;
        }

        int sMsgIndex = cMsg.A.no(selRowIndex).xxRowNum.getValueInt() - 1;
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIndex).mdlGrpId, tMsg.mdlGrpId);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIndex).mdlGrpNm, tMsg.mdlGrpNm);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIndex).mdlGrpDescTxt, tMsg.mdlGrpDescTxt);

        int rowIndex = NSBL0210CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSBL0210CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0210Scrn00_ApplyShift(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {

        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int selRowIndex = cMsg.xxRowNum_S.getValueInt();
        String svcLineBizCd = cMsg.A.no(selRowIndex).svcLineBizCd.getValue();
        String svcPrcShiftNum = cMsg.A.no(selRowIndex).svcPrcShiftNum.getValue();

        // START 06/06/2016 [QC#5489, MOD]
        boolean isMandatoryError = false;
        if (!ZYPCommonFunc.hasValue(svcLineBizCd)) {
            cMsg.A.no(selRowIndex).svcLineBizCd.setErrorInfo(1, ZZZM9025E, new String[] {"Line of Business" });
            isMandatoryError = true;
        }

        if (!ZYPCommonFunc.hasValue(svcPrcShiftNum)) {
            cMsg.A.no(selRowIndex).svcPrcShiftNum.setErrorInfo(1, ZZZM9025E, new String[] {"Shift#" });
            isMandatoryError = true;
        }
        if (isMandatoryError) {
            return;
        }
        // END 06/06/2016 [QC#5489, MOD]

        // START 06/06/2016 [QC#5489, MOD]
        // SVC_PRC_SHIFTTMsg tMsg = NSBL0210CommonLogic.findSvcPrcShift(cMsg.glblCmpyCd.getValue(), svcPrcShiftNum);
        SVC_PRC_SHIFTTMsg tMsg = NSBL0210CommonLogic.findSvcPrcShift(cMsg.glblCmpyCd.getValue(), svcPrcShiftNum, svcLineBizCd);
        // END 06/06/2016 [QC#5489, MOD]
        if (tMsg == null) {
            cMsg.A.no(selRowIndex).svcPrcShiftNum.setErrorInfo(1, NSBM0151E, new String[] {"Shift#", "Service Price Shift" });
            return;
        }

        if (ZYPCommonFunc.hasValue(tMsg.svcLineBizCd) && ZYPCommonFunc.hasValue(svcLineBizCd) && !tMsg.svcLineBizCd.getValue().equals(svcLineBizCd)) {
            cMsg.A.no(selRowIndex).svcPrcShiftNum.setErrorInfo(1, NSBM0157E, new String[] {"Shift#", "Line of Business" });
            return;
        }

        int sMsgIndex = cMsg.A.no(selRowIndex).xxRowNum.getValueInt() - 1;
        // START 06/06/2016 [QC#5489, DEL]
        // ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIndex).svcPrcShiftPk, tMsg.svcPrcShiftPk);
        // END 06/06/2016 [QC#5489, DEL]
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIndex).svcPrcShiftNum, tMsg.svcPrcShiftNum);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIndex).svcPrcShiftDescTxt, tMsg.svcPrcShiftDescTxt);

        int rowIndex = NSBL0210CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSBL0210CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0210Scrn00_ApplyIntgItem(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {

        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int selRowIndex = cMsg.xxRowNum_S.getValueInt();
        String mdseCd = cMsg.A.no(selRowIndex).intgMdseCd.getValue();

        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            cMsg.A.no(selRowIndex).intgMdseCd.setErrorInfo(1, ZZZM9025E, new String[] {"Default Intangible Item#" });
            return;
        }

        MDSETMsg tMsg = NSBL0210CommonLogic.findMdse(cMsg.glblCmpyCd.getValue(), mdseCd);
        if (tMsg == null) {
            cMsg.A.no(selRowIndex).intgMdseCd.setErrorInfo(1, NSBM0151E, new String[] {"Default Intangible Item#", "Mdse" });
            return;
        }

        int sMsgIndex = cMsg.A.no(selRowIndex).xxRowNum.getValueInt() - 1;
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIndex).intgMdseCd, tMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(sMsgIndex).mdseDescShortTxt, tMsg.mdseDescShortTxt);

        int rowIndex = NSBL0210CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSBL0210CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0210Scrn00_PageNext(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int rowIndex = NSBL0210CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() + 1);
        NSBL0210CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0210Scrn00_PagePrev(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        int rowIndex = NSBL0210CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() - 1);
        NSBL0210CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NSBL0210_NMAL6050(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        doProcess_NSBL0210Scrn00_ApplyModelGroup(cMsg, sMsg);
    }

    private void doProcess_NSBL0210Scrn00_TBLColumnSort(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        NSBL0210CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            int i = 0;
            // START 2016/08/01 [QC#12242, MOD]
            NSBL0210CommonLogic.reNumberingRowNum(sMsg);
            // END 2016/08/01 [QC#12242, MOD]
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set Paging Data
            NSBL0210CommonLogic.pagenation(cMsg, sMsg, 0);
        }
    }

    private void initMessages(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {

        // START 05/23/2016 [QC#8567, ADD]
        cMsg.mdlGrpNm_H.clear();
        cMsg.svcLineBizCd_H.clear();
        cMsg.svcPrcShiftDescTxt_H.clear();
        // END   05/23/2016 [QC#8567, ADD]

        sMsg.clear();
        clearSearchResult(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
    }

    private void clearSearchResult(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.D);
        ZYPTableUtil.clear(cMsg.A);
    }

    private void searchData(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSBL0210Query.getInstance().searchData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            NSBL0210CommonLogic.pagenation(cMsg, sMsg, 0);

        } else {
            // No result
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.xxPageShowCurNum.clear();
            cMsg.xxPageShowTotNum.clear();
        }
    }

    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSBL0210F00FMsg fMsg, NSBL0210CMsg cMsg) {
        csvOutFile.writeHeader(createCsvFileHeaderNameArray(cMsg));
    }

    private String[] createCsvFileHeaderNameArray(NSBL0210CMsg cMsg) {
        List<String> hdrElementList = new ArrayList<String>();
        hdrElementList.add("Model Group Name");
        hdrElementList.add("Model Group Description");
        hdrElementList.add("Line of Business");
        hdrElementList.add("Charge Travel");
        hdrElementList.add("Shift#");
        hdrElementList.add("Shift Description");
        hdrElementList.add("Labor Per Hour");
        hdrElementList.add("Minimum Hours");
        hdrElementList.add("Default Intangible Code");
        hdrElementList.add("Default Intangible Description");

        return hdrElementList.toArray(new String[hdrElementList.size()]);
    }

    private void writeCsvRecord(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg, ResultSet rs, NSBL0210F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        ZYPEZDItemValueSetter.setValue(fMsg.mdlGrpNm, rs.getString("MDL_GRP_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdlGrpDescTxt, rs.getString("MDL_GRP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcLineBizCd, rs.getString("SVC_LINE_BIZ_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcPrcTrvlChrgFlg, rs.getString("SVC_PRC_TRVL_CHRG_FLG"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcPrcShiftNum, rs.getString("SVC_PRC_SHIFT_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcPrcShiftDescTxt, rs.getString("SVC_PRC_SHIFT_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcLborUnitAmt, rs.getBigDecimal("SVC_LBOR_UNIT_AMT"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcMinChrgHrsAot, rs.getBigDecimal("SVC_MIN_CHRG_HRS_AOT"));
        ZYPEZDItemValueSetter.setValue(fMsg.intgMdseCd, rs.getString("INTG_MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, rs.getString("MDSE_DESC_SHORT_TXT"));

        csvOutFile.write();
    }

    private Map<String, Object> setQueryParam(NSBL0210CMsg cMsg, NSBL0210SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("svcLineBizCd", cMsg.svcLineBizCd_H.getValue());
        params.put("mdlGrpNm", cMsg.mdlGrpNm_H.getValue());
        params.put("svcPrcShiftDescTxt", cMsg.svcPrcShiftDescTxt_H.getValue());
        // START 2017/06/15 N.Arai [QC#19188, MOD]
        params.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        // END 2017/06/15 N.Arai [QC#19188, MOD]
        params.put("limitNum", sMsg.A.length() + 1);

        return params;
    }
}
