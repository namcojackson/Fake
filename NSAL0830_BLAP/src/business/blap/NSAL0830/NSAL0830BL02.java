/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0830;

import static business.blap.NSAL0830.constant.NSAL0830Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
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
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0830.common.NSAL0830CommonLogic;
import business.db.DS_XS_COPY_INTFCTMsg;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.file.NSAL0830F00FMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsXsCopyIntfc;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_COPY_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          QC#5648
 * 2016/03/23   Hitachi         Y.Takeno        Update          QC#5450
 * 2016/03/25   Hitachi         T.Iwamoto       Create          QC#5542
 * 2016/03/30   Hitachi         T.Iwamoto       Update          QC#5541
 * 2016/04/07   Hitachi         T.Iwamoto       Update          QC#6411
 * 2016/04/19   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/07/22   Hitachi         M.Gotou         Update          QC#11853,11854,12077
 * 2016/07/22   Hitachi         Y.Takeno        Update          QC#12086
 * 2016/07/25   Hitachi         Y.Takeno        Update          QC#11829
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#12141
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#11847,12075
 * 2016/08/09   Hitachi         Y.Takeno        Update          QC#11831,11848
 * 2016/08/22   Hitachi         T.Mizuki        Update          QC#11862
 * 2016/11/28   Hitachi         T.Tomita        Update          QC#12077
 * 2017/09/15   Hitachi         A.Kohinata      Update          QC#19775
 *</pre>
 */
