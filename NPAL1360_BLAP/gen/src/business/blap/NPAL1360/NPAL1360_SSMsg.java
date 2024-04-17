//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180328151518000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1360_SSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1360 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1360_SSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WRK_ORD_DTL_LINE_NUM_S1*/
	public final EZDSStringItem              wrkOrdDtlLineNum_S1;

    /** SER_NUM_S1*/
	public final EZDSStringItem              serNum_S1;


	/**
	 * NPAL1360_SSMsg is constructor.
	 * The initialization when the instance of NPAL1360_SSMsg is generated.
	 */
	public NPAL1360_SSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1360_SSMsg is constructor.
	 * The initialization when the instance of NPAL1360_SSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1360_SSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wrkOrdDtlLineNum_S1 = (EZDSStringItem)newItem("wrkOrdDtlLineNum_S1");
		serNum_S1 = (EZDSStringItem)newItem("serNum_S1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1360_SSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1360_SSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wrkOrdDtlLineNum_S1", "wrkOrdDtlLineNum_S1", "S1", null, TYPE_HANKAKUEISU, "3", null},
	{"serNum_S1", "serNum_S1", "S1", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WRK_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrkOrdDtlLineNum_S1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_S1
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

