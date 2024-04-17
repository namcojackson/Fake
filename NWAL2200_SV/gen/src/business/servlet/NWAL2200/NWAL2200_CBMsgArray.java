// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190724032952000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2200_CBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2200;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL2200 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2200_CBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2200_CBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2200_CBMsg
	 */
	public   NWAL2200_CBMsg no(int n){
		return ( NWAL2200_CBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2200_CBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2200_CBMsg();
	}
}
