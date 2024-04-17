/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB055001;

import static com.canon.cusa.s21.batch.NSB.NSBB055001.constant.NSBB055001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCommonConst;
import parts.common.EZDStringUtil;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.NSBI0550_01TMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetDownTm;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.sun.xml.internal.ws.util.StringUtils;

/**
 * <pre>
 * Finds Interface Visit History Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 07/06/2016   Hitachi         K.Kasai         Create          N/A
 * 10/25/2016   Hitachi         T.Mizuki        Update          QC#14988
 * 03/03/2023   CITS            L.Mandanas      Update          QC#61067
 * 07/06/2023   CITS            R.Jin           Update          QC#61067
 *</pre>
 */
public class NSBB055001 extends S21BatchMain {

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** EXCL_SLS_CMPY_CALL_TP_CD */
    private String findsExclSlsCmpyCallTpCd = null;

    /** FINDS_CLO_FSR_EVENT_CD */
    private String findsCloFsrEventCd = null;

    /** FINDS_VISIT_GLBL_CMPY_CD */
    private String findsVisitGlblCmpyCd = null;

    /** FINDS_VISIT_CTRY_CD */
    private String findsVisitCtryCd = null;

    /** FINDS_GLBL_CMPY_CD */
    private String findsGlblCmpyCd = null;

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
    /** Process Mode */
    private String processMode = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** CMPS_GLBL_CMPY_CD */
    private String cmpsGlblCmpyCd = null;

