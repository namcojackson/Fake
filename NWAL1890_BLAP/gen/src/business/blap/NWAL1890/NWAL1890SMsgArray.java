// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170814094828000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1890SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1890;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWAL1890 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1890SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1890SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1890SMsg
	 */
	public   NWAL1890SMsg no(int n){
		return ( NWAL1890SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1890SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1890SMsg();
	}
}
