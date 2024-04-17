// This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160912104824000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010F14FMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NMAL7010F14 File Layout Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010F14FMsgArray extends EZDFMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7010F14FMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7010F14FMsg
	 */
	public   NMAL7010F14FMsg no(int n){
		return ( NMAL7010F14FMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7010F14FMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7010F14FMsg();
	}
}
