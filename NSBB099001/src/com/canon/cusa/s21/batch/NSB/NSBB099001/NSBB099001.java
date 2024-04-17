/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB099001;

import static com.canon.cusa.s21.batch.NSB.NSBB099001.constant.NSBB099001Constant.*;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.S21_PSNTMsg;
import business.db.SVC_MEMO_KEY_INFOTMsg;
import business.db.SVC_TASK_KEY_INFOTMsg;
import business.db.SVC_TASK_OTBD_MSGTMsg;
import business.db.SVC_TASK_RQST_DUPTMsg;
import business.parts.NSZC133001PMsg;
import business.parts.NSZC134001PMsg;
import business.parts.NSZC135001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC133001.NSZC133001;
import com.canon.cusa.s21.api.NSZ.NSZC134001.NSZC134001;
import com.canon.cusa.s21.api.NSZ.NSZC135001.NSZC135001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.OTBD_INTFC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_RQST_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * Service Call Update Coordination Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/02/27   Hitachi         T.Usui          Create          CSA-QC#60012
 * 2023/08/08   Hitachi         N.Takatsu       Update          QC#61771
 * 2023/09/08   Hitachi         K.Kitachi       Update          CSA-QC#61771
 * 2023/09/25   Hitachi         N.Takatsu       Update          CSA-QC#61771
 * 2023/11/20   Hitachi         N.Takatsu       Update          CSA-QC#61771
 *</pre>
 */
