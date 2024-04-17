/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB076001;

import static com.canon.cusa.s21.batch.NSA.NSAB076001.constant.NSAB076001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.SPLY_REV_COST_YR_SMRYTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>    
 * Supply Revenue Cost Year Summary Creation
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/09   Hitachi         K.Ochiai        Create          N/A
 * 2018/07/18   Hitachi         K.Kitachi       Update          QC#26241
 *</pre>
 */

public class NSAB076001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt = null;

    /** Target Year */
    private String dwhTrgtYr = null;

    /** Commit Number */
    private int commitNumber;

    /** Total Commit Count */
    private int totalCommitCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NSAM0178E);
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.dwhTrgtYr = this.slsDt.substring(0, 4);
    }

    @Override
    protected void mainRoutine() {
        // Delete Data
        deleteTargetYrData("getDeleteData", setParam());

        // Insert Data
        insertTrgtYrData("getTargetYrData", setParam());
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB076001().executeBatch(NSAB076001.class.getSimpleName());
    }

    private Map<String, Object> setParam() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("dwhTrgtYr", this.dwhTrgtYr);
        return inParam;
    }

    // Delete the record of Target Year
    private void deleteTargetYrData(String sqlId, Map<String, Object> paramMap) {
        List<SPLY_REV_COST_YR_SMRYTMsg> inTMsgList = new ArrayList<SPLY_REV_COST_YR_SMRYTMsg>();
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

            while (rs.next()) {
                SPLY_REV_COST_YR_SMRYTMsg tMsgDelete = new SPLY_REV_COST_YR_SMRYTMsg();
                setValue(tMsgDelete.glblCmpyCd, this.glblCmpyCd);
                setValue(tMsgDelete.dwhTrgtYr, this.dwhTrgtYr);
                setValue(tMsgDelete.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
                setValue(tMsgDelete.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
                inTMsgList.add(tMsgDelete);
                if (this.commitNumber == inTMsgList.size()) {
                    deleteData(inTMsgList);
                    inTMsgList = new ArrayList<SPLY_REV_COST_YR_SMRYTMsg>();
                }
            }
            if (inTMsgList.size() != 0) {
                deleteData(inTMsgList);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int deleteData(List<SPLY_REV_COST_YR_SMRYTMsg> inMsgLst) {
        SPLY_REV_COST_YR_SMRYTMsg[] inMsgArray;
        inMsgArray = new SPLY_REV_COST_YR_SMRYTMsg[inMsgLst.size()];
        int deleteCount = S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));

        if (deleteCount != inMsgArray.length) {
            throw new S21AbendException(NSAM0475E, new String[] {inMsgArray[0].getTableName(), inMsgArray[0].getTableName() });
        }
        commit();
        return deleteCount;
    }

    // Insert the record of MLY_SPLY_REV_COST to SPLY_REV_COST_YR_SMRY
    private void insertTrgtYrData(String sqlId, Map<String, Object> paramMap) {
        List<SPLY_REV_COST_YR_SMRYTMsg> inTMsgList = new ArrayList<SPLY_REV_COST_YR_SMRYTMsg>();

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

            while (rs.next()) {
                inTMsgList.add(setCreateValue(rs));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertData(inTMsgList);
                    inTMsgList = new ArrayList<SPLY_REV_COST_YR_SMRYTMsg>();
                    this.totalCommitCount += commitCount;
                }
            }
            if (inTMsgList.size() != 0) {
                commitCount = insertData(inTMsgList);
                this.totalCommitCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private int insertData(List<SPLY_REV_COST_YR_SMRYTMsg> inMsgLst) {

        SPLY_REV_COST_YR_SMRYTMsg[] inMsgArray;
        inMsgArray = new SPLY_REV_COST_YR_SMRYTMsg[inMsgLst.size()];

        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NSAM0469E, new String[] {inMsgArray[0].getTableName(), inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
    }

    // START 2018/07/18 K.Kitachi [QC#26241, MOD]
    private SPLY_REV_COST_YR_SMRYTMsg setCreateValue(ResultSet rs) throws SQLException {
        SPLY_REV_COST_YR_SMRYTMsg inParam = new SPLY_REV_COST_YR_SMRYTMsg();

        setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
        setValue(inParam.dwhTrgtYr, rs.getString("DWH_TRGT_YR"));
        setValue(inParam.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        setValue(inParam.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));

        BigDecimal totRevAmt = fixVal(rs.getBigDecimal("TOT_REV_AMT"), MAX_VAL_AMT);
        BigDecimal totCostAmt = fixVal(rs.getBigDecimal("TOT_COST_AMT"), MAX_VAL_AMT);
        BigDecimal totPrftAmt = fixVal(calculatePrftAmt(totRevAmt, totCostAmt), MAX_VAL_AMT);
        BigDecimal totPrftPct = fixVal(calculatePrftPct(totCostAmt, totPrftAmt), MAX_VAL_PCT);
        setValue(inParam.totRevAmt, totRevAmt);
        setValue(inParam.totCostAmt, totCostAmt);
        setValue(inParam.totPrftAmt, totPrftAmt);
        setValue(inParam.totPrftPct, totPrftPct);

        return inParam;
    }

    private BigDecimal calculatePrftAmt(BigDecimal totRevAmt, BigDecimal totCostAmt) throws SQLException {
        BigDecimal prftAmt = totRevAmt.subtract(totCostAmt);
        return prftAmt;
    }

    private BigDecimal calculatePrftPct(BigDecimal totCostAmt, BigDecimal totPrftAmt) throws SQLException {
        BigDecimal prftPct = BigDecimal.ZERO;
        if (totCostAmt.compareTo(BigDecimal.ZERO) == 0) {
            return prftPct;
        }
        prftPct = totPrftAmt.multiply(BigDecimal.valueOf(MULTIPLY_ONE_HUNDRED)).divide(totCostAmt, 2, RoundingMode.HALF_UP);
        return prftPct;
    }

    private BigDecimal fixVal(BigDecimal val, BigDecimal maxVal) {
        BigDecimal rtrnVal = BigDecimal.ZERO;
        if (!hasValue(val)) {
            return rtrnVal;
        }
        rtrnVal = val;
        if (val.compareTo(maxVal) > 0) {
            rtrnVal = maxVal;
        }
        if (val.compareTo(maxVal.negate()) < 0) {
            rtrnVal = maxVal.negate();
        }
        return rtrnVal;
    }
    // END 2018/07/18 K.Kitachi [QC#26241, MOD]
}
