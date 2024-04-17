// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180919150026000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0050_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWCL0050;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWCL0050 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWCL0050_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWCL0050_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWCL0050_BCMsg
	 */
	public   NWCL0050_BCMsg no(int n){
		return ( NWCL0050_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWCL0050_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWCL0050_BCMsg();
	}
}