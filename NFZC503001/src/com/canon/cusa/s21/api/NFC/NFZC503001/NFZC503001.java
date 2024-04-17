/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC503001;

import static com.canon.cusa.s21.api.NFC.NFZC503001.constant.NFZC503001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.AP_LSE_BO_WF_RQSTTMsg;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_DS_WF_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfConst;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfApproveEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfProcessEndEvent;
import com.canon.cusa.s21.framework.nwf.core.event.ezd.S21NwfRejectEvent;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.core.token.S21NwfToken;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizWorkItem;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 *<pre>
 * Lease Buyout Approve API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         K.Kojima        Create          N/A
 * 2016/03/10   Hitachi         K.Kojima        Update          CSA QC#5292
 * 2016/03/16   Hitachi         K.Kojima        Update          CSA QC#5297
 * 2016/03/18   Hitachi         K.Kojima        Update          CSA QC#5297
 * 2016/03/31   Hitachi         K.Kojima        Update          CSA QC#5531
 *</pre>
 */
public class NFZC503001 implements S21NwfApproveEvent<S21NwfContext, S21NwfToken>, S21NwfRejectEvent<S21NwfContext, S21NwfToken>, S21NwfProcessEndEvent<S21NwfContext, S21NwfToken> {

    /** ssmBatchClient */
    S21SsmBatchClient ssmBatchClient = null;

    /**
     * Approve
     * @param name String
     * @param context S21NwfContext
     * @param token S21NwfToken
     * @throws S21NwfException S21NwfException
     */
    public void approve(String name, S21NwfContext context, S21NwfToken token) throws S21NwfException {
        S21NwfUtilTokenObj tokenObj = (S21NwfUtilTokenObj) token.getTokenObj();
        String userId = context.getUserID();
        // START 2016/03/31 K.Kojima [QC#5531,ADD]
        String leaseByotApvlWfId = ZYPCodeDataUtil.getVarCharConstValue(LEASE_BYOT_APVL_WF_ID, tokenObj.getGlblCmpyCd());
        String documentId = tokenObj.getCondStr9();
        Map<String, Object> apvlLimitData = getApvlLimitData(leaseByotApvlWfId, documentId);
        // END 2016/03/31 K.Kojima [QC#5531,ADD]
        // START 2016/03/31 K.Kojima [QC#5531,MOD]
        // updateWfRqst(null, tokenObj, userId);
        updateWfRqst(null, tokenObj, userId, apvlLimitData);
        // END 2016/03/31 K.Kojima [QC#5531,MOD]
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
        S21NwfUtilTokenObj tokenObj = (S21NwfUtilTokenObj) token.getTokenObj();
        String userId = context.getUserID();
        // START 2016/03/31 K.Kojima [QC#5531,MOD]
        // updateWfRqst(AP_DS_WF_STS.REJECTED, tokenObj, userId);
        updateWfRqst(AP_DS_WF_STS.REJECTED, tokenObj, userId, null);
        // END 2016/03/31 K.Kojima [QC#5531,MOD]
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
        if (S21NwfConst.SIGNAL_APPROVE.equals(name)) {
            S21NwfUtilTokenObj tokenObj = (S21NwfUtilTokenObj) token.getTokenObj();
            String userId = context.getUserID();
            // START 2016/03/31 K.Kojima [QC#5531,MOD]
            // updateWfRqst(AP_DS_WF_STS.APPROVED, tokenObj, userId);
            updateWfRqst(AP_DS_WF_STS.APPROVED, tokenObj, userId, null);
            // END 2016/03/31 K.Kojima [QC#5531,MOD]
        }
        return;
    }

