/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB025001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CRS_SVC_CALL_TP_MAPTMsg;
import business.db.CRS_SVC_CALL_TP_MAPTMsgArray;
import business.db.CRS_SVC_FSR_STS_MAPTMsg;
import business.db.CRS_SVC_FSR_STS_MAPTMsgArray;
import business.db.CRS_SVC_RCV_UPD_STAGETMsg;
import business.db.CRS_SVC_TASK_STS_MAPTMsg;
import business.db.CRS_SVC_TASK_STS_MAPTMsgArray;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC045001PMsg;

import static com.canon.cusa.s21.batch.NSB.NSBB025001.constant.NSBB025001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Update Cross Service Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/04/12   Hitachi         Y.Tsuchimoto    Update          QC#5053
 * 2016/05/24   Hitachi         T.Iwamoto       Update          QC#8809
 * 2016/05/30   Hitachi         T.Tomita        Update          QC#8085
 * 2016/08/22   Hitachi         T.Mizuki        Update          QC#13609
 * 2016/09/02   Hitachi         T.Tomita        Update          QC#13858
 * 2016/09/15   Hitachi         K.Yamada        Update          QC#14552
 * 2018/01/15   Hitachi         T.Tomita        Update          QC#23353
 * 2018/02/01   Hitachi         T.Tomita        Update          QC#23569
 * 2018/02/09   Hitachi         T.Tomita        Update          QC#23569
 * 2018/05/09   Hitachi         T.Tomita        Update          QC#23561
 * 2018/08/07   Hitachi         K.Kojima        Update          QC#27399
 * 2019/02/22   Hitachi         K.Fujimoto      Update          QC#30472
 * 2019/07/08   Hitachi         A.Kohinata      Update          QC#51170
 * 2020/04/01   Hitachi         A.Kohinata      Update          QC#56441
 * </pre>
 */
