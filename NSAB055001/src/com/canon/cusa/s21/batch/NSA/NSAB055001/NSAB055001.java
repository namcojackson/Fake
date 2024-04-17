/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB055001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTR_BLLG_SCHDTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SKIP_RECOV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/** 
 *<pre>
 * Update Billing Schedule Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2016   Hitachi         T.Aoyagi        Create          QC#4959
 * 01/24/2018   Hitachi         T.Tomita        Update          QC#23684
 *</pre>
 */
public class NSAB055001 extends S21BatchMain implements ZYPConstant {

    /** This data has been updated by another user. */
    private static final String NZZM0003E = "NZZM0003E";

    /** It failed to update the @ table. */
    private static final String NZZM0012E = "NZZM0012E";

    /** [@] field is mandatory. */
    private static final String ZZM9000E = "ZZM9000E";

    /** [@] is not found. */
    private static final String ZZZM9006E = "ZZZM9006E";

    /** MAX_COMMIT_NUMBER:1000 */
    private static final int MAX_COMMIT_NUMBER = 1000;

    /** Termination Code */
    private TERM_CD termCd;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Commit Number */
    private int commitNumber;

    /** User Variable1 */
    private String usrVar1;

    /** Sales Date */
    private String salesDate;

    /** Multi Billing Calculation */
    private BigDecimal multiCnt;

    /** Total Count */
    private int totalCount;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * Initialize method.
     */
    @Override
    protected void initRoutine() {

        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code"});
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get User Variable1
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(ZZM9000E, new String[] {"User Variable1"});
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

        // Get Number Constant
        // Mod Start 2018/01/24 QC#23684
        this.multiCnt = ZYPCodeDataUtil.getNumConstValue("NSAB0550MULTI_BLLG_CALC_CNT", this.glblCmpyCd);
        // Mod End 2018/01/24 QC#23684
        if (!hasValue(this.multiCnt)) {
            throw new S21AbendException(ZZZM9006E, new String[]{"VAR_CHAR_CONST"});
        }

        // Initialize
        this.totalCount = 0;
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        updateBllgSchd();
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCount, 0, this.totalCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB055001().executeBatch(NSAB055001.class.getSimpleName());
    }

    private void updateBllgSchd() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = getExecParam();
        Map<String, Object> ssmParam = getSsmParam(null);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getSkipBllgSchd", ssmParam, execParam);
            rs = stmt.executeQuery();
            int contrCnt = 0;
            BigDecimal prvContrPk = null;
            BigDecimal curContrPk = null;

            while (rs.next()) {
                curContrPk = rs.getBigDecimal("DS_CONTR_PK");

                if (prvContrPk == null || prvContrPk.compareTo(curContrPk) != 0) {
                    if (contrCnt > 0 && contrCnt % this.commitNumber == 0) {
                        commit();
                    }
                    prvContrPk = curContrPk;
                    contrCnt++;
                }
                closeBllgSchd(rs);
                totalCount++;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_COMMIT_NUMBER);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> getSsmParam(BigDecimal prntDsContrBllgSchdPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("skipRecovTpCdSkip", SKIP_RECOV_TP.SKIP);
        ssmParam.put("salesDate", this.salesDate);
        ssmParam.put("dsBllgSchdStsCdClose", DS_BLLG_SCHD_STS.CLOSE);
        ssmParam.put("multiCnt", this.multiCnt);
        ssmParam.put("usrVar1", this.usrVar1);
        return ssmParam;
    }

    private void closeBllgSchd(ResultSet rs) throws SQLException {

        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, rs.getBigDecimal("DS_CONTR_BLLG_SCHD_PK"));
        inMsg = (DS_CONTR_BLLG_SCHDTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (inMsg == null) {
            throw new S21AbendException(NZZM0003E, new String[] {"DS_CONTR_BLLG_SCHD"});
        }

        setValue(inMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.CLOSE);
        EZDTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            throw new S21AbendException(NZZM0012E, new String[] {"DS_CONTR_BLLG_SCHD"});
        }
    }
}
