/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC501001;

import static com.canon.cusa.s21.api.NFC.NFZC501001.constant.NFZC501001Constant.AR_REFUND_WF_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_APPROVE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_RCPT_RF_RSNTMsg;
import business.db.AR_RF_RQSTTMsg;
import business.db.AR_RF_RQST_DTLTMsg;
import business.db.AR_RF_RQST_DTLTMsgArray;
import business.db.AR_TRX_BALTMsg;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;
import business.db.CLT_NOTE_DTLTMsg;
import business.db.CLT_NOTE_TPTMsg;

import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizHistWorkItem;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_NOTE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole;
import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Workflow Status update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         J.Kim           Create          N/A
 * 2018/04/19   Hitachi         Y.Takeno        Update          QC#20940
 * 2018/07/17   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/31   Hitachi         E.Kameishi      Update          QC#27462
 * 2020/06/19   CITS            R.Kurahashi     Update          QC#56956
 * 2022/07/25   Hitachi         A.Kohinata      Update          QC#57417
 * 2022/11/07   Hitachi         S.Fujita        Update          QC#60406
 *</pre>
 */
public class NFZC501001 implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>, S21NwfRejectEvent<S21NwfContext, S21NwfToken>, S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {

    /**
     * Approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        S21NwfUtilTokenObj tokenObj = (S21NwfUtilTokenObj) token.getTokenObj();
        // START 2018/07/31 E.Kameishi [QC#27462,MOD]
        String userId = context.getUserID();
        String documentId = tokenObj.getCondNum2().toString();
        // START 2020/06/19 R.Kurahashi [QC#56956,MOD]
        //String apvrUsrId = getWfGroups(ZYPCodeDataUtil.getVarCharConstValue(AR_REFUND_WF_ID, tokenObj.getGlblCmpyCd()), documentId);
        String apvrUsrId = getApvrUsrId(ZYPCodeDataUtil.getVarCharConstValue(AR_REFUND_WF_ID, tokenObj.getGlblCmpyCd()), documentId);
        // END 2020/06/19 R.Kurahashi [QC#56956,MOD]
        // mod start 2022/11/07 QC#60406
        //updateWfRqst(null, tokenObj, userId, apvrUsrId);
        updateWfRqst(null, tokenObj, userId, apvrUsrId, context);
        // mod end 2022/11/07 QC#60406
        // END 2018/07/31 E.Kameishi [QC#27462,MOD]
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
        // del start 2022/11/02 QC#60406
        // S21NwfUtilTokenObj tokenObj = (S21NwfUtilTokenObj)token.getTokenObj();
        // String userId = context.getUserID();
        // updateWfRqst(AR_DS_WF_STS.REJECTED, tokenObj, userId, null);
        // del end 2022/11/02 QC#60406
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
        // mod start 2022/11/02 QC#60406
        // if (S21NwfConst.SIGNAL_APPROVE.equals(name)) {
        if (SIGNAL_APPROVE.equals(name) || ("REJECT".equals(name))) {
        // mod end 2022/11/02 QC#60406
            S21NwfUtilTokenObj tokenObj = (S21NwfUtilTokenObj) token.getTokenObj();
            String userId = context.getUserID();
            // mod start 2022/11/02 QC#60406
            // updateWfRqst(AR_DS_WF_STS.APPROVED, tokenObj, userId, null);
            if (SIGNAL_APPROVE.equals(name)) {
                updateWfRqst(AR_DS_WF_STS.APPROVED, tokenObj, userId, null, context);
            } else if ("REJECT".equals(name)) {
                updateWfRqst(AR_DS_WF_STS.REJECTED, tokenObj, userId, null, context);
            }
            // mod end 2022/11/02 QC#60406
        }
        return;
    }
    // add start 2022/11/02 QC#60406
    private String getWfComment(S21NwfContext context, String docId) {
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        String separator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();    
        try {
            String wfId = ZYPCodeDataUtil.getVarCharConstValue("AR_REFUND_WF_ID", glblCmpyCd);
            List<S21NwfProcess> procs = context.getProcessForBiz(wfId, docId);
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizHistWorkItem> history = p.getHistory();
                for (S21NwfUtilBizHistWorkItem wi : history) {
                    if (wi.isApprovable()) {
                        if (hasValue(sb.toString())) {
                            sb.append(separator);
                        }
                        sb.append(wi.getWorkItemName());
                        sb.append(":");
                        String wfComment = wi.getComment();
                        if (hasValue(wfComment)) {
                            sb.append(wfComment);
                        }
                        String userNm = getUserNm(glblCmpyCd, wi.getActOpUser());
                        if (hasValue(userNm)) {
                            sb.append(" By ");
                            sb.append(wi.getActOpUser());
                            sb.append(" ");
                            sb.append(userNm);
                        }
                    }
                }
            }
            return sb.toString();

        } catch (S21NwfSystemException e) {
            return "";
        }
    }
    // add end 2022/11/02 QC#60406
    
    /**
     * updateWfRqst
     * @param apDsWfSts String
     * @param tokenObj S21NwfUtilTokenObj
     * @param glblCmpyCd String
     * @param userId String
     */
    // mod start 2022/11/02 QC#60406
    // private void updateWfRqst(String apDsWfSts, S21NwfUtilTokenObj tokenObj, String userId, String apvrUsrId) {
    private void updateWfRqst(String apDsWfSts, S21NwfUtilTokenObj tokenObj, String userId, String apvrUsrId, S21NwfContext context) {
     // mod end 2022/11/02 QC#60406
        String glblCmpyCd = tokenObj.getGlblCmpyCd();
        BigDecimal arRfRqstPk = tokenObj.getCondNum2();
        AR_RF_RQSTTMsg arRfRqstTMsg = findArPfRqst(glblCmpyCd, arRfRqstPk);
        if (arRfRqstTMsg != null) {
            // START 2018/07/31 E.Kameishi [QC#27462,MOD]
            if (apDsWfSts != null) {
                setValue(arRfRqstTMsg.arDsWfStsCd, apDsWfSts);
                setValue(arRfRqstTMsg.arRfApvlUsrId, userId);
                setValue(arRfRqstTMsg.arRfApvlUsrNm, getUserNm(glblCmpyCd, userId));
            } else if (apvrUsrId != null && !apvrUsrId.isEmpty()) {
                setValue(arRfRqstTMsg.arRfApvlUsrId, apvrUsrId);
                // START 2020/06/19 R.Kurahashi [QC#56956,MOD]
                //setValue(arRfRqstTMsg.arRfApvlUsrNm, ZYPCodeDataUtil.getVarCharConstValue(apvrUsrId, glblCmpyCd));
                setValue(arRfRqstTMsg.arRfApvlUsrNm, getUserNm(glblCmpyCd, apvrUsrId));
                // END 2020/06/19 R.Kurahashi [QC#56956,MOD]
            }
            // END 2018/07/31 E.Kameishi [QC#27462,MOD]
            EZDTBLAccessor.update(arRfRqstTMsg);    
            // add start 2022/11/02 QC#60406
            String wfComment = null;
            wfComment = getWfComment(context, tokenObj.getCondNum2().toString());
            // add end 2022/11/02 QC#60406
            // START 2018/04/19 [QC#20940, ADD]
            if (AR_DS_WF_STS.APPROVED.equals(apDsWfSts)) {
                // mod start 2022/11/02 QC#60406
                // createCltNoteDtl(arRfRqstTMsg, glblCmpyCd, true);
                createCltNoteDtl(arRfRqstTMsg, glblCmpyCd, true, wfComment);
                // mod end 2022/11/02 QC#60406
            } else if (AR_DS_WF_STS.REJECTED.equals(apDsWfSts)) {
                // mod start 2022/11/02 QC#60406
                // createCltNoteDtl(arRfRqstTMsg, glblCmpyCd, false);
                createCltNoteDtl(arRfRqstTMsg, glblCmpyCd, false, wfComment);
                // mod end 2022/11/02 QC#60406
            }
            // END   2018/04/19 [QC#20940, ADD]
        }
    }

