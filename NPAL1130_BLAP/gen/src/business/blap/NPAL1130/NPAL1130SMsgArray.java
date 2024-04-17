// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230417172251000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1130SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1130;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NPAL1130 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1130SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1130SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1130SMsg
	 */
	public   NPAL1130SMsg no(int n){
		return ( NPAL1130SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1130SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1130SMsg();
	}
}
