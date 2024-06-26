// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160616173433000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMA2660001FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NMA2660001 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMA2660001FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMA2660001FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMA2660001FMsg
	 */
	public   NMA2660001FMsg no(int n){
		return ( NMA2660001FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMA2660001FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMA2660001FMsg();
	}
}
