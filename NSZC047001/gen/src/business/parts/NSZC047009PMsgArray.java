// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151015112914000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC047009PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC047009 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC047009PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC047009PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC047009PMsg
	 */
	public   NSZC047009PMsg no(int n){
		return ( NSZC047009PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC047009PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC047009PMsg();
	}
}
