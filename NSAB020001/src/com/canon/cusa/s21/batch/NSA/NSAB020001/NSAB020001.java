/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB020001;

import static com.canon.cusa.s21.batch.NSA.NSAB020001.constant.NSAB020001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.db.DS_SUB_CONTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Sub Contract Termination Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/25/2015   Hitachi         T.Tsuchida      Create          N/A
 * 01/05/2016   Hitachi         T.Tsuchida      Update          QC2659
 * 02/16/2016   Hitachi         A.Kohinata      Update          QC3373
 * </pre>
 */
public class NSAB020001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** User Variable1 */
    private String usrVar1;

    /** Commit Number */
    private int commitNumber;

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
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get User Variable1
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_USER_VARIABLE1});
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // START 2016/02/16 A.Kohinata [QC3373, MOD]
        // Get Sales Date
//        this.batchProcDate = ZYPDateUtil.getBatProcDate(this.glblCmpyCd);
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, PROGRAM_ID);
        if (!hasValue(this.salesDate)) {
//            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_BATH_PROC_DATE});
            throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_SALES_DATE });
        }
        // END 2016/02/16 A.Kohinata [QC3373, MOD]

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount   = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        updTrmnSubContr();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * Main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB020001().executeBatch(NSAB020001.class.getSimpleName());

    }


    private void updTrmnSubContr() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam();

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getInputData", ssmParam, execParam);
            rs = stmt.executeQuery();

            int commitCount = 0;
            while (rs.next()) {
                updateDsSubContr(rs);
                commitCount++;
                this.normalCount++;

                if (commitCount == this.commitNumber) {
                    commitCount = 0;
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(Integer.parseInt(this.usrVar1));
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> getSsmParam() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("salesDate", this.salesDate);
        return ssmParam;
    }


    private void updateDsSubContr(ResultSet rs) throws SQLException {
        DS_SUB_CONTRTMsg tmsg = new DS_SUB_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tmsg.dsSubContrPk, rs.getBigDecimal(KEY_DS_SUB_CONTR_PK));

        tmsg = (DS_SUB_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(tmsg);

        ZYPEZDItemValueSetter.setValue(tmsg.contrTrmnFlg, ZYPConstant.FLG_ON_Y);
        S21FastTBLAccessor.update(tmsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            this.errorCount = 1;
            throw new S21AbendException(NSZM0031E, new String[] {DS_SUB_CONTR});
        }
    }
}
