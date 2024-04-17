// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20110118104527000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0071_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZZL0071;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZZL0071 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0071_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZZL0071_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZZL0071_BCMsg
	 */
	public   ZZZL0071_BCMsg no(int n){
		return ( ZZZL0071_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZZL0071_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZZL0071_BCMsg();
	}
}
