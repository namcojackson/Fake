/**
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 */
package com.canon.cusa.s21.batch.NLC.NLCB001001;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import business.db.INVTY_SNAP_SHOTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
//import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
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
 * Inventory Master Snapshot
 * 
 * [Daily Processing]
 *   Inventory Master copy of Inventory Master Snapshot.
 *   Daily Shapshot information, "n" days hold, when obtaining the latest data, 
 *   to remove beyond the expiry date.
 *
 * [Monthly Processing]
 *   Update the Inventory Snapshot's Monthly Close Date.
 *   Monthly Shapshot information, "n" months hold, when obtaining the latest data, 
 *   to remove beyond the expiry date.
 *
 * [Daily + Monthly Processing]
 *   Inventory Master copy of Inventory Master Snapshot.
 *   Daily Shapshot information, "n" days hold, when obtaining the latest data, 
 *   to remove beyond the expiry date.
 *   Monthly Shapshot information, "n" months hold, when obtaining the latest data, 
 *   to remove beyond the expiry date.
 *
 * [Delete Processing]
 *   Daily Shapshot information, "n" days hold, when obtaining the latest data, 
 *   to remove beyond the expiry date.
 *   Monthly Shapshot information, "n" months hold, when obtaining the latest data, 
 *   to remove beyond the expiry date.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/30   Fujitsu         S.Yoshida       Create          N/A
 * 2011/07/26   CSAI            M.Takahashi     Update          354677
 *</pre>
 */
public class NLCB001001 extends S21BatchMain {

    // -- Process Type --------------------------
    /** Process Type : Daily Processing */
    private static final String PROC_TP_DLY = "1";

    /** Process Type : Monthly Processing */
    private static final String PROC_TP_MLY = "2";

    /** Process Type : Daily + Monthly Processing */
    private static final String PROC_TP_DLY_MLY = "3";

    /** Process Type : Delete Processing */
    private static final String PROC_TP_DEL = "4";

    // -- Constant table keys -------------------
    /** Constant table key : Days */
    private static final String CONST_KEY_DLY = "NLCB0010_DAYS";

    /** Constant table key : Month */
    private static final String CONST_KEY_MLY = "NLCB0010_MONTH";

    // -- Error Message Code --------------------
    /** The field of [@] is not input. */
    private static final String MSG_ID_ZZM9000E = "ZZM9000E";

    /** The value which is not numerical was input to the field of [@]. */
    private static final String MSG_ID_ZZM9004E = "ZZM9004E";

    /** Date verification error occurred in the field of [@]. */
    private static final String MSG_ID_ZZM9010E = "ZZM9010E";

    /** The code you entered cannot be found in the master. */
    private static final String MSG_ID_NLCM0005E = "NLCM0005E";

    /** The value you entered is incorrect. */
    private static final String MSG_ID_NLCM0052E = "NLCM0052E";

    /** The process abended. */
    private static final String MSG_ID_NLCM0053E = "NLCM0053E";

    /** The value you entered is incorrect. */
    private static final String MSG_ID_NLCM0083E = "NLCM0083E";

    // -- Other Internal constants --------------
    /** Oracle sequence key : Inventory Snapshot PK */
//    private static final String SNAP_SHOPT_SQ_KEY = "INVTY_SNAP_SHOT_SQ";

    /** Debug level for Debug */
    private static final int CST_DEBUG_MSG_LVL = 1;

    /** Process date pattern */
    private static final String PROC_DT_PTRN = "yyyyMMdd";

    /** Default Commit Count */
    private static final int DEF_COMMIT_CNT = 1000;

    /** Default Fetch Size */
    private static final int DEF_FETCH_SIZE = 1000;

    // -- Input parameters ----------------------
    /** Commit Count */
    private int commitCount = DEF_COMMIT_CNT;

    /** Fetch Size */
    private int fetchSize = DEF_FETCH_SIZE;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Process Type */
    private String procTp = null;

    /** Location Type Code */
    private String locTpCd = null;

    // -- Constant table values -----------------
    /** Daily Data the term of keeping (Days) */
    private BigDecimal dlyDataTrmOfKeep = null;

    /** Monthly Data the term of keeping (Month) */
    private BigDecimal mlyDataTrmOfKeep = null;

