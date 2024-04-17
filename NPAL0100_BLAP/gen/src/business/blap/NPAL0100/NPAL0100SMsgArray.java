// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170531102818000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL0100SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL0100;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NPAL0100 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL0100SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL0100SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL0100SMsg
	 */
	public   NPAL0100SMsg no(int n){
		return ( NPAL0100SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL0100SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL0100SMsg();
	}
}
