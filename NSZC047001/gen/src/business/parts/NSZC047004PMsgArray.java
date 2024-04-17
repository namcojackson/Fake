// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151015103654000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC047004PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC047004 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC047004PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC047004PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC047004PMsg
	 */
	public   NSZC047004PMsg no(int n){
		return ( NSZC047004PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC047004PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC047004PMsg();
	}
}
