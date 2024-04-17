//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230228115513000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1200_DBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1200 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1200_DBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRCH_REQ_TP_CD_XX*/
	public final EZDBStringItem              prchReqTpCd_XX;

    /** PRCH_REQ_TP_DESC_TXT_XX*/
	public final EZDBStringItem              prchReqTpDescTxt_XX;


	/**
	 * NPAL1200_DBMsg is constructor.
	 * The initialization when the instance of NPAL1200_DBMsg is generated.
	 */
	public NPAL1200_DBMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1200_DBMsg is constructor.
	 * The initialization when the instance of NPAL1200_DBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1200_DBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prchReqTpCd_XX = (EZDBStringItem)newItem("prchReqTpCd_XX");
		prchReqTpDescTxt_XX = (EZDBStringItem)newItem("prchReqTpDescTxt_XX");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1200_DBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1200_DBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prchReqTpCd_XX", "prchReqTpCd_XX", "XX", null, TYPE_HANKAKUEISU, "4", null},
	{"prchReqTpDescTxt_XX", "prchReqTpDescTxt_XX", "XX", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRCH_REQ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_XX
        {"PRCH_REQ_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpDescTxt_XX
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

