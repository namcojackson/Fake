/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB029001;

import static com.canon.cusa.s21.batch.NSB.NSBB029001.constant.NSBB029001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDTDateItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CRS_SVC_RCV_HDR_STAGETMsg;
import business.db.CRS_SVC_RCV_MTR_STAGETMsg;
import business.db.CRS_SVC_RCV_MTR_STAGETMsgArray;
import business.db.CRS_SVC_RCV_PRT_STAGETMsg;
import business.db.CRS_SVC_RCV_PRT_STAGETMsgArray;
import business.db.CRS_SVC_RCV_TASK_STAGETMsg;
import business.db.CRS_SVC_RCV_TASK_STAGETMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC005001PMsg;
import business.parts.NSZC045001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC005001.NSZC005001;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TM_EVENT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.message.ZYPEZDMessageInfoUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 *<pre>
 *  Close Cross Service Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/16/2016   Hitachi         Y.Takeno        Create          N/A
 * 04/19/2016   Hitachi         Y.Takeno        Update          QC#5459
 * 05/17/2016   Hitachi         Y.Takeno        Update          QC#8067
 * 06/08/2016   Hitachi         Y.Takeno        Update          QC#9640
 * 06/08/2016   Hitachi         Y.Takeno        Update          QC#9646
 * 06/17/2016   Hitachi         Y.Takeno        Update          QC#10219
 * 07/04/2016   Hitachi         T.Iwamoto       Update          QC#9677
 * 10/06/2016   Hitachi         N.Arai          Update          QC#14750
 * 01/29/2018   Hitachi         T.Tomita        Update          QC#23569
 * 07/25/2018   CITS            T.Wada          Update          QC#27095
 * 2018/08/09   Hitachi         K.Kojima        Update          QC#27399
 * 09/18/2018   Hitachi         K.Fujimoto      Update          QC#28294
 * 2018/11/28   Hitachi         S.Kitamura      Update          QC#29016
 * 2019/08/23   Hitachi         K.Fujimoto      Update          QC#52415
 * 2019/08/26   Hitachi         T.Aoyagi        Update          QC#52704
 * 2019/10/14   Hitachi         K.Fujimoto      Update          QC#54011
 * 2020/07/13   CITS            T.Sakurai       Update          QC#57307
 * 2020/09/18   CITS            T.Wada          Update          QC#57307
 * 2023/05/29   Hitachi         T.Usui          Update          QC#61449
 *</pre>
 */
public class NSBB029001 extends S21BatchMain {

    /** Termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global company code */
    private String glblCmpyCd = "";

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    /** S21SsmLowLevelCodingClient */
    private S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(this.getClass());

    /** varConstmap */
    private Map<String, String> varConstMap = null;

    // START 2016/04/19 Y.Takeno [QC#5459, DEL]
//    /** NSZC005001PMsg */
//    private NSZC005001PMsg pMsgNSZC005001 = null;
//
//    /** taskStageFirstRowTMsg */
//    private CRS_SVC_RCV_TASK_STAGETMsg taskStageFirstRowTMsg = null;
//
//    /** cpltTargetFsrVisitTMsg */
//    private FSR_VISITTMsg cpltTargetFsrVisitTMsg = null;
//
//    /** cpltTargetSvcTaskTMsg */
//    private SVC_TASKTMsg cpltTargetSvcTaskTMsg = null;
    // END 2016/04/19 Y.Takeno [QC#5459, DEL]

    // START 2016/04/19 Y.Takeno [QC#5459, ADD]
    /** pMsgNSZC005001List */
    private List<NSZC005001PMsg> pMsgNSZC005001List = null;

    /** fsrVisitStsCdList */
    private List<String> fsrVisitStsCdList = null;

    /** Sales Date */
    private String slsDt;
    // END 2016/04/19 Y.Takeno [QC#5459, ADD] svcTaskRcvTm

    // QC#57307 Add Start
    private String curTaskNum = null;
    private String curOracleSrNum = null;
    // QC#57307 Add Start

