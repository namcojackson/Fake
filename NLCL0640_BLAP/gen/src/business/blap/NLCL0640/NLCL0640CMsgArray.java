// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230511170928000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0640CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0640;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLCL0640 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL0640CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL0640CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL0640CMsg
	 */
	public   NLCL0640CMsg no(int n){
		return ( NLCL0640CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL0640CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL0640CMsg();
	}
}