public class NSBB025001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    private String bizId = "NSBB025001";

    /** Total Commit Count */
    private int totalCommitCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** SVC_MEMO_TP_CD for FSR Update API */
    private String svcMemoTpCdForFsrUpdateApi;

    /** SVC_MEMO_TP_CD for Add Task API */
    private String svcMemoTpCdForAddTaskApi;

    // Add Start 2018/02/09 QC#23569
    /** Sales Date  */
    private String slsDt;
    // Add End 2018/02/09 QC#23569

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSBM0032E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.normalCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.svcMemoTpCdForFsrUpdateApi = getSvcMemoTpCdForFsrUpdateApi();
        this.svcMemoTpCdForAddTaskApi = getSvcMemoTpCdForAddTaskApi();
        // Add Start 2018/02/09 QC#23569
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        // Add End 2018/02/09 QC#23569
    }

    @Override
    protected void mainRoutine() {
        List<CRS_SVC_RCV_UPD_STAGETMsg> targetList = getTargetData(getSearchCondition());
        // START 2016/04/12 [QC#5053, MOD]
        Set<String> errorFsrNum = new HashSet<String>();
        int i = 0;
        for (; i < targetList.size(); i++) {
            CRS_SVC_RCV_UPD_STAGETMsg tMsg = targetList.get(i);
            String nowFsrNum = tMsg.fsrNum.getValue();
            String beforeFsrNum = null;
            if (i > 0) {
                beforeFsrNum = targetList.get(i - 1).fsrNum.getValue();
                if (hasValue(nowFsrNum) && !nowFsrNum.equals(beforeFsrNum)) {
                    //Mod Start K.fujimoto QC#30472
                    // if (errorFsrNum != null && !errorFsrNum.contains(nowFsrNum)) {
                    if (errorFsrNum != null && !errorFsrNum.contains(beforeFsrNum)) {
                    //Mod End   K.fujimoto QC#30472
                        commit();
                    } else {
                        rollback();
                    }
                }
            }

            // Mandatory check
            if (!isMandatoryCheck(tMsg)) {
                this.termCd = TERM_CD.WARNING_END;
                errorFsrNum.add(nowFsrNum);
            } else {
                this.totalCommitCount++;
                if (!updateFSR(tMsg)) {
                    this.termCd = TERM_CD.WARNING_END;
                    errorFsrNum.add(nowFsrNum);
                } else {
                    this.normalCount++;
                }
            }
        }
        if (targetList.size() > 0) {
            CRS_SVC_RCV_UPD_STAGETMsg tMsg = targetList.get(i - 1);
            if (errorFsrNum != null && !errorFsrNum.contains(tMsg.fsrNum.getValue())) {
                commit();
            } else {
                rollback();
            }

            // Update CRS_SVC_RCV_UPD_STAGE
            for (int j = 0; j < targetList.size(); j++) {
                CRS_SVC_RCV_UPD_STAGETMsg targetTmsg = targetList.get(j);
                //Mod Start K.fujimoto QC#30472
                // if (errorFsrNum != null && !errorFsrNum.contains(tMsg.fsrNum.getValue())) {
                if (errorFsrNum != null && !errorFsrNum.contains(targetTmsg.fsrNum.getValue())) {
                //Mod End   K.fujimoto QC#30472
                    updateCrsSvcProcStsCdForCrsSvcRcvUpdStage(targetTmsg, PROC_STS.COMPLEATED);
                } else {
                    updateCrsSvcProcStsCdForCrsSvcRcvUpdStage(targetTmsg, PROC_STS.ERROR);
                }
            }
        }
        if (!errMsgList.isEmpty()) {
            sendMail();
        }
        // END   2016/04/12 [QC#5053, MOD]
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.totalCommitCount, 0);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB025001().executeBatch(NSBB025001.class.getSimpleName());
    }

    private Map<String, Object> getSearchCondition() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("inCompleted", PROC_STS.IN_COMPLETED);
        return inParam;
    }

    private List<CRS_SVC_RCV_UPD_STAGETMsg> getTargetData(Map<String, Object> paramMap) {
        List<CRS_SVC_RCV_UPD_STAGETMsg> inTMsgList = new ArrayList<CRS_SVC_RCV_UPD_STAGETMsg>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getTargetData", paramMap, execParam);
            rs = stmt.executeQuery();

            // get CRS_SVC_RCV_UPD_STAGE
            while (rs.next()) {
                inTMsgList.add(getTargetTmsg(rs));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }

        return inTMsgList;
    }

    private CRS_SVC_RCV_UPD_STAGETMsg getTargetTmsg(ResultSet rs) throws SQLException {
        CRS_SVC_RCV_UPD_STAGETMsg tMsg = new CRS_SVC_RCV_UPD_STAGETMsg();

        setValue(tMsg.glblCmpyCd, rs.getString("GLBL_CMPY_CD"));
        setValue(tMsg.crsSvcRcvUpdStagePk, rs.getBigDecimal("CRS_SVC_RCV_UPD_STAGE_PK"));
        setValue(tMsg.fsrNum, rs.getString("FSR_NUM"));
        setValue(tMsg.crsSvcCratTsOrigTxt, rs.getString("CRS_SVC_CRAT_TS_ORIG_TXT"));
        setValue(tMsg.crsSvcCallTpCd, rs.getString("CRS_SVC_CALL_TP_CD"));
        setValue(tMsg.crsSvcFsrStsCd, rs.getString("CRS_SVC_FSR_STS_CD"));
        setValue(tMsg.fsrPrtyTxt, rs.getString("FSR_PRTY_TXT"));
        setValue(tMsg.mdlNm, rs.getString("MDL_NM"));
        setValue(tMsg.serNum, rs.getString("SER_NUM"));
        setValue(tMsg.crsSvcS21SvcTaskNum, rs.getString("CRS_SVC_S21_SVC_TASK_NUM"));
        setValue(tMsg.crsSvcTaskNm, rs.getString("CRS_SVC_TASK_NM"));
        setValue(tMsg.crsSvcTaskStsCd, rs.getString("CRS_SVC_TASK_STS_CD"));
        setValue(tMsg.svcCmntTxt_01, rs.getString("SVC_CMNT_TXT_01"));
        setValue(tMsg.crsSvcSrNum, rs.getString("CRS_SVC_SR_NUM"));
        setValue(tMsg.crsSvcTaskNum, rs.getString("CRS_SVC_TASK_NUM"));
        setValue(tMsg.crsSvcNoteIdAddTxt, rs.getString("CRS_SVC_NOTE_ID_ADD_TXT"));
        setValue(tMsg.crsSvcResrcId, rs.getString("CRS_SVC_RESRC_ID"));
        setValue(tMsg.svcCmntTxt_02, rs.getString("SVC_CMNT_TXT_02"));
        setValue(tMsg.crsSvcFileNm, rs.getString("CRS_SVC_FILE_NM"));
        setValue(tMsg.crsSvcActCd, rs.getString("CRS_SVC_ACT_CD"));
        setValue(tMsg.crsSvcProcStsCd, rs.getString("CRS_SVC_PROC_STS_CD"));

        return tMsg;
    }

    private boolean isMandatoryCheck(CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
        // FSR_NUM
        if (!hasValue(tMsg.fsrNum)) {
            addErrMsg(NSBM0032E, new String[] {"FSR No." });
            return false;
        }
        // CRS_SVC_CRAT_TS_ORIG_TXT
        if (!isFormatCratSvcCratTsOrigTxt(tMsg.crsSvcCratTsOrigTxt.getValue())) {
            addErrMsg(NSBM0140E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "Creation Timestamp" });
            return false;
        }
        // CRS_SVC_CALL_TP_CD
        if (!hasValue(tMsg.crsSvcCallTpCd)) {
            addErrMsg(NSBM0032E, new String[] {"Service Call Type" });
            return false;
        }
        String dsSvcCallTpCd = getDsSvcCallTpCd(tMsg.crsSvcCallTpCd.getValue());
        if (!hasValue(dsSvcCallTpCd)) {
            addErrMsg(NSBM0011E, new String[] {"Service Call Type:" + tMsg.crsSvcCallTpCd.getValue() });
            return false;
        } else {
            setValue(tMsg.crsSvcCallTpCd, dsSvcCallTpCd);
        }
        // CRS_SVC_FSR_STS_CD
        if (!hasValue(tMsg.crsSvcFsrStsCd)) {
            addErrMsg(NSBM0032E, new String[] {"FSR Status Code" });
            return false;
        }
        String fsrStsCd = getFsrStsCd(tMsg.crsSvcFsrStsCd.getValue());
        if (!hasValue(fsrStsCd)) {
            // mod start 2016/08/22 CSA QC#13609
            addErrMsg(NSBM0011E, new String[] {"FSR Status Code:" + tMsg.crsSvcFsrStsCd.getValue() });
            // mod end 2016/08/22 CSA QC#13609
            return false;
        } else {
            setValue(tMsg.crsSvcFsrStsCd, fsrStsCd);
        }
        // SER_NUM
        if (!hasValue(tMsg.serNum)) {
            addErrMsg(NSBM0141E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "Serial Number" });
            return false;
        }
        // del start 2016/05/30 CSA Defect#8085
