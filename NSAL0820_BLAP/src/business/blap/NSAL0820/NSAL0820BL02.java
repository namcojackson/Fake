/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0820;

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
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0820.common.NSAL0820CommonLogic;
import business.db.DS_ACTL_CNT_INTFCTMsg;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.file.NSAL0820F00FMsg;
import business.parts.NSXC001001PMsg;
import static business.blap.NSAL0820.constant.NSAL0820Constant.*;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ValidationDsActlCntIntfc;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
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
 * Actual Counters for Interface
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         T.Iwamoto       Create          N/A
 * 2016/03/25   Hitachi         T.Iwamoto       Create          QC#5542
 * 2016/03/29   Hitachi         T.Iwamoto       Update          QC#5541
 * 2016/04/22   Hitachi         T.Iwamoto       Update          QC#6691
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/05/25   Hitachi         T.Tomita        Update          QC#8898
 * 2016/07/14   Hitachi         M.Gotou         Update          QC#11853
 * 2016/07/19   Hitachi         M.Gotou         Update          QC#11854
 * 2016/07/20   Hitachi         Y.Takeno        Update          QC#12086
 * 2016/07/25   Hitachi         Y.Takeno        Update          QC#11829
 * 2016/07/27   Hitachi         M.Gotou         Update          QC#12141
 * 2016/07/28   Hitachi         M.Gotou         Update          QC#11847,12075,12077
 * 2016/08/02   Hitachi         Y.Takeno        Update          QC#11831,11848
 * 2016/08/26   Hitachi         T.Mizuki        Update          QC#12077
 * 2016/11/28   Hitachi         T.Tomita        Update          QC#12077
 * 2017/09/15   Hitachi         A.Kohinata      Update          QC#19775
 *</pre>
 */
