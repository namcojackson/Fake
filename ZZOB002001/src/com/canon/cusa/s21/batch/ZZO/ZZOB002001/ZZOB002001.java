/*
 * <pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.ZZO.ZZOB002001;

import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.MODE_DAYTIME;
import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.MODE_NIGHTLY;
import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.MSG_GLBL_CMPY_CD;
import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.TARGET_ALL;
import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.TARGET_CONFIG;
import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.TARGET_FULL;
import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.USR_VAR1;
import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.USR_VAR2;
import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.USR_VAR3;
import static com.canon.cusa.s21.batch.ZZO.ZZOB002001.constant.ZZOB002001Constant.ZZZM9025E;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.internal.S21BatchTransactionQuery;
import com.canon.cusa.s21.framework.common.util.S21StopWatch;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Update Table Statistics.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/11/2019   Fujitsu         C.Ogaki         Create          QC#30652
 * 03/09/2020   CITS            K.Fukumura      Update          QC#56177
 * 05/25/2020   CITS            K.Fukumura      Update          QC#56997
 * 07/07/2020   CITS            K.Ogino         Update          QC#57302
 *</pre>
 */
public class ZZOB002001 extends S21BatchMain {

    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd;

    /** DB schema */
    private String dbSchema;

    /** Exec Mode */
    private String mode;

    /** Update Target */
    private String target;

    // QC#56997 2020/05/25 Start
    /** Success count */
    private int successCount = 0;
    
    /** Fail Count */
    private int failCount = 0;
    // QC#56997 2020/05/25 End

