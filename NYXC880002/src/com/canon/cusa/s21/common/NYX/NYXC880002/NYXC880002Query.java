package com.canon.cusa.s21.common.NYX.NYXC880002;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WF_PROC_COND_STS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.calendar.S21SystemDate;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NYXC880002Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/19   Fujitsu         Q10627          Update          QC#21139
 * 2022/12/26   Fujitsu         Mz.Takahashi    Update          QC#60743
 *</pre>
 */
public class NYXC880002Query extends S21SsmEZDQuerySupport {
    /** Singleton instance */
    private static final NYXC880002Query MY_INSTANCE = new NYXC880002Query();

    /**
     * Private constructor
     */
    private NYXC880002Query() {
        super();
    }

    /**
     * Get the NYEL8820Query instance.
     * @return NYEL8820Query instance
     */
    public static NYXC880002Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getWorklist
     * @param params Search Condition
     * @param maxRow Max Row Count
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWorklist(Map<String, Object> params, int maxRow) {
        params.put("curDt", S21SystemDate.getFWCurrentSystemTime("yyyyMMddHHmm"));
        params.put("maxRow", maxRow);
        
        if (params.containsKey("Administrator") && !params.containsKey("OtherUser")){
            return getSsmEZDClient().queryObjectList("getWorklistForAdmin", params);
        } else {
            return getSsmEZDClient().queryObjectList("getWorklist", params);
        }
    }

