// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220405101921000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0800CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0800;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NSAL0800 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSAL0800CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSAL0800CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSAL0800CMsg
	 */
	public   NSAL0800CMsg no(int n){
		return ( NSAL0800CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSAL0800CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSAL0800CMsg();
	}
}