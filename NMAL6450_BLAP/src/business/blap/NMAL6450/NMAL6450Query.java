/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6450Query
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/22/2010   Fujitsu         N.Sakamoto      Create          N/A                                                         
 *</pre>
 */
package business.blap.NMAL6450;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NMAL6450Query extends S21SsmEZDQuerySupport {
	
	/**
	 * Singleton instance.
	 */
	private static final NMAL6450Query myInstance = new NMAL6450Query();

	/**
	 * Constructor.
	 */
	private NMAL6450Query() {
	    super();
	}

	/**
	 * Singleton instance getter.
	 * @return	NMAL6450Query
	 */
	public static NMAL6450Query getInstance() {
	    return myInstance;
	}

	/**
	 * 「NMAL6450Query.xml」id="getCodeTable"search
	 * @param	cMsg	NMAL6450CMsg
	 * @return	S21SsmEZDResult
	 */
	public S21SsmEZDResult getCodeTable(NMAL6450CMsg bizMsg, NMAL6450SMsg globalMsg) {
		
		return getSsmEZDClient().queryEZDMsgArray( "getCodeTable", bizMsg, globalMsg.A );
	}
    
    public S21SsmEZDResult getScdOrgStru(NMAL6450CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", bizMsg);
        ssmParam.put("slsDate", ZYPDateUtil.getSalesDate());
        return getSsmEZDClient().queryObjectList("getScdOrgStru", ssmParam);
    }
    
}
