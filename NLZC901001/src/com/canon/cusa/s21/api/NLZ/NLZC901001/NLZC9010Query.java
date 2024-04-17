package com.canon.cusa.s21.api.NLZ.NLZC901001;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public class NLZC9010Query extends S21SsmEZDQuerySupport {
	
    /**
     * Singleton instance.
     */
    private static final NLZC9010Query MY_INSTANCE = new NLZC9010Query();

    /**
     * Constructor.
     */
    public NLZC9010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLZC9000Query
     */
    public static NLZC9010Query getInstance() {
        return MY_INSTANCE;
    }

    
    /**
     * 
     * @param ssmParam
     * @return
     */
    public S21SsmEZDResult searchShipInfo(Map<String, String> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchShipInfo", ssmParam);
    }
    
}
