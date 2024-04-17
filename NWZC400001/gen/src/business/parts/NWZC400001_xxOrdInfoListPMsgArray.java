// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151116142007000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC400001_xxOrdInfoListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWZC400001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWZC400001_xxOrdInfoListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWZC400001_xxOrdInfoListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWZC400001_xxOrdInfoListPMsg
	 */
	public   NWZC400001_xxOrdInfoListPMsg no(int n){
		return ( NWZC400001_xxOrdInfoListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWZC400001_xxOrdInfoListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWZC400001_xxOrdInfoListPMsg();
	}
}
