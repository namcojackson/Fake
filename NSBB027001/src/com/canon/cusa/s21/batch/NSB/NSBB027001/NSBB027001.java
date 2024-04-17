/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB027001;

import static com.canon.cusa.s21.batch.NSB.NSBB027001.constant.NSBB027001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.FSR_USGTMsg;
import business.db.FSR_USGTMsgArray;
import business.db.NSBI0270_01TMsg;
import business.db.NSBI0270_02TMsg;
import business.db.NSBI0270_03TMsg;
import business.db.NSBI0270_04TMsg;
import business.db.TECH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Create Cross Service Close
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/02/27   Hitachi         T.Mizuki        Create          N/A
 * 2018/05/25   Hitachi         K.Kitachi       Update          QC#26344
 * 2018/06/12   CITS            M.Naito         Update          QC#26558
 * 2018/06/13   CITS            M.Naito         Update          QC#26561
 * 2018/12/26   Hitachi         S.Kitamura      Update          QC#29743
 * 2019/04/26   Hitachi         A.Kohinata      Update          QC#31213
 * 2019/08/12   Hitachi         K.Kim           Update          QC#51271
 * </pre>
 */
public class NSBB027001 extends S21BatchMain {

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Transaction ID */
    private BigDecimal trxId;

    // START 2018/05/25 K.Kitachi [QC#26344, MOD]
    /** Unit ID 01 (NSBI0270_01) */
    private int unitId01;

    /** Unit ID 02 (NSBI0270_02) */
    private int unitId02;

    /** Unit ID 03 (NSBI0270_03) */
    private int unitId03;

    /** Unit ID 04 (NSBI0270_04) */
    private int unitId04;
    // END 2018/05/25 K.Kitachi [QC#26344, MOD]

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

