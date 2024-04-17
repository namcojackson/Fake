// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151217093833000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC068001_CsaPartsSearchListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC068001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC068001_CsaPartsSearchListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC068001_CsaPartsSearchListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC068001_CsaPartsSearchListPMsg
	 */
	public   NSZC068001_CsaPartsSearchListPMsg no(int n){
		return ( NSZC068001_CsaPartsSearchListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC068001_CsaPartsSearchListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC068001_CsaPartsSearchListPMsg();
	}
}
