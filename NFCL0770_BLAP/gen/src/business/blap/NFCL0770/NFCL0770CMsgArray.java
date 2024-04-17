// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20221130162139000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0770CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL0770;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFCL0770 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFCL0770CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFCL0770CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFCL0770CMsg
	 */
	public   NFCL0770CMsg no(int n){
		return ( NFCL0770CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFCL0770CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFCL0770CMsg();
	}
}
