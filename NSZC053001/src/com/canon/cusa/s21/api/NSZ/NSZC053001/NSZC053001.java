/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;

import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import business.parts.NSZC053001PMsg;
import business.parts.NSZC053001_xxCrRebilDtlListPMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;

/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 03/25/2016   Hitachi         T.Aoyagi        Update          QC#5435
 * 04/06/2016   Hitachi         T.Aoyagi        Update          QC#5963
 *</pre>
 */
public class NSZC053001 extends S21ApiCommonBase implements ZYPConstant {

    /**
     * Constructor
     */
    public NSZC053001() {
        super();
    }

    /**
     * execute Process Mode:01(CI Entry)
     * @param pMsg NSZC053001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(NSZC053001PMsg pMsg, final ONBATCH_TYPE onBatchType) {

        //START 2017/10/17 E.Kameishi [QC#18636,ADD]
        String mode = pMsg.xxModeCd.getValue();
        if (MODE_CI_ENTRY.equals(mode)) {
            for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
                setValue(pMsg.xxCrRebilDtlList.no(i).newTestMtrCnt, pMsg.xxCrRebilDtlList.no(i).newEndTestMtrCnt);
            }
        }
        //END 2017/10/17 E.Kameishi [QC#18636,ADD]
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        // START 03/25/2016 T.Aoyagi [QC#5435, MOD]
        if (!hasValue(pMsg.slsDt)) {
            setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        // END 03/25/2016 T.Aoyagi [QC#5435, MOD]

        NSZC053001CommonLogic.checkParameter(msgMap);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            setValue(pMsg.xxRsltStsCd, RSLT_STS_CD_ERROR);
            // START 04/06/2016 T.Aoyagi [QC#5963, ADD]
            msgMap.flush();
            // END 04/06/2016 T.Aoyagi [QC#5963, ADD]
            return;
        }

        // set default value
        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
            NSZC053001_xxCrRebilDtlListPMsg dtlPMsg = pMsg.xxCrRebilDtlList.no(i);
            if (hasValue(dtlPMsg.invPrintFlg)) {
                setValue(dtlPMsg.invPrintFlg, FLG_ON_Y);
            }
        }

        //START 2017/10/17 E.Kameishi [QC#18636,DEL]
        //String mode = pMsg.xxModeCd.getValue();
        //START 2017/10/17 E.Kameishi [QC#18636,DEL]
        if (MODE_CI_ENTRY.equals(mode)) {
            CIEntryProcess cIEntryProcess = new CIEntryProcess();
            cIEntryProcess.doProcess(msgMap, onBatchType);

        } else if (MODE_CI_UPDATE.equals(mode)) {
            CIUpdateProcess cIUpdateProcess = new CIUpdateProcess();
            cIUpdateProcess.doProcess(msgMap, onBatchType);

        } else if (MODE_SUBMIT_FOR_APPROVAL.equals(mode)) {
            SubmitForApprovalProcess submitForApprovalProcess = new SubmitForApprovalProcess();
            submitForApprovalProcess.doProcess(msgMap, onBatchType);

        } else if (MODE_CI_CANCEL.equals(mode)) {
            CICancelProcess cICancelProcess = new CICancelProcess();
            cICancelProcess.doProcess(msgMap, onBatchType);

        } else if (MODE_OVERRIDE_APPROVER.equals(mode)) {
            OverrideApproverProcess overrideApproverProcess = new OverrideApproverProcess();
            overrideApproverProcess.doProcess(msgMap, onBatchType);

        } else if (MODE_WORK_FLOW_APPROVE.equals(mode)) {
            WorkFlowApproveProcess workFlowApproveProcess = new WorkFlowApproveProcess();
            workFlowApproveProcess.doProcess(msgMap, onBatchType);

        } else if (MODE_CI_REJECT.equals(mode)) {
            CIRejectProcess cIRejectProcess = new CIRejectProcess();
            cIRejectProcess.doProcess(msgMap, onBatchType);

        } else if (MODE_INVOICING_FROM_BILLING.equals(mode)) {
            InvoicingFromBillingProcess invoicingFromBillingProcess = new InvoicingFromBillingProcess();
            invoicingFromBillingProcess.doProcess(msgMap, onBatchType);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            setValue(pMsg.xxRsltStsCd, RSLT_STS_CD_ERROR);
        } else {
            setValue(pMsg.xxRsltStsCd, RSLT_STS_CD_NORMAL);
        }

        msgMap.flush();
    }
}
