/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB140001;

import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.COMMA;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DATE_TIME_FORMAT_14;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DATE_TIME_FORMAT_17;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DATE_TIME_PRINT_FORMAT;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_CONST_SPLIT;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DEF_TS_ZERO;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.HYPHEN;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.INSOURCING_PR;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.INSOURCING_TO_3PL_WH_DB;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.INSOURCING_TO_3PL_WH_OTHER;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.INSOURCING_TO_CSA_WH;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.ONE_BLANK;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.PERIOD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.RPT_ID_NPAF0040;

import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.NPAM0078E;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.NPAM1003E;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.NPAM1499E;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.NPAM1328E;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.NPAM1342E;

import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_CTY_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_DS_BIZ_LAST_UPD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_DS_BIZ_PROC_LOG_PK;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_POST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD1_PRCH_REQ_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD1_SRC_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD1_SRC_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_DEST_INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_ORIG_RQST_MDSE_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRCH_REQ_CRAT_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRCH_REQ_CRAT_TM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRCH_REQ_INSRC_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRCH_REQ_LINE_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRCH_REQ_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRCH_REQ_LINE_TP_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRCH_REQ_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRCH_REQ_QTY;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRCH_REQ_REL_ERR_MSG_TXT;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_SRC_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_SRC_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD2_VND_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD3_PO_ORD_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRD3_PRCH_REQ_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PRH2_PRCH_REQ_NUM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_PV1_PRNT_VND_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_RPT_BR_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_RPT_PRINT_CTRL_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_RPT_PRINT_CTRL_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_RPT_PRINT_CTRL_VAL_TXT_01;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_RTL_WH_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_RWH1_RTL_WH_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_RWH3_RTL_WH_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_ST_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_VD1_LOC_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_BIZ_LAST_UPD_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_PRCH_REQ_LINE_TP_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_PRCH_REQ_REC_TP_PO_REQUISITION;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_PRCH_REQ_REC_TP_TECH_REQUEST;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_PRCH_REQ_SRC_TP_INSOURCING;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_PRNT_PURGE_TS;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_PRNT_VND_CD_LIST;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_PROC_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_PROC_PGM_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_RPT_ID;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_PARAM_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.DB_COLUMN_INSRC_RPT_WRK_PK;

import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.INSRC_RPT_PRINT_RQST_SQ;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.INTL_LANG_VAL_COL_NM;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.REPORT_TITLE;

import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_DIRECT_PRINT;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_INSRC_PR;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_INSRC_PR_EX_SPLY;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_INSRC_TO_3PL_WH_DB;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_INSRC_TO_3PL_WH_OT;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_INSRC_TO_CSA_WH;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_PURGE_DT;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_SMRY_INSRC_PR;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_SMRY_INSRC_PR_SPLY;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_SMRY_INSRC_TO_CHO_WH;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_SMRY_INSRC_TO_CLB_WH;
import static com.canon.cusa.s21.batch.NPA.NPAB140001.constant.NPAB140001Constant.CONST_NPAF0040_SMRY_INSRC_TO_CSA_WH;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.INSRC_RPT_WRKTMsg;
import business.db.RPT_PRINT_CTRLTMsg;
import business.db.DS_BIZ_PROC_LOGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
import com.canon.cusa.s21.framework.printing.eip.S21PrinterOutputParameter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * NPAB140001:Insourcing Report Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/17/2016   CITS            Y.Fujii         Create          E477
 * 04/16/2019   CITS            A.Kobayashi     Update          QC#31144
 *</pre>
 */
public class NPAB140001 extends S21BatchMain {


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


    /** bizProcLogPk. */
    private BigDecimal bizProcLogPk = null;

    /** bizLastUpdTs. */
    private String bizLastUpdTs = null;

    /** S21EIPPrintingService. */
    private S21EIPPrintingService service = null;

    /** Processing Count. */
    private BigDecimal procCnt;

    /** Print Count. */
    private int printCnt;

    /** Total Printing Service count. */
    private int totalPrintCount;

    /** CSA_LIST_INSRC_TXT. */
    private BigDecimal csaListInsrcCnt = BigDecimal.ZERO;

    /** CB_LIST_INSRC_TXT. */
    private BigDecimal cbListInsrcCnt = BigDecimal.ZERO;

    /** CH_LIST_INSRC_TXT. */
    private BigDecimal chListInsrcCnt = BigDecimal.ZERO;

    /** CUSA_LIST_INSRC_TXT. */
    private BigDecimal cusaListInsrcCnt = BigDecimal.ZERO;

    /** INSRC_RPT_WRK Table PK. */
    private BigDecimal insrcRptWrkPk = null;

    /** report work table purge date. */
    BigDecimal purgeDt;

    /** CSA Warehouses(PRCH_REQ_LINE_TP_CD). */
    String[] insrcToCsaWhList;

    /** 3PL Warehouses(PRCH_REQ_LINE_TP_CD). */
    String[] insrcTo3PlWhDBList;

    /** 3PL Warehouses(PRCH_REQ_LINE_TP_CD). */
    String[] insrcTo3PlWhOhterList;

    /** Via Purchase Requisition(PRCH_REQ_LINE_TP_CD). */
    String[] insrcPRList;

    /** Via Purchase Requisition(PRNT_VND_CD). */
    String[] insrcPrExSplyList;

    /** Report Summary CSA Warehouses(PRCH_REQ_LINE_TP_CD). */
    String[] smryInsrcToCsaWhList;

    /** Report Summary 3PL Warehouses(PRCH_REQ_LINE_TP_CD). */
    String[] smryInsrcToClbWhList;

    /** Report Summary Choice Warehouse(PRCH_REQ_LINE_TP_CD). */
    String[] smryInsrcToChoWhList;