    /**
     * @param args arguments
     */
    public static void main(String[] args) {
        new NSBB029001().executeBatch(NSBB029001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // blank check(Global Company Code)
        glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSBM0032E, new String[] {"Global Company Code" });
        }
        // START 2016/04/19 Y.Takeno [QC#5459, ADD]
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        this.fsrVisitStsCdList = new ArrayList<String>();
        this.fsrVisitStsCdList.add(SVC_TASK_STS.COMPLETED);
        this.fsrVisitStsCdList.add(SVC_TASK_STS.CLOSED);
        this.fsrVisitStsCdList.add(SVC_TASK_STS.CANCELLED);
        // END 2016/04/19 Y.Takeno [QC#5459, ADD]
    }

    @Override
    protected void mainRoutine() {
        // initialize varConstMap
        initVarConstMap();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {

            // get request data (CRS_SVC_RCV_HDR_STAGE)
            stmt = ssmLLClient.createPreparedStatement("getCrsSvcRcvHdrStage", getCrsSvcRcvHdrStageParams(), execParam);
            rs = stmt.executeQuery();

            while (rs.next()) {

                if (processRequestData(rs)) {
                    totalNmlCount++;

                } else {
                    totalErrCount++;
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);

        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);

        }

        if (totalErrCount > 0) {
            termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {
        setRecordCount(totalNmlCount, totalErrCount, (totalNmlCount + totalErrCount));
        setTermState(termCd);
    }

    private Map<String, Object> getCrsSvcRcvHdrStageParams() {
        Map<String, Object> prms = new HashMap<String, Object>();
        prms.put("glblCmpyCd", this.glblCmpyCd);
        prms.put("procStsCd", PROC_STS.IN_COMPLETED);

        return prms;
    }

    private boolean processRequestData(ResultSet rs) throws SQLException {

        // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//        this.pMsgNSZC005001 = new NSZC005001PMsg();
        this.pMsgNSZC005001List = new ArrayList<NSZC005001PMsg>();
        // START 2016/04/19 Y.Takeno [QC#5459, MOD]

        BigDecimal stagePk = rs.getBigDecimal("CRS_SVC_RCV_HDR_STAGE_PK");
        String serNum = rs.getString("SER_NUM");
        String fsrNo = rs.getString("FSR_NUM");

        // QC#57307 Add Start
        this.curTaskNum = rs.getString("TASK_NUM");
        this.curOracleSrNum = rs.getString("CRS_SVC_SR_NUM");
        // QC#57307 Add End

        // find SVC_MACH_MSTR
        // START 2019/08/26 T.Aoyagi [QC#52704, MOD]
//        SVC_MACH_MSTRTMsg svcMachMstr = findSvcMachMstr(serNum);
        BigDecimal machPk = rs.getBigDecimal("SVC_MACH_MSTR_PK");
        SVC_MACH_MSTRTMsg svcMachMstr = findSvcMachMstrByPk(machPk);
        // END 2019/08/26 T.Aoyagi [QC#52704, MOD]
        // END 2016/04/19 Y.Takeno [QC#5459, MOD]
        if (svcMachMstr == null) {
            // ERROR : not found SVC_MACH_MSTR
            updateStageTablesStatus(stagePk, PROC_STS.ERROR);
            sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, NSBM0147E, new String[]{fsrNo }));
            return false;
        }
        // START 2016/04/19 Y.Takeno [QC#5459, DEL]
//        setValue(pMsgNSZC005001.svcMachMstrPk, svcMachMstr.svcMachMstrPk);
        // END 2016/04/19 Y.Takeno [QC#5459, DEL]

        // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//        if (!setupPMsgFromCrsSvcRcvTaskStage(fsrNo, stagePk)) {
        Map<String, String> errMap = setupPMsgFromCrsSvcRcvTaskStage(fsrNo, stagePk, svcMachMstr.svcMachMstrPk.getValue());
        if (errMap != null && errMap.size() > 0) {
            // Error
            // START 2016/04/19 Y.Takeno [QC#5459, MOD]
            rollback();
            // END 2016/04/19 Y.Takeno [QC#5459, MOD]
            // send mail
            sendRequestDataErrorMail(errMap);
            // ERROR : CRS_SVC_RCV_TASK_STAGE record is not found.
            updateStageTablesStatus(stagePk, PROC_STS.ERROR);

            // START 2016/04/19 Y.Takeno [QC#5459, MOD]
            commit();
            // END 2016/04/19 Y.Takeno [QC#5459, MOD]

            return false;
            // END 2016/04/19 Y.Takeno [QC#5459, ADD]
        }
        // END 2016/04/19 Y.Takeno [QC#5459, MOD]

        // get Contract (NSXC001001GetContr)
        BigDecimal svcMachMstrPk = svcMachMstr.svcMachMstrPk.getValue();
        // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//        DS_CONTRTMsg dsContr = NSXC001001GetContr.getContrByMachMstrPkInclWarranty(this.glblCmpyCd, svcMachMstrPk, this.cpltTargetSvcTaskTMsg.svcTaskRcvDt.getValue());
        DS_CONTRTMsg dsContr = NSXC001001GetContr.getContrByMachMstrPkInclWarranty(this.glblCmpyCd, svcMachMstrPk, this.pMsgNSZC005001List.get(0).slsDt.getValue());
        String dsContrNum = null;
        if (dsContr != null) {
            dsContrNum = dsContr.dsContrNum.getValue();
        }
//        if (!setupPMsgFromCrsSvcRcvMtrStage(fsrNo, stagePk, svcMachMstr.mdseCd.getValue(), dsContr.dsContrNum.getValue())) {
        // START 2018/07/25 T.Wada [QC#27095, MOD]
        BigDecimal mtrGrpPk = null;
        if (svcMachMstr.mtrGrpPk != null) {
            mtrGrpPk = svcMachMstr.mtrGrpPk.getValue();
        }
        // START 2018/11/28 S.Kitamura [QC#29016, MOD]
        //if (!setupPMsgFromCrsSvcRcvMtrStage(this.pMsgNSZC005001List.get(0), fsrNo, stagePk, svcMachMstr.mdseCd.getValue(), dsContrNum, mtrGrpPk)) {

        // START 2019/08/23 K.Fujimoto [QC#52415, MOD]
        for (NSZC005001PMsg pMsg : this.pMsgNSZC005001List) {
//            if (!setupPMsgFromCrsSvcRcvMtrStage(this.pMsgNSZC005001List.get(0), fsrNo, stagePk, svcMachMstr.mdseCd.getValue(), dsContrNum, mtrGrpPk, svcMachMstrPk)) {
            String crsSrNum = getCrsSrNum(pMsg.fsrNum.getValue(), pMsg.fsrVisitNum.getValue());
            // if (!setupPMsgFromCrsSvcRcvMtrStage(pMsg, fsrNo, stagePk, svcMachMstr.mdseCd.getValue(), dsContrNum, mtrGrpPk, svcMachMstrPk)) {
            // START 2020/07/13 T.Sakurai [QC#57307 MOD]
            if (crsSrNum != null && !setupPMsgFromCrsSvcRcvMtrStage(pMsg, fsrNo, stagePk, svcMachMstr.mdseCd.getValue(), dsContrNum, mtrGrpPk, svcMachMstrPk, crsSrNum, serNum)) {
            // END 2020/07/13 T.Sakurai [QC#57307 MOD]
            // END 2018/11/28 S.Kitamura [QC#29016, MOD]
//            if (!setupPMsgFromCrsSvcRcvMtrStage(this.pMsgNSZC005001List.get(0), fsrNo, stagePk, svcMachMstr.mdseCd.getValue(), dsContrNum)) {
            // END 2018/07/25 T.Wada [QC#27095, MOD]
            // END 2016/04/19 Y.Takeno [QC#5459, MOD]
                return false;
            }
        }


        // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//        if (!setupPMsgFromCrsSvcRcvPrtStage(fsrNo, stagePk)) {
        for (NSZC005001PMsg pMsg : this.pMsgNSZC005001List) {
            String crsSrNum = getCrsSrNum(pMsg.fsrNum.getValue(), pMsg.fsrVisitNum.getValue());
            // if (!setupPMsgFromCrsSvcRcvPrtStage(this.pMsgNSZC005001List.get(0), fsrNo, stagePk)) {
            if (!setupPMsgFromCrsSvcRcvPrtStage(pMsg, fsrNo, stagePk, crsSrNum)) {
                // END 2016/04/19 Y.Takeno [QC#5459, MOD]
                    return false;
                }
        }
        // END 2019/08/23 K.Fujimoto [QC#52415, MOD]
        // START 2016/06/08 [QC#9640, MOD]
        // call Service Dispatch Completion API(NSZC0050)
        if (!callApiNSZC005001(stagePk)) {
            return false;
        }
        // END 2016/06/08 [QC#9640, MOD]

        // update stage tables
        updateStageTablesStatus(stagePk, PROC_STS.COMPLEATED);

        // START 2018/08/09 K.Kojima [QC#27399,DEL]
        // // Call Send Task API
        // // mod start 2016/07/04 CSA Defect#9677
        // callSendTaskApi(this.pMsgNSZC005001List);
        // // mod end 2016/07/04 CSA Defect#9677
        // END 2018/08/09 K.Kojima [QC#27399,DEL]

        // commit transaction
        commit();

        return true;
    }

    // START 2018/08/09 K.Kojima [QC#27399,DEL]
    // // mod start 2016/07/04 CSA Defect#9677
    // private void callSendTaskApi(List<NSZC005001PMsg> msgNSZC005001List) {
    //     List<NSZC107001PMsg> apiPMsgList = new ArrayList<NSZC107001PMsg>();
    //     for (NSZC005001PMsg nszc0050PMsg : this.pMsgNSZC005001List) {
    //         NSZC107001PMsg apiPMsg = new NSZC107001PMsg();
    //         setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
    //         setValue(apiPMsg.slsDt, this.slsDt);
    //         setValue(apiPMsg.svcTaskNum, nszc0050PMsg.xxVisitTaskList.no(0).svcTaskNum);
    //         apiPMsgList.add(apiPMsg);
    //     }
    // 
    //     NSZC107001 api = new NSZC107001();
    //     api.execute(apiPMsgList, ONBATCH_TYPE.BATCH);
    // }
    // // mod end 2016/07/04 CSA Defect#9677
    // END 2018/08/09 K.Kojima [QC#27399,DEL]

    private SVC_MACH_MSTRTMsg findSvcMachMstr(String serNum) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("serNum01", serNum);
        SVC_MACH_MSTRTMsgArray tMsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            return tMsgArray.no(0);
        }
        return null;
    }

    // START 2019/08/26 T.Aoyagi [QC#52704, ADD]
    private SVC_MACH_MSTRTMsg findSvcMachMstrByPk(BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // END 2019/08/26 T.Aoyagi [QC#52704, ADD]
    
    // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//    private boolean setupPMsgFromCrsSvcRcvTaskStage(String fsrNo, BigDecimal stagePk) {
    private Map<String, String> setupPMsgFromCrsSvcRcvTaskStage(String fsrNo, BigDecimal stagePk, BigDecimal svcMachMstrPk) {
    // END 2016/04/19 Y.Takeno [QC#5459, MOD]
        // START 2016/04/19 Y.Takeno [QC#5459, DEL]
//        CRS_SVC_RCV_TASK_STAGETMsgArray tskTMsgArray = findCrsRcvTaskStage(stagePk);
//
//        if (tskTMsgArray == null || (tskTMsgArray != null && tskTMsgArray.getValidCount() == 0)) {
//            // ERROR : Task stage data is not found.
//            updateStageTablesStatus(stagePk, PROC_STS.ERROR);
//            sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, NSBM0141E, new String[]{"FSR No. " + fsrNo, "Task" }));
//            return false;
//        }
//
//        this.taskStageFirstRowTMsg = tskTMsgArray.no(0);
//
//        int timeEventListCount = 0;
//        int visitTaskListCount = 0;
//        for (int i = 0; i < tskTMsgArray.getValidCount(); i++) {
//            CRS_SVC_RCV_TASK_STAGETMsg taskStageTMsg = tskTMsgArray.no(i);
//
//            // find FSR_VISIT
//            FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
//            setValue(tMsg.glblCmpyCd,  this.glblCmpyCd);
//            setValue(tMsg.fsrNum,  taskStageTMsg.fsrNum);
//            setValue(tMsg.fsrVisitNum,  "01");
//            tMsg = (FSR_VISITTMsg) EZDTBLAccessor.findByKey(tMsg);
//
//            if (tMsg != null) {
//                this.cpltTargetFsrVisitTMsg = tMsg;
//
//                do {
//                    if (hasValue(this.cpltTargetFsrVisitTMsg.nextFsrVisitNum) && SVC_TASK_STS.COMPLETED.equals(this.cpltTargetFsrVisitTMsg.fsrVisitStsCd.getValue())) {
//                        // find follow-up record
//                        tMsg = new FSR_VISITTMsg();
//                        setValue(tMsg.glblCmpyCd,  this.glblCmpyCd);
//                        setValue(tMsg.fsrNum,  this.cpltTargetFsrVisitTMsg.fsrNum.getValue());
//                        setValue(tMsg.fsrVisitNum,  this.cpltTargetFsrVisitTMsg.nextFsrVisitNum.getValue());
//                        tMsg = (FSR_VISITTMsg) EZDTBLAccessor.findByKey(tMsg);
//
//                        if (tMsg != null) {
//                            this.cpltTargetFsrVisitTMsg = tMsg;
//                        }
//                    } else {
//                        break;
//                    }
//
//                } while(tMsg != null);
//
//                if (!isFsrVisitCanComplete(this.cpltTargetFsrVisitTMsg)) {
//                    // ERROR : FSR_VISIT cannot complete.
//                    updateStageTablesStatus(stagePk, PROC_STS.ERROR);
//                    sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, NSBM0144E, new String[]{"FSR No. " + fsrNo, "complete" }));
//                    totalErrCount++;
//                    return false;
//                }
//            }
//
//            // find SVC_TASK
//            this.cpltTargetSvcTaskTMsg = new SVC_TASKTMsg();
//            setValue(this.cpltTargetSvcTaskTMsg.glblCmpyCd, this.glblCmpyCd);
//            setValue(this.cpltTargetSvcTaskTMsg.fsrNum, this.cpltTargetFsrVisitTMsg.fsrNum);
//            setValue(this.cpltTargetSvcTaskTMsg.svcTaskNum, this.cpltTargetFsrVisitTMsg.svcTaskNum);
//            this.cpltTargetSvcTaskTMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKey(this.cpltTargetSvcTaskTMsg);
//
//            if (this.cpltTargetSvcTaskTMsg == null) {
//                // ERROR : SVC_TASK record is not found.
//                updateStageTablesStatus(stagePk, PROC_STS.ERROR);
//                sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, NSBM0148E, new String[]{fsrNo }));
//                return false;
//            }
//
//            // Visit Header parameter
//            setValue(pMsgNSZC005001.glblCmpyCd, this.glblCmpyCd);
//            setValue(pMsgNSZC005001.slsDt, this.cpltTargetSvcTaskTMsg.svcTaskRcvDt);
//            setValue(pMsgNSZC005001.userId, BATCH_PROGRAM_ID);
//            setValue(pMsgNSZC005001.fsrNum, cpltTargetFsrVisitTMsg.fsrNum);
//            setValue(pMsgNSZC005001.fsrVisitNum, cpltTargetFsrVisitTMsg.fsrVisitNum);
//            setValue(pMsgNSZC005001.fsrVisitArvDt, this.taskStageFirstRowTMsg.fsrVisitArvDt.getValue());
//            setValue(pMsgNSZC005001.fsrVisitArvTm, convertToTime(this.taskStageFirstRowTMsg.crsSvcLborStartHourMn.getValue()));
//            setValue(pMsgNSZC005001.fsrVisitCpltDt, this.taskStageFirstRowTMsg.fsrVisitArvDt.getValue());
//            setValue(pMsgNSZC005001.fsrVisitCpltTm, convertToTime(this.taskStageFirstRowTMsg.crsSvcLborEndHourMn.getValue()));
//            BigDecimal svcTrvlTmNum = getTmNum(this.taskStageFirstRowTMsg.crsSvcTrvlStartHourMn.getValue(), this.taskStageFirstRowTMsg.crsSvcTrvlEndHourMn.getValue());
//            setValue(pMsgNSZC005001.svcTrvlTmNum, svcTrvlTmNum);
//            setValue(pMsgNSZC005001.invCcyCd, this.varConstMap.get(NSBB0290_INV_CCY_CD));
//            setValue(pMsgNSZC005001.ccyExchRate, BigDecimal.ONE);
//            setValue(pMsgNSZC005001.svcBillTpCd, this.varConstMap.get(NSBB0290_SVC_BILL_TP_CD));
//            setValue(pMsgNSZC005001.pmtTermCashDiscCd, this.varConstMap.get(NSBB0290_PMT_TERM_CASH_DISC_CD));
//
//            // Visit for Task List parameter
//            BigDecimal svcLBorTmNum = getTmNum(taskStageTMsg.crsSvcLborStartHourMn.getValue(), taskStageTMsg.crsSvcLborEndHourMn.getValue());
//            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcTaskNum, this.cpltTargetFsrVisitTMsg.svcTaskNum);
//            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcPblmTpCd, this.varConstMap.get(NSBB0290_SVC_PBLM_TP_CD));
//            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcPblmLocTpCd, this.varConstMap.get(NSBB0290_SVC_PBLM_LOC_TP_CD));
//            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcPblmRsnTpCd, this.varConstMap.get(NSBB0290_SVC_PBLM_RSN_TP_CD));
//            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcPblmCrctTpCd, this.varConstMap.get(NSBB0290_SVC_PBLM_CRCT_TP_CD));
//            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcLborTmNum, svcLBorTmNum);
//            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).xxVisitTaskCpltFlg, ZYPConstant.FLG_ON_Y);
//
//            // Time Event List parameter
//            if (hasValue(taskStageTMsg.fsrVisitArvDt) && hasValue(taskStageTMsg.crsSvcTrvlStartHourMn) && hasValue(taskStageTMsg.crsSvcTrvlEndHourMn)) {
//                BigDecimal durnTmNum = getTmNum(taskStageTMsg.crsSvcTrvlStartHourMn.getValue(), taskStageTMsg.crsSvcTrvlEndHourMn.getValue());
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).fsrNum, taskStageTMsg.fsrNum);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).fsrVisitNum, this.cpltTargetFsrVisitTMsg.fsrVisitNum);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventCd, SVC_TM_EVENT.TRAVEL);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTaskNum, this.cpltTargetFsrVisitTMsg.svcTaskNum);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventFromDt, taskStageTMsg.fsrVisitArvDt.getValue());
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventFromTm, convertToTime(taskStageTMsg.crsSvcTrvlStartHourMn.getValue()));
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventToDt, taskStageTMsg.fsrVisitArvDt.getValue());
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventToTm, convertToTime(taskStageTMsg.crsSvcTrvlEndHourMn.getValue()));
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).durnTmNum, durnTmNum);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).machDownFlg, this.cpltTargetSvcTaskTMsg.machDownFlg);
//                timeEventListCount++;
//            }
//            if (hasValue(taskStageTMsg.fsrVisitArvDt) && hasValue(taskStageTMsg.crsSvcLborStartHourMn) && hasValue(taskStageTMsg.crsSvcLborEndHourMn)) {
//                BigDecimal durnTmNum = getTmNum(taskStageTMsg.crsSvcLborStartHourMn.getValue(), taskStageTMsg.crsSvcLborEndHourMn.getValue());
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).fsrNum, taskStageTMsg.fsrNum);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).fsrVisitNum, this.cpltTargetFsrVisitTMsg.fsrVisitNum);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventCd, SVC_TM_EVENT.LABOR);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTaskNum, this.cpltTargetFsrVisitTMsg.svcTaskNum);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventFromDt, taskStageTMsg.fsrVisitArvDt.getValue());
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventFromTm, convertToTime(taskStageTMsg.crsSvcLborStartHourMn.getValue()));
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventToDt, taskStageTMsg.fsrVisitArvDt.getValue());
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventToTm, convertToTime(taskStageTMsg.crsSvcLborEndHourMn.getValue()));
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).durnTmNum, durnTmNum);
//                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).machDownFlg, this.cpltTargetSvcTaskTMsg.machDownFlg);
//                timeEventListCount++;
//            }
//
//            // countup counter
//            visitTaskListCount++;
//        }
//        pMsgNSZC005001.xxVisitTaskList.setValidCount(visitTaskListCount);
//        pMsgNSZC005001.xxTmEventList.setValidCount(timeEventListCount);
//
//        return true;
        // END 2016/04/19 Y.Takeno [QC#5459, DEL]
        // START 2016/04/19 Y.Takeno [QC#5459, ADD]
        NSZC005001PMsg pMsgNSZC005001 = null;
        CRS_SVC_RCV_TASK_STAGETMsg crsSvcRcvTaskStageTMsg = null;
        FSR_VISITTMsg fsrVisitTMsg = null;
        SVC_TASKTMsg svcTaskTMsg = null;
        String fsrVisitStsCd = null;
        int timeEventListCount;
        int visitTaskListCount;
        // START 2018/09/18 K.Fujimoto [QC#28294, ADD]
        int checkListCount;
        // END 2018/09/18 K.Fujimoto [QC#28294, ADD]
        List<Map<String, Object>> crsSvcRcvTaskStageMapList = findCrsSvcRcvTaskStage(stagePk, fsrNo);
        if (crsSvcRcvTaskStageMapList == null || crsSvcRcvTaskStageMapList.size() == 0) {
            return createMailTemplateParamMap(fsrNo, NSBM0141E, new String[] {"FSR No. " + fsrNo, "Task" });
        }
        for (Map<String, Object> crsSvcRcvTaskStageMap : crsSvcRcvTaskStageMapList) {
            crsSvcRcvTaskStageTMsg = findCrsSvcRcvTaskStage((BigDecimal) crsSvcRcvTaskStageMap.get("CRS_SVC_RCV_TASK_STAGE_PK"), (BigDecimal) crsSvcRcvTaskStageMap.get("CRS_SVC_RCV_HDR_STAGE_PK"));
            if (crsSvcRcvTaskStageTMsg == null) {
                return createMailTemplateParamMap(fsrNo, NSBM0148E, new String[] {fsrNo });
            }
            // Del Start 2018/02/01 QC#23569
//            fsrVisitStsCd = (String) crsSvcRcvTaskStageMap.get("FSR_VISIT_STS_CD");
//            if (!hasValue(fsrVisitStsCd)) {
//// START 2016/10/06 N.Arai [QC#14750, MOD] -->
////                NSZC045001PMsg apiPMsg = createFollowUpCall(crsSvcRcvTaskStageTMsg, svcMachMstrPk);
//                NSZC045001PMsg apiPMsg = createFollowUpCall(crsSvcRcvTaskStageTMsg, svcMachMstrPk, fsrNo);
//                if (apiPMsg == null) {
//                    return createMailTemplateParamMap(fsrNo, NSBM0148E, new String[] {fsrNo });
//                }
//                String errMsgId = callFollowUpCall(apiPMsg);
//                if (hasValue(errMsgId)) {
//                    return createMailTemplateParamMap(fsrNo, errMsgId, null);
//                }
//                String svcTaskNum = apiPMsg.xxSvcTaskList.no(0).svcTaskNum.getValue();
//                updateSvcTask(svcTaskNum, (String) crsSvcRcvTaskStageMap.get("CRS_SVC_RCV_SR_NUM"));
//                crsSvcRcvTaskStageMap.put("SVC_TASK_NUM", svcTaskNum);
//                // Add Start 2018/01/29 QC#23569
//                crsSvcRcvTaskStageMap.put("FSR_NUM", apiPMsg.fsrNum.getValue());
//                // Add End 2018/01/29 QC#23569
//            } else if (this.fsrVisitStsCdList.contains(fsrVisitStsCd)) {
//                return createMailTemplateParamMap(fsrNo, NSBM0144E, new String[] {"FSR No. " + fsrNo, "complete" });
//            }
            // Del Start 2018/02/01 QC#23569

            pMsgNSZC005001 = new NSZC005001PMsg();
            String svcTaskNum = (String) crsSvcRcvTaskStageMap.get("SVC_TASK_NUM");
            svcTaskTMsg = findSvcTask(svcTaskNum);
            if (svcTaskTMsg == null) {
                return createMailTemplateParamMap(fsrNo, NSBM0148E, new String[] {fsrNo });
            }
// START 10/06/2016 N.Arai [QC#14750, MOD]
//fsrVisitTMsg = findFsrVisit(fsrNo, svcTaskNum);
            fsrVisitTMsg = findFsrVisit((String) crsSvcRcvTaskStageMap.get("FSR_NUM"), svcTaskNum);
            if (fsrVisitTMsg == null) {
                return createMailTemplateParamMap(fsrNo, NSBM0148E, new String[] {fsrNo });
            }

            // START 2016/06/08 Y.Takeno [QC#9646, ADD]
            String crsSvcTrvlStartHourMn = crsSvcRcvTaskStageTMsg.crsSvcTrvlStartHourMn.getValue();
            String crsSvcTrvlEndHourMn = crsSvcRcvTaskStageTMsg.crsSvcTrvlEndHourMn.getValue();
            // QC#61449 2023/05/29 Add Start
            String crsSvcLborStartHourMn = crsSvcRcvTaskStageTMsg.crsSvcLborStartHourMn.getValue();
            String crsSvcLborEndHourMn = crsSvcRcvTaskStageTMsg.crsSvcLborEndHourMn.getValue();
            String arriveDt = crsSvcRcvTaskStageTMsg.fsrVisitArvDt.getValue();
            String inRouteDt = null;

            if (hasValue(crsSvcLborStartHourMn) && hasValue(crsSvcLborEndHourMn) && crsSvcLborStartHourMn.compareTo(crsSvcLborEndHourMn) >= 1) {
                arriveDt = returnBeforeDay(arriveDt);
            }

            if (hasValue(crsSvcTrvlStartHourMn) && hasValue(crsSvcTrvlEndHourMn) && crsSvcTrvlStartHourMn.compareTo(crsSvcTrvlEndHourMn) >= 1) {
                inRouteDt = returnBeforeDay(arriveDt);
            } else {
                inRouteDt = arriveDt;
            }
            // QC#61449 2023/05/29 Add End
            if (!hasValue(crsSvcTrvlStartHourMn) || !hasValue(crsSvcTrvlEndHourMn)) {
                crsSvcTrvlStartHourMn = DEFAULT_TRVL_HOUR_MN;
                crsSvcTrvlEndHourMn = DEFAULT_TRVL_HOUR_MN;
            }
            // END 2016/06/08 Y.Takeno [QC#9646, ADD]

            // START 2018/09/18 K.Fujimoto [QC#28294, ADD]
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("svcMachMstrPk",svcTaskTMsg.svcMachMstrPk);
            List<Map<String, Object>> installCheckList = new ArrayList<Map<String, Object>>();

            // Get SVC_DEINS_REQ_FLG Flag.
            DS_SVC_CALL_TPTMsg dsSvcCallTpMsg = getDsSvcCallTp(svcTaskTMsg.dsSvcCallTpCd.getValue());
            
            // Install Call.
            if (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpMsg.svcIstlReqFlg.getValue())) {
                installCheckList = ssmClient.queryObjectList("getInstallCheckList", getInstallCheckListParam(paramMap));
            } 

            // Install Check List parameter
            checkListCount = 0;
            if (installCheckList != null && installCheckList.size() > 0) {
                for (Map<String, Object> installCheckListMap : installCheckList) {
                    setValue(pMsgNSZC005001.xxFsrIstlChkList.no(checkListCount).fsrNum, fsrVisitTMsg.fsrNum);
                    setValue(pMsgNSZC005001.xxFsrIstlChkList.no(checkListCount).svcTaskNum, fsrVisitTMsg.svcTaskNum);
                    setValue(pMsgNSZC005001.xxFsrIstlChkList.no(checkListCount).svcConfigMstrPk, getBigDecimal(installCheckListMap, SVC_CONFIG_MSTR_PK));
                    setValue(pMsgNSZC005001.xxFsrIstlChkList.no(checkListCount).mdseCd, getString(installCheckListMap, MDSE_CD));
                    setValue(pMsgNSZC005001.xxFsrIstlChkList.no(checkListCount).mdlId, getBigDecimal(installCheckListMap, MDL_ID));
                    setValue(pMsgNSZC005001.xxFsrIstlChkList.no(checkListCount).mdlNm, getString(installCheckListMap, MDL_NM));
                    setValue(pMsgNSZC005001.xxFsrIstlChkList.no(checkListCount).serNum, getString(installCheckListMap, SER_NUM));
                    setValue(pMsgNSZC005001.xxFsrIstlChkList.no(checkListCount).istlChkVerFlg, ZYPConstant.FLG_ON_Y);
                    checkListCount++;
                }
                setValue(pMsgNSZC005001.istlCpltFlg,ZYPConstant.FLG_ON_Y);
            }
            // END 2018/09/18 K.Fujimoto [QC#28294, ADD]
            // Visit Header parameter
            setValue(pMsgNSZC005001.glblCmpyCd, this.glblCmpyCd);
