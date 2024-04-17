// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180628172809000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3080CMsgArray.java Copyright FUJITSU LIMITED 2007
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
public class NFCL3080CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3080CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3080CMsg
	 */
	public   NFCL3080CMsg no(int n){
		return ( NFCL3080CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3080CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3080CMsg();
	}
}
