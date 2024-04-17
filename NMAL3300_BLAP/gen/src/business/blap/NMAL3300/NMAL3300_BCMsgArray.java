// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180727133625000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL3300_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL3300;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL3300 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL3300_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL3300_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL3300_BCMsg
	 */
	public   NMAL3300_BCMsg no(int n){
		return ( NMAL3300_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL3300_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL3300_BCMsg();
	}
}
