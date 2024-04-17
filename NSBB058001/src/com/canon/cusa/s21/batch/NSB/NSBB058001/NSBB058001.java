/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB058001;

import static com.canon.cusa.s21.batch.NSB.NSBB058001.constant.NSBB058001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.NSBI0580_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Finds Interface Option Info Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 07/13/2016   Hitachi         T.Mizuki        Create          N/A
 * 10/25/2016   Hitachi         T.Mizuki        Update          QC#14988
 * 01/13/2017   Hitachi         K.Kitachi       Update          QC#17065
 * 03/03/2023   CITS            L.Mandanas      Update          QC#61067
 * 07/06/2023   CITS            R.Jin           Update          QC#61067
 *</pre>
 */
public class NSBB058001 extends S21BatchMain {

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** FINDS_CLO_FSR_EVENT_CD */
    private String findsCloFsrEventCds = null;

    /** FINDS_CLO_FSR_EVENT_CD */
    private String[] findsCloFsrEventCd = null;

    /** FINDS_GLBL_CMPY_CD */
    private String findsGlblCmpyCd = null;

    /** Total Commit Count */
    private int totalCommitCount;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Interface Id */
    private String intfcId = null;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Unit ID */
    private int unitId;

    /** Commit Number */
    private int commitNumber;

    /** DS_BIZ_PROC_LOG */
    private DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg = null;

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // START 2023/03/03 L.Mandanas [QC#61067, ADD]
    /** Service Machine Master Status Code List */
    private List<String> stsCdList = null;

    /** Batch User Variable */
    private String usrVar1 = "";