//        // SVC_TASK_NUM
//        if (!hasValue(tMsg.crsSvcS21SvcTaskNum)) {
//            addErrMsg(NSBM0141E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "Service Task Number" });
//            return false;
//        }
        // del end 2016/05/30 CSA Defect#8085
        // CRS_SVC_TASK_STS_CD
        if (!hasValue(tMsg.crsSvcTaskStsCd)) {
            addErrMsg(NSBM0141E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "Cross Service Task Status Code" });
            return false;
        }
        String svcTaskStsCd = getSvcTaskStsCd(tMsg.crsSvcTaskStsCd.getValue());
        if (!hasValue(svcTaskStsCd)) {
            addErrMsg(NSBM0142E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "Cross Service Task Status Code:" + tMsg.crsSvcTaskStsCd.getValue() });
            return false;
        } else {
            // Mod Start 2018/01/14 QC#23353
            if (!SVC_TASK_STS.ASSIGNED.equals(svcTaskStsCd) && !SVC_TASK_STS.ARRIVED.equals(svcTaskStsCd) && !SVC_TASK_STS.COMPLETED.equals(svcTaskStsCd) && !SVC_TASK_STS.CANCELLED.equals(svcTaskStsCd) && !SVC_TASK_STS.VENDOR_ACKNOWLEDGED.equals(svcTaskStsCd)) {
                setValue(tMsg.crsSvcTaskStsCd, " ");
            } else {
                setValue(tMsg.crsSvcTaskStsCd, svcTaskStsCd);
            }
            // Mod End 2018/01/14 QC#23353
        }
        // START 2016/09/02 T.Tomita [QC#13858, DEL]
