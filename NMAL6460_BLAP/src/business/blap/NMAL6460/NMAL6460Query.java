/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6460Query
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 06/22/2010   Fujitsu         N.Sakamoto      Create          N/A                                                         
 *</pre>
 */
package business.blap.NMAL6460;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NMAL6460Query extends S21SsmEZDQuerySupport {
	
	/**
	 * Singleton instance.
	 */
	private static final NMAL6460Query myInstance = new NMAL6460Query();

	/**
	 * Constructor.
	 */
	private NMAL6460Query() {
	    super();
	}

	/**
	 * Singleton instance getter.
	 * @return	NMAL6460Query
	 */
	public static NMAL6460Query getInstance() {
	    return myInstance;
	}

	/**
	 * 「NMAL6460Query.xml」id="getCodeTable"search
	 * @param	cMsg	NMAL6460CMsg
	 * @return	S21SsmEZDResult
	 */
	public S21SsmEZDResult getCodeTable(NMAL6460CMsg bizMsg, NMAL6460SMsg globalMsg) {
		
		return getSsmEZDClient().queryEZDMsgArray( "getCodeTable", bizMsg, globalMsg.A );
	}
}
