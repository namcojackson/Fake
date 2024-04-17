// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20110112212735000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0070CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZZL0070;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZZL0070 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0070CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZZL0070CMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZZL0070CMsg
	 */
	public   ZZZL0070CMsg no(int n){
		return ( ZZZL0070CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZZL0070CMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZZL0070CMsg();
	}
}