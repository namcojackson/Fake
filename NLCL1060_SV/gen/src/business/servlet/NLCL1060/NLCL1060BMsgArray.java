// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230522152025000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1060BMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL1060;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLCL1060 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL1060BMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL1060BMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL1060BMsg
	 */
	public   NLCL1060BMsg no(int n){
		return ( NLCL1060BMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL1060BMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL1060BMsg();
	}
}
