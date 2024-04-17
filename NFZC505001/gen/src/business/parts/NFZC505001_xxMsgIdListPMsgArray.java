// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160915202848000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFZC505001_xxMsgIdListPMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDPMsgArray;

/**
 * It is NFZC505001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NFZC505001_xxMsgIdListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NFZC505001_xxMsgIdListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NFZC505001_xxMsgIdListPMsg
	 */
	public   NFZC505001_xxMsgIdListPMsg no(int n){
		return ( NFZC505001_xxMsgIdListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NFZC505001_xxMsgIdListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NFZC505001_xxMsgIdListPMsg();
	}
}