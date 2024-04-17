//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230317104141000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1080_XSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1080 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1080_XSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRCH_REQ_TP_CD_X1*/
	public final EZDSStringItem              prchReqTpCd_X1;

    /** PRCH_REQ_REC_TP_CD_X1*/
	public final EZDSStringItem              prchReqRecTpCd_X1;


	/**
	 * NPAL1080_XSMsg is constructor.
	 * The initialization when the instance of NPAL1080_XSMsg is generated.
	 */
	public NPAL1080_XSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1080_XSMsg is constructor.
	 * The initialization when the instance of NPAL1080_XSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1080_XSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prchReqTpCd_X1 = (EZDSStringItem)newItem("prchReqTpCd_X1");
		prchReqRecTpCd_X1 = (EZDSStringItem)newItem("prchReqRecTpCd_X1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1080_XSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1080_XSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prchReqTpCd_X1", "prchReqTpCd_X1", "X1", null, TYPE_HANKAKUEISU, "4", null},
	{"prchReqRecTpCd_X1", "prchReqRecTpCd_X1", "X1", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRCH_REQ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqTpCd_X1
        {"PRCH_REQ_REC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqRecTpCd_X1
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

