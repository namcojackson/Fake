/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6370Query
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/22/2010   Fujitsu         N.Sakamoto      Create          N/A                                                         
 *</pre>
 */
package business.blap.NMAL6370;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NMAL6370Query extends S21SsmEZDQuerySupport {
	
	/**
	 * Singleton instance.
	 */
	private static final NMAL6370Query myInstance = new NMAL6370Query();

	/**
	 * Constructor.
	 */
	private NMAL6370Query() {
	    super();
	}

	/**
	 * Singleton instance getter.
	 * @return	NMAL6370Query
	 */
	public static NMAL6370Query getInstance() {
	    return myInstance;
	}

	/**
	 * 「NMAL6370Query.xml」id="getCodeTable"search
	 * @param	cMsg	NMAL6370CMsg
	 * @return	S21SsmEZDResult
	 */
	public S21SsmEZDResult getCodeTable(NMAL6370CMsg bizMsg, NMAL6370SMsg globalMsg) {
		
		return getSsmEZDClient().queryEZDMsgArray( "getCodeTable", bizMsg, globalMsg.A );
	}
}
