// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170601124145000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3050CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3050;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLBL3050 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3050CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3050CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3050CMsg
	 */
	public   NLBL3050CMsg no(int n){
		return ( NLBL3050CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3050CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3050CMsg();
	}
}
