/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB304001;

import static com.canon.cusa.s21.batch.NFA.NFAB304001.constant.NFAB304001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.COA_HRCHTMsg;
import business.db.NFAI0110_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * COA Hierarchy Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/21   Hitachi         T.Mizuki        Created         N/A
 * 2016/10/06   Hitachi         K.Kasai         Updated         QC#14932
 * 2016/10/20   Fujitsu         T.Murai         Updated         QC#15339
 * </pre>
 */
public class NFAB304001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Process Date */
    private String procDt = "";

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** Interface Id */
    private String intfcId;

    /** Total Commit Count */
    private int totalCommitCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    @Override
    protected void initRoutine() {
        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NFAM0052E, new String[] {"Global Company Code" });
        }

        // "Batch Process Date"
        this.procDt = ZYPDateUtil.getBatProcDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.procDt)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NZZM0012E, new String[] {"Batch Process Date" });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {INTERFACE_ID });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_INSERT_NUMBER) {
            this.commitNumber = MAX_INSERT_NUMBER;
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        // S21TransactionTableAccessor
        S21TransactionTableAccessor trxAccess = new S21TransactionTableAccessor();
        // Get the Latest transaction ID.
        BigDecimal latestTrx = trxAccess.searchIntegrationRecordLatest(this.intfcId);

        // Normal End if no integration data is found.
        if (latestTrx == null) {
            return;
        }

        // Transaction Id List
        BigDecimal[] trxIdList = trxAccess.getIntegrationRecord(this.intfcId);
        for (BigDecimal trxId : trxIdList) {
            if (trxId.equals(latestTrx)) {
                insertCfsInvIntfcData(setSearchCondition(trxId));
            }
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
        new NFAB304001().executeBatch(NFAB304001.class.getSimpleName());
    }

    private void insertCfsInvIntfcData(Map<String, Object> paramMap) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getInputData", paramMap, execParam);
            rs = stmt.executeQuery();

            // START 2016/10/20 [QC#15339, DEL]
//            // Delete all the records.
//            COA_HRCHTMsg delete = new COA_HRCHTMsg();
//            delete.glblCmpyCd.setValue(this.glblCmpyCd);
//            S21FastTBLAccessor.removeByPartialValue(delete, new String[] {"glblCmpyCd" });
            // END 2016/10/20 [QC#15339, DEL]

            int procCount = 0;
            int normalCount = 0;
            List<COA_HRCHTMsg> inTMsgList = new ArrayList<COA_HRCHTMsg>();

            while (rs.next()) {
                // START 2016/10/20 [QC#15339, ADD]
                if (procCount == 0) {
                    // Delete all the records.
                    COA_HRCHTMsg delete = new COA_HRCHTMsg();
                    delete.glblCmpyCd.setValue(this.glblCmpyCd);
                    S21FastTBLAccessor.removeByPartialValue(delete, new String[] {"glblCmpyCd" });
                }
                // END 2016/10/20 [QC#15339, ADD]

                procCount++;
                // insert
                COA_HRCHTMsg inTMsg = setCoaHrchTMsg(rs);
                inTMsgList.add(inTMsg);
                if (this.commitNumber == inTMsgList.size()) {
                    insertInterfaceData(inTMsgList);
                    normalCount += inTMsgList.size();
                    inTMsgList.clear();
                }
            }
            if (inTMsgList.size() != 0) {
                insertInterfaceData(inTMsgList);
                normalCount += inTMsgList.size();
            }
            if (procCount != 0) {
                this.totalCommitCount += normalCount;
            }

            // delete
            NFAI0110_01TMsg delete2 = new NFAI0110_01TMsg();
            delete2.interfaceId.setValue(this.intfcId);
            S21ApiTBLAccessor.removeByPartialKey(delete2);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void insertInterfaceData(List<COA_HRCHTMsg> inTMsgList) {
        int insCnt = S21FastTBLAccessor.insert(inTMsgList.toArray(new COA_HRCHTMsg[inTMsgList.size()]));
        if (insCnt != inTMsgList.size()) {
            this.termCd = TERM_CD.ABNORMAL_END;
            // START 2016/10/06 [QC#14932, MOD]
            throw new S21AbendException(ZZBM0074E);
            // END 2016/10/06 [QC#14932, MOD]
        }
    }

    private Map<String, Object> setSearchCondition(BigDecimal trxId) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("transactionId", trxId);
        inParam.put("interfaceId", this.intfcId);
        return inParam;
    }

    private COA_HRCHTMsg setCoaHrchTMsg(ResultSet rs) throws SQLException {
        COA_HRCHTMsg inTMsg = new COA_HRCHTMsg();

        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.coaSegNm, rs.getString("COA_SEG_NM"));
        setValue(inTMsg.prntCoaValCd, rs.getString("PRNT_FLEX_VAL_CD"));
        setValue(inTMsg.childCoaValCd, rs.getString("CHILD_FLEX_VAL_CD"));
        setValue(inTMsg.coaRngAttrbCd, rs.getString("COA_RNG_ATTRB_CD"));
        setValue(inTMsg.coaStartActvDt, rs.getString("COA_START_ACTV_DT"));
        setValue(inTMsg.coaEndActvDt, rs.getString("COA_END_ACTV_DT"));
        return inTMsg;
    }

}
