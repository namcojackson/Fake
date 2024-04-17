// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160729022233000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL4500CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL4500;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL4500 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL4500CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL4500CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL4500CMsg
	 */
	public   NMAL4500CMsg no(int n){
		return ( NMAL4500CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL4500CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL4500CMsg();
	}
}