public class NSAL0820BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0820_INIT".equals(screenAplID)) {
                doProcess_NSAL0820_INIT((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
                // add start 2016/03/21 CSA Defect#3218
                ZYPGUITableColumn.getColData((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
                // add end 2016/03/21 CSA Defect#3218
            } else if ("NSAL0820Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_Search((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_PageJump((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_PageNext((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_PagePrev((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_Reset((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_Search((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_SelectAll".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_SelectAll((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_UnselectAll".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_UnselectAll((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_TBLColumnSort((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_Download((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_TemplateDownload".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_TemplateDownload((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_Upload".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_Upload((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_ValidateData".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_ValidateData((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_CMN_Delete((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            } else if ("NSAL0820Scrn00_CMN_ColClear".equals(screenAplID)) {
                return;
            } else if ("NSAL0820Scrn00_CMN_ColSave".equals(screenAplID)) {
                return;
            // START 2016/07/27 M.Gotou [QC#12141, ADD]
            } else if ("NSAL0820Scrn00_AddRow".equals(screenAplID)) {
                doProcess_NSAL0820Scrn00_AddRow((NSAL0820CMsg) cMsg, (NSAL0820SMsg) sMsg);
            // END 2016/07/27 M.Gotou [QC#12141, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Init)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820_INIT(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        String dsContrIntfcBatNum = cMsg.dsContrIntfcBatNum_S.getValue();
        // ADD start 2016/03/29 CSA Defect#5541
        String dsContrSrcRefNum = cMsg.dsContrSrcRefNum_S.getValue();
        // ADD end 2016/03/29 CSA Defect#5541
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
        // ADD start 2016/03/29 CSA Defect#5541
        setValue(cMsg.dsContrSrcRefNum_S, dsContrSrcRefNum);
        setValue(cMsg.dsContrIntfcBatNum_S2, cMsg.dsContrIntfcBatNum_S);
        setValue(cMsg.dsContrSrcRefNum_S2, cMsg.dsContrSrcRefNum_S);
        // ADD end 2016/03/29 CSA Defect#5541
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
        // START 2016/05/25 T.Tomita [QC#8898, DEL]
//        createMtrLbPulldownList(cMsg, MTR_LB_TP.REGULAR_METER);
//        createMtrLbPulldownList(cMsg, MTR_LB_TP.BILLING_METER);
        // END 2016/05/25 T.Tomita [QC#8898, DEL]

        // search
        getSearchData(cMsg, sMsg);

        // START 2016/07/27 M.Gotou [QC#12141, ADD]
        S21SsmEZDResult ssmResult = NSAL0820Query.getInstance().countCpltData(cMsg, sMsg);
        BigDecimal count = (BigDecimal) ssmResult.getResultObject();
        setValue(cMsg.xxExstFlg, ZYPConstant.FLG_OFF_N);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            setValue(cMsg.xxExstFlg, ZYPConstant.FLG_ON_Y);
        }
        // END 2016/07/27 M.Gotou [QC#12141, ADD]
    }

    // START 2016/05/25 T.Tomita [QC#8898, DEL]
//    private static void createMtrLbPulldownList(NSAL0820CMsg cMsg, String mtrLbTp) {
//        MTR_LBTMsgArray tMsgAry = getMtrLbList(cMsg.glblCmpyCd.getValue(), mtrLbTp);
//        Map<String, String> tMsgKeys = new HashMap<String, String>();
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "mtrLbCd");
//        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "mtrLbDescTxt");
//        if (MTR_LB_TP.REGULAR_METER.equals(mtrLbTp)) {
//            ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.mtrLbCd_AP, cMsg.mtrLbDescTxt_AP);
//        } else {
//            ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.mtrLbCd_AB, cMsg.mtrLbDescTxt_AB);
//        }
//    }
//
//    private static MTR_LBTMsgArray getMtrLbList(String glblCmpyCd, String mtrLbTp) {
//        MTR_LBTMsg inMsg = new MTR_LBTMsg();
//        inMsg.setSQLID("003");
//        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        inMsg.setConditionValue("mtrLbTpCd01", mtrLbTp);
//        return (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//    }
    // END 2016/05/25 T.Tomita [QC#8898, DEL]

    /**
     * do process (Page Jump)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_PageJump(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Page Next)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_PageNext(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() + 1);
        NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Page Prev)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_PagePrev(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt() - 1);
        NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Reset)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_Reset(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        doProcess_NSAL0820_INIT(cMsg, sMsg);

        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    /**
     * do process (Search)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_Search(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

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
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_TBLColumnSort(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        NSAL0820CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

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
            NSAL0820CommonLogic.pagenation(cMsg, sMsg, 0);
        }
    }

    /**
     * do process (download)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_Download(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0820Query nsal0450Query = NSAL0820Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(nsal0450Query.getClass());

            // create csv file
            cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
            Map<String, Object> params = setDownloadPram(cMsg, sMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("getMainData", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NZZM0000E, null);
                return;
            }
            NSAL0820F00FMsg fMsg = new NSAL0820F00FMsg();
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
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_TemplateDownload(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        // create csv file
        cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(BUSINESS_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
        NSAL0820F00FMsg fMsg = new NSAL0820F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);
        csvOutFile.close();
    }

    /**
     * do process (SelectAll)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_SelectAll(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        NSAL0820CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (MODE_AFTER_OPEN.equals(cMsg.xxModeCd.getValue()) && !DS_CONTR_PROC_STS.COMPLEATED.equals(sMsg.A.no(i).dsContrProcStsCd_A.getValue())) {
                setValue(sMsg.A.no(i).xxChkBox_A, ZYPConstant.CHKBOX_ON_Y);
            }
        }
        int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (UnselectAll)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_UnselectAll(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        NSAL0820CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            sMsg.A.no(i).xxChkBox_A.clear();
        }
        int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (Upload)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_Upload(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        // START 2016/05/20 [QC#4061, MOD]
        String excelPath = cMsg.xxFileData_U.getTempFilePath();
        String path = ZYPExcelUtil.excelToCsvFile(excelPath);
        // END   2016/05/20 [QC#4061, MOD]

        NSAL0820F00FMsg fMsg = new NSAL0820F00FMsg();
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
                        // START 2016/07/20 [QC#12086, ADD]
                        ZYPTableUtil.clear(sMsg.A);
                        sMsg.A.setValidCount(0);
                        // END   2016/07/20 [QC#12086, ADD]
                        return;
                    }
                }
                fMsg.clear();
                // MOD start 2016/04/22 CSA Defect#6691
                sMsg.A.setValidCount(count);
                // MOD end 2016/04/22 CSA Defect#6691
            }

            // Validation Actual Counter Interface Table
            List<DS_ACTL_CNT_INTFCTMsg> dsActlCntIntfcTMsgList = NSAL0820CommonLogic.setDsActlCntIntfc(cMsg, sMsg);
            NSXC001001PMsg prmPmsg = new NSXC001001PMsg();
            NSXC001001ValidationDsActlCntIntfc.validationDsActlCntIntfc(prmPmsg, dsActlCntIntfcTMsgList);
            NSAL0820CommonLogic.updateValidationResult(cMsg, sMsg, dsActlCntIntfcTMsgList, false);

            // ADD start 2016/04/22 CSA Defect#6691
            // commit all record including error record
            cMsg.setCommitSMsg(true);
            // ADD end 2016/04/22 CSA Defect#6691

            if (!hasValue(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(NZZM0002I);
            }

        } finally {
            // set Paging Data
            NSAL0820CommonLogic.pagenation(cMsg, sMsg, 0);

            mappedFile.close();
            cMsg.xxFileData_U.deleteTempFile();
            // START 2016/05/20 [QC#4061, ADD]
            ZYPExcelUtil.deleteFile(path);
            // END   2016/05/20 [QC#4061, ADD]
        }
    }

    /**
     * do process (ValidateData)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_ValidateData(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    /**
     * do process (CMN_Delete)
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     */
    private void doProcess_NSAL0820Scrn00_CMN_Delete(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
        int rowIndex = NSAL0820CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NSAL0820CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private boolean validateAndCopyToSMsg_UPLOAD(int status, int count, NSAL0820_ASMsgArray asMsgArray, NSAL0820F00FMsg fMsg, NSAL0820CMsg cMsg, List<String> msgKeys, List<List<String>> msgArgs, List<EZDSItem> msgItems) {
        if (count > asMsgArray.length()) {
            cMsg.setMessageInfo(NSAM0214E);
            return false;
        }
        int row = count - 1;
        NSAL0820_ASMsg sMsgLine = asMsgArray.no(row);
        setValue(sMsgLine.xxRowNum_A, BigDecimal.valueOf(count));

        if (status == CSV_READ_STATUS_CODE_1000) {
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0208E));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0208E);
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        } else if (status > CSV_READ_STATUS_CODE_1000 && status < CSV_READ_STATUS_CODE_2000) {
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0209E));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0209E);
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        } else if (status >= CSV_READ_STATUS_CODE_2000) {
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0210E));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0210E);
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        }

        // MOD start 2016/04/20 CSA Defect#6691
        if (!hasValue(fMsg.dsContrIntfcBatNum)) {
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM}));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_INTFC_BAT_NUM});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            // START 2016/08/02 Y.Takeno [QC#11831, ADD]
            return false;
            // END 2016/08/02 Y.Takeno [QC#11831, ADD]
        } else {
            setValue(sMsgLine.dsContrIntfcBatNum_A, fMsg.dsContrIntfcBatNum);
        }
        if (!hasValue(fMsg.dsContrSrcRefNum)) {
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM}));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {DS_CONTR_SRC_REF_NUM});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            // START 2016/08/02 Y.Takeno [QC#11831, ADD]
            return false;
            // END 2016/08/02 Y.Takeno [QC#11831, ADD]
        } else {
            setValue(sMsgLine.dsContrSrcRefNum_A, fMsg.dsContrSrcRefNum);
        }

        if (!hasValue(fMsg.contrIntfcSrcTpCd)) {
            // START 2016/07/14 M.Gotou [QC#11854, MOD]
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD}));
            // END 2016/07/14 M.Gotou [QC#11854, MOD]
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {CONTR_INTFC_SRC_TP_CD});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            // START 2016/08/02 Y.Takeno [QC#11831, ADD]
            return false;
            // END 2016/08/02 Y.Takeno [QC#11831, ADD]
        } else {
            setValue(sMsgLine.contrIntfcSrcTpCd_A, fMsg.contrIntfcSrcTpCd);
        }
        // MOD end 2016/04/20 CSA Defect#6691

        // START 2016/07/28 M.Gotou [QC#12077, MOD]
        // START 2016/08/02 Y.Takeno [QC#11848, DEL]
        // START 2016/05/25 T.Tomita [QC#8898, ADD]
