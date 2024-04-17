// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160627164912000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8860_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8860;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NYEL8860 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NYEL8860_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NYEL8860_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NYEL8860_ASMsg
	 */
	public   NYEL8860_ASMsg no(int n){
		return ( NYEL8860_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NYEL8860_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NYEL8860_ASMsg();
	}
}