// START 2016/06/08 Y.Takeno [QC#10219, MOD]
            // setValue(pMsgNSZC005001.slsDt, svcTaskTMsg.svcTaskRcvDt);
            setValue(pMsgNSZC005001.slsDt, this.slsDt);
// END 2016/06/08 Y.Takeno [QC#10219, MOD]
            setValue(pMsgNSZC005001.userId, BATCH_PROGRAM_ID);
            setValue(pMsgNSZC005001.fsrNum, fsrVisitTMsg.fsrNum);
            setValue(pMsgNSZC005001.fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
            // QC#61449 2023/05/29 Mod Start
            setValue(pMsgNSZC005001.fsrVisitArvDt, arriveDt);
            // QC#61449 2023/05/29 Mod Endt
            setValue(pMsgNSZC005001.fsrVisitArvTm, convertToTime(crsSvcRcvTaskStageTMsg.crsSvcLborStartHourMn.getValue()));
            setValue(pMsgNSZC005001.fsrVisitCpltDt, crsSvcRcvTaskStageTMsg.fsrVisitArvDt.getValue());
            setValue(pMsgNSZC005001.fsrVisitCpltTm, convertToTime(crsSvcRcvTaskStageTMsg.crsSvcLborEndHourMn.getValue()));
// START 2016/06/08 Y.Takeno [QC#9646, MOD]
            BigDecimal svcTrvlTmNum = getTmNum(crsSvcTrvlStartHourMn, crsSvcTrvlEndHourMn);
// END 2016/06/08 Y.Takeno [QC#9646, MOD]
            setValue(pMsgNSZC005001.svcTrvlTmNum, svcTrvlTmNum);
            setValue(pMsgNSZC005001.invCcyCd, this.varConstMap.get(NSBB0290_INV_CCY_CD));
            setValue(pMsgNSZC005001.ccyExchRate, BigDecimal.ONE);
            setValue(pMsgNSZC005001.svcBillTpCd, this.varConstMap.get(NSBB0290_SVC_BILL_TP_CD));
            setValue(pMsgNSZC005001.pmtTermCashDiscCd, this.varConstMap.get(NSBB0290_PMT_TERM_CASH_DISC_CD));
            setValue(pMsgNSZC005001.svcMachMstrPk, svcMachMstrPk);
            // START 2018/08/09 K.Kojima [QC#27399,ADD]
            setValue(pMsgNSZC005001.mblIntfcFlg, ZYPConstant.FLG_OFF_N);
            // END 2018/08/09 K.Kojima [QC#27399,ADD]

            timeEventListCount = 0;
            visitTaskListCount = 0;

            // Visit for Task List parameter
            BigDecimal svcLborTmNum = getTmNum(crsSvcRcvTaskStageTMsg.crsSvcLborStartHourMn.getValue(), crsSvcRcvTaskStageTMsg.crsSvcLborEndHourMn.getValue());
            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcTaskNum, fsrVisitTMsg.svcTaskNum);
            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcPblmTpCd, this.varConstMap.get(NSBB0290_SVC_PBLM_TP_CD));
            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcPblmLocTpCd, this.varConstMap.get(NSBB0290_SVC_PBLM_LOC_TP_CD));
            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcPblmRsnTpCd, this.varConstMap.get(NSBB0290_SVC_PBLM_RSN_TP_CD));
            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcPblmCrctTpCd, this.varConstMap.get(NSBB0290_SVC_PBLM_CRCT_TP_CD));
            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).svcLborTmNum, svcLborTmNum);
            setValue(pMsgNSZC005001.xxVisitTaskList.no(visitTaskListCount).xxVisitTaskCpltFlg, ZYPConstant.FLG_ON_Y);

            // Time Event List parameter
