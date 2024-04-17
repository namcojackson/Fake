// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190402141924000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1410_RCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1410;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NPAL1410 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1410_RCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1410_RCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1410_RCMsg
	 */
	public   NPAL1410_RCMsg no(int n){
		return ( NPAL1410_RCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1410_RCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1410_RCMsg();
	}
}
