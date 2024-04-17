/*
 *  <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6350Query
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/02/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.blap.NMAL6350;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NMAL6350Query extends S21SsmEZDQuerySupport {
	
	/**
	 * Singleton instance.
	 */
	private static final NMAL6350Query myInstance = new NMAL6350Query();

	/**
	 * Constructor.
	 */
	private NMAL6350Query() {
	    super();
	}

	/**
	 * Singleton instance getter.
	 * @return	NMAL6350Query
	 */
	public static NMAL6350Query getInstance() {
	    return myInstance;
	}

	/**
	 * 「NMAL6340Query.xml」id="getCodeTableData"search
	 * @param	bizMsg	NMAL6350CMsg
	 * @return	S21SsmEZDResult
	 */
	public S21SsmEZDResult getCodeTableData(NMAL6350CMsg bizMsg, NMAL6350SMsg globalMsg) {
		
		return getSsmEZDClient().queryEZDMsgArray( "getCodeTableData", bizMsg, globalMsg.A );
	}

}
