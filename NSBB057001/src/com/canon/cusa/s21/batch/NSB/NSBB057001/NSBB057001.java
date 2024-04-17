/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB057001;

import static com.canon.cusa.s21.batch.NSB.NSBB057001.constant.NSBB057001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.MDSE_TP_VAL_SETTMsg;
import business.db.MDSE_TP_VAL_SETTMsgArray;
import business.db.NSBI0570_01TMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_LIST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Finds Interface Parts Replacement Info Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 07/14/2016   Hitachi         Y.Osawa         Create          N/A
 * 10/25/2016   Hitachi         T.Mizuki        Update          QC#14988
 * 01/13/2017   Hitachi         K.Kitachi       Update          QC#17065
 * 03/03/2023   CITS            L.Mandanas      Update          QC#61067
 * 04/12/2023   CITS            L.Mandanas      Update          QC#61067
 * 06/23/2023   CITS            R.Jin           Update          QC#61067
 * 07/06/2023   CITS            R.Jin           Update          QC#61067
 * 12/22/2023   CITS            R.Jin           Update          QC#63380
 *</pre>
 */
public class NSBB057001 extends S21BatchMain {

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** FINDS_CLO_FSR_EVENT_CD */
    private String findsCloFsrEventCd = null;

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

    /** MDSE_TP_VAL_SETT */
    private MDSE_TP_VAL_SETTMsg mdseTpValSetMsg = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    // START 2023/03/03 L.Mandanas [QC#61067, ADD]
    /** Compass Country Code */
    private String cmpsCtryCd = null;

    /** FINDS_GLBL_CMPY_CD */
    private String cmpsGlblCmpyCd = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Normal Count */
    private int normalCount = 0;

    /** Compass Local Time Change */
    private String cmpsLocalTimeChng = null;

    /** Compass is CCI Flag */
    private String cmpsIsCsaFlag = null;
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]

    // START 2023/07/06 R.Jin [QC#61067, ADD]
    /** COMPASS Sender Company Code */
    private String cmpsSndrCmpyCd = null;

    /** CMPS_CRSSVC_EXCL_TECH_CD */
    private String crsSvcTechCd = null;

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
        new NSBB057001().executeBatch(NSBB057001.class.getSimpleName());
    }

    @Override
    protected void termRoutine() {
        // START 2023/03/03 L.Mandanas [QC#61067, MOD]
        //setTermState(this.termCd, this.totalCommitCount, 0);
        setTermState(this.termCd, this.normalCount, 0);
        // END 2023/03/03 L.Mandanas [QC#61067, MOD]
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
        this.findsCloFsrEventCd = getVarCharConst(VAR_CHAR_CONST_KEY_FINDS_CLO_FSR_EVENT_CD);
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
        // Get Var Char Const(CMPS_CTRY_CD)
        this.cmpsCtryCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_CTRY_CD);
        if (!ZYPCommonFunc.hasValue(this.cmpsCtryCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_CTRY_CD });
        }

        // Get Var Char Const(CMPS_GLBL_CMPY_CD)
        this.cmpsGlblCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD);
        if (!ZYPCommonFunc.hasValue(this.cmpsGlblCmpyCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD });
        }

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // END 2023/03/03 L.Mandanas [QC#61067, ADD]
        // START 2023/04/12 L.Mandanas [QC#61067, ADD]
        this.cmpsIsCsaFlag = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_CSA_FLG);
        // END 2023/04/12 L.Mandanas [QC#61067, ADD]

        // START 2023/07/06 R.Jin [QC#61067, ADD]
        // Get Var Char Const(CMPS_SNDR_CMPY_CD)
        this.cmpsSndrCmpyCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_SNDR_CMPY_CD);
        if (!ZYPCommonFunc.hasValue(this.cmpsSndrCmpyCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_SNDR_CMPY_CD });
        }

        this.cmpsLocalTimeChng = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_LOCAL_TIME_CHNG);
        if (!ZYPCommonFunc.hasValue(this.cmpsLocalTimeChng)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_LOCAL_TIME_CHNG });
        }

        // Get Var Char Const(CMPS_CRSSVC_EXCL_TECH_CD)
        this.crsSvcTechCd = getVarCharConst(VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_TECH_CD);
        if (!ZYPCommonFunc.hasValue(this.crsSvcTechCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_TECH_CD });
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
        // START 2023/06/23 R.Jin [QC#61067, DEL]
//        // get DsBizProcLog
//        this.dsBizProcLogTMsg = getDsBizProcLog();
        // END 2023/06/23 R.Jin [QC#61067, DEL]

        // get DrumInfo
        this.mdseTpValSetMsg = getMdseTpValSet();

        // insert interface data
        insertInterfaceData();

         // START 2023/06/23 R.Jin [QC#61067, DEL]
