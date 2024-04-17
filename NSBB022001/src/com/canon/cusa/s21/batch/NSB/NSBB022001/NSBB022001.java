/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB022001;

import static com.canon.cusa.s21.batch.NSB.NSBB022001.constant.NSBB022001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CRS_SVC_RCV_RQST_STAGETMsg;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.NSBI0220_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Create Cross Service Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/27   Hitachi         T.Mizuki        Create          N/A
 * 2018/06/14   Hitachi         K.Kim           Update          QC#26559
 * 2019/05/20   Hitachi         K.Kim           Update          QC#50119
 * 2019/08/12   Hitachi         K.Kim           Update          QC#51271
 * </pre>
 */
public class NSBB022001 extends S21BatchMain {

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Unit ID */
    private int unitId;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface Id */
    private String intfcId;

    /** Commit Number */
    private int commitNumber;

    /** Total Commit Count */
    private int totalCommitCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /** DS_BIZ_PROC_LOG */
    private DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg = null;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();

        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(NSBM0032E, new String[] {MSG_ITEM_INTERFACE_ID });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get System Timestamp
        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = this.trxAccess.getNextTransactionId();
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.unitId = 1;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    }

    @Override
    protected void mainRoutine() {

        // Get DsBizProcLog
        this.dsBizProcLogTMsg = getDsBizProcLog();

        // insert interface data
        doProcess();

    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB022001().executeBatch(NSBB022001.class.getSimpleName());
    }

    /**
     * 
     */
    private void doProcess() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            // Get DsBizProcLog
            this.dsBizProcLogTMsg = getDsBizProcLog();
            String dsBizLastUpdTs = null;
            if (this.dsBizProcLogTMsg != null) {
                dsBizLastUpdTs = this.dsBizProcLogTMsg.dsBizLastUpdTs.getValue();
            }
            // Search Target Data
            stmt = this.ssmLLClient.createPreparedStatement("getInputData", setSearchCondition(dsBizLastUpdTs), execParam);
            rsSet = stmt.executeQuery();

            // Insert CRS_SVC_RCV_UPD_STAGE
            // Update CRS_SVC_RCV_RQST_STAGE
            while (rsSet.next()) {
                insertInterfaceData(setCreateValue(rsSet));
                // START 2018/06/14 K.Kim [QC#26559,ADD]
                updateCrsSvcRcvRqstStage(setUpdateValue(rsSet));
                // END 2018/06/14 K.Kim [QC#26559,ADD]
                commit();
                this.totalCommitCount++;
                this.unitId++;
            }
            if (this.totalCommitCount != 0) {
                // insert INTERFACE_TRANSACTION
                trxAccess.createIntegrationRecordForBatch(this.intfcId, this.trxId);
            }

            if (this.dsBizProcLogTMsg != null) {
                updateDsBizProcLog();
            } else {
                insertDsBizProcLog();
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private void insertInterfaceData(NSBI0220_01TMsg inTMsg) {
        S21FastTBLAccessor.insert(inTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0164E, new String[] {TABLE_INTERFACE });
        }
        return;
    }

    // START 2018/06/14 K.Kim [QC#26559,ADD]
    private void updateCrsSvcRcvRqstStage(CRS_SVC_RCV_RQST_STAGETMsg inTMsg) {
        S21FastTBLAccessor.update(inTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0120E, new String[] {"CRS_SVC_RCV_RQST_STAGE"});
        }
        return;
    }
    // END 2018/06/14 K.Kim [QC#26559,ADD]

    private Map<String, Object> setSearchCondition(String dsBizLastUpdTs) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("lastUpdTs", dsBizLastUpdTs);
        // START 2019/08/12 [QC#51271,MOD]
        // String[] svcTaskStsList = new String[NUM_3];
        String[] svcTaskStsList = new String[2];
        svcTaskStsList[0] = SVC_TASK_STS.COMPLETED;
        svcTaskStsList[1] = SVC_TASK_STS.CLOSED;
        // svcTaskStsList[2] = SVC_TASK_STS.CANCELLED;
        inParam.put("svcTaskStsCdList", svcTaskStsList);
        inParam.put("svcCallSrcTp", SVC_CALL_SRC_TP.CROSS_SERVICE);
        // String[] svcDisptEventList = new String[2];
        // svcDisptEventList[0] = SVC_DISPT_EVENT.CLOSE;
        // svcDisptEventList[1] = SVC_DISPT_EVENT.CANCEL;
        // inParam.put("svcDisptEventCdList", svcDisptEventList);
        inParam.put("svcDisptEventCd", SVC_DISPT_EVENT.CLOSE);
        // END 2019/08/12 [QC#51271,MOD]
        // START 2018/06/14 K.Kim [QC#26559,ADD]
        inParam.put("dsCondConstGrpId", DS_COND_CONST_GRP_ID);
        // END 2018/06/14 K.Kim [QC#26559,ADD]
        return inParam;
    }

    private NSBI0220_01TMsg setCreateValue(ResultSet rs) throws SQLException {
        NSBI0220_01TMsg inParam = new NSBI0220_01TMsg();

        setValue(inParam.interfaceId, this.intfcId);
        setValue(inParam.transactionId, this.trxId);
        setValue(inParam.segmentId, BigDecimal.ONE);
        setValue(inParam.unitId, BigDecimal.valueOf(this.unitId));
        setValue(inParam.seqNumber, BigDecimal.ONE);
        setValue(inParam.crsSvcCratTsOrigTxt, rs.getString("CRS_SVC_CRAT_TS_ORIG_TXT"));
        setValue(inParam.crsSvcSrNum, rs.getString("CRS_SVC_SR_NUM"));
        setValue(inParam.svcCallSrcTpCd, rs.getString("SVC_CALL_SRC_TP_CD"));
        setValue(inParam.fsrStsCd, rs.getString("FSR_STS_CD"));
        setValue(inParam.mdseCd, rs.getString("MDSE_CD"));
        setValue(inParam.serNum, rs.getString("SER_NUM"));
        // START 2019/05/20 [QC#50119,MOD]
        // setValue(inParam.fsrNum, rs.getString("FSR_NUM"));
        setValue(inParam.fsrNum, rs.getString("SVC_TASK_NUM"));
        // END 2019/05/20 [QC#50119,MOD]
        // START 2018/06/14 K.Kim [QC#26559,MOD]
        // setValue(inParam.crsSvcTaskStsCd, rs.getString("CRS_SVC_TASK_STS_CD"));
        setValue(inParam.crsSvcTaskStsCd, rs.getString("DS_COND_CONST_VAL_TXT_01"));
        // END 2018/06/14 K.Kim [QC#26559,MOD]
        setValue(inParam.onaJobNum, rs.getString("ONA_JOB_NUM"));
        setValue(inParam.crsSvcTaskNum, rs.getString("CRS_SVC_TASK_NUM"));
        setValue(inParam.onaNoteId, rs.getString("ONA_NOTE_ID"));
        setValue(inParam.crsSvcSrCmntTxt, rs.getString("CRS_SVC_SR_CMNT_TXT"));
        setValue(inParam.crsSvcNoteTpTxt, rs.getString("CRS_SVC_NOTE_TP_TXT"));
        setValue(inParam.crsSvcNoteId, rs.getString("CRS_SVC_NOTE_ID"));
        setValue(inParam.crsSvcFileNm, rs.getString("CRS_SVC_FILE_NM"));
        setValue(inParam.crsSvcActCd, rs.getString("CRS_SVC_ACT_CD"));

        return inParam;
    }

    // START 2018/06/14 K.Kim [QC#26559,ADD]
    private CRS_SVC_RCV_RQST_STAGETMsg setUpdateValue(ResultSet rs) throws SQLException {
        CRS_SVC_RCV_RQST_STAGETMsg inParam = new CRS_SVC_RCV_RQST_STAGETMsg();

        setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        setValue(inParam.crsSvcRcvRqstStagePk, rs.getBigDecimal("CRS_SVC_RCV_RQST_STAGE_PK"));
        inParam = (CRS_SVC_RCV_RQST_STAGETMsg) S21FastTBLAccessor.findByKeyForUpdate(inParam);

        setValue(inParam.crsSvcTaskStsCd, rs.getString("DS_COND_CONST_VAL_TXT_01"));

        return inParam;
    }
    // END 2018/06/14 K.Kim [QC#26559,ADD]

    private DS_BIZ_PROC_LOGTMsg getDsBizProcLog() {

        DS_BIZ_PROC_LOGTMsg inMsg = new DS_BIZ_PROC_LOGTMsg();

        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("procPgmId01", PROGRAM_ID);

        DS_BIZ_PROC_LOGTMsgArray tMsgAry = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            return null;
        } else {
            return (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsgAry.no(0));
        }
    }

    private void insertDsBizProcLog() {

        dsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();

        setValue(dsBizProcLogTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsBizProcLogTMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
        setValue(dsBizProcLogTMsg.procPgmId, PROGRAM_ID);
        setValue(dsBizProcLogTMsg.dsBizProcDt, this.currentSystemTs.substring(0, NUM_8));
        setValue(dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        setValue(dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTs);
        setValue(dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTs);

        S21FastTBLAccessor.insert(dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsBizProcLogTMsg.getReturnCode())) {
            String message = PROC_PGM_ID + " = " + this.dsBizProcLogTMsg.procPgmId.getValue();
            throw new S21AbendException(NSBM0121E, new String[] {this.dsBizProcLogTMsg.getTableName(), message });
        }
    }

    private void updateDsBizProcLog() {

        setValue(dsBizProcLogTMsg.dsBizProcDt, this.currentSystemTs.substring(0, NUM_8));
        setValue(dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        setValue(dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTs);
        setValue(dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTs);

        S21FastTBLAccessor.update(dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsBizProcLogTMsg.getReturnCode())) {
            String message = DS_BIZ_PROC_LOG_PK + " = " + this.dsBizProcLogTMsg.dsBizProcLogPk.getValue();
            throw new S21AbendException(NSBM0120E, new String[] {this.dsBizProcLogTMsg.getTableName(), message });
        }
    }
}
