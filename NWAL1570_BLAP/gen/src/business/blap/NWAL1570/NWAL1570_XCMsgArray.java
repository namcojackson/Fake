// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20240229114306000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1570_XCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1570;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL1570 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1570_XCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1570_XCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1570_XCMsg
	 */
	public   NWAL1570_XCMsg no(int n){
		return ( NWAL1570_XCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1570_XCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1570_XCMsg();
	}
}