//        // update DsBizProcLog
//        if (this.dsBizProcLogTMsg != null) {
//            updateDsBizProcLog();
//        } else {
//            insertDsBizProcLog();
//        }
        // END 2023/06/23 R.Jin [QC#61067, DEL]
    }

    private void insertInterfaceData() {
        List<NSBI0570_01TMsg> inTMsgList = new ArrayList<NSBI0570_01TMsg>();
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
            stmt = this.ssmLLClient.createPreparedStatement("getFsrPartsInfo", setParam(), execParam);
            rsSet = stmt.executeQuery();

            int commitCount = 0;

            // Insert Interface Data
            while (rsSet.next()) {
                this.unitId++;
                inTMsgList.add(setNSBI0570_01(rsSet));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertInterfaceData(inTMsgList);
                    this.totalCommitCount += commitCount;
                    inTMsgList.clear();
                    commitCount = 0;
                }
                // START 2023/03/03 L.Mandanas [QC#61067, ADD]
                this.normalCount++;
                this.unitId++;
                // END 2023/03/03 L.Mandanas [QC#61067, ADD]
            }
            // START 2023/03/03 L.Mandanas [QC#61067, MOD]
            //if (this.unitId != this.totalCommitCount) {
            if (this.normalCount != this.totalCommitCount) {
            // END 2023/03/03 L.Mandanas [QC#61067, MOD]
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
        ssmParam.put("systemTs", this.procTs);
        if (this.dsBizProcLogTMsg != null) {
            ssmParam.put("lastProcTs", this.dsBizProcLogTMsg.dsBizLastProcTs.getValue());
        }

        // START 2023/03/03 L.Mandanas [QC#61067, ADD]
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("flg1", ZYPConstant.FLG_ON_1);
        ssmParam.put("flg0", ZYPConstant.FLG_OFF_0);
        ssmParam.put("fsrStsCd", SVC_TASK_STS.CLOSED);
        // START 2023/07/06 R.Jin [QC#61067, DEL]
//        ssmParam.put("startTs", getStartTs());
//        ssmParam.put("endTs", getEndTs());
        // END 2023/07/06 R.Jin [QC#61067, DEL]
        ssmParam.put("tsLen", TIME_STAMP_FORMAT.length());
        ssmParam.put("svcTaskStsCd", SVC_TASK_STS.CLOSED);
        ssmParam.put("targetDt", getTargetDate());
        ssmParam.put("mdseTpDrum", this.mdseTpValSetMsg.coaMdseTpCd.getValue());
        ssmParam.put("countryCd", this.cmpsCtryCd);
        ssmParam.put("prcListTpCd", PRC_LIST_TP.MSRP);
        ssmParam.put("substringStart", SUBSTRING_START);
        ssmParam.put("substringEnd", SUBSTRING_END);
        ssmParam.put("colon", COLON);
        ssmParam.put("hyphen", HYPHEN);
        ssmParam.put("dateFormat", CRAT_DT_FORMAT);
        ssmParam.put("timeFormat", CRAT_TM_FORMAT);
        ssmParam.put("int0", INT_0);
        // END 2023/03/03 L.Mandanas [QC#61067, ADD]
        // START 2023/07/06 R.Jin [QC#61067, ADD]
        ssmParam.put("crsSvcTechCd", this.crsSvcTechCd);
        ssmParam.put("cmpsDelerTp", this.cmpsDelerTp);
        ssmParam.put("crsSvcLobCd", this.crsSvcLobCd);
        ssmParam.put("slsDt", this.slsDt);
        ssmParam.put("extrDys", this.cmps_Extr_Days);
        // END 2023/07/06 R.Jin [QC#61067, ADD]
        // START 2023/12/22 R.Jin [QC#63380, ADD]
        ssmParam.put("svcDisptEventClose", SVC_DISPT_EVENT.CLOSE);
        // END   2023/12/22 R.Jin [QC#63380, ADD]
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

    private NSBI0570_01TMsg setNSBI0570_01(ResultSet rsSet) {
        NSBI0570_01TMsg tMsg = new NSBI0570_01TMsg();

        try {
            setValue(tMsg.interfaceId, this.intfcId);
            setValue(tMsg.transactionId, this.trxId);
            setValue(tMsg.segmentId, BigDecimal.ONE);
            setValue(tMsg.unitId, BigDecimal.valueOf(this.unitId));
            setValue(tMsg.seqNumber, BigDecimal.ONE);

            setValue(tMsg.serNum, rsSet.getString("SER_NUM"));
            // SELECT 2023/03/03 L.Mandanas [QC#61067, MOD]
            //setValue(tMsg.svcTaskNum, rsSet.getString("SVC_TASK_NUM"));
            setValue(tMsg.origCallTaskNum, rsSet.getString("ORIG_CALL_TASK_NUM"));
            // END 2023/03/03 L.Mandanas [QC#61067, MOD]
            setValue(tMsg.mdseCd, rsSet.getString("MDSE_CD"));
            setValue(tMsg.fsrVisitArvDt, rsSet.getString("FSR_VISIT_ARV_DT"));
            setValue(tMsg.fsrVisitArvTm, rsSet.getString("FSR_VISIT_ARV_TM"));
            // START 2017/01/13 K.Kitachi [QC#17065, MOD]
//            setValue(tMsg.mdseDescShortTxt, rsSet.getString("MDSE_DESC_SHORT_TXT"));
            setValue(tMsg.mdseDescShortTxt, removeDoubleQuote(rsSet.getString("MDSE_DESC_SHORT_TXT")));
            // END 2017/01/13 K.Kitachi [QC#17065, MOD]

            // START 2023/03/03 L.Mandanas [QC#61067, MOD]
            //String coaMdseTpCdTxt = this.mdseTpValSetMsg.coaMdseTpCd.getValue();
            //if (hasValue(coaMdseTpCdTxt) && coaMdseTpCdTxt.equals(rsSet.getString("COA_MDSE_TP_CD"))) {
            //    setValue(tMsg.drumChrgFlg, ZYPConstant.FLG_ON_Y);
            //} else {
            //    setValue(tMsg.drumChrgFlg, ZYPConstant.FLG_OFF_N);
            //}
            setValue(tMsg.drumChrgFlg, rsSet.getString("DRUM_CHRG_FLG"));
            // END 2023/03/03 L.Mandanas [QC#61067, MOD]

            setValue(tMsg.svcPrtQty, rsSet.getBigDecimal("SVC_PRT_QTY"));
            setValue(tMsg.thisMthTotStdCostAmt, rsSet.getBigDecimal("THIS_MTH_TOT_STD_COST_AMT"));
            // START 2023/03/03 L.Mandanas [QC#61067, MOD]
            //setValue(tMsg.fill1Txt_01, STR_0);
            //setValue(tMsg.fill1Txt_02, STR_0);
            //setValue(tMsg.ccyCd, rsSet.getString("COST_CCY_CD"));
            //setValue(tMsg.glblCmpyCd, this.findsGlblCmpyCd);
            //setValue(tMsg.cratDt, this.procDt);
            //setValue(tMsg.cratTm, this.procTm);
            setValue(tMsg.origCcyCtryCd, rsSet.getString("ORIG_CCY_CTRY_CD"));
            // START 2023/07/06 R.Jin [QC#61067, MOD]
//            setValue(tMsg.glblCmpyCd, this.cmpsGlblCmpyCd);
            setValue(tMsg.glblCmpyCd, this.cmpsSndrCmpyCd);
            // END 2023/07/06 R.Jin [QC#61067, MOD]
            setValue(tMsg.cratDt, rsSet.getString("CRAT_DT"));
            setValue(tMsg.cratTm, rsSet.getString("CRAT_TM"));
            // END 2023/03/03 L.Mandanas [QC#61067, MOD]
            setValue(tMsg.ifProcStsCd, IF_PROC_STS_CD);

            // START 2017/01/13 K.Kitachi [QC#17065, ADD]
            setValue(tMsg.funcUnitPrcAmt, rsSet.getBigDecimal("FUNC_UNIT_PRC_AMT"));
            if (ZYPConstant.FLG_ON_Y.equals(this.cmpsIsCsaFlag)) {
                setValue(tMsg.origWsUnitPrcAmt, rsSet.getBigDecimal("ORIG_WS_UNIT_PRC_AMT"));
            }

            if (ZYPConstant.FLG_ON_Y.equals(this.cmpsLocalTimeChng)) {
                convTimeZone(tMsg.fsrVisitArvDt, tMsg.fsrVisitArvTm, rsSet.getString("CTRY_CD"), rsSet.getString("POST_CD"));
            }
            // END 2017/01/13 K.Kitachi [QC#17065, ADD]

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tMsg;
    }

    private int insertInterfaceData(List<NSBI0570_01TMsg> inMsgLst) {
        NSBI0570_01TMsg[] inMsgArray;
        inMsgArray = new NSBI0570_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NSBM0164E, new String[] {inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
    }

    // START 2023/06/23 R.Jin [QC#61067, DEL]
//    private DS_BIZ_PROC_LOGTMsg getDsBizProcLog() {
//        DS_BIZ_PROC_LOGTMsg inMsg = new DS_BIZ_PROC_LOGTMsg();
//        inMsg.setSQLID("001");
//        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        inMsg.setConditionValue("procPgmId01", this.getClass().getSimpleName());
//        DS_BIZ_PROC_LOGTMsgArray tMsgAry = (DS_BIZ_PROC_LOGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//
//        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
//            return null;
//        }
//        return (DS_BIZ_PROC_LOGTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsgAry.no(0));
//    }
//
//    private void insertDsBizProcLog() {
//        this.dsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();
//
//        setValue(this.dsBizProcLogTMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(this.dsBizProcLogTMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
//        setValue(this.dsBizProcLogTMsg.procPgmId, this.getClass().getSimpleName());
//        setValue(this.dsBizProcLogTMsg.dsBizProcDt, this.procDt);
//        setValue(this.dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
//        setValue(this.dsBizProcLogTMsg.dsBizLastProcTs, this.procTs);
//        setValue(this.dsBizProcLogTMsg.dsBizLastUpdTs, this.procTs);
//
//        S21FastTBLAccessor.insert(this.dsBizProcLogTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(this.dsBizProcLogTMsg.getReturnCode())) {
//            String message = PROC_PGM_ID + " = " + this.dsBizProcLogTMsg.procPgmId.getValue();
//            throw new S21AbendException(NSBM0121E, new String[] {this.dsBizProcLogTMsg.getTableName(), message });
//        }
//    }
//
//    private void updateDsBizProcLog() {
//        setValue(this.dsBizProcLogTMsg.dsBizProcDt, this.procDt);
//        setValue(this.dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
//        setValue(this.dsBizProcLogTMsg.dsBizLastProcTs, this.procTs);
//        setValue(this.dsBizProcLogTMsg.dsBizLastUpdTs, this.procTs);
//
//        S21FastTBLAccessor.update(this.dsBizProcLogTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(this.dsBizProcLogTMsg.getReturnCode())) {
//            String message = DS_BIZ_PROC_LOG_PK + " = " + this.dsBizProcLogTMsg.dsBizProcLogPk.getValue();
//            throw new S21AbendException(NSBM0120E, new String[] {this.dsBizProcLogTMsg.getTableName(), message });
//        }
//    }
    // END 2023/06/23 R.Jin [QC#61067, DEL]

    private MDSE_TP_VAL_SETTMsg getMdseTpValSet() {
        MDSE_TP_VAL_SETTMsg inMsg = new MDSE_TP_VAL_SETTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("mdseTpCtxTpCd01", MDSE_TP_CTX_TP.DRUM_ITEM);

        MDSE_TP_VAL_SETTMsgArray tMsgAry = (MDSE_TP_VAL_SETTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgAry == null || tMsgAry.getValidCount() == 0) {
            return null;
        }
        return tMsgAry.no(0);
    }

    // START 2017/01/13 K.Kitachi [QC#17065, ADD]
    private String removeDoubleQuote(String inVal) {
        if (!hasValue(inVal)) {
            return inVal;
        }
        return ZYPCommonFunc.replaceAll(inVal, "\"", "");
    }
    // END 2017/01/13 K.Kitachi [QC#17065, ADD]

    // START 2023/07/06 R.Jin [QC#61067, DEL]
    // START 2023/03/03 L.Mandanas [QC#61067, ADD]
//    /**
//     * getStartTs.
//     * @return String
//     */
//    private String getStartTs() {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParam.put("batProcPgmId", this.getClass().getSimpleName());
//        ssmParam.put("slsDt", this.slsDt);
//        ssmParam.put("tsLen", TIME_STAMP_FORMAT.length());
//        ssmParam.put("dtLen", DATE_FORMAT.length());
//        ssmParam.put("int1", INT_1);
//        ssmParam.put("str0", STR_0);
//        String startDt = (String) this.ssmBatchClient.queryObject("getStartDt", ssmParam);
//        if (!hasValue(startDt)) {
//            return getTargetDate();
//        }
//        ssmParam.put("startDt", startDt + PERCENT);
//        return (String) this.ssmBatchClient.queryObject("getStartTs", ssmParam);
//    }
//
//    /**
//     * getEndTs.
//     * @return String
//     */
//    private String getEndTs() {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParam.put("batProcPgmId", this.getClass().getSimpleName());
//        ssmParam.put("slsDt", this.slsDt);
//        ssmParam.put("tsLen", TIME_STAMP_FORMAT.length());
//        ssmParam.put("str0", STR_0);
//        return (String) this.ssmBatchClient.queryObject("getEndTs", ssmParam);
//    }
    // END 2023/07/06 R.Jin [QC#61067, DEL]

    /**
     * Subtract Sales Date by one day.
     * @return String
     */
    private String getTargetDate() {
        return ZYPDateUtil.addDays(this.slsDt, -1);
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
        if (!hasValue(convTs) || convTs.length() < INT_14) {
            return;
        }
        setValue(dtItem, convTs.substring(0, INT_8));
        setValue(tmItem, convTs.substring(INT_8, INT_14));
    }
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]
}
