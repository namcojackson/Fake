// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170509001412000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC172002PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWZC172002 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWZC172002PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWZC172002PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWZC172002PMsg
	 */
	public   NWZC172002PMsg no(int n){
		return ( NWZC172002PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWZC172002PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWZC172002PMsg();
	}
}