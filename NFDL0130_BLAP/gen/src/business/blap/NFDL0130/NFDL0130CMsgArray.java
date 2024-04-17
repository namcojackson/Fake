// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160912182546000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0130CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0130;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFDL0130 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFDL0130CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0130CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0130CMsg
	 */
	public   NFDL0130CMsg no(int n){
		return ( NFDL0130CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0130CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0130CMsg();
	}
}
