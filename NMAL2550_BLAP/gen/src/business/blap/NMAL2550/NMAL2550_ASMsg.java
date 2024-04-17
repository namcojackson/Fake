//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161125110550000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2550_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2550;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2550 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2550_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DTL_CD_A1*/
	public final EZDSStringItem              xxDtlCd_A1;

    /** DTL_DESC_TXT_A1*/
	public final EZDSStringItem              dtlDescTxt_A1;


	/**
	 * NMAL2550_ASMsg is constructor.
	 * The initialization when the instance of NMAL2550_ASMsg is generated.
	 */
	public NMAL2550_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2550_ASMsg is constructor.
	 * The initialization when the instance of NMAL2550_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2550_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDtlCd_A1 = (EZDSStringItem)newItem("xxDtlCd_A1");
		dtlDescTxt_A1 = (EZDSStringItem)newItem("dtlDescTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2550_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2550_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDtlCd_A1", "xxDtlCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"dtlDescTxt_A1", "dtlDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCd_A1
        {"DTL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dtlDescTxt_A1
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
