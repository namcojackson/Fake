// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151221190714000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC034001PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC034001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC034001PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC034001PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC034001PMsg
	 */
	public   NSZC034001PMsg no(int n){
		return ( NSZC034001PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC034001PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC034001PMsg();
	}
}
