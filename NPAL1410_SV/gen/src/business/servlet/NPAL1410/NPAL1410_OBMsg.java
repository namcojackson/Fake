//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190402141953000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1410_OBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1410;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1410 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1410_OBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SER_NUM_O*/
	public final EZDBStringItem              serNum_O;

    /** SVC_CONFIG_MSTR_PK_O*/
	public final EZDBBigDecimalItem              svcConfigMstrPk_O;

    /** MDSE_CD_O*/
	public final EZDBStringItem              mdseCd_O;


	/**
	 * NPAL1410_OBMsg is constructor.
	 * The initialization when the instance of NPAL1410_OBMsg is generated.
	 */
	public NPAL1410_OBMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1410_OBMsg is constructor.
	 * The initialization when the instance of NPAL1410_OBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1410_OBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		serNum_O = (EZDBStringItem)newItem("serNum_O");
		svcConfigMstrPk_O = (EZDBBigDecimalItem)newItem("svcConfigMstrPk_O");
		mdseCd_O = (EZDBStringItem)newItem("mdseCd_O");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1410_OBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1410_OBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"serNum_O", "serNum_O", "O", null, TYPE_HANKAKUEISU, "30", null},
	{"svcConfigMstrPk_O", "svcConfigMstrPk_O", "O", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_O", "mdseCd_O", "O", null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_O
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_O
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_O
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

