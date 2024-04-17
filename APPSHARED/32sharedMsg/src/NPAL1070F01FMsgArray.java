// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160508235423000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1070F01FMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDFMsgArray;

/**
 * It is NPAL1070F01 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPAL1070F01FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPAL1070F01FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPAL1070F01FMsg
	 */
	public   NPAL1070F01FMsg no(int n){
		return ( NPAL1070F01FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPAL1070F01FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPAL1070F01FMsg();
	}
}