//        // SVC_CMNT_TXT_02
//        if (!hasValue(tMsg.svcCmntTxt_02)) {
//            addErrMsg(NSBM0032E, new String[] {"Service Comment" });
//            return false;
//        }
        // END 2016/09/02 T.Tomita [QC#13858, DEL]
        // CRS_SVC_SR_NUM
        if (!hasValue(tMsg.crsSvcSrNum)) {
            addErrMsg(NSBM0032E, new String[] {"SR Number" });
            return false;
        }
        // CRS_SVC_TASK_NUM
        if (!hasValue(tMsg.crsSvcTaskNum)) {
            addErrMsg(NSBM0141E, new String[] {"SR No." + tMsg.crsSvcSrNum.getValue(), "Cross Service Task Number" });
            return false;
        }

        return true;
    }

    private boolean isFormatCratSvcCratTsOrigTxt(String target) {
        DateFormat format = new SimpleDateFormat(MAIL_DATE_TIME_FORMAT);
        try {
            format.setLenient(false);
            format.parse(target);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    private boolean updateFSR(CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
        boolean rtnFlg = true;
        // Del Start 2018/01/31 QC#23569
//        SVC_TASKTMsg svcTaskForIsAckAndFollowUpTmsg = getSvcTask(tMsg);
//        // mod start 2016/05/30 CSA Defect#8085
////        boolean isAckFlg = isAcknowledgement(svcTaskForIsAckAndFollowUpTmsg);
////        if (isAckFlg) {
////            updateCrsSvcSrNumForSvcTask(tMsg);
////        }
////        boolean isFollowUpFlg = false;
////        if (!isAckFlg) {
////            isFollowUpFlg = isFollowUp(svcTaskForIsAckAndFollowUpTmsg, tMsg);
////            if (isFollowUpFlg) {
////                updateCrsSvcSrNumForSvcTask(tMsg);
////            }
////        }
//        if (svcTaskForIsAckAndFollowUpTmsg == null) {
//            addErrMsg(NSBM0143E, new String[] {"SR No." + tMsg.crsSvcSrNum.getValue(), "Service Task" });
//            return false;
//        }
//        // mod end 2016/05/30 CSA Defect#8085
        // Del End 2018/01/31 QC#23569
        // Mod Start 2018/05/09 QC#23561
//      if (!SVC_TASK_STS.CANCELLED.equals(tMsg.crsSvcFsrStsCd.getValue())) {
        if (!SVC_TASK_STS.CANCELLED.equals(tMsg.crsSvcFsrStsCd.getValue()) && !SVC_TASK_STS.CANCELLED.equals(tMsg.crsSvcTaskStsCd.getValue())) {
        // Mod End 2018/05/09 QC#23561
            SVC_TASKTMsg svcTaskTmsg = getSvcTask(tMsg);
            if (svcTaskTmsg == null) {
                addErrMsg(NSBM0143E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "Service Task" });
                return false;
            }
            FSR_VISITTMsg fsrVisitTmsg = getFsrVisit(svcTaskTmsg);
            if (fsrVisitTmsg == null) {
                addErrMsg(NSBM0143E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "FSR Visit" });
                return false;
            } else {
                if (SVC_TASK_STS.COMPLETED.equals(fsrVisitTmsg.fsrVisitStsCd.getValue()) || SVC_TASK_STS.CLOSED.equals(fsrVisitTmsg.fsrVisitStsCd.getValue()) || SVC_TASK_STS.CANCELLED.equals(fsrVisitTmsg.fsrVisitStsCd.getValue())) {
                    addErrMsg(NSBM0144E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "update" });
                    return false;
                }
            }
            // Mod Start 2018/01/31 QC#23569
            if (matchSvcTaskAndCrsSvc(tMsg, svcTaskTmsg)) {
                rtnFlg = nonFollowUp(fsrVisitTmsg, svcTaskTmsg, tMsg);
            } else {
                SVC_TASKTMsg followUpSvcTaskTmsg = getSvcTaskForCrsSvcSrNum(svcTaskTmsg.fsrNum.getValue(), tMsg.crsSvcSrNum.getValue());
                if (followUpSvcTaskTmsg == null) {
                    if(!followUp(fsrVisitTmsg, svcTaskTmsg, tMsg)) {
                        // [Error] Follow Up Task
                        return false;
                    }
                    followUpSvcTaskTmsg = getSvcTaskForCrsSvcSrNum(svcTaskTmsg.fsrNum.getValue(), tMsg.crsSvcSrNum.getValue());
                    setValue(tMsg.crsSvcS21SvcTaskNum, followUpSvcTaskTmsg.svcTaskNum);
                }
                FSR_VISITTMsg followUpFsrVisitTmsg = getFsrVisit(followUpSvcTaskTmsg);
                rtnFlg = nonFollowUp(followUpFsrVisitTmsg, followUpSvcTaskTmsg, tMsg);
            }
            // Mod End 2018/01/31 QC#23569
        } else {
            SVC_TASKTMsg svcTaskTmsg = getSvcTask(tMsg);
            if (svcTaskTmsg == null) {
                addErrMsg(NSBM0143E, new String[] {"SR No." + tMsg.crsSvcSrNum.getValue(), "Service Task" });
                return false;
            }
            FSR_VISITTMsg fsrVisitTmsg = getFsrVisit(svcTaskTmsg);
            if (fsrVisitTmsg == null) {
                addErrMsg(NSBM0143E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "FSR Visit" });
                return false;
            } else {
                // Mod Start 2018/05/09 QC#23561
                if (SVC_TASK_STS.CANCELLED.equals(fsrVisitTmsg.fsrVisitStsCd.getValue())) {
                    // [Skip]Task was already canceled.
                    return true;
                } else if (SVC_TASK_STS.ARRIVED.equals(fsrVisitTmsg.fsrVisitStsCd.getValue()) || SVC_TASK_STS.COMPLETED.equals(fsrVisitTmsg.fsrVisitStsCd.getValue()) || SVC_TASK_STS.CLOSED.equals(fsrVisitTmsg.fsrVisitStsCd.getValue())) {
                    addErrMsg(NSBM0144E, new String[] {"FSR No." + tMsg.fsrNum.getValue(), "cancel" });
                    return false;
                }
                // Mod End 2018/05/09 QC#23561
            }
            // FSR or Service Task Cancel
            SVC_TASKTMsgArray svcTaskList = getSvcTaskByFsrNumList(tMsg);
            if (svcTaskList.getValidCount() == 1) {
                // FSR Cancel
                rtnFlg = callFsrUpdateApiForCancel(svcTaskTmsg, tMsg);
            } else {
                // Task Cancel
                rtnFlg = callAddTaskApiForCancel(tMsg);
            }
        }
        return rtnFlg;
    }

    // del start 2016/05/30 CSA Defect#8085
//    private boolean isAcknowledgement(SVC_TASKTMsg svsTaskTmsg) {
//        if (svsTaskTmsg != null && !hasValue(svsTaskTmsg.crsSvcSrNum)) {
//            return true;
//        }
//        return false;
//    }
//
//    private boolean isFollowUp(SVC_TASKTMsg svsTaskTmsg, CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
//        String svcTaskCrsSvcSrNum = svsTaskTmsg.crsSvcSrNum.getValue();
//        String stageCrsSvcSrNum = tMsg.crsSvcSrNum.getValue();
//        if (hasValue(stageCrsSvcSrNum) && !stageCrsSvcSrNum.equals(svcTaskCrsSvcSrNum)) {
//            return true;
//        }
//        return false;
//    }
    // del end 2016/05/30 CSA Defect#8085

    private boolean nonFollowUp(FSR_VISITTMsg fsrVisitTmsg, SVC_TASKTMsg svcTaskTmsg, CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
        return callFsrUpdateApiForUpdate(fsrVisitTmsg, svcTaskTmsg, tMsg);
    }

    // Mod Start 2018/01/31 QC#23569
    private boolean followUp(FSR_VISITTMsg fsrVisitTmsg, SVC_TASKTMsg svcTaskTmsg, CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
        String followUpTaskNum = callAddTaskApiForFollowUpCall(fsrVisitTmsg, svcTaskTmsg, tMsg);
        if (!hasValue(followUpTaskNum)) {
            return false;
        }
        updateCrsSvcSrNumForSvcTaskNum(svcTaskTmsg, followUpTaskNum, tMsg);
        return true;
    }
    // Mod End 2018/01/31 QC#23569
    // del start 2016/05/30 CSA Defect#8085
