/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC038001;

import static com.canon.cusa.s21.api.NSZ.NSZC038001.constant.NSZC038001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ACCT_CR_PRFLTMsg;
import business.db.FSRTMsg;
import business.db.FSR_EVENTTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.S21_PSNTMsg;
import business.db.SVC_DISPT_EVENTTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.db.SVC_TASK_HLDTMsg;
import business.db.SVC_TASK_HLDTMsgArray;
import business.parts.NSZC038001PMsg;
import business.parts.NSZC043001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Service Credit Review API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         K.Yamada        Create           N/A
 * 2015/12/14   Hitachi         K.Yamada        Update          CSA QC#895
 * 2016/02/26   Hitachi         T.Iwamoto       Update          CSA QC#4113,4645
 * 2016/03/23   Hitachi         T.Tomita        Update          CSA QC#895
 * 2016/04/01   Hitachi         K.Yamada        Update          CSA QC#6407
 * 2016/06/16   Hitach          A.Kohinata      Update          QC#9677
 * 2016/08/01   Hitachi         K.Yamada        Update          CSA QC#4324
 * 08/11/2016   Fujitsu         S.Nakai         Update          CSA QC#8421
 * 2016/11/18   Hitachi         T.Mizuki        Update          CSA QC#15954
 * 2017/07/19   Hitachi         K.Kim           Update          CSA QC#19899
 * 2017/09/27   Hitachi         T.Tomita        Update          CSA QC#19242
 * 2018/05/08   CITS            M.Naito         Update          CSA QC#24879
 * 2019/02/15   Hitachi         K.Kitachi       Update          CSA QC#30165
 * 2019/05/23   Hitachi         K.Kohinata      Update          CSA QC#50499
 * 2019/08/05   Hitachi         K.Fujimoto      Update          CSA QC#52307
 * 2022/04/11   Hitachi         K.Kitachi       Update          CSA QC#59899
 * </pre>
 */
public class NSZC038001 extends S21ApiCommonBase {

    // add start 2016/03/23 CSA Defect#895
    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;
    // add end 2016/03/23 CSA Defect#895

    // Add Start 2017/09/08 QC#19242
    /** Out of Territory Service Branch Code */
    private String outTrtySvcBrCd = null;
    // Add End 2017/09/08 QC#19242

    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
    /** Click send exclusion call type list */
    private String[] clickSendExclCallTpList = null;
    // END 2019/02/15 K.Kitachi [QC#30165, ADD]

    /**
     * <pre>
     * Constructor
     * </pre>
     */
    public NSZC038001() {
        super();
        // add start 2016/03/23 CSA Defect#895
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // add end 2016/03/23 CSA Defect#895
    }

