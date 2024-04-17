// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20150326172355000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0160_OCMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0160;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is NMAL0160 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMAL0160_OCMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMAL0160_OCMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMAL0160_OCMsg
	 */
	public   NMAL0160_OCMsg no(int n){
		return ( NMAL0160_OCMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMAL0160_OCMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMAL0160_OCMsg();
	}
}
