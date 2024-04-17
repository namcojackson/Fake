/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB059001;

import static com.canon.cusa.s21.batch.NSB.NSBB059001.constant.NSBB059001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDItemAttrDefines;
import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.NSBI0590_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;

/**
 * <pre>
 * Finds Interface Serial No. Info Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 07/20/2016   Hitachi         Y.Osawa         Create          N/A
 * 10/25/2016   Hitachi         T.Mizuki        Update          QC#14988
 * 01/13/2017   Hitachi         K.Kitachi       Update          QC#17065
 * 03/03/2023   CITS            L.Mandanas      Update          QC#61067
 * 07/06/2023   CITS            R.Jin           Update          QC#61067
 *</pre>
 */
public class NSBB059001 extends S21BatchMain {

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** FINDS_WTY_NM */
    private String findsWtyNm = null;

    /** FINDS_SVC_NM */
    private String findsSvcNm = null;

    /** FINDS_CLO_FSR_EVENT_CD */
    private String findsCloFsrEventCd = null;

    /** FINDS_ORG_CMPY_CD */
    private String findsOrgCmpyCd = null;

    /** FINDS_GLBL_CMPY_CD */
    private String findsGlblCmpyCd = null;

    /** FINDS_EXCL_CONTR_DTL_STS */
    private String findsExclContrDtlStsCd = null;

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** Process Time Stamp */
    private String procTs;

    /** Process Date */
    private String procDt;

    /** Process Time */
    private String procTm;

    /** Interface Id */
    private String intfcId = null;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Unit ID */
    private int unitId = 0;

    /** Commit Number */
    private int commitNumber;

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** DS_BIZ_PROC_LOG */
    private DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // START 2023/03/03 L.Mandanas [QC#61067, ADD]
    /** FINDS_GLBL_CMPY_CD */
    private String cmpsGlblCmpyCd = null;

    /** Process Mode */
    private String processMode = null;

    /** Compass Service Machine Master Status Code */
    private String compassSvcMachMstrStsCd = null;

    /** Split Service Program */
    private String splitSvcPgm = null;
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]

    // START 2023/07/06 R.Jin [QC#61067, ADD]
    /** COMPASS Sender Company Code */
    private String cmpsSndrCmpyCd = null;

    /** CMPS_CRSSVC_EXCL_TECH_CD */
    private String crsSvcTechCd = null;

    /** CMPS_CRSSVC_EXCL_LOB_CD */
    private String crsSvcLobCd = null;

    /** CMPS_CTRY_CD */
    private String cmpsCtryCd = null;

    /** CMPS_DLR_SVC_CD */
    private String cmpsDelerTp = null;

    /** FINDS_VISIT_CTRY_CD */
    private String findsCtryCd = null;

    // START 2023/08/21 R.Jin [QC#61067, DEL]
