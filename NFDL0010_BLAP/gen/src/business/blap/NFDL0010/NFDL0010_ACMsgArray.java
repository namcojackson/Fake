// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180801091432000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0010_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0010;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFDL0010 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFDL0010_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0010_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0010_ACMsg
	 */
	public   NFDL0010_ACMsg no(int n){
		return ( NFDL0010_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0010_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0010_ACMsg();
	}
}
