/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB090001;

import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.BW_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.CNTY_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.COLOR_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.CTRY_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.CTY_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.CUR_LOC_ACCT_NM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.CUR_LOC_ACCT_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.DS_ACCT_GRP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.DS_ACCT_GRP_NM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.DS_KEY_ACCT_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.DS_SVC_CALL_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.ERL_START_TS;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FIRST_VISIT_CPLT_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FIRST_VISIT_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FLD_SVC_MGR_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_CPLT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_CPLT_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_CRAT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_CRAT_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_ARV_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_ARV_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_CLO_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_CLO_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_CPLT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_CPLT_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_DISPT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_DISPT_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_ETA_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_ETA_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_SCHD_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.FSR_VISIT_SCHD_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.IND_CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.INT_SECONDS;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.LABOR_RATE;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.MOD_EVENT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.MSG_ITEM_DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.MTR_GRP_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.MTR_READ_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.MTR_READ_EX_RSN_TXT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.NEXT_FSR_VISIT_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.NEXT_VISIT_SVC_CALL_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.NSAM0032E;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.NSAM0033E;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_LABOR;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_LABOR_RATE;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_MEAL_2;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_MODIFICATION;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_PICK_SUPPL_TASK;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_SEC_PERIOD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_TIME_ADJ;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_TRAVEL;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_YMDTS_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PARAM_YM_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PHO_FIX_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PICK_SUPPL_TASK;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.POST_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PREV_FSR_VISIT_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.PROV_NM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.RCLL_COPY_VOL_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.RCLL_GLBL_COPY_VOL_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.RCLL_GLBL_INTVL_DAYS_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.RCLL_INTVL_DAYS_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.RSN_NOT_CPLT_TXT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.ST_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_ASG_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_AVG_TRVL_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_ITRPT_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_ITRPT_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_ITRPT_MEAL_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_ITRPT_TRVL_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_LBOR_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_LBOR_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_LBOR_COST_UNIT_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_PRT_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_SUPPL_FROM_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_SUPPL_FROM_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_SUPPL_TASK_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_SUPPL_TASK_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_SUPPL_TO_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_SUPPL_TO_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_TASK_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_TASK_STS_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_TOT_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_TOT_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_TRVL_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.SVC_TRVL_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TECH_ACPT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TECH_ACPT_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TECH_ACPT_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TECH_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TECH_SCHD_FROM_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TECH_SCHD_FROM_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TECH_SCHD_TO_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TECH_SCHD_TO_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TECH_SCHD_TZ;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TIME_ADJ_VAL;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TOT_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.TSS_FOLL_UP_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.YMDTS_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.YM_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSB.NSBB090001.constant.NSBB090001Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_MLY_FSR_VISITTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
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
 * Monthly FSR Visit Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/22/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NSBB090001 extends S21BatchMain {

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
        new NSBB090001().executeBatch(NSBB090001.class.getSimpleName());
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

        // Get Batch Process date
        this.batchDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.batchDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BATCH_PROC_DATE });
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
            FCT_MLY_FSR_VISITTMsg tMsg = new FCT_MLY_FSR_VISITTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
            }
        }

        // Get Main Data For FCT_MLY_FSR_VISIT Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        queryParam.put(PARAM_LABOR, SVC_TM_EVENT.LABOR);
        queryParam.put(PARAM_TRAVEL, SVC_TM_EVENT.TRAVEL);
        queryParam.put(PARAM_PICK_SUPPL_TASK, PICK_SUPPL_TASK);
        queryParam.put(PARAM_MEAL_2, SVC_SUPPL_TASK_TP.MEAL_2);
        queryParam.put(PARAM_LABOR_RATE, LABOR_RATE);
        queryParam.put(PARAM_YMDTS_LEN, YMDTS_LEN);
        queryParam.put(PARAM_YM_LEN, YM_LEN);
        queryParam.put(PARAM_TIME_ADJ, TIME_ADJ_VAL);
        queryParam.put(PARAM_SEC_PERIOD, INT_SECONDS);
        queryParam.put(PARAM_MODIFICATION, SVC_TM_EVENT.MODIFICATION);

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

                // Map Result Set Data To FCT_MLY_FSR_VISIT
                FCT_MLY_FSR_VISITTMsg tMsg = mapData(rs);

                // Insert FCT_MLY_FSR_VISIT
                EZDTBLAccessor.insert(tMsg);
                if (tMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getTableName(), ":", FSR_NUM, "=", rs.getString(FSR_NUM), ":", SVC_TASK_NUM, "=", rs.getString(SVC_TASK_NUM), ":", FSR_VISIT_NUM, "=", rs.getString(FSR_VISIT_NUM));
                    S21InfoLogOutput.println(NSAM0032E, new String[] {errMsg });
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
     * Map Result Set Data To FCT_MLY_FSR_VISIT
     */
    private FCT_MLY_FSR_VISITTMsg mapData(ResultSet rs) throws SQLException {
        FCT_MLY_FSR_VISITTMsg tMsg = new FCT_MLY_FSR_VISITTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
        BigDecimal fctMlyFsrVisitSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_MLY_FSR_VISIT_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.fctMlyFsrVisitPk, fctMlyFsrVisitSq);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, rs.getString(FSR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTpCd, rs.getString(FSR_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCratDt, rs.getString(FSR_CRAT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCratTm, rs.getString(FSR_CRAT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCpltDt, rs.getString(FSR_CPLT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCpltTm, rs.getString(FSR_CPLT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, rs.getString(SVC_TASK_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskStsCd, rs.getString(SVC_TASK_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsSvcCallTpCd, rs.getString(DS_SVC_CALL_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.phoFixFlg, rs.getString(PHO_FIX_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.erlStartTs, rs.getString(ERL_START_TS));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitNum, rs.getString(FSR_VISIT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.prevFsrVisitNum, rs.getString(PREV_FSR_VISIT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.nextFsrVisitNum, rs.getString(NEXT_FSR_VISIT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.nextVisitSvcCallTpCd, rs.getString(NEXT_VISIT_SVC_CALL_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSupplTaskNum, rs.getString(SVC_SUPPL_TASK_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSupplTaskTpCd, rs.getString(SVC_SUPPL_TASK_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.techCd, rs.getString(TECH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fldSvcMgrCd, rs.getString(FLD_SVC_MGR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.firstVisitFlg, rs.getString(FIRST_VISIT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.firstVisitCpltFlg, rs.getString(FIRST_VISIT_CPLT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.tssFollUpFlg, rs.getString(TSS_FOLL_UP_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.modEventCnt, rs.getBigDecimal(MOD_EVENT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, rs.getBigDecimal(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.rcllIntvlDaysAot, rs.getBigDecimal(RCLL_INTVL_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.rcllCopyVolCnt, rs.getBigDecimal(RCLL_COPY_VOL_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.rcllGlblIntvlDaysAot, rs.getBigDecimal(RCLL_GLBL_INTVL_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.rcllGlblCopyVolCnt, rs.getBigDecimal(RCLL_GLBL_COPY_VOL_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNum, rs.getString(CUR_LOC_ACCT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.curLocAcctNm, rs.getString(CUR_LOC_ACCT_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctGrpCd, rs.getString(DS_ACCT_GRP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsAcctGrpNm, rs.getString(DS_ACCT_GRP_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.curLocNum, rs.getString(CUR_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.indCurLocNum, rs.getString(IND_CUR_LOC_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsKeyAcctFlg, rs.getString(DS_KEY_ACCT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.firstLineAddr, rs.getString(FIRST_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.scdLineAddr, rs.getString(SCD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.thirdLineAddr, rs.getString(THIRD_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.frthLineAddr, rs.getString(FRTH_LINE_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.ctyAddr, rs.getString(CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.cntyPk, rs.getBigDecimal(CNTY_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.provNm, rs.getString(PROV_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, rs.getString(ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.postCd, rs.getString(POST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, rs.getString(CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.techSchdFromDt, rs.getString(TECH_SCHD_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.techSchdFromTm, rs.getString(TECH_SCHD_FROM_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.techSchdToDt, rs.getString(TECH_SCHD_TO_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.techSchdToTm, rs.getString(TECH_SCHD_TO_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.techSchdTz, rs.getString(TECH_SCHD_TZ));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitSchdDt, rs.getString(FSR_VISIT_SCHD_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitSchdTm, rs.getString(FSR_VISIT_SCHD_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitDisptDt, rs.getString(FSR_VISIT_DISPT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitDisptTm, rs.getString(FSR_VISIT_DISPT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcAsgTpCd, rs.getString(SVC_ASG_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.techAcptFlg, rs.getString(TECH_ACPT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.techAcptDt, rs.getString(TECH_ACPT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.techAcptTm, rs.getString(TECH_ACPT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitArvDt, rs.getString(FSR_VISIT_ARV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitArvTm, rs.getString(FSR_VISIT_ARV_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitCpltDt, rs.getString(FSR_VISIT_CPLT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitCpltTm, rs.getString(FSR_VISIT_CPLT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitCloDt, rs.getString(FSR_VISIT_CLO_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitCloTm, rs.getString(FSR_VISIT_CLO_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSupplFromDt, rs.getString(SVC_SUPPL_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSupplFromTm, rs.getString(SVC_SUPPL_FROM_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSupplToDt, rs.getString(SVC_SUPPL_TO_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSupplToTm, rs.getString(SVC_SUPPL_TO_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.rsnNotCpltTxt, rs.getString(RSN_NOT_CPLT_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTrvlAot, rs.getBigDecimal(SVC_TRVL_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborAot, rs.getBigDecimal(SVC_LBOR_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcItrptAot, rs.getBigDecimal(SVC_ITRPT_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcItrptTrvlAot, rs.getBigDecimal(SVC_ITRPT_TRVL_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcItrptMealAot, rs.getBigDecimal(SVC_ITRPT_MEAL_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTotAot, rs.getBigDecimal(SVC_TOT_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcAvgTrvlAot, rs.getBigDecimal(SVC_AVG_TRVL_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborCostUnitAmt, rs.getBigDecimal(SVC_LBOR_COST_UNIT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTrvlCostAmt, rs.getBigDecimal(SVC_TRVL_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborCostAmt, rs.getBigDecimal(SVC_LBOR_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcItrptCostAmt, rs.getBigDecimal(SVC_ITRPT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPrtCostAmt, rs.getBigDecimal(SVC_PRT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTotCostAmt, rs.getBigDecimal(SVC_TOT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitEtaDt, rs.getString(FSR_VISIT_ETA_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitEtaTm, rs.getString(FSR_VISIT_ETA_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrGrpPk, rs.getBigDecimal(MTR_GRP_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.totReadMtrCnt, rs.getBigDecimal(TOT_READ_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.bwReadMtrCnt, rs.getBigDecimal(BW_READ_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.colorReadMtrCnt, rs.getBigDecimal(COLOR_READ_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadDt, rs.getString(MTR_READ_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadExRsnTxt, rs.getString(MTR_READ_EX_RSN_TXT));

        return tMsg;
    }

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }

}
