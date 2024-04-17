// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160317151811000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7080_HBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7080;

import parts.common.EZDBMsgArray;
import parts.common.EZDMsg;

/**
 * It is NMAL7080 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7080_HBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7080_HBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7080_HBMsg
	 */
	public   NMAL7080_HBMsg no(int n){
		return ( NMAL7080_HBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7080_HBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7080_HBMsg();
	}
}
