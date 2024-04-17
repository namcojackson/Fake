// This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151028182309000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0230_IBMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL0230;

import parts.common.*;
import parts.common.EZDBMsgArray;

/**
 * It is NMAL0230 Screen Data Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL0230_IBMsgArray extends EZDBMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL0230_IBMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL0230_IBMsg
	 */
	public   NMAL0230_IBMsg no(int n){
		return ( NMAL0230_IBMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL0230_IBMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL0230_IBMsg();
	}
}