public class NSBB099001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    // -- Other Internal Variable ---------------
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** total search count */
    private int searchCnt = 0;

    /** Task success count */
    private int infoSccessCnt = 0;

    /** commitLimit */
    private int commitLimit = 0;

    /** S21MailTemplate : template */
    private S21MailTemplate template = null;

    private String[] rqstModeRegisterList;

    private String[] rqstModeCancelList;

    private String rqstModeLinefeed;

    private String errTarget = "";

    private String errInfo = null;

    private String errMailFormat;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSBB099001().executeBatch(NSBB099001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        termCd = TERM_CD.NORMAL_END;

        // Get the Global Company Code.
        // If an error occurs, throw Exception.
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Global Company Code" });
        }

        // Get the Sales Date.
        // If an error occurs, throw Exception.
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Sales Date" });
        }

        // Get the WALMART_STATE_REGISTER
        String rqstModeRegister = ZYPCodeDataUtil.getVarCharConstValue(WALMART_STATE_REGISTER, this.glblCmpyCd);
        if (!hasValue(rqstModeRegister)) {
            throw new S21AbendException(NSBM0069E, new String[] {WALMART_STATE_REGISTER });
        }
        this.rqstModeRegisterList = rqstModeRegister.split(",");

        // Get the WALMART_STATE_CANCEL
        String rqstModeCancel = ZYPCodeDataUtil.getVarCharConstValue(WALMART_STATE_CANCEL, this.glblCmpyCd);
        if (!hasValue(rqstModeCancel)) {
            throw new S21AbendException(NSBM0069E, new String[] {WALMART_STATE_CANCEL });
        }
        this.rqstModeCancelList = rqstModeCancel.split(",");

        // Get the WALMART_REPLACE_LINEFEED
        String rqstModeLinefeed = ZYPCodeDataUtil.getVarCharConstValue(WALMART_REPLACE_LINEFEED, this.glblCmpyCd);
        if (!hasValue(rqstModeLinefeed)) {
            throw new S21AbendException(NSBM0069E, new String[] {WALMART_REPLACE_LINEFEED });
        }
        this.rqstModeLinefeed = rqstModeLinefeed;

        // Get Commit Number
        this.commitLimit = getCommitCount();
        if (this.commitLimit <= 0 || this.commitLimit >= MAX_COMMIT_LIMIT) {
            this.commitLimit = MAX_COMMIT_LIMIT;
        }

        // Get Mail Setting
        this.template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(this.template.getBody())) {
            throw new S21AbendException(NSBM0077E, new String[] {MAIL_TEMPLATE_ID_01 });
        }

        // Get the ERR_MAIL_FORMAT
        String errMailFormat = ZYPCodeDataUtil.getVarCharConstValue(WALMART_ERR_MAIL_FORMAT, this.glblCmpyCd);
        if (!hasValue(errMailFormat)) {
            throw new S21AbendException(NSBM0069E, new String[] {WALMART_ERR_MAIL_FORMAT });
        }
        this.errMailFormat = errMailFormat;

        excParam = new S21SsmExecutionParameter();
        excParam.setFetchSize(FETCH_SIZE_MAX);
        excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        // Set term code and total count.
        setTermState(this.termCd, infoSccessCnt, searchCnt - infoSccessCnt, searchCnt);
    }

    /**
     * doProcess
     */
    private void doProcess() {

        doResend();

        doAddTask();

        doVendor();

        doAddNote();

        if (hasValue(this.errTarget)) {
            sendMail();
        }
    }

    private void doResend() {
        PreparedStatement prcIncrStmt = null;
        ResultSet rsInfo = null;

        try {
            prcIncrStmt = ssmLLClient.createPreparedStatement("getResendTarget", setParamForResendTargetList(), excParam);
            rsInfo = prcIncrStmt.executeQuery();

            while (rsInfo.next()) {
                this.searchCnt++;
                String intfcId = getStringFromRs(rsInfo, INTFC_ID);
                String incidentNo = getStringFromRs(rsInfo, CUST_CALL_ID);
                String fsrNum = getStringFromRs(rsInfo, FSR_NUM);
                String taskNum = getStringFromRs(rsInfo, SVC_TASK_NUM);
                String action = null;
                if (OTBD_INTFC_TP.WALMART_INBOUND_CALL_CREATION.equals(getStringFromRs(rsInfo, OTBD_INTFC_TP_CD))) {
                    action = CALL_CREATION;
                } else if (OTBD_INTFC_TP.WALMART_INBOUND_UPDATE_NOTE.equals(getStringFromRs(rsInfo, OTBD_INTFC_TP_CD))) {
                    action = UPDATE_NOTE;
                } else if (OTBD_INTFC_TP.WALMART_INBOUND_CALL_CANCEL.equals(getStringFromRs(rsInfo, OTBD_INTFC_TP_CD))) {
                    action = CALL_CANCEL;
                } else if (OTBD_INTFC_TP.WALMART_OUTBOUND_UPDATE_NOTE.equals(getStringFromRs(rsInfo, OTBD_INTFC_TP_CD))) {
                    action = UPDATE_NOTE;
                } else if (OTBD_INTFC_TP.WALMART_OUTBOUND_THIRD_PARTY.equals(getStringFromRs(rsInfo, OTBD_INTFC_TP_CD))) {
                    action = UPDATE_VENDOR;
                } else if (OTBD_INTFC_TP.WALMART_OUTBOUND_ADD_OR_FOLLOW_UP.equals(getStringFromRs(rsInfo, OTBD_INTFC_TP_CD))) {
                    action = ADD_TASK;
                }
                // START 2023/08/08 N.Takatsu [QC#61771, MOD]
//                if (UNSUBSCRIBE.equals(intfcId)) {
//                    NSZC132001PMsg paramMsg = new NSZC132001PMsg();
//
//                    setValue(paramMsg.custWbhkId, getStringFromRs(rsInfo, CUST_WBHK_ID));
//                    setValue(paramMsg.custTblNm, getStringFromRs(rsInfo, CUST_TBL_NM));
//                    setValue(paramMsg.custSysId, getStringFromRs(rsInfo, CUST_SYS_ID));
//
//                    if (callNszc1320(paramMsg)) {
//                        updateSvcTskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.PROCESSED);
//                        this.infoSccessCnt++;
//                    } else {
//                        addErrorMailMsg(action, intfcId, incidentNo, fsrNum, taskNum);
//
//                        updateSvcTskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.OUTBOUNDERROR);
//                    }
//
//                } else if (INCIDENT_UPDATE.equals(intfcId)) {
                if (INCIDENT_UPDATE.equals(intfcId)) {
                // END   2023/08/08 N.Takatsu [QC#61771, MOD]
                    NSZC133001PMsg paramMsg = new NSZC133001PMsg();

                    setValue(paramMsg.custSysId, getStringFromRs(rsInfo, CUST_SYS_ID));
                    setValue(paramMsg.custCallId, getStringFromRs(rsInfo, CUST_CALL_ID));
                    setValue(paramMsg.fsrNum, getStringFromRs(rsInfo, FSR_NUM));
                    setValue(paramMsg.svcCmntTxt, getStringFromRs(rsInfo, SVC_CMNT_TXT));

                    if (callNszc1330(paramMsg)) {
                        updateSvcTskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.PROCESSED);
                        this.infoSccessCnt++;
                    } else {
                        addErrorMailMsg(action, intfcId, incidentNo, fsrNum, taskNum);

                        updateSvcTskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.OUTBOUNDERROR);
                    }

                } else if (WEBHOOK.equals(intfcId)) {
                    NSZC134001PMsg paramMsg = new NSZC134001PMsg();

                    setValue(paramMsg.custWbhkId, getStringFromRs(rsInfo, CUST_WBHK_ID));
                    setValue(paramMsg.custTblNm, getStringFromRs(rsInfo, CUST_TBL_NM));
                    setValue(paramMsg.custSysId, getStringFromRs(rsInfo, CUST_SYS_ID));
                    setValue(paramMsg.svcTaskWatchFldTxt, getStringFromRs(rsInfo, SVC_TASK_WATCH_FLD_TXT));
                    setValue(paramMsg.svcTaskRtrnFldTxt, getStringFromRs(rsInfo, SVC_TASK_RTRN_FLD_TXT));

                    if (callNszc1340(paramMsg)) {
                        updateSvcTskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.PROCESSED);
                        this.infoSccessCnt++;
                    } else {
                        addErrorMailMsg(action, intfcId, incidentNo, fsrNum, taskNum);

                        updateSvcTskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.OUTBOUNDERROR);
                    }
                } else if (INCIDENT_HOLD.equals(intfcId)) {
                    NSZC135001PMsg paramMsg = new NSZC135001PMsg();

                    setValue(paramMsg.custSysId, getStringFromRs(rsInfo, CUST_SYS_ID));
                    setValue(paramMsg.custCallId, getStringFromRs(rsInfo, CUST_CALL_ID));
                    setValue(paramMsg.custRsnCd, getStringFromRs(rsInfo, CUST_RSN_CD));
                    setValue(paramMsg.svcCmntTxt, getStringFromRs(rsInfo, SVC_CMNT_TXT));
                    setValue(paramMsg.svcStsTxt, getStringFromRs(rsInfo, SVC_STS_TXT));

                    if (callNszc1350(paramMsg)) {
                        updateSvcTskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.PROCESSED);
                        this.infoSccessCnt++;
                    } else {
                        addErrorMailMsg(action, intfcId, incidentNo, fsrNum, taskNum);

                        updateSvcTskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.OUTBOUNDERROR);
                    }
                }
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prcIncrStmt, rsInfo);
        }
    }

    private void doAddTask() {
        PreparedStatement prcIncrStmt = null;
        ResultSet rsInfo = null;

        try {
            prcIncrStmt = ssmLLClient.createPreparedStatement("getTaskTarget", setParamForTaskTargetList(), excParam);
            rsInfo = prcIncrStmt.executeQuery();

            while (rsInfo.next()) {
                this.searchCnt++;
                NSZC133001PMsg paramMsg = new NSZC133001PMsg();
                // START 2023/11/20 N.Takatsu [QC#61771, MOD]
                String svcCmntTxt = String.format(TASK_MESSAGE, getStringFromRs(rsInfo, SVC_TASK_NUM), getStringFromRs(rsInfo, DS_SVC_CALL_TP_CD), getStringFromRs(rsInfo, DS_SVC_CALL_TP_DESC_TXT));
//                String svcCmntTxt = String.format(TASK_MESSAGE, getStringFromRs(rsInfo, SVC_TASK_NUM));
                // END   2023/11/20 N.Takatsu [QC#61771, MOD]

                setValue(paramMsg.custSysId, getStringFromRs(rsInfo, CUST_SYS_ID));
                setValue(paramMsg.custCallId, getStringFromRs(rsInfo, CUST_CALL_ID));
                setValue(paramMsg.fsrNum, getStringFromRs(rsInfo, FSR_NUM));
                setValue(paramMsg.svcCmntTxt, svcCmntTxt);

                insertSvcTaskKeyInf(rsInfo);

                if (callNszc1330(paramMsg)) {
                    insertSvcTaskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.PROCESSED, svcCmntTxt, OTBD_INTFC_TP.WALMART_OUTBOUND_ADD_OR_FOLLOW_UP);
                    this.infoSccessCnt++;
                } else {
                    String action = ADD_TASK;
                    String intfcId = getStringFromRs(rsInfo, INTFC_ID);
                    String incidentNo = getStringFromRs(rsInfo, CUST_CALL_ID);
                    String fsrNum = getStringFromRs(rsInfo, FSR_NUM);
                    String taskNum = getStringFromRs(rsInfo, SVC_TASK_NUM);

                    addErrorMailMsg(action, intfcId, incidentNo, fsrNum, taskNum);

                    insertSvcTaskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.OUTBOUNDERROR, svcCmntTxt, OTBD_INTFC_TP.WALMART_OUTBOUND_ADD_OR_FOLLOW_UP);
                }
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prcIncrStmt, rsInfo);
        }
    }

    private void doVendor() {
        PreparedStatement prcIncrStmt = null;
        ResultSet rsInfo = null;

        try {
            prcIncrStmt = ssmLLClient.createPreparedStatement("getVendorTarget", setParamForVendorTargetList(), excParam);
            rsInfo = prcIncrStmt.executeQuery();

            while (rsInfo.next()) {
                this.searchCnt++;
                NSZC133001PMsg paramMsg = new NSZC133001PMsg();

                // START 2023/11/20 N.Takatsu [QC#61771, MOD]
                String svcCmntTxt;
                String psnFirstNm = getPsnFirstNm(getStringFromRs(rsInfo, TECH_CD));
                if (!hasValue(psnFirstNm)) {
                    svcCmntTxt = String.format(VENDOR_MESSAGE, getStringFromRs(rsInfo, ITT_NUM));
                } else {
                    svcCmntTxt = String.format(VENDOR_MESSAGE_EXSITS, getStringFromRs(rsInfo, ITT_NUM), psnFirstNm);
                }
//                String svcCmntTxt = String.format(VENDOR_MESSAGE, getStringFromRs(rsInfo, ITT_NUM));
                // END   2023/11/20 N.Takatsu [QC#61771, MOD]

                setValue(paramMsg.custSysId, getStringFromRs(rsInfo, CUST_SYS_ID));
                setValue(paramMsg.custCallId, getStringFromRs(rsInfo, CUST_CALL_ID));
                setValue(paramMsg.fsrNum, getStringFromRs(rsInfo, FSR_NUM));
                setValue(paramMsg.svcCmntTxt, svcCmntTxt);

                updateSvcTaskRqstDup(rsInfo);

                if (callNszc1330(paramMsg)) {
                    insertSvcTaskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.PROCESSED, svcCmntTxt, OTBD_INTFC_TP.WALMART_OUTBOUND_THIRD_PARTY);
                    this.infoSccessCnt++;
                } else {
                    String action = UPDATE_VENDOR;
                    String intfcId = getStringFromRs(rsInfo, INTFC_ID);
                    String incidentNo = getStringFromRs(rsInfo, CUST_CALL_ID);
                    String fsrNum = getStringFromRs(rsInfo, FSR_NUM);
                    String taskNum = getStringFromRs(rsInfo, SVC_TASK_NUM);

                    addErrorMailMsg(action, intfcId, incidentNo, fsrNum, taskNum);

                    insertSvcTaskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.OUTBOUNDERROR, svcCmntTxt, OTBD_INTFC_TP.WALMART_OUTBOUND_THIRD_PARTY);
                }
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prcIncrStmt, rsInfo);
        }
    }

    private void doAddNote() {
        PreparedStatement prcIncrStmt = null;
        ResultSet rsInfo = null;

        try {
            prcIncrStmt = ssmLLClient.createPreparedStatement("getNoteTarget", setParamForNoteTargetList(), excParam);
            rsInfo = prcIncrStmt.executeQuery();

            while (rsInfo.next()) {
                this.searchCnt++;
                NSZC133001PMsg paramMsg = new NSZC133001PMsg();
                String svcCmntTxt = null;
                if (ZYPConstant.FLG_ON_Y.equals(this.rqstModeLinefeed)) {
                    svcCmntTxt = getStringFromRs(rsInfo, SVC_CMNT_TXT).replaceAll("\r\n|\r|\n", "\\n");
                } else {
                    svcCmntTxt = getStringFromRs(rsInfo, SVC_CMNT_TXT);
                }

                setValue(paramMsg.custSysId, getStringFromRs(rsInfo, CUST_SYS_ID));
                setValue(paramMsg.custCallId, getStringFromRs(rsInfo, CUST_CALL_ID));
                setValue(paramMsg.fsrNum, getStringFromRs(rsInfo, FSR_NUM));
                setValue(paramMsg.svcCmntTxt, svcCmntTxt);

                if (!hasValue(getStringFromRs(rsInfo, SVC_MEMO_KEY_INFO_PK))) {
                    insertSvcMemoKeyInfo(rsInfo);
                } else {
                    updateSvcMemoKeyInfo(rsInfo);
                }

                if (callNszc1330(paramMsg)) {
                    insertSvcTaskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.PROCESSED, svcCmntTxt, OTBD_INTFC_TP.WALMART_OUTBOUND_UPDATE_NOTE);
                    this.infoSccessCnt++;
                } else {
                    String action = UPDATE_NOTE;
                    String intfcId = getStringFromRs(rsInfo, INTFC_ID);
                    String incidentNo = getStringFromRs(rsInfo, CUST_CALL_ID);
                    String fsrNum = getStringFromRs(rsInfo, FSR_NUM);
                    String taskNum = getStringFromRs(rsInfo, SVC_TASK_NUM);

                    addErrorMailMsg(action, intfcId, incidentNo, fsrNum, taskNum);

                    insertSvcTaskOtbdMsg(rsInfo, SVC_RQST_PROC_STS.OUTBOUNDERROR, svcCmntTxt, OTBD_INTFC_TP.WALMART_OUTBOUND_UPDATE_NOTE);
                }
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prcIncrStmt, rsInfo);
        }
    }

    private void addErrorMailMsg(String action, String intfcId, String incidentNo, String fsrNum, String taskNum) {
        String mailStr = String.format(this.errMailFormat, action, intfcId, incidentNo, fsrNum, taskNum, this.errInfo);
        this.errTarget = this.errTarget + mailStr + CRLF;
    }

    private void sendMail() {
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        S21MailAddress from = addrFromList.get(0);

        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        String sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);
        String sendTime = ZYPDateUtil.formatEzd17ToDisp(sysTime);

        String header = String.format(this.errMailFormat, HEADER_ACTION, HEADER_INTFC_ID, HEADER_INCIDENT_NO, HEADER_FSR_NO, HEADER_TASK_NO, HEADER_ERROR);
        this.errTarget = header + CRLF + this.errTarget;

        template.setTemplateParameter("errDate", sendTime);
        template.setTemplateParameter("message", this.errTarget);

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(from);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    private Map<String, Object> setParamForResendTargetList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("svcRqstProcStsCd", SVC_RQST_PROC_STS.REQUESTED);
        return paramMap;
    }

    private Map<String, Object> setParamForTaskTargetList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("rqstModeRegisterList", this.rqstModeRegisterList);
        paramMap.put("rqstModeCancelList", this.rqstModeCancelList);
        paramMap.put("svcRqstProcStsCd", SVC_RQST_PROC_STS.PROCESSED);
        // START 2023/09/25 N.Takatsu [QC#61771, ADD]
        paramMap.put("rowNum", ROW_NUM);
        // END   2023/09/25 N.Takatsu [QC#61771, ADD]
        return paramMap;
    }

    private Map<String, Object> setParamForVendorTargetList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("rqstModeRegisterList", this.rqstModeRegisterList);
        paramMap.put("rqstModeCancelList", this.rqstModeCancelList);
        paramMap.put("svcRqstProcStsCd", SVC_RQST_PROC_STS.PROCESSED);
        // START 2023/09/08 K.Kitachi [QC#61771, ADD]
        paramMap.put("subStrPos25", SUB_STR_POS_25);
        // END   2023/09/08 K.Kitachi [QC#61771, ADD]
        // START 2023/09/25 N.Takatsu [QC#61771, ADD]
        paramMap.put("fsrVisitLtstFlg", ZYPConstant.FLG_ON_Y);
        paramMap.put("rowNum", ROW_NUM);
        // END   2023/09/25 N.Takatsu [QC#61771, ADD]
        return paramMap;
    }

    private Map<String, Object> setParamForNoteTargetList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("rqstModeRegisterList", this.rqstModeRegisterList);
        paramMap.put("rqstModeCancelList", this.rqstModeCancelList);
        paramMap.put("svcRqstProcStsCd", SVC_RQST_PROC_STS.PROCESSED);
        paramMap.put("svcMemoCatgCd", SVC_MEMO_CATG.DISPATCH_MEMO);
        paramMap.put("svcMemoTpCd", SVC_MEMO_TP.CUSTOMER_NOTE);
        // START 2023/09/25 N.Takatsu [QC#61771, ADD]
        paramMap.put("rowNum", ROW_NUM);
        // END   2023/09/25 N.Takatsu [QC#61771, ADD]
        return paramMap;
    }

    // START 2023/08/08 N.Takatsu [QC#61771, DEL]
