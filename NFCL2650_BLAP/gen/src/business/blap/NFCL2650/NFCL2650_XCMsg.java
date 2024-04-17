//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180515140156000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2650_XCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL2650;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL2650 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL2650_XCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** STMT_SQ_PK_X*/
	public final EZDCBigDecimalItem              stmtSqPk_X;

    /** BILL_TO_CUST_CD_X*/
	public final EZDCStringItem              billToCustCd_X;


	/**
	 * NFCL2650_XCMsg is constructor.
	 * The initialization when the instance of NFCL2650_XCMsg is generated.
	 */
	public NFCL2650_XCMsg() {
		this(false, -1);
	}

	/**
	 * NFCL2650_XCMsg is constructor.
	 * The initialization when the instance of NFCL2650_XCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL2650_XCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		stmtSqPk_X = (EZDCBigDecimalItem)newItem("stmtSqPk_X");
		billToCustCd_X = (EZDCStringItem)newItem("billToCustCd_X");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL2650_XCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL2650_XCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"stmtSqPk_X", "stmtSqPk_X", "X", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"billToCustCd_X", "billToCustCd_X", "X", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"STMT_SQ_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stmtSqPk_X
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_X
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
