// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231027190159000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3060_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3060;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLBL3060 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3060_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3060_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3060_ASMsg
	 */
	public   NLBL3060_ASMsg no(int n){
		return ( NLBL3060_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3060_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3060_ASMsg();
	}
}
