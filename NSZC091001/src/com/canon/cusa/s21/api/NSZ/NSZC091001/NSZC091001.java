/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC091001;

import static com.canon.cusa.s21.api.NSZ.NSZC091001.constant.NSZC091001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDPItem;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.S21_PSNTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC045001PMsg;
import business.parts.NSZC045001_xxSvcMemoListPMsg;
import business.parts.NSZC045001_xxSvcTaskListPMsg;
import business.parts.NSZC091001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.ZYP.message.ZYPEZDMessageInfoUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 * <pre>
 * Create Service Call API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   Hitachi         T.Aoyagi        Create          N/A
 * 2016/05/12   Hitachi         M.Gotou         Update          QC#7828
 * 2016/09/14   Hitachi         T.Tomita        Update          QC#14401
 * 2017/10/10   CITS            M.Naito         Update          QC#21428
 * 2017/11/06   Hitachi         M.Kidokoro      Update          QC#21991
 * 2019/12/04   Hitachi         Y.Takeno        Update          QC#54923
 * </pre>
 */
public class NSZC091001 extends S21ApiCommonBase {

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    // START 2016/09/14 T.Tomita [QC#14401, ADD]
    /** Call Mode */
    private String callMode;
    // END 2016/09/14 T.Tomita [QC#14401, ADD]

    /**
     * Constructor
     */
    public NSZC091001() {
        super();
    }

