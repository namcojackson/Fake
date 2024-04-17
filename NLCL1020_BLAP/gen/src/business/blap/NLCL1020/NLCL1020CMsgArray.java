// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230413135822000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1020CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL1020;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLCL1020 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL1020CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL1020CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL1020CMsg
	 */
	public   NLCL1020CMsg no(int n){
		return ( NLCL1020CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL1020CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL1020CMsg();
	}
}
