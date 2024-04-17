/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC105001;

import static com.canon.cusa.s21.api.NWZ.NSZC105001.constant.NSZC105001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.COND_END_NO_USER;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_APPROVE;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_REJECT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;
import business.db.CLT_NOTE_DTLTMsg;
import business.db.CLT_NOTE_TPTMsg;
import business.db.DS_CONTRTMsg;
import business.db.FSRTMsg;
import business.db.S21_PSNTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMOTMsgArray;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASKTMsgArray;
import business.parts.NSZC038001PMsg;
import business.parts.NSZC038001_xxMsgIdListPMsg;
import business.parts.NSZC061001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC037001.NSZC037001TokenObject;
import com.canon.cusa.s21.api.NSZ.NSZC037001.NSZC037001TokenObjectLine;
import com.canon.cusa.s21.api.NSZ.NSZC038001.NSZC038001;
import com.canon.cusa.s21.api.NSZ.NSZC038001.constant.NSZC038001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC061001.NSZC061001;
import com.canon.cusa.s21.api.NSZ.NSZC061001.constant.NSZC061001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_NOTE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizHistWorkItem;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;

/**
 *<pre>
 * Service Credit Check Workflow API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Hitachi         T.Tomita        Create          CSA QC#895
 * 2016/04/05   Hitachi         T.Tomita        Update          CSA QC#895
 * 2016/08/02   Hitachi         K.Yamada        Update          CSA QC#4324
 * 2016/12/28   Hitachi         K.Kojima        Update          QC#15640
 * 2018/10/30   Hitachi         K.Kitachi       Update          CSA QC#28879
 * 2022/01/27   Hitachi         R.Onozuka       Update          CSA QC#56182
 * 2022/11/15   Hitachi         S.Fujita        Update          CSA QC#60406
 * 2022/12/27   Hitachi         S.Fujita        Update          CSA QC#60744
 * 2023/02/28   Hitachi         S.Fujita        Update          CSA QC#61185
 *</pre>
 */
