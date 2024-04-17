/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;


import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.S21_PSN_VTMsg;
import business.db.S21_PSN_VTMsgArray;
import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.parts.NSZC053001PMsg;
import business.parts.NSZC053001_xxCrRebilDtlListPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.impl.S21NwfTokenImpl;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole;
import com.canon.cusa.s21.framework.nwf.core.user.S21NwfUserRole.USER_ROLE_TYPE;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 03/25/2016   Hitachi         T.Aoyagi        Update          QC#5435
 * 04/11/2016   Hitachi         T.Aoyagi        Update          QC#6805
 * 06/06/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 09/12/2016   Hitachi         K.Kishimoto     Update          QC#5566
 * 01/24/2017   Hitachi         A.Kohinata      Update          QC#17261
 * 2017/09/26   Hitachi         K.Kitachi       Update          QC#21212
 * 2017/11/07   Hitachi         K.Kojima        Update          QC#22331
 * 2018/03/16   Hitachi         K.Kojima        Update          QC#24497
 * 2018/05/30   Hitachi         K.Kitachi       Update          QC#22336
 * 2018/06/25   Hitachi         K.Kitachi       Update          QC#22245
 * 2018/07/19   Hitachi         K.Kojima        Update          QC#26791
 * 2018/08/20   Hitachi         K.Kojima        Update          QC#27769
 * 2019/10/01   Hitachi         A.Kohinata      Update          QC#53643
 * 2022/09/06   Hitachi         B.Amarsanaa     Update          QC#60049
 *</pre>
 */
public class SubmitForApprovalProcess implements ZYPConstant {

    /** onBatchTp */
    private ONBATCH_TYPE onBatchTp;

    /** NSZC0530Query */
    private NSZC0530Query query = NSZC0530Query.getInstance();

