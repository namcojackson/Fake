/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB089001;

import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.AHS_ATTR;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.AHS_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.BW_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.CNTY_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.COLOR_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.COUNT_ONE;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.CPLT_BY_PHO_FIX_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.CTRY_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.CTY_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.CUR_LOC_ACCT_NM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.CUR_LOC_ACCT_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.DS_ACCT_GRP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.DS_ACCT_GRP_NM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.DS_KEY_ACCT_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.DS_SVC_CALL_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.ERL_START_TS;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FIRST_VISIT_CPLT_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FIRST_VISIT_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FRI_FROM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FRI_THRU;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FROM_DTM_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_CLO_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_CLO_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_CPLT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_CPLT_DTM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_CPLT_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_CRAT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_CRAT_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_OPEN_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_TOT_ITRPT_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_TOT_LBOR_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_TOT_PRT_COST_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_TOT_TRVL_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_TOT_VISIT_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_VISIT_ARV_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.FSR_VISIT_ARV_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.IND_CUR_LOC_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.INT_SECONDS;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.INV_CCY_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.INV_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.ISTL_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.LABOR_RATE;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MACH_DOWN_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MDL_GRP_ID;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MIRI_SEC_TO_SEC;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MON_FROM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MON_THRU;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MSG_ITEM_DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.MTR_READ_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.NSAM0032E;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.NSAM0033E;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.ORIG_INV_CCY_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_AHS_ATTR;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_CANCELLED;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_CLOSED;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_COMPLETED;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_COUNT_ONE;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_DEBRIEF_ERROR;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_FIRST_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_HIGH_PART;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_LABOR;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_LABOR_RATE;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_PHONE_FIX_DISPATCHER;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_PHONE_FIX_TECHNICIAN;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_AHS_PHONE_FIX_DISPATCHER;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_AHS_PHONE_FIX_TECHNICIAN;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_MEAL_2;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_PART_CALL;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_PENDING_CHARGE;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_PICK_SUPPL_TASK;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_RSP_TIME;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_SAVED;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_TIME_ADJ;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_TRAVEL;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_YMDTS_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_YMD_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PARAM_YM_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PBLM_SMRY_TXT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PICK_SUPPL_TASK;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.POST_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PROV_NM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.PRT_RSCHD_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.RCLL_FSR_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.RPT_COND_CONST_VAL_NUM_01;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.RPT_COND_CONST_VAL_NUM_02;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.RSP_TIME;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SAT_FROM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SAT_THRU;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.ST_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SUN_FROM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SUN_THRU;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_BY_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_CALL_AVOID_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_CALL_INCDT_DT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_CALL_INCDT_TM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_CALL_RQST_OWNR_TOC_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_CALL_SRC_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_INV_NUM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_LBOR_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_LBOR_DEAL_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_LBOR_FUNC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_LBOR_FUNC_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_LINE_BIZ_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_PBLM_CRCT_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_PBLM_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_PBLM_RSN_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_PBLM_TP_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_PRT_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_PRT_DEAL_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_PRT_FUNC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_PRT_FUNC_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_TRVL_DEAL_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_TRVL_DEAL_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_TRVL_FUNC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.SVC_TRVL_FUNC_DISC_AMT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.TECH_CD;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.THRU_DTM_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.THU_FROM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.THU_THRU;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.TIME_ADJ_VAL;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.TM_AND_MAT_FLG;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.TOT_READ_MTR_CNT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.TRGT_SVC_RSP_AOT;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.TUE_FROM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.TUE_THRU;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.WED_FROM;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.WED_THRU;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.YMDTS_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.YMD_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.YM_LEN;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSB.NSBB089001.constant.NSBB089001Constant.ZZZM9006E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_MLY_FSRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_SUPPL_TASK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TM_EVENT;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Monthly FSR Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/01/2016   CITS            T.Kikuhara      Create          N/A
 * 09/26/2018   Fujitsu         W.Honda         Update          QC#28381
 *</pre>
 */
