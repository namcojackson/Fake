package com.canon.cusa.s21.batch.NFB.NFBB113001;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import business.db.CM_MAINT_INVTMsg;

import com.canon.cusa.s21.api.NFC.NFZC502001.NFZC502001TokenObject;
import com.canon.cusa.s21.api.NFC.NFZC502001.NFZC502001TokenObjectLine;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_MAINT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Contract Department approval notification
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/23/2016   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#14038
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#12725
 * 2016/11/07   Hitachi         J.Kim           Update          QC#15712
 * 2016/12/15   Fujitsu         H.Ikeda         Update          QC#15823
 * 2017/12/26   Hitachi         J.Kim           Update          QC#22458
 * </pre>
 */
public class NFBB113001 extends S21BatchMain implements NFBB113001Constant {

    /** User Profile */
    private S21UserProfileService profile;
    /** Global Company Code */
    private String glblCmpyCd;
    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient;
    /** Total Commit Count */
    private int totalCommitCount;
    /** TERM_CD */
    private TERM_CD termCd = TERM_CD.NORMAL_END;
    /** Commit Count (CM_MAINT_INV) */
    private int cmMaintInvCommitCount;

    // START 2016/09/09 K.Kojima [QC#14038,DEL]
    // /** List For handling CM_MAINT_INV Bulk TBL Accessor */
    // private List<CM_MAINT_INVTMsg> listCmMaintInvTMsg;
    // END 2016/09/09 K.Kojima [QC#14038,DEL]
    /** CM_MAINT_INV Bulk Update Count */
    private int iCntCmMaintInv;

    /**
     * main method, which be called by Batch Shell
     * @param args argument
     */
    public static void main(final String[] args) {
        /** Initialize S21BatchMain */
        new NFBB113001().executeBatch(NFBB113001.class.getSimpleName());
    }
    @Override
    protected void initRoutine() {
        /** Get User Profile Service Instance */
        this.profile = S21UserProfileServiceFactory.getInstance().getService();
        /** Get Global Company Code */
        this.glblCmpyCd = this.profile.getGlobalCompanyCode();
        /** Initialize SSM Batch client. */
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        /** Initialize Commit Count */
        initCommitCount();
        // START 2016/09/09 K.Kojima [QC#14038,DEL]
        // /** Initialize list for bulk TBL accessor. */
        // initListForBulkTBLAccessor();
        // END 2016/09/09 K.Kojima [QC#14038,DEL]
        /** Initialize Bulk Count. */
        initBulkCount();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void mainRoutine() {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        // START 2017/12/26 J.Kim [QC#22458,MOD]
        // queryParam.put("apDsWfStsCd", AP_DS_WF_STS_CD_00);
        // queryParam.put("apMaintInvStsCd", AP_MAINT_INV_STS_CD_12);
        queryParam.put("apDsWfStsCd", AP_DS_WF_STS.PENDING);
        queryParam.put("apMaintInvStsCd", AP_MAINT_INV_STS.PENDING_WORKFLOW_APPROVAL);
        // END 2017/12/26 J.Kim [QC#22458,MOD]
        List<Map<String, Object>> listMaintInv = (List<Map<String, Object>>) ssmBatchClient.queryObjectList(SELECT_CM_MAINT_INV, queryParam);
        if (listMaintInv == null || listMaintInv.size() == 0) {
            listMaintInv = new ArrayList<Map<String, Object>>();
        }
        String userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        // Start work flow by Invoice
        for (int i = 0; i < listMaintInv.size(); i++) {
            // START 2016/09/12 K.Kojima [QC#14038,DEL]
            // String aGrKey =
            // userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
            // Map<String, Object> mapMaintInv = (Map<String,
            // Object>)listMaintInv.get(i);
            // if (startWorkflow(mapMaintInv, aGrKey)) {
            // // Update AP_MAINT_INV_STS_CD on CM_MAINT_INV table to
            // 15(Workflow requested)
            // String apVndCd = (String)mapMaintInv.get(AP_VND_CD);
            // String apInvNum = (String)mapMaintInv.get(AP_INV_NUM);
            // updateCmMaintInv(apInvNum, apVndCd,
            // AP_MAINT_INV_STS_CD_15);
            // }
            // END 2016/09/12 K.Kojima [QC#14038,DEL]
            // START 2016/09/12 K.Kojima [QC#14038,ADD]
            Map<String, Object> mapMaintInv = (Map<String, Object>) listMaintInv.get(i);

            String wfId = getWorkflowId(this.glblCmpyCd, CONST_KEY_MAINT_INV_WF_ID);
            String aGrKey = userId.concat(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));

            if (checkWorkflow(wfId, aGrKey)) {
                String apVndCd = (String) mapMaintInv.get(AP_VND_CD);
                String apInvNum = (String) mapMaintInv.get(AP_INV_NUM);
                // START 2017/12/26 J.Kim [QC#22458,MOD]
                // updateCmMaintInv(apInvNum, apVndCd, AP_MAINT_INV_STS_CD_15);
                updateCmMaintInv(apInvNum, apVndCd, AP_MAINT_INV_STS.WORKFLOW_REQUESTED);
                // END 2017/12/26 J.Kim [QC#22458,MOD]
                startWorkflow(mapMaintInv, wfId, aGrKey);
                String apvrUsrId = getWfGroups(wfId, aGrKey);
                updateCmMaintInvApvrUsrId(apInvNum, apVndCd, apvrUsrId);
                cmMaintInvCommitCount = cmMaintInvCommitCount + 1;
            }
            // END 2016/09/12 K.Kojima [QC#14038,ADD]
        }
        // START 2016/09/09 K.Kojima [QC#14038,DEL]
        // if (iCntCmMaintInv > 0) {
        // bulkUpdateCmMaintInv();
        // }
        // END 2016/09/09 K.Kojima [QC#14038,DEL]
        totalCommitCount = totalCommitCount + cmMaintInvCommitCount;
        commit();
    }
    @Override
    protected void termRoutine() {
        /** Normal End this process */
        setTermState(termCd, totalCommitCount, 0, 0);
    }

