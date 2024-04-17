// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20230314084124000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMA2600001FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NMA2600001 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMA2600001FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMA2600001FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMA2600001FMsg
	 */
	public   NMA2600001FMsg no(int n){
		return ( NMA2600001FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMA2600001FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMA2600001FMsg();
	}
}