public class NSAL0830BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0830_INIT".equals(screenAplID)) {
                doProcess_NSAL0830_INIT((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_Search((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_PageJump((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_PageNext((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_PagePrev((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_Reset((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_Search((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_SelectAll((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_UnselectAll".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_UnselectAll((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_TBLColumnSort((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_Download((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_TemplateDownload((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_Upload((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_ValidateData".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_ValidateData((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_CMN_Delete((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_AddPricingRow".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_AddPricingRow((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            } else if ("NSAL0830Scrn00_DeletePricingRow".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_DeletePricingRow((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
                //  MOD start 2016/04/07 CSA Defect#6411
//            } else if ("NSAL0830Scrn00_ClickCheckbox".equals(screenAplID)) {
//                doProcess_NSAL0830Scrn00_ClickCheckbox((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
                //  MOD end 2016/04/07 CSA Defect#6411
            // START 2016/07/28 M.Gotou [QC#12141, ADD]
            } else if ("NSAL0830Scrn00_AddRow".equals(screenAplID)) {
                doProcess_NSAL0830Scrn00_AddRow((NSAL0830CMsg) cMsg, (NSAL0830SMsg) sMsg);
            // END 2016/07/28 M.Gotou [QC#12141, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830_INIT(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        String dsContrIntfcBatNum = cMsg.dsContrIntfcBatNum_S.getValue();
        // ADD start 2016/03/30 CSA Defect#5541
        String dsContrSrcRefNum = cMsg.dsContrSrcRefNum_S.getValue();
        // ADD end 2016/03/30 CSA Defect#5541
        List<BigDecimal> dsContrIntfcPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            if (hasValue(cMsg.P.no(i).dsContrIntfcPk)) {
                dsContrIntfcPkList.add(cMsg.P.no(i).dsContrIntfcPk.getValue());
            }
        }

        sMsg.clear();
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.P);
        ZYPTableUtil.clear(sMsg.A);
        // START 2016/07/28 M.Gotou [QC#11847,12075, MOD]
        cMsg.setCommitSMsg(true);
        // END 2016/07/28 M.Gotou [QC#11847,12075, MOD]

        setValue(cMsg.dsContrIntfcBatNum_S, dsContrIntfcBatNum);
        // ADD start 2016/03/30 CSA Defect#5541
        setValue(cMsg.dsContrSrcRefNum_S, dsContrSrcRefNum);
        setValue(cMsg.dsContrIntfcBatNum_S2, cMsg.dsContrIntfcBatNum_S);
        setValue(cMsg.dsContrSrcRefNum_S2, cMsg.dsContrSrcRefNum_S);
        // ADD end 2016/03/30 CSA Defect#5541
        cMsg.P.setValidCount(dsContrIntfcPkList.size());
        int index = 0;
        for (BigDecimal dsContrIntfcPk : dsContrIntfcPkList) {
            setValue(cMsg.P.no(index).dsContrIntfcPk, dsContrIntfcPk);
            index++;
        }

        cMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        ZYPCodeDataUtil.createPulldownList(CONTR_INTFC_SRC_TP.class, cMsg.contrIntfcSrcTpCd_SC, cMsg.contrIntfcSrcTpDescTxt_SC);
        ZYPCodeDataUtil.createPulldownList(CONTR_INTFC_SRC_TP.class, cMsg.contrIntfcSrcTpCd_AC, cMsg.contrIntfcSrcTpDescTxt_AC);

        getSearchData(cMsg, sMsg);

        // START 2016/07/28 M.Gotou [QC#12141, ADD]
        S21SsmEZDResult ssmResult = NSAL0830Query.getInstance().countCpltData(cMsg, sMsg);
        BigDecimal count = (BigDecimal) ssmResult.getResultObject();
        setValue(cMsg.xxExstFlg, ZYPConstant.FLG_OFF_N);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            setValue(cMsg.xxExstFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2016/07/28 M.Gotou [QC#12141, ADD]
    }

    /**
     * do process (Page Jump)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_PageJump(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_PageNext(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() + 1);
        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_PagePrev(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() - 1);
        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Reset)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_Reset(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        doProcess_NSAL0830_INIT(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * do process (Search)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_Search(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        cMsg.setCommitSMsg(true);

        getSearchData(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * do process (TBLColumnSort)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_TBLColumnSort(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

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
            // START 2016/07/25 [QC#11829, MOD]
            for (; i < sMsg.A.getValidCount(); i++) {
                sMsg.A.no(i).xxRowNum_A.setValue(i + 1);
            }
            // END 2016/07/25 [QC#11829, MOD]
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set Paging Data
            NSAL0830CommonLogic.pagenation(cMsg, sMsg, 0);
        }
    }

    /**
     * do process (download)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_Download(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0830Query query = NSAL0830Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            // create csv file
            cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
            Map<String, Object> params = setDownloadPram(cMsg, sMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("getMainData", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSAL0830F00FMsg fMsg = new NSAL0830F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
            writeCsvFileForHeaderTab(cMsg, sMsg, rs, fMsg, csvOutFile);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * do process (TemplateDownload)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_TemplateDownload(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        // create csv file
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL0830F00FMsg fMsg = new NSAL0830F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }

    /**
     * do process (SelectAll)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_SelectAll(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (MODE_AFTER_OPEN.equals(cMsg.xxModeCd.getValue()) && !DS_CONTR_PROC_STS.COMPLEATED.equals(sMsg.A.no(i).dsContrProcStsCd_A.getValue())) {
                setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
            }
        }
        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (UnselectAll)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_UnselectAll(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A.clear();
        }
        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Upload)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_Upload(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        // START 2016/05/20 [QC#4061, MOD]
        String excelPath = cMsg.xxFileData_U.getTempFilePath();
        String path = ZYPExcelUtil.excelToCsvFile(excelPath);
        // END   2016/05/20 [QC#4061, MOD]

        NSAL0830F00FMsg fMsg = new NSAL0830F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
            int status = -1;
            // START 2016/07/28 M.Gotou [QC#11847,12075, MOD]
            int rowNum = sMsg.A.getValidCount();

            // do basic check and load to screen(for all csv data)
            while ((status = mappedFile.read()) != 1) {

                rowNum++;
                if (!validateAndCopyToSMsg_UPLOAD(status, rowNum, sMsg.A, fMsg, cMsg)) {
                    if (NSAM0214E.equals(cMsg.getMessageCode())) {
                        // START 2016/07/22 [QC#12086, ADD]
                        ZYPTableUtil.clear(sMsg.A);
                        sMsg.A.setValidCount(0);
                        // END   2016/07/22 [QC#12086, ADD]
                        return;
                    }
                }
                fMsg.clear();
                // MOD start 2016/04/19 CSA Defect#6691
                sMsg.A.setValidCount(rowNum);
                // MOD end 2016/04/19 CSA Defect#6691
            }
            // END 2016/07/28 M.Gotou [QC#11847,12075, MOD]

            // Validation Excess Copy Interface Table
            List<DS_XS_COPY_INTFCTMsg> dsXsCopyIntfcTMsgList = NSAL0830CommonLogic.setDsXsCopyIntfc(cMsg, sMsg);
            NSXC001001ValidationDsXsCopyIntfc.validationDsXsCopyIntfc(dsXsCopyIntfcTMsgList);
            NSAL0830CommonLogic.updateValidationResult(cMsg, sMsg, dsXsCopyIntfcTMsgList, false);

            // ADD start 2016/04/19 CSA Defect#6691
            cMsg.setCommitSMsg(true);
            // ADD end 2016/04/19 CSA Defect#6691

            if (!hasValue(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(NZZM0002I);
            }

        } finally {
            // set Paging Data
            NSAL0830CommonLogic.pagenation(cMsg, sMsg, 0);

            mappedFile.close();
            cMsg.xxFileData_U.deleteTempFile();
            // START 2016/05/20 [QC#4061, ADD]
            ZYPExcelUtil.deleteFile(path);
            // END   2016/05/20 [QC#4061, ADD]
        }
    }

    /**
     * do process (ValidateData)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_ValidateData(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (CMN_Delete)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_CMN_Delete(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (AddPricingRow)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_AddPricingRow(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NSAM0320E, new String[] {MSG_PARAM_ROW, Integer.toString(sMsg.A.length()) });
            return;
        }

        int cRowSeqNum = cMsg.rowSqNum_H.getValueInt();
        int sRowIndex = sMsg.A.no(cRowSeqNum).xxRowNum_A.getValueInt() - 1;
        int sValidCount = sMsg.A.getValidCount();
        sMsg.A.setValidCount(sValidCount + 1);

        for (int i = sValidCount; i > sRowIndex; i--) {
            EZDMsg.copy(sMsg.A.no(i - 1), null, sMsg.A.no(i), null);

            if (i == sRowIndex + 1) {
                sMsg.A.no(i).xsMtrAmtRate_A.clear();
                sMsg.A.no(i).xsMtrCopyQty_A.clear();
                sMsg.A.no(i).dsXsCopyIntfcPk_A.clear();
                setValue(sMsg.A.no(i).dsContrIntfcStsCd_A, XS_COPY_INTFC_STS.NORMAL);
                sMsg.A.no(i).intfcErrMsgTxt_A.clear();
                setValue(sMsg.A.no(i).xxTpCd_A, XX_TP_CD_D);
                // ADD start 2016/04/19 CSA Defect#6691
                setValue(sMsg.A.no(i).xxDplyCtrlFlg_A, ZYPConstant.FLG_OFF_N);
                // ADD end 2016/04/19 CSA Defect#6691
                sMsg.A.no(i).ezUpTime.clear();
                sMsg.A.no(i).ezUpTimeZone.clear();
            }
        }

        // numbering rowNum
        NSAL0830CommonLogic.numberingRowNum(sMsg);

        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    //  MOD start 2016/04/19 CSA Defect#6691
    /**
     * do process (DeletePricingRow)
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     */
    private void doProcess_NSAL0830Scrn00_DeletePricingRow(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        if (!hasValue(cMsg.xxRadioBtn_H)) {
            cMsg.setMessageInfo(NSAM0034E);
            return;
        }

        int selectedRow = cMsg.xxRadioBtn_H.getValueInt();
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.A.no(selectedRow).xxDplyCtrlFlg_A.getValue())) {
            cMsg.setMessageInfo(NSAM0452E);
            return;
        }

        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        NSAL0830CommonLogic.setTargetDeleteList(sMsg, selectedRow);

        // numbering rowNum
        NSAL0830CommonLogic.numberingRowNum(sMsg);

        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
            return;
        }
    }
    // MOD end 2016/04/19 CSA Defect#6691

    // DELETE start 2016/04/06 CSA Defect#6411
//    /**
//     * do process (ClickCheckbox)
//     * @param cMsg NSAL0830CMsg
//     * @param sMsg NSAL0830SMsg
//     */
//    private void doProcess_NSAL0830Scrn00_ClickCheckbox(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
//
//        NSAL0830CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
//
//        int idx = cMsg.xxRowNum_H.getValueInt() - 1;
//        String checkFlg = cMsg.A.no(idx).xxChkBox_A.getValue();
//        if (!hasValue(cMsg.A.no(idx).dsContrSrcRefNum_A)) {
//            return;
//        }
//
//        BigDecimal dsContrIntfcPk = cMsg.A.no(idx).dsContrIntfcPk_A.getValue();
//        if (dsContrIntfcPk != null) {
//            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//                if (dsContrIntfcPk.compareTo(sMsg.A.no(i).dsContrIntfcPk_A.getValue()) == 0) {
//                    if (ZYPConstant.CHKBOX_ON_Y.equals(checkFlg)) {
//                        setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
//                    } else {
//                        sMsg.A.no(i).xxChkBox_A.clear();
//                    }
//                }
//            }
//        }
//
//        int rowIndex = NSAL0830CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
//        NSAL0830CommonLogic.pagenation(cMsg, sMsg, rowIndex);
//    }
    // DELETE end 2016/04/06 CSA Defect#6411

    /**
     * get Search Data List
     * @param cMsg NSAL0830CMsg
     * @return Data List
     */
    private void getSearchData(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        sMsg.clear();
        S21SsmEZDResult ssmResult = NSAL0830Query.getInstance().getSearchData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            // numbering rowNum
            NSAL0830CommonLogic.numberingRowNum(sMsg);

            // set error message to header record
            NSAL0830CommonLogic.setErrorMessageToHeader(sMsg);

            // set Paging Data
            NSAL0830CommonLogic.pagenation(cMsg, sMsg, 0);

            if (isAllwDataCrctFlg(cMsg, sMsg)) {
                setValue(cMsg.xxModeCd, MODE_AFTER_OPEN);
            }

        } else {
            // No result
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.xxPageShowCurNum.clear();
            cMsg.xxPageShowTotNum.clear();
        }
    }

    /**
     * isAllwDataCrctFlg
     * @param cMsg
     * @param sMsg
     * @return true/false
     */
    private boolean isAllwDataCrctFlg(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        // del start 2017/09/15 QC#19775
//        if (hasValue(sMsg.A.no(0).contrIntfcSrcTpCd_A)) {
//            DS_CONTR_INTFC_DEF_RULETMsg inMsg = new DS_CONTR_INTFC_DEF_RULETMsg();
//            inMsg.setConditionValue("glblCmpyCd", cMsg.glblCmpyCd.getValue());
//            inMsg.setConditionValue("contrIntfcSrcTpCd", sMsg.A.no(0).contrIntfcSrcTpCd_A.getValue());
//            inMsg = (DS_CONTR_INTFC_DEF_RULETMsg) EZDTBLAccessor.findByKey(inMsg);
//            if (inMsg != null) {
//                if (ZYPConstant.FLG_ON_Y.equals(inMsg.allwDataCrctFlg.getValue())) {
//                    return true;
//                }
//            }
//        }
        // del end 2017/09/15 QC#19775
        return false;
    }

    /**
     * Write csv file
     * @param bizMsg NSAL0830CMsg
     * @param globalMsg NSAL0830SMsg
     * @param rs ResultSet
     * @param fMsg NSAL0830F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForHeaderTab(NSAL0830CMsg bizMsg, NSAL0830SMsg globalMsg, ResultSet rs, NSAL0830F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        // write header
        writeCsvFileHeader(csvOutFile, fMsg, bizMsg);

        // write contents
        do {
            if (rs.getRow() > LIMIT_DOWNLOAD) {
                bizMsg.setMessageInfo(NZZM0001W);
                break;
            }
            // resultset -> fMsg
            setValue(fMsg.dsContrIntfcBatNum, rs.getString("DS_CONTR_INTFC_BAT_NUM"));
            setValue(fMsg.dsContrSrcRefNum, rs.getString("DS_CONTR_SRC_REF_NUM"));
            setValue(fMsg.contrIntfcSrcTpCd, rs.getString("CONTR_INTFC_SRC_TP_CD"));
            setValue(fMsg.serNum, rs.getString("SER_NUM"));
            setValue(fMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
            setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
            setValue(fMsg.bllgMtrLbCd, rs.getString("BLLG_MTR_LB_CD"));
            // START 2016/03/22 K.Yamada [QC#5648, MOD]
            String mtrLbDescTxt = rs.getString("MTR_LB_DESC_TXT");
            if (hasValue(mtrLbDescTxt)) {
                int length = mtrLbDescTxt.length();
                if (length > LENGTH_30) {
                    length = LENGTH_30;
                }
                setValue(fMsg.mtrLbDescTxt, mtrLbDescTxt.substring(0, length));
            }
            // END 2016/03/22 K.Yamada [QC#5648, MOD]
            setValue(fMsg.xsMtrCopyQty, rs.getBigDecimal("XS_MTR_COPY_QTY"));
            setValue(fMsg.xsMtrAmtRate, rs.getBigDecimal("XS_MTR_AMT_RATE"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * setDownloadPram
     * @param cMsg NSAL0830CMsg
     * @param sMsg NSAL0830SMsg
     * @return S21SsmEZDResult
     */
    private Map<String, Object> setDownloadPram(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (hasValue(cMsg.contrIntfcSrcTpCd_SS)) {
            params.put("contrIntfcSrcTpCd", (String) cMsg.contrIntfcSrcTpCd_SS.getValue());
        }
        if (hasValue(cMsg.dsContrSrcRefNum_S)) {
            params.put("dsContrSrcRefNum", (String) cMsg.dsContrSrcRefNum_S.getValue());
        }
        if (hasValue(cMsg.dsContrIntfcBatNum_S)) {
            params.put("dsContrIntfcBatNum", (String) cMsg.dsContrIntfcBatNum_S.getValue());
        }
        if (hasValue(cMsg.dsContrNum_S)) {
            params.put("dsContrNum", (String) cMsg.dsContrNum_S.getValue());
        }
        if (hasValue(cMsg.xxErrFlg_S) && ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxErrFlg_S.getValue())) {
            List<String> xxErrFlgList = new ArrayList<String>();
            xxErrFlgList.add(DS_CONTR_PROC_STS.ERROR);
            xxErrFlgList.add(DS_CONTR_PROC_STS.REPROCESS);
            params.put("xxErrFlgList", xxErrFlgList);
        }
        if (cMsg.P.getValidCount() > 0) {
            List<BigDecimal> dsContrIntfcPkList = new ArrayList<BigDecimal>();
            for (int i = 0; i < cMsg.P.getValidCount(); i++) {
                dsContrIntfcPkList.add((BigDecimal) cMsg.P.no(i).dsContrIntfcPk.getValue());
            }
            params.put("dsContrIntfcPkList", dsContrIntfcPkList);
        }
        params.put("xxTpCdH", XX_TP_CD_H);
        params.put("xxTpCdD", XX_TP_CD_D);
        params.put("limitNum", LIMIT_DOWNLOAD);
        // START 2016/03/23 [QC#5450, ADD]
        params.put("contrIntfcDtlTpCd", CONTR_INTFC_DTL_TP.USAGE);
        // END   2016/03/23 [QC#5450, ADD]

        return params;
    }

    /**
     * @param writeCsvFileHeader
     * @param fMsg
     * @param cMsg
     */
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0830F00FMsg fMsg, NSAL0830CMsg cMsg) {
        final String[] csvHeader = new String[] {"Interface Bat#", "Source Ref#", "Source Type", "Serial#", "IB ID", "Mdse Code", "Billing Counter Code", "Billing Counter Name", "Allowance", "Price" };
        csvOutFile.writeHeader(csvHeader);
    }

    private boolean validateAndCopyToSMsg_UPLOAD(int status, int count, NSAL0830_ASMsgArray asMsgArray, NSAL0830F00FMsg fMsg, NSAL0830CMsg cMsg) {

        if (count > asMsgArray.length()) {
            cMsg.setMessageInfo(NSAM0214E);
            return false;
        }
        int row = count - 1;

        NSAL0830_ASMsg sMsgLine = asMsgArray.no(row);
        setValue(sMsgLine.xxRowNum_A, BigDecimal.valueOf(count));

        if (status == CSV_READ_STATUS_CODE_1000) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0208E));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0208E);
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        } else if (status > CSV_READ_STATUS_CODE_1000 && status < CSV_READ_STATUS_CODE_2000) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0209E));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0209E);
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        } else if (status >= CSV_READ_STATUS_CODE_2000) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0210E));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0210E);
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        }

        // MOD start 2016/04/19 CSA Defect#6691
        if (!hasValue(fMsg.dsContrIntfcBatNum)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            // START 2016/08/09 Y.Takeno [QC#11831, ADD]
            return false;
            // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        } else {
            setValue(sMsgLine.dsContrIntfcBatNum_A, fMsg.dsContrIntfcBatNum);
        }

        if (!hasValue(fMsg.dsContrSrcRefNum)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            // START 2016/08/09 Y.Takeno [QC#11831, ADD]
            return false;
            // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        } else {
            setValue(sMsgLine.dsContrSrcRefNum_A, fMsg.dsContrSrcRefNum);
        }

        if (!hasValue(fMsg.contrIntfcSrcTpCd)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            // START 2016/08/09 Y.Takeno [QC#11831, ADD]
            return false;
            // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        } else {
            setValue(sMsgLine.contrIntfcSrcTpCd_A, fMsg.contrIntfcSrcTpCd);
        }

//        setValue(sMsgLine.dsContrSrcRefNum_A, fMsg.dsContrSrcRefNum);
//        setValue(sMsgLine.contrIntfcSrcTpCd_A, fMsg.contrIntfcSrcTpCd);
        // MOD end 2016/04/19 CSA Defect#6691


        // START 2016/08/09 Y.Takeno [QC#11848, MOD]
        // MOD start 2016/04/19 CSA Defect#6691
        // START 2016/03/22 K.Yamada [QC#5648, MOD]
        if (!hasValue(fMsg.bllgMtrLbCd) && hasValue(fMsg.mtrLbDescTxt)) {
            setValue(sMsgLine.bllgMtrLbCd_A, getMtrLbCd(cMsg.glblCmpyCd.getValue(), fMsg.mtrLbDescTxt.getValue(), MTR_LB_TP.BILLING_METER));
            setValue(sMsgLine.mtrLbDescTxt_A, fMsg.mtrLbDescTxt);
        } else if (hasValue(fMsg.bllgMtrLbCd) && !hasValue(fMsg.mtrLbDescTxt)) {
            setValue(sMsgLine.bllgMtrLbCd_A, fMsg.bllgMtrLbCd);
            MTR_LBTMsg tMsg = getMtrLbDescTxt(cMsg.glblCmpyCd.getValue(), fMsg.bllgMtrLbCd.getValue());
            if (tMsg == null) {
                setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[] {BLLG_MTR_LB_CD}));
                sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[] {BLLG_MTR_LB_CD});
                setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                return false;
            }
            setValue(sMsgLine.mtrLbDescTxt_A, tMsg.mtrLbDescTxt);
        } else if (!hasValue(fMsg.bllgMtrLbCd) && !hasValue(fMsg.mtrLbDescTxt)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {BLLG_MTR_LB_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {BLLG_MTR_LB_CD});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            // START 2016/08/09 Y.Takeno [QC#11831, ADD]
            return false;
            // END 2016/08/09 Y.Takeno [QC#11831, ADD]
        } else {
            setValue(sMsgLine.bllgMtrLbCd_A, fMsg.bllgMtrLbCd);
            setValue(sMsgLine.mtrLbDescTxt_A, fMsg.mtrLbDescTxt);
        }
        // END 2016/03/22 K.Yamada [QC#5648, MOD]
        // MOD end 2016/04/19 CSA Defect#6691

        // START 2016/03/22 K.Yamada [QC#5648, MOD]
