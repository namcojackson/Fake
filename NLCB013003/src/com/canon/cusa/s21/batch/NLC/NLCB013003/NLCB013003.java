/**
 * <pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB013003;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;

import business.db.SLS_RSLT_WRKTMsg;
import business.db.STK_IN_RSLT_WRKTMsg;
import business.db.STK_OUT_RSLT_WRKTMsg;

import com.canon.cusa.s21.batch.NLC.NLCB013003.NLCB013003;
import com.canon.cusa.s21.batch.NLC.NLCB013003.S21SsmBatchClientCustom;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21ResultSetMapper;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * SCM/DB Daily Stock-In Result To Canon,Inc.
 * Delete Work Data.
 *
 * Argument:Global Company Code
 *          User Company Code
 *          User Variable1(Process Code:Table ID)
 *          User Variable3(Commit Count for Bulk insert)
 *          User Variable4(T or null : TruncateMode Y:DaleteMode)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/04/2010   Canon           M.Yamada        Create          N/A
 * 10/13/2011   CSAI            M.Takahashi     Update          SR#366053
 * </pre>
 */
public class NLCB013003 extends S21BatchMain {
    /** Debug level for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;

    /** Message ID : ZZM9000E The field of [@] is not input. */
    private static final String MSG_ID_ZZM9000E = "ZZM9000E";

    /** The value which is not numerical was input to the field of [@]. */
    private static final String MSG_ID_ZZM9004E = "ZZM9004E";

    /** Message ID : ZZBM0009I */
    private static final String MSG_ID_ZZBM0009I = "ZZBM0009I";

    /** Message ID : NLCM0053E The process abended. */
    private static final String MSG_ID_NLCM0053E = "NLCM0053E";

    /** Message ID : NLCM0063E The value you entered is incorrect. */
    private static final String MSG_ID_NLCM0063E = "NLCM0063E";

    /** Message string : Global Company Code */
    private static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Process Type */
    private static final String MSG_STR_PROC_CODE = "Process Code";

    /** Message string : Commit Count */
    private static final String MSG_STR_COMMIT_COUNT = "Commit Count(VAR_USER3)";

    /** Table ID : STK_IN_RSLT_WRK */
    private static final String TABLE_ID_STK_IN_RSLT_WRK = "STK_IN_RSLT_WRK";

    /** Table ID : STK_OUT_RSLT_WRK */
    private static final String TABLE_ID_STK_OUT_RSLT_WRK = "STK_OUT_RSLT_WRK";

    /** Table ID : SLS_RSLT_WRK */
    private static final String TABLE_ID_SLS_RSLT_WRK = "SLS_RSLT_WRK";

    /** S21UserProfileService */
    private S21UserProfileService profile;

    /** Global Company Code */
    private String glblCmpyCd;

    /** The Process Code */
    private String procCode;

    /** Commit Count */
    private int commitCount = 1000;

    /** The number of cases : Select from STK_IN_RSLT_WRK */
    private int selectCount;

    /** The number of cases : Delete to STK_IN_RSLT_WRK */
    private int deleteCount;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Fetch Size */
    private static final int FETCH_SIZE = 1000;

    /** Termination code */
    private TERM_CD termCd;

    /** deleteMode */
    private String deleteMode = "T";

    /** SSM Client Custom */
    private S21SsmBatchClientCustom ssmBatchClientCustom = null;

