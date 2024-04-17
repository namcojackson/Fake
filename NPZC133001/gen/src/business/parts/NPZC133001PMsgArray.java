// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151211112855000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC133001PMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDPMsgArray;

/**
 * It is NPZC133001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPZC133001PMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPZC133001PMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPZC133001PMsg
	 */
	public   NPZC133001PMsg no(int n){
		return ( NPZC133001PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPZC133001PMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPZC133001PMsg();
	}
}