//    /** CMPS_CONTR_DTL_STS_CD */
//    private String contrDtlSts = null;
    // END 2023/08/21 R.Jin [QC#61067, DEL]

    /** CMPS_SMM_STS_CD */
    private String smmSts = null;

    /** CMPS_EXTR_DAYS */
    private BigDecimal cmps_Extr_Days;
    // END 2023/07/06 R.Jin [QC#61067, ADD]

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NSBB059001().executeBatch(NSBB059001.class.getSimpleName());
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {GLBL_CMPY_CD });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        if (!hasValue(this.slsDt)) {
            throw new S21AbendException(NSBM0071E, new String[] {SLS_DT });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {INTERFACE_ID });
        }

        // Get System Timestamp
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        this.procDt = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);
        this.procTm = ZYPDateUtil.getCurrentSystemTime(TIME_FORMAT);

        // Get Var Char Const
        this.findsWtyNm = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_WTY_NM);
        this.findsSvcNm = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_SVC_NM);
        this.findsCloFsrEventCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_CLO_FSR_EVENT_CD);
        this.findsOrgCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_ORG_CMPY_CD);
        this.findsGlblCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_GLBL_CMPY_CD);
        this.findsExclContrDtlStsCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_EXCL_CONTR_DTL_STS);

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = this.trxAccess.getNextTransactionId();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // START 2023/03/03 L.Mandanas [QC#61067, ADD]
        // Get Var Char Const(FINDS_GLBL_CMPY_CD)
        this.cmpsGlblCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD);
        if (!ZYPCommonFunc.hasValue(this.cmpsGlblCmpyCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD });
        }

        // Get Variable Character Constant
        this.compassSvcMachMstrStsCd = getVarCharConst(VAR_CHAR_CONST_KEY_COMPASS_SVC_MACH_MSTR_STS_CD);
        if (!ZYPCommonFunc.hasValue(this.compassSvcMachMstrStsCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_COMPASS_SVC_MACH_MSTR_STS_CD });
        }

        this.splitSvcPgm = getVarCharConst(VAR_CHAR_CONST_KEY_SPLIT_SVC_PGM);
        if (!ZYPCommonFunc.hasValue(this.splitSvcPgm)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_SPLIT_SVC_PGM });
        }

        // Get User Variable
        this.processMode = getUserVariable1();
        if (!hasValue(this.processMode)) {
            throw new S21AbendException(NSBM0071E, new String[] {"User Variable1" });
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

        
        // Get Var Char Const(CMPS_CTRY_CD)
        this.cmpsCtryCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_CTRY_CD);
        if (!ZYPCommonFunc.hasValue(this.cmpsCtryCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_CTRY_CD });
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

        // START 2023/08/21 R.Jin [QC#61067, DEL]
        // Get Var Char Const(CMPS_CONTR_DTL_STS_CD)