    /**
     * updateWfRqst
     * @param apDsWfSts String
     * @param tokenObj S21NwfUtilTokenObj
     * @param glblCmpyCd String
     * @param userId String
     * @param userNm String
     */
    // START 2016/03/31 K.Kojima [QC#5531,MOD]
    // private void updateWfRqst(String apDsWfSts, S21NwfUtilTokenObj
    // tokenObj, String userId) {
    private void updateWfRqst(String apDsWfSts, S21NwfUtilTokenObj tokenObj, String userId, Map<String, Object> apvlLimitData) {
        // END 2016/03/31 K.Kojima [QC#5531,MOD]
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        String glblCmpyCd = tokenObj.getGlblCmpyCd();
        // START 2016/03/31 K.Kojima [QC#5531,DEL]
        // String leaseByotApvlWfId =
        // ZYPCodeDataUtil.getVarCharConstValue(LEASE_BYOT_APVL_WF_ID,
        // glblCmpyCd);
        // END 2016/03/31 K.Kojima [QC#5531,DEL]
        AP_LSE_BO_WF_RQSTTMsg tMsg = findApLseBoWfRqst(tokenObj, glblCmpyCd);
        if (tMsg != null) {
            if (apDsWfSts != null) {
                setValue(tMsg.apDsWfStsCd, apDsWfSts);
            } else if (apvlLimitData.size() != 0) {
                // START 2016/03/31 K.Kojima [QC#5531,DEL]
                // Map<String, Object> apvlLimitData =
                // getApvlLimitData(glblCmpyCd,
                // tMsg.mdseCd.getValue(),
                // tMsg.apvlLimitToAmt.getValue(),
                // tMsg.cpoDtlFuncNetAmt.getValue(),
                // leaseByotApvlWfId);
                // if (apvlLimitData != null) {
                // END 2016/03/31 K.Kojima [QC#5531,DEL]
                setValue(tMsg.apvlLimitToAmt, (BigDecimal) apvlLimitData.get(COND_VAL_NUM_01));
                setValue(tMsg.apvlRspbNm, ZYPCodeDataUtil.getVarCharConstValue((String) apvlLimitData.get(ATTRB_COND_VAL_TXT_01), glblCmpyCd));
                // START 2016/03/31 K.Kojima [QC#5531,DEL]
                // }
                // END 2016/03/31 K.Kojima [QC#5531,DEL]
            }
            setValue(tMsg.apvlUsrId, userId);
            setValue(tMsg.apvlUsrNm, getUserNm(glblCmpyCd, userId));
            EZDTBLAccessor.update(tMsg);
        }
    }

