// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230808131412000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL2020_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL2020;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NLBL2020 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NLBL2020_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NLBL2020_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NLBL2020_PBMsg
	 */
	public   NLBL2020_PBMsg no(int n){
		return ( NLBL2020_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NLBL2020_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NLBL2020_PBMsg();
	}
}