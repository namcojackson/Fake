// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20221219095204000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2010CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2010;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NWAL2010 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2010CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2010CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2010CMsg
	 */
	public   NWAL2010CMsg no(int n){
		return ( NWAL2010CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2010CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2010CMsg();
	}
}