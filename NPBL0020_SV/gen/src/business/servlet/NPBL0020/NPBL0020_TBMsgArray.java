// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230707082300000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPBL0020_TBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPBL0020;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NPBL0020 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPBL0020_TBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPBL0020_TBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPBL0020_TBMsg
	 */
	public   NPBL0020_TBMsg no(int n){
		return ( NPBL0020_TBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPBL0020_TBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPBL0020_TBMsg();
	}
}