// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20130531130524000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC004001_xxSvcTasNumListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC004001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC004001_xxSvcTasNumListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC004001_xxSvcTasNumListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC004001_xxSvcTasNumListPMsg
	 */
	public   NSZC004001_xxSvcTasNumListPMsg no(int n){
		return ( NSZC004001_xxSvcTasNumListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC004001_xxSvcTasNumListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC004001_xxSvcTasNumListPMsg();
	}
}