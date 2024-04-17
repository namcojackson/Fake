// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180912153217000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7270_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7270;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL7270 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7270_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7270_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7270_PBMsg
	 */
	public   NMAL7270_PBMsg no(int n){
		return ( NMAL7270_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7270_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7270_PBMsg();
	}
}