    /**
     * findApLseBoWfRqst
     * @param tokenObj S21NwfUtilTokenObj
     * @return AP_LSE_BO_WF_RQSTTMsg
     */
    private AP_LSE_BO_WF_RQSTTMsg findApLseBoWfRqst(S21NwfUtilTokenObj tokenObj, String glblCmpyCd) {
        AP_LSE_BO_WF_RQSTTMsg tmsg = new AP_LSE_BO_WF_RQSTTMsg();
        setValue(tmsg.glblCmpyCd, glblCmpyCd);
        setValue(tmsg.cpoOrdNum, tokenObj.getCondStr2());
        // START 2016/03/16 K.Kojima [QC#5297,MOD]
        // setValue(tmsg.cpoOrdTpCd, tokenObj.getCondStr3());
        // setValue(tmsg.cpoDtlLineNum, tokenObj.getCondStr4());
        // setValue(tmsg.cpoOrdTs, tokenObj.getCondStr5());
        // setValue(tmsg.dsOrdLineCatgCd, tokenObj.getCondStr6());
        // setValue(tmsg.apvlTpTxt, tokenObj.getCondStr7());
        setValue(tmsg.cpoDtlLineNum, tokenObj.getCondStr3());
        setValue(tmsg.invNum, tokenObj.getCondStr4());
        setValue(tmsg.invBolNum, tokenObj.getCondStr5());
        setValue(tmsg.invLineNum, tokenObj.getCondStr6());
        setValue(tmsg.invLineSubNum, tokenObj.getCondStr7());
        setValue(tmsg.invLineSubTrxNum, tokenObj.getCondStr8());
        // END 2016/03/16 K.Kojima [QC#5297,MOD]
        tmsg = (AP_LSE_BO_WF_RQSTTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
        if (tmsg != null && AP_DS_WF_STS.PENDING.equals(tmsg.apDsWfStsCd.getValue())) {
            return tmsg;
        }
        return null;
    }

    // START 2016/03/31 K.Kojima [QC#5531,DEL]
    // /**
    // * getApvlLimitData
    // * @param glblCmpyCd String
    // * @param mdseCd String
    // * @param apvlLimitAmt String
    // * @return
    // */
    // private Map<String, Object> getApvlLimitData(String glblCmpyCd,
    // String mdseCd, BigDecimal apvlLimitAmt, BigDecimal
    // cpoDtlFuncNetAmt, String leaseByotApvlWfId) {
    // // START 2016/03/18 K.Kojima [QC#5297,MOD]
    // // if (apvlLimitAmt.compareTo(cpoDtlFuncNetAmt) >= 0) {
    // if (apvlLimitAmt.compareTo(cpoDtlFuncNetAmt) >= 0) {
    // // END 2016/03/18 K.Kojima [QC#5297,MOD]
    // return null;
    // }
    // Map<String, Object> queryParam = new HashMap<String, Object>();
    // queryParam.put("glblCmpyCd", glblCmpyCd);
    // queryParam.put("wfBizAppId", leaseByotApvlWfId);
    // queryParam.put("condValTxt01", mdseCd);
    // queryParam.put("condValNum01", apvlLimitAmt);
    // Map<String, Object> result = (Map<String, Object>)
    // this.ssmBatchClient.queryObject("getApvlLimitData", queryParam,
    // execParam());
    //
    // return result;
    // }
    // END 2016/03/31 K.Kojima [QC#5531,DEL]

    // START 2016/03/31 K.Kojima [QC#5531,ADD]
    /**
     * getApvlLimitData
     * @param leaseByotApvlWfId String
     * @param documentId String
     * @return returnMap Map<String, Object>
     * @throws S21NwfException S21NwfException
     */
    private Map<String, Object> getApvlLimitData(String leaseByotApvlWfId, String documentId) throws S21NwfException {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = factory.getContex();
        List<S21NwfProcess> procs = context.getProcessForBiz(leaseByotApvlWfId, documentId);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        for (S21NwfProcess proc : procs) {
            S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
            List<S21NwfUtilBizWorkItem> tasks = p.getTasks();
            boolean isNext = false;
            for (S21NwfUtilBizWorkItem wi : tasks) {
                if (wi.isApprovable()) {
                    if (isNext == true) {
                        returnMap.put(COND_VAL_NUM_01, wi.getCondition().getCondNum1());
                        returnMap.put(ATTRB_COND_VAL_TXT_01, wi.getGroups().get(0));
                        break;
                    } else if (wi.isComplete() == false) {
                        isNext = true;
                        continue;
                    }
                }
            }
        }
        return returnMap;
    }

    // END 2016/03/31 K.Kojima [QC#5531,ADD]

    /**
     * getUserNm
     * @param glblCmpyCd String
     * @param userId String
     * @return String
     */
    private String getUserNm(String glblCmpyCd, String userId) {
        // START 2016/03/10 K.Kojima [QC#5292,MOD]
        // S21_PSNTMsg tmsg = new S21_PSNTMsg();
        // setValue(tmsg.glblCmpyCd, glblCmpyCd);
        // setValue(tmsg.psnCd, userId);
        // tmsg = (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(tmsg);
        // if (tmsg != null) {
        // return
        // ZYPCommonFunc.concatString(tmsg.psnLastNm.getValue(), ",",
        // tmsg.psnFirstNm.getValue());
        // }
        AUTH_PSNTMsg inMsg = new AUTH_PSNTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("usrNm01", userId);
        inMsg.setMaxCount(1);
        inMsg.setSQLID("053");
        AUTH_PSNTMsgArray outMsg = (AUTH_PSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsg.length() != 0) {
            return ZYPCommonFunc.concatString(outMsg.no(0).firstNm.getValue(), " ", outMsg.no(0).lastNm.getValue());
        }
        // END 2016/03/10 K.Kojima [QC#5292,MOD]
        return null;
    }

    // START 2016/03/31 K.Kojima [QC#5531,DEL]
    // /**
    // * execParam
    // * @return S21SsmExecutionParameter
    // */
    // private S21SsmExecutionParameter execParam() {
    // S21SsmExecutionParameter execPrm = new
    // S21SsmExecutionParameter();
    // execPrm.setFetchSize(FETCH_SIZE_MAX);
    // execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
    // execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
    // execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    // return execPrm;
    // }
    // END 2016/03/31 K.Kojima [QC#5531,DEL]

}
