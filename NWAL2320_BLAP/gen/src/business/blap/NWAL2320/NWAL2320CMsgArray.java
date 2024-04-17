// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170110190040000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2320CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2320;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL2320 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2320CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2320CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2320CMsg
	 */
	public   NWAL2320CMsg no(int n){
		return ( NWAL2320CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2320CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2320CMsg();
	}
}
