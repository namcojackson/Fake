// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180720110758000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3080_PCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3080;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLBL3080 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3080_PCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3080_PCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3080_PCMsg
	 */
	public   NLBL3080_PCMsg no(int n){
		return ( NLBL3080_PCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3080_PCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3080_PCMsg();
	}
}