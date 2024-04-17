// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161116111701000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL3000_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL3000;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL3000 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL3000_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL3000_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL3000_ACMsg
	 */
	public   NMAL3000_ACMsg no(int n){
		return ( NMAL3000_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL3000_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL3000_ACMsg();
	}
}