public class NSBB089001 extends S21BatchMain {

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

    /** Coverage Time By AHS (Read Only Data) */
    private List<Map<String, Object>> ahsList;

    /** Coverage Time By SVC_PRC_SHIFT (Read Only Data) */
    private List<Map<String, Object>> svcPrcShiftList;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NSBB089001().executeBatch(NSBB089001.class.getSimpleName());
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

        // Get Coverage Time Info

        // 1:Get Coverage Time By AHS
        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        this.ahsList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getAhs", queryParam);

        // 2:Get Coverage Time By SVC_PRC_SHIFT
        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        this.svcPrcShiftList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPrcShift", queryParam);

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
            FCT_MLY_FSRTMsg tMsg = new FCT_MLY_FSRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
            }
        }

        // Get Main Data For FCT_MLY_FSR Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        queryParam.put(PARAM_SAVED, SVC_TASK_STS.SAVED);
        queryParam.put(PARAM_COMPLETED, SVC_TASK_STS.COMPLETED);
        queryParam.put(PARAM_PENDING_CHARGE, SVC_TASK_STS.PENDING_CHARGE);
        queryParam.put(PARAM_CLOSED, SVC_TASK_STS.CLOSED);
        queryParam.put(PARAM_CANCELLED, SVC_TASK_STS.CANCELLED);
        queryParam.put(PARAM_DEBRIEF_ERROR, SVC_TASK_STS.DEBRIEF_ERROR);
        queryParam.put(PARAM_FIRST_NUM, FIRST_VISIT_NUM);
        // Mod Start 2018/09/26 QC#28381
