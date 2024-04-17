// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20200714155219000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0100_PBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL0100;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL0100 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL0100_PBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL0100_PBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL0100_PBMsg
	 */
	public   NMAL0100_PBMsg no(int n){
		return ( NMAL0100_PBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL0100_PBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL0100_PBMsg();
	}
}