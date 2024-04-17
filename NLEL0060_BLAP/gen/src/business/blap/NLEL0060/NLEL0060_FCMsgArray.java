// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180828143952000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0060_FCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLEL0060;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLEL0060 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLEL0060_FCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLEL0060_FCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLEL0060_FCMsg
	 */
	public   NLEL0060_FCMsg no(int n){
		return ( NLEL0060_FCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLEL0060_FCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLEL0060_FCMsg();
	}
}
