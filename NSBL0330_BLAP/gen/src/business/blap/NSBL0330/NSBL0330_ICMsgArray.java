// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20191001215248000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0330_ICMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0330;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSBL0330 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSBL0330_ICMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSBL0330_ICMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSBL0330_ICMsg
	 */
	public   NSBL0330_ICMsg no(int n){
		return ( NSBL0330_ICMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSBL0330_ICMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSBL0330_ICMsg();
	}
}
