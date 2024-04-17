/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFD.NFZC500001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.COND_END_NO_USER;
import static com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst.SIGNAL_APPROVE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AR_ADJ_RSNTMsg;
import business.db.AR_ADJ_TPTMsg;
import business.db.AR_TRX_BALTMsg;
import business.db.AR_WRT_OFF_NOTETMsg;
import business.db.AR_WRT_OFF_RQSTTMsg;
import business.db.AR_WRT_OFF_RQST_DTLTMsg;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;
import business.db.CLT_NOTE_DTLTMsg;
import business.db.CLT_NOTE_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_NOTE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizHistWorkItem;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizToken;

/**
 *<pre>
 * Write-Off WF Status Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Hitachi         T.Tsuchida      Create          N/A
 * 2016/05/16   CSAI            K.Uramori       Update          When rejected, PROC_STS needs to be updated.
 * 2018/03/29   Hitachi         J.Kim           Update          QC#21721
 * 2018/04/19   Hitachi         Y.Takeno        Update          QC#20940
 * 2018/07/17   Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/20   Hitachi         Y.Takeno        Update          QC#26989
 * 2019/05/23   Hitachi         H.Umeda         Update          QC#50449
 * 2019/07/29   Hitachi         Y.Takeno        Update          QC#52116
 * 2022/10/21   Hitachi         S.Fujita        Update          QC#60401
 *</pre>
 */