    /**
     * Initialize Commit Count.
     */
    private void initCommitCount() {
        this.cmMaintInvCommitCount = 0;
        this.totalCommitCount = 0;
    }

    // START 2016/09/09 K.Kojima [QC#14038,DEL]
    // /**
    // * Initialize list for bulk TBL accessor.
    // */
    // private void initListForBulkTBLAccessor() {
    // this.listCmMaintInvTMsg = new ArrayList<CM_MAINT_INVTMsg>();
    // }
    // END 2016/09/09 K.Kojima [QC#14038,DEL]

    /**
     * Initialize Bulk Count.
     */
    private void initBulkCount() {
        this.iCntCmMaintInv = 0;
    }

    // START 2016/09/09 K.Kojima [QC#14038,ADD]
    /**
     * checkWorkflow
     * @param wfId
     * @param aGrKey
     */
    private boolean checkWorkflow(String wfId, String aGrKey) {
        String wfKey = aGrKey;
        if (isWorkflowStarted(wfId, wfKey)) {
            return false;
        }
        return true;
    }

    // END 2016/09/09 K.Kojima [QC#14038,ADD]

    /**
     * startWorkflow
     * @param mapMaintInv Map<String, Object>
     * @param wfId
     * @param aGrKey String
     */
    // START 2016/09/09 K.Kojima [QC#14038,MOD]
    // private boolean startWorkflow(Map<String, Object> mapMaintInv,
    // String aGrKey) {
    private void startWorkflow(Map<String, Object> mapMaintInv, String wfId, String aGrKey) {
        // END 2016/09/09 K.Kojima [QC#14038,MOD]

        // START 2016/09/09 K.Kojima [QC#14038,DEL]
        // String wfId = getWorkflowId(getGlobalCompanyCode(),
        // CONST_KEY_MAINT_INV_WF_ID);
        // END 2016/09/09 K.Kojima [QC#14038,DEL]
        String wfKey = aGrKey;
        // START 2016/09/09 K.Kojima [QC#14038,DEL]
        // if (isWorkflowStarted(wfId, wfKey)) {
        // return false;
        // }
        // END 2016/09/09 K.Kojima [QC#14038,DEL]

        // Business Token Object
        NFZC502001TokenObject tokenBiz = new NFZC502001TokenObject();

        String apVndCd = (String)mapMaintInv.get(AP_VND_CD);
        String prntVndNm = (String)mapMaintInv.get(PRNT_VND_NM);
        String apInvNum = (String)mapMaintInv.get(AP_INV_NUM);
        BigDecimal apInvAmt = (BigDecimal)mapMaintInv.get(AP_INV_AMT);
        BigDecimal apAdjAmt = (BigDecimal)mapMaintInv.get(AP_ADJ_AMT);
        if (apAdjAmt.compareTo(BigDecimal.ZERO) != 0) {
            apInvAmt = apAdjAmt;
        }
        // START 2016/09/09 K.Kojima [QC#14038,ADD]
        String ezinuserid = (String) mapMaintInv.get(EZINUSERID);
        // END 2016/09/09 K.Kojima [QC#14038,ADD]

        // Line Data
        NFZC502001TokenObjectLine line = new NFZC502001TokenObjectLine();
        line.setApInvNum(apInvNum);
        line.setApVndCd(apVndCd);
        tokenBiz.addLine(line);

        // Set Condition Data
        tokenBiz.setBizId(wfId);
        tokenBiz.setCondNum1(apInvAmt);
        // START 2016/09/09 K.Kojima [QC#12725,ADD]
        tokenBiz.setGlblCmpyCd(this.glblCmpyCd);
        tokenBiz.setCondStr9(wfKey);
        // END 2016/09/09 K.Kojima [QC#12725,ADD]

        // Set Attribute
        tokenBiz.setAttribute1Lbl("Supplier #");
        tokenBiz.setAttribute1(apVndCd);
        tokenBiz.setAttribute2Lbl("Supplier Name");
        tokenBiz.setAttribute2(prntVndNm);
        tokenBiz.setAttribute3Lbl("Invoice #");
        tokenBiz.setAttribute3(apInvNum);
        tokenBiz.setAttribute4Lbl("Invoice Amount");
        // START 2016/11/07 J.Kim [QC#15712,MOD]
        // tokenBiz.setAttribute4(apInvAmt.toPlainString());
        tokenBiz.setAttribute4(formatAmount(apInvAmt));
        // END 2016/11/07 J.Kim [QC#15712,MOD]

        // Set Biz Screen ID
        // START 2016/09/09 K.Kojima [QC#14038,MOD]
        // tokenBiz.setBizScreenId("NFZC5020");
        tokenBiz.setBizScreenId("NFBL1110");
        // END 2016/09/09 K.Kojima [QC#14038,MOD]

        // Set Biz Screen Parameters
        // START 2016/09/09 K.Kojima [QC#14038,MOD]
        // tokenBiz.setBizScreenParams(apInvAmt);
        tokenBiz.setBizScreenParams(apInvNum, apVndCd);
        // END 2016/09/09 K.Kojima [QC#14038,MOD]

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        S21NwfProcess process = null;

        try {
            context = factory.getContex();
            process = context.newProcess(wfId);
            process.setDocumentId(wfKey);
            process.setGroup(wfKey);
        } catch (S21NwfSystemException e1) {
            e1.printStackTrace();
            throw new S21AbendException(NFDM0008E);
        }

        S21NwfToken token = process.getToken();

        // START 2016/09/09 K.Kojima [QC#14038,ADD]
        token.setAutoDelegateUser(ezinuserid);
        // END 2016/09/09 K.Kojima [QC#14038,ADD]

        token.setTokenObj(tokenBiz);

        try {
            token.start();
        } catch (S21NwfException e) {
            e.printStackTrace();
            throw new S21AbendException(NFDM0008E);
        }
        // START 2016/09/09 K.Kojima [QC#14038,DEL]
        // return true;
        // END 2016/09/09 K.Kojima [QC#14038,DEL]
    }

