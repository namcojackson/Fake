package business.blap.ZZZL0030;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.ZZZL0030.constant.ZZZL0030Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZZL0030Query extends S21SsmEZDQuerySupport implements ZZZL0030Constant {

	/**
	 * Singleton instance.
	 */
	private static final ZZZL0030Query myInstance = new ZZZL0030Query();

	/**
	 * Constructor.
	 */
    private ZZZL0030Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	ZZZL0030Query
	 */
    public static ZZZL0030Query getInstance() {
        return myInstance;
    }    

    /**
     * Method name: getData
     * <dd>The method explanation: Get data from ONL_PROC_LOG
     * <dd>Remarks:
     * @param   cMsg ZZZL0030CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getData(ZZZL0030CMsg cMsg) {
        
        if (cMsg.jvmNm_S.getValue().equals("All")) {
            cMsg.jvmNm_S.clear();
        }
        List eventList = new ArrayList();
        for (int i=0; i < cMsg.A.getValidCount() ; i++) {
            eventList.add(cMsg.A.no(i).scrAppId_A.getValue());
        }
        
        HashMap queryParam = new HashMap();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("scrAppId", eventList.toArray());
        queryParam.put("jvmNm", cMsg.jvmNm_S.getValue());
        queryParam.put("opsUsrId", cMsg.opsUsrId.getValue());
        queryParam.put("bizStartTs", cMsg.bizStartTs.getValue());
        queryParam.put("bizEndTs", cMsg.bizEndTs.getValue());
        
    	return getSsmEZDClient().queryObjectList("getData", queryParam);
    }
    
    /**
     * Method name: getJvmNm
     * <dd>The method explanation: Get data from ONL_PROC_LOG
     * <dd>Remarks:
     * @param   cMsg ZZZL0040CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getJvmNm(ZZZL0030CMsg cMsg) {
        
        Map<String, Object> param = new HashMap<String, Object>();
        
        param.put("cMsg", cMsg);               
        return getSsmEZDClient().queryObjectList("getJvmNm", param);
    }
}
