// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170302155332000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NYEL8850_BCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NYEL8850;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NYEL8850 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NYEL8850_BCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NYEL8850_BCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NYEL8850_BCMsg
	 */
	public   NYEL8850_BCMsg no(int n){
		return ( NYEL8850_BCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NYEL8850_BCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NYEL8850_BCMsg();
	}
}