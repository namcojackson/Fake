/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB016001;

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

import business.db.CRS_SVC_RCV_RQST_STAGETMsg;

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
 * Receive Cross Service Request
 * 
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 2016/02/29   Hitachi         T.Tsuchida      Created         N/A
 * </pre>
 */
public class NSBB016001 extends S21BatchMain {

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

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Initialize
        this.trxAccess = new S21TransactionTableAccessor();
        this.trxIdList = this.trxAccess.getIntegrationRecord(this.intfcId);
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        for (BigDecimal trxId : this.trxIdList) {
            insertCrsSvcRcvRqstStageData("getInputData", setSearchCondition(trxId));

            // Insert Transaction data
            this.trxAccess.endIntegrationProcess(this.intfcId, trxId);
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
        new NSBB016001().executeBatch(NSBB016001.class.getSimpleName());
    }

    private void insertCrsSvcRcvRqstStageData(String sqlId, Map<String, Object> paramMap) {
        List<CRS_SVC_RCV_RQST_STAGETMsg> inTMsgList = new ArrayList<CRS_SVC_RCV_RQST_STAGETMsg>();

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

            // Insert CRS_SVC_RCV_RQST_STAGE
            while (rs.next()) {
                inTMsgList.add(setCreateValue(rs));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertInterfaceData(inTMsgList);
                    inTMsgList = new ArrayList<CRS_SVC_RCV_RQST_STAGETMsg>();
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

    private int insertInterfaceData(List<CRS_SVC_RCV_RQST_STAGETMsg> inMsgLst) {
        CRS_SVC_RCV_RQST_STAGETMsg[] inMsgArray;
        inMsgArray = new CRS_SVC_RCV_RQST_STAGETMsg[inMsgLst.size()];
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

    private CRS_SVC_RCV_RQST_STAGETMsg setCreateValue(ResultSet rs) throws SQLException {
        CRS_SVC_RCV_RQST_STAGETMsg inParam = new CRS_SVC_RCV_RQST_STAGETMsg();

        setValue(inParam.glblCmpyCd, this.glblCmpyCd);
        BigDecimal crsSvcRcvRqstStagePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CRS_SVC_RCV_RQST_STAGE_SQ);
        setValue(inParam.crsSvcRcvRqstStagePk, crsSvcRcvRqstStagePk);
        setValue(inParam.crsSvcSrNum, rs.getString("CRS_SVC_SR_NUM"));
        setValue(inParam.crsSvcCratTsOrigTxt, rs.getString("CRAT_TS_DESC_TXT"));
        setValue(inParam.crsSvcCustNum, rs.getString("CUST_NUM_DESC_TXT"));
        setValue(inParam.custSiteTxt, rs.getString("CUST_SITE_TXT"));
        setValue(inParam.crsSvcCustNm, rs.getString("CRS_SVC_CUST_NM"));
        setValue(inParam.crsSvcFirstLineAddr, rs.getString("CRS_SVC_FIRST_ADDR_DESC_TXT"));
        setValue(inParam.crsSvcScdLineAddr, rs.getString("CRS_SVC_SCD_ADDR_DESC_TXT"));
        setValue(inParam.crsSvcThirdLineAddr, rs.getString("CRS_SVC_THIRD_ADDR_DESC_TXT"));
        setValue(inParam.crsSvcFrthLineAddr, rs.getString("CRS_SVC_FRTH_ADDR_DESC_TXT"));
        setValue(inParam.crsSvcCtyAddr, rs.getString("CRS_SVC_CTY_ADDR_DESC_TXT"));
        setValue(inParam.crsSvcStCd, rs.getString("CRS_SVC_ST_CD_DESC_TXT"));
        setValue(inParam.crsSvcPostCd, rs.getString("CRS_SVC_POST_CD_DESC_TXT"));
        setValue(inParam.crsSvcCntyNm, rs.getString("CRS_SVC_CNTY_NM"));
        setValue(inParam.crsSvcCtryNm, rs.getString("CRS_SVC_CTRY_NM"));
        setValue(inParam.crsSvcCtacFirstNm, rs.getString("CRS_SVC_CTAC_FIRST_NM"));
        setValue(inParam.crsSvcCtacLastNm, rs.getString("CRS_SVC_CTAC_LAST_NM"));
        setValue(inParam.crsSvcPhoNum, rs.getString("CRS_SVC_PHO_NUM"));
        setValue(inParam.mdlNm, rs.getString("CRS_SVC_MDL_NUM"));
        setValue(inParam.serNum, rs.getString("SER_NUM"));
        setValue(inParam.crsSvcPoTxt, rs.getString("PO_DESC_TXT"));
        setValue(inParam.crsSvcUrgCd, rs.getString("EMER_DESC_TXT"));
        setValue(inParam.crsSvcDeferCd, rs.getString("DEFER_CD_DESC_TXT"));
        setValue(inParam.crsSvcPblmCmntTxt, rs.getString("PBLM_CMNT_TXT"));
        setValue(inParam.crsSvcPblmCd, rs.getString("PBLM_CD_DESC_TXT"));
        setValue(inParam.crsSvcSrCmntTxt, rs.getString("FSR_CMNT_TXT"));
        setValue(inParam.crsSvcNoteTpTxt, rs.getString("CRS_SVC_NOTE_TP_TXT"));
        setValue(inParam.crsSvcNoteId, rs.getString("CRS_SVC_NOTE_ID"));
        setValue(inParam.actvContrStsCd, rs.getString("ACTV_CONTR_STS_CD"));
        setValue(inParam.crsSvcTaskNum, rs.getString("CRS_SVC_TASK_NUM"));
        setValue(inParam.crsSvcTaskStsCd, rs.getString("CRS_SVC_TASK_STS_CD"));
        setValue(inParam.crsSvcTaskTpCd, rs.getString("CRS_SVC_TASK_TP_CD"));
        setValue(inParam.crsSvcTaskPrtyNum, rs.getString("CRS_SVC_TASK_PRTY_NUM"));
        setValue(inParam.crsSvcTaskDescTxt, rs.getString("CRS_SVC_TASK_DESC_TXT"));
        setValue(inParam.crsSvcTaskTsOrigTxt, rs.getString("CRS_SVC_TASK_CRAT_DESC_TXT"));
        setValue(inParam.onaJobNum, rs.getString("ONA_JOB_NUM"));
        setValue(inParam.fsrNum, rs.getString("CRS_SVC_FSR_NUM"));
        setValue(inParam.onaNoteId, rs.getString("ONA_NOTE_ID"));
        setValue(inParam.crsSvcFileNm, rs.getString("CRS_SVC_FILE_NM"));
        setValue(inParam.crsSvcActCd, rs.getString("CRS_SVC_ACT_CD"));
        setValue(inParam.crsSvcRqstSqOrigTxt, rs.getString("RQST_SQ_DESC_TXT"));
        setValue(inParam.crsSvcProcStsCd, PROC_STS.IN_COMPLETED);

        return inParam;
    }
}