    @Override
    protected void initRoutine() {
        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Initialization of variable
        selectCount = 0;
        deleteCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profile = S21UserProfileServiceFactory.getInstance().getService();

        // Check on input parameter
        checkParameter();

        // Initialization of SSM Custom
        ssmBatchClientCustom = new S21SsmBatchClientCustom(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("tableName", procCode);

        if (TABLE_ID_STK_IN_RSLT_WRK.equals(procCode)) {

            if ("T".equals(deleteMode)) {
                ssmBatchClientCustom.delete("TruncateTable", params);
                S21InfoLogOutput.println("TRUNCATE_MODE:" + procCode);
            } else {
                deleteStkInRsltWork("getStkInRsltWrkDeleteData", params);
            }

        } else if (TABLE_ID_STK_OUT_RSLT_WRK.equals(procCode)) {

            if ("T".equals(deleteMode)) {
                ssmBatchClientCustom.delete("TruncateTable", params);
                S21InfoLogOutput.println("TRUNCATE_MODE:" + procCode);
            } else {
                deleteStkOutRsltWork("getStkOutRsltWrkDeleteData", params);
            }

        } else if (TABLE_ID_SLS_RSLT_WRK.equals(procCode)) {

            if ("T".equals(deleteMode)) {
                ssmBatchClientCustom.delete("TruncateTable", params);
                S21InfoLogOutput.println("TRUNCATE_MODE:" + procCode);
            } else {
                deleteSlsRsltWork("getSlsRsltWrkDeleteData", params);
            }

        } else {
            String[] tmp = {MSG_STR_PROC_CODE + "(" + procCode + ")" };
            S21InfoLogOutput.println(MSG_ID_NLCM0063E + " " + MSG_STR_PROC_CODE + ":" + procCode);
            throw new S21AbendException(MSG_ID_NLCM0063E, tmp);
        }
    }

    @Override
    protected void termRoutine() {
        // The number of cases : Select is output
        String[] tmp1 = {procCode, "Read", Integer.toString(selectCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp1);
        // The number of cases : Delete from Work Table
        String[] tmp2 = {procCode, "Delete", Integer.toString(deleteCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp2);

        // Setting of termination code
        setTermState(termCd, selectCount, 0, deleteCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {

        // Initialization of S21BatchMain
        new NLCB013003().executeBatch(NLCB013003.class.getSimpleName());
    }

    /**
     * Check processing of input parameter
     */
    private void checkParameter() {

        glblCmpyCd = profile.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            String[] tmp = {MSG_STR_COMP_CODE };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }

        // Get input parameter (Process Code : Table ID)
        procCode = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(getUserVariable1())) {
            String[] tmp = {MSG_STR_PROC_CODE, getUserVariable1() };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }

        // Get input parameter (Commit Count)
        String str = getUserVariable3();
        if (!ZYPCommonFunc.hasValue(str)) {
            String[] tmp = {MSG_STR_COMMIT_COUNT };
            throw new S21AbendException(MSG_ID_ZZM9000E, tmp);
        }
        if (str.matches("[0-9]+")) {
            commitCount = Integer.valueOf(str).intValue();
        } else {
            throw new S21AbendException(MSG_ID_ZZM9004E, new String[] {MSG_STR_COMMIT_COUNT + "(" + str + ")" });
        }

        deleteMode = getUserVariable4();

        if ("Y".equals(deleteMode)) {
            deleteMode = "Y"; // DeleteMode
        } else {
            deleteMode = "T"; // TruncateMode
        }
    }

    /**
     * <pre>
     * Delete the Stock-In Rslt Work. 
     * </pre>
     * 
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void deleteStkInRsltWork(String key, Map<String, String> params) {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            List<STK_IN_RSLT_WRKTMsg> stkInRsltWrkList = new ArrayList<STK_IN_RSLT_WRKTMsg>();
            // Set the fetch size.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            // Execute search for delete query.
            stmt = ssmLLClient.createPreparedStatement(key, params, execParam);
            rs = stmt.executeQuery();

            int cnt = 0;
            int execCnt = 0;
            while (rs.next()) {

                // Delete Work Table.
                STK_IN_RSLT_WRKTMsg stkInRsltWrkTMsg = new STK_IN_RSLT_WRKTMsg();
                S21ResultSetMapper.map(rs, stkInRsltWrkTMsg);
                stkInRsltWrkList.add(stkInRsltWrkTMsg);
                cnt++;
                selectCount++;

                // commit
                if (cnt == commitCount) {
                    execCnt = S21FastTBLAccessor.removePhysical((STK_IN_RSLT_WRKTMsg[]) stkInRsltWrkList.toArray(new STK_IN_RSLT_WRKTMsg[0]));
                    if (execCnt != cnt) {
                        throw new S21AbendException(MSG_ID_NLCM0053E);
                    }
                    commit();
                    deleteCount += cnt;
                    cnt = 0;
                    stkInRsltWrkList.clear();
                }
            }
            if (!stkInRsltWrkList.isEmpty()) {
                execCnt = S21FastTBLAccessor.removePhysical((STK_IN_RSLT_WRKTMsg[]) stkInRsltWrkList.toArray(new STK_IN_RSLT_WRKTMsg[0]));
                if (execCnt != cnt) {
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                deleteCount += cnt;
                stkInRsltWrkList = null;
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * <pre>
     * Delete the Stock-Out Result Work. 
     * </pre>
     * 
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void deleteStkOutRsltWork(String key, Map<String, String> params) {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            List<STK_OUT_RSLT_WRKTMsg> stkOutRsltWrkList = new ArrayList<STK_OUT_RSLT_WRKTMsg>();
            // Set the fetch size.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            // Execute search for delete query.
            stmt = ssmLLClient.createPreparedStatement(key, params, execParam);
            rs = stmt.executeQuery();

            int cnt = 0;
            int execCnt = 0;
            while (rs.next()) {

                // Delete Work Table.
                STK_OUT_RSLT_WRKTMsg stkOutRsltWrkTMsg = new STK_OUT_RSLT_WRKTMsg();
                S21ResultSetMapper.map(rs, stkOutRsltWrkTMsg);
                stkOutRsltWrkList.add(stkOutRsltWrkTMsg);
                cnt++;
                selectCount++;

                // commit
                if (cnt == commitCount) {
                    execCnt = S21FastTBLAccessor.removePhysical((STK_OUT_RSLT_WRKTMsg[]) stkOutRsltWrkList.toArray(new STK_OUT_RSLT_WRKTMsg[0]));
                    if (execCnt != cnt) {
                        throw new S21AbendException(MSG_ID_NLCM0053E);
                    }
                    commit();
                    deleteCount += cnt;
                    cnt = 0;
                    stkOutRsltWrkList.clear();
                }
            }
            if (!stkOutRsltWrkList.isEmpty()) {
                execCnt = S21FastTBLAccessor.removePhysical((STK_OUT_RSLT_WRKTMsg[]) stkOutRsltWrkList.toArray(new STK_OUT_RSLT_WRKTMsg[0]));
                if (execCnt != cnt) {
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                deleteCount += cnt;
                stkOutRsltWrkList = null;
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * <pre>
     * Delete the Sales Result Work. 
     * </pre>
     * 
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void deleteSlsRsltWork(String key, Map<String, String> params) {

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            List<SLS_RSLT_WRKTMsg> slsRsltWrkList = new ArrayList<SLS_RSLT_WRKTMsg>();
            // Set the fetch size.
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);

            // Execute search for delete query.
            stmt = ssmLLClient.createPreparedStatement(key, params, execParam);
            rs = stmt.executeQuery();

            int cnt = 0;
            int execCnt = 0;
            while (rs.next()) {

                // Delete Work Table.
                SLS_RSLT_WRKTMsg slsRsltWrkTMsg = new SLS_RSLT_WRKTMsg();
                S21ResultSetMapper.map(rs, slsRsltWrkTMsg);
                slsRsltWrkList.add(slsRsltWrkTMsg);
                cnt++;
                selectCount++;

                // commit
                if (cnt == commitCount) {
                    execCnt = S21FastTBLAccessor.removePhysical((SLS_RSLT_WRKTMsg[]) slsRsltWrkList.toArray(new SLS_RSLT_WRKTMsg[0]));
                    if (execCnt != cnt) {
                        throw new S21AbendException(MSG_ID_NLCM0053E);
                    }
                    commit();
                    deleteCount += cnt;
                    cnt = 0;
                    slsRsltWrkList.clear();
                }
            }
            if (!slsRsltWrkList.isEmpty()) {
                execCnt = S21FastTBLAccessor.removePhysical((SLS_RSLT_WRKTMsg[]) slsRsltWrkList.toArray(new SLS_RSLT_WRKTMsg[0]));
                if (execCnt != cnt) {
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                deleteCount += cnt;
                slsRsltWrkList = null;
            }

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }
}
