//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20231219170257000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0110_FCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0110 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0110_FCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** FROM_SER_NUM_F1*/
	public final EZDCStringItem              fromSerNum_F1;

    /** THRU_SER_NUM_F1*/
	public final EZDCStringItem              thruSerNum_F1;

    /** LG_SER_NUM_F1*/
	public final EZDCBigDecimalItem              lgSerNum_F1;

    /** MDSE_SER_NUM_RNG_PK_F1*/
	public final EZDCBigDecimalItem              mdseSerNumRngPk_F1;

    /** _EZUpdateDateTime_F1*/
	public final EZDCStringItem              ezUpTime_F1;

    /** _EZUpTimeZone_F1*/
	public final EZDCStringItem              ezUpTimeZone_F1;


	/**
	 * NMAL0110_FCMsg is constructor.
	 * The initialization when the instance of NMAL0110_FCMsg is generated.
	 */
	public NMAL0110_FCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0110_FCMsg is constructor.
	 * The initialization when the instance of NMAL0110_FCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0110_FCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		fromSerNum_F1 = (EZDCStringItem)newItem("fromSerNum_F1");
		thruSerNum_F1 = (EZDCStringItem)newItem("thruSerNum_F1");
		lgSerNum_F1 = (EZDCBigDecimalItem)newItem("lgSerNum_F1");
		mdseSerNumRngPk_F1 = (EZDCBigDecimalItem)newItem("mdseSerNumRngPk_F1");
		ezUpTime_F1 = (EZDCStringItem)newItem("ezUpTime_F1");
		ezUpTimeZone_F1 = (EZDCStringItem)newItem("ezUpTimeZone_F1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0110_FCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0110_FCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"fromSerNum_F1", "fromSerNum_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"thruSerNum_F1", "thruSerNum_F1", "F1", null, TYPE_HANKAKUEISU, "30", null},
	{"lgSerNum_F1", "lgSerNum_F1", "F1", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"mdseSerNumRngPk_F1", "mdseSerNumRngPk_F1", "F1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_F1", "ezUpTime_F1", "F1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_F1", "ezUpTimeZone_F1", "F1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"FROM_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromSerNum_F1
        {"THRU_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thruSerNum_F1
        {"LG_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lgSerNum_F1
        {"MDSE_SER_NUM_RNG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseSerNumRngPk_F1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_F1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_F1
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
