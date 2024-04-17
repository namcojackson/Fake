// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20171226042351000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC047008PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC047008 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC047008PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC047008PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC047008PMsg
	 */
	public   NSZC047008PMsg no(int n){
		return ( NSZC047008PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC047008PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC047008PMsg();
	}
}