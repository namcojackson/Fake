// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230404080654000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1500_SBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1500;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPAL1500 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1500_SBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1500_SBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1500_SBMsg
	 */
	public   NPAL1500_SBMsg no(int n){
		return ( NPAL1500_SBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1500_SBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1500_SBMsg();
	}
}
