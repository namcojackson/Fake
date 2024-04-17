/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB032001;

import static com.canon.cusa.s21.batch.NSB.NSBB032001.constant.NSBB032001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.SVC_TASK_DLY_RPTTMsg;
import business.db.SVC_TASK_HIST_RPTTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Create Task History Report Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   Hitachi         Y.Osawa         Create          N/A
 * 2016/03/18   Hitachi         K.Yamada        Update          CSA QC#5702
 * </pre>
 */
public class NSBB032001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd;

    // del start 2016/03/18 CSA Defect#5702
    /** Interface Id */
    //private String intfcId;
    // del end 2016/03/18 CSA Defect#5702

    /** Sales Date */
    private String salesDate;

    /** Commit Number */
    private int commitNumber;

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;


    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSSM0010E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // del start 2016/03/18 CSA Defect#5702
        // Get Interface Id
        //this.intfcId = getInterfaceID();
        //if (!ZYPCommonFunc.hasValue(this.intfcId)) {
        //    throw new S21AbendException(ZZM9000E, new String[] {MSG_ITEM_INTERFACE_ID });
        //}
        // del end 2016/03/18 CSA Defect#5702

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSSM0011E, new String[] {MSG_ITEM_SALES_DATE });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount   = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        String constDate = ZYPCodeDataUtil.getVarCharConstValue(KEY_CONST_BYGONE_DATES, getGlobalCompanyCode());

        if (constDate == null || constDate.isEmpty()) {
            constDate = CONST_DATES;
        }

        // delete History
        getTaskHistoryReport(constDate);

    }

    @Override
    protected void mainRoutine() {

        List<SVC_TASK_DLY_RPTTMsg> svcDlyTMsgList = new ArrayList<SVC_TASK_DLY_RPTTMsg>();
        List<SVC_TASK_HIST_RPTTMsg> svcHisTMsgList = new ArrayList<SVC_TASK_HIST_RPTTMsg>();

        int inputCount = 0;
        int commitCount = 0;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("cratDt",  this.salesDate);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getDayBeforeTaskDailyReport", paramMap, execParam);
            rs = stmt.executeQuery();

            // delete Work Data
            while (rs.next()) {
                inputCount++;
                // insert List
                svcHisTMsgList.add(setCreateValueHIS(rs));
                // delete List
                svcDlyTMsgList.add(setCreateValueDLY(rs));

                if (this.commitNumber == svcHisTMsgList.size()) {
                    commitCount = insertWorkData(svcHisTMsgList);
                    deleteSvcTaskDLY(svcDlyTMsgList);
                    commit();
                    svcHisTMsgList.clear();
                    svcDlyTMsgList.clear();
                    this.normalCount += commitCount;
                    commitCount = 0;
                }
            }

            if (inputCount != this.normalCount) {
                commitCount = insertWorkData(svcHisTMsgList);
                deleteSvcTaskDLY(svcDlyTMsgList);
                commit();
                this.normalCount += commitCount;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }


    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB032001().executeBatch(NSBB032001.class.getSimpleName());
    }


    private void getTaskHistoryReport(String constDate) {
        List<SVC_TASK_HIST_RPTTMsg> inTMsgList = new ArrayList<SVC_TASK_HIST_RPTTMsg>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);

        int cDate =  -Integer.parseInt(constDate);
        paramMap.put("cratDt",  ZYPDateUtil.addDays(this.salesDate, cDate));

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getTaskHistoryReport", paramMap, execParam);
            rs = stmt.executeQuery();

            // delete Work Data
            while (rs.next()) {
                inTMsgList.add(setDeleteTargetHIS(rs));

                if (this.commitNumber == inTMsgList.size()) {
                    deleteSvcTaskHIST(inTMsgList);
                    inTMsgList.clear();
                }
            }

            if (inTMsgList.size() > 0) {
                deleteSvcTaskHIST(inTMsgList);
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    /**
     * delete Service Task History.
     */
    private void deleteSvcTaskHIST(List<SVC_TASK_HIST_RPTTMsg> inMsgLst) {

        SVC_TASK_HIST_RPTTMsg[] inMsgArray;
        inMsgArray = new SVC_TASK_HIST_RPTTMsg[inMsgLst.size()];
        S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));
        commit();

    }

    /**
     * delete Service Task Daily.
     */
    private void deleteSvcTaskDLY(List<SVC_TASK_DLY_RPTTMsg> inMsgLst) {

        SVC_TASK_DLY_RPTTMsg[] inMsgArray;
        inMsgArray = new SVC_TASK_DLY_RPTTMsg[inMsgLst.size()];
        S21FastTBLAccessor.removePhysical(inMsgLst.toArray(inMsgArray));
    }


    private SVC_TASK_DLY_RPTTMsg setCreateValueDLY(ResultSet rs) {
        SVC_TASK_DLY_RPTTMsg inParam = new SVC_TASK_DLY_RPTTMsg();
        try {
            setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            setValue(inParam.svcTaskDlyRptPk, rs.getBigDecimal("SVC_TASK_DLY_RPT_PK"));

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return inParam;
    }

    private SVC_TASK_HIST_RPTTMsg setCreateValueHIS(ResultSet rs) {
        SVC_TASK_HIST_RPTTMsg inParam = new SVC_TASK_HIST_RPTTMsg();
        try {
            setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            BigDecimal svcModStsPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_HIST_RPT_SQ);
            setValue(inParam.svcTaskHistRptPk, svcModStsPk);
            setValue(inParam.svcTaskDlyRptPk, rs.getBigDecimal("SVC_TASK_DLY_RPT_PK"));
            setValue(inParam.cratDt, rs.getString("CRAT_DT"));
            setValue(inParam.snapShotDtTmTs, rs.getString("SNAP_SHOT_DT_TM_TS"));
            setValue(inParam.orgLayerNum, rs.getBigDecimal("ORG_LAYER_NUM"));
            setValue(inParam.firstOrgCd, rs.getString("FIRST_ORG_CD"));
            setValue(inParam.firstOrgNm, rs.getString("FIRST_ORG_NM"));
            setValue(inParam.scdOrgCd, rs.getString("SCD_ORG_CD"));
            setValue(inParam.scdOrgNm, rs.getString("SCD_ORG_NM"));
            setValue(inParam.thirdOrgCd, rs.getString("THIRD_ORG_CD"));
            setValue(inParam.thirdOrgNm, rs.getString("THIRD_ORG_NM"));
            setValue(inParam.frthOrgCd, rs.getString("FRTH_ORG_CD"));
            setValue(inParam.frthOrgNm, rs.getString("FRTH_ORG_NM"));
            setValue(inParam.fifthOrgCd, rs.getString("FIFTH_ORG_CD"));
            setValue(inParam.fifthOrgNm, rs.getString("FIFTH_ORG_NM"));
            setValue(inParam.sixthOrgCd, rs.getString("SIXTH_ORG_CD"));
            setValue(inParam.sixthOrgNm, rs.getString("SIXTH_ORG_NM"));
            setValue(inParam.svnthOrgCd, rs.getString("SVNTH_ORG_CD"));
            setValue(inParam.svnthOrgNm, rs.getString("SVNTH_ORG_NM"));
            setValue(inParam.eighthOrgCd, rs.getString("EIGHTH_ORG_CD"));
            setValue(inParam.eighthOrgNm, rs.getString("EIGHTH_ORG_NM"));
            setValue(inParam.ninthOrgCd, rs.getString("NINTH_ORG_CD"));
            setValue(inParam.ninthOrgNm, rs.getString("NINTH_ORG_NM"));
            setValue(inParam.tenthOrgCd, rs.getString("TENTH_ORG_CD"));
            setValue(inParam.tenthOrgNm, rs.getString("TENTH_ORG_NM"));
            setValue(inParam.elvthOrgCd, rs.getString("ELVTH_ORG_CD"));
            setValue(inParam.elvthOrgNm, rs.getString("ELVTH_ORG_NM"));
            setValue(inParam.totInProcTaskCnt, rs.getBigDecimal("TOT_IN_PROC_TASK_CNT"));
            setValue(inParam.prtWaitTaskCnt, rs.getBigDecimal("PRT_WAIT_TASK_CNT"));
            setValue(inParam.spclWaitTaskCnt, rs.getBigDecimal("SPCL_WAIT_TASK_CNT"));
            setValue(inParam.othOpenTaskCnt, rs.getBigDecimal("OTH_OPEN_TASK_CNT"));
            setValue(inParam.custTaskCnt, rs.getBigDecimal("CUST_TASK_CNT"));
            setValue(inParam.esclTaskCnt, rs.getBigDecimal("ESCL_TASK_CNT"));
            setValue(inParam.cratTaskCnt, rs.getBigDecimal("CRAT_TASK_CNT"));
            setValue(inParam.cratTaskPerTechRate, rs.getBigDecimal("CRAT_TASK_PER_TECH_RATE"));
            setValue(inParam.aftHourTaskCnt, rs.getBigDecimal("AFT_HOUR_TASK_CNT"));
            setValue(inParam.aftHourTaskPerTechRate, rs.getBigDecimal("AFT_HOUR_TASK_PER_TECH_RATE"));
            setValue(inParam.cloTaskCnt, rs.getBigDecimal("CLO_TASK_CNT"));
            setValue(inParam.cloTaskPerTechRate, rs.getBigDecimal("CLO_TASK_PER_TECH_RATE"));
            setValue(inParam.prtFailCnt, rs.getBigDecimal("PRT_FAIL_CNT"));
            setValue(inParam.postEntryTaskCnt, rs.getBigDecimal("POST_ENTRY_TASK_CNT"));
            setValue(inParam.avalTechCnt, rs.getBigDecimal("AVAL_TECH_CNT"));
            setValue(inParam.rspTmCustTaskRate, rs.getBigDecimal("RSP_TM_CUST_TASK_RATE"));
            setValue(inParam.rspTmAllTaskRate, rs.getBigDecimal("RSP_TM_ALL_TASK_RATE"));
            setValue(inParam.aftHourAvalTechCnt, rs.getBigDecimal("AFT_HOUR_AVAL_TECH_CNT"));
            setValue(inParam.custTaskTotRspAot, rs.getBigDecimal("CUST_TASK_TOT_RSP_AOT"));
            setValue(inParam.cloCustTaskCnt, rs.getBigDecimal("CLO_CUST_TASK_CNT"));
            setValue(inParam.allTaskTotRspAot, rs.getBigDecimal("ALL_TASK_TOT_RSP_AOT"));
            setValue(inParam.cloAllTaskCnt, rs.getBigDecimal("CLO_ALL_TASK_CNT"));
            setValue(inParam.svcTaskLtstFlg, rs.getString("SVC_TASK_LTST_FLG"));

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        return inParam;
    }

    private SVC_TASK_HIST_RPTTMsg setDeleteTargetHIS(ResultSet rs) {
        SVC_TASK_HIST_RPTTMsg inParam = new SVC_TASK_HIST_RPTTMsg();
        try {
            setValue(inParam.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
            setValue(inParam.svcTaskHistRptPk, rs.getBigDecimal("SVC_TASK_HIST_RPT_PK"));

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }

        return inParam;
    }

    private int insertWorkData(List<SVC_TASK_HIST_RPTTMsg> inMsgLst) {
        SVC_TASK_HIST_RPTTMsg[] inMsgArray;
        inMsgArray = new SVC_TASK_HIST_RPTTMsg[inMsgLst.size()];
        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            this.errorCount += inMsgArray.length - insertCount;
            throw new S21AbendException(NSBM0032E, new String[] {"SVC_TASK_HIST_RPT" });
        }

        return insertCount;
    }
}
