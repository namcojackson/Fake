// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20090818184737000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL0020_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL0020;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NYEL0020 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NYEL0020_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NYEL0020_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NYEL0020_BCMsg
	 */
	public   NYEL0020_BCMsg no(int n){
		return ( NYEL0020_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NYEL0020_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NYEL0020_BCMsg();
	}
}
