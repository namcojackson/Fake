/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NFC.NFZC502001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CM_MAINT_INVTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_MAINT_INV_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * AP Invoice Workflow Status Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/17/2016   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/09   Hitachi         K.Kojima        Update          QC#12725
 * 2017/12/26   Hitachi         J.Kim           Update          QC#22458
 * </pre>
 */
public class NFZC502001 implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>,
                                      S21NwfRejectEvent<S21NwfContext, S21NwfToken>,
                                      S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {

    /* Message */
    /** @ is not found. */
    public static final String NFAM0154E = "NFAM0154E";
    /** @  is invalid. */
    public static final String NMZM0143E = "NMZM0143E";
    /** The parameter is blank. [@] */
    public static final String ZZXM0021E = "ZZXM0021E";
    // START 2016/09/09 K.Kojima [QC#12725,ADD]
    /** Unexpected Error Occurred */
    public static final String NFBM0028E = "NFBM0028E";
    // END 2016/09/09 K.Kojima [QC#12725,ADD]

    // START 2017/12/26 J.Kim [QC#22458,DEL]
    // /* AP_DS_WF_STS_CD */
    // /** AP_DS_WF_STS_CD 00 (Pending Approval) */
    // public static final String AP_DS_WF_STS_CD_00 = "00";
    // /** AP_DS_WF_STS_CD 10 (Approved) */
    // public static final String AP_DS_WF_STS_CD_01 = "01";
    // /** AP_DS_WF_STS_CD 11 (Rejected) */
    // public static final String AP_DS_WF_STS_CD_09 = "09";

    // /* AP_MAINT_INV_STS_CD */
    // /** AP_MAINT_INV_STS_CD 15(Workflow Requested) */
    // public static final String AP_MAINT_INV_STS_CD_15 = "15";
    // /** AP_MAINT_INV_STS_CD 20(Approved) */
    // public static final String AP_MAINT_INV_STS_CD_20 = "20";
    // /** AP_MAINT_INV_STS_CD 90(Cancelled) */
    // public static final String AP_MAINT_INV_STS_CD_90 = "90";
    // END 2017/12/26 J.Kim [QC#22458,DEL]

    // START 2016/09/09 K.Kojima [QC#12725,ADD]
    private static final String CONST_KEY_MAINT_INV_WF_ID = "MAINT_INV_WF_ID";
    // END 2016/09/09 K.Kojima [QC#12725,ADD]

    /**
     * Approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        // START 2016/09/09 K.Kojima [QC#12725,ADD]
        NFZC502001TokenObject tokenObj = (NFZC502001TokenObject) token.getTokenObj();
        String wfId = ZYPCodeDataUtil.getVarCharConstValue(CONST_KEY_MAINT_INV_WF_ID, tokenObj.getGlblCmpyCd());
        String documentId = tokenObj.getCondStr9();
        String apvrUsrId = getWfGroups(wfId, documentId);
        List<NFZC502001TokenObjectLine> lines = tokenObj.getLines();
        for (NFZC502001TokenObjectLine line : lines) {
            if (S21StringUtil.isEmpty(line.getApVndCd())) {
                throw new S21NwfBizException(ZZXM0021E, new String[]{"AP Vendor Code"});
            }
            if (S21StringUtil.isEmpty(line.getApInvNum())) {
                throw new S21NwfBizException(ZZXM0021E, new String[]{"AP Invoice Number"});
            }
            CM_MAINT_INVTMsg cmMaintInvTMsg = findCmMaintInv(line);
            if (cmMaintInvTMsg == null) {
                throw new S21NwfBizException(NFAM0154E, new String[]{"CM_MAINT_INV record"});
            } else {
                setValue(cmMaintInvTMsg.apvrUsrId, apvrUsrId);
                setValue(cmMaintInvTMsg.invCmntTxt, apvrUsrId + ":" + wfId + ":" + documentId);//TODO
                EZDTBLAccessor.update(cmMaintInvTMsg);
            }
        }
        // END 2016/09/09 K.Kojima [QC#12725,ADD]
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
        // START 2017/12/26 J.Kim [QC#22458,MOD]
        // commonProc(name, context, token, AP_DS_WF_STS_CD_09, AP_MAINT_INV_STS_CD_90);
        commonProc(name, context, token, AP_DS_WF_STS.REJECTED, AP_MAINT_INV_STS.CANCELLED);
        // END 2017/12/26 J.Kim [QC#22458,MOD]
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
        // START 2016/09/09 K.Kojima [QC#12725,ADD]
        if (S21NwfConst.SIGNAL_APPROVE.equals(name)) {
            // END 2016/09/09 K.Kojima [QC#12725,ADD]
            // START 2017/12/26 J.Kim [QC#22458,MOD]
            // commonProc(name, context, token, AP_DS_WF_STS_CD_01, AP_MAINT_INV_STS_CD_20);
            commonProc(name, context, token, AP_DS_WF_STS.APPROVED, AP_MAINT_INV_STS.APPROVED);
            // END 2017/12/26 J.Kim [QC#22458,MOD]
            // START 2016/09/09 K.Kojima [QC#12725,ADD]
        }
        // END 2016/09/09 K.Kojima [QC#12725,ADD]
        return;
    }
    
    /**
     * commonProc
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @param apDsWfStsCd String 
     * @param apMaintInvStsCd String 
     * @throws S21NwfException S21NwfException
     */
    public void commonProc(String name, S21NwfContext context, S21NwfToken token, String apDsWfStsCd, String apMaintInvStsCd) throws S21NwfException {
        NFZC502001TokenObject tokenObj = (NFZC502001TokenObject) token.getTokenObj();
        List<NFZC502001TokenObjectLine> lines = tokenObj.getLines();
        for (NFZC502001TokenObjectLine line : lines) {
            if (S21StringUtil.isEmpty(line.getApVndCd())) {
                throw new S21NwfBizException(ZZXM0021E, new String[]{"AP Vendor Code"});
            }
            if (S21StringUtil.isEmpty(line.getApInvNum())) {
                throw new S21NwfBizException(ZZXM0021E, new String[]{"AP Invoice Number"});
            }
            CM_MAINT_INVTMsg cmMaintInvTMsg = findCmMaintInv(line);
            if (cmMaintInvTMsg == null) {
                throw new S21NwfBizException(NFAM0154E, new String[]{"CM_MAINT_INV record"});
            } else {
                if (!hasValue(cmMaintInvTMsg.apDsWfStsCd) ||
                    // START 2017/12/26 J.Kim [QC#22458,MOD]
                    //!cmMaintInvTMsg.apDsWfStsCd.getValue().equals(AP_DS_WF_STS_CD_00)) {
                    !cmMaintInvTMsg.apDsWfStsCd.getValue().equals(AP_DS_WF_STS.PENDING)) {
                    // END 2017/12/26 J.Kim [QC#22458,MOD]
                    throw new S21NwfBizException(NMZM0143E, new String[]{"AP_DS_WF_STS_CD in CM_MAINT_INV table"});
                }
                if (!hasValue(cmMaintInvTMsg.apMaintInvStsCd) ||
                    // START 2017/12/26 J.Kim [QC#22458,MOD]
                    //!cmMaintInvTMsg.apMaintInvStsCd.getValue().equals(AP_MAINT_INV_STS_CD_15)) {
                    !cmMaintInvTMsg.apMaintInvStsCd.getValue().equals(AP_MAINT_INV_STS.WORKFLOW_REQUESTED)) {
                    // END 2017/12/26 J.Kim [QC#22458,MOD]
                    throw new S21NwfBizException(NMZM0143E, new String[]{"AP_MAINT_INV_STS_CD in CM_MAINT_INV table"});
                }
                setValue(cmMaintInvTMsg.apDsWfStsCd, apDsWfStsCd);
                setValue(cmMaintInvTMsg.apMaintInvStsCd, apMaintInvStsCd);
                // START 2016/09/09 K.Kojima [QC#12725,ADD]
                setValue(cmMaintInvTMsg.apvrUsrId, (String) null);
                setValue(cmMaintInvTMsg.apvrUsrNm, (String) null);
                // END 2016/09/09 K.Kojima [QC#12725,ADD]
                EZDTBLAccessor.update(cmMaintInvTMsg);
            }
        }
    }

    private CM_MAINT_INVTMsg findCmMaintInv(NFZC502001TokenObjectLine line) {
        CM_MAINT_INVTMsg cmMaintInv = new CM_MAINT_INVTMsg();
        setValue(cmMaintInv.glblCmpyCd, S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode());
        setValue(cmMaintInv.apInvNum, line.getApInvNum());
        setValue(cmMaintInv.apVndCd, line.getApVndCd());
        return (CM_MAINT_INVTMsg) EZDTBLAccessor.findByKey(cmMaintInv);
    }

    // START 2016/09/09 K.Kojima [QC#12725,ADD]
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
    // END 2016/09/09 K.Kojima [QC#12725,ADD]

}

