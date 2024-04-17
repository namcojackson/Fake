// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20191109152745000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2240_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2240;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL2240 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2240_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2240_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2240_BCMsg
	 */
	public   NWAL2240_BCMsg no(int n){
		return ( NWAL2240_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2240_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2240_BCMsg();
	}
}