//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20191226145108000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3200_TSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3200 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3200_TSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MSTR_PRO_NUM*/
	public final EZDSStringItem              mstrProNum;

    /** PRO_NUM*/
	public final EZDSStringItem              proNum;

    /** TRX_LINE_NUM*/
	public final EZDSStringItem              trxLineNum;

    /** SO_NUM*/
	public final EZDSStringItem              soNum;

    /** TRX_HDR_NUM*/
	public final EZDSStringItem              trxHdrNum;


	/**
	 * NLBL3200_TSMsg is constructor.
	 * The initialization when the instance of NLBL3200_TSMsg is generated.
	 */
	public NLBL3200_TSMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3200_TSMsg is constructor.
	 * The initialization when the instance of NLBL3200_TSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3200_TSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mstrProNum = (EZDSStringItem)newItem("mstrProNum");
		proNum = (EZDSStringItem)newItem("proNum");
		trxLineNum = (EZDSStringItem)newItem("trxLineNum");
		soNum = (EZDSStringItem)newItem("soNum");
		trxHdrNum = (EZDSStringItem)newItem("trxHdrNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3200_TSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3200_TSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mstrProNum", "mstrProNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"proNum", "proNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"trxLineNum", "trxLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"soNum", "soNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"trxHdrNum", "trxHdrNum", null, null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MSTR_PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrProNum
        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum
        {"TRX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxLineNum
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum
        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum
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

