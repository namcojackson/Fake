/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB118001;

import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DATE_DATA_FORMAT;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DATE_PRINT_FORMAT;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_CNT_INP_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_DEF_SRC_LOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_HR_SUPV_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_HR_SUPV_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_PHYS_INVTY_CTRL_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_PHYS_INVTY_CTRL_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_PHYS_INVTY_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_REQ_TIME;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_RTL_WH_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_TECH_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_TECH_TOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_COLUMN_USER_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.DEF_SRC_LOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.HAIFUN;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.INTL_LANG_VAL_COL_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.NPAM0078E;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.NPAM1499E;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.PHYS_INVTY_CTRL_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.REPORT_LOGICAL_PRINT_KEY_DELIMITER;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.RPT_ID_NLCF0030;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.RPT_PRINT_RQST_SQ;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.TECH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.TIME_PRINT_FORMAT;
import static com.canon.cusa.s21.batch.NLC.NLCB118001.constant.NLCB118001Constant.USER_ID;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.TECH_PI_CNT_RPT_WRKTMsg;
import business.db.TECH_PI_RPT_RQSTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NLCB118001:Tech PI Count Printing from Tech Mobile
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/06/2018   CITS            K.Fukumura      Create          QC#10572
 * 11/06/2019   CITS            K.Ogino         Create          QC#54508
 *</pre>
 */
