// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230207094043000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1270_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1270;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NPAL1270 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1270_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1270_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1270_ACMsg
	 */
	public   NPAL1270_ACMsg no(int n){
		return ( NPAL1270_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1270_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1270_ACMsg();
	}
}
