// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190118085528000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC044001_xxVisitTaskListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC044001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC044001_xxVisitTaskListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC044001_xxVisitTaskListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC044001_xxVisitTaskListPMsg
	 */
	public   NSZC044001_xxVisitTaskListPMsg no(int n){
		return ( NSZC044001_xxVisitTaskListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC044001_xxVisitTaskListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC044001_xxVisitTaskListPMsg();
	}
}