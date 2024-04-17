// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160414023200000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0600_RCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0600;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLCL0600 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL0600_RCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL0600_RCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL0600_RCMsg
	 */
	public   NLCL0600_RCMsg no(int n){
		return ( NLCL0600_RCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL0600_RCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL0600_RCMsg();
	}
}