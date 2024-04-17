// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180418175317000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC004001_serialInfoListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NLZC004001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLZC004001_serialInfoListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLZC004001_serialInfoListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLZC004001_serialInfoListPMsg
	 */
	public   NLZC004001_serialInfoListPMsg no(int n){
		return ( NLZC004001_serialInfoListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLZC004001_serialInfoListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLZC004001_serialInfoListPMsg();
	}
}
