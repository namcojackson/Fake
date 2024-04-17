/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB112001;

import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.CAL_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.CARR_AT_DEST_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.CARR_AT_DEST_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.CARR_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.CARR_PICK_UP_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.CARR_PICK_UP_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.CRAT_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.CRAT_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.DATE_FORMAT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.DEFAULT_COMMIT_COUNT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.DELY_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.DELY_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.DEST_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.FETCH_SIZE;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.FSR_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.HOUR_TO_DAY;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_APVL_HIST_ACT_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_PRCH_REQ_LINE_SUB_NUM_1;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_PRCH_REQ_LINE_SUB_NUM_2;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_PRCH_REQ_LINE_SUB_NUM_3;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_PRCH_REQ_LINE_SUB_NUM_4;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_PRCH_REQ_LINE_SUB_NUM_5;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_RPT_COND_CONST_GRP_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_SUBSTR_DT_LNGTH;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_SUBSTR_TM_FROM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_SUBSTR_TM_LNGTH;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.KEY_SUBSTR_TRGT_YR_MTH_LNGTH;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.MIN_TO_HOUR;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.MIRI_SEC_TO_SEC;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.MSG_ITEM_BATCH_PROC_DATE;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.MSG_ITEM_DWH_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PARAM_CAL_DT_FROM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PARAM_CAL_DT_TO;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PARAM_CAL_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PARAM_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PARAM_RPT_COND_CONST_GRP_ID;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PARAM_TRGT_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PARAM_TRGT_YR_MTH;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PRCH_REQ_APVL_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PRCH_REQ_APVL_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PRCH_REQ_LINE_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PRCH_REQ_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PRCH_REQ_LINE_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PRCH_REQ_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.PRCH_REQ_TP_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.RCV_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.RCV_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.RTL_WH_CATG_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SEC_TO_MIN;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SHIP_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SHIP_FROM_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SHIP_FRT_AMT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SHIP_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SHIP_TO_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SHPG_SVC_LVL_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SO_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SRC_RTL_WH_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SVC_LVL;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.SVC_TASK_NUM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.TECH_TOC_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.TRGT_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_1001;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_1002;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_1003;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_1004;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_1011;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_3;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_6;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_8;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_9;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_POD_STS;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.VAL_SSL_CD;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.WH_PICK_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.WH_PICK_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.WMS_INTFC_DT;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.WMS_INTFC_TM;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.ZZM9000E;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.ZZZM9006E;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.ZZZM9012E;
import static com.canon.cusa.s21.batch.NLC.NLCB112001.constant.NLCB112001Constant.ZZZM9014E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.FCT_PRT_KPITMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
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
 * Part KPI Fact Creation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/02/2016   CITS            M.Naito         Create          N/A
 *</pre>
 */
public class NLCB112001 extends S21BatchMain {


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

    /** Target Time (Read Only Data) */
    private List<Map<String, Object>> trgtTmLst;

    /** Calendar Type CD (Read Only Data) */
    private List<Map<String, Object>> calTpCdLst;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NLCB112001().executeBatch(NLCB112001.class.getSimpleName());
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

        // Get Carrier Calendar Type CD
        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(PARAM_RPT_COND_CONST_GRP_ID, VAL_POD_STS);
        this.calTpCdLst = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCalTpCd", queryParam);

