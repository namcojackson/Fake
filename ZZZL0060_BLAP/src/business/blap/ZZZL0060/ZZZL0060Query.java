package business.blap.ZZZL0060;

import java.util.HashMap;
import java.util.Map;

import business.blap.ZZZL0060.constant.ZZZL0060Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZZL0060Query extends S21SsmEZDQuerySupport implements ZZZL0060Constant {

	/**
	 * Singleton instance.
	 */
	private static final ZZZL0060Query myInstance = new ZZZL0060Query();

	/**
	 * Constructor.
	 */
    private ZZZL0060Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	ZZZL0060Query
	 */
    public static ZZZL0060Query getInstance() {
        return myInstance;
    }    

    /**
     * Method name: getTableList
     * <dd>The method explanation: Get data from BAT_TBL_REL
     * <dd>Remarks:
     * @param   cMsg ZZZL0060CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getTableList(ZZZL0060CMsg cMsg) {
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cMsg", cMsg);
    	return getSsmEZDClient().queryObjectList("searchTableList", param);
    }
}