//        this.contrDtlSts = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_CONTR_DTL_STS_CD);
//        if (!ZYPCommonFunc.hasValue(this.contrDtlSts)) {
//            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_CONTR_DTL_STS_CD });
//        }
        // START 2023/08/21 R.Jin [QC#61067, DEL]

        // Get Var Char Const(CMPS_SMM_STS_CD)
        this.smmSts = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_SMM_STS_CD);
        if (!ZYPCommonFunc.hasValue(this.smmSts)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_SMM_STS_CD });
        }

        // Get Num Const(CMPS_EXTR_DAYS)
        this.cmps_Extr_Days = getNumConst(NUM_CONST_KEY_CMPS_EXTR_DAYS);
        if (!ZYPCommonFunc.hasValue(this.cmps_Extr_Days)) {
            throw new S21AbendException(USEM0052E, new String[] {this.glblCmpyCd,NUM_CONST_KEY_CMPS_EXTR_DAYS });
        }
        // END 2023/07/06 R.Jin [QC#61067, ADD]
    }

    @Override
    protected void mainRoutine() {
        // get DsBizProcLog
        this.dsBizProcLogTMsg = getDsBizProcLog();

        // insert interface data
        // START 2023/03/03 L.Mandanas [QC#61067, MOD]
        //insertInterfaceData();
        if (this.processMode.equals(DAILY_MODE) || (this.processMode.equals(MONTHLY_MODE) && isMonthlyProcessDate(this.slsDt))) {
            insertInterfaceData();
        }
        // END 2023/03/03 L.Mandanas [QC#61067, MOD]

        // update DsBizProcLog
        if (this.dsBizProcLogTMsg != null) {
            updateDsBizProcLog();
        } else {
            insertDsBizProcLog();
        }
    }

    private void insertInterfaceData() {
        List<NSBI0590_01TMsg> inTMsgList = new ArrayList<NSBI0590_01TMsg>();
        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            // Search Target Data
            stmt = this.ssmLLClient.createPreparedStatement("getFsrSerialInfo", setParam(), execParam);
            rsSet = stmt.executeQuery();

            int commitCount = 0;

            // Insert Interface Data
            while (rsSet.next()) {
                this.unitId++;
                inTMsgList.add(setNSBI0590_01(rsSet));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertInterfaceData(inTMsgList);
                    this.totalCommitCount += commitCount;
                    inTMsgList.clear();
                    commitCount = 0;
                }
            }

            if (this.unitId != this.totalCommitCount) {
                commitCount = insertInterfaceData(inTMsgList);
                this.totalCommitCount += commitCount;
            }
            // mod start 2016/10/25 CSA QC#14988
            if (this.totalCommitCount != 0) {
                // insert INTERFACE_TRANSACTION
                trxAccess.createIntegrationRecordForBatch(this.intfcId, this.trxId);
            }
            // mod end 2016/10/25 CSA QC#14988

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private Map<String, Object> setParam() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("dateFormat", SQL_DATE_FORMAT);
        ssmParam.put("months", MONTHS);
        // START 2023/03/03 L.Mandanas [QC#61067, ADD]
        ssmParam.put("slsDt", this.slsDt);
        ssmParam.put("fsrStsCd", SVC_TASK_STS.CLOSED);
        ssmParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        ssmParam.put("svcMemoTpCd", SVC_MEMO_TP.TERMINATION_NOTE);
        ssmParam.put("cmpsGlblCmpyCd", this.cmpsGlblCmpyCd);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("timeFormat", CRAT_TM_FORMAT);
        ssmParam.put("ifProcStsCd", IF_PROC_STS_CD);
        String[] svcPgmMdseCdList = this.splitSvcPgm.split(COMMA);
        ssmParam.put("svcPgmMdseCdList", svcPgmMdseCdList);
        ssmParam.put("fromTm", FROM_TM);
        ssmParam.put("toTm", TO_TM);

        if (this.processMode.equals(DAILY_MODE)) {
            ssmParam.put("isDaily", ZYPConstant.FLG_ON_Y);
            ssmParam.put("isMonthly", ZYPConstant.FLG_OFF_N);
            ssmParam.put("extrDys", this.cmps_Extr_Days);
        }
        if (this.processMode.equals(MONTHLY_MODE)) {
            ssmParam.put("isDaily", ZYPConstant.FLG_OFF_N);
            ssmParam.put("isMonthly", ZYPConstant.FLG_ON_Y);
            String fromDt = getLastMonthStartDate(this.slsDt);
            ssmParam.put("fromDt", fromDt);
            String thruDt = getNextMonthStartDate(fromDt);
            ssmParam.put("thruDt", thruDt);
            String[] svcMachMstrStsCdList = this.compassSvcMachMstrStsCd.split(COMMA);
            ssmParam.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        }
        // END 2023/03/03 L.Mandanas [QC#61067, ADD]

        // START 2023/07/06 R.Jin [QC#61067, ADD]
        ssmParam.put("sndrCmpyCd", this.cmpsSndrCmpyCd);
        ssmParam.put("crsSvcLobCd", this.crsSvcLobCd);
        ssmParam.put("crsSvcTechCd", this.crsSvcTechCd);
        ssmParam.put("cmpsCtryCd", this.cmpsCtryCd);
        ssmParam.put("cmpsDelerTp", this.cmpsDelerTp);
        ssmParam.put("findsCtryCd", this.findsCtryCd);
