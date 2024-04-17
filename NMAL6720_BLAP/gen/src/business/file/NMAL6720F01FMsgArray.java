// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20171023153406000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6720F01FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NMAL6720F01 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL6720F01FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL6720F01FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL6720F01FMsg
	 */
	public   NMAL6720F01FMsg no(int n){
		return ( NMAL6720F01FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL6720F01FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL6720F01FMsg();
	}
}
