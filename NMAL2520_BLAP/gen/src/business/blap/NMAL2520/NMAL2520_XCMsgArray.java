// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170307132046000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2520_XCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2520;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL2520 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2520_XCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2520_XCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2520_XCMsg
	 */
	public   NMAL2520_XCMsg no(int n){
		return ( NMAL2520_XCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2520_XCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2520_XCMsg();
	}
}