//        setValue(sMsgLine.mtrLbDescTxt_A, fMsg.mtrLbDescTxt);
        // END 2016/03/22 K.Yamada [QC#5648, MOD]
        // END 2016/08/09 Y.Takeno [QC#11848, MOD]

        setValue(sMsgLine.xsMtrCopyQty_A, fMsg.xsMtrCopyQty);
        setValue(sMsgLine.xsMtrAmtRate_A, fMsg.xsMtrAmtRate);

        // MOD start 2016/04/19 CSA Defect#6691
//        setXsCopyIntfcData(cMsg.glblCmpyCd.getValue(), sMsgLine, fMsg);
//
//        if (!hasValue(sMsgLine.dsXsCopyIntfcPk_A)) {
//            this.setContrIntfcPk(cMsg.glblCmpyCd.getValue(), sMsgLine, fMsg);
//        }

     // START 2016/07/22 M.Gotou [QC#12077, MOD]
//        setValue(sMsgLine.serNum_A, fMsg.serNum);
//
//        if (!hasValue(fMsg.svcMachMstrPk) && hasValue(fMsg.serNum)) {
//            setValue(sMsgLine.svcMachMstrPk_A, getIbId(cMsg.glblCmpyCd.getValue(), fMsg.serNum.getValue()));
//        } else {
//            setValue(sMsgLine.svcMachMstrPk_A, fMsg.svcMachMstrPk);
//        }
//        setValue(sMsgLine.mdseCd_A, fMsg.mdseCd);
        // mod start 2016/11/28 CSA Defect#12077
        if (hasValue(fMsg.mdseCd)) {
            setValue(sMsgLine.mdseCd_A, fMsg.mdseCd);
        }
        if (hasValue(fMsg.svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg tMsg = NSAL0830CommonLogic.getSvcMachMstr(cMsg.glblCmpyCd.getValue(), fMsg.svcMachMstrPk.getValue());
            if (tMsg != null) {
                setValue(sMsgLine.svcMachMstrPk_A, fMsg.svcMachMstrPk);
                if (hasValue(fMsg.serNum)) {
                    if (tMsg.serNum.getValue().equals(fMsg.serNum.getValue())) {
                        setValue(sMsgLine.serNum_A, fMsg.serNum);
                    } else {
                        setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0556E));
                        sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0556E);
                        setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                        return false;
                    }
                } else {
                    setValue(sMsgLine.serNum_A, tMsg.serNum);
                }
                if (hasValue(fMsg.mdseCd)) {
                    if (!tMsg.mdseCd.getValue().equals(fMsg.mdseCd.getValue())) {
                        setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0557E));
                        sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0557E);
                        setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                        return false;
                    }
                } else {
                    setValue(sMsgLine.mdseCd_A, tMsg.mdseCd);
                }
            } else {
                setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[]{SVC_MACH_MSTR_PK}));
                sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[]{SVC_MACH_MSTR_PK});
                setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                return false;
            }
        } else {
            if (hasValue(fMsg.serNum)) {
                setValue(sMsgLine.serNum_A, fMsg.serNum);
                SVC_MACH_MSTRTMsg tMsg = NSAL0830CommonLogic.getSvcMachMstrForSerNum(cMsg.glblCmpyCd.getValue(), fMsg.serNum.getValue());
                if (tMsg != null) {
                    setValue(sMsgLine.svcMachMstrPk_A, tMsg.svcMachMstrPk);
                    if (hasValue(fMsg.mdseCd)) {
                        if (!tMsg.mdseCd.getValue().equals(fMsg.mdseCd.getValue())) {
                            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0557E));
                            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0557E);
                            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                            return false;
                        }
                    } else {
                        setValue(sMsgLine.mdseCd_A, tMsg.mdseCd);
                    }
                } else {
                    setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0011E, new String[]{SER_NUM}));
                    sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0011E, new String[]{SER_NUM});
                    setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                    return false;
                }
            }
        }
        // mod end 2016/11/28 CSA Defect#12077
        // END 2016/07/22 M.Gotou [QC#12077, MOD]

        setTpCdAndDplyCtrlFlg(cMsg.glblCmpyCd.getValue(), sMsgLine, asMsgArray, cMsg);

        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            if (hasValue(asMsgArray.no(i).dsXsCopyIntfcPk_A) && hasValue(sMsgLine.dsXsCopyIntfcPk_A) && sMsgLine.dsXsCopyIntfcPk_A.getValue().compareTo(asMsgArray.no(i).dsXsCopyIntfcPk_A.getValue()) == 0) {
                sMsgLine.dsXsCopyIntfcPk_A.clear();
                sMsgLine.ezUpTime.clear();
                sMsgLine.ezUpTimeZone.clear();
                break;
            }
        }
        // MOD end 2016/04/19 CSA Defect#6691

        return true;
    }

    // START 2016/07/22 M.Gotou [QC#12077, DEL]
