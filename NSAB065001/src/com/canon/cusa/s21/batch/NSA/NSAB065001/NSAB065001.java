/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB065001;

import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.PARAM_SYS_SRC_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_CONTR_BR_ACTV_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_CONTR_BR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_CONTR_BR_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_CONTR_BR_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_CONTR_BR_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_LINE_BIZ_ACTV_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_LINE_BIZ_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_LINE_BIZ_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_LINE_BIZ_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_LINE_BIZ_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_RG_ACTV_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_RG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_RG_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_RG_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_RG_ORG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.SVC_RG_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NSA.NSAB065001.constant.NSAB065001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DMN_SVC_CONTR_BRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Service Contract Branch Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NSAB065001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Target Date */
    private String trgtDt = null;

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
        new NSAB065001().executeBatch(NSAB065001.class.getSimpleName());
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

    }

    @Override
    protected void mainRoutine() {

        // Check Target Date Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtDtRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Date Recored
            DMN_SVC_CONTR_BRTMsg tMsg = new DMN_SVC_CONTR_BRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg });
            }
        }

        // Get Main Data For DMN_SVC_CONTR_BR Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_SYS_SRC_CD, SYS_SRC.S21_SERVICE);

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

                // Map Result Set Data To DMN_SVC_CONTR_BR
                DMN_SVC_CONTR_BRTMsg tMsg = mapData(rs);

                // Insert DMN_SVC_CONTR_BR
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", SVC_CONTR_BR_CD, "=", rs.getString(SVC_CONTR_BR_CD));
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
     * Map Result Set Data To DMN_SVC_CONTR_BR
     */
    private DMN_SVC_CONTR_BRTMsg mapData(ResultSet rs) throws SQLException {
        DMN_SVC_CONTR_BRTMsg tMsg = new DMN_SVC_CONTR_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, rs.getString(SVC_CONTR_BR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizCd, rs.getString(SVC_LINE_BIZ_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizDescTxt, rs.getString(SVC_LINE_BIZ_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizActvFlg, rs.getString(SVC_LINE_BIZ_ACTV_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizEffFromDt, rs.getString(SVC_LINE_BIZ_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcLineBizEffThruDt, rs.getString(SVC_LINE_BIZ_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgPk, rs.getBigDecimal(SVC_RG_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgDescTxt, rs.getString(SVC_RG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgActvFlg, rs.getString(SVC_RG_ACTV_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgEffFromDt, rs.getString(SVC_RG_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgEffThruDt, rs.getString(SVC_RG_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcRgOrgCd, rs.getString(SVC_RG_ORG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrDescTxt, rs.getString(SVC_CONTR_BR_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrActvFlg, rs.getString(SVC_CONTR_BR_ACTV_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrEffFromDt, rs.getString(SVC_CONTR_BR_EFF_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrEffThruDt, rs.getString(SVC_CONTR_BR_EFF_THRU_DT));
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
