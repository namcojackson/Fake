// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231110100844000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1500_QCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1500;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL1500 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1500_QCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1500_QCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1500_QCMsg
	 */
	public   NWAL1500_QCMsg no(int n){
		return ( NWAL1500_QCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1500_QCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1500_QCMsg();
	}
}
