// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170412111640000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0210_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0210;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSBL0210 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSBL0210_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSBL0210_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSBL0210_ACMsg
	 */
	public   NSBL0210_ACMsg no(int n){
		return ( NSBL0210_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSBL0210_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSBL0210_ACMsg();
	}
}
