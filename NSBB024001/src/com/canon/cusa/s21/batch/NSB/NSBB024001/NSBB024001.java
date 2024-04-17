/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB024001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import business.db.CRS_SVC_RCV_UPD_STAGETMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
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
 * <pre>
 * Receive Cross Service Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         Y.Tsuchimoto    Created         N/A
 * 2016/05/26   Hitachi         T.Tomita        Update          QC#8085
 * 2016/09/08   Hitachi         K.Yamada        Update          QC#13904
 * </pre>
 */
public class NSBB024001 extends S21BatchMain {

    /** [@] field is mandatory. */
    private static final String NSBM0032E = "NSBM0032E";

    /** Failed to register to the [@] table. */
    private static final String NSBM0068E = "NSBM0068E";

    /** message Item: GlobalCompanyCode */
    private static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: InterfaceId */
    private static final String MSG_ITEM_INTERFACE_ID = "Interface ID";

    /** MAX_COMMIT_NUMBER:1000 */
    private static final int MAX_COMMIT_NUMBER = 1000;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface Id */
    private String intfcId;

    /** Transaction Id List */
    private BigDecimal[] trxIdList;

    /** Commit Number */
    private int commitNumber;

    /** Total Commit Count */
    private int totalCommitCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

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

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.trxIdList = trxAccess.getIntegrationRecord(this.intfcId);
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        for (BigDecimal trxId : this.trxIdList) {
            insertCrsSvcRcvUpdStageData("getInputData", setSearchCondition(trxId));

            // Insert Transaction data
            trxAccess.endIntegrationProcess(this.intfcId, trxId);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB024001().executeBatch(NSBB024001.class.getSimpleName());
    }

    private void insertCrsSvcRcvUpdStageData(String sqlId, Map<String, Object> paramMap) {
        List<CRS_SVC_RCV_UPD_STAGETMsg> inTMsgList = new ArrayList<CRS_SVC_RCV_UPD_STAGETMsg>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement(sqlId, paramMap, execParam);
            rs = stmt.executeQuery();

            int commitCount = 0;

            // Insert CRS_SVC_RCV_UPD_STAGE
            while (rs.next()) {
                inTMsgList.add(setCreateValue(rs));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertInterfaceData(inTMsgList);
                    inTMsgList = new ArrayList<CRS_SVC_RCV_UPD_STAGETMsg>();
                    this.totalCommitCount += commitCount;
                }
            }
            if (inTMsgList.size() != 0) {
                commitCount = insertInterfaceData(inTMsgList);
                this.totalCommitCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int insertInterfaceData(List<CRS_SVC_RCV_UPD_STAGETMsg> inMsgLst) {
        CRS_SVC_RCV_UPD_STAGETMsg[] inMsgArray;
        inMsgArray = new CRS_SVC_RCV_UPD_STAGETMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NSBM0068E, new String[] {inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
    }

    private Map<String, Object> setSearchCondition(BigDecimal trxId) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("interfaceId", this.intfcId);
        inParam.put("transactionId", trxId);
        return inParam;
    }

    private CRS_SVC_RCV_UPD_STAGETMsg setCreateValue(ResultSet rs) throws SQLException {
        CRS_SVC_RCV_UPD_STAGETMsg inParam = new CRS_SVC_RCV_UPD_STAGETMsg();

        setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        BigDecimal crsSvcRcvUpdStagePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CRS_SVC_RCV_UPD_STAGE_SQ);
        setValue(inParam.crsSvcRcvUpdStagePk, crsSvcRcvUpdStagePk);
        setValue(inParam.crsSvcCratTsOrigTxt, rs.getString("CRAT_TS_DESC_TXT"));
        // mod start 2016/09/08 CSA Defect#13904
        //setValue(inParam.fsrNum, rs.getString("CRS_SVC_UPD_FSR_NUM"));
        setValue(inParam.crsSvcS21SvcTaskNum, rs.getString("CRS_SVC_UPD_FSR_NUM"));
        // mod end 2016/09/08 CSA Defect#13904
        setValue(inParam.crsSvcCallTpCd, rs.getString("CRS_SVC_CALL_TP_CD"));
        setValue(inParam.crsSvcFsrStsCd, rs.getString("CRS_SVC_FSR_STS_CD"));
        setValue(inParam.fsrPrtyTxt, rs.getString("FSR_PRTY_TXT"));
        setValue(inParam.mdlNm, rs.getString("CRS_SVC_MDL_NUM"));
        setValue(inParam.serNum, rs.getString("SER_NUM"));
        // del start 2016/05/26 CSA Defect#8085
//        setValue(inParam.crsSvcS21SvcTaskNum, rs.getString("CRS_SVC_S21_TASK_NUM"));
        // del end 2016/05/26 CSA Defect#8085
        setValue(inParam.crsSvcTaskNm, rs.getString("CRS_SVC_TASK_NM"));
        setValue(inParam.crsSvcTaskStsCd, rs.getString("CRS_SVC_TASK_STS_CD"));
        setValue(inParam.svcCmntTxt_01, rs.getString("SVC_CMNT_TXT_01"));
        setValue(inParam.crsSvcSrNum, rs.getString("CRS_SVC_SR_NUM"));
        setValue(inParam.crsSvcTaskNum, rs.getString("CRS_SVC_TASK_NUM"));
        setValue(inParam.crsSvcNoteIdAddTxt, rs.getString("CRS_SVC_NOTE_ID_ADD_TXT"));
        setValue(inParam.crsSvcResrcId, rs.getString("CRS_SVC_RESRC_ID"));
        setValue(inParam.svcCmntTxt_02, rs.getString("CRS_SVC_CMNT_TXT"));
        setValue(inParam.crsSvcFileNm, rs.getString("CRS_SVC_FILE_NM"));
        setValue(inParam.crsSvcActCd, rs.getString("CRS_SVC_ACT_CD"));
        setValue(inParam.crsSvcProcStsCd, PROC_STS.IN_COMPLETED);

        return inParam;
    }
}
