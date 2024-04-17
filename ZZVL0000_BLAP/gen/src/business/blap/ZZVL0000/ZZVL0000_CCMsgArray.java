// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161110145701000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZVL0000_CCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZVL0000;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZVL0000 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZVL0000_CCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZVL0000_CCMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZVL0000_CCMsg
	 */
	public   ZZVL0000_CCMsg no(int n){
		return ( ZZVL0000_CCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZVL0000_CCMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZVL0000_CCMsg();
	}
}