//        setValue(sMsgLine.mtrLbDescTxt_AP, fMsg.mtrLbDescTxt_P);
        // END 2016/05/25 T.Tomita [QC#8898, ADD]
        // END 2016/08/02 Y.Takeno [QC#11848, DEL]

        setValue(sMsgLine.bllblFlg_A, fMsg.bllblFlg);
        setValue(sMsgLine.contrMtrMultRate_A, fMsg.contrMtrMultRate);
        setValue(sMsgLine.intgMdseCd_A, fMsg.intgMdseCd);

        // START 2016/08/02 Y.Takeno [QC#11848, DEL]
        // START 2016/05/25 T.Tomita [QC#8898, ADD]
//        setValue(sMsgLine.mtrLbDescTxt_AB, fMsg.mtrLbDescTxt_B);
        // END 2016/05/25 T.Tomita [QC#8898, ADD]
        // END 2016/08/02 Y.Takeno [QC#11848, DEL]

//        setValue(sMsgLine.serNum_A, fMsg.serNum);
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
            SVC_MACH_MSTRTMsg tMsg = NSAL0820CommonLogic.getSvcMachMstr(cMsg.glblCmpyCd.getValue(), fMsg.svcMachMstrPk.getValue());
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
                SVC_MACH_MSTRTMsg tMsg = NSAL0820CommonLogic.getSvcMachMstrForSerNum(cMsg.glblCmpyCd.getValue(), fMsg.serNum.getValue());
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

        // START 2016/08/02 Y.Takeno [QC#11848, MOD]
