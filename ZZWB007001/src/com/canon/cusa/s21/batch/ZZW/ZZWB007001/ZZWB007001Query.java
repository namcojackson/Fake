package com.canon.cusa.s21.batch.ZZW.ZZWB007001;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public class ZZWB007001Query extends S21SsmEZDQuerySupport {
    /** Singleton instance */
    private static final ZZWB007001Query MY_INSTANCE = new ZZWB007001Query();

    /**
     * Private constructor
     */
    private ZZWB007001Query() {
        super();
    }

    /**
     * Get the ZZWB007001Query instance.
     * @return ZZWB007001Query instance
     */
    public static ZZWB007001Query getInstance() {
        return MY_INSTANCE;
    }

    public S21SsmEZDResult getSuperior(String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("userId", userId);

        return getSsmEZDClient().queryObjectList("getSuperior", params);
    }
    
}
