/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB102001;

import static com.canon.cusa.s21.batch.NSB.NSBB102001.constant.NSBB102001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import business.db.FSR_AUD_LOGTMsg;
import business.db.FSR_HISTTMsg;
import business.db.SVC_TASK_HISTTMsg;
import business.db.FSR_VISIT_HISTTMsg;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;

/**
 * <pre>
 * Create FSR Audit Log Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/07/04   Hitachi         S.Naya          Create          QC#61374
 * </pre>
 */

public class NSBB102001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Time stamp */
    private String systemTimeStamp = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** TermCd */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Total Normal Count */
    private int totalNormalCount = 0;

    /** Total Err Count */
    private int totalErrCount = 0;

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_GLOBAL_COMPANY_CODE });
        }

        // Get System Timestamp
        this.systemTimeStamp = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.totalErrCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalNormalCount, this.totalErrCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB102001().executeBatch(NSBB102001.class.getSimpleName());
    }

    private void doProcess() {

        try {
            String targetRangeStartTime = null;
            String targetRangeEndTime = getLastExecTime(EXCLUDE_FLD_ON);

            // Check target table and Create FSR_AUD_LOG record.
            // Process at regular time intervals.
            do {
                this.normalCount = 0;
                
                targetRangeStartTime = targetRangeEndTime;
                targetRangeEndTime = getTargetRangeEndTime(targetRangeStartTime);

                if(targetRangeEndTime.compareTo(this.systemTimeStamp) > 0){
                    targetRangeEndTime = this.systemTimeStamp;
                }

                checkFsrHist(targetRangeStartTime, targetRangeEndTime);
                checkSvcTaskHist(targetRangeStartTime, targetRangeEndTime);
                checkCrsSvcRcvUpdStage(targetRangeStartTime, targetRangeEndTime);
                checkFsrVisitHist(targetRangeStartTime, targetRangeEndTime);

                commit();
                this.totalNormalCount += this.normalCount;
            } while (!targetRangeEndTime.equals(this.systemTimeStamp));

            // DutyManager does not have history, so it is processed separately.
            this.normalCount = 0;
            checkDtyMgr(getLastExecTime(EXCLUDE_FLD_OFF));

            commit();
            this.totalNormalCount += this.normalCount;

        } catch (SQLException se) {
            sqlExceptionHandler(se);
        } finally {
        }
    }

    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    private String getLastExecTime(String excludeFldMode) throws SQLException {
        Map<String, String> paramMap = new HashMap<String, String>();
        Map<String, String> rs = null;
        Map<String, String> rs2 = null;
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("batProcPgmId", BATCH_ID);
        paramMap.put("getLastExecTimeFlg", ZYPConstant.FLG_ON_Y);
        if (excludeFldMode.equals(EXCLUDE_FLD_ON)){
            paramMap.put("excludeFld", UPD_FLD_TXT_CD_DUTY_MGR);
        } else {
            paramMap.put("excludeFld", null);
        }
        
        rs = (Map<String, String>) this.ssmBatchClient.queryObject("getLastExecTime", paramMap);

        if (rs != null && rs.get("BAT_PROC_START_TS") != null){
            // Check Data registered in the past Abend.
            paramMap.put("lastExecTime",rs.get("BAT_PROC_START_TS"));
            rs2 = (Map<String, String>) ssmBatchClient.queryObject("getFsrAudLogOldRecord", paramMap);

            if (rs2 != null && rs2.get("CRAT_TS") != null && rs.get("BAT_PROC_START_TS").compareTo(rs2.get("CRAT_TS")) < 0) {
                return rs2.get("CRAT_TS");
            } else {
                return rs.get("BAT_PROC_START_TS");
            }
        } else {
            // Check Data registered in the past Abend.
            paramMap.put("lastExecTime",(String)BATCH_DEFAULT_EXEC_TIME);
            rs2 = (Map<String, String>) ssmBatchClient.queryObject("getFsrAudLogOldRecord", paramMap);

            if (rs2 != null && rs2.get("CRAT_TS") != null && BATCH_DEFAULT_EXEC_TIME.compareTo(rs2.get("CRAT_TS")) < 0) {
                return rs2.get("CRAT_TS");
            } else {
                return BATCH_DEFAULT_EXEC_TIME;
            }
        }
    }

    private String getTargetRangeEndTime(String timestamp) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(S21SessionHelper.EZD_TIME_FORMAT, Locale.US);
        TimeZone zone = TimeZone.getDefault();
        dateFormat.setTimeZone(zone);

        int year = Integer.parseInt(timestamp.substring(0, 4));
        int month = Integer.parseInt(timestamp.substring(4, 6));
        int day = Integer.parseInt(timestamp.substring(6, 8));
        int hour = Integer.parseInt(timestamp.substring(8, 10));
        int minute = Integer.parseInt(timestamp.substring(10, 12));
        int second = Integer.parseInt(timestamp.substring(12, 14));
        int millSecond = Integer.parseInt(timestamp.substring(14, 17));

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(zone);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millSecond);

        // Calculate the end point of the processing range.
        cal.add(Calendar.MINUTE, ADD_MINUTES);

        Date date = cal.getTime();
        return dateFormat.format(date);
    }

    private void checkFsrHist(String startTime, String endTime) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String beforeFsrNum = null;
        String beforeFsrStsCd = null;
        String beforeIttNum = null;

        try {
            // Get Target FSR_HIST
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("targetStartTime", startTime);
            paramMap.put("targetEndTime", endTime);

            ps = this.ssmLLClient.createPreparedStatement("getFsrHist", paramMap, execParam());
            rs = ps.executeQuery();

            while (rs.next()) {

                // Not Insert Case
                if (rs.getString(COL_NM_HIST_ACT_NM).equals(HIST_ACT_UPD)) {
                    // For the first record or if FSR_NUM is different compared to the previous record.
                    if (rs.isFirst() || (!rs.isFirst() && beforeFsrNum != null && !beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)))) {
                        // Find record with same FSR_NUM from past FSR_HIST.
                        paramMap.put("fsrNum",rs.getString(COL_NM_FSR_NUM));
                        FSR_HISTTMsg tMsgOld = (FSR_HISTTMsg) ssmBatchClient.queryObject("getFsrHistOldRecord", paramMap);

                        if (tMsgOld != null) {
                            // FSR Status
                            insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_FSR_STATUS, tMsgOld.fsrStsCd.getValue(), COL_NM_FSR_STATUS);
                            // Vendor Call #
                            insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_VENDOR_CALL, tMsgOld.ittNum.getValue(), COL_NM_FSR_VENDOR_CALL);
                        } else {
                            // FSR #
                            insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_FSR, null, COL_NM_FSR_NUM);
                            // FSR Status
                            insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_FSR_STATUS, null, COL_NM_FSR_STATUS);
                            // Vendor Call #
                            insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_VENDOR_CALL, null, COL_NM_FSR_VENDOR_CALL);
                        }
                    } else {
                        // FSR Status
                        insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_FSR_STATUS, beforeFsrStsCd, COL_NM_FSR_STATUS);
                        // Vendor Call #
                        insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_VENDOR_CALL, beforeIttNum, COL_NM_FSR_VENDOR_CALL);
                    }
                } else if (rs.getString(COL_NM_HIST_ACT_NM).equals(HIST_ACT_INS)) {
                    // FSR #
                    insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_FSR, null, COL_NM_FSR_NUM);
                    // FSR Status
                    insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_FSR_STATUS, null, COL_NM_FSR_STATUS);
                    // Vendor Call #
                    insertFsrAudLog(rs, true, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_VENDOR_CALL, null, COL_NM_FSR_VENDOR_CALL);
                } else {
                    continue;
                }

                // Keep as previous record.
                beforeFsrNum = rs.getString(COL_NM_FSR_NUM);
                beforeFsrStsCd = rs.getString(COL_NM_FSR_STATUS);
                beforeIttNum = rs.getString(COL_NM_FSR_VENDOR_CALL);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void checkSvcTaskHist(String startTime, String endTime) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String beforeFsrNum = null;
        String beforeSvcTaskNum = null;
        String beforeDsSvcCallTpCd = null;
        String beforeCrsSvcSrNum = null;

        try {
            // Get Target SVC_TASK_HIST
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("targetStartTime", startTime);
            paramMap.put("targetEndTime", endTime);

            ps = this.ssmLLClient.createPreparedStatement("getSvcTaskHist", paramMap, execParam());
            rs = ps.executeQuery();

            while (rs.next()) {

                // Not Insert Case
                if (rs.getString(COL_NM_HIST_ACT_NM).equals(HIST_ACT_UPD)) {
                    // For the first record or if FSR_NUM and SVC_TASK_NUM is different compared to the previous record.
                    if (rs.isFirst() || (!rs.isFirst() && beforeFsrNum != null && beforeSvcTaskNum != null &&
                            (!beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)) || 
                            (beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)) && !beforeSvcTaskNum.equals(rs.getString(COL_NM_SVC_TASK_NUM)))))) {

                        // Find record with same FSR_NUM and SVC_TASK_NUM from past SVC_TASK_HIST.
                        paramMap.put("fsrNum", rs.getString(COL_NM_FSR_NUM));
                        paramMap.put("svcTaskNum", rs.getString(COL_NM_SVC_TASK_NUM));
                        SVC_TASK_HISTTMsg tMsgOld = (SVC_TASK_HISTTMsg) ssmBatchClient.queryObject("getSvcTaskHistOldRecord", paramMap);

                        if (tMsgOld != null) {
                            // Task Type
                            insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK_TYPE, tMsgOld.dsSvcCallTpCd.getValue(), COL_NM_TASK_TYPE);
                            // Vendor Call #
                            insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_VENDOR_CALL, tMsgOld.crsSvcSrNum.getValue(), COL_NM_TASK_VENDOR_CALL);
                        } else {
                            // Task #
                            insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK, null, COL_NM_SVC_TASK_NUM);
                            // Task Type
                            insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK_TYPE, null, COL_NM_TASK_TYPE);
                            // Vendor Call #
                            insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_VENDOR_CALL, null, COL_NM_TASK_VENDOR_CALL);
                        }
                    } else {
                        // Task Type
                        insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK_TYPE, beforeDsSvcCallTpCd, COL_NM_TASK_TYPE);
                        // Vendor Call #
                        insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_VENDOR_CALL, beforeCrsSvcSrNum, COL_NM_TASK_VENDOR_CALL);
                    }
                } else if (rs.getString(COL_NM_HIST_ACT_NM).equals(HIST_ACT_INS)) {
                    // Task #
                    insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK, null, COL_NM_SVC_TASK_NUM);
                    // Task Type
                    insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK_TYPE, null, COL_NM_TASK_TYPE);
                    // Vendor Call #
                    insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_VENDOR_CALL, null, COL_NM_TASK_VENDOR_CALL);
                } else {
                    continue;
                }

                // Keep as previous record.
                beforeFsrNum = rs.getString(COL_NM_FSR_NUM);
                beforeSvcTaskNum = rs.getString(COL_NM_SVC_TASK_NUM);
                beforeDsSvcCallTpCd = rs.getString(COL_NM_TASK_TYPE);
                beforeCrsSvcSrNum = rs.getString(COL_NM_TASK_VENDOR_CALL);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void checkFsrVisitHist(String startTime, String endTime) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String beforeFsrNum = null;
        String beforeSvcTaskNum = null;
        String beforeFsrVisitNum = null;
        String beforeFsrVisitStsCd = null;
        String beforeTechCd = null;

        try {
            // Get Target FSR_VISIT_HIST
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("targetStartTime", startTime);
            paramMap.put("targetEndTime", endTime);

            ps = this.ssmLLClient.createPreparedStatement("getFsrVisitHist", paramMap, execParam());
            rs = ps.executeQuery();

            while (rs.next()) {

                // Not Insert Case
                if (rs.getString(COL_NM_HIST_ACT_NM).equals(HIST_ACT_UPD)) {
                    // For the first record or if FSR_NUM and SVC_TASK_NUM and FSR_VISIT_NUM is different compared to the previous record.
                    if (rs.isFirst() || (!rs.isFirst() && beforeFsrNum != null && beforeSvcTaskNum != null && beforeFsrVisitNum != null &&
                            (!beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)) ||
                            (beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)) && !beforeSvcTaskNum.equals(rs.getString(COL_NM_SVC_TASK_NUM))) ||
                            (beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)) && !beforeFsrVisitNum.equals(rs.getString(COL_NM_FSR_VISIT_NUM)))))) {

                        // Find record with same FSR_NUM and SVC_TASK_NUM and FSR_VISIT_NUM from past FSR_VISIT_HIST.
                        paramMap.put("fsrNum", rs.getString(COL_NM_FSR_NUM));
                        paramMap.put("svcTaskNum", rs.getString(COL_NM_SVC_TASK_NUM));
                        paramMap.put("fsrVisitNum", rs.getString(COL_NM_FSR_VISIT_NUM));
                        FSR_VISIT_HISTTMsg tMsgOld = (FSR_VISIT_HISTTMsg) ssmBatchClient.queryObject("getFsrVisitHistOldRecord", paramMap);

                        if (tMsgOld != null) {
                            // Task Status
                            insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK_STATUS, tMsgOld.fsrVisitStsCd.getValue(), COL_NM_TASK_STATUS);
                            // Assigned Tech
                            insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TECHNICIAN, tMsgOld.techCd.getValue(), COL_NM_TECHNICIAN);
                        } else {
                            // Task Status
                            insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK_STATUS, null, COL_NM_TASK_STATUS);
                            // Assigned Tech
                            insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TECHNICIAN, null, COL_NM_TECHNICIAN);
                        }
                    } else {
                        // Task Status
                        insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK_STATUS, beforeFsrVisitStsCd, COL_NM_TASK_STATUS);
                        // Assigned Tech
                        insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TECHNICIAN, beforeTechCd, COL_NM_TECHNICIAN);
                    }
                } else if (rs.getString(COL_NM_HIST_ACT_NM).equals(HIST_ACT_INS)) {
                    // Task Status
                    insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TASK_STATUS, null, COL_NM_TASK_STATUS);
                    // Assigned Tech
                    insertFsrAudLog(rs, false, rs.getString(COL_NM_HIST_CRAT_TS), UPD_FLD_TXT_CD_TECHNICIAN, null, COL_NM_TECHNICIAN);
                } else {
                    continue;
                }

                // Keep as previous record.
                beforeFsrNum = rs.getString(COL_NM_FSR_NUM);
                beforeSvcTaskNum = rs.getString(COL_NM_SVC_TASK_NUM);
                beforeFsrVisitNum = rs.getString(COL_NM_FSR_VISIT_NUM);
                beforeFsrVisitStsCd = rs.getString(COL_NM_TASK_STATUS);
                beforeTechCd = rs.getString(COL_NM_TECHNICIAN);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void checkCrsSvcRcvUpdStage(String startTime, String endTime) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String beforeFsrNum = null;
        String beforeSvcTaskNum = null;
        String beforeCrsSvcTaskStsCd = null;

        try {
            // Get Target CRS_SVC_RCV_UPD_STAGE
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("targetStartTime", startTime);
            paramMap.put("targetEndTime", endTime);
            paramMap.put("lastExecTime", startTime);
            paramMap.put("updFldTxt", UPD_FLD_TXT_CD_ESS_TASK_STATUS);
            paramMap.put("getLastExecTimeFlg", ZYPConstant.FLG_OFF_N);

            ps = this.ssmLLClient.createPreparedStatement("getCrsSvcRcvUpdStage", paramMap, execParam());
            rs = ps.executeQuery();

            while (rs.next()) {

                // For the first record or if FSR_NUM and SVC_TASK_NUM is different compared to the previous record.
                if (rs.isFirst() || (!rs.isFirst() && beforeFsrNum != null && beforeSvcTaskNum != null &&
                        (!beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)) ||
                        (beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)) && !beforeSvcTaskNum.equals(rs.getString(COL_NM_SVC_TASK_NUM)))))) {

                    // Find record with same FSR_NUM and SVC_TASK_NUM from past FSR_AUD_LOG.
                    paramMap.put("fsrNum", rs.getString(COL_NM_FSR_NUM));
                    paramMap.put("svcTaskNum", rs.getString(COL_NM_SVC_TASK_NUM));
                    Map<String, String> oldMap = (Map<String, String>) ssmBatchClient.queryObject("getFsrAudLogOldRecord", paramMap);

                    if (oldMap != null) {
                        // ESS Task Status
                        insertFsrAudLog(rs, false, rs.getString("EZUPTIME"), UPD_FLD_TXT_CD_ESS_TASK_STATUS, oldMap.get("NEW_VAL_TXT"), COL_NM_ESS_TASK_STATUS);
                    } else {
                        // ESS Task Status
                        insertFsrAudLog(rs, false, rs.getString("EZUPTIME"), UPD_FLD_TXT_CD_ESS_TASK_STATUS, null, COL_NM_ESS_TASK_STATUS);
                    }
                } else {
                    // ESS Task Status
                    insertFsrAudLog(rs, false, rs.getString("EZUPTIME"), UPD_FLD_TXT_CD_ESS_TASK_STATUS, beforeCrsSvcTaskStsCd, COL_NM_ESS_TASK_STATUS);
                }

                // Keep as previous record.
                beforeFsrNum = rs.getString(COL_NM_FSR_NUM);
                beforeSvcTaskNum = rs.getString(COL_NM_SVC_TASK_NUM);
                beforeCrsSvcTaskStsCd = rs.getString(COL_NM_ESS_TASK_STATUS);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void checkDtyMgr(String lastExecTime) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String beforeFsrNum = null;
        String beforeSvcTaskNum = null;
        
        try {
            // Get Target DTY_MGR
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("glblCmpyCd", this.glblCmpyCd);
            paramMap.put("lastExecTime", lastExecTime);
            paramMap.put("sysTmStamp", this.systemTimeStamp);
            paramMap.put("flgY", ZYPConstant.FLG_ON_Y);
            paramMap.put("svcByLineBizTpCdPps", SVC_BY_LINE_BIZ_TP_CD_PPS);
            paramMap.put("svcByLineBizTpCdLfs", SVC_BY_LINE_BIZ_TP_CD_LFS);
            paramMap.put("updFldTxt", UPD_FLD_TXT_CD_DUTY_MGR);
            paramMap.put("getLastExecTimeFlg", ZYPConstant.FLG_OFF_N);

            ps = this.ssmLLClient.createPreparedStatement("getDtyMgr", paramMap, execParam());
            rs = ps.executeQuery();

            while (rs.next()) {

                // For the first record or if FSR_NUM and SVC_TASK_NUM is different compared to the previous record.
                if (rs.isFirst() || (!rs.isFirst() && beforeFsrNum != null && beforeSvcTaskNum != null &&
                        (!beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)) ||
                        (beforeFsrNum.equals(rs.getString(COL_NM_FSR_NUM)) && !beforeSvcTaskNum.equals(rs.getString(COL_NM_SVC_TASK_NUM)))))) {

                    // Find record with same FSR_NUM and SVC_TASK_NUM from past FSR_AUD_LOG.
                    paramMap.put("fsrNum", rs.getString(COL_NM_FSR_NUM));
                    paramMap.put("svcTaskNum", rs.getString(COL_NM_SVC_TASK_NUM));
                    Map<String, String> oldMap = (Map<String, String>) ssmBatchClient.queryObject("getFsrAudLogOldRecord", paramMap);

                    if (oldMap != null) {
                        if (rs.getString("EZUPTIME_MGR").compareTo(oldMap.get("CRAT_TS")) < 0) {
                            // Duty Mgr
                            insertFsrAudLog(rs, false, this.systemTimeStamp, UPD_FLD_TXT_CD_DUTY_MGR, oldMap.get("NEW_VAL_TXT"), COL_NM_DUTY_MGR);
                        } else {
                            // Duty Mgr
                            insertFsrAudLog(rs, false, rs.getString("EZUPTIME_MGR"), UPD_FLD_TXT_CD_DUTY_MGR, oldMap.get("NEW_VAL_TXT"), COL_NM_DUTY_MGR);
                        }
                    } else {
                        // Duty Mgr
                        insertFsrAudLog(rs, false, rs.getString("EZUPTIME_TSK"), UPD_FLD_TXT_CD_DUTY_MGR, null, COL_NM_DUTY_MGR);
                    }
                }

                // Keep as previous record.
                beforeFsrNum = rs.getString(COL_NM_FSR_NUM);
                beforeSvcTaskNum = rs.getString(COL_NM_SVC_TASK_NUM);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private Boolean checkValDiff(String oldVal, String newVal){
        if ((oldVal != null && !"".equals(oldVal) && newVal == null) || (oldVal == null && newVal != null && !"".equals(newVal)) ||
                (oldVal != null && newVal != null && !oldVal.equals(newVal))) {
            return true;
        }
        else{
            return false;
        }
    }

    private void insertFsrAudLog(ResultSet rs, boolean fsrFlg, String cratTs, String updFldTxt, String oldValTxt, String newValColNm) throws SQLException {

        if (!checkValDiff(oldValTxt, rs.getString(newValColNm))){
            return;
        }

        FSR_AUD_LOGTMsg fsrAudLogTMsg = new FSR_AUD_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.glblCmpyCd,    rs.getString("GLBL_CMPY_CD"));
        ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.fsrAudLogPk,   ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.FSR_AUD_LOG_SQ));
        ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.fsrNum,        rs.getString(COL_NM_FSR_NUM));
        if (fsrFlg != true) {
            ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.svcTaskNum,    rs.getString(COL_NM_SVC_TASK_NUM));
            ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.fsrVisitNum,   rs.getString(COL_NM_FSR_VISIT_NUM));
        }
        ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.cratTs,        cratTs);
        if (USER_ID_LENGTH == rs.getString("EZUPUSERID").length()) {
            ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.usrId,     rs.getString("EZUPUSERID"));
        } else {
            ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.usrId,     USER_ID_SYSTEM);
        }
        ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.updFldTxt,     updFldTxt);
        ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.oldValTxt,     oldValTxt);
        ZYPEZDItemValueSetter.setValue(fsrAudLogTMsg.newValTxt,     rs.getString(newValColNm));

        S21FastTBLAccessor.insert(fsrAudLogTMsg);
        this.normalCount++;
        
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(fsrAudLogTMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0218E);
        }
    }
}
