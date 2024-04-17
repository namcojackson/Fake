// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160512145458000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7090_CBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7090;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL7090 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7090_CBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7090_CBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7090_CBMsg
	 */
	public   NMAL7090_CBMsg no(int n){
		return ( NMAL7090_CBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7090_CBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7090_CBMsg();
	}
}
