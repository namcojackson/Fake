//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231106120032000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6700_OSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6700;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6700 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6700_OSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_RELN_PK_O1*/
	public final EZDSBigDecimalItem              dsAcctRelnPk_O1;

    /** _EZUpdateDateTime_O1*/
	public final EZDSStringItem              ezUpTime_O1;

    /** _EZUpTimeZone_O1*/
	public final EZDSStringItem              ezUpTimeZone_O1;


	/**
	 * NMAL6700_OSMsg is constructor.
	 * The initialization when the instance of NMAL6700_OSMsg is generated.
	 */
	public NMAL6700_OSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6700_OSMsg is constructor.
	 * The initialization when the instance of NMAL6700_OSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6700_OSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctRelnPk_O1 = (EZDSBigDecimalItem)newItem("dsAcctRelnPk_O1");
		ezUpTime_O1 = (EZDSStringItem)newItem("ezUpTime_O1");
		ezUpTimeZone_O1 = (EZDSStringItem)newItem("ezUpTimeZone_O1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6700_OSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6700_OSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctRelnPk_O1", "dsAcctRelnPk_O1", "O1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_O1", "ezUpTime_O1", "O1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_O1", "ezUpTimeZone_O1", "O1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctRelnPk_O1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_O1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_O1
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

