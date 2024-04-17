// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160523135413000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL0140CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL0140;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL0140 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL0140CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL0140CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL0140CMsg
	 */
	public   NWAL0140CMsg no(int n){
		return ( NWAL0140CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL0140CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL0140CMsg();
	}
}
