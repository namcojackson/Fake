/**
 * <pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2011   Fujitsu         I.Kondo         Create          N/A
 * </pre>
 */
package com.canon.cusa.s21.batch.NZZ.NZZB001001;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDAbendException;

import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.internal.S21BatchTransactionQuery;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;

/**
 * ARCHIVE_DATA PL/SQL call batch.
 */
public class NZZB001001 extends S21BatchMain {

    /** procCount */
    private int procCount = 0;

    /** normalCnt */
    private int normalCnt = 0;

    /** errCnt */
    private int errCnt = 0;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialization S21BatchMain */
        new NZZB001001().executeBatch(NZZB001001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
    }

    @Override
    protected void mainRoutine() {
        try {
            execute();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(TERM_CD.NORMAL_END, this.normalCnt, this.errCnt, this.procCount);
    }

    private void execute() throws SQLException {
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        Map<String, Serializable> param = new HashMap<String, Serializable>();
        param.put("glblCmpyCd", getGlobalCompanyCode());
        param.put("srcTblOwnrNm", getUserVariable1());
        param.put("arcProcGrpId", getUserVariable2());

        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = ssmLLClient.createPreparedStatement("getArcRqstPkList", param, ssmParam);
            rs = stmt.executeQuery();

            S21BatchTransactionQuery query = new S21BatchTransactionQuery();

            while (rs.next()) {
                int rqstPk = rs.getBigDecimal("ARC_RQST_PK").intValue();
                boolean result = query.archiveData(rqstPk);
                if (!result) {
                    addCnt(1, 0);
                    throw new EZDAbendException("ERROR! Execute PL/SQL[ARCHIVE_DATA] RQST_ID:" + rqstPk);
                } else {
                    addCnt(1, 1);
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void addCnt(int procCnt, int successCnt) {
        this.procCount = this.procCount + procCnt;
        this.normalCnt = this.normalCnt + successCnt;
        this.errCnt = this.errCnt + (procCnt - successCnt);
    }
}
