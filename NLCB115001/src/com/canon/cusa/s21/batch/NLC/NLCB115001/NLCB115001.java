/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB115001;

import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.BASE_PRC_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.DS_SVC_CALL_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FCT_MLY_FSR_PRT_USG_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FSR_CPLT_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FSR_CPLT_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FSR_CRAT_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FSR_CRAT_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FSR_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FSR_VISIT_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FSR_VISIT_USED_PRT_COST_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.FSR_VISIT_USED_PRT_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.KEY_TASK_CPLT_DT_1;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.KEY_TASK_CPLT_DT_2;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.KEY_TASK_STS_CD_1;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.KEY_TASK_STS_CD_2;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.KEY_TASK_STS_CD_3;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.MSG_ITEM_DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.PARAM_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.PRT_USED_BY_TECH_LOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.SVC_TASK_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.TECH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.VAL_1;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.VAL_6;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NLC.NLCB115001.constant.NLCB115001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_MLY_FSR_PRT_USGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Monthly Parts Usage Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/04/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NLCB115001 extends S21BatchMain {


    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Target Date */
    private String trgtDt = null;

    /** Target Year Month */
    private String trgtYrMth = null;

    /** Commit Count */
    private int commitCount;

    /** Insert Count */
    private int normalRecCnt;

    /** Fetch Count */
    private int totalRecCnt;

    /** Error Count */
    private int errRecCnt;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NLCB115001().executeBatch(NLCB115001.class.getSimpleName());
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

        // Get Target date
        this.trgtDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.trgtDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BATCH_PROC_DATE });
        }

        // Get Target Year Month
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
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
            FCT_MLY_FSR_PRT_USGTMsg tMsg = new FCT_MLY_FSR_PRT_USGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E,  new String[] {errMsg});
            }
        }

        // Get Main Data For FCT_MLY_FSR_PRT_USG Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        param.put(KEY_TASK_CPLT_DT_1, VAL_1);
        param.put(KEY_TASK_CPLT_DT_2, VAL_6);
        param.put(KEY_TASK_STS_CD_1, SVC_TASK_STS.COMPLETED);
        param.put(KEY_TASK_STS_CD_2, SVC_TASK_STS.PENDING_CHARGE);
        param.put(KEY_TASK_STS_CD_3, SVC_TASK_STS.CLOSED);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMainData", param, execParam);
            rs = stmt.executeQuery();

            // Loop for Main data
            int wkInsertCount = 0;
            while (rs.next()) {
                this.totalRecCnt++;

                // Map Result Set Data To FCT_MLY_FSR_PRT_USG
                FCT_MLY_FSR_PRT_USGTMsg tMsg = mapData(rs);

                // Insert FCT_MLY_FSR_PRT_USG
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", FCT_MLY_FSR_PRT_USG_PK, "=", rs.getString(FCT_MLY_FSR_PRT_USG_PK));
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
     * Map Result Set To FCT_MLY_FSR_PRT_USGTMsg
     */
    private FCT_MLY_FSR_PRT_USGTMsg mapData(ResultSet rs) throws SQLException {
        FCT_MLY_FSR_PRT_USGTMsg tMsg = new FCT_MLY_FSR_PRT_USGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
        ZYPEZDItemValueSetter.setValue(tMsg.fctMlyFsrPrtUsgPk, rs.getBigDecimal(FCT_MLY_FSR_PRT_USG_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, rs.getString(FSR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.dsSvcCallTpCd, rs.getString(DS_SVC_CALL_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCratDt, rs.getString(FSR_CRAT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCratTm, rs.getString(FSR_CRAT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCpltDt, rs.getString(FSR_CPLT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrCpltTm, rs.getString(FSR_CPLT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, rs.getBigDecimal(SVC_CONFIG_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, rs.getBigDecimal(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, rs.getBigDecimal(SVC_MACH_MSTR_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, rs.getString(SER_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, rs.getString(SVC_TASK_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitNum, rs.getString(FSR_VISIT_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.techCd, rs.getString(TECH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.prtUsedByTechLocCd, rs.getString(PRT_USED_BY_TECH_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitUsedPrtQty, rs.getBigDecimal(FSR_VISIT_USED_PRT_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrVisitUsedPrtCostAmt, rs.getBigDecimal(FSR_VISIT_USED_PRT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.basePrcAmt, rs.getBigDecimal(BASE_PRC_AMT));
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
