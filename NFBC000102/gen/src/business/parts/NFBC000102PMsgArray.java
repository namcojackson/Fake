// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20100525093631000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBC000102PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NFBC000102 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFBC000102PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFBC000102PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFBC000102PMsg
	 */
	public   NFBC000102PMsg no(int n){
		return ( NFBC000102PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFBC000102PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFBC000102PMsg();
	}
}