//        if (!hasValue(fMsg.svcMachMstrPk) && hasValue(fMsg.serNum)) {
//            // START 2016/08/02 Y.Takeno [QC#11831, MOD]
//            setValue(sMsgLine.bllgMtrLbCd_A, getMtrLbCd(cMsg.glblCmpyCd.getValue(), fMsg.mtrLbDescTxt_B.getValue()));
//            // END 2016/08/02 Y.Takeno [QC#11831, MOD]
//        } else {
//            setValue(sMsgLine.bllgMtrLbCd_A, fMsg.bllgMtrLbCd);
//        }
        // mod start 2016/11/28 CSA Defect#12077
        if (hasValue(fMsg.bllgMtrLbCd) && !hasValue(fMsg.mtrLbDescTxt_B)) {
            setValue(sMsgLine.bllgMtrLbCd_A, fMsg.bllgMtrLbCd);
            setValue(sMsgLine.mtrLbDescTxt_AB, getMtrLbDescTxt(cMsg.glblCmpyCd.getValue(), fMsg.bllgMtrLbCd.getValue()));
        } else if (!hasValue(fMsg.bllgMtrLbCd) && hasValue(fMsg.mtrLbDescTxt_B)) {
            setValue(sMsgLine.mtrLbDescTxt_AB, fMsg.mtrLbDescTxt_B);
            setValue(sMsgLine.bllgMtrLbCd_A, getMtrLbCd(cMsg.glblCmpyCd.getValue(), fMsg.mtrLbDescTxt_B.getValue()));
        } else {
            setValue(sMsgLine.bllgMtrLbCd_A, fMsg.bllgMtrLbCd);
            setValue(sMsgLine.mtrLbDescTxt_AB, fMsg.mtrLbDescTxt_B);
        }

        // Check Error
        if (!hasValue(sMsgLine.bllgMtrLbCd_A) || !hasValue(sMsgLine.mtrLbDescTxt_AB)) {
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {BLLG_MTR_LB}));
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {BLLG_MTR_LB});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        }

        if (!hasValue(fMsg.physMtrLbCd) && !hasValue(fMsg.mtrLbDescTxt_P)) {
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {PHYS_MTR_LB}));
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {PHYS_MTR_LB});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        } else if (hasValue(fMsg.physMtrLbCd) && !hasValue(fMsg.mtrLbDescTxt_P)) {
            setValue(sMsgLine.physMtrLbCd_A, fMsg.physMtrLbCd);
            setValue(sMsgLine.mtrLbDescTxt_AP, getMtrLbDescTxt(cMsg.glblCmpyCd.getValue(), fMsg.physMtrLbCd.getValue()));
        } else if (!hasValue(fMsg.physMtrLbCd) && hasValue(fMsg.mtrLbDescTxt_P)) {
            setValue(sMsgLine.mtrLbDescTxt_AP, fMsg.mtrLbDescTxt_P);
            setValue(sMsgLine.physMtrLbCd_A, getMtrLbCd(cMsg.glblCmpyCd.getValue(), fMsg.mtrLbDescTxt_P.getValue()));
        } else {
            setValue(sMsgLine.physMtrLbCd_A, fMsg.physMtrLbCd);
            setValue(sMsgLine.mtrLbDescTxt_AP, fMsg.mtrLbDescTxt_P);
        }

        // Check Error
        if (!hasValue(sMsgLine.physMtrLbCd_A) || !hasValue(sMsgLine.mtrLbDescTxt_AP)) {
            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {PHYS_MTR_LB}));
            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {PHYS_MTR_LB});
            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
            return false;
        }
        // mod end 2016/11/28 CSA Defect#12077