// START 2016/06/08 Y.Takeno [QC#9646, MOD]
            if (hasValue(crsSvcRcvTaskStageTMsg.fsrVisitArvDt)) {
                BigDecimal durnTmNum = getTmNum(crsSvcTrvlStartHourMn, crsSvcTrvlEndHourMn);
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).fsrNum, crsSvcRcvTaskStageTMsg.fsrNum);
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventCd, SVC_TM_EVENT.TRAVEL);
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTaskNum, fsrVisitTMsg.svcTaskNum);
                // QC#61449 2023/05/29 Mod Start
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventFromDt, inRouteDt);
                // QC#61449 2023/05/29 Mod End
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventFromTm, convertToTime(crsSvcTrvlStartHourMn));
                // QC#61449 2023/05/29 Mod Start
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventToDt, arriveDt);
                // QC#61449 2023/05/29 Mod End
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventToTm, convertToTime(crsSvcTrvlEndHourMn));
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).durnTmNum, durnTmNum);
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).machDownFlg, svcTaskTMsg.machDownFlg);
                timeEventListCount++;
            }
// END 2016/06/08 Y.Takeno [QC#9646, MOD]
            if (hasValue(crsSvcRcvTaskStageTMsg.fsrVisitArvDt) && hasValue(crsSvcRcvTaskStageTMsg.crsSvcLborStartHourMn) && hasValue(crsSvcRcvTaskStageTMsg.crsSvcLborEndHourMn)) {
                BigDecimal durnTmNum = getTmNum(crsSvcRcvTaskStageTMsg.crsSvcLborStartHourMn.getValue(), crsSvcRcvTaskStageTMsg.crsSvcLborEndHourMn.getValue());
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).fsrNum, crsSvcRcvTaskStageTMsg.fsrNum);
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).fsrVisitNum, fsrVisitTMsg.fsrVisitNum);
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventCd, SVC_TM_EVENT.LABOR);
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTaskNum, fsrVisitTMsg.svcTaskNum);
                // QC#61449 2023/05/29 Mod Start
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventFromDt, arriveDt);
                // QC#61449 2023/05/29 Mod End
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventFromTm, convertToTime(crsSvcRcvTaskStageTMsg.crsSvcLborStartHourMn.getValue()));
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventToDt, crsSvcRcvTaskStageTMsg.fsrVisitArvDt.getValue());
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).svcTmEventToTm, convertToTime(crsSvcRcvTaskStageTMsg.crsSvcLborEndHourMn.getValue()));
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).durnTmNum, durnTmNum);
                setValue(pMsgNSZC005001.xxTmEventList.no(timeEventListCount).machDownFlg, svcTaskTMsg.machDownFlg);
                timeEventListCount++;
            }

            // countup counter
            visitTaskListCount++;
            pMsgNSZC005001.xxVisitTaskList.setValidCount(visitTaskListCount);
            pMsgNSZC005001.xxTmEventList.setValidCount(timeEventListCount);
            pMsgNSZC005001.xxFsrIstlChkList.setValidCount(checkListCount);
            
            this.pMsgNSZC005001List.add(pMsgNSZC005001);
        }
        return null;
        // END 2016/04/19 Y.Takeno [QC#5459, ADD]
    }

    // START 2016/04/19 Y.Takeno [QC#5459, DEL]
