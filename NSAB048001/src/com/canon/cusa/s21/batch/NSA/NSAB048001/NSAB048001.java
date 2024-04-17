/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB048001;


import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.BLLG_MSTR_REVN_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.CONST_ROSS_INTFC_MTR_DEL_VAL;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.DEFAULT_PURGE_DAYS;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.DS_BIZ_PROC_LOG;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.MACH_MSTR_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.MRC_CRAT_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.MRC_MOD_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.MSG_ITEM_SALES_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.MTR_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.MTR_DT;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.MTR_TEST_CNT;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.NORMAL;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.NSAM0031E;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.NSAM0032E;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.NSAM0033E;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.PHYS_MTR_NM;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.PHYS_MTR_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.PHYS_MTR_READ_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.ROSS_INTFC_MTR_READ_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.SVC_DLR_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.TIME_STAMP_FORMAT;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.DATE_FORMAT;
import static com.canon.cusa.s21.batch.NSA.NSAB048001.constant.NSAB048001Constant.ZZM9000E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.ROSS_INTFC_MTR_READTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GLBL_CMPY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Batch Program Class for Populate CUSA Retail Meter Reads
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/10/2016   CITS            T.Wada          Create          
 * 10/13/2016   Hitachi         N.Arai          Update          QC#12916
 *</pre>
 */
public class NSAB048001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;
    /** Delete Count */
    private int deleteCount = 0;

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** commit count */
    private int commitCount = 0;

    /** total correct count */
    private int totalCorrectCount = 0;

    /** Counter of Records: Error */
    private int totalErrorCount = 0;

    /** fetchSize */
    private String fetchSize = "1000";

    /** delVal*/
    private BigDecimal saveDays = new BigDecimal("100");

// START 10/13/2016 N.Arai [QC#12916, MOD]
    /** Process Time Stamp */
    private String procTs = null;

    /** DS_BIZ_LAST_PROC_TS */
    private String dsBizLastProcTs = null;
// END 10/13/2016 N.Arai [QC#12916, MOD]

    /**
     * Called for batch shell.
     * @param args execution parameter
     */
    public static void main(String[] args) {
        new NSAB048001().executeBatch(NSAB048001.class.getSimpleName());
    }

    /**
     * Initialization Routine.
     */
    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales date
        salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_SALES_DATE });
        }

        // Get FetchSize
        fetchSize = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(fetchSize)) {
            fetchSize = DEFAULT_FETCH_SIZE;
        }
        // Get Commit Count
        commitCount = getCommitCount();

        // Get Save Days
//        saveDays = ZYPCodeDataUtil.getVarCharConstValue(CONST_ROSS_INTFC_MTR_DEL_VAL, this.glblCmpyCd);
        saveDays = ZYPCodeDataUtil.getNumConstValue(CONST_ROSS_INTFC_MTR_DEL_VAL, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(saveDays)) {
            saveDays = DEFAULT_PURGE_DAYS;
        }

// START 10/13/2016 N.Arai [QC#12916, MOD]
        // Get Process Time Stamp
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
// END 10/13/2016 N.Arai [QC#12916, MOD]

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    /**
     * Main Routine.
     */
    @Override
    protected void mainRoutine() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        execParam.setFetchSize(Integer.parseInt(fetchSize));

// START 10/13/2016 N.Arai [QC#12916, MOD]
        DS_BIZ_PROC_LOGTMsgArray tMsgList = setDsBizProcLogData();
        if (tMsgList.getValidCount() > 0) {
            this.dsBizLastProcTs = tMsgList.no(0).dsBizLastProcTs.getValue();
        }
//END 10/13/2016 N.Arai [QC#12916, MOD]

        // IF Data Purge
        purgeIfData(execParam);
        // IF Data Create
        createIfData(execParam);

// START 10/13/2016 N.Arai [QC#12916, MOD]
        writeDsBizProcLog(tMsgList);
//END 10/13/2016 N.Arai [QC#12916, MOD]
    }

    /**
     * Termination Routine.
     */
    @Override
    protected void termRoutine() {
        // Setting Process Termination Code
        setTermState(this.termCd, this.totalCorrectCount - this.totalErrorCount, this.totalErrorCount, this.totalCorrectCount);
    }

    /**
     * createMtrRead
     * @param execParam
     */
    private void createIfData(S21SsmExecutionParameter execParam) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("GLBL_CMPY_CD", GLBL_CMPY.CANON_USA_INC);
            queryParam.put("MTR_DT", salesDate);
// START 10/13/2016 N.Arai [QC#12916, MOD]
//            queryParam.put("GLBL_CMPY_CD_CSA", this.glblCmpyCd);
            if (ZYPCommonFunc.hasValue(this.dsBizLastProcTs)) {
                queryParam.put("DS_BIZ_LAST_PROC_TS", dsBizLastProcTs);
            }
