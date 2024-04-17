/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC108001;

import static com.canon.cusa.s21.api.NSZ.NSZC108001.constant.NSZC108001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CITS_DBRF_STAGETMsg;
import business.db.CITS_SVC_TASK_UPD_STAGETMsg;
import business.parts.NSZC108001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 * Upadte Task from People Soft.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/15   Hitachi         T.Mizuki        Create          N/A
 * </pre>
 */
public class NSZC108001 extends S21ApiCommonBase {

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /**
     * Constructor
     */
    public NSZC108001() {
        super();
    }

    /**
     * execute
     * @param pMsgList List<NSZC108001PMsg>
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(List<NSZC108001PMsg> pMsgList, final ONBATCH_TYPE onBatchTp) {
        for (NSZC108001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatchTp);
        }
    }

    /**
     * execute
     * @param param NSZC108001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC108001PMsg param, final ONBATCH_TYPE onBatchTp) {
        this.onBatchType = onBatchTp;

        if (!hasValue(param.glblCmpyCd)) {
            ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, "ADB");
        }
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (checkParameter(msgMap)) {
            doProcess(msgMap, param);
        }

        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC108001PMsg pMsg = (NSZC108001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.citsSvcTaskUpdStagePk, NSZM1029E);
        mandatoryCheck(msgMap, pMsg.plsftProcCd, NSZM1031E);

        for (int i = 0; i < pMsg.DebriefList.getValidCount(); i++) {
            mandatoryCheck(msgMap, pMsg.DebriefList.no(i).citsDbrfStagePk, NSZM1030E);
            mandatoryCheck(msgMap, pMsg.DebriefList.no(i).plsftProcCd_A, NSZM1032E);
            if (hasValue(pMsg.DebriefList.no(i).citsDbrfStagePk)) {
                CITS_DBRF_STAGETMsg in2Tmsg = new CITS_DBRF_STAGETMsg();
                ZYPEZDItemValueSetter.setValue(in2Tmsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(in2Tmsg.citsDbrfStagePk, pMsg.DebriefList.no(i).citsDbrfStagePk);
                CITS_DBRF_STAGETMsg cdsTMsg = (CITS_DBRF_STAGETMsg) S21ApiTBLAccessor.findByKey(in2Tmsg);
                if (cdsTMsg == null) {
                    msgMap.addXxMsgId(NSZM1034E);
                }
            }
        }
        if (hasValue(pMsg.citsSvcTaskUpdStagePk)) {
            CITS_SVC_TASK_UPD_STAGETMsg inTmsg = new CITS_SVC_TASK_UPD_STAGETMsg();
            ZYPEZDItemValueSetter.setValue(inTmsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTmsg.citsSvcTaskUpdStagePk, pMsg.citsSvcTaskUpdStagePk);
            CITS_SVC_TASK_UPD_STAGETMsg cstusTMsg = (CITS_SVC_TASK_UPD_STAGETMsg) S21ApiTBLAccessor.findByKey(inTmsg);
            if (cstusTMsg == null) {
                msgMap.addXxMsgId(NSZM1033E);
            }
        }

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

    private void doProcess(S21ApiMessageMap msgMap, NSZC108001PMsg pMsg) {

        CITS_SVC_TASK_UPD_STAGETMsg inTmsg = new CITS_SVC_TASK_UPD_STAGETMsg();
        ZYPEZDItemValueSetter.setValue(inTmsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTmsg.citsSvcTaskUpdStagePk, pMsg.citsSvcTaskUpdStagePk);
        CITS_SVC_TASK_UPD_STAGETMsg cstusTMsg = (CITS_SVC_TASK_UPD_STAGETMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTmsg);

        ZYPEZDItemValueSetter.setValue(cstusTMsg.plsftProcCd, pMsg.plsftProcCd);
        ZYPEZDItemValueSetter.setValue(cstusTMsg.plsftProcMsgTxt, pMsg.plsftProcMsgTxt);
        S21ApiTBLAccessor.update(cstusTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cstusTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM1035E);
        }
        for (int i = 0; i < pMsg.DebriefList.getValidCount(); i++) {
            CITS_DBRF_STAGETMsg in2Tmsg = new CITS_DBRF_STAGETMsg();
            ZYPEZDItemValueSetter.setValue(in2Tmsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(in2Tmsg.citsDbrfStagePk, pMsg.DebriefList.no(i).citsDbrfStagePk);
            CITS_DBRF_STAGETMsg cdsTMsg = (CITS_DBRF_STAGETMsg) S21ApiTBLAccessor.findByKeyForUpdate(in2Tmsg);

            ZYPEZDItemValueSetter.setValue(cdsTMsg.plsftProcCd, pMsg.DebriefList.no(i).plsftProcCd_A);
            ZYPEZDItemValueSetter.setValue(cdsTMsg.plsftProcMsgTxt, pMsg.DebriefList.no(i).plsftProcMsgTxt_A);
            S21ApiTBLAccessor.update(cdsTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cdsTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM1036E);
            }
        }
    }
}
