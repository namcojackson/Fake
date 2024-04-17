// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160530215809000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1250_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1250;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NPAL1250 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1250_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1250_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1250_ACMsg
	 */
	public   NPAL1250_ACMsg no(int n){
		return ( NPAL1250_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1250_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1250_ACMsg();
	}
}