// END 10/13/2016 N.Arai [QC#12916, MOD]

            stmt = ssmLLClient.createPreparedStatement("getMeterRead", queryParam, execParam);
            rs = stmt.executeQuery();

            int insertCount = 0;

            while (rs.next()) {

                totalCorrectCount++;

                // Get WMS Upload [POD] Header
                Map<String, Object> mtrReadInfo = new HashMap<String, Object>();

                mtrReadInfo.put(GLBL_CMPY_CD, rs.getString(GLBL_CMPY_CD));
                mtrReadInfo.put(SER_NUM, rs.getString(SER_NUM));
                mtrReadInfo.put(MDSE_CD, rs.getString(MDSE_CD));
                mtrReadInfo.put(BLLG_MSTR_REVN_CD, rs.getString(BLLG_MSTR_REVN_CD));
                mtrReadInfo.put(SVC_DLR_CD, rs.getString(SVC_DLR_CD));
                mtrReadInfo.put(MACH_MSTR_PK, rs.getBigDecimal(MACH_MSTR_PK));
                mtrReadInfo.put(PHYS_MTR_NUM, rs.getString(PHYS_MTR_NUM));
                mtrReadInfo.put(PHYS_MTR_NM, rs.getString(PHYS_MTR_NM));
                mtrReadInfo.put(PHYS_MTR_READ_PK, rs.getBigDecimal(PHYS_MTR_READ_PK));
                mtrReadInfo.put(MTR_DT, rs.getString(MTR_DT));
                mtrReadInfo.put(MTR_CNT, rs.getBigDecimal(MTR_CNT));
                mtrReadInfo.put(MTR_TEST_CNT, rs.getBigDecimal(MTR_TEST_CNT));
                mtrReadInfo.put(MRC_CRAT_DT, rs.getString(MRC_CRAT_DT));
                mtrReadInfo.put(MRC_MOD_DT, rs.getString(MRC_MOD_DT));

                // createPodData
                insertIfData(mtrReadInfo);

                insertCount++;

                if (insertCount >= commitCount) {
                    commit();
                    this.totalCommitCount += insertCount;
                    insertCount = 0;
                }
            }

            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * purgeIfData
     * @param execParam
     */
    private void purgeIfData(S21SsmExecutionParameter execParam) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("GLBL_CMPY_CD", glblCmpyCd);
            String pDate = ZYPDateUtil.addDays(salesDate, saveDays.intValue() * -1);
            queryParam.put("RGTN_MTR_DT", pDate);
            stmt = ssmLLClient.createPreparedStatement("getPurgeData", queryParam, execParam);
            rs = stmt.executeQuery();
            BigDecimal purgePk = null;
            while (rs.next()) {
                purgePk = rs.getBigDecimal(ROSS_INTFC_MTR_READ_PK);
                ROSS_INTFC_MTR_READTMsg tMsg = new ROSS_INTFC_MTR_READTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrReadPk, purgePk);
                EZDTBLAccessor.remove(tMsg);
                if (tMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
                    throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
                }
                this.deleteCount++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * createPodData
     * @param mtrReadInfoList
     * @param ediLnCtrlCdSeq
     */
    private void insertIfData(Map<String, Object> mtrReadInfo) {

        ROSS_INTFC_MTR_READTMsg tMsg = new ROSS_INTFC_MTR_READTMsg();

        // GLBL_CMPY_CD
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);

        // ROSS_INTFC_MTR_READ_PK
        ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrReadPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ROSS_INTFC_MTR_READ_SQ));
        // ROSS_INTFC_MTR_PROC_CD
        ZYPEZDItemValueSetter.setValue(tMsg.rossIntfcMtrProcCd, NORMAL);

        // MTR_READ_SRC_TP_CD
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadSrcTpCd, DS_MTR_READ_TP.ROSS);
        // DS_MTR_READ_TP_CD
        ZYPEZDItemValueSetter.setValue(tMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ROSS);
        // SER_NUM
        ZYPEZDItemValueSetter.setValue(tMsg.serNum, (String) mtrReadInfo.get(SER_NUM));
        // MDSE_CD
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, (String) mtrReadInfo.get(MDSE_CD));
        // BLLG_MSTR_REVN_CD
        ZYPEZDItemValueSetter.setValue(tMsg.bllgRevnCd, (String) mtrReadInfo.get(BLLG_MSTR_REVN_CD));
        // SVC_DLR_CD
        ZYPEZDItemValueSetter.setValue(tMsg.svcDlrCd, (String) mtrReadInfo.get(SVC_DLR_CD));
        // MACH_MSTR_PK
        ZYPEZDItemValueSetter.setValue(tMsg.machMstrPk, (BigDecimal) mtrReadInfo.get(MACH_MSTR_PK));
        // PHYS_MTR_NUM
        ZYPEZDItemValueSetter.setValue(tMsg.physMtrNum, (String) mtrReadInfo.get(PHYS_MTR_NUM));
        // PHYS_MTR_NM
        ZYPEZDItemValueSetter.setValue(tMsg.physMtrNm, (String) mtrReadInfo.get(PHYS_MTR_NM));
        // PHYS_MTR_READ_PK
        ZYPEZDItemValueSetter.setValue(tMsg.physMtrReadPk, (BigDecimal) mtrReadInfo.get(PHYS_MTR_READ_PK));
        // MTR_READ_DT
        ZYPEZDItemValueSetter.setValue(tMsg.mtrReadDt, (String) mtrReadInfo.get(MTR_DT));
        // RGTN_MTR_DT
        ZYPEZDItemValueSetter.setValue(tMsg.rgtnMtrDt, this.salesDate);
        // READ_MTR_CNT
        ZYPEZDItemValueSetter.setValue(tMsg.readMtrCnt, (BigDecimal) mtrReadInfo.get(MTR_CNT));
        // TEST_MTR_CNT
        ZYPEZDItemValueSetter.setValue(tMsg.testMtrCnt, (BigDecimal) mtrReadInfo.get(MTR_TEST_CNT));
        // EST_FLG
        ZYPEZDItemValueSetter.setValue(tMsg.estFlg, ZYPConstant.FLG_OFF_N);
        // MRC_CRAT_DT
        ZYPEZDItemValueSetter.setValue(tMsg.mrcCratDt, (String) mtrReadInfo.get(MRC_CRAT_DT));
        // MRC_MOD_DT
        ZYPEZDItemValueSetter.setValue(tMsg.mrcModDt, (String) mtrReadInfo.get(MRC_MOD_DT));

        // //////////////////////////////////////////////
        // insert ROSS_INTFC_MTR_READ
        // //////////////////////////////////////////////
        EZDTBLAccessor.insert(tMsg);
        if (tMsg.getReturnCode() != EZDTBLAccessor.RTNCD_NORMAL) {
            throw new S21AbendException(NSAM0033E, new String[] {tMsg.getTableName() });
        }

    }

