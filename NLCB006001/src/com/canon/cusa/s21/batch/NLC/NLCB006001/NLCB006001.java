/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB006001;

import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.ADJ_SUBMIT_TS;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.BUSINESS_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.CONST_NLCB0060_PHYS_INVTY_NO_ADJ_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.CONST_NLCB0060_PHYS_INVTY_NO_ADJ_TXT;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.CONST_NLCB0060_PRINT_PHYS_INVTY_CNT_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.CONST_NLCB0060_PRINT_PHYS_INVTY_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.CONST_NLCF0020_LINE_NUM_OF_1ST_PAGE;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.CONST_NLCF0020_LINE_NUM_OF_2ND_PAGE;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.CONST_NLCF0020_NUM_OF_DESC_LTR;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.CONST_NLCF0020_PURGE_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DATE_BEGININDEX;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DATE_ENDINDEX;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DATE_TIME_FORMAT_14;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DATE_TIME_FORMAT_17;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_DMI_MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_DS_BIZ_LAST_UPD_TS;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_DS_BIZ_PROC_LOG_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_OFA_TOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_ORG_BR_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_ORG_ORG_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_ORG_RG_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIC_ADJ_GRS_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIC_ADJ_NET_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIC_ADJ_SUBMT_TS;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIC_PHYS_INVTY_CTRL_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIC_RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIC_TECH_TOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIC_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIH_ADJ_VAR_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIH_ADJ_VAR_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIH_INVTY_AVAL_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIH_LTST_CNT_INP_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_PIH_MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_COLUMN_TECH_PI_RSLT_RPT_WRK_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_ADJ_SUBMT_TS;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_BIZ_AREA_ORG_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_BR_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_GNRN_TP_CD_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_ORG_STRU_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_PHYS_INVTY_CNT_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_PHYS_INVTY_NO_ADJ_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_PHYS_INVTY_NO_ADJ_TXT;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_PHYS_INVTY_STS_CD_LIST;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_PRNT_PURGE_TS;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_PROC_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_PROC_PGM_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_PSN_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DB_PARAM_RG_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DEFAULT_AMOUNT_SACALE;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DEF_TS_ZERO;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.DETAIL_UOM_VALUE;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.HAIFUN;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.INTL_LANG_VAL_COL_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.NPAM0078E;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.NPAM1003E;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.NPAM1342E;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.NPAM1499E;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.ONE_HUNDRED;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.PERCENT_AMOUNT_SACALE;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.PHYS_INVTY_CTRL_NM;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.REPORT_LOGICAL_PRINT_KEY_DELIMITER;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.RPT_ID_NLCF0020;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.RPT_PRINT_RQST_SQ;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.SLASH;
import static com.canon.cusa.s21.batch.NLC.NLCB006001.constant.NLCB006001Constant.TWO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.TECH_PI_RSLT_RPT_WRKTMsg;

import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NLCB0060:Tech PI Result Printing Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/28/2016   CITS            Y.Fujii         Create          R308
 * 05/24/2018   CITS            K.Kameoka       Create          QC#10572
 * 08/02/2018   CITS            Y.Iwasaki       Update          QC#27451
 * 09/20/2018   CITS            K.Ogino         Update          QC#28192
 * 10/04/2018   CITS            K.Ogino         Update          QC#28192
 * 10/16/2018   CITS            M.Naito         Update          QC#28770
 * 01/24/2019   Fujitsu         T.Ogura         Update          QC#29984
 * 02/04/2019   Fujitsu         T.Ogura         Update          QC#30184
 * 04/16/2019   CITS            A.Kobayashi     Update          QC#31144
 * 02/19/2020   Fujitsu         R.Nakamura      Update          QC#55938
 * 03/03/2020   Fujitsu         R.Nakamura      Update          QC#56060
 *</pre>
 */
public class NLCB006001 extends S21BatchMain {

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
    private int procCnt;

    /** Total Printing Service count. */
    private int totalPrintCount;

    /** report work table purge date. */
    private BigDecimal purgeDt;

    /** Line Number of 1st Page. */
    private BigDecimal lineNumOf1stPage;

    /** Line Number of 2nd Page. */
    private BigDecimal lineNumOf2ndPage;

    /** Description Maximum number of characters. */
    private BigDecimal numOfDescLtr;

    /** page number. */
    private BigDecimal pageNum;

    /** line number. */
    private BigDecimal lineNum;

    /** Total System Value. */
    private BigDecimal totalSysValueAmt;

    /** Total Count Value. */
    private BigDecimal totalCntValueAmt;

    /** Total No Change Value. */
    private BigDecimal totalNoChngValueAmt;

    /** Variance Lines. */
    private BigDecimal totalVarLineCnt;

    /** Unchanged Lines. */
    private BigDecimal totalUnchLineCnt;

    /** Total Count Quantity. */
    private BigDecimal totalLtstCntInpQty;

    /** Total Adjustment Quantity. */
    private BigDecimal totalAdjVarQty;

    /** Target Sub Warehouse Code. */
    private String targetSwhCd;

    /** Total Gross Variance. */
    private BigDecimal totalAdjGrsAmt;

    /** Total Net Variance. */
    private BigDecimal totalAdjNetAmt;

    /** Adjustment Date. */
    private String adjSubmtTs;

    /** detailInfoList. */
    private List<TECH_PI_RSLT_RPT_WRKTMsg> detailInfoList = new ArrayList<TECH_PI_RSLT_RPT_WRKTMsg>();

    // QC#10572 Start.
    private String[] physInvtyStsCdList = null;
    // QC#10572 End.

    // START 2018/10/16 M.Naito [QC#28770, ADD]
    /** physInvtyCntStsCdList. */
    private String[] physInvtyCntStsCdList = null;

    // END 2018/10/16 M.Naito [QC#28770, ADD]

