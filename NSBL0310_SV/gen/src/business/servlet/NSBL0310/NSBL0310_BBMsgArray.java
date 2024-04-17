// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190402130247000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0310_BBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0310;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NSBL0310 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSBL0310_BBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSBL0310_BBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSBL0310_BBMsg
	 */
	public   NSBL0310_BBMsg no(int n){
		return ( NSBL0310_BBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSBL0310_BBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSBL0310_BBMsg();
	}
}