public class NFZC500001 implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>,
                                        S21NwfRejectEvent<S21NwfContext, S21NwfToken>,
                                        S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {

    /**
     * Approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {

        // START 2018/03/28 J.Kim [QC#21721,ADD]
        NFZC500001TokenObject tokenObj = (NFZC500001TokenObject) token.getTokenObj();
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        String aprvrUser = null;

        S21NwfUtilBizToken utilToken = new S21NwfUtilBizToken(token);
        List<S21NwfUserRole> approvers = utilToken.getNextApprovers();
        if (approvers.size() > 0) {
            aprvrUser = approvers.get(0).getUserRole();
        }

        List<NFZC500001TokenObjectLine> lines = tokenObj.getLines();
        for (NFZC500001TokenObjectLine line : lines) {
            AR_WRT_OFF_RQSTTMsg arWrtOffRqstTMsg = findArWrtOffRqst(line);
            if (arWrtOffRqstTMsg != null && aprvrUser != null) {
                setValue(arWrtOffRqstTMsg.wrtOffApvlUsrId, aprvrUser);
                setValue(arWrtOffRqstTMsg.wrtOffApvlUsrNm, getUserNm(glblCmpyCd, aprvrUser));
                EZDTBLAccessor.update(arWrtOffRqstTMsg);
            } else {
                continue;
            }
        }
        // END 2018/03/28 J.Kim [QC#21721,ADD]
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
        NFZC500001TokenObject tokenObj = (NFZC500001TokenObject) token.getTokenObj();

        // START 2018/04/19 [QC#20940, ADD]
        Map<String, Map<String, Object>> rqstGrpMap = new HashMap<String, Map<String, Object>>();
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        // END   2018/04/19 [QC#20940, ADD]

        List<NFZC500001TokenObjectLine> lines = tokenObj.getLines();
        for (NFZC500001TokenObjectLine line : lines) {
            AR_WRT_OFF_RQSTTMsg arWrtOffRqstTMsg = findArWrtOffRqst(line);
            if (arWrtOffRqstTMsg != null) {
                setValue(arWrtOffRqstTMsg.arDsWfStsCd, AR_DS_WF_STS.REJECTED);
// START 2022/10/13 [QC#60401, DEL]
//                setValue(arWrtOffRqstTMsg.arWrtOffNoteTxt, tokenObj.getComment());
// END 2022/10/13 [QC#60401, DEL]
                //---- start 2016/05/16
                setValue(arWrtOffRqstTMsg.procStsCd, PROC_STS.COMPLEATED);
                //---- end 2016/05/16
                EZDTBLAccessor.update(arWrtOffRqstTMsg);
            } else {
                continue;
            }

            AR_TRX_BALTMsg arTrxBalTMsg = findArTrxBal(line.getArTrxBalPk());
            if (arTrxBalTMsg != null
                    && hasValue(arWrtOffRqstTMsg.wrtOffApplyAmt)) {
                if (hasValue(arTrxBalTMsg.dealApplyAdjRsvdAmt)) {
                    // START 2019/07/29 [QC#52116, MOD]
                    // setValue(arTrxBalTMsg.dealApplyAdjRsvdAmt, arTrxBalTMsg.dealApplyAdjRsvdAmt.getValue().subtract(arWrtOffRqstTMsg.wrtOffApplyAmt.getValue()));
                    setValue(arTrxBalTMsg.dealApplyAdjRsvdAmt, BigDecimal.ZERO);
                    // END   2019/07/29 [QC#52116, MOD]
                }
                if (hasValue(arTrxBalTMsg.funcApplyAdjRsvdAmt)) {
                    // START 2019/07/29 [QC#52116, MOD]
                    // setValue(arTrxBalTMsg.funcApplyAdjRsvdAmt, arTrxBalTMsg.funcApplyAdjRsvdAmt.getValue().subtract(arWrtOffRqstTMsg.wrtOffApplyAmt.getValue()));
                    setValue(arTrxBalTMsg.funcApplyAdjRsvdAmt, BigDecimal.ZERO);
                    // END   2019/07/29 [QC#52116, MOD]
                }
                EZDTBLAccessor.update(arTrxBalTMsg);
            }

            // START 2018/04/19 [QC#20940, ADD]
// START 2022/10/13 [QC#60401, DEL]
//            AR_WRT_OFF_RQST_DTLTMsg arWrtOffRqstDtlTMsg = findArWrtOffRqstDtl(line);
//            updateCltNoteData(rqstGrpMap, arWrtOffRqstTMsg, arWrtOffRqstDtlTMsg, glblCmpyCd, false);
// END 2022/10/13 [QC#60401, DEL]
            // END  2018/04/19 [QC#20940, ADD]
        }

        // START 2018/04/19 [QC#20940, ADD]
// START 2022/10/13 [QC#60401, DEL]
//        for (String rqstGepCd : rqstGrpMap.keySet()) {
//            Map<String, Object> wrtOffRqstDataMap = rqstGrpMap.get(rqstGepCd);
//            createCltNoteDtl(wrtOffRqstDataMap, glblCmpyCd, false);
//        }
// END 2022/10/13 [QC#60401, DEL]
        // END   2018/04/19 [QC#20940, ADD]
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
        NFZC500001TokenObject tokenObj = (NFZC500001TokenObject) token.getTokenObj();
        //if (!SIGNAL_APPROVE.equals(name)) {
        //    return;
        //}
        // START 2022/10/13 [QC#60401, MOD]
        // approve
        //if (SIGNAL_APPROVE.equals(name)) {
        if (SIGNAL_APPROVE.equals(name) || ("REJECT".equals(name))) {
        // END 2022/10/13 [QC#60401, MOD]
        
            // START 2018/04/19 [QC#20940, ADD]
            Map<String, Map<String, Object>> rqstGrpMap = new HashMap<String, Map<String, Object>>();
            String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
            // END   2018/04/19 [QC#20940, ADD]

            List<NFZC500001TokenObjectLine> lines = tokenObj.getLines();
            for (NFZC500001TokenObjectLine line : lines) {
                // START 2022/10/13 [QC#60401, ADD]
                String arWrtOffNoteTxt = null;
                String wfComment = null;
                // END 2022/10/13 [QC#60401, ADD]
                AR_WRT_OFF_RQSTTMsg arWrtOffRqstTMsg = findArWrtOffRqst(line);
                if (arWrtOffRqstTMsg != null) {
                    // START 2022/10/13 [QC#60401, MOD]
                    arWrtOffNoteTxt = arWrtOffRqstTMsg.arWrtOffNoteTxt.getValue();
                    if (SIGNAL_APPROVE.equals(name)) {
                        setValue(arWrtOffRqstTMsg.arDsWfStsCd, AR_DS_WF_STS.APPROVED);
                    } else if ("REJECT".equals(name)) {
                        setValue(arWrtOffRqstTMsg.arWrtOffNoteTxt, tokenObj.getComment());
                    }
                    // END 2022/10/13 [QC#60401, MOD]
                    EZDTBLAccessor.update(arWrtOffRqstTMsg);
                } else {
                    continue;
                }
    
                AR_WRT_OFF_RQST_DTLTMsg arWrtOffRqstDtlTMsg = findArWrtOffRqstDtl(line);
                if (arWrtOffRqstDtlTMsg != null) {
                    // START 2022/10/13 [QC#60401, MOD]
                    //setValue(arWrtOffRqstDtlTMsg.arDsWfCmntTxt, tokenObj.getComment());  // TODO comment is not set. need to ask WF team.
                    wfComment = getWfComment(context, arWrtOffRqstTMsg.wrtOffRqstGrpCd.getValue());
                    int length = arWrtOffRqstDtlTMsg.getAttr("arDsWfCmntTxt").getDigit();
                    if (wfComment.length() > length) {
                        setValue(arWrtOffRqstDtlTMsg.arDsWfCmntTxt, wfComment.substring(0, length));
                    } else {
                        setValue(arWrtOffRqstDtlTMsg.arDsWfCmntTxt, wfComment);
                    }
                    // END 2022/10/13 [QC#60401, MOD]
                    EZDTBLAccessor.update(arWrtOffRqstDtlTMsg);
                }

                // START 2018/04/19 [QC#20940, ADD]
                // START 2022/10/13 [QC#60401, MOD]
                String comment = getComment(arWrtOffNoteTxt, wfComment);
                if (SIGNAL_APPROVE.equals(name)) {
                    updateCltNoteData(rqstGrpMap, arWrtOffRqstTMsg, arWrtOffRqstDtlTMsg, glblCmpyCd, true, comment);
                } else {
                    updateCltNoteData(rqstGrpMap, arWrtOffRqstTMsg, arWrtOffRqstDtlTMsg, glblCmpyCd, false, comment);
                }
                // END 2022/10/13 [QC#60401, MOD]
                // END  2018/04/19 [QC#20940, ADD]
            }

            // START 2018/04/19 [QC#20940, ADD]
            for (String rqstGepCd : rqstGrpMap.keySet()) {
                Map<String, Object> wrtOffRqstDataMap = rqstGrpMap.get(rqstGepCd);
                // START 2022/10/13 [QC#60401, MOD]
                if (SIGNAL_APPROVE.equals(name)) {
                    createCltNoteDtl(wrtOffRqstDataMap, glblCmpyCd, true);
                } else {
                    createCltNoteDtl(wrtOffRqstDataMap, glblCmpyCd, false);
                }
                // END 2022/10/13 [QC#60401, MOD]
           }
            // END   2018/04/19 [QC#20940, ADD]

        //---- start add QC#8915. If no aprover is found, throw exception.
        // no user
        } else if (COND_END_NO_USER.equals(name)) {
            throw new S21NwfBizException("NFCM0531E", new String[] {"Approver"});
        } 
        //---- end QC#8915
        return;
    }

    // START 2022/10/13 [QC#60401, ADD]
    private String getWfComment(S21NwfContext context, String wrtOffRqstGrpCd) {
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();
        String separator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();

        try {
            String wfId = ZYPCodeDataUtil.getVarCharConstValue("WRITE_OFF_WF_ID", glblCmpyCd);
            List<S21NwfProcess> procs = context.getProcessForBiz(wfId, wrtOffRqstGrpCd);
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

    private String getComment(String arWrtOffNoteTxt, String wfComment) {
        String separator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        if (hasValue(arWrtOffNoteTxt)) {
            sb.append(arWrtOffNoteTxt);
        }
        if (hasValue(wfComment)) {
            if (hasValue(sb.toString())) {
                sb.append(separator);
            }
            sb.append(wfComment);
        }
        return sb.toString();
    }
    // END 2022/10/13 [QC#60401, ADD]

    private AR_WRT_OFF_RQSTTMsg findArWrtOffRqst(NFZC500001TokenObjectLine line) {
        AR_WRT_OFF_RQSTTMsg tmsg = new AR_WRT_OFF_RQSTTMsg();
        tmsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        tmsg.arWrtOffRqstPk.setValue(line.getPk());

        return (AR_WRT_OFF_RQSTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
    }

    private AR_WRT_OFF_RQST_DTLTMsg findArWrtOffRqstDtl(NFZC500001TokenObjectLine line) {
        AR_WRT_OFF_RQST_DTLTMsg tmsg = new AR_WRT_OFF_RQST_DTLTMsg();
        tmsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        tmsg.arWrtOffRqstPk.setValue(line.getPk());
        // added 2016/02/17
        tmsg.arTrxBalPk.setValue(line.getArTrxBalPk());

        return (AR_WRT_OFF_RQST_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
    }

    //--- mod 2016/02/17 
    private AR_TRX_BALTMsg findArTrxBal(BigDecimal arTrxBalPk) {
        AR_TRX_BALTMsg tmsg = new AR_TRX_BALTMsg();
        tmsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        
        tmsg.arTrxBalPk.setValue(arTrxBalPk);
    
        return (AR_TRX_BALTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
    }


    // START 2018/03/22 J.Kim [QC#21721,ADD]
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
    // END 2018/03/22 J.Kim [QC#21721,ADD]

    // START 2018/04/19 [QC#20940, ADD]
    private void updateCltNoteData(Map<String, Map<String, Object>> rqstGrpMap, AR_WRT_OFF_RQSTTMsg rqstTMsg, AR_WRT_OFF_RQST_DTLTMsg rqstDtlTMsg, String glblCmpyCd, boolean isApproved, String comment) {

        String wrtOffRqstGrpCd = rqstTMsg.wrtOffRqstGrpCd.getValue();
        if (rqstGrpMap.containsKey(wrtOffRqstGrpCd)) {
            Map<String, Object> wrtOffRqstDataMap = rqstGrpMap.get(wrtOffRqstGrpCd);

            // update Amount
// START 2019/05/23 H.Umeda [QC#50449,DEL]
//          BigDecimal wrtoffApplyAmt = (BigDecimal)wrtOffRqstDataMap.get("Amount");
//          wrtoffApplyAmt = wrtoffApplyAmt.add(rqstTMsg.wrtOffApplyAmt.getValue());
//          wrtOffRqstDataMap.put("Amount", wrtoffApplyAmt);
// END   2019/05/23 H.Umeda [QC#50449,DEL]

            // update Invoice
            StringBuilder builder = new StringBuilder();
            String invoice = (String)wrtOffRqstDataMap.get("Invoice");
            if (!invoice.isEmpty()) {
                builder.append(invoice);
                builder.append(",");
            }
            if (ZYPCommonFunc.hasValue(rqstDtlTMsg.arTrxNum)) {
                // START 2018/07/17 [QC#26989, MOD]
                // builder.append(rqstDtlTMsg.arTrxNum.getValue());
                String arCustRefNum = rqstDtlTMsg.arTrxNum.getValue();

                AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
                arTrxBalTMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
                arTrxBalTMsg.arTrxBalPk.setValue(rqstDtlTMsg.arTrxBalPk.getValue());
                arTrxBalTMsg = (AR_TRX_BALTMsg) S21ApiTBLAccessor.findByKey(arTrxBalTMsg);
                if (arTrxBalTMsg != null) {
                    if (ZYPCommonFunc.hasValue(arTrxBalTMsg.arCustRefNum.getValue())) {
                        arCustRefNum = arTrxBalTMsg.arCustRefNum.getValue();
                    }
                }
                builder.append(arCustRefNum);
                // END   2018/07/17 [QC#26989, MOD]
            }
            wrtOffRqstDataMap.put("Invoice", builder.toString());
        } else {

            CLT_NOTE_TPTMsg cltNoteTp = new CLT_NOTE_TPTMsg();
            ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd, glblCmpyCd);
            if (isApproved) {
                ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.APPROVED_WRITE_OFFS);
            } else {
                ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.REJECTED_WRITE_OFFS);
            }
            cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);

            String activity = "";
            AR_ADJ_TPTMsg arAdjTp = new AR_ADJ_TPTMsg();
            ZYPEZDItemValueSetter.setValue(arAdjTp.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(arAdjTp.arAdjTpCd, rqstTMsg.arAdjTpCd);
            arAdjTp = (AR_ADJ_TPTMsg) S21CodeTableAccessor.findByKey(arAdjTp);
            if (arAdjTp != null) {
                activity = arAdjTp.arAdjTpDescTxt.getValue();
            }

            String reason = null;
            if (ZYPCommonFunc.hasValue(rqstTMsg.arAdjRsnCd)) {
                AR_ADJ_RSNTMsg arAdjRsn = new AR_ADJ_RSNTMsg();
                ZYPEZDItemValueSetter.setValue(arAdjRsn.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(arAdjRsn.arAdjRsnCd, rqstTMsg.arAdjRsnCd);
                arAdjRsn = (AR_ADJ_RSNTMsg) S21CodeTableAccessor.findByKey(arAdjRsn);
                if (arAdjRsn != null) {
                    reason = arAdjRsn.arAdjRsnDescTxt.getValue();
                }
            }
            if (!ZYPCommonFunc.hasValue(reason)) {
                AR_WRT_OFF_NOTETMsg wrtOffNote = new AR_WRT_OFF_NOTETMsg();
                ZYPEZDItemValueSetter.setValue(wrtOffNote.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(wrtOffNote.arWrtOffNoteCd, rqstTMsg.arWrtOffNoteCd);
                wrtOffNote = (AR_WRT_OFF_NOTETMsg) S21CodeTableAccessor.findByKey(wrtOffNote);
                if (wrtOffNote != null) {
                    reason = wrtOffNote.arWrtOffNoteDescTxt.getValue();
                }
            }

            String createdBy = getUserNm(glblCmpyCd, rqstTMsg.wrtOffRqstUsrId.getValue());

            Map<String, Object> wrtOffRqstDataMap = new HashMap<String, Object>();
            // set RequestNo
            wrtOffRqstDataMap.put("RequestNo", rqstTMsg.wrtOffRqstGrpCd.getValue());
            // set Reason
            wrtOffRqstDataMap.put("Reason", reason);
            // set Activity
            wrtOffRqstDataMap.put("Activity", activity);
            // set Note
            //wrtOffRqstDataMap.put("Note", rqstTMsg.arWrtOffNoteTxt.getValue());
            wrtOffRqstDataMap.put("Note", comment);
            // set Invoice
            StringBuilder builder = new StringBuilder();
            if (ZYPCommonFunc.hasValue(rqstDtlTMsg.arTrxNum)) {
                // START 2018/07/20 [QC#26989, MOD]
                // builder.append(rqstDtlTMsg.arTrxNum.getValue());
                String arCustRefNum = rqstDtlTMsg.arTrxNum.getValue();

                AR_TRX_BALTMsg arTrxBalTMsg = new AR_TRX_BALTMsg();
                arTrxBalTMsg.glblCmpyCd.setValue(S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
                arTrxBalTMsg.arTrxBalPk.setValue(rqstDtlTMsg.arTrxBalPk.getValue());
                arTrxBalTMsg = (AR_TRX_BALTMsg) S21ApiTBLAccessor.findByKey(arTrxBalTMsg);
                if (arTrxBalTMsg != null) {
                    if (ZYPCommonFunc.hasValue(arTrxBalTMsg.arCustRefNum.getValue())) {
                        arCustRefNum = arTrxBalTMsg.arCustRefNum.getValue();
                    }
                }
                builder.append(arCustRefNum);
                // END   2018/07/20 [QC#26989, MOD]
            }
            wrtOffRqstDataMap.put("Invoice", builder.toString());
            // set Amount
            wrtOffRqstDataMap.put("Amount", rqstTMsg.wrtOffApplyAmt.getValue());
            // set CreatedBy
            wrtOffRqstDataMap.put("CreatedBy", createdBy);
            // set CltAcctCd
            wrtOffRqstDataMap.put("CltAcctCd", rqstDtlTMsg.billToCustAcctCd.getValue());
            // set RqstUsrId
            wrtOffRqstDataMap.put("RqstUsrId", rqstTMsg.wrtOffRqstUsrId.getValue());

            rqstGrpMap.put(rqstTMsg.wrtOffRqstGrpCd.getValue(), wrtOffRqstDataMap);
        }
    }

    private void createCltNoteDtl(Map<String, Object> wrtOffRqstDataMap, String glblCmpyCd, boolean isApproved) {

        CLT_NOTE_TPTMsg cltNoteTp = new CLT_NOTE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteTp.glblCmpyCd, glblCmpyCd);
        if (isApproved) {
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.APPROVED_WRITE_OFFS);
        } else {
            ZYPEZDItemValueSetter.setValue(cltNoteTp.cltNoteTpCd, CLT_NOTE_TP.REJECTED_WRITE_OFFS);
        }
        cltNoteTp = (CLT_NOTE_TPTMsg) S21CodeTableAccessor.findByKey(cltNoteTp);

        String dtlNoteTxtTmpl = ZYPCodeDataUtil.getVarCharConstValue("NFZC5000_CLT_DTL_NOTE_TXT", glblCmpyCd);
        String dtlNoteTxt = String.format(dtlNoteTxtTmpl,
                (String) wrtOffRqstDataMap.get("RequestNo"), (String) wrtOffRqstDataMap.get("Reason"), (String) wrtOffRqstDataMap.get("Activity"),
                (String) wrtOffRqstDataMap.get("Note"), (String) wrtOffRqstDataMap.get("Invoice"), (BigDecimal) wrtOffRqstDataMap.get("Amount"),
                (String) wrtOffRqstDataMap.get("CreatedBy"));
        if (dtlNoteTxt.length() > 4000) {
            dtlNoteTxt = dtlNoteTxt.substring(0, 4000);
        }

        CLT_NOTE_DTLTMsg cltNoteDtlMsg = new CLT_NOTE_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltNoteDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLT_NOTE_DTL_SQ"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctCd, (String) wrtOffRqstDataMap.get("CltAcctCd"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cltAcctTpCd, CLT_ACCT_TP.BILL_TO);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.cratUsrId, (String) wrtOffRqstDataMap.get("RqstUsrId"));
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteSubjTxt, cltNoteTp.cltNoteTpDescTxt);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.colNoteTpCd, cltNoteTp.cltNoteTpCd);
        ZYPEZDItemValueSetter.setValue(cltNoteDtlMsg.dtlNoteTxt, dtlNoteTxt);

        EZDTBLAccessor.create(cltNoteDtlMsg);
    }
    // END   2018/04/19 [QC#20940, ADD]
}
