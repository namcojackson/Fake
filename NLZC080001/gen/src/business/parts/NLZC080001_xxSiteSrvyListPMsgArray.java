// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20231205174426000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC080001_xxSiteSrvyListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NLZC080001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLZC080001_xxSiteSrvyListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLZC080001_xxSiteSrvyListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLZC080001_xxSiteSrvyListPMsg
	 */
	public   NLZC080001_xxSiteSrvyListPMsg no(int n){
		return ( NLZC080001_xxSiteSrvyListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLZC080001_xxSiteSrvyListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLZC080001_xxSiteSrvyListPMsg();
	}
}
