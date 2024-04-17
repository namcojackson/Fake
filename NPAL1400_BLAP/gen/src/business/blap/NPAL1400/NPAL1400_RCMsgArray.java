// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161219012221000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1400_RCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1400;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NPAL1400 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1400_RCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1400_RCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1400_RCMsg
	 */
	public   NPAL1400_RCMsg no(int n){
		return ( NPAL1400_RCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1400_RCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1400_RCMsg();
	}
}
