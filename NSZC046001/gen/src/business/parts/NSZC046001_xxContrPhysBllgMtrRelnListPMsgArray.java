// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220328112903000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC046001_xxContrPhysBllgMtrRelnListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NSZC046001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NSZC046001_xxContrPhysBllgMtrRelnListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NSZC046001_xxContrPhysBllgMtrRelnListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NSZC046001_xxContrPhysBllgMtrRelnListPMsg
	 */
	public   NSZC046001_xxContrPhysBllgMtrRelnListPMsg no(int n){
		return ( NSZC046001_xxContrPhysBllgMtrRelnListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NSZC046001_xxContrPhysBllgMtrRelnListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NSZC046001_xxContrPhysBllgMtrRelnListPMsg();
	}
}
