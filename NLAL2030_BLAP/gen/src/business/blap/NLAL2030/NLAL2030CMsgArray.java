// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230227160343000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2030CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL2030;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NLAL2030 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLAL2030CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLAL2030CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLAL2030CMsg
	 */
	public   NLAL2030CMsg no(int n){
		return ( NLAL2030CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLAL2030CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLAL2030CMsg();
	}
}