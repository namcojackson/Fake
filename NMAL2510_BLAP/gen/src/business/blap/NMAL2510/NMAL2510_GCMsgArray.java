// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160309122718000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2510_GCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2510;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL2510 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2510_GCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2510_GCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2510_GCMsg
	 */
	public   NMAL2510_GCMsg no(int n){
		return ( NMAL2510_GCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2510_GCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2510_GCMsg();
	}
}
