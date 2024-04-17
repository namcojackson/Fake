// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160222213157000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC271004_locationListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NMZC271004 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMZC271004_locationListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMZC271004_locationListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMZC271004_locationListPMsg
	 */
	public   NMZC271004_locationListPMsg no(int n){
		return ( NMZC271004_locationListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMZC271004_locationListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMZC271004_locationListPMsg();
	}
}
