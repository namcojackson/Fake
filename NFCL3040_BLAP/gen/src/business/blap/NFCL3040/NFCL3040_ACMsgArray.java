// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180615205728000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3040_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3040;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFCL3040 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL3040_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL3040_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL3040_ACMsg
	 */
	public   NFCL3040_ACMsg no(int n){
		return ( NFCL3040_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL3040_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL3040_ACMsg();
	}
}