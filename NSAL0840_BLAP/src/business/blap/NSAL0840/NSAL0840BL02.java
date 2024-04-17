/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0840;

import static business.blap.NSAL0840.constant.NSAL0840Constant.*;
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
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import business.blap.NSAL0840.common.NSAL0840CommonLogic;
import business.blap.NSAL0840.NSAL0840Query;
import business.db.DS_ADDL_CHRG_INTFCTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.file.NSAL0840F00FMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsAddlChrgIntfc;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CHRG_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
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
 * 2016/01/14   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/25   Hitachi         T.Iwamoto       Create          QC#5542
 * 2016/03/30   Hitachi         T.Iwamoto       Update          QC#5541
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/07/22   Hitachi         M.Gotou         Update          QC#11853,11854,12077
 * 2016/07/22   Hitachi         Y.Takeno        Update          QC#12086
 * 2016/07/25   Hitachi         Y.Takeno        Update          QC#11829
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#12141
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#11847,12075
 * 2016/09/08   Hitachi         T.Tomita        Update          QC#12082
 * 2016/09/26   Hitachi         K.Kishimoto     Update          QC#14428
 * 2016/10/28   Hitachi         T.Tomita        Update          QC#15468
 * 2017/05/26   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/09/15   Hitachi         A.Kohinata      Update          QC#19775
 *</pre>
 */
