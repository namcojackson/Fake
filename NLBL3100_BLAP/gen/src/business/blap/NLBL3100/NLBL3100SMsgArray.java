// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20151113002952000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3100SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3100;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLBL3100 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL3100SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL3100SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL3100SMsg
	 */
	public   NLBL3100SMsg no(int n){
		return ( NLBL3100SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL3100SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL3100SMsg();
	}
}