//        ssmParam.put("contrDtlSts", this.contrDtlSts);
        ssmParam.put("smmSts", this.smmSts);
        // END 2023/07/06 R.Jin [QC#61067, ADD]

        String[] dsContrDtlStsCdList = this.findsExclContrDtlStsCd.split(COMMA);
        ssmParam.put("dsContrDtlStsCdList", dsContrDtlStsCdList);

        String[] svcDisptEventCdList = this.findsCloFsrEventCd.split(COMMA);
        ssmParam.put("svcDisptEventCdList", svcDisptEventCdList);
        ssmParam.put("systemTs", this.procTs);
        if (this.dsBizProcLogTMsg != null) {
            ssmParam.put("lastProcTs", this.dsBizProcLogTMsg.dsBizLastProcTs.getValue());
        }
        return ssmParam;
    }

    private String getVarCharConst(String varCharConstKey) {
        String varCharConstVal = ZYPCodeDataUtil.getVarCharConstValue(varCharConstKey, this.glblCmpyCd);
        if (!hasValue(varCharConstVal)) {
            throw new S21AbendException(NSBM0069E, new String[] {varCharConstKey });
        }
        return varCharConstVal;
    }

    // START 2023/07/06 R.Jin [QC#61067, ADD]
    private BigDecimal getNumConst(String numConstKey) {
        return ZYPCodeDataUtil.getNumConstValue(numConstKey, this.glblCmpyCd);
    }
    // END 2023/07/06 R.Jin [QC#61067, ADD]

    private NSBI0590_01TMsg setNSBI0590_01(ResultSet rsSet) {
        NSBI0590_01TMsg tMsg = new NSBI0590_01TMsg();
        // START 2023/03/03 L.Mandanas [QC#61067, ADD]
        ArrayList[] columnList = tMsg.getSelectColumnList();
        List<String> rsColNames = new ArrayList<String>();
        ResultSetMetaData rsm;
        String dbNm;
        String rsNm;
        // END 2023/03/03 L.Mandanas [QC#61067, ADD]

        try {
            // START 2023/03/03 L.Mandanas [QC#61067, ADD]
            rsm = rsSet.getMetaData();
            for (int i = 0; i < rsm.getColumnCount(); i++) {
                rsColNames.add(rsm.getColumnName(i + 1));
            }
            // END 2023/03/03 L.Mandanas [QC#61067, ADD]
            setValue(tMsg.interfaceId, this.intfcId);
            setValue(tMsg.transactionId, this.trxId);
            setValue(tMsg.segmentId, BigDecimal.ONE);
            setValue(tMsg.unitId, BigDecimal.valueOf(this.unitId));
            setValue(tMsg.seqNumber, BigDecimal.ONE);

            // START 2023/03/03 L.Mandanas [QC#61067, DEL]
//            setValue(tMsg.svcTaskNum, rsSet.getString("SVC_TASK_NUM"));
//            setValue(tMsg.serNum, rsSet.getString("SER_NUM"));
//            setValue(tMsg.istlDt, rsSet.getString("ISTL_DT"));
//            setValue(tMsg.mdseCd, rsSet.getString("MDSE_CD"));
//            // START 2017/01/13 K.Kitachi [QC#17065, MOD]
////            setValue(tMsg.mdseDescShortTxt, rsSet.getString("MDSE_DESC_SHORT_TXT"));
//            setValue(tMsg.mdseDescShortTxt, removeDoubleQuote(rsSet.getString("MDSE_DESC_SHORT_TXT")));
//            // END 2017/01/13 K.Kitachi [QC#17065, MOD]
//            setValue(tMsg.curLocAcctNum, rsSet.getString("CUR_LOC_ACCT_NUM"));
//            setValue(tMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
//            setValue(tMsg.firstLineAddr, rsSet.getString("ST_CD"));
//            setValue(tMsg.scdLineAddr, rsSet.getString("ST_CD"));
//            setValue(tMsg.ctryCd, rsSet.getString("CTRY_CD"));

//            if (DS_CONTR_CATG.WARRANTY.equals(rsSet.getString("DS_CONTR_CATG_CD"))) {
//                setValue(tMsg.dsContrCatgNm_01, this.findsWtyNm);
//                setValue(tMsg.dsContrCatgNm_02, this.findsWtyNm);
//            } else {
//                setValue(tMsg.dsContrCatgNm_01, this.findsSvcNm);
//                setValue(tMsg.dsContrCatgNm_02, this.findsSvcNm);
//            }
//            setValue(tMsg.fill20Txt_07, rsSet.getString("FILL_20_TXT_07"));

//            setValue(tMsg.contrEffFromDt, rsSet.getString("CONTR_EFF_FROM_DT"));
//            setValue(tMsg.contrEffThruDt, rsSet.getString("CONTR_EFF_THRU_DT"));

//            setValue(tMsg.glblCmpyCd_01, this.findsOrgCmpyCd);

//            setValue(tMsg.dsAcctDlrCd, rsSet.getString("DS_ACCT_DLR_CD"));
//            setValue(tMsg.dsAcctDlrNm, rsSet.getString("DS_ACCT_DLR_NM"));

//            setValue(tMsg.glblCmpyCd_02, this.findsGlblCmpyCd);
//            setValue(tMsg.cratDt, this.procDt);
//            setValue(tMsg.cratTm, this.procTm);
//            setValue(tMsg.ifProcStsCd, IF_PROC_STS_CD);
            // END 2023/03/03 L.Mandanas [QC#61067, DEL]

            // START 2023/03/03 L.Mandanas [QC#61067, ADD]
            for (int idx = VAR_ITEM_START_POSN; idx < columnList[0].size(); idx++) {
                dbNm = columnList[0].get(idx).toString();
                rsNm = columnList[1].get(idx).toString();
                if (!rsColNames.contains(rsNm)) {
                    continue;
                }
                if (tMsg.getAttr(dbNm).getType() == EZDItemAttrDefines.TYPE_SEISU_SYOSU) {
                    tMsg.setDBValue(dbNm, rsSet.getBigDecimal(rsNm));
                } else {
                    tMsg.setDBValue(dbNm, rsSet.getString(rsNm));
                }
            }
            // END 2023/03/03 L.Mandanas [QC#61067, ADD]
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tMsg;
    }

    private int insertInterfaceData(List<NSBI0590_01TMsg> inMsgLst) {
        NSBI0590_01TMsg[] inMsgArray;
        inMsgArray = new NSBI0590_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NSBM0164E, new String[] {inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
    }

    private DS_BIZ_PROC_LOGTMsg getDsBizProcLog() {
        DS_BIZ_PROC_LOGTMsg inMsg = new DS_BIZ_PROC_LOGTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("procPgmId01", this.getClass().getSimpleName());
        DS_BIZ_PROC_LOGTMsgArray tMsgAry = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            return null;
        }
        return (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsgAry.no(0));
    }

    private void insertDsBizProcLog() {
        this.dsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();

        setValue(this.dsBizProcLogTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(this.dsBizProcLogTMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
        setValue(this.dsBizProcLogTMsg.procPgmId, this.getClass().getSimpleName());
        setValue(this.dsBizProcLogTMsg.dsBizProcDt, this.procDt);
        setValue(this.dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        setValue(this.dsBizProcLogTMsg.dsBizLastProcTs, this.procTs);
        setValue(this.dsBizProcLogTMsg.dsBizLastUpdTs, this.procTs);

        S21FastTBLAccessor.insert(this.dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(this.dsBizProcLogTMsg.getReturnCode())) {
            String message = PROC_PGM_ID + " = " + this.dsBizProcLogTMsg.procPgmId.getValue();
            throw new S21AbendException(NSBM0121E, new String[] {this.dsBizProcLogTMsg.getTableName(), message });
        }
    }

    private void updateDsBizProcLog() {
        setValue(this.dsBizProcLogTMsg.dsBizProcDt, this.procDt);
        setValue(this.dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        setValue(this.dsBizProcLogTMsg.dsBizLastProcTs, this.procTs);
        setValue(this.dsBizProcLogTMsg.dsBizLastUpdTs, this.procTs);

        S21FastTBLAccessor.update(this.dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(this.dsBizProcLogTMsg.getReturnCode())) {
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
     * isMonthlyProcessDate.
     * @param date String
     * @return boolean
     */
    private boolean isMonthlyProcessDate(String date) {
        int day = Integer.parseInt(date.substring(INT_6));
        int week = ZYPDateUtil.getDayOfWeek(date);
        if (day <= INT_7 && week == ZYPDateUtil.WEEK_THURSDAY) {
            return true;
        }
        return false;
    }

    /**
     * getLastMonthStartDate.
     * @param date String
     * @return String
     */
    private String getLastMonthStartDate(String date) {
        String startDate = date.substring(0, INT_6) + START_DAY;
        return addMonths(startDate, -1);
    }

    /**
     * getNextMonthStartDate.
     * @param date String
     * @return String
     */
    private String getNextMonthStartDate(String date) {
        String startDate = date.substring(0, INT_6) + START_DAY;
        return addMonths(startDate, 1);
    }

    /**
     * addMonths.
     * @param date String
     * @param numberOfMonths int
     * @return String
     */
    private String addMonths(String date, int numberOfMonths) {
        SimpleDateFormat format = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(date);
        } catch (ParseException e) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(Calendar.MONTH, numberOfMonths);
        String returnDate = format.format(cal.getTime());
        return returnDate;
    }
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]
}