    /**
     * getWorklistPullDown
     * @param usrId
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getWorklistPullDown(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("usrId", usrId);

        return getSsmEZDClient().queryObjectList("getWorklistPullDownForAdmin", params);

        /*
        if (S21StringUtil.isNotEmpty(usrId)){
            return getSsmEZDClient().queryObjectList("getWorklistPullDown", params);
        } else {
            return getSsmEZDClient().queryObjectList("getWorklistPullDownForAdmin", params);
        }
        */
    }

    /**
     * getWorklistPullDown
     * @param procGrpNm Process Group Name
     * @param procNm Process Name
     * @param taskNm Task Name
     * @param usrId
     * @return S21SsmEZDResult
     */
    /*
    public S21SsmEZDResult getWorklistPullDown(String procNm, String taskNm, String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("usrId", usrId);
        params.put("wfProcNm", procNm);
        params.put("wfWrkItemNm", taskNm);
        params.put("curDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getWorklistPullDown", params);
    }
    */

    /**
     * getWorklistTaskPullDown
     * @param procGrpNm
     * @param procNm
     * @param taskNm
     * @param usrId
     * @return
     */
    public S21SsmEZDResult getWorklistTaskPullDown(String procNm,String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("usrId", usrId);
        params.put("wfProcNm", procNm);

        return getSsmEZDClient().queryObjectList("getWorklistTaskPullDownForAdmin", params);

        /*
        if (S21StringUtil.isNotEmpty(usrId)){
            return getSsmEZDClient().queryObjectList("getWorklistTaskPullDown", params);
        } else {
            return getSsmEZDClient().queryObjectList("getWorklistTaskPullDownForAdmin", params);
        }
        */
    }

    /**
     * getActLog
     * @param procID Process UID
     * @param taskID Task UID
     * @return S21SsmEZDResult
     */
    /*
    public S21SsmEZDResult getActLog(BigDecimal procID, BigDecimal taskID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcPk", procID);
        params.put("wfWrkItemPk", taskID);

        return getSsmEZDClient().queryObjectList("getActLog", params);
    }
    */

    /**
     * getToUsers
     * @param taskID
     * @param usrId
     * @param isDelegateTask
     * @return
     */
    public S21SsmEZDResult getToUsers(BigDecimal taskID, String usrId, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfWrkItemPk", taskID.toPlainString());
        params.put("wfUsrId", usrId);

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
            params.put("PROC_STS_COMPLETE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getToUsers", params);
    }

    /**
     * getToUsers
     * @param taskID
     * @param usrId
     * @param isDelegateTask
     * @return
     */
    public S21SsmEZDResult getToUsers(String taskID, String usrId, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfWrkItemPk", taskID);
        params.put("wfUsrId", usrId);

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
            params.put("PROC_STS_COMPLETE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getToUsers", params);
    }

    /**
     * getLeaveLog
     * @param procID Process UID
     * @param taskID Task UID
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLeaveLog(BigDecimal procID, BigDecimal taskID, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcPk", procID);
        params.put("wfWrkItemPk", taskID);

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getLeaveLog", params);

    }

    /**
     * getProc
     * @param procID Process UID
     * @param usrId user ID
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProc(BigDecimal procID, String usrId, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcPk", procID);

        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("wfUsrId", usrId);
        }

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getProc", params);
    }

    /**
     * @param procName Process Name
     * @param documentId Document ID
     * @param usrId user ID
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProc(String documentId, String usrId, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcDocId", documentId);
        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("wfUsrId", usrId);
        }

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
            params.put("PROC_STS_COMPLETE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getProcByDocID", params);
    }

    /**
     * @param groupNm Group ID
     * @param usrId user ID
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getProcByGroupNm(String groupNm, String usrId, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcTagNm", groupNm);
        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("wfUsrId", usrId);
        }

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
            params.put("PROC_STS_COMPLETE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getProcByGroupNm", params);
    }

    /**
     * @param wfProcPk Process PK
     * @param usrId user ID
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getGroupByProcPk(BigDecimal wfProcPk, String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcPk", wfProcPk);
        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("wfUsrId", usrId);
        }

        return getSsmEZDClient().queryObjectList("getGroupByProcPk", params);
    }

    /**
     * getTask
     * @param procID Process UID
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTask(BigDecimal procID, String usrId, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcPk", procID);
        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("wfUsrId", usrId);
        }

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getTask", params);
    }

    /**
     * getHistory
     * @param procID Process UID
     * @param usrId user ID
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHistory(BigDecimal procID, String usrId, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcPk", procID);
        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("wfUsrId", usrId);
        }

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getHistory", params);
    }

    /**
     * getDetail
     * @param procID Process UID
     * @param taskID Task UID
     * @param usrId
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDetail(BigDecimal procID, BigDecimal taskID, String usrId, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfProcPk", procID);
        params.put("wfWrkItemPk", taskID);
        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("wfUsrId", usrId);
        }

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getDetail", params);
    }

    /**
     * getSubOrdinateList
     * @param usrId
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSubOrdinateList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("usrId", usrId);

        return getSsmEZDClient().queryObjectList("getSubOrdinateList", params);
    }

    /**
     * getSubOrdinate
     * @param subOrdinateId
     * @param usrId
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSubOrdinate(String subOrdinateId, String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("subOrdinateId", subOrdinateId);
        params.put("usrId", usrId);

        return getSsmEZDClient().queryObjectList("getSubOrdinateList", params);
    }

    /**
     * isAdministrator
     * @param usrId
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult isAdministrator(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("usrId", usrId);

        return getSsmEZDClient().queryObjectList("isAdministrator", params);
    }

// 2018/09/19 ADD START QC#21139
    /**
     * getAdministrators
     * @param usrIdList
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAdministrators(List<String> usrIdList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        StringBuffer usrIdListPrmBuf = new StringBuffer();
        int i = 0;
        for (String usrId: usrIdList) {
            if (i > 0) {
                usrIdListPrmBuf.append(",");
            }
            usrIdListPrmBuf.append("'");
            usrIdListPrmBuf.append(usrId);
            usrIdListPrmBuf.append("'");
            i++;
        }
        params.put("usrIdList", usrIdListPrmBuf.toString());

        return getSsmEZDClient().queryObjectList("getAdministrators", params);
    }
// 2018/09/19 ADD END   QC#21139

    /**
     * isReassignable
     * @param taskID
     * @param usrId
     * @return
     */
    public S21SsmEZDResult isRecvReassignable(BigDecimal taskID, String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfWrkItemPk", taskID);
        params.put("usrId", usrId);

        return getSsmEZDClient().queryObjectList("isRecvReassignable", params);
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId, String bizId, String glblCmpyCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("srchOptAplId", bizId);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * getFromUsrGrp
     * @param usrId
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFromUsrGrpList(String usrId) {
        return getFromUsrGrpList(usrId, "", "");
    }

    public S21SsmEZDResult getFromUsrGrpList(String usrId, String wfUsrGrpNm, String wfUsrGrpDescTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("fromUser", true);

        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("usrId", usrId);
        }

        if (S21StringUtil.isNotEmpty(wfUsrGrpNm)) {
            params.put("wfUsrGrpNm", wfUsrGrpNm);
        }

        if (S21StringUtil.isNotEmpty(wfUsrGrpDescTxt)) {
            params.put("wfUsrGrpDescTxt", wfUsrGrpDescTxt);
        }

        return getSsmEZDClient().queryObjectList("getUsrGrp", params);
    }

    /**
     * getToUsrGrp
     * @param usrId
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getToUsrGrpList(String usrId) {
        return getToUsrGrpList(usrId, "", "");
    }

    public S21SsmEZDResult getToUsrGrpList(String usrId, String wfUsrGrpNm, String wfUsrGrpDescTxt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        if (S21StringUtil.isNotEmpty(usrId)) {
            params.put("usrId", usrId);
        }

        if (S21StringUtil.isNotEmpty(wfUsrGrpNm)) {
            params.put("wfUsrGrpNm", wfUsrGrpNm);
        }

        if (S21StringUtil.isNotEmpty(wfUsrGrpDescTxt)) {
            params.put("wfUsrGrpDescTxt", wfUsrGrpDescTxt);
        }

        return getSsmEZDClient().queryObjectList("getUsrGrp", params);
    }

    /**
     * getPsnNm
     * @param assigneeCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPsnNm(String usrId, String usrNm) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnCd", usrId);
        params.put("usrNm", usrNm);

        return getSsmEZDClient().queryObjectList("getPsnNm", params);
    }

// QC#23516 ADD START 2018/04/23
    /**
     * getCommentList
     * @param taskID
     * @param isDelegateTask
     * @return
     */
    public S21SsmEZDResult getCommentList(String taskID, String procStsCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wfWrkItemPk", taskID);

        if (WF_PROC_COND_STS.ALL.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
            params.put("PROC_STS_COMPLETE", procStsCd);
        } else if (WF_PROC_COND_STS.ACTIVE.equals(procStsCd)) {
            params.put("PROC_STS_ACTIVE", procStsCd);
        } else if (WF_PROC_COND_STS.COMPLETED.equals(procStsCd)) {
            params.put("PROC_STS_COMPLETE", procStsCd);
        }

        return getSsmEZDClient().queryObjectList("getCommentList", params);
    }
// QC#23516 ADD END 2018/04/23

}