//    private void updateCrsSvcSrNumForSvcTask(CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
//        SVC_TASKTMsg target = getSvcTaskByFsrNumAndSvcTaskNum(tMsg.fsrNum.getValue(), tMsg.crsSvcS21SvcTaskNum.getValue());
//        if (target != null) {
//            setValue(target.crsSvcSrNum, tMsg.crsSvcSrNum);
//            // Update SVC_TASK
//            EZDTBLAccessor.update(target);
//        }
//    }
    // del end 2016/05/30 CSA Defect#8085
    // Mod Start 2018/01/31 QC#23569
    private void updateCrsSvcSrNumForSvcTaskNum(SVC_TASKTMsg svcTaskTmsg, String followUpTaskNum, CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
        SVC_TASKTMsg target = getSvcTaskByFsrNumAndSvcTaskNum(svcTaskTmsg.fsrNum.getValue(), followUpTaskNum);
        if (target != null) {
            setValue(target.svcTaskStsCd, SVC_TASK_STS.IN_PROCESS);
            setValue(target.crsSvcSrNum, tMsg.crsSvcSrNum);
            // Update SVC_TASK
            EZDTBLAccessor.update(target);
        }
    }
    // Mod End 2018/01/31 QC#23569

    private void updateCrsSvcProcStsCdForCrsSvcRcvUpdStage(CRS_SVC_RCV_UPD_STAGETMsg tMsg, String crsSvcProcStsCd) {
        tMsg = (CRS_SVC_RCV_UPD_STAGETMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        setValue(tMsg.crsSvcProcStsCd, crsSvcProcStsCd);
        // Update CRS_SVC_RCV_UPD_STAGE
        EZDTBLAccessor.update(tMsg);
    }

    private boolean callFsrUpdateApiForUpdate(FSR_VISITTMsg fsrVisitTmsg, SVC_TASKTMsg svcTaskTmsg, CRS_SVC_RCV_UPD_STAGETMsg tMsg) {

        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();

        // START 2016/09/02 T.Tomita [QC#13858, MOD]
        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        setValue(apiPMsg.fsrNum, tMsg.fsrNum);
        setValue(apiPMsg.userId, USER_ID);
        setValue(apiPMsg.svcTaskStsCd, tMsg.crsSvcTaskStsCd);
        setValue(apiPMsg.svcMachMstrPk, svcTaskTmsg.svcMachMstrPk);
        setValue(apiPMsg.billToCustUpdFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.shipToCustUpdFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.taskList.no(0).svcTaskNum, svcTaskTmsg.svcTaskNum);
        setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        apiPMsg.taskList.setValidCount(1);
        if (hasValue(tMsg.svcCmntTxt_02)) {
            setValue(apiPMsg.svcMemoList.no(0).svcMemoTpCd, this.svcMemoTpCdForFsrUpdateApi);
            setValue(apiPMsg.svcMemoList.no(0).svcCmntTxt, tMsg.svcCmntTxt_02);
            apiPMsg.svcMemoList.setValidCount(1);
        }
        setValue(apiPMsg.serNum, svcTaskTmsg.serNum);
        setValue(apiPMsg.custMachCtrlNum, svcTaskTmsg.custMachCtrlNum);
        // mod start 2016/05/30 CSA Defect#8085
        setValue(apiPMsg.slsDt, ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.bizId));
        // mod end 2016/05/30 CSA Defect#8085
        // END 2016/09/02 T.Tomita [QC#13858, MOD]

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                addErrMsg(xxMsgId);
            }
            return false;
        }
        return true;
    }

    private boolean callFsrUpdateApiForCancel(SVC_TASKTMsg svcTaskTmsg, CRS_SVC_RCV_UPD_STAGETMsg tMsg) {

        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();

        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_CANCEL_FSR);
        setValue(apiPMsg.fsrNum, tMsg.fsrNum);
        setValue(apiPMsg.userId, USER_ID);
        // mod start 2016/05/30 CSA Defect#8085
        setValue(apiPMsg.slsDt, ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.bizId));
        // mod end 2016/05/30 CSA Defect#8085
        // START 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.taskList.no(0).svcTaskNum, svcTaskTmsg.svcTaskNum);
        setValue(apiPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        apiPMsg.taskList.setValidCount(1);

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                addErrMsg(xxMsgId);
            }
            return false;
        }
        return true;
    }

    // Mod Start 2018/01/31 QC#23569
    private String callAddTaskApiForFollowUpCall(FSR_VISITTMsg fsrVisitTmsg, SVC_TASKTMsg svcTaskTmsg, CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
        NSZC045001PMsg apiPMsg = new NSZC045001PMsg();

        // Follow Up
        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(apiPMsg.xxModeCd, NSZC0450_XX_MODE_CD_FOLLOW_UP_CALL);
        setValue(apiPMsg.xxModeCd, NSZC045001.PROCESS_MODE_ADD_TASK);
        setValue(apiPMsg.fsrNum, tMsg.fsrNum);
        setValue(apiPMsg.userId, USER_ID);
        // Add Start 2018/02/09 QC#23569
        setValue(apiPMsg.slsDt, this.slsDt);
        // Add End 2018/02/09 QC#23569
        Map<String, Object> fsrVisitTask = getFsrVisitTask(svcTaskTmsg.svcTaskNum.getValue(), fsrVisitTmsg.fsrNum.getValue());
        if (fsrVisitTask != null) {
            setValue(apiPMsg.fsrVisitNum, (String) fsrVisitTask.get("FSR_VISIT_NUM"));
        }
        setValue(apiPMsg.bypsPrfTechFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.dsSvcCallTpCd, tMsg.crsSvcCallTpCd);
        setValue(apiPMsg.svcBillTpCd, SVC_BILL_TP._8_NO_CHARGE);
        setValue(apiPMsg.svcTaskRcvDt, getDateForAPI(tMsg.crsSvcCratTsOrigTxt.getValue()));
        setValue(apiPMsg.svcTaskRcvTm, getTimeForAPI(tMsg.crsSvcCratTsOrigTxt.getValue()));
        setValue(apiPMsg.prtChrgFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.svcLborChrgFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.svcMachMstrPk, svcTaskTmsg.svcMachMstrPk);
        // START 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.xxSvcTaskList.no(0).svcTaskNum, tMsg.crsSvcS21SvcTaskNum);
        setValue(apiPMsg.xxSvcTaskList.no(0).techCd, fsrVisitTmsg.techCd);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcCallPrtyCd, getSvcCallPrtyCd(tMsg.crsSvcCallTpCd.getValue()));
        setValue(apiPMsg.xxSvcTaskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.xxSvcTaskList.no(0).mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.xxSvcMemoList.no(0).svcMemoTpCd, this.svcMemoTpCdForAddTaskApi);
        setValue(apiPMsg.xxSvcMemoList.no(0).svcCmntTxt, tMsg.svcCmntTxt_02);
        apiPMsg.xxSvcTaskList.setValidCount(1);
        apiPMsg.xxSvcMemoList.setValidCount(1);

        new NSZC045001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                addErrMsg(xxMsgId);
            }
            return null;
        }
        return apiPMsg.xxSvcTaskList.no(0).svcTaskNum.getValue();
    }
    // Mod End 2018/01/31 QC#23569

    private boolean callAddTaskApiForCancel(CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
        NSZC045001PMsg apiPMsg = new NSZC045001PMsg();
        // Cancel
        // START 2016/09/02 T.Tomita [QC#13858, MOD]
        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, NSZC0450_XX_MODE_CD_CANCEL_TASK);
        setValue(apiPMsg.fsrNum, tMsg.fsrNum);
        setValue(apiPMsg.userId, USER_ID);
        // add start 2020/04/01 QC#56441
        setValue(apiPMsg.slsDt, this.slsDt);
        // add end 2020/04/01 QC#56441
        setValue(apiPMsg.svcLborChrgFlg, ZYPConstant.FLG_OFF_N);
        // START 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // END 2018/08/07 K.Kojima [QC#27399,ADD]
        setValue(apiPMsg.xxSvcTaskList.no(0).svcTaskNum, tMsg.crsSvcS21SvcTaskNum);
        setValue(apiPMsg.xxSvcTaskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.xxSvcTaskList.no(0).mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        apiPMsg.xxSvcTaskList.setValidCount(1);
        if (hasValue(tMsg.svcCmntTxt_02)) {
            setValue(apiPMsg.xxSvcMemoList.no(0).svcMemoTpCd, this.svcMemoTpCdForAddTaskApi);
            setValue(apiPMsg.xxSvcMemoList.no(0).svcCmntTxt, tMsg.svcCmntTxt_02);
            apiPMsg.xxSvcMemoList.setValidCount(1);
        }
        // END 2016/09/02 T.Tomita [QC#13858, MOD]

        new NSZC045001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                addErrMsg(xxMsgId);
            }
            return false;
        }
        return true;
    }

    private String getSvcMemoTpCdForFsrUpdateApi() {
        DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(CONST_GRP_ID_SVC_MEMO_TP, CONST_CONST_CD_FSR_UPDATE_API);
        if (dsCondConstTMsg != null) {
            return dsCondConstTMsg.dsCondConstValTxt_01.getValue();
        } else {
            return null;
        }
    }

    private String getSvcMemoTpCdForAddTaskApi() {
        DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(CONST_GRP_ID_SVC_MEMO_TP, CONST_CONST_CD_ADD_TASK_API);
        if (dsCondConstTMsg != null) {
            return dsCondConstTMsg.dsCondConstValTxt_01.getValue();
        } else {
            return null;
        }
    }

    private DS_COND_CONSTTMsg getDsCondConstTMsg(String dsCondConstGrpId, String dsCondConstCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = new DS_COND_CONSTTMsg();
        setValue(dsCondConstTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsCondConstTMsg.dsCondConstGrpId, dsCondConstGrpId);
        setValue(dsCondConstTMsg.dsCondConstCd, dsCondConstCd);
        return (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(dsCondConstTMsg);
    }

    private String getDsSvcCallTpCd(String crsSvcCallTpCd) {
        CRS_SVC_CALL_TP_MAPTMsg param = new CRS_SVC_CALL_TP_MAPTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("crsSvcCallTpCd01", crsSvcCallTpCd);
        param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);

        CRS_SVC_CALL_TP_MAPTMsgArray list = (CRS_SVC_CALL_TP_MAPTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            return ((CRS_SVC_CALL_TP_MAPTMsg) list.get(0)).dsSvcCallTpCd.getValue();
        } else {
            return null;
        }
    }

    private String getFsrStsCd(String crsSvcFsrStsCd) {
        CRS_SVC_FSR_STS_MAPTMsg param = new CRS_SVC_FSR_STS_MAPTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("crsSvcFsrStsCd01", crsSvcFsrStsCd);
        param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);

        CRS_SVC_FSR_STS_MAPTMsgArray list = (CRS_SVC_FSR_STS_MAPTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            return ((CRS_SVC_FSR_STS_MAPTMsg) list.get(0)).fsrStsCd.getValue();
        } else {
            return null;
        }
    }

    private String getSvcTaskStsCd(String crsSvcTasStsCd) {
        CRS_SVC_TASK_STS_MAPTMsg param = new CRS_SVC_TASK_STS_MAPTMsg();
        param.setSQLID("001");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("crsSvcTaskStsCd01", crsSvcTasStsCd);
        param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);

        CRS_SVC_TASK_STS_MAPTMsgArray list = (CRS_SVC_TASK_STS_MAPTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            return ((CRS_SVC_TASK_STS_MAPTMsg) list.get(0)).svcTaskStsCd.getValue();
        } else {
            return null;
        }
    }

    // del start 2016/05/30 CSA Defect#8085
