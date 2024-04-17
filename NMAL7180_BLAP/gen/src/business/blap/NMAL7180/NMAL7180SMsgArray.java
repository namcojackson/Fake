// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20200218145511000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7180SMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7180;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL7180 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7180SMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7180SMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7180SMsg
	 */
	public   NMAL7180SMsg no(int n){
		return ( NMAL7180SMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7180SMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7180SMsg();
	}
}