    /**
     * execute
     * @param param NSZC091001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC091001PMsg param, final ONBATCH_TYPE onBatchTp) {
        this.onBatchType = onBatchTp;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParameter(msgMap)) {
            doProcess(msgMap, param);
        }

        msgMap.flush();

        // add start 2016/05/12 CSA Defect#7828
        setMessageTxt((NSZC091001PMsg) msgMap.getPmsg());
        // add end 2016/05/12 CSA Defect#7828
        
    }

    // START 2016/09/14 T.Tomita [QC#14401, MOD]
    private boolean checkParameter(S21ApiMessageMap msgMap) {
        if (!commonMandatoryCheck(msgMap)) {
            return false;
        }
        setCallMode(msgMap);
        if (!callModeCheck(msgMap)) {
            return false;
        }
        if (!mandatoryCheck(msgMap)) {
            return false;
        }
        if (!commonMasterCheck(msgMap)) {
            return false;
        }
        if (!masterCheck(msgMap)) {
            return false;
        }
        return true;
    }

    private boolean commonMandatoryCheck(S21ApiMessageMap msgMap) {
        NSZC091001PMsg pMsg = (NSZC091001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            setValue(pMsg.glblCmpyCd, DEF_GLBL_CMPY_CD);
        }
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        mandatoryCheck(msgMap, pMsg.xxSvcActTxt, NSZM0944E);
        mandatoryCheck(msgMap, pMsg.svcTaskNum, NSZM0945E);
        mandatoryCheck(msgMap, pMsg.svcConfigMstrPk, NSZM0946E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void setCallMode(S21ApiMessageMap msgMap) {
        NSZC091001PMsg pMsg = (NSZC091001PMsg) msgMap.getPmsg();
        this.callMode = ZYPCodeDataUtil.getVarCharConstValue(pMsg.xxSvcActTxt.getValue(), pMsg.glblCmpyCd.getValue());
    }

    private boolean callModeCheck(S21ApiMessageMap msgMap) {
        if (!NSZC043001.MODE_CREATE_FSR.equals(this.callMode) && !NSZC043001.MODE_UPDATE_FSR.equals(this.callMode) && !NSZC043001.MODE_CANCEL_FSR.equals(this.callMode)) {
            msgMap.addXxMsgId(NSZM0039E);
            return false;
        }
        return true;
    }

    private boolean mandatoryCheck(S21ApiMessageMap msgMap) {
        if (NSZC043001.MODE_CANCEL_FSR.equals(this.callMode)) {
            return true;
        }

        NSZC091001PMsg pMsg = (NSZC091001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.ctacPsnFirstNm, NSZM0947E);
        mandatoryCheck(msgMap, pMsg.ctacPsnLastNm, NSZM0948E);
        mandatoryCheck(msgMap, pMsg.ctacPsnEmlAddr, NSZM0949E);
        mandatoryCheck(msgMap, pMsg.ctacPsnCellPhoNum, NSZM0950E);
        mandatoryCheck(msgMap, pMsg.svcCustAttnTxt, NSZM0951E);
        mandatoryCheck(msgMap, pMsg.dsSvcCallTpCd, NSZM0952E);
        mandatoryCheck(msgMap, pMsg.svcTaskRcvDt, NSZM0953E);

        // START 2017/11/06 M.Kidokoro [QC#21991, ADD]
        if (NSZC043001.MODE_CREATE_FSR.equals(this.callMode)) {
            mandatoryCheck(msgMap, pMsg.svcPblmTpCd, NSZM1313E);
        }
        // END 2017/11/06 M.Kidokoro [QC#21991, ADD]

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private boolean commonMasterCheck(S21ApiMessageMap msgMap) {
        NSZC091001PMsg pMsg = (NSZC091001PMsg) msgMap.getPmsg();

        if (!existSvcTask(pMsg)) {
            msgMap.addXxMsgId(NSZM0954E);
        }
        if (!existSvcConfigMstr(pMsg)) {
            msgMap.addXxMsgId(NSZM0955E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private boolean masterCheck(S21ApiMessageMap msgMap) {
        if (NSZC043001.MODE_CANCEL_FSR.equals(this.callMode)) {
            return true;
        }

        NSZC091001PMsg pMsg = (NSZC091001PMsg) msgMap.getPmsg();

        if (!existDsSvcCallTp(pMsg)) {
            msgMap.addXxMsgId(NSZM0956E);
        }
        if (!existS21Psn(pMsg)) {
            msgMap.addXxMsgId(NSZM0957E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private void doProcess(S21ApiMessageMap msgMap, NSZC091001PMsg pMsg) {
        if (NSZC043001.MODE_CREATE_FSR.equals(this.callMode)) {
            // Call FSR Update API / 01(create)
            callFsrUpdateApiForCreateMode(msgMap, pMsg);
        } else if (NSZC043001.MODE_UPDATE_FSR.equals(this.callMode)) {
            // Call FSR Update API / 02(update)
            callFsrUpdateApiForUpdateMode(msgMap, pMsg);
        } else if (NSZC043001.MODE_CANCEL_FSR.equals(this.callMode)) {
            if (!existOtherTask(pMsg)) {
                // Call FSR Update API / 03(cancel)
                callFsrUpdateApiForCancelMode(msgMap, pMsg);
            } else {
                // Call Add Task API / 02(cancel)
                callAddTaskApi(msgMap, pMsg);
            }
        }
    }
    // END 2016/09/14 T.Tomita [QC#14401, MOD]

    private void callFsrUpdateApiForCreateMode(S21ApiMessageMap msgMap, NSZC091001PMsg pMsg) {
        NSZC043001 fsrUpdateApi = new NSZC043001();
        NSZC043001PMsg callFsrUpdateApiParam = createFsrUpdateApiForCreateModeParam(pMsg);
        fsrUpdateApi.execute(callFsrUpdateApiParam, this.onBatchType);
        setOutputParamForFsrUpdateApi(msgMap, pMsg, callFsrUpdateApiParam);
    }

    private void callFsrUpdateApiForUpdateMode(S21ApiMessageMap msgMap, NSZC091001PMsg pMsg) {
        NSZC043001 fsrUpdateApi = new NSZC043001();
        NSZC043001PMsg callFsrUpdateApiParam = createFsrUpdateApiForUpdateModeParam(pMsg);
        fsrUpdateApi.execute(callFsrUpdateApiParam, this.onBatchType);
        setOutputParamForFsrUpdateApi(msgMap, pMsg, callFsrUpdateApiParam);
    }

    private void callFsrUpdateApiForCancelMode(S21ApiMessageMap msgMap, NSZC091001PMsg pMsg) {
        NSZC043001 fsrUpdateApi = new NSZC043001();
        NSZC043001PMsg callFsrUpdateApiParam = createFsrUpdateApiForCancelModeParam(pMsg);
        fsrUpdateApi.execute(callFsrUpdateApiParam, this.onBatchType);
        setOutputParamForFsrUpdateApi(msgMap, pMsg, callFsrUpdateApiParam);
    }

    private void callAddTaskApi(S21ApiMessageMap msgMap, NSZC091001PMsg pMsg) {
        NSZC045001 callAddTaskApi = new NSZC045001();
        NSZC045001PMsg callAddTaskApiParam = createAddTaskApiCancelParam(pMsg);
        callAddTaskApi.execute(callAddTaskApiParam, this.onBatchType);
        if (callAddTaskApiParam.xxMsgIdList.getValidCount() == 0) {
            setValue(pMsg.rtrnTrxTxt, STR_ZERO);
        } else {
            setValue(pMsg.rtrnTrxTxt, STR_ONE);
        }
        if (callAddTaskApiParam.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < callAddTaskApiParam.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(callAddTaskApiParam.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
    }

    private NSZC043001PMsg createFsrUpdateApiForCreateModeParam(NSZC091001PMsg pMsg) {
        NSZC043001PMsg nszc043001PMsg = new NSZC043001PMsg();
        setValue(nszc043001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(nszc043001PMsg.slsDt, pMsg.slsDt);
        setValue(nszc043001PMsg.xxModeCd, NSZC043001.MODE_CREATE_FSR);
        if (hasValue(pMsg.techCd)) {
            setValue(nszc043001PMsg.userId, pMsg.techCd);
        } else {
            setValue(nszc043001PMsg.userId, this.getClass().getSimpleName());
        }
        BigDecimal svcMachMstrPk = getSvcMachMstrPk(pMsg);
        setValue(nszc043001PMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(nszc043001PMsg.custTelNum, pMsg.ctacPsnCellPhoNum);
        // START 2019/12/04 [QC#54923, MOD]
        // String svcCustAttnTxt = pMsg.ctacPsnFirstNm.getValue().concat(STR_SPACE).concat(pMsg.ctacPsnLastNm.getValue()).concat(STR_COMMA).concat(pMsg.svcCustAttnTxt.getValue());
        String svcCustAttnTxt = pMsg.svcCustAttnTxt.getValue();
        // END   2019/12/04 [QC#54923, MOD]
        if (svcCustAttnTxt.length() > LENGTH_60) {
            svcCustAttnTxt = svcCustAttnTxt.substring(0, LENGTH_60);
        }
        setValue(nszc043001PMsg.svcCustAttnTxt, svcCustAttnTxt);
        setValue(nszc043001PMsg.custEmlAddr, pMsg.ctacPsnEmlAddr);
        setValue(nszc043001PMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        setValue(nszc043001PMsg.svcTaskRcvDt, pMsg.svcTaskRcvDt);
        // START 2017/10/10 M.Naito [QC#21428, MOD]
//        setValue(nszc043001PMsg.svcTaskRcvTm, SVC_TASK_RCV_TM_VAL);
        setValue(nszc043001PMsg.svcTaskRcvTm, ZYPDateUtil.getCurrentSystemTime(TM_FORMAT_HHMMSS));
        // END 2017/10/10 M.Naito [QC#21428, MOD]
        setValue(nszc043001PMsg.firstSvcTaskFlg, ZYPConstant.FLG_ON_Y);
        // START 2017/11/06 M.Kidokoro [QC#21991, MOD]
//        String svcPblmTpCd = ZYPCodeDataUtil.getVarCharConstValue(OOS_SVC_PBLM_TP_CD, pMsg.glblCmpyCd.getValue());
//        setValue(nszc043001PMsg.svcPblmTpCd, svcPblmTpCd);
        setValue(nszc043001PMsg.svcPblmTpCd, pMsg.svcPblmTpCd);
        // END 2017/11/06 M.Kidokoro [QC#21991, MOD]
        setValue(nszc043001PMsg.svcCallSrcTpCd, SVC_CALL_SRC_TP.OOS);
        setValue(nszc043001PMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        nszc043001PMsg.taskList.setValidCount(1);
        String svcMemoTpCd = ZYPCodeDataUtil.getVarCharConstValue(OOS_SVC_MEMO_TP_CD, pMsg.glblCmpyCd.getValue());
        setValue(nszc043001PMsg.svcMemoList.no(0).svcMemoTpCd, svcMemoTpCd);
        setValue(nszc043001PMsg.svcMemoList.no(0).svcCmntTxt, pMsg.svcCmntTxt);
        nszc043001PMsg.svcMemoList.setValidCount(1);
        return nszc043001PMsg;
    }

    private NSZC043001PMsg createFsrUpdateApiForUpdateModeParam(NSZC091001PMsg pMsg) {
        NSZC043001PMsg nszc043001PMsg = new NSZC043001PMsg();
        setValue(nszc043001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(nszc043001PMsg.slsDt, pMsg.slsDt);
        setValue(nszc043001PMsg.xxModeCd, NSZC043001.MODE_UPDATE_FSR);
        String fsrNum = getFsrNum(pMsg);
        setValue(nszc043001PMsg.fsrNum, fsrNum);
        if (hasValue(pMsg.techCd)) {
            setValue(nszc043001PMsg.userId, pMsg.techCd);
        } else {
            setValue(nszc043001PMsg.userId, this.getClass().getSimpleName());
        }
        BigDecimal svcMachMstrPk = getSvcMachMstrPk(pMsg);
        setValue(nszc043001PMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(nszc043001PMsg.taskList.no(0).svcTaskNum, pMsg.svcTaskNum);
        setValue(nszc043001PMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        nszc043001PMsg.taskList.setValidCount(1);
        setValue(nszc043001PMsg.svcMemoList.no(0).svcCmntTxt, pMsg.svcCmntTxt);
        nszc043001PMsg.svcMemoList.setValidCount(1);
        return nszc043001PMsg;
    }

    private NSZC043001PMsg createFsrUpdateApiForCancelModeParam(NSZC091001PMsg pMsg) {
        NSZC043001PMsg nszc043001PMsg = new NSZC043001PMsg();
        setValue(nszc043001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(nszc043001PMsg.slsDt, pMsg.slsDt);
        setValue(nszc043001PMsg.xxModeCd, NSZC043001.MODE_CANCEL_FSR);
        String fsrNum = getFsrNum(pMsg);
        setValue(nszc043001PMsg.fsrNum, fsrNum);
        if (hasValue(pMsg.techCd)) {
            setValue(nszc043001PMsg.userId, pMsg.techCd);
        } else {
            setValue(nszc043001PMsg.userId, this.getClass().getSimpleName());
        }
        setValue(nszc043001PMsg.taskList.no(0).svcTaskNum, pMsg.svcTaskNum);
        setValue(nszc043001PMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        setValue(nszc043001PMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        nszc043001PMsg.taskList.setValidCount(1);
        return nszc043001PMsg;
    }

    private NSZC045001PMsg createAddTaskApiCancelParam(NSZC091001PMsg pMsg) {
        NSZC045001PMsg addTaskApiPMsg = new NSZC045001PMsg();

        setValue(addTaskApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(addTaskApiPMsg.xxModeCd, NSZC045001.PROCESS_MODE_CANCEL_TASK);
        setValue(addTaskApiPMsg.fsrNum, getFsrNum(pMsg));
        if (hasValue(pMsg.techCd)) {
            setValue(addTaskApiPMsg.userId, pMsg.techCd);
        } else {
            setValue(addTaskApiPMsg.userId, this.getClass().getSimpleName());
        }

        NSZC045001_xxSvcTaskListPMsg taskListPMsg = addTaskApiPMsg.xxSvcTaskList.no(0);
        setValue(taskListPMsg.svcTaskNum, pMsg.svcTaskNum);
        setValue(taskListPMsg.schdDisptEmlFlg, ZYPConstant.FLG_ON_Y);
        addTaskApiPMsg.xxSvcTaskList.setValidCount(1);

        NSZC045001_xxSvcMemoListPMsg memoListPMsg = addTaskApiPMsg.xxSvcMemoList.no(0);
        String svcMemoTpCd = ZYPCodeDataUtil.getVarCharConstValue(OOS_SVC_MEMO_TP_CD, pMsg.glblCmpyCd.getValue());
        setValue(memoListPMsg.svcMemoTpCd, svcMemoTpCd);
        setValue(memoListPMsg.svcCmntTxt, pMsg.svcCmntTxt);
        addTaskApiPMsg.xxSvcMemoList.setValidCount(1);
        return addTaskApiPMsg;
    }

    private void setOutputParamForFsrUpdateApi(S21ApiMessageMap msgMap, NSZC091001PMsg pMsg, NSZC043001PMsg nszc043001PMsg) {
        setValue(pMsg.svcTaskNum, nszc043001PMsg.taskList.no(0).svcTaskNum);
        if (nszc043001PMsg.xxMsgIdList.getValidCount() == 0) {
            setValue(pMsg.rtrnTrxTxt, STR_ZERO);
        } else {
            setValue(pMsg.rtrnTrxTxt, STR_ONE);
        }
        for (int i = 0; i < nszc043001PMsg.xxMsgIdList.getValidCount(); i++) {
            msgMap.addXxMsgId(nszc043001PMsg.xxMsgIdList.no(i).xxMsgId.getValue());
        }
    }

    private boolean existSvcTask(NSZC091001PMsg pMsg) {
        if (STR_ZERO.equals(pMsg.svcTaskNum.getValue())) {
            return true;
        }
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.svcTaskNum, pMsg.svcTaskNum);
        tMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private boolean existSvcConfigMstr(NSZC091001PMsg pMsg) {
        if (BigDecimal.ZERO.compareTo(pMsg.svcConfigMstrPk.getValue()) == 0) {
            return true;
        }
        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        tMsg = (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private boolean existDsSvcCallTp(NSZC091001PMsg pMsg) {
        if (STR_ZERO.equals(pMsg.dsSvcCallTpCd.getValue())) {
            return true;
        }
        DS_SVC_CALL_TPTMsg tMsg = new DS_SVC_CALL_TPTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        tMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private boolean existS21Psn(NSZC091001PMsg pMsg) {
        if (!hasValue(pMsg.techCd)) {
            return true;
        }
        S21_PSNTMsg tMsg = new S21_PSNTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.psnCd, pMsg.techCd);
        tMsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    private boolean existOtherTask(NSZC091001PMsg pMsg) {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("fsrNum01", getFsrNum(pMsg));
        Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);
        if (resCnt > 1) {
            return true;
        }
        return false;
    }

    private BigDecimal getSvcMachMstrPk(NSZC091001PMsg pMsg) {
        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.svcConfigMstrPk, pMsg.svcConfigMstrPk);
        tMsg = (SVC_CONFIG_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.svcMachMstrPk.getValue();
    }

    private String getFsrNum(NSZC091001PMsg pMsg) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(tMsg.svcTaskNum, pMsg.svcTaskNum);
        tMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.fsrNum.getValue();
    }

// add start 2016/05/12 CSA Defect#7828
    /**
     * set Message Text
     * @param pMsg NSZC091001PMsg
     */
    private void setMessageTxt(NSZC091001PMsg pMsg) {
        ZYPEZDMessageInfoUtil util = new ZYPEZDMessageInfoUtil();
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(i).xxMsgTxt, util.getI18nMessage(null, pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), null));
        }
    }
// add end 2016/05/12 CSA Defect#7828
}