    /** CMPS_GLBL_CMPY_CD */
    private String cmpsGlblCmpyCd = null;
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]

    // START 2023/07/06 R.Jin [QC#61067, ADD]
    /** COMPASS Sender Company Code */
    private String cmpsSndrCmpyCd = null;

    /** CMPS_CRSSVC_EXCL_TECH_CD */
    private String crsSvcTechCd = null;

    /** CMPS_CRSSVC_EXCL_LOB_CD */
    private String crsSvcLobCd = null;

    /** CMPS_DLR_SVC_CD */
    private String cmpsDelerTp = null;

    /** FINDS_VISIT_CTRY_CD */
    private String findsCtryCd = null;

    /** CMPS_EXTR_DAYS */
    private BigDecimal cmps_Extr_Days;
    // END 2023/07/06 R.Jin [QC#61067, ADD]

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NSBB058001().executeBatch(NSBB058001.class.getSimpleName());

    }

    @Override
    protected void termRoutine() {

        setTermState(this.termCd, this.normalCount, 0);

    }

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {GLBL_CMPY_CD });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NSZM0392E, new String[] {SLS_DT });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {INTERFACE_ID });
        }

        // Get System Timestamp
        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

        // Get Var Char Const(FINDS_CLO_FSR_EVENT_CD)
        this.findsCloFsrEventCds = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_CLO_FSR_EVENT_CD);
        if (!ZYPCommonFunc.hasValue(this.findsCloFsrEventCds)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_FINDS_CLO_FSR_EVENT_CD });
        }
        this.findsCloFsrEventCd = findsCloFsrEventCds.split(STR_COMMA);

        // Get Var Char Const(FINDS_GLBL_CMPY_CD)
        this.findsGlblCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_GLBL_CMPY_CD);
        if (!ZYPCommonFunc.hasValue(this.findsGlblCmpyCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_FINDS_GLBL_CMPY_CD });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // START 2023/03/03 L.Mandanas [QC#61067, ADD]
        // Get Service Machine Master Status Code
        String stsCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_CMPS_SVC_MACH_MSTR_STS_CD, this.glblCmpyCd);
        if (hasValue(stsCd)) {
            String[] tmpList = stsCd.split(",");
            this.stsCdList = Arrays.asList(tmpList);
        } else {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_SVC_MACH_MSTR_STS_CD });
        }

        // Get Batch User Variable
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(NSBM0071E, new String[] {"User Variable1" });
        }

        // Get Var Char Const(FINDS_GLBL_CMPY_CD)
        this.cmpsGlblCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD);
        if (!ZYPCommonFunc.hasValue(this.cmpsGlblCmpyCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD });
        }
        // END 2023/03/03 L.Mandanas [QC#61067, ADD]

        // START 2023/07/06 R.Jin [QC#61067, ADD]
        // Get Var Char Const(CMPS_SNDR_CMPY_CD)
        this.cmpsSndrCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_SNDR_CMPY_CD);
        if (!ZYPCommonFunc.hasValue(this.cmpsSndrCmpyCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_SNDR_CMPY_CD });
        }

        // Get Var Char Const(CMPS_CRSSVC_EXCL_TECH_CD)
        this.crsSvcTechCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_TECH_CD);
        if (!ZYPCommonFunc.hasValue(this.crsSvcTechCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_TECH_CD });
        }

        // Get Var Char Const(CMPS_CRSSVC_EXCL_LOB_CD)
        this.crsSvcLobCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_LOB_CD);
        if (!ZYPCommonFunc.hasValue(this.crsSvcLobCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_LOB_CD });
        }

        // Get Var Char Const(CMPS_DLR_SVC_CD)
        this.cmpsDelerTp = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_DLR_SVC_CD);
        if (!ZYPCommonFunc.hasValue(this.cmpsDelerTp)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_DLR_SVC_CD });
        }

        // Get Var Char Const(FINDS_VISIT_CTRY_CD)
        this.findsCtryCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_VISIT_CTRY_CD);
        if (!ZYPCommonFunc.hasValue(this.findsCtryCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_FINDS_VISIT_CTRY_CD });
        }

        // Get Num Const(CMPS_EXTR_DAYS)
        this.cmps_Extr_Days = getNumConst(NUM_CONST_KEY_CMPS_EXTR_DAYS);
        if (!ZYPCommonFunc.hasValue(this.cmps_Extr_Days)) {
            throw new S21AbendException(USEM0052E, new String[] {this.glblCmpyCd,NUM_CONST_KEY_CMPS_EXTR_DAYS });
        }
        // END 2023/07/06 R.Jin [QC#61067, ADD]

        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = this.trxAccess.getNextTransactionId();
        this.unitId = 1;
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        // Get DsBizProcLog
        this.dsBizProcLogTMsg = getDsBizProcLog();

        // insert interface data
        doProcess();

    }

    /**
     * 
     */
    private void doProcess() {
        List<NSBI0580_01TMsg> inTMsgList = new ArrayList<NSBI0580_01TMsg>();
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            // START 2023/03/03 L.Mandanas [QC#61067, ADD]
            // Check if monthly batch can be executed
            if (VAR_JOB_MONTHLY.equals(this.usrVar1) && !isMonthly()) {
                return;
            }
            // END 2023/03/03 L.Mandanas [QC#61067, ADD]

            // Search Target Serial Data
            stmt = this.ssmLLClient.createPreparedStatement("getConfigInfo", setParam(), execParam);
            rsSet = stmt.executeQuery();

            int commitCount = 0;
            while (rsSet.next()) {

                inTMsgList.add(setNSBI0580_01(rsSet));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertInterfaceData(inTMsgList);
                    this.totalCommitCount += commitCount;
                    inTMsgList.clear();
                    commitCount = 0;
                }
                this.normalCount++;
                this.unitId++;
            }

            if (this.normalCount != this.totalCommitCount) {
                commitCount = insertInterfaceData(inTMsgList);
                this.totalCommitCount += commitCount;
            }

            // mod start 2016/10/25 CSA QC#14988
            if (this.totalCommitCount != 0) {
                // insert INTERFACE_TRANSACTION
                trxAccess.createIntegrationRecordForBatch(this.intfcId, this.trxId);
            }
            // mod end 2016/10/25 CSA QC#14988
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

    private Map<String, Object> setParam() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcDisptEventCd", this.findsCloFsrEventCd);
        if (this.dsBizProcLogTMsg != null) {
            ssmParam.put("lastProcTs", this.dsBizProcLogTMsg.dsBizLastProcTs.getValue());
        }
        ssmParam.put("currentSystemTs", this.currentSystemTs);
        // START 2023/03/03 L.Mandanas [QC#61067, ADD]
        ssmParam.put("svcMachTpMach", SVC_MACH_TP.MACHINE);
        ssmParam.put("svcMachTpAcc", SVC_MACH_TP.ACCESSORY);
        ssmParam.put("dateFormat", CRAT_DT_FORMAT);
        ssmParam.put("timeFormat", CRAT_TM_FORMAT);
        ssmParam.put("procStsCd", IF_PROC_STS_U);

        if (VAR_JOB_MONTHLY.equals(this.usrVar1)) {
            // Monthly
            ssmParam.put("isMonthly", "1");
            ssmParam.put("stsCdList", this.stsCdList);
        } else {
            // Daily
            ssmParam.put("isMonthly", null);
            ssmParam.put("svcTaskStsCd", SVC_TASK_STS.CLOSED);
            ssmParam.put("fsrStsCd", SVC_TASK_STS.CLOSED);
            // START 2023/07/06 R.Jin [QC#61067, MOD]
//            ssmParam.put("trgtDt", getTargetDate());
            ssmParam.put("slsDt", this.slsDt);
            ssmParam.put("extrDys", this.cmps_Extr_Days);
            // END 2023/07/06 R.Jin [QC#61067, MOD]
            ssmParam.put("fromTm", FROM_TM);
            ssmParam.put("toTm", TO_TM);
        }
        // END 2023/03/03 L.Mandanas [QC#61067, ADD]
        // START 2023/07/06 R.Jin [QC#61067, ADD]
        ssmParam.put("sndrCmpyCd", this.cmpsSndrCmpyCd);
        ssmParam.put("crsSvcTechCd", this.crsSvcTechCd);
        ssmParam.put("crsSvcLobCd", this.crsSvcLobCd);
        ssmParam.put("cmpsDelerTp", this.cmpsDelerTp);
        ssmParam.put("findsCtryCd", this.findsCtryCd);
        // END 2023/07/06 R.Jin [QC#61067, ADD]
        return ssmParam;
    }

    private String getVarCharConst(String varCharConstKey) {
        return ZYPCodeDataUtil.getVarCharConstValue(varCharConstKey, this.glblCmpyCd);

    }

    // START 2023/07/06 R.Jin [QC#61067, ADD]
    private BigDecimal getNumConst(String numConstKey) {
        return ZYPCodeDataUtil.getNumConstValue(numConstKey, this.glblCmpyCd);
    }
    // END 2023/07/06 R.Jin [QC#61067, ADD]

    private NSBI0580_01TMsg setNSBI0580_01(ResultSet fsrData) {

        NSBI0580_01TMsg setParamMsg = new NSBI0580_01TMsg();
        try {
            setValue(setParamMsg.interfaceId, this.intfcId);
            setValue(setParamMsg.transactionId, trxId);
            setValue(setParamMsg.segmentId, BigDecimal.ONE);
            setValue(setParamMsg.unitId, BigDecimal.valueOf(this.unitId));
            setValue(setParamMsg.seqNumber, BigDecimal.ONE);
            setValue(setParamMsg.serNum, fsrData.getString("SER_NUM"));
            setValue(setParamMsg.childSerNum, fsrData.getString("CHILD_SER_NUM"));
            setValue(setParamMsg.istlDt, fsrData.getString("ISTL_DT"));
            // START 2017/01/13 K.Kitachi [QC#17065, MOD]
//            setValue(setParamMsg.mdseDescShortTxt, fsrData.getString("MDSE_DESC_SHORT_TXT"));
            setValue(setParamMsg.mdseDescShortTxt, removeDoubleQuote(fsrData.getString("MDSE_DESC_SHORT_TXT")));
            // END 2017/01/13 K.Kitachi [QC#17065, MOD]
            setValue(setParamMsg.mdseCd, fsrData.getString("MDSE_CD"));
            // START 2023/03/03 L.Mandanas [QC#61067, MOD]
            //setValue(setParamMsg.glblCmpyCd_01, findsGlblCmpyCd);
            //setValue(setParamMsg.cratDt, currentSystemTs.substring(0, NUM_8));
            //setValue(setParamMsg.cratTm, currentSystemTs.substring(NUM_8, NUM_14));
            // START 2023/07/06 R.Jin [QC#61067, MOD]
//            setValue(setParamMsg.glblCmpyCd_01, this.cmpsGlblCmpyCd);
            setValue(setParamMsg.glblCmpyCd_01, fsrData.getString("GLBL_CMPY_CD_01"));
            // END 2023/07/06 R.Jin [QC#61067, MOD]
            setValue(setParamMsg.cratDt, fsrData.getString("CRAT_DT"));
            setValue(setParamMsg.cratTm, fsrData.getString("CRAT_TM"));
            // START 2023/03/03 L.Mandanas [QC#61067, MOD]
            setValue(setParamMsg.ifProcStsCd, IF_PROC_STS_U);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return setParamMsg;
    }

    private int insertInterfaceData(List<NSBI0580_01TMsg> inMsgLst) {
        NSBI0580_01TMsg[] inMsgArray;
        inMsgArray = new NSBI0580_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {

            throw new S21AbendException(NSBM0164E, new String[] {inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
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

    // START 2017/01/13 K.Kitachi [QC#17065, ADD]
    private String removeDoubleQuote(String inVal) {
        if (!hasValue(inVal)) {
            return inVal;
        }
        return ZYPCommonFunc.replaceAll(inVal, "\"", "");
    }
    // END 2017/01/13 K.Kitachi [QC#17065, ADD]

    // START 2023/03/03 L.Mandanas [QC#61067, ADD]
    /**
     * Get Previous date.
     * @return String
     */
    private String getTargetDate() {
        return ZYPDateUtil.addDays(this.slsDt, -1);
    }

    /**
     * Check if first Thursday for monthly batch run.
     * @return boolean
     */
    private boolean isMonthly() {
        double date = Double.parseDouble(this.slsDt.substring(INT_6));
        int day = ZYPDateUtil.getDayOfWeek(this.slsDt);
        if ((date / INT_7 <= 1) && day == Calendar.THURSDAY) {
            // Run monthly batches only on the first Thursday of the month
            return true;
        } else {
            return false;
        }
    }
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]
}
