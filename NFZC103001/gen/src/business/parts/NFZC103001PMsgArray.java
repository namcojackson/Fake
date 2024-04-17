// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150925123207000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFZC103001PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NFZC103001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFZC103001PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFZC103001PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFZC103001PMsg
	 */
	public   NFZC103001PMsg no(int n){
		return ( NFZC103001PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFZC103001PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFZC103001PMsg();
	}
}
