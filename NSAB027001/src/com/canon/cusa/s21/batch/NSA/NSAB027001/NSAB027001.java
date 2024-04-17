/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB027001;

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

import business.db.CFS_INV_INTFCTMsg;
import business.db.INTFC_TS_MGTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * CFS Invoice Import
 * 
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 2016/05/24   Hitachi         T.Tsuchida      Created         N/A
 * 2016/10/18   Hitachi         E.Kameishi      Upadate         QC#14464
 * 2017/01/16   Fujitsu         S.Fujita        Upadate         QC#16956
 * </pre>
 */
public class NSAB027001 extends S21BatchMain {

    /** [@] is mandatory. */
    private static final String ZZZM9025E = "ZZZM9025E";

    // START 2016/01/16 S.Fujita [QC#16956,DEL]
//    /** Failed to insert @ table. [@] */
//    private static final String NSAM0012E = "NSAM0012E";
//
//    /** It failed to delete @ Table.[@] */
//    private static final String NSAM0475E = "NSAM0475E";
    // END   2016/01/16 S.Fujita [QC#16956,ADD]

    // START 2016/01/16 S.Fujita [QC#16956,ADD]
    /** Failed to insert @. */
    private static final String NSAM0032E = "NSAM0032E";
    // END   2016/01/16 S.Fujita [QC#16956,ADD]

    // START 2016/10/18 E.Kameishi [QC#14464, ADD]
    /** [@] cannot be obtained. */
    private static final String NSAM0179E = "NSAM0179E";
    /** Failed to update "@" */
    private static final String NSAM0031E = "NSAM0031E";
    // END 2016/10/18 E.Kameishi [QC#14464, ADD]

    /** message Item: GlobalCompanyCode */
    private static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: InterfaceId */
    private static final String MSG_ITEM_INTERFACE_ID = "Interface ID";

    // START 2016/01/16 S.Fujita [QC#16956,ADD]
    /** message Item: Table Name */
    private static final String TABLE_NAME_VALUE = "CFS_INV_INTFC";
    // END   2016/01/16 S.Fujita [QC#16956,ADD]

    /** parameter: ONE */
    private static final String PARAM_ONE = "1";

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

    /** Error Count */
    private int errorCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21TransactionTableAccessor */
    private S21TransactionTableAccessor trxAccess;

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();

        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();

