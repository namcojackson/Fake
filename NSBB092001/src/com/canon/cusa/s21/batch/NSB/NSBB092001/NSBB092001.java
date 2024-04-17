/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB092001;

import static com.canon.cusa.s21.batch.NSB.NSBB092001.constant.NSBB092001Constant.*;
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

import parts.dbcommon.EZDTBLAccessor;

import business.db.CITS_DBRF_STAGETMsg;
import business.db.CITS_SVC_TASK_UPD_STAGETMsg;
import business.db.DS_BIZ_PROC_LOGTMsg;
import business.db.DS_BIZ_PROC_LOGTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMOTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CITS_PLSFT_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CITS_PLSFT_UPD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TM_EVENT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Update Task to People Soft Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 07/20/2016   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBB092001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Total Process Count */
    private int totalProcCount = 0;

    /** Process Date */
    private String procDt;

    /** Process Time Stamp */
    private String procTs;

    /** Target Count */
    private int targetCount = 0;

    /** Process Unit Count */
    private int procUnitCount;

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** DS_BIZ_PROC_LOG */
    private DS_BIZ_PROC_LOGTMsg dsBizProcLogTMsg = null;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NSBB092001().executeBatch(NSBB092001.class.getSimpleName());
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalProcCount, 0);
    }

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {GLBL_CMPY_CD });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        if (!hasValue(this.slsDt)) {
            throw new S21AbendException(NSBM0032E, new String[] {SLS_DT });
        }

        // Get System Timestamp
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
        this.procDt = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);

        // Set Process Unit Count
        this.procUnitCount = MAX_PROC_UNIT_NUMBER;

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        // get DsBizProcLog
        this.dsBizProcLogTMsg = getDsBizProcLog();

        // insert data for call cancel
        insertCitsStageData(MODE_CANCEL);

        // insert data for assign
        insertCitsStageData(MODE_ASSIGN);

        // insert data for update notes
        insertCitsStageData(MODE_ADD_NOTES);

        // insert data for close
        insertCitsStageData(MODE_CLOSE);

        // insert data for follow up call creation
        insertCitsStageData(MODE_FOLLE_UP_CALL);

        // update DsBizProcLog
        if (this.dsBizProcLogTMsg != null) {
            updateDsBizProcLog();
        } else {
            insertDsBizProcLog();
        }
    }

    private void insertCitsStageData(String str) {
        List<CITS_SVC_TASK_UPD_STAGETMsg> inTMsgList = new ArrayList<CITS_SVC_TASK_UPD_STAGETMsg>();
        List<CITS_DBRF_STAGETMsg> inDbrfTMsgList = new ArrayList<CITS_DBRF_STAGETMsg>();
        SVC_MEMOTMsg svcMemoTMsg = new SVC_MEMOTMsg();
        List<Map<String, Object>> svcMemoList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> dbrfList = null;
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
            stmt = this.ssmLLClient.createPreparedStatement("getSvcTaskInfo", setParam(str), execParam);
            rsSet = stmt.executeQuery();

            int procCount = 0;

            // Insert Interface Data
            while (rsSet.next()) {

                // get SVC_MEMO info
                if (str.equals(MODE_CANCEL)) {
                    svcMemoTMsg = getSvcMemoForCancel(rsSet.getString("SVC_TASK_NUM"));
                } else if (str.equals(MODE_ADD_NOTES)) {
                    svcMemoList = getSvcMemoForAddNotes(rsSet.getString("SVC_TASK_NUM"));
                }

                // set CITS_SVC_TASK_UPD_STAGETMsg
                if (str.equals(MODE_ADD_NOTES)) {
                    for (Map<String, Object> svcMemoMap : svcMemoList) {
                        inTMsgList.add(setCitsSvcTaskUpdStage(rsSet, str, null, svcMemoMap));
                        this.targetCount++;
                    }
                } else {
                    inTMsgList.add(setCitsSvcTaskUpdStage(rsSet, str, svcMemoTMsg, null));
                    this.targetCount++;
                }

                // set CITS_DBRF_STAGE info
                if (str.equals(MODE_CLOSE)) {
                    dbrfList = getLaborPartsUsageInfo(rsSet.getString("FSR_NUM"), rsSet.getString("SVC_TASK_NUM"));
                    for (Map<String, Object> dbrfMap : dbrfList) {
                        inDbrfTMsgList.add(setCitsDbrfStage(dbrfMap));
                    }
                }

                if (this.procUnitCount <= inTMsgList.size()) {
                    // insert CITS_SVC_TASK_UPD_STAGE Info
                    procCount = insertCitsStageData(inTMsgList);
                    // insert CITS_DBRF_STAGE Info
                    insertCitsDbrfStageData(inDbrfTMsgList);
                    this.totalProcCount += procCount;
                    inTMsgList.clear();
                    inDbrfTMsgList.clear();
                    procCount = 0;
                }
            }

            if (this.targetCount != this.totalProcCount) {
                // insert CITS_SVC_TASK_UPD_STAGE Info
                procCount = insertCitsStageData(inTMsgList);
                // insert CITS_DBRF_STAGE Info
                insertCitsDbrfStageData(inDbrfTMsgList);
                this.totalProcCount += procCount;
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    private SVC_MEMOTMsg getSvcMemoForCancel(String svcTaskNum) {
        SVC_MEMOTMsg inTMsg = new SVC_MEMOTMsg();
        inTMsg.setSQLID("011");
        inTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inTMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        inTMsg.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.TASK_CANCEL_REASON);
        SVC_MEMOTMsgArray outTMsgArray = (SVC_MEMOTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
        if (outTMsgArray.getValidCount() > 0) {
            return outTMsgArray.no(0);
        }
        return null;
    }

    private List<Map<String, Object>> getSvcMemoForAddNotes(String svcTaskNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcTaskNum", svcTaskNum);
        if (this.dsBizProcLogTMsg != null) {
            ssmParam.put("dsBizLastProcTs", this.dsBizProcLogTMsg.dsBizLastProcTs.getValue());
        }
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcMemoInfoForAddNotes", ssmParam);
    }

    private List<Map<String, Object>> getLaborPartsUsageInfo(String fsrNum, String svcTaskNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("fsrNum", fsrNum);
        ssmParam.put("svcTaskNum", svcTaskNum);
        ssmParam.put("svcTmEventCd", SVC_TM_EVENT.LABOR);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getLaborPartsUsageInfo", ssmParam);
    }

    private Map<String, Object> setParam(String str) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcCallSrcTpCd", SVC_CALL_SRC_TP.CITS);
        List<String> svcDisptEventCdList = new ArrayList<String>();
        if (str.equals(MODE_CANCEL)) {
            svcDisptEventCdList.add(SVC_DISPT_EVENT.CANCEL);
            ssmParam.put("isCancelCase", ZYPConstant.FLG_ON_Y);
            ssmParam.put("citsPlsftUpdTpCd", CITS_PLSFT_UPD_TP.CALL_CANCELLATION_RESULT_FROM_CITS);
            ssmParam.put("fldSvcProcCd", CITS_PLSFT_PROC.ERROR);
        } else if (str.equals(MODE_ASSIGN)) {
            svcDisptEventCdList.add(SVC_DISPT_EVENT.ASSIGN);
            ssmParam.put("isAssignCase", ZYPConstant.FLG_ON_Y);
            ssmParam.put("citsPlsftUpdTpCd", CITS_PLSFT_UPD_TP.TECH_ASSIGNMENT_FROM_S21);
        } else if (str.equals(MODE_ADD_NOTES)) {
            svcDisptEventCdList.add(SVC_DISPT_EVENT.UPDATE_NOTES);
        } else if (str.equals(MODE_CLOSE)) {
            svcDisptEventCdList.add(SVC_DISPT_EVENT.CLOSE);
            svcDisptEventCdList.add(SVC_DISPT_EVENT.FORCE_CLOSE);
        } else if (str.equals(MODE_FOLLE_UP_CALL)) {
            svcDisptEventCdList.add(SVC_DISPT_EVENT.CREATE_FOLLOW_UP_TASK);
        }
        ssmParam.put("svcDisptEventCdList", svcDisptEventCdList);
        if (this.dsBizProcLogTMsg != null) {
            ssmParam.put("dsBizLastProcTs", this.dsBizProcLogTMsg.dsBizLastProcTs.getValue());
        }
        return ssmParam;
    }

    private CITS_SVC_TASK_UPD_STAGETMsg setCitsSvcTaskUpdStage(ResultSet rsSet, String str, SVC_MEMOTMsg svcMemoTMsg, Map<String, Object> svcMemoMap) {
        CITS_SVC_TASK_UPD_STAGETMsg tMsg = new CITS_SVC_TASK_UPD_STAGETMsg();

        try {
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.citsSvcTaskUpdStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CITS_SVC_TASK_UPD_STAGE_SQ));
            setValue(tMsg.svcTaskNum, rsSet.getString("SVC_TASK_NUM"));
            setValue(tMsg.plsftTaskNum, rsSet.getString("CUST_CSE_NUM"));
            if (str.equals(MODE_CANCEL)) {
                setValue(tMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.CALL_CANCELLATION_FROM_S21);
                setValue(tMsg.svcTaskStsDescTxt, rsSet.getString("SVC_TASK_STS_DESC_TXT"));
                if (svcMemoTMsg != null) {
                    setValue(tMsg.svcCmntTxt, svcMemoTMsg.svcCmntTxt);
                }
            } else if (str.equals(MODE_ASSIGN)) {
                setValue(tMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.TECH_ASSIGNMENT_FROM_S21);
                setValue(tMsg.svcTaskStsDescTxt, rsSet.getString("SVC_TASK_STS_DESC_TXT"));
                setValue(tMsg.techNm, rsSet.getString("TECH_NM"));
                setValue(tMsg.fsrVisitEtaTs, setTs(rsSet.getString("FSR_VISIT_ETA_TS")));
            } else if (str.equals(MODE_ADD_NOTES)) {
                setValue(tMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.ADD_NOTES_FROM_S21);
                setValue(tMsg.svcCmntTxt, (String) svcMemoMap.get("SVC_CMNT_TXT"));
                setValue(tMsg.svcMemoPk, (BigDecimal) svcMemoMap.get("SVC_MEMO_PK"));
            } else if (str.equals(MODE_CLOSE)) {
                setValue(tMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.CALL_CLOSE_FROM_S21);
                setValue(tMsg.svcTaskStsDescTxt, rsSet.getString("SVC_TASK_STS_DESC_TXT"));
                setValue(tMsg.techNm, rsSet.getString("TECH_NM"));
                setValue(tMsg.actlStartTs, setTs(rsSet.getString("FSR_VISIT_ARV_TS")));
                setValue(tMsg.actlEndTs, setTs(rsSet.getString("FSR_VISIT_CPLT_TS")));
                setValue(tMsg.svcPblmCrctTpCd, rsSet.getString("SVC_PBLM_CRCT_TP_CD"));
                setValue(tMsg.svcPblmCrctTpDescTxt, rsSet.getString("SVC_PBLM_CRCT_TP_DESC_TXT"));
            } else if (str.equals(MODE_FOLLE_UP_CALL)) {
                setValue(tMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.FOLLOW_UP_CALL_FROM_S21);
                setValue(tMsg.dsSvcCallTpDescTxt, rsSet.getString("DS_SVC_CALL_TP_DESC_TXT"));
                setValue(tMsg.svcTaskStsDescTxt, rsSet.getString("SVC_TASK_STS_DESC_TXT"));
                setValue(tMsg.techNm, rsSet.getString("TECH_NM"));
                setValue(tMsg.fsrVisitEtaTs, setTs(rsSet.getString("FSR_VISIT_ETA_TS")));
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return tMsg;
    }

    private CITS_DBRF_STAGETMsg setCitsDbrfStage(Map<String, Object> dbrfMap) {
        CITS_DBRF_STAGETMsg tMsg = new CITS_DBRF_STAGETMsg();

        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.citsDbrfStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CITS_DBRF_STAGE_SQ));
        setValue(tMsg.svcTaskNum, (String) dbrfMap.get("SVC_TASK_NUM"));
        setValue(tMsg.plsftTaskNum, (String) dbrfMap.get("CUST_CSE_NUM"));
        setValue(tMsg.techNm, (String) dbrfMap.get("TECH_NM"));
        setValue(tMsg.svcPblmCrctTpCd, (String) dbrfMap.get("SVC_PBLM_CRCT_TP_CD"));
        setValue(tMsg.svcPblmCrctTpDescTxt, (String) dbrfMap.get("SVC_PBLM_CRCT_TP_DESC_TXT"));
        setValue(tMsg.svcPblmLocTpCd, (String) dbrfMap.get("SVC_PBLM_LOC_TP_CD"));
        setValue(tMsg.svcPblmLocTpDescTxt, (String) dbrfMap.get("SVC_PBLM_LOC_TP_DESC_TXT"));
        setValue(tMsg.svcPblmRsnTpCd, (String) dbrfMap.get("SVC_PBLM_RSN_TP_CD"));
        setValue(tMsg.svcPblmRsnTpDescTxt, (String) dbrfMap.get("SVC_PBLM_RSN_TP_DESC_TXT"));
        setValue(tMsg.svcPblmTpCd, (String) dbrfMap.get("SVC_PBLM_TP_CD"));
        setValue(tMsg.svcPblmTpDescTxt, (String) dbrfMap.get("SVC_PBLM_TP_DESC_TXT"));
        setValue(tMsg.mdseCd, (String) dbrfMap.get("MDSE_CD"));
        setValue(tMsg.svcLborStartTs, setTs((String) dbrfMap.get("FSR_VISIT_ARV_TS")));
        setValue(tMsg.svcLborEndTs, setTs((String) dbrfMap.get("FSR_VISIT_CPLT_TS")));
        setValue(tMsg.svcPrtQty, (BigDecimal) dbrfMap.get("SVC_PRT_QTY"));
        return tMsg;
    }

    private int insertCitsStageData(List<CITS_SVC_TASK_UPD_STAGETMsg> inMsgLst) {
        if (inMsgLst.isEmpty()) {
            return 0;
        }
        CITS_SVC_TASK_UPD_STAGETMsg[] inMsgArray;
        inMsgArray = new CITS_SVC_TASK_UPD_STAGETMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NSBM0164E, new String[] {inMsgArray[0].getTableName() });
        }
        return insertCount;
    }

    private void insertCitsDbrfStageData(List<CITS_DBRF_STAGETMsg> inMsgLst) {
        if (inMsgLst.isEmpty()) {
            return;
        }
        CITS_DBRF_STAGETMsg[] inMsgArray;
        inMsgArray = new CITS_DBRF_STAGETMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            throw new S21AbendException(NSBM0164E, new String[] {inMsgArray[0].getTableName() });
        }
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

    private String setTs(String orgTs) {
        if (!ZYPCommonFunc.hasValue(orgTs)) {
            return null;
        } else if (orgTs.length() < TIMESTAMP_LENGTH) {
            return ZYPCommonFunc.rightPad(orgTs, TIMESTAMP_LENGTH, PAD_STR);
        }
        return orgTs;
    }
}
