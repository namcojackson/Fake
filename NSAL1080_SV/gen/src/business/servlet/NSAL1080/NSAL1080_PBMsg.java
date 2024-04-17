//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160122113358000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1080_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1080 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1080_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CR_REBIL_DTL_PK*/
	public final EZDBBigDecimalItem              svcCrRebilDtlPk;

    /** INV_PRINT_FLG*/
	public final EZDBStringItem              invPrintFlg;


	/**
	 * NSAL1080_PBMsg is constructor.
	 * The initialization when the instance of NSAL1080_PBMsg is generated.
	 */
	public NSAL1080_PBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1080_PBMsg is constructor.
	 * The initialization when the instance of NSAL1080_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1080_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcCrRebilDtlPk = (EZDBBigDecimalItem)newItem("svcCrRebilDtlPk");
		invPrintFlg = (EZDBStringItem)newItem("invPrintFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1080_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1080_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcCrRebilDtlPk", "svcCrRebilDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invPrintFlg", "invPrintFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_CR_REBIL_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDtlPk
        {"INV_PRINT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrintFlg
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