        if (!hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_INTERFACE_ID });
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
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        for (BigDecimal trxId : this.trxIdList) {
            insertCfsInvIntfcData("getInputData", setSearchCondition(trxId));

            // Insert Transaction data
            this.trxAccess.endIntegrationProcess(this.intfcId, trxId);
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount != 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCommitCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB027001().executeBatch(NSAB027001.class.getSimpleName());
    }

    private void insertCfsInvIntfcData(String sqlId, Map<String, Object> paramMap) {
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

            int procCount = 0;
            // START 2016/01/16 S.Fujita [QC#16956,DEL]
//            int normalCount = 0;
            // END   2016/01/16 S.Fujita [QC#16956,DEL]

            // START 2016/01/16 S.Fujita [QC#16956,ADD]
            List<CFS_INV_INTFCTMsg> insDataList = new ArrayList<CFS_INV_INTFCTMsg>(this.commitNumber);
            // END   2016/01/16 S.Fujita [QC#16956,ADD]

            while (rs.next()) {
                procCount++;
                // START 2016/01/16 S.Fujita [QC#16956,MOD]
//                normalCount += insertInterfaceData(setCreateValue(rs));
//
//                if (this.commitNumber == normalCount) {
//                    this.errorCount += procCount - normalCount;
//                    this.totalCommitCount += normalCount;
//                    normalCount = 0;
//                    procCount = 0;
//                    commit();
//                }

                insDataList = setCreateValue(rs, insDataList);

                if (insDataList.size() == this.commitNumber) {
                    this.executeBulkIns(insDataList);
                    insDataList.clear();
                }
                // END   2016/01/16 S.Fujita [QC#16956,MOD]
            }

            // START 2016/01/16 S.Fujita [QC#16956,ADD]
            // not reach the max commit number in the repeat
            if (insDataList.size() > 0) {
                this.executeBulkIns(insDataList);
                commit();
            }
            // END   2016/01/16 S.Fujita [QC#16956,ADD]

            if (procCount != 0) {
                // START 2016/01/16 S.Fujita [QC#16956,DEL]
//                this.errorCount += procCount - normalCount;
//                this.totalCommitCount += normalCount;
                // END   2016/01/16 S.Fujita [QC#16956,DEL]

                // START 2016/10/18 E.Kameishi [QC#14464, ADD]
                setBatchDate();
                // END 2016/10/18 E.Kameishi [QC#14464, ADD]
                commit();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    // START 2016/01/16 S.Fujita [QC#16956,DEL]
//    private int insertInterfaceData(CFS_INV_INTFCTMsg inMsg) {
//        S21FastTBLAccessor.insert(inMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
//            S21InfoLogOutput.println(NSAM0012E, new String[] {inMsg.getTableName(), "glblCmpyCd=" + this.glblCmpyCd + ", cfsLeasePblNum=" + inMsg.cfsLeasePblNum.getValue() });
//            return 0;
//        }
//
//        NSAI0270_01TMsg delMsg = new NSAI0270_01TMsg();
//        setValue(delMsg.interfaceId, this.intfcId);
//        setValue(delMsg.cfsLeasePblNum, inMsg.cfsLeasePblNum);
//        S21FastTBLAccessor.logicalRemoveByPartialValue(delMsg, new String[] {"interfaceId", "cfsLeasePblNum"});
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(delMsg.getReturnCode())) {
//            S21InfoLogOutput.println(NSAM0475E, new String[] {inMsg.getTableName(), "glblCmpyCd=" + this.glblCmpyCd + ", cfsLeasePblNum=" + inMsg.cfsLeasePblNum.getValue() });
//            return 0;
//        }
//        return 1;
//    }
    // END   2016/01/16 S.Fujita [QC#16956,DEL]

    private Map<String, Object> setSearchCondition(BigDecimal trxId) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("interfaceId", this.intfcId);
        inParam.put("transactionId", trxId);
        return inParam;
    }

    // START 2016/01/16 S.Fujita [QC#16956,MOD]
//    private CFS_INV_INTFCTMsg setCreateValue(ResultSet rs) throws SQLException {
    private List<CFS_INV_INTFCTMsg> setCreateValue(ResultSet rs, List<CFS_INV_INTFCTMsg> insDataList) throws SQLException {
    // END   2016/01/16 S.Fujita [QC#16956,MOD]
        CFS_INV_INTFCTMsg inTMsg = new CFS_INV_INTFCTMsg();

        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        BigDecimal cfsInvIntfcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CFS_INV_INTFC_SQ);
        setValue(inTMsg.cfsInvIntfcPk, cfsInvIntfcPk);
        setValue(inTMsg.invLineNum, PARAM_ONE);
        setValue(inTMsg.procDt, rs.getString("PROC_DT"));
        setValue(inTMsg.cfsLeasePblNum, rs.getString("CFS_LEASE_PBL_NUM"));
        setValue(inTMsg.csaInvNum, rs.getString("CSA_INV_NUM"));
        setValue(inTMsg.csaContrNum, rs.getString("CSA_CONTR_NUM"));
        setValue(inTMsg.csaContrModTxt, rs.getString("CSA_CONTR_MOD_TXT"));
        setValue(inTMsg.cfsSerNum, rs.getString("CFS_SER_NUM"));
        setValue(inTMsg.invTotDealNetAmt, rs.getBigDecimal("INV_AMT"));
        setValue(inTMsg.bllgPerFromDt, rs.getString("BLLG_PER_FROM_DT"));
        setValue(inTMsg.bllgPerThruDt, rs.getString("BLLG_PER_THRU_DT"));
        setValue(inTMsg.cfsBllgTpTxt, rs.getString("CFS_BLLG_TP_TXT"));
        setValue(inTMsg.cfsLeaseNum, rs.getString("CFS_LEASE_NUM"));
        setValue(inTMsg.endCustNm, rs.getString("END_CUST_NM"));
        setValue(inTMsg.dsAcctDlrCd, rs.getString("DS_ACCT_DLR_CD"));
        setValue(inTMsg.invDt, rs.getString("INV_DT"));
        setValue(inTMsg.bllgMtrLbNm, rs.getString("BLLG_MTR_LB_NM"));
        setValue(inTMsg.ftrFlgOrigTxt, rs.getString("CFS_FTR_FLG_ORIG_TXT"));
        setValue(inTMsg.cmFlgOrigTxt, rs.getString("CFS_CM_FLG_ORIG_TXT"));
        setValue(inTMsg.cfsFleetFlg, rs.getString("CFS_FLEET_FLG_ORIG_TXT"));
        setValue(inTMsg.cfsInvBatNum, rs.getString("CFS_INV_BAT_NUM"));
        setValue(inTMsg.cfsInvProcStsCd, CFS_INV_PROC_STS.NEW_RECORD);

        // START 2016/01/16 S.Fujita [QC#16956,MOD]
//        return inTMsg;
        insDataList.add(inTMsg);
        return insDataList;
        // END   2016/01/16 S.Fujita [QC#16956,MOD]
    }
    // START 2016/10/18 E.Kameishi [QC#14464, ADD]
    private void setBatchDate() {
        String salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            S21InfoLogOutput.println(NSAM0179E, new String[] {"Sales date" });
            throw new S21AbendException(NSAM0179E, new String[] {"Sales date" });
        }
        String batchDate = ZYPDateUtil.addDays(salesDate, -30);
        INTFC_TS_MGTTMsg inTMsg = new INTFC_TS_MGTTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.mngIntfcId, this.intfcId);
        S21FastTBLAccessor.findByKeyForUpdate(inTMsg);
        setValue(inTMsg.mngIntfcTs, ZYPCommonFunc.rightPad(batchDate, 17, "0"));
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            S21FastTBLAccessor.insert(inTMsg);
        } else {
            S21FastTBLAccessor.update(inTMsg);
        }
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {inTMsg.getTableName(), "glblCmpyCd=" + this.glblCmpyCd + ", intfcId" + this.intfcId + ", mngIntfcTs=" + inTMsg.mngIntfcTs });
            throw new S21AbendException(NSAM0031E, new String[] {inTMsg.getTableName(), "glblCmpyCd=" + this.glblCmpyCd + ", intfcId" + this.intfcId + ", mngIntfcTs=" + inTMsg.mngIntfcTs });
        }
    }
    // END 2016/10/18 E.Kameishi [QC#14464, ADD]

    // START 2016/01/16 S.Fujita [QC#16956,ADD]
    /**
     * execute BulkInsert to InterfaceTbl and commit
     * @param insDataList
     */
    private void executeBulkIns(List<CFS_INV_INTFCTMsg> insDataList) {

        // BulkInsert
        int insDataListSize = insDataList.size();
        int insCount = S21FastTBLAccessor.insert(insDataList.toArray(new CFS_INV_INTFCTMsg[insDataListSize]));

        if (insCount != insDataListSize) {
            this.errorCount = insDataListSize;
            rollback();
            throw new S21AbendException(NSAM0032E, new String[] {TABLE_NAME_VALUE});
        }

        this.totalCommitCount += insDataListSize;
    }
    // END   2016/01/16 S.Fujita [QC#16956,ADD]
}