    /**
     * findArPfRqst
     * @param glblCmpyCd String
     * @param arRfRqstPk BigDecimal
     * @return AR_RF_RQSTTMsg
     */
    private AR_RF_RQSTTMsg findArPfRqst(String glblCmpyCd, BigDecimal arRfRqstPk) {
        AR_RF_RQSTTMsg tmsg = new AR_RF_RQSTTMsg();
        setValue(tmsg.glblCmpyCd, glblCmpyCd);
        setValue(tmsg.arRfRqstPk, arRfRqstPk);
        tmsg = (AR_RF_RQSTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
        if (tmsg != null && AR_DS_WF_STS.PENDING.equals(tmsg.arDsWfStsCd.getValue())) {
            return tmsg;
        }
        return null;
    }

    /**
     * getUserNm
     * @param glblCmpyCd String
     * @param userId String
     * @return String
     */
    private String getUserNm(String glblCmpyCd, String userId) {
        AUTH_PSNTMsg inMsg = new AUTH_PSNTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("usrNm01", userId);
        inMsg.setMaxCount(1);
        inMsg.setSQLID("053");
        AUTH_PSNTMsgArray outMsg = (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsg.length() != 0) {
            return ZYPCommonFunc.concatString(outMsg.no(0).firstNm.getValue(), " ", outMsg.no(0).lastNm.getValue());
        }
        return null;
    }

    // START 2018/04/19 [QC#20940, ADD]

    private AR_RF_RQST_DTLTMsgArray findArPfRqstDtl(String glblCmpyCd, BigDecimal arRfRqstPk) {
        AR_RF_RQST_DTLTMsg tmsg = new AR_RF_RQST_DTLTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("arRfRqstPk01", arRfRqstPk);
        return (AR_RF_RQST_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(tmsg);
    }
    // mod start 2022/11/07 QC#60406
    //private void createCltNoteDtl(AR_RF_RQSTTMsg rqstTMsg, String glblCmpyCd, boolean isApproved) {
    private void createCltNoteDtl(AR_RF_RQSTTMsg rqstTMsg, String glblCmpyCd, boolean isApproved, String wfComment) {
    // mod end 2022/11/07 QC#60406
        CLT_NOTE_TPTMsg cltNoteTp = new CLT_NOTE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd, glblCmpyCd);
        if (isApproved) {
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.APPROVED_REFUNDS);
        } else {
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.REJECTED_REFUNDS);
        }
        cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);

        String arTrxNums = "";
        AR_RF_RQST_DTLTMsgArray dtlArray = findArPfRqstDtl(glblCmpyCd, rqstTMsg.arRfRqstPk.getValue());
        if (dtlArray != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0 ; i < dtlArray.getValidCount() ; i++) {
                AR_RF_RQST_DTLTMsg rqstDtlTMsg = dtlArray.no(i);
                if (i != 0) {
                    builder.append(",");
                }

                // START 2018/07/17 [QC#26989, MOD]
                // builder.append(rqstDtlTMsg.arTrxNum.getValue());
                String arCustRefNum = rqstDtlTMsg.arTrxNum.getValue();
                AR_TRX_BALTMsg arTrxBalTMsg = findArTrxBal(rqstDtlTMsg.arTrxBalPk.getValue());
                if (arTrxBalTMsg != null) {
                    if (ZYPCommonFunc.hasValue(arTrxBalTMsg.arCustRefNum.getValue())) {
                        arCustRefNum = arTrxBalTMsg.arCustRefNum.getValue();
                    }
                }
                builder.append(arCustRefNum);
                // END   2018/07/17 [QC#26989, MOD]
            }
            arTrxNums = builder.toString();
        }

        String reason = "";
        // mod start 2022/07/25 QC#57417
        //AR_RCPT_RF_RSNTMsg arRcptRfRsn = new AR_RCPT_RF_RSNTMsg();
        //ZYPEZDItemValueSetter.setValue(arRcptRfRsn.glblCmpyCd, glblCmpyCd);
        //ZYPEZDItemValueSetter.setValue(arRcptRfRsn.arRcptRfRsnCd, rqstTMsg.arRcptRfRsnCd);
        //arRcptRfRsn = (AR_RCPT_RF_RSNTMsg) S21CodeTableAccessor.findByKey(arRcptRfRsn);
        //if (arRcptRfRsn != null) {
        //    reason = arRcptRfRsn.arRcptRfRsnDescTxt.getValue();
        //}
        if (dtlArray != null) {
            List<String> arRcptRfRsnCdList = new ArrayList<String>();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < dtlArray.getValidCount(); i++) {
                String arRcptRfRsnCd = dtlArray.no(i).arRcptRfRsnCd.getValue();

                if (!arRcptRfRsnCdList.contains(arRcptRfRsnCd)) {
                    AR_RCPT_RF_RSNTMsg arRcptRfRsn = new AR_RCPT_RF_RSNTMsg();
                    ZYPEZDItemValueSetter.setValue(arRcptRfRsn.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(arRcptRfRsn.arRcptRfRsnCd, arRcptRfRsnCd);
                    arRcptRfRsn = (AR_RCPT_RF_RSNTMsg) S21CodeTableAccessor.findByKey(arRcptRfRsn);
                    if (arRcptRfRsn != null) {
                        if (builder.length() > 0) {
                            builder.append(",");
                        }
                        builder.append(arRcptRfRsn.arRcptRfRsnDescTxt.getValue());
                    }
                    arRcptRfRsnCdList.add(arRcptRfRsnCd);
                }
            }
            reason = builder.toString();
        }
        // mod end 2022/07/25 QC#57417

        String dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue("NFZC5010_CLT_DTL_NOTE_TXT", glblCmpyCd);
        // mod end 2022/11/07 QC#60406
        //String dtlNoteTxt = String.format(dtlNoteTxtTmpl, rqstTMsg.arRcptRfCmntTxt.getValue(), reason, rqstTMsg.arRfChkCmntTxt.getValue(), rqstTMsg.arRfRqstPk.getValue().toPlainString(), arTrxNums, rqstTMsg.dealRfAmt.getValue());
        String dtlNoteTxt = String.format(dtlNoteTxtTmpl, rqstTMsg.arRcptRfCmntTxt.getValue(), reason, rqstTMsg.arRfChkCmntTxt.getValue(), wfComment, rqstTMsg.arRfRqstPk.getValue().toPlainString(), arTrxNums, rqstTMsg.dealRfAmt.getValue());
        // mod end 2022/11/07 QC#60406
        if (dtlNoteTxt.length() > 4000) {
            dtlNoteTxt = dtlNoteTxt.substring(0, 4000);
        }

        CLT_NOTE_DTLTMsg cltNoteDtlMsg = new CLT_NOTE_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltNoteDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLT_NOTE_DTL_SQ"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctCd, rqstTMsg.billToCustAcctCd.getValue());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctTpCd, CLT_ACCT_TP.BILL_TO);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratUsrId, rqstTMsg.arRfRqstUsrId.getValue());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteSubjTxt, cltNoteTp.cltNoteTpDescTxt);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteTpCd, cltNoteTp.cltNoteTpCd);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.dtlNoteTxt, dtlNoteTxt);

        EZDTBLAccessor.create(cltNoteDtlMsg);
    }
    // END   2018/04/19 [QC#20940, ADD]

    // START 2018/07/17 [QC#26989, ADD]
    private AR_TRX_BALTMsg findArTrxBal(BigDecimal arTrxBalPk) {
        AR_TRX_BALTMsg tmsg = new AR_TRX_BALTMsg();
        tmsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        
        tmsg.arTrxBalPk.setValue(arTrxBalPk);
    
        return (AR_TRX_BALTMsg) S21ApiTBLAccessor.findByKey(tmsg);
    }
    // END   2018/07/17 [QC#26989, ADD]
    // START 2018/07/31 E.Kameishi [QC#27462,ADD]
    /**
     * getWfGroups
     * @param wfId String
     * @param documentId String
     * @return String
     * @throws S21NwfException S21NwfException
     */
    private String getWfGroups(String wfId, String documentId) throws S21NwfException {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = factory.getContex();
        List<S21NwfProcess> procs = context.getProcessForBiz(wfId, documentId);
        String wfGroups = "";
        for (S21NwfProcess proc : procs) {
            S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
            List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
            boolean isNext = false;
            for (S21NwfUtilBizWorkItem wi : tasks) {
                if (wi.isApprovable()) {
                    if (isNext == true) {
                        wfGroups = wi.getGroups().get(0);
                        break;
                    } else if (wi.isComplete() == false) {
                        isNext = true;
                        continue;
                    }
                }
            }
        }
        return wfGroups;
    }
    // END 2018/07/31 E.Kameishi [QC#27462,ADD]
    // START 2020/06/19 R.Kurahashi [QC#56956,ADD]
    /**
     * getApvrUsrId
     * @param wfId String
     * @param documentId String
     * @return String
     * @throws S21NwfException S21NwfException
     */
    private String getApvrUsrId(String wfId, String documentId) throws S21NwfException {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = factory.getContex();
        List<S21NwfProcess> procs = context.getProcessForBiz(wfId, documentId);
        String apvrUsrId = "";
        for (S21NwfProcess proc : procs) {
            S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
            List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
            boolean isNext = false;
            for (S21NwfUtilBizWorkItem wi : tasks) {
                if (wi.isApprovable()) {
                    if (isNext == true) {
                        List<S21NwfUserRole> users = wi.getToUsers();
                        for (S21NwfUserRole user : users) {
                            apvrUsrId = user.getUserRole();
                            break;
                        }
                        break;
                    } else if (wi.isComplete() == false) {
                        isNext = true;
                        continue;
                    }
                }
            }
        }
        return apvrUsrId;
    }
    // END 2020/06/19 R.Kurahashi [QC#56956,ADD]
}
