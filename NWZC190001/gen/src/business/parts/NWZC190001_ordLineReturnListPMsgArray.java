// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170705175913000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC190001_ordLineReturnListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWZC190001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWZC190001_ordLineReturnListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWZC190001_ordLineReturnListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWZC190001_ordLineReturnListPMsg
	 */
	public   NWZC190001_ordLineReturnListPMsg no(int n){
		return ( NWZC190001_ordLineReturnListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWZC190001_ordLineReturnListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWZC190001_ordLineReturnListPMsg();
	}
}
