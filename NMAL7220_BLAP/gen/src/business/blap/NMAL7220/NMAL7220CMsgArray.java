// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160219135054000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7220CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7220;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL7220 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL7220CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL7220CMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL7220CMsg
	 */
	public   NMAL7220CMsg no(int n){
		return ( NMAL7220CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL7220CMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL7220CMsg();
	}
}
