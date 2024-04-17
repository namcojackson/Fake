// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161205104550000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0260_OCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0260;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSBL0260 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSBL0260_OCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSBL0260_OCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSBL0260_OCMsg
	 */
	public   NSBL0260_OCMsg no(int n){
		return ( NSBL0260_OCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSBL0260_OCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSBL0260_OCMsg();
	}
}