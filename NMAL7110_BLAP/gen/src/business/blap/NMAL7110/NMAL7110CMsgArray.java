// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20151223200350000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7110CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7110;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL7110 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7110CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7110CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7110CMsg
	 */
	public   NMAL7110CMsg no(int n){
		return ( NMAL7110CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7110CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7110CMsg();
	}
}