        // Get Target Date
        queryParam = new HashMap<String, Object>();
        queryParam.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
        queryParam.put(PARAM_RPT_COND_CONST_GRP_ID, VAL_SSL_CD);
        this.trgtTmLst = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTrgtDt", queryParam);

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
            FCT_PRT_KPITMsg tMsg = new FCT_PRT_KPITMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
            EZDTBLAccessor.removeByPartialKey(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), "]");
                throw new S21AbendException(ZZZM9014E, new String[] {errMsg});
            }
        }

        // Get Main Data For FCT_PRT_KPI Insert
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(PARAM_GLBL_CMPY_CD, this.glblCmpyCd);
        param.put(PARAM_TRGT_YR_MTH, this.trgtYrMth);
        param.put(KEY_SUBSTR_DT_LNGTH, VAL_8);
        param.put(KEY_SUBSTR_TM_FROM, VAL_9);
        param.put(KEY_SUBSTR_TM_LNGTH, VAL_6);
        param.put(KEY_RPT_COND_CONST_GRP_ID, VAL_POD_STS);
        param.put(KEY_APVL_HIST_ACT_TP_CD, VAL_3);
        param.put(KEY_PRCH_REQ_LINE_SUB_NUM_1, VAL_1001);
        param.put(KEY_PRCH_REQ_LINE_SUB_NUM_2, VAL_1002);
        param.put(KEY_PRCH_REQ_LINE_SUB_NUM_3, VAL_1003);
        param.put(KEY_PRCH_REQ_LINE_SUB_NUM_4, VAL_1004);
        param.put(KEY_PRCH_REQ_LINE_SUB_NUM_5, VAL_1011);
        param.put(KEY_SUBSTR_TRGT_YR_MTH_LNGTH, VAL_6);

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

                // Map Result Set Data To FCT_PRT_KPI
                FCT_PRT_KPITMsg tMsg = mapData(rs);

                // Insert FCT_PRT_KPI
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", PRCH_REQ_NUM, "=", rs.getString(PRCH_REQ_NUM),
                            ", ", PRCH_REQ_LINE_NUM, "=", rs.getString(PRCH_REQ_LINE_NUM), ", ", PRCH_REQ_LINE_SUB_NUM, "=", rs.getString(PRCH_REQ_LINE_SUB_NUM));
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
     * Map Result Set To FCT_PRT_KPITMsg
     */
    private FCT_PRT_KPITMsg mapData(ResultSet rs) throws SQLException {
        FCT_PRT_KPITMsg tMsg = new FCT_PRT_KPITMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dwhTrgtYrMth, this.trgtYrMth);
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqNum, rs.getString(PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineNum, rs.getString(PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineSubNum, rs.getBigDecimal(PRCH_REQ_LINE_SUB_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.soNum, rs.getString(SO_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, rs.getString(MDSE_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineTpCd, rs.getString(PRCH_REQ_LINE_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, rs.getString(PRCH_REQ_TP_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgCd, rs.getString(RTL_WH_CATG_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.techTocCd, rs.getString(TECH_TOC_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipToRtlWhCd, rs.getString(SHIP_TO_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipFromRtlWhCd, rs.getString(SHIP_FROM_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.srcRtlWhCd, rs.getString(SRC_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.destRtlWhCd, rs.getString(DEST_RTL_WH_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.carrCd, rs.getString(CARR_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, rs.getString(SHPG_SVC_LVL_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.shipFrtAmt, rs.getBigDecimal(SHIP_FRT_AMT));
        ZYPEZDItemValueSetter.setValue(tMsg.cratDt, rs.getString(CRAT_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.cratTm, rs.getString(CRAT_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqApvlDt, rs.getString(PRCH_REQ_APVL_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.prchReqApvlTm, rs.getString(PRCH_REQ_APVL_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsIntfcDt, rs.getString(WMS_INTFC_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.wmsIntfcTm, rs.getString(WMS_INTFC_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.whPickDt, rs.getString(WH_PICK_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.whPickTm, rs.getString(WH_PICK_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.shipDt, rs.getString(SHIP_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.shipTm, rs.getString(SHIP_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.carrPickUpDt, rs.getString(CARR_PICK_UP_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.carrPickUpTm, rs.getString(CARR_PICK_UP_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.carrAtDestDt, rs.getString(CARR_AT_DEST_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.carrAtDestTm, rs.getString(CARR_AT_DEST_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.delyDt, rs.getString(DELY_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.delyTm, rs.getString(DELY_TM));
        ZYPEZDItemValueSetter.setValue(tMsg.rcvDt, rs.getString(RCV_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.rcvTm, rs.getString(RCV_TM));
        if (ZYPCommonFunc.hasValue(rs.getString(CRAT_DT)) && ZYPCommonFunc.hasValue(rs.getString(PRCH_REQ_APVL_DT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.cratToApvlTermAot, getProcessingTime(rs.getString(CRAT_DT), rs.getString(CRAT_TM), rs.getString(PRCH_REQ_APVL_DT), rs.getString(PRCH_REQ_APVL_TM), tMsg));
        }
        if (ZYPCommonFunc.hasValue(rs.getString(PRCH_REQ_APVL_DT)) && ZYPCommonFunc.hasValue(rs.getString(WMS_INTFC_DT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.apvlToIntfcTermAot, getProcessingTime(rs.getString(PRCH_REQ_APVL_DT), rs.getString(PRCH_REQ_APVL_TM), rs.getString(WMS_INTFC_DT), rs.getString(WMS_INTFC_TM), tMsg));
        }
        if (ZYPCommonFunc.hasValue(rs.getString(WMS_INTFC_DT)) && ZYPCommonFunc.hasValue(rs.getString(WH_PICK_DT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.intfcToPickTermAot, getProcessingTime(rs.getString(WMS_INTFC_DT), rs.getString(WMS_INTFC_TM), rs.getString(WH_PICK_DT), rs.getString(WH_PICK_TM), tMsg));
        }
        if (ZYPCommonFunc.hasValue(rs.getString(WH_PICK_DT)) && ZYPCommonFunc.hasValue(rs.getString(SHIP_DT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.pickToShipTermAot, getProcessingTime(rs.getString(WH_PICK_DT), rs.getString(WH_PICK_TM), rs.getString(SHIP_DT), rs.getString(SHIP_TM), tMsg));
        }
        if (ZYPCommonFunc.hasValue(rs.getString(SHIP_DT)) && ZYPCommonFunc.hasValue(rs.getString(CARR_PICK_UP_DT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.shipToPickUpTermAot, getProcessingTime(rs.getString(SHIP_DT), rs.getString(SHIP_TM), rs.getString(CARR_PICK_UP_DT), rs.getString(CARR_PICK_UP_TM), tMsg));
        }
        BigDecimal pickUToAtDestTermAot = null;
        if (ZYPCommonFunc.hasValue(rs.getString(CARR_PICK_UP_DT)) && ZYPCommonFunc.hasValue(rs.getString(CARR_AT_DEST_DT))) {
            pickUToAtDestTermAot = getProcessingTime(rs.getString(CARR_PICK_UP_DT), rs.getString(CARR_PICK_UP_TM), rs.getString(CARR_AT_DEST_DT), rs.getString(CARR_AT_DEST_TM), tMsg);
            ZYPEZDItemValueSetter.setValue(tMsg.pickUpToAtDestTermAot, pickUToAtDestTermAot);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(CARR_AT_DEST_DT)) && ZYPCommonFunc.hasValue(rs.getString(DELY_DT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.atDestToDelyTermAot, getProcessingTime(rs.getString(CARR_AT_DEST_DT), rs.getString(CARR_AT_DEST_TM), rs.getString(DELY_DT), rs.getString(DELY_TM), tMsg));
        }
        if (ZYPCommonFunc.hasValue(rs.getString(DELY_DT)) && ZYPCommonFunc.hasValue(rs.getString(RCV_DT))) {
            ZYPEZDItemValueSetter.setValue(tMsg.delyToRcvTermAot, getProcessingTime(rs.getString(DELY_DT), rs.getString(DELY_TM), rs.getString(RCV_DT), rs.getString(RCV_TM), tMsg));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.fsrNum, rs.getString(FSR_NUM));
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, rs.getString(SVC_TASK_NUM));
        String overTrgtFlg = ZYPConstant.FLG_OFF_N;
        if (ZYPCommonFunc.hasValue(pickUToAtDestTermAot) && ZYPCommonFunc.hasValue(rs.getString(SHPG_SVC_LVL_CD))) {
            overTrgtFlg = getOverTrgtFlg(pickUToAtDestTermAot, rs.getString(SHPG_SVC_LVL_CD));
        }
        ZYPEZDItemValueSetter.setValue(tMsg.overTrgtFlg, overTrgtFlg);

        return tMsg;
    }


    /**
     * getProcessingTime
     * @param fromDt String (yyyyMMdd)
     * @param fromTm String (HHmmss)
     * @param toDt String  (yyyyMMdd)
     * @param toTm String (HHmmss)
     * @return BigDecimal
     */
     private BigDecimal getProcessingTime(String fromDt, String fromTm, String toDt, String toTm, FCT_PRT_KPITMsg tMsg) {

         double procTm = 0;
         BigDecimal bdProcTm = null;

         // Check date of parameter
         if (Integer.parseInt(fromDt) > Integer.parseInt(toDt) || tMsg.carrCd.getValue() == null) {
             bdProcTm = BigDecimal.valueOf(procTm);
             return bdProcTm;
         } else if (Integer.parseInt(fromDt) == Integer.parseInt(toDt) && Integer.parseInt(fromTm) >= Integer.parseInt(toTm)) {
             bdProcTm = BigDecimal.valueOf(procTm);
             return bdProcTm;
         }

         // Get Calendar Type CD
         String clndrTpCd = null;
         for (Map<String, Object> clndrTpCdMap : this.calTpCdLst) {
             if (tMsg.carrCd.getValue().equals((String) clndrTpCdMap.get(CARR_CD))) {
                 clndrTpCd = (String) clndrTpCdMap.get(CAL_TP_CD);
             }
         }

         SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
         double dblFromTm = 0;
         double dblToTm = 0;
         double diff;

         try {
             // Convert type of double from String
             dblFromTm = sdf.parse(fromDt + " " + fromTm).getTime();
             dblToTm = sdf.parse(toDt + " " + toTm).getTime();
         } catch (ParseException e) {
             String errMsg = S21StringUtil.concatStrings(tMsg.getReturnCode(), "] [DB_NAME:", tMsg.getTableName(), ", COLUMN:", PRCH_REQ_NUM, "=", tMsg.prchReqNum.getValue(),
                     ", ", PRCH_REQ_LINE_NUM, "=", tMsg.prchReqLineNum.getValue(), ", ", PRCH_REQ_LINE_SUB_NUM, "=", tMsg.prchReqLineSubNum.getValue());
             S21InfoLogOutput.println(ZZZM9012E, new String[] {errMsg});
             this.errRecCnt++;
         }

         // Sum processingTimes
         procTm = (dblToTm - dblFromTm) / (MIRI_SEC_TO_SEC * SEC_TO_MIN * MIN_TO_HOUR * HOUR_TO_DAY);

         // Get the number of non-operation days from CAL
         Map<String, Object> queryParam = new HashMap<String, Object>();
         queryParam.put(PARAM_GLBL_CMPY_CD, glblCmpyCd);
         queryParam.put(PARAM_CAL_TP_CD, clndrTpCd);
         queryParam.put(PARAM_CAL_DT_FROM, fromDt);
         queryParam.put(PARAM_CAL_DT_TO, toDt);
         diff = ((BigDecimal) ssmBatchClient.queryObject("getNonOperationDays", queryParam)).intValue();

         procTm = procTm - diff;

         // Round off to two decimal places
         bdProcTm = BigDecimal.valueOf(procTm).setScale(2, BigDecimal.ROUND_HALF_UP);
         return bdProcTm;
     }

    /**
     * getOverTrgtFlgBigDecimal
     * @param  val        BigDecimal
     * @param  shpgSvcLvl String
     * @return String
     */
     private String getOverTrgtFlg(BigDecimal val, String shpgSvcLvl) {

         String overTrgtFlg = ZYPConstant.FLG_OFF_N;
         BigDecimal date;

         // Get Target Time
         if (ZYPCommonFunc.hasValue(shpgSvcLvl)) {
             for (Map<String, Object> trgtDtMap : this.trgtTmLst) {
                 if (shpgSvcLvl.equals((String) trgtDtMap.get(SVC_LVL))) {
                     date = (BigDecimal) trgtDtMap.get(TRGT_DT);
                     // Compare data of FCT_PART_KPI to target Date
                     if (val.compareTo(date) > 0) {
                         overTrgtFlg = ZYPConstant.FLG_ON_Y;
                     }
                 }
             }
         }
         return overTrgtFlg;
     }

    @Override
    protected void termRoutine() {
        if (this.errRecCnt > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalRecCnt, this.errRecCnt, this.totalRecCnt);
    }
}
