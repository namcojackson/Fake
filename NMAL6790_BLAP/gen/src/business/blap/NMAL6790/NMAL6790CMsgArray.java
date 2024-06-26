// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170802125203000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6790CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6790;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL6790 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6790CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6790CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6790CMsg
	 */
	public   NMAL6790CMsg no(int n){
		return ( NMAL6790CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6790CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6790CMsg();
	}
}