// START 10/13/2016 N.Arai [QC#12916, MOD]
   /**
     * setDsBizProcLogData
     * @param DS_BIZ_PROC_LOGTMsgArray 
     */
    private DS_BIZ_PROC_LOGTMsgArray setDsBizProcLogData() {

        DS_BIZ_PROC_LOGTMsg tMsg = new DS_BIZ_PROC_LOGTMsg();

        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        tMsg.setConditionValue("procPgmId01", PROGRAM_ID);

        // get DS_BIZ_PROC_LOG
        return (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    /**
     * writeDsBizProcLog.
     * @param tMsgList DS_BIZ_PROC_LOGTMsgArray
     */
    private void writeDsBizProcLog(DS_BIZ_PROC_LOGTMsgArray tMsgList) {

        if (ZYPCommonFunc.hasValue(this.dsBizLastProcTs)) {
            DS_BIZ_PROC_LOGTMsg inParam = tMsgList.no(0);

            inParam = (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(inParam);

            if (inParam == null || !EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
                throw new S21AbendException(NSAM0031E, new String[] {DS_BIZ_PROC_LOG});
            } else {
                // Update DS_BIZ_PROC_LOG
                ZYPEZDItemValueSetter.setValue(inParam.dsBizProcDt, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
                ZYPEZDItemValueSetter.setValue(inParam.dsBizLastProcTs, this.procTs);
                ZYPEZDItemValueSetter.setValue(inParam.dsBizLastUpdTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));

                EZDTBLAccessor.update(inParam);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inParam.getReturnCode())) {
                    throw new S21AbendException(NSAM0031E, new String[] {DS_BIZ_PROC_LOG});
                }
            }
        } else {
            // Insert DS_BIZ_PROC_LOG
            DS_BIZ_PROC_LOGTMsg tMsg = new DS_BIZ_PROC_LOGTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
            ZYPEZDItemValueSetter.setValue(tMsg.procPgmId, PROGRAM_ID);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcDt, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastProcTs, this.procTs);
            ZYPEZDItemValueSetter.setValue(tMsg.dsBizLastUpdTs, ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT));

            EZDTBLAccessor.insert(tMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                throw new S21AbendException(NSAM0032E, new String[] {DS_BIZ_PROC_LOG});
            }
        }
    }
// END 10/13/2016 N.Arai [QC#12916, MOD]

}
