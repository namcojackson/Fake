// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180628172809000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3080_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3080;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFCL3080 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL3080_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3080_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3080_ACMsg
	 */
	public   NFCL3080_ACMsg no(int n){
		return ( NFCL3080_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3080_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3080_ACMsg();
	}
}
