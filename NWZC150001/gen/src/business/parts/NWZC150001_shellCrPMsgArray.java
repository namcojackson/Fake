// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150928180816000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC150001_shellCrPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NWZC150001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWZC150001_shellCrPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWZC150001_shellCrPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWZC150001_shellCrPMsg
	 */
	public   NWZC150001_shellCrPMsg no(int n){
		return ( NWZC150001_shellCrPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWZC150001_shellCrPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWZC150001_shellCrPMsg();
	}
}
