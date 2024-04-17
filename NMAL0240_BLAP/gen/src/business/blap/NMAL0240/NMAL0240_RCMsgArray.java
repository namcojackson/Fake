// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180314034724000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0240_RCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0240;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL0240 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL0240_RCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL0240_RCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL0240_RCMsg
	 */
	public   NMAL0240_RCMsg no(int n){
		return ( NMAL0240_RCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL0240_RCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL0240_RCMsg();
	}
}