//        queryParam.put(PARAM_PHONE, DS_SVC_CALL_TP.PHONE);
//        queryParam.put(PARAM_MDS_PHONE, DS_SVC_CALL_TP.MDS_PHONE);
//        queryParam.put(PARAM_PHONE_2, DS_SVC_CALL_TP.PHONE_2);
        queryParam.put(PARAM_PHONE_FIX_DISPATCHER, DS_SVC_CALL_TP.PHONE_FIX_DISPATCHER);
        queryParam.put(PARAM_PHONE_FIX_TECHNICIAN, DS_SVC_CALL_TP.PHONE_FIX_TECHNICIAN);
        queryParam.put(PARAM_AHS_PHONE_FIX_DISPATCHER, DS_SVC_CALL_TP.AHS_PHONE_FIX_DISPATCHER);
        queryParam.put(PARAM_AHS_PHONE_FIX_TECHNICIAN, DS_SVC_CALL_TP.AHS_PHONE_FIX_TECHNICIAN);
        // Mod End 2018/09/26 QC#28381
        queryParam.put(PARAM_PART_CALL, DS_SVC_CALL_TP.PART_CALL);
        queryParam.put(PARAM_HIGH_PART, DS_SVC_CALL_TP.HIGH_PART);
        queryParam.put(PARAM_COUNT_ONE, COUNT_ONE);
        queryParam.put(PARAM_LABOR, SVC_TM_EVENT.LABOR);
        queryParam.put(PARAM_TRAVEL, SVC_TM_EVENT.TRAVEL);
        queryParam.put(PARAM_PICK_SUPPL_TASK, PICK_SUPPL_TASK);
        queryParam.put(PARAM_MEAL_2, SVC_SUPPL_TASK_TP.MEAL_2);
        queryParam.put(PARAM_AHS_ATTR, AHS_ATTR);
        queryParam.put(PARAM_RSP_TIME, RSP_TIME);
        queryParam.put(PARAM_LABOR_RATE, LABOR_RATE);
        queryParam.put(PARAM_YMDTS_LEN, YMDTS_LEN);
        queryParam.put(PARAM_YMD_LEN, YMD_LEN);
        queryParam.put(PARAM_YM_LEN, YM_LEN);
        queryParam.put(PARAM_TIME_ADJ, TIME_ADJ_VAL);

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

                // Map Result Set Data To FCT_MLY_FSR
                FCT_MLY_FSRTMsg tMsg = mapData(rs);

                // Insert FCT_MLY_FSR
                EZDTBLAccessor.insert(tMsg);
                if (tMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getTableName(), ":", FSR_NUM, "=", rs.getString(FSR_NUM));
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
     * Map Result Set Data To FCT_MLY_FSR
     */
    private FCT_MLY_FSRTMsg mapData(ResultSet rs) throws SQLException {
        FCT_MLY_FSRTMsg tMsg = new FCT_MLY_FSRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, rs.getString(FSR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrOpenFlg, rs.getString(FSR_OPEN_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTpCd, rs.getString(FSR_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.dsSvcCallTpCd, rs.getString(DS_SVC_CALL_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcCallSrcTpCd, rs.getString(SVC_CALL_SRC_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcCallAvoidCd, rs.getString(SVC_CALL_AVOID_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcCallRqstOwnrTocCd, rs.getString(SVC_CALL_RQST_OWNR_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcCallIncdtDt, rs.getString(SVC_CALL_INCDT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcCallIncdtTm, rs.getString(SVC_CALL_INCDT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCratDt, rs.getString(FSR_CRAT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCratTm, rs.getString(FSR_CRAT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCpltDt, rs.getString(FSR_CPLT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCpltTm, rs.getString(FSR_CPLT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCloDt, rs.getString(FSR_CLO_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCloTm, rs.getString(FSR_CLO_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcInvNum, rs.getString(SVC_INV_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.invDt, rs.getString(INV_DT));

        BigDecimal trgtSvcRspAot = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(rs.getBigDecimal(TRGT_SVC_RSP_AOT))) {
            trgtSvcRspAot = rs.getBigDecimal(TRGT_SVC_RSP_AOT).multiply(TIME_ADJ_VAL); // Minutes To Seconds
        }
        ZYPEZDItemValueSetter.setValue(tMsg.trgtSvcRspAot, trgtSvcRspAot);

        BigDecimal svcRspAot = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(rs.getString(ERL_START_TS)) && ZYPCommonFunc.hasValue(rs.getString(FSR_VISIT_ARV_DT)) && ZYPCommonFunc.hasValue(rs.getString(FSR_VISIT_ARV_TM))) {
            svcRspAot = new BigDecimal(betweenSeconds(rs.getString(ERL_START_TS), rs.getString(FSR_VISIT_ARV_DT) + rs.getString(FSR_VISIT_ARV_TM)));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.svcRspAot, svcRspAot);

        BigDecimal svcRspDiffAot = trgtSvcRspAot.subtract(svcRspAot);
        ZYPEZDItemValueSetter.setValue(tMsg.svcRspDiffAot, svcRspDiffAot);

        BigDecimal trgtSvcRstrAot = BigDecimal.ZERO;
        ZYPEZDItemValueSetter.setValue(tMsg.trgtSvcRstrAot, trgtSvcRstrAot);

        BigDecimal svcRstrAot = calculateAot(rs.getString(AHS_CD), rs.getString(SVC_BY_LINE_BIZ_TP_CD), rs.getString(ERL_START_TS), rs.getString(FSR_CPLT_DTM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcRstrAot, svcRstrAot);

        BigDecimal svcRstrDiffAot = trgtSvcRstrAot.subtract(svcRstrAot);
        ZYPEZDItemValueSetter.setValue(tMsg.svcRstrDiffAot, svcRstrDiffAot);

        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, rs.getBigDecimal(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlGrpId, rs.getBigDecimal(MDL_GRP_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.tmAndMatFlg, rs.getString(TM_AND_MAT_FLG));
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
        ZYPEZDItemValueSetter.setValue(tMsg.istlDt, rs.getString(ISTL_DT));

        String machDownFlg = rs.getString(MACH_DOWN_FLG);
        BigDecimal machDownAot = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(machDownFlg) && ZYPConstant.FLG_ON_Y.equals(machDownFlg)) {
            machDownAot = svcRstrAot;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.machDownAot, machDownAot);

        ZYPEZDItemValueSetter.setValue(tMsg.pblmSmryTxt, rs.getString(PBLM_SMRY_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPblmTpCd, rs.getString(SVC_PBLM_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPblmLocTpCd, rs.getString(SVC_PBLM_LOC_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPblmRsnTpCd, rs.getString(SVC_PBLM_RSN_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcPblmCrctTpCd, rs.getString(SVC_PBLM_CRCT_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.techCd, rs.getString(TECH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitArvDt, rs.getString(FSR_VISIT_ARV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitArvTm, rs.getString(FSR_VISIT_ARV_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTotVisitCnt, rs.getBigDecimal(FSR_TOT_VISIT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.firstVisitCpltFlg, rs.getString(FIRST_VISIT_CPLT_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.prtRschdFlg, rs.getString(PRT_RSCHD_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.rcllFsrFlg, rs.getString(RCLL_FSR_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTotTrvlAot, rs.getBigDecimal(FSR_TOT_TRVL_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTotLborAot, rs.getBigDecimal(FSR_TOT_LBOR_AOT));

        BigDecimal fsrTotItrptAot = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(rs.getBigDecimal(FSR_TOT_ITRPT_AOT))) {
            fsrTotItrptAot = rs.getBigDecimal(FSR_TOT_ITRPT_AOT).multiply(TIME_ADJ_VAL); // Minutes To Seconds;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTotItrptAot, fsrTotItrptAot);

        ZYPEZDItemValueSetter.setValue(tMsg.svcAvgTrvlAot, rs.getBigDecimal(RPT_COND_CONST_VAL_NUM_02));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLborCostUnitAmt, rs.getBigDecimal(RPT_COND_CONST_VAL_NUM_01));

        BigDecimal fsrTotTrvlCostAmt   = BigDecimal.ZERO;
        BigDecimal fsrTotLborCostAmt   = BigDecimal.ZERO;
        BigDecimal fsrTotItrptCostAmt  = BigDecimal.ZERO;
        BigDecimal fsrTotPrtCostAmt    = BigDecimal.ZERO;
        BigDecimal fsrTotCostAmt       = BigDecimal.ZERO;

        if (ZYPCommonFunc.hasValue(rs.getBigDecimal(RPT_COND_CONST_VAL_NUM_01))) {
            if (ZYPCommonFunc.hasValue(rs.getBigDecimal(FSR_TOT_TRVL_AOT))) {
                fsrTotTrvlCostAmt = rs.getBigDecimal(RPT_COND_CONST_VAL_NUM_01).multiply(rs.getBigDecimal(FSR_TOT_TRVL_AOT).divide(TIME_ADJ_VAL.multiply(TIME_ADJ_VAL), INT_SECONDS, BigDecimal.ROUND_DOWN)); // Seconds To Hours
            }
            if (ZYPCommonFunc.hasValue(rs.getBigDecimal(FSR_TOT_LBOR_AOT))) {
                fsrTotLborCostAmt = rs.getBigDecimal(RPT_COND_CONST_VAL_NUM_01).multiply(rs.getBigDecimal(FSR_TOT_LBOR_AOT).divide(TIME_ADJ_VAL.multiply(TIME_ADJ_VAL), INT_SECONDS, BigDecimal.ROUND_DOWN)); // Seconds To Hours
            }
            if (ZYPCommonFunc.hasValue(rs.getBigDecimal(FSR_TOT_ITRPT_AOT))) {
                fsrTotItrptCostAmt = rs.getBigDecimal(RPT_COND_CONST_VAL_NUM_01).multiply(rs.getBigDecimal(FSR_TOT_ITRPT_AOT).divide(TIME_ADJ_VAL, INT_SECONDS, BigDecimal.ROUND_DOWN)); // Minutes To Hours
            }
        }

        if (ZYPCommonFunc.hasValue(rs.getBigDecimal(FSR_TOT_PRT_COST_AMT))) {
            fsrTotPrtCostAmt = rs.getBigDecimal(FSR_TOT_PRT_COST_AMT);
        }

        fsrTotCostAmt = fsrTotTrvlCostAmt.add(fsrTotLborCostAmt).add(fsrTotItrptCostAmt).add(fsrTotPrtCostAmt);

        ZYPEZDItemValueSetter.setValue(tMsg.fsrTotTrvlCostAmt, fsrTotTrvlCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTotLborCostAmt, fsrTotLborCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTotItrptCostAmt, fsrTotItrptCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTotPrtCostAmt, fsrTotPrtCostAmt);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrTotCostAmt, fsrTotCostAmt);

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
        ZYPEZDItemValueSetter.setValue(tMsg.origInvCcyCd, rs.getString(ORIG_INV_CCY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.invCcyCd, rs.getString(INV_CCY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.cpltByPhoFixFlg, rs.getString(CPLT_BY_PHO_FIX_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.totReadMtrCnt, rs.getBigDecimal(TOT_READ_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.bwReadMtrCnt, rs.getBigDecimal(BW_READ_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.colorReadMtrCnt, rs.getBigDecimal(COLOR_READ_MTR_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadDt, rs.getString(MTR_READ_DT));

        return tMsg;
    }

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }


    private BigDecimal calculateAot(String ahsCd, String svcByLineBizTpCd, String erlStartTs, String fsrCpltDtm) {

        if (!ZYPCommonFunc.hasValue(erlStartTs) || !ZYPCommonFunc.hasValue(fsrCpltDtm)) {
            return BigDecimal.ZERO;
        }

        // Get Coverage Time
        // 1:Get Coverage Time By AHS
        Map<String, Object> coverageTimeMap = null;
        if (ZYPCommonFunc.hasValue(ahsCd)) {
            for (Map<String, Object> ahsMap : this.ahsList) {
                if (ahsCd.equals((String) ahsMap.get(AHS_CD))) {
                    coverageTimeMap = ahsMap;
                }
            }
        }

        // 2:Get Coverage Time By SVC_PRC_SHIFT
        if (ZYPCommonFunc.hasValue(svcByLineBizTpCd) && coverageTimeMap == null) {
            for (Map<String, Object> svcPrcShiftMap : this.svcPrcShiftList) {
                if (svcByLineBizTpCd.equals((String) svcPrcShiftMap.get(SVC_LINE_BIZ_CD))) {
                    coverageTimeMap = svcPrcShiftMap;
                }
            }
        }

        int startDay = ZYPDateUtil.getDayOfWeek(erlStartTs.substring(0, YMD_LEN));
        int days = 1 + ZYPDateUtil.getDiffDays(fsrCpltDtm, erlStartTs);
        long wkSeconds = 0;
        for (int i = 0; i < days; i++) {
            String fromTime = null;
            String thruTime = null;
            String fromCoverageTime = getCoverageTime(startDay, coverageTimeMap, FROM_DTM_FLG);
            String thruCoverageTime = getCoverageTime(startDay, coverageTimeMap, THRU_DTM_FLG);
            if (ZYPCommonFunc.hasValue(fromCoverageTime) && ZYPCommonFunc.hasValue(thruCoverageTime)) {
                if (i == 0) {
                    if (erlStartTs.substring(YMD_LEN).compareTo(fromCoverageTime) > 0) {
                        fromTime = erlStartTs.substring(YMD_LEN);
                    } else {
                        fromTime = fromCoverageTime;
                    }
                    if (erlStartTs.substring(YMD_LEN).compareTo(thruCoverageTime) > 0) {
                        fromTime = null;
                    }
                    if (days != 1) {
                        thruTime = thruCoverageTime;
                    }
                }

                if (i == days - 1) {
                    if (fsrCpltDtm.substring(YMD_LEN).compareTo(thruCoverageTime) < 0) {
                        thruTime = fsrCpltDtm.substring(YMD_LEN);
                    } else {
                        thruTime = thruCoverageTime;
                    }
                    if (fsrCpltDtm.substring(YMD_LEN).compareTo(fromCoverageTime) < 0) {
                        thruTime = null;
                    }
                    if (i != 0) {
                        fromTime = fromCoverageTime;
                    }
                }

                if (i > 0 && i < days - 1) {
                    fromTime = fromCoverageTime;
                    thruTime = thruCoverageTime;
                }

                if (ZYPCommonFunc.hasValue(fromTime) && ZYPCommonFunc.hasValue(thruTime)) {
                    wkSeconds = wkSeconds + betweenSeconds(erlStartTs.substring(0, YMD_LEN) + fromTime, erlStartTs.substring(0, YMD_LEN) + thruTime);
                }
            }
            startDay++;
            if (startDay > ZYPDateUtil.WEEK_SATURDAY) {
                startDay = ZYPDateUtil.WEEK_SUNDAY;
            }
        }

        return new BigDecimal(wkSeconds);
    }

    private String getCoverageTime(int targetDay, Map<String, Object> coverageTimeMap, int fromThruFlg) {
        String outTime = null;
        switch (targetDay) {
            case ZYPDateUtil.WEEK_SUNDAY :
                if (FROM_DTM_FLG == fromThruFlg) {
                    outTime = (String) coverageTimeMap.get(SUN_FROM);
                } else {
                    outTime = (String) coverageTimeMap.get(SUN_THRU);
                }
                break;
            case ZYPDateUtil.WEEK_MONDAY :
                if (FROM_DTM_FLG == fromThruFlg) {
                    outTime = (String) coverageTimeMap.get(MON_FROM);
                } else {
                    outTime = (String) coverageTimeMap.get(MON_THRU);
                }
                break;
            case ZYPDateUtil.WEEK_TUESDAY :
                if (FROM_DTM_FLG == fromThruFlg) {
                    outTime = (String) coverageTimeMap.get(TUE_FROM);
                } else {
                    outTime = (String) coverageTimeMap.get(TUE_THRU);
                }
                break;
            case ZYPDateUtil.WEEK_WEDNESDAY :
                if (FROM_DTM_FLG == fromThruFlg) {
                    outTime = (String) coverageTimeMap.get(WED_FROM);
                } else {
                    outTime = (String) coverageTimeMap.get(WED_THRU);
                }
                break;
            case ZYPDateUtil.WEEK_THURSDAY :
                if (FROM_DTM_FLG == fromThruFlg) {
                    outTime = (String) coverageTimeMap.get(THU_FROM);
                } else {
                    outTime = (String) coverageTimeMap.get(THU_THRU);
                }
                break;
            case ZYPDateUtil.WEEK_FRIDAY :
                if (FROM_DTM_FLG == fromThruFlg) {
                    outTime = (String) coverageTimeMap.get(FRI_FROM);
                } else {
                    outTime = (String) coverageTimeMap.get(FRI_THRU);
                }
                break;
            case ZYPDateUtil.WEEK_SATURDAY :
                if (FROM_DTM_FLG == fromThruFlg) {
                    outTime = (String) coverageTimeMap.get(SAT_FROM);
                } else {
                    outTime = (String) coverageTimeMap.get(SAT_THRU);
                }
                break;
            default :
                break;
        }

        return outTime;
    }

    private long betweenSeconds(String fromDtm, String thruDtm) {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddkkmmss");
        long outSeconds = 0;

        try {
            Date fromDt = sdf.parse(fromDtm);
            Date thruDt = sdf.parse(thruDtm);
            long fromTime = fromDt.getTime();
            long thruTime = thruDt.getTime();
            outSeconds = (thruTime - fromTime) / MIRI_SEC_TO_SEC;
        } catch (ParseException e) {
            outSeconds = 0;
        }
        return outSeconds;
    }

}
