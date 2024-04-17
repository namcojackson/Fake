// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231010145517000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1840_ECMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1840;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL1840 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1840_ECMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1840_ECMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1840_ECMsg
	 */
	public   NWAL1840_ECMsg no(int n){
		return ( NWAL1840_ECMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1840_ECMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1840_ECMsg();
	}
}