//    private CRS_SVC_RCV_TASK_STAGETMsgArray findCrsRcvTaskStage(BigDecimal stagePk) {
//        CRS_SVC_RCV_TASK_STAGETMsg inMsg = new CRS_SVC_RCV_TASK_STAGETMsg();
//        inMsg.setSQLID("002");
//        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        inMsg.setConditionValue("crsSvcRcvHdrStagePk01", stagePk);
//        inMsg.setConditionValue("crsSvcProcStsCd01", PROC_STS.IN_COMPLETED);
//        return (CRS_SVC_RCV_TASK_STAGETMsgArray) EZDTBLAccessor.findByCondition(inMsg);
//    }
    // END 2016/04/19 Y.Takeno [QC#5459, DEL]

    private BigDecimal getTmNum(String trvlStartHourMn, String trvlEndHourMn) {
        if (hasValue(trvlStartHourMn) && trvlStartHourMn.length() == LENGTH_HHMM
                && hasValue(trvlEndHourMn) && trvlEndHourMn.length() == LENGTH_HHMM) {
            long end = Integer.valueOf(trvlEndHourMn.substring(0, 2)) * HOUR + Integer.valueOf(trvlEndHourMn.substring(2));
            long start = Integer.valueOf(trvlStartHourMn.substring(0, 2)) * HOUR + Integer.valueOf(trvlStartHourMn.substring(2));
            // QC#61449 2023/05/29 Add Start
            if (end - start < 0) {
                return BigDecimal.valueOf(end - start + DAY_MINUTES);
            }
            // QC#61449 2023/05/29 Add End
            return BigDecimal.valueOf(end - start);
        }
        return null;
    }

    // START 2018/11/28 S.Kitamura [QC#29016, MOD]

    // START 2019/08/23 K.Fujimoto [QC#52415, MOD]
//    private boolean setupPMsgFromCrsSvcRcvMtrStage(NSZC005001PMsg pMsgNSZC005001, String fsrNo, BigDecimal stagePk, String mdseCd, String dsContrNum, BigDecimal mtrGrpPk, BigDecimal svcMachMstrPK) {
    // START 2020/07/13 T.Sakurai [QC#57307 MOD]
    private boolean setupPMsgFromCrsSvcRcvMtrStage(NSZC005001PMsg pMsgNSZC005001, String fsrNo, BigDecimal stagePk, String mdseCd, String dsContrNum, BigDecimal mtrGrpPk, BigDecimal svcMachMstrPK, String fsrNum, String serNum) {
    // END 2020/07/13 T.Sakurai [QC#57307 MOD]
    // END   2019/08/23 K.Fujimoto [QC#52415, MOD]
    // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//    private boolean setupPMsgFromCrsSvcRcvMtrStage(String fsrNo, BigDecimal stagePk, String mdseCd, String dsContrNum) {
     // START 2018/07/25 T.Wada [QC#27095, MOD]
//    private boolean setupPMsgFromCrsSvcRcvMtrStage(NSZC005001PMsg pMsgNSZC005001, String fsrNo, BigDecimal stagePk, String mdseCd, String dsContrNum, BigDecimal mtrGrpPk) {
//  private boolean setupPMsgFromCrsSvcRcvMtrStage(NSZC005001PMsg pMsgNSZC005001, String fsrNo, BigDecimal stagePk, String mdseCd, String dsContrNum) {
    // END 2018/07/25 T.Wada [QC#27095, MOD]
    // END 2016/04/19 Y.Takeno [QC#5459, MOD]
        // START 2019/08/23 K.Fujimoto [QC#52415, MOD]
        //List<Map<String, Object>> resultList = findCrsRcvMtrStage(stagePk, mtrGrpPk);
        List<Map<String, Object>> resultList = findCrsRcvMtrStage(stagePk, mtrGrpPk, fsrNum);
        // END   2019/08/23 K.Fujimoto [QC#52415, MOD]
        if (resultList == null) {
            return true;
        }
        // START 2020/07/13 T.Sakurai [QC#57307 ADD]
        if (mtrGrpPk == null){
            if (resultList.size() != 0) {
                sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, NSBM0190E, new String[]{serNum}));
                updateStageTablesStatus(stagePk, PROC_STS.ERROR);
                return false;
            }
            return true;
        }
        // END 2020/07/13 T.Sakurai [QC#57307 ADD]
        boolean allEmptyFlg = true;
        //for (int i = 0; i < resultList.size(); i++) {
        int mtrCnt = resultList.size();
        for (int i = 0; i < mtrCnt; i++) {
            Map<String, Object> result = resultList.get(i);
            // MTR_LB_CD is empty
            if (!hasValue((String) result.get("MTR_LB_CD"))) {
                updateStageTablesStatus(stagePk, PROC_STS.ERROR);
                sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, NSBM0142E, new String[]{"FSR No. " + fsrNo, "Cross Service Meter Label Code" }));
                return false;
            }
            setValue(pMsgNSZC005001.xxMtrReadList.no(i).mdseCd, mdseCd);
            // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//            setValue(pMsgNSZC005001.xxMtrReadList.no(i).mtrDt, taskStageFirstRowTMsg.fsrVisitArvDt.getValue());
            setValue(pMsgNSZC005001.xxMtrReadList.no(i).mtrDt, pMsgNSZC005001.fsrVisitArvDt);
            // END 2016/04/19 Y.Takeno [QC#5459, MOD]
            // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//          setValue(pMsgNSZC005001.xxMtrReadList.no(i).mtrReadDt, taskStageFirstRowTMsg.fsrVisitArvDt.getValue());
            setValue(pMsgNSZC005001.xxMtrReadList.no(i).mtrReadDt, pMsgNSZC005001.fsrVisitArvDt);
            // END 2016/04/19 Y.Takeno [QC#5459, MOD]
            setValue(pMsgNSZC005001.xxMtrReadList.no(i).mtrReadTpCd, DS_MTR_READ_TP.OTHER);
            setValue(pMsgNSZC005001.xxMtrReadList.no(i).mtrReadSrcTpCd, MTR_READ_SRC_TP.SERVICE);
            setValue(pMsgNSZC005001.xxMtrReadList.no(i).dsContrNum, dsContrNum);
            setValue(pMsgNSZC005001.xxMtrReadList.no(i).mtrLbCd,  (String) result.get("MTR_LB_CD"));
            setValue(pMsgNSZC005001.xxMtrReadList.no(i).dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.SERVICE_READS);
            // START 2016/06/08 [QC#9646, MOD]
            BigDecimal readMtr;
            BigDecimal testCnt = BigDecimal.ZERO;
            if (hasValue((String) result.get("CRS_SVC_READ_MTR_ORIG_TXT"))) {
                allEmptyFlg  = false;
                readMtr = convertToNumber((String) result.get("CRS_SVC_READ_MTR_ORIG_TXT"));
                if (hasValue((String) result.get("CRS_SVC_TEST_MTR_ORIG_TXT"))) {
                    testCnt = convertToNumber((String) result.get("CRS_SVC_TEST_MTR_ORIG_TXT"));
                }
            } else {
                //setValue(pMsgNSZC005001.xxMtrReadList.no(j).mtrCnt, BigDecimal.ZERO);
                readMtr = getLastMeterCount(svcMachMstrPK, (String) result.get("MTR_LB_CD"), pMsgNSZC005001.fsrVisitArvDt.getValue());
            }
            setValue(pMsgNSZC005001.xxMtrReadList.no(i).mtrCnt, readMtr.subtract(testCnt));
            // QC#61449 2023/05/29 Add Start
            if (!hasValue((String) result.get("CRS_SVC_READ_MTR_ORIG_TXT"))) {
                readMtr = getLastMeterCount(svcMachMstrPK, (String) result.get("MTR_LB_CD"), pMsgNSZC005001.fsrVisitCpltDt.getValue());
            }
            // QC#61449 2023/05/29 Add End
            // Output Meter Data
            setValue(pMsgNSZC005001.xxMtrReadList.no(i + mtrCnt).mdseCd, pMsgNSZC005001.xxMtrReadList.no(i).mdseCd);
            // QC#61449 2023/05/29 Mod Start
            setValue(pMsgNSZC005001.xxMtrReadList.no(i + mtrCnt).mtrDt, pMsgNSZC005001.fsrVisitCpltDt);
            // QC#61449 2023/05/29 Mod End
            setValue(pMsgNSZC005001.xxMtrReadList.no(i + mtrCnt).mtrCnt, readMtr);
            // QC#61449 2023/05/29 Mod Start
            setValue(pMsgNSZC005001.xxMtrReadList.no(i + mtrCnt).mtrReadDt, pMsgNSZC005001.fsrVisitCpltDt);
            // QC#61449 2023/05/29 Mod End
            setValue(pMsgNSZC005001.xxMtrReadList.no(i + mtrCnt).mtrReadTpCd, pMsgNSZC005001.xxMtrReadList.no(i).mtrReadTpCd);
            setValue(pMsgNSZC005001.xxMtrReadList.no(i + mtrCnt).mtrReadSrcTpCd, pMsgNSZC005001.xxMtrReadList.no(i).mtrReadSrcTpCd);
            setValue(pMsgNSZC005001.xxMtrReadList.no(i + mtrCnt).dsContrNum, pMsgNSZC005001.xxMtrReadList.no(i).dsContrNum);
            setValue(pMsgNSZC005001.xxMtrReadList.no(i + mtrCnt).mtrLbCd,  pMsgNSZC005001.xxMtrReadList.no(i).mtrLbCd);
            setValue(pMsgNSZC005001.xxMtrReadList.no(i + mtrCnt).dsMtrReadTpGrpCd, pMsgNSZC005001.xxMtrReadList.no(i).dsMtrReadTpGrpCd);
            // END 2016/06/08 [QC#9646, MOD]
        }
        if (allEmptyFlg) {
            pMsgNSZC005001.xxMtrReadList.clear();
            return true;
        }
        //pMsgNSZC005001.xxMtrReadList.setValidCount(resultList.size());
        pMsgNSZC005001.xxMtrReadList.setValidCount(mtrCnt * 2);
        // END 2018/11/28 S.Kitamura [QC#29016, MOD]
        return true;
    }

    // START 2019/08/23 K.Fujimoto [QC#52415, MOD]
    // private List<Map<String, Object>> findCrsRcvMtrStage(BigDecimal stagePk, BigDecimal mtrGrpPk) {
    private List<Map<String, Object>> findCrsRcvMtrStage(BigDecimal stagePk, BigDecimal mtrGrpPk, String fsrNum) {
    // START 2019/08/23 K.Fujimoto [QC#52415, MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("crsSvcRcvHdrStagePk", stagePk);
        param.put("procStsCd", PROC_STS.IN_COMPLETED);
        param.put("mtrGrpPk", mtrGrpPk);
        // START 2019/08/23 K.Fujimoto [QC#52415, ADD]
        param.put("fsrNum", fsrNum);
        // END   2019/08/23 K.Fujimoto [QC#52415, ADD]
        return (List<Map<String, Object>>) this.ssmClient.queryObjectList("getCrsSvcRcvMtrStage", param);
    }

    // START 2019/08/23 K.Fujimoto [QC#52415, MOD]
    // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//    private boolean setupPMsgFromCrsSvcRcvPrtStage(String fsrNo, BigDecimal stagePk) {
    //private boolean setupPMsgFromCrsSvcRcvPrtStage(NSZC005001PMsg pMsgNSZC005001, String fsrNo, BigDecimal stagePk) {
    private boolean setupPMsgFromCrsSvcRcvPrtStage(NSZC005001PMsg pMsgNSZC005001, String fsrNo, BigDecimal stagePk, String fsrNum) {
    // END 2016/04/19 Y.Takeno [QC#5459, MOD]
    // END   2019/08/23 K.Fujimoto [QC#52415, MOD]
        // START 2019/08/23 K.Fujimoto [QC#52415, MOD]
        // CRS_SVC_RCV_PRT_STAGETMsgArray prtTMsgArray = findCrsRcvPrtStage(stagePk);
        CRS_SVC_RCV_PRT_STAGETMsgArray prtTMsgArray = findCrsRcvPrtStage(stagePk, fsrNum);
        // END   2019/08/23 K.Fujimoto [QC#52415, MOD]
        // START 2016/04/19 Y.Takeno [QC#5459, ADD]
        SVC_TASKTMsg svcTaskTMsg = findSvcTask(pMsgNSZC005001.xxVisitTaskList.no(0).svcTaskNum.getValue());
        // START 2016/05/17 Y.Takeno [QC#8067, ADD]
        if (svcTaskTMsg == null) {
            updateStageTablesStatus(stagePk, PROC_STS.ERROR);
            sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, NSBM0148E, new String[] {fsrNo }));
            return false;
        }
        // END 2016/05/17 Y.Takeno [QC#8067, ADD]
        String svcTaskNum = svcTaskTMsg.svcTaskNum.getValue();
        String dsSvcCallTpCd = svcTaskTMsg.dsSvcCallTpCd.getValue();
        // END 2016/04/19 Y.Takeno [QC#5459, ADD]

        for (int i = 0; i < prtTMsgArray.getValidCount(); i++) {
            CRS_SVC_RCV_PRT_STAGETMsg prtStageTMsg = prtTMsgArray.no(i);
            // START 2016/04/19 Y.Takeno [QC#5459, DEL]
//            String svcTaskNum = this.cpltTargetFsrVisitTMsg.svcTaskNum.getValue();
//            String dsSvcCallTpCd = this.cpltTargetSvcTaskTMsg.dsSvcCallTpCd.getValue();
            // END 2016/04/19 Y.Takeno [QC#5459, DEL]

            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).svcTaskNum, svcTaskNum);
            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).dsSvcCallTpCd, dsSvcCallTpCd);
            // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).prtUsedByTechCd, this.taskStageFirstRowTMsg.crsSvcTechCd);
            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).prtUsedByTechCd, svcTaskTMsg.techCd);