    // QC#28192
    private String[] physInvtyNoAdjStsCdList = null;

    private String physInvtyNoAdjTxt = null;

    /**
     * main.
     * @param args  String[]
     */
    public static void main(String[] args) {
        new NLCB006001().executeBatch(NLCB006001.class.getSimpleName());
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

        // get Pk, LastUpdateTs
        getLastUpdateTs();

        // QC#31144 inform the time for processing data from last task (this message can be changed)
        S21InfoLogOutput.println("TARGET DATA : " + bizLastUpdTs);

        // purge work table
        removeTechPiRsltRptWrk();

        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            queryParam.put(DB_PARAM_LOC_TP_CD, LOC_TP.TECHNICIAN);
            if (ZYPCommonFunc.hasValue(bizProcLogPk)) {
                queryParam.put(DB_PARAM_ADJ_SUBMT_TS, bizLastUpdTs);
            }
            // START 2018/10/16 M.Naito [QC#28770, MOD]
            //QC#10572 mod Start
//            String physInvtyStsCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NLCB0060_PRINT_PHYS_INVTY_STS_CD, glblCmpyCd);
//            if (ZYPCommonFunc.hasValue(physInvtyStsCd)) {
//                this.physInvtyStsCdList = null;
//            } else {
//                this.physInvtyStsCdList = physInvtyStsCd.split(",");
//            }
            queryParam.put(DB_PARAM_PHYS_INVTY_STS_CD_LIST, physInvtyStsCdList);
            // QC#28192
//            String physInvtyNoAdjStsCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NLCB0060_PHYS_INVTY_NO_ADJ_STS_CD, glblCmpyCd);
//            if (!ZYPCommonFunc.hasValue(physInvtyNoAdjStsCd)) {
//                this.physInvtyNoAdjStsCdList = null;
//            } else {
//                this.physInvtyNoAdjStsCdList = physInvtyNoAdjStsCd.split(",");
//            }
            queryParam.put(DB_PARAM_PHYS_INVTY_NO_ADJ_STS_CD_LIST, physInvtyNoAdjStsCdList);

            String constPhysInvtyNoAdjTxt = ZYPCodeDataUtil.getVarCharConstValue(CONST_NLCB0060_PHYS_INVTY_NO_ADJ_TXT, glblCmpyCd);
            if (!ZYPCommonFunc.hasValue(constPhysInvtyNoAdjTxt)) {
                this.physInvtyNoAdjTxt = "There is No Adjustment Records.";
            } else {
                this.physInvtyNoAdjTxt = constPhysInvtyNoAdjTxt;
            }
            queryParam.put(DB_PARAM_PHYS_INVTY_NO_ADJ_TXT, physInvtyNoAdjTxt);

            queryParam.put(DB_PARAM_PHYS_INVTY_CNT_STS_CD_LIST, physInvtyCntStsCdList);
            // END 2018/10/16 M.Naito [QC#28770, MOD]

//            queryParam.put(DB_PARAM_PHYS_INVTY_STS_CD, PHYS_INVTY_STS.CLOSE);
//            List<String> physInvtyCntStsCdList = new ArrayList<String>();
//            physInvtyCntStsCdList.add(PHYS_INVTY_CNT_STS.PI_COMPLETED);
//            physInvtyCntStsCdList.add(PHYS_INVTY_CNT_STS.PI_VARIANCE);
//            queryParam.put(DB_PARAM_PHYS_INVTY_CNT_STS_CD_LIST, physInvtyCntStsCdList);

            //QC#10572 mod Start

            S21SsmExecutionParameter ssmEexcParam = new S21SsmExecutionParameter();

            ssmEexcParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmEexcParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);

            // Get PIResult List
            stmt = this.ssmLLClient.createPreparedStatement("getPIResult", queryParam, ssmEexcParam);
            resultSet = stmt.executeQuery();

            // Initialize detail Info
            initTechPIDetailInfo();

            TECH_PI_RSLT_RPT_WRKTMsg headerInfo = null;
            String printKeyCd = "";
            String currentkeyCd = "";

            // Initialize printing service object.
            this.service = new S21EIPPrintingService();

            while (resultSet.next()) {

                currentkeyCd = getReportLogicalPrintKey(resultSet);
                // same technician check
                if (headerInfo == null) {
                    // first result only
                    headerInfo = getHeaderInfo(resultSet);
                    printKeyCd = currentkeyCd;
                } else {
                    if (!currentkeyCd.equals(printKeyCd)) {
                        // output report work
                        outputTechPiReportWork(headerInfo);
                        totalPrintCount++;

                        // Initialize detail Info
                        initTechPIDetailInfo();

                        // other technician PI Header Create
                        headerInfo = getHeaderInfo(resultSet);
                        printKeyCd = currentkeyCd;
                    }
                }

                mainProcess(headerInfo, resultSet);

                procCnt++;
                totCnt++;
            }

            if (procCnt > 0) {
                // output report work
                outputTechPiReportWork(headerInfo);
                totalPrintCount++;
            }

            if (totalPrintCount > 0) {
                updateLastUpdateTs();
                commit();
            }

            // QC#31144 inform the time to finish and next time processing (this message can be changed)
            S21InfoLogOutput.println("NEXT TIME TARGET DATA : " + bizLastUpdTs);

