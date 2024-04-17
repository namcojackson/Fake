// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20150414104304000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1200_SBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1200;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1200 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1200_SBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1200_SBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1200_SBMsg
	 */
	public   NPAL1200_SBMsg no(int n){
		return ( NPAL1200_SBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1200_SBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1200_SBMsg();
	}
}
