// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20161011135712000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMXC107001PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NMXC107001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMXC107001PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMXC107001PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMXC107001PMsg
	 */
	public   NMXC107001PMsg no(int n){
		return ( NMXC107001PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMXC107001PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMXC107001PMsg();
	}
}