    // START 2018/06/12 M.Naito [QC#26558, ADD]
    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;
    // END 2018/06/12 M.Naito [QC#26558, ADD]

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
        // START 2018/05/25 K.Kitachi [QC#26344, MOD]
        this.unitId01 = 1;
        this.unitId02 = 1;
        this.unitId03 = 1;
        this.unitId04 = 1;
        // END 2018/05/25 K.Kitachi [QC#26344, MOD]
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // START 2018/06/12 M.Naito [QC#26558, ADD]
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // END 2018/06/12 M.Naito [QC#26558, ADD]

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
        new NSBB027001().executeBatch(NSBB027001.class.getSimpleName());
    }

    /**
     * 
     */
    private void doProcess() {
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        PreparedStatement stmtMtr = null;
        ResultSet rsSetMtr = null;
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

            // Insert
            while (rsSet.next()) {

                insertInterfaceHdrData(setCreateHdrValue(rsSet));
                insertInterfaceTskData(setCreateTskValue(rsSet));

                // Search Meter Data
                stmtMtr = this.ssmLLClient.createPreparedStatement("getMtrInf", setSearchMtr(rsSet), execParam);
                rsSetMtr = stmtMtr.executeQuery();

                String mtrLbCd = null;
                BigDecimal testMtrIn = new BigDecimal(0);
                BigDecimal readMtrIn = new BigDecimal(0);
                boolean mtrSubtract = true;
                boolean mtrIn = false;
                NSBI0270_03TMsg mtrTMsg = new NSBI0270_03TMsg();
                while (rsSetMtr.next()) {
                    if (ZYPCommonFunc.hasValue(mtrLbCd)) {
                        // mod start 2019/04/26 QC#31213
                        //if (!mtrLbCd.equals(getCrsSvcMtrLbCd(rsSetMtr.getString("MTR_LB_CD")))) {
                        if (!mtrLbCd.equals(getCrsSvcMtrLbCd(rsSetMtr.getString("MTR_LB_CD"), rsSet.getBigDecimal("SVC_MACH_MSTR_PK")))) {
                        // mod end 2019/04/26 QC#31213
                            insertInterfaceMtrData(mtrTMsg);
                            // START 2018/05/25 K.Kitachi [QC#26344, ADD]
                            this.unitId03++;
                            // END 2018/05/25 K.Kitachi [QC#26344, ADD]
                            mtrTMsg.clear();
                            // mod start 2019/04/26 QC#31213
                            //mtrTMsg = setCreateMtrValue(rsSetMtr);
                            mtrTMsg = setCreateMtrValue(rsSetMtr, rsSet);
                            // mod end 2019/04/26 QC#31213
                            // START 2018/06/12 M.Naito [QC#26558, MOD]
                            // mod start 2019/04/26 QC#31213
                            //mtrLbCd = getCrsSvcMtrLbCd(rsSetMtr.getString("MTR_LB_CD"));
                            mtrLbCd = getCrsSvcMtrLbCd(rsSetMtr.getString("MTR_LB_CD"), rsSet.getBigDecimal("SVC_MACH_MSTR_PK"));
                            // mod end 2019/04/26 QC#31213
//                            mtrLbCd = rsSetMtr.getString("MTR_LB_CD");
                            // END 2018/06/12 M.Naito [QC#26558, MOD]
                            testMtrIn = BigDecimal.ZERO;
                            readMtrIn = BigDecimal.ZERO;
                            mtrSubtract = true;
                            mtrIn = false;
                            if (!DS_TEST_COPY_CLS.TEST_COPY_OUT.equals(rsSetMtr.getString("DS_TEST_COPY_CLS_CD"))) {
                                testMtrIn = rsSetMtr.getBigDecimal("TEST_MTR_CNT");
                                readMtrIn = rsSetMtr.getBigDecimal("READ_MTR_CNT");
                                mtrIn = true;
                            }
                        } else {
                            if (mtrIn && DS_TEST_COPY_CLS.TEST_COPY_OUT.equals(rsSetMtr.getString("DS_TEST_COPY_CLS_CD"))) {
                                if (rsSetMtr.getBigDecimal("READ_MTR_CNT").compareTo(readMtrIn) >= 0) {
                                    BigDecimal testMtr = rsSetMtr.getBigDecimal("TEST_MTR_CNT").subtract(testMtrIn);
                                    setValue(mtrTMsg.testMtrCnt, testMtr);
                                    BigDecimal readMtr = rsSetMtr.getBigDecimal("READ_MTR_CNT").subtract(readMtrIn);
                                    setValue(mtrTMsg.readMtrCnt, readMtr);
                                }
                                mtrSubtract = false;
                            } else if (mtrSubtract) {
                                testMtrIn = rsSetMtr.getBigDecimal("TEST_MTR_CNT");
                                readMtrIn = rsSetMtr.getBigDecimal("READ_MTR_CNT");
                            }
                        }
                    } else {
                        // mod start 2019/04/26 QC#31213
                        //mtrTMsg = setCreateMtrValue(rsSetMtr);
                        mtrTMsg = setCreateMtrValue(rsSetMtr, rsSet);
                        // mod end 2019/04/26 QC#31213
                        // START 2018/06/12 M.Naito [QC#26558, MOD]
                        // mod start 2019/04/26 QC#31213
                        //mtrLbCd = getCrsSvcMtrLbCd(rsSetMtr.getString("MTR_LB_CD"));
                        mtrLbCd = getCrsSvcMtrLbCd(rsSetMtr.getString("MTR_LB_CD"), rsSet.getBigDecimal("SVC_MACH_MSTR_PK"));
                        // mod end 2019/04/26 QC#31213
//                        mtrLbCd = rsSetMtr.getString("MTR_LB_CD");
                        // END 2018/06/12 M.Naito [QC#26558, MOD]
                        if (!DS_TEST_COPY_CLS.TEST_COPY_OUT.equals(rsSetMtr.getString("DS_TEST_COPY_CLS_CD"))) {
                            testMtrIn = rsSetMtr.getBigDecimal("TEST_MTR_CNT");
                            readMtrIn = rsSetMtr.getBigDecimal("READ_MTR_CNT");
                            mtrIn = true;
                        }
                    }
                }
                if (ZYPCommonFunc.hasValue(mtrLbCd)) {
                    insertInterfaceMtrData(mtrTMsg);
                    // START 2018/05/25 K.Kitachi [QC#26344, ADD]
                    this.unitId03++;
                    // END 2018/05/25 K.Kitachi [QC#26344, ADD]
                }
                FSR_USGTMsgArray fsrUsgArray = getFsrUsg(rsSet.getString("FSR_NUM"));
                for (int i = 0; i < fsrUsgArray.length(); i++) {
                    insertInterfacePrtData(setCreatePrtValue((FSR_USGTMsg) fsrUsgArray.get(i)));
                    // START 2018/05/25 K.Kitachi [QC#26344, ADD]
                    this.unitId04++;
                    // END 2018/05/25 K.Kitachi [QC#26344, ADD]
                }
                // START 2018/06/13 M.Naito [QC#26561, DEL]
//                createFsrEvent(rsSet);
                // END 2018/06/13 M.Naito [QC#26561, DEL]
                commit();
                this.totalCommitCount++;
                // START 2018/05/25 K.Kitachi [QC#26344, MOD]
                this.unitId01++;
                this.unitId02++;
                // END 2018/05/25 K.Kitachi [QC#26344, MOD]
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

    private void insertInterfaceHdrData(NSBI0270_01TMsg inTMsg) {
        S21FastTBLAccessor.insert(inTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            // START 2018/05/25 K.Kitachi [QC#26344, MOD]
            throw new S21AbendException(NSBM0164E, new String[] {inTMsg.getTableName() });
            // END 2018/05/25 K.Kitachi [QC#26344, MOD]
        }
        return;
    }

    private void insertInterfaceTskData(NSBI0270_02TMsg inTMsg) {
        S21FastTBLAccessor.insert(inTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            // START 2018/05/25 K.Kitachi [QC#26344, MOD]
            throw new S21AbendException(NSBM0164E, new String[] {inTMsg.getTableName() });
            // END 2018/05/25 K.Kitachi [QC#26344, MOD]
        }
        return;
    }

    private void insertInterfaceMtrData(NSBI0270_03TMsg inTMsg) {
        S21FastTBLAccessor.insert(inTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            // START 2018/05/25 K.Kitachi [QC#26344, MOD]
            throw new S21AbendException(NSBM0164E, new String[] {inTMsg.getTableName() });
            // END 2018/05/25 K.Kitachi [QC#26344, MOD]
        }
        return;
    }

    private void insertInterfacePrtData(NSBI0270_04TMsg inTMsg) {
        S21FastTBLAccessor.insert(inTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            // START 2018/05/25 K.Kitachi [QC#26344, MOD]
            throw new S21AbendException(NSBM0164E, new String[] {inTMsg.getTableName() });
            // END 2018/05/25 K.Kitachi [QC#26344, MOD]
        }
        return;
    }

    // START 2018/06/13 M.Naito [QC#26561, DEL]
//    private void createFsrEvent(ResultSet rs) throws SQLException {
//
//        String svcTaskStsCd = rs.getString("SVC_TASK_STS_CD");
//
//        BigDecimal fsrEventPk = ZYPOracleSeqAccessor.getNumberBigDecimal("FSR_EVENT_SQ");
//        FSR_EVENTTMsg tMsg = new FSR_EVENTTMsg();
//        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(tMsg.fsrEventPk, fsrEventPk);
//        if (SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd)) {
//            setValue(tMsg.svcDisptEventCd, SVC_DISPT_EVENT.CANCEL);
//        } else if (SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd) || SVC_TASK_STS.CLOSED.equals(svcTaskStsCd)) {
//            setValue(tMsg.svcDisptEventCd, SVC_DISPT_EVENT.CLOSE);
//        }
//        setValue(tMsg.svcTaskNum, setValStr(rs.getString("SVC_TASK_NUM")));
//        setValue(tMsg.fsrNum, setValStr(rs.getString("FSR_NUM")));
//        setValue(tMsg.fsrVisitNum, setValStr(rs.getString("FSR_VISIT_NUM")));
//        setValue(tMsg.fsrEventExeUsrId, PROGRAM_ID);
//        setValue(tMsg.fsrEventExeTs, this.currentSystemTs);
//        setValue(tMsg.mblIntfcProcCd, MBL_INTFC_PROC.NO_NEED);
//        setValue(tMsg.mblIntfcFlg, ZYPConstant.FLG_OFF_N);
//
//        S21FastTBLAccessor.insert(tMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//            throw new S21AbendException(NSBM0164E, new String[] {TABLE_FSR_EVENT });
//        }
//    }
    // END 2018/06/13 M.Naito [QC#26561, DEL]

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
        // END 2019/08/12 [QC#51271,MOD]
        inParam.put("svcTaskStsCdList", svcTaskStsList);
        inParam.put("svcCallSrcTp", SVC_CALL_SRC_TP.CROSS_SERVICE);
        // START 2019/08/26 [QC#52930,ADD]
        String[] svcTaskStsList2 = new String[3];
        svcTaskStsList2[0] = SVC_TASK_STS.COMPLETED;
        svcTaskStsList2[1] = SVC_TASK_STS.CLOSED;
        svcTaskStsList2[2] = SVC_TASK_STS.CANCELLED;
        inParam.put("svcTaskStsCdList2", svcTaskStsList2);
        // END 2019/08/26 [QC#52930,ADD]
        return inParam;
    }

    private Map<String, Object> setSearchMtr(ResultSet rs) throws SQLException {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("svcTaskNum", rs.getString("SVC_TASK_NUM"));
        // START 2018/12/26 S.Kitamura [QC#29743,ADD]
        inParam.put("svcMachMstrPk", rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        // END 2018/12/26 S.Kitamura [QC#29743,ADD]
        return inParam;
    }

    private NSBI0270_01TMsg setCreateHdrValue(ResultSet rs) throws SQLException {
        NSBI0270_01TMsg inParam = new NSBI0270_01TMsg();

        setValue(inParam.interfaceId, this.intfcId);
        setValue(inParam.transactionId, this.trxId);
        setValue(inParam.segmentId, BigDecimal.ONE);
        // START 2018/05/25 K.Kitachi [QC#26344, MOD]
        setValue(inParam.unitId, BigDecimal.valueOf(this.unitId01));
        // END 2018/05/25 K.Kitachi [QC#26344, MOD]
        setValue(inParam.seqNumber, BigDecimal.ONE);
        setValue(inParam.crsSvcHdrId, HDR);
        // START 2018/06/12 M.Naito [QC#26558, MOD]
//        setValue(inParam.fsrNum, rs.getString("FSR_NUM"));
//        setValue(inParam.svcTaskNum, rs.getString("SVC_TASK_NUM"));
        setValue(inParam.fsrNum, editSubst(rs.getString("CRS_SVC_SR_NUM"), 0, NUM_10));
        setValue(inParam.svcTaskNum, editSubst(rs.getString("CRS_SVC_TASK_NUM"), 0, NUM_10));
        // END 2018/06/12 M.Naito [QC#26558, MOD]
        setValue(inParam.mdlNm, editSubst(rs.getString("MDL_NM"), 0, NUM_10));
        setValue(inParam.serNum, editSubst(rs.getString("SER_NUM"), 0, NUM_10));
        setValue(inParam.crsSvcCustNm, editSubst(rs.getString("CRS_SVC_CUST_NM"), 0, NUM_20));

        return inParam;
    }

    private NSBI0270_02TMsg setCreateTskValue(ResultSet rs) throws SQLException {
        NSBI0270_02TMsg inParam = new NSBI0270_02TMsg();

        setValue(inParam.interfaceId, this.intfcId);
        setValue(inParam.transactionId, this.trxId);
        setValue(inParam.segmentId, BigDecimal.ONE);
        // START 2018/05/25 K.Kitachi [QC#26344, MOD]
        setValue(inParam.unitId, BigDecimal.valueOf(this.unitId02));
        // END 2018/05/25 K.Kitachi [QC#26344, MOD]
        setValue(inParam.seqNumber, BigDecimal.ONE);
        setValue(inParam.crsSvcHdrId, TSK);
        setValue(inParam.crsSvcSrNum, editSubst(rs.getString("CRS_SVC_SR_NUM"), 0, NUM_10));
        setValue(inParam.crsSvcTaskNum, editSubst(rs.getString("CRS_SVC_TASK_NUM"), 0, NUM_10));
        setValue(inParam.fsrNum, rs.getString("FSR_NUM"));
        setValue(inParam.techCd, editSubst(rs.getString("TECH_CD"), 0, NUM_9));
        setValue(inParam.techNm, editSubst(getTechNm(rs.getString("TECH_CD")), 0, NUM_40));
        setValue(inParam.fsrVisitArvDt_01, rs.getString("FSR_VISIT_ARV_DT"));
        setValue(inParam.svcTaskRcvDt, rs.getString("SVC_TASK_RCV_DT"));
        setValue(inParam.svcTaskRcvTm, editSubst(rs.getString("SVC_TASK_RCV_TM"), 0, NUM_4));
        setValue(inParam.svcTaskRejDt, rs.getString("SVC_TASK_REJ_DT"));
        setValue(inParam.svcTaskRejTm, editSubst(rs.getString("SVC_TASK_REJ_TM"), 0, NUM_4));
        setValue(inParam.svcTrvlTmNum, editMax(rs.getBigDecimal("SVC_TRVL_TM_NUM"), MAX_999));
        setValue(inParam.fsrVisitArvDt_02, rs.getString("FSR_VISIT_ARV_DT"));
        // START 2018/06/12 M.Naito [QC#26558, MOD]
        setValue(inParam.custAvalFromHourMn, editSubst(rs.getString("CUST_AVAL_FROM_HOUR_MN"), 0, NUM_4));
        setValue(inParam.fsrVisitCpltDt, rs.getString("FSR_VISIT_CPLT_DT"));
        setValue(inParam.custAvalToHourMn, editSubst(rs.getString("CUST_AVAL_TO_HOUR_MN"), 0, NUM_4));
        // END 2018/06/12 M.Naito [QC#26558, MOD]

        return inParam;
    }

    // mod start 2019/04/26 QC#31213
    //private NSBI0270_03TMsg setCreateMtrValue(ResultSet rs) throws SQLException {
    private NSBI0270_03TMsg setCreateMtrValue(ResultSet rs, ResultSet rs2) throws SQLException {
    // mod end 2019/04/26 QC#31213
        NSBI0270_03TMsg inParam = new NSBI0270_03TMsg();

        setValue(inParam.interfaceId, this.intfcId);
        setValue(inParam.transactionId, this.trxId);
        setValue(inParam.segmentId, BigDecimal.ONE);
        // START 2018/05/25 K.Kitachi [QC#26344, MOD]
        setValue(inParam.unitId, BigDecimal.valueOf(this.unitId03));
        // END 2018/05/25 K.Kitachi [QC#26344, MOD]
        setValue(inParam.seqNumber, BigDecimal.ONE);
        setValue(inParam.crsSvcHdrId, MTR);
        // START 2018/06/12 M.Naito [QC#26558, MOD]
        // mod start 2019/04/26 QC#31213
        //String crsSvcMtrLbCd = getCrsSvcMtrLbCd(rs.getString("MTR_LB_CD"));
        String crsSvcMtrLbCd = getCrsSvcMtrLbCd(rs.getString("MTR_LB_CD"), rs2.getBigDecimal("SVC_MACH_MSTR_PK"));
        // mod end 2019/04/26 QC#31213
        setValue(inParam.mtrLbCd, crsSvcMtrLbCd);
//        setValue(inParam.mtrLbCd, editSubst(rs.getString("MTR_LB_CD"), 1, 2));
        // END 2018/06/12 M.Naito [QC#26558, MOD]
        setValue(inParam.testMtrCnt, BigDecimal.ZERO);
        setValue(inParam.svcTaskNum, rs.getString("SVC_TASK_NUM"));
        setValue(inParam.readMtrCnt, BigDecimal.ZERO);

        return inParam;
    }

    private NSBI0270_04TMsg setCreatePrtValue(FSR_USGTMsg tMsg) throws SQLException {
        NSBI0270_04TMsg inParam = new NSBI0270_04TMsg();

        setValue(inParam.interfaceId, this.intfcId);
        setValue(inParam.transactionId, this.trxId);
        setValue(inParam.segmentId, BigDecimal.ONE);
        // START 2018/05/25 K.Kitachi [QC#26344, MOD]
        setValue(inParam.unitId, BigDecimal.valueOf(this.unitId04));
        // END 2018/05/25 K.Kitachi [QC#26344, MOD]
        setValue(inParam.seqNumber, BigDecimal.ONE);
        setValue(inParam.crsSvcHdrId, PRT);
        setValue(inParam.mdseCd, tMsg.mdseCd.getValue());
        setValue(inParam.svcTaskNum, tMsg.svcTaskNum.getValue());
        setValue(inParam.svcPrtQty, editMax(tMsg.svcPrtQty.getValue(), MAX_99));

        return inParam;
    }

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

    private String getTechNm(String techTocCd) {

        if (ZYPCommonFunc.hasValue(techTocCd)) {
            TECH_MSTRTMsg inMsg = new TECH_MSTRTMsg();
            setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(inMsg.techTocCd, editSubst(techTocCd, 0, NUM_8));
            inMsg = (TECH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
            if (inMsg == null) {
                return null;
            } else {
                return inMsg.techNm.getValue();
            }
        }
        return null;
    }

    private FSR_USGTMsgArray getFsrUsg(String fsrNum) {

        FSR_USGTMsg inMsg = new FSR_USGTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("fsrNum01", fsrNum);

        return (FSR_USGTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
    }

    private String setValStr(String str) {
        if (!ZYPCommonFunc.hasValue(str)) {
            return null;
        }
        return str;
    }

    private String editSubst(String str, int strStart, int strEnd) {

        if (ZYPCommonFunc.hasValue(str)) {
            if (str.length() <= strEnd) {
                return str;
            } else {
                return str.substring(strStart, strEnd);
            }
        }
        return str;
    }

    private BigDecimal editMax(BigDecimal dcml, int max) {

        BigDecimal maxLen = BigDecimal.valueOf(max);
        if (ZYPCommonFunc.hasValue(dcml)) {
            if (dcml.compareTo(maxLen) > 0) {
                return maxLen;
            } else {
                return dcml;
            }
        }
        return null;
    }

    // START 2018/06/12 M.Naito [QC#26558, ADD]
    // mod start 2019/04/26 QC#31213
    private String getCrsSvcMtrLbCd(String mtrLbCd, BigDecimal svcMachMstrPk) {

        if (ZYPCommonFunc.hasValue(mtrLbCd)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("mtrLbCd", mtrLbCd);
            param.put("svcMachMstrPk", svcMachMstrPk);
            return (String) this.ssmBatchClient.queryObject("getCrsSvcMtrLbCd", param);
        }
        return null;
        
    }
    // mod end 2019/04/26 QC#31213
    // END 2018/06/12 M.Naito [QC#26558, ADD]
}
