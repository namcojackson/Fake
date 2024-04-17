//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231108093943000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6720_WSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6720 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6720_WSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CUST_SPCL_MSG_PK_W1*/
	public final EZDSBigDecimalItem              dsCustSpclMsgPk_W1;

    /** _EZUpdateDateTime_W1*/
	public final EZDSStringItem              ezUpTime_W1;

    /** _EZUpTimeZone_W1*/
	public final EZDSStringItem              ezUpTimeZone_W1;


	/**
	 * NMAL6720_WSMsg is constructor.
	 * The initialization when the instance of NMAL6720_WSMsg is generated.
	 */
	public NMAL6720_WSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6720_WSMsg is constructor.
	 * The initialization when the instance of NMAL6720_WSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6720_WSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsCustSpclMsgPk_W1 = (EZDSBigDecimalItem)newItem("dsCustSpclMsgPk_W1");
		ezUpTime_W1 = (EZDSStringItem)newItem("ezUpTime_W1");
		ezUpTimeZone_W1 = (EZDSStringItem)newItem("ezUpTimeZone_W1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6720_WSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6720_WSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsCustSpclMsgPk_W1", "dsCustSpclMsgPk_W1", "W1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_W1", "ezUpTime_W1", "W1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_W1", "ezUpTimeZone_W1", "W1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CUST_SPCL_MSG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSpclMsgPk_W1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_W1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_W1
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

