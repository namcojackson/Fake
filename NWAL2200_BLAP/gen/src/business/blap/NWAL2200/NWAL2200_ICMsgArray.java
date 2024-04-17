// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180125143359000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2200_ICMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2200;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL2200 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2200_ICMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2200_ICMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2200_ICMsg
	 */
	public   NWAL2200_ICMsg no(int n){
		return ( NWAL2200_ICMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2200_ICMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2200_ICMsg();
	}
}
