// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180628172810000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3080SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3080;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFCL3080 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL3080SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3080SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3080SMsg
	 */
	public   NFCL3080SMsg no(int n){
		return ( NFCL3080SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3080SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3080SMsg();
	}
}
