// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150514171249000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC045001_xxFsrEventListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC045001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC045001_xxFsrEventListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC045001_xxFsrEventListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC045001_xxFsrEventListPMsg
	 */
	public   NSZC045001_xxFsrEventListPMsg no(int n){
		return ( NSZC045001_xxFsrEventListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC045001_xxFsrEventListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC045001_xxFsrEventListPMsg();
	}
}