//    private boolean callNszc1320(NSZC132001PMsg pMsg) {
//        new NSZC132001().execute(pMsg, ONBATCH_TYPE.BATCH);
//        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
//            this.errInfo = S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() });
//            return false;
//        }
//        pMsg.clear();
//        return true;
//    }
    // END   2023/08/08 N.Takatsu [QC#61771, DEL]

    private boolean callNszc1330(NSZC133001PMsg pMsg) {
        new NSZC133001().execute(pMsg, ONBATCH_TYPE.BATCH);
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            this.errInfo = S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() });
            return false;
        }
        pMsg.clear();
        return true;
    }

    private boolean callNszc1340(NSZC134001PMsg pMsg) {
        new NSZC134001().execute(pMsg, ONBATCH_TYPE.BATCH);
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            this.errInfo = S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() });
            return false;
        }
        pMsg.clear();
        return true;
    }

    private boolean callNszc1350(NSZC135001PMsg pMsg) {
        new NSZC135001().execute(pMsg, ONBATCH_TYPE.BATCH);
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            this.errInfo = S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), new String[] {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() });
            return false;
        }
        pMsg.clear();
        return true;
    }

    private void insertSvcTaskKeyInf(ResultSet rs) throws SQLException {

        SVC_TASK_KEY_INFOTMsg svcTskKeyInfTMsg = new SVC_TASK_KEY_INFOTMsg();

        setValue(svcTskKeyInfTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcTskKeyInfTMsg.svcTaskKeyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_KEY_INFO_SQ));
        setValue(svcTskKeyInfTMsg.fsrNum, getStringFromRs(rs, FSR_NUM));
        setValue(svcTskKeyInfTMsg.svcTaskNum, getStringFromRs(rs, SVC_TASK_NUM));

        S21FastTBLAccessor.insert(svcTskKeyInfTMsg);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(svcTskKeyInfTMsg.getReturnCode()) == false) {
            throw new S21AbendException(NSBM0121E, new String[] {SVC_TASK_KEY_INFO });
        }
    }

    private void updateSvcTaskRqstDup(ResultSet rs) throws SQLException {

        SVC_TASK_RQST_DUPTMsg svcTskRqstDupTMsg = new SVC_TASK_RQST_DUPTMsg();

        setValue(svcTskRqstDupTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcTskRqstDupTMsg.svcTaskRqstDupPk, getBigDecimalFromRs(rs, SVC_TASK_RQST_DUP_PK));

        svcTskRqstDupTMsg = (SVC_TASK_RQST_DUPTMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcTskRqstDupTMsg);

        setValue(svcTskRqstDupTMsg.ittNum, getStringFromRs(rs, ITT_NUM));

        S21FastTBLAccessor.update(svcTskRqstDupTMsg);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(svcTskRqstDupTMsg.getReturnCode()) == false) {
            throw new S21AbendException(NSBM0120E, new String[] {SVC_TASK_RQST_DUP });
        }
    }

    private void insertSvcMemoKeyInfo(ResultSet rs) throws SQLException {

        SVC_MEMO_KEY_INFOTMsg svcMemoKeyInfoTMsg = new SVC_MEMO_KEY_INFOTMsg();

        setValue(svcMemoKeyInfoTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcMemoKeyInfoTMsg.svcMemoKeyInfoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_KEY_INFO_SQ));
        setValue(svcMemoKeyInfoTMsg.fsrNum, getStringFromRs(rs, FSR_NUM));
        setValue(svcMemoKeyInfoTMsg.svcTaskNum, getStringFromRs(rs, SVC_TASK_NUM));
        setValue(svcMemoKeyInfoTMsg.svcMemoPk, getBigDecimalFromRs(rs, SVC_MEMO_PK));
        setValue(svcMemoKeyInfoTMsg.lastUpdTs, getStringFromRs(rs, LAST_UPD_TS));

        S21FastTBLAccessor.insert(svcMemoKeyInfoTMsg);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(svcMemoKeyInfoTMsg.getReturnCode()) == false) {
            throw new S21AbendException(NSBM0121E, new String[] {SVC_MEMO_KEY_INFO });
        }
    }

    private void updateSvcMemoKeyInfo(ResultSet rs) throws SQLException {

        SVC_MEMO_KEY_INFOTMsg svcMemoKeyInfoTMsg = new SVC_MEMO_KEY_INFOTMsg();

        setValue(svcMemoKeyInfoTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcMemoKeyInfoTMsg.svcMemoKeyInfoPk, getBigDecimalFromRs(rs, SVC_MEMO_KEY_INFO_PK));

        svcMemoKeyInfoTMsg = (SVC_MEMO_KEY_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcMemoKeyInfoTMsg);

        setValue(svcMemoKeyInfoTMsg.lastUpdTs, getStringFromRs(rs, LAST_UPD_TS));

        S21FastTBLAccessor.update(svcMemoKeyInfoTMsg);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(svcMemoKeyInfoTMsg.getReturnCode()) == false) {
            throw new S21AbendException(NSBM0120E, new String[] {SVC_MEMO_KEY_INFO });
        }
    }

    private void insertSvcTaskOtbdMsg(ResultSet rs, String svcRqstProcStsCd, String svcCmntTxt, String otbdIntfcTpCd) throws SQLException {

        SVC_TASK_OTBD_MSGTMsg svcTaskOtbdTMsg = new SVC_TASK_OTBD_MSGTMsg();

        setValue(svcTaskOtbdTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcTaskOtbdTMsg.svcTaskOtbdMsgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TASK_OTBD_MSG_SQ));
        setValue(svcTaskOtbdTMsg.svcTaskRqstDupPk, getBigDecimalFromRs(rs, SVC_TASK_RQST_DUP_PK));
        setValue(svcTaskOtbdTMsg.fsrNum, getStringFromRs(rs, FSR_NUM));
        setValue(svcTaskOtbdTMsg.svcTaskNum, getStringFromRs(rs, SVC_TASK_NUM));
        setValue(svcTaskOtbdTMsg.svcMemoPk, getBigDecimalFromRs(rs, SVC_MEMO_PK));
        setValue(svcTaskOtbdTMsg.svcCmntTxt, svcCmntTxt);
        setValue(svcTaskOtbdTMsg.svcWrkTxt, getStringFromRs(rs, SVC_WRK_TXT));
        setValue(svcTaskOtbdTMsg.custRsnCd, getStringFromRs(rs, CUST_RSN_CD));
        setValue(svcTaskOtbdTMsg.svcStsTxt, getStringFromRs(rs, SVC_STS_TXT));
        setValue(svcTaskOtbdTMsg.custWbhkId, getStringFromRs(rs, CUST_WBHK_ID));
        setValue(svcTaskOtbdTMsg.custTblNm, getStringFromRs(rs, CUST_TBL_NM));
        setValue(svcTaskOtbdTMsg.custSysId, getStringFromRs(rs, CUST_SYS_ID));
        setValue(svcTaskOtbdTMsg.svcTaskWatchFldTxt, getStringFromRs(rs, SVC_TASK_WATCH_FLD_TXT));
        setValue(svcTaskOtbdTMsg.svcTaskRtrnFldTxt, getStringFromRs(rs, SVC_TASK_RTRN_FLD_TXT));
        setValue(svcTaskOtbdTMsg.cratTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
        setValue(svcTaskOtbdTMsg.intfcId, INCIDENT_UPDATE);
        setValue(svcTaskOtbdTMsg.otbdIntfcTpCd, otbdIntfcTpCd);
        setValue(svcTaskOtbdTMsg.svcRqstProcStsCd, svcRqstProcStsCd);
        setValue(svcTaskOtbdTMsg.errInfoTxt, this.errInfo);
        setValue(svcTaskOtbdTMsg.custCallId, getStringFromRs(rs, CUST_CALL_ID));

        S21FastTBLAccessor.insert(svcTaskOtbdTMsg);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(svcTaskOtbdTMsg.getReturnCode()) == false) {
            throw new S21AbendException(NSBM0121E, new String[] {SVC_TASK_OTBD_MSG });
        }
        this.errInfo = null;
    }

    private void updateSvcTskOtbdMsg(ResultSet rs, String procStsCd) throws SQLException {

        SVC_TASK_OTBD_MSGTMsg svcTaskOtbdTMsg = new SVC_TASK_OTBD_MSGTMsg();

        setValue(svcTaskOtbdTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcTaskOtbdTMsg.svcTaskOtbdMsgPk, getBigDecimalFromRs(rs, SVC_TASK_OTBD_MSG_PK));

        svcTaskOtbdTMsg = (SVC_TASK_OTBD_MSGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcTaskOtbdTMsg);

        setValue(svcTaskOtbdTMsg.svcRqstProcStsCd, procStsCd);
        setValue(svcTaskOtbdTMsg.errInfoTxt, this.errInfo);

        S21FastTBLAccessor.update(svcTaskOtbdTMsg);
        if (S21FastTBLAccessor.RTNCD_NORMAL.equals(svcTaskOtbdTMsg.getReturnCode()) == false) {
            throw new S21AbendException(NSBM0120E, new String[] {SVC_TASK_OTBD_MSG });
        }
        this.errInfo = null;
    }

    private BigDecimal getBigDecimalFromRs(ResultSet rs, String keyStr) throws SQLException {
        BigDecimal ret = null;
        ret = rs.getBigDecimal(keyStr);
        return ret;
    }

    private String getStringFromRs(ResultSet rs, String keyStr) throws SQLException {
        String ret = "";
        ret = rs.getString(keyStr);
        if (!ZYPCommonFunc.hasValue(ret)) {
            ret = "";
        }
        return ret;
    }

    // START 2023/11/20 N.Takatsu [QC#61771, ADD]
    private String getPsnFirstNm(String psnCd) {
        S21_PSNTMsg prmTMsg = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.psnCd, psnCd);
        S21_PSNTMsg psnTMsg = (S21_PSNTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
        if (psnTMsg == null) {
            return null;
        } else {
            return psnTMsg.psnFirstNm.getValue();
        }
    }
    // END   2023/11/20 N.Takatsu [QC#61771, ADD]
}
