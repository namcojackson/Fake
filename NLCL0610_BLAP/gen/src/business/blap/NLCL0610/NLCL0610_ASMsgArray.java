// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160412003749000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0610_ASMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0610;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NLCL0610 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL0610_ASMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL0610_ASMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL0610_ASMsg
	 */
	public   NLCL0610_ASMsg no(int n){
		return ( NLCL0610_ASMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL0610_ASMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL0610_ASMsg();
	}
}