public class NSZC105001 implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>,
                                        S21NwfRejectEvent<S21NwfContext, S21NwfToken>,
                                        S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {
    /**
     * System error has occurred.
     */
    public static final String NFDM0008E = "NFDM0008E";

    /**
     * The Credit Analyst Person does not exists.
     */
    public static final String NSZM0922E = "NSZM0922E";

    // add start 2016/04/05 CSA Defect#895
    /**
     * @ does not exist in @.
     */
    public static final String NSZM0633E = "NSZM0633E";

    /**
     * @ doesn't exist.
     */
    public static final String NSZM0627E = "NSZM0627E";

    /**
     * Work Flow process name
     */
    public static final String WF_PROCESS_NM = "NSWP0004";

    /** E-Mail From Address Group */
    public static final String FROM_ADDR_GRP_CD = "FROM0003";

    /** Mail Template Code */
    public static final String MAIL_TEMP_CD = "NSZC1050M001";

    /** Error E-Mail From Address Group */
    public static final String ERR_TO_ADDR_GRP_CD = "NSZC1050";

    /** Mail Template Code */
    public static final String ERR_MAIL_TEMP_CD = "NSZC1050M002";

    private S21SsmBatchClient ssmBatchClient = null;

    private String sysTs = null;

    private List<String> errMsgList;
    // add end 2016/04/05 CSA Defect#895

    /**
     * Approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        return;
    }

    /**
     * Reject
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void reject(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        return;
    }

    /**
     * End
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void end(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        // add start 2016/04/05 CSA Defect#895
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.sysTs = ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy HH:mm");
        this.errMsgList = new ArrayList<String>();
        // add end 2016/04/05 CSA Defect#895

        NSZC037001TokenObject tokenObj = (NSZC037001TokenObject) token.getTokenObj();
        List<NSZC037001TokenObjectLine> lines = tokenObj.getLineData();
        for (NSZC037001TokenObjectLine line : lines) {
            // call Service Credit Review API
            if (SIGNAL_APPROVE.equals(name)) {
                // Approve Mode
                // mod start 2022/11/15 QC#60406
                //callApi(line, NSZC038001Constant.MODE_APPROVE, context);
                callApi(line, NSZC038001Constant.MODE_APPROVE, context, tokenObj);
                // mod end 2022/11/15 QC#60406
            } else if (SIGNAL_REJECT.equals(name)) {
                // Reject Mode
                // 2019/02/04 CSA QC#28799 Mod Start
//                callApi(line, NSZC038001Constant.MODE_REJECT, context);
                callApi(line, NSZC038001Constant.MODE_REJECT, context, tokenObj);
                // 2019/02/04 CSA QC#28799 Mod End
            } else if (COND_END_NO_USER.equals(name)) {
                // START 2016/12/28 K.Kojima [QC#15640,MOD]
                // throw new S21AbendException(NSZM0922E);
                throw new S21NwfBizException(NSZM0922E, null);
                // END 2016/12/28 K.Kojima [QC#15640,MOD]
            }
        }
    }

    // 2019/02/04 CSA QC#28799 Add Start
     private void callApi(NSZC037001TokenObjectLine line, String xxModeCd, S21NwfContext context) throws S21NwfBizException {
       callApi(line, xxModeCd, context, null);
     }
    // 2019/02/04 CSA QC#28799 Add End
    private void callApi(NSZC037001TokenObjectLine line, String xxModeCd, S21NwfContext context, NSZC037001TokenObject tokenObj) throws S21NwfBizException { // 2019/02/04 CSA QC#28799 Add param: workFlwComment
        
    	// Process Time stamp
        String procTs = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        // set PMsg
        NSZC038001PMsg pMsg = new NSZC038001PMsg();
        setValue(pMsg.glblCmpyCd, line.getDtlAttrb1());
        setValue(pMsg.slsDt, procTs.substring(0, 8));
        setValue(pMsg.usrId, context.getUserID());
        setValue(pMsg.fsrNum, line.getDtlAttrb2());
        setValue(pMsg.xxModeCd, xxModeCd);
        setValue(pMsg.svcTaskApvlDt, procTs.substring(0, 8));
        setValue(pMsg.svcTaskApvlTm, procTs.substring(8, 14));
        // call API
        NSZC038001 api = new NSZC038001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            NSZC038001_xxMsgIdListPMsg msg = pMsg.xxMsgIdList.no(0);
            String[] items = getMsgItems(msg.xxMsgPrmTxt_0.getValue(), msg.xxMsgPrmTxt_1.getValue(), msg.xxMsgPrmTxt_2.getValue(), msg.xxMsgPrmTxt_3.getValue(), msg.xxMsgPrmTxt_4.getValue());
            if (items == null) {
                // mod start 2016/08/02 CSA Defect#4324
                throw new S21NwfBizException(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), null);
            } else {
                throw new S21NwfBizException(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), items);
                // mod end 2016/08/02 CSA Defect#4324
            }
        }

        // add start 2016/04/05 CSA Defect#895
        if ( NSZC038001Constant.MODE_APPROVE.equals(xxModeCd)) {
           // del start 2023/02/28 QC#61185
           // add start 2022/11/15 QC#60406
           //setCollectionText(pMsg, tokenObj, xxModeCd);
           // add end 2022/11/15 QC#60406
           // del start 2023/02/28 QC#61185
            // send Notification Mail
            for (int i = 0; i < pMsg.ManagerList.getValidCount(); i++) {
                sendEmail(pMsg, pMsg.ManagerList.no(i).mgrPsnCd.getValue(), context);
            }
            // sent Notification Error Mail
            if (this.errMsgList.size() > 0) {
                sendErrEmail(pMsg);
            }
        }
        // add end 2016/04/05 CSA Defect#895

        // 2019/02/04 CSA QC#28799 Add Start
        if (NSZC038001Constant.MODE_REJECT.equals(xxModeCd)) {
            // mod start 2022/11/15 QC#60406
            //setCollectionText(pMsg, tokenObj);
            setCollectionText(pMsg, tokenObj, xxModeCd);
            // mod end 2022/11/15 QC#60406
        }
        // 2019/02/04 CSA QC#28799 Add End
    }

    private String[] getMsgItems(String... items) {
        List<String> itemList = new ArrayList<String>();
        for (String item : items) {
            if (!ZYPCommonFunc.hasValue(item)) {
                continue;
            }
            itemList.add(item);
        }
        if (itemList.size() == 0) {
            return null;
        }
        return itemList.toArray(new String[itemList.size()]);
    }

    // add start 2016/04/05 CSA Defect#895
    private void sendEmail(NSZC038001PMsg pMsg, String managerOfUsr, S21NwfContext context) {

        if (!hasValue(managerOfUsr)) {
            return;
        }

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // From Address
        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, FROM_ADDR_GRP_CD);
        fromGrp.setMailKey1("NSZ");
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();
        if (fromAddrList.size() == 0) {
            errMsgList.add(S21MessageFunc.clspGetMessage(NSZM0627E, new String[] {"From Mail Address[ML_GRP_ID＝FROM0003]" }));
            return;
        }

        // To Address
        String managerAddr = getManagerOfUsrAddress(glblCmpyCd, managerOfUsr);
        if (!hasValue(managerAddr)) {
            errMsgList.add(S21MessageFunc.clspGetMessage(NSZM0633E, new String[] {"Email Address", "S21_PSN[PSN_CD=" + managerOfUsr + "]" }));
            return;
        }

        // Mail Template
        S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, MAIL_TEMP_CD);
        if (!hasValue(tmpl.getSubject()) || !hasValue(tmpl.getBody())) {
            errMsgList.add(S21MessageFunc.clspGetMessage(NSZM0627E, new String[] {"Mail Template[ML_TMPL_ID=NSZC1050M001]" }));
            return;
        }

        // START 2018/10/30 K.Kitachi [QC#28879, ADD]
        boolean isBllbl = false;
        BigDecimal count = countBllblTask(pMsg);
        if (BigDecimal.ZERO.compareTo(count) < 0) {
            isBllbl = true;
        }
        // END 2018/10/30 K.Kitachi [QC#28879, ADD]

        // Mail Details
        // START 2018/10/30 K.Kitachi [QC#28879, MOD]
//        Map<String, Object> tempMap = getNotificatonMailData(pMsg);
        Map<String, Object> tempMap = getNotificatonMailData(pMsg, isBllbl);
        // END 2018/10/30 K.Kitachi [QC#28879, MOD]
        if (tempMap == null) {
            errMsgList.add(S21MessageFunc.clspGetMessage(NSZM0627E, new String[] {"Mail content" }));
            return;
        }

        S21Mail mail = new S21Mail(glblCmpyCd);
        // set From Address
        mail.setFromAddress(fromAddrList.get(0));
        // set To Address
        S21MailAddress toAddr = new S21MailAddress(glblCmpyCd, managerAddr);
        mail.setToAddress(toAddr);

        tmpl.setTemplateParameter("SYS_TS", (String) tempMap.get("SYS_TS"));
        tmpl.setTemplateParameter("DS_ACCT_NM", (String) tempMap.get("DS_ACCT_NM"));
        tmpl.setTemplateParameter("FSR_NUM", (String) tempMap.get("FSR_NUM"));
        tmpl.setTemplateParameter("CALL_AMT", (BigDecimal) tempMap.get("CALL_AMT"));
        tmpl.setTemplateParameter("SVC_BR_CD", (String) tempMap.get("SVC_BR_CD"));
        tmpl.setTemplateParameter("DS_ACCT_NUM", (String) tempMap.get("DS_ACCT_NUM"));
        tmpl.setTemplateParameter("DS_CLT_ACCT_STS_DESC_TXT", (String) tempMap.get("DS_CLT_ACCT_STS_DESC_TXT"));
        // START 2018/10/30 K.Kitachi [QC#28879, MOD]
//        tmpl.setTemplateParameter("CR_CHK_REQ_TP_DEF_FLG", (String) tempMap.get("CR_CHK_REQ_TP_DEF_FLG"));
        tmpl.setTemplateParameter("CUST_HARD_HLD_FLG", (String) tempMap.get("CUST_HARD_HLD_FLG"));
        // END 2018/10/30 K.Kitachi [QC#28879, MOD]
        tmpl.setTemplateParameter("CR_LIMIT_AMT", (BigDecimal) tempMap.get("CR_LIMIT_AMT"));
        tmpl.setTemplateParameter("OPEN_AR", (BigDecimal) tempMap.get("OPEN_AR"));
        // START 2018/10/30 K.Kitachi [QC#28879, MOD]
//        tmpl.setTemplateParameter("DEAL_RMNG_BAL_GRS_AMT", (BigDecimal) tempMap.get("DEAL_RMNG_BAL_GRS_AMT"));
        tmpl.setTemplateParameter("IN_PROC_AMT", (BigDecimal) tempMap.get("IN_PROC_AMT"));
        // END 2018/10/30 K.Kitachi [QC#28879, MOD]
        tmpl.setTemplateParameter("CALL_TOT_AMT", (BigDecimal) tempMap.get("CALL_TOT_AMT"));
        tmpl.setTemplateParameter("CALL_TAX_AMT", (BigDecimal) tempMap.get("CALL_TAX_AMT"));
        // START 2022/12/27 S.Fujita [QC#60744, DEL]
        // tmpl.setTemplateParameter("GRACE_PER_DAYS_AOT", (BigDecimal) tempMap.get("GRACE_PER_DAYS_AOT"));
        // END 2022/12/27 S.Fujita [QC#60744, DEL]
        tmpl.setTemplateParameter("PAST_DUE_BY", (BigDecimal) tempMap.get("PAST_DUE_BY"));
        tmpl.setTemplateParameter("PAST_DUE_AMT", (BigDecimal) tempMap.get("PAST_DUE_AMT"));
        tmpl.setTemplateParameter("DS_PO_REQ_FLG", (String) tempMap.get("DS_PO_REQ_FLG"));
        tmpl.setTemplateParameter("CUST_PO_NUM", (String) tempMap.get("CUST_PO_NUM"));
        // START 2022/1/27 R.Onozuka [QC#56182, ADD]
        String slsDate = pMsg.slsDt.getValue().toString();
        FSRTMsg fsrTMsg = getFsrTMsg(glblCmpyCd, (String) tempMap.get("FSR_NUM"));
        String dsSvcCallTpCd = getDsSvcCallTpCd(glblCmpyCd, (String) tempMap.get("FSR_NUM"));
        tmpl.setTemplateParameter("DS_CONTR_NUM", 
                identifyDsContrNum(glblCmpyCd, NSZC061001Constant.PROCESS_MODE_CALL_CREATION, slsDate, 
                        fsrTMsg.svcMachMstrPk.getValue(), dsSvcCallTpCd)
        );
        // END   2022/1/27 R.Onozuka [QC#56182, ADD]
        tmpl.setTemplateParameter("PMT_TERM_CASH_DISC_DESC_TXT", (String) tempMap.get("PMT_TERM_CASH_DISC_DESC_TXT"));
        tmpl.setTemplateParameter("CMNT", getComment(context, (String) tempMap.get("FSR_NUM")));
        tmpl.setTemplateParameter("SVC_TASK_HLD_RSN_DESC_TXT", (String) tempMap.get("SVC_TASK_HLD_RSN_DESC_TXT"));

        // START 2022/12/27 S.Fujita [QC#60744, DEL]
        // START 2018/10/30 K.Kitachi [QC#28879, ADD]
        //if (isBllbl) {
        //    tempMap = getPastDueInfo(pMsg);
        //    if (tempMap != null) {
        //        tmpl.setTemplateParameter("PAST_DUE_BY", (BigDecimal) tempMap.get("PAST_DUE_BY"));
        //        tmpl.setTemplateParameter("PAST_DUE_AMT", (BigDecimal) tempMap.get("PAST_DUE_AMT"));
        //    }
        // }
        // END 2018/10/30 K.Kitachi [QC#28879, ADD]
        // END 2022/12/27 S.Fujita [QC#60744, DEL]
        // START 2022/12/27 S.Fujita [QC#60744, ADD]
        if (isBllbl) {
            tmpl.setTemplateParameter("CALL_TP_TXT", "Billable");
            tmpl.setTemplateParameter("GRACE_PER_DAYS_AOT", (BigDecimal) tempMap.get("GRACE_PER_DAYS_AOT"));
        } else {
            tmpl.setTemplateParameter("CALL_TP_TXT", "Contract");
            tmpl.setTemplateParameter("GRACE_PER_DAYS_AOT", (BigDecimal) tempMap.get("CONTR_GRACE_PER_DAYS_AOT"));
        }
        String dsContrNum = identifyDsContrNum(glblCmpyCd, NSZC061001Constant.PROCESS_MODE_CALL_CREATION, slsDate, fsrTMsg.svcMachMstrPk.getValue(), dsSvcCallTpCd);
        if (ZYPCommonFunc.hasValue(dsContrNum)) {

                tempMap = getPastDueInfo(pMsg);
                if (tempMap != null) {
                    tmpl.setTemplateParameter("CONTR_OPEN_AR", (BigDecimal) tempMap.get("CONTR_OPEN_AR"));
                    tmpl.setTemplateParameter("CONTR_PAST_DUE_BY", (BigDecimal) tempMap.get("CONTR_PAST_DUE_BY"));
                    tmpl.setTemplateParameter("CONTR_PAST_DUE_AMT", (BigDecimal) tempMap.get("CONTR_PAST_DUE_AMT"));
                }
            }
        // END 2022/12/27 S.Fujita [QC#60744, ADD]
        mail.setMailTemplate(tmpl);
        mail.postMail();
    }
    

    private String getManagerOfUsrAddress(String glblCmpyCd, String managerOfUsr) {
        S21_PSNTMsg inMsg = new S21_PSNTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.psnCd, managerOfUsr);
        S21_PSNTMsg s21PsnTMsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (s21PsnTMsg == null) {
            return null;
        }
        return s21PsnTMsg.emlAddr.getValue();
    }

    // START 2018/10/30 K.Kitachi [QC#28879, MOD]
//    private Map<String, Object> getNotificatonMailData(NSZC038001PMsg pMsg) {
    private Map<String, Object> getNotificatonMailData(NSZC038001PMsg pMsg, boolean isBllbl) {
    // END 2018/10/30 K.Kitachi [QC#28879, MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("fsrNum", pMsg.fsrNum.getValue());
        param.put("arCashApplyStsCd", new String[] {AR_CASH_APPLY_STS.PARTIAL, AR_CASH_APPLY_STS.UNAPPLIED });
        param.put("sysTs", this.sysTs);
        param.put("slsDt", pMsg.slsDt.getValue());
        // START 2018/10/30 K.Kitachi [QC#28879, ADD]
        param.put("arTrxTpCd", AR_TRX_TP.RECEIPT);
        // START 2022/12/27 S.Fujita [QC#60744, DEL]
        //if (isBllbl) {
        //    return (Map<String, Object>) this.ssmBatchClient.queryObject("getNotificatonMailDataForBllbl", param);
        //}
        // END 2022/12/27 S.Fujita [QC#60744, DEL]
        // END 2018/10/30 K.Kitachi [QC#28879, ADD]
        
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getNotificatonMailData", param);
    }

    private String getComment(S21NwfContext context, String fsrNum) {
        try {
            List<S21NwfProcess> procs = context.getProcessForBiz(WF_PROCESS_NM, fsrNum);
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizHistWorkItem> history = p.getHistory();
                for (S21NwfUtilBizHistWorkItem wi : history) {
                    String comment = wi.getComment();
                    if (hasValue(comment)) {
                        return comment;
                    }
                }
            }
        } catch (S21NwfSystemException e) {
            return null;
        }

        return null;
    }

    private void sendErrEmail(NSZC038001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // From Address
        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, FROM_ADDR_GRP_CD);
        fromGrp.setMailKey1("NSZ");
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();
        if (fromAddrList.size() == 0) {
            throw new S21AbendException(NSZM0627E, new String[] {"From Mail Address[ML_GRP_ID＝FROM0003]" });
        }

        // To Address
        S21MailGroup toGrp = new S21MailGroup(glblCmpyCd, ERR_TO_ADDR_GRP_CD);
        List<S21MailAddress> toAddrList = toGrp.getMailAddress();
        if (toAddrList.size() == 0) {
            throw new S21AbendException(NSZM0627E, new String[] {"To Mail Address[ML_GRP_ID＝NSZC1050]" });
        }

        // Mail Template
        S21MailTemplate tmpl = new S21MailTemplate(glblCmpyCd, ERR_MAIL_TEMP_CD);
        if (!hasValue(tmpl.getSubject()) || !hasValue(tmpl.getBody())) {
            throw new S21AbendException(NSZM0627E, new String[] {"Mail Template[ML_TMPL_ID=NSZC1050M002]" });
        }

        S21Mail mail = new S21Mail(glblCmpyCd);
        // set From Address
        mail.setFromAddress(fromAddrList.get(0));
        // set To Address
        mail.setToAddress(toAddrList.get(0));

        tmpl.setTemplateParameter("SYS_TS", this.sysTs);
        tmpl.setTemplateParameter("FSR_NUM", pMsg.fsrNum.getValue());
        tmpl.setTemplateParameter("WF_PROCESS_NM", WF_PROCESS_NM);
        tmpl.setTemplateParameter("ERR_MSG", getErrMsg());

        mail.setMailTemplate(tmpl);
        mail.postMail();
    }

    private String getErrMsg() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.errMsgList.size(); i++) {
            if (sb.length() > 0) {
                sb.append("\r\n");
            }
            sb.append(this.errMsgList.get(i));
        }
        return sb.toString();
    }
    // add end 2016/04/05 CSA Defect#895

    // START 2018/10/30 K.Kitachi [QC#28879, ADD]
    private BigDecimal countBllblTask(NSZC038001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        return (BigDecimal) this.ssmBatchClient.queryObject("countBllblTask", paramMap);
    }

    private Map<String, Object> getPastDueInfo(NSZC038001PMsg pMsg) {
        List<String> dsContrNumList = getDsContrNumList(pMsg);
        if (dsContrNumList == null || dsContrNumList.isEmpty()) {
            return null;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("slsDt", pMsg.slsDt.getValue());
        paramMap.put("arTrxTpInvoice", AR_TRX_TP.INVOICE);
        paramMap.put("arCashApplyStsUnapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        paramMap.put("arCashApplyStsPartial", AR_CASH_APPLY_STS.PARTIAL);
        paramMap.put("dsContrNumList", dsContrNumList);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getPastDueInfo", paramMap);
    }

    private List<String> getDsContrNumList(NSZC038001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("slsDt", pMsg.slsDt.getValue());
        paramMap.put("dsContrCatgWty", DS_CONTR_CATG.WARRANTY);
        return (List<String>) this.ssmBatchClient.queryObjectList("getContrNum", paramMap);
    }
    // END 2018/10/30 K.Kitachi [QC#28879, ADD]

    // 2019/02/04 CSA QC#28799 Add Start
    // mod start 2022/11/15 QC#60406
    //private void setCollectionText(NSZC038001PMsg pMsg, NSZC037001TokenObject tokenObj) {
    private void setCollectionText(NSZC038001PMsg pMsg, NSZC037001TokenObject tokenObj, String name) {
    // mod end 2022/11/15 QC#60406
        FSRTMsg fsrTMsg = getFsrTMsg(pMsg.glblCmpyCd.getValue(), pMsg.fsrNum.getValue());
        if (fsrTMsg == null) {
            return;
        }

        String lastUpdName = getLastUpdUserName(fsrTMsg);
        String rejectedUserName = getUserName(pMsg.glblCmpyCd.getValue(), pMsg.usrId.getValue());
        CLT_NOTE_TPTMsg cltNoteTp = new CLT_NOTE_TPTMsg();
        String colNoteSubjTxt = "";
        // add start 2022/11/15 QC#60406
        String cltNoteTpCd = null;
        String dtlNoteTxtTmpl = null;
        String dtlNoteTxt = null;
        if (NSZC038001Constant.MODE_APPROVE.equals(name)) {
            cltNoteTpCd = CLT_NOTE_TP.APPROVED_CREDIT_REVIEW;
            ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd,pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, cltNoteTpCd);
            cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);
            if (cltNoteTp != null && ZYPCommonFunc.hasValue(cltNoteTp.cltNoteTpDescTxt)) {
                colNoteSubjTxt = cltNoteTp.cltNoteTpDescTxt.getValue();
            }
            
            dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NSZC1050_APVL_CLT_DTL_NOTE_TXT, pMsg.glblCmpyCd.getValue());
            if (dtlNoteTxtTmpl == null) {
                dtlNoteTxtTmpl = NSZC105001_APVL_DTL_NOTE_TXT_TMPL;
            }
            dtlNoteTxt = String.format(dtlNoteTxtTmpl, tokenObj.getComment(), pMsg.fsrNum.getValue(), tokenObj.getCallAmt(), lastUpdName, rejectedUserName);
        }else if (NSZC038001Constant.MODE_REJECT.equals(name)) {
        // add end 2022/11/15 QC#60406
            cltNoteTpCd = CLT_NOTE_TP.REJECTED_CREDIT_CHECK;
            // del start 2022/11/15 QC#60406
            // CLT_NOTE_TPTMsg cltNoteTp = new CLT_NOTE_TPTMsg();
            // del end 2022/11/15 QC#60406
            ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd,pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, cltNoteTpCd);
            cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);
            dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NSZC1050_CLT_DTL_NOTE_TXT, pMsg.glblCmpyCd.getValue());
            if (cltNoteTp != null && ZYPCommonFunc.hasValue(cltNoteTp.cltNoteTpDescTxt)) {
                colNoteSubjTxt = cltNoteTp.cltNoteTpDescTxt.getValue();
            }
            // mod start 2022/11/15 QC#60406
            //String dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NSZC1050_CLT_DTL_NOTE_TXT, pMsg.glblCmpyCd.getValue());
            dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NSZC1050_CLT_DTL_NOTE_TXT, pMsg.glblCmpyCd.getValue());
            // mod end 2022/11/15 QC#60406
            if (dtlNoteTxtTmpl == null) {
                dtlNoteTxtTmpl = NSZC105001_DTL_NOTE_TXT_TMPL;
                }
            // mod start 2022/11/15 QC#60406
            //String dtlNoteTxt = String.format(dtlNoteTxtTmpl, tokenObj.getComment(), pMsg.fsrNum.getValue(), tokenObj.getCallAmt(), lastUpdName, rejectedUserName);
            dtlNoteTxt = String.format(dtlNoteTxtTmpl, tokenObj.getComment(), pMsg.fsrNum.getValue(), tokenObj.getCallAmt(), lastUpdName, rejectedUserName);
            // mod end 2022/11/15 QC#60406
        } 
        CLT_NOTE_DTLTMsg cltNoteDtlMsg = new CLT_NOTE_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltNoteDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLT_NOTE_DTL_SQ"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctCd, fsrTMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctTpCd, CLT_ACCT_TP.BILL_TO);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratUsrId, pMsg.usrId.getValue());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteSubjTxt, colNoteSubjTxt);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteTpCd, cltNoteTpCd);

        int dtlNoteMaxLength = cltNoteDtlMsg.getAttr("dtlNoteTxt").getDigit();
        if (dtlNoteTxt.length() > dtlNoteMaxLength) {
            dtlNoteTxt = dtlNoteTxt.substring(0, dtlNoteMaxLength);
        }
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.dtlNoteTxt, dtlNoteTxt);
        
        EZDTBLAccessor.create(cltNoteDtlMsg);
    }
    

    private FSRTMsg getFsrTMsg(String glblCmpyCd, String fsrNum) {

        FSRTMsg fsrTMsg = new FSRTMsg();
        ZYPEZDItemValueSetter.setValue(fsrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(fsrTMsg.fsrNum, fsrNum);

        return (FSRTMsg) S21FastTBLAccessor.findByKey(fsrTMsg);
    }

    private String getLastUpdUserName(FSRTMsg fsrTMsg) {

        SVC_TASKTMsg condSvcTaskTMsg = new SVC_TASKTMsg();
        condSvcTaskTMsg .setSQLID("005");
        condSvcTaskTMsg.setConditionValue("glblCmpyCd01", fsrTMsg.glblCmpyCd.getValue());
        condSvcTaskTMsg.setConditionValue("fsrNum01", fsrTMsg.fsrNum.getValue());

        SVC_TASKTMsgArray svcTaskTMsgArray = (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(condSvcTaskTMsg);
        String svcTaskNum = null;
        if (svcTaskTMsgArray != null && svcTaskTMsgArray.getValidCount() > 0) {
            svcTaskNum = svcTaskTMsgArray.no(0).svcTaskNum.getValue();
        }

        String lastUpdUsrId = null;
        if (ZYPCommonFunc.hasValue(svcTaskNum)) {
            SVC_MEMOTMsg condSvcMemoTMsg = new SVC_MEMOTMsg();
            condSvcMemoTMsg.setSQLID("012");
            condSvcMemoTMsg.setConditionValue("glblCmpyCd01", fsrTMsg.glblCmpyCd.getValue());
            condSvcMemoTMsg.setConditionValue("svcTaskNum01", svcTaskNum);

            SVC_MEMOTMsgArray svcMemoTMsgArray = (SVC_MEMOTMsgArray) EZDTBLAccessor.findByCondition(condSvcMemoTMsg);
            if (svcMemoTMsgArray != null && svcMemoTMsgArray.getValidCount() > 0) {
                for (int i = 0; i < svcMemoTMsgArray.getValidCount(); i++) {
                    if (ZYPCommonFunc.hasValue(svcMemoTMsgArray.no(i).lastUpdUsrId)) {
                        lastUpdUsrId = svcMemoTMsgArray.no(i).lastUpdUsrId.getValue();
                        break;
                    }
                }
            }
        } else {
            lastUpdUsrId = fsrTMsg.ezUpUserID.getValue();
        }

        if (!ZYPCommonFunc.hasValue(lastUpdUsrId)) {
            lastUpdUsrId = fsrTMsg.ezUpUserID.getValue();
        }
        String lastUpdUserNm = null;
        if (ZYPCommonFunc.hasValue(lastUpdUsrId)) {
            lastUpdUserNm = getUserName(fsrTMsg.glblCmpyCd.getValue(), lastUpdUsrId);
        }
        if (!ZYPCommonFunc.hasValue(lastUpdUserNm)) {
            lastUpdUserNm = fsrTMsg.ezUpUserID.getValue();
        }

        return lastUpdUserNm;
    }

    private String getUserName(String glblCmpyCd, String userId) {

        String rslt = null;
        AUTH_PSNTMsg authPsnCondTMsg = new AUTH_PSNTMsg();
        authPsnCondTMsg.setSQLID("053");
        authPsnCondTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        authPsnCondTMsg.setConditionValue("usrNm01", userId);

        AUTH_PSNTMsgArray authPsnTMsgArray = (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(authPsnCondTMsg);
        if (authPsnTMsgArray != null && authPsnTMsgArray.getValidCount() > 0) {
            AUTH_PSNTMsg authPsnTMsg = authPsnTMsgArray.no(0);
            StringBuilder userFullName = new StringBuilder("");
            if (ZYPCommonFunc.hasValue(authPsnTMsg.firstNm)) {
                userFullName.append(authPsnTMsg.firstNm.getValue() + " ");
            }
            // No need middle name -->
//            if (ZYPCommonFunc.hasValue(authPsnTMsg.midNm)) {
//                userFullName.append(authPsnTMsg.midNm.getValue() + " ");
//            }
            // No need middle name <--
            if (ZYPCommonFunc.hasValue(authPsnTMsg.lastNm)) {
                userFullName.append(authPsnTMsg.lastNm.getValue());
            }
            rslt = userFullName.toString().trim();
        } else {
            rslt = userId;
        }

        return rslt;
    }
    // 2019/02/04 CSA QC#28799 Add End
    // START 2022/1/27 R.Onozuka [QC#56182, ADD]
    private String identifyDsContrNum (String glblCmpyCd, String modeCd, String slsDate, BigDecimal svcMachMstrPk, String dsSvcCallTpCd){
        
        String dsContrNum = null;
        String sysTs = ZYPDateUtil.getCurrentSystemTime(FORMAT_TS);
        String sysTm = sysTs.substring(LEN_DT, sysTs.length());
        
        NSZC061001 billCodeApi = new NSZC061001();
        NSZC061001PMsg pmsg = new NSZC061001PMsg();
        pmsg.glblCmpyCd.setValue(glblCmpyCd);
        pmsg.xxModeCd.setValue(modeCd);
        pmsg.startDt.setValue(slsDate);
        pmsg.startTm.setValue(sysTm);
        pmsg.svcMachMstrPk.setValue(svcMachMstrPk);
        pmsg.dsSvcCallTpCd.setValue(dsSvcCallTpCd);
        
        // execute NSZC061001
        billCodeApi.execute(pmsg, ONBATCH_TYPE.ONLINE);
        if (!S21ApiUtil.isXxMsgId(pmsg)) {
            BigDecimal dsContrPk = pmsg.dsContrPk.getValue();
            if (hasValue(dsContrPk)) {
                DS_CONTRTMsg tMsg = getDsContr(glblCmpyCd, dsContrPk);
                dsContrNum = tMsg.dsContrNum.getValue();
            }
        }
    
        return dsContrNum;
    }
    
    private DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }
    
    private String getDsSvcCallTpCd(String glblCmpyCd, String fsrNum){
    
        SVC_TASKTMsg condSvcTaskTMsg = new SVC_TASKTMsg();
        condSvcTaskTMsg.setSQLID("005");
        condSvcTaskTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condSvcTaskTMsg.setConditionValue("fsrNum01", fsrNum);
        SVC_TASKTMsgArray svcTaskTMsgArray = (SVC_TASKTMsgArray) EZDTBLAccessor.findByCondition(condSvcTaskTMsg);
        
        String svcTaskNum = null;
        if (svcTaskTMsgArray != null && svcTaskTMsgArray.getValidCount() > 0) {
            svcTaskNum = svcTaskTMsgArray.no(0).svcTaskNum.getValue();
        }
        
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcTaskNum, svcTaskNum);
        tMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        
        return tMsg.dsSvcCallTpCd.getValue();
    }
    // END 2022/1/27 R.Onozuka [QC#56182, ADD]
}
