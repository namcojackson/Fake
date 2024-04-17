// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530084857000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0200_BSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0200;

import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;

/**
 * It is NMAL0200 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL0200_BSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL0200_BSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL0200_BSMsg
	 */
	public   NMAL0200_BSMsg no(int n){
		return ( NMAL0200_BSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL0200_BSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL0200_BSMsg();
	}
}
