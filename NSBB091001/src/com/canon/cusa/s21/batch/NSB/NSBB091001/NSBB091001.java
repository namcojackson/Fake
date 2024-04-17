package com.canon.cusa.s21.batch.NSB.NSBB091001;

import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.CHRG_COPY_VOL;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.CM_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.CNF_YR_PRT_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.COPIER;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.COPR_MACH_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.FSR_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.INT_SECONDS;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.INV_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.K_CLICK_PRT_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.LABOR_RATE;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.LTST_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_COPY_VOL;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_ISTL_SCD_VISIT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_ISTL_VISIT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_NI_ITRPT_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_NI_LBOR_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_NI_SCD_VISIT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_NI_SCD_VISIT_ITRPT_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_NI_SCD_VISIT_LBOR_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_NI_SCD_VISIT_PRT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_NI_SCD_VISIT_TRVL_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_NI_TRVL_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_NI_VISIT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_CM_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_FSR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_ITRPT_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_ITRPT_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_LBOR_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_LBOR_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_PRT_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_RTRN_VISIT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_TRVL_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_TRVL_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MLY_TOT_VISIT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MSG_ITEM_DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.MSG_ITEM_BATCH_DATE;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_APPROVED;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_BEFORE_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_COPIER;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_EFFECTIVE;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_INSTALL_CALL;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_LABOR;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_LABOR_RATE;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_MEAL_2;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_SEC_PERIOD;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_SERVICE_CALL;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_SIX_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_THREE_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_TIME_ADJ;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_TRAVEL;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_TWELVE_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PARAM_YM_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.PREV_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.RGTN_MTR_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SCD_VISIT_RATIO;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SIX_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_AVG_TRVL_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_BY_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_INV_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_LBOR_COST_UNIT_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_LBOR_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_LBOR_DEAL_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_LBOR_FUNC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_LBOR_FUNC_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_PRT_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_PRT_DEAL_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_PRT_FUNC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_PRT_FUNC_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_TRVL_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_TRVL_DEAL_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_TRVL_FUNC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.SVC_TRVL_FUNC_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TECH_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.THREE_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TIME_ADJ_VAL;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TM_AND_MAT_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_CM_CNT_01;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_CM_CNT_03;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_CM_CNT_06;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_CM_CNT_12;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_MTR_VOL_01;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_MTR_VOL_03;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_MTR_VOL_06;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_MTR_VOL_12;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_VISIT_CNT_01;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_VISIT_CNT_03;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_VISIT_CNT_06;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TOT_VISIT_CNT_12;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.TWELVE_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.VISIT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.YM_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NSB.NSBB091001.constant.NSBB091001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_MLY_FSR_BY_MACHTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FSR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SUPPL_TASK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TM_EVENT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Monthly FSR By Machine Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/25/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NSBB091001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Batch Process Date */
    private String batchDt;

    /** Target Year Month */
    private String trgtYrMth;

    /** Commit Count */
    private int commitCount;

    /** Insert Count */
    private int normalRecCnt;

    /** Fetch Count */
    private int totalRecCnt;

    /** Error Count */
    private int errRecCnt;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NSBB091001().executeBatch(NSBB091001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {

        // Initialize
        this.normalRecCnt = 0;
        this.errRecCnt = 0;
        this.totalRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Commit Count
        this.commitCount = getCommitCount();
        if (this.commitCount <= 0 || this.commitCount >= DEFAULT_COMMIT_COUNT) {
            this.commitCount = DEFAULT_COMMIT_COUNT;
        }

        // Get Batch Process Date
        this.batchDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.batchDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BATCH_DATE });
        }

        // Get Target Year Month
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.batchDt);
        this.trgtYrMth = (String) ssmBatchClient.queryObject("getTrgtYrMth", queryParam);
        if (!ZYPCommonFunc.hasValue(this.trgtYrMth)) {
            throw new S21AbendException(ZZZM9006E, new String[] {MSG_ITEM_DWH_TRGT_YR_MTH });
        }

    }

    @Override
    protected void mainRoutine() {

        // Check Target Year Month Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtYrMthRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Year Month Recored
            FCT_MLY_FSR_BY_MACHTMsg tMsg = new FCT_MLY_FSR_BY_MACHTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg });
            }
        }

        // Get Main Data For FCT_MLY_FSR_BY_MACH Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        queryParam.put(PARAM_YM_LEN, YM_LEN);
        queryParam.put(PARAM_COPIER, COPIER);
        queryParam.put(PARAM_EFFECTIVE, DS_CONTR_STS.EFFECTIVE);
        queryParam.put(PARAM_APPROVED, DS_CONTR_STS.APPROVED);
        queryParam.put(PARAM_SERVICE_CALL, FSR_TP.SERVICE_CALL);
        queryParam.put(PARAM_INSTALL_CALL, FSR_TP.INSTALL_CALL);
        queryParam.put(PARAM_LABOR_RATE, LABOR_RATE);
        queryParam.put(PARAM_SEC_PERIOD, INT_SECONDS);
        queryParam.put(PARAM_LABOR, SVC_TM_EVENT.LABOR);
        queryParam.put(PARAM_TRAVEL, SVC_TM_EVENT.TRAVEL);
        queryParam.put(PARAM_MEAL_2, SVC_SUPPL_TASK_TP.MEAL_2);
        queryParam.put(PARAM_TIME_ADJ, TIME_ADJ_VAL);
        queryParam.put(PARAM_THREE_MTH, THREE_MTH);
        queryParam.put(PARAM_SIX_MTH, SIX_MTH);
        queryParam.put(PARAM_TWELVE_MTH, TWELVE_MTH);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMainData", queryParam, execParam);
            rs = stmt.executeQuery();

            // Loop for Main data
            int wkInsertCount = 0;
            while (rs.next()) {
                this.totalRecCnt++;

                // Map Result Set Data To FCT_MLY_FSR_BY_MACH
                FCT_MLY_FSR_BY_MACHTMsg tMsg = mapData(rs);

                // Get Additional Technician Level Data
                if (ZYPCommonFunc.hasValue(tMsg.techCd)) {
                    // Get Previous Meter Count data
                    String ltstMtrDt = rs.getString(RGTN_MTR_DT);
                    if (ZYPCommonFunc.hasValue(ltstMtrDt)) {
                        getPrevMtrCntData(tMsg, ltstMtrDt);
                    }
                    // Get MCBV/MCBF data
                    getMcbvMcbfData(tMsg);
                }

                // Insert FCT_MLY_FSR_BY_MACH
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", SVC_MACH_MSTR_PK, "=", rs.getString(SVC_MACH_MSTR_PK), ", ", SER_NUM, "=", rs.getString(SER_NUM));
                    S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg });
                    this.errRecCnt++;
                }

                // Commit By Commit Point
                wkInsertCount++;
                if (this.errRecCnt == 0 && wkInsertCount == this.commitCount) {
                    commit();
                    this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                    wkInsertCount = 0;
                }

            } // End Loop

            // Commit By Last Data
            if (this.errRecCnt == 0 && wkInsertCount > 0) {
                this.normalRecCnt = this.normalRecCnt + wkInsertCount;
                commit();
            } else {
                rollback();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * Map Result Set Data To FCT_MLY_FSR_BY_MACH
     */
    private FCT_MLY_FSR_BY_MACHTMsg mapData(ResultSet rs) throws SQLException {
        FCT_MLY_FSR_BY_MACHTMsg tMsg = new FCT_MLY_FSR_BY_MACHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
        BigDecimal fctMlyFsrByMachSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_MLY_FSR_BY_MACH_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.fctMlyFsrByMachPk, fctMlyFsrByMachSq);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.techCd, rs.getString(TECH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, rs.getString(SVC_BY_LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, rs.getBigDecimal(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.coprMachFlg, rs.getString(COPR_MACH_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.tmAndMatFlg, rs.getString(TM_AND_MAT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.prevReadMtrCnt, rs.getBigDecimal(PREV_READ_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.ltstReadMtrCnt, rs.getBigDecimal(LTST_READ_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyCopyVol, rs.getBigDecimal(MLY_COPY_VOL));
        ZYPEZDItemValueSetter.setValue(tMsg.svcInvNum, rs.getString(SVC_INV_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.invDt, rs.getString(INV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.chrgCopyVol, rs.getBigDecimal(CHRG_COPY_VOL));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotFsrCnt, rs.getBigDecimal(MLY_TOT_FSR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotCmCnt, rs.getBigDecimal(MLY_TOT_CM_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotVisitCnt, rs.getBigDecimal(MLY_TOT_VISIT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotRtrnVisitCnt, rs.getBigDecimal(MLY_TOT_RTRN_VISIT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.scdVisitRatio, rs.getBigDecimal(SCD_VISIT_RATIO));
        ZYPEZDItemValueSetter.setValue(tMsg.svcAvgTrvlAot, rs.getBigDecimal(SVC_AVG_TRVL_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborCostUnitAmt, rs.getBigDecimal(SVC_LBOR_COST_UNIT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotTrvlAot, rs.getBigDecimal(MLY_TOT_TRVL_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotLborAot, rs.getBigDecimal(MLY_TOT_LBOR_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotItrptAot, rs.getBigDecimal(MLY_TOT_ITRPT_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotTrvlCostAmt, rs.getBigDecimal(MLY_TOT_TRVL_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotLborCostAmt, rs.getBigDecimal(MLY_TOT_LBOR_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotItrptCostAmt, rs.getBigDecimal(MLY_TOT_ITRPT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotPrtCostAmt, rs.getBigDecimal(MLY_TOT_PRT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyTotCostAmt, rs.getBigDecimal(MLY_TOT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyNiVisitCnt, rs.getBigDecimal(MLY_NI_VISIT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyNiTrvlAot, rs.getBigDecimal(MLY_NI_TRVL_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyNiLborAot, rs.getBigDecimal(MLY_NI_LBOR_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyNiItrptAot, rs.getBigDecimal(MLY_NI_ITRPT_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyNiScdVisitCnt, rs.getBigDecimal(MLY_NI_SCD_VISIT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyNiScdVisitTrvlAot, rs.getBigDecimal(MLY_NI_SCD_VISIT_TRVL_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyNiScdVisitLborAot, rs.getBigDecimal(MLY_NI_SCD_VISIT_LBOR_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyNiScdVisitItrptAot, rs.getBigDecimal(MLY_NI_SCD_VISIT_ITRPT_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyNiScdVisitPrtCnt, rs.getBigDecimal(MLY_NI_SCD_VISIT_PRT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyIstlVisitCnt, rs.getBigDecimal(MLY_ISTL_VISIT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mlyIstlScdVisitCnt, rs.getBigDecimal(MLY_ISTL_SCD_VISIT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.cnfYrPrtCostAmt, rs.getBigDecimal(CNF_YR_PRT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborDealAmt, rs.getBigDecimal(SVC_LBOR_DEAL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborFuncAmt, rs.getBigDecimal(SVC_LBOR_FUNC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborDealDiscAmt, rs.getBigDecimal(SVC_LBOR_DEAL_DISC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborFuncDiscAmt, rs.getBigDecimal(SVC_LBOR_FUNC_DISC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTrvlDealAmt, rs.getBigDecimal(SVC_TRVL_DEAL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTrvlFuncAmt, rs.getBigDecimal(SVC_TRVL_FUNC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTrvlDealDiscAmt, rs.getBigDecimal(SVC_TRVL_DEAL_DISC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTrvlFuncDiscAmt, rs.getBigDecimal(SVC_TRVL_FUNC_DISC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPrtDealAmt, rs.getBigDecimal(SVC_PRT_DEAL_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPrtFuncAmt, rs.getBigDecimal(SVC_PRT_FUNC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPrtDealDiscAmt, rs.getBigDecimal(SVC_PRT_DEAL_DISC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPrtFuncDiscAmt, rs.getBigDecimal(SVC_PRT_FUNC_DISC_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.k_ClickPrtCostAmt, rs.getBigDecimal(K_CLICK_PRT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.totVisitCnt_01, rs.getBigDecimal(TOT_VISIT_CNT_01));
        ZYPEZDItemValueSetter.setValue(tMsg.totCmCnt_01, rs.getBigDecimal(TOT_CM_CNT_01));
        ZYPEZDItemValueSetter.setValue(tMsg.totMtrVol_01, rs.getBigDecimal(TOT_MTR_VOL_01));
        ZYPEZDItemValueSetter.setValue(tMsg.totVisitCnt_03, rs.getBigDecimal(TOT_VISIT_CNT_03));
        ZYPEZDItemValueSetter.setValue(tMsg.totCmCnt_03, rs.getBigDecimal(TOT_CM_CNT_03));
        ZYPEZDItemValueSetter.setValue(tMsg.totMtrVol_03, rs.getBigDecimal(TOT_MTR_VOL_03));
        ZYPEZDItemValueSetter.setValue(tMsg.totVisitCnt_06, rs.getBigDecimal(TOT_VISIT_CNT_06));
        ZYPEZDItemValueSetter.setValue(tMsg.totCmCnt_06, rs.getBigDecimal(TOT_CM_CNT_06));
        ZYPEZDItemValueSetter.setValue(tMsg.totMtrVol_06, rs.getBigDecimal(TOT_MTR_VOL_06));
        ZYPEZDItemValueSetter.setValue(tMsg.totVisitCnt_12, rs.getBigDecimal(TOT_VISIT_CNT_12));
        ZYPEZDItemValueSetter.setValue(tMsg.totCmCnt_12, rs.getBigDecimal(TOT_CM_CNT_12));
        ZYPEZDItemValueSetter.setValue(tMsg.totMtrVol_12, rs.getBigDecimal(TOT_MTR_VOL_12));
        return tMsg;
    }

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }

    private void getPrevMtrCntData(FCT_MLY_FSR_BY_MACHTMsg tMsg, String ltstMtrDt) {
        // get Previous Meter Read Count Data
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_SVC_MACH_MSTR_PK, tMsg.svcMachMstrPk.getValue());
        queryParam.put(PARAM_TRGT_DT, ltstMtrDt);
        queryParam.put(PARAM_INSTALL_CALL, FSR_TP.INSTALL_CALL);

        BigDecimal prevMtrCnt = (BigDecimal) ssmBatchClient.queryObject("getPrevMtrReadData", queryParam);

        if (!ZYPCommonFunc.hasValue(prevMtrCnt)) {
            prevMtrCnt = BigDecimal.ZERO;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.prevReadMtrCnt, prevMtrCnt);

        BigDecimal ltstMtrCnt = tMsg.ltstReadMtrCnt.getValue();
        if (!ZYPCommonFunc.hasValue(ltstMtrCnt)) {
            ltstMtrCnt = BigDecimal.ZERO;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.mlyCopyVol, ltstMtrCnt.subtract(prevMtrCnt));
    }

    private void getMcbvMcbfData(FCT_MLY_FSR_BY_MACHTMsg tMsg) {

        // Get Meter Count data
        Map<String, Object> mtrCntMap = new HashMap<String, Object>();
        BigDecimal totVisitCnt = BigDecimal.ZERO;
        BigDecimal totCmCnt = BigDecimal.ZERO;
        BigDecimal totMtrVol = BigDecimal.ZERO;

        // Get One Months
        mtrCntMap = getMtrCntData(tMsg, 0);
        totVisitCnt = (BigDecimal) mtrCntMap.get(VISIT_CNT);
        totCmCnt = (BigDecimal) mtrCntMap.get(CM_CNT);
        totMtrVol = (BigDecimal) mtrCntMap.get(READ_MTR_CNT);
        ZYPEZDItemValueSetter.setValue(tMsg.totVisitCnt_01, totVisitCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.totCmCnt_01, totCmCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.totMtrVol_01, totMtrVol);

        // Get Three Months
        mtrCntMap = getMtrCntData(tMsg, THREE_MTH);
        totVisitCnt = (BigDecimal) mtrCntMap.get(VISIT_CNT);
        totCmCnt = (BigDecimal) mtrCntMap.get(CM_CNT);
        totMtrVol = (BigDecimal) mtrCntMap.get(READ_MTR_CNT);
        ZYPEZDItemValueSetter.setValue(tMsg.totVisitCnt_03, totVisitCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.totCmCnt_03, totCmCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.totMtrVol_03, totMtrVol);

        // Get Six Months
        mtrCntMap = getMtrCntData(tMsg, SIX_MTH);
        totVisitCnt = (BigDecimal) mtrCntMap.get(VISIT_CNT);
        totCmCnt = (BigDecimal) mtrCntMap.get(CM_CNT);
        totMtrVol = (BigDecimal) mtrCntMap.get(READ_MTR_CNT);
        ZYPEZDItemValueSetter.setValue(tMsg.totVisitCnt_06, totVisitCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.totCmCnt_06, totCmCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.totMtrVol_06, totMtrVol);

        // Get Twelve Months
        mtrCntMap = getMtrCntData(tMsg, TWELVE_MTH);
        totVisitCnt = (BigDecimal) mtrCntMap.get(VISIT_CNT);
        totCmCnt = (BigDecimal) mtrCntMap.get(CM_CNT);
        totMtrVol = (BigDecimal) mtrCntMap.get(READ_MTR_CNT);
        ZYPEZDItemValueSetter.setValue(tMsg.totVisitCnt_12, totVisitCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.totCmCnt_12, totCmCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.totMtrVol_12, totMtrVol);
    }

    private Map<String, Object> getMtrCntData(FCT_MLY_FSR_BY_MACHTMsg tMsg, int beforeMth) {

        // get Meter Read Count data By Machine
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        queryParam.put(PARAM_SVC_MACH_MSTR_PK, tMsg.svcMachMstrPk.getValue());
        queryParam.put(PARAM_YM_LEN, YM_LEN);
        queryParam.put(PARAM_BEFORE_MTH, beforeMth);
        queryParam.put(PARAM_INSTALL_CALL, FSR_TP.INSTALL_CALL);
        List<Map<String, Object>> mtrReadList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMtrReadData", queryParam);

        BigDecimal visitCnt = BigDecimal.ZERO;
        BigDecimal cmCnt = BigDecimal.ZERO;
        BigDecimal totMtrCnt = BigDecimal.ZERO;
        List<Map<String, Object>> sumMtrReadList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < mtrReadList.size(); i++) {
            // next data exist
            if (i + 1 < mtrReadList.size()) {

                String fsrTpCd = (String) mtrReadList.get(i).get(FSR_TP_CD);
                String techCd = (String) mtrReadList.get(i).get(TECH_CD);
                String nextTechCd = (String) mtrReadList.get(i + 1).get(TECH_CD);

                // install visit is not count
                if (!fsrTpCd.equals(FSR_TP.INSTALL_CALL)) {
                    visitCnt = visitCnt.add(BigDecimal.ONE);
                    if (fsrTpCd.equals(FSR_TP.SERVICE_CALL)) {
                        cmCnt = cmCnt.add(BigDecimal.ONE);
                    }
                    BigDecimal readMtrCnt = (BigDecimal) mtrReadList.get(i).get(READ_MTR_CNT);
                    BigDecimal nextReadMtrCnt = (BigDecimal) mtrReadList.get(i + 1).get(READ_MTR_CNT);
                    totMtrCnt = totMtrCnt.add(nextReadMtrCnt.subtract(readMtrCnt));
                }

                // set TechCd change or next data is last data
                if (!techCd.equals(nextTechCd) || i + 1 == mtrReadList.size() - 1) {
                    Map<String, Object> resultMap = new HashMap<String, Object>();
                    resultMap.put(TECH_CD, techCd);
                    resultMap.put(VISIT_CNT, visitCnt);
                    resultMap.put(CM_CNT, cmCnt);
                    resultMap.put(READ_MTR_CNT, totMtrCnt);
                    sumMtrReadList.add(resultMap);
                    visitCnt = BigDecimal.ZERO;
                    cmCnt = BigDecimal.ZERO;
                    totMtrCnt = BigDecimal.ZERO;
                }
            }
        }

        // set Meter Read Data By TechCd
        BigDecimal totVisitCnt = BigDecimal.ZERO;
        BigDecimal totCmCnt = BigDecimal.ZERO;
        BigDecimal totMtrVol = BigDecimal.ZERO;
        for (int i = 0; i < sumMtrReadList.size(); i++) {
            if (tMsg.techCd.getValue().equals(sumMtrReadList.get(i).get(TECH_CD))) {
                totVisitCnt = totVisitCnt.add((BigDecimal) sumMtrReadList.get(i).get(VISIT_CNT));
                totCmCnt = totCmCnt.add((BigDecimal) sumMtrReadList.get(i).get(CM_CNT));
                totMtrVol = totMtrVol.add((BigDecimal) sumMtrReadList.get(i).get(READ_MTR_CNT));
            }
        }

        // set Return Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put(VISIT_CNT, totVisitCnt);
        returnMap.put(CM_CNT, totCmCnt);
        returnMap.put(READ_MTR_CNT, totMtrVol);

        return returnMap;

    }

}
