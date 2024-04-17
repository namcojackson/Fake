// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180515095208000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6710_CBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6710;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL6710 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6710_CBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6710_CBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6710_CBMsg
	 */
	public   NMAL6710_CBMsg no(int n){
		return ( NMAL6710_CBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6710_CBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6710_CBMsg();
	}
}
