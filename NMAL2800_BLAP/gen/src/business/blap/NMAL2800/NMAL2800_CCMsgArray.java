// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180328155318000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2800_CCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2800;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL2800 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2800_CCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2800_CCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2800_CCMsg
	 */
	public   NMAL2800_CCMsg no(int n){
		return ( NMAL2800_CCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2800_CCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2800_CCMsg();
	}
}
