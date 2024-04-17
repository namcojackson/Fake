// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160804171419000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC118001_xxMsgIdListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NPZC118001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPZC118001_xxMsgIdListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPZC118001_xxMsgIdListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPZC118001_xxMsgIdListPMsg
	 */
	public   NPZC118001_xxMsgIdListPMsg no(int n){
		return ( NPZC118001_xxMsgIdListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPZC118001_xxMsgIdListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPZC118001_xxMsgIdListPMsg();
	}
}