//    private BigDecimal getIbId(String glblCmpyCd, String serNum) {
//        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
//        inMsg.setSQLID("002");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("serNum01", serNum);
//        SVC_MACH_MSTRTMsgArray tMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//        if (tMsgArray == null) {
//            return null;
//        }
//        return tMsgArray.no(0).svcMachMstrPk.getValue();
//    }
    // END 2016/07/22 M.Gotou [QC#12077, DEL]

    // START 2016/08/09 Y.Takeno [QC#11848, MOD]
    private String getMtrLbCd(String glblCmpyCd, String mtrLbDescTxt, String mtrLbTp) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrLbDescTxt01", mtrLbDescTxt);
        MTR_LBTMsgArray tMsgArray = (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return null;
        }
        return tMsgArray.no(0).mtrLbCd.getValue();
    }

    private MTR_LBTMsg getMtrLbDescTxt(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg mtrLb = new MTR_LBTMsg();
        setValue(mtrLb.glblCmpyCd, glblCmpyCd);
        setValue(mtrLb.mtrLbCd, mtrLbCd);
        return (MTR_LBTMsg) EZDTBLAccessor.findByKey(mtrLb);
    }
    // END 2016/08/09 Y.Takeno [QC#11848, MOD]

    // DELETE start 2016/04/19 CSA Defect#6691
