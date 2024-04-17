// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230602103740000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0020_ICMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0020;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NFDL0020 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFDL0020_ICMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0020_ICMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0020_ICMsg
	 */
	public   NFDL0020_ICMsg no(int n){
		return ( NFDL0020_ICMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0020_ICMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0020_ICMsg();
	}
}
