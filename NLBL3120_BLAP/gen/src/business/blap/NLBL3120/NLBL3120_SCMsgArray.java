// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20151222183346000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3120_SCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3120;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLBL3120 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3120_SCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3120_SCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3120_SCMsg
	 */
	public   NLBL3120_SCMsg no(int n){
		return ( NLBL3120_SCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3120_SCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3120_SCMsg();
	}
}