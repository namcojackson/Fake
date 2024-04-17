// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151007204029000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC302001_UpdateDetailListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NLZC302001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLZC302001_UpdateDetailListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLZC302001_UpdateDetailListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLZC302001_UpdateDetailListPMsg
	 */
	public   NLZC302001_UpdateDetailListPMsg no(int n){
		return ( NLZC302001_UpdateDetailListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLZC302001_UpdateDetailListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLZC302001_UpdateDetailListPMsg();
	}
}