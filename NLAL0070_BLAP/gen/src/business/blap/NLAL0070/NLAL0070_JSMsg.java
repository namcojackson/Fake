//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161216012747000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL0070_JSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL0070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL0070 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL0070_JSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpdateDateTime_J1*/
	public final EZDSStringItem              ezUpTime_J1;

    /** WH_SCHD_REF_KEY_NUM_SQ_J1*/
	public final EZDSBigDecimalItem              whSchdRefKeyNumSq_J1;

    /** INVTY_LOC_CD_J1*/
	public final EZDSStringItem              invtyLocCd_J1;

    /** RWS_REF_NUM_J1*/
	public final EZDSStringItem              rwsRefNum_J1;


	/**
	 * NLAL0070_JSMsg is constructor.
	 * The initialization when the instance of NLAL0070_JSMsg is generated.
	 */
	public NLAL0070_JSMsg() {
		this(false, -1);
	}

	/**
	 * NLAL0070_JSMsg is constructor.
	 * The initialization when the instance of NLAL0070_JSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL0070_JSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTime_J1 = (EZDSStringItem)newItem("ezUpTime_J1");
		whSchdRefKeyNumSq_J1 = (EZDSBigDecimalItem)newItem("whSchdRefKeyNumSq_J1");
		invtyLocCd_J1 = (EZDSStringItem)newItem("invtyLocCd_J1");
		rwsRefNum_J1 = (EZDSStringItem)newItem("rwsRefNum_J1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL0070_JSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL0070_JSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTime_J1", "ezUpTime_J1", "J1", null, TYPE_HANKAKUEISU, "17", null},
	{"whSchdRefKeyNumSq_J1", "whSchdRefKeyNumSq_J1", "J1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invtyLocCd_J1", "invtyLocCd_J1", "J1", null, TYPE_HANKAKUEISU, "20", null},
	{"rwsRefNum_J1", "rwsRefNum_J1", "J1", null, TYPE_HANKAKUEISU, "15", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_J1
        {"WH_SCHD_REF_KEY_NUM_SQ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whSchdRefKeyNumSq_J1
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_J1
        {"RWS_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsRefNum_J1
	};

	/**
	 * get Array of common (basic) data.
	 * @return Array of common (basis) data
	 */
	protected String[][] getBaseContents() {
		return BASE_CONTENTS;
	}

	/**
	 * get Array of Display Field.
	 * @return Array of  Display  Fields
	 */
	protected String[][] getDispContents() {
		return DISP_CONTENTS;
	}

}
