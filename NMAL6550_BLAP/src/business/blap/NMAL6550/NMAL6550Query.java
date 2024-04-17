/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6550Query
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.blap.NMAL6550;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NMAL6550Query extends S21SsmEZDQuerySupport {
	
	/**
	 * Singleton instance.
	 */
	private static final NMAL6550Query myInstance = new NMAL6550Query();

	/**
	 * Constructor.
	 */
	private NMAL6550Query() {
	    super();
	}

	/**
	 * Singleton instance getter.
	 * @return	NMAL6550Query
	 */
	public static NMAL6550Query getInstance() {
	    return myInstance;
	}

	/**
	 * 「NMAL6340Query.xml」id="getCodeTableData"search
	 * @param	bizMsg	NMAL6550CMsg
	 * @return	S21SsmEZDResult
	 */
	public S21SsmEZDResult getCodeTableData(NMAL6550CMsg bizMsg, NMAL6550SMsg globalMsg) {
		
		return getSsmEZDClient().queryEZDMsgArray( "getCodeTableData", bizMsg, globalMsg.A );
	}

}
