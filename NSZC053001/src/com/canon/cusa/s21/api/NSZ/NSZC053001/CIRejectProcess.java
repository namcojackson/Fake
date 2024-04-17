/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;


import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_CR_REBILTMsg;
import business.parts.NSZC053001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 04/13/2016   Hitachi         K.Yamada        Update          QC#6512
 * 06/06/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 09/12/2016   Hitachi         K.Kishimoto     Update          QC#5566
 * 01/24/2017   Hitachi         A.Kohinata      Update          QC#17261
 *</pre>
 */
public class CIRejectProcess implements ZYPConstant {

    /** onBatchTp */
    private ONBATCH_TYPE onBatchTp;

    /** NSZC0530Query */
    private NSZC0530Query query = NSZC0530Query.getInstance();

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        cIRejectProcess(msgMap);
    }

    private void cIRejectProcess(S21ApiMessageMap msgMap) {

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
        // Release Billing Hold on Contract
        // ----------------------------------------
        NSZC053001CommonLogic.releaseBllgHld(msgMap, crRebilTMsg, this.onBatchTp);

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
        // Send mail to contract rep.
        // ----------------------------------------
        sendMail(msgMap, crRebilTMsg);
    }

    private void updateSvcCrRebil(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg inMsg) {

        setValue(inMsg.svcCrRebilStsCd, SVC_CR_REBIL_STS.ENTERED);
        inMsg.svcCrRebilRsnCd.clear();
        inMsg.svcCrRebilRsnTxt.clear();
        setValue(inMsg.svcCrRebilProcCd, SVC_CR_REBIL_PROC.CREDIT_AND_REBILL);
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0877E);
        }
    }

    // add start 2016/04/13 CSA Defect#6512
    private void sendMail(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg inMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // From Address
        S21MailAddress fromAddr = NSZC053001CommonLogic.getFromMailAddress(glblCmpyCd);
        if (fromAddr == null) {
            msgMap.addXxMsgId(NSZM0184E);
            return;
        }

        // To Address
        String asgPsnEmlAddr = NSZC053001CommonLogic.getEmailAddress(glblCmpyCd, inMsg.custIncdtAsgPsnCd.getValue());
        if (!hasValue(asgPsnEmlAddr)) {
            msgMap.addXxMsgId(NSZM0436E);
            return;
        }

        // Mail Template
        S21MailTemplate tmpl = NSZC053001CommonLogic.getMailTemplate(glblCmpyCd, REJECT_MAIL_TMPL_ID);
        if (!hasValue(tmpl.getSubject()) || !hasValue(tmpl.getBody())) {
            msgMap.addXxMsgId(NSZM0185E);
            return;
        }

        BigDecimal totCrAmt = query.getTotCrAmt(msgMap);
        Map<String, String> contrInfo = query.getContrInfo(msgMap);

        S21Mail mail = new S21Mail(glblCmpyCd);
        // set From Address
        mail.setFromAddress(fromAddr);
        // set To Address
        S21MailAddress toAddr = new S21MailAddress(glblCmpyCd, asgPsnEmlAddr);
        mail.setToAddress(toAddr);

        tmpl.setTemplateParameter("SYS_TS", NSZC053001CommonLogic.formatTimeStamp(inMsg));
        tmpl.setTemplateParameter("DS_ACCT_NM", contrInfo.get("DS_ACCT_NM"));
        tmpl.setTemplateParameter("CUST_INCDT_ID", inMsg.custIncdtId.getValue());
        tmpl.setTemplateParameter("OLD_DEAL_AMT", totCrAmt);
        tmpl.setTemplateParameter("SVC_BR_CD", contrInfo.get("SVC_CONTR_BR_CD"));
        tmpl.setTemplateParameter("DS_ACCT_NUM", contrInfo.get("DS_ACCT_NUM"));
        tmpl.setTemplateParameter("CMNT", NSZC053001CommonLogic.getComment(inMsg));

        mail.setMailTemplate(tmpl);
        mail.postMail();
    }
    // add end 2016/04/13 CSA Defect#6512

}
