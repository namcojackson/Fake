// This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150918160325000
// Velocity macro:EZDMsgArray.vm  V200604010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC610001_RelatedBillOrShipListPMsgArray.java Copyright FUJITSU LIMITED 2007
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
 * It is NMZC610001 Common Business Component Interface Message Array class.
 * @author
 * @version XLA200710010
 */
public class NMZC610001_RelatedBillOrShipListPMsgArray extends EZDPMsgArray {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	/**
	 * get NMZC610001_RelatedBillOrShipListPMsg of the specified element number .
	 * @param n Index Number
	 * @return  NMZC610001_RelatedBillOrShipListPMsg
	 */
	public   NMZC610001_RelatedBillOrShipListPMsg no(int n){
		return ( NMZC610001_RelatedBillOrShipListPMsg)get(n);
	}


	/**
	 * The message career type in the array is acquired.
	 * @return  NMZC610001_RelatedBillOrShipListPMsg
	 */
	public EZDMsg getMyComponent() {
		return new NMZC610001_RelatedBillOrShipListPMsg();
	}
}
