/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Class name: NMAL6670Query
 * 
 * Date         Company         Name            Create/Update   Defect No                                                                                                                       
 * ----------------------------------------------------------------------                                                                                                                       
 * 2013/08/14   Fujitsu         K.Kimura        Create          WDS#1458 Installment Invoice modification                                                         
 *</pre>
 */
package business.blap.NMAL6670;

import business.blap.NMAL6670.NMAL6670Query;
import business.blap.NMAL6670.NMAL6670CMsg;
import business.blap.NMAL6670.NMAL6670SMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

public final class NMAL6670Query extends S21SsmEZDQuerySupport {
	
	/**
	 * Singleton instance.
	 */
	private static final NMAL6670Query myInstance = new NMAL6670Query();

	/**
	 * Constructor.
	 */
	private NMAL6670Query() {
	    super();
	}

	/**
	 * Singleton instance getter.
	 * @return	NMAL6670Query
	 */
	public static NMAL6670Query getInstance() {
	    return myInstance;
	}

	/**
	 * 「NMAL6670Query.xml」id="getCodeTable"search
	 * @param	cMsg	NMAL6670CMsg
	 * @return	S21SsmEZDResult
	 */
	public S21SsmEZDResult getCodeTable(NMAL6670CMsg bizMsg, NMAL6670SMsg globalMsg) {
		
		return getSsmEZDClient().queryEZDMsgArray( "getCodeTable", bizMsg, globalMsg.A );
	}
    
}
