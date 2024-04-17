// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180117134628000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWXC005001_xxMsgIdListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWXC005001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWXC005001_xxMsgIdListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWXC005001_xxMsgIdListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWXC005001_xxMsgIdListPMsg
	 */
	public   NWXC005001_xxMsgIdListPMsg no(int n){
		return ( NWXC005001_xxMsgIdListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWXC005001_xxMsgIdListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWXC005001_xxMsgIdListPMsg();
	}
}
