package business.blap.ZZZL0070;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.blap.ZZZL0070.constant.ZZZL0070Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class ZZZL0070Query extends S21SsmEZDQuerySupport implements ZZZL0070Constant {

	/**
	 * Singleton instance.
	 */
	private static final ZZZL0070Query myInstance = new ZZZL0070Query();

	/**
	 * Constructor.
	 */
    private ZZZL0070Query() {
    	super();
    }
    	
	/**
	 * Singleton instance getter.
	 * @return	ZZZL0070Query
	 */
    public static ZZZL0070Query getInstance() {
        return myInstance;
    }    

    /**
     * Method name: getTableInfo
     * <dd>The method explanation: Get Table Info
     * <dd>Remarks:
     * @param   cMsg ZZZL0070CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getTableInfo(ZZZL0070CMsg cMsg) {
                
        List eventList = new ArrayList();
        for (int i=0; i < cMsg.A.getValidCount() ; i++) {
            eventList.add(cMsg.A.no(i).batProcJobId_A.getValue());
        }
        
//        String startDate = cMsg.xxFromDt.getValue() + "000000000";
//        String endDate = cMsg.xxToDt.getValue() + "235999999";
        String startDate = cMsg.xxFromDt.getValue();
        String endDate = cMsg.xxToDt.getValue();
        
        HashMap queryParam = new HashMap();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("batProcJobId", eventList.toArray());
        queryParam.put("startDate", startDate);
        queryParam.put("endDate", endDate);
        
    	return getSsmEZDClient().queryObjectList("getTableInfo", queryParam);
    }
    
    /**
     * Method name: getJobInfo
     * <dd>The method explanation: Get Job Info
     * <dd>Remarks:
     * @param   cMsg ZZZL0070CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getJobInfo(ZZZL0070CMsg cMsg) {
            
        List eventList = new ArrayList();
        for (int i=0; i < cMsg.A.getValidCount() ; i++) {
            eventList.add(cMsg.A.no(i).batProcJobId_A.getValue());
        }
        
        String startDate = cMsg.xxFromDt.getValue() + "000000000";
        String endDate = cMsg.xxToDt.getValue() + "235999999";
        
        HashMap queryParam = new HashMap();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("batProcJobId", eventList.toArray());
        queryParam.put("startDate", startDate);
        queryParam.put("endDate", endDate);
        
        return getSsmEZDClient().queryObjectList("getJobInfo", queryParam);
    }
    
    /**
     * Method name: getProgramId
     * <dd>The method explanation: Get Program ID
     * <dd>Remarks:
     * @param   cMsg ZZZL0070CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getProgramId(ZZZL0070CMsg cMsg) {
                
        HashMap queryParam = new HashMap();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("batProcJobId", cMsg.batProcJobId.getValue());
        
        return getSsmEZDClient().queryObjectList("getProgramId", queryParam);
    }
}
