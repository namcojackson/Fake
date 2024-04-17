// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160624110222000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0140_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWCL0140;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWCL0140 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWCL0140_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWCL0140_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWCL0140_BCMsg
	 */
	public   NWCL0140_BCMsg no(int n){
		return ( NWCL0140_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWCL0140_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWCL0140_BCMsg();
	}
}
