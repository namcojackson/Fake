// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160414023201000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0600_RSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0600;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLCL0600 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL0600_RSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL0600_RSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL0600_RSMsg
	 */
	public   NLCL0600_RSMsg no(int n){
		return ( NLCL0600_RSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL0600_RSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL0600_RSMsg();
	}
}