    // -- Date to be used in processing ---------
    /** Process date */
    private String procDt = null;

    /** Monthly close date */
    private String mlyCloDt = null;

    /** Delete day for Daily */
    private String dlyDelDt = null;

    /** Delete day for Monthly */
    private String mlyDelDt = null;

    // -- Count of processing -------------------
    /** Total commit count */
    private int totalCommitCount = 0;

    /** Total commit count */
    private int deleteCommitCount = 0;

    /** Total commit count */
    private int createCommitCount = 0;

    /** Total commit count */
    private int updateCommitCount = 0;

    // -- Other Internal Variable ---------------
    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** nonDeleteFlg */
    private String nonDeleteFlg = ZYPConstant.FLG_OFF_N;

    /**
     * <pre>
     * Main method.
     * This method is Initialization S21BatchMain.
     * </pre>
     * 
     * @param args Input parameter.
     */
    public static void main(String[] args) {
        S21InfoLogOutput.println("Start Inventory Master Snapshot Batch.");

        // Initialization S21BatchMain
        new NLCB001001().executeBatch(NLCB001001.class.getSimpleName());
    }

    /**
     * <pre>
     * Initialization routine.
     * </pre>
     * 
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#initRoutine()
     */
    protected void initRoutine() {

        try {

            // Initialization
            profileService = S21UserProfileServiceFactory.getInstance().getService();
            ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            totalCommitCount = 0;
            termCd = TERM_CD.NORMAL_END;

            // Get and check the input parameters.
            getInParams();

        } catch (S21AbendException e) {
            S21InfoLogOutput.println(e.getMessage());
            throw e;
        }
    }

    /**
     * <pre>
     * Main routine.
     * </pre>
     * 
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#mainRoutine()
     */
    protected void mainRoutine() {

        try {

            // Get the Process date.
            procDt = ZYPDateUtil.getBatProcDate();

            // Daily Processing
            if (PROC_TP_DLY.equals(procTp)) {
                EZDDebugOutput.println(CST_DEBUG_MSG_LVL, "Execute the daily processing.", this);
                executeDlyProc();

                // Monthly Processing
            } else if (PROC_TP_MLY.equals(procTp)) {
                EZDDebugOutput.println(CST_DEBUG_MSG_LVL, "Execute the monthly processing.", this);
                executeMlyProc();

                // Daily + Monthly Processing
            } else if (PROC_TP_DLY_MLY.equals(procTp)) {
                EZDDebugOutput.println(CST_DEBUG_MSG_LVL, "Execute the daily and monthly processing.", this);
                executeDlyMlyProc();

                // Delete Processing
            } else if (PROC_TP_DEL.equals(procTp)) {
                EZDDebugOutput.println(CST_DEBUG_MSG_LVL, "Execute the delete processing.", this);
                executeDelete();
            }

        } catch (S21AbendException e) {
            S21InfoLogOutput.println(e.getMessage());
            throw e;
        }
    }

    /**
     * <pre>
     * Term routine.
     * </pre>
     * 
     * @see com.canon.cusa.s21.framework.batch.S21BatchMain#termRoutine()
     */
    protected void termRoutine() {

        // Print result.
        S21InfoLogOutput.println("Inventory Snapshot Batch is normaly end.");
        S21InfoLogOutput.println("Delete commit count is " + deleteCommitCount + ".");
        S21InfoLogOutput.println("Create commit count is " + createCommitCount + ".");
        S21InfoLogOutput.println("Update commit count is " + updateCommitCount + ".");
        S21InfoLogOutput.println("Total  commit count is " + totalCommitCount + ".");

        // Set term code and total commit count.
        setTermState(termCd, totalCommitCount, 0, totalCommitCount);
    }

