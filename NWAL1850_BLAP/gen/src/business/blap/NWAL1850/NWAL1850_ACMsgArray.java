// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220602150034000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1850_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1850;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL1850 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1850_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1850_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1850_ACMsg
	 */
	public   NWAL1850_ACMsg no(int n){
		return ( NWAL1850_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1850_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1850_ACMsg();
	}
}