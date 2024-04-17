//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180328152023000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1360_RBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1360;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1360 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1360_RBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRNT_MDSE_CD_R*/
	public final EZDBStringItem              prntMdseCd_R;

    /** CMPSN_MSG_PK*/
	public final EZDBBigDecimalItem              cmpsnMsgPk;

    /** CMPSN_MSG_SEG_ID*/
	public final EZDBBigDecimalItem              cmpsnMsgSegId;

    /** CMPSN_MSG_TXT*/
	public final EZDBStringItem              cmpsnMsgTxt;


	/**
	 * NPAL1360_RBMsg is constructor.
	 * The initialization when the instance of NPAL1360_RBMsg is generated.
	 */
	public NPAL1360_RBMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1360_RBMsg is constructor.
	 * The initialization when the instance of NPAL1360_RBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1360_RBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prntMdseCd_R = (EZDBStringItem)newItem("prntMdseCd_R");
		cmpsnMsgPk = (EZDBBigDecimalItem)newItem("cmpsnMsgPk");
		cmpsnMsgSegId = (EZDBBigDecimalItem)newItem("cmpsnMsgSegId");
		cmpsnMsgTxt = (EZDBStringItem)newItem("cmpsnMsgTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1360_RBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1360_RBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prntMdseCd_R", "prntMdseCd_R", "R", null, TYPE_HANKAKUEISU, "16", null},
	{"cmpsnMsgPk", "cmpsnMsgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cmpsnMsgSegId", "cmpsnMsgSegId", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnMsgTxt", "cmpsnMsgTxt", null, null, TYPE_HANKAKUEISU, "90", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRNT_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntMdseCd_R
        {"CMPSN_MSG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgPk
        {"CMPSN_MSG_SEG_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgSegId
        {"CMPSN_MSG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnMsgTxt
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

