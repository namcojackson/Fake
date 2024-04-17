// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20100526154508000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC045001PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWZC045001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWZC045001PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWZC045001PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWZC045001PMsg
	 */
	public   NWZC045001PMsg no(int n){
		return ( NWZC045001PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWZC045001PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWZC045001PMsg();
	}
}
