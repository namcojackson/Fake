/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB030001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CRS_SVC_RCV_HDR_STAGETMsg;
import business.db.CRS_SVC_RCV_MTR_STAGETMsg;
import business.db.CRS_SVC_RCV_PRT_STAGETMsg;
import business.db.CRS_SVC_RCV_TASK_STAGETMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 *  Receive Cross Service Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/14/2016   Hitachi         Y.Takeno        Create          N/A
 * 05/17/2016   Hitachi         Y.Takeno        Update          QC#6967
 * 10/05/2016   Hitachi         Y.Zhang         Update          QC#14750
 * 11/26/2019   Hitachi         Y.Takeno        Update          QC#53072
 *</pre>
 */
public class NSBB030001 extends S21BatchMain {

    /** [@] field is mandatory. */
    private static final String NSBM0032E = "NSBM0032E";

    /** Failed to register to the [@] table. */
    private static final String NSBM0068E = "NSBM0068E";

    /** message Item: GlobalCompanyCode */
    private static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: InterfaceId */
    private static final String MSG_ITEM_INTERFACE_ID = "Interface ID";

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface Id */
    private String intfcId;

    /** Transaction Id List */
    private BigDecimal[] trxIdList;

    /** insert Count */
    private int insertCount;

    /** Total Commit Count */
    private int totalCommitCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    /**
     * @param args parameter
     */
    public static void main(String[] args) {
        new NSBB030001().executeBatch(NSBB030001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();

        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();

        if (!hasValue(this.intfcId)) {
            throw new S21AbendException(NSBM0032E, new String[] {MSG_ITEM_INTERFACE_ID });
        }

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        this.trxIdList = this.trxAccess.getIntegrationRecord(this.intfcId);
        for (BigDecimal trxId : this.trxIdList) {

            // Insert Transaction data
            processInterfaceData(trxId);

            this.trxAccess.endIntegrationProcess(this.intfcId, trxId);

            // commit transaction
            commit();

            this.totalCommitCount = this.totalCommitCount + this.insertCount;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    private void processInterfaceData(BigDecimal trxId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> param = getSearchConditionHdrParam(trxId);
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        this.insertCount = 0;

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getNSBI0300_01", param, execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Insert CRS_SVC_RCV_HDR_STAGE
                CRS_SVC_RCV_HDR_STAGETMsg hdrTMsg = createCrsSvcRcvHdrStageTMsg(rs);
                EZDTBLAccessor.insert(hdrTMsg);
                if (hdrTMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
                    throw new S21AbendException(NSBM0068E, new String[] {hdrTMsg.getTableName() });
                }
                this.insertCount++;

                BigDecimal hdrStagePk = hdrTMsg.crsSvcRcvHdrStagePk.getValue();
                // START 2019/11/26 [QC#53072, ADD]
                BigDecimal segmentId = rs.getBigDecimal("SEGMENT_ID");
                // END   2019/11/26 [QC#53072, ADD]
                BigDecimal unitId = rs.getBigDecimal("UNIT_ID");

                // Insert CRS_SVC_RCV_TASK_STAGE
                // START 2019/11/26 [QC#53072, MOD]
                // insertCrsSvcRcvTaskStage(trxId, hdrStagePk, unitId);
                insertCrsSvcRcvTaskStage(trxId, hdrStagePk, segmentId, unitId);
                // END   2019/11/26 [QC#53072, MOD]

                // Insert CRS_SVC_RCV_MTR_STAGE
                // START 2019/11/26 [QC#53072, MOD]
                // insertCrsSvcRcvMtrStage(trxId, hdrStagePk, unitId);
                insertCrsSvcRcvMtrStage(trxId, hdrStagePk, segmentId, unitId);
                // END   2019/11/26 [QC#53072, MOD]

                // Insert CRS_SVC_RCV_PRT_STAGE
                // START 2019/11/26 [QC#53072, MOD]
                // insertCrsSvcRcvPrtStage(trxId, hdrStagePk, unitId);
                insertCrsSvcRcvPrtStage(trxId, hdrStagePk, segmentId, unitId);
                // END   2019/11/26 [QC#53072, MOD]
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private CRS_SVC_RCV_HDR_STAGETMsg createCrsSvcRcvHdrStageTMsg(ResultSet rs) throws SQLException {
        CRS_SVC_RCV_HDR_STAGETMsg tMsg = new CRS_SVC_RCV_HDR_STAGETMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.crsSvcRcvHdrStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CRS_SVC_RCV_HDR_STAGE_SQ));
        setValue(tMsg.crsSvcHdrId, rs.getString("CRS_SVC_HDR_ID"));
        setValue(tMsg.fsrNum, rs.getString("CRS_SVC_CLO_FSR_NUM"));
        setValue(tMsg.mdlNm, rs.getString("CRS_SVC_MDL_NM"));
        setValue(tMsg.serNum, rs.getString("CRS_SVC_SER_NUM"));
        setValue(tMsg.locNm, rs.getString("CRS_SVC_LOC_NM"));
        setValue(tMsg.crsSvcProcStsCd, PROC_STS.IN_COMPLETED);
        return tMsg;
    }

    // START 2019/11/26 [QC#53072, MOD]
    private void insertCrsSvcRcvTaskStage(BigDecimal trxId, BigDecimal hdrStagePk, BigDecimal segmentId, BigDecimal unitId) throws SQLException {
    // END   2019/11/26 [QC#53072, MOD]
        PreparedStatement stmt = null;
        ResultSet rs = null;
        // START 2019/11/26 [QC#53072, MOD]
        // Map<String, Object> param = getSearchConditionParam(trxId, unitId);
        Map<String, Object> param = getSearchConditionParam(trxId, segmentId, unitId);
        // END   2019/11/26 [QC#53072, MOD]
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getNSBI0300_02", param, execParam);
            rs = stmt.executeQuery();

            // START 2016/05/17 Y.Takeno [QC#6967, MOD]
            List<CRS_SVC_RCV_TASK_STAGETMsg> tMsgList = new ArrayList<CRS_SVC_RCV_TASK_STAGETMsg>();
            while (rs.next()) {
                // Insert CRS_SVC_RCV_TASK_STAGE
                tMsgList.add(createCrsSvcRcvTaskStageTMsg(hdrStagePk, rs));
                this.insertCount++;
            }

            if (tMsgList.size() == 0) {
                return;
            }
            int insCount = S21FastTBLAccessor.insert(tMsgList.toArray(new CRS_SVC_RCV_TASK_STAGETMsg[] {}));
            if (insCount != tMsgList.size()) {
                throw new S21AbendException(NSBM0068E, new String[] {new CRS_SVC_RCV_TASK_STAGETMsg().getTableName() });
            }
            // END   2016/05/17 Y.Takeno [QC#6967, MOD]

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private CRS_SVC_RCV_TASK_STAGETMsg createCrsSvcRcvTaskStageTMsg(BigDecimal crsSvcRcvHdrStagePk, ResultSet rs) throws SQLException {
        CRS_SVC_RCV_TASK_STAGETMsg tMsg = new CRS_SVC_RCV_TASK_STAGETMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.crsSvcRcvHdrStagePk, crsSvcRcvHdrStagePk);
        setValue(tMsg.crsSvcRcvTaskStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CRS_SVC_RCV_TASK_STAGE_SQ));
        setValue(tMsg.crsSvcHdrId, rs.getString("CRS_SVC_HDR_ID"));
        setValue(tMsg.fsrNum, rs.getString("CRS_SVC_CLO_FSR_NUM"));
        setValue(tMsg.crsSvcTechCd, rs.getString("CRS_SVC_TECH_CD"));
        setValue(tMsg.crsSvcTechNm, rs.getString("CRS_SVC_TECH_NM"));
        setValue(tMsg.fsrVisitArvDt, rs.getString("FSR_VISIT_ARV_DT"));
        setValue(tMsg.crsSvcTrvlStartHourMn, rs.getString("CRS_SVC_TRVL_START_HOUR_MN"));
        setValue(tMsg.crsSvcTrvlEndHourMn, rs.getString("CRS_SVC_TRVL_END_HOUR_MN"));
        setValue(tMsg.trvlLeadTxt, rs.getString("TRVL_LEAD_TXT"));
        setValue(tMsg.crsSvcLborStartHourMn, rs.getString("CRS_SVC_LBOR_START_HOUR_MN"));
        setValue(tMsg.crsSvcLborEndHourMn, rs.getString("CRS_SVC_LBOR_END_HOUR_MN"));
        setValue(tMsg.crsSvcRsnCd, rs.getString("CRS_SVC_RSN_CD"));
        // START 2016/10/05 Y.Zhang [QC#14750, MOD]
        setValue(tMsg.crsSvcRcvSrNum, rs.getString("CRS_SVC_CLO_SR_NUM"));
        setValue(tMsg.crsSvcTaskNum, rs.getString("CRS_SVC_CLO_TASK_NUM"));
        // END 2016/10/05 Y.Zhang [QC#14750, MOD]
        setValue(tMsg.crsSvcProcStsCd, PROC_STS.IN_COMPLETED);
        return tMsg;
    }

    // START 2019/11/26 [QC#53072, MOD]
    private void insertCrsSvcRcvMtrStage(BigDecimal trxId, BigDecimal hdrStagePk, BigDecimal segmentId, BigDecimal unitId) throws SQLException {
    // END   2019/11/26 [QC#53072, MOD]
        PreparedStatement stmt = null;
        ResultSet rs = null;
        // START 2019/11/26 [QC#53072, MOD]
        // Map<String, Object> param = getSearchConditionParam(trxId, unitId);
        Map<String, Object> param = getSearchConditionParam(trxId, segmentId, unitId);
        // END   2019/11/26 [QC#53072, MOD]
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getNSBI0300_03", param, execParam);
            rs = stmt.executeQuery();

            // START 2016/05/17 Y.Takeno [QC#6967, MOD]
            List<CRS_SVC_RCV_MTR_STAGETMsg> tMsgList = new ArrayList<CRS_SVC_RCV_MTR_STAGETMsg>();
            while (rs.next()) {
                // Insert CRS_SVC_RCV_MTR_STAGE
                tMsgList.add(createCrsSvcRcvMtrStageTMsg(hdrStagePk, rs));
                this.insertCount++;
            }

            if (tMsgList.size() == 0) {
                return;
            }
            int insCount = S21FastTBLAccessor.insert(tMsgList.toArray(new CRS_SVC_RCV_MTR_STAGETMsg[] {}));
            if (insCount != tMsgList.size()) {
                throw new S21AbendException(NSBM0068E, new String[] {new CRS_SVC_RCV_MTR_STAGETMsg().getTableName() });
            }
            // END  2016/05/17 Y.Takeno [QC#6967, MOD]

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private CRS_SVC_RCV_MTR_STAGETMsg createCrsSvcRcvMtrStageTMsg(BigDecimal crsSvcRcvHdrStagePk, ResultSet rs) throws SQLException {
        CRS_SVC_RCV_MTR_STAGETMsg tMsg = new CRS_SVC_RCV_MTR_STAGETMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.crsSvcRcvHdrStagePk, crsSvcRcvHdrStagePk);
        setValue(tMsg.crsSvcRcvMtrStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CRS_SVC_RCV_MTR_STAGE_SQ));
        setValue(tMsg.crsSvcHdrId, rs.getString("CRS_SVC_HDR_ID"));
        setValue(tMsg.crsSvcMtrLbCd, rs.getString("CRS_SVC_MTR_ID"));
        setValue(tMsg.fsrNum, rs.getString("CRS_SVC_CLO_SR_NUM"));
        setValue(tMsg.crsSvcTaskNum, rs.getString("PROC_SVC_TASK_NUM"));
        setValue(tMsg.crsSvcTestMtrOrigTxt, rs.getString("CRS_SVC_TEST_MTR_ORIG_TXT"));
        setValue(tMsg.crsSvcReadMtrOrigTxt, rs.getString("CRS_SVC_READ_MTR_ORIG_TXT"));
        setValue(tMsg.crsSvcProcStsCd, PROC_STS.IN_COMPLETED);
        return tMsg;
    }