//    private String getFsrPrtyTxt(String svcCallPrtyCd) {
//        CRS_SVC_FSR_PRTY_MAPTMsg param = new CRS_SVC_FSR_PRTY_MAPTMsg();
//        param.setSQLID("001");
//        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        param.setConditionValue("svcCallPrtyCd01", svcCallPrtyCd);
//        param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
//
//        CRS_SVC_FSR_PRTY_MAPTMsgArray list = (CRS_SVC_FSR_PRTY_MAPTMsgArray) EZDTBLAccessor.findByCondition(param);
//        if (list.getValidCount() > 0) {
//            return ((CRS_SVC_FSR_PRTY_MAPTMsg) list.get(0)).fsrPrtyTxt.getValue();
//        } else {
//            return null;
//        }
//    }
    // del end 2016/05/30 CSA Defect#8085
    // Add Start 2018/01/31 QC#23569
    private String getSvcCallPrtyCd(String dsSvcCallTpCd) {
        if (!hasValue(dsSvcCallTpCd)) {
            return null;
        }
        DS_SVC_CALL_TPTMsg inMsg = new DS_SVC_CALL_TPTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsSvcCallTpCd, dsSvcCallTpCd);
        DS_SVC_CALL_TPTMsg outMsg = (DS_SVC_CALL_TPTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (outMsg == null) {
            return null;
        }
        return outMsg.svcCallPrtyCd.getValue();
    }
    // Add End 2018/01/31 QC#23569

    private SVC_TASKTMsg getSvcTask(CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
        // mod start 2016/09/15 CSA Defect#14552
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcTaskNum, tMsg.crsSvcS21SvcTaskNum);
        inMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (inMsg == null) {
            return null;
        }
        if (!hasValue(inMsg.crsSvcSrNum)) {
            updateCrsSvcSrNumOnSvcTask(tMsg, inMsg);
        }
        // Mod Start 2018/01/31 QC#23569
