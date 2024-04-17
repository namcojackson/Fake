/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/14/2009   CSAI            K.Uramori       Update          Modification for CSA. Many fields are changed.
 * 2019/08/20   Fujitsu         S.Takami        Update          QC#51897
 * 2019/12/19   Fujitsu         H.Mizukami      Update          QC#53730
 */
package business.blap.NFAL0120;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFAL0120.common.NFAL0120CommonLogic;
import business.blap.NFAL0120.constant.NFAL0120Constant;
import business.db.JRNL_CATGTMsg;
import business.db.JRNL_CATGTMsgArray;
import business.db.SYS_SRCTMsg;
import business.db.SYS_SRCTMsgArray;
import business.file.NFAL0120F00FMsg;
import business.file.NFAL0120FMsg;
import business.parts.NFACommonJrnlEntry;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.JRNL_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 * Class name: NFAL0120BL02
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL0120BL02
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL0120BL02 extends S21BusinessHandler implements NFAL0120Constant {

    /** Singleton instance. */
    private NFAL0120CommonLogic common = new NFAL0120CommonLogic();

    /** Journal Entry Common Module */
    private NFACommonJrnlEntry parts = new NFACommonJrnlEntry();

    /**
     * Method name: doProcess
     * <dd>The method explanation: Call each process by screen id.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            // +++++ [START] : Programming Area
            // NFAL0120Scrn00_PageJump
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            String screenAplID = cMsg.getScreenAplID();

            if ("NFAL0120_INIT".equals(screenAplID)) {
                doProcess_NFAL0120_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NFAL0120CMsg) cMsg, (NFAL0120SMsg) sMsg);
                
            } else if ("NFAL0120Scrn00_OnChange_SYS_SRC_CD".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_OnChange_SYS_SRC_CD(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_OnChange_TRX_CD".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_OnChange_TRX_CD(cMsg, sMsg);
            /*
            } else if ("NFAL0120Scrn00_OnChange_TRX_RSN_CD".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_OnChange_TRX_RSN_CD(cMsg, sMsg);
            */
            } else if ("NFAL0120Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_PageJump(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_InquiryBtn".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_InquiryBtn(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_CSVDownloadBtn".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_CSVDownloadBtn(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_CsvArReclassBtn".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_CsvArReclassBtn(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_CSVDownloadBtn(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_OnChangeSavedSearchOption".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_OnChangeSavedSearchOption(cMsg, sMsg);
            } else if ("NFAL0120Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_TBLColumnSort(cMsg, sMsg);
            // START 2017/12/04 [QC#12525, ADD]
            } else if ("NFAL0120Scrn00_CatgSearchBtn".equals(screenAplID)) {
                doProcess_NFAL0120Scrn00_CatgSearchBtn(cMsg, sMsg);
            // END   2017/12/04 [QC#12525, ADD]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

            // +++++ [E N D] : Programming Area
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Method name: doProcess_NFAL0120_INIT
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120_INIT================================START", this);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg globalMsg = (NFAL0120SMsg) sMsg;
        
        initListBoxes(bizMsg);
        
        // get minimum GL_DATE in Journal Entry
        common.getLastMonth(bizMsg);
        common.checkArchiveDone(bizMsg);
        // START 2017/12/04 [QC#12525, ADD]
        setValue(bizMsg.coaCmpyCd, getGlobalCompanyCode());
        // END   2017/12/04 [QC#12525, ADD]


        // CSA Mod
        // If search criteria for reference text is set, get search result.
        if (ZYPCommonFunc.hasValue(bizMsg.xxQueryFltrTxt)) {
            // Get data
            normalInqury(bizMsg, globalMsg);
        }
        //  ---- End
        
        EZDDebugOutput.println(5, "doProcess_NFAL0120_INIT================================END", this);
    }

    /**
     * Method name: doProcess_NFAL0120Scrn00_InquiryBtn
     * <dd>The method explanation: Reset values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_InquiryBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_InquiryBtn================================START", this);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg globalMsg = (NFAL0120SMsg) sMsg;

        // START 2018/10/25 S.Ohki [QC#28869,ADD]
        NFAL0120CommonLogic.outputSearchLog(bizMsg);
        // END 2018/10/25 S.Ohki [QC#28869,ADD]

        // Get data
        normalInqury(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_InquiryBtn================================END", this);
    }

    /**
     * Method name: doProcess_NFAL0120Scrn00_CSVDownloadBtn
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_CSVDownloadBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CSVDownloadBtn================================START", this);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg globalMsg = (NFAL0120SMsg) sMsg;
        // QC#19408 MOD START
        //// Get data
        //normalInqury(bizMsg, globalMsg);
        //// Create CSV
        //csvDownLoadJrnlInq(bizMsg, globalMsg);
        execCsvdownload(bizMsg, globalMsg);
        // QC#19408 MOD END

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CSVDownloadBtn================================END", this);
    }

    /**
     * Method name: doProcess_NFAL0120Scrn00_CsvArReclassBtn
     * <dd>The method explanation: Initializing.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_CsvArReclassBtn(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CsvArReclassBtn================================START", this);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg globalMsg = (NFAL0120SMsg) sMsg;
        // Get data
        arReclassInqury(bizMsg, globalMsg);
        // Create CSV (A/R reclass)
        csvDownLoadArReclass(bizMsg, globalMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CsvArReclassBtn================================END", this);
    }

    private void normalInqury(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {

        // Clear Screen
        common.clearSearchResult(bizMsg);
        // Get Result
        common.getResultVer1(bizMsg, globalMsg);

        if (bizMsg.xxTotCnt.getValueInt() > 0) {
            // if record found,
            // set fileds for Reset
            setResetFields(bizMsg);
            // Set total Amount for Debit and Credit
            // and total record when needed
            setTotalAmtCount(bizMsg, globalMsg);
        }
    }

    private void execCsvdownload(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {

        Map<String, Object> ssmParam = NFAL0120Query.getInstance().getResultParam(bizMsg, globalMsg, false);
        // START 2018/10/09 J.Kim [QC#28677,MOD]
        // ssmParam.put("rowNum", String.valueOf(CSV_LIMIT_COUNT + 1));
        ssmParam.put("rowNum", null);
        // END 2018/10/09 J.Kim [QC#28677,MOD]

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(MAX_FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFAL0120Query.getInstance().getClass());

            ps = ssmLLClient.createPreparedStatement("getResult", ssmParam, execParam);
            rs = ps.executeQuery();

            // search from Archive
            if (!rs.next()) {
                ssmParam = NFAL0120Query.getInstance().getResultParam(bizMsg, globalMsg, true);
                // START 2018/10/09 J.Kim [QC#28677,MOD]
                // ssmParam.put("rowNum", String.valueOf(CSV_LIMIT_COUNT + 1));
                ssmParam.put("rowNum", null);
                // END 2018/10/09 J.Kim [QC#28677,MOD]
                ps = ssmLLClient.createPreparedStatement("getResult", ssmParam, execParam);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    bizMsg.setMessageInfo("NFAM0002E", null);
                    return;
                }
            }

            csvDownLoadJrnlInq(bizMsg, globalMsg, rs);

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    private void arReclassInqury(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {

        // For AR reclass
        bizMsg.eventId.setValue(YES);
        // Clear Screen
        common.clearSearchResult(bizMsg);
        // Get Result for A/R reclass
        common.getResultArReclass(bizMsg, globalMsg);
    }

    /**
     * Method name: doProcess_NFAL0120Scrn00_CMN_Reset
     * <dd>The method explanation: Reset values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CMN_Reset================================START", this);

        //getTrxRsnListBox(cMsg);

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_CMN_Reset================================END", this);
    }

    private void csvDownLoadJrnlInq(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(FILE_NAME_NORMAL), CSV_EXT);

        //NFAL0120FMsg fMsg = new NFAL0120FMsg();
        NFAL0120F00FMsg fMsg = new NFAL0120F00FMsg();
        // Columns to exclude: Date format of yyyyMMdd
        // and A/R reclass
        excludeItemsForJrnlInq(fMsg);

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // preferred view mod
        // set item order as it shows
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        
        csvOutFile.writeHeader(EXCEL_HEADER_NORMAL, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {

            common.setDrCrAmtCell(globalMsg, i);
            // Mod #2852 2013/10/25 K.Shibuya Start
            // Displaying date format: MM/dd/yyyy
            // globalMsg.A.no(i).xxDtTxt_GL.setValue(ZYPDateUtil.DateFormatter(globalMsg.A.no(i).glDt_A.getValue(), YYYYMMDD, MM_DD_YYYY));
            // globalMsg.A.no(i).xxDtTxt_PR.setValue(ZYPDateUtil.DateFormatter(globalMsg.A.no(i).procDt_A.getValue(), YYYYMMDD, MM_DD_YYYY));
            if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).glDt_A)) {
                globalMsg.A.no(i).xxDtTxt_GL.setValue(ZYPDateUtil.formatEzd8ToDisp(globalMsg.A.no(i).glDt_A.getValue()));
            }
            //if (ZYPCommonFunc.hasValue(globalMsg.A.no(i).procDt_A)) {
            //    globalMsg.A.no(i).xxDtTxt_PR.setValue(ZYPDateUtil.formatEzd8ToDisp(globalMsg.A.no(i).procDt_A.getValue()));
            //}
            // Mod #2852 2013/10/25 K.Shibuya End

            EZDMsg.copy(globalMsg.A.no(i), null, fMsg, null);
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    private void csvDownLoadJrnlInq(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg, ResultSet rs) throws SQLException {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(FILE_NAME_NORMAL), CSV_EXT);

        NFAL0120F00FMsg fMsg = new NFAL0120F00FMsg();
        // Columns to exclude: Date format of yyyyMMdd
        // and A/R reclass
        excludeItemsForJrnlInq(fMsg);

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // preferred view mod
        // set item order as it shows
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        csvOutFile.writeHeader(EXCEL_HEADER_NORMAL, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        // write contents
        do {
            // START 2018/10/09 J.Kim [QC#28677,DEL]
            //if (rs.getRow() >= CSV_LIMIT_COUNT) {
            //    bizMsg.setMessageInfo(NZZM0001W);
            //    break;
            //}
            // END 2018/10/09 J.Kim [QC#28677,DEL]
            String glDt = rs.getString("GL_DT");
            if (ZYPCommonFunc.hasValue(glDt)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_GL, ZYPDateUtil.formatEzd8ToDisp(glDt));
            } else {
                fMsg.xxDtTxt_GL.clear();
            }
            ZYPEZDItemValueSetter.setValue(fMsg.ajeId_A, rs.getString("AJE_ID"));
            ZYPEZDItemValueSetter.setValue(fMsg.ajeInvNum_A, rs.getString("AJE_INV_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.ajeItemCd_A, rs.getString("AJE_ITEM_CD"));
            // START 2018/06/22 S.Katsuma [QC#24025,ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.ajeItemDescTxt_A, rs.getString("AJE_ITEM_DESC_TXT"));
            // END 2018/06/22 S.Katsuma [QC#24025,ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum_A, rs.getString("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaAcctCd_B, rs.getString("COA_ACCT_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaAfflCd_B, rs.getString("COA_AFFL_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaBrCd_B, rs.getString("COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaCcCd_B, rs.getString("COA_CC_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaChCd_B, rs.getString("COA_CH_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaCmpyCd_B, rs.getString("COA_CMPY_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaExtnCd_B, rs.getString("COA_EXTN_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaProdCd_B, rs.getString("COA_PROD_CD_DC"));
            ZYPEZDItemValueSetter.setValue(fMsg.drCoaProjCd_B, rs.getString("COA_PROJ_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A, rs.getString("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.glSendCpltFlg_A, rs.getString("GL_SEND_CPLT_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.invtyTrxPk_A, rs.getBigDecimal("INVTY_TRX_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.jrnlCatgNm_A, rs.getString("JRNL_CATG_NM"));
            String dOrC = rs.getString("DR_OR_CR");
            if (dOrC.equals(DEBIT_NUM_STR)) {
                ZYPEZDItemValueSetter.setValue(fMsg.jrnlFuncDrAmt_A, rs.getBigDecimal("JRNL_FUNC_DR_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.jrnlFuncDrAmt_B, rs.getBigDecimal("JRNL_FUNC_DR_AMT"));
                fMsg.jrnlFuncCrAmt_A.clear();
                fMsg.jrnlFuncCrAmt_B.clear();
            } else if (dOrC.equals(CREDIT_NUM_STR)) {
                fMsg.jrnlFuncDrAmt_A.clear();
                fMsg.jrnlFuncDrAmt_B.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.jrnlFuncCrAmt_A, rs.getBigDecimal("JRNL_FUNC_CR_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.jrnlFuncCrAmt_B, rs.getBigDecimal("JRNL_FUNC_CR_AMT"));
            }
            // START 2018/07/11 S.Katsuma [QC#25710,DEL]
//            ZYPEZDItemValueSetter.setValue(fMsg.jrnlQty_A, rs.getBigDecimal("JRNL_QTY"));
            // END 2018/07/11 S.Katsuma [QC#25710,DEL]
            ZYPEZDItemValueSetter.setValue(fMsg.jrnlSrcNm_A, rs.getString("JRNL_SRC_NM"));
            // START 2018/07/11 S.Katsuma [QC#25710,DEL]
//            ZYPEZDItemValueSetter.setValue(fMsg.manJrnlEntryHdrPk_A, rs.getBigDecimal("MAN_JRNL_ENTRY_HDR_PK"));
            // END 2018/07/11 S.Katsuma [QC#25710,DEL]
            ZYPEZDItemValueSetter.setValue(fMsg.poOrdNum_A, rs.getString("PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.prntDsAssetMstrPk_A, rs.getBigDecimal("PRNT_DS_ASSET_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.psnNum_A, rs.getString("PSN_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.rcptNum_A, rs.getString("RCPT_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.serNum_A, rs.getString("SER_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.soNum_A, rs.getString("SO_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcMachMstrPk_A, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(fMsg.vndCd_A, rs.getString("VND_CD"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxComnScrCondValTxt_A, rs.getString("COA_ACCT_STRG"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxQueryFltrTxt_A1, rs.getString("JRNL_ENTRY_FIRST_REF_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxQueryFltrTxt_A2, rs.getString("JRNL_ENTRY_SCD_REF_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxQueryFltrTxt_A3, rs.getString("JRNL_ENTRY_THIRD_REF_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxQueryFltrTxt_A4, rs.getString("JRNL_ENTRY_FRTH_REF_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxQueryFltrTxt_A5, rs.getString("JRNL_ENTRY_FIFTH_REF_TXT"));

            // START 2019/08/20 S.Takami [QC#51897,ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk_A, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
            // END 2019/08/20 S.Takami [QC#51897,ADD]

            csvOutFile.write();
        } while (rs.next());
        csvOutFile.close();
    }

    private void csvDownLoadArReclass(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(FILE_NAME_AR_RECLASS), CSV_EXT);

        NFAL0120FMsg fMsg = new NFAL0120FMsg();
        // Columns to exclude:
        excludeItemsForArReclass(fMsg);

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(EXCEL_HEADER_AR_RECLASS);

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            EZDMsg.copy(globalMsg.A.no(i), null, fMsg, null);
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    private void excludeItemsForJrnlInq(NFAL0120F00FMsg fMsg) {
        fMsg.addExclusionItem("drCoaCmpyCd_B");
        fMsg.addExclusionItem("drCoaBrCd_B");
        fMsg.addExclusionItem("drCoaCcCd_B");
        fMsg.addExclusionItem("drCoaAcctCd_B");
        fMsg.addExclusionItem("drCoaProdCd_B");
        fMsg.addExclusionItem("drCoaChCd_B");
        fMsg.addExclusionItem("drCoaAfflCd_B");
        fMsg.addExclusionItem("drCoaProjCd_B");
        fMsg.addExclusionItem("drCoaExtnCd_B");
        fMsg.addExclusionItem("jrnlFuncDrAmt_B");
        fMsg.addExclusionItem("jrnlFuncCrAmt_B");
        // START 2018/07/11 S.Katsuma [QC#25710,ADD]
        fMsg.addExclusionItem("jrnlQty_A");
        fMsg.addExclusionItem("manJrnlEntryHdrPk_A");
        // DEL 2018/07/11 S.Katsuma [QC#25710,ADD]
    }

    private void excludeItemsForArReclass(NFAL0120FMsg fMsg) {
        fMsg.addExclusionItem("glDt_A");
        fMsg.addExclusionItem("procDt_A");
        fMsg.addExclusionItem("xxDtTxt_GL");
        fMsg.addExclusionItem("xxDtTxt_PR");
        fMsg.addExclusionItem("ajeId_A");
        fMsg.addExclusionItem("jrnlSrcNm_A");
        fMsg.addExclusionItem("jrnlCatgNm_A");
        fMsg.addExclusionItem("drCoaAcctCd_A");
        fMsg.addExclusionItem("drCoaCmpyCd_A");
        fMsg.addExclusionItem("drCoaBrCd_A");
        fMsg.addExclusionItem("drCoaCcCd_A");
        fMsg.addExclusionItem("drCoaProdCd_A");
        fMsg.addExclusionItem("drCoaChCd_A");
        fMsg.addExclusionItem("drCoaAfflCd_A");
        fMsg.addExclusionItem("drCoaProjCd_A");
        fMsg.addExclusionItem("drCoaExtnCd_A");
        fMsg.addExclusionItem("jrnlFuncDrAmt_A");
        fMsg.addExclusionItem("jrnlFuncCrAmt_A");
        fMsg.addExclusionItem("tocCd_A");
        fMsg.addExclusionItem("billToCustCd_A");
        fMsg.addExclusionItem("vndCd_A");
        fMsg.addExclusionItem("ajeInvNum_A");
        fMsg.addExclusionItem("prmoPk_A");
        fMsg.addExclusionItem("ajeItemCd_A");
        fMsg.addExclusionItem("coaProdCd_A");
        fMsg.addExclusionItem("soNum_A");
    }

    private void setTotalAmtCount(NFAL0120CMsg bizMsg, NFAL0120SMsg globalMsg) {

        // Debit
        // --------------- start CSA modification
        S21SsmEZDResult ssmResultDr = NFAL0120Query.getInstance().getDebitTotal(bizMsg, false);
        
        // START    2019/12/19 [QC#53730, DEL]
        //if (ssmResultDr.isCodeNotFound()) {
        //    // try to search from Archive
        //    ssmResultDr = NFAL0120Query.getInstance().getDebitTotal(bizMsg, true);
        //}
        // END     2019/12/19 [QC#53730, DEL]
        // ------------- end
        
        BigDecimal totalAmtDr = new BigDecimal("0");
        long totalCntDr = 0;
        if (ssmResultDr.isCodeNormal()) {
            List resultList = (List) ssmResultDr.getResultObject();
            if (resultList.size() > 0) {
                Map map = (Map) resultList.get(0);
                // Debit Total Amount
                totalAmtDr = parts.checkNull((BigDecimal) map.get(DR_TOT_AMT));
                // Total Debit Record Count
                totalCntDr = parts.checkNull((BigDecimal) map.get(DR_TOT_COUNT)).longValue();
            }
        }
        bizMsg.xxTotPrcAmt_DR.setValue(totalAmtDr);

        // Credit
        // ----------- start CSA modification
        S21SsmEZDResult ssmResultCr = NFAL0120Query.getInstance().getCreditTotal(bizMsg, false);
        
        // START    2019/12/19 [QC#53730, DEL]
        //if (ssmResultCr.isCodeNotFound()) {
        //    // try to search from Archive
        //    ssmResultCr = NFAL0120Query.getInstance().getCreditTotal(bizMsg, true);
        //}
        // END     2019/12/19 [QC#53730, DEL]
        // ----------------- end
        
        
        BigDecimal totalAmtCr = new BigDecimal("0");
        long totalCntCr = 0;
        if (ssmResultCr.isCodeNormal()) {
            List resultList = (List) ssmResultCr.getResultObject();
            if (resultList.size() > 0) {
                Map map = (Map) resultList.get(0);
                // Credit Total Amount
                totalAmtCr = parts.checkNull((BigDecimal) map.get(CR_TOT_AMT));
                // Total Credit Record Count
                totalCntCr = parts.checkNull((BigDecimal) map.get(CR_TOT_COUNT)).longValue();
            }
        }
        bizMsg.xxTotPrcAmt_CR.setValue(totalAmtCr);        

        long totalCount = totalCntDr + totalCntCr;
        long limit = globalMsg.A.length();
        if (totalCount >= limit) {
            bizMsg.setMessageInfo("NFAM0001W", new String[] {Long.toString(limit), Long.toString(totalCount) });
        }
    }

    /**
     * Method name: doProcess_NFAL0120Scrn00_PageNext
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_PageNext================================START", this);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg globalMsg = (NFAL0120SMsg) sMsg;

        // copy data from SMsg onto CMsg
        bizMsg.A.clear();
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                common.setDrCrAmtCell(globalMsg, i);
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_PageNext================================END", this);
    }

    /**
     * Method name: NFAL0120Scrn00_PageJump
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_PageJump(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "NFAL0120Scrn00_PageJump================================START", this);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg globalMsg = (NFAL0120SMsg) sMsg;

        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                common.setDrCrAmtCell(globalMsg, i);
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount());

        EZDDebugOutput.println(5, "NFAL0120Scrn00_PageJump================================END", this);
    }

    /**
     * Method name: doProcess_NFAL0120Scrn00_PagePrev
     * <dd>The method explanation: Call setData.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_PagePrev================================START", this);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg globalMsg = (NFAL0120SMsg) sMsg;

        bizMsg.A.clear();
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                common.setDrCrAmtCell(globalMsg, i);
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);
        pagenationFrom = pagenationFrom + 1;
        bizMsg.xxPageShowFromNum.setValue(pagenationFrom);
        bizMsg.xxPageShowToNum.setValue(pagenationFrom + bizMsg.A.getValidCount() - 1);

        EZDDebugOutput.println(5, "doProcess_NFAL0120Scrn00_PagePrev================================END", this);
    }

    /**
     * Method name: doProcess_NFAL0120Scrn00_OnChange_SYS_SRC_CD
     * <dd>The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_OnChange_SYS_SRC_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0120Scrn00_OnChange_SYS_SRC_CD================================START", null);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

        //if (bizMsg.sysSrcCd_3.isClear()) {
        //    bizMsg.sysSrcNm.clear();
        //    return;
        //}

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // SYS_SRC_CD = ?sysSrcCd01?
        SYS_SRCTMsg tMsg = new SYS_SRCTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("sysSrcCd01", bizMsg.sysSrcCd_3.getValue());
        SYS_SRCTMsgArray tMsgArr = (SYS_SRCTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        //if (tMsgArr != null && tMsgArr.length() > 0) {
        //    bizMsg.sysSrcNm.setValue(tMsgArr.no(0).sysSrcNm.getValue());
        //}

        EZDDebugOutput.println(1, "doProcess_NFAL0120Scrn00_OnChange_SYS_SRC_CD================================END", null);
    }

    private void doProcess_NFAL0120Scrn00_OnChange_TRX_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0120Scrn00_OnChange_TRX_CD================================START", null);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

    }
    
    /**
     * Method name: doProcess_NFAL0120Scrn00_OnChange_TRX_CD
     * <dd>The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    /*
    private void doProcess_NFAL0120Scrn00_OnChange_TRX_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0120Scrn00_OnChange_TRX_CD================================START", null);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

        if (bizMsg.trxCd_3.isClear()) {
            clearTrxRsnListBox(bizMsg);
            bizMsg.trxNm.clear();
            return;
        }
        

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01?
        TRXTMsg tMsg = new TRXTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        TRXTMsgArray tMsgArr = (TRXTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.trxNm.setValue(tMsgArr.no(0).trxNm.getValue());
        }

        getTrxRsnListBox(cMsg);

        EZDDebugOutput.println(1, "doProcess_NFAL0120Scrn00_OnChange_TRX_CD================================END", null);
    }    
   */

    /*
    private void getTrxRsnListBox(EZDCMsg cMsg) {

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

        clearTrxRsnListBox(bizMsg);

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01?
        // ORDER BY
        // TRX_RSN_CD ASC
        TRX_RSNTMsg tMsg = new TRX_RSNTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        TRX_RSNTMsgArray tMsgArr = (TRX_RSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.trxRsnCd_1.no(i).setValue(tMsgArr.no(i).trxRsnCd.getValue());
                bizMsg.trxRsnCd_2.no(i).setValue(tMsgArr.no(i).trxRsnCd.getValue());
            }
        }
    }

    private void clearTrxRsnListBox(NFAL0120CMsg bizMsg) {
        bizMsg.trxRsnCd_1.clear();
        bizMsg.trxRsnCd_2.clear();
        bizMsg.trxRsnCd_3.clear();
        bizMsg.trxRsnNm.clear();
    }
    */

    
    /**
     * Method name: doProcess_NFAL0120Scrn00_OnChange_TRX_RSN_CD
     * <dd>The method explanation: Business processing for Init
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    /*
    private void doProcess_NFAL0120Scrn00_OnChange_TRX_RSN_CD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFAL0120Scrn00_OnChange_TRX_RSN_CD================================START", null);

        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

        bizMsg.trxRsnNm.clear();
        if (bizMsg.trxRsnCd_3.isClear()) {
            return;
        }

        // <ID>802</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01? AND
        // TRX_CD = ?trxCd01? AND
        // TRX_RSN_CD = ?trxRsnCd01?
        // ORDER BY
        // TRX_CD ASC,
        // TRX_RSN_CD ASC
        TRX_RSNTMsg tMsg = new TRX_RSNTMsg();
        tMsg.setSQLID("802");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        tMsg.setConditionValue("trxCd01", bizMsg.trxCd_3.getValue());
        tMsg.setConditionValue("trxRsnCd01", bizMsg.trxRsnCd_3.getValue());
        TRX_RSNTMsgArray tMsgArr = (TRX_RSNTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            bizMsg.trxRsnNm.setValue(tMsgArr.no(0).trxRsnNm.getValue());
        }

        EZDDebugOutput.println(1, "doProcess_NFAL0120Scrn00_OnChange_TRX_RSN_CD================================END", null);
    }
    */

    private void initListBoxes(NFAL0120CMsg bizMsg) {
        // Sys Src
        setSysSrcListBox(bizMsg);
        
        //---- start 2016/05/31 Add
        NFAL0120CommonLogic.createSavedSearchOptionsPullDown(bizMsg, getContextUserInfo().getUserId());
        //---- end 2016/05/31
        
        // Trx
        //setTrxListBox(bizMsg);
        
        // CSA Modification
        // COA Branch Code
        //setBrCdListBox(bizMsg);
        // COA Channel Code
        //setChCdListBox(bizMsg);

        // START 2017/12/01 [QC#12525, ADD]
        setAjeTrxTpListBox(bizMsg);
        // END   2017/12/01 [QC#12525, ADD]
    }

    private void setSysSrcListBox(NFAL0120CMsg bizMsg) {

        bizMsg.sysSrcCd_1.clear();
        //bizMsg.sysSrcCd_2.clear();
        bizMsg.sysSrcCd_3.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // SYS_SRC_SORT_NUM ASC
        SYS_SRCTMsg tMsg = new SYS_SRCTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        SYS_SRCTMsgArray tMsgArr = (SYS_SRCTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.sysSrcCd_1.no(i).setValue(tMsgArr.no(i).sysSrcCd.getValue());
                bizMsg.sysSrcNm_2.no(i).setValue(tMsgArr.no(i).sysSrcNm.getValue());
            }
        }
    }

    /*
    private void setTrxListBox(NFAL0120CMsg bizMsg) {

        bizMsg.trxCd_1.clear();
        bizMsg.trxCd_2.clear();
        bizMsg.trxCd_3.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // TRX_SORT_NUM ASC
        TRXTMsg tMsg = new TRXTMsg();
        tMsg.setSQLID("801");
        tMsg.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        TRXTMsgArray tMsgArr = (TRXTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgArr != null && tMsgArr.length() > 0) {
            for (int i = 0; i < tMsgArr.length(); i++) {
                bizMsg.trxCd_1.no(i).setValue(tMsgArr.no(i).trxCd.getValue());
                bizMsg.trxCd_2.no(i).setValue(tMsgArr.no(i).trxCd.getValue());
            }
        }
    }
    */

    /*
    private void setBrCdListBox(NFAL0120CMsg bizMsg) {

        bizMsg.coaBrCd_1.clear();
        bizMsg.coaBrCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // COA_BR_CD ASC
        COA_BRTMsg tMsgCoaBr = new COA_BRTMsg();
        tMsgCoaBr.setSQLID("801");
        tMsgCoaBr.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        COA_BRTMsgArray tMsgCoaBrArr = (COA_BRTMsgArray) EZDTBLAccessor.findByCondition(tMsgCoaBr);

        if (tMsgCoaBrArr != null && tMsgCoaBrArr.length() > 0) {
            //test
            //for (int i = 0; i < tMsgCoaBrArr.length(); i++) {
            for (int i = 0; i < 10; i++) {
                bizMsg.coaBrCd_1.no(i).setValue(tMsgCoaBrArr.no(i).coaBrCd.getValue());
                bizMsg.coaBrCd_2.no(i).setValue(tMsgCoaBrArr.no(i).coaBrCd.getValue());
            }
        }
    }

    private void setChCdListBox(NFAL0120CMsg bizMsg) {

        bizMsg.coaChCd_1.clear();
        bizMsg.coaChCd_2.clear();

        // <ID>801</ID>
        // <query><![CDATA[
        // WHERE
        // EZCANCELFLAG = '0' AND
        // GLBL_CMPY_CD = ?glblCmpyCd01?
        // ORDER BY
        // COA_CH_CD ASC
        COA_CHTMsg tMsgCoaCh = new COA_CHTMsg();
        tMsgCoaCh.setSQLID("801");
        tMsgCoaCh.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
        COA_CHTMsgArray tMsgCoaChArr = (COA_CHTMsgArray) EZDTBLAccessor.findByCondition(tMsgCoaCh);

        if (tMsgCoaChArr != null && tMsgCoaChArr.length() > 0) {
            for (int i = 0; i < tMsgCoaChArr.length(); i++) {
                bizMsg.coaChCd_1.no(i).setValue(tMsgCoaChArr.no(i).coaChCd.getValue());
                bizMsg.coaChCd_2.no(i).setValue(tMsgCoaChArr.no(i).coaChCd.getValue());
            }
        }
    }
*/

    // START 2017/12/01 [QC#12525, ADD]
    private void setAjeTrxTpListBox(NFAL0120CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList("AJE_TRX_TP", bizMsg.ajeTrxTpCd_1, bizMsg.ajeTrxTpNm_2);
    }
    // END   2017/12/01 [QC#12525, ADD]

    private void setResetFields(NFAL0120CMsg bizMsg) {

        bizMsg.glDt_FT.setValue(bizMsg.glDt_FR.getValue());
        bizMsg.glDt_TT.setValue(bizMsg.glDt_TO.getValue());

        /*
        bizMsg.sysSrcCd_T.setValue(bizMsg.sysSrcCd_3.getValue());
        bizMsg.trxCd_T.setValue(bizMsg.trxCd_3.getValue());
        bizMsg.trxRsnCd_T.setValue(bizMsg.trxRsnCd_3.getValue());
        bizMsg.sysSrcNm_T.setValue(bizMsg.sysSrcNm.getValue());
        bizMsg.trxNm_T.setValue(bizMsg.trxNm.getValue());
        bizMsg.trxRsnNm_T.setValue(bizMsg.trxRsnNm.getValue());

        bizMsg.ajeId_T.setValue(bizMsg.ajeId.getValue());
        bizMsg.coaBrCd_T.setValue(bizMsg.coaBrCd_3.getValue());
        bizMsg.coaCcCd_T.setValue(bizMsg.coaCcCd.getValue());
        bizMsg.coaAcctCd_T.setValue(bizMsg.coaAcctCd.getValue());
        bizMsg.drCoaProdCd_T.setValue(bizMsg.drCoaProdCd.getValue());
        bizMsg.coaChCd_T.setValue(bizMsg.coaChCd_3.getValue());
        bizMsg.coaAfflCd_T.setValue(bizMsg.coaAfflCd.getValue());
        bizMsg.coaProjCd_T.setValue(bizMsg.coaProjCd.getValue());
        bizMsg.billToCustCd_T.setValue(bizMsg.billToCustCd.getValue());
        bizMsg.vndCd_T.setValue(bizMsg.vndCd.getValue());
        bizMsg.ajeInvNum_T.setValue(bizMsg.ajeInvNum.getValue());
        bizMsg.prmoPk_T.setValue(bizMsg.prmoPk.getValue());
        bizMsg.ajeItemCd_T.setValue(bizMsg.ajeItemCd.getValue());
        bizMsg.coaProdCd_T.setValue(bizMsg.coaProdCd.getValue());
        bizMsg.soNum_T.setValue(bizMsg.soNum.getValue());
        */
    }
    
    /**
     * Method name: doProcess_NFAL0120Scrn00_OnChangeSavedSearchOption
     * <dd>The method explanation: OnChangeSavedSearchOption
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFAL0120Scrn00_OnChangeSavedSearchOption(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg glblMsg = (NFAL0120SMsg) sMsg;
        
        if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            NFAL0120CommonLogic.callNszc0330forSearchOption(bizMsg, glblMsg, getContextUserInfo().getUserId(), this.getGlobalCompanyCode());
        }
    }
    
    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFAL0120Scrn00_TBLColumnSort(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;
        NFAL0120SMsg glblMsg = (NFAL0120SMsg) sMsg;
        
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy = bizMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

            NFAL0120CommonLogic.loadOnePageToCMsg(bizMsg, glblMsg);
            
            // initialize
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
        }
    }

    // START 2017/12/04 [QC#12525, ADD]
    private void doProcess_NFAL0120Scrn00_CatgSearchBtn(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFAL0120CMsg bizMsg = (NFAL0120CMsg) cMsg;

        if (!ZYPCommonFunc.hasValue(bizMsg.jrnlCatgCd)) {
            bizMsg.jrnlCatgNm.clear();
            return;
        }

        if (!NFAL0120CommonLogic.jrnlCatgSearch(bizMsg, getGlobalCompanyCode())) {
            bizMsg.jrnlCatgCd.setErrorInfo(1, "NFAM0024E", new String[] {bizMsg.jrnlCatgCd.getValue(), "Journal Category Code" });
        }
    }
    // END   2017/12/04 [QC#12525, ADD]
}
