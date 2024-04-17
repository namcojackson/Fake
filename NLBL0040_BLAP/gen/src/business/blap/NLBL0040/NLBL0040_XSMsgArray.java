// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20130530053716000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0040_XSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL0040;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLBL0040 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL0040_XSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL0040_XSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL0040_XSMsg
	 */
	public   NLBL0040_XSMsg no(int n){
		return ( NLBL0040_XSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL0040_XSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL0040_XSMsg();
	}
}