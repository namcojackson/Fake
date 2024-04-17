// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20130814144815000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6670SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6670;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL6670 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6670SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6670SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6670SMsg
	 */
	public   NMAL6670SMsg no(int n){
		return ( NMAL6670SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6670SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6670SMsg();
	}
}
