/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB052001;

import static com.canon.cusa.s21.batch.NSB.NSBB052001.constant.NSBB052001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.NSBI2270_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
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
 * BEI Serial Number Interface Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 2016/07/01   Hitachi         Y.Osawa          CREATE          NEW
 *</pre>
 */
public class NSBB052001 extends S21BatchMain {

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** BEI Proc Day */
    private String beiProcDay = null;

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

    /** Error Count */
    private int errorCount = 0;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NSBB052001().executeBatch(NSBB052001.class.getSimpleName());

    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);

    }

    @Override
    protected void initRoutine() {
        // blank check(Global Company Code)
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {GLBL_CMPY_CD });
        }

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(this.intfcId)) {
            throw new S21AbendException(ZZZM9025E, new String[] {INTERFACE_ID });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        if (!hasValue(this.slsDt)) {
            throw new S21AbendException(NSZM0392E, new String[] {SLS_DATE });
        }

        // Get BEI Proc Day
        this.beiProcDay = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_BEI_PROC_DAY, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.beiProcDay)) {
            throw new S21AbendException(NSBM0069E, new String[] {VAR_CHAR_CONST_KEY_BEI_PROC_DAY });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);

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

        // Check Process Date
        if (this.dsBizProcLogTMsg != null && this.slsDt.compareTo(setNextDate(this.dsBizProcLogTMsg.dsBizLastUpdTs.getValue().substring(0, VAL_6), this.beiProcDay)) < 0) {
            return;
        }

        List<NSBI2270_01TMsg> inTMsgList = new ArrayList<NSBI2270_01TMsg>();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        // Search Target Data
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("fsrStsCd", SVC_TASK_STS.CLOSED);

        // Get PSN Tp Cd
        String psnTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_BEI_PSN_TP, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(psnTpCd)) {
            String[] psnTpCdList = psnTpCd.split(COMMA);
            ssmParam.put("psnTpCdList", psnTpCdList);
        }

        if (this.dsBizProcLogTMsg != null) {
            ssmParam.put("cloDt", this.dsBizProcLogTMsg.dsBizLastProcTs.getValue().substring(0, VAL_8));
        }

        try {

            stmt = this.ssmLLClient.createPreparedStatement("getTargetFsrInfo", ssmParam, execParam);
            rsSet = stmt.executeQuery();

            int commitCount = 0;

            // Insert Interface Data
            while (rsSet.next()) {

                inTMsgList.add(setNSBI2270_01(rsSet));

                if (this.commitNumber == inTMsgList.size()) {
                    commitCount = insertInterfaceData(inTMsgList);
                    inTMsgList = new ArrayList<NSBI2270_01TMsg>();
                    this.totalCommitCount += commitCount;
                    commitCount = 0;
                }
                this.normalCount++;
                this.unitId++;
            }

            if (this.normalCount != this.totalCommitCount) {
                insertInterfaceData(inTMsgList);
            }

            // insert INTERFACE_TRANSACTION
            this.trxAccess.createIntegrationRecordForBatch(this.intfcId, this.trxId);

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

    private void insertDsBizProcLog() {

        this.dsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();

        setValue(this.dsBizProcLogTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(this.dsBizProcLogTMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
        setValue(this.dsBizProcLogTMsg.procPgmId, PROGRAM_ID);
        setValue(this.dsBizProcLogTMsg.dsBizProcDt, this.slsDt);
        setValue(this.dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        setValue(this.dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTs);
        setValue(this.dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTs);

        S21FastTBLAccessor.insert(this.dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(this.dsBizProcLogTMsg.getReturnCode())) {
            throw new S21AbendException(NSBM0032E, new String[] {"DS_BIZ_PROC_LOG" });
        }
    }

    private void updateDsBizProcLog() {

        setValue(this.dsBizProcLogTMsg.dsBizProcDt, this.slsDt);
        setValue(this.dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTs);
        setValue(this.dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTs);

        S21FastTBLAccessor.update(this.dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(this.dsBizProcLogTMsg.getReturnCode())) {
            throw new S21AbendException(NSAM0470E, new String[] {"DS_BIZ_PROC_LOG" });
        }
    }

    private NSBI2270_01TMsg setNSBI2270_01(ResultSet fsrData) {

        NSBI2270_01TMsg setParamMsg = new NSBI2270_01TMsg();
        try {
            String svcRsvdTxt = "";

            // add Serial Number
            svcRsvdTxt = ZYPCommonFunc.leftPad(fsrData.getString("SER_NUM"), LEN_18, SPACE);
            // add Machine Description (model desc)
            svcRsvdTxt = setConcatVal(svcRsvdTxt, fsrData.getString("MDSE_DESC_SHORT_TXT"), LEN_32, RIGHT, SPACE);
            // add Initial Meter Reading
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_10, LEFT, SPACE);
            // add Date Sold / Rented
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_8, LEFT, SPACE);
            // add Model Number
            svcRsvdTxt = setConcatVal(svcRsvdTxt, fsrData.getString("MDSE_DESC_SHORT_TXT"), LEN_30, LEFT, SPACE);
            // add Source Code
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_1, LEFT, SPACE);
            // add Meter Reading Last Service Call
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_10, LEFT, SPACE);
            // add NULL FIELD
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_1, LEFT, SPACE);
            // add Date of Last Service Call
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_8, LEFT, SPACE);
            // add Branch ID or Customer Number
            svcRsvdTxt = setConcatVal(svcRsvdTxt, fsrData.getString("LOC_NUM"), LEN_6, LEFT, SPACE);
            // add Program Type
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_10, LEFT, SPACE);
            // add Product Code
            svcRsvdTxt = setConcatVal(svcRsvdTxt, fsrData.getString("MDSE_CD"), LEN_6, LEFT, SPACE);
            // add Sales Rep ID
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_6, LEFT, SPACE);
            // add Connectivity Code
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_2, LEFT, SPACE);
            // add Zip Code
            svcRsvdTxt = setConcatVal(svcRsvdTxt, null, LEN_10, LEFT, SPACE);

            // Common
            setValue(setParamMsg.interfaceId, this.intfcId);
            setValue(setParamMsg.transactionId, this.trxId);
            setValue(setParamMsg.segmentId, BigDecimal.ONE);
            setValue(setParamMsg.unitId, BigDecimal.valueOf(this.unitId));
            setValue(setParamMsg.seqNumber, BigDecimal.ONE);
            setValue(setParamMsg.svcRsvdTxt_01, svcRsvdTxt);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return setParamMsg;
    }

    private String setNextDate(String yyyymm, String dd) {

        int yyyy = Integer.parseInt(yyyymm.substring(0, VAL_4));
        int mm = Integer.parseInt(yyyymm.substring(VAL_4, VAL_6));
        String nextmm = null;
        String nextyyyy = null;
        if (VAL_12 == mm) {
            nextmm = "01";
            nextyyyy = String.valueOf(yyyy + 1);
        } else {
            nextmm = String.valueOf(mm + 1);
            nextyyyy = String.valueOf(yyyy);
        }

        StringBuffer sb = new StringBuffer();
        sb.append(nextyyyy);
        sb.append(ZYPCommonFunc.leftPad(nextmm, 2, "0"));
        sb.append(dd);

        return sb.toString();
    }

    private String setConcatVal(String beiRsvTxt, String addStr, int length, String padPos, String padStr) {

        if (padPos.equals(RIGHT)) {
            beiRsvTxt = beiRsvTxt.concat(PIPE).concat(ZYPCommonFunc.rightPad(addStr, length, padStr));
        } else if (padPos.equals(LEFT)) {
            beiRsvTxt = beiRsvTxt.concat(PIPE).concat(ZYPCommonFunc.leftPad(addStr, length, padStr));
        } else {
            return beiRsvTxt.concat(PIPE).concat(addStr);
        }
        return beiRsvTxt;
    }

    private int insertInterfaceData(List<NSBI2270_01TMsg> inMsgLst) {
        NSBI2270_01TMsg[] inMsgArray;
        inMsgArray = new NSBI2270_01TMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NSBM0032E, new String[] {inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
    }
}
