// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20080715120225000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZZLQ91000PMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is ZZZLQ91000 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZZLQ91000PMsgArray extends EZDPMsgArray {

	/**
	 * get ZZZLQ91000PMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZZLQ91000PMsg
	 */
	public   ZZZLQ91000PMsg no(int n){
		return ( ZZZLQ91000PMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZZLQ91000PMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZZLQ91000PMsg();
	}
}