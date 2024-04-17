// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20100915181341000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWXL0010_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWXL0010;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWXL0010 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWXL0010_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWXL0010_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWXL0010_ASMsg
	 */
	public   NWXL0010_ASMsg no(int n){
		return ( NWXL0010_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWXL0010_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWXL0010_ASMsg();
	}
}