    /** Report Summary Via Purchase Requisition(PRCH_REQ_LINE_TP_CD). */
    String[] smryInsrcPRList;

    /** Report Summary Via Purchase Requisition(PRNT_VND_CD). */
    String smryInsrcPRSplyCSV;

    /** Direct Print. */
    String directPrint;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new NPAB140001().executeBatch(NPAB140001.class.getSimpleName());
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

        // Check the DB parameters.
        checkDBParams();
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(DB_PARAM_RPT_ID, RPT_ID_NPAF0040);

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            // Get RPT_PRINT_CTRL List
            stmt = this.ssmLLClient.createPreparedStatement("getRptPrintCtrl", queryParam, ssmEexcParam);
            result = stmt.executeQuery();

            // Initialize printing service object.
            this.service = new S21EIPPrintingService();

            while (result.next()) {
                RPT_PRINT_CTRLTMsg rptPrintCtrl = setRptPrintCtrl(result);

                // initial count
                printCnt = 0;
                procCnt = BigDecimal.ZERO;

                mainProcessRtlWh(rptPrintCtrl);
                if (BigDecimal.ZERO.compareTo(procCnt) < 0) {
                    // add totalPrintCount
                    totalPrintCount += printCnt;
                    commit();
                }

                totCnt++;

                csaListInsrcCnt = BigDecimal.ZERO;
                cbListInsrcCnt = BigDecimal.ZERO;
                chListInsrcCnt = BigDecimal.ZERO;
                cusaListInsrcCnt = BigDecimal.ZERO;
            }

