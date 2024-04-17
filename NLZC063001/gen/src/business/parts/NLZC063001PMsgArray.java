// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160315193635000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC063001PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NLZC063001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLZC063001PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLZC063001PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLZC063001PMsg
	 */
	public   NLZC063001PMsg no(int n){
		return ( NLZC063001PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLZC063001PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLZC063001PMsg();
	}
}
