/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;


import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;

import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBIL_DTLTMsgArray;
import business.parts.NSZC053001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
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
 * 04/06/2016   Hitachi         T.Aoyagi        Update          QC#5963
 * 06/06/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 09/12/2016   Hitachi         K.Kishimoto     Update          QC#5566
 * 01/24/2017   Hitachi         A.Kohinata      Update          QC#17261
 *</pre>
 */
public class CICancelProcess implements ZYPConstant {

    /** onBatchTp */
    private ONBATCH_TYPE onBatchTp;

    /** NSZC0530Query */
    private NSZC0530Query query = NSZC0530Query.getInstance();

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        cICancelProcess(msgMap);
    }

    private void cICancelProcess(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        // ----------------------------------------
        // Cancel SVC_CR_REBIL
        // ----------------------------------------
        SVC_CR_REBILTMsg crRebilTMsg = null;
        if (hasValue(pMsg.svcCrRebilPk)) {
            crRebilTMsg = query.getSvcCrRebilTMsgForUpdate(msgMap);
        } else {
            crRebilTMsg = query.getSvcCrRebilTMsgByIncdtLineIdForUpdate(msgMap);
        }
        NSZC053001CommonLogic.cancelCrRebillInfo(msgMap, crRebilTMsg);

        // ----------------------------------------
        // Call Billing Schedule API
        // ----------------------------------------
        callBllgShcdApi(msgMap, crRebilTMsg);

        // ----------------------------------------
        // Release Billing Hold on Contract
        // ----------------------------------------
        NSZC053001CommonLogic.releaseBllgHld(msgMap, crRebilTMsg, this.onBatchTp);
        // START 04/06/2016 T.Aoyagi [QC#5963, ADD]
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // END 04/06/2016 T.Aoyagi [QC#5963, ADD]

        // update customer care status
        // Add Start 09/12/2016 <QC#5566>
        String cmnt = NSZC053001CommonLogic.getComment(crRebilTMsg);
        if (!hasValue(cmnt)) {
            cmnt = CALL_PRC_CANON_E193_NOTES_DETAIL_REJECTED;
        }
        // mod start 01/24/2017 CSA Defect#17261
        NSZC053001CommonLogic.callCustomerCareApi(msgMap, crRebilTMsg.custIncdtId.getValue(), crRebilTMsg.custIncdtLineId.getValue(), CALL_PRC_CANON_E193_NOTES_DETAIL_REJECTED, cmnt, crRebilTMsg.ezUpUserID.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // mod end 01/24/2017 CSA Defect#17261
        // Add End   09/12/2016 <QC#5566>
        // ----------------------------------------
        // Cancel workflow
        // ----------------------------------------
        cancelWf(msgMap, crRebilTMsg);
    }

    private void callBllgShcdApi(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal crRebilPk = crRebilTMsg.svcCrRebilPk.getValue();

        SVC_CR_REBIL_DTLTMsgArray crRebilDtlTMsgArray = query.getSvcCrRebilDtlTMsg(glblCmpyCd, crRebilPk);

        for (int i = 0; i < crRebilDtlTMsgArray.getValidCount(); i++) {
            NSZC053001CommonLogic.callBllgShcdApi(msgMap, this.onBatchTp, crRebilDtlTMsgArray.no(i), FLG_ON_Y, FLG_ON_Y);
        }

    }

    private void cancelWf(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg) {

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;

        try {
            context = factory.getContex();
            List<S21NwfProcess> processes = context.getProcess(WF_PROCESS_NM, crRebilTMsg.svcCrRebilPk.getValue().toPlainString());

            for (S21NwfProcess nwfProcess : processes) {
                if (nwfProcess.isActive()) {
                    nwfProcess.getToken().cancel();
                }
            }
        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfBizException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfException e) {
            msgMap.addXxMsgId(NSZM0866E);
        }
    }

}