    // START 2019/11/26 [QC#53072, MOD]
    // private void insertCrsSvcRcvPrtStage(BigDecimal trxId, BigDecimal hdrStagePk, BigDecimal unitId) throws SQLException {
    private void insertCrsSvcRcvPrtStage(BigDecimal trxId, BigDecimal hdrStagePk, BigDecimal segmentId, BigDecimal unitId) throws SQLException {
    // END   2019/11/26 [QC#53072, MOD]
        PreparedStatement stmt = null;
        ResultSet rs = null;
        // START 2019/11/26 [QC#53072, MOD]
        // Map<String, Object> param = getSearchConditionParam(trxId, unitId);
        Map<String, Object> param = getSearchConditionParam(trxId, segmentId, unitId);
        // END   2019/11/26 [QC#53072, MOD]
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getNSBI0300_04", param, execParam);
            rs = stmt.executeQuery();

            // START 2016/05/17 Y.Takeno [QC#6967, MOD]
            List<CRS_SVC_RCV_PRT_STAGETMsg> tMsgList = new ArrayList<CRS_SVC_RCV_PRT_STAGETMsg>();
            while (rs.next()) {
                // Insert CRS_SVC_RCV_PRT_STAGE
                tMsgList.add(createCrsSvcRcvPrtStageTMsg(hdrStagePk, rs));
                this.insertCount++;
            }

            if (tMsgList.size() == 0) {
                return;
            }
            int insCount = S21FastTBLAccessor.insert(tMsgList.toArray(new CRS_SVC_RCV_PRT_STAGETMsg[] {}));
            if (insCount != tMsgList.size()) {
                throw new S21AbendException(NSBM0068E, new String[] {new CRS_SVC_RCV_PRT_STAGETMsg().getTableName() });
            }
            // END  2016/05/17 Y.Takeno [QC#6967, MOD]

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private CRS_SVC_RCV_PRT_STAGETMsg createCrsSvcRcvPrtStageTMsg(BigDecimal crsSvcRcvHdrStagePk, ResultSet rs) throws SQLException {
        CRS_SVC_RCV_PRT_STAGETMsg tMsg = new CRS_SVC_RCV_PRT_STAGETMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.crsSvcRcvHdrStagePk, crsSvcRcvHdrStagePk);
        setValue(tMsg.crsSvcRcvPrtStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CRS_SVC_RCV_PRT_STAGE_SQ));
        setValue(tMsg.crsSvcHdrId, rs.getString("CRS_SVC_HDR_ID"));
        setValue(tMsg.crsSvcPrtMdseCd, rs.getString("CRS_SVC_PRT_MDSE_CD"));
        setValue(tMsg.fsrNum, rs.getString("CRS_SVC_CLO_SR_NUM"));
        setValue(tMsg.crsSvcTaskNum, rs.getString("CRS_SVC_CLO_TASK_NUM"));
        setValue(tMsg.crsSvcPrtQtyOrigTxt, rs.getString("CRS_SVC_PRT_QTY_ORIG_TXT"));
        setValue(tMsg.crsSvcProcStsCd, PROC_STS.IN_COMPLETED);
        return tMsg;
    }

    private Map<String, Object> getSearchConditionHdrParam(BigDecimal trxId) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("interfaceId", this.intfcId);
        inParam.put("transactionId", trxId);
        return inParam;
    }

    // START 2019/11/26 [QC#53072, MOD]
    // private Map<String, Object> getSearchConditionParam(BigDecimal trxId, BigDecimal unitId) {
    private Map<String, Object> getSearchConditionParam(BigDecimal trxId, BigDecimal segmentId, BigDecimal unitId) {
    // END   2019/11/26 [QC#53072, MOD]
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("interfaceId", this.intfcId);
        inParam.put("transactionId", trxId);
        // START 2019/11/26 [QC#53072, MOD]
        inParam.put("segmentId", segmentId);
        // END   2019/11/26 [QC#53072, MOD]
        inParam.put("unitId", unitId);
        return inParam;
    }
}
