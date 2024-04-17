//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20081229173029000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *XXXQ990000_APMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZQ910000 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZQ910000_APMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZBusinessApplicationID_A1*/
	public final EZDPStringItem              ezBusinessID_A1;

    /** _EZCancelFlag_A1*/
	public final EZDPStringItem              ezCancelFlag_A1;

    /** _EZOnlineOperationStartTime_A1*/
	public final EZDPStringItem              ezOnlStartTime_A1;

    /** _EZOnlineOperationEndTime_A1*/
	public final EZDPStringItem              ezOnlEndTime_A1;

    /** _EZOnlineBlockingFlag_A1*/
	public final EZDPStringItem              ezOnlStopFlag_A1;

    /** _EZCompanyCode_A1*/
	public final EZDPStringItem              ezCompanyCode_A1;


	/**
	 * ZZZQ910000_APMsg is constructor.
	 * The initialization when the instance of ZZZQ910000_APMsg is generated.
	 */
	public ZZZQ910000_APMsg() {
		this(false, -1);
	}

	/**
	 * ZZZQ910000_APMsg is constructor.
	 * The initialization when the instance of ZZZQ910000_APMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZQ910000_APMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item
		ezBusinessID_A1 = (EZDPStringItem)newItem("ezBusinessID_A1");
		ezCancelFlag_A1 = (EZDPStringItem)newItem("ezCancelFlag_A1");
		ezOnlStartTime_A1 = (EZDPStringItem)newItem("ezOnlStartTime_A1");
		ezOnlEndTime_A1 = (EZDPStringItem)newItem("ezOnlEndTime_A1");
		ezOnlStopFlag_A1 = (EZDPStringItem)newItem("ezOnlStopFlag_A1");
		ezCompanyCode_A1 = (EZDPStringItem)newItem("ezCompanyCode_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZQ910000_APMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZQ910000_APMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {
	
		// It is defined as {"Key name", "Variabler", "Qualifier", "Array length", "Data type", "Number of digits", and "Number of decimal digits" } etc.
		{"ezBusinessID_A1", "ezBusinessID_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
		{"ezCancelFlag_A1", "ezCancelFlag_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
		{"ezOnlStartTime_A1", "ezOnlStartTime_A1", "A1", null, TYPE_HANKAKUEISU, "6", null},
		{"ezOnlEndTime_A1", "ezOnlEndTime_A1", "A1", null, TYPE_HANKAKUEISU, "6", null},
		{"ezOnlStopFlag_A1", "ezOnlStopFlag_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
		{"ezCompanyCode_A1", "ezCompanyCode_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		// It is defined as {"Message display character string", "Indispensable input paragraph", "Minimum value", "Maximum value", "Division of I/O item/output item", "\", "Comma", "Zero suppression", "Plus display flag", "Number of display item digits", "Number of display fraction part digits", "Division at the date of the edit at the date", "Display pattern of the year of the edit at the date", "0 burials of the edit at the date", and "Separator of the edit at the date" } etc.	
        {"_EZBusinessApplicationID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezBusinessID_A1
        {"_EZCancelFlag",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezCancelFlag_A1
        {"_EZOnlineOperationStartTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezOnlStartTime_A1
        {"_EZOnlineOperationEndTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezOnlEndTime_A1
        {"_EZOnlineBlockingFlag",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezOnlStopFlag_A1
        {"_EZCompanyCode",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezCompanyCode_A1
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

