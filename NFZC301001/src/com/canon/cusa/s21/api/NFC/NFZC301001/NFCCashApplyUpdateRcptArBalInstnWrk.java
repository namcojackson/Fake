/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC301001;

import java.util.List;

import parts.common.EZDDebugOutput;
import business.db.AR_BAL_INSTN_WRKTMsg;
import business.parts.NFZC301002PMsg;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * 
 * <pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/05/2009   Canon           Y.Kondo         Create          N/A
 *</pre>
 */
public class NFCCashApplyUpdateRcptArBalInstnWrk extends S21ApiCommonBase {

    /**
     */
    public NFCCashApplyUpdateRcptArBalInstnWrk() {
        super();
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     */
    protected void execute(final NFZC301002PMsg param, final ONBATCH_TYPE onBatchType) {

        debugLog("execute : start");

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        updateCashApplyRcptArBalInstnWrk(msgMap, onBatchType);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            msgMap.flush();
        }

        debugLog("execute : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     */
    protected void execute(final List<NFZC301002PMsg> params, final ONBATCH_TYPE onBatchType) {

        for (NFZC301002PMsg msg : params) {
            execute(msg, onBatchType);
            if (!NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(msg.getReturnCode())) {
                break;
            }
        }
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     */
    protected void updateCashApplyRcptArBalInstnWrk(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        debugLog("updateCashApplyRcptArBalInstnWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();

        if (NFCConst.CST_NFZC301001_RTN_CD_UNPROC.equals(NFZC301001.getRtnCdNFZC301002(msgMap)) && !NFCConst.CST_XX_PROC_TP_CD_NON.equals(param.xxInstnRcptProcTpCd.getValue())
                && NFCConst.CST_XX_HDR_NUM_GET_FLG_ON.equals(param.xxHdrNumGetFlg.getValue())) {

            AR_BAL_INSTN_WRKTMsg instnWrkT = editArBalInstnWrk(msgMap);

            NFCCashApplyUpdateInvArBalInstnWrk wrk = new NFCCashApplyUpdateInvArBalInstnWrk();
            wrk.insArBalInstnWrk(msgMap, instnWrkT);
        }
        debugLog("updateCashApplyRcptArBalInstnWrk : end");
    }

    /**
     * 
     * <pre>
     * </pre>
     * 
     * @param msgMap S21ApiMessageMap
     * @param unApplyTLast AR_RCPT_UN_APPLYTMsg
     * @return instnWrkT AR_BAL_INSTN_WRKTMsg
     */
    private AR_BAL_INSTN_WRKTMsg editArBalInstnWrk(S21ApiMessageMap msgMap) {

        debugLog("editArBalInstnWrk : start");

        NFZC301002PMsg param = (NFZC301002PMsg) msgMap.getPmsg();
        AR_BAL_INSTN_WRKTMsg instnWrkT = new AR_BAL_INSTN_WRKTMsg();

        instnWrkT.glblCmpyCd.setValue(param.glblCmpyCd.getValue());
        instnWrkT.batDt.setValue(param.batDt.getValue());
        instnWrkT.billToCustCd.setValue(param.xxHdrTrxBillToCustCd.getValue());
        instnWrkT.sellToCustCd.setValue(param.xxHdrTrxSellToCustCd.getValue());
        instnWrkT.payerCustCd.setValue(param.xxHdrTrxPayerCustCd.getValue());
        instnWrkT.procTpCd.setValue(param.procTpCd.getValue());
        instnWrkT.batStsCd.setValue(NFCConst.CST_BAT_STS_CD_UNPROC);
        instnWrkT.arTrxBalPk.setValue(param.xxHdrTrxBalPk.getValue());
        instnWrkT.arTrxTpCd.setValue(param.xxHdrTrxArTrxTpCd.getValue());

        debugLog("editArBalInstnWrk : end");
        return instnWrkT;
    }

    /**
     * @param logmsg String
     */
    protected void debugLog(String logmsg) {
        EZDDebugOutput.println(NFCConst.DEBUG_MSG_LVL, logmsg, this);
    }
}
