/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC110001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.util.List;

import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;

import business.db.CFS_CONTR_PRC_UPLFTTMsg;

import business.parts.NSZC110001PMsg;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.api.NSZ.NSZC110001.constant.NSZC110001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_PROC_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import static com.canon.cusa.s21.api.NSZ.NSZC110001.constant.NSZC110001Constant.NSZM0001E;
import static com.canon.cusa.s21.api.NSZ.NSZC110001.constant.NSZC110001Constant.NSZM1061E;
import static com.canon.cusa.s21.api.NSZ.NSZC110001.constant.NSZC110001Constant.NSZM1062E;
import static com.canon.cusa.s21.api.NSZ.NSZC110001.constant.NSZC110001Constant.NSZM1063E;
import static com.canon.cusa.s21.api.NSZ.NSZC110001.constant.NSZC110001Constant.NSZM1064E;

/**
 * <pre>
 * CFS Contract Uplift API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/29/2015   Hitachi         Y.Zhang         Create          N/A
 *</pre>
 */
public class NSZC110001 extends S21ApiCommonBase {

    /**
     * Constructor
     */
    public NSZC110001() {
        super();
    }

    /**
     * @param params List<NSZC110001PMsg>
     * @param onBatchType final
     */
    public void execute(List<NSZC110001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NSZC110001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * execute
     * @param pMsg NSZC110001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC110001PMsg pMsg, final ONBATCH_TYPE onBatchType) {
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        executeProc(pMsg, onBatchType, msgMap);
        msgMap.flush();
    }

    /**
     * executeProc
     * @param pMsg NSZC110001PMsg
     * @param onBatchType ONBATCH_TYPE
     * @param msgMap S21ApiMessageMap
     */
    public void executeProc(NSZC110001PMsg pMsg, final ONBATCH_TYPE onBatchType, S21ApiMessageMap msgMap) {

        // initialize
        NSZC110001PMsg param = (NSZC110001PMsg) msgMap.getPmsg();

        // mandatory check
        if (!checkParam(msgMap)) {
            return;
        }
        // get CFS_CONTR_PRC_UPLFT Info
        CFS_CONTR_PRC_UPLFTTMsg cfsContrUplftMsg = getcfsContrUplft(param);

        // update CFS_CONTR_PRC_UPLFT
        updateCfsContrPrcUplft(msgMap, cfsContrUplftMsg);
    }

    /**
     *checkParam
     * @param pMsg S21ApiMessageMap
     */
    private boolean checkParam(S21ApiMessageMap msgMap) {

        NSZC110001PMsg param = (NSZC110001PMsg) msgMap.getPmsg();
        // check common mandatory parameter
        checkCommonParameter(msgMap, param.glblCmpyCd, NSZM0001E);
        checkCommonParameter(msgMap, param.cfsContrPrcUplftPk, NSZM1061E);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }

        return true;
    }

    /**
     *checkCommonParameter
     * @param pMsg S21ApiMessageMap
     * @param item EZDPItem
     * @param messageId String
     */
    private void checkCommonParameter(S21ApiMessageMap msgMap, EZDPItem item, String messageId) {
        if (!hasValue(item)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    /**
     *getcfsContrUplft
     * @param param NSZC110001PMsg
     */
    private CFS_CONTR_PRC_UPLFTTMsg getcfsContrUplft(NSZC110001PMsg param) {
        CFS_CONTR_PRC_UPLFTTMsg inTMsg = new CFS_CONTR_PRC_UPLFTTMsg();
        setValue(inTMsg.glblCmpyCd, param.glblCmpyCd);
        setValue(inTMsg.cfsContrPrcUplftPk, param.cfsContrPrcUplftPk);
        CFS_CONTR_PRC_UPLFTTMsg outTMsg = (CFS_CONTR_PRC_UPLFTTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }

    /**
     *updateCfsContrPrcUplft
     * @param msgMap S21ApiMessageMap
     */
    private void updateCfsContrPrcUplft(S21ApiMessageMap msgMap, CFS_CONTR_PRC_UPLFTTMsg cfsContrUplftMsg) {

        NSZC110001PMsg param = (NSZC110001PMsg) msgMap.getPmsg();

        CFS_CONTR_PRC_UPLFTTMsg cfsContrPrcUplftMsg = new CFS_CONTR_PRC_UPLFTTMsg();
        if (cfsContrUplftMsg != null) {
            if(cfsContrUplftMsg.cfsProcStsCd !=null){

                // the value of cfsProcStsCd get from CFS_CONTR_PRC_UPLFT Info is not exist
                if (!hasValue(cfsContrUplftMsg.cfsProcStsCd.getValue())) {
                    // set update failed messageTxt
                    setErrMsg(param, NSZM1064E);
                    // set the value of cfsProcStsCd
                    setValue(param.cfsProcStsCd, CFS_PROC_STS.ERROR);
                    // the value of cfsProcStsCd get from CFS_CONTR_PRC_UPLFTInfo is not "0"
                } else if (!CFS_PROC_STS.IN_COMPLETED.equals(cfsContrUplftMsg.cfsProcStsCd.getValue())) {
                    setErrMsg(param, NSZM1062E);
                    // set the value of cfsProcStsCd
                    setValue(param.cfsProcStsCd, CFS_PROC_STS.ERROR);
                    return;
                } else {
                    // parameter set
                    setValue(cfsContrPrcUplftMsg.glblCmpyCd, param.glblCmpyCd);
                    setValue(cfsContrPrcUplftMsg.cfsContrPrcUplftPk, param.cfsContrPrcUplftPk);
                    cfsContrPrcUplftMsg = (CFS_CONTR_PRC_UPLFTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(cfsContrPrcUplftMsg);
                    // set the value of cfsProcStsCd
                    setValue(cfsContrPrcUplftMsg.cfsProcStsCd, param.cfsProcStsCd);
                    // set the value of cfsProcDtTmTs
                    setValue(cfsContrPrcUplftMsg.cfsProcDtTmTs, getSystemDateTime());
                    // update execute
                    S21ApiTBLAccessor.update(cfsContrPrcUplftMsg);
                    // update failed
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(cfsContrPrcUplftMsg.getReturnCode())) {
                        // set update failed messageTxt
                        setErrMsg(param, NSZM1063E);
                        // set the value of cfsProcStsCd
                        setValue(param.cfsProcStsCd, CFS_PROC_STS.ERROR);
                    }
                }
                
            }
        }
    }

    /**
     *getSystemDateTime
     */
    private static String getSystemDateTime() {

        // get sales date
        String yyyymmdd = ZYPDateUtil.getSalesDate();
        // get current time
        String hhmmss = ZYPDateUtil.getCurrentSystemTime("HHmmss");
        // calculate SystemDateTime
        return yyyymmdd + hhmmss;
    }

    /**
     * <pre>
     * Set Error Message
     * </pre>
     * @param pMsg NFZC504001PMsg
     * @param msgId String
     * @param msgPrms String[]
     */
    private void setErrMsg(NSZC110001PMsg pMsg, String msgId) {
        int cnt = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId)));
        pMsg.xxMsgIdList.setValidCount(cnt + 1);
    }

    /**
     * <pre>
     * Cut Error Message
     * </pre>
     * @param msg String
     * @return Error Message String within MAX_MSG_LEN
     */
    private String cutMsg(String msg) {
        if (msg == null) {
            return null;
        }
        if (msg.length() > NSZC110001Constant.MAX_MSG_LEN) {
            return msg.substring(0, NSZC110001Constant.MAX_MSG_LEN);
        }
        return msg;
    }
}
