package com.canon.cusa.s21.batch.NSA.NSAB071001;

import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.ACCT_ALL_MDL_GRP_COPY_PCT;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.CATG_CD_MDL_GRP;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.CATG_CD_MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.COPY_USG_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.FCT_CATG_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MAX_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MDL_GRP_DESC_TXT;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MDL_GRP_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MIN_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MSG_ITEM_DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MTH_ON_MTH_COPY_PCT;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.MTH_ON_MTH_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.PARAM_CATG_CD_MDL_GRP;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.PARAM_CATG_CD_MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.PARAM_DS_MTR_READ_TP_GRP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.PARAM_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.SVC_BY_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.T_MDL_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.YR_ON_YR_COPY_PCT;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.YR_ON_YR_COPY_QTY;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NSA.NSAB071001.constant.NSAB071001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_MLY_HIST_USGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
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
 * Monthly Usage History
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/29/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NSAB071001 extends S21BatchMain {

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
        new NSAB071001().executeBatch(NSAB071001.class.getSimpleName());
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
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_DT, this.trgtDt);
        this.trgtYrMth = (String) ssmBatchClient.queryObject("getTrgtYrMth", queryParam);
        if (!ZYPCommonFunc.hasValue(this.trgtYrMth)) {
            throw new S21AbendException(ZZZM9006E, new String[] {MSG_ITEM_DWH_TRGT_YR_MTH });
        }

    }

    @Override
    protected void mainRoutine() {

        // Check Target Date Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtYrMthRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Date Recored
            FCT_MLY_HIST_USGTMsg tMsg = new FCT_MLY_HIST_USGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg});
            }
        }

        // Get Main Data For  FCT_MLY_HIST_USG Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, this.trgtDt);
        param.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        param.put(PARAM_DS_MTR_READ_TP_GRP_CD, DS_MTR_READ_TP_GRP.SERVICE_READS);
        param.put(PARAM_CATG_CD_MDL_GRP, CATG_CD_MDL_GRP);
        param.put(PARAM_CATG_CD_MDL_ID, CATG_CD_MDL_ID);

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

                // Map Result Set Data To  FCT_MLY_HIST_USG
                FCT_MLY_HIST_USGTMsg tMsg = mapData(rs);

                // Insert  FCT_MLY_HIST_USG
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", MDL_GRP_ID, "=", rs.getString(MDL_GRP_ID), ", ", MDL_ID, "=", rs.getString(MDL_ID), "]");
                    S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg});
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
     * Map Result Set To  FCT_MLY_HIST_USGTMsg
     */
    private  FCT_MLY_HIST_USGTMsg mapData(ResultSet rs) throws SQLException {
        FCT_MLY_HIST_USGTMsg tMsg = new FCT_MLY_HIST_USGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal fctMlyHistUsgSq = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FCT_MLY_HIST_USG_SQ);
        ZYPEZDItemValueSetter.setValue(tMsg.fctMlyHistUsgPk, fctMlyHistUsgSq);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
        ZYPEZDItemValueSetter.setValue(tMsg.fctCatgCd, rs.getString(FCT_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.fctCratDt, this.trgtDt);
        ZYPEZDItemValueSetter.setValue(tMsg.svcByLineBizTpCd, rs.getString(SVC_BY_LINE_BIZ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlGrpId, rs.getBigDecimal(MDL_GRP_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlGrpDescTxt, rs.getString(MDL_GRP_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, rs.getBigDecimal(MDL_ID));
        ZYPEZDItemValueSetter.setValue(tMsg.t_MdlNm, rs.getString(T_MDL_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.copyUsgQty, rs.getBigDecimal(COPY_USG_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.yrOnYrCopyPct, rs.getBigDecimal(YR_ON_YR_COPY_PCT));
        ZYPEZDItemValueSetter.setValue(tMsg.mthOnMthCopyPct, rs.getBigDecimal(MTH_ON_MTH_COPY_PCT));
        ZYPEZDItemValueSetter.setValue(tMsg.yrOnYrCopyQty, rs.getBigDecimal(YR_ON_YR_COPY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.mthOnMthCopyQty, rs.getBigDecimal(MTH_ON_MTH_COPY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.maxCopyQty, rs.getBigDecimal(MAX_COPY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.minCopyQty, rs.getBigDecimal(MIN_COPY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.acctAllMdlGrpCopyPct, rs.getBigDecimal(ACCT_ALL_MDL_GRP_COPY_PCT));
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
