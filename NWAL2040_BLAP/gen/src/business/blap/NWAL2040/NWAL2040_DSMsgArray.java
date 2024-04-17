// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170925152059000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2040_DSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2040;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWAL2040 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2040_DSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2040_DSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2040_DSMsg
	 */
	public   NWAL2040_DSMsg no(int n){
		return ( NWAL2040_DSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2040_DSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2040_DSMsg();
	}
}