//        if (!matchSvcTaskAndCrsSvc(tMsg, inMsg)) {
//            return null;
//        }
        // Mod End 2018/01/31 QC#23569
        return inMsg;
        // mod end 2016/09/15 CSA Defect#14552
    }

    // add start 2016/09/15 CSA Defect#14552
    private void updateCrsSvcSrNumOnSvcTask(CRS_SVC_RCV_UPD_STAGETMsg tMsg, SVC_TASKTMsg inMsg) {
        setValue(inMsg.crsSvcSrNum, tMsg.crsSvcSrNum);
        // mod start 2019/07/08 QC#51170
        //setValue(inMsg.svcTaskStsCd, SVC_TASK_STS.IN_PROCESS);
        if (!SVC_TASK_STS.CANCELLED.equals(tMsg.crsSvcFsrStsCd.getValue()) && !SVC_TASK_STS.CANCELLED.equals(tMsg.crsSvcTaskStsCd.getValue())) {
            setValue(inMsg.svcTaskStsCd, SVC_TASK_STS.IN_PROCESS);
        }
        // mod end 2019/07/08 QC#51170
        EZDTBLAccessor.update(inMsg);
    }

    private boolean matchSvcTaskAndCrsSvc(CRS_SVC_RCV_UPD_STAGETMsg tMsg, SVC_TASKTMsg inMsg) {
        if (!tMsg.crsSvcSrNum.getValue().equals(inMsg.crsSvcSrNum.getValue())) {
            return false;
        }
        if (!tMsg.fsrNum.getValue().equals(inMsg.fsrNum.getValue())) {
            return false;
        }
        return true;
    }
    // add end 2016/09/15 CSA Defect#14552

    private FSR_VISITTMsg getFsrVisit(SVC_TASKTMsg tMsg) {
        FSR_VISITTMsg param = new FSR_VISITTMsg();
        param.setSQLID("003");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("fsrNum01", tMsg.fsrNum.getValue());
        param.setConditionValue("svcTaskNum01", tMsg.svcTaskNum.getValue());

        FSR_VISITTMsgArray list = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() > 0) {
            return (FSR_VISITTMsg) list.get(0);
        } else {
            return null;
        }
    }

    // del start 2016/05/30 CSA Defect#8085
