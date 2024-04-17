// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160808100803000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2400_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2400;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL2400 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2400_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2400_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2400_ACMsg
	 */
	public   NWAL2400_ACMsg no(int n){
		return ( NWAL2400_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2400_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2400_ACMsg();
	}
}
