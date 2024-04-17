/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;


import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import tools.ezdcommon.EZDMessageFunc;
import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.parts.NSZC053001PMsg;
import business.parts.NSZC053001_xxCrRebilDtlListPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.internal.codetable.S21MsgAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 04/08/2016   Hitachi         T.Aoyagi        Update          QC#6737
 * 04/13/2016   Hitachi         T.Aoyagi        Update          QC#6510
 * 04/13/2016   Hitachi         K.Yamada        Update          QC#6512
 * 09/12/2016   Hitachi         K.Kishimoto     Update          QC#5566
 * 01/24/2017   Hitachi         A.Kohinata      Update          QC#17261
 * 2018/03/13   Hitachi         K.Kojima        Update          QC#21779
 * 2018/03/15   Hitachi         K.Kojima        Update          QC#22669
 *</pre>
 */
public class InvoicingFromBillingProcess implements ZYPConstant {

    /** NSZC0530Query */
    private NSZC0530Query query = NSZC0530Query.getInstance();

    // START 2018/03/13 K.Kojima [QC#21779,ADD]
    /** From Address */
    private S21MailAddress fromAddr = null;

    /** To Address */
    private String asgPsnEmlAddr = null;

    /** Mail Template */
    private S21MailTemplate tmpl = null;

    // END 2018/03/13 K.Kojima [QC#21779,ADD]

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        invoicingFromBillingProcess(msgMap);
    }

    private void invoicingFromBillingProcess(S21ApiMessageMap msgMap) {

        // Get SVC_CR_REBIL
        SVC_CR_REBILTMsg crRebilTMsg = query.getSvcCrRebilTMsgForUpdate(msgMap);
        if (crRebilTMsg == null) {
            msgMap.addXxMsgId(NSZM0890E);
            return;
        }

        // START 2018/03/13 K.Kojima [QC#21779,ADD]
        getMailData(msgMap, crRebilTMsg);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // END 2018/03/13 K.Kojima [QC#21779,ADD]

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

        // START 2018/03/15 K.Kojima [QC#22669,ADD]
        BigDecimal count = query.countNoInvoiceCrRebilCount(crRebilTMsg.glblCmpyCd.getValue(), crRebilTMsg.svcCrRebilPk.getValue());
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return;
        }
        // END 2018/03/15 K.Kojima [QC#22669,ADD]

        // update customer care status
        // Add Start 09/12/2016 <QC#5566>
        String cmnt = NSZC053001CommonLogic.getComment(crRebilTMsg);
        if (!hasValue(cmnt)) {
            // START 2018/03/15 K.Kojima [QC#22669,MOD]
            // cmnt = CALL_PRC_CANON_E193_NOTES_DETAIL_CLOSE;
            cmnt = S21MessageFunc.clspGetMessage(NSZM1323I).substring(NSZM1323I.length() + 1);
            // END 2018/03/15 K.Kojima [QC#22669,MOD]
        }
        // mod start 01/24/2017 CSA Defect#17261
        // START 2018/03/15 K.Kojima [QC#22669,MOD]
        // NSZC053001CommonLogic.callCustomerCareApi(msgMap, crRebilTMsg.custIncdtId.getValue(), crRebilTMsg.custIncdtLineId.getValue(), CALL_PRC_CANON_E193_NOTES_DETAIL_CLOSE, cmnt, crRebilTMsg.ezUpUserID.getValue());
        NSZC053001CommonLogic.callCustomerCareApi(msgMap, crRebilTMsg.custIncdtId.getValue(), crRebilTMsg.custIncdtLineId.getValue(), CALL_PRC_CANON_E193_NOTES_DETAIL_CLOSE, cmnt, UPDATE_USER_SYSTEM_UPDATE);
        // END 2018/03/15 K.Kojima [QC#22669,MOD]
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // mod end 01/24/2017 CSA Defect#17261
        // Add End   09/12/2016 <QC#5566>

        // START 04/08/2016 T.Aoyagi [QC#6737, DEL]
