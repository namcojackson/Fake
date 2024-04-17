// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170424202734000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC111001_xxWtyContrListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC111001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC111001_xxWtyContrListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC111001_xxWtyContrListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC111001_xxWtyContrListPMsg
	 */
	public   NSZC111001_xxWtyContrListPMsg no(int n){
		return ( NSZC111001_xxWtyContrListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC111001_xxWtyContrListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC111001_xxWtyContrListPMsg();
	}
}