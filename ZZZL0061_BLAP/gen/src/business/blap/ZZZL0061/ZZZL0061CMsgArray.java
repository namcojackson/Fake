// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20110114105713000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0061CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZZL0061;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZZL0061 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0061CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZZL0061CMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZZL0061CMsg
	 */
	public   ZZZL0061CMsg no(int n){
		return ( ZZZL0061CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZZL0061CMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZZL0061CMsg();
	}
}
