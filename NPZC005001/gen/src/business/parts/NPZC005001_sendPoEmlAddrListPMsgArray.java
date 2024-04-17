// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20161030205828000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC005001_sendPoEmlAddrListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NPZC005001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPZC005001_sendPoEmlAddrListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPZC005001_sendPoEmlAddrListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPZC005001_sendPoEmlAddrListPMsg
	 */
	public   NPZC005001_sendPoEmlAddrListPMsg no(int n){
		return ( NPZC005001_sendPoEmlAddrListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPZC005001_sendPoEmlAddrListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPZC005001_sendPoEmlAddrListPMsg();
	}
}