//    private void setXsCopyIntfcData(String glblCmpyCd, NSAL0830_ASMsg sMsgLine, NSAL0830F00FMsg fMsg) {
//        if (hasValue(sMsgLine.dsContrIntfcBatNum_A) && hasValue(sMsgLine.dsContrSrcRefNum_A) && hasValue(sMsgLine.contrIntfcSrcTpCd_A)) {
//            if (hasValue(sMsgLine.serNum_A) && hasValue(sMsgLine.mdseCd_A) && hasValue(sMsgLine.bllgMtrLbCd_A)) {
//                S21SsmEZDResult ssmResult = NSAL0830Query.getInstance().getDsXsCopyIntfcPk(glblCmpyCd, sMsgLine);
//                if (ssmResult.isCodeNormal()) {
//                    Map<String, Object> rs = (Map<String, Object>) ssmResult.getResultObject();
//                    setValue(sMsgLine.dsContrNum_A, (String) rs.get("DS_CONTR_NUM"));
//                    setValue(sMsgLine.dsContrIntfcStsCd_A, (String) rs.get("DS_CONTR_INTFC_STS_CD"));
//                    setValue(sMsgLine.intfcErrMsgTxt_A, (String) rs.get("INTFC_ERR_MSG_TXT"));
//                    setValue(sMsgLine.dsContrProcStsDescTxt_A, (String) rs.get("DS_CONTR_PROC_STS_DESC_TXT"));
//                    setValue(sMsgLine.dsContrProcStsCd_A, (String) rs.get("DS_CONTR_PROC_STS_CD"));
//                    setValue(sMsgLine.dsContrIntfcPk_A, (BigDecimal) rs.get("DS_CONTR_INTFC_PK"));
//                    setValue(sMsgLine.dsXsCopyIntfcPk_A, (BigDecimal) rs.get("DS_XS_COPY_INTFC_PK"));
//                    setValue(sMsgLine.xxTpCd_A, XX_TP_CD_D);
//                    setValue(sMsgLine.ezUpTime, (String) rs.get("EZUPTIME"));
//                    setValue(sMsgLine.ezUpTimeZone, (String) rs.get("EZUPTIMEZONE"));
//                }
//            }
//        }
//    }

