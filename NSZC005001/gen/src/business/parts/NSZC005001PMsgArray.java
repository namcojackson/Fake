// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20230411144945000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC005001PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC005001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC005001PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC005001PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC005001PMsg
	 */
	public   NSZC005001PMsg no(int n){
		return ( NSZC005001PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC005001PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC005001PMsg();
	}
}
