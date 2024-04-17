// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20151224171012000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1790CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1790;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL1790 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1790CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1790CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1790CMsg
	 */
	public   NWAL1790CMsg no(int n){
		return ( NWAL1790CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1790CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1790CMsg();
	}
}
