// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170213142338000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0170_ACMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0170;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL0170 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL0170_ACMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL0170_ACMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL0170_ACMsg
	 */
	public   NMAL0170_ACMsg no(int n){
		return ( NMAL0170_ACMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL0170_ACMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL0170_ACMsg();
	}
}
