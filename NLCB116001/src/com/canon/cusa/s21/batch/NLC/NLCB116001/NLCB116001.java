package com.canon.cusa.s21.batch.NLC.NLCB116001;

import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.AGE_INVTY_OVER_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.AGE_INVTY_QTY_01;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.AGE_INVTY_QTY_02;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.AGE_INVTY_QTY_03;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.INVTY_AGING_BCKT_DESC_TXT;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.INVTY_AGING_DAYS_AOT;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.INVTY_LOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.MSG_ITEM_DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.PARAM_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.STK_IN_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NLC.NLCB116001.constant.NLCB116001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_INVTY_OH_AGINGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Inventory Aging Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/28/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NLCB116001 extends S21BatchMain {

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
        new NLCB116001().executeBatch(NLCB116001.class.getSimpleName());
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

        // Check Target Year Month Recored
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        queryParam.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        BigDecimal recCnt = (BigDecimal) ssmBatchClient.queryObject("getTrgtYrMthRec", queryParam);
        if (ZYPCommonFunc.hasValue(recCnt) && (BigDecimal.ZERO.compareTo(recCnt) < 0)) {
            // Delete Target Year Month Recored
            FCT_INVTY_OH_AGINGTMsg tMsg = new FCT_INVTY_OH_AGINGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg });
            }
        }

        // Get Main Data For FCT_INVTY_OH_AGING Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, this.trgtDt);

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

                // Map Result Set Data To FCT_INVTY_OH_AGING
                FCT_INVTY_OH_AGINGTMsg tMsg = mapData(rs);

                // Insert FCT_INVTY_OH_AGING
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName()
                                                                                      , ", COLUMN:", INVTY_LOC_CD, "=", rs.getString(INVTY_LOC_CD)
                                                                                                   , MDSE_CD, "=", rs.getString(MDSE_CD)
                                                                                                   , STK_IN_DT, "=", rs.getString(STK_IN_DT));
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
     * Map Result Set To FCT_INVTY_OH_AGINGTMsg
     */
    private FCT_INVTY_OH_AGINGTMsg mapData(ResultSet rs) throws SQLException {
        FCT_INVTY_OH_AGINGTMsg tMsg = new FCT_INVTY_OH_AGINGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
        ZYPEZDItemValueSetter.setValue(tMsg.invtyLocCd, rs.getString(INVTY_LOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rs.getString(RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, rs.getString(RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgCd, rs.getString(RTL_WH_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.stkInDt, rs.getString(STK_IN_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.invtyAgingDaysAot, rs.getBigDecimal(INVTY_AGING_DAYS_AOT));
        ZYPEZDItemValueSetter.setValue(tMsg.invtyAgingBcktDescTxt, rs.getString(INVTY_AGING_BCKT_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(tMsg.ageInvtyQty_01, rs.getBigDecimal(AGE_INVTY_QTY_01));
        ZYPEZDItemValueSetter.setValue(tMsg.ageInvtyQty_02, rs.getBigDecimal(AGE_INVTY_QTY_02));
        ZYPEZDItemValueSetter.setValue(tMsg.ageInvtyQty_03, rs.getBigDecimal(AGE_INVTY_QTY_03));
        ZYPEZDItemValueSetter.setValue(tMsg.ageInvtyOverQty, rs.getBigDecimal(AGE_INVTY_OVER_QTY));
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