public class NLCB118001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Proc Date */
    private String batProcDate = null;

    /** User Profile Service */
    private S21UserProfileService profSrvc = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Client. */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** total count */
    private int totCnt;

    /** Error count */
    private int errCnt;

    /** S21EIPPrintingService. */
    private S21EIPPrintingService service = null;

    /** Processing Count. */
    private int procCnt;

    /** Total Printing Service count. */
    private int totalPrintCount;

    /** Adjustment Date. */
    private String adjSubmtTs;

     /**
     * main.
     * @param args  String[]
     */
    public static void main(String[] args) {
        new NLCB118001().executeBatch(NLCB118001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        profSrvc = S21UserProfileServiceFactory.getInstance().getService();
        // SQL access parts
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        termCd = TERM_CD.ABNORMAL_END;
        totCnt = 0;
        errCnt = 0;
        totalPrintCount = 0;

        // Check the input parameters.
        checkParams();
    }

    @Override
    protected void mainRoutine() {

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            // Initialize printing service object.
            this.service = new S21EIPPrintingService();
            // =================================================
            // Date Field Format
            // =================================================
            // Print Date, Print Time
            String strPrintDate = convertDateTxt(batProcDate);
            String strPrintTime = ZYPDateUtil.getCurrentSystemTime(TIME_PRINT_FORMAT);
            // =================================================
            // Print Group Key
            // =================================================
            String printGroupKeyCdCur = "";
            String printGroupKeyCdPre = "";
            // EIP Parameter Key
            TECH_PI_CNT_RPT_WRKTMsg eipRptPrmKey = new TECH_PI_CNT_RPT_WRKTMsg();
            List<TECH_PI_CNT_RPT_WRKTMsg> eipRptPrmKeyAry = new ArrayList<TECH_PI_CNT_RPT_WRKTMsg>();
            // =================================================
            // Printed Record Register
            // =================================================
            String physInvtyCtlPrintedKeyCur = "";
            String physInvtyCtlPrintedKeyPre = "";
            // =================================================
            // SQL Cursor
            // =================================================
            // Parameter
            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();
            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            // Get PIResult List
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            stmt = this.ssmLLClient.createPreparedStatement("getPrintData", queryParam, ssmEexcParam);
            resultSet = stmt.executeQuery();

            // Print Request Sequence
            BigDecimal numTecPiCntRptRqstSq = null;

            while (resultSet.next()) {

                // Printed PhysInvtyCtrl Key
                physInvtyCtlPrintedKeyCur = getReportPrintedKey(resultSet);

                // =================================================
                // Print Group Proc
                // =================================================
                // Print Group Ley Get
                printGroupKeyCdCur = getReportLogicalPrintKey(resultSet);

                // Not Equele(Current Group Key, Previous Group Key)
                if (!printGroupKeyCdCur.equals(printGroupKeyCdPre)) {
                    // EIP Request Sequence
                    numTecPiCntRptRqstSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_PI_CNT_RPT_RQST_SQ);
                    // EIP Parameter Set
                    eipRptPrmKey = new TECH_PI_CNT_RPT_WRKTMsg();
                    ZYPEZDItemValueSetter.setValue(eipRptPrmKey.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(eipRptPrmKey.techPiCntRptRqstSq, numTecPiCntRptRqstSq);
                    ZYPEZDItemValueSetter.setValue(eipRptPrmKey.defSrcLocCd, resultSet.getString(DB_COLUMN_DEF_SRC_LOC_CD));
                    ZYPEZDItemValueSetter.setValue(eipRptPrmKey.techCd, resultSet.getString(DB_COLUMN_TECH_TOC_CD));
                    ZYPEZDItemValueSetter.setValue(eipRptPrmKey.printPsnCd, resultSet.getString(DB_COLUMN_USER_ID));
                    ZYPEZDItemValueSetter.setValue(eipRptPrmKey.physInvtyCtrlNm, resultSet.getString(DB_COLUMN_PHYS_INVTY_CTRL_NM));

                    // For Report Title
                    ZYPEZDItemValueSetter.setValue(eipRptPrmKey.physInvtyCtrlNm, resultSet.getString(DB_COLUMN_PHYS_INVTY_CTRL_NM));
                    eipRptPrmKeyAry.add(eipRptPrmKey);
                }
                // =================================================
                // Printed Record Insert(TECH_PI_RPT_RQST)
                // =================================================
                if (!physInvtyCtlPrintedKeyCur.equals(physInvtyCtlPrintedKeyPre)) {
                    // Insert TECH_PI_RPT_RQS
                    insertTecPiRptRqst(resultSet);
                }
                // =================================================
                // EIP Print Work Table Insert()
                // =================================================
                insertTecPiCntRptWrk(resultSet, numTecPiCntRptRqstSq, strPrintDate, strPrintTime);
                // =================================================
                // Current Key -> Previous Key
                // =================================================
                printGroupKeyCdPre = printGroupKeyCdCur;
                physInvtyCtlPrintedKeyPre = physInvtyCtlPrintedKeyCur;
                procCnt++;
                totCnt++;
            }
            if (procCnt > 0) {
                // =================================================
                // Create EIP Report
                // =================================================
                for (TECH_PI_CNT_RPT_WRKTMsg eipRptPrm  : eipRptPrmKeyAry) {
                    createEipReport(eipRptPrm);
                }
                totalPrintCount++;
            }
            termCd = TERM_CD.NORMAL_END;

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, resultSet);
        }
    }

    @Override
    protected void termRoutine() {
        if (totalPrintCount > 0) {
            // If success, activate Create Report Processing.
            this.service.activateAsyncReportJob();
        }
        setTermState(termCd, totCnt - errCnt, errCnt, totCnt);
    }


    /**
     * Check the input parameters. If an error occurs, throws
     * Exception.
     */
    private void checkParams() {

        // Get the Global Company Code.
        glblCmpyCd = profSrvc.getGlobalCompanyCode();
        // If an error occurs, throw Exception.
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            noValueError(new String[] {"Global Company Code"});
        }

        // Get the Batch Proc Date.
        batProcDate = ZYPDateUtil.getBatProcDate(glblCmpyCd);
    }

    /**
     * No Value Error
     * @param item Error Item
     */
    private void noValueError(String[] item) {
        throw new S21AbendException(NPAM0078E, item);
    }

    private void insertTecPiCntRptWrk(ResultSet resultSet, BigDecimal numTechPiCntRptRqstSq, String pStrPrintDate, String pStrPrintTime) throws SQLException {
        TECH_PI_CNT_RPT_WRKTMsg tMsg = new TECH_PI_CNT_RPT_WRKTMsg();
        BigDecimal numTecPiCntRptWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_PI_CNT_RPT_WRK_SQ);

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.techPiCntRptWrkPk, numTecPiCntRptWrkPk);
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcLocCd, resultSet.getString(DB_COLUMN_DEF_SRC_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.defSrcLocNm, resultSet.getString(DB_COLUMN_RTL_WH_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.techCd, resultSet.getString(DB_COLUMN_TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.techNm, resultSet.getString(DB_COLUMN_TECH_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.techMgrCd, resultSet.getString(DB_COLUMN_HR_SUPV_ID));

        ZYPEZDItemValueSetter.setValue(tMsg.techMgrNm, resultSet.getString(DB_COLUMN_HR_SUPV_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCtrlNm, resultSet.getString(DB_COLUMN_PHYS_INVTY_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.physInvtyDtFmtTxt, convertDateTxt(resultSet.getString(DB_COLUMN_PHYS_INVTY_DT)));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, resultSet.getString(DB_COLUMN_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, resultSet.getString(DB_COLUMN_RTL_SWH_CD));

        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, resultSet.getString(DB_COLUMN_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseDescShortTxt, resultSet.getString(DB_COLUMN_MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.cntQtyDplyTxt, resultSet.getString(DB_COLUMN_CNT_INP_QTY));

        ZYPEZDItemValueSetter.setValue(tMsg.printPsnCd, resultSet.getString(DB_COLUMN_USER_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.printDtFmtTxt, pStrPrintDate);
        ZYPEZDItemValueSetter.setValue(tMsg.printRptTmTxt, pStrPrintTime);
        ZYPEZDItemValueSetter.setValue(tMsg.techPiCntRptRqstSq, numTechPiCntRptRqstSq);

        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NPAM1499E, new String[] {tMsg.getTableName(), resultSet.getBigDecimal(DB_COLUMN_PHYS_INVTY_CTRL_PK).toPlainString()});
        }
    }

    // TECH_PI_RPT_RQS Insert
    private void insertTecPiRptRqst(ResultSet resultSet) throws SQLException {
        TECH_PI_RPT_RQSTTMsg tMsg = new TECH_PI_RPT_RQSTTMsg();
        BigDecimal numTechPiRptRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_PI_RPT_RQST_SQ);

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.techPiRptRqstPk, numTechPiRptRqstPk);
        ZYPEZDItemValueSetter.setValue(tMsg.physInvtyCtrlPk, resultSet.getBigDecimal(DB_COLUMN_PHYS_INVTY_CTRL_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.rqstUsrId, resultSet.getString(DB_COLUMN_USER_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.rptRqstProcTs, resultSet.getString(DB_COLUMN_REQ_TIME));


        EZDTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            throw new S21AbendException(NPAM1499E, new String[] {tMsg.getTableName(), resultSet.getBigDecimal(DB_COLUMN_PHYS_INVTY_CTRL_PK).toPlainString()});
        }
    }
    private static String convertDateTxt(String strDate) {
        SimpleDateFormat dateFormatIn = new SimpleDateFormat(DATE_DATA_FORMAT);
        SimpleDateFormat dateFormatOut = new SimpleDateFormat(DATE_PRINT_FORMAT, Locale.US);
        String result = null;

        try {
            Date resultDate = dateFormatIn.parse(strDate);
            result = dateFormatOut.format(resultDate);

        } catch (Exception e) {
            return result;
        }
        return result;
    }
    /**
     * writeReport
     * @param headerInfo    TECH_PI_RSLT_RPT_WRKTMsg
     */
    private void createEipReport(TECH_PI_CNT_RPT_WRKTMsg pEipPrmKey) {

        // Generate S21ReportRequestBean
        S21ReportRequestBean requestBean = new S21ReportRequestBean(RPT_ID_NLCF0030);
        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        // Report Title
        String techTocCd = pEipPrmKey.techCd.getValue();
        String physInvtyCtrlNm = pEipPrmKey.physInvtyCtrlNm.getValue();
        String rptTtlNm = ZYPCommonFunc.concatString(techTocCd, HAIFUN, physInvtyCtrlNm);
        rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, HAIFUN, adjSubmtTs);
        requestBean.setRptTtlNm(rptTtlNm);

        S21InputParameter inputParam = requestBean.getInputParamBeanInstance();
        inputParam.addReportParameter(GLBL_CMPY_CD, this.glblCmpyCd);
        inputParam.addReportParameter(INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter(RPT_PRINT_RQST_SQ, pEipPrmKey.techPiCntRptRqstSq.getValue());
        inputParam.addReportParameter(DEF_SRC_LOC_CD, pEipPrmKey.defSrcLocCd.getValue());
        inputParam.addReportParameter(TECH_CD, techTocCd);
        inputParam.addReportParameter(USER_ID, pEipPrmKey.printPsnCd.getValue());
        inputParam.addReportParameter(PHYS_INVTY_CTRL_NM, physInvtyCtrlNm);
        requestBean.setInputParamBean(inputParam);

        // Printer Setting
        // Set Report Input Parameter
        // QC#54508
//        S21PrinterOutputParameter outputParam = new S21PrinterOutputParameter();
//        outputParam.setBranchNo("01");  // Printer
//        requestBean.setPrintOutParamBean(outputParam);
        // Create Request
        this.service.createReportByAsync(requestBean);
    }

    /**
     * getReportLogicalPrintKey.
     * @param   resultSet   ResultSet
     * @return  key         String
     */
    private String getReportLogicalPrintKey(ResultSet resultSet) throws SQLException {

        String strReqTm = resultSet.getString(DB_COLUMN_REQ_TIME);
        String strUsrId = resultSet.getString(DB_COLUMN_USER_ID);

        String key = ZYPCommonFunc.concatString(strReqTm, REPORT_LOGICAL_PRINT_KEY_DELIMITER , strUsrId);

        return key;
    }

    private String getReportPrintedKey(ResultSet resultSet) throws SQLException {

        String strCtlPk = resultSet.getString(DB_COLUMN_PHYS_INVTY_CTRL_PK);
        String strUsrId = resultSet.getString(DB_COLUMN_USER_ID);
        String strPrtTm = resultSet.getString(DB_COLUMN_REQ_TIME);

        String key = ZYPCommonFunc.concatString(strCtlPk, REPORT_LOGICAL_PRINT_KEY_DELIMITER , strUsrId);
        key = ZYPCommonFunc.concatString(key, REPORT_LOGICAL_PRINT_KEY_DELIMITER , strPrtTm);
        return key;
    }
}
