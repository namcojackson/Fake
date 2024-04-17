// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230901190118000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1160_LSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1160;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NPAL1160 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1160_LSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1160_LSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1160_LSMsg
	 */
	public   NPAL1160_LSMsg no(int n){
		return ( NPAL1160_LSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1160_LSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1160_LSMsg();
	}
}
