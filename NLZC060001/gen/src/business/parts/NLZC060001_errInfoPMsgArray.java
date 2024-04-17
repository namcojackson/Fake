// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160719162547000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC060001_errInfoPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NLZC060001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLZC060001_errInfoPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLZC060001_errInfoPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLZC060001_errInfoPMsg
	 */
	public   NLZC060001_errInfoPMsg no(int n){
		return ( NLZC060001_errInfoPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLZC060001_errInfoPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLZC060001_errInfoPMsg();
	}
}