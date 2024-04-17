/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB114001;

import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.AVAL_INVTY_COST_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.BO_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.INVTY_AVAL_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.IN_TRNST_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_CD_ADJUSTED_1;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_CD_ADJUSTED_2;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_CD_ADJUSTED_3;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_CD_RECEIVED_1;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_CD_RECEIVED_2;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_CD_RECEIVED_3;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_CD_RETURNED_1;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_CD_RETURNED_2;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_CD_USAGE;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_INVTY_TRX_YR_MTH;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_LOC_STS_CD_INTRANSIT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_LOC_STS_CD_ON_HAND;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_LOC_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ON_ORDERED_WEEK_1;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ON_ORDERED_WEEK_2;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ON_ORDERED_WEEK_3;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ON_ORDERED_WEEK_4;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ON_ORDERED_WEEK_5;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ORDERED_WEEK_1;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ORDERED_WEEK_2;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ORDERED_WEEK_3;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ORDERED_WEEK_4;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_ORDERED_WEEK_5;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_PO_HDR_STS_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_RTL_SWH_CD_1;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_RTL_SWH_CD_2;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_SHPG_STS_CD_BO;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.KEY_WEEK;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.LAST_RCPT_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.LAST_USG_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.MAX_INVTY_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.MKTG_MDL_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.MSG_ITEM_DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ON_ORD_QTY_01;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ON_ORD_QTY_02;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ON_ORD_QTY_03;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ON_ORD_QTY_04;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ON_ORD_QTY_05;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ORD_QTY_01;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ORD_QTY_02;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ORD_QTY_03;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ORD_QTY_04;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ORD_QTY_05;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.PARAM_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ROP_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RPLSH_AUTH_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RPLSH_AUTH_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RPLSH_FRI_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RPLSH_MON_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RPLSH_THU_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RPLSH_TUE_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RPLSH_WED_FLG;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RTL_SWH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RTL_SWH_CD_BSD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RTL_SWH_CD_RSV;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.TECH_TOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.TMTH_ADJ_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.TMTH_ADJ_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.TMTH_RCV_INVTY_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.TMTH_RCV_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.TMTH_RTRN_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.TMTH_RTRN_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.TMTH_USG_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.TMTH_USG_QTY;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.UNIT_COST_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_0300001;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_0302003;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_0302022;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_0302044;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_0302045;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_0306001;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_0306013;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_0306014;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_0323001;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_06;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_1;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_10;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_2;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_3;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_4;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_5;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_6;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.VAL_7;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NLC.NLCB114001.constant.NLCB114001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_INVTY_OH_SMRYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 *<pre>
 * Inventory On Hand Summary
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/10/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NLCB114001 extends S21BatchMain {


    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Batch Process Date */
    private String batchDt = null;

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
        new NLCB114001().executeBatch(NLCB114001.class.getSimpleName());
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
            FCT_INVTY_OH_SMRYTMsg tMsg = new FCT_INVTY_OH_SMRYTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg});
            }
        }

        // Get Main Data For FCT_INVTY_OH_SMRY Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_DT, this.batchDt);
        param.put(KEY_LOC_STS_CD_ON_HAND, LOC_STS.DC_STOCK);
        param.put(KEY_LOC_STS_CD_INTRANSIT, LOC_STS.IN_TRANSIT_WH);
        param.put(KEY_SHPG_STS_CD_BO, SHPG_STS.VALIDATED);
        param.put(KEY_ON_ORDERED_WEEK_1, VAL_1);
        param.put(KEY_ON_ORDERED_WEEK_2, VAL_2);
        param.put(KEY_ON_ORDERED_WEEK_3, VAL_3);
        param.put(KEY_ON_ORDERED_WEEK_4, VAL_4);
        param.put(KEY_ON_ORDERED_WEEK_5, VAL_5);
        param.put(KEY_ORDERED_WEEK_1, VAL_1);
        param.put(KEY_ORDERED_WEEK_2, VAL_2);
        param.put(KEY_ORDERED_WEEK_3, VAL_3);
        param.put(KEY_ORDERED_WEEK_4, VAL_4);
        param.put(KEY_ORDERED_WEEK_5, VAL_5);
        param.put(KEY_WEEK, VAL_7);
        param.put(KEY_PO_HDR_STS_CD,  VAL_10);
        param.put(KEY_RTL_SWH_CD_1, RTL_SWH_CD_RSV);
        param.put(KEY_RTL_SWH_CD_2, RTL_SWH_CD_BSD);
        param.put(KEY_LOC_TP_CD, VAL_06);
        param.put(KEY_CD_RECEIVED_1, VAL_0302022);
        param.put(KEY_CD_RECEIVED_2, VAL_0302045);
        param.put(KEY_CD_RECEIVED_3, VAL_0300001);
        param.put(KEY_CD_RETURNED_1, VAL_0302003);
        param.put(KEY_CD_RETURNED_2, VAL_0302044);
        param.put(KEY_CD_ADJUSTED_1, VAL_0306001);
        param.put(KEY_CD_ADJUSTED_2, VAL_0306013);
        param.put(KEY_CD_ADJUSTED_3, VAL_0306014);
        param.put(KEY_CD_USAGE, VAL_0323001);
        param.put(KEY_INVTY_TRX_YR_MTH, VAL_6);

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

                // Map Result Set Data To FCT_INVTY_OH_SMRY
                FCT_INVTY_OH_SMRYTMsg tMsg = mapData(rs);

                // Insert FCT_INVTY_OH_SMRY
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", RTL_WH_CD, "=", rs.getString(RTL_WH_CD), ", ",
                            RTL_SWH_CD, "=", rs.getString(RTL_SWH_CD), ", ", RTL_WH_CATG_CD, "=", rs.getString(RTL_WH_CATG_CD), ", ",
                            TECH_TOC_CD, "=", rs.getString(TECH_TOC_CD), ", ", MDSE_CD, "=", rs.getString(MDSE_CD));
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
     * Map Result Set To FCT_INVTY_OH_SMRYTMsg
     */
    private FCT_INVTY_OH_SMRYTMsg mapData(ResultSet rs) throws SQLException {
        FCT_INVTY_OH_SMRYTMsg tMsg = new FCT_INVTY_OH_SMRYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rs.getString(RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, rs.getString(RTL_SWH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgCd, rs.getString(RTL_WH_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.techTocCd, rs.getString(TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.mktgMdlCd, rs.getString(MKTG_MDL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.unitCostAmt, rs.getBigDecimal(UNIT_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.invtyAvalQty, rs.getBigDecimal(INVTY_AVAL_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.avalInvtyCostAmt, rs.getBigDecimal(AVAL_INVTY_COST_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.boQty, rs.getBigDecimal(BO_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.inTrnstQty, rs.getBigDecimal(IN_TRNST_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.onOrdQty_01, rs.getBigDecimal(ON_ORD_QTY_01));
        ZYPEZDItemValueSetter.setValue(tMsg.onOrdQty_02, rs.getBigDecimal(ON_ORD_QTY_02));
        ZYPEZDItemValueSetter.setValue(tMsg.onOrdQty_03, rs.getBigDecimal(ON_ORD_QTY_03));
        ZYPEZDItemValueSetter.setValue(tMsg.onOrdQty_04, rs.getBigDecimal(ON_ORD_QTY_04));
        ZYPEZDItemValueSetter.setValue(tMsg.onOrdQty_05, rs.getBigDecimal(ON_ORD_QTY_05));
        ZYPEZDItemValueSetter.setValue(tMsg.ordQty_01, rs.getBigDecimal(ORD_QTY_01));
        ZYPEZDItemValueSetter.setValue(tMsg.ordQty_02, rs.getBigDecimal(ORD_QTY_02));
        ZYPEZDItemValueSetter.setValue(tMsg.ordQty_03, rs.getBigDecimal(ORD_QTY_03));
        ZYPEZDItemValueSetter.setValue(tMsg.ordQty_04, rs.getBigDecimal(ORD_QTY_04));
        ZYPEZDItemValueSetter.setValue(tMsg.ordQty_05, rs.getBigDecimal(ORD_QTY_05));
        ZYPEZDItemValueSetter.setValue(tMsg.lastRcptDt, rs.getString(LAST_RCPT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.lastUsgDt, rs.getString(LAST_USG_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.tmthRcvQty, rs.getBigDecimal(TMTH_RCV_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.tmthRcvInvtyAmt, rs.getBigDecimal(TMTH_RCV_INVTY_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.tmthRtrnQty, rs.getBigDecimal(TMTH_RTRN_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.tmthRtrnAmt, rs.getBigDecimal(TMTH_RTRN_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.tmthAdjQty, rs.getBigDecimal(TMTH_ADJ_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.tmthAdjAmt, rs.getBigDecimal(TMTH_ADJ_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.tmthUsgQty, rs.getBigDecimal(TMTH_USG_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.tmthUsgAmt, rs.getBigDecimal(TMTH_USG_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.ropQty, rs.getBigDecimal(ROP_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.maxInvtyQty, rs.getBigDecimal(MAX_INVTY_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.rplshAuthAmt, rs.getBigDecimal(RPLSH_AUTH_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.rplshAuthQty, rs.getBigDecimal(RPLSH_AUTH_QTY));
        ZYPEZDItemValueSetter.setValue(tMsg.rplshMonFlg, rs.getString(RPLSH_MON_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.rplshTueFlg, rs.getString(RPLSH_TUE_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.rplshWedFlg, rs.getString(RPLSH_WED_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.rplshThuFlg, rs.getString(RPLSH_THU_FLG));
        ZYPEZDItemValueSetter.setValue(tMsg.rplshFriFlg, rs.getString(RPLSH_FRI_FLG));

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
