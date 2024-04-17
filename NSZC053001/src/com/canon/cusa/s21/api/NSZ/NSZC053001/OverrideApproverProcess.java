/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;


import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.parts.NSZC047099PMsg;
import business.parts.NSZC053001PMsg;
import business.parts.NSZC053001_xxCrRebilDtlListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 03/25/2016   Hitachi         T.Aoyagi        Update          QC#5435
 * 2019/12/16   Hitachi         A.Kohinata      Update          QC#55068
 * 2020/01/08   Hitachi         K.Kim           Update          QC#55170
 *</pre>
 */
public class OverrideApproverProcess implements ZYPConstant {

    // START 2019/12/23 [QC#55170, ADD]
    /** onBatchTp */
    private ONBATCH_TYPE onBatchTp;
    // END 2019/12/23 [QC#55170, ADD]

    /** NSZC0530Query */
    private NSZC0530Query query = NSZC0530Query.getInstance();

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        // START 2019/12/23 [QC#55170, ADD]
        this.onBatchTp = onBatchType;
        // END 2019/12/23 [QC#55170, ADD]
        overrideApproverProcess(msgMap);
    }

    private void overrideApproverProcess(S21ApiMessageMap msgMap) {

        // Get SVC_CR_REBIL
        SVC_CR_REBILTMsg crRebilTMsg = query.getSvcCrRebilTMsgForUpdate(msgMap);
        if (crRebilTMsg == null) {
            msgMap.addXxMsgId(NSZM0890E);
            return;
        }

        // ----------------------------------------
        // Update SVC_CR_REBIL
        // ----------------------------------------
        updateSvcCrRebil(msgMap, crRebilTMsg);

        // ----------------------------------------
        // update SVC_CR_REBIL_DTL
        // ----------------------------------------
        List<BigDecimal> crRebilDtlPkList = NSZC053001CommonLogic.getCrRebilDtlPkFromPMsg(msgMap);
        for (BigDecimal crRebilDtlPk : crRebilDtlPkList) {

            SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg = query.getSvcCrRebilDtlTMsgForUpdate(msgMap, crRebilDtlPk);
            if (crRebilDtlTMsg == null) {
                msgMap.addXxMsgId(NSZM0892E);
                return;
            }
            updateCrRebilDtl(msgMap, crRebilDtlTMsg);
        }
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // ----------------------------------------
        // Approve workflow
        // ----------------------------------------
        approveWf(msgMap, crRebilTMsg);

        // START 2020/01/08 [QC#55170, ADD]
        BigDecimal dsContrPk = query.getDsContrPk(crRebilTMsg.glblCmpyCd.getValue(), crRebilTMsg.svcCrRebilPk.getValue());
        callBllgSchdApiRecovMode(msgMap, dsContrPk);
        NSZC053001CommonLogic.recovPrntSvcContrBllgForRegAcc(msgMap, crRebilTMsg.svcCrRebilPk.getValue());
        // END 2020/01/08 [QC#55170, ADD]
    }

    private void updateSvcCrRebil(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg inMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        setValue(inMsg.svcCrRebilRsnCd, pMsg.svcCrRebilRsnCd);
        setValue(inMsg.svcCrRebilRsnTxt, pMsg.svcCrRebilRsnTxt);
        setValue(inMsg.svcCrRebilProcCd, pMsg.svcCrRebilProcCd);
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0877E);
        }
    }

    private void updateCrRebilDtl(S21ApiMessageMap msgMap, SVC_CR_REBIL_DTLTMsg inMsg) {

        String invPrintFlg = getInvPrintFlg(msgMap, inMsg.svcCrRebilDtlPk.getValue());
        setValue(inMsg.invPrintFlg, invPrintFlg);
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0879E);
        }
    }

    private String getInvPrintFlg(S21ApiMessageMap msgMap, BigDecimal crRebilDtlPk) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String invPrintFlg = FLG_ON_Y;

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
            NSZC053001_xxCrRebilDtlListPMsg dtlPMsg = pMsg.xxCrRebilDtlList.no(i);
            if (hasValue(dtlPMsg.svcCrRebilDtlPk)) {
                invPrintFlg = dtlPMsg.invPrintFlg.getValue();
                break;
            }
        }
        return invPrintFlg;
    }

    private void approveWf(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg) {

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;

        // START 03/25/2016 T.Aoyagi [QC#5435, MOD]
        try {
            context = factory.getContex();
            List<S21NwfProcess> processes = context.getProcess(WF_PROCESS_NM, crRebilTMsg.svcCrRebilPk.getValue().toPlainString());

            for (S21NwfProcess nwfProcess : processes) {
                if (nwfProcess.isActive()) {
                    nwfProcess.getToken().signal(SIGNAL_APPROVE);
                }
            }
        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0926E);
        } catch (S21NwfBizException e) {
            // mod start 2019/12/16 QC#55068
            //msgMap.addXxMsgId(NSZM0926E);
            msgMap.addXxMsgIdWithPrm(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
            // mod end 2019/12/16 QC#55068
        } catch (S21NwfException e) {
            msgMap.addXxMsgId(NSZM0926E);
        }
        // END 03/25/2016 T.Aoyagi [QC#5435, MOD]
    }

    // START 2019/12/23 [QC#55170, ADD]
    private boolean callBllgSchdApiRecovMode(S21ApiMessageMap msgMap, BigDecimal dsContrPk) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        NSZC047099PMsg apiPMsg = new NSZC047099PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, "99");
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.dsContrPk, dsContrPk);
        NSZC047001 api = new NSZC047001();
        api.execute(apiPMsg, this.onBatchTp);
        if (hasValue(apiPMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            S21ApiMessage msg = msgList.get(0);
            msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }
        return true;
    }
    // END 2019/12/23 [QC#55170, ADD]
}