            termCd = TERM_CD.NORMAL_END;
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } catch (S21AbendException e) {
            rollback();
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, result);
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
     * Check the DB parameters. If an error occurs, throws
     * Exception.
     */
    private void checkDBParams() {

        /** report work table purge date. */
        purgeDt = ZYPCodeDataUtil.getNumConstValue(CONST_NPAF0040_PURGE_DT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(purgeDt)) {
            noValueError(new String[] {CONST_NPAF0040_PURGE_DT});
        }

        /** CSA Warehouses(PRCH_REQ_LINE_TP_CD). */
        String insrcToCsaWh = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_INSRC_TO_CSA_WH, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(insrcToCsaWh)) {
            noValueError(new String[] {CONST_NPAF0040_INSRC_TO_CSA_WH});
        }
        insrcToCsaWhList = insrcToCsaWh.split(DB_CONST_SPLIT, 0);

        /** 3PL Warehouses DB(PRCH_REQ_LINE_TP_CD). */
        String insrcTo3PlWhDB = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_INSRC_TO_3PL_WH_DB, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(insrcTo3PlWhDB)) {
            noValueError(new String[] {CONST_NPAF0040_INSRC_TO_3PL_WH_DB});
        }
        insrcTo3PlWhDBList = insrcTo3PlWhDB.split(DB_CONST_SPLIT, 0);

        /** 3PL Warehouses Other(PRCH_REQ_LINE_TP_CD). */
        String insrcTo3PlWhOhter = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_INSRC_TO_3PL_WH_OT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(insrcTo3PlWhOhter)) {
            noValueError(new String[] {CONST_NPAF0040_INSRC_TO_3PL_WH_OT});
        }
        insrcTo3PlWhOhterList = insrcTo3PlWhOhter.split(DB_CONST_SPLIT, 0);

        /** Via Purchase Requisition(PRCH_REQ_LINE_TP_CD). */
        String insrcPR = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_INSRC_PR, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(insrcPR)) {
            noValueError(new String[] {CONST_NPAF0040_INSRC_PR});
        }
        insrcPRList = insrcPR.split(DB_CONST_SPLIT, 0);

        /** Via Purchase Requisition(PRNT_VND_CD). */
        String insrcPrExSply = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_INSRC_PR_EX_SPLY, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(insrcPrExSply)) {
            // insrcPrExSply default null not checked
            // noValueError(new String[] {CONST_NPAF0040_INSRC_PR_EX_SPLY});
            insrcPrExSplyList = null;
        } else {
            insrcPrExSplyList = insrcPrExSply.split(DB_CONST_SPLIT, 0);
        }

        /** Report Summary CSA Warehouses(PRCH_REQ_LINE_TP_CD). */
        String smryInsrcToCsaWh = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_SMRY_INSRC_TO_CSA_WH, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(smryInsrcToCsaWh)) {
            noValueError(new String[] {CONST_NPAF0040_SMRY_INSRC_TO_CSA_WH});
        }
        smryInsrcToCsaWhList = smryInsrcToCsaWh.split(DB_CONST_SPLIT, 0);

        /** Report Summary 3PL Warehouses(PRCH_REQ_LINE_TP_CD). */
        String smryInsrcToClbWh = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_SMRY_INSRC_TO_CLB_WH, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(smryInsrcToClbWh)) {
            noValueError(new String[] {CONST_NPAF0040_SMRY_INSRC_TO_CLB_WH});
        }
        smryInsrcToClbWhList = smryInsrcToClbWh.split(DB_CONST_SPLIT, 0);

        /** Report Summary Choice Warehouse(PRCH_REQ_LINE_TP_CD). */
        String smryInsrcToChoWh = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_SMRY_INSRC_TO_CHO_WH, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(smryInsrcToChoWh)) {
            noValueError(new String[] {CONST_NPAF0040_SMRY_INSRC_TO_CHO_WH});
        }
        smryInsrcToChoWhList = smryInsrcToChoWh.split(DB_CONST_SPLIT, 0);

        /** Report Summary Via Purchase Requisition(PRCH_REQ_LINE_TP_CD). */
        String smryInsrcPR = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_SMRY_INSRC_PR, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(smryInsrcPR)) {
            noValueError(new String[] {CONST_NPAF0040_SMRY_INSRC_PR});
        }
        smryInsrcPRList = smryInsrcPR.split(DB_CONST_SPLIT, 0);

        /** Report Summary Via Purchase Requisition(PRNT_VND_CD). */
        smryInsrcPRSplyCSV = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_SMRY_INSRC_PR_SPLY, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(smryInsrcPRSplyCSV)) {
            noValueError(new String[] {CONST_NPAF0040_SMRY_INSRC_PR_SPLY});
        }

        /** Direct Print. */
        directPrint = ZYPCodeDataUtil.getVarCharConstValue(CONST_NPAF0040_DIRECT_PRINT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(directPrint)) {
            noValueError(new String[] {CONST_NPAF0040_DIRECT_PRINT});
        }
    }

    /**
     * No Value Error
     * @param item Error Item
     */
    private void noValueError(String[] item) {
        throw new S21AbendException(NPAM0078E, item);
    }

    /**
     * setRptPrintCtrl.
     * @param   result ResultSet
     * @return  rptPrintCtrl RPT_PRINT_CTRLTMsg
     */
    private RPT_PRINT_CTRLTMsg setRptPrintCtrl(ResultSet result) throws SQLException {

        RPT_PRINT_CTRLTMsg rptPrintCtrl = new RPT_PRINT_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(rptPrintCtrl.rptPrintCtrlId, result.getString(DB_COLUMN_RPT_PRINT_CTRL_ID));
        ZYPEZDItemValueSetter.setValue(rptPrintCtrl.rptBrId, result.getString(DB_COLUMN_RPT_BR_ID));
        ZYPEZDItemValueSetter.setValue(rptPrintCtrl.rptPrintCtrlNm, result.getString(DB_COLUMN_RPT_PRINT_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(rptPrintCtrl.rptPrintCtrlValTxt_01, result.getString(DB_COLUMN_RPT_PRINT_CTRL_VAL_TXT_01));

        return rptPrintCtrl;
    }

    /**
     * Main Process.
     * @param rptPrintCtrl RPT_PRINT_CTRLTMsg
     */
    private void mainProcessRtlWh(RPT_PRINT_CTRLTMsg rptPrintCtrl) {

        String rtlWhCd = rptPrintCtrl.rptPrintCtrlId.getValue();
        // remove work data
        removeInsrcRptWrk();

        // get Pk, LastUpdateTs
        getLastUpdateTs(rtlWhCd);

        // QC#31144 inform the time for processing data from last task (this message can be changed)
        S21InfoLogOutput.println("TARGET DATA : " + bizLastUpdTs);

        // get report header info
        INSRC_RPT_WRKTMsg headerInfo = getHeaderInfo(rtlWhCd);

        // output Insourcing Parts Request data
        outputInsourcingData(headerInfo, rptPrintCtrl, rtlWhCd);

        // output Insourcing Error data
        outputInsourcingErrorData(headerInfo, rptPrintCtrl, rtlWhCd);

        if (BigDecimal.ZERO.compareTo(procCnt) < 0) {
            // Update Footer Info
            updateInsrcLineCnt();

            // EIP Report Printing
            writeReport(headerInfo.insrcRptPrintRqstSq.getValue(), rptPrintCtrl);

            // update / insert LastUpdateTs
            updateLastUpdateTs(rtlWhCd);
        }

        // QC#31144 inform the time to finish and next time processing (this message can be changed)
        S21InfoLogOutput.println("NEXT TIME TARGET DATA : " + bizLastUpdTs);

    }

    /**
     * getLastUpdateTs
     * @param   rtlWhCd String
     */
    private void getLastUpdateTs(String rtlWhCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(DB_PARAM_PROC_PGM_ID, BUSINESS_ID + rtlWhCd);

        List<Map<String, Object>> lastUpdateTsList = this.ssmBatchClient.queryObjectList("getLastUpdateTs", ssmParam);

        if (lastUpdateTsList == null || lastUpdateTsList.isEmpty()) {
            bizProcLogPk = null;
            bizLastUpdTs = DEF_TS_ZERO;
        } else {
            Map<String, Object> lastUpdateTsMap = lastUpdateTsList.get(0);
            bizProcLogPk = (BigDecimal) lastUpdateTsMap.get(DB_COLUMN_DS_BIZ_PROC_LOG_PK);
            bizLastUpdTs = (String) lastUpdateTsMap.get(DB_COLUMN_DS_BIZ_LAST_UPD_TS);
            if (!ZYPCommonFunc.hasValue(bizLastUpdTs)) {
                bizLastUpdTs = DEF_TS_ZERO;
            }
        }
    }

    /**
     * updateLastUpdateTs
     * @param   rtlWhCd String
     */
    private void updateLastUpdateTs(String rtlWhCd) {

        if (ZYPCommonFunc.hasValue(bizProcLogPk)) {
            // update 
            DS_BIZ_PROC_LOGTMsg tMsg = new DS_BIZ_PROC_LOGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcLogPk, bizProcLogPk);
            tMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcDt, batProcDate);
                ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT_17));
                ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, bizLastUpdTs);

                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NPAM1003E, new String[] {tMsg.getTableName(), DB_COLUMN_DS_BIZ_PROC_LOG_PK, tMsg.dsBizProcLogPk.getValue().toPlainString() });
                }
            }
        } else {
            // insert
            bizProcLogPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ);

            DS_BIZ_PROC_LOGTMsg tMsg = new DS_BIZ_PROC_LOGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcLogPk, bizProcLogPk);
            ZYPEZDItemValueSetter.setValue(tMsg.procPgmId, BUSINESS_ID + rtlWhCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcDt, batProcDate);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT_17));
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, bizLastUpdTs);

            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NPAM1499E, new String[] {tMsg.getTableName(), bizProcLogPk.toPlainString()});
            }
        }
    }

    /**
     * getHeaderInfo.
     * @param   rtlWhCd String
     * @return  headerInfo INSRC_RPT_WRKTMsg
     */
    private INSRC_RPT_WRKTMsg getHeaderInfo(String rtlWhCd) {

        INSRC_RPT_WRKTMsg headerInfo = new INSRC_RPT_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(headerInfo.glblCmpyCd, glblCmpyCd);
        insrcRptWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INSRC_RPT_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(headerInfo.insrcRptWrkPk, insrcRptWrkPk);
        ZYPEZDItemValueSetter.setValue(headerInfo.insrcRptPrintRqstSq, insrcRptWrkPk);
        ZYPEZDItemValueSetter.setValue(headerInfo.insrcRptTsDplyTxt, getDispLocalDtTm());

        Map<String, Object> sourceWhMap = getSourceWhInfo(rtlWhCd);
        ZYPEZDItemValueSetter.setValue(headerInfo.rtlWhNm, (String) sourceWhMap.get(DB_COLUMN_RTL_WH_NM));

        setSourcePartsCenterAdder(headerInfo, sourceWhMap);

        return headerInfo;
    }

    /**
     * getDispLocalDtTm.
     * @return  dispLocalDtTm String
     */
    private String getDispLocalDtTm() {
        return ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PRINT_FORMAT);
    }

    /**
     * getSourceWhInfo.
     * @param   rtlWhCd String
     * @return  sourceWhInfo Map<String, Object>
     */
    private Map<String, Object> getSourceWhInfo(String rtlWhCd) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(DB_PARAM_PROC_DT, batProcDate);
        queryParam.put(DB_PARAM_RTL_WH_CD, rtlWhCd);

        List<Map<String, Object>> result = this.ssmBatchClient.queryObjectList("getSourceWhInfo", queryParam);
        if (result == null || result.isEmpty()) {
            throw new S21AbendException(NPAM1328E, new String[] {ZYPCommonFunc.concatString("SourceWhInfo", "rtlWhCd=", rtlWhCd)});
        }
        return result.get(0);
    }

    /**
     * setSourcePartsCenterAdder
     * @param   insrcRptTMsg    INSRC_RPT_WRKTMsg
     * @param   sourceWhMap     Map<String, Object>
     */
    private void setSourcePartsCenterAdder(INSRC_RPT_WRKTMsg insrcRptTMsg, Map<String, Object> sourceWhMap) {

        String firstLineAddr = (String) sourceWhMap.get(DB_COLUMN_FIRST_LINE_ADDR);
        String scdLineAddr = (String) sourceWhMap.get(DB_COLUMN_SCD_LINE_ADDR);
        String thirdLineAddr = (String)sourceWhMap.get(DB_COLUMN_THIRD_LINE_ADDR);
        String frthLineAddr = (String) sourceWhMap.get(DB_COLUMN_FRTH_LINE_ADDR);
        String ctyAddr = (String) sourceWhMap.get(DB_COLUMN_CTY_ADDR);
        String stCd = (String) sourceWhMap.get(DB_COLUMN_ST_CD);
        String postCd = (String) sourceWhMap.get(DB_COLUMN_POST_CD);

        // Source Parts Center address list
        List<String> adderList = new ArrayList<String>();

        // check FirstLineAddr
        if (ZYPCommonFunc.hasValue(firstLineAddr) ) {
            adderList.add(firstLineAddr);
        }

        // check outScdLineAddr
        String outScdLineAddr = "";
        // check ScdLineAddr
        if (ZYPCommonFunc.hasValue(scdLineAddr) ) {
            outScdLineAddr = scdLineAddr;
        }
        // check ThirdLineAddr
        if (ZYPCommonFunc.hasValue(thirdLineAddr) ) {
            if (ZYPCommonFunc.hasValue(outScdLineAddr) ) {
                outScdLineAddr = outScdLineAddr + ONE_BLANK;
            }
            outScdLineAddr = outScdLineAddr + thirdLineAddr;
        }
        // check FrthLineAddr
        if (ZYPCommonFunc.hasValue(frthLineAddr) ) {
            if (ZYPCommonFunc.hasValue(outScdLineAddr) ) {
                outScdLineAddr = outScdLineAddr + ONE_BLANK;
            }
            outScdLineAddr = outScdLineAddr + frthLineAddr;
        }
        if (ZYPCommonFunc.hasValue(outScdLineAddr) ) {
            adderList.add(outScdLineAddr);
        }

        // check outThirdLineAddr
        String outThirdLineAddr = "";
        // check ctyAddr
        if (ZYPCommonFunc.hasValue(ctyAddr) ) {
            outThirdLineAddr = ctyAddr;
        }
        // check stCd
        if (ZYPCommonFunc.hasValue(stCd) ) {
            if (ZYPCommonFunc.hasValue(outThirdLineAddr) ) {
                outThirdLineAddr = outThirdLineAddr + COMMA;
            }
            outThirdLineAddr = outThirdLineAddr + stCd;
        }
        // check postCd
        if (ZYPCommonFunc.hasValue(postCd) ) {
            if (ZYPCommonFunc.hasValue(outThirdLineAddr) ) {
                outThirdLineAddr = outThirdLineAddr + ONE_BLANK;
            }
            outThirdLineAddr = outThirdLineAddr + postCd;
        }
        if (ZYPCommonFunc.hasValue(outThirdLineAddr) ) {
            adderList.add(outThirdLineAddr);
        }

        int addrLine = 0;
        // set FirstLineAddr
        if (adderList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(insrcRptTMsg.firstOthLineAddr, adderList.get(addrLine));
            addrLine++;
        }
        // set ScdLineAddr
        if (adderList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(insrcRptTMsg.scdThirdFrthLineAddr, adderList.get(addrLine));
            addrLine++;
        }
        // set ThirdLineAddr
        if (adderList.size() > addrLine ) {
            ZYPEZDItemValueSetter.setValue(insrcRptTMsg.fifthLineAddr, adderList.get(addrLine));
            addrLine++;
        }
    }

    /**
     * outputInsourcingData.
     * @param   headerInfo INSRC_RPT_WRKTMsg
     * @param   rptPrintCtrl RPT_PRINT_CTRLTMsg
     * @param   rtlWhCd String
     */
    private void outputInsourcingData(INSRC_RPT_WRKTMsg headerInfo, RPT_PRINT_CTRLTMsg rptPrintCtrl, String rtlWhCd) {

        // CSA Warehouses
        outputInsourcingData(headerInfo, rptPrintCtrl, rtlWhCd, INSOURCING_TO_CSA_WH);

        // 3PL Warehouses(DB Schenker)
        outputInsourcingData(headerInfo, rptPrintCtrl, rtlWhCd, INSOURCING_TO_3PL_WH_DB);

        // 3PL Warehouses(Others)
        outputInsourcingData(headerInfo, rptPrintCtrl, rtlWhCd, INSOURCING_TO_3PL_WH_OTHER);

        // Via Purchase Requisition
        outputInsourcingData(headerInfo, rptPrintCtrl, rtlWhCd, INSOURCING_PR);
    }

    /**
     * outputInsourcingData.
     * @param   headerInfo INSRC_RPT_WRKTMsg
     * @param   rptPrintCtrl RPT_PRINT_CTRLTMsg
     * @param   insourcingType String
     * @param   rtlWhCd String
     */
    private void outputInsourcingData(INSRC_RPT_WRKTMsg headerInfo, RPT_PRINT_CTRLTMsg rptPrintCtrl, String rtlWhCd, String insourcingType) {
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();
            // query cond set
            Map<String, Object> queryParam = setConditionInsourcingData(headerInfo, rptPrintCtrl, rtlWhCd, insourcingType);

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            // Get InsourcingError List
            stmt = this.ssmLLClient.createPreparedStatement("getInsourcedPartsRequest", queryParam, ssmEexcParam);
            result = stmt.executeQuery();

            while (result.next()) {
                INSRC_RPT_WRKTMsg detailInfo = new INSRC_RPT_WRKTMsg();
                EZDMsg.copy(headerInfo, null, detailInfo, null);

                if (BigDecimal.ZERO.compareTo(procCnt) < 0) {
                    insrcRptWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INSRC_RPT_WRK_SQ);
                    ZYPEZDItemValueSetter.setValue(detailInfo.insrcRptWrkPk, insrcRptWrkPk);
                }
                // set result
                setInsourcingData(detailInfo, result, insourcingType);

                EZDTBLAccessor.insert(detailInfo);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detailInfo.getReturnCode())) {
                    throw new S21AbendException(NPAM1499E, new String[] {detailInfo.getTableName(), insrcRptWrkPk.toPlainString()});
                }
                procCnt = procCnt.add(BigDecimal.ONE);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, result);
        }
    }

    /**
     * setConditionInsourcingData.
     * @param   headerInfo INSRC_RPT_WRKTMsg
     * @param   rptPrintCtrl RPT_PRINT_CTRLTMsg
     * @param   rtlWhCd String
     * @param   insourcingType String
     * @return  setConditionInsourcingData Map<String, Object>
     */
    private Map<String, Object> setConditionInsourcingData(INSRC_RPT_WRKTMsg headerInfo, RPT_PRINT_CTRLTMsg rptPrintCtrl, String rtlWhCd, String insourcingType) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        // common param
        queryParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(DB_PARAM_PRCH_REQ_REC_TP_TECH_REQUEST, PRCH_REQ_REC_TP.TECH_REQUEST);
        queryParam.put(DB_PARAM_PRCH_REQ_REC_TP_PO_REQUISITION, PRCH_REQ_REC_TP.PO_REQUISITION);
        queryParam.put(DB_PARAM_PRCH_REQ_SRC_TP_INSOURCING, PRCH_REQ_SRC_TP.INSOURCING);
        queryParam.put(DB_PARAM_RTL_WH_CD, rtlWhCd);
        List<String> prntVndCdList = null;
        if (ZYPCommonFunc.hasValue(bizProcLogPk)) {
            queryParam.put(DB_PARAM_BIZ_LAST_UPD_TS, bizLastUpdTs);
        } else {
            queryParam.put(DB_PARAM_BIZ_LAST_UPD_TS, null);
        }

        // insourcingType param select
        List<String> prchReqLineTpCdList = null;
        if (INSOURCING_TO_CSA_WH.equals(insourcingType)) {
            prchReqLineTpCdList = new ArrayList<String>();
            for (String prchReqLineTp : insrcToCsaWhList) {
                // Local Warehouse Print Check
                if (!ZYPConstant.FLG_ON_Y.equals(rptPrintCtrl.rptPrintCtrlValTxt_01.getValue()) && 
                        PRCH_REQ_LINE_TP.LOCAL.equals(prchReqLineTp)) {
                    continue;
                }
                prchReqLineTpCdList.add(prchReqLineTp);
            }
        } else if (INSOURCING_TO_3PL_WH_DB.equals(insourcingType)) {
            prchReqLineTpCdList = Arrays.asList(insrcTo3PlWhDBList);
        } else if (INSOURCING_TO_3PL_WH_OTHER.equals(insourcingType)) {
            prchReqLineTpCdList = Arrays.asList(insrcTo3PlWhOhterList);
        } else if (INSOURCING_PR.equals(insourcingType)) {
            prchReqLineTpCdList = Arrays.asList(insrcPRList);
            if (insrcPrExSplyList != null ) {
                prntVndCdList = Arrays.asList(insrcPrExSplyList);
            }
        }
        queryParam.put(DB_PARAM_PRCH_REQ_LINE_TP_CD_LIST, prchReqLineTpCdList);
        queryParam.put(DB_PARAM_PRNT_VND_CD_LIST, prntVndCdList);

        return queryParam;
    }

    /**
     * outputInsourcingErrorData.
     * @param   headerInfo INSRC_RPT_WRKTMsg
     * @param   rptPrintCtrl RPT_PRINT_CTRLTMsg
     * @param   rtlWhCd String
     */
    private void outputInsourcingErrorData(INSRC_RPT_WRKTMsg headerInfo, RPT_PRINT_CTRLTMsg rptPrintCtrl, String rtlWhCd) {
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            Map<String, String> queryParam = new HashMap<String, String>();
            queryParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(DB_PARAM_PRCH_REQ_REC_TP_TECH_REQUEST, PRCH_REQ_REC_TP.TECH_REQUEST);
            queryParam.put(DB_PARAM_RTL_WH_CD, rtlWhCd);

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            // Get InsourcingError List
            stmt = this.ssmLLClient.createPreparedStatement("getInsourcingError", queryParam, ssmEexcParam);
            result = stmt.executeQuery();

            while (result.next()) {
                INSRC_RPT_WRKTMsg detailInfo = new INSRC_RPT_WRKTMsg();
                EZDMsg.copy(headerInfo, null, detailInfo, null);

                if (BigDecimal.ZERO.compareTo(procCnt) < 0) {
                    insrcRptWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.INSRC_RPT_WRK_SQ);
                    ZYPEZDItemValueSetter.setValue(detailInfo.insrcRptWrkPk, insrcRptWrkPk);
                }
                // set result
                setInsourcingError(detailInfo, result);

                EZDTBLAccessor.insert(detailInfo);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detailInfo.getReturnCode())) {
                    throw new S21AbendException(NPAM1499E, new String[] {detailInfo.getTableName(), bizProcLogPk.toPlainString()});
                }
                procCnt = procCnt.add(BigDecimal.ONE);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, result);
        }
    }

    /**
     * setInsourcingData.
     * @param   detailInfo INSRC_RPT_WRKTMsg
     * @param   resultSet ResultSet
     * @param   insourcingType String
     */
    private void setInsourcingData(INSRC_RPT_WRKTMsg detailInfo, ResultSet resultSet, String insourcingType) throws SQLException {

        String prDplyVndNm_01 = null;
        String prDplyVndNm_02 = null;
        String prchReqDplyLineNum = null;
        String srcRtlWhDplyNm = null;
        String destRtlWhDplyNm = null;
        if (INSOURCING_TO_CSA_WH.equals(insourcingType)) {
            ZYPEZDItemValueSetter.setValue(detailInfo.csaPrchReqNum, resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_NUM));
            prchReqDplyLineNum = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_LINE_NUM), PERIOD, resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(detailInfo.csaPrchReqDplyLineNum, prchReqDplyLineNum);
            ZYPEZDItemValueSetter.setValue(detailInfo.csaOrigRqstMdseCd, resultSet.getString(DB_COLUMN_PRD2_ORIG_RQST_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(detailInfo.csaMdseCd, resultSet.getString(DB_COLUMN_PRD2_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(detailInfo.csaPrchReqTxt_01, convertQtyTxt(resultSet.getBigDecimal(DB_COLUMN_PRD1_PRCH_REQ_QTY)));
            ZYPEZDItemValueSetter.setValue(detailInfo.csaPrchReqTxt_02, convertQtyTxt(resultSet.getBigDecimal(DB_COLUMN_PRD2_PRCH_REQ_QTY)));
            srcRtlWhDplyNm = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD1_SRC_RTL_WH_CD), HYPHEN, resultSet.getString(DB_COLUMN_RWH1_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(detailInfo.csaSrcRtlWhDplyNm, srcRtlWhDplyNm);
            ZYPEZDItemValueSetter.setValue(detailInfo.csaSrcRtlSwhCd, resultSet.getString(DB_COLUMN_PRD1_SRC_RTL_SWH_CD));
            destRtlWhDplyNm = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_DEST_INVTY_LOC_CD), HYPHEN, resultSet.getString(DB_COLUMN_RWH3_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(detailInfo.csaDestRtlWhDplyNm, destRtlWhDplyNm);
        } else if (INSOURCING_TO_3PL_WH_DB.equals(insourcingType) || INSOURCING_TO_3PL_WH_OTHER.equals(insourcingType)) {
            ZYPEZDItemValueSetter.setValue(detailInfo.prchReqNum, resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_NUM));
            prchReqDplyLineNum = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_LINE_NUM), PERIOD, resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(detailInfo.prchReqDplyLineNum, prchReqDplyLineNum);
            ZYPEZDItemValueSetter.setValue(detailInfo.origRqstMdseCd, resultSet.getString(DB_COLUMN_PRD2_ORIG_RQST_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(detailInfo.mdseCd, resultSet.getString(DB_COLUMN_PRD2_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(detailInfo.prchReqTxt_01, convertQtyTxt(resultSet.getBigDecimal(DB_COLUMN_PRD1_PRCH_REQ_QTY)));
            ZYPEZDItemValueSetter.setValue(detailInfo.prchReqTxt_02, convertQtyTxt(resultSet.getBigDecimal(DB_COLUMN_PRD2_PRCH_REQ_QTY)));
            srcRtlWhDplyNm = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD1_SRC_RTL_WH_CD), HYPHEN, resultSet.getString(DB_COLUMN_RWH1_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(detailInfo.srcRtlWhDplyNm, srcRtlWhDplyNm);
            ZYPEZDItemValueSetter.setValue(detailInfo.srcRtlSwhCd, resultSet.getString(DB_COLUMN_PRD1_SRC_RTL_SWH_CD));
            destRtlWhDplyNm = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_DEST_INVTY_LOC_CD), HYPHEN, resultSet.getString(DB_COLUMN_RWH3_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(detailInfo.destRtlWhDplyNm, destRtlWhDplyNm);
        } else if (INSOURCING_PR.equals(insourcingType)) {
            ZYPEZDItemValueSetter.setValue(detailInfo.prPrchReqNum, resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_NUM));
            prchReqDplyLineNum = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_LINE_NUM), PERIOD, resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_LINE_SUB_NUM));
            ZYPEZDItemValueSetter.setValue(detailInfo.prPrchReqDplyLineNum, prchReqDplyLineNum);
            ZYPEZDItemValueSetter.setValue(detailInfo.prOrigRqstMdseCd, resultSet.getString(DB_COLUMN_PRD2_ORIG_RQST_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(detailInfo.prMdseCd, resultSet.getString(DB_COLUMN_PRD2_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(detailInfo.prPrchReqTxt_01, convertQtyTxt(resultSet.getBigDecimal(DB_COLUMN_PRD1_PRCH_REQ_QTY)));
            ZYPEZDItemValueSetter.setValue(detailInfo.prPrchReqTxt_02, convertQtyTxt(resultSet.getBigDecimal(DB_COLUMN_PRD2_PRCH_REQ_QTY)));
            srcRtlWhDplyNm = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_SRC_RTL_WH_CD), HYPHEN, resultSet.getString(DB_COLUMN_RWH1_RTL_WH_NM));
            prDplyVndNm_01 = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_PRNT_VND_CD), HYPHEN, resultSet.getString(DB_COLUMN_PV1_PRNT_VND_NM));
            ZYPEZDItemValueSetter.setValue(detailInfo.prDplyVndNm_01, prDplyVndNm_01);
            prDplyVndNm_02 = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_VND_CD), HYPHEN, resultSet.getString(DB_COLUMN_VD1_LOC_NM));
            ZYPEZDItemValueSetter.setValue(detailInfo.prDplyVndNm_02, prDplyVndNm_02);
            ZYPEZDItemValueSetter.setValue(detailInfo.prInsrcPrchReqNum, resultSet.getString(DB_COLUMN_PRD3_PRCH_REQ_NUM));
            ZYPEZDItemValueSetter.setValue(detailInfo.prInsrcPoOrdNum, resultSet.getString(DB_COLUMN_PRD3_PO_ORD_NUM));
            destRtlWhDplyNm = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_DEST_INVTY_LOC_CD), HYPHEN, resultSet.getString(DB_COLUMN_RWH3_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(detailInfo.prDestRtlWhDplyNm, destRtlWhDplyNm);
        }
        ZYPEZDItemValueSetter.setValue(detailInfo.insrcRptLineNum, procCnt.add(BigDecimal.ONE));

        String prchReqCratDtTm = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_CRAT_DT), resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_CRAT_TM), null);
        // compare datetime
        if (bizLastUpdTs.compareTo(prchReqCratDtTm) < 0) {
            bizLastUpdTs = prchReqCratDtTm;
        }

        // Insourced Line count update
        addInsrcLineCnt(resultSet);
    }

    /**
     * setInsourcingError.
     * @param   detailInfo INSRC_RPT_WRKTMsg
     * @param   resultSet ResultSet
     */
    private void setInsourcingError(INSRC_RPT_WRKTMsg detailInfo, ResultSet resultSet) throws SQLException {
        ZYPEZDItemValueSetter.setValue(detailInfo.lePrchReqNum, resultSet.getString(DB_COLUMN_PRH2_PRCH_REQ_NUM));
        String prchReqDplyLineNum;
        prchReqDplyLineNum = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_LINE_NUM), PERIOD, resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(detailInfo.lePrchReqDplyLineNum, prchReqDplyLineNum);
        ZYPEZDItemValueSetter.setValue(detailInfo.leOrigRqstMdseCd, resultSet.getString(DB_COLUMN_PRD2_ORIG_RQST_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(detailInfo.lePrchReqTxt_01, convertQtyTxt(resultSet.getBigDecimal(DB_COLUMN_PRD2_PRCH_REQ_QTY)));
        ZYPEZDItemValueSetter.setValue(detailInfo.lePrchReqTxt_02, convertQtyTxt(resultSet.getBigDecimal(DB_COLUMN_PRD2_PRCH_REQ_INSRC_QTY)));
        String srcRtlWhDplyNm;
        srcRtlWhDplyNm = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_SRC_RTL_WH_CD), HYPHEN, resultSet.getString(DB_COLUMN_RWH1_RTL_WH_NM));
        ZYPEZDItemValueSetter.setValue(detailInfo.leSrcRtlWhDplyNm, srcRtlWhDplyNm);
        ZYPEZDItemValueSetter.setValue(detailInfo.leSrcRtlSwhCd, resultSet.getString(DB_COLUMN_PRD2_SRC_RTL_SWH_CD));
        String destRtlWhDplyNm;
        destRtlWhDplyNm = ZYPCommonFunc.concatString(resultSet.getString(DB_COLUMN_PRD2_DEST_INVTY_LOC_CD), HYPHEN, resultSet.getString(DB_COLUMN_RWH3_RTL_WH_NM));
        ZYPEZDItemValueSetter.setValue(detailInfo.leDestRtlWhDplyNm, destRtlWhDplyNm);
        ZYPEZDItemValueSetter.setValue(detailInfo.lePrchReqRelErrMsgTxt, resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_REL_ERR_MSG_TXT));
        ZYPEZDItemValueSetter.setValue(detailInfo.insrcRptLineNum, procCnt.add(BigDecimal.ONE));
    }

    /**
     * convertQtyTxt.
     * @param   qtyNum BigDecimal
     * @return  qtyTxt String
     */
    private String convertQtyTxt(BigDecimal qtyNum) {
        String qtyTxt = null;
        if (ZYPCommonFunc.hasValue(qtyNum)) {
            qtyTxt = qtyNum.toPlainString();
        }
        return qtyTxt;
    }

    /**
     * addInsrcLineCnt.
     * @param   resultSet   ResultSet
     */
    private void addInsrcLineCnt(ResultSet resultSet) throws SQLException {
        String prchReqLineTpCd = resultSet.getString(DB_COLUMN_PRD2_PRCH_REQ_LINE_TP_CD);
        BigDecimal qtyNum = BigDecimal.ONE; //resultSet.getBigDecimal(DB_COLUMN_PRD2_PRCH_REQ_QTY);
        if (ZYPCommonFunc.hasValue(qtyNum)) {
            if (PRCH_REQ_LINE_TP.INSOURCED_PD.equals(prchReqLineTpCd)) {
                csaListInsrcCnt = csaListInsrcCnt.add(qtyNum);
            } else if (PRCH_REQ_LINE_TP.INSOURCED_DB.equals(prchReqLineTpCd)) {
                cbListInsrcCnt = cbListInsrcCnt.add(qtyNum);
            } else if (PRCH_REQ_LINE_TP.INSOURCED_CH.equals(prchReqLineTpCd)) {
                chListInsrcCnt = chListInsrcCnt.add(qtyNum);
            } else if (PRCH_REQ_LINE_TP.INSOURCED_PO.equals(prchReqLineTpCd)) {
                String prntVndCd = resultSet.getString(DB_COLUMN_PRD2_PRNT_VND_CD);
                if (ZYPCommonFunc.hasValue(prntVndCd) && ZYPCommonFunc.hasValue(smryInsrcPRSplyCSV)) {
                    if (smryInsrcPRSplyCSV.indexOf(prntVndCd) >= 0) {
                        cusaListInsrcCnt = cusaListInsrcCnt.add(qtyNum);
                    }
                }
            }
        }
    }

    /**
     * updateInsrcLineCnt.
     */
    private void updateInsrcLineCnt() {

        if (insrcRptWrkPk != null) {
            // update 
            INSRC_RPT_WRKTMsg tMsg = new INSRC_RPT_WRKTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.insrcRptWrkPk, insrcRptWrkPk);
            tMsg = (INSRC_RPT_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.csaListInsrcTxt, csaListInsrcCnt.toPlainString());
                ZYPEZDItemValueSetter.setValue(tMsg.cbListInsrcTxt, cbListInsrcCnt.toPlainString());
                ZYPEZDItemValueSetter.setValue(tMsg.chListInsrcTxt, chListInsrcCnt.toPlainString());
                ZYPEZDItemValueSetter.setValue(tMsg.cusaListInsrcTxt, cusaListInsrcCnt.toPlainString());

                EZDTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NPAM1003E, new String[] {tMsg.getTableName(), DB_COLUMN_INSRC_RPT_WRK_PK, tMsg.insrcRptWrkPk.getValue().toPlainString() });
                }
            }
        }
    }

    /**
     * writeReport
     * @param insrcRptPrintRqstSq BigDecimal
     */
    private void writeReport(BigDecimal insrcRptPrintRqstSq, RPT_PRINT_CTRLTMsg rptPrintCtrl) {

        // Generate S21ReportRequestBean
        String rtlWhcd = rptPrintCtrl.rptPrintCtrlId.getValue();
        S21ReportRequestBean requestBean = new S21ReportRequestBean(RPT_ID_NPAF0040);
        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        String rptTtlNm = ZYPCommonFunc.concatString(REPORT_TITLE, ONE_BLANK, rtlWhcd);
        rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, ONE_BLANK, insrcRptPrintRqstSq.toPlainString());
        requestBean.setRptTtlNm(rptTtlNm);

        // Set Report Input Parameter
        S21InputParameter inputParam = requestBean.getInputParamBeanInstance();
        inputParam.addReportParameter(GLBL_CMPY_CD, this.glblCmpyCd);
        inputParam.addReportParameter(INSRC_RPT_PRINT_RQST_SQ, insrcRptPrintRqstSq.toPlainString());
        inputParam.addReportParameter(INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter(DB_COLUMN_RTL_WH_CD, rtlWhcd);
        requestBean.setInputParamBean(inputParam);

        // Direct Print
        if (ZYPConstant.FLG_ON_Y.equals(directPrint)) {
            // Set Report Output Parameter
            S21PrinterOutputParameter outputParam = requestBean.getPrintOutParamBeanInstance();
            outputParam.setBranchNo(rptPrintCtrl.rptBrId.getValue());
        }

        // Create Request
        long requestPk = this.service.createReportByAsync(requestBean);
        if (requestPk != 0) {
            printCnt++;
        }
    }

    /**
     * Remove Purge Record
     */
    private void removeInsrcRptWrk() {

       //Check Target Date Record
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        String purgeDtTm = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT_14);
        purgeDtTm = ZYPDateUtil.addDays(purgeDtTm, purgeDt.negate().intValue());
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        paramMap.put(DB_PARAM_PRNT_PURGE_TS, purgeDtTm);

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setMaxRows(0);
            preparedStatement = ssmLLClient.createPreparedStatement("getRemoveInsrcRptWrkData", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                INSRC_RPT_WRKTMsg tMsg = new INSRC_RPT_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.insrcRptWrkPk, rs.getBigDecimal(DB_COLUMN_INSRC_RPT_WRK_PK));

                // Delete Target Date Record
                EZDTBLAccessor.remove(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    throw new S21AbendException(NPAM1342E, new String[] {tMsg.getTableName()});
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, rs);
        }
    }
}
