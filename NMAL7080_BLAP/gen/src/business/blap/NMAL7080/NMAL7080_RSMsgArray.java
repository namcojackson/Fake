// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161014111823000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7080_RSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7080;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL7080 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7080_RSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7080_RSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7080_RSMsg
	 */
	public   NMAL7080_RSMsg no(int n){
		return ( NMAL7080_RSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7080_RSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7080_RSMsg();
	}
}