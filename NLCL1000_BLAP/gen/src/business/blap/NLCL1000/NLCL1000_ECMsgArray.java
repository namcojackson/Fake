// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20120717210015000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1000_ECMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL1000;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLCL1000 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL1000_ECMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL1000_ECMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL1000_ECMsg
	 */
	public   NLCL1000_ECMsg no(int n){
		return ( NLCL1000_ECMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL1000_ECMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL1000_ECMsg();
	}
}