//            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).prtUsedByTechLocCd, this.taskStageFirstRowTMsg.crsSvcTechCd.getValue() + "G");
            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).prtUsedByTechLocCd, svcTaskTMsg.techCd.getValue() + "G");
            // END 2016/04/19 Y.Takeno [QC#5459, MOD]
            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).mdseCd, prtStageTMsg.crsSvcPrtMdseCd);
            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).svcPrtQty, convertToNumber(prtStageTMsg.crsSvcPrtQtyOrigTxt.getValue()));
            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).uomCd, PKG_UOM.EACH);
            // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).svcExecDt, this.taskStageFirstRowTMsg.fsrVisitArvDt.getValue());
            // QC#61449 2023/05/29 Mod Start
            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).svcExecDt, pMsgNSZC005001.fsrVisitCpltDt);
            // QC#61449 2023/05/29 Mod End
            // END 2016/04/19 Y.Takeno [QC#5459, MOD]
            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).fsrUsgProcFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsgNSZC005001.xxFsrUsgList.no(i).svcInvtyExFlg, ZYPConstant.FLG_ON_Y);
        }
        pMsgNSZC005001.xxFsrUsgList.setValidCount(prtTMsgArray.getValidCount());

        return true;
    }

    // START 2019/08/23 K.Fujimoto [QC#52415, MOD]
    //private CRS_SVC_RCV_PRT_STAGETMsgArray findCrsRcvPrtStage(BigDecimal stagePk) {
    private CRS_SVC_RCV_PRT_STAGETMsgArray findCrsRcvPrtStage(BigDecimal stagePk, String fsrNum) {
    // END   2019/08/23 K.Fujimoto [QC#52415, MOD]
        CRS_SVC_RCV_PRT_STAGETMsg inMsg = new CRS_SVC_RCV_PRT_STAGETMsg();
        // inMsg.setSQLID("002");
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("crsSvcRcvHdrStagePk01", stagePk);
        inMsg.setConditionValue("crsSvcProcStsCd01", PROC_STS.IN_COMPLETED);
        // START 2019/08/23 K.Fujimoto [QC#52415, ADD]
        inMsg.setConditionValue("fsrNum01", fsrNum);
        // END   2019/08/23 K.Fujimoto [QC#52415, ADD]
        return (CRS_SVC_RCV_PRT_STAGETMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private String convertToTime(String src) {
        if (src != null && src.length() == LENGTH_HHMM) {
            return src + "00";
        }
        return src;
    }

    private BigDecimal convertToNumber(String src) {
        if (hasValue(src)) {
            return new BigDecimal(src);
        }
        return null;
    }

    // START 2016/04/19 Y.Takeno [QC#5459, DEL]
//    private boolean isFsrVisitCanComplete(FSR_VISITTMsg tMsg) {
//
//        if (SVC_TASK_STS.COMPLETED.equals(tMsg.fsrVisitStsCd.getValue())) {
//            return false;
//        }
//
//        if (SVC_TASK_STS.CLOSED.equals(tMsg.fsrVisitStsCd.getValue())) {
//            return false;
//        }
//
//        if (SVC_TASK_STS.CANCELLED.equals(tMsg.fsrVisitStsCd.getValue())) {
//            return false;
//        }
//
//        return true;
//    }
    // END 2016/04/19 Y.Takeno [QC#5459, DEL]

// START 2016/06/08 [QC#9640, MOD]
    private boolean callApiNSZC005001(BigDecimal stagePk) {
        NSZC005001 apiDebRief = new NSZC005001();
        // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//        api.execute(pMsgNSZC005001, ONBATCH_TYPE.BATCH);
        
        //Any case ,call api by "MODE_DEBRIEF" mode
        //Start 2019/10/14 K.Fujimoto [QC#54011,DEL]
        //apiDebRief.execute(this.pMsgNSZC005001List, ONBATCH_TYPE.BATCH);
        //List<NSZC005001PMsg> pMsgNSZC005001InstallCheckList = new ArrayList<NSZC005001PMsg>();
        //END 2019/10/14 K.Fujimoto [QC#54011,DEL]
        for (NSZC005001PMsg pMsgNSZC005001 : this.pMsgNSZC005001List) {
            //Start 2019/10/14 K.Fujimoto [QC#54011,ADD]
            apiDebRief.execute(pMsgNSZC005001, ONBATCH_TYPE.BATCH);
            //End   2019/10/14 K.Fujimoto [QC#54011,ADD]
            if (S21ApiUtil.isXxMsgId(pMsgNSZC005001)) {
//            S21ApiMessage msg = S21ApiUtil.getXxMsgList(this.pMsgNSZC005001).get(0);
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsgNSZC005001).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                String fsrNo = pMsgNSZC005001.fsrNum.getValue();

                rollback();
                updateStageTablesStatus(stagePk, PROC_STS.ERROR);
                sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, msgId, msgPrms));
                return false;
            }
            if(pMsgNSZC005001.xxFsrIstlChkList.getValidCount() > 0){
                setValue(pMsgNSZC005001.xxModeCd,NSZC005001.MODE_INSTALL_CHECK);
                //Start 2019/10/14 K.Fujimoto [QC#54011,MOD]
                //If pMsg contains Install Check List(Install Call),call api by "MODE_INSTALL_CHECK" mode
                //pMsgNSZC005001InstallCheckList.add(pMsgNSZC005001);
                NSZC005001 apiInstallCheck = new NSZC005001();
                apiInstallCheck.execute(pMsgNSZC005001, ONBATCH_TYPE.BATCH);
                if (S21ApiUtil.isXxMsgId(pMsgNSZC005001)) {
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsgNSZC005001).get(0);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    String fsrNo = pMsgNSZC005001.fsrNum.getValue();
                    rollback();
                    updateStageTablesStatus(stagePk, PROC_STS.ERROR);
                    sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, msgId, msgPrms));
                    return false;
                }
                //End   2019/10/14 K.Fujimoto [QC#54011,MOD]
             }
        }
        //Start 2019/10/14 K.Fujimoto [QC#54011,DEL]
        //If pMsg contains Install Check List(Install Call),call api by "MODE_INSTALL_CHECK" mode
