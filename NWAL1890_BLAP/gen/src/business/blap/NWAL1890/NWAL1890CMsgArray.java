// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170814094826000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1890CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1890;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL1890 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1890CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1890CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1890CMsg
	 */
	public   NWAL1890CMsg no(int n){
		return ( NWAL1890CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1890CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1890CMsg();
	}
}