public class NSAL0840BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0840_INIT".equals(screenAplID)) {
                doProcess_NSAL0840_INIT((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
                ZYPGUITableColumn.getColData((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_Search((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_PageJump((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_PageNext((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_PagePrev((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_Reset((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_Search((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_SelectAll((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_UnselectAll".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_UnselectAll((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_TBLColumnSort((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_Download((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_TemplateDownload((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_Upload((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_ValidateData".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_ValidateData((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_CMN_Delete((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
            } else if ("NSAL0840Scrn00_CMN_ColClear".equals(screenAplID)) {
                return;
            } else if ("NSAL0840Scrn00_CMN_ColSave".equals(screenAplID)) {
                return;
            // START 2016/07/28 M.Gotou [QC#12141, ADD]
            } else if ("NSAL0840Scrn00_AddRow".equals(screenAplID)) {
                doProcess_NSAL0840Scrn00_AddRow((NSAL0840CMsg) cMsg, (NSAL0840SMsg) sMsg);
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
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840_INIT(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

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
        ZYPCodeDataUtil.createPulldownList(CHRG_LVL_TP.class, cMsg.chrgLvlTpCd_AL, cMsg.chrgLvlTpDescTxt_AL);

        // Mod Start 2016/09/26 <QC#14428>
//        ZYPCodeDataUtil.createPulldownList(ADDL_CHRG_TP.class, cMsg.addlChrgTpCd_AT, cMsg.addlChrgTpDescTxt_AT);
        List<Map<String, Object>> AddlChrgTpMapList = NSAL0840Query.getInstance().getAddlChrgTpV();
        int addlChrgTpIdx = 0;
        for (Map<String, Object> AddlChrgTpMap : AddlChrgTpMapList) {
            if (addlChrgTpIdx >= cMsg.addlChrgTpCd_AT.length()) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(cMsg.addlChrgTpCd_AT.no(addlChrgTpIdx), (String) AddlChrgTpMap.get("ADDL_CHRG_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.addlChrgTpDescTxt_AT.no(addlChrgTpIdx), (String) AddlChrgTpMap.get("ADDL_CHRG_TP_DESC_TXT"));
            addlChrgTpIdx++;
        }
      // Mod End   2016/09/26 <QC#14428>
        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.class, cMsg.bllgCycleCd_AB, cMsg.bllgCycleDescTxt_AB);
        // START 2017/05/26 Y.Osawa [QC#18560, ADD]
        NSAL0840CommonLogic.deletePulldownList(cMsg.bllgCycleCd_AB, cMsg.bllgCycleDescTxt_AB, BLLG_CYCLE.DAILY);
        // END   2017/05/26 Y.Osawa [QC#18560, ADD]
        ZYPCodeDataUtil.createPulldownList(ADDL_CHRG_INV_TP.class, cMsg.addlChrgInvTpCd_AI, cMsg.addlChrgInvTpDescTxt_AI);

        getSearchData(cMsg, sMsg);

        // START 2016/07/28 M.Gotou [QC#12141, ADD]
        S21SsmEZDResult ssmResult = NSAL0840Query.getInstance().countCpltData(cMsg, sMsg);
        BigDecimal count = (BigDecimal) ssmResult.getResultObject();
        setValue(cMsg.xxExstFlg, ZYPConstant.FLG_OFF_N);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            setValue(cMsg.xxExstFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2016/07/28 M.Gotou [QC#12141, ADD]
    }

    /**
     * do process (Page Jump)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_PageJump(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
        int rowIndex = NSAL0840CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0840CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_PageNext(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
        int rowIndex = NSAL0840CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() + 1);
        NSAL0840CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_PagePrev(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
        int rowIndex = NSAL0840CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() - 1);
        NSAL0840CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Reset)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_Reset(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        doProcess_NSAL0840_INIT(cMsg, sMsg);
        setValue(cMsg.xxComnColOrdTxt, xxComnColOrdTxt);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * do process (Search)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_Search(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

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
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_TBLColumnSort(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        NSAL0840CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

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
            NSAL0840CommonLogic.pagenation(cMsg, sMsg, 0);
        }
    }

    /**
     * do process (download)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_Download(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0840Query query = NSAL0840Query.getInstance();
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
            NSAL0840F00FMsg fMsg = new NSAL0840F00FMsg();
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
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_TemplateDownload(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        // create csv file
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL0840F00FMsg fMsg = new NSAL0840F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }

    /**
     * do process (SelectAll)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_SelectAll(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        NSAL0840CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (MODE_AFTER_OPEN.equals(cMsg.xxModeCd.getValue()) && !DS_CONTR_PROC_STS.COMPLEATED.equals(sMsg.A.no(i).dsContrProcStsCd_A.getValue())) {
                setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
            }
        }
        int rowIndex = NSAL0840CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0840CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (UnselectAll)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_UnselectAll(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        NSAL0840CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A.clear();
        }
        int rowIndex = NSAL0840CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0840CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Upload)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_Upload(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        // START 2016/09/08 T.Tomita [QC#12082, ADD]
        NSAL0840CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        // END 2016/09/08 T.Tomita [QC#12082, ADD]

        // START 2016/05/20 [QC#4061, MOD]
        String excelPath = cMsg.xxFileData_U.getTempFilePath();
        String path = ZYPExcelUtil.excelToCsvFile(excelPath);
        // END   2016/05/20 [QC#4061, MOD]

        NSAL0840F00FMsg fMsg = new NSAL0840F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);
        try {
            int header = mappedFile.read();
            if (header == 1) {
                cMsg.setMessageInfo(ZYEM0004E);
            }
            int status = -1;
            int count = sMsg.A.getValidCount();

            // do basic check and load to screen(for all csv data)
            while ((status = mappedFile.read()) != 1) {
                count++;
                List<String> msgKeys = new ArrayList<String>();
                List<List<String>> msgArgs = new ArrayList<List<String>>();
                List<EZDSItem> msgItems = new ArrayList<EZDSItem>();

                if (!validateAndCopyToSMsg_UPLOAD(status, count, sMsg.A, fMsg, cMsg, msgKeys, msgArgs, msgItems)) {
                    // if csvFile data over 200 ->return ErrorMessage
                    if (NSAM0214E.equals(cMsg.getMessageCode())) {
                        // START 2016/07/22 [QC#12086, ADD]
                        ZYPTableUtil.clear(sMsg.A);
                        sMsg.A.setValidCount(0);
                        // END   2016/07/22 [QC#12086, ADD]
                        return;
                    }
                }
                fMsg.clear();
                // MOD start 2016/04/22 CSA Defect#6691
                sMsg.A.setValidCount(count);
                // MOD end 2016/04/22 CSA Defect#6691
            }

            // validate Additional Charge Interface Table
            List<DS_ADDL_CHRG_INTFCTMsg> dsAddlChrgIntfcTMsgList = NSAL0840CommonLogic.setDsAddlChrgIntfc(cMsg, sMsg);
            NSXC001001ValidationDsAddlChrgIntfc.validationDsAddlChrgIntfc(dsAddlChrgIntfcTMsgList);
            NSAL0840CommonLogic.updateValidationResult(cMsg, sMsg, dsAddlChrgIntfcTMsgList, false);

            // ADD start 2016/04/22 CSA Defect#6691
            // commit all record including error record
            cMsg.setCommitSMsg(true);
            // ADD end 2016/04/22 CSA Defect#6691

            if (!hasValue(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(NZZM0002I);
            }

        } finally {
            // set Paging Data
            NSAL0840CommonLogic.pagenation(cMsg, sMsg, 0);

            mappedFile.close();
            cMsg.xxFileData_U.deleteTempFile();
            // START 2016/05/20 [QC#4061, ADD]
            ZYPExcelUtil.deleteFile(path);
            // END   2016/05/20 [QC#4061, ADD]
        }
    }

    /**
     * do process (ValidateData)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_ValidateData(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
        int rowIndex = NSAL0840CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0840CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (CMN_Delete)
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     */
    private void doProcess_NSAL0840Scrn00_CMN_Delete(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
        int rowIndex = NSAL0840CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0840CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * get Search Data List
     * @param cMsg NSAL0840CMsg
     * @return Data List
     */
    private void getSearchData(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

        sMsg.clear();
        S21SsmEZDResult ssmResult = NSAL0840Query.getInstance().getSearchData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
            }

            // set Paging Data
            NSAL0840CommonLogic.pagenation(cMsg, sMsg, 0);

            // 
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
    private boolean isAllwDataCrctFlg(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
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
     * @param bizMsg NSAL0840CMsg
     * @param globalMsg NSAL0840SMsg
     * @param rs ResultSet
     * @param fMsg NSAL0840F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForHeaderTab(NSAL0840CMsg bizMsg, NSAL0840SMsg globalMsg, ResultSet rs, NSAL0840F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

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
            setValue(fMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
            setValue(fMsg.dsContrIntfcActDescTxt, rs.getString("DS_CONTR_INTFC_ACT_DESC_TXT"));
            setValue(fMsg.serNum, rs.getString("SER_NUM"));
            setValue(fMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
            setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
            setValue(fMsg.chrgLvlTpCd, rs.getString("CHRG_LVL_TP_CD"));
            setValue(fMsg.addlChrgTpCd, rs.getString("ADDL_CHRG_TP_CD"));
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            setValue(fMsg.effFromDt, rs.getString("EFF_FROM_DT"));
//            setValue(fMsg.effThruDt, rs.getString("EFF_THRU_DT"));
            setValue(fMsg.contrEffFromDtTxt, convertDateFormat(rs.getString("EFF_FROM_DT")));
            setValue(fMsg.contrEffThruDtTxt, convertDateFormat(rs.getString("EFF_THRU_DT")));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]
            setValue(fMsg.bllgCycleCd, rs.getString("BLLG_CYCLE_CD"));
            setValue(fMsg.addlChrgFlatDealPrcAmt, rs.getBigDecimal("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
            setValue(fMsg.addlChrgAplcPct, rs.getBigDecimal("ADDL_CHRG_APLC_PCT"));
            setValue(fMsg.addlChrgInvTpCd, rs.getString("ADDL_CHRG_INV_TP_CD"));
            setValue(fMsg.printDtlFlg, rs.getString("PRINT_DTL_FLG"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * setDownloadPram
     * @param cMsg NSAL0840CMsg
     * @param sMsg NSAL0840SMsg
     * @return S21SsmEZDResult
     */
    private Map<String, Object> setDownloadPram(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {
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
        params.put("base", CONTR_INTFC_DTL_TP.BASE);
        params.put("limitNum", LIMIT_DOWNLOAD);

        return params;
    }

    /**
     * @param writeCsvFileHeader
     * @param fMsg
     * @param cMsg
     */
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0840F00FMsg fMsg, NSAL0840CMsg cMsg) {
        final String[] csvHeader = new String[] {"Interface Bat#", "Source Ref#", "Source Type", "Contract#", "Action", "Serial#",
                "IB ID", "Mdse Code", "Charge Level", "Charge Type", "Start Date", "End Date", "Frequency", "Flat Rate", "Applicable%", "Invoice Type", "Print on Invoice" };

        csvOutFile.writeHeader(csvHeader);
    }

    private boolean validateAndCopyToSMsg_UPLOAD(int status, int count, NSAL0840_ASMsgArray asMsgArray, NSAL0840F00FMsg fMsg, NSAL0840CMsg cMsg, List<String> msgKeys, List<List<String>> msgArgs, List<EZDSItem> msgItems) {

        if (count > asMsgArray.length()) {
            cMsg.setMessageInfo(NSAM0214E);
            return false;
        }
        int row = count - 1;
        NSAL0840_ASMsg sMsgLine = asMsgArray.no(row);
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

        // MOD start 2016/04/20 CSA Defect#6691
        if (!hasValue(fMsg.dsContrIntfcBatNum)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);

        } else {
            setValue(sMsgLine.dsContrIntfcBatNum_A, fMsg.dsContrIntfcBatNum);
        }

        if (!hasValue(fMsg.dsContrSrcRefNum)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);

        } else {
            setValue(sMsgLine.dsContrSrcRefNum_A, fMsg.dsContrSrcRefNum);
        }
        if (!hasValue(fMsg.contrIntfcSrcTpCd)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);

        } else {
            setValue(sMsgLine.contrIntfcSrcTpCd_A, fMsg.contrIntfcSrcTpCd);
        }
        // MOD end 2016/04/20 CSA Defect#6691

        // MOD start 2016/04/20 CSA Defect#6691
        if (!hasValue(fMsg.chrgLvlTpCd)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {CHRG_LVL_TP_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {CHRG_LVL_TP_CD});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);

        } else {
            setValue(sMsgLine.chrgLvlTpCd_A, fMsg.chrgLvlTpCd);
        }
        if (!hasValue(fMsg.addlChrgTpCd)) {
            // START 2016/07/22 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {ADDL_CHRG_TP_CD}));
            // END 2016/07/22 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {ADDL_CHRG_TP_CD});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);

        } else {
            setValue(sMsgLine.addlChrgTpCd_A, fMsg.addlChrgTpCd);
        }
        // MOD end 2016/04/20 CSA Defect#6691

        // START 2016/10/28 T.Tomita [QC#15468, MOD]
//        setValue(sMsgLine.effFromDt_A, fMsg.effFromDt);
//        setValue(sMsgLine.effThruDt_A, fMsg.effThruDt);
        setValue(sMsgLine.effFromDt_A, decodeDateFormat(fMsg.contrEffFromDtTxt.getValue()));
        setValue(sMsgLine.effThruDt_A, decodeDateFormat(fMsg.contrEffThruDtTxt.getValue()));
        // END 2016/10/28 T.Tomita [QC#15468, MOD]
        setValue(sMsgLine.bllgCycleCd_A, fMsg.bllgCycleCd);
        setValue(sMsgLine.addlChrgFlatDealPrcAmt_A, fMsg.addlChrgFlatDealPrcAmt);
        setValue(sMsgLine.addlChrgAplcPct_A, fMsg.addlChrgAplcPct);
        setValue(sMsgLine.addlChrgInvTpCd_A, fMsg.addlChrgInvTpCd);
        setValue(sMsgLine.printDtlFlg_A, fMsg.printDtlFlg);

     // START 2016/07/22 M.Gotou [QC#12077, MOD]
//      setValue(sMsgLine.serNum_A, fMsg.serNum);
//      if (!hasValue(fMsg.svcMachMstrPk) && hasValue(fMsg.serNum)) {
//          setValue(sMsgLine.svcMachMstrPk_A, NSAL0840CommonLogic.getIbId(cMsg.glblCmpyCd.getValue(), fMsg.serNum.getValue()));
//      } else {
//          setValue(sMsgLine.svcMachMstrPk_A, fMsg.svcMachMstrPk);
//      }
//      setValue(sMsgLine.mdseCd_A, fMsg.mdseCd);
        // mod start 2016/11/28 CSA Defect#12077
        if (hasValue(fMsg.mdseCd)) {
            setValue(sMsgLine.mdseCd_A, fMsg.mdseCd);
        }
        if (hasValue(fMsg.svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg tMsg = NSAL0840CommonLogic.getSvcMachMstr(cMsg.glblCmpyCd.getValue(), fMsg.svcMachMstrPk.getValue());
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
                    if (tMsg.mdseCd.getValue().equals(fMsg.mdseCd.getValue())) {
                        setValue(sMsgLine.mdseCd_A, fMsg.mdseCd);
                    } else {
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
                SVC_MACH_MSTRTMsg tMsg = NSAL0840CommonLogic.getSvcMachMstrForSerNum(cMsg.glblCmpyCd.getValue(), fMsg.serNum.getValue());
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

        setDsAddlChrgIntfcData(cMsg.glblCmpyCd.getValue(), sMsgLine);

        // MOD start 2016/04/20 CSA Defect#6691
        // duplicate check (Clear duplicate dsActlCntIntfcPk)
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            if (hasValue(asMsgArray.no(i).dsAddlChrgIntfcPk_A) && hasValue(sMsgLine.dsAddlChrgIntfcPk_A) && sMsgLine.dsAddlChrgIntfcPk_A.getValue().compareTo(asMsgArray.no(i).dsAddlChrgIntfcPk_A.getValue()) == 0) {
                sMsgLine.dsAddlChrgIntfcPk_A.clear();
                sMsgLine.ezUpTime.clear();
                sMsgLine.ezUpTimeZone.clear();
                break;
            }
        }
        // MOD end 2016/04/20 CSA Defect#6691

        if (msgKeys.size() > 0) {
            return false;
        }
        return true;
    }

    // START 2016/07/22 M.Gotou [QC#11854, DEL]
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
    // END 2016/07/22 M.Gotou [QC#11854, DEL]

    // MOD start 2016/04/22 CSA Defect#6691
    // START 2016/07/28 M.Gotou [QC#12077, MOD]
    private void setDsAddlChrgIntfcData(String glblCmpyCd, NSAL0840_ASMsg sMsgLine) {
        if (hasValue(sMsgLine.dsContrIntfcBatNum_A) && hasValue(sMsgLine.dsContrSrcRefNum_A) && hasValue(sMsgLine.contrIntfcSrcTpCd_A) && hasValue(sMsgLine.chrgLvlTpCd_A) && hasValue(sMsgLine.addlChrgTpCd_A)) {
            S21SsmEZDResult ssmResultUpdate = NSAL0840Query.getInstance().getDsAddlChrgIntfc(glblCmpyCd, sMsgLine);
            @SuppressWarnings("unchecked")
            Map<String, Object> rsUpdate = (Map<String, Object>) ssmResultUpdate.getResultObject();
            if (rsUpdate != null) {
                setValue(sMsgLine.dsContrNum_A, (String) rsUpdate.get("DS_CONTR_NUM"));
                setValue(sMsgLine.dsContrIntfcStsCd_A, (String) rsUpdate.get("DS_CONTR_INTFC_STS_CD"));
                setValue(sMsgLine.intfcErrMsgTxt_A, (String) rsUpdate.get("INTFC_ERR_MSG_TXT"));
                setValue(sMsgLine.dsContrProcStsDescTxt_A, (String) rsUpdate.get("DS_CONTR_PROC_STS_DESC_TXT"));
                setValue(sMsgLine.dsContrProcStsCd_A, (String) rsUpdate.get("DS_CONTR_PROC_STS_CD"));
                setValue(sMsgLine.dsContrIntfcPk_A, (BigDecimal) rsUpdate.get("DS_CONTR_INTFC_PK"));
                setValue(sMsgLine.dsAddlChrgIntfcPk_A, (BigDecimal) rsUpdate.get("DS_ADDL_CHRG_INTFC_PK"));
                setValue(sMsgLine.ezUpTime, (String) rsUpdate.get("EZUPTIME"));
                setValue(sMsgLine.ezUpTimeZone, (String) rsUpdate.get("EZUPTIMEZONE"));
            } else {
                S21SsmEZDResult ssmResultInsert = NSAL0840Query.getInstance().getDsContrIntfcPk(glblCmpyCd, sMsgLine);
                Map<String, Object> rsInsert = (Map<String, Object>) ssmResultInsert.getResultObject();
                if (rsInsert != null){
                    setValue(sMsgLine.dsContrNum_A, (String) rsInsert.get("DS_CONTR_NUM"));
                    setValue(sMsgLine.dsContrProcStsCd_A, (String) rsInsert.get("DS_CONTR_PROC_STS_CD"));
                    setValue(sMsgLine.dsContrIntfcPk_A, (BigDecimal) rsInsert.get("DS_CONTR_INTFC_PK"));
                    setValue(sMsgLine.dsContrProcStsDescTxt_A, (String) rsInsert.get("DS_CONTR_PROC_STS_DESC_TXT"));
                    setValue(sMsgLine.dsContrIntfcActDescTxt_A, (String) rsInsert.get("DS_CONTR_INTFC_ACT_DESC_TXT"));
                } else {
                    // START 2016/07/22 M.Gotou [QC#11853,11854, MOD]
                    setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0555E));
                    sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0555E);
                    setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                    // END 2016/07/22 M.Gotou [QC#11853,11854, MOD]
                }
            }
        }
    }
    // END 2016/07/28 M.Gotou [QC#12077, MOD]
    // MOD end 2016/04/22 CSA Defect#6691

    // START 2016/07/28 M.Gotou [QC#12141, ADD]
    private void doProcess_NSAL0840Scrn00_AddRow(NSAL0840CMsg cMsg, NSAL0840SMsg sMsg) {

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
        S21SsmEZDResult ssmResult = NSAL0840Query.getInstance().getDsContrIntfcActDescTxt(cMsg, sMsg);
        String dsContrIntfcActDescTxt = (String) ssmResult.getResultObject();
        setValue(sMsg.A.no(vldCnt).dsContrIntfcActDescTxt_A, dsContrIntfcActDescTxt);
        setValue(sMsg.A.no(vldCnt).xxRowNum_A, BigDecimal.valueOf(vldCnt + 1));
        sMsg.A.setValidCount(vldCnt + 1);

        NSAL0840CommonLogic.pagenation(cMsg, sMsg, vldCnt);

    }
    // END 2016/07/28 M.Gotou [QC#12141, ADD]

    // START 2016/10/28 T.Tomita [QC#15468, ADD]
    private String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }

    private String decodeDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatSepDisp8ToEzd(date);
        }
        return date;
    }
    // END 2016/10/28 T.Tomita [QC#15468, ADD]
}
