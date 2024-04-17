/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB053001;

import static com.canon.cusa.s21.batch.NSB.NSBB053001.constant.NSBB053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

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
import business.db.NSBI2280_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * BEI Call Number Interface Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/04   Hitachi         T.Mizuki        Create          N/A
 * 2017/01/19   Hitachi         K.Kitachi       Update          QC#15949
 *</pre>
 */

public class NSBB053001 extends S21BatchMain {

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Unit ID */
    private int unitId;

    /** Interface Id */
    private String intfcId = null;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Current System Timestamp */
    private String currentSystemTs = null;

    /** Sales Date */
    private String salesDate = null;

    /** DS_BIZ_PROC_LOG */
    private DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg = null;

    @Override
    protected void initRoutine() {

        // Get Global Company Code
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
        this.salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSZM0392E, new String[] {SLS_DATE });
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.unitId = 0;
        this.normalCount = 0;
        this.errorCount = 0;
        this.trxAccess = new S21TransactionTableAccessor();
        this.trxId = this.trxAccess.getNextTransactionId();
        this.currentSystemTs = ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS);
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB053001().executeBatch(NSBB053001.class.getSimpleName());
    }

    private void doProcess() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Get DsBizProcLog
            this.dsBizProcLogTMsg = getDsBizProcLog();

            if (this.dsBizProcLogTMsg != null) {
                // check salesDate
                String nextMMdd = setNextMonthDate(dsBizProcLogTMsg.dsBizLastProcTs.getValue().substring(0, NUM_6));
                if (salesDate.compareTo(nextMMdd) < 0) {
                    return;
                }
            }
            // set execParam
            S21SsmExecutionParameter execParam = setExecParam();
            // set queryParam
            Map<String, Object> queryParam = setQueryParam();
            // Get TargetInfo
            ps = this.ssmLLClient.createPreparedStatement("getTaskInfo", queryParam, execParam);
            rs = ps.executeQuery();
            List<NSBI2280_01TMsg> hdrTMsgList = new ArrayList<NSBI2280_01TMsg>();
            while (rs.next()) {
                this.unitId++;

                // sum READ_MTR_CNT
                Integer sumCnt = getSumCnt(rs.getString("SVC_MACH_MSTR_PK"), rs.getString("FSR_NUM"), rs.getString("FSR_VISIT_NUM"));
                hdrTMsgList.add(setNSBI2280_01(rs, sumCnt));

                if (MAX_FETCH_SIZE == hdrTMsgList.size()) {
                    normalCount += insertData(hdrTMsgList);
                    hdrTMsgList.clear();
                }
            }
            if (0 < hdrTMsgList.size()) {
                normalCount += insertData(hdrTMsgList);
            }

            if (0 != normalCount) {
                // insert INTERFACE_TRANSACTION
                trxAccess.createIntegrationRecordForBatch(this.intfcId, this.trxId);
            }

            if (this.dsBizProcLogTMsg != null) {
                updateDsBizProcLog();
            } else {
                insertDsBizProcLog();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private S21SsmExecutionParameter setExecParam() {

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        return execParam;
    }

    private Map<String, Object> setQueryParam() {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("fsrStsCd", SVC_TASK_STS.CLOSED);
        if (this.dsBizProcLogTMsg != null) {
            queryParam.put("cloDt", dsBizProcLogTMsg.dsBizLastProcTs.getValue().substring(0, NUM_8));
        } else {
            queryParam.put("curDt", this.currentSystemTs.substring(0, NUM_8));
        }
        // Get PSN Tp Cd
        String psnTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_BEI_PSN_TP, this.glblCmpyCd);
        if (ZYPCommonFunc.hasValue(psnTpCd)) {
            String[] psnTpCdList = psnTpCd.split(COMMA);
            queryParam.put("psnTpCdList", psnTpCdList);
        }

        return queryParam;
    }

    private String setNextMonthDate(String yyyymm) {

        int yyyy = Integer.parseInt(yyyymm.substring(0, NUM_4));
        int mm = Integer.parseInt(yyyymm.substring(NUM_4, NUM_6));
        String nextmm = null;
        String nextyyyy = null;
        if (MONTH_DEC == mm) {
            nextmm = "01";
            nextyyyy = String.valueOf(yyyy + 1);
        } else {
            nextmm = String.valueOf(mm + 1);
            nextyyyy = String.valueOf(yyyy);
        }
        String dd = ZYPCodeDataUtil.getVarCharConstValue("BEI_PROC_DAY", glblCmpyCd);

        StringBuffer sb = new StringBuffer();
        sb.append(nextyyyy);
        if (nextmm.length() == 1) {
            sb.append("0");
        }
        sb.append(nextmm);
        if (dd.length() == 1) {
            sb.append("0");
        }
        sb.append(dd);

        return sb.toString();
    }

    private NSBI2280_01TMsg setNSBI2280_01(ResultSet fsrData, int sumCnt) {

        NSBI2280_01TMsg setParamMsg = new NSBI2280_01TMsg();
        try {
            String svcRsvTxt = " ";

            // START 2017/01/19 K.Kitachi [QC#15949, MOD]
            // add SVC_TASK_NUM
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("SVC_TASK_NUM"), NUM_15, LEFT, SPACE);
            // add MDSE_DESC_SHORT_TXT
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("MDSE_DESC_SHORT_TXT"), NUM_30, LEFT, SPACE);
            // add SER_NUM
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("SER_NUM"), NUM_18, LEFT, SPACE);
            // add SVC_CALL_INCDT_DT
            svcRsvTxt = setConcatVal(svcRsvTxt, convertDateFormat(fsrData.getString("SVC_CALL_INCDT_DT")), NUM_8, LEFT, SPACE);
            // add SVC_CALL_INCDT_TM
            svcRsvTxt = setConcatVal(svcRsvTxt, S21StringUtil.subStringByLength(fsrData.getString("SVC_CALL_INCDT_TM"), 0, NUM_4), NUM_4, LEFT, SPACE);
            // add SVC_LBOR_TM_NUM
            svcRsvTxt = setConcatVal(svcRsvTxt, setTM(fsrData.getInt("SVC_LBOR_TM_NUM")), NUM_4, LEFT, SPACE);
            // add FSR_VISIT_ARV_TM
            svcRsvTxt = setConcatVal(svcRsvTxt, S21StringUtil.subStringByLength(fsrData.getString("FSR_VISIT_ARV_TM"), 0, NUM_4), NUM_4, LEFT, SPACE);
            // add FSR_VISIT_CLO_TM
            svcRsvTxt = setConcatVal(svcRsvTxt, S21StringUtil.subStringByLength(fsrData.getString("FSR_VISIT_CLO_TM"), 0, NUM_4), NUM_4, LEFT, SPACE);
            // add DS_SVC_CALL_TP_CD
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("DS_SVC_CALL_TP_CD"), NUM_5, LEFT, SPACE);
            // add SVC_PBLM_TP_CD
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("SVC_PBLM_TP_CD"), NUM_4, LEFT, SPACE);
            // add SVC_PBLM_LOC_TP_CD
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("SVC_PBLM_LOC_TP_CD"), NUM_4, LEFT, SPACE);
            // add SVC_PBLM_RSN_TP_CD
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("SVC_PBLM_RSN_TP_CD"), NUM_4, LEFT, SPACE);
            // add SVC_PBLM_CRCT_TP_CD
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("SVC_PBLM_CRCT_TP_CD"), NUM_4, LEFT, SPACE);
            // add FSR_VISIT_DISPT_DT
            svcRsvTxt = setConcatVal(svcRsvTxt, convertDateFormat(fsrData.getString("FSR_VISIT_DISPT_DT")), NUM_8, LEFT, SPACE);
            // add FSR_VISIT_DISPT_TM
            svcRsvTxt = setConcatVal(svcRsvTxt, S21StringUtil.subStringByLength(fsrData.getString("FSR_VISIT_DISPT_TM"), 0, NUM_4), NUM_4, LEFT, SPACE);
            // add FSR_VISIT_CLO_DT
            svcRsvTxt = setConcatVal(svcRsvTxt, convertDateFormat(fsrData.getString("FSR_VISIT_CLO_DT")), NUM_8, LEFT, SPACE);
            // add SUM(READ_MTR_CNT)
            svcRsvTxt = setConcatVal(svcRsvTxt, String.valueOf(sumCnt), NUM_10, LEFT, ZERO);
            // add Blank(10)
            svcRsvTxt = setConcatVal(svcRsvTxt, SPACE, NUM_10, LEFT, SPACE);
            // add Blank(8)
            svcRsvTxt = setConcatVal(svcRsvTxt, SPACE, NUM_8, LEFT, SPACE);
            // add Blank(4)
            svcRsvTxt = setConcatVal(svcRsvTxt, SPACE, NUM_4, LEFT, SPACE);
            // add Blank(4)
            svcRsvTxt = setConcatVal(svcRsvTxt, SPACE, NUM_4, LEFT, SPACE);
            // add Blank(1)
            svcRsvTxt = setConcatVal(svcRsvTxt, SPACE, 1, LEFT, SPACE);
            // add Blank(1)
            svcRsvTxt = setConcatVal(svcRsvTxt, SPACE, 1, LEFT, SPACE);
            // add TECH_CD
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("TECH_CD"), NUM_9, LEFT, SPACE);
            // add LOC_NUM
            svcRsvTxt = setConcatVal(svcRsvTxt, fsrData.getString("LOC_NUM"), NUM_6, LEFT, SPACE);
            // add Blank(10)
            svcRsvTxt = setConcatVal(svcRsvTxt, SPACE, NUM_10, LEFT, SPACE);
            // END 2017/01/19 K.Kitachi [QC#15949, MOD]

            // Common
            setValue(setParamMsg.interfaceId, intfcId);
            setValue(setParamMsg.transactionId, trxId);
            setValue(setParamMsg.segmentId, BigDecimal.ONE);
            setValue(setParamMsg.unitId, BigDecimal.valueOf(this.unitId));
            setValue(setParamMsg.seqNumber, BigDecimal.ONE);
            setValue(setParamMsg.svcRsvdTxt_01, svcRsvTxt);

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return setParamMsg;
    }

    private int getSumCnt(String svcMachMstrPk, String fsrNum, String fsrVisitNum) {
        Map<String, Object> querySumParam = new HashMap<String, Object>();
        querySumParam.put("glblCmpyCd", this.glblCmpyCd);
        querySumParam.put("svcMachMstrPk", svcMachMstrPk);
        querySumParam.put("fsrNum", fsrNum);
        querySumParam.put("fsrVisitNum", fsrVisitNum);

        Integer sumCnt = (Integer) this.ssmBatchClient.queryObject("getSumCnt", querySumParam);

        if (sumCnt == null) {
            sumCnt = 0;
        }
        return sumCnt;
    }

    private int insertData(List<NSBI2280_01TMsg> inTMsgList) {
        NSBI2280_01TMsg[] inMsgArray;
        inMsgArray = new NSBI2280_01TMsg[inTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(inTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSBM0032E, new String[] {inMsgArray[0].getTableName() });
        }
        commit();
        return insertCount;
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

        dsBizProcLogTMsg = new DS_BIZ_PROC_LOGTMsg();
        setValue(dsBizProcLogTMsg.glblCmpyCd, glblCmpyCd);
        setValue(dsBizProcLogTMsg.dsBizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_BIZ_PROC_LOG_SQ));
        setValue(dsBizProcLogTMsg.procPgmId, PROGRAM_ID);
        setValue(dsBizProcLogTMsg.dsBizProcDt, salesDate);
        setValue(dsBizProcLogTMsg.dsBizProcFlg, ZYPConstant.FLG_ON_Y);
        setValue(dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTs);
        setValue(dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTs);

        S21FastTBLAccessor.insert(dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsBizProcLogTMsg.getReturnCode())) {
            throw new S21AbendException(S21MessageFunc.clspGetMessage(NSBM0164E, new String[] {TBL_NM_DS_BIZ_PROC_LOG }));
        }
    }

    private void updateDsBizProcLog() {

        setValue(dsBizProcLogTMsg.dsBizProcDt, salesDate);
        setValue(dsBizProcLogTMsg.dsBizLastProcTs, this.currentSystemTs);
        setValue(dsBizProcLogTMsg.dsBizLastUpdTs, this.currentSystemTs);

        S21FastTBLAccessor.update(dsBizProcLogTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsBizProcLogTMsg.getReturnCode())) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(S21MessageFunc.clspGetMessage(NSBM0163E, new String[] {TBL_NM_DS_BIZ_PROC_LOG }));
        }
    }

    private String setConcatVal(String beiRsvTxt, String addStr, int length, String padPos, String padStr) {

        // START 2017/01/19 K.Kitachi [QC#15949, MOD]
        if (!ZYPCommonFunc.hasValue(addStr) || addStr.equals(SPACE)) {
        // END 2017/01/19 K.Kitachi [QC#15949, MOD]
            StringBuffer sb = new StringBuffer();
            sb.append(beiRsvTxt);
            sb.append(PIPE);
            sb.append(ZYPCommonFunc.paddingSpace(null, false, length));
            return sb.toString();
        }
        if (padPos.equals(RIGHT)) {
            beiRsvTxt = beiRsvTxt.concat(PIPE).concat(ZYPCommonFunc.rightPad(addStr, length, padStr));
        } else if (padPos.equals(LEFT)) {
            beiRsvTxt = beiRsvTxt.concat(PIPE).concat(ZYPCommonFunc.leftPad(addStr, length, padStr));
        } else {
            return beiRsvTxt.concat(PIPE).concat(addStr);
        }
        return beiRsvTxt;
    }

    private String setTM(int tmNum) {

        String hh = String.valueOf(tmNum / NUM_60);
        String mm = String.valueOf(tmNum % NUM_60);

        StringBuffer sb = new StringBuffer();
        if (hh.length() == 1) {
            sb.append("0");
        }
        sb.append(hh);
        if (mm.length() == 1) {
            sb.append("0");
        }
        sb.append(mm);

        return sb.toString();
    }

    // START 2017/01/19 K.Kitachi [QC#15949, ADD]
    private String convertDateFormat(String date) {
        if (!ZYPCommonFunc.hasValue(date)) {
            return null;
        }
        return S21CalendarUtil.convertFormat(date, ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.TYPE_MMDDYY, ZYPDateUtil.SEPARATOR_SLASH);
    }
    // END 2017/01/19 K.Kitachi [QC#15949, ADD]
}
