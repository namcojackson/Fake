// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180328151516000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1360CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1360;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NPAL1360 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1360CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1360CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1360CMsg
	 */
	public   NPAL1360CMsg no(int n){
		return ( NPAL1360CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1360CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1360CMsg();
	}
}