    // START 2016/11/07 J.Kim [QC#15712,ADD]
    private String formatAmount(BigDecimal amt) {
        if (!ZYPCommonFunc.hasValue(amt)) {
            return null;
        }
        NumberFormat df = NumberFormat.getCurrencyInstance(Locale.US);
        return df.format(amt);
    }
    // END 2016/11/07 J.Kim [QC#15712,ADD]

    private String getWorkflowId(String glblCmpyCd, String constKey) {

        String wfId = ZYPCodeDataUtil.getVarCharConstValue(constKey, glblCmpyCd);

        if (wfId == null) {
            throw new S21AbendException("[Error]Not Found 'VAR_CHAR_CONST' : varCharConstNm=" + constKey);
        }

        return wfId;
    }
    
    private boolean isWorkflowStarted(String wfId, String wfKey) {

        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;

        try {
            context = factory.getContex();

            List<S21NwfProcess>  processes = context.getProcess(wfId, wfKey);
            for (S21NwfProcess p : processes) {
                if (p.isActive()) {
                    return true;
                }
            }
        } catch (S21NwfSystemException e) {
            // System Error
            throw new S21AbendException(NFDM0008E);
        }

        return false;
    }

    /**
     * Update CM_MAINT_INV record
     * @param apInvNum String 
     * @param apVndCd String
     * @param apMaintInvStsCd String
     */
    private void updateCmMaintInv (String apInvNum, String apVndCd, String apMaintInvStsCd) {

        // Update CM_MAINT_INV record
        CM_MAINT_INVTMsg cmMaintInvTMsg = new CM_MAINT_INVTMsg();
        ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apInvNum, apInvNum);
        ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apVndCd, apVndCd);
        S21FastTBLAccessor.findByKeyForUpdate(cmMaintInvTMsg);
        ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apMaintInvStsCd, apMaintInvStsCd);
        // START 2016/09/09 K.Kojima [QC#14038,DEL]
        // listCmMaintInvTMsg.add(cmMaintInvTMsg);
        // END 2016/09/09 K.Kojima [QC#14038,DEL]
        iCntCmMaintInv++;
        // START 2016/09/09 K.Kojima [QC#14038,DEL]
        // if (iCntCmMaintInv == INT_BULK_COM_LIM) {
        // bulkUpdateCmMaintInv();
        // }
        // END 2016/09/09 K.Kojima [QC#14038,DEL]
        // START 2016/09/09 K.Kojima [QC#14038,ADD]
        S21FastTBLAccessor.update(cmMaintInvTMsg);
        // START 2016/12/15 H.Ikeda [QC#15823,ADD]
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cmMaintInvTMsg.getReturnCode())) {
            throw new S21AbendException(NFZM0027E, new String[]{"CM_MAINT_INV"});
        }
        // END 2016/12/15 H.Ikeda [QC#15823,ADD]
        // END 2016/09/09 K.Kojima [QC#14038,ADD]
    }

    // START 2016/09/09 K.Kojima [QC#14038,ADD]
    /**
     * Update CM_MAINT_INV record
     * @param apInvNum String
     * @param apVndCd String
     * @param apMaintInvStsCd String
     * @param apvrUsrId String
     */
    private void updateCmMaintInvApvrUsrId(String apInvNum, String apVndCd, String apvrUsrId) {
        CM_MAINT_INVTMsg cmMaintInvTMsg = new CM_MAINT_INVTMsg();
        ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apInvNum, apInvNum);
        ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apVndCd, apVndCd);
        S21FastTBLAccessor.findByKeyForUpdate(cmMaintInvTMsg);
        // START 2017/12/26 J.Kim [QC#22458,MOD]
        // if (cmMaintInvTMsg.apDsWfStsCd.getValue().equals(AP_DS_WF_STS_CD_00) && cmMaintInvTMsg.apMaintInvStsCd.getValue().equals(AP_MAINT_INV_STS_CD_15) && !ZYPCommonFunc.hasValue(cmMaintInvTMsg.apvrUsrId)) {
        if (cmMaintInvTMsg.apDsWfStsCd.getValue().equals(AP_DS_WF_STS.PENDING) && cmMaintInvTMsg.apMaintInvStsCd.getValue().equals(AP_MAINT_INV_STS.WORKFLOW_REQUESTED) && !ZYPCommonFunc.hasValue(cmMaintInvTMsg.apvrUsrId)) {
            // END 2017/12/26 J.Kim [QC#22458,MOD]
            ZYPEZDItemValueSetter.setValue(cmMaintInvTMsg.apvrUsrId, apvrUsrId);
            S21FastTBLAccessor.update(cmMaintInvTMsg);
            // START 2016/12/15 H.Ikeda [QC#15823,ADD]
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(cmMaintInvTMsg.getReturnCode())) {
                throw new S21AbendException(NFZM0027E, new String[]{"CM_MAINT_INV"});
            }
            // END 2016/12/15 H.Ikeda [QC#15823,ADD]
        }
    }

    // END 2016/09/09 K.Kojima [QC#14038,ADD]

    // START 2016/09/09 K.Kojima [QC#14038,DEL]
    // /**
    // * Bulk update CM_MAINT_INV table.
    // */
    // private void bulkUpdateCmMaintInv() {
    // CM_MAINT_INVTMsg[] cmMaintInvTMsgs =
    // listCmMaintInvTMsg.toArray(new
    // CM_MAINT_INVTMsg[listCmMaintInvTMsg.size()]);
    // int iRet = S21FastTBLAccessor.update(cmMaintInvTMsgs);
    // if (iRet > 0) {
    // cmMaintInvCommitCount = cmMaintInvCommitCount + iRet;
    // listCmMaintInvTMsg = new ArrayList<CM_MAINT_INVTMsg>();
    // iCntCmMaintInv = 0;
    // } else {
    // cmMaintInvCommitCount = 0;
    // throw new S21AbendException(NFBM0028E);
    // }
    // }
    // END 2016/09/09 K.Kojima [QC#14038,DEL]

    // START 2016/09/09 K.Kojima [QC#12725,ADD]
    /**
     * getWfGroups
     * @param wfId String
     * @param documentId String
     * @return String
     */
    private String getWfGroups(String wfId, String documentId) {
        try {
            S21NwfContextFactory factory = new S21NwfUtilContextFactory();
            S21NwfContext context = factory.getContex();
            List<S21NwfProcess> procs = context.getProcessForBiz(wfId, documentId);
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
                for (S21NwfUtilBizWorkItem wi : tasks) {
                    if (wi.isApprovable() && !wi.isComplete()) {
                        return wi.getGroups().get(0);
                    }
                }
            }
        } catch (S21NwfSystemException e) {
            throw new S21AbendException(NFBM0028E);
        }
        return null;
    }
    // END 2016/09/09 K.Kojima [QC#12725,ADD]

}
