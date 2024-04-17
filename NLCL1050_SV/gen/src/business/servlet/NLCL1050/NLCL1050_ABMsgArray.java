// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160920233608000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1050_ABMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL1050;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLCL1050 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL1050_ABMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL1050_ABMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL1050_ABMsg
	 */
	public   NLCL1050_ABMsg no(int n){
		return ( NLCL1050_ABMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL1050_ABMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL1050_ABMsg();
	}
}
