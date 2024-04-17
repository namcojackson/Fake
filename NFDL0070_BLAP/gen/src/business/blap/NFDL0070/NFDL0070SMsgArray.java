// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180724104456000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0070SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0070;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFDL0070 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFDL0070SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0070SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0070SMsg
	 */
	public   NFDL0070SMsg no(int n){
		return ( NFDL0070SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0070SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0070SMsg();
	}
}