            termCd = TERM_CD.NORMAL_END;

        } catch (SQLException e) {
            sqlExceptionHandler(e);
//        } catch (S21AbendException e) {
//            rollback();
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
     * Check the DB parameters. If an error occurs, throws
     * Exception.
     */
    private void checkDBParams() {

        /** report work table purge date. */
        purgeDt = ZYPCodeDataUtil.getNumConstValue(CONST_NLCF0020_PURGE_DT, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(purgeDt)) {
            noValueError(new String[] {CONST_NLCF0020_PURGE_DT});
        }

        lineNumOf1stPage = ZYPCodeDataUtil.getNumConstValue(CONST_NLCF0020_LINE_NUM_OF_1ST_PAGE, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(lineNumOf1stPage)) {
            noValueError(new String[] {CONST_NLCF0020_LINE_NUM_OF_1ST_PAGE});
        }

        lineNumOf2ndPage = ZYPCodeDataUtil.getNumConstValue(CONST_NLCF0020_LINE_NUM_OF_2ND_PAGE, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(lineNumOf2ndPage)) {
            noValueError(new String[] {CONST_NLCF0020_LINE_NUM_OF_2ND_PAGE});
        }

        numOfDescLtr = ZYPCodeDataUtil.getNumConstValue(CONST_NLCF0020_NUM_OF_DESC_LTR, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(numOfDescLtr)) {
            noValueError(new String[] {CONST_NLCF0020_NUM_OF_DESC_LTR});
        }

        // START 2018/10/16 M.Naito [QC#28770, ADD]
        String physInvtyStsCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NLCB0060_PRINT_PHYS_INVTY_STS_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(physInvtyStsCd)) {
            noValueError(new String[] {CONST_NLCB0060_PRINT_PHYS_INVTY_STS_CD});
        } else {
            this.physInvtyStsCdList = physInvtyStsCd.split(",");
        }

        String physInvtyNoAdjStsCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NLCB0060_PHYS_INVTY_NO_ADJ_STS_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(physInvtyNoAdjStsCd)) {
            noValueError(new String[] {CONST_NLCB0060_PHYS_INVTY_NO_ADJ_STS_CD});
        } else {
            this.physInvtyNoAdjStsCdList = physInvtyNoAdjStsCd.split(",");
        }

        String physInvtyCntStsCd = ZYPCodeDataUtil.getVarCharConstValue(CONST_NLCB0060_PRINT_PHYS_INVTY_CNT_STS_CD, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(physInvtyCntStsCd)) {
            noValueError(new String[] {CONST_NLCB0060_PRINT_PHYS_INVTY_CNT_STS_CD});
        } else {
            this.physInvtyCntStsCdList = physInvtyCntStsCd.split(",");
        }
        // END 2018/10/16 M.Naito [QC#28770, ADD]
    }

    /**
     * No Value Error
     * @param item Error Item
     */
    private void noValueError(String[] item) {
        throw new S21AbendException(NPAM0078E, item);
    }

    /**
     * getLastUpdateTs
     */
    private void getLastUpdateTs() {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(DB_PARAM_PROC_PGM_ID, BUSINESS_ID);

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
     */
    private void updateLastUpdateTs() {

        if (ZYPCommonFunc.hasValue(bizProcLogPk)) {
            // update 
            DS_BIZ_PROC_LOGTMsg tMsg = new DS_BIZ_PROC_LOGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcLogPk, bizProcLogPk);
            tMsg = (DS_BIZ_PROC_LOGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);

            if (tMsg != null) {
                ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcDt, batProcDate);
                ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastProcTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT_17));
                // START 2018/10/16 M.Naito [QC#28770, MOD]
//                ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, bizLastUpdTs);
                // Mod Start 2020/02/19 QC#55938