//        // ----------------------------------------
//        // Release Billing Hold on Contract
//        // ----------------------------------------
//        NSZC053001CommonLogic.releaseBllgHld(msgMap, crRebilTMsg);
        // END 04/08/2016 T.Aoyagi [QC#6737, DEL]

        // ----------------------------------------
        // Send mail to Incident creator
        // ----------------------------------------
        sendMail(msgMap, crRebilTMsg);
    }

    // START 2018/03/13 K.Kojima [QC#21779,ADD]
    private void getMailData(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg inMsg) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // From Address
        this.fromAddr = NSZC053001CommonLogic.getFromMailAddress(glblCmpyCd);
        if (this.fromAddr == null) {
            msgMap.addXxMsgId(NSZM0184E);
            return;
        }

        // To Address
        this.asgPsnEmlAddr = NSZC053001CommonLogic.getEmailAddress(glblCmpyCd, inMsg.custCareRgtnPsnCd.getValue());
        if (!hasValue(this.asgPsnEmlAddr)) {
            msgMap.addXxMsgId(NSZM0436E);
            return;
        }

        // Mail Template
        this.tmpl = NSZC053001CommonLogic.getMailTemplate(glblCmpyCd, CMPL_MAIL_TMPL_ID);
        if (!hasValue(this.tmpl.getSubject()) || !hasValue(this.tmpl.getBody())) {
            msgMap.addXxMsgId(NSZM0185E);
            return;
        }
    }
    // END 2018/03/13 K.Kojima [QC#21779,ADD]


    private void updateSvcCrRebil(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg inMsg) {

        setValue(inMsg.svcCrRebilStsCd, SVC_CR_REBIL_STS.PROCESSED);
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0877E);
        }
    }

    private void updateCrRebilDtl(S21ApiMessageMap msgMap, SVC_CR_REBIL_DTLTMsg inMsg) {

        NSZC053001_xxCrRebilDtlListPMsg dtlPMsg = getSvcInvNum(msgMap, inMsg.svcCrRebilDtlPk.getValue());

        // START 04/13/2016 T.Aoyagi [QC#6510, MOD]
        if (hasValue(dtlPMsg.crSvcInvNum)) {
            setValue(inMsg.crSvcInvNum, dtlPMsg.crSvcInvNum);
        }
        if (hasValue(dtlPMsg.rebilSvcInvNum)) {
            setValue(inMsg.rebilSvcInvNum, dtlPMsg.rebilSvcInvNum);
        }
        // END 04/13/2016 T.Aoyagi [QC#6510, MOD]
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0879E);
        }
    }

    private NSZC053001_xxCrRebilDtlListPMsg getSvcInvNum(S21ApiMessageMap msgMap, BigDecimal crRebilDtlPk) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        NSZC053001_xxCrRebilDtlListPMsg rtnDtlPMsg = new NSZC053001_xxCrRebilDtlListPMsg();
        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
            NSZC053001_xxCrRebilDtlListPMsg dtlPMsg = pMsg.xxCrRebilDtlList.no(i);

            if (!hasValue(dtlPMsg.svcCrRebilDtlPk)) {
                continue;
            }
            if (crRebilDtlPk.compareTo(dtlPMsg.svcCrRebilDtlPk.getValue()) == 0) {
                EZDMsg.copy(dtlPMsg, null, rtnDtlPMsg, null);
                break;
            }
        }
        return rtnDtlPMsg;
    }

    // add start 2016/04/13 CSA Defect#6512
    private void sendMail(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg inMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // START 2018/03/13 K.Kojima [QC#21779,DEL]
        // // From Address
        // S21MailAddress fromAddr = NSZC053001CommonLogic.getFromMailAddress(glblCmpyCd);
        // if (fromAddr == null) {
        //     msgMap.addXxMsgId(NSZM0184E);
        //     return;
        // }
        // 
        // // To Address
        // String asgPsnEmlAddr = NSZC053001CommonLogic.getEmailAddress(glblCmpyCd, inMsg.custCareRgtnPsnCd.getValue());
        // if (!hasValue(asgPsnEmlAddr)) {
        //     msgMap.addXxMsgId(NSZM0436E);
        //     return;
        // }
        // 
        // // Mail Template
        // S21MailTemplate tmpl = NSZC053001CommonLogic.getMailTemplate(glblCmpyCd, CMPL_MAIL_TMPL_ID);
        // if (!hasValue(tmpl.getSubject()) || !hasValue(tmpl.getBody())) {
        //     msgMap.addXxMsgId(NSZM0185E);
        //     return;
        // }
        // END 2018/03/13 K.Kojima [QC#21779,DEL]

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
