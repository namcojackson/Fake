package com.canon.cusa.s21.batch.NSA.NSAB057001;

import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.CASE_2;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.CASE_2_DS_CONTR_CTRL_STS_NG_LIST;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.CASE_3;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.COL_ACTV_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.COL_EST_FLG;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.COL_MTR_CNTR_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.COL_SVC_PHYS_MTR_READ_PK;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.MTR_CNTR_ID_BW_HARD_READ;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.MTR_CNTR_ID_TOTAL_HARD_READ;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.NSAM0177E;
import static com.canon.cusa.s21.batch.NSA.NSAB057001.constant.NSAB057001Constant.NSAM0178E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_PHYS_MTR_READTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ROSS_BAT_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ROSS_SEND_TRGT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Update Meter Read Before Send To Sys85 Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2017/04/10   Hitachi         T.Tomita        Update          QC#18239
 *</pre>
 */
public class NSAB057001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** Total Commit Count */
    private int totalCommitCount;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Sales Date */
    private String salesDate;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAM0177E);
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSAM0178E);
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB057001().executeBatch(NSAB057001.class.getSimpleName());
    }

    /**
     * doProcess
     */
    private void doProcess() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            // get request data
            stmt = ssmLLClient.createPreparedStatement("getTargetDataList", getTargetDataListParams(), execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (processRequestData(rs)) {
                    this.normalCount++;

                } else {
                    this.errorCount++;
                }
                if (this.normalCount == this.commitNumber) {
                    commit();
                    this.totalCommitCount += normalCount;
                    normalCount = 0;
                }
            }
            if (this.normalCount > 0) {
                commit();
                this.totalCommitCount += this.normalCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    private boolean processRequestData(ResultSet rs) throws SQLException {
        SVC_PHYS_MTR_READTMsg tMsg = getSvcPhysMtrReadForUpdate(rs);
        if (tMsg != null && hasValue(tMsg.svcPhysMtrPk)) {
            EZDTBLAccessor.update(tMsg);
        } else {
            return false;
        }
        return true;
    }

    private Map<String, Object> getTargetDataListParams() {
        Map<String, Object> prms = new HashMap<String, Object>();
        // mod start 2017/04/10 CSA Defect#18239
        prms.put("glblCmpyCd", this.glblCmpyCd);
        prms.put("dsContrEdiCdCusa", DS_CONTR_EDI.CUSA);
        prms.put("slsDt", this.salesDate);
        prms.put("dsContrCtrlStsCdList", CASE_2_DS_CONTR_CTRL_STS_NG_LIST);
        prms.put("svcMachMstrStsCdTrmn", SVC_MACH_MSTR_STS.TERMINATED);
        prms.put("notProcessed", ROSS_BAT_PROC_STS.NOT_PROCESSED);
        prms.put("notApplicableByTransactionStatus", ROSS_SEND_TRGT.NOT_APPLICABLE_BY_TRANSACTION_STATUS);
        // mod end 2017/04/10 CSA Defect#18239
        return prms;
    }

    private String getBatProcStsCdAndSendTrgtCd(ResultSet rs) throws SQLException {
        // mod start 2017/04/10 CSA Defect#1823
        if (isCase2(rs)) {
            return CASE_2;
        }
        return CASE_3;
        // mod end 2017/04/10 CSA Defect#18239
    }

    private SVC_PHYS_MTR_READTMsg getSvcPhysMtrReadForUpdate(ResultSet rs) throws SQLException {
        SVC_PHYS_MTR_READTMsg tMsg = new SVC_PHYS_MTR_READTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.svcPhysMtrReadPk, rs.getBigDecimal(COL_SVC_PHYS_MTR_READ_PK));
        // mod start 2017/04/10 CSA Defect#18239
        tMsg = (SVC_PHYS_MTR_READTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        if (tMsg == null) {
            return null;
        }

        // Update
        String updateCase = getBatProcStsCdAndSendTrgtCd(rs);
        if (CASE_2.equals(updateCase)) {
            setValue(tMsg.rossBatProcStsCd, ROSS_BAT_PROC_STS.NOT_PROCESSED);
            setValue(tMsg.rossSendTrgtCd, ROSS_SEND_TRGT.APPLICABLE);
        } else if (CASE_3.equals(updateCase)) {
            setValue(tMsg.rossBatProcStsCd, ROSS_BAT_PROC_STS.PROCESSED);
            setValue(tMsg.rossSendTrgtCd, ROSS_SEND_TRGT.NOT_APPLICABLE_BY_TRANSACTION_STATUS);
        }
        // mod end 2017/04/10 CSA Defect#18239
        return tMsg;
    }

    // del start 2017/04/10 CSA Defect#18239
//    private boolean isCase1(ResultSet rs) throws SQLException {
//        if (hasValue(rs.getString(COL_DS_CONTR_PK)) && !DS_CONTR_EDI.CUSA.equals(rs.getString(COL_DS_CONTR_EDI_CD))) {
//            return true;
//        }
//        return false;
//    }
    // del end 2017/04/10 CSA Defect#18239

    // mod start 2017/04/10 CSA Defect#18239
    private boolean isCase2(ResultSet rs) throws SQLException {
        if (!ZYPConstant.FLG_OFF_N.equals(rs.getString(COL_EST_FLG))) {
            return false;
        }
        if (!MTR_CNTR_ID_TOTAL_HARD_READ.equals(rs.getString(COL_MTR_CNTR_ID)) && !MTR_CNTR_ID_BW_HARD_READ.equals(rs.getString(COL_MTR_CNTR_ID))) {
            return false;
        }
        if (!ZYPConstant.FLG_ON_Y.equals(rs.getString(COL_ACTV_FLG))) {
            return false;
        }
        return true;
    }
    // mod end 2017/04/10 CSA Defect#18239

    // del start 2017/04/10 CSA Defect#18239
//    private boolean isCheckDsContrCtrlStsCd(String cd) {
//        if (!CASE_2_DS_CONTR_CTRL_STS_NG_LIST.contains(cd)) {
//            return true;
//        }
//        return false;
//    }
    // del end 2017/04/10 CSA Defect#18239
}