//    private SVC_TASKTMsg getSvcTaskByFsrNumAndSvcTaskNum(CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
//        SVC_TASKTMsg param = new SVC_TASKTMsg();
//        param.setSQLID("006");
//        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        param.setConditionValue("fsrNum01", tMsg.fsrNum.getValue());
//        param.setConditionValue("svcTaskNum01", tMsg.crsSvcS21SvcTaskNum.getValue());
//
//        SVC_TASKTMsgArray list = (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(param);
//        if (list.getValidCount() > 0) {
//            return (SVC_TASKTMsg) list.get(0);
//        } else {
//            return null;
//        }
//    }
    // del end 2016/05/30 CSA Defect#8085

    private SVC_TASKTMsgArray getSvcTaskByFsrNumList(CRS_SVC_RCV_UPD_STAGETMsg tMsg) {
        SVC_TASKTMsg param = new SVC_TASKTMsg();
        param.setSQLID("005");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("fsrNum01", tMsg.fsrNum.getValue());

        return (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(param);
    }

    private SVC_TASKTMsg getSvcTaskByFsrNumAndSvcTaskNum(String fsrNum, String svcTaskNum) {
        SVC_TASKTMsg param = new SVC_TASKTMsg();
        param.setSQLID("006");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("fsrNum01", fsrNum);
        param.setConditionValue("svcTaskNum01", svcTaskNum);

        SVC_TASKTMsgArray list = (SVC_TASKTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(param);
        if (list.getValidCount() > 0) {
            return (SVC_TASKTMsg) list.get(0);
        } else {
            return null;
        }
    }

    private void addErrMsg(String msgId, String[] param) {
        String errMsg = null;
        if (param == null) {
            errMsg = S21MessageFunc.clspGetMessage(msgId);
        } else {
            errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        }
        this.errMsgList.add(errMsg);
    }

    private void addErrMsg(String msgId) {
        addErrMsg(msgId, null);
    }

    private String getDateForAPI(String target) {
        if (!hasValue(target)) {
            return null;
        }
        SimpleDateFormat beforeFormat = new SimpleDateFormat(MAIL_DATE_TIME_FORMAT);
        SimpleDateFormat afterFormat = new SimpleDateFormat(API_DATE_FORMAT);
        Date date = beforeFormat.parse(target, new ParsePosition(0));

        return afterFormat.format(date);
    }

    private String getTimeForAPI(String target) {
        if (!hasValue(target)) {
            return null;
        }
        SimpleDateFormat beforeFormat = new SimpleDateFormat(MAIL_DATE_TIME_FORMAT);
        SimpleDateFormat afterFormat = new SimpleDateFormat(API_TIME_FORMAT);
        Date date = beforeFormat.parse(target, new ParsePosition(0));

        return afterFormat.format(date);
    }

    private void sendMail() {

        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();

        S21MailAddress from = null;
        if (!fromAddrList.isEmpty()) {
            from = fromAddrList.get(0);
        }

        // Get To Address
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(this.glblCmpyCd, MAIL_TEMPLATE_ID);
        if (!hasValue(template.getBody())) {
            throw new S21AbendException(NSBM0135E, new String[] {"Mailtemplate", MAIL_TEMPLATE_ID });
        }

        // Create Subject and Body
        String currentTime = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        String newLine = System.getProperty("line.separator");
        StringBuilder msgBuf = new StringBuilder();
        for (String msg : this.errMsgList) {
            msgBuf.append(msg);
            msgBuf.append(newLine);
        }

        template.setTemplateParameter(MAIL_TEMPLATE_KEY_ID, BIZ_APP_ID);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_DATE, currentTime);
        template.setTemplateParameter(MAIL_TEMPLATE_KEY_MESSAGE, msgBuf.toString());

        // Call Mail API
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    // START 2016/04/12 [QC#5053, ADD]
    private Map<String, Object> getFsrVisitTask(String svcTaskNum, String fsrNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcTaskNum", svcTaskNum);
        param.put("fsrNum", fsrNum);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getFsrVisitTask", param);
    }
    // END   2016/04/12 [QC#5053, ADD]
    // Add Start 2018/01/31 QC#23569
    private SVC_TASKTMsg getSvcTaskForCrsSvcSrNum(String fsrNum, String crsSvcSrNum) {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        inMsg.setSQLID("009");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("fsrNum01", fsrNum);
        inMsg.setConditionValue("crsSvcSrNum01", crsSvcSrNum);

        SVC_TASKTMsgArray list = (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list.getValidCount() == 0) {
            return null;
        }
        return list.no(0);
    }
    // Add End 2018/01/31 QC#23569
}
