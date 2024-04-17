// This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20091001155843000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZLL0010CMsgArray.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZLL0010;

import parts.common.*;
import parts.common.EZDCMsgArray;

/**
 * It is ZZLL0010 Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class ZZLL0010CMsgArray extends EZDCMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get ZZLL0010CMsg of the specified element number .
	 * @param n Index Number
	 * @return  ZZLL0010CMsg
	 */
	public   ZZLL0010CMsg no(int n){
		return ( ZZLL0010CMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  ZZLL0010CMsg
	 */
	public EZDMsg getMyComponent() {
		return new ZZLL0010CMsg();
	}
}