// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20150903222456000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1370CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1370;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NPAL1370 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1370CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1370CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1370CMsg
	 */
	public   NPAL1370CMsg no(int n){
		return ( NPAL1370CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1370CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1370CMsg();
	}
}
