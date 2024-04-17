// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170823190013000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0110SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0110;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NFDL0110 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFDL0110SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFDL0110SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFDL0110SMsg
	 */
	public   NFDL0110SMsg no(int n){
		return ( NFDL0110SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFDL0110SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFDL0110SMsg();
	}
}
