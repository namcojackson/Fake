// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151108195022000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC131001_serNumListPMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDPMsgArray;

/**
 * It is NPZC131001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPZC131001_serNumListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPZC131001_serNumListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPZC131001_serNumListPMsg
	 */
	public   NPZC131001_serNumListPMsg no(int n){
		return ( NPZC131001_serNumListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPZC131001_serNumListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPZC131001_serNumListPMsg();
	}
}
