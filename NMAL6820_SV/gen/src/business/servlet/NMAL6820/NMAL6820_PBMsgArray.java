// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20201001143330000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6820_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6820;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL6820 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6820_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6820_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6820_PBMsg
	 */
	public   NMAL6820_PBMsg no(int n){
		return ( NMAL6820_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6820_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6820_PBMsg();
	}
}