    /**
     * <pre>
     *  Check the input parameters.
     *  If an error occurs, throws Exceptin.
     * </pre>
     */
    private void getInParams() {

        // Get the Global Company Code.
        // If an error occurs, throw Exception.
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get the Process Type.
        // If an error occurs, throw Exception.
        procTp = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(procTp)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {"Process Type" });
        }
        if (!PROC_TP_DLY.equals(procTp) && !PROC_TP_MLY.equals(procTp) && !PROC_TP_DLY_MLY.equals(procTp) && !PROC_TP_DEL.equals(procTp)) {
            throw new S21AbendException(MSG_ID_NLCM0052E);
        }

        // Get the Location Type Code.
        // If an error occurs, throw Exception.
        locTpCd = getUserVariable2();
        if (ZYPCommonFunc.hasValue(locTpCd) && !checkCdVal(LOC_TP.class, locTpCd)) {
            throw new S21AbendException(MSG_ID_NLCM0005E);
        }

        // Get the Commit Count.
        // If an error occurs, throw Exception.
        if (!ZYPCommonFunc.hasValue(getUserVariable3())) {
            commitCount = DEF_COMMIT_CNT;
        } else if (getUserVariable3().matches("[0-9]+")) {
            commitCount = Integer.valueOf(getUserVariable3()).intValue();
        } else {
            throw new S21AbendException(MSG_ID_ZZM9004E, new String[] {"Commit Count" });
        }

        // Get the Fetch Count.
        // If an error occurs, throw Exception.
        if (!ZYPCommonFunc.hasValue(getUserVariable4())) {
            fetchSize = DEF_FETCH_SIZE;
        } else if (getUserVariable4().matches("[0-9]+")) {
            fetchSize = Integer.valueOf(getUserVariable4()).intValue();
        } else {
            throw new S21AbendException(MSG_ID_ZZM9004E, new String[] {"Fetch Size" });
        }

        // Get the non delete mode.
        if (!ZYPCommonFunc.hasValue(getUserVariable5())) {
            nonDeleteFlg = ZYPConstant.FLG_OFF_N;
        } else {
            nonDeleteFlg = getUserVariable5();
        }

        // Get the Days.
        // If an error occurs, throw Exception.
        dlyDataTrmOfKeep = ZYPCodeDataUtil.getNumConstValue(CONST_KEY_DLY, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(dlyDataTrmOfKeep)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {"Days" });
        }
        if (BigDecimal.ZERO.compareTo(dlyDataTrmOfKeep) >= 0) {
            throw new S21AbendException(MSG_ID_NLCM0083E, new String[] {"Days" });
        }

        // Get the Month.
        // If an error occurs, throw Exception.
        mlyDataTrmOfKeep = ZYPCodeDataUtil.getNumConstValue(CONST_KEY_MLY, glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(mlyDataTrmOfKeep)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {"Month" });
        }
        if (BigDecimal.ZERO.compareTo(mlyDataTrmOfKeep) >= 0) {
            throw new S21AbendException(MSG_ID_NLCM0083E, new String[] {"Month" });
        }
    }

    /**
     * <pre>
     * Execute the daily processing.
     *  1. Delete Inventory Snapshot for Daily.
     *  2. Create Inventory Snapshot.
     * </pre>
     */
    private void executeDlyProc() {

        S21InfoLogOutput.println("executeDlyProc start.");

        // Get the Delete date for Daily.
        dlyDelDt = getDelDtForDly();

        // Delete the Inventory Snapshot for Daily.
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locTpCd", locTpCd);
        params.put("delDtDly", dlyDelDt);
        params.put("batProcDt", procDt);
        deleteSnapshot("getDelSnapshotDly", params);

        // Create the Inventory Snapshot.
        params.clear();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locTpCd", locTpCd);
        createSnapshot("getCopyInvty", params);

        S21InfoLogOutput.println("executeDlyProc end.");
    }

    /**
     * <pre>
     * Execute the monthly processing.
     *  1. Delete Inventory Snapshot for Monthly.
     *  2. Update Inventory Snapshot.
     * </pre>
     */
    private void executeMlyProc() {

        S21InfoLogOutput.println("executeMlyProc start.");

        // Get the Monthly Closed Date.
        mlyCloDt = getMlyCloDt();

        // Get the Delete date for Monthly.
        mlyDelDt = getDelDtForMly();

        // Delete the Inventory Snapshot for Monthly.
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locTpCd", locTpCd);
        params.put("delDtMly", mlyDelDt);
        deleteSnapshot("getDelSnapshotMly", params);

        // Clear the Inventory Snapshot.
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locTpCd", locTpCd);
        params.put("mlyCloDt", mlyCloDt);
        params.put("snapShotDt", procDt);
        clearSnapshot("getClSnapshot", params);

        // Update the Inventory Snapshot.
        params.clear();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locTpCd", locTpCd);
        params.put("snapShotDt", procDt);
        updateSnapshot("getUpdSnapshot", params);

        S21InfoLogOutput.println("executeMlyProc end.");

    }

    /**
     * <pre>
     * Execute the daily and monthly processing.
     *  1. Delete Inventory Snapshot for Daily and Monthly. 
     *  2. Create Inventory Snapshot.
     * </pre>
     */
    private void executeDlyMlyProc() {

        S21InfoLogOutput.println("executeDlyMlyProc start.");

        // Get the Monthly Closed Date.
        mlyCloDt = getMlyCloDt();

        // Get the Delete date for Daily.
        dlyDelDt = getDelDtForDly();

        // Get the Delete date for Monthly.
        mlyDelDt = getDelDtForMly();

        // Delete the Inventory Snapshot for Daily and Monthly.
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locTpCd", locTpCd);
        params.put("delDtDly", dlyDelDt);
        params.put("delDtMly", mlyDelDt);
        params.put("batProcDt", procDt);
        deleteSnapshot("getDelSnapshotDlyMly", params);

        // Clear the Inventory Snapshot.
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locTpCd", locTpCd);
        params.put("mlyCloDt", mlyCloDt);
        params.put("snapShotDt", procDt);
        clearSnapshot("getClSnapshot", params);

        // Create the Inventory Snapshot.
        params.clear();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locTpCd", locTpCd);
        createSnapshot("getCopyInvty", params);

        S21InfoLogOutput.println("executeDlyMlyProc end.");
    }

    /**
     * <pre>
     * Execute the daily and monthly processing.
     *  1. Delete Inventory Snapshot for Daily and Monthly. 
     * </pre>
     */
    private void executeDelete() {

        S21InfoLogOutput.println("executeDelete start.");

        nonDeleteFlg = ZYPConstant.FLG_OFF_N;

        // Get the Monthly Closed Date.
        mlyCloDt = getMlyCloDt();

        // Get the Delete date for Daily.
        dlyDelDt = getDelDtForDly();

        // Get the Delete date for Monthly.
        mlyDelDt = getDelDtForMly();

        // Delete the Inventory Snapshot for Daily and Monthly.
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("locTpCd", locTpCd);
        params.put("delDtDly", dlyDelDt);
        params.put("delDtMly", mlyDelDt);
        params.put("batProcDt", procDt);
        deleteSnapshot("getDelSnapshotDlyMly", params);

        S21InfoLogOutput.println("executeDelete end.");
    }
    /**
     * <pre>
     * Delete the Inventory Snapshot. 
     * </ptre>
     * @param key    SSM key.
     * @param params SSM parameter.
     */
    private void deleteSnapshot(String key, Map<String, String> params) {

        S21InfoLogOutput.println("deleteSnapshot start.");

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmtSales = null;
        ResultSet resSet = null;

        if (ZYPConstant.FLG_ON_Y.equals(nonDeleteFlg)) {
            S21InfoLogOutput.println("deleteSnapshot end.(skip)");
            return;
        }

        try {

            List<INVTY_SNAP_SHOTTMsg> snapShotList = new ArrayList<INVTY_SNAP_SHOTTMsg>();
            // Set execParam in order to omit memory .
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(fetchSize);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // Execute search for delete query.
            stmtSales = ssmLLClient.createPreparedStatement(key, params, execParam);
            resSet = stmtSales.executeQuery();

            int cnt = 0;
            int execCnt = 0;
            while (resSet.next()) {

                // Delete the Inventory Snapshot.
                INVTY_SNAP_SHOTTMsg snapShotTMsg = new INVTY_SNAP_SHOTTMsg();
                S21ResultSetMapper.map(resSet, snapShotTMsg);
                snapShotList.add(snapShotTMsg);
                cnt++;

                // commit
                if (cnt == commitCount) {
                    S21InfoLogOutput.println("deleteSnapshot beofre commit.");
                    execCnt = S21FastTBLAccessor.removePhysical((INVTY_SNAP_SHOTTMsg[]) snapShotList.toArray(new INVTY_SNAP_SHOTTMsg[0]));
                    if (execCnt != cnt) {
                        throw new S21AbendException(MSG_ID_NLCM0053E);
                    }
                    commit();
                    deleteCommitCount += cnt;
                    S21InfoLogOutput.println("deleteSnapshot commit. cuont=" + deleteCommitCount);
                    cnt = 0;
                    snapShotList.clear();
                }
            }
            if (cnt > 0) {
                S21InfoLogOutput.println("deleteSnapshot beofre commit.");
                execCnt = S21FastTBLAccessor.removePhysical((INVTY_SNAP_SHOTTMsg[]) snapShotList.toArray(new INVTY_SNAP_SHOTTMsg[0]));
                if (execCnt != cnt) {
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                deleteCommitCount += cnt;
                S21InfoLogOutput.println("deleteSnapshot commit. cuont=" + deleteCommitCount);
                snapShotList = null;
            }
            totalCommitCount += deleteCommitCount;

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSales, resSet);
            S21InfoLogOutput.println("deleteSnapshot end.");
        }
    }

    /**
     * <pre>
     * Create the Inventory Snapshot. 
     * </ptre>
     * @param key    SSM key.
     * @param params SSM parameter.
     */

    private void createSnapshot(String key, Map<String, String> params) {

        S21InfoLogOutput.println("createSnapshot start.");

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmtSales = null;
        ResultSet resSet = null;
//        BigDecimal snapShotPk = null;

        try {

            List<INVTY_SNAP_SHOTTMsg> snapShotList = new ArrayList<INVTY_SNAP_SHOTTMsg>();
            // Set execParam in order to omit memory .
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(fetchSize);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // Execute search for create query.
            stmtSales = ssmLLClient.createPreparedStatement(key, params, execParam);
            resSet = stmtSales.executeQuery();

            int cnt = 0;
            int execCnt = 0;
            while (resSet.next()) {
//            snapShotPk = ZYPOracleSeqAccessor.getNumberBigDecimal(SNAP_SHOPT_SQ_KEY);
//            if (!ZYPCommonFunc.hasValue(snapShotPk)) {
//                throw new S21AbendException(MSG_ID_NLCM0053E);
//            }

                // Create the Inventory Snapshot.
                INVTY_SNAP_SHOTTMsg snapShotTMsg = new INVTY_SNAP_SHOTTMsg();
                S21ResultSetMapper.map(resSet, snapShotTMsg);
//                snapShotTMsg.invtySnapShotPk.setValue(snapShotPk);
                ZYPEZDItemValueSetter.setValue(snapShotTMsg.invtySnapShotDt, procDt);
                ZYPEZDItemValueSetter.setValue(snapShotTMsg.mlyCloYrMth, mlyCloDt);
                snapShotList.add(snapShotTMsg);
                cnt++;

                // commit
                if (cnt == commitCount) {
                    S21InfoLogOutput.println("createSnapshot beofre commit.");
                    execCnt = S21FastTBLAccessor.insert((INVTY_SNAP_SHOTTMsg[]) snapShotList.toArray(new INVTY_SNAP_SHOTTMsg[0]));
                    if (execCnt != cnt) {
                        EZDDebugOutput.println(CST_DEBUG_MSG_LVL, "execCnt:" + execCnt, this);
                        EZDDebugOutput.println(CST_DEBUG_MSG_LVL, "cnt:" + cnt, this);
                        throw new S21AbendException(MSG_ID_NLCM0053E);
                    }
                    commit();
                    createCommitCount += cnt;
                    S21InfoLogOutput.println("createSnapshot commit. cuont=" + createCommitCount);
                    cnt = 0;
                    snapShotList.clear();
                }
            }
            if (cnt > 0) {
                S21InfoLogOutput.println("createSnapshot beofre commit.");
                execCnt = S21FastTBLAccessor.insert((INVTY_SNAP_SHOTTMsg[]) snapShotList.toArray(new INVTY_SNAP_SHOTTMsg[0]));
                if (execCnt != cnt) {
                    EZDDebugOutput.println(CST_DEBUG_MSG_LVL, "execCnt:" + execCnt, this);
                    EZDDebugOutput.println(CST_DEBUG_MSG_LVL, "cnt:" + cnt, this);
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                createCommitCount += cnt;
                S21InfoLogOutput.println("createSnapshot commit. cuont=" + createCommitCount);
                snapShotList = null;
            }
            totalCommitCount += createCommitCount;

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSales, resSet);
            S21InfoLogOutput.println("createSnapshot end.");
        }
    }

    /**
     * <pre>
     * Clear the Monthly Closed Date for Inventory Snapshot. 
     * </ptre>
     * @param key    SSM key.
     * @param params SSM parameter.
     */
    private void clearSnapshot(String key, Map<String, String> params) {

        S21InfoLogOutput.println("clearSnapshot start.");

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmtSales = null;
        ResultSet resSet = null;

        try {

            List<INVTY_SNAP_SHOTTMsg> snapShotList = new ArrayList<INVTY_SNAP_SHOTTMsg>();
            // Set execParam in order to omit memory .
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(fetchSize);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // Execute search for update query.
            stmtSales = ssmLLClient.createPreparedStatement(key, params, execParam);
            resSet = stmtSales.executeQuery();

            int cnt = 0;
            int execCnt = 0;
            while (resSet.next()) {

                // Update the Inventory Snapshot.
                INVTY_SNAP_SHOTTMsg snapShotTMsg = new INVTY_SNAP_SHOTTMsg();
                S21ResultSetMapper.map(resSet, snapShotTMsg);
                snapShotTMsg.mlyCloYrMth.clear();
                snapShotList.add(snapShotTMsg);

                cnt++;

                // commit
                if (cnt == commitCount) {
                    S21InfoLogOutput.println("clearSnapshot beofre commit.");
                    execCnt = S21FastTBLAccessor.update((INVTY_SNAP_SHOTTMsg[]) snapShotList.toArray(new INVTY_SNAP_SHOTTMsg[0]));
                    // not normal end
                    if (execCnt != cnt) {
                        throw new S21AbendException(MSG_ID_NLCM0053E);
                    }
                    commit();
                    updateCommitCount += cnt;
                    S21InfoLogOutput.println("clearSnapshot commit. cuont=" + updateCommitCount);
                    cnt = 0;
                    snapShotList.clear();
                }
            }
            if (cnt > 0) {
                S21InfoLogOutput.println("clearSnapshot beofre commit.");
                execCnt = S21FastTBLAccessor.update((INVTY_SNAP_SHOTTMsg[]) snapShotList.toArray(new INVTY_SNAP_SHOTTMsg[0]));
                if (execCnt != cnt) {
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                updateCommitCount += cnt;
                S21InfoLogOutput.println("clearSnapshot commit. cuont=" + updateCommitCount);
                snapShotList = null;
            }
            totalCommitCount += updateCommitCount;

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSales, resSet);
            S21InfoLogOutput.println("clearSnapshot end.");
        }
    }

    /**
     * <pre>
     * Update the Inventory Snapshot.
     * </ptre>
     * @param key    SSM key.
     * @param params SSM parameter.
     */
    private void updateSnapshot(String key, Map<String, String> params) {

        S21InfoLogOutput.println("updateSnapshot start.");

        S21SsmExecutionParameter execParam = null;
        PreparedStatement stmtSales = null;
        ResultSet resSet = null;

        try {

            List<INVTY_SNAP_SHOTTMsg> snapShotList = new ArrayList<INVTY_SNAP_SHOTTMsg>();
            // Set execParam in order to omit memory .
            execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(fetchSize);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            // Execute search for update query.
            stmtSales = ssmLLClient.createPreparedStatement(key, params, execParam);
            resSet = stmtSales.executeQuery();

            int cnt = 0;
            int execCnt = 0;
            while (resSet.next()) {

                // Update the Inventory Snapshot.
                INVTY_SNAP_SHOTTMsg snapShotTMsg = new INVTY_SNAP_SHOTTMsg();
                S21ResultSetMapper.map(resSet, snapShotTMsg);
                ZYPEZDItemValueSetter.setValue(snapShotTMsg.mlyCloYrMth, mlyCloDt);
                snapShotList.add(snapShotTMsg);

                cnt++;

                // commit
                if (cnt == commitCount) {
                    S21InfoLogOutput.println("updateSnapshot beofre commit.");
                    execCnt = S21FastTBLAccessor.update((INVTY_SNAP_SHOTTMsg[]) snapShotList.toArray(new INVTY_SNAP_SHOTTMsg[0]));
                    // not normal end
                    if (execCnt != cnt) {
                        throw new S21AbendException(MSG_ID_NLCM0053E);
                     }
                    commit();
                    updateCommitCount += cnt;
                    S21InfoLogOutput.println("updateSnapshot commit. cuont=" + updateCommitCount);
                    cnt = 0;
                    snapShotList.clear();
                }
            }
            if (cnt > 0) {
                S21InfoLogOutput.println("updateSnapshot beofre commit.");
                execCnt = S21FastTBLAccessor.update((INVTY_SNAP_SHOTTMsg[]) snapShotList.toArray(new INVTY_SNAP_SHOTTMsg[0]));
                // not normal end
                if (execCnt != cnt) {
                    throw new S21AbendException(MSG_ID_NLCM0053E);
                }
                commit();
                updateCommitCount += cnt;
                S21InfoLogOutput.println("updateSnapshot commit. cuont=" + updateCommitCount);
                snapShotList = null;
            }
            totalCommitCount += updateCommitCount;

        } catch (SQLException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSales, resSet);
            S21InfoLogOutput.println("updateSnapshot end.");
        }
    }

    /**
     * <pre>
     * Check the code vallue.
     * This method is compare to code table.
     * If an mismatch occurs, return false.
     * </pre>
     * 
     * @param cdTblCls Code table constant class
     * @param compVal Compare value
     * @return Result (true:Match, false:Mismatch)
     */
    private boolean checkCdVal(Class cdTblCls, String compVal) {

        try {

            // Get the code table constant fields.
            Field[] cdTblClsFields = cdTblCls.getFields();

            // Compare to code table value.
            String cdVal = null;
            for (int i = 0; i < cdTblClsFields.length; i++) {
                cdVal = (String) cdTblClsFields[i].get(cdTblCls);

                // If an match, return true.
                if (cdVal.equals(compVal)) {
                    return true;
                }
            }

            // If an error occurs, return false.
        } catch (IllegalAccessException e) {
            return false;
        }

        return false;
    }

    /**
     * <pre>
     * Get the Monthly close date.
     * Process date convert of Monthly close date.
     * </pre>
     * 
     * @return Monthly close date
     */
    private String getMlyCloDt() {
        return ZYPDateUtil.convertFormat(procDt, PROC_DT_PTRN, "yyyyMM", null);
    }

    /**
     * <pre>
     * Get the delete date for Daily.
     * Calculate the Delete date for daily, from Process date and Days.
     * </pre>
     * 
     * @return Delete date for Daily
     */
    private String getDelDtForDly() {
        String delDtForDly = null;

        // Delete date for Daily = Process date - Days
        try {
            Calendar clndr = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(PROC_DT_PTRN);
            clndr.setTime(sdf.parse(procDt));
            clndr.add(Calendar.DATE, dlyDataTrmOfKeep.intValue() * -1);
            delDtForDly = sdf.format(clndr.getTime());
        } catch (ParseException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            throw new S21AbendException(MSG_ID_ZZM9010E, new String[] {"Days" });
        }

        return delDtForDly;
    }

    /**
     * <pre>
     * Get the Delete date for Monthly.
     * Calculate the Delete date for Monthly, from Process date and Month.
     * </pre>
     * 
     * @return Delete date for Monthly
     */
    private String getDelDtForMly() {
        String delDtForMly = null;

        // Delete date for Monthly = Process date - (Month + 1)
        try {
            delDtForMly = getMlyCloDt();
            Calendar clndr = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            clndr.setTime(sdf.parse(delDtForMly));
            clndr.add(Calendar.MONTH, mlyDataTrmOfKeep.add(BigDecimal.ONE).negate().intValue());
            delDtForMly = sdf.format(clndr.getTime());
        } catch (ParseException e) {
            EZDDebugOutput.println(CST_DEBUG_MSG_LVL, e.getMessage(), this);
            throw new S21AbendException(MSG_ID_ZZM9010E, new String[] {"Month" });
        }

        return delDtForMly;
    }
}
