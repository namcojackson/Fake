// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160307143519000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2330_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2330;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NWAL2330 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NWAL2330_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NWAL2330_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NWAL2330_PBMsg
	 */
	public   NWAL2330_PBMsg no(int n){
		return ( NWAL2330_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NWAL2330_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NWAL2330_PBMsg();
	}
}