    protected void doProcess(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {

        this.onBatchTp = onBatchType;
        submitForApprovalProcess(msgMap);
    }

    private void submitForApprovalProcess(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        SVC_CR_REBILTMsg crRebilTMsg = query.getSvcCrRebilTMsgForUpdate(msgMap);
        if (crRebilTMsg == null) {
            msgMap.addXxMsgId(NSZM0890E);
            return;
        }

        // START 2018/07/19 K.Kojima [QC#26791,ADD]
        if (!NSZC053001CommonLogic.checkNegativeRead(pMsg.glblCmpyCd.getValue(), crRebilTMsg.svcCrRebilPk.getValue())) {
            msgMap.addXxMsgId(NSZM1333E);
            return;
        }
        // END 2018/07/19 K.Kojima [QC#26791,ADD]

        // ----------------------------------------
        // Update SVC_CR_REBIL
        // ----------------------------------------
        setValue(crRebilTMsg.svcCrRebilStsCd, SVC_CR_REBIL_STS.PENDING_APPROVAL);
        setValue(crRebilTMsg.svcCrRebilRsnCd, pMsg.svcCrRebilRsnCd);
        setValue(crRebilTMsg.svcCrRebilRsnTxt, pMsg.svcCrRebilRsnTxt);
        setValue(crRebilTMsg.svcCrRebilProcCd, pMsg.svcCrRebilProcCd);
        S21ApiTBLAccessor.update(crRebilTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(crRebilTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0877E);
            return;
        }

        // ----------------------------------------
        // Update SVC_CR_REBIL_DTL
        // ----------------------------------------
        List<BigDecimal> crRebilDtlPkList = getSvcCrRebilDtlPkList(msgMap);
        for (BigDecimal crRebilDtlPk : crRebilDtlPkList) {
            SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg = query.getSvcCrRebilDtlTMsgForUpdate(msgMap, crRebilDtlPk);
            if (crRebilDtlTMsg == null) {
                msgMap.addXxMsgId(NSZM0892E);
                return;
            }
            setValue(crRebilDtlTMsg.invPrintFlg, FLG_ON_Y);
            S21ApiTBLAccessor.update(crRebilDtlTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(crRebilDtlTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0879E);
                return;
            }
        }

        // START 2018/06/25 K.Kitachi [QC#22245, DEL]
//        // ----------------------------------------
//        // Release Billing Hold on Contract
//        // ----------------------------------------
//        NSZC053001CommonLogic.billingHld(msgMap, crRebilTMsg, this.onBatchTp);
        // END 2018/06/25 K.Kitachi [QC#22245, DEL]

        // START 2018/03/16 K.Kojima [QC#24497,DEL]
        // // ----------------------------------------
        // // Update customer care status
        // // ----------------------------------------
        // // Add Start 09/12/2016 <QC#5566>
        // StringBuilder notesDetail = new StringBuilder();
        // notesDetail.append(" ");
        // notesDetail.append(NSZC053001CommonLogic.getCurrTimestamp());
        // notesDetail.append(CALL_PRC_CANON_E193_NOTES_DETAIL_APPROVAL);
        // List<Map<String, Object>> approverList = NSZC0530Query.getInstance().getApproverList(crRebilTMsg);
        // for (Map<String, Object> approver : approverList) {
        //     notesDetail.append(CALL_PRC_CANON_E193_PARM_BR);
        //     notesDetail.append((String) approver.get("BOSS_NM"));
        // }
        // // mod start 01/24/2017 CSA Defect#17261
        // NSZC053001CommonLogic.callCustomerCareApi(msgMap, crRebilTMsg.custIncdtId.getValue(), crRebilTMsg.custIncdtLineId.getValue(), null, notesDetail.toString(), crRebilTMsg.ezUpUserID.getValue());
        // if (msgMap.getMsgMgr().isXxMsgId()) {
        //     return;
        // }
        // // mod end 01/24/2017 CSA Defect#17261
        // // Add End   09/12/2016 <QC#5566>
        // END 2018/03/16 K.Kojima [QC#24497,DEL]

        // ----------------------------------------
        // Start workflow
        // ----------------------------------------
        startWf(msgMap, crRebilTMsg);
        // START 2018/03/16 K.Kojima [QC#24497,ADD]
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // END 2018/03/16 K.Kojima [QC#24497,ADD]

        // START 2018/03/16 K.Kojima [QC#24497,ADD]
        // ----------------------------------------
        // Update customer care status
        // ----------------------------------------
        StringBuilder notesDetail = new StringBuilder();
        notesDetail.append(" ");
        notesDetail.append(NSZC053001CommonLogic.getCurrTimestamp());
        notesDetail.append(CALL_PRC_CANON_E193_NOTES_DETAIL_APPROVAL);
        // START 2018/08/20 K.Kojima [QC#27769,DEL]
        // List<Map<String, Object>> approverList = NSZC0530Query.getInstance().getApproverList(crRebilTMsg);
        // for (Map<String, Object> approver : approverList) {
        //     notesDetail.append(CALL_PRC_CANON_E193_PARM_BR);
        //     notesDetail.append((String) approver.get("BOSS_NM"));
        // }
        // END 2018/08/20 K.Kojima [QC#27769,DEL]
        // START 2018/08/20 K.Kojima [QC#27769,ADD]
        setNotes(msgMap, crRebilTMsg.glblCmpyCd.getValue(), notesDetail, crRebilTMsg.svcCrRebilPk.getValue());
        // END 2018/08/20 K.Kojima [QC#27769,ADD]

        NSZC053001CommonLogic.callCustomerCareApi(msgMap, crRebilTMsg.custIncdtId.getValue(), crRebilTMsg.custIncdtLineId.getValue(), null, notesDetail.toString(), crRebilTMsg.ezUpUserID.getValue());
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        // END 2018/03/16 K.Kojima [QC#24497,ADD]
    }

    private List<BigDecimal> getSvcCrRebilDtlPkList(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        List<BigDecimal> svcCrRebilDtlPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg dtlPMsg = pMsg.xxCrRebilDtlList.no(i);

            if (hasValue(dtlPMsg.svcCrRebilDtlPk)) {
                if (!svcCrRebilDtlPkList.contains(dtlPMsg.svcCrRebilDtlPk.getValue())) {
                    svcCrRebilDtlPkList.add(dtlPMsg.svcCrRebilDtlPk.getValue());
                }
            }
        }
        return svcCrRebilDtlPkList;
    }

