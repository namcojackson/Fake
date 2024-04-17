// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160509164730000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6150BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6150;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL6150 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6150BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6150BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6150BMsg
	 */
	public   NMAL6150BMsg no(int n){
		return ( NMAL6150BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6150BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6150BMsg();
	}
}