    /**
     * <pre>
     * Main routine of Service Credit Review API
     * </pre>
     * @param pMsg Input parameter
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(NSZC038001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        if (!checkParam(msgMap)) {
            msgMap.flush();
            return;
        }
        if (!checkFsr(msgMap)) {
            msgMap.flush();
            return;
        }
        // add start 2015/12/14 CSA Defect#895
        if (!checkFsrVisit(msgMap)) {
            msgMap.flush();
            return;
        }
        // add end 2015/12/14 CSA Defect#895

        String processMode = pMsg.xxModeCd.getValue();
        if (MODE_APPROVE.equals(processMode)) {
            // mod start 2016/06/16 QC#9677
            doProcessApprove(msgMap, onBatchType);
            // mod end 2016/06/16 QC#9677
        } else if (MODE_REJECT.equals(processMode)) {
            doProcessReject(msgMap);
        }

        msgMap.flush();
    }

    /**
     * <pre>
     * Service Credit Review API (List)
     * </pre>
     * @param params Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(List<NSZC038001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NSZC038001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    private boolean checkParam(S21ApiMessageMap msgMap) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        // check common mandatory parameter
        checkCommonParameter(msgMap, pMsg.glblCmpyCd, NZZM0012E, "Global Company Code");
        checkCommonParameter(msgMap, pMsg.xxModeCd, NZZM0012E, "Mode Code");
        checkCommonParameter(msgMap, pMsg.slsDt, NZZM0012E, "Sales Date");
        checkCommonParameter(msgMap, pMsg.usrId, NZZM0012E, "User ID");
        checkCommonParameter(msgMap, pMsg.fsrNum, NZZM0012E, "FSR#");
        checkCommonParameter(msgMap, pMsg.svcTaskApvlDt, NZZM0012E, "Service Task Approve Date");
        checkCommonParameter(msgMap, pMsg.svcTaskApvlTm, NZZM0012E, "Service Task Approve Time");

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }

        if (!MODE_APPROVE.equals(pMsg.xxModeCd.getValue()) && !MODE_REJECT.equals(pMsg.xxModeCd.getValue())) {
            msgMap.addXxMsgId(NSZM0039E);
            return false;
        }

        return true;

    }

    private void checkCommonParameter(S21ApiMessageMap msgMap, EZDPItem item, String messageId, String... param) {
        if (!hasValue(item)) {
            msgMap.addXxMsgIdWithPrm(messageId, param);
        }
    }

    private boolean checkFsr(S21ApiMessageMap msgMap) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        FSRTMsg fsr = getFsr(pMsg);
        if (fsr == null) {
            msgMap.addXxMsgId(NSZM0498E);
            return false;
        }
        // del start 2015/12/14 CSA Defect#895
        //if (!SVC_TASK_STS.WAITING_FOR_CREDIT_APPROVAL.equals(fsr.fsrStsCd.getValue())) {
        //    msgMap.addXxMsgId(NSZM0193E);
        //    return false;
        //}
        // del end 2015/12/14 CSA Defect#895
        return true;

    }

    private FSRTMsg getFsr(NSZC038001PMsg pMsg) {

        FSRTMsg inMsg = new FSRTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.fsrNum, pMsg.fsrNum);

        return (FSRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    // add start 2015/12/14 CSA Defect#895
    private boolean checkFsrVisit(S21ApiMessageMap msgMap) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        FSR_VISITTMsgArray fsrVisits = getFsrVisits(pMsg);
        if (fsrVisits == null || fsrVisits.getValidCount() == 0) {
            msgMap.addXxMsgId(NSZM0485E);
            return false;
        }

        boolean hasCrHld = false;
        for (int i = 0; i < fsrVisits.getValidCount(); i++) {
            FSR_VISITTMsg fsrVisit = fsrVisits.no(i);
            if (SVC_TASK_STS.CREDIT_HOLD.equals(fsrVisit.fsrVisitStsCd.getValue())) {
                hasCrHld = true;
                break;
            }
        }
        if (!hasCrHld) {
            msgMap.addXxMsgIdWithPrm(NSZM0629E, new String[]{"FSR Visit status"});
            return false;
        }
        return true;
    }

    private FSR_VISITTMsgArray getFsrVisits(NSZC038001PMsg pMsg) {

        FSR_VISITTMsg inMsg = new FSR_VISITTMsg();
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("fsrNum01", pMsg.fsrNum.getValue());
        inMsg.setSQLID("004");

        return (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    private FSR_VISITTMsgArray getFsrVisitsForUpdate(NSZC038001PMsg pMsg) {

        FSR_VISITTMsg inMsg = new FSR_VISITTMsg();
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("fsrNum01", pMsg.fsrNum.getValue());
        inMsg.setSQLID("004");

        return (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
    }

    private S21_PSNTMsg getS21Psn(NSZC038001PMsg pMsg, String techCd) {

        S21_PSNTMsg inMsg = new S21_PSNTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.psnCd, techCd);

        return (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    // add end 2015/12/14 CSA Defect#895

    // mod start 2016/06/16 QC#9677
    private void doProcessApprove(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
    // mod end 2016/06/16 QC#9677

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();
        // Add Start 2017/09/08 QC#19242
        this.outTrtySvcBrCd = ZYPCodeDataUtil.getVarCharConstValue(OUT_TRTY_SVC_BR_CD, pMsg.glblCmpyCd.getValue());
        // Add End 2017/09/08 QC#19242
        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        String clickSendExclCallTp = ZYPCodeDataUtil.getVarCharConstValue(CLICK_SEND_EXCL_CALL_TP, pMsg.glblCmpyCd.getValue());
        this.clickSendExclCallTpList = clickSendExclCallTp.split(STR_COMMA);
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]
        SVC_TASKTMsgArray svcTaskArray = getSvcTask(pMsg);
        if (svcTaskArray.getValidCount() == 0) {
            return;
        }

        // add start 2016/08/01 CSA Defect#4324
        if (isCreditHardHoldCustomer(pMsg)) {
            msgMap.addXxMsgId(NSZM1045E);
            return;
        }
        // add end 2016/08/01 CSA Defect#4324

        // update SVC_TASK and SVC_TASK_HLD
        boolean hasErr = false;
        for (int i = 0; i < svcTaskArray.getValidCount(); i++) {
            SVC_TASKTMsg svcTask = svcTaskArray.no(i);
            if (!aprvSvcTask(msgMap, svcTask)) {
                hasErr = true;
                break;
            }

            SVC_TASK_HLDTMsgArray svcTaskHldArray = getSvcTaskHld(pMsg, svcTask.svcTaskNum.getValue());
            if (svcTaskHldArray.getValidCount() == 0) {
                continue;
            }

            for (int j = 0; j < svcTaskHldArray.getValidCount(); j++) {
                SVC_TASK_HLDTMsg svcTaskHld = svcTaskHldArray.no(j);
                if (!relSvcTaskHld(msgMap, svcTaskHld)) {
                    hasErr = true;
                    break;
                }
            }
        }

        if (hasErr) {
            return;
        }

        // add start 2015/12/14 CSA Defect#895
        // update FSR_VISIT and create FSR_EVENT
        FSR_VISITTMsgArray fsrVisits = getFsrVisitsForUpdate(pMsg);
        for (int i = 0; i < fsrVisits.getValidCount(); i++) {
            FSR_VISITTMsg fsrVisit = fsrVisits.no(i);
            if (!updateVisitAndCreateEvent(msgMap, fsrVisit)) {
                hasErr = true;
                break;
            }
        }

        if (hasErr) {
            return;
        }
        // add end 2015/12/14 CSA Defect#895

        // create memo
        if (!createSvcMemo(msgMap, APRV_CMNT)) {
            return;
        }

        // add start 2016/03/23 CSA Defect#895
        if (!chkNtfy(pMsg)) {
            return;
        }
        // add end 2016/03/23 CSA Defect#895

        // set manager list
        int mgrListIdx = 0;
        String managerOfUsr = getManager(pMsg.usrId.getValue());

        if (hasValue(managerOfUsr)) {
            setValue(pMsg.ManagerList.no(mgrListIdx).mgrPsnCd, managerOfUsr);
            mgrListIdx++;

            // del start 2016/03/23 CSA Defect#895
//            String managerOfManager = getManager(managerOfUsr);
//            if (hasValue(managerOfManager)) {
//                setValue(pMsg.ManagerList.no(mgrListIdx).mgrPsnCd, managerOfManager);
//                mgrListIdx++;
//            }
            // del end 2016/03/23 CSA Defect#895
            pMsg.ManagerList.setValidCount(mgrListIdx);
        }

        // add start 2016/06/16 QC#9677
// del start 2019/05/23 QC#50499
//        callSendTaskInfoAPI(msgMap, fsrVisits, onBatchType);
// del end 2019/05/23 QC#50499
        // add end 2016/06/16 QC#9677
    }

    private void doProcessReject(S21ApiMessageMap msgMap) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        SVC_TASKTMsgArray svcTaskArray = getSvcTask(pMsg);
        if (svcTaskArray.getValidCount() == 0) {
            return;
        }

        // update SVC_TASK and SVC_TASK_HLD
        boolean hasErr = false;
        for (int i = 0; i < svcTaskArray.getValidCount(); i++) {
            SVC_TASKTMsg svcTask = svcTaskArray.no(i);
            if (!rejSvcTask(msgMap, svcTask)) {
                hasErr = true;
                break;
            }
        }

        // add start 2018/05/08 CSA QC#24879
        // call FSR update API(NSZC043001)
        NSZC043001PMsg fsrUpdateApiPMsg = createNSZC043001PMsgCancelFsr(pMsg, svcTaskArray);
        if (!callApiNSZC043001(msgMap, fsrUpdateApiPMsg)) {
            hasErr = true;
        }
        // add end 2018/05/08 CSA QC#24879

        if (hasErr) {
            return;
        }

        // create memo
        if (!createSvcMemo(msgMap, REJ_CMNT)) {
            return;
        }

    }

    private SVC_TASKTMsgArray getSvcTask(NSZC038001PMsg pMsg) {

        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("fsrNum01", pMsg.fsrNum.getValue());
        inMsg.setConditionValue("svcCrHldFlg01", FLG_ON_Y);
        inMsg.setSQLID("007");
        inMsg.setMaxCount(0);

        return (SVC_TASKTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);
    }
    private SVC_TASKTMsg getSvcTask(String glblCmpyCd, String svcTaskNum) {

        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.svcTaskNum.setValue(svcTaskNum);

        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }
    private boolean aprvSvcTask(S21ApiMessageMap msgMap, SVC_TASKTMsg svcTask) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        // Add start 2019/08/05 CSA QC#52307
        FSR_VISITTMsg fsrVisitTmsg = getFsrVisit(pMsg.glblCmpyCd.getValue(), svcTask.svcTaskNum.getValue());
        SVC_TASKTMsg svcTaskTMsg = getSvcTask(pMsg.glblCmpyCd.getValue(), svcTask.svcTaskNum.getValue());
        // mod start 2017/07/19 CSA QC#19899
        // setValue(svcTask.svcTaskStsCd, SVC_TASK_STS.APPROVED);
        String svcTaskStsCd = SVC_TASK_STS.OPEN;
        if (!ZYPCommonFunc.hasValue(svcTask.soNum)){
            svcTaskStsCd = SVC_TASK_STS.IN_PROCESS;
        } 
        if (fsrVisitTmsg != null && hasValue(fsrVisitTmsg.techCd)) {
            S21_PSNTMsg s21Psn = getS21Psn(pMsg, fsrVisitTmsg.techCd.getValue());
            if (s21Psn == null) {
                msgMap.addXxMsgId(NSZM0602E);
                return false;
            }

            if (PSN_TP.EMPLOYEE.equals(s21Psn.psnTpCd.getValue())) {
                svcTaskStsCd = SVC_TASK_STS.IN_PROCESS;
            } else {
                svcTaskStsCd = SVC_TASK_STS.OPEN;
            }
        }
        if (isOutTrtySvcBr(svcTaskTMsg)) {
            svcTaskStsCd = SVC_TASK_STS.OPEN;
        }
        if (svcTaskStsCd.equals(SVC_TASK_STS.IN_PROCESS)) {
            setValue(svcTask.svcTaskStsCd, svcTaskStsCd);
        }
        // Add end   2019/08/05 CSA QC#52307
        // mod end 2017/07/19 CSA QC#19899
        setValue(svcTask.svcTaskApvlDt, pMsg.svcTaskApvlDt);
        setValue(svcTask.svcTaskApvlTm, pMsg.svcTaskApvlTm);
        setValue(svcTask.svcTaskApvlByUsrId, pMsg.usrId);
        setValue(svcTask.svcCrHldFlg, FLG_OFF_N);

        // mod start 2016/11/18 CSA QC#15954
        String sysTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_TS);
        String sysDtTm = sysTs.substring(0, LEN_DT_TM);
        String sysDt = sysDtTm.substring(0, LEN_DT);
        String sysTm = sysDtTm.substring(LEN_DT, sysDtTm.length());
        String erlStartTs = sysTs;
        // START 2022/04/11 K.Kitachi [QC#59899, ADD]
        String shipToUpdCustCd = null;
        FSRTMsg fsr = getFsr(pMsg);
        if (fsr != null) {
            shipToUpdCustCd = fsr.shipToUpdCustCd.getValue();
        }
        // END 2022/04/11 K.Kitachi [QC#59899, ADD]

        if ((pMsg.svcTaskApvlDt.getValue() + pMsg.svcTaskApvlTm.getValue()).compareTo(sysDt + sysTm) > 0) {

            StringBuilder sb = new StringBuilder();
            sb.append(pMsg.svcTaskApvlDt.getValue());
            sb.append(pMsg.svcTaskApvlTm.getValue());
            sb.append(TS_POSTFIX);
            erlStartTs = sb.toString();
        }
        erlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(
                pMsg.glblCmpyCd.getValue()
              , BigDecimal.ZERO
              , svcTask.svcMachMstrPk.getValue()
              , erlStartTs.substring(0, LEN_DT)
              , erlStartTs.substring(LEN_DT, LEN_DT_TM)
              , false
              // START 2022/04/11 K.Kitachi [QC#59899, ADD]
              , null
              , shipToUpdCustCd
              // END 2022/04/11 K.Kitachi [QC#59899, ADD]
        );
        ZYPEZDItemValueSetter.setValue(svcTask.erlStartTs, erlStartTs);

        String lateStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(
                pMsg.glblCmpyCd.getValue()
              , svcTask.svcRspTmMnAot.getValue()
              , svcTask.svcMachMstrPk.getValue()
              , erlStartTs.substring(0, LEN_DT)
              , erlStartTs.substring(LEN_DT, LEN_DT_TM)
              , false
              // START 2022/04/11 K.Kitachi [QC#59899, ADD]
              , null
              , shipToUpdCustCd
              // END 2022/04/11 K.Kitachi [QC#59899, ADD]
        );
        ZYPEZDItemValueSetter.setValue(svcTask.lateStartTs, lateStartTs);
        // mod end 2016/11/18 CSA QC#15954

        S21ApiTBLAccessor.update(svcTask);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTask.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0167E);
            return false;
        }
        return true;
    }

    private boolean rejSvcTask(S21ApiMessageMap msgMap, SVC_TASKTMsg svcTask) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        setValue(svcTask.svcTaskRejDt, pMsg.svcTaskApvlDt);
        setValue(svcTask.svcTaskRejTm, pMsg.svcTaskApvlTm);
        setValue(svcTask.svcTaskRejByUsrId, pMsg.usrId);

        S21ApiTBLAccessor.update(svcTask);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTask.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0167E);
            return false;
        }
        return true;
    }

    private SVC_TASK_HLDTMsgArray getSvcTaskHld(NSZC038001PMsg pMsg, String svcTaskNum) {

        SVC_TASK_HLDTMsg hldMsg = new SVC_TASK_HLDTMsg();
        hldMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        hldMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        hldMsg.setConditionValue("svcTaskRelFlg01", FLG_OFF_N);
        hldMsg.setSQLID("002");
        hldMsg.setMaxCount(0);

        return (SVC_TASK_HLDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(hldMsg);
    }

    private boolean relSvcTaskHld(S21ApiMessageMap msgMap, SVC_TASK_HLDTMsg svcTaskHld) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        setValue(svcTaskHld.svcTaskRelDt, pMsg.svcTaskApvlDt);
        setValue(svcTaskHld.svcTaskRelUsrId, pMsg.usrId);
        setValue(svcTaskHld.svcTaskRelFlg, FLG_ON_Y);

        S21ApiTBLAccessor.update(svcTaskHld);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskHld.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0203E);
            return false;
        }
        return true;
    }

    private boolean createSvcMemo(S21ApiMessageMap msgMap, String cmntTxt) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        SVC_MEMOTMsg memo = new SVC_MEMOTMsg();
        setValue(memo.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(memo.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
        setValue(memo.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
        setValue(memo.svcMemoTpCd, SVC_MEMO_TP.CREDIT);
        setValue(memo.svcCmntTxt, cmntTxt);
        setValue(memo.fsrNum, pMsg.fsrNum);
        setValue(memo.lastUpdUsrId, pMsg.usrId);

        StringBuilder sb = new StringBuilder();
        sb.append(pMsg.svcTaskApvlDt.getValue());
        sb.append(pMsg.svcTaskApvlTm.getValue());
        setValue(memo.lastUpdTs, sb.toString());

        S21ApiTBLAccessor.insert(memo);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(memo.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0081E);
            return false;
        }
        return true;
    }

    // add start 2015/12/14 CSA Defect#895
    private boolean updateVisitAndCreateEvent(S21ApiMessageMap msgMap, FSR_VISITTMsg fsrVisit) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        if (!SVC_TASK_STS.CREDIT_HOLD.equals(fsrVisit.fsrVisitStsCd.getValue())) {
            return true;
        }

        SVC_TASKTMsg svcTaskTmsg = getSvcTask(pMsg.glblCmpyCd.getValue(), fsrVisit.svcTaskNum.getValue());
        if (svcTaskTmsg != null && ZYPCommonFunc.hasValue(svcTaskTmsg.soNum)) {
            setValue(fsrVisit.fsrVisitStsCd, SVC_TASK_STS.PENDING_INSTALL);
        } else if (hasValue(fsrVisit.techCd)) {
            S21_PSNTMsg s21Psn = getS21Psn(pMsg, fsrVisit.techCd.getValue());
            if (s21Psn == null) {
                msgMap.addXxMsgId(NSZM0602E);
                return false;
            }

            if (PSN_TP.EMPLOYEE.equals(s21Psn.psnTpCd.getValue())) {
                setValue(fsrVisit.fsrVisitStsCd, SVC_TASK_STS.TBO);
            } else {
                // mod start 2016/02/26 CSA Defect#4113
                //setValue(fsrVisit.fsrVisitStsCd, SVC_TASK_STS.OPEN);
                setValue(fsrVisit.fsrVisitStsCd, SVC_TASK_STS.NOTIFY_VENDOR);
                // mod start 2016/02/26 CSA Defect#4113
            }
        } else {
            setValue(fsrVisit.fsrVisitStsCd, SVC_TASK_STS.TBO);
        }

        // Add Start 2017/09/08 QC#19242
        if (isOutTrtySvcBr(svcTaskTmsg)) {
            setValue(fsrVisit.fsrVisitStsCd, SVC_TASK_STS.NOTIFY_VENDOR);
        }
        // Add End 2017/09/08 QC#19242

        // START 2019/02/15 K.Kitachi [QC#30165, ADD]
        if (svcTaskTmsg != null && isClickSendExclCall(svcTaskTmsg.dsSvcCallTpCd.getValue())) {
            setValue(fsrVisit.fsrVisitStsCd, SVC_TASK_STS.NOTIFY_VENDOR);
        }
        // END 2019/02/15 K.Kitachi [QC#30165, ADD]

        S21ApiTBLAccessor.update(fsrVisit);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(fsrVisit.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0169E);
            return false;
        }

        if (!createFsrEvent(msgMap, fsrVisit, SVC_DISPT_EVENT.CREDIT_APPROVAL)) {
            return false;
        }
        if (SVC_TASK_STS.TBO.equals(fsrVisit.fsrVisitStsCd.getValue())) {
            if (!createFsrEvent(msgMap, fsrVisit, SVC_DISPT_EVENT.TBO)) {
                return false;
            }
        } else if (SVC_TASK_STS.PENDING_INSTALL.equals(fsrVisit.fsrVisitStsCd.getValue())) {
            if (!createFsrEvent(msgMap, fsrVisit, SVC_DISPT_EVENT.PENDING_INSTALL)) {
                return false;
            }
        }
        return true;
    }
    private boolean createFsrEvent(S21ApiMessageMap msgMap, FSR_VISITTMsg fsrVisit, String svcDisptEventCd) {

        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();

        FSR_EVENTTMsg fsrEvent = new FSR_EVENTTMsg();
        setValue(fsrEvent.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrEvent.fsrEventPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.FSR_EVENT_SQ));
        setValue(fsrEvent.svcDisptEventCd, svcDisptEventCd);
        setValue(fsrEvent.techCd, fsrVisit.techCd);
        setValue(fsrEvent.svcTaskNum, fsrVisit.svcTaskNum);
        setValue(fsrEvent.fsrNum, fsrVisit.fsrNum);
        setValue(fsrEvent.fsrVisitNum, fsrVisit.fsrVisitNum);
        setValue(fsrEvent.fsrEventExeUsrId, pMsg.usrId);

        if (SVC_TASK_STS.TBO.equals(fsrVisit.fsrVisitStsCd.getValue())) {
            setValue(fsrEvent.mblIntfcProcCd, MBL_INTFC_PROC.NOT_PROCESSED);
            // mod start 2016/02/26 CSA Defect#4645
            //setValue(fsrEvent.mblIntfcFlg, FLG_ON_Y);
            setValue(fsrEvent.mblIntfcFlg, getMblIntfcFlg(pMsg.glblCmpyCd.getValue(), SVC_DISPT_EVENT.CREDIT_APPROVAL));
            // mod end 2016/02/26 CSA Defect#4645
        } else {
            setValue(fsrEvent.mblIntfcProcCd, MBL_INTFC_PROC.NO_NEED);
            setValue(fsrEvent.mblIntfcFlg, FLG_OFF_N);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pMsg.svcTaskApvlDt.getValue());
        sb.append(pMsg.svcTaskApvlTm.getValue());
        setValue(fsrEvent.fsrEventExeTs, sb.toString());

        S21ApiTBLAccessor.insert(fsrEvent);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(fsrEvent.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0300E);
            return false;
        }
        return true;
    }
    // add end 2015/12/14 CSA Defect#895

    // add start 2016/06/16 QC#9677
// del start 2019/05/23 QC#50499
//    private void callSendTaskInfoAPI(S21ApiMessageMap msgMap, FSR_VISITTMsgArray fsrVisits, ONBATCH_TYPE onBatchType) {
//
//        NSZC038001PMsg pMsg = (NSZC038001PMsg) msgMap.getPmsg();
//
//        List<NSZC107001PMsg> apiPMsgList = new ArrayList<NSZC107001PMsg>();
//
//        for (int i = 0; i < fsrVisits.getValidCount(); i++) {
//            String svcTaskNum = fsrVisits.no(i).svcTaskNum.getValue();
//            if (!hasValue(svcTaskNum)) {
//                continue;
//            }
//            NSZC107001PMsg apiPMsg = new NSZC107001PMsg();
//            setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//            setValue(apiPMsg.slsDt, pMsg.slsDt);
//            setValue(apiPMsg.svcTaskNum, svcTaskNum);
//            apiPMsgList.add(apiPMsg);
//        }
//
//        // Call Send Task Info to Click Software
//        NSZC107001 api = new NSZC107001();
//        api.execute(apiPMsgList, onBatchType);
//
//        for (NSZC107001PMsg apiPMsg : apiPMsgList) {
//            if (S21ApiUtil.isXxMsgId(apiPMsg)) {
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
//                for (S21ApiMessage msg : msgList) {
//                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
//                }
//            }
//        }
//    }
// del end 2019/05/23 QC#50499
    // add end 2016/06/16 QC#9677

    private String getManager(String usrId) {
        S21UserInfo userInfo = S21UserProfileServiceFactory.getInstance().getService().getUserInfoFor(usrId);
        return userInfo.getUserDetails().getManagerId();
    }

    // add start 2016/02/26 CSA Defect#4645
    private String getMblIntfcFlg(String glblCmpyCd, String svcDisptEventCd) {
        SVC_DISPT_EVENTTMsg tMsg = new SVC_DISPT_EVENTTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcDisptEventCd, svcDisptEventCd);
        SVC_DISPT_EVENTTMsg outTMsg = (SVC_DISPT_EVENTTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (outTMsg == null) {
            return FLG_ON_Y;
        }
        return outTMsg.mblIntfcFlg.getValue();
    }
    // add end 2016/02/26 CSA Defect#4645

    // add start 2016/03/23 CSA Defect#895
    private boolean chkNtfy(NSZC038001PMsg pMsg) {
        int acctLvl = chkAcctLvl(pMsg);
        int billToLvl = chkBillToLvl(pMsg);
        if (((PROC_OK == acctLvl) && (PROC_OK == billToLvl)) || ((PROC_OK == acctLvl) && (PROC_NONE == billToLvl)) || ((PROC_NONE == acctLvl) && (PROC_OK == billToLvl))) {
            // Not Notification
            return false;
        }
        // Notification
        return true;
    }

    // mod start 2016/04/01 CSA Defect#6407
    private int chkAcctLvl(NSZC038001PMsg pMsg) {
        Map<String, Object> arTrxBal = getArTrxBalForAcct(pMsg);
        Map<String, Object> arCrRfApvlLimit = getArCrRfApvlLimitForAcct(pMsg);

        return checkNotificationNecessity(pMsg, arTrxBal, arCrRfApvlLimit);
    }

    private int chkBillToLvl(NSZC038001PMsg pMsg) {
        FSRTMsg fsr = getFsr(pMsg);
        Map<String, Object> arTrxBal = getArTrxBalForBillToCust(fsr);
        Map<String, Object> arCrRfApvlLimit = getArCrRfApvlLimitForBillToCust(fsr, pMsg.usrId.getValue());

        return checkNotificationNecessity(pMsg, arTrxBal, arCrRfApvlLimit);
    }

    private int checkNotificationNecessity(NSZC038001PMsg pMsg, Map<String, Object> arTrxBal, Map<String, Object> arCrRfApvlLimit) {
        if (arTrxBal == null || arCrRfApvlLimit == null) {
            return PROC_NONE;
        }

        BigDecimal dealRmngBalGrsAmt = (BigDecimal) arTrxBal.get("DEAL_RMNG_BAL_GRS_AMT");
        BigDecimal crLimitAmt = (BigDecimal) arCrRfApvlLimit.get("CR_LIMIT_AMT");
        if (!hasValue(dealRmngBalGrsAmt) || !hasValue(crLimitAmt)) {
            return PROC_NONE;
        }
        if (dealRmngBalGrsAmt.compareTo(crLimitAmt) > 0) {
            return PROC_NG;
        }

        try {
            String invDueDt = (String) arTrxBal.get("INV_DUE_DT");
            BigDecimal gracePreDaysAot = (BigDecimal) arCrRfApvlLimit.get("GRACE_PER_DAYS_AOT");
            if (!hasValue(invDueDt) || !hasValue(gracePreDaysAot)) {
                return PROC_NONE;
            }
            int intGracePreDaysAot = gracePreDaysAot.intValue();
            int numOfDay = diffDays(pMsg.slsDt.getValue(), invDueDt);
            if (intGracePreDaysAot < numOfDay) {
                return PROC_NG;
            }
        } catch (ParseException e) {
            return PROC_NONE;
        }
        return PROC_OK;
    }
    // mod end 2016/04/01 CSA Defect#6407

    private Map<String, Object> getArTrxBalForAcct(NSZC038001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("fsrNum", pMsg.fsrNum.getValue());
        param.put("arCashApplyStsCd", new String[] {AR_CASH_APPLY_STS.PARTIAL, AR_CASH_APPLY_STS.UNAPPLIED });
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getArTrxBalForAcct", param);
    }

    private Map<String, Object> getArCrRfApvlLimitForAcct(NSZC038001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("fsrNum", pMsg.fsrNum.getValue());
        param.put("cltCrAnlstSvcPsnCd", pMsg.usrId.getValue());
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getArCrRfApvlLimitForAcct", param);
    }

    private Map<String, Object> getArTrxBalForBillToCust(FSRTMsg fsrTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", fsrTMsg.glblCmpyCd.getValue());
        if (FLG_ON_Y.equals(fsrTMsg.billToCustUpdFlg.getValue())) {
            param.put("billToCustCd", fsrTMsg.billToUpdCustCd.getValue());
        } else {
            param.put("billToCustCd", fsrTMsg.billToCustCd.getValue());
        }
        param.put("arCashApplyStsCd", new String[] {AR_CASH_APPLY_STS.PARTIAL, AR_CASH_APPLY_STS.UNAPPLIED });
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getArTrxBalForBillToCust", param);
    }

    private Map<String, Object> getArCrRfApvlLimitForBillToCust(FSRTMsg fsrTMsg, String usrId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", fsrTMsg.glblCmpyCd.getValue());
        if (FLG_ON_Y.equals(fsrTMsg.billToCustUpdFlg.getValue())) {
            param.put("billToCustCd", fsrTMsg.billToUpdCustCd.getValue());
        } else {
            param.put("billToCustCd", fsrTMsg.billToCustCd.getValue());
        }
        param.put("cltCrAnlstSvcPsnCd", usrId);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getArCrRfApvlLimitForBillToCust", param);
    }

    private int diffDays(String strDate1, String strDate2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date1 = sdf.parse(strDate1);
        Date date2 = sdf.parse(strDate2);
        return differenceDays(date1, date2);
    }

    private int differenceDays(Date date1, Date date2) {
        long datetime1 = date1.getTime();
        long datetime2 = date2.getTime();
        long one_date_time = 1000 * 60 * 60 * 24;
        long diffDays = (datetime1 - datetime2) / one_date_time;
        return (int) diffDays;
    }
    // add end 2016/03/23 CSA Defect#895

    // add start 2016/08/01 CSA Defect#4324
    private boolean isCreditHardHoldCustomer(NSZC038001PMsg pMsg) {
        FSRTMsg fsr = getFsr(pMsg);

        // location level
        String billToCustCd = null;
        if (hasValue(fsr.billToUpdCustCd)) {
            billToCustCd = fsr.billToUpdCustCd.getValue();
        } else {
            billToCustCd = fsr.billToCustCd.getValue();
        }
        String billToCustHardHoldFlg = geBillTotCustHardHoldFlg(pMsg.glblCmpyCd.getValue(), billToCustCd);
        if (FLG_ON_Y.equals(billToCustHardHoldFlg)) {
            return true;
        }

        // account level
        DS_ACCT_CR_PRFLTMsg dsAcctCrPrfl = getDsAcctCrPrfl(pMsg.glblCmpyCd.getValue(), fsr.billToCustAcctCd.getValue());
        if (dsAcctCrPrfl != null) {
            if (FLG_ON_Y.equals(dsAcctCrPrfl.custHardHldFlg.getValue())) {
                return true;
            }
        }

        return false;
    }

    private String geBillTotCustHardHoldFlg(String glblCmpyCd, String billToCustCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("billToCustCd", billToCustCd);
        return (String) this.ssmBatchClient.queryObject("geBillTotCustHardHoldFlg", param);
    }

    private DS_ACCT_CR_PRFLTMsg getDsAcctCrPrfl(String glblCmpyCd, String billToAcctNum) {
        DS_ACCT_CR_PRFLTMsg tMsg = new DS_ACCT_CR_PRFLTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsAcctNum, billToAcctNum);
        return (DS_ACCT_CR_PRFLTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }
    // add end 2016/08/01 CSA Defect#4324
    // Add Start 2017/09/08 QC#19242
    private boolean isOutTrtySvcBr(SVC_TASKTMsg svcTaskTMsg) {
        // Mod Start 2017/09/27 QC#19242
        if (svcTaskTMsg == null || !ZYPCommonFunc.hasValue(this.outTrtySvcBrCd)) {
            return false;
        }

        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, svcTaskTMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.svcMachMstrPk, svcTaskTMsg.svcMachMstrPk);
        SVC_MACH_MSTRTMsg machMstrTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (machMstrTMsg == null || !ZYPCommonFunc.hasValue(machMstrTMsg.fldSvcBrCd)) {
            return false;
        }

        if (this.outTrtySvcBrCd.equals(machMstrTMsg.fldSvcBrCd.getValue())) {
            return true;
        }
        return false;
        // Mod End 2017/09/27 QC#19242
    }
    // Add End 2017/09/08 QC#19242

    // add start 2018/05/08 CSA QC#24879
    private NSZC043001PMsg createNSZC043001PMsgCancelFsr(NSZC038001PMsg pMsg, SVC_TASKTMsgArray svcTaskArray) {
        NSZC043001PMsg fsrUpdateApiPMsg = new NSZC043001PMsg();

        setValue(fsrUpdateApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrUpdateApiPMsg.xxModeCd, NSZC043001Constant.MODE_CANCEL_FSR);
        setValue(fsrUpdateApiPMsg.slsDt, pMsg.slsDt);
        setValue(fsrUpdateApiPMsg.userId, pMsg.usrId);
        setValue(fsrUpdateApiPMsg.fsrNum, pMsg.fsrNum);
        setValue(fsrUpdateApiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);

        int cnt = svcTaskArray.getValidCount();
        for (int i = 0; i < cnt; i++) {
            SVC_TASKTMsg svcTask = svcTaskArray.no(i);
            setValue(fsrUpdateApiPMsg.taskList.no(0).svcTaskNum, svcTask.svcTaskNum);
            setValue(fsrUpdateApiPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_OFF_N);
            setValue(fsrUpdateApiPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
        }
        fsrUpdateApiPMsg.taskList.setValidCount(cnt);

        return fsrUpdateApiPMsg;
    }

    private boolean callApiNSZC043001(S21ApiMessageMap msgMap, NSZC043001PMsg pMsg) {

        NSZC043001 api = new NSZC043001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return false;
        }
        return true;
    }
    // add end 2018/05/08 CSA QC#24879

    // START 2019/02/15 K.Kitachi [QC#30165, ADD]
    private boolean isClickSendExclCall(String dsSvcCallTpCd) {
        for (int i = 0; i < this.clickSendExclCallTpList.length; i++) {
            if (this.clickSendExclCallTpList[i].equals(dsSvcCallTpCd)) {
                return true;
            }
        }
        return false;
    }
    // END 2019/02/15 K.Kitachi [QC#30165, ADD]

    // Add start 2019/08/05 CSA QC#52307
    private FSR_VISITTMsg getFsrVisit(String glblCmpyCd, String svcTaskNum) {
        FSR_VISITTMsg inMsg = new FSR_VISITTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        inMsg.setSQLID("002");
        FSR_VISITTMsgArray tMsgArray = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        return tMsgArray.no(0);
    }
    // Add end   2019/08/05 CSA QC#52307
}
