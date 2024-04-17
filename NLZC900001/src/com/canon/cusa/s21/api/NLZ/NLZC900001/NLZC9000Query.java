package com.canon.cusa.s21.api.NLZ.NLZC900001;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public class NLZC9000Query extends S21SsmEZDQuerySupport {
	
    /**
     * Singleton instance.
     */
    private static final NLZC9000Query MY_INSTANCE = new NLZC9000Query();

    /**
     * Constructor.
     */
    public NLZC9000Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLZC9000Query
     */
    public static NLZC9000Query getInstance() {
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
    
    
    public S21SsmEZDResult searchShipment(Map<String,String> ssmParam){
        return getSsmEZDClient().queryObjectList("searchShipment", ssmParam);
    }
    
}
