// This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230627184230000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPBL0010_PSMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPBL0010;

import parts.common.*;
import parts.common.EZDSMsgArray;

/**
 * It is NPBL0010 Business Application Global Area Message Array class.
 * @author
 * @version XLA200710010
 */
public class NPBL0010_PSMsgArray extends EZDSMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NPBL0010_PSMsg of the specified element number .
	 * @param n Index Number
	 * @return  NPBL0010_PSMsg
	 */
	public   NPBL0010_PSMsg no(int n){
		return ( NPBL0010_PSMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NPBL0010_PSMsg
	 */
	public EZDMsg getMyComponent() {
		return new NPBL0010_PSMsg();
	}
}
