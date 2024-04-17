// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180910183403000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC054001PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC054001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC054001PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC054001PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC054001PMsg
	 */
	public   NSZC054001PMsg no(int n){
		return ( NSZC054001PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC054001PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC054001PMsg();
	}
}
