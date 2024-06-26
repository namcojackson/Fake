// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161109182621000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZVL0010CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZVL0010;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZVL0010 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZVL0010CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZVL0010CMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZVL0010CMsg
	 */
	public   ZZVL0010CMsg no(int n){
		return ( ZZVL0010CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZVL0010CMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZVL0010CMsg();
	}
}
