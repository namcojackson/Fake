/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB045001;

import static com.canon.cusa.s21.batch.NSB.NSBB045001.constant.NSBB045001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
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
import business.db.DS_MDLTMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Model Duration Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/04   Hitachi         T.Mizuki        Create          N/A
 * 2016/08/31   Hitachi         A.Kohinata      Update          QC#13839
 *</pre>
 */

public class NSBB045001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    // del start 2016/08/31 CSA Defect#13839
    ///** sumDiffMin2 */
    //private int sumDiffMin2 = 0;
    // del end 2016/08/31 CSA Defect#13839

    /** followUpRepairTime */
    private int followUpRepairTime = 0;

    /** totalCount */
    private int totalCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Process Time Stamp */
    private String procTs = null;

    /** Sales Date */
    private String salesDate = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** SVC_BAT_ERR_LOGTMsg list */
    private List<SVC_BAT_ERR_LOGTMsg> svcBatErrLogTMsgList = new ArrayList<SVC_BAT_ERR_LOGTMsg>();

    /** SVC_TASK_CALC_DURN_MTH_AOT */
    private int svcTaskCalcDurnMthAot = 0;

    /** SVC_TASK_CACL_DURN_TP */
    private String svcTaskCaclDurnTp = null;

    /** MULTI_MDL_DURN_CNT */
    private BigDecimal multiMdlDurnCnt = BigDecimal.ZERO;

    // del start 2016/08/31 CSA Defect#13839
    ///** SVC_TM_EVENT_CD */
    //private String svcTmEventCd = null;
    // del end 2016/08/31 CSA Defect#13839

    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NASM0010E);
        }

        // "Sales Date"
        this.salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd, PROGRAM_ID);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.procTs = ZYPDateUtil.getCurrentSystemTime(TIME_STAMP_FORMAT);
    }

    @Override
    protected void mainRoutine() {
        doProcess();

        if (!errMsgList.isEmpty()) {
            termCd = TERM_CD.WARNING_END;
            insertSvcBatErrLog();
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB045001().executeBatch(NSBB045001.class.getSimpleName());
    }

    private void doProcess() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            this.svcTaskCaclDurnTp = ZYPCodeDataUtil.getVarCharConstValue(SVC_TASK_CACL_DURN_TP, this.glblCmpyCd);
            this.multiMdlDurnCnt = ZYPCodeDataUtil.getNumConstValue(MULTI_MDL_DURN_CNT, this.glblCmpyCd);
            // del start 2016/08/31 CSA Defect#13839
            //this.svcTmEventCd = ZYPCodeDataUtil.getVarCharConstValue(SVC_TM_EVENT_CD, this.glblCmpyCd);
            // del end 2016/08/31 CSA Defect#13839
            String salesDateSubtract = salesDateSubtract();

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", this.glblCmpyCd);
            queryParam.put("multiMdlDurnCnt", multiMdlDurnCnt);
            queryParam.put("rest", getUserVariable1());

            List<String> dsSvcCallTpCd = new ArrayList<String>();
            dsSvcCallTpCd.add(DS_SVC_CALL_TP.SERV_CALL);
            dsSvcCallTpCd.add(DS_SVC_CALL_TP.AHS_SERV_CALL);

            List<String> svcTaskStsCd = new ArrayList<String>();
            svcTaskStsCd.add(SVC_TASK_STS.COMPLETED);
            svcTaskStsCd.add(SVC_TASK_STS.CLOSED);

            queryParam.put("dsSvcCallTpCd", dsSvcCallTpCd);
            queryParam.put("svcTaskStsCd", svcTaskStsCd);
            queryParam.put("fsrVisitNum", FSR_VISIT_NUM);
            queryParam.put("salesDate", salesDate);
            queryParam.put("salesDateBefore", salesDateSubtract);

            // getTargetModelInfo
            ps = this.ssmLLClient.createPreparedStatement("getTaskInfo", queryParam, getExecPrm());

            rs = ps.executeQuery();
            int totalRepairTime = 0;
            totalCount = 0;
            BigDecimal mdlId = null;
            // del start 2016/08/31 CSA Defect#13839
            //BigDecimal minute = new BigDecimal("60");
            // del end 2016/08/31 CSA Defect#13839
            while (rs.next()) {

                // update
                if (null != mdlId) {
                    if (!mdlId.equals(rs.getBigDecimal("MDL_ID")) && totalCount != 0) {
                        BigDecimal averageTime = new BigDecimal(totalRepairTime / totalCount);
                        ModelDurationBean mdlDurationBean = new ModelDurationBean();
                        mdlDurationBean.setMdlDurnTmNum(averageTime.setScale(0, BigDecimal.ROUND_HALF_UP));
                        mdlDurationBean.setMdlId(mdlId);
                        boolean errFlgUpdt = false;
                        if (!updateSvcModSerRng(mdlDurationBean)) {
                            errFlgUpdt = true;
                        }
                        if (errFlgUpdt) {
                            this.errorCount++;
                        } else {
                            this.normalCount++;
                            commit();
                        }
                        totalRepairTime = 0;
                        totalCount = 0;
                    }
                }
                mdlId = rs.getBigDecimal("MDL_ID");

                // mod start 2016/08/31 CSA Defect#13839
//                int sumDiffMin = 0;
//                sumDiffMin2 = 0;
//                followUpRepairTime = 0;
//
//                Map<String, Object> queryTimeParam = new HashMap<String, Object>();
//                queryTimeParam.put("glblCmpyCd", this.glblCmpyCd);
//                queryTimeParam.put("svcTaskNum", rs.getString("SVC_TASK_NUM"));
//
//                String[] svcTmEventCdList = svcTmEventCd.split(STR_COMMA);
//                queryTimeParam.put("svcTmEventCd", svcTmEventCdList);
//
//                // getLaborTime
//                List<Map<String, Object>> laborTime = this.ssmBatchClient.queryObjectList("getLaborTime", queryTimeParam, getExecPrm());
//
//                int sumDiffSec = 0;
//                sumDiffSec = getLaborTime(laborTime);
//                BigDecimal sumDiffSecDouble = new BigDecimal(sumDiffSec);
//                sumDiffMin = sumDiffSecDouble.divide(minute, 0, BigDecimal.ROUND_HALF_UP).intValue();
//
//                totalRepairTime += sumDiffMin;
//                ++totalCount;
//                if (!hasValue(rs.getString("NEXT_FSR_VISIT_NUM")) || sumDiffSec == 0 || svcTaskCaclDurnTp.equals(SVC_CALL_ONLY)) {
//                    continue;
//                }
//                getFollowUpTask(rs.getString("NEXT_FSR_VISIT_NUM"));
//                totalRepairTime += followUpRepairTime;

                followUpRepairTime = 0;

                Map<String, Object> queryTimeParam = new HashMap<String, Object>();
                queryTimeParam.put("glblCmpyCd", this.glblCmpyCd);
                queryTimeParam.put("svcTaskNum", rs.getString("SVC_TASK_NUM"));
                // getLaborTime
                Map<String, Object> laborTimeMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getLaborTime", queryTimeParam, getExecPrm());
                if (laborTimeMap == null) {
                    continue;
                }
                BigDecimal laborTime = (BigDecimal) laborTimeMap.get("SVC_LBOR_TM_NUM");
                totalRepairTime += laborTime.intValue();
                ++totalCount;
                if (!hasValue(rs.getString("NEXT_FSR_VISIT_NUM")) || laborTime.intValue() == 0 || svcTaskCaclDurnTp.equals(SVC_CALL_ONLY)) {
                    continue;
                }

                getFollowUpTask(rs.getString("FSR_NUM"), rs.getString("NEXT_FSR_VISIT_NUM"));
                totalRepairTime += followUpRepairTime;
                // mod end 2016/08/31 CSA Defect#13839
            }
            if (0 == totalCount) {
                return;
            }

            //update
            BigDecimal averageTime = new BigDecimal(totalRepairTime / totalCount);

            ModelDurationBean mdlDurationBean = new ModelDurationBean();
            mdlDurationBean.setMdlDurnTmNum(averageTime.setScale(0, BigDecimal.ROUND_HALF_UP));
            mdlDurationBean.setMdlId(mdlId);
            boolean errFlg = false;
            if (!updateSvcModSerRng(mdlDurationBean)) {
                errFlg = true;
            }
            if (errFlg) {
                this.errorCount++;
            } else {
                this.normalCount++;
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    private void getFollowUpTask(String fsrNum, String nextFsrVisitNum) {

        Map<String, Object> queryFollowUpTaskParam = new HashMap<String, Object>();
        queryFollowUpTaskParam.put("glblCmpyCd", this.glblCmpyCd);
        queryFollowUpTaskParam.put("fsrNum", fsrNum);
        queryFollowUpTaskParam.put("fsrVisitNum", nextFsrVisitNum);

        // getFollowUpTask
        List<Map<String, Object>> fllwUpTskList = this.ssmBatchClient.queryObjectList("getFollowUpTask", queryFollowUpTaskParam, getExecPrm());

        for (Map<String, Object> fllwUpTskMap : fllwUpTskList) {
            Map<String, Object> actualRepairParam = new HashMap<String, Object>();
            actualRepairParam.put("glblCmpyCd", this.glblCmpyCd);
            actualRepairParam.put("svcTaskNum", fllwUpTskMap.get("SVC_TASK_NUM"));

            // mod start 2016/08/31 CSA Defect#13839
//            String[] svcTmEventCdList = svcTmEventCd.split(STR_COMMA);
//            actualRepairParam.put("svcTmEventCd", svcTmEventCdList);
//
//            // getLaborTime
//            List<Map<String, Object>> fllwUpLbTmList = this.ssmBatchClient.queryObjectList("getLaborTime", actualRepairParam, getExecPrm());
//
//            int sumDiffSec2 = 0;
//            sumDiffSec2 = getLaborTime(fllwUpLbTmList);
//            BigDecimal sumDiffSecDouble2 = new BigDecimal(sumDiffSec2);
//            BigDecimal minute = new BigDecimal("60");
//            sumDiffMin2 = sumDiffSecDouble2.divide(minute, 0, BigDecimal.ROUND_HALF_UP).intValue();
//            followUpRepairTime += sumDiffMin2;
//
//            if (svcTaskCaclDurnTp.equals(FOR_EACH)) {
//                ++totalCount;
//            }
//            if (null == fllwUpTskMap.get("NEXT_FSR_VISIT_NUM")) {
//                break;
//            } else {
//                getFollowUpTask((String) fllwUpTskMap.get("NEXT_FSR_VISIT_NUM"));
//            }

            // getLaborTime
            Map<String, Object> laborTimeMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getLaborTime", actualRepairParam, getExecPrm());
            if (laborTimeMap == null) {
                break;
            }
            BigDecimal laborTime = (BigDecimal) laborTimeMap.get("SVC_LBOR_TM_NUM");
            followUpRepairTime += laborTime.intValue();

            if (svcTaskCaclDurnTp.equals(FOR_EACH)) {
                ++totalCount;
            }
            if (null == fllwUpTskMap.get("NEXT_FSR_VISIT_NUM")) {
                break;
            } else {
                getFollowUpTask(fsrNum, (String) fllwUpTskMap.get("NEXT_FSR_VISIT_NUM"));
            }
            // mod end 2016/08/31 CSA Defect#13839
        }
        return;
    }

    // del start 2016/08/31 CSA Defect#13839
//    /**
//     * @param strDate1 String
//     * @param strDate2 String
//     * @return int
//     * @throws ParseException 
//     */
//    private int differenceDays(String strDate1, String strDate2) throws ParseException {
//        return ZYPDateUtil.getDiffDays(strDate1, strDate2);
//    }
    // del end 2016/08/31 CSA Defect#13839

    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(MAX_FETCH_SIZE);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    private String salesDateSubtract() {

        this.svcTaskCalcDurnMthAot = ZYPCodeDataUtil.getNumConstValue(SVC_TASK_CALC_DURN_MTH_AOT, this.glblCmpyCd).intValue();
        int yyyy = Integer.parseInt(salesDate.substring(0, NUM_4));
        int mm = Integer.parseInt(salesDate.substring(NUM_4, NUM_6));
        int beforemm = 0;
        int beforeyyyy = yyyy;

        if (svcTaskCalcDurnMthAot < mm) {
            beforemm = mm - svcTaskCalcDurnMthAot;
        } else {
            beforemm = NUM_12 + mm - svcTaskCalcDurnMthAot;
            beforeyyyy = yyyy - 1;
        }

        StringBuilder yyyymmdd = new StringBuilder(NUM_8);
        yyyymmdd.append(beforeyyyy);
        if (10 > beforemm) {
            yyyymmdd.append(ZERO);
        }
        yyyymmdd.append(beforemm);
        yyyymmdd.append(salesDate.substring(NUM_6, NUM_8));

        return yyyymmdd.toString();
    }

    private boolean updateSvcModSerRng(ModelDurationBean mdlDurationBean) {

        BigDecimal mdlId = mdlDurationBean.getMdlId();

        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
        setValue(dsMdlTMsg.glblCmpyCd, glblCmpyCd);
        setValue(dsMdlTMsg.mdlId, mdlId);
        dsMdlTMsg = (DS_MDLTMsg) EZDTBLAccessor.findByKeyForUpdate(dsMdlTMsg);

        if (dsMdlTMsg == null) {
            addErrMsg(mdlDurationBean, NSBM0120E, new String[] {"DS_MDL", createFaileMsg("MDL_ID", mdlId) });
            return false;
        }
        setValue(dsMdlTMsg.mdlDurnTmNum, mdlDurationBean.getMdlDurnTmNum());
        EZDTBLAccessor.update(dsMdlTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsMdlTMsg.getReturnCode())) {
            addErrMsg(mdlDurationBean, NSBM0120E, new String[] {"DS_MDL", createFaileMsg("MDL_ID", mdlId) });
            return false;
        }
        return true;
    }

    // del start 2016/08/31 CSA Defect#13839
//    private int getLaborTime(List<Map<String, Object>> lbTmList) {
//
//        int sumDiffSec = 0;
//        try {
//            for (Map<String, Object> lbTmMap : lbTmList) {
//                String fromDt = (String) lbTmMap.get("SVC_TM_EVENT_FROM_DT");
//                String fromTm = (String) lbTmMap.get("SVC_TM_EVENT_FROM_TM");
//                String toDt = (String) lbTmMap.get("SVC_TM_EVENT_TO_DT");
//                String toTm = (String) lbTmMap.get("SVC_TM_EVENT_TO_TM");
//                int fromhh = Integer.parseInt(fromTm.substring(NUM_0, NUM_2));
//                int frommm = Integer.parseInt(fromTm.substring(NUM_2, NUM_4));
//                int fromss = Integer.parseInt(fromTm.substring(NUM_4, NUM_6));
//                int tohh = Integer.parseInt(toTm.substring(NUM_0, NUM_2));
//                int tomm = Integer.parseInt(toTm.substring(NUM_2, NUM_4));
//                int toss = Integer.parseInt(toTm.substring(NUM_4, NUM_6));
//
//                int fromSec = (fromhh * NUM_60 * NUM_60) + (frommm * NUM_60) + fromss;
//                int toSec = (tohh * NUM_60 * NUM_60) + (tomm * NUM_60) + toss;
//
//                if (fromDt.equals(toDt) && fromTm.equals(toTm)) {
//                    continue;
//                } else if (fromDt.equals(toDt) && !fromTm.equals(toTm)) {
//                    sumDiffSec += toSec - fromSec;
//                } else {
//                    if (fromSec > toSec) {
//                        int hhmmssDiff = (toSec + NUM_DAY) - fromSec;
//                        int daydiff = (differenceDays(toDt, fromDt) - 1) * NUM_DAY;
//                        sumDiffSec += hhmmssDiff + daydiff;
//                    } else {
//                        int hhmmssDiff = toSec - fromSec;
//                        int daydiff;
//                        daydiff = differenceDays(toDt, fromDt) * NUM_DAY;
//                        sumDiffSec += hhmmssDiff + daydiff;
//                    }
//                }
//            }
//        } catch (ParseException e) {
//            return 0;
//        }
//        return sumDiffSec;
//    }
    // del end 2016/08/31 CSA Defect#13839

    private String createFaileMsg(String keyName, BigDecimal keyValue) {

        StringBuffer sb = new StringBuffer(keyName);
        sb.append(STR_COLON);
        sb.append(keyValue.toString());
        return sb.toString();
    }

    private void addErrMsg(ModelDurationBean mdlDurationBean, String msgId, String... param) {

        String errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        this.errMsgList.add(errMsg);

        SVC_BAT_ERR_LOGTMsg svcBatErrLogTMsg = new SVC_BAT_ERR_LOGTMsg();
        setValue(svcBatErrLogTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcBatErrLogTMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_BAT_ERR_LOG_SQ"));
        setValue(svcBatErrLogTMsg.bizAppId, BIZ_APP_ID);
        setValue(svcBatErrLogTMsg.svcBatErrLogTs, this.procTs);
        setValue(svcBatErrLogTMsg.svcBatErrKeyNum_01, mdlDurationBean.getMdlId().toString());
        setValue(svcBatErrLogTMsg.svcBatErrKeyNm_01, KEY_MDL_ID);
        this.svcBatErrLogTMsgList.add(svcBatErrLogTMsg);
    }

    private void insertSvcBatErrLog() {
        SVC_BAT_ERR_LOGTMsg[] inMsgArray;
        inMsgArray = new SVC_BAT_ERR_LOGTMsg[this.svcBatErrLogTMsgList.size()];
        S21FastTBLAccessor.insert(this.svcBatErrLogTMsgList.toArray(inMsgArray));
    }

}
