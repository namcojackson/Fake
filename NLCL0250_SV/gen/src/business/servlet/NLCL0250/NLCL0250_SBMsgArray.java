// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230308144459000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0250_SBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL0250;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLCL0250 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLCL0250_SBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLCL0250_SBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLCL0250_SBMsg
	 */
	public   NLCL0250_SBMsg no(int n){
		return ( NLCL0250_SBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLCL0250_SBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLCL0250_SBMsg();
	}
}
