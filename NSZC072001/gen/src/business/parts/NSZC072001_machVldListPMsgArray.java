// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20161115140119000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC072001_machVldListPMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDPMsgArray;

/**
 * It is NSZC072001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC072001_machVldListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC072001_machVldListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC072001_machVldListPMsg
	 */
	public   NSZC072001_machVldListPMsg no(int n){
		return ( NSZC072001_machVldListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC072001_machVldListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC072001_machVldListPMsg();
	}
}