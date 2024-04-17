// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160305152924000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1760SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1760;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWAL1760 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1760SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1760SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1760SMsg
	 */
	public   NWAL1760SMsg no(int n){
		return ( NWAL1760SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1760SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1760SMsg();
	}
}
