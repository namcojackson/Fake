/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB093001;

import static com.canon.cusa.s21.batch.NSB.NSBB093001.constant.NSBB093001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;

import business.db.CITS_SVC_TASK_CRAT_STAGETMsg;
import business.db.CITS_SVC_TASK_UPD_STAGETMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.DS_SVC_CALL_TPTMsgArray;
import business.db.FSRTMsg;
import business.db.NSBI0630_01TMsg;
import business.db.NSBI0630_01TMsgArray;
import business.db.NSBI0640_01TMsg;
import business.db.NSBI0640_01TMsgArray;
import business.db.NSBI0650_01TMsg;
import business.db.NSBI0650_01TMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC045001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SendMailForErrorInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CITS_PLSFT_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CITS_PLSFT_UPD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Receive Task from PeopleSoft Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 07/25/2016   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBB093001 extends S21BatchMain {

    /** Transaction table Access */
    private S21TransactionTableAccessor trxAccess;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Process Date */
    private String procDt;

    /** Process Time Stamp */
    private String procTs;

    /** Interface Id */
    private String intfcId = null;

    /** Transaction ID */
    private BigDecimal trxId;

    /** Transaction Id List */
    private BigDecimal[] trxIdList;

    /** Total Normal Count */
    private int totalNormalCount = 0;

    /** Total Error Count */
    private int totalErrorCount = 0;

    /** Error List for error mail */
    private List<String> errList;

    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        /* Initialize S21BatchMain */
        new NSBB093001().executeBatch(NSBB093001.class.getSimpleName());
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

        // Get Interface Id
        this.intfcId = getInterfaceID();
        if (!hasValue(this.intfcId)) {
            throw new S21AbendException(NSBM0032E, new String[] {INTERFACE_ID });
        }

        // Get System Timestamp
        this.procTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_DT_TM);
        this.procDt = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);

        this.trxAccess = new S21TransactionTableAccessor();
        this.trxIdList = trxAccess.getIntegrationRecord(this.intfcId);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.errList = new ArrayList<String>();
    }

    @Override
    protected void mainRoutine() {

        for (BigDecimal trgtTrxId : this.trxIdList) {

            this.trxId = trgtTrxId;

            // do main process
            doMainProc();

            // Update INTERFACE_TRANSACTION Info
            this.trxAccess.endIntegrationProcess(this.intfcId, trgtTrxId);
            commit();
        }
        // Send Error Mail
        sendErrMail();
    }

    @Override
    protected void termRoutine() {
        if (this.totalErrorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalNormalCount, this.totalErrorCount);
    }

    private void doMainProc() {
        CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg = new CITS_SVC_TASK_UPD_STAGETMsg();
        NSZC043001PMsg apiPMsg = new NSZC043001PMsg();
        boolean isNomal = true;

        if (INTFC_ID_CREATE.equals(this.intfcId)) {
            NSBI0630_01TMsgArray inTMsgArray = getCreateTask();
            for (int i = 0; i < inTMsgArray.getValidCount(); i++) {
                NSBI0630_01TMsg cratTMsg = inTMsgArray.no(i);
                // insert CITS_SVC_TASK_CRAT_STAGE Info
                isNomal = insertCitsCratStageData(setCitsSvcTaskCratStage(cratTMsg, null, null), inUpdMsg);

                // Validation
                if (isNomal && checkTargetInfo(cratTMsg, null, null, inUpdMsg, apiPMsg)) {
                    // Create Task(Call API)
                    if (callFsrUpdateApi(apiPMsg, cratTMsg, null, null, inUpdMsg)) {
                        commit();
                    } else {
                        rollback();
                    }
                }

                // insert CITS_SVC_TASK_UPD_STAGE Info
                insertCitsUpdStageData(setCitsSvcTaskUpdStage(cratTMsg, null, inUpdMsg, apiPMsg.taskList.no(0).svcTaskNum.getValue()));

                // add Process Count
                addProcCnt(inUpdMsg);

                inUpdMsg.clear();
                apiPMsg.clear();
                ZYPTableUtil.clear(apiPMsg.taskList);
                ZYPTableUtil.clear(apiPMsg.svcMemoList);
            }
        } else if (INTFC_ID_UPD_NOTES.equals(this.intfcId)) {
            NSBI0640_01TMsgArray inTMsgArray = getUpdateNotesTask();
            for (int i = 0; i < inTMsgArray.getValidCount(); i++) {
                NSBI0640_01TMsg updNotesTMsg = inTMsgArray.no(i);
                // insert CITS_SVC_TASK_CRAT_STAGE Info
                isNomal = insertCitsCratStageData(setCitsSvcTaskCratStage(null, updNotesTMsg, null), inUpdMsg);

                // Validation
                if (isNomal && checkTargetInfo(null, updNotesTMsg, null, inUpdMsg, apiPMsg)) {
                    // Create Task(Call API)
                    callFsrUpdateApi(apiPMsg, null, updNotesTMsg, null, inUpdMsg);
                }

                // add Process Count
                addProcCnt(inUpdMsg);

                inUpdMsg.clear();
                apiPMsg.clear();
                ZYPTableUtil.clear(apiPMsg.taskList);
                ZYPTableUtil.clear(apiPMsg.svcMemoList);
            }
        } else if (INTFC_ID_CANCEL.equals(this.intfcId)) {
            NSBI0650_01TMsgArray inTMsgArray = getCancelTask();
            for (int i = 0; i < inTMsgArray.getValidCount(); i++) {
                NSBI0650_01TMsg cancTMsg = inTMsgArray.no(i);
                // insert CITS_SVC_TASK_CRAT_STAGE Info
                isNomal = insertCitsCratStageData(setCitsSvcTaskCratStage(null, null, cancTMsg), inUpdMsg);

                // Validation
                if (isNomal && checkTargetInfo(null, null, cancTMsg, inUpdMsg, apiPMsg)) {
                    // check FSR related SVC_TASK Info
                    if (getSvcTaskCnt(apiPMsg.fsrNum.getValue()) > 1) {
                        // Create Task(Call API)
                        callAddTaskApi(apiPMsg, cancTMsg, inUpdMsg);
                    } else if (getSvcTaskCnt(apiPMsg.fsrNum.getValue()) == 1) {
                        // Create Task(Call API)
                        callFsrUpdateApi(apiPMsg, null, null, cancTMsg, inUpdMsg);
                    }
                }

                // insert CITS_SVC_TASK_UPD_STAGE Info
                insertCitsUpdStageData(setCitsSvcTaskUpdStage(null, cancTMsg, inUpdMsg, null));

                // add Process Count
                addProcCnt(inUpdMsg);

                inUpdMsg.clear();
                apiPMsg.clear();
                ZYPTableUtil.clear(apiPMsg.taskList);
                ZYPTableUtil.clear(apiPMsg.svcMemoList);
            }
        }
    }

    private void addProcCnt(CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg) {
        if (CITS_PLSFT_PROC.ERROR.equals(inUpdMsg.s21FldSvcProcCd.getValue())) {
            this.totalErrorCount++;
        } else {
            this.totalNormalCount++;
        }
    }

    private boolean checkTargetInfo(NSBI0630_01TMsg cratTMsg, NSBI0640_01TMsg updNotesTMsg, NSBI0650_01TMsg cancTMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg, NSZC043001PMsg apiPMsg) {
        // in the case of Create Service Task
        if (cratTMsg != null) {
            // get DS_SVC_CALL_TP Info
            DS_SVC_CALL_TPTMsgArray getDsSvcCallTpTMsgArray = getDsSvcCallTp(cratTMsg.dsSvcCallTpDescTxt);
            // get SVC_MACH_MSTR Info
            SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = getSvcMachMstr(cratTMsg.serNum);
            // Check Target Info
            if (!checkTargetInfoForCrat(cratTMsg, inUpdMsg, getDsSvcCallTpTMsgArray, svcMachMstrTMsgArray)) {
                return false;
            }
            // set FSR Update API
            setValue(apiPMsg.dsSvcCallTpCd, getDsSvcCallTpTMsgArray.no(0).dsSvcCallTpCd);
            setValue(apiPMsg.svcMachMstrPk, svcMachMstrTMsgArray.no(0).svcMachMstrPk);

            // Check CITS_PLN_START Info
            if (!checkAndSetCalculationTs(cratTMsg, inUpdMsg, apiPMsg)) {
                return false;
            }

            // in the case of Update Notes
        } else if (updNotesTMsg != null) {
            // get Service Task Info
            Map<String, Object> svcTaskInfo = getSvcTaskInfo(updNotesTMsg.svcTaskNum);
            if (!checkTargetInfoForUpdNotes(updNotesTMsg, inUpdMsg, svcTaskInfo)) {
                return false;
            }
            // set FSR Update API
            setValue(apiPMsg.fsrNum, (String) svcTaskInfo.get("FSR_NUM"));
            setValue(apiPMsg.svcMachMstrPk, (BigDecimal) svcTaskInfo.get("SVC_MACH_MSTR_PK"));
            setValue(apiPMsg.svcCallIncdtDt, (String) svcTaskInfo.get("SVC_CALL_INCDT_DT"));
            setValue(apiPMsg.svcCallIncdtTm, (String) svcTaskInfo.get("SVC_CALL_INCDT_TM"));
            setValue(apiPMsg.firstProdCtrlCd, (String) svcTaskInfo.get("FIRST_PROD_CTRL_CD"));
            setValue(apiPMsg.taskList.no(0).svcTaskNum, (String) svcTaskInfo.get("SVC_TASK_NUM"));
            setValue(apiPMsg.taskList.no(0).erlStartTs, (String) svcTaskInfo.get("ERL_START_TS"));
            setValue(apiPMsg.taskList.no(0).lateStartTs, (String) svcTaskInfo.get("LATE_START_TS"));

            // in the case of Cancel Service Request
        } else if (cancTMsg != null) {
            // get Service Task Info
            Map<String, Object> svcTaskInfo = getSvcTaskInfo(cancTMsg.svcTaskNum);
            if (!checkTargetInfoForCanc(cancTMsg, inUpdMsg, svcTaskInfo)) {
                return false;
            }
            // set FSR Update API
            setValue(apiPMsg.fsrNum, (String) svcTaskInfo.get("FSR_NUM"));
            setValue(apiPMsg.taskList.no(0).svcTaskNum, (String) svcTaskInfo.get("SVC_TASK_NUM"));
        }
        return true;
    }

    private boolean checkTargetInfoForCrat(NSBI0630_01TMsg cratTMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg, DS_SVC_CALL_TPTMsgArray getDsSvcCallTpTMsgArray, SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray) {
        String[] msg;
        // check update type
        if (isErrorUpdTp(cratTMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.CALL_CREATION_FROM_CITS)) {
            msg = new String[] {UPDATE_TYPE, PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0170E, msg);
            return false;
        }
        // check mandatory
        if (!hasValue(cratTMsg.plsftTaskNum)) {
            msg = new String[] {PLSFT_SVC_ORD_ID, PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0171E, msg);
            return false;
        }
        if (!hasValue(cratTMsg.dsSvcCallTpDescTxt)) {
            msg = new String[] {TASK_TYPE_NM, PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0171E, msg);
            return false;
        }
        if (!hasValue(cratTMsg.serNum)) {
            msg = new String[] {SER_NUM, PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0171E, msg);
            return false;
        }
        // check exist in FSR
        if (checkFsrExist(cratTMsg)) {
            msg = new String[] {PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0172E, msg);
            return false;
        }

        // check Task Type Name in DS_SVC_CALL_TP
        if (!checkDsSvcCallTpExist(getDsSvcCallTpTMsgArray)) {
            msg = new String[] {TASK_TYPE_NM, PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0173E, msg);
            return false;
        }
        // check with SVC_MACH_MSTR Info

        if (!checkSvcMachMstrExist(svcMachMstrTMsgArray)) {
            msg = new String[] {SER_NUM, PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0173E, msg);
            return false;
        }
        if (!validateWithSvcMachMstr(cratTMsg, svcMachMstrTMsgArray)) {
            msg = new String[] {PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0174E, msg);
            return false;
        }
        return true;
    }

    private boolean checkTargetInfoForUpdNotes(NSBI0640_01TMsg updNotesTMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg, Map<String, Object> svcTaskInfo) {
        String[] msg;
        // check update type
        if (isErrorUpdTp(updNotesTMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.ADD_NOTES_FROM_CITS)) {
            msg = new String[] {UPDATE_TYPE, PLSFT_TASK_NUM + " = " + setKeyMessage(updNotesTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0170E, msg);
            return false;
        }
        if (!checkSvcTaskExist(svcTaskInfo)) {
            msg = new String[] {SVC_TASK_INFO, PLSFT_TASK_NUM + " = " + setKeyMessage(updNotesTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0173E, msg);
            return false;
        } else if (!checkSvcTaskSts(svcTaskInfo)) {
            msg = new String[] {PLSFT_TASK_NUM + " = " + setKeyMessage(updNotesTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0176E, msg);
            return false;
        }
        return true;
    }

    private boolean checkTargetInfoForCanc(NSBI0650_01TMsg cancTMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg, Map<String, Object> svcTaskInfo) {
        String[] msg;
        // check update type
        if (isErrorUpdTp(cancTMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.CALL_CANCELLATION_FROM_CITS)) {
            msg = new String[] {UPDATE_TYPE, PLSFT_TASK_NUM + " = " + setKeyMessage(cancTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0170E, msg);
            return false;
        }
        if (!checkSvcTaskExist(svcTaskInfo)) {
            msg = new String[] {SVC_TASK_INFO, PLSFT_TASK_NUM + " = " + setKeyMessage(cancTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0173E, msg);
            return false;
        } else if (!checkSvcTaskSts(svcTaskInfo)) {
            msg = new String[] {PLSFT_TASK_NUM + " = " + setKeyMessage(cancTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0176E, msg);
            return false;
        }
        return true;
    }

    private boolean checkCitsPlnStart(NSBI0630_01TMsg cratTMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg, List<Map<String, Object>> citsPlnStartMap) {
        String[] msg;
        if (citsPlnStartMap == null || citsPlnStartMap.isEmpty() || citsPlnStartMap.size() > 1) {
            msg = new String[] {PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0175E, msg);
            return false;
        }
        if (citsPlnStartMap.get(0).get("SVC_TASK_PLN_TM") == null || citsPlnStartMap.get(0).get("SVC_TASK_PLN_TM") == null
                || citsPlnStartMap.get(0).get("SVC_TASK_RCV_CD") == null || citsPlnStartMap.get(0).get("SVC_TASK_RCV_TM") == null
                || citsPlnStartMap.get(0).get("SVC_CALL_INCDT_CD") == null || citsPlnStartMap.get(0).get("SVC_CALL_INCDT_TM") == null) {
            msg = new String[] {PLSFT_TASK_NUM + " = " + setKeyMessage(cratTMsg.plsftTaskNum) };
            setErrorInfoInUpdStage(inUpdMsg, NSBM0177E, msg);
            return false;
        }
        return true;
    }

    private boolean checkAndSetCalculationTs(NSBI0630_01TMsg cratTMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg, NSZC043001PMsg apiPMsg) {
        Calendar svcTaskRsvCalendar = Calendar.getInstance();
        String svcTaskRcvDt = this.procDt;
        String svcTaskRcsHhmmss = this.procTs.substring(HHMMDD_START, HHMMDD_END);
        if (hasValue(cratTMsg.svcTaskRcvTs)) {
            svcTaskRcvDt = cratTMsg.svcTaskRcvTs.getValue().substring(YYYYMMDD_START, YYYYMMDD_END);
            svcTaskRcsHhmmss = cratTMsg.svcTaskRcvTs.getValue().substring(HHMMDD_START, HHMMDD_END);
        }
        Date inParamDate = toDate(svcTaskRcvDt + svcTaskRcsHhmmss, FORMAT_SYS_DT_TM);
        svcTaskRsvCalendar.setTime(inParamDate);

        List<Map<String, Object>> citsPlnStartMap = getCitsPlnStartInfo(svcTaskRsvCalendar, svcTaskRcsHhmmss);
        if (!checkCitsPlnStart(cratTMsg, inUpdMsg, citsPlnStartMap)) {
            return false;
        }

        // Calculate service date
        // set ERL_START_TS
        String erlSrartTs = getCalcuTs(svcTaskRcvDt, inParamDate, (String) citsPlnStartMap.get(0).get("SVC_TASK_PLN_CD"), (String) citsPlnStartMap.get(0).get("SVC_TASK_PLN_TM"));
        setValue(apiPMsg.taskList.no(0).erlStartTs, erlSrartTs);

        // set SVC_RSV_TS
        String svcTascRcvDt = cratTMsg.svcTaskRcvTs.getValue().substring(YYYYMMDD_START, YYYYMMDD_END);
        String svcTascRcvTm = cratTMsg.svcTaskRcvTs.getValue().substring(HHMMDD_START, HHMMDD_END);
        if (citsPlnStartMap.get(0).get("SVC_TASK_RCV_CD") != null && !"0".equals((String) citsPlnStartMap.get(0).get("SVC_TASK_RCV_CD"))) {
            String svcRcvTs = getCalcuTs(svcTaskRcvDt, inParamDate, (String) citsPlnStartMap.get(0).get("SVC_TASK_RCV_CD"), (String) citsPlnStartMap.get(0).get("SVC_TASK_RCV_TM"));
            svcTascRcvDt = svcRcvTs.substring(YYYYMMDD_START, YYYYMMDD_END);
            svcTascRcvTm = svcRcvTs.substring(HHMMDD_START, HHMMDD_END);
        }
        setValue(apiPMsg.svcTaskRcvDt, svcTascRcvDt);
        setValue(apiPMsg.svcTaskRcvTm, svcTascRcvTm);

        // set SVC_CALL_INCDT_TS
        String svcCallIncdtDt = cratTMsg.svcTaskRcvTs.getValue().substring(YYYYMMDD_START, YYYYMMDD_END);
        String svcCallIncdtTm = cratTMsg.svcTaskRcvTs.getValue().substring(HHMMDD_START, HHMMDD_END);
        if (citsPlnStartMap.get(0).get("SVC_CALL_INCDT_CD") != null && !"0".equals((String) citsPlnStartMap.get(0).get("SVC_CALL_INCDT_CD"))) {
            String svcCallIncdtTs = getCalcuTs(svcTaskRcvDt, inParamDate, (String) citsPlnStartMap.get(0).get("SVC_CALL_INCDT_CD"), (String) citsPlnStartMap.get(0).get("SVC_CALL_INCDT_TM"));
            svcCallIncdtDt = svcCallIncdtTs.substring(YYYYMMDD_START, YYYYMMDD_END);
            svcCallIncdtTm = svcCallIncdtTs.substring(HHMMDD_START, HHMMDD_END);
        }
        setValue(apiPMsg.svcCallIncdtDt, svcCallIncdtDt);
        setValue(apiPMsg.svcCallIncdtTm, svcCallIncdtTm);
        return true;
    }

    private String getCalcuTs(String svcTaskRcvDt, Date inParamDate, String plnCd, String plnTm) {
        if (checkNumber(plnCd)) {
            int addDay = Integer.valueOf(plnCd);
            return setTs(ZYPDateUtil.addBusinessDay(this.glblCmpyCd, svcTaskRcvDt, addDay).concat(plnTm));
        } else {
            Calendar tsCalendar = Calendar.getInstance();
            SimpleDateFormat formatYyyymmdd = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            SimpleDateFormat formatWod = new SimpleDateFormat(FORMAT_WEEK_OF_DAY, Locale.US);
            tsCalendar.setTime(inParamDate);
            boolean isEndOfCalcu = false;
            while (!isEndOfCalcu) {
                tsCalendar.add(Calendar.DAY_OF_MONTH, 1);
                if (plnCd.equals(formatWod.format(tsCalendar.getTime()))) {
                    isEndOfCalcu = true;
                }
            }
            String outputDt = formatYyyymmdd.format(tsCalendar.getTime());
            if (!ZYPDateUtil.isBusinessDay(this.glblCmpyCd, outputDt)) {
                outputDt = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, outputDt, 1);
            }
            return setTs(outputDt.concat(plnTm));
        }
    }

    private boolean isErrorUpdTp(EZDTStringItem strItem, String checkUpdTp) {
        if (!hasValue(strItem) || !checkUpdTp.equals(strItem.getValue())) {
            return true;
        }
        return false;
    }

    private boolean checkFsrExist(NSBI0630_01TMsg cratTMsg) {
        if (getFsrCnt(cratTMsg) != 0) {
            return true;
        }
        return false;
    }

    private boolean checkDsSvcCallTpExist(DS_SVC_CALL_TPTMsgArray getDsSvcCallTpTMsgArray) {
        if (getDsSvcCallTpTMsgArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private boolean checkSvcMachMstrExist(SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray) {
        if (svcMachMstrTMsgArray.getValidCount() == 0) {
            return false;
        }
        return true;
    }

    private boolean checkSvcTaskExist(Map<String, Object> svcTaskInfo) {
        if (svcTaskInfo == null || svcTaskInfo.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean checkSvcTaskSts(Map<String, Object> svcTaskInfo) {
        if (SVC_TASK_STS.CLOSED.equals((String) svcTaskInfo.get("SVC_TASK_STS_CD")) || SVC_TASK_STS.CANCELLED.equals((String) svcTaskInfo.get("SVC_TASK_STS_CD"))) {
            return false;
        }
        return true;
    }

    private boolean validateWithSvcMachMstr(NSBI0630_01TMsg cratTMsg, SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray) {
        if (svcMachMstrTMsgArray.getValidCount() > 1) {
            return false;
        }
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = svcMachMstrTMsgArray.no(0);
        if (hasValue(svcMachMstrTMsg.curLocNum) && !hasValue(cratTMsg.shipToCustCd)) {
            return false;
        } else if (!hasValue(svcMachMstrTMsg.curLocNum) && hasValue(cratTMsg.shipToCustCd)) {
            return false;
        } else if (!hasValue(svcMachMstrTMsg.curLocNum) && !hasValue(cratTMsg.shipToCustCd)) {
            return true;
        } else {
            if (!svcMachMstrTMsg.curLocNum.getValue().equals(cratTMsg.shipToCustCd.getValue())) {
                return false;
            }
        }
        return true;
    }

    private CITS_SVC_TASK_CRAT_STAGETMsg setCitsSvcTaskCratStage(NSBI0630_01TMsg cratTMsg, NSBI0640_01TMsg updNotesTMsg, NSBI0650_01TMsg cancTMsg) {
        CITS_SVC_TASK_CRAT_STAGETMsg tMsg = new CITS_SVC_TASK_CRAT_STAGETMsg();

        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.citsSvcTaskCratStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CITS_SVC_TASK_CRAT_STAGE_SQ));
        if (cratTMsg != null) {
            setValue(tMsg.citsPlsftUpdTpCd, cratTMsg.citsPlsftUpdTpCd);
            setValue(tMsg.plsftTaskNum, cratTMsg.plsftTaskNum);
            setValue(tMsg.dsSvcCallTpDescTxt, cratTMsg.dsSvcCallTpDescTxt);
            setValue(tMsg.serNum, cratTMsg.serNum);
            setValue(tMsg.shipToCustCd, cratTMsg.shipToCustCd);
            setValue(tMsg.svcTaskRcvTs, cratTMsg.svcTaskRcvTs);
            setValue(tMsg.dsAcctNm, cratTMsg.dsAcctNm);
            setValue(tMsg.ctacPsnFirstNm, cratTMsg.ctacPsnFirstNm);
            setValue(tMsg.ctacPsnLastNm, cratTMsg.ctacPsnLastNm);
            setValue(tMsg.custTelNum, cratTMsg.custTelNum);
            setValue(tMsg.custTelExtnNum, cratTMsg.custTelExtnNum);
            setValue(tMsg.custEmlAddr, cratTMsg.custEmlAddr);
            setValue(tMsg.svcCmntTxt, cratTMsg.svcCmntTxt);
            setValue(tMsg.svcPblmTpCd, cratTMsg.svcPblmTpCd);
        } else if (updNotesTMsg != null) {
            setValue(tMsg.citsPlsftUpdTpCd, updNotesTMsg.citsPlsftUpdTpCd);
            setValue(tMsg.svcTaskNum, updNotesTMsg.svcTaskNum);
            setValue(tMsg.plsftTaskNum, updNotesTMsg.plsftTaskNum);
            setValue(tMsg.svcCmntTxt, updNotesTMsg.svcCmntTxt);
        } else if (cancTMsg != null) {
            setValue(tMsg.citsPlsftUpdTpCd, cancTMsg.citsPlsftUpdTpCd);
            setValue(tMsg.svcTaskNum, cancTMsg.svcTaskNum);
            setValue(tMsg.plsftTaskNum, cancTMsg.plsftTaskNum);
            setValue(tMsg.svcCmntTxt, cancTMsg.svcCmntTxt);
        }
        return tMsg;
    }

    private CITS_SVC_TASK_UPD_STAGETMsg setCitsSvcTaskUpdStage(NSBI0630_01TMsg cratTMsg, NSBI0650_01TMsg cancTMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg, String svcTaskNum) {
        setValue(inUpdMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inUpdMsg.citsSvcTaskUpdStagePk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CITS_SVC_TASK_UPD_STAGE_SQ));
        if (!CITS_PLSFT_PROC.ERROR.equals(inUpdMsg.s21FldSvcProcCd.getValue())) {
            setValue(inUpdMsg.s21FldSvcProcCd, CITS_PLSFT_PROC.SUCCESS);
        }
        if (cratTMsg != null) {
            setValue(inUpdMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.CALL_CREATION_RESULT_FROM_CITS);
            setValue(inUpdMsg.svcTaskNum, svcTaskNum);
            setValue(inUpdMsg.plsftTaskNum, cratTMsg.plsftTaskNum);
            setValue(inUpdMsg.dsSvcCallTpDescTxt, cratTMsg.dsSvcCallTpDescTxt);
            setValue(inUpdMsg.svcCmntTxt, cratTMsg.svcCmntTxt);
        } else if (cancTMsg != null) {
            setValue(inUpdMsg.citsPlsftUpdTpCd, CITS_PLSFT_UPD_TP.CALL_CANCELLATION_RESULT_FROM_CITS);
            setValue(inUpdMsg.svcTaskNum, cancTMsg.svcTaskNum);
            setValue(inUpdMsg.plsftTaskNum, cancTMsg.plsftTaskNum);
            setValue(inUpdMsg.svcCmntTxt, cancTMsg.svcCmntTxt);
        }
        return inUpdMsg;
    }

    private boolean insertCitsCratStageData(CITS_SVC_TASK_CRAT_STAGETMsg inMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg) {
        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            setErrorInfoInUpdStage(inUpdMsg, NSBM0121E, new String[] {inMsg.getTableName(), inMsg.citsSvcTaskCratStagePk.getValue().toString() });
            return false;
        }
        commit();
        return true;
    }

    private boolean insertCitsUpdStageData(CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg) {
        S21FastTBLAccessor.insert(inUpdMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inUpdMsg.getReturnCode())) {
            setErrorInfoInUpdStage(inUpdMsg, NSBM0121E, new String[] {inUpdMsg.getTableName(), inUpdMsg.citsSvcTaskUpdStagePk.getValue().toString() });
            return false;
        }
        commit();
        return true;
    }

    private List<Map<String, Object>> getCitsPlnStartInfo(Calendar tmpCalendar, String svcTaskRcsHhmmss) {

        // get CITS_PLN_START Info
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcTaskRsvTm", svcTaskRcsHhmmss);

        // get Day of Week
        int dayOfWeek = tmpCalendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                ssmParam.put("mon", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.TUESDAY:
                ssmParam.put("tue", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.WEDNESDAY:
                ssmParam.put("wed", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.THURSDAY:
                ssmParam.put("thu", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.FRIDAY:
                ssmParam.put("fri", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.SATURDAY:
                ssmParam.put("sat", ZYPConstant.FLG_ON_Y);
                break;
            case Calendar.SUNDAY:
                ssmParam.put("sun", ZYPConstant.FLG_ON_Y);
                break;
            default:
        }
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCitsPlnStartInfo", ssmParam);
    }

    private Map<String, Object> getSvcTaskInfo(EZDTStringItem svcTaskNum) {
        if (!hasValue(svcTaskNum)) {
            return null;
        }
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("svcTaskNum", svcTaskNum.getValue());
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcTaskInfo", ssmParam);
    }

    private NSBI0630_01TMsgArray getCreateTask() {
        NSBI0630_01TMsg inMsg = new NSBI0630_01TMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("interfaceId01", this.intfcId);
        inMsg.setConditionValue("transactionId01", this.trxId);
        NSBI0630_01TMsgArray tMsgAry = (NSBI0630_01TMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        return tMsgAry;
    }

    private NSBI0640_01TMsgArray getUpdateNotesTask() {
        NSBI0640_01TMsg inMsg = new NSBI0640_01TMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("interfaceId01", this.intfcId);
        inMsg.setConditionValue("transactionId01", this.trxId);
        NSBI0640_01TMsgArray tMsgAry = (NSBI0640_01TMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        return tMsgAry;
    }

    private NSBI0650_01TMsgArray getCancelTask() {
        NSBI0650_01TMsg inMsg = new NSBI0650_01TMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("interfaceId01", this.intfcId);
        inMsg.setConditionValue("transactionId01", this.trxId);
        NSBI0650_01TMsgArray tMsgAry = (NSBI0650_01TMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        return tMsgAry;
    }

    private int getFsrCnt(NSBI0630_01TMsg cratTMsg) {
        FSRTMsg inMsg = new FSRTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("custCseNum01", cratTMsg.plsftTaskNum.getValue());
        inMsg.setConditionValue("svcCallSrcTpCd01", SVC_CALL_SRC_TP.CITS);
        return EZDTBLAccessor.count(inMsg);
    }

    private DS_SVC_CALL_TPTMsgArray getDsSvcCallTp(EZDTStringItem dsSvcCallTpDescTxt) {
        if (!hasValue(dsSvcCallTpDescTxt)) {
            return null;
        }
        DS_SVC_CALL_TPTMsg inMsg = new DS_SVC_CALL_TPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("xtrnlCallTpRefTxt01", dsSvcCallTpDescTxt.getValue());
        return (DS_SVC_CALL_TPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private SVC_MACH_MSTRTMsgArray getSvcMachMstr(EZDTStringItem serNum) {
        if (!hasValue(serNum)) {
            return null;
        }
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("serNum01", serNum.getValue());
        SVC_MACH_MSTRTMsgArray tMsgAry = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        return tMsgAry;
    }

    private int getSvcTaskCnt(String fsrNum) {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("fsrNum01", fsrNum);
        return EZDTBLAccessor.count(inMsg);
    }

    private boolean callAddTaskApi(NSZC043001PMsg apiPMsg, NSBI0650_01TMsg cancTMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg) {

        NSZC045001PMsg nszc045001PMsg = new NSZC045001PMsg();
        setValue(nszc045001PMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.slsDt, this.slsDt);
        setValue(nszc045001PMsg.xxModeCd, NSZC045001.PROCESS_MODE_CANCEL_TASK);
        setValue(nszc045001PMsg.userId, BATCH_ID);
        setValue(nszc045001PMsg.fsrNum, apiPMsg.fsrNum);

        // Service Task List
        setValue(nszc045001PMsg.xxSvcTaskList.no(0).svcTaskNum, apiPMsg.taskList.no(0).svcTaskNum);
        setValue(nszc045001PMsg.xxSvcTaskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
        setValue(nszc045001PMsg.xxSvcTaskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        nszc045001PMsg.xxSvcTaskList.setValidCount(1);
        // Service Memo List
        setValue(nszc045001PMsg.xxSvcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.CITS_NOTE);
        String svcCmntTxt = cancTMsg.svcCmntTxt.getValue();
        setValue(nszc045001PMsg.xxSvcMemoList.no(0).svcCmntTxt, cutStr(svcCmntTxt, nszc045001PMsg.xxSvcMemoList.no(0).getAttr("svcCmntTxt").getDigit()));
        nszc045001PMsg.xxSvcMemoList.setValidCount(1);

        new NSZC045001().execute(nszc045001PMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(nszc045001PMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(nszc045001PMsg);
            for (String xxMsgId : xxMsgIdList) {
                setErrorInfoInUpdStage(inUpdMsg, xxMsgId, null);
            }
            rollback();
            return false;
        }
        commit();
        return true;
    }

    private boolean callFsrUpdateApi(NSZC043001PMsg apiPMsg, NSBI0630_01TMsg cratTMsg, NSBI0640_01TMsg updNotesTMsg, NSBI0650_01TMsg cancTMsg, CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg) {

        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.slsDt, this.slsDt);
        setValue(apiPMsg.userId, BATCH_ID);
        // Task List
        setValue(apiPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);

        if (INTFC_ID_CREATE.equals(this.intfcId)) {
            callFsrUpdateApiParamForCrat(apiPMsg, cratTMsg);
        } else if (INTFC_ID_UPD_NOTES.equals(this.intfcId)) {
            callFsrUpdateApiParamForUpdNotes(apiPMsg, updNotesTMsg);
        } else if (INTFC_ID_CANCEL.equals(this.intfcId)) {
            callFsrUpdateApiParamForCanc(apiPMsg, cancTMsg);
        }

        new NSZC043001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                setErrorInfoInUpdStage(inUpdMsg, xxMsgId, null);
            }
            rollback();
            return false;
        }
        commit();
        return true;
    }

    private void callFsrUpdateApiParamForCanc(NSZC043001PMsg apiPMsg, NSBI0650_01TMsg cancTMsg) {
        setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_CANCEL_FSR);
        // Task List
        apiPMsg.taskList.setValidCount(1);
        // Service Memo List
        String svcCmntTxt = cancTMsg.svcCmntTxt.getValue();
        setValue(apiPMsg.svcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.CITS_NOTE);
        setValue(apiPMsg.svcMemoList.no(0).svcCmntTxt, cutStr(svcCmntTxt, apiPMsg.svcMemoList.no(0).getAttr("svcCmntTxt").getDigit()));
        apiPMsg.svcMemoList.setValidCount(1);
    }

    private void callFsrUpdateApiParamForUpdNotes(NSZC043001PMsg apiPMsg, NSBI0640_01TMsg updNotesTMsg) {
        setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_UPDATE_FSR);
        // Task List
        apiPMsg.taskList.setValidCount(1);
        // Service Memo List
        String svcCmntTxt = updNotesTMsg.svcCmntTxt.getValue();
        if (hasValue(svcCmntTxt)) {
            svcCmntTxt = PREFIX_CMNT.concat(svcCmntTxt);
        }
        setValue(apiPMsg.svcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.CITS_NOTE);
        setValue(apiPMsg.svcMemoList.no(0).svcCmntTxt, cutStr(svcCmntTxt, apiPMsg.svcMemoList.no(0).getAttr("svcCmntTxt").getDigit()));
        apiPMsg.svcMemoList.setValidCount(1);
    }

    private void callFsrUpdateApiParamForCrat(NSZC043001PMsg apiPMsg, NSBI0630_01TMsg cratTMsg) {
        setValue(apiPMsg.xxModeCd, NSZC043001Constant.MODE_CREATE_FSR);
        setValue(apiPMsg.serNum, cratTMsg.serNum);
        setValue(apiPMsg.custTelNum, cratTMsg.custTelNum);
        setValue(apiPMsg.custTelExtnNum, cratTMsg.custTelExtnNum);
        setValue(apiPMsg.svcCustAttnTxt, cutEzdStr(cratTMsg.dsAcctNm, apiPMsg.getAttr("svcCustAttnTxt").getDigit()));
        setValue(apiPMsg.custEmlAddr, cratTMsg.custEmlAddr);
        String svcCustCllrTxt = cratTMsg.ctacPsnFirstNm.getValue();
        if (hasValue(svcCustCllrTxt)) {
            svcCustCllrTxt = ZYPCommonFunc.concatString(svcCustCllrTxt, SPACE, cratTMsg.ctacPsnLastNm.getValue());
        } else {
            svcCustCllrTxt = cratTMsg.ctacPsnLastNm.getValue();
        }
        setValue(apiPMsg.svcCustCllrTxt, cutStr(svcCustCllrTxt, apiPMsg.getAttr("svcCustCllrTxt").getDigit()));
        setValue(apiPMsg.svcPblmTpCd, cratTMsg.svcPblmTpCd);
        setValue(apiPMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.CITS);
        setValue(apiPMsg.custCseNum, cratTMsg.plsftTaskNum);
        // Task List
        apiPMsg.taskList.setValidCount(1);
        // Service Memo List
        String svcCmntTxt = cratTMsg.svcCmntTxt.getValue();
        if (hasValue(svcCmntTxt)) {
            // Service Memo List
            setValue(apiPMsg.svcMemoList.no(0).svcMemoTpCd, SVC_MEMO_TP.CITS_NOTE);
            setValue(apiPMsg.svcMemoList.no(0).svcCmntTxt, cutStr(PREFIX_CMNT.concat(svcCmntTxt), apiPMsg.svcMemoList.no(0).getAttr("svcCmntTxt").getDigit()));
            apiPMsg.svcMemoList.setValidCount(1);
        }
    }

    private String setKeyMessage(EZDTStringItem ezdStr) {
        if (!hasValue(ezdStr)) {
            return "-";
        }
        return ezdStr.getValue();
    }

    private void setErrorInfoInUpdStage(CITS_SVC_TASK_UPD_STAGETMsg inUpdMsg, String msgId, String[] msg) {
        String errMsg = S21MessageFunc.clspGetMessage(msgId, msg);
        setValue(inUpdMsg.s21FldSvcProcCd, CITS_PLSFT_PROC.ERROR);
        setValue(inUpdMsg.s21FldSvcProcMsgTxt, errMsg);
        this.errList.add(errMsg);
    }

    private void sendErrMail() {
        if (!this.errList.isEmpty()) {
            NSXC001001SendMailForErrorInfo errorInfo = new NSXC001001SendMailForErrorInfo();
            errorInfo.addErrMsgList(this.errList);
            String errMsg = errorInfo.sendMail(this.glblCmpyCd, BATCH_ID);
            if (errMsg != null) {
                S21InfoLogOutput.println(errMsg);
            }
        }
    }

    private String cutEzdStr(EZDTStringItem ezdTStr, int length) {
        if (!hasValue(ezdTStr)) {
            return null;
        } else if (ezdTStr.getValue().length() > length) {
            return ezdTStr.getValue().substring(0, length);
        }
        return ezdTStr.getValue();
    }

    private boolean checkNumber(String value) {

        if (ZYPCommonFunc.hasValue(value)) {
            if (ZYPCommonFunc.isHankakuSuuji(value)) {
                return true;
            }
            return false;
        }
        return true;
    }

    private String cutStr(String str, int length) {
        if (!hasValue(str)) {
            return null;
        } else if (str.length() > length) {
            return str.substring(0, length);
        }
        return str;
    }

    private static Date toDate(String fromTs, String formatFrom) {

        if (!hasValue(fromTs)) {
            return null;
        }
        SimpleDateFormat parser = new SimpleDateFormat(formatFrom);
        parser.setLenient(false);

        try {
            return parser.parse(fromTs);
        } catch (ParseException e) {
            return null;
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