//                ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, ZYPDateUtil.getCurrentSystemTime(DATE_TIME_FORMAT_17));
                ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, bizLastUpdTs);
                // Mod End 2020/02/19 QC#55938
                // END 2018/10/16 M.Naito [QC#28770, MOD]

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
            ZYPEZDItemValueSetter.setValue(tMsg.procPgmId, BUSINESS_ID);
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
     * Main Process.
     * @param   headerInfo  TECH_PI_RSLT_RPT_WRKTMsg
     * @param   resultSet   ResultSet
     */
    private void mainProcess(TECH_PI_RSLT_RPT_WRKTMsg headerInfo, ResultSet resultSet) throws SQLException {

        TECH_PI_RSLT_RPT_WRKTMsg detailInfo = new TECH_PI_RSLT_RPT_WRKTMsg();
        EZDMsg.copy(headerInfo, null, detailInfo, null);

        // set detail info result set
        setDetailInfo(detailInfo, resultSet);

        // list add
        detailInfoList.add(detailInfo);

    }


    /**
     * getHeaderInfo.
     * @param   resultSet   ResultSet
     * @return  headerInfo  TECH_PI_RSLT_RPT_WRKTMsg
     */
    private TECH_PI_RSLT_RPT_WRKTMsg getHeaderInfo(ResultSet resultSet) throws SQLException {

        TECH_PI_RSLT_RPT_WRKTMsg headerInfo = new TECH_PI_RSLT_RPT_WRKTMsg();

        ZYPEZDItemValueSetter.setValue(headerInfo.glblCmpyCd, glblCmpyCd);
        BigDecimal techPiRsltRptWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_PI_RSLT_RPT_WRK_SQ);
        ZYPEZDItemValueSetter.setValue(headerInfo.techPiRsltRptWrkPk, techPiRsltRptWrkPk);
        ZYPEZDItemValueSetter.setValue(headerInfo.rptPrintRqstSq, techPiRsltRptWrkPk);
        ZYPEZDItemValueSetter.setValue(headerInfo.rptPrintDtTxt, ZYPDateUtil.formatEzd8ToSysDisp(batProcDate));
        String techCd = resultSet.getString(DB_COLUMN_PIC_TECH_TOC_CD);
        ZYPEZDItemValueSetter.setValue(headerInfo.techTocCd, techCd);
        ZYPEZDItemValueSetter.setValue(headerInfo.physInvtyCtrlNm, resultSet.getString(DB_COLUMN_PIC_PHYS_INVTY_CTRL_NM));
        ZYPEZDItemValueSetter.setValue(headerInfo.svcOrgNmDplyTxt, getRegionAndBranch(techCd));
        adjSubmtTs = resultSet.getString(DB_COLUMN_PIC_ADJ_SUBMT_TS);
        if (ZYPCommonFunc.hasValue(adjSubmtTs)) {
            // compare adjust date time
            if (bizLastUpdTs.compareTo(adjSubmtTs) < 0) {
                bizLastUpdTs = adjSubmtTs;
            }
            String adjSubmtDt = getDateFromTimestamp(adjSubmtTs);
            // Add Start 2020/03/03 QC#56060
            ZYPEZDItemValueSetter.setValue(headerInfo.rptPrintDtTxt, ZYPDateUtil.formatEzd8ToSysDisp(adjSubmtDt));
            // Add End 2020/03/03 QC#56060
            ZYPEZDItemValueSetter.setValue(headerInfo.adjDtTxt, ZYPDateUtil.formatEzd8ToSysDisp(adjSubmtDt));
        }

        return headerInfo;
    }

    /**
     * getRegionAndBranch
     * @param   techCd          String
     * @return  regionAndBranch String
     */
    private String getRegionAndBranch(String techCd) {

        String regionAndBranch = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        List<String> gnrnTpCdList = new ArrayList<String>();
        gnrnTpCdList.add(GNRN_TP.CURRENT);
        gnrnTpCdList.add(GNRN_TP.FUTURE);
        ssmParam.put(DB_PARAM_GNRN_TP_CD_LIST, gnrnTpCdList);
        ssmParam.put(DB_PARAM_ORG_STRU_TP_CD, ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        ssmParam.put(DB_PARAM_BIZ_AREA_ORG_CD, BIZ_AREA_ORG.SERVICE);
        ssmParam.put(DB_PARAM_BR_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(DB_PARAM_RG_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(DB_PARAM_PSN_CD, techCd);
        ssmParam.put(DB_PARAM_PROC_DT, batProcDate);

        List<Map<String, Object>> regionAndBranchList = this.ssmBatchClient.queryObjectList("getRegionAndBranch", ssmParam);

        String region = "";
        String branch = "";
        if (regionAndBranchList != null && !regionAndBranchList.isEmpty()) {
            String preTocCd = "";
            String ofaTocCd = "";
            String orgOrgNm = "";
            String orgRgFlg = "";
            String orgBrFlg = "";

            for (Map<String, Object> regAndBrnMap : regionAndBranchList) {
                ofaTocCd = (String) regAndBrnMap.get(DB_COLUMN_OFA_TOC_CD);
                orgOrgNm = (String) regAndBrnMap.get(DB_COLUMN_ORG_ORG_NM);
                orgRgFlg = (String) regAndBrnMap.get(DB_COLUMN_ORG_RG_FLG);
                orgBrFlg = (String) regAndBrnMap.get(DB_COLUMN_ORG_BR_FLG);

                if (!ZYPCommonFunc.hasValue(preTocCd)) {
                    preTocCd = ofaTocCd;
                }

                if (!preTocCd.equals(ofaTocCd)) {
                    break;
                }

                if (ZYPConstant.FLG_ON_Y.equals(orgRgFlg)) {
                    region = orgOrgNm;
                } else if (ZYPConstant.FLG_ON_Y.equals(orgBrFlg)) {
                    branch = orgOrgNm;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(region)) {
            regionAndBranch = region;
        }

        if (ZYPCommonFunc.hasValue(branch)) {
            if (ZYPCommonFunc.hasValue(regionAndBranch)) {
                regionAndBranch = ZYPCommonFunc.concatString(regionAndBranch, SLASH, branch);
            } else {
                regionAndBranch = branch;
            }
        }
        return regionAndBranch;
    }

    /**
     * Remove Purge Record
     */
    private void removeTechPiRsltRptWrk() {

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
            preparedStatement = ssmLLClient.createPreparedStatement("getRemoveTechPiRsltRptWrkData", paramMap, execParam);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                TECH_PI_RSLT_RPT_WRKTMsg tMsg = new TECH_PI_RSLT_RPT_WRKTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.techPiRsltRptWrkPk, rs.getBigDecimal(DB_COLUMN_TECH_PI_RSLT_RPT_WRK_PK));

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

    /**
     * getUnitPriceAmount
     * @param   resultSet   ResultSet
     * @return  unitPrcAmt  BigDecimal
     */
    private BigDecimal getUnitPriceAmount(ResultSet resultSet) throws SQLException {

        NLXC001001GetInventoryItemCostBean bean = new NLXC001001GetInventoryItemCostBean();

        bean.setGlblCmpyCd(glblCmpyCd);
        //PHYS_INVTY_CTRL.WH_CD(PHYS_INVTY_CTRL.RTL_WH_CD + PHYS_INVTY_CTRL.RTL_SWH_CD)
        String invtyLocCd = resultSet.getString(DB_COLUMN_PIC_WH_CD);
        bean.setInvtyLocCd(invtyLocCd);
        String msdnCd = resultSet.getString(DB_COLUMN_PIH_MDSE_CD);
        bean.setMdseCd(msdnCd);
        bean.setQty(BigDecimal.ONE);

        bean = NLXC001001GetInventoryItemCost.getInventoryItemCost(bean);

        return bean.getUnitPrcAmt();
    }

    /**
     * multiplyAmount
     * @param   one     BigDecimal
     * @param   two    BigDecimal
     * @return  result  BigDecimal
     */
    private BigDecimal multiplyAmount(BigDecimal one, BigDecimal two) {
        return multiply(one, two, DEFAULT_AMOUNT_SACALE, RoundingMode.HALF_UP);
    }

    /**
     * getPercentage
     * @param   value       BigDecimal
     * @param   divisor     BigDecimal
     * @return  result      BigDecimal
     */
    private BigDecimal getPercentage(BigDecimal value, BigDecimal divisor) {

        BigDecimal calcValue = null;

        if (ZYPCommonFunc.hasValue(value)) {
            calcValue = value.multiply(ONE_HUNDRED);
        }
        return divide(calcValue, divisor, PERCENT_AMOUNT_SACALE, RoundingMode.HALF_UP);
    }

    /**
     * divide
     * @param   value       BigDecimal
     * @param   divisor     BigDecimal
     * @return  result      BigDecimal
     */
    private BigDecimal divide(BigDecimal value, BigDecimal divisor) {
        if (!ZYPCommonFunc.hasValue(value) || !ZYPCommonFunc.hasValue(divisor)) {
            return BigDecimal.ZERO;
        } else if (BigDecimal.ZERO.compareTo(divisor) == 0) {
            return BigDecimal.ZERO;
        }
        return divide(value, divisor, DEFAULT_AMOUNT_SACALE, RoundingMode.HALF_UP);
    }


    /**
     * divide
     * @param   value       BigDecimal
     * @param   divisor     BigDecimal
     * @param   scale       int
     * @param   rm          RoundingMode
     * @return  result      BigDecimal
     */
    private BigDecimal divide(BigDecimal value, BigDecimal divisor, int scale, RoundingMode rm) {
        if (!ZYPCommonFunc.hasValue(value) || !ZYPCommonFunc.hasValue(divisor)) {
            return BigDecimal.ZERO;
        } else if (BigDecimal.ZERO.compareTo(divisor) == 0) {
            return BigDecimal.ZERO;
        }
        return value.divide(divisor, scale, rm);
    }

    /**
     * multiply
     * @param   one     BigDecimal
     * @param   two     BigDecimal
     * @param   scale   int
     * @param   rm      RoundingMode
     * @return  result  BigDecimal
     */
    private BigDecimal multiply(BigDecimal one, BigDecimal two, int scale, RoundingMode rm) {
        if (!ZYPCommonFunc.hasValue(one) || !ZYPCommonFunc.hasValue(two)) {
            return BigDecimal.ZERO;
        }
        BigDecimal calcResult = one.multiply(two);
        BigDecimal result = calcResult.setScale(scale, rm);
        return result;
    }
    /**
     * setPdfPageNumber
     * @param   detailTMsg  TECH_PI_RSLT_RPT_WRKTMsg
     */
    private void setPdfPageNumber(TECH_PI_RSLT_RPT_WRKTMsg detailTMsg) {

        BigDecimal maxLineNum;
        if (BigDecimal.ONE.compareTo(pageNum) == 0) {
            maxLineNum = lineNumOf1stPage;
        } else {
            maxLineNum = lineNumOf2ndPage;
        }

        // calculation add line
        BigDecimal lineDescLength = new BigDecimal(detailTMsg.mdseDescShortTxt.getValue().length());
        BigDecimal addLineNum = BigDecimal.ZERO.add(lineDescLength.divide(numOfDescLtr, 0, BigDecimal.ROUND_UP));

        // add line
        lineNum = lineNum.add(addLineNum);

        // Page break decision
        if (maxLineNum.compareTo(lineNum) < 0) {
            pageNum = pageNum.add(BigDecimal.ONE);
            lineNum = addLineNum;
        }

        ZYPEZDItemValueSetter.setValue(detailTMsg.techPiRsltRptPgNum, pageNum);
    }

    /**
     * setDetailInfo.
     * @param   detailTMsg  TECH_PI_RSLT_RPT_WRKTMsg
     * @param   resultSet   ResultSet
     */
    private void setDetailInfo(TECH_PI_RSLT_RPT_WRKTMsg detailInfo, ResultSet resultSet) throws SQLException {
        BigDecimal cost = getUnitPriceAmount(resultSet);
        ZYPEZDItemValueSetter.setValue(detailInfo.mdseCd, resultSet.getString(DB_COLUMN_PIH_MDSE_CD));
        ZYPEZDItemValueSetter.setValue(detailInfo.mdseDescShortTxt, resultSet.getString(DB_COLUMN_DMI_MDSE_DESC_SHORT_TXT));
        ZYPEZDItemValueSetter.setValue(detailInfo.whCd, resultSet.getString(DB_COLUMN_PIC_WH_CD));
        ZYPEZDItemValueSetter.setValue(detailInfo.pkgUomCd, DETAIL_UOM_VALUE);
        BigDecimal pihInvtyAvalQty = getQtyOrAmount(resultSet, DB_COLUMN_PIH_INVTY_AVAL_QTY);
        ZYPEZDItemValueSetter.setValue(detailInfo.sysQtyDplyTxt, convertText(pihInvtyAvalQty));
        BigDecimal pihLtstCntInpQty = getQtyOrAmount(resultSet, DB_COLUMN_PIH_LTST_CNT_INP_QTY);
        ZYPEZDItemValueSetter.setValue(detailInfo.cntQtyDplyTxt, convertText(pihLtstCntInpQty));
        BigDecimal pihAdjVarQty = getQtyOrAmount(resultSet, DB_COLUMN_PIH_ADJ_VAR_QTY);
        ZYPEZDItemValueSetter.setValue(detailInfo.adjQtyDplyTxt, convertText(pihAdjVarQty));
        BigDecimal sysValueAmt = multiplyAmount(pihInvtyAvalQty, cost);
        // START 2019/02/04 T.Ogura [QC#30184,MOD]
//        ZYPEZDItemValueSetter.setValue(detailInfo.sysValTxt, convertText(sysValueAmt));
        ZYPEZDItemValueSetter.setValue(detailInfo.sysValTxt, convertAmount(sysValueAmt));
        // END   2019/02/04 T.Ogura [QC#30184,MOD]
        BigDecimal cntValueAmt = multiplyAmount(pihLtstCntInpQty, cost);
        // START 2019/02/04 T.Ogura [QC#30184,MOD]
//        ZYPEZDItemValueSetter.setValue(detailInfo.cntValTxt, convertText(cntValueAmt));
        ZYPEZDItemValueSetter.setValue(detailInfo.cntValTxt, convertAmount(cntValueAmt));
        // END   2019/02/04 T.Ogura [QC#30184,MOD]
        BigDecimal adjValueAmt = multiplyAmount(pihAdjVarQty, cost);
        // START 2019/02/04 T.Ogura [QC#30184,MOD]
//        ZYPEZDItemValueSetter.setValue(detailInfo.adjValTxt, convertText(adjValueAmt));
        ZYPEZDItemValueSetter.setValue(detailInfo.adjValTxt, convertAmount(adjValueAmt));
        // END   2019/02/04 T.Ogura [QC#30184,MOD]
        BigDecimal adjPct = getPercentage(pihAdjVarQty, pihLtstCntInpQty);
        // START 2019/02/04 T.Ogura [QC#30184,MOD]
//        ZYPEZDItemValueSetter.setValue(detailInfo.adjPctDplyTxt, convertText(adjPct));
        ZYPEZDItemValueSetter.setValue(detailInfo.adjPctDplyTxt, convertPercent(adjPct));
        // END   2019/02/04 T.Ogura [QC#30184,MOD]

        // set PDF Page Number
        setPdfPageNumber(detailInfo);

        // add total item value
        totalSysValueAmt = add(totalSysValueAmt, sysValueAmt);
        totalCntValueAmt = add(totalCntValueAmt, cntValueAmt);
        String pihAdjVarFlg = resultSet.getString(DB_COLUMN_PIH_ADJ_VAR_FLG);
        if (ZYPConstant.FLG_ON_Y.equals(pihAdjVarFlg))  {
            totalVarLineCnt = add(totalVarLineCnt, BigDecimal.ONE);
        // START 2019/01/24 T.Ogura [QC#29984,MOD]
//        } else {
//            totalUnchLineCnt = add(totalUnchLineCnt, BigDecimal.ONE);
//            totalNoChngValueAmt = add(totalNoChngValueAmt, cntValueAmt);
//        }
        } else if (ZYPConstant.FLG_OFF_N.equals(pihAdjVarFlg)) {
            totalUnchLineCnt = add(totalUnchLineCnt, BigDecimal.ONE);
            totalNoChngValueAmt = add(totalNoChngValueAmt, cntValueAmt);
        } else {
            // There is No Adjustment Records.
        }
        // END   2019/01/24 T.Ogura [QC#29984,MOD]
        String rtlSwhCd = resultSet.getString(DB_COLUMN_PIC_RTL_SWH_CD);
        // other sub warehouse check
        if (rtlSwhCd.compareTo(targetSwhCd) != 0) {
            BigDecimal adjGrsAmt = getQtyOrAmount(resultSet, DB_COLUMN_PIC_ADJ_GRS_AMT);
            totalAdjGrsAmt = add(totalAdjGrsAmt, adjGrsAmt);
            BigDecimal adjNetAmt = getQtyOrAmount(resultSet, DB_COLUMN_PIC_ADJ_NET_AMT);
            totalAdjNetAmt = add(totalAdjNetAmt, adjNetAmt);
            targetSwhCd = rtlSwhCd;
        }
        totalLtstCntInpQty = add(totalLtstCntInpQty, pihLtstCntInpQty);
        totalAdjVarQty = add(totalAdjVarQty, pihAdjVarQty);
        String adjSubmtTs = resultSet.getString(DB_COLUMN_PIC_ADJ_SUBMT_TS);
        if (ZYPCommonFunc.hasValue(adjSubmtTs)) {
            // compare adjust date time
            if (bizLastUpdTs.compareTo(adjSubmtTs) < 0) {
                bizLastUpdTs = adjSubmtTs;
            }
            String adjSubmtDt = getDateFromTimestamp(adjSubmtTs);
            ZYPEZDItemValueSetter.setValue(detailInfo.adjDtTxt, ZYPDateUtil.formatEzd8ToSysDisp(adjSubmtDt));
        }
    }

    /**
     * convertText.
     * @param   value  BigDecimal
     * @return  text   String
     */
    private String convertText(BigDecimal value) {
        BigDecimal num = value;
        if (!ZYPCommonFunc.hasValue(value)) {
            num = BigDecimal.ZERO;
        }
        return ZYPFormatUtil.formatNumToSysDisp(num);
    }

    // START 2019/02/04 T.Ogura [QC#30184,ADD]
    /**
     * convertAmount.
     * @param   value  BigDecimal
     * @return  String
     */
    private String convertAmount(BigDecimal value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            value = BigDecimal.ZERO;
        }
        String str = ZYPCommonFunc.toEditShosu(value.toPlainString(), DEFAULT_AMOUNT_SACALE);
        return ZYPFormatUtil.formatNumToSysDisp(new BigDecimal(str));
    }
    // END   2019/02/04 T.Ogura [QC#30184,ADD]

    // START 2019/02/04 T.Ogura [QC#30184,ADD]
    /**
     * convertPercent.
     * @param   value  BigDecimal
     * @return  String
     */
    private String convertPercent(BigDecimal value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            value = BigDecimal.ZERO;
        }
        String str = ZYPCommonFunc.toEditShosu(value.toPlainString(), PERCENT_AMOUNT_SACALE);
        return ZYPFormatUtil.formatNumToSysDisp(new BigDecimal(str));
    }
    // END   2019/02/04 T.Ogura [QC#30184,ADD]

    /**
     * initTechPIDetailInfo.
     */
    private void initTechPIDetailInfo() {
        // detail list clear
        detailInfoList.clear();

        // total data clear
        totalSysValueAmt = BigDecimal.ZERO;
        totalCntValueAmt = BigDecimal.ZERO;
        totalNoChngValueAmt = BigDecimal.ZERO;
        totalVarLineCnt = BigDecimal.ZERO;
        totalUnchLineCnt = BigDecimal.ZERO;
        totalLtstCntInpQty = BigDecimal.ZERO;
        totalAdjVarQty = BigDecimal.ZERO;
        totalAdjGrsAmt = BigDecimal.ZERO;
        totalAdjNetAmt = BigDecimal.ZERO;

        pageNum = BigDecimal.ONE;
        lineNum = BigDecimal.ZERO;
        procCnt = 0;

        // Addition Sub Warehouse Clear
        targetSwhCd = "";
    }

    /**
     * add
     * @param   one     BigDecimal
     * @param   two     BigDecimal
     * @return  result  BigDecimal
     */
    private BigDecimal add(BigDecimal one, BigDecimal two) {
        BigDecimal num1 = one;
        if (!ZYPCommonFunc.hasValue(num1)) {
            num1 = BigDecimal.ZERO;
        }
        BigDecimal num2 = two;
        if (!ZYPCommonFunc.hasValue(num2)) {
            num2 = BigDecimal.ZERO;
        }

        return num1.add(num2);
    }

    /**
     * setTotalToHeaderInfo.
     * @param   headerInfo  TECH_PI_RSLT_RPT_WRKTMsg
     */
    private void setTotalToHeaderInfo(TECH_PI_RSLT_RPT_WRKTMsg headerInfo) {
        // START 2019/02/04 T.Ogura [QC#30184,MOD]
//        ZYPEZDItemValueSetter.setValue(headerInfo.totSysValTxt, convertText(totalSysValueAmt));
//        ZYPEZDItemValueSetter.setValue(headerInfo.totCntValTxt, convertText(totalCntValueAmt));
        ZYPEZDItemValueSetter.setValue(headerInfo.totSysValTxt, convertAmount(totalSysValueAmt));
        ZYPEZDItemValueSetter.setValue(headerInfo.totCntValTxt, convertAmount(totalCntValueAmt));
        // END   2019/02/04 T.Ogura [QC#30184,MOD]
        BigDecimal totAdjVal = totalCntValueAmt.subtract(totalSysValueAmt);
        // START 2019/02/04 T.Ogura [QC#30184,MOD]
//        ZYPEZDItemValueSetter.setValue(headerInfo.totAdjValTxt, convertText(totAdjVal));
        ZYPEZDItemValueSetter.setValue(headerInfo.totAdjValTxt, convertAmount(totAdjVal));
        // END   2019/02/04 T.Ogura [QC#30184,MOD]
        BigDecimal noChngValIdx = calcNoChangeValueIndex();
        ZYPEZDItemValueSetter.setValue(headerInfo.noChngValIdxTxt, convertText(noChngValIdx));
        // START 2019/02/04 T.Ogura [QC#30184,MOD]
//        ZYPEZDItemValueSetter.setValue(headerInfo.noChngValTxt, convertText(totalNoChngValueAmt));
        ZYPEZDItemValueSetter.setValue(headerInfo.noChngValTxt, convertAmount(totalNoChngValueAmt));
        // END   2019/02/04 T.Ogura [QC#30184,MOD]
        BigDecimal totCntLine = add(totalVarLineCnt, totalUnchLineCnt);
        ZYPEZDItemValueSetter.setValue(headerInfo.totCntLineTxt, convertText(totCntLine));
        ZYPEZDItemValueSetter.setValue(headerInfo.varLineTxt, convertText(totalVarLineCnt));
        ZYPEZDItemValueSetter.setValue(headerInfo.unchLineTxt, convertText(totalUnchLineCnt));
        // START 2019/02/04 T.Ogura [QC#30184,MOD]
//        ZYPEZDItemValueSetter.setValue(headerInfo.adjGrsTxt, convertText(totalAdjGrsAmt));
//        ZYPEZDItemValueSetter.setValue(headerInfo.adjNetTxt, convertText(totalAdjNetAmt));
        ZYPEZDItemValueSetter.setValue(headerInfo.adjGrsTxt, convertAmount(totalAdjGrsAmt));
        ZYPEZDItemValueSetter.setValue(headerInfo.adjNetTxt, convertAmount(totalAdjNetAmt));
        // END   2019/02/04 T.Ogura [QC#30184,MOD]
    }

    /**
     * calcNoChangeValueIndex.
     * @return  result  BigDecimal
     */
    private BigDecimal calcNoChangeValueIndex() {

        BigDecimal totCntLine = add(totalVarLineCnt, totalUnchLineCnt);
        // QC#28192
        BigDecimal calcWk01 = divide(totalUnchLineCnt, totCntLine, 3, RoundingMode.HALF_DOWN);

        BigDecimal calcWk02 = divide(totalNoChngValueAmt, totalSysValueAmt, 3, RoundingMode.HALF_DOWN);

        BigDecimal calcWk03 = add(calcWk01, calcWk02);

        BigDecimal calcWk07 = divide(calcWk03, TWO, 3, RoundingMode.HALF_DOWN);

        BigDecimal calcWk08 = multiply(calcWk07, ONE_HUNDRED, 1, RoundingMode.HALF_UP);

        return calcWk08;
    }

    /**
     * outputTechPiReportWork.
     * @param   headerInfo  TECH_PI_RSLT_RPT_WRKTMsg
     */
    private void outputTechPiReportWork(TECH_PI_RSLT_RPT_WRKTMsg headerInfo) {
        // set total item to headerInfo
        setTotalToHeaderInfo(headerInfo);

        int insCnt = 0;
        for (TECH_PI_RSLT_RPT_WRKTMsg detailInfo : detailInfoList) {
            // header info copy
            copyHeaderInfoToDetailInfo(headerInfo, detailInfo);
            if (insCnt > 0) {
                BigDecimal rptWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.TECH_PI_RSLT_RPT_WRK_SQ);
                ZYPEZDItemValueSetter.setValue(detailInfo.techPiRsltRptWrkPk, rptWrkPk);
            }

            EZDTBLAccessor.insert(detailInfo);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detailInfo.getReturnCode())) {
                throw new S21AbendException(NPAM1499E, new String[] {detailInfo.getTableName(), headerInfo.techPiRsltRptWrkPk.getValue().toPlainString()});
            }
            insCnt++;
        }

        writeReport(headerInfo);
    }

    /**
     * copyHeaderInfoToDetailInfo.
     * @param   headerInfo  TECH_PI_RSLT_RPT_WRKTMsg
     * @param   detailInfo  TECH_PI_RSLT_RPT_WRKTMsg
     */
    private void copyHeaderInfoToDetailInfo(TECH_PI_RSLT_RPT_WRKTMsg headerInfo, TECH_PI_RSLT_RPT_WRKTMsg detailInfo) {

        // Total Header Info copy
        ZYPEZDItemValueSetter.setValue(detailInfo.totSysValTxt, headerInfo.totSysValTxt);
        ZYPEZDItemValueSetter.setValue(detailInfo.totCntValTxt, headerInfo.totCntValTxt);
        ZYPEZDItemValueSetter.setValue(detailInfo.totAdjValTxt, headerInfo.totAdjValTxt);
        ZYPEZDItemValueSetter.setValue(detailInfo.noChngValIdxTxt, headerInfo.noChngValIdxTxt);
        ZYPEZDItemValueSetter.setValue(detailInfo.noChngValTxt, headerInfo.noChngValTxt);
        ZYPEZDItemValueSetter.setValue(detailInfo.totCntLineTxt, headerInfo.totCntLineTxt);
        ZYPEZDItemValueSetter.setValue(detailInfo.varLineTxt, headerInfo.varLineTxt);
        ZYPEZDItemValueSetter.setValue(detailInfo.unchLineTxt, headerInfo.unchLineTxt);
        ZYPEZDItemValueSetter.setValue(detailInfo.adjGrsTxt, headerInfo.adjGrsTxt);
        ZYPEZDItemValueSetter.setValue(detailInfo.adjNetTxt, headerInfo.adjNetTxt);

    }

    /**
     * writeReport
     * @param headerInfo    TECH_PI_RSLT_RPT_WRKTMsg
     */
    private void writeReport(TECH_PI_RSLT_RPT_WRKTMsg headerInfo) {

        // Generate S21ReportRequestBean
        S21ReportRequestBean requestBean = new S21ReportRequestBean(RPT_ID_NLCF0020);
        requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        String techTocCd = headerInfo.techTocCd.getValue();
        String physInvtyCtrlNm = headerInfo.physInvtyCtrlNm.getValue();
        String rptTtlNm = ZYPCommonFunc.concatString(techTocCd, HAIFUN, physInvtyCtrlNm);
        rptTtlNm = ZYPCommonFunc.concatString(rptTtlNm, HAIFUN, adjSubmtTs);
        requestBean.setRptTtlNm(rptTtlNm);

        // Set Report Input Parameter
        S21InputParameter inputParam = requestBean.getInputParamBeanInstance();
        inputParam.addReportParameter(GLBL_CMPY_CD, this.glblCmpyCd);
        inputParam.addReportParameter(RPT_PRINT_RQST_SQ, headerInfo.rptPrintRqstSq.getValue().toPlainString());
        inputParam.addReportParameter(INTL_LANG_VAL_COL_NM, inputParam.getSystemDefaultLanguage());
        inputParam.addReportParameter(RTL_WH_CD, techTocCd);
        inputParam.addReportParameter(PHYS_INVTY_CTRL_NM, physInvtyCtrlNm);
        inputParam.addReportParameter(ADJ_SUBMIT_TS, adjSubmtTs);
        requestBean.setInputParamBean(inputParam);

        // Create Request
        this.service.createReportByAsync(requestBean);
    }

    /**
     * getQtyOrAmount.
     * @param   resultSet   ResultSet
     * @param   dbColumn    String
     * @return  value       BigDecimal
     */
    private BigDecimal getQtyOrAmount(ResultSet resultSet, String dbColumn) throws SQLException {

        BigDecimal value = resultSet.getBigDecimal(dbColumn);

        if (!ZYPCommonFunc.hasValue(value)) {
            value = BigDecimal.ZERO;
        }

        return value;
    }

    /**
     * getDateFromTimestamp.
     * @param   timestamp   String
     * @return  date        String
     */
    private String getDateFromTimestamp(String timestamp) {

        String  date = "";

        if (ZYPCommonFunc.hasValue(timestamp)) {
            date = timestamp.substring(DATE_BEGININDEX, DATE_ENDINDEX);
        }

        return date;
    }

    /**
     * getReportLogicalPrintKey.
     * @param   resultSet   ResultSet
     * @return  key         String
     */
    private String getReportLogicalPrintKey(ResultSet resultSet) throws SQLException {

        String techCd = resultSet.getString(DB_COLUMN_PIC_TECH_TOC_CD);
        String physInvtyCtrlNm = resultSet.getString(DB_COLUMN_PIC_PHYS_INVTY_CTRL_NM);

        String key = techCd;
        if (ZYPCommonFunc.hasValue(key) && ZYPCommonFunc.hasValue(physInvtyCtrlNm)) {
            key = ZYPCommonFunc.concatString(key, REPORT_LOGICAL_PRINT_KEY_DELIMITER , physInvtyCtrlNm);
        }

        return key;
    }
}
