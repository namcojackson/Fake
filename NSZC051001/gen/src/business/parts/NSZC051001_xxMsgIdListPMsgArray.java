// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180208144850000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC051001_xxMsgIdListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC051001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC051001_xxMsgIdListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC051001_xxMsgIdListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC051001_xxMsgIdListPMsg
	 */
	public   NSZC051001_xxMsgIdListPMsg no(int n){
		return ( NSZC051001_xxMsgIdListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC051001_xxMsgIdListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC051001_xxMsgIdListPMsg();
	}
}
