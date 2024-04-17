/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB066001;

import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.KEY_BW_MTR_GRP_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.KEY_CLR_MTR_GRP_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MAX_COPY_PER_DAY_BLACK_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MAX_COPY_PER_DAY_TOT_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MAX_COPY_TEST_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MDL_ACTV_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MDL_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MDL_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MDL_GRP_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MDL_RGTN_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MTR_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MTR_GRP_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MTR_GRP_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.MTR_GRP_TP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.RCLL_COPY_VOL_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.RCLL_GLBL_COPY_VOL_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.RCLL_GLBL_INTVL_DAYS_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.RCLL_INTVL_DAYS_AOT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.SVC_SEG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.SVC_SEG_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.T_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NSA.NSAB066001.constant.NSAB066001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DMN_MDLTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_GRP_TP;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Model Dimension Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB066001 extends S21BatchMain {


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
        new NSAB066001().executeBatch(NSAB066001.class.getSimpleName());
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
            DMN_MDLTMsg tMsg = new DMN_MDLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg});
            }
        }

        // Get Main Data For DMN_MDL Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(KEY_CLR_MTR_GRP_TP_CD, MTR_GRP_TP.COLOR);
        param.put(KEY_BW_MTR_GRP_TP_CD, MTR_GRP_TP.BLACK_AND_WHITE);

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

                // Map Result Set Data To DMN_MDL
                DMN_MDLTMsg tMsg = mapData(rs);

                // Insert DMN_RTL_SWH
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", MDL_ID, "=", rs.getString(MDL_ID));
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
     * Map Result Set To DMN_MDLTMsg
     */
    private DMN_MDLTMsg mapData(ResultSet rs) throws SQLException {
        DMN_MDLTMsg tMsg = new DMN_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, rs.getBigDecimal(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlGrpId, rs.getBigDecimal(MDL_GRP_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSegCd, rs.getString(SVC_SEG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrGrpPk, rs.getBigDecimal(MTR_GRP_PK));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlRgtnStsCd, rs.getString(MDL_RGTN_STS_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlActvFlg, rs.getString(MDL_ACTV_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlGrpDescTxt, rs.getString(MDL_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlDescTxt, rs.getString(MDL_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.svcSegDescTxt, rs.getString(SVC_SEG_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrGrpDescTxt, rs.getString(MTR_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.t_MdlNm, rs.getString(T_MDL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrGrpTpCd, rs.getString(MTR_GRP_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mtrGrpTpDescTxt, rs.getString(MTR_GRP_TP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.maxCopyPerDayTotCnt, rs.getBigDecimal(MAX_COPY_PER_DAY_TOT_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.maxCopyPerDayBlackCnt, rs.getBigDecimal(MAX_COPY_PER_DAY_BLACK_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.maxCopyTestCnt, rs.getBigDecimal(MAX_COPY_TEST_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.rcllIntvlDaysAot, rs.getBigDecimal(RCLL_INTVL_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.rcllCopyVolCnt, rs.getBigDecimal(RCLL_COPY_VOL_CNT));
        ZYPEZDItemValueSetter.setValue(tMsg.rcllGlblIntvlDaysAot, rs.getBigDecimal(RCLL_GLBL_INTVL_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.rcllGlblCopyVolCnt, rs.getBigDecimal(RCLL_GLBL_COPY_VOL_CNT
));
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
