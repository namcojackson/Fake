package com.canon.cusa.s21.batch.ZZW.ZZWB004001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public class ZZWB004001Query extends S21SsmEZDQuerySupport {
    /** Singleton instance */
    private static final ZZWB004001Query MY_INSTANCE = new ZZWB004001Query();

    /**
     * Private constructor
     */
    private ZZWB004001Query() {
        super();
    }

    /**
     * Get the ZZWB004001Query instance.
     * @return ZZWB004001Query instance
     */
    public static ZZWB004001Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getProcAndTaskIdByApvlId
     * @param wrkItemApvlId
     * @return
     */
    public S21SsmEZDResult getProcAndTaskIdByApvlId(String wrkItemApvlId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wrkItemApvlId", wrkItemApvlId);

        return getSsmEZDClient().queryObjectList("getProcAndTaskIdByApvlId", params);
    }

    /**
     * getQidByFromAddr
     * @param fromAddr
     * @return
     */
    public S21SsmEZDResult getQidByFromAddr(String fromAddr) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("fromAddr", fromAddr);

        return getSsmEZDClient().queryObjectList("getQidByFromAddr", params);
    }

    /**
     * checkAccessAuthorityByQid
     * @param taskId
     * @param userId
     * @param accessType
     * @return
     */
    /*
    public S21SsmEZDResult checkAccessAuthorityByQid(BigDecimal taskId, String userId, String accessType) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("taskId", taskId);
        params.put("userId", userId);
        params.put("accessType", accessType);

        return getSsmEZDClient().queryObjectList("checkAccessAuthorityByQid", params);
    }
    */

    /**
     * getMlTmplErrId
     * @param taskId
     * @return
     */
    public S21SsmEZDResult getMlTmplRtrnId(String wrkItemApvlId) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wrkItemApvlId", wrkItemApvlId);

        return getSsmEZDClient().queryObjectList("getMlTmplRtrnId", params);
    }

    /**
     * getMlTmplErrId
     * @param taskId
     * @return
     */
    public S21SsmEZDResult getMlTmplErrId(String wrkItemApvlId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("wrkItemApvlId", wrkItemApvlId);

        return getSsmEZDClient().queryObjectList("getMlTmplErrId", params);
    }
}
