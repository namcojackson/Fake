// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180307150919000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3070SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3070;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLBL3070 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3070SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3070SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3070SMsg
	 */
	public   NLBL3070SMsg no(int n){
		return ( NLBL3070SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3070SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3070SMsg();
	}
}
