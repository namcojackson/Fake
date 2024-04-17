/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6420Query
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/22/2010   Fujitsu         N.Sakamoto      Create          N/A                                                         
 *</pre>
 */
package business.blap.NMAL6420;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NMAL6420Query extends S21SsmEZDQuerySupport {
	
	/**
	 * Singleton instance.
	 */
	private static final NMAL6420Query myInstance = new NMAL6420Query();

	/**
	 * Constructor.
	 */
	private NMAL6420Query() {
	    super();
	}

	/**
	 * Singleton instance getter.
	 * @return	NMAL6420Query
	 */
	public static NMAL6420Query getInstance() {
	    return myInstance;
	}

	/**
	 * 「NMAL6420Query.xml」id="getCodeTable"search
	 * @param	cMsg	NMAL6420CMsg
	 * @return	S21SsmEZDResult
	 */
	public S21SsmEZDResult getCodeTable(NMAL6420CMsg bizMsg, NMAL6420SMsg globalMsg) {
		
		return getSsmEZDClient().queryEZDMsgArray( "getCodeTable", bizMsg, globalMsg.A );
	}
}
