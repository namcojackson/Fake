// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20140303122610000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0040CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0040;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLGL0040 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLGL0040CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLGL0040CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLGL0040CMsg
	 */
	public   NLGL0040CMsg no(int n){
		return ( NLGL0040CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLGL0040CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLGL0040CMsg();
	}
}