//        if (pMsgNSZC005001InstallCheckList.size() > 0){
//            NSZC005001 apiInstallCheck = new NSZC005001();
//            apiInstallCheck.execute(this.pMsgNSZC005001List, ONBATCH_TYPE.BATCH);
//
//            for (NSZC005001PMsg pMsgNSZC005001 : pMsgNSZC005001InstallCheckList) {
//                  if (S21ApiUtil.isXxMsgId(pMsgNSZC005001)) {
//                           S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsgNSZC005001).get(0);
//                           String msgId = msg.getXxMsgid();
//                           String[] msgPrms = msg.getXxMsgPrmArray();
//                           String fsrNo = pMsgNSZC005001.fsrNum.getValue();
//                           rollback();
//                           updateStageTablesStatus(stagePk, PROC_STS.ERROR);
//                           sendRequestDataErrorMail(createMailTemplateParamMap(fsrNo, msgId, msgPrms));
//                           return false;
//                     }
//               }
//        }
        //END 2019/10/14 K.Fujimoto [QC#54011,DEL]
        return true;
        // END 2016/04/19 Y.Takeno [QC#5459, MOD]
    }
// END 2016/06/08 [QC#9640, MOD]

    private void updateStageTablesStatus(BigDecimal stagePk, String crsSvcProcStsCd) {
        updateCrsSvcRcvHdrStage(stagePk, crsSvcProcStsCd);
        updateCrsSvcRcvTaskStage(stagePk, crsSvcProcStsCd);
        updateCrsSvcRcvMtrStage(stagePk, crsSvcProcStsCd);
        updateCrsSvcRcvPrtStage(stagePk, crsSvcProcStsCd);
    }

    private void updateCrsSvcRcvHdrStage(BigDecimal stagePk, String crsSvcProcStsCd)  {
        CRS_SVC_RCV_HDR_STAGETMsg tMsg = new CRS_SVC_RCV_HDR_STAGETMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.crsSvcRcvHdrStagePk, stagePk);
        tMsg = (CRS_SVC_RCV_HDR_STAGETMsg) EZDTBLAccessor.findByKeyForUpdate(tMsg);
        setValue(tMsg.crsSvcProcStsCd, crsSvcProcStsCd);

        EZDTBLAccessor.update(tMsg);
    }

    private void updateCrsSvcRcvTaskStage(BigDecimal stagePk, String crsSvcProcStsCd) {
        CRS_SVC_RCV_TASK_STAGETMsg inMsg = new CRS_SVC_RCV_TASK_STAGETMsg();
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("crsSvcRcvHdrStagePk01", stagePk);
        inMsg.setSQLID("001");
        CRS_SVC_RCV_TASK_STAGETMsgArray tMsgArray = (CRS_SVC_RCV_TASK_STAGETMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            CRS_SVC_RCV_TASK_STAGETMsg tMsg = tMsgArray.no(i);
            setValue(tMsg.crsSvcProcStsCd, crsSvcProcStsCd);
            EZDTBLAccessor.update(tMsg);
        }
    }

    private void updateCrsSvcRcvMtrStage(BigDecimal stagePk, String crsSvcProcStsCd) {
        CRS_SVC_RCV_MTR_STAGETMsg inMsg = new CRS_SVC_RCV_MTR_STAGETMsg();
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("crsSvcRcvHdrStagePk01", stagePk);
        inMsg.setSQLID("001");
        CRS_SVC_RCV_MTR_STAGETMsgArray tMsgArray = (CRS_SVC_RCV_MTR_STAGETMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            CRS_SVC_RCV_MTR_STAGETMsg tMsg = tMsgArray.no(i);
            setValue(tMsg.crsSvcProcStsCd, crsSvcProcStsCd);
            EZDTBLAccessor.update(tMsg);
        }
    }

    private void updateCrsSvcRcvPrtStage(BigDecimal stagePk, String crsSvcProcStsCd) {
        CRS_SVC_RCV_PRT_STAGETMsg inMsg = new CRS_SVC_RCV_PRT_STAGETMsg();
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("crsSvcRcvHdrStagePk01", stagePk);
        inMsg.setSQLID("001");
        CRS_SVC_RCV_PRT_STAGETMsgArray tMsgArray = (CRS_SVC_RCV_PRT_STAGETMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            CRS_SVC_RCV_PRT_STAGETMsg tMsg = tMsgArray.no(i);
            setValue(tMsg.crsSvcProcStsCd, crsSvcProcStsCd);
            EZDTBLAccessor.update(tMsg);
        }
    }

    private void sendRequestDataErrorMail(Map<String, String> paramMap) {

        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GROUP_ID_FROM);
        groupFrom.setMailKey1(MAIL_GROUP_KEY_FROM);
        S21MailAddress fromAddress;
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"FROM mail-address.", (MAIL_GROUP_ID_FROM + "/" + MAIL_GROUP_KEY_FROM) });
        }

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, MAIL_GROUP_ID_TO);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();
        if (addrToList == null || addrToList.isEmpty()) {
            throw new S21AbendException(NSBM0135E, new String[] {"TO mail-address.", MAIL_GROUP_ID_TO });
        }

        // Get Mail Template.
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, MAIL_TEMPLATE_ID_01);
        if (!ZYPCommonFunc.hasValue(template.getBody())) {
            throw new S21AbendException(NSBM0077E, new String[] {MAIL_TEMPLATE_ID_01 });
        }

        fromAddress = addrFromList.get(0);

        S21Mail mail = new S21Mail(glblCmpyCd);

        // Set From Mail Address.
        mail.setFromAddress(fromAddress);
        // Set To Mail Address.
        mail.setToAddressList(addrToList);

        // Set Parameter
        Iterator<Map.Entry<String, String>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            template.setTemplateParameter(entry.getKey(), entry.getValue());
        }

        mail.setMailTemplate(template);
        mail.setSubject(template.getSubject());
        mail.postMail();
    }

    private Map<String, String> createMailTemplateParamMap(String fsrNum, String msgId, String[] msgPrms) {
        Map<String, String> prmMap = new HashMap<String, String>();
        String errDate = ZYPDateUtil.getCurrentSystemTime(MAIL_DATE_TIME_FORMAT);

        // START 2016/04/19 Y.Takeno [QC#5459, ADD]
        // ${batchId}
        prmMap.put(MAIL_ITEM_BATCH_ID, this.getClass().getSimpleName());
        // END 2016/04/19 Y.Takeno [QC#5459, ADD]

        // ${errDate}
        prmMap.put(MAIL_ITEM_ERR_DATE, errDate);

        // ${message}
        // START 2016/04/19 Y.Takeno [QC#5459, MOD]
//        if (msgId != null && msgPrms != null) {
//            String msg = S21MessageFunc.clspGetMessage(msgId, msgPrms);
//            prmMap.put(MAIL_ITEM_MESSAGE, msg);
//        } else if (msgId != null && msgPrms == null) {
//            String msg = S21MessageFunc.clspGetMessage(msgId);
//            prmMap.put(MAIL_ITEM_MESSAGE, msg);
//        }
        ZYPEZDMessageInfoUtil util = new ZYPEZDMessageInfoUtil();
        String msg = util.getI18nMessage(null, msgId, msgPrms);
        prmMap.put(MAIL_ITEM_MESSAGE, msg);
        // END 2016/04/19 Y.Takeno [QC#5459, MOD]

        // ${fsrnum}
        prmMap.put(MAIL_ITEM_FSR_NUM, fsrNum);

        // QC#57307 Add Start
        if(ZYPCommonFunc.hasValue(this.curTaskNum)) {
            prmMap.put(MAIL_ITEM_TASK_NUM, this.curTaskNum);
        }
        if(ZYPCommonFunc.hasValue(this.curTaskNum)) {
            prmMap.put(MAIL_ITEM_ORACLE_SR_NUM, this.curOracleSrNum);
        }
        // QC#57307 Add End

        return prmMap;
    }

    private void initVarConstMap() {

        this.varConstMap = new HashMap<String, String>();

        String invCcyCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0290_INV_CCY_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(invCcyCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0290_INV_CCY_CD });
        }
        this.varConstMap.put(NSBB0290_INV_CCY_CD, invCcyCd);

        String svcBillTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0290_SVC_BILL_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(svcBillTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0290_SVC_BILL_TP_CD });
        }
        this.varConstMap.put(NSBB0290_SVC_BILL_TP_CD, svcBillTpCd);

        String pmtTermCshDiscCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0290_PMT_TERM_CASH_DISC_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(pmtTermCshDiscCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0290_PMT_TERM_CASH_DISC_CD });
        }
        this.varConstMap.put(NSBB0290_PMT_TERM_CASH_DISC_CD, pmtTermCshDiscCd);

        String svcPblmTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0290_SVC_PBLM_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(svcPblmTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0290_SVC_PBLM_TP_CD });
        }
        this.varConstMap.put(NSBB0290_SVC_PBLM_TP_CD, svcPblmTpCd);

        String pblmLocTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0290_SVC_PBLM_LOC_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(pblmLocTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0290_SVC_PBLM_LOC_TP_CD });
        }
        this.varConstMap.put(NSBB0290_SVC_PBLM_LOC_TP_CD, pblmLocTpCd);

        String svcRblmLocTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0290_SVC_PBLM_RSN_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(svcRblmLocTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0290_SVC_PBLM_RSN_TP_CD });
        }
        this.varConstMap.put(NSBB0290_SVC_PBLM_RSN_TP_CD, svcRblmLocTpCd);

        String svcPblmCrctTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSBB0290_SVC_PBLM_CRCT_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(svcPblmCrctTpCd)) {
            throw new S21AbendException(NSBM0069E, new String[] {NSBB0290_SVC_PBLM_CRCT_TP_CD });
        }
        this.varConstMap.put(NSBB0290_SVC_PBLM_CRCT_TP_CD, svcPblmCrctTpCd);
    }

    // START 2016/04/19 Y.Takeno [QC#5459, ADD]
    private List<Map<String, Object>> findCrsSvcRcvTaskStage(BigDecimal crsSvcRcvHdrStagePk, String fsrNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("crsSvcRcvHdrStagePk", crsSvcRcvHdrStagePk);
        // Del Start 2018/02/01 QC#23569
//        param.put("procStsCd", PROC_STS.IN_COMPLETED);
//        param.put("fsrNum", fsrNum);
        // Del End 2018/02/01 QC#23569
        return (List<Map<String, Object>>) this.ssmClient.queryObjectList("getCrsSvcRcvTaskStage", param);
    }

