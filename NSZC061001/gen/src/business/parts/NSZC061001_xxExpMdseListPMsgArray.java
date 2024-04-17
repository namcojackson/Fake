// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20200615223318000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC061001_xxExpMdseListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC061001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC061001_xxExpMdseListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC061001_xxExpMdseListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC061001_xxExpMdseListPMsg
	 */
	public   NSZC061001_xxExpMdseListPMsg no(int n){
		return ( NSZC061001_xxExpMdseListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC061001_xxExpMdseListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC061001_xxExpMdseListPMsg();
	}
}
