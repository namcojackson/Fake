// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170531092249000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2700CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2700;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL2700 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL2700CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL2700CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL2700CMsg
	 */
	public   NMAL2700CMsg no(int n){
		return ( NMAL2700CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL2700CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL2700CMsg();
	}
}
