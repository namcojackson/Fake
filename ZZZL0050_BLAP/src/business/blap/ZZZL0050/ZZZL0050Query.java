package business.blap.ZZZL0050;

import java.util.HashMap;
import java.util.Map;

import business.blap.ZZZL0050.constant.ZZZL0050Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZZL0050Query extends S21SsmEZDQuerySupport implements ZZZL0050Constant {

	/**
	 * Singleton instance.
	 */
	private static final ZZZL0050Query myInstance = new ZZZL0050Query();

	/**
	 * Constructor.
	 */
    private ZZZL0050Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	ZZZL0050Query
	 */
    public static ZZZL0050Query getInstance() {
        return myInstance;
    }    

    /**
     * Method name: getData
     * <dd>The method explanation: Get data from ONL_PROC_CONFIG
     * <dd>Remarks:
     * @param   cMsg ZZZL0050CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getData(ZZZL0050CMsg cMsg) {
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cMsg", cMsg);
    	return getSsmEZDClient().queryObjectList("getData", param);
    }
    
    /**
     * Method name: getJvmNm
     * <dd>The method explanation: Get data from ONL_PROC_LOG
     * <dd>Remarks:
     * @param   cMsg ZZZL0050CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getJvmNm(ZZZL0050CMsg cMsg) {
        
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("cMsg", cMsg);               
        return getSsmEZDClient().queryObjectList("getJvmNm", param);
    }
}
