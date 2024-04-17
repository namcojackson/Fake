//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160809111734000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3140_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3140 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3140_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INV_GRP_ATTRB_TXT_SV*/
	public final EZDCStringItem              invGrpAttrbTxt_SV;

    /** INV_GRP_ATTRB_TXT_CD*/
	public final EZDCStringItemArray              invGrpAttrbTxt_CD;

    /** INV_GRP_ATTRB_TXT_SC*/
	public final EZDCStringItemArray              invGrpAttrbTxt_SC;


	/**
	 * NFCL3140_ACMsg is constructor.
	 * The initialization when the instance of NFCL3140_ACMsg is generated.
	 */
	public NFCL3140_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3140_ACMsg is constructor.
	 * The initialization when the instance of NFCL3140_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3140_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		invGrpAttrbTxt_SV = (EZDCStringItem)newItem("invGrpAttrbTxt_SV");
		invGrpAttrbTxt_CD = (EZDCStringItemArray)newItemArray("invGrpAttrbTxt_CD");
		invGrpAttrbTxt_SC = (EZDCStringItemArray)newItemArray("invGrpAttrbTxt_SC");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3140_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3140_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"invGrpAttrbTxt_SV", "invGrpAttrbTxt_SV", "SV", null, TYPE_HANKAKUEISU, "27", null},
	{"invGrpAttrbTxt_CD", "invGrpAttrbTxt_CD", "CD", "99", TYPE_HANKAKUEISU, "27", null},
	{"invGrpAttrbTxt_SC", "invGrpAttrbTxt_SC", "SC", "99", TYPE_HANKAKUEISU, "27", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INV_GRP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invGrpAttrbTxt_SV
        {"INV_GRP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invGrpAttrbTxt_CD
        {"INV_GRP_ATTRB_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invGrpAttrbTxt_SC
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