//        if (!hasValue(fMsg.svcMachMstrPk) && hasValue(fMsg.serNum)) {
//            // START 2016/08/02 Y.Takeno [QC#11831, MOD]
//            setValue(sMsgLine.physMtrLbCd_A, getMtrLbCd(cMsg.glblCmpyCd.getValue(), fMsg.mtrLbDescTxt_P.getValue()));
//            // END 2016/08/02 Y.Takeno [QC#11831, MOD]
//        } else {
//            setValue(sMsgLine.physMtrLbCd_A, fMsg.physMtrLbCd);
//        }
//        // MOD start 2016/04/20 CSA Defect#6691
//        if (!hasValue(fMsg.physMtrLbCd)) {
//            // START 2016/07/14 M.Gotou [QC#11854, MOD]
//            setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0362E, new String[] {PHYS_MTR_LB_CD}));
//            // END 2016/07/14 M.Gotou [QC#11854, MOD]
//            sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0362E, new String[] {PHYS_MTR_LB_CD});
//            setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
//            // START 2016/08/02 Y.Takeno [QC#11831, ADD]
//            return false;
//            // END 2016/08/02 Y.Takeno [QC#11831, ADD]
//        } else {
//            setValue(sMsgLine.physMtrLbCd_A, fMsg.physMtrLbCd);
//        }
        // MOD end 2016/04/20 CSA Defect#6691
        // END 2016/07/28 M.Gotou [QC#12077, MOD]
        // END 2016/08/02 Y.Takeno [QC#11848, MOD]

        setDsActlCntIntfcData(cMsg.glblCmpyCd.getValue(), sMsgLine, fMsg);

        // MOD start 2016/04/20 CSA Defect#6691
        // duplicate check (Clear duplicate dsActlCntIntfcPk)
        for (int i = 0; i < asMsgArray.getValidCount(); i++) {
            if (hasValue(asMsgArray.no(i).dsActlCntIntfcPk_A) && hasValue(sMsgLine.dsActlCntIntfcPk_A) && sMsgLine.dsActlCntIntfcPk_A.getValue().compareTo(asMsgArray.no(i).dsActlCntIntfcPk_A.getValue()) == 0) {
                sMsgLine.dsActlCntIntfcPk_A.clear();
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

    // START 2016/08/02 Y.Takeno [QC#11831, MOD]
    private String getMtrLbCd(String glblCmpyCd, String mtrLbDescTxt) {
        MTR_LBTMsg inMsg = new MTR_LBTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrLbDescTxt01", mtrLbDescTxt);
        MTR_LBTMsgArray tMsgArray = (MTR_LBTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        // mod start 2016/08/26 CSA QC#12077
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
        // mod end 2016/08/26 CSA QC#12077
            return null;
        }
        return tMsgArray.no(0).mtrLbCd.getValue();
    }

    // mod start 2016/11/28 CSA Defect#12077
    private String getMtrLbDescTxt(String glblCmpyCd, String mtrLbCd) {
        MTR_LBTMsg mtrLb = new MTR_LBTMsg();
        setValue(mtrLb.glblCmpyCd, glblCmpyCd);
        setValue(mtrLb.mtrLbCd, mtrLbCd);
        MTR_LBTMsg rtnTMsg = (MTR_LBTMsg) EZDTBLAccessor.findByKey(mtrLb);
        if (rtnTMsg == null) {
            return null;
        }
        return rtnTMsg.mtrLbDescTxt.getValue();
    }
    // mod end 2016/11/28 CSA Defect#12077
    // END 2016/08/02 Y.Takeno [QC#11831, MOD]

    // MOD start 2016/04/22 CSA Defect#6691
    // START 2016/08/02 Y.Takeno [QC#11848,12077, MOD]
    private void setDsActlCntIntfcData(String glblCmpyCd, NSAL0820_ASMsg sMsgLine, NSAL0820F00FMsg fMsg) {
        if (hasValue(sMsgLine.dsContrIntfcBatNum_A) && hasValue(sMsgLine.dsContrSrcRefNum_A) && hasValue(sMsgLine.contrIntfcSrcTpCd_A) && hasValue(sMsgLine.physMtrLbCd_A)) {
            S21SsmEZDResult ssmResultUpdate = NSAL0820Query.getInstance().getdsActlCntIntfcPk(glblCmpyCd, sMsgLine);
            Map<String, Object> rsUpdate = (Map<String, Object>) ssmResultUpdate.getResultObject();
            if (rsUpdate != null) {
                setValue(sMsgLine.dsContrNum_A, (String) rsUpdate.get("DS_CONTR_NUM"));
                setValue(sMsgLine.dsContrIntfcActDescTxt_A, (String) rsUpdate.get("DS_CONTR_INTFC_ACT_DESC_TXT"));
                setValue(sMsgLine.dsContrIntfcStsCd_A, (String) rsUpdate.get("DS_CONTR_INTFC_STS_CD"));
                setValue(sMsgLine.intfcErrMsgTxt_A, (String) rsUpdate.get("INTFC_ERR_MSG_TXT"));
                setValue(sMsgLine.dsContrProcStsDescTxt_A, (String) rsUpdate.get("DS_CONTR_PROC_STS_DESC_TXT"));
                setValue(sMsgLine.dsContrProcStsCd_A, (String) rsUpdate.get("DS_CONTR_PROC_STS_CD"));
                setValue(sMsgLine.dsContrIntfcPk_A, (BigDecimal) rsUpdate.get("DS_CONTR_INTFC_PK"));
                setValue(sMsgLine.dsActlCntIntfcPk_A, (BigDecimal) rsUpdate.get("DS_ACTL_CNT_INTFC_PK"));
                setValue(sMsgLine.ezUpTime, (String) rsUpdate.get("EZUPTIME"));
                setValue(sMsgLine.ezUpTimeZone, (String) rsUpdate.get("EZUPTIMEZONE"));
            } else {
                S21SsmEZDResult ssmResultInsert = NSAL0820Query.getInstance().getDsContrIntfcPk(glblCmpyCd, sMsgLine);
                Map<String, Object> rsInsert = (Map<String, Object>) ssmResultInsert.getResultObject();
                if (rsInsert != null) {
                    setValue(sMsgLine.dsContrNum_A, (String) rsInsert.get("DS_CONTR_NUM"));
                    setValue(sMsgLine.dsContrIntfcActDescTxt_A, (String) rsInsert.get("DS_CONTR_INTFC_ACT_DESC_TXT"));
                    setValue(sMsgLine.dsContrProcStsDescTxt_A, (String) rsInsert.get("DS_CONTR_PROC_STS_DESC_TXT"));
                    setValue(sMsgLine.dsContrProcStsCd_A, (String) rsInsert.get("DS_CONTR_PROC_STS_CD"));
                    setValue(sMsgLine.dsContrIntfcPk_A, (BigDecimal) rsInsert.get("DS_CONTR_INTFC_PK"));
                } else {
                    // START 2016/07/14 M.Gotou [QC#11853,11854, MOD]
                    setValue(sMsgLine.intfcErrMsgTxt_A, S21MessageFunc.clspGetMessage(NSAM0553E));
                    //setValue(sMsgLine.intfcErrMsgTxt_A, new EZDMessageInfo(NSAM0553E).getMessage());
                    sMsgLine.intfcErrMsgTxt_A.setErrorInfo(1, NSAM0553E);
                    setValue(sMsgLine.dsContrIntfcStsCd_A, DS_CONTR_INTFC_STS.ERROR);
                    // END 2016/07/14 M.Gotou [QC#11853,11854, MOD]
                }
            }
        }
    }
    // END 2016/08/02 Y.Takeno [QC#11848,12077, MOD]
    // MOD end 2016/04/22 CSA Defect#6691

    /**
     * get Search Data List
     * @param cMsg NSAL0820CMsg
     * @return Data List
     */
    private void getSearchData(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

        sMsg.clear();
        S21SsmEZDResult ssmResult = NSAL0820Query.getInstance().getSearchData(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
            // set Paging Data
            NSAL0820CommonLogic.pagenation(cMsg, sMsg, 0);

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
    private boolean isAllwDataCrctFlg(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
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
     * @param bizMsg NSAL0820CMsg
     * @param globalMsg NSAL0820SMsg
     * @param rs ResultSet
     * @param fMsg NSAL0820F00FMsg
     * @param csvOutFile ZYPCSVOutFile
     * @throws SQLException
     */
    private void writeCsvFileForHeaderTab(NSAL0820CMsg bizMsg, NSAL0820SMsg globalMsg, ResultSet rs, NSAL0820F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

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
            setValue(fMsg.physMtrLbCd, rs.getString("PHYS_MTR_LB_CD"));
            setValue(fMsg.mtrLbDescTxt_P, rs.getString("PHYS_MTR_LB_NM"));
            setValue(fMsg.bllblFlg, rs.getString("BLLBL_FLG"));
            setValue(fMsg.contrMtrMultRate, rs.getBigDecimal("CONTR_MTR_MULT_RATE"));
            setValue(fMsg.bllgMtrLbCd, rs.getString("BLLG_MTR_LB_CD"));
            setValue(fMsg.mtrLbDescTxt_B, rs.getString("BLLG_MTR_LB_NM")); // *
            setValue(fMsg.intgMdseCd, rs.getString("INTG_MDSE_CD"));

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * @param writeCsvFileHeader
     * @param fMsg
     * @param cMsg
     */
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0820F00FMsg fMsg, NSAL0820CMsg cMsg) {
        final String[] csvHeader = new String[] {"Interface Bat#", "Source Ref#", "Source Type", "Serial#", "IB ID", "Mdse Code", "Actual Counter Code", "Actual Counter Name", "Billable", "Multiplier", "Billing Counter Code",
                "Billing Counter Name", "Intangible Mdse Code" };
        csvOutFile.writeHeader(csvHeader);
    }

    /**
     * setDownloadPram
     * @param cMsg NSAL0820CMsg
     * @param sMsg NSAL0820SMsg
     * @return S21SsmEZDResult
     */
    private Map<String, Object> setDownloadPram(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {
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
        params.put("limitNum", LIMIT_DOWNLOAD);

        return params;
    }

    // START 2016/07/27 M.Gotou [QC#12141, ADD]
    private void doProcess_NSAL0820Scrn00_AddRow(NSAL0820CMsg cMsg, NSAL0820SMsg sMsg) {

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
        S21SsmEZDResult ssmResult = NSAL0820Query.getInstance().getDsContrIntfcActDescTxt(cMsg, sMsg);
        String dsContrIntfcActDescTxt = (String) ssmResult.getResultObject();
        setValue(sMsg.A.no(vldCnt).dsContrIntfcActDescTxt_A, dsContrIntfcActDescTxt);
        setValue(sMsg.A.no(vldCnt).xxRowNum_A, BigDecimal.valueOf(vldCnt + 1));
        sMsg.A.setValidCount(vldCnt + 1);

        NSAL0820CommonLogic.pagenation(cMsg, sMsg, vldCnt);

    }
    // END 2016/07/27 M.Gotou [QC#12141, ADD]
}
