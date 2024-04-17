//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230228115551000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1200_DCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1200 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1200_DCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRCH_REQ_TP_CD_XX*/
	public final EZDCStringItem              prchReqTpCd_XX;

    /** PRCH_REQ_TP_DESC_TXT_XX*/
	public final EZDCStringItem              prchReqTpDescTxt_XX;


	/**
	 * NPAL1200_DCMsg is constructor.
	 * The initialization when the instance of NPAL1200_DCMsg is generated.
	 */
	public NPAL1200_DCMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1200_DCMsg is constructor.
	 * The initialization when the instance of NPAL1200_DCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1200_DCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prchReqTpCd_XX = (EZDCStringItem)newItem("prchReqTpCd_XX");
		prchReqTpDescTxt_XX = (EZDCStringItem)newItem("prchReqTpDescTxt_XX");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1200_DCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1200_DCMsgArray();
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

        {"PRCH_REQ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_XX
        {"PRCH_REQ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpDescTxt_XX
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

