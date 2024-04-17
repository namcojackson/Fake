// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20181115103514000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC153001_hldListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWZC153001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWZC153001_hldListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWZC153001_hldListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWZC153001_hldListPMsg
	 */
	public   NWZC153001_hldListPMsg no(int n){
		return ( NWZC153001_hldListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWZC153001_hldListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWZC153001_hldListPMsg();
	}
}