// START 10/06/2016 N.Arai [QC#14750, MOD]
//    private NSZC045001PMsg createFollowUpCall(CRS_SVC_RCV_TASK_STAGETMsg crsSvcRcvTaskStageTMsg, BigDecimal svcMachMstrPk) {
      private NSZC045001PMsg createFollowUpCall(CRS_SVC_RCV_TASK_STAGETMsg crsSvcRcvTaskStageTMsg, BigDecimal svcMachMstrPk, String fsrNum) {

        NSZC045001PMsg apiPMsg = new NSZC045001PMsg();
//        FSR_VISITTMsg fsrVisitTMsg = findFsrVisit(crsSvcRcvTaskStageTMsg.fsrNum.getValue());
        FSR_VISITTMsg fsrVisitTMsg = findFsrVisit(fsrNum);
        if (fsrVisitTMsg == null) {
            return null;
        }
        SVC_TASKTMsg svcTaskTMsg = findSvcTask(fsrVisitTMsg.svcTaskNum.getValue());
        if (svcTaskTMsg == null) {
            return null;
        }

        setValue(apiPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(apiPMsg.slsDt, this.slsDt);
        setValue(apiPMsg.xxModeCd, NSZC045001.PROCESS_MODE_ADD_TASK);
//        setValue(apiPMsg.fsrNum, crsSvcRcvTaskStageTMsg.fsrNum);
        setValue(apiPMsg.fsrNum, fsrNum);
// END 10/06/2016 N.Arai [QC#14750, MOD]
        setValue(apiPMsg.userId, BATCH_PROGRAM_ID);
        setValue(apiPMsg.bypsPrfTechFlg, ZYPConstant.FLG_OFF_N);
        setValue(apiPMsg.dsSvcCallTpCd, svcTaskTMsg.dsSvcCallTpCd);
        setValue(apiPMsg.svcTaskRcvDt, crsSvcRcvTaskStageTMsg.fsrVisitArvDt);
        setValue(apiPMsg.svcTaskRcvTm, SVC_TASK_RCV_TM);
        setValue(apiPMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(apiPMsg.svcPblmRsnTpCd, this.varConstMap.get(NSBB0290_SVC_PBLM_RSN_TP_CD));
        // mod start 2016/07/04 CSA Defect#9677
        setValue(apiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/07/04 CSA Defect#9677
        setValue(apiPMsg.xxSvcTaskList.no(0).svcTaskNum, fsrVisitTMsg.svcTaskNum);
        setValue(apiPMsg.xxSvcTaskList.no(0).svcCallPrtyCd, svcTaskTMsg.svcCallPrtyCd);
        apiPMsg.xxSvcTaskList.setValidCount(1);

        return apiPMsg;
    }

    private String callFollowUpCall(NSZC045001PMsg apiPMsg) {

        new NSZC045001().execute(apiPMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            return apiPMsg.xxMsgIdList.no(0).xxMsgId.getValue();
        }
        return null;
    }

    private void updateSvcTask(String svcTaskNum, String crsSvcSrNum) {
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        setValue(svcTaskTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcTaskTMsg.svcTaskNum, svcTaskNum);
        svcTaskTMsg = (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdate(svcTaskTMsg);
        setValue(svcTaskTMsg.crsSvcSrNum, crsSvcSrNum);

        EZDTBLAccessor.update(svcTaskTMsg);
    }

    private CRS_SVC_RCV_TASK_STAGETMsg findCrsSvcRcvTaskStage(BigDecimal crsSvcRcvTaskStagePk, BigDecimal crsSvcRcvHdrStagePk) {
        CRS_SVC_RCV_TASK_STAGETMsg crsSvcRcvTaskStageTMsg = new CRS_SVC_RCV_TASK_STAGETMsg();
        setValue(crsSvcRcvTaskStageTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(crsSvcRcvTaskStageTMsg.crsSvcRcvTaskStagePk, crsSvcRcvTaskStagePk);
        setValue(crsSvcRcvTaskStageTMsg.crsSvcRcvHdrStagePk, crsSvcRcvHdrStagePk);
        return (CRS_SVC_RCV_TASK_STAGETMsg) EZDTBLAccessor.findByKey(crsSvcRcvTaskStageTMsg);
    }

    private FSR_VISITTMsg findFsrVisit(String fsrNum, String svcTaskNum) {
        FSR_VISITTMsg fsrVisitTMsg = new FSR_VISITTMsg();
        fsrVisitTMsg.setSQLID("003");
        fsrVisitTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        fsrVisitTMsg.setConditionValue("fsrNum01", fsrNum);
        fsrVisitTMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        FSR_VISITTMsgArray fsrVisitTMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(fsrVisitTMsg);
        if (fsrVisitTMsgArray == null || fsrVisitTMsgArray.getValidCount() == 0) {
            // START 2016/05/17 Y.Takeno [QC#8067, MOD]
            return null;
            // END   2016/05/17 Y.Takeno [QC#8067, MOD]
        }
        return fsrVisitTMsgArray.no(0);
    }

    private FSR_VISITTMsg findFsrVisit(String fsrNum) {
        FSR_VISITTMsg fsrVisitTMsg = new FSR_VISITTMsg();
        fsrVisitTMsg.setSQLID("004");
        fsrVisitTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        fsrVisitTMsg.setConditionValue("fsrNum01", fsrNum);
        FSR_VISITTMsgArray fsrVisitTMsgArray = (FSR_VISITTMsgArray) EZDTBLAccessor.findByCondition(fsrVisitTMsg);
        if (fsrVisitTMsgArray == null || fsrVisitTMsgArray.getValidCount() == 0) {
            // START 2016/05/17 Y.Takeno [QC#8067, MOD]
            return null;
            // END   2016/05/17 Y.Takeno [QC#8067, MOD]
        }
        return fsrVisitTMsgArray.no(0);
    }

    private SVC_TASKTMsg findSvcTask(String svcTaskNum) {
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        setValue(svcTaskTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(svcTaskTMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) EZDTBLAccessor.findByKey(svcTaskTMsg);
    }
    // END 2016/04/19 Y.Takeno [QC#5459, ADD]
    
    // START 2018/09/18 K.Fujimoto [QC#28294, MOD]
    /**
     * Get Parameter to Query Install CheckList For Install.
     * @return Query Parameter
     */
    private Map<String, Object> getInstallCheckListParam(Map<String, Object> paramMap) {
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("machStsCd", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        paramMap.put("machStsCdInstl", SVC_MACH_MSTR_STS.INSTALLED);
        paramMap.put("svcMachTpCd", SVC_MACH_TP.ACCESSORY);
        return paramMap;
    }

    /**
     * Get DS Service Call Type.
     * @param dsSvcCallTpNm
     * @return DS Service Call Type TMsg
     */
    private DS_SVC_CALL_TPTMsg getDsSvcCallTp(String dsSvcCallTpCd) {
        DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = new DS_SVC_CALL_TPTMsg();
        setValue(dsSvcCallTpTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsSvcCallTpTMsg.dsSvcCallTpCd, dsSvcCallTpCd);
        return (DS_SVC_CALL_TPTMsg) EZDTBLAccessor.findByKey(dsSvcCallTpTMsg);
    }

    /**
     * Get String Value from Map. (With Conversion from Null to "")
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return String
     */
    private String getString(Map<String, Object> map, String key) {
        String ret = (String) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return "";
    }

    /**
     * Get BigDecimal Value from Map. (With Conversion from Null to
     * BigDecimal.ZERO)
     * @param map Map&lt;Object, Object&gt;
     * @param key String
     * @return BigDecimal
     */
    private BigDecimal getBigDecimal(Map<String, Object> map, String key) {
        BigDecimal ret = (BigDecimal) map.get(key);
        if (ZYPCommonFunc.hasValue(ret)) {
            return ret;
        }
        return BigDecimal.ZERO;
    }
    // END 2018/09/18 K.Fujimoto [QC#28294, MOD]
    // START 2018/11/28 S.Kitamura [QC#29016, ADD]
    private BigDecimal getLastMeterCount(BigDecimal svcMachMstrPK, String mtrLbCd, String mtrReadDt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("svcMachMstrPk", svcMachMstrPK);
        param.put("mtrLbCd", mtrLbCd);
        param.put("mtrReadDt", mtrReadDt);
        param.put("glblCmpyCd", this.glblCmpyCd);
        BigDecimal result = (BigDecimal) this.ssmClient.queryObject("getLastMeterCount", param);
        if (result == null) {
            return BigDecimal.ZERO;
        }
        return result;
    }
    // END 2018/11/28 S.Kitamura [QC#29016, ADD]
    // START 2019/08/23 K.Fujimoto [QC#52415, ADD]
    private String getCrsSrNum(String fsrNum, String fsrVisitNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("fsrNum", fsrNum);
        param.put("fsrVisitNum", fsrVisitNum);
        return (String) this.ssmClient.queryObject("getCrsSrNum", param);
    }
    // END   2019/08/23 K.Fujimoto [QC#52415, ADD]

    // QC#61449 2023/05/29 Add Start
    private String returnBeforeDay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date setDt;
        try {
            setDt = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(setDt);
            cal.add(Calendar.DATE, -1);
            date = sdf.format(cal.getTime());
        } catch (ParseException e) {
            return null;
        }
        return date;
    }
    // QC#61449 2023/05/29 Add End
}