    /** Presence or absence of "NO_INVALIDATE => FALSE" Option */
    private boolean noInvalidateFlase;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new ZZOB002001().executeBatch(ZZOB002001.class.getSimpleName());

    }

    @Override
    protected void initRoutine() {
        // blank check(Global Company Code)
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_GLBL_CMPY_CD });
        }

        // User Variable 1 [DB Schema]
        this.dbSchema = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(this.dbSchema)) {
            throw new S21AbendException(ZZZM9025E, new String[] {USR_VAR1 });
        }

        // User Variable 2 [Target]
        this.target = getUserVariable2();
        if (!ZYPCommonFunc.hasValue(this.target)) {
            throw new S21AbendException(ZZZM9025E, new String[] {USR_VAR2 });
        }

        // User Variable 3 [Mode]
        this.mode = getUserVariable3();
        if (this.target.equals(TARGET_CONFIG) && !ZYPCommonFunc.hasValue(this.mode)) {
            throw new S21AbendException(ZZZM9025E, new String[] {USR_VAR3 });
        }

        // User Variable 4 [Presence or absence of
        // "NO_INVALIDATE => FALSE" Option]
        this.noInvalidateFlase = Boolean.parseBoolean(getUserVariable4());

    }

    @Override
    protected void mainRoutine() {
        try {
            // QC#57302
            if (TARGET_FULL.equals(this.target)) {
                executeSchemaStatistic();
            } else {
                execute();
             }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    @Override
    protected void termRoutine() {
        // QC#56997 2020/05/25 Start
        // setTermState(this.termCd, 0, 0, 0);
        setTermState(this.termCd, successCount, failCount, successCount + failCount);
        // QC#56997 2020/05/25 End
    }

    private void execute() throws SQLException {
        S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        Map<String, Serializable> param = new HashMap<String, Serializable>();
        param.put("schema", this.dbSchema);

        S21SsmExecutionParameter ssmParam = new S21SsmExecutionParameter();
        ssmParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        ssmParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        ssmParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        ResultSet rsConfig = null;

        try {
            // get table list
            stmt = ssmLLClient.createPreparedStatement("getStaleStatisticTableList", param, ssmParam);
            rs = stmt.executeQuery();

            if (TARGET_ALL.equals(this.target)) {
                S21StopWatch sw = new S21StopWatch();
                boolean result;
                String owner;
                String tableNm;
                while (rs.next()) {
                    owner = rs.getString("OWNER");
                    tableNm = rs.getString("TABLE_NAME");
                    S21InfoLogOutput.println(String.format("OWNER=\"%s\", TABLE_NAME=\"%s\"", owner, tableNm));

                    sw.start();
                    result = updateStatistics(tableNm, this.noInvalidateFlase);
                    sw.stop();
                    S21InfoLogOutput.println(String.format("Update statistics Finish: %s [%d msec]", tableNm, sw.getElapsedMilliSec()));
                    if (!result) {
                        // QC#56997 2020/05/25 Start
                        failCount++;
                        // QC#56997 2020/05/25 End
                        this.termCd = TERM_CD.WARNING_END;
                    }
                    // QC#56997 2020/05/25 Start
                    else {
                        successCount++;
                    }
                    // QC#56997 2020/05/25 End
                }
            } else if (TARGET_CONFIG.equals(this.target)) {

                // get config info(STAT_UPD_CONFIG)
                param = new HashMap<String, Serializable>();
                param.put("glblCmpyCd", glblCmpyCd);
                if (MODE_DAYTIME.equals(mode)) {
                    param.put("MODE_DAYTIME", mode);
                } else if (MODE_NIGHTLY.equals(mode)) {
                    param.put("MODE_NYGHTLY", mode);
                }
                stmt2 = ssmLLClient.createPreparedStatement("getStatisticsUpdateList", param);
                rsConfig = stmt2.executeQuery();

                S21StopWatch sw = new S21StopWatch();
                boolean result;
                String owner;
                String tableNm;
                while (rs.next()) {
                    owner = rs.getString("OWNER");
                    tableNm = rs.getString("TABLE_NAME");
                    S21InfoLogOutput.println(String.format("OWNER=\"%s\", TABLE_NAME=\"%s\"", owner, tableNm));

                    // Confirm whether the table to be updated
                    rsConfig.beforeFirst();
                    String statUpdTblNm;
                    // QC#56177 Start
                    // boolean update = false;
                    boolean flgExist = false;
                    // QC#56177 Start
                    while (rsConfig.next()) {
                        statUpdTblNm = rsConfig.getString("STAT_UPD_TBL_NM");
                        if (tableNm.equals(statUpdTblNm)) {
                            // QC#56177 Start
                            //update = true;
                            flgExist = true;
                            // QC#56177 Start
                            break;
                        }
                    }
                    // QC#56177 Start
                    //if (update) {
                    if (!flgExist) {
                    // QC#56177 End
                        // Update statistics
                        sw.start();
                        result = updateStatistics(tableNm, this.noInvalidateFlase);
                        sw.stop();
                        S21InfoLogOutput.println(String.format("Update statistics Finish: %s [%d msec]", tableNm, sw.getElapsedMilliSec()));
                        if (!result) {
                            this.termCd = TERM_CD.WARNING_END;
                            // QC#56997 2020/05/25 Start
                            failCount++;
                            // QC#56997 2020/05/25 End
                        }
                        // QC#56997 2020/05/25 Start
                        else {
                           successCount++;
                        }
                        // QC#56997 2020/05/25 End
                    } else {
                        S21InfoLogOutput.println("It is not a table to update statistics.");
                    }
                }
            }

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
            S21SsmLowLevelCodingClient.closeResource(stmt2, rsConfig);
        }
    }

    /**
     * updateStatistics
     * @param tableName
     * @param noInvldOptFalse "NO_INVAALIDATE => FALSE" option
     * @return
     */
    private Boolean updateStatistics(String tableName, boolean noInvldOptFalse) {
        S21BatchTransactionQuery queryComponet = new S21BatchTransactionQuery();
        String sql;
        if (noInvldOptFalse) {
            sql = String.format("DBMS_STATS.GATHER_TABLE_STATS ('%s','%s', CASCADE => TRUE, NO_INVALIDATE => FALSE); ", this.dbSchema, tableName);
        } else {
            // QC#56997 2020/05/25 Start
            // sql = String.format("DBMS_STATS.GATHER_TABLE_STATS ('%s','%s', CASCADE => TRUE); ", this.dbSchema, tableName);
            sql = String.format("DBMS_STATS.GATHER_TABLE_STATS ('%s','%s', CASCADE => TRUE, NO_INVALIDATE => TRUE); ", this.dbSchema, tableName);
            // QC#56997 2020/05/25 End
        }

        StringBuilder cmd = new StringBuilder();
        cmd.append("DECLARE ");
        cmd.append("BEGIN ");
        cmd.append(sql);
        cmd.append("END;");

        int num = queryComponet.executeUpdate(cmd.toString());

        if (num < 0) {
            S21InfoLogOutput.println(String.format("Error happened \"%s\"", sql));
            return false;
        } else {
            S21InfoLogOutput.println(String.format("Statistics update finished \"%s\"", sql));
        }
        return true;
    }

    // QC#57302 Add
    private void executeSchemaStatistic() throws SQLException {
        S21BatchTransactionQuery queryComponet = new S21BatchTransactionQuery();
        S21StopWatch sw = new S21StopWatch();
        String sql = "";

        if (this.noInvalidateFlase) {
            sql = String.format("DBMS_STATS.GATHER_SCHEMA_STATS ('%s', CASCADE => TRUE, NO_INVALIDATE => FALSE, options => 'GATHER AUTO'); ", this.dbSchema);
        } else {
            sql = String.format("DBMS_STATS.GATHER_SCHEMA_STATS ('%s', CASCADE => TRUE, NO_INVALIDATE => TRUE, options => 'GATHER AUTO'); ", this.dbSchema);
        }

        StringBuilder cmd = new StringBuilder();
        cmd.append("DECLARE ");
        cmd.append("BEGIN ");
        cmd.append(sql);
        cmd.append("END;");

        S21InfoLogOutput.println(String.format("SCHEMA=\"%s\"", this.dbSchema));

        sw.start();
        int num = queryComponet.executeUpdate(cmd.toString());
        sw.stop();
        S21InfoLogOutput.println(String.format("Update statistics Finish: %s [%d msec]", this.dbSchema, sw.getElapsedMilliSec()));

        if (num < 0) {
            S21InfoLogOutput.println(String.format("Error happened \"%s\"", sql));
        } else {
            S21InfoLogOutput.println(String.format("Statistics update finished \"%s\"", sql));
        }
    }
}
