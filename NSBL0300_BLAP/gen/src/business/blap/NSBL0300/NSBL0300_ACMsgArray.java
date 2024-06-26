// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161206104714000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0300_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0300;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSBL0300 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSBL0300_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSBL0300_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSBL0300_ACMsg
	 */
	public   NSBL0300_ACMsg no(int n){
		return ( NSBL0300_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSBL0300_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSBL0300_ACMsg();
	}
}