    /** Compass Local Time Change */
    private String cmpsLocalTimeChng = null;
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]

    // START 2023/07/06 R.Jin [QC#61067, ADD]
    /** COMPASS Sender Company Code */
    private String cmpsSndrCmpyCd = null;

    /** CMPS_CRSSVC_EXCL_TECH_CD */
    private String crsSvcTechCd = null;

    /** CMPS_CTRY_CD */
    private String cmpsCtryCd = null;

    /** CMPS_DLR_SVC_CD */
    private String cmpsDelerTp = null;

    /** CMPS_CRSSVC_EXCL_LOB_CD */
    private String crsSvcLobCd = null;

    /** CMPS_EXTR_DAYS */
    private BigDecimal cmps_Extr_Days;
    // END 2023/07/06 R.Jin [QC#61067, ADD]

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NSBB055001().executeBatch(NSBB055001.class.getSimpleName());
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
        this.findsExclSlsCmpyCallTpCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_EXCL_SLS_CMPY_CALL_TP_CD);
        this.findsCloFsrEventCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_CLO_FSR_EVENT_CD);
        this.findsVisitGlblCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_VISIT_GLBL_CMPY_CD);
        this.findsVisitCtryCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_VISIT_CTRY_CD);
        this.findsGlblCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_GLBL_CMPY_CD);

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = this.trxAccess.getNextTransactionId();
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // START 2023/03/03 L.Mandanas [QC#61067, ADD]
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // Get User Variable
        this.processMode = getUserVariable1();
        if (!hasValue(this.processMode)) {
            throw new S21AbendException(NSBM0071E, new String[] {"User Variable1" });
        }

        // Get Var Char Const(CMPS_GLBL_CMPY_CD)
        this.cmpsGlblCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD);
        if (!ZYPCommonFunc.hasValue(this.cmpsGlblCmpyCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD });
        }

        // Get Variable Character Constant
        this.cmpsLocalTimeChng = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_LOCAL_TIME_CHNG);
        if (!ZYPCommonFunc.hasValue(this.cmpsLocalTimeChng)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_LOCAL_TIME_CHNG });
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
        
        // Get Var Char Const(CMPS_CRSSVC_EXCL_LOB_CD)
        this.crsSvcLobCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_LOB_CD);
        if (!ZYPCommonFunc.hasValue(this.crsSvcLobCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_LOB_CD });
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
        List<NSBI0550_01TMsg> inTMsgList = new ArrayList<NSBI0550_01TMsg>();
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
            stmt = this.ssmLLClient.createPreparedStatement("getFsrVisitHistInfo", setParam(), execParam);
            rsSet = stmt.executeQuery();

            int commitCount = 0;

            // Insert Interface Data
            while (rsSet.next()) {
                this.unitId++;
                inTMsgList.add(setNSBI0550_01(rsSet));

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
        String[] svcDisptEventCdList = this.findsCloFsrEventCd.split(COMMA);
        ssmParam.put("svcDisptEventCdList", svcDisptEventCdList);
        ssmParam.put("procTs", this.procTs);
        if (this.dsBizProcLogTMsg != null) {
            ssmParam.put("dsBizLastProcTs", this.dsBizProcLogTMsg.dsBizLastProcTs.getValue());
        }

        // START 2023/03/03 L.Mandanas [QC#61067, ADD]
        ssmParam.put("colon", COLON);
        ssmParam.put("hyphen", HYPHEN);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("substringStart", SUBSTRING_START);
        ssmParam.put("substringEnd", SUBSTRING_END);
        ssmParam.put("dsSvcCallTpCd", DS_SVC_CALL_TP.RECALL);
        ssmParam.put("timedateFormat", CRAT_TMDT_FORMAT);
        ssmParam.put("int9999", INT_9999);
        ssmParam.put("int0", INT_0);
        ssmParam.put("int1", INT_1);
        ssmParam.put("ifProcStsCd", IF_PROC_STS_CD);
        ssmParam.put("dateFormat", CRAT_DT_FORMAT);
        ssmParam.put("timeFormat", CRAT_TM_FORMAT);
        ssmParam.put("minutesDay", MINUTES_DAY);
        ssmParam.put("svcTaskStsCd", SVC_TASK_STS.CLOSED);
        ssmParam.put("fsrStsCd", SVC_TASK_STS.CLOSED);
        ssmParam.put("fromTm", FROM_TM);
        ssmParam.put("toTm", TO_TM);

        if (this.processMode.equals(DAILY_MODE)) {
            // Daily
            ssmParam.put("isDaily", ZYPConstant.FLG_ON_Y);
            ssmParam.put("isMonthly", ZYPConstant.FLG_OFF_N);
            ssmParam.put("slsDt", this.slsDt);
            // START 2023/07/06 R.Jin [QC#61067, ADD]
            ssmParam.put("extrDys", this.cmps_Extr_Days);
            // END 2023/07/06 R.Jin [QC#61067, ADD]
        } else {
            // Monthly
            ssmParam.put("isDaily", ZYPConstant.FLG_OFF_N);
            ssmParam.put("isMonthly", ZYPConstant.FLG_ON_Y);
            String fromDt = getLastMonthStartDate(this.slsDt);
            ssmParam.put("fromDt", fromDt);
            String thruDt = getNextMonthStartDate(fromDt);
            ssmParam.put("thruDt", thruDt);
        }
        // END 2023/03/03 L.Mandanas [QC#61067, ADD]
        // START 2023/07/06 R.Jin [QC#61067, ADD]
        ssmParam.put("sndrCmpyCd", this.cmpsSndrCmpyCd);
        ssmParam.put("crsSvcTechCd", this.crsSvcTechCd);
        ssmParam.put("cmpsDelerTp", this.cmpsDelerTp);
        ssmParam.put("crsSvcLobCd", this.crsSvcLobCd);
        // END 2023/07/06 R.Jin [QC#61067, ADD]
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

    private NSBI0550_01TMsg setNSBI0550_01(ResultSet rsSet) {
        NSBI0550_01TMsg tMsg = new NSBI0550_01TMsg();

        try {
            setValue(tMsg.interfaceId, this.intfcId);
            setValue(tMsg.transactionId, this.trxId);
            setValue(tMsg.segmentId, BigDecimal.ONE);
            setValue(tMsg.unitId, BigDecimal.valueOf(this.unitId));
            setValue(tMsg.seqNumber, BigDecimal.ONE);

            setValue(tMsg.serNum, rsSet.getString("SER_NUM"));
            // START 2023/03/03 L.Mandanas [QC#61067, DEL]
//            setValue(tMsg.svcTaskNum, rsSet.getString("SVC_TASK_NUM"));
//            setValue(tMsg.fsrVisitArvDt_01, rsSet.getString("FSR_VISIT_ARV_DT"));
//            setValue(tMsg.fsrVisitArvTm_01, rsSet.getString("FSR_VISIT_ARV_TM"));
//            setValue(tMsg.mdseCd, rsSet.getString("MDSE_CD"));
//            setValue(tMsg.dsSvcCallTpNm, rsSet.getString("DS_SVC_CALL_TP_NM"));
//            setValue(tMsg.dsSvcCallTpDescTxt, rsSet.getString("DS_SVC_CALL_TP_DESC_TXT"));
//            setValue(tMsg.svcPblmCrctTpCd, rsSet.getString("SVC_PBLM_CRCT_TP_CD"));
//            setValue(tMsg.svcPblmCrctTpNm, rsSet.getString("SVC_PBLM_CRCT_TP_NM"));
//            setValue(tMsg.fsrCratDt, rsSet.getString("FSR_CRAT_DT"));
//            setValue(tMsg.fsrCratTm, rsSet.getString("FSR_CRAT_TM"));
//            setValue(tMsg.svcTrvlTmNum, rsSet.getBigDecimal("SVC_TRVL_TM_NUM"));
//            setValue(tMsg.svcLborTmNum, rsSet.getBigDecimal("SVC_LBOR_TM_NUM"));
//            setValue(tMsg.fsrVisitArvDt_02, rsSet.getString("FSR_VISIT_ARV_DT"));
//            setValue(tMsg.fsrVisitArvTm_02, rsSet.getString("FSR_VISIT_ARV_TM"));
//            setValue(tMsg.fsrVisitCloDt, rsSet.getString("FSR_VISIT_CLO_DT"));
//            setValue(tMsg.fsrVisitCloTm, rsSet.getString("FSR_VISIT_CLO_TM"));
//            setValue(tMsg.techCd, rsSet.getString("TECH_CD"));
//            setValue(tMsg.ctryCd, this.findsVisitCtryCd);
//            setValue(tMsg.svcPblmTpCd, rsSet.getString("SVC_PBLM_TP_CD"));
//            setValue(tMsg.svcPblmTpDescTxt, rsSet.getString("SVC_PBLM_TP_DESC_TXT"));
//            setValue(tMsg.svcPblmLocTpCd, rsSet.getString("SVC_PBLM_LOC_TP_CD"));
//            setValue(tMsg.svcPblmLocTpDescTxt, rsSet.getString("SVC_PBLM_LOC_TP_DESC_TXT"));
//            setValue(tMsg.svcPblmRsnTpCd, rsSet.getString("SVC_PBLM_RSN_TP_CD"));
//            setValue(tMsg.svcPblmRsnTpDescTxt, rsSet.getString("SVC_PBLM_RSN_TP_DESC_TXT"));
//            setValue(tMsg.svcPblmCrctCd, rsSet.getString("SVC_PBLM_CRCT_TP_CD"));
//            setValue(tMsg.svcPblmCrctDescTxt, rsSet.getString("SVC_PBLM_CRCT_TP_DESC_TXT"));
//            setValue(tMsg.fill10Txt_01, STR_0);
//            setValue(tMsg.fill10Txt_02, STR_0);
//            setValue(tMsg.fill10Txt_03, STR_0);
//            setValue(tMsg.fill10Txt_04, STR_0);
//            setValue(tMsg.fill10Txt_05, STR_0);
//            setValue(tMsg.fill10Txt_06, STR_0);
//            setValue(tMsg.fill10Txt_07, STR_0);
//            setValue(tMsg.fill10Txt_08, STR_0);
//            setValue(tMsg.fill10Txt_09, STR_0);
//            setValue(tMsg.fill10Txt_10, STR_0);
//            setValue(tMsg.fill10Txt_11, STR_0);
//            setValue(tMsg.svcRspTmMnAot, rsSet.getBigDecimal("SVC_RSP_TM_MN_AOT"));
//            if (hasValue(rsSet.getString("PREV_FSR_VISIT_NUM"))) {
//                setValue(tMsg.fill1Txt_01, STR_1);
//            } else {
//                setValue(tMsg.fill1Txt_01, STR_0);
//            }
//            if (ZYPConstant.FLG_ON_Y.equals(rsSet.getString("SVC_RCLL_FLG"))) {
//                setValue(tMsg.fill1Txt_02, STR_1);
//            } else {
//                setValue(tMsg.fill1Txt_02, STR_0);
//            }
//            BigDecimal downTm = NSXC002001GetDownTm.getDownTm(this.glblCmpyCd, rsSet.getString("SVC_TASK_NUM"));
//            setValue(tMsg.svcCallDownTmNum, downTm);
//            setValue(tMsg.glblCmpyCd_03, this.findsGlblCmpyCd);
//            setValue(tMsg.cratDt, this.procDt);
//            setValue(tMsg.cratTm, this.procTm);
//            setValue(tMsg.ifProcStsCd, IF_PROC_STS_CD);
//
//            String[] findsExclSlsCmpyCallTpCdList = this.findsExclSlsCmpyCallTpCd.split(COMMA);
//            if (!Arrays.asList(findsExclSlsCmpyCallTpCdList).contains(rsSet.getString("DS_SVC_CALL_TP_CD"))) {
//                setValue(tMsg.glblCmpyCd_01, this.findsVisitGlblCmpyCd);
//                setValue(tMsg.glblCmpyCd_02, this.findsVisitGlblCmpyCd);
//            }
            // END 2023/03/03 L.Mandanas [QC#61067, DEL]
            // START 2023/03/03 L.Mandanas [QC#61067, ADD]
            setValue(tMsg.svcTaskNumAndFsrNum, rsSet.getString("SVC_TASK_NUM_AND_FSR_NUM"));
            setValue(tMsg.fsrVisitArvDt_01, rsSet.getString("FSR_VISIT_ARV_DT_01"));
            setValue(tMsg.fsrVisitArvTm_01, rsSet.getString("FSR_VISIT_ARV_TM_01"));
            setValue(tMsg.glblCmpyCd_01, rsSet.getString("GLBL_CMPY_CD_01"));
            setValue(tMsg.mdseCd, rsSet.getString("MDSE_CD"));
            setValue(tMsg.dsSvcCallTpCd, rsSet.getString("DS_SVC_CALL_TP_CD"));
            setValue(tMsg.dsSvcCallTpDescTxt, rsSet.getString("DS_SVC_CALL_TP_DESC_TXT"));
            setValue(tMsg.svcPblmTpCd_01, rsSet.getString("SVC_PBLM_TP_CD_01"));
            setValue(tMsg.svcPblmTpDescTxt_01, rsSet.getString("SVC_PBLM_TP_DESC_TXT_01"));
            // START 2023/07/06 R.Jin [QC#61067, MOD]
//            setValue(tMsg.svcCallIncdtDt, rsSet.getString("SVC_CALL_INCDT_DT"));
//            setValue(tMsg.svcCallIncdtTm, rsSet.getString("SVC_CALL_INCDT_TM"));
            setValue(tMsg.svcCallIncdtDt, rsSet.getString("FSR_CRAT_DT"));
            setValue(tMsg.svcCallIncdtTm, rsSet.getString("FSR_CRAT_TM"));
//            setValue(tMsg.svcTrvlTmNum, roundTime(rsSet.getBigDecimal("SVC_TRVL_TM_NUM")));
//            setValue(tMsg.svcLborTmNum, roundTime(rsSet.getBigDecimal("SVC_LBOR_TM_NUM")));
            if(rsSet.getBigDecimal("SVC_TRVL_TM_NUM") != null) {
                setValue(tMsg.svcTrvlTmNum, roundTime(rsSet.getBigDecimal("SVC_TRVL_TM_NUM")));
            }
            if(rsSet.getBigDecimal("SVC_LBOR_TM_NUM") != null) {
                setValue(tMsg.svcLborTmNum, roundTime(rsSet.getBigDecimal("SVC_LBOR_TM_NUM")));
            }
            // END 2023/07/06 R.Jin [QC#61067, MOD]
            setValue(tMsg.fsrVisitArvDt_02, rsSet.getString("FSR_VISIT_ARV_DT_02"));
            setValue(tMsg.fsrVisitArvTm_02, rsSet.getString("FSR_VISIT_ARV_TM_02"));
            setValue(tMsg.fsrVisitCpltDt, rsSet.getString("FSR_VISIT_CPLT_DT"));
            setValue(tMsg.fsrVisitCpltTm, rsSet.getString("FSR_VISIT_CPLT_TM"));
            setValue(tMsg.techCd, rsSet.getString("TECH_CD"));
            // START 2023/07/06 R.Jin [QC#61067, ADD]
//            setValue(tMsg.ctryCd, rsSet.getString("CTRY_CD"));
            setValue(tMsg.ctryCd, this.cmpsCtryCd);
            // END 2023/07/06 R.Jin [QC#61067, ADD]
            setValue(tMsg.glblCmpyCd_02, rsSet.getString("GLBL_CMPY_CD_02"));
            setValue(tMsg.avgMtrReadCnt, rsSet.getBigDecimal("AVG_MTR_READ_CNT"));
            setValue(tMsg.svcPblmTpCd_02, rsSet.getString("SVC_PBLM_TP_CD_02"));
            setValue(tMsg.svcPblmTpDescTxt_02, rsSet.getString("SVC_PBLM_TP_DESC_TXT_02"));
            setValue(tMsg.svcPblmCatgCd, rsSet.getString("SVC_PBLM_CATG_CD"));
            setValue(tMsg.svcPblmLocTpCd_01, rsSet.getString("SVC_PBLM_LOC_TP_CD_01"));
            setValue(tMsg.svcPblmLocTpDescTxt_01, rsSet.getString("SVC_PBLM_LOC_TP_DESC_TXT_01"));
            setValue(tMsg.svcPblmRsnTpCd, rsSet.getString("SVC_PBLM_RSN_TP_CD"));
            setValue(tMsg.svcPblmRsnTpDescTxt, rsSet.getString("SVC_PBLM_RSN_TP_DESC_TXT"));
            setValue(tMsg.svcPblmCrctCd, rsSet.getString("SVC_PBLM_CRCT_CD"));
            setValue(tMsg.svcPblmCrctDescTxt, rsSet.getString("SVC_PBLM_CRCT_DESC_TXT"));
            setValue(tMsg.svcRspTmNum, rsSet.getBigDecimal("SVC_RSP_TM_NUM"));
            setValue(tMsg.reVisitFlg, rsSet.getString("RE_VISIT_FLG"));
            setValue(tMsg.reSvcPblmTpFlg, rsSet.getString("RE_SVC_PBLM_TP_FLG"));
            setValue(tMsg.svcCallDownTmNum, rsSet.getBigDecimal("SVC_CALL_DOWN_TM_NUM"));
            // START 2023/07/06 R.Jin [QC#61067, MOD]
//            setValue(tMsg.glblCmpyCd_03, this.cmpsGlblCmpyCd);
            setValue(tMsg.glblCmpyCd_03, rsSet.getString("GLBL_CMPY_CD_03"));
//            setValue(tMsg.fill10Txt_26, rsSet.getString("SVC_PBLM_TP_CD_02"));
            setValue(tMsg.fill10Txt_26, rsSet.getString("SVC_PBLM_TP_CD_02").startsWith(STR_0) ? rsSet.getString("SVC_PBLM_TP_CD_02") : "");
            // END 2023/07/06 R.Jin [QC#61067, MOD]
            setValue(tMsg.cratDt, rsSet.getString("CRAT_DT"));
            setValue(tMsg.cratTm, rsSet.getString("CRAT_TM"));
            setValue(tMsg.ifProcStsCd, rsSet.getString("IF_PROC_STS_CD"));

            setReadMtrCnt(tMsg, rsSet.getBigDecimal("MTR_GRP_PK"), rsSet.getBigDecimal("SVC_MACH_MSTR_PK"), rsSet.getString("SVC_TASK_NUM"));
            if (ZYPConstant.FLG_ON_Y.equals(this.cmpsLocalTimeChng)) {
                convTimeZone(tMsg, rsSet.getString("CTRY_CD"), rsSet.getString("POST_CD"));
            }
            trimCdFromDesc(tMsg);
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tMsg;
    }

    private int insertInterfaceData(List<NSBI0550_01TMsg> inMsgLst) {
        NSBI0550_01TMsg[] inMsgArray;
        inMsgArray = new NSBI0550_01TMsg[inMsgLst.size()];
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

    /**
     * setReadMtrCnt.
     * @param inParam NSBI0550_01TMsg
     * @param mtrGrpPk BigDecimal
     * @param svcMachMstrPk BigDecimal
     * @param svcTaskNum String
     * @return
     */
    private void setReadMtrCnt(NSBI0550_01TMsg inParam, BigDecimal mtrGrpPk, BigDecimal svcMachMstrPk, String svcTaskNum) {
        if (!hasValue(mtrGrpPk) || !hasValue(svcTaskNum)) {
            return;
        }
        List<Map<String, Object>> cmpsMtrFmlaList = getCmpsMtrFmla(mtrGrpPk);
        if (cmpsMtrFmlaList.size() == 0) {
            return;
        }
        List<Map<String, Object>> svcPhysMtrReadList = getSvcPhysMtrRead(svcMachMstrPk, svcTaskNum);
        if (svcPhysMtrReadList.size() == 0) {
            return;
        }
        for (Map<String, Object> cmpsMtrFmla : cmpsMtrFmlaList) {
            String key = "readMtrCnt_" + (String) cmpsMtrFmla.get("CMPS_MTR_LB_CD");
            BigDecimal val = calcReadMtrCnt(cmpsMtrFmla, svcPhysMtrReadList);
            inParam.setDBValue(key, val);
        }
    }

    /**
     * roundTime.
     * @param tm BigDecimal
     * @return BigDecimal
     */
    private BigDecimal roundTime(BigDecimal tm) {
        BigDecimal limit = new BigDecimal(999);
        if (limit.compareTo(tm) < 0) {
            tm = limit;
        }
        return tm;
    }

    /**
     * trimCdFromDesc.
     * @param inParam NSBI0550_01TMsg
     * @return
     */
    private void trimCdFromDesc(NSBI0550_01TMsg inParam) {
        setValue(inParam.svcPblmTpDescTxt_02, trimCd(inParam.svcPblmTpDescTxt_02, cutProdCd(inParam.svcPblmTpCd_02)));
        // START 2023/08/21 R.Jin [QC#61067, MOD]
//        setValue(inParam.svcPblmLocTpDescTxt_01, trimCd(inParam.svcPblmLocTpDescTxt_01, cutProdCd(inParam.svcPblmLocTpCd_01)));
        setValue(inParam.svcPblmLocTpDescTxt_01, trimCd(inParam.svcPblmLocTpDescTxt_01, inParam.svcPblmLocTpCd_01.getValue()));
        // END 2023/08/21 R.Jin [QC#61067, MOD]
        setValue(inParam.svcPblmRsnTpDescTxt, trimCd(inParam.svcPblmRsnTpDescTxt, inParam.svcPblmRsnTpCd.getValue()));
        setValue(inParam.svcPblmCrctDescTxt, trimCd(inParam.svcPblmCrctDescTxt, inParam.svcPblmCrctCd.getValue()));
        setValue(inParam.dsSvcCallTpDescTxt, trimCd(inParam.dsSvcCallTpDescTxt, inParam.dsSvcCallTpCd.getValue()));
        setValue(inParam.svcPblmTpDescTxt_01, trimCd(inParam.svcPblmTpDescTxt_01, inParam.svcPblmTpCd_01.getValue()));
    }

    /**
     * trimCd.
     * @param desc EZDTStringItem
     * @param cdStr String
     * @return EZDTStringItem
     */
    private EZDTStringItem trimCd(EZDTStringItem desc, String cdStr) {
        String descStr = desc.getValue();
        if (descStr.startsWith(cdStr + " - ")) {
            descStr = descStr.substring(cdStr.length() + 3);
        } else if (descStr.startsWith(cdStr + "-")) {
            descStr = descStr.substring(cdStr.length() + 1);
        }
        setValue(desc, descStr);
        return desc;
    }

    /**
     * cutProdCd.
     * @param item EZDTStringItem
     * @return String
     */
    private String cutProdCd(EZDTStringItem item) {
        if (!hasValue(item)) {
            return null;
        } else if (item.getValue().length() <= 1) {
            return item.getValue();
        } else {
            return item.getValue().substring(1);
        }
    }

    /**
     * convTimeZone.
     * @param inParam NSBI0550_01TMsg
     * @param ctryCd String
     * @param postCd String
     * @return
     */
    private void convTimeZone(NSBI0550_01TMsg inParam, String ctryCd, String postCd) {
        convTimeZone(inParam.fsrVisitArvDt_01, inParam.fsrVisitArvTm_01, ctryCd, postCd);
        convTimeZone(inParam.svcCallIncdtDt, inParam.svcCallIncdtTm, ctryCd, postCd);
        convTimeZone(inParam.fsrVisitArvDt_02, inParam.fsrVisitArvTm_02, ctryCd, postCd);
        convTimeZone(inParam.fsrVisitCpltDt, inParam.fsrVisitCpltTm, ctryCd, postCd);
    }

    /**
     * convTimeZone.
     * @param dtItem EZDTDateItem
     * @param tmItem EZDTStringItem
     * @param ctryCd String
     * @param postCd String
     * @return
     */
    private void convTimeZone(EZDTDateItem dtItem, EZDTStringItem tmItem, String ctryCd, String postCd) {
        int mode = NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC;
        String dateTime = dtItem.getValue() + tmItem.getValue();
        if (!hasValue(dtItem.getValue()) || !hasValue(tmItem.getValue())) {
            return;
        }
        SvcTimeZoneInfo info = NSXC001001SvcTimeZone.convertTime(mode, dateTime, ctryCd, postCd);
        if (info == null) {
            return;
        }
        String convTs = info.getDateTime();
        if (!hasValue(convTs) || convTs.length() < 14) {
            return;
        }
        setValue(dtItem, convTs.substring(0, 8));
        setValue(tmItem, convTs.substring(8, 14));
    }

    /**
     * calcReadMtrCnt.
     * @param cmpsMtrFmla Map<String, Object>
     * @param svcPhysMtrReadList List<Map<String, Object>>
     * @return BigDecimal
     */
    private BigDecimal calcReadMtrCnt(Map<String, Object> cmpsMtrFmla, List<Map<String, Object>> svcPhysMtrReadList) {
        BigDecimal calcReadMtrCnt = BigDecimal.ZERO;
        for (int j = 1; j <= 10; j++) {
            String number = String.valueOf(j);
            if (j < 10) {
                number = "0" + number;
            }
            String mtrFmlaTpCd = (String) cmpsMtrFmla.get("MTR_FMLA_TP_CD_" + number);
            String fmlaMtrLbCd = (String) cmpsMtrFmla.get("FMLA_MTR_LB_CD_" + number);
            if (!hasValue(mtrFmlaTpCd) || !hasValue(fmlaMtrLbCd)) {
                break;
            }
            if (j != 1 && MTR_FMLA_TP.EQUALS.equals(mtrFmlaTpCd)) {
                break;
            }
            for (Map<String, Object> svcPhysMtrRead : svcPhysMtrReadList) {
                String mtrLbCd = (String) svcPhysMtrRead.get("MTR_LB_CD");
                BigDecimal readMtrCnt = (BigDecimal) svcPhysMtrRead.get("READ_MTR_CNT");
                if (fmlaMtrLbCd.equals(mtrLbCd)) {
                    if (MTR_FMLA_TP.EQUALS.equals(mtrFmlaTpCd)) {
                        calcReadMtrCnt = readMtrCnt;
                    } else if (MTR_FMLA_TP.ADD.equals(mtrFmlaTpCd)) {
                        calcReadMtrCnt = calcReadMtrCnt.add(readMtrCnt);
                    } else if (MTR_FMLA_TP.SUBTRACT.equals(mtrFmlaTpCd)) {
                        calcReadMtrCnt = calcReadMtrCnt.subtract(readMtrCnt);
                    }
                    break;
                }
            }
        }
        return calcReadMtrCnt;
    }

    /**
     * getCmpsMtrFmla.
     * @param mtrGrpPk BigDecimal
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getCmpsMtrFmla(BigDecimal mtrGrpPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("mtrGrpPk", mtrGrpPk);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCmpsMtrFmla", ssmParam);
    }

    /**
     * getSvcPhysMtrRead.
     * @param svcMachMstrPk BigDecimal
     * @param svcTaskNum String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getSvcPhysMtrRead(BigDecimal svcMachMstrPk, String svcTaskNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("svcTaskNum", svcTaskNum);
        ssmParam.put("dsTestCopyClsCd", DS_TEST_COPY_CLS.TEST_COPY_OUT);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcPhysMtrRead", ssmParam);
    }
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]
}
