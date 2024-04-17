// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20191030110440000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC183001_cpoInstInfoListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWZC183001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWZC183001_cpoInstInfoListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWZC183001_cpoInstInfoListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWZC183001_cpoInstInfoListPMsg
	 */
	public   NWZC183001_cpoInstInfoListPMsg no(int n){
		return ( NWZC183001_cpoInstInfoListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWZC183001_cpoInstInfoListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWZC183001_cpoInstInfoListPMsg();
	}
}
