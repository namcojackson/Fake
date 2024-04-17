// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20101215123624000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWDL0260_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWDL0260;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWDL0260 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWDL0260_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWDL0260_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWDL0260_ASMsg
	 */
	public   NWDL0260_ASMsg no(int n){
		return ( NWDL0260_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWDL0260_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWDL0260_ASMsg();
	}
}