    // START 03/25/2016 T.Aoyagi [QC#5435, MOD]
    private void startWf(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg) {

        // Create New Workflow Process
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context;
        S21NwfProcess process;

        try {
            context = factory.getContex();
            process = context.newProcess(WF_PROCESS_NM);
            process.setDocumentId(crRebilTMsg.svcCrRebilPk.getValue().toPlainString());

            // set Token Object
            NSZC053001TokenObject tokenBiz = setTokenBiz(msgMap, crRebilTMsg);
            S21NwfTokenImpl token = (S21NwfTokenImpl) process.getToken();
            token.setTokenObj(tokenBiz);

            // Start Workflow
            token.start();

        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0926E);
        } catch (S21NwfBizException e) {
            // Business Error Logic
            // Auto Approve Process Call APIs
            // Approve API / Reject API / Process End API Error
            msgMap.addXxMsgIdWithPrm(e.getMessageInfo().getCode(), e.getMessageInfo().getParameter());
        } catch (S21NwfException e) {
            // System Error Logic
            msgMap.addXxMsgId(NSZM0926E);
        }
    }

    private NSZC053001TokenObject setTokenBiz(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg) {
        // Business Token Object
        NSZC053001TokenObject tokenBiz = new NSZC053001TokenObject();

        // Line Data
        NSZC053001TokenObjectLine line = new NSZC053001TokenObjectLine();
        // mod start 2019/10/01 QC#53643
        //BigDecimal invTotDealNetAmt = query.getInvTotDealNetAmt(crRebilTMsg.glblCmpyCd.getValue(), crRebilTMsg.svcCrRebilPk.getValue());
        Map<String, Object> invAmtMap = query.getInvTotDealNetAmt(crRebilTMsg.glblCmpyCd.getValue(), crRebilTMsg.svcCrRebilPk.getValue());
        BigDecimal oldInvTotDealAmt = (BigDecimal) invAmtMap.get("OLD_INV_TOT_DEAL_AMT");
        BigDecimal newInvTotDealAmt = (BigDecimal) invAmtMap.get("NEW_INV_TOT_DEAL_AMT");
        BigDecimal invTotDealNetAmt = (BigDecimal) invAmtMap.get("INV_TOT_DEAL_NET_AMT");
        // mod end 2019/10/01 QC#53643
        // add start 2022/08/29 QC#60049
        Map<String, Object> ntfyInfoMap = query.getCrRblNoticationEmailInfo(crRebilTMsg.glblCmpyCd.getValue(),crRebilTMsg.custIncdtId.getValue());
        String billToCustAcctNm = (String) ntfyInfoMap.get("BILL_TO_CUST_ACCT_NM");
        String billToCustAcctCd = (String) ntfyInfoMap.get("BILL_TO_CUST_ACCT_CD");
        String contrNum = (String) ntfyInfoMap.get("CONTR_NUM");
        String invNum = (String) ntfyInfoMap.get("INV_NUM");
        String grpInvNum = (String) ntfyInfoMap.get("GRP_INV_NUM");
        String svcCrRebilRsnTxt = (String) ntfyInfoMap.get("SVC_CR_REBIL_RSN_TXT");
        // add end 2022/08/29 QC#60049
        line.setGlblCmpyCd(crRebilTMsg.glblCmpyCd.getValue());
        line.setCustIncdtId(crRebilTMsg.custIncdtId.getValue());
        line.setInvTotDealNetAmt(invTotDealNetAmt);
        line.setSvcContrBrCd(crRebilTMsg.svcContrBrCd.getValue());
        line.setCustIndtAsgPsnCd(crRebilTMsg.custIncdtAsgPsnCd.getValue());
        line.setCustCareRgtnPsnCd(crRebilTMsg.custCareRgtnPsnCd.getValue());
        tokenBiz.addLineData(line);

        // Set Condition Data
        BigDecimal svcRgPk = query.getSvcRgPk(msgMap);
        tokenBiz.setBizId(WF_PROCESS_NM);
        tokenBiz.setCondNum1(svcRgPk);
        // START 04/11/2016 T.Aoyagi [QC#6805, MOD]
        tokenBiz.setCondNum2(invTotDealNetAmt.abs());
        // END 04/11/2016 T.Aoyagi [QC#6805, MOD]
        tokenBiz.setCondStr1(crRebilTMsg.svcContrBrCd.getValue());
        tokenBiz.setCondStr2(crRebilTMsg.custIncdtAsgPsnCd.getValue());
        tokenBiz.setCondStr3(S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        tokenBiz.setCondStr4(ZYPCodeDataUtil.getVarCharConstValue(CONTR_BR_FIRST_ORG_CD, crRebilTMsg.glblCmpyCd.getValue()));
        // START 2018/05/30 K.Kitachi [QC#22336, ADD]
        String hrchExstFlg = ZYPConstant.FLG_OFF_N;
        // START 2018/08/21 K.Kojima [QC#27769,MOD]
        // if (isExistContrHrch(crRebilTMsg, svcRgPk, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId())) {
        //     hrchExstFlg = ZYPConstant.FLG_ON_Y;
        // }
        BigDecimal apvlLimitAmt = getApvlLimitAmt(crRebilTMsg, svcRgPk, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        if (apvlLimitAmt != null) {
            hrchExstFlg = ZYPConstant.FLG_ON_Y;
        }
        // END 2018/08/21 K.Kojima [QC#27769,MOD]
        tokenBiz.setCondStr5(hrchExstFlg);
        // END 2018/05/30 K.Kitachi [QC#22336, ADD]
        // START 2018/08/21 K.Kojima [QC#27769,ADD]
        tokenBiz.setCondNum3(apvlLimitAmt);
        // END 2018/08/21 K.Kojima [QC#27769,ADD]

        // Set Screen Data
        tokenBiz.setAttribute1Lbl("Incident ID");
        tokenBiz.setAttribute1(crRebilTMsg.custIncdtId.getValue());
        tokenBiz.setAttribute2Lbl("Branch");
        tokenBiz.setAttribute2(crRebilTMsg.svcContrBrCd.getValue());
        tokenBiz.setAttribute3Lbl("Contract Admin");
        tokenBiz.setAttribute3(crRebilTMsg.custIncdtAsgPsnCd.getValue());
        tokenBiz.setAttribute4Lbl("Incident Registered Person");
        tokenBiz.setAttribute4(crRebilTMsg.custCareRgtnPsnCd.getValue());

        // START 2017/11/07 K.Kojima [QC#22331,ADD]
        tokenBiz.setBizScreenId("NSAL1090");
        tokenBiz.setBizScreenParams(crRebilTMsg.custIncdtId.getValue());
        // END 2017/11/07 K.Kojima [QC#22331,ADD]

        // add start 2019/10/01 QC#53643
        // Set Mail Item
        tokenBiz.setHdrAttrb1(formatAmount(oldInvTotDealAmt));
        tokenBiz.setHdrAttrb2(formatAmount(newInvTotDealAmt));
        tokenBiz.setHdrAttrb3(formatAmount(invTotDealNetAmt));
        // add end 2019/10/01 QC#53643
        // add start 2022/08/29 Amarsanaa QC#60049
        tokenBiz.setHdrAttrb4(billToCustAcctNm);
        tokenBiz.setHdrAttrb5(billToCustAcctCd);
        tokenBiz.setHdrAttrb6(contrNum);
        tokenBiz.setHdrAttrb7(invNum);
        tokenBiz.setHdrAttrb8(grpInvNum);
        tokenBiz.setHdrAttrb9(svcCrRebilRsnTxt);
        // add end 2022/08/29 QC#60049
        return tokenBiz;
    }
    // END 03/25/2016 T.Aoyagi [QC#5435, MOD]

    // START 2018/05/30 K.Kitachi [QC#22336, ADD]
    // START 2018/08/21 K.Kojima [QC#27769,MOD]
    // private boolean isExistContrHrch(SVC_CR_REBILTMsg crRebilTMsg, BigDecimal svcRgPk, String userId) {
    private BigDecimal getApvlLimitAmt(SVC_CR_REBILTMsg crRebilTMsg, BigDecimal svcRgPk, String userId) {
    // END 2018/08/21 K.Kojima [QC#27769,MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", crRebilTMsg.glblCmpyCd.getValue());
        ssmParam.put("condNum1", svcRgPk);
        ssmParam.put("condStr1", crRebilTMsg.svcContrBrCd.getValue());
        ssmParam.put("condStr2", crRebilTMsg.custIncdtAsgPsnCd.getValue());
        ssmParam.put("condStr4", ZYPCodeDataUtil.getVarCharConstValue(CONTR_BR_FIRST_ORG_CD, crRebilTMsg.glblCmpyCd.getValue()));
        List<Map<String, Object>> hrchList = query.getHrchList(ssmParam);
        for (Map<String, Object> hrch : hrchList) {
            if (userId.equals((String) hrch.get("VAL_STR_1"))) {
                // START 2018/08/21 K.Kojima [QC#27769,MOD]
                // return true;
                return (BigDecimal) hrch.get("COND_NUM_2");
                // END 2018/08/21 K.Kojima [QC#27769,MOD]
            }
        }
        // START 2018/08/21 K.Kojima [QC#27769,MOD]
        // return false;
        return null;
        // END 2018/08/21 K.Kojima [QC#27769,MOD]
    }
    // END 2018/05/30 K.Kitachi [QC#22336, ADD]

    // START 2018/08/20 K.Kojima [QC#27769,ADD]
    private boolean setNotes(S21ApiMessageMap msgMap, String glblCmpyCd, StringBuilder notesDetail, BigDecimal svcCrRebilPk) {
        try {
            S21NwfContextFactory factory = new S21NwfUtilContextFactory();
            S21NwfContext context = factory.getContex();
            List<S21NwfProcess> procs = context.getProcessForBiz(WF_PROCESS_NM, svcCrRebilPk.toPlainString());
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
                for (S21NwfUtilBizWorkItem wi : tasks) {
                    if (wi.isApprovable()) {
                        List<S21NwfUserRole> userRoleList = wi.getToUsers();
                        for (S21NwfUserRole userRole : userRoleList) {
                            if (!USER_ROLE_TYPE.USER.equals(userRole.getType())) {
                                continue;
                            }
                            String userNm = userRole.getUserRole();
                            S21_PSN_VTMsg tMsg = new S21_PSN_VTMsg();
                            tMsg.setSQLID("001");
                            tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                            tMsg.setConditionValue("psnCd01", userNm);
                            S21_PSN_VTMsgArray tMsgArray = (S21_PSN_VTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
                            if (tMsg != null && tMsgArray.getValidCount() > 0) {
                                userNm = tMsgArray.no(0).fullPsnNm.getValue();
                            }
                            notesDetail.append(CALL_PRC_CANON_E193_PARM_BR);
                            notesDetail.append(userNm);
                        }
                    }
                }
            }
        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0926E);
            return false;
        }
        return true;
    }
    // END 2018/08/20 K.Kojima [QC#27769,ADD]

    // add start 2019/10/01 QC#53643
    private String formatAmount(BigDecimal amt) {
        if (!hasValue(amt)) {
            return null;
        }
        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        return df.format(amt);
    }
    // add end 2019/10/01 QC#53643
}