//    private void setContrIntfcPk(String glblCmpyCd, NSAL0830_ASMsg sMsgLine, NSAL0830F00FMsg fMsg) {
//        if (hasValue(sMsgLine.dsContrIntfcBatNum_A) && hasValue(sMsgLine.dsContrSrcRefNum_A) && hasValue(sMsgLine.contrIntfcSrcTpCd_A) && hasValue(sMsgLine.bllgMtrLbCd_A)) {
//
//            S21SsmEZDResult ssmResult = NSAL0830Query.getInstance().getDsContrIntfcPk(glblCmpyCd, sMsgLine);
//            if (ssmResult.isCodeNormal()) {
//                Map<String, Object> rs = (Map<String, Object>) ssmResult.getResultObject();
//                setValue(sMsgLine.dsContrNum_A, (String) rs.get("DS_CONTR_NUM"));
//                setValue(sMsgLine.dsContrIntfcStsCd_A, (String) rs.get("DS_CONTR_INTFC_STS_CD"));
//                setValue(sMsgLine.intfcErrMsgTxt_A, (String) rs.get("INTFC_ERR_MSG_TXT"));
//                setValue(sMsgLine.dsContrProcStsDescTxt_A, (String) rs.get("DS_CONTR_PROC_STS_DESC_TXT"));
//                setValue(sMsgLine.dsContrProcStsCd_A, (String) rs.get("DS_CONTR_PROC_STS_CD"));
//                setValue(sMsgLine.dsContrIntfcPk_A, (BigDecimal) rs.get("DS_CONTR_INTFC_PK"));
//                setValue(sMsgLine.xxTpCd_A, XX_TP_CD_D);
//            }
//        }
//    }
    // DELETE end 2016/04/19 CSA Defect#6691

    // ADD start 2016/04/19 CSA Defect#6691
    private void setTpCdAndDplyCtrlFlg(String glblCmpyCd, NSAL0830_ASMsg sMsgLine, NSAL0830_ASMsgArray asMsgArray, NSAL0830CMsg cMsg) {
        if (hasValue(sMsgLine.dsContrIntfcBatNum_A) && hasValue(sMsgLine.dsContrSrcRefNum_A) && hasValue(sMsgLine.contrIntfcSrcTpCd_A) && hasValue(sMsgLine.bllgMtrLbCd_A)) {
            S21SsmEZDResult ssmResultPrnt = NSAL0830Query.getInstance().getDsContrIntfcPk(glblCmpyCd, sMsgLine);
            Map<String, Object> rsPrnt = (Map<String, Object>) ssmResultPrnt.getResultObject();

            if (rsPrnt != null) {
                setValue(sMsgLine.dsContrNum_A, (String) rsPrnt.get("DS_CONTR_NUM"));
                // START 2016/08/09 Y.Takeno [QC#11848, ADD]
                setValue(sMsgLine.dsContrIntfcActDescTxt_A, (String) rsPrnt.get("DS_CONTR_INTFC_ACT_DESC_TXT"));
                // END 2016/08/09 Y.Takeno [QC#11848, ADD]
                setValue(sMsgLine.dsContrIntfcStsCd_A, (String) rsPrnt.get("DS_CONTR_INTFC_STS_CD"));
                setValue(sMsgLine.intfcErrMsgTxt_A, (String) rsPrnt.get("INTFC_ERR_MSG_TXT"));
                setValue(sMsgLine.dsContrProcStsDescTxt_A, (String) rsPrnt.get("DS_CONTR_PROC_STS_DESC_TXT"));
                setValue(sMsgLine.dsContrProcStsCd_A, (String) rsPrnt.get("DS_CONTR_PROC_STS_CD"));
                setValue(sMsgLine.dsContrIntfcPk_A, (BigDecimal) rsPrnt.get("DS_CONTR_INTFC_PK"));
                setValue(sMsgLine.ezUpTime, (String) rsPrnt.get("EZUPTIME"));
                setValue(sMsgLine.ezUpTimeZone, (String) rsPrnt.get("EZUPTIMEZONE"));
                // mod start 2016/08/22 CSA QC#11862
                if (hasValue(sMsgLine.xsMtrCopyQty_A)) {
                    S21SsmEZDResult ssmResult = NSAL0830Query.getInstance().getXsCopyIntfcData(glblCmpyCd, sMsgLine);
                    Map<String, Object> rs = (Map<String, Object>) ssmResult.getResultObject();
                // mod end 2016/08/22 CSA QC#11862
                    if (rs != null) {
                        setValue(sMsgLine.dsXsCopyIntfcPk_A, (BigDecimal) rs.get("DS_XS_COPY_INTFC_PK"));
                        setValue(sMsgLine.ezUpTime, (String) rs.get("EZUPTIME"));
                        setValue(sMsgLine.ezUpTimeZone, (String) rs.get("EZUPTIMEZONE"));
                    }
                }
                BigDecimal dsContrIntfcPk = sMsgLine.dsContrIntfcPk_A.getValue();

                if (!existTargetRow(asMsgArray, dsContrIntfcPk)) {
                    setValue(sMsgLine.xxDplyCtrlFlg_A, ZYPConstant.FLG_ON_Y);
                    if (!existTargetPrntXsMtrCopyQty((BigDecimal) rsPrnt.get("XS_MTR_COPY_QTY"), sMsgLine.xsMtrCopyQty_A.getValue())) {
                        setValue(sMsgLine.xxTpCd_A, XX_TP_CD_D);
                    } else {
                        setValue(sMsgLine.xxTpCd_A, XX_TP_CD_H);
                    }
                } else {
                    setValue(sMsgLine.xxTpCd_A, XX_TP_CD_D);
                    setValue(sMsgLine.xxDplyCtrlFlg_A, ZYPConstant.FLG_OFF_N);
                }
            } else {
                // START 2016/07/22 M.Gotou [QC#11853,11854, MOD]
                setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0554E));
                sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0554E);
                setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                // END 2016/07/22 M.Gotou [QC#11853,11854, MOD]
            }
        }
    }

    private boolean existTargetRow(NSAL0830_ASMsgArray asMsgArray, BigDecimal dsContrIntfcPk) {
        boolean flg = false;
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            if (dsContrIntfcPk.compareTo(asMsgArray.no(i).dsContrIntfcPk_A.getValue()) == 0) {
                flg = true;
                break;
            }
        }
        return flg;
    }

    private boolean existTargetPrntXsMtrCopyQty(BigDecimal prntXsMtrCopyQty, BigDecimal targetXsMtrCopyQty) {
        boolean flg = false;
        if (hasValue(prntXsMtrCopyQty) && hasValue(targetXsMtrCopyQty)) {
            if (prntXsMtrCopyQty.compareTo(targetXsMtrCopyQty) == 0) {
                flg = true;
            }
        } else if (!hasValue(prntXsMtrCopyQty) && !hasValue(targetXsMtrCopyQty)) {
            flg = true;
        }
        return flg;
    }
    // ADD end 2016/04/19 CSA Defect#6691

    // START 2016/07/28 M.Gotou [QC#12141, ADD]
    private void doProcess_NSAL0830Scrn00_AddRow(NSAL0830CMsg cMsg, NSAL0830SMsg sMsg) {

        if (sMsg.A.getValidCount() == sMsg.A.length()) {
            cMsg.setMessageInfo(NSAM0214E);
            return;
        }

        for (int idx = 0; idx < cMsg.A.getValidCount(); idx++) {
            int sMsgIdx = idx + cMsg.xxPageShowFromNum.getValueInt() - 1;
            if (sMsgIdx < sMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.no(idx), null, sMsg.A.no(sMsgIdx), null);
            }
        }

        int vldCnt = sMsg.A.getValidCount();
        setValue(sMsg.A.no(vldCnt).dsContrIntfcBatNum_A, cMsg.dsContrIntfcBatNum_S);
        S21SsmEZDResult ssmResult = NSAL0830Query.getInstance().getDsContrIntfcActDescTxt(cMsg, sMsg);
        String dsContrIntfcActDescTxt = (String) ssmResult.getResultObject();
        setValue(sMsg.A.no(vldCnt).dsContrIntfcActDescTxt_A, dsContrIntfcActDescTxt);
        setValue(sMsg.A.no(vldCnt).xxRowNum_A, BigDecimal.valueOf(vldCnt + 1));
        sMsg.A.setValidCount(vldCnt + 1);

        NSAL0830CommonLogic.pagenation(cMsg, sMsg, vldCnt);

    }
    // END 2016/07/28 M.Gotou [QC#12141, ADD]
}
