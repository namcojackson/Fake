// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20200210145724000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1510_ESMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1510;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NWAL1510 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL1510_ESMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL1510_ESMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL1510_ESMsg
	 */
	public   NWAL1510_ESMsg no(int n){
		return ( NWAL1510_ESMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL1510_ESMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL1510_ESMsg();
	}
}
